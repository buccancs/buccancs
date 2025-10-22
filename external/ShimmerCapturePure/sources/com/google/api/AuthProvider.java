package com.google.api;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.apache.commons.math.MaxIterationsExceededException;

/* loaded from: classes.dex */
public final class AuthProvider extends GeneratedMessageV3 implements AuthProviderOrBuilder {
    public static final int AUDIENCES_FIELD_NUMBER = 4;
    public static final int AUTHORIZATION_URL_FIELD_NUMBER = 5;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int ISSUER_FIELD_NUMBER = 2;
    public static final int JWKS_URI_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final AuthProvider DEFAULT_INSTANCE = new AuthProvider();
    private static final Parser<AuthProvider> PARSER = new AbstractParser<AuthProvider>() { // from class: com.google.api.AuthProvider.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public AuthProvider m249parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AuthProvider(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object audiences_;
    private volatile Object authorizationUrl_;
    private volatile Object id_;
    private volatile Object issuer_;
    private volatile Object jwksUri_;
    private byte memoizedIsInitialized;

    private AuthProvider(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private AuthProvider() {
        this.memoizedIsInitialized = (byte) -1;
        this.id_ = "";
        this.issuer_ = "";
        this.jwksUri_ = "";
        this.audiences_ = "";
        this.authorizationUrl_ = "";
    }

    private AuthProvider(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.id_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.issuer_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 26) {
                                this.jwksUri_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 34) {
                                this.audiences_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 42) {
                                this.authorizationUrl_ = codedInputStream.readStringRequireUtf8();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
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

    public static AuthProvider getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthProvider> parser() {
        return PARSER;
    }

    static /* synthetic */ void access$1100(ByteString byteString) throws IllegalArgumentException {
        new MaxIterationsExceededException();
    }

    static /* synthetic */ void access$1200(ByteString byteString) throws IllegalArgumentException {
        new MaxIterationsExceededException();
    }

    static /* synthetic */ void access$1300(ByteString byteString) throws IllegalArgumentException {
        new MaxIterationsExceededException();
    }

    static /* synthetic */ void access$1400(ByteString byteString) throws IllegalArgumentException {
        new MaxIterationsExceededException();
    }

    static /* synthetic */ void access$1500(ByteString byteString) throws IllegalArgumentException {
        new MaxIterationsExceededException();
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AuthProto.internal_static_google_api_AuthProvider_descriptor;
    }

    public static AuthProvider parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AuthProvider) PARSER.parseFrom(byteBuffer);
    }

    public static AuthProvider parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthProvider) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AuthProvider) PARSER.parseFrom(byteString);
    }

    public static AuthProvider parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthProvider) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AuthProvider) PARSER.parseFrom(bArr);
    }

    public static AuthProvider parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthProvider) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AuthProvider parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthProvider parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AuthProvider parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AuthProvider parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m248toBuilder();
    }

    public static Builder newBuilder(AuthProvider authProvider) {
        return DEFAULT_INSTANCE.m248toBuilder().mergeFrom(authProvider);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public AuthProvider m243getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<AuthProvider> getParserForType() {
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

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuthProto.internal_static_google_api_AuthProvider_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthProvider.class, Builder.class);
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public String getId() {
        Object obj = this.id_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.id_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public ByteString getIdBytes() {
        Object obj = this.id_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.id_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public String getIssuer() {
        Object obj = this.issuer_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.issuer_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public ByteString getIssuerBytes() {
        Object obj = this.issuer_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.issuer_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public String getJwksUri() {
        Object obj = this.jwksUri_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.jwksUri_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public ByteString getJwksUriBytes() {
        Object obj = this.jwksUri_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.jwksUri_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public String getAudiences() {
        Object obj = this.audiences_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.audiences_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public ByteString getAudiencesBytes() {
        Object obj = this.audiences_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.audiences_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public String getAuthorizationUrl() {
        Object obj = this.authorizationUrl_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.authorizationUrl_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public ByteString getAuthorizationUrlBytes() {
        Object obj = this.authorizationUrl_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.authorizationUrl_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.id_);
        }
        if (!getIssuerBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.issuer_);
        }
        if (!getJwksUriBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.jwksUri_);
        }
        if (!getAudiencesBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.audiences_);
        }
        if (!getAuthorizationUrlBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.authorizationUrl_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getIdBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.id_) : 0;
        if (!getIssuerBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.issuer_);
        }
        if (!getJwksUriBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.jwksUri_);
        }
        if (!getAudiencesBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.audiences_);
        }
        if (!getAuthorizationUrlBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(5, this.authorizationUrl_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuthProvider)) {
            return super.equals(obj);
        }
        AuthProvider authProvider = (AuthProvider) obj;
        return getId().equals(authProvider.getId()) && getIssuer().equals(authProvider.getIssuer()) && getJwksUri().equals(authProvider.getJwksUri()) && getAudiences().equals(authProvider.getAudiences()) && getAuthorizationUrl().equals(authProvider.getAuthorizationUrl()) && this.unknownFields.equals(authProvider.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getId().hashCode()) * 37) + 2) * 53) + getIssuer().hashCode()) * 37) + 3) * 53) + getJwksUri().hashCode()) * 37) + 4) * 53) + getAudiences().hashCode()) * 37) + 5) * 53) + getAuthorizationUrl().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m246newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m248toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m245newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AuthProviderOrBuilder {
        private Object audiences_;
        private Object authorizationUrl_;
        private Object id_;
        private Object issuer_;
        private Object jwksUri_;

        private Builder() {
            this.id_ = "";
            this.issuer_ = "";
            this.jwksUri_ = "";
            this.audiences_ = "";
            this.authorizationUrl_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.id_ = "";
            this.issuer_ = "";
            this.jwksUri_ = "";
            this.audiences_ = "";
            this.authorizationUrl_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AuthProto.internal_static_google_api_AuthProvider_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuthProto.internal_static_google_api_AuthProvider_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthProvider.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = AuthProvider.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m259clear() {
            super.clear();
            this.id_ = "";
            this.issuer_ = "";
            this.jwksUri_ = "";
            this.audiences_ = "";
            this.authorizationUrl_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return AuthProto.internal_static_google_api_AuthProvider_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AuthProvider m272getDefaultInstanceForType() {
            return AuthProvider.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AuthProvider m253build() throws UninitializedMessageException {
            AuthProvider authProviderM255buildPartial = m255buildPartial();
            if (authProviderM255buildPartial.isInitialized()) {
                return authProviderM255buildPartial;
            }
            throw newUninitializedMessageException(authProviderM255buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AuthProvider m255buildPartial() {
            AuthProvider authProvider = new AuthProvider(this);
            authProvider.id_ = this.id_;
            authProvider.issuer_ = this.issuer_;
            authProvider.jwksUri_ = this.jwksUri_;
            authProvider.audiences_ = this.audiences_;
            authProvider.authorizationUrl_ = this.authorizationUrl_;
            onBuilt();
            return authProvider;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m270clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m283setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m261clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m264clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m285setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m251addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m277mergeFrom(Message message) {
            if (message instanceof AuthProvider) {
                return mergeFrom((AuthProvider) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AuthProvider authProvider) {
            if (authProvider == AuthProvider.getDefaultInstance()) {
                return this;
            }
            if (!authProvider.getId().isEmpty()) {
                this.id_ = authProvider.id_;
                onChanged();
            }
            if (!authProvider.getIssuer().isEmpty()) {
                this.issuer_ = authProvider.issuer_;
                onChanged();
            }
            if (!authProvider.getJwksUri().isEmpty()) {
                this.jwksUri_ = authProvider.jwksUri_;
                onChanged();
            }
            if (!authProvider.getAudiences().isEmpty()) {
                this.audiences_ = authProvider.audiences_;
                onChanged();
            }
            if (!authProvider.getAuthorizationUrl().isEmpty()) {
                this.authorizationUrl_ = authProvider.authorizationUrl_;
                onChanged();
            }
            m281mergeUnknownFields(authProvider.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.api.AuthProvider.Builder m278mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.AuthProvider.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.AuthProvider r3 = (com.google.api.AuthProvider) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.AuthProvider r4 = (com.google.api.AuthProvider) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.AuthProvider.Builder.m278mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.AuthProvider$Builder");
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public String getId() {
            Object obj = this.id_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.id_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setId(String str) {
            str.getClass();
            this.id_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public ByteString getIdBytes() {
            Object obj = this.id_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.id_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setIdBytes(ByteString byteString) throws IllegalArgumentException {
            byteString.getClass();
            AuthProvider.access$1100(byteString);
            this.id_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearId() {
            this.id_ = AuthProvider.getDefaultInstance().getId();
            onChanged();
            return this;
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public String getIssuer() {
            Object obj = this.issuer_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.issuer_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setIssuer(String str) {
            str.getClass();
            this.issuer_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public ByteString getIssuerBytes() {
            Object obj = this.issuer_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.issuer_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setIssuerBytes(ByteString byteString) throws IllegalArgumentException {
            byteString.getClass();
            AuthProvider.access$1200(byteString);
            this.issuer_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearIssuer() {
            this.issuer_ = AuthProvider.getDefaultInstance().getIssuer();
            onChanged();
            return this;
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public String getJwksUri() {
            Object obj = this.jwksUri_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.jwksUri_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setJwksUri(String str) {
            str.getClass();
            this.jwksUri_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public ByteString getJwksUriBytes() {
            Object obj = this.jwksUri_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.jwksUri_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setJwksUriBytes(ByteString byteString) throws IllegalArgumentException {
            byteString.getClass();
            AuthProvider.access$1300(byteString);
            this.jwksUri_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearJwksUri() {
            this.jwksUri_ = AuthProvider.getDefaultInstance().getJwksUri();
            onChanged();
            return this;
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public String getAudiences() {
            Object obj = this.audiences_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.audiences_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setAudiences(String str) {
            str.getClass();
            this.audiences_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public ByteString getAudiencesBytes() {
            Object obj = this.audiences_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.audiences_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAudiencesBytes(ByteString byteString) throws IllegalArgumentException {
            byteString.getClass();
            AuthProvider.access$1400(byteString);
            this.audiences_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearAudiences() {
            this.audiences_ = AuthProvider.getDefaultInstance().getAudiences();
            onChanged();
            return this;
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public String getAuthorizationUrl() {
            Object obj = this.authorizationUrl_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.authorizationUrl_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setAuthorizationUrl(String str) {
            str.getClass();
            this.authorizationUrl_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public ByteString getAuthorizationUrlBytes() {
            Object obj = this.authorizationUrl_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.authorizationUrl_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAuthorizationUrlBytes(ByteString byteString) throws IllegalArgumentException {
            byteString.getClass();
            AuthProvider.access$1500(byteString);
            this.authorizationUrl_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearAuthorizationUrl() {
            this.authorizationUrl_ = AuthProvider.getDefaultInstance().getAuthorizationUrl();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m287setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m281mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
