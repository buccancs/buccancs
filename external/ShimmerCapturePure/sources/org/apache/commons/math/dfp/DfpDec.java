package org.apache.commons.math.dfp;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/dfp/DfpDec.class */
public class DfpDec extends Dfp {
    protected DfpDec(DfpField factory) {
        super(factory);
    }

    protected DfpDec(DfpField factory, byte x) {
        super(factory, x);
    }

    protected DfpDec(DfpField factory, int x) {
        super(factory, x);
    }

    protected DfpDec(DfpField factory, long x) {
        super(factory, x);
    }

    protected DfpDec(DfpField factory, double x) {
        super(factory, x);
        round(0);
    }

    public DfpDec(Dfp d) {
        super(d);
        round(0);
    }

    protected DfpDec(DfpField factory, String s) {
        super(factory, s);
        round(0);
    }

    protected DfpDec(DfpField factory, byte sign, byte nans) {
        super(factory, sign, nans);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.commons.math.dfp.DfpField] */
    @Override // org.apache.commons.math.dfp.Dfp
    public Dfp newInstance() {
        return new DfpDec((DfpField) getField2());
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.commons.math.dfp.DfpField] */
    @Override // org.apache.commons.math.dfp.Dfp
    public Dfp newInstance(byte x) {
        return new DfpDec((DfpField) getField2(), x);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.commons.math.dfp.DfpField] */
    @Override // org.apache.commons.math.dfp.Dfp
    public Dfp newInstance(int x) {
        return new DfpDec((DfpField) getField2(), x);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.commons.math.dfp.DfpField] */
    @Override // org.apache.commons.math.dfp.Dfp
    public Dfp newInstance(long x) {
        return new DfpDec((DfpField) getField2(), x);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.commons.math.dfp.DfpField] */
    @Override // org.apache.commons.math.dfp.Dfp
    public Dfp newInstance(double x) {
        return new DfpDec((DfpField) getField2(), x);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [org.apache.commons.math.dfp.DfpField] */
    /* JADX WARN: Type inference failed for: r0v5, types: [org.apache.commons.math.dfp.DfpField] */
    /* JADX WARN: Type inference failed for: r1v1, types: [org.apache.commons.math.dfp.DfpField] */
    @Override // org.apache.commons.math.dfp.Dfp
    public Dfp newInstance(Dfp d) {
        if (getField2().getRadixDigits() != d.getField2().getRadixDigits()) {
            getField2().setIEEEFlagsBits(1);
            Dfp result = newInstance(getZero());
            result.nans = (byte) 3;
            return dotrap(1, "newInstance", d, result);
        }
        return new DfpDec(d);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.commons.math.dfp.DfpField] */
    @Override // org.apache.commons.math.dfp.Dfp
    public Dfp newInstance(String s) {
        return new DfpDec((DfpField) getField2(), s);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.commons.math.dfp.DfpField] */
    @Override // org.apache.commons.math.dfp.Dfp
    public Dfp newInstance(byte sign, byte nans) {
        return new DfpDec(getField2(), sign, nans);
    }

    protected int getDecimalDigits() {
        return (getRadixDigits() * 4) - 3;
    }

    /* JADX WARN: Type inference failed for: r0v101, types: [org.apache.commons.math.dfp.DfpField] */
    /* JADX WARN: Type inference failed for: r0v93, types: [org.apache.commons.math.dfp.DfpField] */
    /* JADX WARN: Type inference failed for: r0v98, types: [org.apache.commons.math.dfp.DfpField] */
    /* JADX WARN: Type inference failed for: r1v21, types: [org.apache.commons.math.dfp.DfpField] */
    @Override // org.apache.commons.math.dfp.Dfp
    protected int round(int in) {
        int n;
        int discarded;
        boolean inc;
        int msb = this.mant[this.mant.length - 1];
        if (msb == 0) {
            return 0;
        }
        int cmaxdigits = this.mant.length * 4;
        int lsbthreshold = 1000;
        while (lsbthreshold > msb) {
            lsbthreshold /= 10;
            cmaxdigits--;
        }
        int digits = getDecimalDigits();
        int lsbshift = cmaxdigits - digits;
        int lsd = lsbshift / 4;
        int lsbthreshold2 = 1;
        for (int i = 0; i < lsbshift % 4; i++) {
            lsbthreshold2 *= 10;
        }
        int lsb = this.mant[lsd];
        if (lsbthreshold2 <= 1 && digits == (4 * this.mant.length) - 3) {
            return super.round(in);
        }
        if (lsbthreshold2 == 1) {
            n = (this.mant[lsd - 1] / 1000) % 10;
            int[] iArr = this.mant;
            int i2 = lsd - 1;
            iArr[i2] = iArr[i2] % 1000;
            discarded = in | this.mant[lsd - 1];
        } else {
            n = ((lsb * 10) / lsbthreshold2) % 10;
            discarded = in | (lsb % (lsbthreshold2 / 10));
        }
        for (int i3 = 0; i3 < lsd; i3++) {
            discarded |= this.mant[i3];
            this.mant[i3] = 0;
        }
        this.mant[lsd] = (lsb / lsbthreshold2) * lsbthreshold2;
        switch (getField2().getRoundingMode()) {
            case ROUND_DOWN:
                inc = false;
                break;
            case ROUND_UP:
                inc = (n == 0 && discarded == 0) ? false : true;
                break;
            case ROUND_HALF_UP:
                inc = n >= 5;
                break;
            case ROUND_HALF_DOWN:
                inc = n > 5;
                break;
            case ROUND_HALF_EVEN:
                inc = n > 5 || (n == 5 && discarded != 0) || (n == 5 && discarded == 0 && ((lsb / lsbthreshold2) & 1) == 1);
                break;
            case ROUND_HALF_ODD:
                inc = n > 5 || (n == 5 && discarded != 0) || (n == 5 && discarded == 0 && ((lsb / lsbthreshold2) & 1) == 0);
                break;
            case ROUND_CEIL:
                inc = this.sign == 1 && !(n == 0 && discarded == 0);
                break;
            case ROUND_FLOOR:
            default:
                inc = this.sign == -1 && !(n == 0 && discarded == 0);
                break;
        }
        if (inc) {
            int rh = lsbthreshold2;
            for (int i4 = lsd; i4 < this.mant.length; i4++) {
                int r = this.mant[i4] + rh;
                rh = r / 10000;
                this.mant[i4] = r % 10000;
            }
            if (rh != 0) {
                shiftRight();
                this.mant[this.mant.length - 1] = rh;
            }
        }
        if (this.exp < -32767) {
            getField2().setIEEEFlagsBits(8);
            return 8;
        }
        if (this.exp > 32768) {
            getField2().setIEEEFlagsBits(4);
            return 4;
        }
        if (n != 0 || discarded != 0) {
            getField2().setIEEEFlagsBits(16);
            return 16;
        }
        return 0;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [org.apache.commons.math.dfp.DfpField] */
    /* JADX WARN: Type inference failed for: r0v41, types: [org.apache.commons.math.dfp.DfpField] */
    /* JADX WARN: Type inference failed for: r0v47, types: [org.apache.commons.math.dfp.DfpField] */
    /* JADX WARN: Type inference failed for: r0v73, types: [org.apache.commons.math.dfp.DfpField] */
    /* JADX WARN: Type inference failed for: r1v1, types: [org.apache.commons.math.dfp.DfpField] */
    @Override // org.apache.commons.math.dfp.Dfp
    public Dfp nextAfter(Dfp x) {
        Dfp inc;
        Dfp result;
        if (getField2().getRadixDigits() != x.getField2().getRadixDigits()) {
            getField2().setIEEEFlagsBits(1);
            Dfp result2 = newInstance(getZero());
            result2.nans = (byte) 3;
            return dotrap(1, "nextAfter", x, result2);
        }
        boolean up = false;
        if (lessThan(x)) {
            up = true;
        }
        if (equals(x)) {
            return newInstance(x);
        }
        if (lessThan(getZero())) {
            up = !up;
        }
        if (up) {
            Dfp inc2 = copysign(power10((log10() - getDecimalDigits()) + 1), this);
            if (equals(getZero())) {
                inc2 = power10K(((-32767) - this.mant.length) - 1);
            }
            if (inc2.equals(getZero())) {
                result = copysign(newInstance(getZero()), this);
            } else {
                result = add(inc2);
            }
        } else {
            Dfp inc3 = copysign(power10(log10()), this);
            if (equals(inc3)) {
                inc = inc3.divide(power10(getDecimalDigits()));
            } else {
                inc = inc3.divide(power10(getDecimalDigits() - 1));
            }
            if (equals(getZero())) {
                inc = power10K(((-32767) - this.mant.length) - 1);
            }
            if (inc.equals(getZero())) {
                result = copysign(newInstance(getZero()), this);
            } else {
                result = subtract(inc);
            }
        }
        if (result.classify() == 1 && classify() != 1) {
            getField2().setIEEEFlagsBits(16);
            result = dotrap(16, "nextAfter", x, result);
        }
        if (result.equals(getZero()) && !equals(getZero())) {
            getField2().setIEEEFlagsBits(16);
            result = dotrap(16, "nextAfter", x, result);
        }
        return result;
    }
}
