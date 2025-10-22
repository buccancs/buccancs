package io.grpc.xds.internal.rbac.engine.cel;

import java.util.List;

/* loaded from: classes3.dex */
public interface Dispatcher {
    Object dispatch(Metadata metadata, long j, String str, List<String> list, Object[] objArr) throws InterpreterException;
}
