package io.opencensus.proto.metrics.v1;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.opencensus.proto.metrics.v1.LabelValue;
import io.opencensus.proto.metrics.v1.Point;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class TimeSeries extends GeneratedMessageV3 implements TimeSeriesOrBuilder {
    public static final int LABEL_VALUES_FIELD_NUMBER = 2;
    public static final int POINTS_FIELD_NUMBER = 3;
    public static final int START_TIMESTAMP_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final TimeSeries DEFAULT_INSTANCE = new TimeSeries();
    private static final Parser<TimeSeries> PARSER = new AbstractParser<TimeSeries>() { // from class: io.opencensus.proto.metrics.v1.TimeSeries.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public TimeSeries m37379parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new TimeSeries(codedInputStream, extensionRegistryLite);
        }
    };
    private int bitField0_;
    private List<LabelValue> labelValues_;
    private byte memoizedIsInitialized;
    private List<Point> points_;
    private Timestamp startTimestamp_;

    private TimeSeries(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private TimeSeries() {
        this.memoizedIsInitialized = (byte) -1;
        this.labelValues_ = Collections.emptyList();
        this.points_ = Collections.emptyList();
    }

    private TimeSeries(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (true) {
            if (z) {
                break;
            }
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                Timestamp timestamp = this.startTimestamp_;
                                Timestamp.Builder builder = timestamp != null ? timestamp.toBuilder() : null;
                                Timestamp message = codedInputStream.readMessage(Timestamp.parser(), extensionRegistryLite);
                                this.startTimestamp_ = message;
                                if (builder != null) {
                                    builder.mergeFrom(message);
                                    this.startTimestamp_ = builder.buildPartial();
                                }
                            } else if (tag == 18) {
                                if ((i & 2) != 2) {
                                    this.labelValues_ = new ArrayList();
                                    i |= 2;
                                }
                                this.labelValues_.add(codedInputStream.readMessage(LabelValue.parser(), extensionRegistryLite));
                            } else if (tag != 26) {
                                if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                if ((i & 4) != 4) {
                                    this.points_ = new ArrayList();
                                    i |= 4;
                                }
                                this.points_.add(codedInputStream.readMessage(Point.parser(), extensionRegistryLite));
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
                if ((i & 2) == 2) {
                    this.labelValues_ = Collections.unmodifiableList(this.labelValues_);
                }
                if ((i & 4) == 4) {
                    this.points_ = Collections.unmodifiableList(this.points_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static TimeSeries getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TimeSeries> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MetricsProto.internal_static_opencensus_proto_metrics_v1_TimeSeries_descriptor;
    }

    public static TimeSeries parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (TimeSeries) PARSER.parseFrom(byteBuffer);
    }

    public static TimeSeries parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TimeSeries) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static TimeSeries parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TimeSeries) PARSER.parseFrom(byteString);
    }

    public static TimeSeries parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TimeSeries) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static TimeSeries parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TimeSeries) PARSER.parseFrom(bArr);
    }

    public static TimeSeries parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TimeSeries) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static TimeSeries parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static TimeSeries parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TimeSeries parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static TimeSeries parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TimeSeries parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static TimeSeries parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m37377toBuilder();
    }

    public static Builder newBuilder(TimeSeries timeSeries) {
        return DEFAULT_INSTANCE.m37377toBuilder().mergeFrom(timeSeries);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public TimeSeries m37372getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
    public List<LabelValue> getLabelValuesList() {
        return this.labelValues_;
    }

    @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
    public List<? extends LabelValueOrBuilder> getLabelValuesOrBuilderList() {
        return this.labelValues_;
    }

    public Parser<TimeSeries> getParserForType() {
        return PARSER;
    }

    @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
    public List<Point> getPointsList() {
        return this.points_;
    }

    @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
    public List<? extends PointOrBuilder> getPointsOrBuilderList() {
        return this.points_;
    }

    @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
    public boolean hasStartTimestamp() {
        return this.startTimestamp_ != null;
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
        return MetricsProto.internal_static_opencensus_proto_metrics_v1_TimeSeries_fieldAccessorTable.ensureFieldAccessorsInitialized(TimeSeries.class, Builder.class);
    }

    @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
    public Timestamp getStartTimestamp() {
        Timestamp timestamp = this.startTimestamp_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
    public TimestampOrBuilder getStartTimestampOrBuilder() {
        return getStartTimestamp();
    }

    @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
    public int getLabelValuesCount() {
        return this.labelValues_.size();
    }

    @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
    public LabelValue getLabelValues(int i) {
        return this.labelValues_.get(i);
    }

    @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
    public LabelValueOrBuilder getLabelValuesOrBuilder(int i) {
        return this.labelValues_.get(i);
    }

    @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
    public int getPointsCount() {
        return this.points_.size();
    }

    @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
    public Point getPoints(int i) {
        return this.points_.get(i);
    }

    @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
    public PointOrBuilder getPointsOrBuilder(int i) {
        return this.points_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.startTimestamp_ != null) {
            codedOutputStream.writeMessage(1, getStartTimestamp());
        }
        for (int i = 0; i < this.labelValues_.size(); i++) {
            codedOutputStream.writeMessage(2, this.labelValues_.get(i));
        }
        for (int i2 = 0; i2 < this.points_.size(); i2++) {
            codedOutputStream.writeMessage(3, this.points_.get(i2));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.startTimestamp_ != null ? CodedOutputStream.computeMessageSize(1, getStartTimestamp()) : 0;
        for (int i2 = 0; i2 < this.labelValues_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, this.labelValues_.get(i2));
        }
        for (int i3 = 0; i3 < this.points_.size(); i3++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, this.points_.get(i3));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimeSeries)) {
            return super.equals(obj);
        }
        TimeSeries timeSeries = (TimeSeries) obj;
        boolean z = hasStartTimestamp() == timeSeries.hasStartTimestamp();
        if (!hasStartTimestamp() ? z : !(!z || !getStartTimestamp().equals(timeSeries.getStartTimestamp()))) {
            if (getLabelValuesList().equals(timeSeries.getLabelValuesList()) && getPointsList().equals(timeSeries.getPointsList()) && this.unknownFields.equals(timeSeries.unknownFields)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasStartTimestamp()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getStartTimestamp().hashCode();
        }
        if (getLabelValuesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getLabelValuesList().hashCode();
        }
        if (getPointsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getPointsList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37374newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37377toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TimeSeriesOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> labelValuesBuilder_;
        private List<LabelValue> labelValues_;
        private RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> pointsBuilder_;
        private List<Point> points_;
        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> startTimestampBuilder_;
        private Timestamp startTimestamp_;

        private Builder() {
            this.startTimestamp_ = null;
            this.labelValues_ = Collections.emptyList();
            this.points_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.startTimestamp_ = null;
            this.labelValues_ = Collections.emptyList();
            this.points_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MetricsProto.internal_static_opencensus_proto_metrics_v1_TimeSeries_descriptor;
        }

        @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
        public boolean hasStartTimestamp() {
            return (this.startTimestampBuilder_ == null && this.startTimestamp_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MetricsProto.internal_static_opencensus_proto_metrics_v1_TimeSeries_fieldAccessorTable.ensureFieldAccessorsInitialized(TimeSeries.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (TimeSeries.alwaysUseFieldBuilders) {
                getLabelValuesFieldBuilder();
                getPointsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37388clear() {
            super.clear();
            if (this.startTimestampBuilder_ == null) {
                this.startTimestamp_ = null;
            } else {
                this.startTimestamp_ = null;
                this.startTimestampBuilder_ = null;
            }
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.labelValues_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV32 = this.pointsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.points_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return MetricsProto.internal_static_opencensus_proto_metrics_v1_TimeSeries_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TimeSeries m37401getDefaultInstanceForType() {
            return TimeSeries.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TimeSeries m37382build() throws UninitializedMessageException {
            TimeSeries timeSeriesM37384buildPartial = m37384buildPartial();
            if (timeSeriesM37384buildPartial.isInitialized()) {
                return timeSeriesM37384buildPartial;
            }
            throw newUninitializedMessageException(timeSeriesM37384buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TimeSeries m37384buildPartial() {
            TimeSeries timeSeries = new TimeSeries(this);
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                timeSeries.startTimestamp_ = this.startTimestamp_;
            } else {
                timeSeries.startTimestamp_ = singleFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.labelValues_ = Collections.unmodifiableList(this.labelValues_);
                    this.bitField0_ &= -3;
                }
                timeSeries.labelValues_ = this.labelValues_;
            } else {
                timeSeries.labelValues_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV32 = this.pointsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.points_ = Collections.unmodifiableList(this.points_);
                    this.bitField0_ &= -5;
                }
                timeSeries.points_ = this.points_;
            } else {
                timeSeries.points_ = repeatedFieldBuilderV32.build();
            }
            timeSeries.bitField0_ = 0;
            onBuilt();
            return timeSeries;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37400clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37412setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37390clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37393clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37414setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37380addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37405mergeFrom(Message message) {
            if (message instanceof TimeSeries) {
                return mergeFrom((TimeSeries) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(TimeSeries timeSeries) {
            if (timeSeries == TimeSeries.getDefaultInstance()) {
                return this;
            }
            if (timeSeries.hasStartTimestamp()) {
                mergeStartTimestamp(timeSeries.getStartTimestamp());
            }
            if (this.labelValuesBuilder_ == null) {
                if (!timeSeries.labelValues_.isEmpty()) {
                    if (this.labelValues_.isEmpty()) {
                        this.labelValues_ = timeSeries.labelValues_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureLabelValuesIsMutable();
                        this.labelValues_.addAll(timeSeries.labelValues_);
                    }
                    onChanged();
                }
            } else if (!timeSeries.labelValues_.isEmpty()) {
                if (!this.labelValuesBuilder_.isEmpty()) {
                    this.labelValuesBuilder_.addAllMessages(timeSeries.labelValues_);
                } else {
                    this.labelValuesBuilder_.dispose();
                    this.labelValuesBuilder_ = null;
                    this.labelValues_ = timeSeries.labelValues_;
                    this.bitField0_ &= -3;
                    this.labelValuesBuilder_ = TimeSeries.alwaysUseFieldBuilders ? getLabelValuesFieldBuilder() : null;
                }
            }
            if (this.pointsBuilder_ == null) {
                if (!timeSeries.points_.isEmpty()) {
                    if (this.points_.isEmpty()) {
                        this.points_ = timeSeries.points_;
                        this.bitField0_ &= -5;
                    } else {
                        ensurePointsIsMutable();
                        this.points_.addAll(timeSeries.points_);
                    }
                    onChanged();
                }
            } else if (!timeSeries.points_.isEmpty()) {
                if (!this.pointsBuilder_.isEmpty()) {
                    this.pointsBuilder_.addAllMessages(timeSeries.points_);
                } else {
                    this.pointsBuilder_.dispose();
                    this.pointsBuilder_ = null;
                    this.points_ = timeSeries.points_;
                    this.bitField0_ &= -5;
                    this.pointsBuilder_ = TimeSeries.alwaysUseFieldBuilders ? getPointsFieldBuilder() : null;
                }
            }
            m37410mergeUnknownFields(timeSeries.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.metrics.v1.TimeSeries.Builder m37406mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.metrics.v1.TimeSeries.access$1100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.metrics.v1.TimeSeries r3 = (io.opencensus.proto.metrics.v1.TimeSeries) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.metrics.v1.TimeSeries r4 = (io.opencensus.proto.metrics.v1.TimeSeries) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.metrics.v1.TimeSeries.Builder.m37406mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.metrics.v1.TimeSeries$Builder");
        }

        @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
        public Timestamp getStartTimestamp() {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Timestamp timestamp = this.startTimestamp_;
            return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
        }

        public Builder setStartTimestamp(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                timestamp.getClass();
                this.startTimestamp_ = timestamp;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(timestamp);
            }
            return this;
        }

        public Builder setStartTimestamp(Timestamp.Builder builder) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.startTimestamp_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeStartTimestamp(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                Timestamp timestamp2 = this.startTimestamp_;
                if (timestamp2 != null) {
                    this.startTimestamp_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
                } else {
                    this.startTimestamp_ = timestamp;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(timestamp);
            }
            return this;
        }

        public Builder clearStartTimestamp() {
            if (this.startTimestampBuilder_ == null) {
                this.startTimestamp_ = null;
                onChanged();
            } else {
                this.startTimestamp_ = null;
                this.startTimestampBuilder_ = null;
            }
            return this;
        }

        public Timestamp.Builder getStartTimestampBuilder() {
            onChanged();
            return getStartTimestampFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
        public TimestampOrBuilder getStartTimestampOrBuilder() {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Timestamp timestamp = this.startTimestamp_;
            return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
        }

        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> getStartTimestampFieldBuilder() {
            if (this.startTimestampBuilder_ == null) {
                this.startTimestampBuilder_ = new SingleFieldBuilderV3<>(getStartTimestamp(), getParentForChildren(), isClean());
                this.startTimestamp_ = null;
            }
            return this.startTimestampBuilder_;
        }

        private void ensureLabelValuesIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.labelValues_ = new ArrayList(this.labelValues_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
        public List<LabelValue> getLabelValuesList() {
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.labelValues_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
        public int getLabelValuesCount() {
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.labelValues_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
        public LabelValue getLabelValues(int i) {
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.labelValues_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setLabelValues(int i, LabelValue labelValue) {
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                labelValue.getClass();
                ensureLabelValuesIsMutable();
                this.labelValues_.set(i, labelValue);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, labelValue);
            }
            return this;
        }

        public Builder setLabelValues(int i, LabelValue.Builder builder) {
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelValuesIsMutable();
                this.labelValues_.set(i, builder.m37059build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m37059build());
            }
            return this;
        }

        public Builder addLabelValues(LabelValue labelValue) {
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                labelValue.getClass();
                ensureLabelValuesIsMutable();
                this.labelValues_.add(labelValue);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(labelValue);
            }
            return this;
        }

        public Builder addLabelValues(int i, LabelValue labelValue) {
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                labelValue.getClass();
                ensureLabelValuesIsMutable();
                this.labelValues_.add(i, labelValue);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, labelValue);
            }
            return this;
        }

        public Builder addLabelValues(LabelValue.Builder builder) {
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelValuesIsMutable();
                this.labelValues_.add(builder.m37059build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m37059build());
            }
            return this;
        }

        public Builder addLabelValues(int i, LabelValue.Builder builder) {
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelValuesIsMutable();
                this.labelValues_.add(i, builder.m37059build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m37059build());
            }
            return this;
        }

        public Builder addAllLabelValues(Iterable<? extends LabelValue> iterable) {
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelValuesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.labelValues_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearLabelValues() {
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.labelValues_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeLabelValues(int i) {
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelValuesIsMutable();
                this.labelValues_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public LabelValue.Builder getLabelValuesBuilder(int i) {
            return getLabelValuesFieldBuilder().getBuilder(i);
        }

        @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
        public LabelValueOrBuilder getLabelValuesOrBuilder(int i) {
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.labelValues_.get(i);
            }
            return (LabelValueOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
        public List<? extends LabelValueOrBuilder> getLabelValuesOrBuilderList() {
            RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> repeatedFieldBuilderV3 = this.labelValuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.labelValues_);
        }

        public LabelValue.Builder addLabelValuesBuilder() {
            return getLabelValuesFieldBuilder().addBuilder(LabelValue.getDefaultInstance());
        }

        public LabelValue.Builder addLabelValuesBuilder(int i) {
            return getLabelValuesFieldBuilder().addBuilder(i, LabelValue.getDefaultInstance());
        }

        public List<LabelValue.Builder> getLabelValuesBuilderList() {
            return getLabelValuesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LabelValue, LabelValue.Builder, LabelValueOrBuilder> getLabelValuesFieldBuilder() {
            if (this.labelValuesBuilder_ == null) {
                this.labelValuesBuilder_ = new RepeatedFieldBuilderV3<>(this.labelValues_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.labelValues_ = null;
            }
            return this.labelValuesBuilder_;
        }

        private void ensurePointsIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.points_ = new ArrayList(this.points_);
                this.bitField0_ |= 4;
            }
        }

        @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
        public List<Point> getPointsList() {
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV3 = this.pointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.points_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
        public int getPointsCount() {
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV3 = this.pointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.points_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
        public Point getPoints(int i) {
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV3 = this.pointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.points_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setPoints(int i, Point point) {
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV3 = this.pointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                point.getClass();
                ensurePointsIsMutable();
                this.points_.set(i, point);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, point);
            }
            return this;
        }

        public Builder setPoints(int i, Point.Builder builder) {
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV3 = this.pointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePointsIsMutable();
                this.points_.set(i, builder.m37198build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m37198build());
            }
            return this;
        }

        public Builder addPoints(Point point) {
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV3 = this.pointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                point.getClass();
                ensurePointsIsMutable();
                this.points_.add(point);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(point);
            }
            return this;
        }

        public Builder addPoints(int i, Point point) {
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV3 = this.pointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                point.getClass();
                ensurePointsIsMutable();
                this.points_.add(i, point);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, point);
            }
            return this;
        }

        public Builder addPoints(Point.Builder builder) {
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV3 = this.pointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePointsIsMutable();
                this.points_.add(builder.m37198build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m37198build());
            }
            return this;
        }

        public Builder addPoints(int i, Point.Builder builder) {
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV3 = this.pointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePointsIsMutable();
                this.points_.add(i, builder.m37198build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m37198build());
            }
            return this;
        }

        public Builder addAllPoints(Iterable<? extends Point> iterable) {
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV3 = this.pointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePointsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.points_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearPoints() {
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV3 = this.pointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.points_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removePoints(int i) {
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV3 = this.pointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePointsIsMutable();
                this.points_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Point.Builder getPointsBuilder(int i) {
            return getPointsFieldBuilder().getBuilder(i);
        }

        @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
        public PointOrBuilder getPointsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV3 = this.pointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.points_.get(i);
            }
            return (PointOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.opencensus.proto.metrics.v1.TimeSeriesOrBuilder
        public List<? extends PointOrBuilder> getPointsOrBuilderList() {
            RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> repeatedFieldBuilderV3 = this.pointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.points_);
        }

        public Point.Builder addPointsBuilder() {
            return getPointsFieldBuilder().addBuilder(Point.getDefaultInstance());
        }

        public Point.Builder addPointsBuilder(int i) {
            return getPointsFieldBuilder().addBuilder(i, Point.getDefaultInstance());
        }

        public List<Point.Builder> getPointsBuilderList() {
            return getPointsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Point, Point.Builder, PointOrBuilder> getPointsFieldBuilder() {
            if (this.pointsBuilder_ == null) {
                this.pointsBuilder_ = new RepeatedFieldBuilderV3<>(this.points_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                this.points_ = null;
            }
            return this.pointsBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37416setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37410mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
