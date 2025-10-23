package io.grpc.xds.shaded.com.google.security.meshca.v1;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class MeshCertificateRequest extends GeneratedMessageV3 implements MeshCertificateRequestOrBuilder {
    public static final int CSR_FIELD_NUMBER = 2;
    public static final int REQUEST_ID_FIELD_NUMBER = 1;
    public static final int VALIDITY_FIELD_NUMBER = 3;
    private static final MeshCertificateRequest DEFAULT_INSTANCE = new MeshCertificateRequest();
    private static final Parser<MeshCertificateRequest> PARSER = new AbstractParser<MeshCertificateRequest>() { // from class: io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequest.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public MeshCertificateRequest m11518parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MeshCertificateRequest(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private volatile Object csr_;
    private byte memoizedIsInitialized;
    private volatile Object requestId_;
    private Duration validity_;

    private MeshCertificateRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private MeshCertificateRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.requestId_ = "";
        this.csr_ = "";
    }

    private MeshCertificateRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.requestId_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            this.csr_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 26) {
                            Duration duration = this.validity_;
                            Duration.Builder builder = duration != null ? duration.toBuilder() : null;
                            Duration message = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.validity_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.validity_ = builder.buildPartial();
                            }
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
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static MeshCertificateRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MeshCertificateRequest> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MeshCaProto.internal_static_google_security_meshca_v1_MeshCertificateRequest_descriptor;
    }

    public static MeshCertificateRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MeshCertificateRequest) PARSER.parseFrom(byteBuffer);
    }

    public static MeshCertificateRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MeshCertificateRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MeshCertificateRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MeshCertificateRequest) PARSER.parseFrom(byteString);
    }

    public static MeshCertificateRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MeshCertificateRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MeshCertificateRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MeshCertificateRequest) PARSER.parseFrom(bArr);
    }

    public static MeshCertificateRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MeshCertificateRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MeshCertificateRequest parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MeshCertificateRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MeshCertificateRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MeshCertificateRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MeshCertificateRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MeshCertificateRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m11516toBuilder();
    }

    public static Builder newBuilder(MeshCertificateRequest meshCertificateRequest) {
        return DEFAULT_INSTANCE.m11516toBuilder().mergeFrom(meshCertificateRequest);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public MeshCertificateRequest m11511getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<MeshCertificateRequest> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequestOrBuilder
    public boolean hasValidity() {
        return this.validity_ != null;
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
        return new MeshCertificateRequest();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MeshCaProto.internal_static_google_security_meshca_v1_MeshCertificateRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(MeshCertificateRequest.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequestOrBuilder
    public String getRequestId() {
        Object obj = this.requestId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.requestId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequestOrBuilder
    public ByteString getRequestIdBytes() {
        Object obj = this.requestId_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.requestId_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequestOrBuilder
    public String getCsr() {
        Object obj = this.csr_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.csr_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequestOrBuilder
    public ByteString getCsrBytes() {
        Object obj = this.csr_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.csr_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequestOrBuilder
    public Duration getValidity() {
        Duration duration = this.validity_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequestOrBuilder
    public DurationOrBuilder getValidityOrBuilder() {
        return getValidity();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getRequestIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.requestId_);
        }
        if (!getCsrBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.csr_);
        }
        if (this.validity_ != null) {
            codedOutputStream.writeMessage(3, getValidity());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getRequestIdBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.requestId_) : 0;
        if (!getCsrBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.csr_);
        }
        if (this.validity_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, getValidity());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MeshCertificateRequest)) {
            return super.equals(obj);
        }
        MeshCertificateRequest meshCertificateRequest = (MeshCertificateRequest) obj;
        if (getRequestId().equals(meshCertificateRequest.getRequestId()) && getCsr().equals(meshCertificateRequest.getCsr()) && hasValidity() == meshCertificateRequest.hasValidity()) {
            return (!hasValidity() || getValidity().equals(meshCertificateRequest.getValidity())) && this.unknownFields.equals(meshCertificateRequest.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getRequestId().hashCode()) * 37) + 2) * 53) + getCsr().hashCode();
        if (hasValidity()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getValidity().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m11513newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m11516toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MeshCertificateRequestOrBuilder {
        private Object csr_;
        private Object requestId_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> validityBuilder_;
        private Duration validity_;

        private Builder() {
            this.requestId_ = "";
            this.csr_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.requestId_ = "";
            this.csr_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MeshCaProto.internal_static_google_security_meshca_v1_MeshCertificateRequest_descriptor;
        }

        @Override // io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequestOrBuilder
        public boolean hasValidity() {
            return (this.validityBuilder_ == null && this.validity_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MeshCaProto.internal_static_google_security_meshca_v1_MeshCertificateRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(MeshCertificateRequest.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = MeshCertificateRequest.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11527clear() {
            super.clear();
            this.requestId_ = "";
            this.csr_ = "";
            if (this.validityBuilder_ == null) {
                this.validity_ = null;
            } else {
                this.validity_ = null;
                this.validityBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return MeshCaProto.internal_static_google_security_meshca_v1_MeshCertificateRequest_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MeshCertificateRequest m11540getDefaultInstanceForType() {
            return MeshCertificateRequest.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MeshCertificateRequest m11521build() throws UninitializedMessageException {
            MeshCertificateRequest meshCertificateRequestM11523buildPartial = m11523buildPartial();
            if (meshCertificateRequestM11523buildPartial.isInitialized()) {
                return meshCertificateRequestM11523buildPartial;
            }
            throw newUninitializedMessageException(meshCertificateRequestM11523buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MeshCertificateRequest m11523buildPartial() {
            MeshCertificateRequest meshCertificateRequest = new MeshCertificateRequest(this);
            meshCertificateRequest.requestId_ = this.requestId_;
            meshCertificateRequest.csr_ = this.csr_;
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.validityBuilder_;
            if (singleFieldBuilderV3 == null) {
                meshCertificateRequest.validity_ = this.validity_;
            } else {
                meshCertificateRequest.validity_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return meshCertificateRequest;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11539clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11551setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11529clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11532clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11553setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11519addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11544mergeFrom(Message message) {
            if (message instanceof MeshCertificateRequest) {
                return mergeFrom((MeshCertificateRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MeshCertificateRequest meshCertificateRequest) {
            if (meshCertificateRequest == MeshCertificateRequest.getDefaultInstance()) {
                return this;
            }
            if (!meshCertificateRequest.getRequestId().isEmpty()) {
                this.requestId_ = meshCertificateRequest.requestId_;
                onChanged();
            }
            if (!meshCertificateRequest.getCsr().isEmpty()) {
                this.csr_ = meshCertificateRequest.csr_;
                onChanged();
            }
            if (meshCertificateRequest.hasValidity()) {
                mergeValidity(meshCertificateRequest.getValidity());
            }
            m11549mergeUnknownFields(meshCertificateRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequest.Builder m11545mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequest.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequest r3 = (io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequest) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequest r4 = (io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequest) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequest.Builder.m11545mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequest$Builder");
        }

        @Override // io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequestOrBuilder
        public String getRequestId() {
            Object obj = this.requestId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.requestId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setRequestId(String str) {
            str.getClass();
            this.requestId_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequestOrBuilder
        public ByteString getRequestIdBytes() {
            Object obj = this.requestId_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.requestId_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRequestIdBytes(ByteString byteString) {
            byteString.getClass();
            MeshCertificateRequest.checkByteStringIsUtf8(byteString);
            this.requestId_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRequestId() {
            this.requestId_ = MeshCertificateRequest.getDefaultInstance().getRequestId();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequestOrBuilder
        public String getCsr() {
            Object obj = this.csr_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.csr_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setCsr(String str) {
            str.getClass();
            this.csr_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequestOrBuilder
        public ByteString getCsrBytes() {
            Object obj = this.csr_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.csr_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setCsrBytes(ByteString byteString) {
            byteString.getClass();
            MeshCertificateRequest.checkByteStringIsUtf8(byteString);
            this.csr_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearCsr() {
            this.csr_ = MeshCertificateRequest.getDefaultInstance().getCsr();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequestOrBuilder
        public Duration getValidity() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.validityBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.validity_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setValidity(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.validityBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.validity_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setValidity(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.validityBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.validity_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeValidity(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.validityBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.validity_;
                if (duration2 != null) {
                    this.validity_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.validity_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearValidity() {
            if (this.validityBuilder_ == null) {
                this.validity_ = null;
                onChanged();
            } else {
                this.validity_ = null;
                this.validityBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getValidityBuilder() {
            onChanged();
            return getValidityFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.com.google.security.meshca.v1.MeshCertificateRequestOrBuilder
        public DurationOrBuilder getValidityOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.validityBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.validity_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getValidityFieldBuilder() {
            if (this.validityBuilder_ == null) {
                this.validityBuilder_ = new SingleFieldBuilderV3<>(getValidity(), getParentForChildren(), isClean());
                this.validity_ = null;
            }
            return this.validityBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m11555setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m11549mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
