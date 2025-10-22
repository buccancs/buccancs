package com.google.rpc;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
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
public final class RetryInfo extends GeneratedMessageV3 implements RetryInfoOrBuilder {
    public static final int RETRY_DELAY_FIELD_NUMBER = 1;
    private static final RetryInfo DEFAULT_INSTANCE = new RetryInfo();
    private static final Parser<RetryInfo> PARSER = new AbstractParser<RetryInfo>() { // from class: com.google.rpc.RetryInfo.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public RetryInfo m3953parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RetryInfo(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private Duration retryDelay_;

    private RetryInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private RetryInfo() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private RetryInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                Duration duration = this.retryDelay_;
                                Duration.Builder builder = duration != null ? duration.toBuilder() : null;
                                Duration message = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                                this.retryDelay_ = message;
                                if (builder != null) {
                                    builder.mergeFrom(message);
                                    this.retryDelay_ = builder.buildPartial();
                                }
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

    public static RetryInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RetryInfo> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_descriptor;
    }

    public static RetryInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RetryInfo) PARSER.parseFrom(byteBuffer);
    }

    public static RetryInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RetryInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RetryInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RetryInfo) PARSER.parseFrom(byteString);
    }

    public static RetryInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RetryInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RetryInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RetryInfo) PARSER.parseFrom(bArr);
    }

    public static RetryInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RetryInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RetryInfo parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RetryInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RetryInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RetryInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RetryInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RetryInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3951toBuilder();
    }

    public static Builder newBuilder(RetryInfo retryInfo) {
        return DEFAULT_INSTANCE.m3951toBuilder().mergeFrom(retryInfo);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public RetryInfo m3946getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<RetryInfo> getParserForType() {
        return PARSER;
    }

    @Override // com.google.rpc.RetryInfoOrBuilder
    public boolean hasRetryDelay() {
        return this.retryDelay_ != null;
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
        return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryInfo.class, Builder.class);
    }

    @Override // com.google.rpc.RetryInfoOrBuilder
    public Duration getRetryDelay() {
        Duration duration = this.retryDelay_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // com.google.rpc.RetryInfoOrBuilder
    public DurationOrBuilder getRetryDelayOrBuilder() {
        return getRetryDelay();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.retryDelay_ != null) {
            codedOutputStream.writeMessage(1, getRetryDelay());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = (this.retryDelay_ != null ? CodedOutputStream.computeMessageSize(1, getRetryDelay()) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = iComputeMessageSize;
        return iComputeMessageSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RetryInfo)) {
            return super.equals(obj);
        }
        RetryInfo retryInfo = (RetryInfo) obj;
        if (hasRetryDelay() != retryInfo.hasRetryDelay()) {
            return false;
        }
        return (!hasRetryDelay() || getRetryDelay().equals(retryInfo.getRetryDelay())) && this.unknownFields.equals(retryInfo.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasRetryDelay()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getRetryDelay().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3948newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3951toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RetryInfoOrBuilder {
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> retryDelayBuilder_;
        private Duration retryDelay_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_descriptor;
        }

        @Override // com.google.rpc.RetryInfoOrBuilder
        public boolean hasRetryDelay() {
            return (this.retryDelayBuilder_ == null && this.retryDelay_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryInfo.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RetryInfo.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3962clear() {
            super.clear();
            if (this.retryDelayBuilder_ == null) {
                this.retryDelay_ = null;
            } else {
                this.retryDelay_ = null;
                this.retryDelayBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RetryInfo m3975getDefaultInstanceForType() {
            return RetryInfo.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RetryInfo m3956build() throws UninitializedMessageException {
            RetryInfo retryInfoM3958buildPartial = m3958buildPartial();
            if (retryInfoM3958buildPartial.isInitialized()) {
                return retryInfoM3958buildPartial;
            }
            throw newUninitializedMessageException(retryInfoM3958buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RetryInfo m3958buildPartial() {
            RetryInfo retryInfo = new RetryInfo(this);
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.retryDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                retryInfo.retryDelay_ = this.retryDelay_;
            } else {
                retryInfo.retryDelay_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return retryInfo;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3974clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3986setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3964clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3967clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3988setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3954addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3979mergeFrom(Message message) {
            if (message instanceof RetryInfo) {
                return mergeFrom((RetryInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RetryInfo retryInfo) {
            if (retryInfo == RetryInfo.getDefaultInstance()) {
                return this;
            }
            if (retryInfo.hasRetryDelay()) {
                mergeRetryDelay(retryInfo.getRetryDelay());
            }
            m3984mergeUnknownFields(retryInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.rpc.RetryInfo.Builder m3980mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.RetryInfo.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.rpc.RetryInfo r3 = (com.google.rpc.RetryInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.rpc.RetryInfo r4 = (com.google.rpc.RetryInfo) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.RetryInfo.Builder.m3980mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.RetryInfo$Builder");
        }

        @Override // com.google.rpc.RetryInfoOrBuilder
        public Duration getRetryDelay() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.retryDelayBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.retryDelay_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setRetryDelay(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.retryDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.retryDelay_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setRetryDelay(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.retryDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.retryDelay_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRetryDelay(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.retryDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.retryDelay_;
                if (duration2 != null) {
                    this.retryDelay_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.retryDelay_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearRetryDelay() {
            if (this.retryDelayBuilder_ == null) {
                this.retryDelay_ = null;
                onChanged();
            } else {
                this.retryDelay_ = null;
                this.retryDelayBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getRetryDelayBuilder() {
            onChanged();
            return getRetryDelayFieldBuilder().getBuilder();
        }

        @Override // com.google.rpc.RetryInfoOrBuilder
        public DurationOrBuilder getRetryDelayOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.retryDelayBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.retryDelay_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getRetryDelayFieldBuilder() {
            if (this.retryDelayBuilder_ == null) {
                this.retryDelayBuilder_ = new SingleFieldBuilderV3<>(getRetryDelay(), getParentForChildren(), isClean());
                this.retryDelay_ = null;
            }
            return this.retryDelayBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3990setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3984mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
