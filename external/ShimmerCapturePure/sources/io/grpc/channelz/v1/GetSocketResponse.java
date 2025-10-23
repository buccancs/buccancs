package io.grpc.channelz.v1;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.channelz.v1.Socket;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class GetSocketResponse extends GeneratedMessageV3 implements GetSocketResponseOrBuilder {
    public static final int SOCKET_FIELD_NUMBER = 1;
    private static final GetSocketResponse DEFAULT_INSTANCE = new GetSocketResponse();
    private static final Parser<GetSocketResponse> PARSER = new AbstractParser<GetSocketResponse>() { // from class: io.grpc.channelz.v1.GetSocketResponse.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public GetSocketResponse m8184parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new GetSocketResponse(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private Socket socket_;

    private GetSocketResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private GetSocketResponse() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private GetSocketResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                Socket socket = this.socket_;
                                Socket.Builder builderM8688toBuilder = socket != null ? socket.m8688toBuilder() : null;
                                Socket socket2 = (Socket) codedInputStream.readMessage(Socket.parser(), extensionRegistryLite);
                                this.socket_ = socket2;
                                if (builderM8688toBuilder != null) {
                                    builderM8688toBuilder.mergeFrom(socket2);
                                    this.socket_ = builderM8688toBuilder.m8695buildPartial();
                                }
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    }
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static GetSocketResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GetSocketResponse> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ChannelzProto.internal_static_grpc_channelz_v1_GetSocketResponse_descriptor;
    }

    public static GetSocketResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (GetSocketResponse) PARSER.parseFrom(byteBuffer);
    }

    public static GetSocketResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetSocketResponse) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static GetSocketResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (GetSocketResponse) PARSER.parseFrom(byteString);
    }

    public static GetSocketResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetSocketResponse) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static GetSocketResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (GetSocketResponse) PARSER.parseFrom(bArr);
    }

    public static GetSocketResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetSocketResponse) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static GetSocketResponse parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static GetSocketResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static GetSocketResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static GetSocketResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static GetSocketResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static GetSocketResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m8182toBuilder();
    }

    public static Builder newBuilder(GetSocketResponse getSocketResponse) {
        return DEFAULT_INSTANCE.m8182toBuilder().mergeFrom(getSocketResponse);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public GetSocketResponse m8177getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<GetSocketResponse> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.channelz.v1.GetSocketResponseOrBuilder
    public boolean hasSocket() {
        return this.socket_ != null;
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
        return new GetSocketResponse();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ChannelzProto.internal_static_grpc_channelz_v1_GetSocketResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(GetSocketResponse.class, Builder.class);
    }

    @Override // io.grpc.channelz.v1.GetSocketResponseOrBuilder
    public Socket getSocket() {
        Socket socket = this.socket_;
        return socket == null ? Socket.getDefaultInstance() : socket;
    }

    @Override // io.grpc.channelz.v1.GetSocketResponseOrBuilder
    public SocketOrBuilder getSocketOrBuilder() {
        return getSocket();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.socket_ != null) {
            codedOutputStream.writeMessage(1, getSocket());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = (this.socket_ != null ? CodedOutputStream.computeMessageSize(1, getSocket()) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeMessageSize;
        return iComputeMessageSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetSocketResponse)) {
            return super.equals(obj);
        }
        GetSocketResponse getSocketResponse = (GetSocketResponse) obj;
        if (hasSocket() != getSocketResponse.hasSocket()) {
            return false;
        }
        return (!hasSocket() || getSocket().equals(getSocketResponse.getSocket())) && this.unknownFields.equals(getSocketResponse.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasSocket()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getSocket().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8179newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8182toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GetSocketResponseOrBuilder {
        private SingleFieldBuilderV3<Socket, Socket.Builder, SocketOrBuilder> socketBuilder_;
        private Socket socket_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ChannelzProto.internal_static_grpc_channelz_v1_GetSocketResponse_descriptor;
        }

        @Override // io.grpc.channelz.v1.GetSocketResponseOrBuilder
        public boolean hasSocket() {
            return (this.socketBuilder_ == null && this.socket_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ChannelzProto.internal_static_grpc_channelz_v1_GetSocketResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(GetSocketResponse.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GetSocketResponse.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8193clear() {
            super.clear();
            if (this.socketBuilder_ == null) {
                this.socket_ = null;
            } else {
                this.socket_ = null;
                this.socketBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ChannelzProto.internal_static_grpc_channelz_v1_GetSocketResponse_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GetSocketResponse m8206getDefaultInstanceForType() {
            return GetSocketResponse.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GetSocketResponse m8187build() throws UninitializedMessageException {
            GetSocketResponse getSocketResponseM8189buildPartial = m8189buildPartial();
            if (getSocketResponseM8189buildPartial.isInitialized()) {
                return getSocketResponseM8189buildPartial;
            }
            throw newUninitializedMessageException(getSocketResponseM8189buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GetSocketResponse m8189buildPartial() {
            GetSocketResponse getSocketResponse = new GetSocketResponse(this);
            SingleFieldBuilderV3<Socket, Socket.Builder, SocketOrBuilder> singleFieldBuilderV3 = this.socketBuilder_;
            if (singleFieldBuilderV3 == null) {
                getSocketResponse.socket_ = this.socket_;
            } else {
                getSocketResponse.socket_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return getSocketResponse;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8205clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8217setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8195clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8198clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8219setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8185addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8210mergeFrom(Message message) {
            if (message instanceof GetSocketResponse) {
                return mergeFrom((GetSocketResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(GetSocketResponse getSocketResponse) {
            if (getSocketResponse == GetSocketResponse.getDefaultInstance()) {
                return this;
            }
            if (getSocketResponse.hasSocket()) {
                mergeSocket(getSocketResponse.getSocket());
            }
            m8215mergeUnknownFields(getSocketResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.channelz.v1.GetSocketResponse.Builder m8211mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.channelz.v1.GetSocketResponse.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.channelz.v1.GetSocketResponse r3 = (io.grpc.channelz.v1.GetSocketResponse) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.channelz.v1.GetSocketResponse r4 = (io.grpc.channelz.v1.GetSocketResponse) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.channelz.v1.GetSocketResponse.Builder.m8211mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.channelz.v1.GetSocketResponse$Builder");
        }

        @Override // io.grpc.channelz.v1.GetSocketResponseOrBuilder
        public Socket getSocket() {
            SingleFieldBuilderV3<Socket, Socket.Builder, SocketOrBuilder> singleFieldBuilderV3 = this.socketBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Socket socket = this.socket_;
            return socket == null ? Socket.getDefaultInstance() : socket;
        }

        public Builder setSocket(Socket socket) {
            SingleFieldBuilderV3<Socket, Socket.Builder, SocketOrBuilder> singleFieldBuilderV3 = this.socketBuilder_;
            if (singleFieldBuilderV3 == null) {
                socket.getClass();
                this.socket_ = socket;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(socket);
            }
            return this;
        }

        public Builder setSocket(Socket.Builder builder) {
            SingleFieldBuilderV3<Socket, Socket.Builder, SocketOrBuilder> singleFieldBuilderV3 = this.socketBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.socket_ = builder.m8693build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m8693build());
            }
            return this;
        }

        public Builder mergeSocket(Socket socket) {
            SingleFieldBuilderV3<Socket, Socket.Builder, SocketOrBuilder> singleFieldBuilderV3 = this.socketBuilder_;
            if (singleFieldBuilderV3 == null) {
                Socket socket2 = this.socket_;
                if (socket2 != null) {
                    this.socket_ = Socket.newBuilder(socket2).mergeFrom(socket).m8695buildPartial();
                } else {
                    this.socket_ = socket;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(socket);
            }
            return this;
        }

        public Builder clearSocket() {
            if (this.socketBuilder_ == null) {
                this.socket_ = null;
                onChanged();
            } else {
                this.socket_ = null;
                this.socketBuilder_ = null;
            }
            return this;
        }

        public Socket.Builder getSocketBuilder() {
            onChanged();
            return getSocketFieldBuilder().getBuilder();
        }

        @Override // io.grpc.channelz.v1.GetSocketResponseOrBuilder
        public SocketOrBuilder getSocketOrBuilder() {
            SingleFieldBuilderV3<Socket, Socket.Builder, SocketOrBuilder> singleFieldBuilderV3 = this.socketBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (SocketOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Socket socket = this.socket_;
            return socket == null ? Socket.getDefaultInstance() : socket;
        }

        private SingleFieldBuilderV3<Socket, Socket.Builder, SocketOrBuilder> getSocketFieldBuilder() {
            if (this.socketBuilder_ == null) {
                this.socketBuilder_ = new SingleFieldBuilderV3<>(getSocket(), getParentForChildren(), isClean());
                this.socket_ = null;
            }
            return this.socketBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8221setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8215mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
