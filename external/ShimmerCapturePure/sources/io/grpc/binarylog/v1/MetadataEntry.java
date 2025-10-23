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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class MetadataEntry extends GeneratedMessageV3 implements MetadataEntryOrBuilder {
    public static final int KEY_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final MetadataEntry DEFAULT_INSTANCE = new MetadataEntry();
    private static final Parser<MetadataEntry> PARSER = new AbstractParser<MetadataEntry>() { // from class: io.grpc.binarylog.v1.MetadataEntry.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public MetadataEntry m7170parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MetadataEntry(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object key_;
    private byte memoizedIsInitialized;
    private ByteString value_;

    private MetadataEntry(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private MetadataEntry() {
        this.memoizedIsInitialized = (byte) -1;
        this.key_ = "";
        this.value_ = ByteString.EMPTY;
    }

    private MetadataEntry(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.key_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.value_ = codedInputStream.readBytes();
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

    public static MetadataEntry getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MetadataEntry> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BinaryLogProto.internal_static_grpc_binarylog_v1_MetadataEntry_descriptor;
    }

    public static MetadataEntry parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MetadataEntry) PARSER.parseFrom(byteBuffer);
    }

    public static MetadataEntry parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetadataEntry) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MetadataEntry parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MetadataEntry) PARSER.parseFrom(byteString);
    }

    public static MetadataEntry parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetadataEntry) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MetadataEntry parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MetadataEntry) PARSER.parseFrom(bArr);
    }

    public static MetadataEntry parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetadataEntry) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MetadataEntry parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MetadataEntry parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetadataEntry parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MetadataEntry parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetadataEntry parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MetadataEntry parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m7168toBuilder();
    }

    public static Builder newBuilder(MetadataEntry metadataEntry) {
        return DEFAULT_INSTANCE.m7168toBuilder().mergeFrom(metadataEntry);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public MetadataEntry m7163getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<MetadataEntry> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.binarylog.v1.MetadataEntryOrBuilder
    public ByteString getValue() {
        return this.value_;
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
        return new MetadataEntry();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BinaryLogProto.internal_static_grpc_binarylog_v1_MetadataEntry_fieldAccessorTable.ensureFieldAccessorsInitialized(MetadataEntry.class, Builder.class);
    }

    @Override // io.grpc.binarylog.v1.MetadataEntryOrBuilder
    public String getKey() {
        Object obj = this.key_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.key_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.binarylog.v1.MetadataEntryOrBuilder
    public ByteString getKeyBytes() {
        Object obj = this.key_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.key_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getKeyBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.key_);
        }
        if (!this.value_.isEmpty()) {
            codedOutputStream.writeBytes(2, this.value_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getKeyBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.key_) : 0;
        if (!this.value_.isEmpty()) {
            iComputeStringSize += CodedOutputStream.computeBytesSize(2, this.value_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MetadataEntry)) {
            return super.equals(obj);
        }
        MetadataEntry metadataEntry = (MetadataEntry) obj;
        return getKey().equals(metadataEntry.getKey()) && getValue().equals(metadataEntry.getValue()) && this.unknownFields.equals(metadataEntry.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getKey().hashCode()) * 37) + 2) * 53) + getValue().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m7165newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m7168toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetadataEntryOrBuilder {
        private Object key_;
        private ByteString value_;

        private Builder() {
            this.key_ = "";
            this.value_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.key_ = "";
            this.value_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_MetadataEntry_descriptor;
        }

        @Override // io.grpc.binarylog.v1.MetadataEntryOrBuilder
        public ByteString getValue() {
            return this.value_;
        }

        public Builder setValue(ByteString byteString) {
            byteString.getClass();
            this.value_ = byteString;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_MetadataEntry_fieldAccessorTable.ensureFieldAccessorsInitialized(MetadataEntry.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = MetadataEntry.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7179clear() {
            super.clear();
            this.key_ = "";
            this.value_ = ByteString.EMPTY;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_MetadataEntry_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetadataEntry m7192getDefaultInstanceForType() {
            return MetadataEntry.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetadataEntry m7173build() throws UninitializedMessageException {
            MetadataEntry metadataEntryM7175buildPartial = m7175buildPartial();
            if (metadataEntryM7175buildPartial.isInitialized()) {
                return metadataEntryM7175buildPartial;
            }
            throw newUninitializedMessageException(metadataEntryM7175buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetadataEntry m7175buildPartial() {
            MetadataEntry metadataEntry = new MetadataEntry(this);
            metadataEntry.key_ = this.key_;
            metadataEntry.value_ = this.value_;
            onBuilt();
            return metadataEntry;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7191clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7203setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7181clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7184clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7205setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7171addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7196mergeFrom(com.google.protobuf.Message message) {
            if (message instanceof MetadataEntry) {
                return mergeFrom((MetadataEntry) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MetadataEntry metadataEntry) {
            if (metadataEntry == MetadataEntry.getDefaultInstance()) {
                return this;
            }
            if (!metadataEntry.getKey().isEmpty()) {
                this.key_ = metadataEntry.key_;
                onChanged();
            }
            if (metadataEntry.getValue() != ByteString.EMPTY) {
                setValue(metadataEntry.getValue());
            }
            m7201mergeUnknownFields(metadataEntry.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.binarylog.v1.MetadataEntry.Builder m7197mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.binarylog.v1.MetadataEntry.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.binarylog.v1.MetadataEntry r3 = (io.grpc.binarylog.v1.MetadataEntry) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.binarylog.v1.MetadataEntry r4 = (io.grpc.binarylog.v1.MetadataEntry) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.binarylog.v1.MetadataEntry.Builder.m7197mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.binarylog.v1.MetadataEntry$Builder");
        }

        @Override // io.grpc.binarylog.v1.MetadataEntryOrBuilder
        public String getKey() {
            Object obj = this.key_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.key_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setKey(String str) {
            str.getClass();
            this.key_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.binarylog.v1.MetadataEntryOrBuilder
        public ByteString getKeyBytes() {
            Object obj = this.key_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.key_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setKeyBytes(ByteString byteString) {
            byteString.getClass();
            MetadataEntry.checkByteStringIsUtf8(byteString);
            this.key_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearKey() {
            this.key_ = MetadataEntry.getDefaultInstance().getKey();
            onChanged();
            return this;
        }

        public Builder clearValue() {
            this.value_ = MetadataEntry.getDefaultInstance().getValue();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m7207setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m7201mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
