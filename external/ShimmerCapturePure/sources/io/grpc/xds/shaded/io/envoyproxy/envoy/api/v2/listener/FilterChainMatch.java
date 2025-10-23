package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener;

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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRange;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRangeOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class FilterChainMatch extends GeneratedMessageV3 implements FilterChainMatchOrBuilder {
    public static final int ADDRESS_SUFFIX_FIELD_NUMBER = 4;
    public static final int APPLICATION_PROTOCOLS_FIELD_NUMBER = 10;
    public static final int DESTINATION_PORT_FIELD_NUMBER = 8;
    public static final int PREFIX_RANGES_FIELD_NUMBER = 3;
    public static final int SERVER_NAMES_FIELD_NUMBER = 11;
    public static final int SOURCE_PORTS_FIELD_NUMBER = 7;
    public static final int SOURCE_PREFIX_RANGES_FIELD_NUMBER = 6;
    public static final int SOURCE_TYPE_FIELD_NUMBER = 12;
    public static final int SUFFIX_LEN_FIELD_NUMBER = 5;
    public static final int TRANSPORT_PROTOCOL_FIELD_NUMBER = 9;
    private static final long serialVersionUID = 0;
    private static final FilterChainMatch DEFAULT_INSTANCE = new FilterChainMatch();
    private static final Parser<FilterChainMatch> PARSER = new AbstractParser<FilterChainMatch>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatch.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public FilterChainMatch m17632parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new FilterChainMatch(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object addressSuffix_;
    private LazyStringList applicationProtocols_;
    private UInt32Value destinationPort_;
    private byte memoizedIsInitialized;
    private List<CidrRange> prefixRanges_;
    private LazyStringList serverNames_;
    private int sourcePortsMemoizedSerializedSize;
    private Internal.IntList sourcePorts_;
    private List<CidrRange> sourcePrefixRanges_;
    private int sourceType_;
    private UInt32Value suffixLen_;
    private volatile Object transportProtocol_;

    private FilterChainMatch(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.sourcePortsMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
    }

    private FilterChainMatch() {
        this.sourcePortsMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.prefixRanges_ = Collections.emptyList();
        this.addressSuffix_ = "";
        this.sourceType_ = 0;
        this.sourcePrefixRanges_ = Collections.emptyList();
        this.sourcePorts_ = emptyIntList();
        this.serverNames_ = LazyStringArrayList.EMPTY;
        this.transportProtocol_ = "";
        this.applicationProtocols_ = LazyStringArrayList.EMPTY;
    }

    private FilterChainMatch(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        UInt32Value.Builder builder;
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0:
                            z = true;
                        case 26:
                            if ((i & 1) == 0) {
                                this.prefixRanges_ = new ArrayList();
                                i |= 1;
                            }
                            this.prefixRanges_.add(codedInputStream.readMessage(CidrRange.parser(), extensionRegistryLite));
                        case 34:
                            this.addressSuffix_ = codedInputStream.readStringRequireUtf8();
                        case 42:
                            UInt32Value uInt32Value = this.suffixLen_;
                            builder = uInt32Value != null ? uInt32Value.toBuilder() : null;
                            UInt32Value message = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.suffixLen_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.suffixLen_ = builder.buildPartial();
                            }
                        case 50:
                            if ((i & 2) == 0) {
                                this.sourcePrefixRanges_ = new ArrayList();
                                i |= 2;
                            }
                            this.sourcePrefixRanges_.add(codedInputStream.readMessage(CidrRange.parser(), extensionRegistryLite));
                        case 56:
                            if ((i & 4) == 0) {
                                this.sourcePorts_ = newIntList();
                                i |= 4;
                            }
                            this.sourcePorts_.addInt(codedInputStream.readUInt32());
                        case 58:
                            int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & 4) == 0 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.sourcePorts_ = newIntList();
                                i |= 4;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.sourcePorts_.addInt(codedInputStream.readUInt32());
                            }
                            codedInputStream.popLimit(iPushLimit);
                            break;
                        case 66:
                            UInt32Value uInt32Value2 = this.destinationPort_;
                            builder = uInt32Value2 != null ? uInt32Value2.toBuilder() : null;
                            UInt32Value message2 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.destinationPort_ = message2;
                            if (builder != null) {
                                builder.mergeFrom(message2);
                                this.destinationPort_ = builder.buildPartial();
                            }
                        case 74:
                            this.transportProtocol_ = codedInputStream.readStringRequireUtf8();
                        case 82:
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if ((i & 16) == 0) {
                                this.applicationProtocols_ = new LazyStringArrayList();
                                i |= 16;
                            }
                            this.applicationProtocols_.add(stringRequireUtf8);
                        case RESET_TO_DEFAULT_CONFIGURATION_COMMAND_VALUE:
                            String stringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            if ((i & 8) == 0) {
                                this.serverNames_ = new LazyStringArrayList();
                                i |= 8;
                            }
                            this.serverNames_.add(stringRequireUtf82);
                        case GET_INTERNAL_EXP_POWER_ENABLE_COMMAND_VALUE:
                            this.sourceType_ = codedInputStream.readEnum();
                        default:
                            if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                z = true;
                            }
                    }
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if ((i & 1) != 0) {
                    this.prefixRanges_ = Collections.unmodifiableList(this.prefixRanges_);
                }
                if ((i & 2) != 0) {
                    this.sourcePrefixRanges_ = Collections.unmodifiableList(this.sourcePrefixRanges_);
                }
                if ((i & 4) != 0) {
                    this.sourcePorts_.makeImmutable();
                }
                if ((i & 16) != 0) {
                    this.applicationProtocols_ = this.applicationProtocols_.getUnmodifiableView();
                }
                if ((i & 8) != 0) {
                    this.serverNames_ = this.serverNames_.getUnmodifiableView();
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static FilterChainMatch getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FilterChainMatch> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ListenerComponentsProto.internal_static_envoy_api_v2_listener_FilterChainMatch_descriptor;
    }

    public static FilterChainMatch parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (FilterChainMatch) PARSER.parseFrom(byteBuffer);
    }

    public static FilterChainMatch parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FilterChainMatch) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static FilterChainMatch parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (FilterChainMatch) PARSER.parseFrom(byteString);
    }

    public static FilterChainMatch parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FilterChainMatch) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static FilterChainMatch parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (FilterChainMatch) PARSER.parseFrom(bArr);
    }

    public static FilterChainMatch parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FilterChainMatch) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static FilterChainMatch parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static FilterChainMatch parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static FilterChainMatch parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static FilterChainMatch parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static FilterChainMatch parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static FilterChainMatch parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m17630toBuilder();
    }

    public static Builder newBuilder(FilterChainMatch filterChainMatch) {
        return DEFAULT_INSTANCE.m17630toBuilder().mergeFrom(filterChainMatch);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    /* renamed from: getApplicationProtocolsList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo17623getApplicationProtocolsList() {
        return this.applicationProtocols_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public FilterChainMatch m17624getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<FilterChainMatch> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public List<CidrRange> getPrefixRangesList() {
        return this.prefixRanges_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public List<? extends CidrRangeOrBuilder> getPrefixRangesOrBuilderList() {
        return this.prefixRanges_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    /* renamed from: getServerNamesList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo17626getServerNamesList() {
        return this.serverNames_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public List<Integer> getSourcePortsList() {
        return this.sourcePorts_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public List<CidrRange> getSourcePrefixRangesList() {
        return this.sourcePrefixRanges_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public List<? extends CidrRangeOrBuilder> getSourcePrefixRangesOrBuilderList() {
        return this.sourcePrefixRanges_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public int getSourceTypeValue() {
        return this.sourceType_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public boolean hasDestinationPort() {
        return this.destinationPort_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public boolean hasSuffixLen() {
        return this.suffixLen_ != null;
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
        return new FilterChainMatch();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ListenerComponentsProto.internal_static_envoy_api_v2_listener_FilterChainMatch_fieldAccessorTable.ensureFieldAccessorsInitialized(FilterChainMatch.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public UInt32Value getDestinationPort() {
        UInt32Value uInt32Value = this.destinationPort_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public UInt32ValueOrBuilder getDestinationPortOrBuilder() {
        return getDestinationPort();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public int getPrefixRangesCount() {
        return this.prefixRanges_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public CidrRange getPrefixRanges(int i) {
        return this.prefixRanges_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public CidrRangeOrBuilder getPrefixRangesOrBuilder(int i) {
        return this.prefixRanges_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public String getAddressSuffix() {
        Object obj = this.addressSuffix_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.addressSuffix_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public ByteString getAddressSuffixBytes() {
        Object obj = this.addressSuffix_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.addressSuffix_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public UInt32Value getSuffixLen() {
        UInt32Value uInt32Value = this.suffixLen_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public UInt32ValueOrBuilder getSuffixLenOrBuilder() {
        return getSuffixLen();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public ConnectionSourceType getSourceType() {
        ConnectionSourceType connectionSourceTypeValueOf = ConnectionSourceType.valueOf(this.sourceType_);
        return connectionSourceTypeValueOf == null ? ConnectionSourceType.UNRECOGNIZED : connectionSourceTypeValueOf;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public int getSourcePrefixRangesCount() {
        return this.sourcePrefixRanges_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public CidrRange getSourcePrefixRanges(int i) {
        return this.sourcePrefixRanges_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public CidrRangeOrBuilder getSourcePrefixRangesOrBuilder(int i) {
        return this.sourcePrefixRanges_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public int getSourcePortsCount() {
        return this.sourcePorts_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public int getSourcePorts(int i) {
        return this.sourcePorts_.getInt(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public int getServerNamesCount() {
        return this.serverNames_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public String getServerNames(int i) {
        return (String) this.serverNames_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public ByteString getServerNamesBytes(int i) {
        return this.serverNames_.getByteString(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public String getTransportProtocol() {
        Object obj = this.transportProtocol_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.transportProtocol_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public ByteString getTransportProtocolBytes() {
        Object obj = this.transportProtocol_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.transportProtocol_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public int getApplicationProtocolsCount() {
        return this.applicationProtocols_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public String getApplicationProtocols(int i) {
        return (String) this.applicationProtocols_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
    public ByteString getApplicationProtocolsBytes(int i) {
        return this.applicationProtocols_.getByteString(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        for (int i = 0; i < this.prefixRanges_.size(); i++) {
            codedOutputStream.writeMessage(3, this.prefixRanges_.get(i));
        }
        if (!getAddressSuffixBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.addressSuffix_);
        }
        if (this.suffixLen_ != null) {
            codedOutputStream.writeMessage(5, getSuffixLen());
        }
        for (int i2 = 0; i2 < this.sourcePrefixRanges_.size(); i2++) {
            codedOutputStream.writeMessage(6, this.sourcePrefixRanges_.get(i2));
        }
        if (getSourcePortsList().size() > 0) {
            codedOutputStream.writeUInt32NoTag(58);
            codedOutputStream.writeUInt32NoTag(this.sourcePortsMemoizedSerializedSize);
        }
        for (int i3 = 0; i3 < this.sourcePorts_.size(); i3++) {
            codedOutputStream.writeUInt32NoTag(this.sourcePorts_.getInt(i3));
        }
        if (this.destinationPort_ != null) {
            codedOutputStream.writeMessage(8, getDestinationPort());
        }
        if (!getTransportProtocolBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 9, this.transportProtocol_);
        }
        for (int i4 = 0; i4 < this.applicationProtocols_.size(); i4++) {
            GeneratedMessageV3.writeString(codedOutputStream, 10, this.applicationProtocols_.getRaw(i4));
        }
        for (int i5 = 0; i5 < this.serverNames_.size(); i5++) {
            GeneratedMessageV3.writeString(codedOutputStream, 11, this.serverNames_.getRaw(i5));
        }
        if (this.sourceType_ != ConnectionSourceType.ANY.getNumber()) {
            codedOutputStream.writeEnum(12, this.sourceType_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.prefixRanges_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, this.prefixRanges_.get(i2));
        }
        if (!getAddressSuffixBytes().isEmpty()) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(4, this.addressSuffix_);
        }
        if (this.suffixLen_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(5, getSuffixLen());
        }
        for (int i3 = 0; i3 < this.sourcePrefixRanges_.size(); i3++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(6, this.sourcePrefixRanges_.get(i3));
        }
        int iComputeUInt32SizeNoTag = 0;
        for (int i4 = 0; i4 < this.sourcePorts_.size(); i4++) {
            iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(this.sourcePorts_.getInt(i4));
        }
        int iComputeStringSize = iComputeMessageSize + iComputeUInt32SizeNoTag;
        if (!getSourcePortsList().isEmpty()) {
            iComputeStringSize = iComputeStringSize + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeUInt32SizeNoTag);
        }
        this.sourcePortsMemoizedSerializedSize = iComputeUInt32SizeNoTag;
        if (this.destinationPort_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(8, getDestinationPort());
        }
        if (!getTransportProtocolBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(9, this.transportProtocol_);
        }
        int iComputeStringSizeNoTag = 0;
        for (int i5 = 0; i5 < this.applicationProtocols_.size(); i5++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.applicationProtocols_.getRaw(i5));
        }
        int size = iComputeStringSize + iComputeStringSizeNoTag + mo17623getApplicationProtocolsList().size();
        int iComputeStringSizeNoTag2 = 0;
        for (int i6 = 0; i6 < this.serverNames_.size(); i6++) {
            iComputeStringSizeNoTag2 += computeStringSizeNoTag(this.serverNames_.getRaw(i6));
        }
        int size2 = size + iComputeStringSizeNoTag2 + mo17626getServerNamesList().size();
        if (this.sourceType_ != ConnectionSourceType.ANY.getNumber()) {
            size2 += CodedOutputStream.computeEnumSize(12, this.sourceType_);
        }
        int serializedSize = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FilterChainMatch)) {
            return super.equals(obj);
        }
        FilterChainMatch filterChainMatch = (FilterChainMatch) obj;
        if (hasDestinationPort() != filterChainMatch.hasDestinationPort()) {
            return false;
        }
        if ((!hasDestinationPort() || getDestinationPort().equals(filterChainMatch.getDestinationPort())) && getPrefixRangesList().equals(filterChainMatch.getPrefixRangesList()) && getAddressSuffix().equals(filterChainMatch.getAddressSuffix()) && hasSuffixLen() == filterChainMatch.hasSuffixLen()) {
            return (!hasSuffixLen() || getSuffixLen().equals(filterChainMatch.getSuffixLen())) && this.sourceType_ == filterChainMatch.sourceType_ && getSourcePrefixRangesList().equals(filterChainMatch.getSourcePrefixRangesList()) && getSourcePortsList().equals(filterChainMatch.getSourcePortsList()) && mo17626getServerNamesList().equals(filterChainMatch.mo17626getServerNamesList()) && getTransportProtocol().equals(filterChainMatch.getTransportProtocol()) && mo17623getApplicationProtocolsList().equals(filterChainMatch.mo17623getApplicationProtocolsList()) && this.unknownFields.equals(filterChainMatch.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasDestinationPort()) {
            iHashCode = (((iHashCode * 37) + 8) * 53) + getDestinationPort().hashCode();
        }
        if (getPrefixRangesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getPrefixRangesList().hashCode();
        }
        int iHashCode2 = (((iHashCode * 37) + 4) * 53) + getAddressSuffix().hashCode();
        if (hasSuffixLen()) {
            iHashCode2 = (((iHashCode2 * 37) + 5) * 53) + getSuffixLen().hashCode();
        }
        int iHashCode3 = (((iHashCode2 * 37) + 12) * 53) + this.sourceType_;
        if (getSourcePrefixRangesCount() > 0) {
            iHashCode3 = (((iHashCode3 * 37) + 6) * 53) + getSourcePrefixRangesList().hashCode();
        }
        if (getSourcePortsCount() > 0) {
            iHashCode3 = (((iHashCode3 * 37) + 7) * 53) + getSourcePortsList().hashCode();
        }
        if (getServerNamesCount() > 0) {
            iHashCode3 = (((iHashCode3 * 37) + 11) * 53) + mo17626getServerNamesList().hashCode();
        }
        int iHashCode4 = (((iHashCode3 * 37) + 9) * 53) + getTransportProtocol().hashCode();
        if (getApplicationProtocolsCount() > 0) {
            iHashCode4 = (((iHashCode4 * 37) + 10) * 53) + mo17623getApplicationProtocolsList().hashCode();
        }
        int iHashCode5 = (iHashCode4 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode5;
        return iHashCode5;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m17627newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m17630toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    /* loaded from: classes5.dex */
    public enum ConnectionSourceType implements ProtocolMessageEnum {
        ANY(0),
        LOCAL(1),
        EXTERNAL(2),
        UNRECOGNIZED(-1);

        public static final int ANY_VALUE = 0;
        public static final int EXTERNAL_VALUE = 2;
        public static final int LOCAL_VALUE = 1;
        private static final Internal.EnumLiteMap<ConnectionSourceType> internalValueMap = new Internal.EnumLiteMap<ConnectionSourceType>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatch.ConnectionSourceType.1
            public ConnectionSourceType findValueByNumber(int i) {
                return ConnectionSourceType.forNumber(i);
            }
        };
        private static final ConnectionSourceType[] VALUES = values();
        private final int value;

        ConnectionSourceType(int i) {
            this.value = i;
        }

        public static ConnectionSourceType forNumber(int i) {
            if (i == 0) {
                return ANY;
            }
            if (i == 1) {
                return LOCAL;
            }
            if (i != 2) {
                return null;
            }
            return EXTERNAL;
        }

        public static Internal.EnumLiteMap<ConnectionSourceType> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static ConnectionSourceType valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) FilterChainMatch.getDescriptor().getEnumTypes().get(0);
        }

        public static ConnectionSourceType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FilterChainMatchOrBuilder {
        private Object addressSuffix_;
        private LazyStringList applicationProtocols_;
        private int bitField0_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> destinationPortBuilder_;
        private UInt32Value destinationPort_;
        private RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> prefixRangesBuilder_;
        private List<CidrRange> prefixRanges_;
        private LazyStringList serverNames_;
        private Internal.IntList sourcePorts_;
        private RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> sourcePrefixRangesBuilder_;
        private List<CidrRange> sourcePrefixRanges_;
        private int sourceType_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> suffixLenBuilder_;
        private UInt32Value suffixLen_;
        private Object transportProtocol_;

        private Builder() {
            this.prefixRanges_ = Collections.emptyList();
            this.addressSuffix_ = "";
            this.sourceType_ = 0;
            this.sourcePrefixRanges_ = Collections.emptyList();
            this.sourcePorts_ = FilterChainMatch.emptyIntList();
            this.serverNames_ = LazyStringArrayList.EMPTY;
            this.transportProtocol_ = "";
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.prefixRanges_ = Collections.emptyList();
            this.addressSuffix_ = "";
            this.sourceType_ = 0;
            this.sourcePrefixRanges_ = Collections.emptyList();
            this.sourcePorts_ = FilterChainMatch.emptyIntList();
            this.serverNames_ = LazyStringArrayList.EMPTY;
            this.transportProtocol_ = "";
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ListenerComponentsProto.internal_static_envoy_api_v2_listener_FilterChainMatch_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public int getSourceTypeValue() {
            return this.sourceType_;
        }

        public Builder setSourceTypeValue(int i) {
            this.sourceType_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public boolean hasDestinationPort() {
            return (this.destinationPortBuilder_ == null && this.destinationPort_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public boolean hasSuffixLen() {
            return (this.suffixLenBuilder_ == null && this.suffixLen_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ListenerComponentsProto.internal_static_envoy_api_v2_listener_FilterChainMatch_fieldAccessorTable.ensureFieldAccessorsInitialized(FilterChainMatch.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (FilterChainMatch.alwaysUseFieldBuilders) {
                getPrefixRangesFieldBuilder();
                getSourcePrefixRangesFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17641clear() {
            super.clear();
            if (this.destinationPortBuilder_ == null) {
                this.destinationPort_ = null;
            } else {
                this.destinationPort_ = null;
                this.destinationPortBuilder_ = null;
            }
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.prefixRanges_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.addressSuffix_ = "";
            if (this.suffixLenBuilder_ == null) {
                this.suffixLen_ = null;
            } else {
                this.suffixLen_ = null;
                this.suffixLenBuilder_ = null;
            }
            this.sourceType_ = 0;
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV32 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.sourcePrefixRanges_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            this.sourcePorts_ = FilterChainMatch.emptyIntList();
            this.bitField0_ &= -5;
            this.serverNames_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -9;
            this.transportProtocol_ = "";
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -17;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ListenerComponentsProto.internal_static_envoy_api_v2_listener_FilterChainMatch_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FilterChainMatch m17654getDefaultInstanceForType() {
            return FilterChainMatch.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FilterChainMatch m17635build() throws UninitializedMessageException {
            FilterChainMatch filterChainMatchM17637buildPartial = m17637buildPartial();
            if (filterChainMatchM17637buildPartial.isInitialized()) {
                return filterChainMatchM17637buildPartial;
            }
            throw newUninitializedMessageException(filterChainMatchM17637buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FilterChainMatch m17637buildPartial() {
            FilterChainMatch filterChainMatch = new FilterChainMatch(this);
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.destinationPortBuilder_;
            if (singleFieldBuilderV3 == null) {
                filterChainMatch.destinationPort_ = this.destinationPort_;
            } else {
                filterChainMatch.destinationPort_ = singleFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.prefixRanges_ = Collections.unmodifiableList(this.prefixRanges_);
                    this.bitField0_ &= -2;
                }
                filterChainMatch.prefixRanges_ = this.prefixRanges_;
            } else {
                filterChainMatch.prefixRanges_ = repeatedFieldBuilderV3.build();
            }
            filterChainMatch.addressSuffix_ = this.addressSuffix_;
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV32 = this.suffixLenBuilder_;
            if (singleFieldBuilderV32 == null) {
                filterChainMatch.suffixLen_ = this.suffixLen_;
            } else {
                filterChainMatch.suffixLen_ = singleFieldBuilderV32.build();
            }
            filterChainMatch.sourceType_ = this.sourceType_;
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV32 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 2) != 0) {
                    this.sourcePrefixRanges_ = Collections.unmodifiableList(this.sourcePrefixRanges_);
                    this.bitField0_ &= -3;
                }
                filterChainMatch.sourcePrefixRanges_ = this.sourcePrefixRanges_;
            } else {
                filterChainMatch.sourcePrefixRanges_ = repeatedFieldBuilderV32.build();
            }
            if ((this.bitField0_ & 4) != 0) {
                this.sourcePorts_.makeImmutable();
                this.bitField0_ &= -5;
            }
            filterChainMatch.sourcePorts_ = this.sourcePorts_;
            if ((this.bitField0_ & 8) != 0) {
                this.serverNames_ = this.serverNames_.getUnmodifiableView();
                this.bitField0_ &= -9;
            }
            filterChainMatch.serverNames_ = this.serverNames_;
            filterChainMatch.transportProtocol_ = this.transportProtocol_;
            if ((this.bitField0_ & 16) != 0) {
                this.applicationProtocols_ = this.applicationProtocols_.getUnmodifiableView();
                this.bitField0_ &= -17;
            }
            filterChainMatch.applicationProtocols_ = this.applicationProtocols_;
            onBuilt();
            return filterChainMatch;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17653clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17665setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17643clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17646clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17667setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17633addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m17658mergeFrom(Message message) {
            if (message instanceof FilterChainMatch) {
                return mergeFrom((FilterChainMatch) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(FilterChainMatch filterChainMatch) {
            if (filterChainMatch == FilterChainMatch.getDefaultInstance()) {
                return this;
            }
            if (filterChainMatch.hasDestinationPort()) {
                mergeDestinationPort(filterChainMatch.getDestinationPort());
            }
            if (this.prefixRangesBuilder_ == null) {
                if (!filterChainMatch.prefixRanges_.isEmpty()) {
                    if (this.prefixRanges_.isEmpty()) {
                        this.prefixRanges_ = filterChainMatch.prefixRanges_;
                        this.bitField0_ &= -2;
                    } else {
                        ensurePrefixRangesIsMutable();
                        this.prefixRanges_.addAll(filterChainMatch.prefixRanges_);
                    }
                    onChanged();
                }
            } else if (!filterChainMatch.prefixRanges_.isEmpty()) {
                if (!this.prefixRangesBuilder_.isEmpty()) {
                    this.prefixRangesBuilder_.addAllMessages(filterChainMatch.prefixRanges_);
                } else {
                    this.prefixRangesBuilder_.dispose();
                    this.prefixRangesBuilder_ = null;
                    this.prefixRanges_ = filterChainMatch.prefixRanges_;
                    this.bitField0_ &= -2;
                    this.prefixRangesBuilder_ = FilterChainMatch.alwaysUseFieldBuilders ? getPrefixRangesFieldBuilder() : null;
                }
            }
            if (!filterChainMatch.getAddressSuffix().isEmpty()) {
                this.addressSuffix_ = filterChainMatch.addressSuffix_;
                onChanged();
            }
            if (filterChainMatch.hasSuffixLen()) {
                mergeSuffixLen(filterChainMatch.getSuffixLen());
            }
            if (filterChainMatch.sourceType_ != 0) {
                setSourceTypeValue(filterChainMatch.getSourceTypeValue());
            }
            if (this.sourcePrefixRangesBuilder_ == null) {
                if (!filterChainMatch.sourcePrefixRanges_.isEmpty()) {
                    if (this.sourcePrefixRanges_.isEmpty()) {
                        this.sourcePrefixRanges_ = filterChainMatch.sourcePrefixRanges_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureSourcePrefixRangesIsMutable();
                        this.sourcePrefixRanges_.addAll(filterChainMatch.sourcePrefixRanges_);
                    }
                    onChanged();
                }
            } else if (!filterChainMatch.sourcePrefixRanges_.isEmpty()) {
                if (!this.sourcePrefixRangesBuilder_.isEmpty()) {
                    this.sourcePrefixRangesBuilder_.addAllMessages(filterChainMatch.sourcePrefixRanges_);
                } else {
                    this.sourcePrefixRangesBuilder_.dispose();
                    this.sourcePrefixRangesBuilder_ = null;
                    this.sourcePrefixRanges_ = filterChainMatch.sourcePrefixRanges_;
                    this.bitField0_ &= -3;
                    this.sourcePrefixRangesBuilder_ = FilterChainMatch.alwaysUseFieldBuilders ? getSourcePrefixRangesFieldBuilder() : null;
                }
            }
            if (!filterChainMatch.sourcePorts_.isEmpty()) {
                if (this.sourcePorts_.isEmpty()) {
                    this.sourcePorts_ = filterChainMatch.sourcePorts_;
                    this.bitField0_ &= -5;
                } else {
                    ensureSourcePortsIsMutable();
                    this.sourcePorts_.addAll(filterChainMatch.sourcePorts_);
                }
                onChanged();
            }
            if (!filterChainMatch.serverNames_.isEmpty()) {
                if (this.serverNames_.isEmpty()) {
                    this.serverNames_ = filterChainMatch.serverNames_;
                    this.bitField0_ &= -9;
                } else {
                    ensureServerNamesIsMutable();
                    this.serverNames_.addAll(filterChainMatch.serverNames_);
                }
                onChanged();
            }
            if (!filterChainMatch.getTransportProtocol().isEmpty()) {
                this.transportProtocol_ = filterChainMatch.transportProtocol_;
                onChanged();
            }
            if (!filterChainMatch.applicationProtocols_.isEmpty()) {
                if (this.applicationProtocols_.isEmpty()) {
                    this.applicationProtocols_ = filterChainMatch.applicationProtocols_;
                    this.bitField0_ &= -17;
                } else {
                    ensureApplicationProtocolsIsMutable();
                    this.applicationProtocols_.addAll(filterChainMatch.applicationProtocols_);
                }
                onChanged();
            }
            m17663mergeUnknownFields(filterChainMatch.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatch.Builder m17659mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatch.access$1800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatch r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatch) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatch r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatch) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatch.Builder.m17659mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatch$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public UInt32Value getDestinationPort() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.destinationPortBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.destinationPort_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setDestinationPort(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.destinationPortBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.destinationPort_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setDestinationPort(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.destinationPortBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.destinationPort_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeDestinationPort(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.destinationPortBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.destinationPort_;
                if (uInt32Value2 != null) {
                    this.destinationPort_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.destinationPort_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearDestinationPort() {
            if (this.destinationPortBuilder_ == null) {
                this.destinationPort_ = null;
                onChanged();
            } else {
                this.destinationPort_ = null;
                this.destinationPortBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getDestinationPortBuilder() {
            onChanged();
            return getDestinationPortFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public UInt32ValueOrBuilder getDestinationPortOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.destinationPortBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.destinationPort_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getDestinationPortFieldBuilder() {
            if (this.destinationPortBuilder_ == null) {
                this.destinationPortBuilder_ = new SingleFieldBuilderV3<>(getDestinationPort(), getParentForChildren(), isClean());
                this.destinationPort_ = null;
            }
            return this.destinationPortBuilder_;
        }

        private void ensurePrefixRangesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.prefixRanges_ = new ArrayList(this.prefixRanges_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public List<CidrRange> getPrefixRangesList() {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.prefixRanges_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public int getPrefixRangesCount() {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.prefixRanges_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public CidrRange getPrefixRanges(int i) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.prefixRanges_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setPrefixRanges(int i, CidrRange cidrRange) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                cidrRange.getClass();
                ensurePrefixRangesIsMutable();
                this.prefixRanges_.set(i, cidrRange);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, cidrRange);
            }
            return this;
        }

        public Builder setPrefixRanges(int i, CidrRange.Builder builder) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePrefixRangesIsMutable();
                this.prefixRanges_.set(i, builder.m14587build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m14587build());
            }
            return this;
        }

        public Builder addPrefixRanges(CidrRange cidrRange) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                cidrRange.getClass();
                ensurePrefixRangesIsMutable();
                this.prefixRanges_.add(cidrRange);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(cidrRange);
            }
            return this;
        }

        public Builder addPrefixRanges(int i, CidrRange cidrRange) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                cidrRange.getClass();
                ensurePrefixRangesIsMutable();
                this.prefixRanges_.add(i, cidrRange);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, cidrRange);
            }
            return this;
        }

        public Builder addPrefixRanges(CidrRange.Builder builder) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePrefixRangesIsMutable();
                this.prefixRanges_.add(builder.m14587build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m14587build());
            }
            return this;
        }

        public Builder addPrefixRanges(int i, CidrRange.Builder builder) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePrefixRangesIsMutable();
                this.prefixRanges_.add(i, builder.m14587build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m14587build());
            }
            return this;
        }

        public Builder addAllPrefixRanges(Iterable<? extends CidrRange> iterable) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePrefixRangesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.prefixRanges_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearPrefixRanges() {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.prefixRanges_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removePrefixRanges(int i) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePrefixRangesIsMutable();
                this.prefixRanges_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public CidrRange.Builder getPrefixRangesBuilder(int i) {
            return getPrefixRangesFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public CidrRangeOrBuilder getPrefixRangesOrBuilder(int i) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.prefixRanges_.get(i);
            }
            return (CidrRangeOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public List<? extends CidrRangeOrBuilder> getPrefixRangesOrBuilderList() {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.prefixRangesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.prefixRanges_);
        }

        public CidrRange.Builder addPrefixRangesBuilder() {
            return getPrefixRangesFieldBuilder().addBuilder(CidrRange.getDefaultInstance());
        }

        public CidrRange.Builder addPrefixRangesBuilder(int i) {
            return getPrefixRangesFieldBuilder().addBuilder(i, CidrRange.getDefaultInstance());
        }

        public List<CidrRange.Builder> getPrefixRangesBuilderList() {
            return getPrefixRangesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> getPrefixRangesFieldBuilder() {
            if (this.prefixRangesBuilder_ == null) {
                this.prefixRangesBuilder_ = new RepeatedFieldBuilderV3<>(this.prefixRanges_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.prefixRanges_ = null;
            }
            return this.prefixRangesBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public String getAddressSuffix() {
            Object obj = this.addressSuffix_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.addressSuffix_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setAddressSuffix(String str) {
            str.getClass();
            this.addressSuffix_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public ByteString getAddressSuffixBytes() {
            Object obj = this.addressSuffix_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.addressSuffix_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAddressSuffixBytes(ByteString byteString) {
            byteString.getClass();
            FilterChainMatch.checkByteStringIsUtf8(byteString);
            this.addressSuffix_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearAddressSuffix() {
            this.addressSuffix_ = FilterChainMatch.getDefaultInstance().getAddressSuffix();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public UInt32Value getSuffixLen() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.suffixLenBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.suffixLen_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setSuffixLen(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.suffixLenBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.suffixLen_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setSuffixLen(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.suffixLenBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.suffixLen_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSuffixLen(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.suffixLenBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.suffixLen_;
                if (uInt32Value2 != null) {
                    this.suffixLen_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.suffixLen_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearSuffixLen() {
            if (this.suffixLenBuilder_ == null) {
                this.suffixLen_ = null;
                onChanged();
            } else {
                this.suffixLen_ = null;
                this.suffixLenBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getSuffixLenBuilder() {
            onChanged();
            return getSuffixLenFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public UInt32ValueOrBuilder getSuffixLenOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.suffixLenBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.suffixLen_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getSuffixLenFieldBuilder() {
            if (this.suffixLenBuilder_ == null) {
                this.suffixLenBuilder_ = new SingleFieldBuilderV3<>(getSuffixLen(), getParentForChildren(), isClean());
                this.suffixLen_ = null;
            }
            return this.suffixLenBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public ConnectionSourceType getSourceType() {
            ConnectionSourceType connectionSourceTypeValueOf = ConnectionSourceType.valueOf(this.sourceType_);
            return connectionSourceTypeValueOf == null ? ConnectionSourceType.UNRECOGNIZED : connectionSourceTypeValueOf;
        }

        public Builder setSourceType(ConnectionSourceType connectionSourceType) {
            connectionSourceType.getClass();
            this.sourceType_ = connectionSourceType.getNumber();
            onChanged();
            return this;
        }

        public Builder clearSourceType() {
            this.sourceType_ = 0;
            onChanged();
            return this;
        }

        private void ensureSourcePrefixRangesIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.sourcePrefixRanges_ = new ArrayList(this.sourcePrefixRanges_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public List<CidrRange> getSourcePrefixRangesList() {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.sourcePrefixRanges_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public int getSourcePrefixRangesCount() {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sourcePrefixRanges_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public CidrRange getSourcePrefixRanges(int i) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sourcePrefixRanges_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setSourcePrefixRanges(int i, CidrRange cidrRange) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                cidrRange.getClass();
                ensureSourcePrefixRangesIsMutable();
                this.sourcePrefixRanges_.set(i, cidrRange);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, cidrRange);
            }
            return this;
        }

        public Builder setSourcePrefixRanges(int i, CidrRange.Builder builder) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourcePrefixRangesIsMutable();
                this.sourcePrefixRanges_.set(i, builder.m14587build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m14587build());
            }
            return this;
        }

        public Builder addSourcePrefixRanges(CidrRange cidrRange) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                cidrRange.getClass();
                ensureSourcePrefixRangesIsMutable();
                this.sourcePrefixRanges_.add(cidrRange);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(cidrRange);
            }
            return this;
        }

        public Builder addSourcePrefixRanges(int i, CidrRange cidrRange) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                cidrRange.getClass();
                ensureSourcePrefixRangesIsMutable();
                this.sourcePrefixRanges_.add(i, cidrRange);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, cidrRange);
            }
            return this;
        }

        public Builder addSourcePrefixRanges(CidrRange.Builder builder) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourcePrefixRangesIsMutable();
                this.sourcePrefixRanges_.add(builder.m14587build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m14587build());
            }
            return this;
        }

        public Builder addSourcePrefixRanges(int i, CidrRange.Builder builder) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourcePrefixRangesIsMutable();
                this.sourcePrefixRanges_.add(i, builder.m14587build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m14587build());
            }
            return this;
        }

        public Builder addAllSourcePrefixRanges(Iterable<? extends CidrRange> iterable) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourcePrefixRangesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.sourcePrefixRanges_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearSourcePrefixRanges() {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.sourcePrefixRanges_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeSourcePrefixRanges(int i) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourcePrefixRangesIsMutable();
                this.sourcePrefixRanges_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public CidrRange.Builder getSourcePrefixRangesBuilder(int i) {
            return getSourcePrefixRangesFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public CidrRangeOrBuilder getSourcePrefixRangesOrBuilder(int i) {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sourcePrefixRanges_.get(i);
            }
            return (CidrRangeOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public List<? extends CidrRangeOrBuilder> getSourcePrefixRangesOrBuilderList() {
            RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> repeatedFieldBuilderV3 = this.sourcePrefixRangesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.sourcePrefixRanges_);
        }

        public CidrRange.Builder addSourcePrefixRangesBuilder() {
            return getSourcePrefixRangesFieldBuilder().addBuilder(CidrRange.getDefaultInstance());
        }

        public CidrRange.Builder addSourcePrefixRangesBuilder(int i) {
            return getSourcePrefixRangesFieldBuilder().addBuilder(i, CidrRange.getDefaultInstance());
        }

        public List<CidrRange.Builder> getSourcePrefixRangesBuilderList() {
            return getSourcePrefixRangesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<CidrRange, CidrRange.Builder, CidrRangeOrBuilder> getSourcePrefixRangesFieldBuilder() {
            if (this.sourcePrefixRangesBuilder_ == null) {
                this.sourcePrefixRangesBuilder_ = new RepeatedFieldBuilderV3<>(this.sourcePrefixRanges_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.sourcePrefixRanges_ = null;
            }
            return this.sourcePrefixRangesBuilder_;
        }

        private void ensureSourcePortsIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.sourcePorts_ = FilterChainMatch.mutableCopy(this.sourcePorts_);
                this.bitField0_ |= 4;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public List<Integer> getSourcePortsList() {
            return (this.bitField0_ & 4) != 0 ? Collections.unmodifiableList(this.sourcePorts_) : this.sourcePorts_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public int getSourcePortsCount() {
            return this.sourcePorts_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public int getSourcePorts(int i) {
            return this.sourcePorts_.getInt(i);
        }

        public Builder setSourcePorts(int i, int i2) {
            ensureSourcePortsIsMutable();
            this.sourcePorts_.setInt(i, i2);
            onChanged();
            return this;
        }

        public Builder addSourcePorts(int i) {
            ensureSourcePortsIsMutable();
            this.sourcePorts_.addInt(i);
            onChanged();
            return this;
        }

        public Builder addAllSourcePorts(Iterable<? extends Integer> iterable) {
            ensureSourcePortsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.sourcePorts_);
            onChanged();
            return this;
        }

        public Builder clearSourcePorts() {
            this.sourcePorts_ = FilterChainMatch.emptyIntList();
            this.bitField0_ &= -5;
            onChanged();
            return this;
        }

        private void ensureServerNamesIsMutable() {
            if ((this.bitField0_ & 8) == 0) {
                this.serverNames_ = new LazyStringArrayList(this.serverNames_);
                this.bitField0_ |= 8;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        /* renamed from: getServerNamesList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo17626getServerNamesList() {
            return this.serverNames_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public int getServerNamesCount() {
            return this.serverNames_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public String getServerNames(int i) {
            return (String) this.serverNames_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public ByteString getServerNamesBytes(int i) {
            return this.serverNames_.getByteString(i);
        }

        public Builder setServerNames(int i, String str) {
            str.getClass();
            ensureServerNamesIsMutable();
            this.serverNames_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addServerNames(String str) {
            str.getClass();
            ensureServerNamesIsMutable();
            this.serverNames_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllServerNames(Iterable<String> iterable) {
            ensureServerNamesIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.serverNames_);
            onChanged();
            return this;
        }

        public Builder clearServerNames() {
            this.serverNames_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -9;
            onChanged();
            return this;
        }

        public Builder addServerNamesBytes(ByteString byteString) {
            byteString.getClass();
            FilterChainMatch.checkByteStringIsUtf8(byteString);
            ensureServerNamesIsMutable();
            this.serverNames_.add(byteString);
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public String getTransportProtocol() {
            Object obj = this.transportProtocol_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.transportProtocol_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setTransportProtocol(String str) {
            str.getClass();
            this.transportProtocol_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public ByteString getTransportProtocolBytes() {
            Object obj = this.transportProtocol_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.transportProtocol_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setTransportProtocolBytes(ByteString byteString) {
            byteString.getClass();
            FilterChainMatch.checkByteStringIsUtf8(byteString);
            this.transportProtocol_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearTransportProtocol() {
            this.transportProtocol_ = FilterChainMatch.getDefaultInstance().getTransportProtocol();
            onChanged();
            return this;
        }

        private void ensureApplicationProtocolsIsMutable() {
            if ((this.bitField0_ & 16) == 0) {
                this.applicationProtocols_ = new LazyStringArrayList(this.applicationProtocols_);
                this.bitField0_ |= 16;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        /* renamed from: getApplicationProtocolsList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo17623getApplicationProtocolsList() {
            return this.applicationProtocols_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public int getApplicationProtocolsCount() {
            return this.applicationProtocols_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public String getApplicationProtocols(int i) {
            return (String) this.applicationProtocols_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatchOrBuilder
        public ByteString getApplicationProtocolsBytes(int i) {
            return this.applicationProtocols_.getByteString(i);
        }

        public Builder setApplicationProtocols(int i, String str) {
            str.getClass();
            ensureApplicationProtocolsIsMutable();
            this.applicationProtocols_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addApplicationProtocols(String str) {
            str.getClass();
            ensureApplicationProtocolsIsMutable();
            this.applicationProtocols_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllApplicationProtocols(Iterable<String> iterable) {
            ensureApplicationProtocolsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.applicationProtocols_);
            onChanged();
            return this;
        }

        public Builder clearApplicationProtocols() {
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -17;
            onChanged();
            return this;
        }

        public Builder addApplicationProtocolsBytes(ByteString byteString) {
            byteString.getClass();
            FilterChainMatch.checkByteStringIsUtf8(byteString);
            ensureApplicationProtocolsIsMutable();
            this.applicationProtocols_.add(byteString);
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m17669setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m17663mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
