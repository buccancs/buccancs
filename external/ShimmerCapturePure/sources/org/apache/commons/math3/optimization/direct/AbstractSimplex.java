package org.apache.commons.math3.optimization.direct;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.PointValuePair;

@Deprecated
/* loaded from: classes5.dex */
public abstract class AbstractSimplex implements OptimizationData {
    private final int dimension;
    private PointValuePair[] simplex;
    private double[][] startConfiguration;

    protected AbstractSimplex(int i) {
        this(i, 1.0d);
    }

    protected AbstractSimplex(int i, double d) {
        this(createHypercubeSteps(i, d));
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0040, code lost:

        r1 = r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected AbstractSimplex(double[] r10) {
        /*
            r9 = this;
            r9.<init>()
            if (r10 == 0) goto L49
            int r0 = r10.length
            if (r0 == 0) goto L43
            int r0 = r10.length
            r9.dimension = r0
            int[] r0 = new int[]{r0, r0}
            java.lang.Class r1 = java.lang.Double.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r1, r0)
            double[][] r0 = (double[][]) r0
            r9.startConfiguration = r0
            r0 = 0
            r1 = 0
        L1b:
            int r2 = r9.dimension
            if (r1 >= r2) goto L42
            double[][] r2 = r9.startConfiguration
            r2 = r2[r1]
            r3 = 0
        L24:
            int r4 = r1 + 1
            if (r3 >= r4) goto L40
            r4 = r10[r3]
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L36
            int r3 = r3 + 1
            java.lang.System.arraycopy(r10, r0, r2, r0, r3)
            goto L24
        L36:
            org.apache.commons.math3.exception.ZeroException r10 = new org.apache.commons.math3.exception.ZeroException
            org.apache.commons.math3.exception.util.LocalizedFormats r1 = org.apache.commons.math3.exception.util.LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r10.<init>(r1, r0)
            throw r10
        L40:
            r1 = r4
            goto L1b
        L42:
            return
        L43:
            org.apache.commons.math3.exception.ZeroException r10 = new org.apache.commons.math3.exception.ZeroException
            r10.<init>()
            throw r10
        L49:
            org.apache.commons.math3.exception.NullArgumentException r10 = new org.apache.commons.math3.exception.NullArgumentException
            r10.<init>()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.direct.AbstractSimplex.<init>(double[]):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0039, code lost:

        r5 = r5 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected AbstractSimplex(double[][] r14) {
        /*
            r13 = this;
            r13.<init>()
            int r0 = r14.length
            if (r0 <= 0) goto L7a
            int r0 = r14.length
            r1 = 1
            int r0 = r0 - r1
            r13.dimension = r0
            int[] r0 = new int[]{r0, r0}
            java.lang.Class r2 = java.lang.Double.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r2, r0)
            double[][] r0 = (double[][]) r0
            r13.startConfiguration = r0
            r0 = 0
            r2 = r14[r0]
            r3 = 0
        L1d:
            int r4 = r14.length
            if (r3 >= r4) goto L79
            r4 = r14[r3]
            int r5 = r4.length
            int r6 = r13.dimension
            if (r5 != r6) goto L70
            r5 = 0
        L28:
            if (r5 >= r3) goto L56
            r6 = r14[r5]
            r7 = 0
        L2d:
            int r8 = r13.dimension
            if (r7 >= r8) goto L3f
            r8 = r4[r7]
            r10 = r6[r7]
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 == 0) goto L3c
            int r5 = r5 + 1
            goto L28
        L3c:
            int r7 = r7 + 1
            goto L2d
        L3f:
            org.apache.commons.math3.exception.MathIllegalArgumentException r14 = new org.apache.commons.math3.exception.MathIllegalArgumentException
            org.apache.commons.math3.exception.util.LocalizedFormats r2 = org.apache.commons.math3.exception.util.LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r4[r0] = r3
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            r4[r1] = r0
            r14.<init>(r2, r4)
            throw r14
        L56:
            if (r3 <= 0) goto L6d
            double[][] r5 = r13.startConfiguration
            int r6 = r3 + (-1)
            r5 = r5[r6]
            r6 = 0
        L5f:
            int r7 = r13.dimension
            if (r6 >= r7) goto L6d
            r7 = r4[r6]
            r9 = r2[r6]
            double r7 = r7 - r9
            r5[r6] = r7
            int r6 = r6 + 1
            goto L5f
        L6d:
            int r3 = r3 + 1
            goto L1d
        L70:
            org.apache.commons.math3.exception.DimensionMismatchException r14 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r0 = r4.length
            int r1 = r13.dimension
            r14.<init>(r0, r1)
            throw r14
        L79:
            return
        L7a:
            org.apache.commons.math3.exception.NotStrictlyPositiveException r0 = new org.apache.commons.math3.exception.NotStrictlyPositiveException
            org.apache.commons.math3.exception.util.LocalizedFormats r1 = org.apache.commons.math3.exception.util.LocalizedFormats.SIMPLEX_NEED_ONE_POINT
            int r14 = r14.length
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            r0.<init>(r1, r14)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.direct.AbstractSimplex.<init>(double[][]):void");
    }

    private static double[] createHypercubeSteps(int i, double d) {
        double[] dArr = new double[i];
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2] = d;
        }
        return dArr;
    }

    public int getDimension() {
        return this.dimension;
    }

    public abstract void iterate(MultivariateFunction multivariateFunction, Comparator<PointValuePair> comparator);

    public int getSize() {
        return this.simplex.length;
    }

    public void build(double[] dArr) {
        int i = this.dimension;
        if (i != dArr.length) {
            throw new DimensionMismatchException(this.dimension, dArr.length);
        }
        PointValuePair[] pointValuePairArr = new PointValuePair[i + 1];
        this.simplex = pointValuePairArr;
        pointValuePairArr[0] = new PointValuePair(dArr, Double.NaN);
        int i2 = 0;
        while (true) {
            int i3 = this.dimension;
            if (i2 >= i3) {
                return;
            }
            double[] dArr2 = this.startConfiguration[i2];
            double[] dArr3 = new double[i3];
            for (int i4 = 0; i4 < this.dimension; i4++) {
                dArr3[i4] = dArr[i4] + dArr2[i4];
            }
            i2++;
            this.simplex[i2] = new PointValuePair(dArr3, Double.NaN);
        }
    }

    public void evaluate(MultivariateFunction multivariateFunction, Comparator<PointValuePair> comparator) {
        int i = 0;
        while (true) {
            PointValuePair[] pointValuePairArr = this.simplex;
            if (i < pointValuePairArr.length) {
                PointValuePair pointValuePair = pointValuePairArr[i];
                double[] pointRef = pointValuePair.getPointRef();
                if (Double.isNaN(pointValuePair.getValue().doubleValue())) {
                    this.simplex[i] = new PointValuePair(pointRef, multivariateFunction.value(pointRef), false);
                }
                i++;
            } else {
                Arrays.sort(pointValuePairArr, comparator);
                return;
            }
        }
    }

    protected void replaceWorstPoint(PointValuePair pointValuePair, Comparator<PointValuePair> comparator) {
        int i = 0;
        while (true) {
            int i2 = this.dimension;
            if (i < i2) {
                if (comparator.compare(this.simplex[i], pointValuePair) > 0) {
                    PointValuePair[] pointValuePairArr = this.simplex;
                    PointValuePair pointValuePair2 = pointValuePairArr[i];
                    pointValuePairArr[i] = pointValuePair;
                    pointValuePair = pointValuePair2;
                }
                i++;
            } else {
                this.simplex[i2] = pointValuePair;
                return;
            }
        }
    }

    public PointValuePair[] getPoints() {
        PointValuePair[] pointValuePairArr = this.simplex;
        PointValuePair[] pointValuePairArr2 = new PointValuePair[pointValuePairArr.length];
        System.arraycopy(pointValuePairArr, 0, pointValuePairArr2, 0, pointValuePairArr.length);
        return pointValuePairArr2;
    }

    protected void setPoints(PointValuePair[] pointValuePairArr) {
        if (pointValuePairArr.length != this.simplex.length) {
            throw new DimensionMismatchException(pointValuePairArr.length, this.simplex.length);
        }
        this.simplex = pointValuePairArr;
    }

    public PointValuePair getPoint(int i) {
        if (i >= 0) {
            PointValuePair[] pointValuePairArr = this.simplex;
            if (i < pointValuePairArr.length) {
                return pointValuePairArr[i];
            }
        }
        throw new OutOfRangeException(Integer.valueOf(i), 0, Integer.valueOf(this.simplex.length - 1));
    }

    protected void setPoint(int i, PointValuePair pointValuePair) {
        if (i >= 0) {
            PointValuePair[] pointValuePairArr = this.simplex;
            if (i < pointValuePairArr.length) {
                pointValuePairArr[i] = pointValuePair;
                return;
            }
        }
        throw new OutOfRangeException(Integer.valueOf(i), 0, Integer.valueOf(this.simplex.length - 1));
    }
}
