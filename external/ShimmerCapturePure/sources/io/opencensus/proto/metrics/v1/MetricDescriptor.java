package io.opencensus.proto.metrics.v1;

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
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.opencensus.proto.metrics.v1.LabelKey;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class MetricDescriptor extends GeneratedMessageV3 implements MetricDescriptorOrBuilder {
    public static final int DESCRIPTION_FIELD_NUMBER = 2;
    public static final int LABEL_KEYS_FIELD_NUMBER = 5;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int TYPE_FIELD_NUMBER = 4;
    public static final int UNIT_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final MetricDescriptor DEFAULT_INSTANCE = new MetricDescriptor();
    private static final Parser<MetricDescriptor> PARSER = new AbstractParser<MetricDescriptor>() { // from class: io.opencensus.proto.metrics.v1.MetricDescriptor.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public MetricDescriptor m37148parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MetricDescriptor(codedInputStream, extensionRegistryLite);
        }
    };
    private int bitField0_;
    private volatile Object description_;
    private List<LabelKey> labelKeys_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private int type_;
    private volatile Object unit_;

    private MetricDescriptor(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private MetricDescriptor() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.description_ = "";
        this.unit_ = "";
        this.type_ = 0;
        this.labelKeys_ = Collections.emptyList();
    }

    private MetricDescriptor(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        } else if (tag == 18) {
                            this.description_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 26) {
                            this.unit_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 32) {
                            this.type_ = codedInputStream.readEnum();
                        } else if (tag != 42) {
                            if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            if ((i & 16) != 16) {
                                this.labelKeys_ = new ArrayList();
                                i |= 16;
                            }
                            this.labelKeys_.add(codedInputStream.readMessage(LabelKey.parser(), extensionRegistryLite));
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if ((i & 16) == 16) {
                    this.labelKeys_ = Collections.unmodifiableList(this.labelKeys_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static MetricDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MetricDescriptor> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MetricsProto.internal_static_opencensus_proto_metrics_v1_MetricDescriptor_descriptor;
    }

    public static MetricDescriptor parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MetricDescriptor) PARSER.parseFrom(byteBuffer);
    }

    public static MetricDescriptor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetricDescriptor) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MetricDescriptor parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MetricDescriptor) PARSER.parseFrom(byteString);
    }

    public static MetricDescriptor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetricDescriptor) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MetricDescriptor parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MetricDescriptor) PARSER.parseFrom(bArr);
    }

    public static MetricDescriptor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetricDescriptor) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MetricDescriptor parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MetricDescriptor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetricDescriptor parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MetricDescriptor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetricDescriptor parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MetricDescriptor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m37146toBuilder();
    }

    public static Builder newBuilder(MetricDescriptor metricDescriptor) {
        return DEFAULT_INSTANCE.m37146toBuilder().mergeFrom(metricDescriptor);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public MetricDescriptor m37141getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
    public List<LabelKey> getLabelKeysList() {
        return this.labelKeys_;
    }

    @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
    public List<? extends LabelKeyOrBuilder> getLabelKeysOrBuilderList() {
        return this.labelKeys_;
    }

    public Parser<MetricDescriptor> getParserForType() {
        return PARSER;
    }

    @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
    public int getTypeValue() {
        return this.type_;
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
        return MetricsProto.internal_static_opencensus_proto_metrics_v1_MetricDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricDescriptor.class, Builder.class);
    }

    @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
    public String getDescription() {
        Object obj = this.description_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.description_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
    public ByteString getDescriptionBytes() {
        Object obj = this.description_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
    public String getUnit() {
        Object obj = this.unit_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.unit_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
    public ByteString getUnitBytes() {
        Object obj = this.unit_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.unit_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
    public Type getType() {
        Type typeValueOf = Type.valueOf(this.type_);
        return typeValueOf == null ? Type.UNRECOGNIZED : typeValueOf;
    }

    @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
    public int getLabelKeysCount() {
        return this.labelKeys_.size();
    }

    @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
    public LabelKey getLabelKeys(int i) {
        return this.labelKeys_.get(i);
    }

    @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
    public LabelKeyOrBuilder getLabelKeysOrBuilder(int i) {
        return this.labelKeys_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.description_);
        }
        if (!getUnitBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.unit_);
        }
        if (this.type_ != Type.UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(4, this.type_);
        }
        for (int i = 0; i < this.labelKeys_.size(); i++) {
            codedOutputStream.writeMessage(5, this.labelKeys_.get(i));
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
        if (!getUnitBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.unit_);
        }
        if (this.type_ != Type.UNSPECIFIED.getNumber()) {
            iComputeStringSize += CodedOutputStream.computeEnumSize(4, this.type_);
        }
        for (int i2 = 0; i2 < this.labelKeys_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(5, this.labelKeys_.get(i2));
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MetricDescriptor)) {
            return super.equals(obj);
        }
        MetricDescriptor metricDescriptor = (MetricDescriptor) obj;
        return getName().equals(metricDescriptor.getName()) && getDescription().equals(metricDescriptor.getDescription()) && getUnit().equals(metricDescriptor.getUnit()) && this.type_ == metricDescriptor.type_ && getLabelKeysList().equals(metricDescriptor.getLabelKeysList()) && this.unknownFields.equals(metricDescriptor.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getDescription().hashCode()) * 37) + 3) * 53) + getUnit().hashCode()) * 37) + 4) * 53) + this.type_;
        if (getLabelKeysCount() > 0) {
            iHashCode = (((iHashCode * 37) + 5) * 53) + getLabelKeysList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37143newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37146toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum Type implements ProtocolMessageEnum {
        UNSPECIFIED(0),
        GAUGE_INT64(1),
        GAUGE_DOUBLE(2),
        GAUGE_DISTRIBUTION(3),
        CUMULATIVE_INT64(4),
        CUMULATIVE_DOUBLE(5),
        CUMULATIVE_DISTRIBUTION(6),
        SUMMARY(7),
        UNRECOGNIZED(-1);

        public static final int CUMULATIVE_DISTRIBUTION_VALUE = 6;
        public static final int CUMULATIVE_DOUBLE_VALUE = 5;
        public static final int CUMULATIVE_INT64_VALUE = 4;
        public static final int GAUGE_DISTRIBUTION_VALUE = 3;
        public static final int GAUGE_DOUBLE_VALUE = 2;
        public static final int GAUGE_INT64_VALUE = 1;
        public static final int SUMMARY_VALUE = 7;
        public static final int UNSPECIFIED_VALUE = 0;
        private static final Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() { // from class: io.opencensus.proto.metrics.v1.MetricDescriptor.Type.1
            public Type findValueByNumber(int i) {
                return Type.forNumber(i);
            }
        };
        private static final Type[] VALUES = values();
        private final int value;

        Type(int i) {
            this.value = i;
        }

        public static Type forNumber(int i) {
            switch (i) {
                case 0:
                    return UNSPECIFIED;
                case 1:
                    return GAUGE_INT64;
                case 2:
                    return GAUGE_DOUBLE;
                case 3:
                    return GAUGE_DISTRIBUTION;
                case 4:
                    return CUMULATIVE_INT64;
                case 5:
                    return CUMULATIVE_DOUBLE;
                case 6:
                    return CUMULATIVE_DISTRIBUTION;
                case 7:
                    return SUMMARY;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<Type> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static Type valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) MetricDescriptor.getDescriptor().getEnumTypes().get(0);
        }

        public static Type valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetricDescriptorOrBuilder {
        private int bitField0_;
        private Object description_;
        private RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> labelKeysBuilder_;
        private List<LabelKey> labelKeys_;
        private Object name_;
        private int type_;
        private Object unit_;

        private Builder() {
            this.name_ = "";
            this.description_ = "";
            this.unit_ = "";
            this.type_ = 0;
            this.labelKeys_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.description_ = "";
            this.unit_ = "";
            this.type_ = 0;
            this.labelKeys_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MetricsProto.internal_static_opencensus_proto_metrics_v1_MetricDescriptor_descriptor;
        }

        @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
        public int getTypeValue() {
            return this.type_;
        }

        public Builder setTypeValue(int i) {
            this.type_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MetricsProto.internal_static_opencensus_proto_metrics_v1_MetricDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricDescriptor.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (MetricDescriptor.alwaysUseFieldBuilders) {
                getLabelKeysFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37157clear() {
            super.clear();
            this.name_ = "";
            this.description_ = "";
            this.unit_ = "";
            this.type_ = 0;
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.labelKeys_ = Collections.emptyList();
                this.bitField0_ &= -17;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return MetricsProto.internal_static_opencensus_proto_metrics_v1_MetricDescriptor_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetricDescriptor m37170getDefaultInstanceForType() {
            return MetricDescriptor.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetricDescriptor m37151build() throws UninitializedMessageException {
            MetricDescriptor metricDescriptorM37153buildPartial = m37153buildPartial();
            if (metricDescriptorM37153buildPartial.isInitialized()) {
                return metricDescriptorM37153buildPartial;
            }
            throw newUninitializedMessageException(metricDescriptorM37153buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetricDescriptor m37153buildPartial() {
            MetricDescriptor metricDescriptor = new MetricDescriptor(this);
            metricDescriptor.name_ = this.name_;
            metricDescriptor.description_ = this.description_;
            metricDescriptor.unit_ = this.unit_;
            metricDescriptor.type_ = this.type_;
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 16) == 16) {
                    this.labelKeys_ = Collections.unmodifiableList(this.labelKeys_);
                    this.bitField0_ &= -17;
                }
                metricDescriptor.labelKeys_ = this.labelKeys_;
            } else {
                metricDescriptor.labelKeys_ = repeatedFieldBuilderV3.build();
            }
            metricDescriptor.bitField0_ = 0;
            onBuilt();
            return metricDescriptor;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37169clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37181setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37159clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37162clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37183setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37149addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37174mergeFrom(Message message) {
            if (message instanceof MetricDescriptor) {
                return mergeFrom((MetricDescriptor) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MetricDescriptor metricDescriptor) {
            if (metricDescriptor == MetricDescriptor.getDefaultInstance()) {
                return this;
            }
            if (!metricDescriptor.getName().isEmpty()) {
                this.name_ = metricDescriptor.name_;
                onChanged();
            }
            if (!metricDescriptor.getDescription().isEmpty()) {
                this.description_ = metricDescriptor.description_;
                onChanged();
            }
            if (!metricDescriptor.getUnit().isEmpty()) {
                this.unit_ = metricDescriptor.unit_;
                onChanged();
            }
            if (metricDescriptor.type_ != 0) {
                setTypeValue(metricDescriptor.getTypeValue());
            }
            if (this.labelKeysBuilder_ == null) {
                if (!metricDescriptor.labelKeys_.isEmpty()) {
                    if (this.labelKeys_.isEmpty()) {
                        this.labelKeys_ = metricDescriptor.labelKeys_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureLabelKeysIsMutable();
                        this.labelKeys_.addAll(metricDescriptor.labelKeys_);
                    }
                    onChanged();
                }
            } else if (!metricDescriptor.labelKeys_.isEmpty()) {
                if (!this.labelKeysBuilder_.isEmpty()) {
                    this.labelKeysBuilder_.addAllMessages(metricDescriptor.labelKeys_);
                } else {
                    this.labelKeysBuilder_.dispose();
                    this.labelKeysBuilder_ = null;
                    this.labelKeys_ = metricDescriptor.labelKeys_;
                    this.bitField0_ &= -17;
                    this.labelKeysBuilder_ = MetricDescriptor.alwaysUseFieldBuilders ? getLabelKeysFieldBuilder() : null;
                }
            }
            m37179mergeUnknownFields(metricDescriptor.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.metrics.v1.MetricDescriptor.Builder m37175mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.metrics.v1.MetricDescriptor.access$1200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.metrics.v1.MetricDescriptor r3 = (io.opencensus.proto.metrics.v1.MetricDescriptor) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.metrics.v1.MetricDescriptor r4 = (io.opencensus.proto.metrics.v1.MetricDescriptor) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.metrics.v1.MetricDescriptor.Builder.m37175mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.metrics.v1.MetricDescriptor$Builder");
        }

        @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
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

        @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
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
            MetricDescriptor.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = MetricDescriptor.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
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

        @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
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
            MetricDescriptor.checkByteStringIsUtf8(byteString);
            this.description_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearDescription() {
            this.description_ = MetricDescriptor.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
        public String getUnit() {
            Object obj = this.unit_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.unit_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setUnit(String str) {
            str.getClass();
            this.unit_ = str;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
        public ByteString getUnitBytes() {
            Object obj = this.unit_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.unit_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setUnitBytes(ByteString byteString) {
            byteString.getClass();
            MetricDescriptor.checkByteStringIsUtf8(byteString);
            this.unit_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearUnit() {
            this.unit_ = MetricDescriptor.getDefaultInstance().getUnit();
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
        public Type getType() {
            Type typeValueOf = Type.valueOf(this.type_);
            return typeValueOf == null ? Type.UNRECOGNIZED : typeValueOf;
        }

        public Builder setType(Type type) {
            type.getClass();
            this.type_ = type.getNumber();
            onChanged();
            return this;
        }

        public Builder clearType() {
            this.type_ = 0;
            onChanged();
            return this;
        }

        private void ensureLabelKeysIsMutable() {
            if ((this.bitField0_ & 16) != 16) {
                this.labelKeys_ = new ArrayList(this.labelKeys_);
                this.bitField0_ |= 16;
            }
        }

        @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
        public List<LabelKey> getLabelKeysList() {
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.labelKeys_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
        public int getLabelKeysCount() {
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.labelKeys_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
        public LabelKey getLabelKeys(int i) {
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.labelKeys_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setLabelKeys(int i, LabelKey labelKey) {
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                labelKey.getClass();
                ensureLabelKeysIsMutable();
                this.labelKeys_.set(i, labelKey);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, labelKey);
            }
            return this;
        }

        public Builder setLabelKeys(int i, LabelKey.Builder builder) {
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelKeysIsMutable();
                this.labelKeys_.set(i, builder.m37013build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m37013build());
            }
            return this;
        }

        public Builder addLabelKeys(LabelKey labelKey) {
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                labelKey.getClass();
                ensureLabelKeysIsMutable();
                this.labelKeys_.add(labelKey);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(labelKey);
            }
            return this;
        }

        public Builder addLabelKeys(int i, LabelKey labelKey) {
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                labelKey.getClass();
                ensureLabelKeysIsMutable();
                this.labelKeys_.add(i, labelKey);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, labelKey);
            }
            return this;
        }

        public Builder addLabelKeys(LabelKey.Builder builder) {
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelKeysIsMutable();
                this.labelKeys_.add(builder.m37013build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m37013build());
            }
            return this;
        }

        public Builder addLabelKeys(int i, LabelKey.Builder builder) {
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelKeysIsMutable();
                this.labelKeys_.add(i, builder.m37013build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m37013build());
            }
            return this;
        }

        public Builder addAllLabelKeys(Iterable<? extends LabelKey> iterable) {
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelKeysIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.labelKeys_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearLabelKeys() {
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.labelKeys_ = Collections.emptyList();
                this.bitField0_ &= -17;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeLabelKeys(int i) {
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelKeysIsMutable();
                this.labelKeys_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public LabelKey.Builder getLabelKeysBuilder(int i) {
            return getLabelKeysFieldBuilder().getBuilder(i);
        }

        @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
        public LabelKeyOrBuilder getLabelKeysOrBuilder(int i) {
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.labelKeys_.get(i);
            }
            return (LabelKeyOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.opencensus.proto.metrics.v1.MetricDescriptorOrBuilder
        public List<? extends LabelKeyOrBuilder> getLabelKeysOrBuilderList() {
            RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> repeatedFieldBuilderV3 = this.labelKeysBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.labelKeys_);
        }

        public LabelKey.Builder addLabelKeysBuilder() {
            return getLabelKeysFieldBuilder().addBuilder(LabelKey.getDefaultInstance());
        }

        public LabelKey.Builder addLabelKeysBuilder(int i) {
            return getLabelKeysFieldBuilder().addBuilder(i, LabelKey.getDefaultInstance());
        }

        public List<LabelKey.Builder> getLabelKeysBuilderList() {
            return getLabelKeysFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LabelKey, LabelKey.Builder, LabelKeyOrBuilder> getLabelKeysFieldBuilder() {
            if (this.labelKeysBuilder_ == null) {
                this.labelKeysBuilder_ = new RepeatedFieldBuilderV3<>(this.labelKeys_, (this.bitField0_ & 16) == 16, getParentForChildren(), isClean());
                this.labelKeys_ = null;
            }
            return this.labelKeysBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37185setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37179mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
