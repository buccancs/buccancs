package org.apache.commons.math.exception.util;

import java.io.Serializable;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/exception/util/Localizable.class */
public interface Localizable extends Serializable {
    String getSourceString();

    String getLocalizedString(Locale locale);
}
