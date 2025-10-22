package io.opencensus.proto.agent.metrics.v1;

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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class ExportMetricsServiceResponse extends GeneratedMessageV3 implements ExportMetricsServiceResponseOrBuilder {
    private static final ExportMetricsServiceResponse DEFAULT_INSTANCE = new ExportMetricsServiceResponse();
    private static final Parser<ExportMetricsServiceResponse> PARSER = new AbstractParser<ExportMetricsServiceResponse>() { // from class: io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceResponse.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ExportMetricsServiceResponse m36550parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ExportMetricsServiceResponse(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;

    private ExportMetricsServiceResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ExportMetricsServiceResponse() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private ExportMetricsServiceResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag == 0 || !parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
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

    public static ExportMetricsServiceResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ExportMetricsServiceResponse> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MetricsServiceProto.internal_static_opencensus_proto_agent_metrics_v1_ExportMetricsServiceResponse_descriptor;
    }

    public static ExportMetricsServiceResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ExportMetricsServiceResponse) PARSER.parseFrom(byteBuffer);
    }

    public static ExportMetricsServiceResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExportMetricsServiceResponse) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ExportMetricsServiceResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ExportMetricsServiceResponse) PARSER.parseFrom(byteString);
    }

    public static ExportMetricsServiceResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExportMetricsServiceResponse) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ExportMetricsServiceResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ExportMetricsServiceResponse) PARSER.parseFrom(bArr);
    }

    public static ExportMetricsServiceResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExportMetricsServiceResponse) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ExportMetricsServiceResponse parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ExportMetricsServiceResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ExportMetricsServiceResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ExportMetricsServiceResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ExportMetricsServiceResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ExportMetricsServiceResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m36548toBuilder();
    }

    public static Builder newBuilder(ExportMetricsServiceResponse exportMetricsServiceResponse) {
        return DEFAULT_INSTANCE.m36548toBuilder().mergeFrom(exportMetricsServiceResponse);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ExportMetricsServiceResponse m36543getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ExportMetricsServiceResponse> getParserForType() {
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

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MetricsServiceProto.internal_static_opencensus_proto_agent_metrics_v1_ExportMetricsServiceResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ExportMetricsServiceResponse.class, Builder.class);
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
        if (!(obj instanceof ExportMetricsServiceResponse)) {
            return super.equals(obj);
        }
        return this.unknownFields.equals(((ExportMetricsServiceResponse) obj).unknownFields);
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
    public Builder m36545newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m36548toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExportMetricsServiceResponseOrBuilder {
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MetricsServiceProto.internal_static_opencensus_proto_agent_metrics_v1_ExportMetricsServiceResponse_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MetricsServiceProto.internal_static_opencensus_proto_agent_metrics_v1_ExportMetricsServiceResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ExportMetricsServiceResponse.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ExportMetricsServiceResponse.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36559clear() {
            super.clear();
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return MetricsServiceProto.internal_static_opencensus_proto_agent_metrics_v1_ExportMetricsServiceResponse_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ExportMetricsServiceResponse m36572getDefaultInstanceForType() {
            return ExportMetricsServiceResponse.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ExportMetricsServiceResponse m36553build() throws UninitializedMessageException {
            ExportMetricsServiceResponse exportMetricsServiceResponseM36555buildPartial = m36555buildPartial();
            if (exportMetricsServiceResponseM36555buildPartial.isInitialized()) {
                return exportMetricsServiceResponseM36555buildPartial;
            }
            throw newUninitializedMessageException(exportMetricsServiceResponseM36555buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ExportMetricsServiceResponse m36555buildPartial() {
            ExportMetricsServiceResponse exportMetricsServiceResponse = new ExportMetricsServiceResponse(this);
            onBuilt();
            return exportMetricsServiceResponse;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36571clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36583setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36561clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36564clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36585setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36551addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36576mergeFrom(Message message) {
            if (message instanceof ExportMetricsServiceResponse) {
                return mergeFrom((ExportMetricsServiceResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ExportMetricsServiceResponse exportMetricsServiceResponse) {
            if (exportMetricsServiceResponse == ExportMetricsServiceResponse.getDefaultInstance()) {
                return this;
            }
            m36581mergeUnknownFields(exportMetricsServiceResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceResponse.Builder m36577mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceResponse.access$500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceResponse r3 = (io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceResponse) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceResponse r4 = (io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceResponse) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceResponse.Builder.m36577mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.agent.metrics.v1.ExportMetricsServiceResponse$Builder");
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m36587setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m36581mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
