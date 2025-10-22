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
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ComparisonFilter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class DurationFilter extends GeneratedMessageV3 implements DurationFilterOrBuilder {
    public static final int COMPARISON_FIELD_NUMBER = 1;
    private static final DurationFilter DEFAULT_INSTANCE = new DurationFilter();
    private static final Parser<DurationFilter> PARSER = new AbstractParser<DurationFilter>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilter.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public DurationFilter m25264parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DurationFilter(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private ComparisonFilter comparison_;
    private byte memoizedIsInitialized;

    private DurationFilter(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DurationFilter() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private DurationFilter(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                ComparisonFilter comparisonFilter = this.comparison_;
                                ComparisonFilter.Builder builderM25215toBuilder = comparisonFilter != null ? comparisonFilter.m25215toBuilder() : null;
                                ComparisonFilter comparisonFilter2 = (ComparisonFilter) codedInputStream.readMessage(ComparisonFilter.parser(), extensionRegistryLite);
                                this.comparison_ = comparisonFilter2;
                                if (builderM25215toBuilder != null) {
                                    builderM25215toBuilder.mergeFrom(comparisonFilter2);
                                    this.comparison_ = builderM25215toBuilder.m25222buildPartial();
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

    public static DurationFilter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DurationFilter> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_DurationFilter_descriptor;
    }

    public static DurationFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DurationFilter) PARSER.parseFrom(byteBuffer);
    }

    public static DurationFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DurationFilter) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DurationFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DurationFilter) PARSER.parseFrom(byteString);
    }

    public static DurationFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DurationFilter) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DurationFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DurationFilter) PARSER.parseFrom(bArr);
    }

    public static DurationFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DurationFilter) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DurationFilter parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DurationFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DurationFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DurationFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DurationFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DurationFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m25262toBuilder();
    }

    public static Builder newBuilder(DurationFilter durationFilter) {
        return DEFAULT_INSTANCE.m25262toBuilder().mergeFrom(durationFilter);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public DurationFilter m25257getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<DurationFilter> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilterOrBuilder
    public boolean hasComparison() {
        return this.comparison_ != null;
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
        return new DurationFilter();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_DurationFilter_fieldAccessorTable.ensureFieldAccessorsInitialized(DurationFilter.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilterOrBuilder
    public ComparisonFilter getComparison() {
        ComparisonFilter comparisonFilter = this.comparison_;
        return comparisonFilter == null ? ComparisonFilter.getDefaultInstance() : comparisonFilter;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilterOrBuilder
    public ComparisonFilterOrBuilder getComparisonOrBuilder() {
        return getComparison();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.comparison_ != null) {
            codedOutputStream.writeMessage(1, getComparison());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = (this.comparison_ != null ? CodedOutputStream.computeMessageSize(1, getComparison()) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeMessageSize;
        return iComputeMessageSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DurationFilter)) {
            return super.equals(obj);
        }
        DurationFilter durationFilter = (DurationFilter) obj;
        if (hasComparison() != durationFilter.hasComparison()) {
            return false;
        }
        return (!hasComparison() || getComparison().equals(durationFilter.getComparison())) && this.unknownFields.equals(durationFilter.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasComparison()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getComparison().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m25259newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m25262toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DurationFilterOrBuilder {
        private SingleFieldBuilderV3<ComparisonFilter, ComparisonFilter.Builder, ComparisonFilterOrBuilder> comparisonBuilder_;
        private ComparisonFilter comparison_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_DurationFilter_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilterOrBuilder
        public boolean hasComparison() {
            return (this.comparisonBuilder_ == null && this.comparison_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_DurationFilter_fieldAccessorTable.ensureFieldAccessorsInitialized(DurationFilter.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = DurationFilter.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25273clear() {
            super.clear();
            if (this.comparisonBuilder_ == null) {
                this.comparison_ = null;
            } else {
                this.comparison_ = null;
                this.comparisonBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_DurationFilter_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DurationFilter m25286getDefaultInstanceForType() {
            return DurationFilter.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DurationFilter m25267build() throws UninitializedMessageException {
            DurationFilter durationFilterM25269buildPartial = m25269buildPartial();
            if (durationFilterM25269buildPartial.isInitialized()) {
                return durationFilterM25269buildPartial;
            }
            throw newUninitializedMessageException(durationFilterM25269buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DurationFilter m25269buildPartial() {
            DurationFilter durationFilter = new DurationFilter(this);
            SingleFieldBuilderV3<ComparisonFilter, ComparisonFilter.Builder, ComparisonFilterOrBuilder> singleFieldBuilderV3 = this.comparisonBuilder_;
            if (singleFieldBuilderV3 == null) {
                durationFilter.comparison_ = this.comparison_;
            } else {
                durationFilter.comparison_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return durationFilter;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25285clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25297setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25275clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25278clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25299setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25265addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25290mergeFrom(Message message) {
            if (message instanceof DurationFilter) {
                return mergeFrom((DurationFilter) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DurationFilter durationFilter) {
            if (durationFilter == DurationFilter.getDefaultInstance()) {
                return this;
            }
            if (durationFilter.hasComparison()) {
                mergeComparison(durationFilter.getComparison());
            }
            m25295mergeUnknownFields(durationFilter.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilter.Builder m25291mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilter.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilter r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilter) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilter r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilter) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilter.Builder.m25291mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilter$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilterOrBuilder
        public ComparisonFilter getComparison() {
            SingleFieldBuilderV3<ComparisonFilter, ComparisonFilter.Builder, ComparisonFilterOrBuilder> singleFieldBuilderV3 = this.comparisonBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            ComparisonFilter comparisonFilter = this.comparison_;
            return comparisonFilter == null ? ComparisonFilter.getDefaultInstance() : comparisonFilter;
        }

        public Builder setComparison(ComparisonFilter comparisonFilter) {
            SingleFieldBuilderV3<ComparisonFilter, ComparisonFilter.Builder, ComparisonFilterOrBuilder> singleFieldBuilderV3 = this.comparisonBuilder_;
            if (singleFieldBuilderV3 == null) {
                comparisonFilter.getClass();
                this.comparison_ = comparisonFilter;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(comparisonFilter);
            }
            return this;
        }

        public Builder setComparison(ComparisonFilter.Builder builder) {
            SingleFieldBuilderV3<ComparisonFilter, ComparisonFilter.Builder, ComparisonFilterOrBuilder> singleFieldBuilderV3 = this.comparisonBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.comparison_ = builder.m25220build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m25220build());
            }
            return this;
        }

        public Builder mergeComparison(ComparisonFilter comparisonFilter) {
            SingleFieldBuilderV3<ComparisonFilter, ComparisonFilter.Builder, ComparisonFilterOrBuilder> singleFieldBuilderV3 = this.comparisonBuilder_;
            if (singleFieldBuilderV3 == null) {
                ComparisonFilter comparisonFilter2 = this.comparison_;
                if (comparisonFilter2 != null) {
                    this.comparison_ = ComparisonFilter.newBuilder(comparisonFilter2).mergeFrom(comparisonFilter).m25222buildPartial();
                } else {
                    this.comparison_ = comparisonFilter;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(comparisonFilter);
            }
            return this;
        }

        public Builder clearComparison() {
            if (this.comparisonBuilder_ == null) {
                this.comparison_ = null;
                onChanged();
            } else {
                this.comparison_ = null;
                this.comparisonBuilder_ = null;
            }
            return this;
        }

        public ComparisonFilter.Builder getComparisonBuilder() {
            onChanged();
            return getComparisonFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.DurationFilterOrBuilder
        public ComparisonFilterOrBuilder getComparisonOrBuilder() {
            SingleFieldBuilderV3<ComparisonFilter, ComparisonFilter.Builder, ComparisonFilterOrBuilder> singleFieldBuilderV3 = this.comparisonBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ComparisonFilterOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            ComparisonFilter comparisonFilter = this.comparison_;
            return comparisonFilter == null ? ComparisonFilter.getDefaultInstance() : comparisonFilter;
        }

        private SingleFieldBuilderV3<ComparisonFilter, ComparisonFilter.Builder, ComparisonFilterOrBuilder> getComparisonFieldBuilder() {
            if (this.comparisonBuilder_ == null) {
                this.comparisonBuilder_ = new SingleFieldBuilderV3<>(getComparison(), getParentForChildren(), isClean());
                this.comparison_ = null;
            }
            return this.comparisonBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m25301setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m25295mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
