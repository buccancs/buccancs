package com.google.rpc;

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
import com.google.protobuf.MessageOrBuilder;
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

/* loaded from: classes2.dex */
public final class Help extends GeneratedMessageV3 implements HelpOrBuilder {
    public static final int LINKS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final Help DEFAULT_INSTANCE = new Help();
    private static final Parser<Help> PARSER = new AbstractParser<Help>() { // from class: com.google.rpc.Help.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Help m3539parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Help(codedInputStream, extensionRegistryLite);
        }
    };
    private List<Link> links_;
    private byte memoizedIsInitialized;

    private Help(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Help() {
        this.memoizedIsInitialized = (byte) -1;
        this.links_ = Collections.emptyList();
    }

    private Help(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (!(z2 & true)) {
                                this.links_ = new ArrayList();
                                z2 |= true;
                            }
                            this.links_.add(codedInputStream.readMessage(Link.parser(), extensionRegistryLite));
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
                    this.links_ = Collections.unmodifiableList(this.links_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static Help getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Help> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_Help_descriptor;
    }

    public static Help parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Help) PARSER.parseFrom(byteBuffer);
    }

    public static Help parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Help) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Help parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Help) PARSER.parseFrom(byteString);
    }

    public static Help parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Help) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Help parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Help) PARSER.parseFrom(bArr);
    }

    public static Help parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Help) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Help parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Help parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Help parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Help parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Help parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Help parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3537toBuilder();
    }

    public static Builder newBuilder(Help help) {
        return DEFAULT_INSTANCE.m3537toBuilder().mergeFrom(help);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Help m3532getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.rpc.HelpOrBuilder
    public List<Link> getLinksList() {
        return this.links_;
    }

    @Override // com.google.rpc.HelpOrBuilder
    public List<? extends LinkOrBuilder> getLinksOrBuilderList() {
        return this.links_;
    }

    public Parser<Help> getParserForType() {
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
        return ErrorDetailsProto.internal_static_google_rpc_Help_fieldAccessorTable.ensureFieldAccessorsInitialized(Help.class, Builder.class);
    }

    @Override // com.google.rpc.HelpOrBuilder
    public int getLinksCount() {
        return this.links_.size();
    }

    @Override // com.google.rpc.HelpOrBuilder
    public Link getLinks(int i) {
        return this.links_.get(i);
    }

    @Override // com.google.rpc.HelpOrBuilder
    public LinkOrBuilder getLinksOrBuilder(int i) {
        return this.links_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.links_.size(); i++) {
            codedOutputStream.writeMessage(1, this.links_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.links_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.links_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Help)) {
            return super.equals(obj);
        }
        Help help = (Help) obj;
        return getLinksList().equals(help.getLinksList()) && this.unknownFields.equals(help.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getLinksCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getLinksList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3534newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3537toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public interface LinkOrBuilder extends MessageOrBuilder {
        String getDescription();

        ByteString getDescriptionBytes();

        String getUrl();

        ByteString getUrlBytes();
    }

    public static final class Link extends GeneratedMessageV3 implements LinkOrBuilder {
        public static final int DESCRIPTION_FIELD_NUMBER = 1;
        public static final int URL_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private static final Link DEFAULT_INSTANCE = new Link();
        private static final Parser<Link> PARSER = new AbstractParser<Link>() { // from class: com.google.rpc.Help.Link.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Link m3585parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Link(codedInputStream, extensionRegistryLite);
            }
        };
        private volatile Object description_;
        private byte memoizedIsInitialized;
        private volatile Object url_;

        private Link(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Link() {
            this.memoizedIsInitialized = (byte) -1;
            this.description_ = "";
            this.url_ = "";
        }

        private Link(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.description_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 18) {
                                    this.url_ = codedInputStream.readStringRequireUtf8();
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

        public static Link getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Link> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_Link_descriptor;
        }

        public static Link parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Link) PARSER.parseFrom(byteBuffer);
        }

        public static Link parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Link) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Link parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Link) PARSER.parseFrom(byteString);
        }

        public static Link parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Link) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Link parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Link) PARSER.parseFrom(bArr);
        }

        public static Link parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Link) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Link parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Link parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Link parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Link parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Link parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Link parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m3583toBuilder();
        }

        public static Builder newBuilder(Link link) {
            return DEFAULT_INSTANCE.m3583toBuilder().mergeFrom(link);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Link m3578getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Link> getParserForType() {
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
            return ErrorDetailsProto.internal_static_google_rpc_Help_Link_fieldAccessorTable.ensureFieldAccessorsInitialized(Link.class, Builder.class);
        }

        @Override // com.google.rpc.Help.LinkOrBuilder
        public String getDescription() {
            Object obj = this.description_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.description_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.rpc.Help.LinkOrBuilder
        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.description_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.rpc.Help.LinkOrBuilder
        public String getUrl() {
            Object obj = this.url_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.url_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.rpc.Help.LinkOrBuilder
        public ByteString getUrlBytes() {
            Object obj = this.url_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.url_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getDescriptionBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.description_);
            }
            if (!getUrlBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.url_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getDescriptionBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.description_) : 0;
            if (!getUrlBytes().isEmpty()) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.url_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Link)) {
                return super.equals(obj);
            }
            Link link = (Link) obj;
            return getDescription().equals(link.getDescription()) && getUrl().equals(link.getUrl()) && this.unknownFields.equals(link.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getDescription().hashCode()) * 37) + 2) * 53) + getUrl().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3580newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3583toBuilder() {
            if (this == DEFAULT_INSTANCE) {
                return new Builder();
            }
            return new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LinkOrBuilder {
            private Object description_;
            private Object url_;

            private Builder() {
                this.description_ = "";
                this.url_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.description_ = "";
                this.url_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ErrorDetailsProto.internal_static_google_rpc_Help_Link_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ErrorDetailsProto.internal_static_google_rpc_Help_Link_fieldAccessorTable.ensureFieldAccessorsInitialized(Link.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Link.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m3594clear() {
                super.clear();
                this.description_ = "";
                this.url_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ErrorDetailsProto.internal_static_google_rpc_Help_Link_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Link m3607getDefaultInstanceForType() {
                return Link.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Link m3588build() throws UninitializedMessageException {
                Link linkM3590buildPartial = m3590buildPartial();
                if (linkM3590buildPartial.isInitialized()) {
                    return linkM3590buildPartial;
                }
                throw newUninitializedMessageException(linkM3590buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Link m3590buildPartial() {
                Link link = new Link(this);
                link.description_ = this.description_;
                link.url_ = this.url_;
                onBuilt();
                return link;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m3606clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m3618setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m3596clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m3599clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m3620setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m3586addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m3611mergeFrom(Message message) {
                if (message instanceof Link) {
                    return mergeFrom((Link) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Link link) {
                if (link == Link.getDefaultInstance()) {
                    return this;
                }
                if (!link.getDescription().isEmpty()) {
                    this.description_ = link.description_;
                    onChanged();
                }
                if (!link.getUrl().isEmpty()) {
                    this.url_ = link.url_;
                    onChanged();
                }
                m3616mergeUnknownFields(link.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.google.rpc.Help.Link.Builder m3612mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.rpc.Help.Link.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.google.rpc.Help$Link r3 = (com.google.rpc.Help.Link) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.google.rpc.Help$Link r4 = (com.google.rpc.Help.Link) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.Help.Link.Builder.m3612mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.Help$Link$Builder");
            }

            @Override // com.google.rpc.Help.LinkOrBuilder
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

            @Override // com.google.rpc.Help.LinkOrBuilder
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
                Link.checkByteStringIsUtf8(byteString);
                this.description_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearDescription() {
                this.description_ = Link.getDefaultInstance().getDescription();
                onChanged();
                return this;
            }

            @Override // com.google.rpc.Help.LinkOrBuilder
            public String getUrl() {
                Object obj = this.url_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.url_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setUrl(String str) {
                str.getClass();
                this.url_ = str;
                onChanged();
                return this;
            }

            @Override // com.google.rpc.Help.LinkOrBuilder
            public ByteString getUrlBytes() {
                Object obj = this.url_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.url_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setUrlBytes(ByteString byteString) {
                byteString.getClass();
                Link.checkByteStringIsUtf8(byteString);
                this.url_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearUrl() {
                this.url_ = Link.getDefaultInstance().getUrl();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m3622setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m3616mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HelpOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> linksBuilder_;
        private List<Link> links_;

        private Builder() {
            this.links_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.links_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_fieldAccessorTable.ensureFieldAccessorsInitialized(Help.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (Help.alwaysUseFieldBuilders) {
                getLinksFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3548clear() {
            super.clear();
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.links_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Help m3561getDefaultInstanceForType() {
            return Help.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Help m3542build() throws UninitializedMessageException {
            Help helpM3544buildPartial = m3544buildPartial();
            if (helpM3544buildPartial.isInitialized()) {
                return helpM3544buildPartial;
            }
            throw newUninitializedMessageException(helpM3544buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Help m3544buildPartial() {
            Help help = new Help(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.links_ = Collections.unmodifiableList(this.links_);
                    this.bitField0_ &= -2;
                }
                help.links_ = this.links_;
            } else {
                help.links_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return help;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3560clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3572setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3550clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3553clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3574setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3540addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3565mergeFrom(Message message) {
            if (message instanceof Help) {
                return mergeFrom((Help) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Help help) {
            if (help == Help.getDefaultInstance()) {
                return this;
            }
            if (this.linksBuilder_ == null) {
                if (!help.links_.isEmpty()) {
                    if (this.links_.isEmpty()) {
                        this.links_ = help.links_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureLinksIsMutable();
                        this.links_.addAll(help.links_);
                    }
                    onChanged();
                }
            } else if (!help.links_.isEmpty()) {
                if (!this.linksBuilder_.isEmpty()) {
                    this.linksBuilder_.addAllMessages(help.links_);
                } else {
                    this.linksBuilder_.dispose();
                    this.linksBuilder_ = null;
                    this.links_ = help.links_;
                    this.bitField0_ &= -2;
                    this.linksBuilder_ = Help.alwaysUseFieldBuilders ? getLinksFieldBuilder() : null;
                }
            }
            m3570mergeUnknownFields(help.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.rpc.Help.Builder m3566mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.Help.access$1800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.rpc.Help r3 = (com.google.rpc.Help) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.rpc.Help r4 = (com.google.rpc.Help) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.Help.Builder.m3566mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.Help$Builder");
        }

        private void ensureLinksIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.links_ = new ArrayList(this.links_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.rpc.HelpOrBuilder
        public List<Link> getLinksList() {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.links_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.rpc.HelpOrBuilder
        public int getLinksCount() {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.links_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.rpc.HelpOrBuilder
        public Link getLinks(int i) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.links_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setLinks(int i, Link link) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                link.getClass();
                ensureLinksIsMutable();
                this.links_.set(i, link);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, link);
            }
            return this;
        }

        public Builder setLinks(int i, Link.Builder builder) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                this.links_.set(i, builder.m3588build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m3588build());
            }
            return this;
        }

        public Builder addLinks(Link link) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                link.getClass();
                ensureLinksIsMutable();
                this.links_.add(link);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(link);
            }
            return this;
        }

        public Builder addLinks(int i, Link link) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                link.getClass();
                ensureLinksIsMutable();
                this.links_.add(i, link);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, link);
            }
            return this;
        }

        public Builder addLinks(Link.Builder builder) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                this.links_.add(builder.m3588build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m3588build());
            }
            return this;
        }

        public Builder addLinks(int i, Link.Builder builder) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                this.links_.add(i, builder.m3588build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m3588build());
            }
            return this;
        }

        public Builder addAllLinks(Iterable<? extends Link> iterable) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.links_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearLinks() {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.links_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeLinks(int i) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                this.links_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Link.Builder getLinksBuilder(int i) {
            return getLinksFieldBuilder().getBuilder(i);
        }

        @Override // com.google.rpc.HelpOrBuilder
        public LinkOrBuilder getLinksOrBuilder(int i) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.links_.get(i);
            }
            return (LinkOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.rpc.HelpOrBuilder
        public List<? extends LinkOrBuilder> getLinksOrBuilderList() {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.links_);
        }

        public Link.Builder addLinksBuilder() {
            return getLinksFieldBuilder().addBuilder(Link.getDefaultInstance());
        }

        public Link.Builder addLinksBuilder(int i) {
            return getLinksFieldBuilder().addBuilder(i, Link.getDefaultInstance());
        }

        public List<Link.Builder> getLinksBuilderList() {
            return getLinksFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> getLinksFieldBuilder() {
            if (this.linksBuilder_ == null) {
                this.linksBuilder_ = new RepeatedFieldBuilderV3<>(this.links_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.links_ = null;
            }
            return this.linksBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3576setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3570mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
