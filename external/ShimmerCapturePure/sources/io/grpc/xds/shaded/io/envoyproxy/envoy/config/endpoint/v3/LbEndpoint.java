package io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3;

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
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthStatus;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Metadata;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.Endpoint;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class LbEndpoint extends GeneratedMessageV3 implements LbEndpointOrBuilder {
    public static final int ENDPOINT_FIELD_NUMBER = 1;
    public static final int ENDPOINT_NAME_FIELD_NUMBER = 5;
    public static final int HEALTH_STATUS_FIELD_NUMBER = 2;
    public static final int LOAD_BALANCING_WEIGHT_FIELD_NUMBER = 4;
    public static final int METADATA_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final LbEndpoint DEFAULT_INSTANCE = new LbEndpoint();
    private static final Parser<LbEndpoint> PARSER = new AbstractParser<LbEndpoint>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpoint.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public LbEndpoint m24895parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new LbEndpoint(codedInputStream, extensionRegistryLite);
        }
    };
    private int healthStatus_;
    private int hostIdentifierCase_;
    private Object hostIdentifier_;
    private UInt32Value loadBalancingWeight_;
    private byte memoizedIsInitialized;
    private Metadata metadata_;

    private LbEndpoint(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.hostIdentifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private LbEndpoint() {
        this.hostIdentifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.healthStatus_ = 0;
    }

    private LbEndpoint(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        UInt32Value.Builder builder;
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
                                builder = this.hostIdentifierCase_ == 1 ? ((Endpoint) this.hostIdentifier_).m24755toBuilder() : null;
                                MessageLite message = codedInputStream.readMessage(Endpoint.parser(), extensionRegistryLite);
                                this.hostIdentifier_ = message;
                                if (builder != null) {
                                    builder.mergeFrom((Endpoint) message);
                                    this.hostIdentifier_ = builder.m24762buildPartial();
                                }
                                this.hostIdentifierCase_ = 1;
                            } else if (tag == 16) {
                                this.healthStatus_ = codedInputStream.readEnum();
                            } else if (tag == 26) {
                                Metadata metadata = this.metadata_;
                                builder = metadata != null ? metadata.m23598toBuilder() : null;
                                Metadata metadata2 = (Metadata) codedInputStream.readMessage(Metadata.parser(), extensionRegistryLite);
                                this.metadata_ = metadata2;
                                if (builder != null) {
                                    builder.mergeFrom(metadata2);
                                    this.metadata_ = builder.m23605buildPartial();
                                }
                            } else if (tag == 34) {
                                UInt32Value uInt32Value = this.loadBalancingWeight_;
                                builder = uInt32Value != null ? uInt32Value.toBuilder() : null;
                                UInt32Value message2 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                                this.loadBalancingWeight_ = message2;
                                if (builder != null) {
                                    builder.mergeFrom(message2);
                                    this.loadBalancingWeight_ = builder.buildPartial();
                                }
                            } else if (tag == 42) {
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.hostIdentifierCase_ = 5;
                                this.hostIdentifier_ = stringRequireUtf8;
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

    public static LbEndpoint getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LbEndpoint> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return EndpointComponentsProto.internal_static_envoy_config_endpoint_v3_LbEndpoint_descriptor;
    }

    public static LbEndpoint parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (LbEndpoint) PARSER.parseFrom(byteBuffer);
    }

    public static LbEndpoint parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LbEndpoint) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static LbEndpoint parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (LbEndpoint) PARSER.parseFrom(byteString);
    }

    public static LbEndpoint parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LbEndpoint) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static LbEndpoint parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (LbEndpoint) PARSER.parseFrom(bArr);
    }

    public static LbEndpoint parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LbEndpoint) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static LbEndpoint parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static LbEndpoint parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LbEndpoint parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static LbEndpoint parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LbEndpoint parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static LbEndpoint parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m24893toBuilder();
    }

    public static Builder newBuilder(LbEndpoint lbEndpoint) {
        return DEFAULT_INSTANCE.m24893toBuilder().mergeFrom(lbEndpoint);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public LbEndpoint m24888getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
    public int getHealthStatusValue() {
        return this.healthStatus_;
    }

    public Parser<LbEndpoint> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
    public boolean hasEndpoint() {
        return this.hostIdentifierCase_ == 1;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
    public boolean hasLoadBalancingWeight() {
        return this.loadBalancingWeight_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
    public boolean hasMetadata() {
        return this.metadata_ != null;
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
        return new LbEndpoint();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EndpointComponentsProto.internal_static_envoy_config_endpoint_v3_LbEndpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(LbEndpoint.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
    public HostIdentifierCase getHostIdentifierCase() {
        return HostIdentifierCase.forNumber(this.hostIdentifierCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
    public Endpoint getEndpoint() {
        if (this.hostIdentifierCase_ == 1) {
            return (Endpoint) this.hostIdentifier_;
        }
        return Endpoint.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
    public EndpointOrBuilder getEndpointOrBuilder() {
        if (this.hostIdentifierCase_ == 1) {
            return (Endpoint) this.hostIdentifier_;
        }
        return Endpoint.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
    public String getEndpointName() {
        String str = this.hostIdentifierCase_ == 5 ? this.hostIdentifier_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.hostIdentifierCase_ == 5) {
            this.hostIdentifier_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
    public ByteString getEndpointNameBytes() {
        String str = this.hostIdentifierCase_ == 5 ? this.hostIdentifier_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.hostIdentifierCase_ == 5) {
                this.hostIdentifier_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
    public HealthStatus getHealthStatus() {
        HealthStatus healthStatusValueOf = HealthStatus.valueOf(this.healthStatus_);
        return healthStatusValueOf == null ? HealthStatus.UNRECOGNIZED : healthStatusValueOf;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
    public Metadata getMetadata() {
        Metadata metadata = this.metadata_;
        return metadata == null ? Metadata.getDefaultInstance() : metadata;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
    public MetadataOrBuilder getMetadataOrBuilder() {
        return getMetadata();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
    public UInt32Value getLoadBalancingWeight() {
        UInt32Value uInt32Value = this.loadBalancingWeight_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
    public UInt32ValueOrBuilder getLoadBalancingWeightOrBuilder() {
        return getLoadBalancingWeight();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.hostIdentifierCase_ == 1) {
            codedOutputStream.writeMessage(1, (Endpoint) this.hostIdentifier_);
        }
        if (this.healthStatus_ != HealthStatus.UNKNOWN.getNumber()) {
            codedOutputStream.writeEnum(2, this.healthStatus_);
        }
        if (this.metadata_ != null) {
            codedOutputStream.writeMessage(3, getMetadata());
        }
        if (this.loadBalancingWeight_ != null) {
            codedOutputStream.writeMessage(4, getLoadBalancingWeight());
        }
        if (this.hostIdentifierCase_ == 5) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.hostIdentifier_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.hostIdentifierCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (Endpoint) this.hostIdentifier_) : 0;
        if (this.healthStatus_ != HealthStatus.UNKNOWN.getNumber()) {
            iComputeMessageSize += CodedOutputStream.computeEnumSize(2, this.healthStatus_);
        }
        if (this.metadata_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getMetadata());
        }
        if (this.loadBalancingWeight_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(4, getLoadBalancingWeight());
        }
        if (this.hostIdentifierCase_ == 5) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(5, this.hostIdentifier_);
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LbEndpoint)) {
            return super.equals(obj);
        }
        LbEndpoint lbEndpoint = (LbEndpoint) obj;
        if (this.healthStatus_ != lbEndpoint.healthStatus_ || hasMetadata() != lbEndpoint.hasMetadata()) {
            return false;
        }
        if ((hasMetadata() && !getMetadata().equals(lbEndpoint.getMetadata())) || hasLoadBalancingWeight() != lbEndpoint.hasLoadBalancingWeight()) {
            return false;
        }
        if ((hasLoadBalancingWeight() && !getLoadBalancingWeight().equals(lbEndpoint.getLoadBalancingWeight())) || !getHostIdentifierCase().equals(lbEndpoint.getHostIdentifierCase())) {
            return false;
        }
        int i = this.hostIdentifierCase_;
        if (i == 1) {
            if (!getEndpoint().equals(lbEndpoint.getEndpoint())) {
                return false;
            }
        } else if (i == 5 && !getEndpointName().equals(lbEndpoint.getEndpointName())) {
            return false;
        }
        return this.unknownFields.equals(lbEndpoint.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 2) * 53) + this.healthStatus_;
        if (hasMetadata()) {
            iHashCode2 = (((iHashCode2 * 37) + 3) * 53) + getMetadata().hashCode();
        }
        if (hasLoadBalancingWeight()) {
            iHashCode2 = (((iHashCode2 * 37) + 4) * 53) + getLoadBalancingWeight().hashCode();
        }
        int i2 = this.hostIdentifierCase_;
        if (i2 == 1) {
            i = ((iHashCode2 * 37) + 1) * 53;
            iHashCode = getEndpoint().hashCode();
        } else {
            if (i2 == 5) {
                i = ((iHashCode2 * 37) + 5) * 53;
                iHashCode = getEndpointName().hashCode();
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
    public Builder m24890newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m24893toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum HostIdentifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        ENDPOINT(1),
        ENDPOINT_NAME(5),
        HOSTIDENTIFIER_NOT_SET(0);

        private final int value;

        HostIdentifierCase(int i) {
            this.value = i;
        }

        public static HostIdentifierCase forNumber(int i) {
            if (i == 0) {
                return HOSTIDENTIFIER_NOT_SET;
            }
            if (i == 1) {
                return ENDPOINT;
            }
            if (i != 5) {
                return null;
            }
            return ENDPOINT_NAME;
        }

        @Deprecated
        public static HostIdentifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LbEndpointOrBuilder {
        private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> endpointBuilder_;
        private int healthStatus_;
        private int hostIdentifierCase_;
        private Object hostIdentifier_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> loadBalancingWeightBuilder_;
        private UInt32Value loadBalancingWeight_;
        private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> metadataBuilder_;
        private Metadata metadata_;

        private Builder() {
            this.hostIdentifierCase_ = 0;
            this.healthStatus_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.hostIdentifierCase_ = 0;
            this.healthStatus_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EndpointComponentsProto.internal_static_envoy_config_endpoint_v3_LbEndpoint_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
        public int getHealthStatusValue() {
            return this.healthStatus_;
        }

        public Builder setHealthStatusValue(int i) {
            this.healthStatus_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
        public boolean hasEndpoint() {
            return this.hostIdentifierCase_ == 1;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
        public boolean hasLoadBalancingWeight() {
            return (this.loadBalancingWeightBuilder_ == null && this.loadBalancingWeight_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
        public boolean hasMetadata() {
            return (this.metadataBuilder_ == null && this.metadata_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EndpointComponentsProto.internal_static_envoy_config_endpoint_v3_LbEndpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(LbEndpoint.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = LbEndpoint.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24904clear() {
            super.clear();
            this.healthStatus_ = 0;
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            if (this.loadBalancingWeightBuilder_ == null) {
                this.loadBalancingWeight_ = null;
            } else {
                this.loadBalancingWeight_ = null;
                this.loadBalancingWeightBuilder_ = null;
            }
            this.hostIdentifierCase_ = 0;
            this.hostIdentifier_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return EndpointComponentsProto.internal_static_envoy_config_endpoint_v3_LbEndpoint_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LbEndpoint m24917getDefaultInstanceForType() {
            return LbEndpoint.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LbEndpoint m24898build() throws UninitializedMessageException {
            LbEndpoint lbEndpointM24900buildPartial = m24900buildPartial();
            if (lbEndpointM24900buildPartial.isInitialized()) {
                return lbEndpointM24900buildPartial;
            }
            throw newUninitializedMessageException(lbEndpointM24900buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LbEndpoint m24900buildPartial() {
            LbEndpoint lbEndpoint = new LbEndpoint(this);
            if (this.hostIdentifierCase_ == 1) {
                SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.endpointBuilder_;
                if (singleFieldBuilderV3 == null) {
                    lbEndpoint.hostIdentifier_ = this.hostIdentifier_;
                } else {
                    lbEndpoint.hostIdentifier_ = singleFieldBuilderV3.build();
                }
            }
            if (this.hostIdentifierCase_ == 5) {
                lbEndpoint.hostIdentifier_ = this.hostIdentifier_;
            }
            lbEndpoint.healthStatus_ = this.healthStatus_;
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV32 = this.metadataBuilder_;
            if (singleFieldBuilderV32 == null) {
                lbEndpoint.metadata_ = this.metadata_;
            } else {
                lbEndpoint.metadata_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV33 = this.loadBalancingWeightBuilder_;
            if (singleFieldBuilderV33 == null) {
                lbEndpoint.loadBalancingWeight_ = this.loadBalancingWeight_;
            } else {
                lbEndpoint.loadBalancingWeight_ = singleFieldBuilderV33.build();
            }
            lbEndpoint.hostIdentifierCase_ = this.hostIdentifierCase_;
            onBuilt();
            return lbEndpoint;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24916clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24928setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24906clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24909clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24930setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24896addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24921mergeFrom(Message message) {
            if (message instanceof LbEndpoint) {
                return mergeFrom((LbEndpoint) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(LbEndpoint lbEndpoint) {
            if (lbEndpoint == LbEndpoint.getDefaultInstance()) {
                return this;
            }
            if (lbEndpoint.healthStatus_ != 0) {
                setHealthStatusValue(lbEndpoint.getHealthStatusValue());
            }
            if (lbEndpoint.hasMetadata()) {
                mergeMetadata(lbEndpoint.getMetadata());
            }
            if (lbEndpoint.hasLoadBalancingWeight()) {
                mergeLoadBalancingWeight(lbEndpoint.getLoadBalancingWeight());
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$endpoint$v3$LbEndpoint$HostIdentifierCase[lbEndpoint.getHostIdentifierCase().ordinal()];
            if (i == 1) {
                mergeEndpoint(lbEndpoint.getEndpoint());
            } else if (i == 2) {
                this.hostIdentifierCase_ = 5;
                this.hostIdentifier_ = lbEndpoint.hostIdentifier_;
                onChanged();
            }
            m24926mergeUnknownFields(lbEndpoint.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpoint.Builder m24922mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpoint.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpoint r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpoint) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpoint r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpoint) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpoint.Builder.m24922mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpoint$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
        public HostIdentifierCase getHostIdentifierCase() {
            return HostIdentifierCase.forNumber(this.hostIdentifierCase_);
        }

        public Builder clearHostIdentifier() {
            this.hostIdentifierCase_ = 0;
            this.hostIdentifier_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
        public Endpoint getEndpoint() {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.endpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.hostIdentifierCase_ == 1) {
                    return (Endpoint) this.hostIdentifier_;
                }
                return Endpoint.getDefaultInstance();
            }
            if (this.hostIdentifierCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            }
            return Endpoint.getDefaultInstance();
        }

        public Builder setEndpoint(Endpoint endpoint) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.endpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                endpoint.getClass();
                this.hostIdentifier_ = endpoint;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(endpoint);
            }
            this.hostIdentifierCase_ = 1;
            return this;
        }

        public Builder setEndpoint(Endpoint.Builder builder) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.endpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.hostIdentifier_ = builder.m24760build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m24760build());
            }
            this.hostIdentifierCase_ = 1;
            return this;
        }

        public Builder mergeEndpoint(Endpoint endpoint) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.endpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.hostIdentifierCase_ != 1 || this.hostIdentifier_ == Endpoint.getDefaultInstance()) {
                    this.hostIdentifier_ = endpoint;
                } else {
                    this.hostIdentifier_ = Endpoint.newBuilder((Endpoint) this.hostIdentifier_).mergeFrom(endpoint).m24762buildPartial();
                }
                onChanged();
            } else {
                if (this.hostIdentifierCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(endpoint);
                }
                this.endpointBuilder_.setMessage(endpoint);
            }
            this.hostIdentifierCase_ = 1;
            return this;
        }

        public Builder clearEndpoint() {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.endpointBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.hostIdentifierCase_ == 1) {
                    this.hostIdentifierCase_ = 0;
                    this.hostIdentifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.hostIdentifierCase_ == 1) {
                this.hostIdentifierCase_ = 0;
                this.hostIdentifier_ = null;
                onChanged();
            }
            return this;
        }

        public Endpoint.Builder getEndpointBuilder() {
            return getEndpointFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
        public EndpointOrBuilder getEndpointOrBuilder() {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3;
            int i = this.hostIdentifierCase_;
            if (i == 1 && (singleFieldBuilderV3 = this.endpointBuilder_) != null) {
                return (EndpointOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 1) {
                return (Endpoint) this.hostIdentifier_;
            }
            return Endpoint.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> getEndpointFieldBuilder() {
            if (this.endpointBuilder_ == null) {
                if (this.hostIdentifierCase_ != 1) {
                    this.hostIdentifier_ = Endpoint.getDefaultInstance();
                }
                this.endpointBuilder_ = new SingleFieldBuilderV3<>((Endpoint) this.hostIdentifier_, getParentForChildren(), isClean());
                this.hostIdentifier_ = null;
            }
            this.hostIdentifierCase_ = 1;
            onChanged();
            return this.endpointBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
        public String getEndpointName() {
            String str = this.hostIdentifierCase_ == 5 ? this.hostIdentifier_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.hostIdentifierCase_ == 5) {
                    this.hostIdentifier_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setEndpointName(String str) {
            str.getClass();
            this.hostIdentifierCase_ = 5;
            this.hostIdentifier_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
        public ByteString getEndpointNameBytes() {
            String str = this.hostIdentifierCase_ == 5 ? this.hostIdentifier_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.hostIdentifierCase_ == 5) {
                    this.hostIdentifier_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setEndpointNameBytes(ByteString byteString) {
            byteString.getClass();
            LbEndpoint.checkByteStringIsUtf8(byteString);
            this.hostIdentifierCase_ = 5;
            this.hostIdentifier_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearEndpointName() {
            if (this.hostIdentifierCase_ == 5) {
                this.hostIdentifierCase_ = 0;
                this.hostIdentifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
        public HealthStatus getHealthStatus() {
            HealthStatus healthStatusValueOf = HealthStatus.valueOf(this.healthStatus_);
            return healthStatusValueOf == null ? HealthStatus.UNRECOGNIZED : healthStatusValueOf;
        }

        public Builder setHealthStatus(HealthStatus healthStatus) {
            healthStatus.getClass();
            this.healthStatus_ = healthStatus.getNumber();
            onChanged();
            return this;
        }

        public Builder clearHealthStatus() {
            this.healthStatus_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
        public Metadata getMetadata() {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Metadata metadata = this.metadata_;
            return metadata == null ? Metadata.getDefaultInstance() : metadata;
        }

        public Builder setMetadata(Metadata metadata) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                metadata.getClass();
                this.metadata_ = metadata;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(metadata);
            }
            return this;
        }

        public Builder setMetadata(Metadata.Builder builder) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.metadata_ = builder.m23603build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m23603build());
            }
            return this;
        }

        public Builder mergeMetadata(Metadata metadata) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Metadata metadata2 = this.metadata_;
                if (metadata2 != null) {
                    this.metadata_ = Metadata.newBuilder(metadata2).mergeFrom(metadata).m23605buildPartial();
                } else {
                    this.metadata_ = metadata;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(metadata);
            }
            return this;
        }

        public Builder clearMetadata() {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
                onChanged();
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        public Metadata.Builder getMetadataBuilder() {
            onChanged();
            return getMetadataFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
        public MetadataOrBuilder getMetadataOrBuilder() {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (MetadataOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Metadata metadata = this.metadata_;
            return metadata == null ? Metadata.getDefaultInstance() : metadata;
        }

        private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> getMetadataFieldBuilder() {
            if (this.metadataBuilder_ == null) {
                this.metadataBuilder_ = new SingleFieldBuilderV3<>(getMetadata(), getParentForChildren(), isClean());
                this.metadata_ = null;
            }
            return this.metadataBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
        public UInt32Value getLoadBalancingWeight() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.loadBalancingWeightBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.loadBalancingWeight_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setLoadBalancingWeight(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.loadBalancingWeightBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.loadBalancingWeight_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setLoadBalancingWeight(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.loadBalancingWeightBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.loadBalancingWeight_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeLoadBalancingWeight(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.loadBalancingWeightBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.loadBalancingWeight_;
                if (uInt32Value2 != null) {
                    this.loadBalancingWeight_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.loadBalancingWeight_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearLoadBalancingWeight() {
            if (this.loadBalancingWeightBuilder_ == null) {
                this.loadBalancingWeight_ = null;
                onChanged();
            } else {
                this.loadBalancingWeight_ = null;
                this.loadBalancingWeightBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getLoadBalancingWeightBuilder() {
            onChanged();
            return getLoadBalancingWeightFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpointOrBuilder
        public UInt32ValueOrBuilder getLoadBalancingWeightOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.loadBalancingWeightBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.loadBalancingWeight_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getLoadBalancingWeightFieldBuilder() {
            if (this.loadBalancingWeightBuilder_ == null) {
                this.loadBalancingWeightBuilder_ = new SingleFieldBuilderV3<>(getLoadBalancingWeight(), getParentForChildren(), isClean());
                this.loadBalancingWeight_ = null;
            }
            return this.loadBalancingWeightBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m24932setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m24926mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpoint$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$endpoint$v3$LbEndpoint$HostIdentifierCase;

        static {
            int[] iArr = new int[HostIdentifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$endpoint$v3$LbEndpoint$HostIdentifierCase = iArr;
            try {
                iArr[HostIdentifierCase.ENDPOINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$endpoint$v3$LbEndpoint$HostIdentifierCase[HostIdentifierCase.ENDPOINT_NAME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$endpoint$v3$LbEndpoint$HostIdentifierCase[HostIdentifierCase.HOSTIDENTIFIER_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
