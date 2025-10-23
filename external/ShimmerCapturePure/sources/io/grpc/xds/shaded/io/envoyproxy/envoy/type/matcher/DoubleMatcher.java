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
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.DoubleRange;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.DoubleRangeOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class DoubleMatcher extends GeneratedMessageV3 implements DoubleMatcherOrBuilder {
    public static final int EXACT_FIELD_NUMBER = 2;
    public static final int RANGE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final DoubleMatcher DEFAULT_INSTANCE = new DoubleMatcher();
    private static final Parser<DoubleMatcher> PARSER = new AbstractParser<DoubleMatcher>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcher.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public DoubleMatcher m33092parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DoubleMatcher(codedInputStream, extensionRegistryLite);
        }
    };
    private int matchPatternCase_;
    private Object matchPattern_;
    private byte memoizedIsInitialized;

    private DoubleMatcher(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.matchPatternCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private DoubleMatcher() {
        this.matchPatternCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private DoubleMatcher(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            DoubleRange.Builder builderM32813toBuilder = this.matchPatternCase_ == 1 ? ((DoubleRange) this.matchPattern_).m32813toBuilder() : null;
                            MessageLite message = codedInputStream.readMessage(DoubleRange.parser(), extensionRegistryLite);
                            this.matchPattern_ = message;
                            if (builderM32813toBuilder != null) {
                                builderM32813toBuilder.mergeFrom((DoubleRange) message);
                                this.matchPattern_ = builderM32813toBuilder.m32820buildPartial();
                            }
                            this.matchPatternCase_ = 1;
                        } else if (tag == 17) {
                            this.matchPatternCase_ = 2;
                            this.matchPattern_ = Double.valueOf(codedInputStream.readDouble());
                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
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

    public static DoubleMatcher getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DoubleMatcher> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return NumberProto.internal_static_envoy_type_matcher_DoubleMatcher_descriptor;
    }

    public static DoubleMatcher parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DoubleMatcher) PARSER.parseFrom(byteBuffer);
    }

    public static DoubleMatcher parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DoubleMatcher) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DoubleMatcher parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DoubleMatcher) PARSER.parseFrom(byteString);
    }

    public static DoubleMatcher parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DoubleMatcher) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DoubleMatcher parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DoubleMatcher) PARSER.parseFrom(bArr);
    }

    public static DoubleMatcher parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DoubleMatcher) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DoubleMatcher parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DoubleMatcher parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DoubleMatcher parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DoubleMatcher parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DoubleMatcher parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DoubleMatcher parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m33090toBuilder();
    }

    public static Builder newBuilder(DoubleMatcher doubleMatcher) {
        return DEFAULT_INSTANCE.m33090toBuilder().mergeFrom(doubleMatcher);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public DoubleMatcher m33085getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<DoubleMatcher> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcherOrBuilder
    public boolean hasRange() {
        return this.matchPatternCase_ == 1;
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
        return new DoubleMatcher();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return NumberProto.internal_static_envoy_type_matcher_DoubleMatcher_fieldAccessorTable.ensureFieldAccessorsInitialized(DoubleMatcher.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcherOrBuilder
    public MatchPatternCase getMatchPatternCase() {
        return MatchPatternCase.forNumber(this.matchPatternCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcherOrBuilder
    public DoubleRange getRange() {
        if (this.matchPatternCase_ == 1) {
            return (DoubleRange) this.matchPattern_;
        }
        return DoubleRange.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcherOrBuilder
    public DoubleRangeOrBuilder getRangeOrBuilder() {
        if (this.matchPatternCase_ == 1) {
            return (DoubleRange) this.matchPattern_;
        }
        return DoubleRange.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcherOrBuilder
    public double getExact() {
        if (this.matchPatternCase_ == 2) {
            return ((Double) this.matchPattern_).doubleValue();
        }
        return 0.0d;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.matchPatternCase_ == 1) {
            codedOutputStream.writeMessage(1, (DoubleRange) this.matchPattern_);
        }
        if (this.matchPatternCase_ == 2) {
            codedOutputStream.writeDouble(2, ((Double) this.matchPattern_).doubleValue());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.matchPatternCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (DoubleRange) this.matchPattern_) : 0;
        if (this.matchPatternCase_ == 2) {
            iComputeMessageSize += CodedOutputStream.computeDoubleSize(2, ((Double) this.matchPattern_).doubleValue());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DoubleMatcher)) {
            return super.equals(obj);
        }
        DoubleMatcher doubleMatcher = (DoubleMatcher) obj;
        if (!getMatchPatternCase().equals(doubleMatcher.getMatchPatternCase())) {
            return false;
        }
        int i = this.matchPatternCase_;
        if (i != 1) {
            if (i == 2 && Double.doubleToLongBits(getExact()) != Double.doubleToLongBits(doubleMatcher.getExact())) {
                return false;
            }
        } else if (!getRange().equals(doubleMatcher.getRange())) {
            return false;
        }
        return this.unknownFields.equals(doubleMatcher.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        int i2 = this.matchPatternCase_;
        if (i2 == 1) {
            i = ((iHashCode2 * 37) + 1) * 53;
            iHashCode = getRange().hashCode();
        } else {
            if (i2 == 2) {
                i = ((iHashCode2 * 37) + 2) * 53;
                iHashCode = Internal.hashLong(Double.doubleToLongBits(getExact()));
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
    public Builder m33087newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m33090toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum MatchPatternCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        RANGE(1),
        EXACT(2),
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
                return RANGE;
            }
            if (i != 2) {
                return null;
            }
            return EXACT;
        }

        @Deprecated
        public static MatchPatternCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DoubleMatcherOrBuilder {
        private int matchPatternCase_;
        private Object matchPattern_;
        private SingleFieldBuilderV3<DoubleRange, DoubleRange.Builder, DoubleRangeOrBuilder> rangeBuilder_;

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
            return NumberProto.internal_static_envoy_type_matcher_DoubleMatcher_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcherOrBuilder
        public boolean hasRange() {
            return this.matchPatternCase_ == 1;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return NumberProto.internal_static_envoy_type_matcher_DoubleMatcher_fieldAccessorTable.ensureFieldAccessorsInitialized(DoubleMatcher.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = DoubleMatcher.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33101clear() {
            super.clear();
            this.matchPatternCase_ = 0;
            this.matchPattern_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return NumberProto.internal_static_envoy_type_matcher_DoubleMatcher_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DoubleMatcher m33114getDefaultInstanceForType() {
            return DoubleMatcher.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DoubleMatcher m33095build() throws UninitializedMessageException {
            DoubleMatcher doubleMatcherM33097buildPartial = m33097buildPartial();
            if (doubleMatcherM33097buildPartial.isInitialized()) {
                return doubleMatcherM33097buildPartial;
            }
            throw newUninitializedMessageException(doubleMatcherM33097buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DoubleMatcher m33097buildPartial() {
            DoubleMatcher doubleMatcher = new DoubleMatcher(this);
            if (this.matchPatternCase_ == 1) {
                SingleFieldBuilderV3<DoubleRange, DoubleRange.Builder, DoubleRangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
                if (singleFieldBuilderV3 == null) {
                    doubleMatcher.matchPattern_ = this.matchPattern_;
                } else {
                    doubleMatcher.matchPattern_ = singleFieldBuilderV3.build();
                }
            }
            if (this.matchPatternCase_ == 2) {
                doubleMatcher.matchPattern_ = this.matchPattern_;
            }
            doubleMatcher.matchPatternCase_ = this.matchPatternCase_;
            onBuilt();
            return doubleMatcher;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33113clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33125setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33103clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33106clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33127setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33093addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33118mergeFrom(Message message) {
            if (message instanceof DoubleMatcher) {
                return mergeFrom((DoubleMatcher) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DoubleMatcher doubleMatcher) {
            if (doubleMatcher == DoubleMatcher.getDefaultInstance()) {
                return this;
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$type$matcher$DoubleMatcher$MatchPatternCase[doubleMatcher.getMatchPatternCase().ordinal()];
            if (i == 1) {
                mergeRange(doubleMatcher.getRange());
            } else if (i == 2) {
                setExact(doubleMatcher.getExact());
            }
            m33123mergeUnknownFields(doubleMatcher.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcher.Builder m33119mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcher.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcher r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcher) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcher r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcher) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcher.Builder.m33119mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcher$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcherOrBuilder
        public MatchPatternCase getMatchPatternCase() {
            return MatchPatternCase.forNumber(this.matchPatternCase_);
        }

        public Builder clearMatchPattern() {
            this.matchPatternCase_ = 0;
            this.matchPattern_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcherOrBuilder
        public DoubleRange getRange() {
            SingleFieldBuilderV3<DoubleRange, DoubleRange.Builder, DoubleRangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.matchPatternCase_ == 1) {
                    return (DoubleRange) this.matchPattern_;
                }
                return DoubleRange.getDefaultInstance();
            }
            if (this.matchPatternCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            }
            return DoubleRange.getDefaultInstance();
        }

        public Builder setRange(DoubleRange doubleRange) {
            SingleFieldBuilderV3<DoubleRange, DoubleRange.Builder, DoubleRangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                doubleRange.getClass();
                this.matchPattern_ = doubleRange;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(doubleRange);
            }
            this.matchPatternCase_ = 1;
            return this;
        }

        public Builder setRange(DoubleRange.Builder builder) {
            SingleFieldBuilderV3<DoubleRange, DoubleRange.Builder, DoubleRangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.matchPattern_ = builder.m32818build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m32818build());
            }
            this.matchPatternCase_ = 1;
            return this;
        }

        public Builder mergeRange(DoubleRange doubleRange) {
            SingleFieldBuilderV3<DoubleRange, DoubleRange.Builder, DoubleRangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.matchPatternCase_ != 1 || this.matchPattern_ == DoubleRange.getDefaultInstance()) {
                    this.matchPattern_ = doubleRange;
                } else {
                    this.matchPattern_ = DoubleRange.newBuilder((DoubleRange) this.matchPattern_).mergeFrom(doubleRange).m32820buildPartial();
                }
                onChanged();
            } else {
                if (this.matchPatternCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(doubleRange);
                }
                this.rangeBuilder_.setMessage(doubleRange);
            }
            this.matchPatternCase_ = 1;
            return this;
        }

        public Builder clearRange() {
            SingleFieldBuilderV3<DoubleRange, DoubleRange.Builder, DoubleRangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
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

        public DoubleRange.Builder getRangeBuilder() {
            return getRangeFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcherOrBuilder
        public DoubleRangeOrBuilder getRangeOrBuilder() {
            SingleFieldBuilderV3<DoubleRange, DoubleRange.Builder, DoubleRangeOrBuilder> singleFieldBuilderV3;
            int i = this.matchPatternCase_;
            if (i == 1 && (singleFieldBuilderV3 = this.rangeBuilder_) != null) {
                return (DoubleRangeOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 1) {
                return (DoubleRange) this.matchPattern_;
            }
            return DoubleRange.getDefaultInstance();
        }

        private SingleFieldBuilderV3<DoubleRange, DoubleRange.Builder, DoubleRangeOrBuilder> getRangeFieldBuilder() {
            if (this.rangeBuilder_ == null) {
                if (this.matchPatternCase_ != 1) {
                    this.matchPattern_ = DoubleRange.getDefaultInstance();
                }
                this.rangeBuilder_ = new SingleFieldBuilderV3<>((DoubleRange) this.matchPattern_, getParentForChildren(), isClean());
                this.matchPattern_ = null;
            }
            this.matchPatternCase_ = 1;
            onChanged();
            return this.rangeBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcherOrBuilder
        public double getExact() {
            if (this.matchPatternCase_ == 2) {
                return ((Double) this.matchPattern_).doubleValue();
            }
            return 0.0d;
        }

        public Builder setExact(double d) {
            this.matchPatternCase_ = 2;
            this.matchPattern_ = Double.valueOf(d);
            onChanged();
            return this;
        }

        public Builder clearExact() {
            if (this.matchPatternCase_ == 2) {
                this.matchPatternCase_ = 0;
                this.matchPattern_ = null;
                onChanged();
            }
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m33129setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m33123mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcher$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$type$matcher$DoubleMatcher$MatchPatternCase;

        static {
            int[] iArr = new int[MatchPatternCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$type$matcher$DoubleMatcher$MatchPatternCase = iArr;
            try {
                iArr[MatchPatternCase.RANGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$matcher$DoubleMatcher$MatchPatternCase[MatchPatternCase.EXACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$matcher$DoubleMatcher$MatchPatternCase[MatchPatternCase.MATCHPATTERN_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
