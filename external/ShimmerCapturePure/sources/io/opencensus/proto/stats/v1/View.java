package io.opencensus.proto.stats.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.opencensus.proto.stats.v1.CountAggregation;
import io.opencensus.proto.stats.v1.DistributionAggregation;
import io.opencensus.proto.stats.v1.LastValueAggregation;
import io.opencensus.proto.stats.v1.Measure;
import io.opencensus.proto.stats.v1.SumAggregation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class View extends GeneratedMessageV3 implements ViewOrBuilder {
    public static final int COLUMNS_FIELD_NUMBER = 4;
    public static final int COUNT_AGGREGATION_FIELD_NUMBER = 5;
    public static final int DESCRIPTION_FIELD_NUMBER = 2;
    public static final int DISTRIBUTION_AGGREGATION_FIELD_NUMBER = 8;
    public static final int LAST_VALUE_AGGREGATION_FIELD_NUMBER = 7;
    public static final int MEASURE_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int SUM_AGGREGATION_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    private static final View DEFAULT_INSTANCE = new View();
    private static final Parser<View> PARSER = new AbstractParser<View>() { // from class: io.opencensus.proto.stats.v1.View.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public View m37795parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new View(codedInputStream, extensionRegistryLite);
        }
    };
    private int aggregationCase_;
    private Object aggregation_;
    private int bitField0_;
    private LazyStringList columns_;
    private volatile Object description_;
    private Measure measure_;
    private byte memoizedIsInitialized;
    private volatile Object name_;

    private View(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.aggregationCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private View() {
        this.aggregationCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.description_ = "";
        this.columns_ = LazyStringArrayList.EMPTY;
    }

    private View(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag != 18) {
                            if (tag == 26) {
                                Measure measure = this.measure_;
                                Measure.Builder builderM37607toBuilder = measure != null ? measure.m37607toBuilder() : null;
                                Measure measure2 = (Measure) codedInputStream.readMessage(Measure.parser(), extensionRegistryLite);
                                this.measure_ = measure2;
                                if (builderM37607toBuilder != null) {
                                    builderM37607toBuilder.mergeFrom(measure2);
                                    this.measure_ = builderM37607toBuilder.m37614buildPartial();
                                }
                            } else if (tag == 34) {
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if ((i & 8) != 8) {
                                    this.columns_ = new LazyStringArrayList();
                                    i |= 8;
                                }
                                this.columns_.add(stringRequireUtf8);
                            } else if (tag == 42) {
                                CountAggregation.Builder builderM37469toBuilder = this.aggregationCase_ == 5 ? ((CountAggregation) this.aggregation_).m37469toBuilder() : null;
                                MessageLite message = codedInputStream.readMessage(CountAggregation.parser(), extensionRegistryLite);
                                this.aggregation_ = message;
                                if (builderM37469toBuilder != null) {
                                    builderM37469toBuilder.mergeFrom((CountAggregation) message);
                                    this.aggregation_ = builderM37469toBuilder.m37476buildPartial();
                                }
                                this.aggregationCase_ = 5;
                            } else if (tag == 50) {
                                SumAggregation.Builder builderM37700toBuilder = this.aggregationCase_ == 6 ? ((SumAggregation) this.aggregation_).m37700toBuilder() : null;
                                MessageLite message2 = codedInputStream.readMessage(SumAggregation.parser(), extensionRegistryLite);
                                this.aggregation_ = message2;
                                if (builderM37700toBuilder != null) {
                                    builderM37700toBuilder.mergeFrom((SumAggregation) message2);
                                    this.aggregation_ = builderM37700toBuilder.m37707buildPartial();
                                }
                                this.aggregationCase_ = 6;
                            } else if (tag == 58) {
                                LastValueAggregation.Builder builderM37561toBuilder = this.aggregationCase_ == 7 ? ((LastValueAggregation) this.aggregation_).m37561toBuilder() : null;
                                MessageLite message3 = codedInputStream.readMessage(LastValueAggregation.parser(), extensionRegistryLite);
                                this.aggregation_ = message3;
                                if (builderM37561toBuilder != null) {
                                    builderM37561toBuilder.mergeFrom((LastValueAggregation) message3);
                                    this.aggregation_ = builderM37561toBuilder.m37568buildPartial();
                                }
                                this.aggregationCase_ = 7;
                            } else if (tag != 66) {
                                if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                DistributionAggregation.Builder builderM37515toBuilder = this.aggregationCase_ == 8 ? ((DistributionAggregation) this.aggregation_).m37515toBuilder() : null;
                                MessageLite message4 = codedInputStream.readMessage(DistributionAggregation.parser(), extensionRegistryLite);
                                this.aggregation_ = message4;
                                if (builderM37515toBuilder != null) {
                                    builderM37515toBuilder.mergeFrom((DistributionAggregation) message4);
                                    this.aggregation_ = builderM37515toBuilder.m37522buildPartial();
                                }
                                this.aggregationCase_ = 8;
                            }
                        } else {
                            this.description_ = codedInputStream.readStringRequireUtf8();
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if ((i & 8) == 8) {
                    this.columns_ = this.columns_.getUnmodifiableView();
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static View getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<View> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return StatsProto.internal_static_opencensus_proto_stats_v1_View_descriptor;
    }

    public static View parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (View) PARSER.parseFrom(byteBuffer);
    }

    public static View parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (View) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static View parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (View) PARSER.parseFrom(byteString);
    }

    public static View parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (View) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static View parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (View) PARSER.parseFrom(bArr);
    }

    public static View parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (View) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static View parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static View parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static View parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static View parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static View parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static View parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m37793toBuilder();
    }

    public static Builder newBuilder(View view) {
        return DEFAULT_INSTANCE.m37793toBuilder().mergeFrom(view);
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    /* renamed from: getColumnsList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo37787getColumnsList() {
        return this.columns_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public View m37788getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<View> getParserForType() {
        return PARSER;
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public boolean hasCountAggregation() {
        return this.aggregationCase_ == 5;
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public boolean hasDistributionAggregation() {
        return this.aggregationCase_ == 8;
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public boolean hasLastValueAggregation() {
        return this.aggregationCase_ == 7;
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public boolean hasMeasure() {
        return this.measure_ != null;
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public boolean hasSumAggregation() {
        return this.aggregationCase_ == 6;
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
        return StatsProto.internal_static_opencensus_proto_stats_v1_View_fieldAccessorTable.ensureFieldAccessorsInitialized(View.class, Builder.class);
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public AggregationCase getAggregationCase() {
        return AggregationCase.forNumber(this.aggregationCase_);
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public String getDescription() {
        Object obj = this.description_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.description_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public ByteString getDescriptionBytes() {
        Object obj = this.description_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public Measure getMeasure() {
        Measure measure = this.measure_;
        return measure == null ? Measure.getDefaultInstance() : measure;
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public MeasureOrBuilder getMeasureOrBuilder() {
        return getMeasure();
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public int getColumnsCount() {
        return this.columns_.size();
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public String getColumns(int i) {
        return (String) this.columns_.get(i);
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public ByteString getColumnsBytes(int i) {
        return this.columns_.getByteString(i);
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public CountAggregation getCountAggregation() {
        if (this.aggregationCase_ == 5) {
            return (CountAggregation) this.aggregation_;
        }
        return CountAggregation.getDefaultInstance();
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public CountAggregationOrBuilder getCountAggregationOrBuilder() {
        if (this.aggregationCase_ == 5) {
            return (CountAggregation) this.aggregation_;
        }
        return CountAggregation.getDefaultInstance();
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public SumAggregation getSumAggregation() {
        if (this.aggregationCase_ == 6) {
            return (SumAggregation) this.aggregation_;
        }
        return SumAggregation.getDefaultInstance();
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public SumAggregationOrBuilder getSumAggregationOrBuilder() {
        if (this.aggregationCase_ == 6) {
            return (SumAggregation) this.aggregation_;
        }
        return SumAggregation.getDefaultInstance();
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public LastValueAggregation getLastValueAggregation() {
        if (this.aggregationCase_ == 7) {
            return (LastValueAggregation) this.aggregation_;
        }
        return LastValueAggregation.getDefaultInstance();
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public LastValueAggregationOrBuilder getLastValueAggregationOrBuilder() {
        if (this.aggregationCase_ == 7) {
            return (LastValueAggregation) this.aggregation_;
        }
        return LastValueAggregation.getDefaultInstance();
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public DistributionAggregation getDistributionAggregation() {
        if (this.aggregationCase_ == 8) {
            return (DistributionAggregation) this.aggregation_;
        }
        return DistributionAggregation.getDefaultInstance();
    }

    @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
    public DistributionAggregationOrBuilder getDistributionAggregationOrBuilder() {
        if (this.aggregationCase_ == 8) {
            return (DistributionAggregation) this.aggregation_;
        }
        return DistributionAggregation.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.description_);
        }
        if (this.measure_ != null) {
            codedOutputStream.writeMessage(3, getMeasure());
        }
        for (int i = 0; i < this.columns_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.columns_.getRaw(i));
        }
        if (this.aggregationCase_ == 5) {
            codedOutputStream.writeMessage(5, (CountAggregation) this.aggregation_);
        }
        if (this.aggregationCase_ == 6) {
            codedOutputStream.writeMessage(6, (SumAggregation) this.aggregation_);
        }
        if (this.aggregationCase_ == 7) {
            codedOutputStream.writeMessage(7, (LastValueAggregation) this.aggregation_);
        }
        if (this.aggregationCase_ == 8) {
            codedOutputStream.writeMessage(8, (DistributionAggregation) this.aggregation_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (!getDescriptionBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.description_);
        }
        if (this.measure_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, getMeasure());
        }
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.columns_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.columns_.getRaw(i2));
        }
        int size = iComputeStringSize + iComputeStringSizeNoTag + mo37787getColumnsList().size();
        if (this.aggregationCase_ == 5) {
            size += CodedOutputStream.computeMessageSize(5, (CountAggregation) this.aggregation_);
        }
        if (this.aggregationCase_ == 6) {
            size += CodedOutputStream.computeMessageSize(6, (SumAggregation) this.aggregation_);
        }
        if (this.aggregationCase_ == 7) {
            size += CodedOutputStream.computeMessageSize(7, (LastValueAggregation) this.aggregation_);
        }
        if (this.aggregationCase_ == 8) {
            size += CodedOutputStream.computeMessageSize(8, (DistributionAggregation) this.aggregation_);
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L4
            return r0
        L4:
            boolean r1 = r6 instanceof io.opencensus.proto.stats.v1.View
            if (r1 != 0) goto Ld
            boolean r6 = super.equals(r6)
            return r6
        Ld:
            io.opencensus.proto.stats.v1.View r6 = (io.opencensus.proto.stats.v1.View) r6
            java.lang.String r1 = r5.getName()
            java.lang.String r2 = r6.getName()
            boolean r1 = r1.equals(r2)
            r2 = 0
            if (r1 == 0) goto L38
            java.lang.String r1 = r5.getDescription()
            java.lang.String r3 = r6.getDescription()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L38
            boolean r1 = r5.hasMeasure()
            boolean r3 = r6.hasMeasure()
            if (r1 != r3) goto L38
            r1 = 1
            goto L39
        L38:
            r1 = 0
        L39:
            boolean r3 = r5.hasMeasure()
            if (r3 == 0) goto L50
            if (r1 == 0) goto L70
            io.opencensus.proto.stats.v1.Measure r1 = r5.getMeasure()
            io.opencensus.proto.stats.v1.Measure r3 = r6.getMeasure()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L70
            goto L52
        L50:
            if (r1 == 0) goto L70
        L52:
            com.google.protobuf.ProtocolStringList r1 = r5.mo37787getColumnsList()
            com.google.protobuf.ProtocolStringList r3 = r6.mo37787getColumnsList()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L70
            io.opencensus.proto.stats.v1.View$AggregationCase r1 = r5.getAggregationCase()
            io.opencensus.proto.stats.v1.View$AggregationCase r3 = r6.getAggregationCase()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L70
            r1 = 1
            goto L71
        L70:
            r1 = 0
        L71:
            if (r1 != 0) goto L74
            return r2
        L74:
            int r3 = r5.aggregationCase_
            r4 = 5
            if (r3 == r4) goto Lb9
            r4 = 6
            if (r3 == r4) goto La8
            r4 = 7
            if (r3 == r4) goto L97
            r4 = 8
            if (r3 == r4) goto L86
            if (r1 == 0) goto Ld4
            goto Lc9
        L86:
            if (r1 == 0) goto Ld4
            io.opencensus.proto.stats.v1.DistributionAggregation r1 = r5.getDistributionAggregation()
            io.opencensus.proto.stats.v1.DistributionAggregation r3 = r6.getDistributionAggregation()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto Ld4
            goto Lc9
        L97:
            if (r1 == 0) goto Ld4
            io.opencensus.proto.stats.v1.LastValueAggregation r1 = r5.getLastValueAggregation()
            io.opencensus.proto.stats.v1.LastValueAggregation r3 = r6.getLastValueAggregation()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto Ld4
            goto Lc9
        La8:
            if (r1 == 0) goto Ld4
            io.opencensus.proto.stats.v1.SumAggregation r1 = r5.getSumAggregation()
            io.opencensus.proto.stats.v1.SumAggregation r3 = r6.getSumAggregation()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto Ld4
            goto Lc9
        Lb9:
            if (r1 == 0) goto Ld4
            io.opencensus.proto.stats.v1.CountAggregation r1 = r5.getCountAggregation()
            io.opencensus.proto.stats.v1.CountAggregation r3 = r6.getCountAggregation()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto Ld4
        Lc9:
            com.google.protobuf.UnknownFieldSet r1 = r5.unknownFields
            com.google.protobuf.UnknownFieldSet r6 = r6.unknownFields
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto Ld4
            goto Ld5
        Ld4:
            r0 = 0
        Ld5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.stats.v1.View.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getDescription().hashCode();
        if (hasMeasure()) {
            iHashCode2 = (((iHashCode2 * 37) + 3) * 53) + getMeasure().hashCode();
        }
        if (getColumnsCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 4) * 53) + mo37787getColumnsList().hashCode();
        }
        int i2 = this.aggregationCase_;
        if (i2 == 5) {
            i = ((iHashCode2 * 37) + 5) * 53;
            iHashCode = getCountAggregation().hashCode();
        } else if (i2 == 6) {
            i = ((iHashCode2 * 37) + 6) * 53;
            iHashCode = getSumAggregation().hashCode();
        } else if (i2 == 7) {
            i = ((iHashCode2 * 37) + 7) * 53;
            iHashCode = getLastValueAggregation().hashCode();
        } else {
            if (i2 == 8) {
                i = ((iHashCode2 * 37) + 8) * 53;
                iHashCode = getDistributionAggregation().hashCode();
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
    public Builder m37790newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37793toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum AggregationCase implements Internal.EnumLite {
        COUNT_AGGREGATION(5),
        SUM_AGGREGATION(6),
        LAST_VALUE_AGGREGATION(7),
        DISTRIBUTION_AGGREGATION(8),
        AGGREGATION_NOT_SET(0);

        private final int value;

        AggregationCase(int i) {
            this.value = i;
        }

        public static AggregationCase forNumber(int i) {
            if (i == 0) {
                return AGGREGATION_NOT_SET;
            }
            if (i == 5) {
                return COUNT_AGGREGATION;
            }
            if (i == 6) {
                return SUM_AGGREGATION;
            }
            if (i == 7) {
                return LAST_VALUE_AGGREGATION;
            }
            if (i != 8) {
                return null;
            }
            return DISTRIBUTION_AGGREGATION;
        }

        @Deprecated
        public static AggregationCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ViewOrBuilder {
        private int aggregationCase_;
        private Object aggregation_;
        private int bitField0_;
        private LazyStringList columns_;
        private SingleFieldBuilderV3<CountAggregation, CountAggregation.Builder, CountAggregationOrBuilder> countAggregationBuilder_;
        private Object description_;
        private SingleFieldBuilderV3<DistributionAggregation, DistributionAggregation.Builder, DistributionAggregationOrBuilder> distributionAggregationBuilder_;
        private SingleFieldBuilderV3<LastValueAggregation, LastValueAggregation.Builder, LastValueAggregationOrBuilder> lastValueAggregationBuilder_;
        private SingleFieldBuilderV3<Measure, Measure.Builder, MeasureOrBuilder> measureBuilder_;
        private Measure measure_;
        private Object name_;
        private SingleFieldBuilderV3<SumAggregation, SumAggregation.Builder, SumAggregationOrBuilder> sumAggregationBuilder_;

        private Builder() {
            this.aggregationCase_ = 0;
            this.name_ = "";
            this.description_ = "";
            this.measure_ = null;
            this.columns_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.aggregationCase_ = 0;
            this.name_ = "";
            this.description_ = "";
            this.measure_ = null;
            this.columns_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StatsProto.internal_static_opencensus_proto_stats_v1_View_descriptor;
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public boolean hasCountAggregation() {
            return this.aggregationCase_ == 5;
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public boolean hasDistributionAggregation() {
            return this.aggregationCase_ == 8;
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public boolean hasLastValueAggregation() {
            return this.aggregationCase_ == 7;
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public boolean hasMeasure() {
            return (this.measureBuilder_ == null && this.measure_ == null) ? false : true;
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public boolean hasSumAggregation() {
            return this.aggregationCase_ == 6;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StatsProto.internal_static_opencensus_proto_stats_v1_View_fieldAccessorTable.ensureFieldAccessorsInitialized(View.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = View.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37804clear() {
            super.clear();
            this.name_ = "";
            this.description_ = "";
            if (this.measureBuilder_ == null) {
                this.measure_ = null;
            } else {
                this.measure_ = null;
                this.measureBuilder_ = null;
            }
            this.columns_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -9;
            this.aggregationCase_ = 0;
            this.aggregation_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return StatsProto.internal_static_opencensus_proto_stats_v1_View_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public View m37817getDefaultInstanceForType() {
            return View.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public View m37798build() throws UninitializedMessageException {
            View viewM37800buildPartial = m37800buildPartial();
            if (viewM37800buildPartial.isInitialized()) {
                return viewM37800buildPartial;
            }
            throw newUninitializedMessageException(viewM37800buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public View m37800buildPartial() {
            View view = new View(this);
            view.name_ = this.name_;
            view.description_ = this.description_;
            SingleFieldBuilderV3<Measure, Measure.Builder, MeasureOrBuilder> singleFieldBuilderV3 = this.measureBuilder_;
            if (singleFieldBuilderV3 == null) {
                view.measure_ = this.measure_;
            } else {
                view.measure_ = singleFieldBuilderV3.build();
            }
            if ((this.bitField0_ & 8) == 8) {
                this.columns_ = this.columns_.getUnmodifiableView();
                this.bitField0_ &= -9;
            }
            view.columns_ = this.columns_;
            if (this.aggregationCase_ == 5) {
                SingleFieldBuilderV3<CountAggregation, CountAggregation.Builder, CountAggregationOrBuilder> singleFieldBuilderV32 = this.countAggregationBuilder_;
                if (singleFieldBuilderV32 == null) {
                    view.aggregation_ = this.aggregation_;
                } else {
                    view.aggregation_ = singleFieldBuilderV32.build();
                }
            }
            if (this.aggregationCase_ == 6) {
                SingleFieldBuilderV3<SumAggregation, SumAggregation.Builder, SumAggregationOrBuilder> singleFieldBuilderV33 = this.sumAggregationBuilder_;
                if (singleFieldBuilderV33 == null) {
                    view.aggregation_ = this.aggregation_;
                } else {
                    view.aggregation_ = singleFieldBuilderV33.build();
                }
            }
            if (this.aggregationCase_ == 7) {
                SingleFieldBuilderV3<LastValueAggregation, LastValueAggregation.Builder, LastValueAggregationOrBuilder> singleFieldBuilderV34 = this.lastValueAggregationBuilder_;
                if (singleFieldBuilderV34 == null) {
                    view.aggregation_ = this.aggregation_;
                } else {
                    view.aggregation_ = singleFieldBuilderV34.build();
                }
            }
            if (this.aggregationCase_ == 8) {
                SingleFieldBuilderV3<DistributionAggregation, DistributionAggregation.Builder, DistributionAggregationOrBuilder> singleFieldBuilderV35 = this.distributionAggregationBuilder_;
                if (singleFieldBuilderV35 == null) {
                    view.aggregation_ = this.aggregation_;
                } else {
                    view.aggregation_ = singleFieldBuilderV35.build();
                }
            }
            view.bitField0_ = 0;
            view.aggregationCase_ = this.aggregationCase_;
            onBuilt();
            return view;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37816clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37828setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37806clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37809clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37830setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37796addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37821mergeFrom(Message message) {
            if (message instanceof View) {
                return mergeFrom((View) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(View view) {
            if (view == View.getDefaultInstance()) {
                return this;
            }
            if (!view.getName().isEmpty()) {
                this.name_ = view.name_;
                onChanged();
            }
            if (!view.getDescription().isEmpty()) {
                this.description_ = view.description_;
                onChanged();
            }
            if (view.hasMeasure()) {
                mergeMeasure(view.getMeasure());
            }
            if (!view.columns_.isEmpty()) {
                if (this.columns_.isEmpty()) {
                    this.columns_ = view.columns_;
                    this.bitField0_ &= -9;
                } else {
                    ensureColumnsIsMutable();
                    this.columns_.addAll(view.columns_);
                }
                onChanged();
            }
            int i = AnonymousClass2.$SwitchMap$io$opencensus$proto$stats$v1$View$AggregationCase[view.getAggregationCase().ordinal()];
            if (i == 1) {
                mergeCountAggregation(view.getCountAggregation());
            } else if (i == 2) {
                mergeSumAggregation(view.getSumAggregation());
            } else if (i == 3) {
                mergeLastValueAggregation(view.getLastValueAggregation());
            } else if (i == 4) {
                mergeDistributionAggregation(view.getDistributionAggregation());
            }
            m37826mergeUnknownFields(view.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.stats.v1.View.Builder m37822mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.stats.v1.View.access$1200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.stats.v1.View r3 = (io.opencensus.proto.stats.v1.View) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.stats.v1.View r4 = (io.opencensus.proto.stats.v1.View) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.stats.v1.View.Builder.m37822mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.stats.v1.View$Builder");
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public AggregationCase getAggregationCase() {
            return AggregationCase.forNumber(this.aggregationCase_);
        }

        public Builder clearAggregation() {
            this.aggregationCase_ = 0;
            this.aggregation_ = null;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setName(String str) {
            str.getClass();
            this.name_ = str;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNameBytes(ByteString byteString) {
            byteString.getClass();
            View.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = View.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public String getDescription() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.description_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setDescription(String str) {
            str.getClass();
            this.description_ = str;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.description_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            byteString.getClass();
            View.checkByteStringIsUtf8(byteString);
            this.description_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearDescription() {
            this.description_ = View.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public Measure getMeasure() {
            SingleFieldBuilderV3<Measure, Measure.Builder, MeasureOrBuilder> singleFieldBuilderV3 = this.measureBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Measure measure = this.measure_;
            return measure == null ? Measure.getDefaultInstance() : measure;
        }

        public Builder setMeasure(Measure measure) {
            SingleFieldBuilderV3<Measure, Measure.Builder, MeasureOrBuilder> singleFieldBuilderV3 = this.measureBuilder_;
            if (singleFieldBuilderV3 == null) {
                measure.getClass();
                this.measure_ = measure;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(measure);
            }
            return this;
        }

        public Builder setMeasure(Measure.Builder builder) {
            SingleFieldBuilderV3<Measure, Measure.Builder, MeasureOrBuilder> singleFieldBuilderV3 = this.measureBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.measure_ = builder.m37612build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m37612build());
            }
            return this;
        }

        public Builder mergeMeasure(Measure measure) {
            SingleFieldBuilderV3<Measure, Measure.Builder, MeasureOrBuilder> singleFieldBuilderV3 = this.measureBuilder_;
            if (singleFieldBuilderV3 == null) {
                Measure measure2 = this.measure_;
                if (measure2 != null) {
                    this.measure_ = Measure.newBuilder(measure2).mergeFrom(measure).m37614buildPartial();
                } else {
                    this.measure_ = measure;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(measure);
            }
            return this;
        }

        public Builder clearMeasure() {
            if (this.measureBuilder_ == null) {
                this.measure_ = null;
                onChanged();
            } else {
                this.measure_ = null;
                this.measureBuilder_ = null;
            }
            return this;
        }

        public Measure.Builder getMeasureBuilder() {
            onChanged();
            return getMeasureFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public MeasureOrBuilder getMeasureOrBuilder() {
            SingleFieldBuilderV3<Measure, Measure.Builder, MeasureOrBuilder> singleFieldBuilderV3 = this.measureBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (MeasureOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Measure measure = this.measure_;
            return measure == null ? Measure.getDefaultInstance() : measure;
        }

        private SingleFieldBuilderV3<Measure, Measure.Builder, MeasureOrBuilder> getMeasureFieldBuilder() {
            if (this.measureBuilder_ == null) {
                this.measureBuilder_ = new SingleFieldBuilderV3<>(getMeasure(), getParentForChildren(), isClean());
                this.measure_ = null;
            }
            return this.measureBuilder_;
        }

        private void ensureColumnsIsMutable() {
            if ((this.bitField0_ & 8) != 8) {
                this.columns_ = new LazyStringArrayList(this.columns_);
                this.bitField0_ |= 8;
            }
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        /* renamed from: getColumnsList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo37787getColumnsList() {
            return this.columns_.getUnmodifiableView();
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public int getColumnsCount() {
            return this.columns_.size();
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public String getColumns(int i) {
            return (String) this.columns_.get(i);
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public ByteString getColumnsBytes(int i) {
            return this.columns_.getByteString(i);
        }

        public Builder setColumns(int i, String str) {
            str.getClass();
            ensureColumnsIsMutable();
            this.columns_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addColumns(String str) {
            str.getClass();
            ensureColumnsIsMutable();
            this.columns_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllColumns(Iterable<String> iterable) {
            ensureColumnsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.columns_);
            onChanged();
            return this;
        }

        public Builder clearColumns() {
            this.columns_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -9;
            onChanged();
            return this;
        }

        public Builder addColumnsBytes(ByteString byteString) {
            byteString.getClass();
            View.checkByteStringIsUtf8(byteString);
            ensureColumnsIsMutable();
            this.columns_.add(byteString);
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public CountAggregation getCountAggregation() {
            SingleFieldBuilderV3<CountAggregation, CountAggregation.Builder, CountAggregationOrBuilder> singleFieldBuilderV3 = this.countAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.aggregationCase_ == 5) {
                    return (CountAggregation) this.aggregation_;
                }
                return CountAggregation.getDefaultInstance();
            }
            if (this.aggregationCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return CountAggregation.getDefaultInstance();
        }

        public Builder setCountAggregation(CountAggregation countAggregation) {
            SingleFieldBuilderV3<CountAggregation, CountAggregation.Builder, CountAggregationOrBuilder> singleFieldBuilderV3 = this.countAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                countAggregation.getClass();
                this.aggregation_ = countAggregation;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(countAggregation);
            }
            this.aggregationCase_ = 5;
            return this;
        }

        public Builder setCountAggregation(CountAggregation.Builder builder) {
            SingleFieldBuilderV3<CountAggregation, CountAggregation.Builder, CountAggregationOrBuilder> singleFieldBuilderV3 = this.countAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.aggregation_ = builder.m37474build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m37474build());
            }
            this.aggregationCase_ = 5;
            return this;
        }

        public Builder mergeCountAggregation(CountAggregation countAggregation) {
            SingleFieldBuilderV3<CountAggregation, CountAggregation.Builder, CountAggregationOrBuilder> singleFieldBuilderV3 = this.countAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.aggregationCase_ != 5 || this.aggregation_ == CountAggregation.getDefaultInstance()) {
                    this.aggregation_ = countAggregation;
                } else {
                    this.aggregation_ = CountAggregation.newBuilder((CountAggregation) this.aggregation_).mergeFrom(countAggregation).m37476buildPartial();
                }
                onChanged();
            } else {
                if (this.aggregationCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(countAggregation);
                }
                this.countAggregationBuilder_.setMessage(countAggregation);
            }
            this.aggregationCase_ = 5;
            return this;
        }

        public Builder clearCountAggregation() {
            SingleFieldBuilderV3<CountAggregation, CountAggregation.Builder, CountAggregationOrBuilder> singleFieldBuilderV3 = this.countAggregationBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.aggregationCase_ == 5) {
                    this.aggregationCase_ = 0;
                    this.aggregation_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.aggregationCase_ == 5) {
                this.aggregationCase_ = 0;
                this.aggregation_ = null;
                onChanged();
            }
            return this;
        }

        public CountAggregation.Builder getCountAggregationBuilder() {
            return getCountAggregationFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public CountAggregationOrBuilder getCountAggregationOrBuilder() {
            SingleFieldBuilderV3<CountAggregation, CountAggregation.Builder, CountAggregationOrBuilder> singleFieldBuilderV3;
            int i = this.aggregationCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.countAggregationBuilder_) != null) {
                return (CountAggregationOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (CountAggregation) this.aggregation_;
            }
            return CountAggregation.getDefaultInstance();
        }

        private SingleFieldBuilderV3<CountAggregation, CountAggregation.Builder, CountAggregationOrBuilder> getCountAggregationFieldBuilder() {
            if (this.countAggregationBuilder_ == null) {
                if (this.aggregationCase_ != 5) {
                    this.aggregation_ = CountAggregation.getDefaultInstance();
                }
                this.countAggregationBuilder_ = new SingleFieldBuilderV3<>((CountAggregation) this.aggregation_, getParentForChildren(), isClean());
                this.aggregation_ = null;
            }
            this.aggregationCase_ = 5;
            onChanged();
            return this.countAggregationBuilder_;
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public SumAggregation getSumAggregation() {
            SingleFieldBuilderV3<SumAggregation, SumAggregation.Builder, SumAggregationOrBuilder> singleFieldBuilderV3 = this.sumAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.aggregationCase_ == 6) {
                    return (SumAggregation) this.aggregation_;
                }
                return SumAggregation.getDefaultInstance();
            }
            if (this.aggregationCase_ == 6) {
                return singleFieldBuilderV3.getMessage();
            }
            return SumAggregation.getDefaultInstance();
        }

        public Builder setSumAggregation(SumAggregation sumAggregation) {
            SingleFieldBuilderV3<SumAggregation, SumAggregation.Builder, SumAggregationOrBuilder> singleFieldBuilderV3 = this.sumAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                sumAggregation.getClass();
                this.aggregation_ = sumAggregation;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(sumAggregation);
            }
            this.aggregationCase_ = 6;
            return this;
        }

        public Builder setSumAggregation(SumAggregation.Builder builder) {
            SingleFieldBuilderV3<SumAggregation, SumAggregation.Builder, SumAggregationOrBuilder> singleFieldBuilderV3 = this.sumAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.aggregation_ = builder.m37705build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m37705build());
            }
            this.aggregationCase_ = 6;
            return this;
        }

        public Builder mergeSumAggregation(SumAggregation sumAggregation) {
            SingleFieldBuilderV3<SumAggregation, SumAggregation.Builder, SumAggregationOrBuilder> singleFieldBuilderV3 = this.sumAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.aggregationCase_ != 6 || this.aggregation_ == SumAggregation.getDefaultInstance()) {
                    this.aggregation_ = sumAggregation;
                } else {
                    this.aggregation_ = SumAggregation.newBuilder((SumAggregation) this.aggregation_).mergeFrom(sumAggregation).m37707buildPartial();
                }
                onChanged();
            } else {
                if (this.aggregationCase_ == 6) {
                    singleFieldBuilderV3.mergeFrom(sumAggregation);
                }
                this.sumAggregationBuilder_.setMessage(sumAggregation);
            }
            this.aggregationCase_ = 6;
            return this;
        }

        public Builder clearSumAggregation() {
            SingleFieldBuilderV3<SumAggregation, SumAggregation.Builder, SumAggregationOrBuilder> singleFieldBuilderV3 = this.sumAggregationBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.aggregationCase_ == 6) {
                    this.aggregationCase_ = 0;
                    this.aggregation_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.aggregationCase_ == 6) {
                this.aggregationCase_ = 0;
                this.aggregation_ = null;
                onChanged();
            }
            return this;
        }

        public SumAggregation.Builder getSumAggregationBuilder() {
            return getSumAggregationFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public SumAggregationOrBuilder getSumAggregationOrBuilder() {
            SingleFieldBuilderV3<SumAggregation, SumAggregation.Builder, SumAggregationOrBuilder> singleFieldBuilderV3;
            int i = this.aggregationCase_;
            if (i == 6 && (singleFieldBuilderV3 = this.sumAggregationBuilder_) != null) {
                return (SumAggregationOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 6) {
                return (SumAggregation) this.aggregation_;
            }
            return SumAggregation.getDefaultInstance();
        }

        private SingleFieldBuilderV3<SumAggregation, SumAggregation.Builder, SumAggregationOrBuilder> getSumAggregationFieldBuilder() {
            if (this.sumAggregationBuilder_ == null) {
                if (this.aggregationCase_ != 6) {
                    this.aggregation_ = SumAggregation.getDefaultInstance();
                }
                this.sumAggregationBuilder_ = new SingleFieldBuilderV3<>((SumAggregation) this.aggregation_, getParentForChildren(), isClean());
                this.aggregation_ = null;
            }
            this.aggregationCase_ = 6;
            onChanged();
            return this.sumAggregationBuilder_;
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public LastValueAggregation getLastValueAggregation() {
            SingleFieldBuilderV3<LastValueAggregation, LastValueAggregation.Builder, LastValueAggregationOrBuilder> singleFieldBuilderV3 = this.lastValueAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.aggregationCase_ == 7) {
                    return (LastValueAggregation) this.aggregation_;
                }
                return LastValueAggregation.getDefaultInstance();
            }
            if (this.aggregationCase_ == 7) {
                return singleFieldBuilderV3.getMessage();
            }
            return LastValueAggregation.getDefaultInstance();
        }

        public Builder setLastValueAggregation(LastValueAggregation lastValueAggregation) {
            SingleFieldBuilderV3<LastValueAggregation, LastValueAggregation.Builder, LastValueAggregationOrBuilder> singleFieldBuilderV3 = this.lastValueAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                lastValueAggregation.getClass();
                this.aggregation_ = lastValueAggregation;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(lastValueAggregation);
            }
            this.aggregationCase_ = 7;
            return this;
        }

        public Builder setLastValueAggregation(LastValueAggregation.Builder builder) {
            SingleFieldBuilderV3<LastValueAggregation, LastValueAggregation.Builder, LastValueAggregationOrBuilder> singleFieldBuilderV3 = this.lastValueAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.aggregation_ = builder.m37566build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m37566build());
            }
            this.aggregationCase_ = 7;
            return this;
        }

        public Builder mergeLastValueAggregation(LastValueAggregation lastValueAggregation) {
            SingleFieldBuilderV3<LastValueAggregation, LastValueAggregation.Builder, LastValueAggregationOrBuilder> singleFieldBuilderV3 = this.lastValueAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.aggregationCase_ != 7 || this.aggregation_ == LastValueAggregation.getDefaultInstance()) {
                    this.aggregation_ = lastValueAggregation;
                } else {
                    this.aggregation_ = LastValueAggregation.newBuilder((LastValueAggregation) this.aggregation_).mergeFrom(lastValueAggregation).m37568buildPartial();
                }
                onChanged();
            } else {
                if (this.aggregationCase_ == 7) {
                    singleFieldBuilderV3.mergeFrom(lastValueAggregation);
                }
                this.lastValueAggregationBuilder_.setMessage(lastValueAggregation);
            }
            this.aggregationCase_ = 7;
            return this;
        }

        public Builder clearLastValueAggregation() {
            SingleFieldBuilderV3<LastValueAggregation, LastValueAggregation.Builder, LastValueAggregationOrBuilder> singleFieldBuilderV3 = this.lastValueAggregationBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.aggregationCase_ == 7) {
                    this.aggregationCase_ = 0;
                    this.aggregation_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.aggregationCase_ == 7) {
                this.aggregationCase_ = 0;
                this.aggregation_ = null;
                onChanged();
            }
            return this;
        }

        public LastValueAggregation.Builder getLastValueAggregationBuilder() {
            return getLastValueAggregationFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public LastValueAggregationOrBuilder getLastValueAggregationOrBuilder() {
            SingleFieldBuilderV3<LastValueAggregation, LastValueAggregation.Builder, LastValueAggregationOrBuilder> singleFieldBuilderV3;
            int i = this.aggregationCase_;
            if (i == 7 && (singleFieldBuilderV3 = this.lastValueAggregationBuilder_) != null) {
                return (LastValueAggregationOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 7) {
                return (LastValueAggregation) this.aggregation_;
            }
            return LastValueAggregation.getDefaultInstance();
        }

        private SingleFieldBuilderV3<LastValueAggregation, LastValueAggregation.Builder, LastValueAggregationOrBuilder> getLastValueAggregationFieldBuilder() {
            if (this.lastValueAggregationBuilder_ == null) {
                if (this.aggregationCase_ != 7) {
                    this.aggregation_ = LastValueAggregation.getDefaultInstance();
                }
                this.lastValueAggregationBuilder_ = new SingleFieldBuilderV3<>((LastValueAggregation) this.aggregation_, getParentForChildren(), isClean());
                this.aggregation_ = null;
            }
            this.aggregationCase_ = 7;
            onChanged();
            return this.lastValueAggregationBuilder_;
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public DistributionAggregation getDistributionAggregation() {
            SingleFieldBuilderV3<DistributionAggregation, DistributionAggregation.Builder, DistributionAggregationOrBuilder> singleFieldBuilderV3 = this.distributionAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.aggregationCase_ == 8) {
                    return (DistributionAggregation) this.aggregation_;
                }
                return DistributionAggregation.getDefaultInstance();
            }
            if (this.aggregationCase_ == 8) {
                return singleFieldBuilderV3.getMessage();
            }
            return DistributionAggregation.getDefaultInstance();
        }

        public Builder setDistributionAggregation(DistributionAggregation distributionAggregation) {
            SingleFieldBuilderV3<DistributionAggregation, DistributionAggregation.Builder, DistributionAggregationOrBuilder> singleFieldBuilderV3 = this.distributionAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                distributionAggregation.getClass();
                this.aggregation_ = distributionAggregation;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(distributionAggregation);
            }
            this.aggregationCase_ = 8;
            return this;
        }

        public Builder setDistributionAggregation(DistributionAggregation.Builder builder) {
            SingleFieldBuilderV3<DistributionAggregation, DistributionAggregation.Builder, DistributionAggregationOrBuilder> singleFieldBuilderV3 = this.distributionAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.aggregation_ = builder.m37520build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m37520build());
            }
            this.aggregationCase_ = 8;
            return this;
        }

        public Builder mergeDistributionAggregation(DistributionAggregation distributionAggregation) {
            SingleFieldBuilderV3<DistributionAggregation, DistributionAggregation.Builder, DistributionAggregationOrBuilder> singleFieldBuilderV3 = this.distributionAggregationBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.aggregationCase_ != 8 || this.aggregation_ == DistributionAggregation.getDefaultInstance()) {
                    this.aggregation_ = distributionAggregation;
                } else {
                    this.aggregation_ = DistributionAggregation.newBuilder((DistributionAggregation) this.aggregation_).mergeFrom(distributionAggregation).m37522buildPartial();
                }
                onChanged();
            } else {
                if (this.aggregationCase_ == 8) {
                    singleFieldBuilderV3.mergeFrom(distributionAggregation);
                }
                this.distributionAggregationBuilder_.setMessage(distributionAggregation);
            }
            this.aggregationCase_ = 8;
            return this;
        }

        public Builder clearDistributionAggregation() {
            SingleFieldBuilderV3<DistributionAggregation, DistributionAggregation.Builder, DistributionAggregationOrBuilder> singleFieldBuilderV3 = this.distributionAggregationBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.aggregationCase_ == 8) {
                    this.aggregationCase_ = 0;
                    this.aggregation_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.aggregationCase_ == 8) {
                this.aggregationCase_ = 0;
                this.aggregation_ = null;
                onChanged();
            }
            return this;
        }

        public DistributionAggregation.Builder getDistributionAggregationBuilder() {
            return getDistributionAggregationFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.stats.v1.ViewOrBuilder
        public DistributionAggregationOrBuilder getDistributionAggregationOrBuilder() {
            SingleFieldBuilderV3<DistributionAggregation, DistributionAggregation.Builder, DistributionAggregationOrBuilder> singleFieldBuilderV3;
            int i = this.aggregationCase_;
            if (i == 8 && (singleFieldBuilderV3 = this.distributionAggregationBuilder_) != null) {
                return (DistributionAggregationOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 8) {
                return (DistributionAggregation) this.aggregation_;
            }
            return DistributionAggregation.getDefaultInstance();
        }

        private SingleFieldBuilderV3<DistributionAggregation, DistributionAggregation.Builder, DistributionAggregationOrBuilder> getDistributionAggregationFieldBuilder() {
            if (this.distributionAggregationBuilder_ == null) {
                if (this.aggregationCase_ != 8) {
                    this.aggregation_ = DistributionAggregation.getDefaultInstance();
                }
                this.distributionAggregationBuilder_ = new SingleFieldBuilderV3<>((DistributionAggregation) this.aggregation_, getParentForChildren(), isClean());
                this.aggregation_ = null;
            }
            this.aggregationCase_ = 8;
            onChanged();
            return this.distributionAggregationBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37832setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37826mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.opencensus.proto.stats.v1.View$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$opencensus$proto$stats$v1$View$AggregationCase;

        static {
            int[] iArr = new int[AggregationCase.values().length];
            $SwitchMap$io$opencensus$proto$stats$v1$View$AggregationCase = iArr;
            try {
                iArr[AggregationCase.COUNT_AGGREGATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$opencensus$proto$stats$v1$View$AggregationCase[AggregationCase.SUM_AGGREGATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$opencensus$proto$stats$v1$View$AggregationCase[AggregationCase.LAST_VALUE_AGGREGATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$opencensus$proto$stats$v1$View$AggregationCase[AggregationCase.DISTRIBUTION_AGGREGATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$opencensus$proto$stats$v1$View$AggregationCase[AggregationCase.AGGREGATION_NOT_SET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
