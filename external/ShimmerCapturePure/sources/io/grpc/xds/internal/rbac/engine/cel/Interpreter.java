package io.grpc.xds.internal.rbac.engine.cel;

import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr;

/* loaded from: classes3.dex */
public interface Interpreter {
    Interpretable createInterpretable(Expr expr) throws InterpreterException;
}
