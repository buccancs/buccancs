package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

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
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.Int64Range;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.Int64RangeOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.RegexMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.RegexMatcherOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class HeaderMatcher extends GeneratedMessageV3 implements HeaderMatcherOrBuilder {
    public static final int EXACT_MATCH_FIELD_NUMBER = 4;
    public static final int INVERT_MATCH_FIELD_NUMBER = 8;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int PREFIX_MATCH_FIELD_NUMBER = 9;
    public static final int PRESENT_MATCH_FIELD_NUMBER = 7;
    public static final int RANGE_MATCH_FIELD_NUMBER = 6;
    public static final int REGEX_MATCH_FIELD_NUMBER = 5;
    public static final int SAFE_REGEX_MATCH_FIELD_NUMBER = 11;
    public static final int SUFFIX_MATCH_FIELD_NUMBER = 10;
    private static final long serialVersionUID = 0;
    private static final HeaderMatcher DEFAULT_INSTANCE = new HeaderMatcher();
    private static final Parser<HeaderMatcher> PARSER = new AbstractParser<HeaderMatcher>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcher.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public HeaderMatcher m18049parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HeaderMatcher(codedInputStream, extensionRegistryLite);
        }
    };
    private int headerMatchSpecifierCase_;
    private Object headerMatchSpecifier_;
    private boolean invertMatch_;
    private byte memoizedIsInitialized;
    private volatile Object name_;

    private HeaderMatcher(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.headerMatchSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private HeaderMatcher() {
        this.headerMatchSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    private HeaderMatcher(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 34) {
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.headerMatchSpecifierCase_ = 4;
                                this.headerMatchSpecifier_ = stringRequireUtf8;
                            } else if (tag != 42) {
                                if (tag == 50) {
                                    Int64Range.Builder builderM32952toBuilder = this.headerMatchSpecifierCase_ == 6 ? ((Int64Range) this.headerMatchSpecifier_).m32952toBuilder() : null;
                                    MessageLite message = codedInputStream.readMessage(Int64Range.parser(), extensionRegistryLite);
                                    this.headerMatchSpecifier_ = message;
                                    if (builderM32952toBuilder != null) {
                                        builderM32952toBuilder.mergeFrom((Int64Range) message);
                                        this.headerMatchSpecifier_ = builderM32952toBuilder.m32959buildPartial();
                                    }
                                    this.headerMatchSpecifierCase_ = 6;
                                } else if (tag == 56) {
                                    this.headerMatchSpecifierCase_ = 7;
                                    this.headerMatchSpecifier_ = Boolean.valueOf(codedInputStream.readBool());
                                } else if (tag == 64) {
                                    this.invertMatch_ = codedInputStream.readBool();
                                } else if (tag == 74) {
                                    String stringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                                    this.headerMatchSpecifierCase_ = 9;
                                    this.headerMatchSpecifier_ = stringRequireUtf82;
                                } else if (tag == 82) {
                                    String stringRequireUtf83 = codedInputStream.readStringRequireUtf8();
                                    this.headerMatchSpecifierCase_ = 10;
                                    this.headerMatchSpecifier_ = stringRequireUtf83;
                                } else if (tag == 90) {
                                    RegexMatcher.Builder builderM33412toBuilder = this.headerMatchSpecifierCase_ == 11 ? ((RegexMatcher) this.headerMatchSpecifier_).m33412toBuilder() : null;
                                    MessageLite message2 = codedInputStream.readMessage(RegexMatcher.parser(), extensionRegistryLite);
                                    this.headerMatchSpecifier_ = message2;
                                    if (builderM33412toBuilder != null) {
                                        builderM33412toBuilder.mergeFrom((RegexMatcher) message2);
                                        this.headerMatchSpecifier_ = builderM33412toBuilder.m33419buildPartial();
                                    }
                                    this.headerMatchSpecifierCase_ = 11;
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                String stringRequireUtf84 = codedInputStream.readStringRequireUtf8();
                                this.headerMatchSpecifierCase_ = 5;
                                this.headerMatchSpecifier_ = stringRequireUtf84;
                            }
                        }
                        z = true;
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

    public static HeaderMatcher getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HeaderMatcher> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RouteComponentsProto.internal_static_envoy_api_v2_route_HeaderMatcher_descriptor;
    }

    public static HeaderMatcher parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HeaderMatcher) PARSER.parseFrom(byteBuffer);
    }

    public static HeaderMatcher parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HeaderMatcher) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HeaderMatcher parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HeaderMatcher) PARSER.parseFrom(byteString);
    }

    public static HeaderMatcher parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HeaderMatcher) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HeaderMatcher parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HeaderMatcher) PARSER.parseFrom(bArr);
    }

    public static HeaderMatcher parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HeaderMatcher) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HeaderMatcher parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HeaderMatcher parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HeaderMatcher parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HeaderMatcher parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HeaderMatcher parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HeaderMatcher parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m18047toBuilder();
    }

    public static Builder newBuilder(HeaderMatcher headerMatcher) {
        return DEFAULT_INSTANCE.m18047toBuilder().mergeFrom(headerMatcher);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public HeaderMatcher m18042getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public boolean getInvertMatch() {
        return this.invertMatch_;
    }

    public Parser<HeaderMatcher> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public boolean hasRangeMatch() {
        return this.headerMatchSpecifierCase_ == 6;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public boolean hasSafeRegexMatch() {
        return this.headerMatchSpecifierCase_ == 11;
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
        return new HeaderMatcher();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RouteComponentsProto.internal_static_envoy_api_v2_route_HeaderMatcher_fieldAccessorTable.ensureFieldAccessorsInitialized(HeaderMatcher.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public HeaderMatchSpecifierCase getHeaderMatchSpecifierCase() {
        return HeaderMatchSpecifierCase.forNumber(this.headerMatchSpecifierCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public String getExactMatch() {
        String str = this.headerMatchSpecifierCase_ == 4 ? this.headerMatchSpecifier_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.headerMatchSpecifierCase_ == 4) {
            this.headerMatchSpecifier_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public ByteString getExactMatchBytes() {
        String str = this.headerMatchSpecifierCase_ == 4 ? this.headerMatchSpecifier_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.headerMatchSpecifierCase_ == 4) {
                this.headerMatchSpecifier_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    @Deprecated
    public String getRegexMatch() {
        String str = this.headerMatchSpecifierCase_ == 5 ? this.headerMatchSpecifier_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.headerMatchSpecifierCase_ == 5) {
            this.headerMatchSpecifier_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    @Deprecated
    public ByteString getRegexMatchBytes() {
        String str = this.headerMatchSpecifierCase_ == 5 ? this.headerMatchSpecifier_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.headerMatchSpecifierCase_ == 5) {
                this.headerMatchSpecifier_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public RegexMatcher getSafeRegexMatch() {
        if (this.headerMatchSpecifierCase_ == 11) {
            return (RegexMatcher) this.headerMatchSpecifier_;
        }
        return RegexMatcher.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public RegexMatcherOrBuilder getSafeRegexMatchOrBuilder() {
        if (this.headerMatchSpecifierCase_ == 11) {
            return (RegexMatcher) this.headerMatchSpecifier_;
        }
        return RegexMatcher.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public Int64Range getRangeMatch() {
        if (this.headerMatchSpecifierCase_ == 6) {
            return (Int64Range) this.headerMatchSpecifier_;
        }
        return Int64Range.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public Int64RangeOrBuilder getRangeMatchOrBuilder() {
        if (this.headerMatchSpecifierCase_ == 6) {
            return (Int64Range) this.headerMatchSpecifier_;
        }
        return Int64Range.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public boolean getPresentMatch() {
        if (this.headerMatchSpecifierCase_ == 7) {
            return ((Boolean) this.headerMatchSpecifier_).booleanValue();
        }
        return false;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public String getPrefixMatch() {
        String str = this.headerMatchSpecifierCase_ == 9 ? this.headerMatchSpecifier_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.headerMatchSpecifierCase_ == 9) {
            this.headerMatchSpecifier_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public ByteString getPrefixMatchBytes() {
        String str = this.headerMatchSpecifierCase_ == 9 ? this.headerMatchSpecifier_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.headerMatchSpecifierCase_ == 9) {
                this.headerMatchSpecifier_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public String getSuffixMatch() {
        String str = this.headerMatchSpecifierCase_ == 10 ? this.headerMatchSpecifier_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.headerMatchSpecifierCase_ == 10) {
            this.headerMatchSpecifier_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
    public ByteString getSuffixMatchBytes() {
        String str = this.headerMatchSpecifierCase_ == 10 ? this.headerMatchSpecifier_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.headerMatchSpecifierCase_ == 10) {
                this.headerMatchSpecifier_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.headerMatchSpecifierCase_ == 4) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.headerMatchSpecifier_);
        }
        if (this.headerMatchSpecifierCase_ == 5) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.headerMatchSpecifier_);
        }
        if (this.headerMatchSpecifierCase_ == 6) {
            codedOutputStream.writeMessage(6, (Int64Range) this.headerMatchSpecifier_);
        }
        if (this.headerMatchSpecifierCase_ == 7) {
            codedOutputStream.writeBool(7, ((Boolean) this.headerMatchSpecifier_).booleanValue());
        }
        boolean z = this.invertMatch_;
        if (z) {
            codedOutputStream.writeBool(8, z);
        }
        if (this.headerMatchSpecifierCase_ == 9) {
            GeneratedMessageV3.writeString(codedOutputStream, 9, this.headerMatchSpecifier_);
        }
        if (this.headerMatchSpecifierCase_ == 10) {
            GeneratedMessageV3.writeString(codedOutputStream, 10, this.headerMatchSpecifier_);
        }
        if (this.headerMatchSpecifierCase_ == 11) {
            codedOutputStream.writeMessage(11, (RegexMatcher) this.headerMatchSpecifier_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (this.headerMatchSpecifierCase_ == 4) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.headerMatchSpecifier_);
        }
        if (this.headerMatchSpecifierCase_ == 5) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(5, this.headerMatchSpecifier_);
        }
        if (this.headerMatchSpecifierCase_ == 6) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(6, (Int64Range) this.headerMatchSpecifier_);
        }
        if (this.headerMatchSpecifierCase_ == 7) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(7, ((Boolean) this.headerMatchSpecifier_).booleanValue());
        }
        boolean z = this.invertMatch_;
        if (z) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(8, z);
        }
        if (this.headerMatchSpecifierCase_ == 9) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(9, this.headerMatchSpecifier_);
        }
        if (this.headerMatchSpecifierCase_ == 10) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(10, this.headerMatchSpecifier_);
        }
        if (this.headerMatchSpecifierCase_ == 11) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(11, (RegexMatcher) this.headerMatchSpecifier_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HeaderMatcher)) {
            return super.equals(obj);
        }
        HeaderMatcher headerMatcher = (HeaderMatcher) obj;
        if (!getName().equals(headerMatcher.getName()) || getInvertMatch() != headerMatcher.getInvertMatch() || !getHeaderMatchSpecifierCase().equals(headerMatcher.getHeaderMatchSpecifierCase())) {
            return false;
        }
        switch (this.headerMatchSpecifierCase_) {
            case 4:
                if (!getExactMatch().equals(headerMatcher.getExactMatch())) {
                    return false;
                }
                break;
            case 5:
                if (!getRegexMatch().equals(headerMatcher.getRegexMatch())) {
                    return false;
                }
                break;
            case 6:
                if (!getRangeMatch().equals(headerMatcher.getRangeMatch())) {
                    return false;
                }
                break;
            case 7:
                if (getPresentMatch() != headerMatcher.getPresentMatch()) {
                    return false;
                }
                break;
            case 9:
                if (!getPrefixMatch().equals(headerMatcher.getPrefixMatch())) {
                    return false;
                }
                break;
            case 10:
                if (!getSuffixMatch().equals(headerMatcher.getSuffixMatch())) {
                    return false;
                }
                break;
            case 11:
                if (!getSafeRegexMatch().equals(headerMatcher.getSafeRegexMatch())) {
                    return false;
                }
                break;
        }
        return this.unknownFields.equals(headerMatcher.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 8) * 53) + Internal.hashBoolean(getInvertMatch());
        switch (this.headerMatchSpecifierCase_) {
            case 4:
                i = ((iHashCode2 * 37) + 4) * 53;
                iHashCode = getExactMatch().hashCode();
                break;
            case 5:
                i = ((iHashCode2 * 37) + 5) * 53;
                iHashCode = getRegexMatch().hashCode();
                break;
            case 6:
                i = ((iHashCode2 * 37) + 6) * 53;
                iHashCode = getRangeMatch().hashCode();
                break;
            case 7:
                i = ((iHashCode2 * 37) + 7) * 53;
                iHashCode = Internal.hashBoolean(getPresentMatch());
                break;
            case 8:
            default:
                int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode3;
                return iHashCode3;
            case 9:
                i = ((iHashCode2 * 37) + 9) * 53;
                iHashCode = getPrefixMatch().hashCode();
                break;
            case 10:
                i = ((iHashCode2 * 37) + 10) * 53;
                iHashCode = getSuffixMatch().hashCode();
                break;
            case 11:
                i = ((iHashCode2 * 37) + 11) * 53;
                iHashCode = getSafeRegexMatch().hashCode();
                break;
        }
        iHashCode2 = i + iHashCode;
        int iHashCode32 = (iHashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode32;
        return iHashCode32;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m18044newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m18047toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum HeaderMatchSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        EXACT_MATCH(4),
        REGEX_MATCH(5),
        SAFE_REGEX_MATCH(11),
        RANGE_MATCH(6),
        PRESENT_MATCH(7),
        PREFIX_MATCH(9),
        SUFFIX_MATCH(10),
        HEADERMATCHSPECIFIER_NOT_SET(0);

        private final int value;

        HeaderMatchSpecifierCase(int i) {
            this.value = i;
        }

        public static HeaderMatchSpecifierCase forNumber(int i) {
            if (i == 0) {
                return HEADERMATCHSPECIFIER_NOT_SET;
            }
            if (i == 4) {
                return EXACT_MATCH;
            }
            if (i == 5) {
                return REGEX_MATCH;
            }
            if (i == 6) {
                return RANGE_MATCH;
            }
            if (i == 7) {
                return PRESENT_MATCH;
            }
            switch (i) {
                case 9:
                    return PREFIX_MATCH;
                case 10:
                    return SUFFIX_MATCH;
                case 11:
                    return SAFE_REGEX_MATCH;
                default:
                    return null;
            }
        }

        @Deprecated
        public static HeaderMatchSpecifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HeaderMatcherOrBuilder {
        private int headerMatchSpecifierCase_;
        private Object headerMatchSpecifier_;
        private boolean invertMatch_;
        private Object name_;
        private SingleFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> rangeMatchBuilder_;
        private SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> safeRegexMatchBuilder_;

        private Builder() {
            this.headerMatchSpecifierCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.headerMatchSpecifierCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_HeaderMatcher_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public boolean getInvertMatch() {
            return this.invertMatch_;
        }

        public Builder setInvertMatch(boolean z) {
            this.invertMatch_ = z;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public boolean hasRangeMatch() {
            return this.headerMatchSpecifierCase_ == 6;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public boolean hasSafeRegexMatch() {
            return this.headerMatchSpecifierCase_ == 11;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_HeaderMatcher_fieldAccessorTable.ensureFieldAccessorsInitialized(HeaderMatcher.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = HeaderMatcher.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18058clear() {
            super.clear();
            this.name_ = "";
            this.invertMatch_ = false;
            this.headerMatchSpecifierCase_ = 0;
            this.headerMatchSpecifier_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_HeaderMatcher_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HeaderMatcher m18071getDefaultInstanceForType() {
            return HeaderMatcher.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HeaderMatcher m18052build() throws UninitializedMessageException {
            HeaderMatcher headerMatcherM18054buildPartial = m18054buildPartial();
            if (headerMatcherM18054buildPartial.isInitialized()) {
                return headerMatcherM18054buildPartial;
            }
            throw newUninitializedMessageException(headerMatcherM18054buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HeaderMatcher m18054buildPartial() {
            HeaderMatcher headerMatcher = new HeaderMatcher(this);
            headerMatcher.name_ = this.name_;
            if (this.headerMatchSpecifierCase_ == 4) {
                headerMatcher.headerMatchSpecifier_ = this.headerMatchSpecifier_;
            }
            if (this.headerMatchSpecifierCase_ == 5) {
                headerMatcher.headerMatchSpecifier_ = this.headerMatchSpecifier_;
            }
            if (this.headerMatchSpecifierCase_ == 11) {
                SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexMatchBuilder_;
                if (singleFieldBuilderV3 == null) {
                    headerMatcher.headerMatchSpecifier_ = this.headerMatchSpecifier_;
                } else {
                    headerMatcher.headerMatchSpecifier_ = singleFieldBuilderV3.build();
                }
            }
            if (this.headerMatchSpecifierCase_ == 6) {
                SingleFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> singleFieldBuilderV32 = this.rangeMatchBuilder_;
                if (singleFieldBuilderV32 == null) {
                    headerMatcher.headerMatchSpecifier_ = this.headerMatchSpecifier_;
                } else {
                    headerMatcher.headerMatchSpecifier_ = singleFieldBuilderV32.build();
                }
            }
            if (this.headerMatchSpecifierCase_ == 7) {
                headerMatcher.headerMatchSpecifier_ = this.headerMatchSpecifier_;
            }
            if (this.headerMatchSpecifierCase_ == 9) {
                headerMatcher.headerMatchSpecifier_ = this.headerMatchSpecifier_;
            }
            if (this.headerMatchSpecifierCase_ == 10) {
                headerMatcher.headerMatchSpecifier_ = this.headerMatchSpecifier_;
            }
            headerMatcher.invertMatch_ = this.invertMatch_;
            headerMatcher.headerMatchSpecifierCase_ = this.headerMatchSpecifierCase_;
            onBuilt();
            return headerMatcher;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18070clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18082setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18060clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18063clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18084setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18050addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18075mergeFrom(Message message) {
            if (message instanceof HeaderMatcher) {
                return mergeFrom((HeaderMatcher) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HeaderMatcher headerMatcher) {
            if (headerMatcher == HeaderMatcher.getDefaultInstance()) {
                return this;
            }
            if (!headerMatcher.getName().isEmpty()) {
                this.name_ = headerMatcher.name_;
                onChanged();
            }
            if (headerMatcher.getInvertMatch()) {
                setInvertMatch(headerMatcher.getInvertMatch());
            }
            switch (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$api$v2$route$HeaderMatcher$HeaderMatchSpecifierCase[headerMatcher.getHeaderMatchSpecifierCase().ordinal()]) {
                case 1:
                    this.headerMatchSpecifierCase_ = 4;
                    this.headerMatchSpecifier_ = headerMatcher.headerMatchSpecifier_;
                    onChanged();
                    break;
                case 2:
                    this.headerMatchSpecifierCase_ = 5;
                    this.headerMatchSpecifier_ = headerMatcher.headerMatchSpecifier_;
                    onChanged();
                    break;
                case 3:
                    mergeSafeRegexMatch(headerMatcher.getSafeRegexMatch());
                    break;
                case 4:
                    mergeRangeMatch(headerMatcher.getRangeMatch());
                    break;
                case 5:
                    setPresentMatch(headerMatcher.getPresentMatch());
                    break;
                case 6:
                    this.headerMatchSpecifierCase_ = 9;
                    this.headerMatchSpecifier_ = headerMatcher.headerMatchSpecifier_;
                    onChanged();
                    break;
                case 7:
                    this.headerMatchSpecifierCase_ = 10;
                    this.headerMatchSpecifier_ = headerMatcher.headerMatchSpecifier_;
                    onChanged();
                    break;
            }
            m18080mergeUnknownFields(headerMatcher.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcher.Builder m18076mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcher.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcher r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcher) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcher r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcher) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcher.Builder.m18076mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcher$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public HeaderMatchSpecifierCase getHeaderMatchSpecifierCase() {
            return HeaderMatchSpecifierCase.forNumber(this.headerMatchSpecifierCase_);
        }

        public Builder clearHeaderMatchSpecifier() {
            this.headerMatchSpecifierCase_ = 0;
            this.headerMatchSpecifier_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
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
            HeaderMatcher.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = HeaderMatcher.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public String getExactMatch() {
            String str = this.headerMatchSpecifierCase_ == 4 ? this.headerMatchSpecifier_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.headerMatchSpecifierCase_ == 4) {
                    this.headerMatchSpecifier_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setExactMatch(String str) {
            str.getClass();
            this.headerMatchSpecifierCase_ = 4;
            this.headerMatchSpecifier_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public ByteString getExactMatchBytes() {
            String str = this.headerMatchSpecifierCase_ == 4 ? this.headerMatchSpecifier_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.headerMatchSpecifierCase_ == 4) {
                    this.headerMatchSpecifier_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setExactMatchBytes(ByteString byteString) {
            byteString.getClass();
            HeaderMatcher.checkByteStringIsUtf8(byteString);
            this.headerMatchSpecifierCase_ = 4;
            this.headerMatchSpecifier_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearExactMatch() {
            if (this.headerMatchSpecifierCase_ == 4) {
                this.headerMatchSpecifierCase_ = 0;
                this.headerMatchSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        @Deprecated
        public String getRegexMatch() {
            String str = this.headerMatchSpecifierCase_ == 5 ? this.headerMatchSpecifier_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.headerMatchSpecifierCase_ == 5) {
                    this.headerMatchSpecifier_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Deprecated
        public Builder setRegexMatch(String str) {
            str.getClass();
            this.headerMatchSpecifierCase_ = 5;
            this.headerMatchSpecifier_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        @Deprecated
        public ByteString getRegexMatchBytes() {
            String str = this.headerMatchSpecifierCase_ == 5 ? this.headerMatchSpecifier_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.headerMatchSpecifierCase_ == 5) {
                    this.headerMatchSpecifier_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        @Deprecated
        public Builder setRegexMatchBytes(ByteString byteString) {
            byteString.getClass();
            HeaderMatcher.checkByteStringIsUtf8(byteString);
            this.headerMatchSpecifierCase_ = 5;
            this.headerMatchSpecifier_ = byteString;
            onChanged();
            return this;
        }

        @Deprecated
        public Builder clearRegexMatch() {
            if (this.headerMatchSpecifierCase_ == 5) {
                this.headerMatchSpecifierCase_ = 0;
                this.headerMatchSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public RegexMatcher getSafeRegexMatch() {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.headerMatchSpecifierCase_ == 11) {
                    return (RegexMatcher) this.headerMatchSpecifier_;
                }
                return RegexMatcher.getDefaultInstance();
            }
            if (this.headerMatchSpecifierCase_ == 11) {
                return singleFieldBuilderV3.getMessage();
            }
            return RegexMatcher.getDefaultInstance();
        }

        public Builder setSafeRegexMatch(RegexMatcher regexMatcher) {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                regexMatcher.getClass();
                this.headerMatchSpecifier_ = regexMatcher;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(regexMatcher);
            }
            this.headerMatchSpecifierCase_ = 11;
            return this;
        }

        public Builder setSafeRegexMatch(RegexMatcher.Builder builder) {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.headerMatchSpecifier_ = builder.m33417build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m33417build());
            }
            this.headerMatchSpecifierCase_ = 11;
            return this;
        }

        public Builder mergeSafeRegexMatch(RegexMatcher regexMatcher) {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.headerMatchSpecifierCase_ != 11 || this.headerMatchSpecifier_ == RegexMatcher.getDefaultInstance()) {
                    this.headerMatchSpecifier_ = regexMatcher;
                } else {
                    this.headerMatchSpecifier_ = RegexMatcher.newBuilder((RegexMatcher) this.headerMatchSpecifier_).mergeFrom(regexMatcher).m33419buildPartial();
                }
                onChanged();
            } else {
                if (this.headerMatchSpecifierCase_ == 11) {
                    singleFieldBuilderV3.mergeFrom(regexMatcher);
                }
                this.safeRegexMatchBuilder_.setMessage(regexMatcher);
            }
            this.headerMatchSpecifierCase_ = 11;
            return this;
        }

        public Builder clearSafeRegexMatch() {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3 = this.safeRegexMatchBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.headerMatchSpecifierCase_ == 11) {
                    this.headerMatchSpecifierCase_ = 0;
                    this.headerMatchSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.headerMatchSpecifierCase_ == 11) {
                this.headerMatchSpecifierCase_ = 0;
                this.headerMatchSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public RegexMatcher.Builder getSafeRegexMatchBuilder() {
            return getSafeRegexMatchFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public RegexMatcherOrBuilder getSafeRegexMatchOrBuilder() {
            SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> singleFieldBuilderV3;
            int i = this.headerMatchSpecifierCase_;
            if (i == 11 && (singleFieldBuilderV3 = this.safeRegexMatchBuilder_) != null) {
                return (RegexMatcherOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 11) {
                return (RegexMatcher) this.headerMatchSpecifier_;
            }
            return RegexMatcher.getDefaultInstance();
        }

        private SingleFieldBuilderV3<RegexMatcher, RegexMatcher.Builder, RegexMatcherOrBuilder> getSafeRegexMatchFieldBuilder() {
            if (this.safeRegexMatchBuilder_ == null) {
                if (this.headerMatchSpecifierCase_ != 11) {
                    this.headerMatchSpecifier_ = RegexMatcher.getDefaultInstance();
                }
                this.safeRegexMatchBuilder_ = new SingleFieldBuilderV3<>((RegexMatcher) this.headerMatchSpecifier_, getParentForChildren(), isClean());
                this.headerMatchSpecifier_ = null;
            }
            this.headerMatchSpecifierCase_ = 11;
            onChanged();
            return this.safeRegexMatchBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public Int64Range getRangeMatch() {
            SingleFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> singleFieldBuilderV3 = this.rangeMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.headerMatchSpecifierCase_ == 6) {
                    return (Int64Range) this.headerMatchSpecifier_;
                }
                return Int64Range.getDefaultInstance();
            }
            if (this.headerMatchSpecifierCase_ == 6) {
                return singleFieldBuilderV3.getMessage();
            }
            return Int64Range.getDefaultInstance();
        }

        public Builder setRangeMatch(Int64Range int64Range) {
            SingleFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> singleFieldBuilderV3 = this.rangeMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                int64Range.getClass();
                this.headerMatchSpecifier_ = int64Range;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(int64Range);
            }
            this.headerMatchSpecifierCase_ = 6;
            return this;
        }

        public Builder setRangeMatch(Int64Range.Builder builder) {
            SingleFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> singleFieldBuilderV3 = this.rangeMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.headerMatchSpecifier_ = builder.m32957build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m32957build());
            }
            this.headerMatchSpecifierCase_ = 6;
            return this;
        }

        public Builder mergeRangeMatch(Int64Range int64Range) {
            SingleFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> singleFieldBuilderV3 = this.rangeMatchBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.headerMatchSpecifierCase_ != 6 || this.headerMatchSpecifier_ == Int64Range.getDefaultInstance()) {
                    this.headerMatchSpecifier_ = int64Range;
                } else {
                    this.headerMatchSpecifier_ = Int64Range.newBuilder((Int64Range) this.headerMatchSpecifier_).mergeFrom(int64Range).m32959buildPartial();
                }
                onChanged();
            } else {
                if (this.headerMatchSpecifierCase_ == 6) {
                    singleFieldBuilderV3.mergeFrom(int64Range);
                }
                this.rangeMatchBuilder_.setMessage(int64Range);
            }
            this.headerMatchSpecifierCase_ = 6;
            return this;
        }

        public Builder clearRangeMatch() {
            SingleFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> singleFieldBuilderV3 = this.rangeMatchBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.headerMatchSpecifierCase_ == 6) {
                    this.headerMatchSpecifierCase_ = 0;
                    this.headerMatchSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.headerMatchSpecifierCase_ == 6) {
                this.headerMatchSpecifierCase_ = 0;
                this.headerMatchSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public Int64Range.Builder getRangeMatchBuilder() {
            return getRangeMatchFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public Int64RangeOrBuilder getRangeMatchOrBuilder() {
            SingleFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> singleFieldBuilderV3;
            int i = this.headerMatchSpecifierCase_;
            if (i == 6 && (singleFieldBuilderV3 = this.rangeMatchBuilder_) != null) {
                return (Int64RangeOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 6) {
                return (Int64Range) this.headerMatchSpecifier_;
            }
            return Int64Range.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> getRangeMatchFieldBuilder() {
            if (this.rangeMatchBuilder_ == null) {
                if (this.headerMatchSpecifierCase_ != 6) {
                    this.headerMatchSpecifier_ = Int64Range.getDefaultInstance();
                }
                this.rangeMatchBuilder_ = new SingleFieldBuilderV3<>((Int64Range) this.headerMatchSpecifier_, getParentForChildren(), isClean());
                this.headerMatchSpecifier_ = null;
            }
            this.headerMatchSpecifierCase_ = 6;
            onChanged();
            return this.rangeMatchBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public boolean getPresentMatch() {
            if (this.headerMatchSpecifierCase_ == 7) {
                return ((Boolean) this.headerMatchSpecifier_).booleanValue();
            }
            return false;
        }

        public Builder setPresentMatch(boolean z) {
            this.headerMatchSpecifierCase_ = 7;
            this.headerMatchSpecifier_ = Boolean.valueOf(z);
            onChanged();
            return this;
        }

        public Builder clearPresentMatch() {
            if (this.headerMatchSpecifierCase_ == 7) {
                this.headerMatchSpecifierCase_ = 0;
                this.headerMatchSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public String getPrefixMatch() {
            String str = this.headerMatchSpecifierCase_ == 9 ? this.headerMatchSpecifier_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.headerMatchSpecifierCase_ == 9) {
                    this.headerMatchSpecifier_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setPrefixMatch(String str) {
            str.getClass();
            this.headerMatchSpecifierCase_ = 9;
            this.headerMatchSpecifier_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public ByteString getPrefixMatchBytes() {
            String str = this.headerMatchSpecifierCase_ == 9 ? this.headerMatchSpecifier_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.headerMatchSpecifierCase_ == 9) {
                    this.headerMatchSpecifier_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setPrefixMatchBytes(ByteString byteString) {
            byteString.getClass();
            HeaderMatcher.checkByteStringIsUtf8(byteString);
            this.headerMatchSpecifierCase_ = 9;
            this.headerMatchSpecifier_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearPrefixMatch() {
            if (this.headerMatchSpecifierCase_ == 9) {
                this.headerMatchSpecifierCase_ = 0;
                this.headerMatchSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public String getSuffixMatch() {
            String str = this.headerMatchSpecifierCase_ == 10 ? this.headerMatchSpecifier_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.headerMatchSpecifierCase_ == 10) {
                    this.headerMatchSpecifier_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setSuffixMatch(String str) {
            str.getClass();
            this.headerMatchSpecifierCase_ = 10;
            this.headerMatchSpecifier_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder
        public ByteString getSuffixMatchBytes() {
            String str = this.headerMatchSpecifierCase_ == 10 ? this.headerMatchSpecifier_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.headerMatchSpecifierCase_ == 10) {
                    this.headerMatchSpecifier_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setSuffixMatchBytes(ByteString byteString) {
            byteString.getClass();
            HeaderMatcher.checkByteStringIsUtf8(byteString);
            this.headerMatchSpecifierCase_ = 10;
            this.headerMatchSpecifier_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearSuffixMatch() {
            if (this.headerMatchSpecifierCase_ == 10) {
                this.headerMatchSpecifierCase_ = 0;
                this.headerMatchSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public Builder clearInvertMatch() {
            this.invertMatch_ = false;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m18086setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m18080mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcher$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$api$v2$route$HeaderMatcher$HeaderMatchSpecifierCase;

        static {
            int[] iArr = new int[HeaderMatchSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$api$v2$route$HeaderMatcher$HeaderMatchSpecifierCase = iArr;
            try {
                iArr[HeaderMatchSpecifierCase.EXACT_MATCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$HeaderMatcher$HeaderMatchSpecifierCase[HeaderMatchSpecifierCase.REGEX_MATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$HeaderMatcher$HeaderMatchSpecifierCase[HeaderMatchSpecifierCase.SAFE_REGEX_MATCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$HeaderMatcher$HeaderMatchSpecifierCase[HeaderMatchSpecifierCase.RANGE_MATCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$HeaderMatcher$HeaderMatchSpecifierCase[HeaderMatchSpecifierCase.PRESENT_MATCH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$HeaderMatcher$HeaderMatchSpecifierCase[HeaderMatchSpecifierCase.PREFIX_MATCH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$HeaderMatcher$HeaderMatchSpecifierCase[HeaderMatchSpecifierCase.SUFFIX_MATCH.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$HeaderMatcher$HeaderMatchSpecifierCase[HeaderMatchSpecifierCase.HEADERMATCHSPECIFIER_NOT_SET.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }
}
