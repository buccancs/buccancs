package org.apache.commons.math.optimization.linear;

import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.optimization.OptimizationException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/linear/UnboundedSolutionException.class */
public class UnboundedSolutionException extends OptimizationException {
    private static final long serialVersionUID = 940539497277290619L;

    public UnboundedSolutionException() {
        super(LocalizedFormats.UNBOUNDED_SOLUTION, new Object[0]);
    }
}
