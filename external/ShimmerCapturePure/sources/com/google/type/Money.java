package com.google.type;

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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class Money extends GeneratedMessageV3 implements MoneyOrBuilder {
    public static final int CURRENCY_CODE_FIELD_NUMBER = 1;
    public static final int NANOS_FIELD_NUMBER = 3;
    public static final int UNITS_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final Money DEFAULT_INSTANCE = new Money();
    private static final Parser<Money> PARSER = new AbstractParser<Money>() { // from class: com.google.type.Money.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Money m4277parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Money(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object currencyCode_;
    private byte memoizedIsInitialized;
    private int nanos_;
    private long units_;

    private Money(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Money() {
        this.memoizedIsInitialized = (byte) -1;
        this.currencyCode_ = "";
    }

    private Money(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.currencyCode_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 16) {
                            this.units_ = codedInputStream.readInt64();
                        } else if (tag == 24) {
                            this.nanos_ = codedInputStream.readInt32();
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

    public static Money getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Money> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MoneyProto.internal_static_google_type_Money_descriptor;
    }

    public static Money parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Money) PARSER.parseFrom(byteBuffer);
    }

    public static Money parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Money) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Money parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Money) PARSER.parseFrom(byteString);
    }

    public static Money parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Money) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Money parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Money) PARSER.parseFrom(bArr);
    }

    public static Money parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Money) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Money parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Money parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Money parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Money parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Money parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Money parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m4275toBuilder();
    }

    public static Builder newBuilder(Money money) {
        return DEFAULT_INSTANCE.m4275toBuilder().mergeFrom(money);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Money m4270getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.type.MoneyOrBuilder
    public int getNanos() {
        return this.nanos_;
    }

    public Parser<Money> getParserForType() {
        return PARSER;
    }

    @Override // com.google.type.MoneyOrBuilder
    public long getUnits() {
        return this.units_;
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
        return MoneyProto.internal_static_google_type_Money_fieldAccessorTable.ensureFieldAccessorsInitialized(Money.class, Builder.class);
    }

    @Override // com.google.type.MoneyOrBuilder
    public String getCurrencyCode() {
        Object obj = this.currencyCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.currencyCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.type.MoneyOrBuilder
    public ByteString getCurrencyCodeBytes() {
        Object obj = this.currencyCode_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.currencyCode_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getCurrencyCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.currencyCode_);
        }
        long j = this.units_;
        if (j != 0) {
            codedOutputStream.writeInt64(2, j);
        }
        int i = this.nanos_;
        if (i != 0) {
            codedOutputStream.writeInt32(3, i);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getCurrencyCodeBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.currencyCode_) : 0;
        long j = this.units_;
        if (j != 0) {
            iComputeStringSize += CodedOutputStream.computeInt64Size(2, j);
        }
        int i2 = this.nanos_;
        if (i2 != 0) {
            iComputeStringSize += CodedOutputStream.computeInt32Size(3, i2);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Money)) {
            return super.equals(obj);
        }
        Money money = (Money) obj;
        return getCurrencyCode().equals(money.getCurrencyCode()) && getUnits() == money.getUnits() && getNanos() == money.getNanos() && this.unknownFields.equals(money.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCurrencyCode().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getUnits())) * 37) + 3) * 53) + getNanos()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m4272newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m4275toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MoneyOrBuilder {
        private Object currencyCode_;
        private int nanos_;
        private long units_;

        private Builder() {
            this.currencyCode_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.currencyCode_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MoneyProto.internal_static_google_type_Money_descriptor;
        }

        @Override // com.google.type.MoneyOrBuilder
        public int getNanos() {
            return this.nanos_;
        }

        public Builder setNanos(int i) {
            this.nanos_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.type.MoneyOrBuilder
        public long getUnits() {
            return this.units_;
        }

        public Builder setUnits(long j) {
            this.units_ = j;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MoneyProto.internal_static_google_type_Money_fieldAccessorTable.ensureFieldAccessorsInitialized(Money.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Money.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4286clear() {
            super.clear();
            this.currencyCode_ = "";
            this.units_ = 0L;
            this.nanos_ = 0;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return MoneyProto.internal_static_google_type_Money_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Money m4299getDefaultInstanceForType() {
            return Money.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Money m4280build() throws UninitializedMessageException {
            Money moneyM4282buildPartial = m4282buildPartial();
            if (moneyM4282buildPartial.isInitialized()) {
                return moneyM4282buildPartial;
            }
            throw newUninitializedMessageException(moneyM4282buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Money m4282buildPartial() {
            Money money = new Money(this);
            money.currencyCode_ = this.currencyCode_;
            money.units_ = this.units_;
            money.nanos_ = this.nanos_;
            onBuilt();
            return money;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4298clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4310setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4288clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4291clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4312setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4278addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4303mergeFrom(Message message) {
            if (message instanceof Money) {
                return mergeFrom((Money) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Money money) {
            if (money == Money.getDefaultInstance()) {
                return this;
            }
            if (!money.getCurrencyCode().isEmpty()) {
                this.currencyCode_ = money.currencyCode_;
                onChanged();
            }
            if (money.getUnits() != 0) {
                setUnits(money.getUnits());
            }
            if (money.getNanos() != 0) {
                setNanos(money.getNanos());
            }
            m4308mergeUnknownFields(money.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.type.Money.Builder m4304mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.type.Money.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.type.Money r3 = (com.google.type.Money) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.type.Money r4 = (com.google.type.Money) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.type.Money.Builder.m4304mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.type.Money$Builder");
        }

        @Override // com.google.type.MoneyOrBuilder
        public String getCurrencyCode() {
            Object obj = this.currencyCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.currencyCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setCurrencyCode(String str) {
            str.getClass();
            this.currencyCode_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.type.MoneyOrBuilder
        public ByteString getCurrencyCodeBytes() {
            Object obj = this.currencyCode_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.currencyCode_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setCurrencyCodeBytes(ByteString byteString) {
            byteString.getClass();
            Money.checkByteStringIsUtf8(byteString);
            this.currencyCode_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearCurrencyCode() {
            this.currencyCode_ = Money.getDefaultInstance().getCurrencyCode();
            onChanged();
            return this;
        }

        public Builder clearUnits() {
            this.units_ = 0L;
            onChanged();
            return this;
        }

        public Builder clearNanos() {
            this.nanos_ = 0;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m4314setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m4308mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
