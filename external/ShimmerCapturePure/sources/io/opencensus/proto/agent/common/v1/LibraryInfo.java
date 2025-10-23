package io.opencensus.proto.agent.common.v1;

import com.google.protobuf.AbstractParser;
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
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class LibraryInfo extends GeneratedMessageV3 implements LibraryInfoOrBuilder {
    public static final int CORE_LIBRARY_VERSION_FIELD_NUMBER = 3;
    public static final int EXPORTER_VERSION_FIELD_NUMBER = 2;
    public static final int LANGUAGE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final LibraryInfo DEFAULT_INSTANCE = new LibraryInfo();
    private static final Parser<LibraryInfo> PARSER = new AbstractParser<LibraryInfo>() { // from class: io.opencensus.proto.agent.common.v1.LibraryInfo.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public LibraryInfo m36319parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new LibraryInfo(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object coreLibraryVersion_;
    private volatile Object exporterVersion_;
    private int language_;
    private byte memoizedIsInitialized;

    private LibraryInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private LibraryInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.language_ = 0;
        this.exporterVersion_ = "";
        this.coreLibraryVersion_ = "";
    }

    private LibraryInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.language_ = codedInputStream.readEnum();
                        } else if (tag == 18) {
                            this.exporterVersion_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag != 26) {
                            if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.coreLibraryVersion_ = codedInputStream.readStringRequireUtf8();
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

    public static LibraryInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LibraryInfo> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return CommonProto.internal_static_opencensus_proto_agent_common_v1_LibraryInfo_descriptor;
    }

    public static LibraryInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (LibraryInfo) PARSER.parseFrom(byteBuffer);
    }

    public static LibraryInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LibraryInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static LibraryInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (LibraryInfo) PARSER.parseFrom(byteString);
    }

    public static LibraryInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LibraryInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static LibraryInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (LibraryInfo) PARSER.parseFrom(bArr);
    }

    public static LibraryInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LibraryInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static LibraryInfo parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static LibraryInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LibraryInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static LibraryInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LibraryInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static LibraryInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m36317toBuilder();
    }

    public static Builder newBuilder(LibraryInfo libraryInfo) {
        return DEFAULT_INSTANCE.m36317toBuilder().mergeFrom(libraryInfo);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public LibraryInfo m36312getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.opencensus.proto.agent.common.v1.LibraryInfoOrBuilder
    public int getLanguageValue() {
        return this.language_;
    }

    public Parser<LibraryInfo> getParserForType() {
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
        return CommonProto.internal_static_opencensus_proto_agent_common_v1_LibraryInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(LibraryInfo.class, Builder.class);
    }

    @Override // io.opencensus.proto.agent.common.v1.LibraryInfoOrBuilder
    public Language getLanguage() {
        Language languageValueOf = Language.valueOf(this.language_);
        return languageValueOf == null ? Language.UNRECOGNIZED : languageValueOf;
    }

    @Override // io.opencensus.proto.agent.common.v1.LibraryInfoOrBuilder
    public String getExporterVersion() {
        Object obj = this.exporterVersion_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.exporterVersion_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.opencensus.proto.agent.common.v1.LibraryInfoOrBuilder
    public ByteString getExporterVersionBytes() {
        Object obj = this.exporterVersion_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.exporterVersion_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.opencensus.proto.agent.common.v1.LibraryInfoOrBuilder
    public String getCoreLibraryVersion() {
        Object obj = this.coreLibraryVersion_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.coreLibraryVersion_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.opencensus.proto.agent.common.v1.LibraryInfoOrBuilder
    public ByteString getCoreLibraryVersionBytes() {
        Object obj = this.coreLibraryVersion_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.coreLibraryVersion_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.language_ != Language.LANGUAGE_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(1, this.language_);
        }
        if (!getExporterVersionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.exporterVersion_);
        }
        if (!getCoreLibraryVersionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.coreLibraryVersion_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeEnumSize = this.language_ != Language.LANGUAGE_UNSPECIFIED.getNumber() ? CodedOutputStream.computeEnumSize(1, this.language_) : 0;
        if (!getExporterVersionBytes().isEmpty()) {
            iComputeEnumSize += GeneratedMessageV3.computeStringSize(2, this.exporterVersion_);
        }
        if (!getCoreLibraryVersionBytes().isEmpty()) {
            iComputeEnumSize += GeneratedMessageV3.computeStringSize(3, this.coreLibraryVersion_);
        }
        int serializedSize = iComputeEnumSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LibraryInfo)) {
            return super.equals(obj);
        }
        LibraryInfo libraryInfo = (LibraryInfo) obj;
        return this.language_ == libraryInfo.language_ && getExporterVersion().equals(libraryInfo.getExporterVersion()) && getCoreLibraryVersion().equals(libraryInfo.getCoreLibraryVersion()) && this.unknownFields.equals(libraryInfo.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.language_) * 37) + 2) * 53) + getExporterVersion().hashCode()) * 37) + 3) * 53) + getCoreLibraryVersion().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m36314newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m36317toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum Language implements ProtocolMessageEnum {
        LANGUAGE_UNSPECIFIED(0),
        CPP(1),
        C_SHARP(2),
        ERLANG(3),
        GO_LANG(4),
        JAVA(5),
        NODE_JS(6),
        PHP(7),
        PYTHON(8),
        RUBY(9),
        UNRECOGNIZED(-1);

        public static final int CPP_VALUE = 1;
        public static final int C_SHARP_VALUE = 2;
        public static final int ERLANG_VALUE = 3;
        public static final int GO_LANG_VALUE = 4;
        public static final int JAVA_VALUE = 5;
        public static final int LANGUAGE_UNSPECIFIED_VALUE = 0;
        public static final int NODE_JS_VALUE = 6;
        public static final int PHP_VALUE = 7;
        public static final int PYTHON_VALUE = 8;
        public static final int RUBY_VALUE = 9;
        private static final Internal.EnumLiteMap<Language> internalValueMap = new Internal.EnumLiteMap<Language>() { // from class: io.opencensus.proto.agent.common.v1.LibraryInfo.Language.1
            public Language findValueByNumber(int i) {
                return Language.forNumber(i);
            }
        };
        private static final Language[] VALUES = values();
        private final int value;

        Language(int i) {
            this.value = i;
        }

        public static Language forNumber(int i) {
            switch (i) {
                case 0:
                    return LANGUAGE_UNSPECIFIED;
                case 1:
                    return CPP;
                case 2:
                    return C_SHARP;
                case 3:
                    return ERLANG;
                case 4:
                    return GO_LANG;
                case 5:
                    return JAVA;
                case 6:
                    return NODE_JS;
                case 7:
                    return PHP;
                case 8:
                    return PYTHON;
                case 9:
                    return RUBY;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<Language> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static Language valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) LibraryInfo.getDescriptor().getEnumTypes().get(0);
        }

        public static Language valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LibraryInfoOrBuilder {
        private Object coreLibraryVersion_;
        private Object exporterVersion_;
        private int language_;

        private Builder() {
            this.language_ = 0;
            this.exporterVersion_ = "";
            this.coreLibraryVersion_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.language_ = 0;
            this.exporterVersion_ = "";
            this.coreLibraryVersion_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return CommonProto.internal_static_opencensus_proto_agent_common_v1_LibraryInfo_descriptor;
        }

        @Override // io.opencensus.proto.agent.common.v1.LibraryInfoOrBuilder
        public int getLanguageValue() {
            return this.language_;
        }

        public Builder setLanguageValue(int i) {
            this.language_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return CommonProto.internal_static_opencensus_proto_agent_common_v1_LibraryInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(LibraryInfo.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = LibraryInfo.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36328clear() {
            super.clear();
            this.language_ = 0;
            this.exporterVersion_ = "";
            this.coreLibraryVersion_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return CommonProto.internal_static_opencensus_proto_agent_common_v1_LibraryInfo_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LibraryInfo m36341getDefaultInstanceForType() {
            return LibraryInfo.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LibraryInfo m36322build() throws UninitializedMessageException {
            LibraryInfo libraryInfoM36324buildPartial = m36324buildPartial();
            if (libraryInfoM36324buildPartial.isInitialized()) {
                return libraryInfoM36324buildPartial;
            }
            throw newUninitializedMessageException(libraryInfoM36324buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LibraryInfo m36324buildPartial() {
            LibraryInfo libraryInfo = new LibraryInfo(this);
            libraryInfo.language_ = this.language_;
            libraryInfo.exporterVersion_ = this.exporterVersion_;
            libraryInfo.coreLibraryVersion_ = this.coreLibraryVersion_;
            onBuilt();
            return libraryInfo;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36340clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36352setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36330clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36333clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36354setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36320addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36345mergeFrom(Message message) {
            if (message instanceof LibraryInfo) {
                return mergeFrom((LibraryInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(LibraryInfo libraryInfo) {
            if (libraryInfo == LibraryInfo.getDefaultInstance()) {
                return this;
            }
            if (libraryInfo.language_ != 0) {
                setLanguageValue(libraryInfo.getLanguageValue());
            }
            if (!libraryInfo.getExporterVersion().isEmpty()) {
                this.exporterVersion_ = libraryInfo.exporterVersion_;
                onChanged();
            }
            if (!libraryInfo.getCoreLibraryVersion().isEmpty()) {
                this.coreLibraryVersion_ = libraryInfo.coreLibraryVersion_;
                onChanged();
            }
            m36350mergeUnknownFields(libraryInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.agent.common.v1.LibraryInfo.Builder m36346mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.agent.common.v1.LibraryInfo.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.agent.common.v1.LibraryInfo r3 = (io.opencensus.proto.agent.common.v1.LibraryInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.agent.common.v1.LibraryInfo r4 = (io.opencensus.proto.agent.common.v1.LibraryInfo) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.agent.common.v1.LibraryInfo.Builder.m36346mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.agent.common.v1.LibraryInfo$Builder");
        }

        @Override // io.opencensus.proto.agent.common.v1.LibraryInfoOrBuilder
        public Language getLanguage() {
            Language languageValueOf = Language.valueOf(this.language_);
            return languageValueOf == null ? Language.UNRECOGNIZED : languageValueOf;
        }

        public Builder setLanguage(Language language) {
            language.getClass();
            this.language_ = language.getNumber();
            onChanged();
            return this;
        }

        public Builder clearLanguage() {
            this.language_ = 0;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.agent.common.v1.LibraryInfoOrBuilder
        public String getExporterVersion() {
            Object obj = this.exporterVersion_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.exporterVersion_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setExporterVersion(String str) {
            str.getClass();
            this.exporterVersion_ = str;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.agent.common.v1.LibraryInfoOrBuilder
        public ByteString getExporterVersionBytes() {
            Object obj = this.exporterVersion_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.exporterVersion_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setExporterVersionBytes(ByteString byteString) {
            byteString.getClass();
            LibraryInfo.checkByteStringIsUtf8(byteString);
            this.exporterVersion_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearExporterVersion() {
            this.exporterVersion_ = LibraryInfo.getDefaultInstance().getExporterVersion();
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.agent.common.v1.LibraryInfoOrBuilder
        public String getCoreLibraryVersion() {
            Object obj = this.coreLibraryVersion_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.coreLibraryVersion_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setCoreLibraryVersion(String str) {
            str.getClass();
            this.coreLibraryVersion_ = str;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.agent.common.v1.LibraryInfoOrBuilder
        public ByteString getCoreLibraryVersionBytes() {
            Object obj = this.coreLibraryVersion_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.coreLibraryVersion_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setCoreLibraryVersionBytes(ByteString byteString) {
            byteString.getClass();
            LibraryInfo.checkByteStringIsUtf8(byteString);
            this.coreLibraryVersion_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearCoreLibraryVersion() {
            this.coreLibraryVersion_ = LibraryInfo.getDefaultInstance().getCoreLibraryVersion();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m36356setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m36350mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
