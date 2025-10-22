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
public final class ExtensionRequest extends GeneratedMessageV3 implements ExtensionRequestOrBuilder {
    public static final int CONTAINING_TYPE_FIELD_NUMBER = 1;
    public static final int EXTENSION_NUMBER_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final ExtensionRequest DEFAULT_INSTANCE = new ExtensionRequest();
    private static final Parser<ExtensionRequest> PARSER = new AbstractParser<ExtensionRequest>() { // from class: io.grpc.reflection.v1alpha.ExtensionRequest.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ExtensionRequest m9719parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ExtensionRequest(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object containingType_;
    private int extensionNumber_;
    private byte memoizedIsInitialized;

    private ExtensionRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ExtensionRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.containingType_ = "";
    }

    private ExtensionRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.containingType_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 16) {
                                this.extensionNumber_ = codedInputStream.readInt32();
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

    public static ExtensionRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ExtensionRequest> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ExtensionRequest_descriptor;
    }

    public static ExtensionRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ExtensionRequest) PARSER.parseFrom(byteBuffer);
    }

    public static ExtensionRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExtensionRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ExtensionRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ExtensionRequest) PARSER.parseFrom(byteString);
    }

    public static ExtensionRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExtensionRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ExtensionRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ExtensionRequest) PARSER.parseFrom(bArr);
    }

    public static ExtensionRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExtensionRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ExtensionRequest parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ExtensionRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ExtensionRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ExtensionRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ExtensionRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ExtensionRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m9717toBuilder();
    }

    public static Builder newBuilder(ExtensionRequest extensionRequest) {
        return DEFAULT_INSTANCE.m9717toBuilder().mergeFrom(extensionRequest);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ExtensionRequest m9712getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.reflection.v1alpha.ExtensionRequestOrBuilder
    public int getExtensionNumber() {
        return this.extensionNumber_;
    }

    public Parser<ExtensionRequest> getParserForType() {
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
        return new ExtensionRequest();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ExtensionRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ExtensionRequest.class, Builder.class);
    }

    @Override // io.grpc.reflection.v1alpha.ExtensionRequestOrBuilder
    public String getContainingType() {
        Object obj = this.containingType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.containingType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.reflection.v1alpha.ExtensionRequestOrBuilder
    public ByteString getContainingTypeBytes() {
        Object obj = this.containingType_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.containingType_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getContainingTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.containingType_);
        }
        int i = this.extensionNumber_;
        if (i != 0) {
            codedOutputStream.writeInt32(2, i);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getContainingTypeBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.containingType_) : 0;
        int i2 = this.extensionNumber_;
        if (i2 != 0) {
            iComputeStringSize += CodedOutputStream.computeInt32Size(2, i2);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExtensionRequest)) {
            return super.equals(obj);
        }
        ExtensionRequest extensionRequest = (ExtensionRequest) obj;
        return getContainingType().equals(extensionRequest.getContainingType()) && getExtensionNumber() == extensionRequest.getExtensionNumber() && this.unknownFields.equals(extensionRequest.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getContainingType().hashCode()) * 37) + 2) * 53) + getExtensionNumber()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9714newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9717toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExtensionRequestOrBuilder {
        private Object containingType_;
        private int extensionNumber_;

        private Builder() {
            this.containingType_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.containingType_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ExtensionRequest_descriptor;
        }

        @Override // io.grpc.reflection.v1alpha.ExtensionRequestOrBuilder
        public int getExtensionNumber() {
            return this.extensionNumber_;
        }

        public Builder setExtensionNumber(int i) {
            this.extensionNumber_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ExtensionRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ExtensionRequest.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ExtensionRequest.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9728clear() {
            super.clear();
            this.containingType_ = "";
            this.extensionNumber_ = 0;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ExtensionRequest_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ExtensionRequest m9741getDefaultInstanceForType() {
            return ExtensionRequest.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ExtensionRequest m9722build() throws UninitializedMessageException {
            ExtensionRequest extensionRequestM9724buildPartial = m9724buildPartial();
            if (extensionRequestM9724buildPartial.isInitialized()) {
                return extensionRequestM9724buildPartial;
            }
            throw newUninitializedMessageException(extensionRequestM9724buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ExtensionRequest m9724buildPartial() {
            ExtensionRequest extensionRequest = new ExtensionRequest(this);
            extensionRequest.containingType_ = this.containingType_;
            extensionRequest.extensionNumber_ = this.extensionNumber_;
            onBuilt();
            return extensionRequest;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9740clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9752setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9730clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9733clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9754setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9720addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9745mergeFrom(Message message) {
            if (message instanceof ExtensionRequest) {
                return mergeFrom((ExtensionRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ExtensionRequest extensionRequest) {
            if (extensionRequest == ExtensionRequest.getDefaultInstance()) {
                return this;
            }
            if (!extensionRequest.getContainingType().isEmpty()) {
                this.containingType_ = extensionRequest.containingType_;
                onChanged();
            }
            if (extensionRequest.getExtensionNumber() != 0) {
                setExtensionNumber(extensionRequest.getExtensionNumber());
            }
            m9750mergeUnknownFields(extensionRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.reflection.v1alpha.ExtensionRequest.Builder m9746mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.reflection.v1alpha.ExtensionRequest.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.reflection.v1alpha.ExtensionRequest r3 = (io.grpc.reflection.v1alpha.ExtensionRequest) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.reflection.v1alpha.ExtensionRequest r4 = (io.grpc.reflection.v1alpha.ExtensionRequest) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.reflection.v1alpha.ExtensionRequest.Builder.m9746mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.reflection.v1alpha.ExtensionRequest$Builder");
        }

        @Override // io.grpc.reflection.v1alpha.ExtensionRequestOrBuilder
        public String getContainingType() {
            Object obj = this.containingType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.containingType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setContainingType(String str) {
            str.getClass();
            this.containingType_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.reflection.v1alpha.ExtensionRequestOrBuilder
        public ByteString getContainingTypeBytes() {
            Object obj = this.containingType_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.containingType_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setContainingTypeBytes(ByteString byteString) {
            byteString.getClass();
            ExtensionRequest.checkByteStringIsUtf8(byteString);
            this.containingType_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearContainingType() {
            this.containingType_ = ExtensionRequest.getDefaultInstance().getContainingType();
            onChanged();
            return this;
        }

        public Builder clearExtensionNumber() {
            this.extensionNumber_ = 0;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9756setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9750mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
