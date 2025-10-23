package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class RuntimeDouble extends GeneratedMessageV3 implements RuntimeDoubleOrBuilder {
    public static final int DEFAULT_VALUE_FIELD_NUMBER = 1;
    public static final int RUNTIME_KEY_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final RuntimeDouble DEFAULT_INSTANCE = new RuntimeDouble();
    private static final Parser<RuntimeDouble> PARSER = new AbstractParser<RuntimeDouble>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDouble.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public RuntimeDouble m16569parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RuntimeDouble(codedInputStream, extensionRegistryLite);
        }
    };
    private double defaultValue_;
    private byte memoizedIsInitialized;
    private volatile Object runtimeKey_;

    private RuntimeDouble(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private RuntimeDouble() {
        this.memoizedIsInitialized = (byte) -1;
        this.runtimeKey_ = "";
    }

    private RuntimeDouble(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag == 9) {
                                this.defaultValue_ = codedInputStream.readDouble();
                            } else if (tag == 18) {
                                this.runtimeKey_ = codedInputStream.readStringRequireUtf8();
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

    public static RuntimeDouble getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RuntimeDouble> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BaseProto.internal_static_envoy_api_v2_core_RuntimeDouble_descriptor;
    }

    public static RuntimeDouble parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RuntimeDouble) PARSER.parseFrom(byteBuffer);
    }

    public static RuntimeDouble parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RuntimeDouble) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RuntimeDouble parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RuntimeDouble) PARSER.parseFrom(byteString);
    }

    public static RuntimeDouble parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RuntimeDouble) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RuntimeDouble parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RuntimeDouble) PARSER.parseFrom(bArr);
    }

    public static RuntimeDouble parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RuntimeDouble) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RuntimeDouble parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RuntimeDouble parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RuntimeDouble parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RuntimeDouble parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RuntimeDouble parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RuntimeDouble parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m16567toBuilder();
    }

    public static Builder newBuilder(RuntimeDouble runtimeDouble) {
        return DEFAULT_INSTANCE.m16567toBuilder().mergeFrom(runtimeDouble);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public RuntimeDouble m16562getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDoubleOrBuilder
    public double getDefaultValue() {
        return this.defaultValue_;
    }

    public Parser<RuntimeDouble> getParserForType() {
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
        return new RuntimeDouble();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BaseProto.internal_static_envoy_api_v2_core_RuntimeDouble_fieldAccessorTable.ensureFieldAccessorsInitialized(RuntimeDouble.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDoubleOrBuilder
    public String getRuntimeKey() {
        Object obj = this.runtimeKey_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.runtimeKey_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDoubleOrBuilder
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
        double d = this.defaultValue_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(1, d);
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
        double d = this.defaultValue_;
        int iComputeDoubleSize = d != 0.0d ? CodedOutputStream.computeDoubleSize(1, d) : 0;
        if (!getRuntimeKeyBytes().isEmpty()) {
            iComputeDoubleSize += GeneratedMessageV3.computeStringSize(2, this.runtimeKey_);
        }
        int serializedSize = iComputeDoubleSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RuntimeDouble)) {
            return super.equals(obj);
        }
        RuntimeDouble runtimeDouble = (RuntimeDouble) obj;
        return Double.doubleToLongBits(getDefaultValue()) == Double.doubleToLongBits(runtimeDouble.getDefaultValue()) && getRuntimeKey().equals(runtimeDouble.getRuntimeKey()) && this.unknownFields.equals(runtimeDouble.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getDefaultValue()))) * 37) + 2) * 53) + getRuntimeKey().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m16564newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m16567toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RuntimeDoubleOrBuilder {
        private double defaultValue_;
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
            return BaseProto.internal_static_envoy_api_v2_core_RuntimeDouble_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDoubleOrBuilder
        public double getDefaultValue() {
            return this.defaultValue_;
        }

        public Builder setDefaultValue(double d) {
            this.defaultValue_ = d;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BaseProto.internal_static_envoy_api_v2_core_RuntimeDouble_fieldAccessorTable.ensureFieldAccessorsInitialized(RuntimeDouble.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RuntimeDouble.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16578clear() {
            super.clear();
            this.defaultValue_ = 0.0d;
            this.runtimeKey_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BaseProto.internal_static_envoy_api_v2_core_RuntimeDouble_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RuntimeDouble m16591getDefaultInstanceForType() {
            return RuntimeDouble.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RuntimeDouble m16572build() throws UninitializedMessageException {
            RuntimeDouble runtimeDoubleM16574buildPartial = m16574buildPartial();
            if (runtimeDoubleM16574buildPartial.isInitialized()) {
                return runtimeDoubleM16574buildPartial;
            }
            throw newUninitializedMessageException(runtimeDoubleM16574buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RuntimeDouble m16574buildPartial() {
            RuntimeDouble runtimeDouble = new RuntimeDouble(this);
            runtimeDouble.defaultValue_ = this.defaultValue_;
            runtimeDouble.runtimeKey_ = this.runtimeKey_;
            onBuilt();
            return runtimeDouble;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16590clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16602setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16580clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16583clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16604setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16570addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m16595mergeFrom(Message message) {
            if (message instanceof RuntimeDouble) {
                return mergeFrom((RuntimeDouble) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RuntimeDouble runtimeDouble) {
            if (runtimeDouble == RuntimeDouble.getDefaultInstance()) {
                return this;
            }
            if (runtimeDouble.getDefaultValue() != 0.0d) {
                setDefaultValue(runtimeDouble.getDefaultValue());
            }
            if (!runtimeDouble.getRuntimeKey().isEmpty()) {
                this.runtimeKey_ = runtimeDouble.runtimeKey_;
                onChanged();
            }
            m16600mergeUnknownFields(runtimeDouble.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDouble.Builder m16596mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDouble.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDouble r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDouble) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDouble r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDouble) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDouble.Builder.m16596mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDouble$Builder");
        }

        public Builder clearDefaultValue() {
            this.defaultValue_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDoubleOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeDoubleOrBuilder
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
            RuntimeDouble.checkByteStringIsUtf8(byteString);
            this.runtimeKey_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRuntimeKey() {
            this.runtimeKey_ = RuntimeDouble.getDefaultInstance().getRuntimeKey();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m16606setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m16600mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
