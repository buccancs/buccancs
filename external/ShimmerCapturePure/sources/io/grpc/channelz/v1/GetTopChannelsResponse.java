package io.grpc.channelz.v1;

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
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.channelz.v1.Channel;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class GetTopChannelsResponse extends GeneratedMessageV3 implements GetTopChannelsResponseOrBuilder {
    public static final int CHANNEL_FIELD_NUMBER = 1;
    public static final int END_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final GetTopChannelsResponse DEFAULT_INSTANCE = new GetTopChannelsResponse();
    private static final Parser<GetTopChannelsResponse> PARSER = new AbstractParser<GetTopChannelsResponse>() { // from class: io.grpc.channelz.v1.GetTopChannelsResponse.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public GetTopChannelsResponse m8368parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new GetTopChannelsResponse(codedInputStream, extensionRegistryLite);
        }
    };
    private List<Channel> channel_;
    private boolean end_;
    private byte memoizedIsInitialized;

    private GetTopChannelsResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private GetTopChannelsResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.channel_ = Collections.emptyList();
    }

    private GetTopChannelsResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            if (!(z2 & true)) {
                                this.channel_ = new ArrayList();
                                z2 |= true;
                            }
                            this.channel_.add(codedInputStream.readMessage(Channel.parser(), extensionRegistryLite));
                        } else if (tag == 16) {
                            this.end_ = codedInputStream.readBool();
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
                if (z2 & true) {
                    this.channel_ = Collections.unmodifiableList(this.channel_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static GetTopChannelsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GetTopChannelsResponse> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ChannelzProto.internal_static_grpc_channelz_v1_GetTopChannelsResponse_descriptor;
    }

    public static GetTopChannelsResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (GetTopChannelsResponse) PARSER.parseFrom(byteBuffer);
    }

    public static GetTopChannelsResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetTopChannelsResponse) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static GetTopChannelsResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (GetTopChannelsResponse) PARSER.parseFrom(byteString);
    }

    public static GetTopChannelsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetTopChannelsResponse) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static GetTopChannelsResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (GetTopChannelsResponse) PARSER.parseFrom(bArr);
    }

    public static GetTopChannelsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetTopChannelsResponse) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static GetTopChannelsResponse parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static GetTopChannelsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static GetTopChannelsResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static GetTopChannelsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static GetTopChannelsResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static GetTopChannelsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m8366toBuilder();
    }

    public static Builder newBuilder(GetTopChannelsResponse getTopChannelsResponse) {
        return DEFAULT_INSTANCE.m8366toBuilder().mergeFrom(getTopChannelsResponse);
    }

    @Override // io.grpc.channelz.v1.GetTopChannelsResponseOrBuilder
    public List<Channel> getChannelList() {
        return this.channel_;
    }

    @Override // io.grpc.channelz.v1.GetTopChannelsResponseOrBuilder
    public List<? extends ChannelOrBuilder> getChannelOrBuilderList() {
        return this.channel_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public GetTopChannelsResponse m8361getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.channelz.v1.GetTopChannelsResponseOrBuilder
    public boolean getEnd() {
        return this.end_;
    }

    public Parser<GetTopChannelsResponse> getParserForType() {
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
        return new GetTopChannelsResponse();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ChannelzProto.internal_static_grpc_channelz_v1_GetTopChannelsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(GetTopChannelsResponse.class, Builder.class);
    }

    @Override // io.grpc.channelz.v1.GetTopChannelsResponseOrBuilder
    public int getChannelCount() {
        return this.channel_.size();
    }

    @Override // io.grpc.channelz.v1.GetTopChannelsResponseOrBuilder
    public Channel getChannel(int i) {
        return this.channel_.get(i);
    }

    @Override // io.grpc.channelz.v1.GetTopChannelsResponseOrBuilder
    public ChannelOrBuilder getChannelOrBuilder(int i) {
        return this.channel_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.channel_.size(); i++) {
            codedOutputStream.writeMessage(1, this.channel_.get(i));
        }
        boolean z = this.end_;
        if (z) {
            codedOutputStream.writeBool(2, z);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeBoolSize = 0;
        for (int i2 = 0; i2 < this.channel_.size(); i2++) {
            iComputeBoolSize += CodedOutputStream.computeMessageSize(1, this.channel_.get(i2));
        }
        boolean z = this.end_;
        if (z) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(2, z);
        }
        int serializedSize = iComputeBoolSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetTopChannelsResponse)) {
            return super.equals(obj);
        }
        GetTopChannelsResponse getTopChannelsResponse = (GetTopChannelsResponse) obj;
        return getChannelList().equals(getTopChannelsResponse.getChannelList()) && getEnd() == getTopChannelsResponse.getEnd() && this.unknownFields.equals(getTopChannelsResponse.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getChannelCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getChannelList().hashCode();
        }
        int iHashBoolean = (((((iHashCode * 37) + 2) * 53) + Internal.hashBoolean(getEnd())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashBoolean;
        return iHashBoolean;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8363newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m8366toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GetTopChannelsResponseOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> channelBuilder_;
        private List<Channel> channel_;
        private boolean end_;

        private Builder() {
            this.channel_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.channel_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ChannelzProto.internal_static_grpc_channelz_v1_GetTopChannelsResponse_descriptor;
        }

        @Override // io.grpc.channelz.v1.GetTopChannelsResponseOrBuilder
        public boolean getEnd() {
            return this.end_;
        }

        public Builder setEnd(boolean z) {
            this.end_ = z;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ChannelzProto.internal_static_grpc_channelz_v1_GetTopChannelsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(GetTopChannelsResponse.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (GetTopChannelsResponse.alwaysUseFieldBuilders) {
                getChannelFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8377clear() {
            super.clear();
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.channel_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.end_ = false;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ChannelzProto.internal_static_grpc_channelz_v1_GetTopChannelsResponse_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GetTopChannelsResponse m8390getDefaultInstanceForType() {
            return GetTopChannelsResponse.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GetTopChannelsResponse m8371build() throws UninitializedMessageException {
            GetTopChannelsResponse getTopChannelsResponseM8373buildPartial = m8373buildPartial();
            if (getTopChannelsResponseM8373buildPartial.isInitialized()) {
                return getTopChannelsResponseM8373buildPartial;
            }
            throw newUninitializedMessageException(getTopChannelsResponseM8373buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GetTopChannelsResponse m8373buildPartial() {
            GetTopChannelsResponse getTopChannelsResponse = new GetTopChannelsResponse(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.channel_ = Collections.unmodifiableList(this.channel_);
                    this.bitField0_ &= -2;
                }
                getTopChannelsResponse.channel_ = this.channel_;
            } else {
                getTopChannelsResponse.channel_ = repeatedFieldBuilderV3.build();
            }
            getTopChannelsResponse.end_ = this.end_;
            onBuilt();
            return getTopChannelsResponse;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8389clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8401setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8379clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8382clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8403setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8369addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m8394mergeFrom(Message message) {
            if (message instanceof GetTopChannelsResponse) {
                return mergeFrom((GetTopChannelsResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(GetTopChannelsResponse getTopChannelsResponse) {
            if (getTopChannelsResponse == GetTopChannelsResponse.getDefaultInstance()) {
                return this;
            }
            if (this.channelBuilder_ == null) {
                if (!getTopChannelsResponse.channel_.isEmpty()) {
                    if (this.channel_.isEmpty()) {
                        this.channel_ = getTopChannelsResponse.channel_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureChannelIsMutable();
                        this.channel_.addAll(getTopChannelsResponse.channel_);
                    }
                    onChanged();
                }
            } else if (!getTopChannelsResponse.channel_.isEmpty()) {
                if (!this.channelBuilder_.isEmpty()) {
                    this.channelBuilder_.addAllMessages(getTopChannelsResponse.channel_);
                } else {
                    this.channelBuilder_.dispose();
                    this.channelBuilder_ = null;
                    this.channel_ = getTopChannelsResponse.channel_;
                    this.bitField0_ &= -2;
                    this.channelBuilder_ = GetTopChannelsResponse.alwaysUseFieldBuilders ? getChannelFieldBuilder() : null;
                }
            }
            if (getTopChannelsResponse.getEnd()) {
                setEnd(getTopChannelsResponse.getEnd());
            }
            m8399mergeUnknownFields(getTopChannelsResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.channelz.v1.GetTopChannelsResponse.Builder m8395mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.channelz.v1.GetTopChannelsResponse.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.channelz.v1.GetTopChannelsResponse r3 = (io.grpc.channelz.v1.GetTopChannelsResponse) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.channelz.v1.GetTopChannelsResponse r4 = (io.grpc.channelz.v1.GetTopChannelsResponse) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.channelz.v1.GetTopChannelsResponse.Builder.m8395mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.channelz.v1.GetTopChannelsResponse$Builder");
        }

        private void ensureChannelIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.channel_ = new ArrayList(this.channel_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.channelz.v1.GetTopChannelsResponseOrBuilder
        public List<Channel> getChannelList() {
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.channel_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.channelz.v1.GetTopChannelsResponseOrBuilder
        public int getChannelCount() {
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.channel_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.channelz.v1.GetTopChannelsResponseOrBuilder
        public Channel getChannel(int i) {
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.channel_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setChannel(int i, Channel channel) {
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                channel.getClass();
                ensureChannelIsMutable();
                this.channel_.set(i, channel);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, channel);
            }
            return this;
        }

        public Builder setChannel(int i, Channel.Builder builder) {
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureChannelIsMutable();
                this.channel_.set(i, builder.m7495build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m7495build());
            }
            return this;
        }

        public Builder addChannel(Channel channel) {
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                channel.getClass();
                ensureChannelIsMutable();
                this.channel_.add(channel);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(channel);
            }
            return this;
        }

        public Builder addChannel(int i, Channel channel) {
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                channel.getClass();
                ensureChannelIsMutable();
                this.channel_.add(i, channel);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, channel);
            }
            return this;
        }

        public Builder addChannel(Channel.Builder builder) {
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureChannelIsMutable();
                this.channel_.add(builder.m7495build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m7495build());
            }
            return this;
        }

        public Builder addChannel(int i, Channel.Builder builder) {
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureChannelIsMutable();
                this.channel_.add(i, builder.m7495build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m7495build());
            }
            return this;
        }

        public Builder addAllChannel(Iterable<? extends Channel> iterable) {
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureChannelIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.channel_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearChannel() {
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.channel_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeChannel(int i) {
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureChannelIsMutable();
                this.channel_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Channel.Builder getChannelBuilder(int i) {
            return getChannelFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.channelz.v1.GetTopChannelsResponseOrBuilder
        public ChannelOrBuilder getChannelOrBuilder(int i) {
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.channel_.get(i);
            }
            return (ChannelOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.channelz.v1.GetTopChannelsResponseOrBuilder
        public List<? extends ChannelOrBuilder> getChannelOrBuilderList() {
            RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.channel_);
        }

        public Channel.Builder addChannelBuilder() {
            return getChannelFieldBuilder().addBuilder(Channel.getDefaultInstance());
        }

        public Channel.Builder addChannelBuilder(int i) {
            return getChannelFieldBuilder().addBuilder(i, Channel.getDefaultInstance());
        }

        public List<Channel.Builder> getChannelBuilderList() {
            return getChannelFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> getChannelFieldBuilder() {
            if (this.channelBuilder_ == null) {
                this.channelBuilder_ = new RepeatedFieldBuilderV3<>(this.channel_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.channel_ = null;
            }
            return this.channelBuilder_;
        }

        public Builder clearEnd() {
            this.end_ = false;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8405setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m8399mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
