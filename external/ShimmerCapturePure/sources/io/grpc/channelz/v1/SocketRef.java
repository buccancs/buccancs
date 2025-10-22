package io.grpc.channelz.v1;

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
public final class SocketRef extends GeneratedMessageV3 implements SocketRefOrBuilder {
    public static final int NAME_FIELD_NUMBER = 4;
    public static final int SOCKET_ID_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final SocketRef DEFAULT_INSTANCE = new SocketRef();
    private static final Parser<SocketRef> PARSER = new AbstractParser<SocketRef>() { // from class: io.grpc.channelz.v1.SocketRef.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public SocketRef m8966parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SocketRef(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private long socketId_;

    private SocketRef(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SocketRef() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    private SocketRef(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag == 24) {
                                this.socketId_ = codedInputStream.readInt64();
                            } else if (tag == 34) {
                                this.name_ = codedInputStream.readStringRequireUtf8();
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

    public static SocketRef getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SocketRef> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ChannelzProto.internal_static_grpc_channelz_v1_SocketRef_descriptor;
    }

    public static SocketRef parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (SocketRef) PARSER.parseFrom(byteBuffer);
    }

    public static SocketRef parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketRef) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SocketRef parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SocketRef) PARSER.parseFrom(byteString);
    }

    public static SocketRef parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketRef) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SocketRef parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SocketRef) PARSER.parseFrom(bArr);
    }

    public static SocketRef parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketRef) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SocketRef parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SocketRef parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SocketRef parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SocketRef parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SocketRef parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SocketRef parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m8964toBuilder();
    }

    public static Builder newBuilder(SocketRef socketRef) {
        return DEFAULT_INSTANCE.m8964toBuilder().mergeFrom(socketRef);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SocketRef m8959getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<SocketRef> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.channelz.v1.SocketRefOrBuilder
    public long getSocketId() {
        return this.socketId_;
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
        return new SocketRef();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ChannelzProto.internal_static_grpc_channelz_v1_SocketRef_fieldAccessorTable.ensureFieldAccessorsInitialized(SocketRef.class, Builder.class);
    }

    @Override // io.grpc.channelz.v1.SocketRefOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.channelz.v1.SocketRefOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        long j = this.socketId_;
        if (j != 0) {
            codedOutputStream.writeInt64(3, j);
        }
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.name_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        long j = this.socketId_;
        int iComputeInt64Size = j != 0 ? CodedOutputStream.computeInt64Size(3, j) : 0;
        if (!getNameBytes().isEmpty()) {
            iComputeInt64Size += GeneratedMessageV3.computeStringSize(4, this.name_);
        }
        int serializedSize = iComputeInt64Size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SocketRef)) {
            return super.equals(obj);
        }
        SocketRef socketRef = (SocketRef) obj;
        return getSocketId() == socketRef.getSocketId() && getName().equals(socketRef.getName()) && this.unknownFields.equals(socketRef.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getSocketId())) * 37) + 4) * 53) + getName().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8961newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8964toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SocketRefOrBuilder {
        private Object name_;
        private long socketId_;

        private Builder() {
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ChannelzProto.internal_static_grpc_channelz_v1_SocketRef_descriptor;
        }

        @Override // io.grpc.channelz.v1.SocketRefOrBuilder
        public long getSocketId() {
            return this.socketId_;
        }

        public Builder setSocketId(long j) {
            this.socketId_ = j;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ChannelzProto.internal_static_grpc_channelz_v1_SocketRef_fieldAccessorTable.ensureFieldAccessorsInitialized(SocketRef.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SocketRef.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8975clear() {
            super.clear();
            this.socketId_ = 0L;
            this.name_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ChannelzProto.internal_static_grpc_channelz_v1_SocketRef_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketRef m8988getDefaultInstanceForType() {
            return SocketRef.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketRef m8969build() throws UninitializedMessageException {
            SocketRef socketRefM8971buildPartial = m8971buildPartial();
            if (socketRefM8971buildPartial.isInitialized()) {
                return socketRefM8971buildPartial;
            }
            throw newUninitializedMessageException(socketRefM8971buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketRef m8971buildPartial() {
            SocketRef socketRef = new SocketRef(this);
            socketRef.socketId_ = this.socketId_;
            socketRef.name_ = this.name_;
            onBuilt();
            return socketRef;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8987clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8999setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8977clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8980clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9001setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8967addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8992mergeFrom(Message message) {
            if (message instanceof SocketRef) {
                return mergeFrom((SocketRef) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SocketRef socketRef) {
            if (socketRef == SocketRef.getDefaultInstance()) {
                return this;
            }
            if (socketRef.getSocketId() != 0) {
                setSocketId(socketRef.getSocketId());
            }
            if (!socketRef.getName().isEmpty()) {
                this.name_ = socketRef.name_;
                onChanged();
            }
            m8997mergeUnknownFields(socketRef.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.channelz.v1.SocketRef.Builder m8993mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.channelz.v1.SocketRef.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.channelz.v1.SocketRef r3 = (io.grpc.channelz.v1.SocketRef) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.channelz.v1.SocketRef r4 = (io.grpc.channelz.v1.SocketRef) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.channelz.v1.SocketRef.Builder.m8993mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.channelz.v1.SocketRef$Builder");
        }

        public Builder clearSocketId() {
            this.socketId_ = 0L;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketRefOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setName(String str) {
            str.getClass();
            this.name_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketRefOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNameBytes(ByteString byteString) {
            byteString.getClass();
            SocketRef.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = SocketRef.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9003setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8997mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
