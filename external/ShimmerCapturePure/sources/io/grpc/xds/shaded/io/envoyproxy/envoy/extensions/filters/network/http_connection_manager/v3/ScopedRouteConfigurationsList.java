package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3;

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
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.ScopedRouteConfiguration;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.ScopedRouteConfigurationOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class ScopedRouteConfigurationsList extends GeneratedMessageV3 implements ScopedRouteConfigurationsListOrBuilder {
    public static final int SCOPED_ROUTE_CONFIGURATIONS_FIELD_NUMBER = 1;
    private static final ScopedRouteConfigurationsList DEFAULT_INSTANCE = new ScopedRouteConfigurationsList();
    private static final Parser<ScopedRouteConfigurationsList> PARSER = new AbstractParser<ScopedRouteConfigurationsList>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsList.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ScopedRouteConfigurationsList m31328parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ScopedRouteConfigurationsList(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<ScopedRouteConfiguration> scopedRouteConfigurations_;

    private ScopedRouteConfigurationsList(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ScopedRouteConfigurationsList() {
        this.memoizedIsInitialized = (byte) -1;
        this.scopedRouteConfigurations_ = Collections.emptyList();
    }

    private ScopedRouteConfigurationsList(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.scopedRouteConfigurations_ = new ArrayList();
                                z2 |= true;
                            }
                            this.scopedRouteConfigurations_.add(codedInputStream.readMessage(ScopedRouteConfiguration.parser(), extensionRegistryLite));
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
                    this.scopedRouteConfigurations_ = Collections.unmodifiableList(this.scopedRouteConfigurations_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ScopedRouteConfigurationsList getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ScopedRouteConfigurationsList> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRouteConfigurationsList_descriptor;
    }

    public static ScopedRouteConfigurationsList parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ScopedRouteConfigurationsList) PARSER.parseFrom(byteBuffer);
    }

    public static ScopedRouteConfigurationsList parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ScopedRouteConfigurationsList) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ScopedRouteConfigurationsList parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ScopedRouteConfigurationsList) PARSER.parseFrom(byteString);
    }

    public static ScopedRouteConfigurationsList parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ScopedRouteConfigurationsList) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ScopedRouteConfigurationsList parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ScopedRouteConfigurationsList) PARSER.parseFrom(bArr);
    }

    public static ScopedRouteConfigurationsList parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ScopedRouteConfigurationsList) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ScopedRouteConfigurationsList parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ScopedRouteConfigurationsList parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ScopedRouteConfigurationsList parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ScopedRouteConfigurationsList parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ScopedRouteConfigurationsList parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ScopedRouteConfigurationsList parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m31326toBuilder();
    }

    public static Builder newBuilder(ScopedRouteConfigurationsList scopedRouteConfigurationsList) {
        return DEFAULT_INSTANCE.m31326toBuilder().mergeFrom(scopedRouteConfigurationsList);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ScopedRouteConfigurationsList m31321getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ScopedRouteConfigurationsList> getParserForType() {
        return PARSER;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsListOrBuilder
    public List<ScopedRouteConfiguration> getScopedRouteConfigurationsList() {
        return this.scopedRouteConfigurations_;
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsListOrBuilder
    public List<? extends ScopedRouteConfigurationOrBuilder> getScopedRouteConfigurationsOrBuilderList() {
        return this.scopedRouteConfigurations_;
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
        return new ScopedRouteConfigurationsList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRouteConfigurationsList_fieldAccessorTable.ensureFieldAccessorsInitialized(ScopedRouteConfigurationsList.class, Builder.class);
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsListOrBuilder
    public int getScopedRouteConfigurationsCount() {
        return this.scopedRouteConfigurations_.size();
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsListOrBuilder
    public ScopedRouteConfiguration getScopedRouteConfigurations(int i) {
        return this.scopedRouteConfigurations_.get(i);
    }

    @Override
    // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsListOrBuilder
    public ScopedRouteConfigurationOrBuilder getScopedRouteConfigurationsOrBuilder(int i) {
        return this.scopedRouteConfigurations_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.scopedRouteConfigurations_.size(); i++) {
            codedOutputStream.writeMessage(1, this.scopedRouteConfigurations_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.scopedRouteConfigurations_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.scopedRouteConfigurations_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ScopedRouteConfigurationsList)) {
            return super.equals(obj);
        }
        ScopedRouteConfigurationsList scopedRouteConfigurationsList = (ScopedRouteConfigurationsList) obj;
        return getScopedRouteConfigurationsList().equals(scopedRouteConfigurationsList.getScopedRouteConfigurationsList()) && this.unknownFields.equals(scopedRouteConfigurationsList.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getScopedRouteConfigurationsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getScopedRouteConfigurationsList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m31323newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m31326toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ScopedRouteConfigurationsListOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> scopedRouteConfigurationsBuilder_;
        private List<ScopedRouteConfiguration> scopedRouteConfigurations_;

        private Builder() {
            this.scopedRouteConfigurations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.scopedRouteConfigurations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRouteConfigurationsList_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRouteConfigurationsList_fieldAccessorTable.ensureFieldAccessorsInitialized(ScopedRouteConfigurationsList.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (ScopedRouteConfigurationsList.alwaysUseFieldBuilders) {
                getScopedRouteConfigurationsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31337clear() {
            super.clear();
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.scopedRouteConfigurations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HttpConnectionManagerProto.internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRouteConfigurationsList_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ScopedRouteConfigurationsList m31350getDefaultInstanceForType() {
            return ScopedRouteConfigurationsList.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ScopedRouteConfigurationsList m31331build() throws UninitializedMessageException {
            ScopedRouteConfigurationsList scopedRouteConfigurationsListM31333buildPartial = m31333buildPartial();
            if (scopedRouteConfigurationsListM31333buildPartial.isInitialized()) {
                return scopedRouteConfigurationsListM31333buildPartial;
            }
            throw newUninitializedMessageException(scopedRouteConfigurationsListM31333buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ScopedRouteConfigurationsList m31333buildPartial() {
            ScopedRouteConfigurationsList scopedRouteConfigurationsList = new ScopedRouteConfigurationsList(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.scopedRouteConfigurations_ = Collections.unmodifiableList(this.scopedRouteConfigurations_);
                    this.bitField0_ &= -2;
                }
                scopedRouteConfigurationsList.scopedRouteConfigurations_ = this.scopedRouteConfigurations_;
            } else {
                scopedRouteConfigurationsList.scopedRouteConfigurations_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return scopedRouteConfigurationsList;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31349clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31361setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31339clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31342clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31363setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31329addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m31354mergeFrom(Message message) {
            if (message instanceof ScopedRouteConfigurationsList) {
                return mergeFrom((ScopedRouteConfigurationsList) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ScopedRouteConfigurationsList scopedRouteConfigurationsList) {
            if (scopedRouteConfigurationsList == ScopedRouteConfigurationsList.getDefaultInstance()) {
                return this;
            }
            if (this.scopedRouteConfigurationsBuilder_ == null) {
                if (!scopedRouteConfigurationsList.scopedRouteConfigurations_.isEmpty()) {
                    if (this.scopedRouteConfigurations_.isEmpty()) {
                        this.scopedRouteConfigurations_ = scopedRouteConfigurationsList.scopedRouteConfigurations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureScopedRouteConfigurationsIsMutable();
                        this.scopedRouteConfigurations_.addAll(scopedRouteConfigurationsList.scopedRouteConfigurations_);
                    }
                    onChanged();
                }
            } else if (!scopedRouteConfigurationsList.scopedRouteConfigurations_.isEmpty()) {
                if (!this.scopedRouteConfigurationsBuilder_.isEmpty()) {
                    this.scopedRouteConfigurationsBuilder_.addAllMessages(scopedRouteConfigurationsList.scopedRouteConfigurations_);
                } else {
                    this.scopedRouteConfigurationsBuilder_.dispose();
                    this.scopedRouteConfigurationsBuilder_ = null;
                    this.scopedRouteConfigurations_ = scopedRouteConfigurationsList.scopedRouteConfigurations_;
                    this.bitField0_ &= -2;
                    this.scopedRouteConfigurationsBuilder_ = ScopedRouteConfigurationsList.alwaysUseFieldBuilders ? getScopedRouteConfigurationsFieldBuilder() : null;
                }
            }
            m31359mergeUnknownFields(scopedRouteConfigurationsList.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsList.Builder m31355mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsList.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsList r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsList) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsList r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsList) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsList.Builder.m31355mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsList$Builder");
        }

        private void ensureScopedRouteConfigurationsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.scopedRouteConfigurations_ = new ArrayList(this.scopedRouteConfigurations_);
                this.bitField0_ |= 1;
            }
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsListOrBuilder
        public List<ScopedRouteConfiguration> getScopedRouteConfigurationsList() {
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.scopedRouteConfigurations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsListOrBuilder
        public int getScopedRouteConfigurationsCount() {
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.scopedRouteConfigurations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsListOrBuilder
        public ScopedRouteConfiguration getScopedRouteConfigurations(int i) {
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.scopedRouteConfigurations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setScopedRouteConfigurations(int i, ScopedRouteConfiguration scopedRouteConfiguration) {
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                scopedRouteConfiguration.getClass();
                ensureScopedRouteConfigurationsIsMutable();
                this.scopedRouteConfigurations_.set(i, scopedRouteConfiguration);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, scopedRouteConfiguration);
            }
            return this;
        }

        public Builder setScopedRouteConfigurations(int i, ScopedRouteConfiguration.Builder builder) {
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureScopedRouteConfigurationsIsMutable();
                this.scopedRouteConfigurations_.set(i, builder.m29659build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m29659build());
            }
            return this;
        }

        public Builder addScopedRouteConfigurations(ScopedRouteConfiguration scopedRouteConfiguration) {
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                scopedRouteConfiguration.getClass();
                ensureScopedRouteConfigurationsIsMutable();
                this.scopedRouteConfigurations_.add(scopedRouteConfiguration);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(scopedRouteConfiguration);
            }
            return this;
        }

        public Builder addScopedRouteConfigurations(int i, ScopedRouteConfiguration scopedRouteConfiguration) {
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                scopedRouteConfiguration.getClass();
                ensureScopedRouteConfigurationsIsMutable();
                this.scopedRouteConfigurations_.add(i, scopedRouteConfiguration);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, scopedRouteConfiguration);
            }
            return this;
        }

        public Builder addScopedRouteConfigurations(ScopedRouteConfiguration.Builder builder) {
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureScopedRouteConfigurationsIsMutable();
                this.scopedRouteConfigurations_.add(builder.m29659build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m29659build());
            }
            return this;
        }

        public Builder addScopedRouteConfigurations(int i, ScopedRouteConfiguration.Builder builder) {
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureScopedRouteConfigurationsIsMutable();
                this.scopedRouteConfigurations_.add(i, builder.m29659build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m29659build());
            }
            return this;
        }

        public Builder addAllScopedRouteConfigurations(Iterable<? extends ScopedRouteConfiguration> iterable) {
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureScopedRouteConfigurationsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.scopedRouteConfigurations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearScopedRouteConfigurations() {
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.scopedRouteConfigurations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeScopedRouteConfigurations(int i) {
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureScopedRouteConfigurationsIsMutable();
                this.scopedRouteConfigurations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public ScopedRouteConfiguration.Builder getScopedRouteConfigurationsBuilder(int i) {
            return getScopedRouteConfigurationsFieldBuilder().getBuilder(i);
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsListOrBuilder
        public ScopedRouteConfigurationOrBuilder getScopedRouteConfigurationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.scopedRouteConfigurations_.get(i);
            }
            return (ScopedRouteConfigurationOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override
        // io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsListOrBuilder
        public List<? extends ScopedRouteConfigurationOrBuilder> getScopedRouteConfigurationsOrBuilderList() {
            RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> repeatedFieldBuilderV3 = this.scopedRouteConfigurationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.scopedRouteConfigurations_);
        }

        public ScopedRouteConfiguration.Builder addScopedRouteConfigurationsBuilder() {
            return getScopedRouteConfigurationsFieldBuilder().addBuilder(ScopedRouteConfiguration.getDefaultInstance());
        }

        public ScopedRouteConfiguration.Builder addScopedRouteConfigurationsBuilder(int i) {
            return getScopedRouteConfigurationsFieldBuilder().addBuilder(i, ScopedRouteConfiguration.getDefaultInstance());
        }

        public List<ScopedRouteConfiguration.Builder> getScopedRouteConfigurationsBuilderList() {
            return getScopedRouteConfigurationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<ScopedRouteConfiguration, ScopedRouteConfiguration.Builder, ScopedRouteConfigurationOrBuilder> getScopedRouteConfigurationsFieldBuilder() {
            if (this.scopedRouteConfigurationsBuilder_ == null) {
                this.scopedRouteConfigurationsBuilder_ = new RepeatedFieldBuilderV3<>(this.scopedRouteConfigurations_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.scopedRouteConfigurations_ = null;
            }
            return this.scopedRouteConfigurationsBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m31365setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m31359mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
