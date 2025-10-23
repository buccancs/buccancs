package udpa.annotations;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.AnyProto;
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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class Security {
    public static final int SECURITY_FIELD_NUMBER = 11122993;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.FieldOptions, FieldSecurityAnnotation> security;
    private static final Descriptors.Descriptor internal_static_udpa_annotations_FieldSecurityAnnotation_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_udpa_annotations_FieldSecurityAnnotation_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    static {
        GeneratedMessage.GeneratedExtension<DescriptorProtos.FieldOptions, FieldSecurityAnnotation> generatedExtensionNewFileScopedGeneratedExtension = GeneratedMessage.newFileScopedGeneratedExtension(FieldSecurityAnnotation.class, FieldSecurityAnnotation.getDefaultInstance());
        security = generatedExtensionNewFileScopedGeneratedExtension;
        descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001fudpa/annotations/security.proto\u0012\u0010udpa.annotations\u001a\u001dudpa/annotations/status.proto\u001a\u0019google/protobuf/any.proto\u001a google/protobuf/descriptor.proto\u001a\u0017validate/validate.proto\"o\n\u0017FieldSecurityAnnotation\u0012*\n\"configure_for_untrusted_downstream\u0018\u0001 \u0001(\b\u0012(\n configure_for_untrusted_upstream\u0018\u0002 \u0001(\b:]\n\bsecurity\u0012\u001d.google.protobuf.FieldOptions\u0018±ò¦\u0005 \u0001(\u000b2).udpa.annotations.FieldSecurityAnnotationB\bº\u0080ÈÑ\u0006\u0002\b\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{Status.getDescriptor(), AnyProto.getDescriptor(), DescriptorProtos.getDescriptor(), Validate.getDescriptor()});
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_udpa_annotations_FieldSecurityAnnotation_descriptor = descriptor2;
        internal_static_udpa_annotations_FieldSecurityAnnotation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"ConfigureForUntrustedDownstream", "ConfigureForUntrustedUpstream"});
        generatedExtensionNewFileScopedGeneratedExtension.internalInit((Descriptors.FieldDescriptor) descriptor.getExtensions().get(0));
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        Status.getDescriptor();
        AnyProto.getDescriptor();
        DescriptorProtos.getDescriptor();
        Validate.getDescriptor();
    }

    private Security() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add(security);
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public interface FieldSecurityAnnotationOrBuilder extends MessageOrBuilder {
        boolean getConfigureForUntrustedDownstream();

        boolean getConfigureForUntrustedUpstream();
    }

    public static final class FieldSecurityAnnotation extends GeneratedMessageV3 implements FieldSecurityAnnotationOrBuilder {
        public static final int CONFIGURE_FOR_UNTRUSTED_DOWNSTREAM_FIELD_NUMBER = 1;
        public static final int CONFIGURE_FOR_UNTRUSTED_UPSTREAM_FIELD_NUMBER = 2;
        private static final FieldSecurityAnnotation DEFAULT_INSTANCE = new FieldSecurityAnnotation();
        private static final Parser<FieldSecurityAnnotation> PARSER = new AbstractParser<FieldSecurityAnnotation>() { // from class: udpa.annotations.Security.FieldSecurityAnnotation.1
            public FieldSecurityAnnotation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new FieldSecurityAnnotation(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private boolean configureForUntrustedDownstream_;
        private boolean configureForUntrustedUpstream_;
        private byte memoizedIsInitialized;

        private FieldSecurityAnnotation(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private FieldSecurityAnnotation() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private FieldSecurityAnnotation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.configureForUntrustedDownstream_ = codedInputStream.readBool();
                                } else if (tag == 16) {
                                    this.configureForUntrustedUpstream_ = codedInputStream.readBool();
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

        public static FieldSecurityAnnotation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldSecurityAnnotation> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Security.internal_static_udpa_annotations_FieldSecurityAnnotation_descriptor;
        }

        public static FieldSecurityAnnotation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FieldSecurityAnnotation) PARSER.parseFrom(byteBuffer);
        }

        public static FieldSecurityAnnotation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldSecurityAnnotation) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static FieldSecurityAnnotation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FieldSecurityAnnotation) PARSER.parseFrom(byteString);
        }

        public static FieldSecurityAnnotation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldSecurityAnnotation) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static FieldSecurityAnnotation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FieldSecurityAnnotation) PARSER.parseFrom(bArr);
        }

        public static FieldSecurityAnnotation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldSecurityAnnotation) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static FieldSecurityAnnotation parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static FieldSecurityAnnotation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FieldSecurityAnnotation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static FieldSecurityAnnotation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FieldSecurityAnnotation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static FieldSecurityAnnotation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m40591toBuilder();
        }

        public static Builder newBuilder(FieldSecurityAnnotation fieldSecurityAnnotation) {
            return DEFAULT_INSTANCE.m40591toBuilder().mergeFrom(fieldSecurityAnnotation);
        }

        @Override // udpa.annotations.Security.FieldSecurityAnnotationOrBuilder
        public boolean getConfigureForUntrustedDownstream() {
            return this.configureForUntrustedDownstream_;
        }

        @Override // udpa.annotations.Security.FieldSecurityAnnotationOrBuilder
        public boolean getConfigureForUntrustedUpstream() {
            return this.configureForUntrustedUpstream_;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FieldSecurityAnnotation m40586getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<FieldSecurityAnnotation> getParserForType() {
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
            return new FieldSecurityAnnotation();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Security.internal_static_udpa_annotations_FieldSecurityAnnotation_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldSecurityAnnotation.class, Builder.class);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.configureForUntrustedDownstream_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            boolean z2 = this.configureForUntrustedUpstream_;
            if (z2) {
                codedOutputStream.writeBool(2, z2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            boolean z = this.configureForUntrustedDownstream_;
            int iComputeBoolSize = z ? CodedOutputStream.computeBoolSize(1, z) : 0;
            boolean z2 = this.configureForUntrustedUpstream_;
            if (z2) {
                iComputeBoolSize += CodedOutputStream.computeBoolSize(2, z2);
            }
            int serializedSize = iComputeBoolSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FieldSecurityAnnotation)) {
                return super.equals(obj);
            }
            FieldSecurityAnnotation fieldSecurityAnnotation = (FieldSecurityAnnotation) obj;
            return getConfigureForUntrustedDownstream() == fieldSecurityAnnotation.getConfigureForUntrustedDownstream() && getConfigureForUntrustedUpstream() == fieldSecurityAnnotation.getConfigureForUntrustedUpstream() && this.unknownFields.equals(fieldSecurityAnnotation.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getConfigureForUntrustedDownstream())) * 37) + 2) * 53) + Internal.hashBoolean(getConfigureForUntrustedUpstream())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m40588newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m40591toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FieldSecurityAnnotationOrBuilder {
            private boolean configureForUntrustedDownstream_;
            private boolean configureForUntrustedUpstream_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Security.internal_static_udpa_annotations_FieldSecurityAnnotation_descriptor;
            }

            @Override // udpa.annotations.Security.FieldSecurityAnnotationOrBuilder
            public boolean getConfigureForUntrustedDownstream() {
                return this.configureForUntrustedDownstream_;
            }

            public Builder setConfigureForUntrustedDownstream(boolean z) {
                this.configureForUntrustedDownstream_ = z;
                onChanged();
                return this;
            }

            @Override // udpa.annotations.Security.FieldSecurityAnnotationOrBuilder
            public boolean getConfigureForUntrustedUpstream() {
                return this.configureForUntrustedUpstream_;
            }

            public Builder setConfigureForUntrustedUpstream(boolean z) {
                this.configureForUntrustedUpstream_ = z;
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Security.internal_static_udpa_annotations_FieldSecurityAnnotation_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldSecurityAnnotation.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = FieldSecurityAnnotation.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40602clear() {
                super.clear();
                this.configureForUntrustedDownstream_ = false;
                this.configureForUntrustedUpstream_ = false;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return Security.internal_static_udpa_annotations_FieldSecurityAnnotation_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public FieldSecurityAnnotation m40615getDefaultInstanceForType() {
                return FieldSecurityAnnotation.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public FieldSecurityAnnotation m40596build() throws UninitializedMessageException {
                FieldSecurityAnnotation fieldSecurityAnnotationM40598buildPartial = m40598buildPartial();
                if (fieldSecurityAnnotationM40598buildPartial.isInitialized()) {
                    return fieldSecurityAnnotationM40598buildPartial;
                }
                throw newUninitializedMessageException(fieldSecurityAnnotationM40598buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public FieldSecurityAnnotation m40598buildPartial() {
                FieldSecurityAnnotation fieldSecurityAnnotation = new FieldSecurityAnnotation(this);
                fieldSecurityAnnotation.configureForUntrustedDownstream_ = this.configureForUntrustedDownstream_;
                fieldSecurityAnnotation.configureForUntrustedUpstream_ = this.configureForUntrustedUpstream_;
                onBuilt();
                return fieldSecurityAnnotation;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40613clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40626setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40604clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40607clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40628setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40594addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m40619mergeFrom(Message message) {
                if (message instanceof FieldSecurityAnnotation) {
                    return mergeFrom((FieldSecurityAnnotation) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(FieldSecurityAnnotation fieldSecurityAnnotation) {
                if (fieldSecurityAnnotation == FieldSecurityAnnotation.getDefaultInstance()) {
                    return this;
                }
                if (fieldSecurityAnnotation.getConfigureForUntrustedDownstream()) {
                    setConfigureForUntrustedDownstream(fieldSecurityAnnotation.getConfigureForUntrustedDownstream());
                }
                if (fieldSecurityAnnotation.getConfigureForUntrustedUpstream()) {
                    setConfigureForUntrustedUpstream(fieldSecurityAnnotation.getConfigureForUntrustedUpstream());
                }
                m40624mergeUnknownFields(fieldSecurityAnnotation.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public udpa.annotations.Security.FieldSecurityAnnotation.Builder m40620mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = udpa.annotations.Security.FieldSecurityAnnotation.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    udpa.annotations.Security$FieldSecurityAnnotation r3 = (udpa.annotations.Security.FieldSecurityAnnotation) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    udpa.annotations.Security$FieldSecurityAnnotation r4 = (udpa.annotations.Security.FieldSecurityAnnotation) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: udpa.annotations.Security.FieldSecurityAnnotation.Builder.m40620mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):udpa.annotations.Security$FieldSecurityAnnotation$Builder");
            }

            public Builder clearConfigureForUntrustedDownstream() {
                this.configureForUntrustedDownstream_ = false;
                onChanged();
                return this;
            }

            public Builder clearConfigureForUntrustedUpstream() {
                this.configureForUntrustedUpstream_ = false;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m40630setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m40624mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }
}
