package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2;

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
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class LightstepConfig extends GeneratedMessageV3 implements LightstepConfigOrBuilder {
    public static final int ACCESS_TOKEN_FILE_FIELD_NUMBER = 2;
    public static final int COLLECTOR_CLUSTER_FIELD_NUMBER = 1;
    public static final int PROPAGATION_MODES_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final Internal.ListAdapter.Converter<Integer, PropagationMode> propagationModes_converter_ = new Internal.ListAdapter.Converter<Integer, PropagationMode>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfig.1
        public PropagationMode convert(Integer num) {
            PropagationMode propagationModeValueOf = PropagationMode.valueOf(num.intValue());
            return propagationModeValueOf == null ? PropagationMode.UNRECOGNIZED : propagationModeValueOf;
        }
    };
    private static final LightstepConfig DEFAULT_INSTANCE = new LightstepConfig();
    private static final Parser<LightstepConfig> PARSER = new AbstractParser<LightstepConfig>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfig.2
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public LightstepConfig m30168parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new LightstepConfig(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object accessTokenFile_;
    private volatile Object collectorCluster_;
    private byte memoizedIsInitialized;
    private int propagationModesMemoizedSerializedSize;
    private List<Integer> propagationModes_;

    private LightstepConfig(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private LightstepConfig() {
        this.memoizedIsInitialized = (byte) -1;
        this.collectorCluster_ = "";
        this.accessTokenFile_ = "";
        this.propagationModes_ = Collections.emptyList();
    }

    private LightstepConfig(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.collectorCluster_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.accessTokenFile_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 24) {
                                int i = codedInputStream.readEnum();
                                if (!(z2 & true)) {
                                    this.propagationModes_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.propagationModes_.add(Integer.valueOf(i));
                            } else if (tag == 26) {
                                int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    int i2 = codedInputStream.readEnum();
                                    if (!(z2 & true)) {
                                        this.propagationModes_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.propagationModes_.add(Integer.valueOf(i2));
                                }
                                codedInputStream.popLimit(iPushLimit);
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
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
                if (z2 & true) {
                    this.propagationModes_ = Collections.unmodifiableList(this.propagationModes_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static LightstepConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LightstepConfig> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LightstepProto.internal_static_envoy_config_trace_v2_LightstepConfig_descriptor;
    }

    public static LightstepConfig parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (LightstepConfig) PARSER.parseFrom(byteBuffer);
    }

    public static LightstepConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LightstepConfig) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static LightstepConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (LightstepConfig) PARSER.parseFrom(byteString);
    }

    public static LightstepConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LightstepConfig) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static LightstepConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (LightstepConfig) PARSER.parseFrom(bArr);
    }

    public static LightstepConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LightstepConfig) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static LightstepConfig parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static LightstepConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LightstepConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static LightstepConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LightstepConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static LightstepConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m30166toBuilder();
    }

    public static Builder newBuilder(LightstepConfig lightstepConfig) {
        return DEFAULT_INSTANCE.m30166toBuilder().mergeFrom(lightstepConfig);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public LightstepConfig m30161getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<LightstepConfig> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
    public List<Integer> getPropagationModesValueList() {
        return this.propagationModes_;
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
        return new LightstepConfig();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LightstepProto.internal_static_envoy_config_trace_v2_LightstepConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(LightstepConfig.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
    public String getCollectorCluster() {
        Object obj = this.collectorCluster_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.collectorCluster_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
    public ByteString getCollectorClusterBytes() {
        Object obj = this.collectorCluster_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.collectorCluster_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
    public String getAccessTokenFile() {
        Object obj = this.accessTokenFile_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.accessTokenFile_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
    public ByteString getAccessTokenFileBytes() {
        Object obj = this.accessTokenFile_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.accessTokenFile_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
    public List<PropagationMode> getPropagationModesList() {
        return new Internal.ListAdapter(this.propagationModes_, propagationModes_converter_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
    public int getPropagationModesCount() {
        return this.propagationModes_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
    public PropagationMode getPropagationModes(int i) {
        return (PropagationMode) propagationModes_converter_.convert(this.propagationModes_.get(i));
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
    public int getPropagationModesValue(int i) {
        return this.propagationModes_.get(i).intValue();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        if (!getCollectorClusterBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.collectorCluster_);
        }
        if (!getAccessTokenFileBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.accessTokenFile_);
        }
        if (getPropagationModesList().size() > 0) {
            codedOutputStream.writeUInt32NoTag(26);
            codedOutputStream.writeUInt32NoTag(this.propagationModesMemoizedSerializedSize);
        }
        for (int i = 0; i < this.propagationModes_.size(); i++) {
            codedOutputStream.writeEnumNoTag(this.propagationModes_.get(i).intValue());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getCollectorClusterBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.collectorCluster_) : 0;
        if (!getAccessTokenFileBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.accessTokenFile_);
        }
        int iComputeEnumSizeNoTag = 0;
        for (int i2 = 0; i2 < this.propagationModes_.size(); i2++) {
            iComputeEnumSizeNoTag += CodedOutputStream.computeEnumSizeNoTag(this.propagationModes_.get(i2).intValue());
        }
        int iComputeUInt32SizeNoTag = iComputeStringSize + iComputeEnumSizeNoTag;
        if (!getPropagationModesList().isEmpty()) {
            iComputeUInt32SizeNoTag = iComputeUInt32SizeNoTag + 1 + CodedOutputStream.computeUInt32SizeNoTag(iComputeEnumSizeNoTag);
        }
        this.propagationModesMemoizedSerializedSize = iComputeEnumSizeNoTag;
        int serializedSize = iComputeUInt32SizeNoTag + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LightstepConfig)) {
            return super.equals(obj);
        }
        LightstepConfig lightstepConfig = (LightstepConfig) obj;
        return getCollectorCluster().equals(lightstepConfig.getCollectorCluster()) && getAccessTokenFile().equals(lightstepConfig.getAccessTokenFile()) && this.propagationModes_.equals(lightstepConfig.propagationModes_) && this.unknownFields.equals(lightstepConfig.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCollectorCluster().hashCode()) * 37) + 2) * 53) + getAccessTokenFile().hashCode();
        if (getPropagationModesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + this.propagationModes_.hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m30163newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m30166toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum PropagationMode implements ProtocolMessageEnum {
        ENVOY(0),
        LIGHTSTEP(1),
        B3(2),
        TRACE_CONTEXT(3),
        UNRECOGNIZED(-1);

        public static final int B3_VALUE = 2;
        public static final int ENVOY_VALUE = 0;
        public static final int LIGHTSTEP_VALUE = 1;
        public static final int TRACE_CONTEXT_VALUE = 3;
        private static final Internal.EnumLiteMap<PropagationMode> internalValueMap = new Internal.EnumLiteMap<PropagationMode>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfig.PropagationMode.1
            public PropagationMode findValueByNumber(int i) {
                return PropagationMode.forNumber(i);
            }
        };
        private static final PropagationMode[] VALUES = values();
        private final int value;

        PropagationMode(int i) {
            this.value = i;
        }

        public static PropagationMode forNumber(int i) {
            if (i == 0) {
                return ENVOY;
            }
            if (i == 1) {
                return LIGHTSTEP;
            }
            if (i == 2) {
                return B3;
            }
            if (i != 3) {
                return null;
            }
            return TRACE_CONTEXT;
        }

        public static Internal.EnumLiteMap<PropagationMode> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static PropagationMode valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) LightstepConfig.getDescriptor().getEnumTypes().get(0);
        }

        public static PropagationMode valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LightstepConfigOrBuilder {
        private Object accessTokenFile_;
        private int bitField0_;
        private Object collectorCluster_;
        private List<Integer> propagationModes_;

        private Builder() {
            this.collectorCluster_ = "";
            this.accessTokenFile_ = "";
            this.propagationModes_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.collectorCluster_ = "";
            this.accessTokenFile_ = "";
            this.propagationModes_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LightstepProto.internal_static_envoy_config_trace_v2_LightstepConfig_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LightstepProto.internal_static_envoy_config_trace_v2_LightstepConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(LightstepConfig.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = LightstepConfig.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30177clear() {
            super.clear();
            this.collectorCluster_ = "";
            this.accessTokenFile_ = "";
            this.propagationModes_ = Collections.emptyList();
            this.bitField0_ &= -2;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return LightstepProto.internal_static_envoy_config_trace_v2_LightstepConfig_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LightstepConfig m30190getDefaultInstanceForType() {
            return LightstepConfig.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LightstepConfig m30171build() throws UninitializedMessageException {
            LightstepConfig lightstepConfigM30173buildPartial = m30173buildPartial();
            if (lightstepConfigM30173buildPartial.isInitialized()) {
                return lightstepConfigM30173buildPartial;
            }
            throw newUninitializedMessageException(lightstepConfigM30173buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LightstepConfig m30173buildPartial() {
            LightstepConfig lightstepConfig = new LightstepConfig(this);
            lightstepConfig.collectorCluster_ = this.collectorCluster_;
            lightstepConfig.accessTokenFile_ = this.accessTokenFile_;
            if ((this.bitField0_ & 1) != 0) {
                this.propagationModes_ = Collections.unmodifiableList(this.propagationModes_);
                this.bitField0_ &= -2;
            }
            lightstepConfig.propagationModes_ = this.propagationModes_;
            onBuilt();
            return lightstepConfig;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30189clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30201setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30179clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30182clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30203setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30169addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m30194mergeFrom(Message message) {
            if (message instanceof LightstepConfig) {
                return mergeFrom((LightstepConfig) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(LightstepConfig lightstepConfig) {
            if (lightstepConfig == LightstepConfig.getDefaultInstance()) {
                return this;
            }
            if (!lightstepConfig.getCollectorCluster().isEmpty()) {
                this.collectorCluster_ = lightstepConfig.collectorCluster_;
                onChanged();
            }
            if (!lightstepConfig.getAccessTokenFile().isEmpty()) {
                this.accessTokenFile_ = lightstepConfig.accessTokenFile_;
                onChanged();
            }
            if (!lightstepConfig.propagationModes_.isEmpty()) {
                if (this.propagationModes_.isEmpty()) {
                    this.propagationModes_ = lightstepConfig.propagationModes_;
                    this.bitField0_ &= -2;
                } else {
                    ensurePropagationModesIsMutable();
                    this.propagationModes_.addAll(lightstepConfig.propagationModes_);
                }
                onChanged();
            }
            m30199mergeUnknownFields(lightstepConfig.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfig.Builder m30195mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfig.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfig r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfig) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfig r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfig) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfig.Builder.m30195mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfig$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
        public String getCollectorCluster() {
            Object obj = this.collectorCluster_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.collectorCluster_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setCollectorCluster(String str) {
            str.getClass();
            this.collectorCluster_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
        public ByteString getCollectorClusterBytes() {
            Object obj = this.collectorCluster_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.collectorCluster_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setCollectorClusterBytes(ByteString byteString) {
            byteString.getClass();
            LightstepConfig.checkByteStringIsUtf8(byteString);
            this.collectorCluster_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearCollectorCluster() {
            this.collectorCluster_ = LightstepConfig.getDefaultInstance().getCollectorCluster();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
        public String getAccessTokenFile() {
            Object obj = this.accessTokenFile_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.accessTokenFile_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setAccessTokenFile(String str) {
            str.getClass();
            this.accessTokenFile_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
        public ByteString getAccessTokenFileBytes() {
            Object obj = this.accessTokenFile_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.accessTokenFile_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAccessTokenFileBytes(ByteString byteString) {
            byteString.getClass();
            LightstepConfig.checkByteStringIsUtf8(byteString);
            this.accessTokenFile_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearAccessTokenFile() {
            this.accessTokenFile_ = LightstepConfig.getDefaultInstance().getAccessTokenFile();
            onChanged();
            return this;
        }

        private void ensurePropagationModesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.propagationModes_ = new ArrayList(this.propagationModes_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
        public List<PropagationMode> getPropagationModesList() {
            return new Internal.ListAdapter(this.propagationModes_, LightstepConfig.propagationModes_converter_);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
        public int getPropagationModesCount() {
            return this.propagationModes_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
        public PropagationMode getPropagationModes(int i) {
            return (PropagationMode) LightstepConfig.propagationModes_converter_.convert(this.propagationModes_.get(i));
        }

        public Builder setPropagationModes(int i, PropagationMode propagationMode) {
            propagationMode.getClass();
            ensurePropagationModesIsMutable();
            this.propagationModes_.set(i, Integer.valueOf(propagationMode.getNumber()));
            onChanged();
            return this;
        }

        public Builder addPropagationModes(PropagationMode propagationMode) {
            propagationMode.getClass();
            ensurePropagationModesIsMutable();
            this.propagationModes_.add(Integer.valueOf(propagationMode.getNumber()));
            onChanged();
            return this;
        }

        public Builder addAllPropagationModes(Iterable<? extends PropagationMode> iterable) {
            ensurePropagationModesIsMutable();
            Iterator<? extends PropagationMode> it2 = iterable.iterator();
            while (it2.hasNext()) {
                this.propagationModes_.add(Integer.valueOf(it2.next().getNumber()));
            }
            onChanged();
            return this;
        }

        public Builder clearPropagationModes() {
            this.propagationModes_ = Collections.emptyList();
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
        public List<Integer> getPropagationModesValueList() {
            return Collections.unmodifiableList(this.propagationModes_);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.LightstepConfigOrBuilder
        public int getPropagationModesValue(int i) {
            return this.propagationModes_.get(i).intValue();
        }

        public Builder setPropagationModesValue(int i, int i2) {
            ensurePropagationModesIsMutable();
            this.propagationModes_.set(i, Integer.valueOf(i2));
            onChanged();
            return this;
        }

        public Builder addPropagationModesValue(int i) {
            ensurePropagationModesIsMutable();
            this.propagationModes_.add(Integer.valueOf(i));
            onChanged();
            return this;
        }

        public Builder addAllPropagationModesValue(Iterable<Integer> iterable) {
            ensurePropagationModesIsMutable();
            Iterator<Integer> it2 = iterable.iterator();
            while (it2.hasNext()) {
                this.propagationModes_.add(Integer.valueOf(it2.next().intValue()));
            }
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m30205setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m30199mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
