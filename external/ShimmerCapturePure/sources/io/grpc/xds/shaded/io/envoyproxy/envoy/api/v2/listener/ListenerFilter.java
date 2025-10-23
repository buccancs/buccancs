package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener;

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
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterChainMatchPredicate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class ListenerFilter extends GeneratedMessageV3 implements ListenerFilterOrBuilder {
    public static final int CONFIG_FIELD_NUMBER = 2;
    public static final int FILTER_DISABLED_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int TYPED_CONFIG_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final ListenerFilter DEFAULT_INSTANCE = new ListenerFilter();
    private static final Parser<ListenerFilter> PARSER = new AbstractParser<ListenerFilter>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilter.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ListenerFilter m17679parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListenerFilter(codedInputStream, extensionRegistryLite);
        }
    };
    private int configTypeCase_;
    private Object configType_;
    private ListenerFilterChainMatchPredicate filterDisabled_;
    private byte memoizedIsInitialized;
    private volatile Object name_;

    private ListenerFilter(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.configTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListenerFilter() {
        this.configTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    private ListenerFilter(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Struct.Builder builderM17723toBuilder;
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
                                builderM17723toBuilder = this.configTypeCase_ == 2 ? ((Struct) this.configType_).toBuilder() : null;
                                Struct message = codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                                this.configType_ = message;
                                if (builderM17723toBuilder != null) {
                                    builderM17723toBuilder.mergeFrom(message);
                                    this.configType_ = builderM17723toBuilder.buildPartial();
                                }
                                this.configTypeCase_ = 2;
                            } else if (tag == 26) {
                                builderM17723toBuilder = this.configTypeCase_ == 3 ? ((Any) this.configType_).toBuilder() : null;
                                Any message2 = codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                                this.configType_ = message2;
                                if (builderM17723toBuilder != null) {
                                    builderM17723toBuilder.mergeFrom(message2);
                                    this.configType_ = builderM17723toBuilder.buildPartial();
                                }
                                this.configTypeCase_ = 3;
                            } else if (tag == 34) {
                                ListenerFilterChainMatchPredicate listenerFilterChainMatchPredicate = this.filterDisabled_;
                                builderM17723toBuilder = listenerFilterChainMatchPredicate != null ? listenerFilterChainMatchPredicate.m17723toBuilder() : null;
                                ListenerFilterChainMatchPredicate listenerFilterChainMatchPredicate2 = (ListenerFilterChainMatchPredicate) codedInputStream.readMessage(ListenerFilterChainMatchPredicate.parser(), extensionRegistryLite);
                                this.filterDisabled_ = listenerFilterChainMatchPredicate2;
                                if (builderM17723toBuilder != null) {
                                    builderM17723toBuilder.mergeFrom(listenerFilterChainMatchPredicate2);
                                    this.filterDisabled_ = builderM17723toBuilder.m17730buildPartial();
                                }
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.name_ = codedInputStream.readStringRequireUtf8();
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

    public static ListenerFilter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListenerFilter> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ListenerComponentsProto.internal_static_envoy_api_v2_listener_ListenerFilter_descriptor;
    }

    public static ListenerFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ListenerFilter) PARSER.parseFrom(byteBuffer);
    }

    public static ListenerFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListenerFilter) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListenerFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ListenerFilter) PARSER.parseFrom(byteString);
    }

    public static ListenerFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListenerFilter) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListenerFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ListenerFilter) PARSER.parseFrom(bArr);
    }

    public static ListenerFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListenerFilter) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListenerFilter parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListenerFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListenerFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListenerFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListenerFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListenerFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m17677toBuilder();
    }

    public static Builder newBuilder(ListenerFilter listenerFilter) {
        return DEFAULT_INSTANCE.m17677toBuilder().mergeFrom(listenerFilter);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ListenerFilter m17672getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ListenerFilter> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
    @Deprecated
    public boolean hasConfig() {
        return this.configTypeCase_ == 2;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
    public boolean hasFilterDisabled() {
        return this.filterDisabled_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
    public boolean hasTypedConfig() {
        return this.configTypeCase_ == 3;
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
        return new ListenerFilter();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ListenerComponentsProto.internal_static_envoy_api_v2_listener_ListenerFilter_fieldAccessorTable.ensureFieldAccessorsInitialized(ListenerFilter.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
    public ConfigTypeCase getConfigTypeCase() {
        return ConfigTypeCase.forNumber(this.configTypeCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
    @Deprecated
    public Struct getConfig() {
        if (this.configTypeCase_ == 2) {
            return (Struct) this.configType_;
        }
        return Struct.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
    @Deprecated
    public StructOrBuilder getConfigOrBuilder() {
        if (this.configTypeCase_ == 2) {
            return (Struct) this.configType_;
        }
        return Struct.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
    public Any getTypedConfig() {
        if (this.configTypeCase_ == 3) {
            return (Any) this.configType_;
        }
        return Any.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
    public AnyOrBuilder getTypedConfigOrBuilder() {
        if (this.configTypeCase_ == 3) {
            return (Any) this.configType_;
        }
        return Any.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
    public ListenerFilterChainMatchPredicate getFilterDisabled() {
        ListenerFilterChainMatchPredicate listenerFilterChainMatchPredicate = this.filterDisabled_;
        return listenerFilterChainMatchPredicate == null ? ListenerFilterChainMatchPredicate.getDefaultInstance() : listenerFilterChainMatchPredicate;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
    public ListenerFilterChainMatchPredicateOrBuilder getFilterDisabledOrBuilder() {
        return getFilterDisabled();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.configTypeCase_ == 2) {
            codedOutputStream.writeMessage(2, (Struct) this.configType_);
        }
        if (this.configTypeCase_ == 3) {
            codedOutputStream.writeMessage(3, (Any) this.configType_);
        }
        if (this.filterDisabled_ != null) {
            codedOutputStream.writeMessage(4, getFilterDisabled());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (this.configTypeCase_ == 2) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, (Struct) this.configType_);
        }
        if (this.configTypeCase_ == 3) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, (Any) this.configType_);
        }
        if (this.filterDisabled_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(4, getFilterDisabled());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListenerFilter)) {
            return super.equals(obj);
        }
        ListenerFilter listenerFilter = (ListenerFilter) obj;
        if (!getName().equals(listenerFilter.getName()) || hasFilterDisabled() != listenerFilter.hasFilterDisabled()) {
            return false;
        }
        if ((hasFilterDisabled() && !getFilterDisabled().equals(listenerFilter.getFilterDisabled())) || !getConfigTypeCase().equals(listenerFilter.getConfigTypeCase())) {
            return false;
        }
        int i = this.configTypeCase_;
        if (i == 2) {
            if (!getConfig().equals(listenerFilter.getConfig())) {
                return false;
            }
        } else if (i == 3 && !getTypedConfig().equals(listenerFilter.getTypedConfig())) {
            return false;
        }
        return this.unknownFields.equals(listenerFilter.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (hasFilterDisabled()) {
            iHashCode2 = (((iHashCode2 * 37) + 4) * 53) + getFilterDisabled().hashCode();
        }
        int i2 = this.configTypeCase_;
        if (i2 == 2) {
            i = ((iHashCode2 * 37) + 2) * 53;
            iHashCode = getConfig().hashCode();
        } else {
            if (i2 == 3) {
                i = ((iHashCode2 * 37) + 3) * 53;
                iHashCode = getTypedConfig().hashCode();
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
    public Builder m17674newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m17677toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ConfigTypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        CONFIG(2),
        TYPED_CONFIG(3),
        CONFIGTYPE_NOT_SET(0);

        private final int value;

        ConfigTypeCase(int i) {
            this.value = i;
        }

        public static ConfigTypeCase forNumber(int i) {
            if (i == 0) {
                return CONFIGTYPE_NOT_SET;
            }
            if (i == 2) {
                return CONFIG;
            }
            if (i != 3) {
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListenerFilterOrBuilder {
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> configBuilder_;
        private int configTypeCase_;
        private Object configType_;
        private SingleFieldBuilderV3<ListenerFilterChainMatchPredicate, ListenerFilterChainMatchPredicate.Builder, ListenerFilterChainMatchPredicateOrBuilder> filterDisabledBuilder_;
        private ListenerFilterChainMatchPredicate filterDisabled_;
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
            return ListenerComponentsProto.internal_static_envoy_api_v2_listener_ListenerFilter_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
        @Deprecated
        public boolean hasConfig() {
            return this.configTypeCase_ == 2;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
        public boolean hasFilterDisabled() {
            return (this.filterDisabledBuilder_ == null && this.filterDisabled_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
        public boolean hasTypedConfig() {
            return this.configTypeCase_ == 3;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ListenerComponentsProto.internal_static_envoy_api_v2_listener_ListenerFilter_fieldAccessorTable.ensureFieldAccessorsInitialized(ListenerFilter.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ListenerFilter.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17688clear() {
            super.clear();
            this.name_ = "";
            if (this.filterDisabledBuilder_ == null) {
                this.filterDisabled_ = null;
            } else {
                this.filterDisabled_ = null;
                this.filterDisabledBuilder_ = null;
            }
            this.configTypeCase_ = 0;
            this.configType_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ListenerComponentsProto.internal_static_envoy_api_v2_listener_ListenerFilter_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ListenerFilter m17701getDefaultInstanceForType() {
            return ListenerFilter.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ListenerFilter m17682build() throws UninitializedMessageException {
            ListenerFilter listenerFilterM17684buildPartial = m17684buildPartial();
            if (listenerFilterM17684buildPartial.isInitialized()) {
                return listenerFilterM17684buildPartial;
            }
            throw newUninitializedMessageException(listenerFilterM17684buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ListenerFilter m17684buildPartial() {
            ListenerFilter listenerFilter = new ListenerFilter(this);
            listenerFilter.name_ = this.name_;
            if (this.configTypeCase_ == 2) {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
                if (singleFieldBuilderV3 == null) {
                    listenerFilter.configType_ = this.configType_;
                } else {
                    listenerFilter.configType_ = singleFieldBuilderV3.build();
                }
            }
            if (this.configTypeCase_ == 3) {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV32 = this.typedConfigBuilder_;
                if (singleFieldBuilderV32 == null) {
                    listenerFilter.configType_ = this.configType_;
                } else {
                    listenerFilter.configType_ = singleFieldBuilderV32.build();
                }
            }
            SingleFieldBuilderV3<ListenerFilterChainMatchPredicate, ListenerFilterChainMatchPredicate.Builder, ListenerFilterChainMatchPredicateOrBuilder> singleFieldBuilderV33 = this.filterDisabledBuilder_;
            if (singleFieldBuilderV33 == null) {
                listenerFilter.filterDisabled_ = this.filterDisabled_;
            } else {
                listenerFilter.filterDisabled_ = singleFieldBuilderV33.build();
            }
            listenerFilter.configTypeCase_ = this.configTypeCase_;
            onBuilt();
            return listenerFilter;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17700clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17712setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17690clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17693clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17714setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17680addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17705mergeFrom(Message message) {
            if (message instanceof ListenerFilter) {
                return mergeFrom((ListenerFilter) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListenerFilter listenerFilter) {
            if (listenerFilter == ListenerFilter.getDefaultInstance()) {
                return this;
            }
            if (!listenerFilter.getName().isEmpty()) {
                this.name_ = listenerFilter.name_;
                onChanged();
            }
            if (listenerFilter.hasFilterDisabled()) {
                mergeFilterDisabled(listenerFilter.getFilterDisabled());
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$api$v2$listener$ListenerFilter$ConfigTypeCase[listenerFilter.getConfigTypeCase().ordinal()];
            if (i == 1) {
                mergeConfig(listenerFilter.getConfig());
            } else if (i == 2) {
                mergeTypedConfig(listenerFilter.getTypedConfig());
            }
            m17710mergeUnknownFields(listenerFilter.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilter.Builder m17706mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilter.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilter r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilter) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilter r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilter) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilter.Builder.m17706mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilter$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
        public ConfigTypeCase getConfigTypeCase() {
            return ConfigTypeCase.forNumber(this.configTypeCase_);
        }

        public Builder clearConfigType() {
            this.configTypeCase_ = 0;
            this.configType_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
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
            ListenerFilter.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = ListenerFilter.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
        @Deprecated
        public Struct getConfig() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configTypeCase_ == 2) {
                    return (Struct) this.configType_;
                }
                return Struct.getDefaultInstance();
            }
            if (this.configTypeCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return Struct.getDefaultInstance();
        }

        @Deprecated
        public Builder setConfig(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
            if (singleFieldBuilderV3 == null) {
                struct.getClass();
                this.configType_ = struct;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(struct);
            }
            this.configTypeCase_ = 2;
            return this;
        }

        @Deprecated
        public Builder setConfig(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.configType_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.configTypeCase_ = 2;
            return this;
        }

        @Deprecated
        public Builder mergeConfig(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configTypeCase_ != 2 || this.configType_ == Struct.getDefaultInstance()) {
                    this.configType_ = struct;
                } else {
                    this.configType_ = Struct.newBuilder((Struct) this.configType_).mergeFrom(struct).buildPartial();
                }
                onChanged();
            } else {
                if (this.configTypeCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(struct);
                }
                this.configBuilder_.setMessage(struct);
            }
            this.configTypeCase_ = 2;
            return this;
        }

        @Deprecated
        public Builder clearConfig() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.configTypeCase_ == 2) {
                    this.configTypeCase_ = 0;
                    this.configType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.configTypeCase_ == 2) {
                this.configTypeCase_ = 0;
                this.configType_ = null;
                onChanged();
            }
            return this;
        }

        @Deprecated
        public Struct.Builder getConfigBuilder() {
            return getConfigFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
        @Deprecated
        public StructOrBuilder getConfigOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3;
            int i = this.configTypeCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.configBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (Struct) this.configType_;
            }
            return Struct.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getConfigFieldBuilder() {
            if (this.configBuilder_ == null) {
                if (this.configTypeCase_ != 2) {
                    this.configType_ = Struct.getDefaultInstance();
                }
                this.configBuilder_ = new SingleFieldBuilderV3<>((Struct) this.configType_, getParentForChildren(), isClean());
                this.configType_ = null;
            }
            this.configTypeCase_ = 2;
            onChanged();
            return this.configBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
        public Any getTypedConfig() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configTypeCase_ == 3) {
                    return (Any) this.configType_;
                }
                return Any.getDefaultInstance();
            }
            if (this.configTypeCase_ == 3) {
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
            this.configTypeCase_ = 3;
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
            this.configTypeCase_ = 3;
            return this;
        }

        public Builder mergeTypedConfig(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configTypeCase_ != 3 || this.configType_ == Any.getDefaultInstance()) {
                    this.configType_ = any;
                } else {
                    this.configType_ = Any.newBuilder((Any) this.configType_).mergeFrom(any).buildPartial();
                }
                onChanged();
            } else {
                if (this.configTypeCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(any);
                }
                this.typedConfigBuilder_.setMessage(any);
            }
            this.configTypeCase_ = 3;
            return this;
        }

        public Builder clearTypedConfig() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.configTypeCase_ == 3) {
                    this.configTypeCase_ = 0;
                    this.configType_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.configTypeCase_ == 3) {
                this.configTypeCase_ = 0;
                this.configType_ = null;
                onChanged();
            }
            return this;
        }

        public Any.Builder getTypedConfigBuilder() {
            return getTypedConfigFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
        public AnyOrBuilder getTypedConfigOrBuilder() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3;
            int i = this.configTypeCase_;
            if (i == 3 && (singleFieldBuilderV3 = this.typedConfigBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 3) {
                return (Any) this.configType_;
            }
            return Any.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getTypedConfigFieldBuilder() {
            if (this.typedConfigBuilder_ == null) {
                if (this.configTypeCase_ != 3) {
                    this.configType_ = Any.getDefaultInstance();
                }
                this.typedConfigBuilder_ = new SingleFieldBuilderV3<>((Any) this.configType_, getParentForChildren(), isClean());
                this.configType_ = null;
            }
            this.configTypeCase_ = 3;
            onChanged();
            return this.typedConfigBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
        public ListenerFilterChainMatchPredicate getFilterDisabled() {
            SingleFieldBuilderV3<ListenerFilterChainMatchPredicate, ListenerFilterChainMatchPredicate.Builder, ListenerFilterChainMatchPredicateOrBuilder> singleFieldBuilderV3 = this.filterDisabledBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            ListenerFilterChainMatchPredicate listenerFilterChainMatchPredicate = this.filterDisabled_;
            return listenerFilterChainMatchPredicate == null ? ListenerFilterChainMatchPredicate.getDefaultInstance() : listenerFilterChainMatchPredicate;
        }

        public Builder setFilterDisabled(ListenerFilterChainMatchPredicate listenerFilterChainMatchPredicate) {
            SingleFieldBuilderV3<ListenerFilterChainMatchPredicate, ListenerFilterChainMatchPredicate.Builder, ListenerFilterChainMatchPredicateOrBuilder> singleFieldBuilderV3 = this.filterDisabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                listenerFilterChainMatchPredicate.getClass();
                this.filterDisabled_ = listenerFilterChainMatchPredicate;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(listenerFilterChainMatchPredicate);
            }
            return this;
        }

        public Builder setFilterDisabled(ListenerFilterChainMatchPredicate.Builder builder) {
            SingleFieldBuilderV3<ListenerFilterChainMatchPredicate, ListenerFilterChainMatchPredicate.Builder, ListenerFilterChainMatchPredicateOrBuilder> singleFieldBuilderV3 = this.filterDisabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.filterDisabled_ = builder.m17728build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m17728build());
            }
            return this;
        }

        public Builder mergeFilterDisabled(ListenerFilterChainMatchPredicate listenerFilterChainMatchPredicate) {
            SingleFieldBuilderV3<ListenerFilterChainMatchPredicate, ListenerFilterChainMatchPredicate.Builder, ListenerFilterChainMatchPredicateOrBuilder> singleFieldBuilderV3 = this.filterDisabledBuilder_;
            if (singleFieldBuilderV3 == null) {
                ListenerFilterChainMatchPredicate listenerFilterChainMatchPredicate2 = this.filterDisabled_;
                if (listenerFilterChainMatchPredicate2 != null) {
                    this.filterDisabled_ = ListenerFilterChainMatchPredicate.newBuilder(listenerFilterChainMatchPredicate2).mergeFrom(listenerFilterChainMatchPredicate).m17730buildPartial();
                } else {
                    this.filterDisabled_ = listenerFilterChainMatchPredicate;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(listenerFilterChainMatchPredicate);
            }
            return this;
        }

        public Builder clearFilterDisabled() {
            if (this.filterDisabledBuilder_ == null) {
                this.filterDisabled_ = null;
                onChanged();
            } else {
                this.filterDisabled_ = null;
                this.filterDisabledBuilder_ = null;
            }
            return this;
        }

        public ListenerFilterChainMatchPredicate.Builder getFilterDisabledBuilder() {
            onChanged();
            return getFilterDisabledFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilterOrBuilder
        public ListenerFilterChainMatchPredicateOrBuilder getFilterDisabledOrBuilder() {
            SingleFieldBuilderV3<ListenerFilterChainMatchPredicate, ListenerFilterChainMatchPredicate.Builder, ListenerFilterChainMatchPredicateOrBuilder> singleFieldBuilderV3 = this.filterDisabledBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ListenerFilterChainMatchPredicateOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            ListenerFilterChainMatchPredicate listenerFilterChainMatchPredicate = this.filterDisabled_;
            return listenerFilterChainMatchPredicate == null ? ListenerFilterChainMatchPredicate.getDefaultInstance() : listenerFilterChainMatchPredicate;
        }

        private SingleFieldBuilderV3<ListenerFilterChainMatchPredicate, ListenerFilterChainMatchPredicate.Builder, ListenerFilterChainMatchPredicateOrBuilder> getFilterDisabledFieldBuilder() {
            if (this.filterDisabledBuilder_ == null) {
                this.filterDisabledBuilder_ = new SingleFieldBuilderV3<>(getFilterDisabled(), getParentForChildren(), isClean());
                this.filterDisabled_ = null;
            }
            return this.filterDisabledBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m17716setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m17710mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilter$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$api$v2$listener$ListenerFilter$ConfigTypeCase;

        static {
            int[] iArr = new int[ConfigTypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$api$v2$listener$ListenerFilter$ConfigTypeCase = iArr;
            try {
                iArr[ConfigTypeCase.CONFIG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$listener$ListenerFilter$ConfigTypeCase[ConfigTypeCase.TYPED_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$listener$ListenerFilter$ConfigTypeCase[ConfigTypeCase.CONFIGTYPE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
