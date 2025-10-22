package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

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

/* loaded from: classes6.dex */
public final class UpstreamHttpProtocolOptions extends GeneratedMessageV3 implements UpstreamHttpProtocolOptionsOrBuilder {
    public static final int AUTO_SAN_VALIDATION_FIELD_NUMBER = 2;
    public static final int AUTO_SNI_FIELD_NUMBER = 1;
    private static final UpstreamHttpProtocolOptions DEFAULT_INSTANCE = new UpstreamHttpProtocolOptions();
    private static final Parser<UpstreamHttpProtocolOptions> PARSER = new AbstractParser<UpstreamHttpProtocolOptions>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptions.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public UpstreamHttpProtocolOptions m24481parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new UpstreamHttpProtocolOptions(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private boolean autoSanValidation_;
    private boolean autoSni_;
    private byte memoizedIsInitialized;

    private UpstreamHttpProtocolOptions(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private UpstreamHttpProtocolOptions() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private UpstreamHttpProtocolOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag == 8) {
                                this.autoSni_ = codedInputStream.readBool();
                            } else if (tag == 16) {
                                this.autoSanValidation_ = codedInputStream.readBool();
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

    public static UpstreamHttpProtocolOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UpstreamHttpProtocolOptions> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ProtocolProto.internal_static_envoy_config_core_v3_UpstreamHttpProtocolOptions_descriptor;
    }

    public static UpstreamHttpProtocolOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (UpstreamHttpProtocolOptions) PARSER.parseFrom(byteBuffer);
    }

    public static UpstreamHttpProtocolOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpstreamHttpProtocolOptions) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static UpstreamHttpProtocolOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (UpstreamHttpProtocolOptions) PARSER.parseFrom(byteString);
    }

    public static UpstreamHttpProtocolOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpstreamHttpProtocolOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static UpstreamHttpProtocolOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (UpstreamHttpProtocolOptions) PARSER.parseFrom(bArr);
    }

    public static UpstreamHttpProtocolOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpstreamHttpProtocolOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static UpstreamHttpProtocolOptions parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static UpstreamHttpProtocolOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpstreamHttpProtocolOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static UpstreamHttpProtocolOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpstreamHttpProtocolOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static UpstreamHttpProtocolOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m24479toBuilder();
    }

    public static Builder newBuilder(UpstreamHttpProtocolOptions upstreamHttpProtocolOptions) {
        return DEFAULT_INSTANCE.m24479toBuilder().mergeFrom(upstreamHttpProtocolOptions);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptionsOrBuilder
    public boolean getAutoSanValidation() {
        return this.autoSanValidation_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptionsOrBuilder
    public boolean getAutoSni() {
        return this.autoSni_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public UpstreamHttpProtocolOptions m24474getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<UpstreamHttpProtocolOptions> getParserForType() {
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
        return new UpstreamHttpProtocolOptions();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ProtocolProto.internal_static_envoy_config_core_v3_UpstreamHttpProtocolOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(UpstreamHttpProtocolOptions.class, Builder.class);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        boolean z = this.autoSni_;
        if (z) {
            codedOutputStream.writeBool(1, z);
        }
        boolean z2 = this.autoSanValidation_;
        if (z2) {
            codedOutputStream.writeBool(2, z2);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        boolean z = this.autoSni_;
        int iComputeBoolSize = z ? CodedOutputStream.computeBoolSize(1, z) : 0;
        boolean z2 = this.autoSanValidation_;
        if (z2) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(2, z2);
        }
        int serializedSize = iComputeBoolSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpstreamHttpProtocolOptions)) {
            return super.equals(obj);
        }
        UpstreamHttpProtocolOptions upstreamHttpProtocolOptions = (UpstreamHttpProtocolOptions) obj;
        return getAutoSni() == upstreamHttpProtocolOptions.getAutoSni() && getAutoSanValidation() == upstreamHttpProtocolOptions.getAutoSanValidation() && this.unknownFields.equals(upstreamHttpProtocolOptions.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getAutoSni())) * 37) + 2) * 53) + Internal.hashBoolean(getAutoSanValidation())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m24476newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m24479toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpstreamHttpProtocolOptionsOrBuilder {
        private boolean autoSanValidation_;
        private boolean autoSni_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtocolProto.internal_static_envoy_config_core_v3_UpstreamHttpProtocolOptions_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptionsOrBuilder
        public boolean getAutoSanValidation() {
            return this.autoSanValidation_;
        }

        public Builder setAutoSanValidation(boolean z) {
            this.autoSanValidation_ = z;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptionsOrBuilder
        public boolean getAutoSni() {
            return this.autoSni_;
        }

        public Builder setAutoSni(boolean z) {
            this.autoSni_ = z;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtocolProto.internal_static_envoy_config_core_v3_UpstreamHttpProtocolOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(UpstreamHttpProtocolOptions.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = UpstreamHttpProtocolOptions.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24490clear() {
            super.clear();
            this.autoSni_ = false;
            this.autoSanValidation_ = false;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ProtocolProto.internal_static_envoy_config_core_v3_UpstreamHttpProtocolOptions_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public UpstreamHttpProtocolOptions m24503getDefaultInstanceForType() {
            return UpstreamHttpProtocolOptions.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public UpstreamHttpProtocolOptions m24484build() throws UninitializedMessageException {
            UpstreamHttpProtocolOptions upstreamHttpProtocolOptionsM24486buildPartial = m24486buildPartial();
            if (upstreamHttpProtocolOptionsM24486buildPartial.isInitialized()) {
                return upstreamHttpProtocolOptionsM24486buildPartial;
            }
            throw newUninitializedMessageException(upstreamHttpProtocolOptionsM24486buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public UpstreamHttpProtocolOptions m24486buildPartial() {
            UpstreamHttpProtocolOptions upstreamHttpProtocolOptions = new UpstreamHttpProtocolOptions(this);
            upstreamHttpProtocolOptions.autoSni_ = this.autoSni_;
            upstreamHttpProtocolOptions.autoSanValidation_ = this.autoSanValidation_;
            onBuilt();
            return upstreamHttpProtocolOptions;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24502clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24514setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24492clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24495clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24516setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24482addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24507mergeFrom(Message message) {
            if (message instanceof UpstreamHttpProtocolOptions) {
                return mergeFrom((UpstreamHttpProtocolOptions) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(UpstreamHttpProtocolOptions upstreamHttpProtocolOptions) {
            if (upstreamHttpProtocolOptions == UpstreamHttpProtocolOptions.getDefaultInstance()) {
                return this;
            }
            if (upstreamHttpProtocolOptions.getAutoSni()) {
                setAutoSni(upstreamHttpProtocolOptions.getAutoSni());
            }
            if (upstreamHttpProtocolOptions.getAutoSanValidation()) {
                setAutoSanValidation(upstreamHttpProtocolOptions.getAutoSanValidation());
            }
            m24512mergeUnknownFields(upstreamHttpProtocolOptions.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptions.Builder m24508mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptions.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptions r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptions) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptions r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptions) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptions.Builder.m24508mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptions$Builder");
        }

        public Builder clearAutoSni() {
            this.autoSni_ = false;
            onChanged();
            return this;
        }

        public Builder clearAutoSanValidation() {
            this.autoSanValidation_ = false;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m24518setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m24512mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
