package io.grpc.channelz.v1;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class SocketOption extends GeneratedMessageV3 implements SocketOptionOrBuilder {
    public static final int ADDITIONAL_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final SocketOption DEFAULT_INSTANCE = new SocketOption();
    private static final Parser<SocketOption> PARSER = new AbstractParser<SocketOption>() { // from class: io.grpc.channelz.v1.SocketOption.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public SocketOption m8782parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SocketOption(codedInputStream, extensionRegistryLite);
        }
    };
    private Any additional_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private volatile Object value_;

    private SocketOption(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SocketOption() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.value_ = "";
    }

    private SocketOption(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.value_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 26) {
                            Any any = this.additional_;
                            Any.Builder builder = any != null ? any.toBuilder() : null;
                            Any message = codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                            this.additional_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.additional_ = builder.buildPartial();
                            }
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

    public static SocketOption getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SocketOption> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ChannelzProto.internal_static_grpc_channelz_v1_SocketOption_descriptor;
    }

    public static SocketOption parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (SocketOption) PARSER.parseFrom(byteBuffer);
    }

    public static SocketOption parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketOption) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SocketOption parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SocketOption) PARSER.parseFrom(byteString);
    }

    public static SocketOption parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketOption) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SocketOption parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SocketOption) PARSER.parseFrom(bArr);
    }

    public static SocketOption parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketOption) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SocketOption parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SocketOption parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SocketOption parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SocketOption parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SocketOption parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SocketOption parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m8780toBuilder();
    }

    public static Builder newBuilder(SocketOption socketOption) {
        return DEFAULT_INSTANCE.m8780toBuilder().mergeFrom(socketOption);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SocketOption m8775getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<SocketOption> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.channelz.v1.SocketOptionOrBuilder
    public boolean hasAdditional() {
        return this.additional_ != null;
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
        return new SocketOption();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ChannelzProto.internal_static_grpc_channelz_v1_SocketOption_fieldAccessorTable.ensureFieldAccessorsInitialized(SocketOption.class, Builder.class);
    }

    @Override // io.grpc.channelz.v1.SocketOptionOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.channelz.v1.SocketOptionOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.channelz.v1.SocketOptionOrBuilder
    public String getValue() {
        Object obj = this.value_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.value_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.channelz.v1.SocketOptionOrBuilder
    public ByteString getValueBytes() {
        Object obj = this.value_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.value_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.channelz.v1.SocketOptionOrBuilder
    public Any getAdditional() {
        Any any = this.additional_;
        return any == null ? Any.getDefaultInstance() : any;
    }

    @Override // io.grpc.channelz.v1.SocketOptionOrBuilder
    public AnyOrBuilder getAdditionalOrBuilder() {
        return getAdditional();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (!getValueBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.value_);
        }
        if (this.additional_ != null) {
            codedOutputStream.writeMessage(3, getAdditional());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (!getValueBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.value_);
        }
        if (this.additional_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, getAdditional());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SocketOption)) {
            return super.equals(obj);
        }
        SocketOption socketOption = (SocketOption) obj;
        if (getName().equals(socketOption.getName()) && getValue().equals(socketOption.getValue()) && hasAdditional() == socketOption.hasAdditional()) {
            return (!hasAdditional() || getAdditional().equals(socketOption.getAdditional())) && this.unknownFields.equals(socketOption.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getValue().hashCode();
        if (hasAdditional()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getAdditional().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8777newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8780toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SocketOptionOrBuilder {
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> additionalBuilder_;
        private Any additional_;
        private Object name_;
        private Object value_;

        private Builder() {
            this.name_ = "";
            this.value_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.value_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ChannelzProto.internal_static_grpc_channelz_v1_SocketOption_descriptor;
        }

        @Override // io.grpc.channelz.v1.SocketOptionOrBuilder
        public boolean hasAdditional() {
            return (this.additionalBuilder_ == null && this.additional_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ChannelzProto.internal_static_grpc_channelz_v1_SocketOption_fieldAccessorTable.ensureFieldAccessorsInitialized(SocketOption.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SocketOption.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8791clear() {
            super.clear();
            this.name_ = "";
            this.value_ = "";
            if (this.additionalBuilder_ == null) {
                this.additional_ = null;
            } else {
                this.additional_ = null;
                this.additionalBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ChannelzProto.internal_static_grpc_channelz_v1_SocketOption_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketOption m8804getDefaultInstanceForType() {
            return SocketOption.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketOption m8785build() throws UninitializedMessageException {
            SocketOption socketOptionM8787buildPartial = m8787buildPartial();
            if (socketOptionM8787buildPartial.isInitialized()) {
                return socketOptionM8787buildPartial;
            }
            throw newUninitializedMessageException(socketOptionM8787buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketOption m8787buildPartial() {
            SocketOption socketOption = new SocketOption(this);
            socketOption.name_ = this.name_;
            socketOption.value_ = this.value_;
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.additionalBuilder_;
            if (singleFieldBuilderV3 == null) {
                socketOption.additional_ = this.additional_;
            } else {
                socketOption.additional_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return socketOption;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8803clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8815setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8793clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8796clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8817setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8783addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8808mergeFrom(Message message) {
            if (message instanceof SocketOption) {
                return mergeFrom((SocketOption) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SocketOption socketOption) {
            if (socketOption == SocketOption.getDefaultInstance()) {
                return this;
            }
            if (!socketOption.getName().isEmpty()) {
                this.name_ = socketOption.name_;
                onChanged();
            }
            if (!socketOption.getValue().isEmpty()) {
                this.value_ = socketOption.value_;
                onChanged();
            }
            if (socketOption.hasAdditional()) {
                mergeAdditional(socketOption.getAdditional());
            }
            m8813mergeUnknownFields(socketOption.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.channelz.v1.SocketOption.Builder m8809mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.channelz.v1.SocketOption.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.channelz.v1.SocketOption r3 = (io.grpc.channelz.v1.SocketOption) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.channelz.v1.SocketOption r4 = (io.grpc.channelz.v1.SocketOption) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.channelz.v1.SocketOption.Builder.m8809mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.channelz.v1.SocketOption$Builder");
        }

        @Override // io.grpc.channelz.v1.SocketOptionOrBuilder
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

        @Override // io.grpc.channelz.v1.SocketOptionOrBuilder
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
            SocketOption.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = SocketOption.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionOrBuilder
        public String getValue() {
            Object obj = this.value_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.value_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setValue(String str) {
            str.getClass();
            this.value_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionOrBuilder
        public ByteString getValueBytes() {
            Object obj = this.value_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.value_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setValueBytes(ByteString byteString) {
            byteString.getClass();
            SocketOption.checkByteStringIsUtf8(byteString);
            this.value_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearValue() {
            this.value_ = SocketOption.getDefaultInstance().getValue();
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionOrBuilder
        public Any getAdditional() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.additionalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Any any = this.additional_;
            return any == null ? Any.getDefaultInstance() : any;
        }

        public Builder setAdditional(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.additionalBuilder_;
            if (singleFieldBuilderV3 == null) {
                any.getClass();
                this.additional_ = any;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(any);
            }
            return this;
        }

        public Builder setAdditional(Any.Builder builder) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.additionalBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.additional_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeAdditional(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.additionalBuilder_;
            if (singleFieldBuilderV3 == null) {
                Any any2 = this.additional_;
                if (any2 != null) {
                    this.additional_ = Any.newBuilder(any2).mergeFrom(any).buildPartial();
                } else {
                    this.additional_ = any;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(any);
            }
            return this;
        }

        public Builder clearAdditional() {
            if (this.additionalBuilder_ == null) {
                this.additional_ = null;
                onChanged();
            } else {
                this.additional_ = null;
                this.additionalBuilder_ = null;
            }
            return this;
        }

        public Any.Builder getAdditionalBuilder() {
            onChanged();
            return getAdditionalFieldBuilder().getBuilder();
        }

        @Override // io.grpc.channelz.v1.SocketOptionOrBuilder
        public AnyOrBuilder getAdditionalOrBuilder() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.additionalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Any any = this.additional_;
            return any == null ? Any.getDefaultInstance() : any;
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getAdditionalFieldBuilder() {
            if (this.additionalBuilder_ == null) {
                this.additionalBuilder_ = new SingleFieldBuilderV3<>(getAdditional(), getParentForChildren(), isClean());
                this.additional_ = null;
            }
            return this.additionalBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8819setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8813mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
