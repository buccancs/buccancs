package com.google.api;

import com.google.api.DocumentationRule;
import com.google.api.Page;
import com.google.protobuf.AbstractMessageLite;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class Documentation extends GeneratedMessageV3 implements DocumentationOrBuilder {
    public static final int DOCUMENTATION_ROOT_URL_FIELD_NUMBER = 4;
    public static final int OVERVIEW_FIELD_NUMBER = 2;
    public static final int PAGES_FIELD_NUMBER = 5;
    public static final int RULES_FIELD_NUMBER = 3;
    public static final int SUMMARY_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final Documentation DEFAULT_INSTANCE = new Documentation();
    private static final Parser<Documentation> PARSER = new AbstractParser<Documentation>() { // from class: com.google.api.Documentation.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Documentation m1176parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Documentation(codedInputStream, extensionRegistryLite);
        }
    };
    private int bitField0_;
    private volatile Object documentationRootUrl_;
    private byte memoizedIsInitialized;
    private volatile Object overview_;
    private List<Page> pages_;
    private List<DocumentationRule> rules_;
    private volatile Object summary_;

    private Documentation(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Documentation() {
        this.memoizedIsInitialized = (byte) -1;
        this.summary_ = "";
        this.pages_ = Collections.emptyList();
        this.rules_ = Collections.emptyList();
        this.documentationRootUrl_ = "";
        this.overview_ = "";
    }

    private Documentation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.summary_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            this.overview_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 26) {
                            if ((i & 4) == 0) {
                                this.rules_ = new ArrayList();
                                i |= 4;
                            }
                            this.rules_.add(codedInputStream.readMessage(DocumentationRule.parser(), extensionRegistryLite));
                        } else if (tag == 34) {
                            this.documentationRootUrl_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 42) {
                            if ((i & 2) == 0) {
                                this.pages_ = new ArrayList();
                                i |= 2;
                            }
                            this.pages_.add(codedInputStream.readMessage(Page.parser(), extensionRegistryLite));
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
                if ((i & 4) != 0) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                }
                if ((i & 2) != 0) {
                    this.pages_ = Collections.unmodifiableList(this.pages_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static Documentation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Documentation> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DocumentationProto.internal_static_google_api_Documentation_descriptor;
    }

    public static Documentation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Documentation) PARSER.parseFrom(byteBuffer);
    }

    public static Documentation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Documentation) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Documentation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Documentation) PARSER.parseFrom(byteString);
    }

    public static Documentation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Documentation) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Documentation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Documentation) PARSER.parseFrom(bArr);
    }

    public static Documentation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Documentation) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Documentation parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Documentation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Documentation parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Documentation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Documentation parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Documentation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m1175toBuilder();
    }

    public static Builder newBuilder(Documentation documentation) {
        return DEFAULT_INSTANCE.m1175toBuilder().mergeFrom(documentation);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Documentation m1170getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.api.DocumentationOrBuilder
    public List<Page> getPagesList() {
        return this.pages_;
    }

    @Override // com.google.api.DocumentationOrBuilder
    public List<? extends PageOrBuilder> getPagesOrBuilderList() {
        return this.pages_;
    }

    public Parser<Documentation> getParserForType() {
        return PARSER;
    }

    @Override // com.google.api.DocumentationOrBuilder
    public List<DocumentationRule> getRulesList() {
        return this.rules_;
    }

    @Override // com.google.api.DocumentationOrBuilder
    public List<? extends DocumentationRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
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
        return DocumentationProto.internal_static_google_api_Documentation_fieldAccessorTable.ensureFieldAccessorsInitialized(Documentation.class, Builder.class);
    }

    @Override // com.google.api.DocumentationOrBuilder
    public String getSummary() {
        Object obj = this.summary_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.summary_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.DocumentationOrBuilder
    public ByteString getSummaryBytes() {
        Object obj = this.summary_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.summary_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.DocumentationOrBuilder
    public int getPagesCount() {
        return this.pages_.size();
    }

    @Override // com.google.api.DocumentationOrBuilder
    public Page getPages(int i) {
        return this.pages_.get(i);
    }

    @Override // com.google.api.DocumentationOrBuilder
    public PageOrBuilder getPagesOrBuilder(int i) {
        return this.pages_.get(i);
    }

    @Override // com.google.api.DocumentationOrBuilder
    public int getRulesCount() {
        return this.rules_.size();
    }

    @Override // com.google.api.DocumentationOrBuilder
    public DocumentationRule getRules(int i) {
        return this.rules_.get(i);
    }

    @Override // com.google.api.DocumentationOrBuilder
    public DocumentationRuleOrBuilder getRulesOrBuilder(int i) {
        return this.rules_.get(i);
    }

    @Override // com.google.api.DocumentationOrBuilder
    public String getDocumentationRootUrl() {
        Object obj = this.documentationRootUrl_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.documentationRootUrl_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.DocumentationOrBuilder
    public ByteString getDocumentationRootUrlBytes() {
        Object obj = this.documentationRootUrl_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.documentationRootUrl_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.DocumentationOrBuilder
    public String getOverview() {
        Object obj = this.overview_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.overview_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.DocumentationOrBuilder
    public ByteString getOverviewBytes() {
        Object obj = this.overview_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.overview_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getSummaryBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.summary_);
        }
        if (!getOverviewBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.overview_);
        }
        for (int i = 0; i < this.rules_.size(); i++) {
            codedOutputStream.writeMessage(3, this.rules_.get(i));
        }
        if (!getDocumentationRootUrlBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.documentationRootUrl_);
        }
        for (int i2 = 0; i2 < this.pages_.size(); i2++) {
            codedOutputStream.writeMessage(5, this.pages_.get(i2));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getSummaryBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.summary_) : 0;
        if (!getOverviewBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.overview_);
        }
        for (int i2 = 0; i2 < this.rules_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, this.rules_.get(i2));
        }
        if (!getDocumentationRootUrlBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.documentationRootUrl_);
        }
        for (int i3 = 0; i3 < this.pages_.size(); i3++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(5, this.pages_.get(i3));
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Documentation)) {
            return super.equals(obj);
        }
        Documentation documentation = (Documentation) obj;
        return getSummary().equals(documentation.getSummary()) && getPagesList().equals(documentation.getPagesList()) && getRulesList().equals(documentation.getRulesList()) && getDocumentationRootUrl().equals(documentation.getDocumentationRootUrl()) && getOverview().equals(documentation.getOverview()) && this.unknownFields.equals(documentation.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSummary().hashCode();
        if (getPagesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 5) * 53) + getPagesList().hashCode();
        }
        if (getRulesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getRulesList().hashCode();
        }
        int iHashCode2 = (((((((((iHashCode * 37) + 4) * 53) + getDocumentationRootUrl().hashCode()) * 37) + 2) * 53) + getOverview().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m1173newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m1175toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1172newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DocumentationOrBuilder {
        private int bitField0_;
        private Object documentationRootUrl_;
        private Object overview_;
        private RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> pagesBuilder_;
        private List<Page> pages_;
        private RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> rulesBuilder_;
        private List<DocumentationRule> rules_;
        private Object summary_;

        private Builder() {
            this.summary_ = "";
            this.pages_ = Collections.emptyList();
            this.rules_ = Collections.emptyList();
            this.documentationRootUrl_ = "";
            this.overview_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.summary_ = "";
            this.pages_ = Collections.emptyList();
            this.rules_ = Collections.emptyList();
            this.documentationRootUrl_ = "";
            this.overview_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DocumentationProto.internal_static_google_api_Documentation_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentationProto.internal_static_google_api_Documentation_fieldAccessorTable.ensureFieldAccessorsInitialized(Documentation.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (Documentation.alwaysUseFieldBuilders) {
                getPagesFieldBuilder();
                getRulesFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1186clear() {
            super.clear();
            this.summary_ = "";
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.pages_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV32 = this.rulesBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            this.documentationRootUrl_ = "";
            this.overview_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return DocumentationProto.internal_static_google_api_Documentation_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Documentation m1199getDefaultInstanceForType() {
            return Documentation.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Documentation m1180build() throws UninitializedMessageException {
            Documentation documentationM1182buildPartial = m1182buildPartial();
            if (documentationM1182buildPartial.isInitialized()) {
                return documentationM1182buildPartial;
            }
            throw newUninitializedMessageException(documentationM1182buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Documentation m1182buildPartial() {
            Documentation documentation = new Documentation(this);
            documentation.summary_ = this.summary_;
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) != 0) {
                    this.pages_ = Collections.unmodifiableList(this.pages_);
                    this.bitField0_ &= -3;
                }
                documentation.pages_ = this.pages_;
            } else {
                documentation.pages_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV32 = this.rulesBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 4) != 0) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                    this.bitField0_ &= -5;
                }
                documentation.rules_ = this.rules_;
            } else {
                documentation.rules_ = repeatedFieldBuilderV32.build();
            }
            documentation.documentationRootUrl_ = this.documentationRootUrl_;
            documentation.overview_ = this.overview_;
            documentation.bitField0_ = 0;
            onBuilt();
            return documentation;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1197clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1210setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1188clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1191clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1212setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1178addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1204mergeFrom(Message message) {
            if (message instanceof Documentation) {
                return mergeFrom((Documentation) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Documentation documentation) {
            if (documentation == Documentation.getDefaultInstance()) {
                return this;
            }
            if (!documentation.getSummary().isEmpty()) {
                this.summary_ = documentation.summary_;
                onChanged();
            }
            if (this.pagesBuilder_ == null) {
                if (!documentation.pages_.isEmpty()) {
                    if (this.pages_.isEmpty()) {
                        this.pages_ = documentation.pages_;
                        this.bitField0_ &= -3;
                    } else {
                        ensurePagesIsMutable();
                        this.pages_.addAll(documentation.pages_);
                    }
                    onChanged();
                }
            } else if (!documentation.pages_.isEmpty()) {
                if (!this.pagesBuilder_.isEmpty()) {
                    this.pagesBuilder_.addAllMessages(documentation.pages_);
                } else {
                    this.pagesBuilder_.dispose();
                    this.pagesBuilder_ = null;
                    this.pages_ = documentation.pages_;
                    this.bitField0_ &= -3;
                    this.pagesBuilder_ = Documentation.alwaysUseFieldBuilders ? getPagesFieldBuilder() : null;
                }
            }
            if (this.rulesBuilder_ == null) {
                if (!documentation.rules_.isEmpty()) {
                    if (this.rules_.isEmpty()) {
                        this.rules_ = documentation.rules_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureRulesIsMutable();
                        this.rules_.addAll(documentation.rules_);
                    }
                    onChanged();
                }
            } else if (!documentation.rules_.isEmpty()) {
                if (!this.rulesBuilder_.isEmpty()) {
                    this.rulesBuilder_.addAllMessages(documentation.rules_);
                } else {
                    this.rulesBuilder_.dispose();
                    this.rulesBuilder_ = null;
                    this.rules_ = documentation.rules_;
                    this.bitField0_ &= -5;
                    this.rulesBuilder_ = Documentation.alwaysUseFieldBuilders ? getRulesFieldBuilder() : null;
                }
            }
            if (!documentation.getDocumentationRootUrl().isEmpty()) {
                this.documentationRootUrl_ = documentation.documentationRootUrl_;
                onChanged();
            }
            if (!documentation.getOverview().isEmpty()) {
                this.overview_ = documentation.overview_;
                onChanged();
            }
            m1208mergeUnknownFields(documentation.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.api.Documentation.Builder m1205mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Documentation.access$1300()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.Documentation r3 = (com.google.api.Documentation) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.Documentation r4 = (com.google.api.Documentation) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Documentation.Builder.m1205mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Documentation$Builder");
        }

        @Override // com.google.api.DocumentationOrBuilder
        public String getSummary() {
            Object obj = this.summary_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.summary_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setSummary(String str) {
            str.getClass();
            this.summary_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.DocumentationOrBuilder
        public ByteString getSummaryBytes() {
            Object obj = this.summary_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.summary_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSummaryBytes(ByteString byteString) {
            byteString.getClass();
            Documentation.checkByteStringIsUtf8(byteString);
            this.summary_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearSummary() {
            this.summary_ = Documentation.getDefaultInstance().getSummary();
            onChanged();
            return this;
        }

        private void ensurePagesIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.pages_ = new ArrayList(this.pages_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.api.DocumentationOrBuilder
        public List<Page> getPagesList() {
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.pages_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.DocumentationOrBuilder
        public int getPagesCount() {
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.pages_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.DocumentationOrBuilder
        public Page getPages(int i) {
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.pages_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setPages(int i, Page page) {
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                page.getClass();
                ensurePagesIsMutable();
                this.pages_.set(i, page);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, page);
            }
            return this;
        }

        public Builder setPages(int i, Page.Builder builder) {
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePagesIsMutable();
                this.pages_.set(i, builder.m2109build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m2109build());
            }
            return this;
        }

        public Builder addPages(Page page) {
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                page.getClass();
                ensurePagesIsMutable();
                this.pages_.add(page);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(page);
            }
            return this;
        }

        public Builder addPages(int i, Page page) {
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                page.getClass();
                ensurePagesIsMutable();
                this.pages_.add(i, page);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, page);
            }
            return this;
        }

        public Builder addPages(Page.Builder builder) {
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePagesIsMutable();
                this.pages_.add(builder.m2109build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m2109build());
            }
            return this;
        }

        public Builder addPages(int i, Page.Builder builder) {
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePagesIsMutable();
                this.pages_.add(i, builder.m2109build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m2109build());
            }
            return this;
        }

        public Builder addAllPages(Iterable<? extends Page> iterable) {
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePagesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.pages_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearPages() {
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.pages_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removePages(int i) {
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePagesIsMutable();
                this.pages_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Page.Builder getPagesBuilder(int i) {
            return getPagesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.DocumentationOrBuilder
        public PageOrBuilder getPagesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.pages_.get(i);
            }
            return (PageOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.DocumentationOrBuilder
        public List<? extends PageOrBuilder> getPagesOrBuilderList() {
            RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.pages_);
        }

        public Page.Builder addPagesBuilder() {
            return getPagesFieldBuilder().addBuilder(Page.getDefaultInstance());
        }

        public Page.Builder addPagesBuilder(int i) {
            return getPagesFieldBuilder().addBuilder(i, Page.getDefaultInstance());
        }

        public List<Page.Builder> getPagesBuilderList() {
            return getPagesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Page, Page.Builder, PageOrBuilder> getPagesFieldBuilder() {
            if (this.pagesBuilder_ == null) {
                this.pagesBuilder_ = new RepeatedFieldBuilderV3<>(this.pages_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.pages_ = null;
            }
            return this.pagesBuilder_;
        }

        private void ensureRulesIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.rules_ = new ArrayList(this.rules_);
                this.bitField0_ |= 4;
            }
        }

        @Override // com.google.api.DocumentationOrBuilder
        public List<DocumentationRule> getRulesList() {
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.rules_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.DocumentationOrBuilder
        public int getRulesCount() {
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.DocumentationOrBuilder
        public DocumentationRule getRules(int i) {
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRules(int i, DocumentationRule documentationRule) {
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                documentationRule.getClass();
                ensureRulesIsMutable();
                this.rules_.set(i, documentationRule);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, documentationRule);
            }
            return this;
        }

        public Builder setRules(int i, DocumentationRule.Builder builder) {
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.set(i, builder.m1226build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m1226build());
            }
            return this;
        }

        public Builder addRules(DocumentationRule documentationRule) {
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                documentationRule.getClass();
                ensureRulesIsMutable();
                this.rules_.add(documentationRule);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(documentationRule);
            }
            return this;
        }

        public Builder addRules(int i, DocumentationRule documentationRule) {
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                documentationRule.getClass();
                ensureRulesIsMutable();
                this.rules_.add(i, documentationRule);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, documentationRule);
            }
            return this;
        }

        public Builder addRules(DocumentationRule.Builder builder) {
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(builder.m1226build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m1226build());
            }
            return this;
        }

        public Builder addRules(int i, DocumentationRule.Builder builder) {
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(i, builder.m1226build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m1226build());
            }
            return this;
        }

        public Builder addAllRules(Iterable<? extends DocumentationRule> iterable) {
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.rules_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRules() {
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRules(int i) {
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public DocumentationRule.Builder getRulesBuilder(int i) {
            return getRulesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.DocumentationOrBuilder
        public DocumentationRuleOrBuilder getRulesOrBuilder(int i) {
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.get(i);
            }
            return (DocumentationRuleOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.DocumentationOrBuilder
        public List<? extends DocumentationRuleOrBuilder> getRulesOrBuilderList() {
            RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rules_);
        }

        public DocumentationRule.Builder addRulesBuilder() {
            return getRulesFieldBuilder().addBuilder(DocumentationRule.getDefaultInstance());
        }

        public DocumentationRule.Builder addRulesBuilder(int i) {
            return getRulesFieldBuilder().addBuilder(i, DocumentationRule.getDefaultInstance());
        }

        public List<DocumentationRule.Builder> getRulesBuilderList() {
            return getRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<DocumentationRule, DocumentationRule.Builder, DocumentationRuleOrBuilder> getRulesFieldBuilder() {
            if (this.rulesBuilder_ == null) {
                this.rulesBuilder_ = new RepeatedFieldBuilderV3<>(this.rules_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                this.rules_ = null;
            }
            return this.rulesBuilder_;
        }

        @Override // com.google.api.DocumentationOrBuilder
        public String getDocumentationRootUrl() {
            Object obj = this.documentationRootUrl_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.documentationRootUrl_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setDocumentationRootUrl(String str) {
            str.getClass();
            this.documentationRootUrl_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.DocumentationOrBuilder
        public ByteString getDocumentationRootUrlBytes() {
            Object obj = this.documentationRootUrl_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.documentationRootUrl_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDocumentationRootUrlBytes(ByteString byteString) {
            byteString.getClass();
            Documentation.checkByteStringIsUtf8(byteString);
            this.documentationRootUrl_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearDocumentationRootUrl() {
            this.documentationRootUrl_ = Documentation.getDefaultInstance().getDocumentationRootUrl();
            onChanged();
            return this;
        }

        @Override // com.google.api.DocumentationOrBuilder
        public String getOverview() {
            Object obj = this.overview_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.overview_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setOverview(String str) {
            str.getClass();
            this.overview_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.DocumentationOrBuilder
        public ByteString getOverviewBytes() {
            Object obj = this.overview_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.overview_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setOverviewBytes(ByteString byteString) {
            byteString.getClass();
            Documentation.checkByteStringIsUtf8(byteString);
            this.overview_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearOverview() {
            this.overview_ = Documentation.getDefaultInstance().getOverview();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1214setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1208mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
