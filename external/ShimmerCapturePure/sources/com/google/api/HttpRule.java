package com.google.api;

import com.google.api.CustomHttpPattern;
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
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class HttpRule extends GeneratedMessageV3 implements HttpRuleOrBuilder {
    public static final int ADDITIONAL_BINDINGS_FIELD_NUMBER = 11;
    public static final int BODY_FIELD_NUMBER = 7;
    public static final int CUSTOM_FIELD_NUMBER = 8;
    public static final int DELETE_FIELD_NUMBER = 5;
    public static final int GET_FIELD_NUMBER = 2;
    public static final int PATCH_FIELD_NUMBER = 6;
    public static final int POST_FIELD_NUMBER = 4;
    public static final int PUT_FIELD_NUMBER = 3;
    public static final int RESPONSE_BODY_FIELD_NUMBER = 12;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final HttpRule DEFAULT_INSTANCE = new HttpRule();
    private static final Parser<HttpRule> PARSER = new AbstractParser<HttpRule>() { // from class: com.google.api.HttpRule.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public HttpRule m1409parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HttpRule(codedInputStream, extensionRegistryLite);
        }
    };
    private List<HttpRule> additionalBindings_;
    private int bitField0_;
    private volatile Object body_;
    private byte memoizedIsInitialized;
    private int patternCase_;
    private Object pattern_;
    private volatile Object responseBody_;
    private volatile Object selector_;

    private HttpRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.patternCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private HttpRule() {
        this.patternCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.selector_ = "";
        this.body_ = "";
        this.responseBody_ = "";
        this.additionalBindings_ = Collections.emptyList();
    }

    private HttpRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        switch (tag) {
                            case 0:
                                z = true;
                            case 10:
                                this.selector_ = codedInputStream.readStringRequireUtf8();
                            case 18:
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 2;
                                this.pattern_ = stringRequireUtf8;
                            case 26:
                                String stringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 3;
                                this.pattern_ = stringRequireUtf82;
                            case 34:
                                String stringRequireUtf83 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 4;
                                this.pattern_ = stringRequireUtf83;
                            case 42:
                                String stringRequireUtf84 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 5;
                                this.pattern_ = stringRequireUtf84;
                            case 50:
                                String stringRequireUtf85 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 6;
                                this.pattern_ = stringRequireUtf85;
                            case 58:
                                this.body_ = codedInputStream.readStringRequireUtf8();
                            case 66:
                                CustomHttpPattern.Builder builderM807toBuilder = this.patternCase_ == 8 ? ((CustomHttpPattern) this.pattern_).m807toBuilder() : null;
                                MessageLite message = codedInputStream.readMessage(CustomHttpPattern.parser(), extensionRegistryLite);
                                this.pattern_ = message;
                                if (builderM807toBuilder != null) {
                                    builderM807toBuilder.mergeFrom((CustomHttpPattern) message);
                                    this.pattern_ = builderM807toBuilder.m814buildPartial();
                                }
                                this.patternCase_ = 8;
                            case RESET_TO_DEFAULT_CONFIGURATION_COMMAND_VALUE:
                                if ((i & 512) == 0) {
                                    this.additionalBindings_ = new ArrayList();
                                    i |= 512;
                                }
                                this.additionalBindings_.add(codedInputStream.readMessage(parser(), extensionRegistryLite));
                            case 98:
                                this.responseBody_ = codedInputStream.readStringRequireUtf8();
                            default:
                                if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    z = true;
                                }
                        }
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    }
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if ((i & 512) != 0) {
                    this.additionalBindings_ = Collections.unmodifiableList(this.additionalBindings_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static HttpRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpRule> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HttpProto.internal_static_google_api_HttpRule_descriptor;
    }

    public static HttpRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HttpRule) PARSER.parseFrom(byteBuffer);
    }

    public static HttpRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpRule) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HttpRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HttpRule) PARSER.parseFrom(byteString);
    }

    public static HttpRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpRule) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HttpRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HttpRule) PARSER.parseFrom(bArr);
    }

    public static HttpRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpRule) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HttpRule parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HttpRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HttpRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HttpRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m1408toBuilder();
    }

    public static Builder newBuilder(HttpRule httpRule) {
        return DEFAULT_INSTANCE.m1408toBuilder().mergeFrom(httpRule);
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public List<HttpRule> getAdditionalBindingsList() {
        return this.additionalBindings_;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public List<? extends HttpRuleOrBuilder> getAdditionalBindingsOrBuilderList() {
        return this.additionalBindings_;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public HttpRule m1403getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<HttpRule> getParserForType() {
        return PARSER;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public boolean hasCustom() {
        return this.patternCase_ == 8;
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
        return HttpProto.internal_static_google_api_HttpRule_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpRule.class, Builder.class);
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public PatternCase getPatternCase() {
        return PatternCase.forNumber(this.patternCase_);
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getSelector() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.selector_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getSelectorBytes() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.selector_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getGet() {
        String str = this.patternCase_ == 2 ? this.pattern_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.patternCase_ == 2) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getGetBytes() {
        String str = this.patternCase_ == 2 ? this.pattern_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.patternCase_ == 2) {
                this.pattern_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getPut() {
        String str = this.patternCase_ == 3 ? this.pattern_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.patternCase_ == 3) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getPutBytes() {
        String str = this.patternCase_ == 3 ? this.pattern_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.patternCase_ == 3) {
                this.pattern_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getPost() {
        String str = this.patternCase_ == 4 ? this.pattern_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.patternCase_ == 4) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getPostBytes() {
        String str = this.patternCase_ == 4 ? this.pattern_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.patternCase_ == 4) {
                this.pattern_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getDelete() {
        String str = this.patternCase_ == 5 ? this.pattern_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.patternCase_ == 5) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getDeleteBytes() {
        String str = this.patternCase_ == 5 ? this.pattern_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.patternCase_ == 5) {
                this.pattern_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getPatch() {
        String str = this.patternCase_ == 6 ? this.pattern_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.patternCase_ == 6) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getPatchBytes() {
        String str = this.patternCase_ == 6 ? this.pattern_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.patternCase_ == 6) {
                this.pattern_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public CustomHttpPattern getCustom() {
        if (this.patternCase_ == 8) {
            return (CustomHttpPattern) this.pattern_;
        }
        return CustomHttpPattern.getDefaultInstance();
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public CustomHttpPatternOrBuilder getCustomOrBuilder() {
        if (this.patternCase_ == 8) {
            return (CustomHttpPattern) this.pattern_;
        }
        return CustomHttpPattern.getDefaultInstance();
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getBody() {
        Object obj = this.body_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.body_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getBodyBytes() {
        Object obj = this.body_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.body_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getResponseBody() {
        Object obj = this.responseBody_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.responseBody_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getResponseBodyBytes() {
        Object obj = this.responseBody_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.responseBody_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public int getAdditionalBindingsCount() {
        return this.additionalBindings_.size();
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public HttpRule getAdditionalBindings(int i) {
        return this.additionalBindings_.get(i);
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public HttpRuleOrBuilder getAdditionalBindingsOrBuilder(int i) {
        return this.additionalBindings_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getSelectorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.selector_);
        }
        if (this.patternCase_ == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.pattern_);
        }
        if (this.patternCase_ == 3) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.pattern_);
        }
        if (this.patternCase_ == 4) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.pattern_);
        }
        if (this.patternCase_ == 5) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.pattern_);
        }
        if (this.patternCase_ == 6) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.pattern_);
        }
        if (!getBodyBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.body_);
        }
        if (this.patternCase_ == 8) {
            codedOutputStream.writeMessage(8, (CustomHttpPattern) this.pattern_);
        }
        for (int i = 0; i < this.additionalBindings_.size(); i++) {
            codedOutputStream.writeMessage(11, this.additionalBindings_.get(i));
        }
        if (!getResponseBodyBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 12, this.responseBody_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getSelectorBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.selector_) : 0;
        if (this.patternCase_ == 2) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.pattern_);
        }
        if (this.patternCase_ == 3) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.pattern_);
        }
        if (this.patternCase_ == 4) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.pattern_);
        }
        if (this.patternCase_ == 5) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(5, this.pattern_);
        }
        if (this.patternCase_ == 6) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(6, this.pattern_);
        }
        if (!getBodyBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(7, this.body_);
        }
        if (this.patternCase_ == 8) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(8, (CustomHttpPattern) this.pattern_);
        }
        for (int i2 = 0; i2 < this.additionalBindings_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(11, this.additionalBindings_.get(i2));
        }
        if (!getResponseBodyBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(12, this.responseBody_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HttpRule)) {
            return super.equals(obj);
        }
        HttpRule httpRule = (HttpRule) obj;
        if (!getSelector().equals(httpRule.getSelector()) || !getBody().equals(httpRule.getBody()) || !getResponseBody().equals(httpRule.getResponseBody()) || !getAdditionalBindingsList().equals(httpRule.getAdditionalBindingsList()) || !getPatternCase().equals(httpRule.getPatternCase())) {
            return false;
        }
        int i = this.patternCase_;
        if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        if (i == 6) {
                            if (!getPatch().equals(httpRule.getPatch())) {
                                return false;
                            }
                        } else if (i == 8 && !getCustom().equals(httpRule.getCustom())) {
                            return false;
                        }
                    } else if (!getDelete().equals(httpRule.getDelete())) {
                        return false;
                    }
                } else if (!getPost().equals(httpRule.getPost())) {
                    return false;
                }
            } else if (!getPut().equals(httpRule.getPut())) {
                return false;
            }
        } else if (!getGet().equals(httpRule.getGet())) {
            return false;
        }
        return this.unknownFields.equals(httpRule.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode()) * 37) + 7) * 53) + getBody().hashCode()) * 37) + 12) * 53) + getResponseBody().hashCode();
        if (getAdditionalBindingsCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 11) * 53) + getAdditionalBindingsList().hashCode();
        }
        int i2 = this.patternCase_;
        if (i2 == 2) {
            i = ((iHashCode2 * 37) + 2) * 53;
            iHashCode = getGet().hashCode();
        } else if (i2 == 3) {
            i = ((iHashCode2 * 37) + 3) * 53;
            iHashCode = getPut().hashCode();
        } else if (i2 == 4) {
            i = ((iHashCode2 * 37) + 4) * 53;
            iHashCode = getPost().hashCode();
        } else if (i2 == 5) {
            i = ((iHashCode2 * 37) + 5) * 53;
            iHashCode = getDelete().hashCode();
        } else if (i2 == 6) {
            i = ((iHashCode2 * 37) + 6) * 53;
            iHashCode = getPatch().hashCode();
        } else {
            if (i2 == 8) {
                i = ((iHashCode2 * 37) + 8) * 53;
                iHashCode = getCustom().hashCode();
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

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m1406newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m1408toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1405newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum PatternCase implements Internal.EnumLite {
        GET(2),
        PUT(3),
        POST(4),
        DELETE(5),
        PATCH(6),
        CUSTOM(8),
        PATTERN_NOT_SET(0);

        private final int value;

        PatternCase(int i) {
            this.value = i;
        }

        public static PatternCase forNumber(int i) {
            if (i == 0) {
                return PATTERN_NOT_SET;
            }
            if (i == 8) {
                return CUSTOM;
            }
            if (i == 2) {
                return GET;
            }
            if (i == 3) {
                return PUT;
            }
            if (i == 4) {
                return POST;
            }
            if (i == 5) {
                return DELETE;
            }
            if (i != 6) {
                return null;
            }
            return PATCH;
        }

        @Deprecated
        public static PatternCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HttpRuleOrBuilder {
        private RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> additionalBindingsBuilder_;
        private List<HttpRule> additionalBindings_;
        private int bitField0_;
        private Object body_;
        private SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> customBuilder_;
        private int patternCase_;
        private Object pattern_;
        private Object responseBody_;
        private Object selector_;

        private Builder() {
            this.patternCase_ = 0;
            this.selector_ = "";
            this.body_ = "";
            this.responseBody_ = "";
            this.additionalBindings_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.patternCase_ = 0;
            this.selector_ = "";
            this.body_ = "";
            this.responseBody_ = "";
            this.additionalBindings_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpProto.internal_static_google_api_HttpRule_descriptor;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public boolean hasCustom() {
            return this.patternCase_ == 8;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpProto.internal_static_google_api_HttpRule_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpRule.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (HttpRule.alwaysUseFieldBuilders) {
                getAdditionalBindingsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1419clear() {
            super.clear();
            this.selector_ = "";
            this.body_ = "";
            this.responseBody_ = "";
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.additionalBindings_ = Collections.emptyList();
                this.bitField0_ &= -513;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.patternCase_ = 0;
            this.pattern_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HttpProto.internal_static_google_api_HttpRule_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HttpRule m1432getDefaultInstanceForType() {
            return HttpRule.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HttpRule m1413build() throws UninitializedMessageException {
            HttpRule httpRuleM1415buildPartial = m1415buildPartial();
            if (httpRuleM1415buildPartial.isInitialized()) {
                return httpRuleM1415buildPartial;
            }
            throw newUninitializedMessageException(httpRuleM1415buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HttpRule m1415buildPartial() {
            HttpRule httpRule = new HttpRule(this);
            httpRule.selector_ = this.selector_;
            if (this.patternCase_ == 2) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 3) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 4) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 5) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 6) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 8) {
                SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
                if (singleFieldBuilderV3 == null) {
                    httpRule.pattern_ = this.pattern_;
                } else {
                    httpRule.pattern_ = singleFieldBuilderV3.build();
                }
            }
            httpRule.body_ = this.body_;
            httpRule.responseBody_ = this.responseBody_;
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 512) != 0) {
                    this.additionalBindings_ = Collections.unmodifiableList(this.additionalBindings_);
                    this.bitField0_ &= -513;
                }
                httpRule.additionalBindings_ = this.additionalBindings_;
            } else {
                httpRule.additionalBindings_ = repeatedFieldBuilderV3.build();
            }
            httpRule.bitField0_ = 0;
            httpRule.patternCase_ = this.patternCase_;
            onBuilt();
            return httpRule;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1430clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1443setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1421clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1424clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1445setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1411addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1437mergeFrom(Message message) {
            if (message instanceof HttpRule) {
                return mergeFrom((HttpRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HttpRule httpRule) {
            if (httpRule == HttpRule.getDefaultInstance()) {
                return this;
            }
            if (!httpRule.getSelector().isEmpty()) {
                this.selector_ = httpRule.selector_;
                onChanged();
            }
            if (!httpRule.getBody().isEmpty()) {
                this.body_ = httpRule.body_;
                onChanged();
            }
            if (!httpRule.getResponseBody().isEmpty()) {
                this.responseBody_ = httpRule.responseBody_;
                onChanged();
            }
            if (this.additionalBindingsBuilder_ == null) {
                if (!httpRule.additionalBindings_.isEmpty()) {
                    if (this.additionalBindings_.isEmpty()) {
                        this.additionalBindings_ = httpRule.additionalBindings_;
                        this.bitField0_ &= -513;
                    } else {
                        ensureAdditionalBindingsIsMutable();
                        this.additionalBindings_.addAll(httpRule.additionalBindings_);
                    }
                    onChanged();
                }
            } else if (!httpRule.additionalBindings_.isEmpty()) {
                if (!this.additionalBindingsBuilder_.isEmpty()) {
                    this.additionalBindingsBuilder_.addAllMessages(httpRule.additionalBindings_);
                } else {
                    this.additionalBindingsBuilder_.dispose();
                    this.additionalBindingsBuilder_ = null;
                    this.additionalBindings_ = httpRule.additionalBindings_;
                    this.bitField0_ &= -513;
                    this.additionalBindingsBuilder_ = HttpRule.alwaysUseFieldBuilders ? getAdditionalBindingsFieldBuilder() : null;
                }
            }
            switch (AnonymousClass2.$SwitchMap$com$google$api$HttpRule$PatternCase[httpRule.getPatternCase().ordinal()]) {
                case 1:
                    this.patternCase_ = 2;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case 2:
                    this.patternCase_ = 3;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case 3:
                    this.patternCase_ = 4;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case 4:
                    this.patternCase_ = 5;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case 5:
                    this.patternCase_ = 6;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case 6:
                    mergeCustom(httpRule.getCustom());
                    break;
            }
            m1441mergeUnknownFields(httpRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.api.HttpRule.Builder m1438mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.HttpRule.access$1300()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.HttpRule r3 = (com.google.api.HttpRule) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.HttpRule r4 = (com.google.api.HttpRule) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.HttpRule.Builder.m1438mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.HttpRule$Builder");
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public PatternCase getPatternCase() {
            return PatternCase.forNumber(this.patternCase_);
        }

        public Builder clearPattern() {
            this.patternCase_ = 0;
            this.pattern_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
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

        @Override // com.google.api.HttpRuleOrBuilder
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
            HttpRule.checkByteStringIsUtf8(byteString);
            this.selector_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearSelector() {
            this.selector_ = HttpRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getGet() {
            String str = this.patternCase_ == 2 ? this.pattern_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.patternCase_ == 2) {
                    this.pattern_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setGet(String str) {
            str.getClass();
            this.patternCase_ = 2;
            this.pattern_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getGetBytes() {
            String str = this.patternCase_ == 2 ? this.pattern_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.patternCase_ == 2) {
                    this.pattern_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setGetBytes(ByteString byteString) {
            byteString.getClass();
            HttpRule.checkByteStringIsUtf8(byteString);
            this.patternCase_ = 2;
            this.pattern_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearGet() {
            if (this.patternCase_ == 2) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getPut() {
            String str = this.patternCase_ == 3 ? this.pattern_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.patternCase_ == 3) {
                    this.pattern_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setPut(String str) {
            str.getClass();
            this.patternCase_ = 3;
            this.pattern_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getPutBytes() {
            String str = this.patternCase_ == 3 ? this.pattern_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.patternCase_ == 3) {
                    this.pattern_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setPutBytes(ByteString byteString) {
            byteString.getClass();
            HttpRule.checkByteStringIsUtf8(byteString);
            this.patternCase_ = 3;
            this.pattern_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearPut() {
            if (this.patternCase_ == 3) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getPost() {
            String str = this.patternCase_ == 4 ? this.pattern_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.patternCase_ == 4) {
                    this.pattern_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setPost(String str) {
            str.getClass();
            this.patternCase_ = 4;
            this.pattern_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getPostBytes() {
            String str = this.patternCase_ == 4 ? this.pattern_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.patternCase_ == 4) {
                    this.pattern_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setPostBytes(ByteString byteString) {
            byteString.getClass();
            HttpRule.checkByteStringIsUtf8(byteString);
            this.patternCase_ = 4;
            this.pattern_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearPost() {
            if (this.patternCase_ == 4) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getDelete() {
            String str = this.patternCase_ == 5 ? this.pattern_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.patternCase_ == 5) {
                    this.pattern_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setDelete(String str) {
            str.getClass();
            this.patternCase_ = 5;
            this.pattern_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getDeleteBytes() {
            String str = this.patternCase_ == 5 ? this.pattern_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.patternCase_ == 5) {
                    this.pattern_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setDeleteBytes(ByteString byteString) {
            byteString.getClass();
            HttpRule.checkByteStringIsUtf8(byteString);
            this.patternCase_ = 5;
            this.pattern_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearDelete() {
            if (this.patternCase_ == 5) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getPatch() {
            String str = this.patternCase_ == 6 ? this.pattern_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.patternCase_ == 6) {
                    this.pattern_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setPatch(String str) {
            str.getClass();
            this.patternCase_ = 6;
            this.pattern_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getPatchBytes() {
            String str = this.patternCase_ == 6 ? this.pattern_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.patternCase_ == 6) {
                    this.pattern_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setPatchBytes(ByteString byteString) {
            byteString.getClass();
            HttpRule.checkByteStringIsUtf8(byteString);
            this.patternCase_ = 6;
            this.pattern_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearPatch() {
            if (this.patternCase_ == 6) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public CustomHttpPattern getCustom() {
            SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.patternCase_ == 8) {
                    return (CustomHttpPattern) this.pattern_;
                }
                return CustomHttpPattern.getDefaultInstance();
            }
            if (this.patternCase_ == 8) {
                return singleFieldBuilderV3.getMessage();
            }
            return CustomHttpPattern.getDefaultInstance();
        }

        public Builder setCustom(CustomHttpPattern customHttpPattern) {
            SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
            if (singleFieldBuilderV3 == null) {
                customHttpPattern.getClass();
                this.pattern_ = customHttpPattern;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(customHttpPattern);
            }
            this.patternCase_ = 8;
            return this;
        }

        public Builder setCustom(CustomHttpPattern.Builder builder) {
            SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.pattern_ = builder.m812build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m812build());
            }
            this.patternCase_ = 8;
            return this;
        }

        public Builder mergeCustom(CustomHttpPattern customHttpPattern) {
            SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.patternCase_ != 8 || this.pattern_ == CustomHttpPattern.getDefaultInstance()) {
                    this.pattern_ = customHttpPattern;
                } else {
                    this.pattern_ = CustomHttpPattern.newBuilder((CustomHttpPattern) this.pattern_).mergeFrom(customHttpPattern).m814buildPartial();
                }
                onChanged();
            } else {
                if (this.patternCase_ == 8) {
                    singleFieldBuilderV3.mergeFrom(customHttpPattern);
                }
                this.customBuilder_.setMessage(customHttpPattern);
            }
            this.patternCase_ = 8;
            return this;
        }

        public Builder clearCustom() {
            SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.patternCase_ == 8) {
                    this.patternCase_ = 0;
                    this.pattern_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.patternCase_ == 8) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        public CustomHttpPattern.Builder getCustomBuilder() {
            return getCustomFieldBuilder().getBuilder();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public CustomHttpPatternOrBuilder getCustomOrBuilder() {
            SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3;
            int i = this.patternCase_;
            if (i == 8 && (singleFieldBuilderV3 = this.customBuilder_) != null) {
                return (CustomHttpPatternOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 8) {
                return (CustomHttpPattern) this.pattern_;
            }
            return CustomHttpPattern.getDefaultInstance();
        }

        private SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> getCustomFieldBuilder() {
            if (this.customBuilder_ == null) {
                if (this.patternCase_ != 8) {
                    this.pattern_ = CustomHttpPattern.getDefaultInstance();
                }
                this.customBuilder_ = new SingleFieldBuilderV3<>((CustomHttpPattern) this.pattern_, getParentForChildren(), isClean());
                this.pattern_ = null;
            }
            this.patternCase_ = 8;
            onChanged();
            return this.customBuilder_;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getBody() {
            Object obj = this.body_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.body_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setBody(String str) {
            str.getClass();
            this.body_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getBodyBytes() {
            Object obj = this.body_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.body_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setBodyBytes(ByteString byteString) {
            byteString.getClass();
            HttpRule.checkByteStringIsUtf8(byteString);
            this.body_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearBody() {
            this.body_ = HttpRule.getDefaultInstance().getBody();
            onChanged();
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getResponseBody() {
            Object obj = this.responseBody_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.responseBody_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setResponseBody(String str) {
            str.getClass();
            this.responseBody_ = str;
            onChanged();
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getResponseBodyBytes() {
            Object obj = this.responseBody_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.responseBody_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setResponseBodyBytes(ByteString byteString) {
            byteString.getClass();
            HttpRule.checkByteStringIsUtf8(byteString);
            this.responseBody_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearResponseBody() {
            this.responseBody_ = HttpRule.getDefaultInstance().getResponseBody();
            onChanged();
            return this;
        }

        private void ensureAdditionalBindingsIsMutable() {
            if ((this.bitField0_ & 512) == 0) {
                this.additionalBindings_ = new ArrayList(this.additionalBindings_);
                this.bitField0_ |= 512;
            }
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public List<HttpRule> getAdditionalBindingsList() {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.additionalBindings_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public int getAdditionalBindingsCount() {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.additionalBindings_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public HttpRule getAdditionalBindings(int i) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.additionalBindings_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setAdditionalBindings(int i, HttpRule httpRule) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                httpRule.getClass();
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.set(i, httpRule);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, httpRule);
            }
            return this;
        }

        public Builder setAdditionalBindings(int i, Builder builder) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.set(i, builder.m1413build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m1413build());
            }
            return this;
        }

        public Builder addAdditionalBindings(HttpRule httpRule) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                httpRule.getClass();
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(httpRule);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(httpRule);
            }
            return this;
        }

        public Builder addAdditionalBindings(int i, HttpRule httpRule) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                httpRule.getClass();
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(i, httpRule);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, httpRule);
            }
            return this;
        }

        public Builder addAdditionalBindings(Builder builder) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(builder.m1413build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m1413build());
            }
            return this;
        }

        public Builder addAdditionalBindings(int i, Builder builder) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(i, builder.m1413build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m1413build());
            }
            return this;
        }

        public Builder addAllAdditionalBindings(Iterable<? extends HttpRule> iterable) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.additionalBindings_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearAdditionalBindings() {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.additionalBindings_ = Collections.emptyList();
                this.bitField0_ &= -513;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeAdditionalBindings(int i) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Builder getAdditionalBindingsBuilder(int i) {
            return getAdditionalBindingsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public HttpRuleOrBuilder getAdditionalBindingsOrBuilder(int i) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.additionalBindings_.get(i);
            }
            return (HttpRuleOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public List<? extends HttpRuleOrBuilder> getAdditionalBindingsOrBuilderList() {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.additionalBindings_);
        }

        public Builder addAdditionalBindingsBuilder() {
            return getAdditionalBindingsFieldBuilder().addBuilder(HttpRule.getDefaultInstance());
        }

        public Builder addAdditionalBindingsBuilder(int i) {
            return getAdditionalBindingsFieldBuilder().addBuilder(i, HttpRule.getDefaultInstance());
        }

        public List<Builder> getAdditionalBindingsBuilderList() {
            return getAdditionalBindingsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> getAdditionalBindingsFieldBuilder() {
            if (this.additionalBindingsBuilder_ == null) {
                this.additionalBindingsBuilder_ = new RepeatedFieldBuilderV3<>(this.additionalBindings_, (this.bitField0_ & 512) != 0, getParentForChildren(), isClean());
                this.additionalBindings_ = null;
            }
            return this.additionalBindingsBuilder_;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1447setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m1441mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: com.google.api.HttpRule$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$api$HttpRule$PatternCase;

        static {
            int[] iArr = new int[PatternCase.values().length];
            $SwitchMap$com$google$api$HttpRule$PatternCase = iArr;
            try {
                iArr[PatternCase.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$api$HttpRule$PatternCase[PatternCase.PUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$api$HttpRule$PatternCase[PatternCase.POST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$api$HttpRule$PatternCase[PatternCase.DELETE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$api$HttpRule$PatternCase[PatternCase.PATCH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$api$HttpRule$PatternCase[PatternCase.CUSTOM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$api$HttpRule$PatternCase[PatternCase.PATTERN_NOT_SET.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }
}
