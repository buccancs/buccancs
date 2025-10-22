package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.DownstreamTlsContext;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.DownstreamTlsContextOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Metadata;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.MetadataOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.TransportSocket;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.TransportSocketOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.Filter;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatch;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class FilterChain extends GeneratedMessageV3 implements FilterChainOrBuilder {
    public static final int FILTERS_FIELD_NUMBER = 3;
    public static final int FILTER_CHAIN_MATCH_FIELD_NUMBER = 1;
    public static final int METADATA_FIELD_NUMBER = 5;
    public static final int NAME_FIELD_NUMBER = 7;
    public static final int TLS_CONTEXT_FIELD_NUMBER = 2;
    public static final int TRANSPORT_SOCKET_FIELD_NUMBER = 6;
    public static final int USE_PROXY_PROTO_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private static final FilterChain DEFAULT_INSTANCE = new FilterChain();
    private static final Parser<FilterChain> PARSER = new AbstractParser<FilterChain>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChain.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public FilterChain m17584parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new FilterChain(codedInputStream, extensionRegistryLite);
        }
    };
    private FilterChainMatch filterChainMatch_;
    private List<Filter> filters_;
    private byte memoizedIsInitialized;
    private Metadata metadata_;
    private volatile Object name_;
    private DownstreamTlsContext tlsContext_;
    private TransportSocket transportSocket_;
    private BoolValue useProxyProto_;

    private FilterChain(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private FilterChain() {
        this.memoizedIsInitialized = (byte) -1;
        this.filters_ = Collections.emptyList();
        this.name_ = "";
    }

    private FilterChain(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        BoolValue.Builder builderM16984toBuilder;
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
                                FilterChainMatch filterChainMatch = this.filterChainMatch_;
                                builderM16984toBuilder = filterChainMatch != null ? filterChainMatch.m17630toBuilder() : null;
                                FilterChainMatch filterChainMatch2 = (FilterChainMatch) codedInputStream.readMessage(FilterChainMatch.parser(), extensionRegistryLite);
                                this.filterChainMatch_ = filterChainMatch2;
                                if (builderM16984toBuilder != null) {
                                    builderM16984toBuilder.mergeFrom(filterChainMatch2);
                                    this.filterChainMatch_ = builderM16984toBuilder.m17637buildPartial();
                                }
                            } else if (tag == 18) {
                                DownstreamTlsContext downstreamTlsContext = this.tlsContext_;
                                builderM16984toBuilder = downstreamTlsContext != null ? downstreamTlsContext.m13610toBuilder() : null;
                                DownstreamTlsContext downstreamTlsContext2 = (DownstreamTlsContext) codedInputStream.readMessage(DownstreamTlsContext.parser(), extensionRegistryLite);
                                this.tlsContext_ = downstreamTlsContext2;
                                if (builderM16984toBuilder != null) {
                                    builderM16984toBuilder.mergeFrom(downstreamTlsContext2);
                                    this.tlsContext_ = builderM16984toBuilder.m13617buildPartial();
                                }
                            } else if (tag == 26) {
                                if (!(z2 & true)) {
                                    this.filters_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.filters_.add(codedInputStream.readMessage(Filter.parser(), extensionRegistryLite));
                            } else if (tag == 34) {
                                BoolValue boolValue = this.useProxyProto_;
                                builderM16984toBuilder = boolValue != null ? boolValue.toBuilder() : null;
                                BoolValue message = codedInputStream.readMessage(BoolValue.parser(), extensionRegistryLite);
                                this.useProxyProto_ = message;
                                if (builderM16984toBuilder != null) {
                                    builderM16984toBuilder.mergeFrom(message);
                                    this.useProxyProto_ = builderM16984toBuilder.buildPartial();
                                }
                            } else if (tag == 42) {
                                Metadata metadata = this.metadata_;
                                builderM16984toBuilder = metadata != null ? metadata.m16288toBuilder() : null;
                                Metadata message2 = codedInputStream.readMessage(Metadata.parser(), extensionRegistryLite);
                                this.metadata_ = message2;
                                if (builderM16984toBuilder != null) {
                                    builderM16984toBuilder.mergeFrom(message2);
                                    this.metadata_ = builderM16984toBuilder.m16295buildPartial();
                                }
                            } else if (tag == 50) {
                                TransportSocket transportSocket = this.transportSocket_;
                                builderM16984toBuilder = transportSocket != null ? transportSocket.m16984toBuilder() : null;
                                TransportSocket message3 = codedInputStream.readMessage(TransportSocket.parser(), extensionRegistryLite);
                                this.transportSocket_ = message3;
                                if (builderM16984toBuilder != null) {
                                    builderM16984toBuilder.mergeFrom(message3);
                                    this.transportSocket_ = builderM16984toBuilder.m16991buildPartial();
                                }
                            } else if (tag == 58) {
                                this.name_ = codedInputStream.readStringRequireUtf8();
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
                if (z2 & true) {
                    this.filters_ = Collections.unmodifiableList(this.filters_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static FilterChain getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FilterChain> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ListenerComponentsProto.internal_static_envoy_api_v2_listener_FilterChain_descriptor;
    }

    public static FilterChain parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (FilterChain) PARSER.parseFrom(byteBuffer);
    }

    public static FilterChain parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FilterChain) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static FilterChain parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (FilterChain) PARSER.parseFrom(byteString);
    }

    public static FilterChain parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FilterChain) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static FilterChain parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (FilterChain) PARSER.parseFrom(bArr);
    }

    public static FilterChain parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FilterChain) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static FilterChain parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static FilterChain parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static FilterChain parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static FilterChain parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static FilterChain parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static FilterChain parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m17582toBuilder();
    }

    public static Builder newBuilder(FilterChain filterChain) {
        return DEFAULT_INSTANCE.m17582toBuilder().mergeFrom(filterChain);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public FilterChain m17577getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public List<Filter> getFiltersList() {
        return this.filters_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public List<? extends FilterOrBuilder> getFiltersOrBuilderList() {
        return this.filters_;
    }

    public Parser<FilterChain> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public boolean hasFilterChainMatch() {
        return this.filterChainMatch_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public boolean hasMetadata() {
        return this.metadata_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    @Deprecated
    public boolean hasTlsContext() {
        return this.tlsContext_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public boolean hasTransportSocket() {
        return this.transportSocket_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public boolean hasUseProxyProto() {
        return this.useProxyProto_ != null;
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
        return new FilterChain();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ListenerComponentsProto.internal_static_envoy_api_v2_listener_FilterChain_fieldAccessorTable.ensureFieldAccessorsInitialized(FilterChain.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public FilterChainMatch getFilterChainMatch() {
        FilterChainMatch filterChainMatch = this.filterChainMatch_;
        return filterChainMatch == null ? FilterChainMatch.getDefaultInstance() : filterChainMatch;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public FilterChainMatchOrBuilder getFilterChainMatchOrBuilder() {
        return getFilterChainMatch();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    @Deprecated
    public DownstreamTlsContext getTlsContext() {
        DownstreamTlsContext downstreamTlsContext = this.tlsContext_;
        return downstreamTlsContext == null ? DownstreamTlsContext.getDefaultInstance() : downstreamTlsContext;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    @Deprecated
    public DownstreamTlsContextOrBuilder getTlsContextOrBuilder() {
        return getTlsContext();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public int getFiltersCount() {
        return this.filters_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public Filter getFilters(int i) {
        return this.filters_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public FilterOrBuilder getFiltersOrBuilder(int i) {
        return this.filters_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public BoolValue getUseProxyProto() {
        BoolValue boolValue = this.useProxyProto_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public BoolValueOrBuilder getUseProxyProtoOrBuilder() {
        return getUseProxyProto();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public Metadata getMetadata() {
        Metadata metadata = this.metadata_;
        return metadata == null ? Metadata.getDefaultInstance() : metadata;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public MetadataOrBuilder getMetadataOrBuilder() {
        return getMetadata();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public TransportSocket getTransportSocket() {
        TransportSocket transportSocket = this.transportSocket_;
        return transportSocket == null ? TransportSocket.getDefaultInstance() : transportSocket;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public TransportSocketOrBuilder getTransportSocketOrBuilder() {
        return getTransportSocket();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.filterChainMatch_ != null) {
            codedOutputStream.writeMessage(1, getFilterChainMatch());
        }
        if (this.tlsContext_ != null) {
            codedOutputStream.writeMessage(2, getTlsContext());
        }
        for (int i = 0; i < this.filters_.size(); i++) {
            codedOutputStream.writeMessage(3, this.filters_.get(i));
        }
        if (this.useProxyProto_ != null) {
            codedOutputStream.writeMessage(4, getUseProxyProto());
        }
        if (this.metadata_ != null) {
            codedOutputStream.writeMessage(5, getMetadata());
        }
        if (this.transportSocket_ != null) {
            codedOutputStream.writeMessage(6, getTransportSocket());
        }
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.name_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.filterChainMatch_ != null ? CodedOutputStream.computeMessageSize(1, getFilterChainMatch()) : 0;
        if (this.tlsContext_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getTlsContext());
        }
        for (int i2 = 0; i2 < this.filters_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, this.filters_.get(i2));
        }
        if (this.useProxyProto_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(4, getUseProxyProto());
        }
        if (this.metadata_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(5, getMetadata());
        }
        if (this.transportSocket_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(6, getTransportSocket());
        }
        if (!getNameBytes().isEmpty()) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(7, this.name_);
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FilterChain)) {
            return super.equals(obj);
        }
        FilterChain filterChain = (FilterChain) obj;
        if (hasFilterChainMatch() != filterChain.hasFilterChainMatch()) {
            return false;
        }
        if ((hasFilterChainMatch() && !getFilterChainMatch().equals(filterChain.getFilterChainMatch())) || hasTlsContext() != filterChain.hasTlsContext()) {
            return false;
        }
        if ((hasTlsContext() && !getTlsContext().equals(filterChain.getTlsContext())) || !getFiltersList().equals(filterChain.getFiltersList()) || hasUseProxyProto() != filterChain.hasUseProxyProto()) {
            return false;
        }
        if ((hasUseProxyProto() && !getUseProxyProto().equals(filterChain.getUseProxyProto())) || hasMetadata() != filterChain.hasMetadata()) {
            return false;
        }
        if ((!hasMetadata() || getMetadata().equals(filterChain.getMetadata())) && hasTransportSocket() == filterChain.hasTransportSocket()) {
            return (!hasTransportSocket() || getTransportSocket().equals(filterChain.getTransportSocket())) && getName().equals(filterChain.getName()) && this.unknownFields.equals(filterChain.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasFilterChainMatch()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getFilterChainMatch().hashCode();
        }
        if (hasTlsContext()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getTlsContext().hashCode();
        }
        if (getFiltersCount() > 0) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getFiltersList().hashCode();
        }
        if (hasUseProxyProto()) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getUseProxyProto().hashCode();
        }
        if (hasMetadata()) {
            iHashCode = (((iHashCode * 37) + 5) * 53) + getMetadata().hashCode();
        }
        if (hasTransportSocket()) {
            iHashCode = (((iHashCode * 37) + 6) * 53) + getTransportSocket().hashCode();
        }
        int iHashCode2 = (((((iHashCode * 37) + 7) * 53) + getName().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m17579newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m17582toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FilterChainOrBuilder {
        private int bitField0_;
        private SingleFieldBuilderV3<FilterChainMatch, FilterChainMatch.Builder, FilterChainMatchOrBuilder> filterChainMatchBuilder_;
        private FilterChainMatch filterChainMatch_;
        private RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> filtersBuilder_;
        private List<Filter> filters_;
        private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> metadataBuilder_;
        private Metadata metadata_;
        private Object name_;
        private SingleFieldBuilderV3<DownstreamTlsContext, DownstreamTlsContext.Builder, DownstreamTlsContextOrBuilder> tlsContextBuilder_;
        private DownstreamTlsContext tlsContext_;
        private SingleFieldBuilderV3<TransportSocket, TransportSocket.Builder, TransportSocketOrBuilder> transportSocketBuilder_;
        private TransportSocket transportSocket_;
        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> useProxyProtoBuilder_;
        private BoolValue useProxyProto_;

        private Builder() {
            this.filters_ = Collections.emptyList();
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.filters_ = Collections.emptyList();
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ListenerComponentsProto.internal_static_envoy_api_v2_listener_FilterChain_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public boolean hasFilterChainMatch() {
            return (this.filterChainMatchBuilder_ == null && this.filterChainMatch_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public boolean hasMetadata() {
            return (this.metadataBuilder_ == null && this.metadata_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        @Deprecated
        public boolean hasTlsContext() {
            return (this.tlsContextBuilder_ == null && this.tlsContext_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public boolean hasTransportSocket() {
            return (this.transportSocketBuilder_ == null && this.transportSocket_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public boolean hasUseProxyProto() {
            return (this.useProxyProtoBuilder_ == null && this.useProxyProto_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ListenerComponentsProto.internal_static_envoy_api_v2_listener_FilterChain_fieldAccessorTable.ensureFieldAccessorsInitialized(FilterChain.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (FilterChain.alwaysUseFieldBuilders) {
                getFiltersFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17593clear() {
            super.clear();
            if (this.filterChainMatchBuilder_ == null) {
                this.filterChainMatch_ = null;
            } else {
                this.filterChainMatch_ = null;
                this.filterChainMatchBuilder_ = null;
            }
            if (this.tlsContextBuilder_ == null) {
                this.tlsContext_ = null;
            } else {
                this.tlsContext_ = null;
                this.tlsContextBuilder_ = null;
            }
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.filters_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            if (this.useProxyProtoBuilder_ == null) {
                this.useProxyProto_ = null;
            } else {
                this.useProxyProto_ = null;
                this.useProxyProtoBuilder_ = null;
            }
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            if (this.transportSocketBuilder_ == null) {
                this.transportSocket_ = null;
            } else {
                this.transportSocket_ = null;
                this.transportSocketBuilder_ = null;
            }
            this.name_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ListenerComponentsProto.internal_static_envoy_api_v2_listener_FilterChain_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FilterChain m17606getDefaultInstanceForType() {
            return FilterChain.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FilterChain m17587build() throws UninitializedMessageException {
            FilterChain filterChainM17589buildPartial = m17589buildPartial();
            if (filterChainM17589buildPartial.isInitialized()) {
                return filterChainM17589buildPartial;
            }
            throw newUninitializedMessageException(filterChainM17589buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FilterChain m17589buildPartial() {
            FilterChain filterChain = new FilterChain(this);
            SingleFieldBuilderV3<FilterChainMatch, FilterChainMatch.Builder, FilterChainMatchOrBuilder> singleFieldBuilderV3 = this.filterChainMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                filterChain.filterChainMatch_ = this.filterChainMatch_;
            } else {
                filterChain.filterChainMatch_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<DownstreamTlsContext, DownstreamTlsContext.Builder, DownstreamTlsContextOrBuilder> singleFieldBuilderV32 = this.tlsContextBuilder_;
            if (singleFieldBuilderV32 == null) {
                filterChain.tlsContext_ = this.tlsContext_;
            } else {
                filterChain.tlsContext_ = singleFieldBuilderV32.build();
            }
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.filters_ = Collections.unmodifiableList(this.filters_);
                    this.bitField0_ &= -2;
                }
                filterChain.filters_ = this.filters_;
            } else {
                filterChain.filters_ = repeatedFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV33 = this.useProxyProtoBuilder_;
            if (singleFieldBuilderV33 == null) {
                filterChain.useProxyProto_ = this.useProxyProto_;
            } else {
                filterChain.useProxyProto_ = singleFieldBuilderV33.build();
            }
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV34 = this.metadataBuilder_;
            if (singleFieldBuilderV34 == null) {
                filterChain.metadata_ = this.metadata_;
            } else {
                filterChain.metadata_ = singleFieldBuilderV34.build();
            }
            SingleFieldBuilderV3<TransportSocket, TransportSocket.Builder, TransportSocketOrBuilder> singleFieldBuilderV35 = this.transportSocketBuilder_;
            if (singleFieldBuilderV35 == null) {
                filterChain.transportSocket_ = this.transportSocket_;
            } else {
                filterChain.transportSocket_ = singleFieldBuilderV35.build();
            }
            filterChain.name_ = this.name_;
            onBuilt();
            return filterChain;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17605clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17617setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17595clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17598clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17619setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17585addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17610mergeFrom(Message message) {
            if (message instanceof FilterChain) {
                return mergeFrom((FilterChain) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(FilterChain filterChain) {
            if (filterChain == FilterChain.getDefaultInstance()) {
                return this;
            }
            if (filterChain.hasFilterChainMatch()) {
                mergeFilterChainMatch(filterChain.getFilterChainMatch());
            }
            if (filterChain.hasTlsContext()) {
                mergeTlsContext(filterChain.getTlsContext());
            }
            if (this.filtersBuilder_ == null) {
                if (!filterChain.filters_.isEmpty()) {
                    if (this.filters_.isEmpty()) {
                        this.filters_ = filterChain.filters_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureFiltersIsMutable();
                        this.filters_.addAll(filterChain.filters_);
                    }
                    onChanged();
                }
            } else if (!filterChain.filters_.isEmpty()) {
                if (!this.filtersBuilder_.isEmpty()) {
                    this.filtersBuilder_.addAllMessages(filterChain.filters_);
                } else {
                    this.filtersBuilder_.dispose();
                    this.filtersBuilder_ = null;
                    this.filters_ = filterChain.filters_;
                    this.bitField0_ &= -2;
                    this.filtersBuilder_ = FilterChain.alwaysUseFieldBuilders ? getFiltersFieldBuilder() : null;
                }
            }
            if (filterChain.hasUseProxyProto()) {
                mergeUseProxyProto(filterChain.getUseProxyProto());
            }
            if (filterChain.hasMetadata()) {
                mergeMetadata(filterChain.getMetadata());
            }
            if (filterChain.hasTransportSocket()) {
                mergeTransportSocket(filterChain.getTransportSocket());
            }
            if (!filterChain.getName().isEmpty()) {
                this.name_ = filterChain.name_;
                onChanged();
            }
            m17615mergeUnknownFields(filterChain.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChain.Builder m17611mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChain.access$1300()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChain r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChain) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChain r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChain) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChain.Builder.m17611mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChain$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public FilterChainMatch getFilterChainMatch() {
            SingleFieldBuilderV3<FilterChainMatch, FilterChainMatch.Builder, FilterChainMatchOrBuilder> singleFieldBuilderV3 = this.filterChainMatchBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            FilterChainMatch filterChainMatch = this.filterChainMatch_;
            return filterChainMatch == null ? FilterChainMatch.getDefaultInstance() : filterChainMatch;
        }

        public Builder setFilterChainMatch(FilterChainMatch filterChainMatch) {
            SingleFieldBuilderV3<FilterChainMatch, FilterChainMatch.Builder, FilterChainMatchOrBuilder> singleFieldBuilderV3 = this.filterChainMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                filterChainMatch.getClass();
                this.filterChainMatch_ = filterChainMatch;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(filterChainMatch);
            }
            return this;
        }

        public Builder setFilterChainMatch(FilterChainMatch.Builder builder) {
            SingleFieldBuilderV3<FilterChainMatch, FilterChainMatch.Builder, FilterChainMatchOrBuilder> singleFieldBuilderV3 = this.filterChainMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.filterChainMatch_ = builder.m17635build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m17635build());
            }
            return this;
        }

        public Builder mergeFilterChainMatch(FilterChainMatch filterChainMatch) {
            SingleFieldBuilderV3<FilterChainMatch, FilterChainMatch.Builder, FilterChainMatchOrBuilder> singleFieldBuilderV3 = this.filterChainMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                FilterChainMatch filterChainMatch2 = this.filterChainMatch_;
                if (filterChainMatch2 != null) {
                    this.filterChainMatch_ = FilterChainMatch.newBuilder(filterChainMatch2).mergeFrom(filterChainMatch).m17637buildPartial();
                } else {
                    this.filterChainMatch_ = filterChainMatch;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(filterChainMatch);
            }
            return this;
        }

        public Builder clearFilterChainMatch() {
            if (this.filterChainMatchBuilder_ == null) {
                this.filterChainMatch_ = null;
                onChanged();
            } else {
                this.filterChainMatch_ = null;
                this.filterChainMatchBuilder_ = null;
            }
            return this;
        }

        public FilterChainMatch.Builder getFilterChainMatchBuilder() {
            onChanged();
            return getFilterChainMatchFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public FilterChainMatchOrBuilder getFilterChainMatchOrBuilder() {
            SingleFieldBuilderV3<FilterChainMatch, FilterChainMatch.Builder, FilterChainMatchOrBuilder> singleFieldBuilderV3 = this.filterChainMatchBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (FilterChainMatchOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            FilterChainMatch filterChainMatch = this.filterChainMatch_;
            return filterChainMatch == null ? FilterChainMatch.getDefaultInstance() : filterChainMatch;
        }

        private SingleFieldBuilderV3<FilterChainMatch, FilterChainMatch.Builder, FilterChainMatchOrBuilder> getFilterChainMatchFieldBuilder() {
            if (this.filterChainMatchBuilder_ == null) {
                this.filterChainMatchBuilder_ = new SingleFieldBuilderV3<>(getFilterChainMatch(), getParentForChildren(), isClean());
                this.filterChainMatch_ = null;
            }
            return this.filterChainMatchBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        @Deprecated
        public DownstreamTlsContext getTlsContext() {
            SingleFieldBuilderV3<DownstreamTlsContext, DownstreamTlsContext.Builder, DownstreamTlsContextOrBuilder> singleFieldBuilderV3 = this.tlsContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            DownstreamTlsContext downstreamTlsContext = this.tlsContext_;
            return downstreamTlsContext == null ? DownstreamTlsContext.getDefaultInstance() : downstreamTlsContext;
        }

        @Deprecated
        public Builder setTlsContext(DownstreamTlsContext downstreamTlsContext) {
            SingleFieldBuilderV3<DownstreamTlsContext, DownstreamTlsContext.Builder, DownstreamTlsContextOrBuilder> singleFieldBuilderV3 = this.tlsContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                downstreamTlsContext.getClass();
                this.tlsContext_ = downstreamTlsContext;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(downstreamTlsContext);
            }
            return this;
        }

        @Deprecated
        public Builder setTlsContext(DownstreamTlsContext.Builder builder) {
            SingleFieldBuilderV3<DownstreamTlsContext, DownstreamTlsContext.Builder, DownstreamTlsContextOrBuilder> singleFieldBuilderV3 = this.tlsContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.tlsContext_ = builder.m13615build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m13615build());
            }
            return this;
        }

        @Deprecated
        public Builder mergeTlsContext(DownstreamTlsContext downstreamTlsContext) {
            SingleFieldBuilderV3<DownstreamTlsContext, DownstreamTlsContext.Builder, DownstreamTlsContextOrBuilder> singleFieldBuilderV3 = this.tlsContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                DownstreamTlsContext downstreamTlsContext2 = this.tlsContext_;
                if (downstreamTlsContext2 != null) {
                    this.tlsContext_ = DownstreamTlsContext.newBuilder(downstreamTlsContext2).mergeFrom(downstreamTlsContext).m13617buildPartial();
                } else {
                    this.tlsContext_ = downstreamTlsContext;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(downstreamTlsContext);
            }
            return this;
        }

        @Deprecated
        public Builder clearTlsContext() {
            if (this.tlsContextBuilder_ == null) {
                this.tlsContext_ = null;
                onChanged();
            } else {
                this.tlsContext_ = null;
                this.tlsContextBuilder_ = null;
            }
            return this;
        }

        @Deprecated
        public DownstreamTlsContext.Builder getTlsContextBuilder() {
            onChanged();
            return getTlsContextFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        @Deprecated
        public DownstreamTlsContextOrBuilder getTlsContextOrBuilder() {
            SingleFieldBuilderV3<DownstreamTlsContext, DownstreamTlsContext.Builder, DownstreamTlsContextOrBuilder> singleFieldBuilderV3 = this.tlsContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (DownstreamTlsContextOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            DownstreamTlsContext downstreamTlsContext = this.tlsContext_;
            return downstreamTlsContext == null ? DownstreamTlsContext.getDefaultInstance() : downstreamTlsContext;
        }

        private SingleFieldBuilderV3<DownstreamTlsContext, DownstreamTlsContext.Builder, DownstreamTlsContextOrBuilder> getTlsContextFieldBuilder() {
            if (this.tlsContextBuilder_ == null) {
                this.tlsContextBuilder_ = new SingleFieldBuilderV3<>(getTlsContext(), getParentForChildren(), isClean());
                this.tlsContext_ = null;
            }
            return this.tlsContextBuilder_;
        }

        private void ensureFiltersIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.filters_ = new ArrayList(this.filters_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public List<Filter> getFiltersList() {
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.filters_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public int getFiltersCount() {
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.filters_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public Filter getFilters(int i) {
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.filters_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setFilters(int i, Filter filter) {
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                filter.getClass();
                ensureFiltersIsMutable();
                this.filters_.set(i, filter);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, filter);
            }
            return this;
        }

        public Builder setFilters(int i, Filter.Builder builder) {
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFiltersIsMutable();
                this.filters_.set(i, builder.m17541build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m17541build());
            }
            return this;
        }

        public Builder addFilters(Filter filter) {
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                filter.getClass();
                ensureFiltersIsMutable();
                this.filters_.add(filter);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(filter);
            }
            return this;
        }

        public Builder addFilters(int i, Filter filter) {
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                filter.getClass();
                ensureFiltersIsMutable();
                this.filters_.add(i, filter);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, filter);
            }
            return this;
        }

        public Builder addFilters(Filter.Builder builder) {
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFiltersIsMutable();
                this.filters_.add(builder.m17541build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m17541build());
            }
            return this;
        }

        public Builder addFilters(int i, Filter.Builder builder) {
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFiltersIsMutable();
                this.filters_.add(i, builder.m17541build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m17541build());
            }
            return this;
        }

        public Builder addAllFilters(Iterable<? extends Filter> iterable) {
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFiltersIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.filters_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearFilters() {
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.filters_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeFilters(int i) {
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFiltersIsMutable();
                this.filters_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Filter.Builder getFiltersBuilder(int i) {
            return getFiltersFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public FilterOrBuilder getFiltersOrBuilder(int i) {
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.filters_.get(i);
            }
            return (FilterOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public List<? extends FilterOrBuilder> getFiltersOrBuilderList() {
            RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> repeatedFieldBuilderV3 = this.filtersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.filters_);
        }

        public Filter.Builder addFiltersBuilder() {
            return getFiltersFieldBuilder().addBuilder(Filter.getDefaultInstance());
        }

        public Filter.Builder addFiltersBuilder(int i) {
            return getFiltersFieldBuilder().addBuilder(i, Filter.getDefaultInstance());
        }

        public List<Filter.Builder> getFiltersBuilderList() {
            return getFiltersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Filter, Filter.Builder, FilterOrBuilder> getFiltersFieldBuilder() {
            if (this.filtersBuilder_ == null) {
                this.filtersBuilder_ = new RepeatedFieldBuilderV3<>(this.filters_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.filters_ = null;
            }
            return this.filtersBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public BoolValue getUseProxyProto() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.useProxyProtoBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            BoolValue boolValue = this.useProxyProto_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        public Builder setUseProxyProto(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.useProxyProtoBuilder_;
            if (singleFieldBuilderV3 == null) {
                boolValue.getClass();
                this.useProxyProto_ = boolValue;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(boolValue);
            }
            return this;
        }

        public Builder setUseProxyProto(BoolValue.Builder builder) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.useProxyProtoBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.useProxyProto_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeUseProxyProto(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.useProxyProtoBuilder_;
            if (singleFieldBuilderV3 == null) {
                BoolValue boolValue2 = this.useProxyProto_;
                if (boolValue2 != null) {
                    this.useProxyProto_ = BoolValue.newBuilder(boolValue2).mergeFrom(boolValue).buildPartial();
                } else {
                    this.useProxyProto_ = boolValue;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(boolValue);
            }
            return this;
        }

        public Builder clearUseProxyProto() {
            if (this.useProxyProtoBuilder_ == null) {
                this.useProxyProto_ = null;
                onChanged();
            } else {
                this.useProxyProto_ = null;
                this.useProxyProtoBuilder_ = null;
            }
            return this;
        }

        public BoolValue.Builder getUseProxyProtoBuilder() {
            onChanged();
            return getUseProxyProtoFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public BoolValueOrBuilder getUseProxyProtoOrBuilder() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.useProxyProtoBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            BoolValue boolValue = this.useProxyProto_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> getUseProxyProtoFieldBuilder() {
            if (this.useProxyProtoBuilder_ == null) {
                this.useProxyProtoBuilder_ = new SingleFieldBuilderV3<>(getUseProxyProto(), getParentForChildren(), isClean());
                this.useProxyProto_ = null;
            }
            return this.useProxyProtoBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public Metadata getMetadata() {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Metadata metadata = this.metadata_;
            return metadata == null ? Metadata.getDefaultInstance() : metadata;
        }

        public Builder setMetadata(Metadata metadata) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                metadata.getClass();
                this.metadata_ = metadata;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(metadata);
            }
            return this;
        }

        public Builder setMetadata(Metadata.Builder builder) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.metadata_ = builder.m16293build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m16293build());
            }
            return this;
        }

        public Builder mergeMetadata(Metadata metadata) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Metadata metadata2 = this.metadata_;
                if (metadata2 != null) {
                    this.metadata_ = Metadata.newBuilder(metadata2).mergeFrom(metadata).m16295buildPartial();
                } else {
                    this.metadata_ = metadata;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(metadata);
            }
            return this;
        }

        public Builder clearMetadata() {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
                onChanged();
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        public Metadata.Builder getMetadataBuilder() {
            onChanged();
            return getMetadataFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public MetadataOrBuilder getMetadataOrBuilder() {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (MetadataOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Metadata metadata = this.metadata_;
            return metadata == null ? Metadata.getDefaultInstance() : metadata;
        }

        private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> getMetadataFieldBuilder() {
            if (this.metadataBuilder_ == null) {
                this.metadataBuilder_ = new SingleFieldBuilderV3<>(getMetadata(), getParentForChildren(), isClean());
                this.metadata_ = null;
            }
            return this.metadataBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public TransportSocket getTransportSocket() {
            SingleFieldBuilderV3<TransportSocket, TransportSocket.Builder, TransportSocketOrBuilder> singleFieldBuilderV3 = this.transportSocketBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            TransportSocket transportSocket = this.transportSocket_;
            return transportSocket == null ? TransportSocket.getDefaultInstance() : transportSocket;
        }

        public Builder setTransportSocket(TransportSocket transportSocket) {
            SingleFieldBuilderV3<TransportSocket, TransportSocket.Builder, TransportSocketOrBuilder> singleFieldBuilderV3 = this.transportSocketBuilder_;
            if (singleFieldBuilderV3 == null) {
                transportSocket.getClass();
                this.transportSocket_ = transportSocket;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(transportSocket);
            }
            return this;
        }

        public Builder setTransportSocket(TransportSocket.Builder builder) {
            SingleFieldBuilderV3<TransportSocket, TransportSocket.Builder, TransportSocketOrBuilder> singleFieldBuilderV3 = this.transportSocketBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.transportSocket_ = builder.m16989build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m16989build());
            }
            return this;
        }

        public Builder mergeTransportSocket(TransportSocket transportSocket) {
            SingleFieldBuilderV3<TransportSocket, TransportSocket.Builder, TransportSocketOrBuilder> singleFieldBuilderV3 = this.transportSocketBuilder_;
            if (singleFieldBuilderV3 == null) {
                TransportSocket transportSocket2 = this.transportSocket_;
                if (transportSocket2 != null) {
                    this.transportSocket_ = TransportSocket.newBuilder(transportSocket2).mergeFrom(transportSocket).m16991buildPartial();
                } else {
                    this.transportSocket_ = transportSocket;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(transportSocket);
            }
            return this;
        }

        public Builder clearTransportSocket() {
            if (this.transportSocketBuilder_ == null) {
                this.transportSocket_ = null;
                onChanged();
            } else {
                this.transportSocket_ = null;
                this.transportSocketBuilder_ = null;
            }
            return this;
        }

        public TransportSocket.Builder getTransportSocketBuilder() {
            onChanged();
            return getTransportSocketFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
        public TransportSocketOrBuilder getTransportSocketOrBuilder() {
            SingleFieldBuilderV3<TransportSocket, TransportSocket.Builder, TransportSocketOrBuilder> singleFieldBuilderV3 = this.transportSocketBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (TransportSocketOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            TransportSocket transportSocket = this.transportSocket_;
            return transportSocket == null ? TransportSocket.getDefaultInstance() : transportSocket;
        }

        private SingleFieldBuilderV3<TransportSocket, TransportSocket.Builder, TransportSocketOrBuilder> getTransportSocketFieldBuilder() {
            if (this.transportSocketBuilder_ == null) {
                this.transportSocketBuilder_ = new SingleFieldBuilderV3<>(getTransportSocket(), getParentForChildren(), isClean());
                this.transportSocket_ = null;
            }
            return this.transportSocketBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainOrBuilder
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
            FilterChain.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = FilterChain.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m17621setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m17615mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
