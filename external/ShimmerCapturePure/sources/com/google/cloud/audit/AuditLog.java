package com.google.cloud.audit;

import com.google.cloud.audit.AuthenticationInfo;
import com.google.cloud.audit.AuthorizationInfo;
import com.google.cloud.audit.RequestMetadata;
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
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class AuditLog extends GeneratedMessageV3 implements AuditLogOrBuilder {
    public static final int AUTHENTICATION_INFO_FIELD_NUMBER = 3;
    public static final int AUTHORIZATION_INFO_FIELD_NUMBER = 9;
    public static final int METHOD_NAME_FIELD_NUMBER = 8;
    public static final int NUM_RESPONSE_ITEMS_FIELD_NUMBER = 12;
    public static final int REQUEST_FIELD_NUMBER = 16;
    public static final int REQUEST_METADATA_FIELD_NUMBER = 4;
    public static final int RESOURCE_NAME_FIELD_NUMBER = 11;
    public static final int RESPONSE_FIELD_NUMBER = 17;
    public static final int SERVICE_DATA_FIELD_NUMBER = 15;
    public static final int SERVICE_NAME_FIELD_NUMBER = 7;
    public static final int STATUS_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final AuditLog DEFAULT_INSTANCE = new AuditLog();
    private static final Parser<AuditLog> PARSER = new AbstractParser<AuditLog>() { // from class: com.google.cloud.audit.AuditLog.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public AuditLog m2753parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AuditLog(codedInputStream, extensionRegistryLite);
        }
    };
    private AuthenticationInfo authenticationInfo_;
    private List<AuthorizationInfo> authorizationInfo_;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private volatile Object methodName_;
    private long numResponseItems_;
    private RequestMetadata requestMetadata_;
    private Struct request_;
    private volatile Object resourceName_;
    private Struct response_;
    private Any serviceData_;
    private volatile Object serviceName_;
    private Status status_;

    private AuditLog(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private AuditLog() {
        this.memoizedIsInitialized = (byte) -1;
        this.serviceName_ = "";
        this.methodName_ = "";
        this.resourceName_ = "";
        this.authorizationInfo_ = Collections.emptyList();
    }

    private AuditLog(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Struct.Builder builderM3997toBuilder;
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
                            case 18:
                                Status status = this.status_;
                                builderM3997toBuilder = status != null ? status.m3997toBuilder() : null;
                                Status message = codedInputStream.readMessage(Status.parser(), extensionRegistryLite);
                                this.status_ = message;
                                if (builderM3997toBuilder != null) {
                                    builderM3997toBuilder.mergeFrom(message);
                                    this.status_ = builderM3997toBuilder.m4004buildPartial();
                                }
                            case 26:
                                AuthenticationInfo authenticationInfo = this.authenticationInfo_;
                                builderM3997toBuilder = authenticationInfo != null ? authenticationInfo.m2798toBuilder() : null;
                                AuthenticationInfo message2 = codedInputStream.readMessage(AuthenticationInfo.parser(), extensionRegistryLite);
                                this.authenticationInfo_ = message2;
                                if (builderM3997toBuilder != null) {
                                    builderM3997toBuilder.mergeFrom(message2);
                                    this.authenticationInfo_ = builderM3997toBuilder.m2805buildPartial();
                                }
                            case 34:
                                RequestMetadata requestMetadata = this.requestMetadata_;
                                builderM3997toBuilder = requestMetadata != null ? requestMetadata.m2890toBuilder() : null;
                                RequestMetadata message3 = codedInputStream.readMessage(RequestMetadata.parser(), extensionRegistryLite);
                                this.requestMetadata_ = message3;
                                if (builderM3997toBuilder != null) {
                                    builderM3997toBuilder.mergeFrom(message3);
                                    this.requestMetadata_ = builderM3997toBuilder.m2897buildPartial();
                                }
                            case 58:
                                this.serviceName_ = codedInputStream.readStringRequireUtf8();
                            case 66:
                                this.methodName_ = codedInputStream.readStringRequireUtf8();
                            case 74:
                                if ((i & 64) == 0) {
                                    this.authorizationInfo_ = new ArrayList();
                                    i |= 64;
                                }
                                this.authorizationInfo_.add(codedInputStream.readMessage(AuthorizationInfo.parser(), extensionRegistryLite));
                            case RESET_TO_DEFAULT_CONFIGURATION_COMMAND_VALUE:
                                this.resourceName_ = codedInputStream.readStringRequireUtf8();
                            case GET_INTERNAL_EXP_POWER_ENABLE_COMMAND_VALUE:
                                this.numResponseItems_ = codedInputStream.readInt64();
                            case 122:
                                Any any = this.serviceData_;
                                builderM3997toBuilder = any != null ? any.toBuilder() : null;
                                Any message4 = codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                                this.serviceData_ = message4;
                                if (builderM3997toBuilder != null) {
                                    builderM3997toBuilder.mergeFrom(message4);
                                    this.serviceData_ = builderM3997toBuilder.buildPartial();
                                }
                            case 130:
                                Struct struct = this.request_;
                                builderM3997toBuilder = struct != null ? struct.toBuilder() : null;
                                Struct message5 = codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                                this.request_ = message5;
                                if (builderM3997toBuilder != null) {
                                    builderM3997toBuilder.mergeFrom(message5);
                                    this.request_ = builderM3997toBuilder.buildPartial();
                                }
                            case 138:
                                Struct struct2 = this.response_;
                                builderM3997toBuilder = struct2 != null ? struct2.toBuilder() : null;
                                Struct message6 = codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                                this.response_ = message6;
                                if (builderM3997toBuilder != null) {
                                    builderM3997toBuilder.mergeFrom(message6);
                                    this.response_ = builderM3997toBuilder.buildPartial();
                                }
                            default:
                                if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    z = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    }
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if ((i & 64) != 0) {
                    this.authorizationInfo_ = Collections.unmodifiableList(this.authorizationInfo_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static AuditLog getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuditLog> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AuditLogProto.internal_static_google_cloud_audit_AuditLog_descriptor;
    }

    public static AuditLog parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AuditLog) PARSER.parseFrom(byteBuffer);
    }

    public static AuditLog parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuditLog) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AuditLog parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AuditLog) PARSER.parseFrom(byteString);
    }

    public static AuditLog parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuditLog) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AuditLog parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AuditLog) PARSER.parseFrom(bArr);
    }

    public static AuditLog parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuditLog) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AuditLog parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AuditLog parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuditLog parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AuditLog parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuditLog parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AuditLog parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m2752toBuilder();
    }

    public static Builder newBuilder(AuditLog auditLog) {
        return DEFAULT_INSTANCE.m2752toBuilder().mergeFrom(auditLog);
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public List<AuthorizationInfo> getAuthorizationInfoList() {
        return this.authorizationInfo_;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public List<? extends AuthorizationInfoOrBuilder> getAuthorizationInfoOrBuilderList() {
        return this.authorizationInfo_;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public AuditLog m2747getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public long getNumResponseItems() {
        return this.numResponseItems_;
    }

    public Parser<AuditLog> getParserForType() {
        return PARSER;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasAuthenticationInfo() {
        return this.authenticationInfo_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasRequest() {
        return this.request_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasRequestMetadata() {
        return this.requestMetadata_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasResponse() {
        return this.response_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasServiceData() {
        return this.serviceData_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasStatus() {
        return this.status_ != null;
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
        return AuditLogProto.internal_static_google_cloud_audit_AuditLog_fieldAccessorTable.ensureFieldAccessorsInitialized(AuditLog.class, Builder.class);
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public String getServiceName() {
        Object obj = this.serviceName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.serviceName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public ByteString getServiceNameBytes() {
        Object obj = this.serviceName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.serviceName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public String getMethodName() {
        Object obj = this.methodName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.methodName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public ByteString getMethodNameBytes() {
        Object obj = this.methodName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.methodName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public String getResourceName() {
        Object obj = this.resourceName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.resourceName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public ByteString getResourceNameBytes() {
        Object obj = this.resourceName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.resourceName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Status getStatus() {
        Status status = this.status_;
        return status == null ? Status.getDefaultInstance() : status;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public StatusOrBuilder getStatusOrBuilder() {
        return getStatus();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public AuthenticationInfo getAuthenticationInfo() {
        AuthenticationInfo authenticationInfo = this.authenticationInfo_;
        return authenticationInfo == null ? AuthenticationInfo.getDefaultInstance() : authenticationInfo;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public AuthenticationInfoOrBuilder getAuthenticationInfoOrBuilder() {
        return getAuthenticationInfo();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public int getAuthorizationInfoCount() {
        return this.authorizationInfo_.size();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public AuthorizationInfo getAuthorizationInfo(int i) {
        return this.authorizationInfo_.get(i);
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public AuthorizationInfoOrBuilder getAuthorizationInfoOrBuilder(int i) {
        return this.authorizationInfo_.get(i);
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public RequestMetadata getRequestMetadata() {
        RequestMetadata requestMetadata = this.requestMetadata_;
        return requestMetadata == null ? RequestMetadata.getDefaultInstance() : requestMetadata;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public RequestMetadataOrBuilder getRequestMetadataOrBuilder() {
        return getRequestMetadata();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Struct getRequest() {
        Struct struct = this.request_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public StructOrBuilder getRequestOrBuilder() {
        return getRequest();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Struct getResponse() {
        Struct struct = this.response_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public StructOrBuilder getResponseOrBuilder() {
        return getResponse();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Any getServiceData() {
        Any any = this.serviceData_;
        return any == null ? Any.getDefaultInstance() : any;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public AnyOrBuilder getServiceDataOrBuilder() {
        return getServiceData();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.status_ != null) {
            codedOutputStream.writeMessage(2, getStatus());
        }
        if (this.authenticationInfo_ != null) {
            codedOutputStream.writeMessage(3, getAuthenticationInfo());
        }
        if (this.requestMetadata_ != null) {
            codedOutputStream.writeMessage(4, getRequestMetadata());
        }
        if (!getServiceNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.serviceName_);
        }
        if (!getMethodNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.methodName_);
        }
        for (int i = 0; i < this.authorizationInfo_.size(); i++) {
            codedOutputStream.writeMessage(9, this.authorizationInfo_.get(i));
        }
        if (!getResourceNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 11, this.resourceName_);
        }
        long j = this.numResponseItems_;
        if (j != 0) {
            codedOutputStream.writeInt64(12, j);
        }
        if (this.serviceData_ != null) {
            codedOutputStream.writeMessage(15, getServiceData());
        }
        if (this.request_ != null) {
            codedOutputStream.writeMessage(16, getRequest());
        }
        if (this.response_ != null) {
            codedOutputStream.writeMessage(17, getResponse());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.status_ != null ? CodedOutputStream.computeMessageSize(2, getStatus()) : 0;
        if (this.authenticationInfo_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getAuthenticationInfo());
        }
        if (this.requestMetadata_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(4, getRequestMetadata());
        }
        if (!getServiceNameBytes().isEmpty()) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(7, this.serviceName_);
        }
        if (!getMethodNameBytes().isEmpty()) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(8, this.methodName_);
        }
        for (int i2 = 0; i2 < this.authorizationInfo_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(9, this.authorizationInfo_.get(i2));
        }
        if (!getResourceNameBytes().isEmpty()) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(11, this.resourceName_);
        }
        long j = this.numResponseItems_;
        if (j != 0) {
            iComputeMessageSize += CodedOutputStream.computeInt64Size(12, j);
        }
        if (this.serviceData_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(15, getServiceData());
        }
        if (this.request_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(16, getRequest());
        }
        if (this.response_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(17, getResponse());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuditLog)) {
            return super.equals(obj);
        }
        AuditLog auditLog = (AuditLog) obj;
        if (!getServiceName().equals(auditLog.getServiceName()) || !getMethodName().equals(auditLog.getMethodName()) || !getResourceName().equals(auditLog.getResourceName()) || getNumResponseItems() != auditLog.getNumResponseItems() || hasStatus() != auditLog.hasStatus()) {
            return false;
        }
        if ((hasStatus() && !getStatus().equals(auditLog.getStatus())) || hasAuthenticationInfo() != auditLog.hasAuthenticationInfo()) {
            return false;
        }
        if ((hasAuthenticationInfo() && !getAuthenticationInfo().equals(auditLog.getAuthenticationInfo())) || !getAuthorizationInfoList().equals(auditLog.getAuthorizationInfoList()) || hasRequestMetadata() != auditLog.hasRequestMetadata()) {
            return false;
        }
        if ((hasRequestMetadata() && !getRequestMetadata().equals(auditLog.getRequestMetadata())) || hasRequest() != auditLog.hasRequest()) {
            return false;
        }
        if ((hasRequest() && !getRequest().equals(auditLog.getRequest())) || hasResponse() != auditLog.hasResponse()) {
            return false;
        }
        if ((!hasResponse() || getResponse().equals(auditLog.getResponse())) && hasServiceData() == auditLog.hasServiceData()) {
            return (!hasServiceData() || getServiceData().equals(auditLog.getServiceData())) && this.unknownFields.equals(auditLog.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 7) * 53) + getServiceName().hashCode()) * 37) + 8) * 53) + getMethodName().hashCode()) * 37) + 11) * 53) + getResourceName().hashCode()) * 37) + 12) * 53) + Internal.hashLong(getNumResponseItems());
        if (hasStatus()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getStatus().hashCode();
        }
        if (hasAuthenticationInfo()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getAuthenticationInfo().hashCode();
        }
        if (getAuthorizationInfoCount() > 0) {
            iHashCode = (((iHashCode * 37) + 9) * 53) + getAuthorizationInfoList().hashCode();
        }
        if (hasRequestMetadata()) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getRequestMetadata().hashCode();
        }
        if (hasRequest()) {
            iHashCode = (((iHashCode * 37) + 16) * 53) + getRequest().hashCode();
        }
        if (hasResponse()) {
            iHashCode = (((iHashCode * 37) + 17) * 53) + getResponse().hashCode();
        }
        if (hasServiceData()) {
            iHashCode = (((iHashCode * 37) + 15) * 53) + getServiceData().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m2750newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m2752toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m2749newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AuditLogOrBuilder {
        private SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> authenticationInfoBuilder_;
        private AuthenticationInfo authenticationInfo_;
        private RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> authorizationInfoBuilder_;
        private List<AuthorizationInfo> authorizationInfo_;
        private int bitField0_;
        private Object methodName_;
        private long numResponseItems_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> requestBuilder_;
        private SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> requestMetadataBuilder_;
        private RequestMetadata requestMetadata_;
        private Struct request_;
        private Object resourceName_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> responseBuilder_;
        private Struct response_;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> serviceDataBuilder_;
        private Any serviceData_;
        private Object serviceName_;
        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> statusBuilder_;
        private Status status_;

        private Builder() {
            this.serviceName_ = "";
            this.methodName_ = "";
            this.resourceName_ = "";
            this.authorizationInfo_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.serviceName_ = "";
            this.methodName_ = "";
            this.resourceName_ = "";
            this.authorizationInfo_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AuditLogProto.internal_static_google_cloud_audit_AuditLog_descriptor;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public long getNumResponseItems() {
            return this.numResponseItems_;
        }

        public Builder setNumResponseItems(long j) {
            this.numResponseItems_ = j;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasAuthenticationInfo() {
            return (this.authenticationInfoBuilder_ == null && this.authenticationInfo_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasRequest() {
            return (this.requestBuilder_ == null && this.request_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasRequestMetadata() {
            return (this.requestMetadataBuilder_ == null && this.requestMetadata_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasResponse() {
            return (this.responseBuilder_ == null && this.response_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasServiceData() {
            return (this.serviceDataBuilder_ == null && this.serviceData_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasStatus() {
            return (this.statusBuilder_ == null && this.status_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditLogProto.internal_static_google_cloud_audit_AuditLog_fieldAccessorTable.ensureFieldAccessorsInitialized(AuditLog.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (AuditLog.alwaysUseFieldBuilders) {
                getAuthorizationInfoFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2763clear() {
            super.clear();
            this.serviceName_ = "";
            this.methodName_ = "";
            this.resourceName_ = "";
            this.numResponseItems_ = 0L;
            if (this.statusBuilder_ == null) {
                this.status_ = null;
            } else {
                this.status_ = null;
                this.statusBuilder_ = null;
            }
            if (this.authenticationInfoBuilder_ == null) {
                this.authenticationInfo_ = null;
            } else {
                this.authenticationInfo_ = null;
                this.authenticationInfoBuilder_ = null;
            }
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.authorizationInfo_ = Collections.emptyList();
                this.bitField0_ &= -65;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            if (this.requestMetadataBuilder_ == null) {
                this.requestMetadata_ = null;
            } else {
                this.requestMetadata_ = null;
                this.requestMetadataBuilder_ = null;
            }
            if (this.requestBuilder_ == null) {
                this.request_ = null;
            } else {
                this.request_ = null;
                this.requestBuilder_ = null;
            }
            if (this.responseBuilder_ == null) {
                this.response_ = null;
            } else {
                this.response_ = null;
                this.responseBuilder_ = null;
            }
            if (this.serviceDataBuilder_ == null) {
                this.serviceData_ = null;
            } else {
                this.serviceData_ = null;
                this.serviceDataBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return AuditLogProto.internal_static_google_cloud_audit_AuditLog_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AuditLog m2776getDefaultInstanceForType() {
            return AuditLog.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AuditLog m2757build() throws UninitializedMessageException {
            AuditLog auditLogM2759buildPartial = m2759buildPartial();
            if (auditLogM2759buildPartial.isInitialized()) {
                return auditLogM2759buildPartial;
            }
            throw newUninitializedMessageException(auditLogM2759buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AuditLog m2759buildPartial() {
            AuditLog auditLog = new AuditLog(this);
            auditLog.serviceName_ = this.serviceName_;
            auditLog.methodName_ = this.methodName_;
            auditLog.resourceName_ = this.resourceName_;
            auditLog.numResponseItems_ = this.numResponseItems_;
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                auditLog.status_ = this.status_;
            } else {
                auditLog.status_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV32 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV32 == null) {
                auditLog.authenticationInfo_ = this.authenticationInfo_;
            } else {
                auditLog.authenticationInfo_ = singleFieldBuilderV32.build();
            }
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 64) != 0) {
                    this.authorizationInfo_ = Collections.unmodifiableList(this.authorizationInfo_);
                    this.bitField0_ &= -65;
                }
                auditLog.authorizationInfo_ = this.authorizationInfo_;
            } else {
                auditLog.authorizationInfo_ = repeatedFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV33 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV33 == null) {
                auditLog.requestMetadata_ = this.requestMetadata_;
            } else {
                auditLog.requestMetadata_ = singleFieldBuilderV33.build();
            }
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV34 = this.requestBuilder_;
            if (singleFieldBuilderV34 == null) {
                auditLog.request_ = this.request_;
            } else {
                auditLog.request_ = singleFieldBuilderV34.build();
            }
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV35 = this.responseBuilder_;
            if (singleFieldBuilderV35 == null) {
                auditLog.response_ = this.response_;
            } else {
                auditLog.response_ = singleFieldBuilderV35.build();
            }
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV36 = this.serviceDataBuilder_;
            if (singleFieldBuilderV36 == null) {
                auditLog.serviceData_ = this.serviceData_;
            } else {
                auditLog.serviceData_ = singleFieldBuilderV36.build();
            }
            auditLog.bitField0_ = 0;
            onBuilt();
            return auditLog;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2774clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2787setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2765clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2768clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2789setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2755addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2781mergeFrom(Message message) {
            if (message instanceof AuditLog) {
                return mergeFrom((AuditLog) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AuditLog auditLog) {
            if (auditLog == AuditLog.getDefaultInstance()) {
                return this;
            }
            if (!auditLog.getServiceName().isEmpty()) {
                this.serviceName_ = auditLog.serviceName_;
                onChanged();
            }
            if (!auditLog.getMethodName().isEmpty()) {
                this.methodName_ = auditLog.methodName_;
                onChanged();
            }
            if (!auditLog.getResourceName().isEmpty()) {
                this.resourceName_ = auditLog.resourceName_;
                onChanged();
            }
            if (auditLog.getNumResponseItems() != 0) {
                setNumResponseItems(auditLog.getNumResponseItems());
            }
            if (auditLog.hasStatus()) {
                mergeStatus(auditLog.getStatus());
            }
            if (auditLog.hasAuthenticationInfo()) {
                mergeAuthenticationInfo(auditLog.getAuthenticationInfo());
            }
            if (this.authorizationInfoBuilder_ == null) {
                if (!auditLog.authorizationInfo_.isEmpty()) {
                    if (this.authorizationInfo_.isEmpty()) {
                        this.authorizationInfo_ = auditLog.authorizationInfo_;
                        this.bitField0_ &= -65;
                    } else {
                        ensureAuthorizationInfoIsMutable();
                        this.authorizationInfo_.addAll(auditLog.authorizationInfo_);
                    }
                    onChanged();
                }
            } else if (!auditLog.authorizationInfo_.isEmpty()) {
                if (!this.authorizationInfoBuilder_.isEmpty()) {
                    this.authorizationInfoBuilder_.addAllMessages(auditLog.authorizationInfo_);
                } else {
                    this.authorizationInfoBuilder_.dispose();
                    this.authorizationInfoBuilder_ = null;
                    this.authorizationInfo_ = auditLog.authorizationInfo_;
                    this.bitField0_ &= -65;
                    this.authorizationInfoBuilder_ = AuditLog.alwaysUseFieldBuilders ? getAuthorizationInfoFieldBuilder() : null;
                }
            }
            if (auditLog.hasRequestMetadata()) {
                mergeRequestMetadata(auditLog.getRequestMetadata());
            }
            if (auditLog.hasRequest()) {
                mergeRequest(auditLog.getRequest());
            }
            if (auditLog.hasResponse()) {
                mergeResponse(auditLog.getResponse());
            }
            if (auditLog.hasServiceData()) {
                mergeServiceData(auditLog.getServiceData());
            }
            m2785mergeUnknownFields(auditLog.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.cloud.audit.AuditLog.Builder m2782mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.cloud.audit.AuditLog.access$1800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.cloud.audit.AuditLog r3 = (com.google.cloud.audit.AuditLog) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.cloud.audit.AuditLog r4 = (com.google.cloud.audit.AuditLog) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.cloud.audit.AuditLog.Builder.m2782mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.cloud.audit.AuditLog$Builder");
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public String getServiceName() {
            Object obj = this.serviceName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.serviceName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setServiceName(String str) {
            str.getClass();
            this.serviceName_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public ByteString getServiceNameBytes() {
            Object obj = this.serviceName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.serviceName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setServiceNameBytes(ByteString byteString) {
            byteString.getClass();
            AuditLog.checkByteStringIsUtf8(byteString);
            this.serviceName_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearServiceName() {
            this.serviceName_ = AuditLog.getDefaultInstance().getServiceName();
            onChanged();
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public String getMethodName() {
            Object obj = this.methodName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.methodName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setMethodName(String str) {
            str.getClass();
            this.methodName_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public ByteString getMethodNameBytes() {
            Object obj = this.methodName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.methodName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setMethodNameBytes(ByteString byteString) {
            byteString.getClass();
            AuditLog.checkByteStringIsUtf8(byteString);
            this.methodName_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearMethodName() {
            this.methodName_ = AuditLog.getDefaultInstance().getMethodName();
            onChanged();
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public String getResourceName() {
            Object obj = this.resourceName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.resourceName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setResourceName(String str) {
            str.getClass();
            this.resourceName_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public ByteString getResourceNameBytes() {
            Object obj = this.resourceName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.resourceName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setResourceNameBytes(ByteString byteString) {
            byteString.getClass();
            AuditLog.checkByteStringIsUtf8(byteString);
            this.resourceName_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearResourceName() {
            this.resourceName_ = AuditLog.getDefaultInstance().getResourceName();
            onChanged();
            return this;
        }

        public Builder clearNumResponseItems() {
            this.numResponseItems_ = 0L;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Status getStatus() {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Status status = this.status_;
            return status == null ? Status.getDefaultInstance() : status;
        }

        public Builder setStatus(Status status) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                status.getClass();
                this.status_ = status;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(status);
            }
            return this;
        }

        public Builder setStatus(Status.Builder builder) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.status_ = builder.m4002build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m4002build());
            }
            return this;
        }

        public Builder mergeStatus(Status status) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                Status status2 = this.status_;
                if (status2 != null) {
                    this.status_ = Status.newBuilder(status2).mergeFrom(status).m4004buildPartial();
                } else {
                    this.status_ = status;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(status);
            }
            return this;
        }

        public Builder clearStatus() {
            if (this.statusBuilder_ == null) {
                this.status_ = null;
                onChanged();
            } else {
                this.status_ = null;
                this.statusBuilder_ = null;
            }
            return this;
        }

        public Status.Builder getStatusBuilder() {
            onChanged();
            return getStatusFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public StatusOrBuilder getStatusOrBuilder() {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (StatusOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Status status = this.status_;
            return status == null ? Status.getDefaultInstance() : status;
        }

        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> getStatusFieldBuilder() {
            if (this.statusBuilder_ == null) {
                this.statusBuilder_ = new SingleFieldBuilderV3<>(getStatus(), getParentForChildren(), isClean());
                this.status_ = null;
            }
            return this.statusBuilder_;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public AuthenticationInfo getAuthenticationInfo() {
            SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            AuthenticationInfo authenticationInfo = this.authenticationInfo_;
            return authenticationInfo == null ? AuthenticationInfo.getDefaultInstance() : authenticationInfo;
        }

        public Builder setAuthenticationInfo(AuthenticationInfo authenticationInfo) {
            SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                authenticationInfo.getClass();
                this.authenticationInfo_ = authenticationInfo;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(authenticationInfo);
            }
            return this;
        }

        public Builder setAuthenticationInfo(AuthenticationInfo.Builder builder) {
            SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.authenticationInfo_ = builder.m2803build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m2803build());
            }
            return this;
        }

        public Builder mergeAuthenticationInfo(AuthenticationInfo authenticationInfo) {
            SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                AuthenticationInfo authenticationInfo2 = this.authenticationInfo_;
                if (authenticationInfo2 != null) {
                    this.authenticationInfo_ = AuthenticationInfo.newBuilder(authenticationInfo2).mergeFrom(authenticationInfo).m2805buildPartial();
                } else {
                    this.authenticationInfo_ = authenticationInfo;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(authenticationInfo);
            }
            return this;
        }

        public Builder clearAuthenticationInfo() {
            if (this.authenticationInfoBuilder_ == null) {
                this.authenticationInfo_ = null;
                onChanged();
            } else {
                this.authenticationInfo_ = null;
                this.authenticationInfoBuilder_ = null;
            }
            return this;
        }

        public AuthenticationInfo.Builder getAuthenticationInfoBuilder() {
            onChanged();
            return getAuthenticationInfoFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public AuthenticationInfoOrBuilder getAuthenticationInfoOrBuilder() {
            SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (AuthenticationInfoOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            AuthenticationInfo authenticationInfo = this.authenticationInfo_;
            return authenticationInfo == null ? AuthenticationInfo.getDefaultInstance() : authenticationInfo;
        }

        private SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> getAuthenticationInfoFieldBuilder() {
            if (this.authenticationInfoBuilder_ == null) {
                this.authenticationInfoBuilder_ = new SingleFieldBuilderV3<>(getAuthenticationInfo(), getParentForChildren(), isClean());
                this.authenticationInfo_ = null;
            }
            return this.authenticationInfoBuilder_;
        }

        private void ensureAuthorizationInfoIsMutable() {
            if ((this.bitField0_ & 64) == 0) {
                this.authorizationInfo_ = new ArrayList(this.authorizationInfo_);
                this.bitField0_ |= 64;
            }
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public List<AuthorizationInfo> getAuthorizationInfoList() {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.authorizationInfo_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public int getAuthorizationInfoCount() {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.authorizationInfo_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public AuthorizationInfo getAuthorizationInfo(int i) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.authorizationInfo_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setAuthorizationInfo(int i, AuthorizationInfo authorizationInfo) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                authorizationInfo.getClass();
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.set(i, authorizationInfo);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, authorizationInfo);
            }
            return this;
        }

        public Builder setAuthorizationInfo(int i, AuthorizationInfo.Builder builder) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.set(i, builder.m2849build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m2849build());
            }
            return this;
        }

        public Builder addAuthorizationInfo(AuthorizationInfo authorizationInfo) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                authorizationInfo.getClass();
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(authorizationInfo);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(authorizationInfo);
            }
            return this;
        }

        public Builder addAuthorizationInfo(int i, AuthorizationInfo authorizationInfo) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                authorizationInfo.getClass();
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(i, authorizationInfo);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, authorizationInfo);
            }
            return this;
        }

        public Builder addAuthorizationInfo(AuthorizationInfo.Builder builder) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(builder.m2849build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m2849build());
            }
            return this;
        }

        public Builder addAuthorizationInfo(int i, AuthorizationInfo.Builder builder) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(i, builder.m2849build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m2849build());
            }
            return this;
        }

        public Builder addAllAuthorizationInfo(Iterable<? extends AuthorizationInfo> iterable) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.authorizationInfo_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearAuthorizationInfo() {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.authorizationInfo_ = Collections.emptyList();
                this.bitField0_ &= -65;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeAuthorizationInfo(int i) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public AuthorizationInfo.Builder getAuthorizationInfoBuilder(int i) {
            return getAuthorizationInfoFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public AuthorizationInfoOrBuilder getAuthorizationInfoOrBuilder(int i) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.authorizationInfo_.get(i);
            }
            return (AuthorizationInfoOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public List<? extends AuthorizationInfoOrBuilder> getAuthorizationInfoOrBuilderList() {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.authorizationInfo_);
        }

        public AuthorizationInfo.Builder addAuthorizationInfoBuilder() {
            return getAuthorizationInfoFieldBuilder().addBuilder(AuthorizationInfo.getDefaultInstance());
        }

        public AuthorizationInfo.Builder addAuthorizationInfoBuilder(int i) {
            return getAuthorizationInfoFieldBuilder().addBuilder(i, AuthorizationInfo.getDefaultInstance());
        }

        public List<AuthorizationInfo.Builder> getAuthorizationInfoBuilderList() {
            return getAuthorizationInfoFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> getAuthorizationInfoFieldBuilder() {
            if (this.authorizationInfoBuilder_ == null) {
                this.authorizationInfoBuilder_ = new RepeatedFieldBuilderV3<>(this.authorizationInfo_, (this.bitField0_ & 64) != 0, getParentForChildren(), isClean());
                this.authorizationInfo_ = null;
            }
            return this.authorizationInfoBuilder_;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public RequestMetadata getRequestMetadata() {
            SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            RequestMetadata requestMetadata = this.requestMetadata_;
            return requestMetadata == null ? RequestMetadata.getDefaultInstance() : requestMetadata;
        }

        public Builder setRequestMetadata(RequestMetadata requestMetadata) {
            SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                requestMetadata.getClass();
                this.requestMetadata_ = requestMetadata;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(requestMetadata);
            }
            return this;
        }

        public Builder setRequestMetadata(RequestMetadata.Builder builder) {
            SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.requestMetadata_ = builder.m2895build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m2895build());
            }
            return this;
        }

        public Builder mergeRequestMetadata(RequestMetadata requestMetadata) {
            SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                RequestMetadata requestMetadata2 = this.requestMetadata_;
                if (requestMetadata2 != null) {
                    this.requestMetadata_ = RequestMetadata.newBuilder(requestMetadata2).mergeFrom(requestMetadata).m2897buildPartial();
                } else {
                    this.requestMetadata_ = requestMetadata;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(requestMetadata);
            }
            return this;
        }

        public Builder clearRequestMetadata() {
            if (this.requestMetadataBuilder_ == null) {
                this.requestMetadata_ = null;
                onChanged();
            } else {
                this.requestMetadata_ = null;
                this.requestMetadataBuilder_ = null;
            }
            return this;
        }

        public RequestMetadata.Builder getRequestMetadataBuilder() {
            onChanged();
            return getRequestMetadataFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public RequestMetadataOrBuilder getRequestMetadataOrBuilder() {
            SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (RequestMetadataOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            RequestMetadata requestMetadata = this.requestMetadata_;
            return requestMetadata == null ? RequestMetadata.getDefaultInstance() : requestMetadata;
        }

        private SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> getRequestMetadataFieldBuilder() {
            if (this.requestMetadataBuilder_ == null) {
                this.requestMetadataBuilder_ = new SingleFieldBuilderV3<>(getRequestMetadata(), getParentForChildren(), isClean());
                this.requestMetadata_ = null;
            }
            return this.requestMetadataBuilder_;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Struct getRequest() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Struct struct = this.request_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        public Builder setRequest(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 == null) {
                struct.getClass();
                this.request_ = struct;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(struct);
            }
            return this;
        }

        public Builder setRequest(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.request_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRequest(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.request_;
                if (struct2 != null) {
                    this.request_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.request_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearRequest() {
            if (this.requestBuilder_ == null) {
                this.request_ = null;
                onChanged();
            } else {
                this.request_ = null;
                this.requestBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getRequestBuilder() {
            onChanged();
            return getRequestFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public StructOrBuilder getRequestOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.request_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getRequestFieldBuilder() {
            if (this.requestBuilder_ == null) {
                this.requestBuilder_ = new SingleFieldBuilderV3<>(getRequest(), getParentForChildren(), isClean());
                this.request_ = null;
            }
            return this.requestBuilder_;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Struct getResponse() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Struct struct = this.response_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        public Builder setResponse(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                struct.getClass();
                this.response_ = struct;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(struct);
            }
            return this;
        }

        public Builder setResponse(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.response_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeResponse(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.response_;
                if (struct2 != null) {
                    this.response_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.response_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearResponse() {
            if (this.responseBuilder_ == null) {
                this.response_ = null;
                onChanged();
            } else {
                this.response_ = null;
                this.responseBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getResponseBuilder() {
            onChanged();
            return getResponseFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public StructOrBuilder getResponseOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.response_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getResponseFieldBuilder() {
            if (this.responseBuilder_ == null) {
                this.responseBuilder_ = new SingleFieldBuilderV3<>(getResponse(), getParentForChildren(), isClean());
                this.response_ = null;
            }
            return this.responseBuilder_;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Any getServiceData() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Any any = this.serviceData_;
            return any == null ? Any.getDefaultInstance() : any;
        }

        public Builder setServiceData(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 == null) {
                any.getClass();
                this.serviceData_ = any;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(any);
            }
            return this;
        }

        public Builder setServiceData(Any.Builder builder) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.serviceData_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeServiceData(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Any any2 = this.serviceData_;
                if (any2 != null) {
                    this.serviceData_ = Any.newBuilder(any2).mergeFrom(any).buildPartial();
                } else {
                    this.serviceData_ = any;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(any);
            }
            return this;
        }

        public Builder clearServiceData() {
            if (this.serviceDataBuilder_ == null) {
                this.serviceData_ = null;
                onChanged();
            } else {
                this.serviceData_ = null;
                this.serviceDataBuilder_ = null;
            }
            return this;
        }

        public Any.Builder getServiceDataBuilder() {
            onChanged();
            return getServiceDataFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public AnyOrBuilder getServiceDataOrBuilder() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Any any = this.serviceData_;
            return any == null ? Any.getDefaultInstance() : any;
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getServiceDataFieldBuilder() {
            if (this.serviceDataBuilder_ == null) {
                this.serviceDataBuilder_ = new SingleFieldBuilderV3<>(getServiceData(), getParentForChildren(), isClean());
                this.serviceData_ = null;
            }
            return this.serviceDataBuilder_;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m2791setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m2785mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
