package com.google.api;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
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

/* loaded from: classes.dex */
public final class MetricRule extends GeneratedMessageV3 implements MetricRuleOrBuilder {
    public static final int METRIC_COSTS_FIELD_NUMBER = 2;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final MetricRule DEFAULT_INSTANCE = new MetricRule();
    private static final Parser<MetricRule> PARSER = new AbstractParser<MetricRule>() { // from class: com.google.api.MetricRule.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public MetricRule m1782parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MetricRule(codedInputStream, extensionRegistryLite);
        }
    };
    private int bitField0_;
    private byte memoizedIsInitialized;
    private MapField<String, Long> metricCosts_;
    private volatile Object selector_;

    private MetricRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private MetricRule() {
        this.memoizedIsInitialized = (byte) -1;
        this.selector_ = "";
    }

    private MetricRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (tag == 10) {
                            this.selector_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            if ((i & 2) == 0) {
                                this.metricCosts_ = MapField.newMapField(MetricCostsDefaultEntryHolder.defaultEntry);
                                i |= 2;
                            }
                            MapEntry message = codedInputStream.readMessage(MetricCostsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                            this.metricCosts_.getMutableMap().put(message.getKey(), message.getValue());
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
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static MetricRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MetricRule> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return QuotaProto.internal_static_google_api_MetricRule_descriptor;
    }

    public static MetricRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MetricRule) PARSER.parseFrom(byteBuffer);
    }

    public static MetricRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetricRule) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MetricRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MetricRule) PARSER.parseFrom(byteString);
    }

    public static MetricRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetricRule) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MetricRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MetricRule) PARSER.parseFrom(bArr);
    }

    public static MetricRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetricRule) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MetricRule parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MetricRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetricRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MetricRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetricRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MetricRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m1781toBuilder();
    }

    public static Builder newBuilder(MetricRule metricRule) {
        return DEFAULT_INSTANCE.m1781toBuilder().mergeFrom(metricRule);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public MetricRule m1776getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<MetricRule> getParserForType() {
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

    protected MapField internalGetMapField(int i) {
        if (i == 2) {
            return internalGetMetricCosts();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return QuotaProto.internal_static_google_api_MetricRule_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricRule.class, Builder.class);
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public String getSelector() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.selector_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public ByteString getSelectorBytes() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.selector_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, Long> internalGetMetricCosts() {
        MapField<String, Long> mapField = this.metricCosts_;
        return mapField == null ? MapField.emptyMapField(MetricCostsDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public int getMetricCostsCount() {
        return internalGetMetricCosts().getMap().size();
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public boolean containsMetricCosts(String str) {
        str.getClass();
        return internalGetMetricCosts().getMap().containsKey(str);
    }

    @Override // com.google.api.MetricRuleOrBuilder
    @Deprecated
    public Map<String, Long> getMetricCosts() {
        return getMetricCostsMap();
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public Map<String, Long> getMetricCostsMap() {
        return internalGetMetricCosts().getMap();
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public long getMetricCostsOrDefault(String str, long j) {
        str.getClass();
        Map map = internalGetMetricCosts().getMap();
        return map.containsKey(str) ? ((Long) map.get(str)).longValue() : j;
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public long getMetricCostsOrThrow(String str) {
        str.getClass();
        Map map = internalGetMetricCosts().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return ((Long) map.get(str)).longValue();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getSelectorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.selector_);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetMetricCosts(), MetricCostsDefaultEntryHolder.defaultEntry, 2);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getSelectorBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.selector_) : 0;
        for (Map.Entry entry : internalGetMetricCosts().getMap().entrySet()) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, MetricCostsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MetricRule)) {
            return super.equals(obj);
        }
        MetricRule metricRule = (MetricRule) obj;
        return getSelector().equals(metricRule.getSelector()) && internalGetMetricCosts().equals(metricRule.internalGetMetricCosts()) && this.unknownFields.equals(metricRule.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode();
        if (!internalGetMetricCosts().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + internalGetMetricCosts().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m1779newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m1781toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1778newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    private static final class MetricCostsDefaultEntryHolder {
        static final MapEntry<String, Long> defaultEntry = MapEntry.newDefaultInstance(QuotaProto.internal_static_google_api_MetricRule_MetricCostsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.INT64, 0L);

        private MetricCostsDefaultEntryHolder() {
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetricRuleOrBuilder {
        private int bitField0_;
        private MapField<String, Long> metricCosts_;
        private Object selector_;

        private Builder() {
            this.selector_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.selector_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return QuotaProto.internal_static_google_api_MetricRule_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 2) {
                return internalGetMetricCosts();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected MapField internalGetMutableMapField(int i) {
            if (i == 2) {
                return internalGetMutableMetricCosts();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return QuotaProto.internal_static_google_api_MetricRule_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricRule.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = MetricRule.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1792clear() {
            super.clear();
            this.selector_ = "";
            internalGetMutableMetricCosts().clear();
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return QuotaProto.internal_static_google_api_MetricRule_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetricRule m1805getDefaultInstanceForType() {
            return MetricRule.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetricRule m1786build() throws UninitializedMessageException {
            MetricRule metricRuleM1788buildPartial = m1788buildPartial();
            if (metricRuleM1788buildPartial.isInitialized()) {
                return metricRuleM1788buildPartial;
            }
            throw newUninitializedMessageException(metricRuleM1788buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetricRule m1788buildPartial() {
            MetricRule metricRule = new MetricRule(this);
            metricRule.selector_ = this.selector_;
            metricRule.metricCosts_ = internalGetMetricCosts();
            metricRule.metricCosts_.makeImmutable();
            metricRule.bitField0_ = 0;
            onBuilt();
            return metricRule;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1803clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1816setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1794clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1797clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1818setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1784addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1810mergeFrom(Message message) {
            if (message instanceof MetricRule) {
                return mergeFrom((MetricRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MetricRule metricRule) {
            if (metricRule == MetricRule.getDefaultInstance()) {
                return this;
            }
            if (!metricRule.getSelector().isEmpty()) {
                this.selector_ = metricRule.selector_;
                onChanged();
            }
            internalGetMutableMetricCosts().mergeFrom(metricRule.internalGetMetricCosts());
            m1814mergeUnknownFields(metricRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.api.MetricRule.Builder m1811mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.MetricRule.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.MetricRule r3 = (com.google.api.MetricRule) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.MetricRule r4 = (com.google.api.MetricRule) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.MetricRule.Builder.m1811mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.MetricRule$Builder");
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public String getSelector() {
            Object obj = this.selector_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.selector_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setSelector(String str) {
            str.getClass();
            this.selector_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public ByteString getSelectorBytes() {
            Object obj = this.selector_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.selector_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            byteString.getClass();
            MetricRule.checkByteStringIsUtf8(byteString);
            this.selector_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearSelector() {
            this.selector_ = MetricRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        private MapField<String, Long> internalGetMetricCosts() {
            MapField<String, Long> mapField = this.metricCosts_;
            return mapField == null ? MapField.emptyMapField(MetricCostsDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, Long> internalGetMutableMetricCosts() {
            onChanged();
            if (this.metricCosts_ == null) {
                this.metricCosts_ = MapField.newMapField(MetricCostsDefaultEntryHolder.defaultEntry);
            }
            if (!this.metricCosts_.isMutable()) {
                this.metricCosts_ = this.metricCosts_.copy();
            }
            return this.metricCosts_;
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public int getMetricCostsCount() {
            return internalGetMetricCosts().getMap().size();
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public boolean containsMetricCosts(String str) {
            str.getClass();
            return internalGetMetricCosts().getMap().containsKey(str);
        }

        @Override // com.google.api.MetricRuleOrBuilder
        @Deprecated
        public Map<String, Long> getMetricCosts() {
            return getMetricCostsMap();
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public Map<String, Long> getMetricCostsMap() {
            return internalGetMetricCosts().getMap();
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public long getMetricCostsOrDefault(String str, long j) {
            str.getClass();
            Map map = internalGetMetricCosts().getMap();
            return map.containsKey(str) ? ((Long) map.get(str)).longValue() : j;
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public long getMetricCostsOrThrow(String str) {
            str.getClass();
            Map map = internalGetMetricCosts().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return ((Long) map.get(str)).longValue();
        }

        public Builder clearMetricCosts() {
            internalGetMutableMetricCosts().getMutableMap().clear();
            return this;
        }

        public Builder removeMetricCosts(String str) {
            str.getClass();
            internalGetMutableMetricCosts().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, Long> getMutableMetricCosts() {
            return internalGetMutableMetricCosts().getMutableMap();
        }

        public Builder putMetricCosts(String str, long j) {
            str.getClass();
            internalGetMutableMetricCosts().getMutableMap().put(str, Long.valueOf(j));
            return this;
        }

        public Builder putAllMetricCosts(Map<String, Long> map) {
            internalGetMutableMetricCosts().getMutableMap().putAll(map);
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1820setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1814mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
