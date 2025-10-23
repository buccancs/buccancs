package io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Node;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.NodeOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class DeltaDiscoveryRequest extends GeneratedMessageV3 implements DeltaDiscoveryRequestOrBuilder {
    public static final int ERROR_DETAIL_FIELD_NUMBER = 7;
    public static final int INITIAL_RESOURCE_VERSIONS_FIELD_NUMBER = 5;
    public static final int NODE_FIELD_NUMBER = 1;
    public static final int RESOURCE_NAMES_SUBSCRIBE_FIELD_NUMBER = 3;
    public static final int RESOURCE_NAMES_UNSUBSCRIBE_FIELD_NUMBER = 4;
    public static final int RESPONSE_NONCE_FIELD_NUMBER = 6;
    public static final int TYPE_URL_FIELD_NUMBER = 2;
    public static final int UDPA_RESOURCES_SUBSCRIBE_FIELD_NUMBER = 8;
    public static final int UDPA_RESOURCES_UNSUBSCRIBE_FIELD_NUMBER = 9;
    private static final long serialVersionUID = 0;
    private static final DeltaDiscoveryRequest DEFAULT_INSTANCE = new DeltaDiscoveryRequest();
    private static final Parser<DeltaDiscoveryRequest> PARSER = new AbstractParser<DeltaDiscoveryRequest>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequest.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public DeltaDiscoveryRequest m32395parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DeltaDiscoveryRequest(codedInputStream, extensionRegistryLite);
        }
    };
    private Status errorDetail_;
    private MapField<String, String> initialResourceVersions_;
    private byte memoizedIsInitialized;
    private Node node_;
    private LazyStringList resourceNamesSubscribe_;
    private LazyStringList resourceNamesUnsubscribe_;
    private volatile Object responseNonce_;
    private volatile Object typeUrl_;
    private List<ResourceLocator> udpaResourcesSubscribe_;
    private List<ResourceLocator> udpaResourcesUnsubscribe_;

    private DeltaDiscoveryRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DeltaDiscoveryRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.typeUrl_ = "";
        this.resourceNamesSubscribe_ = LazyStringArrayList.EMPTY;
        this.udpaResourcesSubscribe_ = Collections.emptyList();
        this.resourceNamesUnsubscribe_ = LazyStringArrayList.EMPTY;
        this.udpaResourcesUnsubscribe_ = Collections.emptyList();
        this.responseNonce_ = "";
    }

    private DeltaDiscoveryRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            Node node = this.node_;
                            Node.Builder builderM23645toBuilder = node != null ? node.m23645toBuilder() : null;
                            Node node2 = (Node) codedInputStream.readMessage(Node.parser(), extensionRegistryLite);
                            this.node_ = node2;
                            if (builderM23645toBuilder != null) {
                                builderM23645toBuilder.mergeFrom(node2);
                                this.node_ = builderM23645toBuilder.m23652buildPartial();
                            }
                        } else if (tag == 18) {
                            this.typeUrl_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 26) {
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if ((i & 1) == 0) {
                                this.resourceNamesSubscribe_ = new LazyStringArrayList();
                                i |= 1;
                            }
                            this.resourceNamesSubscribe_.add(stringRequireUtf8);
                        } else if (tag == 34) {
                            String stringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            if ((i & 4) == 0) {
                                this.resourceNamesUnsubscribe_ = new LazyStringArrayList();
                                i |= 4;
                            }
                            this.resourceNamesUnsubscribe_.add(stringRequireUtf82);
                        } else if (tag == 42) {
                            if ((i & 16) == 0) {
                                this.initialResourceVersions_ = MapField.newMapField(InitialResourceVersionsDefaultEntryHolder.defaultEntry);
                                i |= 16;
                            }
                            MapEntry message = codedInputStream.readMessage(InitialResourceVersionsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                            this.initialResourceVersions_.getMutableMap().put(message.getKey(), message.getValue());
                        } else if (tag == 50) {
                            this.responseNonce_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 58) {
                            Status status = this.errorDetail_;
                            Status.Builder builderM3997toBuilder = status != null ? status.m3997toBuilder() : null;
                            Status status2 = (Status) codedInputStream.readMessage(Status.parser(), extensionRegistryLite);
                            this.errorDetail_ = status2;
                            if (builderM3997toBuilder != null) {
                                builderM3997toBuilder.mergeFrom(status2);
                                this.errorDetail_ = builderM3997toBuilder.m4004buildPartial();
                            }
                        } else if (tag == 66) {
                            if ((i & 2) == 0) {
                                this.udpaResourcesSubscribe_ = new ArrayList();
                                i |= 2;
                            }
                            this.udpaResourcesSubscribe_.add(codedInputStream.readMessage(ResourceLocator.parser(), extensionRegistryLite));
                        } else if (tag == 74) {
                            if ((i & 8) == 0) {
                                this.udpaResourcesUnsubscribe_ = new ArrayList();
                                i |= 8;
                            }
                            this.udpaResourcesUnsubscribe_.add(codedInputStream.readMessage(ResourceLocator.parser(), extensionRegistryLite));
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
                if ((i & 1) != 0) {
                    this.resourceNamesSubscribe_ = this.resourceNamesSubscribe_.getUnmodifiableView();
                }
                if ((i & 4) != 0) {
                    this.resourceNamesUnsubscribe_ = this.resourceNamesUnsubscribe_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.udpaResourcesSubscribe_ = Collections.unmodifiableList(this.udpaResourcesSubscribe_);
                }
                if ((i & 8) != 0) {
                    this.udpaResourcesUnsubscribe_ = Collections.unmodifiableList(this.udpaResourcesUnsubscribe_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static DeltaDiscoveryRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DeltaDiscoveryRequest> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DiscoveryProto.internal_static_envoy_service_discovery_v3_DeltaDiscoveryRequest_descriptor;
    }

    public static DeltaDiscoveryRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DeltaDiscoveryRequest) PARSER.parseFrom(byteBuffer);
    }

    public static DeltaDiscoveryRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeltaDiscoveryRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DeltaDiscoveryRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DeltaDiscoveryRequest) PARSER.parseFrom(byteString);
    }

    public static DeltaDiscoveryRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeltaDiscoveryRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DeltaDiscoveryRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DeltaDiscoveryRequest) PARSER.parseFrom(bArr);
    }

    public static DeltaDiscoveryRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeltaDiscoveryRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DeltaDiscoveryRequest parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DeltaDiscoveryRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DeltaDiscoveryRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DeltaDiscoveryRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DeltaDiscoveryRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DeltaDiscoveryRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m32393toBuilder();
    }

    public static Builder newBuilder(DeltaDiscoveryRequest deltaDiscoveryRequest) {
        return DEFAULT_INSTANCE.m32393toBuilder().mergeFrom(deltaDiscoveryRequest);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public DeltaDiscoveryRequest m32386getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<DeltaDiscoveryRequest> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    /* renamed from: getResourceNamesSubscribeList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo32388getResourceNamesSubscribeList() {
        return this.resourceNamesSubscribe_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    /* renamed from: getResourceNamesUnsubscribeList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo32389getResourceNamesUnsubscribeList() {
        return this.resourceNamesUnsubscribe_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public List<ResourceLocator> getUdpaResourcesSubscribeList() {
        return this.udpaResourcesSubscribe_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public List<? extends ResourceLocatorOrBuilder> getUdpaResourcesSubscribeOrBuilderList() {
        return this.udpaResourcesSubscribe_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public List<ResourceLocator> getUdpaResourcesUnsubscribeList() {
        return this.udpaResourcesUnsubscribe_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public List<? extends ResourceLocatorOrBuilder> getUdpaResourcesUnsubscribeOrBuilderList() {
        return this.udpaResourcesUnsubscribe_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public boolean hasErrorDetail() {
        return this.errorDetail_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public boolean hasNode() {
        return this.node_ != null;
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
        return new DeltaDiscoveryRequest();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected MapField internalGetMapField(int i) {
        if (i == 5) {
            return internalGetInitialResourceVersions();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DiscoveryProto.internal_static_envoy_service_discovery_v3_DeltaDiscoveryRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(DeltaDiscoveryRequest.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public Node getNode() {
        Node node = this.node_;
        return node == null ? Node.getDefaultInstance() : node;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public NodeOrBuilder getNodeOrBuilder() {
        return getNode();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public String getTypeUrl() {
        Object obj = this.typeUrl_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.typeUrl_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public ByteString getTypeUrlBytes() {
        Object obj = this.typeUrl_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.typeUrl_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public int getResourceNamesSubscribeCount() {
        return this.resourceNamesSubscribe_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public String getResourceNamesSubscribe(int i) {
        return (String) this.resourceNamesSubscribe_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public ByteString getResourceNamesSubscribeBytes(int i) {
        return this.resourceNamesSubscribe_.getByteString(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public int getUdpaResourcesSubscribeCount() {
        return this.udpaResourcesSubscribe_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public ResourceLocator getUdpaResourcesSubscribe(int i) {
        return this.udpaResourcesSubscribe_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public ResourceLocatorOrBuilder getUdpaResourcesSubscribeOrBuilder(int i) {
        return this.udpaResourcesSubscribe_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public int getResourceNamesUnsubscribeCount() {
        return this.resourceNamesUnsubscribe_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public String getResourceNamesUnsubscribe(int i) {
        return (String) this.resourceNamesUnsubscribe_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public ByteString getResourceNamesUnsubscribeBytes(int i) {
        return this.resourceNamesUnsubscribe_.getByteString(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public int getUdpaResourcesUnsubscribeCount() {
        return this.udpaResourcesUnsubscribe_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public ResourceLocator getUdpaResourcesUnsubscribe(int i) {
        return this.udpaResourcesUnsubscribe_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public ResourceLocatorOrBuilder getUdpaResourcesUnsubscribeOrBuilder(int i) {
        return this.udpaResourcesUnsubscribe_.get(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, String> internalGetInitialResourceVersions() {
        MapField<String, String> mapField = this.initialResourceVersions_;
        return mapField == null ? MapField.emptyMapField(InitialResourceVersionsDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public int getInitialResourceVersionsCount() {
        return internalGetInitialResourceVersions().getMap().size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public boolean containsInitialResourceVersions(String str) {
        str.getClass();
        return internalGetInitialResourceVersions().getMap().containsKey(str);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    @Deprecated
    public Map<String, String> getInitialResourceVersions() {
        return getInitialResourceVersionsMap();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public Map<String, String> getInitialResourceVersionsMap() {
        return internalGetInitialResourceVersions().getMap();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public String getInitialResourceVersionsOrDefault(String str, String str2) {
        str.getClass();
        Map map = internalGetInitialResourceVersions().getMap();
        return map.containsKey(str) ? (String) map.get(str) : str2;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public String getInitialResourceVersionsOrThrow(String str) {
        str.getClass();
        Map map = internalGetInitialResourceVersions().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return (String) map.get(str);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public String getResponseNonce() {
        Object obj = this.responseNonce_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.responseNonce_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public ByteString getResponseNonceBytes() {
        Object obj = this.responseNonce_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.responseNonce_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public Status getErrorDetail() {
        Status status = this.errorDetail_;
        return status == null ? Status.getDefaultInstance() : status;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
    public StatusOrBuilder getErrorDetailOrBuilder() {
        return getErrorDetail();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.node_ != null) {
            codedOutputStream.writeMessage(1, getNode());
        }
        if (!getTypeUrlBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.typeUrl_);
        }
        for (int i = 0; i < this.resourceNamesSubscribe_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.resourceNamesSubscribe_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.resourceNamesUnsubscribe_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.resourceNamesUnsubscribe_.getRaw(i2));
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetInitialResourceVersions(), InitialResourceVersionsDefaultEntryHolder.defaultEntry, 5);
        if (!getResponseNonceBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.responseNonce_);
        }
        if (this.errorDetail_ != null) {
            codedOutputStream.writeMessage(7, getErrorDetail());
        }
        for (int i3 = 0; i3 < this.udpaResourcesSubscribe_.size(); i3++) {
            codedOutputStream.writeMessage(8, this.udpaResourcesSubscribe_.get(i3));
        }
        for (int i4 = 0; i4 < this.udpaResourcesUnsubscribe_.size(); i4++) {
            codedOutputStream.writeMessage(9, this.udpaResourcesUnsubscribe_.get(i4));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.node_ != null ? CodedOutputStream.computeMessageSize(1, getNode()) : 0;
        if (!getTypeUrlBytes().isEmpty()) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(2, this.typeUrl_);
        }
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.resourceNamesSubscribe_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.resourceNamesSubscribe_.getRaw(i2));
        }
        int size = iComputeMessageSize + iComputeStringSizeNoTag + mo32388getResourceNamesSubscribeList().size();
        int iComputeStringSizeNoTag2 = 0;
        for (int i3 = 0; i3 < this.resourceNamesUnsubscribe_.size(); i3++) {
            iComputeStringSizeNoTag2 += computeStringSizeNoTag(this.resourceNamesUnsubscribe_.getRaw(i3));
        }
        int size2 = size + iComputeStringSizeNoTag2 + mo32389getResourceNamesUnsubscribeList().size();
        for (Map.Entry entry : internalGetInitialResourceVersions().getMap().entrySet()) {
            size2 += CodedOutputStream.computeMessageSize(5, InitialResourceVersionsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        if (!getResponseNonceBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(6, this.responseNonce_);
        }
        if (this.errorDetail_ != null) {
            size2 += CodedOutputStream.computeMessageSize(7, getErrorDetail());
        }
        for (int i4 = 0; i4 < this.udpaResourcesSubscribe_.size(); i4++) {
            size2 += CodedOutputStream.computeMessageSize(8, this.udpaResourcesSubscribe_.get(i4));
        }
        for (int i5 = 0; i5 < this.udpaResourcesUnsubscribe_.size(); i5++) {
            size2 += CodedOutputStream.computeMessageSize(9, this.udpaResourcesUnsubscribe_.get(i5));
        }
        int serializedSize = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeltaDiscoveryRequest)) {
            return super.equals(obj);
        }
        DeltaDiscoveryRequest deltaDiscoveryRequest = (DeltaDiscoveryRequest) obj;
        if (hasNode() != deltaDiscoveryRequest.hasNode()) {
            return false;
        }
        if ((!hasNode() || getNode().equals(deltaDiscoveryRequest.getNode())) && getTypeUrl().equals(deltaDiscoveryRequest.getTypeUrl()) && mo32388getResourceNamesSubscribeList().equals(deltaDiscoveryRequest.mo32388getResourceNamesSubscribeList()) && getUdpaResourcesSubscribeList().equals(deltaDiscoveryRequest.getUdpaResourcesSubscribeList()) && mo32389getResourceNamesUnsubscribeList().equals(deltaDiscoveryRequest.mo32389getResourceNamesUnsubscribeList()) && getUdpaResourcesUnsubscribeList().equals(deltaDiscoveryRequest.getUdpaResourcesUnsubscribeList()) && internalGetInitialResourceVersions().equals(deltaDiscoveryRequest.internalGetInitialResourceVersions()) && getResponseNonce().equals(deltaDiscoveryRequest.getResponseNonce()) && hasErrorDetail() == deltaDiscoveryRequest.hasErrorDetail()) {
            return (!hasErrorDetail() || getErrorDetail().equals(deltaDiscoveryRequest.getErrorDetail())) && this.unknownFields.equals(deltaDiscoveryRequest.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasNode()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getNode().hashCode();
        }
        int iHashCode2 = (((iHashCode * 37) + 2) * 53) + getTypeUrl().hashCode();
        if (getResourceNamesSubscribeCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 3) * 53) + mo32388getResourceNamesSubscribeList().hashCode();
        }
        if (getUdpaResourcesSubscribeCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 8) * 53) + getUdpaResourcesSubscribeList().hashCode();
        }
        if (getResourceNamesUnsubscribeCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 4) * 53) + mo32389getResourceNamesUnsubscribeList().hashCode();
        }
        if (getUdpaResourcesUnsubscribeCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 9) * 53) + getUdpaResourcesUnsubscribeList().hashCode();
        }
        if (!internalGetInitialResourceVersions().getMap().isEmpty()) {
            iHashCode2 = (((iHashCode2 * 37) + 5) * 53) + internalGetInitialResourceVersions().hashCode();
        }
        int iHashCode3 = (((iHashCode2 * 37) + 6) * 53) + getResponseNonce().hashCode();
        if (hasErrorDetail()) {
            iHashCode3 = (((iHashCode3 * 37) + 7) * 53) + getErrorDetail().hashCode();
        }
        int iHashCode4 = (iHashCode3 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode4;
        return iHashCode4;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m32390newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m32393toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    private static final class InitialResourceVersionsDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(DiscoveryProto.internal_static_envoy_service_discovery_v3_DeltaDiscoveryRequest_InitialResourceVersionsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private InitialResourceVersionsDefaultEntryHolder() {
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DeltaDiscoveryRequestOrBuilder {
        private int bitField0_;
        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> errorDetailBuilder_;
        private Status errorDetail_;
        private MapField<String, String> initialResourceVersions_;
        private SingleFieldBuilderV3<Node, Node.Builder, NodeOrBuilder> nodeBuilder_;
        private Node node_;
        private LazyStringList resourceNamesSubscribe_;
        private LazyStringList resourceNamesUnsubscribe_;
        private Object responseNonce_;
        private Object typeUrl_;
        private RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> udpaResourcesSubscribeBuilder_;
        private List<ResourceLocator> udpaResourcesSubscribe_;
        private RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> udpaResourcesUnsubscribeBuilder_;
        private List<ResourceLocator> udpaResourcesUnsubscribe_;

        private Builder() {
            this.typeUrl_ = "";
            this.resourceNamesSubscribe_ = LazyStringArrayList.EMPTY;
            this.udpaResourcesSubscribe_ = Collections.emptyList();
            this.resourceNamesUnsubscribe_ = LazyStringArrayList.EMPTY;
            this.udpaResourcesUnsubscribe_ = Collections.emptyList();
            this.responseNonce_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.typeUrl_ = "";
            this.resourceNamesSubscribe_ = LazyStringArrayList.EMPTY;
            this.udpaResourcesSubscribe_ = Collections.emptyList();
            this.resourceNamesUnsubscribe_ = LazyStringArrayList.EMPTY;
            this.udpaResourcesUnsubscribe_ = Collections.emptyList();
            this.responseNonce_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DiscoveryProto.internal_static_envoy_service_discovery_v3_DeltaDiscoveryRequest_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public boolean hasErrorDetail() {
            return (this.errorDetailBuilder_ == null && this.errorDetail_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public boolean hasNode() {
            return (this.nodeBuilder_ == null && this.node_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 5) {
                return internalGetInitialResourceVersions();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected MapField internalGetMutableMapField(int i) {
            if (i == 5) {
                return internalGetMutableInitialResourceVersions();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DiscoveryProto.internal_static_envoy_service_discovery_v3_DeltaDiscoveryRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(DeltaDiscoveryRequest.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (DeltaDiscoveryRequest.alwaysUseFieldBuilders) {
                getUdpaResourcesSubscribeFieldBuilder();
                getUdpaResourcesUnsubscribeFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32404clear() {
            super.clear();
            if (this.nodeBuilder_ == null) {
                this.node_ = null;
            } else {
                this.node_ = null;
                this.nodeBuilder_ = null;
            }
            this.typeUrl_ = "";
            this.resourceNamesSubscribe_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.udpaResourcesSubscribe_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.resourceNamesUnsubscribe_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV32 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.udpaResourcesUnsubscribe_ = Collections.emptyList();
                this.bitField0_ &= -9;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            internalGetMutableInitialResourceVersions().clear();
            this.responseNonce_ = "";
            if (this.errorDetailBuilder_ == null) {
                this.errorDetail_ = null;
            } else {
                this.errorDetail_ = null;
                this.errorDetailBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return DiscoveryProto.internal_static_envoy_service_discovery_v3_DeltaDiscoveryRequest_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DeltaDiscoveryRequest m32417getDefaultInstanceForType() {
            return DeltaDiscoveryRequest.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DeltaDiscoveryRequest m32398build() throws UninitializedMessageException {
            DeltaDiscoveryRequest deltaDiscoveryRequestM32400buildPartial = m32400buildPartial();
            if (deltaDiscoveryRequestM32400buildPartial.isInitialized()) {
                return deltaDiscoveryRequestM32400buildPartial;
            }
            throw newUninitializedMessageException(deltaDiscoveryRequestM32400buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DeltaDiscoveryRequest m32400buildPartial() {
            DeltaDiscoveryRequest deltaDiscoveryRequest = new DeltaDiscoveryRequest(this);
            SingleFieldBuilderV3<Node, Node.Builder, NodeOrBuilder> singleFieldBuilderV3 = this.nodeBuilder_;
            if (singleFieldBuilderV3 == null) {
                deltaDiscoveryRequest.node_ = this.node_;
            } else {
                deltaDiscoveryRequest.node_ = singleFieldBuilderV3.build();
            }
            deltaDiscoveryRequest.typeUrl_ = this.typeUrl_;
            if ((this.bitField0_ & 1) != 0) {
                this.resourceNamesSubscribe_ = this.resourceNamesSubscribe_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            deltaDiscoveryRequest.resourceNamesSubscribe_ = this.resourceNamesSubscribe_;
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) != 0) {
                    this.udpaResourcesSubscribe_ = Collections.unmodifiableList(this.udpaResourcesSubscribe_);
                    this.bitField0_ &= -3;
                }
                deltaDiscoveryRequest.udpaResourcesSubscribe_ = this.udpaResourcesSubscribe_;
            } else {
                deltaDiscoveryRequest.udpaResourcesSubscribe_ = repeatedFieldBuilderV3.build();
            }
            if ((this.bitField0_ & 4) != 0) {
                this.resourceNamesUnsubscribe_ = this.resourceNamesUnsubscribe_.getUnmodifiableView();
                this.bitField0_ &= -5;
            }
            deltaDiscoveryRequest.resourceNamesUnsubscribe_ = this.resourceNamesUnsubscribe_;
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV32 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 8) != 0) {
                    this.udpaResourcesUnsubscribe_ = Collections.unmodifiableList(this.udpaResourcesUnsubscribe_);
                    this.bitField0_ &= -9;
                }
                deltaDiscoveryRequest.udpaResourcesUnsubscribe_ = this.udpaResourcesUnsubscribe_;
            } else {
                deltaDiscoveryRequest.udpaResourcesUnsubscribe_ = repeatedFieldBuilderV32.build();
            }
            deltaDiscoveryRequest.initialResourceVersions_ = internalGetInitialResourceVersions();
            deltaDiscoveryRequest.initialResourceVersions_.makeImmutable();
            deltaDiscoveryRequest.responseNonce_ = this.responseNonce_;
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV32 = this.errorDetailBuilder_;
            if (singleFieldBuilderV32 == null) {
                deltaDiscoveryRequest.errorDetail_ = this.errorDetail_;
            } else {
                deltaDiscoveryRequest.errorDetail_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return deltaDiscoveryRequest;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32416clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32428setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32406clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32409clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32430setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32396addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m32421mergeFrom(Message message) {
            if (message instanceof DeltaDiscoveryRequest) {
                return mergeFrom((DeltaDiscoveryRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DeltaDiscoveryRequest deltaDiscoveryRequest) {
            if (deltaDiscoveryRequest == DeltaDiscoveryRequest.getDefaultInstance()) {
                return this;
            }
            if (deltaDiscoveryRequest.hasNode()) {
                mergeNode(deltaDiscoveryRequest.getNode());
            }
            if (!deltaDiscoveryRequest.getTypeUrl().isEmpty()) {
                this.typeUrl_ = deltaDiscoveryRequest.typeUrl_;
                onChanged();
            }
            if (!deltaDiscoveryRequest.resourceNamesSubscribe_.isEmpty()) {
                if (this.resourceNamesSubscribe_.isEmpty()) {
                    this.resourceNamesSubscribe_ = deltaDiscoveryRequest.resourceNamesSubscribe_;
                    this.bitField0_ &= -2;
                } else {
                    ensureResourceNamesSubscribeIsMutable();
                    this.resourceNamesSubscribe_.addAll(deltaDiscoveryRequest.resourceNamesSubscribe_);
                }
                onChanged();
            }
            if (this.udpaResourcesSubscribeBuilder_ == null) {
                if (!deltaDiscoveryRequest.udpaResourcesSubscribe_.isEmpty()) {
                    if (this.udpaResourcesSubscribe_.isEmpty()) {
                        this.udpaResourcesSubscribe_ = deltaDiscoveryRequest.udpaResourcesSubscribe_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureUdpaResourcesSubscribeIsMutable();
                        this.udpaResourcesSubscribe_.addAll(deltaDiscoveryRequest.udpaResourcesSubscribe_);
                    }
                    onChanged();
                }
            } else if (!deltaDiscoveryRequest.udpaResourcesSubscribe_.isEmpty()) {
                if (!this.udpaResourcesSubscribeBuilder_.isEmpty()) {
                    this.udpaResourcesSubscribeBuilder_.addAllMessages(deltaDiscoveryRequest.udpaResourcesSubscribe_);
                } else {
                    this.udpaResourcesSubscribeBuilder_.dispose();
                    this.udpaResourcesSubscribeBuilder_ = null;
                    this.udpaResourcesSubscribe_ = deltaDiscoveryRequest.udpaResourcesSubscribe_;
                    this.bitField0_ &= -3;
                    this.udpaResourcesSubscribeBuilder_ = DeltaDiscoveryRequest.alwaysUseFieldBuilders ? getUdpaResourcesSubscribeFieldBuilder() : null;
                }
            }
            if (!deltaDiscoveryRequest.resourceNamesUnsubscribe_.isEmpty()) {
                if (this.resourceNamesUnsubscribe_.isEmpty()) {
                    this.resourceNamesUnsubscribe_ = deltaDiscoveryRequest.resourceNamesUnsubscribe_;
                    this.bitField0_ &= -5;
                } else {
                    ensureResourceNamesUnsubscribeIsMutable();
                    this.resourceNamesUnsubscribe_.addAll(deltaDiscoveryRequest.resourceNamesUnsubscribe_);
                }
                onChanged();
            }
            if (this.udpaResourcesUnsubscribeBuilder_ == null) {
                if (!deltaDiscoveryRequest.udpaResourcesUnsubscribe_.isEmpty()) {
                    if (this.udpaResourcesUnsubscribe_.isEmpty()) {
                        this.udpaResourcesUnsubscribe_ = deltaDiscoveryRequest.udpaResourcesUnsubscribe_;
                        this.bitField0_ &= -9;
                    } else {
                        ensureUdpaResourcesUnsubscribeIsMutable();
                        this.udpaResourcesUnsubscribe_.addAll(deltaDiscoveryRequest.udpaResourcesUnsubscribe_);
                    }
                    onChanged();
                }
            } else if (!deltaDiscoveryRequest.udpaResourcesUnsubscribe_.isEmpty()) {
                if (!this.udpaResourcesUnsubscribeBuilder_.isEmpty()) {
                    this.udpaResourcesUnsubscribeBuilder_.addAllMessages(deltaDiscoveryRequest.udpaResourcesUnsubscribe_);
                } else {
                    this.udpaResourcesUnsubscribeBuilder_.dispose();
                    this.udpaResourcesUnsubscribeBuilder_ = null;
                    this.udpaResourcesUnsubscribe_ = deltaDiscoveryRequest.udpaResourcesUnsubscribe_;
                    this.bitField0_ &= -9;
                    this.udpaResourcesUnsubscribeBuilder_ = DeltaDiscoveryRequest.alwaysUseFieldBuilders ? getUdpaResourcesUnsubscribeFieldBuilder() : null;
                }
            }
            internalGetMutableInitialResourceVersions().mergeFrom(deltaDiscoveryRequest.internalGetInitialResourceVersions());
            if (!deltaDiscoveryRequest.getResponseNonce().isEmpty()) {
                this.responseNonce_ = deltaDiscoveryRequest.responseNonce_;
                onChanged();
            }
            if (deltaDiscoveryRequest.hasErrorDetail()) {
                mergeErrorDetail(deltaDiscoveryRequest.getErrorDetail());
            }
            m32426mergeUnknownFields(deltaDiscoveryRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequest.Builder m32422mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequest.access$1700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequest r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequest) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequest r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequest) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequest.Builder.m32422mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequest$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public Node getNode() {
            SingleFieldBuilderV3<Node, Node.Builder, NodeOrBuilder> singleFieldBuilderV3 = this.nodeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Node node = this.node_;
            return node == null ? Node.getDefaultInstance() : node;
        }

        public Builder setNode(Node node) {
            SingleFieldBuilderV3<Node, Node.Builder, NodeOrBuilder> singleFieldBuilderV3 = this.nodeBuilder_;
            if (singleFieldBuilderV3 == null) {
                node.getClass();
                this.node_ = node;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(node);
            }
            return this;
        }

        public Builder setNode(Node.Builder builder) {
            SingleFieldBuilderV3<Node, Node.Builder, NodeOrBuilder> singleFieldBuilderV3 = this.nodeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.node_ = builder.m23650build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m23650build());
            }
            return this;
        }

        public Builder mergeNode(Node node) {
            SingleFieldBuilderV3<Node, Node.Builder, NodeOrBuilder> singleFieldBuilderV3 = this.nodeBuilder_;
            if (singleFieldBuilderV3 == null) {
                Node node2 = this.node_;
                if (node2 != null) {
                    this.node_ = Node.newBuilder(node2).mergeFrom(node).m23652buildPartial();
                } else {
                    this.node_ = node;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(node);
            }
            return this;
        }

        public Builder clearNode() {
            if (this.nodeBuilder_ == null) {
                this.node_ = null;
                onChanged();
            } else {
                this.node_ = null;
                this.nodeBuilder_ = null;
            }
            return this;
        }

        public Node.Builder getNodeBuilder() {
            onChanged();
            return getNodeFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public NodeOrBuilder getNodeOrBuilder() {
            SingleFieldBuilderV3<Node, Node.Builder, NodeOrBuilder> singleFieldBuilderV3 = this.nodeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (NodeOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Node node = this.node_;
            return node == null ? Node.getDefaultInstance() : node;
        }

        private SingleFieldBuilderV3<Node, Node.Builder, NodeOrBuilder> getNodeFieldBuilder() {
            if (this.nodeBuilder_ == null) {
                this.nodeBuilder_ = new SingleFieldBuilderV3<>(getNode(), getParentForChildren(), isClean());
                this.node_ = null;
            }
            return this.nodeBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public String getTypeUrl() {
            Object obj = this.typeUrl_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.typeUrl_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setTypeUrl(String str) {
            str.getClass();
            this.typeUrl_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public ByteString getTypeUrlBytes() {
            Object obj = this.typeUrl_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.typeUrl_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setTypeUrlBytes(ByteString byteString) {
            byteString.getClass();
            DeltaDiscoveryRequest.checkByteStringIsUtf8(byteString);
            this.typeUrl_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearTypeUrl() {
            this.typeUrl_ = DeltaDiscoveryRequest.getDefaultInstance().getTypeUrl();
            onChanged();
            return this;
        }

        private void ensureResourceNamesSubscribeIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.resourceNamesSubscribe_ = new LazyStringArrayList(this.resourceNamesSubscribe_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        /* renamed from: getResourceNamesSubscribeList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo32388getResourceNamesSubscribeList() {
            return this.resourceNamesSubscribe_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public int getResourceNamesSubscribeCount() {
            return this.resourceNamesSubscribe_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public String getResourceNamesSubscribe(int i) {
            return (String) this.resourceNamesSubscribe_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public ByteString getResourceNamesSubscribeBytes(int i) {
            return this.resourceNamesSubscribe_.getByteString(i);
        }

        public Builder setResourceNamesSubscribe(int i, String str) {
            str.getClass();
            ensureResourceNamesSubscribeIsMutable();
            this.resourceNamesSubscribe_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addResourceNamesSubscribe(String str) {
            str.getClass();
            ensureResourceNamesSubscribeIsMutable();
            this.resourceNamesSubscribe_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllResourceNamesSubscribe(Iterable<String> iterable) {
            ensureResourceNamesSubscribeIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.resourceNamesSubscribe_);
            onChanged();
            return this;
        }

        public Builder clearResourceNamesSubscribe() {
            this.resourceNamesSubscribe_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addResourceNamesSubscribeBytes(ByteString byteString) {
            byteString.getClass();
            DeltaDiscoveryRequest.checkByteStringIsUtf8(byteString);
            ensureResourceNamesSubscribeIsMutable();
            this.resourceNamesSubscribe_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureUdpaResourcesSubscribeIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.udpaResourcesSubscribe_ = new ArrayList(this.udpaResourcesSubscribe_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public List<ResourceLocator> getUdpaResourcesSubscribeList() {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.udpaResourcesSubscribe_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public int getUdpaResourcesSubscribeCount() {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.udpaResourcesSubscribe_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public ResourceLocator getUdpaResourcesSubscribe(int i) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.udpaResourcesSubscribe_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setUdpaResourcesSubscribe(int i, ResourceLocator resourceLocator) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                resourceLocator.getClass();
                ensureUdpaResourcesSubscribeIsMutable();
                this.udpaResourcesSubscribe_.set(i, resourceLocator);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, resourceLocator);
            }
            return this;
        }

        public Builder setUdpaResourcesSubscribe(int i, ResourceLocator.Builder builder) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureUdpaResourcesSubscribeIsMutable();
                this.udpaResourcesSubscribe_.set(i, builder.m10226build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m10226build());
            }
            return this;
        }

        public Builder addUdpaResourcesSubscribe(ResourceLocator resourceLocator) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                resourceLocator.getClass();
                ensureUdpaResourcesSubscribeIsMutable();
                this.udpaResourcesSubscribe_.add(resourceLocator);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(resourceLocator);
            }
            return this;
        }

        public Builder addUdpaResourcesSubscribe(int i, ResourceLocator resourceLocator) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                resourceLocator.getClass();
                ensureUdpaResourcesSubscribeIsMutable();
                this.udpaResourcesSubscribe_.add(i, resourceLocator);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, resourceLocator);
            }
            return this;
        }

        public Builder addUdpaResourcesSubscribe(ResourceLocator.Builder builder) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureUdpaResourcesSubscribeIsMutable();
                this.udpaResourcesSubscribe_.add(builder.m10226build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m10226build());
            }
            return this;
        }

        public Builder addUdpaResourcesSubscribe(int i, ResourceLocator.Builder builder) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureUdpaResourcesSubscribeIsMutable();
                this.udpaResourcesSubscribe_.add(i, builder.m10226build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m10226build());
            }
            return this;
        }

        public Builder addAllUdpaResourcesSubscribe(Iterable<? extends ResourceLocator> iterable) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureUdpaResourcesSubscribeIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.udpaResourcesSubscribe_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearUdpaResourcesSubscribe() {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.udpaResourcesSubscribe_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeUdpaResourcesSubscribe(int i) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureUdpaResourcesSubscribeIsMutable();
                this.udpaResourcesSubscribe_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public ResourceLocator.Builder getUdpaResourcesSubscribeBuilder(int i) {
            return getUdpaResourcesSubscribeFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public ResourceLocatorOrBuilder getUdpaResourcesSubscribeOrBuilder(int i) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.udpaResourcesSubscribe_.get(i);
            }
            return (ResourceLocatorOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public List<? extends ResourceLocatorOrBuilder> getUdpaResourcesSubscribeOrBuilderList() {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesSubscribeBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.udpaResourcesSubscribe_);
        }

        public ResourceLocator.Builder addUdpaResourcesSubscribeBuilder() {
            return getUdpaResourcesSubscribeFieldBuilder().addBuilder(ResourceLocator.getDefaultInstance());
        }

        public ResourceLocator.Builder addUdpaResourcesSubscribeBuilder(int i) {
            return getUdpaResourcesSubscribeFieldBuilder().addBuilder(i, ResourceLocator.getDefaultInstance());
        }

        public List<ResourceLocator.Builder> getUdpaResourcesSubscribeBuilderList() {
            return getUdpaResourcesSubscribeFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> getUdpaResourcesSubscribeFieldBuilder() {
            if (this.udpaResourcesSubscribeBuilder_ == null) {
                this.udpaResourcesSubscribeBuilder_ = new RepeatedFieldBuilderV3<>(this.udpaResourcesSubscribe_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.udpaResourcesSubscribe_ = null;
            }
            return this.udpaResourcesSubscribeBuilder_;
        }

        private void ensureResourceNamesUnsubscribeIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.resourceNamesUnsubscribe_ = new LazyStringArrayList(this.resourceNamesUnsubscribe_);
                this.bitField0_ |= 4;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        /* renamed from: getResourceNamesUnsubscribeList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo32389getResourceNamesUnsubscribeList() {
            return this.resourceNamesUnsubscribe_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public int getResourceNamesUnsubscribeCount() {
            return this.resourceNamesUnsubscribe_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public String getResourceNamesUnsubscribe(int i) {
            return (String) this.resourceNamesUnsubscribe_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public ByteString getResourceNamesUnsubscribeBytes(int i) {
            return this.resourceNamesUnsubscribe_.getByteString(i);
        }

        public Builder setResourceNamesUnsubscribe(int i, String str) {
            str.getClass();
            ensureResourceNamesUnsubscribeIsMutable();
            this.resourceNamesUnsubscribe_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addResourceNamesUnsubscribe(String str) {
            str.getClass();
            ensureResourceNamesUnsubscribeIsMutable();
            this.resourceNamesUnsubscribe_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllResourceNamesUnsubscribe(Iterable<String> iterable) {
            ensureResourceNamesUnsubscribeIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.resourceNamesUnsubscribe_);
            onChanged();
            return this;
        }

        public Builder clearResourceNamesUnsubscribe() {
            this.resourceNamesUnsubscribe_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            onChanged();
            return this;
        }

        public Builder addResourceNamesUnsubscribeBytes(ByteString byteString) {
            byteString.getClass();
            DeltaDiscoveryRequest.checkByteStringIsUtf8(byteString);
            ensureResourceNamesUnsubscribeIsMutable();
            this.resourceNamesUnsubscribe_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureUdpaResourcesUnsubscribeIsMutable() {
            if ((this.bitField0_ & 8) == 0) {
                this.udpaResourcesUnsubscribe_ = new ArrayList(this.udpaResourcesUnsubscribe_);
                this.bitField0_ |= 8;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public List<ResourceLocator> getUdpaResourcesUnsubscribeList() {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.udpaResourcesUnsubscribe_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public int getUdpaResourcesUnsubscribeCount() {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.udpaResourcesUnsubscribe_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public ResourceLocator getUdpaResourcesUnsubscribe(int i) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.udpaResourcesUnsubscribe_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setUdpaResourcesUnsubscribe(int i, ResourceLocator resourceLocator) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                resourceLocator.getClass();
                ensureUdpaResourcesUnsubscribeIsMutable();
                this.udpaResourcesUnsubscribe_.set(i, resourceLocator);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, resourceLocator);
            }
            return this;
        }

        public Builder setUdpaResourcesUnsubscribe(int i, ResourceLocator.Builder builder) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureUdpaResourcesUnsubscribeIsMutable();
                this.udpaResourcesUnsubscribe_.set(i, builder.m10226build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m10226build());
            }
            return this;
        }

        public Builder addUdpaResourcesUnsubscribe(ResourceLocator resourceLocator) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                resourceLocator.getClass();
                ensureUdpaResourcesUnsubscribeIsMutable();
                this.udpaResourcesUnsubscribe_.add(resourceLocator);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(resourceLocator);
            }
            return this;
        }

        public Builder addUdpaResourcesUnsubscribe(int i, ResourceLocator resourceLocator) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                resourceLocator.getClass();
                ensureUdpaResourcesUnsubscribeIsMutable();
                this.udpaResourcesUnsubscribe_.add(i, resourceLocator);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, resourceLocator);
            }
            return this;
        }

        public Builder addUdpaResourcesUnsubscribe(ResourceLocator.Builder builder) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureUdpaResourcesUnsubscribeIsMutable();
                this.udpaResourcesUnsubscribe_.add(builder.m10226build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m10226build());
            }
            return this;
        }

        public Builder addUdpaResourcesUnsubscribe(int i, ResourceLocator.Builder builder) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureUdpaResourcesUnsubscribeIsMutable();
                this.udpaResourcesUnsubscribe_.add(i, builder.m10226build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m10226build());
            }
            return this;
        }

        public Builder addAllUdpaResourcesUnsubscribe(Iterable<? extends ResourceLocator> iterable) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureUdpaResourcesUnsubscribeIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.udpaResourcesUnsubscribe_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearUdpaResourcesUnsubscribe() {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.udpaResourcesUnsubscribe_ = Collections.emptyList();
                this.bitField0_ &= -9;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeUdpaResourcesUnsubscribe(int i) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureUdpaResourcesUnsubscribeIsMutable();
                this.udpaResourcesUnsubscribe_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public ResourceLocator.Builder getUdpaResourcesUnsubscribeBuilder(int i) {
            return getUdpaResourcesUnsubscribeFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public ResourceLocatorOrBuilder getUdpaResourcesUnsubscribeOrBuilder(int i) {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.udpaResourcesUnsubscribe_.get(i);
            }
            return (ResourceLocatorOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public List<? extends ResourceLocatorOrBuilder> getUdpaResourcesUnsubscribeOrBuilderList() {
            RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> repeatedFieldBuilderV3 = this.udpaResourcesUnsubscribeBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.udpaResourcesUnsubscribe_);
        }

        public ResourceLocator.Builder addUdpaResourcesUnsubscribeBuilder() {
            return getUdpaResourcesUnsubscribeFieldBuilder().addBuilder(ResourceLocator.getDefaultInstance());
        }

        public ResourceLocator.Builder addUdpaResourcesUnsubscribeBuilder(int i) {
            return getUdpaResourcesUnsubscribeFieldBuilder().addBuilder(i, ResourceLocator.getDefaultInstance());
        }

        public List<ResourceLocator.Builder> getUdpaResourcesUnsubscribeBuilderList() {
            return getUdpaResourcesUnsubscribeFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<ResourceLocator, ResourceLocator.Builder, ResourceLocatorOrBuilder> getUdpaResourcesUnsubscribeFieldBuilder() {
            if (this.udpaResourcesUnsubscribeBuilder_ == null) {
                this.udpaResourcesUnsubscribeBuilder_ = new RepeatedFieldBuilderV3<>(this.udpaResourcesUnsubscribe_, (this.bitField0_ & 8) != 0, getParentForChildren(), isClean());
                this.udpaResourcesUnsubscribe_ = null;
            }
            return this.udpaResourcesUnsubscribeBuilder_;
        }

        private MapField<String, String> internalGetInitialResourceVersions() {
            MapField<String, String> mapField = this.initialResourceVersions_;
            return mapField == null ? MapField.emptyMapField(InitialResourceVersionsDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, String> internalGetMutableInitialResourceVersions() {
            onChanged();
            if (this.initialResourceVersions_ == null) {
                this.initialResourceVersions_ = MapField.newMapField(InitialResourceVersionsDefaultEntryHolder.defaultEntry);
            }
            if (!this.initialResourceVersions_.isMutable()) {
                this.initialResourceVersions_ = this.initialResourceVersions_.copy();
            }
            return this.initialResourceVersions_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public int getInitialResourceVersionsCount() {
            return internalGetInitialResourceVersions().getMap().size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public boolean containsInitialResourceVersions(String str) {
            str.getClass();
            return internalGetInitialResourceVersions().getMap().containsKey(str);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        @Deprecated
        public Map<String, String> getInitialResourceVersions() {
            return getInitialResourceVersionsMap();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public Map<String, String> getInitialResourceVersionsMap() {
            return internalGetInitialResourceVersions().getMap();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public String getInitialResourceVersionsOrDefault(String str, String str2) {
            str.getClass();
            Map map = internalGetInitialResourceVersions().getMap();
            return map.containsKey(str) ? (String) map.get(str) : str2;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public String getInitialResourceVersionsOrThrow(String str) {
            str.getClass();
            Map map = internalGetInitialResourceVersions().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return (String) map.get(str);
        }

        public Builder clearInitialResourceVersions() {
            internalGetMutableInitialResourceVersions().getMutableMap().clear();
            return this;
        }

        public Builder removeInitialResourceVersions(String str) {
            str.getClass();
            internalGetMutableInitialResourceVersions().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, String> getMutableInitialResourceVersions() {
            return internalGetMutableInitialResourceVersions().getMutableMap();
        }

        public Builder putInitialResourceVersions(String str, String str2) {
            str.getClass();
            str2.getClass();
            internalGetMutableInitialResourceVersions().getMutableMap().put(str, str2);
            return this;
        }

        public Builder putAllInitialResourceVersions(Map<String, String> map) {
            internalGetMutableInitialResourceVersions().getMutableMap().putAll(map);
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public String getResponseNonce() {
            Object obj = this.responseNonce_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.responseNonce_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setResponseNonce(String str) {
            str.getClass();
            this.responseNonce_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public ByteString getResponseNonceBytes() {
            Object obj = this.responseNonce_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.responseNonce_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setResponseNonceBytes(ByteString byteString) {
            byteString.getClass();
            DeltaDiscoveryRequest.checkByteStringIsUtf8(byteString);
            this.responseNonce_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearResponseNonce() {
            this.responseNonce_ = DeltaDiscoveryRequest.getDefaultInstance().getResponseNonce();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public Status getErrorDetail() {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorDetailBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Status status = this.errorDetail_;
            return status == null ? Status.getDefaultInstance() : status;
        }

        public Builder setErrorDetail(Status status) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorDetailBuilder_;
            if (singleFieldBuilderV3 == null) {
                status.getClass();
                this.errorDetail_ = status;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(status);
            }
            return this;
        }

        public Builder setErrorDetail(Status.Builder builder) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorDetailBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.errorDetail_ = builder.m4002build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m4002build());
            }
            return this;
        }

        public Builder mergeErrorDetail(Status status) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorDetailBuilder_;
            if (singleFieldBuilderV3 == null) {
                Status status2 = this.errorDetail_;
                if (status2 != null) {
                    this.errorDetail_ = Status.newBuilder(status2).mergeFrom(status).m4004buildPartial();
                } else {
                    this.errorDetail_ = status;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(status);
            }
            return this;
        }

        public Builder clearErrorDetail() {
            if (this.errorDetailBuilder_ == null) {
                this.errorDetail_ = null;
                onChanged();
            } else {
                this.errorDetail_ = null;
                this.errorDetailBuilder_ = null;
            }
            return this;
        }

        public Status.Builder getErrorDetailBuilder() {
            onChanged();
            return getErrorDetailFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.DeltaDiscoveryRequestOrBuilder
        public StatusOrBuilder getErrorDetailOrBuilder() {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorDetailBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (StatusOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Status status = this.errorDetail_;
            return status == null ? Status.getDefaultInstance() : status;
        }

        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> getErrorDetailFieldBuilder() {
            if (this.errorDetailBuilder_ == null) {
                this.errorDetailBuilder_ = new SingleFieldBuilderV3<>(getErrorDetail(), getParentForChildren(), isClean());
                this.errorDetail_ = null;
            }
            return this.errorDetailBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m32432setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m32426mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
