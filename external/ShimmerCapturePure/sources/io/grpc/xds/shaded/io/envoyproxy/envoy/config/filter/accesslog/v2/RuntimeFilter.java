package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercentOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class RuntimeFilter extends GeneratedMessageV3 implements RuntimeFilterOrBuilder {
    public static final int PERCENT_SAMPLED_FIELD_NUMBER = 2;
    public static final int RUNTIME_KEY_FIELD_NUMBER = 1;
    public static final int USE_INDEPENDENT_RANDOMNESS_FIELD_NUMBER = 3;
    private static final RuntimeFilter DEFAULT_INSTANCE = new RuntimeFilter();
    private static final Parser<RuntimeFilter> PARSER = new AbstractParser<RuntimeFilter>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilter.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public RuntimeFilter m25588parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RuntimeFilter(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private FractionalPercent percentSampled_;
    private volatile Object runtimeKey_;
    private boolean useIndependentRandomness_;

    private RuntimeFilter(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private RuntimeFilter() {
        this.memoizedIsInitialized = (byte) -1;
        this.runtimeKey_ = "";
    }

    private RuntimeFilter(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.runtimeKey_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            FractionalPercent fractionalPercent = this.percentSampled_;
                            FractionalPercent.Builder builderM32859toBuilder = fractionalPercent != null ? fractionalPercent.m32859toBuilder() : null;
                            FractionalPercent fractionalPercent2 = (FractionalPercent) codedInputStream.readMessage(FractionalPercent.parser(), extensionRegistryLite);
                            this.percentSampled_ = fractionalPercent2;
                            if (builderM32859toBuilder != null) {
                                builderM32859toBuilder.mergeFrom(fractionalPercent2);
                                this.percentSampled_ = builderM32859toBuilder.m32866buildPartial();
                            }
                        } else if (tag == 24) {
                            this.useIndependentRandomness_ = codedInputStream.readBool();
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
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static RuntimeFilter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RuntimeFilter> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_RuntimeFilter_descriptor;
    }

    public static RuntimeFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RuntimeFilter) PARSER.parseFrom(byteBuffer);
    }

    public static RuntimeFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RuntimeFilter) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RuntimeFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RuntimeFilter) PARSER.parseFrom(byteString);
    }

    public static RuntimeFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RuntimeFilter) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RuntimeFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RuntimeFilter) PARSER.parseFrom(bArr);
    }

    public static RuntimeFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RuntimeFilter) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RuntimeFilter parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RuntimeFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RuntimeFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RuntimeFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RuntimeFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RuntimeFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m25586toBuilder();
    }

    public static Builder newBuilder(RuntimeFilter runtimeFilter) {
        return DEFAULT_INSTANCE.m25586toBuilder().mergeFrom(runtimeFilter);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public RuntimeFilter m25581getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<RuntimeFilter> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilterOrBuilder
    public boolean getUseIndependentRandomness() {
        return this.useIndependentRandomness_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilterOrBuilder
    public boolean hasPercentSampled() {
        return this.percentSampled_ != null;
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
        return new RuntimeFilter();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_RuntimeFilter_fieldAccessorTable.ensureFieldAccessorsInitialized(RuntimeFilter.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilterOrBuilder
    public String getRuntimeKey() {
        Object obj = this.runtimeKey_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.runtimeKey_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilterOrBuilder
    public ByteString getRuntimeKeyBytes() {
        Object obj = this.runtimeKey_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.runtimeKey_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilterOrBuilder
    public FractionalPercent getPercentSampled() {
        FractionalPercent fractionalPercent = this.percentSampled_;
        return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilterOrBuilder
    public FractionalPercentOrBuilder getPercentSampledOrBuilder() {
        return getPercentSampled();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getRuntimeKeyBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.runtimeKey_);
        }
        if (this.percentSampled_ != null) {
            codedOutputStream.writeMessage(2, getPercentSampled());
        }
        boolean z = this.useIndependentRandomness_;
        if (z) {
            codedOutputStream.writeBool(3, z);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getRuntimeKeyBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.runtimeKey_) : 0;
        if (this.percentSampled_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, getPercentSampled());
        }
        boolean z = this.useIndependentRandomness_;
        if (z) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(3, z);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RuntimeFilter)) {
            return super.equals(obj);
        }
        RuntimeFilter runtimeFilter = (RuntimeFilter) obj;
        if (getRuntimeKey().equals(runtimeFilter.getRuntimeKey()) && hasPercentSampled() == runtimeFilter.hasPercentSampled()) {
            return (!hasPercentSampled() || getPercentSampled().equals(runtimeFilter.getPercentSampled())) && getUseIndependentRandomness() == runtimeFilter.getUseIndependentRandomness() && this.unknownFields.equals(runtimeFilter.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getRuntimeKey().hashCode();
        if (hasPercentSampled()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getPercentSampled().hashCode();
        }
        int iHashBoolean = (((((iHashCode * 37) + 3) * 53) + Internal.hashBoolean(getUseIndependentRandomness())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashBoolean;
        return iHashBoolean;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m25583newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m25586toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RuntimeFilterOrBuilder {
        private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> percentSampledBuilder_;
        private FractionalPercent percentSampled_;
        private Object runtimeKey_;
        private boolean useIndependentRandomness_;

        private Builder() {
            this.runtimeKey_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.runtimeKey_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_RuntimeFilter_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilterOrBuilder
        public boolean getUseIndependentRandomness() {
            return this.useIndependentRandomness_;
        }

        public Builder setUseIndependentRandomness(boolean z) {
            this.useIndependentRandomness_ = z;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilterOrBuilder
        public boolean hasPercentSampled() {
            return (this.percentSampledBuilder_ == null && this.percentSampled_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_RuntimeFilter_fieldAccessorTable.ensureFieldAccessorsInitialized(RuntimeFilter.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RuntimeFilter.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25597clear() {
            super.clear();
            this.runtimeKey_ = "";
            if (this.percentSampledBuilder_ == null) {
                this.percentSampled_ = null;
            } else {
                this.percentSampled_ = null;
                this.percentSampledBuilder_ = null;
            }
            this.useIndependentRandomness_ = false;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return AccesslogProto.internal_static_envoy_config_filter_accesslog_v2_RuntimeFilter_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RuntimeFilter m25610getDefaultInstanceForType() {
            return RuntimeFilter.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RuntimeFilter m25591build() throws UninitializedMessageException {
            RuntimeFilter runtimeFilterM25593buildPartial = m25593buildPartial();
            if (runtimeFilterM25593buildPartial.isInitialized()) {
                return runtimeFilterM25593buildPartial;
            }
            throw newUninitializedMessageException(runtimeFilterM25593buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RuntimeFilter m25593buildPartial() {
            RuntimeFilter runtimeFilter = new RuntimeFilter(this);
            runtimeFilter.runtimeKey_ = this.runtimeKey_;
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.percentSampledBuilder_;
            if (singleFieldBuilderV3 == null) {
                runtimeFilter.percentSampled_ = this.percentSampled_;
            } else {
                runtimeFilter.percentSampled_ = singleFieldBuilderV3.build();
            }
            runtimeFilter.useIndependentRandomness_ = this.useIndependentRandomness_;
            onBuilt();
            return runtimeFilter;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25609clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25621setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25599clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25602clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25623setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25589addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25614mergeFrom(Message message) {
            if (message instanceof RuntimeFilter) {
                return mergeFrom((RuntimeFilter) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RuntimeFilter runtimeFilter) {
            if (runtimeFilter == RuntimeFilter.getDefaultInstance()) {
                return this;
            }
            if (!runtimeFilter.getRuntimeKey().isEmpty()) {
                this.runtimeKey_ = runtimeFilter.runtimeKey_;
                onChanged();
            }
            if (runtimeFilter.hasPercentSampled()) {
                mergePercentSampled(runtimeFilter.getPercentSampled());
            }
            if (runtimeFilter.getUseIndependentRandomness()) {
                setUseIndependentRandomness(runtimeFilter.getUseIndependentRandomness());
            }
            m25619mergeUnknownFields(runtimeFilter.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilter.Builder m25615mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilter.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilter r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilter) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilter r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilter) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilter.Builder.m25615mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilter$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilterOrBuilder
        public String getRuntimeKey() {
            Object obj = this.runtimeKey_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.runtimeKey_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setRuntimeKey(String str) {
            str.getClass();
            this.runtimeKey_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilterOrBuilder
        public ByteString getRuntimeKeyBytes() {
            Object obj = this.runtimeKey_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.runtimeKey_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRuntimeKeyBytes(ByteString byteString) {
            byteString.getClass();
            RuntimeFilter.checkByteStringIsUtf8(byteString);
            this.runtimeKey_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRuntimeKey() {
            this.runtimeKey_ = RuntimeFilter.getDefaultInstance().getRuntimeKey();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilterOrBuilder
        public FractionalPercent getPercentSampled() {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.percentSampledBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            FractionalPercent fractionalPercent = this.percentSampled_;
            return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
        }

        public Builder setPercentSampled(FractionalPercent fractionalPercent) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.percentSampledBuilder_;
            if (singleFieldBuilderV3 == null) {
                fractionalPercent.getClass();
                this.percentSampled_ = fractionalPercent;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(fractionalPercent);
            }
            return this;
        }

        public Builder setPercentSampled(FractionalPercent.Builder builder) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.percentSampledBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.percentSampled_ = builder.m32864build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m32864build());
            }
            return this;
        }

        public Builder mergePercentSampled(FractionalPercent fractionalPercent) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.percentSampledBuilder_;
            if (singleFieldBuilderV3 == null) {
                FractionalPercent fractionalPercent2 = this.percentSampled_;
                if (fractionalPercent2 != null) {
                    this.percentSampled_ = FractionalPercent.newBuilder(fractionalPercent2).mergeFrom(fractionalPercent).m32866buildPartial();
                } else {
                    this.percentSampled_ = fractionalPercent;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(fractionalPercent);
            }
            return this;
        }

        public Builder clearPercentSampled() {
            if (this.percentSampledBuilder_ == null) {
                this.percentSampled_ = null;
                onChanged();
            } else {
                this.percentSampled_ = null;
                this.percentSampledBuilder_ = null;
            }
            return this;
        }

        public FractionalPercent.Builder getPercentSampledBuilder() {
            onChanged();
            return getPercentSampledFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.RuntimeFilterOrBuilder
        public FractionalPercentOrBuilder getPercentSampledOrBuilder() {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.percentSampledBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (FractionalPercentOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            FractionalPercent fractionalPercent = this.percentSampled_;
            return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
        }

        private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> getPercentSampledFieldBuilder() {
            if (this.percentSampledBuilder_ == null) {
                this.percentSampledBuilder_ = new SingleFieldBuilderV3<>(getPercentSampled(), getParentForChildren(), isClean());
                this.percentSampled_ = null;
            }
            return this.percentSampledBuilder_;
        }

        public Builder clearUseIndependentRandomness() {
            this.useIndependentRandomness_ = false;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m25625setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m25619mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
