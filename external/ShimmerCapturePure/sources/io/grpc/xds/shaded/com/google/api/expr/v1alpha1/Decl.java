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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Constant;
import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Type;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class Decl extends GeneratedMessageV3 implements DeclOrBuilder {
    public static final int FUNCTION_FIELD_NUMBER = 3;
    public static final int IDENT_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final Decl DEFAULT_INSTANCE = new Decl();
    private static final Parser<Decl> PARSER = new AbstractParser<Decl>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Decl m10548parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Decl(codedInputStream, extensionRegistryLite);
        }
    };
    private int declKindCase_;
    private Object declKind_;
    private byte memoizedIsInitialized;
    private volatile Object name_;

    private Decl(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.declKindCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Decl() {
        this.declKindCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    private Decl(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                IdentDecl.Builder builderM10685toBuilder = this.declKindCase_ == 2 ? ((IdentDecl) this.declKind_).m10685toBuilder() : null;
                                MessageLite message = codedInputStream.readMessage(IdentDecl.parser(), extensionRegistryLite);
                                this.declKind_ = message;
                                if (builderM10685toBuilder != null) {
                                    builderM10685toBuilder.mergeFrom((IdentDecl) message);
                                    this.declKind_ = builderM10685toBuilder.m10692buildPartial();
                                }
                                this.declKindCase_ = 2;
                            } else if (tag == 26) {
                                FunctionDecl.Builder builderM10592toBuilder = this.declKindCase_ == 3 ? ((FunctionDecl) this.declKind_).m10592toBuilder() : null;
                                MessageLite message2 = codedInputStream.readMessage(FunctionDecl.parser(), extensionRegistryLite);
                                this.declKind_ = message2;
                                if (builderM10592toBuilder != null) {
                                    builderM10592toBuilder.mergeFrom((FunctionDecl) message2);
                                    this.declKind_ = builderM10592toBuilder.m10599buildPartial();
                                }
                                this.declKindCase_ = 3;
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.name_ = codedInputStream.readStringRequireUtf8();
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

    public static Decl getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Decl> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_descriptor;
    }

    public static Decl parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Decl) PARSER.parseFrom(byteBuffer);
    }

    public static Decl parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Decl) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Decl parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Decl) PARSER.parseFrom(byteString);
    }

    public static Decl parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Decl) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Decl parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Decl) PARSER.parseFrom(bArr);
    }

    public static Decl parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Decl) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Decl parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Decl parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Decl parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Decl parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Decl parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Decl parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m10546toBuilder();
    }

    public static Builder newBuilder(Decl decl) {
        return DEFAULT_INSTANCE.m10546toBuilder().mergeFrom(decl);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Decl m10541getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Decl> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
    public boolean hasFunction() {
        return this.declKindCase_ == 3;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
    public boolean hasIdent() {
        return this.declKindCase_ == 2;
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
        return new Decl();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_fieldAccessorTable.ensureFieldAccessorsInitialized(Decl.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
    public DeclKindCase getDeclKindCase() {
        return DeclKindCase.forNumber(this.declKindCase_);
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
    public IdentDecl getIdent() {
        if (this.declKindCase_ == 2) {
            return (IdentDecl) this.declKind_;
        }
        return IdentDecl.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
    public IdentDeclOrBuilder getIdentOrBuilder() {
        if (this.declKindCase_ == 2) {
            return (IdentDecl) this.declKind_;
        }
        return IdentDecl.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
    public FunctionDecl getFunction() {
        if (this.declKindCase_ == 3) {
            return (FunctionDecl) this.declKind_;
        }
        return FunctionDecl.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
    public FunctionDeclOrBuilder getFunctionOrBuilder() {
        if (this.declKindCase_ == 3) {
            return (FunctionDecl) this.declKind_;
        }
        return FunctionDecl.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.declKindCase_ == 2) {
            codedOutputStream.writeMessage(2, (IdentDecl) this.declKind_);
        }
        if (this.declKindCase_ == 3) {
            codedOutputStream.writeMessage(3, (FunctionDecl) this.declKind_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (this.declKindCase_ == 2) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, (IdentDecl) this.declKind_);
        }
        if (this.declKindCase_ == 3) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, (FunctionDecl) this.declKind_);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Decl)) {
            return super.equals(obj);
        }
        Decl decl = (Decl) obj;
        if (!getName().equals(decl.getName()) || !getDeclKindCase().equals(decl.getDeclKindCase())) {
            return false;
        }
        int i = this.declKindCase_;
        if (i == 2) {
            if (!getIdent().equals(decl.getIdent())) {
                return false;
            }
        } else if (i == 3 && !getFunction().equals(decl.getFunction())) {
            return false;
        }
        return this.unknownFields.equals(decl.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        int i2 = this.declKindCase_;
        if (i2 == 2) {
            i = ((iHashCode2 * 37) + 2) * 53;
            iHashCode = getIdent().hashCode();
        } else {
            if (i2 == 3) {
                i = ((iHashCode2 * 37) + 3) * 53;
                iHashCode = getFunction().hashCode();
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
    public Builder m10543newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m10546toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum DeclKindCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        IDENT(2),
        FUNCTION(3),
        DECLKIND_NOT_SET(0);

        private final int value;

        DeclKindCase(int i) {
            this.value = i;
        }

        public static DeclKindCase forNumber(int i) {
            if (i == 0) {
                return DECLKIND_NOT_SET;
            }
            if (i == 2) {
                return IDENT;
            }
            if (i != 3) {
                return null;
            }
            return FUNCTION;
        }

        @Deprecated
        public static DeclKindCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public interface FunctionDeclOrBuilder extends MessageOrBuilder {
        FunctionDecl.Overload getOverloads(int i);

        int getOverloadsCount();

        List<FunctionDecl.Overload> getOverloadsList();

        FunctionDecl.OverloadOrBuilder getOverloadsOrBuilder(int i);

        List<? extends FunctionDecl.OverloadOrBuilder> getOverloadsOrBuilderList();
    }

    public interface IdentDeclOrBuilder extends MessageOrBuilder {
        String getDoc();

        ByteString getDocBytes();

        Type getType();

        TypeOrBuilder getTypeOrBuilder();

        Constant getValue();

        ConstantOrBuilder getValueOrBuilder();

        boolean hasType();

        boolean hasValue();
    }

    public static final class IdentDecl extends GeneratedMessageV3 implements IdentDeclOrBuilder {
        public static final int DOC_FIELD_NUMBER = 3;
        public static final int TYPE_FIELD_NUMBER = 1;
        public static final int VALUE_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private static final IdentDecl DEFAULT_INSTANCE = new IdentDecl();
        private static final Parser<IdentDecl> PARSER = new AbstractParser<IdentDecl>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDecl.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public IdentDecl m10687parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new IdentDecl(codedInputStream, extensionRegistryLite);
            }
        };
        private volatile Object doc_;
        private byte memoizedIsInitialized;
        private Type type_;
        private Constant value_;

        private IdentDecl(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private IdentDecl() {
            this.memoizedIsInitialized = (byte) -1;
            this.doc_ = "";
        }

        private IdentDecl(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    Type type = this.type_;
                                    Type.Builder builderM11284toBuilder = type != null ? type.m11284toBuilder() : null;
                                    Type type2 = (Type) codedInputStream.readMessage(Type.parser(), extensionRegistryLite);
                                    this.type_ = type2;
                                    if (builderM11284toBuilder != null) {
                                        builderM11284toBuilder.mergeFrom(type2);
                                        this.type_ = builderM11284toBuilder.m11337buildPartial();
                                    }
                                } else if (tag == 18) {
                                    Constant constant = this.value_;
                                    Constant.Builder builderM10500toBuilder = constant != null ? constant.m10500toBuilder() : null;
                                    Constant constant2 = (Constant) codedInputStream.readMessage(Constant.parser(), extensionRegistryLite);
                                    this.value_ = constant2;
                                    if (builderM10500toBuilder != null) {
                                        builderM10500toBuilder.mergeFrom(constant2);
                                        this.value_ = builderM10500toBuilder.m10507buildPartial();
                                    }
                                } else if (tag == 26) {
                                    this.doc_ = codedInputStream.readStringRequireUtf8();
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

        public static IdentDecl getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<IdentDecl> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_IdentDecl_descriptor;
        }

        public static IdentDecl parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (IdentDecl) PARSER.parseFrom(byteBuffer);
        }

        public static IdentDecl parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IdentDecl) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static IdentDecl parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (IdentDecl) PARSER.parseFrom(byteString);
        }

        public static IdentDecl parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IdentDecl) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static IdentDecl parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (IdentDecl) PARSER.parseFrom(bArr);
        }

        public static IdentDecl parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IdentDecl) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static IdentDecl parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static IdentDecl parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static IdentDecl parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static IdentDecl parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static IdentDecl parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static IdentDecl parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m10685toBuilder();
        }

        public static Builder newBuilder(IdentDecl identDecl) {
            return DEFAULT_INSTANCE.m10685toBuilder().mergeFrom(identDecl);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public IdentDecl m10680getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<IdentDecl> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
        public boolean hasType() {
            return this.type_ != null;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
        public boolean hasValue() {
            return this.value_ != null;
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
            return new IdentDecl();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_IdentDecl_fieldAccessorTable.ensureFieldAccessorsInitialized(IdentDecl.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
        public Type getType() {
            Type type = this.type_;
            return type == null ? Type.getDefaultInstance() : type;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
        public TypeOrBuilder getTypeOrBuilder() {
            return getType();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
        public Constant getValue() {
            Constant constant = this.value_;
            return constant == null ? Constant.getDefaultInstance() : constant;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
        public ConstantOrBuilder getValueOrBuilder() {
            return getValue();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
        public String getDoc() {
            Object obj = this.doc_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.doc_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
        public ByteString getDocBytes() {
            Object obj = this.doc_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.doc_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.type_ != null) {
                codedOutputStream.writeMessage(1, getType());
            }
            if (this.value_ != null) {
                codedOutputStream.writeMessage(2, getValue());
            }
            if (!getDocBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.doc_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = this.type_ != null ? CodedOutputStream.computeMessageSize(1, getType()) : 0;
            if (this.value_ != null) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getValue());
            }
            if (!getDocBytes().isEmpty()) {
                iComputeMessageSize += GeneratedMessageV3.computeStringSize(3, this.doc_);
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof IdentDecl)) {
                return super.equals(obj);
            }
            IdentDecl identDecl = (IdentDecl) obj;
            if (hasType() != identDecl.hasType()) {
                return false;
            }
            if ((!hasType() || getType().equals(identDecl.getType())) && hasValue() == identDecl.hasValue()) {
                return (!hasValue() || getValue().equals(identDecl.getValue())) && getDoc().equals(identDecl.getDoc()) && this.unknownFields.equals(identDecl.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasType()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getType().hashCode();
            }
            if (hasValue()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getValue().hashCode();
            }
            int iHashCode2 = (((((iHashCode * 37) + 3) * 53) + getDoc().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10682newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10685toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements IdentDeclOrBuilder {
            private Object doc_;
            private SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> typeBuilder_;
            private Type type_;
            private SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> valueBuilder_;
            private Constant value_;

            private Builder() {
                this.doc_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.doc_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_IdentDecl_descriptor;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
            public boolean hasType() {
                return (this.typeBuilder_ == null && this.type_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
            public boolean hasValue() {
                return (this.valueBuilder_ == null && this.value_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_IdentDecl_fieldAccessorTable.ensureFieldAccessorsInitialized(IdentDecl.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = IdentDecl.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10696clear() {
                super.clear();
                if (this.typeBuilder_ == null) {
                    this.type_ = null;
                } else {
                    this.type_ = null;
                    this.typeBuilder_ = null;
                }
                if (this.valueBuilder_ == null) {
                    this.value_ = null;
                } else {
                    this.value_ = null;
                    this.valueBuilder_ = null;
                }
                this.doc_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_IdentDecl_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public IdentDecl m10709getDefaultInstanceForType() {
                return IdentDecl.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public IdentDecl m10690build() throws UninitializedMessageException {
                IdentDecl identDeclM10692buildPartial = m10692buildPartial();
                if (identDeclM10692buildPartial.isInitialized()) {
                    return identDeclM10692buildPartial;
                }
                throw newUninitializedMessageException(identDeclM10692buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public IdentDecl m10692buildPartial() {
                IdentDecl identDecl = new IdentDecl(this);
                SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> singleFieldBuilderV3 = this.typeBuilder_;
                if (singleFieldBuilderV3 == null) {
                    identDecl.type_ = this.type_;
                } else {
                    identDecl.type_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV32 = this.valueBuilder_;
                if (singleFieldBuilderV32 == null) {
                    identDecl.value_ = this.value_;
                } else {
                    identDecl.value_ = singleFieldBuilderV32.build();
                }
                identDecl.doc_ = this.doc_;
                onBuilt();
                return identDecl;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10708clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10720setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10698clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10701clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10722setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10688addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10713mergeFrom(Message message) {
                if (message instanceof IdentDecl) {
                    return mergeFrom((IdentDecl) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(IdentDecl identDecl) {
                if (identDecl == IdentDecl.getDefaultInstance()) {
                    return this;
                }
                if (identDecl.hasType()) {
                    mergeType(identDecl.getType());
                }
                if (identDecl.hasValue()) {
                    mergeValue(identDecl.getValue());
                }
                if (!identDecl.getDoc().isEmpty()) {
                    this.doc_ = identDecl.doc_;
                    onChanged();
                }
                m10718mergeUnknownFields(identDecl.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDecl.Builder m10714mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDecl.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl$IdentDecl r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDecl) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl$IdentDecl r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDecl) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDecl.Builder.m10714mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl$IdentDecl$Builder");
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
            public Type getType() {
                SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> singleFieldBuilderV3 = this.typeBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Type type = this.type_;
                return type == null ? Type.getDefaultInstance() : type;
            }

            public Builder setType(Type type) {
                SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> singleFieldBuilderV3 = this.typeBuilder_;
                if (singleFieldBuilderV3 == null) {
                    type.getClass();
                    this.type_ = type;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(type);
                }
                return this;
            }

            public Builder setType(Type.Builder builder) {
                SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> singleFieldBuilderV3 = this.typeBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.type_ = builder.m11335build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m11335build());
                }
                return this;
            }

            public Builder mergeType(Type type) {
                SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> singleFieldBuilderV3 = this.typeBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Type type2 = this.type_;
                    if (type2 != null) {
                        this.type_ = Type.newBuilder(type2).mergeFrom(type).m11337buildPartial();
                    } else {
                        this.type_ = type;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(type);
                }
                return this;
            }

            public Builder clearType() {
                if (this.typeBuilder_ == null) {
                    this.type_ = null;
                    onChanged();
                } else {
                    this.type_ = null;
                    this.typeBuilder_ = null;
                }
                return this;
            }

            public Type.Builder getTypeBuilder() {
                onChanged();
                return getTypeFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
            public TypeOrBuilder getTypeOrBuilder() {
                SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> singleFieldBuilderV3 = this.typeBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (TypeOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Type type = this.type_;
                return type == null ? Type.getDefaultInstance() : type;
            }

            private SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> getTypeFieldBuilder() {
                if (this.typeBuilder_ == null) {
                    this.typeBuilder_ = new SingleFieldBuilderV3<>(getType(), getParentForChildren(), isClean());
                    this.type_ = null;
                }
                return this.typeBuilder_;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
            public Constant getValue() {
                SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Constant constant = this.value_;
                return constant == null ? Constant.getDefaultInstance() : constant;
            }

            public Builder setValue(Constant constant) {
                SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
                if (singleFieldBuilderV3 == null) {
                    constant.getClass();
                    this.value_ = constant;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(constant);
                }
                return this;
            }

            public Builder setValue(Constant.Builder builder) {
                SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.value_ = builder.m10505build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m10505build());
                }
                return this;
            }

            public Builder mergeValue(Constant constant) {
                SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Constant constant2 = this.value_;
                    if (constant2 != null) {
                        this.value_ = Constant.newBuilder(constant2).mergeFrom(constant).m10507buildPartial();
                    } else {
                        this.value_ = constant;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(constant);
                }
                return this;
            }

            public Builder clearValue() {
                if (this.valueBuilder_ == null) {
                    this.value_ = null;
                    onChanged();
                } else {
                    this.value_ = null;
                    this.valueBuilder_ = null;
                }
                return this;
            }

            public Constant.Builder getValueBuilder() {
                onChanged();
                return getValueFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
            public ConstantOrBuilder getValueOrBuilder() {
                SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (ConstantOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Constant constant = this.value_;
                return constant == null ? Constant.getDefaultInstance() : constant;
            }

            private SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> getValueFieldBuilder() {
                if (this.valueBuilder_ == null) {
                    this.valueBuilder_ = new SingleFieldBuilderV3<>(getValue(), getParentForChildren(), isClean());
                    this.value_ = null;
                }
                return this.valueBuilder_;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
            public String getDoc() {
                Object obj = this.doc_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.doc_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setDoc(String str) {
                str.getClass();
                this.doc_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.IdentDeclOrBuilder
            public ByteString getDocBytes() {
                Object obj = this.doc_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.doc_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setDocBytes(ByteString byteString) {
                byteString.getClass();
                IdentDecl.checkByteStringIsUtf8(byteString);
                this.doc_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearDoc() {
                this.doc_ = IdentDecl.getDefaultInstance().getDoc();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m10724setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m10718mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class FunctionDecl extends GeneratedMessageV3 implements FunctionDeclOrBuilder {
        public static final int OVERLOADS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final FunctionDecl DEFAULT_INSTANCE = new FunctionDecl();
        private static final Parser<FunctionDecl> PARSER = new AbstractParser<FunctionDecl>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public FunctionDecl m10594parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new FunctionDecl(codedInputStream, extensionRegistryLite);
            }
        };
        private byte memoizedIsInitialized;
        private List<Overload> overloads_;

        private FunctionDecl(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private FunctionDecl() {
            this.memoizedIsInitialized = (byte) -1;
            this.overloads_ = Collections.emptyList();
        }

        private FunctionDecl(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                if (!(z2 & true)) {
                                    this.overloads_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.overloads_.add(codedInputStream.readMessage(Overload.parser(), extensionRegistryLite));
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
                    if (z2 & true) {
                        this.overloads_ = Collections.unmodifiableList(this.overloads_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static FunctionDecl getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FunctionDecl> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_descriptor;
        }

        public static FunctionDecl parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FunctionDecl) PARSER.parseFrom(byteBuffer);
        }

        public static FunctionDecl parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FunctionDecl) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static FunctionDecl parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FunctionDecl) PARSER.parseFrom(byteString);
        }

        public static FunctionDecl parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FunctionDecl) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static FunctionDecl parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FunctionDecl) PARSER.parseFrom(bArr);
        }

        public static FunctionDecl parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FunctionDecl) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static FunctionDecl parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static FunctionDecl parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FunctionDecl parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static FunctionDecl parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FunctionDecl parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static FunctionDecl parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m10592toBuilder();
        }

        public static Builder newBuilder(FunctionDecl functionDecl) {
            return DEFAULT_INSTANCE.m10592toBuilder().mergeFrom(functionDecl);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FunctionDecl m10587getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDeclOrBuilder
        public List<Overload> getOverloadsList() {
            return this.overloads_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDeclOrBuilder
        public List<? extends OverloadOrBuilder> getOverloadsOrBuilderList() {
            return this.overloads_;
        }

        public Parser<FunctionDecl> getParserForType() {
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
            return new FunctionDecl();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_fieldAccessorTable.ensureFieldAccessorsInitialized(FunctionDecl.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDeclOrBuilder
        public int getOverloadsCount() {
            return this.overloads_.size();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDeclOrBuilder
        public Overload getOverloads(int i) {
            return this.overloads_.get(i);
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDeclOrBuilder
        public OverloadOrBuilder getOverloadsOrBuilder(int i) {
            return this.overloads_.get(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.overloads_.size(); i++) {
                codedOutputStream.writeMessage(1, this.overloads_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = 0;
            for (int i2 = 0; i2 < this.overloads_.size(); i2++) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.overloads_.get(i2));
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FunctionDecl)) {
                return super.equals(obj);
            }
            FunctionDecl functionDecl = (FunctionDecl) obj;
            return getOverloadsList().equals(functionDecl.getOverloadsList()) && this.unknownFields.equals(functionDecl.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (getOverloadsCount() > 0) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getOverloadsList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10589newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10592toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public interface OverloadOrBuilder extends MessageOrBuilder {
            String getDoc();

            ByteString getDocBytes();

            boolean getIsInstanceFunction();

            String getOverloadId();

            ByteString getOverloadIdBytes();

            Type getParams(int i);

            int getParamsCount();

            List<Type> getParamsList();

            TypeOrBuilder getParamsOrBuilder(int i);

            List<? extends TypeOrBuilder> getParamsOrBuilderList();

            Type getResultType();

            TypeOrBuilder getResultTypeOrBuilder();

            String getTypeParams(int i);

            ByteString getTypeParamsBytes(int i);

            int getTypeParamsCount();

            /* renamed from: getTypeParamsList */
            List<String> mo10635getTypeParamsList();

            boolean hasResultType();
        }

        public static final class Overload extends GeneratedMessageV3 implements OverloadOrBuilder {
            public static final int DOC_FIELD_NUMBER = 6;
            public static final int IS_INSTANCE_FUNCTION_FIELD_NUMBER = 5;
            public static final int OVERLOAD_ID_FIELD_NUMBER = 1;
            public static final int PARAMS_FIELD_NUMBER = 2;
            public static final int RESULT_TYPE_FIELD_NUMBER = 4;
            public static final int TYPE_PARAMS_FIELD_NUMBER = 3;
            private static final long serialVersionUID = 0;
            private static final Overload DEFAULT_INSTANCE = new Overload();
            private static final Parser<Overload> PARSER = new AbstractParser<Overload>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.Overload.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public Overload m10641parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Overload(codedInputStream, extensionRegistryLite);
                }
            };
            private volatile Object doc_;
            private boolean isInstanceFunction_;
            private byte memoizedIsInitialized;
            private volatile Object overloadId_;
            private List<Type> params_;
            private Type resultType_;
            private LazyStringList typeParams_;

            private Overload(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private Overload() {
                this.memoizedIsInitialized = (byte) -1;
                this.overloadId_ = "";
                this.params_ = Collections.emptyList();
                this.typeParams_ = LazyStringArrayList.EMPTY;
                this.doc_ = "";
            }

            private Overload(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                int i = 0;
                while (!z) {
                    try {
                        try {
                            try {
                                int tag = codedInputStream.readTag();
                                if (tag != 0) {
                                    if (tag == 10) {
                                        this.overloadId_ = codedInputStream.readStringRequireUtf8();
                                    } else if (tag == 18) {
                                        if ((i & 1) == 0) {
                                            this.params_ = new ArrayList();
                                            i |= 1;
                                        }
                                        this.params_.add(codedInputStream.readMessage(Type.parser(), extensionRegistryLite));
                                    } else if (tag == 26) {
                                        String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                        if ((i & 2) == 0) {
                                            this.typeParams_ = new LazyStringArrayList();
                                            i |= 2;
                                        }
                                        this.typeParams_.add(stringRequireUtf8);
                                    } else if (tag == 34) {
                                        Type type = this.resultType_;
                                        Type.Builder builderM11284toBuilder = type != null ? type.m11284toBuilder() : null;
                                        Type type2 = (Type) codedInputStream.readMessage(Type.parser(), extensionRegistryLite);
                                        this.resultType_ = type2;
                                        if (builderM11284toBuilder != null) {
                                            builderM11284toBuilder.mergeFrom(type2);
                                            this.resultType_ = builderM11284toBuilder.m11337buildPartial();
                                        }
                                    } else if (tag == 40) {
                                        this.isInstanceFunction_ = codedInputStream.readBool();
                                    } else if (tag == 50) {
                                        this.doc_ = codedInputStream.readStringRequireUtf8();
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
                        if ((i & 1) != 0) {
                            this.params_ = Collections.unmodifiableList(this.params_);
                        }
                        if ((i & 2) != 0) {
                            this.typeParams_ = this.typeParams_.getUnmodifiableView();
                        }
                        this.unknownFields = builderNewBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static Overload getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Overload> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_Overload_descriptor;
            }

            public static Overload parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Overload) PARSER.parseFrom(byteBuffer);
            }

            public static Overload parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Overload) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Overload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Overload) PARSER.parseFrom(byteString);
            }

            public static Overload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Overload) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Overload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Overload) PARSER.parseFrom(bArr);
            }

            public static Overload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Overload) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Overload parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Overload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Overload parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Overload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Overload parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Overload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m10639toBuilder();
            }

            public static Builder newBuilder(Overload overload) {
                return DEFAULT_INSTANCE.m10639toBuilder().mergeFrom(overload);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Overload m10633getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public boolean getIsInstanceFunction() {
                return this.isInstanceFunction_;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public List<Type> getParamsList() {
                return this.params_;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public List<? extends TypeOrBuilder> getParamsOrBuilderList() {
                return this.params_;
            }

            public Parser<Overload> getParserForType() {
                return PARSER;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            /* renamed from: getTypeParamsList, reason: merged with bridge method [inline-methods] */
            public ProtocolStringList mo10635getTypeParamsList() {
                return this.typeParams_;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public boolean hasResultType() {
                return this.resultType_ != null;
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
                return new Overload();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_Overload_fieldAccessorTable.ensureFieldAccessorsInitialized(Overload.class, Builder.class);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public String getOverloadId() {
                Object obj = this.overloadId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.overloadId_ = stringUtf8;
                return stringUtf8;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public ByteString getOverloadIdBytes() {
                Object obj = this.overloadId_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.overloadId_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public int getParamsCount() {
                return this.params_.size();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public Type getParams(int i) {
                return this.params_.get(i);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public TypeOrBuilder getParamsOrBuilder(int i) {
                return this.params_.get(i);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public int getTypeParamsCount() {
                return this.typeParams_.size();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public String getTypeParams(int i) {
                return (String) this.typeParams_.get(i);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public ByteString getTypeParamsBytes(int i) {
                return this.typeParams_.getByteString(i);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public Type getResultType() {
                Type type = this.resultType_;
                return type == null ? Type.getDefaultInstance() : type;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public TypeOrBuilder getResultTypeOrBuilder() {
                return getResultType();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public String getDoc() {
                Object obj = this.doc_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.doc_ = stringUtf8;
                return stringUtf8;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
            public ByteString getDocBytes() {
                Object obj = this.doc_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.doc_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (!getOverloadIdBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.overloadId_);
                }
                for (int i = 0; i < this.params_.size(); i++) {
                    codedOutputStream.writeMessage(2, this.params_.get(i));
                }
                for (int i2 = 0; i2 < this.typeParams_.size(); i2++) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.typeParams_.getRaw(i2));
                }
                if (this.resultType_ != null) {
                    codedOutputStream.writeMessage(4, getResultType());
                }
                boolean z = this.isInstanceFunction_;
                if (z) {
                    codedOutputStream.writeBool(5, z);
                }
                if (!getDocBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 6, this.doc_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeStringSize = !getOverloadIdBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.overloadId_) : 0;
                for (int i2 = 0; i2 < this.params_.size(); i2++) {
                    iComputeStringSize += CodedOutputStream.computeMessageSize(2, this.params_.get(i2));
                }
                int iComputeStringSizeNoTag = 0;
                for (int i3 = 0; i3 < this.typeParams_.size(); i3++) {
                    iComputeStringSizeNoTag += computeStringSizeNoTag(this.typeParams_.getRaw(i3));
                }
                int size = iComputeStringSize + iComputeStringSizeNoTag + mo10635getTypeParamsList().size();
                if (this.resultType_ != null) {
                    size += CodedOutputStream.computeMessageSize(4, getResultType());
                }
                boolean z = this.isInstanceFunction_;
                if (z) {
                    size += CodedOutputStream.computeBoolSize(5, z);
                }
                if (!getDocBytes().isEmpty()) {
                    size += GeneratedMessageV3.computeStringSize(6, this.doc_);
                }
                int serializedSize = size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Overload)) {
                    return super.equals(obj);
                }
                Overload overload = (Overload) obj;
                if (getOverloadId().equals(overload.getOverloadId()) && getParamsList().equals(overload.getParamsList()) && mo10635getTypeParamsList().equals(overload.mo10635getTypeParamsList()) && hasResultType() == overload.hasResultType()) {
                    return (!hasResultType() || getResultType().equals(overload.getResultType())) && getIsInstanceFunction() == overload.getIsInstanceFunction() && getDoc().equals(overload.getDoc()) && this.unknownFields.equals(overload.unknownFields);
                }
                return false;
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOverloadId().hashCode();
                if (getParamsCount() > 0) {
                    iHashCode = (((iHashCode * 37) + 2) * 53) + getParamsList().hashCode();
                }
                if (getTypeParamsCount() > 0) {
                    iHashCode = (((iHashCode * 37) + 3) * 53) + mo10635getTypeParamsList().hashCode();
                }
                if (hasResultType()) {
                    iHashCode = (((iHashCode * 37) + 4) * 53) + getResultType().hashCode();
                }
                int iHashBoolean = (((((((((iHashCode * 37) + 5) * 53) + Internal.hashBoolean(getIsInstanceFunction())) * 37) + 6) * 53) + getDoc().hashCode()) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashBoolean;
                return iHashBoolean;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10636newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10639toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OverloadOrBuilder {
                private int bitField0_;
                private Object doc_;
                private boolean isInstanceFunction_;
                private Object overloadId_;
                private RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> paramsBuilder_;
                private List<Type> params_;
                private SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> resultTypeBuilder_;
                private Type resultType_;
                private LazyStringList typeParams_;

                private Builder() {
                    this.overloadId_ = "";
                    this.params_ = Collections.emptyList();
                    this.typeParams_ = LazyStringArrayList.EMPTY;
                    this.doc_ = "";
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.overloadId_ = "";
                    this.params_ = Collections.emptyList();
                    this.typeParams_ = LazyStringArrayList.EMPTY;
                    this.doc_ = "";
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_Overload_descriptor;
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public boolean getIsInstanceFunction() {
                    return this.isInstanceFunction_;
                }

                public Builder setIsInstanceFunction(boolean z) {
                    this.isInstanceFunction_ = z;
                    onChanged();
                    return this;
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public boolean hasResultType() {
                    return (this.resultTypeBuilder_ == null && this.resultType_ == null) ? false : true;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_Overload_fieldAccessorTable.ensureFieldAccessorsInitialized(Overload.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    if (Overload.alwaysUseFieldBuilders) {
                        getParamsFieldBuilder();
                    }
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10650clear() {
                    super.clear();
                    this.overloadId_ = "";
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        this.params_ = Collections.emptyList();
                        this.bitField0_ &= -2;
                    } else {
                        repeatedFieldBuilderV3.clear();
                    }
                    this.typeParams_ = LazyStringArrayList.EMPTY;
                    this.bitField0_ &= -3;
                    if (this.resultTypeBuilder_ == null) {
                        this.resultType_ = null;
                    } else {
                        this.resultType_ = null;
                        this.resultTypeBuilder_ = null;
                    }
                    this.isInstanceFunction_ = false;
                    this.doc_ = "";
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_Overload_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Overload m10663getDefaultInstanceForType() {
                    return Overload.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Overload m10644build() throws UninitializedMessageException {
                    Overload overloadM10646buildPartial = m10646buildPartial();
                    if (overloadM10646buildPartial.isInitialized()) {
                        return overloadM10646buildPartial;
                    }
                    throw newUninitializedMessageException(overloadM10646buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Overload m10646buildPartial() {
                    Overload overload = new Overload(this);
                    overload.overloadId_ = this.overloadId_;
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        if ((this.bitField0_ & 1) != 0) {
                            this.params_ = Collections.unmodifiableList(this.params_);
                            this.bitField0_ &= -2;
                        }
                        overload.params_ = this.params_;
                    } else {
                        overload.params_ = repeatedFieldBuilderV3.build();
                    }
                    if ((this.bitField0_ & 2) != 0) {
                        this.typeParams_ = this.typeParams_.getUnmodifiableView();
                        this.bitField0_ &= -3;
                    }
                    overload.typeParams_ = this.typeParams_;
                    SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> singleFieldBuilderV3 = this.resultTypeBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        overload.resultType_ = this.resultType_;
                    } else {
                        overload.resultType_ = singleFieldBuilderV3.build();
                    }
                    overload.isInstanceFunction_ = this.isInstanceFunction_;
                    overload.doc_ = this.doc_;
                    onBuilt();
                    return overload;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10662clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10674setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10652clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10655clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10676setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10642addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10667mergeFrom(Message message) {
                    if (message instanceof Overload) {
                        return mergeFrom((Overload) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Overload overload) {
                    if (overload == Overload.getDefaultInstance()) {
                        return this;
                    }
                    if (!overload.getOverloadId().isEmpty()) {
                        this.overloadId_ = overload.overloadId_;
                        onChanged();
                    }
                    if (this.paramsBuilder_ == null) {
                        if (!overload.params_.isEmpty()) {
                            if (this.params_.isEmpty()) {
                                this.params_ = overload.params_;
                                this.bitField0_ &= -2;
                            } else {
                                ensureParamsIsMutable();
                                this.params_.addAll(overload.params_);
                            }
                            onChanged();
                        }
                    } else if (!overload.params_.isEmpty()) {
                        if (!this.paramsBuilder_.isEmpty()) {
                            this.paramsBuilder_.addAllMessages(overload.params_);
                        } else {
                            this.paramsBuilder_.dispose();
                            this.paramsBuilder_ = null;
                            this.params_ = overload.params_;
                            this.bitField0_ &= -2;
                            this.paramsBuilder_ = Overload.alwaysUseFieldBuilders ? getParamsFieldBuilder() : null;
                        }
                    }
                    if (!overload.typeParams_.isEmpty()) {
                        if (this.typeParams_.isEmpty()) {
                            this.typeParams_ = overload.typeParams_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureTypeParamsIsMutable();
                            this.typeParams_.addAll(overload.typeParams_);
                        }
                        onChanged();
                    }
                    if (overload.hasResultType()) {
                        mergeResultType(overload.getResultType());
                    }
                    if (overload.getIsInstanceFunction()) {
                        setIsInstanceFunction(overload.getIsInstanceFunction());
                    }
                    if (!overload.getDoc().isEmpty()) {
                        this.doc_ = overload.doc_;
                        onChanged();
                    }
                    m10672mergeUnknownFields(overload.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.Overload.Builder m10668mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.Overload.access$2300()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl$FunctionDecl$Overload r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.Overload) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl$FunctionDecl$Overload r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.Overload) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.Overload.Builder.m10668mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl$FunctionDecl$Overload$Builder");
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public String getOverloadId() {
                    Object obj = this.overloadId_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.overloadId_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setOverloadId(String str) {
                    str.getClass();
                    this.overloadId_ = str;
                    onChanged();
                    return this;
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public ByteString getOverloadIdBytes() {
                    Object obj = this.overloadId_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.overloadId_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setOverloadIdBytes(ByteString byteString) {
                    byteString.getClass();
                    Overload.checkByteStringIsUtf8(byteString);
                    this.overloadId_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearOverloadId() {
                    this.overloadId_ = Overload.getDefaultInstance().getOverloadId();
                    onChanged();
                    return this;
                }

                private void ensureParamsIsMutable() {
                    if ((this.bitField0_ & 1) == 0) {
                        this.params_ = new ArrayList(this.params_);
                        this.bitField0_ |= 1;
                    }
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public List<Type> getParamsList() {
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return Collections.unmodifiableList(this.params_);
                    }
                    return repeatedFieldBuilderV3.getMessageList();
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public int getParamsCount() {
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.params_.size();
                    }
                    return repeatedFieldBuilderV3.getCount();
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public Type getParams(int i) {
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.params_.get(i);
                    }
                    return repeatedFieldBuilderV3.getMessage(i);
                }

                public Builder setParams(int i, Type type) {
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        type.getClass();
                        ensureParamsIsMutable();
                        this.params_.set(i, type);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.setMessage(i, type);
                    }
                    return this;
                }

                public Builder setParams(int i, Type.Builder builder) {
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureParamsIsMutable();
                        this.params_.set(i, builder.m11335build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.setMessage(i, builder.m11335build());
                    }
                    return this;
                }

                public Builder addParams(Type type) {
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        type.getClass();
                        ensureParamsIsMutable();
                        this.params_.add(type);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(type);
                    }
                    return this;
                }

                public Builder addParams(int i, Type type) {
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        type.getClass();
                        ensureParamsIsMutable();
                        this.params_.add(i, type);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(i, type);
                    }
                    return this;
                }

                public Builder addParams(Type.Builder builder) {
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureParamsIsMutable();
                        this.params_.add(builder.m11335build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(builder.m11335build());
                    }
                    return this;
                }

                public Builder addParams(int i, Type.Builder builder) {
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureParamsIsMutable();
                        this.params_.add(i, builder.m11335build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(i, builder.m11335build());
                    }
                    return this;
                }

                public Builder addAllParams(Iterable<? extends Type> iterable) {
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureParamsIsMutable();
                        AbstractMessageLite.Builder.addAll(iterable, this.params_);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder clearParams() {
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        this.params_ = Collections.emptyList();
                        this.bitField0_ &= -2;
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.clear();
                    }
                    return this;
                }

                public Builder removeParams(int i) {
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureParamsIsMutable();
                        this.params_.remove(i);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.remove(i);
                    }
                    return this;
                }

                public Type.Builder getParamsBuilder(int i) {
                    return getParamsFieldBuilder().getBuilder(i);
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public TypeOrBuilder getParamsOrBuilder(int i) {
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.params_.get(i);
                    }
                    return (TypeOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public List<? extends TypeOrBuilder> getParamsOrBuilderList() {
                    RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.paramsBuilder_;
                    if (repeatedFieldBuilderV3 != null) {
                        return repeatedFieldBuilderV3.getMessageOrBuilderList();
                    }
                    return Collections.unmodifiableList(this.params_);
                }

                public Type.Builder addParamsBuilder() {
                    return getParamsFieldBuilder().addBuilder(Type.getDefaultInstance());
                }

                public Type.Builder addParamsBuilder(int i) {
                    return getParamsFieldBuilder().addBuilder(i, Type.getDefaultInstance());
                }

                public List<Type.Builder> getParamsBuilderList() {
                    return getParamsFieldBuilder().getBuilderList();
                }

                private RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> getParamsFieldBuilder() {
                    if (this.paramsBuilder_ == null) {
                        this.paramsBuilder_ = new RepeatedFieldBuilderV3<>(this.params_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                        this.params_ = null;
                    }
                    return this.paramsBuilder_;
                }

                private void ensureTypeParamsIsMutable() {
                    if ((this.bitField0_ & 2) == 0) {
                        this.typeParams_ = new LazyStringArrayList(this.typeParams_);
                        this.bitField0_ |= 2;
                    }
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                /* renamed from: getTypeParamsList, reason: merged with bridge method [inline-methods] */
                public ProtocolStringList mo10635getTypeParamsList() {
                    return this.typeParams_.getUnmodifiableView();
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public int getTypeParamsCount() {
                    return this.typeParams_.size();
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public String getTypeParams(int i) {
                    return (String) this.typeParams_.get(i);
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public ByteString getTypeParamsBytes(int i) {
                    return this.typeParams_.getByteString(i);
                }

                public Builder setTypeParams(int i, String str) {
                    str.getClass();
                    ensureTypeParamsIsMutable();
                    this.typeParams_.set(i, str);
                    onChanged();
                    return this;
                }

                public Builder addTypeParams(String str) {
                    str.getClass();
                    ensureTypeParamsIsMutable();
                    this.typeParams_.add(str);
                    onChanged();
                    return this;
                }

                public Builder addAllTypeParams(Iterable<String> iterable) {
                    ensureTypeParamsIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.typeParams_);
                    onChanged();
                    return this;
                }

                public Builder clearTypeParams() {
                    this.typeParams_ = LazyStringArrayList.EMPTY;
                    this.bitField0_ &= -3;
                    onChanged();
                    return this;
                }

                public Builder addTypeParamsBytes(ByteString byteString) {
                    byteString.getClass();
                    Overload.checkByteStringIsUtf8(byteString);
                    ensureTypeParamsIsMutable();
                    this.typeParams_.add(byteString);
                    onChanged();
                    return this;
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public Type getResultType() {
                    SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> singleFieldBuilderV3 = this.resultTypeBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    Type type = this.resultType_;
                    return type == null ? Type.getDefaultInstance() : type;
                }

                public Builder setResultType(Type type) {
                    SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> singleFieldBuilderV3 = this.resultTypeBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        type.getClass();
                        this.resultType_ = type;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(type);
                    }
                    return this;
                }

                public Builder setResultType(Type.Builder builder) {
                    SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> singleFieldBuilderV3 = this.resultTypeBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.resultType_ = builder.m11335build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m11335build());
                    }
                    return this;
                }

                public Builder mergeResultType(Type type) {
                    SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> singleFieldBuilderV3 = this.resultTypeBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        Type type2 = this.resultType_;
                        if (type2 != null) {
                            this.resultType_ = Type.newBuilder(type2).mergeFrom(type).m11337buildPartial();
                        } else {
                            this.resultType_ = type;
                        }
                        onChanged();
                    } else {
                        singleFieldBuilderV3.mergeFrom(type);
                    }
                    return this;
                }

                public Builder clearResultType() {
                    if (this.resultTypeBuilder_ == null) {
                        this.resultType_ = null;
                        onChanged();
                    } else {
                        this.resultType_ = null;
                        this.resultTypeBuilder_ = null;
                    }
                    return this;
                }

                public Type.Builder getResultTypeBuilder() {
                    onChanged();
                    return getResultTypeFieldBuilder().getBuilder();
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public TypeOrBuilder getResultTypeOrBuilder() {
                    SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> singleFieldBuilderV3 = this.resultTypeBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return (TypeOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    Type type = this.resultType_;
                    return type == null ? Type.getDefaultInstance() : type;
                }

                private SingleFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> getResultTypeFieldBuilder() {
                    if (this.resultTypeBuilder_ == null) {
                        this.resultTypeBuilder_ = new SingleFieldBuilderV3<>(getResultType(), getParentForChildren(), isClean());
                        this.resultType_ = null;
                    }
                    return this.resultTypeBuilder_;
                }

                public Builder clearIsInstanceFunction() {
                    this.isInstanceFunction_ = false;
                    onChanged();
                    return this;
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public String getDoc() {
                    Object obj = this.doc_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.doc_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setDoc(String str) {
                    str.getClass();
                    this.doc_ = str;
                    onChanged();
                    return this;
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.OverloadOrBuilder
                public ByteString getDocBytes() {
                    Object obj = this.doc_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.doc_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setDocBytes(ByteString byteString) {
                    byteString.getClass();
                    Overload.checkByteStringIsUtf8(byteString);
                    this.doc_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearDoc() {
                    this.doc_ = Overload.getDefaultInstance().getDoc();
                    onChanged();
                    return this;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m10678setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m10672mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FunctionDeclOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> overloadsBuilder_;
            private List<Overload> overloads_;

            private Builder() {
                this.overloads_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.overloads_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_fieldAccessorTable.ensureFieldAccessorsInitialized(FunctionDecl.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (FunctionDecl.alwaysUseFieldBuilders) {
                    getOverloadsFieldBuilder();
                }
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10603clear() {
                super.clear();
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.overloads_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public FunctionDecl m10616getDefaultInstanceForType() {
                return FunctionDecl.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public FunctionDecl m10597build() throws UninitializedMessageException {
                FunctionDecl functionDeclM10599buildPartial = m10599buildPartial();
                if (functionDeclM10599buildPartial.isInitialized()) {
                    return functionDeclM10599buildPartial;
                }
                throw newUninitializedMessageException(functionDeclM10599buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public FunctionDecl m10599buildPartial() {
                FunctionDecl functionDecl = new FunctionDecl(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.overloads_ = Collections.unmodifiableList(this.overloads_);
                        this.bitField0_ &= -2;
                    }
                    functionDecl.overloads_ = this.overloads_;
                } else {
                    functionDecl.overloads_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return functionDecl;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10615clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10627setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10605clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10608clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10629setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10595addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10620mergeFrom(Message message) {
                if (message instanceof FunctionDecl) {
                    return mergeFrom((FunctionDecl) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(FunctionDecl functionDecl) {
                if (functionDecl == FunctionDecl.getDefaultInstance()) {
                    return this;
                }
                if (this.overloadsBuilder_ == null) {
                    if (!functionDecl.overloads_.isEmpty()) {
                        if (this.overloads_.isEmpty()) {
                            this.overloads_ = functionDecl.overloads_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureOverloadsIsMutable();
                            this.overloads_.addAll(functionDecl.overloads_);
                        }
                        onChanged();
                    }
                } else if (!functionDecl.overloads_.isEmpty()) {
                    if (!this.overloadsBuilder_.isEmpty()) {
                        this.overloadsBuilder_.addAllMessages(functionDecl.overloads_);
                    } else {
                        this.overloadsBuilder_.dispose();
                        this.overloadsBuilder_ = null;
                        this.overloads_ = functionDecl.overloads_;
                        this.bitField0_ &= -2;
                        this.overloadsBuilder_ = FunctionDecl.alwaysUseFieldBuilders ? getOverloadsFieldBuilder() : null;
                    }
                }
                m10625mergeUnknownFields(functionDecl.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.Builder m10621mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.access$3500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl$FunctionDecl r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl$FunctionDecl r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDecl.Builder.m10621mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl$FunctionDecl$Builder");
            }

            private void ensureOverloadsIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.overloads_ = new ArrayList(this.overloads_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDeclOrBuilder
            public List<Overload> getOverloadsList() {
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.overloads_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDeclOrBuilder
            public int getOverloadsCount() {
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.overloads_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDeclOrBuilder
            public Overload getOverloads(int i) {
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.overloads_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setOverloads(int i, Overload overload) {
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    overload.getClass();
                    ensureOverloadsIsMutable();
                    this.overloads_.set(i, overload);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, overload);
                }
                return this;
            }

            public Builder setOverloads(int i, Overload.Builder builder) {
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOverloadsIsMutable();
                    this.overloads_.set(i, builder.m10644build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.m10644build());
                }
                return this;
            }

            public Builder addOverloads(Overload overload) {
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    overload.getClass();
                    ensureOverloadsIsMutable();
                    this.overloads_.add(overload);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(overload);
                }
                return this;
            }

            public Builder addOverloads(int i, Overload overload) {
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    overload.getClass();
                    ensureOverloadsIsMutable();
                    this.overloads_.add(i, overload);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, overload);
                }
                return this;
            }

            public Builder addOverloads(Overload.Builder builder) {
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOverloadsIsMutable();
                    this.overloads_.add(builder.m10644build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.m10644build());
                }
                return this;
            }

            public Builder addOverloads(int i, Overload.Builder builder) {
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOverloadsIsMutable();
                    this.overloads_.add(i, builder.m10644build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.m10644build());
                }
                return this;
            }

            public Builder addAllOverloads(Iterable<? extends Overload> iterable) {
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOverloadsIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.overloads_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearOverloads() {
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.overloads_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeOverloads(int i) {
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOverloadsIsMutable();
                    this.overloads_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Overload.Builder getOverloadsBuilder(int i) {
                return getOverloadsFieldBuilder().getBuilder(i);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDeclOrBuilder
            public OverloadOrBuilder getOverloadsOrBuilder(int i) {
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.overloads_.get(i);
                }
                return (OverloadOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.FunctionDeclOrBuilder
            public List<? extends OverloadOrBuilder> getOverloadsOrBuilderList() {
                RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> repeatedFieldBuilderV3 = this.overloadsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.overloads_);
            }

            public Overload.Builder addOverloadsBuilder() {
                return getOverloadsFieldBuilder().addBuilder(Overload.getDefaultInstance());
            }

            public Overload.Builder addOverloadsBuilder(int i) {
                return getOverloadsFieldBuilder().addBuilder(i, Overload.getDefaultInstance());
            }

            public List<Overload.Builder> getOverloadsBuilderList() {
                return getOverloadsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Overload, Overload.Builder, OverloadOrBuilder> getOverloadsFieldBuilder() {
                if (this.overloadsBuilder_ == null) {
                    this.overloadsBuilder_ = new RepeatedFieldBuilderV3<>(this.overloads_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.overloads_ = null;
                }
                return this.overloadsBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m10631setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m10625mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DeclOrBuilder {
        private int declKindCase_;
        private Object declKind_;
        private SingleFieldBuilderV3<FunctionDecl, FunctionDecl.Builder, FunctionDeclOrBuilder> functionBuilder_;
        private SingleFieldBuilderV3<IdentDecl, IdentDecl.Builder, IdentDeclOrBuilder> identBuilder_;
        private Object name_;

        private Builder() {
            this.declKindCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.declKindCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_descriptor;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
        public boolean hasFunction() {
            return this.declKindCase_ == 3;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
        public boolean hasIdent() {
            return this.declKindCase_ == 2;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_fieldAccessorTable.ensureFieldAccessorsInitialized(Decl.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Decl.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10557clear() {
            super.clear();
            this.name_ = "";
            this.declKindCase_ = 0;
            this.declKind_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return DeclProto.internal_static_google_api_expr_v1alpha1_Decl_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Decl m10570getDefaultInstanceForType() {
            return Decl.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Decl m10551build() throws UninitializedMessageException {
            Decl declM10553buildPartial = m10553buildPartial();
            if (declM10553buildPartial.isInitialized()) {
                return declM10553buildPartial;
            }
            throw newUninitializedMessageException(declM10553buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Decl m10553buildPartial() {
            Decl decl = new Decl(this);
            decl.name_ = this.name_;
            if (this.declKindCase_ == 2) {
                SingleFieldBuilderV3<IdentDecl, IdentDecl.Builder, IdentDeclOrBuilder> singleFieldBuilderV3 = this.identBuilder_;
                if (singleFieldBuilderV3 == null) {
                    decl.declKind_ = this.declKind_;
                } else {
                    decl.declKind_ = singleFieldBuilderV3.build();
                }
            }
            if (this.declKindCase_ == 3) {
                SingleFieldBuilderV3<FunctionDecl, FunctionDecl.Builder, FunctionDeclOrBuilder> singleFieldBuilderV32 = this.functionBuilder_;
                if (singleFieldBuilderV32 == null) {
                    decl.declKind_ = this.declKind_;
                } else {
                    decl.declKind_ = singleFieldBuilderV32.build();
                }
            }
            decl.declKindCase_ = this.declKindCase_;
            onBuilt();
            return decl;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10569clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10581setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10559clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10562clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10583setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10549addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10574mergeFrom(Message message) {
            if (message instanceof Decl) {
                return mergeFrom((Decl) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Decl decl) {
            if (decl == Decl.getDefaultInstance()) {
                return this;
            }
            if (!decl.getName().isEmpty()) {
                this.name_ = decl.name_;
                onChanged();
            }
            int i = AnonymousClass2.$SwitchMap$com$google$api$expr$v1alpha1$Decl$DeclKindCase[decl.getDeclKindCase().ordinal()];
            if (i == 1) {
                mergeIdent(decl.getIdent());
            } else if (i == 2) {
                mergeFunction(decl.getFunction());
            }
            m10579mergeUnknownFields(decl.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.Builder m10575mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.access$4500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl.Builder.m10575mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl$Builder");
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
        public DeclKindCase getDeclKindCase() {
            return DeclKindCase.forNumber(this.declKindCase_);
        }

        public Builder clearDeclKind() {
            this.declKindCase_ = 0;
            this.declKind_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
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

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
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
            Decl.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = Decl.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
        public IdentDecl getIdent() {
            SingleFieldBuilderV3<IdentDecl, IdentDecl.Builder, IdentDeclOrBuilder> singleFieldBuilderV3 = this.identBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.declKindCase_ == 2) {
                    return (IdentDecl) this.declKind_;
                }
                return IdentDecl.getDefaultInstance();
            }
            if (this.declKindCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return IdentDecl.getDefaultInstance();
        }

        public Builder setIdent(IdentDecl identDecl) {
            SingleFieldBuilderV3<IdentDecl, IdentDecl.Builder, IdentDeclOrBuilder> singleFieldBuilderV3 = this.identBuilder_;
            if (singleFieldBuilderV3 == null) {
                identDecl.getClass();
                this.declKind_ = identDecl;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(identDecl);
            }
            this.declKindCase_ = 2;
            return this;
        }

        public Builder setIdent(IdentDecl.Builder builder) {
            SingleFieldBuilderV3<IdentDecl, IdentDecl.Builder, IdentDeclOrBuilder> singleFieldBuilderV3 = this.identBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.declKind_ = builder.m10690build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m10690build());
            }
            this.declKindCase_ = 2;
            return this;
        }

        public Builder mergeIdent(IdentDecl identDecl) {
            SingleFieldBuilderV3<IdentDecl, IdentDecl.Builder, IdentDeclOrBuilder> singleFieldBuilderV3 = this.identBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.declKindCase_ != 2 || this.declKind_ == IdentDecl.getDefaultInstance()) {
                    this.declKind_ = identDecl;
                } else {
                    this.declKind_ = IdentDecl.newBuilder((IdentDecl) this.declKind_).mergeFrom(identDecl).m10692buildPartial();
                }
                onChanged();
            } else {
                if (this.declKindCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(identDecl);
                }
                this.identBuilder_.setMessage(identDecl);
            }
            this.declKindCase_ = 2;
            return this;
        }

        public Builder clearIdent() {
            SingleFieldBuilderV3<IdentDecl, IdentDecl.Builder, IdentDeclOrBuilder> singleFieldBuilderV3 = this.identBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.declKindCase_ == 2) {
                    this.declKindCase_ = 0;
                    this.declKind_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.declKindCase_ == 2) {
                this.declKindCase_ = 0;
                this.declKind_ = null;
                onChanged();
            }
            return this;
        }

        public IdentDecl.Builder getIdentBuilder() {
            return getIdentFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
        public IdentDeclOrBuilder getIdentOrBuilder() {
            SingleFieldBuilderV3<IdentDecl, IdentDecl.Builder, IdentDeclOrBuilder> singleFieldBuilderV3;
            int i = this.declKindCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.identBuilder_) != null) {
                return (IdentDeclOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (IdentDecl) this.declKind_;
            }
            return IdentDecl.getDefaultInstance();
        }

        private SingleFieldBuilderV3<IdentDecl, IdentDecl.Builder, IdentDeclOrBuilder> getIdentFieldBuilder() {
            if (this.identBuilder_ == null) {
                if (this.declKindCase_ != 2) {
                    this.declKind_ = IdentDecl.getDefaultInstance();
                }
                this.identBuilder_ = new SingleFieldBuilderV3<>((IdentDecl) this.declKind_, getParentForChildren(), isClean());
                this.declKind_ = null;
            }
            this.declKindCase_ = 2;
            onChanged();
            return this.identBuilder_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
        public FunctionDecl getFunction() {
            SingleFieldBuilderV3<FunctionDecl, FunctionDecl.Builder, FunctionDeclOrBuilder> singleFieldBuilderV3 = this.functionBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.declKindCase_ == 3) {
                    return (FunctionDecl) this.declKind_;
                }
                return FunctionDecl.getDefaultInstance();
            }
            if (this.declKindCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return FunctionDecl.getDefaultInstance();
        }

        public Builder setFunction(FunctionDecl functionDecl) {
            SingleFieldBuilderV3<FunctionDecl, FunctionDecl.Builder, FunctionDeclOrBuilder> singleFieldBuilderV3 = this.functionBuilder_;
            if (singleFieldBuilderV3 == null) {
                functionDecl.getClass();
                this.declKind_ = functionDecl;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(functionDecl);
            }
            this.declKindCase_ = 3;
            return this;
        }

        public Builder setFunction(FunctionDecl.Builder builder) {
            SingleFieldBuilderV3<FunctionDecl, FunctionDecl.Builder, FunctionDeclOrBuilder> singleFieldBuilderV3 = this.functionBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.declKind_ = builder.m10597build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m10597build());
            }
            this.declKindCase_ = 3;
            return this;
        }

        public Builder mergeFunction(FunctionDecl functionDecl) {
            SingleFieldBuilderV3<FunctionDecl, FunctionDecl.Builder, FunctionDeclOrBuilder> singleFieldBuilderV3 = this.functionBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.declKindCase_ != 3 || this.declKind_ == FunctionDecl.getDefaultInstance()) {
                    this.declKind_ = functionDecl;
                } else {
                    this.declKind_ = FunctionDecl.newBuilder((FunctionDecl) this.declKind_).mergeFrom(functionDecl).m10599buildPartial();
                }
                onChanged();
            } else {
                if (this.declKindCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(functionDecl);
                }
                this.functionBuilder_.setMessage(functionDecl);
            }
            this.declKindCase_ = 3;
            return this;
        }

        public Builder clearFunction() {
            SingleFieldBuilderV3<FunctionDecl, FunctionDecl.Builder, FunctionDeclOrBuilder> singleFieldBuilderV3 = this.functionBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.declKindCase_ == 3) {
                    this.declKindCase_ = 0;
                    this.declKind_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.declKindCase_ == 3) {
                this.declKindCase_ = 0;
                this.declKind_ = null;
                onChanged();
            }
            return this;
        }

        public FunctionDecl.Builder getFunctionBuilder() {
            return getFunctionFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.DeclOrBuilder
        public FunctionDeclOrBuilder getFunctionOrBuilder() {
            SingleFieldBuilderV3<FunctionDecl, FunctionDecl.Builder, FunctionDeclOrBuilder> singleFieldBuilderV3;
            int i = this.declKindCase_;
            if (i == 3 && (singleFieldBuilderV3 = this.functionBuilder_) != null) {
                return (FunctionDeclOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 3) {
                return (FunctionDecl) this.declKind_;
            }
            return FunctionDecl.getDefaultInstance();
        }

        private SingleFieldBuilderV3<FunctionDecl, FunctionDecl.Builder, FunctionDeclOrBuilder> getFunctionFieldBuilder() {
            if (this.functionBuilder_ == null) {
                if (this.declKindCase_ != 3) {
                    this.declKind_ = FunctionDecl.getDefaultInstance();
                }
                this.functionBuilder_ = new SingleFieldBuilderV3<>((FunctionDecl) this.declKind_, getParentForChildren(), isClean());
                this.declKind_ = null;
            }
            this.declKindCase_ = 3;
            onChanged();
            return this.functionBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m10585setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m10579mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$api$expr$v1alpha1$Decl$DeclKindCase;

        static {
            int[] iArr = new int[DeclKindCase.values().length];
            $SwitchMap$com$google$api$expr$v1alpha1$Decl$DeclKindCase = iArr;
            try {
                iArr[DeclKindCase.IDENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$api$expr$v1alpha1$Decl$DeclKindCase[DeclKindCase.FUNCTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$api$expr$v1alpha1$Decl$DeclKindCase[DeclKindCase.DECLKIND_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
