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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.FractionalPercentOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class RuntimeFractionalPercent extends GeneratedMessageV3 implements RuntimeFractionalPercentOrBuilder {
    public static final int DEFAULT_VALUE_FIELD_NUMBER = 1;
    public static final int RUNTIME_KEY_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final RuntimeFractionalPercent DEFAULT_INSTANCE = new RuntimeFractionalPercent();
    private static final Parser<RuntimeFractionalPercent> PARSER = new AbstractParser<RuntimeFractionalPercent>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercent.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public RuntimeFractionalPercent m24018parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RuntimeFractionalPercent(codedInputStream, extensionRegistryLite);
        }
    };
    private FractionalPercent defaultValue_;
    private byte memoizedIsInitialized;
    private volatile Object runtimeKey_;

    private RuntimeFractionalPercent(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private RuntimeFractionalPercent() {
        this.memoizedIsInitialized = (byte) -1;
        this.runtimeKey_ = "";
    }

    private RuntimeFractionalPercent(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            FractionalPercent fractionalPercent = this.defaultValue_;
                            FractionalPercent.Builder builderM35023toBuilder = fractionalPercent != null ? fractionalPercent.m35023toBuilder() : null;
                            FractionalPercent fractionalPercent2 = (FractionalPercent) codedInputStream.readMessage(FractionalPercent.parser(), extensionRegistryLite);
                            this.defaultValue_ = fractionalPercent2;
                            if (builderM35023toBuilder != null) {
                                builderM35023toBuilder.mergeFrom(fractionalPercent2);
                                this.defaultValue_ = builderM35023toBuilder.m35030buildPartial();
                            }
                        } else if (tag == 18) {
                            this.runtimeKey_ = codedInputStream.readStringRequireUtf8();
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

    public static RuntimeFractionalPercent getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RuntimeFractionalPercent> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BaseProto.internal_static_envoy_config_core_v3_RuntimeFractionalPercent_descriptor;
    }

    public static RuntimeFractionalPercent parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RuntimeFractionalPercent) PARSER.parseFrom(byteBuffer);
    }

    public static RuntimeFractionalPercent parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RuntimeFractionalPercent) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RuntimeFractionalPercent parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RuntimeFractionalPercent) PARSER.parseFrom(byteString);
    }

    public static RuntimeFractionalPercent parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RuntimeFractionalPercent) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RuntimeFractionalPercent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RuntimeFractionalPercent) PARSER.parseFrom(bArr);
    }

    public static RuntimeFractionalPercent parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RuntimeFractionalPercent) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RuntimeFractionalPercent parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RuntimeFractionalPercent parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RuntimeFractionalPercent parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RuntimeFractionalPercent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RuntimeFractionalPercent parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RuntimeFractionalPercent parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m24016toBuilder();
    }

    public static Builder newBuilder(RuntimeFractionalPercent runtimeFractionalPercent) {
        return DEFAULT_INSTANCE.m24016toBuilder().mergeFrom(runtimeFractionalPercent);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public RuntimeFractionalPercent m24011getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<RuntimeFractionalPercent> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercentOrBuilder
    public boolean hasDefaultValue() {
        return this.defaultValue_ != null;
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
        return new RuntimeFractionalPercent();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BaseProto.internal_static_envoy_config_core_v3_RuntimeFractionalPercent_fieldAccessorTable.ensureFieldAccessorsInitialized(RuntimeFractionalPercent.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercentOrBuilder
    public FractionalPercent getDefaultValue() {
        FractionalPercent fractionalPercent = this.defaultValue_;
        return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercentOrBuilder
    public FractionalPercentOrBuilder getDefaultValueOrBuilder() {
        return getDefaultValue();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercentOrBuilder
    public String getRuntimeKey() {
        Object obj = this.runtimeKey_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.runtimeKey_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercentOrBuilder
    public ByteString getRuntimeKeyBytes() {
        Object obj = this.runtimeKey_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.runtimeKey_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.defaultValue_ != null) {
            codedOutputStream.writeMessage(1, getDefaultValue());
        }
        if (!getRuntimeKeyBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.runtimeKey_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.defaultValue_ != null ? CodedOutputStream.computeMessageSize(1, getDefaultValue()) : 0;
        if (!getRuntimeKeyBytes().isEmpty()) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(2, this.runtimeKey_);
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RuntimeFractionalPercent)) {
            return super.equals(obj);
        }
        RuntimeFractionalPercent runtimeFractionalPercent = (RuntimeFractionalPercent) obj;
        if (hasDefaultValue() != runtimeFractionalPercent.hasDefaultValue()) {
            return false;
        }
        return (!hasDefaultValue() || getDefaultValue().equals(runtimeFractionalPercent.getDefaultValue())) && getRuntimeKey().equals(runtimeFractionalPercent.getRuntimeKey()) && this.unknownFields.equals(runtimeFractionalPercent.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasDefaultValue()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getDefaultValue().hashCode();
        }
        int iHashCode2 = (((((iHashCode * 37) + 2) * 53) + getRuntimeKey().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m24013newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m24016toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RuntimeFractionalPercentOrBuilder {
        private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> defaultValueBuilder_;
        private FractionalPercent defaultValue_;
        private Object runtimeKey_;

        private Builder() {
            this.runtimeKey_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.runtimeKey_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BaseProto.internal_static_envoy_config_core_v3_RuntimeFractionalPercent_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercentOrBuilder
        public boolean hasDefaultValue() {
            return (this.defaultValueBuilder_ == null && this.defaultValue_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BaseProto.internal_static_envoy_config_core_v3_RuntimeFractionalPercent_fieldAccessorTable.ensureFieldAccessorsInitialized(RuntimeFractionalPercent.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RuntimeFractionalPercent.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24027clear() {
            super.clear();
            if (this.defaultValueBuilder_ == null) {
                this.defaultValue_ = null;
            } else {
                this.defaultValue_ = null;
                this.defaultValueBuilder_ = null;
            }
            this.runtimeKey_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BaseProto.internal_static_envoy_config_core_v3_RuntimeFractionalPercent_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RuntimeFractionalPercent m24040getDefaultInstanceForType() {
            return RuntimeFractionalPercent.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RuntimeFractionalPercent m24021build() throws UninitializedMessageException {
            RuntimeFractionalPercent runtimeFractionalPercentM24023buildPartial = m24023buildPartial();
            if (runtimeFractionalPercentM24023buildPartial.isInitialized()) {
                return runtimeFractionalPercentM24023buildPartial;
            }
            throw newUninitializedMessageException(runtimeFractionalPercentM24023buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RuntimeFractionalPercent m24023buildPartial() {
            RuntimeFractionalPercent runtimeFractionalPercent = new RuntimeFractionalPercent(this);
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.defaultValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                runtimeFractionalPercent.defaultValue_ = this.defaultValue_;
            } else {
                runtimeFractionalPercent.defaultValue_ = singleFieldBuilderV3.build();
            }
            runtimeFractionalPercent.runtimeKey_ = this.runtimeKey_;
            onBuilt();
            return runtimeFractionalPercent;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24039clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24051setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24029clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24032clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24053setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24019addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24044mergeFrom(Message message) {
            if (message instanceof RuntimeFractionalPercent) {
                return mergeFrom((RuntimeFractionalPercent) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RuntimeFractionalPercent runtimeFractionalPercent) {
            if (runtimeFractionalPercent == RuntimeFractionalPercent.getDefaultInstance()) {
                return this;
            }
            if (runtimeFractionalPercent.hasDefaultValue()) {
                mergeDefaultValue(runtimeFractionalPercent.getDefaultValue());
            }
            if (!runtimeFractionalPercent.getRuntimeKey().isEmpty()) {
                this.runtimeKey_ = runtimeFractionalPercent.runtimeKey_;
                onChanged();
            }
            m24049mergeUnknownFields(runtimeFractionalPercent.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercent.Builder m24045mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercent.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercent r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercent) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercent r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercent) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercent.Builder.m24045mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercent$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercentOrBuilder
        public FractionalPercent getDefaultValue() {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.defaultValueBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            FractionalPercent fractionalPercent = this.defaultValue_;
            return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
        }

        public Builder setDefaultValue(FractionalPercent fractionalPercent) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.defaultValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                fractionalPercent.getClass();
                this.defaultValue_ = fractionalPercent;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(fractionalPercent);
            }
            return this;
        }

        public Builder setDefaultValue(FractionalPercent.Builder builder) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.defaultValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.defaultValue_ = builder.m35028build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m35028build());
            }
            return this;
        }

        public Builder mergeDefaultValue(FractionalPercent fractionalPercent) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.defaultValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                FractionalPercent fractionalPercent2 = this.defaultValue_;
                if (fractionalPercent2 != null) {
                    this.defaultValue_ = FractionalPercent.newBuilder(fractionalPercent2).mergeFrom(fractionalPercent).m35030buildPartial();
                } else {
                    this.defaultValue_ = fractionalPercent;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(fractionalPercent);
            }
            return this;
        }

        public Builder clearDefaultValue() {
            if (this.defaultValueBuilder_ == null) {
                this.defaultValue_ = null;
                onChanged();
            } else {
                this.defaultValue_ = null;
                this.defaultValueBuilder_ = null;
            }
            return this;
        }

        public FractionalPercent.Builder getDefaultValueBuilder() {
            onChanged();
            return getDefaultValueFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercentOrBuilder
        public FractionalPercentOrBuilder getDefaultValueOrBuilder() {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.defaultValueBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (FractionalPercentOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            FractionalPercent fractionalPercent = this.defaultValue_;
            return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
        }

        private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> getDefaultValueFieldBuilder() {
            if (this.defaultValueBuilder_ == null) {
                this.defaultValueBuilder_ = new SingleFieldBuilderV3<>(getDefaultValue(), getParentForChildren(), isClean());
                this.defaultValue_ = null;
            }
            return this.defaultValueBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercentOrBuilder
        public String getRuntimeKey() {
            Object obj = this.runtimeKey_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.runtimeKey_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setRuntimeKey(String str) {
            str.getClass();
            this.runtimeKey_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercentOrBuilder
        public ByteString getRuntimeKeyBytes() {
            Object obj = this.runtimeKey_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.runtimeKey_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRuntimeKeyBytes(ByteString byteString) {
            byteString.getClass();
            RuntimeFractionalPercent.checkByteStringIsUtf8(byteString);
            this.runtimeKey_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRuntimeKey() {
            this.runtimeKey_ = RuntimeFractionalPercent.getDefaultInstance().getRuntimeKey();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m24055setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m24049mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
