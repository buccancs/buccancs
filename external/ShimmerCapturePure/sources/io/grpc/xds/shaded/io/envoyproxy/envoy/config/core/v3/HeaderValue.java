package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class HeaderValue extends GeneratedMessageV3 implements HeaderValueOrBuilder {
    public static final int KEY_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final HeaderValue DEFAULT_INSTANCE = new HeaderValue();
    private static final Parser<HeaderValue> PARSER = new AbstractParser<HeaderValue>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValue.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public HeaderValue m22768parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HeaderValue(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object key_;
    private byte memoizedIsInitialized;
    private volatile Object value_;

    private HeaderValue(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private HeaderValue() {
        this.memoizedIsInitialized = (byte) -1;
        this.key_ = "";
        this.value_ = "";
    }

    private HeaderValue(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.key_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.value_ = codedInputStream.readStringRequireUtf8();
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

    public static HeaderValue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HeaderValue> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BaseProto.internal_static_envoy_config_core_v3_HeaderValue_descriptor;
    }

    public static HeaderValue parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HeaderValue) PARSER.parseFrom(byteBuffer);
    }

    public static HeaderValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HeaderValue) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HeaderValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HeaderValue) PARSER.parseFrom(byteString);
    }

    public static HeaderValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HeaderValue) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HeaderValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HeaderValue) PARSER.parseFrom(bArr);
    }

    public static HeaderValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HeaderValue) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HeaderValue parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HeaderValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HeaderValue parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HeaderValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HeaderValue parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HeaderValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m22766toBuilder();
    }

    public static Builder newBuilder(HeaderValue headerValue) {
        return DEFAULT_INSTANCE.m22766toBuilder().mergeFrom(headerValue);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public HeaderValue m22761getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<HeaderValue> getParserForType() {
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
        return new HeaderValue();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BaseProto.internal_static_envoy_config_core_v3_HeaderValue_fieldAccessorTable.ensureFieldAccessorsInitialized(HeaderValue.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValueOrBuilder
    public String getKey() {
        Object obj = this.key_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.key_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValueOrBuilder
    public ByteString getKeyBytes() {
        Object obj = this.key_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.key_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValueOrBuilder
    public String getValue() {
        Object obj = this.value_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.value_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValueOrBuilder
    public ByteString getValueBytes() {
        Object obj = this.value_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.value_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getKeyBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.key_);
        }
        if (!getValueBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.value_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getKeyBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.key_) : 0;
        if (!getValueBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.value_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HeaderValue)) {
            return super.equals(obj);
        }
        HeaderValue headerValue = (HeaderValue) obj;
        return getKey().equals(headerValue.getKey()) && getValue().equals(headerValue.getValue()) && this.unknownFields.equals(headerValue.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getKey().hashCode()) * 37) + 2) * 53) + getValue().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m22763newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m22766toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HeaderValueOrBuilder {
        private Object key_;
        private Object value_;

        private Builder() {
            this.key_ = "";
            this.value_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.key_ = "";
            this.value_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BaseProto.internal_static_envoy_config_core_v3_HeaderValue_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BaseProto.internal_static_envoy_config_core_v3_HeaderValue_fieldAccessorTable.ensureFieldAccessorsInitialized(HeaderValue.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = HeaderValue.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22777clear() {
            super.clear();
            this.key_ = "";
            this.value_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BaseProto.internal_static_envoy_config_core_v3_HeaderValue_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HeaderValue m22790getDefaultInstanceForType() {
            return HeaderValue.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HeaderValue m22771build() throws UninitializedMessageException {
            HeaderValue headerValueM22773buildPartial = m22773buildPartial();
            if (headerValueM22773buildPartial.isInitialized()) {
                return headerValueM22773buildPartial;
            }
            throw newUninitializedMessageException(headerValueM22773buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HeaderValue m22773buildPartial() {
            HeaderValue headerValue = new HeaderValue(this);
            headerValue.key_ = this.key_;
            headerValue.value_ = this.value_;
            onBuilt();
            return headerValue;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22789clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22801setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22779clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22782clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22803setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22769addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22794mergeFrom(Message message) {
            if (message instanceof HeaderValue) {
                return mergeFrom((HeaderValue) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HeaderValue headerValue) {
            if (headerValue == HeaderValue.getDefaultInstance()) {
                return this;
            }
            if (!headerValue.getKey().isEmpty()) {
                this.key_ = headerValue.key_;
                onChanged();
            }
            if (!headerValue.getValue().isEmpty()) {
                this.value_ = headerValue.value_;
                onChanged();
            }
            m22799mergeUnknownFields(headerValue.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValue.Builder m22795mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValue.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValue r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValue) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValue r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValue) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValue.Builder.m22795mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValue$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValueOrBuilder
        public String getKey() {
            Object obj = this.key_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.key_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setKey(String str) {
            str.getClass();
            this.key_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValueOrBuilder
        public ByteString getKeyBytes() {
            Object obj = this.key_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.key_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setKeyBytes(ByteString byteString) {
            byteString.getClass();
            HeaderValue.checkByteStringIsUtf8(byteString);
            this.key_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearKey() {
            this.key_ = HeaderValue.getDefaultInstance().getKey();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValueOrBuilder
        public String getValue() {
            Object obj = this.value_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.value_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setValue(String str) {
            str.getClass();
            this.value_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValueOrBuilder
        public ByteString getValueBytes() {
            Object obj = this.value_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.value_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setValueBytes(ByteString byteString) {
            byteString.getClass();
            HeaderValue.checkByteStringIsUtf8(byteString);
            this.value_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearValue() {
            this.value_ = HeaderValue.getDefaultInstance().getValue();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m22805setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m22799mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
