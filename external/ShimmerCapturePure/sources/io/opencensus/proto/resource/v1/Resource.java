package io.opencensus.proto.resource.v1;

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

/* loaded from: classes4.dex */
public final class Resource extends GeneratedMessageV3 implements ResourceOrBuilder {
    public static final int LABELS_FIELD_NUMBER = 2;
    public static final int TYPE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final Resource DEFAULT_INSTANCE = new Resource();
    private static final Parser<Resource> PARSER = new AbstractParser<Resource>() { // from class: io.opencensus.proto.resource.v1.Resource.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Resource m37425parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Resource(codedInputStream, extensionRegistryLite);
        }
    };
    private int bitField0_;
    private MapField<String, String> labels_;
    private byte memoizedIsInitialized;
    private volatile Object type_;

    private Resource(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Resource() {
        this.memoizedIsInitialized = (byte) -1;
        this.type_ = "";
    }

    private Resource(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.type_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag != 18) {
                            if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            if ((i & 2) != 2) {
                                this.labels_ = MapField.newMapField(LabelsDefaultEntryHolder.defaultEntry);
                                i |= 2;
                            }
                            MapEntry message = codedInputStream.readMessage(LabelsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                            this.labels_.getMutableMap().put(message.getKey(), message.getValue());
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

    public static Resource getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Resource> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ResourceProto.internal_static_opencensus_proto_resource_v1_Resource_descriptor;
    }

    public static Resource parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Resource) PARSER.parseFrom(byteBuffer);
    }

    public static Resource parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Resource) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Resource parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Resource) PARSER.parseFrom(byteString);
    }

    public static Resource parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Resource) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Resource parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Resource) PARSER.parseFrom(bArr);
    }

    public static Resource parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Resource) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Resource parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Resource parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Resource parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Resource parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Resource parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Resource parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m37423toBuilder();
    }

    public static Builder newBuilder(Resource resource) {
        return DEFAULT_INSTANCE.m37423toBuilder().mergeFrom(resource);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Resource m37418getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Resource> getParserForType() {
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
            return internalGetLabels();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ResourceProto.internal_static_opencensus_proto_resource_v1_Resource_fieldAccessorTable.ensureFieldAccessorsInitialized(Resource.class, Builder.class);
    }

    @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
    public String getType() {
        Object obj = this.type_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.type_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
    public ByteString getTypeBytes() {
        Object obj = this.type_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.type_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, String> internalGetLabels() {
        MapField<String, String> mapField = this.labels_;
        return mapField == null ? MapField.emptyMapField(LabelsDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
    public int getLabelsCount() {
        return internalGetLabels().getMap().size();
    }

    @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
    public boolean containsLabels(String str) {
        str.getClass();
        return internalGetLabels().getMap().containsKey(str);
    }

    @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
    @Deprecated
    public Map<String, String> getLabels() {
        return getLabelsMap();
    }

    @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
    public Map<String, String> getLabelsMap() {
        return internalGetLabels().getMap();
    }

    @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
    public String getLabelsOrDefault(String str, String str2) {
        str.getClass();
        Map map = internalGetLabels().getMap();
        return map.containsKey(str) ? (String) map.get(str) : str2;
    }

    @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
    public String getLabelsOrThrow(String str) {
        str.getClass();
        Map map = internalGetLabels().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return (String) map.get(str);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.type_);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetLabels(), LabelsDefaultEntryHolder.defaultEntry, 2);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getTypeBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.type_) : 0;
        for (Map.Entry entry : internalGetLabels().getMap().entrySet()) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, LabelsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Resource)) {
            return super.equals(obj);
        }
        Resource resource = (Resource) obj;
        return getType().equals(resource.getType()) && internalGetLabels().equals(resource.internalGetLabels()) && this.unknownFields.equals(resource.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getType().hashCode();
        if (!internalGetLabels().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + internalGetLabels().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37420newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37423toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    private static final class LabelsDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(ResourceProto.internal_static_opencensus_proto_resource_v1_Resource_LabelsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private LabelsDefaultEntryHolder() {
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ResourceOrBuilder {
        private int bitField0_;
        private MapField<String, String> labels_;
        private Object type_;

        private Builder() {
            this.type_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.type_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ResourceProto.internal_static_opencensus_proto_resource_v1_Resource_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 2) {
                return internalGetLabels();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected MapField internalGetMutableMapField(int i) {
            if (i == 2) {
                return internalGetMutableLabels();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ResourceProto.internal_static_opencensus_proto_resource_v1_Resource_fieldAccessorTable.ensureFieldAccessorsInitialized(Resource.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Resource.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37434clear() {
            super.clear();
            this.type_ = "";
            internalGetMutableLabels().clear();
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ResourceProto.internal_static_opencensus_proto_resource_v1_Resource_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Resource m37447getDefaultInstanceForType() {
            return Resource.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Resource m37428build() throws UninitializedMessageException {
            Resource resourceM37430buildPartial = m37430buildPartial();
            if (resourceM37430buildPartial.isInitialized()) {
                return resourceM37430buildPartial;
            }
            throw newUninitializedMessageException(resourceM37430buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Resource m37430buildPartial() {
            Resource resource = new Resource(this);
            resource.type_ = this.type_;
            resource.labels_ = internalGetLabels();
            resource.labels_.makeImmutable();
            resource.bitField0_ = 0;
            onBuilt();
            return resource;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37446clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37458setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37436clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37439clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37460setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37426addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37451mergeFrom(Message message) {
            if (message instanceof Resource) {
                return mergeFrom((Resource) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Resource resource) {
            if (resource == Resource.getDefaultInstance()) {
                return this;
            }
            if (!resource.getType().isEmpty()) {
                this.type_ = resource.type_;
                onChanged();
            }
            internalGetMutableLabels().mergeFrom(resource.internalGetLabels());
            m37456mergeUnknownFields(resource.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.resource.v1.Resource.Builder m37452mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.resource.v1.Resource.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.resource.v1.Resource r3 = (io.opencensus.proto.resource.v1.Resource) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.resource.v1.Resource r4 = (io.opencensus.proto.resource.v1.Resource) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.resource.v1.Resource.Builder.m37452mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.resource.v1.Resource$Builder");
        }

        @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
        public String getType() {
            Object obj = this.type_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.type_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setType(String str) {
            str.getClass();
            this.type_ = str;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
        public ByteString getTypeBytes() {
            Object obj = this.type_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.type_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setTypeBytes(ByteString byteString) {
            byteString.getClass();
            Resource.checkByteStringIsUtf8(byteString);
            this.type_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearType() {
            this.type_ = Resource.getDefaultInstance().getType();
            onChanged();
            return this;
        }

        private MapField<String, String> internalGetLabels() {
            MapField<String, String> mapField = this.labels_;
            return mapField == null ? MapField.emptyMapField(LabelsDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, String> internalGetMutableLabels() {
            onChanged();
            if (this.labels_ == null) {
                this.labels_ = MapField.newMapField(LabelsDefaultEntryHolder.defaultEntry);
            }
            if (!this.labels_.isMutable()) {
                this.labels_ = this.labels_.copy();
            }
            return this.labels_;
        }

        @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
        public int getLabelsCount() {
            return internalGetLabels().getMap().size();
        }

        @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
        public boolean containsLabels(String str) {
            str.getClass();
            return internalGetLabels().getMap().containsKey(str);
        }

        @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
        public Map<String, String> getLabelsMap() {
            return internalGetLabels().getMap();
        }

        @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
        public String getLabelsOrDefault(String str, String str2) {
            str.getClass();
            Map map = internalGetLabels().getMap();
            return map.containsKey(str) ? (String) map.get(str) : str2;
        }

        @Override // io.opencensus.proto.resource.v1.ResourceOrBuilder
        public String getLabelsOrThrow(String str) {
            str.getClass();
            Map map = internalGetLabels().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return (String) map.get(str);
        }

        public Builder clearLabels() {
            internalGetMutableLabels().getMutableMap().clear();
            return this;
        }

        public Builder removeLabels(String str) {
            str.getClass();
            internalGetMutableLabels().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, String> getMutableLabels() {
            return internalGetMutableLabels().getMutableMap();
        }

        public Builder putLabels(String str, String str2) {
            str.getClass();
            str2.getClass();
            internalGetMutableLabels().getMutableMap().put(str, str2);
            return this;
        }

        public Builder putAllLabels(Map<String, String> map) {
            internalGetMutableLabels().getMutableMap().putAll(map);
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37462setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37456mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
