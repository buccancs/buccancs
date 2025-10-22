package io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2;

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
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKey;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKeyOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKind;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKindOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class CustomTag extends GeneratedMessageV3 implements CustomTagOrBuilder {
    public static final int ENVIRONMENT_FIELD_NUMBER = 3;
    public static final int LITERAL_FIELD_NUMBER = 2;
    public static final int METADATA_FIELD_NUMBER = 5;
    public static final int REQUEST_HEADER_FIELD_NUMBER = 4;
    public static final int TAG_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final CustomTag DEFAULT_INSTANCE = new CustomTag();
    private static final Parser<CustomTag> PARSER = new AbstractParser<CustomTag>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public CustomTag m34518parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CustomTag(codedInputStream, extensionRegistryLite);
        }
    };
    private byte memoizedIsInitialized;
    private volatile Object tag_;
    private int typeCase_;
    private Object type_;

    private CustomTag(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.typeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private CustomTag() {
        this.typeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.tag_ = "";
    }

    private CustomTag(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag != 10) {
                            if (tag == 18) {
                                Literal.Builder builderM34654toBuilder = this.typeCase_ == 2 ? ((Literal) this.type_).m34654toBuilder() : null;
                                MessageLite message = codedInputStream.readMessage(Literal.parser(), extensionRegistryLite);
                                this.type_ = message;
                                if (builderM34654toBuilder != null) {
                                    builderM34654toBuilder.mergeFrom((Literal) message);
                                    this.type_ = builderM34654toBuilder.m34661buildPartial();
                                }
                                this.typeCase_ = 2;
                            } else if (tag == 26) {
                                Environment.Builder builderM34562toBuilder = this.typeCase_ == 3 ? ((Environment) this.type_).m34562toBuilder() : null;
                                MessageLite message2 = codedInputStream.readMessage(Environment.parser(), extensionRegistryLite);
                                this.type_ = message2;
                                if (builderM34562toBuilder != null) {
                                    builderM34562toBuilder.mergeFrom((Environment) message2);
                                    this.type_ = builderM34562toBuilder.m34569buildPartial();
                                }
                                this.typeCase_ = 3;
                            } else if (tag == 34) {
                                Header.Builder builderM34608toBuilder = this.typeCase_ == 4 ? ((Header) this.type_).m34608toBuilder() : null;
                                MessageLite message3 = codedInputStream.readMessage(Header.parser(), extensionRegistryLite);
                                this.type_ = message3;
                                if (builderM34608toBuilder != null) {
                                    builderM34608toBuilder.mergeFrom((Header) message3);
                                    this.type_ = builderM34608toBuilder.m34615buildPartial();
                                }
                                this.typeCase_ = 4;
                            } else if (tag == 42) {
                                Metadata.Builder builderM34700toBuilder = this.typeCase_ == 5 ? ((Metadata) this.type_).m34700toBuilder() : null;
                                MessageLite message4 = codedInputStream.readMessage(Metadata.parser(), extensionRegistryLite);
                                this.type_ = message4;
                                if (builderM34700toBuilder != null) {
                                    builderM34700toBuilder.mergeFrom((Metadata) message4);
                                    this.type_ = builderM34700toBuilder.m34707buildPartial();
                                }
                                this.typeCase_ = 5;
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.tag_ = codedInputStream.readStringRequireUtf8();
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

    public static CustomTag getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CustomTag> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_descriptor;
    }

    public static CustomTag parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (CustomTag) PARSER.parseFrom(byteBuffer);
    }

    public static CustomTag parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CustomTag) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static CustomTag parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CustomTag) PARSER.parseFrom(byteString);
    }

    public static CustomTag parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CustomTag) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static CustomTag parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CustomTag) PARSER.parseFrom(bArr);
    }

    public static CustomTag parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CustomTag) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static CustomTag parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static CustomTag parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CustomTag parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static CustomTag parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CustomTag parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static CustomTag parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m34516toBuilder();
    }

    public static Builder newBuilder(CustomTag customTag) {
        return DEFAULT_INSTANCE.m34516toBuilder().mergeFrom(customTag);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public CustomTag m34511getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<CustomTag> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public boolean hasEnvironment() {
        return this.typeCase_ == 3;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public boolean hasLiteral() {
        return this.typeCase_ == 2;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public boolean hasMetadata() {
        return this.typeCase_ == 5;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public boolean hasRequestHeader() {
        return this.typeCase_ == 4;
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
        return new CustomTag();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_fieldAccessorTable.ensureFieldAccessorsInitialized(CustomTag.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public TypeCase getTypeCase() {
        return TypeCase.forNumber(this.typeCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public String getTag() {
        Object obj = this.tag_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.tag_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public ByteString getTagBytes() {
        Object obj = this.tag_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.tag_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public Literal getLiteral() {
        if (this.typeCase_ == 2) {
            return (Literal) this.type_;
        }
        return Literal.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public LiteralOrBuilder getLiteralOrBuilder() {
        if (this.typeCase_ == 2) {
            return (Literal) this.type_;
        }
        return Literal.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public Environment getEnvironment() {
        if (this.typeCase_ == 3) {
            return (Environment) this.type_;
        }
        return Environment.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public EnvironmentOrBuilder getEnvironmentOrBuilder() {
        if (this.typeCase_ == 3) {
            return (Environment) this.type_;
        }
        return Environment.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public Header getRequestHeader() {
        if (this.typeCase_ == 4) {
            return (Header) this.type_;
        }
        return Header.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public HeaderOrBuilder getRequestHeaderOrBuilder() {
        if (this.typeCase_ == 4) {
            return (Header) this.type_;
        }
        return Header.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public Metadata getMetadata() {
        if (this.typeCase_ == 5) {
            return (Metadata) this.type_;
        }
        return Metadata.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
    public MetadataOrBuilder getMetadataOrBuilder() {
        if (this.typeCase_ == 5) {
            return (Metadata) this.type_;
        }
        return Metadata.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getTagBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.tag_);
        }
        if (this.typeCase_ == 2) {
            codedOutputStream.writeMessage(2, (Literal) this.type_);
        }
        if (this.typeCase_ == 3) {
            codedOutputStream.writeMessage(3, (Environment) this.type_);
        }
        if (this.typeCase_ == 4) {
            codedOutputStream.writeMessage(4, (Header) this.type_);
        }
        if (this.typeCase_ == 5) {
            codedOutputStream.writeMessage(5, (Metadata) this.type_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getTagBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.tag_) : 0;
        if (this.typeCase_ == 2) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, (Literal) this.type_);
        }
        if (this.typeCase_ == 3) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, (Environment) this.type_);
        }
        if (this.typeCase_ == 4) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(4, (Header) this.type_);
        }
        if (this.typeCase_ == 5) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(5, (Metadata) this.type_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CustomTag)) {
            return super.equals(obj);
        }
        CustomTag customTag = (CustomTag) obj;
        if (!getTag().equals(customTag.getTag()) || !getTypeCase().equals(customTag.getTypeCase())) {
            return false;
        }
        int i = this.typeCase_;
        if (i != 2) {
            if (i != 3) {
                if (i == 4) {
                    if (!getRequestHeader().equals(customTag.getRequestHeader())) {
                        return false;
                    }
                } else if (i == 5 && !getMetadata().equals(customTag.getMetadata())) {
                    return false;
                }
            } else if (!getEnvironment().equals(customTag.getEnvironment())) {
                return false;
            }
        } else if (!getLiteral().equals(customTag.getLiteral())) {
            return false;
        }
        return this.unknownFields.equals(customTag.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTag().hashCode();
        int i2 = this.typeCase_;
        if (i2 == 2) {
            i = ((iHashCode2 * 37) + 2) * 53;
            iHashCode = getLiteral().hashCode();
        } else if (i2 == 3) {
            i = ((iHashCode2 * 37) + 3) * 53;
            iHashCode = getEnvironment().hashCode();
        } else if (i2 == 4) {
            i = ((iHashCode2 * 37) + 4) * 53;
            iHashCode = getRequestHeader().hashCode();
        } else {
            if (i2 == 5) {
                i = ((iHashCode2 * 37) + 5) * 53;
                iHashCode = getMetadata().hashCode();
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
    public Builder m34513newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m34516toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum TypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        LITERAL(2),
        ENVIRONMENT(3),
        REQUEST_HEADER(4),
        METADATA(5),
        TYPE_NOT_SET(0);

        private final int value;

        TypeCase(int i) {
            this.value = i;
        }

        public static TypeCase forNumber(int i) {
            if (i == 0) {
                return TYPE_NOT_SET;
            }
            if (i == 2) {
                return LITERAL;
            }
            if (i == 3) {
                return ENVIRONMENT;
            }
            if (i == 4) {
                return REQUEST_HEADER;
            }
            if (i != 5) {
                return null;
            }
            return METADATA;
        }

        @Deprecated
        public static TypeCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public interface EnvironmentOrBuilder extends MessageOrBuilder {
        String getDefaultValue();

        ByteString getDefaultValueBytes();

        String getName();

        ByteString getNameBytes();
    }

    public interface HeaderOrBuilder extends MessageOrBuilder {
        String getDefaultValue();

        ByteString getDefaultValueBytes();

        String getName();

        ByteString getNameBytes();
    }

    public interface LiteralOrBuilder extends MessageOrBuilder {
        String getValue();

        ByteString getValueBytes();
    }

    public interface MetadataOrBuilder extends MessageOrBuilder {
        String getDefaultValue();

        ByteString getDefaultValueBytes();

        MetadataKind getKind();

        MetadataKindOrBuilder getKindOrBuilder();

        MetadataKey getMetadataKey();

        MetadataKeyOrBuilder getMetadataKeyOrBuilder();

        boolean hasKind();

        boolean hasMetadataKey();
    }

    public static final class Literal extends GeneratedMessageV3 implements LiteralOrBuilder {
        public static final int VALUE_FIELD_NUMBER = 1;
        private static final Literal DEFAULT_INSTANCE = new Literal();
        private static final Parser<Literal> PARSER = new AbstractParser<Literal>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Literal.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Literal m34656parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Literal(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private volatile Object value_;

        private Literal(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Literal() {
            this.memoizedIsInitialized = (byte) -1;
            this.value_ = "";
        }

        private Literal(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.value_ = codedInputStream.readStringRequireUtf8();
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

        public static Literal getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Literal> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Literal_descriptor;
        }

        public static Literal parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Literal) PARSER.parseFrom(byteBuffer);
        }

        public static Literal parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Literal) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Literal parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Literal) PARSER.parseFrom(byteString);
        }

        public static Literal parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Literal) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Literal parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Literal) PARSER.parseFrom(bArr);
        }

        public static Literal parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Literal) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Literal parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Literal parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Literal parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Literal parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Literal parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Literal parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m34654toBuilder();
        }

        public static Builder newBuilder(Literal literal) {
            return DEFAULT_INSTANCE.m34654toBuilder().mergeFrom(literal);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Literal m34649getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Literal> getParserForType() {
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
            return new Literal();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Literal_fieldAccessorTable.ensureFieldAccessorsInitialized(Literal.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.LiteralOrBuilder
        public String getValue() {
            Object obj = this.value_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.value_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.LiteralOrBuilder
        public ByteString getValueBytes() {
            Object obj = this.value_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.value_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getValueBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.value_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (!getValueBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.value_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeStringSize;
            return iComputeStringSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Literal)) {
                return super.equals(obj);
            }
            Literal literal = (Literal) obj;
            return getValue().equals(literal.getValue()) && this.unknownFields.equals(literal.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getValue().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34651newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34654toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LiteralOrBuilder {
            private Object value_;

            private Builder() {
                this.value_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.value_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Literal_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Literal_fieldAccessorTable.ensureFieldAccessorsInitialized(Literal.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Literal.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34665clear() {
                super.clear();
                this.value_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Literal_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Literal m34678getDefaultInstanceForType() {
                return Literal.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Literal m34659build() throws UninitializedMessageException {
                Literal literalM34661buildPartial = m34661buildPartial();
                if (literalM34661buildPartial.isInitialized()) {
                    return literalM34661buildPartial;
                }
                throw newUninitializedMessageException(literalM34661buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Literal m34661buildPartial() {
                Literal literal = new Literal(this);
                literal.value_ = this.value_;
                onBuilt();
                return literal;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34677clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34689setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34667clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34670clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34691setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34657addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34682mergeFrom(Message message) {
                if (message instanceof Literal) {
                    return mergeFrom((Literal) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Literal literal) {
                if (literal == Literal.getDefaultInstance()) {
                    return this;
                }
                if (!literal.getValue().isEmpty()) {
                    this.value_ = literal.value_;
                    onChanged();
                }
                m34687mergeUnknownFields(literal.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Literal.Builder m34683mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Literal.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag$Literal r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Literal) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag$Literal r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Literal) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Literal.Builder.m34683mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag$Literal$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.LiteralOrBuilder
            public String getValue() {
                Object obj = this.value_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.value_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setValue(String str) {
                str.getClass();
                this.value_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.LiteralOrBuilder
            public ByteString getValueBytes() {
                Object obj = this.value_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.value_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setValueBytes(ByteString byteString) {
                byteString.getClass();
                Literal.checkByteStringIsUtf8(byteString);
                this.value_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearValue() {
                this.value_ = Literal.getDefaultInstance().getValue();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34693setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34687mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Environment extends GeneratedMessageV3 implements EnvironmentOrBuilder {
        public static final int DEFAULT_VALUE_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final Environment DEFAULT_INSTANCE = new Environment();
        private static final Parser<Environment> PARSER = new AbstractParser<Environment>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Environment.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Environment m34564parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Environment(codedInputStream, extensionRegistryLite);
            }
        };
        private volatile Object defaultValue_;
        private byte memoizedIsInitialized;
        private volatile Object name_;

        private Environment(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Environment() {
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
            this.defaultValue_ = "";
        }

        private Environment(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                } else if (tag == 18) {
                                    this.defaultValue_ = codedInputStream.readStringRequireUtf8();
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

        public static Environment getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Environment> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Environment_descriptor;
        }

        public static Environment parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Environment) PARSER.parseFrom(byteBuffer);
        }

        public static Environment parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Environment) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Environment parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Environment) PARSER.parseFrom(byteString);
        }

        public static Environment parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Environment) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Environment parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Environment) PARSER.parseFrom(bArr);
        }

        public static Environment parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Environment) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Environment parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Environment parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Environment parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Environment parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Environment parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Environment parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m34562toBuilder();
        }

        public static Builder newBuilder(Environment environment) {
            return DEFAULT_INSTANCE.m34562toBuilder().mergeFrom(environment);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Environment m34557getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Environment> getParserForType() {
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
            return new Environment();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Environment_fieldAccessorTable.ensureFieldAccessorsInitialized(Environment.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.EnvironmentOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.EnvironmentOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.EnvironmentOrBuilder
        public String getDefaultValue() {
            Object obj = this.defaultValue_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.defaultValue_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.EnvironmentOrBuilder
        public ByteString getDefaultValueBytes() {
            Object obj = this.defaultValue_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.defaultValue_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
            }
            if (!getDefaultValueBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.defaultValue_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
            if (!getDefaultValueBytes().isEmpty()) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.defaultValue_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Environment)) {
                return super.equals(obj);
            }
            Environment environment = (Environment) obj;
            return getName().equals(environment.getName()) && getDefaultValue().equals(environment.getDefaultValue()) && this.unknownFields.equals(environment.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getDefaultValue().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34559newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34562toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EnvironmentOrBuilder {
            private Object defaultValue_;
            private Object name_;

            private Builder() {
                this.name_ = "";
                this.defaultValue_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.defaultValue_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Environment_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Environment_fieldAccessorTable.ensureFieldAccessorsInitialized(Environment.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Environment.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34573clear() {
                super.clear();
                this.name_ = "";
                this.defaultValue_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Environment_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Environment m34586getDefaultInstanceForType() {
                return Environment.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Environment m34567build() throws UninitializedMessageException {
                Environment environmentM34569buildPartial = m34569buildPartial();
                if (environmentM34569buildPartial.isInitialized()) {
                    return environmentM34569buildPartial;
                }
                throw newUninitializedMessageException(environmentM34569buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Environment m34569buildPartial() {
                Environment environment = new Environment(this);
                environment.name_ = this.name_;
                environment.defaultValue_ = this.defaultValue_;
                onBuilt();
                return environment;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34585clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34597setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34575clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34578clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34599setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34565addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34590mergeFrom(Message message) {
                if (message instanceof Environment) {
                    return mergeFrom((Environment) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Environment environment) {
                if (environment == Environment.getDefaultInstance()) {
                    return this;
                }
                if (!environment.getName().isEmpty()) {
                    this.name_ = environment.name_;
                    onChanged();
                }
                if (!environment.getDefaultValue().isEmpty()) {
                    this.defaultValue_ = environment.defaultValue_;
                    onChanged();
                }
                m34595mergeUnknownFields(environment.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Environment.Builder m34591mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Environment.access$1600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag$Environment r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Environment) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag$Environment r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Environment) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Environment.Builder.m34591mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag$Environment$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.EnvironmentOrBuilder
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

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.EnvironmentOrBuilder
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
                Environment.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = Environment.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.EnvironmentOrBuilder
            public String getDefaultValue() {
                Object obj = this.defaultValue_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.defaultValue_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setDefaultValue(String str) {
                str.getClass();
                this.defaultValue_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.EnvironmentOrBuilder
            public ByteString getDefaultValueBytes() {
                Object obj = this.defaultValue_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.defaultValue_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setDefaultValueBytes(ByteString byteString) {
                byteString.getClass();
                Environment.checkByteStringIsUtf8(byteString);
                this.defaultValue_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearDefaultValue() {
                this.defaultValue_ = Environment.getDefaultInstance().getDefaultValue();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34601setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34595mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Header extends GeneratedMessageV3 implements HeaderOrBuilder {
        public static final int DEFAULT_VALUE_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final Header DEFAULT_INSTANCE = new Header();
        private static final Parser<Header> PARSER = new AbstractParser<Header>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Header.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Header m34610parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Header(codedInputStream, extensionRegistryLite);
            }
        };
        private volatile Object defaultValue_;
        private byte memoizedIsInitialized;
        private volatile Object name_;

        private Header(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Header() {
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
            this.defaultValue_ = "";
        }

        private Header(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                } else if (tag == 18) {
                                    this.defaultValue_ = codedInputStream.readStringRequireUtf8();
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

        public static Header getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Header> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Header_descriptor;
        }

        public static Header parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Header) PARSER.parseFrom(byteBuffer);
        }

        public static Header parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Header) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Header parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Header) PARSER.parseFrom(byteString);
        }

        public static Header parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Header) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Header parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Header) PARSER.parseFrom(bArr);
        }

        public static Header parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Header) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Header parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Header parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Header parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Header parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Header parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Header parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m34608toBuilder();
        }

        public static Builder newBuilder(Header header) {
            return DEFAULT_INSTANCE.m34608toBuilder().mergeFrom(header);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Header m34603getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Header> getParserForType() {
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
            return new Header();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Header_fieldAccessorTable.ensureFieldAccessorsInitialized(Header.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.HeaderOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.HeaderOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.HeaderOrBuilder
        public String getDefaultValue() {
            Object obj = this.defaultValue_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.defaultValue_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.HeaderOrBuilder
        public ByteString getDefaultValueBytes() {
            Object obj = this.defaultValue_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.defaultValue_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
            }
            if (!getDefaultValueBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.defaultValue_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
            if (!getDefaultValueBytes().isEmpty()) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.defaultValue_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Header)) {
                return super.equals(obj);
            }
            Header header = (Header) obj;
            return getName().equals(header.getName()) && getDefaultValue().equals(header.getDefaultValue()) && this.unknownFields.equals(header.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getDefaultValue().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34605newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34608toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HeaderOrBuilder {
            private Object defaultValue_;
            private Object name_;

            private Builder() {
                this.name_ = "";
                this.defaultValue_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.defaultValue_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Header_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Header_fieldAccessorTable.ensureFieldAccessorsInitialized(Header.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Header.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34619clear() {
                super.clear();
                this.name_ = "";
                this.defaultValue_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Header_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Header m34632getDefaultInstanceForType() {
                return Header.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Header m34613build() throws UninitializedMessageException {
                Header headerM34615buildPartial = m34615buildPartial();
                if (headerM34615buildPartial.isInitialized()) {
                    return headerM34615buildPartial;
                }
                throw newUninitializedMessageException(headerM34615buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Header m34615buildPartial() {
                Header header = new Header(this);
                header.name_ = this.name_;
                header.defaultValue_ = this.defaultValue_;
                onBuilt();
                return header;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34631clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34643setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34621clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34624clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34645setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34611addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34636mergeFrom(Message message) {
                if (message instanceof Header) {
                    return mergeFrom((Header) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Header header) {
                if (header == Header.getDefaultInstance()) {
                    return this;
                }
                if (!header.getName().isEmpty()) {
                    this.name_ = header.name_;
                    onChanged();
                }
                if (!header.getDefaultValue().isEmpty()) {
                    this.defaultValue_ = header.defaultValue_;
                    onChanged();
                }
                m34641mergeUnknownFields(header.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Header.Builder m34637mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Header.access$2700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag$Header r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Header) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag$Header r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Header) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Header.Builder.m34637mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag$Header$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.HeaderOrBuilder
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

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.HeaderOrBuilder
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
                Header.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = Header.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.HeaderOrBuilder
            public String getDefaultValue() {
                Object obj = this.defaultValue_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.defaultValue_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setDefaultValue(String str) {
                str.getClass();
                this.defaultValue_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.HeaderOrBuilder
            public ByteString getDefaultValueBytes() {
                Object obj = this.defaultValue_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.defaultValue_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setDefaultValueBytes(ByteString byteString) {
                byteString.getClass();
                Header.checkByteStringIsUtf8(byteString);
                this.defaultValue_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearDefaultValue() {
                this.defaultValue_ = Header.getDefaultInstance().getDefaultValue();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34647setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34641mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Metadata extends GeneratedMessageV3 implements MetadataOrBuilder {
        public static final int DEFAULT_VALUE_FIELD_NUMBER = 3;
        public static final int KIND_FIELD_NUMBER = 1;
        public static final int METADATA_KEY_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private static final Metadata DEFAULT_INSTANCE = new Metadata();
        private static final Parser<Metadata> PARSER = new AbstractParser<Metadata>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Metadata.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Metadata m34702parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Metadata(codedInputStream, extensionRegistryLite);
            }
        };
        private volatile Object defaultValue_;
        private MetadataKind kind_;
        private byte memoizedIsInitialized;
        private MetadataKey metadataKey_;

        private Metadata(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Metadata() {
            this.memoizedIsInitialized = (byte) -1;
            this.defaultValue_ = "";
        }

        private Metadata(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    MetadataKind metadataKind = this.kind_;
                                    MetadataKind.Builder builderM33964toBuilder = metadataKind != null ? metadataKind.m33964toBuilder() : null;
                                    MetadataKind metadataKind2 = (MetadataKind) codedInputStream.readMessage(MetadataKind.parser(), extensionRegistryLite);
                                    this.kind_ = metadataKind2;
                                    if (builderM33964toBuilder != null) {
                                        builderM33964toBuilder.mergeFrom(metadataKind2);
                                        this.kind_ = builderM33964toBuilder.m33971buildPartial();
                                    }
                                } else if (tag == 18) {
                                    MetadataKey metadataKey = this.metadataKey_;
                                    MetadataKey.Builder builderM33872toBuilder = metadataKey != null ? metadataKey.m33872toBuilder() : null;
                                    MetadataKey metadataKey2 = (MetadataKey) codedInputStream.readMessage(MetadataKey.parser(), extensionRegistryLite);
                                    this.metadataKey_ = metadataKey2;
                                    if (builderM33872toBuilder != null) {
                                        builderM33872toBuilder.mergeFrom(metadataKey2);
                                        this.metadataKey_ = builderM33872toBuilder.m33879buildPartial();
                                    }
                                } else if (tag == 26) {
                                    this.defaultValue_ = codedInputStream.readStringRequireUtf8();
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

        public static Metadata getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Metadata> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Metadata_descriptor;
        }

        public static Metadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Metadata) PARSER.parseFrom(byteBuffer);
        }

        public static Metadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Metadata) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Metadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Metadata) PARSER.parseFrom(byteString);
        }

        public static Metadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Metadata) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Metadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Metadata) PARSER.parseFrom(bArr);
        }

        public static Metadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Metadata) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Metadata parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Metadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Metadata parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Metadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Metadata parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Metadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m34700toBuilder();
        }

        public static Builder newBuilder(Metadata metadata) {
            return DEFAULT_INSTANCE.m34700toBuilder().mergeFrom(metadata);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Metadata m34695getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Metadata> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
        public boolean hasKind() {
            return this.kind_ != null;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
        public boolean hasMetadataKey() {
            return this.metadataKey_ != null;
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
            return new Metadata();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Metadata_fieldAccessorTable.ensureFieldAccessorsInitialized(Metadata.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
        public MetadataKind getKind() {
            MetadataKind metadataKind = this.kind_;
            return metadataKind == null ? MetadataKind.getDefaultInstance() : metadataKind;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
        public MetadataKindOrBuilder getKindOrBuilder() {
            return getKind();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
        public MetadataKey getMetadataKey() {
            MetadataKey metadataKey = this.metadataKey_;
            return metadataKey == null ? MetadataKey.getDefaultInstance() : metadataKey;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
        public MetadataKeyOrBuilder getMetadataKeyOrBuilder() {
            return getMetadataKey();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
        public String getDefaultValue() {
            Object obj = this.defaultValue_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.defaultValue_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
        public ByteString getDefaultValueBytes() {
            Object obj = this.defaultValue_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.defaultValue_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.kind_ != null) {
                codedOutputStream.writeMessage(1, getKind());
            }
            if (this.metadataKey_ != null) {
                codedOutputStream.writeMessage(2, getMetadataKey());
            }
            if (!getDefaultValueBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.defaultValue_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = this.kind_ != null ? CodedOutputStream.computeMessageSize(1, getKind()) : 0;
            if (this.metadataKey_ != null) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getMetadataKey());
            }
            if (!getDefaultValueBytes().isEmpty()) {
                iComputeMessageSize += GeneratedMessageV3.computeStringSize(3, this.defaultValue_);
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Metadata)) {
                return super.equals(obj);
            }
            Metadata metadata = (Metadata) obj;
            if (hasKind() != metadata.hasKind()) {
                return false;
            }
            if ((!hasKind() || getKind().equals(metadata.getKind())) && hasMetadataKey() == metadata.hasMetadataKey()) {
                return (!hasMetadataKey() || getMetadataKey().equals(metadata.getMetadataKey())) && getDefaultValue().equals(metadata.getDefaultValue()) && this.unknownFields.equals(metadata.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasKind()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getKind().hashCode();
            }
            if (hasMetadataKey()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getMetadataKey().hashCode();
            }
            int iHashCode2 = (((((iHashCode * 37) + 3) * 53) + getDefaultValue().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34697newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34700toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetadataOrBuilder {
            private Object defaultValue_;
            private SingleFieldBuilderV3<MetadataKind, MetadataKind.Builder, MetadataKindOrBuilder> kindBuilder_;
            private MetadataKind kind_;
            private SingleFieldBuilderV3<MetadataKey, MetadataKey.Builder, MetadataKeyOrBuilder> metadataKeyBuilder_;
            private MetadataKey metadataKey_;

            private Builder() {
                this.defaultValue_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.defaultValue_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Metadata_descriptor;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
            public boolean hasKind() {
                return (this.kindBuilder_ == null && this.kind_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
            public boolean hasMetadataKey() {
                return (this.metadataKeyBuilder_ == null && this.metadataKey_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Metadata_fieldAccessorTable.ensureFieldAccessorsInitialized(Metadata.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Metadata.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34711clear() {
                super.clear();
                if (this.kindBuilder_ == null) {
                    this.kind_ = null;
                } else {
                    this.kind_ = null;
                    this.kindBuilder_ = null;
                }
                if (this.metadataKeyBuilder_ == null) {
                    this.metadataKey_ = null;
                } else {
                    this.metadataKey_ = null;
                    this.metadataKeyBuilder_ = null;
                }
                this.defaultValue_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_Metadata_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Metadata m34724getDefaultInstanceForType() {
                return Metadata.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Metadata m34705build() throws UninitializedMessageException {
                Metadata metadataM34707buildPartial = m34707buildPartial();
                if (metadataM34707buildPartial.isInitialized()) {
                    return metadataM34707buildPartial;
                }
                throw newUninitializedMessageException(metadataM34707buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Metadata m34707buildPartial() {
                Metadata metadata = new Metadata(this);
                SingleFieldBuilderV3<MetadataKind, MetadataKind.Builder, MetadataKindOrBuilder> singleFieldBuilderV3 = this.kindBuilder_;
                if (singleFieldBuilderV3 == null) {
                    metadata.kind_ = this.kind_;
                } else {
                    metadata.kind_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<MetadataKey, MetadataKey.Builder, MetadataKeyOrBuilder> singleFieldBuilderV32 = this.metadataKeyBuilder_;
                if (singleFieldBuilderV32 == null) {
                    metadata.metadataKey_ = this.metadataKey_;
                } else {
                    metadata.metadataKey_ = singleFieldBuilderV32.build();
                }
                metadata.defaultValue_ = this.defaultValue_;
                onBuilt();
                return metadata;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34723clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34735setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34713clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34716clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34737setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34703addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m34728mergeFrom(Message message) {
                if (message instanceof Metadata) {
                    return mergeFrom((Metadata) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Metadata metadata) {
                if (metadata == Metadata.getDefaultInstance()) {
                    return this;
                }
                if (metadata.hasKind()) {
                    mergeKind(metadata.getKind());
                }
                if (metadata.hasMetadataKey()) {
                    mergeMetadataKey(metadata.getMetadataKey());
                }
                if (!metadata.getDefaultValue().isEmpty()) {
                    this.defaultValue_ = metadata.defaultValue_;
                    onChanged();
                }
                m34733mergeUnknownFields(metadata.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Metadata.Builder m34729mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Metadata.access$3900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag$Metadata r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Metadata) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag$Metadata r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Metadata) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Metadata.Builder.m34729mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag$Metadata$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
            public MetadataKind getKind() {
                SingleFieldBuilderV3<MetadataKind, MetadataKind.Builder, MetadataKindOrBuilder> singleFieldBuilderV3 = this.kindBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                MetadataKind metadataKind = this.kind_;
                return metadataKind == null ? MetadataKind.getDefaultInstance() : metadataKind;
            }

            public Builder setKind(MetadataKind metadataKind) {
                SingleFieldBuilderV3<MetadataKind, MetadataKind.Builder, MetadataKindOrBuilder> singleFieldBuilderV3 = this.kindBuilder_;
                if (singleFieldBuilderV3 == null) {
                    metadataKind.getClass();
                    this.kind_ = metadataKind;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(metadataKind);
                }
                return this;
            }

            public Builder setKind(MetadataKind.Builder builder) {
                SingleFieldBuilderV3<MetadataKind, MetadataKind.Builder, MetadataKindOrBuilder> singleFieldBuilderV3 = this.kindBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.kind_ = builder.m33969build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m33969build());
                }
                return this;
            }

            public Builder mergeKind(MetadataKind metadataKind) {
                SingleFieldBuilderV3<MetadataKind, MetadataKind.Builder, MetadataKindOrBuilder> singleFieldBuilderV3 = this.kindBuilder_;
                if (singleFieldBuilderV3 == null) {
                    MetadataKind metadataKind2 = this.kind_;
                    if (metadataKind2 != null) {
                        this.kind_ = MetadataKind.newBuilder(metadataKind2).mergeFrom(metadataKind).m33971buildPartial();
                    } else {
                        this.kind_ = metadataKind;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(metadataKind);
                }
                return this;
            }

            public Builder clearKind() {
                if (this.kindBuilder_ == null) {
                    this.kind_ = null;
                    onChanged();
                } else {
                    this.kind_ = null;
                    this.kindBuilder_ = null;
                }
                return this;
            }

            public MetadataKind.Builder getKindBuilder() {
                onChanged();
                return getKindFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
            public MetadataKindOrBuilder getKindOrBuilder() {
                SingleFieldBuilderV3<MetadataKind, MetadataKind.Builder, MetadataKindOrBuilder> singleFieldBuilderV3 = this.kindBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (MetadataKindOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                MetadataKind metadataKind = this.kind_;
                return metadataKind == null ? MetadataKind.getDefaultInstance() : metadataKind;
            }

            private SingleFieldBuilderV3<MetadataKind, MetadataKind.Builder, MetadataKindOrBuilder> getKindFieldBuilder() {
                if (this.kindBuilder_ == null) {
                    this.kindBuilder_ = new SingleFieldBuilderV3<>(getKind(), getParentForChildren(), isClean());
                    this.kind_ = null;
                }
                return this.kindBuilder_;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
            public MetadataKey getMetadataKey() {
                SingleFieldBuilderV3<MetadataKey, MetadataKey.Builder, MetadataKeyOrBuilder> singleFieldBuilderV3 = this.metadataKeyBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                MetadataKey metadataKey = this.metadataKey_;
                return metadataKey == null ? MetadataKey.getDefaultInstance() : metadataKey;
            }

            public Builder setMetadataKey(MetadataKey metadataKey) {
                SingleFieldBuilderV3<MetadataKey, MetadataKey.Builder, MetadataKeyOrBuilder> singleFieldBuilderV3 = this.metadataKeyBuilder_;
                if (singleFieldBuilderV3 == null) {
                    metadataKey.getClass();
                    this.metadataKey_ = metadataKey;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(metadataKey);
                }
                return this;
            }

            public Builder setMetadataKey(MetadataKey.Builder builder) {
                SingleFieldBuilderV3<MetadataKey, MetadataKey.Builder, MetadataKeyOrBuilder> singleFieldBuilderV3 = this.metadataKeyBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.metadataKey_ = builder.m33877build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m33877build());
                }
                return this;
            }

            public Builder mergeMetadataKey(MetadataKey metadataKey) {
                SingleFieldBuilderV3<MetadataKey, MetadataKey.Builder, MetadataKeyOrBuilder> singleFieldBuilderV3 = this.metadataKeyBuilder_;
                if (singleFieldBuilderV3 == null) {
                    MetadataKey metadataKey2 = this.metadataKey_;
                    if (metadataKey2 != null) {
                        this.metadataKey_ = MetadataKey.newBuilder(metadataKey2).mergeFrom(metadataKey).m33879buildPartial();
                    } else {
                        this.metadataKey_ = metadataKey;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(metadataKey);
                }
                return this;
            }

            public Builder clearMetadataKey() {
                if (this.metadataKeyBuilder_ == null) {
                    this.metadataKey_ = null;
                    onChanged();
                } else {
                    this.metadataKey_ = null;
                    this.metadataKeyBuilder_ = null;
                }
                return this;
            }

            public MetadataKey.Builder getMetadataKeyBuilder() {
                onChanged();
                return getMetadataKeyFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
            public MetadataKeyOrBuilder getMetadataKeyOrBuilder() {
                SingleFieldBuilderV3<MetadataKey, MetadataKey.Builder, MetadataKeyOrBuilder> singleFieldBuilderV3 = this.metadataKeyBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (MetadataKeyOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                MetadataKey metadataKey = this.metadataKey_;
                return metadataKey == null ? MetadataKey.getDefaultInstance() : metadataKey;
            }

            private SingleFieldBuilderV3<MetadataKey, MetadataKey.Builder, MetadataKeyOrBuilder> getMetadataKeyFieldBuilder() {
                if (this.metadataKeyBuilder_ == null) {
                    this.metadataKeyBuilder_ = new SingleFieldBuilderV3<>(getMetadataKey(), getParentForChildren(), isClean());
                    this.metadataKey_ = null;
                }
                return this.metadataKeyBuilder_;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
            public String getDefaultValue() {
                Object obj = this.defaultValue_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.defaultValue_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setDefaultValue(String str) {
                str.getClass();
                this.defaultValue_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.MetadataOrBuilder
            public ByteString getDefaultValueBytes() {
                Object obj = this.defaultValue_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.defaultValue_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setDefaultValueBytes(ByteString byteString) {
                byteString.getClass();
                Metadata.checkByteStringIsUtf8(byteString);
                this.defaultValue_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearDefaultValue() {
                this.defaultValue_ = Metadata.getDefaultInstance().getDefaultValue();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34739setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m34733mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CustomTagOrBuilder {
        private SingleFieldBuilderV3<Environment, Environment.Builder, EnvironmentOrBuilder> environmentBuilder_;
        private SingleFieldBuilderV3<Literal, Literal.Builder, LiteralOrBuilder> literalBuilder_;
        private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> metadataBuilder_;
        private SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> requestHeaderBuilder_;
        private Object tag_;
        private int typeCase_;
        private Object type_;

        private Builder() {
            this.typeCase_ = 0;
            this.tag_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.typeCase_ = 0;
            this.tag_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public boolean hasEnvironment() {
            return this.typeCase_ == 3;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public boolean hasLiteral() {
            return this.typeCase_ == 2;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public boolean hasMetadata() {
            return this.typeCase_ == 5;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public boolean hasRequestHeader() {
            return this.typeCase_ == 4;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_fieldAccessorTable.ensureFieldAccessorsInitialized(CustomTag.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = CustomTag.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34527clear() {
            super.clear();
            this.tag_ = "";
            this.typeCase_ = 0;
            this.type_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return CustomTagProto.internal_static_envoy_type_tracing_v2_CustomTag_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CustomTag m34540getDefaultInstanceForType() {
            return CustomTag.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CustomTag m34521build() throws UninitializedMessageException {
            CustomTag customTagM34523buildPartial = m34523buildPartial();
            if (customTagM34523buildPartial.isInitialized()) {
                return customTagM34523buildPartial;
            }
            throw newUninitializedMessageException(customTagM34523buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CustomTag m34523buildPartial() {
            CustomTag customTag = new CustomTag(this);
            customTag.tag_ = this.tag_;
            if (this.typeCase_ == 2) {
                SingleFieldBuilderV3<Literal, Literal.Builder, LiteralOrBuilder> singleFieldBuilderV3 = this.literalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    customTag.type_ = this.type_;
                } else {
                    customTag.type_ = singleFieldBuilderV3.build();
                }
            }
            if (this.typeCase_ == 3) {
                SingleFieldBuilderV3<Environment, Environment.Builder, EnvironmentOrBuilder> singleFieldBuilderV32 = this.environmentBuilder_;
                if (singleFieldBuilderV32 == null) {
                    customTag.type_ = this.type_;
                } else {
                    customTag.type_ = singleFieldBuilderV32.build();
                }
            }
            if (this.typeCase_ == 4) {
                SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> singleFieldBuilderV33 = this.requestHeaderBuilder_;
                if (singleFieldBuilderV33 == null) {
                    customTag.type_ = this.type_;
                } else {
                    customTag.type_ = singleFieldBuilderV33.build();
                }
            }
            if (this.typeCase_ == 5) {
                SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV34 = this.metadataBuilder_;
                if (singleFieldBuilderV34 == null) {
                    customTag.type_ = this.type_;
                } else {
                    customTag.type_ = singleFieldBuilderV34.build();
                }
            }
            customTag.typeCase_ = this.typeCase_;
            onBuilt();
            return customTag;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34539clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34551setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34529clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34532clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34553setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34519addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m34544mergeFrom(Message message) {
            if (message instanceof CustomTag) {
                return mergeFrom((CustomTag) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(CustomTag customTag) {
            if (customTag == CustomTag.getDefaultInstance()) {
                return this;
            }
            if (!customTag.getTag().isEmpty()) {
                this.tag_ = customTag.tag_;
                onChanged();
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$type$tracing$v2$CustomTag$TypeCase[customTag.getTypeCase().ordinal()];
            if (i == 1) {
                mergeLiteral(customTag.getLiteral());
            } else if (i == 2) {
                mergeEnvironment(customTag.getEnvironment());
            } else if (i == 3) {
                mergeRequestHeader(customTag.getRequestHeader());
            } else if (i == 4) {
                mergeMetadata(customTag.getMetadata());
            }
            m34549mergeUnknownFields(customTag.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Builder m34545mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.access$5000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag.Builder.m34545mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public TypeCase getTypeCase() {
            return TypeCase.forNumber(this.typeCase_);
        }

        public Builder clearType() {
            this.typeCase_ = 0;
            this.type_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public String getTag() {
            Object obj = this.tag_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.tag_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setTag(String str) {
            str.getClass();
            this.tag_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public ByteString getTagBytes() {
            Object obj = this.tag_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.tag_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setTagBytes(ByteString byteString) {
            byteString.getClass();
            CustomTag.checkByteStringIsUtf8(byteString);
            this.tag_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearTag() {
            this.tag_ = CustomTag.getDefaultInstance().getTag();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public Literal getLiteral() {
            SingleFieldBuilderV3<Literal, Literal.Builder, LiteralOrBuilder> singleFieldBuilderV3 = this.literalBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ == 2) {
                    return (Literal) this.type_;
                }
                return Literal.getDefaultInstance();
            }
            if (this.typeCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return Literal.getDefaultInstance();
        }

        public Builder setLiteral(Literal literal) {
            SingleFieldBuilderV3<Literal, Literal.Builder, LiteralOrBuilder> singleFieldBuilderV3 = this.literalBuilder_;
            if (singleFieldBuilderV3 == null) {
                literal.getClass();
                this.type_ = literal;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(literal);
            }
            this.typeCase_ = 2;
            return this;
        }

        public Builder setLiteral(Literal.Builder builder) {
            SingleFieldBuilderV3<Literal, Literal.Builder, LiteralOrBuilder> singleFieldBuilderV3 = this.literalBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.type_ = builder.m34659build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m34659build());
            }
            this.typeCase_ = 2;
            return this;
        }

        public Builder mergeLiteral(Literal literal) {
            SingleFieldBuilderV3<Literal, Literal.Builder, LiteralOrBuilder> singleFieldBuilderV3 = this.literalBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ != 2 || this.type_ == Literal.getDefaultInstance()) {
                    this.type_ = literal;
                } else {
                    this.type_ = Literal.newBuilder((Literal) this.type_).mergeFrom(literal).m34661buildPartial();
                }
                onChanged();
            } else {
                if (this.typeCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(literal);
                }
                this.literalBuilder_.setMessage(literal);
            }
            this.typeCase_ = 2;
            return this;
        }

        public Builder clearLiteral() {
            SingleFieldBuilderV3<Literal, Literal.Builder, LiteralOrBuilder> singleFieldBuilderV3 = this.literalBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.typeCase_ == 2) {
                    this.typeCase_ = 0;
                    this.type_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.typeCase_ == 2) {
                this.typeCase_ = 0;
                this.type_ = null;
                onChanged();
            }
            return this;
        }

        public Literal.Builder getLiteralBuilder() {
            return getLiteralFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public LiteralOrBuilder getLiteralOrBuilder() {
            SingleFieldBuilderV3<Literal, Literal.Builder, LiteralOrBuilder> singleFieldBuilderV3;
            int i = this.typeCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.literalBuilder_) != null) {
                return (LiteralOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (Literal) this.type_;
            }
            return Literal.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Literal, Literal.Builder, LiteralOrBuilder> getLiteralFieldBuilder() {
            if (this.literalBuilder_ == null) {
                if (this.typeCase_ != 2) {
                    this.type_ = Literal.getDefaultInstance();
                }
                this.literalBuilder_ = new SingleFieldBuilderV3<>((Literal) this.type_, getParentForChildren(), isClean());
                this.type_ = null;
            }
            this.typeCase_ = 2;
            onChanged();
            return this.literalBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public Environment getEnvironment() {
            SingleFieldBuilderV3<Environment, Environment.Builder, EnvironmentOrBuilder> singleFieldBuilderV3 = this.environmentBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ == 3) {
                    return (Environment) this.type_;
                }
                return Environment.getDefaultInstance();
            }
            if (this.typeCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return Environment.getDefaultInstance();
        }

        public Builder setEnvironment(Environment environment) {
            SingleFieldBuilderV3<Environment, Environment.Builder, EnvironmentOrBuilder> singleFieldBuilderV3 = this.environmentBuilder_;
            if (singleFieldBuilderV3 == null) {
                environment.getClass();
                this.type_ = environment;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(environment);
            }
            this.typeCase_ = 3;
            return this;
        }

        public Builder setEnvironment(Environment.Builder builder) {
            SingleFieldBuilderV3<Environment, Environment.Builder, EnvironmentOrBuilder> singleFieldBuilderV3 = this.environmentBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.type_ = builder.m34567build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m34567build());
            }
            this.typeCase_ = 3;
            return this;
        }

        public Builder mergeEnvironment(Environment environment) {
            SingleFieldBuilderV3<Environment, Environment.Builder, EnvironmentOrBuilder> singleFieldBuilderV3 = this.environmentBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ != 3 || this.type_ == Environment.getDefaultInstance()) {
                    this.type_ = environment;
                } else {
                    this.type_ = Environment.newBuilder((Environment) this.type_).mergeFrom(environment).m34569buildPartial();
                }
                onChanged();
            } else {
                if (this.typeCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(environment);
                }
                this.environmentBuilder_.setMessage(environment);
            }
            this.typeCase_ = 3;
            return this;
        }

        public Builder clearEnvironment() {
            SingleFieldBuilderV3<Environment, Environment.Builder, EnvironmentOrBuilder> singleFieldBuilderV3 = this.environmentBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.typeCase_ == 3) {
                    this.typeCase_ = 0;
                    this.type_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.typeCase_ == 3) {
                this.typeCase_ = 0;
                this.type_ = null;
                onChanged();
            }
            return this;
        }

        public Environment.Builder getEnvironmentBuilder() {
            return getEnvironmentFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public EnvironmentOrBuilder getEnvironmentOrBuilder() {
            SingleFieldBuilderV3<Environment, Environment.Builder, EnvironmentOrBuilder> singleFieldBuilderV3;
            int i = this.typeCase_;
            if (i == 3 && (singleFieldBuilderV3 = this.environmentBuilder_) != null) {
                return (EnvironmentOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 3) {
                return (Environment) this.type_;
            }
            return Environment.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Environment, Environment.Builder, EnvironmentOrBuilder> getEnvironmentFieldBuilder() {
            if (this.environmentBuilder_ == null) {
                if (this.typeCase_ != 3) {
                    this.type_ = Environment.getDefaultInstance();
                }
                this.environmentBuilder_ = new SingleFieldBuilderV3<>((Environment) this.type_, getParentForChildren(), isClean());
                this.type_ = null;
            }
            this.typeCase_ = 3;
            onChanged();
            return this.environmentBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public Header getRequestHeader() {
            SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> singleFieldBuilderV3 = this.requestHeaderBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ == 4) {
                    return (Header) this.type_;
                }
                return Header.getDefaultInstance();
            }
            if (this.typeCase_ == 4) {
                return singleFieldBuilderV3.getMessage();
            }
            return Header.getDefaultInstance();
        }

        public Builder setRequestHeader(Header header) {
            SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> singleFieldBuilderV3 = this.requestHeaderBuilder_;
            if (singleFieldBuilderV3 == null) {
                header.getClass();
                this.type_ = header;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(header);
            }
            this.typeCase_ = 4;
            return this;
        }

        public Builder setRequestHeader(Header.Builder builder) {
            SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> singleFieldBuilderV3 = this.requestHeaderBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.type_ = builder.m34613build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m34613build());
            }
            this.typeCase_ = 4;
            return this;
        }

        public Builder mergeRequestHeader(Header header) {
            SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> singleFieldBuilderV3 = this.requestHeaderBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ != 4 || this.type_ == Header.getDefaultInstance()) {
                    this.type_ = header;
                } else {
                    this.type_ = Header.newBuilder((Header) this.type_).mergeFrom(header).m34615buildPartial();
                }
                onChanged();
            } else {
                if (this.typeCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(header);
                }
                this.requestHeaderBuilder_.setMessage(header);
            }
            this.typeCase_ = 4;
            return this;
        }

        public Builder clearRequestHeader() {
            SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> singleFieldBuilderV3 = this.requestHeaderBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.typeCase_ == 4) {
                    this.typeCase_ = 0;
                    this.type_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.typeCase_ == 4) {
                this.typeCase_ = 0;
                this.type_ = null;
                onChanged();
            }
            return this;
        }

        public Header.Builder getRequestHeaderBuilder() {
            return getRequestHeaderFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public HeaderOrBuilder getRequestHeaderOrBuilder() {
            SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> singleFieldBuilderV3;
            int i = this.typeCase_;
            if (i == 4 && (singleFieldBuilderV3 = this.requestHeaderBuilder_) != null) {
                return (HeaderOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 4) {
                return (Header) this.type_;
            }
            return Header.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> getRequestHeaderFieldBuilder() {
            if (this.requestHeaderBuilder_ == null) {
                if (this.typeCase_ != 4) {
                    this.type_ = Header.getDefaultInstance();
                }
                this.requestHeaderBuilder_ = new SingleFieldBuilderV3<>((Header) this.type_, getParentForChildren(), isClean());
                this.type_ = null;
            }
            this.typeCase_ = 4;
            onChanged();
            return this.requestHeaderBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public Metadata getMetadata() {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ == 5) {
                    return (Metadata) this.type_;
                }
                return Metadata.getDefaultInstance();
            }
            if (this.typeCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return Metadata.getDefaultInstance();
        }

        public Builder setMetadata(Metadata metadata) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                metadata.getClass();
                this.type_ = metadata;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(metadata);
            }
            this.typeCase_ = 5;
            return this;
        }

        public Builder setMetadata(Metadata.Builder builder) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.type_ = builder.m34705build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m34705build());
            }
            this.typeCase_ = 5;
            return this;
        }

        public Builder mergeMetadata(Metadata metadata) {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.typeCase_ != 5 || this.type_ == Metadata.getDefaultInstance()) {
                    this.type_ = metadata;
                } else {
                    this.type_ = Metadata.newBuilder((Metadata) this.type_).mergeFrom(metadata).m34707buildPartial();
                }
                onChanged();
            } else {
                if (this.typeCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(metadata);
                }
                this.metadataBuilder_.setMessage(metadata);
            }
            this.typeCase_ = 5;
            return this;
        }

        public Builder clearMetadata() {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.typeCase_ == 5) {
                    this.typeCase_ = 0;
                    this.type_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.typeCase_ == 5) {
                this.typeCase_ = 0;
                this.type_ = null;
                onChanged();
            }
            return this;
        }

        public Metadata.Builder getMetadataBuilder() {
            return getMetadataFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTagOrBuilder
        public MetadataOrBuilder getMetadataOrBuilder() {
            SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3;
            int i = this.typeCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.metadataBuilder_) != null) {
                return (MetadataOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (Metadata) this.type_;
            }
            return Metadata.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> getMetadataFieldBuilder() {
            if (this.metadataBuilder_ == null) {
                if (this.typeCase_ != 5) {
                    this.type_ = Metadata.getDefaultInstance();
                }
                this.metadataBuilder_ = new SingleFieldBuilderV3<>((Metadata) this.type_, getParentForChildren(), isClean());
                this.type_ = null;
            }
            this.typeCase_ = 5;
            onChanged();
            return this.metadataBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m34555setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m34549mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$type$tracing$v2$CustomTag$TypeCase;

        static {
            int[] iArr = new int[TypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$type$tracing$v2$CustomTag$TypeCase = iArr;
            try {
                iArr[TypeCase.LITERAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$tracing$v2$CustomTag$TypeCase[TypeCase.ENVIRONMENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$tracing$v2$CustomTag$TypeCase[TypeCase.REQUEST_HEADER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$tracing$v2$CustomTag$TypeCase[TypeCase.METADATA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$tracing$v2$CustomTag$TypeCase[TypeCase.TYPE_NOT_SET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
