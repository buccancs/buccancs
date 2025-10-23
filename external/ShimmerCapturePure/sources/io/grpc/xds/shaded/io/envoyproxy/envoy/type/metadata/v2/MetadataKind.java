package io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class MetadataKind extends GeneratedMessageV3 implements MetadataKindOrBuilder {
    public static final int CLUSTER_FIELD_NUMBER = 3;
    public static final int HOST_FIELD_NUMBER = 4;
    public static final int REQUEST_FIELD_NUMBER = 1;
    public static final int ROUTE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final MetadataKind DEFAULT_INSTANCE = new MetadataKind();
    private static final Parser<MetadataKind> PARSER = new AbstractParser<MetadataKind>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public MetadataKind m33966parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MetadataKind(codedInputStream, extensionRegistryLite);
        }
    };
    private int kindCase_;
    private Object kind_;
    private byte memoizedIsInitialized;

    private MetadataKind(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.kindCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private MetadataKind() {
        this.kindCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private MetadataKind(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            Request.Builder builderM34102toBuilder = this.kindCase_ == 1 ? ((Request) this.kind_).m34102toBuilder() : null;
                            MessageLite message = codedInputStream.readMessage(Request.parser(), extensionRegistryLite);
                            this.kind_ = message;
                            if (builderM34102toBuilder != null) {
                                builderM34102toBuilder.mergeFrom((Request) message);
                                this.kind_ = builderM34102toBuilder.m34109buildPartial();
                            }
                            this.kindCase_ = 1;
                        } else if (tag == 18) {
                            Route.Builder builderM34148toBuilder = this.kindCase_ == 2 ? ((Route) this.kind_).m34148toBuilder() : null;
                            MessageLite message2 = codedInputStream.readMessage(Route.parser(), extensionRegistryLite);
                            this.kind_ = message2;
                            if (builderM34148toBuilder != null) {
                                builderM34148toBuilder.mergeFrom((Route) message2);
                                this.kind_ = builderM34148toBuilder.m34155buildPartial();
                            }
                            this.kindCase_ = 2;
                        } else if (tag == 26) {
                            Cluster.Builder builderM34010toBuilder = this.kindCase_ == 3 ? ((Cluster) this.kind_).m34010toBuilder() : null;
                            MessageLite message3 = codedInputStream.readMessage(Cluster.parser(), extensionRegistryLite);
                            this.kind_ = message3;
                            if (builderM34010toBuilder != null) {
                                builderM34010toBuilder.mergeFrom((Cluster) message3);
                                this.kind_ = builderM34010toBuilder.m34017buildPartial();
                            }
                            this.kindCase_ = 3;
                        } else if (tag == 34) {
                            Host.Builder builderM34056toBuilder = this.kindCase_ == 4 ? ((Host) this.kind_).m34056toBuilder() : null;
                            MessageLite message4 = codedInputStream.readMessage(Host.parser(), extensionRegistryLite);
                            this.kind_ = message4;
                            if (builderM34056toBuilder != null) {
                                builderM34056toBuilder.mergeFrom((Host) message4);
                                this.kind_ = builderM34056toBuilder.m34063buildPartial();
                            }
                            this.kindCase_ = 4;
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

    public static MetadataKind getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MetadataKind> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_descriptor;
    }

    public static MetadataKind parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MetadataKind) PARSER.parseFrom(byteBuffer);
    }

    public static MetadataKind parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetadataKind) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MetadataKind parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MetadataKind) PARSER.parseFrom(byteString);
    }

    public static MetadataKind parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetadataKind) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MetadataKind parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MetadataKind) PARSER.parseFrom(bArr);
    }

    public static MetadataKind parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetadataKind) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MetadataKind parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MetadataKind parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetadataKind parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MetadataKind parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetadataKind parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MetadataKind parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m33964toBuilder();
    }

    public static Builder newBuilder(MetadataKind metadataKind) {
        return DEFAULT_INSTANCE.m33964toBuilder().mergeFrom(metadataKind);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public MetadataKind m33959getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<MetadataKind> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
    public boolean hasCluster() {
        return this.kindCase_ == 3;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
    public boolean hasHost() {
        return this.kindCase_ == 4;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
    public boolean hasRequest() {
        return this.kindCase_ == 1;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
    public boolean hasRoute() {
        return this.kindCase_ == 2;
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
        return new MetadataKind();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_fieldAccessorTable.ensureFieldAccessorsInitialized(MetadataKind.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
    public KindCase getKindCase() {
        return KindCase.forNumber(this.kindCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
    public Request getRequest() {
        if (this.kindCase_ == 1) {
            return (Request) this.kind_;
        }
        return Request.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
    public RequestOrBuilder getRequestOrBuilder() {
        if (this.kindCase_ == 1) {
            return (Request) this.kind_;
        }
        return Request.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
    public Route getRoute() {
        if (this.kindCase_ == 2) {
            return (Route) this.kind_;
        }
        return Route.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
    public RouteOrBuilder getRouteOrBuilder() {
        if (this.kindCase_ == 2) {
            return (Route) this.kind_;
        }
        return Route.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
    public Cluster getCluster() {
        if (this.kindCase_ == 3) {
            return (Cluster) this.kind_;
        }
        return Cluster.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
    public ClusterOrBuilder getClusterOrBuilder() {
        if (this.kindCase_ == 3) {
            return (Cluster) this.kind_;
        }
        return Cluster.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
    public Host getHost() {
        if (this.kindCase_ == 4) {
            return (Host) this.kind_;
        }
        return Host.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
    public HostOrBuilder getHostOrBuilder() {
        if (this.kindCase_ == 4) {
            return (Host) this.kind_;
        }
        return Host.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.kindCase_ == 1) {
            codedOutputStream.writeMessage(1, (Request) this.kind_);
        }
        if (this.kindCase_ == 2) {
            codedOutputStream.writeMessage(2, (Route) this.kind_);
        }
        if (this.kindCase_ == 3) {
            codedOutputStream.writeMessage(3, (Cluster) this.kind_);
        }
        if (this.kindCase_ == 4) {
            codedOutputStream.writeMessage(4, (Host) this.kind_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.kindCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (Request) this.kind_) : 0;
        if (this.kindCase_ == 2) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, (Route) this.kind_);
        }
        if (this.kindCase_ == 3) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, (Cluster) this.kind_);
        }
        if (this.kindCase_ == 4) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(4, (Host) this.kind_);
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MetadataKind)) {
            return super.equals(obj);
        }
        MetadataKind metadataKind = (MetadataKind) obj;
        if (!getKindCase().equals(metadataKind.getKindCase())) {
            return false;
        }
        int i = this.kindCase_;
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    if (!getCluster().equals(metadataKind.getCluster())) {
                        return false;
                    }
                } else if (i == 4 && !getHost().equals(metadataKind.getHost())) {
                    return false;
                }
            } else if (!getRoute().equals(metadataKind.getRoute())) {
                return false;
            }
        } else if (!getRequest().equals(metadataKind.getRequest())) {
            return false;
        }
        return this.unknownFields.equals(metadataKind.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        int i2 = this.kindCase_;
        if (i2 == 1) {
            i = ((iHashCode2 * 37) + 1) * 53;
            iHashCode = getRequest().hashCode();
        } else if (i2 == 2) {
            i = ((iHashCode2 * 37) + 2) * 53;
            iHashCode = getRoute().hashCode();
        } else if (i2 == 3) {
            i = ((iHashCode2 * 37) + 3) * 53;
            iHashCode = getCluster().hashCode();
        } else {
            if (i2 == 4) {
                i = ((iHashCode2 * 37) + 4) * 53;
                iHashCode = getHost().hashCode();
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
    public Builder m33961newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m33964toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum KindCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        REQUEST(1),
        ROUTE(2),
        CLUSTER(3),
        HOST(4),
        KIND_NOT_SET(0);

        private final int value;

        KindCase(int i) {
            this.value = i;
        }

        public static KindCase forNumber(int i) {
            if (i == 0) {
                return KIND_NOT_SET;
            }
            if (i == 1) {
                return REQUEST;
            }
            if (i == 2) {
                return ROUTE;
            }
            if (i == 3) {
                return CLUSTER;
            }
            if (i != 4) {
                return null;
            }
            return HOST;
        }

        @Deprecated
        public static KindCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public interface ClusterOrBuilder extends MessageOrBuilder {
    }

    public interface HostOrBuilder extends MessageOrBuilder {
    }

    public interface RequestOrBuilder extends MessageOrBuilder {
    }

    public interface RouteOrBuilder extends MessageOrBuilder {
    }

    public static final class Request extends GeneratedMessageV3 implements RequestOrBuilder {
        private static final Request DEFAULT_INSTANCE = new Request();
        private static final Parser<Request> PARSER = new AbstractParser<Request>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Request.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Request m34104parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Request(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private Request(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Request() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private Request(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag == 0 || !parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                z = true;
                            }
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

        public static Request getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Request> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Request_descriptor;
        }

        public static Request parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Request) PARSER.parseFrom(byteBuffer);
        }

        public static Request parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Request) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Request parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Request) PARSER.parseFrom(byteString);
        }

        public static Request parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Request) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Request parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Request) PARSER.parseFrom(bArr);
        }

        public static Request parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Request) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Request parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Request parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Request parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Request parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Request parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Request parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m34102toBuilder();
        }

        public static Builder newBuilder(Request request) {
            return DEFAULT_INSTANCE.m34102toBuilder().mergeFrom(request);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Request m34097getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Request> getParserForType() {
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
            return new Request();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Request_fieldAccessorTable.ensureFieldAccessorsInitialized(Request.class, Builder.class);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Request) {
                return this.unknownFields.equals(((Request) obj).unknownFields);
            }
            return super.equals(obj);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34099newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34102toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RequestOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Request_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Request_fieldAccessorTable.ensureFieldAccessorsInitialized(Request.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Request.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34113clear() {
                super.clear();
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Request_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Request m34126getDefaultInstanceForType() {
                return Request.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Request m34107build() throws UninitializedMessageException {
                Request requestM34109buildPartial = m34109buildPartial();
                if (requestM34109buildPartial.isInitialized()) {
                    return requestM34109buildPartial;
                }
                throw newUninitializedMessageException(requestM34109buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Request m34109buildPartial() {
                Request request = new Request(this);
                onBuilt();
                return request;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34125clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34137setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34115clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34118clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34139setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34105addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34130mergeFrom(Message message) {
                if (message instanceof Request) {
                    return mergeFrom((Request) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Request request) {
                if (request == Request.getDefaultInstance()) {
                    return this;
                }
                m34135mergeUnknownFields(request.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Request.Builder m34131mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Request.access$500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind$Request r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Request) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind$Request r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Request) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Request.Builder.m34131mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind$Request$Builder");
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34141setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34135mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Route extends GeneratedMessageV3 implements RouteOrBuilder {
        private static final Route DEFAULT_INSTANCE = new Route();
        private static final Parser<Route> PARSER = new AbstractParser<Route>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Route.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Route m34150parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Route(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private Route(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Route() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private Route(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag == 0 || !parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                z = true;
                            }
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

        public static Route getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Route> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Route_descriptor;
        }

        public static Route parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Route) PARSER.parseFrom(byteBuffer);
        }

        public static Route parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Route) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Route parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Route) PARSER.parseFrom(byteString);
        }

        public static Route parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Route) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Route parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Route) PARSER.parseFrom(bArr);
        }

        public static Route parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Route) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Route parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Route parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Route parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Route parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Route parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Route parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m34148toBuilder();
        }

        public static Builder newBuilder(Route route) {
            return DEFAULT_INSTANCE.m34148toBuilder().mergeFrom(route);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Route m34143getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Route> getParserForType() {
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
            return new Route();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Route_fieldAccessorTable.ensureFieldAccessorsInitialized(Route.class, Builder.class);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Route) {
                return this.unknownFields.equals(((Route) obj).unknownFields);
            }
            return super.equals(obj);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34145newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34148toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RouteOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Route_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Route_fieldAccessorTable.ensureFieldAccessorsInitialized(Route.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Route.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34159clear() {
                super.clear();
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Route_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Route m34172getDefaultInstanceForType() {
                return Route.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Route m34153build() throws UninitializedMessageException {
                Route routeM34155buildPartial = m34155buildPartial();
                if (routeM34155buildPartial.isInitialized()) {
                    return routeM34155buildPartial;
                }
                throw newUninitializedMessageException(routeM34155buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Route m34155buildPartial() {
                Route route = new Route(this);
                onBuilt();
                return route;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34171clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34183setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34161clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34164clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34185setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34151addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34176mergeFrom(Message message) {
                if (message instanceof Route) {
                    return mergeFrom((Route) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Route route) {
                if (route == Route.getDefaultInstance()) {
                    return this;
                }
                m34181mergeUnknownFields(route.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Route.Builder m34177mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Route.access$1200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind$Route r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Route) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind$Route r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Route) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Route.Builder.m34177mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind$Route$Builder");
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34187setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34181mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Cluster extends GeneratedMessageV3 implements ClusterOrBuilder {
        private static final Cluster DEFAULT_INSTANCE = new Cluster();
        private static final Parser<Cluster> PARSER = new AbstractParser<Cluster>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Cluster.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Cluster m34012parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Cluster(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private Cluster(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Cluster() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private Cluster(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag == 0 || !parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                z = true;
                            }
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

        public static Cluster getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Cluster> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Cluster_descriptor;
        }

        public static Cluster parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Cluster) PARSER.parseFrom(byteBuffer);
        }

        public static Cluster parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Cluster) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Cluster parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Cluster) PARSER.parseFrom(byteString);
        }

        public static Cluster parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Cluster) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Cluster parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Cluster) PARSER.parseFrom(bArr);
        }

        public static Cluster parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Cluster) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Cluster parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Cluster parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Cluster parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Cluster parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Cluster parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Cluster parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m34010toBuilder();
        }

        public static Builder newBuilder(Cluster cluster) {
            return DEFAULT_INSTANCE.m34010toBuilder().mergeFrom(cluster);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Cluster m34005getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Cluster> getParserForType() {
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
            return new Cluster();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Cluster_fieldAccessorTable.ensureFieldAccessorsInitialized(Cluster.class, Builder.class);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Cluster) {
                return this.unknownFields.equals(((Cluster) obj).unknownFields);
            }
            return super.equals(obj);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34007newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34010toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ClusterOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Cluster_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Cluster_fieldAccessorTable.ensureFieldAccessorsInitialized(Cluster.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Cluster.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34021clear() {
                super.clear();
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Cluster_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Cluster m34034getDefaultInstanceForType() {
                return Cluster.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Cluster m34015build() throws UninitializedMessageException {
                Cluster clusterM34017buildPartial = m34017buildPartial();
                if (clusterM34017buildPartial.isInitialized()) {
                    return clusterM34017buildPartial;
                }
                throw newUninitializedMessageException(clusterM34017buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Cluster m34017buildPartial() {
                Cluster cluster = new Cluster(this);
                onBuilt();
                return cluster;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34033clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34045setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34023clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34026clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34047setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34013addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34038mergeFrom(Message message) {
                if (message instanceof Cluster) {
                    return mergeFrom((Cluster) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Cluster cluster) {
                if (cluster == Cluster.getDefaultInstance()) {
                    return this;
                }
                m34043mergeUnknownFields(cluster.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Cluster.Builder m34039mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Cluster.access$1900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind$Cluster r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Cluster) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind$Cluster r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Cluster) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Cluster.Builder.m34039mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind$Cluster$Builder");
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34049setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34043mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Host extends GeneratedMessageV3 implements HostOrBuilder {
        private static final Host DEFAULT_INSTANCE = new Host();
        private static final Parser<Host> PARSER = new AbstractParser<Host>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Host.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Host m34058parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Host(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private Host(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Host() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private Host(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag == 0 || !parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                z = true;
                            }
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

        public static Host getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Host> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Host_descriptor;
        }

        public static Host parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Host) PARSER.parseFrom(byteBuffer);
        }

        public static Host parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Host) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Host parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Host) PARSER.parseFrom(byteString);
        }

        public static Host parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Host) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Host parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Host) PARSER.parseFrom(bArr);
        }

        public static Host parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Host) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Host parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Host parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Host parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Host parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Host parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Host parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m34056toBuilder();
        }

        public static Builder newBuilder(Host host) {
            return DEFAULT_INSTANCE.m34056toBuilder().mergeFrom(host);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Host m34051getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Host> getParserForType() {
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
            return new Host();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Host_fieldAccessorTable.ensureFieldAccessorsInitialized(Host.class, Builder.class);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Host) {
                return this.unknownFields.equals(((Host) obj).unknownFields);
            }
            return super.equals(obj);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34053newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34056toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HostOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Host_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Host_fieldAccessorTable.ensureFieldAccessorsInitialized(Host.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Host.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34067clear() {
                super.clear();
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_Host_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Host m34080getDefaultInstanceForType() {
                return Host.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Host m34061build() throws UninitializedMessageException {
                Host hostM34063buildPartial = m34063buildPartial();
                if (hostM34063buildPartial.isInitialized()) {
                    return hostM34063buildPartial;
                }
                throw newUninitializedMessageException(hostM34063buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Host m34063buildPartial() {
                Host host = new Host(this);
                onBuilt();
                return host;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34079clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34091setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34069clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34072clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34093setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34059addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34084mergeFrom(Message message) {
                if (message instanceof Host) {
                    return mergeFrom((Host) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Host host) {
                if (host == Host.getDefaultInstance()) {
                    return this;
                }
                m34089mergeUnknownFields(host.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Host.Builder m34085mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Host.access$2600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind$Host r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Host) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind$Host r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Host) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Host.Builder.m34085mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind$Host$Builder");
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34095setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34089mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetadataKindOrBuilder {
        private SingleFieldBuilderV3<Cluster, Cluster.Builder, ClusterOrBuilder> clusterBuilder_;
        private SingleFieldBuilderV3<Host, Host.Builder, HostOrBuilder> hostBuilder_;
        private int kindCase_;
        private Object kind_;
        private SingleFieldBuilderV3<Request, Request.Builder, RequestOrBuilder> requestBuilder_;
        private SingleFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> routeBuilder_;

        private Builder() {
            this.kindCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.kindCase_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
        public boolean hasCluster() {
            return this.kindCase_ == 3;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
        public boolean hasHost() {
            return this.kindCase_ == 4;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
        public boolean hasRequest() {
            return this.kindCase_ == 1;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
        public boolean hasRoute() {
            return this.kindCase_ == 2;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_fieldAccessorTable.ensureFieldAccessorsInitialized(MetadataKind.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = MetadataKind.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33975clear() {
            super.clear();
            this.kindCase_ = 0;
            this.kind_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return MetadataProto.internal_static_envoy_type_metadata_v2_MetadataKind_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetadataKind m33988getDefaultInstanceForType() {
            return MetadataKind.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetadataKind m33969build() throws UninitializedMessageException {
            MetadataKind metadataKindM33971buildPartial = m33971buildPartial();
            if (metadataKindM33971buildPartial.isInitialized()) {
                return metadataKindM33971buildPartial;
            }
            throw newUninitializedMessageException(metadataKindM33971buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetadataKind m33971buildPartial() {
            MetadataKind metadataKind = new MetadataKind(this);
            if (this.kindCase_ == 1) {
                SingleFieldBuilderV3<Request, Request.Builder, RequestOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    metadataKind.kind_ = this.kind_;
                } else {
                    metadataKind.kind_ = singleFieldBuilderV3.build();
                }
            }
            if (this.kindCase_ == 2) {
                SingleFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> singleFieldBuilderV32 = this.routeBuilder_;
                if (singleFieldBuilderV32 == null) {
                    metadataKind.kind_ = this.kind_;
                } else {
                    metadataKind.kind_ = singleFieldBuilderV32.build();
                }
            }
            if (this.kindCase_ == 3) {
                SingleFieldBuilderV3<Cluster, Cluster.Builder, ClusterOrBuilder> singleFieldBuilderV33 = this.clusterBuilder_;
                if (singleFieldBuilderV33 == null) {
                    metadataKind.kind_ = this.kind_;
                } else {
                    metadataKind.kind_ = singleFieldBuilderV33.build();
                }
            }
            if (this.kindCase_ == 4) {
                SingleFieldBuilderV3<Host, Host.Builder, HostOrBuilder> singleFieldBuilderV34 = this.hostBuilder_;
                if (singleFieldBuilderV34 == null) {
                    metadataKind.kind_ = this.kind_;
                } else {
                    metadataKind.kind_ = singleFieldBuilderV34.build();
                }
            }
            metadataKind.kindCase_ = this.kindCase_;
            onBuilt();
            return metadataKind;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33987clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33999setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33977clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33980clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34001setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33967addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33992mergeFrom(Message message) {
            if (message instanceof MetadataKind) {
                return mergeFrom((MetadataKind) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MetadataKind metadataKind) {
            if (metadataKind == MetadataKind.getDefaultInstance()) {
                return this;
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$type$metadata$v2$MetadataKind$KindCase[metadataKind.getKindCase().ordinal()];
            if (i == 1) {
                mergeRequest(metadataKind.getRequest());
            } else if (i == 2) {
                mergeRoute(metadataKind.getRoute());
            } else if (i == 3) {
                mergeCluster(metadataKind.getCluster());
            } else if (i == 4) {
                mergeHost(metadataKind.getHost());
            }
            m33997mergeUnknownFields(metadataKind.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Builder m33993mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.access$3500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind.Builder.m33993mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
        public KindCase getKindCase() {
            return KindCase.forNumber(this.kindCase_);
        }

        public Builder clearKind() {
            this.kindCase_ = 0;
            this.kind_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
        public Request getRequest() {
            SingleFieldBuilderV3<Request, Request.Builder, RequestOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ == 1) {
                    return (Request) this.kind_;
                }
                return Request.getDefaultInstance();
            }
            if (this.kindCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            }
            return Request.getDefaultInstance();
        }

        public Builder setRequest(Request request) {
            SingleFieldBuilderV3<Request, Request.Builder, RequestOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 == null) {
                request.getClass();
                this.kind_ = request;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(request);
            }
            this.kindCase_ = 1;
            return this;
        }

        public Builder setRequest(Request.Builder builder) {
            SingleFieldBuilderV3<Request, Request.Builder, RequestOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.kind_ = builder.m34107build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m34107build());
            }
            this.kindCase_ = 1;
            return this;
        }

        public Builder mergeRequest(Request request) {
            SingleFieldBuilderV3<Request, Request.Builder, RequestOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ != 1 || this.kind_ == Request.getDefaultInstance()) {
                    this.kind_ = request;
                } else {
                    this.kind_ = Request.newBuilder((Request) this.kind_).mergeFrom(request).m34109buildPartial();
                }
                onChanged();
            } else {
                if (this.kindCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(request);
                }
                this.requestBuilder_.setMessage(request);
            }
            this.kindCase_ = 1;
            return this;
        }

        public Builder clearRequest() {
            SingleFieldBuilderV3<Request, Request.Builder, RequestOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.kindCase_ == 1) {
                    this.kindCase_ = 0;
                    this.kind_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.kindCase_ == 1) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        public Request.Builder getRequestBuilder() {
            return getRequestFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
        public RequestOrBuilder getRequestOrBuilder() {
            SingleFieldBuilderV3<Request, Request.Builder, RequestOrBuilder> singleFieldBuilderV3;
            int i = this.kindCase_;
            if (i == 1 && (singleFieldBuilderV3 = this.requestBuilder_) != null) {
                return (RequestOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 1) {
                return (Request) this.kind_;
            }
            return Request.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Request, Request.Builder, RequestOrBuilder> getRequestFieldBuilder() {
            if (this.requestBuilder_ == null) {
                if (this.kindCase_ != 1) {
                    this.kind_ = Request.getDefaultInstance();
                }
                this.requestBuilder_ = new SingleFieldBuilderV3<>((Request) this.kind_, getParentForChildren(), isClean());
                this.kind_ = null;
            }
            this.kindCase_ = 1;
            onChanged();
            return this.requestBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
        public Route getRoute() {
            SingleFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> singleFieldBuilderV3 = this.routeBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ == 2) {
                    return (Route) this.kind_;
                }
                return Route.getDefaultInstance();
            }
            if (this.kindCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return Route.getDefaultInstance();
        }

        public Builder setRoute(Route route) {
            SingleFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> singleFieldBuilderV3 = this.routeBuilder_;
            if (singleFieldBuilderV3 == null) {
                route.getClass();
                this.kind_ = route;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(route);
            }
            this.kindCase_ = 2;
            return this;
        }

        public Builder setRoute(Route.Builder builder) {
            SingleFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> singleFieldBuilderV3 = this.routeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.kind_ = builder.m34153build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m34153build());
            }
            this.kindCase_ = 2;
            return this;
        }

        public Builder mergeRoute(Route route) {
            SingleFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> singleFieldBuilderV3 = this.routeBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ != 2 || this.kind_ == Route.getDefaultInstance()) {
                    this.kind_ = route;
                } else {
                    this.kind_ = Route.newBuilder((Route) this.kind_).mergeFrom(route).m34155buildPartial();
                }
                onChanged();
            } else {
                if (this.kindCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(route);
                }
                this.routeBuilder_.setMessage(route);
            }
            this.kindCase_ = 2;
            return this;
        }

        public Builder clearRoute() {
            SingleFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> singleFieldBuilderV3 = this.routeBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.kindCase_ == 2) {
                    this.kindCase_ = 0;
                    this.kind_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.kindCase_ == 2) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        public Route.Builder getRouteBuilder() {
            return getRouteFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
        public RouteOrBuilder getRouteOrBuilder() {
            SingleFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> singleFieldBuilderV3;
            int i = this.kindCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.routeBuilder_) != null) {
                return (RouteOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (Route) this.kind_;
            }
            return Route.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Route, Route.Builder, RouteOrBuilder> getRouteFieldBuilder() {
            if (this.routeBuilder_ == null) {
                if (this.kindCase_ != 2) {
                    this.kind_ = Route.getDefaultInstance();
                }
                this.routeBuilder_ = new SingleFieldBuilderV3<>((Route) this.kind_, getParentForChildren(), isClean());
                this.kind_ = null;
            }
            this.kindCase_ = 2;
            onChanged();
            return this.routeBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
        public Cluster getCluster() {
            SingleFieldBuilderV3<Cluster, Cluster.Builder, ClusterOrBuilder> singleFieldBuilderV3 = this.clusterBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ == 3) {
                    return (Cluster) this.kind_;
                }
                return Cluster.getDefaultInstance();
            }
            if (this.kindCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return Cluster.getDefaultInstance();
        }

        public Builder setCluster(Cluster cluster) {
            SingleFieldBuilderV3<Cluster, Cluster.Builder, ClusterOrBuilder> singleFieldBuilderV3 = this.clusterBuilder_;
            if (singleFieldBuilderV3 == null) {
                cluster.getClass();
                this.kind_ = cluster;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(cluster);
            }
            this.kindCase_ = 3;
            return this;
        }

        public Builder setCluster(Cluster.Builder builder) {
            SingleFieldBuilderV3<Cluster, Cluster.Builder, ClusterOrBuilder> singleFieldBuilderV3 = this.clusterBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.kind_ = builder.m34015build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m34015build());
            }
            this.kindCase_ = 3;
            return this;
        }

        public Builder mergeCluster(Cluster cluster) {
            SingleFieldBuilderV3<Cluster, Cluster.Builder, ClusterOrBuilder> singleFieldBuilderV3 = this.clusterBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ != 3 || this.kind_ == Cluster.getDefaultInstance()) {
                    this.kind_ = cluster;
                } else {
                    this.kind_ = Cluster.newBuilder((Cluster) this.kind_).mergeFrom(cluster).m34017buildPartial();
                }
                onChanged();
            } else {
                if (this.kindCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(cluster);
                }
                this.clusterBuilder_.setMessage(cluster);
            }
            this.kindCase_ = 3;
            return this;
        }

        public Builder clearCluster() {
            SingleFieldBuilderV3<Cluster, Cluster.Builder, ClusterOrBuilder> singleFieldBuilderV3 = this.clusterBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.kindCase_ == 3) {
                    this.kindCase_ = 0;
                    this.kind_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.kindCase_ == 3) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        public Cluster.Builder getClusterBuilder() {
            return getClusterFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
        public ClusterOrBuilder getClusterOrBuilder() {
            SingleFieldBuilderV3<Cluster, Cluster.Builder, ClusterOrBuilder> singleFieldBuilderV3;
            int i = this.kindCase_;
            if (i == 3 && (singleFieldBuilderV3 = this.clusterBuilder_) != null) {
                return (ClusterOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 3) {
                return (Cluster) this.kind_;
            }
            return Cluster.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Cluster, Cluster.Builder, ClusterOrBuilder> getClusterFieldBuilder() {
            if (this.clusterBuilder_ == null) {
                if (this.kindCase_ != 3) {
                    this.kind_ = Cluster.getDefaultInstance();
                }
                this.clusterBuilder_ = new SingleFieldBuilderV3<>((Cluster) this.kind_, getParentForChildren(), isClean());
                this.kind_ = null;
            }
            this.kindCase_ = 3;
            onChanged();
            return this.clusterBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
        public Host getHost() {
            SingleFieldBuilderV3<Host, Host.Builder, HostOrBuilder> singleFieldBuilderV3 = this.hostBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ == 4) {
                    return (Host) this.kind_;
                }
                return Host.getDefaultInstance();
            }
            if (this.kindCase_ == 4) {
                return singleFieldBuilderV3.getMessage();
            }
            return Host.getDefaultInstance();
        }

        public Builder setHost(Host host) {
            SingleFieldBuilderV3<Host, Host.Builder, HostOrBuilder> singleFieldBuilderV3 = this.hostBuilder_;
            if (singleFieldBuilderV3 == null) {
                host.getClass();
                this.kind_ = host;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(host);
            }
            this.kindCase_ = 4;
            return this;
        }

        public Builder setHost(Host.Builder builder) {
            SingleFieldBuilderV3<Host, Host.Builder, HostOrBuilder> singleFieldBuilderV3 = this.hostBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.kind_ = builder.m34061build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m34061build());
            }
            this.kindCase_ = 4;
            return this;
        }

        public Builder mergeHost(Host host) {
            SingleFieldBuilderV3<Host, Host.Builder, HostOrBuilder> singleFieldBuilderV3 = this.hostBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ != 4 || this.kind_ == Host.getDefaultInstance()) {
                    this.kind_ = host;
                } else {
                    this.kind_ = Host.newBuilder((Host) this.kind_).mergeFrom(host).m34063buildPartial();
                }
                onChanged();
            } else {
                if (this.kindCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(host);
                }
                this.hostBuilder_.setMessage(host);
            }
            this.kindCase_ = 4;
            return this;
        }

        public Builder clearHost() {
            SingleFieldBuilderV3<Host, Host.Builder, HostOrBuilder> singleFieldBuilderV3 = this.hostBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.kindCase_ == 4) {
                    this.kindCase_ = 0;
                    this.kind_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.kindCase_ == 4) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        public Host.Builder getHostBuilder() {
            return getHostFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder
        public HostOrBuilder getHostOrBuilder() {
            SingleFieldBuilderV3<Host, Host.Builder, HostOrBuilder> singleFieldBuilderV3;
            int i = this.kindCase_;
            if (i == 4 && (singleFieldBuilderV3 = this.hostBuilder_) != null) {
                return (HostOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 4) {
                return (Host) this.kind_;
            }
            return Host.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Host, Host.Builder, HostOrBuilder> getHostFieldBuilder() {
            if (this.hostBuilder_ == null) {
                if (this.kindCase_ != 4) {
                    this.kind_ = Host.getDefaultInstance();
                }
                this.hostBuilder_ = new SingleFieldBuilderV3<>((Host) this.kind_, getParentForChildren(), isClean());
                this.kind_ = null;
            }
            this.kindCase_ = 4;
            onChanged();
            return this.hostBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m34003setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m33997mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$type$metadata$v2$MetadataKind$KindCase;

        static {
            int[] iArr = new int[KindCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$type$metadata$v2$MetadataKind$KindCase = iArr;
            try {
                iArr[KindCase.REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$metadata$v2$MetadataKind$KindCase[KindCase.ROUTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$metadata$v2$MetadataKind$KindCase[KindCase.CLUSTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$metadata$v2$MetadataKind$KindCase[KindCase.HOST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$metadata$v2$MetadataKind$KindCase[KindCase.KIND_NOT_SET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
