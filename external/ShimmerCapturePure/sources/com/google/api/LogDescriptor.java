package com.google.api;

import com.google.api.LabelDescriptor;
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
public final class LogDescriptor extends GeneratedMessageV3 implements LogDescriptorOrBuilder {
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 4;
    public static final int LABELS_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final LogDescriptor DEFAULT_INSTANCE = new LogDescriptor();
    private static final Parser<LogDescriptor> PARSER = new AbstractParser<LogDescriptor>() { // from class: com.google.api.LogDescriptor.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public LogDescriptor m1503parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new LogDescriptor(codedInputStream, extensionRegistryLite);
        }
    };
    private int bitField0_;
    private volatile Object description_;
    private volatile Object displayName_;
    private List<LabelDescriptor> labels_;
    private byte memoizedIsInitialized;
    private volatile Object name_;

    private LogDescriptor(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private LogDescriptor() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.labels_ = Collections.emptyList();
        this.description_ = "";
        this.displayName_ = "";
    }

    private LogDescriptor(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            if ((i & 2) == 0) {
                                this.labels_ = new ArrayList();
                                i |= 2;
                            }
                            this.labels_.add(codedInputStream.readMessage(LabelDescriptor.parser(), extensionRegistryLite));
                        } else if (tag == 26) {
                            this.description_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 34) {
                            this.displayName_ = codedInputStream.readStringRequireUtf8();
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
                if ((i & 2) != 0) {
                    this.labels_ = Collections.unmodifiableList(this.labels_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static LogDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LogDescriptor> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LogProto.internal_static_google_api_LogDescriptor_descriptor;
    }

    public static LogDescriptor parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (LogDescriptor) PARSER.parseFrom(byteBuffer);
    }

    public static LogDescriptor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LogDescriptor) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static LogDescriptor parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (LogDescriptor) PARSER.parseFrom(byteString);
    }

    public static LogDescriptor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LogDescriptor) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static LogDescriptor parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (LogDescriptor) PARSER.parseFrom(bArr);
    }

    public static LogDescriptor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LogDescriptor) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static LogDescriptor parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static LogDescriptor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LogDescriptor parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static LogDescriptor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LogDescriptor parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static LogDescriptor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m1502toBuilder();
    }

    public static Builder newBuilder(LogDescriptor logDescriptor) {
        return DEFAULT_INSTANCE.m1502toBuilder().mergeFrom(logDescriptor);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public LogDescriptor m1497getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public List<LabelDescriptor> getLabelsList() {
        return this.labels_;
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList() {
        return this.labels_;
    }

    public Parser<LogDescriptor> getParserForType() {
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
        return LogProto.internal_static_google_api_LogDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(LogDescriptor.class, Builder.class);
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public int getLabelsCount() {
        return this.labels_.size();
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public LabelDescriptor getLabels(int i) {
        return this.labels_.get(i);
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public LabelDescriptorOrBuilder getLabelsOrBuilder(int i) {
        return this.labels_.get(i);
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public String getDescription() {
        Object obj = this.description_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.description_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public ByteString getDescriptionBytes() {
        Object obj = this.description_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public String getDisplayName() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.displayName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.LogDescriptorOrBuilder
    public ByteString getDisplayNameBytes() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.displayName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        for (int i = 0; i < this.labels_.size(); i++) {
            codedOutputStream.writeMessage(2, this.labels_.get(i));
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.description_);
        }
        if (!getDisplayNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.displayName_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        for (int i2 = 0; i2 < this.labels_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, this.labels_.get(i2));
        }
        if (!getDescriptionBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.description_);
        }
        if (!getDisplayNameBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.displayName_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogDescriptor)) {
            return super.equals(obj);
        }
        LogDescriptor logDescriptor = (LogDescriptor) obj;
        return getName().equals(logDescriptor.getName()) && getLabelsList().equals(logDescriptor.getLabelsList()) && getDescription().equals(logDescriptor.getDescription()) && getDisplayName().equals(logDescriptor.getDisplayName()) && this.unknownFields.equals(logDescriptor.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (getLabelsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getLabelsList().hashCode();
        }
        int iHashCode2 = (((((((((iHashCode * 37) + 3) * 53) + getDescription().hashCode()) * 37) + 4) * 53) + getDisplayName().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m1500newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m1502toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1499newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LogDescriptorOrBuilder {
        private int bitField0_;
        private Object description_;
        private Object displayName_;
        private RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> labelsBuilder_;
        private List<LabelDescriptor> labels_;
        private Object name_;

        private Builder() {
            this.name_ = "";
            this.labels_ = Collections.emptyList();
            this.description_ = "";
            this.displayName_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.labels_ = Collections.emptyList();
            this.description_ = "";
            this.displayName_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LogProto.internal_static_google_api_LogDescriptor_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LogProto.internal_static_google_api_LogDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(LogDescriptor.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (LogDescriptor.alwaysUseFieldBuilders) {
                getLabelsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1513clear() {
            super.clear();
            this.name_ = "";
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.labels_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.description_ = "";
            this.displayName_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return LogProto.internal_static_google_api_LogDescriptor_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LogDescriptor m1526getDefaultInstanceForType() {
            return LogDescriptor.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LogDescriptor m1507build() throws UninitializedMessageException {
            LogDescriptor logDescriptorM1509buildPartial = m1509buildPartial();
            if (logDescriptorM1509buildPartial.isInitialized()) {
                return logDescriptorM1509buildPartial;
            }
            throw newUninitializedMessageException(logDescriptorM1509buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LogDescriptor m1509buildPartial() {
            LogDescriptor logDescriptor = new LogDescriptor(this);
            logDescriptor.name_ = this.name_;
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) != 0) {
                    this.labels_ = Collections.unmodifiableList(this.labels_);
                    this.bitField0_ &= -3;
                }
                logDescriptor.labels_ = this.labels_;
            } else {
                logDescriptor.labels_ = repeatedFieldBuilderV3.build();
            }
            logDescriptor.description_ = this.description_;
            logDescriptor.displayName_ = this.displayName_;
            logDescriptor.bitField0_ = 0;
            onBuilt();
            return logDescriptor;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1524clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1537setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1515clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1518clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1539setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1505addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1531mergeFrom(Message message) {
            if (message instanceof LogDescriptor) {
                return mergeFrom((LogDescriptor) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(LogDescriptor logDescriptor) {
            if (logDescriptor == LogDescriptor.getDefaultInstance()) {
                return this;
            }
            if (!logDescriptor.getName().isEmpty()) {
                this.name_ = logDescriptor.name_;
                onChanged();
            }
            if (this.labelsBuilder_ == null) {
                if (!logDescriptor.labels_.isEmpty()) {
                    if (this.labels_.isEmpty()) {
                        this.labels_ = logDescriptor.labels_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureLabelsIsMutable();
                        this.labels_.addAll(logDescriptor.labels_);
                    }
                    onChanged();
                }
            } else if (!logDescriptor.labels_.isEmpty()) {
                if (!this.labelsBuilder_.isEmpty()) {
                    this.labelsBuilder_.addAllMessages(logDescriptor.labels_);
                } else {
                    this.labelsBuilder_.dispose();
                    this.labelsBuilder_ = null;
                    this.labels_ = logDescriptor.labels_;
                    this.bitField0_ &= -3;
                    this.labelsBuilder_ = LogDescriptor.alwaysUseFieldBuilders ? getLabelsFieldBuilder() : null;
                }
            }
            if (!logDescriptor.getDescription().isEmpty()) {
                this.description_ = logDescriptor.description_;
                onChanged();
            }
            if (!logDescriptor.getDisplayName().isEmpty()) {
                this.displayName_ = logDescriptor.displayName_;
                onChanged();
            }
            m1535mergeUnknownFields(logDescriptor.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.api.LogDescriptor.Builder m1532mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.LogDescriptor.access$1100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.LogDescriptor r3 = (com.google.api.LogDescriptor) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.LogDescriptor r4 = (com.google.api.LogDescriptor) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.LogDescriptor.Builder.m1532mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.LogDescriptor$Builder");
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setName(String str) {
            str.getClass();
            this.name_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNameBytes(ByteString byteString) {
            byteString.getClass();
            LogDescriptor.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = LogDescriptor.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        private void ensureLabelsIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.labels_ = new ArrayList(this.labels_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public List<LabelDescriptor> getLabelsList() {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.labels_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public int getLabelsCount() {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.labels_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public LabelDescriptor getLabels(int i) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.labels_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setLabels(int i, LabelDescriptor labelDescriptor) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                labelDescriptor.getClass();
                ensureLabelsIsMutable();
                this.labels_.set(i, labelDescriptor);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, labelDescriptor);
            }
            return this;
        }

        public Builder setLabels(int i, LabelDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                this.labels_.set(i, builder.m1459build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m1459build());
            }
            return this;
        }

        public Builder addLabels(LabelDescriptor labelDescriptor) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                labelDescriptor.getClass();
                ensureLabelsIsMutable();
                this.labels_.add(labelDescriptor);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(labelDescriptor);
            }
            return this;
        }

        public Builder addLabels(int i, LabelDescriptor labelDescriptor) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                labelDescriptor.getClass();
                ensureLabelsIsMutable();
                this.labels_.add(i, labelDescriptor);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, labelDescriptor);
            }
            return this;
        }

        public Builder addLabels(LabelDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                this.labels_.add(builder.m1459build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m1459build());
            }
            return this;
        }

        public Builder addLabels(int i, LabelDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                this.labels_.add(i, builder.m1459build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m1459build());
            }
            return this;
        }

        public Builder addAllLabels(Iterable<? extends LabelDescriptor> iterable) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.labels_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearLabels() {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.labels_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeLabels(int i) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                this.labels_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public LabelDescriptor.Builder getLabelsBuilder(int i) {
            return getLabelsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public LabelDescriptorOrBuilder getLabelsOrBuilder(int i) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.labels_.get(i);
            }
            return (LabelDescriptorOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList() {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.labels_);
        }

        public LabelDescriptor.Builder addLabelsBuilder() {
            return getLabelsFieldBuilder().addBuilder(LabelDescriptor.getDefaultInstance());
        }

        public LabelDescriptor.Builder addLabelsBuilder(int i) {
            return getLabelsFieldBuilder().addBuilder(i, LabelDescriptor.getDefaultInstance());
        }

        public List<LabelDescriptor.Builder> getLabelsBuilderList() {
            return getLabelsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> getLabelsFieldBuilder() {
            if (this.labelsBuilder_ == null) {
                this.labelsBuilder_ = new RepeatedFieldBuilderV3<>(this.labels_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.labels_ = null;
            }
            return this.labelsBuilder_;
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public String getDescription() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.description_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setDescription(String str) {
            str.getClass();
            this.description_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.description_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            byteString.getClass();
            LogDescriptor.checkByteStringIsUtf8(byteString);
            this.description_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearDescription() {
            this.description_ = LogDescriptor.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public String getDisplayName() {
            Object obj = this.displayName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.displayName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setDisplayName(String str) {
            str.getClass();
            this.displayName_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.LogDescriptorOrBuilder
        public ByteString getDisplayNameBytes() {
            Object obj = this.displayName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.displayName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            byteString.getClass();
            LogDescriptor.checkByteStringIsUtf8(byteString);
            this.displayName_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearDisplayName() {
            this.displayName_ = LogDescriptor.getDefaultInstance().getDisplayName();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1541setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1535mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
