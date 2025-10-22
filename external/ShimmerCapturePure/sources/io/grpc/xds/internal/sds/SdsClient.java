package io.grpc.xds.internal.sds;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.Value;
import io.grpc.CallCredentials;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.epoll.Epoll;
import io.grpc.netty.shaded.io.netty.channel.epoll.EpollDomainSocketChannel;
import io.grpc.netty.shaded.io.netty.channel.epoll.EpollEventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.unix.DomainSocketAddress;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultThreadFactory;
import io.grpc.stub.StreamObserver;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryRequest;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponse;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Node;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ApiConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.GrpcService;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SdsSecretConfig;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.Secret;
import io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2.SecretDiscoveryServiceGrpc;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes3.dex */
final class SdsClient {
    private static final String SECRET_TYPE_URL = "type.googleapis.com/envoy.api.v2.auth.Secret";
    private static final EventLoopGroupResource eventLoopGroupResource;
    private static final Logger logger = Logger.getLogger(SdsClient.class.getName());

    static {
        eventLoopGroupResource = Epoll.isAvailable() ? new EventLoopGroupResource("SdsClient") : null;
    }

    private final CallCredentials callCredentials;
    private final Node clientNode;
    private final SdsSecretConfig sdsSecretConfig;
    private final Executor watcherExecutor;
    private ManagedChannel channel;
    private EventLoopGroup eventLoopGroup;
    private DiscoveryResponse lastResponse;
    private StreamObserver<DiscoveryRequest> requestObserver;
    private ResponseObserver responseObserver;
    private SecretDiscoveryServiceGrpc.SecretDiscoveryServiceStub secretDiscoveryServiceStub;
    private SecretWatcher watcher;

    private SdsClient(SdsSecretConfig sdsSecretConfig, Node node, Executor executor, ManagedChannel managedChannel, EventLoopGroup eventLoopGroup, CallCredentials callCredentials) {
        Preconditions.checkNotNull(sdsSecretConfig, "sdsSecretConfig");
        Preconditions.checkNotNull(node, "node");
        this.sdsSecretConfig = sdsSecretConfig;
        this.clientNode = node;
        this.watcherExecutor = executor;
        this.eventLoopGroup = eventLoopGroup;
        Preconditions.checkNotNull(managedChannel, "channel");
        this.channel = managedChannel;
        this.callCredentials = callCredentials;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void start() {
        if (this.requestObserver == null) {
            SecretDiscoveryServiceGrpc.SecretDiscoveryServiceStub secretDiscoveryServiceStubNewStub = SecretDiscoveryServiceGrpc.newStub(this.channel);
            this.secretDiscoveryServiceStub = secretDiscoveryServiceStubNewStub;
            CallCredentials callCredentials = this.callCredentials;
            if (callCredentials != null) {
                this.secretDiscoveryServiceStub = (SecretDiscoveryServiceGrpc.SecretDiscoveryServiceStub) secretDiscoveryServiceStubNewStub.withCallCredentials(callCredentials);
            }
            ResponseObserver responseObserver = new ResponseObserver();
            this.responseObserver = responseObserver;
            this.requestObserver = this.secretDiscoveryServiceStub.streamSecrets(responseObserver);
            logger.log(Level.FINEST, "Stream created for {0}", this.sdsSecretConfig);
        }
    }

    void shutdown() {
        StreamObserver<DiscoveryRequest> streamObserver = this.requestObserver;
        if (streamObserver != null) {
            streamObserver.onCompleted();
            this.requestObserver = null;
            this.channel.shutdownNow();
            EventLoopGroup eventLoopGroup = this.eventLoopGroup;
            if (eventLoopGroup != null) {
                this.eventLoopGroup = (EventLoopGroup) SharedResourceHolder.release(eventLoopGroupResource, eventLoopGroup);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processDiscoveryResponse(final DiscoveryResponse discoveryResponse) {
        this.watcherExecutor.execute(new Runnable() { // from class: io.grpc.xds.internal.sds.SdsClient.1
            @Override // java.lang.Runnable
            public void run() throws UninitializedMessageException {
                try {
                    SdsClient.this.processSecretsFromDiscoveryResponse(discoveryResponse);
                    SdsClient.this.lastResponse = discoveryResponse;
                    SdsClient.this.sendDiscoveryRequestOnStream();
                } catch (Throwable th) {
                    SdsClient.this.sendNack(th);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendNack(Throwable th) throws UninitializedMessageException {
        String nonce;
        String versionInfo;
        DiscoveryResponse discoveryResponse = this.lastResponse;
        if (discoveryResponse != null) {
            nonce = discoveryResponse.getNonce();
            versionInfo = this.lastResponse.getVersionInfo();
        } else {
            nonce = "";
            versionInfo = "";
        }
        Status statusFromThrowable = Status.fromThrowable(th);
        DiscoveryRequest discoveryRequestM12547build = DiscoveryRequest.newBuilder().setTypeUrl(SECRET_TYPE_URL).setResponseNonce(nonce).setVersionInfo(versionInfo).addResourceNames(this.sdsSecretConfig.getName()).setErrorDetail(com.google.rpc.Status.newBuilder().setCode(statusFromThrowable.getCode().value()).setMessage(statusFromThrowable.getDescription() != null ? statusFromThrowable.getDescription() : "Secret not updated").m4002build()).setNode(this.clientNode).m12547build();
        logger.log(Level.FINEST, "Sending NACK req={0}", discoveryRequestM12547build);
        this.requestObserver.onNext(discoveryRequestM12547build);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendErrorToWatcher(final Throwable th) {
        final SecretWatcher secretWatcher = this.watcher;
        if (secretWatcher != null) {
            this.watcherExecutor.execute(new Runnable() { // from class: io.grpc.xds.internal.sds.SdsClient.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        secretWatcher.onError(Status.fromThrowable(th));
                    } catch (Throwable th2) {
                        SdsClient.logger.log(Level.SEVERE, "exception from onError", th2);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processSecretsFromDiscoveryResponse(DiscoveryResponse discoveryResponse) throws InvalidProtocolBufferException {
        List<Any> resourcesList = discoveryResponse.getResourcesList();
        Preconditions.checkState(resourcesList.size() == 1, "exactly one resource expected");
        Any any = resourcesList.get(0);
        String typeUrl = any.getTypeUrl();
        Preconditions.checkState(SECRET_TYPE_URL.equals(typeUrl), "wrong value for typeUrl %s", typeUrl);
        processSecret(Secret.parseFrom(any.getValue()));
    }

    private void processSecret(Secret secret) {
        Preconditions.checkState(this.sdsSecretConfig.getName().equals(secret.getName()), "expected secret name %s", this.sdsSecretConfig.getName());
        SecretWatcher secretWatcher = this.watcher;
        if (secretWatcher != null) {
            secretWatcher.onSecretChanged(secret);
        }
    }

    void watchSecret(SecretWatcher secretWatcher) throws UninitializedMessageException {
        Preconditions.checkNotNull(secretWatcher, "secretWatcher");
        Preconditions.checkState(this.watcher == null, "watcher already set");
        this.watcher = secretWatcher;
        if (this.lastResponse == null) {
            sendDiscoveryRequestOnStream();
        } else {
            this.watcherExecutor.execute(new Runnable() { // from class: io.grpc.xds.internal.sds.SdsClient.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        SdsClient sdsClient = SdsClient.this;
                        sdsClient.processSecretsFromDiscoveryResponse(sdsClient.lastResponse);
                    } catch (Throwable th) {
                        SdsClient.logger.log(Level.SEVERE, "from watcherExecutor.execute", th);
                    }
                }
            });
        }
    }

    void cancelSecretWatch(SecretWatcher secretWatcher) {
        Preconditions.checkNotNull(secretWatcher, "secretWatcher");
        Preconditions.checkArgument(secretWatcher == this.watcher, "Incorrect secretWatcher to cancel");
        this.watcher = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendDiscoveryRequestOnStream() throws UninitializedMessageException {
        String nonce;
        String str;
        String versionInfo;
        DiscoveryResponse discoveryResponse = this.lastResponse;
        if (discoveryResponse != null) {
            nonce = discoveryResponse.getNonce();
            versionInfo = this.lastResponse.getVersionInfo();
            str = "Sending ACK req={0}";
        } else {
            nonce = "";
            str = "Sending initial req={0}";
            versionInfo = "";
        }
        DiscoveryRequest discoveryRequestM12547build = DiscoveryRequest.newBuilder().setTypeUrl(SECRET_TYPE_URL).setResponseNonce(nonce).setVersionInfo(versionInfo).addResourceNames(this.sdsSecretConfig.getName()).setNode(this.clientNode).m12547build();
        logger.log(Level.FINEST, str, discoveryRequestM12547build);
        this.requestObserver.onNext(discoveryRequestM12547build);
    }

    interface SecretWatcher {
        void onError(Status status);

        void onSecretChanged(Secret secret);
    }

    static class Factory {
        Factory() {
        }

        static SdsClient createSdsClient(SdsSecretConfig sdsSecretConfig, Node node, Executor executor, Executor executor2) {
            NettyChannelBuilder nettyChannelBuilderForTarget;
            EventLoopGroup eventLoopGroup;
            ChannelInfo channelInfoExtractChannelInfo = extractChannelInfo(sdsSecretConfig.getSdsConfig());
            String str = channelInfoExtractChannelInfo.targetUri;
            String str2 = channelInfoExtractChannelInfo.channelType;
            if (str2 != null && str2.startsWith("inproc")) {
                return new SdsClient(sdsSecretConfig, node, executor, ((InProcessChannelBuilder) InProcessChannelBuilder.forName(str).executor(executor2)).build(), null, channelInfoExtractChannelInfo.callCredentials);
            }
            if (str.startsWith("unix:")) {
                Preconditions.checkState(Epoll.isAvailable(), "Epoll is not available");
                eventLoopGroup = (EventLoopGroup) SharedResourceHolder.get(SdsClient.eventLoopGroupResource);
                nettyChannelBuilderForTarget = NettyChannelBuilder.forAddress(new DomainSocketAddress(str.substring(5))).eventLoopGroup(eventLoopGroup).channelType(EpollDomainSocketChannel.class);
            } else {
                nettyChannelBuilderForTarget = NettyChannelBuilder.forTarget(str);
                eventLoopGroup = null;
            }
            EventLoopGroup eventLoopGroup2 = eventLoopGroup;
            NettyChannelBuilder nettyChannelBuilderUsePlaintext = nettyChannelBuilderForTarget.usePlaintext();
            if (executor2 != null) {
                nettyChannelBuilderUsePlaintext = (NettyChannelBuilder) nettyChannelBuilderUsePlaintext.executor(executor2);
            }
            return new SdsClient(sdsSecretConfig, node, executor, nettyChannelBuilderUsePlaintext.build(), eventLoopGroup2, channelInfoExtractChannelInfo.callCredentials);
        }

        static ChannelInfo extractChannelInfo(ConfigSource configSource) {
            Preconditions.checkNotNull(configSource, "configSource");
            Preconditions.checkArgument(configSource.hasApiConfigSource(), "only configSource with ApiConfigSource supported");
            ApiConfigSource apiConfigSource = configSource.getApiConfigSource();
            Preconditions.checkArgument(ApiConfigSource.ApiType.GRPC.equals(apiConfigSource.getApiType()), "only GRPC ApiConfigSource type supported");
            boolean z = false;
            Preconditions.checkArgument(apiConfigSource.getGrpcServicesCount() == 1, "expecting exactly 1 GrpcService in ApiConfigSource");
            GrpcService grpcServices = apiConfigSource.getGrpcServices(0);
            if (grpcServices.hasGoogleGrpc() && !grpcServices.hasEnvoyGrpc()) {
                z = true;
            }
            Preconditions.checkArgument(z, "only GoogleGrpc expected in GrpcService");
            GrpcService.GoogleGrpc googleGrpc = grpcServices.getGoogleGrpc();
            CallCredentials verifiedCredentials = getVerifiedCredentials(googleGrpc);
            String targetUri = googleGrpc.getTargetUri();
            String stringValue = googleGrpc.hasConfig() ? ((Value) googleGrpc.getConfig().getFieldsMap().get("channelType")).getStringValue() : null;
            Preconditions.checkArgument(true ^ Strings.isNullOrEmpty(targetUri), "targetUri in GoogleGrpc is empty!");
            return new ChannelInfo(targetUri, stringValue, verifiedCredentials);
        }

        private static CallCredentials getVerifiedCredentials(GrpcService.GoogleGrpc googleGrpc) {
            String credentialsFactoryName = googleGrpc.getCredentialsFactoryName();
            if (credentialsFactoryName.isEmpty()) {
                Preconditions.checkArgument(!googleGrpc.hasChannelCredentials() && googleGrpc.getCallCredentialsCount() == 0, "No credentials supported in GoogleGrpc");
                SdsClient.logger.warning("No CallCredentials specified.");
                return null;
            }
            Preconditions.checkArgument(credentialsFactoryName.equals(FileBasedPluginCredential.PLUGIN_NAME), "factory name should be %s", FileBasedPluginCredential.PLUGIN_NAME);
            if (googleGrpc.hasChannelCredentials()) {
                Preconditions.checkArgument(googleGrpc.getChannelCredentials().hasLocalCredentials(), "only GoogleLocalCredentials supported");
            }
            if (googleGrpc.getCallCredentialsCount() > 0) {
                Preconditions.checkArgument(googleGrpc.getCallCredentialsCount() == 1, "Exactly one CallCredential expected in GoogleGrpc");
                GrpcService.GoogleGrpc.CallCredentials callCredentials = googleGrpc.getCallCredentials(0);
                Preconditions.checkArgument(callCredentials.hasFromPlugin(), "only plugin credential supported");
                return new FileBasedPluginCredential(callCredentials.getFromPlugin());
            }
            SdsClient.logger.warning("No CallCredentials specified.");
            return null;
        }
    }

    static final class ChannelInfo {
        final CallCredentials callCredentials;
        final String channelType;
        final String targetUri;

        private ChannelInfo(String str, String str2, CallCredentials callCredentials) {
            this.targetUri = str;
            this.channelType = str2;
            this.callCredentials = callCredentials;
        }
    }

    private static final class EventLoopGroupResource implements SharedResourceHolder.Resource<EventLoopGroup> {
        private final String name;

        EventLoopGroupResource(String str) {
            this.name = str;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public EventLoopGroup create() {
            return new EpollEventLoopGroup(1, new DefaultThreadFactory(this.name, true));
        }

        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public void close(EventLoopGroup eventLoopGroup) {
            try {
                eventLoopGroup.shutdownGracefully(0L, 0L, TimeUnit.SECONDS).sync();
            } catch (InterruptedException e) {
                SdsClient.logger.log(Level.SEVERE, "from EventLoopGroup.shutdownGracefully", (Throwable) e);
                Thread.currentThread().interrupt();
            }
        }
    }

    private final class ResponseObserver implements StreamObserver<DiscoveryResponse> {
        ResponseObserver() {
        }

        @Override // io.grpc.stub.StreamObserver
        public void onNext(DiscoveryResponse discoveryResponse) {
            SdsClient.logger.log(Level.FINEST, "response={0}", discoveryResponse);
            SdsClient.this.processDiscoveryResponse(discoveryResponse);
        }

        @Override // io.grpc.stub.StreamObserver
        public void onError(Throwable th) {
            SdsClient.this.sendErrorToWatcher(th);
        }

        @Override // io.grpc.stub.StreamObserver
        public void onCompleted() {
            SdsClient.logger.warning("Stream unexpectedly completed.");
        }
    }
}
