package io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogFilter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class AccessLog extends GeneratedMessageV3 implements AccessLogOrBuilder {
    public static final int FILTER_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int TYPED_CONFIG_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private static final AccessLog DEFAULT_INSTANCE = new AccessLog();
    private static final Parser<AccessLog> PARSER = new AbstractParser<AccessLog>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLog.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public AccessLog m19624parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AccessLog(codedInputStream, extensionRegistryLite);
        }
    };
    private int configTypeCase_;
    private Object configType_;
    private AccessLogFilter filter_;
    private byte memoizedIsInitialized;
    private volatile Object name_;

    private AccessLog(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.configTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private AccessLog() {
        this.configTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    private AccessLog(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        AccessLogFilter.Builder builder;
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag != 10) {
                            if (tag == 18) {
                                AccessLogFilter accessLogFilter = this.filter_;
                                builder = accessLogFilter != null ? accessLogFilter.m19668toBuilder() : null;
                                AccessLogFilter accessLogFilter2 = (AccessLogFilter) codedInputStream.readMessage(AccessLogFilter.parser(), extensionRegistryLite);
                                this.filter_ = accessLogFilter2;
                                if (builder != null) {
                                    builder.mergeFrom(accessLogFilter2);
                                    this.filter_ = builder.m19675buildPartial();
                                }
                            } else if (tag == 34) {
                                builder = this.configTypeCase_ == 4 ? ((Any) this.configType_).toBuilder() : null;
                                Any message = codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                                this.configType_ = message;
                                if (builder != null) {
                                    builder.mergeFrom(message);
                                    this.configType_ = builder.buildPartial();
                                }
                                this.configTypeCase_ = 4;
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static AccessLog getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AccessLog> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AccesslogProto.internal_static_envoy_config_accesslog_v3_AccessLog_descriptor;
    }

    public static AccessLog parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AccessLog) PARSER.parseFrom(byteBuffer);
    }

    public static AccessLog parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AccessLog) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AccessLog parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AccessLog) PARSER.parseFrom(byteString);
    }

    public static AccessLog parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AccessLog) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AccessLog parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AccessLog) PARSER.parseFrom(bArr);
    }

    public static AccessLog parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AccessLog) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AccessLog parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AccessLog parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AccessLog parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AccessLog parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AccessLog parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AccessLog parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m19622toBuilder();
    }

    public static Builder newBuilder(AccessLog accessLog) {
        return DEFAULT_INSTANCE.m19622toBuilder().mergeFrom(accessLog);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public AccessLog m19617getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<AccessLog> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
    public boolean hasFilter() {
        return this.filter_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
    public boolean hasTypedConfig() {
        return this.configTypeCase_ == 4;
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
        return new AccessLog();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AccesslogProto.internal_static_envoy_config_accesslog_v3_AccessLog_fieldAccessorTable.ensureFieldAccessorsInitialized(AccessLog.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
    public ConfigTypeCase getConfigTypeCase() {
        return ConfigTypeCase.forNumber(this.configTypeCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
    public AccessLogFilter getFilter() {
        AccessLogFilter accessLogFilter = this.filter_;
        return accessLogFilter == null ? AccessLogFilter.getDefaultInstance() : accessLogFilter;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
    public AccessLogFilterOrBuilder getFilterOrBuilder() {
        return getFilter();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
    public Any getTypedConfig() {
        if (this.configTypeCase_ == 4) {
            return (Any) this.configType_;
        }
        return Any.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
    public AnyOrBuilder getTypedConfigOrBuilder() {
        if (this.configTypeCase_ == 4) {
            return (Any) this.configType_;
        }
        return Any.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.filter_ != null) {
            codedOutputStream.writeMessage(2, getFilter());
        }
        if (this.configTypeCase_ == 4) {
            codedOutputStream.writeMessage(4, (Any) this.configType_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (this.filter_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, getFilter());
        }
        if (this.configTypeCase_ == 4) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(4, (Any) this.configType_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AccessLog)) {
            return super.equals(obj);
        }
        AccessLog accessLog = (AccessLog) obj;
        if (!getName().equals(accessLog.getName()) || hasFilter() != accessLog.hasFilter()) {
            return false;
        }
        if ((!hasFilter() || getFilter().equals(accessLog.getFilter())) && getConfigTypeCase().equals(accessLog.getConfigTypeCase())) {
            return (this.configTypeCase_ != 4 || getTypedConfig().equals(accessLog.getTypedConfig())) && this.unknownFields.equals(accessLog.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (hasFilter()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getFilter().hashCode();
        }
        if (this.configTypeCase_ == 4) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getTypedConfig().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m19619newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m19622toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ConfigTypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        TYPED_CONFIG(4),
        CONFIGTYPE_NOT_SET(0);

        private final int value;

        ConfigTypeCase(int i) {
            this.value = i;
        }

        public static ConfigTypeCase forNumber(int i) {
            if (i == 0) {
                return CONFIGTYPE_NOT_SET;
            }
            if (i != 4) {
                return null;
            }
            return TYPED_CONFIG;
        }

        @Deprecated
        public static ConfigTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AccessLogOrBuilder {
        private int configTypeCase_;
        private Object configType_;
        private SingleFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> filterBuilder_;
        private AccessLogFilter filter_;
        private Object name_;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> typedConfigBuilder_;

        private Builder() {
            this.configTypeCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.configTypeCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AccesslogProto.internal_static_envoy_config_accesslog_v3_AccessLog_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
        public boolean hasFilter() {
            return (this.filterBuilder_ == null && this.filter_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
        public boolean hasTypedConfig() {
            return this.configTypeCase_ == 4;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AccesslogProto.internal_static_envoy_config_accesslog_v3_AccessLog_fieldAccessorTable.ensureFieldAccessorsInitialized(AccessLog.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = AccessLog.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19633clear() {
            super.clear();
            this.name_ = "";
            if (this.filterBuilder_ == null) {
                this.filter_ = null;
            } else {
                this.filter_ = null;
                this.filterBuilder_ = null;
            }
            this.configTypeCase_ = 0;
            this.configType_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return AccesslogProto.internal_static_envoy_config_accesslog_v3_AccessLog_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AccessLog m19646getDefaultInstanceForType() {
            return AccessLog.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AccessLog m19627build() throws UninitializedMessageException {
            AccessLog accessLogM19629buildPartial = m19629buildPartial();
            if (accessLogM19629buildPartial.isInitialized()) {
                return accessLogM19629buildPartial;
            }
            throw newUninitializedMessageException(accessLogM19629buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public AccessLog m19629buildPartial() {
            AccessLog accessLog = new AccessLog(this);
            accessLog.name_ = this.name_;
            SingleFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> singleFieldBuilderV3 = this.filterBuilder_;
            if (singleFieldBuilderV3 == null) {
                accessLog.filter_ = this.filter_;
            } else {
                accessLog.filter_ = singleFieldBuilderV3.build();
            }
            if (this.configTypeCase_ == 4) {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV32 = this.typedConfigBuilder_;
                if (singleFieldBuilderV32 == null) {
                    accessLog.configType_ = this.configType_;
                } else {
                    accessLog.configType_ = singleFieldBuilderV32.build();
                }
            }
            accessLog.configTypeCase_ = this.configTypeCase_;
            onBuilt();
            return accessLog;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19645clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19657setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19635clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19638clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19659setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19625addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m19650mergeFrom(Message message) {
            if (message instanceof AccessLog) {
                return mergeFrom((AccessLog) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AccessLog accessLog) {
            if (accessLog == AccessLog.getDefaultInstance()) {
                return this;
            }
            if (!accessLog.getName().isEmpty()) {
                this.name_ = accessLog.name_;
                onChanged();
            }
            if (accessLog.hasFilter()) {
                mergeFilter(accessLog.getFilter());
            }
            if (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$accesslog$v3$AccessLog$ConfigTypeCase[accessLog.getConfigTypeCase().ordinal()] == 1) {
                mergeTypedConfig(accessLog.getTypedConfig());
            }
            m19655mergeUnknownFields(accessLog.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLog.Builder m19651mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLog.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLog r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLog) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLog r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLog) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLog.Builder.m19651mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLog$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
        public ConfigTypeCase getConfigTypeCase() {
            return ConfigTypeCase.forNumber(this.configTypeCase_);
        }

        public Builder clearConfigType() {
            this.configTypeCase_ = 0;
            this.configType_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setName(String str) {
            str.getClass();
            this.name_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNameBytes(ByteString byteString) {
            byteString.getClass();
            AccessLog.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = AccessLog.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
        public AccessLogFilter getFilter() {
            SingleFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> singleFieldBuilderV3 = this.filterBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            AccessLogFilter accessLogFilter = this.filter_;
            return accessLogFilter == null ? AccessLogFilter.getDefaultInstance() : accessLogFilter;
        }

        public Builder setFilter(AccessLogFilter accessLogFilter) {
            SingleFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> singleFieldBuilderV3 = this.filterBuilder_;
            if (singleFieldBuilderV3 == null) {
                accessLogFilter.getClass();
                this.filter_ = accessLogFilter;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(accessLogFilter);
            }
            return this;
        }

        public Builder setFilter(AccessLogFilter.Builder builder) {
            SingleFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> singleFieldBuilderV3 = this.filterBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.filter_ = builder.m19673build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m19673build());
            }
            return this;
        }

        public Builder mergeFilter(AccessLogFilter accessLogFilter) {
            SingleFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> singleFieldBuilderV3 = this.filterBuilder_;
            if (singleFieldBuilderV3 == null) {
                AccessLogFilter accessLogFilter2 = this.filter_;
                if (accessLogFilter2 != null) {
                    this.filter_ = AccessLogFilter.newBuilder(accessLogFilter2).mergeFrom(accessLogFilter).m19675buildPartial();
                } else {
                    this.filter_ = accessLogFilter;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(accessLogFilter);
            }
            return this;
        }

        public Builder clearFilter() {
            if (this.filterBuilder_ == null) {
                this.filter_ = null;
                onChanged();
            } else {
                this.filter_ = null;
                this.filterBuilder_ = null;
            }
            return this;
        }

        public AccessLogFilter.Builder getFilterBuilder() {
            onChanged();
            return getFilterFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
        public AccessLogFilterOrBuilder getFilterOrBuilder() {
            SingleFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> singleFieldBuilderV3 = this.filterBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (AccessLogFilterOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            AccessLogFilter accessLogFilter = this.filter_;
            return accessLogFilter == null ? AccessLogFilter.getDefaultInstance() : accessLogFilter;
        }

        private SingleFieldBuilderV3<AccessLogFilter, AccessLogFilter.Builder, AccessLogFilterOrBuilder> getFilterFieldBuilder() {
            if (this.filterBuilder_ == null) {
                this.filterBuilder_ = new SingleFieldBuilderV3<>(getFilter(), getParentForChildren(), isClean());
                this.filter_ = null;
            }
            return this.filterBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
        public Any getTypedConfig() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configTypeCase_ == 4) {
                    return (Any) this.configType_;
                }
                return Any.getDefaultInstance();
            }
            if (this.configTypeCase_ == 4) {
                return singleFieldBuilderV3.getMessage();
            }
            return Any.getDefaultInstance();
        }

        public Builder setTypedConfig(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                any.getClass();
                this.configType_ = any;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(any);
            }
            this.configTypeCase_ = 4;
            return this;
        }

        public Builder setTypedConfig(Any.Builder builder) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.configType_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.configTypeCase_ = 4;
            return this;
        }

        public Builder mergeTypedConfig(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configTypeCase_ != 4 || this.configType_ == Any.getDefaultInstance()) {
                    this.configType_ = any;
                } else {
                    this.configType_ = Any.newBuilder((Any) this.configType_).mergeFrom(any).buildPartial();
                }
                onChanged();
            } else {
                if (this.configTypeCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(any);
                }
                this.typedConfigBuilder_.setMessage(any);
            }
            this.configTypeCase_ = 4;
            return this;
        }

        public Builder clearTypedConfig() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.configTypeCase_ == 4) {
                    this.configTypeCase_ = 0;
                    this.configType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.configTypeCase_ == 4) {
                this.configTypeCase_ = 0;
                this.configType_ = null;
                onChanged();
            }
            return this;
        }

        public Any.Builder getTypedConfigBuilder() {
            return getTypedConfigFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLogOrBuilder
        public AnyOrBuilder getTypedConfigOrBuilder() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3;
            int i = this.configTypeCase_;
            if (i == 4 && (singleFieldBuilderV3 = this.typedConfigBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 4) {
                return (Any) this.configType_;
            }
            return Any.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getTypedConfigFieldBuilder() {
            if (this.typedConfigBuilder_ == null) {
                if (this.configTypeCase_ != 4) {
                    this.configType_ = Any.getDefaultInstance();
                }
                this.typedConfigBuilder_ = new SingleFieldBuilderV3<>((Any) this.configType_, getParentForChildren(), isClean());
                this.configType_ = null;
            }
            this.configTypeCase_ = 4;
            onChanged();
            return this.typedConfigBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m19661setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m19655mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLog$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$accesslog$v3$AccessLog$ConfigTypeCase;

        static {
            int[] iArr = new int[ConfigTypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$accesslog$v3$AccessLog$ConfigTypeCase = iArr;
            try {
                iArr[ConfigTypeCase.TYPED_CONFIG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$accesslog$v3$AccessLog$ConfigTypeCase[ConfigTypeCase.CONFIGTYPE_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
