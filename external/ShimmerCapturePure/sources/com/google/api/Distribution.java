package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class Distribution extends GeneratedMessageV3 implements DistributionOrBuilder {
    public static final int BUCKET_COUNTS_FIELD_NUMBER = 7;
    public static final int BUCKET_OPTIONS_FIELD_NUMBER = 6;
    public static final int COUNT_FIELD_NUMBER = 1;
    public static final int EXEMPLARS_FIELD_NUMBER = 10;
    public static final int MEAN_FIELD_NUMBER = 2;
    public static final int RANGE_FIELD_NUMBER = 4;
    public static final int SUM_OF_SQUARED_DEVIATION_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final Distribution DEFAULT_INSTANCE = new Distribution();
    private static final Parser<Distribution> PARSER = new AbstractParser<Distribution>() { // from class: com.google.api.Distribution.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Distribution m854parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Distribution(codedInputStream, extensionRegistryLite);
        }
    };
    private int bitField0_;
    private int bucketCountsMemoizedSerializedSize;
    private Internal.LongList bucketCounts_;
    private BucketOptions bucketOptions_;
    private long count_;
    private List<Exemplar> exemplars_;
    private double mean_;
    private byte memoizedIsInitialized;
    private Range range_;
    private double sumOfSquaredDeviation_;

    private Distribution(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.bucketCountsMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Distribution() {
        this.bucketCountsMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.bucketCounts_ = emptyLongList();
        this.exemplars_ = Collections.emptyList();
    }

    private Distribution(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (tag == 8) {
                            this.count_ = codedInputStream.readInt64();
                        } else if (tag == 17) {
                            this.mean_ = codedInputStream.readDouble();
                        } else if (tag != 25) {
                            if (tag == 34) {
                                Range range = this.range_;
                                Range.Builder builderM1129toBuilder = range != null ? range.m1129toBuilder() : null;
                                Range range2 = (Range) codedInputStream.readMessage(Range.parser(), extensionRegistryLite);
                                this.range_ = range2;
                                if (builderM1129toBuilder != null) {
                                    builderM1129toBuilder.mergeFrom(range2);
                                    this.range_ = builderM1129toBuilder.m1136buildPartial();
                                }
                            } else if (tag == 50) {
                                BucketOptions bucketOptions = this.bucketOptions_;
                                BucketOptions.Builder builderM861toBuilder = bucketOptions != null ? bucketOptions.m861toBuilder() : null;
                                BucketOptions bucketOptions2 = (BucketOptions) codedInputStream.readMessage(BucketOptions.parser(), extensionRegistryLite);
                                this.bucketOptions_ = bucketOptions2;
                                if (builderM861toBuilder != null) {
                                    builderM861toBuilder.mergeFrom(bucketOptions2);
                                    this.bucketOptions_ = builderM861toBuilder.m868buildPartial();
                                }
                            } else if (tag == 56) {
                                if ((i & 32) == 0) {
                                    this.bucketCounts_ = newLongList();
                                    i |= 32;
                                }
                                this.bucketCounts_.addLong(codedInputStream.readInt64());
                            } else if (tag == 58) {
                                int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if ((i & 32) == 0 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.bucketCounts_ = newLongList();
                                    i |= 32;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.bucketCounts_.addLong(codedInputStream.readInt64());
                                }
                                codedInputStream.popLimit(iPushLimit);
                            } else if (tag == 82) {
                                if ((i & 64) == 0) {
                                    this.exemplars_ = new ArrayList();
                                    i |= 64;
                                }
                                this.exemplars_.add(codedInputStream.readMessage(Exemplar.parser(), extensionRegistryLite));
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.sumOfSquaredDeviation_ = codedInputStream.readDouble();
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if ((i & 32) != 0) {
                    this.bucketCounts_.makeImmutable();
                }
                if ((i & 64) != 0) {
                    this.exemplars_ = Collections.unmodifiableList(this.exemplars_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static Distribution getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Distribution> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DistributionProto.internal_static_google_api_Distribution_descriptor;
    }

    public static Distribution parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Distribution) PARSER.parseFrom(byteBuffer);
    }

    public static Distribution parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Distribution) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Distribution parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Distribution) PARSER.parseFrom(byteString);
    }

    public static Distribution parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Distribution) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Distribution parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Distribution) PARSER.parseFrom(bArr);
    }

    public static Distribution parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Distribution) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Distribution parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Distribution parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Distribution parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Distribution parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Distribution parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Distribution parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m853toBuilder();
    }

    public static Builder newBuilder(Distribution distribution) {
        return DEFAULT_INSTANCE.m853toBuilder().mergeFrom(distribution);
    }

    @Override // com.google.api.DistributionOrBuilder
    public List<Long> getBucketCountsList() {
        return this.bucketCounts_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public long getCount() {
        return this.count_;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Distribution m848getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.api.DistributionOrBuilder
    public List<Exemplar> getExemplarsList() {
        return this.exemplars_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public List<? extends ExemplarOrBuilder> getExemplarsOrBuilderList() {
        return this.exemplars_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public double getMean() {
        return this.mean_;
    }

    public Parser<Distribution> getParserForType() {
        return PARSER;
    }

    @Override // com.google.api.DistributionOrBuilder
    public double getSumOfSquaredDeviation() {
        return this.sumOfSquaredDeviation_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public boolean hasBucketOptions() {
        return this.bucketOptions_ != null;
    }

    @Override // com.google.api.DistributionOrBuilder
    public boolean hasRange() {
        return this.range_ != null;
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
        return DistributionProto.internal_static_google_api_Distribution_fieldAccessorTable.ensureFieldAccessorsInitialized(Distribution.class, Builder.class);
    }

    @Override // com.google.api.DistributionOrBuilder
    public Range getRange() {
        Range range = this.range_;
        return range == null ? Range.getDefaultInstance() : range;
    }

    @Override // com.google.api.DistributionOrBuilder
    public RangeOrBuilder getRangeOrBuilder() {
        return getRange();
    }

    @Override // com.google.api.DistributionOrBuilder
    public BucketOptions getBucketOptions() {
        BucketOptions bucketOptions = this.bucketOptions_;
        return bucketOptions == null ? BucketOptions.getDefaultInstance() : bucketOptions;
    }

    @Override // com.google.api.DistributionOrBuilder
    public BucketOptionsOrBuilder getBucketOptionsOrBuilder() {
        return getBucketOptions();
    }

    @Override // com.google.api.DistributionOrBuilder
    public int getBucketCountsCount() {
        return this.bucketCounts_.size();
    }

    @Override // com.google.api.DistributionOrBuilder
    public long getBucketCounts(int i) {
        return this.bucketCounts_.getLong(i);
    }

    @Override // com.google.api.DistributionOrBuilder
    public int getExemplarsCount() {
        return this.exemplars_.size();
    }

    @Override // com.google.api.DistributionOrBuilder
    public Exemplar getExemplars(int i) {
        return this.exemplars_.get(i);
    }

    @Override // com.google.api.DistributionOrBuilder
    public ExemplarOrBuilder getExemplarsOrBuilder(int i) {
        return this.exemplars_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        long j = this.count_;
        if (j != 0) {
            codedOutputStream.writeInt64(1, j);
        }
        double d = this.mean_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(2, d);
        }
        double d2 = this.sumOfSquaredDeviation_;
        if (d2 != 0.0d) {
            codedOutputStream.writeDouble(3, d2);
        }
        if (this.range_ != null) {
            codedOutputStream.writeMessage(4, getRange());
        }
        if (this.bucketOptions_ != null) {
            codedOutputStream.writeMessage(6, getBucketOptions());
        }
        if (getBucketCountsList().size() > 0) {
            codedOutputStream.writeUInt32NoTag(58);
            codedOutputStream.writeUInt32NoTag(this.bucketCountsMemoizedSerializedSize);
        }
        for (int i = 0; i < this.bucketCounts_.size(); i++) {
            codedOutputStream.writeInt64NoTag(this.bucketCounts_.getLong(i));
        }
        for (int i2 = 0; i2 < this.exemplars_.size(); i2++) {
            codedOutputStream.writeMessage(10, this.exemplars_.get(i2));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        long j = this.count_;
        int iComputeInt64Size = j != 0 ? CodedOutputStream.computeInt64Size(1, j) : 0;
        double d = this.mean_;
        if (d != 0.0d) {
            iComputeInt64Size += CodedOutputStream.computeDoubleSize(2, d);
        }
        double d2 = this.sumOfSquaredDeviation_;
        if (d2 != 0.0d) {
            iComputeInt64Size += CodedOutputStream.computeDoubleSize(3, d2);
        }
        if (this.range_ != null) {
            iComputeInt64Size += CodedOutputStream.computeMessageSize(4, getRange());
        }
        if (this.bucketOptions_ != null) {
            iComputeInt64Size += CodedOutputStream.computeMessageSize(6, getBucketOptions());
        }
        int iComputeInt64SizeNoTag = 0;
        for (int i2 = 0; i2 < this.bucketCounts_.size(); i2++) {
            iComputeInt64SizeNoTag += CodedOutputStream.computeInt64SizeNoTag(this.bucketCounts_.getLong(i2));
        }
        int iComputeMessageSize = iComputeInt64Size + iComputeInt64SizeNoTag;
        if (!getBucketCountsList().isEmpty()) {
            iComputeMessageSize = iComputeMessageSize + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt64SizeNoTag);
        }
        this.bucketCountsMemoizedSerializedSize = iComputeInt64SizeNoTag;
        for (int i3 = 0; i3 < this.exemplars_.size(); i3++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(10, this.exemplars_.get(i3));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Distribution)) {
            return super.equals(obj);
        }
        Distribution distribution = (Distribution) obj;
        if (getCount() != distribution.getCount() || Double.doubleToLongBits(getMean()) != Double.doubleToLongBits(distribution.getMean()) || Double.doubleToLongBits(getSumOfSquaredDeviation()) != Double.doubleToLongBits(distribution.getSumOfSquaredDeviation()) || hasRange() != distribution.hasRange()) {
            return false;
        }
        if ((!hasRange() || getRange().equals(distribution.getRange())) && hasBucketOptions() == distribution.hasBucketOptions()) {
            return (!hasBucketOptions() || getBucketOptions().equals(distribution.getBucketOptions())) && getBucketCountsList().equals(distribution.getBucketCountsList()) && getExemplarsList().equals(distribution.getExemplarsList()) && this.unknownFields.equals(distribution.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(getCount())) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getMean()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getSumOfSquaredDeviation()));
        if (hasRange()) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getRange().hashCode();
        }
        if (hasBucketOptions()) {
            iHashCode = (((iHashCode * 37) + 6) * 53) + getBucketOptions().hashCode();
        }
        if (getBucketCountsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 7) * 53) + getBucketCountsList().hashCode();
        }
        if (getExemplarsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 10) * 53) + getExemplarsList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m851newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m853toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m850newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public interface BucketOptionsOrBuilder extends MessageOrBuilder {
        BucketOptions.Explicit getExplicitBuckets();

        BucketOptions.ExplicitOrBuilder getExplicitBucketsOrBuilder();

        BucketOptions.Exponential getExponentialBuckets();

        BucketOptions.ExponentialOrBuilder getExponentialBucketsOrBuilder();

        BucketOptions.Linear getLinearBuckets();

        BucketOptions.LinearOrBuilder getLinearBucketsOrBuilder();

        BucketOptions.OptionsCase getOptionsCase();

        boolean hasExplicitBuckets();

        boolean hasExponentialBuckets();

        boolean hasLinearBuckets();
    }

    public interface ExemplarOrBuilder extends MessageOrBuilder {
        Any getAttachments(int i);

        int getAttachmentsCount();

        List<Any> getAttachmentsList();

        AnyOrBuilder getAttachmentsOrBuilder(int i);

        List<? extends AnyOrBuilder> getAttachmentsOrBuilderList();

        Timestamp getTimestamp();

        TimestampOrBuilder getTimestampOrBuilder();

        double getValue();

        boolean hasTimestamp();
    }

    public interface RangeOrBuilder extends MessageOrBuilder {
        double getMax();

        double getMin();
    }

    public static final class Range extends GeneratedMessageV3 implements RangeOrBuilder {
        public static final int MAX_FIELD_NUMBER = 2;
        public static final int MIN_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final Range DEFAULT_INSTANCE = new Range();
        private static final Parser<Range> PARSER = new AbstractParser<Range>() { // from class: com.google.api.Distribution.Range.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Range m1130parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Range(codedInputStream, extensionRegistryLite);
            }
        };
        private double max_;
        private byte memoizedIsInitialized;
        private double min_;

        private Range(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Range() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private Range(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (tag == 9) {
                                    this.min_ = codedInputStream.readDouble();
                                } else if (tag == 17) {
                                    this.max_ = codedInputStream.readDouble();
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

        public static Range getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Range> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DistributionProto.internal_static_google_api_Distribution_Range_descriptor;
        }

        public static Range parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Range) PARSER.parseFrom(byteBuffer);
        }

        public static Range parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Range) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Range parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Range) PARSER.parseFrom(byteString);
        }

        public static Range parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Range) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Range parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Range) PARSER.parseFrom(bArr);
        }

        public static Range parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Range) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Range parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Range parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Range parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Range parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Range parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Range parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m1129toBuilder();
        }

        public static Builder newBuilder(Range range) {
            return DEFAULT_INSTANCE.m1129toBuilder().mergeFrom(range);
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Range m1124getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.api.Distribution.RangeOrBuilder
        public double getMax() {
            return this.max_;
        }

        @Override // com.google.api.Distribution.RangeOrBuilder
        public double getMin() {
            return this.min_;
        }

        public Parser<Range> getParserForType() {
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
            return DistributionProto.internal_static_google_api_Distribution_Range_fieldAccessorTable.ensureFieldAccessorsInitialized(Range.class, Builder.class);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            double d = this.min_;
            if (d != 0.0d) {
                codedOutputStream.writeDouble(1, d);
            }
            double d2 = this.max_;
            if (d2 != 0.0d) {
                codedOutputStream.writeDouble(2, d2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            double d = this.min_;
            int iComputeDoubleSize = d != 0.0d ? CodedOutputStream.computeDoubleSize(1, d) : 0;
            double d2 = this.max_;
            if (d2 != 0.0d) {
                iComputeDoubleSize += CodedOutputStream.computeDoubleSize(2, d2);
            }
            int serializedSize = iComputeDoubleSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Range)) {
                return super.equals(obj);
            }
            Range range = (Range) obj;
            return Double.doubleToLongBits(getMin()) == Double.doubleToLongBits(range.getMin()) && Double.doubleToLongBits(getMax()) == Double.doubleToLongBits(range.getMax()) && this.unknownFields.equals(range.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getMin()))) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getMax()))) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1127newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1129toBuilder() {
            if (this == DEFAULT_INSTANCE) {
                return new Builder();
            }
            return new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
        public Builder m1126newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RangeOrBuilder {
            private double max_;
            private double min_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_Range_descriptor;
            }

            @Override // com.google.api.Distribution.RangeOrBuilder
            public double getMax() {
                return this.max_;
            }

            public Builder setMax(double d) {
                this.max_ = d;
                onChanged();
                return this;
            }

            @Override // com.google.api.Distribution.RangeOrBuilder
            public double getMin() {
                return this.min_;
            }

            public Builder setMin(double d) {
                this.min_ = d;
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.internal_static_google_api_Distribution_Range_fieldAccessorTable.ensureFieldAccessorsInitialized(Range.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Range.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1140clear() {
                super.clear();
                this.min_ = 0.0d;
                this.max_ = 0.0d;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return DistributionProto.internal_static_google_api_Distribution_Range_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Range m1153getDefaultInstanceForType() {
                return Range.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Range m1134build() throws UninitializedMessageException {
                Range rangeM1136buildPartial = m1136buildPartial();
                if (rangeM1136buildPartial.isInitialized()) {
                    return rangeM1136buildPartial;
                }
                throw newUninitializedMessageException(rangeM1136buildPartial);
            }

            /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Range m1136buildPartial() {
                Range range = new Range(this);
                range.min_ = this.min_;
                range.max_ = this.max_;
                onBuilt();
                return range;
            }

            /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1151clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1164setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1142clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1145clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1166setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1132addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1158mergeFrom(Message message) {
                if (message instanceof Range) {
                    return mergeFrom((Range) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Range range) {
                if (range == Range.getDefaultInstance()) {
                    return this;
                }
                if (range.getMin() != 0.0d) {
                    setMin(range.getMin());
                }
                if (range.getMax() != 0.0d) {
                    setMax(range.getMax());
                }
                m1162mergeUnknownFields(range.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.google.api.Distribution.Range.Builder m1159mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.api.Distribution.Range.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.google.api.Distribution$Range r3 = (com.google.api.Distribution.Range) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.google.api.Distribution$Range r4 = (com.google.api.Distribution.Range) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.Range.Builder.m1159mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Distribution$Range$Builder");
            }

            public Builder clearMin() {
                this.min_ = 0.0d;
                onChanged();
                return this;
            }

            public Builder clearMax() {
                this.max_ = 0.0d;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m1168setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m1162mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class BucketOptions extends GeneratedMessageV3 implements BucketOptionsOrBuilder {
        public static final int EXPLICIT_BUCKETS_FIELD_NUMBER = 3;
        public static final int EXPONENTIAL_BUCKETS_FIELD_NUMBER = 2;
        public static final int LINEAR_BUCKETS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final BucketOptions DEFAULT_INSTANCE = new BucketOptions();
        private static final Parser<BucketOptions> PARSER = new AbstractParser<BucketOptions>() { // from class: com.google.api.Distribution.BucketOptions.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public BucketOptions m862parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new BucketOptions(codedInputStream, extensionRegistryLite);
            }
        };
        private byte memoizedIsInitialized;
        private int optionsCase_;
        private Object options_;

        private BucketOptions(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.optionsCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private BucketOptions() {
            this.optionsCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private BucketOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    Linear.Builder builderM999toBuilder = this.optionsCase_ == 1 ? ((Linear) this.options_).m999toBuilder() : null;
                                    MessageLite message = codedInputStream.readMessage(Linear.parser(), extensionRegistryLite);
                                    this.options_ = message;
                                    if (builderM999toBuilder != null) {
                                        builderM999toBuilder.mergeFrom((Linear) message);
                                        this.options_ = builderM999toBuilder.m1006buildPartial();
                                    }
                                    this.optionsCase_ = 1;
                                } else if (tag == 18) {
                                    Exponential.Builder builderM953toBuilder = this.optionsCase_ == 2 ? ((Exponential) this.options_).m953toBuilder() : null;
                                    MessageLite message2 = codedInputStream.readMessage(Exponential.parser(), extensionRegistryLite);
                                    this.options_ = message2;
                                    if (builderM953toBuilder != null) {
                                        builderM953toBuilder.mergeFrom((Exponential) message2);
                                        this.options_ = builderM953toBuilder.m960buildPartial();
                                    }
                                    this.optionsCase_ = 2;
                                } else if (tag == 26) {
                                    Explicit.Builder builderM907toBuilder = this.optionsCase_ == 3 ? ((Explicit) this.options_).m907toBuilder() : null;
                                    MessageLite message3 = codedInputStream.readMessage(Explicit.parser(), extensionRegistryLite);
                                    this.options_ = message3;
                                    if (builderM907toBuilder != null) {
                                        builderM907toBuilder.mergeFrom((Explicit) message3);
                                        this.options_ = builderM907toBuilder.m914buildPartial();
                                    }
                                    this.optionsCase_ = 3;
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

        public static BucketOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BucketOptions> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DistributionProto.internal_static_google_api_Distribution_BucketOptions_descriptor;
        }

        public static BucketOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (BucketOptions) PARSER.parseFrom(byteBuffer);
        }

        public static BucketOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BucketOptions) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (BucketOptions) PARSER.parseFrom(byteString);
        }

        public static BucketOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BucketOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (BucketOptions) PARSER.parseFrom(bArr);
        }

        public static BucketOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BucketOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static BucketOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BucketOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static BucketOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static BucketOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m861toBuilder();
        }

        public static Builder newBuilder(BucketOptions bucketOptions) {
            return DEFAULT_INSTANCE.m861toBuilder().mergeFrom(bucketOptions);
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public BucketOptions m856getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<BucketOptions> getParserForType() {
            return PARSER;
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public boolean hasExplicitBuckets() {
            return this.optionsCase_ == 3;
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public boolean hasExponentialBuckets() {
            return this.optionsCase_ == 2;
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public boolean hasLinearBuckets() {
            return this.optionsCase_ == 1;
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
            return DistributionProto.internal_static_google_api_Distribution_BucketOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(BucketOptions.class, Builder.class);
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public OptionsCase getOptionsCase() {
            return OptionsCase.forNumber(this.optionsCase_);
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public Linear getLinearBuckets() {
            if (this.optionsCase_ == 1) {
                return (Linear) this.options_;
            }
            return Linear.getDefaultInstance();
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public LinearOrBuilder getLinearBucketsOrBuilder() {
            if (this.optionsCase_ == 1) {
                return (Linear) this.options_;
            }
            return Linear.getDefaultInstance();
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public Exponential getExponentialBuckets() {
            if (this.optionsCase_ == 2) {
                return (Exponential) this.options_;
            }
            return Exponential.getDefaultInstance();
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public ExponentialOrBuilder getExponentialBucketsOrBuilder() {
            if (this.optionsCase_ == 2) {
                return (Exponential) this.options_;
            }
            return Exponential.getDefaultInstance();
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public Explicit getExplicitBuckets() {
            if (this.optionsCase_ == 3) {
                return (Explicit) this.options_;
            }
            return Explicit.getDefaultInstance();
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public ExplicitOrBuilder getExplicitBucketsOrBuilder() {
            if (this.optionsCase_ == 3) {
                return (Explicit) this.options_;
            }
            return Explicit.getDefaultInstance();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.optionsCase_ == 1) {
                codedOutputStream.writeMessage(1, (Linear) this.options_);
            }
            if (this.optionsCase_ == 2) {
                codedOutputStream.writeMessage(2, (Exponential) this.options_);
            }
            if (this.optionsCase_ == 3) {
                codedOutputStream.writeMessage(3, (Explicit) this.options_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = this.optionsCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (Linear) this.options_) : 0;
            if (this.optionsCase_ == 2) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(2, (Exponential) this.options_);
            }
            if (this.optionsCase_ == 3) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(3, (Explicit) this.options_);
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof BucketOptions)) {
                return super.equals(obj);
            }
            BucketOptions bucketOptions = (BucketOptions) obj;
            if (!getOptionsCase().equals(bucketOptions.getOptionsCase())) {
                return false;
            }
            int i = this.optionsCase_;
            if (i != 1) {
                if (i == 2) {
                    if (!getExponentialBuckets().equals(bucketOptions.getExponentialBuckets())) {
                        return false;
                    }
                } else if (i == 3 && !getExplicitBuckets().equals(bucketOptions.getExplicitBuckets())) {
                    return false;
                }
            } else if (!getLinearBuckets().equals(bucketOptions.getLinearBuckets())) {
                return false;
            }
            return this.unknownFields.equals(bucketOptions.unknownFields);
        }

        public int hashCode() {
            int i;
            int iHashCode;
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode2 = 779 + getDescriptor().hashCode();
            int i2 = this.optionsCase_;
            if (i2 == 1) {
                i = ((iHashCode2 * 37) + 1) * 53;
                iHashCode = getLinearBuckets().hashCode();
            } else if (i2 == 2) {
                i = ((iHashCode2 * 37) + 2) * 53;
                iHashCode = getExponentialBuckets().hashCode();
            } else {
                if (i2 == 3) {
                    i = ((iHashCode2 * 37) + 3) * 53;
                    iHashCode = getExplicitBuckets().hashCode();
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

        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m859newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m861toBuilder() {
            if (this == DEFAULT_INSTANCE) {
                return new Builder();
            }
            return new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
        public Builder m858newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public enum OptionsCase implements Internal.EnumLite {
            LINEAR_BUCKETS(1),
            EXPONENTIAL_BUCKETS(2),
            EXPLICIT_BUCKETS(3),
            OPTIONS_NOT_SET(0);

            private final int value;

            OptionsCase(int i) {
                this.value = i;
            }

            public static OptionsCase forNumber(int i) {
                if (i == 0) {
                    return OPTIONS_NOT_SET;
                }
                if (i == 1) {
                    return LINEAR_BUCKETS;
                }
                if (i == 2) {
                    return EXPONENTIAL_BUCKETS;
                }
                if (i != 3) {
                    return null;
                }
                return EXPLICIT_BUCKETS;
            }

            @Deprecated
            public static OptionsCase valueOf(int i) {
                return forNumber(i);
            }

            public int getNumber() {
                return this.value;
            }
        }

        public interface ExplicitOrBuilder extends MessageOrBuilder {
            double getBounds(int i);

            int getBoundsCount();

            List<Double> getBoundsList();
        }

        public interface ExponentialOrBuilder extends MessageOrBuilder {
            double getGrowthFactor();

            int getNumFiniteBuckets();

            double getScale();
        }

        public interface LinearOrBuilder extends MessageOrBuilder {
            int getNumFiniteBuckets();

            double getOffset();

            double getWidth();
        }

        public static final class Linear extends GeneratedMessageV3 implements LinearOrBuilder {
            public static final int NUM_FINITE_BUCKETS_FIELD_NUMBER = 1;
            public static final int OFFSET_FIELD_NUMBER = 3;
            public static final int WIDTH_FIELD_NUMBER = 2;
            private static final long serialVersionUID = 0;
            private static final Linear DEFAULT_INSTANCE = new Linear();
            private static final Parser<Linear> PARSER = new AbstractParser<Linear>() { // from class: com.google.api.Distribution.BucketOptions.Linear.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public Linear m1000parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Linear(codedInputStream, extensionRegistryLite);
                }
            };
            private byte memoizedIsInitialized;
            private int numFiniteBuckets_;
            private double offset_;
            private double width_;

            private Linear(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private Linear() {
                this.memoizedIsInitialized = (byte) -1;
            }

            private Linear(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 8) {
                                    this.numFiniteBuckets_ = codedInputStream.readInt32();
                                } else if (tag == 17) {
                                    this.width_ = codedInputStream.readDouble();
                                } else if (tag == 25) {
                                    this.offset_ = codedInputStream.readDouble();
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
                        this.unknownFields = builderNewBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static Linear getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Linear> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_descriptor;
            }

            public static Linear parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Linear) PARSER.parseFrom(byteBuffer);
            }

            public static Linear parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Linear) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Linear parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Linear) PARSER.parseFrom(byteString);
            }

            public static Linear parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Linear) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Linear parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Linear) PARSER.parseFrom(bArr);
            }

            public static Linear parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Linear) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Linear parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Linear parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Linear parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Linear parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Linear parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Linear parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m999toBuilder();
            }

            public static Builder newBuilder(Linear linear) {
                return DEFAULT_INSTANCE.m999toBuilder().mergeFrom(linear);
            }

            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Linear m994getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
            public int getNumFiniteBuckets() {
                return this.numFiniteBuckets_;
            }

            @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
            public double getOffset() {
                return this.offset_;
            }

            public Parser<Linear> getParserForType() {
                return PARSER;
            }

            @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
            public double getWidth() {
                return this.width_;
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
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_fieldAccessorTable.ensureFieldAccessorsInitialized(Linear.class, Builder.class);
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                int i = this.numFiniteBuckets_;
                if (i != 0) {
                    codedOutputStream.writeInt32(1, i);
                }
                double d = this.width_;
                if (d != 0.0d) {
                    codedOutputStream.writeDouble(2, d);
                }
                double d2 = this.offset_;
                if (d2 != 0.0d) {
                    codedOutputStream.writeDouble(3, d2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.numFiniteBuckets_;
                int iComputeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) : 0;
                double d = this.width_;
                if (d != 0.0d) {
                    iComputeInt32Size += CodedOutputStream.computeDoubleSize(2, d);
                }
                double d2 = this.offset_;
                if (d2 != 0.0d) {
                    iComputeInt32Size += CodedOutputStream.computeDoubleSize(3, d2);
                }
                int serializedSize = iComputeInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Linear)) {
                    return super.equals(obj);
                }
                Linear linear = (Linear) obj;
                return getNumFiniteBuckets() == linear.getNumFiniteBuckets() && Double.doubleToLongBits(getWidth()) == Double.doubleToLongBits(linear.getWidth()) && Double.doubleToLongBits(getOffset()) == Double.doubleToLongBits(linear.getOffset()) && this.unknownFields.equals(linear.unknownFields);
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getNumFiniteBuckets()) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getWidth()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getOffset()))) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode;
                return iHashCode;
            }

            /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m997newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m999toBuilder() {
                if (this == DEFAULT_INSTANCE) {
                    return new Builder();
                }
                return new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
            public Builder m996newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LinearOrBuilder {
                private int numFiniteBuckets_;
                private double offset_;
                private double width_;

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_descriptor;
                }

                @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
                public int getNumFiniteBuckets() {
                    return this.numFiniteBuckets_;
                }

                public Builder setNumFiniteBuckets(int i) {
                    this.numFiniteBuckets_ = i;
                    onChanged();
                    return this;
                }

                @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
                public double getOffset() {
                    return this.offset_;
                }

                public Builder setOffset(double d) {
                    this.offset_ = d;
                    onChanged();
                    return this;
                }

                @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
                public double getWidth() {
                    return this.width_;
                }

                public Builder setWidth(double d) {
                    this.width_ = d;
                    onChanged();
                    return this;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_fieldAccessorTable.ensureFieldAccessorsInitialized(Linear.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = Linear.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m1010clear() {
                    super.clear();
                    this.numFiniteBuckets_ = 0;
                    this.width_ = 0.0d;
                    this.offset_ = 0.0d;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Linear m1023getDefaultInstanceForType() {
                    return Linear.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Linear m1004build() throws UninitializedMessageException {
                    Linear linearM1006buildPartial = m1006buildPartial();
                    if (linearM1006buildPartial.isInitialized()) {
                        return linearM1006buildPartial;
                    }
                    throw newUninitializedMessageException(linearM1006buildPartial);
                }

                /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Linear m1006buildPartial() {
                    Linear linear = new Linear(this);
                    linear.numFiniteBuckets_ = this.numFiniteBuckets_;
                    linear.width_ = this.width_;
                    linear.offset_ = this.offset_;
                    onBuilt();
                    return linear;
                }

                /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m1021clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m1034setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m1012clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m1015clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m1036setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m1002addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m1028mergeFrom(Message message) {
                    if (message instanceof Linear) {
                        return mergeFrom((Linear) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Linear linear) {
                    if (linear == Linear.getDefaultInstance()) {
                        return this;
                    }
                    if (linear.getNumFiniteBuckets() != 0) {
                        setNumFiniteBuckets(linear.getNumFiniteBuckets());
                    }
                    if (linear.getWidth() != 0.0d) {
                        setWidth(linear.getWidth());
                    }
                    if (linear.getOffset() != 0.0d) {
                        setOffset(linear.getOffset());
                    }
                    m1032mergeUnknownFields(linear.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.google.api.Distribution.BucketOptions.Linear.Builder m1029mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.google.api.Distribution.BucketOptions.Linear.access$1700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.google.api.Distribution$BucketOptions$Linear r3 = (com.google.api.Distribution.BucketOptions.Linear) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        com.google.api.Distribution$BucketOptions$Linear r4 = (com.google.api.Distribution.BucketOptions.Linear) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.BucketOptions.Linear.Builder.m1029mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Distribution$BucketOptions$Linear$Builder");
                }

                public Builder clearNumFiniteBuckets() {
                    this.numFiniteBuckets_ = 0;
                    onChanged();
                    return this;
                }

                public Builder clearWidth() {
                    this.width_ = 0.0d;
                    onChanged();
                    return this;
                }

                public Builder clearOffset() {
                    this.offset_ = 0.0d;
                    onChanged();
                    return this;
                }

                /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m1038setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m1032mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        public static final class Exponential extends GeneratedMessageV3 implements ExponentialOrBuilder {
            public static final int GROWTH_FACTOR_FIELD_NUMBER = 2;
            public static final int NUM_FINITE_BUCKETS_FIELD_NUMBER = 1;
            public static final int SCALE_FIELD_NUMBER = 3;
            private static final long serialVersionUID = 0;
            private static final Exponential DEFAULT_INSTANCE = new Exponential();
            private static final Parser<Exponential> PARSER = new AbstractParser<Exponential>() { // from class: com.google.api.Distribution.BucketOptions.Exponential.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public Exponential m954parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Exponential(codedInputStream, extensionRegistryLite);
                }
            };
            private double growthFactor_;
            private byte memoizedIsInitialized;
            private int numFiniteBuckets_;
            private double scale_;

            private Exponential(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private Exponential() {
                this.memoizedIsInitialized = (byte) -1;
            }

            private Exponential(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 8) {
                                    this.numFiniteBuckets_ = codedInputStream.readInt32();
                                } else if (tag == 17) {
                                    this.growthFactor_ = codedInputStream.readDouble();
                                } else if (tag == 25) {
                                    this.scale_ = codedInputStream.readDouble();
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
                        this.unknownFields = builderNewBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static Exponential getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Exponential> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_descriptor;
            }

            public static Exponential parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Exponential) PARSER.parseFrom(byteBuffer);
            }

            public static Exponential parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Exponential) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Exponential parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Exponential) PARSER.parseFrom(byteString);
            }

            public static Exponential parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Exponential) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Exponential parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Exponential) PARSER.parseFrom(bArr);
            }

            public static Exponential parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Exponential) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Exponential parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Exponential parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Exponential parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Exponential parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Exponential parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Exponential parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m953toBuilder();
            }

            public static Builder newBuilder(Exponential exponential) {
                return DEFAULT_INSTANCE.m953toBuilder().mergeFrom(exponential);
            }

            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Exponential m948getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
            public double getGrowthFactor() {
                return this.growthFactor_;
            }

            @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
            public int getNumFiniteBuckets() {
                return this.numFiniteBuckets_;
            }

            public Parser<Exponential> getParserForType() {
                return PARSER;
            }

            @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
            public double getScale() {
                return this.scale_;
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
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_fieldAccessorTable.ensureFieldAccessorsInitialized(Exponential.class, Builder.class);
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                int i = this.numFiniteBuckets_;
                if (i != 0) {
                    codedOutputStream.writeInt32(1, i);
                }
                double d = this.growthFactor_;
                if (d != 0.0d) {
                    codedOutputStream.writeDouble(2, d);
                }
                double d2 = this.scale_;
                if (d2 != 0.0d) {
                    codedOutputStream.writeDouble(3, d2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.numFiniteBuckets_;
                int iComputeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) : 0;
                double d = this.growthFactor_;
                if (d != 0.0d) {
                    iComputeInt32Size += CodedOutputStream.computeDoubleSize(2, d);
                }
                double d2 = this.scale_;
                if (d2 != 0.0d) {
                    iComputeInt32Size += CodedOutputStream.computeDoubleSize(3, d2);
                }
                int serializedSize = iComputeInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Exponential)) {
                    return super.equals(obj);
                }
                Exponential exponential = (Exponential) obj;
                return getNumFiniteBuckets() == exponential.getNumFiniteBuckets() && Double.doubleToLongBits(getGrowthFactor()) == Double.doubleToLongBits(exponential.getGrowthFactor()) && Double.doubleToLongBits(getScale()) == Double.doubleToLongBits(exponential.getScale()) && this.unknownFields.equals(exponential.unknownFields);
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getNumFiniteBuckets()) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getGrowthFactor()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getScale()))) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode;
                return iHashCode;
            }

            /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m951newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m953toBuilder() {
                if (this == DEFAULT_INSTANCE) {
                    return new Builder();
                }
                return new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
            public Builder m950newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExponentialOrBuilder {
                private double growthFactor_;
                private int numFiniteBuckets_;
                private double scale_;

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_descriptor;
                }

                @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
                public double getGrowthFactor() {
                    return this.growthFactor_;
                }

                public Builder setGrowthFactor(double d) {
                    this.growthFactor_ = d;
                    onChanged();
                    return this;
                }

                @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
                public int getNumFiniteBuckets() {
                    return this.numFiniteBuckets_;
                }

                public Builder setNumFiniteBuckets(int i) {
                    this.numFiniteBuckets_ = i;
                    onChanged();
                    return this;
                }

                @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
                public double getScale() {
                    return this.scale_;
                }

                public Builder setScale(double d) {
                    this.scale_ = d;
                    onChanged();
                    return this;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_fieldAccessorTable.ensureFieldAccessorsInitialized(Exponential.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = Exponential.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m964clear() {
                    super.clear();
                    this.numFiniteBuckets_ = 0;
                    this.growthFactor_ = 0.0d;
                    this.scale_ = 0.0d;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Exponential m977getDefaultInstanceForType() {
                    return Exponential.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Exponential m958build() throws UninitializedMessageException {
                    Exponential exponentialM960buildPartial = m960buildPartial();
                    if (exponentialM960buildPartial.isInitialized()) {
                        return exponentialM960buildPartial;
                    }
                    throw newUninitializedMessageException(exponentialM960buildPartial);
                }

                /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Exponential m960buildPartial() {
                    Exponential exponential = new Exponential(this);
                    exponential.numFiniteBuckets_ = this.numFiniteBuckets_;
                    exponential.growthFactor_ = this.growthFactor_;
                    exponential.scale_ = this.scale_;
                    onBuilt();
                    return exponential;
                }

                /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m975clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m988setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m966clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m969clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m990setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m956addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m982mergeFrom(Message message) {
                    if (message instanceof Exponential) {
                        return mergeFrom((Exponential) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Exponential exponential) {
                    if (exponential == Exponential.getDefaultInstance()) {
                        return this;
                    }
                    if (exponential.getNumFiniteBuckets() != 0) {
                        setNumFiniteBuckets(exponential.getNumFiniteBuckets());
                    }
                    if (exponential.getGrowthFactor() != 0.0d) {
                        setGrowthFactor(exponential.getGrowthFactor());
                    }
                    if (exponential.getScale() != 0.0d) {
                        setScale(exponential.getScale());
                    }
                    m986mergeUnknownFields(exponential.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.google.api.Distribution.BucketOptions.Exponential.Builder m983mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.google.api.Distribution.BucketOptions.Exponential.access$2700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.google.api.Distribution$BucketOptions$Exponential r3 = (com.google.api.Distribution.BucketOptions.Exponential) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        com.google.api.Distribution$BucketOptions$Exponential r4 = (com.google.api.Distribution.BucketOptions.Exponential) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.BucketOptions.Exponential.Builder.m983mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Distribution$BucketOptions$Exponential$Builder");
                }

                public Builder clearNumFiniteBuckets() {
                    this.numFiniteBuckets_ = 0;
                    onChanged();
                    return this;
                }

                public Builder clearGrowthFactor() {
                    this.growthFactor_ = 0.0d;
                    onChanged();
                    return this;
                }

                public Builder clearScale() {
                    this.scale_ = 0.0d;
                    onChanged();
                    return this;
                }

                /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m992setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m986mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        public static final class Explicit extends GeneratedMessageV3 implements ExplicitOrBuilder {
            public static final int BOUNDS_FIELD_NUMBER = 1;
            private static final Explicit DEFAULT_INSTANCE = new Explicit();
            private static final Parser<Explicit> PARSER = new AbstractParser<Explicit>() { // from class: com.google.api.Distribution.BucketOptions.Explicit.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public Explicit m908parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Explicit(codedInputStream, extensionRegistryLite);
                }
            };
            private static final long serialVersionUID = 0;
            private int boundsMemoizedSerializedSize;
            private Internal.DoubleList bounds_;
            private byte memoizedIsInitialized;

            private Explicit(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.boundsMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = (byte) -1;
            }

            private Explicit() {
                this.boundsMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = (byte) -1;
                this.bounds_ = emptyDoubleList();
            }

            private Explicit(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (tag == 9) {
                                    if (!(z2 & true)) {
                                        this.bounds_ = newDoubleList();
                                        z2 |= true;
                                    }
                                    this.bounds_.addDouble(codedInputStream.readDouble());
                                } else if (tag == 10) {
                                    int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                                        this.bounds_ = newDoubleList();
                                        z2 |= true;
                                    }
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.bounds_.addDouble(codedInputStream.readDouble());
                                    }
                                    codedInputStream.popLimit(iPushLimit);
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
                            this.bounds_.makeImmutable();
                        }
                        this.unknownFields = builderNewBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static Explicit getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Explicit> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_descriptor;
            }

            public static Explicit parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Explicit) PARSER.parseFrom(byteBuffer);
            }

            public static Explicit parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Explicit) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Explicit parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Explicit) PARSER.parseFrom(byteString);
            }

            public static Explicit parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Explicit) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Explicit parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Explicit) PARSER.parseFrom(bArr);
            }

            public static Explicit parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Explicit) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Explicit parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Explicit parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Explicit parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Explicit parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Explicit parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Explicit parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m907toBuilder();
            }

            public static Builder newBuilder(Explicit explicit) {
                return DEFAULT_INSTANCE.m907toBuilder().mergeFrom(explicit);
            }

            @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
            public List<Double> getBoundsList() {
                return this.bounds_;
            }

            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Explicit m902getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public Parser<Explicit> getParserForType() {
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
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_fieldAccessorTable.ensureFieldAccessorsInitialized(Explicit.class, Builder.class);
            }

            @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
            public int getBoundsCount() {
                return this.bounds_.size();
            }

            @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
            public double getBounds(int i) {
                return this.bounds_.getDouble(i);
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                getSerializedSize();
                if (getBoundsList().size() > 0) {
                    codedOutputStream.writeUInt32NoTag(10);
                    codedOutputStream.writeUInt32NoTag(this.boundsMemoizedSerializedSize);
                }
                for (int i = 0; i < this.bounds_.size(); i++) {
                    codedOutputStream.writeDoubleNoTag(this.bounds_.getDouble(i));
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int size = getBoundsList().size() * 8;
                int iComputeInt32SizeNoTag = !getBoundsList().isEmpty() ? size + 1 + CodedOutputStream.computeInt32SizeNoTag(size) : size;
                this.boundsMemoizedSerializedSize = size;
                int serializedSize = iComputeInt32SizeNoTag + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Explicit)) {
                    return super.equals(obj);
                }
                Explicit explicit = (Explicit) obj;
                return getBoundsList().equals(explicit.getBoundsList()) && this.unknownFields.equals(explicit.unknownFields);
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = 779 + getDescriptor().hashCode();
                if (getBoundsCount() > 0) {
                    iHashCode = (((iHashCode * 37) + 1) * 53) + getBoundsList().hashCode();
                }
                int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m905newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m907toBuilder() {
                if (this == DEFAULT_INSTANCE) {
                    return new Builder();
                }
                return new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
            public Builder m904newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExplicitOrBuilder {
                private int bitField0_;
                private Internal.DoubleList bounds_;

                private Builder() {
                    this.bounds_ = Explicit.emptyDoubleList();
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.bounds_ = Explicit.emptyDoubleList();
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_descriptor;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_fieldAccessorTable.ensureFieldAccessorsInitialized(Explicit.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = Explicit.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m918clear() {
                    super.clear();
                    this.bounds_ = Explicit.emptyDoubleList();
                    this.bitField0_ &= -2;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Explicit m931getDefaultInstanceForType() {
                    return Explicit.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Explicit m912build() throws UninitializedMessageException {
                    Explicit explicitM914buildPartial = m914buildPartial();
                    if (explicitM914buildPartial.isInitialized()) {
                        return explicitM914buildPartial;
                    }
                    throw newUninitializedMessageException(explicitM914buildPartial);
                }

                /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Explicit m914buildPartial() {
                    Explicit explicit = new Explicit(this);
                    if ((this.bitField0_ & 1) != 0) {
                        this.bounds_.makeImmutable();
                        this.bitField0_ &= -2;
                    }
                    explicit.bounds_ = this.bounds_;
                    onBuilt();
                    return explicit;
                }

                /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m929clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m942setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m920clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m923clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m944setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m910addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m936mergeFrom(Message message) {
                    if (message instanceof Explicit) {
                        return mergeFrom((Explicit) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Explicit explicit) {
                    if (explicit == Explicit.getDefaultInstance()) {
                        return this;
                    }
                    if (!explicit.bounds_.isEmpty()) {
                        if (this.bounds_.isEmpty()) {
                            this.bounds_ = explicit.bounds_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureBoundsIsMutable();
                            this.bounds_.addAll(explicit.bounds_);
                        }
                        onChanged();
                    }
                    m940mergeUnknownFields(explicit.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.google.api.Distribution.BucketOptions.Explicit.Builder m937mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.google.api.Distribution.BucketOptions.Explicit.access$3600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.google.api.Distribution$BucketOptions$Explicit r3 = (com.google.api.Distribution.BucketOptions.Explicit) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        com.google.api.Distribution$BucketOptions$Explicit r4 = (com.google.api.Distribution.BucketOptions.Explicit) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.BucketOptions.Explicit.Builder.m937mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Distribution$BucketOptions$Explicit$Builder");
                }

                private void ensureBoundsIsMutable() {
                    if ((this.bitField0_ & 1) == 0) {
                        this.bounds_ = Explicit.mutableCopy(this.bounds_);
                        this.bitField0_ |= 1;
                    }
                }

                @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
                public List<Double> getBoundsList() {
                    return (this.bitField0_ & 1) != 0 ? Collections.unmodifiableList(this.bounds_) : this.bounds_;
                }

                @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
                public int getBoundsCount() {
                    return this.bounds_.size();
                }

                @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
                public double getBounds(int i) {
                    return this.bounds_.getDouble(i);
                }

                public Builder setBounds(int i, double d) {
                    ensureBoundsIsMutable();
                    this.bounds_.setDouble(i, d);
                    onChanged();
                    return this;
                }

                public Builder addBounds(double d) {
                    ensureBoundsIsMutable();
                    this.bounds_.addDouble(d);
                    onChanged();
                    return this;
                }

                public Builder addAllBounds(Iterable<? extends Double> iterable) {
                    ensureBoundsIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.bounds_);
                    onChanged();
                    return this;
                }

                public Builder clearBounds() {
                    this.bounds_ = Explicit.emptyDoubleList();
                    this.bitField0_ &= -2;
                    onChanged();
                    return this;
                }

                /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m946setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m940mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BucketOptionsOrBuilder {
            private SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> explicitBucketsBuilder_;
            private SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> exponentialBucketsBuilder_;
            private SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> linearBucketsBuilder_;
            private int optionsCase_;
            private Object options_;

            private Builder() {
                this.optionsCase_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.optionsCase_ = 0;
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_descriptor;
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public boolean hasExplicitBuckets() {
                return this.optionsCase_ == 3;
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public boolean hasExponentialBuckets() {
                return this.optionsCase_ == 2;
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public boolean hasLinearBuckets() {
                return this.optionsCase_ == 1;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(BucketOptions.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = BucketOptions.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m872clear() {
                super.clear();
                this.optionsCase_ = 0;
                this.options_ = null;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public BucketOptions m885getDefaultInstanceForType() {
                return BucketOptions.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public BucketOptions m866build() throws UninitializedMessageException {
                BucketOptions bucketOptionsM868buildPartial = m868buildPartial();
                if (bucketOptionsM868buildPartial.isInitialized()) {
                    return bucketOptionsM868buildPartial;
                }
                throw newUninitializedMessageException(bucketOptionsM868buildPartial);
            }

            /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public BucketOptions m868buildPartial() {
                BucketOptions bucketOptions = new BucketOptions(this);
                if (this.optionsCase_ == 1) {
                    SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        bucketOptions.options_ = this.options_;
                    } else {
                        bucketOptions.options_ = singleFieldBuilderV3.build();
                    }
                }
                if (this.optionsCase_ == 2) {
                    SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> singleFieldBuilderV32 = this.exponentialBucketsBuilder_;
                    if (singleFieldBuilderV32 == null) {
                        bucketOptions.options_ = this.options_;
                    } else {
                        bucketOptions.options_ = singleFieldBuilderV32.build();
                    }
                }
                if (this.optionsCase_ == 3) {
                    SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> singleFieldBuilderV33 = this.explicitBucketsBuilder_;
                    if (singleFieldBuilderV33 == null) {
                        bucketOptions.options_ = this.options_;
                    } else {
                        bucketOptions.options_ = singleFieldBuilderV33.build();
                    }
                }
                bucketOptions.optionsCase_ = this.optionsCase_;
                onBuilt();
                return bucketOptions;
            }

            /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m883clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m896setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m874clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m877clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m898setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m864addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m890mergeFrom(Message message) {
                if (message instanceof BucketOptions) {
                    return mergeFrom((BucketOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(BucketOptions bucketOptions) {
                if (bucketOptions == BucketOptions.getDefaultInstance()) {
                    return this;
                }
                int i = AnonymousClass2.$SwitchMap$com$google$api$Distribution$BucketOptions$OptionsCase[bucketOptions.getOptionsCase().ordinal()];
                if (i == 1) {
                    mergeLinearBuckets(bucketOptions.getLinearBuckets());
                } else if (i == 2) {
                    mergeExponentialBuckets(bucketOptions.getExponentialBuckets());
                } else if (i == 3) {
                    mergeExplicitBuckets(bucketOptions.getExplicitBuckets());
                }
                m894mergeUnknownFields(bucketOptions.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.google.api.Distribution.BucketOptions.Builder m891mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.api.Distribution.BucketOptions.access$4800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.google.api.Distribution$BucketOptions r3 = (com.google.api.Distribution.BucketOptions) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.google.api.Distribution$BucketOptions r4 = (com.google.api.Distribution.BucketOptions) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.BucketOptions.Builder.m891mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Distribution$BucketOptions$Builder");
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public OptionsCase getOptionsCase() {
                return OptionsCase.forNumber(this.optionsCase_);
            }

            public Builder clearOptions() {
                this.optionsCase_ = 0;
                this.options_ = null;
                onChanged();
                return this;
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public Linear getLinearBuckets() {
                SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ == 1) {
                        return (Linear) this.options_;
                    }
                    return Linear.getDefaultInstance();
                }
                if (this.optionsCase_ == 1) {
                    return singleFieldBuilderV3.getMessage();
                }
                return Linear.getDefaultInstance();
            }

            public Builder setLinearBuckets(Linear linear) {
                SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    linear.getClass();
                    this.options_ = linear;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(linear);
                }
                this.optionsCase_ = 1;
                return this;
            }

            public Builder setLinearBuckets(Linear.Builder builder) {
                SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.options_ = builder.m1004build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m1004build());
                }
                this.optionsCase_ = 1;
                return this;
            }

            public Builder mergeLinearBuckets(Linear linear) {
                SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ != 1 || this.options_ == Linear.getDefaultInstance()) {
                        this.options_ = linear;
                    } else {
                        this.options_ = Linear.newBuilder((Linear) this.options_).mergeFrom(linear).m1006buildPartial();
                    }
                    onChanged();
                } else {
                    if (this.optionsCase_ == 1) {
                        singleFieldBuilderV3.mergeFrom(linear);
                    }
                    this.linearBucketsBuilder_.setMessage(linear);
                }
                this.optionsCase_ = 1;
                return this;
            }

            public Builder clearLinearBuckets() {
                SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    if (this.optionsCase_ == 1) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                    }
                    singleFieldBuilderV3.clear();
                } else if (this.optionsCase_ == 1) {
                    this.optionsCase_ = 0;
                    this.options_ = null;
                    onChanged();
                }
                return this;
            }

            public Linear.Builder getLinearBucketsBuilder() {
                return getLinearBucketsFieldBuilder().getBuilder();
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public LinearOrBuilder getLinearBucketsOrBuilder() {
                SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> singleFieldBuilderV3;
                int i = this.optionsCase_;
                if (i == 1 && (singleFieldBuilderV3 = this.linearBucketsBuilder_) != null) {
                    return (LinearOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                if (i == 1) {
                    return (Linear) this.options_;
                }
                return Linear.getDefaultInstance();
            }

            private SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> getLinearBucketsFieldBuilder() {
                if (this.linearBucketsBuilder_ == null) {
                    if (this.optionsCase_ != 1) {
                        this.options_ = Linear.getDefaultInstance();
                    }
                    this.linearBucketsBuilder_ = new SingleFieldBuilderV3<>((Linear) this.options_, getParentForChildren(), isClean());
                    this.options_ = null;
                }
                this.optionsCase_ = 1;
                onChanged();
                return this.linearBucketsBuilder_;
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public Exponential getExponentialBuckets() {
                SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> singleFieldBuilderV3 = this.exponentialBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ == 2) {
                        return (Exponential) this.options_;
                    }
                    return Exponential.getDefaultInstance();
                }
                if (this.optionsCase_ == 2) {
                    return singleFieldBuilderV3.getMessage();
                }
                return Exponential.getDefaultInstance();
            }

            public Builder setExponentialBuckets(Exponential exponential) {
                SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> singleFieldBuilderV3 = this.exponentialBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    exponential.getClass();
                    this.options_ = exponential;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(exponential);
                }
                this.optionsCase_ = 2;
                return this;
            }

            public Builder setExponentialBuckets(Exponential.Builder builder) {
                SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> singleFieldBuilderV3 = this.exponentialBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.options_ = builder.m958build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m958build());
                }
                this.optionsCase_ = 2;
                return this;
            }

            public Builder mergeExponentialBuckets(Exponential exponential) {
                SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> singleFieldBuilderV3 = this.exponentialBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ != 2 || this.options_ == Exponential.getDefaultInstance()) {
                        this.options_ = exponential;
                    } else {
                        this.options_ = Exponential.newBuilder((Exponential) this.options_).mergeFrom(exponential).m960buildPartial();
                    }
                    onChanged();
                } else {
                    if (this.optionsCase_ == 2) {
                        singleFieldBuilderV3.mergeFrom(exponential);
                    }
                    this.exponentialBucketsBuilder_.setMessage(exponential);
                }
                this.optionsCase_ = 2;
                return this;
            }

            public Builder clearExponentialBuckets() {
                SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> singleFieldBuilderV3 = this.exponentialBucketsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    if (this.optionsCase_ == 2) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                    }
                    singleFieldBuilderV3.clear();
                } else if (this.optionsCase_ == 2) {
                    this.optionsCase_ = 0;
                    this.options_ = null;
                    onChanged();
                }
                return this;
            }

            public Exponential.Builder getExponentialBucketsBuilder() {
                return getExponentialBucketsFieldBuilder().getBuilder();
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public ExponentialOrBuilder getExponentialBucketsOrBuilder() {
                SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> singleFieldBuilderV3;
                int i = this.optionsCase_;
                if (i == 2 && (singleFieldBuilderV3 = this.exponentialBucketsBuilder_) != null) {
                    return (ExponentialOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                if (i == 2) {
                    return (Exponential) this.options_;
                }
                return Exponential.getDefaultInstance();
            }

            private SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> getExponentialBucketsFieldBuilder() {
                if (this.exponentialBucketsBuilder_ == null) {
                    if (this.optionsCase_ != 2) {
                        this.options_ = Exponential.getDefaultInstance();
                    }
                    this.exponentialBucketsBuilder_ = new SingleFieldBuilderV3<>((Exponential) this.options_, getParentForChildren(), isClean());
                    this.options_ = null;
                }
                this.optionsCase_ = 2;
                onChanged();
                return this.exponentialBucketsBuilder_;
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public Explicit getExplicitBuckets() {
                SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> singleFieldBuilderV3 = this.explicitBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ == 3) {
                        return (Explicit) this.options_;
                    }
                    return Explicit.getDefaultInstance();
                }
                if (this.optionsCase_ == 3) {
                    return singleFieldBuilderV3.getMessage();
                }
                return Explicit.getDefaultInstance();
            }

            public Builder setExplicitBuckets(Explicit explicit) {
                SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> singleFieldBuilderV3 = this.explicitBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    explicit.getClass();
                    this.options_ = explicit;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(explicit);
                }
                this.optionsCase_ = 3;
                return this;
            }

            public Builder setExplicitBuckets(Explicit.Builder builder) {
                SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> singleFieldBuilderV3 = this.explicitBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.options_ = builder.m912build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m912build());
                }
                this.optionsCase_ = 3;
                return this;
            }

            public Builder mergeExplicitBuckets(Explicit explicit) {
                SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> singleFieldBuilderV3 = this.explicitBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ != 3 || this.options_ == Explicit.getDefaultInstance()) {
                        this.options_ = explicit;
                    } else {
                        this.options_ = Explicit.newBuilder((Explicit) this.options_).mergeFrom(explicit).m914buildPartial();
                    }
                    onChanged();
                } else {
                    if (this.optionsCase_ == 3) {
                        singleFieldBuilderV3.mergeFrom(explicit);
                    }
                    this.explicitBucketsBuilder_.setMessage(explicit);
                }
                this.optionsCase_ = 3;
                return this;
            }

            public Builder clearExplicitBuckets() {
                SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> singleFieldBuilderV3 = this.explicitBucketsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    if (this.optionsCase_ == 3) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                    }
                    singleFieldBuilderV3.clear();
                } else if (this.optionsCase_ == 3) {
                    this.optionsCase_ = 0;
                    this.options_ = null;
                    onChanged();
                }
                return this;
            }

            public Explicit.Builder getExplicitBucketsBuilder() {
                return getExplicitBucketsFieldBuilder().getBuilder();
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public ExplicitOrBuilder getExplicitBucketsOrBuilder() {
                SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> singleFieldBuilderV3;
                int i = this.optionsCase_;
                if (i == 3 && (singleFieldBuilderV3 = this.explicitBucketsBuilder_) != null) {
                    return (ExplicitOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                if (i == 3) {
                    return (Explicit) this.options_;
                }
                return Explicit.getDefaultInstance();
            }

            private SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> getExplicitBucketsFieldBuilder() {
                if (this.explicitBucketsBuilder_ == null) {
                    if (this.optionsCase_ != 3) {
                        this.options_ = Explicit.getDefaultInstance();
                    }
                    this.explicitBucketsBuilder_ = new SingleFieldBuilderV3<>((Explicit) this.options_, getParentForChildren(), isClean());
                    this.options_ = null;
                }
                this.optionsCase_ = 3;
                onChanged();
                return this.explicitBucketsBuilder_;
            }

            /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m900setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m894mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    /* renamed from: com.google.api.Distribution$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$api$Distribution$BucketOptions$OptionsCase;

        static {
            int[] iArr = new int[BucketOptions.OptionsCase.values().length];
            $SwitchMap$com$google$api$Distribution$BucketOptions$OptionsCase = iArr;
            try {
                iArr[BucketOptions.OptionsCase.LINEAR_BUCKETS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$api$Distribution$BucketOptions$OptionsCase[BucketOptions.OptionsCase.EXPONENTIAL_BUCKETS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$api$Distribution$BucketOptions$OptionsCase[BucketOptions.OptionsCase.EXPLICIT_BUCKETS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$api$Distribution$BucketOptions$OptionsCase[BucketOptions.OptionsCase.OPTIONS_NOT_SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static final class Exemplar extends GeneratedMessageV3 implements ExemplarOrBuilder {
        public static final int ATTACHMENTS_FIELD_NUMBER = 3;
        public static final int TIMESTAMP_FIELD_NUMBER = 2;
        public static final int VALUE_FIELD_NUMBER = 1;
        private static final Exemplar DEFAULT_INSTANCE = new Exemplar();
        private static final Parser<Exemplar> PARSER = new AbstractParser<Exemplar>() { // from class: com.google.api.Distribution.Exemplar.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Exemplar m1084parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Exemplar(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private List<Any> attachments_;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private Timestamp timestamp_;
        private double value_;

        private Exemplar(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Exemplar() {
            this.memoizedIsInitialized = (byte) -1;
            this.attachments_ = Collections.emptyList();
        }

        private Exemplar(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag == 9) {
                                this.value_ = codedInputStream.readDouble();
                            } else if (tag == 18) {
                                Timestamp timestamp = this.timestamp_;
                                Timestamp.Builder builder = timestamp != null ? timestamp.toBuilder() : null;
                                Timestamp message = codedInputStream.readMessage(Timestamp.parser(), extensionRegistryLite);
                                this.timestamp_ = message;
                                if (builder != null) {
                                    builder.mergeFrom(message);
                                    this.timestamp_ = builder.buildPartial();
                                }
                            } else if (tag == 26) {
                                if ((i & 4) == 0) {
                                    this.attachments_ = new ArrayList();
                                    i |= 4;
                                }
                                this.attachments_.add(codedInputStream.readMessage(Any.parser(), extensionRegistryLite));
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
                        this.attachments_ = Collections.unmodifiableList(this.attachments_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static Exemplar getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Exemplar> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DistributionProto.internal_static_google_api_Distribution_Exemplar_descriptor;
        }

        public static Exemplar parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Exemplar) PARSER.parseFrom(byteBuffer);
        }

        public static Exemplar parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Exemplar) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Exemplar parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Exemplar) PARSER.parseFrom(byteString);
        }

        public static Exemplar parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Exemplar) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Exemplar parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Exemplar) PARSER.parseFrom(bArr);
        }

        public static Exemplar parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Exemplar) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Exemplar parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Exemplar parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Exemplar parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Exemplar parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Exemplar parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Exemplar parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m1083toBuilder();
        }

        public static Builder newBuilder(Exemplar exemplar) {
            return DEFAULT_INSTANCE.m1083toBuilder().mergeFrom(exemplar);
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public List<Any> getAttachmentsList() {
            return this.attachments_;
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public List<? extends AnyOrBuilder> getAttachmentsOrBuilderList() {
            return this.attachments_;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Exemplar m1078getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Exemplar> getParserForType() {
            return PARSER;
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public double getValue() {
            return this.value_;
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public boolean hasTimestamp() {
            return this.timestamp_ != null;
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
            return DistributionProto.internal_static_google_api_Distribution_Exemplar_fieldAccessorTable.ensureFieldAccessorsInitialized(Exemplar.class, Builder.class);
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public Timestamp getTimestamp() {
            Timestamp timestamp = this.timestamp_;
            return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public TimestampOrBuilder getTimestampOrBuilder() {
            return getTimestamp();
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public int getAttachmentsCount() {
            return this.attachments_.size();
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public Any getAttachments(int i) {
            return this.attachments_.get(i);
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public AnyOrBuilder getAttachmentsOrBuilder(int i) {
            return this.attachments_.get(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            double d = this.value_;
            if (d != 0.0d) {
                codedOutputStream.writeDouble(1, d);
            }
            if (this.timestamp_ != null) {
                codedOutputStream.writeMessage(2, getTimestamp());
            }
            for (int i = 0; i < this.attachments_.size(); i++) {
                codedOutputStream.writeMessage(3, this.attachments_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            double d = this.value_;
            int iComputeDoubleSize = d != 0.0d ? CodedOutputStream.computeDoubleSize(1, d) : 0;
            if (this.timestamp_ != null) {
                iComputeDoubleSize += CodedOutputStream.computeMessageSize(2, getTimestamp());
            }
            for (int i2 = 0; i2 < this.attachments_.size(); i2++) {
                iComputeDoubleSize += CodedOutputStream.computeMessageSize(3, this.attachments_.get(i2));
            }
            int serializedSize = iComputeDoubleSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Exemplar)) {
                return super.equals(obj);
            }
            Exemplar exemplar = (Exemplar) obj;
            if (Double.doubleToLongBits(getValue()) == Double.doubleToLongBits(exemplar.getValue()) && hasTimestamp() == exemplar.hasTimestamp()) {
                return (!hasTimestamp() || getTimestamp().equals(exemplar.getTimestamp())) && getAttachmentsList().equals(exemplar.getAttachmentsList()) && this.unknownFields.equals(exemplar.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getValue()));
            if (hasTimestamp()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getTimestamp().hashCode();
            }
            if (getAttachmentsCount() > 0) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getAttachmentsList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1081newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1083toBuilder() {
            if (this == DEFAULT_INSTANCE) {
                return new Builder();
            }
            return new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
        public Builder m1080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExemplarOrBuilder {
            private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> attachmentsBuilder_;
            private List<Any> attachments_;
            private int bitField0_;
            private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> timestampBuilder_;
            private Timestamp timestamp_;
            private double value_;

            private Builder() {
                this.attachments_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.attachments_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_Exemplar_descriptor;
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public double getValue() {
                return this.value_;
            }

            public Builder setValue(double d) {
                this.value_ = d;
                onChanged();
                return this;
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public boolean hasTimestamp() {
                return (this.timestampBuilder_ == null && this.timestamp_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.internal_static_google_api_Distribution_Exemplar_fieldAccessorTable.ensureFieldAccessorsInitialized(Exemplar.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (Exemplar.alwaysUseFieldBuilders) {
                    getAttachmentsFieldBuilder();
                }
            }

            /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1094clear() {
                super.clear();
                this.value_ = 0.0d;
                if (this.timestampBuilder_ == null) {
                    this.timestamp_ = null;
                } else {
                    this.timestamp_ = null;
                    this.timestampBuilder_ = null;
                }
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.attachments_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return DistributionProto.internal_static_google_api_Distribution_Exemplar_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Exemplar m1107getDefaultInstanceForType() {
                return Exemplar.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Exemplar m1088build() throws UninitializedMessageException {
                Exemplar exemplarM1090buildPartial = m1090buildPartial();
                if (exemplarM1090buildPartial.isInitialized()) {
                    return exemplarM1090buildPartial;
                }
                throw newUninitializedMessageException(exemplarM1090buildPartial);
            }

            /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Exemplar m1090buildPartial() {
                Exemplar exemplar = new Exemplar(this);
                exemplar.value_ = this.value_;
                SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
                if (singleFieldBuilderV3 == null) {
                    exemplar.timestamp_ = this.timestamp_;
                } else {
                    exemplar.timestamp_ = singleFieldBuilderV3.build();
                }
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 4) != 0) {
                        this.attachments_ = Collections.unmodifiableList(this.attachments_);
                        this.bitField0_ &= -5;
                    }
                    exemplar.attachments_ = this.attachments_;
                } else {
                    exemplar.attachments_ = repeatedFieldBuilderV3.build();
                }
                exemplar.bitField0_ = 0;
                onBuilt();
                return exemplar;
            }

            /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1105clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1118setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1096clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1099clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1120setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1086addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1112mergeFrom(Message message) {
                if (message instanceof Exemplar) {
                    return mergeFrom((Exemplar) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Exemplar exemplar) {
                if (exemplar == Exemplar.getDefaultInstance()) {
                    return this;
                }
                if (exemplar.getValue() != 0.0d) {
                    setValue(exemplar.getValue());
                }
                if (exemplar.hasTimestamp()) {
                    mergeTimestamp(exemplar.getTimestamp());
                }
                if (this.attachmentsBuilder_ == null) {
                    if (!exemplar.attachments_.isEmpty()) {
                        if (this.attachments_.isEmpty()) {
                            this.attachments_ = exemplar.attachments_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureAttachmentsIsMutable();
                            this.attachments_.addAll(exemplar.attachments_);
                        }
                        onChanged();
                    }
                } else if (!exemplar.attachments_.isEmpty()) {
                    if (!this.attachmentsBuilder_.isEmpty()) {
                        this.attachmentsBuilder_.addAllMessages(exemplar.attachments_);
                    } else {
                        this.attachmentsBuilder_.dispose();
                        this.attachmentsBuilder_ = null;
                        this.attachments_ = exemplar.attachments_;
                        this.bitField0_ &= -5;
                        this.attachmentsBuilder_ = Exemplar.alwaysUseFieldBuilders ? getAttachmentsFieldBuilder() : null;
                    }
                }
                m1116mergeUnknownFields(exemplar.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.google.api.Distribution.Exemplar.Builder m1113mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.api.Distribution.Exemplar.access$6000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.google.api.Distribution$Exemplar r3 = (com.google.api.Distribution.Exemplar) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.google.api.Distribution$Exemplar r4 = (com.google.api.Distribution.Exemplar) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.Exemplar.Builder.m1113mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Distribution$Exemplar$Builder");
            }

            public Builder clearValue() {
                this.value_ = 0.0d;
                onChanged();
                return this;
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public Timestamp getTimestamp() {
                SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Timestamp timestamp = this.timestamp_;
                return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
            }

            public Builder setTimestamp(Timestamp timestamp) {
                SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
                if (singleFieldBuilderV3 == null) {
                    timestamp.getClass();
                    this.timestamp_ = timestamp;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(timestamp);
                }
                return this;
            }

            public Builder setTimestamp(Timestamp.Builder builder) {
                SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.timestamp_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeTimestamp(Timestamp timestamp) {
                SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Timestamp timestamp2 = this.timestamp_;
                    if (timestamp2 != null) {
                        this.timestamp_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
                    } else {
                        this.timestamp_ = timestamp;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(timestamp);
                }
                return this;
            }

            public Builder clearTimestamp() {
                if (this.timestampBuilder_ == null) {
                    this.timestamp_ = null;
                    onChanged();
                } else {
                    this.timestamp_ = null;
                    this.timestampBuilder_ = null;
                }
                return this;
            }

            public Timestamp.Builder getTimestampBuilder() {
                onChanged();
                return getTimestampFieldBuilder().getBuilder();
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public TimestampOrBuilder getTimestampOrBuilder() {
                SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Timestamp timestamp = this.timestamp_;
                return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
            }

            private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> getTimestampFieldBuilder() {
                if (this.timestampBuilder_ == null) {
                    this.timestampBuilder_ = new SingleFieldBuilderV3<>(getTimestamp(), getParentForChildren(), isClean());
                    this.timestamp_ = null;
                }
                return this.timestampBuilder_;
            }

            private void ensureAttachmentsIsMutable() {
                if ((this.bitField0_ & 4) == 0) {
                    this.attachments_ = new ArrayList(this.attachments_);
                    this.bitField0_ |= 4;
                }
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public List<Any> getAttachmentsList() {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.attachments_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public int getAttachmentsCount() {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.attachments_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public Any getAttachments(int i) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.attachments_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setAttachments(int i, Any any) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    any.getClass();
                    ensureAttachmentsIsMutable();
                    this.attachments_.set(i, any);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, any);
                }
                return this;
            }

            public Builder setAttachments(int i, Any.Builder builder) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAttachmentsIsMutable();
                    this.attachments_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAttachments(Any any) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    any.getClass();
                    ensureAttachmentsIsMutable();
                    this.attachments_.add(any);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(any);
                }
                return this;
            }

            public Builder addAttachments(int i, Any any) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    any.getClass();
                    ensureAttachmentsIsMutable();
                    this.attachments_.add(i, any);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, any);
                }
                return this;
            }

            public Builder addAttachments(Any.Builder builder) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAttachmentsIsMutable();
                    this.attachments_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addAttachments(int i, Any.Builder builder) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAttachmentsIsMutable();
                    this.attachments_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllAttachments(Iterable<? extends Any> iterable) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAttachmentsIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.attachments_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearAttachments() {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.attachments_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeAttachments(int i) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAttachmentsIsMutable();
                    this.attachments_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Any.Builder getAttachmentsBuilder(int i) {
                return getAttachmentsFieldBuilder().getBuilder(i);
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public AnyOrBuilder getAttachmentsOrBuilder(int i) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.attachments_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public List<? extends AnyOrBuilder> getAttachmentsOrBuilderList() {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.attachments_);
            }

            public Any.Builder addAttachmentsBuilder() {
                return getAttachmentsFieldBuilder().addBuilder(Any.getDefaultInstance());
            }

            public Any.Builder addAttachmentsBuilder(int i) {
                return getAttachmentsFieldBuilder().addBuilder(i, Any.getDefaultInstance());
            }

            public List<Any.Builder> getAttachmentsBuilderList() {
                return getAttachmentsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getAttachmentsFieldBuilder() {
                if (this.attachmentsBuilder_ == null) {
                    this.attachmentsBuilder_ = new RepeatedFieldBuilderV3<>(this.attachments_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                    this.attachments_ = null;
                }
                return this.attachmentsBuilder_;
            }

            /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m1122setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m1116mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DistributionOrBuilder {
        private int bitField0_;
        private Internal.LongList bucketCounts_;
        private SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> bucketOptionsBuilder_;
        private BucketOptions bucketOptions_;
        private long count_;
        private RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> exemplarsBuilder_;
        private List<Exemplar> exemplars_;
        private double mean_;
        private SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> rangeBuilder_;
        private Range range_;
        private double sumOfSquaredDeviation_;

        private Builder() {
            this.bucketCounts_ = Distribution.emptyLongList();
            this.exemplars_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.bucketCounts_ = Distribution.emptyLongList();
            this.exemplars_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DistributionProto.internal_static_google_api_Distribution_descriptor;
        }

        @Override // com.google.api.DistributionOrBuilder
        public long getCount() {
            return this.count_;
        }

        public Builder setCount(long j) {
            this.count_ = j;
            onChanged();
            return this;
        }

        @Override // com.google.api.DistributionOrBuilder
        public double getMean() {
            return this.mean_;
        }

        public Builder setMean(double d) {
            this.mean_ = d;
            onChanged();
            return this;
        }

        @Override // com.google.api.DistributionOrBuilder
        public double getSumOfSquaredDeviation() {
            return this.sumOfSquaredDeviation_;
        }

        public Builder setSumOfSquaredDeviation(double d) {
            this.sumOfSquaredDeviation_ = d;
            onChanged();
            return this;
        }

        @Override // com.google.api.DistributionOrBuilder
        public boolean hasBucketOptions() {
            return (this.bucketOptionsBuilder_ == null && this.bucketOptions_ == null) ? false : true;
        }

        @Override // com.google.api.DistributionOrBuilder
        public boolean hasRange() {
            return (this.rangeBuilder_ == null && this.range_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DistributionProto.internal_static_google_api_Distribution_fieldAccessorTable.ensureFieldAccessorsInitialized(Distribution.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (Distribution.alwaysUseFieldBuilders) {
                getExemplarsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1048clear() {
            super.clear();
            this.count_ = 0L;
            this.mean_ = 0.0d;
            this.sumOfSquaredDeviation_ = 0.0d;
            if (this.rangeBuilder_ == null) {
                this.range_ = null;
            } else {
                this.range_ = null;
                this.rangeBuilder_ = null;
            }
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptions_ = null;
            } else {
                this.bucketOptions_ = null;
                this.bucketOptionsBuilder_ = null;
            }
            this.bucketCounts_ = Distribution.emptyLongList();
            this.bitField0_ &= -33;
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.exemplars_ = Collections.emptyList();
                this.bitField0_ &= -65;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return DistributionProto.internal_static_google_api_Distribution_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Distribution m1061getDefaultInstanceForType() {
            return Distribution.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Distribution m1042build() throws UninitializedMessageException {
            Distribution distributionM1044buildPartial = m1044buildPartial();
            if (distributionM1044buildPartial.isInitialized()) {
                return distributionM1044buildPartial;
            }
            throw newUninitializedMessageException(distributionM1044buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Distribution m1044buildPartial() {
            Distribution distribution = new Distribution(this);
            distribution.count_ = this.count_;
            distribution.mean_ = this.mean_;
            distribution.sumOfSquaredDeviation_ = this.sumOfSquaredDeviation_;
            SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                distribution.range_ = this.range_;
            } else {
                distribution.range_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> singleFieldBuilderV32 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV32 == null) {
                distribution.bucketOptions_ = this.bucketOptions_;
            } else {
                distribution.bucketOptions_ = singleFieldBuilderV32.build();
            }
            if ((this.bitField0_ & 32) != 0) {
                this.bucketCounts_.makeImmutable();
                this.bitField0_ &= -33;
            }
            distribution.bucketCounts_ = this.bucketCounts_;
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 64) != 0) {
                    this.exemplars_ = Collections.unmodifiableList(this.exemplars_);
                    this.bitField0_ &= -65;
                }
                distribution.exemplars_ = this.exemplars_;
            } else {
                distribution.exemplars_ = repeatedFieldBuilderV3.build();
            }
            distribution.bitField0_ = 0;
            onBuilt();
            return distribution;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1059clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1072setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1050clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1053clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1074setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1040addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1066mergeFrom(Message message) {
            if (message instanceof Distribution) {
                return mergeFrom((Distribution) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Distribution distribution) {
            if (distribution == Distribution.getDefaultInstance()) {
                return this;
            }
            if (distribution.getCount() != 0) {
                setCount(distribution.getCount());
            }
            if (distribution.getMean() != 0.0d) {
                setMean(distribution.getMean());
            }
            if (distribution.getSumOfSquaredDeviation() != 0.0d) {
                setSumOfSquaredDeviation(distribution.getSumOfSquaredDeviation());
            }
            if (distribution.hasRange()) {
                mergeRange(distribution.getRange());
            }
            if (distribution.hasBucketOptions()) {
                mergeBucketOptions(distribution.getBucketOptions());
            }
            if (!distribution.bucketCounts_.isEmpty()) {
                if (this.bucketCounts_.isEmpty()) {
                    this.bucketCounts_ = distribution.bucketCounts_;
                    this.bitField0_ &= -33;
                } else {
                    ensureBucketCountsIsMutable();
                    this.bucketCounts_.addAll(distribution.bucketCounts_);
                }
                onChanged();
            }
            if (this.exemplarsBuilder_ == null) {
                if (!distribution.exemplars_.isEmpty()) {
                    if (this.exemplars_.isEmpty()) {
                        this.exemplars_ = distribution.exemplars_;
                        this.bitField0_ &= -65;
                    } else {
                        ensureExemplarsIsMutable();
                        this.exemplars_.addAll(distribution.exemplars_);
                    }
                    onChanged();
                }
            } else if (!distribution.exemplars_.isEmpty()) {
                if (!this.exemplarsBuilder_.isEmpty()) {
                    this.exemplarsBuilder_.addAllMessages(distribution.exemplars_);
                } else {
                    this.exemplarsBuilder_.dispose();
                    this.exemplarsBuilder_ = null;
                    this.exemplars_ = distribution.exemplars_;
                    this.bitField0_ &= -65;
                    this.exemplarsBuilder_ = Distribution.alwaysUseFieldBuilders ? getExemplarsFieldBuilder() : null;
                }
            }
            m1070mergeUnknownFields(distribution.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.api.Distribution.Builder m1067mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Distribution.access$7700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.Distribution r3 = (com.google.api.Distribution) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.Distribution r4 = (com.google.api.Distribution) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.Builder.m1067mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Distribution$Builder");
        }

        public Builder clearCount() {
            this.count_ = 0L;
            onChanged();
            return this;
        }

        public Builder clearMean() {
            this.mean_ = 0.0d;
            onChanged();
            return this;
        }

        public Builder clearSumOfSquaredDeviation() {
            this.sumOfSquaredDeviation_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.api.DistributionOrBuilder
        public Range getRange() {
            SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Range range = this.range_;
            return range == null ? Range.getDefaultInstance() : range;
        }

        public Builder setRange(Range range) {
            SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                range.getClass();
                this.range_ = range;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(range);
            }
            return this;
        }

        public Builder setRange(Range.Builder builder) {
            SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.range_ = builder.m1134build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m1134build());
            }
            return this;
        }

        public Builder mergeRange(Range range) {
            SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                Range range2 = this.range_;
                if (range2 != null) {
                    this.range_ = Range.newBuilder(range2).mergeFrom(range).m1136buildPartial();
                } else {
                    this.range_ = range;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(range);
            }
            return this;
        }

        public Builder clearRange() {
            if (this.rangeBuilder_ == null) {
                this.range_ = null;
                onChanged();
            } else {
                this.range_ = null;
                this.rangeBuilder_ = null;
            }
            return this;
        }

        public Range.Builder getRangeBuilder() {
            onChanged();
            return getRangeFieldBuilder().getBuilder();
        }

        @Override // com.google.api.DistributionOrBuilder
        public RangeOrBuilder getRangeOrBuilder() {
            SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (RangeOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Range range = this.range_;
            return range == null ? Range.getDefaultInstance() : range;
        }

        private SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> getRangeFieldBuilder() {
            if (this.rangeBuilder_ == null) {
                this.rangeBuilder_ = new SingleFieldBuilderV3<>(getRange(), getParentForChildren(), isClean());
                this.range_ = null;
            }
            return this.rangeBuilder_;
        }

        @Override // com.google.api.DistributionOrBuilder
        public BucketOptions getBucketOptions() {
            SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            BucketOptions bucketOptions = this.bucketOptions_;
            return bucketOptions == null ? BucketOptions.getDefaultInstance() : bucketOptions;
        }

        public Builder setBucketOptions(BucketOptions bucketOptions) {
            SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                bucketOptions.getClass();
                this.bucketOptions_ = bucketOptions;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(bucketOptions);
            }
            return this;
        }

        public Builder setBucketOptions(BucketOptions.Builder builder) {
            SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.bucketOptions_ = builder.m866build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m866build());
            }
            return this;
        }

        public Builder mergeBucketOptions(BucketOptions bucketOptions) {
            SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                BucketOptions bucketOptions2 = this.bucketOptions_;
                if (bucketOptions2 != null) {
                    this.bucketOptions_ = BucketOptions.newBuilder(bucketOptions2).mergeFrom(bucketOptions).m868buildPartial();
                } else {
                    this.bucketOptions_ = bucketOptions;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(bucketOptions);
            }
            return this;
        }

        public Builder clearBucketOptions() {
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptions_ = null;
                onChanged();
            } else {
                this.bucketOptions_ = null;
                this.bucketOptionsBuilder_ = null;
            }
            return this;
        }

        public BucketOptions.Builder getBucketOptionsBuilder() {
            onChanged();
            return getBucketOptionsFieldBuilder().getBuilder();
        }

        @Override // com.google.api.DistributionOrBuilder
        public BucketOptionsOrBuilder getBucketOptionsOrBuilder() {
            SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (BucketOptionsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            BucketOptions bucketOptions = this.bucketOptions_;
            return bucketOptions == null ? BucketOptions.getDefaultInstance() : bucketOptions;
        }

        private SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> getBucketOptionsFieldBuilder() {
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptionsBuilder_ = new SingleFieldBuilderV3<>(getBucketOptions(), getParentForChildren(), isClean());
                this.bucketOptions_ = null;
            }
            return this.bucketOptionsBuilder_;
        }

        private void ensureBucketCountsIsMutable() {
            if ((this.bitField0_ & 32) == 0) {
                this.bucketCounts_ = Distribution.mutableCopy(this.bucketCounts_);
                this.bitField0_ |= 32;
            }
        }

        @Override // com.google.api.DistributionOrBuilder
        public List<Long> getBucketCountsList() {
            return (this.bitField0_ & 32) != 0 ? Collections.unmodifiableList(this.bucketCounts_) : this.bucketCounts_;
        }

        @Override // com.google.api.DistributionOrBuilder
        public int getBucketCountsCount() {
            return this.bucketCounts_.size();
        }

        @Override // com.google.api.DistributionOrBuilder
        public long getBucketCounts(int i) {
            return this.bucketCounts_.getLong(i);
        }

        public Builder setBucketCounts(int i, long j) {
            ensureBucketCountsIsMutable();
            this.bucketCounts_.setLong(i, j);
            onChanged();
            return this;
        }

        public Builder addBucketCounts(long j) {
            ensureBucketCountsIsMutable();
            this.bucketCounts_.addLong(j);
            onChanged();
            return this;
        }

        public Builder addAllBucketCounts(Iterable<? extends Long> iterable) {
            ensureBucketCountsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.bucketCounts_);
            onChanged();
            return this;
        }

        public Builder clearBucketCounts() {
            this.bucketCounts_ = Distribution.emptyLongList();
            this.bitField0_ &= -33;
            onChanged();
            return this;
        }

        private void ensureExemplarsIsMutable() {
            if ((this.bitField0_ & 64) == 0) {
                this.exemplars_ = new ArrayList(this.exemplars_);
                this.bitField0_ |= 64;
            }
        }

        @Override // com.google.api.DistributionOrBuilder
        public List<Exemplar> getExemplarsList() {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.exemplars_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.DistributionOrBuilder
        public int getExemplarsCount() {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.exemplars_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.DistributionOrBuilder
        public Exemplar getExemplars(int i) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.exemplars_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setExemplars(int i, Exemplar exemplar) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                exemplar.getClass();
                ensureExemplarsIsMutable();
                this.exemplars_.set(i, exemplar);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, exemplar);
            }
            return this;
        }

        public Builder setExemplars(int i, Exemplar.Builder builder) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExemplarsIsMutable();
                this.exemplars_.set(i, builder.m1088build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m1088build());
            }
            return this;
        }

        public Builder addExemplars(Exemplar exemplar) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                exemplar.getClass();
                ensureExemplarsIsMutable();
                this.exemplars_.add(exemplar);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(exemplar);
            }
            return this;
        }

        public Builder addExemplars(int i, Exemplar exemplar) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                exemplar.getClass();
                ensureExemplarsIsMutable();
                this.exemplars_.add(i, exemplar);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, exemplar);
            }
            return this;
        }

        public Builder addExemplars(Exemplar.Builder builder) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExemplarsIsMutable();
                this.exemplars_.add(builder.m1088build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m1088build());
            }
            return this;
        }

        public Builder addExemplars(int i, Exemplar.Builder builder) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExemplarsIsMutable();
                this.exemplars_.add(i, builder.m1088build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m1088build());
            }
            return this;
        }

        public Builder addAllExemplars(Iterable<? extends Exemplar> iterable) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExemplarsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.exemplars_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearExemplars() {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.exemplars_ = Collections.emptyList();
                this.bitField0_ &= -65;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeExemplars(int i) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExemplarsIsMutable();
                this.exemplars_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Exemplar.Builder getExemplarsBuilder(int i) {
            return getExemplarsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.DistributionOrBuilder
        public ExemplarOrBuilder getExemplarsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.exemplars_.get(i);
            }
            return (ExemplarOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.DistributionOrBuilder
        public List<? extends ExemplarOrBuilder> getExemplarsOrBuilderList() {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.exemplars_);
        }

        public Exemplar.Builder addExemplarsBuilder() {
            return getExemplarsFieldBuilder().addBuilder(Exemplar.getDefaultInstance());
        }

        public Exemplar.Builder addExemplarsBuilder(int i) {
            return getExemplarsFieldBuilder().addBuilder(i, Exemplar.getDefaultInstance());
        }

        public List<Exemplar.Builder> getExemplarsBuilderList() {
            return getExemplarsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> getExemplarsFieldBuilder() {
            if (this.exemplarsBuilder_ == null) {
                this.exemplarsBuilder_ = new RepeatedFieldBuilderV3<>(this.exemplars_, (this.bitField0_ & 64) != 0, getParentForChildren(), isClean());
                this.exemplars_ = null;
            }
            return this.exemplarsBuilder_;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1076setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1070mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
