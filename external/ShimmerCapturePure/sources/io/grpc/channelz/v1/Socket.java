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
import io.grpc.channelz.v1.Address;
import io.grpc.channelz.v1.Security;
import io.grpc.channelz.v1.SocketData;
import io.grpc.channelz.v1.SocketRef;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class Socket extends GeneratedMessageV3 implements SocketOrBuilder {
    public static final int DATA_FIELD_NUMBER = 2;
    public static final int LOCAL_FIELD_NUMBER = 3;
    public static final int REF_FIELD_NUMBER = 1;
    public static final int REMOTE_FIELD_NUMBER = 4;
    public static final int REMOTE_NAME_FIELD_NUMBER = 6;
    public static final int SECURITY_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private static final Socket DEFAULT_INSTANCE = new Socket();
    private static final Parser<Socket> PARSER = new AbstractParser<Socket>() { // from class: io.grpc.channelz.v1.Socket.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Socket m8690parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Socket(codedInputStream, extensionRegistryLite);
        }
    };
    private SocketData data_;
    private Address local_;
    private byte memoizedIsInitialized;
    private SocketRef ref_;
    private volatile Object remoteName_;
    private Address remote_;
    private Security security_;

    private Socket(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Socket() {
        this.memoizedIsInitialized = (byte) -1;
        this.remoteName_ = "";
    }

    private Socket(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                SocketRef socketRef = this.ref_;
                                SocketRef.Builder builderM8964toBuilder = socketRef != null ? socketRef.m8964toBuilder() : null;
                                SocketRef socketRef2 = (SocketRef) codedInputStream.readMessage(SocketRef.parser(), extensionRegistryLite);
                                this.ref_ = socketRef2;
                                if (builderM8964toBuilder != null) {
                                    builderM8964toBuilder.mergeFrom(socketRef2);
                                    this.ref_ = builderM8964toBuilder.m8971buildPartial();
                                }
                            } else if (tag == 18) {
                                SocketData socketData = this.data_;
                                SocketData.Builder builderM8734toBuilder = socketData != null ? socketData.m8734toBuilder() : null;
                                SocketData socketData2 = (SocketData) codedInputStream.readMessage(SocketData.parser(), extensionRegistryLite);
                                this.data_ = socketData2;
                                if (builderM8734toBuilder != null) {
                                    builderM8734toBuilder.mergeFrom(socketData2);
                                    this.data_ = builderM8734toBuilder.m8741buildPartial();
                                }
                            } else if (tag == 26) {
                                Address address = this.local_;
                                Address.Builder builderM7306toBuilder = address != null ? address.m7306toBuilder() : null;
                                Address address2 = (Address) codedInputStream.readMessage(Address.parser(), extensionRegistryLite);
                                this.local_ = address2;
                                if (builderM7306toBuilder != null) {
                                    builderM7306toBuilder.mergeFrom(address2);
                                    this.local_ = builderM7306toBuilder.m7313buildPartial();
                                }
                            } else if (tag == 34) {
                                Address address3 = this.remote_;
                                Address.Builder builderM7306toBuilder2 = address3 != null ? address3.m7306toBuilder() : null;
                                Address address4 = (Address) codedInputStream.readMessage(Address.parser(), extensionRegistryLite);
                                this.remote_ = address4;
                                if (builderM7306toBuilder2 != null) {
                                    builderM7306toBuilder2.mergeFrom(address4);
                                    this.remote_ = builderM7306toBuilder2.m7313buildPartial();
                                }
                            } else if (tag == 42) {
                                Security security = this.security_;
                                Security.Builder builderM8412toBuilder = security != null ? security.m8412toBuilder() : null;
                                Security security2 = (Security) codedInputStream.readMessage(Security.parser(), extensionRegistryLite);
                                this.security_ = security2;
                                if (builderM8412toBuilder != null) {
                                    builderM8412toBuilder.mergeFrom(security2);
                                    this.security_ = builderM8412toBuilder.m8419buildPartial();
                                }
                            } else if (tag == 50) {
                                this.remoteName_ = codedInputStream.readStringRequireUtf8();
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

    public static Socket getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Socket> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ChannelzProto.internal_static_grpc_channelz_v1_Socket_descriptor;
    }

    public static Socket parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Socket) PARSER.parseFrom(byteBuffer);
    }

    public static Socket parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Socket) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Socket parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Socket) PARSER.parseFrom(byteString);
    }

    public static Socket parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Socket) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Socket parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Socket) PARSER.parseFrom(bArr);
    }

    public static Socket parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Socket) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Socket parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Socket parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Socket parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Socket parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Socket parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Socket parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m8688toBuilder();
    }

    public static Builder newBuilder(Socket socket) {
        return DEFAULT_INSTANCE.m8688toBuilder().mergeFrom(socket);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Socket m8683getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Socket> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public boolean hasData() {
        return this.data_ != null;
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public boolean hasLocal() {
        return this.local_ != null;
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public boolean hasRef() {
        return this.ref_ != null;
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public boolean hasRemote() {
        return this.remote_ != null;
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public boolean hasSecurity() {
        return this.security_ != null;
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
        return new Socket();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ChannelzProto.internal_static_grpc_channelz_v1_Socket_fieldAccessorTable.ensureFieldAccessorsInitialized(Socket.class, Builder.class);
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public SocketRef getRef() {
        SocketRef socketRef = this.ref_;
        return socketRef == null ? SocketRef.getDefaultInstance() : socketRef;
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public SocketRefOrBuilder getRefOrBuilder() {
        return getRef();
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public SocketData getData() {
        SocketData socketData = this.data_;
        return socketData == null ? SocketData.getDefaultInstance() : socketData;
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public SocketDataOrBuilder getDataOrBuilder() {
        return getData();
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public Address getLocal() {
        Address address = this.local_;
        return address == null ? Address.getDefaultInstance() : address;
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public AddressOrBuilder getLocalOrBuilder() {
        return getLocal();
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public Address getRemote() {
        Address address = this.remote_;
        return address == null ? Address.getDefaultInstance() : address;
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public AddressOrBuilder getRemoteOrBuilder() {
        return getRemote();
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public Security getSecurity() {
        Security security = this.security_;
        return security == null ? Security.getDefaultInstance() : security;
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public SecurityOrBuilder getSecurityOrBuilder() {
        return getSecurity();
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public String getRemoteName() {
        Object obj = this.remoteName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.remoteName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.channelz.v1.SocketOrBuilder
    public ByteString getRemoteNameBytes() {
        Object obj = this.remoteName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.remoteName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.ref_ != null) {
            codedOutputStream.writeMessage(1, getRef());
        }
        if (this.data_ != null) {
            codedOutputStream.writeMessage(2, getData());
        }
        if (this.local_ != null) {
            codedOutputStream.writeMessage(3, getLocal());
        }
        if (this.remote_ != null) {
            codedOutputStream.writeMessage(4, getRemote());
        }
        if (this.security_ != null) {
            codedOutputStream.writeMessage(5, getSecurity());
        }
        if (!getRemoteNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.remoteName_);
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
        if (this.local_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getLocal());
        }
        if (this.remote_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(4, getRemote());
        }
        if (this.security_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(5, getSecurity());
        }
        if (!getRemoteNameBytes().isEmpty()) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(6, this.remoteName_);
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Socket)) {
            return super.equals(obj);
        }
        Socket socket = (Socket) obj;
        if (hasRef() != socket.hasRef()) {
            return false;
        }
        if ((hasRef() && !getRef().equals(socket.getRef())) || hasData() != socket.hasData()) {
            return false;
        }
        if ((hasData() && !getData().equals(socket.getData())) || hasLocal() != socket.hasLocal()) {
            return false;
        }
        if ((hasLocal() && !getLocal().equals(socket.getLocal())) || hasRemote() != socket.hasRemote()) {
            return false;
        }
        if ((!hasRemote() || getRemote().equals(socket.getRemote())) && hasSecurity() == socket.hasSecurity()) {
            return (!hasSecurity() || getSecurity().equals(socket.getSecurity())) && getRemoteName().equals(socket.getRemoteName()) && this.unknownFields.equals(socket.unknownFields);
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
        if (hasLocal()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getLocal().hashCode();
        }
        if (hasRemote()) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getRemote().hashCode();
        }
        if (hasSecurity()) {
            iHashCode = (((iHashCode * 37) + 5) * 53) + getSecurity().hashCode();
        }
        int iHashCode2 = (((((iHashCode * 37) + 6) * 53) + getRemoteName().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8685newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8688toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SocketOrBuilder {
        private SingleFieldBuilderV3<SocketData, SocketData.Builder, SocketDataOrBuilder> dataBuilder_;
        private SocketData data_;
        private SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> localBuilder_;
        private Address local_;
        private SingleFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> refBuilder_;
        private SocketRef ref_;
        private SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> remoteBuilder_;
        private Object remoteName_;
        private Address remote_;
        private SingleFieldBuilderV3<Security, Security.Builder, SecurityOrBuilder> securityBuilder_;
        private Security security_;

        private Builder() {
            this.remoteName_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.remoteName_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ChannelzProto.internal_static_grpc_channelz_v1_Socket_descriptor;
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public boolean hasData() {
            return (this.dataBuilder_ == null && this.data_ == null) ? false : true;
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public boolean hasLocal() {
            return (this.localBuilder_ == null && this.local_ == null) ? false : true;
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public boolean hasRef() {
            return (this.refBuilder_ == null && this.ref_ == null) ? false : true;
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public boolean hasRemote() {
            return (this.remoteBuilder_ == null && this.remote_ == null) ? false : true;
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public boolean hasSecurity() {
            return (this.securityBuilder_ == null && this.security_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ChannelzProto.internal_static_grpc_channelz_v1_Socket_fieldAccessorTable.ensureFieldAccessorsInitialized(Socket.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Socket.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8699clear() {
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
            if (this.localBuilder_ == null) {
                this.local_ = null;
            } else {
                this.local_ = null;
                this.localBuilder_ = null;
            }
            if (this.remoteBuilder_ == null) {
                this.remote_ = null;
            } else {
                this.remote_ = null;
                this.remoteBuilder_ = null;
            }
            if (this.securityBuilder_ == null) {
                this.security_ = null;
            } else {
                this.security_ = null;
                this.securityBuilder_ = null;
            }
            this.remoteName_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ChannelzProto.internal_static_grpc_channelz_v1_Socket_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Socket m8712getDefaultInstanceForType() {
            return Socket.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Socket m8693build() throws UninitializedMessageException {
            Socket socketM8695buildPartial = m8695buildPartial();
            if (socketM8695buildPartial.isInitialized()) {
                return socketM8695buildPartial;
            }
            throw newUninitializedMessageException(socketM8695buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Socket m8695buildPartial() {
            Socket socket = new Socket(this);
            SingleFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> singleFieldBuilderV3 = this.refBuilder_;
            if (singleFieldBuilderV3 == null) {
                socket.ref_ = this.ref_;
            } else {
                socket.ref_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<SocketData, SocketData.Builder, SocketDataOrBuilder> singleFieldBuilderV32 = this.dataBuilder_;
            if (singleFieldBuilderV32 == null) {
                socket.data_ = this.data_;
            } else {
                socket.data_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV33 = this.localBuilder_;
            if (singleFieldBuilderV33 == null) {
                socket.local_ = this.local_;
            } else {
                socket.local_ = singleFieldBuilderV33.build();
            }
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV34 = this.remoteBuilder_;
            if (singleFieldBuilderV34 == null) {
                socket.remote_ = this.remote_;
            } else {
                socket.remote_ = singleFieldBuilderV34.build();
            }
            SingleFieldBuilderV3<Security, Security.Builder, SecurityOrBuilder> singleFieldBuilderV35 = this.securityBuilder_;
            if (singleFieldBuilderV35 == null) {
                socket.security_ = this.security_;
            } else {
                socket.security_ = singleFieldBuilderV35.build();
            }
            socket.remoteName_ = this.remoteName_;
            onBuilt();
            return socket;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8711clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8723setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8701clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8704clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8725setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8691addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8716mergeFrom(Message message) {
            if (message instanceof Socket) {
                return mergeFrom((Socket) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Socket socket) {
            if (socket == Socket.getDefaultInstance()) {
                return this;
            }
            if (socket.hasRef()) {
                mergeRef(socket.getRef());
            }
            if (socket.hasData()) {
                mergeData(socket.getData());
            }
            if (socket.hasLocal()) {
                mergeLocal(socket.getLocal());
            }
            if (socket.hasRemote()) {
                mergeRemote(socket.getRemote());
            }
            if (socket.hasSecurity()) {
                mergeSecurity(socket.getSecurity());
            }
            if (!socket.getRemoteName().isEmpty()) {
                this.remoteName_ = socket.remoteName_;
                onChanged();
            }
            m8721mergeUnknownFields(socket.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.channelz.v1.Socket.Builder m8717mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.channelz.v1.Socket.access$1100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.channelz.v1.Socket r3 = (io.grpc.channelz.v1.Socket) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.channelz.v1.Socket r4 = (io.grpc.channelz.v1.Socket) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.channelz.v1.Socket.Builder.m8717mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.channelz.v1.Socket$Builder");
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public SocketRef getRef() {
            SingleFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> singleFieldBuilderV3 = this.refBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            SocketRef socketRef = this.ref_;
            return socketRef == null ? SocketRef.getDefaultInstance() : socketRef;
        }

        public Builder setRef(SocketRef socketRef) {
            SingleFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> singleFieldBuilderV3 = this.refBuilder_;
            if (singleFieldBuilderV3 == null) {
                socketRef.getClass();
                this.ref_ = socketRef;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(socketRef);
            }
            return this;
        }

        public Builder setRef(SocketRef.Builder builder) {
            SingleFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> singleFieldBuilderV3 = this.refBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.ref_ = builder.m8969build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m8969build());
            }
            return this;
        }

        public Builder mergeRef(SocketRef socketRef) {
            SingleFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> singleFieldBuilderV3 = this.refBuilder_;
            if (singleFieldBuilderV3 == null) {
                SocketRef socketRef2 = this.ref_;
                if (socketRef2 != null) {
                    this.ref_ = SocketRef.newBuilder(socketRef2).mergeFrom(socketRef).m8971buildPartial();
                } else {
                    this.ref_ = socketRef;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(socketRef);
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

        public SocketRef.Builder getRefBuilder() {
            onChanged();
            return getRefFieldBuilder().getBuilder();
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public SocketRefOrBuilder getRefOrBuilder() {
            SingleFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> singleFieldBuilderV3 = this.refBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (SocketRefOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            SocketRef socketRef = this.ref_;
            return socketRef == null ? SocketRef.getDefaultInstance() : socketRef;
        }

        private SingleFieldBuilderV3<SocketRef, SocketRef.Builder, SocketRefOrBuilder> getRefFieldBuilder() {
            if (this.refBuilder_ == null) {
                this.refBuilder_ = new SingleFieldBuilderV3<>(getRef(), getParentForChildren(), isClean());
                this.ref_ = null;
            }
            return this.refBuilder_;
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public SocketData getData() {
            SingleFieldBuilderV3<SocketData, SocketData.Builder, SocketDataOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            SocketData socketData = this.data_;
            return socketData == null ? SocketData.getDefaultInstance() : socketData;
        }

        public Builder setData(SocketData socketData) {
            SingleFieldBuilderV3<SocketData, SocketData.Builder, SocketDataOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
            if (singleFieldBuilderV3 == null) {
                socketData.getClass();
                this.data_ = socketData;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(socketData);
            }
            return this;
        }

        public Builder setData(SocketData.Builder builder) {
            SingleFieldBuilderV3<SocketData, SocketData.Builder, SocketDataOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.data_ = builder.m8739build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m8739build());
            }
            return this;
        }

        public Builder mergeData(SocketData socketData) {
            SingleFieldBuilderV3<SocketData, SocketData.Builder, SocketDataOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
            if (singleFieldBuilderV3 == null) {
                SocketData socketData2 = this.data_;
                if (socketData2 != null) {
                    this.data_ = SocketData.newBuilder(socketData2).mergeFrom(socketData).m8741buildPartial();
                } else {
                    this.data_ = socketData;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(socketData);
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

        public SocketData.Builder getDataBuilder() {
            onChanged();
            return getDataFieldBuilder().getBuilder();
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public SocketDataOrBuilder getDataOrBuilder() {
            SingleFieldBuilderV3<SocketData, SocketData.Builder, SocketDataOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (SocketDataOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            SocketData socketData = this.data_;
            return socketData == null ? SocketData.getDefaultInstance() : socketData;
        }

        private SingleFieldBuilderV3<SocketData, SocketData.Builder, SocketDataOrBuilder> getDataFieldBuilder() {
            if (this.dataBuilder_ == null) {
                this.dataBuilder_ = new SingleFieldBuilderV3<>(getData(), getParentForChildren(), isClean());
                this.data_ = null;
            }
            return this.dataBuilder_;
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public Address getLocal() {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.localBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Address address = this.local_;
            return address == null ? Address.getDefaultInstance() : address;
        }

        public Builder setLocal(Address address) {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.localBuilder_;
            if (singleFieldBuilderV3 == null) {
                address.getClass();
                this.local_ = address;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(address);
            }
            return this;
        }

        public Builder setLocal(Address.Builder builder) {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.localBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.local_ = builder.m7311build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m7311build());
            }
            return this;
        }

        public Builder mergeLocal(Address address) {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.localBuilder_;
            if (singleFieldBuilderV3 == null) {
                Address address2 = this.local_;
                if (address2 != null) {
                    this.local_ = Address.newBuilder(address2).mergeFrom(address).m7313buildPartial();
                } else {
                    this.local_ = address;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(address);
            }
            return this;
        }

        public Builder clearLocal() {
            if (this.localBuilder_ == null) {
                this.local_ = null;
                onChanged();
            } else {
                this.local_ = null;
                this.localBuilder_ = null;
            }
            return this;
        }

        public Address.Builder getLocalBuilder() {
            onChanged();
            return getLocalFieldBuilder().getBuilder();
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public AddressOrBuilder getLocalOrBuilder() {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.localBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (AddressOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Address address = this.local_;
            return address == null ? Address.getDefaultInstance() : address;
        }

        private SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> getLocalFieldBuilder() {
            if (this.localBuilder_ == null) {
                this.localBuilder_ = new SingleFieldBuilderV3<>(getLocal(), getParentForChildren(), isClean());
                this.local_ = null;
            }
            return this.localBuilder_;
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public Address getRemote() {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.remoteBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Address address = this.remote_;
            return address == null ? Address.getDefaultInstance() : address;
        }

        public Builder setRemote(Address address) {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.remoteBuilder_;
            if (singleFieldBuilderV3 == null) {
                address.getClass();
                this.remote_ = address;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(address);
            }
            return this;
        }

        public Builder setRemote(Address.Builder builder) {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.remoteBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.remote_ = builder.m7311build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m7311build());
            }
            return this;
        }

        public Builder mergeRemote(Address address) {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.remoteBuilder_;
            if (singleFieldBuilderV3 == null) {
                Address address2 = this.remote_;
                if (address2 != null) {
                    this.remote_ = Address.newBuilder(address2).mergeFrom(address).m7313buildPartial();
                } else {
                    this.remote_ = address;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(address);
            }
            return this;
        }

        public Builder clearRemote() {
            if (this.remoteBuilder_ == null) {
                this.remote_ = null;
                onChanged();
            } else {
                this.remote_ = null;
                this.remoteBuilder_ = null;
            }
            return this;
        }

        public Address.Builder getRemoteBuilder() {
            onChanged();
            return getRemoteFieldBuilder().getBuilder();
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public AddressOrBuilder getRemoteOrBuilder() {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.remoteBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (AddressOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Address address = this.remote_;
            return address == null ? Address.getDefaultInstance() : address;
        }

        private SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> getRemoteFieldBuilder() {
            if (this.remoteBuilder_ == null) {
                this.remoteBuilder_ = new SingleFieldBuilderV3<>(getRemote(), getParentForChildren(), isClean());
                this.remote_ = null;
            }
            return this.remoteBuilder_;
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public Security getSecurity() {
            SingleFieldBuilderV3<Security, Security.Builder, SecurityOrBuilder> singleFieldBuilderV3 = this.securityBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Security security = this.security_;
            return security == null ? Security.getDefaultInstance() : security;
        }

        public Builder setSecurity(Security security) {
            SingleFieldBuilderV3<Security, Security.Builder, SecurityOrBuilder> singleFieldBuilderV3 = this.securityBuilder_;
            if (singleFieldBuilderV3 == null) {
                security.getClass();
                this.security_ = security;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(security);
            }
            return this;
        }

        public Builder setSecurity(Security.Builder builder) {
            SingleFieldBuilderV3<Security, Security.Builder, SecurityOrBuilder> singleFieldBuilderV3 = this.securityBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.security_ = builder.m8417build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m8417build());
            }
            return this;
        }

        public Builder mergeSecurity(Security security) {
            SingleFieldBuilderV3<Security, Security.Builder, SecurityOrBuilder> singleFieldBuilderV3 = this.securityBuilder_;
            if (singleFieldBuilderV3 == null) {
                Security security2 = this.security_;
                if (security2 != null) {
                    this.security_ = Security.newBuilder(security2).mergeFrom(security).m8419buildPartial();
                } else {
                    this.security_ = security;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(security);
            }
            return this;
        }

        public Builder clearSecurity() {
            if (this.securityBuilder_ == null) {
                this.security_ = null;
                onChanged();
            } else {
                this.security_ = null;
                this.securityBuilder_ = null;
            }
            return this;
        }

        public Security.Builder getSecurityBuilder() {
            onChanged();
            return getSecurityFieldBuilder().getBuilder();
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public SecurityOrBuilder getSecurityOrBuilder() {
            SingleFieldBuilderV3<Security, Security.Builder, SecurityOrBuilder> singleFieldBuilderV3 = this.securityBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (SecurityOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Security security = this.security_;
            return security == null ? Security.getDefaultInstance() : security;
        }

        private SingleFieldBuilderV3<Security, Security.Builder, SecurityOrBuilder> getSecurityFieldBuilder() {
            if (this.securityBuilder_ == null) {
                this.securityBuilder_ = new SingleFieldBuilderV3<>(getSecurity(), getParentForChildren(), isClean());
                this.security_ = null;
            }
            return this.securityBuilder_;
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public String getRemoteName() {
            Object obj = this.remoteName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.remoteName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setRemoteName(String str) {
            str.getClass();
            this.remoteName_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOrBuilder
        public ByteString getRemoteNameBytes() {
            Object obj = this.remoteName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.remoteName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRemoteNameBytes(ByteString byteString) {
            byteString.getClass();
            Socket.checkByteStringIsUtf8(byteString);
            this.remoteName_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRemoteName() {
            this.remoteName_ = Socket.getDefaultInstance().getRemoteName();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8727setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8721mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
