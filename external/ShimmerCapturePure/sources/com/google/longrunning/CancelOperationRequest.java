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
public final class CancelOperationRequest extends GeneratedMessageV3 implements CancelOperationRequestOrBuilder {
    public static final int NAME_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final CancelOperationRequest DEFAULT_INSTANCE = new CancelOperationRequest();
    private static final Parser<CancelOperationRequest> PARSER = new AbstractParser<CancelOperationRequest>() { // from class: com.google.longrunning.CancelOperationRequest.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public CancelOperationRequest m3031parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CancelOperationRequest(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private volatile Object name_;

    private CancelOperationRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private CancelOperationRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    private CancelOperationRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static CancelOperationRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CancelOperationRequest> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return OperationsProto.internal_static_google_longrunning_CancelOperationRequest_descriptor;
    }

    public static CancelOperationRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (CancelOperationRequest) PARSER.parseFrom(byteBuffer);
    }

    public static CancelOperationRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CancelOperationRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static CancelOperationRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CancelOperationRequest) PARSER.parseFrom(byteString);
    }

    public static CancelOperationRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CancelOperationRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static CancelOperationRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CancelOperationRequest) PARSER.parseFrom(bArr);
    }

    public static CancelOperationRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CancelOperationRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static CancelOperationRequest parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static CancelOperationRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CancelOperationRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static CancelOperationRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CancelOperationRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static CancelOperationRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3030toBuilder();
    }

    public static Builder newBuilder(CancelOperationRequest cancelOperationRequest) {
        return DEFAULT_INSTANCE.m3030toBuilder().mergeFrom(cancelOperationRequest);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public CancelOperationRequest m3025getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<CancelOperationRequest> getParserForType() {
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
        return OperationsProto.internal_static_google_longrunning_CancelOperationRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(CancelOperationRequest.class, Builder.class);
    }

    @Override // com.google.longrunning.CancelOperationRequestOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.longrunning.CancelOperationRequestOrBuilder
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = (!getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeStringSize;
        return iComputeStringSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CancelOperationRequest)) {
            return super.equals(obj);
        }
        CancelOperationRequest cancelOperationRequest = (CancelOperationRequest) obj;
        return getName().equals(cancelOperationRequest.getName()) && this.unknownFields.equals(cancelOperationRequest.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3028newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3030toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m3027newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CancelOperationRequestOrBuilder {
        private Object name_;

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
            return OperationsProto.internal_static_google_longrunning_CancelOperationRequest_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return OperationsProto.internal_static_google_longrunning_CancelOperationRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(CancelOperationRequest.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = CancelOperationRequest.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3041clear() {
            super.clear();
            this.name_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return OperationsProto.internal_static_google_longrunning_CancelOperationRequest_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CancelOperationRequest m3054getDefaultInstanceForType() {
            return CancelOperationRequest.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CancelOperationRequest m3035build() throws UninitializedMessageException {
            CancelOperationRequest cancelOperationRequestM3037buildPartial = m3037buildPartial();
            if (cancelOperationRequestM3037buildPartial.isInitialized()) {
                return cancelOperationRequestM3037buildPartial;
            }
            throw newUninitializedMessageException(cancelOperationRequestM3037buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CancelOperationRequest m3037buildPartial() {
            CancelOperationRequest cancelOperationRequest = new CancelOperationRequest(this);
            cancelOperationRequest.name_ = this.name_;
            onBuilt();
            return cancelOperationRequest;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3052clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3065setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3043clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3046clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3067setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3033addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3059mergeFrom(Message message) {
            if (message instanceof CancelOperationRequest) {
                return mergeFrom((CancelOperationRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(CancelOperationRequest cancelOperationRequest) {
            if (cancelOperationRequest == CancelOperationRequest.getDefaultInstance()) {
                return this;
            }
            if (!cancelOperationRequest.getName().isEmpty()) {
                this.name_ = cancelOperationRequest.name_;
                onChanged();
            }
            m3063mergeUnknownFields(cancelOperationRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.longrunning.CancelOperationRequest.Builder m3060mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.longrunning.CancelOperationRequest.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.longrunning.CancelOperationRequest r3 = (com.google.longrunning.CancelOperationRequest) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.longrunning.CancelOperationRequest r4 = (com.google.longrunning.CancelOperationRequest) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.longrunning.CancelOperationRequest.Builder.m3060mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.longrunning.CancelOperationRequest$Builder");
        }

        @Override // com.google.longrunning.CancelOperationRequestOrBuilder
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

        @Override // com.google.longrunning.CancelOperationRequestOrBuilder
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
            CancelOperationRequest.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = CancelOperationRequest.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3069setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3063mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
