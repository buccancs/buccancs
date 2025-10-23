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
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CertificateValidationContext;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.GenericSecret;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.TlsCertificate;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.TlsSessionTicketKeys;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class Secret extends GeneratedMessageV3 implements SecretOrBuilder {
    public static final int GENERIC_SECRET_FIELD_NUMBER = 5;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int SESSION_TICKET_KEYS_FIELD_NUMBER = 3;
    public static final int TLS_CERTIFICATE_FIELD_NUMBER = 2;
    public static final int VALIDATION_CONTEXT_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private static final Secret DEFAULT_INSTANCE = new Secret();
    private static final Parser<Secret> PARSER = new AbstractParser<Secret>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.Secret.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Secret m32022parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Secret(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private int typeCase_;
    private Object type_;

    private Secret(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.typeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Secret() {
        this.typeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    private Secret(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag != 10) {
                            if (tag == 18) {
                                TlsCertificate.Builder builderM32066toBuilder = this.typeCase_ == 2 ? ((TlsCertificate) this.type_).m32066toBuilder() : null;
                                MessageLite message = codedInputStream.readMessage(TlsCertificate.parser(), extensionRegistryLite);
                                this.type_ = message;
                                if (builderM32066toBuilder != null) {
                                    builderM32066toBuilder.mergeFrom((TlsCertificate) message);
                                    this.type_ = builderM32066toBuilder.m32073buildPartial();
                                }
                                this.typeCase_ = 2;
                            } else if (tag == 26) {
                                TlsSessionTicketKeys.Builder builderM32161toBuilder = this.typeCase_ == 3 ? ((TlsSessionTicketKeys) this.type_).m32161toBuilder() : null;
                                MessageLite message2 = codedInputStream.readMessage(TlsSessionTicketKeys.parser(), extensionRegistryLite);
                                this.type_ = message2;
                                if (builderM32161toBuilder != null) {
                                    builderM32161toBuilder.mergeFrom((TlsSessionTicketKeys) message2);
                                    this.type_ = builderM32161toBuilder.m32168buildPartial();
                                }
                                this.typeCase_ = 3;
                            } else if (tag == 34) {
                                CertificateValidationContext.Builder builderM31604toBuilder = this.typeCase_ == 4 ? ((CertificateValidationContext) this.type_).m31604toBuilder() : null;
                                MessageLite message3 = codedInputStream.readMessage(CertificateValidationContext.parser(), extensionRegistryLite);
                                this.type_ = message3;
                                if (builderM31604toBuilder != null) {
                                    builderM31604toBuilder.mergeFrom((CertificateValidationContext) message3);
                                    this.type_ = builderM31604toBuilder.m31611buildPartial();
                                }
                                this.typeCase_ = 4;
                            } else if (tag == 42) {
                                GenericSecret.Builder builderM31882toBuilder = this.typeCase_ == 5 ? ((GenericSecret) this.type_).m31882toBuilder() : null;
                                MessageLite message4 = codedInputStream.readMessage(GenericSecret.parser(), extensionRegistryLite);
                                this.type_ = message4;
                                if (builderM31882toBuilder != null) {
                                    builderM31882toBuilder.mergeFrom((GenericSecret) message4);
                                    this.type_ = builderM31882toBuilder.m31889buildPartial();
                                }
                                this.typeCase_ = 5;
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.name_ = codedInputStream.readStringRequireUtf8();
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

    public static Secret getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Secret> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SecretProto.internal_static_envoy_extensions_transport_sockets_tls_v3_Secret_descriptor;
    }

    public static Secret parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Secret) PARSER.parseFrom(byteBuffer);
    }

    public static Secret parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Secret) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Secret parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Secret) PARSER.parseFrom(byteString);
    }

    public static Secret parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Secret) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Secret parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Secret) PARSER.parseFrom(bArr);
    }

    public static Secret parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Secret) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Secret parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Secret parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Secret parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Secret parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Secret parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Secret parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m32020toBuilder();
    }

    public static Builder newBuilder(Secret secret) {
        return DEFAULT_INSTANCE.m32020toBuilder().mergeFrom(secret);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Secret m32015getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Secret> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public boolean hasGenericSecret() {
        return this.typeCase_ == 5;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public boolean hasSessionTicketKeys() {
        return this.typeCase_ == 3;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public boolean hasTlsCertificate() {
        return this.typeCase_ == 2;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public boolean hasValidationContext() {
        return this.typeCase_ == 4;
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
        return new Secret();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SecretProto.internal_static_envoy_extensions_transport_sockets_tls_v3_Secret_fieldAccessorTable.ensureFieldAccessorsInitialized(Secret.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public TypeCase getTypeCase() {
        return TypeCase.forNumber(this.typeCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public TlsCertificate getTlsCertificate() {
        if (this.typeCase_ == 2) {
            return (TlsCertificate) this.type_;
        }
        return TlsCertificate.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public TlsCertificateOrBuilder getTlsCertificateOrBuilder() {
        if (this.typeCase_ == 2) {
            return (TlsCertificate) this.type_;
        }
        return TlsCertificate.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public TlsSessionTicketKeys getSessionTicketKeys() {
        if (this.typeCase_ == 3) {
            return (TlsSessionTicketKeys) this.type_;
        }
        return TlsSessionTicketKeys.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public TlsSessionTicketKeysOrBuilder getSessionTicketKeysOrBuilder() {
        if (this.typeCase_ == 3) {
            return (TlsSessionTicketKeys) this.type_;
        }
        return TlsSessionTicketKeys.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public CertificateValidationContext getValidationContext() {
        if (this.typeCase_ == 4) {
            return (CertificateValidationContext) this.type_;
        }
        return CertificateValidationContext.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public CertificateValidationContextOrBuilder getValidationContextOrBuilder() {
        if (this.typeCase_ == 4) {
            return (CertificateValidationContext) this.type_;
        }
        return CertificateValidationContext.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public GenericSecret getGenericSecret() {
        if (this.typeCase_ == 5) {
            return (GenericSecret) this.type_;
        }
        return GenericSecret.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
    public GenericSecretOrBuilder getGenericSecretOrBuilder() {
        if (this.typeCase_ == 5) {
            return (GenericSecret) this.type_;
        }
        return GenericSecret.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.typeCase_ == 2) {
            codedOutputStream.writeMessage(2, (TlsCertificate) this.type_);
        }
        if (this.typeCase_ == 3) {
            codedOutputStream.writeMessage(3, (TlsSessionTicketKeys) this.type_);
        }
        if (this.typeCase_ == 4) {
            codedOutputStream.writeMessage(4, (CertificateValidationContext) this.type_);
        }
        if (this.typeCase_ == 5) {
            codedOutputStream.writeMessage(5, (GenericSecret) this.type_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (this.typeCase_ == 2) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, (TlsCertificate) this.type_);
        }
        if (this.typeCase_ == 3) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, (TlsSessionTicketKeys) this.type_);
        }
        if (this.typeCase_ == 4) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(4, (CertificateValidationContext) this.type_);
        }
        if (this.typeCase_ == 5) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(5, (GenericSecret) this.type_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Secret)) {
            return super.equals(obj);
        }
        Secret secret = (Secret) obj;
        if (!getName().equals(secret.getName()) || !getTypeCase().equals(secret.getTypeCase())) {
            return false;
        }
        int i = this.typeCase_;
        if (i != 2) {
            if (i != 3) {
                if (i == 4) {
                    if (!getValidationContext().equals(secret.getValidationContext())) {
                        return false;
                    }
                } else if (i == 5 && !getGenericSecret().equals(secret.getGenericSecret())) {
                    return false;
                }
            } else if (!getSessionTicketKeys().equals(secret.getSessionTicketKeys())) {
                return false;
            }
        } else if (!getTlsCertificate().equals(secret.getTlsCertificate())) {
            return false;
        }
        return this.unknownFields.equals(secret.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        int i2 = this.typeCase_;
        if (i2 == 2) {
            i = ((iHashCode2 * 37) + 2) * 53;
            iHashCode = getTlsCertificate().hashCode();
        } else if (i2 == 3) {
            i = ((iHashCode2 * 37) + 3) * 53;
            iHashCode = getSessionTicketKeys().hashCode();
        } else if (i2 == 4) {
            i = ((iHashCode2 * 37) + 4) * 53;
            iHashCode = getValidationContext().hashCode();
        } else {
            if (i2 == 5) {
                i = ((iHashCode2 * 37) + 5) * 53;
                iHashCode = getGenericSecret().hashCode();
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
    public Builder m32017newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m32020toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum TypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        TLS_CERTIFICATE(2),
        SESSION_TICKET_KEYS(3),
        VALIDATION_CONTEXT(4),
        GENERIC_SECRET(5),
        TYPE_NOT_SET(0);

        private final int value;

        TypeCase(int i) {
            this.value = i;
        }

        public static TypeCase forNumber(int i) {
            if (i == 0) {
                return TYPE_NOT_SET;
            }
            if (i == 2) {
                return TLS_CERTIFICATE;
            }
            if (i == 3) {
                return SESSION_TICKET_KEYS;
            }
            if (i == 4) {
                return VALIDATION_CONTEXT;
            }
            if (i != 5) {
                return null;
            }
            return GENERIC_SECRET;
        }

        @Deprecated
        public static TypeCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SecretOrBuilder {
        private SingleFieldBuilderV3<GenericSecret, GenericSecret.Builder, GenericSecretOrBuilder> genericSecretBuilder_;
        private Object name_;
        private SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> sessionTicketKeysBuilder_;
        private SingleFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> tlsCertificateBuilder_;
        private int typeCase_;
        private Object type_;
        private SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> validationContextBuilder_;

        private Builder() {
            this.typeCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.typeCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SecretProto.internal_static_envoy_extensions_transport_sockets_tls_v3_Secret_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
        public boolean hasGenericSecret() {
            return this.typeCase_ == 5;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
        public boolean hasSessionTicketKeys() {
            return this.typeCase_ == 3;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
        public boolean hasTlsCertificate() {
            return this.typeCase_ == 2;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
        public boolean hasValidationContext() {
            return this.typeCase_ == 4;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SecretProto.internal_static_envoy_extensions_transport_sockets_tls_v3_Secret_fieldAccessorTable.ensureFieldAccessorsInitialized(Secret.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Secret.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32031clear() {
            super.clear();
            this.name_ = "";
            this.typeCase_ = 0;
            this.type_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return SecretProto.internal_static_envoy_extensions_transport_sockets_tls_v3_Secret_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Secret m32044getDefaultInstanceForType() {
            return Secret.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Secret m32025build() throws UninitializedMessageException {
            Secret secretM32027buildPartial = m32027buildPartial();
            if (secretM32027buildPartial.isInitialized()) {
                return secretM32027buildPartial;
            }
            throw newUninitializedMessageException(secretM32027buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Secret m32027buildPartial() {
            Secret secret = new Secret(this);
            secret.name_ = this.name_;
            if (this.typeCase_ == 2) {
                SingleFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> singleFieldBuilderV3 = this.tlsCertificateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    secret.type_ = this.type_;
                } else {
                    secret.type_ = singleFieldBuilderV3.build();
                }
            }
            if (this.typeCase_ == 3) {
                SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> singleFieldBuilderV32 = this.sessionTicketKeysBuilder_;
                if (singleFieldBuilderV32 == null) {
                    secret.type_ = this.type_;
                } else {
                    secret.type_ = singleFieldBuilderV32.build();
                }
            }
            if (this.typeCase_ == 4) {
                SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV33 = this.validationContextBuilder_;
                if (singleFieldBuilderV33 == null) {
                    secret.type_ = this.type_;
                } else {
                    secret.type_ = singleFieldBuilderV33.build();
                }
            }
            if (this.typeCase_ == 5) {
                SingleFieldBuilderV3<GenericSecret, GenericSecret.Builder, GenericSecretOrBuilder> singleFieldBuilderV34 = this.genericSecretBuilder_;
                if (singleFieldBuilderV34 == null) {
                    secret.type_ = this.type_;
                } else {
                    secret.type_ = singleFieldBuilderV34.build();
                }
            }
            secret.typeCase_ = this.typeCase_;
            onBuilt();
            return secret;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32043clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32055setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32033clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32036clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32057setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32023addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32048mergeFrom(Message message) {
            if (message instanceof Secret) {
                return mergeFrom((Secret) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Secret secret) {
            if (secret == Secret.getDefaultInstance()) {
                return this;
            }
            if (!secret.getName().isEmpty()) {
                this.name_ = secret.name_;
                onChanged();
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$Secret$TypeCase[secret.getTypeCase().ordinal()];
            if (i == 1) {
                mergeTlsCertificate(secret.getTlsCertificate());
            } else if (i == 2) {
                mergeSessionTicketKeys(secret.getSessionTicketKeys());
            } else if (i == 3) {
                mergeValidationContext(secret.getValidationContext());
            } else if (i == 4) {
                mergeGenericSecret(secret.getGenericSecret());
            }
            m32053mergeUnknownFields(secret.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.Secret.Builder m32049mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.Secret.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.Secret r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.Secret) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.Secret r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.Secret) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.Secret.Builder.m32049mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.Secret$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
        public TypeCase getTypeCase() {
            return TypeCase.forNumber(this.typeCase_);
        }

        public Builder clearType() {
            this.typeCase_ = 0;
            this.type_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
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
            Secret.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = Secret.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
        public TlsCertificate getTlsCertificate() {
            SingleFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> singleFieldBuilderV3 = this.tlsCertificateBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ == 2) {
                    return (TlsCertificate) this.type_;
                }
                return TlsCertificate.getDefaultInstance();
            }
            if (this.typeCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return TlsCertificate.getDefaultInstance();
        }

        public Builder setTlsCertificate(TlsCertificate tlsCertificate) {
            SingleFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> singleFieldBuilderV3 = this.tlsCertificateBuilder_;
            if (singleFieldBuilderV3 == null) {
                tlsCertificate.getClass();
                this.type_ = tlsCertificate;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(tlsCertificate);
            }
            this.typeCase_ = 2;
            return this;
        }

        public Builder setTlsCertificate(TlsCertificate.Builder builder) {
            SingleFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> singleFieldBuilderV3 = this.tlsCertificateBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.type_ = builder.m32071build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m32071build());
            }
            this.typeCase_ = 2;
            return this;
        }

        public Builder mergeTlsCertificate(TlsCertificate tlsCertificate) {
            SingleFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> singleFieldBuilderV3 = this.tlsCertificateBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ != 2 || this.type_ == TlsCertificate.getDefaultInstance()) {
                    this.type_ = tlsCertificate;
                } else {
                    this.type_ = TlsCertificate.newBuilder((TlsCertificate) this.type_).mergeFrom(tlsCertificate).m32073buildPartial();
                }
                onChanged();
            } else {
                if (this.typeCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(tlsCertificate);
                }
                this.tlsCertificateBuilder_.setMessage(tlsCertificate);
            }
            this.typeCase_ = 2;
            return this;
        }

        public Builder clearTlsCertificate() {
            SingleFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> singleFieldBuilderV3 = this.tlsCertificateBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.typeCase_ == 2) {
                    this.typeCase_ = 0;
                    this.type_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.typeCase_ == 2) {
                this.typeCase_ = 0;
                this.type_ = null;
                onChanged();
            }
            return this;
        }

        public TlsCertificate.Builder getTlsCertificateBuilder() {
            return getTlsCertificateFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
        public TlsCertificateOrBuilder getTlsCertificateOrBuilder() {
            SingleFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> singleFieldBuilderV3;
            int i = this.typeCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.tlsCertificateBuilder_) != null) {
                return (TlsCertificateOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (TlsCertificate) this.type_;
            }
            return TlsCertificate.getDefaultInstance();
        }

        private SingleFieldBuilderV3<TlsCertificate, TlsCertificate.Builder, TlsCertificateOrBuilder> getTlsCertificateFieldBuilder() {
            if (this.tlsCertificateBuilder_ == null) {
                if (this.typeCase_ != 2) {
                    this.type_ = TlsCertificate.getDefaultInstance();
                }
                this.tlsCertificateBuilder_ = new SingleFieldBuilderV3<>((TlsCertificate) this.type_, getParentForChildren(), isClean());
                this.type_ = null;
            }
            this.typeCase_ = 2;
            onChanged();
            return this.tlsCertificateBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
        public TlsSessionTicketKeys getSessionTicketKeys() {
            SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ == 3) {
                    return (TlsSessionTicketKeys) this.type_;
                }
                return TlsSessionTicketKeys.getDefaultInstance();
            }
            if (this.typeCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return TlsSessionTicketKeys.getDefaultInstance();
        }

        public Builder setSessionTicketKeys(TlsSessionTicketKeys tlsSessionTicketKeys) {
            SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysBuilder_;
            if (singleFieldBuilderV3 == null) {
                tlsSessionTicketKeys.getClass();
                this.type_ = tlsSessionTicketKeys;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(tlsSessionTicketKeys);
            }
            this.typeCase_ = 3;
            return this;
        }

        public Builder setSessionTicketKeys(TlsSessionTicketKeys.Builder builder) {
            SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.type_ = builder.m32166build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m32166build());
            }
            this.typeCase_ = 3;
            return this;
        }

        public Builder mergeSessionTicketKeys(TlsSessionTicketKeys tlsSessionTicketKeys) {
            SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ != 3 || this.type_ == TlsSessionTicketKeys.getDefaultInstance()) {
                    this.type_ = tlsSessionTicketKeys;
                } else {
                    this.type_ = TlsSessionTicketKeys.newBuilder((TlsSessionTicketKeys) this.type_).mergeFrom(tlsSessionTicketKeys).m32168buildPartial();
                }
                onChanged();
            } else {
                if (this.typeCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(tlsSessionTicketKeys);
                }
                this.sessionTicketKeysBuilder_.setMessage(tlsSessionTicketKeys);
            }
            this.typeCase_ = 3;
            return this;
        }

        public Builder clearSessionTicketKeys() {
            SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> singleFieldBuilderV3 = this.sessionTicketKeysBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.typeCase_ == 3) {
                    this.typeCase_ = 0;
                    this.type_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.typeCase_ == 3) {
                this.typeCase_ = 0;
                this.type_ = null;
                onChanged();
            }
            return this;
        }

        public TlsSessionTicketKeys.Builder getSessionTicketKeysBuilder() {
            return getSessionTicketKeysFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
        public TlsSessionTicketKeysOrBuilder getSessionTicketKeysOrBuilder() {
            SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> singleFieldBuilderV3;
            int i = this.typeCase_;
            if (i == 3 && (singleFieldBuilderV3 = this.sessionTicketKeysBuilder_) != null) {
                return (TlsSessionTicketKeysOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 3) {
                return (TlsSessionTicketKeys) this.type_;
            }
            return TlsSessionTicketKeys.getDefaultInstance();
        }

        private SingleFieldBuilderV3<TlsSessionTicketKeys, TlsSessionTicketKeys.Builder, TlsSessionTicketKeysOrBuilder> getSessionTicketKeysFieldBuilder() {
            if (this.sessionTicketKeysBuilder_ == null) {
                if (this.typeCase_ != 3) {
                    this.type_ = TlsSessionTicketKeys.getDefaultInstance();
                }
                this.sessionTicketKeysBuilder_ = new SingleFieldBuilderV3<>((TlsSessionTicketKeys) this.type_, getParentForChildren(), isClean());
                this.type_ = null;
            }
            this.typeCase_ = 3;
            onChanged();
            return this.sessionTicketKeysBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
        public CertificateValidationContext getValidationContext() {
            SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.validationContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ == 4) {
                    return (CertificateValidationContext) this.type_;
                }
                return CertificateValidationContext.getDefaultInstance();
            }
            if (this.typeCase_ == 4) {
                return singleFieldBuilderV3.getMessage();
            }
            return CertificateValidationContext.getDefaultInstance();
        }

        public Builder setValidationContext(CertificateValidationContext certificateValidationContext) {
            SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.validationContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                certificateValidationContext.getClass();
                this.type_ = certificateValidationContext;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(certificateValidationContext);
            }
            this.typeCase_ = 4;
            return this;
        }

        public Builder setValidationContext(CertificateValidationContext.Builder builder) {
            SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.validationContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.type_ = builder.m31609build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m31609build());
            }
            this.typeCase_ = 4;
            return this;
        }

        public Builder mergeValidationContext(CertificateValidationContext certificateValidationContext) {
            SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.validationContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ != 4 || this.type_ == CertificateValidationContext.getDefaultInstance()) {
                    this.type_ = certificateValidationContext;
                } else {
                    this.type_ = CertificateValidationContext.newBuilder((CertificateValidationContext) this.type_).mergeFrom(certificateValidationContext).m31611buildPartial();
                }
                onChanged();
            } else {
                if (this.typeCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(certificateValidationContext);
                }
                this.validationContextBuilder_.setMessage(certificateValidationContext);
            }
            this.typeCase_ = 4;
            return this;
        }

        public Builder clearValidationContext() {
            SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3 = this.validationContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.typeCase_ == 4) {
                    this.typeCase_ = 0;
                    this.type_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.typeCase_ == 4) {
                this.typeCase_ = 0;
                this.type_ = null;
                onChanged();
            }
            return this;
        }

        public CertificateValidationContext.Builder getValidationContextBuilder() {
            return getValidationContextFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
        public CertificateValidationContextOrBuilder getValidationContextOrBuilder() {
            SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> singleFieldBuilderV3;
            int i = this.typeCase_;
            if (i == 4 && (singleFieldBuilderV3 = this.validationContextBuilder_) != null) {
                return (CertificateValidationContextOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 4) {
                return (CertificateValidationContext) this.type_;
            }
            return CertificateValidationContext.getDefaultInstance();
        }

        private SingleFieldBuilderV3<CertificateValidationContext, CertificateValidationContext.Builder, CertificateValidationContextOrBuilder> getValidationContextFieldBuilder() {
            if (this.validationContextBuilder_ == null) {
                if (this.typeCase_ != 4) {
                    this.type_ = CertificateValidationContext.getDefaultInstance();
                }
                this.validationContextBuilder_ = new SingleFieldBuilderV3<>((CertificateValidationContext) this.type_, getParentForChildren(), isClean());
                this.type_ = null;
            }
            this.typeCase_ = 4;
            onChanged();
            return this.validationContextBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
        public GenericSecret getGenericSecret() {
            SingleFieldBuilderV3<GenericSecret, GenericSecret.Builder, GenericSecretOrBuilder> singleFieldBuilderV3 = this.genericSecretBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ == 5) {
                    return (GenericSecret) this.type_;
                }
                return GenericSecret.getDefaultInstance();
            }
            if (this.typeCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return GenericSecret.getDefaultInstance();
        }

        public Builder setGenericSecret(GenericSecret genericSecret) {
            SingleFieldBuilderV3<GenericSecret, GenericSecret.Builder, GenericSecretOrBuilder> singleFieldBuilderV3 = this.genericSecretBuilder_;
            if (singleFieldBuilderV3 == null) {
                genericSecret.getClass();
                this.type_ = genericSecret;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(genericSecret);
            }
            this.typeCase_ = 5;
            return this;
        }

        public Builder setGenericSecret(GenericSecret.Builder builder) {
            SingleFieldBuilderV3<GenericSecret, GenericSecret.Builder, GenericSecretOrBuilder> singleFieldBuilderV3 = this.genericSecretBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.type_ = builder.m31887build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m31887build());
            }
            this.typeCase_ = 5;
            return this;
        }

        public Builder mergeGenericSecret(GenericSecret genericSecret) {
            SingleFieldBuilderV3<GenericSecret, GenericSecret.Builder, GenericSecretOrBuilder> singleFieldBuilderV3 = this.genericSecretBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ != 5 || this.type_ == GenericSecret.getDefaultInstance()) {
                    this.type_ = genericSecret;
                } else {
                    this.type_ = GenericSecret.newBuilder((GenericSecret) this.type_).mergeFrom(genericSecret).m31889buildPartial();
                }
                onChanged();
            } else {
                if (this.typeCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(genericSecret);
                }
                this.genericSecretBuilder_.setMessage(genericSecret);
            }
            this.typeCase_ = 5;
            return this;
        }

        public Builder clearGenericSecret() {
            SingleFieldBuilderV3<GenericSecret, GenericSecret.Builder, GenericSecretOrBuilder> singleFieldBuilderV3 = this.genericSecretBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.typeCase_ == 5) {
                    this.typeCase_ = 0;
                    this.type_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.typeCase_ == 5) {
                this.typeCase_ = 0;
                this.type_ = null;
                onChanged();
            }
            return this;
        }

        public GenericSecret.Builder getGenericSecretBuilder() {
            return getGenericSecretFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SecretOrBuilder
        public GenericSecretOrBuilder getGenericSecretOrBuilder() {
            SingleFieldBuilderV3<GenericSecret, GenericSecret.Builder, GenericSecretOrBuilder> singleFieldBuilderV3;
            int i = this.typeCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.genericSecretBuilder_) != null) {
                return (GenericSecretOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (GenericSecret) this.type_;
            }
            return GenericSecret.getDefaultInstance();
        }

        private SingleFieldBuilderV3<GenericSecret, GenericSecret.Builder, GenericSecretOrBuilder> getGenericSecretFieldBuilder() {
            if (this.genericSecretBuilder_ == null) {
                if (this.typeCase_ != 5) {
                    this.type_ = GenericSecret.getDefaultInstance();
                }
                this.genericSecretBuilder_ = new SingleFieldBuilderV3<>((GenericSecret) this.type_, getParentForChildren(), isClean());
                this.type_ = null;
            }
            this.typeCase_ = 5;
            onChanged();
            return this.genericSecretBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m32059setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m32053mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.Secret$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$Secret$TypeCase;

        static {
            int[] iArr = new int[TypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$Secret$TypeCase = iArr;
            try {
                iArr[TypeCase.TLS_CERTIFICATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$Secret$TypeCase[TypeCase.SESSION_TICKET_KEYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$Secret$TypeCase[TypeCase.VALIDATION_CONTEXT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$Secret$TypeCase[TypeCase.GENERIC_SECRET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$transport_sockets$tls$v3$Secret$TypeCase[TypeCase.TYPE_NOT_SET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
