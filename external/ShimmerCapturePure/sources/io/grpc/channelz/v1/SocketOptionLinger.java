package io.grpc.channelz.v1;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
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
public final class SocketOptionLinger extends GeneratedMessageV3 implements SocketOptionLingerOrBuilder {
    public static final int ACTIVE_FIELD_NUMBER = 1;
    public static final int DURATION_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final SocketOptionLinger DEFAULT_INSTANCE = new SocketOptionLinger();
    private static final Parser<SocketOptionLinger> PARSER = new AbstractParser<SocketOptionLinger>() { // from class: io.grpc.channelz.v1.SocketOptionLinger.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public SocketOptionLinger m8828parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SocketOptionLinger(codedInputStream, extensionRegistryLite);
        }
    };
    private boolean active_;
    private Duration duration_;
    private byte memoizedIsInitialized;

    private SocketOptionLinger(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SocketOptionLinger() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private SocketOptionLinger(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 8) {
                            this.active_ = codedInputStream.readBool();
                        } else if (tag == 18) {
                            Duration duration = this.duration_;
                            Duration.Builder builder = duration != null ? duration.toBuilder() : null;
                            Duration message = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.duration_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.duration_ = builder.buildPartial();
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

    public static SocketOptionLinger getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SocketOptionLinger> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ChannelzProto.internal_static_grpc_channelz_v1_SocketOptionLinger_descriptor;
    }

    public static SocketOptionLinger parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (SocketOptionLinger) PARSER.parseFrom(byteBuffer);
    }

    public static SocketOptionLinger parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketOptionLinger) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SocketOptionLinger parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SocketOptionLinger) PARSER.parseFrom(byteString);
    }

    public static SocketOptionLinger parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketOptionLinger) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SocketOptionLinger parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SocketOptionLinger) PARSER.parseFrom(bArr);
    }

    public static SocketOptionLinger parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SocketOptionLinger) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SocketOptionLinger parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SocketOptionLinger parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SocketOptionLinger parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SocketOptionLinger parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SocketOptionLinger parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SocketOptionLinger parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m8826toBuilder();
    }

    public static Builder newBuilder(SocketOptionLinger socketOptionLinger) {
        return DEFAULT_INSTANCE.m8826toBuilder().mergeFrom(socketOptionLinger);
    }

    @Override // io.grpc.channelz.v1.SocketOptionLingerOrBuilder
    public boolean getActive() {
        return this.active_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SocketOptionLinger m8821getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<SocketOptionLinger> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.channelz.v1.SocketOptionLingerOrBuilder
    public boolean hasDuration() {
        return this.duration_ != null;
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
        return new SocketOptionLinger();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ChannelzProto.internal_static_grpc_channelz_v1_SocketOptionLinger_fieldAccessorTable.ensureFieldAccessorsInitialized(SocketOptionLinger.class, Builder.class);
    }

    @Override // io.grpc.channelz.v1.SocketOptionLingerOrBuilder
    public Duration getDuration() {
        Duration duration = this.duration_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.channelz.v1.SocketOptionLingerOrBuilder
    public DurationOrBuilder getDurationOrBuilder() {
        return getDuration();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        boolean z = this.active_;
        if (z) {
            codedOutputStream.writeBool(1, z);
        }
        if (this.duration_ != null) {
            codedOutputStream.writeMessage(2, getDuration());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        boolean z = this.active_;
        int iComputeBoolSize = z ? CodedOutputStream.computeBoolSize(1, z) : 0;
        if (this.duration_ != null) {
            iComputeBoolSize += CodedOutputStream.computeMessageSize(2, getDuration());
        }
        int serializedSize = iComputeBoolSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SocketOptionLinger)) {
            return super.equals(obj);
        }
        SocketOptionLinger socketOptionLinger = (SocketOptionLinger) obj;
        if (getActive() == socketOptionLinger.getActive() && hasDuration() == socketOptionLinger.hasDuration()) {
            return (!hasDuration() || getDuration().equals(socketOptionLinger.getDuration())) && this.unknownFields.equals(socketOptionLinger.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getActive());
        if (hasDuration()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getDuration().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8823newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8826toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SocketOptionLingerOrBuilder {
        private boolean active_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> durationBuilder_;
        private Duration duration_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ChannelzProto.internal_static_grpc_channelz_v1_SocketOptionLinger_descriptor;
        }

        @Override // io.grpc.channelz.v1.SocketOptionLingerOrBuilder
        public boolean getActive() {
            return this.active_;
        }

        public Builder setActive(boolean z) {
            this.active_ = z;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionLingerOrBuilder
        public boolean hasDuration() {
            return (this.durationBuilder_ == null && this.duration_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ChannelzProto.internal_static_grpc_channelz_v1_SocketOptionLinger_fieldAccessorTable.ensureFieldAccessorsInitialized(SocketOptionLinger.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SocketOptionLinger.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8837clear() {
            super.clear();
            this.active_ = false;
            if (this.durationBuilder_ == null) {
                this.duration_ = null;
            } else {
                this.duration_ = null;
                this.durationBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ChannelzProto.internal_static_grpc_channelz_v1_SocketOptionLinger_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketOptionLinger m8850getDefaultInstanceForType() {
            return SocketOptionLinger.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketOptionLinger m8831build() throws UninitializedMessageException {
            SocketOptionLinger socketOptionLingerM8833buildPartial = m8833buildPartial();
            if (socketOptionLingerM8833buildPartial.isInitialized()) {
                return socketOptionLingerM8833buildPartial;
            }
            throw newUninitializedMessageException(socketOptionLingerM8833buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SocketOptionLinger m8833buildPartial() {
            SocketOptionLinger socketOptionLinger = new SocketOptionLinger(this);
            socketOptionLinger.active_ = this.active_;
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.durationBuilder_;
            if (singleFieldBuilderV3 == null) {
                socketOptionLinger.duration_ = this.duration_;
            } else {
                socketOptionLinger.duration_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return socketOptionLinger;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8849clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8861setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8839clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8842clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8863setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8829addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8854mergeFrom(Message message) {
            if (message instanceof SocketOptionLinger) {
                return mergeFrom((SocketOptionLinger) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SocketOptionLinger socketOptionLinger) {
            if (socketOptionLinger == SocketOptionLinger.getDefaultInstance()) {
                return this;
            }
            if (socketOptionLinger.getActive()) {
                setActive(socketOptionLinger.getActive());
            }
            if (socketOptionLinger.hasDuration()) {
                mergeDuration(socketOptionLinger.getDuration());
            }
            m8859mergeUnknownFields(socketOptionLinger.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.channelz.v1.SocketOptionLinger.Builder m8855mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.channelz.v1.SocketOptionLinger.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.channelz.v1.SocketOptionLinger r3 = (io.grpc.channelz.v1.SocketOptionLinger) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.channelz.v1.SocketOptionLinger r4 = (io.grpc.channelz.v1.SocketOptionLinger) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.channelz.v1.SocketOptionLinger.Builder.m8855mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.channelz.v1.SocketOptionLinger$Builder");
        }

        public Builder clearActive() {
            this.active_ = false;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.SocketOptionLingerOrBuilder
        public Duration getDuration() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.durationBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.duration_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setDuration(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.durationBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.duration_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setDuration(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.durationBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.duration_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeDuration(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.durationBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.duration_;
                if (duration2 != null) {
                    this.duration_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.duration_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearDuration() {
            if (this.durationBuilder_ == null) {
                this.duration_ = null;
                onChanged();
            } else {
                this.duration_ = null;
                this.durationBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getDurationBuilder() {
            onChanged();
            return getDurationFieldBuilder().getBuilder();
        }

        @Override // io.grpc.channelz.v1.SocketOptionLingerOrBuilder
        public DurationOrBuilder getDurationOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.durationBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.duration_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getDurationFieldBuilder() {
            if (this.durationBuilder_ == null) {
                this.durationBuilder_ = new SingleFieldBuilderV3<>(getDuration(), getParentForChildren(), isClean());
                this.duration_ = null;
            }
            return this.durationBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8865setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8859mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
