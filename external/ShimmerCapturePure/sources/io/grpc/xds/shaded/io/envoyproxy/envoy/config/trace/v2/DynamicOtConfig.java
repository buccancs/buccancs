package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2;

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

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class DynamicOtConfig extends GeneratedMessageV3 implements DynamicOtConfigOrBuilder {
    public static final int CONFIG_FIELD_NUMBER = 2;
    public static final int LIBRARY_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final DynamicOtConfig DEFAULT_INSTANCE = new DynamicOtConfig();
    private static final Parser<DynamicOtConfig> PARSER = new AbstractParser<DynamicOtConfig>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfig.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public DynamicOtConfig m30122parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DynamicOtConfig(codedInputStream, extensionRegistryLite);
        }
    };
    private Struct config_;
    private volatile Object library_;
    private byte memoizedIsInitialized;

    private DynamicOtConfig(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DynamicOtConfig() {
        this.memoizedIsInitialized = (byte) -1;
        this.library_ = "";
    }

    private DynamicOtConfig(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.library_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            Struct struct = this.config_;
                            Struct.Builder builder = struct != null ? struct.toBuilder() : null;
                            Struct message = codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            this.config_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.config_ = builder.buildPartial();
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

    public static DynamicOtConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DynamicOtConfig> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DynamicOtProto.internal_static_envoy_config_trace_v2_DynamicOtConfig_descriptor;
    }

    public static DynamicOtConfig parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DynamicOtConfig) PARSER.parseFrom(byteBuffer);
    }

    public static DynamicOtConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DynamicOtConfig) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DynamicOtConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DynamicOtConfig) PARSER.parseFrom(byteString);
    }

    public static DynamicOtConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DynamicOtConfig) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DynamicOtConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DynamicOtConfig) PARSER.parseFrom(bArr);
    }

    public static DynamicOtConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DynamicOtConfig) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DynamicOtConfig parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DynamicOtConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DynamicOtConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DynamicOtConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DynamicOtConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DynamicOtConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m30120toBuilder();
    }

    public static Builder newBuilder(DynamicOtConfig dynamicOtConfig) {
        return DEFAULT_INSTANCE.m30120toBuilder().mergeFrom(dynamicOtConfig);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public DynamicOtConfig m30115getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<DynamicOtConfig> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfigOrBuilder
    public boolean hasConfig() {
        return this.config_ != null;
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
        return new DynamicOtConfig();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DynamicOtProto.internal_static_envoy_config_trace_v2_DynamicOtConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(DynamicOtConfig.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfigOrBuilder
    public String getLibrary() {
        Object obj = this.library_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.library_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfigOrBuilder
    public ByteString getLibraryBytes() {
        Object obj = this.library_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.library_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfigOrBuilder
    public Struct getConfig() {
        Struct struct = this.config_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfigOrBuilder
    public StructOrBuilder getConfigOrBuilder() {
        return getConfig();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getLibraryBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.library_);
        }
        if (this.config_ != null) {
            codedOutputStream.writeMessage(2, getConfig());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getLibraryBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.library_) : 0;
        if (this.config_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, getConfig());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DynamicOtConfig)) {
            return super.equals(obj);
        }
        DynamicOtConfig dynamicOtConfig = (DynamicOtConfig) obj;
        if (getLibrary().equals(dynamicOtConfig.getLibrary()) && hasConfig() == dynamicOtConfig.hasConfig()) {
            return (!hasConfig() || getConfig().equals(dynamicOtConfig.getConfig())) && this.unknownFields.equals(dynamicOtConfig.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getLibrary().hashCode();
        if (hasConfig()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getConfig().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m30117newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m30120toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DynamicOtConfigOrBuilder {
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> configBuilder_;
        private Struct config_;
        private Object library_;

        private Builder() {
            this.library_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.library_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DynamicOtProto.internal_static_envoy_config_trace_v2_DynamicOtConfig_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfigOrBuilder
        public boolean hasConfig() {
            return (this.configBuilder_ == null && this.config_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DynamicOtProto.internal_static_envoy_config_trace_v2_DynamicOtConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(DynamicOtConfig.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = DynamicOtConfig.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30131clear() {
            super.clear();
            this.library_ = "";
            if (this.configBuilder_ == null) {
                this.config_ = null;
            } else {
                this.config_ = null;
                this.configBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return DynamicOtProto.internal_static_envoy_config_trace_v2_DynamicOtConfig_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DynamicOtConfig m30144getDefaultInstanceForType() {
            return DynamicOtConfig.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DynamicOtConfig m30125build() throws UninitializedMessageException {
            DynamicOtConfig dynamicOtConfigM30127buildPartial = m30127buildPartial();
            if (dynamicOtConfigM30127buildPartial.isInitialized()) {
                return dynamicOtConfigM30127buildPartial;
            }
            throw newUninitializedMessageException(dynamicOtConfigM30127buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DynamicOtConfig m30127buildPartial() {
            DynamicOtConfig dynamicOtConfig = new DynamicOtConfig(this);
            dynamicOtConfig.library_ = this.library_;
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
            if (singleFieldBuilderV3 == null) {
                dynamicOtConfig.config_ = this.config_;
            } else {
                dynamicOtConfig.config_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return dynamicOtConfig;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30143clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30155setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30133clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30136clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30157setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30123addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30148mergeFrom(Message message) {
            if (message instanceof DynamicOtConfig) {
                return mergeFrom((DynamicOtConfig) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DynamicOtConfig dynamicOtConfig) {
            if (dynamicOtConfig == DynamicOtConfig.getDefaultInstance()) {
                return this;
            }
            if (!dynamicOtConfig.getLibrary().isEmpty()) {
                this.library_ = dynamicOtConfig.library_;
                onChanged();
            }
            if (dynamicOtConfig.hasConfig()) {
                mergeConfig(dynamicOtConfig.getConfig());
            }
            m30153mergeUnknownFields(dynamicOtConfig.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfig.Builder m30149mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfig.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfig r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfig) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfig r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfig) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfig.Builder.m30149mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfig$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfigOrBuilder
        public String getLibrary() {
            Object obj = this.library_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.library_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setLibrary(String str) {
            str.getClass();
            this.library_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfigOrBuilder
        public ByteString getLibraryBytes() {
            Object obj = this.library_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.library_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setLibraryBytes(ByteString byteString) {
            byteString.getClass();
            DynamicOtConfig.checkByteStringIsUtf8(byteString);
            this.library_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearLibrary() {
            this.library_ = DynamicOtConfig.getDefaultInstance().getLibrary();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfigOrBuilder
        public Struct getConfig() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Struct struct = this.config_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        public Builder setConfig(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
            if (singleFieldBuilderV3 == null) {
                struct.getClass();
                this.config_ = struct;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(struct);
            }
            return this;
        }

        public Builder setConfig(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.config_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeConfig(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.config_;
                if (struct2 != null) {
                    this.config_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.config_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearConfig() {
            if (this.configBuilder_ == null) {
                this.config_ = null;
                onChanged();
            } else {
                this.config_ = null;
                this.configBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getConfigBuilder() {
            onChanged();
            return getConfigFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.DynamicOtConfigOrBuilder
        public StructOrBuilder getConfigOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.config_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getConfigFieldBuilder() {
            if (this.configBuilder_ == null) {
                this.configBuilder_ = new SingleFieldBuilderV3<>(getConfig(), getParentForChildren(), isClean());
                this.config_ = null;
            }
            return this.configBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m30159setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m30153mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
