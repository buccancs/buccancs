package io.grpc.xds.shaded.com.google.api.expr.v1alpha1;

import com.google.protobuf.MessageOrBuilder;

import java.util.Map;

/* loaded from: classes3.dex */
public interface CheckedExprOrBuilder extends MessageOrBuilder {
    boolean containsReferenceMap(long j);

    boolean containsTypeMap(long j);

    Expr getExpr();

    ExprOrBuilder getExprOrBuilder();

    @Deprecated
    Map<Long, Reference> getReferenceMap();

    int getReferenceMapCount();

    Map<Long, Reference> getReferenceMapMap();

    Reference getReferenceMapOrDefault(long j, Reference reference);

    Reference getReferenceMapOrThrow(long j);

    SourceInfo getSourceInfo();

    SourceInfoOrBuilder getSourceInfoOrBuilder();

    @Deprecated
    Map<Long, Type> getTypeMap();

    int getTypeMapCount();

    Map<Long, Type> getTypeMapMap();

    Type getTypeMapOrDefault(long j, Type type);

    Type getTypeMapOrThrow(long j);

    boolean hasExpr();

    boolean hasSourceInfo();
}
