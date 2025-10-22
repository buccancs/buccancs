package io.grpc.xds.internal.rbac.engine.cel;

/* loaded from: classes3.dex */
public interface Interpretable {
    Object eval(Activation activation) throws InterpreterException;
}
