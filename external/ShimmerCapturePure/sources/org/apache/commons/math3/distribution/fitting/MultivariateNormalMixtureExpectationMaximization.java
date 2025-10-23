package org.apache.commons.math3.distribution.fitting;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution;
import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Pair;

/* loaded from: classes5.dex */
public class MultivariateNormalMixtureExpectationMaximization {
    private static final int DEFAULT_MAX_ITERATIONS = 1000;
    private static final double DEFAULT_THRESHOLD = 1.0E-5d;
    private final double[][] data;
    private MixtureMultivariateNormalDistribution fittedModel;
    private double logLikelihood = 0.0d;

    public MultivariateNormalMixtureExpectationMaximization(double[][] dArr) throws NumberIsTooSmallException, DimensionMismatchException {
        if (dArr.length < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(dArr.length));
        }
        this.data = (double[][]) Array.newInstance((Class<?>) Double.TYPE, dArr.length, dArr[0].length);
        for (int i = 0; i < dArr.length; i++) {
            double[] dArr2 = dArr[i];
            if (dArr2.length != dArr[0].length) {
                throw new DimensionMismatchException(dArr[i].length, dArr[0].length);
            }
            if (dArr2.length < 2) {
                throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_TOO_SMALL, Integer.valueOf(dArr[i].length), 2, true);
            }
            this.data[i] = MathArrays.copyOf(dArr2, dArr2.length);
        }
    }

    public static MixtureMultivariateNormalDistribution estimate(double[][] dArr, int i) throws NotStrictlyPositiveException, DimensionMismatchException {
        if (dArr.length < 2) {
            throw new NotStrictlyPositiveException(Integer.valueOf(dArr.length));
        }
        if (i < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
        }
        if (i > dArr.length) {
            throw new NumberIsTooLargeException(Integer.valueOf(i), Integer.valueOf(dArr.length), true);
        }
        int length = dArr.length;
        int i2 = 0;
        int length2 = dArr[0].length;
        DataRow[] dataRowArr = new DataRow[length];
        for (int i3 = 0; i3 < length; i3++) {
            dataRowArr[i3] = new DataRow(dArr[i3]);
        }
        Arrays.sort(dataRowArr);
        double d = 1.0d / i;
        ArrayList arrayList = new ArrayList(i);
        int i4 = 0;
        while (i4 < i) {
            int i5 = (i4 * length) / i;
            i4++;
            int i6 = (i4 * length) / i;
            int i7 = i6 - i5;
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i7, length2);
            double[] dArr3 = new double[length2];
            int i8 = 0;
            while (i5 < i6) {
                while (i2 < length2) {
                    double d2 = dataRowArr[i5].getRow()[i2];
                    dArr3[i2] = dArr3[i2] + d2;
                    dArr2[i8][i2] = d2;
                    i2++;
                }
                i5++;
                i8++;
                i2 = 0;
            }
            MathArrays.scaleInPlace(1.0d / i7, dArr3);
            arrayList.add(new Pair(Double.valueOf(d), new MultivariateNormalDistribution(dArr3, new Covariance(dArr2).getCovarianceMatrix().getData())));
            i2 = 0;
        }
        return new MixtureMultivariateNormalDistribution(arrayList);
    }

    public double getLogLikelihood() {
        return this.logLikelihood;
    }

    public void fit(MixtureMultivariateNormalDistribution mixtureMultivariateNormalDistribution, int i, double d) throws NotStrictlyPositiveException, SingularMatrixException, DimensionMismatchException {
        int i2 = i;
        if (i2 < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        if (d < Double.MIN_VALUE) {
            throw new NotStrictlyPositiveException(Double.valueOf(d));
        }
        double[][] dArr = this.data;
        int length = dArr.length;
        int length2 = dArr[0].length;
        int size = mixtureMultivariateNormalDistribution.getComponents().size();
        int length3 = mixtureMultivariateNormalDistribution.getComponents().get(0).getSecond().getMeans().length;
        if (length3 != length2) {
            throw new DimensionMismatchException(length3, length2);
        }
        this.logLikelihood = Double.NEGATIVE_INFINITY;
        this.fittedModel = new MixtureMultivariateNormalDistribution(mixtureMultivariateNormalDistribution.getComponents());
        int i3 = 0;
        double d2 = 0.0d;
        while (true) {
            int i4 = i3 + 1;
            if (i3 > i2 || FastMath.abs(d2 - this.logLikelihood) <= d) {
                break;
            }
            double d3 = this.logLikelihood;
            List<Pair<Double, MultivariateNormalDistribution>> components = this.fittedModel.getComponents();
            double[] dArr2 = new double[size];
            MultivariateNormalDistribution[] multivariateNormalDistributionArr = new MultivariateNormalDistribution[size];
            for (int i5 = 0; i5 < size; i5++) {
                dArr2[i5] = components.get(i5).getFirst().doubleValue();
                multivariateNormalDistributionArr[i5] = components.get(i5).getSecond();
            }
            double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, size);
            double[] dArr4 = new double[size];
            double[][] dArr5 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, length2);
            double dLog = 0.0d;
            for (int i6 = 0; i6 < length; i6++) {
                double dDensity = this.fittedModel.density(this.data[i6]);
                dLog += FastMath.log(dDensity);
                int i7 = 0;
                while (i7 < size) {
                    double d4 = d3;
                    dArr3[i6][i7] = (dArr2[i7] * multivariateNormalDistributionArr[i7].density(this.data[i6])) / dDensity;
                    dArr4[i7] = dArr4[i7] + dArr3[i6][i7];
                    int i8 = 0;
                    while (i8 < length2) {
                        double[] dArr6 = dArr5[i7];
                        dArr6[i8] = dArr6[i8] + (dArr3[i6][i7] * this.data[i6][i8]);
                        i8++;
                        dDensity = dDensity;
                    }
                    i7++;
                    d3 = d4;
                }
            }
            double d5 = d3;
            double d6 = length;
            this.logLikelihood = dLog / d6;
            double[] dArr7 = new double[size];
            double[][] dArr8 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, length2);
            for (int i9 = 0; i9 < size; i9++) {
                dArr7[i9] = dArr4[i9] / d6;
                for (int i10 = 0; i10 < length2; i10++) {
                    dArr8[i9][i10] = dArr5[i9][i10] / dArr4[i9];
                }
            }
            RealMatrix[] realMatrixArr = new RealMatrix[size];
            for (int i11 = 0; i11 < size; i11++) {
                realMatrixArr[i11] = new Array2DRowRealMatrix(length2, length2);
            }
            for (int i12 = 0; i12 < length; i12++) {
                int i13 = 0;
                while (i13 < size) {
                    Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(MathArrays.ebeSubtract(this.data[i12], dArr8[i13]));
                    realMatrixArr[i13] = realMatrixArr[i13].add(array2DRowRealMatrix.multiply(array2DRowRealMatrix.transpose()).scalarMultiply(dArr3[i12][i13]));
                    i13++;
                    i4 = i4;
                }
            }
            int i14 = i4;
            double[][][] dArr9 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, size, length2, length2);
            for (int i15 = 0; i15 < size; i15++) {
                RealMatrix realMatrixScalarMultiply = realMatrixArr[i15].scalarMultiply(1.0d / dArr4[i15]);
                realMatrixArr[i15] = realMatrixScalarMultiply;
                dArr9[i15] = realMatrixScalarMultiply.getData();
            }
            this.fittedModel = new MixtureMultivariateNormalDistribution(dArr7, dArr8, dArr9);
            i3 = i14;
            i2 = i;
            d2 = d5;
        }
        if (FastMath.abs(d2 - this.logLikelihood) > d) {
            throw new ConvergenceException();
        }
    }

    public void fit(MixtureMultivariateNormalDistribution mixtureMultivariateNormalDistribution) throws NotStrictlyPositiveException, SingularMatrixException, DimensionMismatchException {
        fit(mixtureMultivariateNormalDistribution, 1000, 1.0E-5d);
    }

    public MixtureMultivariateNormalDistribution getFittedModel() {
        return new MixtureMultivariateNormalDistribution(this.fittedModel.getComponents());
    }

    private static class DataRow implements Comparable<DataRow> {
        private final double[] row;
        private Double mean;

        DataRow(double[] dArr) {
            this.row = dArr;
            this.mean = Double.valueOf(0.0d);
            for (double d : dArr) {
                this.mean = Double.valueOf(this.mean.doubleValue() + d);
            }
            this.mean = Double.valueOf(this.mean.doubleValue() / dArr.length);
        }

        public double[] getRow() {
            return this.row;
        }

        @Override // java.lang.Comparable
        public int compareTo(DataRow dataRow) {
            return this.mean.compareTo(dataRow.mean);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof DataRow) {
                return MathArrays.equals(this.row, ((DataRow) obj).row);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.row);
        }
    }
}
