package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Address;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.AddressOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class UpstreamBindConfig extends GeneratedMessageV3 implements UpstreamBindConfigOrBuilder {
    public static final int SOURCE_ADDRESS_FIELD_NUMBER = 1;
    private static final UpstreamBindConfig DEFAULT_INSTANCE = new UpstreamBindConfig();
    private static final Parser<UpstreamBindConfig> PARSER = new AbstractParser<UpstreamBindConfig>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfig.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public UpstreamBindConfig m13331parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new UpstreamBindConfig(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private Address sourceAddress_;

    private UpstreamBindConfig(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private UpstreamBindConfig() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private UpstreamBindConfig(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                Address address = this.sourceAddress_;
                                Address.Builder builderM14257toBuilder = address != null ? address.m14257toBuilder() : null;
                                Address address2 = (Address) codedInputStream.readMessage(Address.parser(), extensionRegistryLite);
                                this.sourceAddress_ = address2;
                                if (builderM14257toBuilder != null) {
                                    builderM14257toBuilder.mergeFrom(address2);
                                    this.sourceAddress_ = builderM14257toBuilder.m14264buildPartial();
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

    public static UpstreamBindConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UpstreamBindConfig> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ClusterProto.internal_static_envoy_api_v2_UpstreamBindConfig_descriptor;
    }

    public static UpstreamBindConfig parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (UpstreamBindConfig) PARSER.parseFrom(byteBuffer);
    }

    public static UpstreamBindConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpstreamBindConfig) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static UpstreamBindConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (UpstreamBindConfig) PARSER.parseFrom(byteString);
    }

    public static UpstreamBindConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpstreamBindConfig) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static UpstreamBindConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (UpstreamBindConfig) PARSER.parseFrom(bArr);
    }

    public static UpstreamBindConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpstreamBindConfig) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static UpstreamBindConfig parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static UpstreamBindConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpstreamBindConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static UpstreamBindConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpstreamBindConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static UpstreamBindConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m13329toBuilder();
    }

    public static Builder newBuilder(UpstreamBindConfig upstreamBindConfig) {
        return DEFAULT_INSTANCE.m13329toBuilder().mergeFrom(upstreamBindConfig);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public UpstreamBindConfig m13324getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<UpstreamBindConfig> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfigOrBuilder
    public boolean hasSourceAddress() {
        return this.sourceAddress_ != null;
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
        return new UpstreamBindConfig();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ClusterProto.internal_static_envoy_api_v2_UpstreamBindConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(UpstreamBindConfig.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfigOrBuilder
    public Address getSourceAddress() {
        Address address = this.sourceAddress_;
        return address == null ? Address.getDefaultInstance() : address;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfigOrBuilder
    public AddressOrBuilder getSourceAddressOrBuilder() {
        return getSourceAddress();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.sourceAddress_ != null) {
            codedOutputStream.writeMessage(1, getSourceAddress());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = (this.sourceAddress_ != null ? CodedOutputStream.computeMessageSize(1, getSourceAddress()) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeMessageSize;
        return iComputeMessageSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpstreamBindConfig)) {
            return super.equals(obj);
        }
        UpstreamBindConfig upstreamBindConfig = (UpstreamBindConfig) obj;
        if (hasSourceAddress() != upstreamBindConfig.hasSourceAddress()) {
            return false;
        }
        return (!hasSourceAddress() || getSourceAddress().equals(upstreamBindConfig.getSourceAddress())) && this.unknownFields.equals(upstreamBindConfig.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasSourceAddress()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getSourceAddress().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m13326newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m13329toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpstreamBindConfigOrBuilder {
        private SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> sourceAddressBuilder_;
        private Address sourceAddress_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ClusterProto.internal_static_envoy_api_v2_UpstreamBindConfig_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfigOrBuilder
        public boolean hasSourceAddress() {
            return (this.sourceAddressBuilder_ == null && this.sourceAddress_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ClusterProto.internal_static_envoy_api_v2_UpstreamBindConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(UpstreamBindConfig.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = UpstreamBindConfig.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13340clear() {
            super.clear();
            if (this.sourceAddressBuilder_ == null) {
                this.sourceAddress_ = null;
            } else {
                this.sourceAddress_ = null;
                this.sourceAddressBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ClusterProto.internal_static_envoy_api_v2_UpstreamBindConfig_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public UpstreamBindConfig m13353getDefaultInstanceForType() {
            return UpstreamBindConfig.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public UpstreamBindConfig m13334build() throws UninitializedMessageException {
            UpstreamBindConfig upstreamBindConfigM13336buildPartial = m13336buildPartial();
            if (upstreamBindConfigM13336buildPartial.isInitialized()) {
                return upstreamBindConfigM13336buildPartial;
            }
            throw newUninitializedMessageException(upstreamBindConfigM13336buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public UpstreamBindConfig m13336buildPartial() {
            UpstreamBindConfig upstreamBindConfig = new UpstreamBindConfig(this);
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.sourceAddressBuilder_;
            if (singleFieldBuilderV3 == null) {
                upstreamBindConfig.sourceAddress_ = this.sourceAddress_;
            } else {
                upstreamBindConfig.sourceAddress_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return upstreamBindConfig;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13352clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13364setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13342clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13345clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13366setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13332addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13357mergeFrom(Message message) {
            if (message instanceof UpstreamBindConfig) {
                return mergeFrom((UpstreamBindConfig) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(UpstreamBindConfig upstreamBindConfig) {
            if (upstreamBindConfig == UpstreamBindConfig.getDefaultInstance()) {
                return this;
            }
            if (upstreamBindConfig.hasSourceAddress()) {
                mergeSourceAddress(upstreamBindConfig.getSourceAddress());
            }
            m13362mergeUnknownFields(upstreamBindConfig.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfig.Builder m13358mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfig.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfig r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfig) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfig r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfig) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfig.Builder.m13358mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfig$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfigOrBuilder
        public Address getSourceAddress() {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.sourceAddressBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Address address = this.sourceAddress_;
            return address == null ? Address.getDefaultInstance() : address;
        }

        public Builder setSourceAddress(Address address) {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.sourceAddressBuilder_;
            if (singleFieldBuilderV3 == null) {
                address.getClass();
                this.sourceAddress_ = address;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(address);
            }
            return this;
        }

        public Builder setSourceAddress(Address.Builder builder) {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.sourceAddressBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sourceAddress_ = builder.m14262build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m14262build());
            }
            return this;
        }

        public Builder mergeSourceAddress(Address address) {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.sourceAddressBuilder_;
            if (singleFieldBuilderV3 == null) {
                Address address2 = this.sourceAddress_;
                if (address2 != null) {
                    this.sourceAddress_ = Address.newBuilder(address2).mergeFrom(address).m14264buildPartial();
                } else {
                    this.sourceAddress_ = address;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(address);
            }
            return this;
        }

        public Builder clearSourceAddress() {
            if (this.sourceAddressBuilder_ == null) {
                this.sourceAddress_ = null;
                onChanged();
            } else {
                this.sourceAddress_ = null;
                this.sourceAddressBuilder_ = null;
            }
            return this;
        }

        public Address.Builder getSourceAddressBuilder() {
            onChanged();
            return getSourceAddressFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamBindConfigOrBuilder
        public AddressOrBuilder getSourceAddressOrBuilder() {
            SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> singleFieldBuilderV3 = this.sourceAddressBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (AddressOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Address address = this.sourceAddress_;
            return address == null ? Address.getDefaultInstance() : address;
        }

        private SingleFieldBuilderV3<Address, Address.Builder, AddressOrBuilder> getSourceAddressFieldBuilder() {
            if (this.sourceAddressBuilder_ == null) {
                this.sourceAddressBuilder_ = new SingleFieldBuilderV3<>(getSourceAddress(), getParentForChildren(), isClean());
                this.sourceAddress_ = null;
            }
            return this.sourceAddressBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m13368setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m13362mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
