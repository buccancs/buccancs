package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

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
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class TlsParameters extends GeneratedMessageV3 implements TlsParametersOrBuilder {
    public static final int CIPHER_SUITES_FIELD_NUMBER = 3;
    public static final int ECDH_CURVES_FIELD_NUMBER = 4;
    public static final int TLS_MAXIMUM_PROTOCOL_VERSION_FIELD_NUMBER = 2;
    public static final int TLS_MINIMUM_PROTOCOL_VERSION_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final TlsParameters DEFAULT_INSTANCE = new TlsParameters();
    private static final Parser<TlsParameters> PARSER = new AbstractParser<TlsParameters>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParameters.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public TlsParameters m13890parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new TlsParameters(codedInputStream, extensionRegistryLite);
        }
    };
    private LazyStringList cipherSuites_;
    private LazyStringList ecdhCurves_;
    private byte memoizedIsInitialized;
    private int tlsMaximumProtocolVersion_;
    private int tlsMinimumProtocolVersion_;

    private TlsParameters(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private TlsParameters() {
        this.memoizedIsInitialized = (byte) -1;
        this.tlsMinimumProtocolVersion_ = 0;
        this.tlsMaximumProtocolVersion_ = 0;
        this.cipherSuites_ = LazyStringArrayList.EMPTY;
        this.ecdhCurves_ = LazyStringArrayList.EMPTY;
    }

    private TlsParameters(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 8) {
                            this.tlsMinimumProtocolVersion_ = codedInputStream.readEnum();
                        } else if (tag == 16) {
                            this.tlsMaximumProtocolVersion_ = codedInputStream.readEnum();
                        } else if (tag == 26) {
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if ((i & 1) == 0) {
                                this.cipherSuites_ = new LazyStringArrayList();
                                i |= 1;
                            }
                            this.cipherSuites_.add(stringRequireUtf8);
                        } else if (tag == 34) {
                            String stringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            if ((i & 2) == 0) {
                                this.ecdhCurves_ = new LazyStringArrayList();
                                i |= 2;
                            }
                            this.ecdhCurves_.add(stringRequireUtf82);
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
                if ((i & 1) != 0) {
                    this.cipherSuites_ = this.cipherSuites_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.ecdhCurves_ = this.ecdhCurves_.getUnmodifiableView();
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static TlsParameters getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TlsParameters> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return CommonProto.internal_static_envoy_api_v2_auth_TlsParameters_descriptor;
    }

    public static TlsParameters parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (TlsParameters) PARSER.parseFrom(byteBuffer);
    }

    public static TlsParameters parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TlsParameters) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static TlsParameters parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TlsParameters) PARSER.parseFrom(byteString);
    }

    public static TlsParameters parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TlsParameters) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static TlsParameters parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TlsParameters) PARSER.parseFrom(bArr);
    }

    public static TlsParameters parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TlsParameters) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static TlsParameters parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static TlsParameters parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TlsParameters parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static TlsParameters parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TlsParameters parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static TlsParameters parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m13888toBuilder();
    }

    public static Builder newBuilder(TlsParameters tlsParameters) {
        return DEFAULT_INSTANCE.m13888toBuilder().mergeFrom(tlsParameters);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
    /* renamed from: getCipherSuitesList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo13881getCipherSuitesList() {
        return this.cipherSuites_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public TlsParameters m13882getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
    /* renamed from: getEcdhCurvesList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo13884getEcdhCurvesList() {
        return this.ecdhCurves_;
    }

    public Parser<TlsParameters> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
    public int getTlsMaximumProtocolVersionValue() {
        return this.tlsMaximumProtocolVersion_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
    public int getTlsMinimumProtocolVersionValue() {
        return this.tlsMinimumProtocolVersion_;
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
        return new TlsParameters();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return CommonProto.internal_static_envoy_api_v2_auth_TlsParameters_fieldAccessorTable.ensureFieldAccessorsInitialized(TlsParameters.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
    public TlsProtocol getTlsMinimumProtocolVersion() {
        TlsProtocol tlsProtocolValueOf = TlsProtocol.valueOf(this.tlsMinimumProtocolVersion_);
        return tlsProtocolValueOf == null ? TlsProtocol.UNRECOGNIZED : tlsProtocolValueOf;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
    public TlsProtocol getTlsMaximumProtocolVersion() {
        TlsProtocol tlsProtocolValueOf = TlsProtocol.valueOf(this.tlsMaximumProtocolVersion_);
        return tlsProtocolValueOf == null ? TlsProtocol.UNRECOGNIZED : tlsProtocolValueOf;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
    public int getCipherSuitesCount() {
        return this.cipherSuites_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
    public String getCipherSuites(int i) {
        return (String) this.cipherSuites_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
    public ByteString getCipherSuitesBytes(int i) {
        return this.cipherSuites_.getByteString(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
    public int getEcdhCurvesCount() {
        return this.ecdhCurves_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
    public String getEcdhCurves(int i) {
        return (String) this.ecdhCurves_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
    public ByteString getEcdhCurvesBytes(int i) {
        return this.ecdhCurves_.getByteString(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.tlsMinimumProtocolVersion_ != TlsProtocol.TLS_AUTO.getNumber()) {
            codedOutputStream.writeEnum(1, this.tlsMinimumProtocolVersion_);
        }
        if (this.tlsMaximumProtocolVersion_ != TlsProtocol.TLS_AUTO.getNumber()) {
            codedOutputStream.writeEnum(2, this.tlsMaximumProtocolVersion_);
        }
        for (int i = 0; i < this.cipherSuites_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.cipherSuites_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.ecdhCurves_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.ecdhCurves_.getRaw(i2));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeEnumSize = this.tlsMinimumProtocolVersion_ != TlsProtocol.TLS_AUTO.getNumber() ? CodedOutputStream.computeEnumSize(1, this.tlsMinimumProtocolVersion_) : 0;
        if (this.tlsMaximumProtocolVersion_ != TlsProtocol.TLS_AUTO.getNumber()) {
            iComputeEnumSize += CodedOutputStream.computeEnumSize(2, this.tlsMaximumProtocolVersion_);
        }
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.cipherSuites_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.cipherSuites_.getRaw(i2));
        }
        int size = iComputeEnumSize + iComputeStringSizeNoTag + mo13881getCipherSuitesList().size();
        int iComputeStringSizeNoTag2 = 0;
        for (int i3 = 0; i3 < this.ecdhCurves_.size(); i3++) {
            iComputeStringSizeNoTag2 += computeStringSizeNoTag(this.ecdhCurves_.getRaw(i3));
        }
        int size2 = size + iComputeStringSizeNoTag2 + mo13884getEcdhCurvesList().size() + this.unknownFields.getSerializedSize();
        this.memoizedSize = size2;
        return size2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TlsParameters)) {
            return super.equals(obj);
        }
        TlsParameters tlsParameters = (TlsParameters) obj;
        return this.tlsMinimumProtocolVersion_ == tlsParameters.tlsMinimumProtocolVersion_ && this.tlsMaximumProtocolVersion_ == tlsParameters.tlsMaximumProtocolVersion_ && mo13881getCipherSuitesList().equals(tlsParameters.mo13881getCipherSuitesList()) && mo13884getEcdhCurvesList().equals(tlsParameters.mo13884getEcdhCurvesList()) && this.unknownFields.equals(tlsParameters.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.tlsMinimumProtocolVersion_) * 37) + 2) * 53) + this.tlsMaximumProtocolVersion_;
        if (getCipherSuitesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + mo13881getCipherSuitesList().hashCode();
        }
        if (getEcdhCurvesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + mo13884getEcdhCurvesList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m13885newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m13888toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum TlsProtocol implements ProtocolMessageEnum {
        TLS_AUTO(0),
        TLSv1_0(1),
        TLSv1_1(2),
        TLSv1_2(3),
        TLSv1_3(4),
        UNRECOGNIZED(-1);

        public static final int TLS_AUTO_VALUE = 0;
        public static final int TLSv1_0_VALUE = 1;
        public static final int TLSv1_1_VALUE = 2;
        public static final int TLSv1_2_VALUE = 3;
        public static final int TLSv1_3_VALUE = 4;
        private static final Internal.EnumLiteMap<TlsProtocol> internalValueMap = new Internal.EnumLiteMap<TlsProtocol>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParameters.TlsProtocol.1
            public TlsProtocol findValueByNumber(int i) {
                return TlsProtocol.forNumber(i);
            }
        };
        private static final TlsProtocol[] VALUES = values();
        private final int value;

        TlsProtocol(int i) {
            this.value = i;
        }

        public static TlsProtocol forNumber(int i) {
            if (i == 0) {
                return TLS_AUTO;
            }
            if (i == 1) {
                return TLSv1_0;
            }
            if (i == 2) {
                return TLSv1_1;
            }
            if (i == 3) {
                return TLSv1_2;
            }
            if (i != 4) {
                return null;
            }
            return TLSv1_3;
        }

        public static Internal.EnumLiteMap<TlsProtocol> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static TlsProtocol valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) TlsParameters.getDescriptor().getEnumTypes().get(0);
        }

        public static TlsProtocol valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TlsParametersOrBuilder {
        private int bitField0_;
        private LazyStringList cipherSuites_;
        private LazyStringList ecdhCurves_;
        private int tlsMaximumProtocolVersion_;
        private int tlsMinimumProtocolVersion_;

        private Builder() {
            this.tlsMinimumProtocolVersion_ = 0;
            this.tlsMaximumProtocolVersion_ = 0;
            this.cipherSuites_ = LazyStringArrayList.EMPTY;
            this.ecdhCurves_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.tlsMinimumProtocolVersion_ = 0;
            this.tlsMaximumProtocolVersion_ = 0;
            this.cipherSuites_ = LazyStringArrayList.EMPTY;
            this.ecdhCurves_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return CommonProto.internal_static_envoy_api_v2_auth_TlsParameters_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
        public int getTlsMaximumProtocolVersionValue() {
            return this.tlsMaximumProtocolVersion_;
        }

        public Builder setTlsMaximumProtocolVersionValue(int i) {
            this.tlsMaximumProtocolVersion_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
        public int getTlsMinimumProtocolVersionValue() {
            return this.tlsMinimumProtocolVersion_;
        }

        public Builder setTlsMinimumProtocolVersionValue(int i) {
            this.tlsMinimumProtocolVersion_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return CommonProto.internal_static_envoy_api_v2_auth_TlsParameters_fieldAccessorTable.ensureFieldAccessorsInitialized(TlsParameters.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = TlsParameters.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13899clear() {
            super.clear();
            this.tlsMinimumProtocolVersion_ = 0;
            this.tlsMaximumProtocolVersion_ = 0;
            this.cipherSuites_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.ecdhCurves_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return CommonProto.internal_static_envoy_api_v2_auth_TlsParameters_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TlsParameters m13912getDefaultInstanceForType() {
            return TlsParameters.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TlsParameters m13893build() throws UninitializedMessageException {
            TlsParameters tlsParametersM13895buildPartial = m13895buildPartial();
            if (tlsParametersM13895buildPartial.isInitialized()) {
                return tlsParametersM13895buildPartial;
            }
            throw newUninitializedMessageException(tlsParametersM13895buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TlsParameters m13895buildPartial() {
            TlsParameters tlsParameters = new TlsParameters(this);
            tlsParameters.tlsMinimumProtocolVersion_ = this.tlsMinimumProtocolVersion_;
            tlsParameters.tlsMaximumProtocolVersion_ = this.tlsMaximumProtocolVersion_;
            if ((this.bitField0_ & 1) != 0) {
                this.cipherSuites_ = this.cipherSuites_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            tlsParameters.cipherSuites_ = this.cipherSuites_;
            if ((this.bitField0_ & 2) != 0) {
                this.ecdhCurves_ = this.ecdhCurves_.getUnmodifiableView();
                this.bitField0_ &= -3;
            }
            tlsParameters.ecdhCurves_ = this.ecdhCurves_;
            onBuilt();
            return tlsParameters;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13911clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13923setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13901clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13904clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13925setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13891addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13916mergeFrom(Message message) {
            if (message instanceof TlsParameters) {
                return mergeFrom((TlsParameters) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(TlsParameters tlsParameters) {
            if (tlsParameters == TlsParameters.getDefaultInstance()) {
                return this;
            }
            if (tlsParameters.tlsMinimumProtocolVersion_ != 0) {
                setTlsMinimumProtocolVersionValue(tlsParameters.getTlsMinimumProtocolVersionValue());
            }
            if (tlsParameters.tlsMaximumProtocolVersion_ != 0) {
                setTlsMaximumProtocolVersionValue(tlsParameters.getTlsMaximumProtocolVersionValue());
            }
            if (!tlsParameters.cipherSuites_.isEmpty()) {
                if (this.cipherSuites_.isEmpty()) {
                    this.cipherSuites_ = tlsParameters.cipherSuites_;
                    this.bitField0_ &= -2;
                } else {
                    ensureCipherSuitesIsMutable();
                    this.cipherSuites_.addAll(tlsParameters.cipherSuites_);
                }
                onChanged();
            }
            if (!tlsParameters.ecdhCurves_.isEmpty()) {
                if (this.ecdhCurves_.isEmpty()) {
                    this.ecdhCurves_ = tlsParameters.ecdhCurves_;
                    this.bitField0_ &= -3;
                } else {
                    ensureEcdhCurvesIsMutable();
                    this.ecdhCurves_.addAll(tlsParameters.ecdhCurves_);
                }
                onChanged();
            }
            m13921mergeUnknownFields(tlsParameters.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParameters.Builder m13917mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParameters.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParameters r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParameters) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParameters r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParameters) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParameters.Builder.m13917mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParameters$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
        public TlsProtocol getTlsMinimumProtocolVersion() {
            TlsProtocol tlsProtocolValueOf = TlsProtocol.valueOf(this.tlsMinimumProtocolVersion_);
            return tlsProtocolValueOf == null ? TlsProtocol.UNRECOGNIZED : tlsProtocolValueOf;
        }

        public Builder setTlsMinimumProtocolVersion(TlsProtocol tlsProtocol) {
            tlsProtocol.getClass();
            this.tlsMinimumProtocolVersion_ = tlsProtocol.getNumber();
            onChanged();
            return this;
        }

        public Builder clearTlsMinimumProtocolVersion() {
            this.tlsMinimumProtocolVersion_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
        public TlsProtocol getTlsMaximumProtocolVersion() {
            TlsProtocol tlsProtocolValueOf = TlsProtocol.valueOf(this.tlsMaximumProtocolVersion_);
            return tlsProtocolValueOf == null ? TlsProtocol.UNRECOGNIZED : tlsProtocolValueOf;
        }

        public Builder setTlsMaximumProtocolVersion(TlsProtocol tlsProtocol) {
            tlsProtocol.getClass();
            this.tlsMaximumProtocolVersion_ = tlsProtocol.getNumber();
            onChanged();
            return this;
        }

        public Builder clearTlsMaximumProtocolVersion() {
            this.tlsMaximumProtocolVersion_ = 0;
            onChanged();
            return this;
        }

        private void ensureCipherSuitesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.cipherSuites_ = new LazyStringArrayList(this.cipherSuites_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
        /* renamed from: getCipherSuitesList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo13881getCipherSuitesList() {
            return this.cipherSuites_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
        public int getCipherSuitesCount() {
            return this.cipherSuites_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
        public String getCipherSuites(int i) {
            return (String) this.cipherSuites_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
        public ByteString getCipherSuitesBytes(int i) {
            return this.cipherSuites_.getByteString(i);
        }

        public Builder setCipherSuites(int i, String str) {
            str.getClass();
            ensureCipherSuitesIsMutable();
            this.cipherSuites_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addCipherSuites(String str) {
            str.getClass();
            ensureCipherSuitesIsMutable();
            this.cipherSuites_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllCipherSuites(Iterable<String> iterable) {
            ensureCipherSuitesIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.cipherSuites_);
            onChanged();
            return this;
        }

        public Builder clearCipherSuites() {
            this.cipherSuites_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addCipherSuitesBytes(ByteString byteString) {
            byteString.getClass();
            TlsParameters.checkByteStringIsUtf8(byteString);
            ensureCipherSuitesIsMutable();
            this.cipherSuites_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureEcdhCurvesIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.ecdhCurves_ = new LazyStringArrayList(this.ecdhCurves_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
        /* renamed from: getEcdhCurvesList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo13884getEcdhCurvesList() {
            return this.ecdhCurves_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
        public int getEcdhCurvesCount() {
            return this.ecdhCurves_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
        public String getEcdhCurves(int i) {
            return (String) this.ecdhCurves_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParametersOrBuilder
        public ByteString getEcdhCurvesBytes(int i) {
            return this.ecdhCurves_.getByteString(i);
        }

        public Builder setEcdhCurves(int i, String str) {
            str.getClass();
            ensureEcdhCurvesIsMutable();
            this.ecdhCurves_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addEcdhCurves(String str) {
            str.getClass();
            ensureEcdhCurvesIsMutable();
            this.ecdhCurves_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllEcdhCurves(Iterable<String> iterable) {
            ensureEcdhCurvesIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.ecdhCurves_);
            onChanged();
            return this;
        }

        public Builder clearEcdhCurves() {
            this.ecdhCurves_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            onChanged();
            return this;
        }

        public Builder addEcdhCurvesBytes(ByteString byteString) {
            byteString.getClass();
            TlsParameters.checkByteStringIsUtf8(byteString);
            ensureEcdhCurvesIsMutable();
            this.ecdhCurves_.add(byteString);
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m13927setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m13921mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
