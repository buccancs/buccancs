package com.google.geo.type;

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
import com.google.type.LatLng;
import com.google.type.LatLngOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class Viewport extends GeneratedMessageV3 implements ViewportOrBuilder {
    public static final int HIGH_FIELD_NUMBER = 2;
    public static final int LOW_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final Viewport DEFAULT_INSTANCE = new Viewport();
    private static final Parser<Viewport> PARSER = new AbstractParser<Viewport>() { // from class: com.google.geo.type.Viewport.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Viewport m2937parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Viewport(codedInputStream, extensionRegistryLite);
        }
    };
    private LatLng high_;
    private LatLng low_;
    private byte memoizedIsInitialized;

    private Viewport(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Viewport() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private Viewport(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        LatLng.Builder builderM4229toBuilder;
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
                            LatLng latLng = this.low_;
                            builderM4229toBuilder = latLng != null ? latLng.m4229toBuilder() : null;
                            LatLng latLng2 = (LatLng) codedInputStream.readMessage(LatLng.parser(), extensionRegistryLite);
                            this.low_ = latLng2;
                            if (builderM4229toBuilder != null) {
                                builderM4229toBuilder.mergeFrom(latLng2);
                                this.low_ = builderM4229toBuilder.m4236buildPartial();
                            }
                        } else if (tag == 18) {
                            LatLng latLng3 = this.high_;
                            builderM4229toBuilder = latLng3 != null ? latLng3.m4229toBuilder() : null;
                            LatLng latLng4 = (LatLng) codedInputStream.readMessage(LatLng.parser(), extensionRegistryLite);
                            this.high_ = latLng4;
                            if (builderM4229toBuilder != null) {
                                builderM4229toBuilder.mergeFrom(latLng4);
                                this.high_ = builderM4229toBuilder.m4236buildPartial();
                            }
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

    public static Viewport getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Viewport> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ViewportProto.internal_static_google_geo_type_Viewport_descriptor;
    }

    public static Viewport parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Viewport) PARSER.parseFrom(byteBuffer);
    }

    public static Viewport parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Viewport) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Viewport parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Viewport) PARSER.parseFrom(byteString);
    }

    public static Viewport parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Viewport) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Viewport parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Viewport) PARSER.parseFrom(bArr);
    }

    public static Viewport parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Viewport) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Viewport parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Viewport parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Viewport parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Viewport parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Viewport parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Viewport parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m2936toBuilder();
    }

    public static Builder newBuilder(Viewport viewport) {
        return DEFAULT_INSTANCE.m2936toBuilder().mergeFrom(viewport);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Viewport m2931getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Viewport> getParserForType() {
        return PARSER;
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public boolean hasHigh() {
        return this.high_ != null;
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public boolean hasLow() {
        return this.low_ != null;
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
        return ViewportProto.internal_static_google_geo_type_Viewport_fieldAccessorTable.ensureFieldAccessorsInitialized(Viewport.class, Builder.class);
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public LatLng getLow() {
        LatLng latLng = this.low_;
        return latLng == null ? LatLng.getDefaultInstance() : latLng;
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public LatLngOrBuilder getLowOrBuilder() {
        return getLow();
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public LatLng getHigh() {
        LatLng latLng = this.high_;
        return latLng == null ? LatLng.getDefaultInstance() : latLng;
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public LatLngOrBuilder getHighOrBuilder() {
        return getHigh();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.low_ != null) {
            codedOutputStream.writeMessage(1, getLow());
        }
        if (this.high_ != null) {
            codedOutputStream.writeMessage(2, getHigh());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.low_ != null ? CodedOutputStream.computeMessageSize(1, getLow()) : 0;
        if (this.high_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getHigh());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Viewport)) {
            return super.equals(obj);
        }
        Viewport viewport = (Viewport) obj;
        if (hasLow() != viewport.hasLow()) {
            return false;
        }
        if ((!hasLow() || getLow().equals(viewport.getLow())) && hasHigh() == viewport.hasHigh()) {
            return (!hasHigh() || getHigh().equals(viewport.getHigh())) && this.unknownFields.equals(viewport.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasLow()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getLow().hashCode();
        }
        if (hasHigh()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getHigh().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m2934newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m2936toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m2933newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ViewportOrBuilder {
        private SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> highBuilder_;
        private LatLng high_;
        private SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> lowBuilder_;
        private LatLng low_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ViewportProto.internal_static_google_geo_type_Viewport_descriptor;
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public boolean hasHigh() {
            return (this.highBuilder_ == null && this.high_ == null) ? false : true;
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public boolean hasLow() {
            return (this.lowBuilder_ == null && this.low_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ViewportProto.internal_static_google_geo_type_Viewport_fieldAccessorTable.ensureFieldAccessorsInitialized(Viewport.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Viewport.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2947clear() {
            super.clear();
            if (this.lowBuilder_ == null) {
                this.low_ = null;
            } else {
                this.low_ = null;
                this.lowBuilder_ = null;
            }
            if (this.highBuilder_ == null) {
                this.high_ = null;
            } else {
                this.high_ = null;
                this.highBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ViewportProto.internal_static_google_geo_type_Viewport_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Viewport m2960getDefaultInstanceForType() {
            return Viewport.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Viewport m2941build() throws UninitializedMessageException {
            Viewport viewportM2943buildPartial = m2943buildPartial();
            if (viewportM2943buildPartial.isInitialized()) {
                return viewportM2943buildPartial;
            }
            throw newUninitializedMessageException(viewportM2943buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Viewport m2943buildPartial() {
            Viewport viewport = new Viewport(this);
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.lowBuilder_;
            if (singleFieldBuilderV3 == null) {
                viewport.low_ = this.low_;
            } else {
                viewport.low_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV32 = this.highBuilder_;
            if (singleFieldBuilderV32 == null) {
                viewport.high_ = this.high_;
            } else {
                viewport.high_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return viewport;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2958clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2971setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2949clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2952clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2973setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2939addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m2965mergeFrom(Message message) {
            if (message instanceof Viewport) {
                return mergeFrom((Viewport) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Viewport viewport) {
            if (viewport == Viewport.getDefaultInstance()) {
                return this;
            }
            if (viewport.hasLow()) {
                mergeLow(viewport.getLow());
            }
            if (viewport.hasHigh()) {
                mergeHigh(viewport.getHigh());
            }
            m2969mergeUnknownFields(viewport.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.geo.type.Viewport.Builder m2966mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.geo.type.Viewport.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.geo.type.Viewport r3 = (com.google.geo.type.Viewport) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.geo.type.Viewport r4 = (com.google.geo.type.Viewport) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.geo.type.Viewport.Builder.m2966mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.geo.type.Viewport$Builder");
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public LatLng getLow() {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.lowBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            LatLng latLng = this.low_;
            return latLng == null ? LatLng.getDefaultInstance() : latLng;
        }

        public Builder setLow(LatLng latLng) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.lowBuilder_;
            if (singleFieldBuilderV3 == null) {
                latLng.getClass();
                this.low_ = latLng;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(latLng);
            }
            return this;
        }

        public Builder setLow(LatLng.Builder builder) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.lowBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.low_ = builder.m4234build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m4234build());
            }
            return this;
        }

        public Builder mergeLow(LatLng latLng) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.lowBuilder_;
            if (singleFieldBuilderV3 == null) {
                LatLng latLng2 = this.low_;
                if (latLng2 != null) {
                    this.low_ = LatLng.newBuilder(latLng2).mergeFrom(latLng).m4236buildPartial();
                } else {
                    this.low_ = latLng;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(latLng);
            }
            return this;
        }

        public Builder clearLow() {
            if (this.lowBuilder_ == null) {
                this.low_ = null;
                onChanged();
            } else {
                this.low_ = null;
                this.lowBuilder_ = null;
            }
            return this;
        }

        public LatLng.Builder getLowBuilder() {
            onChanged();
            return getLowFieldBuilder().getBuilder();
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public LatLngOrBuilder getLowOrBuilder() {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.lowBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (LatLngOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            LatLng latLng = this.low_;
            return latLng == null ? LatLng.getDefaultInstance() : latLng;
        }

        private SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> getLowFieldBuilder() {
            if (this.lowBuilder_ == null) {
                this.lowBuilder_ = new SingleFieldBuilderV3<>(getLow(), getParentForChildren(), isClean());
                this.low_ = null;
            }
            return this.lowBuilder_;
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public LatLng getHigh() {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.highBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            LatLng latLng = this.high_;
            return latLng == null ? LatLng.getDefaultInstance() : latLng;
        }

        public Builder setHigh(LatLng latLng) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.highBuilder_;
            if (singleFieldBuilderV3 == null) {
                latLng.getClass();
                this.high_ = latLng;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(latLng);
            }
            return this;
        }

        public Builder setHigh(LatLng.Builder builder) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.highBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.high_ = builder.m4234build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m4234build());
            }
            return this;
        }

        public Builder mergeHigh(LatLng latLng) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.highBuilder_;
            if (singleFieldBuilderV3 == null) {
                LatLng latLng2 = this.high_;
                if (latLng2 != null) {
                    this.high_ = LatLng.newBuilder(latLng2).mergeFrom(latLng).m4236buildPartial();
                } else {
                    this.high_ = latLng;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(latLng);
            }
            return this;
        }

        public Builder clearHigh() {
            if (this.highBuilder_ == null) {
                this.high_ = null;
                onChanged();
            } else {
                this.high_ = null;
                this.highBuilder_ = null;
            }
            return this;
        }

        public LatLng.Builder getHighBuilder() {
            onChanged();
            return getHighFieldBuilder().getBuilder();
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public LatLngOrBuilder getHighOrBuilder() {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.highBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (LatLngOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            LatLng latLng = this.high_;
            return latLng == null ? LatLng.getDefaultInstance() : latLng;
        }

        private SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> getHighFieldBuilder() {
            if (this.highBuilder_ == null) {
                this.highBuilder_ = new SingleFieldBuilderV3<>(getHigh(), getParentForChildren(), isClean());
                this.high_ = null;
            }
            return this.highBuilder_;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m2975setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m2969mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
