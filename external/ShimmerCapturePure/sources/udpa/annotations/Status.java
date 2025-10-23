package udpa.annotations;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class Status {
    public static final int FILE_STATUS_FIELD_NUMBER = 222707719;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.FileOptions, StatusAnnotation> fileStatus;
    private static final Descriptors.Descriptor internal_static_udpa_annotations_StatusAnnotation_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_udpa_annotations_StatusAnnotation_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    static {
        GeneratedMessage.GeneratedExtension<DescriptorProtos.FileOptions, StatusAnnotation> generatedExtensionNewFileScopedGeneratedExtension = GeneratedMessage.newFileScopedGeneratedExtension(StatusAnnotation.class, StatusAnnotation.getDefaultInstance());
        fileStatus = generatedExtensionNewFileScopedGeneratedExtension;
        descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001dudpa/annotations/status.proto\u0012\u0010udpa.annotations\u001a google/protobuf/descriptor.proto\"t\n\u0010StatusAnnotation\u0012\u0018\n\u0010work_in_progress\u0018\u0001 \u0001(\b\u0012F\n\u0016package_version_status\u0018\u0002 \u0001(\u000e2&.udpa.annotations.PackageVersionStatus*]\n\u0014PackageVersionStatus\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\n\n\u0006FROZEN\u0010\u0001\u0012\n\n\u0006ACTIVE\u0010\u0002\u0012 \n\u001cNEXT_MAJOR_VERSION_CANDIDATE\u0010\u0003:X\n\u000bfile_status\u0012\u001c.google.protobuf.FileOptions\u0018\u0087\u0080\u0099j \u0001(\u000b2\".udpa.annotations.StatusAnnotationb\u0006proto3"}, new Descriptors.FileDescriptor[]{DescriptorProtos.getDescriptor()});
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_udpa_annotations_StatusAnnotation_descriptor = descriptor2;
        internal_static_udpa_annotations_StatusAnnotation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"WorkInProgress", "PackageVersionStatus"});
        generatedExtensionNewFileScopedGeneratedExtension.internalInit((Descriptors.FieldDescriptor) descriptor.getExtensions().get(0));
        DescriptorProtos.getDescriptor();
    }

    private Status() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add(fileStatus);
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public enum PackageVersionStatus implements ProtocolMessageEnum {
        UNKNOWN(0),
        FROZEN(1),
        ACTIVE(2),
        NEXT_MAJOR_VERSION_CANDIDATE(3),
        UNRECOGNIZED(-1);

        public static final int ACTIVE_VALUE = 2;
        public static final int FROZEN_VALUE = 1;
        public static final int NEXT_MAJOR_VERSION_CANDIDATE_VALUE = 3;
        public static final int UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<PackageVersionStatus> internalValueMap = new Internal.EnumLiteMap<PackageVersionStatus>() { // from class: udpa.annotations.Status.PackageVersionStatus.1
            public PackageVersionStatus findValueByNumber(int i) {
                return PackageVersionStatus.forNumber(i);
            }
        };
        private static final PackageVersionStatus[] VALUES = values();
        private final int value;

        PackageVersionStatus(int i) {
            this.value = i;
        }

        public static PackageVersionStatus forNumber(int i) {
            if (i == 0) {
                return UNKNOWN;
            }
            if (i == 1) {
                return FROZEN;
            }
            if (i == 2) {
                return ACTIVE;
            }
            if (i != 3) {
                return null;
            }
            return NEXT_MAJOR_VERSION_CANDIDATE;
        }

        public static Internal.EnumLiteMap<PackageVersionStatus> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static PackageVersionStatus valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) Status.getDescriptor().getEnumTypes().get(0);
        }

        public static PackageVersionStatus valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
    }

    public interface StatusAnnotationOrBuilder extends MessageOrBuilder {
        PackageVersionStatus getPackageVersionStatus();

        int getPackageVersionStatusValue();

        boolean getWorkInProgress();
    }

    public static final class StatusAnnotation extends GeneratedMessageV3 implements StatusAnnotationOrBuilder {
        public static final int PACKAGE_VERSION_STATUS_FIELD_NUMBER = 2;
        public static final int WORK_IN_PROGRESS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final StatusAnnotation DEFAULT_INSTANCE = new StatusAnnotation();
        private static final Parser<StatusAnnotation> PARSER = new AbstractParser<StatusAnnotation>() { // from class: udpa.annotations.Status.StatusAnnotation.1
            public StatusAnnotation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new StatusAnnotation(codedInputStream, extensionRegistryLite);
            }
        };
        private byte memoizedIsInitialized;
        private int packageVersionStatus_;
        private boolean workInProgress_;

        private StatusAnnotation(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private StatusAnnotation() {
            this.memoizedIsInitialized = (byte) -1;
            this.packageVersionStatus_ = 0;
        }

        private StatusAnnotation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 8) {
                                    this.workInProgress_ = codedInputStream.readBool();
                                } else if (tag == 16) {
                                    this.packageVersionStatus_ = codedInputStream.readEnum();
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
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static StatusAnnotation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StatusAnnotation> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Status.internal_static_udpa_annotations_StatusAnnotation_descriptor;
        }

        public static StatusAnnotation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (StatusAnnotation) PARSER.parseFrom(byteBuffer);
        }

        public static StatusAnnotation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StatusAnnotation) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static StatusAnnotation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StatusAnnotation) PARSER.parseFrom(byteString);
        }

        public static StatusAnnotation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StatusAnnotation) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static StatusAnnotation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StatusAnnotation) PARSER.parseFrom(bArr);
        }

        public static StatusAnnotation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StatusAnnotation) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static StatusAnnotation parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static StatusAnnotation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static StatusAnnotation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static StatusAnnotation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static StatusAnnotation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static StatusAnnotation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m40638toBuilder();
        }

        public static Builder newBuilder(StatusAnnotation statusAnnotation) {
            return DEFAULT_INSTANCE.m40638toBuilder().mergeFrom(statusAnnotation);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public StatusAnnotation m40633getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // udpa.annotations.Status.StatusAnnotationOrBuilder
        public int getPackageVersionStatusValue() {
            return this.packageVersionStatus_;
        }

        public Parser<StatusAnnotation> getParserForType() {
            return PARSER;
        }

        @Override // udpa.annotations.Status.StatusAnnotationOrBuilder
        public boolean getWorkInProgress() {
            return this.workInProgress_;
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
            return new StatusAnnotation();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Status.internal_static_udpa_annotations_StatusAnnotation_fieldAccessorTable.ensureFieldAccessorsInitialized(StatusAnnotation.class, Builder.class);
        }

        @Override // udpa.annotations.Status.StatusAnnotationOrBuilder
        public PackageVersionStatus getPackageVersionStatus() {
            PackageVersionStatus packageVersionStatusValueOf = PackageVersionStatus.valueOf(this.packageVersionStatus_);
            return packageVersionStatusValueOf == null ? PackageVersionStatus.UNRECOGNIZED : packageVersionStatusValueOf;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.workInProgress_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            if (this.packageVersionStatus_ != PackageVersionStatus.UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(2, this.packageVersionStatus_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            boolean z = this.workInProgress_;
            int iComputeBoolSize = z ? CodedOutputStream.computeBoolSize(1, z) : 0;
            if (this.packageVersionStatus_ != PackageVersionStatus.UNKNOWN.getNumber()) {
                iComputeBoolSize += CodedOutputStream.computeEnumSize(2, this.packageVersionStatus_);
            }
            int serializedSize = iComputeBoolSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof StatusAnnotation)) {
                return super.equals(obj);
            }
            StatusAnnotation statusAnnotation = (StatusAnnotation) obj;
            return getWorkInProgress() == statusAnnotation.getWorkInProgress() && this.packageVersionStatus_ == statusAnnotation.packageVersionStatus_ && this.unknownFields.equals(statusAnnotation.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getWorkInProgress())) * 37) + 2) * 53) + this.packageVersionStatus_) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m40635newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m40638toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StatusAnnotationOrBuilder {
            private int packageVersionStatus_;
            private boolean workInProgress_;

            private Builder() {
                this.packageVersionStatus_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.packageVersionStatus_ = 0;
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Status.internal_static_udpa_annotations_StatusAnnotation_descriptor;
            }

            @Override // udpa.annotations.Status.StatusAnnotationOrBuilder
            public int getPackageVersionStatusValue() {
                return this.packageVersionStatus_;
            }

            public Builder setPackageVersionStatusValue(int i) {
                this.packageVersionStatus_ = i;
                onChanged();
                return this;
            }

            @Override // udpa.annotations.Status.StatusAnnotationOrBuilder
            public boolean getWorkInProgress() {
                return this.workInProgress_;
            }

            public Builder setWorkInProgress(boolean z) {
                this.workInProgress_ = z;
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Status.internal_static_udpa_annotations_StatusAnnotation_fieldAccessorTable.ensureFieldAccessorsInitialized(StatusAnnotation.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = StatusAnnotation.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40649clear() {
                super.clear();
                this.workInProgress_ = false;
                this.packageVersionStatus_ = 0;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return Status.internal_static_udpa_annotations_StatusAnnotation_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public StatusAnnotation m40662getDefaultInstanceForType() {
                return StatusAnnotation.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public StatusAnnotation m40643build() throws UninitializedMessageException {
                StatusAnnotation statusAnnotationM40645buildPartial = m40645buildPartial();
                if (statusAnnotationM40645buildPartial.isInitialized()) {
                    return statusAnnotationM40645buildPartial;
                }
                throw newUninitializedMessageException(statusAnnotationM40645buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public StatusAnnotation m40645buildPartial() {
                StatusAnnotation statusAnnotation = new StatusAnnotation(this);
                statusAnnotation.workInProgress_ = this.workInProgress_;
                statusAnnotation.packageVersionStatus_ = this.packageVersionStatus_;
                onBuilt();
                return statusAnnotation;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40660clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40673setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40651clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40654clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40675setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40641addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40666mergeFrom(Message message) {
                if (message instanceof StatusAnnotation) {
                    return mergeFrom((StatusAnnotation) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(StatusAnnotation statusAnnotation) {
                if (statusAnnotation == StatusAnnotation.getDefaultInstance()) {
                    return this;
                }
                if (statusAnnotation.getWorkInProgress()) {
                    setWorkInProgress(statusAnnotation.getWorkInProgress());
                }
                if (statusAnnotation.packageVersionStatus_ != 0) {
                    setPackageVersionStatusValue(statusAnnotation.getPackageVersionStatusValue());
                }
                m40671mergeUnknownFields(statusAnnotation.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public udpa.annotations.Status.StatusAnnotation.Builder m40667mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = udpa.annotations.Status.StatusAnnotation.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    udpa.annotations.Status$StatusAnnotation r3 = (udpa.annotations.Status.StatusAnnotation) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    udpa.annotations.Status$StatusAnnotation r4 = (udpa.annotations.Status.StatusAnnotation) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: udpa.annotations.Status.StatusAnnotation.Builder.m40667mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):udpa.annotations.Status$StatusAnnotation$Builder");
            }

            public Builder clearWorkInProgress() {
                this.workInProgress_ = false;
                onChanged();
                return this;
            }

            @Override // udpa.annotations.Status.StatusAnnotationOrBuilder
            public PackageVersionStatus getPackageVersionStatus() {
                PackageVersionStatus packageVersionStatusValueOf = PackageVersionStatus.valueOf(this.packageVersionStatus_);
                return packageVersionStatusValueOf == null ? PackageVersionStatus.UNRECOGNIZED : packageVersionStatusValueOf;
            }

            public Builder setPackageVersionStatus(PackageVersionStatus packageVersionStatus) {
                packageVersionStatus.getClass();
                this.packageVersionStatus_ = packageVersionStatus.getNumber();
                onChanged();
                return this;
            }

            public Builder clearPackageVersionStatus() {
                this.packageVersionStatus_ = 0;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m40677setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m40671mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }
}
