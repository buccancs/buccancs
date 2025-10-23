package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

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
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.GrpcService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class EventServiceConfig extends GeneratedMessageV3 implements EventServiceConfigOrBuilder {
    public static final int GRPC_SERVICE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final EventServiceConfig DEFAULT_INSTANCE = new EventServiceConfig();
    private static final Parser<EventServiceConfig> PARSER = new AbstractParser<EventServiceConfig>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfig.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public EventServiceConfig m21939parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new EventServiceConfig(codedInputStream, extensionRegistryLite);
        }
    };
    private int configSourceSpecifierCase_;
    private Object configSourceSpecifier_;
    private byte memoizedIsInitialized;

    private EventServiceConfig(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.configSourceSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private EventServiceConfig() {
        this.configSourceSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private EventServiceConfig(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            GrpcService.Builder builderM22122toBuilder = this.configSourceSpecifierCase_ == 1 ? ((GrpcService) this.configSourceSpecifier_).m22122toBuilder() : null;
                            MessageLite message = codedInputStream.readMessage(GrpcService.parser(), extensionRegistryLite);
                            this.configSourceSpecifier_ = message;
                            if (builderM22122toBuilder != null) {
                                builderM22122toBuilder.mergeFrom((GrpcService) message);
                                this.configSourceSpecifier_ = builderM22122toBuilder.m22129buildPartial();
                            }
                            this.configSourceSpecifierCase_ = 1;
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

    public static EventServiceConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EventServiceConfig> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return EventServiceConfigProto.internal_static_envoy_config_core_v3_EventServiceConfig_descriptor;
    }

    public static EventServiceConfig parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (EventServiceConfig) PARSER.parseFrom(byteBuffer);
    }

    public static EventServiceConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EventServiceConfig) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static EventServiceConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (EventServiceConfig) PARSER.parseFrom(byteString);
    }

    public static EventServiceConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EventServiceConfig) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static EventServiceConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (EventServiceConfig) PARSER.parseFrom(bArr);
    }

    public static EventServiceConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EventServiceConfig) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static EventServiceConfig parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static EventServiceConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static EventServiceConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static EventServiceConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static EventServiceConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static EventServiceConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m21937toBuilder();
    }

    public static Builder newBuilder(EventServiceConfig eventServiceConfig) {
        return DEFAULT_INSTANCE.m21937toBuilder().mergeFrom(eventServiceConfig);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public EventServiceConfig m21932getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<EventServiceConfig> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfigOrBuilder
    public boolean hasGrpcService() {
        return this.configSourceSpecifierCase_ == 1;
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
        return new EventServiceConfig();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EventServiceConfigProto.internal_static_envoy_config_core_v3_EventServiceConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(EventServiceConfig.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfigOrBuilder
    public ConfigSourceSpecifierCase getConfigSourceSpecifierCase() {
        return ConfigSourceSpecifierCase.forNumber(this.configSourceSpecifierCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfigOrBuilder
    public GrpcService getGrpcService() {
        if (this.configSourceSpecifierCase_ == 1) {
            return (GrpcService) this.configSourceSpecifier_;
        }
        return GrpcService.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfigOrBuilder
    public GrpcServiceOrBuilder getGrpcServiceOrBuilder() {
        if (this.configSourceSpecifierCase_ == 1) {
            return (GrpcService) this.configSourceSpecifier_;
        }
        return GrpcService.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.configSourceSpecifierCase_ == 1) {
            codedOutputStream.writeMessage(1, (GrpcService) this.configSourceSpecifier_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = (this.configSourceSpecifierCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (GrpcService) this.configSourceSpecifier_) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeMessageSize;
        return iComputeMessageSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventServiceConfig)) {
            return super.equals(obj);
        }
        EventServiceConfig eventServiceConfig = (EventServiceConfig) obj;
        if (getConfigSourceSpecifierCase().equals(eventServiceConfig.getConfigSourceSpecifierCase())) {
            return (this.configSourceSpecifierCase_ != 1 || getGrpcService().equals(eventServiceConfig.getGrpcService())) && this.unknownFields.equals(eventServiceConfig.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (this.configSourceSpecifierCase_ == 1) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getGrpcService().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m21934newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m21937toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ConfigSourceSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        GRPC_SERVICE(1),
        CONFIGSOURCESPECIFIER_NOT_SET(0);

        private final int value;

        ConfigSourceSpecifierCase(int i) {
            this.value = i;
        }

        public static ConfigSourceSpecifierCase forNumber(int i) {
            if (i == 0) {
                return CONFIGSOURCESPECIFIER_NOT_SET;
            }
            if (i != 1) {
                return null;
            }
            return GRPC_SERVICE;
        }

        @Deprecated
        public static ConfigSourceSpecifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EventServiceConfigOrBuilder {
        private int configSourceSpecifierCase_;
        private Object configSourceSpecifier_;
        private SingleFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> grpcServiceBuilder_;

        private Builder() {
            this.configSourceSpecifierCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.configSourceSpecifierCase_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EventServiceConfigProto.internal_static_envoy_config_core_v3_EventServiceConfig_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfigOrBuilder
        public boolean hasGrpcService() {
            return this.configSourceSpecifierCase_ == 1;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EventServiceConfigProto.internal_static_envoy_config_core_v3_EventServiceConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(EventServiceConfig.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = EventServiceConfig.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21948clear() {
            super.clear();
            this.configSourceSpecifierCase_ = 0;
            this.configSourceSpecifier_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return EventServiceConfigProto.internal_static_envoy_config_core_v3_EventServiceConfig_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public EventServiceConfig m21961getDefaultInstanceForType() {
            return EventServiceConfig.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public EventServiceConfig m21942build() throws UninitializedMessageException {
            EventServiceConfig eventServiceConfigM21944buildPartial = m21944buildPartial();
            if (eventServiceConfigM21944buildPartial.isInitialized()) {
                return eventServiceConfigM21944buildPartial;
            }
            throw newUninitializedMessageException(eventServiceConfigM21944buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public EventServiceConfig m21944buildPartial() {
            EventServiceConfig eventServiceConfig = new EventServiceConfig(this);
            if (this.configSourceSpecifierCase_ == 1) {
                SingleFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> singleFieldBuilderV3 = this.grpcServiceBuilder_;
                if (singleFieldBuilderV3 == null) {
                    eventServiceConfig.configSourceSpecifier_ = this.configSourceSpecifier_;
                } else {
                    eventServiceConfig.configSourceSpecifier_ = singleFieldBuilderV3.build();
                }
            }
            eventServiceConfig.configSourceSpecifierCase_ = this.configSourceSpecifierCase_;
            onBuilt();
            return eventServiceConfig;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21960clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21972setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21950clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21953clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21974setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21940addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21965mergeFrom(Message message) {
            if (message instanceof EventServiceConfig) {
                return mergeFrom((EventServiceConfig) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(EventServiceConfig eventServiceConfig) {
            if (eventServiceConfig == EventServiceConfig.getDefaultInstance()) {
                return this;
            }
            if (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$core$v3$EventServiceConfig$ConfigSourceSpecifierCase[eventServiceConfig.getConfigSourceSpecifierCase().ordinal()] == 1) {
                mergeGrpcService(eventServiceConfig.getGrpcService());
            }
            m21970mergeUnknownFields(eventServiceConfig.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfig.Builder m21966mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfig.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfig r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfig) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfig r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfig) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfig.Builder.m21966mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfig$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfigOrBuilder
        public ConfigSourceSpecifierCase getConfigSourceSpecifierCase() {
            return ConfigSourceSpecifierCase.forNumber(this.configSourceSpecifierCase_);
        }

        public Builder clearConfigSourceSpecifier() {
            this.configSourceSpecifierCase_ = 0;
            this.configSourceSpecifier_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfigOrBuilder
        public GrpcService getGrpcService() {
            SingleFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> singleFieldBuilderV3 = this.grpcServiceBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configSourceSpecifierCase_ == 1) {
                    return (GrpcService) this.configSourceSpecifier_;
                }
                return GrpcService.getDefaultInstance();
            }
            if (this.configSourceSpecifierCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            }
            return GrpcService.getDefaultInstance();
        }

        public Builder setGrpcService(GrpcService grpcService) {
            SingleFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> singleFieldBuilderV3 = this.grpcServiceBuilder_;
            if (singleFieldBuilderV3 == null) {
                grpcService.getClass();
                this.configSourceSpecifier_ = grpcService;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(grpcService);
            }
            this.configSourceSpecifierCase_ = 1;
            return this;
        }

        public Builder setGrpcService(GrpcService.Builder builder) {
            SingleFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> singleFieldBuilderV3 = this.grpcServiceBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.configSourceSpecifier_ = builder.m22127build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m22127build());
            }
            this.configSourceSpecifierCase_ = 1;
            return this;
        }

        public Builder mergeGrpcService(GrpcService grpcService) {
            SingleFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> singleFieldBuilderV3 = this.grpcServiceBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configSourceSpecifierCase_ != 1 || this.configSourceSpecifier_ == GrpcService.getDefaultInstance()) {
                    this.configSourceSpecifier_ = grpcService;
                } else {
                    this.configSourceSpecifier_ = GrpcService.newBuilder((GrpcService) this.configSourceSpecifier_).mergeFrom(grpcService).m22129buildPartial();
                }
                onChanged();
            } else {
                if (this.configSourceSpecifierCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(grpcService);
                }
                this.grpcServiceBuilder_.setMessage(grpcService);
            }
            this.configSourceSpecifierCase_ = 1;
            return this;
        }

        public Builder clearGrpcService() {
            SingleFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> singleFieldBuilderV3 = this.grpcServiceBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.configSourceSpecifierCase_ == 1) {
                    this.configSourceSpecifierCase_ = 0;
                    this.configSourceSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.configSourceSpecifierCase_ == 1) {
                this.configSourceSpecifierCase_ = 0;
                this.configSourceSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public GrpcService.Builder getGrpcServiceBuilder() {
            return getGrpcServiceFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfigOrBuilder
        public GrpcServiceOrBuilder getGrpcServiceOrBuilder() {
            SingleFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> singleFieldBuilderV3;
            int i = this.configSourceSpecifierCase_;
            if (i == 1 && (singleFieldBuilderV3 = this.grpcServiceBuilder_) != null) {
                return (GrpcServiceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 1) {
                return (GrpcService) this.configSourceSpecifier_;
            }
            return GrpcService.getDefaultInstance();
        }

        private SingleFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> getGrpcServiceFieldBuilder() {
            if (this.grpcServiceBuilder_ == null) {
                if (this.configSourceSpecifierCase_ != 1) {
                    this.configSourceSpecifier_ = GrpcService.getDefaultInstance();
                }
                this.grpcServiceBuilder_ = new SingleFieldBuilderV3<>((GrpcService) this.configSourceSpecifier_, getParentForChildren(), isClean());
                this.configSourceSpecifier_ = null;
            }
            this.configSourceSpecifierCase_ = 1;
            onChanged();
            return this.grpcServiceBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m21976setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m21970mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfig$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$core$v3$EventServiceConfig$ConfigSourceSpecifierCase;

        static {
            int[] iArr = new int[ConfigSourceSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$core$v3$EventServiceConfig$ConfigSourceSpecifierCase = iArr;
            try {
                iArr[ConfigSourceSpecifierCase.GRPC_SERVICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$EventServiceConfig$ConfigSourceSpecifierCase[ConfigSourceSpecifierCase.CONFIGSOURCESPECIFIER_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
