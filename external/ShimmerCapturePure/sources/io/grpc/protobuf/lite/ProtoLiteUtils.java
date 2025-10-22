package io.grpc.protobuf.lite;

import com.google.common.base.Preconditions;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import io.grpc.KnownLength;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public final class ProtoLiteUtils {
    static final int DEFAULT_MAX_MESSAGE_SIZE = 4194304;
    private static final int BUF_SIZE = 8192;
    static volatile ExtensionRegistryLite globalRegistry = ExtensionRegistryLite.getEmptyRegistry();

    private ProtoLiteUtils() {
    }

    public static void setExtensionRegistry(ExtensionRegistryLite extensionRegistryLite) {
        globalRegistry = (ExtensionRegistryLite) Preconditions.checkNotNull(extensionRegistryLite, "newRegistry");
    }

    public static <T extends MessageLite> MethodDescriptor.Marshaller<T> marshaller(T t) {
        return new MessageMarshaller(t);
    }

    public static <T extends MessageLite> Metadata.BinaryMarshaller<T> metadataMarshaller(T t) {
        return new MetadataMarshaller(t);
    }

    static long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        Preconditions.checkNotNull(inputStream, "inputStream cannot be null!");
        Preconditions.checkNotNull(outputStream, "outputStream cannot be null!");
        byte[] bArr = new byte[8192];
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return j;
            }
            outputStream.write(bArr, 0, i);
            j += i;
        }
    }

    private static final class MessageMarshaller<T extends MessageLite> implements MethodDescriptor.PrototypeMarshaller<T> {
        private static final ThreadLocal<Reference<byte[]>> bufs = new ThreadLocal<>();
        private final T defaultInstance;
        private final Parser<T> parser;

        MessageMarshaller(T t) {
            this.defaultInstance = t;
            this.parser = t.getParserForType();
        }

        @Override // io.grpc.MethodDescriptor.PrototypeMarshaller
        public T getMessagePrototype() {
            return this.defaultInstance;
        }

        @Override // io.grpc.MethodDescriptor.ReflectableMarshaller
        public Class<T> getMessageClass() {
            return (Class<T>) this.defaultInstance.getClass();
        }

        @Override // io.grpc.MethodDescriptor.Marshaller
        public InputStream stream(T t) {
            return new ProtoInputStream(t, this.parser);
        }

        @Override // io.grpc.MethodDescriptor.Marshaller
        public T parse(InputStream inputStream) throws IOException {
            CodedInputStream codedInputStreamNewInstance;
            byte[] bArr;
            if ((inputStream instanceof ProtoInputStream) && ((ProtoInputStream) inputStream).parser() == this.parser) {
                try {
                    return (T) ((ProtoInputStream) inputStream).message();
                } catch (IllegalStateException unused) {
                }
            }
            try {
                if (inputStream instanceof KnownLength) {
                    int iAvailable = inputStream.available();
                    if (iAvailable <= 0 || iAvailable > 4194304) {
                        if (iAvailable == 0) {
                            return this.defaultInstance;
                        }
                        codedInputStreamNewInstance = null;
                    } else {
                        ThreadLocal<Reference<byte[]>> threadLocal = bufs;
                        Reference<byte[]> reference = threadLocal.get();
                        if (reference == null || (bArr = reference.get()) == null || bArr.length < iAvailable) {
                            bArr = new byte[iAvailable];
                            threadLocal.set(new WeakReference(bArr));
                        }
                        int i = iAvailable;
                        while (i > 0) {
                            int i2 = inputStream.read(bArr, iAvailable - i, i);
                            if (i2 == -1) {
                                break;
                            }
                            i -= i2;
                        }
                        if (i != 0) {
                            throw new RuntimeException("size inaccurate: " + iAvailable + " != " + (iAvailable - i));
                        }
                        codedInputStreamNewInstance = CodedInputStream.newInstance(bArr, 0, iAvailable);
                    }
                } else {
                    codedInputStreamNewInstance = null;
                }
                if (codedInputStreamNewInstance == null) {
                    codedInputStreamNewInstance = CodedInputStream.newInstance(inputStream);
                }
                codedInputStreamNewInstance.setSizeLimit(Integer.MAX_VALUE);
                try {
                    return (T) parseFrom(codedInputStreamNewInstance);
                } catch (InvalidProtocolBufferException e) {
                    throw Status.INTERNAL.withDescription("Invalid protobuf byte sequence").withCause(e).asRuntimeException();
                }
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.InvalidProtocolBufferException */
        private T parseFrom(CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
            T t = (T) this.parser.parseFrom(codedInputStream, ProtoLiteUtils.globalRegistry);
            try {
                codedInputStream.checkLastTagWas(0);
                return t;
            } catch (InvalidProtocolBufferException e) {
                e.setUnfinishedMessage(t);
                throw e;
            }
        }
    }

    private static final class MetadataMarshaller<T extends MessageLite> implements Metadata.BinaryMarshaller<T> {
        private final T defaultInstance;

        MetadataMarshaller(T t) {
            this.defaultInstance = t;
        }

        @Override // io.grpc.Metadata.BinaryMarshaller
        public byte[] toBytes(T t) {
            return t.toByteArray();
        }

        @Override // io.grpc.Metadata.BinaryMarshaller
        public T parseBytes(byte[] bArr) {
            try {
                return (T) this.defaultInstance.getParserForType().parseFrom(bArr, ProtoLiteUtils.globalRegistry);
            } catch (InvalidProtocolBufferException e) {
                throw new IllegalArgumentException((Throwable) e);
            }
        }
    }
}
