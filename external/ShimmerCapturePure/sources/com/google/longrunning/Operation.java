package com.google.longrunning;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class Operation extends GeneratedMessageV3 implements OperationOrBuilder {
    public static final int DONE_FIELD_NUMBER = 3;
    public static final int ERROR_FIELD_NUMBER = 4;
    public static final int METADATA_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int RESPONSE_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private static final Operation DEFAULT_INSTANCE = new Operation();
    private static final Parser<Operation> PARSER = new AbstractParser<Operation>() { // from class: com.google.longrunning.Operation.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Operation m3261parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Operation(codedInputStream, extensionRegistryLite);
        }
    };
    private boolean done_;
    private byte memoizedIsInitialized;
    private Any metadata_;
    private volatile Object name_;
    private int resultCase_;
    private Object result_;

    private Operation(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.resultCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Operation() {
        this.resultCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    private Operation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Any.Builder builder;
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
                            if (tag != 10) {
                                if (tag == 18) {
                                    Any any = this.metadata_;
                                    builder = any != null ? any.toBuilder() : null;
                                    Any message = codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                                    this.metadata_ = message;
                                    if (builder != null) {
                                        builder.mergeFrom(message);
                                        this.metadata_ = builder.buildPartial();
                                    }
                                } else if (tag == 24) {
                                    this.done_ = codedInputStream.readBool();
                                } else if (tag == 34) {
                                    builder = this.resultCase_ == 4 ? ((Status) this.result_).m3997toBuilder() : null;
                                    MessageLite message2 = codedInputStream.readMessage(Status.parser(), extensionRegistryLite);
                                    this.result_ = message2;
                                    if (builder != null) {
                                        builder.mergeFrom((Status) message2);
                                        this.result_ = builder.m4004buildPartial();
                                    }
                                    this.resultCase_ = 4;
                                } else if (tag == 42) {
                                    builder = this.resultCase_ == 5 ? ((Any) this.result_).toBuilder() : null;
                                    Any message3 = codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                                    this.result_ = message3;
                                    if (builder != null) {
                                        builder.mergeFrom(message3);
                                        this.result_ = builder.buildPartial();
                                    }
                                    this.resultCase_ = 5;
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                this.name_ = codedInputStream.readStringRequireUtf8();
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

    public static Operation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Operation> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return OperationsProto.internal_static_google_longrunning_Operation_descriptor;
    }

    public static Operation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Operation) PARSER.parseFrom(byteBuffer);
    }

    public static Operation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Operation) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Operation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Operation) PARSER.parseFrom(byteString);
    }

    public static Operation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Operation) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Operation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Operation) PARSER.parseFrom(bArr);
    }

    public static Operation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Operation) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Operation parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Operation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Operation parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Operation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Operation parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Operation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3260toBuilder();
    }

    public static Builder newBuilder(Operation operation) {
        return DEFAULT_INSTANCE.m3260toBuilder().mergeFrom(operation);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Operation m3255getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public boolean getDone() {
        return this.done_;
    }

    public Parser<Operation> getParserForType() {
        return PARSER;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public boolean hasError() {
        return this.resultCase_ == 4;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public boolean hasMetadata() {
        return this.metadata_ != null;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public boolean hasResponse() {
        return this.resultCase_ == 5;
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
        return OperationsProto.internal_static_google_longrunning_Operation_fieldAccessorTable.ensureFieldAccessorsInitialized(Operation.class, Builder.class);
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public ResultCase getResultCase() {
        return ResultCase.forNumber(this.resultCase_);
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public Any getMetadata() {
        Any any = this.metadata_;
        return any == null ? Any.getDefaultInstance() : any;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public AnyOrBuilder getMetadataOrBuilder() {
        return getMetadata();
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public Status getError() {
        if (this.resultCase_ == 4) {
            return (Status) this.result_;
        }
        return Status.getDefaultInstance();
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public StatusOrBuilder getErrorOrBuilder() {
        if (this.resultCase_ == 4) {
            return (Status) this.result_;
        }
        return Status.getDefaultInstance();
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public Any getResponse() {
        if (this.resultCase_ == 5) {
            return (Any) this.result_;
        }
        return Any.getDefaultInstance();
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public AnyOrBuilder getResponseOrBuilder() {
        if (this.resultCase_ == 5) {
            return (Any) this.result_;
        }
        return Any.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.metadata_ != null) {
            codedOutputStream.writeMessage(2, getMetadata());
        }
        boolean z = this.done_;
        if (z) {
            codedOutputStream.writeBool(3, z);
        }
        if (this.resultCase_ == 4) {
            codedOutputStream.writeMessage(4, (Status) this.result_);
        }
        if (this.resultCase_ == 5) {
            codedOutputStream.writeMessage(5, (Any) this.result_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (this.metadata_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, getMetadata());
        }
        boolean z = this.done_;
        if (z) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(3, z);
        }
        if (this.resultCase_ == 4) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(4, (Status) this.result_);
        }
        if (this.resultCase_ == 5) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(5, (Any) this.result_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Operation)) {
            return super.equals(obj);
        }
        Operation operation = (Operation) obj;
        if (!getName().equals(operation.getName()) || hasMetadata() != operation.hasMetadata()) {
            return false;
        }
        if ((hasMetadata() && !getMetadata().equals(operation.getMetadata())) || getDone() != operation.getDone() || !getResultCase().equals(operation.getResultCase())) {
            return false;
        }
        int i = this.resultCase_;
        if (i == 4) {
            if (!getError().equals(operation.getError())) {
                return false;
            }
        } else if (i == 5 && !getResponse().equals(operation.getResponse())) {
            return false;
        }
        return this.unknownFields.equals(operation.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (hasMetadata()) {
            iHashCode2 = (((iHashCode2 * 37) + 2) * 53) + getMetadata().hashCode();
        }
        int iHashBoolean = (((iHashCode2 * 37) + 3) * 53) + Internal.hashBoolean(getDone());
        int i2 = this.resultCase_;
        if (i2 == 4) {
            i = ((iHashBoolean * 37) + 4) * 53;
            iHashCode = getError().hashCode();
        } else {
            if (i2 == 5) {
                i = ((iHashBoolean * 37) + 5) * 53;
                iHashCode = getResponse().hashCode();
            }
            int iHashCode3 = (iHashBoolean * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode3;
            return iHashCode3;
        }
        iHashBoolean = i + iHashCode;
        int iHashCode32 = (iHashBoolean * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode32;
        return iHashCode32;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3258newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3260toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m3257newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ResultCase implements Internal.EnumLite {
        ERROR(4),
        RESPONSE(5),
        RESULT_NOT_SET(0);

        private final int value;

        ResultCase(int i) {
            this.value = i;
        }

        public static ResultCase forNumber(int i) {
            if (i == 0) {
                return RESULT_NOT_SET;
            }
            if (i == 4) {
                return ERROR;
            }
            if (i != 5) {
                return null;
            }
            return RESPONSE;
        }

        @Deprecated
        public static ResultCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OperationOrBuilder {
        private boolean done_;
        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> errorBuilder_;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> metadataBuilder_;
        private Any metadata_;
        private Object name_;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> responseBuilder_;
        private int resultCase_;
        private Object result_;

        private Builder() {
            this.resultCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.resultCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return OperationsProto.internal_static_google_longrunning_Operation_descriptor;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public boolean getDone() {
            return this.done_;
        }

        public Builder setDone(boolean z) {
            this.done_ = z;
            onChanged();
            return this;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public boolean hasError() {
            return this.resultCase_ == 4;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public boolean hasMetadata() {
            return (this.metadataBuilder_ == null && this.metadata_ == null) ? false : true;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public boolean hasResponse() {
            return this.resultCase_ == 5;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return OperationsProto.internal_static_google_longrunning_Operation_fieldAccessorTable.ensureFieldAccessorsInitialized(Operation.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Operation.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3271clear() {
            super.clear();
            this.name_ = "";
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            this.done_ = false;
            this.resultCase_ = 0;
            this.result_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return OperationsProto.internal_static_google_longrunning_Operation_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Operation m3284getDefaultInstanceForType() {
            return Operation.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Operation m3265build() throws UninitializedMessageException {
            Operation operationM3267buildPartial = m3267buildPartial();
            if (operationM3267buildPartial.isInitialized()) {
                return operationM3267buildPartial;
            }
            throw newUninitializedMessageException(operationM3267buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Operation m3267buildPartial() {
            Operation operation = new Operation(this);
            operation.name_ = this.name_;
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                operation.metadata_ = this.metadata_;
            } else {
                operation.metadata_ = singleFieldBuilderV3.build();
            }
            operation.done_ = this.done_;
            if (this.resultCase_ == 4) {
                SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV32 = this.errorBuilder_;
                if (singleFieldBuilderV32 == null) {
                    operation.result_ = this.result_;
                } else {
                    operation.result_ = singleFieldBuilderV32.build();
                }
            }
            if (this.resultCase_ == 5) {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV33 = this.responseBuilder_;
                if (singleFieldBuilderV33 == null) {
                    operation.result_ = this.result_;
                } else {
                    operation.result_ = singleFieldBuilderV33.build();
                }
            }
            operation.resultCase_ = this.resultCase_;
            onBuilt();
            return operation;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3282clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3295setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3273clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3276clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3297setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3263addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3289mergeFrom(Message message) {
            if (message instanceof Operation) {
                return mergeFrom((Operation) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Operation operation) {
            if (operation == Operation.getDefaultInstance()) {
                return this;
            }
            if (!operation.getName().isEmpty()) {
                this.name_ = operation.name_;
                onChanged();
            }
            if (operation.hasMetadata()) {
                mergeMetadata(operation.getMetadata());
            }
            if (operation.getDone()) {
                setDone(operation.getDone());
            }
            int i = AnonymousClass2.$SwitchMap$com$google$longrunning$Operation$ResultCase[operation.getResultCase().ordinal()];
            if (i == 1) {
                mergeError(operation.getError());
            } else if (i == 2) {
                mergeResponse(operation.getResponse());
            }
            m3293mergeUnknownFields(operation.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.longrunning.Operation.Builder m3290mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.longrunning.Operation.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.longrunning.Operation r3 = (com.google.longrunning.Operation) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.longrunning.Operation r4 = (com.google.longrunning.Operation) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.longrunning.Operation.Builder.m3290mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.longrunning.Operation$Builder");
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public ResultCase getResultCase() {
            return ResultCase.forNumber(this.resultCase_);
        }

        public Builder clearResult() {
            this.resultCase_ = 0;
            this.result_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.longrunning.OperationOrBuilder
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

        @Override // com.google.longrunning.OperationOrBuilder
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
            Operation.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = Operation.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public Any getMetadata() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Any any = this.metadata_;
            return any == null ? Any.getDefaultInstance() : any;
        }

        public Builder setMetadata(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                any.getClass();
                this.metadata_ = any;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(any);
            }
            return this;
        }

        public Builder setMetadata(Any.Builder builder) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.metadata_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeMetadata(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Any any2 = this.metadata_;
                if (any2 != null) {
                    this.metadata_ = Any.newBuilder(any2).mergeFrom(any).buildPartial();
                } else {
                    this.metadata_ = any;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(any);
            }
            return this;
        }

        public Builder clearMetadata() {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
                onChanged();
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        public Any.Builder getMetadataBuilder() {
            onChanged();
            return getMetadataFieldBuilder().getBuilder();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public AnyOrBuilder getMetadataOrBuilder() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Any any = this.metadata_;
            return any == null ? Any.getDefaultInstance() : any;
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getMetadataFieldBuilder() {
            if (this.metadataBuilder_ == null) {
                this.metadataBuilder_ = new SingleFieldBuilderV3<>(getMetadata(), getParentForChildren(), isClean());
                this.metadata_ = null;
            }
            return this.metadataBuilder_;
        }

        public Builder clearDone() {
            this.done_ = false;
            onChanged();
            return this;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public Status getError() {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.resultCase_ == 4) {
                    return (Status) this.result_;
                }
                return Status.getDefaultInstance();
            }
            if (this.resultCase_ == 4) {
                return singleFieldBuilderV3.getMessage();
            }
            return Status.getDefaultInstance();
        }

        public Builder setError(Status status) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorBuilder_;
            if (singleFieldBuilderV3 == null) {
                status.getClass();
                this.result_ = status;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(status);
            }
            this.resultCase_ = 4;
            return this;
        }

        public Builder setError(Status.Builder builder) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.result_ = builder.m4002build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m4002build());
            }
            this.resultCase_ = 4;
            return this;
        }

        public Builder mergeError(Status status) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.resultCase_ != 4 || this.result_ == Status.getDefaultInstance()) {
                    this.result_ = status;
                } else {
                    this.result_ = Status.newBuilder((Status) this.result_).mergeFrom(status).m4004buildPartial();
                }
                onChanged();
            } else {
                if (this.resultCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(status);
                }
                this.errorBuilder_.setMessage(status);
            }
            this.resultCase_ = 4;
            return this;
        }

        public Builder clearError() {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.resultCase_ == 4) {
                    this.resultCase_ = 0;
                    this.result_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.resultCase_ == 4) {
                this.resultCase_ = 0;
                this.result_ = null;
                onChanged();
            }
            return this;
        }

        public Status.Builder getErrorBuilder() {
            return getErrorFieldBuilder().getBuilder();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public StatusOrBuilder getErrorOrBuilder() {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3;
            int i = this.resultCase_;
            if (i == 4 && (singleFieldBuilderV3 = this.errorBuilder_) != null) {
                return (StatusOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 4) {
                return (Status) this.result_;
            }
            return Status.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> getErrorFieldBuilder() {
            if (this.errorBuilder_ == null) {
                if (this.resultCase_ != 4) {
                    this.result_ = Status.getDefaultInstance();
                }
                this.errorBuilder_ = new SingleFieldBuilderV3<>((Status) this.result_, getParentForChildren(), isClean());
                this.result_ = null;
            }
            this.resultCase_ = 4;
            onChanged();
            return this.errorBuilder_;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public Any getResponse() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.resultCase_ == 5) {
                    return (Any) this.result_;
                }
                return Any.getDefaultInstance();
            }
            if (this.resultCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return Any.getDefaultInstance();
        }

        public Builder setResponse(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                any.getClass();
                this.result_ = any;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(any);
            }
            this.resultCase_ = 5;
            return this;
        }

        public Builder setResponse(Any.Builder builder) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.result_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.resultCase_ = 5;
            return this;
        }

        public Builder mergeResponse(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.resultCase_ != 5 || this.result_ == Any.getDefaultInstance()) {
                    this.result_ = any;
                } else {
                    this.result_ = Any.newBuilder((Any) this.result_).mergeFrom(any).buildPartial();
                }
                onChanged();
            } else {
                if (this.resultCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(any);
                }
                this.responseBuilder_.setMessage(any);
            }
            this.resultCase_ = 5;
            return this;
        }

        public Builder clearResponse() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.resultCase_ == 5) {
                    this.resultCase_ = 0;
                    this.result_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.resultCase_ == 5) {
                this.resultCase_ = 0;
                this.result_ = null;
                onChanged();
            }
            return this;
        }

        public Any.Builder getResponseBuilder() {
            return getResponseFieldBuilder().getBuilder();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public AnyOrBuilder getResponseOrBuilder() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3;
            int i = this.resultCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.responseBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (Any) this.result_;
            }
            return Any.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getResponseFieldBuilder() {
            if (this.responseBuilder_ == null) {
                if (this.resultCase_ != 5) {
                    this.result_ = Any.getDefaultInstance();
                }
                this.responseBuilder_ = new SingleFieldBuilderV3<>((Any) this.result_, getParentForChildren(), isClean());
                this.result_ = null;
            }
            this.resultCase_ = 5;
            onChanged();
            return this.responseBuilder_;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3299setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3293mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: com.google.longrunning.Operation$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$longrunning$Operation$ResultCase;

        static {
            int[] iArr = new int[ResultCase.values().length];
            $SwitchMap$com$google$longrunning$Operation$ResultCase = iArr;
            try {
                iArr[ResultCase.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$longrunning$Operation$ResultCase[ResultCase.RESPONSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$longrunning$Operation$ResultCase[ResultCase.RESULT_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
