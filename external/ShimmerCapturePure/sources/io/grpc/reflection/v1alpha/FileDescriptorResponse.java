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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class FileDescriptorResponse extends GeneratedMessageV3 implements FileDescriptorResponseOrBuilder {
    public static final int FILE_DESCRIPTOR_PROTO_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final FileDescriptorResponse DEFAULT_INSTANCE = new FileDescriptorResponse();
    private static final Parser<FileDescriptorResponse> PARSER = new AbstractParser<FileDescriptorResponse>() { // from class: io.grpc.reflection.v1alpha.FileDescriptorResponse.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public FileDescriptorResponse m9765parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new FileDescriptorResponse(codedInputStream, extensionRegistryLite);
        }
    };
    private List<ByteString> fileDescriptorProto_;
    private byte memoizedIsInitialized;

    private FileDescriptorResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private FileDescriptorResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.fileDescriptorProto_ = Collections.emptyList();
    }

    private FileDescriptorResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.fileDescriptorProto_ = new ArrayList();
                                z2 |= true;
                            }
                            this.fileDescriptorProto_.add(codedInputStream.readBytes());
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
                if (z2 & true) {
                    this.fileDescriptorProto_ = Collections.unmodifiableList(this.fileDescriptorProto_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static FileDescriptorResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FileDescriptorResponse> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_FileDescriptorResponse_descriptor;
    }

    public static FileDescriptorResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (FileDescriptorResponse) PARSER.parseFrom(byteBuffer);
    }

    public static FileDescriptorResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FileDescriptorResponse) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static FileDescriptorResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (FileDescriptorResponse) PARSER.parseFrom(byteString);
    }

    public static FileDescriptorResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FileDescriptorResponse) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static FileDescriptorResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (FileDescriptorResponse) PARSER.parseFrom(bArr);
    }

    public static FileDescriptorResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FileDescriptorResponse) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static FileDescriptorResponse parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static FileDescriptorResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static FileDescriptorResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static FileDescriptorResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static FileDescriptorResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static FileDescriptorResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m9763toBuilder();
    }

    public static Builder newBuilder(FileDescriptorResponse fileDescriptorResponse) {
        return DEFAULT_INSTANCE.m9763toBuilder().mergeFrom(fileDescriptorResponse);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public FileDescriptorResponse m9758getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.reflection.v1alpha.FileDescriptorResponseOrBuilder
    public List<ByteString> getFileDescriptorProtoList() {
        return this.fileDescriptorProto_;
    }

    public Parser<FileDescriptorResponse> getParserForType() {
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
        return new FileDescriptorResponse();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_FileDescriptorResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(FileDescriptorResponse.class, Builder.class);
    }

    @Override // io.grpc.reflection.v1alpha.FileDescriptorResponseOrBuilder
    public int getFileDescriptorProtoCount() {
        return this.fileDescriptorProto_.size();
    }

    @Override // io.grpc.reflection.v1alpha.FileDescriptorResponseOrBuilder
    public ByteString getFileDescriptorProto(int i) {
        return this.fileDescriptorProto_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.fileDescriptorProto_.size(); i++) {
            codedOutputStream.writeBytes(1, this.fileDescriptorProto_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeBytesSizeNoTag = 0;
        for (int i2 = 0; i2 < this.fileDescriptorProto_.size(); i2++) {
            iComputeBytesSizeNoTag += CodedOutputStream.computeBytesSizeNoTag(this.fileDescriptorProto_.get(i2));
        }
        int size = iComputeBytesSizeNoTag + getFileDescriptorProtoList().size() + this.unknownFields.getSerializedSize();
        this.memoizedSize = size;
        return size;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FileDescriptorResponse)) {
            return super.equals(obj);
        }
        FileDescriptorResponse fileDescriptorResponse = (FileDescriptorResponse) obj;
        return getFileDescriptorProtoList().equals(fileDescriptorResponse.getFileDescriptorProtoList()) && this.unknownFields.equals(fileDescriptorResponse.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getFileDescriptorProtoCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getFileDescriptorProtoList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9760newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m9763toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FileDescriptorResponseOrBuilder {
        private int bitField0_;
        private List<ByteString> fileDescriptorProto_;

        private Builder() {
            this.fileDescriptorProto_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.fileDescriptorProto_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_FileDescriptorResponse_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_FileDescriptorResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(FileDescriptorResponse.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = FileDescriptorResponse.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9774clear() {
            super.clear();
            this.fileDescriptorProto_ = Collections.emptyList();
            this.bitField0_ &= -2;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ServerReflectionProto.internal_static_grpc_reflection_v1alpha_FileDescriptorResponse_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FileDescriptorResponse m9787getDefaultInstanceForType() {
            return FileDescriptorResponse.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FileDescriptorResponse m9768build() throws UninitializedMessageException {
            FileDescriptorResponse fileDescriptorResponseM9770buildPartial = m9770buildPartial();
            if (fileDescriptorResponseM9770buildPartial.isInitialized()) {
                return fileDescriptorResponseM9770buildPartial;
            }
            throw newUninitializedMessageException(fileDescriptorResponseM9770buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FileDescriptorResponse m9770buildPartial() {
            FileDescriptorResponse fileDescriptorResponse = new FileDescriptorResponse(this);
            if ((this.bitField0_ & 1) != 0) {
                this.fileDescriptorProto_ = Collections.unmodifiableList(this.fileDescriptorProto_);
                this.bitField0_ &= -2;
            }
            fileDescriptorResponse.fileDescriptorProto_ = this.fileDescriptorProto_;
            onBuilt();
            return fileDescriptorResponse;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9786clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9798setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9776clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9779clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9800setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9766addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m9791mergeFrom(Message message) {
            if (message instanceof FileDescriptorResponse) {
                return mergeFrom((FileDescriptorResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(FileDescriptorResponse fileDescriptorResponse) {
            if (fileDescriptorResponse == FileDescriptorResponse.getDefaultInstance()) {
                return this;
            }
            if (!fileDescriptorResponse.fileDescriptorProto_.isEmpty()) {
                if (this.fileDescriptorProto_.isEmpty()) {
                    this.fileDescriptorProto_ = fileDescriptorResponse.fileDescriptorProto_;
                    this.bitField0_ &= -2;
                } else {
                    ensureFileDescriptorProtoIsMutable();
                    this.fileDescriptorProto_.addAll(fileDescriptorResponse.fileDescriptorProto_);
                }
                onChanged();
            }
            m9796mergeUnknownFields(fileDescriptorResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.reflection.v1alpha.FileDescriptorResponse.Builder m9792mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.reflection.v1alpha.FileDescriptorResponse.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.reflection.v1alpha.FileDescriptorResponse r3 = (io.grpc.reflection.v1alpha.FileDescriptorResponse) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.reflection.v1alpha.FileDescriptorResponse r4 = (io.grpc.reflection.v1alpha.FileDescriptorResponse) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.reflection.v1alpha.FileDescriptorResponse.Builder.m9792mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.reflection.v1alpha.FileDescriptorResponse$Builder");
        }

        private void ensureFileDescriptorProtoIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.fileDescriptorProto_ = new ArrayList(this.fileDescriptorProto_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.reflection.v1alpha.FileDescriptorResponseOrBuilder
        public List<ByteString> getFileDescriptorProtoList() {
            return (this.bitField0_ & 1) != 0 ? Collections.unmodifiableList(this.fileDescriptorProto_) : this.fileDescriptorProto_;
        }

        @Override // io.grpc.reflection.v1alpha.FileDescriptorResponseOrBuilder
        public int getFileDescriptorProtoCount() {
            return this.fileDescriptorProto_.size();
        }

        @Override // io.grpc.reflection.v1alpha.FileDescriptorResponseOrBuilder
        public ByteString getFileDescriptorProto(int i) {
            return this.fileDescriptorProto_.get(i);
        }

        public Builder setFileDescriptorProto(int i, ByteString byteString) {
            byteString.getClass();
            ensureFileDescriptorProtoIsMutable();
            this.fileDescriptorProto_.set(i, byteString);
            onChanged();
            return this;
        }

        public Builder addFileDescriptorProto(ByteString byteString) {
            byteString.getClass();
            ensureFileDescriptorProtoIsMutable();
            this.fileDescriptorProto_.add(byteString);
            onChanged();
            return this;
        }

        public Builder addAllFileDescriptorProto(Iterable<? extends ByteString> iterable) {
            ensureFileDescriptorProtoIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.fileDescriptorProto_);
            onChanged();
            return this;
        }

        public Builder clearFileDescriptorProto() {
            this.fileDescriptorProto_ = Collections.emptyList();
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9802setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m9796mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
