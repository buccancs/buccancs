package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class Endpoint extends GeneratedMessageV3 implements EndpointOrBuilder {
    public static final int ALIASES_FIELD_NUMBER = 2;
    public static final int ALLOW_CORS_FIELD_NUMBER = 5;
    public static final int FEATURES_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int TARGET_FIELD_NUMBER = 101;
    private static final long serialVersionUID = 0;
    private static final Endpoint DEFAULT_INSTANCE = new Endpoint();
    private static final Parser<Endpoint> PARSER = new AbstractParser<Endpoint>() { // from class: com.google.api.Endpoint.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Endpoint m1270parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Endpoint(codedInputStream, extensionRegistryLite);
        }
    };
    private LazyStringList aliases_;
    private boolean allowCors_;
    private int bitField0_;
    private LazyStringList features_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private volatile Object target_;

    private Endpoint(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Endpoint() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.aliases_ = LazyStringArrayList.EMPTY;
        this.features_ = LazyStringArrayList.EMPTY;
        this.target_ = "";
    }

    private Endpoint(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if ((i & 2) == 0) {
                                this.aliases_ = new LazyStringArrayList();
                                i |= 2;
                            }
                            this.aliases_.add(stringRequireUtf8);
                        } else if (tag == 34) {
                            String stringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            if ((i & 4) == 0) {
                                this.features_ = new LazyStringArrayList();
                                i |= 4;
                            }
                            this.features_.add(stringRequireUtf82);
                        } else if (tag == 40) {
                            this.allowCors_ = codedInputStream.readBool();
                        } else if (tag == 810) {
                            this.target_ = codedInputStream.readStringRequireUtf8();
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
                if ((i & 2) != 0) {
                    this.aliases_ = this.aliases_.getUnmodifiableView();
                }
                if ((i & 4) != 0) {
                    this.features_ = this.features_.getUnmodifiableView();
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static Endpoint getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Endpoint> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return EndpointProto.internal_static_google_api_Endpoint_descriptor;
    }

    public static Endpoint parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(byteBuffer);
    }

    public static Endpoint parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Endpoint parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(byteString);
    }

    public static Endpoint parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Endpoint parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(bArr);
    }

    public static Endpoint parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Endpoint parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Endpoint parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Endpoint parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Endpoint parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Endpoint parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Endpoint parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m1269toBuilder();
    }

    public static Builder newBuilder(Endpoint endpoint) {
        return DEFAULT_INSTANCE.m1269toBuilder().mergeFrom(endpoint);
    }

    @Override // com.google.api.EndpointOrBuilder
    @Deprecated
    /* renamed from: getAliasesList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo1261getAliasesList() {
        return this.aliases_;
    }

    @Override // com.google.api.EndpointOrBuilder
    public boolean getAllowCors() {
        return this.allowCors_;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Endpoint m1263getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.api.EndpointOrBuilder
    /* renamed from: getFeaturesList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo1264getFeaturesList() {
        return this.features_;
    }

    public Parser<Endpoint> getParserForType() {
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

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EndpointProto.internal_static_google_api_Endpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(Endpoint.class, Builder.class);
    }

    @Override // com.google.api.EndpointOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.EndpointOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.EndpointOrBuilder
    @Deprecated
    public int getAliasesCount() {
        return this.aliases_.size();
    }

    @Override // com.google.api.EndpointOrBuilder
    @Deprecated
    public String getAliases(int i) {
        return (String) this.aliases_.get(i);
    }

    @Override // com.google.api.EndpointOrBuilder
    @Deprecated
    public ByteString getAliasesBytes(int i) {
        return this.aliases_.getByteString(i);
    }

    @Override // com.google.api.EndpointOrBuilder
    public int getFeaturesCount() {
        return this.features_.size();
    }

    @Override // com.google.api.EndpointOrBuilder
    public String getFeatures(int i) {
        return (String) this.features_.get(i);
    }

    @Override // com.google.api.EndpointOrBuilder
    public ByteString getFeaturesBytes(int i) {
        return this.features_.getByteString(i);
    }

    @Override // com.google.api.EndpointOrBuilder
    public String getTarget() {
        Object obj = this.target_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.target_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.EndpointOrBuilder
    public ByteString getTargetBytes() {
        Object obj = this.target_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.target_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        for (int i = 0; i < this.aliases_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.aliases_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.features_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.features_.getRaw(i2));
        }
        boolean z = this.allowCors_;
        if (z) {
            codedOutputStream.writeBool(5, z);
        }
        if (!getTargetBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 101, this.target_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.aliases_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.aliases_.getRaw(i2));
        }
        int size = iComputeStringSize + iComputeStringSizeNoTag + mo1261getAliasesList().size();
        int iComputeStringSizeNoTag2 = 0;
        for (int i3 = 0; i3 < this.features_.size(); i3++) {
            iComputeStringSizeNoTag2 += computeStringSizeNoTag(this.features_.getRaw(i3));
        }
        int size2 = size + iComputeStringSizeNoTag2 + mo1264getFeaturesList().size();
        boolean z = this.allowCors_;
        if (z) {
            size2 += CodedOutputStream.computeBoolSize(5, z);
        }
        if (!getTargetBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(101, this.target_);
        }
        int serializedSize = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Endpoint)) {
            return super.equals(obj);
        }
        Endpoint endpoint = (Endpoint) obj;
        return getName().equals(endpoint.getName()) && mo1261getAliasesList().equals(endpoint.mo1261getAliasesList()) && mo1264getFeaturesList().equals(endpoint.mo1264getFeaturesList()) && getTarget().equals(endpoint.getTarget()) && getAllowCors() == endpoint.getAllowCors() && this.unknownFields.equals(endpoint.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (getAliasesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + mo1261getAliasesList().hashCode();
        }
        if (getFeaturesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + mo1264getFeaturesList().hashCode();
        }
        int iHashCode2 = (((((((((iHashCode * 37) + 101) * 53) + getTarget().hashCode()) * 37) + 5) * 53) + Internal.hashBoolean(getAllowCors())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m1267newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m1269toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1266newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EndpointOrBuilder {
        private LazyStringList aliases_;
        private boolean allowCors_;
        private int bitField0_;
        private LazyStringList features_;
        private Object name_;
        private Object target_;

        private Builder() {
            this.name_ = "";
            this.aliases_ = LazyStringArrayList.EMPTY;
            this.features_ = LazyStringArrayList.EMPTY;
            this.target_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.aliases_ = LazyStringArrayList.EMPTY;
            this.features_ = LazyStringArrayList.EMPTY;
            this.target_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EndpointProto.internal_static_google_api_Endpoint_descriptor;
        }

        @Override // com.google.api.EndpointOrBuilder
        public boolean getAllowCors() {
            return this.allowCors_;
        }

        public Builder setAllowCors(boolean z) {
            this.allowCors_ = z;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EndpointProto.internal_static_google_api_Endpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(Endpoint.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Endpoint.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1280clear() {
            super.clear();
            this.name_ = "";
            this.aliases_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            this.features_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            this.target_ = "";
            this.allowCors_ = false;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return EndpointProto.internal_static_google_api_Endpoint_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Endpoint m1293getDefaultInstanceForType() {
            return Endpoint.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Endpoint m1274build() throws UninitializedMessageException {
            Endpoint endpointM1276buildPartial = m1276buildPartial();
            if (endpointM1276buildPartial.isInitialized()) {
                return endpointM1276buildPartial;
            }
            throw newUninitializedMessageException(endpointM1276buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Endpoint m1276buildPartial() {
            Endpoint endpoint = new Endpoint(this);
            endpoint.name_ = this.name_;
            if ((this.bitField0_ & 2) != 0) {
                this.aliases_ = this.aliases_.getUnmodifiableView();
                this.bitField0_ &= -3;
            }
            endpoint.aliases_ = this.aliases_;
            if ((this.bitField0_ & 4) != 0) {
                this.features_ = this.features_.getUnmodifiableView();
                this.bitField0_ &= -5;
            }
            endpoint.features_ = this.features_;
            endpoint.target_ = this.target_;
            endpoint.allowCors_ = this.allowCors_;
            endpoint.bitField0_ = 0;
            onBuilt();
            return endpoint;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1291clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1304setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1282clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1285clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1306setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1272addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1298mergeFrom(Message message) {
            if (message instanceof Endpoint) {
                return mergeFrom((Endpoint) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Endpoint endpoint) {
            if (endpoint == Endpoint.getDefaultInstance()) {
                return this;
            }
            if (!endpoint.getName().isEmpty()) {
                this.name_ = endpoint.name_;
                onChanged();
            }
            if (!endpoint.aliases_.isEmpty()) {
                if (this.aliases_.isEmpty()) {
                    this.aliases_ = endpoint.aliases_;
                    this.bitField0_ &= -3;
                } else {
                    ensureAliasesIsMutable();
                    this.aliases_.addAll(endpoint.aliases_);
                }
                onChanged();
            }
            if (!endpoint.features_.isEmpty()) {
                if (this.features_.isEmpty()) {
                    this.features_ = endpoint.features_;
                    this.bitField0_ &= -5;
                } else {
                    ensureFeaturesIsMutable();
                    this.features_.addAll(endpoint.features_);
                }
                onChanged();
            }
            if (!endpoint.getTarget().isEmpty()) {
                this.target_ = endpoint.target_;
                onChanged();
            }
            if (endpoint.getAllowCors()) {
                setAllowCors(endpoint.getAllowCors());
            }
            m1302mergeUnknownFields(endpoint.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.api.Endpoint.Builder m1299mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Endpoint.access$1100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.Endpoint r3 = (com.google.api.Endpoint) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.Endpoint r4 = (com.google.api.Endpoint) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Endpoint.Builder.m1299mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Endpoint$Builder");
        }

        @Override // com.google.api.EndpointOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setName(String str) {
            str.getClass();
            this.name_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.EndpointOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNameBytes(ByteString byteString) {
            byteString.getClass();
            Endpoint.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = Endpoint.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        private void ensureAliasesIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.aliases_ = new LazyStringArrayList(this.aliases_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.api.EndpointOrBuilder
        @Deprecated
        /* renamed from: getAliasesList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo1261getAliasesList() {
            return this.aliases_.getUnmodifiableView();
        }

        @Override // com.google.api.EndpointOrBuilder
        @Deprecated
        public int getAliasesCount() {
            return this.aliases_.size();
        }

        @Override // com.google.api.EndpointOrBuilder
        @Deprecated
        public String getAliases(int i) {
            return (String) this.aliases_.get(i);
        }

        @Override // com.google.api.EndpointOrBuilder
        @Deprecated
        public ByteString getAliasesBytes(int i) {
            return this.aliases_.getByteString(i);
        }

        @Deprecated
        public Builder setAliases(int i, String str) {
            str.getClass();
            ensureAliasesIsMutable();
            this.aliases_.set(i, str);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addAliases(String str) {
            str.getClass();
            ensureAliasesIsMutable();
            this.aliases_.add(str);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addAllAliases(Iterable<String> iterable) {
            ensureAliasesIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.aliases_);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder clearAliases() {
            this.aliases_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addAliasesBytes(ByteString byteString) {
            byteString.getClass();
            Endpoint.checkByteStringIsUtf8(byteString);
            ensureAliasesIsMutable();
            this.aliases_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureFeaturesIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.features_ = new LazyStringArrayList(this.features_);
                this.bitField0_ |= 4;
            }
        }

        @Override // com.google.api.EndpointOrBuilder
        /* renamed from: getFeaturesList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo1264getFeaturesList() {
            return this.features_.getUnmodifiableView();
        }

        @Override // com.google.api.EndpointOrBuilder
        public int getFeaturesCount() {
            return this.features_.size();
        }

        @Override // com.google.api.EndpointOrBuilder
        public String getFeatures(int i) {
            return (String) this.features_.get(i);
        }

        @Override // com.google.api.EndpointOrBuilder
        public ByteString getFeaturesBytes(int i) {
            return this.features_.getByteString(i);
        }

        public Builder setFeatures(int i, String str) {
            str.getClass();
            ensureFeaturesIsMutable();
            this.features_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addFeatures(String str) {
            str.getClass();
            ensureFeaturesIsMutable();
            this.features_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllFeatures(Iterable<String> iterable) {
            ensureFeaturesIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.features_);
            onChanged();
            return this;
        }

        public Builder clearFeatures() {
            this.features_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            onChanged();
            return this;
        }

        public Builder addFeaturesBytes(ByteString byteString) {
            byteString.getClass();
            Endpoint.checkByteStringIsUtf8(byteString);
            ensureFeaturesIsMutable();
            this.features_.add(byteString);
            onChanged();
            return this;
        }

        @Override // com.google.api.EndpointOrBuilder
        public String getTarget() {
            Object obj = this.target_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.target_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setTarget(String str) {
            str.getClass();
            this.target_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.EndpointOrBuilder
        public ByteString getTargetBytes() {
            Object obj = this.target_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.target_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setTargetBytes(ByteString byteString) {
            byteString.getClass();
            Endpoint.checkByteStringIsUtf8(byteString);
            this.target_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearTarget() {
            this.target_ = Endpoint.getDefaultInstance().getTarget();
            onChanged();
            return this;
        }

        public Builder clearAllowCors() {
            this.allowCors_ = false;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1308setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1302mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
