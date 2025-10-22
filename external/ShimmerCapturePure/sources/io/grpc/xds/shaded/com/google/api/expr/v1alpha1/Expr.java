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
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class Expr extends GeneratedMessageV3 implements ExprOrBuilder {
    public static final int CALL_EXPR_FIELD_NUMBER = 6;
    public static final int COMPREHENSION_EXPR_FIELD_NUMBER = 9;
    public static final int CONST_EXPR_FIELD_NUMBER = 3;
    public static final int IDENT_EXPR_FIELD_NUMBER = 4;
    public static final int ID_FIELD_NUMBER = 2;
    public static final int LIST_EXPR_FIELD_NUMBER = 7;
    public static final int SELECT_EXPR_FIELD_NUMBER = 5;
    public static final int STRUCT_EXPR_FIELD_NUMBER = 8;
    private static final long serialVersionUID = 0;
    private static final Expr DEFAULT_INSTANCE = new Expr();
    private static final Parser<Expr> PARSER = new AbstractParser<Expr>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Expr m10733parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Expr(codedInputStream, extensionRegistryLite);
        }
    };
    private int exprKindCase_;
    private Object exprKind_;
    private long id_;
    private byte memoizedIsInitialized;

    private Expr(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.exprKindCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Expr() {
        this.exprKindCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Expr(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag != 16) {
                                if (tag == 26) {
                                    Constant.Builder builderM10500toBuilder = this.exprKindCase_ == 3 ? ((Constant) this.exprKind_).m10500toBuilder() : null;
                                    MessageLite message = codedInputStream.readMessage(Constant.parser(), extensionRegistryLite);
                                    this.exprKind_ = message;
                                    if (builderM10500toBuilder != null) {
                                        builderM10500toBuilder.mergeFrom((Constant) message);
                                        this.exprKind_ = builderM10500toBuilder.m10507buildPartial();
                                    }
                                    this.exprKindCase_ = 3;
                                } else if (tag == 34) {
                                    Ident.Builder builderM11007toBuilder = this.exprKindCase_ == 4 ? ((Ident) this.exprKind_).m11007toBuilder() : null;
                                    MessageLite message2 = codedInputStream.readMessage(Ident.parser(), extensionRegistryLite);
                                    this.exprKind_ = message2;
                                    if (builderM11007toBuilder != null) {
                                        builderM11007toBuilder.mergeFrom((Ident) message2);
                                        this.exprKind_ = builderM11007toBuilder.m11014buildPartial();
                                    }
                                    this.exprKindCase_ = 4;
                                } else if (tag == 42) {
                                    Select.Builder builderM11053toBuilder = this.exprKindCase_ == 5 ? ((Select) this.exprKind_).m11053toBuilder() : null;
                                    MessageLite message3 = codedInputStream.readMessage(Select.parser(), extensionRegistryLite);
                                    this.exprKind_ = message3;
                                    if (builderM11053toBuilder != null) {
                                        builderM11053toBuilder.mergeFrom((Select) message3);
                                        this.exprKind_ = builderM11053toBuilder.m11060buildPartial();
                                    }
                                    this.exprKindCase_ = 5;
                                } else if (tag == 50) {
                                    Call.Builder builderM10777toBuilder = this.exprKindCase_ == 6 ? ((Call) this.exprKind_).m10777toBuilder() : null;
                                    MessageLite message4 = codedInputStream.readMessage(Call.parser(), extensionRegistryLite);
                                    this.exprKind_ = message4;
                                    if (builderM10777toBuilder != null) {
                                        builderM10777toBuilder.mergeFrom((Call) message4);
                                        this.exprKind_ = builderM10777toBuilder.m10784buildPartial();
                                    }
                                    this.exprKindCase_ = 6;
                                } else if (tag == 58) {
                                    CreateList.Builder builderM10869toBuilder = this.exprKindCase_ == 7 ? ((CreateList) this.exprKind_).m10869toBuilder() : null;
                                    MessageLite message5 = codedInputStream.readMessage(CreateList.parser(), extensionRegistryLite);
                                    this.exprKind_ = message5;
                                    if (builderM10869toBuilder != null) {
                                        builderM10869toBuilder.mergeFrom((CreateList) message5);
                                        this.exprKind_ = builderM10869toBuilder.m10876buildPartial();
                                    }
                                    this.exprKindCase_ = 7;
                                } else if (tag == 66) {
                                    CreateStruct.Builder builderM10915toBuilder = this.exprKindCase_ == 8 ? ((CreateStruct) this.exprKind_).m10915toBuilder() : null;
                                    MessageLite message6 = codedInputStream.readMessage(CreateStruct.parser(), extensionRegistryLite);
                                    this.exprKind_ = message6;
                                    if (builderM10915toBuilder != null) {
                                        builderM10915toBuilder.mergeFrom((CreateStruct) message6);
                                        this.exprKind_ = builderM10915toBuilder.m10922buildPartial();
                                    }
                                    this.exprKindCase_ = 8;
                                } else if (tag == 74) {
                                    Comprehension.Builder builderM10823toBuilder = this.exprKindCase_ == 9 ? ((Comprehension) this.exprKind_).m10823toBuilder() : null;
                                    MessageLite message7 = codedInputStream.readMessage(Comprehension.parser(), extensionRegistryLite);
                                    this.exprKind_ = message7;
                                    if (builderM10823toBuilder != null) {
                                        builderM10823toBuilder.mergeFrom((Comprehension) message7);
                                        this.exprKind_ = builderM10823toBuilder.m10830buildPartial();
                                    }
                                    this.exprKindCase_ = 9;
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                this.id_ = codedInputStream.readInt64();
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

    public static Expr getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Expr> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_descriptor;
    }

    public static Expr parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Expr) PARSER.parseFrom(byteBuffer);
    }

    public static Expr parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Expr) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Expr parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Expr) PARSER.parseFrom(byteString);
    }

    public static Expr parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Expr) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Expr parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Expr) PARSER.parseFrom(bArr);
    }

    public static Expr parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Expr) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Expr parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Expr parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Expr parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Expr parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Expr parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Expr parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m10731toBuilder();
    }

    public static Builder newBuilder(Expr expr) {
        return DEFAULT_INSTANCE.m10731toBuilder().mergeFrom(expr);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Expr m10726getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public long getId() {
        return this.id_;
    }

    public Parser<Expr> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public boolean hasCallExpr() {
        return this.exprKindCase_ == 6;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public boolean hasComprehensionExpr() {
        return this.exprKindCase_ == 9;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public boolean hasConstExpr() {
        return this.exprKindCase_ == 3;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public boolean hasIdentExpr() {
        return this.exprKindCase_ == 4;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public boolean hasListExpr() {
        return this.exprKindCase_ == 7;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public boolean hasSelectExpr() {
        return this.exprKindCase_ == 5;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public boolean hasStructExpr() {
        return this.exprKindCase_ == 8;
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
        return new Expr();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_fieldAccessorTable.ensureFieldAccessorsInitialized(Expr.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public ExprKindCase getExprKindCase() {
        return ExprKindCase.forNumber(this.exprKindCase_);
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public Constant getConstExpr() {
        if (this.exprKindCase_ == 3) {
            return (Constant) this.exprKind_;
        }
        return Constant.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public ConstantOrBuilder getConstExprOrBuilder() {
        if (this.exprKindCase_ == 3) {
            return (Constant) this.exprKind_;
        }
        return Constant.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public Ident getIdentExpr() {
        if (this.exprKindCase_ == 4) {
            return (Ident) this.exprKind_;
        }
        return Ident.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public IdentOrBuilder getIdentExprOrBuilder() {
        if (this.exprKindCase_ == 4) {
            return (Ident) this.exprKind_;
        }
        return Ident.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public Select getSelectExpr() {
        if (this.exprKindCase_ == 5) {
            return (Select) this.exprKind_;
        }
        return Select.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public SelectOrBuilder getSelectExprOrBuilder() {
        if (this.exprKindCase_ == 5) {
            return (Select) this.exprKind_;
        }
        return Select.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public Call getCallExpr() {
        if (this.exprKindCase_ == 6) {
            return (Call) this.exprKind_;
        }
        return Call.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public CallOrBuilder getCallExprOrBuilder() {
        if (this.exprKindCase_ == 6) {
            return (Call) this.exprKind_;
        }
        return Call.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public CreateList getListExpr() {
        if (this.exprKindCase_ == 7) {
            return (CreateList) this.exprKind_;
        }
        return CreateList.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public CreateListOrBuilder getListExprOrBuilder() {
        if (this.exprKindCase_ == 7) {
            return (CreateList) this.exprKind_;
        }
        return CreateList.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public CreateStruct getStructExpr() {
        if (this.exprKindCase_ == 8) {
            return (CreateStruct) this.exprKind_;
        }
        return CreateStruct.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public CreateStructOrBuilder getStructExprOrBuilder() {
        if (this.exprKindCase_ == 8) {
            return (CreateStruct) this.exprKind_;
        }
        return CreateStruct.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public Comprehension getComprehensionExpr() {
        if (this.exprKindCase_ == 9) {
            return (Comprehension) this.exprKind_;
        }
        return Comprehension.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
    public ComprehensionOrBuilder getComprehensionExprOrBuilder() {
        if (this.exprKindCase_ == 9) {
            return (Comprehension) this.exprKind_;
        }
        return Comprehension.getDefaultInstance();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        long j = this.id_;
        if (j != 0) {
            codedOutputStream.writeInt64(2, j);
        }
        if (this.exprKindCase_ == 3) {
            codedOutputStream.writeMessage(3, (Constant) this.exprKind_);
        }
        if (this.exprKindCase_ == 4) {
            codedOutputStream.writeMessage(4, (Ident) this.exprKind_);
        }
        if (this.exprKindCase_ == 5) {
            codedOutputStream.writeMessage(5, (Select) this.exprKind_);
        }
        if (this.exprKindCase_ == 6) {
            codedOutputStream.writeMessage(6, (Call) this.exprKind_);
        }
        if (this.exprKindCase_ == 7) {
            codedOutputStream.writeMessage(7, (CreateList) this.exprKind_);
        }
        if (this.exprKindCase_ == 8) {
            codedOutputStream.writeMessage(8, (CreateStruct) this.exprKind_);
        }
        if (this.exprKindCase_ == 9) {
            codedOutputStream.writeMessage(9, (Comprehension) this.exprKind_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        long j = this.id_;
        int iComputeInt64Size = j != 0 ? CodedOutputStream.computeInt64Size(2, j) : 0;
        if (this.exprKindCase_ == 3) {
            iComputeInt64Size += CodedOutputStream.computeMessageSize(3, (Constant) this.exprKind_);
        }
        if (this.exprKindCase_ == 4) {
            iComputeInt64Size += CodedOutputStream.computeMessageSize(4, (Ident) this.exprKind_);
        }
        if (this.exprKindCase_ == 5) {
            iComputeInt64Size += CodedOutputStream.computeMessageSize(5, (Select) this.exprKind_);
        }
        if (this.exprKindCase_ == 6) {
            iComputeInt64Size += CodedOutputStream.computeMessageSize(6, (Call) this.exprKind_);
        }
        if (this.exprKindCase_ == 7) {
            iComputeInt64Size += CodedOutputStream.computeMessageSize(7, (CreateList) this.exprKind_);
        }
        if (this.exprKindCase_ == 8) {
            iComputeInt64Size += CodedOutputStream.computeMessageSize(8, (CreateStruct) this.exprKind_);
        }
        if (this.exprKindCase_ == 9) {
            iComputeInt64Size += CodedOutputStream.computeMessageSize(9, (Comprehension) this.exprKind_);
        }
        int serializedSize = iComputeInt64Size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Expr)) {
            return super.equals(obj);
        }
        Expr expr = (Expr) obj;
        if (getId() != expr.getId() || !getExprKindCase().equals(expr.getExprKindCase())) {
            return false;
        }
        switch (this.exprKindCase_) {
            case 3:
                if (!getConstExpr().equals(expr.getConstExpr())) {
                    return false;
                }
                break;
            case 4:
                if (!getIdentExpr().equals(expr.getIdentExpr())) {
                    return false;
                }
                break;
            case 5:
                if (!getSelectExpr().equals(expr.getSelectExpr())) {
                    return false;
                }
                break;
            case 6:
                if (!getCallExpr().equals(expr.getCallExpr())) {
                    return false;
                }
                break;
            case 7:
                if (!getListExpr().equals(expr.getListExpr())) {
                    return false;
                }
                break;
            case 8:
                if (!getStructExpr().equals(expr.getStructExpr())) {
                    return false;
                }
                break;
            case 9:
                if (!getComprehensionExpr().equals(expr.getComprehensionExpr())) {
                    return false;
                }
                break;
        }
        return this.unknownFields.equals(expr.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getId());
        switch (this.exprKindCase_) {
            case 3:
                i = ((iHashCode2 * 37) + 3) * 53;
                iHashCode = getConstExpr().hashCode();
                break;
            case 4:
                i = ((iHashCode2 * 37) + 4) * 53;
                iHashCode = getIdentExpr().hashCode();
                break;
            case 5:
                i = ((iHashCode2 * 37) + 5) * 53;
                iHashCode = getSelectExpr().hashCode();
                break;
            case 6:
                i = ((iHashCode2 * 37) + 6) * 53;
                iHashCode = getCallExpr().hashCode();
                break;
            case 7:
                i = ((iHashCode2 * 37) + 7) * 53;
                iHashCode = getListExpr().hashCode();
                break;
            case 8:
                i = ((iHashCode2 * 37) + 8) * 53;
                iHashCode = getStructExpr().hashCode();
                break;
            case 9:
                i = ((iHashCode2 * 37) + 9) * 53;
                iHashCode = getComprehensionExpr().hashCode();
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
    public Builder m10728newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m10731toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum ExprKindCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        CONST_EXPR(3),
        IDENT_EXPR(4),
        SELECT_EXPR(5),
        CALL_EXPR(6),
        LIST_EXPR(7),
        STRUCT_EXPR(8),
        COMPREHENSION_EXPR(9),
        EXPRKIND_NOT_SET(0);

        private final int value;

        ExprKindCase(int i) {
            this.value = i;
        }

        public static ExprKindCase forNumber(int i) {
            if (i == 0) {
                return EXPRKIND_NOT_SET;
            }
            switch (i) {
                case 3:
                    return CONST_EXPR;
                case 4:
                    return IDENT_EXPR;
                case 5:
                    return SELECT_EXPR;
                case 6:
                    return CALL_EXPR;
                case 7:
                    return LIST_EXPR;
                case 8:
                    return STRUCT_EXPR;
                case 9:
                    return COMPREHENSION_EXPR;
                default:
                    return null;
            }
        }

        @Deprecated
        public static ExprKindCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public interface CallOrBuilder extends MessageOrBuilder {
        Expr getArgs(int i);

        int getArgsCount();

        List<Expr> getArgsList();

        ExprOrBuilder getArgsOrBuilder(int i);

        List<? extends ExprOrBuilder> getArgsOrBuilderList();

        String getFunction();

        ByteString getFunctionBytes();

        Expr getTarget();

        ExprOrBuilder getTargetOrBuilder();

        boolean hasTarget();
    }

    public interface ComprehensionOrBuilder extends MessageOrBuilder {
        Expr getAccuInit();

        ExprOrBuilder getAccuInitOrBuilder();

        String getAccuVar();

        ByteString getAccuVarBytes();

        Expr getIterRange();

        ExprOrBuilder getIterRangeOrBuilder();

        String getIterVar();

        ByteString getIterVarBytes();

        Expr getLoopCondition();

        ExprOrBuilder getLoopConditionOrBuilder();

        Expr getLoopStep();

        ExprOrBuilder getLoopStepOrBuilder();

        Expr getResult();

        ExprOrBuilder getResultOrBuilder();

        boolean hasAccuInit();

        boolean hasIterRange();

        boolean hasLoopCondition();

        boolean hasLoopStep();

        boolean hasResult();
    }

    public interface CreateListOrBuilder extends MessageOrBuilder {
        Expr getElements(int i);

        int getElementsCount();

        List<Expr> getElementsList();

        ExprOrBuilder getElementsOrBuilder(int i);

        List<? extends ExprOrBuilder> getElementsOrBuilderList();
    }

    public interface CreateStructOrBuilder extends MessageOrBuilder {
        CreateStruct.Entry getEntries(int i);

        int getEntriesCount();

        List<CreateStruct.Entry> getEntriesList();

        CreateStruct.EntryOrBuilder getEntriesOrBuilder(int i);

        List<? extends CreateStruct.EntryOrBuilder> getEntriesOrBuilderList();

        String getMessageName();

        ByteString getMessageNameBytes();
    }

    public interface IdentOrBuilder extends MessageOrBuilder {
        String getName();

        ByteString getNameBytes();
    }

    public interface SelectOrBuilder extends MessageOrBuilder {
        String getField();

        ByteString getFieldBytes();

        Expr getOperand();

        ExprOrBuilder getOperandOrBuilder();

        boolean getTestOnly();

        boolean hasOperand();
    }

    public static final class Ident extends GeneratedMessageV3 implements IdentOrBuilder {
        public static final int NAME_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final Ident DEFAULT_INSTANCE = new Ident();
        private static final Parser<Ident> PARSER = new AbstractParser<Ident>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Ident.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Ident m11009parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Ident(codedInputStream, extensionRegistryLite);
            }
        };
        private byte memoizedIsInitialized;
        private volatile Object name_;

        private Ident(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Ident() {
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
        }

        private Ident(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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

        public static Ident getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Ident> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Ident_descriptor;
        }

        public static Ident parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Ident) PARSER.parseFrom(byteBuffer);
        }

        public static Ident parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Ident) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Ident parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Ident) PARSER.parseFrom(byteString);
        }

        public static Ident parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Ident) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Ident parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Ident) PARSER.parseFrom(bArr);
        }

        public static Ident parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Ident) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Ident parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Ident parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Ident parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Ident parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Ident parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Ident parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m11007toBuilder();
        }

        public static Builder newBuilder(Ident ident) {
            return DEFAULT_INSTANCE.m11007toBuilder().mergeFrom(ident);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Ident m11002getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Ident> getParserForType() {
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
            return new Ident();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Ident_fieldAccessorTable.ensureFieldAccessorsInitialized(Ident.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.IdentOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.IdentOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (!getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeStringSize;
            return iComputeStringSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Ident)) {
                return super.equals(obj);
            }
            Ident ident = (Ident) obj;
            return getName().equals(ident.getName()) && this.unknownFields.equals(ident.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11004newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11007toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements IdentOrBuilder {
            private Object name_;

            private Builder() {
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Ident_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Ident_fieldAccessorTable.ensureFieldAccessorsInitialized(Ident.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Ident.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11018clear() {
                super.clear();
                this.name_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Ident_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Ident m11031getDefaultInstanceForType() {
                return Ident.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Ident m11012build() throws UninitializedMessageException {
                Ident identM11014buildPartial = m11014buildPartial();
                if (identM11014buildPartial.isInitialized()) {
                    return identM11014buildPartial;
                }
                throw newUninitializedMessageException(identM11014buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Ident m11014buildPartial() {
                Ident ident = new Ident(this);
                ident.name_ = this.name_;
                onBuilt();
                return ident;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11030clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11042setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11020clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11023clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11044setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11010addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11035mergeFrom(Message message) {
                if (message instanceof Ident) {
                    return mergeFrom((Ident) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Ident ident) {
                if (ident == Ident.getDefaultInstance()) {
                    return this;
                }
                if (!ident.getName().isEmpty()) {
                    this.name_ = ident.name_;
                    onChanged();
                }
                m11040mergeUnknownFields(ident.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Ident.Builder m11036mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Ident.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$Ident r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Ident) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$Ident r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Ident) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Ident.Builder.m11036mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$Ident$Builder");
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.IdentOrBuilder
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

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.IdentOrBuilder
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
                Ident.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = Ident.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m11046setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m11040mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Select extends GeneratedMessageV3 implements SelectOrBuilder {
        public static final int FIELD_FIELD_NUMBER = 2;
        public static final int OPERAND_FIELD_NUMBER = 1;
        public static final int TEST_ONLY_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private static final Select DEFAULT_INSTANCE = new Select();
        private static final Parser<Select> PARSER = new AbstractParser<Select>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Select.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Select m11055parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Select(codedInputStream, extensionRegistryLite);
            }
        };
        private volatile Object field_;
        private byte memoizedIsInitialized;
        private Expr operand_;
        private boolean testOnly_;

        private Select(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Select() {
            this.memoizedIsInitialized = (byte) -1;
            this.field_ = "";
        }

        private Select(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                Expr expr = this.operand_;
                                Builder builderM10731toBuilder = expr != null ? expr.m10731toBuilder() : null;
                                Expr expr2 = (Expr) codedInputStream.readMessage(Expr.parser(), extensionRegistryLite);
                                this.operand_ = expr2;
                                if (builderM10731toBuilder != null) {
                                    builderM10731toBuilder.mergeFrom(expr2);
                                    this.operand_ = builderM10731toBuilder.m10738buildPartial();
                                }
                            } else if (tag == 18) {
                                this.field_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 24) {
                                this.testOnly_ = codedInputStream.readBool();
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

        public static Select getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Select> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Select_descriptor;
        }

        public static Select parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Select) PARSER.parseFrom(byteBuffer);
        }

        public static Select parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Select) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Select parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Select) PARSER.parseFrom(byteString);
        }

        public static Select parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Select) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Select parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Select) PARSER.parseFrom(bArr);
        }

        public static Select parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Select) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Select parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Select parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Select parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Select parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Select parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Select parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m11053toBuilder();
        }

        public static Builder newBuilder(Select select) {
            return DEFAULT_INSTANCE.m11053toBuilder().mergeFrom(select);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Select m11048getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Select> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.SelectOrBuilder
        public boolean getTestOnly() {
            return this.testOnly_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.SelectOrBuilder
        public boolean hasOperand() {
            return this.operand_ != null;
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
            return new Select();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Select_fieldAccessorTable.ensureFieldAccessorsInitialized(Select.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.SelectOrBuilder
        public Expr getOperand() {
            Expr expr = this.operand_;
            return expr == null ? Expr.getDefaultInstance() : expr;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.SelectOrBuilder
        public ExprOrBuilder getOperandOrBuilder() {
            return getOperand();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.SelectOrBuilder
        public String getField() {
            Object obj = this.field_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.field_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.SelectOrBuilder
        public ByteString getFieldBytes() {
            Object obj = this.field_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.field_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.operand_ != null) {
                codedOutputStream.writeMessage(1, getOperand());
            }
            if (!getFieldBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.field_);
            }
            boolean z = this.testOnly_;
            if (z) {
                codedOutputStream.writeBool(3, z);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = this.operand_ != null ? CodedOutputStream.computeMessageSize(1, getOperand()) : 0;
            if (!getFieldBytes().isEmpty()) {
                iComputeMessageSize += GeneratedMessageV3.computeStringSize(2, this.field_);
            }
            boolean z = this.testOnly_;
            if (z) {
                iComputeMessageSize += CodedOutputStream.computeBoolSize(3, z);
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Select)) {
                return super.equals(obj);
            }
            Select select = (Select) obj;
            if (hasOperand() != select.hasOperand()) {
                return false;
            }
            return (!hasOperand() || getOperand().equals(select.getOperand())) && getField().equals(select.getField()) && getTestOnly() == select.getTestOnly() && this.unknownFields.equals(select.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasOperand()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getOperand().hashCode();
            }
            int iHashCode2 = (((((((((iHashCode * 37) + 2) * 53) + getField().hashCode()) * 37) + 3) * 53) + Internal.hashBoolean(getTestOnly())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11050newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11053toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SelectOrBuilder {
            private Object field_;
            private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> operandBuilder_;
            private Expr operand_;
            private boolean testOnly_;

            private Builder() {
                this.field_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.field_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Select_descriptor;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.SelectOrBuilder
            public boolean getTestOnly() {
                return this.testOnly_;
            }

            public Builder setTestOnly(boolean z) {
                this.testOnly_ = z;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.SelectOrBuilder
            public boolean hasOperand() {
                return (this.operandBuilder_ == null && this.operand_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Select_fieldAccessorTable.ensureFieldAccessorsInitialized(Select.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Select.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11064clear() {
                super.clear();
                if (this.operandBuilder_ == null) {
                    this.operand_ = null;
                } else {
                    this.operand_ = null;
                    this.operandBuilder_ = null;
                }
                this.field_ = "";
                this.testOnly_ = false;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Select_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Select m11077getDefaultInstanceForType() {
                return Select.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Select m11058build() throws UninitializedMessageException {
                Select selectM11060buildPartial = m11060buildPartial();
                if (selectM11060buildPartial.isInitialized()) {
                    return selectM11060buildPartial;
                }
                throw newUninitializedMessageException(selectM11060buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Select m11060buildPartial() {
                Select select = new Select(this);
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.operandBuilder_;
                if (singleFieldBuilderV3 == null) {
                    select.operand_ = this.operand_;
                } else {
                    select.operand_ = singleFieldBuilderV3.build();
                }
                select.field_ = this.field_;
                select.testOnly_ = this.testOnly_;
                onBuilt();
                return select;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11076clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11088setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11066clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11069clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11090setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11056addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m11081mergeFrom(Message message) {
                if (message instanceof Select) {
                    return mergeFrom((Select) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Select select) {
                if (select == Select.getDefaultInstance()) {
                    return this;
                }
                if (select.hasOperand()) {
                    mergeOperand(select.getOperand());
                }
                if (!select.getField().isEmpty()) {
                    this.field_ = select.field_;
                    onChanged();
                }
                if (select.getTestOnly()) {
                    setTestOnly(select.getTestOnly());
                }
                m11086mergeUnknownFields(select.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Select.Builder m11082mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Select.access$1700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$Select r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Select) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$Select r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Select) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Select.Builder.m11082mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$Select$Builder");
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.SelectOrBuilder
            public Expr getOperand() {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.operandBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Expr expr = this.operand_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            public Builder setOperand(Expr expr) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.operandBuilder_;
                if (singleFieldBuilderV3 == null) {
                    expr.getClass();
                    this.operand_ = expr;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(expr);
                }
                return this;
            }

            public Builder setOperand(Builder builder) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.operandBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.operand_ = builder.m10736build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m10736build());
                }
                return this;
            }

            public Builder mergeOperand(Expr expr) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.operandBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Expr expr2 = this.operand_;
                    if (expr2 != null) {
                        this.operand_ = Expr.newBuilder(expr2).mergeFrom(expr).m10738buildPartial();
                    } else {
                        this.operand_ = expr;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(expr);
                }
                return this;
            }

            public Builder clearOperand() {
                if (this.operandBuilder_ == null) {
                    this.operand_ = null;
                    onChanged();
                } else {
                    this.operand_ = null;
                    this.operandBuilder_ = null;
                }
                return this;
            }

            public Builder getOperandBuilder() {
                onChanged();
                return getOperandFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.SelectOrBuilder
            public ExprOrBuilder getOperandOrBuilder() {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.operandBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (ExprOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Expr expr = this.operand_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> getOperandFieldBuilder() {
                if (this.operandBuilder_ == null) {
                    this.operandBuilder_ = new SingleFieldBuilderV3<>(getOperand(), getParentForChildren(), isClean());
                    this.operand_ = null;
                }
                return this.operandBuilder_;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.SelectOrBuilder
            public String getField() {
                Object obj = this.field_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.field_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setField(String str) {
                str.getClass();
                this.field_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.SelectOrBuilder
            public ByteString getFieldBytes() {
                Object obj = this.field_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.field_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setFieldBytes(ByteString byteString) {
                byteString.getClass();
                Select.checkByteStringIsUtf8(byteString);
                this.field_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearField() {
                this.field_ = Select.getDefaultInstance().getField();
                onChanged();
                return this;
            }

            public Builder clearTestOnly() {
                this.testOnly_ = false;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m11092setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m11086mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Call extends GeneratedMessageV3 implements CallOrBuilder {
        public static final int ARGS_FIELD_NUMBER = 3;
        public static final int FUNCTION_FIELD_NUMBER = 2;
        public static final int TARGET_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final Call DEFAULT_INSTANCE = new Call();
        private static final Parser<Call> PARSER = new AbstractParser<Call>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Call.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Call m10779parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Call(codedInputStream, extensionRegistryLite);
            }
        };
        private List<Expr> args_;
        private volatile Object function_;
        private byte memoizedIsInitialized;
        private Expr target_;

        private Call(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Call() {
            this.memoizedIsInitialized = (byte) -1;
            this.function_ = "";
            this.args_ = Collections.emptyList();
        }

        private Call(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                Expr expr = this.target_;
                                Builder builderM10731toBuilder = expr != null ? expr.m10731toBuilder() : null;
                                Expr expr2 = (Expr) codedInputStream.readMessage(Expr.parser(), extensionRegistryLite);
                                this.target_ = expr2;
                                if (builderM10731toBuilder != null) {
                                    builderM10731toBuilder.mergeFrom(expr2);
                                    this.target_ = builderM10731toBuilder.m10738buildPartial();
                                }
                            } else if (tag == 18) {
                                this.function_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 26) {
                                if (!(z2 & true)) {
                                    this.args_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.args_.add(codedInputStream.readMessage(Expr.parser(), extensionRegistryLite));
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
                        this.args_ = Collections.unmodifiableList(this.args_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static Call getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Call> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Call_descriptor;
        }

        public static Call parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Call) PARSER.parseFrom(byteBuffer);
        }

        public static Call parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Call) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Call parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Call) PARSER.parseFrom(byteString);
        }

        public static Call parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Call) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Call parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Call) PARSER.parseFrom(bArr);
        }

        public static Call parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Call) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Call parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Call parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Call parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Call parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Call parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Call parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m10777toBuilder();
        }

        public static Builder newBuilder(Call call) {
            return DEFAULT_INSTANCE.m10777toBuilder().mergeFrom(call);
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
        public List<Expr> getArgsList() {
            return this.args_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
        public List<? extends ExprOrBuilder> getArgsOrBuilderList() {
            return this.args_;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Call m10772getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Call> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
        public boolean hasTarget() {
            return this.target_ != null;
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
            return new Call();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Call_fieldAccessorTable.ensureFieldAccessorsInitialized(Call.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
        public Expr getTarget() {
            Expr expr = this.target_;
            return expr == null ? Expr.getDefaultInstance() : expr;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
        public ExprOrBuilder getTargetOrBuilder() {
            return getTarget();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
        public String getFunction() {
            Object obj = this.function_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.function_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
        public ByteString getFunctionBytes() {
            Object obj = this.function_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.function_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
        public int getArgsCount() {
            return this.args_.size();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
        public Expr getArgs(int i) {
            return this.args_.get(i);
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
        public ExprOrBuilder getArgsOrBuilder(int i) {
            return this.args_.get(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.target_ != null) {
                codedOutputStream.writeMessage(1, getTarget());
            }
            if (!getFunctionBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.function_);
            }
            for (int i = 0; i < this.args_.size(); i++) {
                codedOutputStream.writeMessage(3, this.args_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = this.target_ != null ? CodedOutputStream.computeMessageSize(1, getTarget()) : 0;
            if (!getFunctionBytes().isEmpty()) {
                iComputeMessageSize += GeneratedMessageV3.computeStringSize(2, this.function_);
            }
            for (int i2 = 0; i2 < this.args_.size(); i2++) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(3, this.args_.get(i2));
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Call)) {
                return super.equals(obj);
            }
            Call call = (Call) obj;
            if (hasTarget() != call.hasTarget()) {
                return false;
            }
            return (!hasTarget() || getTarget().equals(call.getTarget())) && getFunction().equals(call.getFunction()) && getArgsList().equals(call.getArgsList()) && this.unknownFields.equals(call.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasTarget()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getTarget().hashCode();
            }
            int iHashCode2 = (((iHashCode * 37) + 2) * 53) + getFunction().hashCode();
            if (getArgsCount() > 0) {
                iHashCode2 = (((iHashCode2 * 37) + 3) * 53) + getArgsList().hashCode();
            }
            int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode3;
            return iHashCode3;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10774newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10777toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CallOrBuilder {
            private RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> argsBuilder_;
            private List<Expr> args_;
            private int bitField0_;
            private Object function_;
            private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> targetBuilder_;
            private Expr target_;

            private Builder() {
                this.function_ = "";
                this.args_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.function_ = "";
                this.args_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Call_descriptor;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
            public boolean hasTarget() {
                return (this.targetBuilder_ == null && this.target_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Call_fieldAccessorTable.ensureFieldAccessorsInitialized(Call.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (Call.alwaysUseFieldBuilders) {
                    getArgsFieldBuilder();
                }
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10788clear() {
                super.clear();
                if (this.targetBuilder_ == null) {
                    this.target_ = null;
                } else {
                    this.target_ = null;
                    this.targetBuilder_ = null;
                }
                this.function_ = "";
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.args_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Call_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Call m10801getDefaultInstanceForType() {
                return Call.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Call m10782build() throws UninitializedMessageException {
                Call callM10784buildPartial = m10784buildPartial();
                if (callM10784buildPartial.isInitialized()) {
                    return callM10784buildPartial;
                }
                throw newUninitializedMessageException(callM10784buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Call m10784buildPartial() {
                Call call = new Call(this);
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.targetBuilder_;
                if (singleFieldBuilderV3 == null) {
                    call.target_ = this.target_;
                } else {
                    call.target_ = singleFieldBuilderV3.build();
                }
                call.function_ = this.function_;
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 1) != 0) {
                        this.args_ = Collections.unmodifiableList(this.args_);
                        this.bitField0_ &= -2;
                    }
                    call.args_ = this.args_;
                } else {
                    call.args_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return call;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10800clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10812setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10790clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10793clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10814setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10780addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10805mergeFrom(Message message) {
                if (message instanceof Call) {
                    return mergeFrom((Call) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Call call) {
                if (call == Call.getDefaultInstance()) {
                    return this;
                }
                if (call.hasTarget()) {
                    mergeTarget(call.getTarget());
                }
                if (!call.getFunction().isEmpty()) {
                    this.function_ = call.function_;
                    onChanged();
                }
                if (this.argsBuilder_ == null) {
                    if (!call.args_.isEmpty()) {
                        if (this.args_.isEmpty()) {
                            this.args_ = call.args_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureArgsIsMutable();
                            this.args_.addAll(call.args_);
                        }
                        onChanged();
                    }
                } else if (!call.args_.isEmpty()) {
                    if (!this.argsBuilder_.isEmpty()) {
                        this.argsBuilder_.addAllMessages(call.args_);
                    } else {
                        this.argsBuilder_.dispose();
                        this.argsBuilder_ = null;
                        this.args_ = call.args_;
                        this.bitField0_ &= -2;
                        this.argsBuilder_ = Call.alwaysUseFieldBuilders ? getArgsFieldBuilder() : null;
                    }
                }
                m10810mergeUnknownFields(call.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Call.Builder m10806mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Call.access$2900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$Call r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Call) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$Call r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Call) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Call.Builder.m10806mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$Call$Builder");
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
            public Expr getTarget() {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.targetBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Expr expr = this.target_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            public Builder setTarget(Expr expr) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.targetBuilder_;
                if (singleFieldBuilderV3 == null) {
                    expr.getClass();
                    this.target_ = expr;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(expr);
                }
                return this;
            }

            public Builder setTarget(Builder builder) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.targetBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.target_ = builder.m10736build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m10736build());
                }
                return this;
            }

            public Builder mergeTarget(Expr expr) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.targetBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Expr expr2 = this.target_;
                    if (expr2 != null) {
                        this.target_ = Expr.newBuilder(expr2).mergeFrom(expr).m10738buildPartial();
                    } else {
                        this.target_ = expr;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(expr);
                }
                return this;
            }

            public Builder clearTarget() {
                if (this.targetBuilder_ == null) {
                    this.target_ = null;
                    onChanged();
                } else {
                    this.target_ = null;
                    this.targetBuilder_ = null;
                }
                return this;
            }

            public Builder getTargetBuilder() {
                onChanged();
                return getTargetFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
            public ExprOrBuilder getTargetOrBuilder() {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.targetBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (ExprOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Expr expr = this.target_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> getTargetFieldBuilder() {
                if (this.targetBuilder_ == null) {
                    this.targetBuilder_ = new SingleFieldBuilderV3<>(getTarget(), getParentForChildren(), isClean());
                    this.target_ = null;
                }
                return this.targetBuilder_;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
            public String getFunction() {
                Object obj = this.function_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.function_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setFunction(String str) {
                str.getClass();
                this.function_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
            public ByteString getFunctionBytes() {
                Object obj = this.function_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.function_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setFunctionBytes(ByteString byteString) {
                byteString.getClass();
                Call.checkByteStringIsUtf8(byteString);
                this.function_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearFunction() {
                this.function_ = Call.getDefaultInstance().getFunction();
                onChanged();
                return this;
            }

            private void ensureArgsIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.args_ = new ArrayList(this.args_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
            public List<Expr> getArgsList() {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.args_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
            public int getArgsCount() {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.args_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
            public Expr getArgs(int i) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.args_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setArgs(int i, Expr expr) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    expr.getClass();
                    ensureArgsIsMutable();
                    this.args_.set(i, expr);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, expr);
                }
                return this;
            }

            public Builder setArgs(int i, Builder builder) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureArgsIsMutable();
                    this.args_.set(i, builder.m10736build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.m10736build());
                }
                return this;
            }

            public Builder addArgs(Expr expr) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    expr.getClass();
                    ensureArgsIsMutable();
                    this.args_.add(expr);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(expr);
                }
                return this;
            }

            public Builder addArgs(int i, Expr expr) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    expr.getClass();
                    ensureArgsIsMutable();
                    this.args_.add(i, expr);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, expr);
                }
                return this;
            }

            public Builder addArgs(Builder builder) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureArgsIsMutable();
                    this.args_.add(builder.m10736build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.m10736build());
                }
                return this;
            }

            public Builder addArgs(int i, Builder builder) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureArgsIsMutable();
                    this.args_.add(i, builder.m10736build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.m10736build());
                }
                return this;
            }

            public Builder addAllArgs(Iterable<? extends Expr> iterable) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureArgsIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.args_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearArgs() {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.args_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeArgs(int i) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureArgsIsMutable();
                    this.args_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Builder getArgsBuilder(int i) {
                return getArgsFieldBuilder().getBuilder(i);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
            public ExprOrBuilder getArgsOrBuilder(int i) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.args_.get(i);
                }
                return (ExprOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CallOrBuilder
            public List<? extends ExprOrBuilder> getArgsOrBuilderList() {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.argsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.args_);
            }

            public Builder addArgsBuilder() {
                return getArgsFieldBuilder().addBuilder(Expr.getDefaultInstance());
            }

            public Builder addArgsBuilder(int i) {
                return getArgsFieldBuilder().addBuilder(i, Expr.getDefaultInstance());
            }

            public List<Builder> getArgsBuilderList() {
                return getArgsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> getArgsFieldBuilder() {
                if (this.argsBuilder_ == null) {
                    this.argsBuilder_ = new RepeatedFieldBuilderV3<>(this.args_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.args_ = null;
                }
                return this.argsBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m10816setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m10810mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class CreateList extends GeneratedMessageV3 implements CreateListOrBuilder {
        public static final int ELEMENTS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final CreateList DEFAULT_INSTANCE = new CreateList();
        private static final Parser<CreateList> PARSER = new AbstractParser<CreateList>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateList.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public CreateList m10871parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CreateList(codedInputStream, extensionRegistryLite);
            }
        };
        private List<Expr> elements_;
        private byte memoizedIsInitialized;

        private CreateList(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CreateList() {
            this.memoizedIsInitialized = (byte) -1;
            this.elements_ = Collections.emptyList();
        }

        private CreateList(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.elements_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.elements_.add(codedInputStream.readMessage(Expr.parser(), extensionRegistryLite));
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
                        this.elements_ = Collections.unmodifiableList(this.elements_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static CreateList getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CreateList> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateList_descriptor;
        }

        public static CreateList parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CreateList) PARSER.parseFrom(byteBuffer);
        }

        public static CreateList parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CreateList) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CreateList parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CreateList) PARSER.parseFrom(byteString);
        }

        public static CreateList parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CreateList) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CreateList parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CreateList) PARSER.parseFrom(bArr);
        }

        public static CreateList parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CreateList) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CreateList parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CreateList parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CreateList parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CreateList parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CreateList parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CreateList parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m10869toBuilder();
        }

        public static Builder newBuilder(CreateList createList) {
            return DEFAULT_INSTANCE.m10869toBuilder().mergeFrom(createList);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CreateList m10864getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateListOrBuilder
        public List<Expr> getElementsList() {
            return this.elements_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateListOrBuilder
        public List<? extends ExprOrBuilder> getElementsOrBuilderList() {
            return this.elements_;
        }

        public Parser<CreateList> getParserForType() {
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
            return new CreateList();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateList_fieldAccessorTable.ensureFieldAccessorsInitialized(CreateList.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateListOrBuilder
        public int getElementsCount() {
            return this.elements_.size();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateListOrBuilder
        public Expr getElements(int i) {
            return this.elements_.get(i);
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateListOrBuilder
        public ExprOrBuilder getElementsOrBuilder(int i) {
            return this.elements_.get(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.elements_.size(); i++) {
                codedOutputStream.writeMessage(1, this.elements_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = 0;
            for (int i2 = 0; i2 < this.elements_.size(); i2++) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.elements_.get(i2));
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CreateList)) {
                return super.equals(obj);
            }
            CreateList createList = (CreateList) obj;
            return getElementsList().equals(createList.getElementsList()) && this.unknownFields.equals(createList.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (getElementsCount() > 0) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getElementsList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10866newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10869toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CreateListOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> elementsBuilder_;
            private List<Expr> elements_;

            private Builder() {
                this.elements_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.elements_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateList_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateList_fieldAccessorTable.ensureFieldAccessorsInitialized(CreateList.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (CreateList.alwaysUseFieldBuilders) {
                    getElementsFieldBuilder();
                }
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10880clear() {
                super.clear();
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.elements_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateList_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CreateList m10893getDefaultInstanceForType() {
                return CreateList.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CreateList m10874build() throws UninitializedMessageException {
                CreateList createListM10876buildPartial = m10876buildPartial();
                if (createListM10876buildPartial.isInitialized()) {
                    return createListM10876buildPartial;
                }
                throw newUninitializedMessageException(createListM10876buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CreateList m10876buildPartial() {
                CreateList createList = new CreateList(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.elements_ = Collections.unmodifiableList(this.elements_);
                        this.bitField0_ &= -2;
                    }
                    createList.elements_ = this.elements_;
                } else {
                    createList.elements_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return createList;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10892clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10904setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10882clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10885clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10906setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10872addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10897mergeFrom(Message message) {
                if (message instanceof CreateList) {
                    return mergeFrom((CreateList) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CreateList createList) {
                if (createList == CreateList.getDefaultInstance()) {
                    return this;
                }
                if (this.elementsBuilder_ == null) {
                    if (!createList.elements_.isEmpty()) {
                        if (this.elements_.isEmpty()) {
                            this.elements_ = createList.elements_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureElementsIsMutable();
                            this.elements_.addAll(createList.elements_);
                        }
                        onChanged();
                    }
                } else if (!createList.elements_.isEmpty()) {
                    if (!this.elementsBuilder_.isEmpty()) {
                        this.elementsBuilder_.addAllMessages(createList.elements_);
                    } else {
                        this.elementsBuilder_.dispose();
                        this.elementsBuilder_ = null;
                        this.elements_ = createList.elements_;
                        this.bitField0_ &= -2;
                        this.elementsBuilder_ = CreateList.alwaysUseFieldBuilders ? getElementsFieldBuilder() : null;
                    }
                }
                m10902mergeUnknownFields(createList.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateList.Builder m10898mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateList.access$3900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$CreateList r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateList) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$CreateList r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateList) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateList.Builder.m10898mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$CreateList$Builder");
            }

            private void ensureElementsIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.elements_ = new ArrayList(this.elements_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateListOrBuilder
            public List<Expr> getElementsList() {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.elements_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateListOrBuilder
            public int getElementsCount() {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.elements_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateListOrBuilder
            public Expr getElements(int i) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.elements_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setElements(int i, Expr expr) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    expr.getClass();
                    ensureElementsIsMutable();
                    this.elements_.set(i, expr);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, expr);
                }
                return this;
            }

            public Builder setElements(int i, Builder builder) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureElementsIsMutable();
                    this.elements_.set(i, builder.m10736build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.m10736build());
                }
                return this;
            }

            public Builder addElements(Expr expr) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    expr.getClass();
                    ensureElementsIsMutable();
                    this.elements_.add(expr);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(expr);
                }
                return this;
            }

            public Builder addElements(int i, Expr expr) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    expr.getClass();
                    ensureElementsIsMutable();
                    this.elements_.add(i, expr);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, expr);
                }
                return this;
            }

            public Builder addElements(Builder builder) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureElementsIsMutable();
                    this.elements_.add(builder.m10736build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.m10736build());
                }
                return this;
            }

            public Builder addElements(int i, Builder builder) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureElementsIsMutable();
                    this.elements_.add(i, builder.m10736build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.m10736build());
                }
                return this;
            }

            public Builder addAllElements(Iterable<? extends Expr> iterable) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureElementsIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.elements_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearElements() {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.elements_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeElements(int i) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureElementsIsMutable();
                    this.elements_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Builder getElementsBuilder(int i) {
                return getElementsFieldBuilder().getBuilder(i);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateListOrBuilder
            public ExprOrBuilder getElementsOrBuilder(int i) {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.elements_.get(i);
                }
                return (ExprOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateListOrBuilder
            public List<? extends ExprOrBuilder> getElementsOrBuilderList() {
                RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> repeatedFieldBuilderV3 = this.elementsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.elements_);
            }

            public Builder addElementsBuilder() {
                return getElementsFieldBuilder().addBuilder(Expr.getDefaultInstance());
            }

            public Builder addElementsBuilder(int i) {
                return getElementsFieldBuilder().addBuilder(i, Expr.getDefaultInstance());
            }

            public List<Builder> getElementsBuilderList() {
                return getElementsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Expr, Builder, ExprOrBuilder> getElementsFieldBuilder() {
                if (this.elementsBuilder_ == null) {
                    this.elementsBuilder_ = new RepeatedFieldBuilderV3<>(this.elements_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.elements_ = null;
                }
                return this.elementsBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m10908setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m10902mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class CreateStruct extends GeneratedMessageV3 implements CreateStructOrBuilder {
        public static final int ENTRIES_FIELD_NUMBER = 2;
        public static final int MESSAGE_NAME_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final CreateStruct DEFAULT_INSTANCE = new CreateStruct();
        private static final Parser<CreateStruct> PARSER = new AbstractParser<CreateStruct>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public CreateStruct m10917parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CreateStruct(codedInputStream, extensionRegistryLite);
            }
        };
        private List<Entry> entries_;
        private byte memoizedIsInitialized;
        private volatile Object messageName_;

        private CreateStruct(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CreateStruct() {
            this.memoizedIsInitialized = (byte) -1;
            this.messageName_ = "";
            this.entries_ = Collections.emptyList();
        }

        private CreateStruct(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.messageName_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                if (!(z2 & true)) {
                                    this.entries_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.entries_.add(codedInputStream.readMessage(Entry.parser(), extensionRegistryLite));
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
                    if (z2 & true) {
                        this.entries_ = Collections.unmodifiableList(this.entries_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static CreateStruct getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CreateStruct> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateStruct_descriptor;
        }

        public static CreateStruct parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CreateStruct) PARSER.parseFrom(byteBuffer);
        }

        public static CreateStruct parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CreateStruct) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CreateStruct parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CreateStruct) PARSER.parseFrom(byteString);
        }

        public static CreateStruct parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CreateStruct) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CreateStruct parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CreateStruct) PARSER.parseFrom(bArr);
        }

        public static CreateStruct parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CreateStruct) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CreateStruct parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CreateStruct parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CreateStruct parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CreateStruct parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CreateStruct parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CreateStruct parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m10915toBuilder();
        }

        public static Builder newBuilder(CreateStruct createStruct) {
            return DEFAULT_INSTANCE.m10915toBuilder().mergeFrom(createStruct);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CreateStruct m10910getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStructOrBuilder
        public List<Entry> getEntriesList() {
            return this.entries_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStructOrBuilder
        public List<? extends EntryOrBuilder> getEntriesOrBuilderList() {
            return this.entries_;
        }

        public Parser<CreateStruct> getParserForType() {
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
            return new CreateStruct();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateStruct_fieldAccessorTable.ensureFieldAccessorsInitialized(CreateStruct.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStructOrBuilder
        public String getMessageName() {
            Object obj = this.messageName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.messageName_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStructOrBuilder
        public ByteString getMessageNameBytes() {
            Object obj = this.messageName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.messageName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStructOrBuilder
        public int getEntriesCount() {
            return this.entries_.size();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStructOrBuilder
        public Entry getEntries(int i) {
            return this.entries_.get(i);
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStructOrBuilder
        public EntryOrBuilder getEntriesOrBuilder(int i) {
            return this.entries_.get(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getMessageNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.messageName_);
            }
            for (int i = 0; i < this.entries_.size(); i++) {
                codedOutputStream.writeMessage(2, this.entries_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getMessageNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.messageName_) : 0;
            for (int i2 = 0; i2 < this.entries_.size(); i2++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(2, this.entries_.get(i2));
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CreateStruct)) {
                return super.equals(obj);
            }
            CreateStruct createStruct = (CreateStruct) obj;
            return getMessageName().equals(createStruct.getMessageName()) && getEntriesList().equals(createStruct.getEntriesList()) && this.unknownFields.equals(createStruct.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getMessageName().hashCode();
            if (getEntriesCount() > 0) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getEntriesList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10912newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10915toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public interface EntryOrBuilder extends MessageOrBuilder {
            String getFieldKey();

            ByteString getFieldKeyBytes();

            long getId();

            Entry.KeyKindCase getKeyKindCase();

            Expr getMapKey();

            ExprOrBuilder getMapKeyOrBuilder();

            Expr getValue();

            ExprOrBuilder getValueOrBuilder();

            boolean hasMapKey();

            boolean hasValue();
        }

        public static final class Entry extends GeneratedMessageV3 implements EntryOrBuilder {
            public static final int FIELD_KEY_FIELD_NUMBER = 2;
            public static final int ID_FIELD_NUMBER = 1;
            public static final int MAP_KEY_FIELD_NUMBER = 3;
            public static final int VALUE_FIELD_NUMBER = 4;
            private static final long serialVersionUID = 0;
            private static final Entry DEFAULT_INSTANCE = new Entry();
            private static final Parser<Entry> PARSER = new AbstractParser<Entry>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.Entry.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public Entry m10963parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Entry(codedInputStream, extensionRegistryLite);
                }
            };
            private long id_;
            private int keyKindCase_;
            private Object keyKind_;
            private byte memoizedIsInitialized;
            private Expr value_;

            private Entry(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.keyKindCase_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            private Entry() {
                this.keyKindCase_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            private Entry(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                Builder builderM10731toBuilder;
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 8) {
                                    this.id_ = codedInputStream.readInt64();
                                } else if (tag != 18) {
                                    if (tag == 26) {
                                        builderM10731toBuilder = this.keyKindCase_ == 3 ? ((Expr) this.keyKind_).m10731toBuilder() : null;
                                        MessageLite message = codedInputStream.readMessage(Expr.parser(), extensionRegistryLite);
                                        this.keyKind_ = message;
                                        if (builderM10731toBuilder != null) {
                                            builderM10731toBuilder.mergeFrom((Expr) message);
                                            this.keyKind_ = builderM10731toBuilder.m10738buildPartial();
                                        }
                                        this.keyKindCase_ = 3;
                                    } else if (tag == 34) {
                                        Expr expr = this.value_;
                                        builderM10731toBuilder = expr != null ? expr.m10731toBuilder() : null;
                                        Expr expr2 = (Expr) codedInputStream.readMessage(Expr.parser(), extensionRegistryLite);
                                        this.value_ = expr2;
                                        if (builderM10731toBuilder != null) {
                                            builderM10731toBuilder.mergeFrom(expr2);
                                            this.value_ = builderM10731toBuilder.m10738buildPartial();
                                        }
                                    } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    }
                                } else {
                                    String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                    this.keyKindCase_ = 2;
                                    this.keyKind_ = stringRequireUtf8;
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

            public static Entry getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Entry> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateStruct_Entry_descriptor;
            }

            public static Entry parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Entry) PARSER.parseFrom(byteBuffer);
            }

            public static Entry parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Entry) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Entry parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Entry) PARSER.parseFrom(byteString);
            }

            public static Entry parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Entry) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Entry parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Entry) PARSER.parseFrom(bArr);
            }

            public static Entry parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Entry) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Entry parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Entry parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Entry parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Entry parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Entry parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Entry parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m10961toBuilder();
            }

            public static Builder newBuilder(Entry entry) {
                return DEFAULT_INSTANCE.m10961toBuilder().mergeFrom(entry);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Entry m10956getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
            public long getId() {
                return this.id_;
            }

            public Parser<Entry> getParserForType() {
                return PARSER;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
            public boolean hasMapKey() {
                return this.keyKindCase_ == 3;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
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
                return new Entry();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateStruct_Entry_fieldAccessorTable.ensureFieldAccessorsInitialized(Entry.class, Builder.class);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
            public KeyKindCase getKeyKindCase() {
                return KeyKindCase.forNumber(this.keyKindCase_);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
            public String getFieldKey() {
                String str = this.keyKindCase_ == 2 ? this.keyKind_ : "";
                if (str instanceof String) {
                    return (String) str;
                }
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.keyKindCase_ == 2) {
                    this.keyKind_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
            public ByteString getFieldKeyBytes() {
                String str = this.keyKindCase_ == 2 ? this.keyKind_ : "";
                if (str instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                    if (this.keyKindCase_ == 2) {
                        this.keyKind_ = byteStringCopyFromUtf8;
                    }
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) str;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
            public Expr getMapKey() {
                if (this.keyKindCase_ == 3) {
                    return (Expr) this.keyKind_;
                }
                return Expr.getDefaultInstance();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
            public ExprOrBuilder getMapKeyOrBuilder() {
                if (this.keyKindCase_ == 3) {
                    return (Expr) this.keyKind_;
                }
                return Expr.getDefaultInstance();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
            public Expr getValue() {
                Expr expr = this.value_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
            public ExprOrBuilder getValueOrBuilder() {
                return getValue();
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                long j = this.id_;
                if (j != 0) {
                    codedOutputStream.writeInt64(1, j);
                }
                if (this.keyKindCase_ == 2) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.keyKind_);
                }
                if (this.keyKindCase_ == 3) {
                    codedOutputStream.writeMessage(3, (Expr) this.keyKind_);
                }
                if (this.value_ != null) {
                    codedOutputStream.writeMessage(4, getValue());
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                long j = this.id_;
                int iComputeInt64Size = j != 0 ? CodedOutputStream.computeInt64Size(1, j) : 0;
                if (this.keyKindCase_ == 2) {
                    iComputeInt64Size += GeneratedMessageV3.computeStringSize(2, this.keyKind_);
                }
                if (this.keyKindCase_ == 3) {
                    iComputeInt64Size += CodedOutputStream.computeMessageSize(3, (Expr) this.keyKind_);
                }
                if (this.value_ != null) {
                    iComputeInt64Size += CodedOutputStream.computeMessageSize(4, getValue());
                }
                int serializedSize = iComputeInt64Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Entry)) {
                    return super.equals(obj);
                }
                Entry entry = (Entry) obj;
                if (getId() != entry.getId() || hasValue() != entry.hasValue()) {
                    return false;
                }
                if ((hasValue() && !getValue().equals(entry.getValue())) || !getKeyKindCase().equals(entry.getKeyKindCase())) {
                    return false;
                }
                int i = this.keyKindCase_;
                if (i == 2) {
                    if (!getFieldKey().equals(entry.getFieldKey())) {
                        return false;
                    }
                } else if (i == 3 && !getMapKey().equals(entry.getMapKey())) {
                    return false;
                }
                return this.unknownFields.equals(entry.unknownFields);
            }

            public int hashCode() {
                int i;
                int iHashCode;
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(getId());
                if (hasValue()) {
                    iHashCode2 = (((iHashCode2 * 37) + 4) * 53) + getValue().hashCode();
                }
                int i2 = this.keyKindCase_;
                if (i2 == 2) {
                    i = ((iHashCode2 * 37) + 2) * 53;
                    iHashCode = getFieldKey().hashCode();
                } else {
                    if (i2 == 3) {
                        i = ((iHashCode2 * 37) + 3) * 53;
                        iHashCode = getMapKey().hashCode();
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
            public Builder m10958newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10961toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public enum KeyKindCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
                FIELD_KEY(2),
                MAP_KEY(3),
                KEYKIND_NOT_SET(0);

                private final int value;

                KeyKindCase(int i) {
                    this.value = i;
                }

                public static KeyKindCase forNumber(int i) {
                    if (i == 0) {
                        return KEYKIND_NOT_SET;
                    }
                    if (i == 2) {
                        return FIELD_KEY;
                    }
                    if (i != 3) {
                        return null;
                    }
                    return MAP_KEY;
                }

                @Deprecated
                public static KeyKindCase valueOf(int i) {
                    return forNumber(i);
                }

                public int getNumber() {
                    return this.value;
                }
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EntryOrBuilder {
                private long id_;
                private int keyKindCase_;
                private Object keyKind_;
                private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> mapKeyBuilder_;
                private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> valueBuilder_;
                private Expr value_;

                private Builder() {
                    this.keyKindCase_ = 0;
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.keyKindCase_ = 0;
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateStruct_Entry_descriptor;
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
                public long getId() {
                    return this.id_;
                }

                public Builder setId(long j) {
                    this.id_ = j;
                    onChanged();
                    return this;
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
                public boolean hasMapKey() {
                    return this.keyKindCase_ == 3;
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
                public boolean hasValue() {
                    return (this.valueBuilder_ == null && this.value_ == null) ? false : true;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateStruct_Entry_fieldAccessorTable.ensureFieldAccessorsInitialized(Entry.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = Entry.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10972clear() {
                    super.clear();
                    this.id_ = 0L;
                    if (this.valueBuilder_ == null) {
                        this.value_ = null;
                    } else {
                        this.value_ = null;
                        this.valueBuilder_ = null;
                    }
                    this.keyKindCase_ = 0;
                    this.keyKind_ = null;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateStruct_Entry_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Entry m10985getDefaultInstanceForType() {
                    return Entry.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Entry m10966build() throws UninitializedMessageException {
                    Entry entryM10968buildPartial = m10968buildPartial();
                    if (entryM10968buildPartial.isInitialized()) {
                        return entryM10968buildPartial;
                    }
                    throw newUninitializedMessageException(entryM10968buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Entry m10968buildPartial() {
                    Entry entry = new Entry(this);
                    entry.id_ = this.id_;
                    if (this.keyKindCase_ == 2) {
                        entry.keyKind_ = this.keyKind_;
                    }
                    if (this.keyKindCase_ == 3) {
                        SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.mapKeyBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            entry.keyKind_ = this.keyKind_;
                        } else {
                            entry.keyKind_ = singleFieldBuilderV3.build();
                        }
                    }
                    SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV32 = this.valueBuilder_;
                    if (singleFieldBuilderV32 == null) {
                        entry.value_ = this.value_;
                    } else {
                        entry.value_ = singleFieldBuilderV32.build();
                    }
                    entry.keyKindCase_ = this.keyKindCase_;
                    onBuilt();
                    return entry;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10984clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10996setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10974clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10977clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10998setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10964addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m10989mergeFrom(Message message) {
                    if (message instanceof Entry) {
                        return mergeFrom((Entry) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Entry entry) {
                    if (entry == Entry.getDefaultInstance()) {
                        return this;
                    }
                    if (entry.getId() != 0) {
                        setId(entry.getId());
                    }
                    if (entry.hasValue()) {
                        mergeValue(entry.getValue());
                    }
                    int i = AnonymousClass2.$SwitchMap$com$google$api$expr$v1alpha1$Expr$CreateStruct$Entry$KeyKindCase[entry.getKeyKindCase().ordinal()];
                    if (i == 1) {
                        this.keyKindCase_ = 2;
                        this.keyKind_ = entry.keyKind_;
                        onChanged();
                    } else if (i == 2) {
                        mergeMapKey(entry.getMapKey());
                    }
                    m10994mergeUnknownFields(entry.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.Entry.Builder m10990mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.Entry.access$5000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$CreateStruct$Entry r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.Entry) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$CreateStruct$Entry r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.Entry) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.Entry.Builder.m10990mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$CreateStruct$Entry$Builder");
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
                public KeyKindCase getKeyKindCase() {
                    return KeyKindCase.forNumber(this.keyKindCase_);
                }

                public Builder clearKeyKind() {
                    this.keyKindCase_ = 0;
                    this.keyKind_ = null;
                    onChanged();
                    return this;
                }

                public Builder clearId() {
                    this.id_ = 0L;
                    onChanged();
                    return this;
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
                public String getFieldKey() {
                    String str = this.keyKindCase_ == 2 ? this.keyKind_ : "";
                    if (!(str instanceof String)) {
                        String stringUtf8 = ((ByteString) str).toStringUtf8();
                        if (this.keyKindCase_ == 2) {
                            this.keyKind_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) str;
                }

                public Builder setFieldKey(String str) {
                    str.getClass();
                    this.keyKindCase_ = 2;
                    this.keyKind_ = str;
                    onChanged();
                    return this;
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
                public ByteString getFieldKeyBytes() {
                    String str = this.keyKindCase_ == 2 ? this.keyKind_ : "";
                    if (str instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                        if (this.keyKindCase_ == 2) {
                            this.keyKind_ = byteStringCopyFromUtf8;
                        }
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) str;
                }

                public Builder setFieldKeyBytes(ByteString byteString) {
                    byteString.getClass();
                    Entry.checkByteStringIsUtf8(byteString);
                    this.keyKindCase_ = 2;
                    this.keyKind_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearFieldKey() {
                    if (this.keyKindCase_ == 2) {
                        this.keyKindCase_ = 0;
                        this.keyKind_ = null;
                        onChanged();
                    }
                    return this;
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
                public Expr getMapKey() {
                    SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.mapKeyBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.keyKindCase_ == 3) {
                            return (Expr) this.keyKind_;
                        }
                        return Expr.getDefaultInstance();
                    }
                    if (this.keyKindCase_ == 3) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    return Expr.getDefaultInstance();
                }

                public Builder setMapKey(Expr expr) {
                    SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.mapKeyBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        expr.getClass();
                        this.keyKind_ = expr;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(expr);
                    }
                    this.keyKindCase_ = 3;
                    return this;
                }

                public Builder setMapKey(Builder builder) {
                    SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.mapKeyBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.keyKind_ = builder.m10736build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m10736build());
                    }
                    this.keyKindCase_ = 3;
                    return this;
                }

                public Builder mergeMapKey(Expr expr) {
                    SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.mapKeyBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.keyKindCase_ != 3 || this.keyKind_ == Expr.getDefaultInstance()) {
                            this.keyKind_ = expr;
                        } else {
                            this.keyKind_ = Expr.newBuilder((Expr) this.keyKind_).mergeFrom(expr).m10738buildPartial();
                        }
                        onChanged();
                    } else {
                        if (this.keyKindCase_ == 3) {
                            singleFieldBuilderV3.mergeFrom(expr);
                        }
                        this.mapKeyBuilder_.setMessage(expr);
                    }
                    this.keyKindCase_ = 3;
                    return this;
                }

                public Builder clearMapKey() {
                    SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.mapKeyBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        if (this.keyKindCase_ == 3) {
                            this.keyKindCase_ = 0;
                            this.keyKind_ = null;
                        }
                        singleFieldBuilderV3.clear();
                    } else if (this.keyKindCase_ == 3) {
                        this.keyKindCase_ = 0;
                        this.keyKind_ = null;
                        onChanged();
                    }
                    return this;
                }

                public Builder getMapKeyBuilder() {
                    return getMapKeyFieldBuilder().getBuilder();
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
                public ExprOrBuilder getMapKeyOrBuilder() {
                    SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3;
                    int i = this.keyKindCase_;
                    if (i == 3 && (singleFieldBuilderV3 = this.mapKeyBuilder_) != null) {
                        return (ExprOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    if (i == 3) {
                        return (Expr) this.keyKind_;
                    }
                    return Expr.getDefaultInstance();
                }

                private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> getMapKeyFieldBuilder() {
                    if (this.mapKeyBuilder_ == null) {
                        if (this.keyKindCase_ != 3) {
                            this.keyKind_ = Expr.getDefaultInstance();
                        }
                        this.mapKeyBuilder_ = new SingleFieldBuilderV3<>((Expr) this.keyKind_, getParentForChildren(), isClean());
                        this.keyKind_ = null;
                    }
                    this.keyKindCase_ = 3;
                    onChanged();
                    return this.mapKeyBuilder_;
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
                public Expr getValue() {
                    SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    Expr expr = this.value_;
                    return expr == null ? Expr.getDefaultInstance() : expr;
                }

                public Builder setValue(Expr expr) {
                    SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        expr.getClass();
                        this.value_ = expr;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(expr);
                    }
                    return this;
                }

                public Builder setValue(Builder builder) {
                    SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.value_ = builder.m10736build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m10736build());
                    }
                    return this;
                }

                public Builder mergeValue(Expr expr) {
                    SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        Expr expr2 = this.value_;
                        if (expr2 != null) {
                            this.value_ = Expr.newBuilder(expr2).mergeFrom(expr).m10738buildPartial();
                        } else {
                            this.value_ = expr;
                        }
                        onChanged();
                    } else {
                        singleFieldBuilderV3.mergeFrom(expr);
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

                public Builder getValueBuilder() {
                    onChanged();
                    return getValueFieldBuilder().getBuilder();
                }

                @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.EntryOrBuilder
                public ExprOrBuilder getValueOrBuilder() {
                    SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return (ExprOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    Expr expr = this.value_;
                    return expr == null ? Expr.getDefaultInstance() : expr;
                }

                private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> getValueFieldBuilder() {
                    if (this.valueBuilder_ == null) {
                        this.valueBuilder_ = new SingleFieldBuilderV3<>(getValue(), getParentForChildren(), isClean());
                        this.value_ = null;
                    }
                    return this.valueBuilder_;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m11000setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m10994mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CreateStructOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> entriesBuilder_;
            private List<Entry> entries_;
            private Object messageName_;

            private Builder() {
                this.messageName_ = "";
                this.entries_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.messageName_ = "";
                this.entries_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateStruct_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateStruct_fieldAccessorTable.ensureFieldAccessorsInitialized(CreateStruct.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (CreateStruct.alwaysUseFieldBuilders) {
                    getEntriesFieldBuilder();
                }
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10926clear() {
                super.clear();
                this.messageName_ = "";
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.entries_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_CreateStruct_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CreateStruct m10939getDefaultInstanceForType() {
                return CreateStruct.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CreateStruct m10920build() throws UninitializedMessageException {
                CreateStruct createStructM10922buildPartial = m10922buildPartial();
                if (createStructM10922buildPartial.isInitialized()) {
                    return createStructM10922buildPartial;
                }
                throw newUninitializedMessageException(createStructM10922buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CreateStruct m10922buildPartial() {
                CreateStruct createStruct = new CreateStruct(this);
                createStruct.messageName_ = this.messageName_;
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 1) != 0) {
                        this.entries_ = Collections.unmodifiableList(this.entries_);
                        this.bitField0_ &= -2;
                    }
                    createStruct.entries_ = this.entries_;
                } else {
                    createStruct.entries_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return createStruct;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10938clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10950setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10928clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10931clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10952setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10918addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10943mergeFrom(Message message) {
                if (message instanceof CreateStruct) {
                    return mergeFrom((CreateStruct) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CreateStruct createStruct) {
                if (createStruct == CreateStruct.getDefaultInstance()) {
                    return this;
                }
                if (!createStruct.getMessageName().isEmpty()) {
                    this.messageName_ = createStruct.messageName_;
                    onChanged();
                }
                if (this.entriesBuilder_ == null) {
                    if (!createStruct.entries_.isEmpty()) {
                        if (this.entries_.isEmpty()) {
                            this.entries_ = createStruct.entries_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureEntriesIsMutable();
                            this.entries_.addAll(createStruct.entries_);
                        }
                        onChanged();
                    }
                } else if (!createStruct.entries_.isEmpty()) {
                    if (!this.entriesBuilder_.isEmpty()) {
                        this.entriesBuilder_.addAllMessages(createStruct.entries_);
                    } else {
                        this.entriesBuilder_.dispose();
                        this.entriesBuilder_ = null;
                        this.entries_ = createStruct.entries_;
                        this.bitField0_ &= -2;
                        this.entriesBuilder_ = CreateStruct.alwaysUseFieldBuilders ? getEntriesFieldBuilder() : null;
                    }
                }
                m10948mergeUnknownFields(createStruct.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.Builder m10944mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.access$6100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$CreateStruct r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$CreateStruct r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStruct.Builder.m10944mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$CreateStruct$Builder");
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStructOrBuilder
            public String getMessageName() {
                Object obj = this.messageName_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.messageName_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setMessageName(String str) {
                str.getClass();
                this.messageName_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStructOrBuilder
            public ByteString getMessageNameBytes() {
                Object obj = this.messageName_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.messageName_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMessageNameBytes(ByteString byteString) {
                byteString.getClass();
                CreateStruct.checkByteStringIsUtf8(byteString);
                this.messageName_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearMessageName() {
                this.messageName_ = CreateStruct.getDefaultInstance().getMessageName();
                onChanged();
                return this;
            }

            private void ensureEntriesIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.entries_ = new ArrayList(this.entries_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStructOrBuilder
            public List<Entry> getEntriesList() {
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.entries_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStructOrBuilder
            public int getEntriesCount() {
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.entries_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStructOrBuilder
            public Entry getEntries(int i) {
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.entries_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setEntries(int i, Entry entry) {
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    entry.getClass();
                    ensureEntriesIsMutable();
                    this.entries_.set(i, entry);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, entry);
                }
                return this;
            }

            public Builder setEntries(int i, Entry.Builder builder) {
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureEntriesIsMutable();
                    this.entries_.set(i, builder.m10966build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.m10966build());
                }
                return this;
            }

            public Builder addEntries(Entry entry) {
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    entry.getClass();
                    ensureEntriesIsMutable();
                    this.entries_.add(entry);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(entry);
                }
                return this;
            }

            public Builder addEntries(int i, Entry entry) {
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    entry.getClass();
                    ensureEntriesIsMutable();
                    this.entries_.add(i, entry);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, entry);
                }
                return this;
            }

            public Builder addEntries(Entry.Builder builder) {
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureEntriesIsMutable();
                    this.entries_.add(builder.m10966build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.m10966build());
                }
                return this;
            }

            public Builder addEntries(int i, Entry.Builder builder) {
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureEntriesIsMutable();
                    this.entries_.add(i, builder.m10966build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.m10966build());
                }
                return this;
            }

            public Builder addAllEntries(Iterable<? extends Entry> iterable) {
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureEntriesIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.entries_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearEntries() {
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.entries_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeEntries(int i) {
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureEntriesIsMutable();
                    this.entries_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Entry.Builder getEntriesBuilder(int i) {
                return getEntriesFieldBuilder().getBuilder(i);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStructOrBuilder
            public EntryOrBuilder getEntriesOrBuilder(int i) {
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.entries_.get(i);
                }
                return (EntryOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.CreateStructOrBuilder
            public List<? extends EntryOrBuilder> getEntriesOrBuilderList() {
                RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> repeatedFieldBuilderV3 = this.entriesBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.entries_);
            }

            public Entry.Builder addEntriesBuilder() {
                return getEntriesFieldBuilder().addBuilder(Entry.getDefaultInstance());
            }

            public Entry.Builder addEntriesBuilder(int i) {
                return getEntriesFieldBuilder().addBuilder(i, Entry.getDefaultInstance());
            }

            public List<Entry.Builder> getEntriesBuilderList() {
                return getEntriesFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Entry, Entry.Builder, EntryOrBuilder> getEntriesFieldBuilder() {
                if (this.entriesBuilder_ == null) {
                    this.entriesBuilder_ = new RepeatedFieldBuilderV3<>(this.entries_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.entries_ = null;
                }
                return this.entriesBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m10954setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m10948mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Comprehension extends GeneratedMessageV3 implements ComprehensionOrBuilder {
        public static final int ACCU_INIT_FIELD_NUMBER = 4;
        public static final int ACCU_VAR_FIELD_NUMBER = 3;
        public static final int ITER_RANGE_FIELD_NUMBER = 2;
        public static final int ITER_VAR_FIELD_NUMBER = 1;
        public static final int LOOP_CONDITION_FIELD_NUMBER = 5;
        public static final int LOOP_STEP_FIELD_NUMBER = 6;
        public static final int RESULT_FIELD_NUMBER = 7;
        private static final long serialVersionUID = 0;
        private static final Comprehension DEFAULT_INSTANCE = new Comprehension();
        private static final Parser<Comprehension> PARSER = new AbstractParser<Comprehension>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Comprehension.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Comprehension m10825parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Comprehension(codedInputStream, extensionRegistryLite);
            }
        };
        private Expr accuInit_;
        private volatile Object accuVar_;
        private Expr iterRange_;
        private volatile Object iterVar_;
        private Expr loopCondition_;
        private Expr loopStep_;
        private byte memoizedIsInitialized;
        private Expr result_;

        private Comprehension(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Comprehension() {
            this.memoizedIsInitialized = (byte) -1;
            this.iterVar_ = "";
            this.accuVar_ = "";
        }

        private Comprehension(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            Builder builderM10731toBuilder;
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
                                    Expr expr = this.iterRange_;
                                    builderM10731toBuilder = expr != null ? expr.m10731toBuilder() : null;
                                    Expr expr2 = (Expr) codedInputStream.readMessage(Expr.parser(), extensionRegistryLite);
                                    this.iterRange_ = expr2;
                                    if (builderM10731toBuilder != null) {
                                        builderM10731toBuilder.mergeFrom(expr2);
                                        this.iterRange_ = builderM10731toBuilder.m10738buildPartial();
                                    }
                                } else if (tag == 26) {
                                    this.accuVar_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 34) {
                                    Expr expr3 = this.accuInit_;
                                    builderM10731toBuilder = expr3 != null ? expr3.m10731toBuilder() : null;
                                    Expr expr4 = (Expr) codedInputStream.readMessage(Expr.parser(), extensionRegistryLite);
                                    this.accuInit_ = expr4;
                                    if (builderM10731toBuilder != null) {
                                        builderM10731toBuilder.mergeFrom(expr4);
                                        this.accuInit_ = builderM10731toBuilder.m10738buildPartial();
                                    }
                                } else if (tag == 42) {
                                    Expr expr5 = this.loopCondition_;
                                    builderM10731toBuilder = expr5 != null ? expr5.m10731toBuilder() : null;
                                    Expr expr6 = (Expr) codedInputStream.readMessage(Expr.parser(), extensionRegistryLite);
                                    this.loopCondition_ = expr6;
                                    if (builderM10731toBuilder != null) {
                                        builderM10731toBuilder.mergeFrom(expr6);
                                        this.loopCondition_ = builderM10731toBuilder.m10738buildPartial();
                                    }
                                } else if (tag == 50) {
                                    Expr expr7 = this.loopStep_;
                                    builderM10731toBuilder = expr7 != null ? expr7.m10731toBuilder() : null;
                                    Expr expr8 = (Expr) codedInputStream.readMessage(Expr.parser(), extensionRegistryLite);
                                    this.loopStep_ = expr8;
                                    if (builderM10731toBuilder != null) {
                                        builderM10731toBuilder.mergeFrom(expr8);
                                        this.loopStep_ = builderM10731toBuilder.m10738buildPartial();
                                    }
                                } else if (tag == 58) {
                                    Expr expr9 = this.result_;
                                    builderM10731toBuilder = expr9 != null ? expr9.m10731toBuilder() : null;
                                    Expr expr10 = (Expr) codedInputStream.readMessage(Expr.parser(), extensionRegistryLite);
                                    this.result_ = expr10;
                                    if (builderM10731toBuilder != null) {
                                        builderM10731toBuilder.mergeFrom(expr10);
                                        this.result_ = builderM10731toBuilder.m10738buildPartial();
                                    }
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                this.iterVar_ = codedInputStream.readStringRequireUtf8();
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

        public static Comprehension getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Comprehension> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Comprehension_descriptor;
        }

        public static Comprehension parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Comprehension) PARSER.parseFrom(byteBuffer);
        }

        public static Comprehension parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Comprehension) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Comprehension parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Comprehension) PARSER.parseFrom(byteString);
        }

        public static Comprehension parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Comprehension) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Comprehension parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Comprehension) PARSER.parseFrom(bArr);
        }

        public static Comprehension parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Comprehension) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Comprehension parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Comprehension parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Comprehension parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Comprehension parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Comprehension parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Comprehension parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m10823toBuilder();
        }

        public static Builder newBuilder(Comprehension comprehension) {
            return DEFAULT_INSTANCE.m10823toBuilder().mergeFrom(comprehension);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Comprehension m10818getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Comprehension> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public boolean hasAccuInit() {
            return this.accuInit_ != null;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public boolean hasIterRange() {
            return this.iterRange_ != null;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public boolean hasLoopCondition() {
            return this.loopCondition_ != null;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public boolean hasLoopStep() {
            return this.loopStep_ != null;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public boolean hasResult() {
            return this.result_ != null;
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
            return new Comprehension();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Comprehension_fieldAccessorTable.ensureFieldAccessorsInitialized(Comprehension.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public String getIterVar() {
            Object obj = this.iterVar_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.iterVar_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public ByteString getIterVarBytes() {
            Object obj = this.iterVar_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.iterVar_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public Expr getIterRange() {
            Expr expr = this.iterRange_;
            return expr == null ? Expr.getDefaultInstance() : expr;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public ExprOrBuilder getIterRangeOrBuilder() {
            return getIterRange();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public String getAccuVar() {
            Object obj = this.accuVar_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.accuVar_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public ByteString getAccuVarBytes() {
            Object obj = this.accuVar_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.accuVar_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public Expr getAccuInit() {
            Expr expr = this.accuInit_;
            return expr == null ? Expr.getDefaultInstance() : expr;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public ExprOrBuilder getAccuInitOrBuilder() {
            return getAccuInit();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public Expr getLoopCondition() {
            Expr expr = this.loopCondition_;
            return expr == null ? Expr.getDefaultInstance() : expr;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public ExprOrBuilder getLoopConditionOrBuilder() {
            return getLoopCondition();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public Expr getLoopStep() {
            Expr expr = this.loopStep_;
            return expr == null ? Expr.getDefaultInstance() : expr;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public ExprOrBuilder getLoopStepOrBuilder() {
            return getLoopStep();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public Expr getResult() {
            Expr expr = this.result_;
            return expr == null ? Expr.getDefaultInstance() : expr;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
        public ExprOrBuilder getResultOrBuilder() {
            return getResult();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getIterVarBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.iterVar_);
            }
            if (this.iterRange_ != null) {
                codedOutputStream.writeMessage(2, getIterRange());
            }
            if (!getAccuVarBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.accuVar_);
            }
            if (this.accuInit_ != null) {
                codedOutputStream.writeMessage(4, getAccuInit());
            }
            if (this.loopCondition_ != null) {
                codedOutputStream.writeMessage(5, getLoopCondition());
            }
            if (this.loopStep_ != null) {
                codedOutputStream.writeMessage(6, getLoopStep());
            }
            if (this.result_ != null) {
                codedOutputStream.writeMessage(7, getResult());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getIterVarBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.iterVar_) : 0;
            if (this.iterRange_ != null) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(2, getIterRange());
            }
            if (!getAccuVarBytes().isEmpty()) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.accuVar_);
            }
            if (this.accuInit_ != null) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(4, getAccuInit());
            }
            if (this.loopCondition_ != null) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(5, getLoopCondition());
            }
            if (this.loopStep_ != null) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(6, getLoopStep());
            }
            if (this.result_ != null) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(7, getResult());
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Comprehension)) {
                return super.equals(obj);
            }
            Comprehension comprehension = (Comprehension) obj;
            if (!getIterVar().equals(comprehension.getIterVar()) || hasIterRange() != comprehension.hasIterRange()) {
                return false;
            }
            if ((hasIterRange() && !getIterRange().equals(comprehension.getIterRange())) || !getAccuVar().equals(comprehension.getAccuVar()) || hasAccuInit() != comprehension.hasAccuInit()) {
                return false;
            }
            if ((hasAccuInit() && !getAccuInit().equals(comprehension.getAccuInit())) || hasLoopCondition() != comprehension.hasLoopCondition()) {
                return false;
            }
            if ((hasLoopCondition() && !getLoopCondition().equals(comprehension.getLoopCondition())) || hasLoopStep() != comprehension.hasLoopStep()) {
                return false;
            }
            if ((!hasLoopStep() || getLoopStep().equals(comprehension.getLoopStep())) && hasResult() == comprehension.hasResult()) {
                return (!hasResult() || getResult().equals(comprehension.getResult())) && this.unknownFields.equals(comprehension.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getIterVar().hashCode();
            if (hasIterRange()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getIterRange().hashCode();
            }
            int iHashCode2 = (((iHashCode * 37) + 3) * 53) + getAccuVar().hashCode();
            if (hasAccuInit()) {
                iHashCode2 = (((iHashCode2 * 37) + 4) * 53) + getAccuInit().hashCode();
            }
            if (hasLoopCondition()) {
                iHashCode2 = (((iHashCode2 * 37) + 5) * 53) + getLoopCondition().hashCode();
            }
            if (hasLoopStep()) {
                iHashCode2 = (((iHashCode2 * 37) + 6) * 53) + getLoopStep().hashCode();
            }
            if (hasResult()) {
                iHashCode2 = (((iHashCode2 * 37) + 7) * 53) + getResult().hashCode();
            }
            int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode3;
            return iHashCode3;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10820newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10823toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ComprehensionOrBuilder {
            private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> accuInitBuilder_;
            private Expr accuInit_;
            private Object accuVar_;
            private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> iterRangeBuilder_;
            private Expr iterRange_;
            private Object iterVar_;
            private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> loopConditionBuilder_;
            private Expr loopCondition_;
            private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> loopStepBuilder_;
            private Expr loopStep_;
            private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> resultBuilder_;
            private Expr result_;

            private Builder() {
                this.iterVar_ = "";
                this.accuVar_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.iterVar_ = "";
                this.accuVar_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Comprehension_descriptor;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public boolean hasAccuInit() {
                return (this.accuInitBuilder_ == null && this.accuInit_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public boolean hasIterRange() {
                return (this.iterRangeBuilder_ == null && this.iterRange_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public boolean hasLoopCondition() {
                return (this.loopConditionBuilder_ == null && this.loopCondition_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public boolean hasLoopStep() {
                return (this.loopStepBuilder_ == null && this.loopStep_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public boolean hasResult() {
                return (this.resultBuilder_ == null && this.result_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Comprehension_fieldAccessorTable.ensureFieldAccessorsInitialized(Comprehension.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Comprehension.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10834clear() {
                super.clear();
                this.iterVar_ = "";
                if (this.iterRangeBuilder_ == null) {
                    this.iterRange_ = null;
                } else {
                    this.iterRange_ = null;
                    this.iterRangeBuilder_ = null;
                }
                this.accuVar_ = "";
                if (this.accuInitBuilder_ == null) {
                    this.accuInit_ = null;
                } else {
                    this.accuInit_ = null;
                    this.accuInitBuilder_ = null;
                }
                if (this.loopConditionBuilder_ == null) {
                    this.loopCondition_ = null;
                } else {
                    this.loopCondition_ = null;
                    this.loopConditionBuilder_ = null;
                }
                if (this.loopStepBuilder_ == null) {
                    this.loopStep_ = null;
                } else {
                    this.loopStep_ = null;
                    this.loopStepBuilder_ = null;
                }
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_Comprehension_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Comprehension m10847getDefaultInstanceForType() {
                return Comprehension.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Comprehension m10828build() throws UninitializedMessageException {
                Comprehension comprehensionM10830buildPartial = m10830buildPartial();
                if (comprehensionM10830buildPartial.isInitialized()) {
                    return comprehensionM10830buildPartial;
                }
                throw newUninitializedMessageException(comprehensionM10830buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Comprehension m10830buildPartial() {
                Comprehension comprehension = new Comprehension(this);
                comprehension.iterVar_ = this.iterVar_;
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.iterRangeBuilder_;
                if (singleFieldBuilderV3 == null) {
                    comprehension.iterRange_ = this.iterRange_;
                } else {
                    comprehension.iterRange_ = singleFieldBuilderV3.build();
                }
                comprehension.accuVar_ = this.accuVar_;
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV32 = this.accuInitBuilder_;
                if (singleFieldBuilderV32 == null) {
                    comprehension.accuInit_ = this.accuInit_;
                } else {
                    comprehension.accuInit_ = singleFieldBuilderV32.build();
                }
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV33 = this.loopConditionBuilder_;
                if (singleFieldBuilderV33 == null) {
                    comprehension.loopCondition_ = this.loopCondition_;
                } else {
                    comprehension.loopCondition_ = singleFieldBuilderV33.build();
                }
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV34 = this.loopStepBuilder_;
                if (singleFieldBuilderV34 == null) {
                    comprehension.loopStep_ = this.loopStep_;
                } else {
                    comprehension.loopStep_ = singleFieldBuilderV34.build();
                }
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV35 = this.resultBuilder_;
                if (singleFieldBuilderV35 == null) {
                    comprehension.result_ = this.result_;
                } else {
                    comprehension.result_ = singleFieldBuilderV35.build();
                }
                onBuilt();
                return comprehension;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10846clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10858setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10836clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10839clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10860setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10826addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m10851mergeFrom(Message message) {
                if (message instanceof Comprehension) {
                    return mergeFrom((Comprehension) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Comprehension comprehension) {
                if (comprehension == Comprehension.getDefaultInstance()) {
                    return this;
                }
                if (!comprehension.getIterVar().isEmpty()) {
                    this.iterVar_ = comprehension.iterVar_;
                    onChanged();
                }
                if (comprehension.hasIterRange()) {
                    mergeIterRange(comprehension.getIterRange());
                }
                if (!comprehension.getAccuVar().isEmpty()) {
                    this.accuVar_ = comprehension.accuVar_;
                    onChanged();
                }
                if (comprehension.hasAccuInit()) {
                    mergeAccuInit(comprehension.getAccuInit());
                }
                if (comprehension.hasLoopCondition()) {
                    mergeLoopCondition(comprehension.getLoopCondition());
                }
                if (comprehension.hasLoopStep()) {
                    mergeLoopStep(comprehension.getLoopStep());
                }
                if (comprehension.hasResult()) {
                    mergeResult(comprehension.getResult());
                }
                m10856mergeUnknownFields(comprehension.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Comprehension.Builder m10852mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Comprehension.access$7600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$Comprehension r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Comprehension) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$Comprehension r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Comprehension) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Comprehension.Builder.m10852mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$Comprehension$Builder");
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public String getIterVar() {
                Object obj = this.iterVar_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.iterVar_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setIterVar(String str) {
                str.getClass();
                this.iterVar_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public ByteString getIterVarBytes() {
                Object obj = this.iterVar_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.iterVar_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setIterVarBytes(ByteString byteString) {
                byteString.getClass();
                Comprehension.checkByteStringIsUtf8(byteString);
                this.iterVar_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearIterVar() {
                this.iterVar_ = Comprehension.getDefaultInstance().getIterVar();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public Expr getIterRange() {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.iterRangeBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Expr expr = this.iterRange_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            public Builder setIterRange(Expr expr) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.iterRangeBuilder_;
                if (singleFieldBuilderV3 == null) {
                    expr.getClass();
                    this.iterRange_ = expr;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(expr);
                }
                return this;
            }

            public Builder setIterRange(Builder builder) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.iterRangeBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.iterRange_ = builder.m10736build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m10736build());
                }
                return this;
            }

            public Builder mergeIterRange(Expr expr) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.iterRangeBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Expr expr2 = this.iterRange_;
                    if (expr2 != null) {
                        this.iterRange_ = Expr.newBuilder(expr2).mergeFrom(expr).m10738buildPartial();
                    } else {
                        this.iterRange_ = expr;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(expr);
                }
                return this;
            }

            public Builder clearIterRange() {
                if (this.iterRangeBuilder_ == null) {
                    this.iterRange_ = null;
                    onChanged();
                } else {
                    this.iterRange_ = null;
                    this.iterRangeBuilder_ = null;
                }
                return this;
            }

            public Builder getIterRangeBuilder() {
                onChanged();
                return getIterRangeFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public ExprOrBuilder getIterRangeOrBuilder() {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.iterRangeBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (ExprOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Expr expr = this.iterRange_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> getIterRangeFieldBuilder() {
                if (this.iterRangeBuilder_ == null) {
                    this.iterRangeBuilder_ = new SingleFieldBuilderV3<>(getIterRange(), getParentForChildren(), isClean());
                    this.iterRange_ = null;
                }
                return this.iterRangeBuilder_;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public String getAccuVar() {
                Object obj = this.accuVar_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.accuVar_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setAccuVar(String str) {
                str.getClass();
                this.accuVar_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public ByteString getAccuVarBytes() {
                Object obj = this.accuVar_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.accuVar_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setAccuVarBytes(ByteString byteString) {
                byteString.getClass();
                Comprehension.checkByteStringIsUtf8(byteString);
                this.accuVar_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAccuVar() {
                this.accuVar_ = Comprehension.getDefaultInstance().getAccuVar();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public Expr getAccuInit() {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.accuInitBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Expr expr = this.accuInit_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            public Builder setAccuInit(Expr expr) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.accuInitBuilder_;
                if (singleFieldBuilderV3 == null) {
                    expr.getClass();
                    this.accuInit_ = expr;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(expr);
                }
                return this;
            }

            public Builder setAccuInit(Builder builder) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.accuInitBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.accuInit_ = builder.m10736build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m10736build());
                }
                return this;
            }

            public Builder mergeAccuInit(Expr expr) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.accuInitBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Expr expr2 = this.accuInit_;
                    if (expr2 != null) {
                        this.accuInit_ = Expr.newBuilder(expr2).mergeFrom(expr).m10738buildPartial();
                    } else {
                        this.accuInit_ = expr;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(expr);
                }
                return this;
            }

            public Builder clearAccuInit() {
                if (this.accuInitBuilder_ == null) {
                    this.accuInit_ = null;
                    onChanged();
                } else {
                    this.accuInit_ = null;
                    this.accuInitBuilder_ = null;
                }
                return this;
            }

            public Builder getAccuInitBuilder() {
                onChanged();
                return getAccuInitFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public ExprOrBuilder getAccuInitOrBuilder() {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.accuInitBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (ExprOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Expr expr = this.accuInit_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> getAccuInitFieldBuilder() {
                if (this.accuInitBuilder_ == null) {
                    this.accuInitBuilder_ = new SingleFieldBuilderV3<>(getAccuInit(), getParentForChildren(), isClean());
                    this.accuInit_ = null;
                }
                return this.accuInitBuilder_;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public Expr getLoopCondition() {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.loopConditionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Expr expr = this.loopCondition_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            public Builder setLoopCondition(Expr expr) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.loopConditionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    expr.getClass();
                    this.loopCondition_ = expr;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(expr);
                }
                return this;
            }

            public Builder setLoopCondition(Builder builder) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.loopConditionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.loopCondition_ = builder.m10736build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m10736build());
                }
                return this;
            }

            public Builder mergeLoopCondition(Expr expr) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.loopConditionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Expr expr2 = this.loopCondition_;
                    if (expr2 != null) {
                        this.loopCondition_ = Expr.newBuilder(expr2).mergeFrom(expr).m10738buildPartial();
                    } else {
                        this.loopCondition_ = expr;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(expr);
                }
                return this;
            }

            public Builder clearLoopCondition() {
                if (this.loopConditionBuilder_ == null) {
                    this.loopCondition_ = null;
                    onChanged();
                } else {
                    this.loopCondition_ = null;
                    this.loopConditionBuilder_ = null;
                }
                return this;
            }

            public Builder getLoopConditionBuilder() {
                onChanged();
                return getLoopConditionFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public ExprOrBuilder getLoopConditionOrBuilder() {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.loopConditionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (ExprOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Expr expr = this.loopCondition_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> getLoopConditionFieldBuilder() {
                if (this.loopConditionBuilder_ == null) {
                    this.loopConditionBuilder_ = new SingleFieldBuilderV3<>(getLoopCondition(), getParentForChildren(), isClean());
                    this.loopCondition_ = null;
                }
                return this.loopConditionBuilder_;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public Expr getLoopStep() {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.loopStepBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Expr expr = this.loopStep_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            public Builder setLoopStep(Expr expr) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.loopStepBuilder_;
                if (singleFieldBuilderV3 == null) {
                    expr.getClass();
                    this.loopStep_ = expr;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(expr);
                }
                return this;
            }

            public Builder setLoopStep(Builder builder) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.loopStepBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.loopStep_ = builder.m10736build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m10736build());
                }
                return this;
            }

            public Builder mergeLoopStep(Expr expr) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.loopStepBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Expr expr2 = this.loopStep_;
                    if (expr2 != null) {
                        this.loopStep_ = Expr.newBuilder(expr2).mergeFrom(expr).m10738buildPartial();
                    } else {
                        this.loopStep_ = expr;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(expr);
                }
                return this;
            }

            public Builder clearLoopStep() {
                if (this.loopStepBuilder_ == null) {
                    this.loopStep_ = null;
                    onChanged();
                } else {
                    this.loopStep_ = null;
                    this.loopStepBuilder_ = null;
                }
                return this;
            }

            public Builder getLoopStepBuilder() {
                onChanged();
                return getLoopStepFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public ExprOrBuilder getLoopStepOrBuilder() {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.loopStepBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (ExprOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Expr expr = this.loopStep_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> getLoopStepFieldBuilder() {
                if (this.loopStepBuilder_ == null) {
                    this.loopStepBuilder_ = new SingleFieldBuilderV3<>(getLoopStep(), getParentForChildren(), isClean());
                    this.loopStep_ = null;
                }
                return this.loopStepBuilder_;
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public Expr getResult() {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Expr expr = this.result_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            public Builder setResult(Expr expr) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    expr.getClass();
                    this.result_ = expr;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(expr);
                }
                return this;
            }

            public Builder setResult(Builder builder) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.result_ = builder.m10736build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m10736build());
                }
                return this;
            }

            public Builder mergeResult(Expr expr) {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Expr expr2 = this.result_;
                    if (expr2 != null) {
                        this.result_ = Expr.newBuilder(expr2).mergeFrom(expr).m10738buildPartial();
                    } else {
                        this.result_ = expr;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(expr);
                }
                return this;
            }

            public Builder clearResult() {
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                    onChanged();
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            public Builder getResultBuilder() {
                onChanged();
                return getResultFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.ComprehensionOrBuilder
            public ExprOrBuilder getResultOrBuilder() {
                SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (ExprOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Expr expr = this.result_;
                return expr == null ? Expr.getDefaultInstance() : expr;
            }

            private SingleFieldBuilderV3<Expr, Builder, ExprOrBuilder> getResultFieldBuilder() {
                if (this.resultBuilder_ == null) {
                    this.resultBuilder_ = new SingleFieldBuilderV3<>(getResult(), getParentForChildren(), isClean());
                    this.result_ = null;
                }
                return this.resultBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m10862setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m10856mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExprOrBuilder {
        private SingleFieldBuilderV3<Call, Call.Builder, CallOrBuilder> callExprBuilder_;
        private SingleFieldBuilderV3<Comprehension, Comprehension.Builder, ComprehensionOrBuilder> comprehensionExprBuilder_;
        private SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> constExprBuilder_;
        private int exprKindCase_;
        private Object exprKind_;
        private long id_;
        private SingleFieldBuilderV3<Ident, Ident.Builder, IdentOrBuilder> identExprBuilder_;
        private SingleFieldBuilderV3<CreateList, CreateList.Builder, CreateListOrBuilder> listExprBuilder_;
        private SingleFieldBuilderV3<Select, Select.Builder, SelectOrBuilder> selectExprBuilder_;
        private SingleFieldBuilderV3<CreateStruct, CreateStruct.Builder, CreateStructOrBuilder> structExprBuilder_;

        private Builder() {
            this.exprKindCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.exprKindCase_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_descriptor;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public long getId() {
            return this.id_;
        }

        public Builder setId(long j) {
            this.id_ = j;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public boolean hasCallExpr() {
            return this.exprKindCase_ == 6;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public boolean hasComprehensionExpr() {
            return this.exprKindCase_ == 9;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public boolean hasConstExpr() {
            return this.exprKindCase_ == 3;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public boolean hasIdentExpr() {
            return this.exprKindCase_ == 4;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public boolean hasListExpr() {
            return this.exprKindCase_ == 7;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public boolean hasSelectExpr() {
            return this.exprKindCase_ == 5;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public boolean hasStructExpr() {
            return this.exprKindCase_ == 8;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_fieldAccessorTable.ensureFieldAccessorsInitialized(Expr.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Expr.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10742clear() {
            super.clear();
            this.id_ = 0L;
            this.exprKindCase_ = 0;
            this.exprKind_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_Expr_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Expr m10755getDefaultInstanceForType() {
            return Expr.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Expr m10736build() throws UninitializedMessageException {
            Expr exprM10738buildPartial = m10738buildPartial();
            if (exprM10738buildPartial.isInitialized()) {
                return exprM10738buildPartial;
            }
            throw newUninitializedMessageException(exprM10738buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Expr m10738buildPartial() {
            Expr expr = new Expr(this);
            expr.id_ = this.id_;
            if (this.exprKindCase_ == 3) {
                SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.constExprBuilder_;
                if (singleFieldBuilderV3 == null) {
                    expr.exprKind_ = this.exprKind_;
                } else {
                    expr.exprKind_ = singleFieldBuilderV3.build();
                }
            }
            if (this.exprKindCase_ == 4) {
                SingleFieldBuilderV3<Ident, Ident.Builder, IdentOrBuilder> singleFieldBuilderV32 = this.identExprBuilder_;
                if (singleFieldBuilderV32 == null) {
                    expr.exprKind_ = this.exprKind_;
                } else {
                    expr.exprKind_ = singleFieldBuilderV32.build();
                }
            }
            if (this.exprKindCase_ == 5) {
                SingleFieldBuilderV3<Select, Select.Builder, SelectOrBuilder> singleFieldBuilderV33 = this.selectExprBuilder_;
                if (singleFieldBuilderV33 == null) {
                    expr.exprKind_ = this.exprKind_;
                } else {
                    expr.exprKind_ = singleFieldBuilderV33.build();
                }
            }
            if (this.exprKindCase_ == 6) {
                SingleFieldBuilderV3<Call, Call.Builder, CallOrBuilder> singleFieldBuilderV34 = this.callExprBuilder_;
                if (singleFieldBuilderV34 == null) {
                    expr.exprKind_ = this.exprKind_;
                } else {
                    expr.exprKind_ = singleFieldBuilderV34.build();
                }
            }
            if (this.exprKindCase_ == 7) {
                SingleFieldBuilderV3<CreateList, CreateList.Builder, CreateListOrBuilder> singleFieldBuilderV35 = this.listExprBuilder_;
                if (singleFieldBuilderV35 == null) {
                    expr.exprKind_ = this.exprKind_;
                } else {
                    expr.exprKind_ = singleFieldBuilderV35.build();
                }
            }
            if (this.exprKindCase_ == 8) {
                SingleFieldBuilderV3<CreateStruct, CreateStruct.Builder, CreateStructOrBuilder> singleFieldBuilderV36 = this.structExprBuilder_;
                if (singleFieldBuilderV36 == null) {
                    expr.exprKind_ = this.exprKind_;
                } else {
                    expr.exprKind_ = singleFieldBuilderV36.build();
                }
            }
            if (this.exprKindCase_ == 9) {
                SingleFieldBuilderV3<Comprehension, Comprehension.Builder, ComprehensionOrBuilder> singleFieldBuilderV37 = this.comprehensionExprBuilder_;
                if (singleFieldBuilderV37 == null) {
                    expr.exprKind_ = this.exprKind_;
                } else {
                    expr.exprKind_ = singleFieldBuilderV37.build();
                }
            }
            expr.exprKindCase_ = this.exprKindCase_;
            onBuilt();
            return expr;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10754clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10766setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10744clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10747clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10768setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10734addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m10759mergeFrom(Message message) {
            if (message instanceof Expr) {
                return mergeFrom((Expr) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Expr expr) {
            if (expr == Expr.getDefaultInstance()) {
                return this;
            }
            if (expr.getId() != 0) {
                setId(expr.getId());
            }
            switch (AnonymousClass2.$SwitchMap$com$google$api$expr$v1alpha1$Expr$ExprKindCase[expr.getExprKindCase().ordinal()]) {
                case 1:
                    mergeConstExpr(expr.getConstExpr());
                    break;
                case 2:
                    mergeIdentExpr(expr.getIdentExpr());
                    break;
                case 3:
                    mergeSelectExpr(expr.getSelectExpr());
                    break;
                case 4:
                    mergeCallExpr(expr.getCallExpr());
                    break;
                case 5:
                    mergeListExpr(expr.getListExpr());
                    break;
                case 6:
                    mergeStructExpr(expr.getStructExpr());
                    break;
                case 7:
                    mergeComprehensionExpr(expr.getComprehensionExpr());
                    break;
            }
            m10764mergeUnknownFields(expr.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Builder m10760mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.access$8800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr.Builder.m10760mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$Builder");
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public ExprKindCase getExprKindCase() {
            return ExprKindCase.forNumber(this.exprKindCase_);
        }

        public Builder clearExprKind() {
            this.exprKindCase_ = 0;
            this.exprKind_ = null;
            onChanged();
            return this;
        }

        public Builder clearId() {
            this.id_ = 0L;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public Constant getConstExpr() {
            SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.constExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.exprKindCase_ == 3) {
                    return (Constant) this.exprKind_;
                }
                return Constant.getDefaultInstance();
            }
            if (this.exprKindCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return Constant.getDefaultInstance();
        }

        public Builder setConstExpr(Constant constant) {
            SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.constExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                constant.getClass();
                this.exprKind_ = constant;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(constant);
            }
            this.exprKindCase_ = 3;
            return this;
        }

        public Builder setConstExpr(Constant.Builder builder) {
            SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.constExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.exprKind_ = builder.m10505build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m10505build());
            }
            this.exprKindCase_ = 3;
            return this;
        }

        public Builder mergeConstExpr(Constant constant) {
            SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.constExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.exprKindCase_ != 3 || this.exprKind_ == Constant.getDefaultInstance()) {
                    this.exprKind_ = constant;
                } else {
                    this.exprKind_ = Constant.newBuilder((Constant) this.exprKind_).mergeFrom(constant).m10507buildPartial();
                }
                onChanged();
            } else {
                if (this.exprKindCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(constant);
                }
                this.constExprBuilder_.setMessage(constant);
            }
            this.exprKindCase_ = 3;
            return this;
        }

        public Builder clearConstExpr() {
            SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3 = this.constExprBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.exprKindCase_ == 3) {
                    this.exprKindCase_ = 0;
                    this.exprKind_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.exprKindCase_ == 3) {
                this.exprKindCase_ = 0;
                this.exprKind_ = null;
                onChanged();
            }
            return this;
        }

        public Constant.Builder getConstExprBuilder() {
            return getConstExprFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public ConstantOrBuilder getConstExprOrBuilder() {
            SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> singleFieldBuilderV3;
            int i = this.exprKindCase_;
            if (i == 3 && (singleFieldBuilderV3 = this.constExprBuilder_) != null) {
                return (ConstantOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 3) {
                return (Constant) this.exprKind_;
            }
            return Constant.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Constant, Constant.Builder, ConstantOrBuilder> getConstExprFieldBuilder() {
            if (this.constExprBuilder_ == null) {
                if (this.exprKindCase_ != 3) {
                    this.exprKind_ = Constant.getDefaultInstance();
                }
                this.constExprBuilder_ = new SingleFieldBuilderV3<>((Constant) this.exprKind_, getParentForChildren(), isClean());
                this.exprKind_ = null;
            }
            this.exprKindCase_ = 3;
            onChanged();
            return this.constExprBuilder_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public Ident getIdentExpr() {
            SingleFieldBuilderV3<Ident, Ident.Builder, IdentOrBuilder> singleFieldBuilderV3 = this.identExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.exprKindCase_ == 4) {
                    return (Ident) this.exprKind_;
                }
                return Ident.getDefaultInstance();
            }
            if (this.exprKindCase_ == 4) {
                return singleFieldBuilderV3.getMessage();
            }
            return Ident.getDefaultInstance();
        }

        public Builder setIdentExpr(Ident ident) {
            SingleFieldBuilderV3<Ident, Ident.Builder, IdentOrBuilder> singleFieldBuilderV3 = this.identExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                ident.getClass();
                this.exprKind_ = ident;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(ident);
            }
            this.exprKindCase_ = 4;
            return this;
        }

        public Builder setIdentExpr(Ident.Builder builder) {
            SingleFieldBuilderV3<Ident, Ident.Builder, IdentOrBuilder> singleFieldBuilderV3 = this.identExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.exprKind_ = builder.m11012build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m11012build());
            }
            this.exprKindCase_ = 4;
            return this;
        }

        public Builder mergeIdentExpr(Ident ident) {
            SingleFieldBuilderV3<Ident, Ident.Builder, IdentOrBuilder> singleFieldBuilderV3 = this.identExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.exprKindCase_ != 4 || this.exprKind_ == Ident.getDefaultInstance()) {
                    this.exprKind_ = ident;
                } else {
                    this.exprKind_ = Ident.newBuilder((Ident) this.exprKind_).mergeFrom(ident).m11014buildPartial();
                }
                onChanged();
            } else {
                if (this.exprKindCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(ident);
                }
                this.identExprBuilder_.setMessage(ident);
            }
            this.exprKindCase_ = 4;
            return this;
        }

        public Builder clearIdentExpr() {
            SingleFieldBuilderV3<Ident, Ident.Builder, IdentOrBuilder> singleFieldBuilderV3 = this.identExprBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.exprKindCase_ == 4) {
                    this.exprKindCase_ = 0;
                    this.exprKind_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.exprKindCase_ == 4) {
                this.exprKindCase_ = 0;
                this.exprKind_ = null;
                onChanged();
            }
            return this;
        }

        public Ident.Builder getIdentExprBuilder() {
            return getIdentExprFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public IdentOrBuilder getIdentExprOrBuilder() {
            SingleFieldBuilderV3<Ident, Ident.Builder, IdentOrBuilder> singleFieldBuilderV3;
            int i = this.exprKindCase_;
            if (i == 4 && (singleFieldBuilderV3 = this.identExprBuilder_) != null) {
                return (IdentOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 4) {
                return (Ident) this.exprKind_;
            }
            return Ident.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Ident, Ident.Builder, IdentOrBuilder> getIdentExprFieldBuilder() {
            if (this.identExprBuilder_ == null) {
                if (this.exprKindCase_ != 4) {
                    this.exprKind_ = Ident.getDefaultInstance();
                }
                this.identExprBuilder_ = new SingleFieldBuilderV3<>((Ident) this.exprKind_, getParentForChildren(), isClean());
                this.exprKind_ = null;
            }
            this.exprKindCase_ = 4;
            onChanged();
            return this.identExprBuilder_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public Select getSelectExpr() {
            SingleFieldBuilderV3<Select, Select.Builder, SelectOrBuilder> singleFieldBuilderV3 = this.selectExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.exprKindCase_ == 5) {
                    return (Select) this.exprKind_;
                }
                return Select.getDefaultInstance();
            }
            if (this.exprKindCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return Select.getDefaultInstance();
        }

        public Builder setSelectExpr(Select select) {
            SingleFieldBuilderV3<Select, Select.Builder, SelectOrBuilder> singleFieldBuilderV3 = this.selectExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                select.getClass();
                this.exprKind_ = select;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(select);
            }
            this.exprKindCase_ = 5;
            return this;
        }

        public Builder setSelectExpr(Select.Builder builder) {
            SingleFieldBuilderV3<Select, Select.Builder, SelectOrBuilder> singleFieldBuilderV3 = this.selectExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.exprKind_ = builder.m11058build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m11058build());
            }
            this.exprKindCase_ = 5;
            return this;
        }

        public Builder mergeSelectExpr(Select select) {
            SingleFieldBuilderV3<Select, Select.Builder, SelectOrBuilder> singleFieldBuilderV3 = this.selectExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.exprKindCase_ != 5 || this.exprKind_ == Select.getDefaultInstance()) {
                    this.exprKind_ = select;
                } else {
                    this.exprKind_ = Select.newBuilder((Select) this.exprKind_).mergeFrom(select).m11060buildPartial();
                }
                onChanged();
            } else {
                if (this.exprKindCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(select);
                }
                this.selectExprBuilder_.setMessage(select);
            }
            this.exprKindCase_ = 5;
            return this;
        }

        public Builder clearSelectExpr() {
            SingleFieldBuilderV3<Select, Select.Builder, SelectOrBuilder> singleFieldBuilderV3 = this.selectExprBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.exprKindCase_ == 5) {
                    this.exprKindCase_ = 0;
                    this.exprKind_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.exprKindCase_ == 5) {
                this.exprKindCase_ = 0;
                this.exprKind_ = null;
                onChanged();
            }
            return this;
        }

        public Select.Builder getSelectExprBuilder() {
            return getSelectExprFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public SelectOrBuilder getSelectExprOrBuilder() {
            SingleFieldBuilderV3<Select, Select.Builder, SelectOrBuilder> singleFieldBuilderV3;
            int i = this.exprKindCase_;
            if (i == 5 && (singleFieldBuilderV3 = this.selectExprBuilder_) != null) {
                return (SelectOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 5) {
                return (Select) this.exprKind_;
            }
            return Select.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Select, Select.Builder, SelectOrBuilder> getSelectExprFieldBuilder() {
            if (this.selectExprBuilder_ == null) {
                if (this.exprKindCase_ != 5) {
                    this.exprKind_ = Select.getDefaultInstance();
                }
                this.selectExprBuilder_ = new SingleFieldBuilderV3<>((Select) this.exprKind_, getParentForChildren(), isClean());
                this.exprKind_ = null;
            }
            this.exprKindCase_ = 5;
            onChanged();
            return this.selectExprBuilder_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public Call getCallExpr() {
            SingleFieldBuilderV3<Call, Call.Builder, CallOrBuilder> singleFieldBuilderV3 = this.callExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.exprKindCase_ == 6) {
                    return (Call) this.exprKind_;
                }
                return Call.getDefaultInstance();
            }
            if (this.exprKindCase_ == 6) {
                return singleFieldBuilderV3.getMessage();
            }
            return Call.getDefaultInstance();
        }

        public Builder setCallExpr(Call call) {
            SingleFieldBuilderV3<Call, Call.Builder, CallOrBuilder> singleFieldBuilderV3 = this.callExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                call.getClass();
                this.exprKind_ = call;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(call);
            }
            this.exprKindCase_ = 6;
            return this;
        }

        public Builder setCallExpr(Call.Builder builder) {
            SingleFieldBuilderV3<Call, Call.Builder, CallOrBuilder> singleFieldBuilderV3 = this.callExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.exprKind_ = builder.m10782build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m10782build());
            }
            this.exprKindCase_ = 6;
            return this;
        }

        public Builder mergeCallExpr(Call call) {
            SingleFieldBuilderV3<Call, Call.Builder, CallOrBuilder> singleFieldBuilderV3 = this.callExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.exprKindCase_ != 6 || this.exprKind_ == Call.getDefaultInstance()) {
                    this.exprKind_ = call;
                } else {
                    this.exprKind_ = Call.newBuilder((Call) this.exprKind_).mergeFrom(call).m10784buildPartial();
                }
                onChanged();
            } else {
                if (this.exprKindCase_ == 6) {
                    singleFieldBuilderV3.mergeFrom(call);
                }
                this.callExprBuilder_.setMessage(call);
            }
            this.exprKindCase_ = 6;
            return this;
        }

        public Builder clearCallExpr() {
            SingleFieldBuilderV3<Call, Call.Builder, CallOrBuilder> singleFieldBuilderV3 = this.callExprBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.exprKindCase_ == 6) {
                    this.exprKindCase_ = 0;
                    this.exprKind_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.exprKindCase_ == 6) {
                this.exprKindCase_ = 0;
                this.exprKind_ = null;
                onChanged();
            }
            return this;
        }

        public Call.Builder getCallExprBuilder() {
            return getCallExprFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public CallOrBuilder getCallExprOrBuilder() {
            SingleFieldBuilderV3<Call, Call.Builder, CallOrBuilder> singleFieldBuilderV3;
            int i = this.exprKindCase_;
            if (i == 6 && (singleFieldBuilderV3 = this.callExprBuilder_) != null) {
                return (CallOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 6) {
                return (Call) this.exprKind_;
            }
            return Call.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Call, Call.Builder, CallOrBuilder> getCallExprFieldBuilder() {
            if (this.callExprBuilder_ == null) {
                if (this.exprKindCase_ != 6) {
                    this.exprKind_ = Call.getDefaultInstance();
                }
                this.callExprBuilder_ = new SingleFieldBuilderV3<>((Call) this.exprKind_, getParentForChildren(), isClean());
                this.exprKind_ = null;
            }
            this.exprKindCase_ = 6;
            onChanged();
            return this.callExprBuilder_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public CreateList getListExpr() {
            SingleFieldBuilderV3<CreateList, CreateList.Builder, CreateListOrBuilder> singleFieldBuilderV3 = this.listExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.exprKindCase_ == 7) {
                    return (CreateList) this.exprKind_;
                }
                return CreateList.getDefaultInstance();
            }
            if (this.exprKindCase_ == 7) {
                return singleFieldBuilderV3.getMessage();
            }
            return CreateList.getDefaultInstance();
        }

        public Builder setListExpr(CreateList createList) {
            SingleFieldBuilderV3<CreateList, CreateList.Builder, CreateListOrBuilder> singleFieldBuilderV3 = this.listExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                createList.getClass();
                this.exprKind_ = createList;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(createList);
            }
            this.exprKindCase_ = 7;
            return this;
        }

        public Builder setListExpr(CreateList.Builder builder) {
            SingleFieldBuilderV3<CreateList, CreateList.Builder, CreateListOrBuilder> singleFieldBuilderV3 = this.listExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.exprKind_ = builder.m10874build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m10874build());
            }
            this.exprKindCase_ = 7;
            return this;
        }

        public Builder mergeListExpr(CreateList createList) {
            SingleFieldBuilderV3<CreateList, CreateList.Builder, CreateListOrBuilder> singleFieldBuilderV3 = this.listExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.exprKindCase_ != 7 || this.exprKind_ == CreateList.getDefaultInstance()) {
                    this.exprKind_ = createList;
                } else {
                    this.exprKind_ = CreateList.newBuilder((CreateList) this.exprKind_).mergeFrom(createList).m10876buildPartial();
                }
                onChanged();
            } else {
                if (this.exprKindCase_ == 7) {
                    singleFieldBuilderV3.mergeFrom(createList);
                }
                this.listExprBuilder_.setMessage(createList);
            }
            this.exprKindCase_ = 7;
            return this;
        }

        public Builder clearListExpr() {
            SingleFieldBuilderV3<CreateList, CreateList.Builder, CreateListOrBuilder> singleFieldBuilderV3 = this.listExprBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.exprKindCase_ == 7) {
                    this.exprKindCase_ = 0;
                    this.exprKind_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.exprKindCase_ == 7) {
                this.exprKindCase_ = 0;
                this.exprKind_ = null;
                onChanged();
            }
            return this;
        }

        public CreateList.Builder getListExprBuilder() {
            return getListExprFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public CreateListOrBuilder getListExprOrBuilder() {
            SingleFieldBuilderV3<CreateList, CreateList.Builder, CreateListOrBuilder> singleFieldBuilderV3;
            int i = this.exprKindCase_;
            if (i == 7 && (singleFieldBuilderV3 = this.listExprBuilder_) != null) {
                return (CreateListOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 7) {
                return (CreateList) this.exprKind_;
            }
            return CreateList.getDefaultInstance();
        }

        private SingleFieldBuilderV3<CreateList, CreateList.Builder, CreateListOrBuilder> getListExprFieldBuilder() {
            if (this.listExprBuilder_ == null) {
                if (this.exprKindCase_ != 7) {
                    this.exprKind_ = CreateList.getDefaultInstance();
                }
                this.listExprBuilder_ = new SingleFieldBuilderV3<>((CreateList) this.exprKind_, getParentForChildren(), isClean());
                this.exprKind_ = null;
            }
            this.exprKindCase_ = 7;
            onChanged();
            return this.listExprBuilder_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public CreateStruct getStructExpr() {
            SingleFieldBuilderV3<CreateStruct, CreateStruct.Builder, CreateStructOrBuilder> singleFieldBuilderV3 = this.structExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.exprKindCase_ == 8) {
                    return (CreateStruct) this.exprKind_;
                }
                return CreateStruct.getDefaultInstance();
            }
            if (this.exprKindCase_ == 8) {
                return singleFieldBuilderV3.getMessage();
            }
            return CreateStruct.getDefaultInstance();
        }

        public Builder setStructExpr(CreateStruct createStruct) {
            SingleFieldBuilderV3<CreateStruct, CreateStruct.Builder, CreateStructOrBuilder> singleFieldBuilderV3 = this.structExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                createStruct.getClass();
                this.exprKind_ = createStruct;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(createStruct);
            }
            this.exprKindCase_ = 8;
            return this;
        }

        public Builder setStructExpr(CreateStruct.Builder builder) {
            SingleFieldBuilderV3<CreateStruct, CreateStruct.Builder, CreateStructOrBuilder> singleFieldBuilderV3 = this.structExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.exprKind_ = builder.m10920build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m10920build());
            }
            this.exprKindCase_ = 8;
            return this;
        }

        public Builder mergeStructExpr(CreateStruct createStruct) {
            SingleFieldBuilderV3<CreateStruct, CreateStruct.Builder, CreateStructOrBuilder> singleFieldBuilderV3 = this.structExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.exprKindCase_ != 8 || this.exprKind_ == CreateStruct.getDefaultInstance()) {
                    this.exprKind_ = createStruct;
                } else {
                    this.exprKind_ = CreateStruct.newBuilder((CreateStruct) this.exprKind_).mergeFrom(createStruct).m10922buildPartial();
                }
                onChanged();
            } else {
                if (this.exprKindCase_ == 8) {
                    singleFieldBuilderV3.mergeFrom(createStruct);
                }
                this.structExprBuilder_.setMessage(createStruct);
            }
            this.exprKindCase_ = 8;
            return this;
        }

        public Builder clearStructExpr() {
            SingleFieldBuilderV3<CreateStruct, CreateStruct.Builder, CreateStructOrBuilder> singleFieldBuilderV3 = this.structExprBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.exprKindCase_ == 8) {
                    this.exprKindCase_ = 0;
                    this.exprKind_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.exprKindCase_ == 8) {
                this.exprKindCase_ = 0;
                this.exprKind_ = null;
                onChanged();
            }
            return this;
        }

        public CreateStruct.Builder getStructExprBuilder() {
            return getStructExprFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public CreateStructOrBuilder getStructExprOrBuilder() {
            SingleFieldBuilderV3<CreateStruct, CreateStruct.Builder, CreateStructOrBuilder> singleFieldBuilderV3;
            int i = this.exprKindCase_;
            if (i == 8 && (singleFieldBuilderV3 = this.structExprBuilder_) != null) {
                return (CreateStructOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 8) {
                return (CreateStruct) this.exprKind_;
            }
            return CreateStruct.getDefaultInstance();
        }

        private SingleFieldBuilderV3<CreateStruct, CreateStruct.Builder, CreateStructOrBuilder> getStructExprFieldBuilder() {
            if (this.structExprBuilder_ == null) {
                if (this.exprKindCase_ != 8) {
                    this.exprKind_ = CreateStruct.getDefaultInstance();
                }
                this.structExprBuilder_ = new SingleFieldBuilderV3<>((CreateStruct) this.exprKind_, getParentForChildren(), isClean());
                this.exprKind_ = null;
            }
            this.exprKindCase_ = 8;
            onChanged();
            return this.structExprBuilder_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public Comprehension getComprehensionExpr() {
            SingleFieldBuilderV3<Comprehension, Comprehension.Builder, ComprehensionOrBuilder> singleFieldBuilderV3 = this.comprehensionExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.exprKindCase_ == 9) {
                    return (Comprehension) this.exprKind_;
                }
                return Comprehension.getDefaultInstance();
            }
            if (this.exprKindCase_ == 9) {
                return singleFieldBuilderV3.getMessage();
            }
            return Comprehension.getDefaultInstance();
        }

        public Builder setComprehensionExpr(Comprehension comprehension) {
            SingleFieldBuilderV3<Comprehension, Comprehension.Builder, ComprehensionOrBuilder> singleFieldBuilderV3 = this.comprehensionExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                comprehension.getClass();
                this.exprKind_ = comprehension;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(comprehension);
            }
            this.exprKindCase_ = 9;
            return this;
        }

        public Builder setComprehensionExpr(Comprehension.Builder builder) {
            SingleFieldBuilderV3<Comprehension, Comprehension.Builder, ComprehensionOrBuilder> singleFieldBuilderV3 = this.comprehensionExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.exprKind_ = builder.m10828build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m10828build());
            }
            this.exprKindCase_ = 9;
            return this;
        }

        public Builder mergeComprehensionExpr(Comprehension comprehension) {
            SingleFieldBuilderV3<Comprehension, Comprehension.Builder, ComprehensionOrBuilder> singleFieldBuilderV3 = this.comprehensionExprBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.exprKindCase_ != 9 || this.exprKind_ == Comprehension.getDefaultInstance()) {
                    this.exprKind_ = comprehension;
                } else {
                    this.exprKind_ = Comprehension.newBuilder((Comprehension) this.exprKind_).mergeFrom(comprehension).m10830buildPartial();
                }
                onChanged();
            } else {
                if (this.exprKindCase_ == 9) {
                    singleFieldBuilderV3.mergeFrom(comprehension);
                }
                this.comprehensionExprBuilder_.setMessage(comprehension);
            }
            this.exprKindCase_ = 9;
            return this;
        }

        public Builder clearComprehensionExpr() {
            SingleFieldBuilderV3<Comprehension, Comprehension.Builder, ComprehensionOrBuilder> singleFieldBuilderV3 = this.comprehensionExprBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.exprKindCase_ == 9) {
                    this.exprKindCase_ = 0;
                    this.exprKind_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.exprKindCase_ == 9) {
                this.exprKindCase_ = 0;
                this.exprKind_ = null;
                onChanged();
            }
            return this;
        }

        public Comprehension.Builder getComprehensionExprBuilder() {
            return getComprehensionExprFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder
        public ComprehensionOrBuilder getComprehensionExprOrBuilder() {
            SingleFieldBuilderV3<Comprehension, Comprehension.Builder, ComprehensionOrBuilder> singleFieldBuilderV3;
            int i = this.exprKindCase_;
            if (i == 9 && (singleFieldBuilderV3 = this.comprehensionExprBuilder_) != null) {
                return (ComprehensionOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 9) {
                return (Comprehension) this.exprKind_;
            }
            return Comprehension.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Comprehension, Comprehension.Builder, ComprehensionOrBuilder> getComprehensionExprFieldBuilder() {
            if (this.comprehensionExprBuilder_ == null) {
                if (this.exprKindCase_ != 9) {
                    this.exprKind_ = Comprehension.getDefaultInstance();
                }
                this.comprehensionExprBuilder_ = new SingleFieldBuilderV3<>((Comprehension) this.exprKind_, getParentForChildren(), isClean());
                this.exprKind_ = null;
            }
            this.exprKindCase_ = 9;
            onChanged();
            return this.comprehensionExprBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m10770setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m10764mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$api$expr$v1alpha1$Expr$CreateStruct$Entry$KeyKindCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$api$expr$v1alpha1$Expr$ExprKindCase;

        static {
            int[] iArr = new int[ExprKindCase.values().length];
            $SwitchMap$com$google$api$expr$v1alpha1$Expr$ExprKindCase = iArr;
            try {
                iArr[ExprKindCase.CONST_EXPR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$api$expr$v1alpha1$Expr$ExprKindCase[ExprKindCase.IDENT_EXPR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$api$expr$v1alpha1$Expr$ExprKindCase[ExprKindCase.SELECT_EXPR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$api$expr$v1alpha1$Expr$ExprKindCase[ExprKindCase.CALL_EXPR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$api$expr$v1alpha1$Expr$ExprKindCase[ExprKindCase.LIST_EXPR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$api$expr$v1alpha1$Expr$ExprKindCase[ExprKindCase.STRUCT_EXPR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$api$expr$v1alpha1$Expr$ExprKindCase[ExprKindCase.COMPREHENSION_EXPR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$api$expr$v1alpha1$Expr$ExprKindCase[ExprKindCase.EXPRKIND_NOT_SET.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[CreateStruct.Entry.KeyKindCase.values().length];
            $SwitchMap$com$google$api$expr$v1alpha1$Expr$CreateStruct$Entry$KeyKindCase = iArr2;
            try {
                iArr2[CreateStruct.Entry.KeyKindCase.FIELD_KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$api$expr$v1alpha1$Expr$CreateStruct$Entry$KeyKindCase[CreateStruct.Entry.KeyKindCase.MAP_KEY.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$api$expr$v1alpha1$Expr$CreateStruct$Entry$KeyKindCase[CreateStruct.Entry.KeyKindCase.KEYKIND_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }
}
