package io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3;

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
public final class DoubleRange extends GeneratedMessageV3 implements DoubleRangeOrBuilder {
    public static final int END_FIELD_NUMBER = 2;
    public static final int START_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final DoubleRange DEFAULT_INSTANCE = new DoubleRange();
    private static final Parser<DoubleRange> PARSER = new AbstractParser<DoubleRange>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.DoubleRange.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public DoubleRange m34979parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DoubleRange(codedInputStream, extensionRegistryLite);
        }
    };
    private double end_;
    private byte memoizedIsInitialized;
    private double start_;

    private DoubleRange(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DoubleRange() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private DoubleRange(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.start_ = codedInputStream.readDouble();
                            } else if (tag == 17) {
                                this.end_ = codedInputStream.readDouble();
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

    public static DoubleRange getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DoubleRange> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RangeProto.internal_static_envoy_type_v3_DoubleRange_descriptor;
    }

    public static DoubleRange parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DoubleRange) PARSER.parseFrom(byteBuffer);
    }

    public static DoubleRange parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DoubleRange) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DoubleRange parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DoubleRange) PARSER.parseFrom(byteString);
    }

    public static DoubleRange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DoubleRange) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DoubleRange parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DoubleRange) PARSER.parseFrom(bArr);
    }

    public static DoubleRange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DoubleRange) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DoubleRange parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DoubleRange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DoubleRange parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DoubleRange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DoubleRange parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DoubleRange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m34977toBuilder();
    }

    public static Builder newBuilder(DoubleRange doubleRange) {
        return DEFAULT_INSTANCE.m34977toBuilder().mergeFrom(doubleRange);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public DoubleRange m34972getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.DoubleRangeOrBuilder
    public double getEnd() {
        return this.end_;
    }

    public Parser<DoubleRange> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.DoubleRangeOrBuilder
    public double getStart() {
        return this.start_;
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
        return new DoubleRange();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RangeProto.internal_static_envoy_type_v3_DoubleRange_fieldAccessorTable.ensureFieldAccessorsInitialized(DoubleRange.class, Builder.class);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        double d = this.start_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(1, d);
        }
        double d2 = this.end_;
        if (d2 != 0.0d) {
            codedOutputStream.writeDouble(2, d2);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        double d = this.start_;
        int iComputeDoubleSize = d != 0.0d ? CodedOutputStream.computeDoubleSize(1, d) : 0;
        double d2 = this.end_;
        if (d2 != 0.0d) {
            iComputeDoubleSize += CodedOutputStream.computeDoubleSize(2, d2);
        }
        int serializedSize = iComputeDoubleSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DoubleRange)) {
            return super.equals(obj);
        }
        DoubleRange doubleRange = (DoubleRange) obj;
        return Double.doubleToLongBits(getStart()) == Double.doubleToLongBits(doubleRange.getStart()) && Double.doubleToLongBits(getEnd()) == Double.doubleToLongBits(doubleRange.getEnd()) && this.unknownFields.equals(doubleRange.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getStart()))) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getEnd()))) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m34974newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m34977toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DoubleRangeOrBuilder {
        private double end_;
        private double start_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RangeProto.internal_static_envoy_type_v3_DoubleRange_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.DoubleRangeOrBuilder
        public double getEnd() {
            return this.end_;
        }

        public Builder setEnd(double d) {
            this.end_ = d;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.DoubleRangeOrBuilder
        public double getStart() {
            return this.start_;
        }

        public Builder setStart(double d) {
            this.start_ = d;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RangeProto.internal_static_envoy_type_v3_DoubleRange_fieldAccessorTable.ensureFieldAccessorsInitialized(DoubleRange.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = DoubleRange.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34988clear() {
            super.clear();
            this.start_ = 0.0d;
            this.end_ = 0.0d;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return RangeProto.internal_static_envoy_type_v3_DoubleRange_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DoubleRange m35001getDefaultInstanceForType() {
            return DoubleRange.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DoubleRange m34982build() throws UninitializedMessageException {
            DoubleRange doubleRangeM34984buildPartial = m34984buildPartial();
            if (doubleRangeM34984buildPartial.isInitialized()) {
                return doubleRangeM34984buildPartial;
            }
            throw newUninitializedMessageException(doubleRangeM34984buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DoubleRange m34984buildPartial() {
            DoubleRange doubleRange = new DoubleRange(this);
            doubleRange.start_ = this.start_;
            doubleRange.end_ = this.end_;
            onBuilt();
            return doubleRange;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m35000clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m35012setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34990clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34993clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m35014setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34980addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m35005mergeFrom(Message message) {
            if (message instanceof DoubleRange) {
                return mergeFrom((DoubleRange) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DoubleRange doubleRange) {
            if (doubleRange == DoubleRange.getDefaultInstance()) {
                return this;
            }
            if (doubleRange.getStart() != 0.0d) {
                setStart(doubleRange.getStart());
            }
            if (doubleRange.getEnd() != 0.0d) {
                setEnd(doubleRange.getEnd());
            }
            m35010mergeUnknownFields(doubleRange.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.DoubleRange.Builder m35006mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.DoubleRange.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.DoubleRange r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.DoubleRange) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.DoubleRange r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.DoubleRange) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.DoubleRange.Builder.m35006mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.DoubleRange$Builder");
        }

        public Builder clearStart() {
            this.start_ = 0.0d;
            onChanged();
            return this;
        }

        public Builder clearEnd() {
            this.end_ = 0.0d;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m35016setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m35010mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
