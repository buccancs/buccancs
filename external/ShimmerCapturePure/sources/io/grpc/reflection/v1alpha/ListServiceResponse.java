package io.grpc.reflection.v1alpha;

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
import io.grpc.reflection.v1alpha.ServiceResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class ListServiceResponse extends GeneratedMessageV3 implements ListServiceResponseOrBuilder {
    public static final int SERVICE_FIELD_NUMBER = 1;
    private static final ListServiceResponse DEFAULT_INSTANCE = new ListServiceResponse();
    private static final Parser<ListServiceResponse> PARSER = new AbstractParser<ListServiceResponse>() { // from class: io.grpc.reflection.v1alpha.ListServiceResponse.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ListServiceResponse m9811parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListServiceResponse(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<ServiceResponse> service_;

    private ListServiceResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListServiceResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.service_ = Collections.emptyList();
    }

    private ListServiceResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.service_ = new ArrayList();
                                z2 |= true;
                            }
                            this.service_.add(codedInputStream.readMessage(ServiceResponse.parser(), extensionRegistryLite));
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
                    this.service_ = Collections.unmodifiableList(this.service_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ListServiceResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListServiceResponse> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ListServiceResponse_descriptor;
    }

    public static ListServiceResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ListServiceResponse) PARSER.parseFrom(byteBuffer);
    }

    public static ListServiceResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListServiceResponse) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListServiceResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ListServiceResponse) PARSER.parseFrom(byteString);
    }

    public static ListServiceResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListServiceResponse) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListServiceResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ListServiceResponse) PARSER.parseFrom(bArr);
    }

    public static ListServiceResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListServiceResponse) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListServiceResponse parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListServiceResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListServiceResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListServiceResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListServiceResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListServiceResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m9809toBuilder();
    }

    public static Builder newBuilder(ListServiceResponse listServiceResponse) {
        return DEFAULT_INSTANCE.m9809toBuilder().mergeFrom(listServiceResponse);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ListServiceResponse m9804getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ListServiceResponse> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.reflection.v1alpha.ListServiceResponseOrBuilder
    public List<ServiceResponse> getServiceList() {
        return this.service_;
    }

    @Override // io.grpc.reflection.v1alpha.ListServiceResponseOrBuilder
    public List<? extends ServiceResponseOrBuilder> getServiceOrBuilderList() {
        return this.service_;
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
        return new ListServiceResponse();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ListServiceResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListServiceResponse.class, Builder.class);
    }

    @Override // io.grpc.reflection.v1alpha.ListServiceResponseOrBuilder
    public int getServiceCount() {
        return this.service_.size();
    }

    @Override // io.grpc.reflection.v1alpha.ListServiceResponseOrBuilder
    public ServiceResponse getService(int i) {
        return this.service_.get(i);
    }

    @Override // io.grpc.reflection.v1alpha.ListServiceResponseOrBuilder
    public ServiceResponseOrBuilder getServiceOrBuilder(int i) {
        return this.service_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.service_.size(); i++) {
            codedOutputStream.writeMessage(1, this.service_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.service_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.service_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListServiceResponse)) {
            return super.equals(obj);
        }
        ListServiceResponse listServiceResponse = (ListServiceResponse) obj;
        return getServiceList().equals(listServiceResponse.getServiceList()) && this.unknownFields.equals(listServiceResponse.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getServiceCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getServiceList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9806newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9809toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListServiceResponseOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> serviceBuilder_;
        private List<ServiceResponse> service_;

        private Builder() {
            this.service_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.service_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ListServiceResponse_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ListServiceResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListServiceResponse.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (ListServiceResponse.alwaysUseFieldBuilders) {
                getServiceFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9820clear() {
            super.clear();
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.service_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_ListServiceResponse_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ListServiceResponse m9833getDefaultInstanceForType() {
            return ListServiceResponse.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ListServiceResponse m9814build() throws UninitializedMessageException {
            ListServiceResponse listServiceResponseM9816buildPartial = m9816buildPartial();
            if (listServiceResponseM9816buildPartial.isInitialized()) {
                return listServiceResponseM9816buildPartial;
            }
            throw newUninitializedMessageException(listServiceResponseM9816buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ListServiceResponse m9816buildPartial() {
            ListServiceResponse listServiceResponse = new ListServiceResponse(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.service_ = Collections.unmodifiableList(this.service_);
                    this.bitField0_ &= -2;
                }
                listServiceResponse.service_ = this.service_;
            } else {
                listServiceResponse.service_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return listServiceResponse;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9832clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9844setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9822clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9825clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9846setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9812addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9837mergeFrom(Message message) {
            if (message instanceof ListServiceResponse) {
                return mergeFrom((ListServiceResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListServiceResponse listServiceResponse) {
            if (listServiceResponse == ListServiceResponse.getDefaultInstance()) {
                return this;
            }
            if (this.serviceBuilder_ == null) {
                if (!listServiceResponse.service_.isEmpty()) {
                    if (this.service_.isEmpty()) {
                        this.service_ = listServiceResponse.service_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureServiceIsMutable();
                        this.service_.addAll(listServiceResponse.service_);
                    }
                    onChanged();
                }
            } else if (!listServiceResponse.service_.isEmpty()) {
                if (!this.serviceBuilder_.isEmpty()) {
                    this.serviceBuilder_.addAllMessages(listServiceResponse.service_);
                } else {
                    this.serviceBuilder_.dispose();
                    this.serviceBuilder_ = null;
                    this.service_ = listServiceResponse.service_;
                    this.bitField0_ &= -2;
                    this.serviceBuilder_ = ListServiceResponse.alwaysUseFieldBuilders ? getServiceFieldBuilder() : null;
                }
            }
            m9842mergeUnknownFields(listServiceResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.reflection.v1alpha.ListServiceResponse.Builder m9838mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.reflection.v1alpha.ListServiceResponse.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.reflection.v1alpha.ListServiceResponse r3 = (io.grpc.reflection.v1alpha.ListServiceResponse) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.reflection.v1alpha.ListServiceResponse r4 = (io.grpc.reflection.v1alpha.ListServiceResponse) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.reflection.v1alpha.ListServiceResponse.Builder.m9838mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.reflection.v1alpha.ListServiceResponse$Builder");
        }

        private void ensureServiceIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.service_ = new ArrayList(this.service_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.reflection.v1alpha.ListServiceResponseOrBuilder
        public List<ServiceResponse> getServiceList() {
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.service_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.reflection.v1alpha.ListServiceResponseOrBuilder
        public int getServiceCount() {
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.service_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.reflection.v1alpha.ListServiceResponseOrBuilder
        public ServiceResponse getService(int i) {
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.service_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setService(int i, ServiceResponse serviceResponse) {
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                serviceResponse.getClass();
                ensureServiceIsMutable();
                this.service_.set(i, serviceResponse);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, serviceResponse);
            }
            return this;
        }

        public Builder setService(int i, ServiceResponse.Builder builder) {
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServiceIsMutable();
                this.service_.set(i, builder.m9952build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m9952build());
            }
            return this;
        }

        public Builder addService(ServiceResponse serviceResponse) {
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                serviceResponse.getClass();
                ensureServiceIsMutable();
                this.service_.add(serviceResponse);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(serviceResponse);
            }
            return this;
        }

        public Builder addService(int i, ServiceResponse serviceResponse) {
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                serviceResponse.getClass();
                ensureServiceIsMutable();
                this.service_.add(i, serviceResponse);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, serviceResponse);
            }
            return this;
        }

        public Builder addService(ServiceResponse.Builder builder) {
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServiceIsMutable();
                this.service_.add(builder.m9952build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m9952build());
            }
            return this;
        }

        public Builder addService(int i, ServiceResponse.Builder builder) {
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServiceIsMutable();
                this.service_.add(i, builder.m9952build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m9952build());
            }
            return this;
        }

        public Builder addAllService(Iterable<? extends ServiceResponse> iterable) {
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServiceIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.service_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearService() {
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.service_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeService(int i) {
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServiceIsMutable();
                this.service_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public ServiceResponse.Builder getServiceBuilder(int i) {
            return getServiceFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.reflection.v1alpha.ListServiceResponseOrBuilder
        public ServiceResponseOrBuilder getServiceOrBuilder(int i) {
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.service_.get(i);
            }
            return (ServiceResponseOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.reflection.v1alpha.ListServiceResponseOrBuilder
        public List<? extends ServiceResponseOrBuilder> getServiceOrBuilderList() {
            RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.service_);
        }

        public ServiceResponse.Builder addServiceBuilder() {
            return getServiceFieldBuilder().addBuilder(ServiceResponse.getDefaultInstance());
        }

        public ServiceResponse.Builder addServiceBuilder(int i) {
            return getServiceFieldBuilder().addBuilder(i, ServiceResponse.getDefaultInstance());
        }

        public List<ServiceResponse.Builder> getServiceBuilderList() {
            return getServiceFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<ServiceResponse, ServiceResponse.Builder, ServiceResponseOrBuilder> getServiceFieldBuilder() {
            if (this.serviceBuilder_ == null) {
                this.serviceBuilder_ = new RepeatedFieldBuilderV3<>(this.service_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.service_ = null;
            }
            return this.serviceBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9848setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9842mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
