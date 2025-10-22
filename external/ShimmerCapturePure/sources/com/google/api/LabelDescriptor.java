package com.google.api;

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
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class LabelDescriptor extends GeneratedMessageV3 implements LabelDescriptorOrBuilder {
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int KEY_FIELD_NUMBER = 1;
    public static final int VALUE_TYPE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final LabelDescriptor DEFAULT_INSTANCE = new LabelDescriptor();
    private static final Parser<LabelDescriptor> PARSER = new AbstractParser<LabelDescriptor>() { // from class: com.google.api.LabelDescriptor.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public LabelDescriptor m1455parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new LabelDescriptor(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object description_;
    private volatile Object key_;
    private byte memoizedIsInitialized;
    private int valueType_;

    private LabelDescriptor(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private LabelDescriptor() {
        this.memoizedIsInitialized = (byte) -1;
        this.key_ = "";
        this.valueType_ = 0;
        this.description_ = "";
    }

    private LabelDescriptor(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.key_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 16) {
                            this.valueType_ = codedInputStream.readEnum();
                        } else if (tag == 26) {
                            this.description_ = codedInputStream.readStringRequireUtf8();
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
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static LabelDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LabelDescriptor> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LabelProto.internal_static_google_api_LabelDescriptor_descriptor;
    }

    public static LabelDescriptor parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (LabelDescriptor) PARSER.parseFrom(byteBuffer);
    }

    public static LabelDescriptor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LabelDescriptor) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static LabelDescriptor parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (LabelDescriptor) PARSER.parseFrom(byteString);
    }

    public static LabelDescriptor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LabelDescriptor) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static LabelDescriptor parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (LabelDescriptor) PARSER.parseFrom(bArr);
    }

    public static LabelDescriptor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LabelDescriptor) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static LabelDescriptor parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static LabelDescriptor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LabelDescriptor parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static LabelDescriptor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LabelDescriptor parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static LabelDescriptor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m1454toBuilder();
    }

    public static Builder newBuilder(LabelDescriptor labelDescriptor) {
        return DEFAULT_INSTANCE.m1454toBuilder().mergeFrom(labelDescriptor);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public LabelDescriptor m1449getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<LabelDescriptor> getParserForType() {
        return PARSER;
    }

    @Override // com.google.api.LabelDescriptorOrBuilder
    public int getValueTypeValue() {
        return this.valueType_;
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
        return LabelProto.internal_static_google_api_LabelDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(LabelDescriptor.class, Builder.class);
    }

    @Override // com.google.api.LabelDescriptorOrBuilder
    public String getKey() {
        Object obj = this.key_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.key_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.LabelDescriptorOrBuilder
    public ByteString getKeyBytes() {
        Object obj = this.key_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.key_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.LabelDescriptorOrBuilder
    public ValueType getValueType() {
        ValueType valueTypeValueOf = ValueType.valueOf(this.valueType_);
        return valueTypeValueOf == null ? ValueType.UNRECOGNIZED : valueTypeValueOf;
    }

    @Override // com.google.api.LabelDescriptorOrBuilder
    public String getDescription() {
        Object obj = this.description_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.description_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.LabelDescriptorOrBuilder
    public ByteString getDescriptionBytes() {
        Object obj = this.description_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getKeyBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.key_);
        }
        if (this.valueType_ != ValueType.STRING.getNumber()) {
            codedOutputStream.writeEnum(2, this.valueType_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.description_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getKeyBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.key_) : 0;
        if (this.valueType_ != ValueType.STRING.getNumber()) {
            iComputeStringSize += CodedOutputStream.computeEnumSize(2, this.valueType_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.description_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LabelDescriptor)) {
            return super.equals(obj);
        }
        LabelDescriptor labelDescriptor = (LabelDescriptor) obj;
        return getKey().equals(labelDescriptor.getKey()) && this.valueType_ == labelDescriptor.valueType_ && getDescription().equals(labelDescriptor.getDescription()) && this.unknownFields.equals(labelDescriptor.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getKey().hashCode()) * 37) + 2) * 53) + this.valueType_) * 37) + 3) * 53) + getDescription().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m1452newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m1454toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1451newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ValueType implements ProtocolMessageEnum {
        STRING(0),
        BOOL(1),
        INT64(2),
        UNRECOGNIZED(-1);

        public static final int BOOL_VALUE = 1;
        public static final int INT64_VALUE = 2;
        public static final int STRING_VALUE = 0;
        private static final Internal.EnumLiteMap<ValueType> internalValueMap = new Internal.EnumLiteMap<ValueType>() { // from class: com.google.api.LabelDescriptor.ValueType.1
            /* renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
            public ValueType m1494findValueByNumber(int i) {
                return ValueType.forNumber(i);
            }
        };
        private static final ValueType[] VALUES = values();
        private final int value;

        ValueType(int i) {
            this.value = i;
        }

        public static ValueType forNumber(int i) {
            if (i == 0) {
                return STRING;
            }
            if (i == 1) {
                return BOOL;
            }
            if (i != 2) {
                return null;
            }
            return INT64;
        }

        public static Internal.EnumLiteMap<ValueType> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static ValueType valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) LabelDescriptor.getDescriptor().getEnumTypes().get(0);
        }

        public static ValueType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LabelDescriptorOrBuilder {
        private Object description_;
        private Object key_;
        private int valueType_;

        private Builder() {
            this.key_ = "";
            this.valueType_ = 0;
            this.description_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.key_ = "";
            this.valueType_ = 0;
            this.description_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LabelProto.internal_static_google_api_LabelDescriptor_descriptor;
        }

        @Override // com.google.api.LabelDescriptorOrBuilder
        public int getValueTypeValue() {
            return this.valueType_;
        }

        public Builder setValueTypeValue(int i) {
            this.valueType_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LabelProto.internal_static_google_api_LabelDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(LabelDescriptor.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = LabelDescriptor.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1465clear() {
            super.clear();
            this.key_ = "";
            this.valueType_ = 0;
            this.description_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return LabelProto.internal_static_google_api_LabelDescriptor_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LabelDescriptor m1478getDefaultInstanceForType() {
            return LabelDescriptor.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LabelDescriptor m1459build() throws UninitializedMessageException {
            LabelDescriptor labelDescriptorM1461buildPartial = m1461buildPartial();
            if (labelDescriptorM1461buildPartial.isInitialized()) {
                return labelDescriptorM1461buildPartial;
            }
            throw newUninitializedMessageException(labelDescriptorM1461buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LabelDescriptor m1461buildPartial() {
            LabelDescriptor labelDescriptor = new LabelDescriptor(this);
            labelDescriptor.key_ = this.key_;
            labelDescriptor.valueType_ = this.valueType_;
            labelDescriptor.description_ = this.description_;
            onBuilt();
            return labelDescriptor;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1476clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1489setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1467clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1470clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1491setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1457addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1483mergeFrom(Message message) {
            if (message instanceof LabelDescriptor) {
                return mergeFrom((LabelDescriptor) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(LabelDescriptor labelDescriptor) {
            if (labelDescriptor == LabelDescriptor.getDefaultInstance()) {
                return this;
            }
            if (!labelDescriptor.getKey().isEmpty()) {
                this.key_ = labelDescriptor.key_;
                onChanged();
            }
            if (labelDescriptor.valueType_ != 0) {
                setValueTypeValue(labelDescriptor.getValueTypeValue());
            }
            if (!labelDescriptor.getDescription().isEmpty()) {
                this.description_ = labelDescriptor.description_;
                onChanged();
            }
            m1487mergeUnknownFields(labelDescriptor.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.api.LabelDescriptor.Builder m1484mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.LabelDescriptor.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.LabelDescriptor r3 = (com.google.api.LabelDescriptor) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.LabelDescriptor r4 = (com.google.api.LabelDescriptor) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.LabelDescriptor.Builder.m1484mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.LabelDescriptor$Builder");
        }

        @Override // com.google.api.LabelDescriptorOrBuilder
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

        @Override // com.google.api.LabelDescriptorOrBuilder
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
            LabelDescriptor.checkByteStringIsUtf8(byteString);
            this.key_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearKey() {
            this.key_ = LabelDescriptor.getDefaultInstance().getKey();
            onChanged();
            return this;
        }

        @Override // com.google.api.LabelDescriptorOrBuilder
        public ValueType getValueType() {
            ValueType valueTypeValueOf = ValueType.valueOf(this.valueType_);
            return valueTypeValueOf == null ? ValueType.UNRECOGNIZED : valueTypeValueOf;
        }

        public Builder setValueType(ValueType valueType) {
            valueType.getClass();
            this.valueType_ = valueType.getNumber();
            onChanged();
            return this;
        }

        public Builder clearValueType() {
            this.valueType_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.api.LabelDescriptorOrBuilder
        public String getDescription() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.description_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setDescription(String str) {
            str.getClass();
            this.description_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.LabelDescriptorOrBuilder
        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.description_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            byteString.getClass();
            LabelDescriptor.checkByteStringIsUtf8(byteString);
            this.description_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearDescription() {
            this.description_ = LabelDescriptor.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1493setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1487mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
