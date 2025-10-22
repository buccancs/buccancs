package io.grpc.alts.internal;

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
import io.grpc.alts.internal.NextHandshakeMessageReq;
import io.grpc.alts.internal.StartClientHandshakeReq;
import io.grpc.alts.internal.StartServerHandshakeReq;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class HandshakerReq extends GeneratedMessageV3 implements HandshakerReqOrBuilder {
    public static final int CLIENT_START_FIELD_NUMBER = 1;
    public static final int NEXT_FIELD_NUMBER = 3;
    public static final int SERVER_START_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final HandshakerReq DEFAULT_INSTANCE = new HandshakerReq();
    private static final Parser<HandshakerReq> PARSER = new AbstractParser<HandshakerReq>() { // from class: io.grpc.alts.internal.HandshakerReq.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public HandshakerReq m6425parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HandshakerReq(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private int reqOneofCase_;
    private Object reqOneof_;

    private HandshakerReq(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.reqOneofCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private HandshakerReq() {
        this.reqOneofCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private HandshakerReq(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                StartClientHandshakeReq.Builder builderM6842toBuilder = this.reqOneofCase_ == 1 ? ((StartClientHandshakeReq) this.reqOneof_).m6842toBuilder() : null;
                                MessageLite message = codedInputStream.readMessage(StartClientHandshakeReq.parser(), extensionRegistryLite);
                                this.reqOneof_ = message;
                                if (builderM6842toBuilder != null) {
                                    builderM6842toBuilder.mergeFrom((StartClientHandshakeReq) message);
                                    this.reqOneof_ = builderM6842toBuilder.m6849buildPartial();
                                }
                                this.reqOneofCase_ = 1;
                            } else if (tag == 18) {
                                StartServerHandshakeReq.Builder builderM6889toBuilder = this.reqOneofCase_ == 2 ? ((StartServerHandshakeReq) this.reqOneof_).m6889toBuilder() : null;
                                MessageLite message2 = codedInputStream.readMessage(StartServerHandshakeReq.parser(), extensionRegistryLite);
                                this.reqOneof_ = message2;
                                if (builderM6889toBuilder != null) {
                                    builderM6889toBuilder.mergeFrom((StartServerHandshakeReq) message2);
                                    this.reqOneof_ = builderM6889toBuilder.m6896buildPartial();
                                }
                                this.reqOneofCase_ = 2;
                            } else if (tag == 26) {
                                NextHandshakeMessageReq.Builder builderM6654toBuilder = this.reqOneofCase_ == 3 ? ((NextHandshakeMessageReq) this.reqOneof_).m6654toBuilder() : null;
                                MessageLite message3 = codedInputStream.readMessage(NextHandshakeMessageReq.parser(), extensionRegistryLite);
                                this.reqOneof_ = message3;
                                if (builderM6654toBuilder != null) {
                                    builderM6654toBuilder.mergeFrom((NextHandshakeMessageReq) message3);
                                    this.reqOneof_ = builderM6654toBuilder.m6661buildPartial();
                                }
                                this.reqOneofCase_ = 3;
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

    public static HandshakerReq getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HandshakerReq> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HandshakerProto.internal_static_grpc_gcp_HandshakerReq_descriptor;
    }

    public static HandshakerReq parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HandshakerReq) PARSER.parseFrom(byteBuffer);
    }

    public static HandshakerReq parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandshakerReq) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HandshakerReq parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HandshakerReq) PARSER.parseFrom(byteString);
    }

    public static HandshakerReq parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandshakerReq) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HandshakerReq parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HandshakerReq) PARSER.parseFrom(bArr);
    }

    public static HandshakerReq parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandshakerReq) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HandshakerReq parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HandshakerReq parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HandshakerReq parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HandshakerReq parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HandshakerReq parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HandshakerReq parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m6423toBuilder();
    }

    public static Builder newBuilder(HandshakerReq handshakerReq) {
        return DEFAULT_INSTANCE.m6423toBuilder().mergeFrom(handshakerReq);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public HandshakerReq m6418getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<HandshakerReq> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public boolean hasClientStart() {
        return this.reqOneofCase_ == 1;
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public boolean hasNext() {
        return this.reqOneofCase_ == 3;
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public boolean hasServerStart() {
        return this.reqOneofCase_ == 2;
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
        return new HandshakerReq();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.internal_static_grpc_gcp_HandshakerReq_fieldAccessorTable.ensureFieldAccessorsInitialized(HandshakerReq.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public ReqOneofCase getReqOneofCase() {
        return ReqOneofCase.forNumber(this.reqOneofCase_);
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public StartClientHandshakeReq getClientStart() {
        if (this.reqOneofCase_ == 1) {
            return (StartClientHandshakeReq) this.reqOneof_;
        }
        return StartClientHandshakeReq.getDefaultInstance();
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public StartClientHandshakeReqOrBuilder getClientStartOrBuilder() {
        if (this.reqOneofCase_ == 1) {
            return (StartClientHandshakeReq) this.reqOneof_;
        }
        return StartClientHandshakeReq.getDefaultInstance();
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public StartServerHandshakeReq getServerStart() {
        if (this.reqOneofCase_ == 2) {
            return (StartServerHandshakeReq) this.reqOneof_;
        }
        return StartServerHandshakeReq.getDefaultInstance();
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public StartServerHandshakeReqOrBuilder getServerStartOrBuilder() {
        if (this.reqOneofCase_ == 2) {
            return (StartServerHandshakeReq) this.reqOneof_;
        }
        return StartServerHandshakeReq.getDefaultInstance();
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public NextHandshakeMessageReq getNext() {
        if (this.reqOneofCase_ == 3) {
            return (NextHandshakeMessageReq) this.reqOneof_;
        }
        return NextHandshakeMessageReq.getDefaultInstance();
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public NextHandshakeMessageReqOrBuilder getNextOrBuilder() {
        if (this.reqOneofCase_ == 3) {
            return (NextHandshakeMessageReq) this.reqOneof_;
        }
        return NextHandshakeMessageReq.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.reqOneofCase_ == 1) {
            codedOutputStream.writeMessage(1, (StartClientHandshakeReq) this.reqOneof_);
        }
        if (this.reqOneofCase_ == 2) {
            codedOutputStream.writeMessage(2, (StartServerHandshakeReq) this.reqOneof_);
        }
        if (this.reqOneofCase_ == 3) {
            codedOutputStream.writeMessage(3, (NextHandshakeMessageReq) this.reqOneof_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.reqOneofCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (StartClientHandshakeReq) this.reqOneof_) : 0;
        if (this.reqOneofCase_ == 2) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, (StartServerHandshakeReq) this.reqOneof_);
        }
        if (this.reqOneofCase_ == 3) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, (NextHandshakeMessageReq) this.reqOneof_);
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HandshakerReq)) {
            return super.equals(obj);
        }
        HandshakerReq handshakerReq = (HandshakerReq) obj;
        if (!getReqOneofCase().equals(handshakerReq.getReqOneofCase())) {
            return false;
        }
        int i = this.reqOneofCase_;
        if (i != 1) {
            if (i == 2) {
                if (!getServerStart().equals(handshakerReq.getServerStart())) {
                    return false;
                }
            } else if (i == 3 && !getNext().equals(handshakerReq.getNext())) {
                return false;
            }
        } else if (!getClientStart().equals(handshakerReq.getClientStart())) {
            return false;
        }
        return this.unknownFields.equals(handshakerReq.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        int i2 = this.reqOneofCase_;
        if (i2 == 1) {
            i = ((iHashCode2 * 37) + 1) * 53;
            iHashCode = getClientStart().hashCode();
        } else if (i2 == 2) {
            i = ((iHashCode2 * 37) + 2) * 53;
            iHashCode = getServerStart().hashCode();
        } else {
            if (i2 == 3) {
                i = ((iHashCode2 * 37) + 3) * 53;
                iHashCode = getNext().hashCode();
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
    public Builder m6420newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m6423toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ReqOneofCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        CLIENT_START(1),
        SERVER_START(2),
        NEXT(3),
        REQONEOF_NOT_SET(0);

        private final int value;

        ReqOneofCase(int i) {
            this.value = i;
        }

        public static ReqOneofCase forNumber(int i) {
            if (i == 0) {
                return REQONEOF_NOT_SET;
            }
            if (i == 1) {
                return CLIENT_START;
            }
            if (i == 2) {
                return SERVER_START;
            }
            if (i != 3) {
                return null;
            }
            return NEXT;
        }

        @Deprecated
        public static ReqOneofCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HandshakerReqOrBuilder {
        private SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> clientStartBuilder_;
        private SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> nextBuilder_;
        private int reqOneofCase_;
        private Object reqOneof_;
        private SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> serverStartBuilder_;

        private Builder() {
            this.reqOneofCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.reqOneofCase_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerReq_descriptor;
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public boolean hasClientStart() {
            return this.reqOneofCase_ == 1;
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public boolean hasNext() {
            return this.reqOneofCase_ == 3;
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public boolean hasServerStart() {
            return this.reqOneofCase_ == 2;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerReq_fieldAccessorTable.ensureFieldAccessorsInitialized(HandshakerReq.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = HandshakerReq.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6434clear() {
            super.clear();
            this.reqOneofCase_ = 0;
            this.reqOneof_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerReq_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HandshakerReq m6447getDefaultInstanceForType() {
            return HandshakerReq.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HandshakerReq m6428build() throws UninitializedMessageException {
            HandshakerReq handshakerReqM6430buildPartial = m6430buildPartial();
            if (handshakerReqM6430buildPartial.isInitialized()) {
                return handshakerReqM6430buildPartial;
            }
            throw newUninitializedMessageException(handshakerReqM6430buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HandshakerReq m6430buildPartial() {
            HandshakerReq handshakerReq = new HandshakerReq(this);
            if (this.reqOneofCase_ == 1) {
                SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> singleFieldBuilderV3 = this.clientStartBuilder_;
                if (singleFieldBuilderV3 == null) {
                    handshakerReq.reqOneof_ = this.reqOneof_;
                } else {
                    handshakerReq.reqOneof_ = singleFieldBuilderV3.build();
                }
            }
            if (this.reqOneofCase_ == 2) {
                SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> singleFieldBuilderV32 = this.serverStartBuilder_;
                if (singleFieldBuilderV32 == null) {
                    handshakerReq.reqOneof_ = this.reqOneof_;
                } else {
                    handshakerReq.reqOneof_ = singleFieldBuilderV32.build();
                }
            }
            if (this.reqOneofCase_ == 3) {
                SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> singleFieldBuilderV33 = this.nextBuilder_;
                if (singleFieldBuilderV33 == null) {
                    handshakerReq.reqOneof_ = this.reqOneof_;
                } else {
                    handshakerReq.reqOneof_ = singleFieldBuilderV33.build();
                }
            }
            handshakerReq.reqOneofCase_ = this.reqOneofCase_;
            onBuilt();
            return handshakerReq;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6446clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6458setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6436clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6439clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6460setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6426addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6451mergeFrom(Message message) {
            if (message instanceof HandshakerReq) {
                return mergeFrom((HandshakerReq) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HandshakerReq handshakerReq) {
            if (handshakerReq == HandshakerReq.getDefaultInstance()) {
                return this;
            }
            int i = AnonymousClass2.$SwitchMap$io$grpc$alts$internal$HandshakerReq$ReqOneofCase[handshakerReq.getReqOneofCase().ordinal()];
            if (i == 1) {
                mergeClientStart(handshakerReq.getClientStart());
            } else if (i == 2) {
                mergeServerStart(handshakerReq.getServerStart());
            } else if (i == 3) {
                mergeNext(handshakerReq.getNext());
            }
            m6456mergeUnknownFields(handshakerReq.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.alts.internal.HandshakerReq.Builder m6452mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.alts.internal.HandshakerReq.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.alts.internal.HandshakerReq r3 = (io.grpc.alts.internal.HandshakerReq) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.alts.internal.HandshakerReq r4 = (io.grpc.alts.internal.HandshakerReq) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.alts.internal.HandshakerReq.Builder.m6452mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.alts.internal.HandshakerReq$Builder");
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public ReqOneofCase getReqOneofCase() {
            return ReqOneofCase.forNumber(this.reqOneofCase_);
        }

        public Builder clearReqOneof() {
            this.reqOneofCase_ = 0;
            this.reqOneof_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public StartClientHandshakeReq getClientStart() {
            SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> singleFieldBuilderV3 = this.clientStartBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.reqOneofCase_ == 1) {
                    return (StartClientHandshakeReq) this.reqOneof_;
                }
                return StartClientHandshakeReq.getDefaultInstance();
            }
            if (this.reqOneofCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            }
            return StartClientHandshakeReq.getDefaultInstance();
        }

        public Builder setClientStart(StartClientHandshakeReq startClientHandshakeReq) {
            SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> singleFieldBuilderV3 = this.clientStartBuilder_;
            if (singleFieldBuilderV3 == null) {
                startClientHandshakeReq.getClass();
                this.reqOneof_ = startClientHandshakeReq;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(startClientHandshakeReq);
            }
            this.reqOneofCase_ = 1;
            return this;
        }

        public Builder setClientStart(StartClientHandshakeReq.Builder builder) {
            SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> singleFieldBuilderV3 = this.clientStartBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.reqOneof_ = builder.m6847build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m6847build());
            }
            this.reqOneofCase_ = 1;
            return this;
        }

        public Builder mergeClientStart(StartClientHandshakeReq startClientHandshakeReq) {
            SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> singleFieldBuilderV3 = this.clientStartBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.reqOneofCase_ != 1 || this.reqOneof_ == StartClientHandshakeReq.getDefaultInstance()) {
                    this.reqOneof_ = startClientHandshakeReq;
                } else {
                    this.reqOneof_ = StartClientHandshakeReq.newBuilder((StartClientHandshakeReq) this.reqOneof_).mergeFrom(startClientHandshakeReq).m6849buildPartial();
                }
                onChanged();
            } else {
                if (this.reqOneofCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(startClientHandshakeReq);
                }
                this.clientStartBuilder_.setMessage(startClientHandshakeReq);
            }
            this.reqOneofCase_ = 1;
            return this;
        }

        public Builder clearClientStart() {
            SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> singleFieldBuilderV3 = this.clientStartBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.reqOneofCase_ == 1) {
                    this.reqOneofCase_ = 0;
                    this.reqOneof_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.reqOneofCase_ == 1) {
                this.reqOneofCase_ = 0;
                this.reqOneof_ = null;
                onChanged();
            }
            return this;
        }

        public StartClientHandshakeReq.Builder getClientStartBuilder() {
            return getClientStartFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public StartClientHandshakeReqOrBuilder getClientStartOrBuilder() {
            SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> singleFieldBuilderV3;
            int i = this.reqOneofCase_;
            if (i == 1 && (singleFieldBuilderV3 = this.clientStartBuilder_) != null) {
                return (StartClientHandshakeReqOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 1) {
                return (StartClientHandshakeReq) this.reqOneof_;
            }
            return StartClientHandshakeReq.getDefaultInstance();
        }

        private SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> getClientStartFieldBuilder() {
            if (this.clientStartBuilder_ == null) {
                if (this.reqOneofCase_ != 1) {
                    this.reqOneof_ = StartClientHandshakeReq.getDefaultInstance();
                }
                this.clientStartBuilder_ = new SingleFieldBuilderV3<>((StartClientHandshakeReq) this.reqOneof_, getParentForChildren(), isClean());
                this.reqOneof_ = null;
            }
            this.reqOneofCase_ = 1;
            onChanged();
            return this.clientStartBuilder_;
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public StartServerHandshakeReq getServerStart() {
            SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> singleFieldBuilderV3 = this.serverStartBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.reqOneofCase_ == 2) {
                    return (StartServerHandshakeReq) this.reqOneof_;
                }
                return StartServerHandshakeReq.getDefaultInstance();
            }
            if (this.reqOneofCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return StartServerHandshakeReq.getDefaultInstance();
        }

        public Builder setServerStart(StartServerHandshakeReq startServerHandshakeReq) {
            SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> singleFieldBuilderV3 = this.serverStartBuilder_;
            if (singleFieldBuilderV3 == null) {
                startServerHandshakeReq.getClass();
                this.reqOneof_ = startServerHandshakeReq;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(startServerHandshakeReq);
            }
            this.reqOneofCase_ = 2;
            return this;
        }

        public Builder setServerStart(StartServerHandshakeReq.Builder builder) {
            SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> singleFieldBuilderV3 = this.serverStartBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.reqOneof_ = builder.m6894build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m6894build());
            }
            this.reqOneofCase_ = 2;
            return this;
        }

        public Builder mergeServerStart(StartServerHandshakeReq startServerHandshakeReq) {
            SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> singleFieldBuilderV3 = this.serverStartBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.reqOneofCase_ != 2 || this.reqOneof_ == StartServerHandshakeReq.getDefaultInstance()) {
                    this.reqOneof_ = startServerHandshakeReq;
                } else {
                    this.reqOneof_ = StartServerHandshakeReq.newBuilder((StartServerHandshakeReq) this.reqOneof_).mergeFrom(startServerHandshakeReq).m6896buildPartial();
                }
                onChanged();
            } else {
                if (this.reqOneofCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(startServerHandshakeReq);
                }
                this.serverStartBuilder_.setMessage(startServerHandshakeReq);
            }
            this.reqOneofCase_ = 2;
            return this;
        }

        public Builder clearServerStart() {
            SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> singleFieldBuilderV3 = this.serverStartBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.reqOneofCase_ == 2) {
                    this.reqOneofCase_ = 0;
                    this.reqOneof_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.reqOneofCase_ == 2) {
                this.reqOneofCase_ = 0;
                this.reqOneof_ = null;
                onChanged();
            }
            return this;
        }

        public StartServerHandshakeReq.Builder getServerStartBuilder() {
            return getServerStartFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public StartServerHandshakeReqOrBuilder getServerStartOrBuilder() {
            SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> singleFieldBuilderV3;
            int i = this.reqOneofCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.serverStartBuilder_) != null) {
                return (StartServerHandshakeReqOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (StartServerHandshakeReq) this.reqOneof_;
            }
            return StartServerHandshakeReq.getDefaultInstance();
        }

        private SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> getServerStartFieldBuilder() {
            if (this.serverStartBuilder_ == null) {
                if (this.reqOneofCase_ != 2) {
                    this.reqOneof_ = StartServerHandshakeReq.getDefaultInstance();
                }
                this.serverStartBuilder_ = new SingleFieldBuilderV3<>((StartServerHandshakeReq) this.reqOneof_, getParentForChildren(), isClean());
                this.reqOneof_ = null;
            }
            this.reqOneofCase_ = 2;
            onChanged();
            return this.serverStartBuilder_;
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public NextHandshakeMessageReq getNext() {
            SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> singleFieldBuilderV3 = this.nextBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.reqOneofCase_ == 3) {
                    return (NextHandshakeMessageReq) this.reqOneof_;
                }
                return NextHandshakeMessageReq.getDefaultInstance();
            }
            if (this.reqOneofCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return NextHandshakeMessageReq.getDefaultInstance();
        }

        public Builder setNext(NextHandshakeMessageReq nextHandshakeMessageReq) {
            SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> singleFieldBuilderV3 = this.nextBuilder_;
            if (singleFieldBuilderV3 == null) {
                nextHandshakeMessageReq.getClass();
                this.reqOneof_ = nextHandshakeMessageReq;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(nextHandshakeMessageReq);
            }
            this.reqOneofCase_ = 3;
            return this;
        }

        public Builder setNext(NextHandshakeMessageReq.Builder builder) {
            SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> singleFieldBuilderV3 = this.nextBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.reqOneof_ = builder.m6659build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m6659build());
            }
            this.reqOneofCase_ = 3;
            return this;
        }

        public Builder mergeNext(NextHandshakeMessageReq nextHandshakeMessageReq) {
            SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> singleFieldBuilderV3 = this.nextBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.reqOneofCase_ != 3 || this.reqOneof_ == NextHandshakeMessageReq.getDefaultInstance()) {
                    this.reqOneof_ = nextHandshakeMessageReq;
                } else {
                    this.reqOneof_ = NextHandshakeMessageReq.newBuilder((NextHandshakeMessageReq) this.reqOneof_).mergeFrom(nextHandshakeMessageReq).m6661buildPartial();
                }
                onChanged();
            } else {
                if (this.reqOneofCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(nextHandshakeMessageReq);
                }
                this.nextBuilder_.setMessage(nextHandshakeMessageReq);
            }
            this.reqOneofCase_ = 3;
            return this;
        }

        public Builder clearNext() {
            SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> singleFieldBuilderV3 = this.nextBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.reqOneofCase_ == 3) {
                    this.reqOneofCase_ = 0;
                    this.reqOneof_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.reqOneofCase_ == 3) {
                this.reqOneofCase_ = 0;
                this.reqOneof_ = null;
                onChanged();
            }
            return this;
        }

        public NextHandshakeMessageReq.Builder getNextBuilder() {
            return getNextFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public NextHandshakeMessageReqOrBuilder getNextOrBuilder() {
            SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> singleFieldBuilderV3;
            int i = this.reqOneofCase_;
            if (i == 3 && (singleFieldBuilderV3 = this.nextBuilder_) != null) {
                return (NextHandshakeMessageReqOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 3) {
                return (NextHandshakeMessageReq) this.reqOneof_;
            }
            return NextHandshakeMessageReq.getDefaultInstance();
        }

        private SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> getNextFieldBuilder() {
            if (this.nextBuilder_ == null) {
                if (this.reqOneofCase_ != 3) {
                    this.reqOneof_ = NextHandshakeMessageReq.getDefaultInstance();
                }
                this.nextBuilder_ = new SingleFieldBuilderV3<>((NextHandshakeMessageReq) this.reqOneof_, getParentForChildren(), isClean());
                this.reqOneof_ = null;
            }
            this.reqOneofCase_ = 3;
            onChanged();
            return this.nextBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m6462setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m6456mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.alts.internal.HandshakerReq$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$alts$internal$HandshakerReq$ReqOneofCase;

        static {
            int[] iArr = new int[ReqOneofCase.values().length];
            $SwitchMap$io$grpc$alts$internal$HandshakerReq$ReqOneofCase = iArr;
            try {
                iArr[ReqOneofCase.CLIENT_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$alts$internal$HandshakerReq$ReqOneofCase[ReqOneofCase.SERVER_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$grpc$alts$internal$HandshakerReq$ReqOneofCase[ReqOneofCase.NEXT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$grpc$alts$internal$HandshakerReq$ReqOneofCase[ReqOneofCase.REQONEOF_NOT_SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
