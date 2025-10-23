package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

import com.google.protobuf.BlockingRpcChannel;
import com.google.protobuf.BlockingService;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcChannel;
import com.google.protobuf.RpcController;
import com.google.protobuf.RpcUtil;
import com.google.protobuf.Service;
import com.google.protobuf.ServiceException;

/* loaded from: classes3.dex */
public abstract class ListenerDiscoveryService implements Service {

    protected ListenerDiscoveryService() {
    }

    public static Service newReflectiveService(final Interface r1) {
        return new ListenerDiscoveryService() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ListenerDiscoveryService.1
            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ListenerDiscoveryService
            public void deltaListeners(RpcController rpcController, DeltaDiscoveryRequest deltaDiscoveryRequest, RpcCallback<DeltaDiscoveryResponse> rpcCallback) {
                r1.deltaListeners(rpcController, deltaDiscoveryRequest, rpcCallback);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ListenerDiscoveryService
            public void streamListeners(RpcController rpcController, DiscoveryRequest discoveryRequest, RpcCallback<DiscoveryResponse> rpcCallback) {
                r1.streamListeners(rpcController, discoveryRequest, rpcCallback);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ListenerDiscoveryService
            public void fetchListeners(RpcController rpcController, DiscoveryRequest discoveryRequest, RpcCallback<DiscoveryResponse> rpcCallback) {
                r1.fetchListeners(rpcController, discoveryRequest, rpcCallback);
            }
        };
    }

    public static BlockingService newReflectiveBlockingService(final BlockingInterface blockingInterface) {
        return new BlockingService() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ListenerDiscoveryService.2
            public final Descriptors.ServiceDescriptor getDescriptorForType() {
                return ListenerDiscoveryService.getDescriptor();
            }

            public final Message callBlockingMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message message) throws ServiceException {
                if (methodDescriptor.getService() != ListenerDiscoveryService.getDescriptor()) {
                    throw new IllegalArgumentException("Service.callBlockingMethod() given method descriptor for wrong service type.");
                }
                int index = methodDescriptor.getIndex();
                if (index == 0) {
                    return blockingInterface.deltaListeners(rpcController, (DeltaDiscoveryRequest) message);
                }
                if (index == 1) {
                    return blockingInterface.streamListeners(rpcController, (DiscoveryRequest) message);
                }
                if (index == 2) {
                    return blockingInterface.fetchListeners(rpcController, (DiscoveryRequest) message);
                }
                throw new AssertionError("Can't get here.");
            }

            public final Message getRequestPrototype(Descriptors.MethodDescriptor methodDescriptor) {
                if (methodDescriptor.getService() != ListenerDiscoveryService.getDescriptor()) {
                    throw new IllegalArgumentException("Service.getRequestPrototype() given method descriptor for wrong service type.");
                }
                int index = methodDescriptor.getIndex();
                if (index == 0) {
                    return DeltaDiscoveryRequest.getDefaultInstance();
                }
                if (index == 1) {
                    return DiscoveryRequest.getDefaultInstance();
                }
                if (index == 2) {
                    return DiscoveryRequest.getDefaultInstance();
                }
                throw new AssertionError("Can't get here.");
            }

            public final Message getResponsePrototype(Descriptors.MethodDescriptor methodDescriptor) {
                if (methodDescriptor.getService() != ListenerDiscoveryService.getDescriptor()) {
                    throw new IllegalArgumentException("Service.getResponsePrototype() given method descriptor for wrong service type.");
                }
                int index = methodDescriptor.getIndex();
                if (index == 0) {
                    return DeltaDiscoveryResponse.getDefaultInstance();
                }
                if (index == 1) {
                    return DiscoveryResponse.getDefaultInstance();
                }
                if (index == 2) {
                    return DiscoveryResponse.getDefaultInstance();
                }
                throw new AssertionError("Can't get here.");
            }
        };
    }

    public static final Descriptors.ServiceDescriptor getDescriptor() {
        return (Descriptors.ServiceDescriptor) LdsProto.getDescriptor().getServices().get(0);
    }

    public static Stub newStub(RpcChannel rpcChannel) {
        return new Stub(rpcChannel);
    }

    public static BlockingInterface newBlockingStub(BlockingRpcChannel blockingRpcChannel) {
        return new BlockingStub(blockingRpcChannel);
    }

    public abstract void deltaListeners(RpcController rpcController, DeltaDiscoveryRequest deltaDiscoveryRequest, RpcCallback<DeltaDiscoveryResponse> rpcCallback);

    public abstract void fetchListeners(RpcController rpcController, DiscoveryRequest discoveryRequest, RpcCallback<DiscoveryResponse> rpcCallback);

    public abstract void streamListeners(RpcController rpcController, DiscoveryRequest discoveryRequest, RpcCallback<DiscoveryResponse> rpcCallback);

    public final Descriptors.ServiceDescriptor getDescriptorForType() {
        return getDescriptor();
    }

    public final void callMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message message, RpcCallback<Message> rpcCallback) {
        if (methodDescriptor.getService() != getDescriptor()) {
            throw new IllegalArgumentException("Service.callMethod() given method descriptor for wrong service type.");
        }
        int index = methodDescriptor.getIndex();
        if (index == 0) {
            deltaListeners(rpcController, (DeltaDiscoveryRequest) message, RpcUtil.specializeCallback(rpcCallback));
        } else if (index == 1) {
            streamListeners(rpcController, (DiscoveryRequest) message, RpcUtil.specializeCallback(rpcCallback));
        } else {
            if (index == 2) {
                fetchListeners(rpcController, (DiscoveryRequest) message, RpcUtil.specializeCallback(rpcCallback));
                return;
            }
            throw new AssertionError("Can't get here.");
        }
    }

    public final Message getRequestPrototype(Descriptors.MethodDescriptor methodDescriptor) {
        if (methodDescriptor.getService() != getDescriptor()) {
            throw new IllegalArgumentException("Service.getRequestPrototype() given method descriptor for wrong service type.");
        }
        int index = methodDescriptor.getIndex();
        if (index == 0) {
            return DeltaDiscoveryRequest.getDefaultInstance();
        }
        if (index == 1) {
            return DiscoveryRequest.getDefaultInstance();
        }
        if (index == 2) {
            return DiscoveryRequest.getDefaultInstance();
        }
        throw new AssertionError("Can't get here.");
    }

    public final Message getResponsePrototype(Descriptors.MethodDescriptor methodDescriptor) {
        if (methodDescriptor.getService() != getDescriptor()) {
            throw new IllegalArgumentException("Service.getResponsePrototype() given method descriptor for wrong service type.");
        }
        int index = methodDescriptor.getIndex();
        if (index == 0) {
            return DeltaDiscoveryResponse.getDefaultInstance();
        }
        if (index == 1) {
            return DiscoveryResponse.getDefaultInstance();
        }
        if (index == 2) {
            return DiscoveryResponse.getDefaultInstance();
        }
        throw new AssertionError("Can't get here.");
    }

    public interface BlockingInterface {
        DeltaDiscoveryResponse deltaListeners(RpcController rpcController, DeltaDiscoveryRequest deltaDiscoveryRequest) throws ServiceException;

        DiscoveryResponse fetchListeners(RpcController rpcController, DiscoveryRequest discoveryRequest) throws ServiceException;

        DiscoveryResponse streamListeners(RpcController rpcController, DiscoveryRequest discoveryRequest) throws ServiceException;
    }

    public interface Interface {
        void deltaListeners(RpcController rpcController, DeltaDiscoveryRequest deltaDiscoveryRequest, RpcCallback<DeltaDiscoveryResponse> rpcCallback);

        void fetchListeners(RpcController rpcController, DiscoveryRequest discoveryRequest, RpcCallback<DiscoveryResponse> rpcCallback);

        void streamListeners(RpcController rpcController, DiscoveryRequest discoveryRequest, RpcCallback<DiscoveryResponse> rpcCallback);
    }

    public static final class Stub extends ListenerDiscoveryService implements Interface {
        private final RpcChannel channel;

        private Stub(RpcChannel rpcChannel) {
            this.channel = rpcChannel;
        }

        public RpcChannel getChannel() {
            return this.channel;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ListenerDiscoveryService
        public void deltaListeners(RpcController rpcController, DeltaDiscoveryRequest deltaDiscoveryRequest, RpcCallback<DeltaDiscoveryResponse> rpcCallback) {
            this.channel.callMethod((Descriptors.MethodDescriptor) getDescriptor().getMethods().get(0), rpcController, deltaDiscoveryRequest, DeltaDiscoveryResponse.getDefaultInstance(), RpcUtil.generalizeCallback(rpcCallback, DeltaDiscoveryResponse.class, DeltaDiscoveryResponse.getDefaultInstance()));
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ListenerDiscoveryService
        public void streamListeners(RpcController rpcController, DiscoveryRequest discoveryRequest, RpcCallback<DiscoveryResponse> rpcCallback) {
            this.channel.callMethod((Descriptors.MethodDescriptor) getDescriptor().getMethods().get(1), rpcController, discoveryRequest, DiscoveryResponse.getDefaultInstance(), RpcUtil.generalizeCallback(rpcCallback, DiscoveryResponse.class, DiscoveryResponse.getDefaultInstance()));
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ListenerDiscoveryService
        public void fetchListeners(RpcController rpcController, DiscoveryRequest discoveryRequest, RpcCallback<DiscoveryResponse> rpcCallback) {
            this.channel.callMethod((Descriptors.MethodDescriptor) getDescriptor().getMethods().get(2), rpcController, discoveryRequest, DiscoveryResponse.getDefaultInstance(), RpcUtil.generalizeCallback(rpcCallback, DiscoveryResponse.class, DiscoveryResponse.getDefaultInstance()));
        }
    }

    private static final class BlockingStub implements BlockingInterface {
        private final BlockingRpcChannel channel;

        private BlockingStub(BlockingRpcChannel blockingRpcChannel) {
            this.channel = blockingRpcChannel;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ListenerDiscoveryService.BlockingInterface
        public DeltaDiscoveryResponse deltaListeners(RpcController rpcController, DeltaDiscoveryRequest deltaDiscoveryRequest) throws ServiceException {
            return this.channel.callBlockingMethod((Descriptors.MethodDescriptor) ListenerDiscoveryService.getDescriptor().getMethods().get(0), rpcController, deltaDiscoveryRequest, DeltaDiscoveryResponse.getDefaultInstance());
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ListenerDiscoveryService.BlockingInterface
        public DiscoveryResponse streamListeners(RpcController rpcController, DiscoveryRequest discoveryRequest) throws ServiceException {
            return this.channel.callBlockingMethod((Descriptors.MethodDescriptor) ListenerDiscoveryService.getDescriptor().getMethods().get(1), rpcController, discoveryRequest, DiscoveryResponse.getDefaultInstance());
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ListenerDiscoveryService.BlockingInterface
        public DiscoveryResponse fetchListeners(RpcController rpcController, DiscoveryRequest discoveryRequest) throws ServiceException {
            return this.channel.callBlockingMethod((Descriptors.MethodDescriptor) ListenerDiscoveryService.getDescriptor().getMethods().get(2), rpcController, discoveryRequest, DiscoveryResponse.getDefaultInstance());
        }
    }
}
