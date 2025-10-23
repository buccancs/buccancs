package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3;

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
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSourceOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRds;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsList;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class ScopedRoutes extends GeneratedMessageV3 implements ScopedRoutesOrBuilder {
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int RDS_CONFIG_SOURCE_FIELD_NUMBER = 3;
    public static final int SCOPED_RDS_FIELD_NUMBER = 5;
    public static final int SCOPED_ROUTE_CONFIGURATIONS_LIST_FIELD_NUMBER = 4;
    public static final int SCOPE_KEY_BUILDER_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final ScopedRoutes DEFAULT_INSTANCE = new ScopedRoutes();
    private static final Parser<ScopedRoutes> PARSER = new AbstractParser<ScopedRoutes>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ScopedRoutes m31374parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ScopedRoutes(codedInputStream, extensionRegistryLite);
        }
    };
    private int configSpecifierCase_;
    private Object configSpecifier_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private ConfigSource rdsConfigSource_;
    private ScopeKeyBuilder scopeKeyBuilder_;

    private ScopedRoutes(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.configSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private ScopedRoutes() {
        this.configSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    private ScopedRoutes(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag != 10) {
                                if (tag == 18) {
                                    ScopeKeyBuilder scopeKeyBuilder = this.scopeKeyBuilder_;
                                    ScopeKeyBuilder.Builder builderM31418toBuilder = scopeKeyBuilder != null ? scopeKeyBuilder.m31418toBuilder() : null;
                                    ScopeKeyBuilder scopeKeyBuilder2 = (ScopeKeyBuilder) codedInputStream.readMessage(ScopeKeyBuilder.parser(), extensionRegistryLite);
                                    this.scopeKeyBuilder_ = scopeKeyBuilder2;
                                    if (builderM31418toBuilder != null) {
                                        builderM31418toBuilder.mergeFrom(scopeKeyBuilder2);
                                        this.scopeKeyBuilder_ = builderM31418toBuilder.m31425buildPartial();
                                    }
                                } else if (tag == 26) {
                                    ConfigSource configSource = this.rdsConfigSource_;
                                    ConfigSource.Builder builderM21799toBuilder = configSource != null ? configSource.m21799toBuilder() : null;
                                    ConfigSource configSource2 = (ConfigSource) codedInputStream.readMessage(ConfigSource.parser(), extensionRegistryLite);
                                    this.rdsConfigSource_ = configSource2;
                                    if (builderM21799toBuilder != null) {
                                        builderM21799toBuilder.mergeFrom(configSource2);
                                        this.rdsConfigSource_ = builderM21799toBuilder.m21806buildPartial();
                                    }
                                } else if (tag == 34) {
                                    ScopedRouteConfigurationsList.Builder builderM31326toBuilder = this.configSpecifierCase_ == 4 ? ((ScopedRouteConfigurationsList) this.configSpecifier_).m31326toBuilder() : null;
                                    MessageLite message = codedInputStream.readMessage(ScopedRouteConfigurationsList.parser(), extensionRegistryLite);
                                    this.configSpecifier_ = message;
                                    if (builderM31326toBuilder != null) {
                                        builderM31326toBuilder.mergeFrom((ScopedRouteConfigurationsList) message);
                                        this.configSpecifier_ = builderM31326toBuilder.m31333buildPartial();
                                    }
                                    this.configSpecifierCase_ = 4;
                                } else if (tag == 42) {
                                    ScopedRds.Builder builderM31280toBuilder = this.configSpecifierCase_ == 5 ? ((ScopedRds) this.configSpecifier_).m31280toBuilder() : null;
                                    MessageLite message2 = codedInputStream.readMessage(ScopedRds.parser(), extensionRegistryLite);
                                    this.configSpecifier_ = message2;
                                    if (builderM31280toBuilder != null) {
                                        builderM31280toBuilder.mergeFrom((ScopedRds) message2);
                                        this.configSpecifier_ = builderM31280toBuilder.m31287buildPartial();
                                    }
                                    this.configSpecifierCase_ = 5;
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                this.name_ = codedInputStream.readStringRequireUtf8();
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

    public static ScopedRoutes getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ScopedRoutes> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_descriptor;
    }

    public static ScopedRoutes parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ScopedRoutes) PARSER.parseFrom(byteBuffer);
    }

    public static ScopedRoutes parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ScopedRoutes) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ScopedRoutes parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ScopedRoutes) PARSER.parseFrom(byteString);
    }

    public static ScopedRoutes parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ScopedRoutes) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ScopedRoutes parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ScopedRoutes) PARSER.parseFrom(bArr);
    }

    public static ScopedRoutes parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ScopedRoutes) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ScopedRoutes parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ScopedRoutes parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ScopedRoutes parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ScopedRoutes parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ScopedRoutes parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ScopedRoutes parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m31372toBuilder();
    }

    public static Builder newBuilder(ScopedRoutes scopedRoutes) {
        return DEFAULT_INSTANCE.m31372toBuilder().mergeFrom(scopedRoutes);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ScopedRoutes m31367getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ScopedRoutes> getParserForType() {
        return PARSER;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public boolean hasRdsConfigSource() {
        return this.rdsConfigSource_ != null;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public boolean hasScopeKeyBuilder() {
        return this.scopeKeyBuilder_ != null;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public boolean hasScopedRds() {
        return this.configSpecifierCase_ == 5;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public boolean hasScopedRouteConfigurationsList() {
        return this.configSpecifierCase_ == 4;
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
        return new ScopedRoutes();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_fieldAccessorTable.ensureFieldAccessorsInitialized(ScopedRoutes.class, Builder.class);
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public ConfigSpecifierCase getConfigSpecifierCase() {
        return ConfigSpecifierCase.forNumber(this.configSpecifierCase_);
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public ScopeKeyBuilder getScopeKeyBuilder() {
        ScopeKeyBuilder scopeKeyBuilder = this.scopeKeyBuilder_;
        return scopeKeyBuilder == null ? ScopeKeyBuilder.getDefaultInstance() : scopeKeyBuilder;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public ScopeKeyBuilderOrBuilder getScopeKeyBuilderOrBuilder() {
        return getScopeKeyBuilder();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public ConfigSource getRdsConfigSource() {
        ConfigSource configSource = this.rdsConfigSource_;
        return configSource == null ? ConfigSource.getDefaultInstance() : configSource;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public ConfigSourceOrBuilder getRdsConfigSourceOrBuilder() {
        return getRdsConfigSource();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public ScopedRouteConfigurationsList getScopedRouteConfigurationsList() {
        if (this.configSpecifierCase_ == 4) {
            return (ScopedRouteConfigurationsList) this.configSpecifier_;
        }
        return ScopedRouteConfigurationsList.getDefaultInstance();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public ScopedRouteConfigurationsListOrBuilder getScopedRouteConfigurationsListOrBuilder() {
        if (this.configSpecifierCase_ == 4) {
            return (ScopedRouteConfigurationsList) this.configSpecifier_;
        }
        return ScopedRouteConfigurationsList.getDefaultInstance();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public ScopedRds getScopedRds() {
        if (this.configSpecifierCase_ == 5) {
            return (ScopedRds) this.configSpecifier_;
        }
        return ScopedRds.getDefaultInstance();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
    public ScopedRdsOrBuilder getScopedRdsOrBuilder() {
        if (this.configSpecifierCase_ == 5) {
            return (ScopedRds) this.configSpecifier_;
        }
        return ScopedRds.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.scopeKeyBuilder_ != null) {
            codedOutputStream.writeMessage(2, getScopeKeyBuilder());
        }
        if (this.rdsConfigSource_ != null) {
            codedOutputStream.writeMessage(3, getRdsConfigSource());
        }
        if (this.configSpecifierCase_ == 4) {
            codedOutputStream.writeMessage(4, (ScopedRouteConfigurationsList) this.configSpecifier_);
        }
        if (this.configSpecifierCase_ == 5) {
            codedOutputStream.writeMessage(5, (ScopedRds) this.configSpecifier_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (this.scopeKeyBuilder_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, getScopeKeyBuilder());
        }
        if (this.rdsConfigSource_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, getRdsConfigSource());
        }
        if (this.configSpecifierCase_ == 4) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(4, (ScopedRouteConfigurationsList) this.configSpecifier_);
        }
        if (this.configSpecifierCase_ == 5) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(5, (ScopedRds) this.configSpecifier_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ScopedRoutes)) {
            return super.equals(obj);
        }
        ScopedRoutes scopedRoutes = (ScopedRoutes) obj;
        if (!getName().equals(scopedRoutes.getName()) || hasScopeKeyBuilder() != scopedRoutes.hasScopeKeyBuilder()) {
            return false;
        }
        if ((hasScopeKeyBuilder() && !getScopeKeyBuilder().equals(scopedRoutes.getScopeKeyBuilder())) || hasRdsConfigSource() != scopedRoutes.hasRdsConfigSource()) {
            return false;
        }
        if ((hasRdsConfigSource() && !getRdsConfigSource().equals(scopedRoutes.getRdsConfigSource())) || !getConfigSpecifierCase().equals(scopedRoutes.getConfigSpecifierCase())) {
            return false;
        }
        int i = this.configSpecifierCase_;
        if (i == 4) {
            if (!getScopedRouteConfigurationsList().equals(scopedRoutes.getScopedRouteConfigurationsList())) {
                return false;
            }
        } else if (i == 5 && !getScopedRds().equals(scopedRoutes.getScopedRds())) {
            return false;
        }
        return this.unknownFields.equals(scopedRoutes.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (hasScopeKeyBuilder()) {
            iHashCode2 = (((iHashCode2 * 37) + 2) * 53) + getScopeKeyBuilder().hashCode();
        }
        if (hasRdsConfigSource()) {
            iHashCode2 = (((iHashCode2 * 37) + 3) * 53) + getRdsConfigSource().hashCode();
        }
        int i2 = this.configSpecifierCase_;
        if (i2 == 4) {
            i = ((iHashCode2 * 37) + 4) * 53;
            iHashCode = getScopedRouteConfigurationsList().hashCode();
        } else {
            if (i2 == 5) {
                i = ((iHashCode2 * 37) + 5) * 53;
                iHashCode = getScopedRds().hashCode();
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
    public Builder m31369newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m31372toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ConfigSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        SCOPED_ROUTE_CONFIGURATIONS_LIST(4),
        SCOPED_RDS(5),
        CONFIGSPECIFIER_NOT_SET(0);

        private final int value;

        ConfigSpecifierCase(int i) {
            this.value = i;
        }

        public static ConfigSpecifierCase forNumber(int i) {
            if (i == 0) {
                return CONFIGSPECIFIER_NOT_SET;
            }
            if (i == 4) {
                return SCOPED_ROUTE_CONFIGURATIONS_LIST;
            }
            if (i != 5) {
                return null;
            }
            return SCOPED_RDS;
        }

        @Deprecated
        public static ConfigSpecifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public interface ScopeKeyBuilderOrBuilder extends MessageOrBuilder {
        ScopeKeyBuilder.FragmentBuilder getFragments(int i);

        int getFragmentsCount();

        List<ScopeKeyBuilder.FragmentBuilder> getFragmentsList();

        ScopeKeyBuilder.FragmentBuilderOrBuilder getFragmentsOrBuilder(int i);

        List<? extends ScopeKeyBuilder.FragmentBuilderOrBuilder> getFragmentsOrBuilderList();
    }

    public static final class ScopeKeyBuilder extends GeneratedMessageV3 implements ScopeKeyBuilderOrBuilder {
        public static final int FRAGMENTS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final ScopeKeyBuilder DEFAULT_INSTANCE = new ScopeKeyBuilder();
        private static final Parser<ScopeKeyBuilder> PARSER = new AbstractParser<ScopeKeyBuilder>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public ScopeKeyBuilder m31420parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ScopeKeyBuilder(codedInputStream, extensionRegistryLite);
            }
        };
        private List<FragmentBuilder> fragments_;
        private byte memoizedIsInitialized;

        private ScopeKeyBuilder(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ScopeKeyBuilder() {
            this.memoizedIsInitialized = (byte) -1;
            this.fragments_ = Collections.emptyList();
        }

        private ScopeKeyBuilder(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.fragments_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.fragments_.add(codedInputStream.readMessage(FragmentBuilder.parser(), extensionRegistryLite));
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
                        this.fragments_ = Collections.unmodifiableList(this.fragments_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static ScopeKeyBuilder getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ScopeKeyBuilder> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_descriptor;
        }

        public static ScopeKeyBuilder parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ScopeKeyBuilder) PARSER.parseFrom(byteBuffer);
        }

        public static ScopeKeyBuilder parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ScopeKeyBuilder) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ScopeKeyBuilder parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ScopeKeyBuilder) PARSER.parseFrom(byteString);
        }

        public static ScopeKeyBuilder parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ScopeKeyBuilder) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ScopeKeyBuilder parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ScopeKeyBuilder) PARSER.parseFrom(bArr);
        }

        public static ScopeKeyBuilder parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ScopeKeyBuilder) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ScopeKeyBuilder parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ScopeKeyBuilder parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ScopeKeyBuilder parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ScopeKeyBuilder parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ScopeKeyBuilder parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ScopeKeyBuilder parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m31418toBuilder();
        }

        public static Builder newBuilder(ScopeKeyBuilder scopeKeyBuilder) {
            return DEFAULT_INSTANCE.m31418toBuilder().mergeFrom(scopeKeyBuilder);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ScopeKeyBuilder m31413getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilderOrBuilder
        public List<FragmentBuilder> getFragmentsList() {
            return this.fragments_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilderOrBuilder
        public List<? extends FragmentBuilderOrBuilder> getFragmentsOrBuilderList() {
            return this.fragments_;
        }

        public Parser<ScopeKeyBuilder> getParserForType() {
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
            return new ScopeKeyBuilder();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_fieldAccessorTable.ensureFieldAccessorsInitialized(ScopeKeyBuilder.class, Builder.class);
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilderOrBuilder
        public int getFragmentsCount() {
            return this.fragments_.size();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilderOrBuilder
        public FragmentBuilder getFragments(int i) {
            return this.fragments_.get(i);
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilderOrBuilder
        public FragmentBuilderOrBuilder getFragmentsOrBuilder(int i) {
            return this.fragments_.get(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.fragments_.size(); i++) {
                codedOutputStream.writeMessage(1, this.fragments_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = 0;
            for (int i2 = 0; i2 < this.fragments_.size(); i2++) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.fragments_.get(i2));
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ScopeKeyBuilder)) {
                return super.equals(obj);
            }
            ScopeKeyBuilder scopeKeyBuilder = (ScopeKeyBuilder) obj;
            return getFragmentsList().equals(scopeKeyBuilder.getFragmentsList()) && this.unknownFields.equals(scopeKeyBuilder.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (getFragmentsCount() > 0) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getFragmentsList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31415newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31418toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public interface FragmentBuilderOrBuilder extends MessageOrBuilder {
            FragmentBuilder.HeaderValueExtractor getHeaderValueExtractor();

            FragmentBuilder.HeaderValueExtractorOrBuilder getHeaderValueExtractorOrBuilder();

            FragmentBuilder.TypeCase getTypeCase();

            boolean hasHeaderValueExtractor();
        }

        public static final class FragmentBuilder extends GeneratedMessageV3 implements FragmentBuilderOrBuilder {
            public static final int HEADER_VALUE_EXTRACTOR_FIELD_NUMBER = 1;
            private static final long serialVersionUID = 0;
            private static final FragmentBuilder DEFAULT_INSTANCE = new FragmentBuilder();
            private static final Parser<FragmentBuilder> PARSER = new AbstractParser<FragmentBuilder>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public FragmentBuilder m31466parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new FragmentBuilder(codedInputStream, extensionRegistryLite);
                }
            };
            private byte memoizedIsInitialized;
            private int typeCase_;
            private Object type_;

            private FragmentBuilder(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.typeCase_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            private FragmentBuilder() {
                this.typeCase_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            private FragmentBuilder(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    HeaderValueExtractor.Builder builderM31510toBuilder = this.typeCase_ == 1 ? ((HeaderValueExtractor) this.type_).m31510toBuilder() : null;
                                    MessageLite message = codedInputStream.readMessage(HeaderValueExtractor.parser(), extensionRegistryLite);
                                    this.type_ = message;
                                    if (builderM31510toBuilder != null) {
                                        builderM31510toBuilder.mergeFrom((HeaderValueExtractor) message);
                                        this.type_ = builderM31510toBuilder.m31517buildPartial();
                                    }
                                    this.typeCase_ = 1;
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
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

            public static FragmentBuilder getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<FragmentBuilder> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_descriptor;
            }

            public static FragmentBuilder parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (FragmentBuilder) PARSER.parseFrom(byteBuffer);
            }

            public static FragmentBuilder parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (FragmentBuilder) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static FragmentBuilder parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (FragmentBuilder) PARSER.parseFrom(byteString);
            }

            public static FragmentBuilder parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (FragmentBuilder) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static FragmentBuilder parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (FragmentBuilder) PARSER.parseFrom(bArr);
            }

            public static FragmentBuilder parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (FragmentBuilder) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static FragmentBuilder parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static FragmentBuilder parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static FragmentBuilder parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static FragmentBuilder parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static FragmentBuilder parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static FragmentBuilder parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m31464toBuilder();
            }

            public static Builder newBuilder(FragmentBuilder fragmentBuilder) {
                return DEFAULT_INSTANCE.m31464toBuilder().mergeFrom(fragmentBuilder);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public FragmentBuilder m31459getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public Parser<FragmentBuilder> getParserForType() {
                return PARSER;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilderOrBuilder
            public boolean hasHeaderValueExtractor() {
                return this.typeCase_ == 1;
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
                return new FragmentBuilder();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_fieldAccessorTable.ensureFieldAccessorsInitialized(FragmentBuilder.class, Builder.class);
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilderOrBuilder
            public TypeCase getTypeCase() {
                return TypeCase.forNumber(this.typeCase_);
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilderOrBuilder
            public HeaderValueExtractor getHeaderValueExtractor() {
                if (this.typeCase_ == 1) {
                    return (HeaderValueExtractor) this.type_;
                }
                return HeaderValueExtractor.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilderOrBuilder
            public HeaderValueExtractorOrBuilder getHeaderValueExtractorOrBuilder() {
                if (this.typeCase_ == 1) {
                    return (HeaderValueExtractor) this.type_;
                }
                return HeaderValueExtractor.getDefaultInstance();
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (this.typeCase_ == 1) {
                    codedOutputStream.writeMessage(1, (HeaderValueExtractor) this.type_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeMessageSize = (this.typeCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (HeaderValueExtractor) this.type_) : 0) + this.unknownFields.getSerializedSize();
                this.memoizedSize = iComputeMessageSize;
                return iComputeMessageSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof FragmentBuilder)) {
                    return super.equals(obj);
                }
                FragmentBuilder fragmentBuilder = (FragmentBuilder) obj;
                if (getTypeCase().equals(fragmentBuilder.getTypeCase())) {
                    return (this.typeCase_ != 1 || getHeaderValueExtractor().equals(fragmentBuilder.getHeaderValueExtractor())) && this.unknownFields.equals(fragmentBuilder.unknownFields);
                }
                return false;
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = 779 + getDescriptor().hashCode();
                if (this.typeCase_ == 1) {
                    iHashCode = (((iHashCode * 37) + 1) * 53) + getHeaderValueExtractor().hashCode();
                }
                int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31461newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31464toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public enum TypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
                HEADER_VALUE_EXTRACTOR(1),
                TYPE_NOT_SET(0);

                private final int value;

                TypeCase(int i) {
                    this.value = i;
                }

                public static TypeCase forNumber(int i) {
                    if (i == 0) {
                        return TYPE_NOT_SET;
                    }
                    if (i != 1) {
                        return null;
                    }
                    return HEADER_VALUE_EXTRACTOR;
                }

                @Deprecated
                public static TypeCase valueOf(int i) {
                    return forNumber(i);
                }

                public int getNumber() {
                    return this.value;
                }
            }

            public interface HeaderValueExtractorOrBuilder extends MessageOrBuilder {
                HeaderValueExtractor.KvElement getElement();

                HeaderValueExtractor.KvElementOrBuilder getElementOrBuilder();

                String getElementSeparator();

                ByteString getElementSeparatorBytes();

                HeaderValueExtractor.ExtractTypeCase getExtractTypeCase();

                int getIndex();

                String getName();

                ByteString getNameBytes();

                boolean hasElement();
            }

            public static final class HeaderValueExtractor extends GeneratedMessageV3 implements HeaderValueExtractorOrBuilder {
                public static final int ELEMENT_FIELD_NUMBER = 4;
                public static final int ELEMENT_SEPARATOR_FIELD_NUMBER = 2;
                public static final int INDEX_FIELD_NUMBER = 3;
                public static final int NAME_FIELD_NUMBER = 1;
                private static final long serialVersionUID = 0;
                private static final HeaderValueExtractor DEFAULT_INSTANCE = new HeaderValueExtractor();
                private static final Parser<HeaderValueExtractor> PARSER = new AbstractParser<HeaderValueExtractor>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.1
                    /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                    public HeaderValueExtractor m31512parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                        return new HeaderValueExtractor(codedInputStream, extensionRegistryLite);
                    }
                };
                private volatile Object elementSeparator_;
                private int extractTypeCase_;
                private Object extractType_;
                private byte memoizedIsInitialized;
                private volatile Object name_;

                private HeaderValueExtractor(GeneratedMessageV3.Builder<?> builder) {
                    super(builder);
                    this.extractTypeCase_ = 0;
                    this.memoizedIsInitialized = (byte) -1;
                }

                private HeaderValueExtractor() {
                    this.extractTypeCase_ = 0;
                    this.memoizedIsInitialized = (byte) -1;
                    this.name_ = "";
                    this.elementSeparator_ = "";
                }

                private HeaderValueExtractor(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                            this.name_ = codedInputStream.readStringRequireUtf8();
                                        } else if (tag == 18) {
                                            this.elementSeparator_ = codedInputStream.readStringRequireUtf8();
                                        } else if (tag == 24) {
                                            this.extractTypeCase_ = 3;
                                            this.extractType_ = Integer.valueOf(codedInputStream.readUInt32());
                                        } else if (tag == 34) {
                                            KvElement.Builder builderM31556toBuilder = this.extractTypeCase_ == 4 ? ((KvElement) this.extractType_).m31556toBuilder() : null;
                                            MessageLite message = codedInputStream.readMessage(KvElement.parser(), extensionRegistryLite);
                                            this.extractType_ = message;
                                            if (builderM31556toBuilder != null) {
                                                builderM31556toBuilder.mergeFrom((KvElement) message);
                                                this.extractType_ = builderM31556toBuilder.m31563buildPartial();
                                            }
                                            this.extractTypeCase_ = 4;
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

                public static HeaderValueExtractor getDefaultInstance() {
                    return DEFAULT_INSTANCE;
                }

                public static Parser<HeaderValueExtractor> parser() {
                    return PARSER;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_descriptor;
                }

                public static HeaderValueExtractor parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                    return (HeaderValueExtractor) PARSER.parseFrom(byteBuffer);
                }

                public static HeaderValueExtractor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (HeaderValueExtractor) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
                }

                public static HeaderValueExtractor parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                    return (HeaderValueExtractor) PARSER.parseFrom(byteString);
                }

                public static HeaderValueExtractor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (HeaderValueExtractor) PARSER.parseFrom(byteString, extensionRegistryLite);
                }

                public static HeaderValueExtractor parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                    return (HeaderValueExtractor) PARSER.parseFrom(bArr);
                }

                public static HeaderValueExtractor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (HeaderValueExtractor) PARSER.parseFrom(bArr, extensionRegistryLite);
                }

                public static HeaderValueExtractor parseFrom(InputStream inputStream) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
                }

                public static HeaderValueExtractor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
                }

                public static HeaderValueExtractor parseDelimitedFrom(InputStream inputStream) throws IOException {
                    return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
                }

                public static HeaderValueExtractor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
                }

                public static HeaderValueExtractor parseFrom(CodedInputStream codedInputStream) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
                }

                public static HeaderValueExtractor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
                }

                public static Builder newBuilder() {
                    return DEFAULT_INSTANCE.m31510toBuilder();
                }

                public static Builder newBuilder(HeaderValueExtractor headerValueExtractor) {
                    return DEFAULT_INSTANCE.m31510toBuilder().mergeFrom(headerValueExtractor);
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public HeaderValueExtractor m31505getDefaultInstanceForType() {
                    return DEFAULT_INSTANCE;
                }

                public Parser<HeaderValueExtractor> getParserForType() {
                    return PARSER;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                public boolean hasElement() {
                    return this.extractTypeCase_ == 4;
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
                    return new HeaderValueExtractor();
                }

                public final UnknownFieldSet getUnknownFields() {
                    return this.unknownFields;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_fieldAccessorTable.ensureFieldAccessorsInitialized(HeaderValueExtractor.class, Builder.class);
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                public ExtractTypeCase getExtractTypeCase() {
                    return ExtractTypeCase.forNumber(this.extractTypeCase_);
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                public String getName() {
                    Object obj = this.name_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.name_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                public ByteString getNameBytes() {
                    Object obj = this.name_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.name_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                public String getElementSeparator() {
                    Object obj = this.elementSeparator_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.elementSeparator_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                public ByteString getElementSeparatorBytes() {
                    Object obj = this.elementSeparator_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.elementSeparator_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                public int getIndex() {
                    if (this.extractTypeCase_ == 3) {
                        return ((Integer) this.extractType_).intValue();
                    }
                    return 0;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                public KvElement getElement() {
                    if (this.extractTypeCase_ == 4) {
                        return (KvElement) this.extractType_;
                    }
                    return KvElement.getDefaultInstance();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                public KvElementOrBuilder getElementOrBuilder() {
                    if (this.extractTypeCase_ == 4) {
                        return (KvElement) this.extractType_;
                    }
                    return KvElement.getDefaultInstance();
                }

                public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                    if (!getNameBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
                    }
                    if (!getElementSeparatorBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 2, this.elementSeparator_);
                    }
                    if (this.extractTypeCase_ == 3) {
                        codedOutputStream.writeUInt32(3, ((Integer) this.extractType_).intValue());
                    }
                    if (this.extractTypeCase_ == 4) {
                        codedOutputStream.writeMessage(4, (KvElement) this.extractType_);
                    }
                    this.unknownFields.writeTo(codedOutputStream);
                }

                public int getSerializedSize() {
                    int i = this.memoizedSize;
                    if (i != -1) {
                        return i;
                    }
                    int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
                    if (!getElementSeparatorBytes().isEmpty()) {
                        iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.elementSeparator_);
                    }
                    if (this.extractTypeCase_ == 3) {
                        iComputeStringSize += CodedOutputStream.computeUInt32Size(3, ((Integer) this.extractType_).intValue());
                    }
                    if (this.extractTypeCase_ == 4) {
                        iComputeStringSize += CodedOutputStream.computeMessageSize(4, (KvElement) this.extractType_);
                    }
                    int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
                    this.memoizedSize = serializedSize;
                    return serializedSize;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof HeaderValueExtractor)) {
                        return super.equals(obj);
                    }
                    HeaderValueExtractor headerValueExtractor = (HeaderValueExtractor) obj;
                    if (!getName().equals(headerValueExtractor.getName()) || !getElementSeparator().equals(headerValueExtractor.getElementSeparator()) || !getExtractTypeCase().equals(headerValueExtractor.getExtractTypeCase())) {
                        return false;
                    }
                    int i = this.extractTypeCase_;
                    if (i == 3) {
                        if (getIndex() != headerValueExtractor.getIndex()) {
                            return false;
                        }
                    } else if (i == 4 && !getElement().equals(headerValueExtractor.getElement())) {
                        return false;
                    }
                    return this.unknownFields.equals(headerValueExtractor.unknownFields);
                }

                public int hashCode() {
                    int i;
                    int index;
                    if (this.memoizedHashCode != 0) {
                        return this.memoizedHashCode;
                    }
                    int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getElementSeparator().hashCode();
                    int i2 = this.extractTypeCase_;
                    if (i2 == 3) {
                        i = ((iHashCode * 37) + 3) * 53;
                        index = getIndex();
                    } else {
                        if (i2 == 4) {
                            i = ((iHashCode * 37) + 4) * 53;
                            index = getElement().hashCode();
                        }
                        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
                        this.memoizedHashCode = iHashCode2;
                        return iHashCode2;
                    }
                    iHashCode = i + index;
                    int iHashCode22 = (iHashCode * 29) + this.unknownFields.hashCode();
                    this.memoizedHashCode = iHashCode22;
                    return iHashCode22;
                }

                /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m31507newBuilderForType() {
                    return newBuilder();
                }

                /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m31510toBuilder() {
                    return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                    return new Builder(builderParent);
                }

                public enum ExtractTypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
                    INDEX(3),
                    ELEMENT(4),
                    EXTRACTTYPE_NOT_SET(0);

                    private final int value;

                    ExtractTypeCase(int i) {
                        this.value = i;
                    }

                    public static ExtractTypeCase forNumber(int i) {
                        if (i == 0) {
                            return EXTRACTTYPE_NOT_SET;
                        }
                        if (i == 3) {
                            return INDEX;
                        }
                        if (i != 4) {
                            return null;
                        }
                        return ELEMENT;
                    }

                    @Deprecated
                    public static ExtractTypeCase valueOf(int i) {
                        return forNumber(i);
                    }

                    public int getNumber() {
                        return this.value;
                    }
                }

                public interface KvElementOrBuilder extends MessageOrBuilder {
                    String getKey();

                    ByteString getKeyBytes();

                    String getSeparator();

                    ByteString getSeparatorBytes();
                }

                public static final class KvElement extends GeneratedMessageV3 implements KvElementOrBuilder {
                    public static final int KEY_FIELD_NUMBER = 2;
                    public static final int SEPARATOR_FIELD_NUMBER = 1;
                    private static final long serialVersionUID = 0;
                    private static final KvElement DEFAULT_INSTANCE = new KvElement();
                    private static final Parser<KvElement> PARSER = new AbstractParser<KvElement>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElement.1
                        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                        public KvElement m31558parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                            return new KvElement(codedInputStream, extensionRegistryLite);
                        }
                    };
                    private volatile Object key_;
                    private byte memoizedIsInitialized;
                    private volatile Object separator_;

                    private KvElement(GeneratedMessageV3.Builder<?> builder) {
                        super(builder);
                        this.memoizedIsInitialized = (byte) -1;
                    }

                    private KvElement() {
                        this.memoizedIsInitialized = (byte) -1;
                        this.separator_ = "";
                        this.key_ = "";
                    }

                    private KvElement(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                                this.separator_ = codedInputStream.readStringRequireUtf8();
                                            } else if (tag == 18) {
                                                this.key_ = codedInputStream.readStringRequireUtf8();
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

                    public static KvElement getDefaultInstance() {
                        return DEFAULT_INSTANCE;
                    }

                    public static Parser<KvElement> parser() {
                        return PARSER;
                    }

                    public static final Descriptors.Descriptor getDescriptor() {
                        return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_KvElement_descriptor;
                    }

                    public static KvElement parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                        return (KvElement) PARSER.parseFrom(byteBuffer);
                    }

                    public static KvElement parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                        return (KvElement) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
                    }

                    public static KvElement parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                        return (KvElement) PARSER.parseFrom(byteString);
                    }

                    public static KvElement parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                        return (KvElement) PARSER.parseFrom(byteString, extensionRegistryLite);
                    }

                    public static KvElement parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                        return (KvElement) PARSER.parseFrom(bArr);
                    }

                    public static KvElement parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                        return (KvElement) PARSER.parseFrom(bArr, extensionRegistryLite);
                    }

                    public static KvElement parseFrom(InputStream inputStream) throws IOException {
                        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
                    }

                    public static KvElement parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
                    }

                    public static KvElement parseDelimitedFrom(InputStream inputStream) throws IOException {
                        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
                    }

                    public static KvElement parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
                    }

                    public static KvElement parseFrom(CodedInputStream codedInputStream) throws IOException {
                        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
                    }

                    public static KvElement parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
                    }

                    public static Builder newBuilder() {
                        return DEFAULT_INSTANCE.m31556toBuilder();
                    }

                    public static Builder newBuilder(KvElement kvElement) {
                        return DEFAULT_INSTANCE.m31556toBuilder().mergeFrom(kvElement);
                    }

                    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public KvElement m31551getDefaultInstanceForType() {
                        return DEFAULT_INSTANCE;
                    }

                    public Parser<KvElement> getParserForType() {
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
                        return new KvElement();
                    }

                    public final UnknownFieldSet getUnknownFields() {
                        return this.unknownFields;
                    }

                    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                        return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_KvElement_fieldAccessorTable.ensureFieldAccessorsInitialized(KvElement.class, Builder.class);
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElementOrBuilder
                    public String getSeparator() {
                        Object obj = this.separator_;
                        if (obj instanceof String) {
                            return (String) obj;
                        }
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.separator_ = stringUtf8;
                        return stringUtf8;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElementOrBuilder
                    public ByteString getSeparatorBytes() {
                        Object obj = this.separator_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.separator_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElementOrBuilder
                    public String getKey() {
                        Object obj = this.key_;
                        if (obj instanceof String) {
                            return (String) obj;
                        }
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.key_ = stringUtf8;
                        return stringUtf8;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElementOrBuilder
                    public ByteString getKeyBytes() {
                        Object obj = this.key_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.key_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                        if (!getSeparatorBytes().isEmpty()) {
                            GeneratedMessageV3.writeString(codedOutputStream, 1, this.separator_);
                        }
                        if (!getKeyBytes().isEmpty()) {
                            GeneratedMessageV3.writeString(codedOutputStream, 2, this.key_);
                        }
                        this.unknownFields.writeTo(codedOutputStream);
                    }

                    public int getSerializedSize() {
                        int i = this.memoizedSize;
                        if (i != -1) {
                            return i;
                        }
                        int iComputeStringSize = !getSeparatorBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.separator_) : 0;
                        if (!getKeyBytes().isEmpty()) {
                            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.key_);
                        }
                        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
                        this.memoizedSize = serializedSize;
                        return serializedSize;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof KvElement)) {
                            return super.equals(obj);
                        }
                        KvElement kvElement = (KvElement) obj;
                        return getSeparator().equals(kvElement.getSeparator()) && getKey().equals(kvElement.getKey()) && this.unknownFields.equals(kvElement.unknownFields);
                    }

                    public int hashCode() {
                        if (this.memoizedHashCode != 0) {
                            return this.memoizedHashCode;
                        }
                        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSeparator().hashCode()) * 37) + 2) * 53) + getKey().hashCode()) * 29) + this.unknownFields.hashCode();
                        this.memoizedHashCode = iHashCode;
                        return iHashCode;
                    }

                    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m31553newBuilderForType() {
                        return newBuilder();
                    }

                    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m31556toBuilder() {
                        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                        return new Builder(builderParent);
                    }

                    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements KvElementOrBuilder {
                        private Object key_;
                        private Object separator_;

                        private Builder() {
                            this.separator_ = "";
                            this.key_ = "";
                            maybeForceBuilderInitialization();
                        }

                        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                            super(builderParent);
                            this.separator_ = "";
                            this.key_ = "";
                            maybeForceBuilderInitialization();
                        }

                        public static final Descriptors.Descriptor getDescriptor() {
                            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_KvElement_descriptor;
                        }

                        public final boolean isInitialized() {
                            return true;
                        }

                        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_KvElement_fieldAccessorTable.ensureFieldAccessorsInitialized(KvElement.class, Builder.class);
                        }

                        private void maybeForceBuilderInitialization() {
                            boolean unused = KvElement.alwaysUseFieldBuilders;
                        }

                        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                        public Builder m31567clear() {
                            super.clear();
                            this.separator_ = "";
                            this.key_ = "";
                            return this;
                        }

                        public Descriptors.Descriptor getDescriptorForType() {
                            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_KvElement_descriptor;
                        }

                        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                        public KvElement m31580getDefaultInstanceForType() {
                            return KvElement.getDefaultInstance();
                        }

                        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                        public KvElement m31561build() throws UninitializedMessageException {
                            KvElement kvElementM31563buildPartial = m31563buildPartial();
                            if (kvElementM31563buildPartial.isInitialized()) {
                                return kvElementM31563buildPartial;
                            }
                            throw newUninitializedMessageException(kvElementM31563buildPartial);
                        }

                        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                        public KvElement m31563buildPartial() {
                            KvElement kvElement = new KvElement(this);
                            kvElement.separator_ = this.separator_;
                            kvElement.key_ = this.key_;
                            onBuilt();
                            return kvElement;
                        }

                        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                        public Builder m31579clone() {
                            return (Builder) super.clone();
                        }

                        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                        public Builder m31591setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                            return (Builder) super.setField(fieldDescriptor, obj);
                        }

                        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                        public Builder m31569clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                            return (Builder) super.clearField(fieldDescriptor);
                        }

                        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                        public Builder m31572clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                            return (Builder) super.clearOneof(oneofDescriptor);
                        }

                        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                        public Builder m31593setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                        }

                        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                        public Builder m31559addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                        }

                        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                        public Builder m31584mergeFrom(Message message) {
                            if (message instanceof KvElement) {
                                return mergeFrom((KvElement) message);
                            }
                            super.mergeFrom(message);
                            return this;
                        }

                        public Builder mergeFrom(KvElement kvElement) {
                            if (kvElement == KvElement.getDefaultInstance()) {
                                return this;
                            }
                            if (!kvElement.getSeparator().isEmpty()) {
                                this.separator_ = kvElement.separator_;
                                onChanged();
                            }
                            if (!kvElement.getKey().isEmpty()) {
                                this.key_ = kvElement.key_;
                                onChanged();
                            }
                            m31589mergeUnknownFields(kvElement.unknownFields);
                            onChanged();
                            return this;
                        }

                        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElement.Builder m31585mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                            /*
                                r2 = this;
                                r0 = 0
                                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElement.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$HeaderValueExtractor$KvElement r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElement) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$HeaderValueExtractor$KvElement r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElement) r4     // Catch: java.lang.Throwable -> L11
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
                            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElement.Builder.m31585mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$HeaderValueExtractor$KvElement$Builder");
                        }

                        @Override
                        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElementOrBuilder
                        public String getSeparator() {
                            Object obj = this.separator_;
                            if (!(obj instanceof String)) {
                                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                                this.separator_ = stringUtf8;
                                return stringUtf8;
                            }
                            return (String) obj;
                        }

                        public Builder setSeparator(String str) {
                            str.getClass();
                            this.separator_ = str;
                            onChanged();
                            return this;
                        }

                        @Override
                        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElementOrBuilder
                        public ByteString getSeparatorBytes() {
                            Object obj = this.separator_;
                            if (obj instanceof String) {
                                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                                this.separator_ = byteStringCopyFromUtf8;
                                return byteStringCopyFromUtf8;
                            }
                            return (ByteString) obj;
                        }

                        public Builder setSeparatorBytes(ByteString byteString) {
                            byteString.getClass();
                            KvElement.checkByteStringIsUtf8(byteString);
                            this.separator_ = byteString;
                            onChanged();
                            return this;
                        }

                        public Builder clearSeparator() {
                            this.separator_ = KvElement.getDefaultInstance().getSeparator();
                            onChanged();
                            return this;
                        }

                        @Override
                        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElementOrBuilder
                        public String getKey() {
                            Object obj = this.key_;
                            if (!(obj instanceof String)) {
                                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                                this.key_ = stringUtf8;
                                return stringUtf8;
                            }
                            return (String) obj;
                        }

                        public Builder setKey(String str) {
                            str.getClass();
                            this.key_ = str;
                            onChanged();
                            return this;
                        }

                        @Override
                        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElementOrBuilder
                        public ByteString getKeyBytes() {
                            Object obj = this.key_;
                            if (obj instanceof String) {
                                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                                this.key_ = byteStringCopyFromUtf8;
                                return byteStringCopyFromUtf8;
                            }
                            return (ByteString) obj;
                        }

                        public Builder setKeyBytes(ByteString byteString) {
                            byteString.getClass();
                            KvElement.checkByteStringIsUtf8(byteString);
                            this.key_ = byteString;
                            onChanged();
                            return this;
                        }

                        public Builder clearKey() {
                            this.key_ = KvElement.getDefaultInstance().getKey();
                            onChanged();
                            return this;
                        }

                        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                        public final Builder m31595setUnknownFields(UnknownFieldSet unknownFieldSet) {
                            return (Builder) super.setUnknownFields(unknownFieldSet);
                        }

                        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                        public final Builder m31589mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                            return (Builder) super.mergeUnknownFields(unknownFieldSet);
                        }
                    }
                }

                public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HeaderValueExtractorOrBuilder {
                    private SingleFieldBuilderV3<KvElement, KvElement.Builder, KvElementOrBuilder> elementBuilder_;
                    private Object elementSeparator_;
                    private int extractTypeCase_;
                    private Object extractType_;
                    private Object name_;

                    private Builder() {
                        this.extractTypeCase_ = 0;
                        this.name_ = "";
                        this.elementSeparator_ = "";
                        maybeForceBuilderInitialization();
                    }

                    private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                        super(builderParent);
                        this.extractTypeCase_ = 0;
                        this.name_ = "";
                        this.elementSeparator_ = "";
                        maybeForceBuilderInitialization();
                    }

                    public static final Descriptors.Descriptor getDescriptor() {
                        return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_descriptor;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                    public boolean hasElement() {
                        return this.extractTypeCase_ == 4;
                    }

                    public final boolean isInitialized() {
                        return true;
                    }

                    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                        return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_fieldAccessorTable.ensureFieldAccessorsInitialized(HeaderValueExtractor.class, Builder.class);
                    }

                    private void maybeForceBuilderInitialization() {
                        boolean unused = HeaderValueExtractor.alwaysUseFieldBuilders;
                    }

                    /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m31521clear() {
                        super.clear();
                        this.name_ = "";
                        this.elementSeparator_ = "";
                        this.extractTypeCase_ = 0;
                        this.extractType_ = null;
                        return this;
                    }

                    public Descriptors.Descriptor getDescriptorForType() {
                        return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_descriptor;
                    }

                    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public HeaderValueExtractor m31534getDefaultInstanceForType() {
                        return HeaderValueExtractor.getDefaultInstance();
                    }

                    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                    /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public HeaderValueExtractor m31515build() throws UninitializedMessageException {
                        HeaderValueExtractor headerValueExtractorM31517buildPartial = m31517buildPartial();
                        if (headerValueExtractorM31517buildPartial.isInitialized()) {
                            return headerValueExtractorM31517buildPartial;
                        }
                        throw newUninitializedMessageException(headerValueExtractorM31517buildPartial);
                    }

                    /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public HeaderValueExtractor m31517buildPartial() {
                        HeaderValueExtractor headerValueExtractor = new HeaderValueExtractor(this);
                        headerValueExtractor.name_ = this.name_;
                        headerValueExtractor.elementSeparator_ = this.elementSeparator_;
                        if (this.extractTypeCase_ == 3) {
                            headerValueExtractor.extractType_ = this.extractType_;
                        }
                        if (this.extractTypeCase_ == 4) {
                            SingleFieldBuilderV3<KvElement, KvElement.Builder, KvElementOrBuilder> singleFieldBuilderV3 = this.elementBuilder_;
                            if (singleFieldBuilderV3 == null) {
                                headerValueExtractor.extractType_ = this.extractType_;
                            } else {
                                headerValueExtractor.extractType_ = singleFieldBuilderV3.build();
                            }
                        }
                        headerValueExtractor.extractTypeCase_ = this.extractTypeCase_;
                        onBuilt();
                        return headerValueExtractor;
                    }

                    /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m31533clone() {
                        return (Builder) super.clone();
                    }

                    /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m31545setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                        return (Builder) super.setField(fieldDescriptor, obj);
                    }

                    /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m31523clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                        return (Builder) super.clearField(fieldDescriptor);
                    }

                    /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m31526clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                        return (Builder) super.clearOneof(oneofDescriptor);
                    }

                    /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m31547setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                        return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                    }

                    /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m31513addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                        return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                    }

                    /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m31538mergeFrom(Message message) {
                        if (message instanceof HeaderValueExtractor) {
                            return mergeFrom((HeaderValueExtractor) message);
                        }
                        super.mergeFrom(message);
                        return this;
                    }

                    public Builder mergeFrom(HeaderValueExtractor headerValueExtractor) {
                        if (headerValueExtractor == HeaderValueExtractor.getDefaultInstance()) {
                            return this;
                        }
                        if (!headerValueExtractor.getName().isEmpty()) {
                            this.name_ = headerValueExtractor.name_;
                            onChanged();
                        }
                        if (!headerValueExtractor.getElementSeparator().isEmpty()) {
                            this.elementSeparator_ = headerValueExtractor.elementSeparator_;
                            onChanged();
                        }
                        int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$HeaderValueExtractor$ExtractTypeCase[headerValueExtractor.getExtractTypeCase().ordinal()];
                        if (i == 1) {
                            setIndex(headerValueExtractor.getIndex());
                        } else if (i == 2) {
                            mergeElement(headerValueExtractor.getElement());
                        }
                        m31543mergeUnknownFields(headerValueExtractor.unknownFields);
                        onChanged();
                        return this;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                    /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.Builder m31539mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                        /*
                            r2 = this;
                            r0 = 0
                            com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.access$2000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                            java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                            io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$HeaderValueExtractor r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                            io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$HeaderValueExtractor r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor) r4     // Catch: java.lang.Throwable -> L11
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
                        throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.Builder.m31539mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$HeaderValueExtractor$Builder");
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                    public ExtractTypeCase getExtractTypeCase() {
                        return ExtractTypeCase.forNumber(this.extractTypeCase_);
                    }

                    public Builder clearExtractType() {
                        this.extractTypeCase_ = 0;
                        this.extractType_ = null;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
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

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
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
                        HeaderValueExtractor.checkByteStringIsUtf8(byteString);
                        this.name_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearName() {
                        this.name_ = HeaderValueExtractor.getDefaultInstance().getName();
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                    public String getElementSeparator() {
                        Object obj = this.elementSeparator_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.elementSeparator_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    public Builder setElementSeparator(String str) {
                        str.getClass();
                        this.elementSeparator_ = str;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                    public ByteString getElementSeparatorBytes() {
                        Object obj = this.elementSeparator_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.elementSeparator_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setElementSeparatorBytes(ByteString byteString) {
                        byteString.getClass();
                        HeaderValueExtractor.checkByteStringIsUtf8(byteString);
                        this.elementSeparator_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearElementSeparator() {
                        this.elementSeparator_ = HeaderValueExtractor.getDefaultInstance().getElementSeparator();
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                    public int getIndex() {
                        if (this.extractTypeCase_ == 3) {
                            return ((Integer) this.extractType_).intValue();
                        }
                        return 0;
                    }

                    public Builder setIndex(int i) {
                        this.extractTypeCase_ = 3;
                        this.extractType_ = Integer.valueOf(i);
                        onChanged();
                        return this;
                    }

                    public Builder clearIndex() {
                        if (this.extractTypeCase_ == 3) {
                            this.extractTypeCase_ = 0;
                            this.extractType_ = null;
                            onChanged();
                        }
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                    public KvElement getElement() {
                        SingleFieldBuilderV3<KvElement, KvElement.Builder, KvElementOrBuilder> singleFieldBuilderV3 = this.elementBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            if (this.extractTypeCase_ == 4) {
                                return (KvElement) this.extractType_;
                            }
                            return KvElement.getDefaultInstance();
                        }
                        if (this.extractTypeCase_ == 4) {
                            return singleFieldBuilderV3.getMessage();
                        }
                        return KvElement.getDefaultInstance();
                    }

                    public Builder setElement(KvElement kvElement) {
                        SingleFieldBuilderV3<KvElement, KvElement.Builder, KvElementOrBuilder> singleFieldBuilderV3 = this.elementBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            kvElement.getClass();
                            this.extractType_ = kvElement;
                            onChanged();
                        } else {
                            singleFieldBuilderV3.setMessage(kvElement);
                        }
                        this.extractTypeCase_ = 4;
                        return this;
                    }

                    public Builder setElement(KvElement.Builder builder) {
                        SingleFieldBuilderV3<KvElement, KvElement.Builder, KvElementOrBuilder> singleFieldBuilderV3 = this.elementBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            this.extractType_ = builder.m31561build();
                            onChanged();
                        } else {
                            singleFieldBuilderV3.setMessage(builder.m31561build());
                        }
                        this.extractTypeCase_ = 4;
                        return this;
                    }

                    public Builder mergeElement(KvElement kvElement) {
                        SingleFieldBuilderV3<KvElement, KvElement.Builder, KvElementOrBuilder> singleFieldBuilderV3 = this.elementBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            if (this.extractTypeCase_ != 4 || this.extractType_ == KvElement.getDefaultInstance()) {
                                this.extractType_ = kvElement;
                            } else {
                                this.extractType_ = KvElement.newBuilder((KvElement) this.extractType_).mergeFrom(kvElement).m31563buildPartial();
                            }
                            onChanged();
                        } else {
                            if (this.extractTypeCase_ == 4) {
                                singleFieldBuilderV3.mergeFrom(kvElement);
                            }
                            this.elementBuilder_.setMessage(kvElement);
                        }
                        this.extractTypeCase_ = 4;
                        return this;
                    }

                    public Builder clearElement() {
                        SingleFieldBuilderV3<KvElement, KvElement.Builder, KvElementOrBuilder> singleFieldBuilderV3 = this.elementBuilder_;
                        if (singleFieldBuilderV3 != null) {
                            if (this.extractTypeCase_ == 4) {
                                this.extractTypeCase_ = 0;
                                this.extractType_ = null;
                            }
                            singleFieldBuilderV3.clear();
                        } else if (this.extractTypeCase_ == 4) {
                            this.extractTypeCase_ = 0;
                            this.extractType_ = null;
                            onChanged();
                        }
                        return this;
                    }

                    public KvElement.Builder getElementBuilder() {
                        return getElementFieldBuilder().getBuilder();
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorOrBuilder
                    public KvElementOrBuilder getElementOrBuilder() {
                        SingleFieldBuilderV3<KvElement, KvElement.Builder, KvElementOrBuilder> singleFieldBuilderV3;
                        int i = this.extractTypeCase_;
                        if (i == 4 && (singleFieldBuilderV3 = this.elementBuilder_) != null) {
                            return (KvElementOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                        }
                        if (i == 4) {
                            return (KvElement) this.extractType_;
                        }
                        return KvElement.getDefaultInstance();
                    }

                    private SingleFieldBuilderV3<KvElement, KvElement.Builder, KvElementOrBuilder> getElementFieldBuilder() {
                        if (this.elementBuilder_ == null) {
                            if (this.extractTypeCase_ != 4) {
                                this.extractType_ = KvElement.getDefaultInstance();
                            }
                            this.elementBuilder_ = new SingleFieldBuilderV3<>((KvElement) this.extractType_, getParentForChildren(), isClean());
                            this.extractType_ = null;
                        }
                        this.extractTypeCase_ = 4;
                        onChanged();
                        return this.elementBuilder_;
                    }

                    /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public final Builder m31549setUnknownFields(UnknownFieldSet unknownFieldSet) {
                        return (Builder) super.setUnknownFields(unknownFieldSet);
                    }

                    /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public final Builder m31543mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                        return (Builder) super.mergeUnknownFields(unknownFieldSet);
                    }
                }
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FragmentBuilderOrBuilder {
                private SingleFieldBuilderV3<HeaderValueExtractor, HeaderValueExtractor.Builder, HeaderValueExtractorOrBuilder> headerValueExtractorBuilder_;
                private int typeCase_;
                private Object type_;

                private Builder() {
                    this.typeCase_ = 0;
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.typeCase_ = 0;
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_descriptor;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilderOrBuilder
                public boolean hasHeaderValueExtractor() {
                    return this.typeCase_ == 1;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_fieldAccessorTable.ensureFieldAccessorsInitialized(FragmentBuilder.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = FragmentBuilder.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m31475clear() {
                    super.clear();
                    this.typeCase_ = 0;
                    this.type_ = null;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public FragmentBuilder m31488getDefaultInstanceForType() {
                    return FragmentBuilder.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public FragmentBuilder m31469build() throws UninitializedMessageException {
                    FragmentBuilder fragmentBuilderM31471buildPartial = m31471buildPartial();
                    if (fragmentBuilderM31471buildPartial.isInitialized()) {
                        return fragmentBuilderM31471buildPartial;
                    }
                    throw newUninitializedMessageException(fragmentBuilderM31471buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public FragmentBuilder m31471buildPartial() {
                    FragmentBuilder fragmentBuilder = new FragmentBuilder(this);
                    if (this.typeCase_ == 1) {
                        SingleFieldBuilderV3<HeaderValueExtractor, HeaderValueExtractor.Builder, HeaderValueExtractorOrBuilder> singleFieldBuilderV3 = this.headerValueExtractorBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            fragmentBuilder.type_ = this.type_;
                        } else {
                            fragmentBuilder.type_ = singleFieldBuilderV3.build();
                        }
                    }
                    fragmentBuilder.typeCase_ = this.typeCase_;
                    onBuilt();
                    return fragmentBuilder;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m31487clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m31499setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m31477clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m31480clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m31501setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m31467addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m31492mergeFrom(Message message) {
                    if (message instanceof FragmentBuilder) {
                        return mergeFrom((FragmentBuilder) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(FragmentBuilder fragmentBuilder) {
                    if (fragmentBuilder == FragmentBuilder.getDefaultInstance()) {
                        return this;
                    }
                    if (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$TypeCase[fragmentBuilder.getTypeCase().ordinal()] == 1) {
                        mergeHeaderValueExtractor(fragmentBuilder.getHeaderValueExtractor());
                    }
                    m31497mergeUnknownFields(fragmentBuilder.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.Builder m31493mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.access$3100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes$ScopeKeyBuilder$FragmentBuilder r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes$ScopeKeyBuilder$FragmentBuilder r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.Builder.m31493mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$Builder");
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilderOrBuilder
                public TypeCase getTypeCase() {
                    return TypeCase.forNumber(this.typeCase_);
                }

                public Builder clearType() {
                    this.typeCase_ = 0;
                    this.type_ = null;
                    onChanged();
                    return this;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilderOrBuilder
                public HeaderValueExtractor getHeaderValueExtractor() {
                    SingleFieldBuilderV3<HeaderValueExtractor, HeaderValueExtractor.Builder, HeaderValueExtractorOrBuilder> singleFieldBuilderV3 = this.headerValueExtractorBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.typeCase_ == 1) {
                            return (HeaderValueExtractor) this.type_;
                        }
                        return HeaderValueExtractor.getDefaultInstance();
                    }
                    if (this.typeCase_ == 1) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    return HeaderValueExtractor.getDefaultInstance();
                }

                public Builder setHeaderValueExtractor(HeaderValueExtractor headerValueExtractor) {
                    SingleFieldBuilderV3<HeaderValueExtractor, HeaderValueExtractor.Builder, HeaderValueExtractorOrBuilder> singleFieldBuilderV3 = this.headerValueExtractorBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        headerValueExtractor.getClass();
                        this.type_ = headerValueExtractor;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(headerValueExtractor);
                    }
                    this.typeCase_ = 1;
                    return this;
                }

                public Builder setHeaderValueExtractor(HeaderValueExtractor.Builder builder) {
                    SingleFieldBuilderV3<HeaderValueExtractor, HeaderValueExtractor.Builder, HeaderValueExtractorOrBuilder> singleFieldBuilderV3 = this.headerValueExtractorBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.type_ = builder.m31515build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m31515build());
                    }
                    this.typeCase_ = 1;
                    return this;
                }

                public Builder mergeHeaderValueExtractor(HeaderValueExtractor headerValueExtractor) {
                    SingleFieldBuilderV3<HeaderValueExtractor, HeaderValueExtractor.Builder, HeaderValueExtractorOrBuilder> singleFieldBuilderV3 = this.headerValueExtractorBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.typeCase_ != 1 || this.type_ == HeaderValueExtractor.getDefaultInstance()) {
                            this.type_ = headerValueExtractor;
                        } else {
                            this.type_ = HeaderValueExtractor.newBuilder((HeaderValueExtractor) this.type_).mergeFrom(headerValueExtractor).m31517buildPartial();
                        }
                        onChanged();
                    } else {
                        if (this.typeCase_ == 1) {
                            singleFieldBuilderV3.mergeFrom(headerValueExtractor);
                        }
                        this.headerValueExtractorBuilder_.setMessage(headerValueExtractor);
                    }
                    this.typeCase_ = 1;
                    return this;
                }

                public Builder clearHeaderValueExtractor() {
                    SingleFieldBuilderV3<HeaderValueExtractor, HeaderValueExtractor.Builder, HeaderValueExtractorOrBuilder> singleFieldBuilderV3 = this.headerValueExtractorBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        if (this.typeCase_ == 1) {
                            this.typeCase_ = 0;
                            this.type_ = null;
                        }
                        singleFieldBuilderV3.clear();
                    } else if (this.typeCase_ == 1) {
                        this.typeCase_ = 0;
                        this.type_ = null;
                        onChanged();
                    }
                    return this;
                }

                public HeaderValueExtractor.Builder getHeaderValueExtractorBuilder() {
                    return getHeaderValueExtractorFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilderOrBuilder
                public HeaderValueExtractorOrBuilder getHeaderValueExtractorOrBuilder() {
                    SingleFieldBuilderV3<HeaderValueExtractor, HeaderValueExtractor.Builder, HeaderValueExtractorOrBuilder> singleFieldBuilderV3;
                    int i = this.typeCase_;
                    if (i == 1 && (singleFieldBuilderV3 = this.headerValueExtractorBuilder_) != null) {
                        return (HeaderValueExtractorOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    if (i == 1) {
                        return (HeaderValueExtractor) this.type_;
                    }
                    return HeaderValueExtractor.getDefaultInstance();
                }

                private SingleFieldBuilderV3<HeaderValueExtractor, HeaderValueExtractor.Builder, HeaderValueExtractorOrBuilder> getHeaderValueExtractorFieldBuilder() {
                    if (this.headerValueExtractorBuilder_ == null) {
                        if (this.typeCase_ != 1) {
                            this.type_ = HeaderValueExtractor.getDefaultInstance();
                        }
                        this.headerValueExtractorBuilder_ = new SingleFieldBuilderV3<>((HeaderValueExtractor) this.type_, getParentForChildren(), isClean());
                        this.type_ = null;
                    }
                    this.typeCase_ = 1;
                    onChanged();
                    return this.headerValueExtractorBuilder_;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m31503setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m31497mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ScopeKeyBuilderOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> fragmentsBuilder_;
            private List<FragmentBuilder> fragments_;

            private Builder() {
                this.fragments_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.fragments_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_fieldAccessorTable.ensureFieldAccessorsInitialized(ScopeKeyBuilder.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (ScopeKeyBuilder.alwaysUseFieldBuilders) {
                    getFragmentsFieldBuilder();
                }
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31429clear() {
                super.clear();
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.fragments_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ScopeKeyBuilder m31442getDefaultInstanceForType() {
                return ScopeKeyBuilder.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ScopeKeyBuilder m31423build() throws UninitializedMessageException {
                ScopeKeyBuilder scopeKeyBuilderM31425buildPartial = m31425buildPartial();
                if (scopeKeyBuilderM31425buildPartial.isInitialized()) {
                    return scopeKeyBuilderM31425buildPartial;
                }
                throw newUninitializedMessageException(scopeKeyBuilderM31425buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ScopeKeyBuilder m31425buildPartial() {
                ScopeKeyBuilder scopeKeyBuilder = new ScopeKeyBuilder(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.fragments_ = Collections.unmodifiableList(this.fragments_);
                        this.bitField0_ &= -2;
                    }
                    scopeKeyBuilder.fragments_ = this.fragments_;
                } else {
                    scopeKeyBuilder.fragments_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return scopeKeyBuilder;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31441clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31453setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31431clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31434clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31455setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31421addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m31446mergeFrom(Message message) {
                if (message instanceof ScopeKeyBuilder) {
                    return mergeFrom((ScopeKeyBuilder) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ScopeKeyBuilder scopeKeyBuilder) {
                if (scopeKeyBuilder == ScopeKeyBuilder.getDefaultInstance()) {
                    return this;
                }
                if (this.fragmentsBuilder_ == null) {
                    if (!scopeKeyBuilder.fragments_.isEmpty()) {
                        if (this.fragments_.isEmpty()) {
                            this.fragments_ = scopeKeyBuilder.fragments_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureFragmentsIsMutable();
                            this.fragments_.addAll(scopeKeyBuilder.fragments_);
                        }
                        onChanged();
                    }
                } else if (!scopeKeyBuilder.fragments_.isEmpty()) {
                    if (!this.fragmentsBuilder_.isEmpty()) {
                        this.fragmentsBuilder_.addAllMessages(scopeKeyBuilder.fragments_);
                    } else {
                        this.fragmentsBuilder_.dispose();
                        this.fragmentsBuilder_ = null;
                        this.fragments_ = scopeKeyBuilder.fragments_;
                        this.bitField0_ &= -2;
                        this.fragmentsBuilder_ = ScopeKeyBuilder.alwaysUseFieldBuilders ? getFragmentsFieldBuilder() : null;
                    }
                }
                m31451mergeUnknownFields(scopeKeyBuilder.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.Builder m31447mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.access$4000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes$ScopeKeyBuilder r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes$ScopeKeyBuilder r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.Builder.m31447mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes$ScopeKeyBuilder$Builder");
            }

            private void ensureFragmentsIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.fragments_ = new ArrayList(this.fragments_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilderOrBuilder
            public List<FragmentBuilder> getFragmentsList() {
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.fragments_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilderOrBuilder
            public int getFragmentsCount() {
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.fragments_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilderOrBuilder
            public FragmentBuilder getFragments(int i) {
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.fragments_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setFragments(int i, FragmentBuilder fragmentBuilder) {
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    fragmentBuilder.getClass();
                    ensureFragmentsIsMutable();
                    this.fragments_.set(i, fragmentBuilder);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, fragmentBuilder);
                }
                return this;
            }

            public Builder setFragments(int i, FragmentBuilder.Builder builder) {
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFragmentsIsMutable();
                    this.fragments_.set(i, builder.m31469build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.m31469build());
                }
                return this;
            }

            public Builder addFragments(FragmentBuilder fragmentBuilder) {
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    fragmentBuilder.getClass();
                    ensureFragmentsIsMutable();
                    this.fragments_.add(fragmentBuilder);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(fragmentBuilder);
                }
                return this;
            }

            public Builder addFragments(int i, FragmentBuilder fragmentBuilder) {
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    fragmentBuilder.getClass();
                    ensureFragmentsIsMutable();
                    this.fragments_.add(i, fragmentBuilder);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, fragmentBuilder);
                }
                return this;
            }

            public Builder addFragments(FragmentBuilder.Builder builder) {
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFragmentsIsMutable();
                    this.fragments_.add(builder.m31469build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.m31469build());
                }
                return this;
            }

            public Builder addFragments(int i, FragmentBuilder.Builder builder) {
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFragmentsIsMutable();
                    this.fragments_.add(i, builder.m31469build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.m31469build());
                }
                return this;
            }

            public Builder addAllFragments(Iterable<? extends FragmentBuilder> iterable) {
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFragmentsIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.fragments_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearFragments() {
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.fragments_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeFragments(int i) {
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFragmentsIsMutable();
                    this.fragments_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public FragmentBuilder.Builder getFragmentsBuilder(int i) {
                return getFragmentsFieldBuilder().getBuilder(i);
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilderOrBuilder
            public FragmentBuilderOrBuilder getFragmentsOrBuilder(int i) {
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.fragments_.get(i);
                }
                return (FragmentBuilderOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilderOrBuilder
            public List<? extends FragmentBuilderOrBuilder> getFragmentsOrBuilderList() {
                RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.fragments_);
            }

            public FragmentBuilder.Builder addFragmentsBuilder() {
                return getFragmentsFieldBuilder().addBuilder(FragmentBuilder.getDefaultInstance());
            }

            public FragmentBuilder.Builder addFragmentsBuilder(int i) {
                return getFragmentsFieldBuilder().addBuilder(i, FragmentBuilder.getDefaultInstance());
            }

            public List<FragmentBuilder.Builder> getFragmentsBuilderList() {
                return getFragmentsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<FragmentBuilder, FragmentBuilder.Builder, FragmentBuilderOrBuilder> getFragmentsFieldBuilder() {
                if (this.fragmentsBuilder_ == null) {
                    this.fragmentsBuilder_ = new RepeatedFieldBuilderV3<>(this.fragments_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.fragments_ = null;
                }
                return this.fragmentsBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m31457setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m31451mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ScopedRoutesOrBuilder {
        private int configSpecifierCase_;
        private Object configSpecifier_;
        private Object name_;
        private SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> rdsConfigSourceBuilder_;
        private ConfigSource rdsConfigSource_;
        private SingleFieldBuilderV3<ScopeKeyBuilder, ScopeKeyBuilder.Builder, ScopeKeyBuilderOrBuilder> scopeKeyBuilderBuilder_;
        private ScopeKeyBuilder scopeKeyBuilder_;
        private SingleFieldBuilderV3<ScopedRds, ScopedRds.Builder, ScopedRdsOrBuilder> scopedRdsBuilder_;
        private SingleFieldBuilderV3<ScopedRouteConfigurationsList, ScopedRouteConfigurationsList.Builder, ScopedRouteConfigurationsListOrBuilder> scopedRouteConfigurationsListBuilder_;

        private Builder() {
            this.configSpecifierCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.configSpecifierCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_descriptor;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
        public boolean hasRdsConfigSource() {
            return (this.rdsConfigSourceBuilder_ == null && this.rdsConfigSource_ == null) ? false : true;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
        public boolean hasScopeKeyBuilder() {
            return (this.scopeKeyBuilderBuilder_ == null && this.scopeKeyBuilder_ == null) ? false : true;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
        public boolean hasScopedRds() {
            return this.configSpecifierCase_ == 5;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
        public boolean hasScopedRouteConfigurationsList() {
            return this.configSpecifierCase_ == 4;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_fieldAccessorTable.ensureFieldAccessorsInitialized(ScopedRoutes.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ScopedRoutes.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31383clear() {
            super.clear();
            this.name_ = "";
            if (this.scopeKeyBuilderBuilder_ == null) {
                this.scopeKeyBuilder_ = null;
            } else {
                this.scopeKeyBuilder_ = null;
                this.scopeKeyBuilderBuilder_ = null;
            }
            if (this.rdsConfigSourceBuilder_ == null) {
                this.rdsConfigSource_ = null;
            } else {
                this.rdsConfigSource_ = null;
                this.rdsConfigSourceBuilder_ = null;
            }
            this.configSpecifierCase_ = 0;
            this.configSpecifier_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ScopedRoutes m31396getDefaultInstanceForType() {
            return ScopedRoutes.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ScopedRoutes m31377build() throws UninitializedMessageException {
            ScopedRoutes scopedRoutesM31379buildPartial = m31379buildPartial();
            if (scopedRoutesM31379buildPartial.isInitialized()) {
                return scopedRoutesM31379buildPartial;
            }
            throw newUninitializedMessageException(scopedRoutesM31379buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ScopedRoutes m31379buildPartial() {
            ScopedRoutes scopedRoutes = new ScopedRoutes(this);
            scopedRoutes.name_ = this.name_;
            SingleFieldBuilderV3<ScopeKeyBuilder, ScopeKeyBuilder.Builder, ScopeKeyBuilderOrBuilder> singleFieldBuilderV3 = this.scopeKeyBuilderBuilder_;
            if (singleFieldBuilderV3 == null) {
                scopedRoutes.scopeKeyBuilder_ = this.scopeKeyBuilder_;
            } else {
                scopedRoutes.scopeKeyBuilder_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV32 = this.rdsConfigSourceBuilder_;
            if (singleFieldBuilderV32 == null) {
                scopedRoutes.rdsConfigSource_ = this.rdsConfigSource_;
            } else {
                scopedRoutes.rdsConfigSource_ = singleFieldBuilderV32.build();
            }
            if (this.configSpecifierCase_ == 4) {
                SingleFieldBuilderV3<ScopedRouteConfigurationsList, ScopedRouteConfigurationsList.Builder, ScopedRouteConfigurationsListOrBuilder> singleFieldBuilderV33 = this.scopedRouteConfigurationsListBuilder_;
                if (singleFieldBuilderV33 == null) {
                    scopedRoutes.configSpecifier_ = this.configSpecifier_;
                } else {
                    scopedRoutes.configSpecifier_ = singleFieldBuilderV33.build();
                }
            }
            if (this.configSpecifierCase_ == 5) {
                SingleFieldBuilderV3<ScopedRds, ScopedRds.Builder, ScopedRdsOrBuilder> singleFieldBuilderV34 = this.scopedRdsBuilder_;
                if (singleFieldBuilderV34 == null) {
                    scopedRoutes.configSpecifier_ = this.configSpecifier_;
                } else {
                    scopedRoutes.configSpecifier_ = singleFieldBuilderV34.build();
                }
            }
            scopedRoutes.configSpecifierCase_ = this.configSpecifierCase_;
            onBuilt();
            return scopedRoutes;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31395clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31407setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31385clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31388clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31409setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31375addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31400mergeFrom(Message message) {
            if (message instanceof ScopedRoutes) {
                return mergeFrom((ScopedRoutes) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ScopedRoutes scopedRoutes) {
            if (scopedRoutes == ScopedRoutes.getDefaultInstance()) {
                return this;
            }
            if (!scopedRoutes.getName().isEmpty()) {
                this.name_ = scopedRoutes.name_;
                onChanged();
            }
            if (scopedRoutes.hasScopeKeyBuilder()) {
                mergeScopeKeyBuilder(scopedRoutes.getScopeKeyBuilder());
            }
            if (scopedRoutes.hasRdsConfigSource()) {
                mergeRdsConfigSource(scopedRoutes.getRdsConfigSource());
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$ScopedRoutes$ConfigSpecifierCase[scopedRoutes.getConfigSpecifierCase().ordinal()];
            if (i == 1) {
                mergeScopedRouteConfigurationsList(scopedRoutes.getScopedRouteConfigurationsList());
            } else if (i == 2) {
                mergeScopedRds(scopedRoutes.getScopedRds());
            }
            m31405mergeUnknownFields(scopedRoutes.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.Builder m31401mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.access$5200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.Builder.m31401mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes$Builder");
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
        public ConfigSpecifierCase getConfigSpecifierCase() {
            return ConfigSpecifierCase.forNumber(this.configSpecifierCase_);
        }

        public Builder clearConfigSpecifier() {
            this.configSpecifierCase_ = 0;
            this.configSpecifier_ = null;
            onChanged();
            return this;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
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

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
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
            ScopedRoutes.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = ScopedRoutes.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
        public ScopeKeyBuilder getScopeKeyBuilder() {
            SingleFieldBuilderV3<ScopeKeyBuilder, ScopeKeyBuilder.Builder, ScopeKeyBuilderOrBuilder> singleFieldBuilderV3 = this.scopeKeyBuilderBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            ScopeKeyBuilder scopeKeyBuilder = this.scopeKeyBuilder_;
            return scopeKeyBuilder == null ? ScopeKeyBuilder.getDefaultInstance() : scopeKeyBuilder;
        }

        public Builder setScopeKeyBuilder(ScopeKeyBuilder scopeKeyBuilder) {
            SingleFieldBuilderV3<ScopeKeyBuilder, ScopeKeyBuilder.Builder, ScopeKeyBuilderOrBuilder> singleFieldBuilderV3 = this.scopeKeyBuilderBuilder_;
            if (singleFieldBuilderV3 == null) {
                scopeKeyBuilder.getClass();
                this.scopeKeyBuilder_ = scopeKeyBuilder;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(scopeKeyBuilder);
            }
            return this;
        }

        public Builder setScopeKeyBuilder(ScopeKeyBuilder.Builder builder) {
            SingleFieldBuilderV3<ScopeKeyBuilder, ScopeKeyBuilder.Builder, ScopeKeyBuilderOrBuilder> singleFieldBuilderV3 = this.scopeKeyBuilderBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.scopeKeyBuilder_ = builder.m31423build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m31423build());
            }
            return this;
        }

        public Builder mergeScopeKeyBuilder(ScopeKeyBuilder scopeKeyBuilder) {
            SingleFieldBuilderV3<ScopeKeyBuilder, ScopeKeyBuilder.Builder, ScopeKeyBuilderOrBuilder> singleFieldBuilderV3 = this.scopeKeyBuilderBuilder_;
            if (singleFieldBuilderV3 == null) {
                ScopeKeyBuilder scopeKeyBuilder2 = this.scopeKeyBuilder_;
                if (scopeKeyBuilder2 != null) {
                    this.scopeKeyBuilder_ = ScopeKeyBuilder.newBuilder(scopeKeyBuilder2).mergeFrom(scopeKeyBuilder).m31425buildPartial();
                } else {
                    this.scopeKeyBuilder_ = scopeKeyBuilder;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(scopeKeyBuilder);
            }
            return this;
        }

        public Builder clearScopeKeyBuilder() {
            if (this.scopeKeyBuilderBuilder_ == null) {
                this.scopeKeyBuilder_ = null;
                onChanged();
            } else {
                this.scopeKeyBuilder_ = null;
                this.scopeKeyBuilderBuilder_ = null;
            }
            return this;
        }

        public ScopeKeyBuilder.Builder getScopeKeyBuilderBuilder() {
            onChanged();
            return getScopeKeyBuilderFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
        public ScopeKeyBuilderOrBuilder getScopeKeyBuilderOrBuilder() {
            SingleFieldBuilderV3<ScopeKeyBuilder, ScopeKeyBuilder.Builder, ScopeKeyBuilderOrBuilder> singleFieldBuilderV3 = this.scopeKeyBuilderBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ScopeKeyBuilderOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            ScopeKeyBuilder scopeKeyBuilder = this.scopeKeyBuilder_;
            return scopeKeyBuilder == null ? ScopeKeyBuilder.getDefaultInstance() : scopeKeyBuilder;
        }

        private SingleFieldBuilderV3<ScopeKeyBuilder, ScopeKeyBuilder.Builder, ScopeKeyBuilderOrBuilder> getScopeKeyBuilderFieldBuilder() {
            if (this.scopeKeyBuilderBuilder_ == null) {
                this.scopeKeyBuilderBuilder_ = new SingleFieldBuilderV3<>(getScopeKeyBuilder(), getParentForChildren(), isClean());
                this.scopeKeyBuilder_ = null;
            }
            return this.scopeKeyBuilderBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
        public ConfigSource getRdsConfigSource() {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.rdsConfigSourceBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            ConfigSource configSource = this.rdsConfigSource_;
            return configSource == null ? ConfigSource.getDefaultInstance() : configSource;
        }

        public Builder setRdsConfigSource(ConfigSource configSource) {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.rdsConfigSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                configSource.getClass();
                this.rdsConfigSource_ = configSource;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(configSource);
            }
            return this;
        }

        public Builder setRdsConfigSource(ConfigSource.Builder builder) {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.rdsConfigSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.rdsConfigSource_ = builder.m21804build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m21804build());
            }
            return this;
        }

        public Builder mergeRdsConfigSource(ConfigSource configSource) {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.rdsConfigSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                ConfigSource configSource2 = this.rdsConfigSource_;
                if (configSource2 != null) {
                    this.rdsConfigSource_ = ConfigSource.newBuilder(configSource2).mergeFrom(configSource).m21806buildPartial();
                } else {
                    this.rdsConfigSource_ = configSource;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(configSource);
            }
            return this;
        }

        public Builder clearRdsConfigSource() {
            if (this.rdsConfigSourceBuilder_ == null) {
                this.rdsConfigSource_ = null;
                onChanged();
            } else {
                this.rdsConfigSource_ = null;
                this.rdsConfigSourceBuilder_ = null;
            }
            return this;
        }

        public ConfigSource.Builder getRdsConfigSourceBuilder() {
            onChanged();
            return getRdsConfigSourceFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
        public ConfigSourceOrBuilder getRdsConfigSourceOrBuilder() {
            SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> singleFieldBuilderV3 = this.rdsConfigSourceBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ConfigSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            ConfigSource configSource = this.rdsConfigSource_;
            return configSource == null ? ConfigSource.getDefaultInstance() : configSource;
        }

        private SingleFieldBuilderV3<ConfigSource, ConfigSource.Builder, ConfigSourceOrBuilder> getRdsConfigSourceFieldBuilder() {
            if (this.rdsConfigSourceBuilder_ == null) {
                this.rdsConfigSourceBuilder_ = new SingleFieldBuilderV3<>(getRdsConfigSource(), getParentForChildren(), isClean());
                this.rdsConfigSource_ = null;
            }
            return this.rdsConfigSourceBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
        public ScopedRouteConfigurationsList getScopedRouteConfigurationsList() {
            SingleFieldBuilderV3<ScopedRouteConfigurationsList, ScopedRouteConfigurationsList.Builder, ScopedRouteConfigurationsListOrBuilder> singleFieldBuilderV3 = this.scopedRouteConfigurationsListBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configSpecifierCase_ == 4) {
                    return (ScopedRouteConfigurationsList) this.configSpecifier_;
                }
                return ScopedRouteConfigurationsList.getDefaultInstance();
            }
            if (this.configSpecifierCase_ == 4) {
                return singleFieldBuilderV3.getMessage();
            }
            return ScopedRouteConfigurationsList.getDefaultInstance();
        }

        public Builder setScopedRouteConfigurationsList(ScopedRouteConfigurationsList scopedRouteConfigurationsList) {
            SingleFieldBuilderV3<ScopedRouteConfigurationsList, ScopedRouteConfigurationsList.Builder, ScopedRouteConfigurationsListOrBuilder> singleFieldBuilderV3 = this.scopedRouteConfigurationsListBuilder_;
            if (singleFieldBuilderV3 == null) {
                scopedRouteConfigurationsList.getClass();
                this.configSpecifier_ = scopedRouteConfigurationsList;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(scopedRouteConfigurationsList);
            }
            this.configSpecifierCase_ = 4;
            return this;
        }

        public Builder setScopedRouteConfigurationsList(ScopedRouteConfigurationsList.Builder builder) {
            SingleFieldBuilderV3<ScopedRouteConfigurationsList, ScopedRouteConfigurationsList.Builder, ScopedRouteConfigurationsListOrBuilder> singleFieldBuilderV3 = this.scopedRouteConfigurationsListBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.configSpecifier_ = builder.m31331build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m31331build());
            }
            this.configSpecifierCase_ = 4;
            return this;
        }

        public Builder mergeScopedRouteConfigurationsList(ScopedRouteConfigurationsList scopedRouteConfigurationsList) {
            SingleFieldBuilderV3<ScopedRouteConfigurationsList, ScopedRouteConfigurationsList.Builder, ScopedRouteConfigurationsListOrBuilder> singleFieldBuilderV3 = this.scopedRouteConfigurationsListBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configSpecifierCase_ != 4 || this.configSpecifier_ == ScopedRouteConfigurationsList.getDefaultInstance()) {
                    this.configSpecifier_ = scopedRouteConfigurationsList;
                } else {
                    this.configSpecifier_ = ScopedRouteConfigurationsList.newBuilder((ScopedRouteConfigurationsList) this.configSpecifier_).mergeFrom(scopedRouteConfigurationsList).m31333buildPartial();
                }
                onChanged();
            } else {
                if (this.configSpecifierCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(scopedRouteConfigurationsList);
                }
                this.scopedRouteConfigurationsListBuilder_.setMessage(scopedRouteConfigurationsList);
            }
            this.configSpecifierCase_ = 4;
            return this;
        }

        public Builder clearScopedRouteConfigurationsList() {
            SingleFieldBuilderV3<ScopedRouteConfigurationsList, ScopedRouteConfigurationsList.Builder, ScopedRouteConfigurationsListOrBuilder> singleFieldBuilderV3 = this.scopedRouteConfigurationsListBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.configSpecifierCase_ == 4) {
                    this.configSpecifierCase_ = 0;
                    this.configSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.configSpecifierCase_ == 4) {
                this.configSpecifierCase_ = 0;
                this.configSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public ScopedRouteConfigurationsList.Builder getScopedRouteConfigurationsListBuilder() {
            return getScopedRouteConfigurationsListFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
        public ScopedRouteConfigurationsListOrBuilder getScopedRouteConfigurationsListOrBuilder() {
            SingleFieldBuilderV3<ScopedRouteConfigurationsList, ScopedRouteConfigurationsList.Builder, ScopedRouteConfigurationsListOrBuilder> singleFieldBuilderV3;
            int i = this.configSpecifierCase_;
            if (i == 4 && (singleFieldBuilderV3 = this.scopedRouteConfigurationsListBuilder_) != null) {
                return (ScopedRouteConfigurationsListOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 4) {
                return (ScopedRouteConfigurationsList) this.configSpecifier_;
            }
            return ScopedRouteConfigurationsList.getDefaultInstance();
        }

        private SingleFieldBuilderV3<ScopedRouteConfigurationsList, ScopedRouteConfigurationsList.Builder, ScopedRouteConfigurationsListOrBuilder> getScopedRouteConfigurationsListFieldBuilder() {
            if (this.scopedRouteConfigurationsListBuilder_ == null) {
                if (this.configSpecifierCase_ != 4) {
                    this.configSpecifier_ = ScopedRouteConfigurationsList.getDefaultInstance();
                }
                this.scopedRouteConfigurationsListBuilder_ = new SingleFieldBuilderV3<>((ScopedRouteConfigurationsList) this.configSpecifier_, getParentForChildren(), isClean());
                this.configSpecifier_ = null;
            }
            this.configSpecifierCase_ = 4;
            onChanged();
            return this.scopedRouteConfigurationsListBuilder_;
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
        public ScopedRds getScopedRds() {
            SingleFieldBuilderV3<ScopedRds, ScopedRds.Builder, ScopedRdsOrBuilder> singleFieldBuilderV3 = this.scopedRdsBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configSpecifierCase_ == 5) {
                    return (ScopedRds) this.configSpecifier_;
                }
                return ScopedRds.getDefaultInstance();
            }
            if (this.configSpecifierCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return ScopedRds.getDefaultInstance();
        }

        public Builder setScopedRds(ScopedRds scopedRds) {
            SingleFieldBuilderV3<ScopedRds, ScopedRds.Builder, ScopedRdsOrBuilder> singleFieldBuilderV3 = this.scopedRdsBuilder_;
            if (singleFieldBuilderV3 == null) {
                scopedRds.getClass();
                this.configSpecifier_ = scopedRds;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(scopedRds);
            }
            this.configSpecifierCase_ = 5;
            return this;
        }

        public Builder setScopedRds(ScopedRds.Builder builder) {
            SingleFieldBuilderV3<ScopedRds, ScopedRds.Builder, ScopedRdsOrBuilder> singleFieldBuilderV3 = this.scopedRdsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.configSpecifier_ = builder.m31285build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m31285build());
            }
            this.configSpecifierCase_ = 5;
            return this;
        }

        public Builder mergeScopedRds(ScopedRds scopedRds) {
            SingleFieldBuilderV3<ScopedRds, ScopedRds.Builder, ScopedRdsOrBuilder> singleFieldBuilderV3 = this.scopedRdsBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.configSpecifierCase_ != 5 || this.configSpecifier_ == ScopedRds.getDefaultInstance()) {
                    this.configSpecifier_ = scopedRds;
                } else {
                    this.configSpecifier_ = ScopedRds.newBuilder((ScopedRds) this.configSpecifier_).mergeFrom(scopedRds).m31287buildPartial();
                }
                onChanged();
            } else {
                if (this.configSpecifierCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(scopedRds);
                }
                this.scopedRdsBuilder_.setMessage(scopedRds);
            }
            this.configSpecifierCase_ = 5;
            return this;
        }

        public Builder clearScopedRds() {
            SingleFieldBuilderV3<ScopedRds, ScopedRds.Builder, ScopedRdsOrBuilder> singleFieldBuilderV3 = this.scopedRdsBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.configSpecifierCase_ == 5) {
                    this.configSpecifierCase_ = 0;
                    this.configSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.configSpecifierCase_ == 5) {
                this.configSpecifierCase_ = 0;
                this.configSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public ScopedRds.Builder getScopedRdsBuilder() {
            return getScopedRdsFieldBuilder().getBuilder();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesOrBuilder
        public ScopedRdsOrBuilder getScopedRdsOrBuilder() {
            SingleFieldBuilderV3<ScopedRds, ScopedRds.Builder, ScopedRdsOrBuilder> singleFieldBuilderV3;
            int i = this.configSpecifierCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.scopedRdsBuilder_) != null) {
                return (ScopedRdsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (ScopedRds) this.configSpecifier_;
            }
            return ScopedRds.getDefaultInstance();
        }

        private SingleFieldBuilderV3<ScopedRds, ScopedRds.Builder, ScopedRdsOrBuilder> getScopedRdsFieldBuilder() {
            if (this.scopedRdsBuilder_ == null) {
                if (this.configSpecifierCase_ != 5) {
                    this.configSpecifier_ = ScopedRds.getDefaultInstance();
                }
                this.scopedRdsBuilder_ = new SingleFieldBuilderV3<>((ScopedRds) this.configSpecifier_, getParentForChildren(), isClean());
                this.configSpecifier_ = null;
            }
            this.configSpecifierCase_ = 5;
            onChanged();
            return this.scopedRdsBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m31411setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m31405mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$ScopedRoutes$ConfigSpecifierCase;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$HeaderValueExtractor$ExtractTypeCase;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$TypeCase;

        static {
            int[] iArr = new int[ConfigSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$ScopedRoutes$ConfigSpecifierCase = iArr;
            try {
                iArr[ConfigSpecifierCase.SCOPED_ROUTE_CONFIGURATIONS_LIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$ScopedRoutes$ConfigSpecifierCase[ConfigSpecifierCase.SCOPED_RDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$ScopedRoutes$ConfigSpecifierCase[ConfigSpecifierCase.CONFIGSPECIFIER_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[ScopeKeyBuilder.FragmentBuilder.TypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$TypeCase = iArr2;
            try {
                iArr2[ScopeKeyBuilder.FragmentBuilder.TypeCase.HEADER_VALUE_EXTRACTOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$TypeCase[ScopeKeyBuilder.FragmentBuilder.TypeCase.TYPE_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.ExtractTypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$HeaderValueExtractor$ExtractTypeCase = iArr3;
            try {
                iArr3[ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.ExtractTypeCase.INDEX.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$HeaderValueExtractor$ExtractTypeCase[ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.ExtractTypeCase.ELEMENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$extensions$filters$network$http_connection_manager$v3$ScopedRoutes$ScopeKeyBuilder$FragmentBuilder$HeaderValueExtractor$ExtractTypeCase[ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.ExtractTypeCase.EXTRACTTYPE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }
}
