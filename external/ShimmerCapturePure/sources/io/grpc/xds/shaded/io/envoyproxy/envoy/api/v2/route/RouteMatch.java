package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
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
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeFractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeFractionalPercentOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.RegexMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.RegexMatcherOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class RouteMatch extends GeneratedMessageV3 implements RouteMatchOrBuilder {
    public static final int CASE_SENSITIVE_FIELD_NUMBER = 4;
    public static final int GRPC_FIELD_NUMBER = 8;
    public static final int HEADERS_FIELD_NUMBER = 6;
    public static final int PATH_FIELD_NUMBER = 2;
    public static final int PREFIX_FIELD_NUMBER = 1;
    public static final int QUERY_PARAMETERS_FIELD_NUMBER = 7;
    public static final int REGEX_FIELD_NUMBER = 3;
    public static final int RUNTIME_FRACTION_FIELD_NUMBER = 9;
    public static final int SAFE_REGEX_FIELD_NUMBER = 10;
    public static final int TLS_CONTEXT_FIELD_NUMBER = 11;
    private static final long serialVersionUID = 0;
    private static final RouteMatch DEFAULT_INSTANCE = new RouteMatch();
    private static final Parser<RouteMatch> PARSER = new AbstractParser<RouteMatch>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public RouteMatch m19250parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RouteMatch(codedInputStream, extensionRegistryLite);
        }
    };
    private BoolValue caseSensitive_;
    private GrpcRouteMatchOptions grpc_;
    private List<HeaderMatcher> headers_;
    private byte memoizedIsInitialized;
    private int pathSpecifierCase_;
    private Object pathSpecifier_;
    private List<QueryParameterMatcher> queryParameters_;
    private RuntimeFractionalPercent runtimeFraction_;
    private TlsContextMatchOptions tlsContext_;

    private RouteMatch(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.pathSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private RouteMatch() {
        this.pathSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.headers_ = Collections.emptyList();
        this.queryParameters_ = Collections.emptyList();
    }

    private RouteMatch(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        BoolValue.Builder builder;
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0:
                            z = true;
                        case 10:
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            this.pathSpecifierCase_ = 1;
                            this.pathSpecifier_ = stringRequireUtf8;
                        case 18:
                            String stringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            this.pathSpecifierCase_ = 2;
                            this.pathSpecifier_ = stringRequireUtf82;
                        case 26:
                            String stringRequireUtf83 = codedInputStream.readStringRequireUtf8();
                            this.pathSpecifierCase_ = 3;
                            this.pathSpecifier_ = stringRequireUtf83;
                        case 34:
                            BoolValue boolValue = this.caseSensitive_;
                            builder = boolValue != null ? boolValue.toBuilder() : null;
                            BoolValue message = codedInputStream.readMessage(BoolValue.parser(), extensionRegistryLite);
                            this.caseSensitive_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.caseSensitive_ = builder.buildPartial();
                            }
                        case 50:
                            if ((i & 1) == 0) {
                                this.headers_ = new ArrayList();
                                i |= 1;
                            }
                            this.headers_.add(codedInputStream.readMessage(HeaderMatcher.parser(), extensionRegistryLite));
                        case 58:
                            if ((i & 2) == 0) {
                                this.queryParameters_ = new ArrayList();
                                i |= 2;
                            }
                            this.queryParameters_.add(codedInputStream.readMessage(QueryParameterMatcher.parser(), extensionRegistryLite));
                        case 66:
                            GrpcRouteMatchOptions grpcRouteMatchOptions = this.grpc_;
                            builder = grpcRouteMatchOptions != null ? grpcRouteMatchOptions.m19294toBuilder() : null;
                            GrpcRouteMatchOptions grpcRouteMatchOptions2 = (GrpcRouteMatchOptions) codedInputStream.readMessage(GrpcRouteMatchOptions.parser(), extensionRegistryLite);
                            this.grpc_ = grpcRouteMatchOptions2;
                            if (builder != null) {
                                builder.mergeFrom(grpcRouteMatchOptions2);
                                this.grpc_ = builder.m19301buildPartial();
                            }
                        case 74:
                            RuntimeFractionalPercent runtimeFractionalPercent = this.runtimeFraction_;
                            builder = runtimeFractionalPercent != null ? runtimeFractionalPercent.m16659toBuilder() : null;
                            RuntimeFractionalPercent runtimeFractionalPercent2 = (RuntimeFractionalPercent) codedInputStream.readMessage(RuntimeFractionalPercent.parser(), extensionRegistryLite);
                            this.runtimeFraction_ = runtimeFractionalPercent2;
                            if (builder != null) {
                                builder.mergeFrom(runtimeFractionalPercent2);
                                this.runtimeFraction_ = builder.m16666buildPartial();
                            }
                        case 82:
                            builder = this.pathSpecifierCase_ == 10 ? ((RegexMatcher) this.pathSpecifier_).m33412toBuilder() : null;
                            MessageLite message2 = codedInputStream.readMessage(RegexMatcher.parser(), extensionRegistryLite);
                            this.pathSpecifier_ = message2;
                            if (builder != null) {
                                builder.mergeFrom((RegexMatcher) message2);
                                this.pathSpecifier_ = builder.m33419buildPartial();
                            }
                            this.pathSpecifierCase_ = 10;
                        case RESET_TO_DEFAULT_CONFIGURATION_COMMAND_VALUE:
                            TlsContextMatchOptions tlsContextMatchOptions = this.tlsContext_;
                            builder = tlsContextMatchOptions != null ? tlsContextMatchOptions.m19340toBuilder() : null;
                            TlsContextMatchOptions tlsContextMatchOptions2 = (TlsContextMatchOptions) codedInputStream.readMessage(TlsContextMatchOptions.parser(), extensionRegistryLite);
                            this.tlsContext_ = tlsContextMatchOptions2;
                            if (builder != null) {
                                builder.mergeFrom(tlsContextMatchOptions2);
                                this.tlsContext_ = builder.m19347buildPartial();
                            }
                        default:
                            if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                z = true;
                            }
                    }
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if ((i & 1) != 0) {
                    this.headers_ = Collections.unmodifiableList(this.headers_);
                }
                if ((i & 2) != 0) {
                    this.queryParameters_ = Collections.unmodifiableList(this.queryParameters_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static RouteMatch getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RouteMatch> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_descriptor;
    }

    public static RouteMatch parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RouteMatch) PARSER.parseFrom(byteBuffer);
    }

    public static RouteMatch parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RouteMatch) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RouteMatch parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RouteMatch) PARSER.parseFrom(byteString);
    }

    public static RouteMatch parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RouteMatch) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RouteMatch parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RouteMatch) PARSER.parseFrom(bArr);
    }

    public static RouteMatch parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RouteMatch) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RouteMatch parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RouteMatch parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RouteMatch parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RouteMatch parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RouteMatch parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RouteMatch parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m19248toBuilder();
    }

    public static Builder newBuilder(RouteMatch routeMatch) {
        return DEFAULT_INSTANCE.m19248toBuilder().mergeFrom(routeMatch);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public RouteMatch m19243getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public List<HeaderMatcher> getHeadersList() {
        return this.headers_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public List<? extends HeaderMatcherOrBuilder> getHeadersOrBuilderList() {
        return this.headers_;
    }

    public Parser<RouteMatch> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public List<QueryParameterMatcher> getQueryParametersList() {
        return this.queryParameters_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public List<? extends QueryParameterMatcherOrBuilder> getQueryParametersOrBuilderList() {
        return this.queryParameters_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public boolean hasCaseSensitive() {
        return this.caseSensitive_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public boolean hasGrpc() {
        return this.grpc_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public boolean hasRuntimeFraction() {
        return this.runtimeFraction_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public boolean hasSafeRegex() {
        return this.pathSpecifierCase_ == 10;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public boolean hasTlsContext() {
        return this.tlsContext_ != null;
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
        return new RouteMatch();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_fieldAccessorTable.ensureFieldAccessorsInitialized(RouteMatch.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public PathSpecifierCase getPathSpecifierCase() {
        return PathSpecifierCase.forNumber(this.pathSpecifierCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public String getPrefix() {
        String str = this.pathSpecifierCase_ == 1 ? this.pathSpecifier_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.pathSpecifierCase_ == 1) {
            this.pathSpecifier_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public ByteString getPrefixBytes() {
        String str = this.pathSpecifierCase_ == 1 ? this.pathSpecifier_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.pathSpecifierCase_ == 1) {
                this.pathSpecifier_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public String getPath() {
        String str = this.pathSpecifierCase_ == 2 ? this.pathSpecifier_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.pathSpecifierCase_ == 2) {
            this.pathSpecifier_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public ByteString getPathBytes() {
        String str = this.pathSpecifierCase_ == 2 ? this.pathSpecifier_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.pathSpecifierCase_ == 2) {
                this.pathSpecifier_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    @Deprecated
    public String getRegex() {
        String str = this.pathSpecifierCase_ == 3 ? this.pathSpecifier_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.pathSpecifierCase_ == 3) {
            this.pathSpecifier_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    @Deprecated
    public ByteString getRegexBytes() {
        String str = this.pathSpecifierCase_ == 3 ? this.pathSpecifier_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.pathSpecifierCase_ == 3) {
                this.pathSpecifier_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public RegexMatcher getSafeRegex() {
        if (this.pathSpecifierCase_ == 10) {
            return (RegexMatcher) this.pathSpecifier_;
        }
        return RegexMatcher.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public RegexMatcherOrBuilder getSafeRegexOrBuilder() {
        if (this.pathSpecifierCase_ == 10) {
            return (RegexMatcher) this.pathSpecifier_;
        }
        return RegexMatcher.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public BoolValue getCaseSensitive() {
        BoolValue boolValue = this.caseSensitive_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public BoolValueOrBuilder getCaseSensitiveOrBuilder() {
        return getCaseSensitive();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public RuntimeFractionalPercent getRuntimeFraction() {
        RuntimeFractionalPercent runtimeFractionalPercent = this.runtimeFraction_;
        return runtimeFractionalPercent == null ? RuntimeFractionalPercent.getDefaultInstance() : runtimeFractionalPercent;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public RuntimeFractionalPercentOrBuilder getRuntimeFractionOrBuilder() {
        return getRuntimeFraction();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public int getHeadersCount() {
        return this.headers_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public HeaderMatcher getHeaders(int i) {
        return this.headers_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public HeaderMatcherOrBuilder getHeadersOrBuilder(int i) {
        return this.headers_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public int getQueryParametersCount() {
        return this.queryParameters_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public QueryParameterMatcher getQueryParameters(int i) {
        return this.queryParameters_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public QueryParameterMatcherOrBuilder getQueryParametersOrBuilder(int i) {
        return this.queryParameters_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public GrpcRouteMatchOptions getGrpc() {
        GrpcRouteMatchOptions grpcRouteMatchOptions = this.grpc_;
        return grpcRouteMatchOptions == null ? GrpcRouteMatchOptions.getDefaultInstance() : grpcRouteMatchOptions;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public GrpcRouteMatchOptionsOrBuilder getGrpcOrBuilder() {
        return getGrpc();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public TlsContextMatchOptions getTlsContext() {
        TlsContextMatchOptions tlsContextMatchOptions = this.tlsContext_;
        return tlsContextMatchOptions == null ? TlsContextMatchOptions.getDefaultInstance() : tlsContextMatchOptions;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
    public TlsContextMatchOptionsOrBuilder getTlsContextOrBuilder() {
        return getTlsContext();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.pathSpecifierCase_ == 1) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.pathSpecifier_);
        }
        if (this.pathSpecifierCase_ == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.pathSpecifier_);
        }
        if (this.pathSpecifierCase_ == 3) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.pathSpecifier_);
        }
        if (this.caseSensitive_ != null) {
            codedOutputStream.writeMessage(4, getCaseSensitive());
        }
        for (int i = 0; i < this.headers_.size(); i++) {
            codedOutputStream.writeMessage(6, this.headers_.get(i));
        }
        for (int i2 = 0; i2 < this.queryParameters_.size(); i2++) {
            codedOutputStream.writeMessage(7, this.queryParameters_.get(i2));
        }
        if (this.grpc_ != null) {
            codedOutputStream.writeMessage(8, getGrpc());
        }
        if (this.runtimeFraction_ != null) {
            codedOutputStream.writeMessage(9, getRuntimeFraction());
        }
        if (this.pathSpecifierCase_ == 10) {
            codedOutputStream.writeMessage(10, (RegexMatcher) this.pathSpecifier_);
        }
        if (this.tlsContext_ != null) {
            codedOutputStream.writeMessage(11, getTlsContext());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = this.pathSpecifierCase_ == 1 ? GeneratedMessageV3.computeStringSize(1, this.pathSpecifier_) : 0;
        if (this.pathSpecifierCase_ == 2) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.pathSpecifier_);
        }
        if (this.pathSpecifierCase_ == 3) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.pathSpecifier_);
        }
        if (this.caseSensitive_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(4, getCaseSensitive());
        }
        for (int i2 = 0; i2 < this.headers_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(6, this.headers_.get(i2));
        }
        for (int i3 = 0; i3 < this.queryParameters_.size(); i3++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(7, this.queryParameters_.get(i3));
        }
        if (this.grpc_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(8, getGrpc());
        }
        if (this.runtimeFraction_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(9, getRuntimeFraction());
        }
        if (this.pathSpecifierCase_ == 10) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(10, (RegexMatcher) this.pathSpecifier_);
        }
        if (this.tlsContext_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(11, getTlsContext());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RouteMatch)) {
            return super.equals(obj);
        }
        RouteMatch routeMatch = (RouteMatch) obj;
        if (hasCaseSensitive() != routeMatch.hasCaseSensitive()) {
            return false;
        }
        if ((hasCaseSensitive() && !getCaseSensitive().equals(routeMatch.getCaseSensitive())) || hasRuntimeFraction() != routeMatch.hasRuntimeFraction()) {
            return false;
        }
        if ((hasRuntimeFraction() && !getRuntimeFraction().equals(routeMatch.getRuntimeFraction())) || !getHeadersList().equals(routeMatch.getHeadersList()) || !getQueryParametersList().equals(routeMatch.getQueryParametersList()) || hasGrpc() != routeMatch.hasGrpc()) {
            return false;
        }
        if ((hasGrpc() && !getGrpc().equals(routeMatch.getGrpc())) || hasTlsContext() != routeMatch.hasTlsContext()) {
            return false;
        }
        if ((hasTlsContext() && !getTlsContext().equals(routeMatch.getTlsContext())) || !getPathSpecifierCase().equals(routeMatch.getPathSpecifierCase())) {
            return false;
        }
        int i = this.pathSpecifierCase_;
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    if (!getRegex().equals(routeMatch.getRegex())) {
                        return false;
                    }
                } else if (i == 10 && !getSafeRegex().equals(routeMatch.getSafeRegex())) {
                    return false;
                }
            } else if (!getPath().equals(routeMatch.getPath())) {
                return false;
            }
        } else if (!getPrefix().equals(routeMatch.getPrefix())) {
            return false;
        }
        return this.unknownFields.equals(routeMatch.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        if (hasCaseSensitive()) {
            iHashCode2 = (((iHashCode2 * 37) + 4) * 53) + getCaseSensitive().hashCode();
        }
        if (hasRuntimeFraction()) {
            iHashCode2 = (((iHashCode2 * 37) + 9) * 53) + getRuntimeFraction().hashCode();
        }
        if (getHeadersCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 6) * 53) + getHeadersList().hashCode();
        }
        if (getQueryParametersCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 7) * 53) + getQueryParametersList().hashCode();
        }
        if (hasGrpc()) {
            iHashCode2 = (((iHashCode2 * 37) + 8) * 53) + getGrpc().hashCode();
        }
        if (hasTlsContext()) {
            iHashCode2 = (((iHashCode2 * 37) + 11) * 53) + getTlsContext().hashCode();
        }
        int i2 = this.pathSpecifierCase_;
        if (i2 == 1) {
            i = ((iHashCode2 * 37) + 1) * 53;
            iHashCode = getPrefix().hashCode();
        } else if (i2 == 2) {
            i = ((iHashCode2 * 37) + 2) * 53;
            iHashCode = getPath().hashCode();
        } else if (i2 == 3) {
            i = ((iHashCode2 * 37) + 3) * 53;
            iHashCode = getRegex().hashCode();
        } else {
            if (i2 == 10) {
                i = ((iHashCode2 * 37) + 10) * 53;
                iHashCode = getSafeRegex().hashCode();
            }
            int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode3;
            return iHashCode3;
        }
        iHashCode2 = i + iHashCode;
        int iHashCode32 = (iHashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode32;
        return iHashCode32;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m19245newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m19248toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum PathSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        PREFIX(1),
        PATH(2),
        REGEX(3),
        SAFE_REGEX(10),
        PATHSPECIFIER_NOT_SET(0);

        private final int value;

        PathSpecifierCase(int i) {
            this.value = i;
        }

        public static PathSpecifierCase forNumber(int i) {
            if (i == 0) {
                return PATHSPECIFIER_NOT_SET;
            }
            if (i == 1) {
                return PREFIX;
            }
            if (i == 2) {
                return PATH;
            }
            if (i == 3) {
                return REGEX;
            }
            if (i != 10) {
                return null;
            }
            return SAFE_REGEX;
        }

        @Deprecated
        public static PathSpecifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public interface GrpcRouteMatchOptionsOrBuilder extends MessageOrBuilder {
    }

    public interface TlsContextMatchOptionsOrBuilder extends MessageOrBuilder {
        BoolValue getPresented();

        BoolValueOrBuilder getPresentedOrBuilder();

        BoolValue getValidated();

        BoolValueOrBuilder getValidatedOrBuilder();

        boolean hasPresented();

        boolean hasValidated();
    }

    public static final class GrpcRouteMatchOptions extends GeneratedMessageV3 implements GrpcRouteMatchOptionsOrBuilder {
        private static final GrpcRouteMatchOptions DEFAULT_INSTANCE = new GrpcRouteMatchOptions();
        private static final Parser<GrpcRouteMatchOptions> PARSER = new AbstractParser<GrpcRouteMatchOptions>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.GrpcRouteMatchOptions.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public GrpcRouteMatchOptions m19296parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GrpcRouteMatchOptions(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private GrpcRouteMatchOptions(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private GrpcRouteMatchOptions() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private GrpcRouteMatchOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag == 0 || !parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                z = true;
                            }
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

        public static GrpcRouteMatchOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<GrpcRouteMatchOptions> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_GrpcRouteMatchOptions_descriptor;
        }

        public static GrpcRouteMatchOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GrpcRouteMatchOptions) PARSER.parseFrom(byteBuffer);
        }

        public static GrpcRouteMatchOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GrpcRouteMatchOptions) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static GrpcRouteMatchOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GrpcRouteMatchOptions) PARSER.parseFrom(byteString);
        }

        public static GrpcRouteMatchOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GrpcRouteMatchOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static GrpcRouteMatchOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GrpcRouteMatchOptions) PARSER.parseFrom(bArr);
        }

        public static GrpcRouteMatchOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GrpcRouteMatchOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static GrpcRouteMatchOptions parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static GrpcRouteMatchOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GrpcRouteMatchOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static GrpcRouteMatchOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GrpcRouteMatchOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static GrpcRouteMatchOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m19294toBuilder();
        }

        public static Builder newBuilder(GrpcRouteMatchOptions grpcRouteMatchOptions) {
            return DEFAULT_INSTANCE.m19294toBuilder().mergeFrom(grpcRouteMatchOptions);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GrpcRouteMatchOptions m19289getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<GrpcRouteMatchOptions> getParserForType() {
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
            return new GrpcRouteMatchOptions();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_GrpcRouteMatchOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(GrpcRouteMatchOptions.class, Builder.class);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof GrpcRouteMatchOptions) {
                return this.unknownFields.equals(((GrpcRouteMatchOptions) obj).unknownFields);
            }
            return super.equals(obj);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19291newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19294toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GrpcRouteMatchOptionsOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_GrpcRouteMatchOptions_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_GrpcRouteMatchOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(GrpcRouteMatchOptions.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GrpcRouteMatchOptions.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19305clear() {
                super.clear();
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_GrpcRouteMatchOptions_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public GrpcRouteMatchOptions m19318getDefaultInstanceForType() {
                return GrpcRouteMatchOptions.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public GrpcRouteMatchOptions m19299build() throws UninitializedMessageException {
                GrpcRouteMatchOptions grpcRouteMatchOptionsM19301buildPartial = m19301buildPartial();
                if (grpcRouteMatchOptionsM19301buildPartial.isInitialized()) {
                    return grpcRouteMatchOptionsM19301buildPartial;
                }
                throw newUninitializedMessageException(grpcRouteMatchOptionsM19301buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public GrpcRouteMatchOptions m19301buildPartial() {
                GrpcRouteMatchOptions grpcRouteMatchOptions = new GrpcRouteMatchOptions(this);
                onBuilt();
                return grpcRouteMatchOptions;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19317clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19329setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19307clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19310clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19331setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19297addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19322mergeFrom(Message message) {
                if (message instanceof GrpcRouteMatchOptions) {
                    return mergeFrom((GrpcRouteMatchOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(GrpcRouteMatchOptions grpcRouteMatchOptions) {
                if (grpcRouteMatchOptions == GrpcRouteMatchOptions.getDefaultInstance()) {
                    return this;
                }
                m19327mergeUnknownFields(grpcRouteMatchOptions.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.GrpcRouteMatchOptions.Builder m19323mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.GrpcRouteMatchOptions.access$500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch$GrpcRouteMatchOptions r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.GrpcRouteMatchOptions) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch$GrpcRouteMatchOptions r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.GrpcRouteMatchOptions) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.GrpcRouteMatchOptions.Builder.m19323mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch$GrpcRouteMatchOptions$Builder");
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m19333setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m19327mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class TlsContextMatchOptions extends GeneratedMessageV3 implements TlsContextMatchOptionsOrBuilder {
        public static final int PRESENTED_FIELD_NUMBER = 1;
        public static final int VALIDATED_FIELD_NUMBER = 2;
        private static final TlsContextMatchOptions DEFAULT_INSTANCE = new TlsContextMatchOptions();
        private static final Parser<TlsContextMatchOptions> PARSER = new AbstractParser<TlsContextMatchOptions>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptions.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public TlsContextMatchOptions m19342parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TlsContextMatchOptions(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private BoolValue presented_;
        private BoolValue validated_;

        private TlsContextMatchOptions(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private TlsContextMatchOptions() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private TlsContextMatchOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            BoolValue.Builder builder;
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
                                BoolValue boolValue = this.presented_;
                                builder = boolValue != null ? boolValue.toBuilder() : null;
                                BoolValue message = codedInputStream.readMessage(BoolValue.parser(), extensionRegistryLite);
                                this.presented_ = message;
                                if (builder != null) {
                                    builder.mergeFrom(message);
                                    this.presented_ = builder.buildPartial();
                                }
                            } else if (tag == 18) {
                                BoolValue boolValue2 = this.validated_;
                                builder = boolValue2 != null ? boolValue2.toBuilder() : null;
                                BoolValue message2 = codedInputStream.readMessage(BoolValue.parser(), extensionRegistryLite);
                                this.validated_ = message2;
                                if (builder != null) {
                                    builder.mergeFrom(message2);
                                    this.validated_ = builder.buildPartial();
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

        public static TlsContextMatchOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TlsContextMatchOptions> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_TlsContextMatchOptions_descriptor;
        }

        public static TlsContextMatchOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (TlsContextMatchOptions) PARSER.parseFrom(byteBuffer);
        }

        public static TlsContextMatchOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TlsContextMatchOptions) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static TlsContextMatchOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (TlsContextMatchOptions) PARSER.parseFrom(byteString);
        }

        public static TlsContextMatchOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TlsContextMatchOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static TlsContextMatchOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (TlsContextMatchOptions) PARSER.parseFrom(bArr);
        }

        public static TlsContextMatchOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TlsContextMatchOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static TlsContextMatchOptions parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static TlsContextMatchOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TlsContextMatchOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static TlsContextMatchOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TlsContextMatchOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static TlsContextMatchOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m19340toBuilder();
        }

        public static Builder newBuilder(TlsContextMatchOptions tlsContextMatchOptions) {
            return DEFAULT_INSTANCE.m19340toBuilder().mergeFrom(tlsContextMatchOptions);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TlsContextMatchOptions m19335getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<TlsContextMatchOptions> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptionsOrBuilder
        public boolean hasPresented() {
            return this.presented_ != null;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptionsOrBuilder
        public boolean hasValidated() {
            return this.validated_ != null;
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
            return new TlsContextMatchOptions();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_TlsContextMatchOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(TlsContextMatchOptions.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptionsOrBuilder
        public BoolValue getPresented() {
            BoolValue boolValue = this.presented_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptionsOrBuilder
        public BoolValueOrBuilder getPresentedOrBuilder() {
            return getPresented();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptionsOrBuilder
        public BoolValue getValidated() {
            BoolValue boolValue = this.validated_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptionsOrBuilder
        public BoolValueOrBuilder getValidatedOrBuilder() {
            return getValidated();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.presented_ != null) {
                codedOutputStream.writeMessage(1, getPresented());
            }
            if (this.validated_ != null) {
                codedOutputStream.writeMessage(2, getValidated());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = this.presented_ != null ? CodedOutputStream.computeMessageSize(1, getPresented()) : 0;
            if (this.validated_ != null) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getValidated());
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof TlsContextMatchOptions)) {
                return super.equals(obj);
            }
            TlsContextMatchOptions tlsContextMatchOptions = (TlsContextMatchOptions) obj;
            if (hasPresented() != tlsContextMatchOptions.hasPresented()) {
                return false;
            }
            if ((!hasPresented() || getPresented().equals(tlsContextMatchOptions.getPresented())) && hasValidated() == tlsContextMatchOptions.hasValidated()) {
                return (!hasValidated() || getValidated().equals(tlsContextMatchOptions.getValidated())) && this.unknownFields.equals(tlsContextMatchOptions.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasPresented()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getPresented().hashCode();
            }
            if (hasValidated()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getValidated().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19337newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19340toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TlsContextMatchOptionsOrBuilder {
            private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> presentedBuilder_;
            private BoolValue presented_;
            private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> validatedBuilder_;
            private BoolValue validated_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_TlsContextMatchOptions_descriptor;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptionsOrBuilder
            public boolean hasPresented() {
                return (this.presentedBuilder_ == null && this.presented_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptionsOrBuilder
            public boolean hasValidated() {
                return (this.validatedBuilder_ == null && this.validated_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_TlsContextMatchOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(TlsContextMatchOptions.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = TlsContextMatchOptions.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19351clear() {
                super.clear();
                if (this.presentedBuilder_ == null) {
                    this.presented_ = null;
                } else {
                    this.presented_ = null;
                    this.presentedBuilder_ = null;
                }
                if (this.validatedBuilder_ == null) {
                    this.validated_ = null;
                } else {
                    this.validated_ = null;
                    this.validatedBuilder_ = null;
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_TlsContextMatchOptions_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public TlsContextMatchOptions m19364getDefaultInstanceForType() {
                return TlsContextMatchOptions.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public TlsContextMatchOptions m19345build() throws UninitializedMessageException {
                TlsContextMatchOptions tlsContextMatchOptionsM19347buildPartial = m19347buildPartial();
                if (tlsContextMatchOptionsM19347buildPartial.isInitialized()) {
                    return tlsContextMatchOptionsM19347buildPartial;
                }
                throw newUninitializedMessageException(tlsContextMatchOptionsM19347buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public TlsContextMatchOptions m19347buildPartial() {
                TlsContextMatchOptions tlsContextMatchOptions = new TlsContextMatchOptions(this);
                SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.presentedBuilder_;
                if (singleFieldBuilderV3 == null) {
                    tlsContextMatchOptions.presented_ = this.presented_;
                } else {
                    tlsContextMatchOptions.presented_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV32 = this.validatedBuilder_;
                if (singleFieldBuilderV32 == null) {
                    tlsContextMatchOptions.validated_ = this.validated_;
                } else {
                    tlsContextMatchOptions.validated_ = singleFieldBuilderV32.build();
                }
                onBuilt();
                return tlsContextMatchOptions;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19363clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19375setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19353clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19356clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19377setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19343addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m19368mergeFrom(Message message) {
                if (message instanceof TlsContextMatchOptions) {
                    return mergeFrom((TlsContextMatchOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(TlsContextMatchOptions tlsContextMatchOptions) {
                if (tlsContextMatchOptions == TlsContextMatchOptions.getDefaultInstance()) {
                    return this;
                }
                if (tlsContextMatchOptions.hasPresented()) {
                    mergePresented(tlsContextMatchOptions.getPresented());
                }
                if (tlsContextMatchOptions.hasValidated()) {
                    mergeValidated(tlsContextMatchOptions.getValidated());
                }
                m19373mergeUnknownFields(tlsContextMatchOptions.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptions.Builder m19369mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptions.access$1400()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch$TlsContextMatchOptions r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptions) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch$TlsContextMatchOptions r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptions) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptions.Builder.m19369mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch$TlsContextMatchOptions$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptionsOrBuilder
            public BoolValue getPresented() {
                SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.presentedBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                BoolValue boolValue = this.presented_;
                return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
            }

            public Builder setPresented(BoolValue boolValue) {
                SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.presentedBuilder_;
                if (singleFieldBuilderV3 == null) {
                    boolValue.getClass();
                    this.presented_ = boolValue;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(boolValue);
                }
                return this;
            }

            public Builder setPresented(BoolValue.Builder builder) {
                SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.presentedBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.presented_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergePresented(BoolValue boolValue) {
                SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.presentedBuilder_;
                if (singleFieldBuilderV3 == null) {
                    BoolValue boolValue2 = this.presented_;
                    if (boolValue2 != null) {
                        this.presented_ = BoolValue.newBuilder(boolValue2).mergeFrom(boolValue).buildPartial();
                    } else {
                        this.presented_ = boolValue;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(boolValue);
                }
                return this;
            }

            public Builder clearPresented() {
                if (this.presentedBuilder_ == null) {
                    this.presented_ = null;
                    onChanged();
                } else {
                    this.presented_ = null;
                    this.presentedBuilder_ = null;
                }
                return this;
            }

            public BoolValue.Builder getPresentedBuilder() {
                onChanged();
                return getPresentedFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptionsOrBuilder
            public BoolValueOrBuilder getPresentedOrBuilder() {
                SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.presentedBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                BoolValue boolValue = this.presented_;
                return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
            }

            private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> getPresentedFieldBuilder() {
                if (this.presentedBuilder_ == null) {
                    this.presentedBuilder_ = new SingleFieldBuilderV3<>(getPresented(), getParentForChildren(), isClean());
                    this.presented_ = null;
                }
                return this.presentedBuilder_;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptionsOrBuilder
            public BoolValue getValidated() {
                SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.validatedBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                BoolValue boolValue = this.validated_;
                return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
            }

            public Builder setValidated(BoolValue boolValue) {
                SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.validatedBuilder_;
                if (singleFieldBuilderV3 == null) {
                    boolValue.getClass();
                    this.validated_ = boolValue;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(boolValue);
                }
                return this;
            }

            public Builder setValidated(BoolValue.Builder builder) {
                SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.validatedBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.validated_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeValidated(BoolValue boolValue) {
                SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.validatedBuilder_;
                if (singleFieldBuilderV3 == null) {
                    BoolValue boolValue2 = this.validated_;
                    if (boolValue2 != null) {
                        this.validated_ = BoolValue.newBuilder(boolValue2).mergeFrom(boolValue).buildPartial();
                    } else {
                        this.validated_ = boolValue;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(boolValue);
                }
                return this;
            }

            public Builder clearValidated() {
                if (this.validatedBuilder_ == null) {
                    this.validated_ = null;
                    onChanged();
                } else {
                    this.validated_ = null;
                    this.validatedBuilder_ = null;
                }
                return this;
            }

            public BoolValue.Builder getValidatedBuilder() {
                onChanged();
                return getValidatedFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.TlsContextMatchOptionsOrBuilder
            public BoolValueOrBuilder getValidatedOrBuilder() {
                SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.validatedBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                BoolValue boolValue = this.validated_;
                return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
            }

            private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> getValidatedFieldBuilder() {
                if (this.validatedBuilder_ == null) {
                    this.validatedBuilder_ = new SingleFieldBuilderV3<>(getValidated(), getParentForChildren(), isClean());
                    this.validated_ = null;
                }
                return this.validatedBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m19379setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m19373mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RouteMatchOrBuilder {
        private int bitField0_;
        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> caseSensitiveBuilder_;
        private BoolValue caseSensitive_;
        private SingleFieldBuilderV3<GrpcRouteMatchOptions, GrpcRouteMatchOptions.Builder, GrpcRouteMatchOptionsOrBuilder> grpcBuilder_;
        private GrpcRouteMatchOptions grpc_;
        private RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> headersBuilder_;
        private List<HeaderMatcher> headers_;
        private int pathSpecifierCase_;
        private Object pathSpecifier_;
        private RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> queryParametersBuilder_;
        private List<QueryParameterMatcher> queryParameters_;
        private SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> runtimeFractionBuilder_;
        private RuntimeFractionalPercent runtimeFraction_;
        private SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> safeRegexBuilder_;
        private SingleFieldBuilderV3<TlsContextMatchOptions, TlsContextMatchOptions.Builder, TlsContextMatchOptionsOrBuilder> tlsContextBuilder_;
        private TlsContextMatchOptions tlsContext_;

        private Builder() {
            this.pathSpecifierCase_ = 0;
            this.headers_ = Collections.emptyList();
            this.queryParameters_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.pathSpecifierCase_ = 0;
            this.headers_ = Collections.emptyList();
            this.queryParameters_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public boolean hasCaseSensitive() {
            return (this.caseSensitiveBuilder_ == null && this.caseSensitive_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public boolean hasGrpc() {
            return (this.grpcBuilder_ == null && this.grpc_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public boolean hasRuntimeFraction() {
            return (this.runtimeFractionBuilder_ == null && this.runtimeFraction_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public boolean hasSafeRegex() {
            return this.pathSpecifierCase_ == 10;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public boolean hasTlsContext() {
            return (this.tlsContextBuilder_ == null && this.tlsContext_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_fieldAccessorTable.ensureFieldAccessorsInitialized(RouteMatch.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (RouteMatch.alwaysUseFieldBuilders) {
                getHeadersFieldBuilder();
                getQueryParametersFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19259clear() {
            super.clear();
            if (this.caseSensitiveBuilder_ == null) {
                this.caseSensitive_ = null;
            } else {
                this.caseSensitive_ = null;
                this.caseSensitiveBuilder_ = null;
            }
            if (this.runtimeFractionBuilder_ == null) {
                this.runtimeFraction_ = null;
            } else {
                this.runtimeFraction_ = null;
                this.runtimeFractionBuilder_ = null;
            }
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.headers_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV32 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.queryParameters_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            if (this.grpcBuilder_ == null) {
                this.grpc_ = null;
            } else {
                this.grpc_ = null;
                this.grpcBuilder_ = null;
            }
            if (this.tlsContextBuilder_ == null) {
                this.tlsContext_ = null;
            } else {
                this.tlsContext_ = null;
                this.tlsContextBuilder_ = null;
            }
            this.pathSpecifierCase_ = 0;
            this.pathSpecifier_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_RouteMatch_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RouteMatch m19272getDefaultInstanceForType() {
            return RouteMatch.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RouteMatch m19253build() throws UninitializedMessageException {
            RouteMatch routeMatchM19255buildPartial = m19255buildPartial();
            if (routeMatchM19255buildPartial.isInitialized()) {
                return routeMatchM19255buildPartial;
            }
            throw newUninitializedMessageException(routeMatchM19255buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RouteMatch m19255buildPartial() {
            RouteMatch routeMatch = new RouteMatch(this);
            if (this.pathSpecifierCase_ == 1) {
                routeMatch.pathSpecifier_ = this.pathSpecifier_;
            }
            if (this.pathSpecifierCase_ == 2) {
                routeMatch.pathSpecifier_ = this.pathSpecifier_;
            }
            if (this.pathSpecifierCase_ == 3) {
                routeMatch.pathSpecifier_ = this.pathSpecifier_;
            }
            if (this.pathSpecifierCase_ == 10) {
                SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexBuilder_;
                if (singleFieldBuilderV3 == null) {
                    routeMatch.pathSpecifier_ = this.pathSpecifier_;
                } else {
                    routeMatch.pathSpecifier_ = singleFieldBuilderV3.build();
                }
            }
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV32 = this.caseSensitiveBuilder_;
            if (singleFieldBuilderV32 == null) {
                routeMatch.caseSensitive_ = this.caseSensitive_;
            } else {
                routeMatch.caseSensitive_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV33 = this.runtimeFractionBuilder_;
            if (singleFieldBuilderV33 == null) {
                routeMatch.runtimeFraction_ = this.runtimeFraction_;
            } else {
                routeMatch.runtimeFraction_ = singleFieldBuilderV33.build();
            }
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.headers_ = Collections.unmodifiableList(this.headers_);
                    this.bitField0_ &= -2;
                }
                routeMatch.headers_ = this.headers_;
            } else {
                routeMatch.headers_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV32 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 2) != 0) {
                    this.queryParameters_ = Collections.unmodifiableList(this.queryParameters_);
                    this.bitField0_ &= -3;
                }
                routeMatch.queryParameters_ = this.queryParameters_;
            } else {
                routeMatch.queryParameters_ = repeatedFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<GrpcRouteMatchOptions, GrpcRouteMatchOptions.Builder, GrpcRouteMatchOptionsOrBuilder> singleFieldBuilderV34 = this.grpcBuilder_;
            if (singleFieldBuilderV34 == null) {
                routeMatch.grpc_ = this.grpc_;
            } else {
                routeMatch.grpc_ = singleFieldBuilderV34.build();
            }
            SingleFieldBuilderV3<TlsContextMatchOptions, TlsContextMatchOptions.Builder, TlsContextMatchOptionsOrBuilder> singleFieldBuilderV35 = this.tlsContextBuilder_;
            if (singleFieldBuilderV35 == null) {
                routeMatch.tlsContext_ = this.tlsContext_;
            } else {
                routeMatch.tlsContext_ = singleFieldBuilderV35.build();
            }
            routeMatch.pathSpecifierCase_ = this.pathSpecifierCase_;
            onBuilt();
            return routeMatch;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19271clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19283setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19261clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19264clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19285setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19251addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19276mergeFrom(Message message) {
            if (message instanceof RouteMatch) {
                return mergeFrom((RouteMatch) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RouteMatch routeMatch) {
            if (routeMatch == RouteMatch.getDefaultInstance()) {
                return this;
            }
            if (routeMatch.hasCaseSensitive()) {
                mergeCaseSensitive(routeMatch.getCaseSensitive());
            }
            if (routeMatch.hasRuntimeFraction()) {
                mergeRuntimeFraction(routeMatch.getRuntimeFraction());
            }
            if (this.headersBuilder_ == null) {
                if (!routeMatch.headers_.isEmpty()) {
                    if (this.headers_.isEmpty()) {
                        this.headers_ = routeMatch.headers_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureHeadersIsMutable();
                        this.headers_.addAll(routeMatch.headers_);
                    }
                    onChanged();
                }
            } else if (!routeMatch.headers_.isEmpty()) {
                if (!this.headersBuilder_.isEmpty()) {
                    this.headersBuilder_.addAllMessages(routeMatch.headers_);
                } else {
                    this.headersBuilder_.dispose();
                    this.headersBuilder_ = null;
                    this.headers_ = routeMatch.headers_;
                    this.bitField0_ &= -2;
                    this.headersBuilder_ = RouteMatch.alwaysUseFieldBuilders ? getHeadersFieldBuilder() : null;
                }
            }
            if (this.queryParametersBuilder_ == null) {
                if (!routeMatch.queryParameters_.isEmpty()) {
                    if (this.queryParameters_.isEmpty()) {
                        this.queryParameters_ = routeMatch.queryParameters_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureQueryParametersIsMutable();
                        this.queryParameters_.addAll(routeMatch.queryParameters_);
                    }
                    onChanged();
                }
            } else if (!routeMatch.queryParameters_.isEmpty()) {
                if (!this.queryParametersBuilder_.isEmpty()) {
                    this.queryParametersBuilder_.addAllMessages(routeMatch.queryParameters_);
                } else {
                    this.queryParametersBuilder_.dispose();
                    this.queryParametersBuilder_ = null;
                    this.queryParameters_ = routeMatch.queryParameters_;
                    this.bitField0_ &= -3;
                    this.queryParametersBuilder_ = RouteMatch.alwaysUseFieldBuilders ? getQueryParametersFieldBuilder() : null;
                }
            }
            if (routeMatch.hasGrpc()) {
                mergeGrpc(routeMatch.getGrpc());
            }
            if (routeMatch.hasTlsContext()) {
                mergeTlsContext(routeMatch.getTlsContext());
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$api$v2$route$RouteMatch$PathSpecifierCase[routeMatch.getPathSpecifierCase().ordinal()];
            if (i == 1) {
                this.pathSpecifierCase_ = 1;
                this.pathSpecifier_ = routeMatch.pathSpecifier_;
                onChanged();
            } else if (i == 2) {
                this.pathSpecifierCase_ = 2;
                this.pathSpecifier_ = routeMatch.pathSpecifier_;
                onChanged();
            } else if (i == 3) {
                this.pathSpecifierCase_ = 3;
                this.pathSpecifier_ = routeMatch.pathSpecifier_;
                onChanged();
            } else if (i == 4) {
                mergeSafeRegex(routeMatch.getSafeRegex());
            }
            m19281mergeUnknownFields(routeMatch.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.Builder m19277mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.access$3100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch.Builder.m19277mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public PathSpecifierCase getPathSpecifierCase() {
            return PathSpecifierCase.forNumber(this.pathSpecifierCase_);
        }

        public Builder clearPathSpecifier() {
            this.pathSpecifierCase_ = 0;
            this.pathSpecifier_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public String getPrefix() {
            String str = this.pathSpecifierCase_ == 1 ? this.pathSpecifier_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.pathSpecifierCase_ == 1) {
                    this.pathSpecifier_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setPrefix(String str) {
            str.getClass();
            this.pathSpecifierCase_ = 1;
            this.pathSpecifier_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public ByteString getPrefixBytes() {
            String str = this.pathSpecifierCase_ == 1 ? this.pathSpecifier_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.pathSpecifierCase_ == 1) {
                    this.pathSpecifier_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setPrefixBytes(ByteString byteString) {
            byteString.getClass();
            RouteMatch.checkByteStringIsUtf8(byteString);
            this.pathSpecifierCase_ = 1;
            this.pathSpecifier_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearPrefix() {
            if (this.pathSpecifierCase_ == 1) {
                this.pathSpecifierCase_ = 0;
                this.pathSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public String getPath() {
            String str = this.pathSpecifierCase_ == 2 ? this.pathSpecifier_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.pathSpecifierCase_ == 2) {
                    this.pathSpecifier_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setPath(String str) {
            str.getClass();
            this.pathSpecifierCase_ = 2;
            this.pathSpecifier_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public ByteString getPathBytes() {
            String str = this.pathSpecifierCase_ == 2 ? this.pathSpecifier_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.pathSpecifierCase_ == 2) {
                    this.pathSpecifier_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setPathBytes(ByteString byteString) {
            byteString.getClass();
            RouteMatch.checkByteStringIsUtf8(byteString);
            this.pathSpecifierCase_ = 2;
            this.pathSpecifier_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearPath() {
            if (this.pathSpecifierCase_ == 2) {
                this.pathSpecifierCase_ = 0;
                this.pathSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        @Deprecated
        public String getRegex() {
            String str = this.pathSpecifierCase_ == 3 ? this.pathSpecifier_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.pathSpecifierCase_ == 3) {
                    this.pathSpecifier_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Deprecated
        public Builder setRegex(String str) {
            str.getClass();
            this.pathSpecifierCase_ = 3;
            this.pathSpecifier_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        @Deprecated
        public ByteString getRegexBytes() {
            String str = this.pathSpecifierCase_ == 3 ? this.pathSpecifier_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.pathSpecifierCase_ == 3) {
                    this.pathSpecifier_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        @Deprecated
        public Builder setRegexBytes(ByteString byteString) {
            byteString.getClass();
            RouteMatch.checkByteStringIsUtf8(byteString);
            this.pathSpecifierCase_ = 3;
            this.pathSpecifier_ = byteString;
            onChanged();
            return this;
        }

        @Deprecated
        public Builder clearRegex() {
            if (this.pathSpecifierCase_ == 3) {
                this.pathSpecifierCase_ = 0;
                this.pathSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public RegexMatcher getSafeRegex() {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.pathSpecifierCase_ == 10) {
                    return (RegexMatcher) this.pathSpecifier_;
                }
                return RegexMatcher.getDefaultInstance();
            }
            if (this.pathSpecifierCase_ == 10) {
                return singleFieldBuilderV3.getMessage();
            }
            return RegexMatcher.getDefaultInstance();
        }

        public Builder setSafeRegex(RegexMatcher regexMatcher) {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexBuilder_;
            if (singleFieldBuilderV3 == null) {
                regexMatcher.getClass();
                this.pathSpecifier_ = regexMatcher;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(regexMatcher);
            }
            this.pathSpecifierCase_ = 10;
            return this;
        }

        public Builder setSafeRegex(RegexMatcher.Builder builder) {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.pathSpecifier_ = builder.m33417build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m33417build());
            }
            this.pathSpecifierCase_ = 10;
            return this;
        }

        public Builder mergeSafeRegex(RegexMatcher regexMatcher) {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.pathSpecifierCase_ != 10 || this.pathSpecifier_ == RegexMatcher.getDefaultInstance()) {
                    this.pathSpecifier_ = regexMatcher;
                } else {
                    this.pathSpecifier_ = RegexMatcher.newBuilder((RegexMatcher) this.pathSpecifier_).mergeFrom(regexMatcher).m33419buildPartial();
                }
                onChanged();
            } else {
                if (this.pathSpecifierCase_ == 10) {
                    singleFieldBuilderV3.mergeFrom(regexMatcher);
                }
                this.safeRegexBuilder_.setMessage(regexMatcher);
            }
            this.pathSpecifierCase_ = 10;
            return this;
        }

        public Builder clearSafeRegex() {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.pathSpecifierCase_ == 10) {
                    this.pathSpecifierCase_ = 0;
                    this.pathSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.pathSpecifierCase_ == 10) {
                this.pathSpecifierCase_ = 0;
                this.pathSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public RegexMatcher.Builder getSafeRegexBuilder() {
            return getSafeRegexFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public RegexMatcherOrBuilder getSafeRegexOrBuilder() {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3;
            int i = this.pathSpecifierCase_;
            if (i == 10 && (singleFieldBuilderV3 = this.safeRegexBuilder_) != null) {
                return (RegexMatcherOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 10) {
                return (RegexMatcher) this.pathSpecifier_;
            }
            return RegexMatcher.getDefaultInstance();
        }

        private SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> getSafeRegexFieldBuilder() {
            if (this.safeRegexBuilder_ == null) {
                if (this.pathSpecifierCase_ != 10) {
                    this.pathSpecifier_ = RegexMatcher.getDefaultInstance();
                }
                this.safeRegexBuilder_ = new SingleFieldBuilderV3<>((RegexMatcher) this.pathSpecifier_, getParentForChildren(), isClean());
                this.pathSpecifier_ = null;
            }
            this.pathSpecifierCase_ = 10;
            onChanged();
            return this.safeRegexBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public BoolValue getCaseSensitive() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.caseSensitiveBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            BoolValue boolValue = this.caseSensitive_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        public Builder setCaseSensitive(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.caseSensitiveBuilder_;
            if (singleFieldBuilderV3 == null) {
                boolValue.getClass();
                this.caseSensitive_ = boolValue;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(boolValue);
            }
            return this;
        }

        public Builder setCaseSensitive(BoolValue.Builder builder) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.caseSensitiveBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.caseSensitive_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeCaseSensitive(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.caseSensitiveBuilder_;
            if (singleFieldBuilderV3 == null) {
                BoolValue boolValue2 = this.caseSensitive_;
                if (boolValue2 != null) {
                    this.caseSensitive_ = BoolValue.newBuilder(boolValue2).mergeFrom(boolValue).buildPartial();
                } else {
                    this.caseSensitive_ = boolValue;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(boolValue);
            }
            return this;
        }

        public Builder clearCaseSensitive() {
            if (this.caseSensitiveBuilder_ == null) {
                this.caseSensitive_ = null;
                onChanged();
            } else {
                this.caseSensitive_ = null;
                this.caseSensitiveBuilder_ = null;
            }
            return this;
        }

        public BoolValue.Builder getCaseSensitiveBuilder() {
            onChanged();
            return getCaseSensitiveFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public BoolValueOrBuilder getCaseSensitiveOrBuilder() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.caseSensitiveBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            BoolValue boolValue = this.caseSensitive_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> getCaseSensitiveFieldBuilder() {
            if (this.caseSensitiveBuilder_ == null) {
                this.caseSensitiveBuilder_ = new SingleFieldBuilderV3<>(getCaseSensitive(), getParentForChildren(), isClean());
                this.caseSensitive_ = null;
            }
            return this.caseSensitiveBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public RuntimeFractionalPercent getRuntimeFraction() {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.runtimeFractionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            RuntimeFractionalPercent runtimeFractionalPercent = this.runtimeFraction_;
            return runtimeFractionalPercent == null ? RuntimeFractionalPercent.getDefaultInstance() : runtimeFractionalPercent;
        }

        public Builder setRuntimeFraction(RuntimeFractionalPercent runtimeFractionalPercent) {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.runtimeFractionBuilder_;
            if (singleFieldBuilderV3 == null) {
                runtimeFractionalPercent.getClass();
                this.runtimeFraction_ = runtimeFractionalPercent;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(runtimeFractionalPercent);
            }
            return this;
        }

        public Builder setRuntimeFraction(RuntimeFractionalPercent.Builder builder) {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.runtimeFractionBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.runtimeFraction_ = builder.m16664build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m16664build());
            }
            return this;
        }

        public Builder mergeRuntimeFraction(RuntimeFractionalPercent runtimeFractionalPercent) {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.runtimeFractionBuilder_;
            if (singleFieldBuilderV3 == null) {
                RuntimeFractionalPercent runtimeFractionalPercent2 = this.runtimeFraction_;
                if (runtimeFractionalPercent2 != null) {
                    this.runtimeFraction_ = RuntimeFractionalPercent.newBuilder(runtimeFractionalPercent2).mergeFrom(runtimeFractionalPercent).m16666buildPartial();
                } else {
                    this.runtimeFraction_ = runtimeFractionalPercent;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(runtimeFractionalPercent);
            }
            return this;
        }

        public Builder clearRuntimeFraction() {
            if (this.runtimeFractionBuilder_ == null) {
                this.runtimeFraction_ = null;
                onChanged();
            } else {
                this.runtimeFraction_ = null;
                this.runtimeFractionBuilder_ = null;
            }
            return this;
        }

        public RuntimeFractionalPercent.Builder getRuntimeFractionBuilder() {
            onChanged();
            return getRuntimeFractionFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public RuntimeFractionalPercentOrBuilder getRuntimeFractionOrBuilder() {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.runtimeFractionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (RuntimeFractionalPercentOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            RuntimeFractionalPercent runtimeFractionalPercent = this.runtimeFraction_;
            return runtimeFractionalPercent == null ? RuntimeFractionalPercent.getDefaultInstance() : runtimeFractionalPercent;
        }

        private SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> getRuntimeFractionFieldBuilder() {
            if (this.runtimeFractionBuilder_ == null) {
                this.runtimeFractionBuilder_ = new SingleFieldBuilderV3<>(getRuntimeFraction(), getParentForChildren(), isClean());
                this.runtimeFraction_ = null;
            }
            return this.runtimeFractionBuilder_;
        }

        private void ensureHeadersIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.headers_ = new ArrayList(this.headers_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public List<HeaderMatcher> getHeadersList() {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.headers_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public int getHeadersCount() {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.headers_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public HeaderMatcher getHeaders(int i) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.headers_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setHeaders(int i, HeaderMatcher headerMatcher) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerMatcher.getClass();
                ensureHeadersIsMutable();
                this.headers_.set(i, headerMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, headerMatcher);
            }
            return this;
        }

        public Builder setHeaders(int i, HeaderMatcher.Builder builder) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureHeadersIsMutable();
                this.headers_.set(i, builder.m18052build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m18052build());
            }
            return this;
        }

        public Builder addHeaders(HeaderMatcher headerMatcher) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerMatcher.getClass();
                ensureHeadersIsMutable();
                this.headers_.add(headerMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(headerMatcher);
            }
            return this;
        }

        public Builder addHeaders(int i, HeaderMatcher headerMatcher) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerMatcher.getClass();
                ensureHeadersIsMutable();
                this.headers_.add(i, headerMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, headerMatcher);
            }
            return this;
        }

        public Builder addHeaders(HeaderMatcher.Builder builder) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureHeadersIsMutable();
                this.headers_.add(builder.m18052build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m18052build());
            }
            return this;
        }

        public Builder addHeaders(int i, HeaderMatcher.Builder builder) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureHeadersIsMutable();
                this.headers_.add(i, builder.m18052build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m18052build());
            }
            return this;
        }

        public Builder addAllHeaders(Iterable<? extends HeaderMatcher> iterable) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureHeadersIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.headers_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearHeaders() {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.headers_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeHeaders(int i) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureHeadersIsMutable();
                this.headers_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public HeaderMatcher.Builder getHeadersBuilder(int i) {
            return getHeadersFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public HeaderMatcherOrBuilder getHeadersOrBuilder(int i) {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.headers_.get(i);
            }
            return (HeaderMatcherOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public List<? extends HeaderMatcherOrBuilder> getHeadersOrBuilderList() {
            RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.headers_);
        }

        public HeaderMatcher.Builder addHeadersBuilder() {
            return getHeadersFieldBuilder().addBuilder(HeaderMatcher.getDefaultInstance());
        }

        public HeaderMatcher.Builder addHeadersBuilder(int i) {
            return getHeadersFieldBuilder().addBuilder(i, HeaderMatcher.getDefaultInstance());
        }

        public List<HeaderMatcher.Builder> getHeadersBuilderList() {
            return getHeadersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<HeaderMatcher, HeaderMatcher.Builder, HeaderMatcherOrBuilder> getHeadersFieldBuilder() {
            if (this.headersBuilder_ == null) {
                this.headersBuilder_ = new RepeatedFieldBuilderV3<>(this.headers_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.headers_ = null;
            }
            return this.headersBuilder_;
        }

        private void ensureQueryParametersIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.queryParameters_ = new ArrayList(this.queryParameters_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public List<QueryParameterMatcher> getQueryParametersList() {
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV3 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.queryParameters_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public int getQueryParametersCount() {
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV3 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.queryParameters_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public QueryParameterMatcher getQueryParameters(int i) {
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV3 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.queryParameters_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setQueryParameters(int i, QueryParameterMatcher queryParameterMatcher) {
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV3 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                queryParameterMatcher.getClass();
                ensureQueryParametersIsMutable();
                this.queryParameters_.set(i, queryParameterMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, queryParameterMatcher);
            }
            return this;
        }

        public Builder setQueryParameters(int i, QueryParameterMatcher.Builder builder) {
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV3 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureQueryParametersIsMutable();
                this.queryParameters_.set(i, builder.m18144build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m18144build());
            }
            return this;
        }

        public Builder addQueryParameters(QueryParameterMatcher queryParameterMatcher) {
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV3 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                queryParameterMatcher.getClass();
                ensureQueryParametersIsMutable();
                this.queryParameters_.add(queryParameterMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(queryParameterMatcher);
            }
            return this;
        }

        public Builder addQueryParameters(int i, QueryParameterMatcher queryParameterMatcher) {
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV3 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                queryParameterMatcher.getClass();
                ensureQueryParametersIsMutable();
                this.queryParameters_.add(i, queryParameterMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, queryParameterMatcher);
            }
            return this;
        }

        public Builder addQueryParameters(QueryParameterMatcher.Builder builder) {
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV3 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureQueryParametersIsMutable();
                this.queryParameters_.add(builder.m18144build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m18144build());
            }
            return this;
        }

        public Builder addQueryParameters(int i, QueryParameterMatcher.Builder builder) {
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV3 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureQueryParametersIsMutable();
                this.queryParameters_.add(i, builder.m18144build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m18144build());
            }
            return this;
        }

        public Builder addAllQueryParameters(Iterable<? extends QueryParameterMatcher> iterable) {
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV3 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureQueryParametersIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.queryParameters_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearQueryParameters() {
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV3 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.queryParameters_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeQueryParameters(int i) {
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV3 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureQueryParametersIsMutable();
                this.queryParameters_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public QueryParameterMatcher.Builder getQueryParametersBuilder(int i) {
            return getQueryParametersFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public QueryParameterMatcherOrBuilder getQueryParametersOrBuilder(int i) {
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV3 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.queryParameters_.get(i);
            }
            return (QueryParameterMatcherOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public List<? extends QueryParameterMatcherOrBuilder> getQueryParametersOrBuilderList() {
            RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> repeatedFieldBuilderV3 = this.queryParametersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.queryParameters_);
        }

        public QueryParameterMatcher.Builder addQueryParametersBuilder() {
            return getQueryParametersFieldBuilder().addBuilder(QueryParameterMatcher.getDefaultInstance());
        }

        public QueryParameterMatcher.Builder addQueryParametersBuilder(int i) {
            return getQueryParametersFieldBuilder().addBuilder(i, QueryParameterMatcher.getDefaultInstance());
        }

        public List<QueryParameterMatcher.Builder> getQueryParametersBuilderList() {
            return getQueryParametersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<QueryParameterMatcher, QueryParameterMatcher.Builder, QueryParameterMatcherOrBuilder> getQueryParametersFieldBuilder() {
            if (this.queryParametersBuilder_ == null) {
                this.queryParametersBuilder_ = new RepeatedFieldBuilderV3<>(this.queryParameters_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.queryParameters_ = null;
            }
            return this.queryParametersBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public GrpcRouteMatchOptions getGrpc() {
            SingleFieldBuilderV3<GrpcRouteMatchOptions, GrpcRouteMatchOptions.Builder, GrpcRouteMatchOptionsOrBuilder> singleFieldBuilderV3 = this.grpcBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            GrpcRouteMatchOptions grpcRouteMatchOptions = this.grpc_;
            return grpcRouteMatchOptions == null ? GrpcRouteMatchOptions.getDefaultInstance() : grpcRouteMatchOptions;
        }

        public Builder setGrpc(GrpcRouteMatchOptions grpcRouteMatchOptions) {
            SingleFieldBuilderV3<GrpcRouteMatchOptions, GrpcRouteMatchOptions.Builder, GrpcRouteMatchOptionsOrBuilder> singleFieldBuilderV3 = this.grpcBuilder_;
            if (singleFieldBuilderV3 == null) {
                grpcRouteMatchOptions.getClass();
                this.grpc_ = grpcRouteMatchOptions;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(grpcRouteMatchOptions);
            }
            return this;
        }

        public Builder setGrpc(GrpcRouteMatchOptions.Builder builder) {
            SingleFieldBuilderV3<GrpcRouteMatchOptions, GrpcRouteMatchOptions.Builder, GrpcRouteMatchOptionsOrBuilder> singleFieldBuilderV3 = this.grpcBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.grpc_ = builder.m19299build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m19299build());
            }
            return this;
        }

        public Builder mergeGrpc(GrpcRouteMatchOptions grpcRouteMatchOptions) {
            SingleFieldBuilderV3<GrpcRouteMatchOptions, GrpcRouteMatchOptions.Builder, GrpcRouteMatchOptionsOrBuilder> singleFieldBuilderV3 = this.grpcBuilder_;
            if (singleFieldBuilderV3 == null) {
                GrpcRouteMatchOptions grpcRouteMatchOptions2 = this.grpc_;
                if (grpcRouteMatchOptions2 != null) {
                    this.grpc_ = GrpcRouteMatchOptions.newBuilder(grpcRouteMatchOptions2).mergeFrom(grpcRouteMatchOptions).m19301buildPartial();
                } else {
                    this.grpc_ = grpcRouteMatchOptions;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(grpcRouteMatchOptions);
            }
            return this;
        }

        public Builder clearGrpc() {
            if (this.grpcBuilder_ == null) {
                this.grpc_ = null;
                onChanged();
            } else {
                this.grpc_ = null;
                this.grpcBuilder_ = null;
            }
            return this;
        }

        public GrpcRouteMatchOptions.Builder getGrpcBuilder() {
            onChanged();
            return getGrpcFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public GrpcRouteMatchOptionsOrBuilder getGrpcOrBuilder() {
            SingleFieldBuilderV3<GrpcRouteMatchOptions, GrpcRouteMatchOptions.Builder, GrpcRouteMatchOptionsOrBuilder> singleFieldBuilderV3 = this.grpcBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (GrpcRouteMatchOptionsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            GrpcRouteMatchOptions grpcRouteMatchOptions = this.grpc_;
            return grpcRouteMatchOptions == null ? GrpcRouteMatchOptions.getDefaultInstance() : grpcRouteMatchOptions;
        }

        private SingleFieldBuilderV3<GrpcRouteMatchOptions, GrpcRouteMatchOptions.Builder, GrpcRouteMatchOptionsOrBuilder> getGrpcFieldBuilder() {
            if (this.grpcBuilder_ == null) {
                this.grpcBuilder_ = new SingleFieldBuilderV3<>(getGrpc(), getParentForChildren(), isClean());
                this.grpc_ = null;
            }
            return this.grpcBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public TlsContextMatchOptions getTlsContext() {
            SingleFieldBuilderV3<TlsContextMatchOptions, TlsContextMatchOptions.Builder, TlsContextMatchOptionsOrBuilder> singleFieldBuilderV3 = this.tlsContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            TlsContextMatchOptions tlsContextMatchOptions = this.tlsContext_;
            return tlsContextMatchOptions == null ? TlsContextMatchOptions.getDefaultInstance() : tlsContextMatchOptions;
        }

        public Builder setTlsContext(TlsContextMatchOptions tlsContextMatchOptions) {
            SingleFieldBuilderV3<TlsContextMatchOptions, TlsContextMatchOptions.Builder, TlsContextMatchOptionsOrBuilder> singleFieldBuilderV3 = this.tlsContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                tlsContextMatchOptions.getClass();
                this.tlsContext_ = tlsContextMatchOptions;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(tlsContextMatchOptions);
            }
            return this;
        }

        public Builder setTlsContext(TlsContextMatchOptions.Builder builder) {
            SingleFieldBuilderV3<TlsContextMatchOptions, TlsContextMatchOptions.Builder, TlsContextMatchOptionsOrBuilder> singleFieldBuilderV3 = this.tlsContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.tlsContext_ = builder.m19345build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m19345build());
            }
            return this;
        }

        public Builder mergeTlsContext(TlsContextMatchOptions tlsContextMatchOptions) {
            SingleFieldBuilderV3<TlsContextMatchOptions, TlsContextMatchOptions.Builder, TlsContextMatchOptionsOrBuilder> singleFieldBuilderV3 = this.tlsContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                TlsContextMatchOptions tlsContextMatchOptions2 = this.tlsContext_;
                if (tlsContextMatchOptions2 != null) {
                    this.tlsContext_ = TlsContextMatchOptions.newBuilder(tlsContextMatchOptions2).mergeFrom(tlsContextMatchOptions).m19347buildPartial();
                } else {
                    this.tlsContext_ = tlsContextMatchOptions;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(tlsContextMatchOptions);
            }
            return this;
        }

        public Builder clearTlsContext() {
            if (this.tlsContextBuilder_ == null) {
                this.tlsContext_ = null;
                onChanged();
            } else {
                this.tlsContext_ = null;
                this.tlsContextBuilder_ = null;
            }
            return this;
        }

        public TlsContextMatchOptions.Builder getTlsContextBuilder() {
            onChanged();
            return getTlsContextFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatchOrBuilder
        public TlsContextMatchOptionsOrBuilder getTlsContextOrBuilder() {
            SingleFieldBuilderV3<TlsContextMatchOptions, TlsContextMatchOptions.Builder, TlsContextMatchOptionsOrBuilder> singleFieldBuilderV3 = this.tlsContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (TlsContextMatchOptionsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            TlsContextMatchOptions tlsContextMatchOptions = this.tlsContext_;
            return tlsContextMatchOptions == null ? TlsContextMatchOptions.getDefaultInstance() : tlsContextMatchOptions;
        }

        private SingleFieldBuilderV3<TlsContextMatchOptions, TlsContextMatchOptions.Builder, TlsContextMatchOptionsOrBuilder> getTlsContextFieldBuilder() {
            if (this.tlsContextBuilder_ == null) {
                this.tlsContextBuilder_ = new SingleFieldBuilderV3<>(getTlsContext(), getParentForChildren(), isClean());
                this.tlsContext_ = null;
            }
            return this.tlsContextBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m19287setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m19281mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteMatch$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$api$v2$route$RouteMatch$PathSpecifierCase;

        static {
            int[] iArr = new int[PathSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$api$v2$route$RouteMatch$PathSpecifierCase = iArr;
            try {
                iArr[PathSpecifierCase.PREFIX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$RouteMatch$PathSpecifierCase[PathSpecifierCase.PATH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$RouteMatch$PathSpecifierCase[PathSpecifierCase.REGEX.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$RouteMatch$PathSpecifierCase[PathSpecifierCase.SAFE_REGEX.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$RouteMatch$PathSpecifierCase[PathSpecifierCase.PATHSPECIFIER_NOT_SET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
