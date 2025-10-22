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
public final class Page extends GeneratedMessageV3 implements PageOrBuilder {
    public static final int CONTENT_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int SUBPAGES_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final Page DEFAULT_INSTANCE = new Page();
    private static final Parser<Page> PARSER = new AbstractParser<Page>() { // from class: com.google.api.Page.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Page m2105parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Page(codedInputStream, extensionRegistryLite);
        }
    };
    private int bitField0_;
    private volatile Object content_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private List<Page> subpages_;

    private Page(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Page() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.content_ = "";
        this.subpages_ = Collections.emptyList();
    }

    private Page(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.content_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 26) {
                                if ((i & 4) == 0) {
                                    this.subpages_ = new ArrayList();
                                    i |= 4;
                                }
                                this.subpages_.add(codedInputStream.readMessage(parser(), extensionRegistryLite));
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
                if ((i & 4) != 0) {
                    this.subpages_ = Collections.unmodifiableList(this.subpages_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static Page getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Page> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DocumentationProto.internal_static_google_api_Page_descriptor;
    }

    public static Page parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Page) PARSER.parseFrom(byteBuffer);
    }

    public static Page parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Page) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Page parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Page) PARSER.parseFrom(byteString);
    }

    public static Page parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Page) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Page parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Page) PARSER.parseFrom(bArr);
    }

    public static Page parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Page) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Page parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Page parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Page parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Page parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Page parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Page parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m2104toBuilder();
    }

    public static Builder newBuilder(Page page) {
        return DEFAULT_INSTANCE.m2104toBuilder().mergeFrom(page);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Page m2099getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Page> getParserForType() {
        return PARSER;
    }

    @Override // com.google.api.PageOrBuilder
    public List<Page> getSubpagesList() {
        return this.subpages_;
    }

    @Override // com.google.api.PageOrBuilder
    public List<? extends PageOrBuilder> getSubpagesOrBuilderList() {
        return this.subpages_;
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
        return DocumentationProto.internal_static_google_api_Page_fieldAccessorTable.ensureFieldAccessorsInitialized(Page.class, Builder.class);
    }

    @Override // com.google.api.PageOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.PageOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.PageOrBuilder
    public String getContent() {
        Object obj = this.content_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.content_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.PageOrBuilder
    public ByteString getContentBytes() {
        Object obj = this.content_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.content_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.PageOrBuilder
    public int getSubpagesCount() {
        return this.subpages_.size();
    }

    @Override // com.google.api.PageOrBuilder
    public Page getSubpages(int i) {
        return this.subpages_.get(i);
    }

    @Override // com.google.api.PageOrBuilder
    public PageOrBuilder getSubpagesOrBuilder(int i) {
        return this.subpages_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (!getContentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.content_);
        }
        for (int i = 0; i < this.subpages_.size(); i++) {
            codedOutputStream.writeMessage(3, this.subpages_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (!getContentBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.content_);
        }
        for (int i2 = 0; i2 < this.subpages_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, this.subpages_.get(i2));
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Page)) {
            return super.equals(obj);
        }
        Page page = (Page) obj;
        return getName().equals(page.getName()) && getContent().equals(page.getContent()) && getSubpagesList().equals(page.getSubpagesList()) && this.unknownFields.equals(page.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getContent().hashCode();
        if (getSubpagesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getSubpagesList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m2102newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m2104toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m2101newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PageOrBuilder {
        private int bitField0_;
        private Object content_;
        private Object name_;
        private RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> subpagesBuilder_;
        private List<Page> subpages_;

        private Builder() {
            this.name_ = "";
            this.content_ = "";
            this.subpages_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.content_ = "";
            this.subpages_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DocumentationProto.internal_static_google_api_Page_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentationProto.internal_static_google_api_Page_fieldAccessorTable.ensureFieldAccessorsInitialized(Page.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (Page.alwaysUseFieldBuilders) {
                getSubpagesFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2115clear() {
            super.clear();
            this.name_ = "";
            this.content_ = "";
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.subpages_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return DocumentationProto.internal_static_google_api_Page_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Page m2128getDefaultInstanceForType() {
            return Page.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Page m2109build() throws UninitializedMessageException {
            Page pageM2111buildPartial = m2111buildPartial();
            if (pageM2111buildPartial.isInitialized()) {
                return pageM2111buildPartial;
            }
            throw newUninitializedMessageException(pageM2111buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Page m2111buildPartial() {
            Page page = new Page(this);
            page.name_ = this.name_;
            page.content_ = this.content_;
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 4) != 0) {
                    this.subpages_ = Collections.unmodifiableList(this.subpages_);
                    this.bitField0_ &= -5;
                }
                page.subpages_ = this.subpages_;
            } else {
                page.subpages_ = repeatedFieldBuilderV3.build();
            }
            page.bitField0_ = 0;
            onBuilt();
            return page;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2126clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2139setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2117clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2120clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2141setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2107addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2133mergeFrom(Message message) {
            if (message instanceof Page) {
                return mergeFrom((Page) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Page page) {
            if (page == Page.getDefaultInstance()) {
                return this;
            }
            if (!page.getName().isEmpty()) {
                this.name_ = page.name_;
                onChanged();
            }
            if (!page.getContent().isEmpty()) {
                this.content_ = page.content_;
                onChanged();
            }
            if (this.subpagesBuilder_ == null) {
                if (!page.subpages_.isEmpty()) {
                    if (this.subpages_.isEmpty()) {
                        this.subpages_ = page.subpages_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureSubpagesIsMutable();
                        this.subpages_.addAll(page.subpages_);
                    }
                    onChanged();
                }
            } else if (!page.subpages_.isEmpty()) {
                if (!this.subpagesBuilder_.isEmpty()) {
                    this.subpagesBuilder_.addAllMessages(page.subpages_);
                } else {
                    this.subpagesBuilder_.dispose();
                    this.subpagesBuilder_ = null;
                    this.subpages_ = page.subpages_;
                    this.bitField0_ &= -5;
                    this.subpagesBuilder_ = Page.alwaysUseFieldBuilders ? getSubpagesFieldBuilder() : null;
                }
            }
            m2137mergeUnknownFields(page.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.api.Page.Builder m2134mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Page.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.Page r3 = (com.google.api.Page) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.Page r4 = (com.google.api.Page) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Page.Builder.m2134mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Page$Builder");
        }

        @Override // com.google.api.PageOrBuilder
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

        @Override // com.google.api.PageOrBuilder
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
            Page.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = Page.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // com.google.api.PageOrBuilder
        public String getContent() {
            Object obj = this.content_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.content_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setContent(String str) {
            str.getClass();
            this.content_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.PageOrBuilder
        public ByteString getContentBytes() {
            Object obj = this.content_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.content_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setContentBytes(ByteString byteString) {
            byteString.getClass();
            Page.checkByteStringIsUtf8(byteString);
            this.content_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearContent() {
            this.content_ = Page.getDefaultInstance().getContent();
            onChanged();
            return this;
        }

        private void ensureSubpagesIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.subpages_ = new ArrayList(this.subpages_);
                this.bitField0_ |= 4;
            }
        }

        @Override // com.google.api.PageOrBuilder
        public List<Page> getSubpagesList() {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.subpages_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.PageOrBuilder
        public int getSubpagesCount() {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.subpages_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.PageOrBuilder
        public Page getSubpages(int i) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.subpages_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setSubpages(int i, Page page) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                page.getClass();
                ensureSubpagesIsMutable();
                this.subpages_.set(i, page);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, page);
            }
            return this;
        }

        public Builder setSubpages(int i, Builder builder) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                this.subpages_.set(i, builder.m2109build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m2109build());
            }
            return this;
        }

        public Builder addSubpages(Page page) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                page.getClass();
                ensureSubpagesIsMutable();
                this.subpages_.add(page);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(page);
            }
            return this;
        }

        public Builder addSubpages(int i, Page page) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                page.getClass();
                ensureSubpagesIsMutable();
                this.subpages_.add(i, page);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, page);
            }
            return this;
        }

        public Builder addSubpages(Builder builder) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                this.subpages_.add(builder.m2109build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m2109build());
            }
            return this;
        }

        public Builder addSubpages(int i, Builder builder) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                this.subpages_.add(i, builder.m2109build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m2109build());
            }
            return this;
        }

        public Builder addAllSubpages(Iterable<? extends Page> iterable) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.subpages_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearSubpages() {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.subpages_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeSubpages(int i) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                this.subpages_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Builder getSubpagesBuilder(int i) {
            return getSubpagesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.PageOrBuilder
        public PageOrBuilder getSubpagesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.subpages_.get(i);
            }
            return (PageOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.PageOrBuilder
        public List<? extends PageOrBuilder> getSubpagesOrBuilderList() {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.subpages_);
        }

        public Builder addSubpagesBuilder() {
            return getSubpagesFieldBuilder().addBuilder(Page.getDefaultInstance());
        }

        public Builder addSubpagesBuilder(int i) {
            return getSubpagesFieldBuilder().addBuilder(i, Page.getDefaultInstance());
        }

        public List<Builder> getSubpagesBuilderList() {
            return getSubpagesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> getSubpagesFieldBuilder() {
            if (this.subpagesBuilder_ == null) {
                this.subpagesBuilder_ = new RepeatedFieldBuilderV3<>(this.subpages_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                this.subpages_ = null;
            }
            return this.subpagesBuilder_;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m2143setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m2137mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
