package io.grpc.xds.shaded.com.google.security.meshca.v1;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Descriptors;
import io.grpc.BindableService;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServiceDescriptor;
import io.grpc.protobuf.ProtoFileDescriptorSupplier;
import io.grpc.protobuf.ProtoMethodDescriptorSupplier;
import io.grpc.protobuf.ProtoServiceDescriptorSupplier;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.AbstractAsyncStub;
import io.grpc.stub.AbstractBlockingStub;
import io.grpc.stub.AbstractFutureStub;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;

/* loaded from: classes3.dex */
public final class MeshCertificateServiceGrpc {
    public static final String SERVICE_NAME = "google.security.meshca.v1.MeshCertificateService";
    private static final int METHODID_CREATE_CERTIFICATE = 0;
    private static volatile MethodDescriptor<MeshCertificateRequest, MeshCertificateResponse> getCreateCertificateMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private MeshCertificateServiceGrpc() {
    }

    public static MethodDescriptor<MeshCertificateRequest, MeshCertificateResponse> getCreateCertificateMethod() {
        MethodDescriptor<MeshCertificateRequest, MeshCertificateResponse> methodDescriptorBuild = getCreateCertificateMethod;
        if (methodDescriptorBuild == null) {
            synchronized (MeshCertificateServiceGrpc.class) {
                methodDescriptorBuild = getCreateCertificateMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateCertificate")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(MeshCertificateRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(MeshCertificateResponse.getDefaultInstance())).setSchemaDescriptor(new MeshCertificateServiceMethodDescriptorSupplier("CreateCertificate")).build();
                    getCreateCertificateMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MeshCertificateServiceStub newStub(Channel channel) {
        return (MeshCertificateServiceStub) MeshCertificateServiceStub.newStub(new AbstractStub.StubFactory<MeshCertificateServiceStub>() { // from class: io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateServiceGrpc.1
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public MeshCertificateServiceStub newStub(Channel channel2, CallOptions callOptions) {
                return new MeshCertificateServiceStub(channel2, callOptions);
            }
        }, channel);
    }

    public static MeshCertificateServiceBlockingStub newBlockingStub(Channel channel) {
        return (MeshCertificateServiceBlockingStub) MeshCertificateServiceBlockingStub.newStub(new AbstractStub.StubFactory<MeshCertificateServiceBlockingStub>() { // from class: io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateServiceGrpc.2
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public MeshCertificateServiceBlockingStub newStub(Channel channel2, CallOptions callOptions) {
                return new MeshCertificateServiceBlockingStub(channel2, callOptions);
            }
        }, channel);
    }

    public static MeshCertificateServiceFutureStub newFutureStub(Channel channel) {
        return (MeshCertificateServiceFutureStub) MeshCertificateServiceFutureStub.newStub(new AbstractStub.StubFactory<MeshCertificateServiceFutureStub>() { // from class: io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateServiceGrpc.3
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public MeshCertificateServiceFutureStub newStub(Channel channel2, CallOptions callOptions) {
                return new MeshCertificateServiceFutureStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptorBuild = serviceDescriptor;
        if (serviceDescriptorBuild == null) {
            synchronized (MeshCertificateServiceGrpc.class) {
                serviceDescriptorBuild = serviceDescriptor;
                if (serviceDescriptorBuild == null) {
                    serviceDescriptorBuild = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new MeshCertificateServiceFileDescriptorSupplier()).addMethod(getCreateCertificateMethod()).build();
                    serviceDescriptor = serviceDescriptorBuild;
                }
            }
        }
        return serviceDescriptorBuild;
    }

    public static abstract class MeshCertificateServiceImplBase implements BindableService {
        public void createCertificate(MeshCertificateRequest meshCertificateRequest, StreamObserver<MeshCertificateResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(MeshCertificateServiceGrpc.getCreateCertificateMethod(), streamObserver);
        }

        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(MeshCertificateServiceGrpc.getServiceDescriptor()).addMethod(MeshCertificateServiceGrpc.getCreateCertificateMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class MeshCertificateServiceStub extends AbstractAsyncStub<MeshCertificateServiceStub> {
        private MeshCertificateServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public MeshCertificateServiceStub build(Channel channel, CallOptions callOptions) {
            return new MeshCertificateServiceStub(channel, callOptions);
        }

        public void createCertificate(MeshCertificateRequest meshCertificateRequest, StreamObserver<MeshCertificateResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(MeshCertificateServiceGrpc.getCreateCertificateMethod(), getCallOptions()), meshCertificateRequest, streamObserver);
        }
    }

    public static final class MeshCertificateServiceBlockingStub extends AbstractBlockingStub<MeshCertificateServiceBlockingStub> {
        private MeshCertificateServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public MeshCertificateServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new MeshCertificateServiceBlockingStub(channel, callOptions);
        }

        public MeshCertificateResponse createCertificate(MeshCertificateRequest meshCertificateRequest) {
            return (MeshCertificateResponse) ClientCalls.blockingUnaryCall(getChannel(), MeshCertificateServiceGrpc.getCreateCertificateMethod(), getCallOptions(), meshCertificateRequest);
        }
    }

    public static final class MeshCertificateServiceFutureStub extends AbstractFutureStub<MeshCertificateServiceFutureStub> {
        private MeshCertificateServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public MeshCertificateServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new MeshCertificateServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<MeshCertificateResponse> createCertificate(MeshCertificateRequest meshCertificateRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(MeshCertificateServiceGrpc.getCreateCertificateMethod(), getCallOptions()), meshCertificateRequest);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final MeshCertificateServiceImplBase serviceImpl;

        MethodHandlers(MeshCertificateServiceImplBase meshCertificateServiceImplBase, int i) {
            this.serviceImpl = meshCertificateServiceImplBase;
            this.methodId = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override
        // io.grpc.stub.ServerCalls.UnaryMethod, io.grpc.stub.ServerCalls.UnaryRequestMethod, io.grpc.stub.ServerCalls.ServerStreamingMethod
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.createCertificate((MeshCertificateRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        @Override
        // io.grpc.stub.ServerCalls.ClientStreamingMethod, io.grpc.stub.ServerCalls.StreamingRequestMethod, io.grpc.stub.ServerCalls.BidiStreamingMethod
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            throw new AssertionError();
        }
    }

    private static abstract class MeshCertificateServiceBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        MeshCertificateServiceBaseDescriptorSupplier() {
        }

        @Override // io.grpc.protobuf.ProtoFileDescriptorSupplier
        public Descriptors.FileDescriptor getFileDescriptor() {
            return MeshCaProto.getDescriptor();
        }

        @Override // io.grpc.protobuf.ProtoServiceDescriptorSupplier
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("MeshCertificateService");
        }
    }

    private static final class MeshCertificateServiceFileDescriptorSupplier extends MeshCertificateServiceBaseDescriptorSupplier {
        MeshCertificateServiceFileDescriptorSupplier() {
        }
    }

    private static final class MeshCertificateServiceMethodDescriptorSupplier extends MeshCertificateServiceBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        MeshCertificateServiceMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override // io.grpc.protobuf.ProtoMethodDescriptorSupplier
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }
}
