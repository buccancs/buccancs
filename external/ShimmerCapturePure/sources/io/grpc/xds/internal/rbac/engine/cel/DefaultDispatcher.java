package io.grpc.xds.internal.rbac.engine.cel;

import java.util.List;

/* loaded from: classes3.dex */
public class DefaultDispatcher implements Dispatcher {
    public static DefaultDispatcher create() {
        return new DefaultDispatcher();
    }

    @Override // io.grpc.xds.internal.rbac.engine.cel.Dispatcher
    public Object dispatch(Metadata metadata, long j, String str, List<String> list, Object[] objArr) throws InterpreterException {
        return new Object();
    }
}
