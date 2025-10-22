package io.opencensus.proto.metrics.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.DoubleValueOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Int64Value;
import com.google.protobuf.Int64ValueOrBuilder;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class SummaryValue extends GeneratedMessageV3 implements SummaryValueOrBuilder {
    public static final int COUNT_FIELD_NUMBER = 1;
    public static final int SNAPSHOT_FIELD_NUMBER = 3;
    public static final int SUM_FIELD_NUMBER = 2;
    private static final SummaryValue DEFAULT_INSTANCE = new SummaryValue();
    private static final Parser<SummaryValue> PARSER = new AbstractParser<SummaryValue>() { // from class: io.opencensus.proto.metrics.v1.SummaryValue.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public SummaryValue m37241parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SummaryValue(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private Int64Value count_;
    private byte memoizedIsInitialized;
    private Snapshot snapshot_;
    private DoubleValue sum_;

    private SummaryValue(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SummaryValue() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private SummaryValue(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Int64Value.Builder builderM37285toBuilder;
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
                            Int64Value int64Value = this.count_;
                            builderM37285toBuilder = int64Value != null ? int64Value.toBuilder() : null;
                            Int64Value message = codedInputStream.readMessage(Int64Value.parser(), extensionRegistryLite);
                            this.count_ = message;
                            if (builderM37285toBuilder != null) {
                                builderM37285toBuilder.mergeFrom(message);
                                this.count_ = builderM37285toBuilder.buildPartial();
                            }
                        } else if (tag == 18) {
                            DoubleValue doubleValue = this.sum_;
                            builderM37285toBuilder = doubleValue != null ? doubleValue.toBuilder() : null;
                            DoubleValue message2 = codedInputStream.readMessage(DoubleValue.parser(), extensionRegistryLite);
                            this.sum_ = message2;
                            if (builderM37285toBuilder != null) {
                                builderM37285toBuilder.mergeFrom(message2);
                                this.sum_ = builderM37285toBuilder.buildPartial();
                            }
                        } else if (tag != 26) {
                            if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            Snapshot snapshot = this.snapshot_;
                            builderM37285toBuilder = snapshot != null ? snapshot.m37285toBuilder() : null;
                            Snapshot snapshot2 = (Snapshot) codedInputStream.readMessage(Snapshot.parser(), extensionRegistryLite);
                            this.snapshot_ = snapshot2;
                            if (builderM37285toBuilder != null) {
                                builderM37285toBuilder.mergeFrom(snapshot2);
                                this.snapshot_ = builderM37285toBuilder.m37292buildPartial();
                            }
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

    public static SummaryValue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SummaryValue> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_descriptor;
    }

    public static SummaryValue parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (SummaryValue) PARSER.parseFrom(byteBuffer);
    }

    public static SummaryValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SummaryValue) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SummaryValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SummaryValue) PARSER.parseFrom(byteString);
    }

    public static SummaryValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SummaryValue) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SummaryValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SummaryValue) PARSER.parseFrom(bArr);
    }

    public static SummaryValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SummaryValue) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SummaryValue parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SummaryValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SummaryValue parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SummaryValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SummaryValue parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SummaryValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m37239toBuilder();
    }

    public static Builder newBuilder(SummaryValue summaryValue) {
        return DEFAULT_INSTANCE.m37239toBuilder().mergeFrom(summaryValue);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SummaryValue m37234getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<SummaryValue> getParserForType() {
        return PARSER;
    }

    @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
    public boolean hasCount() {
        return this.count_ != null;
    }

    @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
    public boolean hasSnapshot() {
        return this.snapshot_ != null;
    }

    @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
    public boolean hasSum() {
        return this.sum_ != null;
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
        return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_fieldAccessorTable.ensureFieldAccessorsInitialized(SummaryValue.class, Builder.class);
    }

    @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
    public Int64Value getCount() {
        Int64Value int64Value = this.count_;
        return int64Value == null ? Int64Value.getDefaultInstance() : int64Value;
    }

    @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
    public Int64ValueOrBuilder getCountOrBuilder() {
        return getCount();
    }

    @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
    public DoubleValue getSum() {
        DoubleValue doubleValue = this.sum_;
        return doubleValue == null ? DoubleValue.getDefaultInstance() : doubleValue;
    }

    @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
    public DoubleValueOrBuilder getSumOrBuilder() {
        return getSum();
    }

    @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
    public Snapshot getSnapshot() {
        Snapshot snapshot = this.snapshot_;
        return snapshot == null ? Snapshot.getDefaultInstance() : snapshot;
    }

    @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
    public SnapshotOrBuilder getSnapshotOrBuilder() {
        return getSnapshot();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.count_ != null) {
            codedOutputStream.writeMessage(1, getCount());
        }
        if (this.sum_ != null) {
            codedOutputStream.writeMessage(2, getSum());
        }
        if (this.snapshot_ != null) {
            codedOutputStream.writeMessage(3, getSnapshot());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.count_ != null ? CodedOutputStream.computeMessageSize(1, getCount()) : 0;
        if (this.sum_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getSum());
        }
        if (this.snapshot_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getSnapshot());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L4
            return r0
        L4:
            boolean r1 = r5 instanceof io.opencensus.proto.metrics.v1.SummaryValue
            if (r1 != 0) goto Ld
            boolean r5 = super.equals(r5)
            return r5
        Ld:
            io.opencensus.proto.metrics.v1.SummaryValue r5 = (io.opencensus.proto.metrics.v1.SummaryValue) r5
            boolean r1 = r4.hasCount()
            boolean r2 = r5.hasCount()
            r3 = 0
            if (r1 != r2) goto L1c
            r1 = 1
            goto L1d
        L1c:
            r1 = 0
        L1d:
            boolean r2 = r4.hasCount()
            if (r2 == 0) goto L34
            if (r1 == 0) goto L42
            com.google.protobuf.Int64Value r1 = r4.getCount()
            com.google.protobuf.Int64Value r2 = r5.getCount()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L42
            goto L36
        L34:
            if (r1 == 0) goto L42
        L36:
            boolean r1 = r4.hasSum()
            boolean r2 = r5.hasSum()
            if (r1 != r2) goto L42
            r1 = 1
            goto L43
        L42:
            r1 = 0
        L43:
            boolean r2 = r4.hasSum()
            if (r2 == 0) goto L5a
            if (r1 == 0) goto L68
            com.google.protobuf.DoubleValue r1 = r4.getSum()
            com.google.protobuf.DoubleValue r2 = r5.getSum()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L68
            goto L5c
        L5a:
            if (r1 == 0) goto L68
        L5c:
            boolean r1 = r4.hasSnapshot()
            boolean r2 = r5.hasSnapshot()
            if (r1 != r2) goto L68
            r1 = 1
            goto L69
        L68:
            r1 = 0
        L69:
            boolean r2 = r4.hasSnapshot()
            if (r2 == 0) goto L80
            if (r1 == 0) goto L8d
            io.opencensus.proto.metrics.v1.SummaryValue$Snapshot r1 = r4.getSnapshot()
            io.opencensus.proto.metrics.v1.SummaryValue$Snapshot r2 = r5.getSnapshot()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L8d
            goto L82
        L80:
            if (r1 == 0) goto L8d
        L82:
            com.google.protobuf.UnknownFieldSet r1 = r4.unknownFields
            com.google.protobuf.UnknownFieldSet r5 = r5.unknownFields
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L8d
            goto L8e
        L8d:
            r0 = 0
        L8e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.metrics.v1.SummaryValue.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasCount()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getCount().hashCode();
        }
        if (hasSum()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getSum().hashCode();
        }
        if (hasSnapshot()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getSnapshot().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37236newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37239toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public interface SnapshotOrBuilder extends MessageOrBuilder {
        Int64Value getCount();

        Int64ValueOrBuilder getCountOrBuilder();

        Snapshot.ValueAtPercentile getPercentileValues(int i);

        int getPercentileValuesCount();

        List<Snapshot.ValueAtPercentile> getPercentileValuesList();

        Snapshot.ValueAtPercentileOrBuilder getPercentileValuesOrBuilder(int i);

        List<? extends Snapshot.ValueAtPercentileOrBuilder> getPercentileValuesOrBuilderList();

        DoubleValue getSum();

        DoubleValueOrBuilder getSumOrBuilder();

        boolean hasCount();

        boolean hasSum();
    }

    public static final class Snapshot extends GeneratedMessageV3 implements SnapshotOrBuilder {
        public static final int COUNT_FIELD_NUMBER = 1;
        public static final int PERCENTILE_VALUES_FIELD_NUMBER = 3;
        public static final int SUM_FIELD_NUMBER = 2;
        private static final Snapshot DEFAULT_INSTANCE = new Snapshot();
        private static final Parser<Snapshot> PARSER = new AbstractParser<Snapshot>() { // from class: io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Snapshot m37287parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Snapshot(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private Int64Value count_;
        private byte memoizedIsInitialized;
        private List<ValueAtPercentile> percentileValues_;
        private DoubleValue sum_;

        private Snapshot(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Snapshot() {
            this.memoizedIsInitialized = (byte) -1;
            this.percentileValues_ = Collections.emptyList();
        }

        private Snapshot(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            Int64Value.Builder builder;
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
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                Int64Value int64Value = this.count_;
                                builder = int64Value != null ? int64Value.toBuilder() : null;
                                Int64Value message = codedInputStream.readMessage(Int64Value.parser(), extensionRegistryLite);
                                this.count_ = message;
                                if (builder != null) {
                                    builder.mergeFrom(message);
                                    this.count_ = builder.buildPartial();
                                }
                            } else if (tag == 18) {
                                DoubleValue doubleValue = this.sum_;
                                builder = doubleValue != null ? doubleValue.toBuilder() : null;
                                DoubleValue message2 = codedInputStream.readMessage(DoubleValue.parser(), extensionRegistryLite);
                                this.sum_ = message2;
                                if (builder != null) {
                                    builder.mergeFrom(message2);
                                    this.sum_ = builder.buildPartial();
                                }
                            } else if (tag != 26) {
                                if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                if ((i & 4) != 4) {
                                    this.percentileValues_ = new ArrayList();
                                    i |= 4;
                                }
                                this.percentileValues_.add(codedInputStream.readMessage(ValueAtPercentile.parser(), extensionRegistryLite));
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    if ((i & 4) == 4) {
                        this.percentileValues_ = Collections.unmodifiableList(this.percentileValues_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static Snapshot getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Snapshot> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_Snapshot_descriptor;
        }

        public static Snapshot parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Snapshot) PARSER.parseFrom(byteBuffer);
        }

        public static Snapshot parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Snapshot) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Snapshot parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Snapshot) PARSER.parseFrom(byteString);
        }

        public static Snapshot parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Snapshot) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Snapshot parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Snapshot) PARSER.parseFrom(bArr);
        }

        public static Snapshot parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Snapshot) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Snapshot parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Snapshot parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Snapshot parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Snapshot parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Snapshot parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Snapshot parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m37285toBuilder();
        }

        public static Builder newBuilder(Snapshot snapshot) {
            return DEFAULT_INSTANCE.m37285toBuilder().mergeFrom(snapshot);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Snapshot m37280getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Snapshot> getParserForType() {
            return PARSER;
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
        public List<ValueAtPercentile> getPercentileValuesList() {
            return this.percentileValues_;
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
        public List<? extends ValueAtPercentileOrBuilder> getPercentileValuesOrBuilderList() {
            return this.percentileValues_;
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
        public boolean hasCount() {
            return this.count_ != null;
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
        public boolean hasSum() {
            return this.sum_ != null;
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
            return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_Snapshot_fieldAccessorTable.ensureFieldAccessorsInitialized(Snapshot.class, Builder.class);
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
        public Int64Value getCount() {
            Int64Value int64Value = this.count_;
            return int64Value == null ? Int64Value.getDefaultInstance() : int64Value;
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
        public Int64ValueOrBuilder getCountOrBuilder() {
            return getCount();
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
        public DoubleValue getSum() {
            DoubleValue doubleValue = this.sum_;
            return doubleValue == null ? DoubleValue.getDefaultInstance() : doubleValue;
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
        public DoubleValueOrBuilder getSumOrBuilder() {
            return getSum();
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
        public int getPercentileValuesCount() {
            return this.percentileValues_.size();
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
        public ValueAtPercentile getPercentileValues(int i) {
            return this.percentileValues_.get(i);
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
        public ValueAtPercentileOrBuilder getPercentileValuesOrBuilder(int i) {
            return this.percentileValues_.get(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.count_ != null) {
                codedOutputStream.writeMessage(1, getCount());
            }
            if (this.sum_ != null) {
                codedOutputStream.writeMessage(2, getSum());
            }
            for (int i = 0; i < this.percentileValues_.size(); i++) {
                codedOutputStream.writeMessage(3, this.percentileValues_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = this.count_ != null ? CodedOutputStream.computeMessageSize(1, getCount()) : 0;
            if (this.sum_ != null) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getSum());
            }
            for (int i2 = 0; i2 < this.percentileValues_.size(); i2++) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(3, this.percentileValues_.get(i2));
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x0042  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean equals(java.lang.Object r5) {
            /*
                r4 = this;
                r0 = 1
                if (r5 != r4) goto L4
                return r0
            L4:
                boolean r1 = r5 instanceof io.opencensus.proto.metrics.v1.SummaryValue.Snapshot
                if (r1 != 0) goto Ld
                boolean r5 = super.equals(r5)
                return r5
            Ld:
                io.opencensus.proto.metrics.v1.SummaryValue$Snapshot r5 = (io.opencensus.proto.metrics.v1.SummaryValue.Snapshot) r5
                boolean r1 = r4.hasCount()
                boolean r2 = r5.hasCount()
                r3 = 0
                if (r1 != r2) goto L1c
                r1 = 1
                goto L1d
            L1c:
                r1 = 0
            L1d:
                boolean r2 = r4.hasCount()
                if (r2 == 0) goto L34
                if (r1 == 0) goto L42
                com.google.protobuf.Int64Value r1 = r4.getCount()
                com.google.protobuf.Int64Value r2 = r5.getCount()
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L42
                goto L36
            L34:
                if (r1 == 0) goto L42
            L36:
                boolean r1 = r4.hasSum()
                boolean r2 = r5.hasSum()
                if (r1 != r2) goto L42
                r1 = 1
                goto L43
            L42:
                r1 = 0
            L43:
                boolean r2 = r4.hasSum()
                if (r2 == 0) goto L5a
                if (r1 == 0) goto L75
                com.google.protobuf.DoubleValue r1 = r4.getSum()
                com.google.protobuf.DoubleValue r2 = r5.getSum()
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L75
                goto L5c
            L5a:
                if (r1 == 0) goto L75
            L5c:
                java.util.List r1 = r4.getPercentileValuesList()
                java.util.List r2 = r5.getPercentileValuesList()
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L75
                com.google.protobuf.UnknownFieldSet r1 = r4.unknownFields
                com.google.protobuf.UnknownFieldSet r5 = r5.unknownFields
                boolean r5 = r1.equals(r5)
                if (r5 == 0) goto L75
                goto L76
            L75:
                r0 = 0
            L76:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasCount()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getCount().hashCode();
            }
            if (hasSum()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getSum().hashCode();
            }
            if (getPercentileValuesCount() > 0) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getPercentileValuesList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37282newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37285toBuilder() {
            if (this == DEFAULT_INSTANCE) {
                return new Builder();
            }
            return new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public interface ValueAtPercentileOrBuilder extends MessageOrBuilder {
            double getPercentile();

            double getValue();
        }

        public static final class ValueAtPercentile extends GeneratedMessageV3 implements ValueAtPercentileOrBuilder {
            public static final int PERCENTILE_FIELD_NUMBER = 1;
            public static final int VALUE_FIELD_NUMBER = 2;
            private static final ValueAtPercentile DEFAULT_INSTANCE = new ValueAtPercentile();
            private static final Parser<ValueAtPercentile> PARSER = new AbstractParser<ValueAtPercentile>() { // from class: io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.ValueAtPercentile.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public ValueAtPercentile m37333parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new ValueAtPercentile(codedInputStream, extensionRegistryLite);
                }
            };
            private static final long serialVersionUID = 0;
            private byte memoizedIsInitialized;
            private double percentile_;
            private double value_;

            private ValueAtPercentile(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private ValueAtPercentile() {
                this.memoizedIsInitialized = (byte) -1;
                this.percentile_ = 0.0d;
                this.value_ = 0.0d;
            }

            private ValueAtPercentile(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.percentile_ = codedInputStream.readDouble();
                                    } else if (tag != 17) {
                                        if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                        }
                                    } else {
                                        this.value_ = codedInputStream.readDouble();
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

            public static ValueAtPercentile getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ValueAtPercentile> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_Snapshot_ValueAtPercentile_descriptor;
            }

            public static ValueAtPercentile parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (ValueAtPercentile) PARSER.parseFrom(byteBuffer);
            }

            public static ValueAtPercentile parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ValueAtPercentile) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static ValueAtPercentile parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (ValueAtPercentile) PARSER.parseFrom(byteString);
            }

            public static ValueAtPercentile parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ValueAtPercentile) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static ValueAtPercentile parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (ValueAtPercentile) PARSER.parseFrom(bArr);
            }

            public static ValueAtPercentile parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ValueAtPercentile) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static ValueAtPercentile parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static ValueAtPercentile parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static ValueAtPercentile parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static ValueAtPercentile parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static ValueAtPercentile parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static ValueAtPercentile parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m37331toBuilder();
            }

            public static Builder newBuilder(ValueAtPercentile valueAtPercentile) {
                return DEFAULT_INSTANCE.m37331toBuilder().mergeFrom(valueAtPercentile);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ValueAtPercentile m37326getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public Parser<ValueAtPercentile> getParserForType() {
                return PARSER;
            }

            @Override // io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.ValueAtPercentileOrBuilder
            public double getPercentile() {
                return this.percentile_;
            }

            @Override // io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.ValueAtPercentileOrBuilder
            public double getValue() {
                return this.value_;
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
                return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_Snapshot_ValueAtPercentile_fieldAccessorTable.ensureFieldAccessorsInitialized(ValueAtPercentile.class, Builder.class);
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                double d = this.percentile_;
                if (d != 0.0d) {
                    codedOutputStream.writeDouble(1, d);
                }
                double d2 = this.value_;
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
                double d = this.percentile_;
                int iComputeDoubleSize = d != 0.0d ? CodedOutputStream.computeDoubleSize(1, d) : 0;
                double d2 = this.value_;
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
                if (!(obj instanceof ValueAtPercentile)) {
                    return super.equals(obj);
                }
                ValueAtPercentile valueAtPercentile = (ValueAtPercentile) obj;
                return Double.doubleToLongBits(getPercentile()) == Double.doubleToLongBits(valueAtPercentile.getPercentile()) && Double.doubleToLongBits(getValue()) == Double.doubleToLongBits(valueAtPercentile.getValue()) && this.unknownFields.equals(valueAtPercentile.unknownFields);
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getPercentile()))) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getValue()))) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode;
                return iHashCode;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m37328newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m37331toBuilder() {
                if (this == DEFAULT_INSTANCE) {
                    return new Builder();
                }
                return new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ValueAtPercentileOrBuilder {
                private double percentile_;
                private double value_;

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_Snapshot_ValueAtPercentile_descriptor;
                }

                @Override // io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.ValueAtPercentileOrBuilder
                public double getPercentile() {
                    return this.percentile_;
                }

                public Builder setPercentile(double d) {
                    this.percentile_ = d;
                    onChanged();
                    return this;
                }

                @Override // io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.ValueAtPercentileOrBuilder
                public double getValue() {
                    return this.value_;
                }

                public Builder setValue(double d) {
                    this.value_ = d;
                    onChanged();
                    return this;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_Snapshot_ValueAtPercentile_fieldAccessorTable.ensureFieldAccessorsInitialized(ValueAtPercentile.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = ValueAtPercentile.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m37342clear() {
                    super.clear();
                    this.percentile_ = 0.0d;
                    this.value_ = 0.0d;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_Snapshot_ValueAtPercentile_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public ValueAtPercentile m37355getDefaultInstanceForType() {
                    return ValueAtPercentile.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public ValueAtPercentile m37336build() throws UninitializedMessageException {
                    ValueAtPercentile valueAtPercentileM37338buildPartial = m37338buildPartial();
                    if (valueAtPercentileM37338buildPartial.isInitialized()) {
                        return valueAtPercentileM37338buildPartial;
                    }
                    throw newUninitializedMessageException(valueAtPercentileM37338buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public ValueAtPercentile m37338buildPartial() {
                    ValueAtPercentile valueAtPercentile = new ValueAtPercentile(this);
                    valueAtPercentile.percentile_ = this.percentile_;
                    valueAtPercentile.value_ = this.value_;
                    onBuilt();
                    return valueAtPercentile;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m37354clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m37366setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m37344clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m37347clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m37368setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m37334addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m37359mergeFrom(Message message) {
                    if (message instanceof ValueAtPercentile) {
                        return mergeFrom((ValueAtPercentile) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(ValueAtPercentile valueAtPercentile) {
                    if (valueAtPercentile == ValueAtPercentile.getDefaultInstance()) {
                        return this;
                    }
                    if (valueAtPercentile.getPercentile() != 0.0d) {
                        setPercentile(valueAtPercentile.getPercentile());
                    }
                    if (valueAtPercentile.getValue() != 0.0d) {
                        setValue(valueAtPercentile.getValue());
                    }
                    m37364mergeUnknownFields(valueAtPercentile.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.ValueAtPercentile.Builder m37360mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.ValueAtPercentile.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        io.opencensus.proto.metrics.v1.SummaryValue$Snapshot$ValueAtPercentile r3 = (io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.ValueAtPercentile) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        io.opencensus.proto.metrics.v1.SummaryValue$Snapshot$ValueAtPercentile r4 = (io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.ValueAtPercentile) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.ValueAtPercentile.Builder.m37360mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.metrics.v1.SummaryValue$Snapshot$ValueAtPercentile$Builder");
                }

                public Builder clearPercentile() {
                    this.percentile_ = 0.0d;
                    onChanged();
                    return this;
                }

                public Builder clearValue() {
                    this.value_ = 0.0d;
                    onChanged();
                    return this;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m37370setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m37364mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SnapshotOrBuilder {
            private int bitField0_;
            private SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> countBuilder_;
            private Int64Value count_;
            private RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> percentileValuesBuilder_;
            private List<ValueAtPercentile> percentileValues_;
            private SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> sumBuilder_;
            private DoubleValue sum_;

            private Builder() {
                this.count_ = null;
                this.sum_ = null;
                this.percentileValues_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.count_ = null;
                this.sum_ = null;
                this.percentileValues_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_Snapshot_descriptor;
            }

            @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
            public boolean hasCount() {
                return (this.countBuilder_ == null && this.count_ == null) ? false : true;
            }

            @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
            public boolean hasSum() {
                return (this.sumBuilder_ == null && this.sum_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_Snapshot_fieldAccessorTable.ensureFieldAccessorsInitialized(Snapshot.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (Snapshot.alwaysUseFieldBuilders) {
                    getPercentileValuesFieldBuilder();
                }
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m37296clear() {
                super.clear();
                if (this.countBuilder_ == null) {
                    this.count_ = null;
                } else {
                    this.count_ = null;
                    this.countBuilder_ = null;
                }
                if (this.sumBuilder_ == null) {
                    this.sum_ = null;
                } else {
                    this.sum_ = null;
                    this.sumBuilder_ = null;
                }
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.percentileValues_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_Snapshot_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Snapshot m37309getDefaultInstanceForType() {
                return Snapshot.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Snapshot m37290build() throws UninitializedMessageException {
                Snapshot snapshotM37292buildPartial = m37292buildPartial();
                if (snapshotM37292buildPartial.isInitialized()) {
                    return snapshotM37292buildPartial;
                }
                throw newUninitializedMessageException(snapshotM37292buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Snapshot m37292buildPartial() {
                Snapshot snapshot = new Snapshot(this);
                SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> singleFieldBuilderV3 = this.countBuilder_;
                if (singleFieldBuilderV3 == null) {
                    snapshot.count_ = this.count_;
                } else {
                    snapshot.count_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV32 = this.sumBuilder_;
                if (singleFieldBuilderV32 == null) {
                    snapshot.sum_ = this.sum_;
                } else {
                    snapshot.sum_ = singleFieldBuilderV32.build();
                }
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.percentileValues_ = Collections.unmodifiableList(this.percentileValues_);
                        this.bitField0_ &= -5;
                    }
                    snapshot.percentileValues_ = this.percentileValues_;
                } else {
                    snapshot.percentileValues_ = repeatedFieldBuilderV3.build();
                }
                snapshot.bitField0_ = 0;
                onBuilt();
                return snapshot;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m37308clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m37320setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m37298clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m37301clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m37322setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m37288addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m37313mergeFrom(Message message) {
                if (message instanceof Snapshot) {
                    return mergeFrom((Snapshot) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Snapshot snapshot) {
                if (snapshot == Snapshot.getDefaultInstance()) {
                    return this;
                }
                if (snapshot.hasCount()) {
                    mergeCount(snapshot.getCount());
                }
                if (snapshot.hasSum()) {
                    mergeSum(snapshot.getSum());
                }
                if (this.percentileValuesBuilder_ == null) {
                    if (!snapshot.percentileValues_.isEmpty()) {
                        if (this.percentileValues_.isEmpty()) {
                            this.percentileValues_ = snapshot.percentileValues_;
                            this.bitField0_ &= -5;
                        } else {
                            ensurePercentileValuesIsMutable();
                            this.percentileValues_.addAll(snapshot.percentileValues_);
                        }
                        onChanged();
                    }
                } else if (!snapshot.percentileValues_.isEmpty()) {
                    if (!this.percentileValuesBuilder_.isEmpty()) {
                        this.percentileValuesBuilder_.addAllMessages(snapshot.percentileValues_);
                    } else {
                        this.percentileValuesBuilder_.dispose();
                        this.percentileValuesBuilder_ = null;
                        this.percentileValues_ = snapshot.percentileValues_;
                        this.bitField0_ &= -5;
                        this.percentileValuesBuilder_ = Snapshot.alwaysUseFieldBuilders ? getPercentileValuesFieldBuilder() : null;
                    }
                }
                m37318mergeUnknownFields(snapshot.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.Builder m37314mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.access$1900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.opencensus.proto.metrics.v1.SummaryValue$Snapshot r3 = (io.opencensus.proto.metrics.v1.SummaryValue.Snapshot) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.opencensus.proto.metrics.v1.SummaryValue$Snapshot r4 = (io.opencensus.proto.metrics.v1.SummaryValue.Snapshot) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.metrics.v1.SummaryValue.Snapshot.Builder.m37314mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.metrics.v1.SummaryValue$Snapshot$Builder");
            }

            @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
            public Int64Value getCount() {
                SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> singleFieldBuilderV3 = this.countBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Int64Value int64Value = this.count_;
                return int64Value == null ? Int64Value.getDefaultInstance() : int64Value;
            }

            public Builder setCount(Int64Value int64Value) {
                SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> singleFieldBuilderV3 = this.countBuilder_;
                if (singleFieldBuilderV3 == null) {
                    int64Value.getClass();
                    this.count_ = int64Value;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(int64Value);
                }
                return this;
            }

            public Builder setCount(Int64Value.Builder builder) {
                SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> singleFieldBuilderV3 = this.countBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.count_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeCount(Int64Value int64Value) {
                SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> singleFieldBuilderV3 = this.countBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Int64Value int64Value2 = this.count_;
                    if (int64Value2 != null) {
                        this.count_ = Int64Value.newBuilder(int64Value2).mergeFrom(int64Value).buildPartial();
                    } else {
                        this.count_ = int64Value;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(int64Value);
                }
                return this;
            }

            public Builder clearCount() {
                if (this.countBuilder_ == null) {
                    this.count_ = null;
                    onChanged();
                } else {
                    this.count_ = null;
                    this.countBuilder_ = null;
                }
                return this;
            }

            public Int64Value.Builder getCountBuilder() {
                onChanged();
                return getCountFieldBuilder().getBuilder();
            }

            @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
            public Int64ValueOrBuilder getCountOrBuilder() {
                SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> singleFieldBuilderV3 = this.countBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Int64Value int64Value = this.count_;
                return int64Value == null ? Int64Value.getDefaultInstance() : int64Value;
            }

            private SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> getCountFieldBuilder() {
                if (this.countBuilder_ == null) {
                    this.countBuilder_ = new SingleFieldBuilderV3<>(getCount(), getParentForChildren(), isClean());
                    this.count_ = null;
                }
                return this.countBuilder_;
            }

            @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
            public DoubleValue getSum() {
                SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.sumBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                DoubleValue doubleValue = this.sum_;
                return doubleValue == null ? DoubleValue.getDefaultInstance() : doubleValue;
            }

            public Builder setSum(DoubleValue doubleValue) {
                SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.sumBuilder_;
                if (singleFieldBuilderV3 == null) {
                    doubleValue.getClass();
                    this.sum_ = doubleValue;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(doubleValue);
                }
                return this;
            }

            public Builder setSum(DoubleValue.Builder builder) {
                SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.sumBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.sum_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeSum(DoubleValue doubleValue) {
                SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.sumBuilder_;
                if (singleFieldBuilderV3 == null) {
                    DoubleValue doubleValue2 = this.sum_;
                    if (doubleValue2 != null) {
                        this.sum_ = DoubleValue.newBuilder(doubleValue2).mergeFrom(doubleValue).buildPartial();
                    } else {
                        this.sum_ = doubleValue;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(doubleValue);
                }
                return this;
            }

            public Builder clearSum() {
                if (this.sumBuilder_ == null) {
                    this.sum_ = null;
                    onChanged();
                } else {
                    this.sum_ = null;
                    this.sumBuilder_ = null;
                }
                return this;
            }

            public DoubleValue.Builder getSumBuilder() {
                onChanged();
                return getSumFieldBuilder().getBuilder();
            }

            @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
            public DoubleValueOrBuilder getSumOrBuilder() {
                SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.sumBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                DoubleValue doubleValue = this.sum_;
                return doubleValue == null ? DoubleValue.getDefaultInstance() : doubleValue;
            }

            private SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> getSumFieldBuilder() {
                if (this.sumBuilder_ == null) {
                    this.sumBuilder_ = new SingleFieldBuilderV3<>(getSum(), getParentForChildren(), isClean());
                    this.sum_ = null;
                }
                return this.sumBuilder_;
            }

            private void ensurePercentileValuesIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.percentileValues_ = new ArrayList(this.percentileValues_);
                    this.bitField0_ |= 4;
                }
            }

            @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
            public List<ValueAtPercentile> getPercentileValuesList() {
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.percentileValues_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
            public int getPercentileValuesCount() {
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.percentileValues_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
            public ValueAtPercentile getPercentileValues(int i) {
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.percentileValues_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setPercentileValues(int i, ValueAtPercentile valueAtPercentile) {
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    valueAtPercentile.getClass();
                    ensurePercentileValuesIsMutable();
                    this.percentileValues_.set(i, valueAtPercentile);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, valueAtPercentile);
                }
                return this;
            }

            public Builder setPercentileValues(int i, ValueAtPercentile.Builder builder) {
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensurePercentileValuesIsMutable();
                    this.percentileValues_.set(i, builder.m37336build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.m37336build());
                }
                return this;
            }

            public Builder addPercentileValues(ValueAtPercentile valueAtPercentile) {
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    valueAtPercentile.getClass();
                    ensurePercentileValuesIsMutable();
                    this.percentileValues_.add(valueAtPercentile);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(valueAtPercentile);
                }
                return this;
            }

            public Builder addPercentileValues(int i, ValueAtPercentile valueAtPercentile) {
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    valueAtPercentile.getClass();
                    ensurePercentileValuesIsMutable();
                    this.percentileValues_.add(i, valueAtPercentile);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, valueAtPercentile);
                }
                return this;
            }

            public Builder addPercentileValues(ValueAtPercentile.Builder builder) {
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensurePercentileValuesIsMutable();
                    this.percentileValues_.add(builder.m37336build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.m37336build());
                }
                return this;
            }

            public Builder addPercentileValues(int i, ValueAtPercentile.Builder builder) {
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensurePercentileValuesIsMutable();
                    this.percentileValues_.add(i, builder.m37336build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.m37336build());
                }
                return this;
            }

            public Builder addAllPercentileValues(Iterable<? extends ValueAtPercentile> iterable) {
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensurePercentileValuesIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.percentileValues_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearPercentileValues() {
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.percentileValues_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removePercentileValues(int i) {
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensurePercentileValuesIsMutable();
                    this.percentileValues_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public ValueAtPercentile.Builder getPercentileValuesBuilder(int i) {
                return getPercentileValuesFieldBuilder().getBuilder(i);
            }

            @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
            public ValueAtPercentileOrBuilder getPercentileValuesOrBuilder(int i) {
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.percentileValues_.get(i);
                }
                return (ValueAtPercentileOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // io.opencensus.proto.metrics.v1.SummaryValue.SnapshotOrBuilder
            public List<? extends ValueAtPercentileOrBuilder> getPercentileValuesOrBuilderList() {
                RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> repeatedFieldBuilderV3 = this.percentileValuesBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.percentileValues_);
            }

            public ValueAtPercentile.Builder addPercentileValuesBuilder() {
                return getPercentileValuesFieldBuilder().addBuilder(ValueAtPercentile.getDefaultInstance());
            }

            public ValueAtPercentile.Builder addPercentileValuesBuilder(int i) {
                return getPercentileValuesFieldBuilder().addBuilder(i, ValueAtPercentile.getDefaultInstance());
            }

            public List<ValueAtPercentile.Builder> getPercentileValuesBuilderList() {
                return getPercentileValuesFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<ValueAtPercentile, ValueAtPercentile.Builder, ValueAtPercentileOrBuilder> getPercentileValuesFieldBuilder() {
                if (this.percentileValuesBuilder_ == null) {
                    this.percentileValuesBuilder_ = new RepeatedFieldBuilderV3<>(this.percentileValues_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                    this.percentileValues_ = null;
                }
                return this.percentileValuesBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m37324setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m37318mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SummaryValueOrBuilder {
        private SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> countBuilder_;
        private Int64Value count_;
        private SingleFieldBuilderV3<Snapshot, Snapshot.Builder, SnapshotOrBuilder> snapshotBuilder_;
        private Snapshot snapshot_;
        private SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> sumBuilder_;
        private DoubleValue sum_;

        private Builder() {
            this.count_ = null;
            this.sum_ = null;
            this.snapshot_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.count_ = null;
            this.sum_ = null;
            this.snapshot_ = null;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_descriptor;
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
        public boolean hasCount() {
            return (this.countBuilder_ == null && this.count_ == null) ? false : true;
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
        public boolean hasSnapshot() {
            return (this.snapshotBuilder_ == null && this.snapshot_ == null) ? false : true;
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
        public boolean hasSum() {
            return (this.sumBuilder_ == null && this.sum_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_fieldAccessorTable.ensureFieldAccessorsInitialized(SummaryValue.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SummaryValue.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37250clear() {
            super.clear();
            if (this.countBuilder_ == null) {
                this.count_ = null;
            } else {
                this.count_ = null;
                this.countBuilder_ = null;
            }
            if (this.sumBuilder_ == null) {
                this.sum_ = null;
            } else {
                this.sum_ = null;
                this.sumBuilder_ = null;
            }
            if (this.snapshotBuilder_ == null) {
                this.snapshot_ = null;
            } else {
                this.snapshot_ = null;
                this.snapshotBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return MetricsProto.internal_static_opencensus_proto_metrics_v1_SummaryValue_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SummaryValue m37263getDefaultInstanceForType() {
            return SummaryValue.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SummaryValue m37244build() throws UninitializedMessageException {
            SummaryValue summaryValueM37246buildPartial = m37246buildPartial();
            if (summaryValueM37246buildPartial.isInitialized()) {
                return summaryValueM37246buildPartial;
            }
            throw newUninitializedMessageException(summaryValueM37246buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SummaryValue m37246buildPartial() {
            SummaryValue summaryValue = new SummaryValue(this);
            SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> singleFieldBuilderV3 = this.countBuilder_;
            if (singleFieldBuilderV3 == null) {
                summaryValue.count_ = this.count_;
            } else {
                summaryValue.count_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV32 = this.sumBuilder_;
            if (singleFieldBuilderV32 == null) {
                summaryValue.sum_ = this.sum_;
            } else {
                summaryValue.sum_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<Snapshot, Snapshot.Builder, SnapshotOrBuilder> singleFieldBuilderV33 = this.snapshotBuilder_;
            if (singleFieldBuilderV33 == null) {
                summaryValue.snapshot_ = this.snapshot_;
            } else {
                summaryValue.snapshot_ = singleFieldBuilderV33.build();
            }
            onBuilt();
            return summaryValue;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37262clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37274setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37252clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37255clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37276setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37242addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37267mergeFrom(Message message) {
            if (message instanceof SummaryValue) {
                return mergeFrom((SummaryValue) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SummaryValue summaryValue) {
            if (summaryValue == SummaryValue.getDefaultInstance()) {
                return this;
            }
            if (summaryValue.hasCount()) {
                mergeCount(summaryValue.getCount());
            }
            if (summaryValue.hasSum()) {
                mergeSum(summaryValue.getSum());
            }
            if (summaryValue.hasSnapshot()) {
                mergeSnapshot(summaryValue.getSnapshot());
            }
            m37272mergeUnknownFields(summaryValue.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.metrics.v1.SummaryValue.Builder m37268mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.metrics.v1.SummaryValue.access$2900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.metrics.v1.SummaryValue r3 = (io.opencensus.proto.metrics.v1.SummaryValue) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.metrics.v1.SummaryValue r4 = (io.opencensus.proto.metrics.v1.SummaryValue) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.metrics.v1.SummaryValue.Builder.m37268mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.metrics.v1.SummaryValue$Builder");
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
        public Int64Value getCount() {
            SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> singleFieldBuilderV3 = this.countBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Int64Value int64Value = this.count_;
            return int64Value == null ? Int64Value.getDefaultInstance() : int64Value;
        }

        public Builder setCount(Int64Value int64Value) {
            SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> singleFieldBuilderV3 = this.countBuilder_;
            if (singleFieldBuilderV3 == null) {
                int64Value.getClass();
                this.count_ = int64Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(int64Value);
            }
            return this;
        }

        public Builder setCount(Int64Value.Builder builder) {
            SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> singleFieldBuilderV3 = this.countBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.count_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeCount(Int64Value int64Value) {
            SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> singleFieldBuilderV3 = this.countBuilder_;
            if (singleFieldBuilderV3 == null) {
                Int64Value int64Value2 = this.count_;
                if (int64Value2 != null) {
                    this.count_ = Int64Value.newBuilder(int64Value2).mergeFrom(int64Value).buildPartial();
                } else {
                    this.count_ = int64Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(int64Value);
            }
            return this;
        }

        public Builder clearCount() {
            if (this.countBuilder_ == null) {
                this.count_ = null;
                onChanged();
            } else {
                this.count_ = null;
                this.countBuilder_ = null;
            }
            return this;
        }

        public Int64Value.Builder getCountBuilder() {
            onChanged();
            return getCountFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
        public Int64ValueOrBuilder getCountOrBuilder() {
            SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> singleFieldBuilderV3 = this.countBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Int64Value int64Value = this.count_;
            return int64Value == null ? Int64Value.getDefaultInstance() : int64Value;
        }

        private SingleFieldBuilderV3<Int64Value, Int64Value.Builder, Int64ValueOrBuilder> getCountFieldBuilder() {
            if (this.countBuilder_ == null) {
                this.countBuilder_ = new SingleFieldBuilderV3<>(getCount(), getParentForChildren(), isClean());
                this.count_ = null;
            }
            return this.countBuilder_;
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
        public DoubleValue getSum() {
            SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.sumBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            DoubleValue doubleValue = this.sum_;
            return doubleValue == null ? DoubleValue.getDefaultInstance() : doubleValue;
        }

        public Builder setSum(DoubleValue doubleValue) {
            SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.sumBuilder_;
            if (singleFieldBuilderV3 == null) {
                doubleValue.getClass();
                this.sum_ = doubleValue;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(doubleValue);
            }
            return this;
        }

        public Builder setSum(DoubleValue.Builder builder) {
            SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.sumBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sum_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSum(DoubleValue doubleValue) {
            SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.sumBuilder_;
            if (singleFieldBuilderV3 == null) {
                DoubleValue doubleValue2 = this.sum_;
                if (doubleValue2 != null) {
                    this.sum_ = DoubleValue.newBuilder(doubleValue2).mergeFrom(doubleValue).buildPartial();
                } else {
                    this.sum_ = doubleValue;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(doubleValue);
            }
            return this;
        }

        public Builder clearSum() {
            if (this.sumBuilder_ == null) {
                this.sum_ = null;
                onChanged();
            } else {
                this.sum_ = null;
                this.sumBuilder_ = null;
            }
            return this;
        }

        public DoubleValue.Builder getSumBuilder() {
            onChanged();
            return getSumFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
        public DoubleValueOrBuilder getSumOrBuilder() {
            SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.sumBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            DoubleValue doubleValue = this.sum_;
            return doubleValue == null ? DoubleValue.getDefaultInstance() : doubleValue;
        }

        private SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> getSumFieldBuilder() {
            if (this.sumBuilder_ == null) {
                this.sumBuilder_ = new SingleFieldBuilderV3<>(getSum(), getParentForChildren(), isClean());
                this.sum_ = null;
            }
            return this.sumBuilder_;
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
        public Snapshot getSnapshot() {
            SingleFieldBuilderV3<Snapshot, Snapshot.Builder, SnapshotOrBuilder> singleFieldBuilderV3 = this.snapshotBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Snapshot snapshot = this.snapshot_;
            return snapshot == null ? Snapshot.getDefaultInstance() : snapshot;
        }

        public Builder setSnapshot(Snapshot snapshot) {
            SingleFieldBuilderV3<Snapshot, Snapshot.Builder, SnapshotOrBuilder> singleFieldBuilderV3 = this.snapshotBuilder_;
            if (singleFieldBuilderV3 == null) {
                snapshot.getClass();
                this.snapshot_ = snapshot;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(snapshot);
            }
            return this;
        }

        public Builder setSnapshot(Snapshot.Builder builder) {
            SingleFieldBuilderV3<Snapshot, Snapshot.Builder, SnapshotOrBuilder> singleFieldBuilderV3 = this.snapshotBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.snapshot_ = builder.m37290build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m37290build());
            }
            return this;
        }

        public Builder mergeSnapshot(Snapshot snapshot) {
            SingleFieldBuilderV3<Snapshot, Snapshot.Builder, SnapshotOrBuilder> singleFieldBuilderV3 = this.snapshotBuilder_;
            if (singleFieldBuilderV3 == null) {
                Snapshot snapshot2 = this.snapshot_;
                if (snapshot2 != null) {
                    this.snapshot_ = Snapshot.newBuilder(snapshot2).mergeFrom(snapshot).m37292buildPartial();
                } else {
                    this.snapshot_ = snapshot;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(snapshot);
            }
            return this;
        }

        public Builder clearSnapshot() {
            if (this.snapshotBuilder_ == null) {
                this.snapshot_ = null;
                onChanged();
            } else {
                this.snapshot_ = null;
                this.snapshotBuilder_ = null;
            }
            return this;
        }

        public Snapshot.Builder getSnapshotBuilder() {
            onChanged();
            return getSnapshotFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.metrics.v1.SummaryValueOrBuilder
        public SnapshotOrBuilder getSnapshotOrBuilder() {
            SingleFieldBuilderV3<Snapshot, Snapshot.Builder, SnapshotOrBuilder> singleFieldBuilderV3 = this.snapshotBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (SnapshotOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Snapshot snapshot = this.snapshot_;
            return snapshot == null ? Snapshot.getDefaultInstance() : snapshot;
        }

        private SingleFieldBuilderV3<Snapshot, Snapshot.Builder, SnapshotOrBuilder> getSnapshotFieldBuilder() {
            if (this.snapshotBuilder_ == null) {
                this.snapshotBuilder_ = new SingleFieldBuilderV3<>(getSnapshot(), getParentForChildren(), isClean());
                this.snapshot_ = null;
            }
            return this.snapshotBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37278setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37272mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
