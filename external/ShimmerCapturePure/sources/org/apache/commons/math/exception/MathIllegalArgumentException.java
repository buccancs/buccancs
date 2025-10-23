package org.apache.commons.math.exception;

import java.util.Locale;

import org.apache.commons.math.exception.util.ArgUtils;
import org.apache.commons.math.exception.util.Localizable;
import org.apache.commons.math.exception.util.MessageFactory;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/exception/MathIllegalArgumentException.class */
public class MathIllegalArgumentException extends IllegalArgumentException implements MathThrowable {
    private static final long serialVersionUID = -6024911025449780478L;
    private final Localizable specific;
    private final Localizable general;
    private final Object[] arguments;

    protected MathIllegalArgumentException(Localizable specific, Localizable general, Object... args) {
        this.specific = specific;
        this.general = general;
        this.arguments = ArgUtils.flatten(args);
    }

    protected MathIllegalArgumentException(Localizable general, Object... args) {
        this(null, general, args);
    }

    @Override // org.apache.commons.math.exception.MathThrowable
    public Localizable getSpecificPattern() {
        return this.specific;
    }

    @Override // org.apache.commons.math.exception.MathThrowable
    public Localizable getGeneralPattern() {
        return this.general;
    }

    @Override // org.apache.commons.math.exception.MathThrowable
    public Object[] getArguments() {
        return (Object[]) this.arguments.clone();
    }

    @Override // org.apache.commons.math.exception.MathThrowable
    public String getMessage(Locale locale) {
        return MessageFactory.buildMessage(locale, this.specific, this.general, this.arguments);
    }

    @Override // java.lang.Throwable, org.apache.commons.math.exception.MathThrowable
    public String getMessage() {
        return getMessage(Locale.US);
    }

    @Override // java.lang.Throwable, org.apache.commons.math.exception.MathThrowable
    public String getLocalizedMessage() {
        return getMessage(Locale.getDefault());
    }
}
