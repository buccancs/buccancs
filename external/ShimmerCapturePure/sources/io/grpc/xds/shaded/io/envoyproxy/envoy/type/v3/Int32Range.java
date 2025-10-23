package io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3;

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
public final class Int32Range extends GeneratedMessageV3 implements Int32RangeOrBuilder {
    public static final int END_FIELD_NUMBER = 2;
    public static final int START_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final Int32Range DEFAULT_INSTANCE = new Int32Range();
    private static final Parser<Int32Range> PARSER = new AbstractParser<Int32Range>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int32Range.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Int32Range m35072parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Int32Range(codedInputStream, extensionRegistryLite);
        }
    };
    private int end_;
    private byte memoizedIsInitialized;
    private int start_;

    private Int32Range(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Int32Range() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private Int32Range(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.start_ = codedInputStream.readInt32();
                            } else if (tag == 16) {
                                this.end_ = codedInputStream.readInt32();
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

    public static Int32Range getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Int32Range> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RangeProto.internal_static_envoy_type_v3_Int32Range_descriptor;
    }

    public static Int32Range parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Int32Range) PARSER.parseFrom(byteBuffer);
    }

    public static Int32Range parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Int32Range) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Int32Range parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Int32Range) PARSER.parseFrom(byteString);
    }

    public static Int32Range parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Int32Range) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Int32Range parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Int32Range) PARSER.parseFrom(bArr);
    }

    public static Int32Range parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Int32Range) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Int32Range parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Int32Range parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Int32Range parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Int32Range parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Int32Range parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Int32Range parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m35070toBuilder();
    }

    public static Builder newBuilder(Int32Range int32Range) {
        return DEFAULT_INSTANCE.m35070toBuilder().mergeFrom(int32Range);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Int32Range m35065getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int32RangeOrBuilder
    public int getEnd() {
        return this.end_;
    }

    public Parser<Int32Range> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int32RangeOrBuilder
    public int getStart() {
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
        return new Int32Range();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RangeProto.internal_static_envoy_type_v3_Int32Range_fieldAccessorTable.ensureFieldAccessorsInitialized(Int32Range.class, Builder.class);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i = this.start_;
        if (i != 0) {
            codedOutputStream.writeInt32(1, i);
        }
        int i2 = this.end_;
        if (i2 != 0) {
            codedOutputStream.writeInt32(2, i2);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = this.start_;
        int iComputeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) : 0;
        int i3 = this.end_;
        if (i3 != 0) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(2, i3);
        }
        int serializedSize = iComputeInt32Size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Int32Range)) {
            return super.equals(obj);
        }
        Int32Range int32Range = (Int32Range) obj;
        return getStart() == int32Range.getStart() && getEnd() == int32Range.getEnd() && this.unknownFields.equals(int32Range.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getStart()) * 37) + 2) * 53) + getEnd()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m35067newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m35070toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements Int32RangeOrBuilder {
        private int end_;
        private int start_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RangeProto.internal_static_envoy_type_v3_Int32Range_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int32RangeOrBuilder
        public int getEnd() {
            return this.end_;
        }

        public Builder setEnd(int i) {
            this.end_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int32RangeOrBuilder
        public int getStart() {
            return this.start_;
        }

        public Builder setStart(int i) {
            this.start_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RangeProto.internal_static_envoy_type_v3_Int32Range_fieldAccessorTable.ensureFieldAccessorsInitialized(Int32Range.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Int32Range.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m35081clear() {
            super.clear();
            this.start_ = 0;
            this.end_ = 0;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return RangeProto.internal_static_envoy_type_v3_Int32Range_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Int32Range m35094getDefaultInstanceForType() {
            return Int32Range.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Int32Range m35075build() throws UninitializedMessageException {
            Int32Range int32RangeM35077buildPartial = m35077buildPartial();
            if (int32RangeM35077buildPartial.isInitialized()) {
                return int32RangeM35077buildPartial;
            }
            throw newUninitializedMessageException(int32RangeM35077buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Int32Range m35077buildPartial() {
            Int32Range int32Range = new Int32Range(this);
            int32Range.start_ = this.start_;
            int32Range.end_ = this.end_;
            onBuilt();
            return int32Range;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m35093clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m35105setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m35083clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m35086clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m35107setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m35073addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m35098mergeFrom(Message message) {
            if (message instanceof Int32Range) {
                return mergeFrom((Int32Range) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Int32Range int32Range) {
            if (int32Range == Int32Range.getDefaultInstance()) {
                return this;
            }
            if (int32Range.getStart() != 0) {
                setStart(int32Range.getStart());
            }
            if (int32Range.getEnd() != 0) {
                setEnd(int32Range.getEnd());
            }
            m35103mergeUnknownFields(int32Range.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int32Range.Builder m35099mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int32Range.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int32Range r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int32Range) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int32Range r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int32Range) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int32Range.Builder.m35099mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int32Range$Builder");
        }

        public Builder clearStart() {
            this.start_ = 0;
            onChanged();
            return this;
        }

        public Builder clearEnd() {
            this.end_ = 0;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m35109setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m35103mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
