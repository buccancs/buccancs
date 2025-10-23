package io.opencensus.proto.trace.v1;

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
import io.opencensus.proto.trace.v1.TruncatableString;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class Module extends GeneratedMessageV3 implements ModuleOrBuilder {
    public static final int BUILD_ID_FIELD_NUMBER = 2;
    public static final int MODULE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final Module DEFAULT_INSTANCE = new Module();
    private static final Parser<Module> PARSER = new AbstractParser<Module>() { // from class: io.opencensus.proto.trace.v1.Module.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Module m37934parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Module(codedInputStream, extensionRegistryLite);
        }
    };
    private TruncatableString buildId_;
    private byte memoizedIsInitialized;
    private TruncatableString module_;

    private Module(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Module() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private Module(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        TruncatableString.Builder builderM38763toBuilder;
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
                            TruncatableString truncatableString = this.module_;
                            builderM38763toBuilder = truncatableString != null ? truncatableString.m38763toBuilder() : null;
                            TruncatableString truncatableString2 = (TruncatableString) codedInputStream.readMessage(TruncatableString.parser(), extensionRegistryLite);
                            this.module_ = truncatableString2;
                            if (builderM38763toBuilder != null) {
                                builderM38763toBuilder.mergeFrom(truncatableString2);
                                this.module_ = builderM38763toBuilder.m38770buildPartial();
                            }
                        } else if (tag != 18) {
                            if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            TruncatableString truncatableString3 = this.buildId_;
                            builderM38763toBuilder = truncatableString3 != null ? truncatableString3.m38763toBuilder() : null;
                            TruncatableString truncatableString4 = (TruncatableString) codedInputStream.readMessage(TruncatableString.parser(), extensionRegistryLite);
                            this.buildId_ = truncatableString4;
                            if (builderM38763toBuilder != null) {
                                builderM38763toBuilder.mergeFrom(truncatableString4);
                                this.buildId_ = builderM38763toBuilder.m38770buildPartial();
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
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static Module getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Module> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return TraceProto.internal_static_opencensus_proto_trace_v1_Module_descriptor;
    }

    public static Module parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Module) PARSER.parseFrom(byteBuffer);
    }

    public static Module parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Module) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Module parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Module) PARSER.parseFrom(byteString);
    }

    public static Module parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Module) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Module parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Module) PARSER.parseFrom(bArr);
    }

    public static Module parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Module) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Module parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Module parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Module parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Module parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Module parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Module parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m37932toBuilder();
    }

    public static Builder newBuilder(Module module) {
        return DEFAULT_INSTANCE.m37932toBuilder().mergeFrom(module);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Module m37927getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Module> getParserForType() {
        return PARSER;
    }

    @Override // io.opencensus.proto.trace.v1.ModuleOrBuilder
    public boolean hasBuildId() {
        return this.buildId_ != null;
    }

    @Override // io.opencensus.proto.trace.v1.ModuleOrBuilder
    public boolean hasModule() {
        return this.module_ != null;
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
        return TraceProto.internal_static_opencensus_proto_trace_v1_Module_fieldAccessorTable.ensureFieldAccessorsInitialized(Module.class, Builder.class);
    }

    @Override // io.opencensus.proto.trace.v1.ModuleOrBuilder
    public TruncatableString getModule() {
        TruncatableString truncatableString = this.module_;
        return truncatableString == null ? TruncatableString.getDefaultInstance() : truncatableString;
    }

    @Override // io.opencensus.proto.trace.v1.ModuleOrBuilder
    public TruncatableStringOrBuilder getModuleOrBuilder() {
        return getModule();
    }

    @Override // io.opencensus.proto.trace.v1.ModuleOrBuilder
    public TruncatableString getBuildId() {
        TruncatableString truncatableString = this.buildId_;
        return truncatableString == null ? TruncatableString.getDefaultInstance() : truncatableString;
    }

    @Override // io.opencensus.proto.trace.v1.ModuleOrBuilder
    public TruncatableStringOrBuilder getBuildIdOrBuilder() {
        return getBuildId();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.module_ != null) {
            codedOutputStream.writeMessage(1, getModule());
        }
        if (this.buildId_ != null) {
            codedOutputStream.writeMessage(2, getBuildId());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.module_ != null ? CodedOutputStream.computeMessageSize(1, getModule()) : 0;
        if (this.buildId_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getBuildId());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0042  */
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
            boolean r1 = r5 instanceof io.opencensus.proto.trace.v1.Module
            if (r1 != 0) goto Ld
            boolean r5 = super.equals(r5)
            return r5
        Ld:
            io.opencensus.proto.trace.v1.Module r5 = (io.opencensus.proto.trace.v1.Module) r5
            boolean r1 = r4.hasModule()
            boolean r2 = r5.hasModule()
            r3 = 0
            if (r1 != r2) goto L1c
            r1 = 1
            goto L1d
        L1c:
            r1 = 0
        L1d:
            boolean r2 = r4.hasModule()
            if (r2 == 0) goto L34
            if (r1 == 0) goto L42
            io.opencensus.proto.trace.v1.TruncatableString r1 = r4.getModule()
            io.opencensus.proto.trace.v1.TruncatableString r2 = r5.getModule()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L42
            goto L36
        L34:
            if (r1 == 0) goto L42
        L36:
            boolean r1 = r4.hasBuildId()
            boolean r2 = r5.hasBuildId()
            if (r1 != r2) goto L42
            r1 = 1
            goto L43
        L42:
            r1 = 0
        L43:
            boolean r2 = r4.hasBuildId()
            if (r2 == 0) goto L5a
            if (r1 == 0) goto L67
            io.opencensus.proto.trace.v1.TruncatableString r1 = r4.getBuildId()
            io.opencensus.proto.trace.v1.TruncatableString r2 = r5.getBuildId()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L67
            goto L5c
        L5a:
            if (r1 == 0) goto L67
        L5c:
            com.google.protobuf.UnknownFieldSet r1 = r4.unknownFields
            com.google.protobuf.UnknownFieldSet r5 = r5.unknownFields
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L67
            goto L68
        L67:
            r0 = 0
        L68:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.trace.v1.Module.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasModule()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getModule().hashCode();
        }
        if (hasBuildId()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getBuildId().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37929newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m37932toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ModuleOrBuilder {
        private SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> buildIdBuilder_;
        private TruncatableString buildId_;
        private SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> moduleBuilder_;
        private TruncatableString module_;

        private Builder() {
            this.module_ = null;
            this.buildId_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.module_ = null;
            this.buildId_ = null;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TraceProto.internal_static_opencensus_proto_trace_v1_Module_descriptor;
        }

        @Override // io.opencensus.proto.trace.v1.ModuleOrBuilder
        public boolean hasBuildId() {
            return (this.buildIdBuilder_ == null && this.buildId_ == null) ? false : true;
        }

        @Override // io.opencensus.proto.trace.v1.ModuleOrBuilder
        public boolean hasModule() {
            return (this.moduleBuilder_ == null && this.module_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TraceProto.internal_static_opencensus_proto_trace_v1_Module_fieldAccessorTable.ensureFieldAccessorsInitialized(Module.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Module.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37943clear() {
            super.clear();
            if (this.moduleBuilder_ == null) {
                this.module_ = null;
            } else {
                this.module_ = null;
                this.moduleBuilder_ = null;
            }
            if (this.buildIdBuilder_ == null) {
                this.buildId_ = null;
            } else {
                this.buildId_ = null;
                this.buildIdBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return TraceProto.internal_static_opencensus_proto_trace_v1_Module_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Module m37956getDefaultInstanceForType() {
            return Module.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Module m37937build() throws UninitializedMessageException {
            Module moduleM37939buildPartial = m37939buildPartial();
            if (moduleM37939buildPartial.isInitialized()) {
                return moduleM37939buildPartial;
            }
            throw newUninitializedMessageException(moduleM37939buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Module m37939buildPartial() {
            Module module = new Module(this);
            SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> singleFieldBuilderV3 = this.moduleBuilder_;
            if (singleFieldBuilderV3 == null) {
                module.module_ = this.module_;
            } else {
                module.module_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> singleFieldBuilderV32 = this.buildIdBuilder_;
            if (singleFieldBuilderV32 == null) {
                module.buildId_ = this.buildId_;
            } else {
                module.buildId_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return module;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37955clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37967setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37945clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37948clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37969setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37935addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m37960mergeFrom(Message message) {
            if (message instanceof Module) {
                return mergeFrom((Module) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Module module) {
            if (module == Module.getDefaultInstance()) {
                return this;
            }
            if (module.hasModule()) {
                mergeModule(module.getModule());
            }
            if (module.hasBuildId()) {
                mergeBuildId(module.getBuildId());
            }
            m37965mergeUnknownFields(module.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.trace.v1.Module.Builder m37961mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.trace.v1.Module.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.trace.v1.Module r3 = (io.opencensus.proto.trace.v1.Module) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.trace.v1.Module r4 = (io.opencensus.proto.trace.v1.Module) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.trace.v1.Module.Builder.m37961mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.trace.v1.Module$Builder");
        }

        @Override // io.opencensus.proto.trace.v1.ModuleOrBuilder
        public TruncatableString getModule() {
            SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> singleFieldBuilderV3 = this.moduleBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            TruncatableString truncatableString = this.module_;
            return truncatableString == null ? TruncatableString.getDefaultInstance() : truncatableString;
        }

        public Builder setModule(TruncatableString truncatableString) {
            SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> singleFieldBuilderV3 = this.moduleBuilder_;
            if (singleFieldBuilderV3 == null) {
                truncatableString.getClass();
                this.module_ = truncatableString;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(truncatableString);
            }
            return this;
        }

        public Builder setModule(TruncatableString.Builder builder) {
            SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> singleFieldBuilderV3 = this.moduleBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.module_ = builder.m38768build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m38768build());
            }
            return this;
        }

        public Builder mergeModule(TruncatableString truncatableString) {
            SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> singleFieldBuilderV3 = this.moduleBuilder_;
            if (singleFieldBuilderV3 == null) {
                TruncatableString truncatableString2 = this.module_;
                if (truncatableString2 != null) {
                    this.module_ = TruncatableString.newBuilder(truncatableString2).mergeFrom(truncatableString).m38770buildPartial();
                } else {
                    this.module_ = truncatableString;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(truncatableString);
            }
            return this;
        }

        public Builder clearModule() {
            if (this.moduleBuilder_ == null) {
                this.module_ = null;
                onChanged();
            } else {
                this.module_ = null;
                this.moduleBuilder_ = null;
            }
            return this;
        }

        public TruncatableString.Builder getModuleBuilder() {
            onChanged();
            return getModuleFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.trace.v1.ModuleOrBuilder
        public TruncatableStringOrBuilder getModuleOrBuilder() {
            SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> singleFieldBuilderV3 = this.moduleBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (TruncatableStringOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            TruncatableString truncatableString = this.module_;
            return truncatableString == null ? TruncatableString.getDefaultInstance() : truncatableString;
        }

        private SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> getModuleFieldBuilder() {
            if (this.moduleBuilder_ == null) {
                this.moduleBuilder_ = new SingleFieldBuilderV3<>(getModule(), getParentForChildren(), isClean());
                this.module_ = null;
            }
            return this.moduleBuilder_;
        }

        @Override // io.opencensus.proto.trace.v1.ModuleOrBuilder
        public TruncatableString getBuildId() {
            SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> singleFieldBuilderV3 = this.buildIdBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            TruncatableString truncatableString = this.buildId_;
            return truncatableString == null ? TruncatableString.getDefaultInstance() : truncatableString;
        }

        public Builder setBuildId(TruncatableString truncatableString) {
            SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> singleFieldBuilderV3 = this.buildIdBuilder_;
            if (singleFieldBuilderV3 == null) {
                truncatableString.getClass();
                this.buildId_ = truncatableString;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(truncatableString);
            }
            return this;
        }

        public Builder setBuildId(TruncatableString.Builder builder) {
            SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> singleFieldBuilderV3 = this.buildIdBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.buildId_ = builder.m38768build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m38768build());
            }
            return this;
        }

        public Builder mergeBuildId(TruncatableString truncatableString) {
            SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> singleFieldBuilderV3 = this.buildIdBuilder_;
            if (singleFieldBuilderV3 == null) {
                TruncatableString truncatableString2 = this.buildId_;
                if (truncatableString2 != null) {
                    this.buildId_ = TruncatableString.newBuilder(truncatableString2).mergeFrom(truncatableString).m38770buildPartial();
                } else {
                    this.buildId_ = truncatableString;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(truncatableString);
            }
            return this;
        }

        public Builder clearBuildId() {
            if (this.buildIdBuilder_ == null) {
                this.buildId_ = null;
                onChanged();
            } else {
                this.buildId_ = null;
                this.buildIdBuilder_ = null;
            }
            return this;
        }

        public TruncatableString.Builder getBuildIdBuilder() {
            onChanged();
            return getBuildIdFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.trace.v1.ModuleOrBuilder
        public TruncatableStringOrBuilder getBuildIdOrBuilder() {
            SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> singleFieldBuilderV3 = this.buildIdBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (TruncatableStringOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            TruncatableString truncatableString = this.buildId_;
            return truncatableString == null ? TruncatableString.getDefaultInstance() : truncatableString;
        }

        private SingleFieldBuilderV3<TruncatableString, TruncatableString.Builder, TruncatableStringOrBuilder> getBuildIdFieldBuilder() {
            if (this.buildIdBuilder_ == null) {
                this.buildIdBuilder_ = new SingleFieldBuilderV3<>(getBuildId(), getParentForChildren(), isClean());
                this.buildId_ = null;
            }
            return this.buildIdBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37971setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m37965mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
