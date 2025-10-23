package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2;

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
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercentOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class FaultAbort extends GeneratedMessageV3 implements FaultAbortOrBuilder {
    public static final int HEADER_ABORT_FIELD_NUMBER = 4;
    public static final int HTTP_STATUS_FIELD_NUMBER = 2;
    public static final int PERCENTAGE_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final FaultAbort DEFAULT_INSTANCE = new FaultAbort();
    private static final Parser<FaultAbort> PARSER = new AbstractParser<FaultAbort>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public FaultAbort m25957parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new FaultAbort(codedInputStream, extensionRegistryLite);
        }
    };
    private int errorTypeCase_;
    private Object errorType_;
    private byte memoizedIsInitialized;
    private FractionalPercent percentage_;

    private FaultAbort(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.errorTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private FaultAbort() {
        this.errorTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private FaultAbort(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag != 16) {
                            if (tag == 26) {
                                FractionalPercent fractionalPercent = this.percentage_;
                                FractionalPercent.Builder builderM32859toBuilder = fractionalPercent != null ? fractionalPercent.m32859toBuilder() : null;
                                FractionalPercent fractionalPercent2 = (FractionalPercent) codedInputStream.readMessage(FractionalPercent.parser(), extensionRegistryLite);
                                this.percentage_ = fractionalPercent2;
                                if (builderM32859toBuilder != null) {
                                    builderM32859toBuilder.mergeFrom(fractionalPercent2);
                                    this.percentage_ = builderM32859toBuilder.m32866buildPartial();
                                }
                            } else if (tag == 34) {
                                HeaderAbort.Builder builderM26001toBuilder = this.errorTypeCase_ == 4 ? ((HeaderAbort) this.errorType_).m26001toBuilder() : null;
                                MessageLite message = codedInputStream.readMessage(HeaderAbort.parser(), extensionRegistryLite);
                                this.errorType_ = message;
                                if (builderM26001toBuilder != null) {
                                    builderM26001toBuilder.mergeFrom((HeaderAbort) message);
                                    this.errorType_ = builderM26001toBuilder.m26008buildPartial();
                                }
                                this.errorTypeCase_ = 4;
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.errorTypeCase_ = 2;
                            this.errorType_ = Integer.valueOf(codedInputStream.readUInt32());
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

    public static FaultAbort getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FaultAbort> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return FaultProto.internal_static_envoy_config_filter_http_fault_v2_FaultAbort_descriptor;
    }

    public static FaultAbort parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (FaultAbort) PARSER.parseFrom(byteBuffer);
    }

    public static FaultAbort parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FaultAbort) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static FaultAbort parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (FaultAbort) PARSER.parseFrom(byteString);
    }

    public static FaultAbort parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FaultAbort) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static FaultAbort parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (FaultAbort) PARSER.parseFrom(bArr);
    }

    public static FaultAbort parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FaultAbort) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static FaultAbort parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static FaultAbort parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static FaultAbort parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static FaultAbort parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static FaultAbort parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static FaultAbort parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m25955toBuilder();
    }

    public static Builder newBuilder(FaultAbort faultAbort) {
        return DEFAULT_INSTANCE.m25955toBuilder().mergeFrom(faultAbort);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public FaultAbort m25950getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<FaultAbort> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
    public boolean hasHeaderAbort() {
        return this.errorTypeCase_ == 4;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
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
        return new FaultAbort();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return FaultProto.internal_static_envoy_config_filter_http_fault_v2_FaultAbort_fieldAccessorTable.ensureFieldAccessorsInitialized(FaultAbort.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
    public ErrorTypeCase getErrorTypeCase() {
        return ErrorTypeCase.forNumber(this.errorTypeCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
    public int getHttpStatus() {
        if (this.errorTypeCase_ == 2) {
            return ((Integer) this.errorType_).intValue();
        }
        return 0;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
    public HeaderAbort getHeaderAbort() {
        if (this.errorTypeCase_ == 4) {
            return (HeaderAbort) this.errorType_;
        }
        return HeaderAbort.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
    public HeaderAbortOrBuilder getHeaderAbortOrBuilder() {
        if (this.errorTypeCase_ == 4) {
            return (HeaderAbort) this.errorType_;
        }
        return HeaderAbort.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
    public FractionalPercent getPercentage() {
        FractionalPercent fractionalPercent = this.percentage_;
        return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
    public FractionalPercentOrBuilder getPercentageOrBuilder() {
        return getPercentage();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.errorTypeCase_ == 2) {
            codedOutputStream.writeUInt32(2, ((Integer) this.errorType_).intValue());
        }
        if (this.percentage_ != null) {
            codedOutputStream.writeMessage(3, getPercentage());
        }
        if (this.errorTypeCase_ == 4) {
            codedOutputStream.writeMessage(4, (HeaderAbort) this.errorType_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeUInt32Size = this.errorTypeCase_ == 2 ? CodedOutputStream.computeUInt32Size(2, ((Integer) this.errorType_).intValue()) : 0;
        if (this.percentage_ != null) {
            iComputeUInt32Size += CodedOutputStream.computeMessageSize(3, getPercentage());
        }
        if (this.errorTypeCase_ == 4) {
            iComputeUInt32Size += CodedOutputStream.computeMessageSize(4, (HeaderAbort) this.errorType_);
        }
        int serializedSize = iComputeUInt32Size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FaultAbort)) {
            return super.equals(obj);
        }
        FaultAbort faultAbort = (FaultAbort) obj;
        if (hasPercentage() != faultAbort.hasPercentage()) {
            return false;
        }
        if ((hasPercentage() && !getPercentage().equals(faultAbort.getPercentage())) || !getErrorTypeCase().equals(faultAbort.getErrorTypeCase())) {
            return false;
        }
        int i = this.errorTypeCase_;
        if (i == 2) {
            if (getHttpStatus() != faultAbort.getHttpStatus()) {
                return false;
            }
        } else if (i == 4 && !getHeaderAbort().equals(faultAbort.getHeaderAbort())) {
            return false;
        }
        return this.unknownFields.equals(faultAbort.unknownFields);
    }

    public int hashCode() {
        int i;
        int httpStatus;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasPercentage()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getPercentage().hashCode();
        }
        int i2 = this.errorTypeCase_;
        if (i2 == 2) {
            i = ((iHashCode * 37) + 2) * 53;
            httpStatus = getHttpStatus();
        } else {
            if (i2 == 4) {
                i = ((iHashCode * 37) + 4) * 53;
                httpStatus = getHeaderAbort().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }
        iHashCode = i + httpStatus;
        int iHashCode22 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode22;
        return iHashCode22;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m25952newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m25955toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ErrorTypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        HTTP_STATUS(2),
        HEADER_ABORT(4),
        ERRORTYPE_NOT_SET(0);

        private final int value;

        ErrorTypeCase(int i) {
            this.value = i;
        }

        public static ErrorTypeCase forNumber(int i) {
            if (i == 0) {
                return ERRORTYPE_NOT_SET;
            }
            if (i == 2) {
                return HTTP_STATUS;
            }
            if (i != 4) {
                return null;
            }
            return HEADER_ABORT;
        }

        @Deprecated
        public static ErrorTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public interface HeaderAbortOrBuilder extends MessageOrBuilder {
    }

    public static final class HeaderAbort extends GeneratedMessageV3 implements HeaderAbortOrBuilder {
        private static final HeaderAbort DEFAULT_INSTANCE = new HeaderAbort();
        private static final Parser<HeaderAbort> PARSER = new AbstractParser<HeaderAbort>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort.HeaderAbort.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public HeaderAbort m26003parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new HeaderAbort(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private HeaderAbort(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private HeaderAbort() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private HeaderAbort(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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

        public static HeaderAbort getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<HeaderAbort> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return FaultProto.internal_static_envoy_config_filter_http_fault_v2_FaultAbort_HeaderAbort_descriptor;
        }

        public static HeaderAbort parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (HeaderAbort) PARSER.parseFrom(byteBuffer);
        }

        public static HeaderAbort parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HeaderAbort) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static HeaderAbort parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (HeaderAbort) PARSER.parseFrom(byteString);
        }

        public static HeaderAbort parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HeaderAbort) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static HeaderAbort parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (HeaderAbort) PARSER.parseFrom(bArr);
        }

        public static HeaderAbort parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HeaderAbort) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static HeaderAbort parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static HeaderAbort parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static HeaderAbort parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static HeaderAbort parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static HeaderAbort parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static HeaderAbort parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m26001toBuilder();
        }

        public static Builder newBuilder(HeaderAbort headerAbort) {
            return DEFAULT_INSTANCE.m26001toBuilder().mergeFrom(headerAbort);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HeaderAbort m25996getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<HeaderAbort> getParserForType() {
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
            return new HeaderAbort();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return FaultProto.internal_static_envoy_config_filter_http_fault_v2_FaultAbort_HeaderAbort_fieldAccessorTable.ensureFieldAccessorsInitialized(HeaderAbort.class, Builder.class);
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
            if (obj instanceof HeaderAbort) {
                return this.unknownFields.equals(((HeaderAbort) obj).unknownFields);
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
        public Builder m25998newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m26001toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HeaderAbortOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return FaultProto.internal_static_envoy_config_filter_http_fault_v2_FaultAbort_HeaderAbort_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return FaultProto.internal_static_envoy_config_filter_http_fault_v2_FaultAbort_HeaderAbort_fieldAccessorTable.ensureFieldAccessorsInitialized(HeaderAbort.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = HeaderAbort.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m26012clear() {
                super.clear();
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return FaultProto.internal_static_envoy_config_filter_http_fault_v2_FaultAbort_HeaderAbort_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HeaderAbort m26025getDefaultInstanceForType() {
                return HeaderAbort.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HeaderAbort m26006build() throws UninitializedMessageException {
                HeaderAbort headerAbortM26008buildPartial = m26008buildPartial();
                if (headerAbortM26008buildPartial.isInitialized()) {
                    return headerAbortM26008buildPartial;
                }
                throw newUninitializedMessageException(headerAbortM26008buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HeaderAbort m26008buildPartial() {
                HeaderAbort headerAbort = new HeaderAbort(this);
                onBuilt();
                return headerAbort;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m26024clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m26036setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m26014clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m26017clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m26038setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m26004addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m26029mergeFrom(Message message) {
                if (message instanceof HeaderAbort) {
                    return mergeFrom((HeaderAbort) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(HeaderAbort headerAbort) {
                if (headerAbort == HeaderAbort.getDefaultInstance()) {
                    return this;
                }
                m26034mergeUnknownFields(headerAbort.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort.HeaderAbort.Builder m26030mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort.HeaderAbort.access$500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort$HeaderAbort r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort.HeaderAbort) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort$HeaderAbort r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort.HeaderAbort) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort.HeaderAbort.Builder.m26030mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort$HeaderAbort$Builder");
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m26040setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m26034mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FaultAbortOrBuilder {
        private int errorTypeCase_;
        private Object errorType_;
        private SingleFieldBuilderV3<HeaderAbort, HeaderAbort.Builder, HeaderAbortOrBuilder> headerAbortBuilder_;
        private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> percentageBuilder_;
        private FractionalPercent percentage_;

        private Builder() {
            this.errorTypeCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.errorTypeCase_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return FaultProto.internal_static_envoy_config_filter_http_fault_v2_FaultAbort_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
        public boolean hasHeaderAbort() {
            return this.errorTypeCase_ == 4;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
        public boolean hasPercentage() {
            return (this.percentageBuilder_ == null && this.percentage_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return FaultProto.internal_static_envoy_config_filter_http_fault_v2_FaultAbort_fieldAccessorTable.ensureFieldAccessorsInitialized(FaultAbort.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = FaultAbort.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25966clear() {
            super.clear();
            if (this.percentageBuilder_ == null) {
                this.percentage_ = null;
            } else {
                this.percentage_ = null;
                this.percentageBuilder_ = null;
            }
            this.errorTypeCase_ = 0;
            this.errorType_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return FaultProto.internal_static_envoy_config_filter_http_fault_v2_FaultAbort_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FaultAbort m25979getDefaultInstanceForType() {
            return FaultAbort.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FaultAbort m25960build() throws UninitializedMessageException {
            FaultAbort faultAbortM25962buildPartial = m25962buildPartial();
            if (faultAbortM25962buildPartial.isInitialized()) {
                return faultAbortM25962buildPartial;
            }
            throw newUninitializedMessageException(faultAbortM25962buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FaultAbort m25962buildPartial() {
            FaultAbort faultAbort = new FaultAbort(this);
            if (this.errorTypeCase_ == 2) {
                faultAbort.errorType_ = this.errorType_;
            }
            if (this.errorTypeCase_ == 4) {
                SingleFieldBuilderV3<HeaderAbort, HeaderAbort.Builder, HeaderAbortOrBuilder> singleFieldBuilderV3 = this.headerAbortBuilder_;
                if (singleFieldBuilderV3 == null) {
                    faultAbort.errorType_ = this.errorType_;
                } else {
                    faultAbort.errorType_ = singleFieldBuilderV3.build();
                }
            }
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV32 = this.percentageBuilder_;
            if (singleFieldBuilderV32 == null) {
                faultAbort.percentage_ = this.percentage_;
            } else {
                faultAbort.percentage_ = singleFieldBuilderV32.build();
            }
            faultAbort.errorTypeCase_ = this.errorTypeCase_;
            onBuilt();
            return faultAbort;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25978clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25990setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25968clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25971clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25992setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25958addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m25983mergeFrom(Message message) {
            if (message instanceof FaultAbort) {
                return mergeFrom((FaultAbort) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(FaultAbort faultAbort) {
            if (faultAbort == FaultAbort.getDefaultInstance()) {
                return this;
            }
            if (faultAbort.hasPercentage()) {
                mergePercentage(faultAbort.getPercentage());
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$filter$http$fault$v2$FaultAbort$ErrorTypeCase[faultAbort.getErrorTypeCase().ordinal()];
            if (i == 1) {
                setHttpStatus(faultAbort.getHttpStatus());
            } else if (i == 2) {
                mergeHeaderAbort(faultAbort.getHeaderAbort());
            }
            m25988mergeUnknownFields(faultAbort.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort.Builder m25984mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort.access$1500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort.Builder.m25984mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
        public ErrorTypeCase getErrorTypeCase() {
            return ErrorTypeCase.forNumber(this.errorTypeCase_);
        }

        public Builder clearErrorType() {
            this.errorTypeCase_ = 0;
            this.errorType_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
        public int getHttpStatus() {
            if (this.errorTypeCase_ == 2) {
                return ((Integer) this.errorType_).intValue();
            }
            return 0;
        }

        public Builder setHttpStatus(int i) {
            this.errorTypeCase_ = 2;
            this.errorType_ = Integer.valueOf(i);
            onChanged();
            return this;
        }

        public Builder clearHttpStatus() {
            if (this.errorTypeCase_ == 2) {
                this.errorTypeCase_ = 0;
                this.errorType_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
        public HeaderAbort getHeaderAbort() {
            SingleFieldBuilderV3<HeaderAbort, HeaderAbort.Builder, HeaderAbortOrBuilder> singleFieldBuilderV3 = this.headerAbortBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.errorTypeCase_ == 4) {
                    return (HeaderAbort) this.errorType_;
                }
                return HeaderAbort.getDefaultInstance();
            }
            if (this.errorTypeCase_ == 4) {
                return singleFieldBuilderV3.getMessage();
            }
            return HeaderAbort.getDefaultInstance();
        }

        public Builder setHeaderAbort(HeaderAbort headerAbort) {
            SingleFieldBuilderV3<HeaderAbort, HeaderAbort.Builder, HeaderAbortOrBuilder> singleFieldBuilderV3 = this.headerAbortBuilder_;
            if (singleFieldBuilderV3 == null) {
                headerAbort.getClass();
                this.errorType_ = headerAbort;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(headerAbort);
            }
            this.errorTypeCase_ = 4;
            return this;
        }

        public Builder setHeaderAbort(HeaderAbort.Builder builder) {
            SingleFieldBuilderV3<HeaderAbort, HeaderAbort.Builder, HeaderAbortOrBuilder> singleFieldBuilderV3 = this.headerAbortBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.errorType_ = builder.m26006build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m26006build());
            }
            this.errorTypeCase_ = 4;
            return this;
        }

        public Builder mergeHeaderAbort(HeaderAbort headerAbort) {
            SingleFieldBuilderV3<HeaderAbort, HeaderAbort.Builder, HeaderAbortOrBuilder> singleFieldBuilderV3 = this.headerAbortBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.errorTypeCase_ != 4 || this.errorType_ == HeaderAbort.getDefaultInstance()) {
                    this.errorType_ = headerAbort;
                } else {
                    this.errorType_ = HeaderAbort.newBuilder((HeaderAbort) this.errorType_).mergeFrom(headerAbort).m26008buildPartial();
                }
                onChanged();
            } else {
                if (this.errorTypeCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(headerAbort);
                }
                this.headerAbortBuilder_.setMessage(headerAbort);
            }
            this.errorTypeCase_ = 4;
            return this;
        }

        public Builder clearHeaderAbort() {
            SingleFieldBuilderV3<HeaderAbort, HeaderAbort.Builder, HeaderAbortOrBuilder> singleFieldBuilderV3 = this.headerAbortBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.errorTypeCase_ == 4) {
                    this.errorTypeCase_ = 0;
                    this.errorType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.errorTypeCase_ == 4) {
                this.errorTypeCase_ = 0;
                this.errorType_ = null;
                onChanged();
            }
            return this;
        }

        public HeaderAbort.Builder getHeaderAbortBuilder() {
            return getHeaderAbortFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
        public HeaderAbortOrBuilder getHeaderAbortOrBuilder() {
            SingleFieldBuilderV3<HeaderAbort, HeaderAbort.Builder, HeaderAbortOrBuilder> singleFieldBuilderV3;
            int i = this.errorTypeCase_;
            if (i == 4 && (singleFieldBuilderV3 = this.headerAbortBuilder_) != null) {
                return (HeaderAbortOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 4) {
                return (HeaderAbort) this.errorType_;
            }
            return HeaderAbort.getDefaultInstance();
        }

        private SingleFieldBuilderV3<HeaderAbort, HeaderAbort.Builder, HeaderAbortOrBuilder> getHeaderAbortFieldBuilder() {
            if (this.headerAbortBuilder_ == null) {
                if (this.errorTypeCase_ != 4) {
                    this.errorType_ = HeaderAbort.getDefaultInstance();
                }
                this.headerAbortBuilder_ = new SingleFieldBuilderV3<>((HeaderAbort) this.errorType_, getParentForChildren(), isClean());
                this.errorType_ = null;
            }
            this.errorTypeCase_ = 4;
            onChanged();
            return this.headerAbortBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbortOrBuilder
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
        public final Builder m25994setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m25988mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$filter$http$fault$v2$FaultAbort$ErrorTypeCase;

        static {
            int[] iArr = new int[ErrorTypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$filter$http$fault$v2$FaultAbort$ErrorTypeCase = iArr;
            try {
                iArr[ErrorTypeCase.HTTP_STATUS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$filter$http$fault$v2$FaultAbort$ErrorTypeCase[ErrorTypeCase.HEADER_ABORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$filter$http$fault$v2$FaultAbort$ErrorTypeCase[ErrorTypeCase.ERRORTYPE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
