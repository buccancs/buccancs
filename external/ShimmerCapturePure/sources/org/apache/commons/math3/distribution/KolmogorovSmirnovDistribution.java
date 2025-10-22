package org.apache.commons.math3.distribution;

import java.io.Serializable;
import java.lang.reflect.Array;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.fraction.FractionConversionException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class KolmogorovSmirnovDistribution implements Serializable {
    private static final long serialVersionUID = -4670676796862967187L;
    private int n;

    public KolmogorovSmirnovDistribution(int i) throws NotStrictlyPositiveException {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_NUMBER_OF_SAMPLES, Integer.valueOf(i));
        }
        this.n = i;
    }

    public double cdf(double d) throws MathArithmeticException {
        return cdf(d, false);
    }

    public double cdfExact(double d) throws MathArithmeticException {
        return cdf(d, true);
    }

    public double cdf(double d, boolean z) throws MathArithmeticException {
        int i = this.n;
        double d2 = 1.0d;
        double d3 = 1.0d / i;
        double d4 = 0.5d * d3;
        if (d <= d4) {
            return 0.0d;
        }
        if (d4 >= d || d > d3) {
            if (1.0d - d3 <= d && d < 1.0d) {
                return 1.0d - (FastMath.pow(1.0d - d, i) * 2.0d);
            }
            if (1.0d <= d) {
                return 1.0d;
            }
            return z ? exactK(d) : roundedK(d);
        }
        double d5 = (d * 2.0d) - d3;
        for (int i2 = 1; i2 <= this.n; i2++) {
            d2 *= i2 * d5;
        }
        return d2;
    }

    private double exactK(double d) throws NotPositiveException, NonSquareMatrixException, MathArithmeticException {
        int iCeil = ((int) FastMath.ceil(this.n * d)) - 1;
        BigFraction bigFractionDivide = (BigFraction) createH(d).power(this.n).getEntry(iCeil, iCeil);
        for (int i = 1; i <= this.n; i++) {
            bigFractionDivide = bigFractionDivide.multiply(i).divide(this.n);
        }
        return bigFractionDivide.bigDecimalValue(20, 4).doubleValue();
    }

    private double roundedK(double d) throws OutOfRangeException, FractionConversionException, NumberIsTooLargeException, MathArithmeticException {
        int iCeil = (int) FastMath.ceil(this.n * d);
        FieldMatrix<BigFraction> fieldMatrixCreateH = createH(d);
        int rowDimension = fieldMatrixCreateH.getRowDimension();
        Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(rowDimension, rowDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                array2DRowRealMatrix.setEntry(i, i2, ((BigFraction) fieldMatrixCreateH.getEntry(i, i2)).doubleValue());
            }
        }
        int i3 = 1;
        int i4 = iCeil - 1;
        double entry = array2DRowRealMatrix.power(this.n).getEntry(i4, i4);
        while (true) {
            int i5 = this.n;
            if (i3 > i5) {
                return entry;
            }
            entry *= i3 / i5;
            i3++;
        }
    }

    private FieldMatrix<BigFraction> createH(double d) throws FractionConversionException, NumberIsTooLargeException {
        BigFraction bigFraction;
        int i;
        int iCeil = (int) FastMath.ceil(this.n * d);
        int i2 = iCeil * 2;
        int i3 = i2 - 1;
        double d2 = iCeil - (this.n * d);
        if (d2 >= 1.0d) {
            throw new NumberIsTooLargeException(Double.valueOf(d2), Double.valueOf(1.0d), false);
        }
        try {
            try {
                bigFraction = new BigFraction(d2, 1.0E-20d, 10000);
            } catch (FractionConversionException unused) {
                bigFraction = new BigFraction(d2, 1.0E-10d, 10000);
            }
        } catch (FractionConversionException unused2) {
            bigFraction = new BigFraction(d2, 1.0E-5d, 10000);
        }
        BigFraction[][] bigFractionArr = (BigFraction[][]) Array.newInstance((Class<?>) BigFraction.class, i3, i3);
        for (int i4 = 0; i4 < i3; i4++) {
            for (int i5 = 0; i5 < i3; i5++) {
                if ((i4 - i5) + 1 < 0) {
                    bigFractionArr[i4][i5] = BigFraction.ZERO;
                } else {
                    bigFractionArr[i4][i5] = BigFraction.ONE;
                }
            }
        }
        BigFraction[] bigFractionArr2 = new BigFraction[i3];
        bigFractionArr2[0] = bigFraction;
        for (int i6 = 1; i6 < i3; i6++) {
            bigFractionArr2[i6] = bigFraction.multiply(bigFractionArr2[i6 - 1]);
        }
        for (int i7 = 0; i7 < i3; i7++) {
            BigFraction[] bigFractionArr3 = bigFractionArr[i7];
            bigFractionArr3[0] = bigFractionArr3[0].subtract(bigFractionArr2[i7]);
            BigFraction[] bigFractionArr4 = bigFractionArr[i2 - 2];
            bigFractionArr4[i7] = bigFractionArr4[i7].subtract(bigFractionArr2[(i3 - i7) - 1]);
        }
        if (bigFraction.compareTo(BigFraction.ONE_HALF) == 1) {
            BigFraction[] bigFractionArr5 = bigFractionArr[i2 - 2];
            bigFractionArr5[0] = bigFractionArr5[0].add(bigFraction.multiply(2).subtract(1).pow(i3));
        }
        int i8 = 0;
        while (i8 < i3) {
            int i9 = 0;
            while (true) {
                i = i8 + 1;
                if (i9 < i) {
                    int i10 = (i8 - i9) + 1;
                    if (i10 > 0) {
                        for (int i11 = 2; i11 <= i10; i11++) {
                            BigFraction[] bigFractionArr6 = bigFractionArr[i8];
                            bigFractionArr6[i9] = bigFractionArr6[i9].divide(i11);
                        }
                    }
                    i9++;
                }
            }
            i8 = i;
        }
        return new Array2DRowFieldMatrix(BigFractionField.getInstance(), bigFractionArr);
    }
}
