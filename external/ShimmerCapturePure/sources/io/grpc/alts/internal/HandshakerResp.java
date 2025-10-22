package io.grpc.alts.internal;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.alts.internal.HandshakerResult;
import io.grpc.alts.internal.HandshakerStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class HandshakerResp extends GeneratedMessageV3 implements HandshakerRespOrBuilder {
    public static final int BYTES_CONSUMED_FIELD_NUMBER = 2;
    public static final int OUT_FRAMES_FIELD_NUMBER = 1;
    public static final int RESULT_FIELD_NUMBER = 3;
    public static final int STATUS_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private static final HandshakerResp DEFAULT_INSTANCE = new HandshakerResp();
    private static final Parser<HandshakerResp> PARSER = new AbstractParser<HandshakerResp>() { // from class: io.grpc.alts.internal.HandshakerResp.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public HandshakerResp m6471parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HandshakerResp(codedInputStream, extensionRegistryLite);
        }
    };
    private int bytesConsumed_;
    private byte memoizedIsInitialized;
    private ByteString outFrames_;
    private HandshakerResult result_;
    private HandshakerStatus status_;

    private HandshakerResp(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private HandshakerResp() {
        this.memoizedIsInitialized = (byte) -1;
        this.outFrames_ = ByteString.EMPTY;
    }

    private HandshakerResp(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.outFrames_ = codedInputStream.readBytes();
                            } else if (tag != 16) {
                                if (tag == 26) {
                                    HandshakerResult handshakerResult = this.result_;
                                    HandshakerResult.Builder builderM6515toBuilder = handshakerResult != null ? handshakerResult.m6515toBuilder() : null;
                                    HandshakerResult handshakerResult2 = (HandshakerResult) codedInputStream.readMessage(HandshakerResult.parser(), extensionRegistryLite);
                                    this.result_ = handshakerResult2;
                                    if (builderM6515toBuilder != null) {
                                        builderM6515toBuilder.mergeFrom(handshakerResult2);
                                        this.result_ = builderM6515toBuilder.m6522buildPartial();
                                    }
                                } else if (tag == 34) {
                                    HandshakerStatus handshakerStatus = this.status_;
                                    HandshakerStatus.Builder builderM6561toBuilder = handshakerStatus != null ? handshakerStatus.m6561toBuilder() : null;
                                    HandshakerStatus handshakerStatus2 = (HandshakerStatus) codedInputStream.readMessage(HandshakerStatus.parser(), extensionRegistryLite);
                                    this.status_ = handshakerStatus2;
                                    if (builderM6561toBuilder != null) {
                                        builderM6561toBuilder.mergeFrom(handshakerStatus2);
                                        this.status_ = builderM6561toBuilder.m6568buildPartial();
                                    }
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                this.bytesConsumed_ = codedInputStream.readUInt32();
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
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static HandshakerResp getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HandshakerResp> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HandshakerProto.internal_static_grpc_gcp_HandshakerResp_descriptor;
    }

    public static HandshakerResp parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HandshakerResp) PARSER.parseFrom(byteBuffer);
    }

    public static HandshakerResp parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandshakerResp) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HandshakerResp parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HandshakerResp) PARSER.parseFrom(byteString);
    }

    public static HandshakerResp parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandshakerResp) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HandshakerResp parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HandshakerResp) PARSER.parseFrom(bArr);
    }

    public static HandshakerResp parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandshakerResp) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HandshakerResp parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HandshakerResp parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HandshakerResp parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HandshakerResp parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HandshakerResp parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HandshakerResp parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m6469toBuilder();
    }

    public static Builder newBuilder(HandshakerResp handshakerResp) {
        return DEFAULT_INSTANCE.m6469toBuilder().mergeFrom(handshakerResp);
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public int getBytesConsumed() {
        return this.bytesConsumed_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public HandshakerResp m6464getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public ByteString getOutFrames() {
        return this.outFrames_;
    }

    public Parser<HandshakerResp> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public boolean hasResult() {
        return this.result_ != null;
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public boolean hasStatus() {
        return this.status_ != null;
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
        return new HandshakerResp();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.internal_static_grpc_gcp_HandshakerResp_fieldAccessorTable.ensureFieldAccessorsInitialized(HandshakerResp.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public HandshakerResult getResult() {
        HandshakerResult handshakerResult = this.result_;
        return handshakerResult == null ? HandshakerResult.getDefaultInstance() : handshakerResult;
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public HandshakerResultOrBuilder getResultOrBuilder() {
        return getResult();
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public HandshakerStatus getStatus() {
        HandshakerStatus handshakerStatus = this.status_;
        return handshakerStatus == null ? HandshakerStatus.getDefaultInstance() : handshakerStatus;
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public HandshakerStatusOrBuilder getStatusOrBuilder() {
        return getStatus();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.outFrames_.isEmpty()) {
            codedOutputStream.writeBytes(1, this.outFrames_);
        }
        int i = this.bytesConsumed_;
        if (i != 0) {
            codedOutputStream.writeUInt32(2, i);
        }
        if (this.result_ != null) {
            codedOutputStream.writeMessage(3, getResult());
        }
        if (this.status_ != null) {
            codedOutputStream.writeMessage(4, getStatus());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeBytesSize = !this.outFrames_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.outFrames_) : 0;
        int i2 = this.bytesConsumed_;
        if (i2 != 0) {
            iComputeBytesSize += CodedOutputStream.computeUInt32Size(2, i2);
        }
        if (this.result_ != null) {
            iComputeBytesSize += CodedOutputStream.computeMessageSize(3, getResult());
        }
        if (this.status_ != null) {
            iComputeBytesSize += CodedOutputStream.computeMessageSize(4, getStatus());
        }
        int serializedSize = iComputeBytesSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HandshakerResp)) {
            return super.equals(obj);
        }
        HandshakerResp handshakerResp = (HandshakerResp) obj;
        if (!getOutFrames().equals(handshakerResp.getOutFrames()) || getBytesConsumed() != handshakerResp.getBytesConsumed() || hasResult() != handshakerResp.hasResult()) {
            return false;
        }
        if ((!hasResult() || getResult().equals(handshakerResp.getResult())) && hasStatus() == handshakerResp.hasStatus()) {
            return (!hasStatus() || getStatus().equals(handshakerResp.getStatus())) && this.unknownFields.equals(handshakerResp.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOutFrames().hashCode()) * 37) + 2) * 53) + getBytesConsumed();
        if (hasResult()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getResult().hashCode();
        }
        if (hasStatus()) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getStatus().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m6466newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m6469toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HandshakerRespOrBuilder {
        private int bytesConsumed_;
        private ByteString outFrames_;
        private SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> resultBuilder_;
        private HandshakerResult result_;
        private SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> statusBuilder_;
        private HandshakerStatus status_;

        private Builder() {
            this.outFrames_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.outFrames_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerResp_descriptor;
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public int getBytesConsumed() {
            return this.bytesConsumed_;
        }

        public Builder setBytesConsumed(int i) {
            this.bytesConsumed_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public ByteString getOutFrames() {
            return this.outFrames_;
        }

        public Builder setOutFrames(ByteString byteString) {
            byteString.getClass();
            this.outFrames_ = byteString;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public boolean hasResult() {
            return (this.resultBuilder_ == null && this.result_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public boolean hasStatus() {
            return (this.statusBuilder_ == null && this.status_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerResp_fieldAccessorTable.ensureFieldAccessorsInitialized(HandshakerResp.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = HandshakerResp.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6480clear() {
            super.clear();
            this.outFrames_ = ByteString.EMPTY;
            this.bytesConsumed_ = 0;
            if (this.resultBuilder_ == null) {
                this.result_ = null;
            } else {
                this.result_ = null;
                this.resultBuilder_ = null;
            }
            if (this.statusBuilder_ == null) {
                this.status_ = null;
            } else {
                this.status_ = null;
                this.statusBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerResp_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HandshakerResp m6493getDefaultInstanceForType() {
            return HandshakerResp.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HandshakerResp m6474build() throws UninitializedMessageException {
            HandshakerResp handshakerRespM6476buildPartial = m6476buildPartial();
            if (handshakerRespM6476buildPartial.isInitialized()) {
                return handshakerRespM6476buildPartial;
            }
            throw newUninitializedMessageException(handshakerRespM6476buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HandshakerResp m6476buildPartial() {
            HandshakerResp handshakerResp = new HandshakerResp(this);
            handshakerResp.outFrames_ = this.outFrames_;
            handshakerResp.bytesConsumed_ = this.bytesConsumed_;
            SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
            if (singleFieldBuilderV3 == null) {
                handshakerResp.result_ = this.result_;
            } else {
                handshakerResp.result_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> singleFieldBuilderV32 = this.statusBuilder_;
            if (singleFieldBuilderV32 == null) {
                handshakerResp.status_ = this.status_;
            } else {
                handshakerResp.status_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return handshakerResp;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6492clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6504setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6482clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6485clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6506setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6472addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6497mergeFrom(Message message) {
            if (message instanceof HandshakerResp) {
                return mergeFrom((HandshakerResp) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HandshakerResp handshakerResp) {
            if (handshakerResp == HandshakerResp.getDefaultInstance()) {
                return this;
            }
            if (handshakerResp.getOutFrames() != ByteString.EMPTY) {
                setOutFrames(handshakerResp.getOutFrames());
            }
            if (handshakerResp.getBytesConsumed() != 0) {
                setBytesConsumed(handshakerResp.getBytesConsumed());
            }
            if (handshakerResp.hasResult()) {
                mergeResult(handshakerResp.getResult());
            }
            if (handshakerResp.hasStatus()) {
                mergeStatus(handshakerResp.getStatus());
            }
            m6502mergeUnknownFields(handshakerResp.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.alts.internal.HandshakerResp.Builder m6498mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.alts.internal.HandshakerResp.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.alts.internal.HandshakerResp r3 = (io.grpc.alts.internal.HandshakerResp) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.alts.internal.HandshakerResp r4 = (io.grpc.alts.internal.HandshakerResp) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.alts.internal.HandshakerResp.Builder.m6498mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.alts.internal.HandshakerResp$Builder");
        }

        public Builder clearOutFrames() {
            this.outFrames_ = HandshakerResp.getDefaultInstance().getOutFrames();
            onChanged();
            return this;
        }

        public Builder clearBytesConsumed() {
            this.bytesConsumed_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public HandshakerResult getResult() {
            SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            HandshakerResult handshakerResult = this.result_;
            return handshakerResult == null ? HandshakerResult.getDefaultInstance() : handshakerResult;
        }

        public Builder setResult(HandshakerResult handshakerResult) {
            SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
            if (singleFieldBuilderV3 == null) {
                handshakerResult.getClass();
                this.result_ = handshakerResult;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(handshakerResult);
            }
            return this;
        }

        public Builder setResult(HandshakerResult.Builder builder) {
            SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.result_ = builder.m6520build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m6520build());
            }
            return this;
        }

        public Builder mergeResult(HandshakerResult handshakerResult) {
            SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
            if (singleFieldBuilderV3 == null) {
                HandshakerResult handshakerResult2 = this.result_;
                if (handshakerResult2 != null) {
                    this.result_ = HandshakerResult.newBuilder(handshakerResult2).mergeFrom(handshakerResult).m6522buildPartial();
                } else {
                    this.result_ = handshakerResult;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(handshakerResult);
            }
            return this;
        }

        public Builder clearResult() {
            if (this.resultBuilder_ == null) {
                this.result_ = null;
                onChanged();
            } else {
                this.result_ = null;
                this.resultBuilder_ = null;
            }
            return this;
        }

        public HandshakerResult.Builder getResultBuilder() {
            onChanged();
            return getResultFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public HandshakerResultOrBuilder getResultOrBuilder() {
            SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (HandshakerResultOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            HandshakerResult handshakerResult = this.result_;
            return handshakerResult == null ? HandshakerResult.getDefaultInstance() : handshakerResult;
        }

        private SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> getResultFieldBuilder() {
            if (this.resultBuilder_ == null) {
                this.resultBuilder_ = new SingleFieldBuilderV3<>(getResult(), getParentForChildren(), isClean());
                this.result_ = null;
            }
            return this.resultBuilder_;
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public HandshakerStatus getStatus() {
            SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            HandshakerStatus handshakerStatus = this.status_;
            return handshakerStatus == null ? HandshakerStatus.getDefaultInstance() : handshakerStatus;
        }

        public Builder setStatus(HandshakerStatus handshakerStatus) {
            SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                handshakerStatus.getClass();
                this.status_ = handshakerStatus;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(handshakerStatus);
            }
            return this;
        }

        public Builder setStatus(HandshakerStatus.Builder builder) {
            SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.status_ = builder.m6566build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m6566build());
            }
            return this;
        }

        public Builder mergeStatus(HandshakerStatus handshakerStatus) {
            SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                HandshakerStatus handshakerStatus2 = this.status_;
                if (handshakerStatus2 != null) {
                    this.status_ = HandshakerStatus.newBuilder(handshakerStatus2).mergeFrom(handshakerStatus).m6568buildPartial();
                } else {
                    this.status_ = handshakerStatus;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(handshakerStatus);
            }
            return this;
        }

        public Builder clearStatus() {
            if (this.statusBuilder_ == null) {
                this.status_ = null;
                onChanged();
            } else {
                this.status_ = null;
                this.statusBuilder_ = null;
            }
            return this;
        }

        public HandshakerStatus.Builder getStatusBuilder() {
            onChanged();
            return getStatusFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public HandshakerStatusOrBuilder getStatusOrBuilder() {
            SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (HandshakerStatusOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            HandshakerStatus handshakerStatus = this.status_;
            return handshakerStatus == null ? HandshakerStatus.getDefaultInstance() : handshakerStatus;
        }

        private SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> getStatusFieldBuilder() {
            if (this.statusBuilder_ == null) {
                this.statusBuilder_ = new SingleFieldBuilderV3<>(getStatus(), getParentForChildren(), isClean());
                this.status_ = null;
            }
            return this.statusBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m6508setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m6502mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
