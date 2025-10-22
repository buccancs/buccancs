package org.apache.commons.math3.stat.inference;

/* loaded from: classes5.dex */
public class BinomialTest {
    public boolean binomialTest(int i, int i2, double d, AlternativeHypothesis alternativeHypothesis, double d2) {
        return binomialTest(i, i2, d, alternativeHypothesis) < d2;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x004e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0050 A[EDGE_INSN: B:43:0x0050->B:26:0x0050 BREAK  A[LOOP:0: B:16:0x002c->B:45:?], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public double binomialTest(int r10, int r11, double r12, org.apache.commons.math3.stat.inference.AlternativeHypothesis r14) {
        /*
            r9 = this;
            if (r10 < 0) goto La9
            if (r11 < 0) goto L9f
            r0 = 0
            r2 = 0
            r3 = 1
            int r4 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r4 < 0) goto L8d
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r6 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r6 > 0) goto L8d
            r6 = 2
            if (r10 < r11) goto L77
            if (r14 == 0) goto L71
            org.apache.commons.math3.distribution.BinomialDistribution r7 = new org.apache.commons.math3.distribution.BinomialDistribution
            r8 = 0
            r7.<init>(r8, r10, r12)
            int[] r12 = org.apache.commons.math3.stat.inference.BinomialTest.AnonymousClass1.$SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis
            int r13 = r14.ordinal()
            r12 = r12[r13]
            if (r12 == r3) goto L6a
            if (r12 == r6) goto L65
            r13 = 3
            if (r12 != r13) goto L51
        L2c:
            double r12 = r7.probability(r2)
            double r3 = r7.probability(r10)
            int r14 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r14 != 0) goto L42
            r3 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r12 = r12 * r3
            double r0 = r0 + r12
            int r2 = r2 + 1
        L3f:
            int r10 = r10 + (-1)
            goto L4c
        L42:
            int r14 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r14 >= 0) goto L4a
            double r0 = r0 + r12
            int r2 = r2 + 1
            goto L4c
        L4a:
            double r0 = r0 + r3
            goto L3f
        L4c:
            if (r2 > r11) goto L50
            if (r10 >= r11) goto L2c
        L50:
            return r0
        L51:
            org.apache.commons.math3.exception.MathInternalError r10 = new org.apache.commons.math3.exception.MathInternalError
            org.apache.commons.math3.exception.util.LocalizedFormats r11 = org.apache.commons.math3.exception.util.LocalizedFormats.OUT_OF_RANGE_SIMPLE
            java.lang.Object[] r12 = new java.lang.Object[r13]
            r12[r2] = r14
            org.apache.commons.math3.stat.inference.AlternativeHypothesis r13 = org.apache.commons.math3.stat.inference.AlternativeHypothesis.TWO_SIDED
            r12[r3] = r13
            org.apache.commons.math3.stat.inference.AlternativeHypothesis r13 = org.apache.commons.math3.stat.inference.AlternativeHypothesis.LESS_THAN
            r12[r6] = r13
            r10.<init>(r11, r12)
            throw r10
        L65:
            double r10 = r7.cumulativeProbability(r11)
            return r10
        L6a:
            int r11 = r11 - r3
            double r10 = r7.cumulativeProbability(r11)
            double r4 = r4 - r10
            return r4
        L71:
            org.apache.commons.math3.exception.NullArgumentException r10 = new org.apache.commons.math3.exception.NullArgumentException
            r10.<init>()
            throw r10
        L77:
            org.apache.commons.math3.exception.MathIllegalArgumentException r12 = new org.apache.commons.math3.exception.MathIllegalArgumentException
            org.apache.commons.math3.exception.util.LocalizedFormats r13 = org.apache.commons.math3.exception.util.LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER
            java.lang.Object[] r14 = new java.lang.Object[r6]
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r14[r2] = r10
            java.lang.Integer r10 = java.lang.Integer.valueOf(r11)
            r14[r3] = r10
            r12.<init>(r13, r14)
            throw r12
        L8d:
            org.apache.commons.math3.exception.OutOfRangeException r10 = new org.apache.commons.math3.exception.OutOfRangeException
            java.lang.Double r11 = java.lang.Double.valueOf(r12)
            java.lang.Integer r12 = java.lang.Integer.valueOf(r2)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r3)
            r10.<init>(r11, r12, r13)
            throw r10
        L9f:
            org.apache.commons.math3.exception.NotPositiveException r10 = new org.apache.commons.math3.exception.NotPositiveException
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r10.<init>(r11)
            throw r10
        La9:
            org.apache.commons.math3.exception.NotPositiveException r11 = new org.apache.commons.math3.exception.NotPositiveException
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r11.<init>(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.inference.BinomialTest.binomialTest(int, int, double, org.apache.commons.math3.stat.inference.AlternativeHypothesis):double");
    }

    /* renamed from: org.apache.commons.math3.stat.inference.BinomialTest$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis;

        static {
            int[] iArr = new int[AlternativeHypothesis.values().length];
            $SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis = iArr;
            try {
                iArr[AlternativeHypothesis.GREATER_THAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis[AlternativeHypothesis.LESS_THAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis[AlternativeHypothesis.TWO_SIDED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
