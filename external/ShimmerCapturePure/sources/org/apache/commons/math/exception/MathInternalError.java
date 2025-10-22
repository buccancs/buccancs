package org.apache.commons.math.exception;

import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/exception/MathInternalError.class */
public class MathInternalError extends MathIllegalStateException {
    private static final long serialVersionUID = -6276776513966934846L;
    private static final String REPORT_URL = "https://issues.apache.org/jira/browse/MATH";

    public MathInternalError() {
        super(LocalizedFormats.INTERNAL_ERROR, REPORT_URL);
    }

    public MathInternalError(Throwable cause) {
        super(LocalizedFormats.INTERNAL_ERROR, REPORT_URL);
    }
}
