package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderValueOption;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderValueOptionOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicy;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HedgePolicy;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RateLimit;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RetryPolicy;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.Route;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualCluster;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class VirtualHost extends GeneratedMessageV3 implements VirtualHostOrBuilder {
    public static final int CORS_FIELD_NUMBER = 8;
    public static final int DOMAINS_FIELD_NUMBER = 2;
    public static final int HEDGE_POLICY_FIELD_NUMBER = 17;
    public static final int INCLUDE_ATTEMPT_COUNT_IN_RESPONSE_FIELD_NUMBER = 19;
    public static final int INCLUDE_REQUEST_ATTEMPT_COUNT_FIELD_NUMBER = 14;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int PER_FILTER_CONFIG_FIELD_NUMBER = 12;
    public static final int PER_REQUEST_BUFFER_LIMIT_BYTES_FIELD_NUMBER = 18;
    public static final int RATE_LIMITS_FIELD_NUMBER = 6;
    public static final int REQUEST_HEADERS_TO_ADD_FIELD_NUMBER = 7;
    public static final int REQUEST_HEADERS_TO_REMOVE_FIELD_NUMBER = 13;
    public static final int REQUIRE_TLS_FIELD_NUMBER = 4;
    public static final int RESPONSE_HEADERS_TO_ADD_FIELD_NUMBER = 10;
    public static final int RESPONSE_HEADERS_TO_REMOVE_FIELD_NUMBER = 11;
    public static final int RETRY_POLICY_FIELD_NUMBER = 16;
    public static final int RETRY_POLICY_TYPED_CONFIG_FIELD_NUMBER = 20;
    public static final int ROUTES_FIELD_NUMBER = 3;
    public static final int TYPED_PER_FILTER_CONFIG_FIELD_NUMBER = 15;
    public static final int VIRTUAL_CLUSTERS_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private static final VirtualHost DEFAULT_INSTANCE = new VirtualHost();
    private static final Parser<VirtualHost> PARSER = new AbstractParser<VirtualHost>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHost.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public VirtualHost m19483parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new VirtualHost(codedInputStream, extensionRegistryLite);
        }
    };
    private CorsPolicy cors_;
    private LazyStringList domains_;
    private HedgePolicy hedgePolicy_;
    private boolean includeAttemptCountInResponse_;
    private boolean includeRequestAttemptCount_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private MapField<String, Struct> perFilterConfig_;
    private UInt32Value perRequestBufferLimitBytes_;
    private List<RateLimit> rateLimits_;
    private List<HeaderValueOption> requestHeadersToAdd_;
    private LazyStringList requestHeadersToRemove_;
    private int requireTls_;
    private List<HeaderValueOption> responseHeadersToAdd_;
    private LazyStringList responseHeadersToRemove_;
    private Any retryPolicyTypedConfig_;
    private RetryPolicy retryPolicy_;
    private List<Route> routes_;
    private MapField<String, Any> typedPerFilterConfig_;
    private List<VirtualCluster> virtualClusters_;

    private VirtualHost(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private VirtualHost() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.domains_ = LazyStringArrayList.EMPTY;
        this.routes_ = Collections.emptyList();
        this.requireTls_ = 0;
        this.virtualClusters_ = Collections.emptyList();
        this.rateLimits_ = Collections.emptyList();
        this.requestHeadersToAdd_ = Collections.emptyList();
        this.requestHeadersToRemove_ = LazyStringArrayList.EMPTY;
        this.responseHeadersToAdd_ = Collections.emptyList();
        this.responseHeadersToRemove_ = LazyStringArrayList.EMPTY;
    }

    private VirtualHost(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Any.Builder builderM17863toBuilder;
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
                        switch (tag) {
                            case 0:
                                z = true;
                            case 10:
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            case 18:
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if ((i & 1) == 0) {
                                    this.domains_ = new LazyStringArrayList();
                                    i |= 1;
                                }
                                this.domains_.add(stringRequireUtf8);
                            case 26:
                                if ((i & 2) == 0) {
                                    this.routes_ = new ArrayList();
                                    i |= 2;
                                }
                                this.routes_.add(codedInputStream.readMessage(Route.parser(), extensionRegistryLite));
                            case 32:
                                this.requireTls_ = codedInputStream.readEnum();
                            case 42:
                                if ((i & 4) == 0) {
                                    this.virtualClusters_ = new ArrayList();
                                    i |= 4;
                                }
                                this.virtualClusters_.add(codedInputStream.readMessage(VirtualCluster.parser(), extensionRegistryLite));
                            case 50:
                                if ((i & 8) == 0) {
                                    this.rateLimits_ = new ArrayList();
                                    i |= 8;
                                }
                                this.rateLimits_.add(codedInputStream.readMessage(RateLimit.parser(), extensionRegistryLite));
                            case 58:
                                if ((i & 16) == 0) {
                                    this.requestHeadersToAdd_ = new ArrayList();
                                    i |= 16;
                                }
                                this.requestHeadersToAdd_.add(codedInputStream.readMessage(HeaderValueOption.parser(), extensionRegistryLite));
                            case 66:
                                CorsPolicy corsPolicy = this.cors_;
                                builderM17863toBuilder = corsPolicy != null ? corsPolicy.m17863toBuilder() : null;
                                CorsPolicy message = codedInputStream.readMessage(CorsPolicy.parser(), extensionRegistryLite);
                                this.cors_ = message;
                                if (builderM17863toBuilder != null) {
                                    builderM17863toBuilder.mergeFrom(message);
                                    this.cors_ = builderM17863toBuilder.m17870buildPartial();
                                }
                            case 82:
                                if ((i & 64) == 0) {
                                    this.responseHeadersToAdd_ = new ArrayList();
                                    i |= 64;
                                }
                                this.responseHeadersToAdd_.add(codedInputStream.readMessage(HeaderValueOption.parser(), extensionRegistryLite));
                            case RESET_TO_DEFAULT_CONFIGURATION_COMMAND_VALUE:
                                String stringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                                if ((i & 128) == 0) {
                                    this.responseHeadersToRemove_ = new LazyStringArrayList();
                                    i |= 128;
                                }
                                this.responseHeadersToRemove_.add(stringRequireUtf82);
                            case 98:
                                if ((i & 256) == 0) {
                                    this.perFilterConfig_ = MapField.newMapField(PerFilterConfigDefaultEntryHolder.defaultEntry);
                                    i |= 256;
                                }
                                MapEntry message2 = codedInputStream.readMessage(PerFilterConfigDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.perFilterConfig_.getMutableMap().put(message2.getKey(), message2.getValue());
                            case 106:
                                String stringRequireUtf83 = codedInputStream.readStringRequireUtf8();
                                if ((i & 32) == 0) {
                                    this.requestHeadersToRemove_ = new LazyStringArrayList();
                                    i |= 32;
                                }
                                this.requestHeadersToRemove_.add(stringRequireUtf83);
                            case 112:
                                this.includeRequestAttemptCount_ = codedInputStream.readBool();
                            case 122:
                                if ((i & 512) == 0) {
                                    this.typedPerFilterConfig_ = MapField.newMapField(TypedPerFilterConfigDefaultEntryHolder.defaultEntry);
                                    i |= 512;
                                }
                                MapEntry message3 = codedInputStream.readMessage(TypedPerFilterConfigDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.typedPerFilterConfig_.getMutableMap().put(message3.getKey(), message3.getValue());
                            case 130:
                                RetryPolicy retryPolicy = this.retryPolicy_;
                                builderM17863toBuilder = retryPolicy != null ? retryPolicy.m18600toBuilder() : null;
                                RetryPolicy message4 = codedInputStream.readMessage(RetryPolicy.parser(), extensionRegistryLite);
                                this.retryPolicy_ = message4;
                                if (builderM17863toBuilder != null) {
                                    builderM17863toBuilder.mergeFrom(message4);
                                    this.retryPolicy_ = builderM17863toBuilder.m18607buildPartial();
                                }
                            case 138:
                                HedgePolicy hedgePolicy = this.hedgePolicy_;
                                builderM17863toBuilder = hedgePolicy != null ? hedgePolicy.m18093toBuilder() : null;
                                HedgePolicy message5 = codedInputStream.readMessage(HedgePolicy.parser(), extensionRegistryLite);
                                this.hedgePolicy_ = message5;
                                if (builderM17863toBuilder != null) {
                                    builderM17863toBuilder.mergeFrom(message5);
                                    this.hedgePolicy_ = builderM17863toBuilder.m18100buildPartial();
                                }
                            case 146:
                                UInt32Value uInt32Value = this.perRequestBufferLimitBytes_;
                                builderM17863toBuilder = uInt32Value != null ? uInt32Value.toBuilder() : null;
                                UInt32Value message6 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                                this.perRequestBufferLimitBytes_ = message6;
                                if (builderM17863toBuilder != null) {
                                    builderM17863toBuilder.mergeFrom(message6);
                                    this.perRequestBufferLimitBytes_ = builderM17863toBuilder.buildPartial();
                                }
                            case 152:
                                this.includeAttemptCountInResponse_ = codedInputStream.readBool();
                            case 162:
                                Any any = this.retryPolicyTypedConfig_;
                                builderM17863toBuilder = any != null ? any.toBuilder() : null;
                                Any message7 = codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                                this.retryPolicyTypedConfig_ = message7;
                                if (builderM17863toBuilder != null) {
                                    builderM17863toBuilder.mergeFrom(message7);
                                    this.retryPolicyTypedConfig_ = builderM17863toBuilder.buildPartial();
                                }
                            default:
                                if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    z = true;
                                }
                        }
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    }
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if ((i & 1) != 0) {
                    this.domains_ = this.domains_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.routes_ = Collections.unmodifiableList(this.routes_);
                }
                if ((i & 4) != 0) {
                    this.virtualClusters_ = Collections.unmodifiableList(this.virtualClusters_);
                }
                if ((i & 8) != 0) {
                    this.rateLimits_ = Collections.unmodifiableList(this.rateLimits_);
                }
                if ((i & 16) != 0) {
                    this.requestHeadersToAdd_ = Collections.unmodifiableList(this.requestHeadersToAdd_);
                }
                if ((i & 64) != 0) {
                    this.responseHeadersToAdd_ = Collections.unmodifiableList(this.responseHeadersToAdd_);
                }
                if ((i & 128) != 0) {
                    this.responseHeadersToRemove_ = this.responseHeadersToRemove_.getUnmodifiableView();
                }
                if ((i & 32) != 0) {
                    this.requestHeadersToRemove_ = this.requestHeadersToRemove_.getUnmodifiableView();
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static VirtualHost getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<VirtualHost> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RouteComponentsProto.internal_static_envoy_api_v2_route_VirtualHost_descriptor;
    }

    public static VirtualHost parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (VirtualHost) PARSER.parseFrom(byteBuffer);
    }

    public static VirtualHost parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (VirtualHost) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static VirtualHost parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (VirtualHost) PARSER.parseFrom(byteString);
    }

    public static VirtualHost parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (VirtualHost) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static VirtualHost parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (VirtualHost) PARSER.parseFrom(bArr);
    }

    public static VirtualHost parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (VirtualHost) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static VirtualHost parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static VirtualHost parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static VirtualHost parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static VirtualHost parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static VirtualHost parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static VirtualHost parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m19481toBuilder();
    }

    public static Builder newBuilder(VirtualHost virtualHost) {
        return DEFAULT_INSTANCE.m19481toBuilder().mergeFrom(virtualHost);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public VirtualHost m19473getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    /* renamed from: getDomainsList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo19475getDomainsList() {
        return this.domains_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public boolean getIncludeAttemptCountInResponse() {
        return this.includeAttemptCountInResponse_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public boolean getIncludeRequestAttemptCount() {
        return this.includeRequestAttemptCount_;
    }

    public Parser<VirtualHost> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public List<RateLimit> getRateLimitsList() {
        return this.rateLimits_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public List<? extends RateLimitOrBuilder> getRateLimitsOrBuilderList() {
        return this.rateLimits_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public List<HeaderValueOption> getRequestHeadersToAddList() {
        return this.requestHeadersToAdd_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public List<? extends HeaderValueOptionOrBuilder> getRequestHeadersToAddOrBuilderList() {
        return this.requestHeadersToAdd_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    /* renamed from: getRequestHeadersToRemoveList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo19476getRequestHeadersToRemoveList() {
        return this.requestHeadersToRemove_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public int getRequireTlsValue() {
        return this.requireTls_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public List<HeaderValueOption> getResponseHeadersToAddList() {
        return this.responseHeadersToAdd_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public List<? extends HeaderValueOptionOrBuilder> getResponseHeadersToAddOrBuilderList() {
        return this.responseHeadersToAdd_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    /* renamed from: getResponseHeadersToRemoveList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo19477getResponseHeadersToRemoveList() {
        return this.responseHeadersToRemove_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public List<Route> getRoutesList() {
        return this.routes_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public List<? extends RouteOrBuilder> getRoutesOrBuilderList() {
        return this.routes_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public List<VirtualCluster> getVirtualClustersList() {
        return this.virtualClusters_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public List<? extends VirtualClusterOrBuilder> getVirtualClustersOrBuilderList() {
        return this.virtualClusters_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public boolean hasCors() {
        return this.cors_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public boolean hasHedgePolicy() {
        return this.hedgePolicy_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public boolean hasPerRequestBufferLimitBytes() {
        return this.perRequestBufferLimitBytes_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public boolean hasRetryPolicy() {
        return this.retryPolicy_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public boolean hasRetryPolicyTypedConfig() {
        return this.retryPolicyTypedConfig_ != null;
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
        return new VirtualHost();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected MapField internalGetMapField(int i) {
        if (i == 12) {
            return internalGetPerFilterConfig();
        }
        if (i == 15) {
            return internalGetTypedPerFilterConfig();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RouteComponentsProto.internal_static_envoy_api_v2_route_VirtualHost_fieldAccessorTable.ensureFieldAccessorsInitialized(VirtualHost.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public int getDomainsCount() {
        return this.domains_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public String getDomains(int i) {
        return (String) this.domains_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public ByteString getDomainsBytes(int i) {
        return this.domains_.getByteString(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public int getRoutesCount() {
        return this.routes_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public Route getRoutes(int i) {
        return this.routes_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public RouteOrBuilder getRoutesOrBuilder(int i) {
        return this.routes_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public TlsRequirementType getRequireTls() {
        TlsRequirementType tlsRequirementTypeValueOf = TlsRequirementType.valueOf(this.requireTls_);
        return tlsRequirementTypeValueOf == null ? TlsRequirementType.UNRECOGNIZED : tlsRequirementTypeValueOf;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public int getVirtualClustersCount() {
        return this.virtualClusters_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public VirtualCluster getVirtualClusters(int i) {
        return this.virtualClusters_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public VirtualClusterOrBuilder getVirtualClustersOrBuilder(int i) {
        return this.virtualClusters_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public int getRateLimitsCount() {
        return this.rateLimits_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public RateLimit getRateLimits(int i) {
        return this.rateLimits_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public RateLimitOrBuilder getRateLimitsOrBuilder(int i) {
        return this.rateLimits_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public int getRequestHeadersToAddCount() {
        return this.requestHeadersToAdd_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public HeaderValueOption getRequestHeadersToAdd(int i) {
        return this.requestHeadersToAdd_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public HeaderValueOptionOrBuilder getRequestHeadersToAddOrBuilder(int i) {
        return this.requestHeadersToAdd_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public int getRequestHeadersToRemoveCount() {
        return this.requestHeadersToRemove_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public String getRequestHeadersToRemove(int i) {
        return (String) this.requestHeadersToRemove_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public ByteString getRequestHeadersToRemoveBytes(int i) {
        return this.requestHeadersToRemove_.getByteString(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public int getResponseHeadersToAddCount() {
        return this.responseHeadersToAdd_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public HeaderValueOption getResponseHeadersToAdd(int i) {
        return this.responseHeadersToAdd_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public HeaderValueOptionOrBuilder getResponseHeadersToAddOrBuilder(int i) {
        return this.responseHeadersToAdd_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public int getResponseHeadersToRemoveCount() {
        return this.responseHeadersToRemove_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public String getResponseHeadersToRemove(int i) {
        return (String) this.responseHeadersToRemove_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public ByteString getResponseHeadersToRemoveBytes(int i) {
        return this.responseHeadersToRemove_.getByteString(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public CorsPolicy getCors() {
        CorsPolicy corsPolicy = this.cors_;
        return corsPolicy == null ? CorsPolicy.getDefaultInstance() : corsPolicy;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public CorsPolicyOrBuilder getCorsOrBuilder() {
        return getCors();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, Struct> internalGetPerFilterConfig() {
        MapField<String, Struct> mapField = this.perFilterConfig_;
        return mapField == null ? MapField.emptyMapField(PerFilterConfigDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    @Deprecated
    public int getPerFilterConfigCount() {
        return internalGetPerFilterConfig().getMap().size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    @Deprecated
    public boolean containsPerFilterConfig(String str) {
        str.getClass();
        return internalGetPerFilterConfig().getMap().containsKey(str);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    @Deprecated
    public Map<String, Struct> getPerFilterConfig() {
        return getPerFilterConfigMap();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    @Deprecated
    public Map<String, Struct> getPerFilterConfigMap() {
        return internalGetPerFilterConfig().getMap();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    @Deprecated
    public Struct getPerFilterConfigOrDefault(String str, Struct struct) {
        str.getClass();
        Map map = internalGetPerFilterConfig().getMap();
        return map.containsKey(str) ? (Struct) map.get(str) : struct;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    @Deprecated
    public Struct getPerFilterConfigOrThrow(String str) {
        str.getClass();
        Map map = internalGetPerFilterConfig().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return (Struct) map.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, Any> internalGetTypedPerFilterConfig() {
        MapField<String, Any> mapField = this.typedPerFilterConfig_;
        return mapField == null ? MapField.emptyMapField(TypedPerFilterConfigDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public int getTypedPerFilterConfigCount() {
        return internalGetTypedPerFilterConfig().getMap().size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public boolean containsTypedPerFilterConfig(String str) {
        str.getClass();
        return internalGetTypedPerFilterConfig().getMap().containsKey(str);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    @Deprecated
    public Map<String, Any> getTypedPerFilterConfig() {
        return getTypedPerFilterConfigMap();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public Map<String, Any> getTypedPerFilterConfigMap() {
        return internalGetTypedPerFilterConfig().getMap();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public Any getTypedPerFilterConfigOrDefault(String str, Any any) {
        str.getClass();
        Map map = internalGetTypedPerFilterConfig().getMap();
        return map.containsKey(str) ? (Any) map.get(str) : any;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public Any getTypedPerFilterConfigOrThrow(String str) {
        str.getClass();
        Map map = internalGetTypedPerFilterConfig().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return (Any) map.get(str);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public RetryPolicy getRetryPolicy() {
        RetryPolicy retryPolicy = this.retryPolicy_;
        return retryPolicy == null ? RetryPolicy.getDefaultInstance() : retryPolicy;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public RetryPolicyOrBuilder getRetryPolicyOrBuilder() {
        return getRetryPolicy();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public Any getRetryPolicyTypedConfig() {
        Any any = this.retryPolicyTypedConfig_;
        return any == null ? Any.getDefaultInstance() : any;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public AnyOrBuilder getRetryPolicyTypedConfigOrBuilder() {
        return getRetryPolicyTypedConfig();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public HedgePolicy getHedgePolicy() {
        HedgePolicy hedgePolicy = this.hedgePolicy_;
        return hedgePolicy == null ? HedgePolicy.getDefaultInstance() : hedgePolicy;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public HedgePolicyOrBuilder getHedgePolicyOrBuilder() {
        return getHedgePolicy();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public UInt32Value getPerRequestBufferLimitBytes() {
        UInt32Value uInt32Value = this.perRequestBufferLimitBytes_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
    public UInt32ValueOrBuilder getPerRequestBufferLimitBytesOrBuilder() {
        return getPerRequestBufferLimitBytes();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        for (int i = 0; i < this.domains_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.domains_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.routes_.size(); i2++) {
            codedOutputStream.writeMessage(3, this.routes_.get(i2));
        }
        if (this.requireTls_ != TlsRequirementType.NONE.getNumber()) {
            codedOutputStream.writeEnum(4, this.requireTls_);
        }
        for (int i3 = 0; i3 < this.virtualClusters_.size(); i3++) {
            codedOutputStream.writeMessage(5, this.virtualClusters_.get(i3));
        }
        for (int i4 = 0; i4 < this.rateLimits_.size(); i4++) {
            codedOutputStream.writeMessage(6, this.rateLimits_.get(i4));
        }
        for (int i5 = 0; i5 < this.requestHeadersToAdd_.size(); i5++) {
            codedOutputStream.writeMessage(7, this.requestHeadersToAdd_.get(i5));
        }
        if (this.cors_ != null) {
            codedOutputStream.writeMessage(8, getCors());
        }
        for (int i6 = 0; i6 < this.responseHeadersToAdd_.size(); i6++) {
            codedOutputStream.writeMessage(10, this.responseHeadersToAdd_.get(i6));
        }
        for (int i7 = 0; i7 < this.responseHeadersToRemove_.size(); i7++) {
            GeneratedMessageV3.writeString(codedOutputStream, 11, this.responseHeadersToRemove_.getRaw(i7));
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetPerFilterConfig(), PerFilterConfigDefaultEntryHolder.defaultEntry, 12);
        for (int i8 = 0; i8 < this.requestHeadersToRemove_.size(); i8++) {
            GeneratedMessageV3.writeString(codedOutputStream, 13, this.requestHeadersToRemove_.getRaw(i8));
        }
        boolean z = this.includeRequestAttemptCount_;
        if (z) {
            codedOutputStream.writeBool(14, z);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetTypedPerFilterConfig(), TypedPerFilterConfigDefaultEntryHolder.defaultEntry, 15);
        if (this.retryPolicy_ != null) {
            codedOutputStream.writeMessage(16, getRetryPolicy());
        }
        if (this.hedgePolicy_ != null) {
            codedOutputStream.writeMessage(17, getHedgePolicy());
        }
        if (this.perRequestBufferLimitBytes_ != null) {
            codedOutputStream.writeMessage(18, getPerRequestBufferLimitBytes());
        }
        boolean z2 = this.includeAttemptCountInResponse_;
        if (z2) {
            codedOutputStream.writeBool(19, z2);
        }
        if (this.retryPolicyTypedConfig_ != null) {
            codedOutputStream.writeMessage(20, getRetryPolicyTypedConfig());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.domains_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.domains_.getRaw(i2));
        }
        int size = iComputeStringSize + iComputeStringSizeNoTag + mo19475getDomainsList().size();
        for (int i3 = 0; i3 < this.routes_.size(); i3++) {
            size += CodedOutputStream.computeMessageSize(3, this.routes_.get(i3));
        }
        if (this.requireTls_ != TlsRequirementType.NONE.getNumber()) {
            size += CodedOutputStream.computeEnumSize(4, this.requireTls_);
        }
        for (int i4 = 0; i4 < this.virtualClusters_.size(); i4++) {
            size += CodedOutputStream.computeMessageSize(5, this.virtualClusters_.get(i4));
        }
        for (int i5 = 0; i5 < this.rateLimits_.size(); i5++) {
            size += CodedOutputStream.computeMessageSize(6, this.rateLimits_.get(i5));
        }
        for (int i6 = 0; i6 < this.requestHeadersToAdd_.size(); i6++) {
            size += CodedOutputStream.computeMessageSize(7, this.requestHeadersToAdd_.get(i6));
        }
        if (this.cors_ != null) {
            size += CodedOutputStream.computeMessageSize(8, getCors());
        }
        for (int i7 = 0; i7 < this.responseHeadersToAdd_.size(); i7++) {
            size += CodedOutputStream.computeMessageSize(10, this.responseHeadersToAdd_.get(i7));
        }
        int iComputeStringSizeNoTag2 = 0;
        for (int i8 = 0; i8 < this.responseHeadersToRemove_.size(); i8++) {
            iComputeStringSizeNoTag2 += computeStringSizeNoTag(this.responseHeadersToRemove_.getRaw(i8));
        }
        int size2 = size + iComputeStringSizeNoTag2 + mo19477getResponseHeadersToRemoveList().size();
        for (Map.Entry entry : internalGetPerFilterConfig().getMap().entrySet()) {
            size2 += CodedOutputStream.computeMessageSize(12, PerFilterConfigDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        int iComputeStringSizeNoTag3 = 0;
        for (int i9 = 0; i9 < this.requestHeadersToRemove_.size(); i9++) {
            iComputeStringSizeNoTag3 += computeStringSizeNoTag(this.requestHeadersToRemove_.getRaw(i9));
        }
        int size3 = size2 + iComputeStringSizeNoTag3 + mo19476getRequestHeadersToRemoveList().size();
        boolean z = this.includeRequestAttemptCount_;
        if (z) {
            size3 += CodedOutputStream.computeBoolSize(14, z);
        }
        for (Map.Entry entry2 : internalGetTypedPerFilterConfig().getMap().entrySet()) {
            size3 += CodedOutputStream.computeMessageSize(15, TypedPerFilterConfigDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry2.getKey()).setValue(entry2.getValue()).build());
        }
        if (this.retryPolicy_ != null) {
            size3 += CodedOutputStream.computeMessageSize(16, getRetryPolicy());
        }
        if (this.hedgePolicy_ != null) {
            size3 += CodedOutputStream.computeMessageSize(17, getHedgePolicy());
        }
        if (this.perRequestBufferLimitBytes_ != null) {
            size3 += CodedOutputStream.computeMessageSize(18, getPerRequestBufferLimitBytes());
        }
        boolean z2 = this.includeAttemptCountInResponse_;
        if (z2) {
            size3 += CodedOutputStream.computeBoolSize(19, z2);
        }
        if (this.retryPolicyTypedConfig_ != null) {
            size3 += CodedOutputStream.computeMessageSize(20, getRetryPolicyTypedConfig());
        }
        int serializedSize = size3 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VirtualHost)) {
            return super.equals(obj);
        }
        VirtualHost virtualHost = (VirtualHost) obj;
        if (!getName().equals(virtualHost.getName()) || !mo19475getDomainsList().equals(virtualHost.mo19475getDomainsList()) || !getRoutesList().equals(virtualHost.getRoutesList()) || this.requireTls_ != virtualHost.requireTls_ || !getVirtualClustersList().equals(virtualHost.getVirtualClustersList()) || !getRateLimitsList().equals(virtualHost.getRateLimitsList()) || !getRequestHeadersToAddList().equals(virtualHost.getRequestHeadersToAddList()) || !mo19476getRequestHeadersToRemoveList().equals(virtualHost.mo19476getRequestHeadersToRemoveList()) || !getResponseHeadersToAddList().equals(virtualHost.getResponseHeadersToAddList()) || !mo19477getResponseHeadersToRemoveList().equals(virtualHost.mo19477getResponseHeadersToRemoveList()) || hasCors() != virtualHost.hasCors()) {
            return false;
        }
        if ((hasCors() && !getCors().equals(virtualHost.getCors())) || !internalGetPerFilterConfig().equals(virtualHost.internalGetPerFilterConfig()) || !internalGetTypedPerFilterConfig().equals(virtualHost.internalGetTypedPerFilterConfig()) || getIncludeRequestAttemptCount() != virtualHost.getIncludeRequestAttemptCount() || getIncludeAttemptCountInResponse() != virtualHost.getIncludeAttemptCountInResponse() || hasRetryPolicy() != virtualHost.hasRetryPolicy()) {
            return false;
        }
        if ((hasRetryPolicy() && !getRetryPolicy().equals(virtualHost.getRetryPolicy())) || hasRetryPolicyTypedConfig() != virtualHost.hasRetryPolicyTypedConfig()) {
            return false;
        }
        if ((hasRetryPolicyTypedConfig() && !getRetryPolicyTypedConfig().equals(virtualHost.getRetryPolicyTypedConfig())) || hasHedgePolicy() != virtualHost.hasHedgePolicy()) {
            return false;
        }
        if ((!hasHedgePolicy() || getHedgePolicy().equals(virtualHost.getHedgePolicy())) && hasPerRequestBufferLimitBytes() == virtualHost.hasPerRequestBufferLimitBytes()) {
            return (!hasPerRequestBufferLimitBytes() || getPerRequestBufferLimitBytes().equals(virtualHost.getPerRequestBufferLimitBytes())) && this.unknownFields.equals(virtualHost.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (getDomainsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + mo19475getDomainsList().hashCode();
        }
        if (getRoutesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getRoutesList().hashCode();
        }
        int iHashCode2 = (((iHashCode * 37) + 4) * 53) + this.requireTls_;
        if (getVirtualClustersCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 5) * 53) + getVirtualClustersList().hashCode();
        }
        if (getRateLimitsCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 6) * 53) + getRateLimitsList().hashCode();
        }
        if (getRequestHeadersToAddCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 7) * 53) + getRequestHeadersToAddList().hashCode();
        }
        if (getRequestHeadersToRemoveCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 13) * 53) + mo19476getRequestHeadersToRemoveList().hashCode();
        }
        if (getResponseHeadersToAddCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 10) * 53) + getResponseHeadersToAddList().hashCode();
        }
        if (getResponseHeadersToRemoveCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 11) * 53) + mo19477getResponseHeadersToRemoveList().hashCode();
        }
        if (hasCors()) {
            iHashCode2 = (((iHashCode2 * 37) + 8) * 53) + getCors().hashCode();
        }
        if (!internalGetPerFilterConfig().getMap().isEmpty()) {
            iHashCode2 = (((iHashCode2 * 37) + 12) * 53) + internalGetPerFilterConfig().hashCode();
        }
        if (!internalGetTypedPerFilterConfig().getMap().isEmpty()) {
            iHashCode2 = (((iHashCode2 * 37) + 15) * 53) + internalGetTypedPerFilterConfig().hashCode();
        }
        int iHashBoolean = (((((((iHashCode2 * 37) + 14) * 53) + Internal.hashBoolean(getIncludeRequestAttemptCount())) * 37) + 19) * 53) + Internal.hashBoolean(getIncludeAttemptCountInResponse());
        if (hasRetryPolicy()) {
            iHashBoolean = (((iHashBoolean * 37) + 16) * 53) + getRetryPolicy().hashCode();
        }
        if (hasRetryPolicyTypedConfig()) {
            iHashBoolean = (((iHashBoolean * 37) + 20) * 53) + getRetryPolicyTypedConfig().hashCode();
        }
        if (hasHedgePolicy()) {
            iHashBoolean = (((iHashBoolean * 37) + 17) * 53) + getHedgePolicy().hashCode();
        }
        if (hasPerRequestBufferLimitBytes()) {
            iHashBoolean = (((iHashBoolean * 37) + 18) * 53) + getPerRequestBufferLimitBytes().hashCode();
        }
        int iHashCode3 = (iHashBoolean * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode3;
        return iHashCode3;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m19478newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m19481toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum TlsRequirementType implements ProtocolMessageEnum {
        NONE(0),
        EXTERNAL_ONLY(1),
        ALL(2),
        UNRECOGNIZED(-1);

        public static final int ALL_VALUE = 2;
        public static final int EXTERNAL_ONLY_VALUE = 1;
        public static final int NONE_VALUE = 0;
        private static final Internal.EnumLiteMap<TlsRequirementType> internalValueMap = new Internal.EnumLiteMap<TlsRequirementType>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHost.TlsRequirementType.1
            public TlsRequirementType findValueByNumber(int i) {
                return TlsRequirementType.forNumber(i);
            }
        };
        private static final TlsRequirementType[] VALUES = values();
        private final int value;

        TlsRequirementType(int i) {
            this.value = i;
        }

        public static TlsRequirementType forNumber(int i) {
            if (i == 0) {
                return NONE;
            }
            if (i == 1) {
                return EXTERNAL_ONLY;
            }
            if (i != 2) {
                return null;
            }
            return ALL;
        }

        public static Internal.EnumLiteMap<TlsRequirementType> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static TlsRequirementType valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) VirtualHost.getDescriptor().getEnumTypes().get(0);
        }

        public static TlsRequirementType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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

    private static final class PerFilterConfigDefaultEntryHolder {
        static final MapEntry<String, Struct> defaultEntry = MapEntry.newDefaultInstance(RouteComponentsProto.internal_static_envoy_api_v2_route_VirtualHost_PerFilterConfigEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Struct.getDefaultInstance());

        private PerFilterConfigDefaultEntryHolder() {
        }
    }

    private static final class TypedPerFilterConfigDefaultEntryHolder {
        static final MapEntry<String, Any> defaultEntry = MapEntry.newDefaultInstance(RouteComponentsProto.internal_static_envoy_api_v2_route_VirtualHost_TypedPerFilterConfigEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Any.getDefaultInstance());

        private TypedPerFilterConfigDefaultEntryHolder() {
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements VirtualHostOrBuilder {
        private int bitField0_;
        private SingleFieldBuilderV3<CorsPolicy, CorsPolicy.Builder, CorsPolicyOrBuilder> corsBuilder_;
        private CorsPolicy cors_;
        private LazyStringList domains_;
        private SingleFieldBuilderV3<HedgePolicy, HedgePolicy.Builder, HedgePolicyOrBuilder> hedgePolicyBuilder_;
        private HedgePolicy hedgePolicy_;
        private boolean includeAttemptCountInResponse_;
        private boolean includeRequestAttemptCount_;
        private Object name_;
        private MapField<String, Struct> perFilterConfig_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> perRequestBufferLimitBytesBuilder_;
        private UInt32Value perRequestBufferLimitBytes_;
        private RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> rateLimitsBuilder_;
        private List<RateLimit> rateLimits_;
        private RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> requestHeadersToAddBuilder_;
        private List<HeaderValueOption> requestHeadersToAdd_;
        private LazyStringList requestHeadersToRemove_;
        private int requireTls_;
        private RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> responseHeadersToAddBuilder_;
        private List<HeaderValueOption> responseHeadersToAdd_;
        private LazyStringList responseHeadersToRemove_;
        private SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> retryPolicyBuilder_;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> retryPolicyTypedConfigBuilder_;
        private Any retryPolicyTypedConfig_;
        private RetryPolicy retryPolicy_;
        private RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> routesBuilder_;
        private List<Route> routes_;
        private MapField<String, Any> typedPerFilterConfig_;
        private RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> virtualClustersBuilder_;
        private List<VirtualCluster> virtualClusters_;

        private Builder() {
            this.name_ = "";
            this.domains_ = LazyStringArrayList.EMPTY;
            this.routes_ = Collections.emptyList();
            this.requireTls_ = 0;
            this.virtualClusters_ = Collections.emptyList();
            this.rateLimits_ = Collections.emptyList();
            this.requestHeadersToAdd_ = Collections.emptyList();
            this.requestHeadersToRemove_ = LazyStringArrayList.EMPTY;
            this.responseHeadersToAdd_ = Collections.emptyList();
            this.responseHeadersToRemove_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.domains_ = LazyStringArrayList.EMPTY;
            this.routes_ = Collections.emptyList();
            this.requireTls_ = 0;
            this.virtualClusters_ = Collections.emptyList();
            this.rateLimits_ = Collections.emptyList();
            this.requestHeadersToAdd_ = Collections.emptyList();
            this.requestHeadersToRemove_ = LazyStringArrayList.EMPTY;
            this.responseHeadersToAdd_ = Collections.emptyList();
            this.responseHeadersToRemove_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_VirtualHost_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public boolean getIncludeAttemptCountInResponse() {
            return this.includeAttemptCountInResponse_;
        }

        public Builder setIncludeAttemptCountInResponse(boolean z) {
            this.includeAttemptCountInResponse_ = z;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public boolean getIncludeRequestAttemptCount() {
            return this.includeRequestAttemptCount_;
        }

        public Builder setIncludeRequestAttemptCount(boolean z) {
            this.includeRequestAttemptCount_ = z;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public int getRequireTlsValue() {
            return this.requireTls_;
        }

        public Builder setRequireTlsValue(int i) {
            this.requireTls_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public boolean hasCors() {
            return (this.corsBuilder_ == null && this.cors_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public boolean hasHedgePolicy() {
            return (this.hedgePolicyBuilder_ == null && this.hedgePolicy_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public boolean hasPerRequestBufferLimitBytes() {
            return (this.perRequestBufferLimitBytesBuilder_ == null && this.perRequestBufferLimitBytes_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public boolean hasRetryPolicy() {
            return (this.retryPolicyBuilder_ == null && this.retryPolicy_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public boolean hasRetryPolicyTypedConfig() {
            return (this.retryPolicyTypedConfigBuilder_ == null && this.retryPolicyTypedConfig_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 12) {
                return internalGetPerFilterConfig();
            }
            if (i == 15) {
                return internalGetTypedPerFilterConfig();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected MapField internalGetMutableMapField(int i) {
            if (i == 12) {
                return internalGetMutablePerFilterConfig();
            }
            if (i == 15) {
                return internalGetMutableTypedPerFilterConfig();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_VirtualHost_fieldAccessorTable.ensureFieldAccessorsInitialized(VirtualHost.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (VirtualHost.alwaysUseFieldBuilders) {
                getRoutesFieldBuilder();
                getVirtualClustersFieldBuilder();
                getRateLimitsFieldBuilder();
                getRequestHeadersToAddFieldBuilder();
                getResponseHeadersToAddFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19492clear() {
            super.clear();
            this.name_ = "";
            this.domains_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.routes_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.requireTls_ = 0;
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV32 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.virtualClusters_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV33 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV33 == null) {
                this.rateLimits_ = Collections.emptyList();
                this.bitField0_ &= -9;
            } else {
                repeatedFieldBuilderV33.clear();
            }
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV34 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV34 == null) {
                this.requestHeadersToAdd_ = Collections.emptyList();
                this.bitField0_ &= -17;
            } else {
                repeatedFieldBuilderV34.clear();
            }
            this.requestHeadersToRemove_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -33;
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV35 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV35 == null) {
                this.responseHeadersToAdd_ = Collections.emptyList();
                this.bitField0_ &= -65;
            } else {
                repeatedFieldBuilderV35.clear();
            }
            this.responseHeadersToRemove_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -129;
            if (this.corsBuilder_ == null) {
                this.cors_ = null;
            } else {
                this.cors_ = null;
                this.corsBuilder_ = null;
            }
            internalGetMutablePerFilterConfig().clear();
            internalGetMutableTypedPerFilterConfig().clear();
            this.includeRequestAttemptCount_ = false;
            this.includeAttemptCountInResponse_ = false;
            if (this.retryPolicyBuilder_ == null) {
                this.retryPolicy_ = null;
            } else {
                this.retryPolicy_ = null;
                this.retryPolicyBuilder_ = null;
            }
            if (this.retryPolicyTypedConfigBuilder_ == null) {
                this.retryPolicyTypedConfig_ = null;
            } else {
                this.retryPolicyTypedConfig_ = null;
                this.retryPolicyTypedConfigBuilder_ = null;
            }
            if (this.hedgePolicyBuilder_ == null) {
                this.hedgePolicy_ = null;
            } else {
                this.hedgePolicy_ = null;
                this.hedgePolicyBuilder_ = null;
            }
            if (this.perRequestBufferLimitBytesBuilder_ == null) {
                this.perRequestBufferLimitBytes_ = null;
            } else {
                this.perRequestBufferLimitBytes_ = null;
                this.perRequestBufferLimitBytesBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_VirtualHost_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public VirtualHost m19505getDefaultInstanceForType() {
            return VirtualHost.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public VirtualHost m19486build() throws UninitializedMessageException {
            VirtualHost virtualHostM19488buildPartial = m19488buildPartial();
            if (virtualHostM19488buildPartial.isInitialized()) {
                return virtualHostM19488buildPartial;
            }
            throw newUninitializedMessageException(virtualHostM19488buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public VirtualHost m19488buildPartial() {
            VirtualHost virtualHost = new VirtualHost(this);
            virtualHost.name_ = this.name_;
            if ((this.bitField0_ & 1) != 0) {
                this.domains_ = this.domains_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            virtualHost.domains_ = this.domains_;
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) != 0) {
                    this.routes_ = Collections.unmodifiableList(this.routes_);
                    this.bitField0_ &= -3;
                }
                virtualHost.routes_ = this.routes_;
            } else {
                virtualHost.routes_ = repeatedFieldBuilderV3.build();
            }
            virtualHost.requireTls_ = this.requireTls_;
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV32 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 4) != 0) {
                    this.virtualClusters_ = Collections.unmodifiableList(this.virtualClusters_);
                    this.bitField0_ &= -5;
                }
                virtualHost.virtualClusters_ = this.virtualClusters_;
            } else {
                virtualHost.virtualClusters_ = repeatedFieldBuilderV32.build();
            }
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV33 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV33 == null) {
                if ((this.bitField0_ & 8) != 0) {
                    this.rateLimits_ = Collections.unmodifiableList(this.rateLimits_);
                    this.bitField0_ &= -9;
                }
                virtualHost.rateLimits_ = this.rateLimits_;
            } else {
                virtualHost.rateLimits_ = repeatedFieldBuilderV33.build();
            }
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV34 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV34 == null) {
                if ((this.bitField0_ & 16) != 0) {
                    this.requestHeadersToAdd_ = Collections.unmodifiableList(this.requestHeadersToAdd_);
                    this.bitField0_ &= -17;
                }
                virtualHost.requestHeadersToAdd_ = this.requestHeadersToAdd_;
            } else {
                virtualHost.requestHeadersToAdd_ = repeatedFieldBuilderV34.build();
            }
            if ((this.bitField0_ & 32) != 0) {
                this.requestHeadersToRemove_ = this.requestHeadersToRemove_.getUnmodifiableView();
                this.bitField0_ &= -33;
            }
            virtualHost.requestHeadersToRemove_ = this.requestHeadersToRemove_;
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV35 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV35 == null) {
                if ((this.bitField0_ & 64) != 0) {
                    this.responseHeadersToAdd_ = Collections.unmodifiableList(this.responseHeadersToAdd_);
                    this.bitField0_ &= -65;
                }
                virtualHost.responseHeadersToAdd_ = this.responseHeadersToAdd_;
            } else {
                virtualHost.responseHeadersToAdd_ = repeatedFieldBuilderV35.build();
            }
            if ((this.bitField0_ & 128) != 0) {
                this.responseHeadersToRemove_ = this.responseHeadersToRemove_.getUnmodifiableView();
                this.bitField0_ &= -129;
            }
            virtualHost.responseHeadersToRemove_ = this.responseHeadersToRemove_;
            SingleFieldBuilderV3<CorsPolicy, CorsPolicy.Builder, CorsPolicyOrBuilder> singleFieldBuilderV3 = this.corsBuilder_;
            if (singleFieldBuilderV3 == null) {
                virtualHost.cors_ = this.cors_;
            } else {
                virtualHost.cors_ = singleFieldBuilderV3.build();
            }
            virtualHost.perFilterConfig_ = internalGetPerFilterConfig();
            virtualHost.perFilterConfig_.makeImmutable();
            virtualHost.typedPerFilterConfig_ = internalGetTypedPerFilterConfig();
            virtualHost.typedPerFilterConfig_.makeImmutable();
            virtualHost.includeRequestAttemptCount_ = this.includeRequestAttemptCount_;
            virtualHost.includeAttemptCountInResponse_ = this.includeAttemptCountInResponse_;
            SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> singleFieldBuilderV32 = this.retryPolicyBuilder_;
            if (singleFieldBuilderV32 == null) {
                virtualHost.retryPolicy_ = this.retryPolicy_;
            } else {
                virtualHost.retryPolicy_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV33 = this.retryPolicyTypedConfigBuilder_;
            if (singleFieldBuilderV33 == null) {
                virtualHost.retryPolicyTypedConfig_ = this.retryPolicyTypedConfig_;
            } else {
                virtualHost.retryPolicyTypedConfig_ = singleFieldBuilderV33.build();
            }
            SingleFieldBuilderV3<HedgePolicy, HedgePolicy.Builder, HedgePolicyOrBuilder> singleFieldBuilderV34 = this.hedgePolicyBuilder_;
            if (singleFieldBuilderV34 == null) {
                virtualHost.hedgePolicy_ = this.hedgePolicy_;
            } else {
                virtualHost.hedgePolicy_ = singleFieldBuilderV34.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV35 = this.perRequestBufferLimitBytesBuilder_;
            if (singleFieldBuilderV35 == null) {
                virtualHost.perRequestBufferLimitBytes_ = this.perRequestBufferLimitBytes_;
            } else {
                virtualHost.perRequestBufferLimitBytes_ = singleFieldBuilderV35.build();
            }
            onBuilt();
            return virtualHost;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19504clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19516setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19494clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19497clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19518setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19484addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19509mergeFrom(Message message) {
            if (message instanceof VirtualHost) {
                return mergeFrom((VirtualHost) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(VirtualHost virtualHost) {
            if (virtualHost == VirtualHost.getDefaultInstance()) {
                return this;
            }
            if (!virtualHost.getName().isEmpty()) {
                this.name_ = virtualHost.name_;
                onChanged();
            }
            if (!virtualHost.domains_.isEmpty()) {
                if (this.domains_.isEmpty()) {
                    this.domains_ = virtualHost.domains_;
                    this.bitField0_ &= -2;
                } else {
                    ensureDomainsIsMutable();
                    this.domains_.addAll(virtualHost.domains_);
                }
                onChanged();
            }
            if (this.routesBuilder_ == null) {
                if (!virtualHost.routes_.isEmpty()) {
                    if (this.routes_.isEmpty()) {
                        this.routes_ = virtualHost.routes_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureRoutesIsMutable();
                        this.routes_.addAll(virtualHost.routes_);
                    }
                    onChanged();
                }
            } else if (!virtualHost.routes_.isEmpty()) {
                if (!this.routesBuilder_.isEmpty()) {
                    this.routesBuilder_.addAllMessages(virtualHost.routes_);
                } else {
                    this.routesBuilder_.dispose();
                    this.routesBuilder_ = null;
                    this.routes_ = virtualHost.routes_;
                    this.bitField0_ &= -3;
                    this.routesBuilder_ = VirtualHost.alwaysUseFieldBuilders ? getRoutesFieldBuilder() : null;
                }
            }
            if (virtualHost.requireTls_ != 0) {
                setRequireTlsValue(virtualHost.getRequireTlsValue());
            }
            if (this.virtualClustersBuilder_ == null) {
                if (!virtualHost.virtualClusters_.isEmpty()) {
                    if (this.virtualClusters_.isEmpty()) {
                        this.virtualClusters_ = virtualHost.virtualClusters_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureVirtualClustersIsMutable();
                        this.virtualClusters_.addAll(virtualHost.virtualClusters_);
                    }
                    onChanged();
                }
            } else if (!virtualHost.virtualClusters_.isEmpty()) {
                if (!this.virtualClustersBuilder_.isEmpty()) {
                    this.virtualClustersBuilder_.addAllMessages(virtualHost.virtualClusters_);
                } else {
                    this.virtualClustersBuilder_.dispose();
                    this.virtualClustersBuilder_ = null;
                    this.virtualClusters_ = virtualHost.virtualClusters_;
                    this.bitField0_ &= -5;
                    this.virtualClustersBuilder_ = VirtualHost.alwaysUseFieldBuilders ? getVirtualClustersFieldBuilder() : null;
                }
            }
            if (this.rateLimitsBuilder_ == null) {
                if (!virtualHost.rateLimits_.isEmpty()) {
                    if (this.rateLimits_.isEmpty()) {
                        this.rateLimits_ = virtualHost.rateLimits_;
                        this.bitField0_ &= -9;
                    } else {
                        ensureRateLimitsIsMutable();
                        this.rateLimits_.addAll(virtualHost.rateLimits_);
                    }
                    onChanged();
                }
            } else if (!virtualHost.rateLimits_.isEmpty()) {
                if (!this.rateLimitsBuilder_.isEmpty()) {
                    this.rateLimitsBuilder_.addAllMessages(virtualHost.rateLimits_);
                } else {
                    this.rateLimitsBuilder_.dispose();
                    this.rateLimitsBuilder_ = null;
                    this.rateLimits_ = virtualHost.rateLimits_;
                    this.bitField0_ &= -9;
                    this.rateLimitsBuilder_ = VirtualHost.alwaysUseFieldBuilders ? getRateLimitsFieldBuilder() : null;
                }
            }
            if (this.requestHeadersToAddBuilder_ == null) {
                if (!virtualHost.requestHeadersToAdd_.isEmpty()) {
                    if (this.requestHeadersToAdd_.isEmpty()) {
                        this.requestHeadersToAdd_ = virtualHost.requestHeadersToAdd_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureRequestHeadersToAddIsMutable();
                        this.requestHeadersToAdd_.addAll(virtualHost.requestHeadersToAdd_);
                    }
                    onChanged();
                }
            } else if (!virtualHost.requestHeadersToAdd_.isEmpty()) {
                if (!this.requestHeadersToAddBuilder_.isEmpty()) {
                    this.requestHeadersToAddBuilder_.addAllMessages(virtualHost.requestHeadersToAdd_);
                } else {
                    this.requestHeadersToAddBuilder_.dispose();
                    this.requestHeadersToAddBuilder_ = null;
                    this.requestHeadersToAdd_ = virtualHost.requestHeadersToAdd_;
                    this.bitField0_ &= -17;
                    this.requestHeadersToAddBuilder_ = VirtualHost.alwaysUseFieldBuilders ? getRequestHeadersToAddFieldBuilder() : null;
                }
            }
            if (!virtualHost.requestHeadersToRemove_.isEmpty()) {
                if (this.requestHeadersToRemove_.isEmpty()) {
                    this.requestHeadersToRemove_ = virtualHost.requestHeadersToRemove_;
                    this.bitField0_ &= -33;
                } else {
                    ensureRequestHeadersToRemoveIsMutable();
                    this.requestHeadersToRemove_.addAll(virtualHost.requestHeadersToRemove_);
                }
                onChanged();
            }
            if (this.responseHeadersToAddBuilder_ == null) {
                if (!virtualHost.responseHeadersToAdd_.isEmpty()) {
                    if (this.responseHeadersToAdd_.isEmpty()) {
                        this.responseHeadersToAdd_ = virtualHost.responseHeadersToAdd_;
                        this.bitField0_ &= -65;
                    } else {
                        ensureResponseHeadersToAddIsMutable();
                        this.responseHeadersToAdd_.addAll(virtualHost.responseHeadersToAdd_);
                    }
                    onChanged();
                }
            } else if (!virtualHost.responseHeadersToAdd_.isEmpty()) {
                if (!this.responseHeadersToAddBuilder_.isEmpty()) {
                    this.responseHeadersToAddBuilder_.addAllMessages(virtualHost.responseHeadersToAdd_);
                } else {
                    this.responseHeadersToAddBuilder_.dispose();
                    this.responseHeadersToAddBuilder_ = null;
                    this.responseHeadersToAdd_ = virtualHost.responseHeadersToAdd_;
                    this.bitField0_ &= -65;
                    this.responseHeadersToAddBuilder_ = VirtualHost.alwaysUseFieldBuilders ? getResponseHeadersToAddFieldBuilder() : null;
                }
            }
            if (!virtualHost.responseHeadersToRemove_.isEmpty()) {
                if (this.responseHeadersToRemove_.isEmpty()) {
                    this.responseHeadersToRemove_ = virtualHost.responseHeadersToRemove_;
                    this.bitField0_ &= -129;
                } else {
                    ensureResponseHeadersToRemoveIsMutable();
                    this.responseHeadersToRemove_.addAll(virtualHost.responseHeadersToRemove_);
                }
                onChanged();
            }
            if (virtualHost.hasCors()) {
                mergeCors(virtualHost.getCors());
            }
            internalGetMutablePerFilterConfig().mergeFrom(virtualHost.internalGetPerFilterConfig());
            internalGetMutableTypedPerFilterConfig().mergeFrom(virtualHost.internalGetTypedPerFilterConfig());
            if (virtualHost.getIncludeRequestAttemptCount()) {
                setIncludeRequestAttemptCount(virtualHost.getIncludeRequestAttemptCount());
            }
            if (virtualHost.getIncludeAttemptCountInResponse()) {
                setIncludeAttemptCountInResponse(virtualHost.getIncludeAttemptCountInResponse());
            }
            if (virtualHost.hasRetryPolicy()) {
                mergeRetryPolicy(virtualHost.getRetryPolicy());
            }
            if (virtualHost.hasRetryPolicyTypedConfig()) {
                mergeRetryPolicyTypedConfig(virtualHost.getRetryPolicyTypedConfig());
            }
            if (virtualHost.hasHedgePolicy()) {
                mergeHedgePolicy(virtualHost.getHedgePolicy());
            }
            if (virtualHost.hasPerRequestBufferLimitBytes()) {
                mergePerRequestBufferLimitBytes(virtualHost.getPerRequestBufferLimitBytes());
            }
            m19514mergeUnknownFields(virtualHost.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHost.Builder m19510mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHost.access$3100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHost r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHost) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHost r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHost) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHost.Builder.m19510mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHost$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setName(String str) {
            str.getClass();
            this.name_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNameBytes(ByteString byteString) {
            byteString.getClass();
            VirtualHost.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = VirtualHost.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        private void ensureDomainsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.domains_ = new LazyStringArrayList(this.domains_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        /* renamed from: getDomainsList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo19475getDomainsList() {
            return this.domains_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public int getDomainsCount() {
            return this.domains_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public String getDomains(int i) {
            return (String) this.domains_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public ByteString getDomainsBytes(int i) {
            return this.domains_.getByteString(i);
        }

        public Builder setDomains(int i, String str) {
            str.getClass();
            ensureDomainsIsMutable();
            this.domains_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addDomains(String str) {
            str.getClass();
            ensureDomainsIsMutable();
            this.domains_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllDomains(Iterable<String> iterable) {
            ensureDomainsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.domains_);
            onChanged();
            return this;
        }

        public Builder clearDomains() {
            this.domains_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addDomainsBytes(ByteString byteString) {
            byteString.getClass();
            VirtualHost.checkByteStringIsUtf8(byteString);
            ensureDomainsIsMutable();
            this.domains_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureRoutesIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.routes_ = new ArrayList(this.routes_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public List<Route> getRoutesList() {
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.routes_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public int getRoutesCount() {
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.routes_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public Route getRoutes(int i) {
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.routes_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRoutes(int i, Route route) {
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                route.getClass();
                ensureRoutesIsMutable();
                this.routes_.set(i, route);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, route);
            }
            return this;
        }

        public Builder setRoutes(int i, Route.Builder builder) {
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRoutesIsMutable();
                this.routes_.set(i, builder.m18791build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m18791build());
            }
            return this;
        }

        public Builder addRoutes(Route route) {
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                route.getClass();
                ensureRoutesIsMutable();
                this.routes_.add(route);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(route);
            }
            return this;
        }

        public Builder addRoutes(int i, Route route) {
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                route.getClass();
                ensureRoutesIsMutable();
                this.routes_.add(i, route);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, route);
            }
            return this;
        }

        public Builder addRoutes(Route.Builder builder) {
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRoutesIsMutable();
                this.routes_.add(builder.m18791build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m18791build());
            }
            return this;
        }

        public Builder addRoutes(int i, Route.Builder builder) {
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRoutesIsMutable();
                this.routes_.add(i, builder.m18791build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m18791build());
            }
            return this;
        }

        public Builder addAllRoutes(Iterable<? extends Route> iterable) {
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRoutesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.routes_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRoutes() {
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.routes_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRoutes(int i) {
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRoutesIsMutable();
                this.routes_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Route.Builder getRoutesBuilder(int i) {
            return getRoutesFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public RouteOrBuilder getRoutesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.routes_.get(i);
            }
            return (RouteOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public List<? extends RouteOrBuilder> getRoutesOrBuilderList() {
            RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> repeatedFieldBuilderV3 = this.routesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.routes_);
        }

        public Route.Builder addRoutesBuilder() {
            return getRoutesFieldBuilder().addBuilder(Route.getDefaultInstance());
        }

        public Route.Builder addRoutesBuilder(int i) {
            return getRoutesFieldBuilder().addBuilder(i, Route.getDefaultInstance());
        }

        public List<Route.Builder> getRoutesBuilderList() {
            return getRoutesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> getRoutesFieldBuilder() {
            if (this.routesBuilder_ == null) {
                this.routesBuilder_ = new RepeatedFieldBuilderV3<>(this.routes_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.routes_ = null;
            }
            return this.routesBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public TlsRequirementType getRequireTls() {
            TlsRequirementType tlsRequirementTypeValueOf = TlsRequirementType.valueOf(this.requireTls_);
            return tlsRequirementTypeValueOf == null ? TlsRequirementType.UNRECOGNIZED : tlsRequirementTypeValueOf;
        }

        public Builder setRequireTls(TlsRequirementType tlsRequirementType) {
            tlsRequirementType.getClass();
            this.requireTls_ = tlsRequirementType.getNumber();
            onChanged();
            return this;
        }

        public Builder clearRequireTls() {
            this.requireTls_ = 0;
            onChanged();
            return this;
        }

        private void ensureVirtualClustersIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.virtualClusters_ = new ArrayList(this.virtualClusters_);
                this.bitField0_ |= 4;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public List<VirtualCluster> getVirtualClustersList() {
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV3 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.virtualClusters_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public int getVirtualClustersCount() {
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV3 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.virtualClusters_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public VirtualCluster getVirtualClusters(int i) {
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV3 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.virtualClusters_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setVirtualClusters(int i, VirtualCluster virtualCluster) {
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV3 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                virtualCluster.getClass();
                ensureVirtualClustersIsMutable();
                this.virtualClusters_.set(i, virtualCluster);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, virtualCluster);
            }
            return this;
        }

        public Builder setVirtualClusters(int i, VirtualCluster.Builder builder) {
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV3 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureVirtualClustersIsMutable();
                this.virtualClusters_.set(i, builder.m19437build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m19437build());
            }
            return this;
        }

        public Builder addVirtualClusters(VirtualCluster virtualCluster) {
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV3 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                virtualCluster.getClass();
                ensureVirtualClustersIsMutable();
                this.virtualClusters_.add(virtualCluster);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(virtualCluster);
            }
            return this;
        }

        public Builder addVirtualClusters(int i, VirtualCluster virtualCluster) {
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV3 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                virtualCluster.getClass();
                ensureVirtualClustersIsMutable();
                this.virtualClusters_.add(i, virtualCluster);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, virtualCluster);
            }
            return this;
        }

        public Builder addVirtualClusters(VirtualCluster.Builder builder) {
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV3 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureVirtualClustersIsMutable();
                this.virtualClusters_.add(builder.m19437build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m19437build());
            }
            return this;
        }

        public Builder addVirtualClusters(int i, VirtualCluster.Builder builder) {
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV3 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureVirtualClustersIsMutable();
                this.virtualClusters_.add(i, builder.m19437build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m19437build());
            }
            return this;
        }

        public Builder addAllVirtualClusters(Iterable<? extends VirtualCluster> iterable) {
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV3 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureVirtualClustersIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.virtualClusters_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearVirtualClusters() {
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV3 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.virtualClusters_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeVirtualClusters(int i) {
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV3 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureVirtualClustersIsMutable();
                this.virtualClusters_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public VirtualCluster.Builder getVirtualClustersBuilder(int i) {
            return getVirtualClustersFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public VirtualClusterOrBuilder getVirtualClustersOrBuilder(int i) {
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV3 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.virtualClusters_.get(i);
            }
            return (VirtualClusterOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public List<? extends VirtualClusterOrBuilder> getVirtualClustersOrBuilderList() {
            RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> repeatedFieldBuilderV3 = this.virtualClustersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.virtualClusters_);
        }

        public VirtualCluster.Builder addVirtualClustersBuilder() {
            return getVirtualClustersFieldBuilder().addBuilder(VirtualCluster.getDefaultInstance());
        }

        public VirtualCluster.Builder addVirtualClustersBuilder(int i) {
            return getVirtualClustersFieldBuilder().addBuilder(i, VirtualCluster.getDefaultInstance());
        }

        public List<VirtualCluster.Builder> getVirtualClustersBuilderList() {
            return getVirtualClustersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<VirtualCluster, VirtualCluster.Builder, VirtualClusterOrBuilder> getVirtualClustersFieldBuilder() {
            if (this.virtualClustersBuilder_ == null) {
                this.virtualClustersBuilder_ = new RepeatedFieldBuilderV3<>(this.virtualClusters_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                this.virtualClusters_ = null;
            }
            return this.virtualClustersBuilder_;
        }

        private void ensureRateLimitsIsMutable() {
            if ((this.bitField0_ & 8) == 0) {
                this.rateLimits_ = new ArrayList(this.rateLimits_);
                this.bitField0_ |= 8;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public List<RateLimit> getRateLimitsList() {
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV3 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.rateLimits_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public int getRateLimitsCount() {
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV3 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rateLimits_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public RateLimit getRateLimits(int i) {
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV3 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rateLimits_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRateLimits(int i, RateLimit rateLimit) {
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV3 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                rateLimit.getClass();
                ensureRateLimitsIsMutable();
                this.rateLimits_.set(i, rateLimit);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, rateLimit);
            }
            return this;
        }

        public Builder setRateLimits(int i, RateLimit.Builder builder) {
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV3 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRateLimitsIsMutable();
                this.rateLimits_.set(i, builder.m18512build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m18512build());
            }
            return this;
        }

        public Builder addRateLimits(RateLimit rateLimit) {
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV3 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                rateLimit.getClass();
                ensureRateLimitsIsMutable();
                this.rateLimits_.add(rateLimit);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(rateLimit);
            }
            return this;
        }

        public Builder addRateLimits(int i, RateLimit rateLimit) {
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV3 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                rateLimit.getClass();
                ensureRateLimitsIsMutable();
                this.rateLimits_.add(i, rateLimit);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, rateLimit);
            }
            return this;
        }

        public Builder addRateLimits(RateLimit.Builder builder) {
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV3 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRateLimitsIsMutable();
                this.rateLimits_.add(builder.m18512build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m18512build());
            }
            return this;
        }

        public Builder addRateLimits(int i, RateLimit.Builder builder) {
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV3 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRateLimitsIsMutable();
                this.rateLimits_.add(i, builder.m18512build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m18512build());
            }
            return this;
        }

        public Builder addAllRateLimits(Iterable<? extends RateLimit> iterable) {
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV3 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRateLimitsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.rateLimits_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRateLimits() {
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV3 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rateLimits_ = Collections.emptyList();
                this.bitField0_ &= -9;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRateLimits(int i) {
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV3 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRateLimitsIsMutable();
                this.rateLimits_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public RateLimit.Builder getRateLimitsBuilder(int i) {
            return getRateLimitsFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public RateLimitOrBuilder getRateLimitsOrBuilder(int i) {
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV3 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rateLimits_.get(i);
            }
            return (RateLimitOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public List<? extends RateLimitOrBuilder> getRateLimitsOrBuilderList() {
            RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> repeatedFieldBuilderV3 = this.rateLimitsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rateLimits_);
        }

        public RateLimit.Builder addRateLimitsBuilder() {
            return getRateLimitsFieldBuilder().addBuilder(RateLimit.getDefaultInstance());
        }

        public RateLimit.Builder addRateLimitsBuilder(int i) {
            return getRateLimitsFieldBuilder().addBuilder(i, RateLimit.getDefaultInstance());
        }

        public List<RateLimit.Builder> getRateLimitsBuilderList() {
            return getRateLimitsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<RateLimit, RateLimit.Builder, RateLimitOrBuilder> getRateLimitsFieldBuilder() {
            if (this.rateLimitsBuilder_ == null) {
                this.rateLimitsBuilder_ = new RepeatedFieldBuilderV3<>(this.rateLimits_, (this.bitField0_ & 8) != 0, getParentForChildren(), isClean());
                this.rateLimits_ = null;
            }
            return this.rateLimitsBuilder_;
        }

        private void ensureRequestHeadersToAddIsMutable() {
            if ((this.bitField0_ & 16) == 0) {
                this.requestHeadersToAdd_ = new ArrayList(this.requestHeadersToAdd_);
                this.bitField0_ |= 16;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public List<HeaderValueOption> getRequestHeadersToAddList() {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.requestHeadersToAdd_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public int getRequestHeadersToAddCount() {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.requestHeadersToAdd_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public HeaderValueOption getRequestHeadersToAdd(int i) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.requestHeadersToAdd_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRequestHeadersToAdd(int i, HeaderValueOption headerValueOption) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerValueOption.getClass();
                ensureRequestHeadersToAddIsMutable();
                this.requestHeadersToAdd_.set(i, headerValueOption);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, headerValueOption);
            }
            return this;
        }

        public Builder setRequestHeadersToAdd(int i, HeaderValueOption.Builder builder) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequestHeadersToAddIsMutable();
                this.requestHeadersToAdd_.set(i, builder.m15507build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m15507build());
            }
            return this;
        }

        public Builder addRequestHeadersToAdd(HeaderValueOption headerValueOption) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerValueOption.getClass();
                ensureRequestHeadersToAddIsMutable();
                this.requestHeadersToAdd_.add(headerValueOption);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(headerValueOption);
            }
            return this;
        }

        public Builder addRequestHeadersToAdd(int i, HeaderValueOption headerValueOption) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerValueOption.getClass();
                ensureRequestHeadersToAddIsMutable();
                this.requestHeadersToAdd_.add(i, headerValueOption);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, headerValueOption);
            }
            return this;
        }

        public Builder addRequestHeadersToAdd(HeaderValueOption.Builder builder) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequestHeadersToAddIsMutable();
                this.requestHeadersToAdd_.add(builder.m15507build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m15507build());
            }
            return this;
        }

        public Builder addRequestHeadersToAdd(int i, HeaderValueOption.Builder builder) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequestHeadersToAddIsMutable();
                this.requestHeadersToAdd_.add(i, builder.m15507build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m15507build());
            }
            return this;
        }

        public Builder addAllRequestHeadersToAdd(Iterable<? extends HeaderValueOption> iterable) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequestHeadersToAddIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.requestHeadersToAdd_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRequestHeadersToAdd() {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.requestHeadersToAdd_ = Collections.emptyList();
                this.bitField0_ &= -17;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRequestHeadersToAdd(int i) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequestHeadersToAddIsMutable();
                this.requestHeadersToAdd_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public HeaderValueOption.Builder getRequestHeadersToAddBuilder(int i) {
            return getRequestHeadersToAddFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public HeaderValueOptionOrBuilder getRequestHeadersToAddOrBuilder(int i) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.requestHeadersToAdd_.get(i);
            }
            return (HeaderValueOptionOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public List<? extends HeaderValueOptionOrBuilder> getRequestHeadersToAddOrBuilderList() {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.requestHeadersToAdd_);
        }

        public HeaderValueOption.Builder addRequestHeadersToAddBuilder() {
            return getRequestHeadersToAddFieldBuilder().addBuilder(HeaderValueOption.getDefaultInstance());
        }

        public HeaderValueOption.Builder addRequestHeadersToAddBuilder(int i) {
            return getRequestHeadersToAddFieldBuilder().addBuilder(i, HeaderValueOption.getDefaultInstance());
        }

        public List<HeaderValueOption.Builder> getRequestHeadersToAddBuilderList() {
            return getRequestHeadersToAddFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> getRequestHeadersToAddFieldBuilder() {
            if (this.requestHeadersToAddBuilder_ == null) {
                this.requestHeadersToAddBuilder_ = new RepeatedFieldBuilderV3<>(this.requestHeadersToAdd_, (this.bitField0_ & 16) != 0, getParentForChildren(), isClean());
                this.requestHeadersToAdd_ = null;
            }
            return this.requestHeadersToAddBuilder_;
        }

        private void ensureRequestHeadersToRemoveIsMutable() {
            if ((this.bitField0_ & 32) == 0) {
                this.requestHeadersToRemove_ = new LazyStringArrayList(this.requestHeadersToRemove_);
                this.bitField0_ |= 32;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        /* renamed from: getRequestHeadersToRemoveList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo19476getRequestHeadersToRemoveList() {
            return this.requestHeadersToRemove_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public int getRequestHeadersToRemoveCount() {
            return this.requestHeadersToRemove_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public String getRequestHeadersToRemove(int i) {
            return (String) this.requestHeadersToRemove_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public ByteString getRequestHeadersToRemoveBytes(int i) {
            return this.requestHeadersToRemove_.getByteString(i);
        }

        public Builder setRequestHeadersToRemove(int i, String str) {
            str.getClass();
            ensureRequestHeadersToRemoveIsMutable();
            this.requestHeadersToRemove_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addRequestHeadersToRemove(String str) {
            str.getClass();
            ensureRequestHeadersToRemoveIsMutable();
            this.requestHeadersToRemove_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllRequestHeadersToRemove(Iterable<String> iterable) {
            ensureRequestHeadersToRemoveIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.requestHeadersToRemove_);
            onChanged();
            return this;
        }

        public Builder clearRequestHeadersToRemove() {
            this.requestHeadersToRemove_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -33;
            onChanged();
            return this;
        }

        public Builder addRequestHeadersToRemoveBytes(ByteString byteString) {
            byteString.getClass();
            VirtualHost.checkByteStringIsUtf8(byteString);
            ensureRequestHeadersToRemoveIsMutable();
            this.requestHeadersToRemove_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureResponseHeadersToAddIsMutable() {
            if ((this.bitField0_ & 64) == 0) {
                this.responseHeadersToAdd_ = new ArrayList(this.responseHeadersToAdd_);
                this.bitField0_ |= 64;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public List<HeaderValueOption> getResponseHeadersToAddList() {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.responseHeadersToAdd_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public int getResponseHeadersToAddCount() {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.responseHeadersToAdd_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public HeaderValueOption getResponseHeadersToAdd(int i) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.responseHeadersToAdd_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setResponseHeadersToAdd(int i, HeaderValueOption headerValueOption) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerValueOption.getClass();
                ensureResponseHeadersToAddIsMutable();
                this.responseHeadersToAdd_.set(i, headerValueOption);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, headerValueOption);
            }
            return this;
        }

        public Builder setResponseHeadersToAdd(int i, HeaderValueOption.Builder builder) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureResponseHeadersToAddIsMutable();
                this.responseHeadersToAdd_.set(i, builder.m15507build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m15507build());
            }
            return this;
        }

        public Builder addResponseHeadersToAdd(HeaderValueOption headerValueOption) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerValueOption.getClass();
                ensureResponseHeadersToAddIsMutable();
                this.responseHeadersToAdd_.add(headerValueOption);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(headerValueOption);
            }
            return this;
        }

        public Builder addResponseHeadersToAdd(int i, HeaderValueOption headerValueOption) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerValueOption.getClass();
                ensureResponseHeadersToAddIsMutable();
                this.responseHeadersToAdd_.add(i, headerValueOption);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, headerValueOption);
            }
            return this;
        }

        public Builder addResponseHeadersToAdd(HeaderValueOption.Builder builder) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureResponseHeadersToAddIsMutable();
                this.responseHeadersToAdd_.add(builder.m15507build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m15507build());
            }
            return this;
        }

        public Builder addResponseHeadersToAdd(int i, HeaderValueOption.Builder builder) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureResponseHeadersToAddIsMutable();
                this.responseHeadersToAdd_.add(i, builder.m15507build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m15507build());
            }
            return this;
        }

        public Builder addAllResponseHeadersToAdd(Iterable<? extends HeaderValueOption> iterable) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureResponseHeadersToAddIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.responseHeadersToAdd_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearResponseHeadersToAdd() {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.responseHeadersToAdd_ = Collections.emptyList();
                this.bitField0_ &= -65;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeResponseHeadersToAdd(int i) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureResponseHeadersToAddIsMutable();
                this.responseHeadersToAdd_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public HeaderValueOption.Builder getResponseHeadersToAddBuilder(int i) {
            return getResponseHeadersToAddFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public HeaderValueOptionOrBuilder getResponseHeadersToAddOrBuilder(int i) {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.responseHeadersToAdd_.get(i);
            }
            return (HeaderValueOptionOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public List<? extends HeaderValueOptionOrBuilder> getResponseHeadersToAddOrBuilderList() {
            RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.responseHeadersToAddBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.responseHeadersToAdd_);
        }

        public HeaderValueOption.Builder addResponseHeadersToAddBuilder() {
            return getResponseHeadersToAddFieldBuilder().addBuilder(HeaderValueOption.getDefaultInstance());
        }

        public HeaderValueOption.Builder addResponseHeadersToAddBuilder(int i) {
            return getResponseHeadersToAddFieldBuilder().addBuilder(i, HeaderValueOption.getDefaultInstance());
        }

        public List<HeaderValueOption.Builder> getResponseHeadersToAddBuilderList() {
            return getResponseHeadersToAddFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> getResponseHeadersToAddFieldBuilder() {
            if (this.responseHeadersToAddBuilder_ == null) {
                this.responseHeadersToAddBuilder_ = new RepeatedFieldBuilderV3<>(this.responseHeadersToAdd_, (this.bitField0_ & 64) != 0, getParentForChildren(), isClean());
                this.responseHeadersToAdd_ = null;
            }
            return this.responseHeadersToAddBuilder_;
        }

        private void ensureResponseHeadersToRemoveIsMutable() {
            if ((this.bitField0_ & 128) == 0) {
                this.responseHeadersToRemove_ = new LazyStringArrayList(this.responseHeadersToRemove_);
                this.bitField0_ |= 128;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        /* renamed from: getResponseHeadersToRemoveList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo19477getResponseHeadersToRemoveList() {
            return this.responseHeadersToRemove_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public int getResponseHeadersToRemoveCount() {
            return this.responseHeadersToRemove_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public String getResponseHeadersToRemove(int i) {
            return (String) this.responseHeadersToRemove_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public ByteString getResponseHeadersToRemoveBytes(int i) {
            return this.responseHeadersToRemove_.getByteString(i);
        }

        public Builder setResponseHeadersToRemove(int i, String str) {
            str.getClass();
            ensureResponseHeadersToRemoveIsMutable();
            this.responseHeadersToRemove_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addResponseHeadersToRemove(String str) {
            str.getClass();
            ensureResponseHeadersToRemoveIsMutable();
            this.responseHeadersToRemove_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllResponseHeadersToRemove(Iterable<String> iterable) {
            ensureResponseHeadersToRemoveIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.responseHeadersToRemove_);
            onChanged();
            return this;
        }

        public Builder clearResponseHeadersToRemove() {
            this.responseHeadersToRemove_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -129;
            onChanged();
            return this;
        }

        public Builder addResponseHeadersToRemoveBytes(ByteString byteString) {
            byteString.getClass();
            VirtualHost.checkByteStringIsUtf8(byteString);
            ensureResponseHeadersToRemoveIsMutable();
            this.responseHeadersToRemove_.add(byteString);
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public CorsPolicy getCors() {
            SingleFieldBuilderV3<CorsPolicy, CorsPolicy.Builder, CorsPolicyOrBuilder> singleFieldBuilderV3 = this.corsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            CorsPolicy corsPolicy = this.cors_;
            return corsPolicy == null ? CorsPolicy.getDefaultInstance() : corsPolicy;
        }

        public Builder setCors(CorsPolicy corsPolicy) {
            SingleFieldBuilderV3<CorsPolicy, CorsPolicy.Builder, CorsPolicyOrBuilder> singleFieldBuilderV3 = this.corsBuilder_;
            if (singleFieldBuilderV3 == null) {
                corsPolicy.getClass();
                this.cors_ = corsPolicy;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(corsPolicy);
            }
            return this;
        }

        public Builder setCors(CorsPolicy.Builder builder) {
            SingleFieldBuilderV3<CorsPolicy, CorsPolicy.Builder, CorsPolicyOrBuilder> singleFieldBuilderV3 = this.corsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.cors_ = builder.m17868build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m17868build());
            }
            return this;
        }

        public Builder mergeCors(CorsPolicy corsPolicy) {
            SingleFieldBuilderV3<CorsPolicy, CorsPolicy.Builder, CorsPolicyOrBuilder> singleFieldBuilderV3 = this.corsBuilder_;
            if (singleFieldBuilderV3 == null) {
                CorsPolicy corsPolicy2 = this.cors_;
                if (corsPolicy2 != null) {
                    this.cors_ = CorsPolicy.newBuilder(corsPolicy2).mergeFrom(corsPolicy).m17870buildPartial();
                } else {
                    this.cors_ = corsPolicy;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(corsPolicy);
            }
            return this;
        }

        public Builder clearCors() {
            if (this.corsBuilder_ == null) {
                this.cors_ = null;
                onChanged();
            } else {
                this.cors_ = null;
                this.corsBuilder_ = null;
            }
            return this;
        }

        public CorsPolicy.Builder getCorsBuilder() {
            onChanged();
            return getCorsFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public CorsPolicyOrBuilder getCorsOrBuilder() {
            SingleFieldBuilderV3<CorsPolicy, CorsPolicy.Builder, CorsPolicyOrBuilder> singleFieldBuilderV3 = this.corsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (CorsPolicyOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            CorsPolicy corsPolicy = this.cors_;
            return corsPolicy == null ? CorsPolicy.getDefaultInstance() : corsPolicy;
        }

        private SingleFieldBuilderV3<CorsPolicy, CorsPolicy.Builder, CorsPolicyOrBuilder> getCorsFieldBuilder() {
            if (this.corsBuilder_ == null) {
                this.corsBuilder_ = new SingleFieldBuilderV3<>(getCors(), getParentForChildren(), isClean());
                this.cors_ = null;
            }
            return this.corsBuilder_;
        }

        private MapField<String, Struct> internalGetPerFilterConfig() {
            MapField<String, Struct> mapField = this.perFilterConfig_;
            return mapField == null ? MapField.emptyMapField(PerFilterConfigDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, Struct> internalGetMutablePerFilterConfig() {
            onChanged();
            if (this.perFilterConfig_ == null) {
                this.perFilterConfig_ = MapField.newMapField(PerFilterConfigDefaultEntryHolder.defaultEntry);
            }
            if (!this.perFilterConfig_.isMutable()) {
                this.perFilterConfig_ = this.perFilterConfig_.copy();
            }
            return this.perFilterConfig_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        @Deprecated
        public int getPerFilterConfigCount() {
            return internalGetPerFilterConfig().getMap().size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        @Deprecated
        public boolean containsPerFilterConfig(String str) {
            str.getClass();
            return internalGetPerFilterConfig().getMap().containsKey(str);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        @Deprecated
        public Map<String, Struct> getPerFilterConfig() {
            return getPerFilterConfigMap();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        @Deprecated
        public Map<String, Struct> getPerFilterConfigMap() {
            return internalGetPerFilterConfig().getMap();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        @Deprecated
        public Struct getPerFilterConfigOrDefault(String str, Struct struct) {
            str.getClass();
            Map map = internalGetPerFilterConfig().getMap();
            return map.containsKey(str) ? (Struct) map.get(str) : struct;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        @Deprecated
        public Struct getPerFilterConfigOrThrow(String str) {
            str.getClass();
            Map map = internalGetPerFilterConfig().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return (Struct) map.get(str);
        }

        @Deprecated
        public Builder clearPerFilterConfig() {
            internalGetMutablePerFilterConfig().getMutableMap().clear();
            return this;
        }

        @Deprecated
        public Builder removePerFilterConfig(String str) {
            str.getClass();
            internalGetMutablePerFilterConfig().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, Struct> getMutablePerFilterConfig() {
            return internalGetMutablePerFilterConfig().getMutableMap();
        }

        @Deprecated
        public Builder putPerFilterConfig(String str, Struct struct) {
            str.getClass();
            struct.getClass();
            internalGetMutablePerFilterConfig().getMutableMap().put(str, struct);
            return this;
        }

        @Deprecated
        public Builder putAllPerFilterConfig(Map<String, Struct> map) {
            internalGetMutablePerFilterConfig().getMutableMap().putAll(map);
            return this;
        }

        private MapField<String, Any> internalGetTypedPerFilterConfig() {
            MapField<String, Any> mapField = this.typedPerFilterConfig_;
            return mapField == null ? MapField.emptyMapField(TypedPerFilterConfigDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, Any> internalGetMutableTypedPerFilterConfig() {
            onChanged();
            if (this.typedPerFilterConfig_ == null) {
                this.typedPerFilterConfig_ = MapField.newMapField(TypedPerFilterConfigDefaultEntryHolder.defaultEntry);
            }
            if (!this.typedPerFilterConfig_.isMutable()) {
                this.typedPerFilterConfig_ = this.typedPerFilterConfig_.copy();
            }
            return this.typedPerFilterConfig_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public int getTypedPerFilterConfigCount() {
            return internalGetTypedPerFilterConfig().getMap().size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public boolean containsTypedPerFilterConfig(String str) {
            str.getClass();
            return internalGetTypedPerFilterConfig().getMap().containsKey(str);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        @Deprecated
        public Map<String, Any> getTypedPerFilterConfig() {
            return getTypedPerFilterConfigMap();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public Map<String, Any> getTypedPerFilterConfigMap() {
            return internalGetTypedPerFilterConfig().getMap();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public Any getTypedPerFilterConfigOrDefault(String str, Any any) {
            str.getClass();
            Map map = internalGetTypedPerFilterConfig().getMap();
            return map.containsKey(str) ? (Any) map.get(str) : any;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public Any getTypedPerFilterConfigOrThrow(String str) {
            str.getClass();
            Map map = internalGetTypedPerFilterConfig().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return (Any) map.get(str);
        }

        public Builder clearTypedPerFilterConfig() {
            internalGetMutableTypedPerFilterConfig().getMutableMap().clear();
            return this;
        }

        public Builder removeTypedPerFilterConfig(String str) {
            str.getClass();
            internalGetMutableTypedPerFilterConfig().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, Any> getMutableTypedPerFilterConfig() {
            return internalGetMutableTypedPerFilterConfig().getMutableMap();
        }

        public Builder putTypedPerFilterConfig(String str, Any any) {
            str.getClass();
            any.getClass();
            internalGetMutableTypedPerFilterConfig().getMutableMap().put(str, any);
            return this;
        }

        public Builder putAllTypedPerFilterConfig(Map<String, Any> map) {
            internalGetMutableTypedPerFilterConfig().getMutableMap().putAll(map);
            return this;
        }

        public Builder clearIncludeRequestAttemptCount() {
            this.includeRequestAttemptCount_ = false;
            onChanged();
            return this;
        }

        public Builder clearIncludeAttemptCountInResponse() {
            this.includeAttemptCountInResponse_ = false;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public RetryPolicy getRetryPolicy() {
            SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> singleFieldBuilderV3 = this.retryPolicyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            RetryPolicy retryPolicy = this.retryPolicy_;
            return retryPolicy == null ? RetryPolicy.getDefaultInstance() : retryPolicy;
        }

        public Builder setRetryPolicy(RetryPolicy retryPolicy) {
            SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> singleFieldBuilderV3 = this.retryPolicyBuilder_;
            if (singleFieldBuilderV3 == null) {
                retryPolicy.getClass();
                this.retryPolicy_ = retryPolicy;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(retryPolicy);
            }
            return this;
        }

        public Builder setRetryPolicy(RetryPolicy.Builder builder) {
            SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> singleFieldBuilderV3 = this.retryPolicyBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.retryPolicy_ = builder.m18605build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m18605build());
            }
            return this;
        }

        public Builder mergeRetryPolicy(RetryPolicy retryPolicy) {
            SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> singleFieldBuilderV3 = this.retryPolicyBuilder_;
            if (singleFieldBuilderV3 == null) {
                RetryPolicy retryPolicy2 = this.retryPolicy_;
                if (retryPolicy2 != null) {
                    this.retryPolicy_ = RetryPolicy.newBuilder(retryPolicy2).mergeFrom(retryPolicy).m18607buildPartial();
                } else {
                    this.retryPolicy_ = retryPolicy;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(retryPolicy);
            }
            return this;
        }

        public Builder clearRetryPolicy() {
            if (this.retryPolicyBuilder_ == null) {
                this.retryPolicy_ = null;
                onChanged();
            } else {
                this.retryPolicy_ = null;
                this.retryPolicyBuilder_ = null;
            }
            return this;
        }

        public RetryPolicy.Builder getRetryPolicyBuilder() {
            onChanged();
            return getRetryPolicyFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public RetryPolicyOrBuilder getRetryPolicyOrBuilder() {
            SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> singleFieldBuilderV3 = this.retryPolicyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (RetryPolicyOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            RetryPolicy retryPolicy = this.retryPolicy_;
            return retryPolicy == null ? RetryPolicy.getDefaultInstance() : retryPolicy;
        }

        private SingleFieldBuilderV3<RetryPolicy, RetryPolicy.Builder, RetryPolicyOrBuilder> getRetryPolicyFieldBuilder() {
            if (this.retryPolicyBuilder_ == null) {
                this.retryPolicyBuilder_ = new SingleFieldBuilderV3<>(getRetryPolicy(), getParentForChildren(), isClean());
                this.retryPolicy_ = null;
            }
            return this.retryPolicyBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public Any getRetryPolicyTypedConfig() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.retryPolicyTypedConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Any any = this.retryPolicyTypedConfig_;
            return any == null ? Any.getDefaultInstance() : any;
        }

        public Builder setRetryPolicyTypedConfig(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.retryPolicyTypedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                any.getClass();
                this.retryPolicyTypedConfig_ = any;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(any);
            }
            return this;
        }

        public Builder setRetryPolicyTypedConfig(Any.Builder builder) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.retryPolicyTypedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.retryPolicyTypedConfig_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRetryPolicyTypedConfig(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.retryPolicyTypedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                Any any2 = this.retryPolicyTypedConfig_;
                if (any2 != null) {
                    this.retryPolicyTypedConfig_ = Any.newBuilder(any2).mergeFrom(any).buildPartial();
                } else {
                    this.retryPolicyTypedConfig_ = any;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(any);
            }
            return this;
        }

        public Builder clearRetryPolicyTypedConfig() {
            if (this.retryPolicyTypedConfigBuilder_ == null) {
                this.retryPolicyTypedConfig_ = null;
                onChanged();
            } else {
                this.retryPolicyTypedConfig_ = null;
                this.retryPolicyTypedConfigBuilder_ = null;
            }
            return this;
        }

        public Any.Builder getRetryPolicyTypedConfigBuilder() {
            onChanged();
            return getRetryPolicyTypedConfigFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public AnyOrBuilder getRetryPolicyTypedConfigOrBuilder() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.retryPolicyTypedConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Any any = this.retryPolicyTypedConfig_;
            return any == null ? Any.getDefaultInstance() : any;
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getRetryPolicyTypedConfigFieldBuilder() {
            if (this.retryPolicyTypedConfigBuilder_ == null) {
                this.retryPolicyTypedConfigBuilder_ = new SingleFieldBuilderV3<>(getRetryPolicyTypedConfig(), getParentForChildren(), isClean());
                this.retryPolicyTypedConfig_ = null;
            }
            return this.retryPolicyTypedConfigBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public HedgePolicy getHedgePolicy() {
            SingleFieldBuilderV3<HedgePolicy, HedgePolicy.Builder, HedgePolicyOrBuilder> singleFieldBuilderV3 = this.hedgePolicyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            HedgePolicy hedgePolicy = this.hedgePolicy_;
            return hedgePolicy == null ? HedgePolicy.getDefaultInstance() : hedgePolicy;
        }

        public Builder setHedgePolicy(HedgePolicy hedgePolicy) {
            SingleFieldBuilderV3<HedgePolicy, HedgePolicy.Builder, HedgePolicyOrBuilder> singleFieldBuilderV3 = this.hedgePolicyBuilder_;
            if (singleFieldBuilderV3 == null) {
                hedgePolicy.getClass();
                this.hedgePolicy_ = hedgePolicy;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(hedgePolicy);
            }
            return this;
        }

        public Builder setHedgePolicy(HedgePolicy.Builder builder) {
            SingleFieldBuilderV3<HedgePolicy, HedgePolicy.Builder, HedgePolicyOrBuilder> singleFieldBuilderV3 = this.hedgePolicyBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.hedgePolicy_ = builder.m18098build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m18098build());
            }
            return this;
        }

        public Builder mergeHedgePolicy(HedgePolicy hedgePolicy) {
            SingleFieldBuilderV3<HedgePolicy, HedgePolicy.Builder, HedgePolicyOrBuilder> singleFieldBuilderV3 = this.hedgePolicyBuilder_;
            if (singleFieldBuilderV3 == null) {
                HedgePolicy hedgePolicy2 = this.hedgePolicy_;
                if (hedgePolicy2 != null) {
                    this.hedgePolicy_ = HedgePolicy.newBuilder(hedgePolicy2).mergeFrom(hedgePolicy).m18100buildPartial();
                } else {
                    this.hedgePolicy_ = hedgePolicy;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(hedgePolicy);
            }
            return this;
        }

        public Builder clearHedgePolicy() {
            if (this.hedgePolicyBuilder_ == null) {
                this.hedgePolicy_ = null;
                onChanged();
            } else {
                this.hedgePolicy_ = null;
                this.hedgePolicyBuilder_ = null;
            }
            return this;
        }

        public HedgePolicy.Builder getHedgePolicyBuilder() {
            onChanged();
            return getHedgePolicyFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public HedgePolicyOrBuilder getHedgePolicyOrBuilder() {
            SingleFieldBuilderV3<HedgePolicy, HedgePolicy.Builder, HedgePolicyOrBuilder> singleFieldBuilderV3 = this.hedgePolicyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (HedgePolicyOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            HedgePolicy hedgePolicy = this.hedgePolicy_;
            return hedgePolicy == null ? HedgePolicy.getDefaultInstance() : hedgePolicy;
        }

        private SingleFieldBuilderV3<HedgePolicy, HedgePolicy.Builder, HedgePolicyOrBuilder> getHedgePolicyFieldBuilder() {
            if (this.hedgePolicyBuilder_ == null) {
                this.hedgePolicyBuilder_ = new SingleFieldBuilderV3<>(getHedgePolicy(), getParentForChildren(), isClean());
                this.hedgePolicy_ = null;
            }
            return this.hedgePolicyBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public UInt32Value getPerRequestBufferLimitBytes() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.perRequestBufferLimitBytesBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.perRequestBufferLimitBytes_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setPerRequestBufferLimitBytes(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.perRequestBufferLimitBytesBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.perRequestBufferLimitBytes_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setPerRequestBufferLimitBytes(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.perRequestBufferLimitBytesBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.perRequestBufferLimitBytes_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergePerRequestBufferLimitBytes(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.perRequestBufferLimitBytesBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.perRequestBufferLimitBytes_;
                if (uInt32Value2 != null) {
                    this.perRequestBufferLimitBytes_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.perRequestBufferLimitBytes_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearPerRequestBufferLimitBytes() {
            if (this.perRequestBufferLimitBytesBuilder_ == null) {
                this.perRequestBufferLimitBytes_ = null;
                onChanged();
            } else {
                this.perRequestBufferLimitBytes_ = null;
                this.perRequestBufferLimitBytesBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getPerRequestBufferLimitBytesBuilder() {
            onChanged();
            return getPerRequestBufferLimitBytesFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHostOrBuilder
        public UInt32ValueOrBuilder getPerRequestBufferLimitBytesOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.perRequestBufferLimitBytesBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.perRequestBufferLimitBytes_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getPerRequestBufferLimitBytesFieldBuilder() {
            if (this.perRequestBufferLimitBytesBuilder_ == null) {
                this.perRequestBufferLimitBytesBuilder_ = new SingleFieldBuilderV3<>(getPerRequestBufferLimitBytes(), getParentForChildren(), isClean());
                this.perRequestBufferLimitBytes_ = null;
            }
            return this.perRequestBufferLimitBytesBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m19520setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m19514mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
