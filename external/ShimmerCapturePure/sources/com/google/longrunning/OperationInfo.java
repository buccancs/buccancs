package com.google.longrunning;

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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class OperationInfo extends GeneratedMessageV3 implements OperationInfoOrBuilder {
    public static final int METADATA_TYPE_FIELD_NUMBER = 2;
    public static final int RESPONSE_TYPE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final OperationInfo DEFAULT_INSTANCE = new OperationInfo();
    private static final Parser<OperationInfo> PARSER = new AbstractParser<OperationInfo>() { // from class: com.google.longrunning.OperationInfo.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public OperationInfo m3307parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new OperationInfo(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private volatile Object metadataType_;
    private volatile Object responseType_;

    private OperationInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private OperationInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.responseType_ = "";
        this.metadataType_ = "";
    }

    private OperationInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.responseType_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.metadataType_ = codedInputStream.readStringRequireUtf8();
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

    public static OperationInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<OperationInfo> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return OperationsProto.internal_static_google_longrunning_OperationInfo_descriptor;
    }

    public static OperationInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (OperationInfo) PARSER.parseFrom(byteBuffer);
    }

    public static OperationInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (OperationInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static OperationInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (OperationInfo) PARSER.parseFrom(byteString);
    }

    public static OperationInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (OperationInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static OperationInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (OperationInfo) PARSER.parseFrom(bArr);
    }

    public static OperationInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (OperationInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static OperationInfo parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static OperationInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static OperationInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static OperationInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static OperationInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static OperationInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3306toBuilder();
    }

    public static Builder newBuilder(OperationInfo operationInfo) {
        return DEFAULT_INSTANCE.m3306toBuilder().mergeFrom(operationInfo);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public OperationInfo m3301getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<OperationInfo> getParserForType() {
        return PARSER;
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

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return OperationsProto.internal_static_google_longrunning_OperationInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(OperationInfo.class, Builder.class);
    }

    @Override // com.google.longrunning.OperationInfoOrBuilder
    public String getResponseType() {
        Object obj = this.responseType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.responseType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.longrunning.OperationInfoOrBuilder
    public ByteString getResponseTypeBytes() {
        Object obj = this.responseType_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.responseType_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.longrunning.OperationInfoOrBuilder
    public String getMetadataType() {
        Object obj = this.metadataType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.metadataType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.longrunning.OperationInfoOrBuilder
    public ByteString getMetadataTypeBytes() {
        Object obj = this.metadataType_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.metadataType_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getResponseTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.responseType_);
        }
        if (!getMetadataTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.metadataType_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getResponseTypeBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.responseType_) : 0;
        if (!getMetadataTypeBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.metadataType_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OperationInfo)) {
            return super.equals(obj);
        }
        OperationInfo operationInfo = (OperationInfo) obj;
        return getResponseType().equals(operationInfo.getResponseType()) && getMetadataType().equals(operationInfo.getMetadataType()) && this.unknownFields.equals(operationInfo.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getResponseType().hashCode()) * 37) + 2) * 53) + getMetadataType().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3304newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3306toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m3303newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OperationInfoOrBuilder {
        private Object metadataType_;
        private Object responseType_;

        private Builder() {
            this.responseType_ = "";
            this.metadataType_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.responseType_ = "";
            this.metadataType_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return OperationsProto.internal_static_google_longrunning_OperationInfo_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return OperationsProto.internal_static_google_longrunning_OperationInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(OperationInfo.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = OperationInfo.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3317clear() {
            super.clear();
            this.responseType_ = "";
            this.metadataType_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return OperationsProto.internal_static_google_longrunning_OperationInfo_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public OperationInfo m3330getDefaultInstanceForType() {
            return OperationInfo.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public OperationInfo m3311build() throws UninitializedMessageException {
            OperationInfo operationInfoM3313buildPartial = m3313buildPartial();
            if (operationInfoM3313buildPartial.isInitialized()) {
                return operationInfoM3313buildPartial;
            }
            throw newUninitializedMessageException(operationInfoM3313buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public OperationInfo m3313buildPartial() {
            OperationInfo operationInfo = new OperationInfo(this);
            operationInfo.responseType_ = this.responseType_;
            operationInfo.metadataType_ = this.metadataType_;
            onBuilt();
            return operationInfo;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3328clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3341setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3319clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3322clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3343setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3309addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3335mergeFrom(Message message) {
            if (message instanceof OperationInfo) {
                return mergeFrom((OperationInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(OperationInfo operationInfo) {
            if (operationInfo == OperationInfo.getDefaultInstance()) {
                return this;
            }
            if (!operationInfo.getResponseType().isEmpty()) {
                this.responseType_ = operationInfo.responseType_;
                onChanged();
            }
            if (!operationInfo.getMetadataType().isEmpty()) {
                this.metadataType_ = operationInfo.metadataType_;
                onChanged();
            }
            m3339mergeUnknownFields(operationInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.longrunning.OperationInfo.Builder m3336mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.longrunning.OperationInfo.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.longrunning.OperationInfo r3 = (com.google.longrunning.OperationInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.longrunning.OperationInfo r4 = (com.google.longrunning.OperationInfo) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.longrunning.OperationInfo.Builder.m3336mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.longrunning.OperationInfo$Builder");
        }

        @Override // com.google.longrunning.OperationInfoOrBuilder
        public String getResponseType() {
            Object obj = this.responseType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.responseType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setResponseType(String str) {
            str.getClass();
            this.responseType_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.longrunning.OperationInfoOrBuilder
        public ByteString getResponseTypeBytes() {
            Object obj = this.responseType_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.responseType_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setResponseTypeBytes(ByteString byteString) {
            byteString.getClass();
            OperationInfo.checkByteStringIsUtf8(byteString);
            this.responseType_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearResponseType() {
            this.responseType_ = OperationInfo.getDefaultInstance().getResponseType();
            onChanged();
            return this;
        }

        @Override // com.google.longrunning.OperationInfoOrBuilder
        public String getMetadataType() {
            Object obj = this.metadataType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.metadataType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setMetadataType(String str) {
            str.getClass();
            this.metadataType_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.longrunning.OperationInfoOrBuilder
        public ByteString getMetadataTypeBytes() {
            Object obj = this.metadataType_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.metadataType_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setMetadataTypeBytes(ByteString byteString) {
            byteString.getClass();
            OperationInfo.checkByteStringIsUtf8(byteString);
            this.metadataType_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearMetadataType() {
            this.metadataType_ = OperationInfo.getDefaultInstance().getMetadataType();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3345setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3339mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
