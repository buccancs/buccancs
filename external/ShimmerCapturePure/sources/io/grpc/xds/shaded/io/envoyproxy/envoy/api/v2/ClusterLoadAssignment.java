package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.Endpoint;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.LocalityLbEndpoints;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.LocalityLbEndpointsOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercentOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public final class ClusterLoadAssignment extends GeneratedMessageV3 implements ClusterLoadAssignmentOrBuilder {
    public static final int CLUSTER_NAME_FIELD_NUMBER = 1;
    public static final int ENDPOINTS_FIELD_NUMBER = 2;
    public static final int NAMED_ENDPOINTS_FIELD_NUMBER = 5;
    public static final int POLICY_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private static final ClusterLoadAssignment DEFAULT_INSTANCE = new ClusterLoadAssignment();
    private static final Parser<ClusterLoadAssignment> PARSER = new AbstractParser<ClusterLoadAssignment>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ClusterLoadAssignment m12310parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ClusterLoadAssignment(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object clusterName_;
    private List<LocalityLbEndpoints> endpoints_;
    private byte memoizedIsInitialized;
    private MapField<String, Endpoint> namedEndpoints_;
    private Policy policy_;

    private ClusterLoadAssignment(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ClusterLoadAssignment() {
        this.memoizedIsInitialized = (byte) -1;
        this.clusterName_ = "";
        this.endpoints_ = Collections.emptyList();
    }

    private ClusterLoadAssignment(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.clusterName_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                if ((i & 1) == 0) {
                                    this.endpoints_ = new ArrayList();
                                    i |= 1;
                                }
                                this.endpoints_.add(codedInputStream.readMessage(LocalityLbEndpoints.parser(), extensionRegistryLite));
                            } else if (tag == 34) {
                                Policy policy = this.policy_;
                                Policy.Builder builderM12354toBuilder = policy != null ? policy.m12354toBuilder() : null;
                                Policy policy2 = (Policy) codedInputStream.readMessage(Policy.parser(), extensionRegistryLite);
                                this.policy_ = policy2;
                                if (builderM12354toBuilder != null) {
                                    builderM12354toBuilder.mergeFrom(policy2);
                                    this.policy_ = builderM12354toBuilder.m12361buildPartial();
                                }
                            } else if (tag == 42) {
                                if ((i & 2) == 0) {
                                    this.namedEndpoints_ = MapField.newMapField(NamedEndpointsDefaultEntryHolder.defaultEntry);
                                    i |= 2;
                                }
                                MapEntry message = codedInputStream.readMessage(NamedEndpointsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.namedEndpoints_.getMutableMap().put(message.getKey(), message.getValue());
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
                if ((i & 1) != 0) {
                    this.endpoints_ = Collections.unmodifiableList(this.endpoints_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ClusterLoadAssignment getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ClusterLoadAssignment> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_descriptor;
    }

    public static ClusterLoadAssignment parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ClusterLoadAssignment) PARSER.parseFrom(byteBuffer);
    }

    public static ClusterLoadAssignment parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ClusterLoadAssignment) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ClusterLoadAssignment parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ClusterLoadAssignment) PARSER.parseFrom(byteString);
    }

    public static ClusterLoadAssignment parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ClusterLoadAssignment) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ClusterLoadAssignment parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ClusterLoadAssignment) PARSER.parseFrom(bArr);
    }

    public static ClusterLoadAssignment parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ClusterLoadAssignment) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ClusterLoadAssignment parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ClusterLoadAssignment parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ClusterLoadAssignment parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ClusterLoadAssignment parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ClusterLoadAssignment parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ClusterLoadAssignment parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m12308toBuilder();
    }

    public static Builder newBuilder(ClusterLoadAssignment clusterLoadAssignment) {
        return DEFAULT_INSTANCE.m12308toBuilder().mergeFrom(clusterLoadAssignment);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ClusterLoadAssignment m12303getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public List<LocalityLbEndpoints> getEndpointsList() {
        return this.endpoints_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public List<? extends LocalityLbEndpointsOrBuilder> getEndpointsOrBuilderList() {
        return this.endpoints_;
    }

    public Parser<ClusterLoadAssignment> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public boolean hasPolicy() {
        return this.policy_ != null;
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
        return new ClusterLoadAssignment();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected MapField internalGetMapField(int i) {
        if (i == 5) {
            return internalGetNamedEndpoints();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_fieldAccessorTable.ensureFieldAccessorsInitialized(ClusterLoadAssignment.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public String getClusterName() {
        Object obj = this.clusterName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.clusterName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public ByteString getClusterNameBytes() {
        Object obj = this.clusterName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.clusterName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public int getEndpointsCount() {
        return this.endpoints_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public LocalityLbEndpoints getEndpoints(int i) {
        return this.endpoints_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public LocalityLbEndpointsOrBuilder getEndpointsOrBuilder(int i) {
        return this.endpoints_.get(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, Endpoint> internalGetNamedEndpoints() {
        MapField<String, Endpoint> mapField = this.namedEndpoints_;
        return mapField == null ? MapField.emptyMapField(NamedEndpointsDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public int getNamedEndpointsCount() {
        return internalGetNamedEndpoints().getMap().size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public boolean containsNamedEndpoints(String str) {
        str.getClass();
        return internalGetNamedEndpoints().getMap().containsKey(str);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    @Deprecated
    public Map<String, Endpoint> getNamedEndpoints() {
        return getNamedEndpointsMap();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public Map<String, Endpoint> getNamedEndpointsMap() {
        return internalGetNamedEndpoints().getMap();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public Endpoint getNamedEndpointsOrDefault(String str, Endpoint endpoint) {
        str.getClass();
        Map map = internalGetNamedEndpoints().getMap();
        return map.containsKey(str) ? (Endpoint) map.get(str) : endpoint;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public Endpoint getNamedEndpointsOrThrow(String str) {
        str.getClass();
        Map map = internalGetNamedEndpoints().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return (Endpoint) map.get(str);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public Policy getPolicy() {
        Policy policy = this.policy_;
        return policy == null ? Policy.getDefaultInstance() : policy;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
    public PolicyOrBuilder getPolicyOrBuilder() {
        return getPolicy();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getClusterNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.clusterName_);
        }
        for (int i = 0; i < this.endpoints_.size(); i++) {
            codedOutputStream.writeMessage(2, this.endpoints_.get(i));
        }
        if (this.policy_ != null) {
            codedOutputStream.writeMessage(4, getPolicy());
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetNamedEndpoints(), NamedEndpointsDefaultEntryHolder.defaultEntry, 5);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getClusterNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.clusterName_) : 0;
        for (int i2 = 0; i2 < this.endpoints_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, this.endpoints_.get(i2));
        }
        if (this.policy_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(4, getPolicy());
        }
        for (Map.Entry entry : internalGetNamedEndpoints().getMap().entrySet()) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(5, NamedEndpointsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClusterLoadAssignment)) {
            return super.equals(obj);
        }
        ClusterLoadAssignment clusterLoadAssignment = (ClusterLoadAssignment) obj;
        if (getClusterName().equals(clusterLoadAssignment.getClusterName()) && getEndpointsList().equals(clusterLoadAssignment.getEndpointsList()) && internalGetNamedEndpoints().equals(clusterLoadAssignment.internalGetNamedEndpoints()) && hasPolicy() == clusterLoadAssignment.hasPolicy()) {
            return (!hasPolicy() || getPolicy().equals(clusterLoadAssignment.getPolicy())) && this.unknownFields.equals(clusterLoadAssignment.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getClusterName().hashCode();
        if (getEndpointsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getEndpointsList().hashCode();
        }
        if (!internalGetNamedEndpoints().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 5) * 53) + internalGetNamedEndpoints().hashCode();
        }
        if (hasPolicy()) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getPolicy().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m12305newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m12308toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public interface PolicyOrBuilder extends MessageOrBuilder {
        @Deprecated
        boolean getDisableOverprovisioning();

        Policy.DropOverload getDropOverloads(int i);

        int getDropOverloadsCount();

        List<Policy.DropOverload> getDropOverloadsList();

        Policy.DropOverloadOrBuilder getDropOverloadsOrBuilder(int i);

        List<? extends Policy.DropOverloadOrBuilder> getDropOverloadsOrBuilderList();

        Duration getEndpointStaleAfter();

        DurationOrBuilder getEndpointStaleAfterOrBuilder();

        UInt32Value getOverprovisioningFactor();

        UInt32ValueOrBuilder getOverprovisioningFactorOrBuilder();

        boolean hasEndpointStaleAfter();

        boolean hasOverprovisioningFactor();
    }

    public static final class Policy extends GeneratedMessageV3 implements PolicyOrBuilder {
        public static final int DISABLE_OVERPROVISIONING_FIELD_NUMBER = 5;
        public static final int DROP_OVERLOADS_FIELD_NUMBER = 2;
        public static final int ENDPOINT_STALE_AFTER_FIELD_NUMBER = 4;
        public static final int OVERPROVISIONING_FACTOR_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private static final Policy DEFAULT_INSTANCE = new Policy();
        private static final Parser<Policy> PARSER = new AbstractParser<Policy>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Policy m12356parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Policy(codedInputStream, extensionRegistryLite);
            }
        };
        private boolean disableOverprovisioning_;
        private List<DropOverload> dropOverloads_;
        private Duration endpointStaleAfter_;
        private byte memoizedIsInitialized;
        private UInt32Value overprovisioningFactor_;

        private Policy(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Policy() {
            this.memoizedIsInitialized = (byte) -1;
            this.dropOverloads_ = Collections.emptyList();
        }

        private Policy(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            UInt32Value.Builder builder;
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
                                if (tag != 18) {
                                    if (tag == 26) {
                                        UInt32Value uInt32Value = this.overprovisioningFactor_;
                                        builder = uInt32Value != null ? uInt32Value.toBuilder() : null;
                                        UInt32Value message = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                                        this.overprovisioningFactor_ = message;
                                        if (builder != null) {
                                            builder.mergeFrom(message);
                                            this.overprovisioningFactor_ = builder.buildPartial();
                                        }
                                    } else if (tag == 34) {
                                        Duration duration = this.endpointStaleAfter_;
                                        builder = duration != null ? duration.toBuilder() : null;
                                        Duration message2 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                                        this.endpointStaleAfter_ = message2;
                                        if (builder != null) {
                                            builder.mergeFrom(message2);
                                            this.endpointStaleAfter_ = builder.buildPartial();
                                        }
                                    } else if (tag == 40) {
                                        this.disableOverprovisioning_ = codedInputStream.readBool();
                                    } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    }
                                } else {
                                    if (!(z2 & true)) {
                                        this.dropOverloads_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.dropOverloads_.add(codedInputStream.readMessage(DropOverload.parser(), extensionRegistryLite));
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
                        this.dropOverloads_ = Collections.unmodifiableList(this.dropOverloads_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static Policy getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Policy> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_descriptor;
        }

        public static Policy parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Policy) PARSER.parseFrom(byteBuffer);
        }

        public static Policy parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Policy) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Policy parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Policy) PARSER.parseFrom(byteString);
        }

        public static Policy parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Policy) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Policy parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Policy) PARSER.parseFrom(bArr);
        }

        public static Policy parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Policy) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Policy parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Policy parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Policy parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Policy parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Policy parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Policy parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m12354toBuilder();
        }

        public static Builder newBuilder(Policy policy) {
            return DEFAULT_INSTANCE.m12354toBuilder().mergeFrom(policy);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Policy m12349getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
        @Deprecated
        public boolean getDisableOverprovisioning() {
            return this.disableOverprovisioning_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
        public List<DropOverload> getDropOverloadsList() {
            return this.dropOverloads_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
        public List<? extends DropOverloadOrBuilder> getDropOverloadsOrBuilderList() {
            return this.dropOverloads_;
        }

        public Parser<Policy> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
        public boolean hasEndpointStaleAfter() {
            return this.endpointStaleAfter_ != null;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
        public boolean hasOverprovisioningFactor() {
            return this.overprovisioningFactor_ != null;
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
            return new Policy();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_fieldAccessorTable.ensureFieldAccessorsInitialized(Policy.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
        public int getDropOverloadsCount() {
            return this.dropOverloads_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
        public DropOverload getDropOverloads(int i) {
            return this.dropOverloads_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
        public DropOverloadOrBuilder getDropOverloadsOrBuilder(int i) {
            return this.dropOverloads_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
        public UInt32Value getOverprovisioningFactor() {
            UInt32Value uInt32Value = this.overprovisioningFactor_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
        public UInt32ValueOrBuilder getOverprovisioningFactorOrBuilder() {
            return getOverprovisioningFactor();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
        public Duration getEndpointStaleAfter() {
            Duration duration = this.endpointStaleAfter_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
        public DurationOrBuilder getEndpointStaleAfterOrBuilder() {
            return getEndpointStaleAfter();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.dropOverloads_.size(); i++) {
                codedOutputStream.writeMessage(2, this.dropOverloads_.get(i));
            }
            if (this.overprovisioningFactor_ != null) {
                codedOutputStream.writeMessage(3, getOverprovisioningFactor());
            }
            if (this.endpointStaleAfter_ != null) {
                codedOutputStream.writeMessage(4, getEndpointStaleAfter());
            }
            boolean z = this.disableOverprovisioning_;
            if (z) {
                codedOutputStream.writeBool(5, z);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeBoolSize = 0;
            for (int i2 = 0; i2 < this.dropOverloads_.size(); i2++) {
                iComputeBoolSize += CodedOutputStream.computeMessageSize(2, this.dropOverloads_.get(i2));
            }
            if (this.overprovisioningFactor_ != null) {
                iComputeBoolSize += CodedOutputStream.computeMessageSize(3, getOverprovisioningFactor());
            }
            if (this.endpointStaleAfter_ != null) {
                iComputeBoolSize += CodedOutputStream.computeMessageSize(4, getEndpointStaleAfter());
            }
            boolean z = this.disableOverprovisioning_;
            if (z) {
                iComputeBoolSize += CodedOutputStream.computeBoolSize(5, z);
            }
            int serializedSize = iComputeBoolSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Policy)) {
                return super.equals(obj);
            }
            Policy policy = (Policy) obj;
            if (!getDropOverloadsList().equals(policy.getDropOverloadsList()) || hasOverprovisioningFactor() != policy.hasOverprovisioningFactor()) {
                return false;
            }
            if ((!hasOverprovisioningFactor() || getOverprovisioningFactor().equals(policy.getOverprovisioningFactor())) && hasEndpointStaleAfter() == policy.hasEndpointStaleAfter()) {
                return (!hasEndpointStaleAfter() || getEndpointStaleAfter().equals(policy.getEndpointStaleAfter())) && getDisableOverprovisioning() == policy.getDisableOverprovisioning() && this.unknownFields.equals(policy.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (getDropOverloadsCount() > 0) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getDropOverloadsList().hashCode();
            }
            if (hasOverprovisioningFactor()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getOverprovisioningFactor().hashCode();
            }
            if (hasEndpointStaleAfter()) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + getEndpointStaleAfter().hashCode();
            }
            int iHashBoolean = (((((iHashCode * 37) + 5) * 53) + Internal.hashBoolean(getDisableOverprovisioning())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashBoolean;
            return iHashBoolean;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12351newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12354toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public interface DropOverloadOrBuilder extends MessageOrBuilder {
            String getCategory();

            ByteString getCategoryBytes();

            FractionalPercent getDropPercentage();

            FractionalPercentOrBuilder getDropPercentageOrBuilder();

            boolean hasDropPercentage();
        }

        public static final class DropOverload extends GeneratedMessageV3 implements DropOverloadOrBuilder {
            public static final int CATEGORY_FIELD_NUMBER = 1;
            public static final int DROP_PERCENTAGE_FIELD_NUMBER = 2;
            private static final long serialVersionUID = 0;
            private static final DropOverload DEFAULT_INSTANCE = new DropOverload();
            private static final Parser<DropOverload> PARSER = new AbstractParser<DropOverload>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverload.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public DropOverload m12402parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new DropOverload(codedInputStream, extensionRegistryLite);
                }
            };
            private volatile Object category_;
            private FractionalPercent dropPercentage_;
            private byte memoizedIsInitialized;

            private DropOverload(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private DropOverload() {
                this.memoizedIsInitialized = (byte) -1;
                this.category_ = "";
            }

            private DropOverload(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.category_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 18) {
                                    FractionalPercent fractionalPercent = this.dropPercentage_;
                                    FractionalPercent.Builder builderM32859toBuilder = fractionalPercent != null ? fractionalPercent.m32859toBuilder() : null;
                                    FractionalPercent fractionalPercent2 = (FractionalPercent) codedInputStream.readMessage(FractionalPercent.parser(), extensionRegistryLite);
                                    this.dropPercentage_ = fractionalPercent2;
                                    if (builderM32859toBuilder != null) {
                                        builderM32859toBuilder.mergeFrom(fractionalPercent2);
                                        this.dropPercentage_ = builderM32859toBuilder.m32866buildPartial();
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

            public static DropOverload getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<DropOverload> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_DropOverload_descriptor;
            }

            public static DropOverload parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (DropOverload) PARSER.parseFrom(byteBuffer);
            }

            public static DropOverload parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (DropOverload) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static DropOverload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (DropOverload) PARSER.parseFrom(byteString);
            }

            public static DropOverload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (DropOverload) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static DropOverload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (DropOverload) PARSER.parseFrom(bArr);
            }

            public static DropOverload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (DropOverload) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static DropOverload parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static DropOverload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static DropOverload parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static DropOverload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static DropOverload parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static DropOverload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m12400toBuilder();
            }

            public static Builder newBuilder(DropOverload dropOverload) {
                return DEFAULT_INSTANCE.m12400toBuilder().mergeFrom(dropOverload);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public DropOverload m12395getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public Parser<DropOverload> getParserForType() {
                return PARSER;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverloadOrBuilder
            public boolean hasDropPercentage() {
                return this.dropPercentage_ != null;
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
                return new DropOverload();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_DropOverload_fieldAccessorTable.ensureFieldAccessorsInitialized(DropOverload.class, Builder.class);
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverloadOrBuilder
            public String getCategory() {
                Object obj = this.category_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.category_ = stringUtf8;
                return stringUtf8;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverloadOrBuilder
            public ByteString getCategoryBytes() {
                Object obj = this.category_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.category_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverloadOrBuilder
            public FractionalPercent getDropPercentage() {
                FractionalPercent fractionalPercent = this.dropPercentage_;
                return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverloadOrBuilder
            public FractionalPercentOrBuilder getDropPercentageOrBuilder() {
                return getDropPercentage();
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (!getCategoryBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.category_);
                }
                if (this.dropPercentage_ != null) {
                    codedOutputStream.writeMessage(2, getDropPercentage());
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeStringSize = !getCategoryBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.category_) : 0;
                if (this.dropPercentage_ != null) {
                    iComputeStringSize += CodedOutputStream.computeMessageSize(2, getDropPercentage());
                }
                int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof DropOverload)) {
                    return super.equals(obj);
                }
                DropOverload dropOverload = (DropOverload) obj;
                if (getCategory().equals(dropOverload.getCategory()) && hasDropPercentage() == dropOverload.hasDropPercentage()) {
                    return (!hasDropPercentage() || getDropPercentage().equals(dropOverload.getDropPercentage())) && this.unknownFields.equals(dropOverload.unknownFields);
                }
                return false;
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCategory().hashCode();
                if (hasDropPercentage()) {
                    iHashCode = (((iHashCode * 37) + 2) * 53) + getDropPercentage().hashCode();
                }
                int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m12397newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m12400toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DropOverloadOrBuilder {
                private Object category_;
                private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> dropPercentageBuilder_;
                private FractionalPercent dropPercentage_;

                private Builder() {
                    this.category_ = "";
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.category_ = "";
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_DropOverload_descriptor;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverloadOrBuilder
                public boolean hasDropPercentage() {
                    return (this.dropPercentageBuilder_ == null && this.dropPercentage_ == null) ? false : true;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_DropOverload_fieldAccessorTable.ensureFieldAccessorsInitialized(DropOverload.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = DropOverload.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m12411clear() {
                    super.clear();
                    this.category_ = "";
                    if (this.dropPercentageBuilder_ == null) {
                        this.dropPercentage_ = null;
                    } else {
                        this.dropPercentage_ = null;
                        this.dropPercentageBuilder_ = null;
                    }
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_DropOverload_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public DropOverload m12424getDefaultInstanceForType() {
                    return DropOverload.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public DropOverload m12405build() throws UninitializedMessageException {
                    DropOverload dropOverloadM12407buildPartial = m12407buildPartial();
                    if (dropOverloadM12407buildPartial.isInitialized()) {
                        return dropOverloadM12407buildPartial;
                    }
                    throw newUninitializedMessageException(dropOverloadM12407buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public DropOverload m12407buildPartial() {
                    DropOverload dropOverload = new DropOverload(this);
                    dropOverload.category_ = this.category_;
                    SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.dropPercentageBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        dropOverload.dropPercentage_ = this.dropPercentage_;
                    } else {
                        dropOverload.dropPercentage_ = singleFieldBuilderV3.build();
                    }
                    onBuilt();
                    return dropOverload;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m12423clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m12435setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m12413clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m12416clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m12437setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m12403addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m12428mergeFrom(Message message) {
                    if (message instanceof DropOverload) {
                        return mergeFrom((DropOverload) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(DropOverload dropOverload) {
                    if (dropOverload == DropOverload.getDefaultInstance()) {
                        return this;
                    }
                    if (!dropOverload.getCategory().isEmpty()) {
                        this.category_ = dropOverload.category_;
                        onChanged();
                    }
                    if (dropOverload.hasDropPercentage()) {
                        mergeDropPercentage(dropOverload.getDropPercentage());
                    }
                    m12433mergeUnknownFields(dropOverload.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverload.Builder m12429mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverload.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment$Policy$DropOverload r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverload) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment$Policy$DropOverload r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverload) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverload.Builder.m12429mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment$Policy$DropOverload$Builder");
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverloadOrBuilder
                public String getCategory() {
                    Object obj = this.category_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.category_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setCategory(String str) {
                    str.getClass();
                    this.category_ = str;
                    onChanged();
                    return this;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverloadOrBuilder
                public ByteString getCategoryBytes() {
                    Object obj = this.category_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.category_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setCategoryBytes(ByteString byteString) {
                    byteString.getClass();
                    DropOverload.checkByteStringIsUtf8(byteString);
                    this.category_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearCategory() {
                    this.category_ = DropOverload.getDefaultInstance().getCategory();
                    onChanged();
                    return this;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverloadOrBuilder
                public FractionalPercent getDropPercentage() {
                    SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.dropPercentageBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    FractionalPercent fractionalPercent = this.dropPercentage_;
                    return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
                }

                public Builder setDropPercentage(FractionalPercent fractionalPercent) {
                    SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.dropPercentageBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        fractionalPercent.getClass();
                        this.dropPercentage_ = fractionalPercent;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(fractionalPercent);
                    }
                    return this;
                }

                public Builder setDropPercentage(FractionalPercent.Builder builder) {
                    SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.dropPercentageBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.dropPercentage_ = builder.m32864build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m32864build());
                    }
                    return this;
                }

                public Builder mergeDropPercentage(FractionalPercent fractionalPercent) {
                    SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.dropPercentageBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        FractionalPercent fractionalPercent2 = this.dropPercentage_;
                        if (fractionalPercent2 != null) {
                            this.dropPercentage_ = FractionalPercent.newBuilder(fractionalPercent2).mergeFrom(fractionalPercent).m32866buildPartial();
                        } else {
                            this.dropPercentage_ = fractionalPercent;
                        }
                        onChanged();
                    } else {
                        singleFieldBuilderV3.mergeFrom(fractionalPercent);
                    }
                    return this;
                }

                public Builder clearDropPercentage() {
                    if (this.dropPercentageBuilder_ == null) {
                        this.dropPercentage_ = null;
                        onChanged();
                    } else {
                        this.dropPercentage_ = null;
                        this.dropPercentageBuilder_ = null;
                    }
                    return this;
                }

                public FractionalPercent.Builder getDropPercentageBuilder() {
                    onChanged();
                    return getDropPercentageFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverloadOrBuilder
                public FractionalPercentOrBuilder getDropPercentageOrBuilder() {
                    SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.dropPercentageBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return (FractionalPercentOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    FractionalPercent fractionalPercent = this.dropPercentage_;
                    return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
                }

                private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> getDropPercentageFieldBuilder() {
                    if (this.dropPercentageBuilder_ == null) {
                        this.dropPercentageBuilder_ = new SingleFieldBuilderV3<>(getDropPercentage(), getParentForChildren(), isClean());
                        this.dropPercentage_ = null;
                    }
                    return this.dropPercentageBuilder_;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m12439setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m12433mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PolicyOrBuilder {
            private int bitField0_;
            private boolean disableOverprovisioning_;
            private RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> dropOverloadsBuilder_;
            private List<DropOverload> dropOverloads_;
            private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> endpointStaleAfterBuilder_;
            private Duration endpointStaleAfter_;
            private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> overprovisioningFactorBuilder_;
            private UInt32Value overprovisioningFactor_;

            private Builder() {
                this.dropOverloads_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.dropOverloads_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_descriptor;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
            @Deprecated
            public boolean getDisableOverprovisioning() {
                return this.disableOverprovisioning_;
            }

            @Deprecated
            public Builder setDisableOverprovisioning(boolean z) {
                this.disableOverprovisioning_ = z;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
            public boolean hasEndpointStaleAfter() {
                return (this.endpointStaleAfterBuilder_ == null && this.endpointStaleAfter_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
            public boolean hasOverprovisioningFactor() {
                return (this.overprovisioningFactorBuilder_ == null && this.overprovisioningFactor_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_fieldAccessorTable.ensureFieldAccessorsInitialized(Policy.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (Policy.alwaysUseFieldBuilders) {
                    getDropOverloadsFieldBuilder();
                }
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m12365clear() {
                super.clear();
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.dropOverloads_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                if (this.overprovisioningFactorBuilder_ == null) {
                    this.overprovisioningFactor_ = null;
                } else {
                    this.overprovisioningFactor_ = null;
                    this.overprovisioningFactorBuilder_ = null;
                }
                if (this.endpointStaleAfterBuilder_ == null) {
                    this.endpointStaleAfter_ = null;
                } else {
                    this.endpointStaleAfter_ = null;
                    this.endpointStaleAfterBuilder_ = null;
                }
                this.disableOverprovisioning_ = false;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Policy m12378getDefaultInstanceForType() {
                return Policy.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Policy m12359build() throws UninitializedMessageException {
                Policy policyM12361buildPartial = m12361buildPartial();
                if (policyM12361buildPartial.isInitialized()) {
                    return policyM12361buildPartial;
                }
                throw newUninitializedMessageException(policyM12361buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Policy m12361buildPartial() {
                Policy policy = new Policy(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.dropOverloads_ = Collections.unmodifiableList(this.dropOverloads_);
                        this.bitField0_ &= -2;
                    }
                    policy.dropOverloads_ = this.dropOverloads_;
                } else {
                    policy.dropOverloads_ = repeatedFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.overprovisioningFactorBuilder_;
                if (singleFieldBuilderV3 == null) {
                    policy.overprovisioningFactor_ = this.overprovisioningFactor_;
                } else {
                    policy.overprovisioningFactor_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV32 = this.endpointStaleAfterBuilder_;
                if (singleFieldBuilderV32 == null) {
                    policy.endpointStaleAfter_ = this.endpointStaleAfter_;
                } else {
                    policy.endpointStaleAfter_ = singleFieldBuilderV32.build();
                }
                policy.disableOverprovisioning_ = this.disableOverprovisioning_;
                onBuilt();
                return policy;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m12377clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m12389setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m12367clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m12370clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m12391setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m12357addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m12382mergeFrom(Message message) {
                if (message instanceof Policy) {
                    return mergeFrom((Policy) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Policy policy) {
                if (policy == Policy.getDefaultInstance()) {
                    return this;
                }
                if (this.dropOverloadsBuilder_ == null) {
                    if (!policy.dropOverloads_.isEmpty()) {
                        if (this.dropOverloads_.isEmpty()) {
                            this.dropOverloads_ = policy.dropOverloads_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureDropOverloadsIsMutable();
                            this.dropOverloads_.addAll(policy.dropOverloads_);
                        }
                        onChanged();
                    }
                } else if (!policy.dropOverloads_.isEmpty()) {
                    if (!this.dropOverloadsBuilder_.isEmpty()) {
                        this.dropOverloadsBuilder_.addAllMessages(policy.dropOverloads_);
                    } else {
                        this.dropOverloadsBuilder_.dispose();
                        this.dropOverloadsBuilder_ = null;
                        this.dropOverloads_ = policy.dropOverloads_;
                        this.bitField0_ &= -2;
                        this.dropOverloadsBuilder_ = Policy.alwaysUseFieldBuilders ? getDropOverloadsFieldBuilder() : null;
                    }
                }
                if (policy.hasOverprovisioningFactor()) {
                    mergeOverprovisioningFactor(policy.getOverprovisioningFactor());
                }
                if (policy.hasEndpointStaleAfter()) {
                    mergeEndpointStaleAfter(policy.getEndpointStaleAfter());
                }
                if (policy.getDisableOverprovisioning()) {
                    setDisableOverprovisioning(policy.getDisableOverprovisioning());
                }
                m12387mergeUnknownFields(policy.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.Builder m12383mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.access$2000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment$Policy r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment$Policy r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Policy.Builder.m12383mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment$Policy$Builder");
            }

            private void ensureDropOverloadsIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.dropOverloads_ = new ArrayList(this.dropOverloads_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
            public List<DropOverload> getDropOverloadsList() {
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.dropOverloads_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
            public int getDropOverloadsCount() {
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.dropOverloads_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
            public DropOverload getDropOverloads(int i) {
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.dropOverloads_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setDropOverloads(int i, DropOverload dropOverload) {
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    dropOverload.getClass();
                    ensureDropOverloadsIsMutable();
                    this.dropOverloads_.set(i, dropOverload);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, dropOverload);
                }
                return this;
            }

            public Builder setDropOverloads(int i, DropOverload.Builder builder) {
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDropOverloadsIsMutable();
                    this.dropOverloads_.set(i, builder.m12405build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.m12405build());
                }
                return this;
            }

            public Builder addDropOverloads(DropOverload dropOverload) {
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    dropOverload.getClass();
                    ensureDropOverloadsIsMutable();
                    this.dropOverloads_.add(dropOverload);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(dropOverload);
                }
                return this;
            }

            public Builder addDropOverloads(int i, DropOverload dropOverload) {
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    dropOverload.getClass();
                    ensureDropOverloadsIsMutable();
                    this.dropOverloads_.add(i, dropOverload);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, dropOverload);
                }
                return this;
            }

            public Builder addDropOverloads(DropOverload.Builder builder) {
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDropOverloadsIsMutable();
                    this.dropOverloads_.add(builder.m12405build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.m12405build());
                }
                return this;
            }

            public Builder addDropOverloads(int i, DropOverload.Builder builder) {
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDropOverloadsIsMutable();
                    this.dropOverloads_.add(i, builder.m12405build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.m12405build());
                }
                return this;
            }

            public Builder addAllDropOverloads(Iterable<? extends DropOverload> iterable) {
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDropOverloadsIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.dropOverloads_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearDropOverloads() {
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.dropOverloads_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeDropOverloads(int i) {
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDropOverloadsIsMutable();
                    this.dropOverloads_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public DropOverload.Builder getDropOverloadsBuilder(int i) {
                return getDropOverloadsFieldBuilder().getBuilder(i);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
            public DropOverloadOrBuilder getDropOverloadsOrBuilder(int i) {
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.dropOverloads_.get(i);
                }
                return (DropOverloadOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
            public List<? extends DropOverloadOrBuilder> getDropOverloadsOrBuilderList() {
                RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> repeatedFieldBuilderV3 = this.dropOverloadsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.dropOverloads_);
            }

            public DropOverload.Builder addDropOverloadsBuilder() {
                return getDropOverloadsFieldBuilder().addBuilder(DropOverload.getDefaultInstance());
            }

            public DropOverload.Builder addDropOverloadsBuilder(int i) {
                return getDropOverloadsFieldBuilder().addBuilder(i, DropOverload.getDefaultInstance());
            }

            public List<DropOverload.Builder> getDropOverloadsBuilderList() {
                return getDropOverloadsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<DropOverload, DropOverload.Builder, DropOverloadOrBuilder> getDropOverloadsFieldBuilder() {
                if (this.dropOverloadsBuilder_ == null) {
                    this.dropOverloadsBuilder_ = new RepeatedFieldBuilderV3<>(this.dropOverloads_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.dropOverloads_ = null;
                }
                return this.dropOverloadsBuilder_;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
            public UInt32Value getOverprovisioningFactor() {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.overprovisioningFactorBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                UInt32Value uInt32Value = this.overprovisioningFactor_;
                return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
            }

            public Builder setOverprovisioningFactor(UInt32Value uInt32Value) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.overprovisioningFactorBuilder_;
                if (singleFieldBuilderV3 == null) {
                    uInt32Value.getClass();
                    this.overprovisioningFactor_ = uInt32Value;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(uInt32Value);
                }
                return this;
            }

            public Builder setOverprovisioningFactor(UInt32Value.Builder builder) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.overprovisioningFactorBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.overprovisioningFactor_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeOverprovisioningFactor(UInt32Value uInt32Value) {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.overprovisioningFactorBuilder_;
                if (singleFieldBuilderV3 == null) {
                    UInt32Value uInt32Value2 = this.overprovisioningFactor_;
                    if (uInt32Value2 != null) {
                        this.overprovisioningFactor_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                    } else {
                        this.overprovisioningFactor_ = uInt32Value;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(uInt32Value);
                }
                return this;
            }

            public Builder clearOverprovisioningFactor() {
                if (this.overprovisioningFactorBuilder_ == null) {
                    this.overprovisioningFactor_ = null;
                    onChanged();
                } else {
                    this.overprovisioningFactor_ = null;
                    this.overprovisioningFactorBuilder_ = null;
                }
                return this;
            }

            public UInt32Value.Builder getOverprovisioningFactorBuilder() {
                onChanged();
                return getOverprovisioningFactorFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
            public UInt32ValueOrBuilder getOverprovisioningFactorOrBuilder() {
                SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.overprovisioningFactorBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                UInt32Value uInt32Value = this.overprovisioningFactor_;
                return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
            }

            private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getOverprovisioningFactorFieldBuilder() {
                if (this.overprovisioningFactorBuilder_ == null) {
                    this.overprovisioningFactorBuilder_ = new SingleFieldBuilderV3<>(getOverprovisioningFactor(), getParentForChildren(), isClean());
                    this.overprovisioningFactor_ = null;
                }
                return this.overprovisioningFactorBuilder_;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
            public Duration getEndpointStaleAfter() {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.endpointStaleAfterBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Duration duration = this.endpointStaleAfter_;
                return duration == null ? Duration.getDefaultInstance() : duration;
            }

            public Builder setEndpointStaleAfter(Duration duration) {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.endpointStaleAfterBuilder_;
                if (singleFieldBuilderV3 == null) {
                    duration.getClass();
                    this.endpointStaleAfter_ = duration;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(duration);
                }
                return this;
            }

            public Builder setEndpointStaleAfter(Duration.Builder builder) {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.endpointStaleAfterBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.endpointStaleAfter_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeEndpointStaleAfter(Duration duration) {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.endpointStaleAfterBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Duration duration2 = this.endpointStaleAfter_;
                    if (duration2 != null) {
                        this.endpointStaleAfter_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                    } else {
                        this.endpointStaleAfter_ = duration;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(duration);
                }
                return this;
            }

            public Builder clearEndpointStaleAfter() {
                if (this.endpointStaleAfterBuilder_ == null) {
                    this.endpointStaleAfter_ = null;
                    onChanged();
                } else {
                    this.endpointStaleAfter_ = null;
                    this.endpointStaleAfterBuilder_ = null;
                }
                return this;
            }

            public Duration.Builder getEndpointStaleAfterBuilder() {
                onChanged();
                return getEndpointStaleAfterFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.PolicyOrBuilder
            public DurationOrBuilder getEndpointStaleAfterOrBuilder() {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.endpointStaleAfterBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Duration duration = this.endpointStaleAfter_;
                return duration == null ? Duration.getDefaultInstance() : duration;
            }

            private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getEndpointStaleAfterFieldBuilder() {
                if (this.endpointStaleAfterBuilder_ == null) {
                    this.endpointStaleAfterBuilder_ = new SingleFieldBuilderV3<>(getEndpointStaleAfter(), getParentForChildren(), isClean());
                    this.endpointStaleAfter_ = null;
                }
                return this.endpointStaleAfterBuilder_;
            }

            @Deprecated
            public Builder clearDisableOverprovisioning() {
                this.disableOverprovisioning_ = false;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m12393setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m12387mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    private static final class NamedEndpointsDefaultEntryHolder {
        static final MapEntry<String, Endpoint> defaultEntry = MapEntry.newDefaultInstance(EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_NamedEndpointsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Endpoint.getDefaultInstance());

        private NamedEndpointsDefaultEntryHolder() {
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ClusterLoadAssignmentOrBuilder {
        private int bitField0_;
        private Object clusterName_;
        private RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> endpointsBuilder_;
        private List<LocalityLbEndpoints> endpoints_;
        private MapField<String, Endpoint> namedEndpoints_;
        private SingleFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> policyBuilder_;
        private Policy policy_;

        private Builder() {
            this.clusterName_ = "";
            this.endpoints_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.clusterName_ = "";
            this.endpoints_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public boolean hasPolicy() {
            return (this.policyBuilder_ == null && this.policy_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 5) {
                return internalGetNamedEndpoints();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected MapField internalGetMutableMapField(int i) {
            if (i == 5) {
                return internalGetMutableNamedEndpoints();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_fieldAccessorTable.ensureFieldAccessorsInitialized(ClusterLoadAssignment.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (ClusterLoadAssignment.alwaysUseFieldBuilders) {
                getEndpointsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12319clear() {
            super.clear();
            this.clusterName_ = "";
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.endpoints_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            internalGetMutableNamedEndpoints().clear();
            if (this.policyBuilder_ == null) {
                this.policy_ = null;
            } else {
                this.policy_ = null;
                this.policyBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return EndpointProto.internal_static_envoy_api_v2_ClusterLoadAssignment_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ClusterLoadAssignment m12332getDefaultInstanceForType() {
            return ClusterLoadAssignment.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ClusterLoadAssignment m12313build() throws UninitializedMessageException {
            ClusterLoadAssignment clusterLoadAssignmentM12315buildPartial = m12315buildPartial();
            if (clusterLoadAssignmentM12315buildPartial.isInitialized()) {
                return clusterLoadAssignmentM12315buildPartial;
            }
            throw newUninitializedMessageException(clusterLoadAssignmentM12315buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ClusterLoadAssignment m12315buildPartial() {
            ClusterLoadAssignment clusterLoadAssignment = new ClusterLoadAssignment(this);
            clusterLoadAssignment.clusterName_ = this.clusterName_;
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.endpoints_ = Collections.unmodifiableList(this.endpoints_);
                    this.bitField0_ &= -2;
                }
                clusterLoadAssignment.endpoints_ = this.endpoints_;
            } else {
                clusterLoadAssignment.endpoints_ = repeatedFieldBuilderV3.build();
            }
            clusterLoadAssignment.namedEndpoints_ = internalGetNamedEndpoints();
            clusterLoadAssignment.namedEndpoints_.makeImmutable();
            SingleFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> singleFieldBuilderV3 = this.policyBuilder_;
            if (singleFieldBuilderV3 == null) {
                clusterLoadAssignment.policy_ = this.policy_;
            } else {
                clusterLoadAssignment.policy_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return clusterLoadAssignment;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12331clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12343setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12321clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12324clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12345setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12311addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12336mergeFrom(Message message) {
            if (message instanceof ClusterLoadAssignment) {
                return mergeFrom((ClusterLoadAssignment) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ClusterLoadAssignment clusterLoadAssignment) {
            if (clusterLoadAssignment == ClusterLoadAssignment.getDefaultInstance()) {
                return this;
            }
            if (!clusterLoadAssignment.getClusterName().isEmpty()) {
                this.clusterName_ = clusterLoadAssignment.clusterName_;
                onChanged();
            }
            if (this.endpointsBuilder_ == null) {
                if (!clusterLoadAssignment.endpoints_.isEmpty()) {
                    if (this.endpoints_.isEmpty()) {
                        this.endpoints_ = clusterLoadAssignment.endpoints_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureEndpointsIsMutable();
                        this.endpoints_.addAll(clusterLoadAssignment.endpoints_);
                    }
                    onChanged();
                }
            } else if (!clusterLoadAssignment.endpoints_.isEmpty()) {
                if (!this.endpointsBuilder_.isEmpty()) {
                    this.endpointsBuilder_.addAllMessages(clusterLoadAssignment.endpoints_);
                } else {
                    this.endpointsBuilder_.dispose();
                    this.endpointsBuilder_ = null;
                    this.endpoints_ = clusterLoadAssignment.endpoints_;
                    this.bitField0_ &= -2;
                    this.endpointsBuilder_ = ClusterLoadAssignment.alwaysUseFieldBuilders ? getEndpointsFieldBuilder() : null;
                }
            }
            internalGetMutableNamedEndpoints().mergeFrom(clusterLoadAssignment.internalGetNamedEndpoints());
            if (clusterLoadAssignment.hasPolicy()) {
                mergePolicy(clusterLoadAssignment.getPolicy());
            }
            m12341mergeUnknownFields(clusterLoadAssignment.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Builder m12337mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.access$3300()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment.Builder.m12337mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public String getClusterName() {
            Object obj = this.clusterName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.clusterName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setClusterName(String str) {
            str.getClass();
            this.clusterName_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public ByteString getClusterNameBytes() {
            Object obj = this.clusterName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.clusterName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setClusterNameBytes(ByteString byteString) {
            byteString.getClass();
            ClusterLoadAssignment.checkByteStringIsUtf8(byteString);
            this.clusterName_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearClusterName() {
            this.clusterName_ = ClusterLoadAssignment.getDefaultInstance().getClusterName();
            onChanged();
            return this;
        }

        private void ensureEndpointsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.endpoints_ = new ArrayList(this.endpoints_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public List<LocalityLbEndpoints> getEndpointsList() {
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.endpoints_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public int getEndpointsCount() {
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.endpoints_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public LocalityLbEndpoints getEndpoints(int i) {
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.endpoints_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setEndpoints(int i, LocalityLbEndpoints localityLbEndpoints) {
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                localityLbEndpoints.getClass();
                ensureEndpointsIsMutable();
                this.endpoints_.set(i, localityLbEndpoints);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, localityLbEndpoints);
            }
            return this;
        }

        public Builder setEndpoints(int i, LocalityLbEndpoints.Builder builder) {
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                this.endpoints_.set(i, builder.m17357build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m17357build());
            }
            return this;
        }

        public Builder addEndpoints(LocalityLbEndpoints localityLbEndpoints) {
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                localityLbEndpoints.getClass();
                ensureEndpointsIsMutable();
                this.endpoints_.add(localityLbEndpoints);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(localityLbEndpoints);
            }
            return this;
        }

        public Builder addEndpoints(int i, LocalityLbEndpoints localityLbEndpoints) {
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                localityLbEndpoints.getClass();
                ensureEndpointsIsMutable();
                this.endpoints_.add(i, localityLbEndpoints);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, localityLbEndpoints);
            }
            return this;
        }

        public Builder addEndpoints(LocalityLbEndpoints.Builder builder) {
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                this.endpoints_.add(builder.m17357build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m17357build());
            }
            return this;
        }

        public Builder addEndpoints(int i, LocalityLbEndpoints.Builder builder) {
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                this.endpoints_.add(i, builder.m17357build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m17357build());
            }
            return this;
        }

        public Builder addAllEndpoints(Iterable<? extends LocalityLbEndpoints> iterable) {
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.endpoints_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearEndpoints() {
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.endpoints_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeEndpoints(int i) {
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                this.endpoints_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public LocalityLbEndpoints.Builder getEndpointsBuilder(int i) {
            return getEndpointsFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public LocalityLbEndpointsOrBuilder getEndpointsOrBuilder(int i) {
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.endpoints_.get(i);
            }
            return (LocalityLbEndpointsOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public List<? extends LocalityLbEndpointsOrBuilder> getEndpointsOrBuilderList() {
            RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.endpoints_);
        }

        public LocalityLbEndpoints.Builder addEndpointsBuilder() {
            return getEndpointsFieldBuilder().addBuilder(LocalityLbEndpoints.getDefaultInstance());
        }

        public LocalityLbEndpoints.Builder addEndpointsBuilder(int i) {
            return getEndpointsFieldBuilder().addBuilder(i, LocalityLbEndpoints.getDefaultInstance());
        }

        public List<LocalityLbEndpoints.Builder> getEndpointsBuilderList() {
            return getEndpointsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LocalityLbEndpoints, LocalityLbEndpoints.Builder, LocalityLbEndpointsOrBuilder> getEndpointsFieldBuilder() {
            if (this.endpointsBuilder_ == null) {
                this.endpointsBuilder_ = new RepeatedFieldBuilderV3<>(this.endpoints_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.endpoints_ = null;
            }
            return this.endpointsBuilder_;
        }

        private MapField<String, Endpoint> internalGetNamedEndpoints() {
            MapField<String, Endpoint> mapField = this.namedEndpoints_;
            return mapField == null ? MapField.emptyMapField(NamedEndpointsDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, Endpoint> internalGetMutableNamedEndpoints() {
            onChanged();
            if (this.namedEndpoints_ == null) {
                this.namedEndpoints_ = MapField.newMapField(NamedEndpointsDefaultEntryHolder.defaultEntry);
            }
            if (!this.namedEndpoints_.isMutable()) {
                this.namedEndpoints_ = this.namedEndpoints_.copy();
            }
            return this.namedEndpoints_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public int getNamedEndpointsCount() {
            return internalGetNamedEndpoints().getMap().size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public boolean containsNamedEndpoints(String str) {
            str.getClass();
            return internalGetNamedEndpoints().getMap().containsKey(str);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        @Deprecated
        public Map<String, Endpoint> getNamedEndpoints() {
            return getNamedEndpointsMap();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public Map<String, Endpoint> getNamedEndpointsMap() {
            return internalGetNamedEndpoints().getMap();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public Endpoint getNamedEndpointsOrDefault(String str, Endpoint endpoint) {
            str.getClass();
            Map map = internalGetNamedEndpoints().getMap();
            return map.containsKey(str) ? (Endpoint) map.get(str) : endpoint;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public Endpoint getNamedEndpointsOrThrow(String str) {
            str.getClass();
            Map map = internalGetNamedEndpoints().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return (Endpoint) map.get(str);
        }

        public Builder clearNamedEndpoints() {
            internalGetMutableNamedEndpoints().getMutableMap().clear();
            return this;
        }

        public Builder removeNamedEndpoints(String str) {
            str.getClass();
            internalGetMutableNamedEndpoints().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, Endpoint> getMutableNamedEndpoints() {
            return internalGetMutableNamedEndpoints().getMutableMap();
        }

        public Builder putNamedEndpoints(String str, Endpoint endpoint) {
            str.getClass();
            endpoint.getClass();
            internalGetMutableNamedEndpoints().getMutableMap().put(str, endpoint);
            return this;
        }

        public Builder putAllNamedEndpoints(Map<String, Endpoint> map) {
            internalGetMutableNamedEndpoints().getMutableMap().putAll(map);
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public Policy getPolicy() {
            SingleFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> singleFieldBuilderV3 = this.policyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Policy policy = this.policy_;
            return policy == null ? Policy.getDefaultInstance() : policy;
        }

        public Builder setPolicy(Policy policy) {
            SingleFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> singleFieldBuilderV3 = this.policyBuilder_;
            if (singleFieldBuilderV3 == null) {
                policy.getClass();
                this.policy_ = policy;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(policy);
            }
            return this;
        }

        public Builder setPolicy(Policy.Builder builder) {
            SingleFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> singleFieldBuilderV3 = this.policyBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.policy_ = builder.m12359build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m12359build());
            }
            return this;
        }

        public Builder mergePolicy(Policy policy) {
            SingleFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> singleFieldBuilderV3 = this.policyBuilder_;
            if (singleFieldBuilderV3 == null) {
                Policy policy2 = this.policy_;
                if (policy2 != null) {
                    this.policy_ = Policy.newBuilder(policy2).mergeFrom(policy).m12361buildPartial();
                } else {
                    this.policy_ = policy;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(policy);
            }
            return this;
        }

        public Builder clearPolicy() {
            if (this.policyBuilder_ == null) {
                this.policy_ = null;
                onChanged();
            } else {
                this.policy_ = null;
                this.policyBuilder_ = null;
            }
            return this;
        }

        public Policy.Builder getPolicyBuilder() {
            onChanged();
            return getPolicyFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignmentOrBuilder
        public PolicyOrBuilder getPolicyOrBuilder() {
            SingleFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> singleFieldBuilderV3 = this.policyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (PolicyOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Policy policy = this.policy_;
            return policy == null ? Policy.getDefaultInstance() : policy;
        }

        private SingleFieldBuilderV3<Policy, Policy.Builder, PolicyOrBuilder> getPolicyFieldBuilder() {
            if (this.policyBuilder_ == null) {
                this.policyBuilder_ = new SingleFieldBuilderV3<>(getPolicy(), getParentForChildren(), isClean());
                this.policy_ = null;
            }
            return this.policyBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m12347setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m12341mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
