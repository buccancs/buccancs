package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

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
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class CidrRange extends GeneratedMessageV3 implements CidrRangeOrBuilder {
    public static final int ADDRESS_PREFIX_FIELD_NUMBER = 1;
    public static final int PREFIX_LEN_FIELD_NUMBER = 2;
    private static final CidrRange DEFAULT_INSTANCE = new CidrRange();
    private static final Parser<CidrRange> PARSER = new AbstractParser<CidrRange>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRange.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public CidrRange m14584parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CidrRange(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private volatile Object addressPrefix_;
    private byte memoizedIsInitialized;
    private UInt32Value prefixLen_;

    private CidrRange(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private CidrRange() {
        this.memoizedIsInitialized = (byte) -1;
        this.addressPrefix_ = "";
    }

    private CidrRange(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.addressPrefix_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            UInt32Value uInt32Value = this.prefixLen_;
                            UInt32Value.Builder builder = uInt32Value != null ? uInt32Value.toBuilder() : null;
                            UInt32Value message = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.prefixLen_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.prefixLen_ = builder.buildPartial();
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

    public static CidrRange getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CidrRange> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AddressProto.internal_static_envoy_api_v2_core_CidrRange_descriptor;
    }

    public static CidrRange parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (CidrRange) PARSER.parseFrom(byteBuffer);
    }

    public static CidrRange parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CidrRange) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static CidrRange parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CidrRange) PARSER.parseFrom(byteString);
    }

    public static CidrRange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CidrRange) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static CidrRange parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CidrRange) PARSER.parseFrom(bArr);
    }

    public static CidrRange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CidrRange) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static CidrRange parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static CidrRange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CidrRange parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static CidrRange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CidrRange parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static CidrRange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m14582toBuilder();
    }

    public static Builder newBuilder(CidrRange cidrRange) {
        return DEFAULT_INSTANCE.m14582toBuilder().mergeFrom(cidrRange);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public CidrRange m14577getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<CidrRange> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRangeOrBuilder
    public boolean hasPrefixLen() {
        return this.prefixLen_ != null;
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
        return new CidrRange();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AddressProto.internal_static_envoy_api_v2_core_CidrRange_fieldAccessorTable.ensureFieldAccessorsInitialized(CidrRange.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRangeOrBuilder
    public String getAddressPrefix() {
        Object obj = this.addressPrefix_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.addressPrefix_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRangeOrBuilder
    public ByteString getAddressPrefixBytes() {
        Object obj = this.addressPrefix_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.addressPrefix_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRangeOrBuilder
    public UInt32Value getPrefixLen() {
        UInt32Value uInt32Value = this.prefixLen_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRangeOrBuilder
    public UInt32ValueOrBuilder getPrefixLenOrBuilder() {
        return getPrefixLen();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getAddressPrefixBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.addressPrefix_);
        }
        if (this.prefixLen_ != null) {
            codedOutputStream.writeMessage(2, getPrefixLen());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getAddressPrefixBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.addressPrefix_) : 0;
        if (this.prefixLen_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, getPrefixLen());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CidrRange)) {
            return super.equals(obj);
        }
        CidrRange cidrRange = (CidrRange) obj;
        if (getAddressPrefix().equals(cidrRange.getAddressPrefix()) && hasPrefixLen() == cidrRange.hasPrefixLen()) {
            return (!hasPrefixLen() || getPrefixLen().equals(cidrRange.getPrefixLen())) && this.unknownFields.equals(cidrRange.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getAddressPrefix().hashCode();
        if (hasPrefixLen()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getPrefixLen().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14579newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14582toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CidrRangeOrBuilder {
        private Object addressPrefix_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> prefixLenBuilder_;
        private UInt32Value prefixLen_;

        private Builder() {
            this.addressPrefix_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.addressPrefix_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AddressProto.internal_static_envoy_api_v2_core_CidrRange_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRangeOrBuilder
        public boolean hasPrefixLen() {
            return (this.prefixLenBuilder_ == null && this.prefixLen_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AddressProto.internal_static_envoy_api_v2_core_CidrRange_fieldAccessorTable.ensureFieldAccessorsInitialized(CidrRange.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = CidrRange.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14593clear() {
            super.clear();
            this.addressPrefix_ = "";
            if (this.prefixLenBuilder_ == null) {
                this.prefixLen_ = null;
            } else {
                this.prefixLen_ = null;
                this.prefixLenBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return AddressProto.internal_static_envoy_api_v2_core_CidrRange_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CidrRange m14606getDefaultInstanceForType() {
            return CidrRange.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CidrRange m14587build() throws UninitializedMessageException {
            CidrRange cidrRangeM14589buildPartial = m14589buildPartial();
            if (cidrRangeM14589buildPartial.isInitialized()) {
                return cidrRangeM14589buildPartial;
            }
            throw newUninitializedMessageException(cidrRangeM14589buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CidrRange m14589buildPartial() {
            CidrRange cidrRange = new CidrRange(this);
            cidrRange.addressPrefix_ = this.addressPrefix_;
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.prefixLenBuilder_;
            if (singleFieldBuilderV3 == null) {
                cidrRange.prefixLen_ = this.prefixLen_;
            } else {
                cidrRange.prefixLen_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return cidrRange;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14605clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14617setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14595clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14598clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14619setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14585addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14610mergeFrom(Message message) {
            if (message instanceof CidrRange) {
                return mergeFrom((CidrRange) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(CidrRange cidrRange) {
            if (cidrRange == CidrRange.getDefaultInstance()) {
                return this;
            }
            if (!cidrRange.getAddressPrefix().isEmpty()) {
                this.addressPrefix_ = cidrRange.addressPrefix_;
                onChanged();
            }
            if (cidrRange.hasPrefixLen()) {
                mergePrefixLen(cidrRange.getPrefixLen());
            }
            m14615mergeUnknownFields(cidrRange.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRange.Builder m14611mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRange.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRange r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRange) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRange r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRange) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRange.Builder.m14611mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRange$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRangeOrBuilder
        public String getAddressPrefix() {
            Object obj = this.addressPrefix_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.addressPrefix_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setAddressPrefix(String str) {
            str.getClass();
            this.addressPrefix_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRangeOrBuilder
        public ByteString getAddressPrefixBytes() {
            Object obj = this.addressPrefix_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.addressPrefix_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAddressPrefixBytes(ByteString byteString) {
            byteString.getClass();
            CidrRange.checkByteStringIsUtf8(byteString);
            this.addressPrefix_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearAddressPrefix() {
            this.addressPrefix_ = CidrRange.getDefaultInstance().getAddressPrefix();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRangeOrBuilder
        public UInt32Value getPrefixLen() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.prefixLenBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.prefixLen_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setPrefixLen(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.prefixLenBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.prefixLen_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setPrefixLen(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.prefixLenBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.prefixLen_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergePrefixLen(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.prefixLenBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.prefixLen_;
                if (uInt32Value2 != null) {
                    this.prefixLen_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.prefixLen_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearPrefixLen() {
            if (this.prefixLenBuilder_ == null) {
                this.prefixLen_ = null;
                onChanged();
            } else {
                this.prefixLen_ = null;
                this.prefixLenBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getPrefixLenBuilder() {
            onChanged();
            return getPrefixLenFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRangeOrBuilder
        public UInt32ValueOrBuilder getPrefixLenOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.prefixLenBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.prefixLen_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getPrefixLenFieldBuilder() {
            if (this.prefixLenBuilder_ == null) {
                this.prefixLenBuilder_ = new SingleFieldBuilderV3<>(getPrefixLen(), getParentForChildren(), isClean());
                this.prefixLen_ = null;
            }
            return this.prefixLenBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14621setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14615mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
