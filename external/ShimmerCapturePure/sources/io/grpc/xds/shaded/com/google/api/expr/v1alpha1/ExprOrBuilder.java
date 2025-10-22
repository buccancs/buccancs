package io.grpc.xds.shaded.com.google.api.expr.v1alpha1;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr;

/* loaded from: classes3.dex */
public interface ExprOrBuilder extends MessageOrBuilder {
    Expr.Call getCallExpr();

    Expr.CallOrBuilder getCallExprOrBuilder();

    Expr.Comprehension getComprehensionExpr();

    Expr.ComprehensionOrBuilder getComprehensionExprOrBuilder();

    Constant getConstExpr();

    ConstantOrBuilder getConstExprOrBuilder();

    Expr.ExprKindCase getExprKindCase();

    long getId();

    Expr.Ident getIdentExpr();

    Expr.IdentOrBuilder getIdentExprOrBuilder();

    Expr.CreateList getListExpr();

    Expr.CreateListOrBuilder getListExprOrBuilder();

    Expr.Select getSelectExpr();

    Expr.SelectOrBuilder getSelectExprOrBuilder();

    Expr.CreateStruct getStructExpr();

    Expr.CreateStructOrBuilder getStructExprOrBuilder();

    boolean hasCallExpr();

    boolean hasComprehensionExpr();

    boolean hasConstExpr();

    boolean hasIdentExpr();

    boolean hasListExpr();

    boolean hasSelectExpr();

    boolean hasStructExpr();
}
