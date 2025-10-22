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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class HandshakerStatus extends GeneratedMessageV3 implements HandshakerStatusOrBuilder {
    public static final int CODE_FIELD_NUMBER = 1;
    public static final int DETAILS_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final HandshakerStatus DEFAULT_INSTANCE = new HandshakerStatus();
    private static final Parser<HandshakerStatus> PARSER = new AbstractParser<HandshakerStatus>() { // from class: io.grpc.alts.internal.HandshakerStatus.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public HandshakerStatus m6563parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HandshakerStatus(codedInputStream, extensionRegistryLite);
        }
    };
    private int code_;
    private volatile Object details_;
    private byte memoizedIsInitialized;

    private HandshakerStatus(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private HandshakerStatus() {
        this.memoizedIsInitialized = (byte) -1;
        this.details_ = "";
    }

    private HandshakerStatus(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.code_ = codedInputStream.readUInt32();
                            } else if (tag == 18) {
                                this.details_ = codedInputStream.readStringRequireUtf8();
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

    public static HandshakerStatus getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HandshakerStatus> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HandshakerProto.internal_static_grpc_gcp_HandshakerStatus_descriptor;
    }

    public static HandshakerStatus parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HandshakerStatus) PARSER.parseFrom(byteBuffer);
    }

    public static HandshakerStatus parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandshakerStatus) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HandshakerStatus parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HandshakerStatus) PARSER.parseFrom(byteString);
    }

    public static HandshakerStatus parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandshakerStatus) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HandshakerStatus parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HandshakerStatus) PARSER.parseFrom(bArr);
    }

    public static HandshakerStatus parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandshakerStatus) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HandshakerStatus parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HandshakerStatus parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HandshakerStatus parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HandshakerStatus parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HandshakerStatus parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HandshakerStatus parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m6561toBuilder();
    }

    public static Builder newBuilder(HandshakerStatus handshakerStatus) {
        return DEFAULT_INSTANCE.m6561toBuilder().mergeFrom(handshakerStatus);
    }

    @Override // io.grpc.alts.internal.HandshakerStatusOrBuilder
    public int getCode() {
        return this.code_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public HandshakerStatus m6556getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<HandshakerStatus> getParserForType() {
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
        return new HandshakerStatus();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.internal_static_grpc_gcp_HandshakerStatus_fieldAccessorTable.ensureFieldAccessorsInitialized(HandshakerStatus.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.HandshakerStatusOrBuilder
    public String getDetails() {
        Object obj = this.details_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.details_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.HandshakerStatusOrBuilder
    public ByteString getDetailsBytes() {
        Object obj = this.details_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.details_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i = this.code_;
        if (i != 0) {
            codedOutputStream.writeUInt32(1, i);
        }
        if (!getDetailsBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.details_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = this.code_;
        int iComputeUInt32Size = i2 != 0 ? CodedOutputStream.computeUInt32Size(1, i2) : 0;
        if (!getDetailsBytes().isEmpty()) {
            iComputeUInt32Size += GeneratedMessageV3.computeStringSize(2, this.details_);
        }
        int serializedSize = iComputeUInt32Size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HandshakerStatus)) {
            return super.equals(obj);
        }
        HandshakerStatus handshakerStatus = (HandshakerStatus) obj;
        return getCode() == handshakerStatus.getCode() && getDetails().equals(handshakerStatus.getDetails()) && this.unknownFields.equals(handshakerStatus.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCode()) * 37) + 2) * 53) + getDetails().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m6558newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m6561toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HandshakerStatusOrBuilder {
        private int code_;
        private Object details_;

        private Builder() {
            this.details_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.details_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerStatus_descriptor;
        }

        @Override // io.grpc.alts.internal.HandshakerStatusOrBuilder
        public int getCode() {
            return this.code_;
        }

        public Builder setCode(int i) {
            this.code_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerStatus_fieldAccessorTable.ensureFieldAccessorsInitialized(HandshakerStatus.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = HandshakerStatus.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6572clear() {
            super.clear();
            this.code_ = 0;
            this.details_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerStatus_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HandshakerStatus m6585getDefaultInstanceForType() {
            return HandshakerStatus.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HandshakerStatus m6566build() throws UninitializedMessageException {
            HandshakerStatus handshakerStatusM6568buildPartial = m6568buildPartial();
            if (handshakerStatusM6568buildPartial.isInitialized()) {
                return handshakerStatusM6568buildPartial;
            }
            throw newUninitializedMessageException(handshakerStatusM6568buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HandshakerStatus m6568buildPartial() {
            HandshakerStatus handshakerStatus = new HandshakerStatus(this);
            handshakerStatus.code_ = this.code_;
            handshakerStatus.details_ = this.details_;
            onBuilt();
            return handshakerStatus;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6584clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6596setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6574clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6577clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6598setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6564addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6589mergeFrom(Message message) {
            if (message instanceof HandshakerStatus) {
                return mergeFrom((HandshakerStatus) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HandshakerStatus handshakerStatus) {
            if (handshakerStatus == HandshakerStatus.getDefaultInstance()) {
                return this;
            }
            if (handshakerStatus.getCode() != 0) {
                setCode(handshakerStatus.getCode());
            }
            if (!handshakerStatus.getDetails().isEmpty()) {
                this.details_ = handshakerStatus.details_;
                onChanged();
            }
            m6594mergeUnknownFields(handshakerStatus.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.alts.internal.HandshakerStatus.Builder m6590mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.alts.internal.HandshakerStatus.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.alts.internal.HandshakerStatus r3 = (io.grpc.alts.internal.HandshakerStatus) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.alts.internal.HandshakerStatus r4 = (io.grpc.alts.internal.HandshakerStatus) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.alts.internal.HandshakerStatus.Builder.m6590mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.alts.internal.HandshakerStatus$Builder");
        }

        public Builder clearCode() {
            this.code_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.HandshakerStatusOrBuilder
        public String getDetails() {
            Object obj = this.details_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.details_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setDetails(String str) {
            str.getClass();
            this.details_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.HandshakerStatusOrBuilder
        public ByteString getDetailsBytes() {
            Object obj = this.details_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.details_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDetailsBytes(ByteString byteString) {
            byteString.getClass();
            HandshakerStatus.checkByteStringIsUtf8(byteString);
            this.details_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearDetails() {
            this.details_ = HandshakerStatus.getDefaultInstance().getDetails();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m6600setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m6594mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
