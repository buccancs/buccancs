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
public final class LocalizedMessage extends GeneratedMessageV3 implements LocalizedMessageOrBuilder {
    public static final int LOCALE_FIELD_NUMBER = 1;
    public static final int MESSAGE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final LocalizedMessage DEFAULT_INSTANCE = new LocalizedMessage();
    private static final Parser<LocalizedMessage> PARSER = new AbstractParser<LocalizedMessage>() { // from class: com.google.rpc.LocalizedMessage.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public LocalizedMessage m3631parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new LocalizedMessage(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object locale_;
    private byte memoizedIsInitialized;
    private volatile Object message_;

    private LocalizedMessage(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private LocalizedMessage() {
        this.memoizedIsInitialized = (byte) -1;
        this.locale_ = "";
        this.message_ = "";
    }

    private LocalizedMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag == 10) {
                                this.locale_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.message_ = codedInputStream.readStringRequireUtf8();
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

    public static LocalizedMessage getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LocalizedMessage> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_LocalizedMessage_descriptor;
    }

    public static LocalizedMessage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (LocalizedMessage) PARSER.parseFrom(byteBuffer);
    }

    public static LocalizedMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LocalizedMessage) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static LocalizedMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (LocalizedMessage) PARSER.parseFrom(byteString);
    }

    public static LocalizedMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LocalizedMessage) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static LocalizedMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (LocalizedMessage) PARSER.parseFrom(bArr);
    }

    public static LocalizedMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LocalizedMessage) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static LocalizedMessage parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static LocalizedMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LocalizedMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static LocalizedMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LocalizedMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static LocalizedMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3629toBuilder();
    }

    public static Builder newBuilder(LocalizedMessage localizedMessage) {
        return DEFAULT_INSTANCE.m3629toBuilder().mergeFrom(localizedMessage);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public LocalizedMessage m3624getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<LocalizedMessage> getParserForType() {
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
        return ErrorDetailsProto.internal_static_google_rpc_LocalizedMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(LocalizedMessage.class, Builder.class);
    }

    @Override // com.google.rpc.LocalizedMessageOrBuilder
    public String getLocale() {
        Object obj = this.locale_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.locale_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.LocalizedMessageOrBuilder
    public ByteString getLocaleBytes() {
        Object obj = this.locale_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.locale_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.rpc.LocalizedMessageOrBuilder
    public String getMessage() {
        Object obj = this.message_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.message_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.LocalizedMessageOrBuilder
    public ByteString getMessageBytes() {
        Object obj = this.message_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.message_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getLocaleBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.locale_);
        }
        if (!getMessageBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.message_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getLocaleBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.locale_) : 0;
        if (!getMessageBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.message_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocalizedMessage)) {
            return super.equals(obj);
        }
        LocalizedMessage localizedMessage = (LocalizedMessage) obj;
        return getLocale().equals(localizedMessage.getLocale()) && getMessage().equals(localizedMessage.getMessage()) && this.unknownFields.equals(localizedMessage.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getLocale().hashCode()) * 37) + 2) * 53) + getMessage().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3626newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3629toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LocalizedMessageOrBuilder {
        private Object locale_;
        private Object message_;

        private Builder() {
            this.locale_ = "";
            this.message_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.locale_ = "";
            this.message_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_LocalizedMessage_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_LocalizedMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(LocalizedMessage.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = LocalizedMessage.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3640clear() {
            super.clear();
            this.locale_ = "";
            this.message_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_LocalizedMessage_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LocalizedMessage m3653getDefaultInstanceForType() {
            return LocalizedMessage.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LocalizedMessage m3634build() throws UninitializedMessageException {
            LocalizedMessage localizedMessageM3636buildPartial = m3636buildPartial();
            if (localizedMessageM3636buildPartial.isInitialized()) {
                return localizedMessageM3636buildPartial;
            }
            throw newUninitializedMessageException(localizedMessageM3636buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LocalizedMessage m3636buildPartial() {
            LocalizedMessage localizedMessage = new LocalizedMessage(this);
            localizedMessage.locale_ = this.locale_;
            localizedMessage.message_ = this.message_;
            onBuilt();
            return localizedMessage;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3652clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3664setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3642clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3645clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3666setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3632addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3657mergeFrom(Message message) {
            if (message instanceof LocalizedMessage) {
                return mergeFrom((LocalizedMessage) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(LocalizedMessage localizedMessage) {
            if (localizedMessage == LocalizedMessage.getDefaultInstance()) {
                return this;
            }
            if (!localizedMessage.getLocale().isEmpty()) {
                this.locale_ = localizedMessage.locale_;
                onChanged();
            }
            if (!localizedMessage.getMessage().isEmpty()) {
                this.message_ = localizedMessage.message_;
                onChanged();
            }
            m3662mergeUnknownFields(localizedMessage.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.rpc.LocalizedMessage.Builder m3658mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.LocalizedMessage.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.rpc.LocalizedMessage r3 = (com.google.rpc.LocalizedMessage) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.rpc.LocalizedMessage r4 = (com.google.rpc.LocalizedMessage) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.LocalizedMessage.Builder.m3658mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.LocalizedMessage$Builder");
        }

        @Override // com.google.rpc.LocalizedMessageOrBuilder
        public String getLocale() {
            Object obj = this.locale_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.locale_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setLocale(String str) {
            str.getClass();
            this.locale_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.rpc.LocalizedMessageOrBuilder
        public ByteString getLocaleBytes() {
            Object obj = this.locale_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.locale_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setLocaleBytes(ByteString byteString) {
            byteString.getClass();
            LocalizedMessage.checkByteStringIsUtf8(byteString);
            this.locale_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearLocale() {
            this.locale_ = LocalizedMessage.getDefaultInstance().getLocale();
            onChanged();
            return this;
        }

        @Override // com.google.rpc.LocalizedMessageOrBuilder
        public String getMessage() {
            Object obj = this.message_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.message_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setMessage(String str) {
            str.getClass();
            this.message_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.rpc.LocalizedMessageOrBuilder
        public ByteString getMessageBytes() {
            Object obj = this.message_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.message_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setMessageBytes(ByteString byteString) {
            byteString.getClass();
            LocalizedMessage.checkByteStringIsUtf8(byteString);
            this.message_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearMessage() {
            this.message_ = LocalizedMessage.getDefaultInstance().getMessage();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3668setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3662mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
