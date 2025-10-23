package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.AbstractMessageLite;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class SubstitutionFormatString extends GeneratedMessageV3 implements SubstitutionFormatStringOrBuilder {
    public static final int JSON_FORMAT_FIELD_NUMBER = 2;
    public static final int TEXT_FORMAT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final SubstitutionFormatString DEFAULT_INSTANCE = new SubstitutionFormatString();
    private static final Parser<SubstitutionFormatString> PARSER = new AbstractParser<SubstitutionFormatString>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatString.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public SubstitutionFormatString m24250parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SubstitutionFormatString(codedInputStream, extensionRegistryLite);
        }
    };
    private int formatCase_;
    private Object format_;
    private byte memoizedIsInitialized;

    private SubstitutionFormatString(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.formatCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private SubstitutionFormatString() {
        this.formatCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private SubstitutionFormatString(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            this.formatCase_ = 1;
                            this.format_ = stringRequireUtf8;
                        } else if (tag == 18) {
                            Struct.Builder builder = this.formatCase_ == 2 ? ((Struct) this.format_).toBuilder() : null;
                            Struct message = codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            this.format_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.format_ = builder.buildPartial();
                            }
                            this.formatCase_ = 2;
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

    public static SubstitutionFormatString getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SubstitutionFormatString> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SubstitutionFormatStringProto.internal_static_envoy_config_core_v3_SubstitutionFormatString_descriptor;
    }

    public static SubstitutionFormatString parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (SubstitutionFormatString) PARSER.parseFrom(byteBuffer);
    }

    public static SubstitutionFormatString parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SubstitutionFormatString) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SubstitutionFormatString parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SubstitutionFormatString) PARSER.parseFrom(byteString);
    }

    public static SubstitutionFormatString parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SubstitutionFormatString) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SubstitutionFormatString parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SubstitutionFormatString) PARSER.parseFrom(bArr);
    }

    public static SubstitutionFormatString parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SubstitutionFormatString) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SubstitutionFormatString parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SubstitutionFormatString parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SubstitutionFormatString parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SubstitutionFormatString parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SubstitutionFormatString parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SubstitutionFormatString parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m24248toBuilder();
    }

    public static Builder newBuilder(SubstitutionFormatString substitutionFormatString) {
        return DEFAULT_INSTANCE.m24248toBuilder().mergeFrom(substitutionFormatString);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SubstitutionFormatString m24243getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<SubstitutionFormatString> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatStringOrBuilder
    public boolean hasJsonFormat() {
        return this.formatCase_ == 2;
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
        return new SubstitutionFormatString();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SubstitutionFormatStringProto.internal_static_envoy_config_core_v3_SubstitutionFormatString_fieldAccessorTable.ensureFieldAccessorsInitialized(SubstitutionFormatString.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatStringOrBuilder
    public FormatCase getFormatCase() {
        return FormatCase.forNumber(this.formatCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatStringOrBuilder
    public String getTextFormat() {
        String str = this.formatCase_ == 1 ? this.format_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.formatCase_ == 1) {
            this.format_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatStringOrBuilder
    public ByteString getTextFormatBytes() {
        String str = this.formatCase_ == 1 ? this.format_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.formatCase_ == 1) {
                this.format_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatStringOrBuilder
    public Struct getJsonFormat() {
        if (this.formatCase_ == 2) {
            return (Struct) this.format_;
        }
        return Struct.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatStringOrBuilder
    public StructOrBuilder getJsonFormatOrBuilder() {
        if (this.formatCase_ == 2) {
            return (Struct) this.format_;
        }
        return Struct.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.formatCase_ == 1) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.format_);
        }
        if (this.formatCase_ == 2) {
            codedOutputStream.writeMessage(2, (Struct) this.format_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = this.formatCase_ == 1 ? GeneratedMessageV3.computeStringSize(1, this.format_) : 0;
        if (this.formatCase_ == 2) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, (Struct) this.format_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SubstitutionFormatString)) {
            return super.equals(obj);
        }
        SubstitutionFormatString substitutionFormatString = (SubstitutionFormatString) obj;
        if (!getFormatCase().equals(substitutionFormatString.getFormatCase())) {
            return false;
        }
        int i = this.formatCase_;
        if (i == 1) {
            if (!getTextFormat().equals(substitutionFormatString.getTextFormat())) {
                return false;
            }
        } else if (i == 2 && !getJsonFormat().equals(substitutionFormatString.getJsonFormat())) {
            return false;
        }
        return this.unknownFields.equals(substitutionFormatString.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        int i2 = this.formatCase_;
        if (i2 == 1) {
            i = ((iHashCode2 * 37) + 1) * 53;
            iHashCode = getTextFormat().hashCode();
        } else {
            if (i2 == 2) {
                i = ((iHashCode2 * 37) + 2) * 53;
                iHashCode = getJsonFormat().hashCode();
            }
            int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode3;
            return iHashCode3;
        }
        iHashCode2 = i + iHashCode;
        int iHashCode32 = (iHashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode32;
        return iHashCode32;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m24245newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m24248toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum FormatCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        TEXT_FORMAT(1),
        JSON_FORMAT(2),
        FORMAT_NOT_SET(0);

        private final int value;

        FormatCase(int i) {
            this.value = i;
        }

        public static FormatCase forNumber(int i) {
            if (i == 0) {
                return FORMAT_NOT_SET;
            }
            if (i == 1) {
                return TEXT_FORMAT;
            }
            if (i != 2) {
                return null;
            }
            return JSON_FORMAT;
        }

        @Deprecated
        public static FormatCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SubstitutionFormatStringOrBuilder {
        private int formatCase_;
        private Object format_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> jsonFormatBuilder_;

        private Builder() {
            this.formatCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.formatCase_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SubstitutionFormatStringProto.internal_static_envoy_config_core_v3_SubstitutionFormatString_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatStringOrBuilder
        public boolean hasJsonFormat() {
            return this.formatCase_ == 2;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SubstitutionFormatStringProto.internal_static_envoy_config_core_v3_SubstitutionFormatString_fieldAccessorTable.ensureFieldAccessorsInitialized(SubstitutionFormatString.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SubstitutionFormatString.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24259clear() {
            super.clear();
            this.formatCase_ = 0;
            this.format_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return SubstitutionFormatStringProto.internal_static_envoy_config_core_v3_SubstitutionFormatString_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SubstitutionFormatString m24272getDefaultInstanceForType() {
            return SubstitutionFormatString.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SubstitutionFormatString m24253build() throws UninitializedMessageException {
            SubstitutionFormatString substitutionFormatStringM24255buildPartial = m24255buildPartial();
            if (substitutionFormatStringM24255buildPartial.isInitialized()) {
                return substitutionFormatStringM24255buildPartial;
            }
            throw newUninitializedMessageException(substitutionFormatStringM24255buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SubstitutionFormatString m24255buildPartial() {
            SubstitutionFormatString substitutionFormatString = new SubstitutionFormatString(this);
            if (this.formatCase_ == 1) {
                substitutionFormatString.format_ = this.format_;
            }
            if (this.formatCase_ == 2) {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.jsonFormatBuilder_;
                if (singleFieldBuilderV3 == null) {
                    substitutionFormatString.format_ = this.format_;
                } else {
                    substitutionFormatString.format_ = singleFieldBuilderV3.build();
                }
            }
            substitutionFormatString.formatCase_ = this.formatCase_;
            onBuilt();
            return substitutionFormatString;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24271clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24283setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24261clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24264clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24285setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24251addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m24276mergeFrom(Message message) {
            if (message instanceof SubstitutionFormatString) {
                return mergeFrom((SubstitutionFormatString) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SubstitutionFormatString substitutionFormatString) {
            if (substitutionFormatString == SubstitutionFormatString.getDefaultInstance()) {
                return this;
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$core$v3$SubstitutionFormatString$FormatCase[substitutionFormatString.getFormatCase().ordinal()];
            if (i == 1) {
                this.formatCase_ = 1;
                this.format_ = substitutionFormatString.format_;
                onChanged();
            } else if (i == 2) {
                mergeJsonFormat(substitutionFormatString.getJsonFormat());
            }
            m24281mergeUnknownFields(substitutionFormatString.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatString.Builder m24277mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatString.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatString r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatString) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatString r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatString) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatString.Builder.m24277mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatString$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatStringOrBuilder
        public FormatCase getFormatCase() {
            return FormatCase.forNumber(this.formatCase_);
        }

        public Builder clearFormat() {
            this.formatCase_ = 0;
            this.format_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatStringOrBuilder
        public String getTextFormat() {
            String str = this.formatCase_ == 1 ? this.format_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.formatCase_ == 1) {
                    this.format_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setTextFormat(String str) {
            str.getClass();
            this.formatCase_ = 1;
            this.format_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatStringOrBuilder
        public ByteString getTextFormatBytes() {
            String str = this.formatCase_ == 1 ? this.format_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.formatCase_ == 1) {
                    this.format_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setTextFormatBytes(ByteString byteString) {
            byteString.getClass();
            SubstitutionFormatString.checkByteStringIsUtf8(byteString);
            this.formatCase_ = 1;
            this.format_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearTextFormat() {
            if (this.formatCase_ == 1) {
                this.formatCase_ = 0;
                this.format_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatStringOrBuilder
        public Struct getJsonFormat() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.jsonFormatBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.formatCase_ == 2) {
                    return (Struct) this.format_;
                }
                return Struct.getDefaultInstance();
            }
            if (this.formatCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return Struct.getDefaultInstance();
        }

        public Builder setJsonFormat(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.jsonFormatBuilder_;
            if (singleFieldBuilderV3 == null) {
                struct.getClass();
                this.format_ = struct;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(struct);
            }
            this.formatCase_ = 2;
            return this;
        }

        public Builder setJsonFormat(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.jsonFormatBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.format_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.formatCase_ = 2;
            return this;
        }

        public Builder mergeJsonFormat(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.jsonFormatBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.formatCase_ != 2 || this.format_ == Struct.getDefaultInstance()) {
                    this.format_ = struct;
                } else {
                    this.format_ = Struct.newBuilder((Struct) this.format_).mergeFrom(struct).buildPartial();
                }
                onChanged();
            } else {
                if (this.formatCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(struct);
                }
                this.jsonFormatBuilder_.setMessage(struct);
            }
            this.formatCase_ = 2;
            return this;
        }

        public Builder clearJsonFormat() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.jsonFormatBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.formatCase_ == 2) {
                    this.formatCase_ = 0;
                    this.format_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.formatCase_ == 2) {
                this.formatCase_ = 0;
                this.format_ = null;
                onChanged();
            }
            return this;
        }

        public Struct.Builder getJsonFormatBuilder() {
            return getJsonFormatFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatStringOrBuilder
        public StructOrBuilder getJsonFormatOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3;
            int i = this.formatCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.jsonFormatBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (Struct) this.format_;
            }
            return Struct.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getJsonFormatFieldBuilder() {
            if (this.jsonFormatBuilder_ == null) {
                if (this.formatCase_ != 2) {
                    this.format_ = Struct.getDefaultInstance();
                }
                this.jsonFormatBuilder_ = new SingleFieldBuilderV3<>((Struct) this.format_, getParentForChildren(), isClean());
                this.format_ = null;
            }
            this.formatCase_ = 2;
            onChanged();
            return this.jsonFormatBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m24287setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m24281mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatString$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$core$v3$SubstitutionFormatString$FormatCase;

        static {
            int[] iArr = new int[FormatCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$core$v3$SubstitutionFormatString$FormatCase = iArr;
            try {
                iArr[FormatCase.TEXT_FORMAT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$SubstitutionFormatString$FormatCase[FormatCase.JSON_FORMAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$SubstitutionFormatString$FormatCase[FormatCase.FORMAT_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
