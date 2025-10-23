package com.google.api;

import com.google.api.Advice;
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

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class ConfigChange extends GeneratedMessageV3 implements ConfigChangeOrBuilder {
    public static final int ADVICES_FIELD_NUMBER = 5;
    public static final int CHANGE_TYPE_FIELD_NUMBER = 4;
    public static final int ELEMENT_FIELD_NUMBER = 1;
    public static final int NEW_VALUE_FIELD_NUMBER = 3;
    public static final int OLD_VALUE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final ConfigChange DEFAULT_INSTANCE = new ConfigChange();
    private static final Parser<ConfigChange> PARSER = new AbstractParser<ConfigChange>() { // from class: com.google.api.ConfigChange.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ConfigChange m620parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ConfigChange(codedInputStream, extensionRegistryLite);
        }
    };
    private List<Advice> advices_;
    private int bitField0_;
    private int changeType_;
    private volatile Object element_;
    private byte memoizedIsInitialized;
    private volatile Object newValue_;
    private volatile Object oldValue_;

    private ConfigChange(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ConfigChange() {
        this.memoizedIsInitialized = (byte) -1;
        this.element_ = "";
        this.oldValue_ = "";
        this.newValue_ = "";
        this.changeType_ = 0;
        this.advices_ = Collections.emptyList();
    }

    private ConfigChange(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            this.element_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            this.oldValue_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 26) {
                            this.newValue_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 32) {
                            this.changeType_ = codedInputStream.readEnum();
                        } else if (tag == 42) {
                            if ((i & 16) == 0) {
                                this.advices_ = new ArrayList();
                                i |= 16;
                            }
                            this.advices_.add(codedInputStream.readMessage(Advice.parser(), extensionRegistryLite));
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
                if ((i & 16) != 0) {
                    this.advices_ = Collections.unmodifiableList(this.advices_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ConfigChange getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ConfigChange> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ConfigChangeProto.internal_static_google_api_ConfigChange_descriptor;
    }

    public static ConfigChange parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ConfigChange) PARSER.parseFrom(byteBuffer);
    }

    public static ConfigChange parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ConfigChange) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ConfigChange) PARSER.parseFrom(byteString);
    }

    public static ConfigChange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ConfigChange) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ConfigChange) PARSER.parseFrom(bArr);
    }

    public static ConfigChange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ConfigChange) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ConfigChange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ConfigChange parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ConfigChange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ConfigChange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m619toBuilder();
    }

    public static Builder newBuilder(ConfigChange configChange) {
        return DEFAULT_INSTANCE.m619toBuilder().mergeFrom(configChange);
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public List<Advice> getAdvicesList() {
        return this.advices_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public List<? extends AdviceOrBuilder> getAdvicesOrBuilderList() {
        return this.advices_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public int getChangeTypeValue() {
        return this.changeType_;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ConfigChange m614getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ConfigChange> getParserForType() {
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
        return ConfigChangeProto.internal_static_google_api_ConfigChange_fieldAccessorTable.ensureFieldAccessorsInitialized(ConfigChange.class, Builder.class);
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public String getElement() {
        Object obj = this.element_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.element_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ByteString getElementBytes() {
        Object obj = this.element_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.element_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public String getOldValue() {
        Object obj = this.oldValue_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.oldValue_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ByteString getOldValueBytes() {
        Object obj = this.oldValue_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.oldValue_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public String getNewValue() {
        Object obj = this.newValue_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.newValue_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ByteString getNewValueBytes() {
        Object obj = this.newValue_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.newValue_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ChangeType getChangeType() {
        ChangeType changeTypeValueOf = ChangeType.valueOf(this.changeType_);
        return changeTypeValueOf == null ? ChangeType.UNRECOGNIZED : changeTypeValueOf;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public int getAdvicesCount() {
        return this.advices_.size();
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public Advice getAdvices(int i) {
        return this.advices_.get(i);
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public AdviceOrBuilder getAdvicesOrBuilder(int i) {
        return this.advices_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getElementBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.element_);
        }
        if (!getOldValueBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.oldValue_);
        }
        if (!getNewValueBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.newValue_);
        }
        if (this.changeType_ != ChangeType.CHANGE_TYPE_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(4, this.changeType_);
        }
        for (int i = 0; i < this.advices_.size(); i++) {
            codedOutputStream.writeMessage(5, this.advices_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getElementBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.element_) : 0;
        if (!getOldValueBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.oldValue_);
        }
        if (!getNewValueBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.newValue_);
        }
        if (this.changeType_ != ChangeType.CHANGE_TYPE_UNSPECIFIED.getNumber()) {
            iComputeStringSize += CodedOutputStream.computeEnumSize(4, this.changeType_);
        }
        for (int i2 = 0; i2 < this.advices_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(5, this.advices_.get(i2));
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConfigChange)) {
            return super.equals(obj);
        }
        ConfigChange configChange = (ConfigChange) obj;
        return getElement().equals(configChange.getElement()) && getOldValue().equals(configChange.getOldValue()) && getNewValue().equals(configChange.getNewValue()) && this.changeType_ == configChange.changeType_ && getAdvicesList().equals(configChange.getAdvicesList()) && this.unknownFields.equals(configChange.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getElement().hashCode()) * 37) + 2) * 53) + getOldValue().hashCode()) * 37) + 3) * 53) + getNewValue().hashCode()) * 37) + 4) * 53) + this.changeType_;
        if (getAdvicesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 5) * 53) + getAdvicesList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m617newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m619toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m616newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ConfigChangeOrBuilder {
        private RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> advicesBuilder_;
        private List<Advice> advices_;
        private int bitField0_;
        private int changeType_;
        private Object element_;
        private Object newValue_;
        private Object oldValue_;

        private Builder() {
            this.element_ = "";
            this.oldValue_ = "";
            this.newValue_ = "";
            this.changeType_ = 0;
            this.advices_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.element_ = "";
            this.oldValue_ = "";
            this.newValue_ = "";
            this.changeType_ = 0;
            this.advices_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ConfigChangeProto.internal_static_google_api_ConfigChange_descriptor;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public int getChangeTypeValue() {
            return this.changeType_;
        }

        public Builder setChangeTypeValue(int i) {
            this.changeType_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ConfigChangeProto.internal_static_google_api_ConfigChange_fieldAccessorTable.ensureFieldAccessorsInitialized(ConfigChange.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (ConfigChange.alwaysUseFieldBuilders) {
                getAdvicesFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m630clear() {
            super.clear();
            this.element_ = "";
            this.oldValue_ = "";
            this.newValue_ = "";
            this.changeType_ = 0;
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.advices_ = Collections.emptyList();
                this.bitField0_ &= -17;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ConfigChangeProto.internal_static_google_api_ConfigChange_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ConfigChange m643getDefaultInstanceForType() {
            return ConfigChange.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ConfigChange m624build() throws UninitializedMessageException {
            ConfigChange configChangeM626buildPartial = m626buildPartial();
            if (configChangeM626buildPartial.isInitialized()) {
                return configChangeM626buildPartial;
            }
            throw newUninitializedMessageException(configChangeM626buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ConfigChange m626buildPartial() {
            ConfigChange configChange = new ConfigChange(this);
            configChange.element_ = this.element_;
            configChange.oldValue_ = this.oldValue_;
            configChange.newValue_ = this.newValue_;
            configChange.changeType_ = this.changeType_;
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 16) != 0) {
                    this.advices_ = Collections.unmodifiableList(this.advices_);
                    this.bitField0_ &= -17;
                }
                configChange.advices_ = this.advices_;
            } else {
                configChange.advices_ = repeatedFieldBuilderV3.build();
            }
            configChange.bitField0_ = 0;
            onBuilt();
            return configChange;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m641clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m654setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m632clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m635clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m656setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m622addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m648mergeFrom(Message message) {
            if (message instanceof ConfigChange) {
                return mergeFrom((ConfigChange) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ConfigChange configChange) {
            if (configChange == ConfigChange.getDefaultInstance()) {
                return this;
            }
            if (!configChange.getElement().isEmpty()) {
                this.element_ = configChange.element_;
                onChanged();
            }
            if (!configChange.getOldValue().isEmpty()) {
                this.oldValue_ = configChange.oldValue_;
                onChanged();
            }
            if (!configChange.getNewValue().isEmpty()) {
                this.newValue_ = configChange.newValue_;
                onChanged();
            }
            if (configChange.changeType_ != 0) {
                setChangeTypeValue(configChange.getChangeTypeValue());
            }
            if (this.advicesBuilder_ == null) {
                if (!configChange.advices_.isEmpty()) {
                    if (this.advices_.isEmpty()) {
                        this.advices_ = configChange.advices_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureAdvicesIsMutable();
                        this.advices_.addAll(configChange.advices_);
                    }
                    onChanged();
                }
            } else if (!configChange.advices_.isEmpty()) {
                if (!this.advicesBuilder_.isEmpty()) {
                    this.advicesBuilder_.addAllMessages(configChange.advices_);
                } else {
                    this.advicesBuilder_.dispose();
                    this.advicesBuilder_ = null;
                    this.advices_ = configChange.advices_;
                    this.bitField0_ &= -17;
                    this.advicesBuilder_ = ConfigChange.alwaysUseFieldBuilders ? getAdvicesFieldBuilder() : null;
                }
            }
            m652mergeUnknownFields(configChange.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.api.ConfigChange.Builder m649mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.ConfigChange.access$1200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.ConfigChange r3 = (com.google.api.ConfigChange) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.ConfigChange r4 = (com.google.api.ConfigChange) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.ConfigChange.Builder.m649mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.ConfigChange$Builder");
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public String getElement() {
            Object obj = this.element_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.element_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setElement(String str) {
            str.getClass();
            this.element_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ByteString getElementBytes() {
            Object obj = this.element_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.element_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setElementBytes(ByteString byteString) {
            byteString.getClass();
            ConfigChange.checkByteStringIsUtf8(byteString);
            this.element_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearElement() {
            this.element_ = ConfigChange.getDefaultInstance().getElement();
            onChanged();
            return this;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public String getOldValue() {
            Object obj = this.oldValue_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.oldValue_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setOldValue(String str) {
            str.getClass();
            this.oldValue_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ByteString getOldValueBytes() {
            Object obj = this.oldValue_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.oldValue_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setOldValueBytes(ByteString byteString) {
            byteString.getClass();
            ConfigChange.checkByteStringIsUtf8(byteString);
            this.oldValue_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearOldValue() {
            this.oldValue_ = ConfigChange.getDefaultInstance().getOldValue();
            onChanged();
            return this;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public String getNewValue() {
            Object obj = this.newValue_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.newValue_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setNewValue(String str) {
            str.getClass();
            this.newValue_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ByteString getNewValueBytes() {
            Object obj = this.newValue_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.newValue_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNewValueBytes(ByteString byteString) {
            byteString.getClass();
            ConfigChange.checkByteStringIsUtf8(byteString);
            this.newValue_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearNewValue() {
            this.newValue_ = ConfigChange.getDefaultInstance().getNewValue();
            onChanged();
            return this;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ChangeType getChangeType() {
            ChangeType changeTypeValueOf = ChangeType.valueOf(this.changeType_);
            return changeTypeValueOf == null ? ChangeType.UNRECOGNIZED : changeTypeValueOf;
        }

        public Builder setChangeType(ChangeType changeType) {
            changeType.getClass();
            this.changeType_ = changeType.getNumber();
            onChanged();
            return this;
        }

        public Builder clearChangeType() {
            this.changeType_ = 0;
            onChanged();
            return this;
        }

        private void ensureAdvicesIsMutable() {
            if ((this.bitField0_ & 16) == 0) {
                this.advices_ = new ArrayList(this.advices_);
                this.bitField0_ |= 16;
            }
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public List<Advice> getAdvicesList() {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.advices_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public int getAdvicesCount() {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.advices_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public Advice getAdvices(int i) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.advices_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setAdvices(int i, Advice advice) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                advice.getClass();
                ensureAdvicesIsMutable();
                this.advices_.set(i, advice);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, advice);
            }
            return this;
        }

        public Builder setAdvices(int i, Advice.Builder builder) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                this.advices_.set(i, builder.m207build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m207build());
            }
            return this;
        }

        public Builder addAdvices(Advice advice) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                advice.getClass();
                ensureAdvicesIsMutable();
                this.advices_.add(advice);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(advice);
            }
            return this;
        }

        public Builder addAdvices(int i, Advice advice) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                advice.getClass();
                ensureAdvicesIsMutable();
                this.advices_.add(i, advice);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, advice);
            }
            return this;
        }

        public Builder addAdvices(Advice.Builder builder) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                this.advices_.add(builder.m207build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m207build());
            }
            return this;
        }

        public Builder addAdvices(int i, Advice.Builder builder) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                this.advices_.add(i, builder.m207build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m207build());
            }
            return this;
        }

        public Builder addAllAdvices(Iterable<? extends Advice> iterable) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.advices_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearAdvices() {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.advices_ = Collections.emptyList();
                this.bitField0_ &= -17;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeAdvices(int i) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                this.advices_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Advice.Builder getAdvicesBuilder(int i) {
            return getAdvicesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public AdviceOrBuilder getAdvicesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.advices_.get(i);
            }
            return (AdviceOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public List<? extends AdviceOrBuilder> getAdvicesOrBuilderList() {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.advices_);
        }

        public Advice.Builder addAdvicesBuilder() {
            return getAdvicesFieldBuilder().addBuilder(Advice.getDefaultInstance());
        }

        public Advice.Builder addAdvicesBuilder(int i) {
            return getAdvicesFieldBuilder().addBuilder(i, Advice.getDefaultInstance());
        }

        public List<Advice.Builder> getAdvicesBuilderList() {
            return getAdvicesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> getAdvicesFieldBuilder() {
            if (this.advicesBuilder_ == null) {
                this.advicesBuilder_ = new RepeatedFieldBuilderV3<>(this.advices_, (this.bitField0_ & 16) != 0, getParentForChildren(), isClean());
                this.advices_ = null;
            }
            return this.advicesBuilder_;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m658setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m652mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
