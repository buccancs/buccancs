package io.grpc.xds;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import io.grpc.ConnectivityState;
import io.grpc.EquivalentAddressGroup;
import io.grpc.InternalLogId;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.LoadBalancerRegistry;
import io.grpc.Status;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServiceConfigUtil;
import io.grpc.util.ForwardingLoadBalancerHelper;
import io.grpc.util.GracefulSwitchLoadBalancer;
import io.grpc.xds.CdsLoadBalancerProvider;
import io.grpc.xds.EdsLoadBalancerProvider;
import io.grpc.xds.EnvoyServerProtoData;
import io.grpc.xds.XdsClient;
import io.grpc.xds.XdsLogger;
import io.grpc.xds.XdsSubchannelPickers;
import io.grpc.xds.internal.sds.SslContextProvider;
import io.grpc.xds.internal.sds.TlsContextManager;
import io.grpc.xds.internal.sds.TlsContextManagerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public final class CdsLoadBalancer extends LoadBalancer {
    private static final String XDS_SECURITY_ENV_VAR = "GRPC_XDS_EXPERIMENTAL_SECURITY_SUPPORT";
    private final LoadBalancerRegistry lbRegistry;
    private final XdsLogger logger;
    private final GracefulSwitchLoadBalancer switchingLoadBalancer;
    private final TlsContextManager tlsContextManager;
    @Nullable
    private String clusterName;
    private boolean enableXdsSecurity;
    @Nullable
    private XdsClient xdsClient;

    @Nullable
    private ObjectPool<XdsClient> xdsClientPool;

    CdsLoadBalancer(LoadBalancer.Helper helper) {
        this(helper, LoadBalancerRegistry.getDefaultRegistry(), TlsContextManagerImpl.getInstance());
    }

    CdsLoadBalancer(LoadBalancer.Helper helper, LoadBalancerRegistry loadBalancerRegistry, TlsContextManager tlsContextManager) {
        Preconditions.checkNotNull(helper, "helper");
        this.lbRegistry = loadBalancerRegistry;
        this.switchingLoadBalancer = new GracefulSwitchLoadBalancer(helper);
        this.tlsContextManager = tlsContextManager;
        XdsLogger xdsLoggerWithLogId = XdsLogger.withLogId(InternalLogId.allocate("cds-lb", helper.getAuthority()));
        this.logger = xdsLoggerWithLogId;
        xdsLoggerWithLogId.log(XdsLogger.XdsLogLevel.INFO, "Created");
    }

    @Override // io.grpc.LoadBalancer
    public boolean canHandleEmptyAddressListFromNameResolution() {
        return true;
    }

    void setXdsSecurity(boolean z) {
        this.enableXdsSecurity = z;
    }

    @Override // io.grpc.LoadBalancer
    public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        this.logger.log(XdsLogger.XdsLogLevel.DEBUG, "Received resolution result: {0}", resolvedAddresses);
        if (this.xdsClientPool == null) {
            ObjectPool<XdsClient> objectPool = (ObjectPool) resolvedAddresses.getAttributes().get(XdsAttributes.XDS_CLIENT_POOL);
            this.xdsClientPool = objectPool;
            Preconditions.checkNotNull(objectPool, "missing xDS client pool");
            this.xdsClient = this.xdsClientPool.getObject();
        }
        Object loadBalancingPolicyConfig = resolvedAddresses.getLoadBalancingPolicyConfig();
        Preconditions.checkNotNull(loadBalancingPolicyConfig, "missing CDS lb config");
        CdsLoadBalancerProvider.CdsConfig cdsConfig = (CdsLoadBalancerProvider.CdsConfig) loadBalancingPolicyConfig;
        this.logger.log(XdsLogger.XdsLogLevel.INFO, "Received CDS lb config: cluster={0}", cdsConfig.name);
        if (!cdsConfig.name.equals(this.clusterName)) {
            this.switchingLoadBalancer.switchTo(new ClusterBalancerFactory(cdsConfig.name));
        }
        this.switchingLoadBalancer.handleResolvedAddresses(resolvedAddresses);
        this.clusterName = cdsConfig.name;
    }

    @Override // io.grpc.LoadBalancer
    public void handleNameResolutionError(Status status) {
        this.logger.log(XdsLogger.XdsLogLevel.WARNING, "Received name resolution error: {0}", status);
        this.switchingLoadBalancer.handleNameResolutionError(status);
    }

    @Override // io.grpc.LoadBalancer
    public void shutdown() {
        this.logger.log(XdsLogger.XdsLogLevel.INFO, "Shutdown");
        this.switchingLoadBalancer.shutdown();
        ObjectPool<XdsClient> objectPool = this.xdsClientPool;
        if (objectPool != null) {
            objectPool.returnObject(this.xdsClient);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isXdsSecurityEnabled() {
        return this.enableXdsSecurity || Boolean.valueOf(System.getenv(XDS_SECURITY_ENV_VAR)).booleanValue();
    }

    private static final class EdsLoadBalancingHelper extends ForwardingLoadBalancerHelper {
        private final LoadBalancer.Helper delegate;
        private final AtomicReference<SslContextProvider> sslContextProvider;

        EdsLoadBalancingHelper(LoadBalancer.Helper helper, AtomicReference<SslContextProvider> atomicReference) {
            this.delegate = helper;
            this.sslContextProvider = atomicReference;
        }

        private static List<EquivalentAddressGroup> addUpstreamTlsContext(List<EquivalentAddressGroup> list, EnvoyServerProtoData.UpstreamTlsContext upstreamTlsContext) {
            if (upstreamTlsContext == null || list == null) {
                return list;
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (EquivalentAddressGroup equivalentAddressGroup : list) {
                arrayList.add(new EquivalentAddressGroup(equivalentAddressGroup.getAddresses(), equivalentAddressGroup.getAttributes().toBuilder().set(XdsAttributes.ATTR_UPSTREAM_TLS_CONTEXT, upstreamTlsContext).build()));
            }
            return arrayList;
        }

        @Override // io.grpc.util.ForwardingLoadBalancerHelper
        protected LoadBalancer.Helper delegate() {
            return this.delegate;
        }

        @Override // io.grpc.util.ForwardingLoadBalancerHelper, io.grpc.LoadBalancer.Helper
        public LoadBalancer.Subchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
            if (this.sslContextProvider.get() != null) {
                createSubchannelArgs = createSubchannelArgs.toBuilder().setAddresses(addUpstreamTlsContext(createSubchannelArgs.getAddresses(), this.sslContextProvider.get().getUpstreamTlsContext())).build();
            }
            return this.delegate.createSubchannel(createSubchannelArgs);
        }
    }

    private final class ClusterBalancerFactory extends LoadBalancer.Factory {
        final String clusterName;

        ClusterBalancerFactory(String str) {
            this.clusterName = str;
        }

        public boolean equals(Object obj) {
            if (obj instanceof ClusterBalancerFactory) {
                return this.clusterName.equals(((ClusterBalancerFactory) obj).clusterName);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.clusterName);
        }

        @Override // io.grpc.LoadBalancer.Factory
        public LoadBalancer newLoadBalancer(final LoadBalancer.Helper helper) {
            return new LoadBalancer() { // from class: io.grpc.xds.CdsLoadBalancer.ClusterBalancerFactory.1

                @Nullable
                ClusterWatcherImpl clusterWatcher;

                @Override // io.grpc.LoadBalancer
                public boolean canHandleEmptyAddressListFromNameResolution() {
                    return true;
                }

                @Override // io.grpc.LoadBalancer
                public void handleNameResolutionError(Status status) {
                    ClusterWatcherImpl clusterWatcherImpl = this.clusterWatcher;
                    if (clusterWatcherImpl == null || clusterWatcherImpl.edsBalancer == null) {
                        helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new XdsSubchannelPickers.ErrorPicker(status));
                    }
                }

                @Override // io.grpc.LoadBalancer
                public void shutdown() {
                    ClusterWatcherImpl clusterWatcherImpl = this.clusterWatcher;
                    if (clusterWatcherImpl != null) {
                        if (clusterWatcherImpl.edsBalancer != null) {
                            this.clusterWatcher.edsBalancer.shutdown();
                        }
                        CdsLoadBalancer.this.xdsClient.cancelClusterDataWatch(ClusterBalancerFactory.this.clusterName, this.clusterWatcher);
                        CdsLoadBalancer.this.logger.log(XdsLogger.XdsLogLevel.INFO, "Cancelled cluster watcher on {0} with xDS client {1}", ClusterBalancerFactory.this.clusterName, CdsLoadBalancer.this.xdsClient);
                    }
                }

                @Override // io.grpc.LoadBalancer
                public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
                    if (this.clusterWatcher == null) {
                        this.clusterWatcher = CdsLoadBalancer.this.new ClusterWatcherImpl(helper, resolvedAddresses);
                        CdsLoadBalancer.this.logger.log(XdsLogger.XdsLogLevel.INFO, "Start cluster watcher on {0} with xDS client {1}", ClusterBalancerFactory.this.clusterName, CdsLoadBalancer.this.xdsClient);
                        CdsLoadBalancer.this.xdsClient.watchClusterData(ClusterBalancerFactory.this.clusterName, this.clusterWatcher);
                    }
                }
            };
        }
    }

    private final class ClusterWatcherImpl implements XdsClient.ClusterWatcher {

        final EdsLoadBalancingHelper helper;
        final LoadBalancer.ResolvedAddresses resolvedAddresses;
        @Nullable
        LoadBalancer edsBalancer;

        ClusterWatcherImpl(LoadBalancer.Helper helper, LoadBalancer.ResolvedAddresses resolvedAddresses) {
            this.helper = new EdsLoadBalancingHelper(helper, new AtomicReference());
            this.resolvedAddresses = resolvedAddresses;
        }

        @Override // io.grpc.xds.XdsClient.ClusterWatcher
        public void onClusterChanged(XdsClient.ClusterUpdate clusterUpdate) {
            if (CdsLoadBalancer.this.logger.isLoggable(XdsLogger.XdsLogLevel.INFO)) {
                XdsLogger xdsLogger = CdsLoadBalancer.this.logger;
                XdsLogger.XdsLogLevel xdsLogLevel = XdsLogger.XdsLogLevel.INFO;
                Object[] objArr = new Object[5];
                objArr[0] = CdsLoadBalancer.this.xdsClient;
                objArr[1] = clusterUpdate.getClusterName();
                objArr[2] = clusterUpdate.getEdsServiceName();
                objArr[3] = clusterUpdate.getLbPolicy();
                objArr[4] = Boolean.valueOf(clusterUpdate.getLrsServerName() != null);
                xdsLogger.log(xdsLogLevel, "Received cluster update from xDS client {0}: cluster_name={1}, eds_service_name={2}, lb_policy={3}, report_load={4}", objArr);
            }
            Preconditions.checkArgument(clusterUpdate.getLbPolicy().equals("round_robin"), "can only support round_robin policy");
            LoadBalancerProvider provider = CdsLoadBalancer.this.lbRegistry.getProvider(clusterUpdate.getLbPolicy());
            EdsLoadBalancerProvider.EdsConfig edsConfig = new EdsLoadBalancerProvider.EdsConfig(clusterUpdate.getClusterName(), clusterUpdate.getEdsServiceName(), clusterUpdate.getLrsServerName(), new ServiceConfigUtil.PolicySelection(provider, ImmutableMap.of(), provider.parseLoadBalancingPolicyConfig(ImmutableMap.of()).getConfig()));
            if (CdsLoadBalancer.this.isXdsSecurityEnabled()) {
                updateSslContextProvider(clusterUpdate.getUpstreamTlsContext());
            }
            if (this.edsBalancer == null) {
                this.edsBalancer = CdsLoadBalancer.this.lbRegistry.getProvider("eds_experimental").newLoadBalancer(this.helper);
            }
            this.edsBalancer.handleResolvedAddresses(this.resolvedAddresses.toBuilder().setLoadBalancingPolicyConfig(edsConfig).build());
        }

        private void updateSslContextProvider(EnvoyServerProtoData.UpstreamTlsContext upstreamTlsContext) {
            SslContextProvider sslContextProvider = (SslContextProvider) this.helper.sslContextProvider.get();
            if (sslContextProvider != null) {
                if (sslContextProvider.getUpstreamTlsContext().equals(upstreamTlsContext)) {
                    return;
                } else {
                    CdsLoadBalancer.this.tlsContextManager.releaseClientSslContextProvider(sslContextProvider);
                }
            }
            if (upstreamTlsContext != null) {
                this.helper.sslContextProvider.set(CdsLoadBalancer.this.tlsContextManager.findOrCreateClientSslContextProvider(upstreamTlsContext));
            } else {
                this.helper.sslContextProvider.set(null);
            }
        }

        @Override // io.grpc.xds.XdsClient.ResourceWatcher
        public void onResourceDoesNotExist(String str) {
            CdsLoadBalancer.this.logger.log(XdsLogger.XdsLogLevel.INFO, "Resource {0} is unavailable", str);
            LoadBalancer loadBalancer = this.edsBalancer;
            if (loadBalancer != null) {
                loadBalancer.shutdown();
                this.edsBalancer = null;
            }
            this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new XdsSubchannelPickers.ErrorPicker(Status.UNAVAILABLE.withDescription("Resource " + str + " is unavailable")));
        }

        @Override // io.grpc.xds.XdsClient.ResourceWatcher
        public void onError(Status status) {
            CdsLoadBalancer.this.logger.log(XdsLogger.XdsLogLevel.WARNING, "Received error from xDS client {0}: {1}: {2}", CdsLoadBalancer.this.xdsClient, status.getCode(), status.getDescription());
            if (this.edsBalancer == null) {
                this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new XdsSubchannelPickers.ErrorPicker(status));
            }
        }
    }
}
