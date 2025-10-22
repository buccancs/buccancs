package io.grpc.xds.shaded.com.github.udpa.udpa.core.v1;

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
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ContextParams;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class ResourceLocator extends GeneratedMessageV3 implements ResourceLocatorOrBuilder {
    public static final int AUTHORITY_FIELD_NUMBER = 3;
    public static final int DIRECTIVES_FIELD_NUMBER = 6;
    public static final int EXACT_CONTEXT_FIELD_NUMBER = 5;
    public static final int ID_FIELD_NUMBER = 2;
    public static final int RESOURCE_TYPE_FIELD_NUMBER = 4;
    public static final int SCHEME_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final ResourceLocator DEFAULT_INSTANCE = new ResourceLocator();
    private static final Parser<ResourceLocator> PARSER = new AbstractParser<ResourceLocator>() { // from class: io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ResourceLocator m10223parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ResourceLocator(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object authority_;
    private int contextParamSpecifierCase_;
    private Object contextParamSpecifier_;
    private List<Directive> directives_;
    private LazyStringList id_;
    private byte memoizedIsInitialized;
    private volatile Object resourceType_;
    private int scheme_;

    private ResourceLocator(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.contextParamSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private ResourceLocator() {
        this.contextParamSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.scheme_ = 0;
        this.id_ = LazyStringArrayList.EMPTY;
        this.authority_ = "";
        this.resourceType_ = "";
        this.directives_ = Collections.emptyList();
    }

    private ResourceLocator(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (tag == 8) {
                            this.scheme_ = codedInputStream.readEnum();
                        } else if (tag == 18) {
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if ((i & 1) == 0) {
                                this.id_ = new LazyStringArrayList();
                                i |= 1;
                            }
                            this.id_.add(stringRequireUtf8);
                        } else if (tag == 26) {
                            this.authority_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 34) {
                            this.resourceType_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 42) {
                            ContextParams.Builder builderM10174toBuilder = this.contextParamSpecifierCase_ == 5 ? ((ContextParams) this.contextParamSpecifier_).m10174toBuilder() : null;
                            MessageLite message = codedInputStream.readMessage(ContextParams.parser(), extensionRegistryLite);
                            this.contextParamSpecifier_ = message;
                            if (builderM10174toBuilder != null) {
                                builderM10174toBuilder.mergeFrom((ContextParams) message);
                                this.contextParamSpecifier_ = builderM10174toBuilder.m10181buildPartial();
                            }
                            this.contextParamSpecifierCase_ = 5;
                        } else if (tag == 50) {
                            if ((i & 2) == 0) {
                                this.directives_ = new ArrayList();
                                i |= 2;
                            }
                            this.directives_.add(codedInputStream.readMessage(Directive.parser(), extensionRegistryLite));
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
                if ((i & 1) != 0) {
                    this.id_ = this.id_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.directives_ = Collections.unmodifiableList(this.directives_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ResourceLocator getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ResourceLocator> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ResourceLocatorProto.internal_static_udpa_core_v1_ResourceLocator_descriptor;
    }

    public static ResourceLocator parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ResourceLocator) PARSER.parseFrom(byteBuffer);
    }

    public static ResourceLocator parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceLocator) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ResourceLocator parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ResourceLocator) PARSER.parseFrom(byteString);
    }

    public static ResourceLocator parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceLocator) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ResourceLocator parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ResourceLocator) PARSER.parseFrom(bArr);
    }

    public static ResourceLocator parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceLocator) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ResourceLocator parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ResourceLocator parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ResourceLocator parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ResourceLocator parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ResourceLocator parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ResourceLocator parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m10221toBuilder();
    }

    public static Builder newBuilder(ResourceLocator resourceLocator) {
        return DEFAULT_INSTANCE.m10221toBuilder().mergeFrom(resourceLocator);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ResourceLocator m10215getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public List<Directive> getDirectivesList() {
        return this.directives_;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public List<? extends DirectiveOrBuilder> getDirectivesOrBuilderList() {
        return this.directives_;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    /* renamed from: getIdList, reason: merged with bridge method [inline-methods] */
    public ProtocolStringList mo10217getIdList() {
        return this.id_;
    }

    public Parser<ResourceLocator> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public int getSchemeValue() {
        return this.scheme_;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public boolean hasExactContext() {
        return this.contextParamSpecifierCase_ == 5;
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
        return new ResourceLocator();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ResourceLocatorProto.internal_static_udpa_core_v1_ResourceLocator_fieldAccessorTable.ensureFieldAccessorsInitialized(ResourceLocator.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public ContextParamSpecifierCase getContextParamSpecifierCase() {
        return ContextParamSpecifierCase.forNumber(this.contextParamSpecifierCase_);
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public Scheme getScheme() {
        Scheme schemeValueOf = Scheme.valueOf(this.scheme_);
        return schemeValueOf == null ? Scheme.UNRECOGNIZED : schemeValueOf;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public int getIdCount() {
        return this.id_.size();
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public String getId(int i) {
        return (String) this.id_.get(i);
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public ByteString getIdBytes(int i) {
        return this.id_.getByteString(i);
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public String getAuthority() {
        Object obj = this.authority_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.authority_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public ByteString getAuthorityBytes() {
        Object obj = this.authority_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.authority_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public String getResourceType() {
        Object obj = this.resourceType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.resourceType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public ByteString getResourceTypeBytes() {
        Object obj = this.resourceType_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.resourceType_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public ContextParams getExactContext() {
        if (this.contextParamSpecifierCase_ == 5) {
            return (ContextParams) this.contextParamSpecifier_;
        }
        return ContextParams.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public ContextParamsOrBuilder getExactContextOrBuilder() {
        if (this.contextParamSpecifierCase_ == 5) {
            return (ContextParams) this.contextParamSpecifier_;
        }
        return ContextParams.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public int getDirectivesCount() {
        return this.directives_.size();
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public Directive getDirectives(int i) {
        return this.directives_.get(i);
    }

    @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
    public DirectiveOrBuilder getDirectivesOrBuilder(int i) {
        return this.directives_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.scheme_ != Scheme.UDPA.getNumber()) {
            codedOutputStream.writeEnum(1, this.scheme_);
        }
        for (int i = 0; i < this.id_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.id_.getRaw(i));
        }
        if (!getAuthorityBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.authority_);
        }
        if (!getResourceTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.resourceType_);
        }
        if (this.contextParamSpecifierCase_ == 5) {
            codedOutputStream.writeMessage(5, (ContextParams) this.contextParamSpecifier_);
        }
        for (int i2 = 0; i2 < this.directives_.size(); i2++) {
            codedOutputStream.writeMessage(6, this.directives_.get(i2));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeEnumSize = this.scheme_ != Scheme.UDPA.getNumber() ? CodedOutputStream.computeEnumSize(1, this.scheme_) : 0;
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.id_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.id_.getRaw(i2));
        }
        int size = iComputeEnumSize + iComputeStringSizeNoTag + mo10217getIdList().size();
        if (!getAuthorityBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(3, this.authority_);
        }
        if (!getResourceTypeBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(4, this.resourceType_);
        }
        if (this.contextParamSpecifierCase_ == 5) {
            size += CodedOutputStream.computeMessageSize(5, (ContextParams) this.contextParamSpecifier_);
        }
        for (int i3 = 0; i3 < this.directives_.size(); i3++) {
            size += CodedOutputStream.computeMessageSize(6, this.directives_.get(i3));
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResourceLocator)) {
            return super.equals(obj);
        }
        ResourceLocator resourceLocator = (ResourceLocator) obj;
        if (this.scheme_ == resourceLocator.scheme_ && mo10217getIdList().equals(resourceLocator.mo10217getIdList()) && getAuthority().equals(resourceLocator.getAuthority()) && getResourceType().equals(resourceLocator.getResourceType()) && getDirectivesList().equals(resourceLocator.getDirectivesList()) && getContextParamSpecifierCase().equals(resourceLocator.getContextParamSpecifierCase())) {
            return (this.contextParamSpecifierCase_ != 5 || getExactContext().equals(resourceLocator.getExactContext())) && this.unknownFields.equals(resourceLocator.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.scheme_;
        if (getIdCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + mo10217getIdList().hashCode();
        }
        int iHashCode2 = (((((((iHashCode * 37) + 3) * 53) + getAuthority().hashCode()) * 37) + 4) * 53) + getResourceType().hashCode();
        if (getDirectivesCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 6) * 53) + getDirectivesList().hashCode();
        }
        if (this.contextParamSpecifierCase_ == 5) {
            iHashCode2 = (((iHashCode2 * 37) + 5) * 53) + getExactContext().hashCode();
        }
        int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode3;
        return iHashCode3;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m10218newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m10221toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum Scheme implements ProtocolMessageEnum {
        UDPA(0),
        HTTP(1),
        FILE(2),
        UNRECOGNIZED(-1);

        public static final int FILE_VALUE = 2;
        public static final int HTTP_VALUE = 1;
        public static final int UDPA_VALUE = 0;
        private static final Internal.EnumLiteMap<Scheme> internalValueMap = new Internal.EnumLiteMap<Scheme>() { // from class: io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.Scheme.1
            public Scheme findValueByNumber(int i) {
                return Scheme.forNumber(i);
            }
        };
        private static final Scheme[] VALUES = values();
        private final int value;

        Scheme(int i) {
            this.value = i;
        }

        public static Scheme forNumber(int i) {
            if (i == 0) {
                return UDPA;
            }
            if (i == 1) {
                return HTTP;
            }
            if (i != 2) {
                return null;
            }
            return FILE;
        }

        public static Internal.EnumLiteMap<Scheme> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static Scheme valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) ResourceLocator.getDescriptor().getEnumTypes().get(0);
        }

        public static Scheme valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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

    public enum ContextParamSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        EXACT_CONTEXT(5),
        CONTEXTPARAMSPECIFIER_NOT_SET(0);

        private final int value;

        ContextParamSpecifierCase(int i) {
            this.value = i;
        }

        public static ContextParamSpecifierCase forNumber(int i) {
            if (i == 0) {
                return CONTEXTPARAMSPECIFIER_NOT_SET;
            }
            if (i != 5) {
                return null;
            }
            return EXACT_CONTEXT;
        }

        @Deprecated
        public static ContextParamSpecifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public interface DirectiveOrBuilder extends MessageOrBuilder {
        ResourceLocator getAlt();

        ResourceLocatorOrBuilder getAltOrBuilder();

        Directive.DirectiveCase getDirectiveCase();

        String getEntry();

        ByteString getEntryBytes();

        boolean hasAlt();
    }

    public static final class Directive extends GeneratedMessageV3 implements DirectiveOrBuilder {
        public static final int ALT_FIELD_NUMBER = 1;
        public static final int ENTRY_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private static final Directive DEFAULT_INSTANCE = new Directive();
        private static final Parser<Directive> PARSER = new AbstractParser<Directive>() { // from class: io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.Directive.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Directive m10269parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Directive(codedInputStream, extensionRegistryLite);
            }
        };
        private int directiveCase_;
        private Object directive_;
        private byte memoizedIsInitialized;

        private Directive(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.directiveCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private Directive() {
            this.directiveCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private Directive(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                Builder builderM10221toBuilder = this.directiveCase_ == 1 ? ((ResourceLocator) this.directive_).m10221toBuilder() : null;
                                MessageLite message = codedInputStream.readMessage(ResourceLocator.parser(), extensionRegistryLite);
                                this.directive_ = message;
                                if (builderM10221toBuilder != null) {
                                    builderM10221toBuilder.mergeFrom((ResourceLocator) message);
                                    this.directive_ = builderM10221toBuilder.m10228buildPartial();
                                }
                                this.directiveCase_ = 1;
                            } else if (tag == 18) {
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.directiveCase_ = 2;
                                this.directive_ = stringRequireUtf8;
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

        public static Directive getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Directive> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ResourceLocatorProto.internal_static_udpa_core_v1_ResourceLocator_Directive_descriptor;
        }

        public static Directive parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Directive) PARSER.parseFrom(byteBuffer);
        }

        public static Directive parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Directive) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Directive parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Directive) PARSER.parseFrom(byteString);
        }

        public static Directive parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Directive) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Directive parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Directive) PARSER.parseFrom(bArr);
        }

        public static Directive parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Directive) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Directive parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Directive parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Directive parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Directive parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Directive parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Directive parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m10267toBuilder();
        }

        public static Builder newBuilder(Directive directive) {
            return DEFAULT_INSTANCE.m10267toBuilder().mergeFrom(directive);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Directive m10262getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Directive> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.DirectiveOrBuilder
        public boolean hasAlt() {
            return this.directiveCase_ == 1;
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
            return new Directive();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ResourceLocatorProto.internal_static_udpa_core_v1_ResourceLocator_Directive_fieldAccessorTable.ensureFieldAccessorsInitialized(Directive.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.DirectiveOrBuilder
        public DirectiveCase getDirectiveCase() {
            return DirectiveCase.forNumber(this.directiveCase_);
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.DirectiveOrBuilder
        public ResourceLocator getAlt() {
            if (this.directiveCase_ == 1) {
                return (ResourceLocator) this.directive_;
            }
            return ResourceLocator.getDefaultInstance();
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.DirectiveOrBuilder
        public ResourceLocatorOrBuilder getAltOrBuilder() {
            if (this.directiveCase_ == 1) {
                return (ResourceLocator) this.directive_;
            }
            return ResourceLocator.getDefaultInstance();
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.DirectiveOrBuilder
        public String getEntry() {
            String str = this.directiveCase_ == 2 ? this.directive_ : "";
            if (str instanceof String) {
                return (String) str;
            }
            String stringUtf8 = ((ByteString) str).toStringUtf8();
            if (this.directiveCase_ == 2) {
                this.directive_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.DirectiveOrBuilder
        public ByteString getEntryBytes() {
            String str = this.directiveCase_ == 2 ? this.directive_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.directiveCase_ == 2) {
                    this.directive_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.directiveCase_ == 1) {
                codedOutputStream.writeMessage(1, (ResourceLocator) this.directive_);
            }
            if (this.directiveCase_ == 2) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.directive_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = this.directiveCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (ResourceLocator) this.directive_) : 0;
            if (this.directiveCase_ == 2) {
                iComputeMessageSize += GeneratedMessageV3.computeStringSize(2, this.directive_);
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Directive)) {
                return super.equals(obj);
            }
            Directive directive = (Directive) obj;
            if (!getDirectiveCase().equals(directive.getDirectiveCase())) {
                return false;
            }
            int i = this.directiveCase_;
            if (i == 1) {
                if (!getAlt().equals(directive.getAlt())) {
                    return false;
                }
            } else if (i == 2 && !getEntry().equals(directive.getEntry())) {
                return false;
            }
            return this.unknownFields.equals(directive.unknownFields);
        }

        public int hashCode() {
            int i;
            int iHashCode;
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode2 = 779 + getDescriptor().hashCode();
            int i2 = this.directiveCase_;
            if (i2 == 1) {
                i = ((iHashCode2 * 37) + 1) * 53;
                iHashCode = getAlt().hashCode();
            } else {
                if (i2 == 2) {
                    i = ((iHashCode2 * 37) + 2) * 53;
                    iHashCode = getEntry().hashCode();
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
        public Builder m10264newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10267toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public enum DirectiveCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
            ALT(1),
            ENTRY(2),
            DIRECTIVE_NOT_SET(0);

            private final int value;

            DirectiveCase(int i) {
                this.value = i;
            }

            public static DirectiveCase forNumber(int i) {
                if (i == 0) {
                    return DIRECTIVE_NOT_SET;
                }
                if (i == 1) {
                    return ALT;
                }
                if (i != 2) {
                    return null;
                }
                return ENTRY;
            }

            @Deprecated
            public static DirectiveCase valueOf(int i) {
                return forNumber(i);
            }

            public int getNumber() {
                return this.value;
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DirectiveOrBuilder {
            private SingleFieldBuilderV3<ResourceLocator, Builder, ResourceLocatorOrBuilder> altBuilder_;
            private int directiveCase_;
            private Object directive_;

            private Builder() {
                this.directiveCase_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.directiveCase_ = 0;
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ResourceLocatorProto.internal_static_udpa_core_v1_ResourceLocator_Directive_descriptor;
            }

            @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.DirectiveOrBuilder
            public boolean hasAlt() {
                return this.directiveCase_ == 1;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ResourceLocatorProto.internal_static_udpa_core_v1_ResourceLocator_Directive_fieldAccessorTable.ensureFieldAccessorsInitialized(Directive.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Directive.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10278clear() {
                super.clear();
                this.directiveCase_ = 0;
                this.directive_ = null;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ResourceLocatorProto.internal_static_udpa_core_v1_ResourceLocator_Directive_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Directive m10291getDefaultInstanceForType() {
                return Directive.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Directive m10272build() throws UninitializedMessageException {
                Directive directiveM10274buildPartial = m10274buildPartial();
                if (directiveM10274buildPartial.isInitialized()) {
                    return directiveM10274buildPartial;
                }
                throw newUninitializedMessageException(directiveM10274buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Directive m10274buildPartial() {
                Directive directive = new Directive(this);
                if (this.directiveCase_ == 1) {
                    SingleFieldBuilderV3<ResourceLocator, Builder, ResourceLocatorOrBuilder> singleFieldBuilderV3 = this.altBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        directive.directive_ = this.directive_;
                    } else {
                        directive.directive_ = singleFieldBuilderV3.build();
                    }
                }
                if (this.directiveCase_ == 2) {
                    directive.directive_ = this.directive_;
                }
                directive.directiveCase_ = this.directiveCase_;
                onBuilt();
                return directive;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10290clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10302setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10280clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10283clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10304setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10270addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10295mergeFrom(Message message) {
                if (message instanceof Directive) {
                    return mergeFrom((Directive) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Directive directive) {
                if (directive == Directive.getDefaultInstance()) {
                    return this;
                }
                int i = AnonymousClass2.$SwitchMap$com$github$udpa$udpa$core$v1$ResourceLocator$Directive$DirectiveCase[directive.getDirectiveCase().ordinal()];
                if (i == 1) {
                    mergeAlt(directive.getAlt());
                } else if (i == 2) {
                    this.directiveCase_ = 2;
                    this.directive_ = directive.directive_;
                    onChanged();
                }
                m10300mergeUnknownFields(directive.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.Directive.Builder m10296mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.Directive.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator$Directive r3 = (io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.Directive) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator$Directive r4 = (io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.Directive) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.Directive.Builder.m10296mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator$Directive$Builder");
            }

            @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.DirectiveOrBuilder
            public DirectiveCase getDirectiveCase() {
                return DirectiveCase.forNumber(this.directiveCase_);
            }

            public Builder clearDirective() {
                this.directiveCase_ = 0;
                this.directive_ = null;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.DirectiveOrBuilder
            public ResourceLocator getAlt() {
                SingleFieldBuilderV3<ResourceLocator, Builder, ResourceLocatorOrBuilder> singleFieldBuilderV3 = this.altBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.directiveCase_ == 1) {
                        return (ResourceLocator) this.directive_;
                    }
                    return ResourceLocator.getDefaultInstance();
                }
                if (this.directiveCase_ == 1) {
                    return singleFieldBuilderV3.getMessage();
                }
                return ResourceLocator.getDefaultInstance();
            }

            public Builder setAlt(ResourceLocator resourceLocator) {
                SingleFieldBuilderV3<ResourceLocator, Builder, ResourceLocatorOrBuilder> singleFieldBuilderV3 = this.altBuilder_;
                if (singleFieldBuilderV3 == null) {
                    resourceLocator.getClass();
                    this.directive_ = resourceLocator;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(resourceLocator);
                }
                this.directiveCase_ = 1;
                return this;
            }

            public Builder setAlt(Builder builder) {
                SingleFieldBuilderV3<ResourceLocator, Builder, ResourceLocatorOrBuilder> singleFieldBuilderV3 = this.altBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.directive_ = builder.m10226build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m10226build());
                }
                this.directiveCase_ = 1;
                return this;
            }

            public Builder mergeAlt(ResourceLocator resourceLocator) {
                SingleFieldBuilderV3<ResourceLocator, Builder, ResourceLocatorOrBuilder> singleFieldBuilderV3 = this.altBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.directiveCase_ != 1 || this.directive_ == ResourceLocator.getDefaultInstance()) {
                        this.directive_ = resourceLocator;
                    } else {
                        this.directive_ = ResourceLocator.newBuilder((ResourceLocator) this.directive_).mergeFrom(resourceLocator).m10228buildPartial();
                    }
                    onChanged();
                } else {
                    if (this.directiveCase_ == 1) {
                        singleFieldBuilderV3.mergeFrom(resourceLocator);
                    }
                    this.altBuilder_.setMessage(resourceLocator);
                }
                this.directiveCase_ = 1;
                return this;
            }

            public Builder clearAlt() {
                SingleFieldBuilderV3<ResourceLocator, Builder, ResourceLocatorOrBuilder> singleFieldBuilderV3 = this.altBuilder_;
                if (singleFieldBuilderV3 != null) {
                    if (this.directiveCase_ == 1) {
                        this.directiveCase_ = 0;
                        this.directive_ = null;
                    }
                    singleFieldBuilderV3.clear();
                } else if (this.directiveCase_ == 1) {
                    this.directiveCase_ = 0;
                    this.directive_ = null;
                    onChanged();
                }
                return this;
            }

            public Builder getAltBuilder() {
                return getAltFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.DirectiveOrBuilder
            public ResourceLocatorOrBuilder getAltOrBuilder() {
                SingleFieldBuilderV3<ResourceLocator, Builder, ResourceLocatorOrBuilder> singleFieldBuilderV3;
                int i = this.directiveCase_;
                if (i == 1 && (singleFieldBuilderV3 = this.altBuilder_) != null) {
                    return (ResourceLocatorOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                if (i == 1) {
                    return (ResourceLocator) this.directive_;
                }
                return ResourceLocator.getDefaultInstance();
            }

            private SingleFieldBuilderV3<ResourceLocator, Builder, ResourceLocatorOrBuilder> getAltFieldBuilder() {
                if (this.altBuilder_ == null) {
                    if (this.directiveCase_ != 1) {
                        this.directive_ = ResourceLocator.getDefaultInstance();
                    }
                    this.altBuilder_ = new SingleFieldBuilderV3<>((ResourceLocator) this.directive_, getParentForChildren(), isClean());
                    this.directive_ = null;
                }
                this.directiveCase_ = 1;
                onChanged();
                return this.altBuilder_;
            }

            @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.DirectiveOrBuilder
            public String getEntry() {
                String str = this.directiveCase_ == 2 ? this.directive_ : "";
                if (!(str instanceof String)) {
                    String stringUtf8 = ((ByteString) str).toStringUtf8();
                    if (this.directiveCase_ == 2) {
                        this.directive_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) str;
            }

            public Builder setEntry(String str) {
                str.getClass();
                this.directiveCase_ = 2;
                this.directive_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.DirectiveOrBuilder
            public ByteString getEntryBytes() {
                String str = this.directiveCase_ == 2 ? this.directive_ : "";
                if (str instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                    if (this.directiveCase_ == 2) {
                        this.directive_ = byteStringCopyFromUtf8;
                    }
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) str;
            }

            public Builder setEntryBytes(ByteString byteString) {
                byteString.getClass();
                Directive.checkByteStringIsUtf8(byteString);
                this.directiveCase_ = 2;
                this.directive_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearEntry() {
                if (this.directiveCase_ == 2) {
                    this.directiveCase_ = 0;
                    this.directive_ = null;
                    onChanged();
                }
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m10306setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m10300mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ResourceLocatorOrBuilder {
        private Object authority_;
        private int bitField0_;
        private int contextParamSpecifierCase_;
        private Object contextParamSpecifier_;
        private RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> directivesBuilder_;
        private List<Directive> directives_;
        private SingleFieldBuilderV3<ContextParams, ContextParams.Builder, ContextParamsOrBuilder> exactContextBuilder_;
        private LazyStringList id_;
        private Object resourceType_;
        private int scheme_;

        private Builder() {
            this.contextParamSpecifierCase_ = 0;
            this.scheme_ = 0;
            this.id_ = LazyStringArrayList.EMPTY;
            this.authority_ = "";
            this.resourceType_ = "";
            this.directives_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.contextParamSpecifierCase_ = 0;
            this.scheme_ = 0;
            this.id_ = LazyStringArrayList.EMPTY;
            this.authority_ = "";
            this.resourceType_ = "";
            this.directives_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ResourceLocatorProto.internal_static_udpa_core_v1_ResourceLocator_descriptor;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public int getSchemeValue() {
            return this.scheme_;
        }

        public Builder setSchemeValue(int i) {
            this.scheme_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public boolean hasExactContext() {
            return this.contextParamSpecifierCase_ == 5;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ResourceLocatorProto.internal_static_udpa_core_v1_ResourceLocator_fieldAccessorTable.ensureFieldAccessorsInitialized(ResourceLocator.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (ResourceLocator.alwaysUseFieldBuilders) {
                getDirectivesFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10232clear() {
            super.clear();
            this.scheme_ = 0;
            this.id_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.authority_ = "";
            this.resourceType_ = "";
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.directives_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.contextParamSpecifierCase_ = 0;
            this.contextParamSpecifier_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ResourceLocatorProto.internal_static_udpa_core_v1_ResourceLocator_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ResourceLocator m10245getDefaultInstanceForType() {
            return ResourceLocator.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ResourceLocator m10226build() throws UninitializedMessageException {
            ResourceLocator resourceLocatorM10228buildPartial = m10228buildPartial();
            if (resourceLocatorM10228buildPartial.isInitialized()) {
                return resourceLocatorM10228buildPartial;
            }
            throw newUninitializedMessageException(resourceLocatorM10228buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ResourceLocator m10228buildPartial() {
            ResourceLocator resourceLocator = new ResourceLocator(this);
            resourceLocator.scheme_ = this.scheme_;
            if ((this.bitField0_ & 1) != 0) {
                this.id_ = this.id_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            resourceLocator.id_ = this.id_;
            resourceLocator.authority_ = this.authority_;
            resourceLocator.resourceType_ = this.resourceType_;
            if (this.contextParamSpecifierCase_ == 5) {
                SingleFieldBuilderV3<ContextParams, ContextParams.Builder, ContextParamsOrBuilder> singleFieldBuilderV3 = this.exactContextBuilder_;
                if (singleFieldBuilderV3 == null) {
                    resourceLocator.contextParamSpecifier_ = this.contextParamSpecifier_;
                } else {
                    resourceLocator.contextParamSpecifier_ = singleFieldBuilderV3.build();
                }
            }
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) != 0) {
                    this.directives_ = Collections.unmodifiableList(this.directives_);
                    this.bitField0_ &= -3;
                }
                resourceLocator.directives_ = this.directives_;
            } else {
                resourceLocator.directives_ = repeatedFieldBuilderV3.build();
            }
            resourceLocator.contextParamSpecifierCase_ = this.contextParamSpecifierCase_;
            onBuilt();
            return resourceLocator;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10244clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10256setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10234clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10237clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10258setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10224addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10249mergeFrom(Message message) {
            if (message instanceof ResourceLocator) {
                return mergeFrom((ResourceLocator) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ResourceLocator resourceLocator) {
            if (resourceLocator == ResourceLocator.getDefaultInstance()) {
                return this;
            }
            if (resourceLocator.scheme_ != 0) {
                setSchemeValue(resourceLocator.getSchemeValue());
            }
            if (!resourceLocator.id_.isEmpty()) {
                if (this.id_.isEmpty()) {
                    this.id_ = resourceLocator.id_;
                    this.bitField0_ &= -2;
                } else {
                    ensureIdIsMutable();
                    this.id_.addAll(resourceLocator.id_);
                }
                onChanged();
            }
            if (!resourceLocator.getAuthority().isEmpty()) {
                this.authority_ = resourceLocator.authority_;
                onChanged();
            }
            if (!resourceLocator.getResourceType().isEmpty()) {
                this.resourceType_ = resourceLocator.resourceType_;
                onChanged();
            }
            if (this.directivesBuilder_ == null) {
                if (!resourceLocator.directives_.isEmpty()) {
                    if (this.directives_.isEmpty()) {
                        this.directives_ = resourceLocator.directives_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureDirectivesIsMutable();
                        this.directives_.addAll(resourceLocator.directives_);
                    }
                    onChanged();
                }
            } else if (!resourceLocator.directives_.isEmpty()) {
                if (!this.directivesBuilder_.isEmpty()) {
                    this.directivesBuilder_.addAllMessages(resourceLocator.directives_);
                } else {
                    this.directivesBuilder_.dispose();
                    this.directivesBuilder_ = null;
                    this.directives_ = resourceLocator.directives_;
                    this.bitField0_ &= -3;
                    this.directivesBuilder_ = ResourceLocator.alwaysUseFieldBuilders ? getDirectivesFieldBuilder() : null;
                }
            }
            if (AnonymousClass2.$SwitchMap$com$github$udpa$udpa$core$v1$ResourceLocator$ContextParamSpecifierCase[resourceLocator.getContextParamSpecifierCase().ordinal()] == 1) {
                mergeExactContext(resourceLocator.getExactContext());
            }
            m10254mergeUnknownFields(resourceLocator.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.Builder m10250mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.access$2300()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator r3 = (io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator r4 = (io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator.Builder.m10250mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator$Builder");
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public ContextParamSpecifierCase getContextParamSpecifierCase() {
            return ContextParamSpecifierCase.forNumber(this.contextParamSpecifierCase_);
        }

        public Builder clearContextParamSpecifier() {
            this.contextParamSpecifierCase_ = 0;
            this.contextParamSpecifier_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public Scheme getScheme() {
            Scheme schemeValueOf = Scheme.valueOf(this.scheme_);
            return schemeValueOf == null ? Scheme.UNRECOGNIZED : schemeValueOf;
        }

        public Builder setScheme(Scheme scheme) {
            scheme.getClass();
            this.scheme_ = scheme.getNumber();
            onChanged();
            return this;
        }

        public Builder clearScheme() {
            this.scheme_ = 0;
            onChanged();
            return this;
        }

        private void ensureIdIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.id_ = new LazyStringArrayList(this.id_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        /* renamed from: getIdList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo10217getIdList() {
            return this.id_.getUnmodifiableView();
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public int getIdCount() {
            return this.id_.size();
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public String getId(int i) {
            return (String) this.id_.get(i);
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public ByteString getIdBytes(int i) {
            return this.id_.getByteString(i);
        }

        public Builder setId(int i, String str) {
            str.getClass();
            ensureIdIsMutable();
            this.id_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addId(String str) {
            str.getClass();
            ensureIdIsMutable();
            this.id_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllId(Iterable<String> iterable) {
            ensureIdIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.id_);
            onChanged();
            return this;
        }

        public Builder clearId() {
            this.id_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addIdBytes(ByteString byteString) {
            byteString.getClass();
            ResourceLocator.checkByteStringIsUtf8(byteString);
            ensureIdIsMutable();
            this.id_.add(byteString);
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public String getAuthority() {
            Object obj = this.authority_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.authority_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setAuthority(String str) {
            str.getClass();
            this.authority_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public ByteString getAuthorityBytes() {
            Object obj = this.authority_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.authority_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAuthorityBytes(ByteString byteString) {
            byteString.getClass();
            ResourceLocator.checkByteStringIsUtf8(byteString);
            this.authority_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearAuthority() {
            this.authority_ = ResourceLocator.getDefaultInstance().getAuthority();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public String getResourceType() {
            Object obj = this.resourceType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.resourceType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setResourceType(String str) {
            str.getClass();
            this.resourceType_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public ByteString getResourceTypeBytes() {
            Object obj = this.resourceType_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.resourceType_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setResourceTypeBytes(ByteString byteString) {
            byteString.getClass();
            ResourceLocator.checkByteStringIsUtf8(byteString);
            this.resourceType_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearResourceType() {
            this.resourceType_ = ResourceLocator.getDefaultInstance().getResourceType();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public ContextParams getExactContext() {
            SingleFieldBuilderV3<ContextParams, ContextParams.Builder, ContextParamsOrBuilder> singleFieldBuilderV3 = this.exactContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.contextParamSpecifierCase_ == 5) {
                    return (ContextParams) this.contextParamSpecifier_;
                }
                return ContextParams.getDefaultInstance();
            }
            if (this.contextParamSpecifierCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return ContextParams.getDefaultInstance();
        }

        public Builder setExactContext(ContextParams contextParams) {
            SingleFieldBuilderV3<ContextParams, ContextParams.Builder, ContextParamsOrBuilder> singleFieldBuilderV3 = this.exactContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                contextParams.getClass();
                this.contextParamSpecifier_ = contextParams;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(contextParams);
            }
            this.contextParamSpecifierCase_ = 5;
            return this;
        }

        public Builder setExactContext(ContextParams.Builder builder) {
            SingleFieldBuilderV3<ContextParams, ContextParams.Builder, ContextParamsOrBuilder> singleFieldBuilderV3 = this.exactContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.contextParamSpecifier_ = builder.m10179build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m10179build());
            }
            this.contextParamSpecifierCase_ = 5;
            return this;
        }

        public Builder mergeExactContext(ContextParams contextParams) {
            SingleFieldBuilderV3<ContextParams, ContextParams.Builder, ContextParamsOrBuilder> singleFieldBuilderV3 = this.exactContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.contextParamSpecifierCase_ != 5 || this.contextParamSpecifier_ == ContextParams.getDefaultInstance()) {
                    this.contextParamSpecifier_ = contextParams;
                } else {
                    this.contextParamSpecifier_ = ContextParams.newBuilder((ContextParams) this.contextParamSpecifier_).mergeFrom(contextParams).m10181buildPartial();
                }
                onChanged();
            } else {
                if (this.contextParamSpecifierCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(contextParams);
                }
                this.exactContextBuilder_.setMessage(contextParams);
            }
            this.contextParamSpecifierCase_ = 5;
            return this;
        }

        public Builder clearExactContext() {
            SingleFieldBuilderV3<ContextParams, ContextParams.Builder, ContextParamsOrBuilder> singleFieldBuilderV3 = this.exactContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.contextParamSpecifierCase_ == 5) {
                    this.contextParamSpecifierCase_ = 0;
                    this.contextParamSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.contextParamSpecifierCase_ == 5) {
                this.contextParamSpecifierCase_ = 0;
                this.contextParamSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public ContextParams.Builder getExactContextBuilder() {
            return getExactContextFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public ContextParamsOrBuilder getExactContextOrBuilder() {
            SingleFieldBuilderV3<ContextParams, ContextParams.Builder, ContextParamsOrBuilder> singleFieldBuilderV3;
            int i = this.contextParamSpecifierCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.exactContextBuilder_) != null) {
                return (ContextParamsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (ContextParams) this.contextParamSpecifier_;
            }
            return ContextParams.getDefaultInstance();
        }

        private SingleFieldBuilderV3<ContextParams, ContextParams.Builder, ContextParamsOrBuilder> getExactContextFieldBuilder() {
            if (this.exactContextBuilder_ == null) {
                if (this.contextParamSpecifierCase_ != 5) {
                    this.contextParamSpecifier_ = ContextParams.getDefaultInstance();
                }
                this.exactContextBuilder_ = new SingleFieldBuilderV3<>((ContextParams) this.contextParamSpecifier_, getParentForChildren(), isClean());
                this.contextParamSpecifier_ = null;
            }
            this.contextParamSpecifierCase_ = 5;
            onChanged();
            return this.exactContextBuilder_;
        }

        private void ensureDirectivesIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.directives_ = new ArrayList(this.directives_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public List<Directive> getDirectivesList() {
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.directives_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public int getDirectivesCount() {
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.directives_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public Directive getDirectives(int i) {
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.directives_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setDirectives(int i, Directive directive) {
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                directive.getClass();
                ensureDirectivesIsMutable();
                this.directives_.set(i, directive);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, directive);
            }
            return this;
        }

        public Builder setDirectives(int i, Directive.Builder builder) {
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDirectivesIsMutable();
                this.directives_.set(i, builder.m10272build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m10272build());
            }
            return this;
        }

        public Builder addDirectives(Directive directive) {
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                directive.getClass();
                ensureDirectivesIsMutable();
                this.directives_.add(directive);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(directive);
            }
            return this;
        }

        public Builder addDirectives(int i, Directive directive) {
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                directive.getClass();
                ensureDirectivesIsMutable();
                this.directives_.add(i, directive);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, directive);
            }
            return this;
        }

        public Builder addDirectives(Directive.Builder builder) {
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDirectivesIsMutable();
                this.directives_.add(builder.m10272build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m10272build());
            }
            return this;
        }

        public Builder addDirectives(int i, Directive.Builder builder) {
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDirectivesIsMutable();
                this.directives_.add(i, builder.m10272build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m10272build());
            }
            return this;
        }

        public Builder addAllDirectives(Iterable<? extends Directive> iterable) {
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDirectivesIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.directives_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearDirectives() {
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.directives_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeDirectives(int i) {
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDirectivesIsMutable();
                this.directives_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Directive.Builder getDirectivesBuilder(int i) {
            return getDirectivesFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public DirectiveOrBuilder getDirectivesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.directives_.get(i);
            }
            return (DirectiveOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder
        public List<? extends DirectiveOrBuilder> getDirectivesOrBuilderList() {
            RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> repeatedFieldBuilderV3 = this.directivesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.directives_);
        }

        public Directive.Builder addDirectivesBuilder() {
            return getDirectivesFieldBuilder().addBuilder(Directive.getDefaultInstance());
        }

        public Directive.Builder addDirectivesBuilder(int i) {
            return getDirectivesFieldBuilder().addBuilder(i, Directive.getDefaultInstance());
        }

        public List<Directive.Builder> getDirectivesBuilderList() {
            return getDirectivesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Directive, Directive.Builder, DirectiveOrBuilder> getDirectivesFieldBuilder() {
            if (this.directivesBuilder_ == null) {
                this.directivesBuilder_ = new RepeatedFieldBuilderV3<>(this.directives_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.directives_ = null;
            }
            return this.directivesBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m10260setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m10254mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$github$udpa$udpa$core$v1$ResourceLocator$ContextParamSpecifierCase;
        static final /* synthetic */ int[] $SwitchMap$com$github$udpa$udpa$core$v1$ResourceLocator$Directive$DirectiveCase;

        static {
            int[] iArr = new int[ContextParamSpecifierCase.values().length];
            $SwitchMap$com$github$udpa$udpa$core$v1$ResourceLocator$ContextParamSpecifierCase = iArr;
            try {
                iArr[ContextParamSpecifierCase.EXACT_CONTEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$github$udpa$udpa$core$v1$ResourceLocator$ContextParamSpecifierCase[ContextParamSpecifierCase.CONTEXTPARAMSPECIFIER_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[Directive.DirectiveCase.values().length];
            $SwitchMap$com$github$udpa$udpa$core$v1$ResourceLocator$Directive$DirectiveCase = iArr2;
            try {
                iArr2[Directive.DirectiveCase.ALT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$github$udpa$udpa$core$v1$ResourceLocator$Directive$DirectiveCase[Directive.DirectiveCase.ENTRY.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$github$udpa$udpa$core$v1$ResourceLocator$Directive$DirectiveCase[Directive.DirectiveCase.DIRECTIVE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
