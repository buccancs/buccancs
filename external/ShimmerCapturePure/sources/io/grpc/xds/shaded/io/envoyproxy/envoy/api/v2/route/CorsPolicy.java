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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeFractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeFractionalPercentOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public final class CorsPolicy extends GeneratedMessageV3 implements CorsPolicyOrBuilder {
    public static final int ALLOW_CREDENTIALS_FIELD_NUMBER = 6;
    public static final int ALLOW_HEADERS_FIELD_NUMBER = 3;
    public static final int ALLOW_METHODS_FIELD_NUMBER = 2;
    public static final int ALLOW_ORIGIN_FIELD_NUMBER = 1;
    public static final int ALLOW_ORIGIN_REGEX_FIELD_NUMBER = 8;
    public static final int ALLOW_ORIGIN_STRING_MATCH_FIELD_NUMBER = 11;
    public static final int ENABLED_FIELD_NUMBER = 7;
    public static final int EXPOSE_HEADERS_FIELD_NUMBER = 4;
    public static final int FILTER_ENABLED_FIELD_NUMBER = 9;
    public static final int MAX_AGE_FIELD_NUMBER = 5;
    public static final int SHADOW_ENABLED_FIELD_NUMBER = 10;
    private static final long serialVersionUID = 0;
    private static final CorsPolicy DEFAULT_INSTANCE = new CorsPolicy();
    private static final Parser<CorsPolicy> PARSER = new AbstractParser<CorsPolicy>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicy.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public CorsPolicy m17865parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CorsPolicy(codedInputStream, extensionRegistryLite);
        }
    };
    private BoolValue allowCredentials_;
    private volatile Object allowHeaders_;
    private volatile Object allowMethods_;
    private LazyStringList allowOriginRegex_;
    private List<StringMatcher> allowOriginStringMatch_;
    private LazyStringList allowOrigin_;
    private int enabledSpecifierCase_;
    private Object enabledSpecifier_;
    private volatile Object exposeHeaders_;
    private volatile Object maxAge_;
    private byte memoizedIsInitialized;
    private RuntimeFractionalPercent shadowEnabled_;

    private CorsPolicy(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.enabledSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private CorsPolicy() {
        this.enabledSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.allowOrigin_ = LazyStringArrayList.EMPTY;
        this.allowOriginRegex_ = LazyStringArrayList.EMPTY;
        this.allowOriginStringMatch_ = Collections.emptyList();
        this.allowMethods_ = "";
        this.allowHeaders_ = "";
        this.exposeHeaders_ = "";
        this.maxAge_ = "";
    }

    private CorsPolicy(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        RuntimeFractionalPercent.Builder builder;
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
                            if ((i & 1) == 0) {
                                this.allowOrigin_ = new LazyStringArrayList();
                                i |= 1;
                            }
                            this.allowOrigin_.add(stringRequireUtf8);
                        case 18:
                            this.allowMethods_ = codedInputStream.readStringRequireUtf8();
                        case 26:
                            this.allowHeaders_ = codedInputStream.readStringRequireUtf8();
                        case 34:
                            this.exposeHeaders_ = codedInputStream.readStringRequireUtf8();
                        case 42:
                            this.maxAge_ = codedInputStream.readStringRequireUtf8();
                        case 50:
                            BoolValue boolValue = this.allowCredentials_;
                            builder = boolValue != null ? boolValue.toBuilder() : null;
                            BoolValue message = codedInputStream.readMessage(BoolValue.parser(), extensionRegistryLite);
                            this.allowCredentials_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.allowCredentials_ = builder.buildPartial();
                            }
                        case 58:
                            builder = this.enabledSpecifierCase_ == 7 ? ((BoolValue) this.enabledSpecifier_).toBuilder() : null;
                            BoolValue message2 = codedInputStream.readMessage(BoolValue.parser(), extensionRegistryLite);
                            this.enabledSpecifier_ = message2;
                            if (builder != null) {
                                builder.mergeFrom(message2);
                                this.enabledSpecifier_ = builder.buildPartial();
                            }
                            this.enabledSpecifierCase_ = 7;
                        case 66:
                            String stringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            if ((i & 2) == 0) {
                                this.allowOriginRegex_ = new LazyStringArrayList();
                                i |= 2;
                            }
                            this.allowOriginRegex_.add(stringRequireUtf82);
                        case 74:
                            builder = this.enabledSpecifierCase_ == 9 ? ((RuntimeFractionalPercent) this.enabledSpecifier_).m16659toBuilder() : null;
                            MessageLite message3 = codedInputStream.readMessage(RuntimeFractionalPercent.parser(), extensionRegistryLite);
                            this.enabledSpecifier_ = message3;
                            if (builder != null) {
                                builder.mergeFrom((RuntimeFractionalPercent) message3);
                                this.enabledSpecifier_ = builder.m16666buildPartial();
                            }
                            this.enabledSpecifierCase_ = 9;
                        case 82:
                            RuntimeFractionalPercent runtimeFractionalPercent = this.shadowEnabled_;
                            builder = runtimeFractionalPercent != null ? runtimeFractionalPercent.m16659toBuilder() : null;
                            RuntimeFractionalPercent runtimeFractionalPercent2 = (RuntimeFractionalPercent) codedInputStream.readMessage(RuntimeFractionalPercent.parser(), extensionRegistryLite);
                            this.shadowEnabled_ = runtimeFractionalPercent2;
                            if (builder != null) {
                                builder.mergeFrom(runtimeFractionalPercent2);
                                this.shadowEnabled_ = builder.m16666buildPartial();
                            }
                        case RESET_TO_DEFAULT_CONFIGURATION_COMMAND_VALUE:
                            if ((i & 4) == 0) {
                                this.allowOriginStringMatch_ = new ArrayList();
                                i |= 4;
                            }
                            this.allowOriginStringMatch_.add(codedInputStream.readMessage(StringMatcher.parser(), extensionRegistryLite));
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
                    this.allowOrigin_ = this.allowOrigin_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.allowOriginRegex_ = this.allowOriginRegex_.getUnmodifiableView();
                }
                if ((i & 4) != 0) {
                    this.allowOriginStringMatch_ = Collections.unmodifiableList(this.allowOriginStringMatch_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static CorsPolicy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CorsPolicy> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RouteComponentsProto.internal_static_envoy_api_v2_route_CorsPolicy_descriptor;
    }

    public static CorsPolicy parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (CorsPolicy) PARSER.parseFrom(byteBuffer);
    }

    public static CorsPolicy parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CorsPolicy) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static CorsPolicy parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CorsPolicy) PARSER.parseFrom(byteString);
    }

    public static CorsPolicy parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CorsPolicy) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static CorsPolicy parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CorsPolicy) PARSER.parseFrom(bArr);
    }

    public static CorsPolicy parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CorsPolicy) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static CorsPolicy parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static CorsPolicy parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CorsPolicy parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static CorsPolicy parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CorsPolicy parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static CorsPolicy parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m17863toBuilder();
    }

    public static Builder newBuilder(CorsPolicy corsPolicy) {
        return DEFAULT_INSTANCE.m17863toBuilder().mergeFrom(corsPolicy);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    @Deprecated
    /* renamed from: getAllowOriginList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo17856getAllowOriginList() {
        return this.allowOrigin_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    @Deprecated
    /* renamed from: getAllowOriginRegexList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo17857getAllowOriginRegexList() {
        return this.allowOriginRegex_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public List<StringMatcher> getAllowOriginStringMatchList() {
        return this.allowOriginStringMatch_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public List<? extends StringMatcherOrBuilder> getAllowOriginStringMatchOrBuilderList() {
        return this.allowOriginStringMatch_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public CorsPolicy m17858getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<CorsPolicy> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public boolean hasAllowCredentials() {
        return this.allowCredentials_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    @Deprecated
    public boolean hasEnabled() {
        return this.enabledSpecifierCase_ == 7;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public boolean hasFilterEnabled() {
        return this.enabledSpecifierCase_ == 9;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public boolean hasShadowEnabled() {
        return this.shadowEnabled_ != null;
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
        return new CorsPolicy();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RouteComponentsProto.internal_static_envoy_api_v2_route_CorsPolicy_fieldAccessorTable.ensureFieldAccessorsInitialized(CorsPolicy.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public EnabledSpecifierCase getEnabledSpecifierCase() {
        return EnabledSpecifierCase.forNumber(this.enabledSpecifierCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    @Deprecated
    public int getAllowOriginCount() {
        return this.allowOrigin_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    @Deprecated
    public String getAllowOrigin(int i) {
        return (String) this.allowOrigin_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    @Deprecated
    public ByteString getAllowOriginBytes(int i) {
        return this.allowOrigin_.getByteString(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    @Deprecated
    public int getAllowOriginRegexCount() {
        return this.allowOriginRegex_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    @Deprecated
    public String getAllowOriginRegex(int i) {
        return (String) this.allowOriginRegex_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    @Deprecated
    public ByteString getAllowOriginRegexBytes(int i) {
        return this.allowOriginRegex_.getByteString(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public int getAllowOriginStringMatchCount() {
        return this.allowOriginStringMatch_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public StringMatcher getAllowOriginStringMatch(int i) {
        return this.allowOriginStringMatch_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public StringMatcherOrBuilder getAllowOriginStringMatchOrBuilder(int i) {
        return this.allowOriginStringMatch_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public String getAllowMethods() {
        Object obj = this.allowMethods_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.allowMethods_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public ByteString getAllowMethodsBytes() {
        Object obj = this.allowMethods_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.allowMethods_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public String getAllowHeaders() {
        Object obj = this.allowHeaders_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.allowHeaders_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public ByteString getAllowHeadersBytes() {
        Object obj = this.allowHeaders_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.allowHeaders_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public String getExposeHeaders() {
        Object obj = this.exposeHeaders_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.exposeHeaders_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public ByteString getExposeHeadersBytes() {
        Object obj = this.exposeHeaders_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.exposeHeaders_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public String getMaxAge() {
        Object obj = this.maxAge_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.maxAge_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public ByteString getMaxAgeBytes() {
        Object obj = this.maxAge_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.maxAge_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public BoolValue getAllowCredentials() {
        BoolValue boolValue = this.allowCredentials_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public BoolValueOrBuilder getAllowCredentialsOrBuilder() {
        return getAllowCredentials();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    @Deprecated
    public BoolValue getEnabled() {
        if (this.enabledSpecifierCase_ == 7) {
            return (BoolValue) this.enabledSpecifier_;
        }
        return BoolValue.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    @Deprecated
    public BoolValueOrBuilder getEnabledOrBuilder() {
        if (this.enabledSpecifierCase_ == 7) {
            return (BoolValue) this.enabledSpecifier_;
        }
        return BoolValue.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public RuntimeFractionalPercent getFilterEnabled() {
        if (this.enabledSpecifierCase_ == 9) {
            return (RuntimeFractionalPercent) this.enabledSpecifier_;
        }
        return RuntimeFractionalPercent.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public RuntimeFractionalPercentOrBuilder getFilterEnabledOrBuilder() {
        if (this.enabledSpecifierCase_ == 9) {
            return (RuntimeFractionalPercent) this.enabledSpecifier_;
        }
        return RuntimeFractionalPercent.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public RuntimeFractionalPercent getShadowEnabled() {
        RuntimeFractionalPercent runtimeFractionalPercent = this.shadowEnabled_;
        return runtimeFractionalPercent == null ? RuntimeFractionalPercent.getDefaultInstance() : runtimeFractionalPercent;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
    public RuntimeFractionalPercentOrBuilder getShadowEnabledOrBuilder() {
        return getShadowEnabled();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.allowOrigin_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.allowOrigin_.getRaw(i));
        }
        if (!getAllowMethodsBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.allowMethods_);
        }
        if (!getAllowHeadersBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.allowHeaders_);
        }
        if (!getExposeHeadersBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.exposeHeaders_);
        }
        if (!getMaxAgeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.maxAge_);
        }
        if (this.allowCredentials_ != null) {
            codedOutputStream.writeMessage(6, getAllowCredentials());
        }
        if (this.enabledSpecifierCase_ == 7) {
            codedOutputStream.writeMessage(7, (BoolValue) this.enabledSpecifier_);
        }
        for (int i2 = 0; i2 < this.allowOriginRegex_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.allowOriginRegex_.getRaw(i2));
        }
        if (this.enabledSpecifierCase_ == 9) {
            codedOutputStream.writeMessage(9, (RuntimeFractionalPercent) this.enabledSpecifier_);
        }
        if (this.shadowEnabled_ != null) {
            codedOutputStream.writeMessage(10, getShadowEnabled());
        }
        for (int i3 = 0; i3 < this.allowOriginStringMatch_.size(); i3++) {
            codedOutputStream.writeMessage(11, this.allowOriginStringMatch_.get(i3));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.allowOrigin_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.allowOrigin_.getRaw(i2));
        }
        int size = iComputeStringSizeNoTag + mo17856getAllowOriginList().size();
        if (!getAllowMethodsBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(2, this.allowMethods_);
        }
        if (!getAllowHeadersBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(3, this.allowHeaders_);
        }
        if (!getExposeHeadersBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(4, this.exposeHeaders_);
        }
        if (!getMaxAgeBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(5, this.maxAge_);
        }
        if (this.allowCredentials_ != null) {
            size += CodedOutputStream.computeMessageSize(6, getAllowCredentials());
        }
        if (this.enabledSpecifierCase_ == 7) {
            size += CodedOutputStream.computeMessageSize(7, (BoolValue) this.enabledSpecifier_);
        }
        int iComputeStringSizeNoTag2 = 0;
        for (int i3 = 0; i3 < this.allowOriginRegex_.size(); i3++) {
            iComputeStringSizeNoTag2 += computeStringSizeNoTag(this.allowOriginRegex_.getRaw(i3));
        }
        int size2 = size + iComputeStringSizeNoTag2 + mo17857getAllowOriginRegexList().size();
        if (this.enabledSpecifierCase_ == 9) {
            size2 += CodedOutputStream.computeMessageSize(9, (RuntimeFractionalPercent) this.enabledSpecifier_);
        }
        if (this.shadowEnabled_ != null) {
            size2 += CodedOutputStream.computeMessageSize(10, getShadowEnabled());
        }
        for (int i4 = 0; i4 < this.allowOriginStringMatch_.size(); i4++) {
            size2 += CodedOutputStream.computeMessageSize(11, this.allowOriginStringMatch_.get(i4));
        }
        int serializedSize = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CorsPolicy)) {
            return super.equals(obj);
        }
        CorsPolicy corsPolicy = (CorsPolicy) obj;
        if (!mo17856getAllowOriginList().equals(corsPolicy.mo17856getAllowOriginList()) || !mo17857getAllowOriginRegexList().equals(corsPolicy.mo17857getAllowOriginRegexList()) || !getAllowOriginStringMatchList().equals(corsPolicy.getAllowOriginStringMatchList()) || !getAllowMethods().equals(corsPolicy.getAllowMethods()) || !getAllowHeaders().equals(corsPolicy.getAllowHeaders()) || !getExposeHeaders().equals(corsPolicy.getExposeHeaders()) || !getMaxAge().equals(corsPolicy.getMaxAge()) || hasAllowCredentials() != corsPolicy.hasAllowCredentials()) {
            return false;
        }
        if ((hasAllowCredentials() && !getAllowCredentials().equals(corsPolicy.getAllowCredentials())) || hasShadowEnabled() != corsPolicy.hasShadowEnabled()) {
            return false;
        }
        if ((hasShadowEnabled() && !getShadowEnabled().equals(corsPolicy.getShadowEnabled())) || !getEnabledSpecifierCase().equals(corsPolicy.getEnabledSpecifierCase())) {
            return false;
        }
        int i = this.enabledSpecifierCase_;
        if (i == 7) {
            if (!getEnabled().equals(corsPolicy.getEnabled())) {
                return false;
            }
        } else if (i == 9 && !getFilterEnabled().equals(corsPolicy.getFilterEnabled())) {
            return false;
        }
        return this.unknownFields.equals(corsPolicy.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        if (getAllowOriginCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 1) * 53) + mo17856getAllowOriginList().hashCode();
        }
        if (getAllowOriginRegexCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 8) * 53) + mo17857getAllowOriginRegexList().hashCode();
        }
        if (getAllowOriginStringMatchCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 11) * 53) + getAllowOriginStringMatchList().hashCode();
        }
        int iHashCode3 = (((((((((((((((iHashCode2 * 37) + 2) * 53) + getAllowMethods().hashCode()) * 37) + 3) * 53) + getAllowHeaders().hashCode()) * 37) + 4) * 53) + getExposeHeaders().hashCode()) * 37) + 5) * 53) + getMaxAge().hashCode();
        if (hasAllowCredentials()) {
            iHashCode3 = (((iHashCode3 * 37) + 6) * 53) + getAllowCredentials().hashCode();
        }
        if (hasShadowEnabled()) {
            iHashCode3 = (((iHashCode3 * 37) + 10) * 53) + getShadowEnabled().hashCode();
        }
        int i2 = this.enabledSpecifierCase_;
        if (i2 == 7) {
            i = ((iHashCode3 * 37) + 7) * 53;
            iHashCode = getEnabled().hashCode();
        } else {
            if (i2 == 9) {
                i = ((iHashCode3 * 37) + 9) * 53;
                iHashCode = getFilterEnabled().hashCode();
            }
            int iHashCode4 = (iHashCode3 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode4;
            return iHashCode4;
        }
        iHashCode3 = i + iHashCode;
        int iHashCode42 = (iHashCode3 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode42;
        return iHashCode42;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m17860newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m17863toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum EnabledSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        ENABLED(7),
        FILTER_ENABLED(9),
        ENABLEDSPECIFIER_NOT_SET(0);

        private final int value;

        EnabledSpecifierCase(int i) {
            this.value = i;
        }

        public static EnabledSpecifierCase forNumber(int i) {
            if (i == 0) {
                return ENABLEDSPECIFIER_NOT_SET;
            }
            if (i == 7) {
                return ENABLED;
            }
            if (i != 9) {
                return null;
            }
            return FILTER_ENABLED;
        }

        @Deprecated
        public static EnabledSpecifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CorsPolicyOrBuilder {
        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> allowCredentialsBuilder_;
        private BoolValue allowCredentials_;
        private Object allowHeaders_;
        private Object allowMethods_;
        private LazyStringList allowOriginRegex_;
        private RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> allowOriginStringMatchBuilder_;
        private List<StringMatcher> allowOriginStringMatch_;
        private LazyStringList allowOrigin_;
        private int bitField0_;
        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> enabledBuilder_;
        private int enabledSpecifierCase_;
        private Object enabledSpecifier_;
        private Object exposeHeaders_;
        private SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> filterEnabledBuilder_;
        private Object maxAge_;
        private SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> shadowEnabledBuilder_;
        private RuntimeFractionalPercent shadowEnabled_;

        private Builder() {
            this.enabledSpecifierCase_ = 0;
            this.allowOrigin_ = LazyStringArrayList.EMPTY;
            this.allowOriginRegex_ = LazyStringArrayList.EMPTY;
            this.allowOriginStringMatch_ = Collections.emptyList();
            this.allowMethods_ = "";
            this.allowHeaders_ = "";
            this.exposeHeaders_ = "";
            this.maxAge_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.enabledSpecifierCase_ = 0;
            this.allowOrigin_ = LazyStringArrayList.EMPTY;
            this.allowOriginRegex_ = LazyStringArrayList.EMPTY;
            this.allowOriginStringMatch_ = Collections.emptyList();
            this.allowMethods_ = "";
            this.allowHeaders_ = "";
            this.exposeHeaders_ = "";
            this.maxAge_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_CorsPolicy_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public boolean hasAllowCredentials() {
            return (this.allowCredentialsBuilder_ == null && this.allowCredentials_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        @Deprecated
        public boolean hasEnabled() {
            return this.enabledSpecifierCase_ == 7;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public boolean hasFilterEnabled() {
            return this.enabledSpecifierCase_ == 9;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public boolean hasShadowEnabled() {
            return (this.shadowEnabledBuilder_ == null && this.shadowEnabled_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_CorsPolicy_fieldAccessorTable.ensureFieldAccessorsInitialized(CorsPolicy.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (CorsPolicy.alwaysUseFieldBuilders) {
                getAllowOriginStringMatchFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17874clear() {
            super.clear();
            this.allowOrigin_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.allowOriginRegex_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.allowOriginStringMatch_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.allowMethods_ = "";
            this.allowHeaders_ = "";
            this.exposeHeaders_ = "";
            this.maxAge_ = "";
            if (this.allowCredentialsBuilder_ == null) {
                this.allowCredentials_ = null;
            } else {
                this.allowCredentials_ = null;
                this.allowCredentialsBuilder_ = null;
            }
            if (this.shadowEnabledBuilder_ == null) {
                this.shadowEnabled_ = null;
            } else {
                this.shadowEnabled_ = null;
                this.shadowEnabledBuilder_ = null;
            }
            this.enabledSpecifierCase_ = 0;
            this.enabledSpecifier_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_CorsPolicy_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CorsPolicy m17887getDefaultInstanceForType() {
            return CorsPolicy.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CorsPolicy m17868build() throws UninitializedMessageException {
            CorsPolicy corsPolicyM17870buildPartial = m17870buildPartial();
            if (corsPolicyM17870buildPartial.isInitialized()) {
                return corsPolicyM17870buildPartial;
            }
            throw newUninitializedMessageException(corsPolicyM17870buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CorsPolicy m17870buildPartial() {
            CorsPolicy corsPolicy = new CorsPolicy(this);
            if ((this.bitField0_ & 1) != 0) {
                this.allowOrigin_ = this.allowOrigin_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            corsPolicy.allowOrigin_ = this.allowOrigin_;
            if ((this.bitField0_ & 2) != 0) {
                this.allowOriginRegex_ = this.allowOriginRegex_.getUnmodifiableView();
                this.bitField0_ &= -3;
            }
            corsPolicy.allowOriginRegex_ = this.allowOriginRegex_;
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 4) != 0) {
                    this.allowOriginStringMatch_ = Collections.unmodifiableList(this.allowOriginStringMatch_);
                    this.bitField0_ &= -5;
                }
                corsPolicy.allowOriginStringMatch_ = this.allowOriginStringMatch_;
            } else {
                corsPolicy.allowOriginStringMatch_ = repeatedFieldBuilderV3.build();
            }
            corsPolicy.allowMethods_ = this.allowMethods_;
            corsPolicy.allowHeaders_ = this.allowHeaders_;
            corsPolicy.exposeHeaders_ = this.exposeHeaders_;
            corsPolicy.maxAge_ = this.maxAge_;
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.allowCredentialsBuilder_;
            if (singleFieldBuilderV3 == null) {
                corsPolicy.allowCredentials_ = this.allowCredentials_;
            } else {
                corsPolicy.allowCredentials_ = singleFieldBuilderV3.build();
            }
            if (this.enabledSpecifierCase_ == 7) {
                SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV32 = this.enabledBuilder_;
                if (singleFieldBuilderV32 == null) {
                    corsPolicy.enabledSpecifier_ = this.enabledSpecifier_;
                } else {
                    corsPolicy.enabledSpecifier_ = singleFieldBuilderV32.build();
                }
            }
            if (this.enabledSpecifierCase_ == 9) {
                SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV33 = this.filterEnabledBuilder_;
                if (singleFieldBuilderV33 == null) {
                    corsPolicy.enabledSpecifier_ = this.enabledSpecifier_;
                } else {
                    corsPolicy.enabledSpecifier_ = singleFieldBuilderV33.build();
                }
            }
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV34 = this.shadowEnabledBuilder_;
            if (singleFieldBuilderV34 == null) {
                corsPolicy.shadowEnabled_ = this.shadowEnabled_;
            } else {
                corsPolicy.shadowEnabled_ = singleFieldBuilderV34.build();
            }
            corsPolicy.enabledSpecifierCase_ = this.enabledSpecifierCase_;
            onBuilt();
            return corsPolicy;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17886clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17898setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17876clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17879clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17900setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17866addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17891mergeFrom(Message message) {
            if (message instanceof CorsPolicy) {
                return mergeFrom((CorsPolicy) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(CorsPolicy corsPolicy) {
            if (corsPolicy == CorsPolicy.getDefaultInstance()) {
                return this;
            }
            if (!corsPolicy.allowOrigin_.isEmpty()) {
                if (this.allowOrigin_.isEmpty()) {
                    this.allowOrigin_ = corsPolicy.allowOrigin_;
                    this.bitField0_ &= -2;
                } else {
                    ensureAllowOriginIsMutable();
                    this.allowOrigin_.addAll(corsPolicy.allowOrigin_);
                }
                onChanged();
            }
            if (!corsPolicy.allowOriginRegex_.isEmpty()) {
                if (this.allowOriginRegex_.isEmpty()) {
                    this.allowOriginRegex_ = corsPolicy.allowOriginRegex_;
                    this.bitField0_ &= -3;
                } else {
                    ensureAllowOriginRegexIsMutable();
                    this.allowOriginRegex_.addAll(corsPolicy.allowOriginRegex_);
                }
                onChanged();
            }
            if (this.allowOriginStringMatchBuilder_ == null) {
                if (!corsPolicy.allowOriginStringMatch_.isEmpty()) {
                    if (this.allowOriginStringMatch_.isEmpty()) {
                        this.allowOriginStringMatch_ = corsPolicy.allowOriginStringMatch_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureAllowOriginStringMatchIsMutable();
                        this.allowOriginStringMatch_.addAll(corsPolicy.allowOriginStringMatch_);
                    }
                    onChanged();
                }
            } else if (!corsPolicy.allowOriginStringMatch_.isEmpty()) {
                if (!this.allowOriginStringMatchBuilder_.isEmpty()) {
                    this.allowOriginStringMatchBuilder_.addAllMessages(corsPolicy.allowOriginStringMatch_);
                } else {
                    this.allowOriginStringMatchBuilder_.dispose();
                    this.allowOriginStringMatchBuilder_ = null;
                    this.allowOriginStringMatch_ = corsPolicy.allowOriginStringMatch_;
                    this.bitField0_ &= -5;
                    this.allowOriginStringMatchBuilder_ = CorsPolicy.alwaysUseFieldBuilders ? getAllowOriginStringMatchFieldBuilder() : null;
                }
            }
            if (!corsPolicy.getAllowMethods().isEmpty()) {
                this.allowMethods_ = corsPolicy.allowMethods_;
                onChanged();
            }
            if (!corsPolicy.getAllowHeaders().isEmpty()) {
                this.allowHeaders_ = corsPolicy.allowHeaders_;
                onChanged();
            }
            if (!corsPolicy.getExposeHeaders().isEmpty()) {
                this.exposeHeaders_ = corsPolicy.exposeHeaders_;
                onChanged();
            }
            if (!corsPolicy.getMaxAge().isEmpty()) {
                this.maxAge_ = corsPolicy.maxAge_;
                onChanged();
            }
            if (corsPolicy.hasAllowCredentials()) {
                mergeAllowCredentials(corsPolicy.getAllowCredentials());
            }
            if (corsPolicy.hasShadowEnabled()) {
                mergeShadowEnabled(corsPolicy.getShadowEnabled());
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$api$v2$route$CorsPolicy$EnabledSpecifierCase[corsPolicy.getEnabledSpecifierCase().ordinal()];
            if (i == 1) {
                mergeEnabled(corsPolicy.getEnabled());
            } else if (i == 2) {
                mergeFilterEnabled(corsPolicy.getFilterEnabled());
            }
            m17896mergeUnknownFields(corsPolicy.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicy.Builder m17892mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicy.access$1700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicy r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicy) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicy r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicy) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicy.Builder.m17892mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicy$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public EnabledSpecifierCase getEnabledSpecifierCase() {
            return EnabledSpecifierCase.forNumber(this.enabledSpecifierCase_);
        }

        public Builder clearEnabledSpecifier() {
            this.enabledSpecifierCase_ = 0;
            this.enabledSpecifier_ = null;
            onChanged();
            return this;
        }

        private void ensureAllowOriginIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.allowOrigin_ = new LazyStringArrayList(this.allowOrigin_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        @Deprecated
        /* renamed from: getAllowOriginList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo17856getAllowOriginList() {
            return this.allowOrigin_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        @Deprecated
        public int getAllowOriginCount() {
            return this.allowOrigin_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        @Deprecated
        public String getAllowOrigin(int i) {
            return (String) this.allowOrigin_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        @Deprecated
        public ByteString getAllowOriginBytes(int i) {
            return this.allowOrigin_.getByteString(i);
        }

        @Deprecated
        public Builder setAllowOrigin(int i, String str) {
            str.getClass();
            ensureAllowOriginIsMutable();
            this.allowOrigin_.set(i, str);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addAllowOrigin(String str) {
            str.getClass();
            ensureAllowOriginIsMutable();
            this.allowOrigin_.add(str);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addAllAllowOrigin(Iterable<String> iterable) {
            ensureAllowOriginIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.allowOrigin_);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder clearAllowOrigin() {
            this.allowOrigin_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addAllowOriginBytes(ByteString byteString) {
            byteString.getClass();
            CorsPolicy.checkByteStringIsUtf8(byteString);
            ensureAllowOriginIsMutable();
            this.allowOrigin_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureAllowOriginRegexIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.allowOriginRegex_ = new LazyStringArrayList(this.allowOriginRegex_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        @Deprecated
        /* renamed from: getAllowOriginRegexList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo17857getAllowOriginRegexList() {
            return this.allowOriginRegex_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        @Deprecated
        public int getAllowOriginRegexCount() {
            return this.allowOriginRegex_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        @Deprecated
        public String getAllowOriginRegex(int i) {
            return (String) this.allowOriginRegex_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        @Deprecated
        public ByteString getAllowOriginRegexBytes(int i) {
            return this.allowOriginRegex_.getByteString(i);
        }

        @Deprecated
        public Builder setAllowOriginRegex(int i, String str) {
            str.getClass();
            ensureAllowOriginRegexIsMutable();
            this.allowOriginRegex_.set(i, str);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addAllowOriginRegex(String str) {
            str.getClass();
            ensureAllowOriginRegexIsMutable();
            this.allowOriginRegex_.add(str);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addAllAllowOriginRegex(Iterable<String> iterable) {
            ensureAllowOriginRegexIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.allowOriginRegex_);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder clearAllowOriginRegex() {
            this.allowOriginRegex_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addAllowOriginRegexBytes(ByteString byteString) {
            byteString.getClass();
            CorsPolicy.checkByteStringIsUtf8(byteString);
            ensureAllowOriginRegexIsMutable();
            this.allowOriginRegex_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureAllowOriginStringMatchIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.allowOriginStringMatch_ = new ArrayList(this.allowOriginStringMatch_);
                this.bitField0_ |= 4;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public List<StringMatcher> getAllowOriginStringMatchList() {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.allowOriginStringMatch_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public int getAllowOriginStringMatchCount() {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.allowOriginStringMatch_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public StringMatcher getAllowOriginStringMatch(int i) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.allowOriginStringMatch_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setAllowOriginStringMatch(int i, StringMatcher stringMatcher) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                stringMatcher.getClass();
                ensureAllowOriginStringMatchIsMutable();
                this.allowOriginStringMatch_.set(i, stringMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, stringMatcher);
            }
            return this;
        }

        public Builder setAllowOriginStringMatch(int i, StringMatcher.Builder builder) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAllowOriginStringMatchIsMutable();
                this.allowOriginStringMatch_.set(i, builder.m33509build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m33509build());
            }
            return this;
        }

        public Builder addAllowOriginStringMatch(StringMatcher stringMatcher) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                stringMatcher.getClass();
                ensureAllowOriginStringMatchIsMutable();
                this.allowOriginStringMatch_.add(stringMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(stringMatcher);
            }
            return this;
        }

        public Builder addAllowOriginStringMatch(int i, StringMatcher stringMatcher) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                stringMatcher.getClass();
                ensureAllowOriginStringMatchIsMutable();
                this.allowOriginStringMatch_.add(i, stringMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, stringMatcher);
            }
            return this;
        }

        public Builder addAllowOriginStringMatch(StringMatcher.Builder builder) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAllowOriginStringMatchIsMutable();
                this.allowOriginStringMatch_.add(builder.m33509build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m33509build());
            }
            return this;
        }

        public Builder addAllowOriginStringMatch(int i, StringMatcher.Builder builder) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAllowOriginStringMatchIsMutable();
                this.allowOriginStringMatch_.add(i, builder.m33509build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m33509build());
            }
            return this;
        }

        public Builder addAllAllowOriginStringMatch(Iterable<? extends StringMatcher> iterable) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAllowOriginStringMatchIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.allowOriginStringMatch_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearAllowOriginStringMatch() {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.allowOriginStringMatch_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeAllowOriginStringMatch(int i) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAllowOriginStringMatchIsMutable();
                this.allowOriginStringMatch_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public StringMatcher.Builder getAllowOriginStringMatchBuilder(int i) {
            return getAllowOriginStringMatchFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public StringMatcherOrBuilder getAllowOriginStringMatchOrBuilder(int i) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.allowOriginStringMatch_.get(i);
            }
            return (StringMatcherOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public List<? extends StringMatcherOrBuilder> getAllowOriginStringMatchOrBuilderList() {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.allowOriginStringMatch_);
        }

        public StringMatcher.Builder addAllowOriginStringMatchBuilder() {
            return getAllowOriginStringMatchFieldBuilder().addBuilder(StringMatcher.getDefaultInstance());
        }

        public StringMatcher.Builder addAllowOriginStringMatchBuilder(int i) {
            return getAllowOriginStringMatchFieldBuilder().addBuilder(i, StringMatcher.getDefaultInstance());
        }

        public List<StringMatcher.Builder> getAllowOriginStringMatchBuilderList() {
            return getAllowOriginStringMatchFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> getAllowOriginStringMatchFieldBuilder() {
            if (this.allowOriginStringMatchBuilder_ == null) {
                this.allowOriginStringMatchBuilder_ = new RepeatedFieldBuilderV3<>(this.allowOriginStringMatch_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                this.allowOriginStringMatch_ = null;
            }
            return this.allowOriginStringMatchBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public String getAllowMethods() {
            Object obj = this.allowMethods_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.allowMethods_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setAllowMethods(String str) {
            str.getClass();
            this.allowMethods_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public ByteString getAllowMethodsBytes() {
            Object obj = this.allowMethods_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.allowMethods_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAllowMethodsBytes(ByteString byteString) {
            byteString.getClass();
            CorsPolicy.checkByteStringIsUtf8(byteString);
            this.allowMethods_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearAllowMethods() {
            this.allowMethods_ = CorsPolicy.getDefaultInstance().getAllowMethods();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public String getAllowHeaders() {
            Object obj = this.allowHeaders_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.allowHeaders_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setAllowHeaders(String str) {
            str.getClass();
            this.allowHeaders_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public ByteString getAllowHeadersBytes() {
            Object obj = this.allowHeaders_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.allowHeaders_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAllowHeadersBytes(ByteString byteString) {
            byteString.getClass();
            CorsPolicy.checkByteStringIsUtf8(byteString);
            this.allowHeaders_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearAllowHeaders() {
            this.allowHeaders_ = CorsPolicy.getDefaultInstance().getAllowHeaders();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public String getExposeHeaders() {
            Object obj = this.exposeHeaders_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.exposeHeaders_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setExposeHeaders(String str) {
            str.getClass();
            this.exposeHeaders_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public ByteString getExposeHeadersBytes() {
            Object obj = this.exposeHeaders_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.exposeHeaders_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setExposeHeadersBytes(ByteString byteString) {
            byteString.getClass();
            CorsPolicy.checkByteStringIsUtf8(byteString);
            this.exposeHeaders_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearExposeHeaders() {
            this.exposeHeaders_ = CorsPolicy.getDefaultInstance().getExposeHeaders();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public String getMaxAge() {
            Object obj = this.maxAge_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.maxAge_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setMaxAge(String str) {
            str.getClass();
            this.maxAge_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public ByteString getMaxAgeBytes() {
            Object obj = this.maxAge_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.maxAge_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setMaxAgeBytes(ByteString byteString) {
            byteString.getClass();
            CorsPolicy.checkByteStringIsUtf8(byteString);
            this.maxAge_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearMaxAge() {
            this.maxAge_ = CorsPolicy.getDefaultInstance().getMaxAge();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public BoolValue getAllowCredentials() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.allowCredentialsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            BoolValue boolValue = this.allowCredentials_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        public Builder setAllowCredentials(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.allowCredentialsBuilder_;
            if (singleFieldBuilderV3 == null) {
                boolValue.getClass();
                this.allowCredentials_ = boolValue;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(boolValue);
            }
            return this;
        }

        public Builder setAllowCredentials(BoolValue.Builder builder) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.allowCredentialsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.allowCredentials_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeAllowCredentials(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.allowCredentialsBuilder_;
            if (singleFieldBuilderV3 == null) {
                BoolValue boolValue2 = this.allowCredentials_;
                if (boolValue2 != null) {
                    this.allowCredentials_ = BoolValue.newBuilder(boolValue2).mergeFrom(boolValue).buildPartial();
                } else {
                    this.allowCredentials_ = boolValue;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(boolValue);
            }
            return this;
        }

        public Builder clearAllowCredentials() {
            if (this.allowCredentialsBuilder_ == null) {
                this.allowCredentials_ = null;
                onChanged();
            } else {
                this.allowCredentials_ = null;
                this.allowCredentialsBuilder_ = null;
            }
            return this;
        }

        public BoolValue.Builder getAllowCredentialsBuilder() {
            onChanged();
            return getAllowCredentialsFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public BoolValueOrBuilder getAllowCredentialsOrBuilder() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.allowCredentialsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            BoolValue boolValue = this.allowCredentials_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> getAllowCredentialsFieldBuilder() {
            if (this.allowCredentialsBuilder_ == null) {
                this.allowCredentialsBuilder_ = new SingleFieldBuilderV3<>(getAllowCredentials(), getParentForChildren(), isClean());
                this.allowCredentials_ = null;
            }
            return this.allowCredentialsBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        @Deprecated
        public BoolValue getEnabled() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.enabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.enabledSpecifierCase_ == 7) {
                    return (BoolValue) this.enabledSpecifier_;
                }
                return BoolValue.getDefaultInstance();
            }
            if (this.enabledSpecifierCase_ == 7) {
                return singleFieldBuilderV3.getMessage();
            }
            return BoolValue.getDefaultInstance();
        }

        @Deprecated
        public Builder setEnabled(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.enabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                boolValue.getClass();
                this.enabledSpecifier_ = boolValue;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(boolValue);
            }
            this.enabledSpecifierCase_ = 7;
            return this;
        }

        @Deprecated
        public Builder setEnabled(BoolValue.Builder builder) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.enabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.enabledSpecifier_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.enabledSpecifierCase_ = 7;
            return this;
        }

        @Deprecated
        public Builder mergeEnabled(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.enabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.enabledSpecifierCase_ != 7 || this.enabledSpecifier_ == BoolValue.getDefaultInstance()) {
                    this.enabledSpecifier_ = boolValue;
                } else {
                    this.enabledSpecifier_ = BoolValue.newBuilder((BoolValue) this.enabledSpecifier_).mergeFrom(boolValue).buildPartial();
                }
                onChanged();
            } else {
                if (this.enabledSpecifierCase_ == 7) {
                    singleFieldBuilderV3.mergeFrom(boolValue);
                }
                this.enabledBuilder_.setMessage(boolValue);
            }
            this.enabledSpecifierCase_ = 7;
            return this;
        }

        @Deprecated
        public Builder clearEnabled() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.enabledBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.enabledSpecifierCase_ == 7) {
                    this.enabledSpecifierCase_ = 0;
                    this.enabledSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.enabledSpecifierCase_ == 7) {
                this.enabledSpecifierCase_ = 0;
                this.enabledSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Deprecated
        public BoolValue.Builder getEnabledBuilder() {
            return getEnabledFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        @Deprecated
        public BoolValueOrBuilder getEnabledOrBuilder() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3;
            int i = this.enabledSpecifierCase_;
            if (i == 7 && (singleFieldBuilderV3 = this.enabledBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 7) {
                return (BoolValue) this.enabledSpecifier_;
            }
            return BoolValue.getDefaultInstance();
        }

        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> getEnabledFieldBuilder() {
            if (this.enabledBuilder_ == null) {
                if (this.enabledSpecifierCase_ != 7) {
                    this.enabledSpecifier_ = BoolValue.getDefaultInstance();
                }
                this.enabledBuilder_ = new SingleFieldBuilderV3<>((BoolValue) this.enabledSpecifier_, getParentForChildren(), isClean());
                this.enabledSpecifier_ = null;
            }
            this.enabledSpecifierCase_ = 7;
            onChanged();
            return this.enabledBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public RuntimeFractionalPercent getFilterEnabled() {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.filterEnabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.enabledSpecifierCase_ == 9) {
                    return (RuntimeFractionalPercent) this.enabledSpecifier_;
                }
                return RuntimeFractionalPercent.getDefaultInstance();
            }
            if (this.enabledSpecifierCase_ == 9) {
                return singleFieldBuilderV3.getMessage();
            }
            return RuntimeFractionalPercent.getDefaultInstance();
        }

        public Builder setFilterEnabled(RuntimeFractionalPercent runtimeFractionalPercent) {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.filterEnabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                runtimeFractionalPercent.getClass();
                this.enabledSpecifier_ = runtimeFractionalPercent;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(runtimeFractionalPercent);
            }
            this.enabledSpecifierCase_ = 9;
            return this;
        }

        public Builder setFilterEnabled(RuntimeFractionalPercent.Builder builder) {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.filterEnabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.enabledSpecifier_ = builder.m16664build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m16664build());
            }
            this.enabledSpecifierCase_ = 9;
            return this;
        }

        public Builder mergeFilterEnabled(RuntimeFractionalPercent runtimeFractionalPercent) {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.filterEnabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.enabledSpecifierCase_ != 9 || this.enabledSpecifier_ == RuntimeFractionalPercent.getDefaultInstance()) {
                    this.enabledSpecifier_ = runtimeFractionalPercent;
                } else {
                    this.enabledSpecifier_ = RuntimeFractionalPercent.newBuilder((RuntimeFractionalPercent) this.enabledSpecifier_).mergeFrom(runtimeFractionalPercent).m16666buildPartial();
                }
                onChanged();
            } else {
                if (this.enabledSpecifierCase_ == 9) {
                    singleFieldBuilderV3.mergeFrom(runtimeFractionalPercent);
                }
                this.filterEnabledBuilder_.setMessage(runtimeFractionalPercent);
            }
            this.enabledSpecifierCase_ = 9;
            return this;
        }

        public Builder clearFilterEnabled() {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.filterEnabledBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.enabledSpecifierCase_ == 9) {
                    this.enabledSpecifierCase_ = 0;
                    this.enabledSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.enabledSpecifierCase_ == 9) {
                this.enabledSpecifierCase_ = 0;
                this.enabledSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public RuntimeFractionalPercent.Builder getFilterEnabledBuilder() {
            return getFilterEnabledFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public RuntimeFractionalPercentOrBuilder getFilterEnabledOrBuilder() {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3;
            int i = this.enabledSpecifierCase_;
            if (i == 9 && (singleFieldBuilderV3 = this.filterEnabledBuilder_) != null) {
                return (RuntimeFractionalPercentOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 9) {
                return (RuntimeFractionalPercent) this.enabledSpecifier_;
            }
            return RuntimeFractionalPercent.getDefaultInstance();
        }

        private SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> getFilterEnabledFieldBuilder() {
            if (this.filterEnabledBuilder_ == null) {
                if (this.enabledSpecifierCase_ != 9) {
                    this.enabledSpecifier_ = RuntimeFractionalPercent.getDefaultInstance();
                }
                this.filterEnabledBuilder_ = new SingleFieldBuilderV3<>((RuntimeFractionalPercent) this.enabledSpecifier_, getParentForChildren(), isClean());
                this.enabledSpecifier_ = null;
            }
            this.enabledSpecifierCase_ = 9;
            onChanged();
            return this.filterEnabledBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public RuntimeFractionalPercent getShadowEnabled() {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.shadowEnabledBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            RuntimeFractionalPercent runtimeFractionalPercent = this.shadowEnabled_;
            return runtimeFractionalPercent == null ? RuntimeFractionalPercent.getDefaultInstance() : runtimeFractionalPercent;
        }

        public Builder setShadowEnabled(RuntimeFractionalPercent runtimeFractionalPercent) {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.shadowEnabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                runtimeFractionalPercent.getClass();
                this.shadowEnabled_ = runtimeFractionalPercent;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(runtimeFractionalPercent);
            }
            return this;
        }

        public Builder setShadowEnabled(RuntimeFractionalPercent.Builder builder) {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.shadowEnabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.shadowEnabled_ = builder.m16664build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m16664build());
            }
            return this;
        }

        public Builder mergeShadowEnabled(RuntimeFractionalPercent runtimeFractionalPercent) {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.shadowEnabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                RuntimeFractionalPercent runtimeFractionalPercent2 = this.shadowEnabled_;
                if (runtimeFractionalPercent2 != null) {
                    this.shadowEnabled_ = RuntimeFractionalPercent.newBuilder(runtimeFractionalPercent2).mergeFrom(runtimeFractionalPercent).m16666buildPartial();
                } else {
                    this.shadowEnabled_ = runtimeFractionalPercent;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(runtimeFractionalPercent);
            }
            return this;
        }

        public Builder clearShadowEnabled() {
            if (this.shadowEnabledBuilder_ == null) {
                this.shadowEnabled_ = null;
                onChanged();
            } else {
                this.shadowEnabled_ = null;
                this.shadowEnabledBuilder_ = null;
            }
            return this;
        }

        public RuntimeFractionalPercent.Builder getShadowEnabledBuilder() {
            onChanged();
            return getShadowEnabledFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicyOrBuilder
        public RuntimeFractionalPercentOrBuilder getShadowEnabledOrBuilder() {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.shadowEnabledBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (RuntimeFractionalPercentOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            RuntimeFractionalPercent runtimeFractionalPercent = this.shadowEnabled_;
            return runtimeFractionalPercent == null ? RuntimeFractionalPercent.getDefaultInstance() : runtimeFractionalPercent;
        }

        private SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> getShadowEnabledFieldBuilder() {
            if (this.shadowEnabledBuilder_ == null) {
                this.shadowEnabledBuilder_ = new SingleFieldBuilderV3<>(getShadowEnabled(), getParentForChildren(), isClean());
                this.shadowEnabled_ = null;
            }
            return this.shadowEnabledBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m17902setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m17896mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicy$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$api$v2$route$CorsPolicy$EnabledSpecifierCase;

        static {
            int[] iArr = new int[EnabledSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$api$v2$route$CorsPolicy$EnabledSpecifierCase = iArr;
            try {
                iArr[EnabledSpecifierCase.ENABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$CorsPolicy$EnabledSpecifierCase[EnabledSpecifierCase.FILTER_ENABLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$CorsPolicy$EnabledSpecifierCase[EnabledSpecifierCase.ENABLEDSPECIFIER_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
