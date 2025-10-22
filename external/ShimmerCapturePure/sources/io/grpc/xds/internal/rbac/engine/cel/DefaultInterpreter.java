package io.grpc.xds.internal.rbac.engine.cel;

import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr;

/* loaded from: classes3.dex */
public class DefaultInterpreter implements Interpreter {
    public DefaultInterpreter(RuntimeTypeProvider runtimeTypeProvider, Dispatcher dispatcher) {
    }

    @Override // io.grpc.xds.internal.rbac.engine.cel.Interpreter
    public Interpretable createInterpretable(Expr expr) throws InterpreterException {
        return new DefaultInterpretable(expr);
    }

    private static class DefaultInterpretable implements Interpretable {
        public DefaultInterpretable(Expr expr) {
        }

        @Override // io.grpc.xds.internal.rbac.engine.cel.Interpretable
        public Object eval(Activation activation) throws InterpreterException {
            return new Object();
        }
    }
}
