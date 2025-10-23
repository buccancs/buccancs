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

/* loaded from: classes6.dex */
public final class ResponseFlagFilter extends GeneratedMessageV3 implements ResponseFlagFilterOrBuilder {
    public static final int FLAGS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final ResponseFlagFilter DEFAULT_INSTANCE = new ResponseFlagFilter();
    private static final Parser<ResponseFlagFilter> PARSER = new AbstractParser<ResponseFlagFilter>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilter.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ResponseFlagFilter m25542parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ResponseFlagFilter(codedInputStream, extensionRegistryLite);
        }
    };
    private LazyStringList flags_;
    private byte memoizedIsInitialized;

    private ResponseFlagFilter(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ResponseFlagFilter() {
        this.memoizedIsInitialized = (byte) -1;
        this.flags_ = LazyStringArrayList.EMPTY;
    }

    private ResponseFlagFilter(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if (!(z2 & true)) {
                                this.flags_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.flags_.add(stringRequireUtf8);
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
                    this.flags_ = this.flags_.getUnmodifiableView();
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ResponseFlagFilter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ResponseFlagFilter> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_ResponseFlagFilter_descriptor;
    }

    public static ResponseFlagFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ResponseFlagFilter) PARSER.parseFrom(byteBuffer);
    }

    public static ResponseFlagFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResponseFlagFilter) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ResponseFlagFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ResponseFlagFilter) PARSER.parseFrom(byteString);
    }

    public static ResponseFlagFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResponseFlagFilter) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ResponseFlagFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ResponseFlagFilter) PARSER.parseFrom(bArr);
    }

    public static ResponseFlagFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResponseFlagFilter) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ResponseFlagFilter parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ResponseFlagFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ResponseFlagFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ResponseFlagFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ResponseFlagFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ResponseFlagFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m25540toBuilder();
    }

    public static Builder newBuilder(ResponseFlagFilter responseFlagFilter) {
        return DEFAULT_INSTANCE.m25540toBuilder().mergeFrom(responseFlagFilter);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ResponseFlagFilter m25534getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilterOrBuilder
    /* renamed from: getFlagsList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo25536getFlagsList() {
        return this.flags_;
    }

    public Parser<ResponseFlagFilter> getParserForType() {
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
        return new ResponseFlagFilter();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_ResponseFlagFilter_fieldAccessorTable.ensureFieldAccessorsInitialized(ResponseFlagFilter.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilterOrBuilder
    public int getFlagsCount() {
        return this.flags_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilterOrBuilder
    public String getFlags(int i) {
        return (String) this.flags_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilterOrBuilder
    public ByteString getFlagsBytes(int i) {
        return this.flags_.getByteString(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.flags_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.flags_.getRaw(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.flags_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.flags_.getRaw(i2));
        }
        int size = iComputeStringSizeNoTag + mo25536getFlagsList().size() + this.unknownFields.getSerializedSize();
        this.memoizedSize = size;
        return size;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResponseFlagFilter)) {
            return super.equals(obj);
        }
        ResponseFlagFilter responseFlagFilter = (ResponseFlagFilter) obj;
        return mo25536getFlagsList().equals(responseFlagFilter.mo25536getFlagsList()) && this.unknownFields.equals(responseFlagFilter.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getFlagsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + mo25536getFlagsList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m25537newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m25540toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ResponseFlagFilterOrBuilder {
        private int bitField0_;
        private LazyStringList flags_;

        private Builder() {
            this.flags_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.flags_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_ResponseFlagFilter_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_ResponseFlagFilter_fieldAccessorTable.ensureFieldAccessorsInitialized(ResponseFlagFilter.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ResponseFlagFilter.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25551clear() {
            super.clear();
            this.flags_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_ResponseFlagFilter_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ResponseFlagFilter m25564getDefaultInstanceForType() {
            return ResponseFlagFilter.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ResponseFlagFilter m25545build() throws UninitializedMessageException {
            ResponseFlagFilter responseFlagFilterM25547buildPartial = m25547buildPartial();
            if (responseFlagFilterM25547buildPartial.isInitialized()) {
                return responseFlagFilterM25547buildPartial;
            }
            throw newUninitializedMessageException(responseFlagFilterM25547buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ResponseFlagFilter m25547buildPartial() {
            ResponseFlagFilter responseFlagFilter = new ResponseFlagFilter(this);
            if ((this.bitField0_ & 1) != 0) {
                this.flags_ = this.flags_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            responseFlagFilter.flags_ = this.flags_;
            onBuilt();
            return responseFlagFilter;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25563clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25575setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25553clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25556clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25577setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25543addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25568mergeFrom(Message message) {
            if (message instanceof ResponseFlagFilter) {
                return mergeFrom((ResponseFlagFilter) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ResponseFlagFilter responseFlagFilter) {
            if (responseFlagFilter == ResponseFlagFilter.getDefaultInstance()) {
                return this;
            }
            if (!responseFlagFilter.flags_.isEmpty()) {
                if (this.flags_.isEmpty()) {
                    this.flags_ = responseFlagFilter.flags_;
                    this.bitField0_ &= -2;
                } else {
                    ensureFlagsIsMutable();
                    this.flags_.addAll(responseFlagFilter.flags_);
                }
                onChanged();
            }
            m25573mergeUnknownFields(responseFlagFilter.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilter.Builder m25569mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilter.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilter r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilter) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilter r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilter) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilter.Builder.m25569mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilter$Builder");
        }

        private void ensureFlagsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.flags_ = new LazyStringArrayList(this.flags_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilterOrBuilder
        /* renamed from: getFlagsList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo25536getFlagsList() {
            return this.flags_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilterOrBuilder
        public int getFlagsCount() {
            return this.flags_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilterOrBuilder
        public String getFlags(int i) {
            return (String) this.flags_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ResponseFlagFilterOrBuilder
        public ByteString getFlagsBytes(int i) {
            return this.flags_.getByteString(i);
        }

        public Builder setFlags(int i, String str) {
            str.getClass();
            ensureFlagsIsMutable();
            this.flags_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addFlags(String str) {
            str.getClass();
            ensureFlagsIsMutable();
            this.flags_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllFlags(Iterable<String> iterable) {
            ensureFlagsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.flags_);
            onChanged();
            return this;
        }

        public Builder clearFlags() {
            this.flags_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addFlagsBytes(ByteString byteString) {
            byteString.getClass();
            ResponseFlagFilter.checkByteStringIsUtf8(byteString);
            ensureFlagsIsMutable();
            this.flags_.add(byteString);
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m25579setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m25573mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
