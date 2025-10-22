package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

import com.google.protobuf.AbstractMessageLite;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.DataSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.DataSourceOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class TlsSessionTicketKeys extends GeneratedMessageV3 implements TlsSessionTicketKeysOrBuilder {
    public static final int KEYS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final TlsSessionTicketKeys DEFAULT_INSTANCE = new TlsSessionTicketKeys();
    private static final Parser<TlsSessionTicketKeys> PARSER = new AbstractParser<TlsSessionTicketKeys>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeys.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public TlsSessionTicketKeys m13937parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new TlsSessionTicketKeys(codedInputStream, extensionRegistryLite);
        }
    };
    private List<DataSource> keys_;
    private byte memoizedIsInitialized;

    private TlsSessionTicketKeys(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private TlsSessionTicketKeys() {
        this.memoizedIsInitialized = (byte) -1;
        this.keys_ = Collections.emptyList();
    }

    private TlsSessionTicketKeys(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.keys_ = new ArrayList();
                                z2 |= true;
                            }
                            this.keys_.add(codedInputStream.readMessage(DataSource.parser(), extensionRegistryLite));
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
                if (z2 & true) {
                    this.keys_ = Collections.unmodifiableList(this.keys_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static TlsSessionTicketKeys getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TlsSessionTicketKeys> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return CommonProto.internal_static_envoy_api_v2_auth_TlsSessionTicketKeys_descriptor;
    }

    public static TlsSessionTicketKeys parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (TlsSessionTicketKeys) PARSER.parseFrom(byteBuffer);
    }

    public static TlsSessionTicketKeys parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TlsSessionTicketKeys) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static TlsSessionTicketKeys parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TlsSessionTicketKeys) PARSER.parseFrom(byteString);
    }

    public static TlsSessionTicketKeys parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TlsSessionTicketKeys) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static TlsSessionTicketKeys parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TlsSessionTicketKeys) PARSER.parseFrom(bArr);
    }

    public static TlsSessionTicketKeys parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TlsSessionTicketKeys) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static TlsSessionTicketKeys parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static TlsSessionTicketKeys parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TlsSessionTicketKeys parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static TlsSessionTicketKeys parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TlsSessionTicketKeys parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static TlsSessionTicketKeys parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m13935toBuilder();
    }

    public static Builder newBuilder(TlsSessionTicketKeys tlsSessionTicketKeys) {
        return DEFAULT_INSTANCE.m13935toBuilder().mergeFrom(tlsSessionTicketKeys);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public TlsSessionTicketKeys m13930getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeysOrBuilder
    public List<DataSource> getKeysList() {
        return this.keys_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeysOrBuilder
    public List<? extends DataSourceOrBuilder> getKeysOrBuilderList() {
        return this.keys_;
    }

    public Parser<TlsSessionTicketKeys> getParserForType() {
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
        return new TlsSessionTicketKeys();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return CommonProto.internal_static_envoy_api_v2_auth_TlsSessionTicketKeys_fieldAccessorTable.ensureFieldAccessorsInitialized(TlsSessionTicketKeys.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeysOrBuilder
    public int getKeysCount() {
        return this.keys_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeysOrBuilder
    public DataSource getKeys(int i) {
        return this.keys_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeysOrBuilder
    public DataSourceOrBuilder getKeysOrBuilder(int i) {
        return this.keys_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.keys_.size(); i++) {
            codedOutputStream.writeMessage(1, this.keys_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.keys_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.keys_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TlsSessionTicketKeys)) {
            return super.equals(obj);
        }
        TlsSessionTicketKeys tlsSessionTicketKeys = (TlsSessionTicketKeys) obj;
        return getKeysList().equals(tlsSessionTicketKeys.getKeysList()) && this.unknownFields.equals(tlsSessionTicketKeys.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getKeysCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getKeysList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m13932newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m13935toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TlsSessionTicketKeysOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> keysBuilder_;
        private List<DataSource> keys_;

        private Builder() {
            this.keys_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.keys_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return CommonProto.internal_static_envoy_api_v2_auth_TlsSessionTicketKeys_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return CommonProto.internal_static_envoy_api_v2_auth_TlsSessionTicketKeys_fieldAccessorTable.ensureFieldAccessorsInitialized(TlsSessionTicketKeys.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (TlsSessionTicketKeys.alwaysUseFieldBuilders) {
                getKeysFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13946clear() {
            super.clear();
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.keys_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return CommonProto.internal_static_envoy_api_v2_auth_TlsSessionTicketKeys_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TlsSessionTicketKeys m13959getDefaultInstanceForType() {
            return TlsSessionTicketKeys.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TlsSessionTicketKeys m13940build() throws UninitializedMessageException {
            TlsSessionTicketKeys tlsSessionTicketKeysM13942buildPartial = m13942buildPartial();
            if (tlsSessionTicketKeysM13942buildPartial.isInitialized()) {
                return tlsSessionTicketKeysM13942buildPartial;
            }
            throw newUninitializedMessageException(tlsSessionTicketKeysM13942buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TlsSessionTicketKeys m13942buildPartial() {
            TlsSessionTicketKeys tlsSessionTicketKeys = new TlsSessionTicketKeys(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.keys_ = Collections.unmodifiableList(this.keys_);
                    this.bitField0_ &= -2;
                }
                tlsSessionTicketKeys.keys_ = this.keys_;
            } else {
                tlsSessionTicketKeys.keys_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return tlsSessionTicketKeys;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13958clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13970setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13948clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13951clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13972setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13938addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13963mergeFrom(Message message) {
            if (message instanceof TlsSessionTicketKeys) {
                return mergeFrom((TlsSessionTicketKeys) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(TlsSessionTicketKeys tlsSessionTicketKeys) {
            if (tlsSessionTicketKeys == TlsSessionTicketKeys.getDefaultInstance()) {
                return this;
            }
            if (this.keysBuilder_ == null) {
                if (!tlsSessionTicketKeys.keys_.isEmpty()) {
                    if (this.keys_.isEmpty()) {
                        this.keys_ = tlsSessionTicketKeys.keys_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureKeysIsMutable();
                        this.keys_.addAll(tlsSessionTicketKeys.keys_);
                    }
                    onChanged();
                }
            } else if (!tlsSessionTicketKeys.keys_.isEmpty()) {
                if (!this.keysBuilder_.isEmpty()) {
                    this.keysBuilder_.addAllMessages(tlsSessionTicketKeys.keys_);
                } else {
                    this.keysBuilder_.dispose();
                    this.keysBuilder_ = null;
                    this.keys_ = tlsSessionTicketKeys.keys_;
                    this.bitField0_ &= -2;
                    this.keysBuilder_ = TlsSessionTicketKeys.alwaysUseFieldBuilders ? getKeysFieldBuilder() : null;
                }
            }
            m13968mergeUnknownFields(tlsSessionTicketKeys.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeys.Builder m13964mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeys.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeys r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeys) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeys r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeys) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeys.Builder.m13964mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeys$Builder");
        }

        private void ensureKeysIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.keys_ = new ArrayList(this.keys_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeysOrBuilder
        public List<DataSource> getKeysList() {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.keys_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeysOrBuilder
        public int getKeysCount() {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.keys_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeysOrBuilder
        public DataSource getKeys(int i) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.keys_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setKeys(int i, DataSource dataSource) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                dataSource.getClass();
                ensureKeysIsMutable();
                this.keys_.set(i, dataSource);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, dataSource);
            }
            return this;
        }

        public Builder setKeys(int i, DataSource.Builder builder) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureKeysIsMutable();
                this.keys_.set(i, builder.m14725build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m14725build());
            }
            return this;
        }

        public Builder addKeys(DataSource dataSource) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                dataSource.getClass();
                ensureKeysIsMutable();
                this.keys_.add(dataSource);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(dataSource);
            }
            return this;
        }

        public Builder addKeys(int i, DataSource dataSource) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                dataSource.getClass();
                ensureKeysIsMutable();
                this.keys_.add(i, dataSource);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, dataSource);
            }
            return this;
        }

        public Builder addKeys(DataSource.Builder builder) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureKeysIsMutable();
                this.keys_.add(builder.m14725build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m14725build());
            }
            return this;
        }

        public Builder addKeys(int i, DataSource.Builder builder) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureKeysIsMutable();
                this.keys_.add(i, builder.m14725build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m14725build());
            }
            return this;
        }

        public Builder addAllKeys(Iterable<? extends DataSource> iterable) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureKeysIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.keys_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearKeys() {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.keys_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeKeys(int i) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureKeysIsMutable();
                this.keys_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public DataSource.Builder getKeysBuilder(int i) {
            return getKeysFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeysOrBuilder
        public DataSourceOrBuilder getKeysOrBuilder(int i) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.keys_.get(i);
            }
            return (DataSourceOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsSessionTicketKeysOrBuilder
        public List<? extends DataSourceOrBuilder> getKeysOrBuilderList() {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.keysBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.keys_);
        }

        public DataSource.Builder addKeysBuilder() {
            return getKeysFieldBuilder().addBuilder(DataSource.getDefaultInstance());
        }

        public DataSource.Builder addKeysBuilder(int i) {
            return getKeysFieldBuilder().addBuilder(i, DataSource.getDefaultInstance());
        }

        public List<DataSource.Builder> getKeysBuilderList() {
            return getKeysFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> getKeysFieldBuilder() {
            if (this.keysBuilder_ == null) {
                this.keysBuilder_ = new RepeatedFieldBuilderV3<>(this.keys_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.keys_ = null;
            }
            return this.keysBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m13974setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m13968mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
