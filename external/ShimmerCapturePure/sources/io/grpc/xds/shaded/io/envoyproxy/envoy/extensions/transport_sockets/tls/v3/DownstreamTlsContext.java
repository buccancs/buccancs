package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
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
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SdsSecretConfig;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.TlsSessionTicketKeys;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class DownstreamTlsContext extends GeneratedMessageV3 implements DownstreamTlsContextOrBuilder {
    public static final int COMMON_TLS_CONTEXT_FIELD_NUMBER = 1;
    public static final int DISABLE_STATELESS_SESSION_RESUMPTION_FIELD_NUMBER = 7;
    public static final int REQUIRE_CLIENT_CERTIFICATE_FIELD_NUMBER = 2;
    public static final int REQUIRE_SNI_FIELD_NUMBER = 3;
    public static final int SESSION_TICKET_KEYS_FIELD_NUMBER = 4;
    public static final int SESSION_TICKET_KEYS_SDS_SECRET_CONFIG_FIELD_NUMBER = 5;
    public static final int SESSION_TIMEOUT_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    private static final DownstreamTlsContext DEFAULT_INSTANCE = new DownstreamTlsContext();
    private static final Parser<DownstreamTlsContext> PARSER = new AbstractParser<DownstreamTlsContext>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContext.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public DownstreamTlsContext m31838parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DownstreamTlsContext(codedInputStream, extensionRegistryLite);
        }
    };
    private CommonTlsContext commonTlsContext_;
    private byte memoizedIsInitialized;
    private BoolValue requireClientCertificate_;
    private BoolValue requireSni_;
    private int sessionTicketKeysTypeCase_;
    private Object sessionTicketKeysType_;
    private Duration sessionTimeout_;

    private DownstreamTlsContext(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.sessionTicketKeysTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private DownstreamTlsContext() {
        this.sessionTicketKeysTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private DownstreamTlsContext(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            CommonTlsContext commonTlsContext = this.commonTlsContext_;
                            builder = commonTlsContext != null ? commonTlsContext.m31652toBuilder() : null;
                            CommonTlsContext commonTlsContext2 = (CommonTlsContext) codedInputStream.readMessage(CommonTlsContext.parser(), extensionRegistryLite);
                            this.commonTlsContext_ = commonTlsContext2;
                            if (builder != null) {
                                builder.mergeFrom(commonTlsContext2);
                                this.commonTlsContext_ = builder.m31659buildPartial();
                            }
                        } else if (tag == 18) {
                            BoolValue boolValue = this.requireClientCertificate_;
                            builder = boolValue != null ? boolValue.toBuilder() : null;
                            BoolValue message = codedInputStream.readMessage(BoolValue.parser(), extensionRegistryLite);
                            this.requireClientCertificate_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.requireClientCertificate_ = builder.buildPartial();
                            }
                        } else if (tag == 26) {
                            BoolValue boolValue2 = this.requireSni_;
                            builder = boolValue2 != null ? boolValue2.toBuilder() : null;
                            BoolValue message2 = codedInputStream.readMessage(BoolValue.parser(), extensionRegistryLite);
                            this.requireSni_ = message2;
                            if (builder != null) {
                                builder.mergeFrom(message2);
                                this.requireSni_ = builder.buildPartial();
                            }
                        } else if (tag == 34) {
                            builder = this.sessionTicketKeysTypeCase_ == 4 ? ((TlsSessionTicketKeys) this.sessionTicketKeysType_).m32161toBuilder() : null;
                            MessageLite message3 = codedInputStream.readMessage(TlsSessionTicketKeys.parser(), extensionRegistryLite);
                            this.sessionTicketKeysType_ = message3;
                            if (builder != null) {
                                builder.mergeFrom((TlsSessionTicketKeys) message3);
                                this.sessionTicketKeysType_ = builder.m32168buildPartial();
                            }
                            this.sessionTicketKeysTypeCase_ = 4;
                        } else if (tag == 42) {
                            builder = this.sessionTicketKeysTypeCase_ == 5 ? ((SdsSecretConfig) this.sessionTicketKeysType_).m31974toBuilder() : null;
                            MessageLite message4 = codedInputStream.readMessage(SdsSecretConfig.parser(), extensionRegistryLite);
                            this.sessionTicketKeysType_ = message4;
                            if (builder != null) {
                                builder.mergeFrom((SdsSecretConfig) message4);
                                this.sessionTicketKeysType_ = builder.m31981buildPartial();
                            }
                            this.sessionTicketKeysTypeCase_ = 5;
                        } else if (tag == 50) {
                            Duration duration = this.sessionTimeout_;
                            builder = duration != null ? duration.toBuilder() : null;
                            Duration message5 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.sessionTimeout_ = message5;
                            if (builder != null) {
                                builder.mergeFrom(message5);
                                this.sessionTimeout_ = builder.buildPartial();
                            }
                        } else if (tag == 56) {
                            this.sessionTicketKeysTypeCase_ = 7;
                            this.sessionTicketKeysType_ = Boolean.valueOf(codedInputStream.readBool());
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

    public static DownstreamTlsContext getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DownstreamTlsContext> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_DownstreamTlsContext_descriptor;
    }

    public static DownstreamTlsContext parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DownstreamTlsContext) PARSER.parseFrom(byteBuffer);
    }

    public static DownstreamTlsContext parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DownstreamTlsContext) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DownstreamTlsContext parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DownstreamTlsContext) PARSER.parseFrom(byteString);
    }

    public static DownstreamTlsContext parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DownstreamTlsContext) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DownstreamTlsContext parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DownstreamTlsContext) PARSER.parseFrom(bArr);
    }

    public static DownstreamTlsContext parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DownstreamTlsContext) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DownstreamTlsContext parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DownstreamTlsContext parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DownstreamTlsContext parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DownstreamTlsContext parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DownstreamTlsContext parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DownstreamTlsContext parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m31836toBuilder();
    }

    public static Builder newBuilder(DownstreamTlsContext downstreamTlsContext) {
        return DEFAULT_INSTANCE.m31836toBuilder().mergeFrom(downstreamTlsContext);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public DownstreamTlsContext m31831getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<DownstreamTlsContext> getParserForType() {
        return PARSER;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public boolean hasCommonTlsContext() {
        return this.commonTlsContext_ != null;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public boolean hasRequireClientCertificate() {
        return this.requireClientCertificate_ != null;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public boolean hasRequireSni() {
        return this.requireSni_ != null;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public boolean hasSessionTicketKeys() {
        return this.sessionTicketKeysTypeCase_ == 4;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public boolean hasSessionTicketKeysSdsSecretConfig() {
        return this.sessionTicketKeysTypeCase_ == 5;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public boolean hasSessionTimeout() {
        return this.sessionTimeout_ != null;
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
        return new DownstreamTlsContext();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_DownstreamTlsContext_fieldAccessorTable.ensureFieldAccessorsInitialized(DownstreamTlsContext.class, Builder.class);
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public SessionTicketKeysTypeCase getSessionTicketKeysTypeCase() {
        return SessionTicketKeysTypeCase.forNumber(this.sessionTicketKeysTypeCase_);
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public CommonTlsContext getCommonTlsContext() {
        CommonTlsContext commonTlsContext = this.commonTlsContext_;
        return commonTlsContext == null ? CommonTlsContext.getDefaultInstance() : commonTlsContext;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public CommonTlsContextOrBuilder getCommonTlsContextOrBuilder() {
        return getCommonTlsContext();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public BoolValue getRequireClientCertificate() {
        BoolValue boolValue = this.requireClientCertificate_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public BoolValueOrBuilder getRequireClientCertificateOrBuilder() {
        return getRequireClientCertificate();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public BoolValue getRequireSni() {
        BoolValue boolValue = this.requireSni_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public BoolValueOrBuilder getRequireSniOrBuilder() {
        return getRequireSni();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public TlsSessionTicketKeys getSessionTicketKeys() {
        if (this.sessionTicketKeysTypeCase_ == 4) {
            return (TlsSessionTicketKeys) this.sessionTicketKeysType_;
        }
        return TlsSessionTicketKeys.getDefaultInstance();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public TlsSessionTicketKeysOrBuilder getSessionTicketKeysOrBuilder() {
        if (this.sessionTicketKeysTypeCase_ == 4) {
            return (TlsSessionTicketKeys) this.sessionTicketKeysType_;
        }
        return TlsSessionTicketKeys.getDefaultInstance();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public SdsSecretConfig getSessionTicketKeysSdsSecretConfig() {
        if (this.sessionTicketKeysTypeCase_ == 5) {
            return (SdsSecretConfig) this.sessionTicketKeysType_;
        }
        return SdsSecretConfig.getDefaultInstance();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public SdsSecretConfigOrBuilder getSessionTicketKeysSdsSecretConfigOrBuilder() {
        if (this.sessionTicketKeysTypeCase_ == 5) {
            return (SdsSecretConfig) this.sessionTicketKeysType_;
        }
        return SdsSecretConfig.getDefaultInstance();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public boolean getDisableStatelessSessionResumption() {
        if (this.sessionTicketKeysTypeCase_ == 7) {
            return ((Boolean) this.sessionTicketKeysType_).booleanValue();
        }
        return false;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public Duration getSessionTimeout() {
        Duration duration = this.sessionTimeout_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
    public DurationOrBuilder getSessionTimeoutOrBuilder() {
        return getSessionTimeout();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.commonTlsContext_ != null) {
            codedOutputStream.writeMessage(1, getCommonTlsContext());
        }
        if (this.requireClientCertificate_ != null) {
            codedOutputStream.writeMessage(2, getRequireClientCertificate());
        }
        if (this.requireSni_ != null) {
            codedOutputStream.writeMessage(3, getRequireSni());
        }
        if (this.sessionTicketKeysTypeCase_ == 4) {
            codedOutputStream.writeMessage(4, (TlsSessionTicketKeys) this.sessionTicketKeysType_);
        }
        if (this.sessionTicketKeysTypeCase_ == 5) {
            codedOutputStream.writeMessage(5, (SdsSecretConfig) this.sessionTicketKeysType_);
        }
        if (this.sessionTimeout_ != null) {
            codedOutputStream.writeMessage(6, getSessionTimeout());
        }
        if (this.sessionTicketKeysTypeCase_ == 7) {
            codedOutputStream.writeBool(7, ((Boolean) this.sessionTicketKeysType_).booleanValue());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.commonTlsContext_ != null ? CodedOutputStream.computeMessageSize(1, getCommonTlsContext()) : 0;
        if (this.requireClientCertificate_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getRequireClientCertificate());
        }
        if (this.requireSni_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getRequireSni());
        }
        if (this.sessionTicketKeysTypeCase_ == 4) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(4, (TlsSessionTicketKeys) this.sessionTicketKeysType_);
        }
        if (this.sessionTicketKeysTypeCase_ == 5) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(5, (SdsSecretConfig) this.sessionTicketKeysType_);
        }
        if (this.sessionTimeout_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(6, getSessionTimeout());
        }
        if (this.sessionTicketKeysTypeCase_ == 7) {
            iComputeMessageSize += CodedOutputStream.computeBoolSize(7, ((Boolean) this.sessionTicketKeysType_).booleanValue());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DownstreamTlsContext)) {
            return super.equals(obj);
        }
        DownstreamTlsContext downstreamTlsContext = (DownstreamTlsContext) obj;
        if (hasCommonTlsContext() != downstreamTlsContext.hasCommonTlsContext()) {
            return false;
        }
        if ((hasCommonTlsContext() && !getCommonTlsContext().equals(downstreamTlsContext.getCommonTlsContext())) || hasRequireClientCertificate() != downstreamTlsContext.hasRequireClientCertificate()) {
            return false;
        }
        if ((hasRequireClientCertificate() && !getRequireClientCertificate().equals(downstreamTlsContext.getRequireClientCertificate())) || hasRequireSni() != downstreamTlsContext.hasRequireSni()) {
            return false;
        }
        if ((hasRequireSni() && !getRequireSni().equals(downstreamTlsContext.getRequireSni())) || hasSessionTimeout() != downstreamTlsContext.hasSessionTimeout()) {
            return false;
        }
        if ((hasSessionTimeout() && !getSessionTimeout().equals(downstreamTlsContext.getSessionTimeout())) || !getSessionTicketKeysTypeCase().equals(downstreamTlsContext.getSessionTicketKeysTypeCase())) {
            return false;
        }
        int i = this.sessionTicketKeysTypeCase_;
        if (i != 4) {
            if (i == 5) {
                if (!getSessionTicketKeysSdsSecretConfig().equals(downstreamTlsContext.getSessionTicketKeysSdsSecretConfig())) {
                    return false;
                }
            } else if (i == 7 && getDisableStatelessSessionResumption() != downstreamTlsContext.getDisableStatelessSessionResumption()) {
                return false;
            }
        } else if (!getSessionTicketKeys().equals(downstreamTlsContext.getSessionTicketKeys())) {
            return false;
        }
        return this.unknownFields.equals(downstreamTlsContext.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        if (hasCommonTlsContext()) {
            iHashCode2 = (((iHashCode2 * 37) + 1) * 53) + getCommonTlsContext().hashCode();
        }
        if (hasRequireClientCertificate()) {
            iHashCode2 = (((iHashCode2 * 37) + 2) * 53) + getRequireClientCertificate().hashCode();
        }
        if (hasRequireSni()) {
            iHashCode2 = (((iHashCode2 * 37) + 3) * 53) + getRequireSni().hashCode();
        }
        if (hasSessionTimeout()) {
            iHashCode2 = (((iHashCode2 * 37) + 6) * 53) + getSessionTimeout().hashCode();
        }
        int i2 = this.sessionTicketKeysTypeCase_;
        if (i2 == 4) {
            i = ((iHashCode2 * 37) + 4) * 53;
            iHashCode = getSessionTicketKeys().hashCode();
        } else if (i2 == 5) {
            i = ((iHashCode2 * 37) + 5) * 53;
            iHashCode = getSessionTicketKeysSdsSecretConfig().hashCode();
        } else {
            if (i2 == 7) {
                i = ((iHashCode2 * 37) + 7) * 53;
                iHashCode = Internal.hashBoolean(getDisableStatelessSessionResumption());
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
    public Builder m31833newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m31836toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum SessionTicketKeysTypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        SESSION_TICKET_KEYS(4),
        SESSION_TICKET_KEYS_SDS_SECRET_CONFIG(5),
        DISABLE_STATELESS_SESSION_RESUMPTION(7),
        SESSIONTICKETKEYSTYPE_NOT_SET(0);

        private final int value;

        SessionTicketKeysTypeCase(int i) {
            this.value = i;
        }

        public static SessionTicketKeysTypeCase forNumber(int i) {
            if (i == 0) {
                return SESSIONTICKETKEYSTYPE_NOT_SET;
            }
            if (i == 7) {
                return DISABLE_STATELESS_SESSION_RESUMPTION;
            }
            if (i == 4) {
                return SESSION_TICKET_KEYS;
            }
            if (i != 5) {
                return null;
            }
            return SESSION_TICKET_KEYS_SDS_SECRET_CONFIG;
        }

        @Deprecated
        public static SessionTicketKeysTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DownstreamTlsContextOrBuilder {
        private SingleFieldBuilderV3<CommonTlsContext, CommonTlsContext.Builder, CommonTlsContextOrBuilder> commonTlsContextBuilder_;
        private CommonTlsContext commonTlsContext_;
        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> requireClientCertificateBuilder_;
        private BoolValue requireClientCertificate_;
        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> requireSniBuilder_;
        private BoolValue requireSni_;
        private SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> sessionTicketKeysBuilder_;
        private SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> sessionTicketKeysSdsSecretConfigBuilder_;
        private int sessionTicketKeysTypeCase_;
        private Object sessionTicketKeysType_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> sessionTimeoutBuilder_;
        private Duration sessionTimeout_;

        private Builder() {
            this.sessionTicketKeysTypeCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.sessionTicketKeysTypeCase_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_DownstreamTlsContext_descriptor;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public boolean hasCommonTlsContext() {
            return (this.commonTlsContextBuilder_ == null && this.commonTlsContext_ == null) ? false : true;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public boolean hasRequireClientCertificate() {
            return (this.requireClientCertificateBuilder_ == null && this.requireClientCertificate_ == null) ? false : true;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public boolean hasRequireSni() {
            return (this.requireSniBuilder_ == null && this.requireSni_ == null) ? false : true;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public boolean hasSessionTicketKeys() {
            return this.sessionTicketKeysTypeCase_ == 4;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public boolean hasSessionTicketKeysSdsSecretConfig() {
            return this.sessionTicketKeysTypeCase_ == 5;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public boolean hasSessionTimeout() {
            return (this.sessionTimeoutBuilder_ == null && this.sessionTimeout_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_DownstreamTlsContext_fieldAccessorTable.ensureFieldAccessorsInitialized(DownstreamTlsContext.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = DownstreamTlsContext.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31847clear() {
            super.clear();
            if (this.commonTlsContextBuilder_ == null) {
                this.commonTlsContext_ = null;
            } else {
                this.commonTlsContext_ = null;
                this.commonTlsContextBuilder_ = null;
            }
            if (this.requireClientCertificateBuilder_ == null) {
                this.requireClientCertificate_ = null;
            } else {
                this.requireClientCertificate_ = null;
                this.requireClientCertificateBuilder_ = null;
            }
            if (this.requireSniBuilder_ == null) {
                this.requireSni_ = null;
            } else {
                this.requireSni_ = null;
                this.requireSniBuilder_ = null;
            }
            if (this.sessionTimeoutBuilder_ == null) {
                this.sessionTimeout_ = null;
            } else {
                this.sessionTimeout_ = null;
                this.sessionTimeoutBuilder_ = null;
            }
            this.sessionTicketKeysTypeCase_ = 0;
            this.sessionTicketKeysType_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_DownstreamTlsContext_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DownstreamTlsContext m31860getDefaultInstanceForType() {
            return DownstreamTlsContext.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DownstreamTlsContext m31841build() throws UninitializedMessageException {
            DownstreamTlsContext downstreamTlsContextM31843buildPartial = m31843buildPartial();
            if (downstreamTlsContextM31843buildPartial.isInitialized()) {
                return downstreamTlsContextM31843buildPartial;
            }
            throw newUninitializedMessageException(downstreamTlsContextM31843buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DownstreamTlsContext m31843buildPartial() {
            DownstreamTlsContext downstreamTlsContext = new DownstreamTlsContext(this);
            SingleFieldBuilderV3<CommonTlsContext, CommonTlsContext.Builder, CommonTlsContextOrBuilder> singleFieldBuilderV3 = this.commonTlsContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                downstreamTlsContext.commonTlsContext_ = this.commonTlsContext_;
            } else {
                downstreamTlsContext.commonTlsContext_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV32 = this.requireClientCertificateBuilder_;
            if (singleFieldBuilderV32 == null) {
                downstreamTlsContext.requireClientCertificate_ = this.requireClientCertificate_;
            } else {
                downstreamTlsContext.requireClientCertificate_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV33 = this.requireSniBuilder_;
            if (singleFieldBuilderV33 == null) {
                downstreamTlsContext.requireSni_ = this.requireSni_;
            } else {
                downstreamTlsContext.requireSni_ = singleFieldBuilderV33.build();
            }
            if (this.sessionTicketKeysTypeCase_ == 4) {
                SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> singleFieldBuilderV34 = this.sessionTicketKeysBuilder_;
                if (singleFieldBuilderV34 == null) {
                    downstreamTlsContext.sessionTicketKeysType_ = this.sessionTicketKeysType_;
                } else {
                    downstreamTlsContext.sessionTicketKeysType_ = singleFieldBuilderV34.build();
                }
            }
            if (this.sessionTicketKeysTypeCase_ == 5) {
                SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV35 = this.sessionTicketKeysSdsSecretConfigBuilder_;
                if (singleFieldBuilderV35 == null) {
                    downstreamTlsContext.sessionTicketKeysType_ = this.sessionTicketKeysType_;
                } else {
                    downstreamTlsContext.sessionTicketKeysType_ = singleFieldBuilderV35.build();
                }
            }
            if (this.sessionTicketKeysTypeCase_ == 7) {
                downstreamTlsContext.sessionTicketKeysType_ = this.sessionTicketKeysType_;
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV36 = this.sessionTimeoutBuilder_;
            if (singleFieldBuilderV36 == null) {
                downstreamTlsContext.sessionTimeout_ = this.sessionTimeout_;
            } else {
                downstreamTlsContext.sessionTimeout_ = singleFieldBuilderV36.build();
            }
            downstreamTlsContext.sessionTicketKeysTypeCase_ = this.sessionTicketKeysTypeCase_;
            onBuilt();
            return downstreamTlsContext;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31859clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31871setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31849clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31852clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31873setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31839addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31864mergeFrom(Message message) {
            if (message instanceof DownstreamTlsContext) {
                return mergeFrom((DownstreamTlsContext) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DownstreamTlsContext downstreamTlsContext) {
            if (downstreamTlsContext == DownstreamTlsContext.getDefaultInstance()) {
                return this;
            }
            if (downstreamTlsContext.hasCommonTlsContext()) {
                mergeCommonTlsContext(downstreamTlsContext.getCommonTlsContext());
            }
            if (downstreamTlsContext.hasRequireClientCertificate()) {
                mergeRequireClientCertificate(downstreamTlsContext.getRequireClientCertificate());
            }
            if (downstreamTlsContext.hasRequireSni()) {
                mergeRequireSni(downstreamTlsContext.getRequireSni());
            }
            if (downstreamTlsContext.hasSessionTimeout()) {
                mergeSessionTimeout(downstreamTlsContext.getSessionTimeout());
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$DownstreamTlsContext$SessionTicketKeysTypeCase[downstreamTlsContext.getSessionTicketKeysTypeCase().ordinal()];
            if (i == 1) {
                mergeSessionTicketKeys(downstreamTlsContext.getSessionTicketKeys());
            } else if (i == 2) {
                mergeSessionTicketKeysSdsSecretConfig(downstreamTlsContext.getSessionTicketKeysSdsSecretConfig());
            } else if (i == 3) {
                setDisableStatelessSessionResumption(downstreamTlsContext.getDisableStatelessSessionResumption());
            }
            m31869mergeUnknownFields(downstreamTlsContext.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContext.Builder m31865mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContext.access$1100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContext r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContext) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContext r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContext) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContext.Builder.m31865mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContext$Builder");
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public SessionTicketKeysTypeCase getSessionTicketKeysTypeCase() {
            return SessionTicketKeysTypeCase.forNumber(this.sessionTicketKeysTypeCase_);
        }

        public Builder clearSessionTicketKeysType() {
            this.sessionTicketKeysTypeCase_ = 0;
            this.sessionTicketKeysType_ = null;
            onChanged();
            return this;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public CommonTlsContext getCommonTlsContext() {
            SingleFieldBuilderV3<CommonTlsContext, CommonTlsContext.Builder, CommonTlsContextOrBuilder> singleFieldBuilderV3 = this.commonTlsContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            CommonTlsContext commonTlsContext = this.commonTlsContext_;
            return commonTlsContext == null ? CommonTlsContext.getDefaultInstance() : commonTlsContext;
        }

        public Builder setCommonTlsContext(CommonTlsContext commonTlsContext) {
            SingleFieldBuilderV3<CommonTlsContext, CommonTlsContext.Builder, CommonTlsContextOrBuilder> singleFieldBuilderV3 = this.commonTlsContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                commonTlsContext.getClass();
                this.commonTlsContext_ = commonTlsContext;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(commonTlsContext);
            }
            return this;
        }

        public Builder setCommonTlsContext(CommonTlsContext.Builder builder) {
            SingleFieldBuilderV3<CommonTlsContext, CommonTlsContext.Builder, CommonTlsContextOrBuilder> singleFieldBuilderV3 = this.commonTlsContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.commonTlsContext_ = builder.m31657build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m31657build());
            }
            return this;
        }

        public Builder mergeCommonTlsContext(CommonTlsContext commonTlsContext) {
            SingleFieldBuilderV3<CommonTlsContext, CommonTlsContext.Builder, CommonTlsContextOrBuilder> singleFieldBuilderV3 = this.commonTlsContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                CommonTlsContext commonTlsContext2 = this.commonTlsContext_;
                if (commonTlsContext2 != null) {
                    this.commonTlsContext_ = CommonTlsContext.newBuilder(commonTlsContext2).mergeFrom(commonTlsContext).m31659buildPartial();
                } else {
                    this.commonTlsContext_ = commonTlsContext;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(commonTlsContext);
            }
            return this;
        }

        public Builder clearCommonTlsContext() {
            if (this.commonTlsContextBuilder_ == null) {
                this.commonTlsContext_ = null;
                onChanged();
            } else {
                this.commonTlsContext_ = null;
                this.commonTlsContextBuilder_ = null;
            }
            return this;
        }

        public CommonTlsContext.Builder getCommonTlsContextBuilder() {
            onChanged();
            return getCommonTlsContextFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public CommonTlsContextOrBuilder getCommonTlsContextOrBuilder() {
            SingleFieldBuilderV3<CommonTlsContext, CommonTlsContext.Builder, CommonTlsContextOrBuilder> singleFieldBuilderV3 = this.commonTlsContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (CommonTlsContextOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            CommonTlsContext commonTlsContext = this.commonTlsContext_;
            return commonTlsContext == null ? CommonTlsContext.getDefaultInstance() : commonTlsContext;
        }

        private SingleFieldBuilderV3<CommonTlsContext, CommonTlsContext.Builder, CommonTlsContextOrBuilder> getCommonTlsContextFieldBuilder() {
            if (this.commonTlsContextBuilder_ == null) {
                this.commonTlsContextBuilder_ = new SingleFieldBuilderV3<>(getCommonTlsContext(), getParentForChildren(), isClean());
                this.commonTlsContext_ = null;
            }
            return this.commonTlsContextBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public BoolValue getRequireClientCertificate() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.requireClientCertificateBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            BoolValue boolValue = this.requireClientCertificate_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        public Builder setRequireClientCertificate(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.requireClientCertificateBuilder_;
            if (singleFieldBuilderV3 == null) {
                boolValue.getClass();
                this.requireClientCertificate_ = boolValue;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(boolValue);
            }
            return this;
        }

        public Builder setRequireClientCertificate(BoolValue.Builder builder) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.requireClientCertificateBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.requireClientCertificate_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRequireClientCertificate(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.requireClientCertificateBuilder_;
            if (singleFieldBuilderV3 == null) {
                BoolValue boolValue2 = this.requireClientCertificate_;
                if (boolValue2 != null) {
                    this.requireClientCertificate_ = BoolValue.newBuilder(boolValue2).mergeFrom(boolValue).buildPartial();
                } else {
                    this.requireClientCertificate_ = boolValue;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(boolValue);
            }
            return this;
        }

        public Builder clearRequireClientCertificate() {
            if (this.requireClientCertificateBuilder_ == null) {
                this.requireClientCertificate_ = null;
                onChanged();
            } else {
                this.requireClientCertificate_ = null;
                this.requireClientCertificateBuilder_ = null;
            }
            return this;
        }

        public BoolValue.Builder getRequireClientCertificateBuilder() {
            onChanged();
            return getRequireClientCertificateFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public BoolValueOrBuilder getRequireClientCertificateOrBuilder() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.requireClientCertificateBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            BoolValue boolValue = this.requireClientCertificate_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> getRequireClientCertificateFieldBuilder() {
            if (this.requireClientCertificateBuilder_ == null) {
                this.requireClientCertificateBuilder_ = new SingleFieldBuilderV3<>(getRequireClientCertificate(), getParentForChildren(), isClean());
                this.requireClientCertificate_ = null;
            }
            return this.requireClientCertificateBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public BoolValue getRequireSni() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.requireSniBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            BoolValue boolValue = this.requireSni_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        public Builder setRequireSni(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.requireSniBuilder_;
            if (singleFieldBuilderV3 == null) {
                boolValue.getClass();
                this.requireSni_ = boolValue;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(boolValue);
            }
            return this;
        }

        public Builder setRequireSni(BoolValue.Builder builder) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.requireSniBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.requireSni_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRequireSni(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.requireSniBuilder_;
            if (singleFieldBuilderV3 == null) {
                BoolValue boolValue2 = this.requireSni_;
                if (boolValue2 != null) {
                    this.requireSni_ = BoolValue.newBuilder(boolValue2).mergeFrom(boolValue).buildPartial();
                } else {
                    this.requireSni_ = boolValue;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(boolValue);
            }
            return this;
        }

        public Builder clearRequireSni() {
            if (this.requireSniBuilder_ == null) {
                this.requireSni_ = null;
                onChanged();
            } else {
                this.requireSni_ = null;
                this.requireSniBuilder_ = null;
            }
            return this;
        }

        public BoolValue.Builder getRequireSniBuilder() {
            onChanged();
            return getRequireSniFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public BoolValueOrBuilder getRequireSniOrBuilder() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.requireSniBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            BoolValue boolValue = this.requireSni_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> getRequireSniFieldBuilder() {
            if (this.requireSniBuilder_ == null) {
                this.requireSniBuilder_ = new SingleFieldBuilderV3<>(getRequireSni(), getParentForChildren(), isClean());
                this.requireSni_ = null;
            }
            return this.requireSniBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public TlsSessionTicketKeys getSessionTicketKeys() {
            SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.sessionTicketKeysTypeCase_ == 4) {
                    return (TlsSessionTicketKeys) this.sessionTicketKeysType_;
                }
                return TlsSessionTicketKeys.getDefaultInstance();
            }
            if (this.sessionTicketKeysTypeCase_ == 4) {
                return singleFieldBuilderV3.getMessage();
            }
            return TlsSessionTicketKeys.getDefaultInstance();
        }

        public Builder setSessionTicketKeys(TlsSessionTicketKeys tlsSessionTicketKeys) {
            SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysBuilder_;
            if (singleFieldBuilderV3 == null) {
                tlsSessionTicketKeys.getClass();
                this.sessionTicketKeysType_ = tlsSessionTicketKeys;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(tlsSessionTicketKeys);
            }
            this.sessionTicketKeysTypeCase_ = 4;
            return this;
        }

        public Builder setSessionTicketKeys(TlsSessionTicketKeys.Builder builder) {
            SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sessionTicketKeysType_ = builder.m32166build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m32166build());
            }
            this.sessionTicketKeysTypeCase_ = 4;
            return this;
        }

        public Builder mergeSessionTicketKeys(TlsSessionTicketKeys tlsSessionTicketKeys) {
            SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.sessionTicketKeysTypeCase_ != 4 || this.sessionTicketKeysType_ == TlsSessionTicketKeys.getDefaultInstance()) {
                    this.sessionTicketKeysType_ = tlsSessionTicketKeys;
                } else {
                    this.sessionTicketKeysType_ = TlsSessionTicketKeys.newBuilder((TlsSessionTicketKeys) this.sessionTicketKeysType_).mergeFrom(tlsSessionTicketKeys).m32168buildPartial();
                }
                onChanged();
            } else {
                if (this.sessionTicketKeysTypeCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(tlsSessionTicketKeys);
                }
                this.sessionTicketKeysBuilder_.setMessage(tlsSessionTicketKeys);
            }
            this.sessionTicketKeysTypeCase_ = 4;
            return this;
        }

        public Builder clearSessionTicketKeys() {
            SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.sessionTicketKeysTypeCase_ == 4) {
                    this.sessionTicketKeysTypeCase_ = 0;
                    this.sessionTicketKeysType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.sessionTicketKeysTypeCase_ == 4) {
                this.sessionTicketKeysTypeCase_ = 0;
                this.sessionTicketKeysType_ = null;
                onChanged();
            }
            return this;
        }

        public TlsSessionTicketKeys.Builder getSessionTicketKeysBuilder() {
            return getSessionTicketKeysFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public TlsSessionTicketKeysOrBuilder getSessionTicketKeysOrBuilder() {
            SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> singleFieldBuilderV3;
            int i = this.sessionTicketKeysTypeCase_;
            if (i == 4 && (singleFieldBuilderV3 = this.sessionTicketKeysBuilder_) != null) {
                return (TlsSessionTicketKeysOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 4) {
                return (TlsSessionTicketKeys) this.sessionTicketKeysType_;
            }
            return TlsSessionTicketKeys.getDefaultInstance();
        }

        private SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> getSessionTicketKeysFieldBuilder() {
            if (this.sessionTicketKeysBuilder_ == null) {
                if (this.sessionTicketKeysTypeCase_ != 4) {
                    this.sessionTicketKeysType_ = TlsSessionTicketKeys.getDefaultInstance();
                }
                this.sessionTicketKeysBuilder_ = new SingleFieldBuilderV3<>((TlsSessionTicketKeys) this.sessionTicketKeysType_, getParentForChildren(), isClean());
                this.sessionTicketKeysType_ = null;
            }
            this.sessionTicketKeysTypeCase_ = 4;
            onChanged();
            return this.sessionTicketKeysBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public SdsSecretConfig getSessionTicketKeysSdsSecretConfig() {
            SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysSdsSecretConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.sessionTicketKeysTypeCase_ == 5) {
                    return (SdsSecretConfig) this.sessionTicketKeysType_;
                }
                return SdsSecretConfig.getDefaultInstance();
            }
            if (this.sessionTicketKeysTypeCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return SdsSecretConfig.getDefaultInstance();
        }

        public Builder setSessionTicketKeysSdsSecretConfig(SdsSecretConfig sdsSecretConfig) {
            SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysSdsSecretConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                sdsSecretConfig.getClass();
                this.sessionTicketKeysType_ = sdsSecretConfig;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(sdsSecretConfig);
            }
            this.sessionTicketKeysTypeCase_ = 5;
            return this;
        }

        public Builder setSessionTicketKeysSdsSecretConfig(SdsSecretConfig.Builder builder) {
            SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysSdsSecretConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sessionTicketKeysType_ = builder.m31979build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m31979build());
            }
            this.sessionTicketKeysTypeCase_ = 5;
            return this;
        }

        public Builder mergeSessionTicketKeysSdsSecretConfig(SdsSecretConfig sdsSecretConfig) {
            SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysSdsSecretConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.sessionTicketKeysTypeCase_ != 5 || this.sessionTicketKeysType_ == SdsSecretConfig.getDefaultInstance()) {
                    this.sessionTicketKeysType_ = sdsSecretConfig;
                } else {
                    this.sessionTicketKeysType_ = SdsSecretConfig.newBuilder((SdsSecretConfig) this.sessionTicketKeysType_).mergeFrom(sdsSecretConfig).m31981buildPartial();
                }
                onChanged();
            } else {
                if (this.sessionTicketKeysTypeCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(sdsSecretConfig);
                }
                this.sessionTicketKeysSdsSecretConfigBuilder_.setMessage(sdsSecretConfig);
            }
            this.sessionTicketKeysTypeCase_ = 5;
            return this;
        }

        public Builder clearSessionTicketKeysSdsSecretConfig() {
            SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysSdsSecretConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.sessionTicketKeysTypeCase_ == 5) {
                    this.sessionTicketKeysTypeCase_ = 0;
                    this.sessionTicketKeysType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.sessionTicketKeysTypeCase_ == 5) {
                this.sessionTicketKeysTypeCase_ = 0;
                this.sessionTicketKeysType_ = null;
                onChanged();
            }
            return this;
        }

        public SdsSecretConfig.Builder getSessionTicketKeysSdsSecretConfigBuilder() {
            return getSessionTicketKeysSdsSecretConfigFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public SdsSecretConfigOrBuilder getSessionTicketKeysSdsSecretConfigOrBuilder() {
            SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3;
            int i = this.sessionTicketKeysTypeCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.sessionTicketKeysSdsSecretConfigBuilder_) != null) {
                return (SdsSecretConfigOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (SdsSecretConfig) this.sessionTicketKeysType_;
            }
            return SdsSecretConfig.getDefaultInstance();
        }

        private SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> getSessionTicketKeysSdsSecretConfigFieldBuilder() {
            if (this.sessionTicketKeysSdsSecretConfigBuilder_ == null) {
                if (this.sessionTicketKeysTypeCase_ != 5) {
                    this.sessionTicketKeysType_ = SdsSecretConfig.getDefaultInstance();
                }
                this.sessionTicketKeysSdsSecretConfigBuilder_ = new SingleFieldBuilderV3<>((SdsSecretConfig) this.sessionTicketKeysType_, getParentForChildren(), isClean());
                this.sessionTicketKeysType_ = null;
            }
            this.sessionTicketKeysTypeCase_ = 5;
            onChanged();
            return this.sessionTicketKeysSdsSecretConfigBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public boolean getDisableStatelessSessionResumption() {
            if (this.sessionTicketKeysTypeCase_ == 7) {
                return ((Boolean) this.sessionTicketKeysType_).booleanValue();
            }
            return false;
        }

        public Builder setDisableStatelessSessionResumption(boolean z) {
            this.sessionTicketKeysTypeCase_ = 7;
            this.sessionTicketKeysType_ = Boolean.valueOf(z);
            onChanged();
            return this;
        }

        public Builder clearDisableStatelessSessionResumption() {
            if (this.sessionTicketKeysTypeCase_ == 7) {
                this.sessionTicketKeysTypeCase_ = 0;
                this.sessionTicketKeysType_ = null;
                onChanged();
            }
            return this;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public Duration getSessionTimeout() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.sessionTimeoutBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.sessionTimeout_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setSessionTimeout(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.sessionTimeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.sessionTimeout_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setSessionTimeout(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.sessionTimeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sessionTimeout_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSessionTimeout(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.sessionTimeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.sessionTimeout_;
                if (duration2 != null) {
                    this.sessionTimeout_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.sessionTimeout_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearSessionTimeout() {
            if (this.sessionTimeoutBuilder_ == null) {
                this.sessionTimeout_ = null;
                onChanged();
            } else {
                this.sessionTimeout_ = null;
                this.sessionTimeoutBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getSessionTimeoutBuilder() {
            onChanged();
            return getSessionTimeoutFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContextOrBuilder
        public DurationOrBuilder getSessionTimeoutOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.sessionTimeoutBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.sessionTimeout_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getSessionTimeoutFieldBuilder() {
            if (this.sessionTimeoutBuilder_ == null) {
                this.sessionTimeoutBuilder_ = new SingleFieldBuilderV3<>(getSessionTimeout(), getParentForChildren(), isClean());
                this.sessionTimeout_ = null;
            }
            return this.sessionTimeoutBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m31875setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m31869mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.DownstreamTlsContext$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$DownstreamTlsContext$SessionTicketKeysTypeCase;

        static {
            int[] iArr = new int[SessionTicketKeysTypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$DownstreamTlsContext$SessionTicketKeysTypeCase = iArr;
            try {
                iArr[SessionTicketKeysTypeCase.SESSION_TICKET_KEYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$DownstreamTlsContext$SessionTicketKeysTypeCase[SessionTicketKeysTypeCase.SESSION_TICKET_KEYS_SDS_SECRET_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$DownstreamTlsContext$SessionTicketKeysTypeCase[SessionTicketKeysTypeCase.DISABLE_STATELESS_SESSION_RESUMPTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$DownstreamTlsContext$SessionTicketKeysTypeCase[SessionTicketKeysTypeCase.SESSIONTICKETKEYSTYPE_NOT_SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
