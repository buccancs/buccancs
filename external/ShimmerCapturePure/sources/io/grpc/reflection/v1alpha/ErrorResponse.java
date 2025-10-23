package io.grpc.reflection.v1alpha;

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

/* loaded from: classes3.dex */
public final class ErrorResponse extends GeneratedMessageV3 implements ErrorResponseOrBuilder {
    public static final int ERROR_CODE_FIELD_NUMBER = 1;
    public static final int ERROR_MESSAGE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final ErrorResponse DEFAULT_INSTANCE = new ErrorResponse();
    private static final Parser<ErrorResponse> PARSER = new AbstractParser<ErrorResponse>() { // from class: io.grpc.reflection.v1alpha.ErrorResponse.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ErrorResponse m9627parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ErrorResponse(codedInputStream, extensionRegistryLite);
        }
    };
    private int errorCode_;
    private volatile Object errorMessage_;
    private byte memoizedIsInitialized;

    private ErrorResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ErrorResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.errorMessage_ = "";
    }

    private ErrorResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag == 8) {
                                this.errorCode_ = codedInputStream.readInt32();
                            } else if (tag == 18) {
                                this.errorMessage_ = codedInputStream.readStringRequireUtf8();
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

    public static ErrorResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ErrorResponse> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ErrorResponse_descriptor;
    }

    public static ErrorResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ErrorResponse) PARSER.parseFrom(byteBuffer);
    }

    public static ErrorResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ErrorResponse) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ErrorResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ErrorResponse) PARSER.parseFrom(byteString);
    }

    public static ErrorResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ErrorResponse) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ErrorResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ErrorResponse) PARSER.parseFrom(bArr);
    }

    public static ErrorResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ErrorResponse) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ErrorResponse parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ErrorResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ErrorResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ErrorResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ErrorResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ErrorResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m9625toBuilder();
    }

    public static Builder newBuilder(ErrorResponse errorResponse) {
        return DEFAULT_INSTANCE.m9625toBuilder().mergeFrom(errorResponse);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ErrorResponse m9620getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.reflection.v1alpha.ErrorResponseOrBuilder
    public int getErrorCode() {
        return this.errorCode_;
    }

    public Parser<ErrorResponse> getParserForType() {
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

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ErrorResponse();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ErrorResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ErrorResponse.class, Builder.class);
    }

    @Override // io.grpc.reflection.v1alpha.ErrorResponseOrBuilder
    public String getErrorMessage() {
        Object obj = this.errorMessage_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.errorMessage_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.reflection.v1alpha.ErrorResponseOrBuilder
    public ByteString getErrorMessageBytes() {
        Object obj = this.errorMessage_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.errorMessage_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i = this.errorCode_;
        if (i != 0) {
            codedOutputStream.writeInt32(1, i);
        }
        if (!getErrorMessageBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.errorMessage_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = this.errorCode_;
        int iComputeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) : 0;
        if (!getErrorMessageBytes().isEmpty()) {
            iComputeInt32Size += GeneratedMessageV3.computeStringSize(2, this.errorMessage_);
        }
        int serializedSize = iComputeInt32Size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ErrorResponse)) {
            return super.equals(obj);
        }
        ErrorResponse errorResponse = (ErrorResponse) obj;
        return getErrorCode() == errorResponse.getErrorCode() && getErrorMessage().equals(errorResponse.getErrorMessage()) && this.unknownFields.equals(errorResponse.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getErrorCode()) * 37) + 2) * 53) + getErrorMessage().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9622newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9625toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ErrorResponseOrBuilder {
        private int errorCode_;
        private Object errorMessage_;

        private Builder() {
            this.errorMessage_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.errorMessage_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ErrorResponse_descriptor;
        }

        @Override // io.grpc.reflection.v1alpha.ErrorResponseOrBuilder
        public int getErrorCode() {
            return this.errorCode_;
        }

        public Builder setErrorCode(int i) {
            this.errorCode_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ErrorResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ErrorResponse.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ErrorResponse.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9636clear() {
            super.clear();
            this.errorCode_ = 0;
            this.errorMessage_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ErrorResponse_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ErrorResponse m9649getDefaultInstanceForType() {
            return ErrorResponse.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ErrorResponse m9630build() throws UninitializedMessageException {
            ErrorResponse errorResponseM9632buildPartial = m9632buildPartial();
            if (errorResponseM9632buildPartial.isInitialized()) {
                return errorResponseM9632buildPartial;
            }
            throw newUninitializedMessageException(errorResponseM9632buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ErrorResponse m9632buildPartial() {
            ErrorResponse errorResponse = new ErrorResponse(this);
            errorResponse.errorCode_ = this.errorCode_;
            errorResponse.errorMessage_ = this.errorMessage_;
            onBuilt();
            return errorResponse;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9648clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9660setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9638clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9641clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9662setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9628addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9653mergeFrom(Message message) {
            if (message instanceof ErrorResponse) {
                return mergeFrom((ErrorResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ErrorResponse errorResponse) {
            if (errorResponse == ErrorResponse.getDefaultInstance()) {
                return this;
            }
            if (errorResponse.getErrorCode() != 0) {
                setErrorCode(errorResponse.getErrorCode());
            }
            if (!errorResponse.getErrorMessage().isEmpty()) {
                this.errorMessage_ = errorResponse.errorMessage_;
                onChanged();
            }
            m9658mergeUnknownFields(errorResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.reflection.v1alpha.ErrorResponse.Builder m9654mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.reflection.v1alpha.ErrorResponse.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.reflection.v1alpha.ErrorResponse r3 = (io.grpc.reflection.v1alpha.ErrorResponse) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.reflection.v1alpha.ErrorResponse r4 = (io.grpc.reflection.v1alpha.ErrorResponse) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.reflection.v1alpha.ErrorResponse.Builder.m9654mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.reflection.v1alpha.ErrorResponse$Builder");
        }

        public Builder clearErrorCode() {
            this.errorCode_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.reflection.v1alpha.ErrorResponseOrBuilder
        public String getErrorMessage() {
            Object obj = this.errorMessage_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.errorMessage_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setErrorMessage(String str) {
            str.getClass();
            this.errorMessage_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.reflection.v1alpha.ErrorResponseOrBuilder
        public ByteString getErrorMessageBytes() {
            Object obj = this.errorMessage_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.errorMessage_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setErrorMessageBytes(ByteString byteString) {
            byteString.getClass();
            ErrorResponse.checkByteStringIsUtf8(byteString);
            this.errorMessage_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearErrorMessage() {
            this.errorMessage_ = ErrorResponse.getDefaultInstance().getErrorMessage();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9664setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9658mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
