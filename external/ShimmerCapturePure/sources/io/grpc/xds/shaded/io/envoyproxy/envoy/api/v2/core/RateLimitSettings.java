package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.DoubleValueOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class RateLimitSettings extends GeneratedMessageV3 implements RateLimitSettingsOrBuilder {
    public static final int FILL_RATE_FIELD_NUMBER = 2;
    public static final int MAX_TOKENS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final RateLimitSettings DEFAULT_INSTANCE = new RateLimitSettings();
    private static final Parser<RateLimitSettings> PARSER = new AbstractParser<RateLimitSettings>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettings.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public RateLimitSettings m16429parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RateLimitSettings(codedInputStream, extensionRegistryLite);
        }
    };
    private DoubleValue fillRate_;
    private UInt32Value maxTokens_;
    private byte memoizedIsInitialized;

    private RateLimitSettings(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private RateLimitSettings() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private RateLimitSettings(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        UInt32Value.Builder builder;
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
                            UInt32Value uInt32Value = this.maxTokens_;
                            builder = uInt32Value != null ? uInt32Value.toBuilder() : null;
                            UInt32Value message = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.maxTokens_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.maxTokens_ = builder.buildPartial();
                            }
                        } else if (tag == 18) {
                            DoubleValue doubleValue = this.fillRate_;
                            builder = doubleValue != null ? doubleValue.toBuilder() : null;
                            DoubleValue message2 = codedInputStream.readMessage(DoubleValue.parser(), extensionRegistryLite);
                            this.fillRate_ = message2;
                            if (builder != null) {
                                builder.mergeFrom(message2);
                                this.fillRate_ = builder.buildPartial();
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

    public static RateLimitSettings getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RateLimitSettings> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ConfigSourceProto.internal_static_envoy_api_v2_core_RateLimitSettings_descriptor;
    }

    public static RateLimitSettings parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RateLimitSettings) PARSER.parseFrom(byteBuffer);
    }

    public static RateLimitSettings parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RateLimitSettings) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RateLimitSettings parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RateLimitSettings) PARSER.parseFrom(byteString);
    }

    public static RateLimitSettings parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RateLimitSettings) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RateLimitSettings parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RateLimitSettings) PARSER.parseFrom(bArr);
    }

    public static RateLimitSettings parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RateLimitSettings) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RateLimitSettings parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RateLimitSettings parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RateLimitSettings parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RateLimitSettings parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RateLimitSettings parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RateLimitSettings parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m16427toBuilder();
    }

    public static Builder newBuilder(RateLimitSettings rateLimitSettings) {
        return DEFAULT_INSTANCE.m16427toBuilder().mergeFrom(rateLimitSettings);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public RateLimitSettings m16422getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<RateLimitSettings> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettingsOrBuilder
    public boolean hasFillRate() {
        return this.fillRate_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettingsOrBuilder
    public boolean hasMaxTokens() {
        return this.maxTokens_ != null;
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
        return new RateLimitSettings();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ConfigSourceProto.internal_static_envoy_api_v2_core_RateLimitSettings_fieldAccessorTable.ensureFieldAccessorsInitialized(RateLimitSettings.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettingsOrBuilder
    public UInt32Value getMaxTokens() {
        UInt32Value uInt32Value = this.maxTokens_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettingsOrBuilder
    public UInt32ValueOrBuilder getMaxTokensOrBuilder() {
        return getMaxTokens();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettingsOrBuilder
    public DoubleValue getFillRate() {
        DoubleValue doubleValue = this.fillRate_;
        return doubleValue == null ? DoubleValue.getDefaultInstance() : doubleValue;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettingsOrBuilder
    public DoubleValueOrBuilder getFillRateOrBuilder() {
        return getFillRate();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.maxTokens_ != null) {
            codedOutputStream.writeMessage(1, getMaxTokens());
        }
        if (this.fillRate_ != null) {
            codedOutputStream.writeMessage(2, getFillRate());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.maxTokens_ != null ? CodedOutputStream.computeMessageSize(1, getMaxTokens()) : 0;
        if (this.fillRate_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getFillRate());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RateLimitSettings)) {
            return super.equals(obj);
        }
        RateLimitSettings rateLimitSettings = (RateLimitSettings) obj;
        if (hasMaxTokens() != rateLimitSettings.hasMaxTokens()) {
            return false;
        }
        if ((!hasMaxTokens() || getMaxTokens().equals(rateLimitSettings.getMaxTokens())) && hasFillRate() == rateLimitSettings.hasFillRate()) {
            return (!hasFillRate() || getFillRate().equals(rateLimitSettings.getFillRate())) && this.unknownFields.equals(rateLimitSettings.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasMaxTokens()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getMaxTokens().hashCode();
        }
        if (hasFillRate()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getFillRate().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m16424newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m16427toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RateLimitSettingsOrBuilder {
        private SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> fillRateBuilder_;
        private DoubleValue fillRate_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> maxTokensBuilder_;
        private UInt32Value maxTokens_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ConfigSourceProto.internal_static_envoy_api_v2_core_RateLimitSettings_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettingsOrBuilder
        public boolean hasFillRate() {
            return (this.fillRateBuilder_ == null && this.fillRate_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettingsOrBuilder
        public boolean hasMaxTokens() {
            return (this.maxTokensBuilder_ == null && this.maxTokens_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ConfigSourceProto.internal_static_envoy_api_v2_core_RateLimitSettings_fieldAccessorTable.ensureFieldAccessorsInitialized(RateLimitSettings.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RateLimitSettings.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16438clear() {
            super.clear();
            if (this.maxTokensBuilder_ == null) {
                this.maxTokens_ = null;
            } else {
                this.maxTokens_ = null;
                this.maxTokensBuilder_ = null;
            }
            if (this.fillRateBuilder_ == null) {
                this.fillRate_ = null;
            } else {
                this.fillRate_ = null;
                this.fillRateBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ConfigSourceProto.internal_static_envoy_api_v2_core_RateLimitSettings_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RateLimitSettings m16451getDefaultInstanceForType() {
            return RateLimitSettings.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RateLimitSettings m16432build() throws UninitializedMessageException {
            RateLimitSettings rateLimitSettingsM16434buildPartial = m16434buildPartial();
            if (rateLimitSettingsM16434buildPartial.isInitialized()) {
                return rateLimitSettingsM16434buildPartial;
            }
            throw newUninitializedMessageException(rateLimitSettingsM16434buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RateLimitSettings m16434buildPartial() {
            RateLimitSettings rateLimitSettings = new RateLimitSettings(this);
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxTokensBuilder_;
            if (singleFieldBuilderV3 == null) {
                rateLimitSettings.maxTokens_ = this.maxTokens_;
            } else {
                rateLimitSettings.maxTokens_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV32 = this.fillRateBuilder_;
            if (singleFieldBuilderV32 == null) {
                rateLimitSettings.fillRate_ = this.fillRate_;
            } else {
                rateLimitSettings.fillRate_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return rateLimitSettings;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16450clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16462setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16440clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16443clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16464setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16430addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16455mergeFrom(Message message) {
            if (message instanceof RateLimitSettings) {
                return mergeFrom((RateLimitSettings) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RateLimitSettings rateLimitSettings) {
            if (rateLimitSettings == RateLimitSettings.getDefaultInstance()) {
                return this;
            }
            if (rateLimitSettings.hasMaxTokens()) {
                mergeMaxTokens(rateLimitSettings.getMaxTokens());
            }
            if (rateLimitSettings.hasFillRate()) {
                mergeFillRate(rateLimitSettings.getFillRate());
            }
            m16460mergeUnknownFields(rateLimitSettings.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettings.Builder m16456mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettings.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettings r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettings) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettings r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettings) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettings.Builder.m16456mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettings$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettingsOrBuilder
        public UInt32Value getMaxTokens() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxTokensBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.maxTokens_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setMaxTokens(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxTokensBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.maxTokens_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setMaxTokens(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxTokensBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.maxTokens_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeMaxTokens(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxTokensBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.maxTokens_;
                if (uInt32Value2 != null) {
                    this.maxTokens_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.maxTokens_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearMaxTokens() {
            if (this.maxTokensBuilder_ == null) {
                this.maxTokens_ = null;
                onChanged();
            } else {
                this.maxTokens_ = null;
                this.maxTokensBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getMaxTokensBuilder() {
            onChanged();
            return getMaxTokensFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettingsOrBuilder
        public UInt32ValueOrBuilder getMaxTokensOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.maxTokensBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.maxTokens_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getMaxTokensFieldBuilder() {
            if (this.maxTokensBuilder_ == null) {
                this.maxTokensBuilder_ = new SingleFieldBuilderV3<>(getMaxTokens(), getParentForChildren(), isClean());
                this.maxTokens_ = null;
            }
            return this.maxTokensBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettingsOrBuilder
        public DoubleValue getFillRate() {
            SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.fillRateBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            DoubleValue doubleValue = this.fillRate_;
            return doubleValue == null ? DoubleValue.getDefaultInstance() : doubleValue;
        }

        public Builder setFillRate(DoubleValue doubleValue) {
            SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.fillRateBuilder_;
            if (singleFieldBuilderV3 == null) {
                doubleValue.getClass();
                this.fillRate_ = doubleValue;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(doubleValue);
            }
            return this;
        }

        public Builder setFillRate(DoubleValue.Builder builder) {
            SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.fillRateBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.fillRate_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeFillRate(DoubleValue doubleValue) {
            SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.fillRateBuilder_;
            if (singleFieldBuilderV3 == null) {
                DoubleValue doubleValue2 = this.fillRate_;
                if (doubleValue2 != null) {
                    this.fillRate_ = DoubleValue.newBuilder(doubleValue2).mergeFrom(doubleValue).buildPartial();
                } else {
                    this.fillRate_ = doubleValue;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(doubleValue);
            }
            return this;
        }

        public Builder clearFillRate() {
            if (this.fillRateBuilder_ == null) {
                this.fillRate_ = null;
                onChanged();
            } else {
                this.fillRate_ = null;
                this.fillRateBuilder_ = null;
            }
            return this;
        }

        public DoubleValue.Builder getFillRateBuilder() {
            onChanged();
            return getFillRateFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettingsOrBuilder
        public DoubleValueOrBuilder getFillRateOrBuilder() {
            SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> singleFieldBuilderV3 = this.fillRateBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            DoubleValue doubleValue = this.fillRate_;
            return doubleValue == null ? DoubleValue.getDefaultInstance() : doubleValue;
        }

        private SingleFieldBuilderV3<DoubleValue, DoubleValue.Builder, DoubleValueOrBuilder> getFillRateFieldBuilder() {
            if (this.fillRateBuilder_ == null) {
                this.fillRateBuilder_ = new SingleFieldBuilderV3<>(getFillRate(), getParentForChildren(), isClean());
                this.fillRate_ = null;
            }
            return this.fillRateBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m16466setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m16460mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
