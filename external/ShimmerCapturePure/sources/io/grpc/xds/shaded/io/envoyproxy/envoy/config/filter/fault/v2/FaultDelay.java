package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercentOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class FaultDelay extends GeneratedMessageV3 implements FaultDelayOrBuilder {
    public static final int FIXED_DELAY_FIELD_NUMBER = 3;
    public static final int HEADER_DELAY_FIELD_NUMBER = 5;
    public static final int PERCENTAGE_FIELD_NUMBER = 4;
    public static final int TYPE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final FaultDelay DEFAULT_INSTANCE = new FaultDelay();
    private static final Parser<FaultDelay> PARSER = new AbstractParser<FaultDelay>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public FaultDelay m25726parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new FaultDelay(codedInputStream, extensionRegistryLite);
        }
    };
    private int faultDelaySecifierCase_;
    private Object faultDelaySecifier_;
    private byte memoizedIsInitialized;
    private FractionalPercent percentage_;
    private int type_;

    private FaultDelay(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.faultDelaySecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private FaultDelay() {
        this.faultDelaySecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.type_ = 0;
    }

    private FaultDelay(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Duration.Builder builderM25771toBuilder;
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag != 8) {
                            if (tag == 26) {
                                builderM25771toBuilder = this.faultDelaySecifierCase_ == 3 ? ((Duration) this.faultDelaySecifier_).toBuilder() : null;
                                Duration message = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                                this.faultDelaySecifier_ = message;
                                if (builderM25771toBuilder != null) {
                                    builderM25771toBuilder.mergeFrom(message);
                                    this.faultDelaySecifier_ = builderM25771toBuilder.buildPartial();
                                }
                                this.faultDelaySecifierCase_ = 3;
                            } else if (tag == 34) {
                                FractionalPercent fractionalPercent = this.percentage_;
                                builderM25771toBuilder = fractionalPercent != null ? fractionalPercent.m32859toBuilder() : null;
                                FractionalPercent message2 = codedInputStream.readMessage(FractionalPercent.parser(), extensionRegistryLite);
                                this.percentage_ = message2;
                                if (builderM25771toBuilder != null) {
                                    builderM25771toBuilder.mergeFrom(message2);
                                    this.percentage_ = builderM25771toBuilder.m32866buildPartial();
                                }
                            } else if (tag == 42) {
                                builderM25771toBuilder = this.faultDelaySecifierCase_ == 5 ? ((HeaderDelay) this.faultDelaySecifier_).m25771toBuilder() : null;
                                HeaderDelay message3 = codedInputStream.readMessage(HeaderDelay.parser(), extensionRegistryLite);
                                this.faultDelaySecifier_ = message3;
                                if (builderM25771toBuilder != null) {
                                    builderM25771toBuilder.mergeFrom(message3);
                                    this.faultDelaySecifier_ = builderM25771toBuilder.m25778buildPartial();
                                }
                                this.faultDelaySecifierCase_ = 5;
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.type_ = codedInputStream.readEnum();
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

    public static FaultDelay getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FaultDelay> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return FaultProto.internal_static_envoy_config_filter_fault_v2_FaultDelay_descriptor;
    }

    public static FaultDelay parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (FaultDelay) PARSER.parseFrom(byteBuffer);
    }

    public static FaultDelay parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FaultDelay) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static FaultDelay parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (FaultDelay) PARSER.parseFrom(byteString);
    }

    public static FaultDelay parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FaultDelay) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static FaultDelay parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (FaultDelay) PARSER.parseFrom(bArr);
    }

    public static FaultDelay parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FaultDelay) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static FaultDelay parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static FaultDelay parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static FaultDelay parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static FaultDelay parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static FaultDelay parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static FaultDelay parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m25724toBuilder();
    }

    public static Builder newBuilder(FaultDelay faultDelay) {
        return DEFAULT_INSTANCE.m25724toBuilder().mergeFrom(faultDelay);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public FaultDelay m25719getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<FaultDelay> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
    @Deprecated
    public int getTypeValue() {
        return this.type_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
    public boolean hasFixedDelay() {
        return this.faultDelaySecifierCase_ == 3;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
    public boolean hasHeaderDelay() {
        return this.faultDelaySecifierCase_ == 5;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
    public boolean hasPercentage() {
        return this.percentage_ != null;
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
        return new FaultDelay();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return FaultProto.internal_static_envoy_config_filter_fault_v2_FaultDelay_fieldAccessorTable.ensureFieldAccessorsInitialized(FaultDelay.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
    public FaultDelaySecifierCase getFaultDelaySecifierCase() {
        return FaultDelaySecifierCase.forNumber(this.faultDelaySecifierCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
    @Deprecated
    public FaultDelayType getType() {
        FaultDelayType faultDelayTypeValueOf = FaultDelayType.valueOf(this.type_);
        return faultDelayTypeValueOf == null ? FaultDelayType.UNRECOGNIZED : faultDelayTypeValueOf;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
    public Duration getFixedDelay() {
        if (this.faultDelaySecifierCase_ == 3) {
            return (Duration) this.faultDelaySecifier_;
        }
        return Duration.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
    public DurationOrBuilder getFixedDelayOrBuilder() {
        if (this.faultDelaySecifierCase_ == 3) {
            return (Duration) this.faultDelaySecifier_;
        }
        return Duration.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
    public HeaderDelay getHeaderDelay() {
        if (this.faultDelaySecifierCase_ == 5) {
            return (HeaderDelay) this.faultDelaySecifier_;
        }
        return HeaderDelay.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
    public HeaderDelayOrBuilder getHeaderDelayOrBuilder() {
        if (this.faultDelaySecifierCase_ == 5) {
            return (HeaderDelay) this.faultDelaySecifier_;
        }
        return HeaderDelay.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
    public FractionalPercent getPercentage() {
        FractionalPercent fractionalPercent = this.percentage_;
        return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
    public FractionalPercentOrBuilder getPercentageOrBuilder() {
        return getPercentage();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.type_ != FaultDelayType.FIXED.getNumber()) {
            codedOutputStream.writeEnum(1, this.type_);
        }
        if (this.faultDelaySecifierCase_ == 3) {
            codedOutputStream.writeMessage(3, (Duration) this.faultDelaySecifier_);
        }
        if (this.percentage_ != null) {
            codedOutputStream.writeMessage(4, getPercentage());
        }
        if (this.faultDelaySecifierCase_ == 5) {
            codedOutputStream.writeMessage(5, (HeaderDelay) this.faultDelaySecifier_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeEnumSize = this.type_ != FaultDelayType.FIXED.getNumber() ? CodedOutputStream.computeEnumSize(1, this.type_) : 0;
        if (this.faultDelaySecifierCase_ == 3) {
            iComputeEnumSize += CodedOutputStream.computeMessageSize(3, (Duration) this.faultDelaySecifier_);
        }
        if (this.percentage_ != null) {
            iComputeEnumSize += CodedOutputStream.computeMessageSize(4, getPercentage());
        }
        if (this.faultDelaySecifierCase_ == 5) {
            iComputeEnumSize += CodedOutputStream.computeMessageSize(5, (HeaderDelay) this.faultDelaySecifier_);
        }
        int serializedSize = iComputeEnumSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FaultDelay)) {
            return super.equals(obj);
        }
        FaultDelay faultDelay = (FaultDelay) obj;
        if (this.type_ != faultDelay.type_ || hasPercentage() != faultDelay.hasPercentage()) {
            return false;
        }
        if ((hasPercentage() && !getPercentage().equals(faultDelay.getPercentage())) || !getFaultDelaySecifierCase().equals(faultDelay.getFaultDelaySecifierCase())) {
            return false;
        }
        int i = this.faultDelaySecifierCase_;
        if (i == 3) {
            if (!getFixedDelay().equals(faultDelay.getFixedDelay())) {
                return false;
            }
        } else if (i == 5 && !getHeaderDelay().equals(faultDelay.getHeaderDelay())) {
            return false;
        }
        return this.unknownFields.equals(faultDelay.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.type_;
        if (hasPercentage()) {
            iHashCode2 = (((iHashCode2 * 37) + 4) * 53) + getPercentage().hashCode();
        }
        int i2 = this.faultDelaySecifierCase_;
        if (i2 == 3) {
            i = ((iHashCode2 * 37) + 3) * 53;
            iHashCode = getFixedDelay().hashCode();
        } else {
            if (i2 == 5) {
                i = ((iHashCode2 * 37) + 5) * 53;
                iHashCode = getHeaderDelay().hashCode();
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
    public Builder m25721newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m25724toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum FaultDelayType implements ProtocolMessageEnum {
        FIXED(0),
        UNRECOGNIZED(-1);

        public static final int FIXED_VALUE = 0;
        private static final Internal.EnumLiteMap<FaultDelayType> internalValueMap = new Internal.EnumLiteMap<FaultDelayType>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay.FaultDelayType.1
            public FaultDelayType findValueByNumber(int i) {
                return FaultDelayType.forNumber(i);
            }
        };
        private static final FaultDelayType[] VALUES = values();
        private final int value;

        FaultDelayType(int i) {
            this.value = i;
        }

        public static FaultDelayType forNumber(int i) {
            if (i != 0) {
                return null;
            }
            return FIXED;
        }

        public static Internal.EnumLiteMap<FaultDelayType> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static FaultDelayType valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) FaultDelay.getDescriptor().getEnumTypes().get(0);
        }

        public static FaultDelayType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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

    public enum FaultDelaySecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        FIXED_DELAY(3),
        HEADER_DELAY(5),
        FAULTDELAYSECIFIER_NOT_SET(0);

        private final int value;

        FaultDelaySecifierCase(int i) {
            this.value = i;
        }

        public static FaultDelaySecifierCase forNumber(int i) {
            if (i == 0) {
                return FAULTDELAYSECIFIER_NOT_SET;
            }
            if (i == 3) {
                return FIXED_DELAY;
            }
            if (i != 5) {
                return null;
            }
            return HEADER_DELAY;
        }

        @Deprecated
        public static FaultDelaySecifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public interface HeaderDelayOrBuilder extends MessageOrBuilder {
    }

    public static final class HeaderDelay extends GeneratedMessageV3 implements HeaderDelayOrBuilder {
        private static final HeaderDelay DEFAULT_INSTANCE = new HeaderDelay();
        private static final Parser<HeaderDelay> PARSER = new AbstractParser<HeaderDelay>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay.HeaderDelay.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public HeaderDelay m25773parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new HeaderDelay(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private HeaderDelay(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private HeaderDelay() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private HeaderDelay(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag == 0 || !parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                z = true;
                            }
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

        public static HeaderDelay getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<HeaderDelay> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return FaultProto.internal_static_envoy_config_filter_fault_v2_FaultDelay_HeaderDelay_descriptor;
        }

        public static HeaderDelay parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (HeaderDelay) PARSER.parseFrom(byteBuffer);
        }

        public static HeaderDelay parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HeaderDelay) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static HeaderDelay parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (HeaderDelay) PARSER.parseFrom(byteString);
        }

        public static HeaderDelay parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HeaderDelay) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static HeaderDelay parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (HeaderDelay) PARSER.parseFrom(bArr);
        }

        public static HeaderDelay parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HeaderDelay) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static HeaderDelay parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static HeaderDelay parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static HeaderDelay parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static HeaderDelay parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static HeaderDelay parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static HeaderDelay parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m25771toBuilder();
        }

        public static Builder newBuilder(HeaderDelay headerDelay) {
            return DEFAULT_INSTANCE.m25771toBuilder().mergeFrom(headerDelay);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HeaderDelay m25766getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<HeaderDelay> getParserForType() {
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

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new HeaderDelay();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return FaultProto.internal_static_envoy_config_filter_fault_v2_FaultDelay_HeaderDelay_fieldAccessorTable.ensureFieldAccessorsInitialized(HeaderDelay.class, Builder.class);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof HeaderDelay) {
                return this.unknownFields.equals(((HeaderDelay) obj).unknownFields);
            }
            return super.equals(obj);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25768newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25771toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HeaderDelayOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return FaultProto.internal_static_envoy_config_filter_fault_v2_FaultDelay_HeaderDelay_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return FaultProto.internal_static_envoy_config_filter_fault_v2_FaultDelay_HeaderDelay_fieldAccessorTable.ensureFieldAccessorsInitialized(HeaderDelay.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = HeaderDelay.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m25782clear() {
                super.clear();
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return FaultProto.internal_static_envoy_config_filter_fault_v2_FaultDelay_HeaderDelay_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HeaderDelay m25795getDefaultInstanceForType() {
                return HeaderDelay.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HeaderDelay m25776build() throws UninitializedMessageException {
                HeaderDelay headerDelayM25778buildPartial = m25778buildPartial();
                if (headerDelayM25778buildPartial.isInitialized()) {
                    return headerDelayM25778buildPartial;
                }
                throw newUninitializedMessageException(headerDelayM25778buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HeaderDelay m25778buildPartial() {
                HeaderDelay headerDelay = new HeaderDelay(this);
                onBuilt();
                return headerDelay;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m25794clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m25806setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m25784clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m25787clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m25808setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m25774addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m25799mergeFrom(Message message) {
                if (message instanceof HeaderDelay) {
                    return mergeFrom((HeaderDelay) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(HeaderDelay headerDelay) {
                if (headerDelay == HeaderDelay.getDefaultInstance()) {
                    return this;
                }
                m25804mergeUnknownFields(headerDelay.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay.HeaderDelay.Builder m25800mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay.HeaderDelay.access$500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay$HeaderDelay r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay.HeaderDelay) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay$HeaderDelay r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay.HeaderDelay) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay.HeaderDelay.Builder.m25800mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay$HeaderDelay$Builder");
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m25810setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m25804mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FaultDelayOrBuilder {
        private int faultDelaySecifierCase_;
        private Object faultDelaySecifier_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> fixedDelayBuilder_;
        private SingleFieldBuilderV3<HeaderDelay, HeaderDelay.Builder, HeaderDelayOrBuilder> headerDelayBuilder_;
        private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> percentageBuilder_;
        private FractionalPercent percentage_;
        private int type_;

        private Builder() {
            this.faultDelaySecifierCase_ = 0;
            this.type_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.faultDelaySecifierCase_ = 0;
            this.type_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return FaultProto.internal_static_envoy_config_filter_fault_v2_FaultDelay_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
        @Deprecated
        public int getTypeValue() {
            return this.type_;
        }

        @Deprecated
        public Builder setTypeValue(int i) {
            this.type_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
        public boolean hasFixedDelay() {
            return this.faultDelaySecifierCase_ == 3;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
        public boolean hasHeaderDelay() {
            return this.faultDelaySecifierCase_ == 5;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
        public boolean hasPercentage() {
            return (this.percentageBuilder_ == null && this.percentage_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return FaultProto.internal_static_envoy_config_filter_fault_v2_FaultDelay_fieldAccessorTable.ensureFieldAccessorsInitialized(FaultDelay.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = FaultDelay.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25735clear() {
            super.clear();
            this.type_ = 0;
            if (this.percentageBuilder_ == null) {
                this.percentage_ = null;
            } else {
                this.percentage_ = null;
                this.percentageBuilder_ = null;
            }
            this.faultDelaySecifierCase_ = 0;
            this.faultDelaySecifier_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return FaultProto.internal_static_envoy_config_filter_fault_v2_FaultDelay_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FaultDelay m25748getDefaultInstanceForType() {
            return FaultDelay.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FaultDelay m25729build() throws UninitializedMessageException {
            FaultDelay faultDelayM25731buildPartial = m25731buildPartial();
            if (faultDelayM25731buildPartial.isInitialized()) {
                return faultDelayM25731buildPartial;
            }
            throw newUninitializedMessageException(faultDelayM25731buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FaultDelay m25731buildPartial() {
            FaultDelay faultDelay = new FaultDelay(this);
            faultDelay.type_ = this.type_;
            if (this.faultDelaySecifierCase_ == 3) {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.fixedDelayBuilder_;
                if (singleFieldBuilderV3 == null) {
                    faultDelay.faultDelaySecifier_ = this.faultDelaySecifier_;
                } else {
                    faultDelay.faultDelaySecifier_ = singleFieldBuilderV3.build();
                }
            }
            if (this.faultDelaySecifierCase_ == 5) {
                SingleFieldBuilderV3<HeaderDelay, HeaderDelay.Builder, HeaderDelayOrBuilder> singleFieldBuilderV32 = this.headerDelayBuilder_;
                if (singleFieldBuilderV32 == null) {
                    faultDelay.faultDelaySecifier_ = this.faultDelaySecifier_;
                } else {
                    faultDelay.faultDelaySecifier_ = singleFieldBuilderV32.build();
                }
            }
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV33 = this.percentageBuilder_;
            if (singleFieldBuilderV33 == null) {
                faultDelay.percentage_ = this.percentage_;
            } else {
                faultDelay.percentage_ = singleFieldBuilderV33.build();
            }
            faultDelay.faultDelaySecifierCase_ = this.faultDelaySecifierCase_;
            onBuilt();
            return faultDelay;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25747clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25759setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25737clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25740clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25761setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25727addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25752mergeFrom(Message message) {
            if (message instanceof FaultDelay) {
                return mergeFrom((FaultDelay) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(FaultDelay faultDelay) {
            if (faultDelay == FaultDelay.getDefaultInstance()) {
                return this;
            }
            if (faultDelay.type_ != 0) {
                setTypeValue(faultDelay.getTypeValue());
            }
            if (faultDelay.hasPercentage()) {
                mergePercentage(faultDelay.getPercentage());
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$filter$fault$v2$FaultDelay$FaultDelaySecifierCase[faultDelay.getFaultDelaySecifierCase().ordinal()];
            if (i == 1) {
                mergeFixedDelay(faultDelay.getFixedDelay());
            } else if (i == 2) {
                mergeHeaderDelay(faultDelay.getHeaderDelay());
            }
            m25757mergeUnknownFields(faultDelay.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay.Builder m25753mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay.access$1600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay.Builder.m25753mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
        public FaultDelaySecifierCase getFaultDelaySecifierCase() {
            return FaultDelaySecifierCase.forNumber(this.faultDelaySecifierCase_);
        }

        public Builder clearFaultDelaySecifier() {
            this.faultDelaySecifierCase_ = 0;
            this.faultDelaySecifier_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
        @Deprecated
        public FaultDelayType getType() {
            FaultDelayType faultDelayTypeValueOf = FaultDelayType.valueOf(this.type_);
            return faultDelayTypeValueOf == null ? FaultDelayType.UNRECOGNIZED : faultDelayTypeValueOf;
        }

        @Deprecated
        public Builder setType(FaultDelayType faultDelayType) {
            faultDelayType.getClass();
            this.type_ = faultDelayType.getNumber();
            onChanged();
            return this;
        }

        @Deprecated
        public Builder clearType() {
            this.type_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
        public Duration getFixedDelay() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.fixedDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.faultDelaySecifierCase_ == 3) {
                    return (Duration) this.faultDelaySecifier_;
                }
                return Duration.getDefaultInstance();
            }
            if (this.faultDelaySecifierCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return Duration.getDefaultInstance();
        }

        public Builder setFixedDelay(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.fixedDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.faultDelaySecifier_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            this.faultDelaySecifierCase_ = 3;
            return this;
        }

        public Builder setFixedDelay(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.fixedDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.faultDelaySecifier_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.faultDelaySecifierCase_ = 3;
            return this;
        }

        public Builder mergeFixedDelay(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.fixedDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.faultDelaySecifierCase_ != 3 || this.faultDelaySecifier_ == Duration.getDefaultInstance()) {
                    this.faultDelaySecifier_ = duration;
                } else {
                    this.faultDelaySecifier_ = Duration.newBuilder((Duration) this.faultDelaySecifier_).mergeFrom(duration).buildPartial();
                }
                onChanged();
            } else {
                if (this.faultDelaySecifierCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(duration);
                }
                this.fixedDelayBuilder_.setMessage(duration);
            }
            this.faultDelaySecifierCase_ = 3;
            return this;
        }

        public Builder clearFixedDelay() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.fixedDelayBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.faultDelaySecifierCase_ == 3) {
                    this.faultDelaySecifierCase_ = 0;
                    this.faultDelaySecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.faultDelaySecifierCase_ == 3) {
                this.faultDelaySecifierCase_ = 0;
                this.faultDelaySecifier_ = null;
                onChanged();
            }
            return this;
        }

        public Duration.Builder getFixedDelayBuilder() {
            return getFixedDelayFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
        public DurationOrBuilder getFixedDelayOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3;
            int i = this.faultDelaySecifierCase_;
            if (i == 3 && (singleFieldBuilderV3 = this.fixedDelayBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 3) {
                return (Duration) this.faultDelaySecifier_;
            }
            return Duration.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getFixedDelayFieldBuilder() {
            if (this.fixedDelayBuilder_ == null) {
                if (this.faultDelaySecifierCase_ != 3) {
                    this.faultDelaySecifier_ = Duration.getDefaultInstance();
                }
                this.fixedDelayBuilder_ = new SingleFieldBuilderV3<>((Duration) this.faultDelaySecifier_, getParentForChildren(), isClean());
                this.faultDelaySecifier_ = null;
            }
            this.faultDelaySecifierCase_ = 3;
            onChanged();
            return this.fixedDelayBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
        public HeaderDelay getHeaderDelay() {
            SingleFieldBuilderV3<HeaderDelay, HeaderDelay.Builder, HeaderDelayOrBuilder> singleFieldBuilderV3 = this.headerDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.faultDelaySecifierCase_ == 5) {
                    return (HeaderDelay) this.faultDelaySecifier_;
                }
                return HeaderDelay.getDefaultInstance();
            }
            if (this.faultDelaySecifierCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return HeaderDelay.getDefaultInstance();
        }

        public Builder setHeaderDelay(HeaderDelay headerDelay) {
            SingleFieldBuilderV3<HeaderDelay, HeaderDelay.Builder, HeaderDelayOrBuilder> singleFieldBuilderV3 = this.headerDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                headerDelay.getClass();
                this.faultDelaySecifier_ = headerDelay;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(headerDelay);
            }
            this.faultDelaySecifierCase_ = 5;
            return this;
        }

        public Builder setHeaderDelay(HeaderDelay.Builder builder) {
            SingleFieldBuilderV3<HeaderDelay, HeaderDelay.Builder, HeaderDelayOrBuilder> singleFieldBuilderV3 = this.headerDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.faultDelaySecifier_ = builder.m25776build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m25776build());
            }
            this.faultDelaySecifierCase_ = 5;
            return this;
        }

        public Builder mergeHeaderDelay(HeaderDelay headerDelay) {
            SingleFieldBuilderV3<HeaderDelay, HeaderDelay.Builder, HeaderDelayOrBuilder> singleFieldBuilderV3 = this.headerDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.faultDelaySecifierCase_ != 5 || this.faultDelaySecifier_ == HeaderDelay.getDefaultInstance()) {
                    this.faultDelaySecifier_ = headerDelay;
                } else {
                    this.faultDelaySecifier_ = HeaderDelay.newBuilder((HeaderDelay) this.faultDelaySecifier_).mergeFrom(headerDelay).m25778buildPartial();
                }
                onChanged();
            } else {
                if (this.faultDelaySecifierCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(headerDelay);
                }
                this.headerDelayBuilder_.setMessage(headerDelay);
            }
            this.faultDelaySecifierCase_ = 5;
            return this;
        }

        public Builder clearHeaderDelay() {
            SingleFieldBuilderV3<HeaderDelay, HeaderDelay.Builder, HeaderDelayOrBuilder> singleFieldBuilderV3 = this.headerDelayBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.faultDelaySecifierCase_ == 5) {
                    this.faultDelaySecifierCase_ = 0;
                    this.faultDelaySecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.faultDelaySecifierCase_ == 5) {
                this.faultDelaySecifierCase_ = 0;
                this.faultDelaySecifier_ = null;
                onChanged();
            }
            return this;
        }

        public HeaderDelay.Builder getHeaderDelayBuilder() {
            return getHeaderDelayFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
        public HeaderDelayOrBuilder getHeaderDelayOrBuilder() {
            SingleFieldBuilderV3<HeaderDelay, HeaderDelay.Builder, HeaderDelayOrBuilder> singleFieldBuilderV3;
            int i = this.faultDelaySecifierCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.headerDelayBuilder_) != null) {
                return (HeaderDelayOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (HeaderDelay) this.faultDelaySecifier_;
            }
            return HeaderDelay.getDefaultInstance();
        }

        private SingleFieldBuilderV3<HeaderDelay, HeaderDelay.Builder, HeaderDelayOrBuilder> getHeaderDelayFieldBuilder() {
            if (this.headerDelayBuilder_ == null) {
                if (this.faultDelaySecifierCase_ != 5) {
                    this.faultDelaySecifier_ = HeaderDelay.getDefaultInstance();
                }
                this.headerDelayBuilder_ = new SingleFieldBuilderV3<>((HeaderDelay) this.faultDelaySecifier_, getParentForChildren(), isClean());
                this.faultDelaySecifier_ = null;
            }
            this.faultDelaySecifierCase_ = 5;
            onChanged();
            return this.headerDelayBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
        public FractionalPercent getPercentage() {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.percentageBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            FractionalPercent fractionalPercent = this.percentage_;
            return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
        }

        public Builder setPercentage(FractionalPercent fractionalPercent) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.percentageBuilder_;
            if (singleFieldBuilderV3 == null) {
                fractionalPercent.getClass();
                this.percentage_ = fractionalPercent;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(fractionalPercent);
            }
            return this;
        }

        public Builder setPercentage(FractionalPercent.Builder builder) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.percentageBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.percentage_ = builder.m32864build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m32864build());
            }
            return this;
        }

        public Builder mergePercentage(FractionalPercent fractionalPercent) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.percentageBuilder_;
            if (singleFieldBuilderV3 == null) {
                FractionalPercent fractionalPercent2 = this.percentage_;
                if (fractionalPercent2 != null) {
                    this.percentage_ = FractionalPercent.newBuilder(fractionalPercent2).mergeFrom(fractionalPercent).m32866buildPartial();
                } else {
                    this.percentage_ = fractionalPercent;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(fractionalPercent);
            }
            return this;
        }

        public Builder clearPercentage() {
            if (this.percentageBuilder_ == null) {
                this.percentage_ = null;
                onChanged();
            } else {
                this.percentage_ = null;
                this.percentageBuilder_ = null;
            }
            return this;
        }

        public FractionalPercent.Builder getPercentageBuilder() {
            onChanged();
            return getPercentageFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelayOrBuilder
        public FractionalPercentOrBuilder getPercentageOrBuilder() {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.percentageBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (FractionalPercentOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            FractionalPercent fractionalPercent = this.percentage_;
            return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
        }

        private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> getPercentageFieldBuilder() {
            if (this.percentageBuilder_ == null) {
                this.percentageBuilder_ = new SingleFieldBuilderV3<>(getPercentage(), getParentForChildren(), isClean());
                this.percentage_ = null;
            }
            return this.percentageBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m25763setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m25757mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultDelay$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$filter$fault$v2$FaultDelay$FaultDelaySecifierCase;

        static {
            int[] iArr = new int[FaultDelaySecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$filter$fault$v2$FaultDelay$FaultDelaySecifierCase = iArr;
            try {
                iArr[FaultDelaySecifierCase.FIXED_DELAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$filter$fault$v2$FaultDelay$FaultDelaySecifierCase[FaultDelaySecifierCase.HEADER_DELAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$filter$fault$v2$FaultDelay$FaultDelaySecifierCase[FaultDelaySecifierCase.FAULTDELAYSECIFIER_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
