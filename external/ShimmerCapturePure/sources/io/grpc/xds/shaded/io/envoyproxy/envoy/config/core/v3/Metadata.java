package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

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
import com.google.protobuf.Struct;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/* loaded from: classes6.dex */
public final class Metadata extends GeneratedMessageV3 implements MetadataOrBuilder {
    public static final int FILTER_METADATA_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final Metadata DEFAULT_INSTANCE = new Metadata();
    private static final Parser<Metadata> PARSER = new AbstractParser<Metadata>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Metadata.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Metadata m23600parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Metadata(codedInputStream, extensionRegistryLite);
        }
    };
    private MapField<String, Struct> filterMetadata_;
    private byte memoizedIsInitialized;

    private Metadata(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Metadata() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private Metadata(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (tag == 10) {
                            if (!(z2 & true)) {
                                this.filterMetadata_ = MapField.newMapField(FilterMetadataDefaultEntryHolder.defaultEntry);
                                z2 |= true;
                            }
                            MapEntry message = codedInputStream.readMessage(FilterMetadataDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                            this.filterMetadata_.getMutableMap().put(message.getKey(), message.getValue());
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

    public static Metadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Metadata> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BaseProto.internal_static_envoy_config_core_v3_Metadata_descriptor;
    }

    public static Metadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Metadata) PARSER.parseFrom(byteBuffer);
    }

    public static Metadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Metadata) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Metadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Metadata) PARSER.parseFrom(byteString);
    }

    public static Metadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Metadata) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Metadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Metadata) PARSER.parseFrom(bArr);
    }

    public static Metadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Metadata) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Metadata parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Metadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Metadata parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Metadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Metadata parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Metadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m23598toBuilder();
    }

    public static Builder newBuilder(Metadata metadata) {
        return DEFAULT_INSTANCE.m23598toBuilder().mergeFrom(metadata);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Metadata m23593getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Metadata> getParserForType() {
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

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Metadata();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected MapField internalGetMapField(int i) {
        if (i == 1) {
            return internalGetFilterMetadata();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BaseProto.internal_static_envoy_config_core_v3_Metadata_fieldAccessorTable.ensureFieldAccessorsInitialized(Metadata.class, Builder.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, Struct> internalGetFilterMetadata() {
        MapField<String, Struct> mapField = this.filterMetadata_;
        return mapField == null ? MapField.emptyMapField(FilterMetadataDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder
    public int getFilterMetadataCount() {
        return internalGetFilterMetadata().getMap().size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder
    public boolean containsFilterMetadata(String str) {
        str.getClass();
        return internalGetFilterMetadata().getMap().containsKey(str);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder
    @Deprecated
    public Map<String, Struct> getFilterMetadata() {
        return getFilterMetadataMap();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder
    public Map<String, Struct> getFilterMetadataMap() {
        return internalGetFilterMetadata().getMap();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder
    public Struct getFilterMetadataOrDefault(String str, Struct struct) {
        str.getClass();
        Map map = internalGetFilterMetadata().getMap();
        return map.containsKey(str) ? (Struct) map.get(str) : struct;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder
    public Struct getFilterMetadataOrThrow(String str) {
        str.getClass();
        Map map = internalGetFilterMetadata().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return (Struct) map.get(str);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetFilterMetadata(), FilterMetadataDefaultEntryHolder.defaultEntry, 1);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (Map.Entry entry : internalGetFilterMetadata().getMap().entrySet()) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, FilterMetadataDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Metadata)) {
            return super.equals(obj);
        }
        Metadata metadata = (Metadata) obj;
        return internalGetFilterMetadata().equals(metadata.internalGetFilterMetadata()) && this.unknownFields.equals(metadata.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (!internalGetFilterMetadata().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + internalGetFilterMetadata().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m23595newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m23598toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    private static final class FilterMetadataDefaultEntryHolder {
        static final MapEntry<String, Struct> defaultEntry = MapEntry.newDefaultInstance(BaseProto.internal_static_envoy_config_core_v3_Metadata_FilterMetadataEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Struct.getDefaultInstance());

        private FilterMetadataDefaultEntryHolder() {
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetadataOrBuilder {
        private int bitField0_;
        private MapField<String, Struct> filterMetadata_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BaseProto.internal_static_envoy_config_core_v3_Metadata_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 1) {
                return internalGetFilterMetadata();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected MapField internalGetMutableMapField(int i) {
            if (i == 1) {
                return internalGetMutableFilterMetadata();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BaseProto.internal_static_envoy_config_core_v3_Metadata_fieldAccessorTable.ensureFieldAccessorsInitialized(Metadata.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Metadata.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23609clear() {
            super.clear();
            internalGetMutableFilterMetadata().clear();
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BaseProto.internal_static_envoy_config_core_v3_Metadata_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Metadata m23622getDefaultInstanceForType() {
            return Metadata.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Metadata m23603build() throws UninitializedMessageException {
            Metadata metadataM23605buildPartial = m23605buildPartial();
            if (metadataM23605buildPartial.isInitialized()) {
                return metadataM23605buildPartial;
            }
            throw newUninitializedMessageException(metadataM23605buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Metadata m23605buildPartial() {
            Metadata metadata = new Metadata(this);
            metadata.filterMetadata_ = internalGetFilterMetadata();
            metadata.filterMetadata_.makeImmutable();
            onBuilt();
            return metadata;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23621clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23633setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23611clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23614clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23635setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23601addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23626mergeFrom(Message message) {
            if (message instanceof Metadata) {
                return mergeFrom((Metadata) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Metadata metadata) {
            if (metadata == Metadata.getDefaultInstance()) {
                return this;
            }
            internalGetMutableFilterMetadata().mergeFrom(metadata.internalGetFilterMetadata());
            m23631mergeUnknownFields(metadata.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Metadata.Builder m23627mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Metadata.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Metadata r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Metadata) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Metadata r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Metadata) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Metadata.Builder.m23627mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Metadata$Builder");
        }

        private MapField<String, Struct> internalGetFilterMetadata() {
            MapField<String, Struct> mapField = this.filterMetadata_;
            return mapField == null ? MapField.emptyMapField(FilterMetadataDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, Struct> internalGetMutableFilterMetadata() {
            onChanged();
            if (this.filterMetadata_ == null) {
                this.filterMetadata_ = MapField.newMapField(FilterMetadataDefaultEntryHolder.defaultEntry);
            }
            if (!this.filterMetadata_.isMutable()) {
                this.filterMetadata_ = this.filterMetadata_.copy();
            }
            return this.filterMetadata_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder
        public int getFilterMetadataCount() {
            return internalGetFilterMetadata().getMap().size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder
        public boolean containsFilterMetadata(String str) {
            str.getClass();
            return internalGetFilterMetadata().getMap().containsKey(str);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder
        @Deprecated
        public Map<String, Struct> getFilterMetadata() {
            return getFilterMetadataMap();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder
        public Map<String, Struct> getFilterMetadataMap() {
            return internalGetFilterMetadata().getMap();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder
        public Struct getFilterMetadataOrDefault(String str, Struct struct) {
            str.getClass();
            Map map = internalGetFilterMetadata().getMap();
            return map.containsKey(str) ? (Struct) map.get(str) : struct;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder
        public Struct getFilterMetadataOrThrow(String str) {
            str.getClass();
            Map map = internalGetFilterMetadata().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return (Struct) map.get(str);
        }

        public Builder clearFilterMetadata() {
            internalGetMutableFilterMetadata().getMutableMap().clear();
            return this;
        }

        public Builder removeFilterMetadata(String str) {
            str.getClass();
            internalGetMutableFilterMetadata().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, Struct> getMutableFilterMetadata() {
            return internalGetMutableFilterMetadata().getMutableMap();
        }

        public Builder putFilterMetadata(String str, Struct struct) {
            str.getClass();
            struct.getClass();
            internalGetMutableFilterMetadata().getMutableMap().put(str, struct);
            return this;
        }

        public Builder putAllFilterMetadata(Map<String, Struct> map) {
            internalGetMutableFilterMetadata().getMutableMap().putAll(map);
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m23637setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m23631mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
