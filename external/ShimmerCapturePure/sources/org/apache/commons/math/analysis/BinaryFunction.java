package org.apache.commons.math.analysis;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
@Deprecated
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/BinaryFunction.class */
public abstract class BinaryFunction implements BivariateRealFunction {
    public static final BinaryFunction ADD = new BinaryFunction() { // from class: org.apache.commons.math.analysis.BinaryFunction.1
        @Override
        // org.apache.commons.math.analysis.BinaryFunction, org.apache.commons.math.analysis.BivariateRealFunction
        public double value(double x, double y) {
            return x + y;
        }
    };
    public static final BinaryFunction SUBTRACT = new BinaryFunction() { // from class: org.apache.commons.math.analysis.BinaryFunction.2
        @Override
        // org.apache.commons.math.analysis.BinaryFunction, org.apache.commons.math.analysis.BivariateRealFunction
        public double value(double x, double y) {
            return x - y;
        }
    };
    public static final BinaryFunction MULTIPLY = new BinaryFunction() { // from class: org.apache.commons.math.analysis.BinaryFunction.3
        @Override
        // org.apache.commons.math.analysis.BinaryFunction, org.apache.commons.math.analysis.BivariateRealFunction
        public double value(double x, double y) {
            return x * y;
        }
    };
    public static final BinaryFunction DIVIDE = new BinaryFunction() { // from class: org.apache.commons.math.analysis.BinaryFunction.4
        @Override
        // org.apache.commons.math.analysis.BinaryFunction, org.apache.commons.math.analysis.BivariateRealFunction
        public double value(double x, double y) {
            return x / y;
        }
    };
    public static final BinaryFunction POW = new BinaryFunction() { // from class: org.apache.commons.math.analysis.BinaryFunction.5
        @Override
        // org.apache.commons.math.analysis.BinaryFunction, org.apache.commons.math.analysis.BivariateRealFunction
        public double value(double x, double y) {
            return FastMath.pow(x, y);
        }
    };
    public static final BinaryFunction ATAN2 = new BinaryFunction() { // from class: org.apache.commons.math.analysis.BinaryFunction.6
        @Override
        // org.apache.commons.math.analysis.BinaryFunction, org.apache.commons.math.analysis.BivariateRealFunction
        public double value(double x, double y) {
            return FastMath.atan2(x, y);
        }
    };

    @Override // org.apache.commons.math.analysis.BivariateRealFunction
    public abstract double value(double d, double d2) throws FunctionEvaluationException;

    public ComposableFunction fix1stArgument(final double fixedX) {
        return new ComposableFunction() { // from class: org.apache.commons.math.analysis.BinaryFunction.7
            @Override
            // org.apache.commons.math.analysis.ComposableFunction, org.apache.commons.math.analysis.UnivariateRealFunction
            public double value(double x) throws FunctionEvaluationException {
                return BinaryFunction.this.value(fixedX, x);
            }
        };
    }

    public ComposableFunction fix2ndArgument(final double fixedY) {
        return new ComposableFunction() { // from class: org.apache.commons.math.analysis.BinaryFunction.8
            @Override
            // org.apache.commons.math.analysis.ComposableFunction, org.apache.commons.math.analysis.UnivariateRealFunction
            public double value(double x) throws FunctionEvaluationException {
                return BinaryFunction.this.value(x, fixedY);
            }
        };
    }
}
