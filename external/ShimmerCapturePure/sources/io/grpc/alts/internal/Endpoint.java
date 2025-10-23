package io.grpc.alts.internal;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class Endpoint extends GeneratedMessageV3 implements EndpointOrBuilder {
    public static final int IP_ADDRESS_FIELD_NUMBER = 1;
    public static final int PORT_FIELD_NUMBER = 2;
    public static final int PROTOCOL_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final Endpoint DEFAULT_INSTANCE = new Endpoint();
    private static final Parser<Endpoint> PARSER = new AbstractParser<Endpoint>() { // from class: io.grpc.alts.internal.Endpoint.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Endpoint m6378parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Endpoint(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object ipAddress_;
    private byte memoizedIsInitialized;
    private int port_;
    private int protocol_;

    private Endpoint(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Endpoint() {
        this.memoizedIsInitialized = (byte) -1;
        this.ipAddress_ = "";
        this.protocol_ = 0;
    }

    private Endpoint(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            this.ipAddress_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 16) {
                            this.port_ = codedInputStream.readInt32();
                        } else if (tag == 24) {
                            this.protocol_ = codedInputStream.readEnum();
                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                        }
                    }
                    z = true;
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static Endpoint getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Endpoint> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HandshakerProto.internal_static_grpc_gcp_Endpoint_descriptor;
    }

    public static Endpoint parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(byteBuffer);
    }

    public static Endpoint parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Endpoint parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(byteString);
    }

    public static Endpoint parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Endpoint parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(bArr);
    }

    public static Endpoint parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Endpoint parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Endpoint parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Endpoint parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Endpoint parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Endpoint parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Endpoint parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m6376toBuilder();
    }

    public static Builder newBuilder(Endpoint endpoint) {
        return DEFAULT_INSTANCE.m6376toBuilder().mergeFrom(endpoint);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Endpoint m6371getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Endpoint> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.alts.internal.EndpointOrBuilder
    public int getPort() {
        return this.port_;
    }

    @Override // io.grpc.alts.internal.EndpointOrBuilder
    public int getProtocolValue() {
        return this.protocol_;
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Endpoint();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.internal_static_grpc_gcp_Endpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(Endpoint.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.EndpointOrBuilder
    public String getIpAddress() {
        Object obj = this.ipAddress_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.ipAddress_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.EndpointOrBuilder
    public ByteString getIpAddressBytes() {
        Object obj = this.ipAddress_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.ipAddress_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.alts.internal.EndpointOrBuilder
    public NetworkProtocol getProtocol() {
        NetworkProtocol networkProtocolValueOf = NetworkProtocol.valueOf(this.protocol_);
        return networkProtocolValueOf == null ? NetworkProtocol.UNRECOGNIZED : networkProtocolValueOf;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getIpAddressBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.ipAddress_);
        }
        int i = this.port_;
        if (i != 0) {
            codedOutputStream.writeInt32(2, i);
        }
        if (this.protocol_ != NetworkProtocol.NETWORK_PROTOCOL_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(3, this.protocol_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getIpAddressBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.ipAddress_) : 0;
        int i2 = this.port_;
        if (i2 != 0) {
            iComputeStringSize += CodedOutputStream.computeInt32Size(2, i2);
        }
        if (this.protocol_ != NetworkProtocol.NETWORK_PROTOCOL_UNSPECIFIED.getNumber()) {
            iComputeStringSize += CodedOutputStream.computeEnumSize(3, this.protocol_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Endpoint)) {
            return super.equals(obj);
        }
        Endpoint endpoint = (Endpoint) obj;
        return getIpAddress().equals(endpoint.getIpAddress()) && getPort() == endpoint.getPort() && this.protocol_ == endpoint.protocol_ && this.unknownFields.equals(endpoint.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getIpAddress().hashCode()) * 37) + 2) * 53) + getPort()) * 37) + 3) * 53) + this.protocol_) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m6373newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m6376toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EndpointOrBuilder {
        private Object ipAddress_;
        private int port_;
        private int protocol_;

        private Builder() {
            this.ipAddress_ = "";
            this.protocol_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.ipAddress_ = "";
            this.protocol_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_Endpoint_descriptor;
        }

        @Override // io.grpc.alts.internal.EndpointOrBuilder
        public int getPort() {
            return this.port_;
        }

        public Builder setPort(int i) {
            this.port_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.EndpointOrBuilder
        public int getProtocolValue() {
            return this.protocol_;
        }

        public Builder setProtocolValue(int i) {
            this.protocol_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.internal_static_grpc_gcp_Endpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(Endpoint.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Endpoint.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6387clear() {
            super.clear();
            this.ipAddress_ = "";
            this.port_ = 0;
            this.protocol_ = 0;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HandshakerProto.internal_static_grpc_gcp_Endpoint_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Endpoint m6400getDefaultInstanceForType() {
            return Endpoint.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Endpoint m6381build() throws UninitializedMessageException {
            Endpoint endpointM6383buildPartial = m6383buildPartial();
            if (endpointM6383buildPartial.isInitialized()) {
                return endpointM6383buildPartial;
            }
            throw newUninitializedMessageException(endpointM6383buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Endpoint m6383buildPartial() {
            Endpoint endpoint = new Endpoint(this);
            endpoint.ipAddress_ = this.ipAddress_;
            endpoint.port_ = this.port_;
            endpoint.protocol_ = this.protocol_;
            onBuilt();
            return endpoint;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6399clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6411setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6389clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6392clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6413setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6379addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6404mergeFrom(Message message) {
            if (message instanceof Endpoint) {
                return mergeFrom((Endpoint) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Endpoint endpoint) {
            if (endpoint == Endpoint.getDefaultInstance()) {
                return this;
            }
            if (!endpoint.getIpAddress().isEmpty()) {
                this.ipAddress_ = endpoint.ipAddress_;
                onChanged();
            }
            if (endpoint.getPort() != 0) {
                setPort(endpoint.getPort());
            }
            if (endpoint.protocol_ != 0) {
                setProtocolValue(endpoint.getProtocolValue());
            }
            m6409mergeUnknownFields(endpoint.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.alts.internal.Endpoint.Builder m6405mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.alts.internal.Endpoint.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.alts.internal.Endpoint r3 = (io.grpc.alts.internal.Endpoint) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                if (r3 == 0) goto L10
                r2.mergeFrom(r3)
            L10:
                return r2
            L11:
                r3 = move-exception
                goto L21
            L13:
                r3 = move-exception
                com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                io.grpc.alts.internal.Endpoint r4 = (io.grpc.alts.internal.Endpoint) r4     // Catch: java.lang.Throwable -> L11
                java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                throw r3     // Catch: java.lang.Throwable -> L1f
            L1f:
                r3 = move-exception
                r0 = r4
            L21:
                if (r0 == 0) goto L26
                r2.mergeFrom(r0)
            L26:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.alts.internal.Endpoint.Builder.m6405mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.alts.internal.Endpoint$Builder");
        }

        @Override // io.grpc.alts.internal.EndpointOrBuilder
        public String getIpAddress() {
            Object obj = this.ipAddress_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.ipAddress_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setIpAddress(String str) {
            str.getClass();
            this.ipAddress_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.EndpointOrBuilder
        public ByteString getIpAddressBytes() {
            Object obj = this.ipAddress_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.ipAddress_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setIpAddressBytes(ByteString byteString) {
            byteString.getClass();
            Endpoint.checkByteStringIsUtf8(byteString);
            this.ipAddress_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearIpAddress() {
            this.ipAddress_ = Endpoint.getDefaultInstance().getIpAddress();
            onChanged();
            return this;
        }

        public Builder clearPort() {
            this.port_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.EndpointOrBuilder
        public NetworkProtocol getProtocol() {
            NetworkProtocol networkProtocolValueOf = NetworkProtocol.valueOf(this.protocol_);
            return networkProtocolValueOf == null ? NetworkProtocol.UNRECOGNIZED : networkProtocolValueOf;
        }

        public Builder setProtocol(NetworkProtocol networkProtocol) {
            networkProtocol.getClass();
            this.protocol_ = networkProtocol.getNumber();
            onChanged();
            return this;
        }

        public Builder clearProtocol() {
            this.protocol_ = 0;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m6415setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m6409mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
