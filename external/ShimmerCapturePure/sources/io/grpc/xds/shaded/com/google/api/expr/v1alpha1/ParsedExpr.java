package io.grpc.xds.shaded.com.google.api.expr.v1alpha1;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr;
import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.SourceInfo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class ParsedExpr extends GeneratedMessageV3 implements ParsedExprOrBuilder {
    public static final int EXPR_FIELD_NUMBER = 2;
    public static final int SOURCE_INFO_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final ParsedExpr DEFAULT_INSTANCE = new ParsedExpr();
    private static final Parser<ParsedExpr> PARSER = new AbstractParser<ParsedExpr>() { // from class: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExpr.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ParsedExpr m11101parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ParsedExpr(codedInputStream, extensionRegistryLite);
        }
    };
    private Expr expr_;
    private byte memoizedIsInitialized;
    private SourceInfo sourceInfo_;

    private ParsedExpr(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ParsedExpr() {
        this.memoizedIsInitialized = (byte) -1;
    }

    private ParsedExpr(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 18) {
                            Expr expr = this.expr_;
                            Expr.Builder builderM10731toBuilder = expr != null ? expr.m10731toBuilder() : null;
                            Expr expr2 = (Expr) codedInputStream.readMessage(Expr.parser(), extensionRegistryLite);
                            this.expr_ = expr2;
                            if (builderM10731toBuilder != null) {
                                builderM10731toBuilder.mergeFrom(expr2);
                                this.expr_ = builderM10731toBuilder.m10738buildPartial();
                            }
                        } else if (tag == 26) {
                            SourceInfo sourceInfo = this.sourceInfo_;
                            SourceInfo.Builder builderM11192toBuilder = sourceInfo != null ? sourceInfo.m11192toBuilder() : null;
                            SourceInfo sourceInfo2 = (SourceInfo) codedInputStream.readMessage(SourceInfo.parser(), extensionRegistryLite);
                            this.sourceInfo_ = sourceInfo2;
                            if (builderM11192toBuilder != null) {
                                builderM11192toBuilder.mergeFrom(sourceInfo2);
                                this.sourceInfo_ = builderM11192toBuilder.m11199buildPartial();
                            }
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

    public static ParsedExpr getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ParsedExpr> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SyntaxProto.internal_static_google_api_expr_v1alpha1_ParsedExpr_descriptor;
    }

    public static ParsedExpr parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ParsedExpr) PARSER.parseFrom(byteBuffer);
    }

    public static ParsedExpr parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ParsedExpr) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ParsedExpr parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ParsedExpr) PARSER.parseFrom(byteString);
    }

    public static ParsedExpr parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ParsedExpr) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ParsedExpr parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ParsedExpr) PARSER.parseFrom(bArr);
    }

    public static ParsedExpr parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ParsedExpr) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ParsedExpr parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ParsedExpr parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ParsedExpr parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ParsedExpr parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ParsedExpr parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ParsedExpr parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m11099toBuilder();
    }

    public static Builder newBuilder(ParsedExpr parsedExpr) {
        return DEFAULT_INSTANCE.m11099toBuilder().mergeFrom(parsedExpr);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ParsedExpr m11094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ParsedExpr> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExprOrBuilder
    public boolean hasExpr() {
        return this.expr_ != null;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExprOrBuilder
    public boolean hasSourceInfo() {
        return this.sourceInfo_ != null;
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
        return new ParsedExpr();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SyntaxProto.internal_static_google_api_expr_v1alpha1_ParsedExpr_fieldAccessorTable.ensureFieldAccessorsInitialized(ParsedExpr.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExprOrBuilder
    public Expr getExpr() {
        Expr expr = this.expr_;
        return expr == null ? Expr.getDefaultInstance() : expr;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExprOrBuilder
    public ExprOrBuilder getExprOrBuilder() {
        return getExpr();
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExprOrBuilder
    public SourceInfo getSourceInfo() {
        SourceInfo sourceInfo = this.sourceInfo_;
        return sourceInfo == null ? SourceInfo.getDefaultInstance() : sourceInfo;
    }

    @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExprOrBuilder
    public SourceInfoOrBuilder getSourceInfoOrBuilder() {
        return getSourceInfo();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.expr_ != null) {
            codedOutputStream.writeMessage(2, getExpr());
        }
        if (this.sourceInfo_ != null) {
            codedOutputStream.writeMessage(3, getSourceInfo());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.expr_ != null ? CodedOutputStream.computeMessageSize(2, getExpr()) : 0;
        if (this.sourceInfo_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getSourceInfo());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ParsedExpr)) {
            return super.equals(obj);
        }
        ParsedExpr parsedExpr = (ParsedExpr) obj;
        if (hasExpr() != parsedExpr.hasExpr()) {
            return false;
        }
        if ((!hasExpr() || getExpr().equals(parsedExpr.getExpr())) && hasSourceInfo() == parsedExpr.hasSourceInfo()) {
            return (!hasSourceInfo() || getSourceInfo().equals(parsedExpr.getSourceInfo())) && this.unknownFields.equals(parsedExpr.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasExpr()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getExpr().hashCode();
        }
        if (hasSourceInfo()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getSourceInfo().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m11096newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m11099toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ParsedExprOrBuilder {
        private SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> exprBuilder_;
        private Expr expr_;
        private SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> sourceInfoBuilder_;
        private SourceInfo sourceInfo_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_ParsedExpr_descriptor;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExprOrBuilder
        public boolean hasExpr() {
            return (this.exprBuilder_ == null && this.expr_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExprOrBuilder
        public boolean hasSourceInfo() {
            return (this.sourceInfoBuilder_ == null && this.sourceInfo_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_ParsedExpr_fieldAccessorTable.ensureFieldAccessorsInitialized(ParsedExpr.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ParsedExpr.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11110clear() {
            super.clear();
            if (this.exprBuilder_ == null) {
                this.expr_ = null;
            } else {
                this.expr_ = null;
                this.exprBuilder_ = null;
            }
            if (this.sourceInfoBuilder_ == null) {
                this.sourceInfo_ = null;
            } else {
                this.sourceInfo_ = null;
                this.sourceInfoBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return SyntaxProto.internal_static_google_api_expr_v1alpha1_ParsedExpr_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParsedExpr m11123getDefaultInstanceForType() {
            return ParsedExpr.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParsedExpr m11104build() throws UninitializedMessageException {
            ParsedExpr parsedExprM11106buildPartial = m11106buildPartial();
            if (parsedExprM11106buildPartial.isInitialized()) {
                return parsedExprM11106buildPartial;
            }
            throw newUninitializedMessageException(parsedExprM11106buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParsedExpr m11106buildPartial() {
            ParsedExpr parsedExpr = new ParsedExpr(this);
            SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> singleFieldBuilderV3 = this.exprBuilder_;
            if (singleFieldBuilderV3 == null) {
                parsedExpr.expr_ = this.expr_;
            } else {
                parsedExpr.expr_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV32 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV32 == null) {
                parsedExpr.sourceInfo_ = this.sourceInfo_;
            } else {
                parsedExpr.sourceInfo_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return parsedExpr;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11122clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11134setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11112clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11115clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11136setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11102addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m11127mergeFrom(Message message) {
            if (message instanceof ParsedExpr) {
                return mergeFrom((ParsedExpr) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ParsedExpr parsedExpr) {
            if (parsedExpr == ParsedExpr.getDefaultInstance()) {
                return this;
            }
            if (parsedExpr.hasExpr()) {
                mergeExpr(parsedExpr.getExpr());
            }
            if (parsedExpr.hasSourceInfo()) {
                mergeSourceInfo(parsedExpr.getSourceInfo());
            }
            m11132mergeUnknownFields(parsedExpr.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExpr.Builder m11128mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExpr.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExpr r3 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExpr) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExpr r4 = (io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExpr) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExpr.Builder.m11128mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExpr$Builder");
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExprOrBuilder
        public Expr getExpr() {
            SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> singleFieldBuilderV3 = this.exprBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Expr expr = this.expr_;
            return expr == null ? Expr.getDefaultInstance() : expr;
        }

        public Builder setExpr(Expr expr) {
            SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> singleFieldBuilderV3 = this.exprBuilder_;
            if (singleFieldBuilderV3 == null) {
                expr.getClass();
                this.expr_ = expr;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(expr);
            }
            return this;
        }

        public Builder setExpr(Expr.Builder builder) {
            SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> singleFieldBuilderV3 = this.exprBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.expr_ = builder.m10736build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m10736build());
            }
            return this;
        }

        public Builder mergeExpr(Expr expr) {
            SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> singleFieldBuilderV3 = this.exprBuilder_;
            if (singleFieldBuilderV3 == null) {
                Expr expr2 = this.expr_;
                if (expr2 != null) {
                    this.expr_ = Expr.newBuilder(expr2).mergeFrom(expr).m10738buildPartial();
                } else {
                    this.expr_ = expr;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(expr);
            }
            return this;
        }

        public Builder clearExpr() {
            if (this.exprBuilder_ == null) {
                this.expr_ = null;
                onChanged();
            } else {
                this.expr_ = null;
                this.exprBuilder_ = null;
            }
            return this;
        }

        public Expr.Builder getExprBuilder() {
            onChanged();
            return getExprFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExprOrBuilder
        public ExprOrBuilder getExprOrBuilder() {
            SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> singleFieldBuilderV3 = this.exprBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ExprOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Expr expr = this.expr_;
            return expr == null ? Expr.getDefaultInstance() : expr;
        }

        private SingleFieldBuilderV3<Expr, Expr.Builder, ExprOrBuilder> getExprFieldBuilder() {
            if (this.exprBuilder_ == null) {
                this.exprBuilder_ = new SingleFieldBuilderV3<>(getExpr(), getParentForChildren(), isClean());
                this.expr_ = null;
            }
            return this.exprBuilder_;
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExprOrBuilder
        public SourceInfo getSourceInfo() {
            SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            SourceInfo sourceInfo = this.sourceInfo_;
            return sourceInfo == null ? SourceInfo.getDefaultInstance() : sourceInfo;
        }

        public Builder setSourceInfo(SourceInfo sourceInfo) {
            SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                sourceInfo.getClass();
                this.sourceInfo_ = sourceInfo;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(sourceInfo);
            }
            return this;
        }

        public Builder setSourceInfo(SourceInfo.Builder builder) {
            SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sourceInfo_ = builder.m11197build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m11197build());
            }
            return this;
        }

        public Builder mergeSourceInfo(SourceInfo sourceInfo) {
            SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                SourceInfo sourceInfo2 = this.sourceInfo_;
                if (sourceInfo2 != null) {
                    this.sourceInfo_ = SourceInfo.newBuilder(sourceInfo2).mergeFrom(sourceInfo).m11199buildPartial();
                } else {
                    this.sourceInfo_ = sourceInfo;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(sourceInfo);
            }
            return this;
        }

        public Builder clearSourceInfo() {
            if (this.sourceInfoBuilder_ == null) {
                this.sourceInfo_ = null;
                onChanged();
            } else {
                this.sourceInfo_ = null;
                this.sourceInfoBuilder_ = null;
            }
            return this;
        }

        public SourceInfo.Builder getSourceInfoBuilder() {
            onChanged();
            return getSourceInfoFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ParsedExprOrBuilder
        public SourceInfoOrBuilder getSourceInfoOrBuilder() {
            SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (SourceInfoOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            SourceInfo sourceInfo = this.sourceInfo_;
            return sourceInfo == null ? SourceInfo.getDefaultInstance() : sourceInfo;
        }

        private SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> getSourceInfoFieldBuilder() {
            if (this.sourceInfoBuilder_ == null) {
                this.sourceInfoBuilder_ = new SingleFieldBuilderV3<>(getSourceInfo(), getParentForChildren(), isClean());
                this.sourceInfo_ = null;
            }
            return this.sourceInfoBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m11138setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m11132mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
