package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.MultivariateOptimizer;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.util.FastMath;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

@Deprecated
/* loaded from: classes5.dex */
public class BOBYQAOptimizer extends BaseAbstractMultivariateSimpleBoundsOptimizer<MultivariateFunction> implements MultivariateOptimizer {
    public static final double DEFAULT_INITIAL_RADIUS = 10.0d;
    public static final double DEFAULT_STOPPING_RADIUS = 1.0E-8d;
    public static final int MINIMUM_PROBLEM_DIMENSION = 2;
    private static final double HALF = 0.5d;
    private static final double MINUS_ONE = -1.0d;
    private static final double ONE = 1.0d;
    private static final double ONE_OVER_A_THOUSAND = 0.001d;
    private static final double ONE_OVER_EIGHT = 0.125d;
    private static final double ONE_OVER_FOUR = 0.25d;
    private static final double ONE_OVER_TEN = 0.1d;
    private static final double SIXTEEN = 16.0d;
    private static final double TEN = 10.0d;
    private static final double TWO = 2.0d;
    private static final double TWO_HUNDRED_FIFTY = 250.0d;
    private static final double ZERO = 0.0d;
    private final int numberOfInterpolationPoints;
    private final double stoppingTrustRegionRadius;
    private ArrayRealVector alternativeNewPoint;
    private Array2DRowRealMatrix bMatrix;
    private double[] boundDifference;
    private ArrayRealVector currentBest;
    private ArrayRealVector fAtInterpolationPoints;
    private ArrayRealVector gradientAtTrustRegionCenter;
    private double initialTrustRegionRadius;
    private Array2DRowRealMatrix interpolationPoints;
    private boolean isMinimize;
    private ArrayRealVector lagrangeValuesAtNewPoint;
    private ArrayRealVector lowerDifference;
    private ArrayRealVector modelSecondDerivativesParameters;
    private ArrayRealVector modelSecondDerivativesValues;
    private ArrayRealVector newPoint;
    private ArrayRealVector originShift;
    private ArrayRealVector trialStepPoint;
    private int trustRegionCenterInterpolationPointIndex;
    private ArrayRealVector trustRegionCenterOffset;
    private ArrayRealVector upperDifference;
    private Array2DRowRealMatrix zMatrix;

    public BOBYQAOptimizer(int i) {
        this(i, 10.0d, 1.0E-8d);
    }

    public BOBYQAOptimizer(int i, double d, double d2) {
        super(null);
        this.numberOfInterpolationPoints = i;
        this.initialTrustRegionRadius = d;
        this.stoppingTrustRegionRadius = d2;
    }

    private static void printMethod() {
    }

    private static void printState(int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String caller(int i) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[i];
        return stackTraceElement.getMethodName() + " (at line " + stackTraceElement.getLineNumber() + ")";
    }

    @Override // org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer
    protected PointValuePair doOptimize() throws OutOfRangeException {
        double[] lowerBound = getLowerBound();
        double[] upperBound = getUpperBound();
        setup(lowerBound, upperBound);
        this.isMinimize = getGoalType() == GoalType.MINIMIZE;
        this.currentBest = new ArrayRealVector(getStartPoint());
        double dBobyqa = bobyqa(lowerBound, upperBound);
        double[] dataRef = this.currentBest.getDataRef();
        if (!this.isMinimize) {
            dBobyqa = -dBobyqa;
        }
        return new PointValuePair(dataRef, dBobyqa);
    }

    private double bobyqa(double[] dArr, double[] dArr2) throws OutOfRangeException {
        printMethod();
        int dimension = this.currentBest.getDimension();
        for (int i = 0; i < dimension; i++) {
            double d = this.boundDifference[i];
            this.lowerDifference.setEntry(i, dArr[i] - this.currentBest.getEntry(i));
            this.upperDifference.setEntry(i, dArr2[i] - this.currentBest.getEntry(i));
            if (this.lowerDifference.getEntry(i) >= (-this.initialTrustRegionRadius)) {
                if (this.lowerDifference.getEntry(i) >= 0.0d) {
                    this.currentBest.setEntry(i, dArr[i]);
                    this.lowerDifference.setEntry(i, 0.0d);
                    this.upperDifference.setEntry(i, d);
                } else {
                    this.currentBest.setEntry(i, dArr[i] + this.initialTrustRegionRadius);
                    this.lowerDifference.setEntry(i, -this.initialTrustRegionRadius);
                    this.upperDifference.setEntry(i, FastMath.max(dArr2[i] - this.currentBest.getEntry(i), this.initialTrustRegionRadius));
                }
            } else if (this.upperDifference.getEntry(i) <= this.initialTrustRegionRadius) {
                if (this.upperDifference.getEntry(i) <= 0.0d) {
                    this.currentBest.setEntry(i, dArr2[i]);
                    this.lowerDifference.setEntry(i, -d);
                    this.upperDifference.setEntry(i, 0.0d);
                } else {
                    this.currentBest.setEntry(i, dArr2[i] - this.initialTrustRegionRadius);
                    this.lowerDifference.setEntry(i, FastMath.min(dArr[i] - this.currentBest.getEntry(i), -this.initialTrustRegionRadius));
                    this.upperDifference.setEntry(i, this.initialTrustRegionRadius);
                }
            }
        }
        return bobyqb(dArr, dArr2);
    }

    /* JADX WARN: Removed duplicated region for block: B:130:0x04dd  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x04e3  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x058d  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0958  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0d6f  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x0fbb  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x0fcb  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x0fe5  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x0410 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:438:0x097e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:442:0x0a68 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:443:0x0a29 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:449:0x09bb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:452:0x042f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x040b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private double bobyqb(double[] r97, double[] r98) throws org.apache.commons.math3.exception.OutOfRangeException, org.apache.commons.math3.exception.DimensionMismatchException {
        /*
            Method dump skipped, instructions count: 4101
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb(double[], double[]):double");
    }

    /* JADX WARN: Removed duplicated region for block: B:149:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0537 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private double[] altmov(int r52, double r53) throws org.apache.commons.math3.exception.OutOfRangeException {
        /*
            Method dump skipped, instructions count: 1372
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov(int, double):double[]");
    }

    private void prelim(double[] dArr, double[] dArr2) throws OutOfRangeException {
        double d;
        int i;
        int i2;
        double d2;
        double d3;
        double d4;
        int i3;
        long j;
        double d5;
        int i4;
        int i5;
        double d6;
        printMethod();
        int dimension = this.currentBest.getDimension();
        int i6 = this.numberOfInterpolationPoints;
        int rowDimension = this.bMatrix.getRowDimension();
        double d7 = this.initialTrustRegionRadius;
        double d8 = d7 * d7;
        double d9 = 1.0d / d8;
        int i7 = dimension + 1;
        for (int i8 = 0; i8 < dimension; i8++) {
            this.originShift.setEntry(i8, this.currentBest.getEntry(i8));
            for (int i9 = 0; i9 < i6; i9++) {
                this.interpolationPoints.setEntry(i9, i8, 0.0d);
            }
            for (int i10 = 0; i10 < rowDimension; i10++) {
                this.bMatrix.setEntry(i10, i8, 0.0d);
            }
        }
        int i11 = (dimension * i7) / 2;
        for (int i12 = 0; i12 < i11; i12++) {
            this.modelSecondDerivativesValues.setEntry(i12, 0.0d);
        }
        for (int i13 = 0; i13 < i6; i13++) {
            this.modelSecondDerivativesParameters.setEntry(i13, 0.0d);
            int i14 = i6 - i7;
            for (int i15 = 0; i15 < i14; i15++) {
                this.zMatrix.setEntry(i13, i15, 0.0d);
            }
        }
        double d10 = Double.NaN;
        int i16 = 0;
        int i17 = 0;
        while (true) {
            int evaluations = getEvaluations();
            int i18 = evaluations - dimension;
            int i19 = evaluations - 1;
            int i20 = i18 - 1;
            int i21 = dimension * 2;
            if (evaluations <= i21) {
                if (evaluations < 1 || evaluations > dimension) {
                    int i22 = i16;
                    i = i18;
                    if (evaluations > dimension) {
                        double entry = this.interpolationPoints.getEntry(i, i20);
                        int i23 = i17;
                        d = d9;
                        double dMax = -this.initialTrustRegionRadius;
                        if (this.lowerDifference.getEntry(i20) == 0.0d) {
                            i = i;
                            dMax = FastMath.min(this.initialTrustRegionRadius * TWO, this.upperDifference.getEntry(i20));
                        } else {
                            i = i;
                        }
                        if (this.upperDifference.getEntry(i20) == 0.0d) {
                            dMax = FastMath.max(this.initialTrustRegionRadius * (-2.0d), this.lowerDifference.getEntry(i20));
                        }
                        this.interpolationPoints.setEntry(evaluations, i20, dMax);
                        d4 = entry;
                        double d11 = dMax;
                        i17 = i23;
                        i2 = i22;
                        d2 = d8;
                        d3 = d11;
                    } else {
                        d = d9;
                        i2 = i22;
                        d4 = 0.0d;
                    }
                } else {
                    i = i18;
                    d4 = this.initialTrustRegionRadius;
                    int i24 = i16;
                    if (this.upperDifference.getEntry(i19) == 0.0d) {
                        d4 = -d4;
                    }
                    this.interpolationPoints.setEntry(evaluations, i19, d4);
                    d = d9;
                    i2 = i24;
                }
                d2 = d8;
                d3 = 0.0d;
            } else {
                d = d9;
                i = i18;
                int i25 = (evaluations - i7) / dimension;
                i17 = (evaluations - (i25 * dimension)) - dimension;
                i2 = i25 + i17;
                if (i2 > dimension) {
                    i17 = i2 - dimension;
                    i2 = i17;
                }
                int i26 = i2 - 1;
                int i27 = i17 - 1;
                Array2DRowRealMatrix array2DRowRealMatrix = this.interpolationPoints;
                d2 = d8;
                array2DRowRealMatrix.setEntry(evaluations, i26, array2DRowRealMatrix.getEntry(i2, i26));
                Array2DRowRealMatrix array2DRowRealMatrix2 = this.interpolationPoints;
                array2DRowRealMatrix2.setEntry(evaluations, i27, array2DRowRealMatrix2.getEntry(i17, i27));
                d3 = 0.0d;
                d4 = 0.0d;
            }
            int i28 = 0;
            while (i28 < dimension) {
                int i29 = i2;
                double d12 = d3;
                int i30 = dimension;
                int i31 = i6;
                this.currentBest.setEntry(i28, FastMath.min(FastMath.max(dArr[i28], this.originShift.getEntry(i28) + this.interpolationPoints.getEntry(evaluations, i28)), dArr2[i28]));
                if (this.interpolationPoints.getEntry(evaluations, i28) == this.lowerDifference.getEntry(i28)) {
                    this.currentBest.setEntry(i28, dArr[i28]);
                }
                if (this.interpolationPoints.getEntry(evaluations, i28) == this.upperDifference.getEntry(i28)) {
                    this.currentBest.setEntry(i28, dArr2[i28]);
                }
                i28++;
                dimension = i30;
                i2 = i29;
                d3 = d12;
                i6 = i31;
            }
            int i32 = i6;
            int i33 = i2;
            double d13 = d3;
            int i34 = dimension;
            double dComputeObjectiveValue = computeObjectiveValue(this.currentBest.toArray());
            if (!this.isMinimize) {
                dComputeObjectiveValue = -dComputeObjectiveValue;
            }
            int evaluations2 = getEvaluations();
            this.fAtInterpolationPoints.setEntry(evaluations, dComputeObjectiveValue);
            if (evaluations2 == 1) {
                this.trustRegionCenterInterpolationPointIndex = 0;
                d10 = dComputeObjectiveValue;
            } else if (dComputeObjectiveValue < this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex)) {
                this.trustRegionCenterInterpolationPointIndex = evaluations;
            }
            if (evaluations2 > i21 + 1) {
                i3 = i34;
                j = 0;
                d5 = d;
                this.zMatrix.setEntry(0, i20, d5);
                this.zMatrix.setEntry(evaluations, i20, d5);
                double d14 = -d5;
                i4 = i33;
                this.zMatrix.setEntry(i4, i20, d14);
                this.zMatrix.setEntry(i17, i20, d14);
                int i35 = i4 - 1;
                this.modelSecondDerivativesValues.setEntry((((i4 * i35) / 2) + i17) - 1, (((d10 - this.fAtInterpolationPoints.getEntry(i4)) - this.fAtInterpolationPoints.getEntry(i17)) + dComputeObjectiveValue) / (this.interpolationPoints.getEntry(evaluations, i35) * this.interpolationPoints.getEntry(evaluations, i17 - 1)));
            } else if (evaluations2 < 2 || evaluations2 > i7) {
                if (evaluations2 >= i34 + 2) {
                    double d15 = (dComputeObjectiveValue - d10) / d13;
                    double d16 = d13 - d4;
                    this.modelSecondDerivativesValues.setEntry((((i + 1) * i) / 2) - 1, ((d15 - this.gradientAtTrustRegionCenter.getEntry(i20)) * TWO) / d16);
                    ArrayRealVector arrayRealVector = this.gradientAtTrustRegionCenter;
                    arrayRealVector.setEntry(i20, ((arrayRealVector.getEntry(i20) * d13) - (d15 * d4)) / d16);
                    double d17 = d4 * d13;
                    j = 0;
                    if (d17 < 0.0d) {
                        i5 = i;
                        if (dComputeObjectiveValue < this.fAtInterpolationPoints.getEntry(i5)) {
                            ArrayRealVector arrayRealVector2 = this.fAtInterpolationPoints;
                            i32 = i32;
                            i3 = i34;
                            arrayRealVector2.setEntry(evaluations, arrayRealVector2.getEntry(i5));
                            this.fAtInterpolationPoints.setEntry(i5, dComputeObjectiveValue);
                            if (this.trustRegionCenterInterpolationPointIndex == evaluations) {
                                this.trustRegionCenterInterpolationPointIndex = i5;
                            }
                            d6 = d13;
                            this.interpolationPoints.setEntry(i5, i20, d6);
                            this.interpolationPoints.setEntry(evaluations, i20, d4);
                            this.bMatrix.setEntry(0, i20, (-(d4 + d6)) / d17);
                            this.bMatrix.setEntry(evaluations, i20, (-0.5d) / this.interpolationPoints.getEntry(i5, i20));
                            Array2DRowRealMatrix array2DRowRealMatrix3 = this.bMatrix;
                            array2DRowRealMatrix3.setEntry(i5, i20, (-array2DRowRealMatrix3.getEntry(0, i20)) - this.bMatrix.getEntry(evaluations, i20));
                            this.zMatrix.setEntry(0, i20, FastMath.sqrt(TWO) / d17);
                            this.zMatrix.setEntry(evaluations, i20, FastMath.sqrt(0.5d) / d2);
                            Array2DRowRealMatrix array2DRowRealMatrix4 = this.zMatrix;
                            array2DRowRealMatrix4.setEntry(i5, i20, (-array2DRowRealMatrix4.getEntry(0, i20)) - this.zMatrix.getEntry(evaluations, i20));
                        } else {
                            i32 = i32;
                            i3 = i34;
                        }
                    } else {
                        i32 = i32;
                        i3 = i34;
                        i5 = i;
                    }
                    d6 = d13;
                    this.bMatrix.setEntry(0, i20, (-(d4 + d6)) / d17);
                    this.bMatrix.setEntry(evaluations, i20, (-0.5d) / this.interpolationPoints.getEntry(i5, i20));
                    Array2DRowRealMatrix array2DRowRealMatrix32 = this.bMatrix;
                    array2DRowRealMatrix32.setEntry(i5, i20, (-array2DRowRealMatrix32.getEntry(0, i20)) - this.bMatrix.getEntry(evaluations, i20));
                    this.zMatrix.setEntry(0, i20, FastMath.sqrt(TWO) / d17);
                    this.zMatrix.setEntry(evaluations, i20, FastMath.sqrt(0.5d) / d2);
                    Array2DRowRealMatrix array2DRowRealMatrix42 = this.zMatrix;
                    array2DRowRealMatrix42.setEntry(i5, i20, (-array2DRowRealMatrix42.getEntry(0, i20)) - this.zMatrix.getEntry(evaluations, i20));
                } else {
                    i32 = i32;
                    i3 = i34;
                    j = 0;
                }
                i4 = i33;
                d5 = d;
            } else {
                this.gradientAtTrustRegionCenter.setEntry(i19, (dComputeObjectiveValue - d10) / d4);
                if (i32 < evaluations2 + i34) {
                    double d18 = 1.0d / d4;
                    this.bMatrix.setEntry(0, i19, -d18);
                    this.bMatrix.setEntry(evaluations, i19, d18);
                    this.bMatrix.setEntry(i32 + i19, i19, (-0.5d) * d2);
                }
                i32 = i32;
                i3 = i34;
                i4 = i33;
                d5 = d;
                j = 0;
            }
            i6 = i32;
            if (getEvaluations() >= i6) {
                return;
            }
            d9 = d5;
            dimension = i3;
            i16 = i4;
            d8 = d2;
        }
    }

    private double[] trsbox(double d, ArrayRealVector arrayRealVector, ArrayRealVector arrayRealVector2, ArrayRealVector arrayRealVector3, ArrayRealVector arrayRealVector4, ArrayRealVector arrayRealVector5) throws OutOfRangeException, DimensionMismatchException {
        double d2;
        int i;
        int i2;
        double d3;
        int i3;
        double d4;
        int i4;
        int i5;
        ArrayRealVector arrayRealVector6;
        int i6;
        int i7;
        double d5;
        int i8;
        ArrayRealVector arrayRealVector7;
        int i9;
        double d6;
        int i10;
        int i11;
        int i12;
        double d7;
        int i13;
        double d8;
        double d9;
        double d10;
        int i14;
        double d11;
        double d12;
        double dMin;
        double entry;
        double entry2;
        ArrayRealVector arrayRealVector8 = arrayRealVector2;
        ArrayRealVector arrayRealVector9 = arrayRealVector3;
        ArrayRealVector arrayRealVector10 = arrayRealVector4;
        ArrayRealVector arrayRealVector11 = arrayRealVector5;
        printMethod();
        int dimension = this.currentBest.getDimension();
        int i15 = this.numberOfInterpolationPoints;
        int i16 = 0;
        for (int i17 = 0; i17 < dimension; i17++) {
            arrayRealVector8.setEntry(i17, 0.0d);
            if (this.trustRegionCenterOffset.getEntry(i17) <= this.lowerDifference.getEntry(i17)) {
                if (this.gradientAtTrustRegionCenter.getEntry(i17) >= 0.0d) {
                    arrayRealVector8.setEntry(i17, -1.0d);
                }
            } else if (this.trustRegionCenterOffset.getEntry(i17) >= this.upperDifference.getEntry(i17) && this.gradientAtTrustRegionCenter.getEntry(i17) <= 0.0d) {
                arrayRealVector8.setEntry(i17, 1.0d);
            }
            if (arrayRealVector8.getEntry(i17) != 0.0d) {
                i16++;
            }
            this.trialStepPoint.setEntry(i17, 0.0d);
            arrayRealVector.setEntry(i17, this.gradientAtTrustRegionCenter.getEntry(i17));
        }
        double d13 = d * d;
        char c = 20;
        double d14 = 0.0d;
        double d15 = 0.0d;
        double entry3 = 0.0d;
        double d16 = 0.0d;
        double d17 = 0.0d;
        double d18 = 0.0d;
        double d19 = 0.0d;
        double d20 = 0.0d;
        double d21 = 0.0d;
        double d22 = 0.0d;
        double d23 = 0.0d;
        double d24 = 0.0d;
        double d25 = 0.0d;
        int i18 = 0;
        char c2 = 20;
        int i19 = 0;
        double d26 = -1.0d;
        int i20 = -1;
        int i21 = 0;
        while (true) {
            if (c2 == c) {
                d2 = d13;
                i = i18;
                i2 = i19;
                d3 = d26;
                i3 = i20;
                d4 = d24;
                i4 = i21;
                i5 = i15;
                arrayRealVector6 = arrayRealVector9;
                printState(20);
                i6 = 30;
                d19 = 0.0d;
            } else if (c2 != 30) {
                if (c2 == '2') {
                    d5 = d13;
                    int i22 = i16;
                    int i23 = i18;
                    i8 = i19;
                    double d27 = d26;
                    int i24 = i20;
                    double d28 = d24;
                    i7 = i15;
                    arrayRealVector7 = arrayRealVector9;
                    printState(50);
                    double d29 = d5;
                    double entry4 = 0.0d;
                    double entry5 = 0.0d;
                    for (int i25 = 0; i25 < dimension; i25++) {
                        if (arrayRealVector8.getEntry(i25) == 0.0d) {
                            double entry6 = this.trialStepPoint.getEntry(i25);
                            d29 -= entry6 * entry6;
                            entry4 += arrayRealVector7.getEntry(i25) * this.trialStepPoint.getEntry(i25);
                            entry5 += arrayRealVector7.getEntry(i25) * arrayRealVector10.getEntry(i25);
                        }
                    }
                    if (d29 <= 0.0d) {
                        arrayRealVector9 = arrayRealVector7;
                        i20 = i24;
                        d24 = d28;
                        i15 = i7;
                        i19 = i8;
                        i18 = i23;
                        d26 = d27;
                        c = 20;
                        i16 = i22;
                        d13 = d5;
                        c2 = Matrix.MATRIX_TYPE_ZERO;
                    } else {
                        double dSqrt = FastMath.sqrt((d17 * d29) + (entry4 * entry4));
                        double d30 = entry4 < 0.0d ? (dSqrt - entry4) / d17 : d29 / (dSqrt + entry4);
                        double dMin2 = entry5 > 0.0d ? FastMath.min(d30, d14 / entry5) : d30;
                        int i26 = -1;
                        for (int i27 = 0; i27 < dimension; i27++) {
                            if (arrayRealVector7.getEntry(i27) != 0.0d) {
                                double entry7 = this.trustRegionCenterOffset.getEntry(i27) + this.trialStepPoint.getEntry(i27);
                                if (arrayRealVector7.getEntry(i27) > 0.0d) {
                                    entry = this.upperDifference.getEntry(i27) - entry7;
                                    entry2 = arrayRealVector7.getEntry(i27);
                                } else {
                                    entry = this.lowerDifference.getEntry(i27) - entry7;
                                    entry2 = arrayRealVector7.getEntry(i27);
                                }
                                double d31 = entry / entry2;
                                if (d31 < dMin2) {
                                    i26 = i27;
                                    dMin2 = d31;
                                }
                            }
                        }
                        if (dMin2 > 0.0d) {
                            i14 = i23 + 1;
                            d9 = d30;
                            double d32 = entry5 / d17;
                            if (i26 != -1 || d32 <= 0.0d) {
                                d6 = d28;
                                dMin = d27;
                            } else {
                                d6 = d28;
                                dMin = FastMath.min(d27, d32);
                                if (dMin == -1.0d) {
                                    dMin = d32;
                                }
                            }
                            int i28 = 0;
                            double d33 = 0.0d;
                            while (i28 < dimension) {
                                arrayRealVector.setEntry(i28, arrayRealVector.getEntry(i28) + (arrayRealVector4.getEntry(i28) * dMin2));
                                if (arrayRealVector8.getEntry(i28) == 0.0d) {
                                    double entry8 = arrayRealVector.getEntry(i28);
                                    d33 += entry8 * entry8;
                                }
                                ArrayRealVector arrayRealVector12 = this.trialStepPoint;
                                arrayRealVector12.setEntry(i28, arrayRealVector12.getEntry(i28) + (arrayRealVector7.getEntry(i28) * dMin2));
                                i28++;
                                dMin = dMin;
                            }
                            d11 = dMin;
                            double dMax = FastMath.max((d14 - ((0.5d * dMin2) * entry5)) * dMin2, 0.0d);
                            d16 += dMax;
                            d10 = 0.0d;
                            d12 = dMax;
                            double d34 = d14;
                            d14 = d33;
                            d25 = d34;
                        } else {
                            d9 = d30;
                            d6 = d28;
                            d10 = 0.0d;
                            i14 = i23;
                            d11 = d27;
                            d12 = 0.0d;
                        }
                        if (i26 >= 0) {
                            i16 = i22 + 1;
                            arrayRealVector8.setEntry(i26, 1.0d);
                            if (arrayRealVector7.getEntry(i26) < d10) {
                                arrayRealVector8.setEntry(i26, -1.0d);
                            }
                            double entry9 = this.trialStepPoint.getEntry(i26);
                            double d35 = d5 - (entry9 * entry9);
                            if (d35 <= d10) {
                                arrayRealVector10 = arrayRealVector4;
                                arrayRealVector11 = arrayRealVector5;
                                arrayRealVector9 = arrayRealVector7;
                                i20 = i26;
                                i15 = i7;
                                i19 = i8;
                                d24 = d6;
                                d26 = d11;
                                c2 = 190;
                            } else {
                                arrayRealVector10 = arrayRealVector4;
                                arrayRealVector11 = arrayRealVector5;
                                arrayRealVector9 = arrayRealVector7;
                                i20 = i26;
                                i15 = i7;
                                i19 = i8;
                                d24 = d6;
                                d26 = d11;
                                c2 = 20;
                            }
                            c = 20;
                            i18 = i14;
                            d13 = d35;
                        } else {
                            i9 = i22;
                            double d36 = d12;
                            i10 = i21;
                            if (dMin2 < d9) {
                                if (i14 != i10 && d36 > 0.01d * d16) {
                                    d19 = d14 / d25;
                                    arrayRealVector10 = arrayRealVector4;
                                    arrayRealVector11 = arrayRealVector5;
                                    i16 = i9;
                                    arrayRealVector9 = arrayRealVector7;
                                    i21 = i10;
                                    i20 = i26;
                                    i15 = i7;
                                    i19 = i8;
                                    d24 = d6;
                                    d26 = d11;
                                    c2 = 30;
                                } else {
                                    arrayRealVector10 = arrayRealVector4;
                                    arrayRealVector11 = arrayRealVector5;
                                    i16 = i9;
                                    arrayRealVector9 = arrayRealVector7;
                                    i21 = i10;
                                    i20 = i26;
                                    i15 = i7;
                                    i19 = i8;
                                    d24 = d6;
                                    d26 = d11;
                                    c2 = 190;
                                }
                                c = 20;
                                i18 = i14;
                                d13 = d5;
                            } else {
                                i20 = i26;
                                i12 = 90;
                                i11 = i14;
                                printState(i12);
                                i19 = i11;
                                i13 = 100;
                                d7 = 0.0d;
                            }
                        }
                    }
                } else if (c2 == 'Z') {
                    i7 = i15;
                    d5 = d13;
                    i8 = i19;
                    arrayRealVector7 = arrayRealVector9;
                    i9 = i16;
                    d6 = d24;
                    i10 = i21;
                    i11 = i18;
                    i12 = 90;
                    printState(i12);
                    i19 = i11;
                    i13 = 100;
                    d7 = 0.0d;
                } else if (c2 == 'd') {
                    d5 = d13;
                    i8 = i19;
                    d7 = d26;
                    i7 = i15;
                    arrayRealVector7 = arrayRealVector9;
                    i9 = i16;
                    d6 = d24;
                    i10 = i21;
                    i19 = i18;
                    i13 = 100;
                } else if (c2 == 'x') {
                    double d37 = d13;
                    int i29 = i16;
                    int i30 = i18;
                    int i31 = i19;
                    double d38 = d26;
                    int i32 = i20;
                    double d39 = d24;
                    int i33 = i15;
                    printState(120);
                    int i34 = i30 + 1;
                    double d40 = (d14 * d15) - (entry3 * entry3);
                    if (d40 <= 1.0E-4d * d16 * d16) {
                        arrayRealVector9 = arrayRealVector3;
                        i20 = i32;
                        d24 = d39;
                        i15 = i33;
                        i19 = i31;
                        d13 = d37;
                        d26 = d38;
                        c = 20;
                        i18 = i34;
                        i16 = i29;
                        c2 = 190;
                    } else {
                        double dSqrt2 = FastMath.sqrt(d40);
                        int i35 = 0;
                        while (i35 < dimension) {
                            int i36 = i34;
                            if (arrayRealVector8.getEntry(i35) == 0.0d) {
                                arrayRealVector3.setEntry(i35, ((this.trialStepPoint.getEntry(i35) * entry3) - (arrayRealVector.getEntry(i35) * d15)) / dSqrt2);
                            } else {
                                arrayRealVector3.setEntry(i35, 0.0d);
                            }
                            i35++;
                            i34 = i36;
                        }
                        int i37 = i34;
                        double d41 = 0.0d;
                        double d42 = -dSqrt2;
                        d24 = d39;
                        int i38 = 0;
                        d18 = 1.0d;
                        i20 = -1;
                        while (true) {
                            if (i38 >= dimension) {
                                i16 = i29;
                                break;
                            }
                            if (arrayRealVector8.getEntry(i38) == d41) {
                                double entry10 = (this.trustRegionCenterOffset.getEntry(i38) + this.trialStepPoint.getEntry(i38)) - this.lowerDifference.getEntry(i38);
                                double entry11 = (this.upperDifference.getEntry(i38) - this.trustRegionCenterOffset.getEntry(i38)) - this.trialStepPoint.getEntry(i38);
                                if (entry10 <= 0.0d) {
                                    i16 = i29 + 1;
                                    arrayRealVector8.setEntry(i38, -1.0d);
                                    break;
                                }
                                if (entry11 <= 0.0d) {
                                    i16 = i29 + 1;
                                    arrayRealVector8.setEntry(i38, 1.0d);
                                    break;
                                }
                                double entry12 = this.trialStepPoint.getEntry(i38);
                                double entry13 = arrayRealVector3.getEntry(i38);
                                double d43 = (entry12 * entry12) + (entry13 * entry13);
                                double entry14 = this.trustRegionCenterOffset.getEntry(i38) - this.lowerDifference.getEntry(i38);
                                double d44 = d43 - (entry14 * entry14);
                                if (d44 > 0.0d) {
                                    double dSqrt3 = FastMath.sqrt(d44) - arrayRealVector3.getEntry(i38);
                                    if (d18 * dSqrt3 > entry10) {
                                        d18 = entry10 / dSqrt3;
                                        i20 = i38;
                                        d24 = -1.0d;
                                    }
                                }
                                double entry15 = this.upperDifference.getEntry(i38) - this.trustRegionCenterOffset.getEntry(i38);
                                double d45 = d43 - (entry15 * entry15);
                                if (d45 > 0.0d) {
                                    double dSqrt4 = FastMath.sqrt(d45) + arrayRealVector3.getEntry(i38);
                                    if (d18 * dSqrt4 > entry11) {
                                        i20 = i38;
                                        d18 = entry11 / dSqrt4;
                                        d24 = 1.0d;
                                    }
                                }
                            }
                            i38++;
                            d41 = 0.0d;
                        }
                        d23 = d42;
                        arrayRealVector9 = arrayRealVector3;
                        i15 = i33;
                        i19 = i31;
                        d13 = d37;
                        d26 = d38;
                        c2 = 210;
                        c = 20;
                        i18 = i37;
                    }
                } else if (c2 == 150) {
                    double d46 = d13;
                    int i39 = i19;
                    double d47 = d26;
                    printState(150);
                    double entry16 = 0.0d;
                    double entry17 = 0.0d;
                    double entry18 = 0.0d;
                    for (int i40 = 0; i40 < dimension; i40++) {
                        if (arrayRealVector8.getEntry(i40) == 0.0d) {
                            entry16 += arrayRealVector9.getEntry(i40) * arrayRealVector10.getEntry(i40);
                            entry17 += this.trialStepPoint.getEntry(i40) * arrayRealVector10.getEntry(i40);
                            entry18 += this.trialStepPoint.getEntry(i40) * arrayRealVector11.getEntry(i40);
                        }
                    }
                    int i41 = (int) ((17.0d * d18) + 3.1d);
                    int i42 = 0;
                    int i43 = -1;
                    double d48 = 0.0d;
                    double d49 = 0.0d;
                    while (i42 < i41) {
                        int i44 = i15;
                        int i45 = i39;
                        int i46 = i18;
                        double d50 = d47;
                        d20 = (i42 * d18) / i41;
                        double d51 = (d20 + d20) / ((d20 * d20) + 1.0d);
                        double d52 = d51 * (((d20 * entry3) - d23) - ((d51 * 0.5d) * (entry16 + ((((d20 * entry18) - entry17) - entry17) * d20))));
                        if (d52 > d48) {
                            d48 = d52;
                            i43 = i42;
                            d21 = d49;
                        } else if (i42 == i43 + 1) {
                            d22 = d52;
                        }
                        i42++;
                        d49 = d52;
                        i15 = i44;
                        i39 = i45;
                        i18 = i46;
                        d47 = d50;
                    }
                    int i47 = i15;
                    int i48 = i39;
                    int i49 = i18;
                    d7 = d47;
                    if (i43 >= 0) {
                        if (i43 < i41) {
                            d20 = ((i43 + (((d22 - d21) / (((d48 + d48) - d21) - d22)) * 0.5d)) * d18) / i41;
                        }
                        double d53 = d20 * d20;
                        double d54 = 1.0d - d53;
                        double d55 = d53 + 1.0d;
                        double d56 = d54 / d55;
                        double d57 = (d20 + d20) / d55;
                        double d58 = (((d20 * entry3) - d23) - ((0.5d * d57) * (entry16 + ((((entry18 * d20) - entry17) - entry17) * d20)))) * d57;
                        if (d58 > 0.0d) {
                            int i50 = 0;
                            d14 = 0.0d;
                            entry3 = 0.0d;
                            while (i50 < dimension) {
                                int i51 = i41;
                                int i52 = i16;
                                arrayRealVector.setEntry(i50, arrayRealVector.getEntry(i50) + ((d56 - 1.0d) * arrayRealVector11.getEntry(i50)) + (arrayRealVector10.getEntry(i50) * d57));
                                if (arrayRealVector8.getEntry(i50) == 0.0d) {
                                    ArrayRealVector arrayRealVector13 = this.trialStepPoint;
                                    arrayRealVector13.setEntry(i50, (arrayRealVector13.getEntry(i50) * d56) + (arrayRealVector9.getEntry(i50) * d57));
                                    entry3 += this.trialStepPoint.getEntry(i50) * arrayRealVector.getEntry(i50);
                                    double entry19 = arrayRealVector.getEntry(i50);
                                    d14 += entry19 * entry19;
                                }
                                arrayRealVector11.setEntry(i50, (arrayRealVector11.getEntry(i50) * d56) + (arrayRealVector10.getEntry(i50) * d57));
                                i50++;
                                arrayRealVector8 = arrayRealVector2;
                                arrayRealVector9 = arrayRealVector3;
                                i16 = i52;
                                i41 = i51;
                            }
                            int i53 = i41;
                            int i54 = i16;
                            d16 += d58;
                            int i55 = i20;
                            if (i55 < 0 || i43 != i53) {
                                arrayRealVector8 = arrayRealVector2;
                                double d59 = d24;
                                if (d58 <= 0.01d * d16) {
                                    break;
                                }
                                arrayRealVector9 = arrayRealVector3;
                                i20 = i55;
                                i16 = i54;
                                d24 = d59;
                                i15 = i47;
                                i19 = i48;
                                d13 = d46;
                                i18 = i49;
                                d26 = d7;
                                c2 = 'x';
                                c = 20;
                            } else {
                                i16 = i54 + 1;
                                arrayRealVector8 = arrayRealVector2;
                                arrayRealVector8.setEntry(i55, d24);
                                arrayRealVector9 = arrayRealVector3;
                                i20 = i55;
                                i15 = i47;
                                i19 = i48;
                                d13 = d46;
                                i18 = i49;
                                d26 = d7;
                                c2 = 'd';
                                c = 20;
                            }
                        }
                    }
                    i15 = i47;
                    i19 = i48;
                    d13 = d46;
                    i18 = i49;
                    d26 = d7;
                    c2 = 190;
                    c = 20;
                } else {
                    if (c2 == 190) {
                        d7 = d26;
                        break;
                    }
                    if (c2 == 210) {
                        printState(210);
                        int i56 = 0;
                        for (int i57 = 0; i57 < dimension; i57++) {
                            arrayRealVector10.setEntry(i57, 0.0d);
                            i56 = i56;
                            int i58 = 0;
                            while (i58 <= i57) {
                                if (i58 < i57) {
                                    d8 = d13;
                                    arrayRealVector10.setEntry(i57, arrayRealVector10.getEntry(i57) + (this.modelSecondDerivativesValues.getEntry(i56) * arrayRealVector9.getEntry(i58)));
                                } else {
                                    d8 = d13;
                                }
                                arrayRealVector10.setEntry(i58, arrayRealVector10.getEntry(i58) + (this.modelSecondDerivativesValues.getEntry(i56) * arrayRealVector9.getEntry(i57)));
                                i56++;
                                i58++;
                                d13 = d8;
                            }
                        }
                        double d60 = d13;
                        RealVector realVectorEbeMultiply = this.interpolationPoints.operate(arrayRealVector9).ebeMultiply(this.modelSecondDerivativesParameters);
                        for (int i59 = 0; i59 < i15; i59++) {
                            if (this.modelSecondDerivativesParameters.getEntry(i59) != 0.0d) {
                                for (int i60 = 0; i60 < dimension; i60++) {
                                    arrayRealVector10.setEntry(i60, arrayRealVector10.getEntry(i60) + (realVectorEbeMultiply.getEntry(i59) * this.interpolationPoints.getEntry(i59, i60)));
                                }
                            }
                        }
                        double d61 = d26;
                        if (d61 != 0.0d) {
                            d26 = d61;
                            d13 = d60;
                            c2 = '2';
                        } else {
                            int i61 = i19;
                            if (i18 > i61) {
                                i19 = i61;
                                d26 = d61;
                                d13 = d60;
                                c2 = 150;
                            } else {
                                for (int i62 = 0; i62 < dimension; i62++) {
                                    arrayRealVector11.setEntry(i62, arrayRealVector10.getEntry(i62));
                                }
                                i19 = i61;
                                d26 = d61;
                                d13 = d60;
                                c2 = 'x';
                            }
                        }
                        c = 20;
                    } else {
                        throw new MathIllegalStateException(LocalizedFormats.SIMPLE_MESSAGE, "trsbox");
                    }
                }
                printState(i13);
                i16 = i9;
                if (i16 >= dimension - 1) {
                    arrayRealVector10 = arrayRealVector4;
                    arrayRealVector11 = arrayRealVector5;
                    arrayRealVector9 = arrayRealVector7;
                    i21 = i10;
                    i18 = i19;
                    i15 = i7;
                    i19 = i8;
                    d13 = d5;
                    d24 = d6;
                    d26 = d7;
                    c2 = 190;
                    c = 20;
                } else {
                    d14 = 0.0d;
                    d15 = 0.0d;
                    entry3 = 0.0d;
                    for (int i63 = 0; i63 < dimension; i63++) {
                        if (arrayRealVector8.getEntry(i63) == 0.0d) {
                            double entry20 = this.trialStepPoint.getEntry(i63);
                            d15 += entry20 * entry20;
                            entry3 += this.trialStepPoint.getEntry(i63) * arrayRealVector.getEntry(i63);
                            double entry21 = arrayRealVector.getEntry(i63);
                            d14 += entry21 * entry21;
                            arrayRealVector7.setEntry(i63, this.trialStepPoint.getEntry(i63));
                        } else {
                            arrayRealVector7.setEntry(i63, 0.0d);
                        }
                    }
                    arrayRealVector10 = arrayRealVector4;
                    arrayRealVector11 = arrayRealVector5;
                    arrayRealVector9 = arrayRealVector7;
                    i21 = i10;
                    i18 = i19;
                    i15 = i7;
                    d13 = d5;
                    d24 = d6;
                    d26 = d7;
                    c2 = 210;
                    c = 20;
                }
            } else {
                d2 = d13;
                i = i18;
                i2 = i19;
                d3 = d26;
                i3 = i20;
                d4 = d24;
                i4 = i21;
                i5 = i15;
                arrayRealVector6 = arrayRealVector9;
                i6 = 30;
            }
            printState(i6);
            d17 = 0.0d;
            for (int i64 = 0; i64 < dimension; i64++) {
                if (arrayRealVector8.getEntry(i64) != 0.0d) {
                    arrayRealVector6.setEntry(i64, 0.0d);
                } else if (d19 == 0.0d) {
                    arrayRealVector6.setEntry(i64, -arrayRealVector.getEntry(i64));
                } else {
                    arrayRealVector6.setEntry(i64, (arrayRealVector6.getEntry(i64) * d19) - arrayRealVector.getEntry(i64));
                }
                double entry22 = arrayRealVector6.getEntry(i64);
                d17 += entry22 * entry22;
            }
            if (d17 == 0.0d) {
                arrayRealVector11 = arrayRealVector5;
                arrayRealVector9 = arrayRealVector6;
                i21 = i4;
            } else {
                if (d19 == 0.0d) {
                    i21 = (i + dimension) - i16;
                    d14 = d17;
                } else {
                    i21 = i4;
                }
                if (d14 * d2 <= 1.0E-4d * d16 * d16) {
                    arrayRealVector11 = arrayRealVector5;
                    arrayRealVector9 = arrayRealVector6;
                } else {
                    arrayRealVector11 = arrayRealVector5;
                    arrayRealVector9 = arrayRealVector6;
                    i20 = i3;
                    i15 = i5;
                    i19 = i2;
                    d13 = d2;
                    d24 = d4;
                    i18 = i;
                    d26 = d3;
                    c2 = 210;
                    c = 20;
                    arrayRealVector10 = arrayRealVector4;
                }
            }
            i20 = i3;
            i15 = i5;
            i19 = i2;
            d13 = d2;
            d24 = d4;
            i18 = i;
            d26 = d3;
            c2 = 190;
            c = 20;
            arrayRealVector10 = arrayRealVector4;
        }
        printState(190);
        double d62 = 0.0d;
        for (int i65 = 0; i65 < dimension; i65++) {
            this.newPoint.setEntry(i65, FastMath.max(FastMath.min(this.trustRegionCenterOffset.getEntry(i65) + this.trialStepPoint.getEntry(i65), this.upperDifference.getEntry(i65)), this.lowerDifference.getEntry(i65)));
            if (arrayRealVector8.getEntry(i65) == -1.0d) {
                this.newPoint.setEntry(i65, this.lowerDifference.getEntry(i65));
            }
            if (arrayRealVector8.getEntry(i65) == 1.0d) {
                this.newPoint.setEntry(i65, this.upperDifference.getEntry(i65));
            }
            this.trialStepPoint.setEntry(i65, this.newPoint.getEntry(i65) - this.trustRegionCenterOffset.getEntry(i65));
            double entry23 = this.trialStepPoint.getEntry(i65);
            d62 += entry23 * entry23;
        }
        return new double[]{d62, d7};
    }

    private void update(double d, double d2, int i) throws OutOfRangeException {
        double d3;
        int i2 = i;
        printMethod();
        int dimension = this.currentBest.getDimension();
        int i3 = this.numberOfInterpolationPoints;
        int i4 = 1;
        int i5 = (i3 - dimension) - 1;
        ArrayRealVector arrayRealVector = new ArrayRealVector(i3 + dimension);
        int i6 = 0;
        double dMax = 0.0d;
        for (int i7 = 0; i7 < i3; i7++) {
            for (int i8 = 0; i8 < i5; i8++) {
                dMax = FastMath.max(dMax, FastMath.abs(this.zMatrix.getEntry(i7, i8)));
            }
        }
        double d4 = dMax * 1.0E-20d;
        while (i4 < i5) {
            if (FastMath.abs(this.zMatrix.getEntry(i2, i4)) > d4) {
                double entry = this.zMatrix.getEntry(i2, 0);
                double entry2 = this.zMatrix.getEntry(i2, i4);
                double dSqrt = FastMath.sqrt((entry * entry) + (entry2 * entry2));
                double entry3 = this.zMatrix.getEntry(i2, 0) / dSqrt;
                double entry4 = this.zMatrix.getEntry(i2, i4) / dSqrt;
                int i9 = 0;
                while (i9 < i3) {
                    double entry5 = (this.zMatrix.getEntry(i9, 0) * entry3) + (this.zMatrix.getEntry(i9, i4) * entry4);
                    Array2DRowRealMatrix array2DRowRealMatrix = this.zMatrix;
                    array2DRowRealMatrix.setEntry(i9, i4, (array2DRowRealMatrix.getEntry(i9, i4) * entry3) - (this.zMatrix.getEntry(i9, 0) * entry4));
                    this.zMatrix.setEntry(i9, 0, entry5);
                    i9++;
                    d4 = d4;
                }
            }
            this.zMatrix.setEntry(i2, i4, 0.0d);
            i4++;
            d4 = d4;
        }
        for (int i10 = 0; i10 < i3; i10++) {
            arrayRealVector.setEntry(i10, this.zMatrix.getEntry(i2, 0) * this.zMatrix.getEntry(i10, 0));
        }
        double entry6 = arrayRealVector.getEntry(i2);
        double entry7 = this.lagrangeValuesAtNewPoint.getEntry(i2);
        ArrayRealVector arrayRealVector2 = this.lagrangeValuesAtNewPoint;
        arrayRealVector2.setEntry(i2, arrayRealVector2.getEntry(i2) - 1.0d);
        double dSqrt2 = FastMath.sqrt(d2);
        double d5 = entry7 / dSqrt2;
        double entry8 = this.zMatrix.getEntry(i2, 0) / dSqrt2;
        int i11 = 0;
        while (i11 < i3) {
            Array2DRowRealMatrix array2DRowRealMatrix2 = this.zMatrix;
            double d6 = d5;
            double entry9 = (array2DRowRealMatrix2.getEntry(i11, i6) * d5) - (this.lagrangeValuesAtNewPoint.getEntry(i11) * entry8);
            i6 = 0;
            array2DRowRealMatrix2.setEntry(i11, 0, entry9);
            i11++;
            d5 = d6;
        }
        int i12 = 0;
        while (i12 < dimension) {
            int i13 = i3 + i12;
            arrayRealVector.setEntry(i13, this.bMatrix.getEntry(i2, i12));
            double entry10 = ((this.lagrangeValuesAtNewPoint.getEntry(i13) * entry6) - (arrayRealVector.getEntry(i13) * entry7)) / d2;
            int i14 = i12;
            double entry11 = (((-d) * arrayRealVector.getEntry(i13)) - (this.lagrangeValuesAtNewPoint.getEntry(i13) * entry7)) / d2;
            int i15 = 0;
            while (i15 <= i13) {
                int i16 = dimension;
                Array2DRowRealMatrix array2DRowRealMatrix3 = this.bMatrix;
                double d7 = entry6;
                int i17 = i14;
                ArrayRealVector arrayRealVector3 = arrayRealVector;
                array2DRowRealMatrix3.setEntry(i15, i17, array2DRowRealMatrix3.getEntry(i15, i17) + (this.lagrangeValuesAtNewPoint.getEntry(i15) * entry10) + (arrayRealVector.getEntry(i15) * entry11));
                if (i15 >= i3) {
                    Array2DRowRealMatrix array2DRowRealMatrix4 = this.bMatrix;
                    d3 = entry7;
                    array2DRowRealMatrix4.setEntry(i13, i15 - i3, array2DRowRealMatrix4.getEntry(i15, i17));
                } else {
                    d3 = entry7;
                }
                i15++;
                arrayRealVector = arrayRealVector3;
                dimension = i16;
                entry7 = d3;
                i14 = i17;
                entry6 = d7;
            }
            i12 = i14 + 1;
            i2 = i;
            entry6 = entry6;
        }
    }

    private void setup(double[] dArr, double[] dArr2) {
        printMethod();
        int length = getStartPoint().length;
        if (length < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(length), 2, true);
        }
        int i = length + 2;
        int i2 = length + 1;
        int[] iArr = {i, (i * i2) / 2};
        int i3 = this.numberOfInterpolationPoints;
        if (i3 < iArr[0] || i3 > iArr[1]) {
            throw new OutOfRangeException(LocalizedFormats.NUMBER_OF_INTERPOLATION_POINTS, Integer.valueOf(this.numberOfInterpolationPoints), Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
        }
        this.boundDifference = new double[length];
        double d = this.initialTrustRegionRadius * TWO;
        double dMin = Double.POSITIVE_INFINITY;
        for (int i4 = 0; i4 < length; i4++) {
            double[] dArr3 = this.boundDifference;
            double d2 = dArr2[i4] - dArr[i4];
            dArr3[i4] = d2;
            dMin = FastMath.min(dMin, d2);
        }
        if (dMin < d) {
            this.initialTrustRegionRadius = dMin / 3.0d;
        }
        this.bMatrix = new Array2DRowRealMatrix(this.numberOfInterpolationPoints + length, length);
        int i5 = this.numberOfInterpolationPoints;
        this.zMatrix = new Array2DRowRealMatrix(i5, (i5 - length) - 1);
        this.interpolationPoints = new Array2DRowRealMatrix(this.numberOfInterpolationPoints, length);
        this.originShift = new ArrayRealVector(length);
        this.fAtInterpolationPoints = new ArrayRealVector(this.numberOfInterpolationPoints);
        this.trustRegionCenterOffset = new ArrayRealVector(length);
        this.gradientAtTrustRegionCenter = new ArrayRealVector(length);
        this.lowerDifference = new ArrayRealVector(length);
        this.upperDifference = new ArrayRealVector(length);
        this.modelSecondDerivativesParameters = new ArrayRealVector(this.numberOfInterpolationPoints);
        this.newPoint = new ArrayRealVector(length);
        this.alternativeNewPoint = new ArrayRealVector(length);
        this.trialStepPoint = new ArrayRealVector(length);
        this.lagrangeValuesAtNewPoint = new ArrayRealVector(this.numberOfInterpolationPoints + length);
        this.modelSecondDerivativesValues = new ArrayRealVector((length * i2) / 2);
    }

    private static class PathIsExploredException extends RuntimeException {
        private static final String PATH_IS_EXPLORED = "If this exception is thrown, just remove it from the code";
        private static final long serialVersionUID = 745350979634801853L;

        PathIsExploredException() {
            super("If this exception is thrown, just remove it from the code " + BOBYQAOptimizer.caller(3));
        }
    }
}
