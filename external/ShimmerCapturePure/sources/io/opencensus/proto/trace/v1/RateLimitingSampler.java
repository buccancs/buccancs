package io.opencensus.proto.trace.v1;

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

/* loaded from: classes4.dex */
public final class RateLimitingSampler extends GeneratedMessageV3 implements RateLimitingSamplerOrBuilder {
    public static final int QPS_FIELD_NUMBER = 1;
    private static final RateLimitingSampler DEFAULT_INSTANCE = new RateLimitingSampler();
    private static final Parser<RateLimitingSampler> PARSER = new AbstractParser<RateLimitingSampler>() { // from class: io.opencensus.proto.trace.v1.RateLimitingSampler.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public RateLimitingSampler m38026parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RateLimitingSampler(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private long qps_;

    private RateLimitingSampler(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private RateLimitingSampler() {
        this.memoizedIsInitialized = (byte) -1;
        this.qps_ = 0L;
    }

    private RateLimitingSampler(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.qps_ = codedInputStream.readInt64();
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static RateLimitingSampler getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RateLimitingSampler> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return TraceConfigProto.internal_static_opencensus_proto_trace_v1_RateLimitingSampler_descriptor;
    }

    public static RateLimitingSampler parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RateLimitingSampler) PARSER.parseFrom(byteBuffer);
    }

    public static RateLimitingSampler parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RateLimitingSampler) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RateLimitingSampler parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RateLimitingSampler) PARSER.parseFrom(byteString);
    }

    public static RateLimitingSampler parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RateLimitingSampler) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RateLimitingSampler parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RateLimitingSampler) PARSER.parseFrom(bArr);
    }

    public static RateLimitingSampler parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RateLimitingSampler) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RateLimitingSampler parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RateLimitingSampler parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RateLimitingSampler parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RateLimitingSampler parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RateLimitingSampler parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RateLimitingSampler parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m38024toBuilder();
    }

    public static Builder newBuilder(RateLimitingSampler rateLimitingSampler) {
        return DEFAULT_INSTANCE.m38024toBuilder().mergeFrom(rateLimitingSampler);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public RateLimitingSampler m38019getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<RateLimitingSampler> getParserForType() {
        return PARSER;
    }

    @Override // io.opencensus.proto.trace.v1.RateLimitingSamplerOrBuilder
    public long getQps() {
        return this.qps_;
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

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TraceConfigProto.internal_static_opencensus_proto_trace_v1_RateLimitingSampler_fieldAccessorTable.ensureFieldAccessorsInitialized(RateLimitingSampler.class, Builder.class);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        long j = this.qps_;
        if (j != 0) {
            codedOutputStream.writeInt64(1, j);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        long j = this.qps_;
        int iComputeInt64Size = (j != 0 ? CodedOutputStream.computeInt64Size(1, j) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeInt64Size;
        return iComputeInt64Size;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RateLimitingSampler)) {
            return super.equals(obj);
        }
        RateLimitingSampler rateLimitingSampler = (RateLimitingSampler) obj;
        return getQps() == rateLimitingSampler.getQps() && this.unknownFields.equals(rateLimitingSampler.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(getQps())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m38021newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m38024toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RateLimitingSamplerOrBuilder {
        private long qps_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TraceConfigProto.internal_static_opencensus_proto_trace_v1_RateLimitingSampler_descriptor;
        }

        @Override // io.opencensus.proto.trace.v1.RateLimitingSamplerOrBuilder
        public long getQps() {
            return this.qps_;
        }

        public Builder setQps(long j) {
            this.qps_ = j;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TraceConfigProto.internal_static_opencensus_proto_trace_v1_RateLimitingSampler_fieldAccessorTable.ensureFieldAccessorsInitialized(RateLimitingSampler.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RateLimitingSampler.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38035clear() {
            super.clear();
            this.qps_ = 0L;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return TraceConfigProto.internal_static_opencensus_proto_trace_v1_RateLimitingSampler_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RateLimitingSampler m38048getDefaultInstanceForType() {
            return RateLimitingSampler.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RateLimitingSampler m38029build() throws UninitializedMessageException {
            RateLimitingSampler rateLimitingSamplerM38031buildPartial = m38031buildPartial();
            if (rateLimitingSamplerM38031buildPartial.isInitialized()) {
                return rateLimitingSamplerM38031buildPartial;
            }
            throw newUninitializedMessageException(rateLimitingSamplerM38031buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RateLimitingSampler m38031buildPartial() {
            RateLimitingSampler rateLimitingSampler = new RateLimitingSampler(this);
            rateLimitingSampler.qps_ = this.qps_;
            onBuilt();
            return rateLimitingSampler;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38047clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38059setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38037clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38040clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38061setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38027addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m38052mergeFrom(Message message) {
            if (message instanceof RateLimitingSampler) {
                return mergeFrom((RateLimitingSampler) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RateLimitingSampler rateLimitingSampler) {
            if (rateLimitingSampler == RateLimitingSampler.getDefaultInstance()) {
                return this;
            }
            if (rateLimitingSampler.getQps() != 0) {
                setQps(rateLimitingSampler.getQps());
            }
            m38057mergeUnknownFields(rateLimitingSampler.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.trace.v1.RateLimitingSampler.Builder m38053mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.trace.v1.RateLimitingSampler.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.trace.v1.RateLimitingSampler r3 = (io.opencensus.proto.trace.v1.RateLimitingSampler) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.trace.v1.RateLimitingSampler r4 = (io.opencensus.proto.trace.v1.RateLimitingSampler) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.trace.v1.RateLimitingSampler.Builder.m38053mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.trace.v1.RateLimitingSampler$Builder");
        }

        public Builder clearQps() {
            this.qps_ = 0L;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m38063setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m38057mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
