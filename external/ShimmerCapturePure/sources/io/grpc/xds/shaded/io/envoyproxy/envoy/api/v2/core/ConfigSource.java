package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
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
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.AggregatedConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.SelfConfigSource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class ConfigSource extends GeneratedMessageV3 implements ConfigSourceOrBuilder {
    public static final int ADS_FIELD_NUMBER = 3;
    public static final int API_CONFIG_SOURCE_FIELD_NUMBER = 2;
    public static final int INITIAL_FETCH_TIMEOUT_FIELD_NUMBER = 4;
    public static final int PATH_FIELD_NUMBER = 1;
    public static final int RESOURCE_API_VERSION_FIELD_NUMBER = 6;
    public static final int SELF_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private static final ConfigSource DEFAULT_INSTANCE = new ConfigSource();
    private static final Parser<ConfigSource> PARSER = new AbstractParser<ConfigSource>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ConfigSource m14630parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ConfigSource(codedInputStream, extensionRegistryLite);
        }
    };
    private int configSourceSpecifierCase_;
    private Object configSourceSpecifier_;
    private Duration initialFetchTimeout_;
    private byte memoizedIsInitialized;
    private int resourceApiVersion_;

    private ConfigSource(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.configSourceSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private ConfigSource() {
        this.configSourceSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.resourceApiVersion_ = 0;
    }

    private ConfigSource(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Duration.Builder builderM16751toBuilder;
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
                            if (tag != 10) {
                                if (tag == 18) {
                                    builderM16751toBuilder = this.configSourceSpecifierCase_ == 2 ? ((ApiConfigSource) this.configSourceSpecifier_).m14350toBuilder() : null;
                                    MessageLite message = codedInputStream.readMessage(ApiConfigSource.parser(), extensionRegistryLite);
                                    this.configSourceSpecifier_ = message;
                                    if (builderM16751toBuilder != null) {
                                        builderM16751toBuilder.mergeFrom((ApiConfigSource) message);
                                        this.configSourceSpecifier_ = builderM16751toBuilder.m14358buildPartial();
                                    }
                                    this.configSourceSpecifierCase_ = 2;
                                } else if (tag == 26) {
                                    builderM16751toBuilder = this.configSourceSpecifierCase_ == 3 ? ((AggregatedConfigSource) this.configSourceSpecifier_).m14303toBuilder() : null;
                                    MessageLite message2 = codedInputStream.readMessage(AggregatedConfigSource.parser(), extensionRegistryLite);
                                    this.configSourceSpecifier_ = message2;
                                    if (builderM16751toBuilder != null) {
                                        builderM16751toBuilder.mergeFrom((AggregatedConfigSource) message2);
                                        this.configSourceSpecifier_ = builderM16751toBuilder.m14310buildPartial();
                                    }
                                    this.configSourceSpecifierCase_ = 3;
                                } else if (tag == 34) {
                                    Duration duration = this.initialFetchTimeout_;
                                    builderM16751toBuilder = duration != null ? duration.toBuilder() : null;
                                    Duration message3 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                                    this.initialFetchTimeout_ = message3;
                                    if (builderM16751toBuilder != null) {
                                        builderM16751toBuilder.mergeFrom(message3);
                                        this.initialFetchTimeout_ = builderM16751toBuilder.buildPartial();
                                    }
                                } else if (tag == 42) {
                                    builderM16751toBuilder = this.configSourceSpecifierCase_ == 5 ? ((SelfConfigSource) this.configSourceSpecifier_).m16751toBuilder() : null;
                                    SelfConfigSource message4 = codedInputStream.readMessage(SelfConfigSource.parser(), extensionRegistryLite);
                                    this.configSourceSpecifier_ = message4;
                                    if (builderM16751toBuilder != null) {
                                        builderM16751toBuilder.mergeFrom(message4);
                                        this.configSourceSpecifier_ = builderM16751toBuilder.m16758buildPartial();
                                    }
                                    this.configSourceSpecifierCase_ = 5;
                                } else if (tag == 48) {
                                    this.resourceApiVersion_ = codedInputStream.readEnum();
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.configSourceSpecifierCase_ = 1;
                                this.configSourceSpecifier_ = stringRequireUtf8;
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

    public static ConfigSource getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ConfigSource> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ConfigSourceProto.internal_static_envoy_api_v2_core_ConfigSource_descriptor;
    }

    public static ConfigSource parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ConfigSource) PARSER.parseFrom(byteBuffer);
    }

    public static ConfigSource parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ConfigSource) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ConfigSource parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ConfigSource) PARSER.parseFrom(byteString);
    }

    public static ConfigSource parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ConfigSource) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ConfigSource parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ConfigSource) PARSER.parseFrom(bArr);
    }

    public static ConfigSource parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ConfigSource) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ConfigSource parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ConfigSource parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ConfigSource parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ConfigSource parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ConfigSource parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ConfigSource parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m14628toBuilder();
    }

    public static Builder newBuilder(ConfigSource configSource) {
        return DEFAULT_INSTANCE.m14628toBuilder().mergeFrom(configSource);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ConfigSource m14623getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ConfigSource> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public int getResourceApiVersionValue() {
        return this.resourceApiVersion_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public boolean hasAds() {
        return this.configSourceSpecifierCase_ == 3;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public boolean hasApiConfigSource() {
        return this.configSourceSpecifierCase_ == 2;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public boolean hasInitialFetchTimeout() {
        return this.initialFetchTimeout_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public boolean hasSelf() {
        return this.configSourceSpecifierCase_ == 5;
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
        return new ConfigSource();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ConfigSourceProto.internal_static_envoy_api_v2_core_ConfigSource_fieldAccessorTable.ensureFieldAccessorsInitialized(ConfigSource.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public ConfigSourceSpecifierCase getConfigSourceSpecifierCase() {
        return ConfigSourceSpecifierCase.forNumber(this.configSourceSpecifierCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public String getPath() {
        String str = this.configSourceSpecifierCase_ == 1 ? this.configSourceSpecifier_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.configSourceSpecifierCase_ == 1) {
            this.configSourceSpecifier_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public ByteString getPathBytes() {
        String str = this.configSourceSpecifierCase_ == 1 ? this.configSourceSpecifier_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.configSourceSpecifierCase_ == 1) {
                this.configSourceSpecifier_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public ApiConfigSource getApiConfigSource() {
        if (this.configSourceSpecifierCase_ == 2) {
            return (ApiConfigSource) this.configSourceSpecifier_;
        }
        return ApiConfigSource.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public ApiConfigSourceOrBuilder getApiConfigSourceOrBuilder() {
        if (this.configSourceSpecifierCase_ == 2) {
            return (ApiConfigSource) this.configSourceSpecifier_;
        }
        return ApiConfigSource.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public AggregatedConfigSource getAds() {
        if (this.configSourceSpecifierCase_ == 3) {
            return (AggregatedConfigSource) this.configSourceSpecifier_;
        }
        return AggregatedConfigSource.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public AggregatedConfigSourceOrBuilder getAdsOrBuilder() {
        if (this.configSourceSpecifierCase_ == 3) {
            return (AggregatedConfigSource) this.configSourceSpecifier_;
        }
        return AggregatedConfigSource.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public SelfConfigSource getSelf() {
        if (this.configSourceSpecifierCase_ == 5) {
            return (SelfConfigSource) this.configSourceSpecifier_;
        }
        return SelfConfigSource.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public SelfConfigSourceOrBuilder getSelfOrBuilder() {
        if (this.configSourceSpecifierCase_ == 5) {
            return (SelfConfigSource) this.configSourceSpecifier_;
        }
        return SelfConfigSource.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public Duration getInitialFetchTimeout() {
        Duration duration = this.initialFetchTimeout_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public DurationOrBuilder getInitialFetchTimeoutOrBuilder() {
        return getInitialFetchTimeout();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
    public ApiVersion getResourceApiVersion() {
        ApiVersion apiVersionValueOf = ApiVersion.valueOf(this.resourceApiVersion_);
        return apiVersionValueOf == null ? ApiVersion.UNRECOGNIZED : apiVersionValueOf;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.configSourceSpecifierCase_ == 1) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.configSourceSpecifier_);
        }
        if (this.configSourceSpecifierCase_ == 2) {
            codedOutputStream.writeMessage(2, (ApiConfigSource) this.configSourceSpecifier_);
        }
        if (this.configSourceSpecifierCase_ == 3) {
            codedOutputStream.writeMessage(3, (AggregatedConfigSource) this.configSourceSpecifier_);
        }
        if (this.initialFetchTimeout_ != null) {
            codedOutputStream.writeMessage(4, getInitialFetchTimeout());
        }
        if (this.configSourceSpecifierCase_ == 5) {
            codedOutputStream.writeMessage(5, (SelfConfigSource) this.configSourceSpecifier_);
        }
        if (this.resourceApiVersion_ != ApiVersion.AUTO.getNumber()) {
            codedOutputStream.writeEnum(6, this.resourceApiVersion_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = this.configSourceSpecifierCase_ == 1 ? GeneratedMessageV3.computeStringSize(1, this.configSourceSpecifier_) : 0;
        if (this.configSourceSpecifierCase_ == 2) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, (ApiConfigSource) this.configSourceSpecifier_);
        }
        if (this.configSourceSpecifierCase_ == 3) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, (AggregatedConfigSource) this.configSourceSpecifier_);
        }
        if (this.initialFetchTimeout_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(4, getInitialFetchTimeout());
        }
        if (this.configSourceSpecifierCase_ == 5) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(5, (SelfConfigSource) this.configSourceSpecifier_);
        }
        if (this.resourceApiVersion_ != ApiVersion.AUTO.getNumber()) {
            iComputeStringSize += CodedOutputStream.computeEnumSize(6, this.resourceApiVersion_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConfigSource)) {
            return super.equals(obj);
        }
        ConfigSource configSource = (ConfigSource) obj;
        if (hasInitialFetchTimeout() != configSource.hasInitialFetchTimeout()) {
            return false;
        }
        if ((hasInitialFetchTimeout() && !getInitialFetchTimeout().equals(configSource.getInitialFetchTimeout())) || this.resourceApiVersion_ != configSource.resourceApiVersion_ || !getConfigSourceSpecifierCase().equals(configSource.getConfigSourceSpecifierCase())) {
            return false;
        }
        int i = this.configSourceSpecifierCase_;
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    if (!getAds().equals(configSource.getAds())) {
                        return false;
                    }
                } else if (i == 5 && !getSelf().equals(configSource.getSelf())) {
                    return false;
                }
            } else if (!getApiConfigSource().equals(configSource.getApiConfigSource())) {
                return false;
            }
        } else if (!getPath().equals(configSource.getPath())) {
            return false;
        }
        return this.unknownFields.equals(configSource.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        if (hasInitialFetchTimeout()) {
            iHashCode2 = (((iHashCode2 * 37) + 4) * 53) + getInitialFetchTimeout().hashCode();
        }
        int i2 = (((iHashCode2 * 37) + 6) * 53) + this.resourceApiVersion_;
        int i3 = this.configSourceSpecifierCase_;
        if (i3 == 1) {
            i = ((i2 * 37) + 1) * 53;
            iHashCode = getPath().hashCode();
        } else if (i3 == 2) {
            i = ((i2 * 37) + 2) * 53;
            iHashCode = getApiConfigSource().hashCode();
        } else if (i3 == 3) {
            i = ((i2 * 37) + 3) * 53;
            iHashCode = getAds().hashCode();
        } else {
            if (i3 == 5) {
                i = ((i2 * 37) + 5) * 53;
                iHashCode = getSelf().hashCode();
            }
            int iHashCode3 = (i2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode3;
            return iHashCode3;
        }
        i2 = i + iHashCode;
        int iHashCode32 = (i2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode32;
        return iHashCode32;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14625newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14628toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ConfigSourceSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        PATH(1),
        API_CONFIG_SOURCE(2),
        ADS(3),
        SELF(5),
        CONFIGSOURCESPECIFIER_NOT_SET(0);

        private final int value;

        ConfigSourceSpecifierCase(int i) {
            this.value = i;
        }

        public static ConfigSourceSpecifierCase forNumber(int i) {
            if (i == 0) {
                return CONFIGSOURCESPECIFIER_NOT_SET;
            }
            if (i == 1) {
                return PATH;
            }
            if (i == 2) {
                return API_CONFIG_SOURCE;
            }
            if (i == 3) {
                return ADS;
            }
            if (i != 5) {
                return null;
            }
            return SELF;
        }

        @Deprecated
        public static ConfigSourceSpecifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ConfigSourceOrBuilder {
        private SingleFieldBuilderV3<AggregatedConfigSource, AggregatedConfigSource.Builder, AggregatedConfigSourceOrBuilder> adsBuilder_;
        private SingleFieldBuilderV3<ApiConfigSource, ApiConfigSource.Builder, ApiConfigSourceOrBuilder> apiConfigSourceBuilder_;
        private int configSourceSpecifierCase_;
        private Object configSourceSpecifier_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> initialFetchTimeoutBuilder_;
        private Duration initialFetchTimeout_;
        private int resourceApiVersion_;
        private SingleFieldBuilderV3<SelfConfigSource, SelfConfigSource.Builder, SelfConfigSourceOrBuilder> selfBuilder_;

        private Builder() {
            this.configSourceSpecifierCase_ = 0;
            this.resourceApiVersion_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.configSourceSpecifierCase_ = 0;
            this.resourceApiVersion_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ConfigSourceProto.internal_static_envoy_api_v2_core_ConfigSource_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public int getResourceApiVersionValue() {
            return this.resourceApiVersion_;
        }

        public Builder setResourceApiVersionValue(int i) {
            this.resourceApiVersion_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public boolean hasAds() {
            return this.configSourceSpecifierCase_ == 3;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public boolean hasApiConfigSource() {
            return this.configSourceSpecifierCase_ == 2;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public boolean hasInitialFetchTimeout() {
            return (this.initialFetchTimeoutBuilder_ == null && this.initialFetchTimeout_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public boolean hasSelf() {
            return this.configSourceSpecifierCase_ == 5;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ConfigSourceProto.internal_static_envoy_api_v2_core_ConfigSource_fieldAccessorTable.ensureFieldAccessorsInitialized(ConfigSource.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ConfigSource.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14639clear() {
            super.clear();
            if (this.initialFetchTimeoutBuilder_ == null) {
                this.initialFetchTimeout_ = null;
            } else {
                this.initialFetchTimeout_ = null;
                this.initialFetchTimeoutBuilder_ = null;
            }
            this.resourceApiVersion_ = 0;
            this.configSourceSpecifierCase_ = 0;
            this.configSourceSpecifier_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ConfigSourceProto.internal_static_envoy_api_v2_core_ConfigSource_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ConfigSource m14652getDefaultInstanceForType() {
            return ConfigSource.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ConfigSource m14633build() throws UninitializedMessageException {
            ConfigSource configSourceM14635buildPartial = m14635buildPartial();
            if (configSourceM14635buildPartial.isInitialized()) {
                return configSourceM14635buildPartial;
            }
            throw newUninitializedMessageException(configSourceM14635buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ConfigSource m14635buildPartial() {
            ConfigSource configSource = new ConfigSource(this);
            if (this.configSourceSpecifierCase_ == 1) {
                configSource.configSourceSpecifier_ = this.configSourceSpecifier_;
            }
            if (this.configSourceSpecifierCase_ == 2) {
                SingleFieldBuilderV3<ApiConfigSource, ApiConfigSource.Builder, ApiConfigSourceOrBuilder> singleFieldBuilderV3 = this.apiConfigSourceBuilder_;
                if (singleFieldBuilderV3 == null) {
                    configSource.configSourceSpecifier_ = this.configSourceSpecifier_;
                } else {
                    configSource.configSourceSpecifier_ = singleFieldBuilderV3.build();
                }
            }
            if (this.configSourceSpecifierCase_ == 3) {
                SingleFieldBuilderV3<AggregatedConfigSource, AggregatedConfigSource.Builder, AggregatedConfigSourceOrBuilder> singleFieldBuilderV32 = this.adsBuilder_;
                if (singleFieldBuilderV32 == null) {
                    configSource.configSourceSpecifier_ = this.configSourceSpecifier_;
                } else {
                    configSource.configSourceSpecifier_ = singleFieldBuilderV32.build();
                }
            }
            if (this.configSourceSpecifierCase_ == 5) {
                SingleFieldBuilderV3<SelfConfigSource, SelfConfigSource.Builder, SelfConfigSourceOrBuilder> singleFieldBuilderV33 = this.selfBuilder_;
                if (singleFieldBuilderV33 == null) {
                    configSource.configSourceSpecifier_ = this.configSourceSpecifier_;
                } else {
                    configSource.configSourceSpecifier_ = singleFieldBuilderV33.build();
                }
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV34 = this.initialFetchTimeoutBuilder_;
            if (singleFieldBuilderV34 == null) {
                configSource.initialFetchTimeout_ = this.initialFetchTimeout_;
            } else {
                configSource.initialFetchTimeout_ = singleFieldBuilderV34.build();
            }
            configSource.resourceApiVersion_ = this.resourceApiVersion_;
            configSource.configSourceSpecifierCase_ = this.configSourceSpecifierCase_;
            onBuilt();
            return configSource;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14651clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14663setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14641clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14644clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14665setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14631addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14656mergeFrom(Message message) {
            if (message instanceof ConfigSource) {
                return mergeFrom((ConfigSource) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ConfigSource configSource) {
            if (configSource == ConfigSource.getDefaultInstance()) {
                return this;
            }
            if (configSource.hasInitialFetchTimeout()) {
                mergeInitialFetchTimeout(configSource.getInitialFetchTimeout());
            }
            if (configSource.resourceApiVersion_ != 0) {
                setResourceApiVersionValue(configSource.getResourceApiVersionValue());
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$api$v2$core$ConfigSource$ConfigSourceSpecifierCase[configSource.getConfigSourceSpecifierCase().ordinal()];
            if (i == 1) {
                this.configSourceSpecifierCase_ = 1;
                this.configSourceSpecifier_ = configSource.configSourceSpecifier_;
                onChanged();
            } else if (i == 2) {
                mergeApiConfigSource(configSource.getApiConfigSource());
            } else if (i == 3) {
                mergeAds(configSource.getAds());
            } else if (i == 4) {
                mergeSelf(configSource.getSelf());
            }
            m14661mergeUnknownFields(configSource.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource.Builder m14657mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource.Builder.m14657mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public ConfigSourceSpecifierCase getConfigSourceSpecifierCase() {
            return ConfigSourceSpecifierCase.forNumber(this.configSourceSpecifierCase_);
        }

        public Builder clearConfigSourceSpecifier() {
            this.configSourceSpecifierCase_ = 0;
            this.configSourceSpecifier_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public String getPath() {
            String str = this.configSourceSpecifierCase_ == 1 ? this.configSourceSpecifier_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.configSourceSpecifierCase_ == 1) {
                    this.configSourceSpecifier_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setPath(String str) {
            str.getClass();
            this.configSourceSpecifierCase_ = 1;
            this.configSourceSpecifier_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public ByteString getPathBytes() {
            String str = this.configSourceSpecifierCase_ == 1 ? this.configSourceSpecifier_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.configSourceSpecifierCase_ == 1) {
                    this.configSourceSpecifier_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setPathBytes(ByteString byteString) {
            byteString.getClass();
            ConfigSource.checkByteStringIsUtf8(byteString);
            this.configSourceSpecifierCase_ = 1;
            this.configSourceSpecifier_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearPath() {
            if (this.configSourceSpecifierCase_ == 1) {
                this.configSourceSpecifierCase_ = 0;
                this.configSourceSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public ApiConfigSource getApiConfigSource() {
            SingleFieldBuilderV3<ApiConfigSource, ApiConfigSource.Builder, ApiConfigSourceOrBuilder> singleFieldBuilderV3 = this.apiConfigSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configSourceSpecifierCase_ == 2) {
                    return (ApiConfigSource) this.configSourceSpecifier_;
                }
                return ApiConfigSource.getDefaultInstance();
            }
            if (this.configSourceSpecifierCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return ApiConfigSource.getDefaultInstance();
        }

        public Builder setApiConfigSource(ApiConfigSource apiConfigSource) {
            SingleFieldBuilderV3<ApiConfigSource, ApiConfigSource.Builder, ApiConfigSourceOrBuilder> singleFieldBuilderV3 = this.apiConfigSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                apiConfigSource.getClass();
                this.configSourceSpecifier_ = apiConfigSource;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(apiConfigSource);
            }
            this.configSourceSpecifierCase_ = 2;
            return this;
        }

        public Builder setApiConfigSource(ApiConfigSource.Builder builder) {
            SingleFieldBuilderV3<ApiConfigSource, ApiConfigSource.Builder, ApiConfigSourceOrBuilder> singleFieldBuilderV3 = this.apiConfigSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.configSourceSpecifier_ = builder.m14356build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m14356build());
            }
            this.configSourceSpecifierCase_ = 2;
            return this;
        }

        public Builder mergeApiConfigSource(ApiConfigSource apiConfigSource) {
            SingleFieldBuilderV3<ApiConfigSource, ApiConfigSource.Builder, ApiConfigSourceOrBuilder> singleFieldBuilderV3 = this.apiConfigSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configSourceSpecifierCase_ != 2 || this.configSourceSpecifier_ == ApiConfigSource.getDefaultInstance()) {
                    this.configSourceSpecifier_ = apiConfigSource;
                } else {
                    this.configSourceSpecifier_ = ApiConfigSource.newBuilder((ApiConfigSource) this.configSourceSpecifier_).mergeFrom(apiConfigSource).m14358buildPartial();
                }
                onChanged();
            } else {
                if (this.configSourceSpecifierCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(apiConfigSource);
                }
                this.apiConfigSourceBuilder_.setMessage(apiConfigSource);
            }
            this.configSourceSpecifierCase_ = 2;
            return this;
        }

        public Builder clearApiConfigSource() {
            SingleFieldBuilderV3<ApiConfigSource, ApiConfigSource.Builder, ApiConfigSourceOrBuilder> singleFieldBuilderV3 = this.apiConfigSourceBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.configSourceSpecifierCase_ == 2) {
                    this.configSourceSpecifierCase_ = 0;
                    this.configSourceSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.configSourceSpecifierCase_ == 2) {
                this.configSourceSpecifierCase_ = 0;
                this.configSourceSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public ApiConfigSource.Builder getApiConfigSourceBuilder() {
            return getApiConfigSourceFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public ApiConfigSourceOrBuilder getApiConfigSourceOrBuilder() {
            SingleFieldBuilderV3<ApiConfigSource, ApiConfigSource.Builder, ApiConfigSourceOrBuilder> singleFieldBuilderV3;
            int i = this.configSourceSpecifierCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.apiConfigSourceBuilder_) != null) {
                return (ApiConfigSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (ApiConfigSource) this.configSourceSpecifier_;
            }
            return ApiConfigSource.getDefaultInstance();
        }

        private SingleFieldBuilderV3<ApiConfigSource, ApiConfigSource.Builder, ApiConfigSourceOrBuilder> getApiConfigSourceFieldBuilder() {
            if (this.apiConfigSourceBuilder_ == null) {
                if (this.configSourceSpecifierCase_ != 2) {
                    this.configSourceSpecifier_ = ApiConfigSource.getDefaultInstance();
                }
                this.apiConfigSourceBuilder_ = new SingleFieldBuilderV3<>((ApiConfigSource) this.configSourceSpecifier_, getParentForChildren(), isClean());
                this.configSourceSpecifier_ = null;
            }
            this.configSourceSpecifierCase_ = 2;
            onChanged();
            return this.apiConfigSourceBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public AggregatedConfigSource getAds() {
            SingleFieldBuilderV3<AggregatedConfigSource, AggregatedConfigSource.Builder, AggregatedConfigSourceOrBuilder> singleFieldBuilderV3 = this.adsBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configSourceSpecifierCase_ == 3) {
                    return (AggregatedConfigSource) this.configSourceSpecifier_;
                }
                return AggregatedConfigSource.getDefaultInstance();
            }
            if (this.configSourceSpecifierCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return AggregatedConfigSource.getDefaultInstance();
        }

        public Builder setAds(AggregatedConfigSource aggregatedConfigSource) {
            SingleFieldBuilderV3<AggregatedConfigSource, AggregatedConfigSource.Builder, AggregatedConfigSourceOrBuilder> singleFieldBuilderV3 = this.adsBuilder_;
            if (singleFieldBuilderV3 == null) {
                aggregatedConfigSource.getClass();
                this.configSourceSpecifier_ = aggregatedConfigSource;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(aggregatedConfigSource);
            }
            this.configSourceSpecifierCase_ = 3;
            return this;
        }

        public Builder setAds(AggregatedConfigSource.Builder builder) {
            SingleFieldBuilderV3<AggregatedConfigSource, AggregatedConfigSource.Builder, AggregatedConfigSourceOrBuilder> singleFieldBuilderV3 = this.adsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.configSourceSpecifier_ = builder.m14308build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m14308build());
            }
            this.configSourceSpecifierCase_ = 3;
            return this;
        }

        public Builder mergeAds(AggregatedConfigSource aggregatedConfigSource) {
            SingleFieldBuilderV3<AggregatedConfigSource, AggregatedConfigSource.Builder, AggregatedConfigSourceOrBuilder> singleFieldBuilderV3 = this.adsBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configSourceSpecifierCase_ != 3 || this.configSourceSpecifier_ == AggregatedConfigSource.getDefaultInstance()) {
                    this.configSourceSpecifier_ = aggregatedConfigSource;
                } else {
                    this.configSourceSpecifier_ = AggregatedConfigSource.newBuilder((AggregatedConfigSource) this.configSourceSpecifier_).mergeFrom(aggregatedConfigSource).m14310buildPartial();
                }
                onChanged();
            } else {
                if (this.configSourceSpecifierCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(aggregatedConfigSource);
                }
                this.adsBuilder_.setMessage(aggregatedConfigSource);
            }
            this.configSourceSpecifierCase_ = 3;
            return this;
        }

        public Builder clearAds() {
            SingleFieldBuilderV3<AggregatedConfigSource, AggregatedConfigSource.Builder, AggregatedConfigSourceOrBuilder> singleFieldBuilderV3 = this.adsBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.configSourceSpecifierCase_ == 3) {
                    this.configSourceSpecifierCase_ = 0;
                    this.configSourceSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.configSourceSpecifierCase_ == 3) {
                this.configSourceSpecifierCase_ = 0;
                this.configSourceSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public AggregatedConfigSource.Builder getAdsBuilder() {
            return getAdsFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public AggregatedConfigSourceOrBuilder getAdsOrBuilder() {
            SingleFieldBuilderV3<AggregatedConfigSource, AggregatedConfigSource.Builder, AggregatedConfigSourceOrBuilder> singleFieldBuilderV3;
            int i = this.configSourceSpecifierCase_;
            if (i == 3 && (singleFieldBuilderV3 = this.adsBuilder_) != null) {
                return (AggregatedConfigSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 3) {
                return (AggregatedConfigSource) this.configSourceSpecifier_;
            }
            return AggregatedConfigSource.getDefaultInstance();
        }

        private SingleFieldBuilderV3<AggregatedConfigSource, AggregatedConfigSource.Builder, AggregatedConfigSourceOrBuilder> getAdsFieldBuilder() {
            if (this.adsBuilder_ == null) {
                if (this.configSourceSpecifierCase_ != 3) {
                    this.configSourceSpecifier_ = AggregatedConfigSource.getDefaultInstance();
                }
                this.adsBuilder_ = new SingleFieldBuilderV3<>((AggregatedConfigSource) this.configSourceSpecifier_, getParentForChildren(), isClean());
                this.configSourceSpecifier_ = null;
            }
            this.configSourceSpecifierCase_ = 3;
            onChanged();
            return this.adsBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public SelfConfigSource getSelf() {
            SingleFieldBuilderV3<SelfConfigSource, SelfConfigSource.Builder, SelfConfigSourceOrBuilder> singleFieldBuilderV3 = this.selfBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configSourceSpecifierCase_ == 5) {
                    return (SelfConfigSource) this.configSourceSpecifier_;
                }
                return SelfConfigSource.getDefaultInstance();
            }
            if (this.configSourceSpecifierCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return SelfConfigSource.getDefaultInstance();
        }

        public Builder setSelf(SelfConfigSource selfConfigSource) {
            SingleFieldBuilderV3<SelfConfigSource, SelfConfigSource.Builder, SelfConfigSourceOrBuilder> singleFieldBuilderV3 = this.selfBuilder_;
            if (singleFieldBuilderV3 == null) {
                selfConfigSource.getClass();
                this.configSourceSpecifier_ = selfConfigSource;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(selfConfigSource);
            }
            this.configSourceSpecifierCase_ = 5;
            return this;
        }

        public Builder setSelf(SelfConfigSource.Builder builder) {
            SingleFieldBuilderV3<SelfConfigSource, SelfConfigSource.Builder, SelfConfigSourceOrBuilder> singleFieldBuilderV3 = this.selfBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.configSourceSpecifier_ = builder.m16756build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m16756build());
            }
            this.configSourceSpecifierCase_ = 5;
            return this;
        }

        public Builder mergeSelf(SelfConfigSource selfConfigSource) {
            SingleFieldBuilderV3<SelfConfigSource, SelfConfigSource.Builder, SelfConfigSourceOrBuilder> singleFieldBuilderV3 = this.selfBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configSourceSpecifierCase_ != 5 || this.configSourceSpecifier_ == SelfConfigSource.getDefaultInstance()) {
                    this.configSourceSpecifier_ = selfConfigSource;
                } else {
                    this.configSourceSpecifier_ = SelfConfigSource.newBuilder((SelfConfigSource) this.configSourceSpecifier_).mergeFrom(selfConfigSource).m16758buildPartial();
                }
                onChanged();
            } else {
                if (this.configSourceSpecifierCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(selfConfigSource);
                }
                this.selfBuilder_.setMessage(selfConfigSource);
            }
            this.configSourceSpecifierCase_ = 5;
            return this;
        }

        public Builder clearSelf() {
            SingleFieldBuilderV3<SelfConfigSource, SelfConfigSource.Builder, SelfConfigSourceOrBuilder> singleFieldBuilderV3 = this.selfBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.configSourceSpecifierCase_ == 5) {
                    this.configSourceSpecifierCase_ = 0;
                    this.configSourceSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.configSourceSpecifierCase_ == 5) {
                this.configSourceSpecifierCase_ = 0;
                this.configSourceSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public SelfConfigSource.Builder getSelfBuilder() {
            return getSelfFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public SelfConfigSourceOrBuilder getSelfOrBuilder() {
            SingleFieldBuilderV3<SelfConfigSource, SelfConfigSource.Builder, SelfConfigSourceOrBuilder> singleFieldBuilderV3;
            int i = this.configSourceSpecifierCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.selfBuilder_) != null) {
                return (SelfConfigSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (SelfConfigSource) this.configSourceSpecifier_;
            }
            return SelfConfigSource.getDefaultInstance();
        }

        private SingleFieldBuilderV3<SelfConfigSource, SelfConfigSource.Builder, SelfConfigSourceOrBuilder> getSelfFieldBuilder() {
            if (this.selfBuilder_ == null) {
                if (this.configSourceSpecifierCase_ != 5) {
                    this.configSourceSpecifier_ = SelfConfigSource.getDefaultInstance();
                }
                this.selfBuilder_ = new SingleFieldBuilderV3<>((SelfConfigSource) this.configSourceSpecifier_, getParentForChildren(), isClean());
                this.configSourceSpecifier_ = null;
            }
            this.configSourceSpecifierCase_ = 5;
            onChanged();
            return this.selfBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public Duration getInitialFetchTimeout() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.initialFetchTimeoutBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.initialFetchTimeout_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setInitialFetchTimeout(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.initialFetchTimeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.initialFetchTimeout_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setInitialFetchTimeout(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.initialFetchTimeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.initialFetchTimeout_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeInitialFetchTimeout(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.initialFetchTimeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.initialFetchTimeout_;
                if (duration2 != null) {
                    this.initialFetchTimeout_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.initialFetchTimeout_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearInitialFetchTimeout() {
            if (this.initialFetchTimeoutBuilder_ == null) {
                this.initialFetchTimeout_ = null;
                onChanged();
            } else {
                this.initialFetchTimeout_ = null;
                this.initialFetchTimeoutBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getInitialFetchTimeoutBuilder() {
            onChanged();
            return getInitialFetchTimeoutFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public DurationOrBuilder getInitialFetchTimeoutOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.initialFetchTimeoutBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.initialFetchTimeout_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getInitialFetchTimeoutFieldBuilder() {
            if (this.initialFetchTimeoutBuilder_ == null) {
                this.initialFetchTimeoutBuilder_ = new SingleFieldBuilderV3<>(getInitialFetchTimeout(), getParentForChildren(), isClean());
                this.initialFetchTimeout_ = null;
            }
            return this.initialFetchTimeoutBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder
        public ApiVersion getResourceApiVersion() {
            ApiVersion apiVersionValueOf = ApiVersion.valueOf(this.resourceApiVersion_);
            return apiVersionValueOf == null ? ApiVersion.UNRECOGNIZED : apiVersionValueOf;
        }

        public Builder setResourceApiVersion(ApiVersion apiVersion) {
            apiVersion.getClass();
            this.resourceApiVersion_ = apiVersion.getNumber();
            onChanged();
            return this;
        }

        public Builder clearResourceApiVersion() {
            this.resourceApiVersion_ = 0;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14667setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14661mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$api$v2$core$ConfigSource$ConfigSourceSpecifierCase;

        static {
            int[] iArr = new int[ConfigSourceSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$api$v2$core$ConfigSource$ConfigSourceSpecifierCase = iArr;
            try {
                iArr[ConfigSourceSpecifierCase.PATH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$ConfigSource$ConfigSourceSpecifierCase[ConfigSourceSpecifierCase.API_CONFIG_SOURCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$ConfigSource$ConfigSourceSpecifierCase[ConfigSourceSpecifierCase.ADS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$ConfigSource$ConfigSourceSpecifierCase[ConfigSourceSpecifierCase.SELF.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$ConfigSource$ConfigSourceSpecifierCase[ConfigSourceSpecifierCase.CONFIGSOURCESPECIFIER_NOT_SET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
