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
import io.grpc.channelz.v1.Server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class GetServerResponse extends GeneratedMessageV3 implements GetServerResponseOrBuilder {
    public static final int SERVER_FIELD_NUMBER = 1;
    private static final GetServerResponse DEFAULT_INSTANCE = new GetServerResponse();
    private static final Parser<GetServerResponse> PARSER = new AbstractParser<GetServerResponse>() { // from class: io.grpc.channelz.v1.GetServerResponse.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public GetServerResponse m7908parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new GetServerResponse(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private Server server_;

    private GetServerResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private GetServerResponse() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private GetServerResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                Server server = this.server_;
                                Server.Builder builderM8550toBuilder = server != null ? server.m8550toBuilder() : null;
                                Server server2 = (Server) codedInputStream.readMessage(Server.parser(), extensionRegistryLite);
                                this.server_ = server2;
                                if (builderM8550toBuilder != null) {
                                    builderM8550toBuilder.mergeFrom(server2);
                                    this.server_ = builderM8550toBuilder.m8557buildPartial();
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

    public static GetServerResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GetServerResponse> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ChannelzProto.internal_static_grpc_channelz_v1_GetServerResponse_descriptor;
    }

    public static GetServerResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (GetServerResponse) PARSER.parseFrom(byteBuffer);
    }

    public static GetServerResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetServerResponse) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static GetServerResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (GetServerResponse) PARSER.parseFrom(byteString);
    }

    public static GetServerResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetServerResponse) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static GetServerResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (GetServerResponse) PARSER.parseFrom(bArr);
    }

    public static GetServerResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetServerResponse) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static GetServerResponse parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static GetServerResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static GetServerResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static GetServerResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static GetServerResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static GetServerResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m7906toBuilder();
    }

    public static Builder newBuilder(GetServerResponse getServerResponse) {
        return DEFAULT_INSTANCE.m7906toBuilder().mergeFrom(getServerResponse);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public GetServerResponse m7901getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<GetServerResponse> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.channelz.v1.GetServerResponseOrBuilder
    public boolean hasServer() {
        return this.server_ != null;
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
        return new GetServerResponse();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ChannelzProto.internal_static_grpc_channelz_v1_GetServerResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(GetServerResponse.class, Builder.class);
    }

    @Override // io.grpc.channelz.v1.GetServerResponseOrBuilder
    public Server getServer() {
        Server server = this.server_;
        return server == null ? Server.getDefaultInstance() : server;
    }

    @Override // io.grpc.channelz.v1.GetServerResponseOrBuilder
    public ServerOrBuilder getServerOrBuilder() {
        return getServer();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.server_ != null) {
            codedOutputStream.writeMessage(1, getServer());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = (this.server_ != null ? CodedOutputStream.computeMessageSize(1, getServer()) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeMessageSize;
        return iComputeMessageSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetServerResponse)) {
            return super.equals(obj);
        }
        GetServerResponse getServerResponse = (GetServerResponse) obj;
        if (hasServer() != getServerResponse.hasServer()) {
            return false;
        }
        return (!hasServer() || getServer().equals(getServerResponse.getServer())) && this.unknownFields.equals(getServerResponse.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasServer()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getServer().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m7903newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m7906toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GetServerResponseOrBuilder {
        private SingleFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> serverBuilder_;
        private Server server_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ChannelzProto.internal_static_grpc_channelz_v1_GetServerResponse_descriptor;
        }

        @Override // io.grpc.channelz.v1.GetServerResponseOrBuilder
        public boolean hasServer() {
            return (this.serverBuilder_ == null && this.server_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ChannelzProto.internal_static_grpc_channelz_v1_GetServerResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(GetServerResponse.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GetServerResponse.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7917clear() {
            super.clear();
            if (this.serverBuilder_ == null) {
                this.server_ = null;
            } else {
                this.server_ = null;
                this.serverBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ChannelzProto.internal_static_grpc_channelz_v1_GetServerResponse_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GetServerResponse m7930getDefaultInstanceForType() {
            return GetServerResponse.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GetServerResponse m7911build() throws UninitializedMessageException {
            GetServerResponse getServerResponseM7913buildPartial = m7913buildPartial();
            if (getServerResponseM7913buildPartial.isInitialized()) {
                return getServerResponseM7913buildPartial;
            }
            throw newUninitializedMessageException(getServerResponseM7913buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GetServerResponse m7913buildPartial() {
            GetServerResponse getServerResponse = new GetServerResponse(this);
            SingleFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> singleFieldBuilderV3 = this.serverBuilder_;
            if (singleFieldBuilderV3 == null) {
                getServerResponse.server_ = this.server_;
            } else {
                getServerResponse.server_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return getServerResponse;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7929clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7941setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7919clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7922clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7943setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7909addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7934mergeFrom(Message message) {
            if (message instanceof GetServerResponse) {
                return mergeFrom((GetServerResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(GetServerResponse getServerResponse) {
            if (getServerResponse == GetServerResponse.getDefaultInstance()) {
                return this;
            }
            if (getServerResponse.hasServer()) {
                mergeServer(getServerResponse.getServer());
            }
            m7939mergeUnknownFields(getServerResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.channelz.v1.GetServerResponse.Builder m7935mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.channelz.v1.GetServerResponse.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.channelz.v1.GetServerResponse r3 = (io.grpc.channelz.v1.GetServerResponse) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.channelz.v1.GetServerResponse r4 = (io.grpc.channelz.v1.GetServerResponse) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.channelz.v1.GetServerResponse.Builder.m7935mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.channelz.v1.GetServerResponse$Builder");
        }

        @Override // io.grpc.channelz.v1.GetServerResponseOrBuilder
        public Server getServer() {
            SingleFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> singleFieldBuilderV3 = this.serverBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Server server = this.server_;
            return server == null ? Server.getDefaultInstance() : server;
        }

        public Builder setServer(Server server) {
            SingleFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> singleFieldBuilderV3 = this.serverBuilder_;
            if (singleFieldBuilderV3 == null) {
                server.getClass();
                this.server_ = server;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(server);
            }
            return this;
        }

        public Builder setServer(Server.Builder builder) {
            SingleFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> singleFieldBuilderV3 = this.serverBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.server_ = builder.m8555build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m8555build());
            }
            return this;
        }

        public Builder mergeServer(Server server) {
            SingleFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> singleFieldBuilderV3 = this.serverBuilder_;
            if (singleFieldBuilderV3 == null) {
                Server server2 = this.server_;
                if (server2 != null) {
                    this.server_ = Server.newBuilder(server2).mergeFrom(server).m8557buildPartial();
                } else {
                    this.server_ = server;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(server);
            }
            return this;
        }

        public Builder clearServer() {
            if (this.serverBuilder_ == null) {
                this.server_ = null;
                onChanged();
            } else {
                this.server_ = null;
                this.serverBuilder_ = null;
            }
            return this;
        }

        public Server.Builder getServerBuilder() {
            onChanged();
            return getServerFieldBuilder().getBuilder();
        }

        @Override // io.grpc.channelz.v1.GetServerResponseOrBuilder
        public ServerOrBuilder getServerOrBuilder() {
            SingleFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> singleFieldBuilderV3 = this.serverBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ServerOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Server server = this.server_;
            return server == null ? Server.getDefaultInstance() : server;
        }

        private SingleFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> getServerFieldBuilder() {
            if (this.serverBuilder_ == null) {
                this.serverBuilder_ = new SingleFieldBuilderV3<>(getServer(), getParentForChildren(), isClean());
                this.server_ = null;
            }
            return this.serverBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m7945setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m7939mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
