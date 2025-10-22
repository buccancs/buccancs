package io.grpc.channelz.v1;

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
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.channelz.v1.ChannelRef;
import io.grpc.channelz.v1.SubchannelRef;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class ChannelTraceEvent extends GeneratedMessageV3 implements ChannelTraceEventOrBuilder {
    public static final int CHANNEL_REF_FIELD_NUMBER = 4;
    public static final int DESCRIPTION_FIELD_NUMBER = 1;
    public static final int SEVERITY_FIELD_NUMBER = 2;
    public static final int SUBCHANNEL_REF_FIELD_NUMBER = 5;
    public static final int TIMESTAMP_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final ChannelTraceEvent DEFAULT_INSTANCE = new ChannelTraceEvent();
    private static final Parser<ChannelTraceEvent> PARSER = new AbstractParser<ChannelTraceEvent>() { // from class: io.grpc.channelz.v1.ChannelTraceEvent.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ChannelTraceEvent m7723parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ChannelTraceEvent(codedInputStream, extensionRegistryLite);
        }
    };
    private int childRefCase_;
    private Object childRef_;
    private volatile Object description_;
    private byte memoizedIsInitialized;
    private int severity_;
    private Timestamp timestamp_;

    private ChannelTraceEvent(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.childRefCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private ChannelTraceEvent() {
        this.childRefCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.description_ = "";
        this.severity_ = 0;
    }

    private ChannelTraceEvent(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Timestamp.Builder builderM9056toBuilder;
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
                                this.description_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag != 16) {
                                if (tag == 26) {
                                    Timestamp timestamp = this.timestamp_;
                                    builderM9056toBuilder = timestamp != null ? timestamp.toBuilder() : null;
                                    Timestamp message = codedInputStream.readMessage(Timestamp.parser(), extensionRegistryLite);
                                    this.timestamp_ = message;
                                    if (builderM9056toBuilder != null) {
                                        builderM9056toBuilder.mergeFrom(message);
                                        this.timestamp_ = builderM9056toBuilder.buildPartial();
                                    }
                                } else if (tag == 34) {
                                    builderM9056toBuilder = this.childRefCase_ == 4 ? ((ChannelRef) this.childRef_).m7629toBuilder() : null;
                                    ChannelRef message2 = codedInputStream.readMessage(ChannelRef.parser(), extensionRegistryLite);
                                    this.childRef_ = message2;
                                    if (builderM9056toBuilder != null) {
                                        builderM9056toBuilder.mergeFrom(message2);
                                        this.childRef_ = builderM9056toBuilder.m7636buildPartial();
                                    }
                                    this.childRefCase_ = 4;
                                } else if (tag == 42) {
                                    builderM9056toBuilder = this.childRefCase_ == 5 ? ((SubchannelRef) this.childRef_).m9056toBuilder() : null;
                                    SubchannelRef message3 = codedInputStream.readMessage(SubchannelRef.parser(), extensionRegistryLite);
                                    this.childRef_ = message3;
                                    if (builderM9056toBuilder != null) {
                                        builderM9056toBuilder.mergeFrom(message3);
                                        this.childRef_ = builderM9056toBuilder.m9063buildPartial();
                                    }
                                    this.childRefCase_ = 5;
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                this.severity_ = codedInputStream.readEnum();
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

    public static ChannelTraceEvent getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ChannelTraceEvent> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ChannelzProto.internal_static_grpc_channelz_v1_ChannelTraceEvent_descriptor;
    }

    public static ChannelTraceEvent parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ChannelTraceEvent) PARSER.parseFrom(byteBuffer);
    }

    public static ChannelTraceEvent parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ChannelTraceEvent) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ChannelTraceEvent parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ChannelTraceEvent) PARSER.parseFrom(byteString);
    }

    public static ChannelTraceEvent parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ChannelTraceEvent) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ChannelTraceEvent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ChannelTraceEvent) PARSER.parseFrom(bArr);
    }

    public static ChannelTraceEvent parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ChannelTraceEvent) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ChannelTraceEvent parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ChannelTraceEvent parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ChannelTraceEvent parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ChannelTraceEvent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ChannelTraceEvent parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ChannelTraceEvent parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m7721toBuilder();
    }

    public static Builder newBuilder(ChannelTraceEvent channelTraceEvent) {
        return DEFAULT_INSTANCE.m7721toBuilder().mergeFrom(channelTraceEvent);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ChannelTraceEvent m7716getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ChannelTraceEvent> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
    public int getSeverityValue() {
        return this.severity_;
    }

    @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
    public boolean hasChannelRef() {
        return this.childRefCase_ == 4;
    }

    @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
    public boolean hasSubchannelRef() {
        return this.childRefCase_ == 5;
    }

    @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
    public boolean hasTimestamp() {
        return this.timestamp_ != null;
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
        return new ChannelTraceEvent();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ChannelzProto.internal_static_grpc_channelz_v1_ChannelTraceEvent_fieldAccessorTable.ensureFieldAccessorsInitialized(ChannelTraceEvent.class, Builder.class);
    }

    @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
    public ChildRefCase getChildRefCase() {
        return ChildRefCase.forNumber(this.childRefCase_);
    }

    @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
    public String getDescription() {
        Object obj = this.description_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.description_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
    public ByteString getDescriptionBytes() {
        Object obj = this.description_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
    public Severity getSeverity() {
        Severity severityValueOf = Severity.valueOf(this.severity_);
        return severityValueOf == null ? Severity.UNRECOGNIZED : severityValueOf;
    }

    @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
    public Timestamp getTimestamp() {
        Timestamp timestamp = this.timestamp_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
    public TimestampOrBuilder getTimestampOrBuilder() {
        return getTimestamp();
    }

    @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
    public ChannelRef getChannelRef() {
        if (this.childRefCase_ == 4) {
            return (ChannelRef) this.childRef_;
        }
        return ChannelRef.getDefaultInstance();
    }

    @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
    public ChannelRefOrBuilder getChannelRefOrBuilder() {
        if (this.childRefCase_ == 4) {
            return (ChannelRef) this.childRef_;
        }
        return ChannelRef.getDefaultInstance();
    }

    @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
    public SubchannelRef getSubchannelRef() {
        if (this.childRefCase_ == 5) {
            return (SubchannelRef) this.childRef_;
        }
        return SubchannelRef.getDefaultInstance();
    }

    @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
    public SubchannelRefOrBuilder getSubchannelRefOrBuilder() {
        if (this.childRefCase_ == 5) {
            return (SubchannelRef) this.childRef_;
        }
        return SubchannelRef.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.description_);
        }
        if (this.severity_ != Severity.CT_UNKNOWN.getNumber()) {
            codedOutputStream.writeEnum(2, this.severity_);
        }
        if (this.timestamp_ != null) {
            codedOutputStream.writeMessage(3, getTimestamp());
        }
        if (this.childRefCase_ == 4) {
            codedOutputStream.writeMessage(4, (ChannelRef) this.childRef_);
        }
        if (this.childRefCase_ == 5) {
            codedOutputStream.writeMessage(5, (SubchannelRef) this.childRef_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getDescriptionBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.description_) : 0;
        if (this.severity_ != Severity.CT_UNKNOWN.getNumber()) {
            iComputeStringSize += CodedOutputStream.computeEnumSize(2, this.severity_);
        }
        if (this.timestamp_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, getTimestamp());
        }
        if (this.childRefCase_ == 4) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(4, (ChannelRef) this.childRef_);
        }
        if (this.childRefCase_ == 5) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(5, (SubchannelRef) this.childRef_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ChannelTraceEvent)) {
            return super.equals(obj);
        }
        ChannelTraceEvent channelTraceEvent = (ChannelTraceEvent) obj;
        if (!getDescription().equals(channelTraceEvent.getDescription()) || this.severity_ != channelTraceEvent.severity_ || hasTimestamp() != channelTraceEvent.hasTimestamp()) {
            return false;
        }
        if ((hasTimestamp() && !getTimestamp().equals(channelTraceEvent.getTimestamp())) || !getChildRefCase().equals(channelTraceEvent.getChildRefCase())) {
            return false;
        }
        int i = this.childRefCase_;
        if (i == 4) {
            if (!getChannelRef().equals(channelTraceEvent.getChannelRef())) {
                return false;
            }
        } else if (i == 5 && !getSubchannelRef().equals(channelTraceEvent.getSubchannelRef())) {
            return false;
        }
        return this.unknownFields.equals(channelTraceEvent.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getDescription().hashCode()) * 37) + 2) * 53) + this.severity_;
        if (hasTimestamp()) {
            iHashCode2 = (((iHashCode2 * 37) + 3) * 53) + getTimestamp().hashCode();
        }
        int i2 = this.childRefCase_;
        if (i2 == 4) {
            i = ((iHashCode2 * 37) + 4) * 53;
            iHashCode = getChannelRef().hashCode();
        } else {
            if (i2 == 5) {
                i = ((iHashCode2 * 37) + 5) * 53;
                iHashCode = getSubchannelRef().hashCode();
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
    public Builder m7718newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m7721toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum Severity implements ProtocolMessageEnum {
        CT_UNKNOWN(0),
        CT_INFO(1),
        CT_WARNING(2),
        CT_ERROR(3),
        UNRECOGNIZED(-1);

        public static final int CT_ERROR_VALUE = 3;
        public static final int CT_INFO_VALUE = 1;
        public static final int CT_UNKNOWN_VALUE = 0;
        public static final int CT_WARNING_VALUE = 2;
        private static final Internal.EnumLiteMap<Severity> internalValueMap = new Internal.EnumLiteMap<Severity>() { // from class: io.grpc.channelz.v1.ChannelTraceEvent.Severity.1
            public Severity findValueByNumber(int i) {
                return Severity.forNumber(i);
            }
        };
        private static final Severity[] VALUES = values();
        private final int value;

        Severity(int i) {
            this.value = i;
        }

        public static Severity forNumber(int i) {
            if (i == 0) {
                return CT_UNKNOWN;
            }
            if (i == 1) {
                return CT_INFO;
            }
            if (i == 2) {
                return CT_WARNING;
            }
            if (i != 3) {
                return null;
            }
            return CT_ERROR;
        }

        public static Internal.EnumLiteMap<Severity> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static Severity valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) ChannelTraceEvent.getDescriptor().getEnumTypes().get(0);
        }

        public static Severity valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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

    public enum ChildRefCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        CHANNEL_REF(4),
        SUBCHANNEL_REF(5),
        CHILDREF_NOT_SET(0);

        private final int value;

        ChildRefCase(int i) {
            this.value = i;
        }

        public static ChildRefCase forNumber(int i) {
            if (i == 0) {
                return CHILDREF_NOT_SET;
            }
            if (i == 4) {
                return CHANNEL_REF;
            }
            if (i != 5) {
                return null;
            }
            return SUBCHANNEL_REF;
        }

        @Deprecated
        public static ChildRefCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ChannelTraceEventOrBuilder {
        private SingleFieldBuilderV3<ChannelRef, ChannelRef.Builder, ChannelRefOrBuilder> channelRefBuilder_;
        private int childRefCase_;
        private Object childRef_;
        private Object description_;
        private int severity_;
        private SingleFieldBuilderV3<SubchannelRef, SubchannelRef.Builder, SubchannelRefOrBuilder> subchannelRefBuilder_;
        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> timestampBuilder_;
        private Timestamp timestamp_;

        private Builder() {
            this.childRefCase_ = 0;
            this.description_ = "";
            this.severity_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.childRefCase_ = 0;
            this.description_ = "";
            this.severity_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ChannelzProto.internal_static_grpc_channelz_v1_ChannelTraceEvent_descriptor;
        }

        @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
        public int getSeverityValue() {
            return this.severity_;
        }

        public Builder setSeverityValue(int i) {
            this.severity_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
        public boolean hasChannelRef() {
            return this.childRefCase_ == 4;
        }

        @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
        public boolean hasSubchannelRef() {
            return this.childRefCase_ == 5;
        }

        @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
        public boolean hasTimestamp() {
            return (this.timestampBuilder_ == null && this.timestamp_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ChannelzProto.internal_static_grpc_channelz_v1_ChannelTraceEvent_fieldAccessorTable.ensureFieldAccessorsInitialized(ChannelTraceEvent.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ChannelTraceEvent.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7732clear() {
            super.clear();
            this.description_ = "";
            this.severity_ = 0;
            if (this.timestampBuilder_ == null) {
                this.timestamp_ = null;
            } else {
                this.timestamp_ = null;
                this.timestampBuilder_ = null;
            }
            this.childRefCase_ = 0;
            this.childRef_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ChannelzProto.internal_static_grpc_channelz_v1_ChannelTraceEvent_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ChannelTraceEvent m7745getDefaultInstanceForType() {
            return ChannelTraceEvent.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ChannelTraceEvent m7726build() throws UninitializedMessageException {
            ChannelTraceEvent channelTraceEventM7728buildPartial = m7728buildPartial();
            if (channelTraceEventM7728buildPartial.isInitialized()) {
                return channelTraceEventM7728buildPartial;
            }
            throw newUninitializedMessageException(channelTraceEventM7728buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ChannelTraceEvent m7728buildPartial() {
            ChannelTraceEvent channelTraceEvent = new ChannelTraceEvent(this);
            channelTraceEvent.description_ = this.description_;
            channelTraceEvent.severity_ = this.severity_;
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                channelTraceEvent.timestamp_ = this.timestamp_;
            } else {
                channelTraceEvent.timestamp_ = singleFieldBuilderV3.build();
            }
            if (this.childRefCase_ == 4) {
                SingleFieldBuilderV3<ChannelRef, ChannelRef.Builder, ChannelRefOrBuilder> singleFieldBuilderV32 = this.channelRefBuilder_;
                if (singleFieldBuilderV32 == null) {
                    channelTraceEvent.childRef_ = this.childRef_;
                } else {
                    channelTraceEvent.childRef_ = singleFieldBuilderV32.build();
                }
            }
            if (this.childRefCase_ == 5) {
                SingleFieldBuilderV3<SubchannelRef, SubchannelRef.Builder, SubchannelRefOrBuilder> singleFieldBuilderV33 = this.subchannelRefBuilder_;
                if (singleFieldBuilderV33 == null) {
                    channelTraceEvent.childRef_ = this.childRef_;
                } else {
                    channelTraceEvent.childRef_ = singleFieldBuilderV33.build();
                }
            }
            channelTraceEvent.childRefCase_ = this.childRefCase_;
            onBuilt();
            return channelTraceEvent;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7744clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7756setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7734clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7737clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7758setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7724addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m7749mergeFrom(Message message) {
            if (message instanceof ChannelTraceEvent) {
                return mergeFrom((ChannelTraceEvent) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ChannelTraceEvent channelTraceEvent) {
            if (channelTraceEvent == ChannelTraceEvent.getDefaultInstance()) {
                return this;
            }
            if (!channelTraceEvent.getDescription().isEmpty()) {
                this.description_ = channelTraceEvent.description_;
                onChanged();
            }
            if (channelTraceEvent.severity_ != 0) {
                setSeverityValue(channelTraceEvent.getSeverityValue());
            }
            if (channelTraceEvent.hasTimestamp()) {
                mergeTimestamp(channelTraceEvent.getTimestamp());
            }
            int i = AnonymousClass2.$SwitchMap$io$grpc$channelz$v1$ChannelTraceEvent$ChildRefCase[channelTraceEvent.getChildRefCase().ordinal()];
            if (i == 1) {
                mergeChannelRef(channelTraceEvent.getChannelRef());
            } else if (i == 2) {
                mergeSubchannelRef(channelTraceEvent.getSubchannelRef());
            }
            m7754mergeUnknownFields(channelTraceEvent.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.channelz.v1.ChannelTraceEvent.Builder m7750mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.channelz.v1.ChannelTraceEvent.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.channelz.v1.ChannelTraceEvent r3 = (io.grpc.channelz.v1.ChannelTraceEvent) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.channelz.v1.ChannelTraceEvent r4 = (io.grpc.channelz.v1.ChannelTraceEvent) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.channelz.v1.ChannelTraceEvent.Builder.m7750mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.channelz.v1.ChannelTraceEvent$Builder");
        }

        @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
        public ChildRefCase getChildRefCase() {
            return ChildRefCase.forNumber(this.childRefCase_);
        }

        public Builder clearChildRef() {
            this.childRefCase_ = 0;
            this.childRef_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
        public String getDescription() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.description_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setDescription(String str) {
            str.getClass();
            this.description_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.description_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            byteString.getClass();
            ChannelTraceEvent.checkByteStringIsUtf8(byteString);
            this.description_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearDescription() {
            this.description_ = ChannelTraceEvent.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
        public Severity getSeverity() {
            Severity severityValueOf = Severity.valueOf(this.severity_);
            return severityValueOf == null ? Severity.UNRECOGNIZED : severityValueOf;
        }

        public Builder setSeverity(Severity severity) {
            severity.getClass();
            this.severity_ = severity.getNumber();
            onChanged();
            return this;
        }

        public Builder clearSeverity() {
            this.severity_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
        public Timestamp getTimestamp() {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Timestamp timestamp = this.timestamp_;
            return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
        }

        public Builder setTimestamp(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                timestamp.getClass();
                this.timestamp_ = timestamp;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(timestamp);
            }
            return this;
        }

        public Builder setTimestamp(Timestamp.Builder builder) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.timestamp_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeTimestamp(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                Timestamp timestamp2 = this.timestamp_;
                if (timestamp2 != null) {
                    this.timestamp_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
                } else {
                    this.timestamp_ = timestamp;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(timestamp);
            }
            return this;
        }

        public Builder clearTimestamp() {
            if (this.timestampBuilder_ == null) {
                this.timestamp_ = null;
                onChanged();
            } else {
                this.timestamp_ = null;
                this.timestampBuilder_ = null;
            }
            return this;
        }

        public Timestamp.Builder getTimestampBuilder() {
            onChanged();
            return getTimestampFieldBuilder().getBuilder();
        }

        @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
        public TimestampOrBuilder getTimestampOrBuilder() {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Timestamp timestamp = this.timestamp_;
            return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
        }

        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> getTimestampFieldBuilder() {
            if (this.timestampBuilder_ == null) {
                this.timestampBuilder_ = new SingleFieldBuilderV3<>(getTimestamp(), getParentForChildren(), isClean());
                this.timestamp_ = null;
            }
            return this.timestampBuilder_;
        }

        @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
        public ChannelRef getChannelRef() {
            SingleFieldBuilderV3<ChannelRef, ChannelRef.Builder, ChannelRefOrBuilder> singleFieldBuilderV3 = this.channelRefBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.childRefCase_ == 4) {
                    return (ChannelRef) this.childRef_;
                }
                return ChannelRef.getDefaultInstance();
            }
            if (this.childRefCase_ == 4) {
                return singleFieldBuilderV3.getMessage();
            }
            return ChannelRef.getDefaultInstance();
        }

        public Builder setChannelRef(ChannelRef channelRef) {
            SingleFieldBuilderV3<ChannelRef, ChannelRef.Builder, ChannelRefOrBuilder> singleFieldBuilderV3 = this.channelRefBuilder_;
            if (singleFieldBuilderV3 == null) {
                channelRef.getClass();
                this.childRef_ = channelRef;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(channelRef);
            }
            this.childRefCase_ = 4;
            return this;
        }

        public Builder setChannelRef(ChannelRef.Builder builder) {
            SingleFieldBuilderV3<ChannelRef, ChannelRef.Builder, ChannelRefOrBuilder> singleFieldBuilderV3 = this.channelRefBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.childRef_ = builder.m7634build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m7634build());
            }
            this.childRefCase_ = 4;
            return this;
        }

        public Builder mergeChannelRef(ChannelRef channelRef) {
            SingleFieldBuilderV3<ChannelRef, ChannelRef.Builder, ChannelRefOrBuilder> singleFieldBuilderV3 = this.channelRefBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.childRefCase_ != 4 || this.childRef_ == ChannelRef.getDefaultInstance()) {
                    this.childRef_ = channelRef;
                } else {
                    this.childRef_ = ChannelRef.newBuilder((ChannelRef) this.childRef_).mergeFrom(channelRef).m7636buildPartial();
                }
                onChanged();
            } else {
                if (this.childRefCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(channelRef);
                }
                this.channelRefBuilder_.setMessage(channelRef);
            }
            this.childRefCase_ = 4;
            return this;
        }

        public Builder clearChannelRef() {
            SingleFieldBuilderV3<ChannelRef, ChannelRef.Builder, ChannelRefOrBuilder> singleFieldBuilderV3 = this.channelRefBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.childRefCase_ == 4) {
                    this.childRefCase_ = 0;
                    this.childRef_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.childRefCase_ == 4) {
                this.childRefCase_ = 0;
                this.childRef_ = null;
                onChanged();
            }
            return this;
        }

        public ChannelRef.Builder getChannelRefBuilder() {
            return getChannelRefFieldBuilder().getBuilder();
        }

        @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
        public ChannelRefOrBuilder getChannelRefOrBuilder() {
            SingleFieldBuilderV3<ChannelRef, ChannelRef.Builder, ChannelRefOrBuilder> singleFieldBuilderV3;
            int i = this.childRefCase_;
            if (i == 4 && (singleFieldBuilderV3 = this.channelRefBuilder_) != null) {
                return (ChannelRefOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 4) {
                return (ChannelRef) this.childRef_;
            }
            return ChannelRef.getDefaultInstance();
        }

        private SingleFieldBuilderV3<ChannelRef, ChannelRef.Builder, ChannelRefOrBuilder> getChannelRefFieldBuilder() {
            if (this.channelRefBuilder_ == null) {
                if (this.childRefCase_ != 4) {
                    this.childRef_ = ChannelRef.getDefaultInstance();
                }
                this.channelRefBuilder_ = new SingleFieldBuilderV3<>((ChannelRef) this.childRef_, getParentForChildren(), isClean());
                this.childRef_ = null;
            }
            this.childRefCase_ = 4;
            onChanged();
            return this.channelRefBuilder_;
        }

        @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
        public SubchannelRef getSubchannelRef() {
            SingleFieldBuilderV3<SubchannelRef, SubchannelRef.Builder, SubchannelRefOrBuilder> singleFieldBuilderV3 = this.subchannelRefBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.childRefCase_ == 5) {
                    return (SubchannelRef) this.childRef_;
                }
                return SubchannelRef.getDefaultInstance();
            }
            if (this.childRefCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return SubchannelRef.getDefaultInstance();
        }

        public Builder setSubchannelRef(SubchannelRef subchannelRef) {
            SingleFieldBuilderV3<SubchannelRef, SubchannelRef.Builder, SubchannelRefOrBuilder> singleFieldBuilderV3 = this.subchannelRefBuilder_;
            if (singleFieldBuilderV3 == null) {
                subchannelRef.getClass();
                this.childRef_ = subchannelRef;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(subchannelRef);
            }
            this.childRefCase_ = 5;
            return this;
        }

        public Builder setSubchannelRef(SubchannelRef.Builder builder) {
            SingleFieldBuilderV3<SubchannelRef, SubchannelRef.Builder, SubchannelRefOrBuilder> singleFieldBuilderV3 = this.subchannelRefBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.childRef_ = builder.m9061build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m9061build());
            }
            this.childRefCase_ = 5;
            return this;
        }

        public Builder mergeSubchannelRef(SubchannelRef subchannelRef) {
            SingleFieldBuilderV3<SubchannelRef, SubchannelRef.Builder, SubchannelRefOrBuilder> singleFieldBuilderV3 = this.subchannelRefBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.childRefCase_ != 5 || this.childRef_ == SubchannelRef.getDefaultInstance()) {
                    this.childRef_ = subchannelRef;
                } else {
                    this.childRef_ = SubchannelRef.newBuilder((SubchannelRef) this.childRef_).mergeFrom(subchannelRef).m9063buildPartial();
                }
                onChanged();
            } else {
                if (this.childRefCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(subchannelRef);
                }
                this.subchannelRefBuilder_.setMessage(subchannelRef);
            }
            this.childRefCase_ = 5;
            return this;
        }

        public Builder clearSubchannelRef() {
            SingleFieldBuilderV3<SubchannelRef, SubchannelRef.Builder, SubchannelRefOrBuilder> singleFieldBuilderV3 = this.subchannelRefBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.childRefCase_ == 5) {
                    this.childRefCase_ = 0;
                    this.childRef_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.childRefCase_ == 5) {
                this.childRefCase_ = 0;
                this.childRef_ = null;
                onChanged();
            }
            return this;
        }

        public SubchannelRef.Builder getSubchannelRefBuilder() {
            return getSubchannelRefFieldBuilder().getBuilder();
        }

        @Override // io.grpc.channelz.v1.ChannelTraceEventOrBuilder
        public SubchannelRefOrBuilder getSubchannelRefOrBuilder() {
            SingleFieldBuilderV3<SubchannelRef, SubchannelRef.Builder, SubchannelRefOrBuilder> singleFieldBuilderV3;
            int i = this.childRefCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.subchannelRefBuilder_) != null) {
                return (SubchannelRefOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (SubchannelRef) this.childRef_;
            }
            return SubchannelRef.getDefaultInstance();
        }

        private SingleFieldBuilderV3<SubchannelRef, SubchannelRef.Builder, SubchannelRefOrBuilder> getSubchannelRefFieldBuilder() {
            if (this.subchannelRefBuilder_ == null) {
                if (this.childRefCase_ != 5) {
                    this.childRef_ = SubchannelRef.getDefaultInstance();
                }
                this.subchannelRefBuilder_ = new SingleFieldBuilderV3<>((SubchannelRef) this.childRef_, getParentForChildren(), isClean());
                this.childRef_ = null;
            }
            this.childRefCase_ = 5;
            onChanged();
            return this.subchannelRefBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m7760setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m7754mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.channelz.v1.ChannelTraceEvent$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$channelz$v1$ChannelTraceEvent$ChildRefCase;

        static {
            int[] iArr = new int[ChildRefCase.values().length];
            $SwitchMap$io$grpc$channelz$v1$ChannelTraceEvent$ChildRefCase = iArr;
            try {
                iArr[ChildRefCase.CHANNEL_REF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$channelz$v1$ChannelTraceEvent$ChildRefCase[ChildRefCase.SUBCHANNEL_REF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$grpc$channelz$v1$ChannelTraceEvent$ChildRefCase[ChildRefCase.CHILDREF_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
