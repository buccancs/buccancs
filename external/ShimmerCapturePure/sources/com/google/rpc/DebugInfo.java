package com.google.rpc;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
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

/* loaded from: classes2.dex */
public final class DebugInfo extends GeneratedMessageV3 implements DebugInfoOrBuilder {
    public static final int DETAIL_FIELD_NUMBER = 2;
    public static final int STACK_ENTRIES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final DebugInfo DEFAULT_INSTANCE = new DebugInfo();
    private static final Parser<DebugInfo> PARSER = new AbstractParser<DebugInfo>() { // from class: com.google.rpc.DebugInfo.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public DebugInfo m3493parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DebugInfo(codedInputStream, extensionRegistryLite);
        }
    };
    private int bitField0_;
    private volatile Object detail_;
    private byte memoizedIsInitialized;
    private LazyStringList stackEntries_;

    private DebugInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DebugInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.stackEntries_ = LazyStringArrayList.EMPTY;
        this.detail_ = "";
    }

    private DebugInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if (!(z2 & true)) {
                                    this.stackEntries_ = new LazyStringArrayList();
                                    z2 |= true;
                                }
                                this.stackEntries_.add(stringRequireUtf8);
                            } else if (tag == 18) {
                                this.detail_ = codedInputStream.readStringRequireUtf8();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    }
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if (z2 & true) {
                    this.stackEntries_ = this.stackEntries_.getUnmodifiableView();
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static DebugInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DebugInfo> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_descriptor;
    }

    public static DebugInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DebugInfo) PARSER.parseFrom(byteBuffer);
    }

    public static DebugInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DebugInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DebugInfo) PARSER.parseFrom(byteString);
    }

    public static DebugInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DebugInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DebugInfo) PARSER.parseFrom(bArr);
    }

    public static DebugInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DebugInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DebugInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DebugInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DebugInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DebugInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3491toBuilder();
    }

    public static Builder newBuilder(DebugInfo debugInfo) {
        return DEFAULT_INSTANCE.m3491toBuilder().mergeFrom(debugInfo);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public DebugInfo m3485getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<DebugInfo> getParserForType() {
        return PARSER;
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    /* renamed from: getStackEntriesList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo3487getStackEntriesList() {
        return this.stackEntries_;
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
        return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DebugInfo.class, Builder.class);
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public int getStackEntriesCount() {
        return this.stackEntries_.size();
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public String getStackEntries(int i) {
        return (String) this.stackEntries_.get(i);
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public ByteString getStackEntriesBytes(int i) {
        return this.stackEntries_.getByteString(i);
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public String getDetail() {
        Object obj = this.detail_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.detail_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public ByteString getDetailBytes() {
        Object obj = this.detail_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.detail_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.stackEntries_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.stackEntries_.getRaw(i));
        }
        if (!getDetailBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.detail_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.stackEntries_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.stackEntries_.getRaw(i2));
        }
        int size = iComputeStringSizeNoTag + mo3487getStackEntriesList().size();
        if (!getDetailBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(2, this.detail_);
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DebugInfo)) {
            return super.equals(obj);
        }
        DebugInfo debugInfo = (DebugInfo) obj;
        return mo3487getStackEntriesList().equals(debugInfo.mo3487getStackEntriesList()) && getDetail().equals(debugInfo.getDetail()) && this.unknownFields.equals(debugInfo.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getStackEntriesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + mo3487getStackEntriesList().hashCode();
        }
        int iHashCode2 = (((((iHashCode * 37) + 2) * 53) + getDetail().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3488newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3491toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DebugInfoOrBuilder {
        private int bitField0_;
        private Object detail_;
        private LazyStringList stackEntries_;

        private Builder() {
            this.stackEntries_ = LazyStringArrayList.EMPTY;
            this.detail_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.stackEntries_ = LazyStringArrayList.EMPTY;
            this.detail_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DebugInfo.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = DebugInfo.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3502clear() {
            super.clear();
            this.stackEntries_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.detail_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DebugInfo m3515getDefaultInstanceForType() {
            return DebugInfo.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DebugInfo m3496build() throws UninitializedMessageException {
            DebugInfo debugInfoM3498buildPartial = m3498buildPartial();
            if (debugInfoM3498buildPartial.isInitialized()) {
                return debugInfoM3498buildPartial;
            }
            throw newUninitializedMessageException(debugInfoM3498buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DebugInfo m3498buildPartial() {
            DebugInfo debugInfo = new DebugInfo(this);
            if ((this.bitField0_ & 1) != 0) {
                this.stackEntries_ = this.stackEntries_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            debugInfo.stackEntries_ = this.stackEntries_;
            debugInfo.detail_ = this.detail_;
            debugInfo.bitField0_ = 0;
            onBuilt();
            return debugInfo;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3514clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3526setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3504clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3507clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3528setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3494addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3519mergeFrom(Message message) {
            if (message instanceof DebugInfo) {
                return mergeFrom((DebugInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DebugInfo debugInfo) {
            if (debugInfo == DebugInfo.getDefaultInstance()) {
                return this;
            }
            if (!debugInfo.stackEntries_.isEmpty()) {
                if (this.stackEntries_.isEmpty()) {
                    this.stackEntries_ = debugInfo.stackEntries_;
                    this.bitField0_ &= -2;
                } else {
                    ensureStackEntriesIsMutable();
                    this.stackEntries_.addAll(debugInfo.stackEntries_);
                }
                onChanged();
            }
            if (!debugInfo.getDetail().isEmpty()) {
                this.detail_ = debugInfo.detail_;
                onChanged();
            }
            m3524mergeUnknownFields(debugInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.rpc.DebugInfo.Builder m3520mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.DebugInfo.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.rpc.DebugInfo r3 = (com.google.rpc.DebugInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.rpc.DebugInfo r4 = (com.google.rpc.DebugInfo) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.DebugInfo.Builder.m3520mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.DebugInfo$Builder");
        }

        private void ensureStackEntriesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.stackEntries_ = new LazyStringArrayList(this.stackEntries_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        /* renamed from: getStackEntriesList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo3487getStackEntriesList() {
            return this.stackEntries_.getUnmodifiableView();
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public int getStackEntriesCount() {
            return this.stackEntries_.size();
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public String getStackEntries(int i) {
            return (String) this.stackEntries_.get(i);
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public ByteString getStackEntriesBytes(int i) {
            return this.stackEntries_.getByteString(i);
        }

        public Builder setStackEntries(int i, String str) {
            str.getClass();
            ensureStackEntriesIsMutable();
            this.stackEntries_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addStackEntries(String str) {
            str.getClass();
            ensureStackEntriesIsMutable();
            this.stackEntries_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllStackEntries(Iterable<String> iterable) {
            ensureStackEntriesIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.stackEntries_);
            onChanged();
            return this;
        }

        public Builder clearStackEntries() {
            this.stackEntries_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addStackEntriesBytes(ByteString byteString) {
            byteString.getClass();
            DebugInfo.checkByteStringIsUtf8(byteString);
            ensureStackEntriesIsMutable();
            this.stackEntries_.add(byteString);
            onChanged();
            return this;
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public String getDetail() {
            Object obj = this.detail_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.detail_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setDetail(String str) {
            str.getClass();
            this.detail_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public ByteString getDetailBytes() {
            Object obj = this.detail_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.detail_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDetailBytes(ByteString byteString) {
            byteString.getClass();
            DebugInfo.checkByteStringIsUtf8(byteString);
            this.detail_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearDetail() {
            this.detail_ = DebugInfo.getDefaultInstance().getDetail();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3530setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3524mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
