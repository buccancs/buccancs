package io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.CollectionEntry;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.CollectionEntryOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class ClusterCollection extends GeneratedMessageV3 implements ClusterCollectionOrBuilder {
    public static final int ENTRIES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final ClusterCollection DEFAULT_INSTANCE = new ClusterCollection();
    private static final Parser<ClusterCollection> PARSER = new AbstractParser<ClusterCollection>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollection.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ClusterCollection m21062parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ClusterCollection(codedInputStream, extensionRegistryLite);
        }
    };
    private CollectionEntry entries_;
    private byte memoizedIsInitialized;

    private ClusterCollection(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ClusterCollection() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private ClusterCollection(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag == 10) {
                                CollectionEntry collectionEntry = this.entries_;
                                CollectionEntry.Builder builderM10082toBuilder = collectionEntry != null ? collectionEntry.m10082toBuilder() : null;
                                CollectionEntry collectionEntry2 = (CollectionEntry) codedInputStream.readMessage(CollectionEntry.parser(), extensionRegistryLite);
                                this.entries_ = collectionEntry2;
                                if (builderM10082toBuilder != null) {
                                    builderM10082toBuilder.mergeFrom(collectionEntry2);
                                    this.entries_ = builderM10082toBuilder.m10089buildPartial();
                                }
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

    public static ClusterCollection getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ClusterCollection> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ClusterProto.internal_static_envoy_config_cluster_v3_ClusterCollection_descriptor;
    }

    public static ClusterCollection parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ClusterCollection) PARSER.parseFrom(byteBuffer);
    }

    public static ClusterCollection parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ClusterCollection) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ClusterCollection parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ClusterCollection) PARSER.parseFrom(byteString);
    }

    public static ClusterCollection parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ClusterCollection) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ClusterCollection parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ClusterCollection) PARSER.parseFrom(bArr);
    }

    public static ClusterCollection parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ClusterCollection) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ClusterCollection parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ClusterCollection parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ClusterCollection parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ClusterCollection parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ClusterCollection parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ClusterCollection parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m21060toBuilder();
    }

    public static Builder newBuilder(ClusterCollection clusterCollection) {
        return DEFAULT_INSTANCE.m21060toBuilder().mergeFrom(clusterCollection);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ClusterCollection m21055getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ClusterCollection> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollectionOrBuilder
    public boolean hasEntries() {
        return this.entries_ != null;
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
        return new ClusterCollection();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ClusterProto.internal_static_envoy_config_cluster_v3_ClusterCollection_fieldAccessorTable.ensureFieldAccessorsInitialized(ClusterCollection.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollectionOrBuilder
    public CollectionEntry getEntries() {
        CollectionEntry collectionEntry = this.entries_;
        return collectionEntry == null ? CollectionEntry.getDefaultInstance() : collectionEntry;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollectionOrBuilder
    public CollectionEntryOrBuilder getEntriesOrBuilder() {
        return getEntries();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.entries_ != null) {
            codedOutputStream.writeMessage(1, getEntries());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = (this.entries_ != null ? CodedOutputStream.computeMessageSize(1, getEntries()) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeMessageSize;
        return iComputeMessageSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClusterCollection)) {
            return super.equals(obj);
        }
        ClusterCollection clusterCollection = (ClusterCollection) obj;
        if (hasEntries() != clusterCollection.hasEntries()) {
            return false;
        }
        return (!hasEntries() || getEntries().equals(clusterCollection.getEntries())) && this.unknownFields.equals(clusterCollection.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasEntries()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getEntries().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m21057newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m21060toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    /* loaded from: classes6.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ClusterCollectionOrBuilder {
        private SingleFieldBuilderV3<CollectionEntry, CollectionEntry.Builder, CollectionEntryOrBuilder> entriesBuilder_;
        private CollectionEntry entries_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ClusterProto.internal_static_envoy_config_cluster_v3_ClusterCollection_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollectionOrBuilder
        public boolean hasEntries() {
            return (this.entriesBuilder_ == null && this.entries_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ClusterProto.internal_static_envoy_config_cluster_v3_ClusterCollection_fieldAccessorTable.ensureFieldAccessorsInitialized(ClusterCollection.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ClusterCollection.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21071clear() {
            super.clear();
            if (this.entriesBuilder_ == null) {
                this.entries_ = null;
            } else {
                this.entries_ = null;
                this.entriesBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ClusterProto.internal_static_envoy_config_cluster_v3_ClusterCollection_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ClusterCollection m21084getDefaultInstanceForType() {
            return ClusterCollection.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ClusterCollection m21065build() throws UninitializedMessageException {
            ClusterCollection clusterCollectionM21067buildPartial = m21067buildPartial();
            if (clusterCollectionM21067buildPartial.isInitialized()) {
                return clusterCollectionM21067buildPartial;
            }
            throw newUninitializedMessageException(clusterCollectionM21067buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ClusterCollection m21067buildPartial() {
            ClusterCollection clusterCollection = new ClusterCollection(this);
            SingleFieldBuilderV3<CollectionEntry, CollectionEntry.Builder, CollectionEntryOrBuilder> singleFieldBuilderV3 = this.entriesBuilder_;
            if (singleFieldBuilderV3 == null) {
                clusterCollection.entries_ = this.entries_;
            } else {
                clusterCollection.entries_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return clusterCollection;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21083clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21095setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21073clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21076clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21097setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21063addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21088mergeFrom(Message message) {
            if (message instanceof ClusterCollection) {
                return mergeFrom((ClusterCollection) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ClusterCollection clusterCollection) {
            if (clusterCollection == ClusterCollection.getDefaultInstance()) {
                return this;
            }
            if (clusterCollection.hasEntries()) {
                mergeEntries(clusterCollection.getEntries());
            }
            m21093mergeUnknownFields(clusterCollection.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollection.Builder m21089mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollection.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollection r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollection) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollection r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollection) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollection.Builder.m21089mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollection$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollectionOrBuilder
        public CollectionEntry getEntries() {
            SingleFieldBuilderV3<CollectionEntry, CollectionEntry.Builder, CollectionEntryOrBuilder> singleFieldBuilderV3 = this.entriesBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            CollectionEntry collectionEntry = this.entries_;
            return collectionEntry == null ? CollectionEntry.getDefaultInstance() : collectionEntry;
        }

        public Builder setEntries(CollectionEntry collectionEntry) {
            SingleFieldBuilderV3<CollectionEntry, CollectionEntry.Builder, CollectionEntryOrBuilder> singleFieldBuilderV3 = this.entriesBuilder_;
            if (singleFieldBuilderV3 == null) {
                collectionEntry.getClass();
                this.entries_ = collectionEntry;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(collectionEntry);
            }
            return this;
        }

        public Builder setEntries(CollectionEntry.Builder builder) {
            SingleFieldBuilderV3<CollectionEntry, CollectionEntry.Builder, CollectionEntryOrBuilder> singleFieldBuilderV3 = this.entriesBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.entries_ = builder.m10087build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m10087build());
            }
            return this;
        }

        public Builder mergeEntries(CollectionEntry collectionEntry) {
            SingleFieldBuilderV3<CollectionEntry, CollectionEntry.Builder, CollectionEntryOrBuilder> singleFieldBuilderV3 = this.entriesBuilder_;
            if (singleFieldBuilderV3 == null) {
                CollectionEntry collectionEntry2 = this.entries_;
                if (collectionEntry2 != null) {
                    this.entries_ = CollectionEntry.newBuilder(collectionEntry2).mergeFrom(collectionEntry).m10089buildPartial();
                } else {
                    this.entries_ = collectionEntry;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(collectionEntry);
            }
            return this;
        }

        public Builder clearEntries() {
            if (this.entriesBuilder_ == null) {
                this.entries_ = null;
                onChanged();
            } else {
                this.entries_ = null;
                this.entriesBuilder_ = null;
            }
            return this;
        }

        public CollectionEntry.Builder getEntriesBuilder() {
            onChanged();
            return getEntriesFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.ClusterCollectionOrBuilder
        public CollectionEntryOrBuilder getEntriesOrBuilder() {
            SingleFieldBuilderV3<CollectionEntry, CollectionEntry.Builder, CollectionEntryOrBuilder> singleFieldBuilderV3 = this.entriesBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (CollectionEntryOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            CollectionEntry collectionEntry = this.entries_;
            return collectionEntry == null ? CollectionEntry.getDefaultInstance() : collectionEntry;
        }

        private SingleFieldBuilderV3<CollectionEntry, CollectionEntry.Builder, CollectionEntryOrBuilder> getEntriesFieldBuilder() {
            if (this.entriesBuilder_ == null) {
                this.entriesBuilder_ = new SingleFieldBuilderV3<>(getEntries(), getParentForChildren(), isClean());
                this.entries_ = null;
            }
            return this.entriesBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m21099setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m21093mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
