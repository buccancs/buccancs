package io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3;

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
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public final class LoadBalancingPolicy extends GeneratedMessageV3 implements LoadBalancingPolicyOrBuilder {
    public static final int POLICIES_FIELD_NUMBER = 1;
    private static final LoadBalancingPolicy DEFAULT_INSTANCE = new LoadBalancingPolicy();
    private static final Parser<LoadBalancingPolicy> PARSER = new AbstractParser<LoadBalancingPolicy>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public LoadBalancingPolicy m21154parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new LoadBalancingPolicy(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<Policy> policies_;

    private LoadBalancingPolicy(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private LoadBalancingPolicy() {
        this.memoizedIsInitialized = (byte) -1;
        this.policies_ = Collections.emptyList();
    }

    private LoadBalancingPolicy(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (tag == 10) {
                            if (!(z2 & true)) {
                                this.policies_ = new ArrayList();
                                z2 |= true;
                            }
                            this.policies_.add(codedInputStream.readMessage(Policy.parser(), extensionRegistryLite));
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
                if (z2 & true) {
                    this.policies_ = Collections.unmodifiableList(this.policies_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static LoadBalancingPolicy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LoadBalancingPolicy> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ClusterProto.internal_static_envoy_config_cluster_v3_LoadBalancingPolicy_descriptor;
    }

    public static LoadBalancingPolicy parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (LoadBalancingPolicy) PARSER.parseFrom(byteBuffer);
    }

    public static LoadBalancingPolicy parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LoadBalancingPolicy) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static LoadBalancingPolicy parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (LoadBalancingPolicy) PARSER.parseFrom(byteString);
    }

    public static LoadBalancingPolicy parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LoadBalancingPolicy) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static LoadBalancingPolicy parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (LoadBalancingPolicy) PARSER.parseFrom(bArr);
    }

    public static LoadBalancingPolicy parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LoadBalancingPolicy) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static LoadBalancingPolicy parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static LoadBalancingPolicy parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LoadBalancingPolicy parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static LoadBalancingPolicy parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LoadBalancingPolicy parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static LoadBalancingPolicy parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m21152toBuilder();
    }

    public static Builder newBuilder(LoadBalancingPolicy loadBalancingPolicy) {
        return DEFAULT_INSTANCE.m21152toBuilder().mergeFrom(loadBalancingPolicy);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public LoadBalancingPolicy m21147getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<LoadBalancingPolicy> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicyOrBuilder
    public List<Policy> getPoliciesList() {
        return this.policies_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicyOrBuilder
    public List<? extends PolicyOrBuilder> getPoliciesOrBuilderList() {
        return this.policies_;
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
        return new LoadBalancingPolicy();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ClusterProto.internal_static_envoy_config_cluster_v3_LoadBalancingPolicy_fieldAccessorTable.ensureFieldAccessorsInitialized(LoadBalancingPolicy.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicyOrBuilder
    public int getPoliciesCount() {
        return this.policies_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicyOrBuilder
    public Policy getPolicies(int i) {
        return this.policies_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicyOrBuilder
    public PolicyOrBuilder getPoliciesOrBuilder(int i) {
        return this.policies_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.policies_.size(); i++) {
            codedOutputStream.writeMessage(1, this.policies_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.policies_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.policies_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LoadBalancingPolicy)) {
            return super.equals(obj);
        }
        LoadBalancingPolicy loadBalancingPolicy = (LoadBalancingPolicy) obj;
        return getPoliciesList().equals(loadBalancingPolicy.getPoliciesList()) && this.unknownFields.equals(loadBalancingPolicy.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getPoliciesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getPoliciesList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m21149newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m21152toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public interface PolicyOrBuilder extends MessageOrBuilder {
        String getName();

        ByteString getNameBytes();

        Any getTypedConfig();

        AnyOrBuilder getTypedConfigOrBuilder();

        boolean hasTypedConfig();
    }

    public static final class Policy extends GeneratedMessageV3 implements PolicyOrBuilder {
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int TYPED_CONFIG_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private static final Policy DEFAULT_INSTANCE = new Policy();
        private static final Parser<Policy> PARSER = new AbstractParser<Policy>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.Policy.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Policy m21200parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Policy(codedInputStream, extensionRegistryLite);
            }
        };
        private byte memoizedIsInitialized;
        private volatile Object name_;
        private Any typedConfig_;

        private Policy(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Policy() {
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
        }

        private Policy(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            } else if (tag == 26) {
                                Any any = this.typedConfig_;
                                Any.Builder builder = any != null ? any.toBuilder() : null;
                                Any message = codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                                this.typedConfig_ = message;
                                if (builder != null) {
                                    builder.mergeFrom(message);
                                    this.typedConfig_ = builder.buildPartial();
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

        public static Policy getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Policy> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ClusterProto.internal_static_envoy_config_cluster_v3_LoadBalancingPolicy_Policy_descriptor;
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
            return DEFAULT_INSTANCE.m21198toBuilder();
        }

        public static Builder newBuilder(Policy policy) {
            return DEFAULT_INSTANCE.m21198toBuilder().mergeFrom(policy);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Policy m21193getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Policy> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.PolicyOrBuilder
        public boolean hasTypedConfig() {
            return this.typedConfig_ != null;
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
            return ClusterProto.internal_static_envoy_config_cluster_v3_LoadBalancingPolicy_Policy_fieldAccessorTable.ensureFieldAccessorsInitialized(Policy.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.PolicyOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.PolicyOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.PolicyOrBuilder
        public Any getTypedConfig() {
            Any any = this.typedConfig_;
            return any == null ? Any.getDefaultInstance() : any;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.PolicyOrBuilder
        public AnyOrBuilder getTypedConfigOrBuilder() {
            return getTypedConfig();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
            }
            if (this.typedConfig_ != null) {
                codedOutputStream.writeMessage(3, getTypedConfig());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
            if (this.typedConfig_ != null) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(3, getTypedConfig());
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
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
            if (getName().equals(policy.getName()) && hasTypedConfig() == policy.hasTypedConfig()) {
                return (!hasTypedConfig() || getTypedConfig().equals(policy.getTypedConfig())) && this.unknownFields.equals(policy.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
            if (hasTypedConfig()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getTypedConfig().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21195newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21198toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PolicyOrBuilder {
            private Object name_;
            private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> typedConfigBuilder_;
            private Any typedConfig_;

            private Builder() {
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ClusterProto.internal_static_envoy_config_cluster_v3_LoadBalancingPolicy_Policy_descriptor;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.PolicyOrBuilder
            public boolean hasTypedConfig() {
                return (this.typedConfigBuilder_ == null && this.typedConfig_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ClusterProto.internal_static_envoy_config_cluster_v3_LoadBalancingPolicy_Policy_fieldAccessorTable.ensureFieldAccessorsInitialized(Policy.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Policy.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m21209clear() {
                super.clear();
                this.name_ = "";
                if (this.typedConfigBuilder_ == null) {
                    this.typedConfig_ = null;
                } else {
                    this.typedConfig_ = null;
                    this.typedConfigBuilder_ = null;
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ClusterProto.internal_static_envoy_config_cluster_v3_LoadBalancingPolicy_Policy_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Policy m21222getDefaultInstanceForType() {
                return Policy.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Policy m21203build() throws UninitializedMessageException {
                Policy policyM21205buildPartial = m21205buildPartial();
                if (policyM21205buildPartial.isInitialized()) {
                    return policyM21205buildPartial;
                }
                throw newUninitializedMessageException(policyM21205buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Policy m21205buildPartial() {
                Policy policy = new Policy(this);
                policy.name_ = this.name_;
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    policy.typedConfig_ = this.typedConfig_;
                } else {
                    policy.typedConfig_ = singleFieldBuilderV3.build();
                }
                onBuilt();
                return policy;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m21221clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m21233setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m21211clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m21214clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m21235setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m21201addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m21226mergeFrom(Message message) {
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
                if (!policy.getName().isEmpty()) {
                    this.name_ = policy.name_;
                    onChanged();
                }
                if (policy.hasTypedConfig()) {
                    mergeTypedConfig(policy.getTypedConfig());
                }
                m21231mergeUnknownFields(policy.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.Policy.Builder m21227mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.Policy.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy$Policy r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.Policy) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy$Policy r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.Policy) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.Policy.Builder.m21227mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy$Policy$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.PolicyOrBuilder
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

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.PolicyOrBuilder
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
                Policy.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = Policy.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.PolicyOrBuilder
            public Any getTypedConfig() {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Any any = this.typedConfig_;
                return any == null ? Any.getDefaultInstance() : any;
            }

            public Builder setTypedConfig(Any any) {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    any.getClass();
                    this.typedConfig_ = any;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(any);
                }
                return this;
            }

            public Builder setTypedConfig(Any.Builder builder) {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.typedConfig_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeTypedConfig(Any any) {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Any any2 = this.typedConfig_;
                    if (any2 != null) {
                        this.typedConfig_ = Any.newBuilder(any2).mergeFrom(any).buildPartial();
                    } else {
                        this.typedConfig_ = any;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(any);
                }
                return this;
            }

            public Builder clearTypedConfig() {
                if (this.typedConfigBuilder_ == null) {
                    this.typedConfig_ = null;
                    onChanged();
                } else {
                    this.typedConfig_ = null;
                    this.typedConfigBuilder_ = null;
                }
                return this;
            }

            public Any.Builder getTypedConfigBuilder() {
                onChanged();
                return getTypedConfigFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.PolicyOrBuilder
            public AnyOrBuilder getTypedConfigOrBuilder() {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Any any = this.typedConfig_;
                return any == null ? Any.getDefaultInstance() : any;
            }

            private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getTypedConfigFieldBuilder() {
                if (this.typedConfigBuilder_ == null) {
                    this.typedConfigBuilder_ = new SingleFieldBuilderV3<>(getTypedConfig(), getParentForChildren(), isClean());
                    this.typedConfig_ = null;
                }
                return this.typedConfigBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m21237setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m21231mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LoadBalancingPolicyOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> policiesBuilder_;
        private List<Policy> policies_;

        private Builder() {
            this.policies_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.policies_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ClusterProto.internal_static_envoy_config_cluster_v3_LoadBalancingPolicy_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ClusterProto.internal_static_envoy_config_cluster_v3_LoadBalancingPolicy_fieldAccessorTable.ensureFieldAccessorsInitialized(LoadBalancingPolicy.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (LoadBalancingPolicy.alwaysUseFieldBuilders) {
                getPoliciesFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21163clear() {
            super.clear();
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.policies_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ClusterProto.internal_static_envoy_config_cluster_v3_LoadBalancingPolicy_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LoadBalancingPolicy m21176getDefaultInstanceForType() {
            return LoadBalancingPolicy.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LoadBalancingPolicy m21157build() throws UninitializedMessageException {
            LoadBalancingPolicy loadBalancingPolicyM21159buildPartial = m21159buildPartial();
            if (loadBalancingPolicyM21159buildPartial.isInitialized()) {
                return loadBalancingPolicyM21159buildPartial;
            }
            throw newUninitializedMessageException(loadBalancingPolicyM21159buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LoadBalancingPolicy m21159buildPartial() {
            LoadBalancingPolicy loadBalancingPolicy = new LoadBalancingPolicy(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.policies_ = Collections.unmodifiableList(this.policies_);
                    this.bitField0_ &= -2;
                }
                loadBalancingPolicy.policies_ = this.policies_;
            } else {
                loadBalancingPolicy.policies_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return loadBalancingPolicy;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21175clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21187setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21165clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21168clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21189setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21155addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m21180mergeFrom(Message message) {
            if (message instanceof LoadBalancingPolicy) {
                return mergeFrom((LoadBalancingPolicy) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(LoadBalancingPolicy loadBalancingPolicy) {
            if (loadBalancingPolicy == LoadBalancingPolicy.getDefaultInstance()) {
                return this;
            }
            if (this.policiesBuilder_ == null) {
                if (!loadBalancingPolicy.policies_.isEmpty()) {
                    if (this.policies_.isEmpty()) {
                        this.policies_ = loadBalancingPolicy.policies_;
                        this.bitField0_ &= -2;
                    } else {
                        ensurePoliciesIsMutable();
                        this.policies_.addAll(loadBalancingPolicy.policies_);
                    }
                    onChanged();
                }
            } else if (!loadBalancingPolicy.policies_.isEmpty()) {
                if (!this.policiesBuilder_.isEmpty()) {
                    this.policiesBuilder_.addAllMessages(loadBalancingPolicy.policies_);
                } else {
                    this.policiesBuilder_.dispose();
                    this.policiesBuilder_ = null;
                    this.policies_ = loadBalancingPolicy.policies_;
                    this.bitField0_ &= -2;
                    this.policiesBuilder_ = LoadBalancingPolicy.alwaysUseFieldBuilders ? getPoliciesFieldBuilder() : null;
                }
            }
            m21185mergeUnknownFields(loadBalancingPolicy.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.Builder m21181mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.access$1700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy.Builder.m21181mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy$Builder");
        }

        private void ensurePoliciesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.policies_ = new ArrayList(this.policies_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicyOrBuilder
        public List<Policy> getPoliciesList() {
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.policies_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicyOrBuilder
        public int getPoliciesCount() {
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.policies_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicyOrBuilder
        public Policy getPolicies(int i) {
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.policies_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setPolicies(int i, Policy policy) {
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                policy.getClass();
                ensurePoliciesIsMutable();
                this.policies_.set(i, policy);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, policy);
            }
            return this;
        }

        public Builder setPolicies(int i, Policy.Builder builder) {
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePoliciesIsMutable();
                this.policies_.set(i, builder.m21203build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m21203build());
            }
            return this;
        }

        public Builder addPolicies(Policy policy) {
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                policy.getClass();
                ensurePoliciesIsMutable();
                this.policies_.add(policy);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(policy);
            }
            return this;
        }

        public Builder addPolicies(int i, Policy policy) {
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                policy.getClass();
                ensurePoliciesIsMutable();
                this.policies_.add(i, policy);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, policy);
            }
            return this;
        }

        public Builder addPolicies(Policy.Builder builder) {
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePoliciesIsMutable();
                this.policies_.add(builder.m21203build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m21203build());
            }
            return this;
        }

        public Builder addPolicies(int i, Policy.Builder builder) {
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePoliciesIsMutable();
                this.policies_.add(i, builder.m21203build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m21203build());
            }
            return this;
        }

        public Builder addAllPolicies(Iterable<? extends Policy> iterable) {
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePoliciesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.policies_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearPolicies() {
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.policies_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removePolicies(int i) {
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePoliciesIsMutable();
                this.policies_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Policy.Builder getPoliciesBuilder(int i) {
            return getPoliciesFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicyOrBuilder
        public PolicyOrBuilder getPoliciesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.policies_.get(i);
            }
            return (PolicyOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicyOrBuilder
        public List<? extends PolicyOrBuilder> getPoliciesOrBuilderList() {
            RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> repeatedFieldBuilderV3 = this.policiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.policies_);
        }

        public Policy.Builder addPoliciesBuilder() {
            return getPoliciesFieldBuilder().addBuilder(Policy.getDefaultInstance());
        }

        public Policy.Builder addPoliciesBuilder(int i) {
            return getPoliciesFieldBuilder().addBuilder(i, Policy.getDefaultInstance());
        }

        public List<Policy.Builder> getPoliciesBuilderList() {
            return getPoliciesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> getPoliciesFieldBuilder() {
            if (this.policiesBuilder_ == null) {
                this.policiesBuilder_ = new RepeatedFieldBuilderV3<>(this.policies_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.policies_ = null;
            }
            return this.policiesBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m21191setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m21185mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
