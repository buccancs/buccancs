package io.opencensus.proto.stats.v1;

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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class DistributionAggregation extends GeneratedMessageV3 implements DistributionAggregationOrBuilder {
    public static final int BUCKET_BOUNDS_FIELD_NUMBER = 1;
    private static final DistributionAggregation DEFAULT_INSTANCE = new DistributionAggregation();
    private static final Parser<DistributionAggregation> PARSER = new AbstractParser<DistributionAggregation>() { // from class: io.opencensus.proto.stats.v1.DistributionAggregation.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public DistributionAggregation m37517parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DistributionAggregation(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private int bucketBoundsMemoizedSerializedSize;
    private List<Double> bucketBounds_;
    private byte memoizedIsInitialized;

    private DistributionAggregation(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.bucketBoundsMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
    }

    private DistributionAggregation() {
        this.bucketBoundsMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.bucketBounds_ = Collections.emptyList();
    }

    private DistributionAggregation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (true) {
            if (z) {
                break;
            }
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 9) {
                                if (!(z2 & true)) {
                                    this.bucketBounds_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.bucketBounds_.add(Double.valueOf(codedInputStream.readDouble()));
                            } else if (tag != 10) {
                                if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.bucketBounds_ = new ArrayList();
                                    z2 |= true;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.bucketBounds_.add(Double.valueOf(codedInputStream.readDouble()));
                                }
                                codedInputStream.popLimit(iPushLimit);
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    }
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if (z2 & true) {
                    this.bucketBounds_ = Collections.unmodifiableList(this.bucketBounds_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static DistributionAggregation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DistributionAggregation> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return StatsProto.internal_static_opencensus_proto_stats_v1_DistributionAggregation_descriptor;
    }

    public static DistributionAggregation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DistributionAggregation) PARSER.parseFrom(byteBuffer);
    }

    public static DistributionAggregation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DistributionAggregation) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DistributionAggregation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DistributionAggregation) PARSER.parseFrom(byteString);
    }

    public static DistributionAggregation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DistributionAggregation) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DistributionAggregation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DistributionAggregation) PARSER.parseFrom(bArr);
    }

    public static DistributionAggregation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DistributionAggregation) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DistributionAggregation parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DistributionAggregation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DistributionAggregation parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DistributionAggregation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DistributionAggregation parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DistributionAggregation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m37515toBuilder();
    }

    public static Builder newBuilder(DistributionAggregation distributionAggregation) {
        return DEFAULT_INSTANCE.m37515toBuilder().mergeFrom(distributionAggregation);
    }

    @Override // io.opencensus.proto.stats.v1.DistributionAggregationOrBuilder
    public List<Double> getBucketBoundsList() {
        return this.bucketBounds_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public DistributionAggregation m37510getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<DistributionAggregation> getParserForType() {
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
        return StatsProto.internal_static_opencensus_proto_stats_v1_DistributionAggregation_fieldAccessorTable.ensureFieldAccessorsInitialized(DistributionAggregation.class, Builder.class);
    }

    @Override // io.opencensus.proto.stats.v1.DistributionAggregationOrBuilder
    public int getBucketBoundsCount() {
        return this.bucketBounds_.size();
    }

    @Override // io.opencensus.proto.stats.v1.DistributionAggregationOrBuilder
    public double getBucketBounds(int i) {
        return this.bucketBounds_.get(i).doubleValue();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        if (getBucketBoundsList().size() > 0) {
            codedOutputStream.writeUInt32NoTag(10);
            codedOutputStream.writeUInt32NoTag(this.bucketBoundsMemoizedSerializedSize);
        }
        for (int i = 0; i < this.bucketBounds_.size(); i++) {
            codedOutputStream.writeDoubleNoTag(this.bucketBounds_.get(i).doubleValue());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int size = getBucketBoundsList().size() * 8;
        int iComputeInt32SizeNoTag = !getBucketBoundsList().isEmpty() ? size + 1 + CodedOutputStream.computeInt32SizeNoTag(size) : size;
        this.bucketBoundsMemoizedSerializedSize = size;
        int serializedSize = iComputeInt32SizeNoTag + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DistributionAggregation)) {
            return super.equals(obj);
        }
        DistributionAggregation distributionAggregation = (DistributionAggregation) obj;
        return getBucketBoundsList().equals(distributionAggregation.getBucketBoundsList()) && this.unknownFields.equals(distributionAggregation.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getBucketBoundsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getBucketBoundsList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37512newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37515toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DistributionAggregationOrBuilder {
        private int bitField0_;
        private List<Double> bucketBounds_;

        private Builder() {
            this.bucketBounds_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.bucketBounds_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StatsProto.internal_static_opencensus_proto_stats_v1_DistributionAggregation_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StatsProto.internal_static_opencensus_proto_stats_v1_DistributionAggregation_fieldAccessorTable.ensureFieldAccessorsInitialized(DistributionAggregation.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = DistributionAggregation.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37526clear() {
            super.clear();
            this.bucketBounds_ = Collections.emptyList();
            this.bitField0_ &= -2;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return StatsProto.internal_static_opencensus_proto_stats_v1_DistributionAggregation_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DistributionAggregation m37539getDefaultInstanceForType() {
            return DistributionAggregation.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DistributionAggregation m37520build() throws UninitializedMessageException {
            DistributionAggregation distributionAggregationM37522buildPartial = m37522buildPartial();
            if (distributionAggregationM37522buildPartial.isInitialized()) {
                return distributionAggregationM37522buildPartial;
            }
            throw newUninitializedMessageException(distributionAggregationM37522buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DistributionAggregation m37522buildPartial() {
            DistributionAggregation distributionAggregation = new DistributionAggregation(this);
            if ((this.bitField0_ & 1) == 1) {
                this.bucketBounds_ = Collections.unmodifiableList(this.bucketBounds_);
                this.bitField0_ &= -2;
            }
            distributionAggregation.bucketBounds_ = this.bucketBounds_;
            onBuilt();
            return distributionAggregation;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37538clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37550setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37528clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37531clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37552setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37518addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37543mergeFrom(Message message) {
            if (message instanceof DistributionAggregation) {
                return mergeFrom((DistributionAggregation) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DistributionAggregation distributionAggregation) {
            if (distributionAggregation == DistributionAggregation.getDefaultInstance()) {
                return this;
            }
            if (!distributionAggregation.bucketBounds_.isEmpty()) {
                if (this.bucketBounds_.isEmpty()) {
                    this.bucketBounds_ = distributionAggregation.bucketBounds_;
                    this.bitField0_ &= -2;
                } else {
                    ensureBucketBoundsIsMutable();
                    this.bucketBounds_.addAll(distributionAggregation.bucketBounds_);
                }
                onChanged();
            }
            m37548mergeUnknownFields(distributionAggregation.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.stats.v1.DistributionAggregation.Builder m37544mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.stats.v1.DistributionAggregation.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.stats.v1.DistributionAggregation r3 = (io.opencensus.proto.stats.v1.DistributionAggregation) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.stats.v1.DistributionAggregation r4 = (io.opencensus.proto.stats.v1.DistributionAggregation) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.stats.v1.DistributionAggregation.Builder.m37544mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.stats.v1.DistributionAggregation$Builder");
        }

        private void ensureBucketBoundsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.bucketBounds_ = new ArrayList(this.bucketBounds_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.opencensus.proto.stats.v1.DistributionAggregationOrBuilder
        public List<Double> getBucketBoundsList() {
            return Collections.unmodifiableList(this.bucketBounds_);
        }

        @Override // io.opencensus.proto.stats.v1.DistributionAggregationOrBuilder
        public int getBucketBoundsCount() {
            return this.bucketBounds_.size();
        }

        @Override // io.opencensus.proto.stats.v1.DistributionAggregationOrBuilder
        public double getBucketBounds(int i) {
            return this.bucketBounds_.get(i).doubleValue();
        }

        public Builder setBucketBounds(int i, double d) {
            ensureBucketBoundsIsMutable();
            this.bucketBounds_.set(i, Double.valueOf(d));
            onChanged();
            return this;
        }

        public Builder addBucketBounds(double d) {
            ensureBucketBoundsIsMutable();
            this.bucketBounds_.add(Double.valueOf(d));
            onChanged();
            return this;
        }

        public Builder addAllBucketBounds(Iterable<? extends Double> iterable) {
            ensureBucketBoundsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.bucketBounds_);
            onChanged();
            return this;
        }

        public Builder clearBucketBounds() {
            this.bucketBounds_ = Collections.emptyList();
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37554setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37548mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
