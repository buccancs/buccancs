package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

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

/* loaded from: classes3.dex */
public final class BackoffStrategy extends GeneratedMessageV3 implements BackoffStrategyOrBuilder {
    public static final int BASE_INTERVAL_FIELD_NUMBER = 1;
    public static final int MAX_INTERVAL_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final BackoffStrategy DEFAULT_INSTANCE = new BackoffStrategy();
    private static final Parser<BackoffStrategy> PARSER = new AbstractParser<BackoffStrategy>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategy.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public BackoffStrategy m14446parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BackoffStrategy(codedInputStream, extensionRegistryLite);
        }
    };
    private Duration baseInterval_;
    private Duration maxInterval_;
    private byte memoizedIsInitialized;

    private BackoffStrategy(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private BackoffStrategy() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private BackoffStrategy(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Duration.Builder builder;
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
                            Duration duration = this.baseInterval_;
                            builder = duration != null ? duration.toBuilder() : null;
                            Duration message = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.baseInterval_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.baseInterval_ = builder.buildPartial();
                            }
                        } else if (tag == 18) {
                            Duration duration2 = this.maxInterval_;
                            builder = duration2 != null ? duration2.toBuilder() : null;
                            Duration message2 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.maxInterval_ = message2;
                            if (builder != null) {
                                builder.mergeFrom(message2);
                                this.maxInterval_ = builder.buildPartial();
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

    public static BackoffStrategy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BackoffStrategy> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BackoffProto.internal_static_envoy_api_v2_core_BackoffStrategy_descriptor;
    }

    public static BackoffStrategy parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BackoffStrategy) PARSER.parseFrom(byteBuffer);
    }

    public static BackoffStrategy parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BackoffStrategy) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BackoffStrategy parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BackoffStrategy) PARSER.parseFrom(byteString);
    }

    public static BackoffStrategy parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BackoffStrategy) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BackoffStrategy parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BackoffStrategy) PARSER.parseFrom(bArr);
    }

    public static BackoffStrategy parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BackoffStrategy) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BackoffStrategy parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BackoffStrategy parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BackoffStrategy parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BackoffStrategy parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BackoffStrategy parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BackoffStrategy parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m14444toBuilder();
    }

    public static Builder newBuilder(BackoffStrategy backoffStrategy) {
        return DEFAULT_INSTANCE.m14444toBuilder().mergeFrom(backoffStrategy);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public BackoffStrategy m14439getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<BackoffStrategy> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategyOrBuilder
    public boolean hasBaseInterval() {
        return this.baseInterval_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategyOrBuilder
    public boolean hasMaxInterval() {
        return this.maxInterval_ != null;
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
        return new BackoffStrategy();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BackoffProto.internal_static_envoy_api_v2_core_BackoffStrategy_fieldAccessorTable.ensureFieldAccessorsInitialized(BackoffStrategy.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategyOrBuilder
    public Duration getBaseInterval() {
        Duration duration = this.baseInterval_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategyOrBuilder
    public DurationOrBuilder getBaseIntervalOrBuilder() {
        return getBaseInterval();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategyOrBuilder
    public Duration getMaxInterval() {
        Duration duration = this.maxInterval_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategyOrBuilder
    public DurationOrBuilder getMaxIntervalOrBuilder() {
        return getMaxInterval();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.baseInterval_ != null) {
            codedOutputStream.writeMessage(1, getBaseInterval());
        }
        if (this.maxInterval_ != null) {
            codedOutputStream.writeMessage(2, getMaxInterval());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.baseInterval_ != null ? CodedOutputStream.computeMessageSize(1, getBaseInterval()) : 0;
        if (this.maxInterval_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getMaxInterval());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BackoffStrategy)) {
            return super.equals(obj);
        }
        BackoffStrategy backoffStrategy = (BackoffStrategy) obj;
        if (hasBaseInterval() != backoffStrategy.hasBaseInterval()) {
            return false;
        }
        if ((!hasBaseInterval() || getBaseInterval().equals(backoffStrategy.getBaseInterval())) && hasMaxInterval() == backoffStrategy.hasMaxInterval()) {
            return (!hasMaxInterval() || getMaxInterval().equals(backoffStrategy.getMaxInterval())) && this.unknownFields.equals(backoffStrategy.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasBaseInterval()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getBaseInterval().hashCode();
        }
        if (hasMaxInterval()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getMaxInterval().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14441newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14444toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BackoffStrategyOrBuilder {
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> baseIntervalBuilder_;
        private Duration baseInterval_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> maxIntervalBuilder_;
        private Duration maxInterval_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BackoffProto.internal_static_envoy_api_v2_core_BackoffStrategy_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategyOrBuilder
        public boolean hasBaseInterval() {
            return (this.baseIntervalBuilder_ == null && this.baseInterval_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategyOrBuilder
        public boolean hasMaxInterval() {
            return (this.maxIntervalBuilder_ == null && this.maxInterval_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BackoffProto.internal_static_envoy_api_v2_core_BackoffStrategy_fieldAccessorTable.ensureFieldAccessorsInitialized(BackoffStrategy.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = BackoffStrategy.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14455clear() {
            super.clear();
            if (this.baseIntervalBuilder_ == null) {
                this.baseInterval_ = null;
            } else {
                this.baseInterval_ = null;
                this.baseIntervalBuilder_ = null;
            }
            if (this.maxIntervalBuilder_ == null) {
                this.maxInterval_ = null;
            } else {
                this.maxInterval_ = null;
                this.maxIntervalBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BackoffProto.internal_static_envoy_api_v2_core_BackoffStrategy_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public BackoffStrategy m14468getDefaultInstanceForType() {
            return BackoffStrategy.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public BackoffStrategy m14449build() throws UninitializedMessageException {
            BackoffStrategy backoffStrategyM14451buildPartial = m14451buildPartial();
            if (backoffStrategyM14451buildPartial.isInitialized()) {
                return backoffStrategyM14451buildPartial;
            }
            throw newUninitializedMessageException(backoffStrategyM14451buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public BackoffStrategy m14451buildPartial() {
            BackoffStrategy backoffStrategy = new BackoffStrategy(this);
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                backoffStrategy.baseInterval_ = this.baseInterval_;
            } else {
                backoffStrategy.baseInterval_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV32 = this.maxIntervalBuilder_;
            if (singleFieldBuilderV32 == null) {
                backoffStrategy.maxInterval_ = this.maxInterval_;
            } else {
                backoffStrategy.maxInterval_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return backoffStrategy;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14467clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14479setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14457clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14460clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14481setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14447addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14472mergeFrom(Message message) {
            if (message instanceof BackoffStrategy) {
                return mergeFrom((BackoffStrategy) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BackoffStrategy backoffStrategy) {
            if (backoffStrategy == BackoffStrategy.getDefaultInstance()) {
                return this;
            }
            if (backoffStrategy.hasBaseInterval()) {
                mergeBaseInterval(backoffStrategy.getBaseInterval());
            }
            if (backoffStrategy.hasMaxInterval()) {
                mergeMaxInterval(backoffStrategy.getMaxInterval());
            }
            m14477mergeUnknownFields(backoffStrategy.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategy.Builder m14473mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategy.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategy r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategy) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategy r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategy) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategy.Builder.m14473mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategy$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategyOrBuilder
        public Duration getBaseInterval() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseIntervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.baseInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setBaseInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.baseInterval_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setBaseInterval(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.baseInterval_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeBaseInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.baseInterval_;
                if (duration2 != null) {
                    this.baseInterval_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.baseInterval_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearBaseInterval() {
            if (this.baseIntervalBuilder_ == null) {
                this.baseInterval_ = null;
                onChanged();
            } else {
                this.baseInterval_ = null;
                this.baseIntervalBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getBaseIntervalBuilder() {
            onChanged();
            return getBaseIntervalFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategyOrBuilder
        public DurationOrBuilder getBaseIntervalOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.baseIntervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.baseInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getBaseIntervalFieldBuilder() {
            if (this.baseIntervalBuilder_ == null) {
                this.baseIntervalBuilder_ = new SingleFieldBuilderV3<>(getBaseInterval(), getParentForChildren(), isClean());
                this.baseInterval_ = null;
            }
            return this.baseIntervalBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategyOrBuilder
        public Duration getMaxInterval() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.maxIntervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.maxInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setMaxInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.maxIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.maxInterval_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setMaxInterval(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.maxIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.maxInterval_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeMaxInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.maxIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.maxInterval_;
                if (duration2 != null) {
                    this.maxInterval_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.maxInterval_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearMaxInterval() {
            if (this.maxIntervalBuilder_ == null) {
                this.maxInterval_ = null;
                onChanged();
            } else {
                this.maxInterval_ = null;
                this.maxIntervalBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getMaxIntervalBuilder() {
            onChanged();
            return getMaxIntervalFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BackoffStrategyOrBuilder
        public DurationOrBuilder getMaxIntervalOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.maxIntervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.maxInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getMaxIntervalFieldBuilder() {
            if (this.maxIntervalBuilder_ == null) {
                this.maxIntervalBuilder_ = new SingleFieldBuilderV3<>(getMaxInterval(), getParentForChildren(), isClean());
                this.maxInterval_ = null;
            }
            return this.maxIntervalBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14483setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14477mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
