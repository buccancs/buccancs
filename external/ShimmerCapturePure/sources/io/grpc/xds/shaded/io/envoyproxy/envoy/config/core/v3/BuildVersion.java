package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

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
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.SemanticVersion;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.SemanticVersionOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class BuildVersion extends GeneratedMessageV3 implements BuildVersionOrBuilder {
    public static final int METADATA_FIELD_NUMBER = 2;
    public static final int VERSION_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final BuildVersion DEFAULT_INSTANCE = new BuildVersion();
    private static final Parser<BuildVersion> PARSER = new AbstractParser<BuildVersion>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersion.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public BuildVersion m21709parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BuildVersion(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private Struct metadata_;
    private SemanticVersion version_;

    private BuildVersion(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private BuildVersion() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private BuildVersion(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        SemanticVersion.Builder builder;
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            SemanticVersion semanticVersion = this.version_;
                            builder = semanticVersion != null ? semanticVersion.m35208toBuilder() : null;
                            SemanticVersion semanticVersion2 = (SemanticVersion) codedInputStream.readMessage(SemanticVersion.parser(), extensionRegistryLite);
                            this.version_ = semanticVersion2;
                            if (builder != null) {
                                builder.mergeFrom(semanticVersion2);
                                this.version_ = builder.m35215buildPartial();
                            }
                        } else if (tag == 18) {
                            Struct struct = this.metadata_;
                            builder = struct != null ? struct.toBuilder() : null;
                            Struct message = codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            this.metadata_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.metadata_ = builder.buildPartial();
                            }
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

    public static BuildVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BuildVersion> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BaseProto.internal_static_envoy_config_core_v3_BuildVersion_descriptor;
    }

    public static BuildVersion parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BuildVersion) PARSER.parseFrom(byteBuffer);
    }

    public static BuildVersion parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BuildVersion) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BuildVersion parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BuildVersion) PARSER.parseFrom(byteString);
    }

    public static BuildVersion parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BuildVersion) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BuildVersion parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BuildVersion) PARSER.parseFrom(bArr);
    }

    public static BuildVersion parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BuildVersion) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BuildVersion parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BuildVersion parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BuildVersion parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BuildVersion parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BuildVersion parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BuildVersion parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m21707toBuilder();
    }

    public static Builder newBuilder(BuildVersion buildVersion) {
        return DEFAULT_INSTANCE.m21707toBuilder().mergeFrom(buildVersion);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public BuildVersion m21702getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<BuildVersion> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersionOrBuilder
    public boolean hasMetadata() {
        return this.metadata_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersionOrBuilder
    public boolean hasVersion() {
        return this.version_ != null;
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
        return new BuildVersion();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BaseProto.internal_static_envoy_config_core_v3_BuildVersion_fieldAccessorTable.ensureFieldAccessorsInitialized(BuildVersion.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersionOrBuilder
    public SemanticVersion getVersion() {
        SemanticVersion semanticVersion = this.version_;
        return semanticVersion == null ? SemanticVersion.getDefaultInstance() : semanticVersion;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersionOrBuilder
    public SemanticVersionOrBuilder getVersionOrBuilder() {
        return getVersion();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersionOrBuilder
    public Struct getMetadata() {
        Struct struct = this.metadata_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersionOrBuilder
    public StructOrBuilder getMetadataOrBuilder() {
        return getMetadata();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.version_ != null) {
            codedOutputStream.writeMessage(1, getVersion());
        }
        if (this.metadata_ != null) {
            codedOutputStream.writeMessage(2, getMetadata());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.version_ != null ? CodedOutputStream.computeMessageSize(1, getVersion()) : 0;
        if (this.metadata_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getMetadata());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BuildVersion)) {
            return super.equals(obj);
        }
        BuildVersion buildVersion = (BuildVersion) obj;
        if (hasVersion() != buildVersion.hasVersion()) {
            return false;
        }
        if ((!hasVersion() || getVersion().equals(buildVersion.getVersion())) && hasMetadata() == buildVersion.hasMetadata()) {
            return (!hasMetadata() || getMetadata().equals(buildVersion.getMetadata())) && this.unknownFields.equals(buildVersion.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasVersion()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getVersion().hashCode();
        }
        if (hasMetadata()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getMetadata().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m21704newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m21707toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BuildVersionOrBuilder {
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> metadataBuilder_;
        private Struct metadata_;
        private SingleFieldBuilderV3<SemanticVersion, SemanticVersion.Builder, SemanticVersionOrBuilder> versionBuilder_;
        private SemanticVersion version_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BaseProto.internal_static_envoy_config_core_v3_BuildVersion_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersionOrBuilder
        public boolean hasMetadata() {
            return (this.metadataBuilder_ == null && this.metadata_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersionOrBuilder
        public boolean hasVersion() {
            return (this.versionBuilder_ == null && this.version_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BaseProto.internal_static_envoy_config_core_v3_BuildVersion_fieldAccessorTable.ensureFieldAccessorsInitialized(BuildVersion.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = BuildVersion.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21718clear() {
            super.clear();
            if (this.versionBuilder_ == null) {
                this.version_ = null;
            } else {
                this.version_ = null;
                this.versionBuilder_ = null;
            }
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BaseProto.internal_static_envoy_config_core_v3_BuildVersion_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public BuildVersion m21731getDefaultInstanceForType() {
            return BuildVersion.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public BuildVersion m21712build() throws UninitializedMessageException {
            BuildVersion buildVersionM21714buildPartial = m21714buildPartial();
            if (buildVersionM21714buildPartial.isInitialized()) {
                return buildVersionM21714buildPartial;
            }
            throw newUninitializedMessageException(buildVersionM21714buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public BuildVersion m21714buildPartial() {
            BuildVersion buildVersion = new BuildVersion(this);
            SingleFieldBuilderV3<SemanticVersion, SemanticVersion.Builder, SemanticVersionOrBuilder> singleFieldBuilderV3 = this.versionBuilder_;
            if (singleFieldBuilderV3 == null) {
                buildVersion.version_ = this.version_;
            } else {
                buildVersion.version_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV32 = this.metadataBuilder_;
            if (singleFieldBuilderV32 == null) {
                buildVersion.metadata_ = this.metadata_;
            } else {
                buildVersion.metadata_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return buildVersion;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21730clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21742setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21720clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21723clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21744setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21710addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21735mergeFrom(Message message) {
            if (message instanceof BuildVersion) {
                return mergeFrom((BuildVersion) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BuildVersion buildVersion) {
            if (buildVersion == BuildVersion.getDefaultInstance()) {
                return this;
            }
            if (buildVersion.hasVersion()) {
                mergeVersion(buildVersion.getVersion());
            }
            if (buildVersion.hasMetadata()) {
                mergeMetadata(buildVersion.getMetadata());
            }
            m21740mergeUnknownFields(buildVersion.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersion.Builder m21736mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersion.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersion r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersion) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersion r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersion) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersion.Builder.m21736mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersion$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersionOrBuilder
        public SemanticVersion getVersion() {
            SingleFieldBuilderV3<SemanticVersion, SemanticVersion.Builder, SemanticVersionOrBuilder> singleFieldBuilderV3 = this.versionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            SemanticVersion semanticVersion = this.version_;
            return semanticVersion == null ? SemanticVersion.getDefaultInstance() : semanticVersion;
        }

        public Builder setVersion(SemanticVersion semanticVersion) {
            SingleFieldBuilderV3<SemanticVersion, SemanticVersion.Builder, SemanticVersionOrBuilder> singleFieldBuilderV3 = this.versionBuilder_;
            if (singleFieldBuilderV3 == null) {
                semanticVersion.getClass();
                this.version_ = semanticVersion;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(semanticVersion);
            }
            return this;
        }

        public Builder setVersion(SemanticVersion.Builder builder) {
            SingleFieldBuilderV3<SemanticVersion, SemanticVersion.Builder, SemanticVersionOrBuilder> singleFieldBuilderV3 = this.versionBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.version_ = builder.m35213build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m35213build());
            }
            return this;
        }

        public Builder mergeVersion(SemanticVersion semanticVersion) {
            SingleFieldBuilderV3<SemanticVersion, SemanticVersion.Builder, SemanticVersionOrBuilder> singleFieldBuilderV3 = this.versionBuilder_;
            if (singleFieldBuilderV3 == null) {
                SemanticVersion semanticVersion2 = this.version_;
                if (semanticVersion2 != null) {
                    this.version_ = SemanticVersion.newBuilder(semanticVersion2).mergeFrom(semanticVersion).m35215buildPartial();
                } else {
                    this.version_ = semanticVersion;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(semanticVersion);
            }
            return this;
        }

        public Builder clearVersion() {
            if (this.versionBuilder_ == null) {
                this.version_ = null;
                onChanged();
            } else {
                this.version_ = null;
                this.versionBuilder_ = null;
            }
            return this;
        }

        public SemanticVersion.Builder getVersionBuilder() {
            onChanged();
            return getVersionFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersionOrBuilder
        public SemanticVersionOrBuilder getVersionOrBuilder() {
            SingleFieldBuilderV3<SemanticVersion, SemanticVersion.Builder, SemanticVersionOrBuilder> singleFieldBuilderV3 = this.versionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (SemanticVersionOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            SemanticVersion semanticVersion = this.version_;
            return semanticVersion == null ? SemanticVersion.getDefaultInstance() : semanticVersion;
        }

        private SingleFieldBuilderV3<SemanticVersion, SemanticVersion.Builder, SemanticVersionOrBuilder> getVersionFieldBuilder() {
            if (this.versionBuilder_ == null) {
                this.versionBuilder_ = new SingleFieldBuilderV3<>(getVersion(), getParentForChildren(), isClean());
                this.version_ = null;
            }
            return this.versionBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersionOrBuilder
        public Struct getMetadata() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Struct struct = this.metadata_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        public Builder setMetadata(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                struct.getClass();
                this.metadata_ = struct;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(struct);
            }
            return this;
        }

        public Builder setMetadata(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.metadata_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeMetadata(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.metadata_;
                if (struct2 != null) {
                    this.metadata_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.metadata_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearMetadata() {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
                onChanged();
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getMetadataBuilder() {
            onChanged();
            return getMetadataFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BuildVersionOrBuilder
        public StructOrBuilder getMetadataOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.metadata_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getMetadataFieldBuilder() {
            if (this.metadataBuilder_ == null) {
                this.metadataBuilder_ = new SingleFieldBuilderV3<>(getMetadata(), getParentForChildren(), isClean());
                this.metadata_ = null;
            }
            return this.metadataBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m21746setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m21740mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
