package com.google.rpc;

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

/* loaded from: classes2.dex */
public final class ResourceInfo extends GeneratedMessageV3 implements ResourceInfoOrBuilder {
    public static final int DESCRIPTION_FIELD_NUMBER = 4;
    public static final int OWNER_FIELD_NUMBER = 3;
    public static final int RESOURCE_NAME_FIELD_NUMBER = 2;
    public static final int RESOURCE_TYPE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final ResourceInfo DEFAULT_INSTANCE = new ResourceInfo();
    private static final Parser<ResourceInfo> PARSER = new AbstractParser<ResourceInfo>() { // from class: com.google.rpc.ResourceInfo.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ResourceInfo m3907parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ResourceInfo(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object description_;
    private byte memoizedIsInitialized;
    private volatile Object owner_;
    private volatile Object resourceName_;
    private volatile Object resourceType_;

    private ResourceInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ResourceInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.resourceType_ = "";
        this.resourceName_ = "";
        this.owner_ = "";
        this.description_ = "";
    }

    private ResourceInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.resourceType_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            this.resourceName_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 26) {
                            this.owner_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 34) {
                            this.description_ = codedInputStream.readStringRequireUtf8();
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

    public static ResourceInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ResourceInfo> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_descriptor;
    }

    public static ResourceInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ResourceInfo) PARSER.parseFrom(byteBuffer);
    }

    public static ResourceInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ResourceInfo) PARSER.parseFrom(byteString);
    }

    public static ResourceInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ResourceInfo) PARSER.parseFrom(bArr);
    }

    public static ResourceInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ResourceInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ResourceInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ResourceInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ResourceInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3905toBuilder();
    }

    public static Builder newBuilder(ResourceInfo resourceInfo) {
        return DEFAULT_INSTANCE.m3905toBuilder().mergeFrom(resourceInfo);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ResourceInfo m3900getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ResourceInfo> getParserForType() {
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
        return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ResourceInfo.class, Builder.class);
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public String getResourceType() {
        Object obj = this.resourceType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.resourceType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public ByteString getResourceTypeBytes() {
        Object obj = this.resourceType_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.resourceType_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public String getResourceName() {
        Object obj = this.resourceName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.resourceName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public ByteString getResourceNameBytes() {
        Object obj = this.resourceName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.resourceName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public String getOwner() {
        Object obj = this.owner_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.owner_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public ByteString getOwnerBytes() {
        Object obj = this.owner_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.owner_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public String getDescription() {
        Object obj = this.description_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.description_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public ByteString getDescriptionBytes() {
        Object obj = this.description_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getResourceTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.resourceType_);
        }
        if (!getResourceNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.resourceName_);
        }
        if (!getOwnerBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.owner_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.description_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getResourceTypeBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.resourceType_) : 0;
        if (!getResourceNameBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.resourceName_);
        }
        if (!getOwnerBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.owner_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.description_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResourceInfo)) {
            return super.equals(obj);
        }
        ResourceInfo resourceInfo = (ResourceInfo) obj;
        return getResourceType().equals(resourceInfo.getResourceType()) && getResourceName().equals(resourceInfo.getResourceName()) && getOwner().equals(resourceInfo.getOwner()) && getDescription().equals(resourceInfo.getDescription()) && this.unknownFields.equals(resourceInfo.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getResourceType().hashCode()) * 37) + 2) * 53) + getResourceName().hashCode()) * 37) + 3) * 53) + getOwner().hashCode()) * 37) + 4) * 53) + getDescription().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3902newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3905toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ResourceInfoOrBuilder {
        private Object description_;
        private Object owner_;
        private Object resourceName_;
        private Object resourceType_;

        private Builder() {
            this.resourceType_ = "";
            this.resourceName_ = "";
            this.owner_ = "";
            this.description_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.resourceType_ = "";
            this.resourceName_ = "";
            this.owner_ = "";
            this.description_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ResourceInfo.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ResourceInfo.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3916clear() {
            super.clear();
            this.resourceType_ = "";
            this.resourceName_ = "";
            this.owner_ = "";
            this.description_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ResourceInfo m3929getDefaultInstanceForType() {
            return ResourceInfo.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ResourceInfo m3910build() throws UninitializedMessageException {
            ResourceInfo resourceInfoM3912buildPartial = m3912buildPartial();
            if (resourceInfoM3912buildPartial.isInitialized()) {
                return resourceInfoM3912buildPartial;
            }
            throw newUninitializedMessageException(resourceInfoM3912buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ResourceInfo m3912buildPartial() {
            ResourceInfo resourceInfo = new ResourceInfo(this);
            resourceInfo.resourceType_ = this.resourceType_;
            resourceInfo.resourceName_ = this.resourceName_;
            resourceInfo.owner_ = this.owner_;
            resourceInfo.description_ = this.description_;
            onBuilt();
            return resourceInfo;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3928clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3940setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3918clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3921clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3942setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3908addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3933mergeFrom(Message message) {
            if (message instanceof ResourceInfo) {
                return mergeFrom((ResourceInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ResourceInfo resourceInfo) {
            if (resourceInfo == ResourceInfo.getDefaultInstance()) {
                return this;
            }
            if (!resourceInfo.getResourceType().isEmpty()) {
                this.resourceType_ = resourceInfo.resourceType_;
                onChanged();
            }
            if (!resourceInfo.getResourceName().isEmpty()) {
                this.resourceName_ = resourceInfo.resourceName_;
                onChanged();
            }
            if (!resourceInfo.getOwner().isEmpty()) {
                this.owner_ = resourceInfo.owner_;
                onChanged();
            }
            if (!resourceInfo.getDescription().isEmpty()) {
                this.description_ = resourceInfo.description_;
                onChanged();
            }
            m3938mergeUnknownFields(resourceInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.rpc.ResourceInfo.Builder m3934mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.ResourceInfo.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.rpc.ResourceInfo r3 = (com.google.rpc.ResourceInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.rpc.ResourceInfo r4 = (com.google.rpc.ResourceInfo) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.ResourceInfo.Builder.m3934mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.ResourceInfo$Builder");
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public String getResourceType() {
            Object obj = this.resourceType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.resourceType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setResourceType(String str) {
            str.getClass();
            this.resourceType_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public ByteString getResourceTypeBytes() {
            Object obj = this.resourceType_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.resourceType_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setResourceTypeBytes(ByteString byteString) {
            byteString.getClass();
            ResourceInfo.checkByteStringIsUtf8(byteString);
            this.resourceType_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearResourceType() {
            this.resourceType_ = ResourceInfo.getDefaultInstance().getResourceType();
            onChanged();
            return this;
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public String getResourceName() {
            Object obj = this.resourceName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.resourceName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setResourceName(String str) {
            str.getClass();
            this.resourceName_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public ByteString getResourceNameBytes() {
            Object obj = this.resourceName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.resourceName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setResourceNameBytes(ByteString byteString) {
            byteString.getClass();
            ResourceInfo.checkByteStringIsUtf8(byteString);
            this.resourceName_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearResourceName() {
            this.resourceName_ = ResourceInfo.getDefaultInstance().getResourceName();
            onChanged();
            return this;
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public String getOwner() {
            Object obj = this.owner_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.owner_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setOwner(String str) {
            str.getClass();
            this.owner_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public ByteString getOwnerBytes() {
            Object obj = this.owner_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.owner_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setOwnerBytes(ByteString byteString) {
            byteString.getClass();
            ResourceInfo.checkByteStringIsUtf8(byteString);
            this.owner_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearOwner() {
            this.owner_ = ResourceInfo.getDefaultInstance().getOwner();
            onChanged();
            return this;
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public String getDescription() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.description_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setDescription(String str) {
            str.getClass();
            this.description_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.description_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            byteString.getClass();
            ResourceInfo.checkByteStringIsUtf8(byteString);
            this.description_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearDescription() {
            this.description_ = ResourceInfo.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3944setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3938mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
