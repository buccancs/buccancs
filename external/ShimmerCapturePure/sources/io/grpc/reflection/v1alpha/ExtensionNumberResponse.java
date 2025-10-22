package io.grpc.reflection.v1alpha;

import com.google.protobuf.AbstractMessageLite;
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
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class ExtensionNumberResponse extends GeneratedMessageV3 implements ExtensionNumberResponseOrBuilder {
    public static final int BASE_TYPE_NAME_FIELD_NUMBER = 1;
    public static final int EXTENSION_NUMBER_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final ExtensionNumberResponse DEFAULT_INSTANCE = new ExtensionNumberResponse();
    private static final Parser<ExtensionNumberResponse> PARSER = new AbstractParser<ExtensionNumberResponse>() { // from class: io.grpc.reflection.v1alpha.ExtensionNumberResponse.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ExtensionNumberResponse m9673parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ExtensionNumberResponse(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object baseTypeName_;
    private int extensionNumberMemoizedSerializedSize;
    private Internal.IntList extensionNumber_;
    private byte memoizedIsInitialized;

    private ExtensionNumberResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.extensionNumberMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
    }

    private ExtensionNumberResponse() {
        this.extensionNumberMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.baseTypeName_ = "";
        this.extensionNumber_ = emptyIntList();
    }

    private ExtensionNumberResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            this.baseTypeName_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 16) {
                            if (!(z2 & true)) {
                                this.extensionNumber_ = newIntList();
                                z2 |= true;
                            }
                            this.extensionNumber_.addInt(codedInputStream.readInt32());
                        } else if (tag == 18) {
                            int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                                this.extensionNumber_ = newIntList();
                                z2 |= true;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.extensionNumber_.addInt(codedInputStream.readInt32());
                            }
                            codedInputStream.popLimit(iPushLimit);
                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                        }
                    }
                    z = true;
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if (z2 & true) {
                    this.extensionNumber_.makeImmutable();
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ExtensionNumberResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ExtensionNumberResponse> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ExtensionNumberResponse_descriptor;
    }

    public static ExtensionNumberResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ExtensionNumberResponse) PARSER.parseFrom(byteBuffer);
    }

    public static ExtensionNumberResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExtensionNumberResponse) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ExtensionNumberResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ExtensionNumberResponse) PARSER.parseFrom(byteString);
    }

    public static ExtensionNumberResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExtensionNumberResponse) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ExtensionNumberResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ExtensionNumberResponse) PARSER.parseFrom(bArr);
    }

    public static ExtensionNumberResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExtensionNumberResponse) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ExtensionNumberResponse parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ExtensionNumberResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ExtensionNumberResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ExtensionNumberResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ExtensionNumberResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ExtensionNumberResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m9671toBuilder();
    }

    public static Builder newBuilder(ExtensionNumberResponse extensionNumberResponse) {
        return DEFAULT_INSTANCE.m9671toBuilder().mergeFrom(extensionNumberResponse);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ExtensionNumberResponse m9666getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.reflection.v1alpha.ExtensionNumberResponseOrBuilder
    public List<Integer> getExtensionNumberList() {
        return this.extensionNumber_;
    }

    public Parser<ExtensionNumberResponse> getParserForType() {
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
        return new ExtensionNumberResponse();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ExtensionNumberResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ExtensionNumberResponse.class, Builder.class);
    }

    @Override // io.grpc.reflection.v1alpha.ExtensionNumberResponseOrBuilder
    public String getBaseTypeName() {
        Object obj = this.baseTypeName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.baseTypeName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.reflection.v1alpha.ExtensionNumberResponseOrBuilder
    public ByteString getBaseTypeNameBytes() {
        Object obj = this.baseTypeName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.baseTypeName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.reflection.v1alpha.ExtensionNumberResponseOrBuilder
    public int getExtensionNumberCount() {
        return this.extensionNumber_.size();
    }

    @Override // io.grpc.reflection.v1alpha.ExtensionNumberResponseOrBuilder
    public int getExtensionNumber(int i) {
        return this.extensionNumber_.getInt(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        if (!getBaseTypeNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.baseTypeName_);
        }
        if (getExtensionNumberList().size() > 0) {
            codedOutputStream.writeUInt32NoTag(18);
            codedOutputStream.writeUInt32NoTag(this.extensionNumberMemoizedSerializedSize);
        }
        for (int i = 0; i < this.extensionNumber_.size(); i++) {
            codedOutputStream.writeInt32NoTag(this.extensionNumber_.getInt(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getBaseTypeNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.baseTypeName_) : 0;
        int iComputeInt32SizeNoTag = 0;
        for (int i2 = 0; i2 < this.extensionNumber_.size(); i2++) {
            iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(this.extensionNumber_.getInt(i2));
        }
        int iComputeInt32SizeNoTag2 = iComputeStringSize + iComputeInt32SizeNoTag;
        if (!getExtensionNumberList().isEmpty()) {
            iComputeInt32SizeNoTag2 = iComputeInt32SizeNoTag2 + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag);
        }
        this.extensionNumberMemoizedSerializedSize = iComputeInt32SizeNoTag;
        int serializedSize = iComputeInt32SizeNoTag2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExtensionNumberResponse)) {
            return super.equals(obj);
        }
        ExtensionNumberResponse extensionNumberResponse = (ExtensionNumberResponse) obj;
        return getBaseTypeName().equals(extensionNumberResponse.getBaseTypeName()) && getExtensionNumberList().equals(extensionNumberResponse.getExtensionNumberList()) && this.unknownFields.equals(extensionNumberResponse.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getBaseTypeName().hashCode();
        if (getExtensionNumberCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getExtensionNumberList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9668newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9671toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExtensionNumberResponseOrBuilder {
        private Object baseTypeName_;
        private int bitField0_;
        private Internal.IntList extensionNumber_;

        private Builder() {
            this.baseTypeName_ = "";
            this.extensionNumber_ = ExtensionNumberResponse.emptyIntList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.baseTypeName_ = "";
            this.extensionNumber_ = ExtensionNumberResponse.emptyIntList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ExtensionNumberResponse_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ExtensionNumberResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ExtensionNumberResponse.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ExtensionNumberResponse.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9682clear() {
            super.clear();
            this.baseTypeName_ = "";
            this.extensionNumber_ = ExtensionNumberResponse.emptyIntList();
            this.bitField0_ &= -2;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ExtensionNumberResponse_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ExtensionNumberResponse m9695getDefaultInstanceForType() {
            return ExtensionNumberResponse.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ExtensionNumberResponse m9676build() throws UninitializedMessageException {
            ExtensionNumberResponse extensionNumberResponseM9678buildPartial = m9678buildPartial();
            if (extensionNumberResponseM9678buildPartial.isInitialized()) {
                return extensionNumberResponseM9678buildPartial;
            }
            throw newUninitializedMessageException(extensionNumberResponseM9678buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ExtensionNumberResponse m9678buildPartial() {
            ExtensionNumberResponse extensionNumberResponse = new ExtensionNumberResponse(this);
            extensionNumberResponse.baseTypeName_ = this.baseTypeName_;
            if ((this.bitField0_ & 1) != 0) {
                this.extensionNumber_.makeImmutable();
                this.bitField0_ &= -2;
            }
            extensionNumberResponse.extensionNumber_ = this.extensionNumber_;
            onBuilt();
            return extensionNumberResponse;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9694clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9706setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9684clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9687clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9708setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9674addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9699mergeFrom(Message message) {
            if (message instanceof ExtensionNumberResponse) {
                return mergeFrom((ExtensionNumberResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ExtensionNumberResponse extensionNumberResponse) {
            if (extensionNumberResponse == ExtensionNumberResponse.getDefaultInstance()) {
                return this;
            }
            if (!extensionNumberResponse.getBaseTypeName().isEmpty()) {
                this.baseTypeName_ = extensionNumberResponse.baseTypeName_;
                onChanged();
            }
            if (!extensionNumberResponse.extensionNumber_.isEmpty()) {
                if (this.extensionNumber_.isEmpty()) {
                    this.extensionNumber_ = extensionNumberResponse.extensionNumber_;
                    this.bitField0_ &= -2;
                } else {
                    ensureExtensionNumberIsMutable();
                    this.extensionNumber_.addAll(extensionNumberResponse.extensionNumber_);
                }
                onChanged();
            }
            m9704mergeUnknownFields(extensionNumberResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.reflection.v1alpha.ExtensionNumberResponse.Builder m9700mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.reflection.v1alpha.ExtensionNumberResponse.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.reflection.v1alpha.ExtensionNumberResponse r3 = (io.grpc.reflection.v1alpha.ExtensionNumberResponse) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.reflection.v1alpha.ExtensionNumberResponse r4 = (io.grpc.reflection.v1alpha.ExtensionNumberResponse) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.reflection.v1alpha.ExtensionNumberResponse.Builder.m9700mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.reflection.v1alpha.ExtensionNumberResponse$Builder");
        }

        @Override // io.grpc.reflection.v1alpha.ExtensionNumberResponseOrBuilder
        public String getBaseTypeName() {
            Object obj = this.baseTypeName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.baseTypeName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setBaseTypeName(String str) {
            str.getClass();
            this.baseTypeName_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.reflection.v1alpha.ExtensionNumberResponseOrBuilder
        public ByteString getBaseTypeNameBytes() {
            Object obj = this.baseTypeName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.baseTypeName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setBaseTypeNameBytes(ByteString byteString) {
            byteString.getClass();
            ExtensionNumberResponse.checkByteStringIsUtf8(byteString);
            this.baseTypeName_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearBaseTypeName() {
            this.baseTypeName_ = ExtensionNumberResponse.getDefaultInstance().getBaseTypeName();
            onChanged();
            return this;
        }

        private void ensureExtensionNumberIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.extensionNumber_ = ExtensionNumberResponse.mutableCopy(this.extensionNumber_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.reflection.v1alpha.ExtensionNumberResponseOrBuilder
        public List<Integer> getExtensionNumberList() {
            return (this.bitField0_ & 1) != 0 ? Collections.unmodifiableList(this.extensionNumber_) : this.extensionNumber_;
        }

        @Override // io.grpc.reflection.v1alpha.ExtensionNumberResponseOrBuilder
        public int getExtensionNumberCount() {
            return this.extensionNumber_.size();
        }

        @Override // io.grpc.reflection.v1alpha.ExtensionNumberResponseOrBuilder
        public int getExtensionNumber(int i) {
            return this.extensionNumber_.getInt(i);
        }

        public Builder setExtensionNumber(int i, int i2) {
            ensureExtensionNumberIsMutable();
            this.extensionNumber_.setInt(i, i2);
            onChanged();
            return this;
        }

        public Builder addExtensionNumber(int i) {
            ensureExtensionNumberIsMutable();
            this.extensionNumber_.addInt(i);
            onChanged();
            return this;
        }

        public Builder addAllExtensionNumber(Iterable<? extends Integer> iterable) {
            ensureExtensionNumberIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.extensionNumber_);
            onChanged();
            return this;
        }

        public Builder clearExtensionNumber() {
            this.extensionNumber_ = ExtensionNumberResponse.emptyIntList();
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9710setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9704mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
