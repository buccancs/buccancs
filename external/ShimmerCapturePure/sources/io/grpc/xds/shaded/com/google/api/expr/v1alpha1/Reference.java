package io.grpc.xds.shaded.com.google.api.expr.v1alpha1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class Reference extends GeneratedMessageV3 implements ReferenceOrBuilder {
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int OVERLOAD_ID_FIELD_NUMBER = 3;
    public static final int VALUE_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private static final Reference DEFAULT_INSTANCE = new Reference();
    private static final Parser<Reference> PARSER = new AbstractParser<Reference>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Reference.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Reference m11148parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Reference(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private LazyStringList overloadId_;
    private Constant value_;

    private Reference(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Reference() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.overloadId_ = LazyStringArrayList.EMPTY;
    }

    private Reference(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 26) {
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if (!(z2 & true)) {
                                this.overloadId_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.overloadId_.add(stringRequireUtf8);
                        } else if (tag == 34) {
                            Constant constant = this.value_;
                            Constant.Builder builderM10500toBuilder = constant != null ? constant.m10500toBuilder() : null;
                            Constant constant2 = (Constant) codedInputStream.readMessage(Constant.parser(), extensionRegistryLite);
                            this.value_ = constant2;
                            if (builderM10500toBuilder != null) {
                                builderM10500toBuilder.mergeFrom(constant2);
                                this.value_ = builderM10500toBuilder.m10507buildPartial();
                            }
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
                if (z2 & true) {
                    this.overloadId_ = this.overloadId_.getUnmodifiableView();
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static Reference getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Reference> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DeclProto.internal_static_google_api_expr_v1alpha1_Reference_descriptor;
    }

    public static Reference parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Reference) PARSER.parseFrom(byteBuffer);
    }

    public static Reference parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Reference) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Reference parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Reference) PARSER.parseFrom(byteString);
    }

    public static Reference parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Reference) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Reference parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Reference) PARSER.parseFrom(bArr);
    }

    public static Reference parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Reference) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Reference parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Reference parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Reference parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Reference parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Reference parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Reference parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m11146toBuilder();
    }

    public static Builder newBuilder(Reference reference) {
        return DEFAULT_INSTANCE.m11146toBuilder().mergeFrom(reference);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Reference m11140getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
    /* renamed from: getOverloadIdList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo11142getOverloadIdList() {
        return this.overloadId_;
    }

    public Parser<Reference> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
    public boolean hasValue() {
        return this.value_ != null;
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
        return new Reference();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DeclProto.internal_static_google_api_expr_v1alpha1_Reference_fieldAccessorTable.ensureFieldAccessorsInitialized(Reference.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
    public int getOverloadIdCount() {
        return this.overloadId_.size();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
    public String getOverloadId(int i) {
        return (String) this.overloadId_.get(i);
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
    public ByteString getOverloadIdBytes(int i) {
        return this.overloadId_.getByteString(i);
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
    public Constant getValue() {
        Constant constant = this.value_;
        return constant == null ? Constant.getDefaultInstance() : constant;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
    public ConstantOrBuilder getValueOrBuilder() {
        return getValue();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        for (int i = 0; i < this.overloadId_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.overloadId_.getRaw(i));
        }
        if (this.value_ != null) {
            codedOutputStream.writeMessage(4, getValue());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.overloadId_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.overloadId_.getRaw(i2));
        }
        int size = iComputeStringSize + iComputeStringSizeNoTag + mo11142getOverloadIdList().size();
        if (this.value_ != null) {
            size += CodedOutputStream.computeMessageSize(4, getValue());
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Reference)) {
            return super.equals(obj);
        }
        Reference reference = (Reference) obj;
        if (getName().equals(reference.getName()) && mo11142getOverloadIdList().equals(reference.mo11142getOverloadIdList()) && hasValue() == reference.hasValue()) {
            return (!hasValue() || getValue().equals(reference.getValue())) && this.unknownFields.equals(reference.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (getOverloadIdCount() > 0) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + mo11142getOverloadIdList().hashCode();
        }
        if (hasValue()) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getValue().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m11143newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m11146toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ReferenceOrBuilder {
        private int bitField0_;
        private Object name_;
        private LazyStringList overloadId_;
        private SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> valueBuilder_;
        private Constant value_;

        private Builder() {
            this.name_ = "";
            this.overloadId_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.overloadId_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeclProto.internal_static_google_api_expr_v1alpha1_Reference_descriptor;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
        public boolean hasValue() {
            return (this.valueBuilder_ == null && this.value_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeclProto.internal_static_google_api_expr_v1alpha1_Reference_fieldAccessorTable.ensureFieldAccessorsInitialized(Reference.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Reference.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11157clear() {
            super.clear();
            this.name_ = "";
            this.overloadId_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            if (this.valueBuilder_ == null) {
                this.value_ = null;
            } else {
                this.value_ = null;
                this.valueBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return DeclProto.internal_static_google_api_expr_v1alpha1_Reference_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Reference m11170getDefaultInstanceForType() {
            return Reference.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Reference m11151build() throws UninitializedMessageException {
            Reference referenceM11153buildPartial = m11153buildPartial();
            if (referenceM11153buildPartial.isInitialized()) {
                return referenceM11153buildPartial;
            }
            throw newUninitializedMessageException(referenceM11153buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Reference m11153buildPartial() {
            Reference reference = new Reference(this);
            reference.name_ = this.name_;
            if ((this.bitField0_ & 1) != 0) {
                this.overloadId_ = this.overloadId_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            reference.overloadId_ = this.overloadId_;
            SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
            if (singleFieldBuilderV3 == null) {
                reference.value_ = this.value_;
            } else {
                reference.value_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return reference;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11169clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11181setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11159clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11162clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11183setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11149addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11174mergeFrom(Message message) {
            if (message instanceof Reference) {
                return mergeFrom((Reference) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Reference reference) {
            if (reference == Reference.getDefaultInstance()) {
                return this;
            }
            if (!reference.getName().isEmpty()) {
                this.name_ = reference.name_;
                onChanged();
            }
            if (!reference.overloadId_.isEmpty()) {
                if (this.overloadId_.isEmpty()) {
                    this.overloadId_ = reference.overloadId_;
                    this.bitField0_ &= -2;
                } else {
                    ensureOverloadIdIsMutable();
                    this.overloadId_.addAll(reference.overloadId_);
                }
                onChanged();
            }
            if (reference.hasValue()) {
                mergeValue(reference.getValue());
            }
            m11179mergeUnknownFields(reference.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Reference.Builder m11175mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Reference.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Reference r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Reference) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Reference r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Reference) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Reference.Builder.m11175mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Reference$Builder");
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
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

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
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
            Reference.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = Reference.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        private void ensureOverloadIdIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.overloadId_ = new LazyStringArrayList(this.overloadId_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
        /* renamed from: getOverloadIdList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo11142getOverloadIdList() {
            return this.overloadId_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
        public int getOverloadIdCount() {
            return this.overloadId_.size();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
        public String getOverloadId(int i) {
            return (String) this.overloadId_.get(i);
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
        public ByteString getOverloadIdBytes(int i) {
            return this.overloadId_.getByteString(i);
        }

        public Builder setOverloadId(int i, String str) {
            str.getClass();
            ensureOverloadIdIsMutable();
            this.overloadId_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addOverloadId(String str) {
            str.getClass();
            ensureOverloadIdIsMutable();
            this.overloadId_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllOverloadId(Iterable<String> iterable) {
            ensureOverloadIdIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.overloadId_);
            onChanged();
            return this;
        }

        public Builder clearOverloadId() {
            this.overloadId_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addOverloadIdBytes(ByteString byteString) {
            byteString.getClass();
            Reference.checkByteStringIsUtf8(byteString);
            ensureOverloadIdIsMutable();
            this.overloadId_.add(byteString);
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
        public Constant getValue() {
            SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Constant constant = this.value_;
            return constant == null ? Constant.getDefaultInstance() : constant;
        }

        public Builder setValue(Constant constant) {
            SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
            if (singleFieldBuilderV3 == null) {
                constant.getClass();
                this.value_ = constant;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(constant);
            }
            return this;
        }

        public Builder setValue(Constant.Builder builder) {
            SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.value_ = builder.m10505build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m10505build());
            }
            return this;
        }

        public Builder mergeValue(Constant constant) {
            SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
            if (singleFieldBuilderV3 == null) {
                Constant constant2 = this.value_;
                if (constant2 != null) {
                    this.value_ = Constant.newBuilder(constant2).mergeFrom(constant).m10507buildPartial();
                } else {
                    this.value_ = constant;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(constant);
            }
            return this;
        }

        public Builder clearValue() {
            if (this.valueBuilder_ == null) {
                this.value_ = null;
                onChanged();
            } else {
                this.value_ = null;
                this.valueBuilder_ = null;
            }
            return this;
        }

        public Constant.Builder getValueBuilder() {
            onChanged();
            return getValueFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ReferenceOrBuilder
        public ConstantOrBuilder getValueOrBuilder() {
            SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ConstantOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Constant constant = this.value_;
            return constant == null ? Constant.getDefaultInstance() : constant;
        }

        private SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> getValueFieldBuilder() {
            if (this.valueBuilder_ == null) {
                this.valueBuilder_ = new SingleFieldBuilderV3<>(getValue(), getParentForChildren(), isClean());
                this.value_ = null;
            }
            return this.valueBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m11185setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m11179mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
