package com.google.api;

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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class ContextRule extends GeneratedMessageV3 implements ContextRuleOrBuilder {
    public static final int ALLOWED_REQUEST_EXTENSIONS_FIELD_NUMBER = 4;
    public static final int ALLOWED_RESPONSE_EXTENSIONS_FIELD_NUMBER = 5;
    public static final int PROVIDED_FIELD_NUMBER = 3;
    public static final int REQUESTED_FIELD_NUMBER = 2;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final ContextRule DEFAULT_INSTANCE = new ContextRule();
    private static final Parser<ContextRule> PARSER = new AbstractParser<ContextRule>() { // from class: com.google.api.ContextRule.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ContextRule m716parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ContextRule(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private LazyStringList allowedRequestExtensions_;
    private LazyStringList allowedResponseExtensions_;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private LazyStringList provided_;
    private LazyStringList requested_;
    private volatile Object selector_;

    private ContextRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ContextRule() {
        this.memoizedIsInitialized = (byte) -1;
        this.selector_ = "";
        this.requested_ = LazyStringArrayList.EMPTY;
        this.provided_ = LazyStringArrayList.EMPTY;
        this.allowedRequestExtensions_ = LazyStringArrayList.EMPTY;
        this.allowedResponseExtensions_ = LazyStringArrayList.EMPTY;
    }

    private ContextRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            this.selector_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if ((i & 2) == 0) {
                                this.requested_ = new LazyStringArrayList();
                                i |= 2;
                            }
                            this.requested_.add(stringRequireUtf8);
                        } else if (tag == 26) {
                            String stringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            if ((i & 4) == 0) {
                                this.provided_ = new LazyStringArrayList();
                                i |= 4;
                            }
                            this.provided_.add(stringRequireUtf82);
                        } else if (tag == 34) {
                            String stringRequireUtf83 = codedInputStream.readStringRequireUtf8();
                            if ((i & 8) == 0) {
                                this.allowedRequestExtensions_ = new LazyStringArrayList();
                                i |= 8;
                            }
                            this.allowedRequestExtensions_.add(stringRequireUtf83);
                        } else if (tag == 42) {
                            String stringRequireUtf84 = codedInputStream.readStringRequireUtf8();
                            if ((i & 16) == 0) {
                                this.allowedResponseExtensions_ = new LazyStringArrayList();
                                i |= 16;
                            }
                            this.allowedResponseExtensions_.add(stringRequireUtf84);
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
                if ((i & 2) != 0) {
                    this.requested_ = this.requested_.getUnmodifiableView();
                }
                if ((i & 4) != 0) {
                    this.provided_ = this.provided_.getUnmodifiableView();
                }
                if ((i & 8) != 0) {
                    this.allowedRequestExtensions_ = this.allowedRequestExtensions_.getUnmodifiableView();
                }
                if ((i & 16) != 0) {
                    this.allowedResponseExtensions_ = this.allowedResponseExtensions_.getUnmodifiableView();
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ContextRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ContextRule> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ContextProto.internal_static_google_api_ContextRule_descriptor;
    }

    public static ContextRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ContextRule) PARSER.parseFrom(byteBuffer);
    }

    public static ContextRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ContextRule) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ContextRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ContextRule) PARSER.parseFrom(byteString);
    }

    public static ContextRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ContextRule) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ContextRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ContextRule) PARSER.parseFrom(bArr);
    }

    public static ContextRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ContextRule) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ContextRule parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ContextRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ContextRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ContextRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ContextRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ContextRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m715toBuilder();
    }

    public static Builder newBuilder(ContextRule contextRule) {
        return DEFAULT_INSTANCE.m715toBuilder().mergeFrom(contextRule);
    }

    @Override // com.google.api.ContextRuleOrBuilder
    /* renamed from: getAllowedRequestExtensionsList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo705getAllowedRequestExtensionsList() {
        return this.allowedRequestExtensions_;
    }

    @Override // com.google.api.ContextRuleOrBuilder
    /* renamed from: getAllowedResponseExtensionsList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo706getAllowedResponseExtensionsList() {
        return this.allowedResponseExtensions_;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ContextRule m708getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ContextRule> getParserForType() {
        return PARSER;
    }

    @Override // com.google.api.ContextRuleOrBuilder
    /* renamed from: getProvidedList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo709getProvidedList() {
        return this.provided_;
    }

    @Override // com.google.api.ContextRuleOrBuilder
    /* renamed from: getRequestedList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo710getRequestedList() {
        return this.requested_;
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
        return ContextProto.internal_static_google_api_ContextRule_fieldAccessorTable.ensureFieldAccessorsInitialized(ContextRule.class, Builder.class);
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public String getSelector() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.selector_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public ByteString getSelectorBytes() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.selector_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public int getRequestedCount() {
        return this.requested_.size();
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public String getRequested(int i) {
        return (String) this.requested_.get(i);
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public ByteString getRequestedBytes(int i) {
        return this.requested_.getByteString(i);
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public int getProvidedCount() {
        return this.provided_.size();
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public String getProvided(int i) {
        return (String) this.provided_.get(i);
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public ByteString getProvidedBytes(int i) {
        return this.provided_.getByteString(i);
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public int getAllowedRequestExtensionsCount() {
        return this.allowedRequestExtensions_.size();
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public String getAllowedRequestExtensions(int i) {
        return (String) this.allowedRequestExtensions_.get(i);
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public ByteString getAllowedRequestExtensionsBytes(int i) {
        return this.allowedRequestExtensions_.getByteString(i);
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public int getAllowedResponseExtensionsCount() {
        return this.allowedResponseExtensions_.size();
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public String getAllowedResponseExtensions(int i) {
        return (String) this.allowedResponseExtensions_.get(i);
    }

    @Override // com.google.api.ContextRuleOrBuilder
    public ByteString getAllowedResponseExtensionsBytes(int i) {
        return this.allowedResponseExtensions_.getByteString(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getSelectorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.selector_);
        }
        for (int i = 0; i < this.requested_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.requested_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.provided_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.provided_.getRaw(i2));
        }
        for (int i3 = 0; i3 < this.allowedRequestExtensions_.size(); i3++) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.allowedRequestExtensions_.getRaw(i3));
        }
        for (int i4 = 0; i4 < this.allowedResponseExtensions_.size(); i4++) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.allowedResponseExtensions_.getRaw(i4));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getSelectorBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.selector_) : 0;
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.requested_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.requested_.getRaw(i2));
        }
        int size = iComputeStringSize + iComputeStringSizeNoTag + mo710getRequestedList().size();
        int iComputeStringSizeNoTag2 = 0;
        for (int i3 = 0; i3 < this.provided_.size(); i3++) {
            iComputeStringSizeNoTag2 += computeStringSizeNoTag(this.provided_.getRaw(i3));
        }
        int size2 = size + iComputeStringSizeNoTag2 + mo709getProvidedList().size();
        int iComputeStringSizeNoTag3 = 0;
        for (int i4 = 0; i4 < this.allowedRequestExtensions_.size(); i4++) {
            iComputeStringSizeNoTag3 += computeStringSizeNoTag(this.allowedRequestExtensions_.getRaw(i4));
        }
        int size3 = size2 + iComputeStringSizeNoTag3 + mo705getAllowedRequestExtensionsList().size();
        int iComputeStringSizeNoTag4 = 0;
        for (int i5 = 0; i5 < this.allowedResponseExtensions_.size(); i5++) {
            iComputeStringSizeNoTag4 += computeStringSizeNoTag(this.allowedResponseExtensions_.getRaw(i5));
        }
        int size4 = size3 + iComputeStringSizeNoTag4 + mo706getAllowedResponseExtensionsList().size() + this.unknownFields.getSerializedSize();
        this.memoizedSize = size4;
        return size4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ContextRule)) {
            return super.equals(obj);
        }
        ContextRule contextRule = (ContextRule) obj;
        return getSelector().equals(contextRule.getSelector()) && mo710getRequestedList().equals(contextRule.mo710getRequestedList()) && mo709getProvidedList().equals(contextRule.mo709getProvidedList()) && mo705getAllowedRequestExtensionsList().equals(contextRule.mo705getAllowedRequestExtensionsList()) && mo706getAllowedResponseExtensionsList().equals(contextRule.mo706getAllowedResponseExtensionsList()) && this.unknownFields.equals(contextRule.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode();
        if (getRequestedCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + mo710getRequestedList().hashCode();
        }
        if (getProvidedCount() > 0) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + mo709getProvidedList().hashCode();
        }
        if (getAllowedRequestExtensionsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + mo705getAllowedRequestExtensionsList().hashCode();
        }
        if (getAllowedResponseExtensionsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 5) * 53) + mo706getAllowedResponseExtensionsList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m713newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m715toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m712newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ContextRuleOrBuilder {
        private LazyStringList allowedRequestExtensions_;
        private LazyStringList allowedResponseExtensions_;
        private int bitField0_;
        private LazyStringList provided_;
        private LazyStringList requested_;
        private Object selector_;

        private Builder() {
            this.selector_ = "";
            this.requested_ = LazyStringArrayList.EMPTY;
            this.provided_ = LazyStringArrayList.EMPTY;
            this.allowedRequestExtensions_ = LazyStringArrayList.EMPTY;
            this.allowedResponseExtensions_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.selector_ = "";
            this.requested_ = LazyStringArrayList.EMPTY;
            this.provided_ = LazyStringArrayList.EMPTY;
            this.allowedRequestExtensions_ = LazyStringArrayList.EMPTY;
            this.allowedResponseExtensions_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ContextProto.internal_static_google_api_ContextRule_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ContextProto.internal_static_google_api_ContextRule_fieldAccessorTable.ensureFieldAccessorsInitialized(ContextRule.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ContextRule.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m726clear() {
            super.clear();
            this.selector_ = "";
            this.requested_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            this.provided_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            this.allowedRequestExtensions_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -9;
            this.allowedResponseExtensions_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -17;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ContextProto.internal_static_google_api_ContextRule_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ContextRule m739getDefaultInstanceForType() {
            return ContextRule.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ContextRule m720build() throws UninitializedMessageException {
            ContextRule contextRuleM722buildPartial = m722buildPartial();
            if (contextRuleM722buildPartial.isInitialized()) {
                return contextRuleM722buildPartial;
            }
            throw newUninitializedMessageException(contextRuleM722buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ContextRule m722buildPartial() {
            ContextRule contextRule = new ContextRule(this);
            contextRule.selector_ = this.selector_;
            if ((this.bitField0_ & 2) != 0) {
                this.requested_ = this.requested_.getUnmodifiableView();
                this.bitField0_ &= -3;
            }
            contextRule.requested_ = this.requested_;
            if ((this.bitField0_ & 4) != 0) {
                this.provided_ = this.provided_.getUnmodifiableView();
                this.bitField0_ &= -5;
            }
            contextRule.provided_ = this.provided_;
            if ((this.bitField0_ & 8) != 0) {
                this.allowedRequestExtensions_ = this.allowedRequestExtensions_.getUnmodifiableView();
                this.bitField0_ &= -9;
            }
            contextRule.allowedRequestExtensions_ = this.allowedRequestExtensions_;
            if ((this.bitField0_ & 16) != 0) {
                this.allowedResponseExtensions_ = this.allowedResponseExtensions_.getUnmodifiableView();
                this.bitField0_ &= -17;
            }
            contextRule.allowedResponseExtensions_ = this.allowedResponseExtensions_;
            contextRule.bitField0_ = 0;
            onBuilt();
            return contextRule;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m737clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m750setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m728clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m731clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m752setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m718addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m744mergeFrom(Message message) {
            if (message instanceof ContextRule) {
                return mergeFrom((ContextRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ContextRule contextRule) {
            if (contextRule == ContextRule.getDefaultInstance()) {
                return this;
            }
            if (!contextRule.getSelector().isEmpty()) {
                this.selector_ = contextRule.selector_;
                onChanged();
            }
            if (!contextRule.requested_.isEmpty()) {
                if (this.requested_.isEmpty()) {
                    this.requested_ = contextRule.requested_;
                    this.bitField0_ &= -3;
                } else {
                    ensureRequestedIsMutable();
                    this.requested_.addAll(contextRule.requested_);
                }
                onChanged();
            }
            if (!contextRule.provided_.isEmpty()) {
                if (this.provided_.isEmpty()) {
                    this.provided_ = contextRule.provided_;
                    this.bitField0_ &= -5;
                } else {
                    ensureProvidedIsMutable();
                    this.provided_.addAll(contextRule.provided_);
                }
                onChanged();
            }
            if (!contextRule.allowedRequestExtensions_.isEmpty()) {
                if (this.allowedRequestExtensions_.isEmpty()) {
                    this.allowedRequestExtensions_ = contextRule.allowedRequestExtensions_;
                    this.bitField0_ &= -9;
                } else {
                    ensureAllowedRequestExtensionsIsMutable();
                    this.allowedRequestExtensions_.addAll(contextRule.allowedRequestExtensions_);
                }
                onChanged();
            }
            if (!contextRule.allowedResponseExtensions_.isEmpty()) {
                if (this.allowedResponseExtensions_.isEmpty()) {
                    this.allowedResponseExtensions_ = contextRule.allowedResponseExtensions_;
                    this.bitField0_ &= -17;
                } else {
                    ensureAllowedResponseExtensionsIsMutable();
                    this.allowedResponseExtensions_.addAll(contextRule.allowedResponseExtensions_);
                }
                onChanged();
            }
            m748mergeUnknownFields(contextRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.api.ContextRule.Builder m745mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.ContextRule.access$1100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.ContextRule r3 = (com.google.api.ContextRule) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.ContextRule r4 = (com.google.api.ContextRule) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.ContextRule.Builder.m745mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.ContextRule$Builder");
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public String getSelector() {
            Object obj = this.selector_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.selector_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setSelector(String str) {
            str.getClass();
            this.selector_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public ByteString getSelectorBytes() {
            Object obj = this.selector_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.selector_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            byteString.getClass();
            ContextRule.checkByteStringIsUtf8(byteString);
            this.selector_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearSelector() {
            this.selector_ = ContextRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        private void ensureRequestedIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.requested_ = new LazyStringArrayList(this.requested_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.api.ContextRuleOrBuilder
        /* renamed from: getRequestedList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo710getRequestedList() {
            return this.requested_.getUnmodifiableView();
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public int getRequestedCount() {
            return this.requested_.size();
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public String getRequested(int i) {
            return (String) this.requested_.get(i);
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public ByteString getRequestedBytes(int i) {
            return this.requested_.getByteString(i);
        }

        public Builder setRequested(int i, String str) {
            str.getClass();
            ensureRequestedIsMutable();
            this.requested_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addRequested(String str) {
            str.getClass();
            ensureRequestedIsMutable();
            this.requested_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllRequested(Iterable<String> iterable) {
            ensureRequestedIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.requested_);
            onChanged();
            return this;
        }

        public Builder clearRequested() {
            this.requested_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            onChanged();
            return this;
        }

        public Builder addRequestedBytes(ByteString byteString) {
            byteString.getClass();
            ContextRule.checkByteStringIsUtf8(byteString);
            ensureRequestedIsMutable();
            this.requested_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureProvidedIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.provided_ = new LazyStringArrayList(this.provided_);
                this.bitField0_ |= 4;
            }
        }

        @Override // com.google.api.ContextRuleOrBuilder
        /* renamed from: getProvidedList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo709getProvidedList() {
            return this.provided_.getUnmodifiableView();
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public int getProvidedCount() {
            return this.provided_.size();
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public String getProvided(int i) {
            return (String) this.provided_.get(i);
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public ByteString getProvidedBytes(int i) {
            return this.provided_.getByteString(i);
        }

        public Builder setProvided(int i, String str) {
            str.getClass();
            ensureProvidedIsMutable();
            this.provided_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addProvided(String str) {
            str.getClass();
            ensureProvidedIsMutable();
            this.provided_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllProvided(Iterable<String> iterable) {
            ensureProvidedIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.provided_);
            onChanged();
            return this;
        }

        public Builder clearProvided() {
            this.provided_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            onChanged();
            return this;
        }

        public Builder addProvidedBytes(ByteString byteString) {
            byteString.getClass();
            ContextRule.checkByteStringIsUtf8(byteString);
            ensureProvidedIsMutable();
            this.provided_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureAllowedRequestExtensionsIsMutable() {
            if ((this.bitField0_ & 8) == 0) {
                this.allowedRequestExtensions_ = new LazyStringArrayList(this.allowedRequestExtensions_);
                this.bitField0_ |= 8;
            }
        }

        @Override // com.google.api.ContextRuleOrBuilder
        /* renamed from: getAllowedRequestExtensionsList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo705getAllowedRequestExtensionsList() {
            return this.allowedRequestExtensions_.getUnmodifiableView();
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public int getAllowedRequestExtensionsCount() {
            return this.allowedRequestExtensions_.size();
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public String getAllowedRequestExtensions(int i) {
            return (String) this.allowedRequestExtensions_.get(i);
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public ByteString getAllowedRequestExtensionsBytes(int i) {
            return this.allowedRequestExtensions_.getByteString(i);
        }

        public Builder setAllowedRequestExtensions(int i, String str) {
            str.getClass();
            ensureAllowedRequestExtensionsIsMutable();
            this.allowedRequestExtensions_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addAllowedRequestExtensions(String str) {
            str.getClass();
            ensureAllowedRequestExtensionsIsMutable();
            this.allowedRequestExtensions_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllAllowedRequestExtensions(Iterable<String> iterable) {
            ensureAllowedRequestExtensionsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.allowedRequestExtensions_);
            onChanged();
            return this;
        }

        public Builder clearAllowedRequestExtensions() {
            this.allowedRequestExtensions_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -9;
            onChanged();
            return this;
        }

        public Builder addAllowedRequestExtensionsBytes(ByteString byteString) {
            byteString.getClass();
            ContextRule.checkByteStringIsUtf8(byteString);
            ensureAllowedRequestExtensionsIsMutable();
            this.allowedRequestExtensions_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureAllowedResponseExtensionsIsMutable() {
            if ((this.bitField0_ & 16) == 0) {
                this.allowedResponseExtensions_ = new LazyStringArrayList(this.allowedResponseExtensions_);
                this.bitField0_ |= 16;
            }
        }

        @Override // com.google.api.ContextRuleOrBuilder
        /* renamed from: getAllowedResponseExtensionsList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo706getAllowedResponseExtensionsList() {
            return this.allowedResponseExtensions_.getUnmodifiableView();
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public int getAllowedResponseExtensionsCount() {
            return this.allowedResponseExtensions_.size();
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public String getAllowedResponseExtensions(int i) {
            return (String) this.allowedResponseExtensions_.get(i);
        }

        @Override // com.google.api.ContextRuleOrBuilder
        public ByteString getAllowedResponseExtensionsBytes(int i) {
            return this.allowedResponseExtensions_.getByteString(i);
        }

        public Builder setAllowedResponseExtensions(int i, String str) {
            str.getClass();
            ensureAllowedResponseExtensionsIsMutable();
            this.allowedResponseExtensions_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addAllowedResponseExtensions(String str) {
            str.getClass();
            ensureAllowedResponseExtensionsIsMutable();
            this.allowedResponseExtensions_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllAllowedResponseExtensions(Iterable<String> iterable) {
            ensureAllowedResponseExtensionsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.allowedResponseExtensions_);
            onChanged();
            return this;
        }

        public Builder clearAllowedResponseExtensions() {
            this.allowedResponseExtensions_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -17;
            onChanged();
            return this;
        }

        public Builder addAllowedResponseExtensionsBytes(ByteString byteString) {
            byteString.getClass();
            ContextRule.checkByteStringIsUtf8(byteString);
            ensureAllowedResponseExtensionsIsMutable();
            this.allowedResponseExtensions_.add(byteString);
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m754setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m748mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
