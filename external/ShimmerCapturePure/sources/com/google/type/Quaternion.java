package com.google.type;

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

/* loaded from: classes2.dex */
public final class Quaternion extends GeneratedMessageV3 implements QuaternionOrBuilder {
    public static final int W_FIELD_NUMBER = 4;
    public static final int X_FIELD_NUMBER = 1;
    public static final int Y_FIELD_NUMBER = 2;
    public static final int Z_FIELD_NUMBER = 3;
    private static final Quaternion DEFAULT_INSTANCE = new Quaternion();
    private static final Parser<Quaternion> PARSER = new AbstractParser<Quaternion>() { // from class: com.google.type.Quaternion.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Quaternion m4371parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Quaternion(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private double w_;
    private double x_;
    private double y_;
    private double z_;

    private Quaternion(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Quaternion() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private Quaternion(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 9) {
                            this.x_ = codedInputStream.readDouble();
                        } else if (tag == 17) {
                            this.y_ = codedInputStream.readDouble();
                        } else if (tag == 25) {
                            this.z_ = codedInputStream.readDouble();
                        } else if (tag == 33) {
                            this.w_ = codedInputStream.readDouble();
                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
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

    public static Quaternion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Quaternion> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return QuaternionProto.internal_static_google_type_Quaternion_descriptor;
    }

    public static Quaternion parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Quaternion) PARSER.parseFrom(byteBuffer);
    }

    public static Quaternion parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Quaternion) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Quaternion parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Quaternion) PARSER.parseFrom(byteString);
    }

    public static Quaternion parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Quaternion) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Quaternion parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Quaternion) PARSER.parseFrom(bArr);
    }

    public static Quaternion parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Quaternion) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Quaternion parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Quaternion parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Quaternion parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Quaternion parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Quaternion parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Quaternion parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m4369toBuilder();
    }

    public static Builder newBuilder(Quaternion quaternion) {
        return DEFAULT_INSTANCE.m4369toBuilder().mergeFrom(quaternion);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Quaternion m4364getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Quaternion> getParserForType() {
        return PARSER;
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getW() {
        return this.w_;
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getX() {
        return this.x_;
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getY() {
        return this.y_;
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getZ() {
        return this.z_;
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
        return QuaternionProto.internal_static_google_type_Quaternion_fieldAccessorTable.ensureFieldAccessorsInitialized(Quaternion.class, Builder.class);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        double d = this.x_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(1, d);
        }
        double d2 = this.y_;
        if (d2 != 0.0d) {
            codedOutputStream.writeDouble(2, d2);
        }
        double d3 = this.z_;
        if (d3 != 0.0d) {
            codedOutputStream.writeDouble(3, d3);
        }
        double d4 = this.w_;
        if (d4 != 0.0d) {
            codedOutputStream.writeDouble(4, d4);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        double d = this.x_;
        int iComputeDoubleSize = d != 0.0d ? CodedOutputStream.computeDoubleSize(1, d) : 0;
        double d2 = this.y_;
        if (d2 != 0.0d) {
            iComputeDoubleSize += CodedOutputStream.computeDoubleSize(2, d2);
        }
        double d3 = this.z_;
        if (d3 != 0.0d) {
            iComputeDoubleSize += CodedOutputStream.computeDoubleSize(3, d3);
        }
        double d4 = this.w_;
        if (d4 != 0.0d) {
            iComputeDoubleSize += CodedOutputStream.computeDoubleSize(4, d4);
        }
        int serializedSize = iComputeDoubleSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Quaternion)) {
            return super.equals(obj);
        }
        Quaternion quaternion = (Quaternion) obj;
        return Double.doubleToLongBits(getX()) == Double.doubleToLongBits(quaternion.getX()) && Double.doubleToLongBits(getY()) == Double.doubleToLongBits(quaternion.getY()) && Double.doubleToLongBits(getZ()) == Double.doubleToLongBits(quaternion.getZ()) && Double.doubleToLongBits(getW()) == Double.doubleToLongBits(quaternion.getW()) && this.unknownFields.equals(quaternion.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getX()))) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getY()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getZ()))) * 37) + 4) * 53) + Internal.hashLong(Double.doubleToLongBits(getW()))) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m4366newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m4369toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements QuaternionOrBuilder {
        private double w_;
        private double x_;
        private double y_;
        private double z_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return QuaternionProto.internal_static_google_type_Quaternion_descriptor;
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getW() {
            return this.w_;
        }

        public Builder setW(double d) {
            this.w_ = d;
            onChanged();
            return this;
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getX() {
            return this.x_;
        }

        public Builder setX(double d) {
            this.x_ = d;
            onChanged();
            return this;
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getY() {
            return this.y_;
        }

        public Builder setY(double d) {
            this.y_ = d;
            onChanged();
            return this;
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getZ() {
            return this.z_;
        }

        public Builder setZ(double d) {
            this.z_ = d;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return QuaternionProto.internal_static_google_type_Quaternion_fieldAccessorTable.ensureFieldAccessorsInitialized(Quaternion.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Quaternion.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4380clear() {
            super.clear();
            this.x_ = 0.0d;
            this.y_ = 0.0d;
            this.z_ = 0.0d;
            this.w_ = 0.0d;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return QuaternionProto.internal_static_google_type_Quaternion_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Quaternion m4393getDefaultInstanceForType() {
            return Quaternion.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Quaternion m4374build() throws UninitializedMessageException {
            Quaternion quaternionM4376buildPartial = m4376buildPartial();
            if (quaternionM4376buildPartial.isInitialized()) {
                return quaternionM4376buildPartial;
            }
            throw newUninitializedMessageException(quaternionM4376buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Quaternion m4376buildPartial() {
            Quaternion quaternion = new Quaternion(this);
            quaternion.x_ = this.x_;
            quaternion.y_ = this.y_;
            quaternion.z_ = this.z_;
            quaternion.w_ = this.w_;
            onBuilt();
            return quaternion;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4392clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4404setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4382clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4385clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4406setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4372addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4397mergeFrom(Message message) {
            if (message instanceof Quaternion) {
                return mergeFrom((Quaternion) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Quaternion quaternion) {
            if (quaternion == Quaternion.getDefaultInstance()) {
                return this;
            }
            if (quaternion.getX() != 0.0d) {
                setX(quaternion.getX());
            }
            if (quaternion.getY() != 0.0d) {
                setY(quaternion.getY());
            }
            if (quaternion.getZ() != 0.0d) {
                setZ(quaternion.getZ());
            }
            if (quaternion.getW() != 0.0d) {
                setW(quaternion.getW());
            }
            m4402mergeUnknownFields(quaternion.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.type.Quaternion.Builder m4398mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.type.Quaternion.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.type.Quaternion r3 = (com.google.type.Quaternion) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.type.Quaternion r4 = (com.google.type.Quaternion) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.type.Quaternion.Builder.m4398mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.type.Quaternion$Builder");
        }

        public Builder clearX() {
            this.x_ = 0.0d;
            onChanged();
            return this;
        }

        public Builder clearY() {
            this.y_ = 0.0d;
            onChanged();
            return this;
        }

        public Builder clearZ() {
            this.z_ = 0.0d;
            onChanged();
            return this;
        }

        public Builder clearW() {
            this.w_ = 0.0d;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m4408setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m4402mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
