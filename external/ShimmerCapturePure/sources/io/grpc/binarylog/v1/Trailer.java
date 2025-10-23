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
public final class Trailer extends GeneratedMessageV3 implements TrailerOrBuilder {
    public static final int METADATA_FIELD_NUMBER = 1;
    public static final int STATUS_CODE_FIELD_NUMBER = 2;
    public static final int STATUS_DETAILS_FIELD_NUMBER = 4;
    public static final int STATUS_MESSAGE_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final Trailer DEFAULT_INSTANCE = new Trailer();
    private static final Parser<Trailer> PARSER = new AbstractParser<Trailer>() { // from class: io.grpc.binarylog.v1.Trailer.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Trailer m7262parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Trailer(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private Metadata metadata_;
    private int statusCode_;
    private ByteString statusDetails_;
    private volatile Object statusMessage_;

    private Trailer(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Trailer() {
        this.memoizedIsInitialized = (byte) -1;
        this.statusMessage_ = "";
        this.statusDetails_ = ByteString.EMPTY;
    }

    private Trailer(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            } else if (tag == 16) {
                                this.statusCode_ = codedInputStream.readUInt32();
                            } else if (tag == 26) {
                                this.statusMessage_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 34) {
                                this.statusDetails_ = codedInputStream.readBytes();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    }
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static Trailer getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Trailer> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BinaryLogProto.internal_static_grpc_binarylog_v1_Trailer_descriptor;
    }

    public static Trailer parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Trailer) PARSER.parseFrom(byteBuffer);
    }

    public static Trailer parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Trailer) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Trailer parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Trailer) PARSER.parseFrom(byteString);
    }

    public static Trailer parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Trailer) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Trailer parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Trailer) PARSER.parseFrom(bArr);
    }

    public static Trailer parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Trailer) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Trailer parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Trailer parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Trailer parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Trailer parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Trailer parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Trailer parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m7260toBuilder();
    }

    public static Builder newBuilder(Trailer trailer) {
        return DEFAULT_INSTANCE.m7260toBuilder().mergeFrom(trailer);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Trailer m7255getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Trailer> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.binarylog.v1.TrailerOrBuilder
    public int getStatusCode() {
        return this.statusCode_;
    }

    @Override // io.grpc.binarylog.v1.TrailerOrBuilder
    public ByteString getStatusDetails() {
        return this.statusDetails_;
    }

    @Override // io.grpc.binarylog.v1.TrailerOrBuilder
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
        return new Trailer();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BinaryLogProto.internal_static_grpc_binarylog_v1_Trailer_fieldAccessorTable.ensureFieldAccessorsInitialized(Trailer.class, Builder.class);
    }

    @Override // io.grpc.binarylog.v1.TrailerOrBuilder
    public Metadata getMetadata() {
        Metadata metadata = this.metadata_;
        return metadata == null ? Metadata.getDefaultInstance() : metadata;
    }

    @Override // io.grpc.binarylog.v1.TrailerOrBuilder
    public MetadataOrBuilder getMetadataOrBuilder() {
        return getMetadata();
    }

    @Override // io.grpc.binarylog.v1.TrailerOrBuilder
    public String getStatusMessage() {
        Object obj = this.statusMessage_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.statusMessage_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.binarylog.v1.TrailerOrBuilder
    public ByteString getStatusMessageBytes() {
        Object obj = this.statusMessage_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.statusMessage_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.metadata_ != null) {
            codedOutputStream.writeMessage(1, getMetadata());
        }
        int i = this.statusCode_;
        if (i != 0) {
            codedOutputStream.writeUInt32(2, i);
        }
        if (!getStatusMessageBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.statusMessage_);
        }
        if (!this.statusDetails_.isEmpty()) {
            codedOutputStream.writeBytes(4, this.statusDetails_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.metadata_ != null ? CodedOutputStream.computeMessageSize(1, getMetadata()) : 0;
        int i2 = this.statusCode_;
        if (i2 != 0) {
            iComputeMessageSize += CodedOutputStream.computeUInt32Size(2, i2);
        }
        if (!getStatusMessageBytes().isEmpty()) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(3, this.statusMessage_);
        }
        if (!this.statusDetails_.isEmpty()) {
            iComputeMessageSize += CodedOutputStream.computeBytesSize(4, this.statusDetails_);
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Trailer)) {
            return super.equals(obj);
        }
        Trailer trailer = (Trailer) obj;
        if (hasMetadata() != trailer.hasMetadata()) {
            return false;
        }
        return (!hasMetadata() || getMetadata().equals(trailer.getMetadata())) && getStatusCode() == trailer.getStatusCode() && getStatusMessage().equals(trailer.getStatusMessage()) && getStatusDetails().equals(trailer.getStatusDetails()) && this.unknownFields.equals(trailer.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasMetadata()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getMetadata().hashCode();
        }
        int statusCode = (((((((((((((iHashCode * 37) + 2) * 53) + getStatusCode()) * 37) + 3) * 53) + getStatusMessage().hashCode()) * 37) + 4) * 53) + getStatusDetails().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = statusCode;
        return statusCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m7257newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m7260toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TrailerOrBuilder {
        private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> metadataBuilder_;
        private Metadata metadata_;
        private int statusCode_;
        private ByteString statusDetails_;
        private Object statusMessage_;

        private Builder() {
            this.statusMessage_ = "";
            this.statusDetails_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.statusMessage_ = "";
            this.statusDetails_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_Trailer_descriptor;
        }

        @Override // io.grpc.binarylog.v1.TrailerOrBuilder
        public int getStatusCode() {
            return this.statusCode_;
        }

        public Builder setStatusCode(int i) {
            this.statusCode_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.binarylog.v1.TrailerOrBuilder
        public ByteString getStatusDetails() {
            return this.statusDetails_;
        }

        public Builder setStatusDetails(ByteString byteString) {
            byteString.getClass();
            this.statusDetails_ = byteString;
            onChanged();
            return this;
        }

        @Override // io.grpc.binarylog.v1.TrailerOrBuilder
        public boolean hasMetadata() {
            return (this.metadataBuilder_ == null && this.metadata_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_Trailer_fieldAccessorTable.ensureFieldAccessorsInitialized(Trailer.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Trailer.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7271clear() {
            super.clear();
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            this.statusCode_ = 0;
            this.statusMessage_ = "";
            this.statusDetails_ = ByteString.EMPTY;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_Trailer_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Trailer m7284getDefaultInstanceForType() {
            return Trailer.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Trailer m7265build() throws UninitializedMessageException {
            Trailer trailerM7267buildPartial = m7267buildPartial();
            if (trailerM7267buildPartial.isInitialized()) {
                return trailerM7267buildPartial;
            }
            throw newUninitializedMessageException(trailerM7267buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Trailer m7267buildPartial() {
            Trailer trailer = new Trailer(this);
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                trailer.metadata_ = this.metadata_;
            } else {
                trailer.metadata_ = singleFieldBuilderV3.build();
            }
            trailer.statusCode_ = this.statusCode_;
            trailer.statusMessage_ = this.statusMessage_;
            trailer.statusDetails_ = this.statusDetails_;
            onBuilt();
            return trailer;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7283clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7295setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7273clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7276clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7297setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7263addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7288mergeFrom(com.google.protobuf.Message message) {
            if (message instanceof Trailer) {
                return mergeFrom((Trailer) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Trailer trailer) {
            if (trailer == Trailer.getDefaultInstance()) {
                return this;
            }
            if (trailer.hasMetadata()) {
                mergeMetadata(trailer.getMetadata());
            }
            if (trailer.getStatusCode() != 0) {
                setStatusCode(trailer.getStatusCode());
            }
            if (!trailer.getStatusMessage().isEmpty()) {
                this.statusMessage_ = trailer.statusMessage_;
                onChanged();
            }
            if (trailer.getStatusDetails() != ByteString.EMPTY) {
                setStatusDetails(trailer.getStatusDetails());
            }
            m7293mergeUnknownFields(trailer.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.binarylog.v1.Trailer.Builder m7289mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.binarylog.v1.Trailer.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.binarylog.v1.Trailer r3 = (io.grpc.binarylog.v1.Trailer) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.binarylog.v1.Trailer r4 = (io.grpc.binarylog.v1.Trailer) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.binarylog.v1.Trailer.Builder.m7289mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.binarylog.v1.Trailer$Builder");
        }

        @Override // io.grpc.binarylog.v1.TrailerOrBuilder
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

        @Override // io.grpc.binarylog.v1.TrailerOrBuilder
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

        public Builder clearStatusCode() {
            this.statusCode_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.binarylog.v1.TrailerOrBuilder
        public String getStatusMessage() {
            Object obj = this.statusMessage_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.statusMessage_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setStatusMessage(String str) {
            str.getClass();
            this.statusMessage_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.binarylog.v1.TrailerOrBuilder
        public ByteString getStatusMessageBytes() {
            Object obj = this.statusMessage_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.statusMessage_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setStatusMessageBytes(ByteString byteString) {
            byteString.getClass();
            Trailer.checkByteStringIsUtf8(byteString);
            this.statusMessage_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearStatusMessage() {
            this.statusMessage_ = Trailer.getDefaultInstance().getStatusMessage();
            onChanged();
            return this;
        }

        public Builder clearStatusDetails() {
            this.statusDetails_ = Trailer.getDefaultInstance().getStatusDetails();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m7299setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m7293mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
