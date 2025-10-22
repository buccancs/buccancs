package com.google.api;

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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class ResourceDescriptor extends GeneratedMessageV3 implements ResourceDescriptorOrBuilder {
    public static final int HISTORY_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_FIELD_NUMBER = 3;
    public static final int PATTERN_FIELD_NUMBER = 2;
    public static final int PLURAL_FIELD_NUMBER = 5;
    public static final int SINGULAR_FIELD_NUMBER = 6;
    public static final int TYPE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final ResourceDescriptor DEFAULT_INSTANCE = new ResourceDescriptor();
    private static final Parser<ResourceDescriptor> PARSER = new AbstractParser<ResourceDescriptor>() { // from class: com.google.api.ResourceDescriptor.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ResourceDescriptor m2337parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ResourceDescriptor(codedInputStream, extensionRegistryLite);
        }
    };
    private int bitField0_;
    private int history_;
    private byte memoizedIsInitialized;
    private volatile Object nameField_;
    private LazyStringList pattern_;
    private volatile Object plural_;
    private volatile Object singular_;
    private volatile Object type_;

    private ResourceDescriptor(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ResourceDescriptor() {
        this.memoizedIsInitialized = (byte) -1;
        this.type_ = "";
        this.pattern_ = LazyStringArrayList.EMPTY;
        this.nameField_ = "";
        this.history_ = 0;
        this.plural_ = "";
        this.singular_ = "";
    }

    private ResourceDescriptor(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.type_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if ((i & 2) == 0) {
                                    this.pattern_ = new LazyStringArrayList();
                                    i |= 2;
                                }
                                this.pattern_.add(stringRequireUtf8);
                            } else if (tag == 26) {
                                this.nameField_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 32) {
                                this.history_ = codedInputStream.readEnum();
                            } else if (tag == 42) {
                                this.plural_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 50) {
                                this.singular_ = codedInputStream.readStringRequireUtf8();
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
                if ((i & 2) != 0) {
                    this.pattern_ = this.pattern_.getUnmodifiableView();
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ResourceDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ResourceDescriptor> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ResourceProto.internal_static_google_api_ResourceDescriptor_descriptor;
    }

    public static ResourceDescriptor parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ResourceDescriptor) PARSER.parseFrom(byteBuffer);
    }

    public static ResourceDescriptor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceDescriptor) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ResourceDescriptor parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ResourceDescriptor) PARSER.parseFrom(byteString);
    }

    public static ResourceDescriptor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceDescriptor) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ResourceDescriptor parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ResourceDescriptor) PARSER.parseFrom(bArr);
    }

    public static ResourceDescriptor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceDescriptor) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ResourceDescriptor parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ResourceDescriptor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ResourceDescriptor parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ResourceDescriptor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ResourceDescriptor parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ResourceDescriptor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m2336toBuilder();
    }

    public static Builder newBuilder(ResourceDescriptor resourceDescriptor) {
        return DEFAULT_INSTANCE.m2336toBuilder().mergeFrom(resourceDescriptor);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ResourceDescriptor m2330getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.api.ResourceDescriptorOrBuilder
    public int getHistoryValue() {
        return this.history_;
    }

    public Parser<ResourceDescriptor> getParserForType() {
        return PARSER;
    }

    @Override // com.google.api.ResourceDescriptorOrBuilder
    /* renamed from: getPatternList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo2331getPatternList() {
        return this.pattern_;
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
        return ResourceProto.internal_static_google_api_ResourceDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(ResourceDescriptor.class, Builder.class);
    }

    @Override // com.google.api.ResourceDescriptorOrBuilder
    public String getType() {
        Object obj = this.type_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.type_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ResourceDescriptorOrBuilder
    public ByteString getTypeBytes() {
        Object obj = this.type_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.type_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.ResourceDescriptorOrBuilder
    public int getPatternCount() {
        return this.pattern_.size();
    }

    @Override // com.google.api.ResourceDescriptorOrBuilder
    public String getPattern(int i) {
        return (String) this.pattern_.get(i);
    }

    @Override // com.google.api.ResourceDescriptorOrBuilder
    public ByteString getPatternBytes(int i) {
        return this.pattern_.getByteString(i);
    }

    @Override // com.google.api.ResourceDescriptorOrBuilder
    public String getNameField() {
        Object obj = this.nameField_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.nameField_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ResourceDescriptorOrBuilder
    public ByteString getNameFieldBytes() {
        Object obj = this.nameField_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.nameField_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.ResourceDescriptorOrBuilder
    public History getHistory() {
        History historyValueOf = History.valueOf(this.history_);
        return historyValueOf == null ? History.UNRECOGNIZED : historyValueOf;
    }

    @Override // com.google.api.ResourceDescriptorOrBuilder
    public String getPlural() {
        Object obj = this.plural_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.plural_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ResourceDescriptorOrBuilder
    public ByteString getPluralBytes() {
        Object obj = this.plural_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.plural_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.ResourceDescriptorOrBuilder
    public String getSingular() {
        Object obj = this.singular_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.singular_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ResourceDescriptorOrBuilder
    public ByteString getSingularBytes() {
        Object obj = this.singular_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.singular_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.type_);
        }
        for (int i = 0; i < this.pattern_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.pattern_.getRaw(i));
        }
        if (!getNameFieldBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.nameField_);
        }
        if (this.history_ != History.HISTORY_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(4, this.history_);
        }
        if (!getPluralBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.plural_);
        }
        if (!getSingularBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.singular_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getTypeBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.type_) : 0;
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.pattern_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.pattern_.getRaw(i2));
        }
        int size = iComputeStringSize + iComputeStringSizeNoTag + mo2331getPatternList().size();
        if (!getNameFieldBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(3, this.nameField_);
        }
        if (this.history_ != History.HISTORY_UNSPECIFIED.getNumber()) {
            size += CodedOutputStream.computeEnumSize(4, this.history_);
        }
        if (!getPluralBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(5, this.plural_);
        }
        if (!getSingularBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(6, this.singular_);
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResourceDescriptor)) {
            return super.equals(obj);
        }
        ResourceDescriptor resourceDescriptor = (ResourceDescriptor) obj;
        return getType().equals(resourceDescriptor.getType()) && mo2331getPatternList().equals(resourceDescriptor.mo2331getPatternList()) && getNameField().equals(resourceDescriptor.getNameField()) && this.history_ == resourceDescriptor.history_ && getPlural().equals(resourceDescriptor.getPlural()) && getSingular().equals(resourceDescriptor.getSingular()) && this.unknownFields.equals(resourceDescriptor.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getType().hashCode();
        if (getPatternCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + mo2331getPatternList().hashCode();
        }
        int iHashCode2 = (((((((((((((((((iHashCode * 37) + 3) * 53) + getNameField().hashCode()) * 37) + 4) * 53) + this.history_) * 37) + 5) * 53) + getPlural().hashCode()) * 37) + 6) * 53) + getSingular().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m2334newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m2336toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m2333newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum History implements ProtocolMessageEnum {
        HISTORY_UNSPECIFIED(0),
        ORIGINALLY_SINGLE_PATTERN(1),
        FUTURE_MULTI_PATTERN(2),
        UNRECOGNIZED(-1);

        public static final int FUTURE_MULTI_PATTERN_VALUE = 2;
        public static final int HISTORY_UNSPECIFIED_VALUE = 0;
        public static final int ORIGINALLY_SINGLE_PATTERN_VALUE = 1;
        private static final Internal.EnumLiteMap<History> internalValueMap = new Internal.EnumLiteMap<History>() { // from class: com.google.api.ResourceDescriptor.History.1
            /* renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
            public History m2376findValueByNumber(int i) {
                return History.forNumber(i);
            }
        };
        private static final History[] VALUES = values();
        private final int value;

        History(int i) {
            this.value = i;
        }

        public static History forNumber(int i) {
            if (i == 0) {
                return HISTORY_UNSPECIFIED;
            }
            if (i == 1) {
                return ORIGINALLY_SINGLE_PATTERN;
            }
            if (i != 2) {
                return null;
            }
            return FUTURE_MULTI_PATTERN;
        }

        public static Internal.EnumLiteMap<History> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static History valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) ResourceDescriptor.getDescriptor().getEnumTypes().get(0);
        }

        public static History valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ResourceDescriptorOrBuilder {
        private int bitField0_;
        private int history_;
        private Object nameField_;
        private LazyStringList pattern_;
        private Object plural_;
        private Object singular_;
        private Object type_;

        private Builder() {
            this.type_ = "";
            this.pattern_ = LazyStringArrayList.EMPTY;
            this.nameField_ = "";
            this.history_ = 0;
            this.plural_ = "";
            this.singular_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.type_ = "";
            this.pattern_ = LazyStringArrayList.EMPTY;
            this.nameField_ = "";
            this.history_ = 0;
            this.plural_ = "";
            this.singular_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ResourceProto.internal_static_google_api_ResourceDescriptor_descriptor;
        }

        @Override // com.google.api.ResourceDescriptorOrBuilder
        public int getHistoryValue() {
            return this.history_;
        }

        public Builder setHistoryValue(int i) {
            this.history_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ResourceProto.internal_static_google_api_ResourceDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(ResourceDescriptor.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ResourceDescriptor.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2347clear() {
            super.clear();
            this.type_ = "";
            this.pattern_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            this.nameField_ = "";
            this.history_ = 0;
            this.plural_ = "";
            this.singular_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ResourceProto.internal_static_google_api_ResourceDescriptor_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ResourceDescriptor m2360getDefaultInstanceForType() {
            return ResourceDescriptor.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ResourceDescriptor m2341build() throws UninitializedMessageException {
            ResourceDescriptor resourceDescriptorM2343buildPartial = m2343buildPartial();
            if (resourceDescriptorM2343buildPartial.isInitialized()) {
                return resourceDescriptorM2343buildPartial;
            }
            throw newUninitializedMessageException(resourceDescriptorM2343buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ResourceDescriptor m2343buildPartial() {
            ResourceDescriptor resourceDescriptor = new ResourceDescriptor(this);
            resourceDescriptor.type_ = this.type_;
            if ((this.bitField0_ & 2) != 0) {
                this.pattern_ = this.pattern_.getUnmodifiableView();
                this.bitField0_ &= -3;
            }
            resourceDescriptor.pattern_ = this.pattern_;
            resourceDescriptor.nameField_ = this.nameField_;
            resourceDescriptor.history_ = this.history_;
            resourceDescriptor.plural_ = this.plural_;
            resourceDescriptor.singular_ = this.singular_;
            resourceDescriptor.bitField0_ = 0;
            onBuilt();
            return resourceDescriptor;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2358clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2371setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2349clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2352clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2373setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2339addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2365mergeFrom(Message message) {
            if (message instanceof ResourceDescriptor) {
                return mergeFrom((ResourceDescriptor) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ResourceDescriptor resourceDescriptor) {
            if (resourceDescriptor == ResourceDescriptor.getDefaultInstance()) {
                return this;
            }
            if (!resourceDescriptor.getType().isEmpty()) {
                this.type_ = resourceDescriptor.type_;
                onChanged();
            }
            if (!resourceDescriptor.pattern_.isEmpty()) {
                if (this.pattern_.isEmpty()) {
                    this.pattern_ = resourceDescriptor.pattern_;
                    this.bitField0_ &= -3;
                } else {
                    ensurePatternIsMutable();
                    this.pattern_.addAll(resourceDescriptor.pattern_);
                }
                onChanged();
            }
            if (!resourceDescriptor.getNameField().isEmpty()) {
                this.nameField_ = resourceDescriptor.nameField_;
                onChanged();
            }
            if (resourceDescriptor.history_ != 0) {
                setHistoryValue(resourceDescriptor.getHistoryValue());
            }
            if (!resourceDescriptor.getPlural().isEmpty()) {
                this.plural_ = resourceDescriptor.plural_;
                onChanged();
            }
            if (!resourceDescriptor.getSingular().isEmpty()) {
                this.singular_ = resourceDescriptor.singular_;
                onChanged();
            }
            m2369mergeUnknownFields(resourceDescriptor.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.api.ResourceDescriptor.Builder m2366mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.ResourceDescriptor.access$1200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.ResourceDescriptor r3 = (com.google.api.ResourceDescriptor) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.ResourceDescriptor r4 = (com.google.api.ResourceDescriptor) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.ResourceDescriptor.Builder.m2366mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.ResourceDescriptor$Builder");
        }

        @Override // com.google.api.ResourceDescriptorOrBuilder
        public String getType() {
            Object obj = this.type_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.type_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setType(String str) {
            str.getClass();
            this.type_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.ResourceDescriptorOrBuilder
        public ByteString getTypeBytes() {
            Object obj = this.type_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.type_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setTypeBytes(ByteString byteString) {
            byteString.getClass();
            ResourceDescriptor.checkByteStringIsUtf8(byteString);
            this.type_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearType() {
            this.type_ = ResourceDescriptor.getDefaultInstance().getType();
            onChanged();
            return this;
        }

        private void ensurePatternIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.pattern_ = new LazyStringArrayList(this.pattern_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.api.ResourceDescriptorOrBuilder
        /* renamed from: getPatternList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo2331getPatternList() {
            return this.pattern_.getUnmodifiableView();
        }

        @Override // com.google.api.ResourceDescriptorOrBuilder
        public int getPatternCount() {
            return this.pattern_.size();
        }

        @Override // com.google.api.ResourceDescriptorOrBuilder
        public String getPattern(int i) {
            return (String) this.pattern_.get(i);
        }

        @Override // com.google.api.ResourceDescriptorOrBuilder
        public ByteString getPatternBytes(int i) {
            return this.pattern_.getByteString(i);
        }

        public Builder setPattern(int i, String str) {
            str.getClass();
            ensurePatternIsMutable();
            this.pattern_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addPattern(String str) {
            str.getClass();
            ensurePatternIsMutable();
            this.pattern_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllPattern(Iterable<String> iterable) {
            ensurePatternIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.pattern_);
            onChanged();
            return this;
        }

        public Builder clearPattern() {
            this.pattern_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            onChanged();
            return this;
        }

        public Builder addPatternBytes(ByteString byteString) {
            byteString.getClass();
            ResourceDescriptor.checkByteStringIsUtf8(byteString);
            ensurePatternIsMutable();
            this.pattern_.add(byteString);
            onChanged();
            return this;
        }

        @Override // com.google.api.ResourceDescriptorOrBuilder
        public String getNameField() {
            Object obj = this.nameField_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.nameField_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setNameField(String str) {
            str.getClass();
            this.nameField_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.ResourceDescriptorOrBuilder
        public ByteString getNameFieldBytes() {
            Object obj = this.nameField_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.nameField_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNameFieldBytes(ByteString byteString) {
            byteString.getClass();
            ResourceDescriptor.checkByteStringIsUtf8(byteString);
            this.nameField_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearNameField() {
            this.nameField_ = ResourceDescriptor.getDefaultInstance().getNameField();
            onChanged();
            return this;
        }

        @Override // com.google.api.ResourceDescriptorOrBuilder
        public History getHistory() {
            History historyValueOf = History.valueOf(this.history_);
            return historyValueOf == null ? History.UNRECOGNIZED : historyValueOf;
        }

        public Builder setHistory(History history) {
            history.getClass();
            this.history_ = history.getNumber();
            onChanged();
            return this;
        }

        public Builder clearHistory() {
            this.history_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.api.ResourceDescriptorOrBuilder
        public String getPlural() {
            Object obj = this.plural_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.plural_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setPlural(String str) {
            str.getClass();
            this.plural_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.ResourceDescriptorOrBuilder
        public ByteString getPluralBytes() {
            Object obj = this.plural_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.plural_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setPluralBytes(ByteString byteString) {
            byteString.getClass();
            ResourceDescriptor.checkByteStringIsUtf8(byteString);
            this.plural_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearPlural() {
            this.plural_ = ResourceDescriptor.getDefaultInstance().getPlural();
            onChanged();
            return this;
        }

        @Override // com.google.api.ResourceDescriptorOrBuilder
        public String getSingular() {
            Object obj = this.singular_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.singular_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setSingular(String str) {
            str.getClass();
            this.singular_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.ResourceDescriptorOrBuilder
        public ByteString getSingularBytes() {
            Object obj = this.singular_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.singular_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSingularBytes(ByteString byteString) {
            byteString.getClass();
            ResourceDescriptor.checkByteStringIsUtf8(byteString);
            this.singular_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearSingular() {
            this.singular_ = ResourceDescriptor.getDefaultInstance().getSingular();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m2375setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m2369mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
