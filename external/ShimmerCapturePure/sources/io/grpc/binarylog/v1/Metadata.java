package io.grpc.binarylog.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.binarylog.v1.MetadataEntry;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class Metadata extends GeneratedMessageV3 implements MetadataOrBuilder {
    public static final int ENTRY_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final Metadata DEFAULT_INSTANCE = new Metadata();
    private static final Parser<Metadata> PARSER = new AbstractParser<Metadata>() { // from class: io.grpc.binarylog.v1.Metadata.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Metadata m7124parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Metadata(codedInputStream, extensionRegistryLite);
        }
    };
    private List<MetadataEntry> entry_;
    private byte memoizedIsInitialized;

    private Metadata(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Metadata() {
        this.memoizedIsInitialized = (byte) -1;
        this.entry_ = Collections.emptyList();
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
                                this.entry_ = new ArrayList();
                                z2 |= true;
                            }
                            this.entry_.add(codedInputStream.readMessage(MetadataEntry.parser(), extensionRegistryLite));
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
                if (z2 & true) {
                    this.entry_ = Collections.unmodifiableList(this.entry_);
                }
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
        return BinaryLogProto.internal_static_grpc_binarylog_v1_Metadata_descriptor;
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
        return DEFAULT_INSTANCE.m7122toBuilder();
    }

    public static Builder newBuilder(Metadata metadata) {
        return DEFAULT_INSTANCE.m7122toBuilder().mergeFrom(metadata);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Metadata m7117getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.binarylog.v1.MetadataOrBuilder
    public List<MetadataEntry> getEntryList() {
        return this.entry_;
    }

    @Override // io.grpc.binarylog.v1.MetadataOrBuilder
    public List<? extends MetadataEntryOrBuilder> getEntryOrBuilderList() {
        return this.entry_;
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

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BinaryLogProto.internal_static_grpc_binarylog_v1_Metadata_fieldAccessorTable.ensureFieldAccessorsInitialized(Metadata.class, Builder.class);
    }

    @Override // io.grpc.binarylog.v1.MetadataOrBuilder
    public int getEntryCount() {
        return this.entry_.size();
    }

    @Override // io.grpc.binarylog.v1.MetadataOrBuilder
    public MetadataEntry getEntry(int i) {
        return this.entry_.get(i);
    }

    @Override // io.grpc.binarylog.v1.MetadataOrBuilder
    public MetadataEntryOrBuilder getEntryOrBuilder(int i) {
        return this.entry_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.entry_.size(); i++) {
            codedOutputStream.writeMessage(1, this.entry_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.entry_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.entry_.get(i2));
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
        return getEntryList().equals(metadata.getEntryList()) && this.unknownFields.equals(metadata.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getEntryCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getEntryList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m7119newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m7122toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetadataOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> entryBuilder_;
        private List<MetadataEntry> entry_;

        private Builder() {
            this.entry_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.entry_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_Metadata_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_Metadata_fieldAccessorTable.ensureFieldAccessorsInitialized(Metadata.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (Metadata.alwaysUseFieldBuilders) {
                getEntryFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7133clear() {
            super.clear();
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.entry_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_Metadata_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Metadata m7146getDefaultInstanceForType() {
            return Metadata.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Metadata m7127build() throws UninitializedMessageException {
            Metadata metadataM7129buildPartial = m7129buildPartial();
            if (metadataM7129buildPartial.isInitialized()) {
                return metadataM7129buildPartial;
            }
            throw newUninitializedMessageException(metadataM7129buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Metadata m7129buildPartial() {
            Metadata metadata = new Metadata(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.entry_ = Collections.unmodifiableList(this.entry_);
                    this.bitField0_ &= -2;
                }
                metadata.entry_ = this.entry_;
            } else {
                metadata.entry_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return metadata;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7145clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7157setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7135clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7138clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7159setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7125addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7150mergeFrom(com.google.protobuf.Message message) {
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
            if (this.entryBuilder_ == null) {
                if (!metadata.entry_.isEmpty()) {
                    if (this.entry_.isEmpty()) {
                        this.entry_ = metadata.entry_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureEntryIsMutable();
                        this.entry_.addAll(metadata.entry_);
                    }
                    onChanged();
                }
            } else if (!metadata.entry_.isEmpty()) {
                if (!this.entryBuilder_.isEmpty()) {
                    this.entryBuilder_.addAllMessages(metadata.entry_);
                } else {
                    this.entryBuilder_.dispose();
                    this.entryBuilder_ = null;
                    this.entry_ = metadata.entry_;
                    this.bitField0_ &= -2;
                    this.entryBuilder_ = Metadata.alwaysUseFieldBuilders ? getEntryFieldBuilder() : null;
                }
            }
            m7155mergeUnknownFields(metadata.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.binarylog.v1.Metadata.Builder m7151mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.binarylog.v1.Metadata.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.binarylog.v1.Metadata r3 = (io.grpc.binarylog.v1.Metadata) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.binarylog.v1.Metadata r4 = (io.grpc.binarylog.v1.Metadata) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.binarylog.v1.Metadata.Builder.m7151mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.binarylog.v1.Metadata$Builder");
        }

        private void ensureEntryIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.entry_ = new ArrayList(this.entry_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.binarylog.v1.MetadataOrBuilder
        public List<MetadataEntry> getEntryList() {
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.entry_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.binarylog.v1.MetadataOrBuilder
        public int getEntryCount() {
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entry_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.binarylog.v1.MetadataOrBuilder
        public MetadataEntry getEntry(int i) {
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entry_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setEntry(int i, MetadataEntry metadataEntry) {
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                metadataEntry.getClass();
                ensureEntryIsMutable();
                this.entry_.set(i, metadataEntry);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, metadataEntry);
            }
            return this;
        }

        public Builder setEntry(int i, MetadataEntry.Builder builder) {
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntryIsMutable();
                this.entry_.set(i, builder.m7173build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m7173build());
            }
            return this;
        }

        public Builder addEntry(MetadataEntry metadataEntry) {
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                metadataEntry.getClass();
                ensureEntryIsMutable();
                this.entry_.add(metadataEntry);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(metadataEntry);
            }
            return this;
        }

        public Builder addEntry(int i, MetadataEntry metadataEntry) {
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                metadataEntry.getClass();
                ensureEntryIsMutable();
                this.entry_.add(i, metadataEntry);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, metadataEntry);
            }
            return this;
        }

        public Builder addEntry(MetadataEntry.Builder builder) {
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntryIsMutable();
                this.entry_.add(builder.m7173build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m7173build());
            }
            return this;
        }

        public Builder addEntry(int i, MetadataEntry.Builder builder) {
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntryIsMutable();
                this.entry_.add(i, builder.m7173build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m7173build());
            }
            return this;
        }

        public Builder addAllEntry(Iterable<? extends MetadataEntry> iterable) {
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntryIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.entry_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearEntry() {
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.entry_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeEntry(int i) {
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntryIsMutable();
                this.entry_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public MetadataEntry.Builder getEntryBuilder(int i) {
            return getEntryFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.binarylog.v1.MetadataOrBuilder
        public MetadataEntryOrBuilder getEntryOrBuilder(int i) {
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entry_.get(i);
            }
            return (MetadataEntryOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.binarylog.v1.MetadataOrBuilder
        public List<? extends MetadataEntryOrBuilder> getEntryOrBuilderList() {
            RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> repeatedFieldBuilderV3 = this.entryBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.entry_);
        }

        public MetadataEntry.Builder addEntryBuilder() {
            return getEntryFieldBuilder().addBuilder(MetadataEntry.getDefaultInstance());
        }

        public MetadataEntry.Builder addEntryBuilder(int i) {
            return getEntryFieldBuilder().addBuilder(i, MetadataEntry.getDefaultInstance());
        }

        public List<MetadataEntry.Builder> getEntryBuilderList() {
            return getEntryFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<MetadataEntry, MetadataEntry.Builder, MetadataEntryOrBuilder> getEntryFieldBuilder() {
            if (this.entryBuilder_ == null) {
                this.entryBuilder_ = new RepeatedFieldBuilderV3<>(this.entry_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.entry_ = null;
            }
            return this.entryBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m7161setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m7155mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
