package io.grpc.xds;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.alts.GoogleDefaultChannelBuilder;
import io.grpc.internal.ObjectPool;
import io.grpc.xds.Bootstrapper;
import io.grpc.xds.EnvoyProtoData;
import io.grpc.xds.EnvoyServerProtoData;
import io.grpc.xds.LoadStatsManager;
import io.grpc.xds.XdsLogger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
abstract class XdsClient {

    XdsClient() {
    }

    void cancelClientStatsReport() {
    }

    void cancelClusterDataWatch(String str, ClusterWatcher clusterWatcher) {
    }

    void cancelEndpointDataWatch(String str, EndpointWatcher endpointWatcher) {
    }

    void reportClientStats() {
    }

    abstract void shutdown();

    void watchClusterData(String str, ClusterWatcher clusterWatcher) {
    }

    void watchConfigData(String str, ConfigWatcher configWatcher) {
    }

    void watchEndpointData(String str, EndpointWatcher endpointWatcher) {
    }

    void watchListenerData(int i, ListenerWatcher listenerWatcher) {
    }

    LoadStatsManager.LoadStatsStore addClientStats(String str, @Nullable String str2) {
        throw new UnsupportedOperationException();
    }

    void removeClientStats(String str, @Nullable String str2) {
        throw new UnsupportedOperationException();
    }

    interface ClusterWatcher extends ResourceWatcher {
        void onClusterChanged(ClusterUpdate clusterUpdate);
    }

    interface ConfigWatcher extends ResourceWatcher {
        void onConfigChanged(ConfigUpdate configUpdate);
    }

    interface EndpointWatcher extends ResourceWatcher {
        void onEndpointChanged(EndpointUpdate endpointUpdate);
    }

    interface ListenerWatcher extends ResourceWatcher {
        void onListenerChanged(ListenerUpdate listenerUpdate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface ResourceWatcher {
        void onError(Status status);

        void onResourceDoesNotExist(String str);
    }

    interface XdsClientPoolFactory {
        ObjectPool<XdsClient> newXdsClientObjectPool(Bootstrapper.BootstrapInfo bootstrapInfo);
    }

    static final class ConfigUpdate {
        private final List<EnvoyProtoData.Route> routes;

        private ConfigUpdate(List<EnvoyProtoData.Route> list) {
            this.routes = list;
        }

        static Builder newBuilder() {
            return new Builder();
        }

        List<EnvoyProtoData.Route> getRoutes() {
            return this.routes;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("routes", this.routes).toString();
        }

        static final class Builder {
            private final List<EnvoyProtoData.Route> routes;

            private Builder() {
                this.routes = new ArrayList();
            }

            Builder addRoutes(Collection<EnvoyProtoData.Route> collection) {
                this.routes.addAll(collection);
                return this;
            }

            ConfigUpdate build() {
                Preconditions.checkState(!this.routes.isEmpty(), "routes is empty");
                return new ConfigUpdate(Collections.unmodifiableList(this.routes));
            }
        }
    }

    static final class ClusterUpdate {
        private final String clusterName;

        @Nullable
        private final String edsServiceName;
        private final String lbPolicy;

        @Nullable
        private final String lrsServerName;
        private final EnvoyServerProtoData.UpstreamTlsContext upstreamTlsContext;

        private ClusterUpdate(String str, @Nullable String str2, String str3, @Nullable String str4, @Nullable EnvoyServerProtoData.UpstreamTlsContext upstreamTlsContext) {
            this.clusterName = str;
            this.edsServiceName = str2;
            this.lbPolicy = str3;
            this.lrsServerName = str4;
            this.upstreamTlsContext = upstreamTlsContext;
        }

        static Builder newBuilder() {
            return new Builder();
        }

        String getClusterName() {
            return this.clusterName;
        }

        @Nullable
        String getEdsServiceName() {
            return this.edsServiceName;
        }

        String getLbPolicy() {
            return this.lbPolicy;
        }

        @Nullable
        String getLrsServerName() {
            return this.lrsServerName;
        }

        @Nullable
        EnvoyServerProtoData.UpstreamTlsContext getUpstreamTlsContext() {
            return this.upstreamTlsContext;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("clusterName", this.clusterName).add("edsServiceName", this.edsServiceName).add("lbPolicy", this.lbPolicy).add("lrsServerName", this.lrsServerName).add("upstreamTlsContext", this.upstreamTlsContext).toString();
        }

        public int hashCode() {
            return Objects.hash(this.clusterName, this.edsServiceName, this.lbPolicy, this.lrsServerName, this.upstreamTlsContext);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ClusterUpdate clusterUpdate = (ClusterUpdate) obj;
            return Objects.equals(this.clusterName, clusterUpdate.clusterName) && Objects.equals(this.edsServiceName, clusterUpdate.edsServiceName) && Objects.equals(this.lbPolicy, clusterUpdate.lbPolicy) && Objects.equals(this.lrsServerName, clusterUpdate.lrsServerName) && Objects.equals(this.upstreamTlsContext, clusterUpdate.upstreamTlsContext);
        }

        static final class Builder {
            private String clusterName;

            @Nullable
            private String edsServiceName;
            private String lbPolicy;

            @Nullable
            private String lrsServerName;

            @Nullable
            private EnvoyServerProtoData.UpstreamTlsContext upstreamTlsContext;

            private Builder() {
            }

            Builder setClusterName(String str) {
                this.clusterName = str;
                return this;
            }

            Builder setEdsServiceName(String str) {
                this.edsServiceName = str;
                return this;
            }

            Builder setLbPolicy(String str) {
                this.lbPolicy = str;
                return this;
            }

            Builder setLrsServerName(String str) {
                this.lrsServerName = str;
                return this;
            }

            Builder setUpstreamTlsContext(EnvoyServerProtoData.UpstreamTlsContext upstreamTlsContext) {
                this.upstreamTlsContext = upstreamTlsContext;
                return this;
            }

            ClusterUpdate build() {
                Preconditions.checkState(this.clusterName != null, "clusterName is not set");
                Preconditions.checkState(this.lbPolicy != null, "lbPolicy is not set");
                return new ClusterUpdate(this.clusterName, this.edsServiceName, this.lbPolicy, this.lrsServerName, this.upstreamTlsContext);
            }
        }
    }

    static final class EndpointUpdate {
        private final String clusterName;
        private final List<EnvoyProtoData.DropOverload> dropPolicies;
        private final Map<EnvoyProtoData.Locality, EnvoyProtoData.LocalityLbEndpoints> localityLbEndpointsMap;

        private EndpointUpdate(String str, Map<EnvoyProtoData.Locality, EnvoyProtoData.LocalityLbEndpoints> map, List<EnvoyProtoData.DropOverload> list) {
            this.clusterName = str;
            this.localityLbEndpointsMap = map;
            this.dropPolicies = list;
        }

        static Builder newBuilder() {
            return new Builder();
        }

        String getClusterName() {
            return this.clusterName;
        }

        Map<EnvoyProtoData.Locality, EnvoyProtoData.LocalityLbEndpoints> getLocalityLbEndpointsMap() {
            return Collections.unmodifiableMap(this.localityLbEndpointsMap);
        }

        List<EnvoyProtoData.DropOverload> getDropPolicies() {
            return Collections.unmodifiableList(this.dropPolicies);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            EndpointUpdate endpointUpdate = (EndpointUpdate) obj;
            return Objects.equals(this.clusterName, endpointUpdate.clusterName) && Objects.equals(this.localityLbEndpointsMap, endpointUpdate.localityLbEndpointsMap) && Objects.equals(this.dropPolicies, endpointUpdate.dropPolicies);
        }

        public int hashCode() {
            return Objects.hash(this.clusterName, this.localityLbEndpointsMap, this.dropPolicies);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("clusterName", this.clusterName).add("localityLbEndpointsMap", this.localityLbEndpointsMap).add("dropPolicies", this.dropPolicies).toString();
        }

        static final class Builder {
            private String clusterName;
            private List<EnvoyProtoData.DropOverload> dropPolicies;
            private Map<EnvoyProtoData.Locality, EnvoyProtoData.LocalityLbEndpoints> localityLbEndpointsMap;

            private Builder() {
                this.localityLbEndpointsMap = new LinkedHashMap();
                this.dropPolicies = new ArrayList();
            }

            Builder setClusterName(String str) {
                this.clusterName = str;
                return this;
            }

            Builder addLocalityLbEndpoints(EnvoyProtoData.Locality locality, EnvoyProtoData.LocalityLbEndpoints localityLbEndpoints) {
                this.localityLbEndpointsMap.put(locality, localityLbEndpoints);
                return this;
            }

            Builder addDropPolicy(EnvoyProtoData.DropOverload dropOverload) {
                this.dropPolicies.add(dropOverload);
                return this;
            }

            EndpointUpdate build() {
                Preconditions.checkState(this.clusterName != null, "clusterName is not set");
                return new EndpointUpdate(this.clusterName, ImmutableMap.copyOf((Map) this.localityLbEndpointsMap), ImmutableList.copyOf((Collection) this.dropPolicies));
            }
        }
    }

    static final class ListenerUpdate {
        private final EnvoyServerProtoData.Listener listener;

        private ListenerUpdate(EnvoyServerProtoData.Listener listener) {
            this.listener = listener;
        }

        static Builder newBuilder() {
            return new Builder();
        }

        public EnvoyServerProtoData.Listener getListener() {
            return this.listener;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("listener", this.listener).toString();
        }

        static final class Builder {
            private EnvoyServerProtoData.Listener listener;

            private Builder() {
            }

            Builder setListener(EnvoyServerProtoData.Listener listener) {
                this.listener = listener;
                return this;
            }

            ListenerUpdate build() {
                Preconditions.checkState(this.listener != null, "listener is not set");
                return new ListenerUpdate(this.listener);
            }
        }
    }

    static abstract class XdsClientFactory {
        XdsClientFactory() {
        }

        abstract XdsClient createXdsClient();
    }

    static final class RefCountedXdsClientObjectPool implements ObjectPool<XdsClient> {
        private final XdsClientFactory xdsClientFactory;
        @Nullable
        XdsClient xdsClient;
        private int refCount;

        RefCountedXdsClientObjectPool(XdsClientFactory xdsClientFactory) {
            this.xdsClientFactory = (XdsClientFactory) Preconditions.checkNotNull(xdsClientFactory, "xdsClientFactory");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.internal.ObjectPool
        public synchronized XdsClient getObject() {
            if (this.xdsClient == null) {
                Preconditions.checkState(this.refCount == 0, "Bug: refCount should be zero while xdsClient is null");
                this.xdsClient = this.xdsClientFactory.createXdsClient();
            }
            this.refCount++;
            return this.xdsClient;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.internal.ObjectPool
        public synchronized XdsClient returnObject(Object obj) {
            XdsClient xdsClient = this.xdsClient;
            Preconditions.checkState(obj == xdsClient, "Bug: the returned object '%s' does not match current XdsClient '%s'", obj, xdsClient);
            int i = this.refCount - 1;
            this.refCount = i;
            Preconditions.checkState(i >= 0, "Bug: refCount of XdsClient less than 0");
            if (this.refCount == 0) {
                this.xdsClient.shutdown();
                this.xdsClient = null;
            }
            return null;
        }
    }

    static abstract class XdsChannelFactory {
        private static final String XDS_V3_SERVER_FEATURE = "xds_v3";
        static boolean experimentalV3SupportEnvVar = Boolean.parseBoolean(System.getenv("GRPC_XDS_EXPERIMENTAL_V3_SUPPORT"));
        private static final XdsChannelFactory DEFAULT_INSTANCE = new XdsChannelFactory() { // from class: io.grpc.xds.XdsClient.XdsChannelFactory.1
            @Override
                // io.grpc.xds.XdsClient.XdsChannelFactory
            XdsChannel createChannel(List<Bootstrapper.ServerInfo> list) {
                ManagedChannelBuilder<?> managedChannelBuilderForTarget;
                Preconditions.checkArgument(!list.isEmpty(), "No management server provided.");
                XdsLogger xdsLoggerWithPrefix = XdsLogger.withPrefix("xds-client-channel-factory");
                Bootstrapper.ServerInfo serverInfo = list.get(0);
                String serverUri = serverInfo.getServerUri();
                xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.INFO, "Creating channel to {0}", serverUri);
                Iterator<Bootstrapper.ChannelCreds> it2 = serverInfo.getChannelCredentials().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        managedChannelBuilderForTarget = null;
                        break;
                    }
                    if (it2.next().getType().equals("google_default")) {
                        xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.INFO, "Using channel credentials: google_default");
                        managedChannelBuilderForTarget = GoogleDefaultChannelBuilder.forTarget(serverUri);
                        break;
                    }
                }
                if (managedChannelBuilderForTarget == null) {
                    xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.INFO, "Using default channel credentials");
                    managedChannelBuilderForTarget = ManagedChannelBuilder.forTarget(serverUri);
                }
                return new XdsChannel(managedChannelBuilderForTarget.keepAliveTime(5L, TimeUnit.MINUTES).build(), experimentalV3SupportEnvVar && serverInfo.getServerFeatures().contains(XdsChannelFactory.XDS_V3_SERVER_FEATURE));
            }
        };

        XdsChannelFactory() {
        }

        static XdsChannelFactory getInstance() {
            return DEFAULT_INSTANCE;
        }

        abstract XdsChannel createChannel(List<Bootstrapper.ServerInfo> list);
    }

    static final class XdsChannel {
        private final ManagedChannel managedChannel;
        private final boolean useProtocolV3;

        XdsChannel(ManagedChannel managedChannel, boolean z) {
            this.managedChannel = managedChannel;
            this.useProtocolV3 = z;
        }

        ManagedChannel getManagedChannel() {
            return this.managedChannel;
        }

        boolean isUseProtocolV3() {
            return this.useProtocolV3;
        }
    }
}
