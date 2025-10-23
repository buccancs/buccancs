package org.apache.commons.math.optimization;

import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/SimpleRealPointChecker.class */
public class SimpleRealPointChecker implements RealConvergenceChecker {
    private static final double DEFAULT_RELATIVE_THRESHOLD = 1.1102230246251565E-14d;
    private static final double DEFAULT_ABSOLUTE_THRESHOLD = 2.2250738585072014E-306d;
    private final double relativeThreshold;
    private final double absoluteThreshold;

    public SimpleRealPointChecker() {
        this.relativeThreshold = DEFAULT_RELATIVE_THRESHOLD;
        this.absoluteThreshold = DEFAULT_ABSOLUTE_THRESHOLD;
    }

    public SimpleRealPointChecker(double relativeThreshold, double absoluteThreshold) {
        this.relativeThreshold = relativeThreshold;
        this.absoluteThreshold = absoluteThreshold;
    }

    @Override // org.apache.commons.math.optimization.RealConvergenceChecker
    public boolean converged(int iteration, RealPointValuePair previous, RealPointValuePair current) {
        double[] p = previous.getPoint();
        double[] c = current.getPoint();
        for (int i = 0; i < p.length; i++) {
            double difference = FastMath.abs(p[i] - c[i]);
            double size = FastMath.max(FastMath.abs(p[i]), FastMath.abs(c[i]));
            if (difference > size * this.relativeThreshold && difference > this.absoluteThreshold) {
                return false;
            }
        }
        return true;
    }
}
