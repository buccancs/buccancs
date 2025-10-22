package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BuildVersion;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class Extension extends GeneratedMessageV3 implements ExtensionOrBuilder {
    public static final int CATEGORY_FIELD_NUMBER = 2;
    public static final int DISABLED_FIELD_NUMBER = 5;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int TYPE_DESCRIPTOR_FIELD_NUMBER = 3;
    public static final int VERSION_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private static final Extension DEFAULT_INSTANCE = new Extension();
    private static final Parser<Extension> PARSER = new AbstractParser<Extension>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Extension.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Extension m14814parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Extension(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object category_;
    private boolean disabled_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private volatile Object typeDescriptor_;
    private BuildVersion version_;

    private Extension(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Extension() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.category_ = "";
        this.typeDescriptor_ = "";
    }

    private Extension(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            this.category_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 26) {
                            this.typeDescriptor_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 34) {
                            BuildVersion buildVersion = this.version_;
                            BuildVersion.Builder builderM14536toBuilder = buildVersion != null ? buildVersion.m14536toBuilder() : null;
                            BuildVersion buildVersion2 = (BuildVersion) codedInputStream.readMessage(BuildVersion.parser(), extensionRegistryLite);
                            this.version_ = buildVersion2;
                            if (builderM14536toBuilder != null) {
                                builderM14536toBuilder.mergeFrom(buildVersion2);
                                this.version_ = builderM14536toBuilder.m14543buildPartial();
                            }
                        } else if (tag == 40) {
                            this.disabled_ = codedInputStream.readBool();
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

    public static Extension getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Extension> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BaseProto.internal_static_envoy_api_v2_core_Extension_descriptor;
    }

    public static Extension parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Extension) PARSER.parseFrom(byteBuffer);
    }

    public static Extension parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Extension) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Extension parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Extension) PARSER.parseFrom(byteString);
    }

    public static Extension parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Extension) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Extension parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Extension) PARSER.parseFrom(bArr);
    }

    public static Extension parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Extension) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Extension parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Extension parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Extension parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Extension parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Extension parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Extension parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m14812toBuilder();
    }

    public static Builder newBuilder(Extension extension) {
        return DEFAULT_INSTANCE.m14812toBuilder().mergeFrom(extension);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Extension m14807getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
    public boolean getDisabled() {
        return this.disabled_;
    }

    public Parser<Extension> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
    public boolean hasVersion() {
        return this.version_ != null;
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
        return new Extension();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BaseProto.internal_static_envoy_api_v2_core_Extension_fieldAccessorTable.ensureFieldAccessorsInitialized(Extension.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
    public String getCategory() {
        Object obj = this.category_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.category_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
    public ByteString getCategoryBytes() {
        Object obj = this.category_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.category_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
    public String getTypeDescriptor() {
        Object obj = this.typeDescriptor_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.typeDescriptor_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
    public ByteString getTypeDescriptorBytes() {
        Object obj = this.typeDescriptor_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.typeDescriptor_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
    public BuildVersion getVersion() {
        BuildVersion buildVersion = this.version_;
        return buildVersion == null ? BuildVersion.getDefaultInstance() : buildVersion;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
    public BuildVersionOrBuilder getVersionOrBuilder() {
        return getVersion();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (!getCategoryBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.category_);
        }
        if (!getTypeDescriptorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.typeDescriptor_);
        }
        if (this.version_ != null) {
            codedOutputStream.writeMessage(4, getVersion());
        }
        boolean z = this.disabled_;
        if (z) {
            codedOutputStream.writeBool(5, z);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (!getCategoryBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.category_);
        }
        if (!getTypeDescriptorBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.typeDescriptor_);
        }
        if (this.version_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(4, getVersion());
        }
        boolean z = this.disabled_;
        if (z) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(5, z);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Extension)) {
            return super.equals(obj);
        }
        Extension extension = (Extension) obj;
        if (getName().equals(extension.getName()) && getCategory().equals(extension.getCategory()) && getTypeDescriptor().equals(extension.getTypeDescriptor()) && hasVersion() == extension.hasVersion()) {
            return (!hasVersion() || getVersion().equals(extension.getVersion())) && getDisabled() == extension.getDisabled() && this.unknownFields.equals(extension.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getCategory().hashCode()) * 37) + 3) * 53) + getTypeDescriptor().hashCode();
        if (hasVersion()) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getVersion().hashCode();
        }
        int iHashBoolean = (((((iHashCode * 37) + 5) * 53) + Internal.hashBoolean(getDisabled())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashBoolean;
        return iHashBoolean;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14809newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14812toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExtensionOrBuilder {
        private Object category_;
        private boolean disabled_;
        private Object name_;
        private Object typeDescriptor_;
        private SingleFieldBuilderV3<BuildVersion, BuildVersion.Builder, BuildVersionOrBuilder> versionBuilder_;
        private BuildVersion version_;

        private Builder() {
            this.name_ = "";
            this.category_ = "";
            this.typeDescriptor_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.category_ = "";
            this.typeDescriptor_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BaseProto.internal_static_envoy_api_v2_core_Extension_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
        public boolean getDisabled() {
            return this.disabled_;
        }

        public Builder setDisabled(boolean z) {
            this.disabled_ = z;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
        public boolean hasVersion() {
            return (this.versionBuilder_ == null && this.version_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BaseProto.internal_static_envoy_api_v2_core_Extension_fieldAccessorTable.ensureFieldAccessorsInitialized(Extension.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Extension.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14823clear() {
            super.clear();
            this.name_ = "";
            this.category_ = "";
            this.typeDescriptor_ = "";
            if (this.versionBuilder_ == null) {
                this.version_ = null;
            } else {
                this.version_ = null;
                this.versionBuilder_ = null;
            }
            this.disabled_ = false;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BaseProto.internal_static_envoy_api_v2_core_Extension_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Extension m14836getDefaultInstanceForType() {
            return Extension.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Extension m14817build() throws UninitializedMessageException {
            Extension extensionM14819buildPartial = m14819buildPartial();
            if (extensionM14819buildPartial.isInitialized()) {
                return extensionM14819buildPartial;
            }
            throw newUninitializedMessageException(extensionM14819buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Extension m14819buildPartial() {
            Extension extension = new Extension(this);
            extension.name_ = this.name_;
            extension.category_ = this.category_;
            extension.typeDescriptor_ = this.typeDescriptor_;
            SingleFieldBuilderV3<BuildVersion, BuildVersion.Builder, BuildVersionOrBuilder> singleFieldBuilderV3 = this.versionBuilder_;
            if (singleFieldBuilderV3 == null) {
                extension.version_ = this.version_;
            } else {
                extension.version_ = singleFieldBuilderV3.build();
            }
            extension.disabled_ = this.disabled_;
            onBuilt();
            return extension;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14835clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14847setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14825clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14828clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14849setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14815addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14840mergeFrom(Message message) {
            if (message instanceof Extension) {
                return mergeFrom((Extension) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Extension extension) {
            if (extension == Extension.getDefaultInstance()) {
                return this;
            }
            if (!extension.getName().isEmpty()) {
                this.name_ = extension.name_;
                onChanged();
            }
            if (!extension.getCategory().isEmpty()) {
                this.category_ = extension.category_;
                onChanged();
            }
            if (!extension.getTypeDescriptor().isEmpty()) {
                this.typeDescriptor_ = extension.typeDescriptor_;
                onChanged();
            }
            if (extension.hasVersion()) {
                mergeVersion(extension.getVersion());
            }
            if (extension.getDisabled()) {
                setDisabled(extension.getDisabled());
            }
            m14845mergeUnknownFields(extension.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Extension.Builder m14841mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Extension.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Extension r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Extension) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Extension r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Extension) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Extension.Builder.m14841mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Extension$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
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
            Extension.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = Extension.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
        public String getCategory() {
            Object obj = this.category_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.category_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setCategory(String str) {
            str.getClass();
            this.category_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
        public ByteString getCategoryBytes() {
            Object obj = this.category_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.category_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setCategoryBytes(ByteString byteString) {
            byteString.getClass();
            Extension.checkByteStringIsUtf8(byteString);
            this.category_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearCategory() {
            this.category_ = Extension.getDefaultInstance().getCategory();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
        public String getTypeDescriptor() {
            Object obj = this.typeDescriptor_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.typeDescriptor_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setTypeDescriptor(String str) {
            str.getClass();
            this.typeDescriptor_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
        public ByteString getTypeDescriptorBytes() {
            Object obj = this.typeDescriptor_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.typeDescriptor_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setTypeDescriptorBytes(ByteString byteString) {
            byteString.getClass();
            Extension.checkByteStringIsUtf8(byteString);
            this.typeDescriptor_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearTypeDescriptor() {
            this.typeDescriptor_ = Extension.getDefaultInstance().getTypeDescriptor();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
        public BuildVersion getVersion() {
            SingleFieldBuilderV3<BuildVersion, BuildVersion.Builder, BuildVersionOrBuilder> singleFieldBuilderV3 = this.versionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            BuildVersion buildVersion = this.version_;
            return buildVersion == null ? BuildVersion.getDefaultInstance() : buildVersion;
        }

        public Builder setVersion(BuildVersion buildVersion) {
            SingleFieldBuilderV3<BuildVersion, BuildVersion.Builder, BuildVersionOrBuilder> singleFieldBuilderV3 = this.versionBuilder_;
            if (singleFieldBuilderV3 == null) {
                buildVersion.getClass();
                this.version_ = buildVersion;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(buildVersion);
            }
            return this;
        }

        public Builder setVersion(BuildVersion.Builder builder) {
            SingleFieldBuilderV3<BuildVersion, BuildVersion.Builder, BuildVersionOrBuilder> singleFieldBuilderV3 = this.versionBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.version_ = builder.m14541build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m14541build());
            }
            return this;
        }

        public Builder mergeVersion(BuildVersion buildVersion) {
            SingleFieldBuilderV3<BuildVersion, BuildVersion.Builder, BuildVersionOrBuilder> singleFieldBuilderV3 = this.versionBuilder_;
            if (singleFieldBuilderV3 == null) {
                BuildVersion buildVersion2 = this.version_;
                if (buildVersion2 != null) {
                    this.version_ = BuildVersion.newBuilder(buildVersion2).mergeFrom(buildVersion).m14543buildPartial();
                } else {
                    this.version_ = buildVersion;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(buildVersion);
            }
            return this;
        }

        public Builder clearVersion() {
            if (this.versionBuilder_ == null) {
                this.version_ = null;
                onChanged();
            } else {
                this.version_ = null;
                this.versionBuilder_ = null;
            }
            return this;
        }

        public BuildVersion.Builder getVersionBuilder() {
            onChanged();
            return getVersionFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ExtensionOrBuilder
        public BuildVersionOrBuilder getVersionOrBuilder() {
            SingleFieldBuilderV3<BuildVersion, BuildVersion.Builder, BuildVersionOrBuilder> singleFieldBuilderV3 = this.versionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (BuildVersionOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            BuildVersion buildVersion = this.version_;
            return buildVersion == null ? BuildVersion.getDefaultInstance() : buildVersion;
        }

        private SingleFieldBuilderV3<BuildVersion, BuildVersion.Builder, BuildVersionOrBuilder> getVersionFieldBuilder() {
            if (this.versionBuilder_ == null) {
                this.versionBuilder_ = new SingleFieldBuilderV3<>(getVersion(), getParentForChildren(), isClean());
                this.version_ = null;
            }
            return this.versionBuilder_;
        }

        public Builder clearDisabled() {
            this.disabled_ = false;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14851setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14845mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
