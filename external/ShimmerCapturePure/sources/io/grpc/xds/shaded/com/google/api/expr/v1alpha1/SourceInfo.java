package io.grpc.xds.shaded.com.google.api.expr.v1alpha1;

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
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public final class SourceInfo extends GeneratedMessageV3 implements SourceInfoOrBuilder {
    public static final int LINE_OFFSETS_FIELD_NUMBER = 3;
    public static final int LOCATION_FIELD_NUMBER = 2;
    public static final int MACRO_CALLS_FIELD_NUMBER = 5;
    public static final int POSITIONS_FIELD_NUMBER = 4;
    public static final int SYNTAX_VERSION_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final SourceInfo DEFAULT_INSTANCE = new SourceInfo();
    private static final Parser<SourceInfo> PARSER = new AbstractParser<SourceInfo>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfo.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public SourceInfo m11194parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SourceInfo(codedInputStream, extensionRegistryLite);
        }
    };
    private int lineOffsetsMemoizedSerializedSize;
    private Internal.IntList lineOffsets_;
    private volatile Object location_;
    private MapField<Long, Expr> macroCalls_;
    private byte memoizedIsInitialized;
    private MapField<Long, Integer> positions_;
    private volatile Object syntaxVersion_;

    private SourceInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.lineOffsetsMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
    }

    private SourceInfo() {
        this.lineOffsetsMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.syntaxVersion_ = "";
        this.location_ = "";
        this.lineOffsets_ = emptyIntList();
    }

    private SourceInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            this.syntaxVersion_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            this.location_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 24) {
                            if ((i & 1) == 0) {
                                this.lineOffsets_ = newIntList();
                                i |= 1;
                            }
                            this.lineOffsets_.addInt(codedInputStream.readInt32());
                        } else if (tag == 26) {
                            int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & 1) == 0 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.lineOffsets_ = newIntList();
                                i |= 1;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.lineOffsets_.addInt(codedInputStream.readInt32());
                            }
                            codedInputStream.popLimit(iPushLimit);
                        } else if (tag == 34) {
                            if ((i & 2) == 0) {
                                this.positions_ = MapField.newMapField(PositionsDefaultEntryHolder.defaultEntry);
                                i |= 2;
                            }
                            MapEntry message = codedInputStream.readMessage(PositionsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                            this.positions_.getMutableMap().put(message.getKey(), message.getValue());
                        } else if (tag == 42) {
                            if ((i & 4) == 0) {
                                this.macroCalls_ = MapField.newMapField(MacroCallsDefaultEntryHolder.defaultEntry);
                                i |= 4;
                            }
                            MapEntry message2 = codedInputStream.readMessage(MacroCallsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                            this.macroCalls_.getMutableMap().put(message2.getKey(), message2.getValue());
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
                if ((i & 1) != 0) {
                    this.lineOffsets_.makeImmutable();
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static SourceInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SourceInfo> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SyntaxProto.internal_static_google_api_expr_v1alpha1_SourceInfo_descriptor;
    }

    public static SourceInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (SourceInfo) PARSER.parseFrom(byteBuffer);
    }

    public static SourceInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SourceInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SourceInfo) PARSER.parseFrom(byteString);
    }

    public static SourceInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SourceInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SourceInfo) PARSER.parseFrom(bArr);
    }

    public static SourceInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SourceInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SourceInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SourceInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SourceInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SourceInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m11192toBuilder();
    }

    public static Builder newBuilder(SourceInfo sourceInfo) {
        return DEFAULT_INSTANCE.m11192toBuilder().mergeFrom(sourceInfo);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SourceInfo m11187getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public List<Integer> getLineOffsetsList() {
        return this.lineOffsets_;
    }

    public Parser<SourceInfo> getParserForType() {
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
        return new SourceInfo();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected MapField internalGetMapField(int i) {
        if (i == 4) {
            return internalGetPositions();
        }
        if (i == 5) {
            return internalGetMacroCalls();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SyntaxProto.internal_static_google_api_expr_v1alpha1_SourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SourceInfo.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public String getSyntaxVersion() {
        Object obj = this.syntaxVersion_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.syntaxVersion_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public ByteString getSyntaxVersionBytes() {
        Object obj = this.syntaxVersion_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.syntaxVersion_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public String getLocation() {
        Object obj = this.location_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.location_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public ByteString getLocationBytes() {
        Object obj = this.location_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.location_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public int getLineOffsetsCount() {
        return this.lineOffsets_.size();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public int getLineOffsets(int i) {
        return this.lineOffsets_.getInt(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<Long, Integer> internalGetPositions() {
        MapField<Long, Integer> mapField = this.positions_;
        return mapField == null ? MapField.emptyMapField(PositionsDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public int getPositionsCount() {
        return internalGetPositions().getMap().size();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public boolean containsPositions(long j) {
        return internalGetPositions().getMap().containsKey(Long.valueOf(j));
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    @Deprecated
    public Map<Long, Integer> getPositions() {
        return getPositionsMap();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public Map<Long, Integer> getPositionsMap() {
        return internalGetPositions().getMap();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public int getPositionsOrDefault(long j, int i) {
        Map map = internalGetPositions().getMap();
        return map.containsKey(Long.valueOf(j)) ? ((Integer) map.get(Long.valueOf(j))).intValue() : i;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public int getPositionsOrThrow(long j) {
        Map map = internalGetPositions().getMap();
        if (!map.containsKey(Long.valueOf(j))) {
            throw new IllegalArgumentException();
        }
        return ((Integer) map.get(Long.valueOf(j))).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<Long, Expr> internalGetMacroCalls() {
        MapField<Long, Expr> mapField = this.macroCalls_;
        return mapField == null ? MapField.emptyMapField(MacroCallsDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public int getMacroCallsCount() {
        return internalGetMacroCalls().getMap().size();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public boolean containsMacroCalls(long j) {
        return internalGetMacroCalls().getMap().containsKey(Long.valueOf(j));
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    @Deprecated
    public Map<Long, Expr> getMacroCalls() {
        return getMacroCallsMap();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public Map<Long, Expr> getMacroCallsMap() {
        return internalGetMacroCalls().getMap();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public Expr getMacroCallsOrDefault(long j, Expr expr) {
        Map map = internalGetMacroCalls().getMap();
        return map.containsKey(Long.valueOf(j)) ? (Expr) map.get(Long.valueOf(j)) : expr;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
    public Expr getMacroCallsOrThrow(long j) {
        Map map = internalGetMacroCalls().getMap();
        if (!map.containsKey(Long.valueOf(j))) {
            throw new IllegalArgumentException();
        }
        return (Expr) map.get(Long.valueOf(j));
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        if (!getSyntaxVersionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.syntaxVersion_);
        }
        if (!getLocationBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.location_);
        }
        if (getLineOffsetsList().size() > 0) {
            codedOutputStream.writeUInt32NoTag(26);
            codedOutputStream.writeUInt32NoTag(this.lineOffsetsMemoizedSerializedSize);
        }
        for (int i = 0; i < this.lineOffsets_.size(); i++) {
            codedOutputStream.writeInt32NoTag(this.lineOffsets_.getInt(i));
        }
        GeneratedMessageV3.serializeLongMapTo(codedOutputStream, internalGetPositions(), PositionsDefaultEntryHolder.defaultEntry, 4);
        GeneratedMessageV3.serializeLongMapTo(codedOutputStream, internalGetMacroCalls(), MacroCallsDefaultEntryHolder.defaultEntry, 5);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getSyntaxVersionBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.syntaxVersion_) : 0;
        if (!getLocationBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.location_);
        }
        int iComputeInt32SizeNoTag = 0;
        for (int i2 = 0; i2 < this.lineOffsets_.size(); i2++) {
            iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(this.lineOffsets_.getInt(i2));
        }
        int iComputeMessageSize = iComputeStringSize + iComputeInt32SizeNoTag;
        if (!getLineOffsetsList().isEmpty()) {
            iComputeMessageSize = iComputeMessageSize + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag);
        }
        this.lineOffsetsMemoizedSerializedSize = iComputeInt32SizeNoTag;
        for (Map.Entry entry : internalGetPositions().getMap().entrySet()) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(4, PositionsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        for (Map.Entry entry2 : internalGetMacroCalls().getMap().entrySet()) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(5, MacroCallsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry2.getKey()).setValue(entry2.getValue()).build());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SourceInfo)) {
            return super.equals(obj);
        }
        SourceInfo sourceInfo = (SourceInfo) obj;
        return getSyntaxVersion().equals(sourceInfo.getSyntaxVersion()) && getLocation().equals(sourceInfo.getLocation()) && getLineOffsetsList().equals(sourceInfo.getLineOffsetsList()) && internalGetPositions().equals(sourceInfo.internalGetPositions()) && internalGetMacroCalls().equals(sourceInfo.internalGetMacroCalls()) && this.unknownFields.equals(sourceInfo.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSyntaxVersion().hashCode()) * 37) + 2) * 53) + getLocation().hashCode();
        if (getLineOffsetsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getLineOffsetsList().hashCode();
        }
        if (!internalGetPositions().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + internalGetPositions().hashCode();
        }
        if (!internalGetMacroCalls().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 5) * 53) + internalGetMacroCalls().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m11189newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m11192toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    private static final class PositionsDefaultEntryHolder {
        static final MapEntry<Long, Integer> defaultEntry = MapEntry.newDefaultInstance(SyntaxProto.internal_static_google_api_expr_v1alpha1_SourceInfo_PositionsEntry_descriptor, WireFormat.FieldType.INT64, 0L, WireFormat.FieldType.INT32, 0);

        private PositionsDefaultEntryHolder() {
        }
    }

    private static final class MacroCallsDefaultEntryHolder {
        static final MapEntry<Long, Expr> defaultEntry = MapEntry.newDefaultInstance(SyntaxProto.internal_static_google_api_expr_v1alpha1_SourceInfo_MacroCallsEntry_descriptor, WireFormat.FieldType.INT64, 0L, WireFormat.FieldType.MESSAGE, Expr.getDefaultInstance());

        private MacroCallsDefaultEntryHolder() {
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SourceInfoOrBuilder {
        private int bitField0_;
        private Internal.IntList lineOffsets_;
        private Object location_;
        private MapField<Long, Expr> macroCalls_;
        private MapField<Long, Integer> positions_;
        private Object syntaxVersion_;

        private Builder() {
            this.syntaxVersion_ = "";
            this.location_ = "";
            this.lineOffsets_ = SourceInfo.emptyIntList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.syntaxVersion_ = "";
            this.location_ = "";
            this.lineOffsets_ = SourceInfo.emptyIntList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_SourceInfo_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 4) {
                return internalGetPositions();
            }
            if (i == 5) {
                return internalGetMacroCalls();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected MapField internalGetMutableMapField(int i) {
            if (i == 4) {
                return internalGetMutablePositions();
            }
            if (i == 5) {
                return internalGetMutableMacroCalls();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_SourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SourceInfo.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SourceInfo.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11203clear() {
            super.clear();
            this.syntaxVersion_ = "";
            this.location_ = "";
            this.lineOffsets_ = SourceInfo.emptyIntList();
            this.bitField0_ &= -2;
            internalGetMutablePositions().clear();
            internalGetMutableMacroCalls().clear();
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_SourceInfo_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SourceInfo m11216getDefaultInstanceForType() {
            return SourceInfo.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SourceInfo m11197build() throws UninitializedMessageException {
            SourceInfo sourceInfoM11199buildPartial = m11199buildPartial();
            if (sourceInfoM11199buildPartial.isInitialized()) {
                return sourceInfoM11199buildPartial;
            }
            throw newUninitializedMessageException(sourceInfoM11199buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SourceInfo m11199buildPartial() {
            SourceInfo sourceInfo = new SourceInfo(this);
            sourceInfo.syntaxVersion_ = this.syntaxVersion_;
            sourceInfo.location_ = this.location_;
            if ((this.bitField0_ & 1) != 0) {
                this.lineOffsets_.makeImmutable();
                this.bitField0_ &= -2;
            }
            sourceInfo.lineOffsets_ = this.lineOffsets_;
            sourceInfo.positions_ = internalGetPositions();
            sourceInfo.positions_.makeImmutable();
            sourceInfo.macroCalls_ = internalGetMacroCalls();
            sourceInfo.macroCalls_.makeImmutable();
            onBuilt();
            return sourceInfo;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11215clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11227setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11205clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11208clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11229setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11195addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11220mergeFrom(Message message) {
            if (message instanceof SourceInfo) {
                return mergeFrom((SourceInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SourceInfo sourceInfo) {
            if (sourceInfo == SourceInfo.getDefaultInstance()) {
                return this;
            }
            if (!sourceInfo.getSyntaxVersion().isEmpty()) {
                this.syntaxVersion_ = sourceInfo.syntaxVersion_;
                onChanged();
            }
            if (!sourceInfo.getLocation().isEmpty()) {
                this.location_ = sourceInfo.location_;
                onChanged();
            }
            if (!sourceInfo.lineOffsets_.isEmpty()) {
                if (this.lineOffsets_.isEmpty()) {
                    this.lineOffsets_ = sourceInfo.lineOffsets_;
                    this.bitField0_ &= -2;
                } else {
                    ensureLineOffsetsIsMutable();
                    this.lineOffsets_.addAll(sourceInfo.lineOffsets_);
                }
                onChanged();
            }
            internalGetMutablePositions().mergeFrom(sourceInfo.internalGetPositions());
            internalGetMutableMacroCalls().mergeFrom(sourceInfo.internalGetMacroCalls());
            m11225mergeUnknownFields(sourceInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfo.Builder m11221mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfo.access$1300()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfo r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfo r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfo) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfo.Builder.m11221mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfo$Builder");
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public String getSyntaxVersion() {
            Object obj = this.syntaxVersion_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.syntaxVersion_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setSyntaxVersion(String str) {
            str.getClass();
            this.syntaxVersion_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public ByteString getSyntaxVersionBytes() {
            Object obj = this.syntaxVersion_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.syntaxVersion_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSyntaxVersionBytes(ByteString byteString) {
            byteString.getClass();
            SourceInfo.checkByteStringIsUtf8(byteString);
            this.syntaxVersion_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearSyntaxVersion() {
            this.syntaxVersion_ = SourceInfo.getDefaultInstance().getSyntaxVersion();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public String getLocation() {
            Object obj = this.location_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.location_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setLocation(String str) {
            str.getClass();
            this.location_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public ByteString getLocationBytes() {
            Object obj = this.location_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.location_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setLocationBytes(ByteString byteString) {
            byteString.getClass();
            SourceInfo.checkByteStringIsUtf8(byteString);
            this.location_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearLocation() {
            this.location_ = SourceInfo.getDefaultInstance().getLocation();
            onChanged();
            return this;
        }

        private void ensureLineOffsetsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.lineOffsets_ = SourceInfo.mutableCopy(this.lineOffsets_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public List<Integer> getLineOffsetsList() {
            return (this.bitField0_ & 1) != 0 ? Collections.unmodifiableList(this.lineOffsets_) : this.lineOffsets_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public int getLineOffsetsCount() {
            return this.lineOffsets_.size();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public int getLineOffsets(int i) {
            return this.lineOffsets_.getInt(i);
        }

        public Builder setLineOffsets(int i, int i2) {
            ensureLineOffsetsIsMutable();
            this.lineOffsets_.setInt(i, i2);
            onChanged();
            return this;
        }

        public Builder addLineOffsets(int i) {
            ensureLineOffsetsIsMutable();
            this.lineOffsets_.addInt(i);
            onChanged();
            return this;
        }

        public Builder addAllLineOffsets(Iterable<? extends Integer> iterable) {
            ensureLineOffsetsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.lineOffsets_);
            onChanged();
            return this;
        }

        public Builder clearLineOffsets() {
            this.lineOffsets_ = SourceInfo.emptyIntList();
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        private MapField<Long, Integer> internalGetPositions() {
            MapField<Long, Integer> mapField = this.positions_;
            return mapField == null ? MapField.emptyMapField(PositionsDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<Long, Integer> internalGetMutablePositions() {
            onChanged();
            if (this.positions_ == null) {
                this.positions_ = MapField.newMapField(PositionsDefaultEntryHolder.defaultEntry);
            }
            if (!this.positions_.isMutable()) {
                this.positions_ = this.positions_.copy();
            }
            return this.positions_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public int getPositionsCount() {
            return internalGetPositions().getMap().size();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public boolean containsPositions(long j) {
            return internalGetPositions().getMap().containsKey(Long.valueOf(j));
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        @Deprecated
        public Map<Long, Integer> getPositions() {
            return getPositionsMap();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public Map<Long, Integer> getPositionsMap() {
            return internalGetPositions().getMap();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public int getPositionsOrDefault(long j, int i) {
            Map map = internalGetPositions().getMap();
            return map.containsKey(Long.valueOf(j)) ? ((Integer) map.get(Long.valueOf(j))).intValue() : i;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public int getPositionsOrThrow(long j) {
            Map map = internalGetPositions().getMap();
            if (!map.containsKey(Long.valueOf(j))) {
                throw new IllegalArgumentException();
            }
            return ((Integer) map.get(Long.valueOf(j))).intValue();
        }

        public Builder clearPositions() {
            internalGetMutablePositions().getMutableMap().clear();
            return this;
        }

        public Builder removePositions(long j) {
            internalGetMutablePositions().getMutableMap().remove(Long.valueOf(j));
            return this;
        }

        @Deprecated
        public Map<Long, Integer> getMutablePositions() {
            return internalGetMutablePositions().getMutableMap();
        }

        public Builder putPositions(long j, int i) {
            internalGetMutablePositions().getMutableMap().put(Long.valueOf(j), Integer.valueOf(i));
            return this;
        }

        public Builder putAllPositions(Map<Long, Integer> map) {
            internalGetMutablePositions().getMutableMap().putAll(map);
            return this;
        }

        private MapField<Long, Expr> internalGetMacroCalls() {
            MapField<Long, Expr> mapField = this.macroCalls_;
            return mapField == null ? MapField.emptyMapField(MacroCallsDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<Long, Expr> internalGetMutableMacroCalls() {
            onChanged();
            if (this.macroCalls_ == null) {
                this.macroCalls_ = MapField.newMapField(MacroCallsDefaultEntryHolder.defaultEntry);
            }
            if (!this.macroCalls_.isMutable()) {
                this.macroCalls_ = this.macroCalls_.copy();
            }
            return this.macroCalls_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public int getMacroCallsCount() {
            return internalGetMacroCalls().getMap().size();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public boolean containsMacroCalls(long j) {
            return internalGetMacroCalls().getMap().containsKey(Long.valueOf(j));
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        @Deprecated
        public Map<Long, Expr> getMacroCalls() {
            return getMacroCallsMap();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public Map<Long, Expr> getMacroCallsMap() {
            return internalGetMacroCalls().getMap();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public Expr getMacroCallsOrDefault(long j, Expr expr) {
            Map map = internalGetMacroCalls().getMap();
            return map.containsKey(Long.valueOf(j)) ? (Expr) map.get(Long.valueOf(j)) : expr;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfoOrBuilder
        public Expr getMacroCallsOrThrow(long j) {
            Map map = internalGetMacroCalls().getMap();
            if (!map.containsKey(Long.valueOf(j))) {
                throw new IllegalArgumentException();
            }
            return (Expr) map.get(Long.valueOf(j));
        }

        public Builder clearMacroCalls() {
            internalGetMutableMacroCalls().getMutableMap().clear();
            return this;
        }

        public Builder removeMacroCalls(long j) {
            internalGetMutableMacroCalls().getMutableMap().remove(Long.valueOf(j));
            return this;
        }

        @Deprecated
        public Map<Long, Expr> getMutableMacroCalls() {
            return internalGetMutableMacroCalls().getMutableMap();
        }

        public Builder putMacroCalls(long j, Expr expr) {
            expr.getClass();
            internalGetMutableMacroCalls().getMutableMap().put(Long.valueOf(j), expr);
            return this;
        }

        public Builder putAllMacroCalls(Map<Long, Expr> map) {
            internalGetMutableMacroCalls().getMutableMap().putAll(map);
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m11231setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m11225mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
