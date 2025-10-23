package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.DataSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.DataSourceOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class DirectResponseAction extends GeneratedMessageV3 implements DirectResponseActionOrBuilder {
    public static final int BODY_FIELD_NUMBER = 2;
    public static final int STATUS_FIELD_NUMBER = 1;
    private static final DirectResponseAction DEFAULT_INSTANCE = new DirectResponseAction();
    private static final Parser<DirectResponseAction> PARSER = new AbstractParser<DirectResponseAction>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseAction.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public DirectResponseAction m17957parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DirectResponseAction(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private DataSource body_;
    private byte memoizedIsInitialized;
    private int status_;

    private DirectResponseAction(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DirectResponseAction() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private DirectResponseAction(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 8) {
                            this.status_ = codedInputStream.readUInt32();
                        } else if (tag == 18) {
                            DataSource dataSource = this.body_;
                            DataSource.Builder builderM14720toBuilder = dataSource != null ? dataSource.m14720toBuilder() : null;
                            DataSource dataSource2 = (DataSource) codedInputStream.readMessage(DataSource.parser(), extensionRegistryLite);
                            this.body_ = dataSource2;
                            if (builderM14720toBuilder != null) {
                                builderM14720toBuilder.mergeFrom(dataSource2);
                                this.body_ = builderM14720toBuilder.m14727buildPartial();
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

    public static DirectResponseAction getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DirectResponseAction> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RouteComponentsProto.internal_static_envoy_api_v2_route_DirectResponseAction_descriptor;
    }

    public static DirectResponseAction parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DirectResponseAction) PARSER.parseFrom(byteBuffer);
    }

    public static DirectResponseAction parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DirectResponseAction) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DirectResponseAction parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DirectResponseAction) PARSER.parseFrom(byteString);
    }

    public static DirectResponseAction parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DirectResponseAction) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DirectResponseAction parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DirectResponseAction) PARSER.parseFrom(bArr);
    }

    public static DirectResponseAction parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DirectResponseAction) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DirectResponseAction parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DirectResponseAction parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DirectResponseAction parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DirectResponseAction parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DirectResponseAction parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DirectResponseAction parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m17955toBuilder();
    }

    public static Builder newBuilder(DirectResponseAction directResponseAction) {
        return DEFAULT_INSTANCE.m17955toBuilder().mergeFrom(directResponseAction);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public DirectResponseAction m17950getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<DirectResponseAction> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseActionOrBuilder
    public int getStatus() {
        return this.status_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseActionOrBuilder
    public boolean hasBody() {
        return this.body_ != null;
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
        return new DirectResponseAction();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RouteComponentsProto.internal_static_envoy_api_v2_route_DirectResponseAction_fieldAccessorTable.ensureFieldAccessorsInitialized(DirectResponseAction.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseActionOrBuilder
    public DataSource getBody() {
        DataSource dataSource = this.body_;
        return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseActionOrBuilder
    public DataSourceOrBuilder getBodyOrBuilder() {
        return getBody();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i = this.status_;
        if (i != 0) {
            codedOutputStream.writeUInt32(1, i);
        }
        if (this.body_ != null) {
            codedOutputStream.writeMessage(2, getBody());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = this.status_;
        int iComputeUInt32Size = i2 != 0 ? CodedOutputStream.computeUInt32Size(1, i2) : 0;
        if (this.body_ != null) {
            iComputeUInt32Size += CodedOutputStream.computeMessageSize(2, getBody());
        }
        int serializedSize = iComputeUInt32Size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DirectResponseAction)) {
            return super.equals(obj);
        }
        DirectResponseAction directResponseAction = (DirectResponseAction) obj;
        if (getStatus() == directResponseAction.getStatus() && hasBody() == directResponseAction.hasBody()) {
            return (!hasBody() || getBody().equals(directResponseAction.getBody())) && this.unknownFields.equals(directResponseAction.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getStatus();
        if (hasBody()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getBody().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m17952newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m17955toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DirectResponseActionOrBuilder {
        private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> bodyBuilder_;
        private DataSource body_;
        private int status_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_DirectResponseAction_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseActionOrBuilder
        public int getStatus() {
            return this.status_;
        }

        public Builder setStatus(int i) {
            this.status_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseActionOrBuilder
        public boolean hasBody() {
            return (this.bodyBuilder_ == null && this.body_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_DirectResponseAction_fieldAccessorTable.ensureFieldAccessorsInitialized(DirectResponseAction.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = DirectResponseAction.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17966clear() {
            super.clear();
            this.status_ = 0;
            if (this.bodyBuilder_ == null) {
                this.body_ = null;
            } else {
                this.body_ = null;
                this.bodyBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_DirectResponseAction_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DirectResponseAction m17979getDefaultInstanceForType() {
            return DirectResponseAction.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DirectResponseAction m17960build() throws UninitializedMessageException {
            DirectResponseAction directResponseActionM17962buildPartial = m17962buildPartial();
            if (directResponseActionM17962buildPartial.isInitialized()) {
                return directResponseActionM17962buildPartial;
            }
            throw newUninitializedMessageException(directResponseActionM17962buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DirectResponseAction m17962buildPartial() {
            DirectResponseAction directResponseAction = new DirectResponseAction(this);
            directResponseAction.status_ = this.status_;
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.bodyBuilder_;
            if (singleFieldBuilderV3 == null) {
                directResponseAction.body_ = this.body_;
            } else {
                directResponseAction.body_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return directResponseAction;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17978clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17990setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17968clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17971clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17992setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17958addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17983mergeFrom(Message message) {
            if (message instanceof DirectResponseAction) {
                return mergeFrom((DirectResponseAction) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DirectResponseAction directResponseAction) {
            if (directResponseAction == DirectResponseAction.getDefaultInstance()) {
                return this;
            }
            if (directResponseAction.getStatus() != 0) {
                setStatus(directResponseAction.getStatus());
            }
            if (directResponseAction.hasBody()) {
                mergeBody(directResponseAction.getBody());
            }
            m17988mergeUnknownFields(directResponseAction.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseAction.Builder m17984mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseAction.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseAction r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseAction) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseAction r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseAction) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseAction.Builder.m17984mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseAction$Builder");
        }

        public Builder clearStatus() {
            this.status_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseActionOrBuilder
        public DataSource getBody() {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.bodyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            DataSource dataSource = this.body_;
            return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
        }

        public Builder setBody(DataSource dataSource) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.bodyBuilder_;
            if (singleFieldBuilderV3 == null) {
                dataSource.getClass();
                this.body_ = dataSource;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(dataSource);
            }
            return this;
        }

        public Builder setBody(DataSource.Builder builder) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.bodyBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.body_ = builder.m14725build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m14725build());
            }
            return this;
        }

        public Builder mergeBody(DataSource dataSource) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.bodyBuilder_;
            if (singleFieldBuilderV3 == null) {
                DataSource dataSource2 = this.body_;
                if (dataSource2 != null) {
                    this.body_ = DataSource.newBuilder(dataSource2).mergeFrom(dataSource).m14727buildPartial();
                } else {
                    this.body_ = dataSource;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(dataSource);
            }
            return this;
        }

        public Builder clearBody() {
            if (this.bodyBuilder_ == null) {
                this.body_ = null;
                onChanged();
            } else {
                this.body_ = null;
                this.bodyBuilder_ = null;
            }
            return this;
        }

        public DataSource.Builder getBodyBuilder() {
            onChanged();
            return getBodyFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.DirectResponseActionOrBuilder
        public DataSourceOrBuilder getBodyOrBuilder() {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.bodyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (DataSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            DataSource dataSource = this.body_;
            return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
        }

        private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> getBodyFieldBuilder() {
            if (this.bodyBuilder_ == null) {
                this.bodyBuilder_ = new SingleFieldBuilderV3<>(getBody(), getParentForChildren(), isClean());
                this.body_ = null;
            }
            return this.bodyBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m17994setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m17988mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
