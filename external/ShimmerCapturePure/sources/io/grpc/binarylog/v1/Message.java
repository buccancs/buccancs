package io.grpc.binarylog.v1;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class Message extends GeneratedMessageV3 implements MessageOrBuilder {
    public static final int DATA_FIELD_NUMBER = 2;
    public static final int LENGTH_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final Message DEFAULT_INSTANCE = new Message();
    private static final Parser<Message> PARSER = new AbstractParser<Message>() { // from class: io.grpc.binarylog.v1.Message.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Message m7078parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Message(codedInputStream, extensionRegistryLite);
        }
    };
    private ByteString data_;
    private int length_;
    private byte memoizedIsInitialized;

    private Message(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Message() {
        this.memoizedIsInitialized = (byte) -1;
        this.data_ = ByteString.EMPTY;
    }

    private Message(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.length_ = codedInputStream.readUInt32();
                            } else if (tag == 18) {
                                this.data_ = codedInputStream.readBytes();
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

    public static Message getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Message> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BinaryLogProto.internal_static_grpc_binarylog_v1_Message_descriptor;
    }

    public static Message parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Message) PARSER.parseFrom(byteBuffer);
    }

    public static Message parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Message) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Message parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Message) PARSER.parseFrom(byteString);
    }

    public static Message parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Message) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Message parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Message) PARSER.parseFrom(bArr);
    }

    public static Message parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Message) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Message parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Message parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Message parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Message parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Message parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Message parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m7076toBuilder();
    }

    public static Builder newBuilder(Message message) {
        return DEFAULT_INSTANCE.m7076toBuilder().mergeFrom(message);
    }

    @Override // io.grpc.binarylog.v1.MessageOrBuilder
    public ByteString getData() {
        return this.data_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Message m7071getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.binarylog.v1.MessageOrBuilder
    public int getLength() {
        return this.length_;
    }

    public Parser<Message> getParserForType() {
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
        return new Message();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BinaryLogProto.internal_static_grpc_binarylog_v1_Message_fieldAccessorTable.ensureFieldAccessorsInitialized(Message.class, Builder.class);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i = this.length_;
        if (i != 0) {
            codedOutputStream.writeUInt32(1, i);
        }
        if (!this.data_.isEmpty()) {
            codedOutputStream.writeBytes(2, this.data_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = this.length_;
        int iComputeUInt32Size = i2 != 0 ? CodedOutputStream.computeUInt32Size(1, i2) : 0;
        if (!this.data_.isEmpty()) {
            iComputeUInt32Size += CodedOutputStream.computeBytesSize(2, this.data_);
        }
        int serializedSize = iComputeUInt32Size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Message)) {
            return super.equals(obj);
        }
        Message message = (Message) obj;
        return getLength() == message.getLength() && getData().equals(message.getData()) && this.unknownFields.equals(message.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getLength()) * 37) + 2) * 53) + getData().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m7073newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m7076toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MessageOrBuilder {
        private ByteString data_;
        private int length_;

        private Builder() {
            this.data_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.data_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_Message_descriptor;
        }

        @Override // io.grpc.binarylog.v1.MessageOrBuilder
        public ByteString getData() {
            return this.data_;
        }

        public Builder setData(ByteString byteString) {
            byteString.getClass();
            this.data_ = byteString;
            onChanged();
            return this;
        }

        @Override // io.grpc.binarylog.v1.MessageOrBuilder
        public int getLength() {
            return this.length_;
        }

        public Builder setLength(int i) {
            this.length_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_Message_fieldAccessorTable.ensureFieldAccessorsInitialized(Message.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Message.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7087clear() {
            super.clear();
            this.length_ = 0;
            this.data_ = ByteString.EMPTY;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BinaryLogProto.internal_static_grpc_binarylog_v1_Message_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Message m7100getDefaultInstanceForType() {
            return Message.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Message m7081build() throws UninitializedMessageException {
            Message messageM7083buildPartial = m7083buildPartial();
            if (messageM7083buildPartial.isInitialized()) {
                return messageM7083buildPartial;
            }
            throw newUninitializedMessageException(messageM7083buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Message m7083buildPartial() {
            Message message = new Message(this);
            message.length_ = this.length_;
            message.data_ = this.data_;
            onBuilt();
            return message;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7099clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7111setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7089clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7092clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7113setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7079addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7104mergeFrom(com.google.protobuf.Message message) {
            if (message instanceof Message) {
                return mergeFrom((Message) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Message message) {
            if (message == Message.getDefaultInstance()) {
                return this;
            }
            if (message.getLength() != 0) {
                setLength(message.getLength());
            }
            if (message.getData() != ByteString.EMPTY) {
                setData(message.getData());
            }
            m7109mergeUnknownFields(message.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.binarylog.v1.Message.Builder m7105mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.binarylog.v1.Message.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.binarylog.v1.Message r3 = (io.grpc.binarylog.v1.Message) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.binarylog.v1.Message r4 = (io.grpc.binarylog.v1.Message) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.binarylog.v1.Message.Builder.m7105mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.binarylog.v1.Message$Builder");
        }

        public Builder clearLength() {
            this.length_ = 0;
            onChanged();
            return this;
        }

        public Builder clearData() {
            this.data_ = Message.getDefaultInstance().getData();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m7115setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m7109mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
