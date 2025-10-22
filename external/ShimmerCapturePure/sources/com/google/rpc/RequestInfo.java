package com.google.rpc;

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
public final class RequestInfo extends GeneratedMessageV3 implements RequestInfoOrBuilder {
    public static final int REQUEST_ID_FIELD_NUMBER = 1;
    public static final int SERVING_DATA_FIELD_NUMBER = 2;
    private static final RequestInfo DEFAULT_INSTANCE = new RequestInfo();
    private static final Parser<RequestInfo> PARSER = new AbstractParser<RequestInfo>() { // from class: com.google.rpc.RequestInfo.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public RequestInfo m3861parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RequestInfo(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private volatile Object requestId_;
    private volatile Object servingData_;

    private RequestInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private RequestInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.requestId_ = "";
        this.servingData_ = "";
    }

    private RequestInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.requestId_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.servingData_ = codedInputStream.readStringRequireUtf8();
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

    public static RequestInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RequestInfo> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_RequestInfo_descriptor;
    }

    public static RequestInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RequestInfo) PARSER.parseFrom(byteBuffer);
    }

    public static RequestInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RequestInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RequestInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RequestInfo) PARSER.parseFrom(byteString);
    }

    public static RequestInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RequestInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RequestInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RequestInfo) PARSER.parseFrom(bArr);
    }

    public static RequestInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RequestInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RequestInfo parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RequestInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RequestInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RequestInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RequestInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RequestInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3859toBuilder();
    }

    public static Builder newBuilder(RequestInfo requestInfo) {
        return DEFAULT_INSTANCE.m3859toBuilder().mergeFrom(requestInfo);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public RequestInfo m3854getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<RequestInfo> getParserForType() {
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
        return ErrorDetailsProto.internal_static_google_rpc_RequestInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RequestInfo.class, Builder.class);
    }

    @Override // com.google.rpc.RequestInfoOrBuilder
    public String getRequestId() {
        Object obj = this.requestId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.requestId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.RequestInfoOrBuilder
    public ByteString getRequestIdBytes() {
        Object obj = this.requestId_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.requestId_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.rpc.RequestInfoOrBuilder
    public String getServingData() {
        Object obj = this.servingData_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.servingData_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.RequestInfoOrBuilder
    public ByteString getServingDataBytes() {
        Object obj = this.servingData_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.servingData_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getRequestIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.requestId_);
        }
        if (!getServingDataBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.servingData_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getRequestIdBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.requestId_) : 0;
        if (!getServingDataBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.servingData_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RequestInfo)) {
            return super.equals(obj);
        }
        RequestInfo requestInfo = (RequestInfo) obj;
        return getRequestId().equals(requestInfo.getRequestId()) && getServingData().equals(requestInfo.getServingData()) && this.unknownFields.equals(requestInfo.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getRequestId().hashCode()) * 37) + 2) * 53) + getServingData().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3856newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3859toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RequestInfoOrBuilder {
        private Object requestId_;
        private Object servingData_;

        private Builder() {
            this.requestId_ = "";
            this.servingData_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.requestId_ = "";
            this.servingData_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_RequestInfo_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_RequestInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RequestInfo.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RequestInfo.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3870clear() {
            super.clear();
            this.requestId_ = "";
            this.servingData_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_RequestInfo_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RequestInfo m3883getDefaultInstanceForType() {
            return RequestInfo.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RequestInfo m3864build() throws UninitializedMessageException {
            RequestInfo requestInfoM3866buildPartial = m3866buildPartial();
            if (requestInfoM3866buildPartial.isInitialized()) {
                return requestInfoM3866buildPartial;
            }
            throw newUninitializedMessageException(requestInfoM3866buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RequestInfo m3866buildPartial() {
            RequestInfo requestInfo = new RequestInfo(this);
            requestInfo.requestId_ = this.requestId_;
            requestInfo.servingData_ = this.servingData_;
            onBuilt();
            return requestInfo;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3882clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3894setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3872clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3875clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3896setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3862addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3887mergeFrom(Message message) {
            if (message instanceof RequestInfo) {
                return mergeFrom((RequestInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RequestInfo requestInfo) {
            if (requestInfo == RequestInfo.getDefaultInstance()) {
                return this;
            }
            if (!requestInfo.getRequestId().isEmpty()) {
                this.requestId_ = requestInfo.requestId_;
                onChanged();
            }
            if (!requestInfo.getServingData().isEmpty()) {
                this.servingData_ = requestInfo.servingData_;
                onChanged();
            }
            m3892mergeUnknownFields(requestInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.rpc.RequestInfo.Builder m3888mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.RequestInfo.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.rpc.RequestInfo r3 = (com.google.rpc.RequestInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.rpc.RequestInfo r4 = (com.google.rpc.RequestInfo) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.RequestInfo.Builder.m3888mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.RequestInfo$Builder");
        }

        @Override // com.google.rpc.RequestInfoOrBuilder
        public String getRequestId() {
            Object obj = this.requestId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.requestId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setRequestId(String str) {
            str.getClass();
            this.requestId_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.rpc.RequestInfoOrBuilder
        public ByteString getRequestIdBytes() {
            Object obj = this.requestId_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.requestId_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRequestIdBytes(ByteString byteString) {
            byteString.getClass();
            RequestInfo.checkByteStringIsUtf8(byteString);
            this.requestId_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRequestId() {
            this.requestId_ = RequestInfo.getDefaultInstance().getRequestId();
            onChanged();
            return this;
        }

        @Override // com.google.rpc.RequestInfoOrBuilder
        public String getServingData() {
            Object obj = this.servingData_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.servingData_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setServingData(String str) {
            str.getClass();
            this.servingData_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.rpc.RequestInfoOrBuilder
        public ByteString getServingDataBytes() {
            Object obj = this.servingData_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.servingData_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setServingDataBytes(ByteString byteString) {
            byteString.getClass();
            RequestInfo.checkByteStringIsUtf8(byteString);
            this.servingData_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearServingData() {
            this.servingData_ = RequestInfo.getDefaultInstance().getServingData();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3898setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3892mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
