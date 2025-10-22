package io.opencensus.proto.agent.metrics.v1;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.opencensus.proto.agent.common.v1.Node;
import io.opencensus.proto.agent.common.v1.NodeOrBuilder;
import io.opencensus.proto.metrics.v1.Metric;
import io.opencensus.proto.metrics.v1.MetricOrBuilder;
import io.opencensus.proto.resource.v1.Resource;
import io.opencensus.proto.resource.v1.ResourceOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class ExportMetricsServiceRequest extends GeneratedMessageV3 implements ExportMetricsServiceRequestOrBuilder {
    public static final int METRICS_FIELD_NUMBER = 2;
    public static final int NODE_FIELD_NUMBER = 1;
    public static final int RESOURCE_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final ExportMetricsServiceRequest DEFAULT_INSTANCE = new ExportMetricsServiceRequest();
    private static final Parser<ExportMetricsServiceRequest> PARSER = new AbstractParser<ExportMetricsServiceRequest>() { // from class: io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequest.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ExportMetricsServiceRequest m36504parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ExportMetricsServiceRequest(codedInputStream, extensionRegistryLite);
        }
    };
    private int bitField0_;
    private byte memoizedIsInitialized;
    private List<Metric> metrics_;
    private Node node_;
    private Resource resource_;

    private ExportMetricsServiceRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ExportMetricsServiceRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.metrics_ = Collections.emptyList();
    }

    private ExportMetricsServiceRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (true) {
            if (z) {
                break;
            }
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            Node node = this.node_;
                            Node.Builder builderM36364toBuilder = node != null ? node.m36364toBuilder() : null;
                            Node node2 = (Node) codedInputStream.readMessage(Node.parser(), extensionRegistryLite);
                            this.node_ = node2;
                            if (builderM36364toBuilder != null) {
                                builderM36364toBuilder.mergeFrom(node2);
                                this.node_ = builderM36364toBuilder.m36371buildPartial();
                            }
                        } else if (tag == 18) {
                            if ((i & 2) != 2) {
                                this.metrics_ = new ArrayList();
                                i |= 2;
                            }
                            this.metrics_.add(codedInputStream.readMessage(Metric.parser(), extensionRegistryLite));
                        } else if (tag != 26) {
                            if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            Resource resource = this.resource_;
                            Resource.Builder builderM37423toBuilder = resource != null ? resource.m37423toBuilder() : null;
                            Resource resource2 = (Resource) codedInputStream.readMessage(Resource.parser(), extensionRegistryLite);
                            this.resource_ = resource2;
                            if (builderM37423toBuilder != null) {
                                builderM37423toBuilder.mergeFrom(resource2);
                                this.resource_ = builderM37423toBuilder.m37430buildPartial();
                            }
                        }
                    }
                    z = true;
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if ((i & 2) == 2) {
                    this.metrics_ = Collections.unmodifiableList(this.metrics_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ExportMetricsServiceRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ExportMetricsServiceRequest> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MetricsServiceProto.internal_static_opencensus_proto_agent_metrics_v1_ExportMetricsServiceRequest_descriptor;
    }

    public static ExportMetricsServiceRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ExportMetricsServiceRequest) PARSER.parseFrom(byteBuffer);
    }

    public static ExportMetricsServiceRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExportMetricsServiceRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ExportMetricsServiceRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ExportMetricsServiceRequest) PARSER.parseFrom(byteString);
    }

    public static ExportMetricsServiceRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExportMetricsServiceRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ExportMetricsServiceRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ExportMetricsServiceRequest) PARSER.parseFrom(bArr);
    }

    public static ExportMetricsServiceRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExportMetricsServiceRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ExportMetricsServiceRequest parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ExportMetricsServiceRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ExportMetricsServiceRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ExportMetricsServiceRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ExportMetricsServiceRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ExportMetricsServiceRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m36502toBuilder();
    }

    public static Builder newBuilder(ExportMetricsServiceRequest exportMetricsServiceRequest) {
        return DEFAULT_INSTANCE.m36502toBuilder().mergeFrom(exportMetricsServiceRequest);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ExportMetricsServiceRequest m36497getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
    public List<Metric> getMetricsList() {
        return this.metrics_;
    }

    @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
    public List<? extends MetricOrBuilder> getMetricsOrBuilderList() {
        return this.metrics_;
    }

    public Parser<ExportMetricsServiceRequest> getParserForType() {
        return PARSER;
    }

    @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
    public boolean hasNode() {
        return this.node_ != null;
    }

    @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
    public boolean hasResource() {
        return this.resource_ != null;
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

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MetricsServiceProto.internal_static_opencensus_proto_agent_metrics_v1_ExportMetricsServiceRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ExportMetricsServiceRequest.class, Builder.class);
    }

    @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
    public Node getNode() {
        Node node = this.node_;
        return node == null ? Node.getDefaultInstance() : node;
    }

    @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
    public NodeOrBuilder getNodeOrBuilder() {
        return getNode();
    }

    @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
    public int getMetricsCount() {
        return this.metrics_.size();
    }

    @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
    public Metric getMetrics(int i) {
        return this.metrics_.get(i);
    }

    @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
    public MetricOrBuilder getMetricsOrBuilder(int i) {
        return this.metrics_.get(i);
    }

    @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
    public Resource getResource() {
        Resource resource = this.resource_;
        return resource == null ? Resource.getDefaultInstance() : resource;
    }

    @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
    public ResourceOrBuilder getResourceOrBuilder() {
        return getResource();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.node_ != null) {
            codedOutputStream.writeMessage(1, getNode());
        }
        for (int i = 0; i < this.metrics_.size(); i++) {
            codedOutputStream.writeMessage(2, this.metrics_.get(i));
        }
        if (this.resource_ != null) {
            codedOutputStream.writeMessage(3, getResource());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.node_ != null ? CodedOutputStream.computeMessageSize(1, getNode()) : 0;
        for (int i2 = 0; i2 < this.metrics_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, this.metrics_.get(i2));
        }
        if (this.resource_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getResource());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L4
            return r0
        L4:
            boolean r1 = r5 instanceof io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequest
            if (r1 != 0) goto Ld
            boolean r5 = super.equals(r5)
            return r5
        Ld:
            io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequest r5 = (io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequest) r5
            boolean r1 = r4.hasNode()
            boolean r2 = r5.hasNode()
            r3 = 0
            if (r1 != r2) goto L1c
            r1 = 1
            goto L1d
        L1c:
            r1 = 0
        L1d:
            boolean r2 = r4.hasNode()
            if (r2 == 0) goto L34
            if (r1 == 0) goto L50
            io.opencensus.proto.agent.common.v1.Node r1 = r4.getNode()
            io.opencensus.proto.agent.common.v1.Node r2 = r5.getNode()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L50
            goto L36
        L34:
            if (r1 == 0) goto L50
        L36:
            java.util.List r1 = r4.getMetricsList()
            java.util.List r2 = r5.getMetricsList()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L50
            boolean r1 = r4.hasResource()
            boolean r2 = r5.hasResource()
            if (r1 != r2) goto L50
            r1 = 1
            goto L51
        L50:
            r1 = 0
        L51:
            boolean r2 = r4.hasResource()
            if (r2 == 0) goto L68
            if (r1 == 0) goto L75
            io.opencensus.proto.resource.v1.Resource r1 = r4.getResource()
            io.opencensus.proto.resource.v1.Resource r2 = r5.getResource()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L75
            goto L6a
        L68:
            if (r1 == 0) goto L75
        L6a:
            com.google.protobuf.UnknownFieldSet r1 = r4.unknownFields
            com.google.protobuf.UnknownFieldSet r5 = r5.unknownFields
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L75
            goto L76
        L75:
            r0 = 0
        L76:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequest.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasNode()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getNode().hashCode();
        }
        if (getMetricsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getMetricsList().hashCode();
        }
        if (hasResource()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getResource().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m36499newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m36502toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExportMetricsServiceRequestOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> metricsBuilder_;
        private List<Metric> metrics_;
        private SingleFieldBuilderV3<Node, Node.Builder, NodeOrBuilder> nodeBuilder_;
        private Node node_;
        private SingleFieldBuilderV3<Resource, Resource.Builder, ResourceOrBuilder> resourceBuilder_;
        private Resource resource_;

        private Builder() {
            this.node_ = null;
            this.metrics_ = Collections.emptyList();
            this.resource_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.node_ = null;
            this.metrics_ = Collections.emptyList();
            this.resource_ = null;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MetricsServiceProto.internal_static_opencensus_proto_agent_metrics_v1_ExportMetricsServiceRequest_descriptor;
        }

        @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
        public boolean hasNode() {
            return (this.nodeBuilder_ == null && this.node_ == null) ? false : true;
        }

        @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
        public boolean hasResource() {
            return (this.resourceBuilder_ == null && this.resource_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MetricsServiceProto.internal_static_opencensus_proto_agent_metrics_v1_ExportMetricsServiceRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ExportMetricsServiceRequest.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (ExportMetricsServiceRequest.alwaysUseFieldBuilders) {
                getMetricsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36513clear() {
            super.clear();
            if (this.nodeBuilder_ == null) {
                this.node_ = null;
            } else {
                this.node_ = null;
                this.nodeBuilder_ = null;
            }
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.metrics_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            if (this.resourceBuilder_ == null) {
                this.resource_ = null;
            } else {
                this.resource_ = null;
                this.resourceBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return MetricsServiceProto.internal_static_opencensus_proto_agent_metrics_v1_ExportMetricsServiceRequest_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ExportMetricsServiceRequest m36526getDefaultInstanceForType() {
            return ExportMetricsServiceRequest.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ExportMetricsServiceRequest m36507build() throws UninitializedMessageException {
            ExportMetricsServiceRequest exportMetricsServiceRequestM36509buildPartial = m36509buildPartial();
            if (exportMetricsServiceRequestM36509buildPartial.isInitialized()) {
                return exportMetricsServiceRequestM36509buildPartial;
            }
            throw newUninitializedMessageException(exportMetricsServiceRequestM36509buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ExportMetricsServiceRequest m36509buildPartial() {
            ExportMetricsServiceRequest exportMetricsServiceRequest = new ExportMetricsServiceRequest(this);
            SingleFieldBuilderV3<Node, Node.Builder, NodeOrBuilder> singleFieldBuilderV3 = this.nodeBuilder_;
            if (singleFieldBuilderV3 == null) {
                exportMetricsServiceRequest.node_ = this.node_;
            } else {
                exportMetricsServiceRequest.node_ = singleFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.metrics_ = Collections.unmodifiableList(this.metrics_);
                    this.bitField0_ &= -3;
                }
                exportMetricsServiceRequest.metrics_ = this.metrics_;
            } else {
                exportMetricsServiceRequest.metrics_ = repeatedFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Resource, Resource.Builder, ResourceOrBuilder> singleFieldBuilderV32 = this.resourceBuilder_;
            if (singleFieldBuilderV32 == null) {
                exportMetricsServiceRequest.resource_ = this.resource_;
            } else {
                exportMetricsServiceRequest.resource_ = singleFieldBuilderV32.build();
            }
            exportMetricsServiceRequest.bitField0_ = 0;
            onBuilt();
            return exportMetricsServiceRequest;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36525clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36537setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36515clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36518clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36539setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36505addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36530mergeFrom(Message message) {
            if (message instanceof ExportMetricsServiceRequest) {
                return mergeFrom((ExportMetricsServiceRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ExportMetricsServiceRequest exportMetricsServiceRequest) {
            if (exportMetricsServiceRequest == ExportMetricsServiceRequest.getDefaultInstance()) {
                return this;
            }
            if (exportMetricsServiceRequest.hasNode()) {
                mergeNode(exportMetricsServiceRequest.getNode());
            }
            if (this.metricsBuilder_ == null) {
                if (!exportMetricsServiceRequest.metrics_.isEmpty()) {
                    if (this.metrics_.isEmpty()) {
                        this.metrics_ = exportMetricsServiceRequest.metrics_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureMetricsIsMutable();
                        this.metrics_.addAll(exportMetricsServiceRequest.metrics_);
                    }
                    onChanged();
                }
            } else if (!exportMetricsServiceRequest.metrics_.isEmpty()) {
                if (!this.metricsBuilder_.isEmpty()) {
                    this.metricsBuilder_.addAllMessages(exportMetricsServiceRequest.metrics_);
                } else {
                    this.metricsBuilder_.dispose();
                    this.metricsBuilder_ = null;
                    this.metrics_ = exportMetricsServiceRequest.metrics_;
                    this.bitField0_ &= -3;
                    this.metricsBuilder_ = ExportMetricsServiceRequest.alwaysUseFieldBuilders ? getMetricsFieldBuilder() : null;
                }
            }
            if (exportMetricsServiceRequest.hasResource()) {
                mergeResource(exportMetricsServiceRequest.getResource());
            }
            m36535mergeUnknownFields(exportMetricsServiceRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequest.Builder m36531mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequest.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequest r3 = (io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequest) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequest r4 = (io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequest) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequest.Builder.m36531mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequest$Builder");
        }

        @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
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
                this.node_ = builder.m36369build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m36369build());
            }
            return this;
        }

        public Builder mergeNode(Node node) {
            SingleFieldBuilderV3<Node, Node.Builder, NodeOrBuilder> singleFieldBuilderV3 = this.nodeBuilder_;
            if (singleFieldBuilderV3 == null) {
                Node node2 = this.node_;
                if (node2 != null) {
                    this.node_ = Node.newBuilder(node2).mergeFrom(node).m36371buildPartial();
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

        @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
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

        private void ensureMetricsIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.metrics_ = new ArrayList(this.metrics_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
        public List<Metric> getMetricsList() {
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.metrics_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
        public int getMetricsCount() {
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.metrics_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
        public Metric getMetrics(int i) {
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.metrics_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setMetrics(int i, Metric metric) {
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                metric.getClass();
                ensureMetricsIsMutable();
                this.metrics_.set(i, metric);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, metric);
            }
            return this;
        }

        public Builder setMetrics(int i, Metric.Builder builder) {
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                this.metrics_.set(i, builder.m37105build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m37105build());
            }
            return this;
        }

        public Builder addMetrics(Metric metric) {
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                metric.getClass();
                ensureMetricsIsMutable();
                this.metrics_.add(metric);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(metric);
            }
            return this;
        }

        public Builder addMetrics(int i, Metric metric) {
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                metric.getClass();
                ensureMetricsIsMutable();
                this.metrics_.add(i, metric);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, metric);
            }
            return this;
        }

        public Builder addMetrics(Metric.Builder builder) {
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                this.metrics_.add(builder.m37105build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m37105build());
            }
            return this;
        }

        public Builder addMetrics(int i, Metric.Builder builder) {
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                this.metrics_.add(i, builder.m37105build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m37105build());
            }
            return this;
        }

        public Builder addAllMetrics(Iterable<? extends Metric> iterable) {
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.metrics_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearMetrics() {
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.metrics_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeMetrics(int i) {
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                this.metrics_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Metric.Builder getMetricsBuilder(int i) {
            return getMetricsFieldBuilder().getBuilder(i);
        }

        @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
        public MetricOrBuilder getMetricsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.metrics_.get(i);
            }
            return (MetricOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
        public List<? extends MetricOrBuilder> getMetricsOrBuilderList() {
            RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.metrics_);
        }

        public Metric.Builder addMetricsBuilder() {
            return getMetricsFieldBuilder().addBuilder(Metric.getDefaultInstance());
        }

        public Metric.Builder addMetricsBuilder(int i) {
            return getMetricsFieldBuilder().addBuilder(i, Metric.getDefaultInstance());
        }

        public List<Metric.Builder> getMetricsBuilderList() {
            return getMetricsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Metric, Metric.Builder, MetricOrBuilder> getMetricsFieldBuilder() {
            if (this.metricsBuilder_ == null) {
                this.metricsBuilder_ = new RepeatedFieldBuilderV3<>(this.metrics_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.metrics_ = null;
            }
            return this.metricsBuilder_;
        }

        @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
        public Resource getResource() {
            SingleFieldBuilderV3<Resource, Resource.Builder, ResourceOrBuilder> singleFieldBuilderV3 = this.resourceBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Resource resource = this.resource_;
            return resource == null ? Resource.getDefaultInstance() : resource;
        }

        public Builder setResource(Resource resource) {
            SingleFieldBuilderV3<Resource, Resource.Builder, ResourceOrBuilder> singleFieldBuilderV3 = this.resourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                resource.getClass();
                this.resource_ = resource;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(resource);
            }
            return this;
        }

        public Builder setResource(Resource.Builder builder) {
            SingleFieldBuilderV3<Resource, Resource.Builder, ResourceOrBuilder> singleFieldBuilderV3 = this.resourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.resource_ = builder.m37428build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m37428build());
            }
            return this;
        }

        public Builder mergeResource(Resource resource) {
            SingleFieldBuilderV3<Resource, Resource.Builder, ResourceOrBuilder> singleFieldBuilderV3 = this.resourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                Resource resource2 = this.resource_;
                if (resource2 != null) {
                    this.resource_ = Resource.newBuilder(resource2).mergeFrom(resource).m37430buildPartial();
                } else {
                    this.resource_ = resource;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(resource);
            }
            return this;
        }

        public Builder clearResource() {
            if (this.resourceBuilder_ == null) {
                this.resource_ = null;
                onChanged();
            } else {
                this.resource_ = null;
                this.resourceBuilder_ = null;
            }
            return this;
        }

        public Resource.Builder getResourceBuilder() {
            onChanged();
            return getResourceFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceRequestOrBuilder
        public ResourceOrBuilder getResourceOrBuilder() {
            SingleFieldBuilderV3<Resource, Resource.Builder, ResourceOrBuilder> singleFieldBuilderV3 = this.resourceBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ResourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Resource resource = this.resource_;
            return resource == null ? Resource.getDefaultInstance() : resource;
        }

        private SingleFieldBuilderV3<Resource, Resource.Builder, ResourceOrBuilder> getResourceFieldBuilder() {
            if (this.resourceBuilder_ == null) {
                this.resourceBuilder_ = new SingleFieldBuilderV3<>(getResource(), getParentForChildren(), isClean());
                this.resource_ = null;
            }
            return this.resourceBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m36541setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m36535mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
