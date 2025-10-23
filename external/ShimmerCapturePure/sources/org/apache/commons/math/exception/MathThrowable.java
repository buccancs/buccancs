package org.apache.commons.math.exception;

import java.util.Locale;

import org.apache.commons.math.exception.util.Localizable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/exception/MathThrowable.class */
public interface MathThrowable {
    Localizable getSpecificPattern();

    Localizable getGeneralPattern();

    Object[] getArguments();

    String getMessage(Locale locale);

    String getMessage();

    String getLocalizedMessage();
}
