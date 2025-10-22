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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/* loaded from: classes.dex */
public final class MonitoredResourceMetadata extends GeneratedMessageV3 implements MonitoredResourceMetadataOrBuilder {
    public static final int SYSTEM_LABELS_FIELD_NUMBER = 1;
    public static final int USER_LABELS_FIELD_NUMBER = 2;
    private static final MonitoredResourceMetadata DEFAULT_INSTANCE = new MonitoredResourceMetadata();
    private static final Parser<MonitoredResourceMetadata> PARSER = new AbstractParser<MonitoredResourceMetadata>() { // from class: com.google.api.MonitoredResourceMetadata.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public MonitoredResourceMetadata m1920parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MonitoredResourceMetadata(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private Struct systemLabels_;
    private MapField<String, String> userLabels_;

    private MonitoredResourceMetadata(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private MonitoredResourceMetadata() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private MonitoredResourceMetadata(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag == 10) {
                                Struct struct = this.systemLabels_;
                                Struct.Builder builder = struct != null ? struct.toBuilder() : null;
                                Struct message = codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                                this.systemLabels_ = message;
                                if (builder != null) {
                                    builder.mergeFrom(message);
                                    this.systemLabels_ = builder.buildPartial();
                                }
                            } else if (tag == 18) {
                                if ((i & 2) == 0) {
                                    this.userLabels_ = MapField.newMapField(UserLabelsDefaultEntryHolder.defaultEntry);
                                    i |= 2;
                                }
                                MapEntry message2 = codedInputStream.readMessage(UserLabelsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.userLabels_.getMutableMap().put(message2.getKey(), message2.getValue());
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
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
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static MonitoredResourceMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MonitoredResourceMetadata> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_descriptor;
    }

    public static MonitoredResourceMetadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MonitoredResourceMetadata) PARSER.parseFrom(byteBuffer);
    }

    public static MonitoredResourceMetadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MonitoredResourceMetadata) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MonitoredResourceMetadata) PARSER.parseFrom(byteString);
    }

    public static MonitoredResourceMetadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MonitoredResourceMetadata) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MonitoredResourceMetadata) PARSER.parseFrom(bArr);
    }

    public static MonitoredResourceMetadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MonitoredResourceMetadata) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MonitoredResourceMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MonitoredResourceMetadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MonitoredResourceMetadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m1919toBuilder();
    }

    public static Builder newBuilder(MonitoredResourceMetadata monitoredResourceMetadata) {
        return DEFAULT_INSTANCE.m1919toBuilder().mergeFrom(monitoredResourceMetadata);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public MonitoredResourceMetadata m1914getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<MonitoredResourceMetadata> getParserForType() {
        return PARSER;
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public boolean hasSystemLabels() {
        return this.systemLabels_ != null;
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
            return internalGetUserLabels();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoredResourceMetadata.class, Builder.class);
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public Struct getSystemLabels() {
        Struct struct = this.systemLabels_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public StructOrBuilder getSystemLabelsOrBuilder() {
        return getSystemLabels();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, String> internalGetUserLabels() {
        MapField<String, String> mapField = this.userLabels_;
        return mapField == null ? MapField.emptyMapField(UserLabelsDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public int getUserLabelsCount() {
        return internalGetUserLabels().getMap().size();
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public boolean containsUserLabels(String str) {
        str.getClass();
        return internalGetUserLabels().getMap().containsKey(str);
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    @Deprecated
    public Map<String, String> getUserLabels() {
        return getUserLabelsMap();
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public Map<String, String> getUserLabelsMap() {
        return internalGetUserLabels().getMap();
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public String getUserLabelsOrDefault(String str, String str2) {
        str.getClass();
        Map map = internalGetUserLabels().getMap();
        return map.containsKey(str) ? (String) map.get(str) : str2;
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public String getUserLabelsOrThrow(String str) {
        str.getClass();
        Map map = internalGetUserLabels().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return (String) map.get(str);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.systemLabels_ != null) {
            codedOutputStream.writeMessage(1, getSystemLabels());
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetUserLabels(), UserLabelsDefaultEntryHolder.defaultEntry, 2);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.systemLabels_ != null ? CodedOutputStream.computeMessageSize(1, getSystemLabels()) : 0;
        for (Map.Entry entry : internalGetUserLabels().getMap().entrySet()) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, UserLabelsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MonitoredResourceMetadata)) {
            return super.equals(obj);
        }
        MonitoredResourceMetadata monitoredResourceMetadata = (MonitoredResourceMetadata) obj;
        if (hasSystemLabels() != monitoredResourceMetadata.hasSystemLabels()) {
            return false;
        }
        return (!hasSystemLabels() || getSystemLabels().equals(monitoredResourceMetadata.getSystemLabels())) && internalGetUserLabels().equals(monitoredResourceMetadata.internalGetUserLabels()) && this.unknownFields.equals(monitoredResourceMetadata.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasSystemLabels()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getSystemLabels().hashCode();
        }
        if (!internalGetUserLabels().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + internalGetUserLabels().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m1917newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m1919toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1916newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    private static final class UserLabelsDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_UserLabelsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private UserLabelsDefaultEntryHolder() {
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MonitoredResourceMetadataOrBuilder {
        private int bitField0_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> systemLabelsBuilder_;
        private Struct systemLabels_;
        private MapField<String, String> userLabels_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_descriptor;
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public boolean hasSystemLabels() {
            return (this.systemLabelsBuilder_ == null && this.systemLabels_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 2) {
                return internalGetUserLabels();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected MapField internalGetMutableMapField(int i) {
            if (i == 2) {
                return internalGetMutableUserLabels();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoredResourceMetadata.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = MonitoredResourceMetadata.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1930clear() {
            super.clear();
            if (this.systemLabelsBuilder_ == null) {
                this.systemLabels_ = null;
            } else {
                this.systemLabels_ = null;
                this.systemLabelsBuilder_ = null;
            }
            internalGetMutableUserLabels().clear();
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MonitoredResourceMetadata m1943getDefaultInstanceForType() {
            return MonitoredResourceMetadata.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MonitoredResourceMetadata m1924build() throws UninitializedMessageException {
            MonitoredResourceMetadata monitoredResourceMetadataM1926buildPartial = m1926buildPartial();
            if (monitoredResourceMetadataM1926buildPartial.isInitialized()) {
                return monitoredResourceMetadataM1926buildPartial;
            }
            throw newUninitializedMessageException(monitoredResourceMetadataM1926buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MonitoredResourceMetadata m1926buildPartial() {
            MonitoredResourceMetadata monitoredResourceMetadata = new MonitoredResourceMetadata(this);
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 == null) {
                monitoredResourceMetadata.systemLabels_ = this.systemLabels_;
            } else {
                monitoredResourceMetadata.systemLabels_ = singleFieldBuilderV3.build();
            }
            monitoredResourceMetadata.userLabels_ = internalGetUserLabels();
            monitoredResourceMetadata.userLabels_.makeImmutable();
            monitoredResourceMetadata.bitField0_ = 0;
            onBuilt();
            return monitoredResourceMetadata;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1941clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1954setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1932clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1935clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1956setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1922addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1948mergeFrom(Message message) {
            if (message instanceof MonitoredResourceMetadata) {
                return mergeFrom((MonitoredResourceMetadata) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MonitoredResourceMetadata monitoredResourceMetadata) {
            if (monitoredResourceMetadata == MonitoredResourceMetadata.getDefaultInstance()) {
                return this;
            }
            if (monitoredResourceMetadata.hasSystemLabels()) {
                mergeSystemLabels(monitoredResourceMetadata.getSystemLabels());
            }
            internalGetMutableUserLabels().mergeFrom(monitoredResourceMetadata.internalGetUserLabels());
            m1952mergeUnknownFields(monitoredResourceMetadata.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.api.MonitoredResourceMetadata.Builder m1949mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.MonitoredResourceMetadata.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.MonitoredResourceMetadata r3 = (com.google.api.MonitoredResourceMetadata) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.MonitoredResourceMetadata r4 = (com.google.api.MonitoredResourceMetadata) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.MonitoredResourceMetadata.Builder.m1949mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.MonitoredResourceMetadata$Builder");
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public Struct getSystemLabels() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Struct struct = this.systemLabels_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        public Builder setSystemLabels(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 == null) {
                struct.getClass();
                this.systemLabels_ = struct;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(struct);
            }
            return this;
        }

        public Builder setSystemLabels(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.systemLabels_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSystemLabels(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.systemLabels_;
                if (struct2 != null) {
                    this.systemLabels_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.systemLabels_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearSystemLabels() {
            if (this.systemLabelsBuilder_ == null) {
                this.systemLabels_ = null;
                onChanged();
            } else {
                this.systemLabels_ = null;
                this.systemLabelsBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getSystemLabelsBuilder() {
            onChanged();
            return getSystemLabelsFieldBuilder().getBuilder();
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public StructOrBuilder getSystemLabelsOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.systemLabels_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getSystemLabelsFieldBuilder() {
            if (this.systemLabelsBuilder_ == null) {
                this.systemLabelsBuilder_ = new SingleFieldBuilderV3<>(getSystemLabels(), getParentForChildren(), isClean());
                this.systemLabels_ = null;
            }
            return this.systemLabelsBuilder_;
        }

        private MapField<String, String> internalGetUserLabels() {
            MapField<String, String> mapField = this.userLabels_;
            return mapField == null ? MapField.emptyMapField(UserLabelsDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, String> internalGetMutableUserLabels() {
            onChanged();
            if (this.userLabels_ == null) {
                this.userLabels_ = MapField.newMapField(UserLabelsDefaultEntryHolder.defaultEntry);
            }
            if (!this.userLabels_.isMutable()) {
                this.userLabels_ = this.userLabels_.copy();
            }
            return this.userLabels_;
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public int getUserLabelsCount() {
            return internalGetUserLabels().getMap().size();
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public boolean containsUserLabels(String str) {
            str.getClass();
            return internalGetUserLabels().getMap().containsKey(str);
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        @Deprecated
        public Map<String, String> getUserLabels() {
            return getUserLabelsMap();
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public Map<String, String> getUserLabelsMap() {
            return internalGetUserLabels().getMap();
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public String getUserLabelsOrDefault(String str, String str2) {
            str.getClass();
            Map map = internalGetUserLabels().getMap();
            return map.containsKey(str) ? (String) map.get(str) : str2;
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public String getUserLabelsOrThrow(String str) {
            str.getClass();
            Map map = internalGetUserLabels().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return (String) map.get(str);
        }

        public Builder clearUserLabels() {
            internalGetMutableUserLabels().getMutableMap().clear();
            return this;
        }

        public Builder removeUserLabels(String str) {
            str.getClass();
            internalGetMutableUserLabels().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, String> getMutableUserLabels() {
            return internalGetMutableUserLabels().getMutableMap();
        }

        public Builder putUserLabels(String str, String str2) {
            str.getClass();
            str2.getClass();
            internalGetMutableUserLabels().getMutableMap().put(str, str2);
            return this;
        }

        public Builder putAllUserLabels(Map<String, String> map) {
            internalGetMutableUserLabels().getMutableMap().putAll(map);
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1958setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1952mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
