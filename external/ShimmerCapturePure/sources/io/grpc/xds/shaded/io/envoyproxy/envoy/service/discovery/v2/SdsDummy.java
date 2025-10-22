package io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2;

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

/* loaded from: classes4.dex */
public final class SdsDummy extends GeneratedMessageV3 implements SdsDummyOrBuilder {
    private static final SdsDummy DEFAULT_INSTANCE = new SdsDummy();
    private static final Parser<SdsDummy> PARSER = new AbstractParser<SdsDummy>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2.SdsDummy.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public SdsDummy m32301parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SdsDummy(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;

    private SdsDummy(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SdsDummy() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private SdsDummy(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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

    public static SdsDummy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SdsDummy> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SdsProto.internal_static_envoy_service_discovery_v2_SdsDummy_descriptor;
    }

    public static SdsDummy parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (SdsDummy) PARSER.parseFrom(byteBuffer);
    }

    public static SdsDummy parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SdsDummy) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SdsDummy parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SdsDummy) PARSER.parseFrom(byteString);
    }

    public static SdsDummy parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SdsDummy) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SdsDummy parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SdsDummy) PARSER.parseFrom(bArr);
    }

    public static SdsDummy parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SdsDummy) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SdsDummy parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SdsDummy parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SdsDummy parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SdsDummy parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SdsDummy parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SdsDummy parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m32299toBuilder();
    }

    public static Builder newBuilder(SdsDummy sdsDummy) {
        return DEFAULT_INSTANCE.m32299toBuilder().mergeFrom(sdsDummy);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SdsDummy m32294getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<SdsDummy> getParserForType() {
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
        return new SdsDummy();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SdsProto.internal_static_envoy_service_discovery_v2_SdsDummy_fieldAccessorTable.ensureFieldAccessorsInitialized(SdsDummy.class, Builder.class);
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
        if (obj instanceof SdsDummy) {
            return this.unknownFields.equals(((SdsDummy) obj).unknownFields);
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
    public Builder m32296newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m32299toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SdsDummyOrBuilder {
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SdsProto.internal_static_envoy_service_discovery_v2_SdsDummy_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SdsProto.internal_static_envoy_service_discovery_v2_SdsDummy_fieldAccessorTable.ensureFieldAccessorsInitialized(SdsDummy.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SdsDummy.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32310clear() {
            super.clear();
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return SdsProto.internal_static_envoy_service_discovery_v2_SdsDummy_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SdsDummy m32323getDefaultInstanceForType() {
            return SdsDummy.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SdsDummy m32304build() throws UninitializedMessageException {
            SdsDummy sdsDummyM32306buildPartial = m32306buildPartial();
            if (sdsDummyM32306buildPartial.isInitialized()) {
                return sdsDummyM32306buildPartial;
            }
            throw newUninitializedMessageException(sdsDummyM32306buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SdsDummy m32306buildPartial() {
            SdsDummy sdsDummy = new SdsDummy(this);
            onBuilt();
            return sdsDummy;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32322clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32334setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32312clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32315clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32336setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32302addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32327mergeFrom(Message message) {
            if (message instanceof SdsDummy) {
                return mergeFrom((SdsDummy) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SdsDummy sdsDummy) {
            if (sdsDummy == SdsDummy.getDefaultInstance()) {
                return this;
            }
            m32332mergeUnknownFields(sdsDummy.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2.SdsDummy.Builder m32328mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2.SdsDummy.access$500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2.SdsDummy r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2.SdsDummy) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2.SdsDummy r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2.SdsDummy) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2.SdsDummy.Builder.m32328mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2.SdsDummy$Builder");
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m32338setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m32332mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
