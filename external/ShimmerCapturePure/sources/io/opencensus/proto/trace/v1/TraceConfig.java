package io.opencensus.proto.trace.v1;

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
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.opencensus.proto.trace.v1.ConstantSampler;
import io.opencensus.proto.trace.v1.ProbabilitySampler;
import io.opencensus.proto.trace.v1.RateLimitingSampler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class TraceConfig extends GeneratedMessageV3 implements TraceConfigOrBuilder {
    public static final int CONSTANT_SAMPLER_FIELD_NUMBER = 2;
    public static final int MAX_NUMBER_OF_ANNOTATIONS_FIELD_NUMBER = 5;
    public static final int MAX_NUMBER_OF_ATTRIBUTES_FIELD_NUMBER = 4;
    public static final int MAX_NUMBER_OF_LINKS_FIELD_NUMBER = 7;
    public static final int MAX_NUMBER_OF_MESSAGE_EVENTS_FIELD_NUMBER = 6;
    public static final int PROBABILITY_SAMPLER_FIELD_NUMBER = 1;
    public static final int RATE_LIMITING_SAMPLER_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final TraceConfig DEFAULT_INSTANCE = new TraceConfig();
    private static final Parser<TraceConfig> PARSER = new AbstractParser<TraceConfig>() { // from class: io.opencensus.proto.trace.v1.TraceConfig.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public TraceConfig m38719parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new TraceConfig(codedInputStream, extensionRegistryLite);
        }
    };
    private long maxNumberOfAnnotations_;
    private long maxNumberOfAttributes_;
    private long maxNumberOfLinks_;
    private long maxNumberOfMessageEvents_;
    private byte memoizedIsInitialized;
    private int samplerCase_;
    private Object sampler_;

    private TraceConfig(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.samplerCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private TraceConfig() {
        this.samplerCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.maxNumberOfAttributes_ = 0L;
        this.maxNumberOfAnnotations_ = 0L;
        this.maxNumberOfMessageEvents_ = 0L;
        this.maxNumberOfLinks_ = 0L;
    }

    private TraceConfig(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            ProbabilitySampler.Builder builderM37978toBuilder = this.samplerCase_ == 1 ? ((ProbabilitySampler) this.sampler_).m37978toBuilder() : null;
                            MessageLite message = codedInputStream.readMessage(ProbabilitySampler.parser(), extensionRegistryLite);
                            this.sampler_ = message;
                            if (builderM37978toBuilder != null) {
                                builderM37978toBuilder.mergeFrom((ProbabilitySampler) message);
                                this.sampler_ = builderM37978toBuilder.m37985buildPartial();
                            }
                            this.samplerCase_ = 1;
                        } else if (tag == 18) {
                            ConstantSampler.Builder builderM37885toBuilder = this.samplerCase_ == 2 ? ((ConstantSampler) this.sampler_).m37885toBuilder() : null;
                            MessageLite message2 = codedInputStream.readMessage(ConstantSampler.parser(), extensionRegistryLite);
                            this.sampler_ = message2;
                            if (builderM37885toBuilder != null) {
                                builderM37885toBuilder.mergeFrom((ConstantSampler) message2);
                                this.sampler_ = builderM37885toBuilder.m37892buildPartial();
                            }
                            this.samplerCase_ = 2;
                        } else if (tag == 26) {
                            RateLimitingSampler.Builder builderM38024toBuilder = this.samplerCase_ == 3 ? ((RateLimitingSampler) this.sampler_).m38024toBuilder() : null;
                            MessageLite message3 = codedInputStream.readMessage(RateLimitingSampler.parser(), extensionRegistryLite);
                            this.sampler_ = message3;
                            if (builderM38024toBuilder != null) {
                                builderM38024toBuilder.mergeFrom((RateLimitingSampler) message3);
                                this.sampler_ = builderM38024toBuilder.m38031buildPartial();
                            }
                            this.samplerCase_ = 3;
                        } else if (tag == 32) {
                            this.maxNumberOfAttributes_ = codedInputStream.readInt64();
                        } else if (tag == 40) {
                            this.maxNumberOfAnnotations_ = codedInputStream.readInt64();
                        } else if (tag == 48) {
                            this.maxNumberOfMessageEvents_ = codedInputStream.readInt64();
                        } else if (tag != 56) {
                            if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.maxNumberOfLinks_ = codedInputStream.readInt64();
                        }
                    }
                    z = true;
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static TraceConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TraceConfig> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return TraceConfigProto.internal_static_opencensus_proto_trace_v1_TraceConfig_descriptor;
    }

    public static TraceConfig parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (TraceConfig) PARSER.parseFrom(byteBuffer);
    }

    public static TraceConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TraceConfig) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static TraceConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TraceConfig) PARSER.parseFrom(byteString);
    }

    public static TraceConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TraceConfig) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static TraceConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TraceConfig) PARSER.parseFrom(bArr);
    }

    public static TraceConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TraceConfig) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static TraceConfig parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static TraceConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TraceConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static TraceConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TraceConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static TraceConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m38717toBuilder();
    }

    public static Builder newBuilder(TraceConfig traceConfig) {
        return DEFAULT_INSTANCE.m38717toBuilder().mergeFrom(traceConfig);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public TraceConfig m38712getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
    public long getMaxNumberOfAnnotations() {
        return this.maxNumberOfAnnotations_;
    }

    @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
    public long getMaxNumberOfAttributes() {
        return this.maxNumberOfAttributes_;
    }

    @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
    public long getMaxNumberOfLinks() {
        return this.maxNumberOfLinks_;
    }

    @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
    public long getMaxNumberOfMessageEvents() {
        return this.maxNumberOfMessageEvents_;
    }

    public Parser<TraceConfig> getParserForType() {
        return PARSER;
    }

    @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
    public boolean hasConstantSampler() {
        return this.samplerCase_ == 2;
    }

    @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
    public boolean hasProbabilitySampler() {
        return this.samplerCase_ == 1;
    }

    @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
    public boolean hasRateLimitingSampler() {
        return this.samplerCase_ == 3;
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
        return TraceConfigProto.internal_static_opencensus_proto_trace_v1_TraceConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(TraceConfig.class, Builder.class);
    }

    @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
    public SamplerCase getSamplerCase() {
        return SamplerCase.forNumber(this.samplerCase_);
    }

    @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
    public ProbabilitySampler getProbabilitySampler() {
        if (this.samplerCase_ == 1) {
            return (ProbabilitySampler) this.sampler_;
        }
        return ProbabilitySampler.getDefaultInstance();
    }

    @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
    public ProbabilitySamplerOrBuilder getProbabilitySamplerOrBuilder() {
        if (this.samplerCase_ == 1) {
            return (ProbabilitySampler) this.sampler_;
        }
        return ProbabilitySampler.getDefaultInstance();
    }

    @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
    public ConstantSampler getConstantSampler() {
        if (this.samplerCase_ == 2) {
            return (ConstantSampler) this.sampler_;
        }
        return ConstantSampler.getDefaultInstance();
    }

    @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
    public ConstantSamplerOrBuilder getConstantSamplerOrBuilder() {
        if (this.samplerCase_ == 2) {
            return (ConstantSampler) this.sampler_;
        }
        return ConstantSampler.getDefaultInstance();
    }

    @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
    public RateLimitingSampler getRateLimitingSampler() {
        if (this.samplerCase_ == 3) {
            return (RateLimitingSampler) this.sampler_;
        }
        return RateLimitingSampler.getDefaultInstance();
    }

    @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
    public RateLimitingSamplerOrBuilder getRateLimitingSamplerOrBuilder() {
        if (this.samplerCase_ == 3) {
            return (RateLimitingSampler) this.sampler_;
        }
        return RateLimitingSampler.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.samplerCase_ == 1) {
            codedOutputStream.writeMessage(1, (ProbabilitySampler) this.sampler_);
        }
        if (this.samplerCase_ == 2) {
            codedOutputStream.writeMessage(2, (ConstantSampler) this.sampler_);
        }
        if (this.samplerCase_ == 3) {
            codedOutputStream.writeMessage(3, (RateLimitingSampler) this.sampler_);
        }
        long j = this.maxNumberOfAttributes_;
        if (j != 0) {
            codedOutputStream.writeInt64(4, j);
        }
        long j2 = this.maxNumberOfAnnotations_;
        if (j2 != 0) {
            codedOutputStream.writeInt64(5, j2);
        }
        long j3 = this.maxNumberOfMessageEvents_;
        if (j3 != 0) {
            codedOutputStream.writeInt64(6, j3);
        }
        long j4 = this.maxNumberOfLinks_;
        if (j4 != 0) {
            codedOutputStream.writeInt64(7, j4);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.samplerCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (ProbabilitySampler) this.sampler_) : 0;
        if (this.samplerCase_ == 2) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, (ConstantSampler) this.sampler_);
        }
        if (this.samplerCase_ == 3) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, (RateLimitingSampler) this.sampler_);
        }
        long j = this.maxNumberOfAttributes_;
        if (j != 0) {
            iComputeMessageSize += CodedOutputStream.computeInt64Size(4, j);
        }
        long j2 = this.maxNumberOfAnnotations_;
        if (j2 != 0) {
            iComputeMessageSize += CodedOutputStream.computeInt64Size(5, j2);
        }
        long j3 = this.maxNumberOfMessageEvents_;
        if (j3 != 0) {
            iComputeMessageSize += CodedOutputStream.computeInt64Size(6, j3);
        }
        long j4 = this.maxNumberOfLinks_;
        if (j4 != 0) {
            iComputeMessageSize += CodedOutputStream.computeInt64Size(7, j4);
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TraceConfig)) {
            return super.equals(obj);
        }
        TraceConfig traceConfig = (TraceConfig) obj;
        boolean z = getMaxNumberOfAttributes() == traceConfig.getMaxNumberOfAttributes() && getMaxNumberOfAnnotations() == traceConfig.getMaxNumberOfAnnotations() && getMaxNumberOfMessageEvents() == traceConfig.getMaxNumberOfMessageEvents() && getMaxNumberOfLinks() == traceConfig.getMaxNumberOfLinks() && getSamplerCase().equals(traceConfig.getSamplerCase());
        if (!z) {
            return false;
        }
        int i = this.samplerCase_;
        if (i == 1 ? !(!z || !getProbabilitySampler().equals(traceConfig.getProbabilitySampler())) : !(i == 2 ? !z || !getConstantSampler().equals(traceConfig.getConstantSampler()) : i == 3 ? !z || !getRateLimitingSampler().equals(traceConfig.getRateLimitingSampler()) : !z)) {
            if (this.unknownFields.equals(traceConfig.unknownFields)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 4) * 53) + Internal.hashLong(getMaxNumberOfAttributes())) * 37) + 5) * 53) + Internal.hashLong(getMaxNumberOfAnnotations())) * 37) + 6) * 53) + Internal.hashLong(getMaxNumberOfMessageEvents())) * 37) + 7) * 53) + Internal.hashLong(getMaxNumberOfLinks());
        int i2 = this.samplerCase_;
        if (i2 == 1) {
            i = ((iHashCode2 * 37) + 1) * 53;
            iHashCode = getProbabilitySampler().hashCode();
        } else if (i2 == 2) {
            i = ((iHashCode2 * 37) + 2) * 53;
            iHashCode = getConstantSampler().hashCode();
        } else {
            if (i2 == 3) {
                i = ((iHashCode2 * 37) + 3) * 53;
                iHashCode = getRateLimitingSampler().hashCode();
            }
            int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode3;
            return iHashCode3;
        }
        iHashCode2 = i + iHashCode;
        int iHashCode32 = (iHashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode32;
        return iHashCode32;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m38714newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m38717toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum SamplerCase implements Internal.EnumLite {
        PROBABILITY_SAMPLER(1),
        CONSTANT_SAMPLER(2),
        RATE_LIMITING_SAMPLER(3),
        SAMPLER_NOT_SET(0);

        private final int value;

        SamplerCase(int i) {
            this.value = i;
        }

        public static SamplerCase forNumber(int i) {
            if (i == 0) {
                return SAMPLER_NOT_SET;
            }
            if (i == 1) {
                return PROBABILITY_SAMPLER;
            }
            if (i == 2) {
                return CONSTANT_SAMPLER;
            }
            if (i != 3) {
                return null;
            }
            return RATE_LIMITING_SAMPLER;
        }

        @Deprecated
        public static SamplerCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TraceConfigOrBuilder {
        private SingleFieldBuilderV3<ConstantSampler, ConstantSampler.Builder, ConstantSamplerOrBuilder> constantSamplerBuilder_;
        private long maxNumberOfAnnotations_;
        private long maxNumberOfAttributes_;
        private long maxNumberOfLinks_;
        private long maxNumberOfMessageEvents_;
        private SingleFieldBuilderV3<ProbabilitySampler, ProbabilitySampler.Builder, ProbabilitySamplerOrBuilder> probabilitySamplerBuilder_;
        private SingleFieldBuilderV3<RateLimitingSampler, RateLimitingSampler.Builder, RateLimitingSamplerOrBuilder> rateLimitingSamplerBuilder_;
        private int samplerCase_;
        private Object sampler_;

        private Builder() {
            this.samplerCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.samplerCase_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TraceConfigProto.internal_static_opencensus_proto_trace_v1_TraceConfig_descriptor;
        }

        @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
        public long getMaxNumberOfAnnotations() {
            return this.maxNumberOfAnnotations_;
        }

        public Builder setMaxNumberOfAnnotations(long j) {
            this.maxNumberOfAnnotations_ = j;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
        public long getMaxNumberOfAttributes() {
            return this.maxNumberOfAttributes_;
        }

        public Builder setMaxNumberOfAttributes(long j) {
            this.maxNumberOfAttributes_ = j;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
        public long getMaxNumberOfLinks() {
            return this.maxNumberOfLinks_;
        }

        public Builder setMaxNumberOfLinks(long j) {
            this.maxNumberOfLinks_ = j;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
        public long getMaxNumberOfMessageEvents() {
            return this.maxNumberOfMessageEvents_;
        }

        public Builder setMaxNumberOfMessageEvents(long j) {
            this.maxNumberOfMessageEvents_ = j;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
        public boolean hasConstantSampler() {
            return this.samplerCase_ == 2;
        }

        @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
        public boolean hasProbabilitySampler() {
            return this.samplerCase_ == 1;
        }

        @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
        public boolean hasRateLimitingSampler() {
            return this.samplerCase_ == 3;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TraceConfigProto.internal_static_opencensus_proto_trace_v1_TraceConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(TraceConfig.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = TraceConfig.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38728clear() {
            super.clear();
            this.maxNumberOfAttributes_ = 0L;
            this.maxNumberOfAnnotations_ = 0L;
            this.maxNumberOfMessageEvents_ = 0L;
            this.maxNumberOfLinks_ = 0L;
            this.samplerCase_ = 0;
            this.sampler_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return TraceConfigProto.internal_static_opencensus_proto_trace_v1_TraceConfig_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TraceConfig m38741getDefaultInstanceForType() {
            return TraceConfig.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TraceConfig m38722build() throws UninitializedMessageException {
            TraceConfig traceConfigM38724buildPartial = m38724buildPartial();
            if (traceConfigM38724buildPartial.isInitialized()) {
                return traceConfigM38724buildPartial;
            }
            throw newUninitializedMessageException(traceConfigM38724buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TraceConfig m38724buildPartial() {
            TraceConfig traceConfig = new TraceConfig(this);
            if (this.samplerCase_ == 1) {
                SingleFieldBuilderV3<ProbabilitySampler, ProbabilitySampler.Builder, ProbabilitySamplerOrBuilder> singleFieldBuilderV3 = this.probabilitySamplerBuilder_;
                if (singleFieldBuilderV3 == null) {
                    traceConfig.sampler_ = this.sampler_;
                } else {
                    traceConfig.sampler_ = singleFieldBuilderV3.build();
                }
            }
            if (this.samplerCase_ == 2) {
                SingleFieldBuilderV3<ConstantSampler, ConstantSampler.Builder, ConstantSamplerOrBuilder> singleFieldBuilderV32 = this.constantSamplerBuilder_;
                if (singleFieldBuilderV32 == null) {
                    traceConfig.sampler_ = this.sampler_;
                } else {
                    traceConfig.sampler_ = singleFieldBuilderV32.build();
                }
            }
            if (this.samplerCase_ == 3) {
                SingleFieldBuilderV3<RateLimitingSampler, RateLimitingSampler.Builder, RateLimitingSamplerOrBuilder> singleFieldBuilderV33 = this.rateLimitingSamplerBuilder_;
                if (singleFieldBuilderV33 == null) {
                    traceConfig.sampler_ = this.sampler_;
                } else {
                    traceConfig.sampler_ = singleFieldBuilderV33.build();
                }
            }
            traceConfig.maxNumberOfAttributes_ = this.maxNumberOfAttributes_;
            traceConfig.maxNumberOfAnnotations_ = this.maxNumberOfAnnotations_;
            traceConfig.maxNumberOfMessageEvents_ = this.maxNumberOfMessageEvents_;
            traceConfig.maxNumberOfLinks_ = this.maxNumberOfLinks_;
            traceConfig.samplerCase_ = this.samplerCase_;
            onBuilt();
            return traceConfig;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38740clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38752setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38730clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38733clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38754setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38720addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38745mergeFrom(Message message) {
            if (message instanceof TraceConfig) {
                return mergeFrom((TraceConfig) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(TraceConfig traceConfig) {
            if (traceConfig == TraceConfig.getDefaultInstance()) {
                return this;
            }
            if (traceConfig.getMaxNumberOfAttributes() != 0) {
                setMaxNumberOfAttributes(traceConfig.getMaxNumberOfAttributes());
            }
            if (traceConfig.getMaxNumberOfAnnotations() != 0) {
                setMaxNumberOfAnnotations(traceConfig.getMaxNumberOfAnnotations());
            }
            if (traceConfig.getMaxNumberOfMessageEvents() != 0) {
                setMaxNumberOfMessageEvents(traceConfig.getMaxNumberOfMessageEvents());
            }
            if (traceConfig.getMaxNumberOfLinks() != 0) {
                setMaxNumberOfLinks(traceConfig.getMaxNumberOfLinks());
            }
            int i = AnonymousClass2.$SwitchMap$io$opencensus$proto$trace$v1$TraceConfig$SamplerCase[traceConfig.getSamplerCase().ordinal()];
            if (i == 1) {
                mergeProbabilitySampler(traceConfig.getProbabilitySampler());
            } else if (i == 2) {
                mergeConstantSampler(traceConfig.getConstantSampler());
            } else if (i == 3) {
                mergeRateLimitingSampler(traceConfig.getRateLimitingSampler());
            }
            m38750mergeUnknownFields(traceConfig.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.trace.v1.TraceConfig.Builder m38746mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.trace.v1.TraceConfig.access$1100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.trace.v1.TraceConfig r3 = (io.opencensus.proto.trace.v1.TraceConfig) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.trace.v1.TraceConfig r4 = (io.opencensus.proto.trace.v1.TraceConfig) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.trace.v1.TraceConfig.Builder.m38746mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.trace.v1.TraceConfig$Builder");
        }

        @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
        public SamplerCase getSamplerCase() {
            return SamplerCase.forNumber(this.samplerCase_);
        }

        public Builder clearSampler() {
            this.samplerCase_ = 0;
            this.sampler_ = null;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
        public ProbabilitySampler getProbabilitySampler() {
            SingleFieldBuilderV3<ProbabilitySampler, ProbabilitySampler.Builder, ProbabilitySamplerOrBuilder> singleFieldBuilderV3 = this.probabilitySamplerBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.samplerCase_ == 1) {
                    return (ProbabilitySampler) this.sampler_;
                }
                return ProbabilitySampler.getDefaultInstance();
            }
            if (this.samplerCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            }
            return ProbabilitySampler.getDefaultInstance();
        }

        public Builder setProbabilitySampler(ProbabilitySampler probabilitySampler) {
            SingleFieldBuilderV3<ProbabilitySampler, ProbabilitySampler.Builder, ProbabilitySamplerOrBuilder> singleFieldBuilderV3 = this.probabilitySamplerBuilder_;
            if (singleFieldBuilderV3 == null) {
                probabilitySampler.getClass();
                this.sampler_ = probabilitySampler;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(probabilitySampler);
            }
            this.samplerCase_ = 1;
            return this;
        }

        public Builder setProbabilitySampler(ProbabilitySampler.Builder builder) {
            SingleFieldBuilderV3<ProbabilitySampler, ProbabilitySampler.Builder, ProbabilitySamplerOrBuilder> singleFieldBuilderV3 = this.probabilitySamplerBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sampler_ = builder.m37983build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m37983build());
            }
            this.samplerCase_ = 1;
            return this;
        }

        public Builder mergeProbabilitySampler(ProbabilitySampler probabilitySampler) {
            SingleFieldBuilderV3<ProbabilitySampler, ProbabilitySampler.Builder, ProbabilitySamplerOrBuilder> singleFieldBuilderV3 = this.probabilitySamplerBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.samplerCase_ != 1 || this.sampler_ == ProbabilitySampler.getDefaultInstance()) {
                    this.sampler_ = probabilitySampler;
                } else {
                    this.sampler_ = ProbabilitySampler.newBuilder((ProbabilitySampler) this.sampler_).mergeFrom(probabilitySampler).m37985buildPartial();
                }
                onChanged();
            } else {
                if (this.samplerCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(probabilitySampler);
                }
                this.probabilitySamplerBuilder_.setMessage(probabilitySampler);
            }
            this.samplerCase_ = 1;
            return this;
        }

        public Builder clearProbabilitySampler() {
            SingleFieldBuilderV3<ProbabilitySampler, ProbabilitySampler.Builder, ProbabilitySamplerOrBuilder> singleFieldBuilderV3 = this.probabilitySamplerBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.samplerCase_ == 1) {
                    this.samplerCase_ = 0;
                    this.sampler_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.samplerCase_ == 1) {
                this.samplerCase_ = 0;
                this.sampler_ = null;
                onChanged();
            }
            return this;
        }

        public ProbabilitySampler.Builder getProbabilitySamplerBuilder() {
            return getProbabilitySamplerFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
        public ProbabilitySamplerOrBuilder getProbabilitySamplerOrBuilder() {
            SingleFieldBuilderV3<ProbabilitySampler, ProbabilitySampler.Builder, ProbabilitySamplerOrBuilder> singleFieldBuilderV3;
            int i = this.samplerCase_;
            if (i == 1 && (singleFieldBuilderV3 = this.probabilitySamplerBuilder_) != null) {
                return (ProbabilitySamplerOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 1) {
                return (ProbabilitySampler) this.sampler_;
            }
            return ProbabilitySampler.getDefaultInstance();
        }

        private SingleFieldBuilderV3<ProbabilitySampler, ProbabilitySampler.Builder, ProbabilitySamplerOrBuilder> getProbabilitySamplerFieldBuilder() {
            if (this.probabilitySamplerBuilder_ == null) {
                if (this.samplerCase_ != 1) {
                    this.sampler_ = ProbabilitySampler.getDefaultInstance();
                }
                this.probabilitySamplerBuilder_ = new SingleFieldBuilderV3<>((ProbabilitySampler) this.sampler_, getParentForChildren(), isClean());
                this.sampler_ = null;
            }
            this.samplerCase_ = 1;
            onChanged();
            return this.probabilitySamplerBuilder_;
        }

        @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
        public ConstantSampler getConstantSampler() {
            SingleFieldBuilderV3<ConstantSampler, ConstantSampler.Builder, ConstantSamplerOrBuilder> singleFieldBuilderV3 = this.constantSamplerBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.samplerCase_ == 2) {
                    return (ConstantSampler) this.sampler_;
                }
                return ConstantSampler.getDefaultInstance();
            }
            if (this.samplerCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return ConstantSampler.getDefaultInstance();
        }

        public Builder setConstantSampler(ConstantSampler constantSampler) {
            SingleFieldBuilderV3<ConstantSampler, ConstantSampler.Builder, ConstantSamplerOrBuilder> singleFieldBuilderV3 = this.constantSamplerBuilder_;
            if (singleFieldBuilderV3 == null) {
                constantSampler.getClass();
                this.sampler_ = constantSampler;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(constantSampler);
            }
            this.samplerCase_ = 2;
            return this;
        }

        public Builder setConstantSampler(ConstantSampler.Builder builder) {
            SingleFieldBuilderV3<ConstantSampler, ConstantSampler.Builder, ConstantSamplerOrBuilder> singleFieldBuilderV3 = this.constantSamplerBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sampler_ = builder.m37890build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m37890build());
            }
            this.samplerCase_ = 2;
            return this;
        }

        public Builder mergeConstantSampler(ConstantSampler constantSampler) {
            SingleFieldBuilderV3<ConstantSampler, ConstantSampler.Builder, ConstantSamplerOrBuilder> singleFieldBuilderV3 = this.constantSamplerBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.samplerCase_ != 2 || this.sampler_ == ConstantSampler.getDefaultInstance()) {
                    this.sampler_ = constantSampler;
                } else {
                    this.sampler_ = ConstantSampler.newBuilder((ConstantSampler) this.sampler_).mergeFrom(constantSampler).m37892buildPartial();
                }
                onChanged();
            } else {
                if (this.samplerCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(constantSampler);
                }
                this.constantSamplerBuilder_.setMessage(constantSampler);
            }
            this.samplerCase_ = 2;
            return this;
        }

        public Builder clearConstantSampler() {
            SingleFieldBuilderV3<ConstantSampler, ConstantSampler.Builder, ConstantSamplerOrBuilder> singleFieldBuilderV3 = this.constantSamplerBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.samplerCase_ == 2) {
                    this.samplerCase_ = 0;
                    this.sampler_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.samplerCase_ == 2) {
                this.samplerCase_ = 0;
                this.sampler_ = null;
                onChanged();
            }
            return this;
        }

        public ConstantSampler.Builder getConstantSamplerBuilder() {
            return getConstantSamplerFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
        public ConstantSamplerOrBuilder getConstantSamplerOrBuilder() {
            SingleFieldBuilderV3<ConstantSampler, ConstantSampler.Builder, ConstantSamplerOrBuilder> singleFieldBuilderV3;
            int i = this.samplerCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.constantSamplerBuilder_) != null) {
                return (ConstantSamplerOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (ConstantSampler) this.sampler_;
            }
            return ConstantSampler.getDefaultInstance();
        }

        private SingleFieldBuilderV3<ConstantSampler, ConstantSampler.Builder, ConstantSamplerOrBuilder> getConstantSamplerFieldBuilder() {
            if (this.constantSamplerBuilder_ == null) {
                if (this.samplerCase_ != 2) {
                    this.sampler_ = ConstantSampler.getDefaultInstance();
                }
                this.constantSamplerBuilder_ = new SingleFieldBuilderV3<>((ConstantSampler) this.sampler_, getParentForChildren(), isClean());
                this.sampler_ = null;
            }
            this.samplerCase_ = 2;
            onChanged();
            return this.constantSamplerBuilder_;
        }

        @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
        public RateLimitingSampler getRateLimitingSampler() {
            SingleFieldBuilderV3<RateLimitingSampler, RateLimitingSampler.Builder, RateLimitingSamplerOrBuilder> singleFieldBuilderV3 = this.rateLimitingSamplerBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.samplerCase_ == 3) {
                    return (RateLimitingSampler) this.sampler_;
                }
                return RateLimitingSampler.getDefaultInstance();
            }
            if (this.samplerCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return RateLimitingSampler.getDefaultInstance();
        }

        public Builder setRateLimitingSampler(RateLimitingSampler rateLimitingSampler) {
            SingleFieldBuilderV3<RateLimitingSampler, RateLimitingSampler.Builder, RateLimitingSamplerOrBuilder> singleFieldBuilderV3 = this.rateLimitingSamplerBuilder_;
            if (singleFieldBuilderV3 == null) {
                rateLimitingSampler.getClass();
                this.sampler_ = rateLimitingSampler;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(rateLimitingSampler);
            }
            this.samplerCase_ = 3;
            return this;
        }

        public Builder setRateLimitingSampler(RateLimitingSampler.Builder builder) {
            SingleFieldBuilderV3<RateLimitingSampler, RateLimitingSampler.Builder, RateLimitingSamplerOrBuilder> singleFieldBuilderV3 = this.rateLimitingSamplerBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sampler_ = builder.m38029build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m38029build());
            }
            this.samplerCase_ = 3;
            return this;
        }

        public Builder mergeRateLimitingSampler(RateLimitingSampler rateLimitingSampler) {
            SingleFieldBuilderV3<RateLimitingSampler, RateLimitingSampler.Builder, RateLimitingSamplerOrBuilder> singleFieldBuilderV3 = this.rateLimitingSamplerBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.samplerCase_ != 3 || this.sampler_ == RateLimitingSampler.getDefaultInstance()) {
                    this.sampler_ = rateLimitingSampler;
                } else {
                    this.sampler_ = RateLimitingSampler.newBuilder((RateLimitingSampler) this.sampler_).mergeFrom(rateLimitingSampler).m38031buildPartial();
                }
                onChanged();
            } else {
                if (this.samplerCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(rateLimitingSampler);
                }
                this.rateLimitingSamplerBuilder_.setMessage(rateLimitingSampler);
            }
            this.samplerCase_ = 3;
            return this;
        }

        public Builder clearRateLimitingSampler() {
            SingleFieldBuilderV3<RateLimitingSampler, RateLimitingSampler.Builder, RateLimitingSamplerOrBuilder> singleFieldBuilderV3 = this.rateLimitingSamplerBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.samplerCase_ == 3) {
                    this.samplerCase_ = 0;
                    this.sampler_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.samplerCase_ == 3) {
                this.samplerCase_ = 0;
                this.sampler_ = null;
                onChanged();
            }
            return this;
        }

        public RateLimitingSampler.Builder getRateLimitingSamplerBuilder() {
            return getRateLimitingSamplerFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.trace.v1.TraceConfigOrBuilder
        public RateLimitingSamplerOrBuilder getRateLimitingSamplerOrBuilder() {
            SingleFieldBuilderV3<RateLimitingSampler, RateLimitingSampler.Builder, RateLimitingSamplerOrBuilder> singleFieldBuilderV3;
            int i = this.samplerCase_;
            if (i == 3 && (singleFieldBuilderV3 = this.rateLimitingSamplerBuilder_) != null) {
                return (RateLimitingSamplerOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 3) {
                return (RateLimitingSampler) this.sampler_;
            }
            return RateLimitingSampler.getDefaultInstance();
        }

        private SingleFieldBuilderV3<RateLimitingSampler, RateLimitingSampler.Builder, RateLimitingSamplerOrBuilder> getRateLimitingSamplerFieldBuilder() {
            if (this.rateLimitingSamplerBuilder_ == null) {
                if (this.samplerCase_ != 3) {
                    this.sampler_ = RateLimitingSampler.getDefaultInstance();
                }
                this.rateLimitingSamplerBuilder_ = new SingleFieldBuilderV3<>((RateLimitingSampler) this.sampler_, getParentForChildren(), isClean());
                this.sampler_ = null;
            }
            this.samplerCase_ = 3;
            onChanged();
            return this.rateLimitingSamplerBuilder_;
        }

        public Builder clearMaxNumberOfAttributes() {
            this.maxNumberOfAttributes_ = 0L;
            onChanged();
            return this;
        }

        public Builder clearMaxNumberOfAnnotations() {
            this.maxNumberOfAnnotations_ = 0L;
            onChanged();
            return this;
        }

        public Builder clearMaxNumberOfMessageEvents() {
            this.maxNumberOfMessageEvents_ = 0L;
            onChanged();
            return this;
        }

        public Builder clearMaxNumberOfLinks() {
            this.maxNumberOfLinks_ = 0L;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m38756setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m38750mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.opencensus.proto.trace.v1.TraceConfig$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$opencensus$proto$trace$v1$TraceConfig$SamplerCase;

        static {
            int[] iArr = new int[SamplerCase.values().length];
            $SwitchMap$io$opencensus$proto$trace$v1$TraceConfig$SamplerCase = iArr;
            try {
                iArr[SamplerCase.PROBABILITY_SAMPLER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$opencensus$proto$trace$v1$TraceConfig$SamplerCase[SamplerCase.CONSTANT_SAMPLER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$opencensus$proto$trace$v1$TraceConfig$SamplerCase[SamplerCase.RATE_LIMITING_SAMPLER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$opencensus$proto$trace$v1$TraceConfig$SamplerCase[SamplerCase.SAMPLER_NOT_SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
