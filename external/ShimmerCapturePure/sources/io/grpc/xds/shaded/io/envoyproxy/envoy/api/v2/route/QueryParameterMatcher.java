package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
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
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class QueryParameterMatcher extends GeneratedMessageV3 implements QueryParameterMatcherOrBuilder {
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int PRESENT_MATCH_FIELD_NUMBER = 6;
    public static final int REGEX_FIELD_NUMBER = 4;
    public static final int STRING_MATCH_FIELD_NUMBER = 5;
    public static final int VALUE_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final QueryParameterMatcher DEFAULT_INSTANCE = new QueryParameterMatcher();
    private static final Parser<QueryParameterMatcher> PARSER = new AbstractParser<QueryParameterMatcher>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcher.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public QueryParameterMatcher m18141parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new QueryParameterMatcher(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private int queryParameterMatchSpecifierCase_;
    private Object queryParameterMatchSpecifier_;
    private BoolValue regex_;
    private volatile Object value_;

    private QueryParameterMatcher(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.queryParameterMatchSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private QueryParameterMatcher() {
        this.queryParameterMatchSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.value_ = "";
    }

    private QueryParameterMatcher(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        BoolValue.Builder builderM33504toBuilder;
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
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag != 26) {
                            if (tag == 34) {
                                BoolValue boolValue = this.regex_;
                                builderM33504toBuilder = boolValue != null ? boolValue.toBuilder() : null;
                                BoolValue message = codedInputStream.readMessage(BoolValue.parser(), extensionRegistryLite);
                                this.regex_ = message;
                                if (builderM33504toBuilder != null) {
                                    builderM33504toBuilder.mergeFrom(message);
                                    this.regex_ = builderM33504toBuilder.buildPartial();
                                }
                            } else if (tag == 42) {
                                builderM33504toBuilder = this.queryParameterMatchSpecifierCase_ == 5 ? ((StringMatcher) this.queryParameterMatchSpecifier_).m33504toBuilder() : null;
                                MessageLite message2 = codedInputStream.readMessage(StringMatcher.parser(), extensionRegistryLite);
                                this.queryParameterMatchSpecifier_ = message2;
                                if (builderM33504toBuilder != null) {
                                    builderM33504toBuilder.mergeFrom((StringMatcher) message2);
                                    this.queryParameterMatchSpecifier_ = builderM33504toBuilder.m33511buildPartial();
                                }
                                this.queryParameterMatchSpecifierCase_ = 5;
                            } else if (tag == 48) {
                                this.queryParameterMatchSpecifierCase_ = 6;
                                this.queryParameterMatchSpecifier_ = Boolean.valueOf(codedInputStream.readBool());
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.value_ = codedInputStream.readStringRequireUtf8();
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

    public static QueryParameterMatcher getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<QueryParameterMatcher> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RouteComponentsProto.internal_static_envoy_api_v2_route_QueryParameterMatcher_descriptor;
    }

    public static QueryParameterMatcher parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (QueryParameterMatcher) PARSER.parseFrom(byteBuffer);
    }

    public static QueryParameterMatcher parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (QueryParameterMatcher) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static QueryParameterMatcher parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (QueryParameterMatcher) PARSER.parseFrom(byteString);
    }

    public static QueryParameterMatcher parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (QueryParameterMatcher) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static QueryParameterMatcher parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (QueryParameterMatcher) PARSER.parseFrom(bArr);
    }

    public static QueryParameterMatcher parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (QueryParameterMatcher) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static QueryParameterMatcher parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static QueryParameterMatcher parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static QueryParameterMatcher parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static QueryParameterMatcher parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static QueryParameterMatcher parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static QueryParameterMatcher parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m18139toBuilder();
    }

    public static Builder newBuilder(QueryParameterMatcher queryParameterMatcher) {
        return DEFAULT_INSTANCE.m18139toBuilder().mergeFrom(queryParameterMatcher);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public QueryParameterMatcher m18134getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<QueryParameterMatcher> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
    @Deprecated
    public boolean hasRegex() {
        return this.regex_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
    public boolean hasStringMatch() {
        return this.queryParameterMatchSpecifierCase_ == 5;
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
        return new QueryParameterMatcher();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RouteComponentsProto.internal_static_envoy_api_v2_route_QueryParameterMatcher_fieldAccessorTable.ensureFieldAccessorsInitialized(QueryParameterMatcher.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
    public QueryParameterMatchSpecifierCase getQueryParameterMatchSpecifierCase() {
        return QueryParameterMatchSpecifierCase.forNumber(this.queryParameterMatchSpecifierCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
    @Deprecated
    public String getValue() {
        Object obj = this.value_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.value_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
    @Deprecated
    public ByteString getValueBytes() {
        Object obj = this.value_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.value_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
    @Deprecated
    public BoolValue getRegex() {
        BoolValue boolValue = this.regex_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
    @Deprecated
    public BoolValueOrBuilder getRegexOrBuilder() {
        return getRegex();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
    public StringMatcher getStringMatch() {
        if (this.queryParameterMatchSpecifierCase_ == 5) {
            return (StringMatcher) this.queryParameterMatchSpecifier_;
        }
        return StringMatcher.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
    public StringMatcherOrBuilder getStringMatchOrBuilder() {
        if (this.queryParameterMatchSpecifierCase_ == 5) {
            return (StringMatcher) this.queryParameterMatchSpecifier_;
        }
        return StringMatcher.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
    public boolean getPresentMatch() {
        if (this.queryParameterMatchSpecifierCase_ == 6) {
            return ((Boolean) this.queryParameterMatchSpecifier_).booleanValue();
        }
        return false;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (!getValueBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.value_);
        }
        if (this.regex_ != null) {
            codedOutputStream.writeMessage(4, getRegex());
        }
        if (this.queryParameterMatchSpecifierCase_ == 5) {
            codedOutputStream.writeMessage(5, (StringMatcher) this.queryParameterMatchSpecifier_);
        }
        if (this.queryParameterMatchSpecifierCase_ == 6) {
            codedOutputStream.writeBool(6, ((Boolean) this.queryParameterMatchSpecifier_).booleanValue());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (!getValueBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.value_);
        }
        if (this.regex_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(4, getRegex());
        }
        if (this.queryParameterMatchSpecifierCase_ == 5) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(5, (StringMatcher) this.queryParameterMatchSpecifier_);
        }
        if (this.queryParameterMatchSpecifierCase_ == 6) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(6, ((Boolean) this.queryParameterMatchSpecifier_).booleanValue());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QueryParameterMatcher)) {
            return super.equals(obj);
        }
        QueryParameterMatcher queryParameterMatcher = (QueryParameterMatcher) obj;
        if (!getName().equals(queryParameterMatcher.getName()) || !getValue().equals(queryParameterMatcher.getValue()) || hasRegex() != queryParameterMatcher.hasRegex()) {
            return false;
        }
        if ((hasRegex() && !getRegex().equals(queryParameterMatcher.getRegex())) || !getQueryParameterMatchSpecifierCase().equals(queryParameterMatcher.getQueryParameterMatchSpecifierCase())) {
            return false;
        }
        int i = this.queryParameterMatchSpecifierCase_;
        if (i == 5) {
            if (!getStringMatch().equals(queryParameterMatcher.getStringMatch())) {
                return false;
            }
        } else if (i == 6 && getPresentMatch() != queryParameterMatcher.getPresentMatch()) {
            return false;
        }
        return this.unknownFields.equals(queryParameterMatcher.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 3) * 53) + getValue().hashCode();
        if (hasRegex()) {
            iHashCode2 = (((iHashCode2 * 37) + 4) * 53) + getRegex().hashCode();
        }
        int i2 = this.queryParameterMatchSpecifierCase_;
        if (i2 == 5) {
            i = ((iHashCode2 * 37) + 5) * 53;
            iHashCode = getStringMatch().hashCode();
        } else {
            if (i2 == 6) {
                i = ((iHashCode2 * 37) + 6) * 53;
                iHashCode = Internal.hashBoolean(getPresentMatch());
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
    public Builder m18136newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m18139toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum QueryParameterMatchSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        STRING_MATCH(5),
        PRESENT_MATCH(6),
        QUERYPARAMETERMATCHSPECIFIER_NOT_SET(0);

        private final int value;

        QueryParameterMatchSpecifierCase(int i) {
            this.value = i;
        }

        public static QueryParameterMatchSpecifierCase forNumber(int i) {
            if (i == 0) {
                return QUERYPARAMETERMATCHSPECIFIER_NOT_SET;
            }
            if (i == 5) {
                return STRING_MATCH;
            }
            if (i != 6) {
                return null;
            }
            return PRESENT_MATCH;
        }

        @Deprecated
        public static QueryParameterMatchSpecifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements QueryParameterMatcherOrBuilder {
        private Object name_;
        private int queryParameterMatchSpecifierCase_;
        private Object queryParameterMatchSpecifier_;
        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> regexBuilder_;
        private BoolValue regex_;
        private SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> stringMatchBuilder_;
        private Object value_;

        private Builder() {
            this.queryParameterMatchSpecifierCase_ = 0;
            this.name_ = "";
            this.value_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.queryParameterMatchSpecifierCase_ = 0;
            this.name_ = "";
            this.value_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_QueryParameterMatcher_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
        @Deprecated
        public boolean hasRegex() {
            return (this.regexBuilder_ == null && this.regex_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
        public boolean hasStringMatch() {
            return this.queryParameterMatchSpecifierCase_ == 5;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_QueryParameterMatcher_fieldAccessorTable.ensureFieldAccessorsInitialized(QueryParameterMatcher.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = QueryParameterMatcher.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18150clear() {
            super.clear();
            this.name_ = "";
            this.value_ = "";
            if (this.regexBuilder_ == null) {
                this.regex_ = null;
            } else {
                this.regex_ = null;
                this.regexBuilder_ = null;
            }
            this.queryParameterMatchSpecifierCase_ = 0;
            this.queryParameterMatchSpecifier_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_QueryParameterMatcher_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public QueryParameterMatcher m18163getDefaultInstanceForType() {
            return QueryParameterMatcher.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public QueryParameterMatcher m18144build() throws UninitializedMessageException {
            QueryParameterMatcher queryParameterMatcherM18146buildPartial = m18146buildPartial();
            if (queryParameterMatcherM18146buildPartial.isInitialized()) {
                return queryParameterMatcherM18146buildPartial;
            }
            throw newUninitializedMessageException(queryParameterMatcherM18146buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public QueryParameterMatcher m18146buildPartial() {
            QueryParameterMatcher queryParameterMatcher = new QueryParameterMatcher(this);
            queryParameterMatcher.name_ = this.name_;
            queryParameterMatcher.value_ = this.value_;
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.regexBuilder_;
            if (singleFieldBuilderV3 == null) {
                queryParameterMatcher.regex_ = this.regex_;
            } else {
                queryParameterMatcher.regex_ = singleFieldBuilderV3.build();
            }
            if (this.queryParameterMatchSpecifierCase_ == 5) {
                SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV32 = this.stringMatchBuilder_;
                if (singleFieldBuilderV32 == null) {
                    queryParameterMatcher.queryParameterMatchSpecifier_ = this.queryParameterMatchSpecifier_;
                } else {
                    queryParameterMatcher.queryParameterMatchSpecifier_ = singleFieldBuilderV32.build();
                }
            }
            if (this.queryParameterMatchSpecifierCase_ == 6) {
                queryParameterMatcher.queryParameterMatchSpecifier_ = this.queryParameterMatchSpecifier_;
            }
            queryParameterMatcher.queryParameterMatchSpecifierCase_ = this.queryParameterMatchSpecifierCase_;
            onBuilt();
            return queryParameterMatcher;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18162clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18174setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18152clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18155clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18176setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18142addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18167mergeFrom(Message message) {
            if (message instanceof QueryParameterMatcher) {
                return mergeFrom((QueryParameterMatcher) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(QueryParameterMatcher queryParameterMatcher) {
            if (queryParameterMatcher == QueryParameterMatcher.getDefaultInstance()) {
                return this;
            }
            if (!queryParameterMatcher.getName().isEmpty()) {
                this.name_ = queryParameterMatcher.name_;
                onChanged();
            }
            if (!queryParameterMatcher.getValue().isEmpty()) {
                this.value_ = queryParameterMatcher.value_;
                onChanged();
            }
            if (queryParameterMatcher.hasRegex()) {
                mergeRegex(queryParameterMatcher.getRegex());
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$api$v2$route$QueryParameterMatcher$QueryParameterMatchSpecifierCase[queryParameterMatcher.getQueryParameterMatchSpecifierCase().ordinal()];
            if (i == 1) {
                mergeStringMatch(queryParameterMatcher.getStringMatch());
            } else if (i == 2) {
                setPresentMatch(queryParameterMatcher.getPresentMatch());
            }
            m18172mergeUnknownFields(queryParameterMatcher.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcher.Builder m18168mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcher.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcher r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcher) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcher r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcher) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcher.Builder.m18168mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcher$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
        public QueryParameterMatchSpecifierCase getQueryParameterMatchSpecifierCase() {
            return QueryParameterMatchSpecifierCase.forNumber(this.queryParameterMatchSpecifierCase_);
        }

        public Builder clearQueryParameterMatchSpecifier() {
            this.queryParameterMatchSpecifierCase_ = 0;
            this.queryParameterMatchSpecifier_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
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
            QueryParameterMatcher.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = QueryParameterMatcher.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
        @Deprecated
        public String getValue() {
            Object obj = this.value_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.value_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Deprecated
        public Builder setValue(String str) {
            str.getClass();
            this.value_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
        @Deprecated
        public ByteString getValueBytes() {
            Object obj = this.value_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.value_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Deprecated
        public Builder setValueBytes(ByteString byteString) {
            byteString.getClass();
            QueryParameterMatcher.checkByteStringIsUtf8(byteString);
            this.value_ = byteString;
            onChanged();
            return this;
        }

        @Deprecated
        public Builder clearValue() {
            this.value_ = QueryParameterMatcher.getDefaultInstance().getValue();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
        @Deprecated
        public BoolValue getRegex() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.regexBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            BoolValue boolValue = this.regex_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        @Deprecated
        public Builder setRegex(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.regexBuilder_;
            if (singleFieldBuilderV3 == null) {
                boolValue.getClass();
                this.regex_ = boolValue;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(boolValue);
            }
            return this;
        }

        @Deprecated
        public Builder setRegex(BoolValue.Builder builder) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.regexBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.regex_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        @Deprecated
        public Builder mergeRegex(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.regexBuilder_;
            if (singleFieldBuilderV3 == null) {
                BoolValue boolValue2 = this.regex_;
                if (boolValue2 != null) {
                    this.regex_ = BoolValue.newBuilder(boolValue2).mergeFrom(boolValue).buildPartial();
                } else {
                    this.regex_ = boolValue;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(boolValue);
            }
            return this;
        }

        @Deprecated
        public Builder clearRegex() {
            if (this.regexBuilder_ == null) {
                this.regex_ = null;
                onChanged();
            } else {
                this.regex_ = null;
                this.regexBuilder_ = null;
            }
            return this;
        }

        @Deprecated
        public BoolValue.Builder getRegexBuilder() {
            onChanged();
            return getRegexFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
        @Deprecated
        public BoolValueOrBuilder getRegexOrBuilder() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.regexBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            BoolValue boolValue = this.regex_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> getRegexFieldBuilder() {
            if (this.regexBuilder_ == null) {
                this.regexBuilder_ = new SingleFieldBuilderV3<>(getRegex(), getParentForChildren(), isClean());
                this.regex_ = null;
            }
            return this.regexBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
        public StringMatcher getStringMatch() {
            SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.stringMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.queryParameterMatchSpecifierCase_ == 5) {
                    return (StringMatcher) this.queryParameterMatchSpecifier_;
                }
                return StringMatcher.getDefaultInstance();
            }
            if (this.queryParameterMatchSpecifierCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return StringMatcher.getDefaultInstance();
        }

        public Builder setStringMatch(StringMatcher stringMatcher) {
            SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.stringMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                stringMatcher.getClass();
                this.queryParameterMatchSpecifier_ = stringMatcher;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(stringMatcher);
            }
            this.queryParameterMatchSpecifierCase_ = 5;
            return this;
        }

        public Builder setStringMatch(StringMatcher.Builder builder) {
            SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.stringMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.queryParameterMatchSpecifier_ = builder.m33509build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m33509build());
            }
            this.queryParameterMatchSpecifierCase_ = 5;
            return this;
        }

        public Builder mergeStringMatch(StringMatcher stringMatcher) {
            SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.stringMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.queryParameterMatchSpecifierCase_ != 5 || this.queryParameterMatchSpecifier_ == StringMatcher.getDefaultInstance()) {
                    this.queryParameterMatchSpecifier_ = stringMatcher;
                } else {
                    this.queryParameterMatchSpecifier_ = StringMatcher.newBuilder((StringMatcher) this.queryParameterMatchSpecifier_).mergeFrom(stringMatcher).m33511buildPartial();
                }
                onChanged();
            } else {
                if (this.queryParameterMatchSpecifierCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(stringMatcher);
                }
                this.stringMatchBuilder_.setMessage(stringMatcher);
            }
            this.queryParameterMatchSpecifierCase_ = 5;
            return this;
        }

        public Builder clearStringMatch() {
            SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.stringMatchBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.queryParameterMatchSpecifierCase_ == 5) {
                    this.queryParameterMatchSpecifierCase_ = 0;
                    this.queryParameterMatchSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.queryParameterMatchSpecifierCase_ == 5) {
                this.queryParameterMatchSpecifierCase_ = 0;
                this.queryParameterMatchSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public StringMatcher.Builder getStringMatchBuilder() {
            return getStringMatchFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
        public StringMatcherOrBuilder getStringMatchOrBuilder() {
            SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3;
            int i = this.queryParameterMatchSpecifierCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.stringMatchBuilder_) != null) {
                return (StringMatcherOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (StringMatcher) this.queryParameterMatchSpecifier_;
            }
            return StringMatcher.getDefaultInstance();
        }

        private SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> getStringMatchFieldBuilder() {
            if (this.stringMatchBuilder_ == null) {
                if (this.queryParameterMatchSpecifierCase_ != 5) {
                    this.queryParameterMatchSpecifier_ = StringMatcher.getDefaultInstance();
                }
                this.stringMatchBuilder_ = new SingleFieldBuilderV3<>((StringMatcher) this.queryParameterMatchSpecifier_, getParentForChildren(), isClean());
                this.queryParameterMatchSpecifier_ = null;
            }
            this.queryParameterMatchSpecifierCase_ = 5;
            onChanged();
            return this.stringMatchBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcherOrBuilder
        public boolean getPresentMatch() {
            if (this.queryParameterMatchSpecifierCase_ == 6) {
                return ((Boolean) this.queryParameterMatchSpecifier_).booleanValue();
            }
            return false;
        }

        public Builder setPresentMatch(boolean z) {
            this.queryParameterMatchSpecifierCase_ = 6;
            this.queryParameterMatchSpecifier_ = Boolean.valueOf(z);
            onChanged();
            return this;
        }

        public Builder clearPresentMatch() {
            if (this.queryParameterMatchSpecifierCase_ == 6) {
                this.queryParameterMatchSpecifierCase_ = 0;
                this.queryParameterMatchSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m18178setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m18172mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcher$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$api$v2$route$QueryParameterMatcher$QueryParameterMatchSpecifierCase;

        static {
            int[] iArr = new int[QueryParameterMatchSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$api$v2$route$QueryParameterMatcher$QueryParameterMatchSpecifierCase = iArr;
            try {
                iArr[QueryParameterMatchSpecifierCase.STRING_MATCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$QueryParameterMatcher$QueryParameterMatchSpecifierCase[QueryParameterMatchSpecifierCase.PRESENT_MATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$QueryParameterMatcher$QueryParameterMatchSpecifierCase[QueryParameterMatchSpecifierCase.QUERYPARAMETERMATCHSPECIFIER_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
