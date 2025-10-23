package com.google.longrunning;

import com.google.longrunning.Operation;
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

/* loaded from: classes2.dex */
public final class ListOperationsResponse extends GeneratedMessageV3 implements ListOperationsResponseOrBuilder {
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    public static final int OPERATIONS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final ListOperationsResponse DEFAULT_INSTANCE = new ListOperationsResponse();
    private static final Parser<ListOperationsResponse> PARSER = new AbstractParser<ListOperationsResponse>() { // from class: com.google.longrunning.ListOperationsResponse.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ListOperationsResponse m3215parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListOperationsResponse(codedInputStream, extensionRegistryLite);
        }
    };
    private int bitField0_;
    private byte memoizedIsInitialized;
    private volatile Object nextPageToken_;
    private List<Operation> operations_;

    private ListOperationsResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListOperationsResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.operations_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    private ListOperationsResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.operations_ = new ArrayList();
                                z2 |= true;
                            }
                            this.operations_.add(codedInputStream.readMessage(Operation.parser(), extensionRegistryLite));
                        } else if (tag == 18) {
                            this.nextPageToken_ = codedInputStream.readStringRequireUtf8();
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
                    this.operations_ = Collections.unmodifiableList(this.operations_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ListOperationsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListOperationsResponse> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return OperationsProto.internal_static_google_longrunning_ListOperationsResponse_descriptor;
    }

    public static ListOperationsResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) PARSER.parseFrom(byteBuffer);
    }

    public static ListOperationsResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) PARSER.parseFrom(byteString);
    }

    public static ListOperationsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) PARSER.parseFrom(bArr);
    }

    public static ListOperationsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListOperationsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListOperationsResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListOperationsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListOperationsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3214toBuilder();
    }

    public static Builder newBuilder(ListOperationsResponse listOperationsResponse) {
        return DEFAULT_INSTANCE.m3214toBuilder().mergeFrom(listOperationsResponse);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ListOperationsResponse m3209getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.longrunning.ListOperationsResponseOrBuilder
    public List<Operation> getOperationsList() {
        return this.operations_;
    }

    @Override // com.google.longrunning.ListOperationsResponseOrBuilder
    public List<? extends OperationOrBuilder> getOperationsOrBuilderList() {
        return this.operations_;
    }

    public Parser<ListOperationsResponse> getParserForType() {
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
        return OperationsProto.internal_static_google_longrunning_ListOperationsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListOperationsResponse.class, Builder.class);
    }

    @Override // com.google.longrunning.ListOperationsResponseOrBuilder
    public int getOperationsCount() {
        return this.operations_.size();
    }

    @Override // com.google.longrunning.ListOperationsResponseOrBuilder
    public Operation getOperations(int i) {
        return this.operations_.get(i);
    }

    @Override // com.google.longrunning.ListOperationsResponseOrBuilder
    public OperationOrBuilder getOperationsOrBuilder(int i) {
        return this.operations_.get(i);
    }

    @Override // com.google.longrunning.ListOperationsResponseOrBuilder
    public String getNextPageToken() {
        Object obj = this.nextPageToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.nextPageToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.longrunning.ListOperationsResponseOrBuilder
    public ByteString getNextPageTokenBytes() {
        Object obj = this.nextPageToken_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.nextPageToken_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.operations_.size(); i++) {
            codedOutputStream.writeMessage(1, this.operations_.get(i));
        }
        if (!getNextPageTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.nextPageToken_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = 0;
        for (int i2 = 0; i2 < this.operations_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(1, this.operations_.get(i2));
        }
        if (!getNextPageTokenBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.nextPageToken_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListOperationsResponse)) {
            return super.equals(obj);
        }
        ListOperationsResponse listOperationsResponse = (ListOperationsResponse) obj;
        return getOperationsList().equals(listOperationsResponse.getOperationsList()) && getNextPageToken().equals(listOperationsResponse.getNextPageToken()) && this.unknownFields.equals(listOperationsResponse.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getOperationsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getOperationsList().hashCode();
        }
        int iHashCode2 = (((((iHashCode * 37) + 2) * 53) + getNextPageToken().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3212newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3214toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m3211newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListOperationsResponseOrBuilder {
        private int bitField0_;
        private Object nextPageToken_;
        private RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> operationsBuilder_;
        private List<Operation> operations_;

        private Builder() {
            this.operations_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.operations_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return OperationsProto.internal_static_google_longrunning_ListOperationsResponse_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return OperationsProto.internal_static_google_longrunning_ListOperationsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListOperationsResponse.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (ListOperationsResponse.alwaysUseFieldBuilders) {
                getOperationsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3225clear() {
            super.clear();
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.operations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.nextPageToken_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return OperationsProto.internal_static_google_longrunning_ListOperationsResponse_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ListOperationsResponse m3238getDefaultInstanceForType() {
            return ListOperationsResponse.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ListOperationsResponse m3219build() throws UninitializedMessageException {
            ListOperationsResponse listOperationsResponseM3221buildPartial = m3221buildPartial();
            if (listOperationsResponseM3221buildPartial.isInitialized()) {
                return listOperationsResponseM3221buildPartial;
            }
            throw newUninitializedMessageException(listOperationsResponseM3221buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ListOperationsResponse m3221buildPartial() {
            ListOperationsResponse listOperationsResponse = new ListOperationsResponse(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.operations_ = Collections.unmodifiableList(this.operations_);
                    this.bitField0_ &= -2;
                }
                listOperationsResponse.operations_ = this.operations_;
            } else {
                listOperationsResponse.operations_ = repeatedFieldBuilderV3.build();
            }
            listOperationsResponse.nextPageToken_ = this.nextPageToken_;
            listOperationsResponse.bitField0_ = 0;
            onBuilt();
            return listOperationsResponse;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3236clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3249setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3227clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3230clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3251setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3217addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3243mergeFrom(Message message) {
            if (message instanceof ListOperationsResponse) {
                return mergeFrom((ListOperationsResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListOperationsResponse listOperationsResponse) {
            if (listOperationsResponse == ListOperationsResponse.getDefaultInstance()) {
                return this;
            }
            if (this.operationsBuilder_ == null) {
                if (!listOperationsResponse.operations_.isEmpty()) {
                    if (this.operations_.isEmpty()) {
                        this.operations_ = listOperationsResponse.operations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureOperationsIsMutable();
                        this.operations_.addAll(listOperationsResponse.operations_);
                    }
                    onChanged();
                }
            } else if (!listOperationsResponse.operations_.isEmpty()) {
                if (!this.operationsBuilder_.isEmpty()) {
                    this.operationsBuilder_.addAllMessages(listOperationsResponse.operations_);
                } else {
                    this.operationsBuilder_.dispose();
                    this.operationsBuilder_ = null;
                    this.operations_ = listOperationsResponse.operations_;
                    this.bitField0_ &= -2;
                    this.operationsBuilder_ = ListOperationsResponse.alwaysUseFieldBuilders ? getOperationsFieldBuilder() : null;
                }
            }
            if (!listOperationsResponse.getNextPageToken().isEmpty()) {
                this.nextPageToken_ = listOperationsResponse.nextPageToken_;
                onChanged();
            }
            m3247mergeUnknownFields(listOperationsResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.longrunning.ListOperationsResponse.Builder m3244mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.longrunning.ListOperationsResponse.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.longrunning.ListOperationsResponse r3 = (com.google.longrunning.ListOperationsResponse) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.longrunning.ListOperationsResponse r4 = (com.google.longrunning.ListOperationsResponse) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.longrunning.ListOperationsResponse.Builder.m3244mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.longrunning.ListOperationsResponse$Builder");
        }

        private void ensureOperationsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.operations_ = new ArrayList(this.operations_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.longrunning.ListOperationsResponseOrBuilder
        public List<Operation> getOperationsList() {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.operations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.longrunning.ListOperationsResponseOrBuilder
        public int getOperationsCount() {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.operations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.longrunning.ListOperationsResponseOrBuilder
        public Operation getOperations(int i) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.operations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setOperations(int i, Operation operation) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                operation.getClass();
                ensureOperationsIsMutable();
                this.operations_.set(i, operation);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, operation);
            }
            return this;
        }

        public Builder setOperations(int i, Operation.Builder builder) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                this.operations_.set(i, builder.m3265build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m3265build());
            }
            return this;
        }

        public Builder addOperations(Operation operation) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                operation.getClass();
                ensureOperationsIsMutable();
                this.operations_.add(operation);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(operation);
            }
            return this;
        }

        public Builder addOperations(int i, Operation operation) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                operation.getClass();
                ensureOperationsIsMutable();
                this.operations_.add(i, operation);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, operation);
            }
            return this;
        }

        public Builder addOperations(Operation.Builder builder) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                this.operations_.add(builder.m3265build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m3265build());
            }
            return this;
        }

        public Builder addOperations(int i, Operation.Builder builder) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                this.operations_.add(i, builder.m3265build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m3265build());
            }
            return this;
        }

        public Builder addAllOperations(Iterable<? extends Operation> iterable) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.operations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearOperations() {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.operations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeOperations(int i) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                this.operations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Operation.Builder getOperationsBuilder(int i) {
            return getOperationsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.longrunning.ListOperationsResponseOrBuilder
        public OperationOrBuilder getOperationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.operations_.get(i);
            }
            return (OperationOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.longrunning.ListOperationsResponseOrBuilder
        public List<? extends OperationOrBuilder> getOperationsOrBuilderList() {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.operations_);
        }

        public Operation.Builder addOperationsBuilder() {
            return getOperationsFieldBuilder().addBuilder(Operation.getDefaultInstance());
        }

        public Operation.Builder addOperationsBuilder(int i) {
            return getOperationsFieldBuilder().addBuilder(i, Operation.getDefaultInstance());
        }

        public List<Operation.Builder> getOperationsBuilderList() {
            return getOperationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> getOperationsFieldBuilder() {
            if (this.operationsBuilder_ == null) {
                this.operationsBuilder_ = new RepeatedFieldBuilderV3<>(this.operations_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.operations_ = null;
            }
            return this.operationsBuilder_;
        }

        @Override // com.google.longrunning.ListOperationsResponseOrBuilder
        public String getNextPageToken() {
            Object obj = this.nextPageToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.nextPageToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setNextPageToken(String str) {
            str.getClass();
            this.nextPageToken_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.longrunning.ListOperationsResponseOrBuilder
        public ByteString getNextPageTokenBytes() {
            Object obj = this.nextPageToken_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.nextPageToken_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNextPageTokenBytes(ByteString byteString) {
            byteString.getClass();
            ListOperationsResponse.checkByteStringIsUtf8(byteString);
            this.nextPageToken_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearNextPageToken() {
            this.nextPageToken_ = ListOperationsResponse.getDefaultInstance().getNextPageToken();
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3253setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m3247mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
