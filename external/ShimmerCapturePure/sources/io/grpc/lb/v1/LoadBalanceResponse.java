package io.grpc.lb.v1;

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
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.lb.v1.FallbackResponse;
import io.grpc.lb.v1.InitialLoadBalanceResponse;
import io.grpc.lb.v1.ServerList;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class LoadBalanceResponse extends GeneratedMessageV3 implements LoadBalanceResponseOrBuilder {
    public static final int FALLBACK_RESPONSE_FIELD_NUMBER = 3;
    public static final int INITIAL_RESPONSE_FIELD_NUMBER = 1;
    public static final int SERVER_LIST_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final LoadBalanceResponse DEFAULT_INSTANCE = new LoadBalanceResponse();
    private static final Parser<LoadBalanceResponse> PARSER = new AbstractParser<LoadBalanceResponse>() { // from class: io.grpc.lb.v1.LoadBalanceResponse.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public LoadBalanceResponse m9473parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new LoadBalanceResponse(codedInputStream, extensionRegistryLite);
        }
    };
    private int loadBalanceResponseTypeCase_;
    private Object loadBalanceResponseType_;
    private byte memoizedIsInitialized;

    private LoadBalanceResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.loadBalanceResponseTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private LoadBalanceResponse() {
        this.loadBalanceResponseTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private LoadBalanceResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                InitialLoadBalanceResponse.Builder builderM9379toBuilder = this.loadBalanceResponseTypeCase_ == 1 ? ((InitialLoadBalanceResponse) this.loadBalanceResponseType_).m9379toBuilder() : null;
                                MessageLite message = codedInputStream.readMessage(InitialLoadBalanceResponse.parser(), extensionRegistryLite);
                                this.loadBalanceResponseType_ = message;
                                if (builderM9379toBuilder != null) {
                                    builderM9379toBuilder.mergeFrom((InitialLoadBalanceResponse) message);
                                    this.loadBalanceResponseType_ = builderM9379toBuilder.m9386buildPartial();
                                }
                                this.loadBalanceResponseTypeCase_ = 1;
                            } else if (tag == 18) {
                                ServerList.Builder builderM9563toBuilder = this.loadBalanceResponseTypeCase_ == 2 ? ((ServerList) this.loadBalanceResponseType_).m9563toBuilder() : null;
                                MessageLite message2 = codedInputStream.readMessage(ServerList.parser(), extensionRegistryLite);
                                this.loadBalanceResponseType_ = message2;
                                if (builderM9563toBuilder != null) {
                                    builderM9563toBuilder.mergeFrom((ServerList) message2);
                                    this.loadBalanceResponseType_ = builderM9563toBuilder.m9570buildPartial();
                                }
                                this.loadBalanceResponseTypeCase_ = 2;
                            } else if (tag == 26) {
                                FallbackResponse.Builder builderM9287toBuilder = this.loadBalanceResponseTypeCase_ == 3 ? ((FallbackResponse) this.loadBalanceResponseType_).m9287toBuilder() : null;
                                MessageLite message3 = codedInputStream.readMessage(FallbackResponse.parser(), extensionRegistryLite);
                                this.loadBalanceResponseType_ = message3;
                                if (builderM9287toBuilder != null) {
                                    builderM9287toBuilder.mergeFrom((FallbackResponse) message3);
                                    this.loadBalanceResponseType_ = builderM9287toBuilder.m9294buildPartial();
                                }
                                this.loadBalanceResponseTypeCase_ = 3;
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

    public static LoadBalanceResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LoadBalanceResponse> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceResponse_descriptor;
    }

    public static LoadBalanceResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (LoadBalanceResponse) PARSER.parseFrom(byteBuffer);
    }

    public static LoadBalanceResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LoadBalanceResponse) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static LoadBalanceResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (LoadBalanceResponse) PARSER.parseFrom(byteString);
    }

    public static LoadBalanceResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LoadBalanceResponse) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static LoadBalanceResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (LoadBalanceResponse) PARSER.parseFrom(bArr);
    }

    public static LoadBalanceResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LoadBalanceResponse) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static LoadBalanceResponse parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static LoadBalanceResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LoadBalanceResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static LoadBalanceResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LoadBalanceResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static LoadBalanceResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m9471toBuilder();
    }

    public static Builder newBuilder(LoadBalanceResponse loadBalanceResponse) {
        return DEFAULT_INSTANCE.m9471toBuilder().mergeFrom(loadBalanceResponse);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public LoadBalanceResponse m9466getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<LoadBalanceResponse> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
    public boolean hasFallbackResponse() {
        return this.loadBalanceResponseTypeCase_ == 3;
    }

    @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
    public boolean hasInitialResponse() {
        return this.loadBalanceResponseTypeCase_ == 1;
    }

    @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
    public boolean hasServerList() {
        return this.loadBalanceResponseTypeCase_ == 2;
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
        return new LoadBalanceResponse();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(LoadBalanceResponse.class, Builder.class);
    }

    @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
    public LoadBalanceResponseTypeCase getLoadBalanceResponseTypeCase() {
        return LoadBalanceResponseTypeCase.forNumber(this.loadBalanceResponseTypeCase_);
    }

    @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
    public InitialLoadBalanceResponse getInitialResponse() {
        if (this.loadBalanceResponseTypeCase_ == 1) {
            return (InitialLoadBalanceResponse) this.loadBalanceResponseType_;
        }
        return InitialLoadBalanceResponse.getDefaultInstance();
    }

    @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
    public InitialLoadBalanceResponseOrBuilder getInitialResponseOrBuilder() {
        if (this.loadBalanceResponseTypeCase_ == 1) {
            return (InitialLoadBalanceResponse) this.loadBalanceResponseType_;
        }
        return InitialLoadBalanceResponse.getDefaultInstance();
    }

    @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
    public ServerList getServerList() {
        if (this.loadBalanceResponseTypeCase_ == 2) {
            return (ServerList) this.loadBalanceResponseType_;
        }
        return ServerList.getDefaultInstance();
    }

    @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
    public ServerListOrBuilder getServerListOrBuilder() {
        if (this.loadBalanceResponseTypeCase_ == 2) {
            return (ServerList) this.loadBalanceResponseType_;
        }
        return ServerList.getDefaultInstance();
    }

    @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
    public FallbackResponse getFallbackResponse() {
        if (this.loadBalanceResponseTypeCase_ == 3) {
            return (FallbackResponse) this.loadBalanceResponseType_;
        }
        return FallbackResponse.getDefaultInstance();
    }

    @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
    public FallbackResponseOrBuilder getFallbackResponseOrBuilder() {
        if (this.loadBalanceResponseTypeCase_ == 3) {
            return (FallbackResponse) this.loadBalanceResponseType_;
        }
        return FallbackResponse.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.loadBalanceResponseTypeCase_ == 1) {
            codedOutputStream.writeMessage(1, (InitialLoadBalanceResponse) this.loadBalanceResponseType_);
        }
        if (this.loadBalanceResponseTypeCase_ == 2) {
            codedOutputStream.writeMessage(2, (ServerList) this.loadBalanceResponseType_);
        }
        if (this.loadBalanceResponseTypeCase_ == 3) {
            codedOutputStream.writeMessage(3, (FallbackResponse) this.loadBalanceResponseType_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.loadBalanceResponseTypeCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (InitialLoadBalanceResponse) this.loadBalanceResponseType_) : 0;
        if (this.loadBalanceResponseTypeCase_ == 2) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, (ServerList) this.loadBalanceResponseType_);
        }
        if (this.loadBalanceResponseTypeCase_ == 3) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, (FallbackResponse) this.loadBalanceResponseType_);
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LoadBalanceResponse)) {
            return super.equals(obj);
        }
        LoadBalanceResponse loadBalanceResponse = (LoadBalanceResponse) obj;
        if (!getLoadBalanceResponseTypeCase().equals(loadBalanceResponse.getLoadBalanceResponseTypeCase())) {
            return false;
        }
        int i = this.loadBalanceResponseTypeCase_;
        if (i != 1) {
            if (i == 2) {
                if (!getServerList().equals(loadBalanceResponse.getServerList())) {
                    return false;
                }
            } else if (i == 3 && !getFallbackResponse().equals(loadBalanceResponse.getFallbackResponse())) {
                return false;
            }
        } else if (!getInitialResponse().equals(loadBalanceResponse.getInitialResponse())) {
            return false;
        }
        return this.unknownFields.equals(loadBalanceResponse.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        int i2 = this.loadBalanceResponseTypeCase_;
        if (i2 == 1) {
            i = ((iHashCode2 * 37) + 1) * 53;
            iHashCode = getInitialResponse().hashCode();
        } else if (i2 == 2) {
            i = ((iHashCode2 * 37) + 2) * 53;
            iHashCode = getServerList().hashCode();
        } else {
            if (i2 == 3) {
                i = ((iHashCode2 * 37) + 3) * 53;
                iHashCode = getFallbackResponse().hashCode();
            }
            int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode3;
            return iHashCode3;
        }
        iHashCode2 = i + iHashCode;
        int iHashCode32 = (iHashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode32;
        return iHashCode32;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9468newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9471toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum LoadBalanceResponseTypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        INITIAL_RESPONSE(1),
        SERVER_LIST(2),
        FALLBACK_RESPONSE(3),
        LOADBALANCERESPONSETYPE_NOT_SET(0);

        private final int value;

        LoadBalanceResponseTypeCase(int i) {
            this.value = i;
        }

        public static LoadBalanceResponseTypeCase forNumber(int i) {
            if (i == 0) {
                return LOADBALANCERESPONSETYPE_NOT_SET;
            }
            if (i == 1) {
                return INITIAL_RESPONSE;
            }
            if (i == 2) {
                return SERVER_LIST;
            }
            if (i != 3) {
                return null;
            }
            return FALLBACK_RESPONSE;
        }

        @Deprecated
        public static LoadBalanceResponseTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LoadBalanceResponseOrBuilder {
        private SingleFieldBuilderV3<FallbackResponse, FallbackResponse.Builder, FallbackResponseOrBuilder> fallbackResponseBuilder_;
        private SingleFieldBuilderV3<InitialLoadBalanceResponse, InitialLoadBalanceResponse.Builder, InitialLoadBalanceResponseOrBuilder> initialResponseBuilder_;
        private int loadBalanceResponseTypeCase_;
        private Object loadBalanceResponseType_;
        private SingleFieldBuilderV3<ServerList, ServerList.Builder, ServerListOrBuilder> serverListBuilder_;

        private Builder() {
            this.loadBalanceResponseTypeCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.loadBalanceResponseTypeCase_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceResponse_descriptor;
        }

        @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
        public boolean hasFallbackResponse() {
            return this.loadBalanceResponseTypeCase_ == 3;
        }

        @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
        public boolean hasInitialResponse() {
            return this.loadBalanceResponseTypeCase_ == 1;
        }

        @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
        public boolean hasServerList() {
            return this.loadBalanceResponseTypeCase_ == 2;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(LoadBalanceResponse.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = LoadBalanceResponse.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9482clear() {
            super.clear();
            this.loadBalanceResponseTypeCase_ = 0;
            this.loadBalanceResponseType_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceResponse_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LoadBalanceResponse m9495getDefaultInstanceForType() {
            return LoadBalanceResponse.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LoadBalanceResponse m9476build() throws UninitializedMessageException {
            LoadBalanceResponse loadBalanceResponseM9478buildPartial = m9478buildPartial();
            if (loadBalanceResponseM9478buildPartial.isInitialized()) {
                return loadBalanceResponseM9478buildPartial;
            }
            throw newUninitializedMessageException(loadBalanceResponseM9478buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LoadBalanceResponse m9478buildPartial() {
            LoadBalanceResponse loadBalanceResponse = new LoadBalanceResponse(this);
            if (this.loadBalanceResponseTypeCase_ == 1) {
                SingleFieldBuilderV3<InitialLoadBalanceResponse, InitialLoadBalanceResponse.Builder, InitialLoadBalanceResponseOrBuilder> singleFieldBuilderV3 = this.initialResponseBuilder_;
                if (singleFieldBuilderV3 == null) {
                    loadBalanceResponse.loadBalanceResponseType_ = this.loadBalanceResponseType_;
                } else {
                    loadBalanceResponse.loadBalanceResponseType_ = singleFieldBuilderV3.build();
                }
            }
            if (this.loadBalanceResponseTypeCase_ == 2) {
                SingleFieldBuilderV3<ServerList, ServerList.Builder, ServerListOrBuilder> singleFieldBuilderV32 = this.serverListBuilder_;
                if (singleFieldBuilderV32 == null) {
                    loadBalanceResponse.loadBalanceResponseType_ = this.loadBalanceResponseType_;
                } else {
                    loadBalanceResponse.loadBalanceResponseType_ = singleFieldBuilderV32.build();
                }
            }
            if (this.loadBalanceResponseTypeCase_ == 3) {
                SingleFieldBuilderV3<FallbackResponse, FallbackResponse.Builder, FallbackResponseOrBuilder> singleFieldBuilderV33 = this.fallbackResponseBuilder_;
                if (singleFieldBuilderV33 == null) {
                    loadBalanceResponse.loadBalanceResponseType_ = this.loadBalanceResponseType_;
                } else {
                    loadBalanceResponse.loadBalanceResponseType_ = singleFieldBuilderV33.build();
                }
            }
            loadBalanceResponse.loadBalanceResponseTypeCase_ = this.loadBalanceResponseTypeCase_;
            onBuilt();
            return loadBalanceResponse;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9494clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9506setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9484clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9487clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9508setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9474addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9499mergeFrom(Message message) {
            if (message instanceof LoadBalanceResponse) {
                return mergeFrom((LoadBalanceResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(LoadBalanceResponse loadBalanceResponse) {
            if (loadBalanceResponse == LoadBalanceResponse.getDefaultInstance()) {
                return this;
            }
            int i = AnonymousClass2.$SwitchMap$io$grpc$lb$v1$LoadBalanceResponse$LoadBalanceResponseTypeCase[loadBalanceResponse.getLoadBalanceResponseTypeCase().ordinal()];
            if (i == 1) {
                mergeInitialResponse(loadBalanceResponse.getInitialResponse());
            } else if (i == 2) {
                mergeServerList(loadBalanceResponse.getServerList());
            } else if (i == 3) {
                mergeFallbackResponse(loadBalanceResponse.getFallbackResponse());
            }
            m9504mergeUnknownFields(loadBalanceResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.lb.v1.LoadBalanceResponse.Builder m9500mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.lb.v1.LoadBalanceResponse.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.lb.v1.LoadBalanceResponse r3 = (io.grpc.lb.v1.LoadBalanceResponse) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.lb.v1.LoadBalanceResponse r4 = (io.grpc.lb.v1.LoadBalanceResponse) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.lb.v1.LoadBalanceResponse.Builder.m9500mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.lb.v1.LoadBalanceResponse$Builder");
        }

        @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
        public LoadBalanceResponseTypeCase getLoadBalanceResponseTypeCase() {
            return LoadBalanceResponseTypeCase.forNumber(this.loadBalanceResponseTypeCase_);
        }

        public Builder clearLoadBalanceResponseType() {
            this.loadBalanceResponseTypeCase_ = 0;
            this.loadBalanceResponseType_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
        public InitialLoadBalanceResponse getInitialResponse() {
            SingleFieldBuilderV3<InitialLoadBalanceResponse, InitialLoadBalanceResponse.Builder, InitialLoadBalanceResponseOrBuilder> singleFieldBuilderV3 = this.initialResponseBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.loadBalanceResponseTypeCase_ == 1) {
                    return (InitialLoadBalanceResponse) this.loadBalanceResponseType_;
                }
                return InitialLoadBalanceResponse.getDefaultInstance();
            }
            if (this.loadBalanceResponseTypeCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            }
            return InitialLoadBalanceResponse.getDefaultInstance();
        }

        public Builder setInitialResponse(InitialLoadBalanceResponse initialLoadBalanceResponse) {
            SingleFieldBuilderV3<InitialLoadBalanceResponse, InitialLoadBalanceResponse.Builder, InitialLoadBalanceResponseOrBuilder> singleFieldBuilderV3 = this.initialResponseBuilder_;
            if (singleFieldBuilderV3 == null) {
                initialLoadBalanceResponse.getClass();
                this.loadBalanceResponseType_ = initialLoadBalanceResponse;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(initialLoadBalanceResponse);
            }
            this.loadBalanceResponseTypeCase_ = 1;
            return this;
        }

        public Builder setInitialResponse(InitialLoadBalanceResponse.Builder builder) {
            SingleFieldBuilderV3<InitialLoadBalanceResponse, InitialLoadBalanceResponse.Builder, InitialLoadBalanceResponseOrBuilder> singleFieldBuilderV3 = this.initialResponseBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.loadBalanceResponseType_ = builder.m9384build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m9384build());
            }
            this.loadBalanceResponseTypeCase_ = 1;
            return this;
        }

        public Builder mergeInitialResponse(InitialLoadBalanceResponse initialLoadBalanceResponse) {
            SingleFieldBuilderV3<InitialLoadBalanceResponse, InitialLoadBalanceResponse.Builder, InitialLoadBalanceResponseOrBuilder> singleFieldBuilderV3 = this.initialResponseBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.loadBalanceResponseTypeCase_ != 1 || this.loadBalanceResponseType_ == InitialLoadBalanceResponse.getDefaultInstance()) {
                    this.loadBalanceResponseType_ = initialLoadBalanceResponse;
                } else {
                    this.loadBalanceResponseType_ = InitialLoadBalanceResponse.newBuilder((InitialLoadBalanceResponse) this.loadBalanceResponseType_).mergeFrom(initialLoadBalanceResponse).m9386buildPartial();
                }
                onChanged();
            } else {
                if (this.loadBalanceResponseTypeCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(initialLoadBalanceResponse);
                }
                this.initialResponseBuilder_.setMessage(initialLoadBalanceResponse);
            }
            this.loadBalanceResponseTypeCase_ = 1;
            return this;
        }

        public Builder clearInitialResponse() {
            SingleFieldBuilderV3<InitialLoadBalanceResponse, InitialLoadBalanceResponse.Builder, InitialLoadBalanceResponseOrBuilder> singleFieldBuilderV3 = this.initialResponseBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.loadBalanceResponseTypeCase_ == 1) {
                    this.loadBalanceResponseTypeCase_ = 0;
                    this.loadBalanceResponseType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.loadBalanceResponseTypeCase_ == 1) {
                this.loadBalanceResponseTypeCase_ = 0;
                this.loadBalanceResponseType_ = null;
                onChanged();
            }
            return this;
        }

        public InitialLoadBalanceResponse.Builder getInitialResponseBuilder() {
            return getInitialResponseFieldBuilder().getBuilder();
        }

        @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
        public InitialLoadBalanceResponseOrBuilder getInitialResponseOrBuilder() {
            SingleFieldBuilderV3<InitialLoadBalanceResponse, InitialLoadBalanceResponse.Builder, InitialLoadBalanceResponseOrBuilder> singleFieldBuilderV3;
            int i = this.loadBalanceResponseTypeCase_;
            if (i == 1 && (singleFieldBuilderV3 = this.initialResponseBuilder_) != null) {
                return (InitialLoadBalanceResponseOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 1) {
                return (InitialLoadBalanceResponse) this.loadBalanceResponseType_;
            }
            return InitialLoadBalanceResponse.getDefaultInstance();
        }

        private SingleFieldBuilderV3<InitialLoadBalanceResponse, InitialLoadBalanceResponse.Builder, InitialLoadBalanceResponseOrBuilder> getInitialResponseFieldBuilder() {
            if (this.initialResponseBuilder_ == null) {
                if (this.loadBalanceResponseTypeCase_ != 1) {
                    this.loadBalanceResponseType_ = InitialLoadBalanceResponse.getDefaultInstance();
                }
                this.initialResponseBuilder_ = new SingleFieldBuilderV3<>((InitialLoadBalanceResponse) this.loadBalanceResponseType_, getParentForChildren(), isClean());
                this.loadBalanceResponseType_ = null;
            }
            this.loadBalanceResponseTypeCase_ = 1;
            onChanged();
            return this.initialResponseBuilder_;
        }

        @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
        public ServerList getServerList() {
            SingleFieldBuilderV3<ServerList, ServerList.Builder, ServerListOrBuilder> singleFieldBuilderV3 = this.serverListBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.loadBalanceResponseTypeCase_ == 2) {
                    return (ServerList) this.loadBalanceResponseType_;
                }
                return ServerList.getDefaultInstance();
            }
            if (this.loadBalanceResponseTypeCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return ServerList.getDefaultInstance();
        }

        public Builder setServerList(ServerList serverList) {
            SingleFieldBuilderV3<ServerList, ServerList.Builder, ServerListOrBuilder> singleFieldBuilderV3 = this.serverListBuilder_;
            if (singleFieldBuilderV3 == null) {
                serverList.getClass();
                this.loadBalanceResponseType_ = serverList;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(serverList);
            }
            this.loadBalanceResponseTypeCase_ = 2;
            return this;
        }

        public Builder setServerList(ServerList.Builder builder) {
            SingleFieldBuilderV3<ServerList, ServerList.Builder, ServerListOrBuilder> singleFieldBuilderV3 = this.serverListBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.loadBalanceResponseType_ = builder.m9568build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m9568build());
            }
            this.loadBalanceResponseTypeCase_ = 2;
            return this;
        }

        public Builder mergeServerList(ServerList serverList) {
            SingleFieldBuilderV3<ServerList, ServerList.Builder, ServerListOrBuilder> singleFieldBuilderV3 = this.serverListBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.loadBalanceResponseTypeCase_ != 2 || this.loadBalanceResponseType_ == ServerList.getDefaultInstance()) {
                    this.loadBalanceResponseType_ = serverList;
                } else {
                    this.loadBalanceResponseType_ = ServerList.newBuilder((ServerList) this.loadBalanceResponseType_).mergeFrom(serverList).m9570buildPartial();
                }
                onChanged();
            } else {
                if (this.loadBalanceResponseTypeCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(serverList);
                }
                this.serverListBuilder_.setMessage(serverList);
            }
            this.loadBalanceResponseTypeCase_ = 2;
            return this;
        }

        public Builder clearServerList() {
            SingleFieldBuilderV3<ServerList, ServerList.Builder, ServerListOrBuilder> singleFieldBuilderV3 = this.serverListBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.loadBalanceResponseTypeCase_ == 2) {
                    this.loadBalanceResponseTypeCase_ = 0;
                    this.loadBalanceResponseType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.loadBalanceResponseTypeCase_ == 2) {
                this.loadBalanceResponseTypeCase_ = 0;
                this.loadBalanceResponseType_ = null;
                onChanged();
            }
            return this;
        }

        public ServerList.Builder getServerListBuilder() {
            return getServerListFieldBuilder().getBuilder();
        }

        @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
        public ServerListOrBuilder getServerListOrBuilder() {
            SingleFieldBuilderV3<ServerList, ServerList.Builder, ServerListOrBuilder> singleFieldBuilderV3;
            int i = this.loadBalanceResponseTypeCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.serverListBuilder_) != null) {
                return (ServerListOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (ServerList) this.loadBalanceResponseType_;
            }
            return ServerList.getDefaultInstance();
        }

        private SingleFieldBuilderV3<ServerList, ServerList.Builder, ServerListOrBuilder> getServerListFieldBuilder() {
            if (this.serverListBuilder_ == null) {
                if (this.loadBalanceResponseTypeCase_ != 2) {
                    this.loadBalanceResponseType_ = ServerList.getDefaultInstance();
                }
                this.serverListBuilder_ = new SingleFieldBuilderV3<>((ServerList) this.loadBalanceResponseType_, getParentForChildren(), isClean());
                this.loadBalanceResponseType_ = null;
            }
            this.loadBalanceResponseTypeCase_ = 2;
            onChanged();
            return this.serverListBuilder_;
        }

        @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
        public FallbackResponse getFallbackResponse() {
            SingleFieldBuilderV3<FallbackResponse, FallbackResponse.Builder, FallbackResponseOrBuilder> singleFieldBuilderV3 = this.fallbackResponseBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.loadBalanceResponseTypeCase_ == 3) {
                    return (FallbackResponse) this.loadBalanceResponseType_;
                }
                return FallbackResponse.getDefaultInstance();
            }
            if (this.loadBalanceResponseTypeCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return FallbackResponse.getDefaultInstance();
        }

        public Builder setFallbackResponse(FallbackResponse fallbackResponse) {
            SingleFieldBuilderV3<FallbackResponse, FallbackResponse.Builder, FallbackResponseOrBuilder> singleFieldBuilderV3 = this.fallbackResponseBuilder_;
            if (singleFieldBuilderV3 == null) {
                fallbackResponse.getClass();
                this.loadBalanceResponseType_ = fallbackResponse;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(fallbackResponse);
            }
            this.loadBalanceResponseTypeCase_ = 3;
            return this;
        }

        public Builder setFallbackResponse(FallbackResponse.Builder builder) {
            SingleFieldBuilderV3<FallbackResponse, FallbackResponse.Builder, FallbackResponseOrBuilder> singleFieldBuilderV3 = this.fallbackResponseBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.loadBalanceResponseType_ = builder.m9292build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m9292build());
            }
            this.loadBalanceResponseTypeCase_ = 3;
            return this;
        }

        public Builder mergeFallbackResponse(FallbackResponse fallbackResponse) {
            SingleFieldBuilderV3<FallbackResponse, FallbackResponse.Builder, FallbackResponseOrBuilder> singleFieldBuilderV3 = this.fallbackResponseBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.loadBalanceResponseTypeCase_ != 3 || this.loadBalanceResponseType_ == FallbackResponse.getDefaultInstance()) {
                    this.loadBalanceResponseType_ = fallbackResponse;
                } else {
                    this.loadBalanceResponseType_ = FallbackResponse.newBuilder((FallbackResponse) this.loadBalanceResponseType_).mergeFrom(fallbackResponse).m9294buildPartial();
                }
                onChanged();
            } else {
                if (this.loadBalanceResponseTypeCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(fallbackResponse);
                }
                this.fallbackResponseBuilder_.setMessage(fallbackResponse);
            }
            this.loadBalanceResponseTypeCase_ = 3;
            return this;
        }

        public Builder clearFallbackResponse() {
            SingleFieldBuilderV3<FallbackResponse, FallbackResponse.Builder, FallbackResponseOrBuilder> singleFieldBuilderV3 = this.fallbackResponseBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.loadBalanceResponseTypeCase_ == 3) {
                    this.loadBalanceResponseTypeCase_ = 0;
                    this.loadBalanceResponseType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.loadBalanceResponseTypeCase_ == 3) {
                this.loadBalanceResponseTypeCase_ = 0;
                this.loadBalanceResponseType_ = null;
                onChanged();
            }
            return this;
        }

        public FallbackResponse.Builder getFallbackResponseBuilder() {
            return getFallbackResponseFieldBuilder().getBuilder();
        }

        @Override // io.grpc.lb.v1.LoadBalanceResponseOrBuilder
        public FallbackResponseOrBuilder getFallbackResponseOrBuilder() {
            SingleFieldBuilderV3<FallbackResponse, FallbackResponse.Builder, FallbackResponseOrBuilder> singleFieldBuilderV3;
            int i = this.loadBalanceResponseTypeCase_;
            if (i == 3 && (singleFieldBuilderV3 = this.fallbackResponseBuilder_) != null) {
                return (FallbackResponseOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 3) {
                return (FallbackResponse) this.loadBalanceResponseType_;
            }
            return FallbackResponse.getDefaultInstance();
        }

        private SingleFieldBuilderV3<FallbackResponse, FallbackResponse.Builder, FallbackResponseOrBuilder> getFallbackResponseFieldBuilder() {
            if (this.fallbackResponseBuilder_ == null) {
                if (this.loadBalanceResponseTypeCase_ != 3) {
                    this.loadBalanceResponseType_ = FallbackResponse.getDefaultInstance();
                }
                this.fallbackResponseBuilder_ = new SingleFieldBuilderV3<>((FallbackResponse) this.loadBalanceResponseType_, getParentForChildren(), isClean());
                this.loadBalanceResponseType_ = null;
            }
            this.loadBalanceResponseTypeCase_ = 3;
            onChanged();
            return this.fallbackResponseBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9510setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9504mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.lb.v1.LoadBalanceResponse$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$lb$v1$LoadBalanceResponse$LoadBalanceResponseTypeCase;

        static {
            int[] iArr = new int[LoadBalanceResponseTypeCase.values().length];
            $SwitchMap$io$grpc$lb$v1$LoadBalanceResponse$LoadBalanceResponseTypeCase = iArr;
            try {
                iArr[LoadBalanceResponseTypeCase.INITIAL_RESPONSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$lb$v1$LoadBalanceResponse$LoadBalanceResponseTypeCase[LoadBalanceResponseTypeCase.SERVER_LIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$grpc$lb$v1$LoadBalanceResponse$LoadBalanceResponseTypeCase[LoadBalanceResponseTypeCase.FALLBACK_RESPONSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$grpc$lb$v1$LoadBalanceResponse$LoadBalanceResponseTypeCase[LoadBalanceResponseTypeCase.LOADBALANCERESPONSETYPE_NOT_SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
