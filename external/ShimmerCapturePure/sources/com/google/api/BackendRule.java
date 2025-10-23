package com.google.api;

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

/* loaded from: classes.dex */
public final class BackendRule extends GeneratedMessageV3 implements BackendRuleOrBuilder {
    public static final int ADDRESS_FIELD_NUMBER = 2;
    public static final int DEADLINE_FIELD_NUMBER = 3;
    public static final int JWT_AUDIENCE_FIELD_NUMBER = 7;
    public static final int MIN_DEADLINE_FIELD_NUMBER = 4;
    public static final int OPERATION_DEADLINE_FIELD_NUMBER = 5;
    public static final int PATH_TRANSLATION_FIELD_NUMBER = 6;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final BackendRule DEFAULT_INSTANCE = new BackendRule();
    private static final Parser<BackendRule> PARSER = new AbstractParser<BackendRule>() { // from class: com.google.api.BackendRule.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public BackendRule m479parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BackendRule(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object address_;
    private int authenticationCase_;
    private Object authentication_;
    private double deadline_;
    private byte memoizedIsInitialized;
    private double minDeadline_;
    private double operationDeadline_;
    private int pathTranslation_;
    private volatile Object selector_;

    private BackendRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.authenticationCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private BackendRule() {
        this.authenticationCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.selector_ = "";
        this.address_ = "";
        this.pathTranslation_ = 0;
    }

    private BackendRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.selector_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            this.address_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 25) {
                            this.deadline_ = codedInputStream.readDouble();
                        } else if (tag == 33) {
                            this.minDeadline_ = codedInputStream.readDouble();
                        } else if (tag == 41) {
                            this.operationDeadline_ = codedInputStream.readDouble();
                        } else if (tag == 48) {
                            this.pathTranslation_ = codedInputStream.readEnum();
                        } else if (tag == 58) {
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            this.authenticationCase_ = 7;
                            this.authentication_ = stringRequireUtf8;
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

    public static BackendRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BackendRule> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BackendProto.internal_static_google_api_BackendRule_descriptor;
    }

    public static BackendRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BackendRule) PARSER.parseFrom(byteBuffer);
    }

    public static BackendRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BackendRule) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BackendRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BackendRule) PARSER.parseFrom(byteString);
    }

    public static BackendRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BackendRule) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BackendRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BackendRule) PARSER.parseFrom(bArr);
    }

    public static BackendRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BackendRule) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BackendRule parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BackendRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BackendRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BackendRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BackendRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BackendRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m478toBuilder();
    }

    public static Builder newBuilder(BackendRule backendRule) {
        return DEFAULT_INSTANCE.m478toBuilder().mergeFrom(backendRule);
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public double getDeadline() {
        return this.deadline_;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public BackendRule m473getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public double getMinDeadline() {
        return this.minDeadline_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public double getOperationDeadline() {
        return this.operationDeadline_;
    }

    public Parser<BackendRule> getParserForType() {
        return PARSER;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public int getPathTranslationValue() {
        return this.pathTranslation_;
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
        return BackendProto.internal_static_google_api_BackendRule_fieldAccessorTable.ensureFieldAccessorsInitialized(BackendRule.class, Builder.class);
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public AuthenticationCase getAuthenticationCase() {
        return AuthenticationCase.forNumber(this.authenticationCase_);
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public String getSelector() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.selector_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getSelectorBytes() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.selector_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public String getAddress() {
        Object obj = this.address_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.address_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getAddressBytes() {
        Object obj = this.address_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.address_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public PathTranslation getPathTranslation() {
        PathTranslation pathTranslationValueOf = PathTranslation.valueOf(this.pathTranslation_);
        return pathTranslationValueOf == null ? PathTranslation.UNRECOGNIZED : pathTranslationValueOf;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public String getJwtAudience() {
        String str = this.authenticationCase_ == 7 ? this.authentication_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.authenticationCase_ == 7) {
            this.authentication_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getJwtAudienceBytes() {
        String str = this.authenticationCase_ == 7 ? this.authentication_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.authenticationCase_ == 7) {
                this.authentication_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getSelectorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.selector_);
        }
        if (!getAddressBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.address_);
        }
        double d = this.deadline_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(3, d);
        }
        double d2 = this.minDeadline_;
        if (d2 != 0.0d) {
            codedOutputStream.writeDouble(4, d2);
        }
        double d3 = this.operationDeadline_;
        if (d3 != 0.0d) {
            codedOutputStream.writeDouble(5, d3);
        }
        if (this.pathTranslation_ != PathTranslation.PATH_TRANSLATION_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(6, this.pathTranslation_);
        }
        if (this.authenticationCase_ == 7) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.authentication_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getSelectorBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.selector_) : 0;
        if (!getAddressBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.address_);
        }
        double d = this.deadline_;
        if (d != 0.0d) {
            iComputeStringSize += CodedOutputStream.computeDoubleSize(3, d);
        }
        double d2 = this.minDeadline_;
        if (d2 != 0.0d) {
            iComputeStringSize += CodedOutputStream.computeDoubleSize(4, d2);
        }
        double d3 = this.operationDeadline_;
        if (d3 != 0.0d) {
            iComputeStringSize += CodedOutputStream.computeDoubleSize(5, d3);
        }
        if (this.pathTranslation_ != PathTranslation.PATH_TRANSLATION_UNSPECIFIED.getNumber()) {
            iComputeStringSize += CodedOutputStream.computeEnumSize(6, this.pathTranslation_);
        }
        if (this.authenticationCase_ == 7) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(7, this.authentication_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BackendRule)) {
            return super.equals(obj);
        }
        BackendRule backendRule = (BackendRule) obj;
        if (getSelector().equals(backendRule.getSelector()) && getAddress().equals(backendRule.getAddress()) && Double.doubleToLongBits(getDeadline()) == Double.doubleToLongBits(backendRule.getDeadline()) && Double.doubleToLongBits(getMinDeadline()) == Double.doubleToLongBits(backendRule.getMinDeadline()) && Double.doubleToLongBits(getOperationDeadline()) == Double.doubleToLongBits(backendRule.getOperationDeadline()) && this.pathTranslation_ == backendRule.pathTranslation_ && getAuthenticationCase().equals(backendRule.getAuthenticationCase())) {
            return (this.authenticationCase_ != 7 || getJwtAudience().equals(backendRule.getJwtAudience())) && this.unknownFields.equals(backendRule.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode()) * 37) + 2) * 53) + getAddress().hashCode()) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getDeadline()))) * 37) + 4) * 53) + Internal.hashLong(Double.doubleToLongBits(getMinDeadline()))) * 37) + 5) * 53) + Internal.hashLong(Double.doubleToLongBits(getOperationDeadline()))) * 37) + 6) * 53) + this.pathTranslation_;
        if (this.authenticationCase_ == 7) {
            iHashCode = (((iHashCode * 37) + 7) * 53) + getJwtAudience().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m476newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m478toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m475newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum PathTranslation implements ProtocolMessageEnum {
        PATH_TRANSLATION_UNSPECIFIED(0),
        CONSTANT_ADDRESS(1),
        APPEND_PATH_TO_ADDRESS(2),
        UNRECOGNIZED(-1);

        public static final int APPEND_PATH_TO_ADDRESS_VALUE = 2;
        public static final int CONSTANT_ADDRESS_VALUE = 1;
        public static final int PATH_TRANSLATION_UNSPECIFIED_VALUE = 0;
        private static final Internal.EnumLiteMap<PathTranslation> internalValueMap = new Internal.EnumLiteMap<PathTranslation>() { // from class: com.google.api.BackendRule.PathTranslation.1
            /* renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
            public PathTranslation m518findValueByNumber(int i) {
                return PathTranslation.forNumber(i);
            }
        };
        private static final PathTranslation[] VALUES = values();
        private final int value;

        PathTranslation(int i) {
            this.value = i;
        }

        public static PathTranslation forNumber(int i) {
            if (i == 0) {
                return PATH_TRANSLATION_UNSPECIFIED;
            }
            if (i == 1) {
                return CONSTANT_ADDRESS;
            }
            if (i != 2) {
                return null;
            }
            return APPEND_PATH_TO_ADDRESS;
        }

        public static Internal.EnumLiteMap<PathTranslation> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static PathTranslation valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) BackendRule.getDescriptor().getEnumTypes().get(0);
        }

        public static PathTranslation valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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

    public enum AuthenticationCase implements Internal.EnumLite {
        JWT_AUDIENCE(7),
        AUTHENTICATION_NOT_SET(0);

        private final int value;

        AuthenticationCase(int i) {
            this.value = i;
        }

        public static AuthenticationCase forNumber(int i) {
            if (i == 0) {
                return AUTHENTICATION_NOT_SET;
            }
            if (i != 7) {
                return null;
            }
            return JWT_AUDIENCE;
        }

        @Deprecated
        public static AuthenticationCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BackendRuleOrBuilder {
        private Object address_;
        private int authenticationCase_;
        private Object authentication_;
        private double deadline_;
        private double minDeadline_;
        private double operationDeadline_;
        private int pathTranslation_;
        private Object selector_;

        private Builder() {
            this.authenticationCase_ = 0;
            this.selector_ = "";
            this.address_ = "";
            this.pathTranslation_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.authenticationCase_ = 0;
            this.selector_ = "";
            this.address_ = "";
            this.pathTranslation_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BackendProto.internal_static_google_api_BackendRule_descriptor;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public double getDeadline() {
            return this.deadline_;
        }

        public Builder setDeadline(double d) {
            this.deadline_ = d;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public double getMinDeadline() {
            return this.minDeadline_;
        }

        public Builder setMinDeadline(double d) {
            this.minDeadline_ = d;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public double getOperationDeadline() {
            return this.operationDeadline_;
        }

        public Builder setOperationDeadline(double d) {
            this.operationDeadline_ = d;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public int getPathTranslationValue() {
            return this.pathTranslation_;
        }

        public Builder setPathTranslationValue(int i) {
            this.pathTranslation_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BackendProto.internal_static_google_api_BackendRule_fieldAccessorTable.ensureFieldAccessorsInitialized(BackendRule.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = BackendRule.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m489clear() {
            super.clear();
            this.selector_ = "";
            this.address_ = "";
            this.deadline_ = 0.0d;
            this.minDeadline_ = 0.0d;
            this.operationDeadline_ = 0.0d;
            this.pathTranslation_ = 0;
            this.authenticationCase_ = 0;
            this.authentication_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BackendProto.internal_static_google_api_BackendRule_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public BackendRule m502getDefaultInstanceForType() {
            return BackendRule.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public BackendRule m483build() throws UninitializedMessageException {
            BackendRule backendRuleM485buildPartial = m485buildPartial();
            if (backendRuleM485buildPartial.isInitialized()) {
                return backendRuleM485buildPartial;
            }
            throw newUninitializedMessageException(backendRuleM485buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public BackendRule m485buildPartial() {
            BackendRule backendRule = new BackendRule(this);
            backendRule.selector_ = this.selector_;
            backendRule.address_ = this.address_;
            backendRule.deadline_ = this.deadline_;
            backendRule.minDeadline_ = this.minDeadline_;
            backendRule.operationDeadline_ = this.operationDeadline_;
            backendRule.pathTranslation_ = this.pathTranslation_;
            if (this.authenticationCase_ == 7) {
                backendRule.authentication_ = this.authentication_;
            }
            backendRule.authenticationCase_ = this.authenticationCase_;
            onBuilt();
            return backendRule;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m500clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m513setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m491clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m494clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m515setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m481addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m507mergeFrom(Message message) {
            if (message instanceof BackendRule) {
                return mergeFrom((BackendRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BackendRule backendRule) {
            if (backendRule == BackendRule.getDefaultInstance()) {
                return this;
            }
            if (!backendRule.getSelector().isEmpty()) {
                this.selector_ = backendRule.selector_;
                onChanged();
            }
            if (!backendRule.getAddress().isEmpty()) {
                this.address_ = backendRule.address_;
                onChanged();
            }
            if (backendRule.getDeadline() != 0.0d) {
                setDeadline(backendRule.getDeadline());
            }
            if (backendRule.getMinDeadline() != 0.0d) {
                setMinDeadline(backendRule.getMinDeadline());
            }
            if (backendRule.getOperationDeadline() != 0.0d) {
                setOperationDeadline(backendRule.getOperationDeadline());
            }
            if (backendRule.pathTranslation_ != 0) {
                setPathTranslationValue(backendRule.getPathTranslationValue());
            }
            if (AnonymousClass2.$SwitchMap$com$google$api$BackendRule$AuthenticationCase[backendRule.getAuthenticationCase().ordinal()] == 1) {
                this.authenticationCase_ = 7;
                this.authentication_ = backendRule.authentication_;
                onChanged();
            }
            m511mergeUnknownFields(backendRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.api.BackendRule.Builder m508mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.BackendRule.access$1300()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.BackendRule r3 = (com.google.api.BackendRule) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.BackendRule r4 = (com.google.api.BackendRule) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.BackendRule.Builder.m508mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.BackendRule$Builder");
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public AuthenticationCase getAuthenticationCase() {
            return AuthenticationCase.forNumber(this.authenticationCase_);
        }

        public Builder clearAuthentication() {
            this.authenticationCase_ = 0;
            this.authentication_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getSelector() {
            Object obj = this.selector_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.selector_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setSelector(String str) {
            str.getClass();
            this.selector_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public ByteString getSelectorBytes() {
            Object obj = this.selector_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.selector_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            byteString.getClass();
            BackendRule.checkByteStringIsUtf8(byteString);
            this.selector_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearSelector() {
            this.selector_ = BackendRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getAddress() {
            Object obj = this.address_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.address_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setAddress(String str) {
            str.getClass();
            this.address_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public ByteString getAddressBytes() {
            Object obj = this.address_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.address_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAddressBytes(ByteString byteString) {
            byteString.getClass();
            BackendRule.checkByteStringIsUtf8(byteString);
            this.address_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearAddress() {
            this.address_ = BackendRule.getDefaultInstance().getAddress();
            onChanged();
            return this;
        }

        public Builder clearDeadline() {
            this.deadline_ = 0.0d;
            onChanged();
            return this;
        }

        public Builder clearMinDeadline() {
            this.minDeadline_ = 0.0d;
            onChanged();
            return this;
        }

        public Builder clearOperationDeadline() {
            this.operationDeadline_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public PathTranslation getPathTranslation() {
            PathTranslation pathTranslationValueOf = PathTranslation.valueOf(this.pathTranslation_);
            return pathTranslationValueOf == null ? PathTranslation.UNRECOGNIZED : pathTranslationValueOf;
        }

        public Builder setPathTranslation(PathTranslation pathTranslation) {
            pathTranslation.getClass();
            this.pathTranslation_ = pathTranslation.getNumber();
            onChanged();
            return this;
        }

        public Builder clearPathTranslation() {
            this.pathTranslation_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getJwtAudience() {
            String str = this.authenticationCase_ == 7 ? this.authentication_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.authenticationCase_ == 7) {
                    this.authentication_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setJwtAudience(String str) {
            str.getClass();
            this.authenticationCase_ = 7;
            this.authentication_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public ByteString getJwtAudienceBytes() {
            String str = this.authenticationCase_ == 7 ? this.authentication_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.authenticationCase_ == 7) {
                    this.authentication_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setJwtAudienceBytes(ByteString byteString) {
            byteString.getClass();
            BackendRule.checkByteStringIsUtf8(byteString);
            this.authenticationCase_ = 7;
            this.authentication_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearJwtAudience() {
            if (this.authenticationCase_ == 7) {
                this.authenticationCase_ = 0;
                this.authentication_ = null;
                onChanged();
            }
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m517setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m511mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: com.google.api.BackendRule$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$api$BackendRule$AuthenticationCase;

        static {
            int[] iArr = new int[AuthenticationCase.values().length];
            $SwitchMap$com$google$api$BackendRule$AuthenticationCase = iArr;
            try {
                iArr[AuthenticationCase.JWT_AUDIENCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$api$BackendRule$AuthenticationCase[AuthenticationCase.AUTHENTICATION_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
