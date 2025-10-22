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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class Locality extends GeneratedMessageV3 implements LocalityOrBuilder {
    public static final int REGION_FIELD_NUMBER = 1;
    public static final int SUB_ZONE_FIELD_NUMBER = 3;
    public static final int ZONE_FIELD_NUMBER = 2;
    private static final Locality DEFAULT_INSTANCE = new Locality();
    private static final Parser<Locality> PARSER = new AbstractParser<Locality>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Locality.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Locality m23554parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Locality(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private volatile Object region_;
    private volatile Object subZone_;
    private volatile Object zone_;

    private Locality(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Locality() {
        this.memoizedIsInitialized = (byte) -1;
        this.region_ = "";
        this.zone_ = "";
        this.subZone_ = "";
    }

    private Locality(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.region_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            this.zone_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 26) {
                            this.subZone_ = codedInputStream.readStringRequireUtf8();
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

    public static Locality getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Locality> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BaseProto.internal_static_envoy_config_core_v3_Locality_descriptor;
    }

    public static Locality parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Locality) PARSER.parseFrom(byteBuffer);
    }

    public static Locality parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Locality) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Locality parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Locality) PARSER.parseFrom(byteString);
    }

    public static Locality parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Locality) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Locality parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Locality) PARSER.parseFrom(bArr);
    }

    public static Locality parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Locality) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Locality parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Locality parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Locality parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Locality parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Locality parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Locality parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m23552toBuilder();
    }

    public static Builder newBuilder(Locality locality) {
        return DEFAULT_INSTANCE.m23552toBuilder().mergeFrom(locality);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Locality m23547getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Locality> getParserForType() {
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
        return new Locality();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BaseProto.internal_static_envoy_config_core_v3_Locality_fieldAccessorTable.ensureFieldAccessorsInitialized(Locality.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.LocalityOrBuilder
    public String getRegion() {
        Object obj = this.region_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.region_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.LocalityOrBuilder
    public ByteString getRegionBytes() {
        Object obj = this.region_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.region_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.LocalityOrBuilder
    public String getZone() {
        Object obj = this.zone_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.zone_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.LocalityOrBuilder
    public ByteString getZoneBytes() {
        Object obj = this.zone_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.zone_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.LocalityOrBuilder
    public String getSubZone() {
        Object obj = this.subZone_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.subZone_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.LocalityOrBuilder
    public ByteString getSubZoneBytes() {
        Object obj = this.subZone_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.subZone_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getRegionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.region_);
        }
        if (!getZoneBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.zone_);
        }
        if (!getSubZoneBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.subZone_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getRegionBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.region_) : 0;
        if (!getZoneBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.zone_);
        }
        if (!getSubZoneBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.subZone_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Locality)) {
            return super.equals(obj);
        }
        Locality locality = (Locality) obj;
        return getRegion().equals(locality.getRegion()) && getZone().equals(locality.getZone()) && getSubZone().equals(locality.getSubZone()) && this.unknownFields.equals(locality.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getRegion().hashCode()) * 37) + 2) * 53) + getZone().hashCode()) * 37) + 3) * 53) + getSubZone().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m23549newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m23552toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LocalityOrBuilder {
        private Object region_;
        private Object subZone_;
        private Object zone_;

        private Builder() {
            this.region_ = "";
            this.zone_ = "";
            this.subZone_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.region_ = "";
            this.zone_ = "";
            this.subZone_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BaseProto.internal_static_envoy_config_core_v3_Locality_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BaseProto.internal_static_envoy_config_core_v3_Locality_fieldAccessorTable.ensureFieldAccessorsInitialized(Locality.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Locality.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23563clear() {
            super.clear();
            this.region_ = "";
            this.zone_ = "";
            this.subZone_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BaseProto.internal_static_envoy_config_core_v3_Locality_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Locality m23576getDefaultInstanceForType() {
            return Locality.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Locality m23557build() throws UninitializedMessageException {
            Locality localityM23559buildPartial = m23559buildPartial();
            if (localityM23559buildPartial.isInitialized()) {
                return localityM23559buildPartial;
            }
            throw newUninitializedMessageException(localityM23559buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Locality m23559buildPartial() {
            Locality locality = new Locality(this);
            locality.region_ = this.region_;
            locality.zone_ = this.zone_;
            locality.subZone_ = this.subZone_;
            onBuilt();
            return locality;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23575clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23587setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23565clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23568clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23589setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23555addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23580mergeFrom(Message message) {
            if (message instanceof Locality) {
                return mergeFrom((Locality) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Locality locality) {
            if (locality == Locality.getDefaultInstance()) {
                return this;
            }
            if (!locality.getRegion().isEmpty()) {
                this.region_ = locality.region_;
                onChanged();
            }
            if (!locality.getZone().isEmpty()) {
                this.zone_ = locality.zone_;
                onChanged();
            }
            if (!locality.getSubZone().isEmpty()) {
                this.subZone_ = locality.subZone_;
                onChanged();
            }
            m23585mergeUnknownFields(locality.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Locality.Builder m23581mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Locality.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Locality r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Locality) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Locality r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Locality) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Locality.Builder.m23581mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Locality$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.LocalityOrBuilder
        public String getRegion() {
            Object obj = this.region_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.region_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setRegion(String str) {
            str.getClass();
            this.region_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.LocalityOrBuilder
        public ByteString getRegionBytes() {
            Object obj = this.region_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.region_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRegionBytes(ByteString byteString) {
            byteString.getClass();
            Locality.checkByteStringIsUtf8(byteString);
            this.region_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRegion() {
            this.region_ = Locality.getDefaultInstance().getRegion();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.LocalityOrBuilder
        public String getZone() {
            Object obj = this.zone_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.zone_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setZone(String str) {
            str.getClass();
            this.zone_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.LocalityOrBuilder
        public ByteString getZoneBytes() {
            Object obj = this.zone_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.zone_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setZoneBytes(ByteString byteString) {
            byteString.getClass();
            Locality.checkByteStringIsUtf8(byteString);
            this.zone_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearZone() {
            this.zone_ = Locality.getDefaultInstance().getZone();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.LocalityOrBuilder
        public String getSubZone() {
            Object obj = this.subZone_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.subZone_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setSubZone(String str) {
            str.getClass();
            this.subZone_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.LocalityOrBuilder
        public ByteString getSubZoneBytes() {
            Object obj = this.subZone_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.subZone_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSubZoneBytes(ByteString byteString) {
            byteString.getClass();
            Locality.checkByteStringIsUtf8(byteString);
            this.subZone_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearSubZone() {
            this.subZone_ = Locality.getDefaultInstance().getSubZone();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m23591setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m23585mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
