package com.google.type;

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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class Date extends GeneratedMessageV3 implements DateOrBuilder {
    public static final int DAY_FIELD_NUMBER = 3;
    public static final int MONTH_FIELD_NUMBER = 2;
    public static final int YEAR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final Date DEFAULT_INSTANCE = new Date();
    private static final Parser<Date> PARSER = new AbstractParser<Date>() { // from class: com.google.type.Date.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Date m4092parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Date(codedInputStream, extensionRegistryLite);
        }
    };
    private int day_;
    private byte memoizedIsInitialized;
    private int month_;
    private int year_;

    private Date(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Date() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private Date(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.year_ = codedInputStream.readInt32();
                        } else if (tag == 16) {
                            this.month_ = codedInputStream.readInt32();
                        } else if (tag == 24) {
                            this.day_ = codedInputStream.readInt32();
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

    public static Date getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Date> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DateProto.internal_static_google_type_Date_descriptor;
    }

    public static Date parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Date) PARSER.parseFrom(byteBuffer);
    }

    public static Date parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Date) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Date parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Date) PARSER.parseFrom(byteString);
    }

    public static Date parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Date) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Date parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Date) PARSER.parseFrom(bArr);
    }

    public static Date parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Date) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Date parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Date parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Date parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Date parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Date parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Date parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m4090toBuilder();
    }

    public static Builder newBuilder(Date date) {
        return DEFAULT_INSTANCE.m4090toBuilder().mergeFrom(date);
    }

    @Override // com.google.type.DateOrBuilder
    public int getDay() {
        return this.day_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Date m4085getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.type.DateOrBuilder
    public int getMonth() {
        return this.month_;
    }

    public Parser<Date> getParserForType() {
        return PARSER;
    }

    @Override // com.google.type.DateOrBuilder
    public int getYear() {
        return this.year_;
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
        return DateProto.internal_static_google_type_Date_fieldAccessorTable.ensureFieldAccessorsInitialized(Date.class, Builder.class);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i = this.year_;
        if (i != 0) {
            codedOutputStream.writeInt32(1, i);
        }
        int i2 = this.month_;
        if (i2 != 0) {
            codedOutputStream.writeInt32(2, i2);
        }
        int i3 = this.day_;
        if (i3 != 0) {
            codedOutputStream.writeInt32(3, i3);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = this.year_;
        int iComputeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) : 0;
        int i3 = this.month_;
        if (i3 != 0) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(2, i3);
        }
        int i4 = this.day_;
        if (i4 != 0) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(3, i4);
        }
        int serializedSize = iComputeInt32Size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Date)) {
            return super.equals(obj);
        }
        Date date = (Date) obj;
        return getYear() == date.getYear() && getMonth() == date.getMonth() && getDay() == date.getDay() && this.unknownFields.equals(date.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getYear()) * 37) + 2) * 53) + getMonth()) * 37) + 3) * 53) + getDay()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m4087newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m4090toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DateOrBuilder {
        private int day_;
        private int month_;
        private int year_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DateProto.internal_static_google_type_Date_descriptor;
        }

        @Override // com.google.type.DateOrBuilder
        public int getDay() {
            return this.day_;
        }

        public Builder setDay(int i) {
            this.day_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.type.DateOrBuilder
        public int getMonth() {
            return this.month_;
        }

        public Builder setMonth(int i) {
            this.month_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.type.DateOrBuilder
        public int getYear() {
            return this.year_;
        }

        public Builder setYear(int i) {
            this.year_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DateProto.internal_static_google_type_Date_fieldAccessorTable.ensureFieldAccessorsInitialized(Date.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Date.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4101clear() {
            super.clear();
            this.year_ = 0;
            this.month_ = 0;
            this.day_ = 0;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return DateProto.internal_static_google_type_Date_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Date m4114getDefaultInstanceForType() {
            return Date.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Date m4095build() throws UninitializedMessageException {
            Date dateM4097buildPartial = m4097buildPartial();
            if (dateM4097buildPartial.isInitialized()) {
                return dateM4097buildPartial;
            }
            throw newUninitializedMessageException(dateM4097buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Date m4097buildPartial() {
            Date date = new Date(this);
            date.year_ = this.year_;
            date.month_ = this.month_;
            date.day_ = this.day_;
            onBuilt();
            return date;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4113clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4125setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4103clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4106clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4127setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4093addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4118mergeFrom(Message message) {
            if (message instanceof Date) {
                return mergeFrom((Date) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Date date) {
            if (date == Date.getDefaultInstance()) {
                return this;
            }
            if (date.getYear() != 0) {
                setYear(date.getYear());
            }
            if (date.getMonth() != 0) {
                setMonth(date.getMonth());
            }
            if (date.getDay() != 0) {
                setDay(date.getDay());
            }
            m4123mergeUnknownFields(date.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.type.Date.Builder m4119mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.type.Date.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.type.Date r3 = (com.google.type.Date) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.type.Date r4 = (com.google.type.Date) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.type.Date.Builder.m4119mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.type.Date$Builder");
        }

        public Builder clearYear() {
            this.year_ = 0;
            onChanged();
            return this;
        }

        public Builder clearMonth() {
            this.month_ = 0;
            onChanged();
            return this;
        }

        public Builder clearDay() {
            this.day_ = 0;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m4129setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m4123mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
