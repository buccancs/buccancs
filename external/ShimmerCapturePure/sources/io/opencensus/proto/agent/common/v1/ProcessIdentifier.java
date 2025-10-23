package io.opencensus.proto.agent.common.v1;

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
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class ProcessIdentifier extends GeneratedMessageV3 implements ProcessIdentifierOrBuilder {
    public static final int HOST_NAME_FIELD_NUMBER = 1;
    public static final int PID_FIELD_NUMBER = 2;
    public static final int START_TIMESTAMP_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final ProcessIdentifier DEFAULT_INSTANCE = new ProcessIdentifier();
    private static final Parser<ProcessIdentifier> PARSER = new AbstractParser<ProcessIdentifier>() { // from class: io.opencensus.proto.agent.common.v1.ProcessIdentifier.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ProcessIdentifier m36412parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ProcessIdentifier(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object hostName_;
    private byte memoizedIsInitialized;
    private int pid_;
    private Timestamp startTimestamp_;

    private ProcessIdentifier(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ProcessIdentifier() {
        this.memoizedIsInitialized = (byte) -1;
        this.hostName_ = "";
        this.pid_ = 0;
    }

    private ProcessIdentifier(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.hostName_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 16) {
                            this.pid_ = codedInputStream.readUInt32();
                        } else if (tag != 26) {
                            if (!parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            Timestamp timestamp = this.startTimestamp_;
                            Timestamp.Builder builder = timestamp != null ? timestamp.toBuilder() : null;
                            Timestamp message = codedInputStream.readMessage(Timestamp.parser(), extensionRegistryLite);
                            this.startTimestamp_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.startTimestamp_ = builder.buildPartial();
                            }
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

    public static ProcessIdentifier getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProcessIdentifier> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return CommonProto.internal_static_opencensus_proto_agent_common_v1_ProcessIdentifier_descriptor;
    }

    public static ProcessIdentifier parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ProcessIdentifier) PARSER.parseFrom(byteBuffer);
    }

    public static ProcessIdentifier parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProcessIdentifier) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ProcessIdentifier parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ProcessIdentifier) PARSER.parseFrom(byteString);
    }

    public static ProcessIdentifier parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProcessIdentifier) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ProcessIdentifier parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ProcessIdentifier) PARSER.parseFrom(bArr);
    }

    public static ProcessIdentifier parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProcessIdentifier) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ProcessIdentifier parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ProcessIdentifier parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ProcessIdentifier parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ProcessIdentifier parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ProcessIdentifier parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ProcessIdentifier parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m36410toBuilder();
    }

    public static Builder newBuilder(ProcessIdentifier processIdentifier) {
        return DEFAULT_INSTANCE.m36410toBuilder().mergeFrom(processIdentifier);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ProcessIdentifier m36405getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ProcessIdentifier> getParserForType() {
        return PARSER;
    }

    @Override // io.opencensus.proto.agent.common.v1.ProcessIdentifierOrBuilder
    public int getPid() {
        return this.pid_;
    }

    @Override // io.opencensus.proto.agent.common.v1.ProcessIdentifierOrBuilder
    public boolean hasStartTimestamp() {
        return this.startTimestamp_ != null;
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
        return CommonProto.internal_static_opencensus_proto_agent_common_v1_ProcessIdentifier_fieldAccessorTable.ensureFieldAccessorsInitialized(ProcessIdentifier.class, Builder.class);
    }

    @Override // io.opencensus.proto.agent.common.v1.ProcessIdentifierOrBuilder
    public String getHostName() {
        Object obj = this.hostName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.hostName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.opencensus.proto.agent.common.v1.ProcessIdentifierOrBuilder
    public ByteString getHostNameBytes() {
        Object obj = this.hostName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.hostName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.opencensus.proto.agent.common.v1.ProcessIdentifierOrBuilder
    public Timestamp getStartTimestamp() {
        Timestamp timestamp = this.startTimestamp_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    @Override // io.opencensus.proto.agent.common.v1.ProcessIdentifierOrBuilder
    public TimestampOrBuilder getStartTimestampOrBuilder() {
        return getStartTimestamp();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getHostNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.hostName_);
        }
        int i = this.pid_;
        if (i != 0) {
            codedOutputStream.writeUInt32(2, i);
        }
        if (this.startTimestamp_ != null) {
            codedOutputStream.writeMessage(3, getStartTimestamp());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getHostNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.hostName_) : 0;
        int i2 = this.pid_;
        if (i2 != 0) {
            iComputeStringSize += CodedOutputStream.computeUInt32Size(2, i2);
        }
        if (this.startTimestamp_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, getStartTimestamp());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProcessIdentifier)) {
            return super.equals(obj);
        }
        ProcessIdentifier processIdentifier = (ProcessIdentifier) obj;
        boolean z = getHostName().equals(processIdentifier.getHostName()) && getPid() == processIdentifier.getPid() && hasStartTimestamp() == processIdentifier.hasStartTimestamp();
        if (!hasStartTimestamp() ? z : !(!z || !getStartTimestamp().equals(processIdentifier.getStartTimestamp()))) {
            if (this.unknownFields.equals(processIdentifier.unknownFields)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getHostName().hashCode()) * 37) + 2) * 53) + getPid();
        if (hasStartTimestamp()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getStartTimestamp().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m36407newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m36410toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProcessIdentifierOrBuilder {
        private Object hostName_;
        private int pid_;
        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> startTimestampBuilder_;
        private Timestamp startTimestamp_;

        private Builder() {
            this.hostName_ = "";
            this.startTimestamp_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.hostName_ = "";
            this.startTimestamp_ = null;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return CommonProto.internal_static_opencensus_proto_agent_common_v1_ProcessIdentifier_descriptor;
        }

        @Override // io.opencensus.proto.agent.common.v1.ProcessIdentifierOrBuilder
        public int getPid() {
            return this.pid_;
        }

        public Builder setPid(int i) {
            this.pid_ = i;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.agent.common.v1.ProcessIdentifierOrBuilder
        public boolean hasStartTimestamp() {
            return (this.startTimestampBuilder_ == null && this.startTimestamp_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return CommonProto.internal_static_opencensus_proto_agent_common_v1_ProcessIdentifier_fieldAccessorTable.ensureFieldAccessorsInitialized(ProcessIdentifier.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ProcessIdentifier.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36421clear() {
            super.clear();
            this.hostName_ = "";
            this.pid_ = 0;
            if (this.startTimestampBuilder_ == null) {
                this.startTimestamp_ = null;
            } else {
                this.startTimestamp_ = null;
                this.startTimestampBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return CommonProto.internal_static_opencensus_proto_agent_common_v1_ProcessIdentifier_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ProcessIdentifier m36434getDefaultInstanceForType() {
            return ProcessIdentifier.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ProcessIdentifier m36415build() throws UninitializedMessageException {
            ProcessIdentifier processIdentifierM36417buildPartial = m36417buildPartial();
            if (processIdentifierM36417buildPartial.isInitialized()) {
                return processIdentifierM36417buildPartial;
            }
            throw newUninitializedMessageException(processIdentifierM36417buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ProcessIdentifier m36417buildPartial() {
            ProcessIdentifier processIdentifier = new ProcessIdentifier(this);
            processIdentifier.hostName_ = this.hostName_;
            processIdentifier.pid_ = this.pid_;
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                processIdentifier.startTimestamp_ = this.startTimestamp_;
            } else {
                processIdentifier.startTimestamp_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return processIdentifier;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36433clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36445setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36423clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36426clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36447setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36413addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m36438mergeFrom(Message message) {
            if (message instanceof ProcessIdentifier) {
                return mergeFrom((ProcessIdentifier) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ProcessIdentifier processIdentifier) {
            if (processIdentifier == ProcessIdentifier.getDefaultInstance()) {
                return this;
            }
            if (!processIdentifier.getHostName().isEmpty()) {
                this.hostName_ = processIdentifier.hostName_;
                onChanged();
            }
            if (processIdentifier.getPid() != 0) {
                setPid(processIdentifier.getPid());
            }
            if (processIdentifier.hasStartTimestamp()) {
                mergeStartTimestamp(processIdentifier.getStartTimestamp());
            }
            m36443mergeUnknownFields(processIdentifier.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.opencensus.proto.agent.common.v1.ProcessIdentifier.Builder m36439mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.opencensus.proto.agent.common.v1.ProcessIdentifier.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.opencensus.proto.agent.common.v1.ProcessIdentifier r3 = (io.opencensus.proto.agent.common.v1.ProcessIdentifier) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.opencensus.proto.agent.common.v1.ProcessIdentifier r4 = (io.opencensus.proto.agent.common.v1.ProcessIdentifier) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.proto.agent.common.v1.ProcessIdentifier.Builder.m36439mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.opencensus.proto.agent.common.v1.ProcessIdentifier$Builder");
        }

        @Override // io.opencensus.proto.agent.common.v1.ProcessIdentifierOrBuilder
        public String getHostName() {
            Object obj = this.hostName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.hostName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setHostName(String str) {
            str.getClass();
            this.hostName_ = str;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.agent.common.v1.ProcessIdentifierOrBuilder
        public ByteString getHostNameBytes() {
            Object obj = this.hostName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.hostName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setHostNameBytes(ByteString byteString) {
            byteString.getClass();
            ProcessIdentifier.checkByteStringIsUtf8(byteString);
            this.hostName_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearHostName() {
            this.hostName_ = ProcessIdentifier.getDefaultInstance().getHostName();
            onChanged();
            return this;
        }

        public Builder clearPid() {
            this.pid_ = 0;
            onChanged();
            return this;
        }

        @Override // io.opencensus.proto.agent.common.v1.ProcessIdentifierOrBuilder
        public Timestamp getStartTimestamp() {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Timestamp timestamp = this.startTimestamp_;
            return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
        }

        public Builder setStartTimestamp(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                timestamp.getClass();
                this.startTimestamp_ = timestamp;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(timestamp);
            }
            return this;
        }

        public Builder setStartTimestamp(Timestamp.Builder builder) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.startTimestamp_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeStartTimestamp(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                Timestamp timestamp2 = this.startTimestamp_;
                if (timestamp2 != null) {
                    this.startTimestamp_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
                } else {
                    this.startTimestamp_ = timestamp;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(timestamp);
            }
            return this;
        }

        public Builder clearStartTimestamp() {
            if (this.startTimestampBuilder_ == null) {
                this.startTimestamp_ = null;
                onChanged();
            } else {
                this.startTimestamp_ = null;
                this.startTimestampBuilder_ = null;
            }
            return this;
        }

        public Timestamp.Builder getStartTimestampBuilder() {
            onChanged();
            return getStartTimestampFieldBuilder().getBuilder();
        }

        @Override // io.opencensus.proto.agent.common.v1.ProcessIdentifierOrBuilder
        public TimestampOrBuilder getStartTimestampOrBuilder() {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Timestamp timestamp = this.startTimestamp_;
            return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
        }

        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> getStartTimestampFieldBuilder() {
            if (this.startTimestampBuilder_ == null) {
                this.startTimestampBuilder_ = new SingleFieldBuilderV3<>(getStartTimestamp(), getParentForChildren(), isClean());
                this.startTimestamp_ = null;
            }
            return this.startTimestampBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m36449setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m36443mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
