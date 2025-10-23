package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class HeaderFilter extends GeneratedMessageV3 implements HeaderFilterOrBuilder {
    public static final int HEADER_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final HeaderFilter DEFAULT_INSTANCE = new HeaderFilter();
    private static final Parser<HeaderFilter> PARSER = new AbstractParser<HeaderFilter>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilter.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public HeaderFilter m25403parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HeaderFilter(codedInputStream, extensionRegistryLite);
        }
    };
    private HeaderMatcher header_;
    private byte memoizedIsInitialized;

    private HeaderFilter(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private HeaderFilter() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private HeaderFilter(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                HeaderMatcher headerMatcher = this.header_;
                                HeaderMatcher.Builder builderM18047toBuilder = headerMatcher != null ? headerMatcher.m18047toBuilder() : null;
                                HeaderMatcher headerMatcher2 = (HeaderMatcher) codedInputStream.readMessage(HeaderMatcher.parser(), extensionRegistryLite);
                                this.header_ = headerMatcher2;
                                if (builderM18047toBuilder != null) {
                                    builderM18047toBuilder.mergeFrom(headerMatcher2);
                                    this.header_ = builderM18047toBuilder.m18054buildPartial();
                                }
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

    public static HeaderFilter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HeaderFilter> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_HeaderFilter_descriptor;
    }

    public static HeaderFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HeaderFilter) PARSER.parseFrom(byteBuffer);
    }

    public static HeaderFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HeaderFilter) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HeaderFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HeaderFilter) PARSER.parseFrom(byteString);
    }

    public static HeaderFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HeaderFilter) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HeaderFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HeaderFilter) PARSER.parseFrom(bArr);
    }

    public static HeaderFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HeaderFilter) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HeaderFilter parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HeaderFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HeaderFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HeaderFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HeaderFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HeaderFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m25401toBuilder();
    }

    public static Builder newBuilder(HeaderFilter headerFilter) {
        return DEFAULT_INSTANCE.m25401toBuilder().mergeFrom(headerFilter);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public HeaderFilter m25396getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<HeaderFilter> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilterOrBuilder
    public boolean hasHeader() {
        return this.header_ != null;
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
        return new HeaderFilter();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_HeaderFilter_fieldAccessorTable.ensureFieldAccessorsInitialized(HeaderFilter.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilterOrBuilder
    public HeaderMatcher getHeader() {
        HeaderMatcher headerMatcher = this.header_;
        return headerMatcher == null ? HeaderMatcher.getDefaultInstance() : headerMatcher;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilterOrBuilder
    public HeaderMatcherOrBuilder getHeaderOrBuilder() {
        return getHeader();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.header_ != null) {
            codedOutputStream.writeMessage(1, getHeader());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = (this.header_ != null ? CodedOutputStream.computeMessageSize(1, getHeader()) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeMessageSize;
        return iComputeMessageSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HeaderFilter)) {
            return super.equals(obj);
        }
        HeaderFilter headerFilter = (HeaderFilter) obj;
        if (hasHeader() != headerFilter.hasHeader()) {
            return false;
        }
        return (!hasHeader() || getHeader().equals(headerFilter.getHeader())) && this.unknownFields.equals(headerFilter.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasHeader()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getHeader().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m25398newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m25401toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HeaderFilterOrBuilder {
        private SingleFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> headerBuilder_;
        private HeaderMatcher header_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_HeaderFilter_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilterOrBuilder
        public boolean hasHeader() {
            return (this.headerBuilder_ == null && this.header_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_HeaderFilter_fieldAccessorTable.ensureFieldAccessorsInitialized(HeaderFilter.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = HeaderFilter.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25412clear() {
            super.clear();
            if (this.headerBuilder_ == null) {
                this.header_ = null;
            } else {
                this.header_ = null;
                this.headerBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_HeaderFilter_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HeaderFilter m25425getDefaultInstanceForType() {
            return HeaderFilter.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HeaderFilter m25406build() throws UninitializedMessageException {
            HeaderFilter headerFilterM25408buildPartial = m25408buildPartial();
            if (headerFilterM25408buildPartial.isInitialized()) {
                return headerFilterM25408buildPartial;
            }
            throw newUninitializedMessageException(headerFilterM25408buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HeaderFilter m25408buildPartial() {
            HeaderFilter headerFilter = new HeaderFilter(this);
            SingleFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> singleFieldBuilderV3 = this.headerBuilder_;
            if (singleFieldBuilderV3 == null) {
                headerFilter.header_ = this.header_;
            } else {
                headerFilter.header_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return headerFilter;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25424clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25436setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25414clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25417clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25438setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25404addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25429mergeFrom(Message message) {
            if (message instanceof HeaderFilter) {
                return mergeFrom((HeaderFilter) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HeaderFilter headerFilter) {
            if (headerFilter == HeaderFilter.getDefaultInstance()) {
                return this;
            }
            if (headerFilter.hasHeader()) {
                mergeHeader(headerFilter.getHeader());
            }
            m25434mergeUnknownFields(headerFilter.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilter.Builder m25430mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilter.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilter r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilter) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilter r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilter) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilter.Builder.m25430mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilter$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilterOrBuilder
        public HeaderMatcher getHeader() {
            SingleFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> singleFieldBuilderV3 = this.headerBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            HeaderMatcher headerMatcher = this.header_;
            return headerMatcher == null ? HeaderMatcher.getDefaultInstance() : headerMatcher;
        }

        public Builder setHeader(HeaderMatcher headerMatcher) {
            SingleFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> singleFieldBuilderV3 = this.headerBuilder_;
            if (singleFieldBuilderV3 == null) {
                headerMatcher.getClass();
                this.header_ = headerMatcher;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(headerMatcher);
            }
            return this;
        }

        public Builder setHeader(HeaderMatcher.Builder builder) {
            SingleFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> singleFieldBuilderV3 = this.headerBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.header_ = builder.m18052build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m18052build());
            }
            return this;
        }

        public Builder mergeHeader(HeaderMatcher headerMatcher) {
            SingleFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> singleFieldBuilderV3 = this.headerBuilder_;
            if (singleFieldBuilderV3 == null) {
                HeaderMatcher headerMatcher2 = this.header_;
                if (headerMatcher2 != null) {
                    this.header_ = HeaderMatcher.newBuilder(headerMatcher2).mergeFrom(headerMatcher).m18054buildPartial();
                } else {
                    this.header_ = headerMatcher;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(headerMatcher);
            }
            return this;
        }

        public Builder clearHeader() {
            if (this.headerBuilder_ == null) {
                this.header_ = null;
                onChanged();
            } else {
                this.header_ = null;
                this.headerBuilder_ = null;
            }
            return this;
        }

        public HeaderMatcher.Builder getHeaderBuilder() {
            onChanged();
            return getHeaderFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.HeaderFilterOrBuilder
        public HeaderMatcherOrBuilder getHeaderOrBuilder() {
            SingleFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> singleFieldBuilderV3 = this.headerBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (HeaderMatcherOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            HeaderMatcher headerMatcher = this.header_;
            return headerMatcher == null ? HeaderMatcher.getDefaultInstance() : headerMatcher;
        }

        private SingleFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> getHeaderFieldBuilder() {
            if (this.headerBuilder_ == null) {
                this.headerBuilder_ = new SingleFieldBuilderV3<>(getHeader(), getParentForChildren(), isClean());
                this.header_ = null;
            }
            return this.headerBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m25440setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m25434mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
