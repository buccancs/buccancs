package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3;

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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfig;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfigOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CertificateValidationContext;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SdsSecretConfig;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.TlsCertificate;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.TlsParameters;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class CommonTlsContext extends GeneratedMessageV3 implements CommonTlsContextOrBuilder {
    public static final int ALPN_PROTOCOLS_FIELD_NUMBER = 4;
    public static final int COMBINED_VALIDATION_CONTEXT_FIELD_NUMBER = 8;
    public static final int TLS_CERTIFICATES_FIELD_NUMBER = 2;
    public static final int TLS_CERTIFICATE_CERTIFICATE_PROVIDER_FIELD_NUMBER = 9;
    public static final int TLS_CERTIFICATE_CERTIFICATE_PROVIDER_INSTANCE_FIELD_NUMBER = 11;
    public static final int TLS_CERTIFICATE_SDS_SECRET_CONFIGS_FIELD_NUMBER = 6;
    public static final int TLS_PARAMS_FIELD_NUMBER = 1;
    public static final int VALIDATION_CONTEXT_CERTIFICATE_PROVIDER_FIELD_NUMBER = 10;
    public static final int VALIDATION_CONTEXT_CERTIFICATE_PROVIDER_INSTANCE_FIELD_NUMBER = 12;
    public static final int VALIDATION_CONTEXT_FIELD_NUMBER = 3;
    public static final int VALIDATION_CONTEXT_SDS_SECRET_CONFIG_FIELD_NUMBER = 7;
    private static final CommonTlsContext DEFAULT_INSTANCE = new CommonTlsContext();
    private static final Parser<CommonTlsContext> PARSER = new AbstractParser<CommonTlsContext>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public CommonTlsContext m31654parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CommonTlsContext(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private LazyStringList alpnProtocols_;
    private byte memoizedIsInitialized;
    private CertificateProviderInstance tlsCertificateCertificateProviderInstance_;
    private CertificateProvider tlsCertificateCertificateProvider_;
    private List<SdsSecretConfig> tlsCertificateSdsSecretConfigs_;
    private List<TlsCertificate> tlsCertificates_;
    private TlsParameters tlsParams_;
    private int validationContextTypeCase_;
    private Object validationContextType_;

    private CommonTlsContext(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.validationContextTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private CommonTlsContext() {
        this.validationContextTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.tlsCertificates_ = Collections.emptyList();
        this.tlsCertificateSdsSecretConfigs_ = Collections.emptyList();
        this.alpnProtocols_ = LazyStringArrayList.EMPTY;
    }

    private CommonTlsContext(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            TlsParameters tlsParameters = this.tlsParams_;
                            TlsParameters.Builder builderM32114toBuilder = tlsParameters != null ? tlsParameters.m32114toBuilder() : null;
                            TlsParameters tlsParameters2 = (TlsParameters) codedInputStream.readMessage(TlsParameters.parser(), extensionRegistryLite);
                            this.tlsParams_ = tlsParameters2;
                            if (builderM32114toBuilder != null) {
                                builderM32114toBuilder.mergeFrom(tlsParameters2);
                                this.tlsParams_ = builderM32114toBuilder.m32121buildPartial();
                            }
                        case 18:
                            if ((i & 1) == 0) {
                                this.tlsCertificates_ = new ArrayList();
                                i |= 1;
                            }
                            this.tlsCertificates_.add(codedInputStream.readMessage(TlsCertificate.parser(), extensionRegistryLite));
                        case 26:
                            CertificateValidationContext.Builder builderM31604toBuilder = this.validationContextTypeCase_ == 3 ? ((CertificateValidationContext) this.validationContextType_).m31604toBuilder() : null;
                            MessageLite message = codedInputStream.readMessage(CertificateValidationContext.parser(), extensionRegistryLite);
                            this.validationContextType_ = message;
                            if (builderM31604toBuilder != null) {
                                builderM31604toBuilder.mergeFrom((CertificateValidationContext) message);
                                this.validationContextType_ = builderM31604toBuilder.m31611buildPartial();
                            }
                            this.validationContextTypeCase_ = 3;
                        case 34:
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if ((i & 4) == 0) {
                                this.alpnProtocols_ = new LazyStringArrayList();
                                i |= 4;
                            }
                            this.alpnProtocols_.add(stringRequireUtf8);
                        case 50:
                            if ((i & 2) == 0) {
                                this.tlsCertificateSdsSecretConfigs_ = new ArrayList();
                                i |= 2;
                            }
                            this.tlsCertificateSdsSecretConfigs_.add(codedInputStream.readMessage(SdsSecretConfig.parser(), extensionRegistryLite));
                        case 58:
                            SdsSecretConfig.Builder builderM31975toBuilder = this.validationContextTypeCase_ == 7 ? ((SdsSecretConfig) this.validationContextType_).m31974toBuilder() : null;
                            MessageLite message2 = codedInputStream.readMessage(SdsSecretConfig.parser(), extensionRegistryLite);
                            this.validationContextType_ = message2;
                            if (builderM31975toBuilder != null) {
                                builderM31975toBuilder.mergeFrom((SdsSecretConfig) message2);
                                this.validationContextType_ = builderM31975toBuilder.m31981buildPartial();
                            }
                            this.validationContextTypeCase_ = 7;
                        case 66:
                            CombinedCertificateValidationContext.Builder builderM31790toBuilder = this.validationContextTypeCase_ == 8 ? ((CombinedCertificateValidationContext) this.validationContextType_).m31790toBuilder() : null;
                            MessageLite message3 = codedInputStream.readMessage(CombinedCertificateValidationContext.parser(), extensionRegistryLite);
                            this.validationContextType_ = message3;
                            if (builderM31790toBuilder != null) {
                                builderM31790toBuilder.mergeFrom((CombinedCertificateValidationContext) message3);
                                this.validationContextType_ = builderM31790toBuilder.m31797buildPartial();
                            }
                            this.validationContextTypeCase_ = 8;
                        case 74:
                            CertificateProvider certificateProvider = this.tlsCertificateCertificateProvider_;
                            CertificateProvider.Builder builderM31698toBuilder = certificateProvider != null ? certificateProvider.m31698toBuilder() : null;
                            CertificateProvider certificateProvider2 = (CertificateProvider) codedInputStream.readMessage(CertificateProvider.parser(), extensionRegistryLite);
                            this.tlsCertificateCertificateProvider_ = certificateProvider2;
                            if (builderM31698toBuilder != null) {
                                builderM31698toBuilder.mergeFrom(certificateProvider2);
                                this.tlsCertificateCertificateProvider_ = builderM31698toBuilder.m31705buildPartial();
                            }
                        case 82:
                            CertificateProvider.Builder builderM31698toBuilder2 = this.validationContextTypeCase_ == 10 ? ((CertificateProvider) this.validationContextType_).m31698toBuilder() : null;
                            MessageLite message4 = codedInputStream.readMessage(CertificateProvider.parser(), extensionRegistryLite);
                            this.validationContextType_ = message4;
                            if (builderM31698toBuilder2 != null) {
                                builderM31698toBuilder2.mergeFrom((CertificateProvider) message4);
                                this.validationContextType_ = builderM31698toBuilder2.m31705buildPartial();
                            }
                            this.validationContextTypeCase_ = 10;
                        case RESET_TO_DEFAULT_CONFIGURATION_COMMAND_VALUE:
                            CertificateProviderInstance certificateProviderInstance = this.tlsCertificateCertificateProviderInstance_;
                            CertificateProviderInstance.Builder builderM31744toBuilder = certificateProviderInstance != null ? certificateProviderInstance.m31744toBuilder() : null;
                            CertificateProviderInstance certificateProviderInstance2 = (CertificateProviderInstance) codedInputStream.readMessage(CertificateProviderInstance.parser(), extensionRegistryLite);
                            this.tlsCertificateCertificateProviderInstance_ = certificateProviderInstance2;
                            if (builderM31744toBuilder != null) {
                                builderM31744toBuilder.mergeFrom(certificateProviderInstance2);
                                this.tlsCertificateCertificateProviderInstance_ = builderM31744toBuilder.m31751buildPartial();
                            }
                        case 98:
                            CertificateProviderInstance.Builder builderM31744toBuilder2 = this.validationContextTypeCase_ == 12 ? ((CertificateProviderInstance) this.validationContextType_).m31744toBuilder() : null;
                            MessageLite message5 = codedInputStream.readMessage(CertificateProviderInstance.parser(), extensionRegistryLite);
                            this.validationContextType_ = message5;
                            if (builderM31744toBuilder2 != null) {
                                builderM31744toBuilder2.mergeFrom((CertificateProviderInstance) message5);
                                this.validationContextType_ = builderM31744toBuilder2.m31751buildPartial();
                            }
                            this.validationContextTypeCase_ = 12;
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
                    this.tlsCertificates_ = Collections.unmodifiableList(this.tlsCertificates_);
                }
                if ((i & 4) != 0) {
                    this.alpnProtocols_ = this.alpnProtocols_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.tlsCertificateSdsSecretConfigs_ = Collections.unmodifiableList(this.tlsCertificateSdsSecretConfigs_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static CommonTlsContext getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CommonTlsContext> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_descriptor;
    }

    public static CommonTlsContext parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (CommonTlsContext) PARSER.parseFrom(byteBuffer);
    }

    public static CommonTlsContext parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CommonTlsContext) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static CommonTlsContext parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CommonTlsContext) PARSER.parseFrom(byteString);
    }

    public static CommonTlsContext parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CommonTlsContext) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static CommonTlsContext parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CommonTlsContext) PARSER.parseFrom(bArr);
    }

    public static CommonTlsContext parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CommonTlsContext) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static CommonTlsContext parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static CommonTlsContext parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CommonTlsContext parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static CommonTlsContext parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CommonTlsContext parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static CommonTlsContext parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m31652toBuilder();
    }

    public static Builder newBuilder(CommonTlsContext commonTlsContext) {
        return DEFAULT_INSTANCE.m31652toBuilder().mergeFrom(commonTlsContext);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    /* renamed from: getAlpnProtocolsList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo31646getAlpnProtocolsList() {
        return this.alpnProtocols_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public CommonTlsContext m31647getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<CommonTlsContext> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public List<SdsSecretConfig> getTlsCertificateSdsSecretConfigsList() {
        return this.tlsCertificateSdsSecretConfigs_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public List<? extends SdsSecretConfigOrBuilder> getTlsCertificateSdsSecretConfigsOrBuilderList() {
        return this.tlsCertificateSdsSecretConfigs_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public List<TlsCertificate> getTlsCertificatesList() {
        return this.tlsCertificates_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public List<? extends TlsCertificateOrBuilder> getTlsCertificatesOrBuilderList() {
        return this.tlsCertificates_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public boolean hasCombinedValidationContext() {
        return this.validationContextTypeCase_ == 8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public boolean hasTlsCertificateCertificateProvider() {
        return this.tlsCertificateCertificateProvider_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public boolean hasTlsCertificateCertificateProviderInstance() {
        return this.tlsCertificateCertificateProviderInstance_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public boolean hasTlsParams() {
        return this.tlsParams_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public boolean hasValidationContext() {
        return this.validationContextTypeCase_ == 3;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public boolean hasValidationContextCertificateProvider() {
        return this.validationContextTypeCase_ == 10;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public boolean hasValidationContextCertificateProviderInstance() {
        return this.validationContextTypeCase_ == 12;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public boolean hasValidationContextSdsSecretConfig() {
        return this.validationContextTypeCase_ == 7;
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
        return new CommonTlsContext();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_fieldAccessorTable.ensureFieldAccessorsInitialized(CommonTlsContext.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public ValidationContextTypeCase getValidationContextTypeCase() {
        return ValidationContextTypeCase.forNumber(this.validationContextTypeCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public TlsParameters getTlsParams() {
        TlsParameters tlsParameters = this.tlsParams_;
        return tlsParameters == null ? TlsParameters.getDefaultInstance() : tlsParameters;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public TlsParametersOrBuilder getTlsParamsOrBuilder() {
        return getTlsParams();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public int getTlsCertificatesCount() {
        return this.tlsCertificates_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public TlsCertificate getTlsCertificates(int i) {
        return this.tlsCertificates_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public TlsCertificateOrBuilder getTlsCertificatesOrBuilder(int i) {
        return this.tlsCertificates_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public int getTlsCertificateSdsSecretConfigsCount() {
        return this.tlsCertificateSdsSecretConfigs_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public SdsSecretConfig getTlsCertificateSdsSecretConfigs(int i) {
        return this.tlsCertificateSdsSecretConfigs_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public SdsSecretConfigOrBuilder getTlsCertificateSdsSecretConfigsOrBuilder(int i) {
        return this.tlsCertificateSdsSecretConfigs_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public CertificateProvider getTlsCertificateCertificateProvider() {
        CertificateProvider certificateProvider = this.tlsCertificateCertificateProvider_;
        return certificateProvider == null ? CertificateProvider.getDefaultInstance() : certificateProvider;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public CertificateProviderOrBuilder getTlsCertificateCertificateProviderOrBuilder() {
        return getTlsCertificateCertificateProvider();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public CertificateProviderInstance getTlsCertificateCertificateProviderInstance() {
        CertificateProviderInstance certificateProviderInstance = this.tlsCertificateCertificateProviderInstance_;
        return certificateProviderInstance == null ? CertificateProviderInstance.getDefaultInstance() : certificateProviderInstance;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public CertificateProviderInstanceOrBuilder getTlsCertificateCertificateProviderInstanceOrBuilder() {
        return getTlsCertificateCertificateProviderInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public CertificateValidationContext getValidationContext() {
        if (this.validationContextTypeCase_ == 3) {
            return (CertificateValidationContext) this.validationContextType_;
        }
        return CertificateValidationContext.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public CertificateValidationContextOrBuilder getValidationContextOrBuilder() {
        if (this.validationContextTypeCase_ == 3) {
            return (CertificateValidationContext) this.validationContextType_;
        }
        return CertificateValidationContext.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public SdsSecretConfig getValidationContextSdsSecretConfig() {
        if (this.validationContextTypeCase_ == 7) {
            return (SdsSecretConfig) this.validationContextType_;
        }
        return SdsSecretConfig.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public SdsSecretConfigOrBuilder getValidationContextSdsSecretConfigOrBuilder() {
        if (this.validationContextTypeCase_ == 7) {
            return (SdsSecretConfig) this.validationContextType_;
        }
        return SdsSecretConfig.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public CombinedCertificateValidationContext getCombinedValidationContext() {
        if (this.validationContextTypeCase_ == 8) {
            return (CombinedCertificateValidationContext) this.validationContextType_;
        }
        return CombinedCertificateValidationContext.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public CombinedCertificateValidationContextOrBuilder getCombinedValidationContextOrBuilder() {
        if (this.validationContextTypeCase_ == 8) {
            return (CombinedCertificateValidationContext) this.validationContextType_;
        }
        return CombinedCertificateValidationContext.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public CertificateProvider getValidationContextCertificateProvider() {
        if (this.validationContextTypeCase_ == 10) {
            return (CertificateProvider) this.validationContextType_;
        }
        return CertificateProvider.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public CertificateProviderOrBuilder getValidationContextCertificateProviderOrBuilder() {
        if (this.validationContextTypeCase_ == 10) {
            return (CertificateProvider) this.validationContextType_;
        }
        return CertificateProvider.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public CertificateProviderInstance getValidationContextCertificateProviderInstance() {
        if (this.validationContextTypeCase_ == 12) {
            return (CertificateProviderInstance) this.validationContextType_;
        }
        return CertificateProviderInstance.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public CertificateProviderInstanceOrBuilder getValidationContextCertificateProviderInstanceOrBuilder() {
        if (this.validationContextTypeCase_ == 12) {
            return (CertificateProviderInstance) this.validationContextType_;
        }
        return CertificateProviderInstance.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public int getAlpnProtocolsCount() {
        return this.alpnProtocols_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public String getAlpnProtocols(int i) {
        return (String) this.alpnProtocols_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
    public ByteString getAlpnProtocolsBytes(int i) {
        return this.alpnProtocols_.getByteString(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.tlsParams_ != null) {
            codedOutputStream.writeMessage(1, getTlsParams());
        }
        for (int i = 0; i < this.tlsCertificates_.size(); i++) {
            codedOutputStream.writeMessage(2, this.tlsCertificates_.get(i));
        }
        if (this.validationContextTypeCase_ == 3) {
            codedOutputStream.writeMessage(3, (CertificateValidationContext) this.validationContextType_);
        }
        for (int i2 = 0; i2 < this.alpnProtocols_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.alpnProtocols_.getRaw(i2));
        }
        for (int i3 = 0; i3 < this.tlsCertificateSdsSecretConfigs_.size(); i3++) {
            codedOutputStream.writeMessage(6, this.tlsCertificateSdsSecretConfigs_.get(i3));
        }
        if (this.validationContextTypeCase_ == 7) {
            codedOutputStream.writeMessage(7, (SdsSecretConfig) this.validationContextType_);
        }
        if (this.validationContextTypeCase_ == 8) {
            codedOutputStream.writeMessage(8, (CombinedCertificateValidationContext) this.validationContextType_);
        }
        if (this.tlsCertificateCertificateProvider_ != null) {
            codedOutputStream.writeMessage(9, getTlsCertificateCertificateProvider());
        }
        if (this.validationContextTypeCase_ == 10) {
            codedOutputStream.writeMessage(10, (CertificateProvider) this.validationContextType_);
        }
        if (this.tlsCertificateCertificateProviderInstance_ != null) {
            codedOutputStream.writeMessage(11, getTlsCertificateCertificateProviderInstance());
        }
        if (this.validationContextTypeCase_ == 12) {
            codedOutputStream.writeMessage(12, (CertificateProviderInstance) this.validationContextType_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.tlsParams_ != null ? CodedOutputStream.computeMessageSize(1, getTlsParams()) : 0;
        for (int i2 = 0; i2 < this.tlsCertificates_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, this.tlsCertificates_.get(i2));
        }
        if (this.validationContextTypeCase_ == 3) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, (CertificateValidationContext) this.validationContextType_);
        }
        int iComputeStringSizeNoTag = 0;
        for (int i3 = 0; i3 < this.alpnProtocols_.size(); i3++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.alpnProtocols_.getRaw(i3));
        }
        int size = iComputeMessageSize + iComputeStringSizeNoTag + mo31646getAlpnProtocolsList().size();
        for (int i4 = 0; i4 < this.tlsCertificateSdsSecretConfigs_.size(); i4++) {
            size += CodedOutputStream.computeMessageSize(6, this.tlsCertificateSdsSecretConfigs_.get(i4));
        }
        if (this.validationContextTypeCase_ == 7) {
            size += CodedOutputStream.computeMessageSize(7, (SdsSecretConfig) this.validationContextType_);
        }
        if (this.validationContextTypeCase_ == 8) {
            size += CodedOutputStream.computeMessageSize(8, (CombinedCertificateValidationContext) this.validationContextType_);
        }
        if (this.tlsCertificateCertificateProvider_ != null) {
            size += CodedOutputStream.computeMessageSize(9, getTlsCertificateCertificateProvider());
        }
        if (this.validationContextTypeCase_ == 10) {
            size += CodedOutputStream.computeMessageSize(10, (CertificateProvider) this.validationContextType_);
        }
        if (this.tlsCertificateCertificateProviderInstance_ != null) {
            size += CodedOutputStream.computeMessageSize(11, getTlsCertificateCertificateProviderInstance());
        }
        if (this.validationContextTypeCase_ == 12) {
            size += CodedOutputStream.computeMessageSize(12, (CertificateProviderInstance) this.validationContextType_);
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CommonTlsContext)) {
            return super.equals(obj);
        }
        CommonTlsContext commonTlsContext = (CommonTlsContext) obj;
        if (hasTlsParams() != commonTlsContext.hasTlsParams()) {
            return false;
        }
        if ((hasTlsParams() && !getTlsParams().equals(commonTlsContext.getTlsParams())) || !getTlsCertificatesList().equals(commonTlsContext.getTlsCertificatesList()) || !getTlsCertificateSdsSecretConfigsList().equals(commonTlsContext.getTlsCertificateSdsSecretConfigsList()) || hasTlsCertificateCertificateProvider() != commonTlsContext.hasTlsCertificateCertificateProvider()) {
            return false;
        }
        if ((hasTlsCertificateCertificateProvider() && !getTlsCertificateCertificateProvider().equals(commonTlsContext.getTlsCertificateCertificateProvider())) || hasTlsCertificateCertificateProviderInstance() != commonTlsContext.hasTlsCertificateCertificateProviderInstance()) {
            return false;
        }
        if ((hasTlsCertificateCertificateProviderInstance() && !getTlsCertificateCertificateProviderInstance().equals(commonTlsContext.getTlsCertificateCertificateProviderInstance())) || !mo31646getAlpnProtocolsList().equals(commonTlsContext.mo31646getAlpnProtocolsList()) || !getValidationContextTypeCase().equals(commonTlsContext.getValidationContextTypeCase())) {
            return false;
        }
        int i = this.validationContextTypeCase_;
        if (i != 3) {
            if (i != 10) {
                if (i != 12) {
                    if (i == 7) {
                        if (!getValidationContextSdsSecretConfig().equals(commonTlsContext.getValidationContextSdsSecretConfig())) {
                            return false;
                        }
                    } else if (i == 8 && !getCombinedValidationContext().equals(commonTlsContext.getCombinedValidationContext())) {
                        return false;
                    }
                } else if (!getValidationContextCertificateProviderInstance().equals(commonTlsContext.getValidationContextCertificateProviderInstance())) {
                    return false;
                }
            } else if (!getValidationContextCertificateProvider().equals(commonTlsContext.getValidationContextCertificateProvider())) {
                return false;
            }
        } else if (!getValidationContext().equals(commonTlsContext.getValidationContext())) {
            return false;
        }
        return this.unknownFields.equals(commonTlsContext.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        if (hasTlsParams()) {
            iHashCode2 = (((iHashCode2 * 37) + 1) * 53) + getTlsParams().hashCode();
        }
        if (getTlsCertificatesCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 2) * 53) + getTlsCertificatesList().hashCode();
        }
        if (getTlsCertificateSdsSecretConfigsCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 6) * 53) + getTlsCertificateSdsSecretConfigsList().hashCode();
        }
        if (hasTlsCertificateCertificateProvider()) {
            iHashCode2 = (((iHashCode2 * 37) + 9) * 53) + getTlsCertificateCertificateProvider().hashCode();
        }
        if (hasTlsCertificateCertificateProviderInstance()) {
            iHashCode2 = (((iHashCode2 * 37) + 11) * 53) + getTlsCertificateCertificateProviderInstance().hashCode();
        }
        if (getAlpnProtocolsCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 4) * 53) + mo31646getAlpnProtocolsList().hashCode();
        }
        int i2 = this.validationContextTypeCase_;
        if (i2 == 3) {
            i = ((iHashCode2 * 37) + 3) * 53;
            iHashCode = getValidationContext().hashCode();
        } else if (i2 == 10) {
            i = ((iHashCode2 * 37) + 10) * 53;
            iHashCode = getValidationContextCertificateProvider().hashCode();
        } else if (i2 == 12) {
            i = ((iHashCode2 * 37) + 12) * 53;
            iHashCode = getValidationContextCertificateProviderInstance().hashCode();
        } else if (i2 == 7) {
            i = ((iHashCode2 * 37) + 7) * 53;
            iHashCode = getValidationContextSdsSecretConfig().hashCode();
        } else {
            if (i2 == 8) {
                i = ((iHashCode2 * 37) + 8) * 53;
                iHashCode = getCombinedValidationContext().hashCode();
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
    public Builder m31649newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m31652toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ValidationContextTypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        VALIDATION_CONTEXT(3),
        VALIDATION_CONTEXT_SDS_SECRET_CONFIG(7),
        COMBINED_VALIDATION_CONTEXT(8),
        VALIDATION_CONTEXT_CERTIFICATE_PROVIDER(10),
        VALIDATION_CONTEXT_CERTIFICATE_PROVIDER_INSTANCE(12),
        VALIDATIONCONTEXTTYPE_NOT_SET(0);

        private final int value;

        ValidationContextTypeCase(int i) {
            this.value = i;
        }

        public static ValidationContextTypeCase forNumber(int i) {
            if (i == 0) {
                return VALIDATIONCONTEXTTYPE_NOT_SET;
            }
            if (i == 3) {
                return VALIDATION_CONTEXT;
            }
            if (i == 10) {
                return VALIDATION_CONTEXT_CERTIFICATE_PROVIDER;
            }
            if (i == 12) {
                return VALIDATION_CONTEXT_CERTIFICATE_PROVIDER_INSTANCE;
            }
            if (i == 7) {
                return VALIDATION_CONTEXT_SDS_SECRET_CONFIG;
            }
            if (i != 8) {
                return null;
            }
            return COMBINED_VALIDATION_CONTEXT;
        }

        @Deprecated
        public static ValidationContextTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public interface CertificateProviderInstanceOrBuilder extends MessageOrBuilder {
        String getCertificateName();

        ByteString getCertificateNameBytes();

        String getInstanceName();

        ByteString getInstanceNameBytes();
    }

    public interface CertificateProviderOrBuilder extends MessageOrBuilder {
        CertificateProvider.ConfigCase getConfigCase();

        String getName();

        ByteString getNameBytes();

        TypedExtensionConfig getTypedConfig();

        TypedExtensionConfigOrBuilder getTypedConfigOrBuilder();

        boolean hasTypedConfig();
    }

    public interface CombinedCertificateValidationContextOrBuilder extends MessageOrBuilder {
        CertificateValidationContext getDefaultValidationContext();

        CertificateValidationContextOrBuilder getDefaultValidationContextOrBuilder();

        CertificateProvider getValidationContextCertificateProvider();

        CertificateProviderInstance getValidationContextCertificateProviderInstance();

        CertificateProviderInstanceOrBuilder getValidationContextCertificateProviderInstanceOrBuilder();

        CertificateProviderOrBuilder getValidationContextCertificateProviderOrBuilder();

        SdsSecretConfig getValidationContextSdsSecretConfig();

        SdsSecretConfigOrBuilder getValidationContextSdsSecretConfigOrBuilder();

        boolean hasDefaultValidationContext();

        boolean hasValidationContextCertificateProvider();

        boolean hasValidationContextCertificateProviderInstance();

        boolean hasValidationContextSdsSecretConfig();
    }

    public static final class CertificateProvider extends GeneratedMessageV3 implements CertificateProviderOrBuilder {
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int TYPED_CONFIG_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private static final CertificateProvider DEFAULT_INSTANCE = new CertificateProvider();
        private static final Parser<CertificateProvider> PARSER = new AbstractParser<CertificateProvider>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProvider.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public CertificateProvider m31700parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CertificateProvider(codedInputStream, extensionRegistryLite);
            }
        };
        private int configCase_;
        private Object config_;
        private byte memoizedIsInitialized;
        private volatile Object name_;

        private CertificateProvider(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.configCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private CertificateProvider() {
            this.configCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
        }

        private CertificateProvider(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                TypedExtensionConfig.Builder builderM24433toBuilder = this.configCase_ == 2 ? ((TypedExtensionConfig) this.config_).m24433toBuilder() : null;
                                MessageLite message = codedInputStream.readMessage(TypedExtensionConfig.parser(), extensionRegistryLite);
                                this.config_ = message;
                                if (builderM24433toBuilder != null) {
                                    builderM24433toBuilder.mergeFrom((TypedExtensionConfig) message);
                                    this.config_ = builderM24433toBuilder.m24440buildPartial();
                                }
                                this.configCase_ = 2;
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

        public static CertificateProvider getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CertificateProvider> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProvider_descriptor;
        }

        public static CertificateProvider parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CertificateProvider) PARSER.parseFrom(byteBuffer);
        }

        public static CertificateProvider parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CertificateProvider) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CertificateProvider parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CertificateProvider) PARSER.parseFrom(byteString);
        }

        public static CertificateProvider parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CertificateProvider) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CertificateProvider parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CertificateProvider) PARSER.parseFrom(bArr);
        }

        public static CertificateProvider parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CertificateProvider) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CertificateProvider parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CertificateProvider parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CertificateProvider parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CertificateProvider parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CertificateProvider parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CertificateProvider parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m31698toBuilder();
        }

        public static Builder newBuilder(CertificateProvider certificateProvider) {
            return DEFAULT_INSTANCE.m31698toBuilder().mergeFrom(certificateProvider);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CertificateProvider m31693getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<CertificateProvider> getParserForType() {
            return PARSER;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderOrBuilder
        public boolean hasTypedConfig() {
            return this.configCase_ == 2;
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
            return new CertificateProvider();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProvider_fieldAccessorTable.ensureFieldAccessorsInitialized(CertificateProvider.class, Builder.class);
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderOrBuilder
        public ConfigCase getConfigCase() {
            return ConfigCase.forNumber(this.configCase_);
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderOrBuilder
        public TypedExtensionConfig getTypedConfig() {
            if (this.configCase_ == 2) {
                return (TypedExtensionConfig) this.config_;
            }
            return TypedExtensionConfig.getDefaultInstance();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderOrBuilder
        public TypedExtensionConfigOrBuilder getTypedConfigOrBuilder() {
            if (this.configCase_ == 2) {
                return (TypedExtensionConfig) this.config_;
            }
            return TypedExtensionConfig.getDefaultInstance();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
            }
            if (this.configCase_ == 2) {
                codedOutputStream.writeMessage(2, (TypedExtensionConfig) this.config_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
            if (this.configCase_ == 2) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(2, (TypedExtensionConfig) this.config_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CertificateProvider)) {
                return super.equals(obj);
            }
            CertificateProvider certificateProvider = (CertificateProvider) obj;
            if (getName().equals(certificateProvider.getName()) && getConfigCase().equals(certificateProvider.getConfigCase())) {
                return (this.configCase_ != 2 || getTypedConfig().equals(certificateProvider.getTypedConfig())) && this.unknownFields.equals(certificateProvider.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
            if (this.configCase_ == 2) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getTypedConfig().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31695newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31698toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public enum ConfigCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
            TYPED_CONFIG(2),
            CONFIG_NOT_SET(0);

            private final int value;

            ConfigCase(int i) {
                this.value = i;
            }

            public static ConfigCase forNumber(int i) {
                if (i == 0) {
                    return CONFIG_NOT_SET;
                }
                if (i != 2) {
                    return null;
                }
                return TYPED_CONFIG;
            }

            @Deprecated
            public static ConfigCase valueOf(int i) {
                return forNumber(i);
            }

            public int getNumber() {
                return this.value;
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CertificateProviderOrBuilder {
            private int configCase_;
            private Object config_;
            private Object name_;
            private SingleFieldBuilderV3<TypedExtensionConfig, TypedExtensionConfig.Builder, TypedExtensionConfigOrBuilder> typedConfigBuilder_;

            private Builder() {
                this.configCase_ = 0;
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.configCase_ = 0;
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProvider_descriptor;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderOrBuilder
            public boolean hasTypedConfig() {
                return this.configCase_ == 2;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProvider_fieldAccessorTable.ensureFieldAccessorsInitialized(CertificateProvider.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CertificateProvider.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31709clear() {
                super.clear();
                this.name_ = "";
                this.configCase_ = 0;
                this.config_ = null;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProvider_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CertificateProvider m31722getDefaultInstanceForType() {
                return CertificateProvider.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CertificateProvider m31703build() throws UninitializedMessageException {
                CertificateProvider certificateProviderM31705buildPartial = m31705buildPartial();
                if (certificateProviderM31705buildPartial.isInitialized()) {
                    return certificateProviderM31705buildPartial;
                }
                throw newUninitializedMessageException(certificateProviderM31705buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CertificateProvider m31705buildPartial() {
                CertificateProvider certificateProvider = new CertificateProvider(this);
                certificateProvider.name_ = this.name_;
                if (this.configCase_ == 2) {
                    SingleFieldBuilderV3<TypedExtensionConfig, TypedExtensionConfig.Builder, TypedExtensionConfigOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        certificateProvider.config_ = this.config_;
                    } else {
                        certificateProvider.config_ = singleFieldBuilderV3.build();
                    }
                }
                certificateProvider.configCase_ = this.configCase_;
                onBuilt();
                return certificateProvider;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31721clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31733setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31711clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31714clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31735setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31701addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31726mergeFrom(Message message) {
                if (message instanceof CertificateProvider) {
                    return mergeFrom((CertificateProvider) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CertificateProvider certificateProvider) {
                if (certificateProvider == CertificateProvider.getDefaultInstance()) {
                    return this;
                }
                if (!certificateProvider.getName().isEmpty()) {
                    this.name_ = certificateProvider.name_;
                    onChanged();
                }
                if (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$CommonTlsContext$CertificateProvider$ConfigCase[certificateProvider.getConfigCase().ordinal()] == 1) {
                    mergeTypedConfig(certificateProvider.getTypedConfig());
                }
                m31731mergeUnknownFields(certificateProvider.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProvider.Builder m31727mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProvider.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext$CertificateProvider r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProvider) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext$CertificateProvider r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProvider) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProvider.Builder.m31727mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext$CertificateProvider$Builder");
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderOrBuilder
            public ConfigCase getConfigCase() {
                return ConfigCase.forNumber(this.configCase_);
            }

            public Builder clearConfig() {
                this.configCase_ = 0;
                this.config_ = null;
                onChanged();
                return this;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderOrBuilder
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

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderOrBuilder
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
                CertificateProvider.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = CertificateProvider.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderOrBuilder
            public TypedExtensionConfig getTypedConfig() {
                SingleFieldBuilderV3<TypedExtensionConfig, TypedExtensionConfig.Builder, TypedExtensionConfigOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.configCase_ == 2) {
                        return (TypedExtensionConfig) this.config_;
                    }
                    return TypedExtensionConfig.getDefaultInstance();
                }
                if (this.configCase_ == 2) {
                    return singleFieldBuilderV3.getMessage();
                }
                return TypedExtensionConfig.getDefaultInstance();
            }

            public Builder setTypedConfig(TypedExtensionConfig typedExtensionConfig) {
                SingleFieldBuilderV3<TypedExtensionConfig, TypedExtensionConfig.Builder, TypedExtensionConfigOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    typedExtensionConfig.getClass();
                    this.config_ = typedExtensionConfig;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(typedExtensionConfig);
                }
                this.configCase_ = 2;
                return this;
            }

            public Builder setTypedConfig(TypedExtensionConfig.Builder builder) {
                SingleFieldBuilderV3<TypedExtensionConfig, TypedExtensionConfig.Builder, TypedExtensionConfigOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.config_ = builder.m24438build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m24438build());
                }
                this.configCase_ = 2;
                return this;
            }

            public Builder mergeTypedConfig(TypedExtensionConfig typedExtensionConfig) {
                SingleFieldBuilderV3<TypedExtensionConfig, TypedExtensionConfig.Builder, TypedExtensionConfigOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.configCase_ != 2 || this.config_ == TypedExtensionConfig.getDefaultInstance()) {
                        this.config_ = typedExtensionConfig;
                    } else {
                        this.config_ = TypedExtensionConfig.newBuilder((TypedExtensionConfig) this.config_).mergeFrom(typedExtensionConfig).m24440buildPartial();
                    }
                    onChanged();
                } else {
                    if (this.configCase_ == 2) {
                        singleFieldBuilderV3.mergeFrom(typedExtensionConfig);
                    }
                    this.typedConfigBuilder_.setMessage(typedExtensionConfig);
                }
                this.configCase_ = 2;
                return this;
            }

            public Builder clearTypedConfig() {
                SingleFieldBuilderV3<TypedExtensionConfig, TypedExtensionConfig.Builder, TypedExtensionConfigOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 != null) {
                    if (this.configCase_ == 2) {
                        this.configCase_ = 0;
                        this.config_ = null;
                    }
                    singleFieldBuilderV3.clear();
                } else if (this.configCase_ == 2) {
                    this.configCase_ = 0;
                    this.config_ = null;
                    onChanged();
                }
                return this;
            }

            public TypedExtensionConfig.Builder getTypedConfigBuilder() {
                return getTypedConfigFieldBuilder().getBuilder();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderOrBuilder
            public TypedExtensionConfigOrBuilder getTypedConfigOrBuilder() {
                SingleFieldBuilderV3<TypedExtensionConfig, TypedExtensionConfig.Builder, TypedExtensionConfigOrBuilder> singleFieldBuilderV3;
                int i = this.configCase_;
                if (i == 2 && (singleFieldBuilderV3 = this.typedConfigBuilder_) != null) {
                    return (TypedExtensionConfigOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                if (i == 2) {
                    return (TypedExtensionConfig) this.config_;
                }
                return TypedExtensionConfig.getDefaultInstance();
            }

            private SingleFieldBuilderV3<TypedExtensionConfig, TypedExtensionConfig.Builder, TypedExtensionConfigOrBuilder> getTypedConfigFieldBuilder() {
                if (this.typedConfigBuilder_ == null) {
                    if (this.configCase_ != 2) {
                        this.config_ = TypedExtensionConfig.getDefaultInstance();
                    }
                    this.typedConfigBuilder_ = new SingleFieldBuilderV3<>((TypedExtensionConfig) this.config_, getParentForChildren(), isClean());
                    this.config_ = null;
                }
                this.configCase_ = 2;
                onChanged();
                return this.typedConfigBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m31737setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m31731mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class CertificateProviderInstance extends GeneratedMessageV3 implements CertificateProviderInstanceOrBuilder {
        public static final int CERTIFICATE_NAME_FIELD_NUMBER = 2;
        public static final int INSTANCE_NAME_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final CertificateProviderInstance DEFAULT_INSTANCE = new CertificateProviderInstance();
        private static final Parser<CertificateProviderInstance> PARSER = new AbstractParser<CertificateProviderInstance>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstance.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public CertificateProviderInstance m31746parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CertificateProviderInstance(codedInputStream, extensionRegistryLite);
            }
        };
        private volatile Object certificateName_;
        private volatile Object instanceName_;
        private byte memoizedIsInitialized;

        private CertificateProviderInstance(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CertificateProviderInstance() {
            this.memoizedIsInitialized = (byte) -1;
            this.instanceName_ = "";
            this.certificateName_ = "";
        }

        private CertificateProviderInstance(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (tag == 10) {
                                    this.instanceName_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 18) {
                                    this.certificateName_ = codedInputStream.readStringRequireUtf8();
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static CertificateProviderInstance getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CertificateProviderInstance> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProviderInstance_descriptor;
        }

        public static CertificateProviderInstance parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CertificateProviderInstance) PARSER.parseFrom(byteBuffer);
        }

        public static CertificateProviderInstance parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CertificateProviderInstance) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CertificateProviderInstance parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CertificateProviderInstance) PARSER.parseFrom(byteString);
        }

        public static CertificateProviderInstance parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CertificateProviderInstance) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CertificateProviderInstance parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CertificateProviderInstance) PARSER.parseFrom(bArr);
        }

        public static CertificateProviderInstance parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CertificateProviderInstance) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CertificateProviderInstance parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CertificateProviderInstance parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CertificateProviderInstance parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CertificateProviderInstance parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CertificateProviderInstance parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CertificateProviderInstance parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m31744toBuilder();
        }

        public static Builder newBuilder(CertificateProviderInstance certificateProviderInstance) {
            return DEFAULT_INSTANCE.m31744toBuilder().mergeFrom(certificateProviderInstance);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CertificateProviderInstance m31739getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<CertificateProviderInstance> getParserForType() {
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
            return new CertificateProviderInstance();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProviderInstance_fieldAccessorTable.ensureFieldAccessorsInitialized(CertificateProviderInstance.class, Builder.class);
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstanceOrBuilder
        public String getInstanceName() {
            Object obj = this.instanceName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.instanceName_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstanceOrBuilder
        public ByteString getInstanceNameBytes() {
            Object obj = this.instanceName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.instanceName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstanceOrBuilder
        public String getCertificateName() {
            Object obj = this.certificateName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.certificateName_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstanceOrBuilder
        public ByteString getCertificateNameBytes() {
            Object obj = this.certificateName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.certificateName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getInstanceNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.instanceName_);
            }
            if (!getCertificateNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.certificateName_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getInstanceNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.instanceName_) : 0;
            if (!getCertificateNameBytes().isEmpty()) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.certificateName_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CertificateProviderInstance)) {
                return super.equals(obj);
            }
            CertificateProviderInstance certificateProviderInstance = (CertificateProviderInstance) obj;
            return getInstanceName().equals(certificateProviderInstance.getInstanceName()) && getCertificateName().equals(certificateProviderInstance.getCertificateName()) && this.unknownFields.equals(certificateProviderInstance.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getInstanceName().hashCode()) * 37) + 2) * 53) + getCertificateName().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31741newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31744toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CertificateProviderInstanceOrBuilder {
            private Object certificateName_;
            private Object instanceName_;

            private Builder() {
                this.instanceName_ = "";
                this.certificateName_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.instanceName_ = "";
                this.certificateName_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProviderInstance_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProviderInstance_fieldAccessorTable.ensureFieldAccessorsInitialized(CertificateProviderInstance.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CertificateProviderInstance.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31755clear() {
                super.clear();
                this.instanceName_ = "";
                this.certificateName_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProviderInstance_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CertificateProviderInstance m31768getDefaultInstanceForType() {
                return CertificateProviderInstance.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CertificateProviderInstance m31749build() throws UninitializedMessageException {
                CertificateProviderInstance certificateProviderInstanceM31751buildPartial = m31751buildPartial();
                if (certificateProviderInstanceM31751buildPartial.isInitialized()) {
                    return certificateProviderInstanceM31751buildPartial;
                }
                throw newUninitializedMessageException(certificateProviderInstanceM31751buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CertificateProviderInstance m31751buildPartial() {
                CertificateProviderInstance certificateProviderInstance = new CertificateProviderInstance(this);
                certificateProviderInstance.instanceName_ = this.instanceName_;
                certificateProviderInstance.certificateName_ = this.certificateName_;
                onBuilt();
                return certificateProviderInstance;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31767clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31779setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31757clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31760clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31781setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31747addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31772mergeFrom(Message message) {
                if (message instanceof CertificateProviderInstance) {
                    return mergeFrom((CertificateProviderInstance) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CertificateProviderInstance certificateProviderInstance) {
                if (certificateProviderInstance == CertificateProviderInstance.getDefaultInstance()) {
                    return this;
                }
                if (!certificateProviderInstance.getInstanceName().isEmpty()) {
                    this.instanceName_ = certificateProviderInstance.instanceName_;
                    onChanged();
                }
                if (!certificateProviderInstance.getCertificateName().isEmpty()) {
                    this.certificateName_ = certificateProviderInstance.certificateName_;
                    onChanged();
                }
                m31777mergeUnknownFields(certificateProviderInstance.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstance.Builder m31773mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstance.access$1800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext$CertificateProviderInstance r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstance) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext$CertificateProviderInstance r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstance) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstance.Builder.m31773mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext$CertificateProviderInstance$Builder");
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstanceOrBuilder
            public String getInstanceName() {
                Object obj = this.instanceName_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.instanceName_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setInstanceName(String str) {
                str.getClass();
                this.instanceName_ = str;
                onChanged();
                return this;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstanceOrBuilder
            public ByteString getInstanceNameBytes() {
                Object obj = this.instanceName_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.instanceName_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setInstanceNameBytes(ByteString byteString) {
                byteString.getClass();
                CertificateProviderInstance.checkByteStringIsUtf8(byteString);
                this.instanceName_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearInstanceName() {
                this.instanceName_ = CertificateProviderInstance.getDefaultInstance().getInstanceName();
                onChanged();
                return this;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstanceOrBuilder
            public String getCertificateName() {
                Object obj = this.certificateName_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.certificateName_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setCertificateName(String str) {
                str.getClass();
                this.certificateName_ = str;
                onChanged();
                return this;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstanceOrBuilder
            public ByteString getCertificateNameBytes() {
                Object obj = this.certificateName_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.certificateName_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCertificateNameBytes(ByteString byteString) {
                byteString.getClass();
                CertificateProviderInstance.checkByteStringIsUtf8(byteString);
                this.certificateName_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearCertificateName() {
                this.certificateName_ = CertificateProviderInstance.getDefaultInstance().getCertificateName();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m31783setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m31777mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class CombinedCertificateValidationContext extends GeneratedMessageV3 implements CombinedCertificateValidationContextOrBuilder {
        public static final int DEFAULT_VALIDATION_CONTEXT_FIELD_NUMBER = 1;
        public static final int VALIDATION_CONTEXT_CERTIFICATE_PROVIDER_FIELD_NUMBER = 3;
        public static final int VALIDATION_CONTEXT_CERTIFICATE_PROVIDER_INSTANCE_FIELD_NUMBER = 4;
        public static final int VALIDATION_CONTEXT_SDS_SECRET_CONFIG_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private static final CombinedCertificateValidationContext DEFAULT_INSTANCE = new CombinedCertificateValidationContext();
        private static final Parser<CombinedCertificateValidationContext> PARSER = new AbstractParser<CombinedCertificateValidationContext>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContext.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public CombinedCertificateValidationContext m31792parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CombinedCertificateValidationContext(codedInputStream, extensionRegistryLite);
            }
        };
        private CertificateValidationContext defaultValidationContext_;
        private byte memoizedIsInitialized;
        private CertificateProviderInstance validationContextCertificateProviderInstance_;
        private CertificateProvider validationContextCertificateProvider_;
        private SdsSecretConfig validationContextSdsSecretConfig_;

        private CombinedCertificateValidationContext(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CombinedCertificateValidationContext() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private CombinedCertificateValidationContext(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (tag == 10) {
                                    CertificateValidationContext certificateValidationContext = this.defaultValidationContext_;
                                    CertificateValidationContext.Builder builderM31604toBuilder = certificateValidationContext != null ? certificateValidationContext.m31604toBuilder() : null;
                                    CertificateValidationContext certificateValidationContext2 = (CertificateValidationContext) codedInputStream.readMessage(CertificateValidationContext.parser(), extensionRegistryLite);
                                    this.defaultValidationContext_ = certificateValidationContext2;
                                    if (builderM31604toBuilder != null) {
                                        builderM31604toBuilder.mergeFrom(certificateValidationContext2);
                                        this.defaultValidationContext_ = builderM31604toBuilder.m31611buildPartial();
                                    }
                                } else if (tag == 18) {
                                    SdsSecretConfig sdsSecretConfig = this.validationContextSdsSecretConfig_;
                                    SdsSecretConfig.Builder builderM31975toBuilder = sdsSecretConfig != null ? sdsSecretConfig.m31974toBuilder() : null;
                                    SdsSecretConfig sdsSecretConfig2 = (SdsSecretConfig) codedInputStream.readMessage(SdsSecretConfig.parser(), extensionRegistryLite);
                                    this.validationContextSdsSecretConfig_ = sdsSecretConfig2;
                                    if (builderM31975toBuilder != null) {
                                        builderM31975toBuilder.mergeFrom(sdsSecretConfig2);
                                        this.validationContextSdsSecretConfig_ = builderM31975toBuilder.m31981buildPartial();
                                    }
                                } else if (tag == 26) {
                                    CertificateProvider certificateProvider = this.validationContextCertificateProvider_;
                                    CertificateProvider.Builder builderM31698toBuilder = certificateProvider != null ? certificateProvider.m31698toBuilder() : null;
                                    CertificateProvider certificateProvider2 = (CertificateProvider) codedInputStream.readMessage(CertificateProvider.parser(), extensionRegistryLite);
                                    this.validationContextCertificateProvider_ = certificateProvider2;
                                    if (builderM31698toBuilder != null) {
                                        builderM31698toBuilder.mergeFrom(certificateProvider2);
                                        this.validationContextCertificateProvider_ = builderM31698toBuilder.m31705buildPartial();
                                    }
                                } else if (tag == 34) {
                                    CertificateProviderInstance certificateProviderInstance = this.validationContextCertificateProviderInstance_;
                                    CertificateProviderInstance.Builder builderM31744toBuilder = certificateProviderInstance != null ? certificateProviderInstance.m31744toBuilder() : null;
                                    CertificateProviderInstance certificateProviderInstance2 = (CertificateProviderInstance) codedInputStream.readMessage(CertificateProviderInstance.parser(), extensionRegistryLite);
                                    this.validationContextCertificateProviderInstance_ = certificateProviderInstance2;
                                    if (builderM31744toBuilder != null) {
                                        builderM31744toBuilder.mergeFrom(certificateProviderInstance2);
                                        this.validationContextCertificateProviderInstance_ = builderM31744toBuilder.m31751buildPartial();
                                    }
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static CombinedCertificateValidationContext getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CombinedCertificateValidationContext> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CombinedCertificateValidationContext_descriptor;
        }

        public static CombinedCertificateValidationContext parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CombinedCertificateValidationContext) PARSER.parseFrom(byteBuffer);
        }

        public static CombinedCertificateValidationContext parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CombinedCertificateValidationContext) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CombinedCertificateValidationContext parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CombinedCertificateValidationContext) PARSER.parseFrom(byteString);
        }

        public static CombinedCertificateValidationContext parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CombinedCertificateValidationContext) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CombinedCertificateValidationContext parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CombinedCertificateValidationContext) PARSER.parseFrom(bArr);
        }

        public static CombinedCertificateValidationContext parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CombinedCertificateValidationContext) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CombinedCertificateValidationContext parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CombinedCertificateValidationContext parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CombinedCertificateValidationContext parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CombinedCertificateValidationContext parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CombinedCertificateValidationContext parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CombinedCertificateValidationContext parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m31790toBuilder();
        }

        public static Builder newBuilder(CombinedCertificateValidationContext combinedCertificateValidationContext) {
            return DEFAULT_INSTANCE.m31790toBuilder().mergeFrom(combinedCertificateValidationContext);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CombinedCertificateValidationContext m31785getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<CombinedCertificateValidationContext> getParserForType() {
            return PARSER;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
        public boolean hasDefaultValidationContext() {
            return this.defaultValidationContext_ != null;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
        public boolean hasValidationContextCertificateProvider() {
            return this.validationContextCertificateProvider_ != null;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
        public boolean hasValidationContextCertificateProviderInstance() {
            return this.validationContextCertificateProviderInstance_ != null;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
        public boolean hasValidationContextSdsSecretConfig() {
            return this.validationContextSdsSecretConfig_ != null;
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
            return new CombinedCertificateValidationContext();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CombinedCertificateValidationContext_fieldAccessorTable.ensureFieldAccessorsInitialized(CombinedCertificateValidationContext.class, Builder.class);
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
        public CertificateValidationContext getDefaultValidationContext() {
            CertificateValidationContext certificateValidationContext = this.defaultValidationContext_;
            return certificateValidationContext == null ? CertificateValidationContext.getDefaultInstance() : certificateValidationContext;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
        public CertificateValidationContextOrBuilder getDefaultValidationContextOrBuilder() {
            return getDefaultValidationContext();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
        public SdsSecretConfig getValidationContextSdsSecretConfig() {
            SdsSecretConfig sdsSecretConfig = this.validationContextSdsSecretConfig_;
            return sdsSecretConfig == null ? SdsSecretConfig.getDefaultInstance() : sdsSecretConfig;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
        public SdsSecretConfigOrBuilder getValidationContextSdsSecretConfigOrBuilder() {
            return getValidationContextSdsSecretConfig();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
        public CertificateProvider getValidationContextCertificateProvider() {
            CertificateProvider certificateProvider = this.validationContextCertificateProvider_;
            return certificateProvider == null ? CertificateProvider.getDefaultInstance() : certificateProvider;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
        public CertificateProviderOrBuilder getValidationContextCertificateProviderOrBuilder() {
            return getValidationContextCertificateProvider();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
        public CertificateProviderInstance getValidationContextCertificateProviderInstance() {
            CertificateProviderInstance certificateProviderInstance = this.validationContextCertificateProviderInstance_;
            return certificateProviderInstance == null ? CertificateProviderInstance.getDefaultInstance() : certificateProviderInstance;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
        public CertificateProviderInstanceOrBuilder getValidationContextCertificateProviderInstanceOrBuilder() {
            return getValidationContextCertificateProviderInstance();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.defaultValidationContext_ != null) {
                codedOutputStream.writeMessage(1, getDefaultValidationContext());
            }
            if (this.validationContextSdsSecretConfig_ != null) {
                codedOutputStream.writeMessage(2, getValidationContextSdsSecretConfig());
            }
            if (this.validationContextCertificateProvider_ != null) {
                codedOutputStream.writeMessage(3, getValidationContextCertificateProvider());
            }
            if (this.validationContextCertificateProviderInstance_ != null) {
                codedOutputStream.writeMessage(4, getValidationContextCertificateProviderInstance());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = this.defaultValidationContext_ != null ? CodedOutputStream.computeMessageSize(1, getDefaultValidationContext()) : 0;
            if (this.validationContextSdsSecretConfig_ != null) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getValidationContextSdsSecretConfig());
            }
            if (this.validationContextCertificateProvider_ != null) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getValidationContextCertificateProvider());
            }
            if (this.validationContextCertificateProviderInstance_ != null) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(4, getValidationContextCertificateProviderInstance());
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CombinedCertificateValidationContext)) {
                return super.equals(obj);
            }
            CombinedCertificateValidationContext combinedCertificateValidationContext = (CombinedCertificateValidationContext) obj;
            if (hasDefaultValidationContext() != combinedCertificateValidationContext.hasDefaultValidationContext()) {
                return false;
            }
            if ((hasDefaultValidationContext() && !getDefaultValidationContext().equals(combinedCertificateValidationContext.getDefaultValidationContext())) || hasValidationContextSdsSecretConfig() != combinedCertificateValidationContext.hasValidationContextSdsSecretConfig()) {
                return false;
            }
            if ((hasValidationContextSdsSecretConfig() && !getValidationContextSdsSecretConfig().equals(combinedCertificateValidationContext.getValidationContextSdsSecretConfig())) || hasValidationContextCertificateProvider() != combinedCertificateValidationContext.hasValidationContextCertificateProvider()) {
                return false;
            }
            if ((!hasValidationContextCertificateProvider() || getValidationContextCertificateProvider().equals(combinedCertificateValidationContext.getValidationContextCertificateProvider())) && hasValidationContextCertificateProviderInstance() == combinedCertificateValidationContext.hasValidationContextCertificateProviderInstance()) {
                return (!hasValidationContextCertificateProviderInstance() || getValidationContextCertificateProviderInstance().equals(combinedCertificateValidationContext.getValidationContextCertificateProviderInstance())) && this.unknownFields.equals(combinedCertificateValidationContext.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasDefaultValidationContext()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getDefaultValidationContext().hashCode();
            }
            if (hasValidationContextSdsSecretConfig()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getValidationContextSdsSecretConfig().hashCode();
            }
            if (hasValidationContextCertificateProvider()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getValidationContextCertificateProvider().hashCode();
            }
            if (hasValidationContextCertificateProviderInstance()) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + getValidationContextCertificateProviderInstance().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31787newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31790toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CombinedCertificateValidationContextOrBuilder {
            private SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> defaultValidationContextBuilder_;
            private CertificateValidationContext defaultValidationContext_;
            private SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> validationContextCertificateProviderBuilder_;
            private SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> validationContextCertificateProviderInstanceBuilder_;
            private CertificateProviderInstance validationContextCertificateProviderInstance_;
            private CertificateProvider validationContextCertificateProvider_;
            private SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> validationContextSdsSecretConfigBuilder_;
            private SdsSecretConfig validationContextSdsSecretConfig_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CombinedCertificateValidationContext_descriptor;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
            public boolean hasDefaultValidationContext() {
                return (this.defaultValidationContextBuilder_ == null && this.defaultValidationContext_ == null) ? false : true;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
            public boolean hasValidationContextCertificateProvider() {
                return (this.validationContextCertificateProviderBuilder_ == null && this.validationContextCertificateProvider_ == null) ? false : true;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
            public boolean hasValidationContextCertificateProviderInstance() {
                return (this.validationContextCertificateProviderInstanceBuilder_ == null && this.validationContextCertificateProviderInstance_ == null) ? false : true;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
            public boolean hasValidationContextSdsSecretConfig() {
                return (this.validationContextSdsSecretConfigBuilder_ == null && this.validationContextSdsSecretConfig_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CombinedCertificateValidationContext_fieldAccessorTable.ensureFieldAccessorsInitialized(CombinedCertificateValidationContext.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CombinedCertificateValidationContext.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31801clear() {
                super.clear();
                if (this.defaultValidationContextBuilder_ == null) {
                    this.defaultValidationContext_ = null;
                } else {
                    this.defaultValidationContext_ = null;
                    this.defaultValidationContextBuilder_ = null;
                }
                if (this.validationContextSdsSecretConfigBuilder_ == null) {
                    this.validationContextSdsSecretConfig_ = null;
                } else {
                    this.validationContextSdsSecretConfig_ = null;
                    this.validationContextSdsSecretConfigBuilder_ = null;
                }
                if (this.validationContextCertificateProviderBuilder_ == null) {
                    this.validationContextCertificateProvider_ = null;
                } else {
                    this.validationContextCertificateProvider_ = null;
                    this.validationContextCertificateProviderBuilder_ = null;
                }
                if (this.validationContextCertificateProviderInstanceBuilder_ == null) {
                    this.validationContextCertificateProviderInstance_ = null;
                } else {
                    this.validationContextCertificateProviderInstance_ = null;
                    this.validationContextCertificateProviderInstanceBuilder_ = null;
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CombinedCertificateValidationContext_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CombinedCertificateValidationContext m31814getDefaultInstanceForType() {
                return CombinedCertificateValidationContext.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CombinedCertificateValidationContext m31795build() throws UninitializedMessageException {
                CombinedCertificateValidationContext combinedCertificateValidationContextM31797buildPartial = m31797buildPartial();
                if (combinedCertificateValidationContextM31797buildPartial.isInitialized()) {
                    return combinedCertificateValidationContextM31797buildPartial;
                }
                throw newUninitializedMessageException(combinedCertificateValidationContextM31797buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CombinedCertificateValidationContext m31797buildPartial() {
                CombinedCertificateValidationContext combinedCertificateValidationContext = new CombinedCertificateValidationContext(this);
                SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.defaultValidationContextBuilder_;
                if (singleFieldBuilderV3 == null) {
                    combinedCertificateValidationContext.defaultValidationContext_ = this.defaultValidationContext_;
                } else {
                    combinedCertificateValidationContext.defaultValidationContext_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV32 = this.validationContextSdsSecretConfigBuilder_;
                if (singleFieldBuilderV32 == null) {
                    combinedCertificateValidationContext.validationContextSdsSecretConfig_ = this.validationContextSdsSecretConfig_;
                } else {
                    combinedCertificateValidationContext.validationContextSdsSecretConfig_ = singleFieldBuilderV32.build();
                }
                SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV33 = this.validationContextCertificateProviderBuilder_;
                if (singleFieldBuilderV33 == null) {
                    combinedCertificateValidationContext.validationContextCertificateProvider_ = this.validationContextCertificateProvider_;
                } else {
                    combinedCertificateValidationContext.validationContextCertificateProvider_ = singleFieldBuilderV33.build();
                }
                SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV34 = this.validationContextCertificateProviderInstanceBuilder_;
                if (singleFieldBuilderV34 == null) {
                    combinedCertificateValidationContext.validationContextCertificateProviderInstance_ = this.validationContextCertificateProviderInstance_;
                } else {
                    combinedCertificateValidationContext.validationContextCertificateProviderInstance_ = singleFieldBuilderV34.build();
                }
                onBuilt();
                return combinedCertificateValidationContext;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31813clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31825setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31803clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31806clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31827setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31793addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31818mergeFrom(Message message) {
                if (message instanceof CombinedCertificateValidationContext) {
                    return mergeFrom((CombinedCertificateValidationContext) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CombinedCertificateValidationContext combinedCertificateValidationContext) {
                if (combinedCertificateValidationContext == CombinedCertificateValidationContext.getDefaultInstance()) {
                    return this;
                }
                if (combinedCertificateValidationContext.hasDefaultValidationContext()) {
                    mergeDefaultValidationContext(combinedCertificateValidationContext.getDefaultValidationContext());
                }
                if (combinedCertificateValidationContext.hasValidationContextSdsSecretConfig()) {
                    mergeValidationContextSdsSecretConfig(combinedCertificateValidationContext.getValidationContextSdsSecretConfig());
                }
                if (combinedCertificateValidationContext.hasValidationContextCertificateProvider()) {
                    mergeValidationContextCertificateProvider(combinedCertificateValidationContext.getValidationContextCertificateProvider());
                }
                if (combinedCertificateValidationContext.hasValidationContextCertificateProviderInstance()) {
                    mergeValidationContextCertificateProviderInstance(combinedCertificateValidationContext.getValidationContextCertificateProviderInstance());
                }
                m31823mergeUnknownFields(combinedCertificateValidationContext.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContext.Builder m31819mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContext.access$3100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext$CombinedCertificateValidationContext r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContext) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext$CombinedCertificateValidationContext r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContext) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContext.Builder.m31819mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext$CombinedCertificateValidationContext$Builder");
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
            public CertificateValidationContext getDefaultValidationContext() {
                SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.defaultValidationContextBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                CertificateValidationContext certificateValidationContext = this.defaultValidationContext_;
                return certificateValidationContext == null ? CertificateValidationContext.getDefaultInstance() : certificateValidationContext;
            }

            public Builder setDefaultValidationContext(CertificateValidationContext certificateValidationContext) {
                SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.defaultValidationContextBuilder_;
                if (singleFieldBuilderV3 == null) {
                    certificateValidationContext.getClass();
                    this.defaultValidationContext_ = certificateValidationContext;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(certificateValidationContext);
                }
                return this;
            }

            public Builder setDefaultValidationContext(CertificateValidationContext.Builder builder) {
                SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.defaultValidationContextBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.defaultValidationContext_ = builder.m31609build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m31609build());
                }
                return this;
            }

            public Builder mergeDefaultValidationContext(CertificateValidationContext certificateValidationContext) {
                SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.defaultValidationContextBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CertificateValidationContext certificateValidationContext2 = this.defaultValidationContext_;
                    if (certificateValidationContext2 != null) {
                        this.defaultValidationContext_ = CertificateValidationContext.newBuilder(certificateValidationContext2).mergeFrom(certificateValidationContext).m31611buildPartial();
                    } else {
                        this.defaultValidationContext_ = certificateValidationContext;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(certificateValidationContext);
                }
                return this;
            }

            public Builder clearDefaultValidationContext() {
                if (this.defaultValidationContextBuilder_ == null) {
                    this.defaultValidationContext_ = null;
                    onChanged();
                } else {
                    this.defaultValidationContext_ = null;
                    this.defaultValidationContextBuilder_ = null;
                }
                return this;
            }

            public CertificateValidationContext.Builder getDefaultValidationContextBuilder() {
                onChanged();
                return getDefaultValidationContextFieldBuilder().getBuilder();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
            public CertificateValidationContextOrBuilder getDefaultValidationContextOrBuilder() {
                SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.defaultValidationContextBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (CertificateValidationContextOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                CertificateValidationContext certificateValidationContext = this.defaultValidationContext_;
                return certificateValidationContext == null ? CertificateValidationContext.getDefaultInstance() : certificateValidationContext;
            }

            private SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> getDefaultValidationContextFieldBuilder() {
                if (this.defaultValidationContextBuilder_ == null) {
                    this.defaultValidationContextBuilder_ = new SingleFieldBuilderV3<>(getDefaultValidationContext(), getParentForChildren(), isClean());
                    this.defaultValidationContext_ = null;
                }
                return this.defaultValidationContextBuilder_;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
            public SdsSecretConfig getValidationContextSdsSecretConfig() {
                SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.validationContextSdsSecretConfigBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                SdsSecretConfig sdsSecretConfig = this.validationContextSdsSecretConfig_;
                return sdsSecretConfig == null ? SdsSecretConfig.getDefaultInstance() : sdsSecretConfig;
            }

            public Builder setValidationContextSdsSecretConfig(SdsSecretConfig sdsSecretConfig) {
                SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.validationContextSdsSecretConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    sdsSecretConfig.getClass();
                    this.validationContextSdsSecretConfig_ = sdsSecretConfig;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(sdsSecretConfig);
                }
                return this;
            }

            public Builder setValidationContextSdsSecretConfig(SdsSecretConfig.Builder builder) {
                SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.validationContextSdsSecretConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.validationContextSdsSecretConfig_ = builder.m31979build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m31979build());
                }
                return this;
            }

            public Builder mergeValidationContextSdsSecretConfig(SdsSecretConfig sdsSecretConfig) {
                SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.validationContextSdsSecretConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    SdsSecretConfig sdsSecretConfig2 = this.validationContextSdsSecretConfig_;
                    if (sdsSecretConfig2 != null) {
                        this.validationContextSdsSecretConfig_ = SdsSecretConfig.newBuilder(sdsSecretConfig2).mergeFrom(sdsSecretConfig).m31981buildPartial();
                    } else {
                        this.validationContextSdsSecretConfig_ = sdsSecretConfig;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(sdsSecretConfig);
                }
                return this;
            }

            public Builder clearValidationContextSdsSecretConfig() {
                if (this.validationContextSdsSecretConfigBuilder_ == null) {
                    this.validationContextSdsSecretConfig_ = null;
                    onChanged();
                } else {
                    this.validationContextSdsSecretConfig_ = null;
                    this.validationContextSdsSecretConfigBuilder_ = null;
                }
                return this;
            }

            public SdsSecretConfig.Builder getValidationContextSdsSecretConfigBuilder() {
                onChanged();
                return getValidationContextSdsSecretConfigFieldBuilder().getBuilder();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
            public SdsSecretConfigOrBuilder getValidationContextSdsSecretConfigOrBuilder() {
                SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.validationContextSdsSecretConfigBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (SdsSecretConfigOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                SdsSecretConfig sdsSecretConfig = this.validationContextSdsSecretConfig_;
                return sdsSecretConfig == null ? SdsSecretConfig.getDefaultInstance() : sdsSecretConfig;
            }

            private SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> getValidationContextSdsSecretConfigFieldBuilder() {
                if (this.validationContextSdsSecretConfigBuilder_ == null) {
                    this.validationContextSdsSecretConfigBuilder_ = new SingleFieldBuilderV3<>(getValidationContextSdsSecretConfig(), getParentForChildren(), isClean());
                    this.validationContextSdsSecretConfig_ = null;
                }
                return this.validationContextSdsSecretConfigBuilder_;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
            public CertificateProvider getValidationContextCertificateProvider() {
                SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                CertificateProvider certificateProvider = this.validationContextCertificateProvider_;
                return certificateProvider == null ? CertificateProvider.getDefaultInstance() : certificateProvider;
            }

            public Builder setValidationContextCertificateProvider(CertificateProvider certificateProvider) {
                SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderBuilder_;
                if (singleFieldBuilderV3 == null) {
                    certificateProvider.getClass();
                    this.validationContextCertificateProvider_ = certificateProvider;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(certificateProvider);
                }
                return this;
            }

            public Builder setValidationContextCertificateProvider(CertificateProvider.Builder builder) {
                SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.validationContextCertificateProvider_ = builder.m31703build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m31703build());
                }
                return this;
            }

            public Builder mergeValidationContextCertificateProvider(CertificateProvider certificateProvider) {
                SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CertificateProvider certificateProvider2 = this.validationContextCertificateProvider_;
                    if (certificateProvider2 != null) {
                        this.validationContextCertificateProvider_ = CertificateProvider.newBuilder(certificateProvider2).mergeFrom(certificateProvider).m31705buildPartial();
                    } else {
                        this.validationContextCertificateProvider_ = certificateProvider;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(certificateProvider);
                }
                return this;
            }

            public Builder clearValidationContextCertificateProvider() {
                if (this.validationContextCertificateProviderBuilder_ == null) {
                    this.validationContextCertificateProvider_ = null;
                    onChanged();
                } else {
                    this.validationContextCertificateProvider_ = null;
                    this.validationContextCertificateProviderBuilder_ = null;
                }
                return this;
            }

            public CertificateProvider.Builder getValidationContextCertificateProviderBuilder() {
                onChanged();
                return getValidationContextCertificateProviderFieldBuilder().getBuilder();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
            public CertificateProviderOrBuilder getValidationContextCertificateProviderOrBuilder() {
                SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (CertificateProviderOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                CertificateProvider certificateProvider = this.validationContextCertificateProvider_;
                return certificateProvider == null ? CertificateProvider.getDefaultInstance() : certificateProvider;
            }

            private SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> getValidationContextCertificateProviderFieldBuilder() {
                if (this.validationContextCertificateProviderBuilder_ == null) {
                    this.validationContextCertificateProviderBuilder_ = new SingleFieldBuilderV3<>(getValidationContextCertificateProvider(), getParentForChildren(), isClean());
                    this.validationContextCertificateProvider_ = null;
                }
                return this.validationContextCertificateProviderBuilder_;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
            public CertificateProviderInstance getValidationContextCertificateProviderInstance() {
                SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderInstanceBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                CertificateProviderInstance certificateProviderInstance = this.validationContextCertificateProviderInstance_;
                return certificateProviderInstance == null ? CertificateProviderInstance.getDefaultInstance() : certificateProviderInstance;
            }

            public Builder setValidationContextCertificateProviderInstance(CertificateProviderInstance certificateProviderInstance) {
                SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderInstanceBuilder_;
                if (singleFieldBuilderV3 == null) {
                    certificateProviderInstance.getClass();
                    this.validationContextCertificateProviderInstance_ = certificateProviderInstance;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(certificateProviderInstance);
                }
                return this;
            }

            public Builder setValidationContextCertificateProviderInstance(CertificateProviderInstance.Builder builder) {
                SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderInstanceBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.validationContextCertificateProviderInstance_ = builder.m31749build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m31749build());
                }
                return this;
            }

            public Builder mergeValidationContextCertificateProviderInstance(CertificateProviderInstance certificateProviderInstance) {
                SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderInstanceBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CertificateProviderInstance certificateProviderInstance2 = this.validationContextCertificateProviderInstance_;
                    if (certificateProviderInstance2 != null) {
                        this.validationContextCertificateProviderInstance_ = CertificateProviderInstance.newBuilder(certificateProviderInstance2).mergeFrom(certificateProviderInstance).m31751buildPartial();
                    } else {
                        this.validationContextCertificateProviderInstance_ = certificateProviderInstance;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(certificateProviderInstance);
                }
                return this;
            }

            public Builder clearValidationContextCertificateProviderInstance() {
                if (this.validationContextCertificateProviderInstanceBuilder_ == null) {
                    this.validationContextCertificateProviderInstance_ = null;
                    onChanged();
                } else {
                    this.validationContextCertificateProviderInstance_ = null;
                    this.validationContextCertificateProviderInstanceBuilder_ = null;
                }
                return this;
            }

            public CertificateProviderInstance.Builder getValidationContextCertificateProviderInstanceBuilder() {
                onChanged();
                return getValidationContextCertificateProviderInstanceFieldBuilder().getBuilder();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextOrBuilder
            public CertificateProviderInstanceOrBuilder getValidationContextCertificateProviderInstanceOrBuilder() {
                SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderInstanceBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (CertificateProviderInstanceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                CertificateProviderInstance certificateProviderInstance = this.validationContextCertificateProviderInstance_;
                return certificateProviderInstance == null ? CertificateProviderInstance.getDefaultInstance() : certificateProviderInstance;
            }

            private SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> getValidationContextCertificateProviderInstanceFieldBuilder() {
                if (this.validationContextCertificateProviderInstanceBuilder_ == null) {
                    this.validationContextCertificateProviderInstanceBuilder_ = new SingleFieldBuilderV3<>(getValidationContextCertificateProviderInstance(), getParentForChildren(), isClean());
                    this.validationContextCertificateProviderInstance_ = null;
                }
                return this.validationContextCertificateProviderInstanceBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m31829setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m31823mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CommonTlsContextOrBuilder {
        private LazyStringList alpnProtocols_;
        private int bitField0_;
        private SingleFieldBuilderV3<CombinedCertificateValidationContext, CombinedCertificateValidationContext.Builder, CombinedCertificateValidationContextOrBuilder> combinedValidationContextBuilder_;
        private SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> tlsCertificateCertificateProviderBuilder_;
        private SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> tlsCertificateCertificateProviderInstanceBuilder_;
        private CertificateProviderInstance tlsCertificateCertificateProviderInstance_;
        private CertificateProvider tlsCertificateCertificateProvider_;
        private RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> tlsCertificateSdsSecretConfigsBuilder_;
        private List<SdsSecretConfig> tlsCertificateSdsSecretConfigs_;
        private RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> tlsCertificatesBuilder_;
        private List<TlsCertificate> tlsCertificates_;
        private SingleFieldBuilderV3<TlsParameters, TlsParameters.Builder, TlsParametersOrBuilder> tlsParamsBuilder_;
        private TlsParameters tlsParams_;
        private SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> validationContextBuilder_;
        private SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> validationContextCertificateProviderBuilder_;
        private SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> validationContextCertificateProviderInstanceBuilder_;
        private SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> validationContextSdsSecretConfigBuilder_;
        private int validationContextTypeCase_;
        private Object validationContextType_;

        private Builder() {
            this.validationContextTypeCase_ = 0;
            this.tlsCertificates_ = Collections.emptyList();
            this.tlsCertificateSdsSecretConfigs_ = Collections.emptyList();
            this.alpnProtocols_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.validationContextTypeCase_ = 0;
            this.tlsCertificates_ = Collections.emptyList();
            this.tlsCertificateSdsSecretConfigs_ = Collections.emptyList();
            this.alpnProtocols_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_descriptor;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public boolean hasCombinedValidationContext() {
            return this.validationContextTypeCase_ == 8;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public boolean hasTlsCertificateCertificateProvider() {
            return (this.tlsCertificateCertificateProviderBuilder_ == null && this.tlsCertificateCertificateProvider_ == null) ? false : true;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public boolean hasTlsCertificateCertificateProviderInstance() {
            return (this.tlsCertificateCertificateProviderInstanceBuilder_ == null && this.tlsCertificateCertificateProviderInstance_ == null) ? false : true;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public boolean hasTlsParams() {
            return (this.tlsParamsBuilder_ == null && this.tlsParams_ == null) ? false : true;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public boolean hasValidationContext() {
            return this.validationContextTypeCase_ == 3;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public boolean hasValidationContextCertificateProvider() {
            return this.validationContextTypeCase_ == 10;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public boolean hasValidationContextCertificateProviderInstance() {
            return this.validationContextTypeCase_ == 12;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public boolean hasValidationContextSdsSecretConfig() {
            return this.validationContextTypeCase_ == 7;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_fieldAccessorTable.ensureFieldAccessorsInitialized(CommonTlsContext.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (CommonTlsContext.alwaysUseFieldBuilders) {
                getTlsCertificatesFieldBuilder();
                getTlsCertificateSdsSecretConfigsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31663clear() {
            super.clear();
            if (this.tlsParamsBuilder_ == null) {
                this.tlsParams_ = null;
            } else {
                this.tlsParams_ = null;
                this.tlsParamsBuilder_ = null;
            }
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.tlsCertificates_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV32 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.tlsCertificateSdsSecretConfigs_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            if (this.tlsCertificateCertificateProviderBuilder_ == null) {
                this.tlsCertificateCertificateProvider_ = null;
            } else {
                this.tlsCertificateCertificateProvider_ = null;
                this.tlsCertificateCertificateProviderBuilder_ = null;
            }
            if (this.tlsCertificateCertificateProviderInstanceBuilder_ == null) {
                this.tlsCertificateCertificateProviderInstance_ = null;
            } else {
                this.tlsCertificateCertificateProviderInstance_ = null;
                this.tlsCertificateCertificateProviderInstanceBuilder_ = null;
            }
            this.alpnProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            this.validationContextTypeCase_ = 0;
            this.validationContextType_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return TlsProto.internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CommonTlsContext m31676getDefaultInstanceForType() {
            return CommonTlsContext.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CommonTlsContext m31657build() throws UninitializedMessageException {
            CommonTlsContext commonTlsContextM31659buildPartial = m31659buildPartial();
            if (commonTlsContextM31659buildPartial.isInitialized()) {
                return commonTlsContextM31659buildPartial;
            }
            throw newUninitializedMessageException(commonTlsContextM31659buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CommonTlsContext m31659buildPartial() {
            CommonTlsContext commonTlsContext = new CommonTlsContext(this);
            SingleFieldBuilderV3<TlsParameters, TlsParameters.Builder, TlsParametersOrBuilder> singleFieldBuilderV3 = this.tlsParamsBuilder_;
            if (singleFieldBuilderV3 == null) {
                commonTlsContext.tlsParams_ = this.tlsParams_;
            } else {
                commonTlsContext.tlsParams_ = singleFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.tlsCertificates_ = Collections.unmodifiableList(this.tlsCertificates_);
                    this.bitField0_ &= -2;
                }
                commonTlsContext.tlsCertificates_ = this.tlsCertificates_;
            } else {
                commonTlsContext.tlsCertificates_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV32 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 2) != 0) {
                    this.tlsCertificateSdsSecretConfigs_ = Collections.unmodifiableList(this.tlsCertificateSdsSecretConfigs_);
                    this.bitField0_ &= -3;
                }
                commonTlsContext.tlsCertificateSdsSecretConfigs_ = this.tlsCertificateSdsSecretConfigs_;
            } else {
                commonTlsContext.tlsCertificateSdsSecretConfigs_ = repeatedFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV32 = this.tlsCertificateCertificateProviderBuilder_;
            if (singleFieldBuilderV32 == null) {
                commonTlsContext.tlsCertificateCertificateProvider_ = this.tlsCertificateCertificateProvider_;
            } else {
                commonTlsContext.tlsCertificateCertificateProvider_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV33 = this.tlsCertificateCertificateProviderInstanceBuilder_;
            if (singleFieldBuilderV33 == null) {
                commonTlsContext.tlsCertificateCertificateProviderInstance_ = this.tlsCertificateCertificateProviderInstance_;
            } else {
                commonTlsContext.tlsCertificateCertificateProviderInstance_ = singleFieldBuilderV33.build();
            }
            if (this.validationContextTypeCase_ == 3) {
                SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV34 = this.validationContextBuilder_;
                if (singleFieldBuilderV34 == null) {
                    commonTlsContext.validationContextType_ = this.validationContextType_;
                } else {
                    commonTlsContext.validationContextType_ = singleFieldBuilderV34.build();
                }
            }
            if (this.validationContextTypeCase_ == 7) {
                SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV35 = this.validationContextSdsSecretConfigBuilder_;
                if (singleFieldBuilderV35 == null) {
                    commonTlsContext.validationContextType_ = this.validationContextType_;
                } else {
                    commonTlsContext.validationContextType_ = singleFieldBuilderV35.build();
                }
            }
            if (this.validationContextTypeCase_ == 8) {
                SingleFieldBuilderV3<CombinedCertificateValidationContext, CombinedCertificateValidationContext.Builder, CombinedCertificateValidationContextOrBuilder> singleFieldBuilderV36 = this.combinedValidationContextBuilder_;
                if (singleFieldBuilderV36 == null) {
                    commonTlsContext.validationContextType_ = this.validationContextType_;
                } else {
                    commonTlsContext.validationContextType_ = singleFieldBuilderV36.build();
                }
            }
            if (this.validationContextTypeCase_ == 10) {
                SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV37 = this.validationContextCertificateProviderBuilder_;
                if (singleFieldBuilderV37 == null) {
                    commonTlsContext.validationContextType_ = this.validationContextType_;
                } else {
                    commonTlsContext.validationContextType_ = singleFieldBuilderV37.build();
                }
            }
            if (this.validationContextTypeCase_ == 12) {
                SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV38 = this.validationContextCertificateProviderInstanceBuilder_;
                if (singleFieldBuilderV38 == null) {
                    commonTlsContext.validationContextType_ = this.validationContextType_;
                } else {
                    commonTlsContext.validationContextType_ = singleFieldBuilderV38.build();
                }
            }
            if ((this.bitField0_ & 4) != 0) {
                this.alpnProtocols_ = this.alpnProtocols_.getUnmodifiableView();
                this.bitField0_ &= -5;
            }
            commonTlsContext.alpnProtocols_ = this.alpnProtocols_;
            commonTlsContext.validationContextTypeCase_ = this.validationContextTypeCase_;
            onBuilt();
            return commonTlsContext;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31675clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31687setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31665clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31668clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31689setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31655addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31680mergeFrom(Message message) {
            if (message instanceof CommonTlsContext) {
                return mergeFrom((CommonTlsContext) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(CommonTlsContext commonTlsContext) {
            if (commonTlsContext == CommonTlsContext.getDefaultInstance()) {
                return this;
            }
            if (commonTlsContext.hasTlsParams()) {
                mergeTlsParams(commonTlsContext.getTlsParams());
            }
            if (this.tlsCertificatesBuilder_ == null) {
                if (!commonTlsContext.tlsCertificates_.isEmpty()) {
                    if (this.tlsCertificates_.isEmpty()) {
                        this.tlsCertificates_ = commonTlsContext.tlsCertificates_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureTlsCertificatesIsMutable();
                        this.tlsCertificates_.addAll(commonTlsContext.tlsCertificates_);
                    }
                    onChanged();
                }
            } else if (!commonTlsContext.tlsCertificates_.isEmpty()) {
                if (!this.tlsCertificatesBuilder_.isEmpty()) {
                    this.tlsCertificatesBuilder_.addAllMessages(commonTlsContext.tlsCertificates_);
                } else {
                    this.tlsCertificatesBuilder_.dispose();
                    this.tlsCertificatesBuilder_ = null;
                    this.tlsCertificates_ = commonTlsContext.tlsCertificates_;
                    this.bitField0_ &= -2;
                    this.tlsCertificatesBuilder_ = CommonTlsContext.alwaysUseFieldBuilders ? getTlsCertificatesFieldBuilder() : null;
                }
            }
            if (this.tlsCertificateSdsSecretConfigsBuilder_ == null) {
                if (!commonTlsContext.tlsCertificateSdsSecretConfigs_.isEmpty()) {
                    if (this.tlsCertificateSdsSecretConfigs_.isEmpty()) {
                        this.tlsCertificateSdsSecretConfigs_ = commonTlsContext.tlsCertificateSdsSecretConfigs_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureTlsCertificateSdsSecretConfigsIsMutable();
                        this.tlsCertificateSdsSecretConfigs_.addAll(commonTlsContext.tlsCertificateSdsSecretConfigs_);
                    }
                    onChanged();
                }
            } else if (!commonTlsContext.tlsCertificateSdsSecretConfigs_.isEmpty()) {
                if (!this.tlsCertificateSdsSecretConfigsBuilder_.isEmpty()) {
                    this.tlsCertificateSdsSecretConfigsBuilder_.addAllMessages(commonTlsContext.tlsCertificateSdsSecretConfigs_);
                } else {
                    this.tlsCertificateSdsSecretConfigsBuilder_.dispose();
                    this.tlsCertificateSdsSecretConfigsBuilder_ = null;
                    this.tlsCertificateSdsSecretConfigs_ = commonTlsContext.tlsCertificateSdsSecretConfigs_;
                    this.bitField0_ &= -3;
                    this.tlsCertificateSdsSecretConfigsBuilder_ = CommonTlsContext.alwaysUseFieldBuilders ? getTlsCertificateSdsSecretConfigsFieldBuilder() : null;
                }
            }
            if (commonTlsContext.hasTlsCertificateCertificateProvider()) {
                mergeTlsCertificateCertificateProvider(commonTlsContext.getTlsCertificateCertificateProvider());
            }
            if (commonTlsContext.hasTlsCertificateCertificateProviderInstance()) {
                mergeTlsCertificateCertificateProviderInstance(commonTlsContext.getTlsCertificateCertificateProviderInstance());
            }
            if (!commonTlsContext.alpnProtocols_.isEmpty()) {
                if (this.alpnProtocols_.isEmpty()) {
                    this.alpnProtocols_ = commonTlsContext.alpnProtocols_;
                    this.bitField0_ &= -5;
                } else {
                    ensureAlpnProtocolsIsMutable();
                    this.alpnProtocols_.addAll(commonTlsContext.alpnProtocols_);
                }
                onChanged();
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$CommonTlsContext$ValidationContextTypeCase[commonTlsContext.getValidationContextTypeCase().ordinal()];
            if (i == 1) {
                mergeValidationContext(commonTlsContext.getValidationContext());
            } else if (i == 2) {
                mergeValidationContextSdsSecretConfig(commonTlsContext.getValidationContextSdsSecretConfig());
            } else if (i == 3) {
                mergeCombinedValidationContext(commonTlsContext.getCombinedValidationContext());
            } else if (i == 4) {
                mergeValidationContextCertificateProvider(commonTlsContext.getValidationContextCertificateProvider());
            } else if (i == 5) {
                mergeValidationContextCertificateProviderInstance(commonTlsContext.getValidationContextCertificateProviderInstance());
            }
            m31685mergeUnknownFields(commonTlsContext.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.Builder m31681mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.access$4800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.Builder.m31681mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext$Builder");
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public ValidationContextTypeCase getValidationContextTypeCase() {
            return ValidationContextTypeCase.forNumber(this.validationContextTypeCase_);
        }

        public Builder clearValidationContextType() {
            this.validationContextTypeCase_ = 0;
            this.validationContextType_ = null;
            onChanged();
            return this;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public TlsParameters getTlsParams() {
            SingleFieldBuilderV3<TlsParameters, TlsParameters.Builder, TlsParametersOrBuilder> singleFieldBuilderV3 = this.tlsParamsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            TlsParameters tlsParameters = this.tlsParams_;
            return tlsParameters == null ? TlsParameters.getDefaultInstance() : tlsParameters;
        }

        public Builder setTlsParams(TlsParameters tlsParameters) {
            SingleFieldBuilderV3<TlsParameters, TlsParameters.Builder, TlsParametersOrBuilder> singleFieldBuilderV3 = this.tlsParamsBuilder_;
            if (singleFieldBuilderV3 == null) {
                tlsParameters.getClass();
                this.tlsParams_ = tlsParameters;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(tlsParameters);
            }
            return this;
        }

        public Builder setTlsParams(TlsParameters.Builder builder) {
            SingleFieldBuilderV3<TlsParameters, TlsParameters.Builder, TlsParametersOrBuilder> singleFieldBuilderV3 = this.tlsParamsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.tlsParams_ = builder.m32119build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m32119build());
            }
            return this;
        }

        public Builder mergeTlsParams(TlsParameters tlsParameters) {
            SingleFieldBuilderV3<TlsParameters, TlsParameters.Builder, TlsParametersOrBuilder> singleFieldBuilderV3 = this.tlsParamsBuilder_;
            if (singleFieldBuilderV3 == null) {
                TlsParameters tlsParameters2 = this.tlsParams_;
                if (tlsParameters2 != null) {
                    this.tlsParams_ = TlsParameters.newBuilder(tlsParameters2).mergeFrom(tlsParameters).m32121buildPartial();
                } else {
                    this.tlsParams_ = tlsParameters;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(tlsParameters);
            }
            return this;
        }

        public Builder clearTlsParams() {
            if (this.tlsParamsBuilder_ == null) {
                this.tlsParams_ = null;
                onChanged();
            } else {
                this.tlsParams_ = null;
                this.tlsParamsBuilder_ = null;
            }
            return this;
        }

        public TlsParameters.Builder getTlsParamsBuilder() {
            onChanged();
            return getTlsParamsFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public TlsParametersOrBuilder getTlsParamsOrBuilder() {
            SingleFieldBuilderV3<TlsParameters, TlsParameters.Builder, TlsParametersOrBuilder> singleFieldBuilderV3 = this.tlsParamsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (TlsParametersOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            TlsParameters tlsParameters = this.tlsParams_;
            return tlsParameters == null ? TlsParameters.getDefaultInstance() : tlsParameters;
        }

        private SingleFieldBuilderV3<TlsParameters, TlsParameters.Builder, TlsParametersOrBuilder> getTlsParamsFieldBuilder() {
            if (this.tlsParamsBuilder_ == null) {
                this.tlsParamsBuilder_ = new SingleFieldBuilderV3<>(getTlsParams(), getParentForChildren(), isClean());
                this.tlsParams_ = null;
            }
            return this.tlsParamsBuilder_;
        }

        private void ensureTlsCertificatesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.tlsCertificates_ = new ArrayList(this.tlsCertificates_);
                this.bitField0_ |= 1;
            }
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public List<TlsCertificate> getTlsCertificatesList() {
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.tlsCertificates_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public int getTlsCertificatesCount() {
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.tlsCertificates_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public TlsCertificate getTlsCertificates(int i) {
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.tlsCertificates_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setTlsCertificates(int i, TlsCertificate tlsCertificate) {
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                tlsCertificate.getClass();
                ensureTlsCertificatesIsMutable();
                this.tlsCertificates_.set(i, tlsCertificate);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, tlsCertificate);
            }
            return this;
        }

        public Builder setTlsCertificates(int i, TlsCertificate.Builder builder) {
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTlsCertificatesIsMutable();
                this.tlsCertificates_.set(i, builder.m32071build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m32071build());
            }
            return this;
        }

        public Builder addTlsCertificates(TlsCertificate tlsCertificate) {
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                tlsCertificate.getClass();
                ensureTlsCertificatesIsMutable();
                this.tlsCertificates_.add(tlsCertificate);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(tlsCertificate);
            }
            return this;
        }

        public Builder addTlsCertificates(int i, TlsCertificate tlsCertificate) {
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                tlsCertificate.getClass();
                ensureTlsCertificatesIsMutable();
                this.tlsCertificates_.add(i, tlsCertificate);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, tlsCertificate);
            }
            return this;
        }

        public Builder addTlsCertificates(TlsCertificate.Builder builder) {
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTlsCertificatesIsMutable();
                this.tlsCertificates_.add(builder.m32071build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m32071build());
            }
            return this;
        }

        public Builder addTlsCertificates(int i, TlsCertificate.Builder builder) {
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTlsCertificatesIsMutable();
                this.tlsCertificates_.add(i, builder.m32071build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m32071build());
            }
            return this;
        }

        public Builder addAllTlsCertificates(Iterable<? extends TlsCertificate> iterable) {
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTlsCertificatesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.tlsCertificates_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearTlsCertificates() {
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.tlsCertificates_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeTlsCertificates(int i) {
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTlsCertificatesIsMutable();
                this.tlsCertificates_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public TlsCertificate.Builder getTlsCertificatesBuilder(int i) {
            return getTlsCertificatesFieldBuilder().getBuilder(i);
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public TlsCertificateOrBuilder getTlsCertificatesOrBuilder(int i) {
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.tlsCertificates_.get(i);
            }
            return (TlsCertificateOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public List<? extends TlsCertificateOrBuilder> getTlsCertificatesOrBuilderList() {
            RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificatesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.tlsCertificates_);
        }

        public TlsCertificate.Builder addTlsCertificatesBuilder() {
            return getTlsCertificatesFieldBuilder().addBuilder(TlsCertificate.getDefaultInstance());
        }

        public TlsCertificate.Builder addTlsCertificatesBuilder(int i) {
            return getTlsCertificatesFieldBuilder().addBuilder(i, TlsCertificate.getDefaultInstance());
        }

        public List<TlsCertificate.Builder> getTlsCertificatesBuilderList() {
            return getTlsCertificatesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> getTlsCertificatesFieldBuilder() {
            if (this.tlsCertificatesBuilder_ == null) {
                this.tlsCertificatesBuilder_ = new RepeatedFieldBuilderV3<>(this.tlsCertificates_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.tlsCertificates_ = null;
            }
            return this.tlsCertificatesBuilder_;
        }

        private void ensureTlsCertificateSdsSecretConfigsIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.tlsCertificateSdsSecretConfigs_ = new ArrayList(this.tlsCertificateSdsSecretConfigs_);
                this.bitField0_ |= 2;
            }
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public List<SdsSecretConfig> getTlsCertificateSdsSecretConfigsList() {
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.tlsCertificateSdsSecretConfigs_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public int getTlsCertificateSdsSecretConfigsCount() {
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.tlsCertificateSdsSecretConfigs_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public SdsSecretConfig getTlsCertificateSdsSecretConfigs(int i) {
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.tlsCertificateSdsSecretConfigs_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setTlsCertificateSdsSecretConfigs(int i, SdsSecretConfig sdsSecretConfig) {
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                sdsSecretConfig.getClass();
                ensureTlsCertificateSdsSecretConfigsIsMutable();
                this.tlsCertificateSdsSecretConfigs_.set(i, sdsSecretConfig);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, sdsSecretConfig);
            }
            return this;
        }

        public Builder setTlsCertificateSdsSecretConfigs(int i, SdsSecretConfig.Builder builder) {
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTlsCertificateSdsSecretConfigsIsMutable();
                this.tlsCertificateSdsSecretConfigs_.set(i, builder.m31979build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m31979build());
            }
            return this;
        }

        public Builder addTlsCertificateSdsSecretConfigs(SdsSecretConfig sdsSecretConfig) {
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                sdsSecretConfig.getClass();
                ensureTlsCertificateSdsSecretConfigsIsMutable();
                this.tlsCertificateSdsSecretConfigs_.add(sdsSecretConfig);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(sdsSecretConfig);
            }
            return this;
        }

        public Builder addTlsCertificateSdsSecretConfigs(int i, SdsSecretConfig sdsSecretConfig) {
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                sdsSecretConfig.getClass();
                ensureTlsCertificateSdsSecretConfigsIsMutable();
                this.tlsCertificateSdsSecretConfigs_.add(i, sdsSecretConfig);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, sdsSecretConfig);
            }
            return this;
        }

        public Builder addTlsCertificateSdsSecretConfigs(SdsSecretConfig.Builder builder) {
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTlsCertificateSdsSecretConfigsIsMutable();
                this.tlsCertificateSdsSecretConfigs_.add(builder.m31979build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m31979build());
            }
            return this;
        }

        public Builder addTlsCertificateSdsSecretConfigs(int i, SdsSecretConfig.Builder builder) {
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTlsCertificateSdsSecretConfigsIsMutable();
                this.tlsCertificateSdsSecretConfigs_.add(i, builder.m31979build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m31979build());
            }
            return this;
        }

        public Builder addAllTlsCertificateSdsSecretConfigs(Iterable<? extends SdsSecretConfig> iterable) {
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTlsCertificateSdsSecretConfigsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.tlsCertificateSdsSecretConfigs_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearTlsCertificateSdsSecretConfigs() {
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.tlsCertificateSdsSecretConfigs_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeTlsCertificateSdsSecretConfigs(int i) {
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTlsCertificateSdsSecretConfigsIsMutable();
                this.tlsCertificateSdsSecretConfigs_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public SdsSecretConfig.Builder getTlsCertificateSdsSecretConfigsBuilder(int i) {
            return getTlsCertificateSdsSecretConfigsFieldBuilder().getBuilder(i);
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public SdsSecretConfigOrBuilder getTlsCertificateSdsSecretConfigsOrBuilder(int i) {
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.tlsCertificateSdsSecretConfigs_.get(i);
            }
            return (SdsSecretConfigOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public List<? extends SdsSecretConfigOrBuilder> getTlsCertificateSdsSecretConfigsOrBuilderList() {
            RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> repeatedFieldBuilderV3 = this.tlsCertificateSdsSecretConfigsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.tlsCertificateSdsSecretConfigs_);
        }

        public SdsSecretConfig.Builder addTlsCertificateSdsSecretConfigsBuilder() {
            return getTlsCertificateSdsSecretConfigsFieldBuilder().addBuilder(SdsSecretConfig.getDefaultInstance());
        }

        public SdsSecretConfig.Builder addTlsCertificateSdsSecretConfigsBuilder(int i) {
            return getTlsCertificateSdsSecretConfigsFieldBuilder().addBuilder(i, SdsSecretConfig.getDefaultInstance());
        }

        public List<SdsSecretConfig.Builder> getTlsCertificateSdsSecretConfigsBuilderList() {
            return getTlsCertificateSdsSecretConfigsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> getTlsCertificateSdsSecretConfigsFieldBuilder() {
            if (this.tlsCertificateSdsSecretConfigsBuilder_ == null) {
                this.tlsCertificateSdsSecretConfigsBuilder_ = new RepeatedFieldBuilderV3<>(this.tlsCertificateSdsSecretConfigs_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.tlsCertificateSdsSecretConfigs_ = null;
            }
            return this.tlsCertificateSdsSecretConfigsBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public CertificateProvider getTlsCertificateCertificateProvider() {
            SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.tlsCertificateCertificateProviderBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            CertificateProvider certificateProvider = this.tlsCertificateCertificateProvider_;
            return certificateProvider == null ? CertificateProvider.getDefaultInstance() : certificateProvider;
        }

        public Builder setTlsCertificateCertificateProvider(CertificateProvider certificateProvider) {
            SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.tlsCertificateCertificateProviderBuilder_;
            if (singleFieldBuilderV3 == null) {
                certificateProvider.getClass();
                this.tlsCertificateCertificateProvider_ = certificateProvider;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(certificateProvider);
            }
            return this;
        }

        public Builder setTlsCertificateCertificateProvider(CertificateProvider.Builder builder) {
            SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.tlsCertificateCertificateProviderBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.tlsCertificateCertificateProvider_ = builder.m31703build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m31703build());
            }
            return this;
        }

        public Builder mergeTlsCertificateCertificateProvider(CertificateProvider certificateProvider) {
            SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.tlsCertificateCertificateProviderBuilder_;
            if (singleFieldBuilderV3 == null) {
                CertificateProvider certificateProvider2 = this.tlsCertificateCertificateProvider_;
                if (certificateProvider2 != null) {
                    this.tlsCertificateCertificateProvider_ = CertificateProvider.newBuilder(certificateProvider2).mergeFrom(certificateProvider).m31705buildPartial();
                } else {
                    this.tlsCertificateCertificateProvider_ = certificateProvider;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(certificateProvider);
            }
            return this;
        }

        public Builder clearTlsCertificateCertificateProvider() {
            if (this.tlsCertificateCertificateProviderBuilder_ == null) {
                this.tlsCertificateCertificateProvider_ = null;
                onChanged();
            } else {
                this.tlsCertificateCertificateProvider_ = null;
                this.tlsCertificateCertificateProviderBuilder_ = null;
            }
            return this;
        }

        public CertificateProvider.Builder getTlsCertificateCertificateProviderBuilder() {
            onChanged();
            return getTlsCertificateCertificateProviderFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public CertificateProviderOrBuilder getTlsCertificateCertificateProviderOrBuilder() {
            SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.tlsCertificateCertificateProviderBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (CertificateProviderOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            CertificateProvider certificateProvider = this.tlsCertificateCertificateProvider_;
            return certificateProvider == null ? CertificateProvider.getDefaultInstance() : certificateProvider;
        }

        private SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> getTlsCertificateCertificateProviderFieldBuilder() {
            if (this.tlsCertificateCertificateProviderBuilder_ == null) {
                this.tlsCertificateCertificateProviderBuilder_ = new SingleFieldBuilderV3<>(getTlsCertificateCertificateProvider(), getParentForChildren(), isClean());
                this.tlsCertificateCertificateProvider_ = null;
            }
            return this.tlsCertificateCertificateProviderBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public CertificateProviderInstance getTlsCertificateCertificateProviderInstance() {
            SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.tlsCertificateCertificateProviderInstanceBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            CertificateProviderInstance certificateProviderInstance = this.tlsCertificateCertificateProviderInstance_;
            return certificateProviderInstance == null ? CertificateProviderInstance.getDefaultInstance() : certificateProviderInstance;
        }

        public Builder setTlsCertificateCertificateProviderInstance(CertificateProviderInstance certificateProviderInstance) {
            SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.tlsCertificateCertificateProviderInstanceBuilder_;
            if (singleFieldBuilderV3 == null) {
                certificateProviderInstance.getClass();
                this.tlsCertificateCertificateProviderInstance_ = certificateProviderInstance;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(certificateProviderInstance);
            }
            return this;
        }

        public Builder setTlsCertificateCertificateProviderInstance(CertificateProviderInstance.Builder builder) {
            SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.tlsCertificateCertificateProviderInstanceBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.tlsCertificateCertificateProviderInstance_ = builder.m31749build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m31749build());
            }
            return this;
        }

        public Builder mergeTlsCertificateCertificateProviderInstance(CertificateProviderInstance certificateProviderInstance) {
            SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.tlsCertificateCertificateProviderInstanceBuilder_;
            if (singleFieldBuilderV3 == null) {
                CertificateProviderInstance certificateProviderInstance2 = this.tlsCertificateCertificateProviderInstance_;
                if (certificateProviderInstance2 != null) {
                    this.tlsCertificateCertificateProviderInstance_ = CertificateProviderInstance.newBuilder(certificateProviderInstance2).mergeFrom(certificateProviderInstance).m31751buildPartial();
                } else {
                    this.tlsCertificateCertificateProviderInstance_ = certificateProviderInstance;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(certificateProviderInstance);
            }
            return this;
        }

        public Builder clearTlsCertificateCertificateProviderInstance() {
            if (this.tlsCertificateCertificateProviderInstanceBuilder_ == null) {
                this.tlsCertificateCertificateProviderInstance_ = null;
                onChanged();
            } else {
                this.tlsCertificateCertificateProviderInstance_ = null;
                this.tlsCertificateCertificateProviderInstanceBuilder_ = null;
            }
            return this;
        }

        public CertificateProviderInstance.Builder getTlsCertificateCertificateProviderInstanceBuilder() {
            onChanged();
            return getTlsCertificateCertificateProviderInstanceFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public CertificateProviderInstanceOrBuilder getTlsCertificateCertificateProviderInstanceOrBuilder() {
            SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.tlsCertificateCertificateProviderInstanceBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (CertificateProviderInstanceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            CertificateProviderInstance certificateProviderInstance = this.tlsCertificateCertificateProviderInstance_;
            return certificateProviderInstance == null ? CertificateProviderInstance.getDefaultInstance() : certificateProviderInstance;
        }

        private SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> getTlsCertificateCertificateProviderInstanceFieldBuilder() {
            if (this.tlsCertificateCertificateProviderInstanceBuilder_ == null) {
                this.tlsCertificateCertificateProviderInstanceBuilder_ = new SingleFieldBuilderV3<>(getTlsCertificateCertificateProviderInstance(), getParentForChildren(), isClean());
                this.tlsCertificateCertificateProviderInstance_ = null;
            }
            return this.tlsCertificateCertificateProviderInstanceBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public CertificateValidationContext getValidationContext() {
            SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.validationContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.validationContextTypeCase_ == 3) {
                    return (CertificateValidationContext) this.validationContextType_;
                }
                return CertificateValidationContext.getDefaultInstance();
            }
            if (this.validationContextTypeCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return CertificateValidationContext.getDefaultInstance();
        }

        public Builder setValidationContext(CertificateValidationContext certificateValidationContext) {
            SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.validationContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                certificateValidationContext.getClass();
                this.validationContextType_ = certificateValidationContext;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(certificateValidationContext);
            }
            this.validationContextTypeCase_ = 3;
            return this;
        }

        public Builder setValidationContext(CertificateValidationContext.Builder builder) {
            SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.validationContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.validationContextType_ = builder.m31609build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m31609build());
            }
            this.validationContextTypeCase_ = 3;
            return this;
        }

        public Builder mergeValidationContext(CertificateValidationContext certificateValidationContext) {
            SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.validationContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.validationContextTypeCase_ != 3 || this.validationContextType_ == CertificateValidationContext.getDefaultInstance()) {
                    this.validationContextType_ = certificateValidationContext;
                } else {
                    this.validationContextType_ = CertificateValidationContext.newBuilder((CertificateValidationContext) this.validationContextType_).mergeFrom(certificateValidationContext).m31611buildPartial();
                }
                onChanged();
            } else {
                if (this.validationContextTypeCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(certificateValidationContext);
                }
                this.validationContextBuilder_.setMessage(certificateValidationContext);
            }
            this.validationContextTypeCase_ = 3;
            return this;
        }

        public Builder clearValidationContext() {
            SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.validationContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.validationContextTypeCase_ == 3) {
                    this.validationContextTypeCase_ = 0;
                    this.validationContextType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.validationContextTypeCase_ == 3) {
                this.validationContextTypeCase_ = 0;
                this.validationContextType_ = null;
                onChanged();
            }
            return this;
        }

        public CertificateValidationContext.Builder getValidationContextBuilder() {
            return getValidationContextFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public CertificateValidationContextOrBuilder getValidationContextOrBuilder() {
            SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3;
            int i = this.validationContextTypeCase_;
            if (i == 3 && (singleFieldBuilderV3 = this.validationContextBuilder_) != null) {
                return (CertificateValidationContextOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 3) {
                return (CertificateValidationContext) this.validationContextType_;
            }
            return CertificateValidationContext.getDefaultInstance();
        }

        private SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> getValidationContextFieldBuilder() {
            if (this.validationContextBuilder_ == null) {
                if (this.validationContextTypeCase_ != 3) {
                    this.validationContextType_ = CertificateValidationContext.getDefaultInstance();
                }
                this.validationContextBuilder_ = new SingleFieldBuilderV3<>((CertificateValidationContext) this.validationContextType_, getParentForChildren(), isClean());
                this.validationContextType_ = null;
            }
            this.validationContextTypeCase_ = 3;
            onChanged();
            return this.validationContextBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public SdsSecretConfig getValidationContextSdsSecretConfig() {
            SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.validationContextSdsSecretConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.validationContextTypeCase_ == 7) {
                    return (SdsSecretConfig) this.validationContextType_;
                }
                return SdsSecretConfig.getDefaultInstance();
            }
            if (this.validationContextTypeCase_ == 7) {
                return singleFieldBuilderV3.getMessage();
            }
            return SdsSecretConfig.getDefaultInstance();
        }

        public Builder setValidationContextSdsSecretConfig(SdsSecretConfig sdsSecretConfig) {
            SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.validationContextSdsSecretConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                sdsSecretConfig.getClass();
                this.validationContextType_ = sdsSecretConfig;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(sdsSecretConfig);
            }
            this.validationContextTypeCase_ = 7;
            return this;
        }

        public Builder setValidationContextSdsSecretConfig(SdsSecretConfig.Builder builder) {
            SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.validationContextSdsSecretConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.validationContextType_ = builder.m31979build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m31979build());
            }
            this.validationContextTypeCase_ = 7;
            return this;
        }

        public Builder mergeValidationContextSdsSecretConfig(SdsSecretConfig sdsSecretConfig) {
            SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.validationContextSdsSecretConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.validationContextTypeCase_ != 7 || this.validationContextType_ == SdsSecretConfig.getDefaultInstance()) {
                    this.validationContextType_ = sdsSecretConfig;
                } else {
                    this.validationContextType_ = SdsSecretConfig.newBuilder((SdsSecretConfig) this.validationContextType_).mergeFrom(sdsSecretConfig).m31981buildPartial();
                }
                onChanged();
            } else {
                if (this.validationContextTypeCase_ == 7) {
                    singleFieldBuilderV3.mergeFrom(sdsSecretConfig);
                }
                this.validationContextSdsSecretConfigBuilder_.setMessage(sdsSecretConfig);
            }
            this.validationContextTypeCase_ = 7;
            return this;
        }

        public Builder clearValidationContextSdsSecretConfig() {
            SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3 = this.validationContextSdsSecretConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.validationContextTypeCase_ == 7) {
                    this.validationContextTypeCase_ = 0;
                    this.validationContextType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.validationContextTypeCase_ == 7) {
                this.validationContextTypeCase_ = 0;
                this.validationContextType_ = null;
                onChanged();
            }
            return this;
        }

        public SdsSecretConfig.Builder getValidationContextSdsSecretConfigBuilder() {
            return getValidationContextSdsSecretConfigFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public SdsSecretConfigOrBuilder getValidationContextSdsSecretConfigOrBuilder() {
            SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> singleFieldBuilderV3;
            int i = this.validationContextTypeCase_;
            if (i == 7 && (singleFieldBuilderV3 = this.validationContextSdsSecretConfigBuilder_) != null) {
                return (SdsSecretConfigOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 7) {
                return (SdsSecretConfig) this.validationContextType_;
            }
            return SdsSecretConfig.getDefaultInstance();
        }

        private SingleFieldBuilderV3<SdsSecretConfig, SdsSecretConfig.Builder, SdsSecretConfigOrBuilder> getValidationContextSdsSecretConfigFieldBuilder() {
            if (this.validationContextSdsSecretConfigBuilder_ == null) {
                if (this.validationContextTypeCase_ != 7) {
                    this.validationContextType_ = SdsSecretConfig.getDefaultInstance();
                }
                this.validationContextSdsSecretConfigBuilder_ = new SingleFieldBuilderV3<>((SdsSecretConfig) this.validationContextType_, getParentForChildren(), isClean());
                this.validationContextType_ = null;
            }
            this.validationContextTypeCase_ = 7;
            onChanged();
            return this.validationContextSdsSecretConfigBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public CombinedCertificateValidationContext getCombinedValidationContext() {
            SingleFieldBuilderV3<CombinedCertificateValidationContext, CombinedCertificateValidationContext.Builder, CombinedCertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.combinedValidationContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.validationContextTypeCase_ == 8) {
                    return (CombinedCertificateValidationContext) this.validationContextType_;
                }
                return CombinedCertificateValidationContext.getDefaultInstance();
            }
            if (this.validationContextTypeCase_ == 8) {
                return singleFieldBuilderV3.getMessage();
            }
            return CombinedCertificateValidationContext.getDefaultInstance();
        }

        public Builder setCombinedValidationContext(CombinedCertificateValidationContext combinedCertificateValidationContext) {
            SingleFieldBuilderV3<CombinedCertificateValidationContext, CombinedCertificateValidationContext.Builder, CombinedCertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.combinedValidationContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                combinedCertificateValidationContext.getClass();
                this.validationContextType_ = combinedCertificateValidationContext;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(combinedCertificateValidationContext);
            }
            this.validationContextTypeCase_ = 8;
            return this;
        }

        public Builder setCombinedValidationContext(CombinedCertificateValidationContext.Builder builder) {
            SingleFieldBuilderV3<CombinedCertificateValidationContext, CombinedCertificateValidationContext.Builder, CombinedCertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.combinedValidationContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.validationContextType_ = builder.m31795build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m31795build());
            }
            this.validationContextTypeCase_ = 8;
            return this;
        }

        public Builder mergeCombinedValidationContext(CombinedCertificateValidationContext combinedCertificateValidationContext) {
            SingleFieldBuilderV3<CombinedCertificateValidationContext, CombinedCertificateValidationContext.Builder, CombinedCertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.combinedValidationContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.validationContextTypeCase_ != 8 || this.validationContextType_ == CombinedCertificateValidationContext.getDefaultInstance()) {
                    this.validationContextType_ = combinedCertificateValidationContext;
                } else {
                    this.validationContextType_ = CombinedCertificateValidationContext.newBuilder((CombinedCertificateValidationContext) this.validationContextType_).mergeFrom(combinedCertificateValidationContext).m31797buildPartial();
                }
                onChanged();
            } else {
                if (this.validationContextTypeCase_ == 8) {
                    singleFieldBuilderV3.mergeFrom(combinedCertificateValidationContext);
                }
                this.combinedValidationContextBuilder_.setMessage(combinedCertificateValidationContext);
            }
            this.validationContextTypeCase_ = 8;
            return this;
        }

        public Builder clearCombinedValidationContext() {
            SingleFieldBuilderV3<CombinedCertificateValidationContext, CombinedCertificateValidationContext.Builder, CombinedCertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.combinedValidationContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.validationContextTypeCase_ == 8) {
                    this.validationContextTypeCase_ = 0;
                    this.validationContextType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.validationContextTypeCase_ == 8) {
                this.validationContextTypeCase_ = 0;
                this.validationContextType_ = null;
                onChanged();
            }
            return this;
        }

        public CombinedCertificateValidationContext.Builder getCombinedValidationContextBuilder() {
            return getCombinedValidationContextFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public CombinedCertificateValidationContextOrBuilder getCombinedValidationContextOrBuilder() {
            SingleFieldBuilderV3<CombinedCertificateValidationContext, CombinedCertificateValidationContext.Builder, CombinedCertificateValidationContextOrBuilder> singleFieldBuilderV3;
            int i = this.validationContextTypeCase_;
            if (i == 8 && (singleFieldBuilderV3 = this.combinedValidationContextBuilder_) != null) {
                return (CombinedCertificateValidationContextOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 8) {
                return (CombinedCertificateValidationContext) this.validationContextType_;
            }
            return CombinedCertificateValidationContext.getDefaultInstance();
        }

        private SingleFieldBuilderV3<CombinedCertificateValidationContext, CombinedCertificateValidationContext.Builder, CombinedCertificateValidationContextOrBuilder> getCombinedValidationContextFieldBuilder() {
            if (this.combinedValidationContextBuilder_ == null) {
                if (this.validationContextTypeCase_ != 8) {
                    this.validationContextType_ = CombinedCertificateValidationContext.getDefaultInstance();
                }
                this.combinedValidationContextBuilder_ = new SingleFieldBuilderV3<>((CombinedCertificateValidationContext) this.validationContextType_, getParentForChildren(), isClean());
                this.validationContextType_ = null;
            }
            this.validationContextTypeCase_ = 8;
            onChanged();
            return this.combinedValidationContextBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public CertificateProvider getValidationContextCertificateProvider() {
            SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.validationContextTypeCase_ == 10) {
                    return (CertificateProvider) this.validationContextType_;
                }
                return CertificateProvider.getDefaultInstance();
            }
            if (this.validationContextTypeCase_ == 10) {
                return singleFieldBuilderV3.getMessage();
            }
            return CertificateProvider.getDefaultInstance();
        }

        public Builder setValidationContextCertificateProvider(CertificateProvider certificateProvider) {
            SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderBuilder_;
            if (singleFieldBuilderV3 == null) {
                certificateProvider.getClass();
                this.validationContextType_ = certificateProvider;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(certificateProvider);
            }
            this.validationContextTypeCase_ = 10;
            return this;
        }

        public Builder setValidationContextCertificateProvider(CertificateProvider.Builder builder) {
            SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.validationContextType_ = builder.m31703build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m31703build());
            }
            this.validationContextTypeCase_ = 10;
            return this;
        }

        public Builder mergeValidationContextCertificateProvider(CertificateProvider certificateProvider) {
            SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.validationContextTypeCase_ != 10 || this.validationContextType_ == CertificateProvider.getDefaultInstance()) {
                    this.validationContextType_ = certificateProvider;
                } else {
                    this.validationContextType_ = CertificateProvider.newBuilder((CertificateProvider) this.validationContextType_).mergeFrom(certificateProvider).m31705buildPartial();
                }
                onChanged();
            } else {
                if (this.validationContextTypeCase_ == 10) {
                    singleFieldBuilderV3.mergeFrom(certificateProvider);
                }
                this.validationContextCertificateProviderBuilder_.setMessage(certificateProvider);
            }
            this.validationContextTypeCase_ = 10;
            return this;
        }

        public Builder clearValidationContextCertificateProvider() {
            SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.validationContextTypeCase_ == 10) {
                    this.validationContextTypeCase_ = 0;
                    this.validationContextType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.validationContextTypeCase_ == 10) {
                this.validationContextTypeCase_ = 0;
                this.validationContextType_ = null;
                onChanged();
            }
            return this;
        }

        public CertificateProvider.Builder getValidationContextCertificateProviderBuilder() {
            return getValidationContextCertificateProviderFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public CertificateProviderOrBuilder getValidationContextCertificateProviderOrBuilder() {
            SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> singleFieldBuilderV3;
            int i = this.validationContextTypeCase_;
            if (i == 10 && (singleFieldBuilderV3 = this.validationContextCertificateProviderBuilder_) != null) {
                return (CertificateProviderOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 10) {
                return (CertificateProvider) this.validationContextType_;
            }
            return CertificateProvider.getDefaultInstance();
        }

        private SingleFieldBuilderV3<CertificateProvider, CertificateProvider.Builder, CertificateProviderOrBuilder> getValidationContextCertificateProviderFieldBuilder() {
            if (this.validationContextCertificateProviderBuilder_ == null) {
                if (this.validationContextTypeCase_ != 10) {
                    this.validationContextType_ = CertificateProvider.getDefaultInstance();
                }
                this.validationContextCertificateProviderBuilder_ = new SingleFieldBuilderV3<>((CertificateProvider) this.validationContextType_, getParentForChildren(), isClean());
                this.validationContextType_ = null;
            }
            this.validationContextTypeCase_ = 10;
            onChanged();
            return this.validationContextCertificateProviderBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public CertificateProviderInstance getValidationContextCertificateProviderInstance() {
            SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderInstanceBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.validationContextTypeCase_ == 12) {
                    return (CertificateProviderInstance) this.validationContextType_;
                }
                return CertificateProviderInstance.getDefaultInstance();
            }
            if (this.validationContextTypeCase_ == 12) {
                return singleFieldBuilderV3.getMessage();
            }
            return CertificateProviderInstance.getDefaultInstance();
        }

        public Builder setValidationContextCertificateProviderInstance(CertificateProviderInstance certificateProviderInstance) {
            SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderInstanceBuilder_;
            if (singleFieldBuilderV3 == null) {
                certificateProviderInstance.getClass();
                this.validationContextType_ = certificateProviderInstance;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(certificateProviderInstance);
            }
            this.validationContextTypeCase_ = 12;
            return this;
        }

        public Builder setValidationContextCertificateProviderInstance(CertificateProviderInstance.Builder builder) {
            SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderInstanceBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.validationContextType_ = builder.m31749build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m31749build());
            }
            this.validationContextTypeCase_ = 12;
            return this;
        }

        public Builder mergeValidationContextCertificateProviderInstance(CertificateProviderInstance certificateProviderInstance) {
            SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderInstanceBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.validationContextTypeCase_ != 12 || this.validationContextType_ == CertificateProviderInstance.getDefaultInstance()) {
                    this.validationContextType_ = certificateProviderInstance;
                } else {
                    this.validationContextType_ = CertificateProviderInstance.newBuilder((CertificateProviderInstance) this.validationContextType_).mergeFrom(certificateProviderInstance).m31751buildPartial();
                }
                onChanged();
            } else {
                if (this.validationContextTypeCase_ == 12) {
                    singleFieldBuilderV3.mergeFrom(certificateProviderInstance);
                }
                this.validationContextCertificateProviderInstanceBuilder_.setMessage(certificateProviderInstance);
            }
            this.validationContextTypeCase_ = 12;
            return this;
        }

        public Builder clearValidationContextCertificateProviderInstance() {
            SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3 = this.validationContextCertificateProviderInstanceBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.validationContextTypeCase_ == 12) {
                    this.validationContextTypeCase_ = 0;
                    this.validationContextType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.validationContextTypeCase_ == 12) {
                this.validationContextTypeCase_ = 0;
                this.validationContextType_ = null;
                onChanged();
            }
            return this;
        }

        public CertificateProviderInstance.Builder getValidationContextCertificateProviderInstanceBuilder() {
            return getValidationContextCertificateProviderInstanceFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public CertificateProviderInstanceOrBuilder getValidationContextCertificateProviderInstanceOrBuilder() {
            SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> singleFieldBuilderV3;
            int i = this.validationContextTypeCase_;
            if (i == 12 && (singleFieldBuilderV3 = this.validationContextCertificateProviderInstanceBuilder_) != null) {
                return (CertificateProviderInstanceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 12) {
                return (CertificateProviderInstance) this.validationContextType_;
            }
            return CertificateProviderInstance.getDefaultInstance();
        }

        private SingleFieldBuilderV3<CertificateProviderInstance, CertificateProviderInstance.Builder, CertificateProviderInstanceOrBuilder> getValidationContextCertificateProviderInstanceFieldBuilder() {
            if (this.validationContextCertificateProviderInstanceBuilder_ == null) {
                if (this.validationContextTypeCase_ != 12) {
                    this.validationContextType_ = CertificateProviderInstance.getDefaultInstance();
                }
                this.validationContextCertificateProviderInstanceBuilder_ = new SingleFieldBuilderV3<>((CertificateProviderInstance) this.validationContextType_, getParentForChildren(), isClean());
                this.validationContextType_ = null;
            }
            this.validationContextTypeCase_ = 12;
            onChanged();
            return this.validationContextCertificateProviderInstanceBuilder_;
        }

        private void ensureAlpnProtocolsIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.alpnProtocols_ = new LazyStringArrayList(this.alpnProtocols_);
                this.bitField0_ |= 4;
            }
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        /* renamed from: getAlpnProtocolsList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo31646getAlpnProtocolsList() {
            return this.alpnProtocols_.getUnmodifiableView();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public int getAlpnProtocolsCount() {
            return this.alpnProtocols_.size();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public String getAlpnProtocols(int i) {
            return (String) this.alpnProtocols_.get(i);
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContextOrBuilder
        public ByteString getAlpnProtocolsBytes(int i) {
            return this.alpnProtocols_.getByteString(i);
        }

        public Builder setAlpnProtocols(int i, String str) {
            str.getClass();
            ensureAlpnProtocolsIsMutable();
            this.alpnProtocols_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addAlpnProtocols(String str) {
            str.getClass();
            ensureAlpnProtocolsIsMutable();
            this.alpnProtocols_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllAlpnProtocols(Iterable<String> iterable) {
            ensureAlpnProtocolsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.alpnProtocols_);
            onChanged();
            return this;
        }

        public Builder clearAlpnProtocols() {
            this.alpnProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            onChanged();
            return this;
        }

        public Builder addAlpnProtocolsBytes(ByteString byteString) {
            byteString.getClass();
            CommonTlsContext.checkByteStringIsUtf8(byteString);
            ensureAlpnProtocolsIsMutable();
            this.alpnProtocols_.add(byteString);
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m31691setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m31685mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$CommonTlsContext$CertificateProvider$ConfigCase;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$CommonTlsContext$ValidationContextTypeCase;

        static {
            int[] iArr = new int[ValidationContextTypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$CommonTlsContext$ValidationContextTypeCase = iArr;
            try {
                iArr[ValidationContextTypeCase.VALIDATION_CONTEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$CommonTlsContext$ValidationContextTypeCase[ValidationContextTypeCase.VALIDATION_CONTEXT_SDS_SECRET_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$CommonTlsContext$ValidationContextTypeCase[ValidationContextTypeCase.COMBINED_VALIDATION_CONTEXT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$CommonTlsContext$ValidationContextTypeCase[ValidationContextTypeCase.VALIDATION_CONTEXT_CERTIFICATE_PROVIDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$CommonTlsContext$ValidationContextTypeCase[ValidationContextTypeCase.VALIDATION_CONTEXT_CERTIFICATE_PROVIDER_INSTANCE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$CommonTlsContext$ValidationContextTypeCase[ValidationContextTypeCase.VALIDATIONCONTEXTTYPE_NOT_SET.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[CertificateProvider.ConfigCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$CommonTlsContext$CertificateProvider$ConfigCase = iArr2;
            try {
                iArr2[CertificateProvider.ConfigCase.TYPED_CONFIG.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$CommonTlsContext$CertificateProvider$ConfigCase[CertificateProvider.ConfigCase.CONFIG_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }
}
