package io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2;

import com.google.protobuf.AbstractMessageLite;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr;
import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.Permission;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.Principal;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public final class Policy extends GeneratedMessageV3 implements PolicyOrBuilder {
    public static final int CONDITION_FIELD_NUMBER = 3;
    public static final int PERMISSIONS_FIELD_NUMBER = 1;
    public static final int PRINCIPALS_FIELD_NUMBER = 2;
    private static final Policy DEFAULT_INSTANCE = new Policy();
    private static final Parser<Policy> PARSER = new AbstractParser<Policy>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.Policy.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Policy m27577parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Policy(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private Expr condition_;
    private byte memoizedIsInitialized;
    private List<Permission> permissions_;
    private List<Principal> principals_;

    private Policy(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Policy() {
        this.memoizedIsInitialized = (byte) -1;
        this.permissions_ = Collections.emptyList();
        this.principals_ = Collections.emptyList();
    }

    private Policy(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (tag != 0) {
                            if (tag == 10) {
                                if ((i & 1) == 0) {
                                    this.permissions_ = new ArrayList();
                                    i |= 1;
                                }
                                this.permissions_.add(codedInputStream.readMessage(Permission.parser(), extensionRegistryLite));
                            } else if (tag == 18) {
                                if ((i & 2) == 0) {
                                    this.principals_ = new ArrayList();
                                    i |= 2;
                                }
                                this.principals_.add(codedInputStream.readMessage(Principal.parser(), extensionRegistryLite));
                            } else if (tag == 26) {
                                Expr expr = this.condition_;
                                Expr.Builder builderM10731toBuilder = expr != null ? expr.m10731toBuilder() : null;
                                Expr expr2 = (Expr) codedInputStream.readMessage(Expr.parser(), extensionRegistryLite);
                                this.condition_ = expr2;
                                if (builderM10731toBuilder != null) {
                                    builderM10731toBuilder.mergeFrom(expr2);
                                    this.condition_ = builderM10731toBuilder.m10738buildPartial();
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
                if ((i & 1) != 0) {
                    this.permissions_ = Collections.unmodifiableList(this.permissions_);
                }
                if ((i & 2) != 0) {
                    this.principals_ = Collections.unmodifiableList(this.principals_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static Policy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Policy> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RbacProto.internal_static_envoy_config_rbac_v2_Policy_descriptor;
    }

    public static Policy parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Policy) PARSER.parseFrom(byteBuffer);
    }

    public static Policy parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Policy) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Policy parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Policy) PARSER.parseFrom(byteString);
    }

    public static Policy parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Policy) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Policy parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Policy) PARSER.parseFrom(bArr);
    }

    public static Policy parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Policy) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Policy parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Policy parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Policy parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Policy parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Policy parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Policy parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m27575toBuilder();
    }

    public static Builder newBuilder(Policy policy) {
        return DEFAULT_INSTANCE.m27575toBuilder().mergeFrom(policy);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Policy m27570getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Policy> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
    public List<Permission> getPermissionsList() {
        return this.permissions_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
    public List<? extends PermissionOrBuilder> getPermissionsOrBuilderList() {
        return this.permissions_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
    public List<Principal> getPrincipalsList() {
        return this.principals_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
    public List<? extends PrincipalOrBuilder> getPrincipalsOrBuilderList() {
        return this.principals_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
    public boolean hasCondition() {
        return this.condition_ != null;
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
        return new Policy();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RbacProto.internal_static_envoy_config_rbac_v2_Policy_fieldAccessorTable.ensureFieldAccessorsInitialized(Policy.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
    public int getPermissionsCount() {
        return this.permissions_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
    public Permission getPermissions(int i) {
        return this.permissions_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
    public PermissionOrBuilder getPermissionsOrBuilder(int i) {
        return this.permissions_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
    public int getPrincipalsCount() {
        return this.principals_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
    public Principal getPrincipals(int i) {
        return this.principals_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
    public PrincipalOrBuilder getPrincipalsOrBuilder(int i) {
        return this.principals_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
    public Expr getCondition() {
        Expr expr = this.condition_;
        return expr == null ? Expr.getDefaultInstance() : expr;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
    public ExprOrBuilder getConditionOrBuilder() {
        return getCondition();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.permissions_.size(); i++) {
            codedOutputStream.writeMessage(1, this.permissions_.get(i));
        }
        for (int i2 = 0; i2 < this.principals_.size(); i2++) {
            codedOutputStream.writeMessage(2, this.principals_.get(i2));
        }
        if (this.condition_ != null) {
            codedOutputStream.writeMessage(3, getCondition());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.permissions_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.permissions_.get(i2));
        }
        for (int i3 = 0; i3 < this.principals_.size(); i3++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, this.principals_.get(i3));
        }
        if (this.condition_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getCondition());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Policy)) {
            return super.equals(obj);
        }
        Policy policy = (Policy) obj;
        if (getPermissionsList().equals(policy.getPermissionsList()) && getPrincipalsList().equals(policy.getPrincipalsList()) && hasCondition() == policy.hasCondition()) {
            return (!hasCondition() || getCondition().equals(policy.getCondition())) && this.unknownFields.equals(policy.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getPermissionsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getPermissionsList().hashCode();
        }
        if (getPrincipalsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getPrincipalsList().hashCode();
        }
        if (hasCondition()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getCondition().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m27572newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m27575toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PolicyOrBuilder {
        private int bitField0_;
        private SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> conditionBuilder_;
        private Expr condition_;
        private RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> permissionsBuilder_;
        private List<Permission> permissions_;
        private RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> principalsBuilder_;
        private List<Principal> principals_;

        private Builder() {
            this.permissions_ = Collections.emptyList();
            this.principals_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.permissions_ = Collections.emptyList();
            this.principals_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RbacProto.internal_static_envoy_config_rbac_v2_Policy_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
        public boolean hasCondition() {
            return (this.conditionBuilder_ == null && this.condition_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RbacProto.internal_static_envoy_config_rbac_v2_Policy_fieldAccessorTable.ensureFieldAccessorsInitialized(Policy.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (Policy.alwaysUseFieldBuilders) {
                getPermissionsFieldBuilder();
                getPrincipalsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27586clear() {
            super.clear();
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.permissions_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV32 = this.principalsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.principals_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            if (this.conditionBuilder_ == null) {
                this.condition_ = null;
            } else {
                this.condition_ = null;
                this.conditionBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return RbacProto.internal_static_envoy_config_rbac_v2_Policy_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Policy m27599getDefaultInstanceForType() {
            return Policy.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Policy m27580build() throws UninitializedMessageException {
            Policy policyM27582buildPartial = m27582buildPartial();
            if (policyM27582buildPartial.isInitialized()) {
                return policyM27582buildPartial;
            }
            throw newUninitializedMessageException(policyM27582buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Policy m27582buildPartial() {
            Policy policy = new Policy(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.permissions_ = Collections.unmodifiableList(this.permissions_);
                    this.bitField0_ &= -2;
                }
                policy.permissions_ = this.permissions_;
            } else {
                policy.permissions_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV32 = this.principalsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 2) != 0) {
                    this.principals_ = Collections.unmodifiableList(this.principals_);
                    this.bitField0_ &= -3;
                }
                policy.principals_ = this.principals_;
            } else {
                policy.principals_ = repeatedFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> singleFieldBuilderV3 = this.conditionBuilder_;
            if (singleFieldBuilderV3 == null) {
                policy.condition_ = this.condition_;
            } else {
                policy.condition_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return policy;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27598clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27610setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27588clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27591clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27612setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27578addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27603mergeFrom(Message message) {
            if (message instanceof Policy) {
                return mergeFrom((Policy) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Policy policy) {
            if (policy == Policy.getDefaultInstance()) {
                return this;
            }
            if (this.permissionsBuilder_ == null) {
                if (!policy.permissions_.isEmpty()) {
                    if (this.permissions_.isEmpty()) {
                        this.permissions_ = policy.permissions_;
                        this.bitField0_ &= -2;
                    } else {
                        ensurePermissionsIsMutable();
                        this.permissions_.addAll(policy.permissions_);
                    }
                    onChanged();
                }
            } else if (!policy.permissions_.isEmpty()) {
                if (!this.permissionsBuilder_.isEmpty()) {
                    this.permissionsBuilder_.addAllMessages(policy.permissions_);
                } else {
                    this.permissionsBuilder_.dispose();
                    this.permissionsBuilder_ = null;
                    this.permissions_ = policy.permissions_;
                    this.bitField0_ &= -2;
                    this.permissionsBuilder_ = Policy.alwaysUseFieldBuilders ? getPermissionsFieldBuilder() : null;
                }
            }
            if (this.principalsBuilder_ == null) {
                if (!policy.principals_.isEmpty()) {
                    if (this.principals_.isEmpty()) {
                        this.principals_ = policy.principals_;
                        this.bitField0_ &= -3;
                    } else {
                        ensurePrincipalsIsMutable();
                        this.principals_.addAll(policy.principals_);
                    }
                    onChanged();
                }
            } else if (!policy.principals_.isEmpty()) {
                if (!this.principalsBuilder_.isEmpty()) {
                    this.principalsBuilder_.addAllMessages(policy.principals_);
                } else {
                    this.principalsBuilder_.dispose();
                    this.principalsBuilder_ = null;
                    this.principals_ = policy.principals_;
                    this.bitField0_ &= -3;
                    this.principalsBuilder_ = Policy.alwaysUseFieldBuilders ? getPrincipalsFieldBuilder() : null;
                }
            }
            if (policy.hasCondition()) {
                mergeCondition(policy.getCondition());
            }
            m27608mergeUnknownFields(policy.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.Policy.Builder m27604mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.Policy.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.Policy r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.Policy) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.Policy r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.Policy) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.Policy.Builder.m27604mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.Policy$Builder");
        }

        private void ensurePermissionsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.permissions_ = new ArrayList(this.permissions_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
        public List<Permission> getPermissionsList() {
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.permissions_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
        public int getPermissionsCount() {
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.permissions_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
        public Permission getPermissions(int i) {
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.permissions_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setPermissions(int i, Permission permission) {
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                permission.getClass();
                ensurePermissionsIsMutable();
                this.permissions_.set(i, permission);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, permission);
            }
            return this;
        }

        public Builder setPermissions(int i, Permission.Builder builder) {
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePermissionsIsMutable();
                this.permissions_.set(i, builder.m27488build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m27488build());
            }
            return this;
        }

        public Builder addPermissions(Permission permission) {
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                permission.getClass();
                ensurePermissionsIsMutable();
                this.permissions_.add(permission);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(permission);
            }
            return this;
        }

        public Builder addPermissions(int i, Permission permission) {
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                permission.getClass();
                ensurePermissionsIsMutable();
                this.permissions_.add(i, permission);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, permission);
            }
            return this;
        }

        public Builder addPermissions(Permission.Builder builder) {
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePermissionsIsMutable();
                this.permissions_.add(builder.m27488build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m27488build());
            }
            return this;
        }

        public Builder addPermissions(int i, Permission.Builder builder) {
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePermissionsIsMutable();
                this.permissions_.add(i, builder.m27488build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m27488build());
            }
            return this;
        }

        public Builder addAllPermissions(Iterable<? extends Permission> iterable) {
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePermissionsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.permissions_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearPermissions() {
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.permissions_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removePermissions(int i) {
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePermissionsIsMutable();
                this.permissions_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Permission.Builder getPermissionsBuilder(int i) {
            return getPermissionsFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
        public PermissionOrBuilder getPermissionsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.permissions_.get(i);
            }
            return (PermissionOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
        public List<? extends PermissionOrBuilder> getPermissionsOrBuilderList() {
            RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> repeatedFieldBuilderV3 = this.permissionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.permissions_);
        }

        public Permission.Builder addPermissionsBuilder() {
            return getPermissionsFieldBuilder().addBuilder(Permission.getDefaultInstance());
        }

        public Permission.Builder addPermissionsBuilder(int i) {
            return getPermissionsFieldBuilder().addBuilder(i, Permission.getDefaultInstance());
        }

        public List<Permission.Builder> getPermissionsBuilderList() {
            return getPermissionsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Permission, Permission.Builder, PermissionOrBuilder> getPermissionsFieldBuilder() {
            if (this.permissionsBuilder_ == null) {
                this.permissionsBuilder_ = new RepeatedFieldBuilderV3<>(this.permissions_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.permissions_ = null;
            }
            return this.permissionsBuilder_;
        }

        private void ensurePrincipalsIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.principals_ = new ArrayList(this.principals_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
        public List<Principal> getPrincipalsList() {
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV3 = this.principalsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.principals_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
        public int getPrincipalsCount() {
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV3 = this.principalsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.principals_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
        public Principal getPrincipals(int i) {
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV3 = this.principalsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.principals_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setPrincipals(int i, Principal principal) {
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV3 = this.principalsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                principal.getClass();
                ensurePrincipalsIsMutable();
                this.principals_.set(i, principal);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, principal);
            }
            return this;
        }

        public Builder setPrincipals(int i, Principal.Builder builder) {
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV3 = this.principalsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePrincipalsIsMutable();
                this.principals_.set(i, builder.m27672build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m27672build());
            }
            return this;
        }

        public Builder addPrincipals(Principal principal) {
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV3 = this.principalsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                principal.getClass();
                ensurePrincipalsIsMutable();
                this.principals_.add(principal);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(principal);
            }
            return this;
        }

        public Builder addPrincipals(int i, Principal principal) {
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV3 = this.principalsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                principal.getClass();
                ensurePrincipalsIsMutable();
                this.principals_.add(i, principal);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, principal);
            }
            return this;
        }

        public Builder addPrincipals(Principal.Builder builder) {
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV3 = this.principalsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePrincipalsIsMutable();
                this.principals_.add(builder.m27672build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m27672build());
            }
            return this;
        }

        public Builder addPrincipals(int i, Principal.Builder builder) {
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV3 = this.principalsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePrincipalsIsMutable();
                this.principals_.add(i, builder.m27672build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m27672build());
            }
            return this;
        }

        public Builder addAllPrincipals(Iterable<? extends Principal> iterable) {
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV3 = this.principalsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePrincipalsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.principals_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearPrincipals() {
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV3 = this.principalsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.principals_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removePrincipals(int i) {
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV3 = this.principalsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePrincipalsIsMutable();
                this.principals_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Principal.Builder getPrincipalsBuilder(int i) {
            return getPrincipalsFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
        public PrincipalOrBuilder getPrincipalsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV3 = this.principalsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.principals_.get(i);
            }
            return (PrincipalOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
        public List<? extends PrincipalOrBuilder> getPrincipalsOrBuilderList() {
            RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> repeatedFieldBuilderV3 = this.principalsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.principals_);
        }

        public Principal.Builder addPrincipalsBuilder() {
            return getPrincipalsFieldBuilder().addBuilder(Principal.getDefaultInstance());
        }

        public Principal.Builder addPrincipalsBuilder(int i) {
            return getPrincipalsFieldBuilder().addBuilder(i, Principal.getDefaultInstance());
        }

        public List<Principal.Builder> getPrincipalsBuilderList() {
            return getPrincipalsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Principal, Principal.Builder, PrincipalOrBuilder> getPrincipalsFieldBuilder() {
            if (this.principalsBuilder_ == null) {
                this.principalsBuilder_ = new RepeatedFieldBuilderV3<>(this.principals_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.principals_ = null;
            }
            return this.principalsBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
        public Expr getCondition() {
            SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> singleFieldBuilderV3 = this.conditionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Expr expr = this.condition_;
            return expr == null ? Expr.getDefaultInstance() : expr;
        }

        public Builder setCondition(Expr expr) {
            SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> singleFieldBuilderV3 = this.conditionBuilder_;
            if (singleFieldBuilderV3 == null) {
                expr.getClass();
                this.condition_ = expr;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(expr);
            }
            return this;
        }

        public Builder setCondition(Expr.Builder builder) {
            SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> singleFieldBuilderV3 = this.conditionBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.condition_ = builder.m10736build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m10736build());
            }
            return this;
        }

        public Builder mergeCondition(Expr expr) {
            SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> singleFieldBuilderV3 = this.conditionBuilder_;
            if (singleFieldBuilderV3 == null) {
                Expr expr2 = this.condition_;
                if (expr2 != null) {
                    this.condition_ = Expr.newBuilder(expr2).mergeFrom(expr).m10738buildPartial();
                } else {
                    this.condition_ = expr;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(expr);
            }
            return this;
        }

        public Builder clearCondition() {
            if (this.conditionBuilder_ == null) {
                this.condition_ = null;
                onChanged();
            } else {
                this.condition_ = null;
                this.conditionBuilder_ = null;
            }
            return this;
        }

        public Expr.Builder getConditionBuilder() {
            onChanged();
            return getConditionFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.PolicyOrBuilder
        public ExprOrBuilder getConditionOrBuilder() {
            SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> singleFieldBuilderV3 = this.conditionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ExprOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Expr expr = this.condition_;
            return expr == null ? Expr.getDefaultInstance() : expr;
        }

        private SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> getConditionFieldBuilder() {
            if (this.conditionBuilder_ == null) {
                this.conditionBuilder_ = new SingleFieldBuilderV3<>(getCondition(), getParentForChildren(), isClean());
                this.condition_ = null;
            }
            return this.conditionBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m27614setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m27608mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
