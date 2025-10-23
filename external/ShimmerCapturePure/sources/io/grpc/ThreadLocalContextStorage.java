package io.grpc;

import io.grpc.Context;

import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
final class ThreadLocalContextStorage extends Context.Storage {
    static final ThreadLocal<Context> localContext = new ThreadLocal<>();
    private static final Logger log = Logger.getLogger(ThreadLocalContextStorage.class.getName());

    ThreadLocalContextStorage() {
    }

    @Override // io.grpc.Context.Storage
    public Context doAttach(Context context) {
        Context contextCurrent = current();
        localContext.set(context);
        return contextCurrent;
    }

    @Override // io.grpc.Context.Storage
    public void detach(Context context, Context context2) {
        if (current() != context) {
            log.log(Level.SEVERE, "Context was not attached when detaching", new Throwable().fillInStackTrace());
        }
        if (context2 != Context.ROOT) {
            localContext.set(context2);
        } else {
            localContext.set(null);
        }
    }

    @Override // io.grpc.Context.Storage
    public Context current() {
        Context context = localContext.get();
        return context == null ? Context.ROOT : context;
    }
}
