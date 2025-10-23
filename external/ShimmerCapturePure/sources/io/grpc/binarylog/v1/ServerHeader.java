package io.grpc.binarylog.v1;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.binarylog.v1.Metadata;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class ServerHeader extends GeneratedMessageV3 implements ServerHeaderOrBuilder {
    public static final int METADATA_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final ServerHeader DEFAULT_INSTANCE = new ServerHeader();
    private static final Parser<ServerHeader> PARSER = new AbstractParser<ServerHeader>() { // from class: io.grpc.binarylog.v1.ServerHeader.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ServerHeader m7216parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ServerHeader(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private Metadata metadata_;

    private ServerHeader(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ServerHeader() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private ServerHeader(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                Metadata metadata = this.metadata_;
                                Metadata.Builder builderM7122toBuilder = metadata != null ? metadata.m7122toBuilder() : null;
                                Metadata metadata2 = (Metadata) codedInputStream.readMessage(Metadata.parser(), extensionRegistryLite);
                                this.metadata_ = metadata2;
                                if (builderM7122toBuilder != null) {
                                    builderM7122toBuilder.mergeFrom(metadata2);
                                    this.metadata_ = builderM7122toBuilder.m7129buildPartial();
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

    public static ServerHeader getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ServerHeader> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BinaryLogProto.internal_static_grpc_binarylog_v1_ServerHeader_descriptor;
    }

    public static ServerHeader parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ServerHeader) PARSER.parseFrom(byteBuffer);
    }

    public static ServerHeader parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ServerHeader) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ServerHeader parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ServerHeader) PARSER.parseFrom(byteString);
    }

    public static ServerHeader parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ServerHeader) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ServerHeader parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ServerHeader) PARSER.parseFrom(bArr);
    }

    public static ServerHeader parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ServerHeader) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ServerHeader parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ServerHeader parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ServerHeader parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ServerHeader parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ServerHeader parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ServerHeader parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m7214toBuilder();
    }

    public static Builder newBuilder(ServerHeader serverHeader) {
        return DEFAULT_INSTANCE.m7214toBuilder().mergeFrom(serverHeader);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ServerHeader m7209getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ServerHeader> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.binarylog.v1.ServerHeaderOrBuilder
    public boolean hasMetadata() {
        return this.metadata_ != null;
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
        return new ServerHeader();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BinaryLogProto.internal_static_grpc_binarylog_v1_ServerHeader_fieldAccessorTable.ensureFieldAccessorsInitialized(ServerHeader.class, Builder.class);
    }

    @Override // io.grpc.binarylog.v1.ServerHeaderOrBuilder
    public Metadata getMetadata() {
        Metadata metadata = this.metadata_;
        return metadata == null ? Metadata.getDefaultInstance() : metadata;
    }

    @Override // io.grpc.binarylog.v1.ServerHeaderOrBuilder
    public MetadataOrBuilder getMetadataOrBuilder() {
        return getMetadata();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.metadata_ != null) {
            codedOutputStream.writeMessage(1, getMetadata());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = (this.metadata_ != null ? CodedOutputStream.computeMessageSize(1, getMetadata()) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeMessageSize;
        return iComputeMessageSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ServerHeader)) {
            return super.equals(obj);
        }
        ServerHeader serverHeader = (ServerHeader) obj;
        if (hasMetadata() != serverHeader.hasMetadata()) {
            return false;
        }
        return (!hasMetadata() || getMetadata().equals(serverHeader.getMetadata())) && this.unknownFields.equals(serverHeader.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasMetadata()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getMetadata().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m7211newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m7214toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ServerHeaderOrBuilder {
        private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> metadataBuilder_;
        private Metadata metadata_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_ServerHeader_descriptor;
        }

        @Override // io.grpc.binarylog.v1.ServerHeaderOrBuilder
        public boolean hasMetadata() {
            return (this.metadataBuilder_ == null && this.metadata_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_ServerHeader_fieldAccessorTable.ensureFieldAccessorsInitialized(ServerHeader.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ServerHeader.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7225clear() {
            super.clear();
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_ServerHeader_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ServerHeader m7238getDefaultInstanceForType() {
            return ServerHeader.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ServerHeader m7219build() throws UninitializedMessageException {
            ServerHeader serverHeaderM7221buildPartial = m7221buildPartial();
            if (serverHeaderM7221buildPartial.isInitialized()) {
                return serverHeaderM7221buildPartial;
            }
            throw newUninitializedMessageException(serverHeaderM7221buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ServerHeader m7221buildPartial() {
            ServerHeader serverHeader = new ServerHeader(this);
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                serverHeader.metadata_ = this.metadata_;
            } else {
                serverHeader.metadata_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return serverHeader;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7237clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7249setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7227clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7230clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7251setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7217addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7242mergeFrom(com.google.protobuf.Message message) {
            if (message instanceof ServerHeader) {
                return mergeFrom((ServerHeader) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ServerHeader serverHeader) {
            if (serverHeader == ServerHeader.getDefaultInstance()) {
                return this;
            }
            if (serverHeader.hasMetadata()) {
                mergeMetadata(serverHeader.getMetadata());
            }
            m7247mergeUnknownFields(serverHeader.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.binarylog.v1.ServerHeader.Builder m7243mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.binarylog.v1.ServerHeader.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.binarylog.v1.ServerHeader r3 = (io.grpc.binarylog.v1.ServerHeader) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.binarylog.v1.ServerHeader r4 = (io.grpc.binarylog.v1.ServerHeader) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.binarylog.v1.ServerHeader.Builder.m7243mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.binarylog.v1.ServerHeader$Builder");
        }

        @Override // io.grpc.binarylog.v1.ServerHeaderOrBuilder
        public Metadata getMetadata() {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Metadata metadata = this.metadata_;
            return metadata == null ? Metadata.getDefaultInstance() : metadata;
        }

        public Builder setMetadata(Metadata metadata) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                metadata.getClass();
                this.metadata_ = metadata;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(metadata);
            }
            return this;
        }

        public Builder setMetadata(Metadata.Builder builder) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.metadata_ = builder.m7127build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m7127build());
            }
            return this;
        }

        public Builder mergeMetadata(Metadata metadata) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Metadata metadata2 = this.metadata_;
                if (metadata2 != null) {
                    this.metadata_ = Metadata.newBuilder(metadata2).mergeFrom(metadata).m7129buildPartial();
                } else {
                    this.metadata_ = metadata;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(metadata);
            }
            return this;
        }

        public Builder clearMetadata() {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
                onChanged();
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        public Metadata.Builder getMetadataBuilder() {
            onChanged();
            return getMetadataFieldBuilder().getBuilder();
        }

        @Override // io.grpc.binarylog.v1.ServerHeaderOrBuilder
        public MetadataOrBuilder getMetadataOrBuilder() {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (MetadataOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Metadata metadata = this.metadata_;
            return metadata == null ? Metadata.getDefaultInstance() : metadata;
        }

        private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> getMetadataFieldBuilder() {
            if (this.metadataBuilder_ == null) {
                this.metadataBuilder_ = new SingleFieldBuilderV3<>(getMetadata(), getParentForChildren(), isClean());
                this.metadata_ = null;
            }
            return this.metadataBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m7253setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m7247mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
