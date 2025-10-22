package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ControlPlane;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ControlPlaneOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class DiscoveryResponse extends GeneratedMessageV3 implements DiscoveryResponseOrBuilder {
    public static final int CANARY_FIELD_NUMBER = 3;
    public static final int CONTROL_PLANE_FIELD_NUMBER = 6;
    public static final int NONCE_FIELD_NUMBER = 5;
    public static final int RESOURCES_FIELD_NUMBER = 2;
    public static final int TYPE_URL_FIELD_NUMBER = 4;
    public static final int VERSION_INFO_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final DiscoveryResponse DEFAULT_INSTANCE = new DiscoveryResponse();
    private static final Parser<DiscoveryResponse> PARSER = new AbstractParser<DiscoveryResponse>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponse.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public DiscoveryResponse m12590parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DiscoveryResponse(codedInputStream, extensionRegistryLite);
        }
    };
    private boolean canary_;
    private ControlPlane controlPlane_;
    private byte memoizedIsInitialized;
    private volatile Object nonce_;
    private List<Any> resources_;
    private volatile Object typeUrl_;
    private volatile Object versionInfo_;

    private DiscoveryResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DiscoveryResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.versionInfo_ = "";
        this.resources_ = Collections.emptyList();
        this.typeUrl_ = "";
        this.nonce_ = "";
    }

    private DiscoveryResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.versionInfo_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                if (!(z2 & true)) {
                                    this.resources_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.resources_.add(codedInputStream.readMessage(Any.parser(), extensionRegistryLite));
                            } else if (tag == 24) {
                                this.canary_ = codedInputStream.readBool();
                            } else if (tag == 34) {
                                this.typeUrl_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 42) {
                                this.nonce_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 50) {
                                ControlPlane controlPlane = this.controlPlane_;
                                ControlPlane.Builder builderM14674toBuilder = controlPlane != null ? controlPlane.m14674toBuilder() : null;
                                ControlPlane controlPlane2 = (ControlPlane) codedInputStream.readMessage(ControlPlane.parser(), extensionRegistryLite);
                                this.controlPlane_ = controlPlane2;
                                if (builderM14674toBuilder != null) {
                                    builderM14674toBuilder.mergeFrom(controlPlane2);
                                    this.controlPlane_ = builderM14674toBuilder.m14681buildPartial();
                                }
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
                    this.resources_ = Collections.unmodifiableList(this.resources_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static DiscoveryResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DiscoveryResponse> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DiscoveryProto.internal_static_envoy_api_v2_DiscoveryResponse_descriptor;
    }

    public static DiscoveryResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DiscoveryResponse) PARSER.parseFrom(byteBuffer);
    }

    public static DiscoveryResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DiscoveryResponse) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DiscoveryResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DiscoveryResponse) PARSER.parseFrom(byteString);
    }

    public static DiscoveryResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DiscoveryResponse) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DiscoveryResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DiscoveryResponse) PARSER.parseFrom(bArr);
    }

    public static DiscoveryResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DiscoveryResponse) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DiscoveryResponse parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DiscoveryResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DiscoveryResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DiscoveryResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DiscoveryResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DiscoveryResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m12588toBuilder();
    }

    public static Builder newBuilder(DiscoveryResponse discoveryResponse) {
        return DEFAULT_INSTANCE.m12588toBuilder().mergeFrom(discoveryResponse);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public boolean getCanary() {
        return this.canary_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public DiscoveryResponse m12583getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<DiscoveryResponse> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public List<Any> getResourcesList() {
        return this.resources_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public List<? extends AnyOrBuilder> getResourcesOrBuilderList() {
        return this.resources_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public boolean hasControlPlane() {
        return this.controlPlane_ != null;
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
        return new DiscoveryResponse();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DiscoveryProto.internal_static_envoy_api_v2_DiscoveryResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(DiscoveryResponse.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public String getVersionInfo() {
        Object obj = this.versionInfo_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.versionInfo_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public ByteString getVersionInfoBytes() {
        Object obj = this.versionInfo_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.versionInfo_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public int getResourcesCount() {
        return this.resources_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public Any getResources(int i) {
        return this.resources_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public AnyOrBuilder getResourcesOrBuilder(int i) {
        return this.resources_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public String getTypeUrl() {
        Object obj = this.typeUrl_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.typeUrl_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public ByteString getTypeUrlBytes() {
        Object obj = this.typeUrl_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.typeUrl_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public String getNonce() {
        Object obj = this.nonce_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.nonce_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public ByteString getNonceBytes() {
        Object obj = this.nonce_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.nonce_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public ControlPlane getControlPlane() {
        ControlPlane controlPlane = this.controlPlane_;
        return controlPlane == null ? ControlPlane.getDefaultInstance() : controlPlane;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
    public ControlPlaneOrBuilder getControlPlaneOrBuilder() {
        return getControlPlane();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getVersionInfoBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.versionInfo_);
        }
        for (int i = 0; i < this.resources_.size(); i++) {
            codedOutputStream.writeMessage(2, this.resources_.get(i));
        }
        boolean z = this.canary_;
        if (z) {
            codedOutputStream.writeBool(3, z);
        }
        if (!getTypeUrlBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.typeUrl_);
        }
        if (!getNonceBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.nonce_);
        }
        if (this.controlPlane_ != null) {
            codedOutputStream.writeMessage(6, getControlPlane());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getVersionInfoBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.versionInfo_) : 0;
        for (int i2 = 0; i2 < this.resources_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, this.resources_.get(i2));
        }
        boolean z = this.canary_;
        if (z) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(3, z);
        }
        if (!getTypeUrlBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.typeUrl_);
        }
        if (!getNonceBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(5, this.nonce_);
        }
        if (this.controlPlane_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(6, getControlPlane());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DiscoveryResponse)) {
            return super.equals(obj);
        }
        DiscoveryResponse discoveryResponse = (DiscoveryResponse) obj;
        if (getVersionInfo().equals(discoveryResponse.getVersionInfo()) && getResourcesList().equals(discoveryResponse.getResourcesList()) && getCanary() == discoveryResponse.getCanary() && getTypeUrl().equals(discoveryResponse.getTypeUrl()) && getNonce().equals(discoveryResponse.getNonce()) && hasControlPlane() == discoveryResponse.hasControlPlane()) {
            return (!hasControlPlane() || getControlPlane().equals(discoveryResponse.getControlPlane())) && this.unknownFields.equals(discoveryResponse.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getVersionInfo().hashCode();
        if (getResourcesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getResourcesList().hashCode();
        }
        int iHashBoolean = (((((((((((iHashCode * 37) + 3) * 53) + Internal.hashBoolean(getCanary())) * 37) + 4) * 53) + getTypeUrl().hashCode()) * 37) + 5) * 53) + getNonce().hashCode();
        if (hasControlPlane()) {
            iHashBoolean = (((iHashBoolean * 37) + 6) * 53) + getControlPlane().hashCode();
        }
        int iHashCode2 = (iHashBoolean * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m12585newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m12588toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DiscoveryResponseOrBuilder {
        private int bitField0_;
        private boolean canary_;
        private SingleFieldBuilderV3<ControlPlane, ControlPlane.Builder, ControlPlaneOrBuilder> controlPlaneBuilder_;
        private ControlPlane controlPlane_;
        private Object nonce_;
        private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> resourcesBuilder_;
        private List<Any> resources_;
        private Object typeUrl_;
        private Object versionInfo_;

        private Builder() {
            this.versionInfo_ = "";
            this.resources_ = Collections.emptyList();
            this.typeUrl_ = "";
            this.nonce_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.versionInfo_ = "";
            this.resources_ = Collections.emptyList();
            this.typeUrl_ = "";
            this.nonce_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DiscoveryProto.internal_static_envoy_api_v2_DiscoveryResponse_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
        public boolean getCanary() {
            return this.canary_;
        }

        public Builder setCanary(boolean z) {
            this.canary_ = z;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
        public boolean hasControlPlane() {
            return (this.controlPlaneBuilder_ == null && this.controlPlane_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DiscoveryProto.internal_static_envoy_api_v2_DiscoveryResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(DiscoveryResponse.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (DiscoveryResponse.alwaysUseFieldBuilders) {
                getResourcesFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12599clear() {
            super.clear();
            this.versionInfo_ = "";
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.resources_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.canary_ = false;
            this.typeUrl_ = "";
            this.nonce_ = "";
            if (this.controlPlaneBuilder_ == null) {
                this.controlPlane_ = null;
            } else {
                this.controlPlane_ = null;
                this.controlPlaneBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return DiscoveryProto.internal_static_envoy_api_v2_DiscoveryResponse_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DiscoveryResponse m12612getDefaultInstanceForType() {
            return DiscoveryResponse.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DiscoveryResponse m12593build() throws UninitializedMessageException {
            DiscoveryResponse discoveryResponseM12595buildPartial = m12595buildPartial();
            if (discoveryResponseM12595buildPartial.isInitialized()) {
                return discoveryResponseM12595buildPartial;
            }
            throw newUninitializedMessageException(discoveryResponseM12595buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DiscoveryResponse m12595buildPartial() {
            DiscoveryResponse discoveryResponse = new DiscoveryResponse(this);
            discoveryResponse.versionInfo_ = this.versionInfo_;
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.resources_ = Collections.unmodifiableList(this.resources_);
                    this.bitField0_ &= -2;
                }
                discoveryResponse.resources_ = this.resources_;
            } else {
                discoveryResponse.resources_ = repeatedFieldBuilderV3.build();
            }
            discoveryResponse.canary_ = this.canary_;
            discoveryResponse.typeUrl_ = this.typeUrl_;
            discoveryResponse.nonce_ = this.nonce_;
            SingleFieldBuilderV3<ControlPlane, ControlPlane.Builder, ControlPlaneOrBuilder> singleFieldBuilderV3 = this.controlPlaneBuilder_;
            if (singleFieldBuilderV3 == null) {
                discoveryResponse.controlPlane_ = this.controlPlane_;
            } else {
                discoveryResponse.controlPlane_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return discoveryResponse;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12611clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12623setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12601clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12604clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12625setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12591addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m12616mergeFrom(Message message) {
            if (message instanceof DiscoveryResponse) {
                return mergeFrom((DiscoveryResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DiscoveryResponse discoveryResponse) {
            if (discoveryResponse == DiscoveryResponse.getDefaultInstance()) {
                return this;
            }
            if (!discoveryResponse.getVersionInfo().isEmpty()) {
                this.versionInfo_ = discoveryResponse.versionInfo_;
                onChanged();
            }
            if (this.resourcesBuilder_ == null) {
                if (!discoveryResponse.resources_.isEmpty()) {
                    if (this.resources_.isEmpty()) {
                        this.resources_ = discoveryResponse.resources_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureResourcesIsMutable();
                        this.resources_.addAll(discoveryResponse.resources_);
                    }
                    onChanged();
                }
            } else if (!discoveryResponse.resources_.isEmpty()) {
                if (!this.resourcesBuilder_.isEmpty()) {
                    this.resourcesBuilder_.addAllMessages(discoveryResponse.resources_);
                } else {
                    this.resourcesBuilder_.dispose();
                    this.resourcesBuilder_ = null;
                    this.resources_ = discoveryResponse.resources_;
                    this.bitField0_ &= -2;
                    this.resourcesBuilder_ = DiscoveryResponse.alwaysUseFieldBuilders ? getResourcesFieldBuilder() : null;
                }
            }
            if (discoveryResponse.getCanary()) {
                setCanary(discoveryResponse.getCanary());
            }
            if (!discoveryResponse.getTypeUrl().isEmpty()) {
                this.typeUrl_ = discoveryResponse.typeUrl_;
                onChanged();
            }
            if (!discoveryResponse.getNonce().isEmpty()) {
                this.nonce_ = discoveryResponse.nonce_;
                onChanged();
            }
            if (discoveryResponse.hasControlPlane()) {
                mergeControlPlane(discoveryResponse.getControlPlane());
            }
            m12621mergeUnknownFields(discoveryResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponse.Builder m12617mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponse.access$1200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponse r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponse) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponse r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponse) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponse.Builder.m12617mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponse$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
        public String getVersionInfo() {
            Object obj = this.versionInfo_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.versionInfo_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setVersionInfo(String str) {
            str.getClass();
            this.versionInfo_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
        public ByteString getVersionInfoBytes() {
            Object obj = this.versionInfo_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.versionInfo_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setVersionInfoBytes(ByteString byteString) {
            byteString.getClass();
            DiscoveryResponse.checkByteStringIsUtf8(byteString);
            this.versionInfo_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearVersionInfo() {
            this.versionInfo_ = DiscoveryResponse.getDefaultInstance().getVersionInfo();
            onChanged();
            return this;
        }

        private void ensureResourcesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.resources_ = new ArrayList(this.resources_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
        public List<Any> getResourcesList() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.resources_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
        public int getResourcesCount() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.resources_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
        public Any getResources(int i) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.resources_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setResources(int i, Any any) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                any.getClass();
                ensureResourcesIsMutable();
                this.resources_.set(i, any);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, any);
            }
            return this;
        }

        public Builder setResources(int i, Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureResourcesIsMutable();
                this.resources_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addResources(Any any) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                any.getClass();
                ensureResourcesIsMutable();
                this.resources_.add(any);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(any);
            }
            return this;
        }

        public Builder addResources(int i, Any any) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                any.getClass();
                ensureResourcesIsMutable();
                this.resources_.add(i, any);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, any);
            }
            return this;
        }

        public Builder addResources(Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureResourcesIsMutable();
                this.resources_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addResources(int i, Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureResourcesIsMutable();
                this.resources_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllResources(Iterable<? extends Any> iterable) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureResourcesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.resources_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearResources() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.resources_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeResources(int i) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureResourcesIsMutable();
                this.resources_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Any.Builder getResourcesBuilder(int i) {
            return getResourcesFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
        public AnyOrBuilder getResourcesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.resources_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
        public List<? extends AnyOrBuilder> getResourcesOrBuilderList() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.resourcesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.resources_);
        }

        public Any.Builder addResourcesBuilder() {
            return getResourcesFieldBuilder().addBuilder(Any.getDefaultInstance());
        }

        public Any.Builder addResourcesBuilder(int i) {
            return getResourcesFieldBuilder().addBuilder(i, Any.getDefaultInstance());
        }

        public List<Any.Builder> getResourcesBuilderList() {
            return getResourcesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getResourcesFieldBuilder() {
            if (this.resourcesBuilder_ == null) {
                this.resourcesBuilder_ = new RepeatedFieldBuilderV3<>(this.resources_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.resources_ = null;
            }
            return this.resourcesBuilder_;
        }

        public Builder clearCanary() {
            this.canary_ = false;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
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
            DiscoveryResponse.checkByteStringIsUtf8(byteString);
            this.typeUrl_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearTypeUrl() {
            this.typeUrl_ = DiscoveryResponse.getDefaultInstance().getTypeUrl();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
        public String getNonce() {
            Object obj = this.nonce_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.nonce_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setNonce(String str) {
            str.getClass();
            this.nonce_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
        public ByteString getNonceBytes() {
            Object obj = this.nonce_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.nonce_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNonceBytes(ByteString byteString) {
            byteString.getClass();
            DiscoveryResponse.checkByteStringIsUtf8(byteString);
            this.nonce_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearNonce() {
            this.nonce_ = DiscoveryResponse.getDefaultInstance().getNonce();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
        public ControlPlane getControlPlane() {
            SingleFieldBuilderV3<ControlPlane, ControlPlane.Builder, ControlPlaneOrBuilder> singleFieldBuilderV3 = this.controlPlaneBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            ControlPlane controlPlane = this.controlPlane_;
            return controlPlane == null ? ControlPlane.getDefaultInstance() : controlPlane;
        }

        public Builder setControlPlane(ControlPlane controlPlane) {
            SingleFieldBuilderV3<ControlPlane, ControlPlane.Builder, ControlPlaneOrBuilder> singleFieldBuilderV3 = this.controlPlaneBuilder_;
            if (singleFieldBuilderV3 == null) {
                controlPlane.getClass();
                this.controlPlane_ = controlPlane;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(controlPlane);
            }
            return this;
        }

        public Builder setControlPlane(ControlPlane.Builder builder) {
            SingleFieldBuilderV3<ControlPlane, ControlPlane.Builder, ControlPlaneOrBuilder> singleFieldBuilderV3 = this.controlPlaneBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.controlPlane_ = builder.m14679build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m14679build());
            }
            return this;
        }

        public Builder mergeControlPlane(ControlPlane controlPlane) {
            SingleFieldBuilderV3<ControlPlane, ControlPlane.Builder, ControlPlaneOrBuilder> singleFieldBuilderV3 = this.controlPlaneBuilder_;
            if (singleFieldBuilderV3 == null) {
                ControlPlane controlPlane2 = this.controlPlane_;
                if (controlPlane2 != null) {
                    this.controlPlane_ = ControlPlane.newBuilder(controlPlane2).mergeFrom(controlPlane).m14681buildPartial();
                } else {
                    this.controlPlane_ = controlPlane;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(controlPlane);
            }
            return this;
        }

        public Builder clearControlPlane() {
            if (this.controlPlaneBuilder_ == null) {
                this.controlPlane_ = null;
                onChanged();
            } else {
                this.controlPlane_ = null;
                this.controlPlaneBuilder_ = null;
            }
            return this;
        }

        public ControlPlane.Builder getControlPlaneBuilder() {
            onChanged();
            return getControlPlaneFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponseOrBuilder
        public ControlPlaneOrBuilder getControlPlaneOrBuilder() {
            SingleFieldBuilderV3<ControlPlane, ControlPlane.Builder, ControlPlaneOrBuilder> singleFieldBuilderV3 = this.controlPlaneBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ControlPlaneOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            ControlPlane controlPlane = this.controlPlane_;
            return controlPlane == null ? ControlPlane.getDefaultInstance() : controlPlane;
        }

        private SingleFieldBuilderV3<ControlPlane, ControlPlane.Builder, ControlPlaneOrBuilder> getControlPlaneFieldBuilder() {
            if (this.controlPlaneBuilder_ == null) {
                this.controlPlaneBuilder_ = new SingleFieldBuilderV3<>(getControlPlane(), getParentForChildren(), isClean());
                this.controlPlane_ = null;
            }
            return this.controlPlaneBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m12627setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m12621mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
