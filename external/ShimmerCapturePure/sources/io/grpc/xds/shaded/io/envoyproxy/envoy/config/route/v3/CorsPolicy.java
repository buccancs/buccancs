package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

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
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercentOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.StringMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.StringMatcherOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class CorsPolicy extends GeneratedMessageV3 implements CorsPolicyOrBuilder {
    public static final int ALLOW_CREDENTIALS_FIELD_NUMBER = 6;
    public static final int ALLOW_HEADERS_FIELD_NUMBER = 3;
    public static final int ALLOW_METHODS_FIELD_NUMBER = 2;
    public static final int ALLOW_ORIGIN_STRING_MATCH_FIELD_NUMBER = 11;
    public static final int EXPOSE_HEADERS_FIELD_NUMBER = 4;
    public static final int FILTER_ENABLED_FIELD_NUMBER = 9;
    public static final int MAX_AGE_FIELD_NUMBER = 5;
    public static final int SHADOW_ENABLED_FIELD_NUMBER = 10;
    private static final long serialVersionUID = 0;
    private static final CorsPolicy DEFAULT_INSTANCE = new CorsPolicy();
    private static final Parser<CorsPolicy> PARSER = new AbstractParser<CorsPolicy>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicy.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public CorsPolicy m27808parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CorsPolicy(codedInputStream, extensionRegistryLite);
        }
    };
    private BoolValue allowCredentials_;
    private volatile Object allowHeaders_;
    private volatile Object allowMethods_;
    private List<StringMatcher> allowOriginStringMatch_;
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
        this.allowOriginStringMatch_ = Collections.emptyList();
        this.allowMethods_ = "";
        this.allowHeaders_ = "";
        this.exposeHeaders_ = "";
        this.maxAge_ = "";
    }

    private CorsPolicy(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        BoolValue.Builder builderM24016toBuilder;
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
                        if (tag == 18) {
                            this.allowMethods_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 26) {
                            this.allowHeaders_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 34) {
                            this.exposeHeaders_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag != 42) {
                            if (tag == 50) {
                                BoolValue boolValue = this.allowCredentials_;
                                builderM24016toBuilder = boolValue != null ? boolValue.toBuilder() : null;
                                BoolValue message = codedInputStream.readMessage(BoolValue.parser(), extensionRegistryLite);
                                this.allowCredentials_ = message;
                                if (builderM24016toBuilder != null) {
                                    builderM24016toBuilder.mergeFrom(message);
                                    this.allowCredentials_ = builderM24016toBuilder.buildPartial();
                                }
                            } else if (tag == 74) {
                                builderM24016toBuilder = this.enabledSpecifierCase_ == 9 ? ((RuntimeFractionalPercent) this.enabledSpecifier_).m24016toBuilder() : null;
                                MessageLite message2 = codedInputStream.readMessage(RuntimeFractionalPercent.parser(), extensionRegistryLite);
                                this.enabledSpecifier_ = message2;
                                if (builderM24016toBuilder != null) {
                                    builderM24016toBuilder.mergeFrom((RuntimeFractionalPercent) message2);
                                    this.enabledSpecifier_ = builderM24016toBuilder.m24023buildPartial();
                                }
                                this.enabledSpecifierCase_ = 9;
                            } else if (tag == 82) {
                                RuntimeFractionalPercent runtimeFractionalPercent = this.shadowEnabled_;
                                builderM24016toBuilder = runtimeFractionalPercent != null ? runtimeFractionalPercent.m24016toBuilder() : null;
                                RuntimeFractionalPercent runtimeFractionalPercent2 = (RuntimeFractionalPercent) codedInputStream.readMessage(RuntimeFractionalPercent.parser(), extensionRegistryLite);
                                this.shadowEnabled_ = runtimeFractionalPercent2;
                                if (builderM24016toBuilder != null) {
                                    builderM24016toBuilder.mergeFrom(runtimeFractionalPercent2);
                                    this.shadowEnabled_ = builderM24016toBuilder.m24023buildPartial();
                                }
                            } else if (tag == 90) {
                                if (!(z2 & true)) {
                                    this.allowOriginStringMatch_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.allowOriginStringMatch_.add(codedInputStream.readMessage(StringMatcher.parser(), extensionRegistryLite));
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.maxAge_ = codedInputStream.readStringRequireUtf8();
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
        return RouteComponentsProto.internal_static_envoy_config_route_v3_CorsPolicy_descriptor;
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
        return DEFAULT_INSTANCE.m27806toBuilder();
    }

    public static Builder newBuilder(CorsPolicy corsPolicy) {
        return DEFAULT_INSTANCE.m27806toBuilder().mergeFrom(corsPolicy);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public List<StringMatcher> getAllowOriginStringMatchList() {
        return this.allowOriginStringMatch_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public List<? extends StringMatcherOrBuilder> getAllowOriginStringMatchOrBuilderList() {
        return this.allowOriginStringMatch_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public CorsPolicy m27801getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<CorsPolicy> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public boolean hasAllowCredentials() {
        return this.allowCredentials_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public boolean hasFilterEnabled() {
        return this.enabledSpecifierCase_ == 9;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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
        return RouteComponentsProto.internal_static_envoy_config_route_v3_CorsPolicy_fieldAccessorTable.ensureFieldAccessorsInitialized(CorsPolicy.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public EnabledSpecifierCase getEnabledSpecifierCase() {
        return EnabledSpecifierCase.forNumber(this.enabledSpecifierCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public int getAllowOriginStringMatchCount() {
        return this.allowOriginStringMatch_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public StringMatcher getAllowOriginStringMatch(int i) {
        return this.allowOriginStringMatch_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public StringMatcherOrBuilder getAllowOriginStringMatchOrBuilder(int i) {
        return this.allowOriginStringMatch_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public String getAllowMethods() {
        Object obj = this.allowMethods_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.allowMethods_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public ByteString getAllowMethodsBytes() {
        Object obj = this.allowMethods_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.allowMethods_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public String getAllowHeaders() {
        Object obj = this.allowHeaders_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.allowHeaders_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public ByteString getAllowHeadersBytes() {
        Object obj = this.allowHeaders_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.allowHeaders_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public String getExposeHeaders() {
        Object obj = this.exposeHeaders_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.exposeHeaders_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public ByteString getExposeHeadersBytes() {
        Object obj = this.exposeHeaders_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.exposeHeaders_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public String getMaxAge() {
        Object obj = this.maxAge_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.maxAge_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public ByteString getMaxAgeBytes() {
        Object obj = this.maxAge_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.maxAge_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public BoolValue getAllowCredentials() {
        BoolValue boolValue = this.allowCredentials_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public BoolValueOrBuilder getAllowCredentialsOrBuilder() {
        return getAllowCredentials();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public RuntimeFractionalPercent getFilterEnabled() {
        if (this.enabledSpecifierCase_ == 9) {
            return (RuntimeFractionalPercent) this.enabledSpecifier_;
        }
        return RuntimeFractionalPercent.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public RuntimeFractionalPercentOrBuilder getFilterEnabledOrBuilder() {
        if (this.enabledSpecifierCase_ == 9) {
            return (RuntimeFractionalPercent) this.enabledSpecifier_;
        }
        return RuntimeFractionalPercent.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public RuntimeFractionalPercent getShadowEnabled() {
        RuntimeFractionalPercent runtimeFractionalPercent = this.shadowEnabled_;
        return runtimeFractionalPercent == null ? RuntimeFractionalPercent.getDefaultInstance() : runtimeFractionalPercent;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
    public RuntimeFractionalPercentOrBuilder getShadowEnabledOrBuilder() {
        return getShadowEnabled();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
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
        if (this.enabledSpecifierCase_ == 9) {
            codedOutputStream.writeMessage(9, (RuntimeFractionalPercent) this.enabledSpecifier_);
        }
        if (this.shadowEnabled_ != null) {
            codedOutputStream.writeMessage(10, getShadowEnabled());
        }
        for (int i = 0; i < this.allowOriginStringMatch_.size(); i++) {
            codedOutputStream.writeMessage(11, this.allowOriginStringMatch_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getAllowMethodsBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(2, this.allowMethods_) : 0;
        if (!getAllowHeadersBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.allowHeaders_);
        }
        if (!getExposeHeadersBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.exposeHeaders_);
        }
        if (!getMaxAgeBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(5, this.maxAge_);
        }
        if (this.allowCredentials_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(6, getAllowCredentials());
        }
        if (this.enabledSpecifierCase_ == 9) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(9, (RuntimeFractionalPercent) this.enabledSpecifier_);
        }
        if (this.shadowEnabled_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(10, getShadowEnabled());
        }
        for (int i2 = 0; i2 < this.allowOriginStringMatch_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(11, this.allowOriginStringMatch_.get(i2));
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
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
        if (!getAllowOriginStringMatchList().equals(corsPolicy.getAllowOriginStringMatchList()) || !getAllowMethods().equals(corsPolicy.getAllowMethods()) || !getAllowHeaders().equals(corsPolicy.getAllowHeaders()) || !getExposeHeaders().equals(corsPolicy.getExposeHeaders()) || !getMaxAge().equals(corsPolicy.getMaxAge()) || hasAllowCredentials() != corsPolicy.hasAllowCredentials()) {
            return false;
        }
        if ((hasAllowCredentials() && !getAllowCredentials().equals(corsPolicy.getAllowCredentials())) || hasShadowEnabled() != corsPolicy.hasShadowEnabled()) {
            return false;
        }
        if ((!hasShadowEnabled() || getShadowEnabled().equals(corsPolicy.getShadowEnabled())) && getEnabledSpecifierCase().equals(corsPolicy.getEnabledSpecifierCase())) {
            return (this.enabledSpecifierCase_ != 9 || getFilterEnabled().equals(corsPolicy.getFilterEnabled())) && this.unknownFields.equals(corsPolicy.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getAllowOriginStringMatchCount() > 0) {
            iHashCode = (((iHashCode * 37) + 11) * 53) + getAllowOriginStringMatchList().hashCode();
        }
        int iHashCode2 = (((((((((((((((iHashCode * 37) + 2) * 53) + getAllowMethods().hashCode()) * 37) + 3) * 53) + getAllowHeaders().hashCode()) * 37) + 4) * 53) + getExposeHeaders().hashCode()) * 37) + 5) * 53) + getMaxAge().hashCode();
        if (hasAllowCredentials()) {
            iHashCode2 = (((iHashCode2 * 37) + 6) * 53) + getAllowCredentials().hashCode();
        }
        if (hasShadowEnabled()) {
            iHashCode2 = (((iHashCode2 * 37) + 10) * 53) + getShadowEnabled().hashCode();
        }
        if (this.enabledSpecifierCase_ == 9) {
            iHashCode2 = (((iHashCode2 * 37) + 9) * 53) + getFilterEnabled().hashCode();
        }
        int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode3;
        return iHashCode3;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m27803newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m27806toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum EnabledSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
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
        private RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> allowOriginStringMatchBuilder_;
        private List<StringMatcher> allowOriginStringMatch_;
        private int bitField0_;
        private int enabledSpecifierCase_;
        private Object enabledSpecifier_;
        private Object exposeHeaders_;
        private SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> filterEnabledBuilder_;
        private Object maxAge_;
        private SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> shadowEnabledBuilder_;
        private RuntimeFractionalPercent shadowEnabled_;

        private Builder() {
            this.enabledSpecifierCase_ = 0;
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
            this.allowOriginStringMatch_ = Collections.emptyList();
            this.allowMethods_ = "";
            this.allowHeaders_ = "";
            this.exposeHeaders_ = "";
            this.maxAge_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_config_route_v3_CorsPolicy_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
        public boolean hasAllowCredentials() {
            return (this.allowCredentialsBuilder_ == null && this.allowCredentials_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
        public boolean hasFilterEnabled() {
            return this.enabledSpecifierCase_ == 9;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
        public boolean hasShadowEnabled() {
            return (this.shadowEnabledBuilder_ == null && this.shadowEnabled_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_config_route_v3_CorsPolicy_fieldAccessorTable.ensureFieldAccessorsInitialized(CorsPolicy.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (CorsPolicy.alwaysUseFieldBuilders) {
                getAllowOriginStringMatchFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27817clear() {
            super.clear();
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.allowOriginStringMatch_ = Collections.emptyList();
                this.bitField0_ &= -2;
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
            return RouteComponentsProto.internal_static_envoy_config_route_v3_CorsPolicy_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CorsPolicy m27830getDefaultInstanceForType() {
            return CorsPolicy.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CorsPolicy m27811build() throws UninitializedMessageException {
            CorsPolicy corsPolicyM27813buildPartial = m27813buildPartial();
            if (corsPolicyM27813buildPartial.isInitialized()) {
                return corsPolicyM27813buildPartial;
            }
            throw newUninitializedMessageException(corsPolicyM27813buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CorsPolicy m27813buildPartial() {
            CorsPolicy corsPolicy = new CorsPolicy(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.allowOriginStringMatch_ = Collections.unmodifiableList(this.allowOriginStringMatch_);
                    this.bitField0_ &= -2;
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
            if (this.enabledSpecifierCase_ == 9) {
                SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV32 = this.filterEnabledBuilder_;
                if (singleFieldBuilderV32 == null) {
                    corsPolicy.enabledSpecifier_ = this.enabledSpecifier_;
                } else {
                    corsPolicy.enabledSpecifier_ = singleFieldBuilderV32.build();
                }
            }
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV33 = this.shadowEnabledBuilder_;
            if (singleFieldBuilderV33 == null) {
                corsPolicy.shadowEnabled_ = this.shadowEnabled_;
            } else {
                corsPolicy.shadowEnabled_ = singleFieldBuilderV33.build();
            }
            corsPolicy.enabledSpecifierCase_ = this.enabledSpecifierCase_;
            onBuilt();
            return corsPolicy;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27829clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27841setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27819clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27822clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27843setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27809addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27834mergeFrom(Message message) {
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
            if (this.allowOriginStringMatchBuilder_ == null) {
                if (!corsPolicy.allowOriginStringMatch_.isEmpty()) {
                    if (this.allowOriginStringMatch_.isEmpty()) {
                        this.allowOriginStringMatch_ = corsPolicy.allowOriginStringMatch_;
                        this.bitField0_ &= -2;
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
                    this.bitField0_ &= -2;
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
            if (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$route$v3$CorsPolicy$EnabledSpecifierCase[corsPolicy.getEnabledSpecifierCase().ordinal()] == 1) {
                mergeFilterEnabled(corsPolicy.getFilterEnabled());
            }
            m27839mergeUnknownFields(corsPolicy.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicy.Builder m27835mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicy.access$1500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicy r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicy) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicy r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicy) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicy.Builder.m27835mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicy$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
        public EnabledSpecifierCase getEnabledSpecifierCase() {
            return EnabledSpecifierCase.forNumber(this.enabledSpecifierCase_);
        }

        public Builder clearEnabledSpecifier() {
            this.enabledSpecifierCase_ = 0;
            this.enabledSpecifier_ = null;
            onChanged();
            return this;
        }

        private void ensureAllowOriginStringMatchIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.allowOriginStringMatch_ = new ArrayList(this.allowOriginStringMatch_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
        public List<StringMatcher> getAllowOriginStringMatchList() {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.allowOriginStringMatch_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
        public int getAllowOriginStringMatchCount() {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.allowOriginStringMatch_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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
                this.allowOriginStringMatch_.set(i, builder.m33831build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m33831build());
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
                this.allowOriginStringMatch_.add(builder.m33831build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m33831build());
            }
            return this;
        }

        public Builder addAllowOriginStringMatch(int i, StringMatcher.Builder builder) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAllowOriginStringMatchIsMutable();
                this.allowOriginStringMatch_.add(i, builder.m33831build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m33831build());
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
                this.bitField0_ &= -2;
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
        public StringMatcherOrBuilder getAllowOriginStringMatchOrBuilder(int i) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.allowOriginStringMatchBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.allowOriginStringMatch_.get(i);
            }
            return (StringMatcherOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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
                this.allowOriginStringMatchBuilder_ = new RepeatedFieldBuilderV3<>(this.allowOriginStringMatch_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.allowOriginStringMatch_ = null;
            }
            return this.allowOriginStringMatchBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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
                this.enabledSpecifier_ = builder.m24021build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m24021build());
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
                    this.enabledSpecifier_ = RuntimeFractionalPercent.newBuilder((RuntimeFractionalPercent) this.enabledSpecifier_).mergeFrom(runtimeFractionalPercent).m24023buildPartial();
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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
                this.shadowEnabled_ = builder.m24021build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m24021build());
            }
            return this;
        }

        public Builder mergeShadowEnabled(RuntimeFractionalPercent runtimeFractionalPercent) {
            SingleFieldBuilderV3<RuntimeFractionalPercent, RuntimeFractionalPercent.Builder, RuntimeFractionalPercentOrBuilder> singleFieldBuilderV3 = this.shadowEnabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                RuntimeFractionalPercent runtimeFractionalPercent2 = this.shadowEnabled_;
                if (runtimeFractionalPercent2 != null) {
                    this.shadowEnabled_ = RuntimeFractionalPercent.newBuilder(runtimeFractionalPercent2).mergeFrom(runtimeFractionalPercent).m24023buildPartial();
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicyOrBuilder
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
        public final Builder m27845setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m27839mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicy$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$route$v3$CorsPolicy$EnabledSpecifierCase;

        static {
            int[] iArr = new int[EnabledSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$route$v3$CorsPolicy$EnabledSpecifierCase = iArr;
            try {
                iArr[EnabledSpecifierCase.FILTER_ENABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$CorsPolicy$EnabledSpecifierCase[EnabledSpecifierCase.ENABLEDSPECIFIER_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
