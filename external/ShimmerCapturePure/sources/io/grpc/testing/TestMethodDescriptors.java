package io.grpc.testing;

import io.grpc.MethodDescriptor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* loaded from: classes3.dex */
public final class TestMethodDescriptors {
    private TestMethodDescriptors() {
    }

    public static MethodDescriptor<Void, Void> voidMethod() {
        return MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName("service_foo", "method_bar")).setRequestMarshaller(voidMarshaller()).setResponseMarshaller(voidMarshaller()).build();
    }

    public static MethodDescriptor.Marshaller<Void> voidMarshaller() {
        return new NoopMarshaller();
    }

    private static final class NoopMarshaller implements MethodDescriptor.Marshaller<Void> {
        private NoopMarshaller() {
        }

        @Override // io.grpc.MethodDescriptor.Marshaller
        public Void parse(InputStream inputStream) {
            return null;
        }

        @Override // io.grpc.MethodDescriptor.Marshaller
        public InputStream stream(Void r2) {
            return new ByteArrayInputStream(new byte[0]);
        }
    }
}
