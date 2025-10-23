package org.apache.commons.math3.analysis.polynomials;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class PolynomialsUtils {
    private static final List<BigFraction> CHEBYSHEV_COEFFICIENTS;
    private static final List<BigFraction> HERMITE_COEFFICIENTS;
    private static final Map<JacobiKey, List<BigFraction>> JACOBI_COEFFICIENTS;
    private static final List<BigFraction> LAGUERRE_COEFFICIENTS;
    private static final List<BigFraction> LEGENDRE_COEFFICIENTS;

    static {
        ArrayList arrayList = new ArrayList();
        CHEBYSHEV_COEFFICIENTS = arrayList;
        arrayList.add(BigFraction.ONE);
        arrayList.add(BigFraction.ZERO);
        arrayList.add(BigFraction.ONE);
        ArrayList arrayList2 = new ArrayList();
        HERMITE_COEFFICIENTS = arrayList2;
        arrayList2.add(BigFraction.ONE);
        arrayList2.add(BigFraction.ZERO);
        arrayList2.add(BigFraction.TWO);
        ArrayList arrayList3 = new ArrayList();
        LAGUERRE_COEFFICIENTS = arrayList3;
        arrayList3.add(BigFraction.ONE);
        arrayList3.add(BigFraction.ONE);
        arrayList3.add(BigFraction.MINUS_ONE);
        ArrayList arrayList4 = new ArrayList();
        LEGENDRE_COEFFICIENTS = arrayList4;
        arrayList4.add(BigFraction.ONE);
        arrayList4.add(BigFraction.ZERO);
        arrayList4.add(BigFraction.ONE);
        JACOBI_COEFFICIENTS = new HashMap();
    }

    private PolynomialsUtils() {
    }

    public static PolynomialFunction createChebyshevPolynomial(int i) {
        return buildPolynomial(i, CHEBYSHEV_COEFFICIENTS, new RecurrenceCoefficientsGenerator() { // from class: org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.1
            private final BigFraction[] coeffs = {BigFraction.ZERO, BigFraction.TWO, BigFraction.ONE};

            @Override // org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.RecurrenceCoefficientsGenerator
            public BigFraction[] generate(int i2) {
                return this.coeffs;
            }
        });
    }

    public static PolynomialFunction createHermitePolynomial(int i) {
        return buildPolynomial(i, HERMITE_COEFFICIENTS, new RecurrenceCoefficientsGenerator() { // from class: org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.2
            @Override // org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.RecurrenceCoefficientsGenerator
            public BigFraction[] generate(int i2) {
                return new BigFraction[]{BigFraction.ZERO, BigFraction.TWO, new BigFraction(i2 * 2)};
            }
        });
    }

    public static PolynomialFunction createLaguerrePolynomial(int i) {
        return buildPolynomial(i, LAGUERRE_COEFFICIENTS, new RecurrenceCoefficientsGenerator() { // from class: org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.3
            @Override // org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.RecurrenceCoefficientsGenerator
            public BigFraction[] generate(int i2) {
                int i3 = i2 + 1;
                return new BigFraction[]{new BigFraction((i2 * 2) + 1, i3), new BigFraction(-1, i3), new BigFraction(i2, i3)};
            }
        });
    }

    public static PolynomialFunction createLegendrePolynomial(int i) {
        return buildPolynomial(i, LEGENDRE_COEFFICIENTS, new RecurrenceCoefficientsGenerator() { // from class: org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.4
            @Override // org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.RecurrenceCoefficientsGenerator
            public BigFraction[] generate(int i2) {
                int i3 = i2 + 1;
                return new BigFraction[]{BigFraction.ZERO, new BigFraction(i2 + i3, i3), new BigFraction(i2, i3)};
            }
        });
    }

    public static PolynomialFunction createJacobiPolynomial(int i, final int i2, final int i3) {
        JacobiKey jacobiKey = new JacobiKey(i2, i3);
        Map<JacobiKey, List<BigFraction>> map = JACOBI_COEFFICIENTS;
        if (!map.containsKey(jacobiKey)) {
            ArrayList arrayList = new ArrayList();
            map.put(jacobiKey, arrayList);
            arrayList.add(BigFraction.ONE);
            arrayList.add(new BigFraction(i2 - i3, 2));
            arrayList.add(new BigFraction(i2 + 2 + i3, 2));
        }
        return buildPolynomial(i, map.get(jacobiKey), new RecurrenceCoefficientsGenerator() { // from class: org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.5
            @Override // org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.RecurrenceCoefficientsGenerator
            public BigFraction[] generate(int i4) {
                int i5 = i4 + 1;
                int i6 = i2 + i5 + i3;
                int i7 = i6 + i5;
                int i8 = i7 - 1;
                int i9 = i7 - 2;
                int i10 = i5 * 2 * i6 * i9;
                int i11 = i2;
                int i12 = i3;
                return new BigFraction[]{new BigFraction(((i11 * i11) - (i12 * i12)) * i8, i10), new BigFraction(i8 * i7 * i9, i10), new BigFraction(((i2 + i5) - 1) * 2 * ((i5 + i3) - 1) * i7, i10)};
            }
        });
    }

    public static double[] shift(double[] dArr, double d) {
        int length = dArr.length;
        double[] dArr2 = new double[length];
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, length, length);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 <= i; i2++) {
                iArr[i][i2] = (int) CombinatoricsUtils.binomialCoefficient(i, i2);
            }
        }
        for (int i3 = 0; i3 < length; i3++) {
            dArr2[0] = dArr2[0] + (dArr[i3] * FastMath.pow(d, i3));
        }
        int i4 = length - 1;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i5;
            while (i6 < i4) {
                int i7 = i5 + 1;
                int i8 = i6 + 1;
                dArr2[i7] = dArr2[i7] + (iArr[i8][r4] * dArr[i8] * FastMath.pow(d, i6 - i5));
                i6 = i8;
            }
        }
        return dArr2;
    }

    private static PolynomialFunction buildPolynomial(int i, List<BigFraction> list, RecurrenceCoefficientsGenerator recurrenceCoefficientsGenerator) {
        synchronized (list) {
            int iFloor = ((int) FastMath.floor(FastMath.sqrt(list.size() * 2))) - 1;
            if (i > iFloor) {
                computeUpToDegree(i, iFloor, recurrenceCoefficientsGenerator, list);
            }
        }
        int i2 = i + 1;
        int i3 = (i * i2) / 2;
        double[] dArr = new double[i2];
        for (int i4 = 0; i4 <= i; i4++) {
            dArr[i4] = list.get(i3 + i4).doubleValue();
        }
        return new PolynomialFunction(dArr);
    }

    private static void computeUpToDegree(int i, int i2, RecurrenceCoefficientsGenerator recurrenceCoefficientsGenerator, List<BigFraction> list) {
        int i3 = ((i2 - 1) * i2) / 2;
        while (i2 < i) {
            int i4 = i3 + i2;
            BigFraction[] bigFractionArrGenerate = recurrenceCoefficientsGenerator.generate(i2);
            BigFraction bigFraction = list.get(i4);
            list.add(bigFraction.multiply(bigFractionArrGenerate[0]).subtract(list.get(i3).multiply(bigFractionArrGenerate[2])));
            int i5 = 1;
            while (i5 < i2) {
                BigFraction bigFraction2 = list.get(i4 + i5);
                list.add(bigFraction2.multiply(bigFractionArrGenerate[0]).add(bigFraction.multiply(bigFractionArrGenerate[1])).subtract(list.get(i3 + i5).multiply(bigFractionArrGenerate[2])));
                i5++;
                bigFraction = bigFraction2;
            }
            BigFraction bigFraction3 = list.get(i4 + i2);
            list.add(bigFraction3.multiply(bigFractionArrGenerate[0]).add(bigFraction.multiply(bigFractionArrGenerate[1])));
            list.add(bigFraction3.multiply(bigFractionArrGenerate[1]));
            i2++;
            i3 = i4;
        }
    }

    private interface RecurrenceCoefficientsGenerator {
        BigFraction[] generate(int i);
    }

    private static class JacobiKey {
        private final int v;
        private final int w;

        JacobiKey(int i, int i2) {
            this.v = i;
            this.w = i2;
        }

        public int hashCode() {
            return (this.v << 16) ^ this.w;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof JacobiKey)) {
                return false;
            }
            JacobiKey jacobiKey = (JacobiKey) obj;
            return this.v == jacobiKey.v && this.w == jacobiKey.w;
        }
    }
}
