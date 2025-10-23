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
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.opencensus.proto.stats.v1.Tag;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class Measurement extends GeneratedMessageV3 implements MeasurementOrBuilder {
    public static final int DOUBLE_VALUE_FIELD_NUMBER = 3;
    public static final int INT_VALUE_FIELD_NUMBER = 4;
    public static final int MEASURE_NAME_FIELD_NUMBER = 2;
    public static final int TAGS_FIELD_NUMBER = 1;
    public static final int TIME_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private static final Measurement DEFAULT_INSTANCE = new Measurement();
    private static final Parser<Measurement> PARSER = new AbstractParser<Measurement>() { // from class: io.opencensus.proto.stats.v1.Measurement.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Measurement m37656parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Measurement(codedInputStream, extensionRegistryLite);
        }
    };
    private int bitField0_;
    private volatile Object measureName_;
    private byte memoizedIsInitialized;
    private List<Tag> tags_;
    private Timestamp time_;
    private int valueCase_;
    private Object value_;

    private Measurement(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.valueCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Measurement() {
        this.valueCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.tags_ = Collections.emptyList();
        this.measureName_ = "";
    }

    private Measurement(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag == 10) {
                                if (!(z2 & true)) {
                                    this.tags_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.tags_.add(codedInputStream.readMessage(Tag.parser(), extensionRegistryLite));
                            } else if (tag == 18) {
                                this.measureName_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 25) {
                                this.valueCase_ = 3;
                                this.value_ = Double.valueOf(codedInputStream.readDouble());
                            } else if (tag == 32) {
                                this.valueCase_ = 4;
                                this.value_ = Long.valueOf(codedInputStream.readInt64());
                            } else if (tag != 42) {
                                if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                Timestamp timestamp = this.time_;
                                Timestamp.Builder builder = timestamp != null ? timestamp.toBuilder() : null;
                                Timestamp message = codedInputStream.readMessage(Timestamp.parser(), extensionRegistryLite);
                                this.time_ = message;
                                if (builder != null) {
                                    builder.mergeFrom(message);
                                    this.time_ = builder.buildPartial();
                                }
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
                    this.tags_ = Collections.unmodifiableList(this.tags_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static Measurement getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Measurement> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return StatsProto.internal_static_opencensus_proto_stats_v1_Measurement_descriptor;
    }

    public static Measurement parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Measurement) PARSER.parseFrom(byteBuffer);
    }

    public static Measurement parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Measurement) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Measurement parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Measurement) PARSER.parseFrom(byteString);
    }

    public static Measurement parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Measurement) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Measurement parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Measurement) PARSER.parseFrom(bArr);
    }

    public static Measurement parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Measurement) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Measurement parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Measurement parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Measurement parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Measurement parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Measurement parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Measurement parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m37654toBuilder();
    }

    public static Builder newBuilder(Measurement measurement) {
        return DEFAULT_INSTANCE.m37654toBuilder().mergeFrom(measurement);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Measurement m37649getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Measurement> getParserForType() {
        return PARSER;
    }

    @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
    public List<Tag> getTagsList() {
        return this.tags_;
    }

    @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
    public List<? extends TagOrBuilder> getTagsOrBuilderList() {
        return this.tags_;
    }

    @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
    public boolean hasTime() {
        return this.time_ != null;
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
        return StatsProto.internal_static_opencensus_proto_stats_v1_Measurement_fieldAccessorTable.ensureFieldAccessorsInitialized(Measurement.class, Builder.class);
    }

    @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
    public ValueCase getValueCase() {
        return ValueCase.forNumber(this.valueCase_);
    }

    @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
    public int getTagsCount() {
        return this.tags_.size();
    }

    @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
    public Tag getTags(int i) {
        return this.tags_.get(i);
    }

    @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
    public TagOrBuilder getTagsOrBuilder(int i) {
        return this.tags_.get(i);
    }

    @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
    public String getMeasureName() {
        Object obj = this.measureName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.measureName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
    public ByteString getMeasureNameBytes() {
        Object obj = this.measureName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.measureName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
    public double getDoubleValue() {
        if (this.valueCase_ == 3) {
            return ((Double) this.value_).doubleValue();
        }
        return 0.0d;
    }

    @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
    public long getIntValue() {
        if (this.valueCase_ == 4) {
            return ((Long) this.value_).longValue();
        }
        return 0L;
    }

    @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
    public Timestamp getTime() {
        Timestamp timestamp = this.time_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
    public TimestampOrBuilder getTimeOrBuilder() {
        return getTime();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.tags_.size(); i++) {
            codedOutputStream.writeMessage(1, this.tags_.get(i));
        }
        if (!getMeasureNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.measureName_);
        }
        if (this.valueCase_ == 3) {
            codedOutputStream.writeDouble(3, ((Double) this.value_).doubleValue());
        }
        if (this.valueCase_ == 4) {
            codedOutputStream.writeInt64(4, ((Long) this.value_).longValue());
        }
        if (this.time_ != null) {
            codedOutputStream.writeMessage(5, getTime());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.tags_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.tags_.get(i2));
        }
        if (!getMeasureNameBytes().isEmpty()) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(2, this.measureName_);
        }
        if (this.valueCase_ == 3) {
            iComputeMessageSize += CodedOutputStream.computeDoubleSize(3, ((Double) this.value_).doubleValue());
        }
        if (this.valueCase_ == 4) {
            iComputeMessageSize += CodedOutputStream.computeInt64Size(4, ((Long) this.value_).longValue());
        }
        if (this.time_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(5, getTime());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0062  */
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
            boolean r1 = r8 instanceof io.opencensus.proto.stats.v1.Measurement
            if (r1 != 0) goto Ld
            boolean r8 = super.equals(r8)
            return r8
        Ld:
            io.opencensus.proto.stats.v1.Measurement r8 = (io.opencensus.proto.stats.v1.Measurement) r8
            java.util.List r1 = r7.getTagsList()
            java.util.List r2 = r8.getTagsList()
            boolean r1 = r1.equals(r2)
            r2 = 0
            if (r1 == 0) goto L38
            java.lang.String r1 = r7.getMeasureName()
            java.lang.String r3 = r8.getMeasureName()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L38
            boolean r1 = r7.hasTime()
            boolean r3 = r8.hasTime()
            if (r1 != r3) goto L38
            r1 = 1
            goto L39
        L38:
            r1 = 0
        L39:
            boolean r3 = r7.hasTime()
            if (r3 == 0) goto L50
            if (r1 == 0) goto L62
            com.google.protobuf.Timestamp r1 = r7.getTime()
            com.google.protobuf.Timestamp r3 = r8.getTime()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L62
            goto L52
        L50:
            if (r1 == 0) goto L62
        L52:
            io.opencensus.proto.stats.v1.Measurement$ValueCase r1 = r7.getValueCase()
            io.opencensus.proto.stats.v1.Measurement$ValueCase r3 = r8.getValueCase()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L62
            r1 = 1
            goto L63
        L62:
            r1 = 0
        L63:
            if (r1 != 0) goto L66
            return r2
        L66:
            int r3 = r7.valueCase_
            r4 = 3
            if (r3 == r4) goto L80
            r4 = 4
            if (r3 == r4) goto L71
            if (r1 == 0) goto La1
            goto L96
        L71:
            if (r1 == 0) goto La1
            long r3 = r7.getIntValue()
            long r5 = r8.getIntValue()
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto La1
            goto L96
        L80:
            if (r1 == 0) goto La1
            double r3 = r7.getDoubleValue()
            long r3 = java.lang.Double.doubleToLongBits(r3)
            double r5 = r8.getDoubleValue()
            long r5 = java.lang.Double.doubleToLongBits(r5)
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto La1
        L96:
            com.google.protobuf.UnknownFieldSet r1 = r7.unknownFields
            com.google.protobuf.UnknownFieldSet r8 = r8.unknownFields
            boolean r8 = r1.equals(r8)
            if (r8 == 0) goto La1
            goto La2
        La1:
            r0 = 0
        La2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.stats.v1.Measurement.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i;
        int iHashLong;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getTagsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getTagsList().hashCode();
        }
        int iHashCode2 = (((iHashCode * 37) + 2) * 53) + getMeasureName().hashCode();
        if (hasTime()) {
            iHashCode2 = (((iHashCode2 * 37) + 5) * 53) + getTime().hashCode();
        }
        int i2 = this.valueCase_;
        if (i2 == 3) {
            i = ((iHashCode2 * 37) + 3) * 53;
            iHashLong = Internal.hashLong(Double.doubleToLongBits(getDoubleValue()));
        } else {
            if (i2 == 4) {
                i = ((iHashCode2 * 37) + 4) * 53;
                iHashLong = Internal.hashLong(getIntValue());
            }
            int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode3;
            return iHashCode3;
        }
        iHashCode2 = i + iHashLong;
        int iHashCode32 = (iHashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode32;
        return iHashCode32;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37651newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37654toBuilder() {
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
        DOUBLE_VALUE(3),
        INT_VALUE(4),
        VALUE_NOT_SET(0);

        private final int value;

        ValueCase(int i) {
            this.value = i;
        }

        public static ValueCase forNumber(int i) {
            if (i == 0) {
                return VALUE_NOT_SET;
            }
            if (i == 3) {
                return DOUBLE_VALUE;
            }
            if (i != 4) {
                return null;
            }
            return INT_VALUE;
        }

        @Deprecated
        public static ValueCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MeasurementOrBuilder {
        private int bitField0_;
        private Object measureName_;
        private RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> tagsBuilder_;
        private List<Tag> tags_;
        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> timeBuilder_;
        private Timestamp time_;
        private int valueCase_;
        private Object value_;

        private Builder() {
            this.valueCase_ = 0;
            this.tags_ = Collections.emptyList();
            this.measureName_ = "";
            this.time_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.valueCase_ = 0;
            this.tags_ = Collections.emptyList();
            this.measureName_ = "";
            this.time_ = null;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StatsProto.internal_static_opencensus_proto_stats_v1_Measurement_descriptor;
        }

        @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
        public boolean hasTime() {
            return (this.timeBuilder_ == null && this.time_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StatsProto.internal_static_opencensus_proto_stats_v1_Measurement_fieldAccessorTable.ensureFieldAccessorsInitialized(Measurement.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (Measurement.alwaysUseFieldBuilders) {
                getTagsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37665clear() {
            super.clear();
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.tags_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.measureName_ = "";
            if (this.timeBuilder_ == null) {
                this.time_ = null;
            } else {
                this.time_ = null;
                this.timeBuilder_ = null;
            }
            this.valueCase_ = 0;
            this.value_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return StatsProto.internal_static_opencensus_proto_stats_v1_Measurement_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Measurement m37678getDefaultInstanceForType() {
            return Measurement.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Measurement m37659build() throws UninitializedMessageException {
            Measurement measurementM37661buildPartial = m37661buildPartial();
            if (measurementM37661buildPartial.isInitialized()) {
                return measurementM37661buildPartial;
            }
            throw newUninitializedMessageException(measurementM37661buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Measurement m37661buildPartial() {
            Measurement measurement = new Measurement(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.tags_ = Collections.unmodifiableList(this.tags_);
                    this.bitField0_ &= -2;
                }
                measurement.tags_ = this.tags_;
            } else {
                measurement.tags_ = repeatedFieldBuilderV3.build();
            }
            measurement.measureName_ = this.measureName_;
            if (this.valueCase_ == 3) {
                measurement.value_ = this.value_;
            }
            if (this.valueCase_ == 4) {
                measurement.value_ = this.value_;
            }
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timeBuilder_;
            if (singleFieldBuilderV3 == null) {
                measurement.time_ = this.time_;
            } else {
                measurement.time_ = singleFieldBuilderV3.build();
            }
            measurement.bitField0_ = 0;
            measurement.valueCase_ = this.valueCase_;
            onBuilt();
            return measurement;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37677clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37689setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37667clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37670clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37691setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37657addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37682mergeFrom(Message message) {
            if (message instanceof Measurement) {
                return mergeFrom((Measurement) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Measurement measurement) {
            if (measurement == Measurement.getDefaultInstance()) {
                return this;
            }
            if (this.tagsBuilder_ == null) {
                if (!measurement.tags_.isEmpty()) {
                    if (this.tags_.isEmpty()) {
                        this.tags_ = measurement.tags_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureTagsIsMutable();
                        this.tags_.addAll(measurement.tags_);
                    }
                    onChanged();
                }
            } else if (!measurement.tags_.isEmpty()) {
                if (!this.tagsBuilder_.isEmpty()) {
                    this.tagsBuilder_.addAllMessages(measurement.tags_);
                } else {
                    this.tagsBuilder_.dispose();
                    this.tagsBuilder_ = null;
                    this.tags_ = measurement.tags_;
                    this.bitField0_ &= -2;
                    this.tagsBuilder_ = Measurement.alwaysUseFieldBuilders ? getTagsFieldBuilder() : null;
                }
            }
            if (!measurement.getMeasureName().isEmpty()) {
                this.measureName_ = measurement.measureName_;
                onChanged();
            }
            if (measurement.hasTime()) {
                mergeTime(measurement.getTime());
            }
            int i = AnonymousClass2.$SwitchMap$io$opencensus$proto$stats$v1$Measurement$ValueCase[measurement.getValueCase().ordinal()];
            if (i == 1) {
                setDoubleValue(measurement.getDoubleValue());
            } else if (i == 2) {
                setIntValue(measurement.getIntValue());
            }
            m37687mergeUnknownFields(measurement.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.stats.v1.Measurement.Builder m37683mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.stats.v1.Measurement.access$1200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.stats.v1.Measurement r3 = (io.opencensus.proto.stats.v1.Measurement) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.stats.v1.Measurement r4 = (io.opencensus.proto.stats.v1.Measurement) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.stats.v1.Measurement.Builder.m37683mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.stats.v1.Measurement$Builder");
        }

        @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
        public ValueCase getValueCase() {
            return ValueCase.forNumber(this.valueCase_);
        }

        public Builder clearValue() {
            this.valueCase_ = 0;
            this.value_ = null;
            onChanged();
            return this;
        }

        private void ensureTagsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.tags_ = new ArrayList(this.tags_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
        public List<Tag> getTagsList() {
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.tags_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
        public int getTagsCount() {
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.tags_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
        public Tag getTags(int i) {
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.tags_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setTags(int i, Tag tag) {
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                tag.getClass();
                ensureTagsIsMutable();
                this.tags_.set(i, tag);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, tag);
            }
            return this;
        }

        public Builder setTags(int i, Tag.Builder builder) {
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTagsIsMutable();
                this.tags_.set(i, builder.m37751build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m37751build());
            }
            return this;
        }

        public Builder addTags(Tag tag) {
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                tag.getClass();
                ensureTagsIsMutable();
                this.tags_.add(tag);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(tag);
            }
            return this;
        }

        public Builder addTags(int i, Tag tag) {
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                tag.getClass();
                ensureTagsIsMutable();
                this.tags_.add(i, tag);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, tag);
            }
            return this;
        }

        public Builder addTags(Tag.Builder builder) {
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTagsIsMutable();
                this.tags_.add(builder.m37751build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m37751build());
            }
            return this;
        }

        public Builder addTags(int i, Tag.Builder builder) {
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTagsIsMutable();
                this.tags_.add(i, builder.m37751build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m37751build());
            }
            return this;
        }

        public Builder addAllTags(Iterable<? extends Tag> iterable) {
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTagsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.tags_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearTags() {
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.tags_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeTags(int i) {
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTagsIsMutable();
                this.tags_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Tag.Builder getTagsBuilder(int i) {
            return getTagsFieldBuilder().getBuilder(i);
        }

        @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
        public TagOrBuilder getTagsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.tags_.get(i);
            }
            return (TagOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
        public List<? extends TagOrBuilder> getTagsOrBuilderList() {
            RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.tags_);
        }

        public Tag.Builder addTagsBuilder() {
            return getTagsFieldBuilder().addBuilder(Tag.getDefaultInstance());
        }

        public Tag.Builder addTagsBuilder(int i) {
            return getTagsFieldBuilder().addBuilder(i, Tag.getDefaultInstance());
        }

        public List<Tag.Builder> getTagsBuilderList() {
            return getTagsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Tag, Tag.Builder, TagOrBuilder> getTagsFieldBuilder() {
            if (this.tagsBuilder_ == null) {
                this.tagsBuilder_ = new RepeatedFieldBuilderV3<>(this.tags_, (this.bitField0_ & 1) == 1, getParentForChildren(), isClean());
                this.tags_ = null;
            }
            return this.tagsBuilder_;
        }

        @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
        public String getMeasureName() {
            Object obj = this.measureName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.measureName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setMeasureName(String str) {
            str.getClass();
            this.measureName_ = str;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
        public ByteString getMeasureNameBytes() {
            Object obj = this.measureName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.measureName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setMeasureNameBytes(ByteString byteString) {
            byteString.getClass();
            Measurement.checkByteStringIsUtf8(byteString);
            this.measureName_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearMeasureName() {
            this.measureName_ = Measurement.getDefaultInstance().getMeasureName();
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
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

        @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
        public long getIntValue() {
            if (this.valueCase_ == 4) {
                return ((Long) this.value_).longValue();
            }
            return 0L;
        }

        public Builder setIntValue(long j) {
            this.valueCase_ = 4;
            this.value_ = Long.valueOf(j);
            onChanged();
            return this;
        }

        public Builder clearIntValue() {
            if (this.valueCase_ == 4) {
                this.valueCase_ = 0;
                this.value_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
        public Timestamp getTime() {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Timestamp timestamp = this.time_;
            return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
        }

        public Builder setTime(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timeBuilder_;
            if (singleFieldBuilderV3 == null) {
                timestamp.getClass();
                this.time_ = timestamp;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(timestamp);
            }
            return this;
        }

        public Builder setTime(Timestamp.Builder builder) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.time_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeTime(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timeBuilder_;
            if (singleFieldBuilderV3 == null) {
                Timestamp timestamp2 = this.time_;
                if (timestamp2 != null) {
                    this.time_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
                } else {
                    this.time_ = timestamp;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(timestamp);
            }
            return this;
        }

        public Builder clearTime() {
            if (this.timeBuilder_ == null) {
                this.time_ = null;
                onChanged();
            } else {
                this.time_ = null;
                this.timeBuilder_ = null;
            }
            return this;
        }

        public Timestamp.Builder getTimeBuilder() {
            onChanged();
            return getTimeFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.stats.v1.MeasurementOrBuilder
        public TimestampOrBuilder getTimeOrBuilder() {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Timestamp timestamp = this.time_;
            return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
        }

        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> getTimeFieldBuilder() {
            if (this.timeBuilder_ == null) {
                this.timeBuilder_ = new SingleFieldBuilderV3<>(getTime(), getParentForChildren(), isClean());
                this.time_ = null;
            }
            return this.timeBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37693setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37687mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.opencensus.proto.stats.v1.Measurement$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$opencensus$proto$stats$v1$Measurement$ValueCase;

        static {
            int[] iArr = new int[ValueCase.values().length];
            $SwitchMap$io$opencensus$proto$stats$v1$Measurement$ValueCase = iArr;
            try {
                iArr[ValueCase.DOUBLE_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$opencensus$proto$stats$v1$Measurement$ValueCase[ValueCase.INT_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$opencensus$proto$stats$v1$Measurement$ValueCase[ValueCase.VALUE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
