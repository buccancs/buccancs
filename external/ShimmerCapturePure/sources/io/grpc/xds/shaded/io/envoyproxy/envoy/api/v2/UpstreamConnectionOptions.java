package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

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
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.TcpKeepalive;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.TcpKeepaliveOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class UpstreamConnectionOptions extends GeneratedMessageV3 implements UpstreamConnectionOptionsOrBuilder {
    public static final int TCP_KEEPALIVE_FIELD_NUMBER = 1;
    private static final UpstreamConnectionOptions DEFAULT_INSTANCE = new UpstreamConnectionOptions();
    private static final Parser<UpstreamConnectionOptions> PARSER = new AbstractParser<UpstreamConnectionOptions>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptions.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public UpstreamConnectionOptions m13377parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new UpstreamConnectionOptions(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private TcpKeepalive tcpKeepalive_;

    private UpstreamConnectionOptions(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private UpstreamConnectionOptions() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private UpstreamConnectionOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                TcpKeepalive tcpKeepalive = this.tcpKeepalive_;
                                TcpKeepalive.Builder builderM16891toBuilder = tcpKeepalive != null ? tcpKeepalive.m16891toBuilder() : null;
                                TcpKeepalive tcpKeepalive2 = (TcpKeepalive) codedInputStream.readMessage(TcpKeepalive.parser(), extensionRegistryLite);
                                this.tcpKeepalive_ = tcpKeepalive2;
                                if (builderM16891toBuilder != null) {
                                    builderM16891toBuilder.mergeFrom(tcpKeepalive2);
                                    this.tcpKeepalive_ = builderM16891toBuilder.m16898buildPartial();
                                }
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

    public static UpstreamConnectionOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UpstreamConnectionOptions> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ClusterProto.internal_static_envoy_api_v2_UpstreamConnectionOptions_descriptor;
    }

    public static UpstreamConnectionOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (UpstreamConnectionOptions) PARSER.parseFrom(byteBuffer);
    }

    public static UpstreamConnectionOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpstreamConnectionOptions) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static UpstreamConnectionOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (UpstreamConnectionOptions) PARSER.parseFrom(byteString);
    }

    public static UpstreamConnectionOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpstreamConnectionOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static UpstreamConnectionOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (UpstreamConnectionOptions) PARSER.parseFrom(bArr);
    }

    public static UpstreamConnectionOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpstreamConnectionOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static UpstreamConnectionOptions parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static UpstreamConnectionOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpstreamConnectionOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static UpstreamConnectionOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpstreamConnectionOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static UpstreamConnectionOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m13375toBuilder();
    }

    public static Builder newBuilder(UpstreamConnectionOptions upstreamConnectionOptions) {
        return DEFAULT_INSTANCE.m13375toBuilder().mergeFrom(upstreamConnectionOptions);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public UpstreamConnectionOptions m13370getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<UpstreamConnectionOptions> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptionsOrBuilder
    public boolean hasTcpKeepalive() {
        return this.tcpKeepalive_ != null;
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
        return new UpstreamConnectionOptions();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ClusterProto.internal_static_envoy_api_v2_UpstreamConnectionOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(UpstreamConnectionOptions.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptionsOrBuilder
    public TcpKeepalive getTcpKeepalive() {
        TcpKeepalive tcpKeepalive = this.tcpKeepalive_;
        return tcpKeepalive == null ? TcpKeepalive.getDefaultInstance() : tcpKeepalive;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptionsOrBuilder
    public TcpKeepaliveOrBuilder getTcpKeepaliveOrBuilder() {
        return getTcpKeepalive();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.tcpKeepalive_ != null) {
            codedOutputStream.writeMessage(1, getTcpKeepalive());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = (this.tcpKeepalive_ != null ? CodedOutputStream.computeMessageSize(1, getTcpKeepalive()) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeMessageSize;
        return iComputeMessageSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpstreamConnectionOptions)) {
            return super.equals(obj);
        }
        UpstreamConnectionOptions upstreamConnectionOptions = (UpstreamConnectionOptions) obj;
        if (hasTcpKeepalive() != upstreamConnectionOptions.hasTcpKeepalive()) {
            return false;
        }
        return (!hasTcpKeepalive() || getTcpKeepalive().equals(upstreamConnectionOptions.getTcpKeepalive())) && this.unknownFields.equals(upstreamConnectionOptions.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasTcpKeepalive()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getTcpKeepalive().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m13372newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m13375toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpstreamConnectionOptionsOrBuilder {
        private SingleFieldBuilderV3<TcpKeepalive, TcpKeepalive.Builder, TcpKeepaliveOrBuilder> tcpKeepaliveBuilder_;
        private TcpKeepalive tcpKeepalive_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ClusterProto.internal_static_envoy_api_v2_UpstreamConnectionOptions_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptionsOrBuilder
        public boolean hasTcpKeepalive() {
            return (this.tcpKeepaliveBuilder_ == null && this.tcpKeepalive_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ClusterProto.internal_static_envoy_api_v2_UpstreamConnectionOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(UpstreamConnectionOptions.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = UpstreamConnectionOptions.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13386clear() {
            super.clear();
            if (this.tcpKeepaliveBuilder_ == null) {
                this.tcpKeepalive_ = null;
            } else {
                this.tcpKeepalive_ = null;
                this.tcpKeepaliveBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ClusterProto.internal_static_envoy_api_v2_UpstreamConnectionOptions_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public UpstreamConnectionOptions m13399getDefaultInstanceForType() {
            return UpstreamConnectionOptions.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public UpstreamConnectionOptions m13380build() throws UninitializedMessageException {
            UpstreamConnectionOptions upstreamConnectionOptionsM13382buildPartial = m13382buildPartial();
            if (upstreamConnectionOptionsM13382buildPartial.isInitialized()) {
                return upstreamConnectionOptionsM13382buildPartial;
            }
            throw newUninitializedMessageException(upstreamConnectionOptionsM13382buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public UpstreamConnectionOptions m13382buildPartial() {
            UpstreamConnectionOptions upstreamConnectionOptions = new UpstreamConnectionOptions(this);
            SingleFieldBuilderV3<TcpKeepalive, TcpKeepalive.Builder, TcpKeepaliveOrBuilder> singleFieldBuilderV3 = this.tcpKeepaliveBuilder_;
            if (singleFieldBuilderV3 == null) {
                upstreamConnectionOptions.tcpKeepalive_ = this.tcpKeepalive_;
            } else {
                upstreamConnectionOptions.tcpKeepalive_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return upstreamConnectionOptions;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13398clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13410setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13388clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13391clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13412setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13378addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13403mergeFrom(Message message) {
            if (message instanceof UpstreamConnectionOptions) {
                return mergeFrom((UpstreamConnectionOptions) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(UpstreamConnectionOptions upstreamConnectionOptions) {
            if (upstreamConnectionOptions == UpstreamConnectionOptions.getDefaultInstance()) {
                return this;
            }
            if (upstreamConnectionOptions.hasTcpKeepalive()) {
                mergeTcpKeepalive(upstreamConnectionOptions.getTcpKeepalive());
            }
            m13408mergeUnknownFields(upstreamConnectionOptions.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptions.Builder m13404mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptions.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptions r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptions) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptions r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptions) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptions.Builder.m13404mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptions$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptionsOrBuilder
        public TcpKeepalive getTcpKeepalive() {
            SingleFieldBuilderV3<TcpKeepalive, TcpKeepalive.Builder, TcpKeepaliveOrBuilder> singleFieldBuilderV3 = this.tcpKeepaliveBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            TcpKeepalive tcpKeepalive = this.tcpKeepalive_;
            return tcpKeepalive == null ? TcpKeepalive.getDefaultInstance() : tcpKeepalive;
        }

        public Builder setTcpKeepalive(TcpKeepalive tcpKeepalive) {
            SingleFieldBuilderV3<TcpKeepalive, TcpKeepalive.Builder, TcpKeepaliveOrBuilder> singleFieldBuilderV3 = this.tcpKeepaliveBuilder_;
            if (singleFieldBuilderV3 == null) {
                tcpKeepalive.getClass();
                this.tcpKeepalive_ = tcpKeepalive;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(tcpKeepalive);
            }
            return this;
        }

        public Builder setTcpKeepalive(TcpKeepalive.Builder builder) {
            SingleFieldBuilderV3<TcpKeepalive, TcpKeepalive.Builder, TcpKeepaliveOrBuilder> singleFieldBuilderV3 = this.tcpKeepaliveBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.tcpKeepalive_ = builder.m16896build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m16896build());
            }
            return this;
        }

        public Builder mergeTcpKeepalive(TcpKeepalive tcpKeepalive) {
            SingleFieldBuilderV3<TcpKeepalive, TcpKeepalive.Builder, TcpKeepaliveOrBuilder> singleFieldBuilderV3 = this.tcpKeepaliveBuilder_;
            if (singleFieldBuilderV3 == null) {
                TcpKeepalive tcpKeepalive2 = this.tcpKeepalive_;
                if (tcpKeepalive2 != null) {
                    this.tcpKeepalive_ = TcpKeepalive.newBuilder(tcpKeepalive2).mergeFrom(tcpKeepalive).m16898buildPartial();
                } else {
                    this.tcpKeepalive_ = tcpKeepalive;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(tcpKeepalive);
            }
            return this;
        }

        public Builder clearTcpKeepalive() {
            if (this.tcpKeepaliveBuilder_ == null) {
                this.tcpKeepalive_ = null;
                onChanged();
            } else {
                this.tcpKeepalive_ = null;
                this.tcpKeepaliveBuilder_ = null;
            }
            return this;
        }

        public TcpKeepalive.Builder getTcpKeepaliveBuilder() {
            onChanged();
            return getTcpKeepaliveFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.UpstreamConnectionOptionsOrBuilder
        public TcpKeepaliveOrBuilder getTcpKeepaliveOrBuilder() {
            SingleFieldBuilderV3<TcpKeepalive, TcpKeepalive.Builder, TcpKeepaliveOrBuilder> singleFieldBuilderV3 = this.tcpKeepaliveBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (TcpKeepaliveOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            TcpKeepalive tcpKeepalive = this.tcpKeepalive_;
            return tcpKeepalive == null ? TcpKeepalive.getDefaultInstance() : tcpKeepalive;
        }

        private SingleFieldBuilderV3<TcpKeepalive, TcpKeepalive.Builder, TcpKeepaliveOrBuilder> getTcpKeepaliveFieldBuilder() {
            if (this.tcpKeepaliveBuilder_ == null) {
                this.tcpKeepaliveBuilder_ = new SingleFieldBuilderV3<>(getTcpKeepalive(), getParentForChildren(), isClean());
                this.tcpKeepalive_ = null;
            }
            return this.tcpKeepaliveBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m13414setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m13408mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
