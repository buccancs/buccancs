package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.RegexMatcher;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class StringMatcher extends GeneratedMessageV3 implements StringMatcherOrBuilder {
    public static final int EXACT_FIELD_NUMBER = 1;
    public static final int IGNORE_CASE_FIELD_NUMBER = 6;
    public static final int PREFIX_FIELD_NUMBER = 2;
    public static final int REGEX_FIELD_NUMBER = 4;
    public static final int SAFE_REGEX_FIELD_NUMBER = 5;
    public static final int SUFFIX_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final StringMatcher DEFAULT_INSTANCE = new StringMatcher();
    private static final Parser<StringMatcher> PARSER = new AbstractParser<StringMatcher>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public StringMatcher m33506parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new StringMatcher(codedInputStream, extensionRegistryLite);
        }
    };
    private boolean ignoreCase_;
    private int matchPatternCase_;
    private Object matchPattern_;
    private byte memoizedIsInitialized;

    private StringMatcher(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.matchPatternCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private StringMatcher() {
        this.matchPatternCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private StringMatcher(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.matchPatternCase_ = 1;
                            this.matchPattern_ = stringRequireUtf8;
                        } else if (tag == 18) {
                            String stringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            this.matchPatternCase_ = 2;
                            this.matchPattern_ = stringRequireUtf82;
                        } else if (tag == 26) {
                            String stringRequireUtf83 = codedInputStream.readStringRequireUtf8();
                            this.matchPatternCase_ = 3;
                            this.matchPattern_ = stringRequireUtf83;
                        } else if (tag == 34) {
                            String stringRequireUtf84 = codedInputStream.readStringRequireUtf8();
                            this.matchPatternCase_ = 4;
                            this.matchPattern_ = stringRequireUtf84;
                        } else if (tag == 42) {
                            RegexMatcher.Builder builderM33412toBuilder = this.matchPatternCase_ == 5 ? ((RegexMatcher) this.matchPattern_).m33412toBuilder() : null;
                            MessageLite message = codedInputStream.readMessage(RegexMatcher.parser(), extensionRegistryLite);
                            this.matchPattern_ = message;
                            if (builderM33412toBuilder != null) {
                                builderM33412toBuilder.mergeFrom((RegexMatcher) message);
                                this.matchPattern_ = builderM33412toBuilder.m33419buildPartial();
                            }
                            this.matchPatternCase_ = 5;
                        } else if (tag == 48) {
                            this.ignoreCase_ = codedInputStream.readBool();
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

    public static StringMatcher getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StringMatcher> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return StringProto.internal_static_envoy_type_matcher_StringMatcher_descriptor;
    }

    public static StringMatcher parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (StringMatcher) PARSER.parseFrom(byteBuffer);
    }

    public static StringMatcher parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StringMatcher) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static StringMatcher parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (StringMatcher) PARSER.parseFrom(byteString);
    }

    public static StringMatcher parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StringMatcher) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static StringMatcher parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (StringMatcher) PARSER.parseFrom(bArr);
    }

    public static StringMatcher parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StringMatcher) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static StringMatcher parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static StringMatcher parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static StringMatcher parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static StringMatcher parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static StringMatcher parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static StringMatcher parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m33504toBuilder();
    }

    public static Builder newBuilder(StringMatcher stringMatcher) {
        return DEFAULT_INSTANCE.m33504toBuilder().mergeFrom(stringMatcher);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public StringMatcher m33499getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
    public boolean getIgnoreCase() {
        return this.ignoreCase_;
    }

    public Parser<StringMatcher> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
    public boolean hasSafeRegex() {
        return this.matchPatternCase_ == 5;
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
        return new StringMatcher();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return StringProto.internal_static_envoy_type_matcher_StringMatcher_fieldAccessorTable.ensureFieldAccessorsInitialized(StringMatcher.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
    public MatchPatternCase getMatchPatternCase() {
        return MatchPatternCase.forNumber(this.matchPatternCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
    public String getExact() {
        String str = this.matchPatternCase_ == 1 ? this.matchPattern_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.matchPatternCase_ == 1) {
            this.matchPattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
    public ByteString getExactBytes() {
        String str = this.matchPatternCase_ == 1 ? this.matchPattern_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.matchPatternCase_ == 1) {
                this.matchPattern_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
    public String getPrefix() {
        String str = this.matchPatternCase_ == 2 ? this.matchPattern_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.matchPatternCase_ == 2) {
            this.matchPattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
    public ByteString getPrefixBytes() {
        String str = this.matchPatternCase_ == 2 ? this.matchPattern_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.matchPatternCase_ == 2) {
                this.matchPattern_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
    public String getSuffix() {
        String str = this.matchPatternCase_ == 3 ? this.matchPattern_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.matchPatternCase_ == 3) {
            this.matchPattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
    public ByteString getSuffixBytes() {
        String str = this.matchPatternCase_ == 3 ? this.matchPattern_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.matchPatternCase_ == 3) {
                this.matchPattern_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
    @Deprecated
    public String getRegex() {
        String str = this.matchPatternCase_ == 4 ? this.matchPattern_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.matchPatternCase_ == 4) {
            this.matchPattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
    @Deprecated
    public ByteString getRegexBytes() {
        String str = this.matchPatternCase_ == 4 ? this.matchPattern_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.matchPatternCase_ == 4) {
                this.matchPattern_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
    public RegexMatcher getSafeRegex() {
        if (this.matchPatternCase_ == 5) {
            return (RegexMatcher) this.matchPattern_;
        }
        return RegexMatcher.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
    public RegexMatcherOrBuilder getSafeRegexOrBuilder() {
        if (this.matchPatternCase_ == 5) {
            return (RegexMatcher) this.matchPattern_;
        }
        return RegexMatcher.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.matchPatternCase_ == 1) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.matchPattern_);
        }
        if (this.matchPatternCase_ == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.matchPattern_);
        }
        if (this.matchPatternCase_ == 3) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.matchPattern_);
        }
        if (this.matchPatternCase_ == 4) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.matchPattern_);
        }
        if (this.matchPatternCase_ == 5) {
            codedOutputStream.writeMessage(5, (RegexMatcher) this.matchPattern_);
        }
        boolean z = this.ignoreCase_;
        if (z) {
            codedOutputStream.writeBool(6, z);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = this.matchPatternCase_ == 1 ? GeneratedMessageV3.computeStringSize(1, this.matchPattern_) : 0;
        if (this.matchPatternCase_ == 2) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.matchPattern_);
        }
        if (this.matchPatternCase_ == 3) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.matchPattern_);
        }
        if (this.matchPatternCase_ == 4) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.matchPattern_);
        }
        if (this.matchPatternCase_ == 5) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(5, (RegexMatcher) this.matchPattern_);
        }
        boolean z = this.ignoreCase_;
        if (z) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(6, z);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StringMatcher)) {
            return super.equals(obj);
        }
        StringMatcher stringMatcher = (StringMatcher) obj;
        if (getIgnoreCase() != stringMatcher.getIgnoreCase() || !getMatchPatternCase().equals(stringMatcher.getMatchPatternCase())) {
            return false;
        }
        int i = this.matchPatternCase_;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        if (!getRegex().equals(stringMatcher.getRegex())) {
                            return false;
                        }
                    } else if (i == 5 && !getSafeRegex().equals(stringMatcher.getSafeRegex())) {
                        return false;
                    }
                } else if (!getSuffix().equals(stringMatcher.getSuffix())) {
                    return false;
                }
            } else if (!getPrefix().equals(stringMatcher.getPrefix())) {
                return false;
            }
        } else if (!getExact().equals(stringMatcher.getExact())) {
            return false;
        }
        return this.unknownFields.equals(stringMatcher.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 6) * 53) + Internal.hashBoolean(getIgnoreCase());
        int i2 = this.matchPatternCase_;
        if (i2 == 1) {
            i = ((iHashCode2 * 37) + 1) * 53;
            iHashCode = getExact().hashCode();
        } else if (i2 == 2) {
            i = ((iHashCode2 * 37) + 2) * 53;
            iHashCode = getPrefix().hashCode();
        } else if (i2 == 3) {
            i = ((iHashCode2 * 37) + 3) * 53;
            iHashCode = getSuffix().hashCode();
        } else if (i2 == 4) {
            i = ((iHashCode2 * 37) + 4) * 53;
            iHashCode = getRegex().hashCode();
        } else {
            if (i2 == 5) {
                i = ((iHashCode2 * 37) + 5) * 53;
                iHashCode = getSafeRegex().hashCode();
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
    public Builder m33501newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m33504toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum MatchPatternCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        EXACT(1),
        PREFIX(2),
        SUFFIX(3),
        REGEX(4),
        SAFE_REGEX(5),
        MATCHPATTERN_NOT_SET(0);

        private final int value;

        MatchPatternCase(int i) {
            this.value = i;
        }

        public static MatchPatternCase forNumber(int i) {
            if (i == 0) {
                return MATCHPATTERN_NOT_SET;
            }
            if (i == 1) {
                return EXACT;
            }
            if (i == 2) {
                return PREFIX;
            }
            if (i == 3) {
                return SUFFIX;
            }
            if (i == 4) {
                return REGEX;
            }
            if (i != 5) {
                return null;
            }
            return SAFE_REGEX;
        }

        @Deprecated
        public static MatchPatternCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StringMatcherOrBuilder {
        private boolean ignoreCase_;
        private int matchPatternCase_;
        private Object matchPattern_;
        private SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> safeRegexBuilder_;

        private Builder() {
            this.matchPatternCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.matchPatternCase_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StringProto.internal_static_envoy_type_matcher_StringMatcher_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
        public boolean getIgnoreCase() {
            return this.ignoreCase_;
        }

        public Builder setIgnoreCase(boolean z) {
            this.ignoreCase_ = z;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
        public boolean hasSafeRegex() {
            return this.matchPatternCase_ == 5;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StringProto.internal_static_envoy_type_matcher_StringMatcher_fieldAccessorTable.ensureFieldAccessorsInitialized(StringMatcher.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = StringMatcher.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33515clear() {
            super.clear();
            this.ignoreCase_ = false;
            this.matchPatternCase_ = 0;
            this.matchPattern_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return StringProto.internal_static_envoy_type_matcher_StringMatcher_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public StringMatcher m33528getDefaultInstanceForType() {
            return StringMatcher.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public StringMatcher m33509build() throws UninitializedMessageException {
            StringMatcher stringMatcherM33511buildPartial = m33511buildPartial();
            if (stringMatcherM33511buildPartial.isInitialized()) {
                return stringMatcherM33511buildPartial;
            }
            throw newUninitializedMessageException(stringMatcherM33511buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public StringMatcher m33511buildPartial() {
            StringMatcher stringMatcher = new StringMatcher(this);
            if (this.matchPatternCase_ == 1) {
                stringMatcher.matchPattern_ = this.matchPattern_;
            }
            if (this.matchPatternCase_ == 2) {
                stringMatcher.matchPattern_ = this.matchPattern_;
            }
            if (this.matchPatternCase_ == 3) {
                stringMatcher.matchPattern_ = this.matchPattern_;
            }
            if (this.matchPatternCase_ == 4) {
                stringMatcher.matchPattern_ = this.matchPattern_;
            }
            if (this.matchPatternCase_ == 5) {
                SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexBuilder_;
                if (singleFieldBuilderV3 == null) {
                    stringMatcher.matchPattern_ = this.matchPattern_;
                } else {
                    stringMatcher.matchPattern_ = singleFieldBuilderV3.build();
                }
            }
            stringMatcher.ignoreCase_ = this.ignoreCase_;
            stringMatcher.matchPatternCase_ = this.matchPatternCase_;
            onBuilt();
            return stringMatcher;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33527clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33539setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33517clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33520clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33541setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33507addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33532mergeFrom(Message message) {
            if (message instanceof StringMatcher) {
                return mergeFrom((StringMatcher) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(StringMatcher stringMatcher) {
            if (stringMatcher == StringMatcher.getDefaultInstance()) {
                return this;
            }
            if (stringMatcher.getIgnoreCase()) {
                setIgnoreCase(stringMatcher.getIgnoreCase());
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$type$matcher$StringMatcher$MatchPatternCase[stringMatcher.getMatchPatternCase().ordinal()];
            if (i == 1) {
                this.matchPatternCase_ = 1;
                this.matchPattern_ = stringMatcher.matchPattern_;
                onChanged();
            } else if (i == 2) {
                this.matchPatternCase_ = 2;
                this.matchPattern_ = stringMatcher.matchPattern_;
                onChanged();
            } else if (i == 3) {
                this.matchPatternCase_ = 3;
                this.matchPattern_ = stringMatcher.matchPattern_;
                onChanged();
            } else if (i == 4) {
                this.matchPatternCase_ = 4;
                this.matchPattern_ = stringMatcher.matchPattern_;
                onChanged();
            } else if (i == 5) {
                mergeSafeRegex(stringMatcher.getSafeRegex());
            }
            m33537mergeUnknownFields(stringMatcher.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher.Builder m33533mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher.Builder.m33533mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
        public MatchPatternCase getMatchPatternCase() {
            return MatchPatternCase.forNumber(this.matchPatternCase_);
        }

        public Builder clearMatchPattern() {
            this.matchPatternCase_ = 0;
            this.matchPattern_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
        public String getExact() {
            String str = this.matchPatternCase_ == 1 ? this.matchPattern_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.matchPatternCase_ == 1) {
                    this.matchPattern_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setExact(String str) {
            str.getClass();
            this.matchPatternCase_ = 1;
            this.matchPattern_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
        public ByteString getExactBytes() {
            String str = this.matchPatternCase_ == 1 ? this.matchPattern_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.matchPatternCase_ == 1) {
                    this.matchPattern_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setExactBytes(ByteString byteString) {
            byteString.getClass();
            StringMatcher.checkByteStringIsUtf8(byteString);
            this.matchPatternCase_ = 1;
            this.matchPattern_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearExact() {
            if (this.matchPatternCase_ == 1) {
                this.matchPatternCase_ = 0;
                this.matchPattern_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
        public String getPrefix() {
            String str = this.matchPatternCase_ == 2 ? this.matchPattern_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.matchPatternCase_ == 2) {
                    this.matchPattern_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setPrefix(String str) {
            str.getClass();
            this.matchPatternCase_ = 2;
            this.matchPattern_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
        public ByteString getPrefixBytes() {
            String str = this.matchPatternCase_ == 2 ? this.matchPattern_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.matchPatternCase_ == 2) {
                    this.matchPattern_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setPrefixBytes(ByteString byteString) {
            byteString.getClass();
            StringMatcher.checkByteStringIsUtf8(byteString);
            this.matchPatternCase_ = 2;
            this.matchPattern_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearPrefix() {
            if (this.matchPatternCase_ == 2) {
                this.matchPatternCase_ = 0;
                this.matchPattern_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
        public String getSuffix() {
            String str = this.matchPatternCase_ == 3 ? this.matchPattern_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.matchPatternCase_ == 3) {
                    this.matchPattern_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setSuffix(String str) {
            str.getClass();
            this.matchPatternCase_ = 3;
            this.matchPattern_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
        public ByteString getSuffixBytes() {
            String str = this.matchPatternCase_ == 3 ? this.matchPattern_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.matchPatternCase_ == 3) {
                    this.matchPattern_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setSuffixBytes(ByteString byteString) {
            byteString.getClass();
            StringMatcher.checkByteStringIsUtf8(byteString);
            this.matchPatternCase_ = 3;
            this.matchPattern_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearSuffix() {
            if (this.matchPatternCase_ == 3) {
                this.matchPatternCase_ = 0;
                this.matchPattern_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
        @Deprecated
        public String getRegex() {
            String str = this.matchPatternCase_ == 4 ? this.matchPattern_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.matchPatternCase_ == 4) {
                    this.matchPattern_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Deprecated
        public Builder setRegex(String str) {
            str.getClass();
            this.matchPatternCase_ = 4;
            this.matchPattern_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
        @Deprecated
        public ByteString getRegexBytes() {
            String str = this.matchPatternCase_ == 4 ? this.matchPattern_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.matchPatternCase_ == 4) {
                    this.matchPattern_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        @Deprecated
        public Builder setRegexBytes(ByteString byteString) {
            byteString.getClass();
            StringMatcher.checkByteStringIsUtf8(byteString);
            this.matchPatternCase_ = 4;
            this.matchPattern_ = byteString;
            onChanged();
            return this;
        }

        @Deprecated
        public Builder clearRegex() {
            if (this.matchPatternCase_ == 4) {
                this.matchPatternCase_ = 0;
                this.matchPattern_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
        public RegexMatcher getSafeRegex() {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.matchPatternCase_ == 5) {
                    return (RegexMatcher) this.matchPattern_;
                }
                return RegexMatcher.getDefaultInstance();
            }
            if (this.matchPatternCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return RegexMatcher.getDefaultInstance();
        }

        public Builder setSafeRegex(RegexMatcher regexMatcher) {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexBuilder_;
            if (singleFieldBuilderV3 == null) {
                regexMatcher.getClass();
                this.matchPattern_ = regexMatcher;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(regexMatcher);
            }
            this.matchPatternCase_ = 5;
            return this;
        }

        public Builder setSafeRegex(RegexMatcher.Builder builder) {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.matchPattern_ = builder.m33417build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m33417build());
            }
            this.matchPatternCase_ = 5;
            return this;
        }

        public Builder mergeSafeRegex(RegexMatcher regexMatcher) {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.matchPatternCase_ != 5 || this.matchPattern_ == RegexMatcher.getDefaultInstance()) {
                    this.matchPattern_ = regexMatcher;
                } else {
                    this.matchPattern_ = RegexMatcher.newBuilder((RegexMatcher) this.matchPattern_).mergeFrom(regexMatcher).m33419buildPartial();
                }
                onChanged();
            } else {
                if (this.matchPatternCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(regexMatcher);
                }
                this.safeRegexBuilder_.setMessage(regexMatcher);
            }
            this.matchPatternCase_ = 5;
            return this;
        }

        public Builder clearSafeRegex() {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.matchPatternCase_ == 5) {
                    this.matchPatternCase_ = 0;
                    this.matchPattern_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.matchPatternCase_ == 5) {
                this.matchPatternCase_ = 0;
                this.matchPattern_ = null;
                onChanged();
            }
            return this;
        }

        public RegexMatcher.Builder getSafeRegexBuilder() {
            return getSafeRegexFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder
        public RegexMatcherOrBuilder getSafeRegexOrBuilder() {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3;
            int i = this.matchPatternCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.safeRegexBuilder_) != null) {
                return (RegexMatcherOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (RegexMatcher) this.matchPattern_;
            }
            return RegexMatcher.getDefaultInstance();
        }

        private SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> getSafeRegexFieldBuilder() {
            if (this.safeRegexBuilder_ == null) {
                if (this.matchPatternCase_ != 5) {
                    this.matchPattern_ = RegexMatcher.getDefaultInstance();
                }
                this.safeRegexBuilder_ = new SingleFieldBuilderV3<>((RegexMatcher) this.matchPattern_, getParentForChildren(), isClean());
                this.matchPattern_ = null;
            }
            this.matchPatternCase_ = 5;
            onChanged();
            return this.safeRegexBuilder_;
        }

        public Builder clearIgnoreCase() {
            this.ignoreCase_ = false;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m33543setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m33537mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$type$matcher$StringMatcher$MatchPatternCase;

        static {
            int[] iArr = new int[MatchPatternCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$type$matcher$StringMatcher$MatchPatternCase = iArr;
            try {
                iArr[MatchPatternCase.EXACT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$matcher$StringMatcher$MatchPatternCase[MatchPatternCase.PREFIX.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$matcher$StringMatcher$MatchPatternCase[MatchPatternCase.SUFFIX.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$matcher$StringMatcher$MatchPatternCase[MatchPatternCase.REGEX.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$matcher$StringMatcher$MatchPatternCase[MatchPatternCase.SAFE_REGEX.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$matcher$StringMatcher$MatchPatternCase[MatchPatternCase.MATCHPATTERN_NOT_SET.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
