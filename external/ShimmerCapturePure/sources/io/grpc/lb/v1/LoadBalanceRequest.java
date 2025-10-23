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
import io.grpc.lb.v1.ClientStats;
import io.grpc.lb.v1.InitialLoadBalanceRequest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class LoadBalanceRequest extends GeneratedMessageV3 implements LoadBalanceRequestOrBuilder {
    public static final int CLIENT_STATS_FIELD_NUMBER = 2;
    public static final int INITIAL_REQUEST_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final LoadBalanceRequest DEFAULT_INSTANCE = new LoadBalanceRequest();
    private static final Parser<LoadBalanceRequest> PARSER = new AbstractParser<LoadBalanceRequest>() { // from class: io.grpc.lb.v1.LoadBalanceRequest.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public LoadBalanceRequest m9427parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new LoadBalanceRequest(codedInputStream, extensionRegistryLite);
        }
    };
    private int loadBalanceRequestTypeCase_;
    private Object loadBalanceRequestType_;
    private byte memoizedIsInitialized;

    private LoadBalanceRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.loadBalanceRequestTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private LoadBalanceRequest() {
        this.loadBalanceRequestTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private LoadBalanceRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                InitialLoadBalanceRequest.Builder builderM9333toBuilder = this.loadBalanceRequestTypeCase_ == 1 ? ((InitialLoadBalanceRequest) this.loadBalanceRequestType_).m9333toBuilder() : null;
                                MessageLite message = codedInputStream.readMessage(InitialLoadBalanceRequest.parser(), extensionRegistryLite);
                                this.loadBalanceRequestType_ = message;
                                if (builderM9333toBuilder != null) {
                                    builderM9333toBuilder.mergeFrom((InitialLoadBalanceRequest) message);
                                    this.loadBalanceRequestType_ = builderM9333toBuilder.m9340buildPartial();
                                }
                                this.loadBalanceRequestTypeCase_ = 1;
                            } else if (tag == 18) {
                                ClientStats.Builder builderM9195toBuilder = this.loadBalanceRequestTypeCase_ == 2 ? ((ClientStats) this.loadBalanceRequestType_).m9195toBuilder() : null;
                                MessageLite message2 = codedInputStream.readMessage(ClientStats.parser(), extensionRegistryLite);
                                this.loadBalanceRequestType_ = message2;
                                if (builderM9195toBuilder != null) {
                                    builderM9195toBuilder.mergeFrom((ClientStats) message2);
                                    this.loadBalanceRequestType_ = builderM9195toBuilder.m9202buildPartial();
                                }
                                this.loadBalanceRequestTypeCase_ = 2;
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

    public static LoadBalanceRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LoadBalanceRequest> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceRequest_descriptor;
    }

    public static LoadBalanceRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (LoadBalanceRequest) PARSER.parseFrom(byteBuffer);
    }

    public static LoadBalanceRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LoadBalanceRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static LoadBalanceRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (LoadBalanceRequest) PARSER.parseFrom(byteString);
    }

    public static LoadBalanceRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LoadBalanceRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static LoadBalanceRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (LoadBalanceRequest) PARSER.parseFrom(bArr);
    }

    public static LoadBalanceRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LoadBalanceRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static LoadBalanceRequest parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static LoadBalanceRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LoadBalanceRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static LoadBalanceRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LoadBalanceRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static LoadBalanceRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m9425toBuilder();
    }

    public static Builder newBuilder(LoadBalanceRequest loadBalanceRequest) {
        return DEFAULT_INSTANCE.m9425toBuilder().mergeFrom(loadBalanceRequest);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public LoadBalanceRequest m9420getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<LoadBalanceRequest> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.lb.v1.LoadBalanceRequestOrBuilder
    public boolean hasClientStats() {
        return this.loadBalanceRequestTypeCase_ == 2;
    }

    @Override // io.grpc.lb.v1.LoadBalanceRequestOrBuilder
    public boolean hasInitialRequest() {
        return this.loadBalanceRequestTypeCase_ == 1;
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
        return new LoadBalanceRequest();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(LoadBalanceRequest.class, Builder.class);
    }

    @Override // io.grpc.lb.v1.LoadBalanceRequestOrBuilder
    public LoadBalanceRequestTypeCase getLoadBalanceRequestTypeCase() {
        return LoadBalanceRequestTypeCase.forNumber(this.loadBalanceRequestTypeCase_);
    }

    @Override // io.grpc.lb.v1.LoadBalanceRequestOrBuilder
    public InitialLoadBalanceRequest getInitialRequest() {
        if (this.loadBalanceRequestTypeCase_ == 1) {
            return (InitialLoadBalanceRequest) this.loadBalanceRequestType_;
        }
        return InitialLoadBalanceRequest.getDefaultInstance();
    }

    @Override // io.grpc.lb.v1.LoadBalanceRequestOrBuilder
    public InitialLoadBalanceRequestOrBuilder getInitialRequestOrBuilder() {
        if (this.loadBalanceRequestTypeCase_ == 1) {
            return (InitialLoadBalanceRequest) this.loadBalanceRequestType_;
        }
        return InitialLoadBalanceRequest.getDefaultInstance();
    }

    @Override // io.grpc.lb.v1.LoadBalanceRequestOrBuilder
    public ClientStats getClientStats() {
        if (this.loadBalanceRequestTypeCase_ == 2) {
            return (ClientStats) this.loadBalanceRequestType_;
        }
        return ClientStats.getDefaultInstance();
    }

    @Override // io.grpc.lb.v1.LoadBalanceRequestOrBuilder
    public ClientStatsOrBuilder getClientStatsOrBuilder() {
        if (this.loadBalanceRequestTypeCase_ == 2) {
            return (ClientStats) this.loadBalanceRequestType_;
        }
        return ClientStats.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.loadBalanceRequestTypeCase_ == 1) {
            codedOutputStream.writeMessage(1, (InitialLoadBalanceRequest) this.loadBalanceRequestType_);
        }
        if (this.loadBalanceRequestTypeCase_ == 2) {
            codedOutputStream.writeMessage(2, (ClientStats) this.loadBalanceRequestType_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.loadBalanceRequestTypeCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (InitialLoadBalanceRequest) this.loadBalanceRequestType_) : 0;
        if (this.loadBalanceRequestTypeCase_ == 2) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, (ClientStats) this.loadBalanceRequestType_);
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LoadBalanceRequest)) {
            return super.equals(obj);
        }
        LoadBalanceRequest loadBalanceRequest = (LoadBalanceRequest) obj;
        if (!getLoadBalanceRequestTypeCase().equals(loadBalanceRequest.getLoadBalanceRequestTypeCase())) {
            return false;
        }
        int i = this.loadBalanceRequestTypeCase_;
        if (i == 1) {
            if (!getInitialRequest().equals(loadBalanceRequest.getInitialRequest())) {
                return false;
            }
        } else if (i == 2 && !getClientStats().equals(loadBalanceRequest.getClientStats())) {
            return false;
        }
        return this.unknownFields.equals(loadBalanceRequest.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        int i2 = this.loadBalanceRequestTypeCase_;
        if (i2 == 1) {
            i = ((iHashCode2 * 37) + 1) * 53;
            iHashCode = getInitialRequest().hashCode();
        } else {
            if (i2 == 2) {
                i = ((iHashCode2 * 37) + 2) * 53;
                iHashCode = getClientStats().hashCode();
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
    public Builder m9422newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9425toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum LoadBalanceRequestTypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        INITIAL_REQUEST(1),
        CLIENT_STATS(2),
        LOADBALANCEREQUESTTYPE_NOT_SET(0);

        private final int value;

        LoadBalanceRequestTypeCase(int i) {
            this.value = i;
        }

        public static LoadBalanceRequestTypeCase forNumber(int i) {
            if (i == 0) {
                return LOADBALANCEREQUESTTYPE_NOT_SET;
            }
            if (i == 1) {
                return INITIAL_REQUEST;
            }
            if (i != 2) {
                return null;
            }
            return CLIENT_STATS;
        }

        @Deprecated
        public static LoadBalanceRequestTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LoadBalanceRequestOrBuilder {
        private SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> clientStatsBuilder_;
        private SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> initialRequestBuilder_;
        private int loadBalanceRequestTypeCase_;
        private Object loadBalanceRequestType_;

        private Builder() {
            this.loadBalanceRequestTypeCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.loadBalanceRequestTypeCase_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceRequest_descriptor;
        }

        @Override // io.grpc.lb.v1.LoadBalanceRequestOrBuilder
        public boolean hasClientStats() {
            return this.loadBalanceRequestTypeCase_ == 2;
        }

        @Override // io.grpc.lb.v1.LoadBalanceRequestOrBuilder
        public boolean hasInitialRequest() {
            return this.loadBalanceRequestTypeCase_ == 1;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(LoadBalanceRequest.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = LoadBalanceRequest.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9436clear() {
            super.clear();
            this.loadBalanceRequestTypeCase_ = 0;
            this.loadBalanceRequestType_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceRequest_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LoadBalanceRequest m9449getDefaultInstanceForType() {
            return LoadBalanceRequest.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LoadBalanceRequest m9430build() throws UninitializedMessageException {
            LoadBalanceRequest loadBalanceRequestM9432buildPartial = m9432buildPartial();
            if (loadBalanceRequestM9432buildPartial.isInitialized()) {
                return loadBalanceRequestM9432buildPartial;
            }
            throw newUninitializedMessageException(loadBalanceRequestM9432buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LoadBalanceRequest m9432buildPartial() {
            LoadBalanceRequest loadBalanceRequest = new LoadBalanceRequest(this);
            if (this.loadBalanceRequestTypeCase_ == 1) {
                SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> singleFieldBuilderV3 = this.initialRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    loadBalanceRequest.loadBalanceRequestType_ = this.loadBalanceRequestType_;
                } else {
                    loadBalanceRequest.loadBalanceRequestType_ = singleFieldBuilderV3.build();
                }
            }
            if (this.loadBalanceRequestTypeCase_ == 2) {
                SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> singleFieldBuilderV32 = this.clientStatsBuilder_;
                if (singleFieldBuilderV32 == null) {
                    loadBalanceRequest.loadBalanceRequestType_ = this.loadBalanceRequestType_;
                } else {
                    loadBalanceRequest.loadBalanceRequestType_ = singleFieldBuilderV32.build();
                }
            }
            loadBalanceRequest.loadBalanceRequestTypeCase_ = this.loadBalanceRequestTypeCase_;
            onBuilt();
            return loadBalanceRequest;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9448clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9460setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9438clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9441clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9462setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9428addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9453mergeFrom(Message message) {
            if (message instanceof LoadBalanceRequest) {
                return mergeFrom((LoadBalanceRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(LoadBalanceRequest loadBalanceRequest) {
            if (loadBalanceRequest == LoadBalanceRequest.getDefaultInstance()) {
                return this;
            }
            int i = AnonymousClass2.$SwitchMap$io$grpc$lb$v1$LoadBalanceRequest$LoadBalanceRequestTypeCase[loadBalanceRequest.getLoadBalanceRequestTypeCase().ordinal()];
            if (i == 1) {
                mergeInitialRequest(loadBalanceRequest.getInitialRequest());
            } else if (i == 2) {
                mergeClientStats(loadBalanceRequest.getClientStats());
            }
            m9458mergeUnknownFields(loadBalanceRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.lb.v1.LoadBalanceRequest.Builder m9454mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.lb.v1.LoadBalanceRequest.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.lb.v1.LoadBalanceRequest r3 = (io.grpc.lb.v1.LoadBalanceRequest) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.lb.v1.LoadBalanceRequest r4 = (io.grpc.lb.v1.LoadBalanceRequest) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.lb.v1.LoadBalanceRequest.Builder.m9454mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.lb.v1.LoadBalanceRequest$Builder");
        }

        @Override // io.grpc.lb.v1.LoadBalanceRequestOrBuilder
        public LoadBalanceRequestTypeCase getLoadBalanceRequestTypeCase() {
            return LoadBalanceRequestTypeCase.forNumber(this.loadBalanceRequestTypeCase_);
        }

        public Builder clearLoadBalanceRequestType() {
            this.loadBalanceRequestTypeCase_ = 0;
            this.loadBalanceRequestType_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.lb.v1.LoadBalanceRequestOrBuilder
        public InitialLoadBalanceRequest getInitialRequest() {
            SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> singleFieldBuilderV3 = this.initialRequestBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.loadBalanceRequestTypeCase_ == 1) {
                    return (InitialLoadBalanceRequest) this.loadBalanceRequestType_;
                }
                return InitialLoadBalanceRequest.getDefaultInstance();
            }
            if (this.loadBalanceRequestTypeCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            }
            return InitialLoadBalanceRequest.getDefaultInstance();
        }

        public Builder setInitialRequest(InitialLoadBalanceRequest initialLoadBalanceRequest) {
            SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> singleFieldBuilderV3 = this.initialRequestBuilder_;
            if (singleFieldBuilderV3 == null) {
                initialLoadBalanceRequest.getClass();
                this.loadBalanceRequestType_ = initialLoadBalanceRequest;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(initialLoadBalanceRequest);
            }
            this.loadBalanceRequestTypeCase_ = 1;
            return this;
        }

        public Builder setInitialRequest(InitialLoadBalanceRequest.Builder builder) {
            SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> singleFieldBuilderV3 = this.initialRequestBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.loadBalanceRequestType_ = builder.m9338build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m9338build());
            }
            this.loadBalanceRequestTypeCase_ = 1;
            return this;
        }

        public Builder mergeInitialRequest(InitialLoadBalanceRequest initialLoadBalanceRequest) {
            SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> singleFieldBuilderV3 = this.initialRequestBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.loadBalanceRequestTypeCase_ != 1 || this.loadBalanceRequestType_ == InitialLoadBalanceRequest.getDefaultInstance()) {
                    this.loadBalanceRequestType_ = initialLoadBalanceRequest;
                } else {
                    this.loadBalanceRequestType_ = InitialLoadBalanceRequest.newBuilder((InitialLoadBalanceRequest) this.loadBalanceRequestType_).mergeFrom(initialLoadBalanceRequest).m9340buildPartial();
                }
                onChanged();
            } else {
                if (this.loadBalanceRequestTypeCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(initialLoadBalanceRequest);
                }
                this.initialRequestBuilder_.setMessage(initialLoadBalanceRequest);
            }
            this.loadBalanceRequestTypeCase_ = 1;
            return this;
        }

        public Builder clearInitialRequest() {
            SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> singleFieldBuilderV3 = this.initialRequestBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.loadBalanceRequestTypeCase_ == 1) {
                    this.loadBalanceRequestTypeCase_ = 0;
                    this.loadBalanceRequestType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.loadBalanceRequestTypeCase_ == 1) {
                this.loadBalanceRequestTypeCase_ = 0;
                this.loadBalanceRequestType_ = null;
                onChanged();
            }
            return this;
        }

        public InitialLoadBalanceRequest.Builder getInitialRequestBuilder() {
            return getInitialRequestFieldBuilder().getBuilder();
        }

        @Override // io.grpc.lb.v1.LoadBalanceRequestOrBuilder
        public InitialLoadBalanceRequestOrBuilder getInitialRequestOrBuilder() {
            SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> singleFieldBuilderV3;
            int i = this.loadBalanceRequestTypeCase_;
            if (i == 1 && (singleFieldBuilderV3 = this.initialRequestBuilder_) != null) {
                return (InitialLoadBalanceRequestOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 1) {
                return (InitialLoadBalanceRequest) this.loadBalanceRequestType_;
            }
            return InitialLoadBalanceRequest.getDefaultInstance();
        }

        private SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> getInitialRequestFieldBuilder() {
            if (this.initialRequestBuilder_ == null) {
                if (this.loadBalanceRequestTypeCase_ != 1) {
                    this.loadBalanceRequestType_ = InitialLoadBalanceRequest.getDefaultInstance();
                }
                this.initialRequestBuilder_ = new SingleFieldBuilderV3<>((InitialLoadBalanceRequest) this.loadBalanceRequestType_, getParentForChildren(), isClean());
                this.loadBalanceRequestType_ = null;
            }
            this.loadBalanceRequestTypeCase_ = 1;
            onChanged();
            return this.initialRequestBuilder_;
        }

        @Override // io.grpc.lb.v1.LoadBalanceRequestOrBuilder
        public ClientStats getClientStats() {
            SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> singleFieldBuilderV3 = this.clientStatsBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.loadBalanceRequestTypeCase_ == 2) {
                    return (ClientStats) this.loadBalanceRequestType_;
                }
                return ClientStats.getDefaultInstance();
            }
            if (this.loadBalanceRequestTypeCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return ClientStats.getDefaultInstance();
        }

        public Builder setClientStats(ClientStats clientStats) {
            SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> singleFieldBuilderV3 = this.clientStatsBuilder_;
            if (singleFieldBuilderV3 == null) {
                clientStats.getClass();
                this.loadBalanceRequestType_ = clientStats;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(clientStats);
            }
            this.loadBalanceRequestTypeCase_ = 2;
            return this;
        }

        public Builder setClientStats(ClientStats.Builder builder) {
            SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> singleFieldBuilderV3 = this.clientStatsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.loadBalanceRequestType_ = builder.m9200build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m9200build());
            }
            this.loadBalanceRequestTypeCase_ = 2;
            return this;
        }

        public Builder mergeClientStats(ClientStats clientStats) {
            SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> singleFieldBuilderV3 = this.clientStatsBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.loadBalanceRequestTypeCase_ != 2 || this.loadBalanceRequestType_ == ClientStats.getDefaultInstance()) {
                    this.loadBalanceRequestType_ = clientStats;
                } else {
                    this.loadBalanceRequestType_ = ClientStats.newBuilder((ClientStats) this.loadBalanceRequestType_).mergeFrom(clientStats).m9202buildPartial();
                }
                onChanged();
            } else {
                if (this.loadBalanceRequestTypeCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(clientStats);
                }
                this.clientStatsBuilder_.setMessage(clientStats);
            }
            this.loadBalanceRequestTypeCase_ = 2;
            return this;
        }

        public Builder clearClientStats() {
            SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> singleFieldBuilderV3 = this.clientStatsBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.loadBalanceRequestTypeCase_ == 2) {
                    this.loadBalanceRequestTypeCase_ = 0;
                    this.loadBalanceRequestType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.loadBalanceRequestTypeCase_ == 2) {
                this.loadBalanceRequestTypeCase_ = 0;
                this.loadBalanceRequestType_ = null;
                onChanged();
            }
            return this;
        }

        public ClientStats.Builder getClientStatsBuilder() {
            return getClientStatsFieldBuilder().getBuilder();
        }

        @Override // io.grpc.lb.v1.LoadBalanceRequestOrBuilder
        public ClientStatsOrBuilder getClientStatsOrBuilder() {
            SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> singleFieldBuilderV3;
            int i = this.loadBalanceRequestTypeCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.clientStatsBuilder_) != null) {
                return (ClientStatsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (ClientStats) this.loadBalanceRequestType_;
            }
            return ClientStats.getDefaultInstance();
        }

        private SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> getClientStatsFieldBuilder() {
            if (this.clientStatsBuilder_ == null) {
                if (this.loadBalanceRequestTypeCase_ != 2) {
                    this.loadBalanceRequestType_ = ClientStats.getDefaultInstance();
                }
                this.clientStatsBuilder_ = new SingleFieldBuilderV3<>((ClientStats) this.loadBalanceRequestType_, getParentForChildren(), isClean());
                this.loadBalanceRequestType_ = null;
            }
            this.loadBalanceRequestTypeCase_ = 2;
            onChanged();
            return this.clientStatsBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9464setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9458mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.lb.v1.LoadBalanceRequest$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$lb$v1$LoadBalanceRequest$LoadBalanceRequestTypeCase;

        static {
            int[] iArr = new int[LoadBalanceRequestTypeCase.values().length];
            $SwitchMap$io$grpc$lb$v1$LoadBalanceRequest$LoadBalanceRequestTypeCase = iArr;
            try {
                iArr[LoadBalanceRequestTypeCase.INITIAL_REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$lb$v1$LoadBalanceRequest$LoadBalanceRequestTypeCase[LoadBalanceRequestTypeCase.CLIENT_STATS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$grpc$lb$v1$LoadBalanceRequest$LoadBalanceRequestTypeCase[LoadBalanceRequestTypeCase.LOADBALANCEREQUESTTYPE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
