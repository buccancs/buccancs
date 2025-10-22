package io.grpc.lb.v1;

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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.lb.v1.Server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class ServerList extends GeneratedMessageV3 implements ServerListOrBuilder {
    public static final int SERVERS_FIELD_NUMBER = 1;
    private static final ServerList DEFAULT_INSTANCE = new ServerList();
    private static final Parser<ServerList> PARSER = new AbstractParser<ServerList>() { // from class: io.grpc.lb.v1.ServerList.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ServerList m9565parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ServerList(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<Server> servers_;

    private ServerList(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ServerList() {
        this.memoizedIsInitialized = (byte) -1;
        this.servers_ = Collections.emptyList();
    }

    private ServerList(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (!(z2 & true)) {
                                this.servers_ = new ArrayList();
                                z2 |= true;
                            }
                            this.servers_.add(codedInputStream.readMessage(Server.parser(), extensionRegistryLite));
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
                    this.servers_ = Collections.unmodifiableList(this.servers_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ServerList getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ServerList> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_ServerList_descriptor;
    }

    public static ServerList parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ServerList) PARSER.parseFrom(byteBuffer);
    }

    public static ServerList parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ServerList) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ServerList parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ServerList) PARSER.parseFrom(byteString);
    }

    public static ServerList parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ServerList) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ServerList parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ServerList) PARSER.parseFrom(bArr);
    }

    public static ServerList parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ServerList) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ServerList parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ServerList parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ServerList parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ServerList parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ServerList parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ServerList parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m9563toBuilder();
    }

    public static Builder newBuilder(ServerList serverList) {
        return DEFAULT_INSTANCE.m9563toBuilder().mergeFrom(serverList);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ServerList m9558getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ServerList> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.lb.v1.ServerListOrBuilder
    public List<Server> getServersList() {
        return this.servers_;
    }

    @Override // io.grpc.lb.v1.ServerListOrBuilder
    public List<? extends ServerOrBuilder> getServersOrBuilderList() {
        return this.servers_;
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
        return new ServerList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_ServerList_fieldAccessorTable.ensureFieldAccessorsInitialized(ServerList.class, Builder.class);
    }

    @Override // io.grpc.lb.v1.ServerListOrBuilder
    public int getServersCount() {
        return this.servers_.size();
    }

    @Override // io.grpc.lb.v1.ServerListOrBuilder
    public Server getServers(int i) {
        return this.servers_.get(i);
    }

    @Override // io.grpc.lb.v1.ServerListOrBuilder
    public ServerOrBuilder getServersOrBuilder(int i) {
        return this.servers_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.servers_.size(); i++) {
            codedOutputStream.writeMessage(1, this.servers_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.servers_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.servers_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ServerList)) {
            return super.equals(obj);
        }
        ServerList serverList = (ServerList) obj;
        return getServersList().equals(serverList.getServersList()) && this.unknownFields.equals(serverList.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getServersCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getServersList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9560newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9563toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ServerListOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> serversBuilder_;
        private List<Server> servers_;

        private Builder() {
            this.servers_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.servers_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_ServerList_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_ServerList_fieldAccessorTable.ensureFieldAccessorsInitialized(ServerList.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (ServerList.alwaysUseFieldBuilders) {
                getServersFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9574clear() {
            super.clear();
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.servers_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_ServerList_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ServerList m9587getDefaultInstanceForType() {
            return ServerList.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ServerList m9568build() throws UninitializedMessageException {
            ServerList serverListM9570buildPartial = m9570buildPartial();
            if (serverListM9570buildPartial.isInitialized()) {
                return serverListM9570buildPartial;
            }
            throw newUninitializedMessageException(serverListM9570buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ServerList m9570buildPartial() {
            ServerList serverList = new ServerList(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.servers_ = Collections.unmodifiableList(this.servers_);
                    this.bitField0_ &= -2;
                }
                serverList.servers_ = this.servers_;
            } else {
                serverList.servers_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return serverList;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9586clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9598setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9576clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9579clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9600setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9566addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9591mergeFrom(Message message) {
            if (message instanceof ServerList) {
                return mergeFrom((ServerList) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ServerList serverList) {
            if (serverList == ServerList.getDefaultInstance()) {
                return this;
            }
            if (this.serversBuilder_ == null) {
                if (!serverList.servers_.isEmpty()) {
                    if (this.servers_.isEmpty()) {
                        this.servers_ = serverList.servers_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureServersIsMutable();
                        this.servers_.addAll(serverList.servers_);
                    }
                    onChanged();
                }
            } else if (!serverList.servers_.isEmpty()) {
                if (!this.serversBuilder_.isEmpty()) {
                    this.serversBuilder_.addAllMessages(serverList.servers_);
                } else {
                    this.serversBuilder_.dispose();
                    this.serversBuilder_ = null;
                    this.servers_ = serverList.servers_;
                    this.bitField0_ &= -2;
                    this.serversBuilder_ = ServerList.alwaysUseFieldBuilders ? getServersFieldBuilder() : null;
                }
            }
            m9596mergeUnknownFields(serverList.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.lb.v1.ServerList.Builder m9592mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.lb.v1.ServerList.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.lb.v1.ServerList r3 = (io.grpc.lb.v1.ServerList) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.lb.v1.ServerList r4 = (io.grpc.lb.v1.ServerList) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.lb.v1.ServerList.Builder.m9592mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.lb.v1.ServerList$Builder");
        }

        private void ensureServersIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.servers_ = new ArrayList(this.servers_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.lb.v1.ServerListOrBuilder
        public List<Server> getServersList() {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.servers_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.lb.v1.ServerListOrBuilder
        public int getServersCount() {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.servers_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.lb.v1.ServerListOrBuilder
        public Server getServers(int i) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.servers_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setServers(int i, Server server) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                server.getClass();
                ensureServersIsMutable();
                this.servers_.set(i, server);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, server);
            }
            return this;
        }

        public Builder setServers(int i, Server.Builder builder) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServersIsMutable();
                this.servers_.set(i, builder.m9522build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m9522build());
            }
            return this;
        }

        public Builder addServers(Server server) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                server.getClass();
                ensureServersIsMutable();
                this.servers_.add(server);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(server);
            }
            return this;
        }

        public Builder addServers(int i, Server server) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                server.getClass();
                ensureServersIsMutable();
                this.servers_.add(i, server);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, server);
            }
            return this;
        }

        public Builder addServers(Server.Builder builder) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServersIsMutable();
                this.servers_.add(builder.m9522build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m9522build());
            }
            return this;
        }

        public Builder addServers(int i, Server.Builder builder) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServersIsMutable();
                this.servers_.add(i, builder.m9522build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m9522build());
            }
            return this;
        }

        public Builder addAllServers(Iterable<? extends Server> iterable) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServersIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.servers_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearServers() {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.servers_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeServers(int i) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServersIsMutable();
                this.servers_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Server.Builder getServersBuilder(int i) {
            return getServersFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.lb.v1.ServerListOrBuilder
        public ServerOrBuilder getServersOrBuilder(int i) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.servers_.get(i);
            }
            return (ServerOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.lb.v1.ServerListOrBuilder
        public List<? extends ServerOrBuilder> getServersOrBuilderList() {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.servers_);
        }

        public Server.Builder addServersBuilder() {
            return getServersFieldBuilder().addBuilder(Server.getDefaultInstance());
        }

        public Server.Builder addServersBuilder(int i) {
            return getServersFieldBuilder().addBuilder(i, Server.getDefaultInstance());
        }

        public List<Server.Builder> getServersBuilderList() {
            return getServersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> getServersFieldBuilder() {
            if (this.serversBuilder_ == null) {
                this.serversBuilder_ = new RepeatedFieldBuilderV3<>(this.servers_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.servers_ = null;
            }
            return this.serversBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9602setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9596mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
