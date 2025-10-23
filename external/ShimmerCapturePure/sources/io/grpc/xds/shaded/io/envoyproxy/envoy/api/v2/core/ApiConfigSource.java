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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RateLimitSettings;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class ApiConfigSource extends GeneratedMessageV3 implements ApiConfigSourceOrBuilder {
    public static final int API_TYPE_FIELD_NUMBER = 1;
    public static final int CLUSTER_NAMES_FIELD_NUMBER = 2;
    public static final int GRPC_SERVICES_FIELD_NUMBER = 4;
    public static final int RATE_LIMIT_SETTINGS_FIELD_NUMBER = 6;
    public static final int REFRESH_DELAY_FIELD_NUMBER = 3;
    public static final int REQUEST_TIMEOUT_FIELD_NUMBER = 5;
    public static final int SET_NODE_ON_FIRST_MESSAGE_ONLY_FIELD_NUMBER = 7;
    public static final int TRANSPORT_API_VERSION_FIELD_NUMBER = 8;
    private static final long serialVersionUID = 0;
    private static final ApiConfigSource DEFAULT_INSTANCE = new ApiConfigSource();
    private static final Parser<ApiConfigSource> PARSER = new AbstractParser<ApiConfigSource>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSource.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ApiConfigSource m14352parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ApiConfigSource(codedInputStream, extensionRegistryLite);
        }
    };
    private int apiType_;
    private LazyStringList clusterNames_;
    private List<GrpcService> grpcServices_;
    private byte memoizedIsInitialized;
    private RateLimitSettings rateLimitSettings_;
    private Duration refreshDelay_;
    private Duration requestTimeout_;
    private boolean setNodeOnFirstMessageOnly_;
    private int transportApiVersion_;

    private ApiConfigSource(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ApiConfigSource() {
        this.memoizedIsInitialized = (byte) -1;
        this.apiType_ = 0;
        this.transportApiVersion_ = 0;
        this.clusterNames_ = LazyStringArrayList.EMPTY;
        this.grpcServices_ = Collections.emptyList();
    }

    private ApiConfigSource(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Duration.Builder builderM16427toBuilder;
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
                            if (tag == 8) {
                                this.apiType_ = codedInputStream.readEnum();
                            } else if (tag != 18) {
                                if (tag == 26) {
                                    Duration duration = this.refreshDelay_;
                                    builderM16427toBuilder = duration != null ? duration.toBuilder() : null;
                                    Duration message = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                                    this.refreshDelay_ = message;
                                    if (builderM16427toBuilder != null) {
                                        builderM16427toBuilder.mergeFrom(message);
                                        this.refreshDelay_ = builderM16427toBuilder.buildPartial();
                                    }
                                } else if (tag == 34) {
                                    if ((i & 2) == 0) {
                                        this.grpcServices_ = new ArrayList();
                                        i |= 2;
                                    }
                                    this.grpcServices_.add(codedInputStream.readMessage(GrpcService.parser(), extensionRegistryLite));
                                } else if (tag == 42) {
                                    Duration duration2 = this.requestTimeout_;
                                    builderM16427toBuilder = duration2 != null ? duration2.toBuilder() : null;
                                    Duration message2 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                                    this.requestTimeout_ = message2;
                                    if (builderM16427toBuilder != null) {
                                        builderM16427toBuilder.mergeFrom(message2);
                                        this.requestTimeout_ = builderM16427toBuilder.buildPartial();
                                    }
                                } else if (tag == 50) {
                                    RateLimitSettings rateLimitSettings = this.rateLimitSettings_;
                                    builderM16427toBuilder = rateLimitSettings != null ? rateLimitSettings.m16427toBuilder() : null;
                                    RateLimitSettings rateLimitSettings2 = (RateLimitSettings) codedInputStream.readMessage(RateLimitSettings.parser(), extensionRegistryLite);
                                    this.rateLimitSettings_ = rateLimitSettings2;
                                    if (builderM16427toBuilder != null) {
                                        builderM16427toBuilder.mergeFrom(rateLimitSettings2);
                                        this.rateLimitSettings_ = builderM16427toBuilder.m16434buildPartial();
                                    }
                                } else if (tag == 56) {
                                    this.setNodeOnFirstMessageOnly_ = codedInputStream.readBool();
                                } else if (tag == 64) {
                                    this.transportApiVersion_ = codedInputStream.readEnum();
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if ((i & 1) == 0) {
                                    this.clusterNames_ = new LazyStringArrayList();
                                    i |= 1;
                                }
                                this.clusterNames_.add(stringRequireUtf8);
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
                if ((i & 1) != 0) {
                    this.clusterNames_ = this.clusterNames_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.grpcServices_ = Collections.unmodifiableList(this.grpcServices_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ApiConfigSource getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ApiConfigSource> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ConfigSourceProto.internal_static_envoy_api_v2_core_ApiConfigSource_descriptor;
    }

    public static ApiConfigSource parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ApiConfigSource) PARSER.parseFrom(byteBuffer);
    }

    public static ApiConfigSource parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ApiConfigSource) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ApiConfigSource parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ApiConfigSource) PARSER.parseFrom(byteString);
    }

    public static ApiConfigSource parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ApiConfigSource) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ApiConfigSource parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ApiConfigSource) PARSER.parseFrom(bArr);
    }

    public static ApiConfigSource parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ApiConfigSource) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ApiConfigSource parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ApiConfigSource parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ApiConfigSource parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ApiConfigSource parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ApiConfigSource parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ApiConfigSource parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m14350toBuilder();
    }

    public static Builder newBuilder(ApiConfigSource apiConfigSource) {
        return DEFAULT_INSTANCE.m14350toBuilder().mergeFrom(apiConfigSource);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public int getApiTypeValue() {
        return this.apiType_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    /* renamed from: getClusterNamesList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo14344getClusterNamesList() {
        return this.clusterNames_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ApiConfigSource m14345getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public List<GrpcService> getGrpcServicesList() {
        return this.grpcServices_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public List<? extends GrpcServiceOrBuilder> getGrpcServicesOrBuilderList() {
        return this.grpcServices_;
    }

    public Parser<ApiConfigSource> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public boolean getSetNodeOnFirstMessageOnly() {
        return this.setNodeOnFirstMessageOnly_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public int getTransportApiVersionValue() {
        return this.transportApiVersion_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public boolean hasRateLimitSettings() {
        return this.rateLimitSettings_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public boolean hasRefreshDelay() {
        return this.refreshDelay_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public boolean hasRequestTimeout() {
        return this.requestTimeout_ != null;
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
        return new ApiConfigSource();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ConfigSourceProto.internal_static_envoy_api_v2_core_ApiConfigSource_fieldAccessorTable.ensureFieldAccessorsInitialized(ApiConfigSource.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public ApiType getApiType() {
        ApiType apiTypeValueOf = ApiType.valueOf(this.apiType_);
        return apiTypeValueOf == null ? ApiType.UNRECOGNIZED : apiTypeValueOf;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public ApiVersion getTransportApiVersion() {
        ApiVersion apiVersionValueOf = ApiVersion.valueOf(this.transportApiVersion_);
        return apiVersionValueOf == null ? ApiVersion.UNRECOGNIZED : apiVersionValueOf;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public int getClusterNamesCount() {
        return this.clusterNames_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public String getClusterNames(int i) {
        return (String) this.clusterNames_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public ByteString getClusterNamesBytes(int i) {
        return this.clusterNames_.getByteString(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public int getGrpcServicesCount() {
        return this.grpcServices_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public GrpcService getGrpcServices(int i) {
        return this.grpcServices_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public GrpcServiceOrBuilder getGrpcServicesOrBuilder(int i) {
        return this.grpcServices_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public Duration getRefreshDelay() {
        Duration duration = this.refreshDelay_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public DurationOrBuilder getRefreshDelayOrBuilder() {
        return getRefreshDelay();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public Duration getRequestTimeout() {
        Duration duration = this.requestTimeout_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public DurationOrBuilder getRequestTimeoutOrBuilder() {
        return getRequestTimeout();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public RateLimitSettings getRateLimitSettings() {
        RateLimitSettings rateLimitSettings = this.rateLimitSettings_;
        return rateLimitSettings == null ? RateLimitSettings.getDefaultInstance() : rateLimitSettings;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
    public RateLimitSettingsOrBuilder getRateLimitSettingsOrBuilder() {
        return getRateLimitSettings();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.apiType_ != ApiType.UNSUPPORTED_REST_LEGACY.getNumber()) {
            codedOutputStream.writeEnum(1, this.apiType_);
        }
        for (int i = 0; i < this.clusterNames_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.clusterNames_.getRaw(i));
        }
        if (this.refreshDelay_ != null) {
            codedOutputStream.writeMessage(3, getRefreshDelay());
        }
        for (int i2 = 0; i2 < this.grpcServices_.size(); i2++) {
            codedOutputStream.writeMessage(4, this.grpcServices_.get(i2));
        }
        if (this.requestTimeout_ != null) {
            codedOutputStream.writeMessage(5, getRequestTimeout());
        }
        if (this.rateLimitSettings_ != null) {
            codedOutputStream.writeMessage(6, getRateLimitSettings());
        }
        boolean z = this.setNodeOnFirstMessageOnly_;
        if (z) {
            codedOutputStream.writeBool(7, z);
        }
        if (this.transportApiVersion_ != ApiVersion.AUTO.getNumber()) {
            codedOutputStream.writeEnum(8, this.transportApiVersion_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeEnumSize = this.apiType_ != ApiType.UNSUPPORTED_REST_LEGACY.getNumber() ? CodedOutputStream.computeEnumSize(1, this.apiType_) : 0;
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.clusterNames_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.clusterNames_.getRaw(i2));
        }
        int size = iComputeEnumSize + iComputeStringSizeNoTag + mo14344getClusterNamesList().size();
        if (this.refreshDelay_ != null) {
            size += CodedOutputStream.computeMessageSize(3, getRefreshDelay());
        }
        for (int i3 = 0; i3 < this.grpcServices_.size(); i3++) {
            size += CodedOutputStream.computeMessageSize(4, this.grpcServices_.get(i3));
        }
        if (this.requestTimeout_ != null) {
            size += CodedOutputStream.computeMessageSize(5, getRequestTimeout());
        }
        if (this.rateLimitSettings_ != null) {
            size += CodedOutputStream.computeMessageSize(6, getRateLimitSettings());
        }
        boolean z = this.setNodeOnFirstMessageOnly_;
        if (z) {
            size += CodedOutputStream.computeBoolSize(7, z);
        }
        if (this.transportApiVersion_ != ApiVersion.AUTO.getNumber()) {
            size += CodedOutputStream.computeEnumSize(8, this.transportApiVersion_);
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApiConfigSource)) {
            return super.equals(obj);
        }
        ApiConfigSource apiConfigSource = (ApiConfigSource) obj;
        if (this.apiType_ != apiConfigSource.apiType_ || this.transportApiVersion_ != apiConfigSource.transportApiVersion_ || !mo14344getClusterNamesList().equals(apiConfigSource.mo14344getClusterNamesList()) || !getGrpcServicesList().equals(apiConfigSource.getGrpcServicesList()) || hasRefreshDelay() != apiConfigSource.hasRefreshDelay()) {
            return false;
        }
        if ((hasRefreshDelay() && !getRefreshDelay().equals(apiConfigSource.getRefreshDelay())) || hasRequestTimeout() != apiConfigSource.hasRequestTimeout()) {
            return false;
        }
        if ((!hasRequestTimeout() || getRequestTimeout().equals(apiConfigSource.getRequestTimeout())) && hasRateLimitSettings() == apiConfigSource.hasRateLimitSettings()) {
            return (!hasRateLimitSettings() || getRateLimitSettings().equals(apiConfigSource.getRateLimitSettings())) && getSetNodeOnFirstMessageOnly() == apiConfigSource.getSetNodeOnFirstMessageOnly() && this.unknownFields.equals(apiConfigSource.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.apiType_) * 37) + 8) * 53) + this.transportApiVersion_;
        if (getClusterNamesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + mo14344getClusterNamesList().hashCode();
        }
        if (getGrpcServicesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getGrpcServicesList().hashCode();
        }
        if (hasRefreshDelay()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getRefreshDelay().hashCode();
        }
        if (hasRequestTimeout()) {
            iHashCode = (((iHashCode * 37) + 5) * 53) + getRequestTimeout().hashCode();
        }
        if (hasRateLimitSettings()) {
            iHashCode = (((iHashCode * 37) + 6) * 53) + getRateLimitSettings().hashCode();
        }
        int iHashBoolean = (((((iHashCode * 37) + 7) * 53) + Internal.hashBoolean(getSetNodeOnFirstMessageOnly())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashBoolean;
        return iHashBoolean;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14347newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14350toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ApiType implements ProtocolMessageEnum {
        UNSUPPORTED_REST_LEGACY(0),
        REST(1),
        GRPC(2),
        DELTA_GRPC(3),
        UNRECOGNIZED(-1);

        public static final int DELTA_GRPC_VALUE = 3;
        public static final int GRPC_VALUE = 2;
        public static final int REST_VALUE = 1;

        @Deprecated
        public static final int UNSUPPORTED_REST_LEGACY_VALUE = 0;
        private static final Internal.EnumLiteMap<ApiType> internalValueMap = new Internal.EnumLiteMap<ApiType>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSource.ApiType.1
            public ApiType findValueByNumber(int i) {
                return ApiType.forNumber(i);
            }
        };
        private static final ApiType[] VALUES = values();
        private final int value;

        ApiType(int i) {
            this.value = i;
        }

        public static ApiType forNumber(int i) {
            if (i == 0) {
                return UNSUPPORTED_REST_LEGACY;
            }
            if (i == 1) {
                return REST;
            }
            if (i == 2) {
                return GRPC;
            }
            if (i != 3) {
                return null;
            }
            return DELTA_GRPC;
        }

        public static Internal.EnumLiteMap<ApiType> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static ApiType valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) ApiConfigSource.getDescriptor().getEnumTypes().get(0);
        }

        public static ApiType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ApiConfigSourceOrBuilder {
        private int apiType_;
        private int bitField0_;
        private LazyStringList clusterNames_;
        private RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> grpcServicesBuilder_;
        private List<GrpcService> grpcServices_;
        private SingleFieldBuilderV3<RateLimitSettings, RateLimitSettings.Builder, RateLimitSettingsOrBuilder> rateLimitSettingsBuilder_;
        private RateLimitSettings rateLimitSettings_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> refreshDelayBuilder_;
        private Duration refreshDelay_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> requestTimeoutBuilder_;
        private Duration requestTimeout_;
        private boolean setNodeOnFirstMessageOnly_;
        private int transportApiVersion_;

        private Builder() {
            this.apiType_ = 0;
            this.transportApiVersion_ = 0;
            this.clusterNames_ = LazyStringArrayList.EMPTY;
            this.grpcServices_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.apiType_ = 0;
            this.transportApiVersion_ = 0;
            this.clusterNames_ = LazyStringArrayList.EMPTY;
            this.grpcServices_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ConfigSourceProto.internal_static_envoy_api_v2_core_ApiConfigSource_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public int getApiTypeValue() {
            return this.apiType_;
        }

        public Builder setApiTypeValue(int i) {
            this.apiType_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public boolean getSetNodeOnFirstMessageOnly() {
            return this.setNodeOnFirstMessageOnly_;
        }

        public Builder setSetNodeOnFirstMessageOnly(boolean z) {
            this.setNodeOnFirstMessageOnly_ = z;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public int getTransportApiVersionValue() {
            return this.transportApiVersion_;
        }

        public Builder setTransportApiVersionValue(int i) {
            this.transportApiVersion_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public boolean hasRateLimitSettings() {
            return (this.rateLimitSettingsBuilder_ == null && this.rateLimitSettings_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public boolean hasRefreshDelay() {
            return (this.refreshDelayBuilder_ == null && this.refreshDelay_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public boolean hasRequestTimeout() {
            return (this.requestTimeoutBuilder_ == null && this.requestTimeout_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ConfigSourceProto.internal_static_envoy_api_v2_core_ApiConfigSource_fieldAccessorTable.ensureFieldAccessorsInitialized(ApiConfigSource.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (ApiConfigSource.alwaysUseFieldBuilders) {
                getGrpcServicesFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14362clear() {
            super.clear();
            this.apiType_ = 0;
            this.transportApiVersion_ = 0;
            this.clusterNames_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.grpcServices_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            if (this.refreshDelayBuilder_ == null) {
                this.refreshDelay_ = null;
            } else {
                this.refreshDelay_ = null;
                this.refreshDelayBuilder_ = null;
            }
            if (this.requestTimeoutBuilder_ == null) {
                this.requestTimeout_ = null;
            } else {
                this.requestTimeout_ = null;
                this.requestTimeoutBuilder_ = null;
            }
            if (this.rateLimitSettingsBuilder_ == null) {
                this.rateLimitSettings_ = null;
            } else {
                this.rateLimitSettings_ = null;
                this.rateLimitSettingsBuilder_ = null;
            }
            this.setNodeOnFirstMessageOnly_ = false;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ConfigSourceProto.internal_static_envoy_api_v2_core_ApiConfigSource_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ApiConfigSource m14375getDefaultInstanceForType() {
            return ApiConfigSource.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ApiConfigSource m14356build() throws UninitializedMessageException {
            ApiConfigSource apiConfigSourceM14358buildPartial = m14358buildPartial();
            if (apiConfigSourceM14358buildPartial.isInitialized()) {
                return apiConfigSourceM14358buildPartial;
            }
            throw newUninitializedMessageException(apiConfigSourceM14358buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ApiConfigSource m14358buildPartial() {
            ApiConfigSource apiConfigSource = new ApiConfigSource(this);
            apiConfigSource.apiType_ = this.apiType_;
            apiConfigSource.transportApiVersion_ = this.transportApiVersion_;
            if ((this.bitField0_ & 1) != 0) {
                this.clusterNames_ = this.clusterNames_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            apiConfigSource.clusterNames_ = this.clusterNames_;
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) != 0) {
                    this.grpcServices_ = Collections.unmodifiableList(this.grpcServices_);
                    this.bitField0_ &= -3;
                }
                apiConfigSource.grpcServices_ = this.grpcServices_;
            } else {
                apiConfigSource.grpcServices_ = repeatedFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.refreshDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                apiConfigSource.refreshDelay_ = this.refreshDelay_;
            } else {
                apiConfigSource.refreshDelay_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV32 = this.requestTimeoutBuilder_;
            if (singleFieldBuilderV32 == null) {
                apiConfigSource.requestTimeout_ = this.requestTimeout_;
            } else {
                apiConfigSource.requestTimeout_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<RateLimitSettings, RateLimitSettings.Builder, RateLimitSettingsOrBuilder> singleFieldBuilderV33 = this.rateLimitSettingsBuilder_;
            if (singleFieldBuilderV33 == null) {
                apiConfigSource.rateLimitSettings_ = this.rateLimitSettings_;
            } else {
                apiConfigSource.rateLimitSettings_ = singleFieldBuilderV33.build();
            }
            apiConfigSource.setNodeOnFirstMessageOnly_ = this.setNodeOnFirstMessageOnly_;
            onBuilt();
            return apiConfigSource;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14374clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14386setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14364clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14367clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14388setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14354addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14379mergeFrom(Message message) {
            if (message instanceof ApiConfigSource) {
                return mergeFrom((ApiConfigSource) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ApiConfigSource apiConfigSource) {
            if (apiConfigSource == ApiConfigSource.getDefaultInstance()) {
                return this;
            }
            if (apiConfigSource.apiType_ != 0) {
                setApiTypeValue(apiConfigSource.getApiTypeValue());
            }
            if (apiConfigSource.transportApiVersion_ != 0) {
                setTransportApiVersionValue(apiConfigSource.getTransportApiVersionValue());
            }
            if (!apiConfigSource.clusterNames_.isEmpty()) {
                if (this.clusterNames_.isEmpty()) {
                    this.clusterNames_ = apiConfigSource.clusterNames_;
                    this.bitField0_ &= -2;
                } else {
                    ensureClusterNamesIsMutable();
                    this.clusterNames_.addAll(apiConfigSource.clusterNames_);
                }
                onChanged();
            }
            if (this.grpcServicesBuilder_ == null) {
                if (!apiConfigSource.grpcServices_.isEmpty()) {
                    if (this.grpcServices_.isEmpty()) {
                        this.grpcServices_ = apiConfigSource.grpcServices_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureGrpcServicesIsMutable();
                        this.grpcServices_.addAll(apiConfigSource.grpcServices_);
                    }
                    onChanged();
                }
            } else if (!apiConfigSource.grpcServices_.isEmpty()) {
                if (!this.grpcServicesBuilder_.isEmpty()) {
                    this.grpcServicesBuilder_.addAllMessages(apiConfigSource.grpcServices_);
                } else {
                    this.grpcServicesBuilder_.dispose();
                    this.grpcServicesBuilder_ = null;
                    this.grpcServices_ = apiConfigSource.grpcServices_;
                    this.bitField0_ &= -3;
                    this.grpcServicesBuilder_ = ApiConfigSource.alwaysUseFieldBuilders ? getGrpcServicesFieldBuilder() : null;
                }
            }
            if (apiConfigSource.hasRefreshDelay()) {
                mergeRefreshDelay(apiConfigSource.getRefreshDelay());
            }
            if (apiConfigSource.hasRequestTimeout()) {
                mergeRequestTimeout(apiConfigSource.getRequestTimeout());
            }
            if (apiConfigSource.hasRateLimitSettings()) {
                mergeRateLimitSettings(apiConfigSource.getRateLimitSettings());
            }
            if (apiConfigSource.getSetNodeOnFirstMessageOnly()) {
                setSetNodeOnFirstMessageOnly(apiConfigSource.getSetNodeOnFirstMessageOnly());
            }
            m14384mergeUnknownFields(apiConfigSource.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSource.Builder m14380mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSource.access$1400()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSource r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSource) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSource r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSource) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSource.Builder.m14380mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSource$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public ApiType getApiType() {
            ApiType apiTypeValueOf = ApiType.valueOf(this.apiType_);
            return apiTypeValueOf == null ? ApiType.UNRECOGNIZED : apiTypeValueOf;
        }

        public Builder setApiType(ApiType apiType) {
            apiType.getClass();
            this.apiType_ = apiType.getNumber();
            onChanged();
            return this;
        }

        public Builder clearApiType() {
            this.apiType_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public ApiVersion getTransportApiVersion() {
            ApiVersion apiVersionValueOf = ApiVersion.valueOf(this.transportApiVersion_);
            return apiVersionValueOf == null ? ApiVersion.UNRECOGNIZED : apiVersionValueOf;
        }

        public Builder setTransportApiVersion(ApiVersion apiVersion) {
            apiVersion.getClass();
            this.transportApiVersion_ = apiVersion.getNumber();
            onChanged();
            return this;
        }

        public Builder clearTransportApiVersion() {
            this.transportApiVersion_ = 0;
            onChanged();
            return this;
        }

        private void ensureClusterNamesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.clusterNames_ = new LazyStringArrayList(this.clusterNames_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        /* renamed from: getClusterNamesList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo14344getClusterNamesList() {
            return this.clusterNames_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public int getClusterNamesCount() {
            return this.clusterNames_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public String getClusterNames(int i) {
            return (String) this.clusterNames_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public ByteString getClusterNamesBytes(int i) {
            return this.clusterNames_.getByteString(i);
        }

        public Builder setClusterNames(int i, String str) {
            str.getClass();
            ensureClusterNamesIsMutable();
            this.clusterNames_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addClusterNames(String str) {
            str.getClass();
            ensureClusterNamesIsMutable();
            this.clusterNames_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllClusterNames(Iterable<String> iterable) {
            ensureClusterNamesIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.clusterNames_);
            onChanged();
            return this;
        }

        public Builder clearClusterNames() {
            this.clusterNames_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addClusterNamesBytes(ByteString byteString) {
            byteString.getClass();
            ApiConfigSource.checkByteStringIsUtf8(byteString);
            ensureClusterNamesIsMutable();
            this.clusterNames_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureGrpcServicesIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.grpcServices_ = new ArrayList(this.grpcServices_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public List<GrpcService> getGrpcServicesList() {
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.grpcServices_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public int getGrpcServicesCount() {
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.grpcServices_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public GrpcService getGrpcServices(int i) {
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.grpcServices_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setGrpcServices(int i, GrpcService grpcService) {
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                grpcService.getClass();
                ensureGrpcServicesIsMutable();
                this.grpcServices_.set(i, grpcService);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, grpcService);
            }
            return this;
        }

        public Builder setGrpcServices(int i, GrpcService.Builder builder) {
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureGrpcServicesIsMutable();
                this.grpcServices_.set(i, builder.m14909build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m14909build());
            }
            return this;
        }

        public Builder addGrpcServices(GrpcService grpcService) {
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                grpcService.getClass();
                ensureGrpcServicesIsMutable();
                this.grpcServices_.add(grpcService);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(grpcService);
            }
            return this;
        }

        public Builder addGrpcServices(int i, GrpcService grpcService) {
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                grpcService.getClass();
                ensureGrpcServicesIsMutable();
                this.grpcServices_.add(i, grpcService);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, grpcService);
            }
            return this;
        }

        public Builder addGrpcServices(GrpcService.Builder builder) {
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureGrpcServicesIsMutable();
                this.grpcServices_.add(builder.m14909build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m14909build());
            }
            return this;
        }

        public Builder addGrpcServices(int i, GrpcService.Builder builder) {
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureGrpcServicesIsMutable();
                this.grpcServices_.add(i, builder.m14909build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m14909build());
            }
            return this;
        }

        public Builder addAllGrpcServices(Iterable<? extends GrpcService> iterable) {
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureGrpcServicesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.grpcServices_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearGrpcServices() {
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.grpcServices_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeGrpcServices(int i) {
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureGrpcServicesIsMutable();
                this.grpcServices_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public GrpcService.Builder getGrpcServicesBuilder(int i) {
            return getGrpcServicesFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public GrpcServiceOrBuilder getGrpcServicesOrBuilder(int i) {
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.grpcServices_.get(i);
            }
            return (GrpcServiceOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public List<? extends GrpcServiceOrBuilder> getGrpcServicesOrBuilderList() {
            RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> repeatedFieldBuilderV3 = this.grpcServicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.grpcServices_);
        }

        public GrpcService.Builder addGrpcServicesBuilder() {
            return getGrpcServicesFieldBuilder().addBuilder(GrpcService.getDefaultInstance());
        }

        public GrpcService.Builder addGrpcServicesBuilder(int i) {
            return getGrpcServicesFieldBuilder().addBuilder(i, GrpcService.getDefaultInstance());
        }

        public List<GrpcService.Builder> getGrpcServicesBuilderList() {
            return getGrpcServicesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<GrpcService, GrpcService.Builder, GrpcServiceOrBuilder> getGrpcServicesFieldBuilder() {
            if (this.grpcServicesBuilder_ == null) {
                this.grpcServicesBuilder_ = new RepeatedFieldBuilderV3<>(this.grpcServices_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.grpcServices_ = null;
            }
            return this.grpcServicesBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public Duration getRefreshDelay() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.refreshDelayBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.refreshDelay_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setRefreshDelay(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.refreshDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.refreshDelay_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setRefreshDelay(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.refreshDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.refreshDelay_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRefreshDelay(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.refreshDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.refreshDelay_;
                if (duration2 != null) {
                    this.refreshDelay_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.refreshDelay_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearRefreshDelay() {
            if (this.refreshDelayBuilder_ == null) {
                this.refreshDelay_ = null;
                onChanged();
            } else {
                this.refreshDelay_ = null;
                this.refreshDelayBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getRefreshDelayBuilder() {
            onChanged();
            return getRefreshDelayFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public DurationOrBuilder getRefreshDelayOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.refreshDelayBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.refreshDelay_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getRefreshDelayFieldBuilder() {
            if (this.refreshDelayBuilder_ == null) {
                this.refreshDelayBuilder_ = new SingleFieldBuilderV3<>(getRefreshDelay(), getParentForChildren(), isClean());
                this.refreshDelay_ = null;
            }
            return this.refreshDelayBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public Duration getRequestTimeout() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.requestTimeoutBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.requestTimeout_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setRequestTimeout(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.requestTimeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.requestTimeout_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setRequestTimeout(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.requestTimeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.requestTimeout_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRequestTimeout(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.requestTimeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.requestTimeout_;
                if (duration2 != null) {
                    this.requestTimeout_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.requestTimeout_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearRequestTimeout() {
            if (this.requestTimeoutBuilder_ == null) {
                this.requestTimeout_ = null;
                onChanged();
            } else {
                this.requestTimeout_ = null;
                this.requestTimeoutBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getRequestTimeoutBuilder() {
            onChanged();
            return getRequestTimeoutFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public DurationOrBuilder getRequestTimeoutOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.requestTimeoutBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.requestTimeout_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getRequestTimeoutFieldBuilder() {
            if (this.requestTimeoutBuilder_ == null) {
                this.requestTimeoutBuilder_ = new SingleFieldBuilderV3<>(getRequestTimeout(), getParentForChildren(), isClean());
                this.requestTimeout_ = null;
            }
            return this.requestTimeoutBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public RateLimitSettings getRateLimitSettings() {
            SingleFieldBuilderV3<RateLimitSettings, RateLimitSettings.Builder, RateLimitSettingsOrBuilder> singleFieldBuilderV3 = this.rateLimitSettingsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            RateLimitSettings rateLimitSettings = this.rateLimitSettings_;
            return rateLimitSettings == null ? RateLimitSettings.getDefaultInstance() : rateLimitSettings;
        }

        public Builder setRateLimitSettings(RateLimitSettings rateLimitSettings) {
            SingleFieldBuilderV3<RateLimitSettings, RateLimitSettings.Builder, RateLimitSettingsOrBuilder> singleFieldBuilderV3 = this.rateLimitSettingsBuilder_;
            if (singleFieldBuilderV3 == null) {
                rateLimitSettings.getClass();
                this.rateLimitSettings_ = rateLimitSettings;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(rateLimitSettings);
            }
            return this;
        }

        public Builder setRateLimitSettings(RateLimitSettings.Builder builder) {
            SingleFieldBuilderV3<RateLimitSettings, RateLimitSettings.Builder, RateLimitSettingsOrBuilder> singleFieldBuilderV3 = this.rateLimitSettingsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.rateLimitSettings_ = builder.m16432build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m16432build());
            }
            return this;
        }

        public Builder mergeRateLimitSettings(RateLimitSettings rateLimitSettings) {
            SingleFieldBuilderV3<RateLimitSettings, RateLimitSettings.Builder, RateLimitSettingsOrBuilder> singleFieldBuilderV3 = this.rateLimitSettingsBuilder_;
            if (singleFieldBuilderV3 == null) {
                RateLimitSettings rateLimitSettings2 = this.rateLimitSettings_;
                if (rateLimitSettings2 != null) {
                    this.rateLimitSettings_ = RateLimitSettings.newBuilder(rateLimitSettings2).mergeFrom(rateLimitSettings).m16434buildPartial();
                } else {
                    this.rateLimitSettings_ = rateLimitSettings;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(rateLimitSettings);
            }
            return this;
        }

        public Builder clearRateLimitSettings() {
            if (this.rateLimitSettingsBuilder_ == null) {
                this.rateLimitSettings_ = null;
                onChanged();
            } else {
                this.rateLimitSettings_ = null;
                this.rateLimitSettingsBuilder_ = null;
            }
            return this;
        }

        public RateLimitSettings.Builder getRateLimitSettingsBuilder() {
            onChanged();
            return getRateLimitSettingsFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ApiConfigSourceOrBuilder
        public RateLimitSettingsOrBuilder getRateLimitSettingsOrBuilder() {
            SingleFieldBuilderV3<RateLimitSettings, RateLimitSettings.Builder, RateLimitSettingsOrBuilder> singleFieldBuilderV3 = this.rateLimitSettingsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (RateLimitSettingsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            RateLimitSettings rateLimitSettings = this.rateLimitSettings_;
            return rateLimitSettings == null ? RateLimitSettings.getDefaultInstance() : rateLimitSettings;
        }

        private SingleFieldBuilderV3<RateLimitSettings, RateLimitSettings.Builder, RateLimitSettingsOrBuilder> getRateLimitSettingsFieldBuilder() {
            if (this.rateLimitSettingsBuilder_ == null) {
                this.rateLimitSettingsBuilder_ = new SingleFieldBuilderV3<>(getRateLimitSettings(), getParentForChildren(), isClean());
                this.rateLimitSettings_ = null;
            }
            return this.rateLimitSettingsBuilder_;
        }

        public Builder clearSetNodeOnFirstMessageOnly() {
            this.setNodeOnFirstMessageOnly_ = false;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14390setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14384mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
