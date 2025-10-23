package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2;

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
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AccessLogFilter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public final class AndFilter extends GeneratedMessageV3 implements AndFilterOrBuilder {
    public static final int FILTERS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final AndFilter DEFAULT_INSTANCE = new AndFilter();
    private static final Parser<AndFilter> PARSER = new AbstractParser<AndFilter>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilter.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public AndFilter m25171parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AndFilter(codedInputStream, extensionRegistryLite);
        }
    };
    private List<AccessLogFilter> filters_;
    private byte memoizedIsInitialized;

    private AndFilter(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private AndFilter() {
        this.memoizedIsInitialized = (byte) -1;
        this.filters_ = Collections.emptyList();
    }

    private AndFilter(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.filters_ = new ArrayList();
                                z2 |= true;
                            }
                            this.filters_.add(codedInputStream.readMessage(AccessLogFilter.parser(), extensionRegistryLite));
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
                    this.filters_ = Collections.unmodifiableList(this.filters_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static AndFilter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AndFilter> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_AndFilter_descriptor;
    }

    public static AndFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AndFilter) PARSER.parseFrom(byteBuffer);
    }

    public static AndFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AndFilter) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AndFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AndFilter) PARSER.parseFrom(byteString);
    }

    public static AndFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AndFilter) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AndFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AndFilter) PARSER.parseFrom(bArr);
    }

    public static AndFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AndFilter) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AndFilter parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AndFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AndFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AndFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AndFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AndFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m25169toBuilder();
    }

    public static Builder newBuilder(AndFilter andFilter) {
        return DEFAULT_INSTANCE.m25169toBuilder().mergeFrom(andFilter);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public AndFilter m25164getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilterOrBuilder
    public List<AccessLogFilter> getFiltersList() {
        return this.filters_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilterOrBuilder
    public List<? extends AccessLogFilterOrBuilder> getFiltersOrBuilderList() {
        return this.filters_;
    }

    public Parser<AndFilter> getParserForType() {
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
        return new AndFilter();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_AndFilter_fieldAccessorTable.ensureFieldAccessorsInitialized(AndFilter.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilterOrBuilder
    public int getFiltersCount() {
        return this.filters_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilterOrBuilder
    public AccessLogFilter getFilters(int i) {
        return this.filters_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilterOrBuilder
    public AccessLogFilterOrBuilder getFiltersOrBuilder(int i) {
        return this.filters_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.filters_.size(); i++) {
            codedOutputStream.writeMessage(1, this.filters_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.filters_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.filters_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AndFilter)) {
            return super.equals(obj);
        }
        AndFilter andFilter = (AndFilter) obj;
        return getFiltersList().equals(andFilter.getFiltersList()) && this.unknownFields.equals(andFilter.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getFiltersCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getFiltersList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m25166newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m25169toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AndFilterOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> filtersBuilder_;
        private List<AccessLogFilter> filters_;

        private Builder() {
            this.filters_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.filters_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_AndFilter_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_AndFilter_fieldAccessorTable.ensureFieldAccessorsInitialized(AndFilter.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (AndFilter.alwaysUseFieldBuilders) {
                getFiltersFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25180clear() {
            super.clear();
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.filters_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_AndFilter_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AndFilter m25193getDefaultInstanceForType() {
            return AndFilter.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AndFilter m25174build() throws UninitializedMessageException {
            AndFilter andFilterM25176buildPartial = m25176buildPartial();
            if (andFilterM25176buildPartial.isInitialized()) {
                return andFilterM25176buildPartial;
            }
            throw newUninitializedMessageException(andFilterM25176buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AndFilter m25176buildPartial() {
            AndFilter andFilter = new AndFilter(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.filters_ = Collections.unmodifiableList(this.filters_);
                    this.bitField0_ &= -2;
                }
                andFilter.filters_ = this.filters_;
            } else {
                andFilter.filters_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return andFilter;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25192clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25204setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25182clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25185clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25206setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25172addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25197mergeFrom(Message message) {
            if (message instanceof AndFilter) {
                return mergeFrom((AndFilter) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AndFilter andFilter) {
            if (andFilter == AndFilter.getDefaultInstance()) {
                return this;
            }
            if (this.filtersBuilder_ == null) {
                if (!andFilter.filters_.isEmpty()) {
                    if (this.filters_.isEmpty()) {
                        this.filters_ = andFilter.filters_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureFiltersIsMutable();
                        this.filters_.addAll(andFilter.filters_);
                    }
                    onChanged();
                }
            } else if (!andFilter.filters_.isEmpty()) {
                if (!this.filtersBuilder_.isEmpty()) {
                    this.filtersBuilder_.addAllMessages(andFilter.filters_);
                } else {
                    this.filtersBuilder_.dispose();
                    this.filtersBuilder_ = null;
                    this.filters_ = andFilter.filters_;
                    this.bitField0_ &= -2;
                    this.filtersBuilder_ = AndFilter.alwaysUseFieldBuilders ? getFiltersFieldBuilder() : null;
                }
            }
            m25202mergeUnknownFields(andFilter.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilter.Builder m25198mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilter.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilter r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilter) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilter r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilter) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilter.Builder.m25198mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilter$Builder");
        }

        private void ensureFiltersIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.filters_ = new ArrayList(this.filters_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilterOrBuilder
        public List<AccessLogFilter> getFiltersList() {
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.filters_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilterOrBuilder
        public int getFiltersCount() {
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.filters_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilterOrBuilder
        public AccessLogFilter getFilters(int i) {
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.filters_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setFilters(int i, AccessLogFilter accessLogFilter) {
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                accessLogFilter.getClass();
                ensureFiltersIsMutable();
                this.filters_.set(i, accessLogFilter);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, accessLogFilter);
            }
            return this;
        }

        public Builder setFilters(int i, AccessLogFilter.Builder builder) {
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFiltersIsMutable();
                this.filters_.set(i, builder.m25128build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m25128build());
            }
            return this;
        }

        public Builder addFilters(AccessLogFilter accessLogFilter) {
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                accessLogFilter.getClass();
                ensureFiltersIsMutable();
                this.filters_.add(accessLogFilter);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(accessLogFilter);
            }
            return this;
        }

        public Builder addFilters(int i, AccessLogFilter accessLogFilter) {
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                accessLogFilter.getClass();
                ensureFiltersIsMutable();
                this.filters_.add(i, accessLogFilter);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, accessLogFilter);
            }
            return this;
        }

        public Builder addFilters(AccessLogFilter.Builder builder) {
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFiltersIsMutable();
                this.filters_.add(builder.m25128build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m25128build());
            }
            return this;
        }

        public Builder addFilters(int i, AccessLogFilter.Builder builder) {
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFiltersIsMutable();
                this.filters_.add(i, builder.m25128build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m25128build());
            }
            return this;
        }

        public Builder addAllFilters(Iterable<? extends AccessLogFilter> iterable) {
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFiltersIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.filters_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearFilters() {
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.filters_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeFilters(int i) {
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFiltersIsMutable();
                this.filters_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public AccessLogFilter.Builder getFiltersBuilder(int i) {
            return getFiltersFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilterOrBuilder
        public AccessLogFilterOrBuilder getFiltersOrBuilder(int i) {
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.filters_.get(i);
            }
            return (AccessLogFilterOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AndFilterOrBuilder
        public List<? extends AccessLogFilterOrBuilder> getFiltersOrBuilderList() {
            RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.filters_);
        }

        public AccessLogFilter.Builder addFiltersBuilder() {
            return getFiltersFieldBuilder().addBuilder(AccessLogFilter.getDefaultInstance());
        }

        public AccessLogFilter.Builder addFiltersBuilder(int i) {
            return getFiltersFieldBuilder().addBuilder(i, AccessLogFilter.getDefaultInstance());
        }

        public List<AccessLogFilter.Builder> getFiltersBuilderList() {
            return getFiltersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> getFiltersFieldBuilder() {
            if (this.filtersBuilder_ == null) {
                this.filtersBuilder_ = new RepeatedFieldBuilderV3<>(this.filters_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.filters_ = null;
            }
            return this.filtersBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m25208setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m25202mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
