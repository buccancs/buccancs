package io.opencensus.proto.metrics.v1;

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
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.opencensus.proto.metrics.v1.DistributionValue;
import io.opencensus.proto.metrics.v1.SummaryValue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class Point extends GeneratedMessageV3 implements PointOrBuilder {
    public static final int DISTRIBUTION_VALUE_FIELD_NUMBER = 4;
    public static final int DOUBLE_VALUE_FIELD_NUMBER = 3;
    public static final int INT64_VALUE_FIELD_NUMBER = 2;
    public static final int SUMMARY_VALUE_FIELD_NUMBER = 5;
    public static final int TIMESTAMP_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final Point DEFAULT_INSTANCE = new Point();
    private static final Parser<Point> PARSER = new AbstractParser<Point>() { // from class: io.opencensus.proto.metrics.v1.Point.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Point m37195parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Point(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private Timestamp timestamp_;
    private int valueCase_;
    private Object value_;

    private Point(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.valueCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Point() {
        this.valueCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Point(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Timestamp.Builder builderM37239toBuilder;
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
                                Timestamp timestamp = this.timestamp_;
                                builderM37239toBuilder = timestamp != null ? timestamp.toBuilder() : null;
                                Timestamp message = codedInputStream.readMessage(Timestamp.parser(), extensionRegistryLite);
                                this.timestamp_ = message;
                                if (builderM37239toBuilder != null) {
                                    builderM37239toBuilder.mergeFrom(message);
                                    this.timestamp_ = builderM37239toBuilder.buildPartial();
                                }
                            } else if (tag == 16) {
                                this.valueCase_ = 2;
                                this.value_ = Long.valueOf(codedInputStream.readInt64());
                            } else if (tag == 25) {
                                this.valueCase_ = 3;
                                this.value_ = Double.valueOf(codedInputStream.readDouble());
                            } else if (tag == 34) {
                                builderM37239toBuilder = this.valueCase_ == 4 ? ((DistributionValue) this.value_).m36778toBuilder() : null;
                                DistributionValue message2 = codedInputStream.readMessage(DistributionValue.parser(), extensionRegistryLite);
                                this.value_ = message2;
                                if (builderM37239toBuilder != null) {
                                    builderM37239toBuilder.mergeFrom(message2);
                                    this.value_ = builderM37239toBuilder.m36923buildPartial();
                                }
                                this.valueCase_ = 4;
                            } else if (tag != 42) {
                                if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                builderM37239toBuilder = this.valueCase_ == 5 ? ((SummaryValue) this.value_).m37239toBuilder() : null;
                                SummaryValue message3 = codedInputStream.readMessage(SummaryValue.parser(), extensionRegistryLite);
                                this.value_ = message3;
                                if (builderM37239toBuilder != null) {
                                    builderM37239toBuilder.mergeFrom(message3);
                                    this.value_ = builderM37239toBuilder.m37246buildPartial();
                                }
                                this.valueCase_ = 5;
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

    public static Point getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Point> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MetricsProto.internal_static_opencensus_proto_metrics_v1_Point_descriptor;
    }

    public static Point parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Point) PARSER.parseFrom(byteBuffer);
    }

    public static Point parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Point) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Point parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Point) PARSER.parseFrom(byteString);
    }

    public static Point parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Point) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Point parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Point) PARSER.parseFrom(bArr);
    }

    public static Point parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Point) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Point parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Point parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Point parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Point parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Point parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Point parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m37193toBuilder();
    }

    public static Builder newBuilder(Point point) {
        return DEFAULT_INSTANCE.m37193toBuilder().mergeFrom(point);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Point m37188getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Point> getParserForType() {
        return PARSER;
    }

    @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
    public boolean hasDistributionValue() {
        return this.valueCase_ == 4;
    }

    @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
    public boolean hasSummaryValue() {
        return this.valueCase_ == 5;
    }

    @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
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
        return MetricsProto.internal_static_opencensus_proto_metrics_v1_Point_fieldAccessorTable.ensureFieldAccessorsInitialized(Point.class, Builder.class);
    }

    @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
    public ValueCase getValueCase() {
        return ValueCase.forNumber(this.valueCase_);
    }

    @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
    public Timestamp getTimestamp() {
        Timestamp timestamp = this.timestamp_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
    public TimestampOrBuilder getTimestampOrBuilder() {
        return getTimestamp();
    }

    @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
    public long getInt64Value() {
        if (this.valueCase_ == 2) {
            return ((Long) this.value_).longValue();
        }
        return 0L;
    }

    @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
    public double getDoubleValue() {
        if (this.valueCase_ == 3) {
            return ((Double) this.value_).doubleValue();
        }
        return 0.0d;
    }

    @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
    public DistributionValue getDistributionValue() {
        if (this.valueCase_ == 4) {
            return (DistributionValue) this.value_;
        }
        return DistributionValue.getDefaultInstance();
    }

    @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
    public DistributionValueOrBuilder getDistributionValueOrBuilder() {
        if (this.valueCase_ == 4) {
            return (DistributionValue) this.value_;
        }
        return DistributionValue.getDefaultInstance();
    }

    @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
    public SummaryValue getSummaryValue() {
        if (this.valueCase_ == 5) {
            return (SummaryValue) this.value_;
        }
        return SummaryValue.getDefaultInstance();
    }

    @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
    public SummaryValueOrBuilder getSummaryValueOrBuilder() {
        if (this.valueCase_ == 5) {
            return (SummaryValue) this.value_;
        }
        return SummaryValue.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.timestamp_ != null) {
            codedOutputStream.writeMessage(1, getTimestamp());
        }
        if (this.valueCase_ == 2) {
            codedOutputStream.writeInt64(2, ((Long) this.value_).longValue());
        }
        if (this.valueCase_ == 3) {
            codedOutputStream.writeDouble(3, ((Double) this.value_).doubleValue());
        }
        if (this.valueCase_ == 4) {
            codedOutputStream.writeMessage(4, (DistributionValue) this.value_);
        }
        if (this.valueCase_ == 5) {
            codedOutputStream.writeMessage(5, (SummaryValue) this.value_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.timestamp_ != null ? CodedOutputStream.computeMessageSize(1, getTimestamp()) : 0;
        if (this.valueCase_ == 2) {
            iComputeMessageSize += CodedOutputStream.computeInt64Size(2, ((Long) this.value_).longValue());
        }
        if (this.valueCase_ == 3) {
            iComputeMessageSize += CodedOutputStream.computeDoubleSize(3, ((Double) this.value_).doubleValue());
        }
        if (this.valueCase_ == 4) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(4, (DistributionValue) this.value_);
        }
        if (this.valueCase_ == 5) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(5, (SummaryValue) this.value_);
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 1
            if (r8 != r7) goto L4
            return r0
        L4:
            boolean r1 = r8 instanceof io.opencensus.proto.metrics.v1.Point
            if (r1 != 0) goto Ld
            boolean r8 = super.equals(r8)
            return r8
        Ld:
            io.opencensus.proto.metrics.v1.Point r8 = (io.opencensus.proto.metrics.v1.Point) r8
            boolean r1 = r7.hasTimestamp()
            boolean r2 = r8.hasTimestamp()
            r3 = 0
            if (r1 != r2) goto L1c
            r1 = 1
            goto L1d
        L1c:
            r1 = 0
        L1d:
            boolean r2 = r7.hasTimestamp()
            if (r2 == 0) goto L34
            if (r1 == 0) goto L46
            com.google.protobuf.Timestamp r1 = r7.getTimestamp()
            com.google.protobuf.Timestamp r2 = r8.getTimestamp()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L46
            goto L36
        L34:
            if (r1 == 0) goto L46
        L36:
            io.opencensus.proto.metrics.v1.Point$ValueCase r1 = r7.getValueCase()
            io.opencensus.proto.metrics.v1.Point$ValueCase r2 = r8.getValueCase()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L46
            r1 = 1
            goto L47
        L46:
            r1 = 0
        L47:
            if (r1 != 0) goto L4a
            return r3
        L4a:
            int r2 = r7.valueCase_
            r4 = 2
            if (r2 == r4) goto L94
            r4 = 3
            if (r2 == r4) goto L7d
            r4 = 4
            if (r2 == r4) goto L6c
            r4 = 5
            if (r2 == r4) goto L5b
            if (r1 == 0) goto Lad
            goto La2
        L5b:
            if (r1 == 0) goto Lad
            io.opencensus.proto.metrics.v1.SummaryValue r1 = r7.getSummaryValue()
            io.opencensus.proto.metrics.v1.SummaryValue r2 = r8.getSummaryValue()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto Lad
            goto La2
        L6c:
            if (r1 == 0) goto Lad
            io.opencensus.proto.metrics.v1.DistributionValue r1 = r7.getDistributionValue()
            io.opencensus.proto.metrics.v1.DistributionValue r2 = r8.getDistributionValue()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto Lad
            goto La2
        L7d:
            if (r1 == 0) goto Lad
            double r1 = r7.getDoubleValue()
            long r1 = java.lang.Double.doubleToLongBits(r1)
            double r4 = r8.getDoubleValue()
            long r4 = java.lang.Double.doubleToLongBits(r4)
            int r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r6 != 0) goto Lad
            goto La2
        L94:
            if (r1 == 0) goto Lad
            long r1 = r7.getInt64Value()
            long r4 = r8.getInt64Value()
            int r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r6 != 0) goto Lad
        La2:
            com.google.protobuf.UnknownFieldSet r1 = r7.unknownFields
            com.google.protobuf.UnknownFieldSet r8 = r8.unknownFields
            boolean r8 = r1.equals(r8)
            if (r8 == 0) goto Lad
            goto Lae
        Lad:
            r0 = 0
        Lae:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.metrics.v1.Point.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i;
        int iHashLong;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasTimestamp()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getTimestamp().hashCode();
        }
        int i2 = this.valueCase_;
        if (i2 == 2) {
            i = ((iHashCode * 37) + 2) * 53;
            iHashLong = Internal.hashLong(getInt64Value());
        } else if (i2 == 3) {
            i = ((iHashCode * 37) + 3) * 53;
            iHashLong = Internal.hashLong(Double.doubleToLongBits(getDoubleValue()));
        } else if (i2 == 4) {
            i = ((iHashCode * 37) + 4) * 53;
            iHashLong = getDistributionValue().hashCode();
        } else {
            if (i2 == 5) {
                i = ((iHashCode * 37) + 5) * 53;
                iHashLong = getSummaryValue().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }
        iHashCode = i + iHashLong;
        int iHashCode22 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode22;
        return iHashCode22;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37190newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37193toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ValueCase implements Internal.EnumLite {
        INT64_VALUE(2),
        DOUBLE_VALUE(3),
        DISTRIBUTION_VALUE(4),
        SUMMARY_VALUE(5),
        VALUE_NOT_SET(0);

        private final int value;

        ValueCase(int i) {
            this.value = i;
        }

        public static ValueCase forNumber(int i) {
            if (i == 0) {
                return VALUE_NOT_SET;
            }
            if (i == 2) {
                return INT64_VALUE;
            }
            if (i == 3) {
                return DOUBLE_VALUE;
            }
            if (i == 4) {
                return DISTRIBUTION_VALUE;
            }
            if (i != 5) {
                return null;
            }
            return SUMMARY_VALUE;
        }

        @Deprecated
        public static ValueCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PointOrBuilder {
        private SingleFieldBuilderV3<DistributionValue, DistributionValue.Builder, DistributionValueOrBuilder> distributionValueBuilder_;
        private SingleFieldBuilderV3<SummaryValue, SummaryValue.Builder, SummaryValueOrBuilder> summaryValueBuilder_;
        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> timestampBuilder_;
        private Timestamp timestamp_;
        private int valueCase_;
        private Object value_;

        private Builder() {
            this.valueCase_ = 0;
            this.timestamp_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.valueCase_ = 0;
            this.timestamp_ = null;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MetricsProto.internal_static_opencensus_proto_metrics_v1_Point_descriptor;
        }

        @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
        public boolean hasDistributionValue() {
            return this.valueCase_ == 4;
        }

        @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
        public boolean hasSummaryValue() {
            return this.valueCase_ == 5;
        }

        @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
        public boolean hasTimestamp() {
            return (this.timestampBuilder_ == null && this.timestamp_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MetricsProto.internal_static_opencensus_proto_metrics_v1_Point_fieldAccessorTable.ensureFieldAccessorsInitialized(Point.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Point.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37204clear() {
            super.clear();
            if (this.timestampBuilder_ == null) {
                this.timestamp_ = null;
            } else {
                this.timestamp_ = null;
                this.timestampBuilder_ = null;
            }
            this.valueCase_ = 0;
            this.value_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return MetricsProto.internal_static_opencensus_proto_metrics_v1_Point_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Point m37217getDefaultInstanceForType() {
            return Point.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Point m37198build() throws UninitializedMessageException {
            Point pointM37200buildPartial = m37200buildPartial();
            if (pointM37200buildPartial.isInitialized()) {
                return pointM37200buildPartial;
            }
            throw newUninitializedMessageException(pointM37200buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Point m37200buildPartial() {
            Point point = new Point(this);
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                point.timestamp_ = this.timestamp_;
            } else {
                point.timestamp_ = singleFieldBuilderV3.build();
            }
            if (this.valueCase_ == 2) {
                point.value_ = this.value_;
            }
            if (this.valueCase_ == 3) {
                point.value_ = this.value_;
            }
            if (this.valueCase_ == 4) {
                SingleFieldBuilderV3<DistributionValue, DistributionValue.Builder, DistributionValueOrBuilder> singleFieldBuilderV32 = this.distributionValueBuilder_;
                if (singleFieldBuilderV32 == null) {
                    point.value_ = this.value_;
                } else {
                    point.value_ = singleFieldBuilderV32.build();
                }
            }
            if (this.valueCase_ == 5) {
                SingleFieldBuilderV3<SummaryValue, SummaryValue.Builder, SummaryValueOrBuilder> singleFieldBuilderV33 = this.summaryValueBuilder_;
                if (singleFieldBuilderV33 == null) {
                    point.value_ = this.value_;
                } else {
                    point.value_ = singleFieldBuilderV33.build();
                }
            }
            point.valueCase_ = this.valueCase_;
            onBuilt();
            return point;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37216clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37228setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37206clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37209clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37230setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37196addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37221mergeFrom(Message message) {
            if (message instanceof Point) {
                return mergeFrom((Point) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Point point) {
            if (point == Point.getDefaultInstance()) {
                return this;
            }
            if (point.hasTimestamp()) {
                mergeTimestamp(point.getTimestamp());
            }
            int i = AnonymousClass2.$SwitchMap$io$opencensus$proto$metrics$v1$Point$ValueCase[point.getValueCase().ordinal()];
            if (i == 1) {
                setInt64Value(point.getInt64Value());
            } else if (i == 2) {
                setDoubleValue(point.getDoubleValue());
            } else if (i == 3) {
                mergeDistributionValue(point.getDistributionValue());
            } else if (i == 4) {
                mergeSummaryValue(point.getSummaryValue());
            }
            m37226mergeUnknownFields(point.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.metrics.v1.Point.Builder m37222mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.metrics.v1.Point.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.metrics.v1.Point r3 = (io.opencensus.proto.metrics.v1.Point) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.metrics.v1.Point r4 = (io.opencensus.proto.metrics.v1.Point) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.metrics.v1.Point.Builder.m37222mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.metrics.v1.Point$Builder");
        }

        @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
        public ValueCase getValueCase() {
            return ValueCase.forNumber(this.valueCase_);
        }

        public Builder clearValue() {
            this.valueCase_ = 0;
            this.value_ = null;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
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

        @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
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

        @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
        public long getInt64Value() {
            if (this.valueCase_ == 2) {
                return ((Long) this.value_).longValue();
            }
            return 0L;
        }

        public Builder setInt64Value(long j) {
            this.valueCase_ = 2;
            this.value_ = Long.valueOf(j);
            onChanged();
            return this;
        }

        public Builder clearInt64Value() {
            if (this.valueCase_ == 2) {
                this.valueCase_ = 0;
                this.value_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
        public double getDoubleValue() {
            if (this.valueCase_ == 3) {
                return ((Double) this.value_).doubleValue();
            }
            return 0.0d;
        }

        public Builder setDoubleValue(double d) {
            this.valueCase_ = 3;
            this.value_ = Double.valueOf(d);
            onChanged();
            return this;
        }

        public Builder clearDoubleValue() {
            if (this.valueCase_ == 3) {
                this.valueCase_ = 0;
                this.value_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
        public DistributionValue getDistributionValue() {
            SingleFieldBuilderV3<DistributionValue, DistributionValue.Builder, DistributionValueOrBuilder> singleFieldBuilderV3 = this.distributionValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 4) {
                    return (DistributionValue) this.value_;
                }
                return DistributionValue.getDefaultInstance();
            }
            if (this.valueCase_ == 4) {
                return singleFieldBuilderV3.getMessage();
            }
            return DistributionValue.getDefaultInstance();
        }

        public Builder setDistributionValue(DistributionValue distributionValue) {
            SingleFieldBuilderV3<DistributionValue, DistributionValue.Builder, DistributionValueOrBuilder> singleFieldBuilderV3 = this.distributionValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                distributionValue.getClass();
                this.value_ = distributionValue;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(distributionValue);
            }
            this.valueCase_ = 4;
            return this;
        }

        public Builder setDistributionValue(DistributionValue.Builder builder) {
            SingleFieldBuilderV3<DistributionValue, DistributionValue.Builder, DistributionValueOrBuilder> singleFieldBuilderV3 = this.distributionValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.value_ = builder.m36921build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m36921build());
            }
            this.valueCase_ = 4;
            return this;
        }

        public Builder mergeDistributionValue(DistributionValue distributionValue) {
            SingleFieldBuilderV3<DistributionValue, DistributionValue.Builder, DistributionValueOrBuilder> singleFieldBuilderV3 = this.distributionValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ != 4 || this.value_ == DistributionValue.getDefaultInstance()) {
                    this.value_ = distributionValue;
                } else {
                    this.value_ = DistributionValue.newBuilder((DistributionValue) this.value_).mergeFrom(distributionValue).m36923buildPartial();
                }
                onChanged();
            } else {
                if (this.valueCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(distributionValue);
                }
                this.distributionValueBuilder_.setMessage(distributionValue);
            }
            this.valueCase_ = 4;
            return this;
        }

        public Builder clearDistributionValue() {
            SingleFieldBuilderV3<DistributionValue, DistributionValue.Builder, DistributionValueOrBuilder> singleFieldBuilderV3 = this.distributionValueBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.valueCase_ == 4) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.valueCase_ == 4) {
                this.valueCase_ = 0;
                this.value_ = null;
                onChanged();
            }
            return this;
        }

        public DistributionValue.Builder getDistributionValueBuilder() {
            return getDistributionValueFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
        public DistributionValueOrBuilder getDistributionValueOrBuilder() {
            SingleFieldBuilderV3<DistributionValue, DistributionValue.Builder, DistributionValueOrBuilder> singleFieldBuilderV3;
            int i = this.valueCase_;
            if (i == 4 && (singleFieldBuilderV3 = this.distributionValueBuilder_) != null) {
                return (DistributionValueOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 4) {
                return (DistributionValue) this.value_;
            }
            return DistributionValue.getDefaultInstance();
        }

        private SingleFieldBuilderV3<DistributionValue, DistributionValue.Builder, DistributionValueOrBuilder> getDistributionValueFieldBuilder() {
            if (this.distributionValueBuilder_ == null) {
                if (this.valueCase_ != 4) {
                    this.value_ = DistributionValue.getDefaultInstance();
                }
                this.distributionValueBuilder_ = new SingleFieldBuilderV3<>((DistributionValue) this.value_, getParentForChildren(), isClean());
                this.value_ = null;
            }
            this.valueCase_ = 4;
            onChanged();
            return this.distributionValueBuilder_;
        }

        @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
        public SummaryValue getSummaryValue() {
            SingleFieldBuilderV3<SummaryValue, SummaryValue.Builder, SummaryValueOrBuilder> singleFieldBuilderV3 = this.summaryValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ == 5) {
                    return (SummaryValue) this.value_;
                }
                return SummaryValue.getDefaultInstance();
            }
            if (this.valueCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return SummaryValue.getDefaultInstance();
        }

        public Builder setSummaryValue(SummaryValue summaryValue) {
            SingleFieldBuilderV3<SummaryValue, SummaryValue.Builder, SummaryValueOrBuilder> singleFieldBuilderV3 = this.summaryValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                summaryValue.getClass();
                this.value_ = summaryValue;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(summaryValue);
            }
            this.valueCase_ = 5;
            return this;
        }

        public Builder setSummaryValue(SummaryValue.Builder builder) {
            SingleFieldBuilderV3<SummaryValue, SummaryValue.Builder, SummaryValueOrBuilder> singleFieldBuilderV3 = this.summaryValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.value_ = builder.m37244build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m37244build());
            }
            this.valueCase_ = 5;
            return this;
        }

        public Builder mergeSummaryValue(SummaryValue summaryValue) {
            SingleFieldBuilderV3<SummaryValue, SummaryValue.Builder, SummaryValueOrBuilder> singleFieldBuilderV3 = this.summaryValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.valueCase_ != 5 || this.value_ == SummaryValue.getDefaultInstance()) {
                    this.value_ = summaryValue;
                } else {
                    this.value_ = SummaryValue.newBuilder((SummaryValue) this.value_).mergeFrom(summaryValue).m37246buildPartial();
                }
                onChanged();
            } else {
                if (this.valueCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(summaryValue);
                }
                this.summaryValueBuilder_.setMessage(summaryValue);
            }
            this.valueCase_ = 5;
            return this;
        }

        public Builder clearSummaryValue() {
            SingleFieldBuilderV3<SummaryValue, SummaryValue.Builder, SummaryValueOrBuilder> singleFieldBuilderV3 = this.summaryValueBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.valueCase_ == 5) {
                    this.valueCase_ = 0;
                    this.value_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.valueCase_ == 5) {
                this.valueCase_ = 0;
                this.value_ = null;
                onChanged();
            }
            return this;
        }

        public SummaryValue.Builder getSummaryValueBuilder() {
            return getSummaryValueFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.metrics.v1.PointOrBuilder
        public SummaryValueOrBuilder getSummaryValueOrBuilder() {
            SingleFieldBuilderV3<SummaryValue, SummaryValue.Builder, SummaryValueOrBuilder> singleFieldBuilderV3;
            int i = this.valueCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.summaryValueBuilder_) != null) {
                return (SummaryValueOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (SummaryValue) this.value_;
            }
            return SummaryValue.getDefaultInstance();
        }

        private SingleFieldBuilderV3<SummaryValue, SummaryValue.Builder, SummaryValueOrBuilder> getSummaryValueFieldBuilder() {
            if (this.summaryValueBuilder_ == null) {
                if (this.valueCase_ != 5) {
                    this.value_ = SummaryValue.getDefaultInstance();
                }
                this.summaryValueBuilder_ = new SingleFieldBuilderV3<>((SummaryValue) this.value_, getParentForChildren(), isClean());
                this.value_ = null;
            }
            this.valueCase_ = 5;
            onChanged();
            return this.summaryValueBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37232setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37226mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.opencensus.proto.metrics.v1.Point$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$opencensus$proto$metrics$v1$Point$ValueCase;

        static {
            int[] iArr = new int[ValueCase.values().length];
            $SwitchMap$io$opencensus$proto$metrics$v1$Point$ValueCase = iArr;
            try {
                iArr[ValueCase.INT64_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$opencensus$proto$metrics$v1$Point$ValueCase[ValueCase.DOUBLE_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$opencensus$proto$metrics$v1$Point$ValueCase[ValueCase.DISTRIBUTION_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$opencensus$proto$metrics$v1$Point$ValueCase[ValueCase.SUMMARY_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$opencensus$proto$metrics$v1$Point$ValueCase[ValueCase.VALUE_NOT_SET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
