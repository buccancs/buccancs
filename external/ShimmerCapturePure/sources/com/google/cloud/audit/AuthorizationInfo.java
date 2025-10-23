package com.google.cloud.audit;

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
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class AuthorizationInfo extends GeneratedMessageV3 implements AuthorizationInfoOrBuilder {
    public static final int GRANTED_FIELD_NUMBER = 3;
    public static final int PERMISSION_FIELD_NUMBER = 2;
    public static final int RESOURCE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final AuthorizationInfo DEFAULT_INSTANCE = new AuthorizationInfo();
    private static final Parser<AuthorizationInfo> PARSER = new AbstractParser<AuthorizationInfo>() { // from class: com.google.cloud.audit.AuthorizationInfo.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public AuthorizationInfo m2845parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AuthorizationInfo(codedInputStream, extensionRegistryLite);
        }
    };
    private boolean granted_;
    private byte memoizedIsInitialized;
    private volatile Object permission_;
    private volatile Object resource_;

    private AuthorizationInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private AuthorizationInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.resource_ = "";
        this.permission_ = "";
    }

    private AuthorizationInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.resource_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            this.permission_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 24) {
                            this.granted_ = codedInputStream.readBool();
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

    public static AuthorizationInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthorizationInfo> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AuditLogProto.internal_static_google_cloud_audit_AuthorizationInfo_descriptor;
    }

    public static AuthorizationInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AuthorizationInfo) PARSER.parseFrom(byteBuffer);
    }

    public static AuthorizationInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthorizationInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AuthorizationInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AuthorizationInfo) PARSER.parseFrom(byteString);
    }

    public static AuthorizationInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthorizationInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AuthorizationInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AuthorizationInfo) PARSER.parseFrom(bArr);
    }

    public static AuthorizationInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthorizationInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AuthorizationInfo parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AuthorizationInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthorizationInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AuthorizationInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthorizationInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AuthorizationInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m2844toBuilder();
    }

    public static Builder newBuilder(AuthorizationInfo authorizationInfo) {
        return DEFAULT_INSTANCE.m2844toBuilder().mergeFrom(authorizationInfo);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public AuthorizationInfo m2839getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.cloud.audit.AuthorizationInfoOrBuilder
    public boolean getGranted() {
        return this.granted_;
    }

    public Parser<AuthorizationInfo> getParserForType() {
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
        return AuditLogProto.internal_static_google_cloud_audit_AuthorizationInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthorizationInfo.class, Builder.class);
    }

    @Override // com.google.cloud.audit.AuthorizationInfoOrBuilder
    public String getResource() {
        Object obj = this.resource_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.resource_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.audit.AuthorizationInfoOrBuilder
    public ByteString getResourceBytes() {
        Object obj = this.resource_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.resource_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.audit.AuthorizationInfoOrBuilder
    public String getPermission() {
        Object obj = this.permission_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.permission_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.audit.AuthorizationInfoOrBuilder
    public ByteString getPermissionBytes() {
        Object obj = this.permission_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.permission_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getResourceBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.resource_);
        }
        if (!getPermissionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.permission_);
        }
        boolean z = this.granted_;
        if (z) {
            codedOutputStream.writeBool(3, z);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getResourceBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.resource_) : 0;
        if (!getPermissionBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.permission_);
        }
        boolean z = this.granted_;
        if (z) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(3, z);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuthorizationInfo)) {
            return super.equals(obj);
        }
        AuthorizationInfo authorizationInfo = (AuthorizationInfo) obj;
        return getResource().equals(authorizationInfo.getResource()) && getPermission().equals(authorizationInfo.getPermission()) && getGranted() == authorizationInfo.getGranted() && this.unknownFields.equals(authorizationInfo.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getResource().hashCode()) * 37) + 2) * 53) + getPermission().hashCode()) * 37) + 3) * 53) + Internal.hashBoolean(getGranted())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m2842newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m2844toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m2841newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AuthorizationInfoOrBuilder {
        private boolean granted_;
        private Object permission_;
        private Object resource_;

        private Builder() {
            this.resource_ = "";
            this.permission_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.resource_ = "";
            this.permission_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AuditLogProto.internal_static_google_cloud_audit_AuthorizationInfo_descriptor;
        }

        @Override // com.google.cloud.audit.AuthorizationInfoOrBuilder
        public boolean getGranted() {
            return this.granted_;
        }

        public Builder setGranted(boolean z) {
            this.granted_ = z;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditLogProto.internal_static_google_cloud_audit_AuthorizationInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthorizationInfo.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = AuthorizationInfo.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2855clear() {
            super.clear();
            this.resource_ = "";
            this.permission_ = "";
            this.granted_ = false;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return AuditLogProto.internal_static_google_cloud_audit_AuthorizationInfo_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AuthorizationInfo m2868getDefaultInstanceForType() {
            return AuthorizationInfo.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AuthorizationInfo m2849build() throws UninitializedMessageException {
            AuthorizationInfo authorizationInfoM2851buildPartial = m2851buildPartial();
            if (authorizationInfoM2851buildPartial.isInitialized()) {
                return authorizationInfoM2851buildPartial;
            }
            throw newUninitializedMessageException(authorizationInfoM2851buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AuthorizationInfo m2851buildPartial() {
            AuthorizationInfo authorizationInfo = new AuthorizationInfo(this);
            authorizationInfo.resource_ = this.resource_;
            authorizationInfo.permission_ = this.permission_;
            authorizationInfo.granted_ = this.granted_;
            onBuilt();
            return authorizationInfo;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2866clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2879setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2857clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2860clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2881setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2847addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2873mergeFrom(Message message) {
            if (message instanceof AuthorizationInfo) {
                return mergeFrom((AuthorizationInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AuthorizationInfo authorizationInfo) {
            if (authorizationInfo == AuthorizationInfo.getDefaultInstance()) {
                return this;
            }
            if (!authorizationInfo.getResource().isEmpty()) {
                this.resource_ = authorizationInfo.resource_;
                onChanged();
            }
            if (!authorizationInfo.getPermission().isEmpty()) {
                this.permission_ = authorizationInfo.permission_;
                onChanged();
            }
            if (authorizationInfo.getGranted()) {
                setGranted(authorizationInfo.getGranted());
            }
            m2877mergeUnknownFields(authorizationInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.cloud.audit.AuthorizationInfo.Builder m2874mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.cloud.audit.AuthorizationInfo.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.cloud.audit.AuthorizationInfo r3 = (com.google.cloud.audit.AuthorizationInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.cloud.audit.AuthorizationInfo r4 = (com.google.cloud.audit.AuthorizationInfo) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.cloud.audit.AuthorizationInfo.Builder.m2874mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.cloud.audit.AuthorizationInfo$Builder");
        }

        @Override // com.google.cloud.audit.AuthorizationInfoOrBuilder
        public String getResource() {
            Object obj = this.resource_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.resource_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setResource(String str) {
            str.getClass();
            this.resource_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.audit.AuthorizationInfoOrBuilder
        public ByteString getResourceBytes() {
            Object obj = this.resource_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.resource_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setResourceBytes(ByteString byteString) {
            byteString.getClass();
            AuthorizationInfo.checkByteStringIsUtf8(byteString);
            this.resource_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearResource() {
            this.resource_ = AuthorizationInfo.getDefaultInstance().getResource();
            onChanged();
            return this;
        }

        @Override // com.google.cloud.audit.AuthorizationInfoOrBuilder
        public String getPermission() {
            Object obj = this.permission_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.permission_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setPermission(String str) {
            str.getClass();
            this.permission_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.audit.AuthorizationInfoOrBuilder
        public ByteString getPermissionBytes() {
            Object obj = this.permission_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.permission_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setPermissionBytes(ByteString byteString) {
            byteString.getClass();
            AuthorizationInfo.checkByteStringIsUtf8(byteString);
            this.permission_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearPermission() {
            this.permission_ = AuthorizationInfo.getDefaultInstance().getPermission();
            onChanged();
            return this;
        }

        public Builder clearGranted() {
            this.granted_ = false;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m2883setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m2877mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
