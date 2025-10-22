package io.grpc.channelz.v1;

import com.google.protobuf.AbstractMessageLite;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.channelz.v1.ServerData;
import io.grpc.channelz.v1.ServerRef;
import io.grpc.channelz.v1.SocketRef;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class Server extends GeneratedMessageV3 implements ServerOrBuilder {
    public static final int DATA_FIELD_NUMBER = 2;
    public static final int LISTEN_SOCKET_FIELD_NUMBER = 3;
    public static final int REF_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final Server DEFAULT_INSTANCE = new Server();
    private static final Parser<Server> PARSER = new AbstractParser<Server>() { // from class: io.grpc.channelz.v1.Server.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Server m8552parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Server(codedInputStream, extensionRegistryLite);
        }
    };
    private ServerData data_;
    private List<SocketRef> listenSocket_;
    private byte memoizedIsInitialized;
    private ServerRef ref_;

    private Server(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Server() {
        this.memoizedIsInitialized = (byte) -1;
        this.listenSocket_ = Collections.emptyList();
    }

    private Server(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            ServerRef serverRef = this.ref_;
                            ServerRef.Builder builderM8642toBuilder = serverRef != null ? serverRef.m8642toBuilder() : null;
                            ServerRef serverRef2 = (ServerRef) codedInputStream.readMessage(ServerRef.parser(), extensionRegistryLite);
                            this.ref_ = serverRef2;
                            if (builderM8642toBuilder != null) {
                                builderM8642toBuilder.mergeFrom(serverRef2);
                                this.ref_ = builderM8642toBuilder.m8649buildPartial();
                            }
                        } else if (tag == 18) {
                            ServerData serverData = this.data_;
                            ServerData.Builder builderM8596toBuilder = serverData != null ? serverData.m8596toBuilder() : null;
                            ServerData serverData2 = (ServerData) codedInputStream.readMessage(ServerData.parser(), extensionRegistryLite);
                            this.data_ = serverData2;
                            if (builderM8596toBuilder != null) {
                                builderM8596toBuilder.mergeFrom(serverData2);
                                this.data_ = builderM8596toBuilder.m8603buildPartial();
                            }
                        } else if (tag == 26) {
                            if (!(z2 & true)) {
                                this.listenSocket_ = new ArrayList();
                                z2 |= true;
                            }
                            this.listenSocket_.add(codedInputStream.readMessage(SocketRef.parser(), extensionRegistryLite));
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
                if (z2 & true) {
                    this.listenSocket_ = Collections.unmodifiableList(this.listenSocket_);
                }
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
        return ChannelzProto.internal_static_grpc_channelz_v1_Server_descriptor;
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
        return DEFAULT_INSTANCE.m8550toBuilder();
    }

    public static Builder newBuilder(Server server) {
        return DEFAULT_INSTANCE.m8550toBuilder().mergeFrom(server);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Server m8545getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.channelz.v1.ServerOrBuilder
    public List<SocketRef> getListenSocketList() {
        return this.listenSocket_;
    }

    @Override // io.grpc.channelz.v1.ServerOrBuilder
    public List<? extends SocketRefOrBuilder> getListenSocketOrBuilderList() {
        return this.listenSocket_;
    }

    public Parser<Server> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.channelz.v1.ServerOrBuilder
    public boolean hasData() {
        return this.data_ != null;
    }

    @Override // io.grpc.channelz.v1.ServerOrBuilder
    public boolean hasRef() {
        return this.ref_ != null;
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
        return ChannelzProto.internal_static_grpc_channelz_v1_Server_fieldAccessorTable.ensureFieldAccessorsInitialized(Server.class, Builder.class);
    }

    @Override // io.grpc.channelz.v1.ServerOrBuilder
    public ServerRef getRef() {
        ServerRef serverRef = this.ref_;
        return serverRef == null ? ServerRef.getDefaultInstance() : serverRef;
    }

    @Override // io.grpc.channelz.v1.ServerOrBuilder
    public ServerRefOrBuilder getRefOrBuilder() {
        return getRef();
    }

    @Override // io.grpc.channelz.v1.ServerOrBuilder
    public ServerData getData() {
        ServerData serverData = this.data_;
        return serverData == null ? ServerData.getDefaultInstance() : serverData;
    }

    @Override // io.grpc.channelz.v1.ServerOrBuilder
    public ServerDataOrBuilder getDataOrBuilder() {
        return getData();
    }

    @Override // io.grpc.channelz.v1.ServerOrBuilder
    public int getListenSocketCount() {
        return this.listenSocket_.size();
    }

    @Override // io.grpc.channelz.v1.ServerOrBuilder
    public SocketRef getListenSocket(int i) {
        return this.listenSocket_.get(i);
    }

    @Override // io.grpc.channelz.v1.ServerOrBuilder
    public SocketRefOrBuilder getListenSocketOrBuilder(int i) {
        return this.listenSocket_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.ref_ != null) {
            codedOutputStream.writeMessage(1, getRef());
        }
        if (this.data_ != null) {
            codedOutputStream.writeMessage(2, getData());
        }
        for (int i = 0; i < this.listenSocket_.size(); i++) {
            codedOutputStream.writeMessage(3, this.listenSocket_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.ref_ != null ? CodedOutputStream.computeMessageSize(1, getRef()) : 0;
        if (this.data_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getData());
        }
        for (int i2 = 0; i2 < this.listenSocket_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, this.listenSocket_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
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
        if (hasRef() != server.hasRef()) {
            return false;
        }
        if ((!hasRef() || getRef().equals(server.getRef())) && hasData() == server.hasData()) {
            return (!hasData() || getData().equals(server.getData())) && getListenSocketList().equals(server.getListenSocketList()) && this.unknownFields.equals(server.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasRef()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getRef().hashCode();
        }
        if (hasData()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getData().hashCode();
        }
        if (getListenSocketCount() > 0) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getListenSocketList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8547newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8550toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ServerOrBuilder {
        private int bitField0_;
        private SingleFieldBuilderV3<ServerData, ServerData.Builder, ServerDataOrBuilder> dataBuilder_;
        private ServerData data_;
        private RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> listenSocketBuilder_;
        private List<SocketRef> listenSocket_;
        private SingleFieldBuilderV3<ServerRef, ServerRef.Builder, ServerRefOrBuilder> refBuilder_;
        private ServerRef ref_;

        private Builder() {
            this.listenSocket_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.listenSocket_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ChannelzProto.internal_static_grpc_channelz_v1_Server_descriptor;
        }

        @Override // io.grpc.channelz.v1.ServerOrBuilder
        public boolean hasData() {
            return (this.dataBuilder_ == null && this.data_ == null) ? false : true;
        }

        @Override // io.grpc.channelz.v1.ServerOrBuilder
        public boolean hasRef() {
            return (this.refBuilder_ == null && this.ref_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ChannelzProto.internal_static_grpc_channelz_v1_Server_fieldAccessorTable.ensureFieldAccessorsInitialized(Server.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (Server.alwaysUseFieldBuilders) {
                getListenSocketFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8561clear() {
            super.clear();
            if (this.refBuilder_ == null) {
                this.ref_ = null;
            } else {
                this.ref_ = null;
                this.refBuilder_ = null;
            }
            if (this.dataBuilder_ == null) {
                this.data_ = null;
            } else {
                this.data_ = null;
                this.dataBuilder_ = null;
            }
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.listenSocket_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ChannelzProto.internal_static_grpc_channelz_v1_Server_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Server m8574getDefaultInstanceForType() {
            return Server.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Server m8555build() throws UninitializedMessageException {
            Server serverM8557buildPartial = m8557buildPartial();
            if (serverM8557buildPartial.isInitialized()) {
                return serverM8557buildPartial;
            }
            throw newUninitializedMessageException(serverM8557buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Server m8557buildPartial() {
            Server server = new Server(this);
            SingleFieldBuilderV3<ServerRef, ServerRef.Builder, ServerRefOrBuilder> singleFieldBuilderV3 = this.refBuilder_;
            if (singleFieldBuilderV3 == null) {
                server.ref_ = this.ref_;
            } else {
                server.ref_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<ServerData, ServerData.Builder, ServerDataOrBuilder> singleFieldBuilderV32 = this.dataBuilder_;
            if (singleFieldBuilderV32 == null) {
                server.data_ = this.data_;
            } else {
                server.data_ = singleFieldBuilderV32.build();
            }
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.listenSocket_ = Collections.unmodifiableList(this.listenSocket_);
                    this.bitField0_ &= -2;
                }
                server.listenSocket_ = this.listenSocket_;
            } else {
                server.listenSocket_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return server;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8573clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8585setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8563clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8566clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8587setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8553addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8578mergeFrom(Message message) {
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
            if (server.hasRef()) {
                mergeRef(server.getRef());
            }
            if (server.hasData()) {
                mergeData(server.getData());
            }
            if (this.listenSocketBuilder_ == null) {
                if (!server.listenSocket_.isEmpty()) {
                    if (this.listenSocket_.isEmpty()) {
                        this.listenSocket_ = server.listenSocket_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureListenSocketIsMutable();
                        this.listenSocket_.addAll(server.listenSocket_);
                    }
                    onChanged();
                }
            } else if (!server.listenSocket_.isEmpty()) {
                if (!this.listenSocketBuilder_.isEmpty()) {
                    this.listenSocketBuilder_.addAllMessages(server.listenSocket_);
                } else {
                    this.listenSocketBuilder_.dispose();
                    this.listenSocketBuilder_ = null;
                    this.listenSocket_ = server.listenSocket_;
                    this.bitField0_ &= -2;
                    this.listenSocketBuilder_ = Server.alwaysUseFieldBuilders ? getListenSocketFieldBuilder() : null;
                }
            }
            m8583mergeUnknownFields(server.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.channelz.v1.Server.Builder m8579mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.channelz.v1.Server.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.channelz.v1.Server r3 = (io.grpc.channelz.v1.Server) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.channelz.v1.Server r4 = (io.grpc.channelz.v1.Server) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.channelz.v1.Server.Builder.m8579mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.channelz.v1.Server$Builder");
        }

        @Override // io.grpc.channelz.v1.ServerOrBuilder
        public ServerRef getRef() {
            SingleFieldBuilderV3<ServerRef, ServerRef.Builder, ServerRefOrBuilder> singleFieldBuilderV3 = this.refBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            ServerRef serverRef = this.ref_;
            return serverRef == null ? ServerRef.getDefaultInstance() : serverRef;
        }

        public Builder setRef(ServerRef serverRef) {
            SingleFieldBuilderV3<ServerRef, ServerRef.Builder, ServerRefOrBuilder> singleFieldBuilderV3 = this.refBuilder_;
            if (singleFieldBuilderV3 == null) {
                serverRef.getClass();
                this.ref_ = serverRef;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(serverRef);
            }
            return this;
        }

        public Builder setRef(ServerRef.Builder builder) {
            SingleFieldBuilderV3<ServerRef, ServerRef.Builder, ServerRefOrBuilder> singleFieldBuilderV3 = this.refBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.ref_ = builder.m8647build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m8647build());
            }
            return this;
        }

        public Builder mergeRef(ServerRef serverRef) {
            SingleFieldBuilderV3<ServerRef, ServerRef.Builder, ServerRefOrBuilder> singleFieldBuilderV3 = this.refBuilder_;
            if (singleFieldBuilderV3 == null) {
                ServerRef serverRef2 = this.ref_;
                if (serverRef2 != null) {
                    this.ref_ = ServerRef.newBuilder(serverRef2).mergeFrom(serverRef).m8649buildPartial();
                } else {
                    this.ref_ = serverRef;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(serverRef);
            }
            return this;
        }

        public Builder clearRef() {
            if (this.refBuilder_ == null) {
                this.ref_ = null;
                onChanged();
            } else {
                this.ref_ = null;
                this.refBuilder_ = null;
            }
            return this;
        }

        public ServerRef.Builder getRefBuilder() {
            onChanged();
            return getRefFieldBuilder().getBuilder();
        }

        @Override // io.grpc.channelz.v1.ServerOrBuilder
        public ServerRefOrBuilder getRefOrBuilder() {
            SingleFieldBuilderV3<ServerRef, ServerRef.Builder, ServerRefOrBuilder> singleFieldBuilderV3 = this.refBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ServerRefOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            ServerRef serverRef = this.ref_;
            return serverRef == null ? ServerRef.getDefaultInstance() : serverRef;
        }

        private SingleFieldBuilderV3<ServerRef, ServerRef.Builder, ServerRefOrBuilder> getRefFieldBuilder() {
            if (this.refBuilder_ == null) {
                this.refBuilder_ = new SingleFieldBuilderV3<>(getRef(), getParentForChildren(), isClean());
                this.ref_ = null;
            }
            return this.refBuilder_;
        }

        @Override // io.grpc.channelz.v1.ServerOrBuilder
        public ServerData getData() {
            SingleFieldBuilderV3<ServerData, ServerData.Builder, ServerDataOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            ServerData serverData = this.data_;
            return serverData == null ? ServerData.getDefaultInstance() : serverData;
        }

        public Builder setData(ServerData serverData) {
            SingleFieldBuilderV3<ServerData, ServerData.Builder, ServerDataOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
            if (singleFieldBuilderV3 == null) {
                serverData.getClass();
                this.data_ = serverData;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(serverData);
            }
            return this;
        }

        public Builder setData(ServerData.Builder builder) {
            SingleFieldBuilderV3<ServerData, ServerData.Builder, ServerDataOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.data_ = builder.m8601build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m8601build());
            }
            return this;
        }

        public Builder mergeData(ServerData serverData) {
            SingleFieldBuilderV3<ServerData, ServerData.Builder, ServerDataOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
            if (singleFieldBuilderV3 == null) {
                ServerData serverData2 = this.data_;
                if (serverData2 != null) {
                    this.data_ = ServerData.newBuilder(serverData2).mergeFrom(serverData).m8603buildPartial();
                } else {
                    this.data_ = serverData;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(serverData);
            }
            return this;
        }

        public Builder clearData() {
            if (this.dataBuilder_ == null) {
                this.data_ = null;
                onChanged();
            } else {
                this.data_ = null;
                this.dataBuilder_ = null;
            }
            return this;
        }

        public ServerData.Builder getDataBuilder() {
            onChanged();
            return getDataFieldBuilder().getBuilder();
        }

        @Override // io.grpc.channelz.v1.ServerOrBuilder
        public ServerDataOrBuilder getDataOrBuilder() {
            SingleFieldBuilderV3<ServerData, ServerData.Builder, ServerDataOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ServerDataOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            ServerData serverData = this.data_;
            return serverData == null ? ServerData.getDefaultInstance() : serverData;
        }

        private SingleFieldBuilderV3<ServerData, ServerData.Builder, ServerDataOrBuilder> getDataFieldBuilder() {
            if (this.dataBuilder_ == null) {
                this.dataBuilder_ = new SingleFieldBuilderV3<>(getData(), getParentForChildren(), isClean());
                this.data_ = null;
            }
            return this.dataBuilder_;
        }

        private void ensureListenSocketIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.listenSocket_ = new ArrayList(this.listenSocket_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.channelz.v1.ServerOrBuilder
        public List<SocketRef> getListenSocketList() {
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.listenSocket_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.channelz.v1.ServerOrBuilder
        public int getListenSocketCount() {
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.listenSocket_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.channelz.v1.ServerOrBuilder
        public SocketRef getListenSocket(int i) {
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.listenSocket_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setListenSocket(int i, SocketRef socketRef) {
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                socketRef.getClass();
                ensureListenSocketIsMutable();
                this.listenSocket_.set(i, socketRef);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, socketRef);
            }
            return this;
        }

        public Builder setListenSocket(int i, SocketRef.Builder builder) {
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureListenSocketIsMutable();
                this.listenSocket_.set(i, builder.m8969build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m8969build());
            }
            return this;
        }

        public Builder addListenSocket(SocketRef socketRef) {
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                socketRef.getClass();
                ensureListenSocketIsMutable();
                this.listenSocket_.add(socketRef);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(socketRef);
            }
            return this;
        }

        public Builder addListenSocket(int i, SocketRef socketRef) {
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                socketRef.getClass();
                ensureListenSocketIsMutable();
                this.listenSocket_.add(i, socketRef);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, socketRef);
            }
            return this;
        }

        public Builder addListenSocket(SocketRef.Builder builder) {
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureListenSocketIsMutable();
                this.listenSocket_.add(builder.m8969build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m8969build());
            }
            return this;
        }

        public Builder addListenSocket(int i, SocketRef.Builder builder) {
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureListenSocketIsMutable();
                this.listenSocket_.add(i, builder.m8969build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m8969build());
            }
            return this;
        }

        public Builder addAllListenSocket(Iterable<? extends SocketRef> iterable) {
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureListenSocketIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.listenSocket_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearListenSocket() {
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.listenSocket_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeListenSocket(int i) {
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureListenSocketIsMutable();
                this.listenSocket_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public SocketRef.Builder getListenSocketBuilder(int i) {
            return getListenSocketFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.channelz.v1.ServerOrBuilder
        public SocketRefOrBuilder getListenSocketOrBuilder(int i) {
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.listenSocket_.get(i);
            }
            return (SocketRefOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.channelz.v1.ServerOrBuilder
        public List<? extends SocketRefOrBuilder> getListenSocketOrBuilderList() {
            RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> repeatedFieldBuilderV3 = this.listenSocketBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.listenSocket_);
        }

        public SocketRef.Builder addListenSocketBuilder() {
            return getListenSocketFieldBuilder().addBuilder(SocketRef.getDefaultInstance());
        }

        public SocketRef.Builder addListenSocketBuilder(int i) {
            return getListenSocketFieldBuilder().addBuilder(i, SocketRef.getDefaultInstance());
        }

        public List<SocketRef.Builder> getListenSocketBuilderList() {
            return getListenSocketFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> getListenSocketFieldBuilder() {
            if (this.listenSocketBuilder_ == null) {
                this.listenSocketBuilder_ = new RepeatedFieldBuilderV3<>(this.listenSocket_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.listenSocket_ = null;
            }
            return this.listenSocketBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8589setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8583mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
