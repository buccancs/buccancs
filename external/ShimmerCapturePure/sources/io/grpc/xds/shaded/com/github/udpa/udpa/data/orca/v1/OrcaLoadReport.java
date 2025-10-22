package io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/* loaded from: classes3.dex */
public final class OrcaLoadReport extends GeneratedMessageV3 implements OrcaLoadReportOrBuilder {
    public static final int CPU_UTILIZATION_FIELD_NUMBER = 1;
    public static final int MEM_UTILIZATION_FIELD_NUMBER = 2;
    public static final int REQUEST_COST_FIELD_NUMBER = 4;
    public static final int RPS_FIELD_NUMBER = 3;
    public static final int UTILIZATION_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private static final OrcaLoadReport DEFAULT_INSTANCE = new OrcaLoadReport();
    private static final Parser<OrcaLoadReport> PARSER = new AbstractParser<OrcaLoadReport>() { // from class: io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReport.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public OrcaLoadReport m10363parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new OrcaLoadReport(codedInputStream, extensionRegistryLite);
        }
    };
    private double cpuUtilization_;
    private double memUtilization_;
    private byte memoizedIsInitialized;
    private MapField<String, Double> requestCost_;
    private long rps_;
    private MapField<String, Double> utilization_;

    private OrcaLoadReport(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private OrcaLoadReport() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private OrcaLoadReport(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 9) {
                                this.cpuUtilization_ = codedInputStream.readDouble();
                            } else if (tag == 17) {
                                this.memUtilization_ = codedInputStream.readDouble();
                            } else if (tag == 24) {
                                this.rps_ = codedInputStream.readUInt64();
                            } else if (tag == 34) {
                                if ((i & 1) == 0) {
                                    this.requestCost_ = MapField.newMapField(RequestCostDefaultEntryHolder.defaultEntry);
                                    i |= 1;
                                }
                                MapEntry message = codedInputStream.readMessage(RequestCostDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.requestCost_.getMutableMap().put(message.getKey(), message.getValue());
                            } else if (tag == 42) {
                                if ((i & 2) == 0) {
                                    this.utilization_ = MapField.newMapField(UtilizationDefaultEntryHolder.defaultEntry);
                                    i |= 2;
                                }
                                MapEntry message2 = codedInputStream.readMessage(UtilizationDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.utilization_.getMutableMap().put(message2.getKey(), message2.getValue());
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

    public static OrcaLoadReport getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<OrcaLoadReport> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return OrcaLoadReportProto.internal_static_udpa_data_orca_v1_OrcaLoadReport_descriptor;
    }

    public static OrcaLoadReport parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (OrcaLoadReport) PARSER.parseFrom(byteBuffer);
    }

    public static OrcaLoadReport parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (OrcaLoadReport) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static OrcaLoadReport parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (OrcaLoadReport) PARSER.parseFrom(byteString);
    }

    public static OrcaLoadReport parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (OrcaLoadReport) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static OrcaLoadReport parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (OrcaLoadReport) PARSER.parseFrom(bArr);
    }

    public static OrcaLoadReport parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (OrcaLoadReport) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static OrcaLoadReport parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static OrcaLoadReport parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static OrcaLoadReport parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static OrcaLoadReport parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static OrcaLoadReport parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static OrcaLoadReport parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m10361toBuilder();
    }

    public static Builder newBuilder(OrcaLoadReport orcaLoadReport) {
        return DEFAULT_INSTANCE.m10361toBuilder().mergeFrom(orcaLoadReport);
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    public double getCpuUtilization() {
        return this.cpuUtilization_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public OrcaLoadReport m10356getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    public double getMemUtilization() {
        return this.memUtilization_;
    }

    public Parser<OrcaLoadReport> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    public long getRps() {
        return this.rps_;
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
        return new OrcaLoadReport();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected MapField internalGetMapField(int i) {
        if (i == 4) {
            return internalGetRequestCost();
        }
        if (i == 5) {
            return internalGetUtilization();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return OrcaLoadReportProto.internal_static_udpa_data_orca_v1_OrcaLoadReport_fieldAccessorTable.ensureFieldAccessorsInitialized(OrcaLoadReport.class, Builder.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, Double> internalGetRequestCost() {
        MapField<String, Double> mapField = this.requestCost_;
        return mapField == null ? MapField.emptyMapField(RequestCostDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    public int getRequestCostCount() {
        return internalGetRequestCost().getMap().size();
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    public boolean containsRequestCost(String str) {
        str.getClass();
        return internalGetRequestCost().getMap().containsKey(str);
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    @Deprecated
    public Map<String, Double> getRequestCost() {
        return getRequestCostMap();
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    public Map<String, Double> getRequestCostMap() {
        return internalGetRequestCost().getMap();
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    public double getRequestCostOrDefault(String str, double d) {
        str.getClass();
        Map map = internalGetRequestCost().getMap();
        return map.containsKey(str) ? ((Double) map.get(str)).doubleValue() : d;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    public double getRequestCostOrThrow(String str) {
        str.getClass();
        Map map = internalGetRequestCost().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return ((Double) map.get(str)).doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, Double> internalGetUtilization() {
        MapField<String, Double> mapField = this.utilization_;
        return mapField == null ? MapField.emptyMapField(UtilizationDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    public int getUtilizationCount() {
        return internalGetUtilization().getMap().size();
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    public boolean containsUtilization(String str) {
        str.getClass();
        return internalGetUtilization().getMap().containsKey(str);
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    @Deprecated
    public Map<String, Double> getUtilization() {
        return getUtilizationMap();
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    public Map<String, Double> getUtilizationMap() {
        return internalGetUtilization().getMap();
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    public double getUtilizationOrDefault(String str, double d) {
        str.getClass();
        Map map = internalGetUtilization().getMap();
        return map.containsKey(str) ? ((Double) map.get(str)).doubleValue() : d;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
    public double getUtilizationOrThrow(String str) {
        str.getClass();
        Map map = internalGetUtilization().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return ((Double) map.get(str)).doubleValue();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        double d = this.cpuUtilization_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(1, d);
        }
        double d2 = this.memUtilization_;
        if (d2 != 0.0d) {
            codedOutputStream.writeDouble(2, d2);
        }
        long j = this.rps_;
        if (j != 0) {
            codedOutputStream.writeUInt64(3, j);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetRequestCost(), RequestCostDefaultEntryHolder.defaultEntry, 4);
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetUtilization(), UtilizationDefaultEntryHolder.defaultEntry, 5);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        double d = this.cpuUtilization_;
        int iComputeDoubleSize = d != 0.0d ? CodedOutputStream.computeDoubleSize(1, d) : 0;
        double d2 = this.memUtilization_;
        if (d2 != 0.0d) {
            iComputeDoubleSize += CodedOutputStream.computeDoubleSize(2, d2);
        }
        long j = this.rps_;
        if (j != 0) {
            iComputeDoubleSize += CodedOutputStream.computeUInt64Size(3, j);
        }
        for (Map.Entry entry : internalGetRequestCost().getMap().entrySet()) {
            iComputeDoubleSize += CodedOutputStream.computeMessageSize(4, RequestCostDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        for (Map.Entry entry2 : internalGetUtilization().getMap().entrySet()) {
            iComputeDoubleSize += CodedOutputStream.computeMessageSize(5, UtilizationDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry2.getKey()).setValue(entry2.getValue()).build());
        }
        int serializedSize = iComputeDoubleSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OrcaLoadReport)) {
            return super.equals(obj);
        }
        OrcaLoadReport orcaLoadReport = (OrcaLoadReport) obj;
        return Double.doubleToLongBits(getCpuUtilization()) == Double.doubleToLongBits(orcaLoadReport.getCpuUtilization()) && Double.doubleToLongBits(getMemUtilization()) == Double.doubleToLongBits(orcaLoadReport.getMemUtilization()) && getRps() == orcaLoadReport.getRps() && internalGetRequestCost().equals(orcaLoadReport.internalGetRequestCost()) && internalGetUtilization().equals(orcaLoadReport.internalGetUtilization()) && this.unknownFields.equals(orcaLoadReport.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getCpuUtilization()))) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getMemUtilization()))) * 37) + 3) * 53) + Internal.hashLong(getRps());
        if (!internalGetRequestCost().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + internalGetRequestCost().hashCode();
        }
        if (!internalGetUtilization().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 5) * 53) + internalGetUtilization().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m10358newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m10361toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    private static final class RequestCostDefaultEntryHolder {
        static final MapEntry<String, Double> defaultEntry = MapEntry.newDefaultInstance(OrcaLoadReportProto.internal_static_udpa_data_orca_v1_OrcaLoadReport_RequestCostEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.DOUBLE, Double.valueOf(0.0d));

        private RequestCostDefaultEntryHolder() {
        }
    }

    private static final class UtilizationDefaultEntryHolder {
        static final MapEntry<String, Double> defaultEntry = MapEntry.newDefaultInstance(OrcaLoadReportProto.internal_static_udpa_data_orca_v1_OrcaLoadReport_UtilizationEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.DOUBLE, Double.valueOf(0.0d));

        private UtilizationDefaultEntryHolder() {
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OrcaLoadReportOrBuilder {
        private int bitField0_;
        private double cpuUtilization_;
        private double memUtilization_;
        private MapField<String, Double> requestCost_;
        private long rps_;
        private MapField<String, Double> utilization_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return OrcaLoadReportProto.internal_static_udpa_data_orca_v1_OrcaLoadReport_descriptor;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        public double getCpuUtilization() {
            return this.cpuUtilization_;
        }

        public Builder setCpuUtilization(double d) {
            this.cpuUtilization_ = d;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        public double getMemUtilization() {
            return this.memUtilization_;
        }

        public Builder setMemUtilization(double d) {
            this.memUtilization_ = d;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        public long getRps() {
            return this.rps_;
        }

        public Builder setRps(long j) {
            this.rps_ = j;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 4) {
                return internalGetRequestCost();
            }
            if (i == 5) {
                return internalGetUtilization();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected MapField internalGetMutableMapField(int i) {
            if (i == 4) {
                return internalGetMutableRequestCost();
            }
            if (i == 5) {
                return internalGetMutableUtilization();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return OrcaLoadReportProto.internal_static_udpa_data_orca_v1_OrcaLoadReport_fieldAccessorTable.ensureFieldAccessorsInitialized(OrcaLoadReport.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = OrcaLoadReport.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10372clear() {
            super.clear();
            this.cpuUtilization_ = 0.0d;
            this.memUtilization_ = 0.0d;
            this.rps_ = 0L;
            internalGetMutableRequestCost().clear();
            internalGetMutableUtilization().clear();
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return OrcaLoadReportProto.internal_static_udpa_data_orca_v1_OrcaLoadReport_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public OrcaLoadReport m10385getDefaultInstanceForType() {
            return OrcaLoadReport.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public OrcaLoadReport m10366build() throws UninitializedMessageException {
            OrcaLoadReport orcaLoadReportM10368buildPartial = m10368buildPartial();
            if (orcaLoadReportM10368buildPartial.isInitialized()) {
                return orcaLoadReportM10368buildPartial;
            }
            throw newUninitializedMessageException(orcaLoadReportM10368buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public OrcaLoadReport m10368buildPartial() {
            OrcaLoadReport orcaLoadReport = new OrcaLoadReport(this);
            orcaLoadReport.cpuUtilization_ = this.cpuUtilization_;
            orcaLoadReport.memUtilization_ = this.memUtilization_;
            orcaLoadReport.rps_ = this.rps_;
            orcaLoadReport.requestCost_ = internalGetRequestCost();
            orcaLoadReport.requestCost_.makeImmutable();
            orcaLoadReport.utilization_ = internalGetUtilization();
            orcaLoadReport.utilization_.makeImmutable();
            onBuilt();
            return orcaLoadReport;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10384clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10396setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10374clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10377clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10398setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10364addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10389mergeFrom(Message message) {
            if (message instanceof OrcaLoadReport) {
                return mergeFrom((OrcaLoadReport) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(OrcaLoadReport orcaLoadReport) {
            if (orcaLoadReport == OrcaLoadReport.getDefaultInstance()) {
                return this;
            }
            if (orcaLoadReport.getCpuUtilization() != 0.0d) {
                setCpuUtilization(orcaLoadReport.getCpuUtilization());
            }
            if (orcaLoadReport.getMemUtilization() != 0.0d) {
                setMemUtilization(orcaLoadReport.getMemUtilization());
            }
            if (orcaLoadReport.getRps() != 0) {
                setRps(orcaLoadReport.getRps());
            }
            internalGetMutableRequestCost().mergeFrom(orcaLoadReport.internalGetRequestCost());
            internalGetMutableUtilization().mergeFrom(orcaLoadReport.internalGetUtilization());
            m10394mergeUnknownFields(orcaLoadReport.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReport.Builder m10390mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReport.access$1200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReport r3 = (io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReport) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReport r4 = (io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReport) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReport.Builder.m10390mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReport$Builder");
        }

        public Builder clearCpuUtilization() {
            this.cpuUtilization_ = 0.0d;
            onChanged();
            return this;
        }

        public Builder clearMemUtilization() {
            this.memUtilization_ = 0.0d;
            onChanged();
            return this;
        }

        public Builder clearRps() {
            this.rps_ = 0L;
            onChanged();
            return this;
        }

        private MapField<String, Double> internalGetRequestCost() {
            MapField<String, Double> mapField = this.requestCost_;
            return mapField == null ? MapField.emptyMapField(RequestCostDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, Double> internalGetMutableRequestCost() {
            onChanged();
            if (this.requestCost_ == null) {
                this.requestCost_ = MapField.newMapField(RequestCostDefaultEntryHolder.defaultEntry);
            }
            if (!this.requestCost_.isMutable()) {
                this.requestCost_ = this.requestCost_.copy();
            }
            return this.requestCost_;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        public int getRequestCostCount() {
            return internalGetRequestCost().getMap().size();
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        public boolean containsRequestCost(String str) {
            str.getClass();
            return internalGetRequestCost().getMap().containsKey(str);
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        @Deprecated
        public Map<String, Double> getRequestCost() {
            return getRequestCostMap();
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        public Map<String, Double> getRequestCostMap() {
            return internalGetRequestCost().getMap();
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        public double getRequestCostOrDefault(String str, double d) {
            str.getClass();
            Map map = internalGetRequestCost().getMap();
            return map.containsKey(str) ? ((Double) map.get(str)).doubleValue() : d;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        public double getRequestCostOrThrow(String str) {
            str.getClass();
            Map map = internalGetRequestCost().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return ((Double) map.get(str)).doubleValue();
        }

        public Builder clearRequestCost() {
            internalGetMutableRequestCost().getMutableMap().clear();
            return this;
        }

        public Builder removeRequestCost(String str) {
            str.getClass();
            internalGetMutableRequestCost().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, Double> getMutableRequestCost() {
            return internalGetMutableRequestCost().getMutableMap();
        }

        public Builder putRequestCost(String str, double d) {
            str.getClass();
            internalGetMutableRequestCost().getMutableMap().put(str, Double.valueOf(d));
            return this;
        }

        public Builder putAllRequestCost(Map<String, Double> map) {
            internalGetMutableRequestCost().getMutableMap().putAll(map);
            return this;
        }

        private MapField<String, Double> internalGetUtilization() {
            MapField<String, Double> mapField = this.utilization_;
            return mapField == null ? MapField.emptyMapField(UtilizationDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, Double> internalGetMutableUtilization() {
            onChanged();
            if (this.utilization_ == null) {
                this.utilization_ = MapField.newMapField(UtilizationDefaultEntryHolder.defaultEntry);
            }
            if (!this.utilization_.isMutable()) {
                this.utilization_ = this.utilization_.copy();
            }
            return this.utilization_;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        public int getUtilizationCount() {
            return internalGetUtilization().getMap().size();
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        public boolean containsUtilization(String str) {
            str.getClass();
            return internalGetUtilization().getMap().containsKey(str);
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        @Deprecated
        public Map<String, Double> getUtilization() {
            return getUtilizationMap();
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        public Map<String, Double> getUtilizationMap() {
            return internalGetUtilization().getMap();
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        public double getUtilizationOrDefault(String str, double d) {
            str.getClass();
            Map map = internalGetUtilization().getMap();
            return map.containsKey(str) ? ((Double) map.get(str)).doubleValue() : d;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReportOrBuilder
        public double getUtilizationOrThrow(String str) {
            str.getClass();
            Map map = internalGetUtilization().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return ((Double) map.get(str)).doubleValue();
        }

        public Builder clearUtilization() {
            internalGetMutableUtilization().getMutableMap().clear();
            return this;
        }

        public Builder removeUtilization(String str) {
            str.getClass();
            internalGetMutableUtilization().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, Double> getMutableUtilization() {
            return internalGetMutableUtilization().getMutableMap();
        }

        public Builder putUtilization(String str, double d) {
            str.getClass();
            internalGetMutableUtilization().getMutableMap().put(str, Double.valueOf(d));
            return this;
        }

        public Builder putAllUtilization(Map<String, Double> map) {
            internalGetMutableUtilization().getMutableMap().putAll(map);
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m10400setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m10394mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
