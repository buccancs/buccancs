package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

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
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class SocketAddress extends GeneratedMessageV3 implements SocketAddressOrBuilder {
    public static final int ADDRESS_FIELD_NUMBER = 2;
    public static final int IPV4_COMPAT_FIELD_NUMBER = 6;
    public static final int NAMED_PORT_FIELD_NUMBER = 4;
    public static final int PORT_VALUE_FIELD_NUMBER = 3;
    public static final int PROTOCOL_FIELD_NUMBER = 1;
    public static final int RESOLVER_NAME_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private static final SocketAddress DEFAULT_INSTANCE = new SocketAddress();
    private static final Parser<SocketAddress> PARSER = new AbstractParser<SocketAddress>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddress.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public SocketAddress m24156parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SocketAddress(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object address_;
    private boolean ipv4Compat_;
    private byte memoizedIsInitialized;
    private int portSpecifierCase_;
    private Object portSpecifier_;
    private int protocol_;
    private volatile Object resolverName_;

    private SocketAddress(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.portSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private SocketAddress() {
        this.portSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.protocol_ = 0;
        this.address_ = "";
        this.resolverName_ = "";
    }

    private SocketAddress(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 8) {
                            this.protocol_ = codedInputStream.readEnum();
                        } else if (tag == 18) {
                            this.address_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 24) {
                            this.portSpecifierCase_ = 3;
                            this.portSpecifier_ = Integer.valueOf(codedInputStream.readUInt32());
                        } else if (tag == 34) {
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            this.portSpecifierCase_ = 4;
                            this.portSpecifier_ = stringRequireUtf8;
                        } else if (tag == 42) {
                            this.resolverName_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 48) {
                            this.ipv4Compat_ = codedInputStream.readBool();
                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
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

    public static SocketAddress getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SocketAddress> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AddressProto.internal_static_envoy_config_core_v3_SocketAddress_descriptor;
    }

    public static SocketAddress parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (SocketAddress) PARSER.parseFrom(byteBuffer);
    }

    public static SocketAddress parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketAddress) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SocketAddress parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SocketAddress) PARSER.parseFrom(byteString);
    }

    public static SocketAddress parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketAddress) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SocketAddress parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SocketAddress) PARSER.parseFrom(bArr);
    }

    public static SocketAddress parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketAddress) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SocketAddress parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SocketAddress parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SocketAddress parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SocketAddress parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SocketAddress parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SocketAddress parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m24154toBuilder();
    }

    public static Builder newBuilder(SocketAddress socketAddress) {
        return DEFAULT_INSTANCE.m24154toBuilder().mergeFrom(socketAddress);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SocketAddress m24149getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
    public boolean getIpv4Compat() {
        return this.ipv4Compat_;
    }

    public Parser<SocketAddress> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
    public int getProtocolValue() {
        return this.protocol_;
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
        return new SocketAddress();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AddressProto.internal_static_envoy_config_core_v3_SocketAddress_fieldAccessorTable.ensureFieldAccessorsInitialized(SocketAddress.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
    public PortSpecifierCase getPortSpecifierCase() {
        return PortSpecifierCase.forNumber(this.portSpecifierCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
    public Protocol getProtocol() {
        Protocol protocolValueOf = Protocol.valueOf(this.protocol_);
        return protocolValueOf == null ? Protocol.UNRECOGNIZED : protocolValueOf;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
    public String getAddress() {
        Object obj = this.address_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.address_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
    public ByteString getAddressBytes() {
        Object obj = this.address_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.address_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
    public int getPortValue() {
        if (this.portSpecifierCase_ == 3) {
            return ((Integer) this.portSpecifier_).intValue();
        }
        return 0;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
    public String getNamedPort() {
        String str = this.portSpecifierCase_ == 4 ? this.portSpecifier_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.portSpecifierCase_ == 4) {
            this.portSpecifier_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
    public ByteString getNamedPortBytes() {
        String str = this.portSpecifierCase_ == 4 ? this.portSpecifier_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.portSpecifierCase_ == 4) {
                this.portSpecifier_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
    public String getResolverName() {
        Object obj = this.resolverName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.resolverName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
    public ByteString getResolverNameBytes() {
        Object obj = this.resolverName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.resolverName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.protocol_ != Protocol.TCP.getNumber()) {
            codedOutputStream.writeEnum(1, this.protocol_);
        }
        if (!getAddressBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.address_);
        }
        if (this.portSpecifierCase_ == 3) {
            codedOutputStream.writeUInt32(3, ((Integer) this.portSpecifier_).intValue());
        }
        if (this.portSpecifierCase_ == 4) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.portSpecifier_);
        }
        if (!getResolverNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.resolverName_);
        }
        boolean z = this.ipv4Compat_;
        if (z) {
            codedOutputStream.writeBool(6, z);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeEnumSize = this.protocol_ != Protocol.TCP.getNumber() ? CodedOutputStream.computeEnumSize(1, this.protocol_) : 0;
        if (!getAddressBytes().isEmpty()) {
            iComputeEnumSize += GeneratedMessageV3.computeStringSize(2, this.address_);
        }
        if (this.portSpecifierCase_ == 3) {
            iComputeEnumSize += CodedOutputStream.computeUInt32Size(3, ((Integer) this.portSpecifier_).intValue());
        }
        if (this.portSpecifierCase_ == 4) {
            iComputeEnumSize += GeneratedMessageV3.computeStringSize(4, this.portSpecifier_);
        }
        if (!getResolverNameBytes().isEmpty()) {
            iComputeEnumSize += GeneratedMessageV3.computeStringSize(5, this.resolverName_);
        }
        boolean z = this.ipv4Compat_;
        if (z) {
            iComputeEnumSize += CodedOutputStream.computeBoolSize(6, z);
        }
        int serializedSize = iComputeEnumSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SocketAddress)) {
            return super.equals(obj);
        }
        SocketAddress socketAddress = (SocketAddress) obj;
        if (this.protocol_ != socketAddress.protocol_ || !getAddress().equals(socketAddress.getAddress()) || !getResolverName().equals(socketAddress.getResolverName()) || getIpv4Compat() != socketAddress.getIpv4Compat() || !getPortSpecifierCase().equals(socketAddress.getPortSpecifierCase())) {
            return false;
        }
        int i = this.portSpecifierCase_;
        if (i == 3) {
            if (getPortValue() != socketAddress.getPortValue()) {
                return false;
            }
        } else if (i == 4 && !getNamedPort().equals(socketAddress.getNamedPort())) {
            return false;
        }
        return this.unknownFields.equals(socketAddress.unknownFields);
    }

    public int hashCode() {
        int i;
        int portValue;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.protocol_) * 37) + 2) * 53) + getAddress().hashCode()) * 37) + 5) * 53) + getResolverName().hashCode()) * 37) + 6) * 53) + Internal.hashBoolean(getIpv4Compat());
        int i2 = this.portSpecifierCase_;
        if (i2 == 3) {
            i = ((iHashCode * 37) + 3) * 53;
            portValue = getPortValue();
        } else {
            if (i2 == 4) {
                i = ((iHashCode * 37) + 4) * 53;
                portValue = getNamedPort().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }
        iHashCode = i + portValue;
        int iHashCode22 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode22;
        return iHashCode22;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m24151newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m24154toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum Protocol implements ProtocolMessageEnum {
        TCP(0),
        UDP(1),
        UNRECOGNIZED(-1);

        public static final int TCP_VALUE = 0;
        public static final int UDP_VALUE = 1;
        private static final Internal.EnumLiteMap<Protocol> internalValueMap = new Internal.EnumLiteMap<Protocol>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddress.Protocol.1
            public Protocol findValueByNumber(int i) {
                return Protocol.forNumber(i);
            }
        };
        private static final Protocol[] VALUES = values();
        private final int value;

        Protocol(int i) {
            this.value = i;
        }

        public static Protocol forNumber(int i) {
            if (i == 0) {
                return TCP;
            }
            if (i != 1) {
                return null;
            }
            return UDP;
        }

        public static Internal.EnumLiteMap<Protocol> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static Protocol valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) SocketAddress.getDescriptor().getEnumTypes().get(0);
        }

        public static Protocol valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
    }

    public enum PortSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        PORT_VALUE(3),
        NAMED_PORT(4),
        PORTSPECIFIER_NOT_SET(0);

        private final int value;

        PortSpecifierCase(int i) {
            this.value = i;
        }

        public static PortSpecifierCase forNumber(int i) {
            if (i == 0) {
                return PORTSPECIFIER_NOT_SET;
            }
            if (i == 3) {
                return PORT_VALUE;
            }
            if (i != 4) {
                return null;
            }
            return NAMED_PORT;
        }

        @Deprecated
        public static PortSpecifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SocketAddressOrBuilder {
        private Object address_;
        private boolean ipv4Compat_;
        private int portSpecifierCase_;
        private Object portSpecifier_;
        private int protocol_;
        private Object resolverName_;

        private Builder() {
            this.portSpecifierCase_ = 0;
            this.protocol_ = 0;
            this.address_ = "";
            this.resolverName_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.portSpecifierCase_ = 0;
            this.protocol_ = 0;
            this.address_ = "";
            this.resolverName_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AddressProto.internal_static_envoy_config_core_v3_SocketAddress_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
        public boolean getIpv4Compat() {
            return this.ipv4Compat_;
        }

        public Builder setIpv4Compat(boolean z) {
            this.ipv4Compat_ = z;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
        public int getProtocolValue() {
            return this.protocol_;
        }

        public Builder setProtocolValue(int i) {
            this.protocol_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AddressProto.internal_static_envoy_config_core_v3_SocketAddress_fieldAccessorTable.ensureFieldAccessorsInitialized(SocketAddress.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SocketAddress.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24165clear() {
            super.clear();
            this.protocol_ = 0;
            this.address_ = "";
            this.resolverName_ = "";
            this.ipv4Compat_ = false;
            this.portSpecifierCase_ = 0;
            this.portSpecifier_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return AddressProto.internal_static_envoy_config_core_v3_SocketAddress_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketAddress m24178getDefaultInstanceForType() {
            return SocketAddress.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketAddress m24159build() throws UninitializedMessageException {
            SocketAddress socketAddressM24161buildPartial = m24161buildPartial();
            if (socketAddressM24161buildPartial.isInitialized()) {
                return socketAddressM24161buildPartial;
            }
            throw newUninitializedMessageException(socketAddressM24161buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketAddress m24161buildPartial() {
            SocketAddress socketAddress = new SocketAddress(this);
            socketAddress.protocol_ = this.protocol_;
            socketAddress.address_ = this.address_;
            if (this.portSpecifierCase_ == 3) {
                socketAddress.portSpecifier_ = this.portSpecifier_;
            }
            if (this.portSpecifierCase_ == 4) {
                socketAddress.portSpecifier_ = this.portSpecifier_;
            }
            socketAddress.resolverName_ = this.resolverName_;
            socketAddress.ipv4Compat_ = this.ipv4Compat_;
            socketAddress.portSpecifierCase_ = this.portSpecifierCase_;
            onBuilt();
            return socketAddress;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24177clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24189setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24167clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24170clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24191setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24157addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24182mergeFrom(Message message) {
            if (message instanceof SocketAddress) {
                return mergeFrom((SocketAddress) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SocketAddress socketAddress) {
            if (socketAddress == SocketAddress.getDefaultInstance()) {
                return this;
            }
            if (socketAddress.protocol_ != 0) {
                setProtocolValue(socketAddress.getProtocolValue());
            }
            if (!socketAddress.getAddress().isEmpty()) {
                this.address_ = socketAddress.address_;
                onChanged();
            }
            if (!socketAddress.getResolverName().isEmpty()) {
                this.resolverName_ = socketAddress.resolverName_;
                onChanged();
            }
            if (socketAddress.getIpv4Compat()) {
                setIpv4Compat(socketAddress.getIpv4Compat());
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$core$v3$SocketAddress$PortSpecifierCase[socketAddress.getPortSpecifierCase().ordinal()];
            if (i == 1) {
                setPortValue(socketAddress.getPortValue());
            } else if (i == 2) {
                this.portSpecifierCase_ = 4;
                this.portSpecifier_ = socketAddress.portSpecifier_;
                onChanged();
            }
            m24187mergeUnknownFields(socketAddress.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddress.Builder m24183mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddress.access$1100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddress r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddress) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddress r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddress) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddress.Builder.m24183mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddress$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
        public PortSpecifierCase getPortSpecifierCase() {
            return PortSpecifierCase.forNumber(this.portSpecifierCase_);
        }

        public Builder clearPortSpecifier() {
            this.portSpecifierCase_ = 0;
            this.portSpecifier_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
        public Protocol getProtocol() {
            Protocol protocolValueOf = Protocol.valueOf(this.protocol_);
            return protocolValueOf == null ? Protocol.UNRECOGNIZED : protocolValueOf;
        }

        public Builder setProtocol(Protocol protocol) {
            protocol.getClass();
            this.protocol_ = protocol.getNumber();
            onChanged();
            return this;
        }

        public Builder clearProtocol() {
            this.protocol_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
        public String getAddress() {
            Object obj = this.address_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.address_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setAddress(String str) {
            str.getClass();
            this.address_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
        public ByteString getAddressBytes() {
            Object obj = this.address_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.address_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAddressBytes(ByteString byteString) {
            byteString.getClass();
            SocketAddress.checkByteStringIsUtf8(byteString);
            this.address_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearAddress() {
            this.address_ = SocketAddress.getDefaultInstance().getAddress();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
        public int getPortValue() {
            if (this.portSpecifierCase_ == 3) {
                return ((Integer) this.portSpecifier_).intValue();
            }
            return 0;
        }

        public Builder setPortValue(int i) {
            this.portSpecifierCase_ = 3;
            this.portSpecifier_ = Integer.valueOf(i);
            onChanged();
            return this;
        }

        public Builder clearPortValue() {
            if (this.portSpecifierCase_ == 3) {
                this.portSpecifierCase_ = 0;
                this.portSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
        public String getNamedPort() {
            String str = this.portSpecifierCase_ == 4 ? this.portSpecifier_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.portSpecifierCase_ == 4) {
                    this.portSpecifier_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setNamedPort(String str) {
            str.getClass();
            this.portSpecifierCase_ = 4;
            this.portSpecifier_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
        public ByteString getNamedPortBytes() {
            String str = this.portSpecifierCase_ == 4 ? this.portSpecifier_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.portSpecifierCase_ == 4) {
                    this.portSpecifier_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setNamedPortBytes(ByteString byteString) {
            byteString.getClass();
            SocketAddress.checkByteStringIsUtf8(byteString);
            this.portSpecifierCase_ = 4;
            this.portSpecifier_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearNamedPort() {
            if (this.portSpecifierCase_ == 4) {
                this.portSpecifierCase_ = 0;
                this.portSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
        public String getResolverName() {
            Object obj = this.resolverName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.resolverName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setResolverName(String str) {
            str.getClass();
            this.resolverName_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddressOrBuilder
        public ByteString getResolverNameBytes() {
            Object obj = this.resolverName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.resolverName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setResolverNameBytes(ByteString byteString) {
            byteString.getClass();
            SocketAddress.checkByteStringIsUtf8(byteString);
            this.resolverName_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearResolverName() {
            this.resolverName_ = SocketAddress.getDefaultInstance().getResolverName();
            onChanged();
            return this;
        }

        public Builder clearIpv4Compat() {
            this.ipv4Compat_ = false;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m24193setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m24187mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddress$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$core$v3$SocketAddress$PortSpecifierCase;

        static {
            int[] iArr = new int[PortSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$core$v3$SocketAddress$PortSpecifierCase = iArr;
            try {
                iArr[PortSpecifierCase.PORT_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$SocketAddress$PortSpecifierCase[PortSpecifierCase.NAMED_PORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$SocketAddress$PortSpecifierCase[PortSpecifierCase.PORTSPECIFIER_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
