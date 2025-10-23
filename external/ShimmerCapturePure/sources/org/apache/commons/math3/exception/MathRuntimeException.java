package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.ExceptionContext;
import org.apache.commons.math3.exception.util.ExceptionContextProvider;
import org.apache.commons.math3.exception.util.Localizable;

/* loaded from: classes5.dex */
public class MathRuntimeException extends RuntimeException implements ExceptionContextProvider {
    private static final long serialVersionUID = 20120926;
    private final ExceptionContext context;

    public MathRuntimeException(Localizable localizable, Object... objArr) {
        ExceptionContext exceptionContext = new ExceptionContext(this);
        this.context = exceptionContext;
        exceptionContext.addMessage(localizable, objArr);
    }

    @Override // org.apache.commons.math3.exception.util.ExceptionContextProvider
    public ExceptionContext getContext() {
        return this.context;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.context.getMessage();
    }

    @Override // java.lang.Throwable
    public String getLocalizedMessage() {
        return this.context.getLocalizedMessage();
    }
}
