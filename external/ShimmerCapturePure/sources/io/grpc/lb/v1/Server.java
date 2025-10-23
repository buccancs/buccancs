package io.grpc.lb.v1;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class Server extends GeneratedMessageV3 implements ServerOrBuilder {
    public static final int DROP_FIELD_NUMBER = 4;
    public static final int IP_ADDRESS_FIELD_NUMBER = 1;
    public static final int LOAD_BALANCE_TOKEN_FIELD_NUMBER = 3;
    public static final int PORT_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final Server DEFAULT_INSTANCE = new Server();
    private static final Parser<Server> PARSER = new AbstractParser<Server>() { // from class: io.grpc.lb.v1.Server.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Server m9519parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Server(codedInputStream, extensionRegistryLite);
        }
    };
    private boolean drop_;
    private ByteString ipAddress_;
    private volatile Object loadBalanceToken_;
    private byte memoizedIsInitialized;
    private int port_;

    private Server(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Server() {
        this.memoizedIsInitialized = (byte) -1;
        this.ipAddress_ = ByteString.EMPTY;
        this.loadBalanceToken_ = "";
    }

    private Server(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.ipAddress_ = codedInputStream.readBytes();
                        } else if (tag == 16) {
                            this.port_ = codedInputStream.readInt32();
                        } else if (tag == 26) {
                            this.loadBalanceToken_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 32) {
                            this.drop_ = codedInputStream.readBool();
                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static Server getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Server> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_Server_descriptor;
    }

    public static Server parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Server) PARSER.parseFrom(byteBuffer);
    }

    public static Server parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Server) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Server parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Server) PARSER.parseFrom(byteString);
    }

    public static Server parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Server) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Server parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Server) PARSER.parseFrom(bArr);
    }

    public static Server parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Server) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Server parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Server parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Server parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Server parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Server parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Server parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m9517toBuilder();
    }

    public static Builder newBuilder(Server server) {
        return DEFAULT_INSTANCE.m9517toBuilder().mergeFrom(server);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Server m9512getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.lb.v1.ServerOrBuilder
    public boolean getDrop() {
        return this.drop_;
    }

    @Override // io.grpc.lb.v1.ServerOrBuilder
    public ByteString getIpAddress() {
        return this.ipAddress_;
    }

    public Parser<Server> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.lb.v1.ServerOrBuilder
    public int getPort() {
        return this.port_;
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
        return new Server();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_Server_fieldAccessorTable.ensureFieldAccessorsInitialized(Server.class, Builder.class);
    }

    @Override // io.grpc.lb.v1.ServerOrBuilder
    public String getLoadBalanceToken() {
        Object obj = this.loadBalanceToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.loadBalanceToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.lb.v1.ServerOrBuilder
    public ByteString getLoadBalanceTokenBytes() {
        Object obj = this.loadBalanceToken_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.loadBalanceToken_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.ipAddress_.isEmpty()) {
            codedOutputStream.writeBytes(1, this.ipAddress_);
        }
        int i = this.port_;
        if (i != 0) {
            codedOutputStream.writeInt32(2, i);
        }
        if (!getLoadBalanceTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.loadBalanceToken_);
        }
        boolean z = this.drop_;
        if (z) {
            codedOutputStream.writeBool(4, z);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeBytesSize = !this.ipAddress_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.ipAddress_) : 0;
        int i2 = this.port_;
        if (i2 != 0) {
            iComputeBytesSize += CodedOutputStream.computeInt32Size(2, i2);
        }
        if (!getLoadBalanceTokenBytes().isEmpty()) {
            iComputeBytesSize += GeneratedMessageV3.computeStringSize(3, this.loadBalanceToken_);
        }
        boolean z = this.drop_;
        if (z) {
            iComputeBytesSize += CodedOutputStream.computeBoolSize(4, z);
        }
        int serializedSize = iComputeBytesSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Server)) {
            return super.equals(obj);
        }
        Server server = (Server) obj;
        return getIpAddress().equals(server.getIpAddress()) && getPort() == server.getPort() && getLoadBalanceToken().equals(server.getLoadBalanceToken()) && getDrop() == server.getDrop() && this.unknownFields.equals(server.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getIpAddress().hashCode()) * 37) + 2) * 53) + getPort()) * 37) + 3) * 53) + getLoadBalanceToken().hashCode()) * 37) + 4) * 53) + Internal.hashBoolean(getDrop())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9514newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9517toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ServerOrBuilder {
        private boolean drop_;
        private ByteString ipAddress_;
        private Object loadBalanceToken_;
        private int port_;

        private Builder() {
            this.ipAddress_ = ByteString.EMPTY;
            this.loadBalanceToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.ipAddress_ = ByteString.EMPTY;
            this.loadBalanceToken_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_Server_descriptor;
        }

        @Override // io.grpc.lb.v1.ServerOrBuilder
        public boolean getDrop() {
            return this.drop_;
        }

        public Builder setDrop(boolean z) {
            this.drop_ = z;
            onChanged();
            return this;
        }

        @Override // io.grpc.lb.v1.ServerOrBuilder
        public ByteString getIpAddress() {
            return this.ipAddress_;
        }

        public Builder setIpAddress(ByteString byteString) {
            byteString.getClass();
            this.ipAddress_ = byteString;
            onChanged();
            return this;
        }

        @Override // io.grpc.lb.v1.ServerOrBuilder
        public int getPort() {
            return this.port_;
        }

        public Builder setPort(int i) {
            this.port_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_Server_fieldAccessorTable.ensureFieldAccessorsInitialized(Server.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Server.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9528clear() {
            super.clear();
            this.ipAddress_ = ByteString.EMPTY;
            this.port_ = 0;
            this.loadBalanceToken_ = "";
            this.drop_ = false;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_Server_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Server m9541getDefaultInstanceForType() {
            return Server.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Server m9522build() throws UninitializedMessageException {
            Server serverM9524buildPartial = m9524buildPartial();
            if (serverM9524buildPartial.isInitialized()) {
                return serverM9524buildPartial;
            }
            throw newUninitializedMessageException(serverM9524buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Server m9524buildPartial() {
            Server server = new Server(this);
            server.ipAddress_ = this.ipAddress_;
            server.port_ = this.port_;
            server.loadBalanceToken_ = this.loadBalanceToken_;
            server.drop_ = this.drop_;
            onBuilt();
            return server;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9540clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9552setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9530clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9533clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9554setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9520addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9545mergeFrom(Message message) {
            if (message instanceof Server) {
                return mergeFrom((Server) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Server server) {
            if (server == Server.getDefaultInstance()) {
                return this;
            }
            if (server.getIpAddress() != ByteString.EMPTY) {
                setIpAddress(server.getIpAddress());
            }
            if (server.getPort() != 0) {
                setPort(server.getPort());
            }
            if (!server.getLoadBalanceToken().isEmpty()) {
                this.loadBalanceToken_ = server.loadBalanceToken_;
                onChanged();
            }
            if (server.getDrop()) {
                setDrop(server.getDrop());
            }
            m9550mergeUnknownFields(server.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.lb.v1.Server.Builder m9546mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.lb.v1.Server.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.lb.v1.Server r3 = (io.grpc.lb.v1.Server) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.lb.v1.Server r4 = (io.grpc.lb.v1.Server) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.lb.v1.Server.Builder.m9546mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.lb.v1.Server$Builder");
        }

        public Builder clearIpAddress() {
            this.ipAddress_ = Server.getDefaultInstance().getIpAddress();
            onChanged();
            return this;
        }

        public Builder clearPort() {
            this.port_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.lb.v1.ServerOrBuilder
        public String getLoadBalanceToken() {
            Object obj = this.loadBalanceToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.loadBalanceToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setLoadBalanceToken(String str) {
            str.getClass();
            this.loadBalanceToken_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.lb.v1.ServerOrBuilder
        public ByteString getLoadBalanceTokenBytes() {
            Object obj = this.loadBalanceToken_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.loadBalanceToken_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setLoadBalanceTokenBytes(ByteString byteString) {
            byteString.getClass();
            Server.checkByteStringIsUtf8(byteString);
            this.loadBalanceToken_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearLoadBalanceToken() {
            this.loadBalanceToken_ = Server.getDefaultInstance().getLoadBalanceToken();
            onChanged();
            return this;
        }

        public Builder clearDrop() {
            this.drop_ = false;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9556setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9550mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
