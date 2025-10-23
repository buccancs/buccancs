package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class CMAESOptimizer extends MultivariateOptimizer {
    private final int checkFeasableCount;
    private final boolean generateStatistics;
    private final boolean isActiveCMA;
    private final int maxIterations;
    private final RandomGenerator random;
    private final List<RealMatrix> statisticsDHistory;
    private final List<Double> statisticsFitnessHistory;
    private final List<RealMatrix> statisticsMeanHistory;
    private final List<Double> statisticsSigmaHistory;
    private final double stopFitness;
    private RealMatrix B;
    private RealMatrix BD;
    private RealMatrix C;
    private RealMatrix D;
    private double cc;
    private double ccov1;
    private double ccov1Sep;
    private double ccovmu;
    private double ccovmuSep;
    private double chiN;
    private double cs;
    private double damps;
    private RealMatrix diagC;
    private RealMatrix diagD;
    private int diagonalOnly;
    private int dimension;
    private double[] fitnessHistory;
    private int historySize;
    private double[] inputSigma;
    private boolean isMinimize;
    private int iterations;
    private int lambda;
    private double logMu2;
    private int mu;
    private double mueff;
    private double normps;
    private RealMatrix pc;
    private RealMatrix ps;
    private double sigma;
    private double stopTolFun;
    private double stopTolHistFun;
    private double stopTolUpX;
    private double stopTolX;
    private RealMatrix weights;
    private RealMatrix xmean;

    public CMAESOptimizer(int i, double d, boolean z, int i2, int i3, RandomGenerator randomGenerator, boolean z2, ConvergenceChecker<PointValuePair> convergenceChecker) {
        super(convergenceChecker);
        this.isMinimize = true;
        this.statisticsSigmaHistory = new ArrayList();
        this.statisticsMeanHistory = new ArrayList();
        this.statisticsFitnessHistory = new ArrayList();
        this.statisticsDHistory = new ArrayList();
        this.maxIterations = i;
        this.stopFitness = d;
        this.isActiveCMA = z;
        this.diagonalOnly = i2;
        this.checkFeasableCount = i3;
        this.random = randomGenerator;
        this.generateStatistics = z2;
    }

    private static void push(double[] dArr, double d) {
        for (int length = dArr.length - 1; length > 0; length--) {
            dArr[length] = dArr[length - 1];
        }
        dArr[0] = d;
    }

    private static RealMatrix log(RealMatrix realMatrix) {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, realMatrix.getRowDimension(), realMatrix.getColumnDimension());
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                dArr[i][i2] = FastMath.log(realMatrix.getEntry(i, i2));
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix sqrt(RealMatrix realMatrix) {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, realMatrix.getRowDimension(), realMatrix.getColumnDimension());
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                dArr[i][i2] = FastMath.sqrt(realMatrix.getEntry(i, i2));
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix square(RealMatrix realMatrix) throws OutOfRangeException {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, realMatrix.getRowDimension(), realMatrix.getColumnDimension());
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                double entry = realMatrix.getEntry(i, i2);
                dArr[i][i2] = entry * entry;
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix times(RealMatrix realMatrix, RealMatrix realMatrix2) {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, realMatrix.getRowDimension(), realMatrix.getColumnDimension());
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                dArr[i][i2] = realMatrix.getEntry(i, i2) * realMatrix2.getEntry(i, i2);
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix divide(RealMatrix realMatrix, RealMatrix realMatrix2) {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, realMatrix.getRowDimension(), realMatrix.getColumnDimension());
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                dArr[i][i2] = realMatrix.getEntry(i, i2) / realMatrix2.getEntry(i, i2);
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix selectColumns(RealMatrix realMatrix, int[] iArr) {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, realMatrix.getRowDimension(), iArr.length);
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                dArr[i][i2] = realMatrix.getEntry(i, iArr[i2]);
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix triu(RealMatrix realMatrix, int i) {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, realMatrix.getRowDimension(), realMatrix.getColumnDimension());
        int i2 = 0;
        while (i2 < realMatrix.getRowDimension()) {
            for (int i3 = 0; i3 < realMatrix.getColumnDimension(); i3++) {
                dArr[i2][i3] = i2 <= i3 - i ? realMatrix.getEntry(i2, i3) : 0.0d;
            }
            i2++;
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix sumRows(RealMatrix realMatrix) {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 1, realMatrix.getColumnDimension());
        for (int i = 0; i < realMatrix.getColumnDimension(); i++) {
            double entry = 0.0d;
            for (int i2 = 0; i2 < realMatrix.getRowDimension(); i2++) {
                entry += realMatrix.getEntry(i2, i);
            }
            dArr[0][i] = entry;
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix diag(RealMatrix realMatrix) {
        if (realMatrix.getColumnDimension() == 1) {
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, realMatrix.getRowDimension(), realMatrix.getRowDimension());
            for (int i = 0; i < realMatrix.getRowDimension(); i++) {
                dArr[i][i] = realMatrix.getEntry(i, 0);
            }
            return new Array2DRowRealMatrix(dArr, false);
        }
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, realMatrix.getRowDimension(), 1);
        for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
            dArr2[i2][0] = realMatrix.getEntry(i2, i2);
        }
        return new Array2DRowRealMatrix(dArr2, false);
    }

    private static void copyColumn(RealMatrix realMatrix, int i, RealMatrix realMatrix2, int i2) throws OutOfRangeException {
        for (int i3 = 0; i3 < realMatrix.getRowDimension(); i3++) {
            realMatrix2.setEntry(i3, i2, realMatrix.getEntry(i3, i));
        }
    }

    private static RealMatrix ones(int i, int i2) {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i, i2);
        for (int i3 = 0; i3 < i; i3++) {
            Arrays.fill(dArr[i3], 1.0d);
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix eye(int i, int i2) {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i, i2);
        for (int i3 = 0; i3 < i; i3++) {
            if (i3 < i2) {
                dArr[i3][i3] = 1.0d;
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix zeros(int i, int i2) {
        return new Array2DRowRealMatrix(i, i2);
    }

    private static RealMatrix repmat(RealMatrix realMatrix, int i, int i2) {
        int rowDimension = realMatrix.getRowDimension();
        int columnDimension = realMatrix.getColumnDimension();
        int i3 = i * rowDimension;
        int i4 = i2 * columnDimension;
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i3, i4);
        for (int i5 = 0; i5 < i3; i5++) {
            for (int i6 = 0; i6 < i4; i6++) {
                dArr[i5][i6] = realMatrix.getEntry(i5 % rowDimension, i6 % columnDimension);
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix sequence(double d, double d2, double d3) {
        int i = (int) (((d2 - d) / d3) + 1.0d);
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i, 1);
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2][0] = d;
            d += d3;
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static double max(RealMatrix realMatrix) throws OutOfRangeException {
        double d = -1.7976931348623157E308d;
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                double entry = realMatrix.getEntry(i, i2);
                if (d < entry) {
                    d = entry;
                }
            }
        }
        return d;
    }

    private static double min(RealMatrix realMatrix) throws OutOfRangeException {
        double d = Double.MAX_VALUE;
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                double entry = realMatrix.getEntry(i, i2);
                if (d > entry) {
                    d = entry;
                }
            }
        }
        return d;
    }

    private static double max(double[] dArr) {
        double d = -1.7976931348623157E308d;
        for (double d2 : dArr) {
            if (d < d2) {
                d = d2;
            }
        }
        return d;
    }

    private static double min(double[] dArr) {
        double d = Double.MAX_VALUE;
        for (double d2 : dArr) {
            if (d > d2) {
                d = d2;
            }
        }
        return d;
    }

    private static int[] inverse(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[iArr[i]] = i;
        }
        return iArr2;
    }

    private static int[] reverse(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = iArr[(iArr.length - i) - 1];
        }
        return iArr2;
    }

    public List<RealMatrix> getStatisticsDHistory() {
        return this.statisticsDHistory;
    }

    public List<Double> getStatisticsFitnessHistory() {
        return this.statisticsFitnessHistory;
    }

    public List<RealMatrix> getStatisticsMeanHistory() {
        return this.statisticsMeanHistory;
    }

    public List<Double> getStatisticsSigmaHistory() {
        return this.statisticsSigmaHistory;
    }

    @Override
    // org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer, org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    public PointValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException, DimensionMismatchException {
        return super.optimize(optimizationDataArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x0288, code lost:

        r14 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x028a, code lost:

        r14 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x02a7, code lost:

        if (r17 != r15[r23[(int) ((r25.lambda / 4.0d) + 0.1d)]]) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x02a9, code lost:

        r23 = r1;
        r25.sigma *= org.apache.commons.math3.util.FastMath.exp((r25.cs / r25.damps) + 0.2d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x02bd, code lost:

        r23 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x02c2, code lost:

        if (r25.iterations <= 2) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x02c4, code lost:

        r0 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x02d1, code lost:

        if ((org.apache.commons.math3.util.FastMath.max(r11, r0) - org.apache.commons.math3.util.FastMath.min(r7, r0)) != 0.0d) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x02d3, code lost:

        r25.sigma *= org.apache.commons.math3.util.FastMath.exp((r25.cs / r25.damps) + 0.2d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x02e5, code lost:

        r0 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x02e7, code lost:

        push(r25.fitnessHistory, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x02ee, code lost:

        if (r25.generateStatistics == false) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x02f0, code lost:

        r25.statisticsSigmaHistory.add(java.lang.Double.valueOf(r25.sigma));
        r25.statisticsFitnessHistory.add(java.lang.Double.valueOf(r0));
        r25.statisticsMeanHistory.add(r25.xmean.transpose());
        r25.statisticsDHistory.add(r25.diagD.transpose().scalarMultiply(100000.0d));
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0323, code lost:

        r25.iterations++;
        r11 = r5;
        r7 = r16;
        r12 = r17;
        r9 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0216, code lost:

        r7 = min(r25.fitnessHistory);
        r11 = max(r25.fitnessHistory);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0225, code lost:

        if (r25.iterations <= 2) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0234, code lost:

        if ((org.apache.commons.math3.util.FastMath.max(r11, r3) - org.apache.commons.math3.util.FastMath.min(r7, r1)) >= r25.stopTolFun) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x023c, code lost:

        if (r25.iterations <= r25.fitnessHistory.length) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0244, code lost:

        if ((r11 - r7) >= r25.stopTolHistFun) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x025b, code lost:

        if ((max(r25.diagD) / min(r25.diagD)) <= 1.0E7d) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0262, code lost:

        if (getConvergenceChecker() == null) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0264, code lost:

        r4 = r10.getColumn(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x026d, code lost:

        if (r25.isMinimize == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x026f, code lost:

        r13 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0271, code lost:

        r13 = -r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0272, code lost:

        r0 = new org.apache.commons.math3.optim.PointValuePair(r4, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0275, code lost:

        if (r21 == null) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0283, code lost:

        if (getConvergenceChecker().converged(r25.iterations, r0, r21) == false) goto L100;
     */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01b2  */
    @Override // org.apache.commons.math3.optim.BaseOptimizer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.commons.math3.optim.PointValuePair doOptimize() throws org.apache.commons.math3.exception.OutOfRangeException, org.apache.commons.math3.linear.MatrixDimensionMismatchException, org.apache.commons.math3.exception.DimensionMismatchException {
        /*
            Method dump skipped, instructions count: 821
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize():org.apache.commons.math3.optim.PointValuePair");
    }

    @Override
    // org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer, org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    protected void parseOptimizationData(OptimizationData... optimizationDataArr) {
        super.parseOptimizationData(optimizationDataArr);
        for (OptimizationData optimizationData : optimizationDataArr) {
            if (optimizationData instanceof Sigma) {
                this.inputSigma = ((Sigma) optimizationData).getSigma();
            } else if (optimizationData instanceof PopulationSize) {
                this.lambda = ((PopulationSize) optimizationData).getPopulationSize();
            }
        }
        checkParameters();
    }

    private void checkParameters() {
        double[] startPoint = getStartPoint();
        double[] lowerBound = getLowerBound();
        double[] upperBound = getUpperBound();
        double[] dArr = this.inputSigma;
        if (dArr != null) {
            if (dArr.length != startPoint.length) {
                throw new DimensionMismatchException(this.inputSigma.length, startPoint.length);
            }
            for (int i = 0; i < startPoint.length; i++) {
                if (this.inputSigma[i] > upperBound[i] - lowerBound[i]) {
                    throw new OutOfRangeException(Double.valueOf(this.inputSigma[i]), 0, Double.valueOf(upperBound[i] - lowerBound[i]));
                }
            }
        }
    }

    private void initializeCMA(double[] dArr) throws OutOfRangeException {
        if (this.lambda <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(this.lambda));
        }
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, dArr.length, 1);
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i][0] = this.inputSigma[i];
        }
        Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(dArr2, false);
        this.sigma = max(array2DRowRealMatrix);
        this.stopTolUpX = max(array2DRowRealMatrix) * 1000.0d;
        this.stopTolX = max(array2DRowRealMatrix) * 1.0E-11d;
        this.stopTolFun = 1.0E-12d;
        this.stopTolHistFun = 1.0E-13d;
        int i2 = this.lambda / 2;
        this.mu = i2;
        this.logMu2 = FastMath.log(i2 + 0.5d);
        this.weights = log(sequence(1.0d, this.mu, 1.0d)).scalarMultiply(-1.0d).scalarAdd(this.logMu2);
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i3 = 0; i3 < this.mu; i3++) {
            double entry = this.weights.getEntry(i3, 0);
            d += entry;
            d2 += entry * entry;
        }
        this.weights = this.weights.scalarMultiply(1.0d / d);
        double d3 = (d * d) / d2;
        this.mueff = d3;
        int i4 = this.dimension;
        this.cc = ((d3 / i4) + 4.0d) / ((i4 + 4) + ((d3 * 2.0d) / i4));
        this.cs = (d3 + 2.0d) / ((i4 + d3) + 3.0d);
        this.damps = (((FastMath.max(0.0d, FastMath.sqrt((d3 - 1.0d) / (i4 + 1)) - 1.0d) * 2.0d) + 1.0d) * FastMath.max(0.3d, 1.0d - (this.dimension / (this.maxIterations + 1.0E-6d)))) + this.cs;
        int i5 = this.dimension;
        double d4 = this.mueff;
        double d5 = 2.0d / (((i5 + 1.3d) * (i5 + 1.3d)) + d4);
        this.ccov1 = d5;
        this.ccovmu = FastMath.min(1.0d - d5, (((d4 - 2.0d) + (1.0d / d4)) * 2.0d) / (((i5 + 2) * (i5 + 2)) + d4));
        this.ccov1Sep = FastMath.min(1.0d, (this.ccov1 * (this.dimension + 1.5d)) / 3.0d);
        this.ccovmuSep = FastMath.min(1.0d - this.ccov1, (this.ccovmu * (this.dimension + 1.5d)) / 3.0d);
        double dSqrt = FastMath.sqrt(this.dimension);
        int i6 = this.dimension;
        this.chiN = dSqrt * ((1.0d - (1.0d / (i6 * 4.0d))) + (1.0d / ((i6 * 21.0d) * i6)));
        this.xmean = MatrixUtils.createColumnRealMatrix(dArr);
        RealMatrix realMatrixScalarMultiply = array2DRowRealMatrix.scalarMultiply(1.0d / this.sigma);
        this.diagD = realMatrixScalarMultiply;
        this.diagC = square(realMatrixScalarMultiply);
        this.pc = zeros(this.dimension, 1);
        RealMatrix realMatrixZeros = zeros(this.dimension, 1);
        this.ps = realMatrixZeros;
        this.normps = realMatrixZeros.getFrobeniusNorm();
        int i7 = this.dimension;
        this.B = eye(i7, i7);
        this.D = ones(this.dimension, 1);
        this.BD = times(this.B, repmat(this.diagD.transpose(), this.dimension, 1));
        this.C = this.B.multiply(diag(square(this.D)).multiply(this.B.transpose()));
        int i8 = ((int) ((this.dimension * 30) / this.lambda)) + 10;
        this.historySize = i8;
        this.fitnessHistory = new double[i8];
        for (int i9 = 0; i9 < this.historySize; i9++) {
            this.fitnessHistory[i9] = Double.MAX_VALUE;
        }
    }

    private boolean updateEvolutionPaths(RealMatrix realMatrix, RealMatrix realMatrix2) throws MatrixDimensionMismatchException, DimensionMismatchException {
        RealMatrix realMatrixScalarMultiply = this.ps.scalarMultiply(1.0d - this.cs);
        RealMatrix realMatrixMultiply = this.B.multiply(realMatrix);
        double d = this.cs;
        RealMatrix realMatrixAdd = realMatrixScalarMultiply.add(realMatrixMultiply.scalarMultiply(FastMath.sqrt(d * (2.0d - d) * this.mueff)));
        this.ps = realMatrixAdd;
        double frobeniusNorm = realMatrixAdd.getFrobeniusNorm();
        this.normps = frobeniusNorm;
        boolean z = (frobeniusNorm / FastMath.sqrt(1.0d - FastMath.pow(1.0d - this.cs, this.iterations * 2))) / this.chiN < (2.0d / (((double) this.dimension) + 1.0d)) + 1.4d;
        RealMatrix realMatrixScalarMultiply2 = this.pc.scalarMultiply(1.0d - this.cc);
        this.pc = realMatrixScalarMultiply2;
        if (z) {
            RealMatrix realMatrixSubtract = this.xmean.subtract(realMatrix2);
            double d2 = this.cc;
            this.pc = realMatrixScalarMultiply2.add(realMatrixSubtract.scalarMultiply(FastMath.sqrt((d2 * (2.0d - d2)) * this.mueff) / this.sigma));
        }
        return z;
    }

    private void updateCovarianceDiagonalOnly(boolean z, RealMatrix realMatrix) throws MatrixDimensionMismatchException {
        double d;
        if (z) {
            d = 0.0d;
        } else {
            double d2 = this.ccov1Sep;
            double d3 = this.cc;
            d = d2 * d3 * (2.0d - d3);
        }
        RealMatrix realMatrixAdd = this.diagC.scalarMultiply(d + ((1.0d - this.ccov1Sep) - this.ccovmuSep)).add(square(this.pc).scalarMultiply(this.ccov1Sep)).add(times(this.diagC, square(realMatrix).multiply(this.weights)).scalarMultiply(this.ccovmuSep));
        this.diagC = realMatrixAdd;
        this.diagD = sqrt(realMatrixAdd);
        int i = this.diagonalOnly;
        if (i <= 1 || this.iterations <= i) {
            return;
        }
        this.diagonalOnly = 0;
        int i2 = this.dimension;
        this.B = eye(i2, i2);
        this.BD = diag(this.diagD);
        this.C = diag(this.diagC);
    }

    private void updateCovariance(boolean z, RealMatrix realMatrix, RealMatrix realMatrix2, int[] iArr, RealMatrix realMatrix3) throws OutOfRangeException, MatrixDimensionMismatchException, DimensionMismatchException {
        double d;
        double d2;
        if (this.ccov1 + this.ccovmu > 0.0d) {
            RealMatrix realMatrixScalarMultiply = realMatrix.subtract(repmat(realMatrix3, 1, this.mu)).scalarMultiply(1.0d / this.sigma);
            RealMatrix realMatrix4 = this.pc;
            RealMatrix realMatrixScalarMultiply2 = realMatrix4.multiply(realMatrix4.transpose()).scalarMultiply(this.ccov1);
            if (z) {
                d2 = 0.0d;
            } else {
                double d3 = this.ccov1;
                double d4 = this.cc;
                d2 = d3 * d4 * (2.0d - d4);
            }
            double d5 = 1.0d - this.ccov1;
            double d6 = this.ccovmu;
            double d7 = d2 + (d5 - d6);
            if (this.isActiveCMA) {
                double dPow = (((1.0d - d6) * 0.25d) * this.mueff) / (FastMath.pow(this.dimension + 2, 1.5d) + (this.mueff * 2.0d));
                RealMatrix realMatrixSelectColumns = selectColumns(realMatrix2, MathArrays.copyOf(reverse(iArr), this.mu));
                RealMatrix realMatrixSqrt = sqrt(sumRows(square(realMatrixSelectColumns)));
                int[] iArrSortedIndices = sortedIndices(realMatrixSqrt.getRow(0));
                RealMatrix realMatrixSelectColumns2 = selectColumns(divide(selectColumns(realMatrixSqrt, reverse(iArrSortedIndices)), selectColumns(realMatrixSqrt, iArrSortedIndices)), inverse(iArrSortedIndices));
                double entry = 0.33999999999999997d / square(realMatrixSelectColumns2).multiply(this.weights).getEntry(0, 0);
                if (dPow <= entry) {
                    entry = dPow;
                }
                RealMatrix realMatrixMultiply = this.BD.multiply(times(realMatrixSelectColumns, repmat(realMatrixSelectColumns2, this.dimension, 1)));
                double d8 = 0.5d * entry;
                this.C = this.C.scalarMultiply(d7 + d8).add(realMatrixScalarMultiply2).add(realMatrixScalarMultiply.scalarMultiply(this.ccovmu + d8).multiply(times(repmat(this.weights, 1, this.dimension), realMatrixScalarMultiply.transpose()))).subtract(realMatrixMultiply.multiply(diag(this.weights)).multiply(realMatrixMultiply.transpose()).scalarMultiply(entry));
                d = entry;
            } else {
                this.C = this.C.scalarMultiply(d7).add(realMatrixScalarMultiply2).add(realMatrixScalarMultiply.scalarMultiply(this.ccovmu).multiply(times(repmat(this.weights, 1, this.dimension), realMatrixScalarMultiply.transpose())));
                d = 0.0d;
            }
        } else {
            d = 0.0d;
        }
        updateBD(d);
    }

    private void updateBD(double d) throws OutOfRangeException, MatrixDimensionMismatchException {
        double d2 = this.ccov1;
        double d3 = this.ccovmu;
        if (d2 + d3 + d <= 0.0d || (((this.iterations % 1.0d) / ((d2 + d3) + d)) / this.dimension) / 10.0d >= 1.0d) {
            return;
        }
        RealMatrix realMatrixAdd = triu(this.C, 0).add(triu(this.C, 1).transpose());
        this.C = realMatrixAdd;
        EigenDecomposition eigenDecomposition = new EigenDecomposition(realMatrixAdd);
        this.B = eigenDecomposition.getV();
        RealMatrix d4 = eigenDecomposition.getD();
        this.D = d4;
        RealMatrix realMatrixDiag = diag(d4);
        this.diagD = realMatrixDiag;
        if (min(realMatrixDiag) <= 0.0d) {
            for (int i = 0; i < this.dimension; i++) {
                if (this.diagD.getEntry(i, 0) < 0.0d) {
                    this.diagD.setEntry(i, 0, 0.0d);
                }
            }
            double dMax = max(this.diagD) / 1.0E14d;
            RealMatrix realMatrix = this.C;
            int i2 = this.dimension;
            this.C = realMatrix.add(eye(i2, i2).scalarMultiply(dMax));
            this.diagD = this.diagD.add(ones(this.dimension, 1).scalarMultiply(dMax));
        }
        if (max(this.diagD) > min(this.diagD) * 1.0E14d) {
            double dMax2 = (max(this.diagD) / 1.0E14d) - min(this.diagD);
            RealMatrix realMatrix2 = this.C;
            int i3 = this.dimension;
            this.C = realMatrix2.add(eye(i3, i3).scalarMultiply(dMax2));
            this.diagD = this.diagD.add(ones(this.dimension, 1).scalarMultiply(dMax2));
        }
        this.diagC = diag(this.C);
        RealMatrix realMatrixSqrt = sqrt(this.diagD);
        this.diagD = realMatrixSqrt;
        this.BD = times(this.B, repmat(realMatrixSqrt.transpose(), this.dimension, 1));
    }

    private int[] sortedIndices(double[] dArr) {
        DoubleIndex[] doubleIndexArr = new DoubleIndex[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            doubleIndexArr[i] = new DoubleIndex(dArr[i], i);
        }
        Arrays.sort(doubleIndexArr);
        int[] iArr = new int[dArr.length];
        for (int i2 = 0; i2 < dArr.length; i2++) {
            iArr[i2] = doubleIndexArr[i2].index;
        }
        return iArr;
    }

    private double valueRange(ValuePenaltyPair[] valuePenaltyPairArr) {
        double d = Double.NEGATIVE_INFINITY;
        double d2 = Double.MAX_VALUE;
        for (ValuePenaltyPair valuePenaltyPair : valuePenaltyPairArr) {
            if (valuePenaltyPair.value > d) {
                d = valuePenaltyPair.value;
            }
            if (valuePenaltyPair.value < d2) {
                d2 = valuePenaltyPair.value;
            }
        }
        return d - d2;
    }

    private double[] randn(int i) {
        double[] dArr = new double[i];
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2] = this.random.nextGaussian();
        }
        return dArr;
    }

    private RealMatrix randn1(int i, int i2) {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i, i2);
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                dArr[i3][i4] = this.random.nextGaussian();
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    public static class Sigma implements OptimizationData {
        private final double[] sigma;

        public Sigma(double[] dArr) throws NotPositiveException {
            for (int i = 0; i < dArr.length; i++) {
                if (dArr[i] < 0.0d) {
                    throw new NotPositiveException(Double.valueOf(dArr[i]));
                }
            }
            this.sigma = (double[]) dArr.clone();
        }

        public double[] getSigma() {
            return (double[]) this.sigma.clone();
        }
    }

    public static class PopulationSize implements OptimizationData {
        private final int lambda;

        public PopulationSize(int i) throws NotStrictlyPositiveException {
            if (i <= 0) {
                throw new NotStrictlyPositiveException(Integer.valueOf(i));
            }
            this.lambda = i;
        }

        public int getPopulationSize() {
            return this.lambda;
        }
    }

    private static class DoubleIndex implements Comparable<DoubleIndex> {
        private final int index;
        private final double value;

        DoubleIndex(double d, int i) {
            this.value = d;
            this.index = i;
        }

        @Override // java.lang.Comparable
        public int compareTo(DoubleIndex doubleIndex) {
            return Double.compare(this.value, doubleIndex.value);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof DoubleIndex) && Double.compare(this.value, ((DoubleIndex) obj).value) == 0;
        }

        public int hashCode() {
            long jDoubleToLongBits = Double.doubleToLongBits(this.value);
            return (int) (jDoubleToLongBits ^ ((jDoubleToLongBits >>> 32) ^ 1438542));
        }
    }

    private static class ValuePenaltyPair {
        private double penalty;
        private double value;

        ValuePenaltyPair(double d, double d2) {
            this.value = d;
            this.penalty = d2;
        }
    }

    private class FitnessFunction {
        private final boolean isRepairMode = true;

        FitnessFunction() {
        }

        public ValuePenaltyPair value(double[] dArr) {
            double dComputeObjectiveValue;
            double dPenalty;
            if (this.isRepairMode) {
                double[] dArrRepair = repair(dArr);
                dComputeObjectiveValue = CMAESOptimizer.this.computeObjectiveValue(dArrRepair);
                dPenalty = penalty(dArr, dArrRepair);
            } else {
                dComputeObjectiveValue = CMAESOptimizer.this.computeObjectiveValue(dArr);
                dPenalty = 0.0d;
            }
            if (!CMAESOptimizer.this.isMinimize) {
                dComputeObjectiveValue = -dComputeObjectiveValue;
            }
            if (!CMAESOptimizer.this.isMinimize) {
                dPenalty = -dPenalty;
            }
            return new ValuePenaltyPair(dComputeObjectiveValue, dPenalty);
        }

        public boolean isFeasible(double[] dArr) {
            double[] lowerBound = CMAESOptimizer.this.getLowerBound();
            double[] upperBound = CMAESOptimizer.this.getUpperBound();
            for (int i = 0; i < dArr.length; i++) {
                double d = dArr[i];
                if (d < lowerBound[i] || d > upperBound[i]) {
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double[] repair(double[] dArr) {
            double[] lowerBound = CMAESOptimizer.this.getLowerBound();
            double[] upperBound = CMAESOptimizer.this.getUpperBound();
            double[] dArr2 = new double[dArr.length];
            for (int i = 0; i < dArr.length; i++) {
                double d = dArr[i];
                double d2 = lowerBound[i];
                if (d < d2) {
                    dArr2[i] = d2;
                } else {
                    double d3 = upperBound[i];
                    if (d > d3) {
                        dArr2[i] = d3;
                    } else {
                        dArr2[i] = d;
                    }
                }
            }
            return dArr2;
        }

        private double penalty(double[] dArr, double[] dArr2) {
            double dAbs = 0.0d;
            for (int i = 0; i < dArr.length; i++) {
                dAbs += FastMath.abs(dArr[i] - dArr2[i]);
            }
            return CMAESOptimizer.this.isMinimize ? dAbs : -dAbs;
        }
    }
}
