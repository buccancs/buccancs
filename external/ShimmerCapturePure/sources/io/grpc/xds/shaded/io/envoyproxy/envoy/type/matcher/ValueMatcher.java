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
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ListMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class ValueMatcher extends GeneratedMessageV3 implements ValueMatcherOrBuilder {
    public static final int BOOL_MATCH_FIELD_NUMBER = 4;
    public static final int DOUBLE_MATCH_FIELD_NUMBER = 2;
    public static final int LIST_MATCH_FIELD_NUMBER = 6;
    public static final int NULL_MATCH_FIELD_NUMBER = 1;
    public static final int PRESENT_MATCH_FIELD_NUMBER = 5;
    public static final int STRING_MATCH_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final ValueMatcher DEFAULT_INSTANCE = new ValueMatcher();
    private static final Parser<ValueMatcher> PARSER = new AbstractParser<ValueMatcher>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ValueMatcher m33552parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ValueMatcher(codedInputStream, extensionRegistryLite);
        }
    };
    private int matchPatternCase_;
    private Object matchPattern_;
    private byte memoizedIsInitialized;

    private ValueMatcher(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.matchPatternCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private ValueMatcher() {
        this.matchPatternCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private ValueMatcher(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag == 10) {
                                NullMatch.Builder builderM33596toBuilder = this.matchPatternCase_ == 1 ? ((NullMatch) this.matchPattern_).m33596toBuilder() : null;
                                MessageLite message = codedInputStream.readMessage(NullMatch.parser(), extensionRegistryLite);
                                this.matchPattern_ = message;
                                if (builderM33596toBuilder != null) {
                                    builderM33596toBuilder.mergeFrom((NullMatch) message);
                                    this.matchPattern_ = builderM33596toBuilder.m33603buildPartial();
                                }
                                this.matchPatternCase_ = 1;
                            } else if (tag == 18) {
                                DoubleMatcher.Builder builderM33090toBuilder = this.matchPatternCase_ == 2 ? ((DoubleMatcher) this.matchPattern_).m33090toBuilder() : null;
                                MessageLite message2 = codedInputStream.readMessage(DoubleMatcher.parser(), extensionRegistryLite);
                                this.matchPattern_ = message2;
                                if (builderM33090toBuilder != null) {
                                    builderM33090toBuilder.mergeFrom((DoubleMatcher) message2);
                                    this.matchPattern_ = builderM33090toBuilder.m33097buildPartial();
                                }
                                this.matchPatternCase_ = 2;
                            } else if (tag == 26) {
                                StringMatcher.Builder builderM33504toBuilder = this.matchPatternCase_ == 3 ? ((StringMatcher) this.matchPattern_).m33504toBuilder() : null;
                                MessageLite message3 = codedInputStream.readMessage(StringMatcher.parser(), extensionRegistryLite);
                                this.matchPattern_ = message3;
                                if (builderM33504toBuilder != null) {
                                    builderM33504toBuilder.mergeFrom((StringMatcher) message3);
                                    this.matchPattern_ = builderM33504toBuilder.m33511buildPartial();
                                }
                                this.matchPatternCase_ = 3;
                            } else if (tag == 32) {
                                this.matchPatternCase_ = 4;
                                this.matchPattern_ = Boolean.valueOf(codedInputStream.readBool());
                            } else if (tag == 40) {
                                this.matchPatternCase_ = 5;
                                this.matchPattern_ = Boolean.valueOf(codedInputStream.readBool());
                            } else if (tag == 50) {
                                ListMatcher.Builder builderM33137toBuilder = this.matchPatternCase_ == 6 ? ((ListMatcher) this.matchPattern_).m33136toBuilder() : null;
                                MessageLite message4 = codedInputStream.readMessage(ListMatcher.parser(), extensionRegistryLite);
                                this.matchPattern_ = message4;
                                if (builderM33137toBuilder != null) {
                                    builderM33137toBuilder.mergeFrom((ListMatcher) message4);
                                    this.matchPattern_ = builderM33137toBuilder.m33143buildPartial();
                                }
                                this.matchPatternCase_ = 6;
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
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

    public static ValueMatcher getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ValueMatcher> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ValueProto.internal_static_envoy_type_matcher_ValueMatcher_descriptor;
    }

    public static ValueMatcher parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ValueMatcher) PARSER.parseFrom(byteBuffer);
    }

    public static ValueMatcher parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ValueMatcher) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ValueMatcher parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ValueMatcher) PARSER.parseFrom(byteString);
    }

    public static ValueMatcher parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ValueMatcher) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ValueMatcher parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ValueMatcher) PARSER.parseFrom(bArr);
    }

    public static ValueMatcher parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ValueMatcher) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ValueMatcher parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ValueMatcher parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ValueMatcher parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ValueMatcher parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ValueMatcher parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ValueMatcher parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m33550toBuilder();
    }

    public static Builder newBuilder(ValueMatcher valueMatcher) {
        return DEFAULT_INSTANCE.m33550toBuilder().mergeFrom(valueMatcher);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ValueMatcher m33545getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ValueMatcher> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public boolean hasDoubleMatch() {
        return this.matchPatternCase_ == 2;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public boolean hasListMatch() {
        return this.matchPatternCase_ == 6;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public boolean hasNullMatch() {
        return this.matchPatternCase_ == 1;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public boolean hasStringMatch() {
        return this.matchPatternCase_ == 3;
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
        return new ValueMatcher();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ValueProto.internal_static_envoy_type_matcher_ValueMatcher_fieldAccessorTable.ensureFieldAccessorsInitialized(ValueMatcher.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public MatchPatternCase getMatchPatternCase() {
        return MatchPatternCase.forNumber(this.matchPatternCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public NullMatch getNullMatch() {
        if (this.matchPatternCase_ == 1) {
            return (NullMatch) this.matchPattern_;
        }
        return NullMatch.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public NullMatchOrBuilder getNullMatchOrBuilder() {
        if (this.matchPatternCase_ == 1) {
            return (NullMatch) this.matchPattern_;
        }
        return NullMatch.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public DoubleMatcher getDoubleMatch() {
        if (this.matchPatternCase_ == 2) {
            return (DoubleMatcher) this.matchPattern_;
        }
        return DoubleMatcher.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public DoubleMatcherOrBuilder getDoubleMatchOrBuilder() {
        if (this.matchPatternCase_ == 2) {
            return (DoubleMatcher) this.matchPattern_;
        }
        return DoubleMatcher.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public StringMatcher getStringMatch() {
        if (this.matchPatternCase_ == 3) {
            return (StringMatcher) this.matchPattern_;
        }
        return StringMatcher.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public StringMatcherOrBuilder getStringMatchOrBuilder() {
        if (this.matchPatternCase_ == 3) {
            return (StringMatcher) this.matchPattern_;
        }
        return StringMatcher.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public boolean getBoolMatch() {
        if (this.matchPatternCase_ == 4) {
            return ((Boolean) this.matchPattern_).booleanValue();
        }
        return false;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public boolean getPresentMatch() {
        if (this.matchPatternCase_ == 5) {
            return ((Boolean) this.matchPattern_).booleanValue();
        }
        return false;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public ListMatcher getListMatch() {
        if (this.matchPatternCase_ == 6) {
            return (ListMatcher) this.matchPattern_;
        }
        return ListMatcher.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
    public ListMatcherOrBuilder getListMatchOrBuilder() {
        if (this.matchPatternCase_ == 6) {
            return (ListMatcher) this.matchPattern_;
        }
        return ListMatcher.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.matchPatternCase_ == 1) {
            codedOutputStream.writeMessage(1, (NullMatch) this.matchPattern_);
        }
        if (this.matchPatternCase_ == 2) {
            codedOutputStream.writeMessage(2, (DoubleMatcher) this.matchPattern_);
        }
        if (this.matchPatternCase_ == 3) {
            codedOutputStream.writeMessage(3, (StringMatcher) this.matchPattern_);
        }
        if (this.matchPatternCase_ == 4) {
            codedOutputStream.writeBool(4, ((Boolean) this.matchPattern_).booleanValue());
        }
        if (this.matchPatternCase_ == 5) {
            codedOutputStream.writeBool(5, ((Boolean) this.matchPattern_).booleanValue());
        }
        if (this.matchPatternCase_ == 6) {
            codedOutputStream.writeMessage(6, (ListMatcher) this.matchPattern_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.matchPatternCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (NullMatch) this.matchPattern_) : 0;
        if (this.matchPatternCase_ == 2) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, (DoubleMatcher) this.matchPattern_);
        }
        if (this.matchPatternCase_ == 3) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, (StringMatcher) this.matchPattern_);
        }
        if (this.matchPatternCase_ == 4) {
            iComputeMessageSize += CodedOutputStream.computeBoolSize(4, ((Boolean) this.matchPattern_).booleanValue());
        }
        if (this.matchPatternCase_ == 5) {
            iComputeMessageSize += CodedOutputStream.computeBoolSize(5, ((Boolean) this.matchPattern_).booleanValue());
        }
        if (this.matchPatternCase_ == 6) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(6, (ListMatcher) this.matchPattern_);
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ValueMatcher)) {
            return super.equals(obj);
        }
        ValueMatcher valueMatcher = (ValueMatcher) obj;
        if (!getMatchPatternCase().equals(valueMatcher.getMatchPatternCase())) {
            return false;
        }
        switch (this.matchPatternCase_) {
            case 1:
                if (!getNullMatch().equals(valueMatcher.getNullMatch())) {
                    return false;
                }
                break;
            case 2:
                if (!getDoubleMatch().equals(valueMatcher.getDoubleMatch())) {
                    return false;
                }
                break;
            case 3:
                if (!getStringMatch().equals(valueMatcher.getStringMatch())) {
                    return false;
                }
                break;
            case 4:
                if (getBoolMatch() != valueMatcher.getBoolMatch()) {
                    return false;
                }
                break;
            case 5:
                if (getPresentMatch() != valueMatcher.getPresentMatch()) {
                    return false;
                }
                break;
            case 6:
                if (!getListMatch().equals(valueMatcher.getListMatch())) {
                    return false;
                }
                break;
        }
        return this.unknownFields.equals(valueMatcher.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        switch (this.matchPatternCase_) {
            case 1:
                i = ((iHashCode2 * 37) + 1) * 53;
                iHashCode = getNullMatch().hashCode();
                break;
            case 2:
                i = ((iHashCode2 * 37) + 2) * 53;
                iHashCode = getDoubleMatch().hashCode();
                break;
            case 3:
                i = ((iHashCode2 * 37) + 3) * 53;
                iHashCode = getStringMatch().hashCode();
                break;
            case 4:
                i = ((iHashCode2 * 37) + 4) * 53;
                iHashCode = Internal.hashBoolean(getBoolMatch());
                break;
            case 5:
                i = ((iHashCode2 * 37) + 5) * 53;
                iHashCode = Internal.hashBoolean(getPresentMatch());
                break;
            case 6:
                i = ((iHashCode2 * 37) + 6) * 53;
                iHashCode = getListMatch().hashCode();
                break;
            default:
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
    public Builder m33547newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m33550toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum MatchPatternCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        NULL_MATCH(1),
        DOUBLE_MATCH(2),
        STRING_MATCH(3),
        BOOL_MATCH(4),
        PRESENT_MATCH(5),
        LIST_MATCH(6),
        MATCHPATTERN_NOT_SET(0);

        private final int value;

        MatchPatternCase(int i) {
            this.value = i;
        }

        public static MatchPatternCase forNumber(int i) {
            switch (i) {
                case 0:
                    return MATCHPATTERN_NOT_SET;
                case 1:
                    return NULL_MATCH;
                case 2:
                    return DOUBLE_MATCH;
                case 3:
                    return STRING_MATCH;
                case 4:
                    return BOOL_MATCH;
                case 5:
                    return PRESENT_MATCH;
                case 6:
                    return LIST_MATCH;
                default:
                    return null;
            }
        }

        @Deprecated
        public static MatchPatternCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public interface NullMatchOrBuilder extends MessageOrBuilder {
    }

    public static final class NullMatch extends GeneratedMessageV3 implements NullMatchOrBuilder {
        private static final NullMatch DEFAULT_INSTANCE = new NullMatch();
        private static final Parser<NullMatch> PARSER = new AbstractParser<NullMatch>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher.NullMatch.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public NullMatch m33598parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new NullMatch(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private NullMatch(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private NullMatch() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private NullMatch(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag == 0 || !parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                z = true;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        }
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static NullMatch getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<NullMatch> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ValueProto.internal_static_envoy_type_matcher_ValueMatcher_NullMatch_descriptor;
        }

        public static NullMatch parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NullMatch) PARSER.parseFrom(byteBuffer);
        }

        public static NullMatch parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NullMatch) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static NullMatch parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NullMatch) PARSER.parseFrom(byteString);
        }

        public static NullMatch parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NullMatch) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static NullMatch parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NullMatch) PARSER.parseFrom(bArr);
        }

        public static NullMatch parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NullMatch) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static NullMatch parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static NullMatch parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static NullMatch parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static NullMatch parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static NullMatch parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static NullMatch parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m33596toBuilder();
        }

        public static Builder newBuilder(NullMatch nullMatch) {
            return DEFAULT_INSTANCE.m33596toBuilder().mergeFrom(nullMatch);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public NullMatch m33591getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<NullMatch> getParserForType() {
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

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new NullMatch();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ValueProto.internal_static_envoy_type_matcher_ValueMatcher_NullMatch_fieldAccessorTable.ensureFieldAccessorsInitialized(NullMatch.class, Builder.class);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof NullMatch) {
                return this.unknownFields.equals(((NullMatch) obj).unknownFields);
            }
            return super.equals(obj);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33593newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33596toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements NullMatchOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ValueProto.internal_static_envoy_type_matcher_ValueMatcher_NullMatch_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ValueProto.internal_static_envoy_type_matcher_ValueMatcher_NullMatch_fieldAccessorTable.ensureFieldAccessorsInitialized(NullMatch.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = NullMatch.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33607clear() {
                super.clear();
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ValueProto.internal_static_envoy_type_matcher_ValueMatcher_NullMatch_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public NullMatch m33620getDefaultInstanceForType() {
                return NullMatch.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public NullMatch m33601build() throws UninitializedMessageException {
                NullMatch nullMatchM33603buildPartial = m33603buildPartial();
                if (nullMatchM33603buildPartial.isInitialized()) {
                    return nullMatchM33603buildPartial;
                }
                throw newUninitializedMessageException(nullMatchM33603buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public NullMatch m33603buildPartial() {
                NullMatch nullMatch = new NullMatch(this);
                onBuilt();
                return nullMatch;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33619clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33631setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33609clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33612clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33633setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33599addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33624mergeFrom(Message message) {
                if (message instanceof NullMatch) {
                    return mergeFrom((NullMatch) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(NullMatch nullMatch) {
                if (nullMatch == NullMatch.getDefaultInstance()) {
                    return this;
                }
                m33629mergeUnknownFields(nullMatch.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher.NullMatch.Builder m33625mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher.NullMatch.access$500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher$NullMatch r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher.NullMatch) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher$NullMatch r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher.NullMatch) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher.NullMatch.Builder.m33625mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher$NullMatch$Builder");
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m33635setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m33629mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ValueMatcherOrBuilder {
        private SingleFieldBuilderV3<DoubleMatcher, DoubleMatcher.Builder, DoubleMatcherOrBuilder> doubleMatchBuilder_;
        private SingleFieldBuilderV3<ListMatcher, ListMatcher.Builder, ListMatcherOrBuilder> listMatchBuilder_;
        private int matchPatternCase_;
        private Object matchPattern_;
        private SingleFieldBuilderV3<NullMatch, NullMatch.Builder, NullMatchOrBuilder> nullMatchBuilder_;
        private SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> stringMatchBuilder_;

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
            return ValueProto.internal_static_envoy_type_matcher_ValueMatcher_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public boolean hasDoubleMatch() {
            return this.matchPatternCase_ == 2;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public boolean hasListMatch() {
            return this.matchPatternCase_ == 6;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public boolean hasNullMatch() {
            return this.matchPatternCase_ == 1;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public boolean hasStringMatch() {
            return this.matchPatternCase_ == 3;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ValueProto.internal_static_envoy_type_matcher_ValueMatcher_fieldAccessorTable.ensureFieldAccessorsInitialized(ValueMatcher.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ValueMatcher.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33561clear() {
            super.clear();
            this.matchPatternCase_ = 0;
            this.matchPattern_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ValueProto.internal_static_envoy_type_matcher_ValueMatcher_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ValueMatcher m33574getDefaultInstanceForType() {
            return ValueMatcher.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ValueMatcher m33555build() throws UninitializedMessageException {
            ValueMatcher valueMatcherM33557buildPartial = m33557buildPartial();
            if (valueMatcherM33557buildPartial.isInitialized()) {
                return valueMatcherM33557buildPartial;
            }
            throw newUninitializedMessageException(valueMatcherM33557buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ValueMatcher m33557buildPartial() {
            ValueMatcher valueMatcher = new ValueMatcher(this);
            if (this.matchPatternCase_ == 1) {
                SingleFieldBuilderV3<NullMatch, NullMatch.Builder, NullMatchOrBuilder> singleFieldBuilderV3 = this.nullMatchBuilder_;
                if (singleFieldBuilderV3 == null) {
                    valueMatcher.matchPattern_ = this.matchPattern_;
                } else {
                    valueMatcher.matchPattern_ = singleFieldBuilderV3.build();
                }
            }
            if (this.matchPatternCase_ == 2) {
                SingleFieldBuilderV3<DoubleMatcher, DoubleMatcher.Builder, DoubleMatcherOrBuilder> singleFieldBuilderV32 = this.doubleMatchBuilder_;
                if (singleFieldBuilderV32 == null) {
                    valueMatcher.matchPattern_ = this.matchPattern_;
                } else {
                    valueMatcher.matchPattern_ = singleFieldBuilderV32.build();
                }
            }
            if (this.matchPatternCase_ == 3) {
                SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV33 = this.stringMatchBuilder_;
                if (singleFieldBuilderV33 == null) {
                    valueMatcher.matchPattern_ = this.matchPattern_;
                } else {
                    valueMatcher.matchPattern_ = singleFieldBuilderV33.build();
                }
            }
            if (this.matchPatternCase_ == 4) {
                valueMatcher.matchPattern_ = this.matchPattern_;
            }
            if (this.matchPatternCase_ == 5) {
                valueMatcher.matchPattern_ = this.matchPattern_;
            }
            if (this.matchPatternCase_ == 6) {
                SingleFieldBuilderV3<ListMatcher, ListMatcher.Builder, ListMatcherOrBuilder> singleFieldBuilderV34 = this.listMatchBuilder_;
                if (singleFieldBuilderV34 == null) {
                    valueMatcher.matchPattern_ = this.matchPattern_;
                } else {
                    valueMatcher.matchPattern_ = singleFieldBuilderV34.build();
                }
            }
            valueMatcher.matchPatternCase_ = this.matchPatternCase_;
            onBuilt();
            return valueMatcher;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33573clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33585setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33563clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33566clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33587setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33553addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33578mergeFrom(Message message) {
            if (message instanceof ValueMatcher) {
                return mergeFrom((ValueMatcher) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ValueMatcher valueMatcher) {
            if (valueMatcher == ValueMatcher.getDefaultInstance()) {
                return this;
            }
            switch (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$type$matcher$ValueMatcher$MatchPatternCase[valueMatcher.getMatchPatternCase().ordinal()]) {
                case 1:
                    mergeNullMatch(valueMatcher.getNullMatch());
                    break;
                case 2:
                    mergeDoubleMatch(valueMatcher.getDoubleMatch());
                    break;
                case 3:
                    mergeStringMatch(valueMatcher.getStringMatch());
                    break;
                case 4:
                    setBoolMatch(valueMatcher.getBoolMatch());
                    break;
                case 5:
                    setPresentMatch(valueMatcher.getPresentMatch());
                    break;
                case 6:
                    mergeListMatch(valueMatcher.getListMatch());
                    break;
            }
            m33583mergeUnknownFields(valueMatcher.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher.Builder m33579mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher.access$1400()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher.Builder.m33579mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public MatchPatternCase getMatchPatternCase() {
            return MatchPatternCase.forNumber(this.matchPatternCase_);
        }

        public Builder clearMatchPattern() {
            this.matchPatternCase_ = 0;
            this.matchPattern_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public NullMatch getNullMatch() {
            SingleFieldBuilderV3<NullMatch, NullMatch.Builder, NullMatchOrBuilder> singleFieldBuilderV3 = this.nullMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.matchPatternCase_ == 1) {
                    return (NullMatch) this.matchPattern_;
                }
                return NullMatch.getDefaultInstance();
            }
            if (this.matchPatternCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            }
            return NullMatch.getDefaultInstance();
        }

        public Builder setNullMatch(NullMatch nullMatch) {
            SingleFieldBuilderV3<NullMatch, NullMatch.Builder, NullMatchOrBuilder> singleFieldBuilderV3 = this.nullMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                nullMatch.getClass();
                this.matchPattern_ = nullMatch;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(nullMatch);
            }
            this.matchPatternCase_ = 1;
            return this;
        }

        public Builder setNullMatch(NullMatch.Builder builder) {
            SingleFieldBuilderV3<NullMatch, NullMatch.Builder, NullMatchOrBuilder> singleFieldBuilderV3 = this.nullMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.matchPattern_ = builder.m33601build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m33601build());
            }
            this.matchPatternCase_ = 1;
            return this;
        }

        public Builder mergeNullMatch(NullMatch nullMatch) {
            SingleFieldBuilderV3<NullMatch, NullMatch.Builder, NullMatchOrBuilder> singleFieldBuilderV3 = this.nullMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.matchPatternCase_ != 1 || this.matchPattern_ == NullMatch.getDefaultInstance()) {
                    this.matchPattern_ = nullMatch;
                } else {
                    this.matchPattern_ = NullMatch.newBuilder((NullMatch) this.matchPattern_).mergeFrom(nullMatch).m33603buildPartial();
                }
                onChanged();
            } else {
                if (this.matchPatternCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(nullMatch);
                }
                this.nullMatchBuilder_.setMessage(nullMatch);
            }
            this.matchPatternCase_ = 1;
            return this;
        }

        public Builder clearNullMatch() {
            SingleFieldBuilderV3<NullMatch, NullMatch.Builder, NullMatchOrBuilder> singleFieldBuilderV3 = this.nullMatchBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.matchPatternCase_ == 1) {
                    this.matchPatternCase_ = 0;
                    this.matchPattern_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.matchPatternCase_ == 1) {
                this.matchPatternCase_ = 0;
                this.matchPattern_ = null;
                onChanged();
            }
            return this;
        }

        public NullMatch.Builder getNullMatchBuilder() {
            return getNullMatchFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public NullMatchOrBuilder getNullMatchOrBuilder() {
            SingleFieldBuilderV3<NullMatch, NullMatch.Builder, NullMatchOrBuilder> singleFieldBuilderV3;
            int i = this.matchPatternCase_;
            if (i == 1 && (singleFieldBuilderV3 = this.nullMatchBuilder_) != null) {
                return (NullMatchOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 1) {
                return (NullMatch) this.matchPattern_;
            }
            return NullMatch.getDefaultInstance();
        }

        private SingleFieldBuilderV3<NullMatch, NullMatch.Builder, NullMatchOrBuilder> getNullMatchFieldBuilder() {
            if (this.nullMatchBuilder_ == null) {
                if (this.matchPatternCase_ != 1) {
                    this.matchPattern_ = NullMatch.getDefaultInstance();
                }
                this.nullMatchBuilder_ = new SingleFieldBuilderV3<>((NullMatch) this.matchPattern_, getParentForChildren(), isClean());
                this.matchPattern_ = null;
            }
            this.matchPatternCase_ = 1;
            onChanged();
            return this.nullMatchBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public DoubleMatcher getDoubleMatch() {
            SingleFieldBuilderV3<DoubleMatcher, DoubleMatcher.Builder, DoubleMatcherOrBuilder> singleFieldBuilderV3 = this.doubleMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.matchPatternCase_ == 2) {
                    return (DoubleMatcher) this.matchPattern_;
                }
                return DoubleMatcher.getDefaultInstance();
            }
            if (this.matchPatternCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return DoubleMatcher.getDefaultInstance();
        }

        public Builder setDoubleMatch(DoubleMatcher doubleMatcher) {
            SingleFieldBuilderV3<DoubleMatcher, DoubleMatcher.Builder, DoubleMatcherOrBuilder> singleFieldBuilderV3 = this.doubleMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                doubleMatcher.getClass();
                this.matchPattern_ = doubleMatcher;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(doubleMatcher);
            }
            this.matchPatternCase_ = 2;
            return this;
        }

        public Builder setDoubleMatch(DoubleMatcher.Builder builder) {
            SingleFieldBuilderV3<DoubleMatcher, DoubleMatcher.Builder, DoubleMatcherOrBuilder> singleFieldBuilderV3 = this.doubleMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.matchPattern_ = builder.m33095build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m33095build());
            }
            this.matchPatternCase_ = 2;
            return this;
        }

        public Builder mergeDoubleMatch(DoubleMatcher doubleMatcher) {
            SingleFieldBuilderV3<DoubleMatcher, DoubleMatcher.Builder, DoubleMatcherOrBuilder> singleFieldBuilderV3 = this.doubleMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.matchPatternCase_ != 2 || this.matchPattern_ == DoubleMatcher.getDefaultInstance()) {
                    this.matchPattern_ = doubleMatcher;
                } else {
                    this.matchPattern_ = DoubleMatcher.newBuilder((DoubleMatcher) this.matchPattern_).mergeFrom(doubleMatcher).m33097buildPartial();
                }
                onChanged();
            } else {
                if (this.matchPatternCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(doubleMatcher);
                }
                this.doubleMatchBuilder_.setMessage(doubleMatcher);
            }
            this.matchPatternCase_ = 2;
            return this;
        }

        public Builder clearDoubleMatch() {
            SingleFieldBuilderV3<DoubleMatcher, DoubleMatcher.Builder, DoubleMatcherOrBuilder> singleFieldBuilderV3 = this.doubleMatchBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.matchPatternCase_ == 2) {
                    this.matchPatternCase_ = 0;
                    this.matchPattern_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.matchPatternCase_ == 2) {
                this.matchPatternCase_ = 0;
                this.matchPattern_ = null;
                onChanged();
            }
            return this;
        }

        public DoubleMatcher.Builder getDoubleMatchBuilder() {
            return getDoubleMatchFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public DoubleMatcherOrBuilder getDoubleMatchOrBuilder() {
            SingleFieldBuilderV3<DoubleMatcher, DoubleMatcher.Builder, DoubleMatcherOrBuilder> singleFieldBuilderV3;
            int i = this.matchPatternCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.doubleMatchBuilder_) != null) {
                return (DoubleMatcherOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (DoubleMatcher) this.matchPattern_;
            }
            return DoubleMatcher.getDefaultInstance();
        }

        private SingleFieldBuilderV3<DoubleMatcher, DoubleMatcher.Builder, DoubleMatcherOrBuilder> getDoubleMatchFieldBuilder() {
            if (this.doubleMatchBuilder_ == null) {
                if (this.matchPatternCase_ != 2) {
                    this.matchPattern_ = DoubleMatcher.getDefaultInstance();
                }
                this.doubleMatchBuilder_ = new SingleFieldBuilderV3<>((DoubleMatcher) this.matchPattern_, getParentForChildren(), isClean());
                this.matchPattern_ = null;
            }
            this.matchPatternCase_ = 2;
            onChanged();
            return this.doubleMatchBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public StringMatcher getStringMatch() {
            SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.stringMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.matchPatternCase_ == 3) {
                    return (StringMatcher) this.matchPattern_;
                }
                return StringMatcher.getDefaultInstance();
            }
            if (this.matchPatternCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return StringMatcher.getDefaultInstance();
        }

        public Builder setStringMatch(StringMatcher stringMatcher) {
            SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.stringMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                stringMatcher.getClass();
                this.matchPattern_ = stringMatcher;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(stringMatcher);
            }
            this.matchPatternCase_ = 3;
            return this;
        }

        public Builder setStringMatch(StringMatcher.Builder builder) {
            SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.stringMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.matchPattern_ = builder.m33509build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m33509build());
            }
            this.matchPatternCase_ = 3;
            return this;
        }

        public Builder mergeStringMatch(StringMatcher stringMatcher) {
            SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.stringMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.matchPatternCase_ != 3 || this.matchPattern_ == StringMatcher.getDefaultInstance()) {
                    this.matchPattern_ = stringMatcher;
                } else {
                    this.matchPattern_ = StringMatcher.newBuilder((StringMatcher) this.matchPattern_).mergeFrom(stringMatcher).m33511buildPartial();
                }
                onChanged();
            } else {
                if (this.matchPatternCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(stringMatcher);
                }
                this.stringMatchBuilder_.setMessage(stringMatcher);
            }
            this.matchPatternCase_ = 3;
            return this;
        }

        public Builder clearStringMatch() {
            SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.stringMatchBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.matchPatternCase_ == 3) {
                    this.matchPatternCase_ = 0;
                    this.matchPattern_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.matchPatternCase_ == 3) {
                this.matchPatternCase_ = 0;
                this.matchPattern_ = null;
                onChanged();
            }
            return this;
        }

        public StringMatcher.Builder getStringMatchBuilder() {
            return getStringMatchFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public StringMatcherOrBuilder getStringMatchOrBuilder() {
            SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3;
            int i = this.matchPatternCase_;
            if (i == 3 && (singleFieldBuilderV3 = this.stringMatchBuilder_) != null) {
                return (StringMatcherOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 3) {
                return (StringMatcher) this.matchPattern_;
            }
            return StringMatcher.getDefaultInstance();
        }

        private SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> getStringMatchFieldBuilder() {
            if (this.stringMatchBuilder_ == null) {
                if (this.matchPatternCase_ != 3) {
                    this.matchPattern_ = StringMatcher.getDefaultInstance();
                }
                this.stringMatchBuilder_ = new SingleFieldBuilderV3<>((StringMatcher) this.matchPattern_, getParentForChildren(), isClean());
                this.matchPattern_ = null;
            }
            this.matchPatternCase_ = 3;
            onChanged();
            return this.stringMatchBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public boolean getBoolMatch() {
            if (this.matchPatternCase_ == 4) {
                return ((Boolean) this.matchPattern_).booleanValue();
            }
            return false;
        }

        public Builder setBoolMatch(boolean z) {
            this.matchPatternCase_ = 4;
            this.matchPattern_ = Boolean.valueOf(z);
            onChanged();
            return this;
        }

        public Builder clearBoolMatch() {
            if (this.matchPatternCase_ == 4) {
                this.matchPatternCase_ = 0;
                this.matchPattern_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public boolean getPresentMatch() {
            if (this.matchPatternCase_ == 5) {
                return ((Boolean) this.matchPattern_).booleanValue();
            }
            return false;
        }

        public Builder setPresentMatch(boolean z) {
            this.matchPatternCase_ = 5;
            this.matchPattern_ = Boolean.valueOf(z);
            onChanged();
            return this;
        }

        public Builder clearPresentMatch() {
            if (this.matchPatternCase_ == 5) {
                this.matchPatternCase_ = 0;
                this.matchPattern_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public ListMatcher getListMatch() {
            SingleFieldBuilderV3<ListMatcher, ListMatcher.Builder, ListMatcherOrBuilder> singleFieldBuilderV3 = this.listMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.matchPatternCase_ == 6) {
                    return (ListMatcher) this.matchPattern_;
                }
                return ListMatcher.getDefaultInstance();
            }
            if (this.matchPatternCase_ == 6) {
                return singleFieldBuilderV3.getMessage();
            }
            return ListMatcher.getDefaultInstance();
        }

        public Builder setListMatch(ListMatcher listMatcher) {
            SingleFieldBuilderV3<ListMatcher, ListMatcher.Builder, ListMatcherOrBuilder> singleFieldBuilderV3 = this.listMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                listMatcher.getClass();
                this.matchPattern_ = listMatcher;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(listMatcher);
            }
            this.matchPatternCase_ = 6;
            return this;
        }

        public Builder setListMatch(ListMatcher.Builder builder) {
            SingleFieldBuilderV3<ListMatcher, ListMatcher.Builder, ListMatcherOrBuilder> singleFieldBuilderV3 = this.listMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.matchPattern_ = builder.m33141build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m33141build());
            }
            this.matchPatternCase_ = 6;
            return this;
        }

        public Builder mergeListMatch(ListMatcher listMatcher) {
            SingleFieldBuilderV3<ListMatcher, ListMatcher.Builder, ListMatcherOrBuilder> singleFieldBuilderV3 = this.listMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.matchPatternCase_ != 6 || this.matchPattern_ == ListMatcher.getDefaultInstance()) {
                    this.matchPattern_ = listMatcher;
                } else {
                    this.matchPattern_ = ListMatcher.newBuilder((ListMatcher) this.matchPattern_).mergeFrom(listMatcher).m33143buildPartial();
                }
                onChanged();
            } else {
                if (this.matchPatternCase_ == 6) {
                    singleFieldBuilderV3.mergeFrom(listMatcher);
                }
                this.listMatchBuilder_.setMessage(listMatcher);
            }
            this.matchPatternCase_ = 6;
            return this;
        }

        public Builder clearListMatch() {
            SingleFieldBuilderV3<ListMatcher, ListMatcher.Builder, ListMatcherOrBuilder> singleFieldBuilderV3 = this.listMatchBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.matchPatternCase_ == 6) {
                    this.matchPatternCase_ = 0;
                    this.matchPattern_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.matchPatternCase_ == 6) {
                this.matchPatternCase_ = 0;
                this.matchPattern_ = null;
                onChanged();
            }
            return this;
        }

        public ListMatcher.Builder getListMatchBuilder() {
            return getListMatchFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcherOrBuilder
        public ListMatcherOrBuilder getListMatchOrBuilder() {
            SingleFieldBuilderV3<ListMatcher, ListMatcher.Builder, ListMatcherOrBuilder> singleFieldBuilderV3;
            int i = this.matchPatternCase_;
            if (i == 6 && (singleFieldBuilderV3 = this.listMatchBuilder_) != null) {
                return (ListMatcherOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 6) {
                return (ListMatcher) this.matchPattern_;
            }
            return ListMatcher.getDefaultInstance();
        }

        private SingleFieldBuilderV3<ListMatcher, ListMatcher.Builder, ListMatcherOrBuilder> getListMatchFieldBuilder() {
            if (this.listMatchBuilder_ == null) {
                if (this.matchPatternCase_ != 6) {
                    this.matchPattern_ = ListMatcher.getDefaultInstance();
                }
                this.listMatchBuilder_ = new SingleFieldBuilderV3<>((ListMatcher) this.matchPattern_, getParentForChildren(), isClean());
                this.matchPattern_ = null;
            }
            this.matchPatternCase_ = 6;
            onChanged();
            return this.listMatchBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m33589setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m33583mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$type$matcher$ValueMatcher$MatchPatternCase;

        static {
            int[] iArr = new int[MatchPatternCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$type$matcher$ValueMatcher$MatchPatternCase = iArr;
            try {
                iArr[MatchPatternCase.NULL_MATCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$matcher$ValueMatcher$MatchPatternCase[MatchPatternCase.DOUBLE_MATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$matcher$ValueMatcher$MatchPatternCase[MatchPatternCase.STRING_MATCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$matcher$ValueMatcher$MatchPatternCase[MatchPatternCase.BOOL_MATCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$matcher$ValueMatcher$MatchPatternCase[MatchPatternCase.PRESENT_MATCH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$matcher$ValueMatcher$MatchPatternCase[MatchPatternCase.LIST_MATCH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$matcher$ValueMatcher$MatchPatternCase[MatchPatternCase.MATCHPATTERN_NOT_SET.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }
}
