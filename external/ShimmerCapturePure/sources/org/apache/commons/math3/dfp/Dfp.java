package org.apache.commons.math3.dfp;

import com.shimmerresearch.verisense.communication.VerisenseMessage;

import java.util.Arrays;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.dfp.DfpField;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class Dfp implements RealFieldElement<Dfp> {
    public static final int ERR_SCALE = 32760;
    public static final byte FINITE = 0;
    public static final byte INFINITE = 1;
    public static final int MAX_EXP = 32768;
    public static final int MIN_EXP = -32767;
    public static final byte QNAN = 3;
    public static final int RADIX = 10000;
    public static final byte SNAN = 2;
    private static final String ADD_TRAP = "add";
    private static final String ALIGN_TRAP = "align";
    private static final String DIVIDE_TRAP = "divide";
    private static final String GREATER_THAN_TRAP = "greaterThan";
    private static final String LESS_THAN_TRAP = "lessThan";
    private static final String MULTIPLY_TRAP = "multiply";
    private static final String NAN_STRING = "NaN";
    private static final String NEG_INFINITY_STRING = "-Infinity";
    private static final String NEW_INSTANCE_TRAP = "newInstance";
    private static final String NEXT_AFTER_TRAP = "nextAfter";
    private static final String POS_INFINITY_STRING = "Infinity";
    private static final String SQRT_TRAP = "sqrt";
    private static final String TRUNC_TRAP = "trunc";
    private final DfpField field;
    protected int exp;
    protected int[] mant;
    protected byte nans;
    protected byte sign;

    protected Dfp(DfpField dfpField) {
        this.mant = new int[dfpField.getRadixDigits()];
        this.sign = (byte) 1;
        this.exp = 0;
        this.nans = (byte) 0;
        this.field = dfpField;
    }

    protected Dfp(DfpField dfpField, byte b) {
        this(dfpField, b);
    }

    protected Dfp(DfpField dfpField, int i) {
        this(dfpField, i);
    }

    protected Dfp(DfpField dfpField, long j) {
        boolean z;
        this.mant = new int[dfpField.getRadixDigits()];
        int i = 0;
        this.nans = (byte) 0;
        this.field = dfpField;
        if (j == Long.MIN_VALUE) {
            j++;
            z = true;
        } else {
            z = false;
        }
        if (j < 0) {
            this.sign = (byte) -1;
            j = -j;
        } else {
            this.sign = (byte) 1;
        }
        this.exp = 0;
        while (j != 0) {
            int[] iArr = this.mant;
            int length = iArr.length;
            int i2 = this.exp;
            System.arraycopy(iArr, length - i2, iArr, (iArr.length - 1) - i2, i2);
            int[] iArr2 = this.mant;
            iArr2[iArr2.length - 1] = (int) (j % VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER);
            j /= VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER;
            this.exp++;
        }
        if (!z) {
            return;
        }
        while (true) {
            int[] iArr3 = this.mant;
            if (i >= iArr3.length - 1) {
                return;
            }
            int i3 = iArr3[i];
            if (i3 != 0) {
                iArr3[i] = i3 + 1;
                return;
            }
            i++;
        }
    }

    protected Dfp(DfpField dfpField, double d) {
        this.mant = new int[dfpField.getRadixDigits()];
        this.sign = (byte) 1;
        this.exp = 0;
        this.nans = (byte) 0;
        this.field = dfpField;
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        long j = jDoubleToLongBits & 4503599627370495L;
        int i = (int) ((9218868437227405312L & jDoubleToLongBits) >> 52);
        int i2 = i - 1023;
        if (i2 == -1023) {
            if (d == 0.0d) {
                if ((jDoubleToLongBits & Long.MIN_VALUE) != 0) {
                    this.sign = (byte) -1;
                    return;
                }
                return;
            } else {
                i2 = i - 1022;
                while ((j & 4503599627370496L) == 0) {
                    i2--;
                    j <<= 1;
                }
                j &= 4503599627370495L;
            }
        }
        if (i2 != 1024) {
            Dfp dfpMultiply = new Dfp(dfpField, j).divide(new Dfp(dfpField, 4503599627370496L)).add(dfpField.getOne()).multiply(DfpMath.pow(dfpField.getTwo(), i2));
            dfpMultiply = (jDoubleToLongBits & Long.MIN_VALUE) != 0 ? dfpMultiply.negate() : dfpMultiply;
            int[] iArr = dfpMultiply.mant;
            int[] iArr2 = this.mant;
            System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
            this.sign = dfpMultiply.sign;
            this.exp = dfpMultiply.exp;
            this.nans = dfpMultiply.nans;
            return;
        }
        if (d != d) {
            this.sign = (byte) 1;
            this.nans = (byte) 3;
        } else if (d < 0.0d) {
            this.sign = (byte) -1;
            this.nans = (byte) 1;
        } else {
            this.sign = (byte) 1;
            this.nans = (byte) 1;
        }
    }

    public Dfp(Dfp dfp) {
        this.mant = (int[]) dfp.mant.clone();
        this.sign = dfp.sign;
        this.exp = dfp.exp;
        this.nans = dfp.nans;
        this.field = dfp.field;
    }

    protected Dfp(DfpField dfpField, String str) {
        int iCharAt;
        int i;
        int[] iArr;
        String str2 = str;
        this.mant = new int[dfpField.getRadixDigits()];
        int i2 = 1;
        this.sign = (byte) 1;
        this.exp = 0;
        this.nans = (byte) 0;
        this.field = dfpField;
        int radixDigits = (getRadixDigits() * 4) + 8;
        char[] cArr = new char[radixDigits];
        if (str2.equals(POS_INFINITY_STRING)) {
            this.sign = (byte) 1;
            this.nans = (byte) 1;
            return;
        }
        if (str2.equals(NEG_INFINITY_STRING)) {
            this.sign = (byte) -1;
            this.nans = (byte) 1;
            return;
        }
        if (str2.equals(NAN_STRING)) {
            this.sign = (byte) 1;
            this.nans = (byte) 3;
            return;
        }
        int iIndexOf = str2.indexOf("e");
        iIndexOf = iIndexOf == -1 ? str2.indexOf("E") : iIndexOf;
        if (iIndexOf != -1) {
            String strSubstring = str2.substring(0, iIndexOf);
            String strSubstring2 = str2.substring(iIndexOf + 1);
            boolean z = false;
            iCharAt = 0;
            for (int i3 = 0; i3 < strSubstring2.length(); i3++) {
                if (strSubstring2.charAt(i3) == '-') {
                    z = true;
                } else if (strSubstring2.charAt(i3) >= '0' && strSubstring2.charAt(i3) <= '9') {
                    iCharAt = ((iCharAt * 10) + strSubstring2.charAt(i3)) - 48;
                }
            }
            iCharAt = z ? -iCharAt : iCharAt;
            str2 = strSubstring;
        } else {
            iCharAt = 0;
        }
        if (str2.indexOf("-") != -1) {
            this.sign = (byte) -1;
        }
        int i4 = 0;
        boolean z2 = false;
        int i5 = 0;
        do {
            if (str2.charAt(i4) >= '1' && str2.charAt(i4) <= '9') {
                break;
            }
            if (z2 && str2.charAt(i4) == '0') {
                i5--;
            }
            z2 = str2.charAt(i4) == '.' ? true : z2;
            i4++;
        } while (i4 != str2.length());
        cArr[0] = '0';
        cArr[1] = '0';
        cArr[2] = '0';
        cArr[3] = '0';
        int i6 = i5;
        int i7 = 0;
        int i8 = 4;
        while (true) {
            if (i4 == str2.length()) {
                i = 4;
                break;
            }
            i = 4;
            if (i8 == (this.mant.length * 4) + 5) {
                break;
            }
            if (str2.charAt(i4) == '.') {
                i4++;
                i6 = i7;
                i2 = 1;
                z2 = true;
            } else {
                if (str2.charAt(i4) < '0' || str2.charAt(i4) > '9') {
                    i4++;
                } else {
                    cArr[i8] = str2.charAt(i4);
                    i8++;
                    i4++;
                    i7++;
                }
                i2 = 1;
            }
        }
        if (z2 && i8 != i) {
            while (true) {
                i8--;
                if (i8 == i || cArr[i8] != '0') {
                    break;
                }
                i7--;
                i = 4;
            }
        }
        if (z2 && i7 == 0) {
            i6 = 0;
        }
        i6 = z2 ? i6 : i8 - 4;
        int i9 = i7 + 3;
        for (int i10 = 4; i9 > i10 && cArr[i9] == '0'; i10 = 4) {
            i9--;
        }
        int i11 = 4;
        int i12 = ((400 - i6) - (iCharAt % 4)) % 4;
        int i13 = 4 - i12;
        int i14 = i6 + i12;
        while (true) {
            int i15 = i9 - i13;
            iArr = this.mant;
            if (i15 >= iArr.length * 4) {
                break;
            }
            int i16 = 0;
            while (i16 < i11) {
                i9++;
                cArr[i9] = '0';
                i16++;
                i11 = 4;
            }
        }
        for (int length = iArr.length - i2; length >= 0; length--) {
            this.mant[length] = ((cArr[i13] - '0') * 1000) + ((cArr[i13 + 1] - '0') * 100) + ((cArr[i13 + 2] - '0') * 10) + (cArr[i13 + 3] - '0');
            i13 += 4;
        }
        this.exp = (i14 + iCharAt) / 4;
        if (i13 < radixDigits) {
            round((cArr[i13] - '0') * 1000);
        }
    }

    protected Dfp(DfpField dfpField, byte b, byte b2) {
        this.field = dfpField;
        this.mant = new int[dfpField.getRadixDigits()];
        this.sign = b;
        this.exp = 0;
        this.nans = b2;
    }

    private static int compare(Dfp dfp, Dfp dfp2) {
        int[] iArr = dfp.mant;
        if (iArr[iArr.length - 1] == 0) {
            int[] iArr2 = dfp2.mant;
            if (iArr2[iArr2.length - 1] == 0 && dfp.nans == 0 && dfp2.nans == 0) {
                return 0;
            }
        }
        byte b = dfp.sign;
        byte b2 = dfp2.sign;
        if (b != b2) {
            return b == -1 ? -1 : 1;
        }
        byte b3 = dfp.nans;
        if (b3 == 1 && dfp2.nans == 0) {
            return b;
        }
        if (b3 == 0 && dfp2.nans == 1) {
            return -b2;
        }
        if (b3 == 1 && dfp2.nans == 1) {
            return 0;
        }
        int[] iArr3 = dfp2.mant;
        if (iArr3[iArr3.length - 1] != 0 && iArr[iArr3.length - 1] != 0) {
            int i = dfp.exp;
            int i2 = dfp2.exp;
            if (i < i2) {
                return -b;
            }
            if (i > i2) {
                return b;
            }
        }
        for (int length = iArr.length - 1; length >= 0; length--) {
            int i3 = dfp.mant[length];
            int i4 = dfp2.mant[length];
            if (i3 > i4) {
                return dfp.sign;
            }
            if (i3 < i4) {
                return -dfp.sign;
            }
        }
        return 0;
    }

    public static Dfp copysign(Dfp dfp, Dfp dfp2) {
        Dfp dfpNewInstance = dfp.newInstance(dfp);
        dfpNewInstance.sign = dfp2.sign;
        return dfpNewInstance;
    }

    public int classify() {
        return this.nans;
    }

    @Override // org.apache.commons.math3.FieldElement
    public DfpField getField() {
        return this.field;
    }

    public boolean isInfinite() {
        return this.nans == 1;
    }

    public boolean isNaN() {
        byte b = this.nans;
        return b == 3 || b == 2;
    }

    public int log10K() {
        return this.exp - 1;
    }

    protected Dfp trap(int i, String str, Dfp dfp, Dfp dfp2, Dfp dfp3) {
        return dfp2;
    }

    public Dfp newInstance() {
        return new Dfp(getField());
    }

    public Dfp newInstance(byte b) {
        return new Dfp(getField(), b);
    }

    public Dfp newInstance(int i) {
        return new Dfp(getField(), i);
    }

    public Dfp newInstance(long j) {
        return new Dfp(getField(), j);
    }

    public Dfp newInstance(double d) {
        return new Dfp(getField(), d);
    }

    public Dfp newInstance(Dfp dfp) {
        if (this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp dfpNewInstance = newInstance(getZero());
            dfpNewInstance.nans = (byte) 3;
            return dotrap(1, NEW_INSTANCE_TRAP, dfp, dfpNewInstance);
        }
        return new Dfp(dfp);
    }

    public Dfp newInstance(String str) {
        return new Dfp(this.field, str);
    }

    public Dfp newInstance(byte b, byte b2) {
        return this.field.newDfp(b, b2);
    }

    public int getRadixDigits() {
        return this.field.getRadixDigits();
    }

    public Dfp getZero() {
        return this.field.getZero();
    }

    public Dfp getOne() {
        return this.field.getOne();
    }

    public Dfp getTwo() {
        return this.field.getTwo();
    }

    protected void shiftLeft() {
        for (int length = this.mant.length - 1; length > 0; length--) {
            int[] iArr = this.mant;
            iArr[length] = iArr[length - 1];
        }
        this.mant[0] = 0;
        this.exp--;
    }

    protected void shiftRight() {
        int i = 0;
        while (true) {
            int[] iArr = this.mant;
            if (i < iArr.length - 1) {
                int i2 = i + 1;
                iArr[i] = iArr[i2];
                i = i2;
            } else {
                iArr[iArr.length - 1] = 0;
                this.exp++;
                return;
            }
        }
    }

    protected int align(int i) {
        int i2 = this.exp - i;
        int i3 = i2 < 0 ? -i2 : i2;
        if (i2 == 0) {
            return 0;
        }
        int[] iArr = this.mant;
        if (i3 > iArr.length + 1) {
            Arrays.fill(iArr, 0);
            this.exp = i;
            this.field.setIEEEFlagsBits(16);
            dotrap(16, ALIGN_TRAP, this, this);
            return 0;
        }
        boolean z = false;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i2 < 0) {
                if (i4 != 0) {
                    z = true;
                }
                i4 = this.mant[0];
                shiftRight();
            } else {
                shiftLeft();
            }
        }
        if (z) {
            this.field.setIEEEFlagsBits(16);
            dotrap(16, ALIGN_TRAP, this, this);
        }
        return i4;
    }

    public boolean lessThan(Dfp dfp) {
        if (this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp dfpNewInstance = newInstance(getZero());
            dfpNewInstance.nans = (byte) 3;
            dotrap(1, LESS_THAN_TRAP, dfp, dfpNewInstance);
            return false;
        }
        if (!isNaN() && !dfp.isNaN()) {
            return compare(this, dfp) < 0;
        }
        this.field.setIEEEFlagsBits(1);
        dotrap(1, LESS_THAN_TRAP, dfp, newInstance(getZero()));
        return false;
    }

    public boolean greaterThan(Dfp dfp) {
        if (this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp dfpNewInstance = newInstance(getZero());
            dfpNewInstance.nans = (byte) 3;
            dotrap(1, GREATER_THAN_TRAP, dfp, dfpNewInstance);
            return false;
        }
        if (!isNaN() && !dfp.isNaN()) {
            return compare(this, dfp) > 0;
        }
        this.field.setIEEEFlagsBits(1);
        dotrap(1, GREATER_THAN_TRAP, dfp, newInstance(getZero()));
        return false;
    }

    public boolean negativeOrNull() {
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        if (this.sign >= 0) {
            int[] iArr = this.mant;
            if (iArr[iArr.length - 1] != 0 || isInfinite()) {
                return false;
            }
        }
        return true;
    }

    public boolean strictlyNegative() {
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        if (this.sign >= 0) {
            return false;
        }
        int[] iArr = this.mant;
        return iArr[iArr.length - 1] != 0 || isInfinite();
    }

    public boolean positiveOrNull() {
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        if (this.sign <= 0) {
            int[] iArr = this.mant;
            if (iArr[iArr.length - 1] != 0 || isInfinite()) {
                return false;
            }
        }
        return true;
    }

    public boolean strictlyPositive() {
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        if (this.sign <= 0) {
            return false;
        }
        int[] iArr = this.mant;
        return iArr[iArr.length - 1] != 0 || isInfinite();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp abs() {
        Dfp dfpNewInstance = newInstance(this);
        dfpNewInstance.sign = (byte) 1;
        return dfpNewInstance;
    }

    public boolean isZero() {
        if (!isNaN()) {
            int[] iArr = this.mant;
            return iArr[iArr.length - 1] == 0 && !isInfinite();
        }
        this.field.setIEEEFlagsBits(1);
        dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
        return false;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Dfp)) {
            return false;
        }
        Dfp dfp = (Dfp) obj;
        return !isNaN() && !dfp.isNaN() && this.field.getRadixDigits() == dfp.field.getRadixDigits() && compare(this, dfp) == 0;
    }

    public int hashCode() {
        return (isZero() ? 0 : this.sign << 8) + 17 + (this.nans << 16) + this.exp + Arrays.hashCode(this.mant);
    }

    public boolean unequal(Dfp dfp) {
        if (isNaN() || dfp.isNaN() || this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            return false;
        }
        return greaterThan(dfp) || lessThan(dfp);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp rint() {
        return trunc(DfpField.RoundingMode.ROUND_HALF_EVEN);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp floor() {
        return trunc(DfpField.RoundingMode.ROUND_FLOOR);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp ceil() {
        return trunc(DfpField.RoundingMode.ROUND_CEIL);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp remainder(Dfp dfp) {
        Dfp dfpSubtract = subtract(divide(dfp).rint().multiply(dfp));
        if (dfpSubtract.mant[this.mant.length - 1] == 0) {
            dfpSubtract.sign = this.sign;
        }
        return dfpSubtract;
    }

    protected Dfp trunc(DfpField.RoundingMode roundingMode) {
        int i;
        if (isNaN()) {
            return newInstance(this);
        }
        if (this.nans == 1) {
            return newInstance(this);
        }
        int[] iArr = this.mant;
        if (iArr[iArr.length - 1] == 0) {
            return newInstance(this);
        }
        int i2 = this.exp;
        if (i2 < 0) {
            this.field.setIEEEFlagsBits(16);
            return dotrap(16, TRUNC_TRAP, this, newInstance(getZero()));
        }
        if (i2 >= iArr.length) {
            return newInstance(this);
        }
        Dfp dfpNewInstance = newInstance(this);
        boolean z = false;
        for (int i3 = 0; i3 < this.mant.length - dfpNewInstance.exp; i3++) {
            int[] iArr2 = dfpNewInstance.mant;
            z |= iArr2[i3] != 0;
            iArr2[i3] = 0;
        }
        if (!z) {
            return dfpNewInstance;
        }
        int i4 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode[roundingMode.ordinal()];
        if (i4 != 1) {
            if (i4 == 2) {
                if (dfpNewInstance.sign == 1) {
                    dfpNewInstance = dfpNewInstance.add(getOne());
                }
            } else {
                Dfp dfpNewInstance2 = newInstance("0.5");
                Dfp dfpSubtract = subtract(dfpNewInstance);
                dfpSubtract.sign = (byte) 1;
                if (dfpSubtract.greaterThan(dfpNewInstance2)) {
                    dfpSubtract = newInstance(getOne());
                    dfpSubtract.sign = this.sign;
                    dfpNewInstance = dfpNewInstance.add(dfpSubtract);
                }
                if (dfpSubtract.equals(dfpNewInstance2) && (i = dfpNewInstance.exp) > 0 && (dfpNewInstance.mant[this.mant.length - i] & 1) != 0) {
                    Dfp dfpNewInstance3 = newInstance(getOne());
                    dfpNewInstance3.sign = this.sign;
                    dfpNewInstance = dfpNewInstance.add(dfpNewInstance3);
                }
            }
        } else if (dfpNewInstance.sign == -1) {
            dfpNewInstance = dfpNewInstance.add(newInstance(-1));
        }
        this.field.setIEEEFlagsBits(16);
        return dotrap(16, TRUNC_TRAP, this, dfpNewInstance);
    }

    public int intValue() {
        Dfp dfpRint = rint();
        if (dfpRint.greaterThan(newInstance(Integer.MAX_VALUE))) {
            return Integer.MAX_VALUE;
        }
        if (dfpRint.lessThan(newInstance(Integer.MIN_VALUE))) {
            return Integer.MIN_VALUE;
        }
        int i = 0;
        for (int length = this.mant.length - 1; length >= this.mant.length - dfpRint.exp; length--) {
            i = (i * 10000) + dfpRint.mant[length];
        }
        return dfpRint.sign == -1 ? -i : i;
    }

    public Dfp power10K(int i) {
        Dfp dfpNewInstance = newInstance(getOne());
        dfpNewInstance.exp = i + 1;
        return dfpNewInstance;
    }

    public int intLog10() {
        int[] iArr = this.mant;
        return iArr[iArr.length + (-1)] > 1000 ? (this.exp * 4) - 1 : iArr[iArr.length + (-1)] > 100 ? (this.exp * 4) - 2 : iArr[iArr.length + (-1)] > 10 ? (this.exp * 4) - 3 : (this.exp * 4) - 4;
    }

    public Dfp power10(int i) {
        Dfp dfpNewInstance = newInstance(getOne());
        if (i >= 0) {
            dfpNewInstance.exp = (i / 4) + 1;
        } else {
            dfpNewInstance.exp = (i + 1) / 4;
        }
        int i2 = ((i % 4) + 4) % 4;
        if (i2 == 0) {
            return dfpNewInstance;
        }
        if (i2 == 1) {
            return dfpNewInstance.multiply(10);
        }
        if (i2 == 2) {
            return dfpNewInstance.multiply(100);
        }
        return dfpNewInstance.multiply(1000);
    }

    protected int complement(int i) {
        int i2 = 10000 - i;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int[] iArr = this.mant;
            if (i4 >= iArr.length) {
                break;
            }
            iArr[i4] = 9999 - iArr[i4];
            i4++;
        }
        int i5 = i2 / 10000;
        int i6 = i2 - (i5 * 10000);
        while (true) {
            int[] iArr2 = this.mant;
            if (i3 >= iArr2.length) {
                return i6;
            }
            int i7 = iArr2[i3] + i5;
            i5 = i7 / 10000;
            iArr2[i3] = i7 - (i5 * 10000);
            i3++;
        }
    }

    @Override // org.apache.commons.math3.FieldElement
    public Dfp add(Dfp dfp) {
        int iAlign;
        int iComplement;
        int[] iArr;
        if (this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp dfpNewInstance = newInstance(getZero());
            dfpNewInstance.nans = (byte) 3;
            return dotrap(1, ADD_TRAP, dfp, dfpNewInstance);
        }
        if (this.nans != 0 || dfp.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (dfp.isNaN()) {
                return dfp;
            }
            byte b = this.nans;
            if (b == 1 && dfp.nans == 0) {
                return this;
            }
            byte b2 = dfp.nans;
            if (b2 == 1 && b == 0) {
                return dfp;
            }
            if (b2 == 1 && b == 1 && this.sign == dfp.sign) {
                return dfp;
            }
            if (b2 == 1 && b == 1 && this.sign != dfp.sign) {
                this.field.setIEEEFlagsBits(1);
                Dfp dfpNewInstance2 = newInstance(getZero());
                dfpNewInstance2.nans = (byte) 3;
                return dotrap(1, ADD_TRAP, dfp, dfpNewInstance2);
            }
        }
        Dfp dfpNewInstance3 = newInstance(this);
        Dfp dfpNewInstance4 = newInstance(dfp);
        Dfp dfpNewInstance5 = newInstance(getZero());
        byte b3 = dfpNewInstance3.sign;
        byte b4 = dfpNewInstance4.sign;
        dfpNewInstance3.sign = (byte) 1;
        dfpNewInstance4.sign = (byte) 1;
        byte b5 = compare(dfpNewInstance3, dfpNewInstance4) > 0 ? b3 : b4;
        int[] iArr2 = dfpNewInstance4.mant;
        int[] iArr3 = this.mant;
        if (iArr2[iArr3.length - 1] == 0) {
            dfpNewInstance4.exp = dfpNewInstance3.exp;
        }
        if (dfpNewInstance3.mant[iArr3.length - 1] == 0) {
            dfpNewInstance3.exp = dfpNewInstance4.exp;
        }
        int i = dfpNewInstance3.exp;
        int i2 = dfpNewInstance4.exp;
        if (i < i2) {
            iComplement = dfpNewInstance3.align(i2);
            iAlign = 0;
        } else {
            iAlign = dfpNewInstance4.align(i);
            iComplement = 0;
        }
        if (b3 != b4) {
            if (b3 == b5) {
                iAlign = dfpNewInstance4.complement(iAlign);
            } else {
                iComplement = dfpNewInstance3.complement(iComplement);
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.mant.length; i4++) {
            int i5 = dfpNewInstance3.mant[i4] + dfpNewInstance4.mant[i4] + i3;
            i3 = i5 / 10000;
            dfpNewInstance5.mant[i4] = i5 - (i3 * 10000);
        }
        dfpNewInstance5.exp = dfpNewInstance3.exp;
        dfpNewInstance5.sign = b5;
        if (i3 != 0 && b3 == b4) {
            int i6 = dfpNewInstance5.mant[0];
            dfpNewInstance5.shiftRight();
            dfpNewInstance5.mant[this.mant.length - 1] = i3;
            int iRound = dfpNewInstance5.round(i6);
            if (iRound != 0) {
                dfpNewInstance5 = dotrap(iRound, ADD_TRAP, dfp, dfpNewInstance5);
            }
        }
        int i7 = 0;
        while (true) {
            iArr = this.mant;
            if (i7 >= iArr.length || dfpNewInstance5.mant[iArr.length - 1] != 0) {
                break;
            }
            dfpNewInstance5.shiftLeft();
            if (i7 == 0) {
                dfpNewInstance5.mant[0] = iComplement + iAlign;
                iComplement = 0;
                iAlign = 0;
            }
            i7++;
        }
        if (dfpNewInstance5.mant[iArr.length - 1] == 0) {
            dfpNewInstance5.exp = 0;
            if (b3 != b4) {
                dfpNewInstance5.sign = (byte) 1;
            }
        }
        int iRound2 = dfpNewInstance5.round(iComplement + iAlign);
        return iRound2 != 0 ? dotrap(iRound2, ADD_TRAP, dfp, dfpNewInstance5) : dfpNewInstance5;
    }

    @Override // org.apache.commons.math3.FieldElement
    public Dfp negate() {
        Dfp dfpNewInstance = newInstance(this);
        dfpNewInstance.sign = (byte) (-dfpNewInstance.sign);
        return dfpNewInstance;
    }

    @Override // org.apache.commons.math3.FieldElement
    public Dfp subtract(Dfp dfp) {
        return add(dfp.negate());
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected int round(int r8) {
        /*
            r7 = this;
            int[] r0 = org.apache.commons.math3.dfp.Dfp.AnonymousClass1.$SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode
            org.apache.commons.math3.dfp.DfpField r1 = r7.field
            org.apache.commons.math3.dfp.DfpField$RoundingMode r1 = r1.getRoundingMode()
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 0
            r2 = 5000(0x1388, float:7.006E-42)
            r3 = 1
            switch(r0) {
                case 2: goto L3e;
                case 3: goto L32;
                case 4: goto L63;
                case 5: goto L2f;
                case 6: goto L2c;
                case 7: goto L29;
                case 8: goto L1d;
                default: goto L15;
            }
        L15:
            byte r0 = r7.sign
            r2 = -1
            if (r0 != r2) goto L63
            if (r8 == 0) goto L63
            goto L44
        L1d:
            if (r8 > r2) goto L44
            if (r8 != r2) goto L63
            int[] r0 = r7.mant
            r0 = r0[r1]
            r0 = r0 & r3
            if (r0 != 0) goto L63
            goto L44
        L29:
            if (r8 <= r2) goto L63
            goto L44
        L2c:
            if (r8 < r2) goto L63
            goto L44
        L2f:
            if (r8 == 0) goto L63
            goto L44
        L32:
            if (r8 > r2) goto L44
            if (r8 != r2) goto L63
            int[] r0 = r7.mant
            r0 = r0[r1]
            r0 = r0 & r3
            if (r0 != r3) goto L63
            goto L44
        L3e:
            byte r0 = r7.sign
            if (r0 != r3) goto L63
            if (r8 == 0) goto L63
        L44:
            r0 = 0
            r2 = 1
        L46:
            int[] r4 = r7.mant
            int r5 = r4.length
            if (r0 >= r5) goto L58
            r5 = r4[r0]
            int r5 = r5 + r2
            int r2 = r5 / 10000
            int r6 = r2 * 10000
            int r5 = r5 - r6
            r4[r0] = r5
            int r0 = r0 + 1
            goto L46
        L58:
            if (r2 == 0) goto L63
            r7.shiftRight()
            int[] r0 = r7.mant
            int r4 = r0.length
            int r4 = r4 - r3
            r0[r4] = r2
        L63:
            int r0 = r7.exp
            r2 = -32767(0xffffffffffff8001, float:NaN)
            if (r0 >= r2) goto L71
            org.apache.commons.math3.dfp.DfpField r8 = r7.field
            r0 = 8
            r8.setIEEEFlagsBits(r0)
            return r0
        L71:
            r2 = 32768(0x8000, float:4.5918E-41)
            if (r0 <= r2) goto L7d
            org.apache.commons.math3.dfp.DfpField r8 = r7.field
            r0 = 4
            r8.setIEEEFlagsBits(r0)
            return r0
        L7d:
            if (r8 == 0) goto L87
            org.apache.commons.math3.dfp.DfpField r8 = r7.field
            r0 = 16
            r8.setIEEEFlagsBits(r0)
            return r0
        L87:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.Dfp.round(int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00aa, code lost:

        if (r12.mant[r11.mant.length - 1] == 0) goto L43;
     */
    @Override // org.apache.commons.math3.FieldElement
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.commons.math3.dfp.Dfp multiply(org.apache.commons.math3.dfp.Dfp r12) {
        /*
            Method dump skipped, instructions count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.Dfp.multiply(org.apache.commons.math3.dfp.Dfp):org.apache.commons.math3.dfp.Dfp");
    }

    @Override // org.apache.commons.math3.FieldElement
    public Dfp multiply(int i) {
        if (i >= 0 && i < 10000) {
            return multiplyFast(i);
        }
        return multiply(newInstance(i));
    }

    private Dfp multiplyFast(int i) {
        int i2;
        Dfp dfpNewInstance = newInstance(this);
        if (this.nans != 0) {
            if (isNaN()) {
                return this;
            }
            byte b = this.nans;
            if (b == 1 && i != 0) {
                return newInstance(this);
            }
            if (b == 1 && i == 0) {
                this.field.setIEEEFlagsBits(1);
                Dfp dfpNewInstance2 = newInstance(getZero());
                dfpNewInstance2.nans = (byte) 3;
                return dotrap(1, MULTIPLY_TRAP, newInstance(getZero()), dfpNewInstance2);
            }
        }
        if (i < 0 || i >= 10000) {
            this.field.setIEEEFlagsBits(1);
            Dfp dfpNewInstance3 = newInstance(getZero());
            dfpNewInstance3.nans = (byte) 3;
            return dotrap(1, MULTIPLY_TRAP, dfpNewInstance3, dfpNewInstance3);
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int[] iArr = this.mant;
            if (i3 >= iArr.length) {
                break;
            }
            int i5 = (iArr[i3] * i) + i4;
            i4 = i5 / 10000;
            dfpNewInstance.mant[i3] = i5 - (i4 * 10000);
            i3++;
        }
        if (i4 != 0) {
            i2 = dfpNewInstance.mant[0];
            dfpNewInstance.shiftRight();
            dfpNewInstance.mant[this.mant.length - 1] = i4;
        } else {
            i2 = 0;
        }
        if (dfpNewInstance.mant[this.mant.length - 1] == 0) {
            dfpNewInstance.exp = 0;
        }
        int iRound = dfpNewInstance.round(i2);
        return iRound != 0 ? dotrap(iRound, MULTIPLY_TRAP, dfpNewInstance, dfpNewInstance) : dfpNewInstance;
    }

    @Override // org.apache.commons.math3.FieldElement
    public Dfp divide(Dfp dfp) {
        int[] iArr;
        int[] iArr2;
        int i;
        int iRound;
        int i2;
        int[] iArr3;
        int i3 = 1;
        if (this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp dfpNewInstance = newInstance(getZero());
            dfpNewInstance.nans = (byte) 3;
            return dotrap(1, DIVIDE_TRAP, dfp, dfpNewInstance);
        }
        Dfp dfpNewInstance2 = newInstance(getZero());
        if (this.nans != 0 || dfp.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (dfp.isNaN()) {
                return dfp;
            }
            byte b = this.nans;
            if (b == 1 && dfp.nans == 0) {
                Dfp dfpNewInstance3 = newInstance(this);
                dfpNewInstance3.sign = (byte) (this.sign * dfp.sign);
                return dfpNewInstance3;
            }
            byte b2 = dfp.nans;
            if (b2 == 1 && b == 0) {
                Dfp dfpNewInstance4 = newInstance(getZero());
                dfpNewInstance4.sign = (byte) (this.sign * dfp.sign);
                return dfpNewInstance4;
            }
            if (b2 == 1 && b == 1) {
                this.field.setIEEEFlagsBits(1);
                Dfp dfpNewInstance5 = newInstance(getZero());
                dfpNewInstance5.nans = (byte) 3;
                return dotrap(1, DIVIDE_TRAP, dfp, dfpNewInstance5);
            }
        }
        int[] iArr4 = dfp.mant;
        int[] iArr5 = this.mant;
        int i4 = 2;
        if (iArr4[iArr5.length - 1] == 0) {
            this.field.setIEEEFlagsBits(2);
            Dfp dfpNewInstance6 = newInstance(getZero());
            dfpNewInstance6.sign = (byte) (this.sign * dfp.sign);
            dfpNewInstance6.nans = (byte) 1;
            return dotrap(2, DIVIDE_TRAP, dfp, dfpNewInstance6);
        }
        int[] iArr6 = new int[iArr5.length + 1];
        int[] iArr7 = new int[iArr5.length + 2];
        int[] iArr8 = new int[iArr5.length + 1];
        iArr6[iArr5.length] = 0;
        iArr7[iArr5.length] = 0;
        iArr7[iArr5.length + 1] = 0;
        iArr8[iArr5.length] = 0;
        int i5 = 0;
        while (true) {
            iArr = this.mant;
            if (i5 >= iArr.length) {
                break;
            }
            iArr6[i5] = iArr[i5];
            iArr7[i5] = 0;
            iArr8[i5] = 0;
            i5++;
        }
        int length = iArr.length + 1;
        int i6 = 0;
        int i7 = 0;
        while (length >= 0) {
            int[] iArr9 = this.mant;
            int i8 = (iArr6[iArr9.length] * 10000) + iArr6[iArr9.length - i3];
            int[] iArr10 = dfp.mant;
            int i9 = i8 / (iArr10[iArr9.length - i3] + i3);
            int i10 = (i8 + i3) / iArr10[iArr9.length - i3];
            boolean z = false;
            while (!z) {
                i7 = (i9 + i10) / i4;
                int i11 = 0;
                int i12 = 0;
                while (true) {
                    int[] iArr11 = this.mant;
                    i2 = i9;
                    if (i11 >= iArr11.length + i3) {
                        break;
                    }
                    int i13 = ((i11 < iArr11.length ? dfp.mant[i11] : 0) * i7) + i12;
                    int i14 = i13 / 10000;
                    iArr8[i11] = i13 - (i14 * 10000);
                    i11++;
                    i12 = i14;
                    i9 = i2;
                    i3 = 1;
                }
                int i15 = 0;
                int i16 = 1;
                while (true) {
                    iArr3 = this.mant;
                    if (i15 >= iArr3.length + 1) {
                        break;
                    }
                    int i17 = (9999 - iArr8[i15]) + iArr6[i15] + i16;
                    i16 = i17 / 10000;
                    iArr8[i15] = i17 - (i16 * 10000);
                    i15++;
                }
                if (i16 == 0) {
                    i10 = i7 - 1;
                    i9 = i2;
                    i3 = 1;
                    i4 = 2;
                } else {
                    boolean z2 = z;
                    int i18 = ((iArr8[iArr3.length] * 10000) + iArr8[iArr3.length - 1]) / (dfp.mant[iArr3.length - 1] + 1);
                    i4 = 2;
                    if (i18 >= 2) {
                        i9 = i7 + i18;
                        z = z2;
                    } else {
                        boolean z3 = false;
                        for (int length2 = iArr3.length - 1; length2 >= 0; length2--) {
                            int i19 = dfp.mant[length2];
                            int i20 = iArr8[length2];
                            if (i19 > i20) {
                                z3 = true;
                            }
                            if (i19 < i20) {
                                break;
                            }
                        }
                        z = iArr8[this.mant.length] != 0 ? false : z3;
                        i9 = !z ? i7 + 1 : i2;
                    }
                    i3 = 1;
                }
            }
            iArr7[length] = i7;
            if (i7 != 0 || i6 != 0) {
                i6++;
            }
            if ((this.field.getRoundingMode() == DfpField.RoundingMode.ROUND_DOWN && i6 == this.mant.length) || i6 > this.mant.length) {
                break;
            }
            iArr6[0] = 0;
            int i21 = 0;
            while (i21 < this.mant.length) {
                int i22 = i21 + 1;
                iArr6[i22] = iArr8[i21];
                i21 = i22;
            }
            length--;
            i3 = 1;
        }
        int[] iArr12 = this.mant;
        int length3 = iArr12.length;
        int length4 = iArr12.length + 1;
        while (true) {
            if (length4 < 0) {
                break;
            }
            if (iArr7[length4] != 0) {
                length3 = length4;
                break;
            }
            length4--;
        }
        int i23 = 0;
        while (true) {
            iArr2 = this.mant;
            if (i23 >= iArr2.length) {
                break;
            }
            dfpNewInstance2.mant[(iArr2.length - i23) - 1] = iArr7[length3 - i23];
            i23++;
        }
        dfpNewInstance2.exp = ((this.exp - dfp.exp) + length3) - iArr2.length;
        dfpNewInstance2.sign = (byte) (this.sign == dfp.sign ? 1 : -1);
        if (dfpNewInstance2.mant[iArr2.length - 1] == 0) {
            i = 0;
            dfpNewInstance2.exp = 0;
        } else {
            i = 0;
        }
        if (length3 > iArr2.length - 1) {
            iRound = dfpNewInstance2.round(iArr7[length3 - iArr2.length]);
        } else {
            iRound = dfpNewInstance2.round(i);
        }
        return iRound != 0 ? dotrap(iRound, DIVIDE_TRAP, dfp, dfpNewInstance2) : dfpNewInstance2;
    }

    public Dfp divide(int i) {
        if (this.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (this.nans == 1) {
                return newInstance(this);
            }
        }
        if (i == 0) {
            this.field.setIEEEFlagsBits(2);
            Dfp dfpNewInstance = newInstance(getZero());
            dfpNewInstance.sign = this.sign;
            dfpNewInstance.nans = (byte) 1;
            return dotrap(2, DIVIDE_TRAP, getZero(), dfpNewInstance);
        }
        if (i < 0 || i >= 10000) {
            this.field.setIEEEFlagsBits(1);
            Dfp dfpNewInstance2 = newInstance(getZero());
            dfpNewInstance2.nans = (byte) 3;
            return dotrap(1, DIVIDE_TRAP, dfpNewInstance2, dfpNewInstance2);
        }
        Dfp dfpNewInstance3 = newInstance(this);
        int i2 = 0;
        for (int length = this.mant.length - 1; length >= 0; length--) {
            int[] iArr = dfpNewInstance3.mant;
            int i3 = (i2 * 10000) + iArr[length];
            int i4 = i3 / i;
            i2 = i3 - (i4 * i);
            iArr[length] = i4;
        }
        if (dfpNewInstance3.mant[this.mant.length - 1] == 0) {
            dfpNewInstance3.shiftLeft();
            int i5 = i2 * 10000;
            int i6 = i5 / i;
            i2 = i5 - (i6 * i);
            dfpNewInstance3.mant[0] = i6;
        }
        int iRound = dfpNewInstance3.round((i2 * 10000) / i);
        return iRound != 0 ? dotrap(iRound, DIVIDE_TRAP, dfpNewInstance3, dfpNewInstance3) : dfpNewInstance3;
    }

    @Override // org.apache.commons.math3.RealFieldElement, org.apache.commons.math3.FieldElement
    public Dfp reciprocal() {
        return this.field.getOne().divide(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp sqrt() {
        byte b = this.nans;
        if (b == 0) {
            int[] iArr = this.mant;
            if (iArr[iArr.length - 1] == 0) {
                return newInstance(this);
            }
        }
        if (b != 0) {
            if (b == 1 && this.sign == 1) {
                return newInstance(this);
            }
            if (b == 3) {
                return newInstance(this);
            }
            if (b == 2) {
                this.field.setIEEEFlagsBits(1);
                return dotrap(1, SQRT_TRAP, null, newInstance(this));
            }
        }
        if (this.sign == -1) {
            this.field.setIEEEFlagsBits(1);
            Dfp dfpNewInstance = newInstance(this);
            dfpNewInstance.nans = (byte) 3;
            return dotrap(1, SQRT_TRAP, null, dfpNewInstance);
        }
        Dfp dfpNewInstance2 = newInstance(this);
        int i = dfpNewInstance2.exp;
        if (i < -1 || i > 1) {
            dfpNewInstance2.exp = this.exp / 2;
        }
        int[] iArr2 = dfpNewInstance2.mant;
        int[] iArr3 = this.mant;
        int i2 = iArr2[iArr3.length - 1] / 2000;
        if (i2 == 0) {
            iArr2[iArr3.length - 1] = (iArr2[iArr3.length - 1] / 2) + 1;
        } else if (i2 == 2) {
            iArr2[iArr3.length - 1] = 1500;
        } else if (i2 == 3) {
            iArr2[iArr3.length - 1] = 2200;
        } else {
            iArr2[iArr3.length - 1] = 3000;
        }
        newInstance(dfpNewInstance2);
        Dfp zero = getZero();
        getZero();
        while (dfpNewInstance2.unequal(zero)) {
            Dfp dfpNewInstance3 = newInstance(dfpNewInstance2);
            dfpNewInstance3.sign = (byte) -1;
            Dfp dfpDivide = dfpNewInstance3.add(divide(dfpNewInstance2)).divide(2);
            Dfp dfpAdd = dfpNewInstance2.add(dfpDivide);
            if (dfpAdd.equals(zero) || dfpDivide.mant[this.mant.length - 1] == 0) {
                return dfpAdd;
            }
            zero = dfpNewInstance2;
            dfpNewInstance2 = dfpAdd;
        }
        return dfpNewInstance2;
    }

    public String toString() {
        byte b = this.nans;
        if (b != 0) {
            return b == 1 ? this.sign < 0 ? NEG_INFINITY_STRING : POS_INFINITY_STRING : NAN_STRING;
        }
        int i = this.exp;
        if (i > this.mant.length || i < -1) {
            return dfp2sci();
        }
        return dfp2string();
    }

    protected String dfp2sci() {
        int i;
        int[] iArr = this.mant;
        int length = iArr.length * 4;
        char[] cArr = new char[length];
        char[] cArr2 = new char[(iArr.length * 4) + 20];
        int i2 = 0;
        for (int length2 = iArr.length - 1; length2 >= 0; length2--) {
            int i3 = this.mant[length2];
            cArr[i2] = (char) ((i3 / 1000) + 48);
            cArr[i2 + 1] = (char) (((i3 / 100) % 10) + 48);
            int i4 = i2 + 3;
            cArr[i2 + 2] = (char) (((i3 / 10) % 10) + 48);
            i2 += 4;
            cArr[i4] = (char) ((i3 % 10) + 48);
        }
        int i5 = 0;
        while (i5 < length && cArr[i5] == '0') {
            i5++;
        }
        if (this.sign == -1) {
            cArr2[0] = '-';
            i = 1;
        } else {
            i = 0;
        }
        if (i5 != length) {
            int i6 = i + 1;
            cArr2[i] = cArr[i5];
            int i7 = i + 2;
            cArr2[i6] = '.';
            for (int i8 = i5 + 1; i8 < length; i8++) {
                cArr2[i7] = cArr[i8];
                i7++;
            }
            int i9 = i7 + 1;
            cArr2[i7] = 'e';
            int i10 = ((this.exp * 4) - i5) - 1;
            int i11 = i10 < 0 ? -i10 : i10;
            int i12 = 1000000000;
            while (i12 > i11) {
                i12 /= 10;
            }
            if (i10 < 0) {
                cArr2[i9] = '-';
                i9 = i7 + 2;
            }
            while (i12 > 0) {
                cArr2[i9] = (char) ((i11 / i12) + 48);
                i11 %= i12;
                i12 /= 10;
                i9++;
            }
            return new String(cArr2, 0, i9);
        }
        cArr2[i] = '0';
        cArr2[i + 1] = '.';
        cArr2[i + 2] = '0';
        cArr2[i + 3] = 'e';
        cArr2[i + 4] = '0';
        return new String(cArr2, 0, 5);
    }

    protected String dfp2string() {
        int i;
        boolean z;
        char c;
        char[] cArr = new char[(this.mant.length * 4) + 20];
        int i2 = this.exp;
        cArr[0] = ' ';
        int i3 = 1;
        if (i2 <= 0) {
            cArr[1] = '0';
            cArr[2] = '.';
            i = 3;
            z = true;
        } else {
            i = 1;
            z = false;
        }
        while (i2 < 0) {
            cArr[i] = '0';
            cArr[i + 1] = '0';
            int i4 = i + 3;
            cArr[i + 2] = '0';
            i += 4;
            cArr[i4] = '0';
            i2++;
        }
        for (int length = this.mant.length - 1; length >= 0; length--) {
            int i5 = this.mant[length];
            cArr[i] = (char) ((i5 / 1000) + 48);
            cArr[i + 1] = (char) (((i5 / 100) % 10) + 48);
            cArr[i + 2] = (char) (((i5 / 10) % 10) + 48);
            int i6 = i + 4;
            cArr[i + 3] = (char) ((i5 % 10) + 48);
            i2--;
            if (i2 == 0) {
                i += 5;
                cArr[i6] = '.';
                z = true;
            } else {
                i = i6;
            }
        }
        while (i2 > 0) {
            cArr[i] = '0';
            cArr[i + 1] = '0';
            int i7 = i + 3;
            cArr[i + 2] = '0';
            i += 4;
            cArr[i7] = '0';
            i2--;
        }
        if (!z) {
            cArr[i] = '.';
            i++;
        }
        while (true) {
            c = cArr[i3];
            if (c != '0') {
                break;
            }
            i3++;
        }
        if (c == '.') {
            i3--;
        }
        while (cArr[i - 1] == '0') {
            i--;
        }
        if (this.sign < 0) {
            i3--;
            cArr[i3] = '-';
        }
        return new String(cArr, i3, i - i3);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.commons.math3.dfp.Dfp dotrap(int r10, java.lang.String r11, org.apache.commons.math3.dfp.Dfp r12, org.apache.commons.math3.dfp.Dfp r13) {
        /*
            r9 = this;
            r0 = 3
            r1 = 1
            if (r10 == r1) goto La5
            r2 = 2
            if (r10 == r2) goto L4a
            r0 = 4
            if (r10 == r0) goto L33
            r0 = 8
            if (r10 == r0) goto L11
            r7 = r13
            goto Lb4
        L11:
            int r0 = r13.exp
            int[] r1 = r9.mant
            int r1 = r1.length
            int r0 = r0 + r1
            r1 = -32767(0xffffffffffff8001, float:NaN)
            if (r0 >= r1) goto L28
            org.apache.commons.math3.dfp.Dfp r0 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r0 = r9.newInstance(r0)
            byte r1 = r13.sign
            r0.sign = r1
            goto L2c
        L28:
            org.apache.commons.math3.dfp.Dfp r0 = r9.newInstance(r13)
        L2c:
            int r1 = r13.exp
            int r1 = r1 + 32760
            r13.exp = r1
            goto L47
        L33:
            int r0 = r13.exp
            int r0 = r0 + (-32760)
            r13.exp = r0
            org.apache.commons.math3.dfp.Dfp r0 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r0 = r9.newInstance(r0)
            byte r2 = r13.sign
            r0.sign = r2
            r0.nans = r1
        L47:
            r7 = r0
            goto Lb4
        L4a:
            byte r3 = r9.nans
            if (r3 != 0) goto L6a
            int[] r3 = r9.mant
            int r4 = r3.length
            int r4 = r4 - r1
            r3 = r3[r4]
            if (r3 == 0) goto L6a
            org.apache.commons.math3.dfp.Dfp r3 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r3 = r9.newInstance(r3)
            byte r4 = r9.sign
            byte r5 = r12.sign
            int r4 = r4 * r5
            byte r4 = (byte) r4
            r3.sign = r4
            r3.nans = r1
            goto L6b
        L6a:
            r3 = r13
        L6b:
            byte r4 = r9.nans
            if (r4 != 0) goto L81
            int[] r4 = r9.mant
            int r5 = r4.length
            int r5 = r5 - r1
            r4 = r4[r5]
            if (r4 != 0) goto L81
            org.apache.commons.math3.dfp.Dfp r3 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r3 = r9.newInstance(r3)
            r3.nans = r0
        L81:
            byte r4 = r9.nans
            if (r4 == r1) goto L87
            if (r4 != r0) goto L91
        L87:
            org.apache.commons.math3.dfp.Dfp r3 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r3 = r9.newInstance(r3)
            r3.nans = r0
        L91:
            byte r4 = r9.nans
            if (r4 == r1) goto L9a
            if (r4 != r2) goto L98
            goto L9a
        L98:
            r7 = r3
            goto Lb4
        L9a:
            org.apache.commons.math3.dfp.Dfp r1 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r1 = r9.newInstance(r1)
            r1.nans = r0
            goto Lb3
        La5:
            org.apache.commons.math3.dfp.Dfp r1 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r1 = r9.newInstance(r1)
            byte r2 = r13.sign
            r1.sign = r2
            r1.nans = r0
        Lb3:
            r7 = r1
        Lb4:
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
            r8 = r13
            org.apache.commons.math3.dfp.Dfp r10 = r3.trap(r4, r5, r6, r7, r8)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.Dfp.dotrap(int, java.lang.String, org.apache.commons.math3.dfp.Dfp, org.apache.commons.math3.dfp.Dfp):org.apache.commons.math3.dfp.Dfp");
    }

    public Dfp nextAfter(Dfp dfp) {
        Dfp dfpSubtract;
        if (this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp dfpNewInstance = newInstance(getZero());
            dfpNewInstance.nans = (byte) 3;
            return dotrap(1, NEXT_AFTER_TRAP, dfp, dfpNewInstance);
        }
        boolean zLessThan = lessThan(dfp);
        if (compare(this, dfp) == 0) {
            return newInstance(dfp);
        }
        if (lessThan(getZero())) {
            zLessThan = !zLessThan;
        }
        if (zLessThan) {
            Dfp dfpNewInstance2 = newInstance(getOne());
            dfpNewInstance2.exp = (this.exp - this.mant.length) + 1;
            dfpNewInstance2.sign = this.sign;
            if (equals(getZero())) {
                dfpNewInstance2.exp = (-32767) - this.mant.length;
            }
            dfpSubtract = add(dfpNewInstance2);
        } else {
            Dfp dfpNewInstance3 = newInstance(getOne());
            dfpNewInstance3.exp = this.exp;
            dfpNewInstance3.sign = this.sign;
            if (equals(dfpNewInstance3)) {
                dfpNewInstance3.exp = this.exp - this.mant.length;
            } else {
                dfpNewInstance3.exp = (this.exp - this.mant.length) + 1;
            }
            if (equals(getZero())) {
                dfpNewInstance3.exp = (-32767) - this.mant.length;
            }
            dfpSubtract = subtract(dfpNewInstance3);
        }
        if (dfpSubtract.classify() == 1 && classify() != 1) {
            this.field.setIEEEFlagsBits(16);
            dfpSubtract = dotrap(16, NEXT_AFTER_TRAP, dfp, dfpSubtract);
        }
        if (!dfpSubtract.equals(getZero()) || equals(getZero())) {
            return dfpSubtract;
        }
        this.field.setIEEEFlagsBits(16);
        return dotrap(16, NEXT_AFTER_TRAP, dfp, dfpSubtract);
    }

    public double toDouble() throws NumberFormatException {
        boolean z;
        Dfp dfpNegate;
        if (isInfinite()) {
            return lessThan(getZero()) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        if (isNaN()) {
            return Double.NaN;
        }
        int iCompare = compare(this, getZero());
        if (iCompare == 0) {
            return this.sign < 0 ? -0.0d : 0.0d;
        }
        if (iCompare < 0) {
            dfpNegate = negate();
            z = true;
        } else {
            z = false;
            dfpNegate = this;
        }
        int iIntLog10 = (int) (dfpNegate.intLog10() * 3.32d);
        if (iIntLog10 < 0) {
            iIntLog10--;
        }
        Dfp dfpPow = DfpMath.pow(getTwo(), iIntLog10);
        while (true) {
            if (!dfpPow.lessThan(dfpNegate) && !dfpPow.equals(dfpNegate)) {
                break;
            }
            dfpPow = dfpPow.multiply(2);
            iIntLog10++;
        }
        int i = iIntLog10 - 1;
        Dfp dfpDivide = dfpNegate.divide(DfpMath.pow(getTwo(), i));
        if (i > -1023) {
            dfpDivide = dfpDivide.subtract(getOne());
        }
        if (i < -1074) {
            return 0.0d;
        }
        if (i > 1023) {
            return z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        String string = dfpDivide.multiply(newInstance(4503599627370496L)).rint().toString();
        long j = Long.parseLong(string.substring(0, string.length() - 1));
        if (j == 4503599627370496L) {
            j = 0;
        } else {
            iIntLog10 = i;
        }
        if (iIntLog10 <= -1023) {
            iIntLog10--;
        }
        while (iIntLog10 < -1023) {
            iIntLog10++;
            j >>>= 1;
        }
        double dLongBitsToDouble = Double.longBitsToDouble(((iIntLog10 + 1023) << 52) | j);
        return z ? -dLongBitsToDouble : dLongBitsToDouble;
    }

    public double[] toSplitDouble() {
        double dLongBitsToDouble = Double.longBitsToDouble(Double.doubleToLongBits(toDouble()) & (-1073741824));
        return new double[]{dLongBitsToDouble, subtract(newInstance(dLongBitsToDouble)).toDouble()};
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public double getReal() {
        return toDouble();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp add(double d) {
        return add(newInstance(d));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp subtract(double d) {
        return subtract(newInstance(d));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp multiply(double d) {
        return multiply(newInstance(d));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp divide(double d) {
        return divide(newInstance(d));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp remainder(double d) {
        return remainder(newInstance(d));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public long round() {
        return FastMath.round(toDouble());
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp signum() {
        if (isNaN() || isZero()) {
            return this;
        }
        return newInstance(this.sign > 0 ? 1 : -1);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp copySign(Dfp dfp) {
        byte b = this.sign;
        return ((b < 0 || dfp.sign < 0) && (b >= 0 || dfp.sign >= 0)) ? negate() : this;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp copySign(double d) {
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        byte b = this.sign;
        return ((b < 0 || jDoubleToLongBits < 0) && (b >= 0 || jDoubleToLongBits >= 0)) ? negate() : this;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp scalb(int i) {
        return multiply(DfpMath.pow(getTwo(), i));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp hypot(Dfp dfp) {
        return multiply(this).add(dfp.multiply(dfp)).sqrt();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp cbrt() {
        return rootN(3);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp rootN(int i) {
        return this.sign >= 0 ? DfpMath.pow(this, getOne().divide(i)) : DfpMath.pow(negate(), getOne().divide(i)).negate();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp pow(double d) {
        return DfpMath.pow(this, newInstance(d));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp pow(int i) {
        return DfpMath.pow(this, i);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp pow(Dfp dfp) {
        return DfpMath.pow(this, dfp);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp exp() {
        return DfpMath.exp(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp expm1() {
        return DfpMath.exp(this).subtract(getOne());
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp log() {
        return DfpMath.log(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp log1p() {
        return DfpMath.log(add(getOne()));
    }

    @Deprecated
    public int log10() {
        return intLog10();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp cos() {
        return DfpMath.cos(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp sin() {
        return DfpMath.sin(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp tan() {
        return DfpMath.tan(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp acos() {
        return DfpMath.acos(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp asin() {
        return DfpMath.asin(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp atan() {
        return DfpMath.atan(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp atan2(Dfp dfp) throws DimensionMismatchException {
        Dfp dfpSqrt = dfp.multiply(dfp).add(multiply(this)).sqrt();
        if (dfp.sign >= 0) {
            return getTwo().multiply(divide(dfpSqrt.add(dfp)).atan());
        }
        Dfp dfpMultiply = getTwo().multiply(divide(dfpSqrt.subtract(dfp)).atan());
        return newInstance(dfpMultiply.sign <= 0 ? -3.141592653589793d : 3.141592653589793d).subtract(dfpMultiply);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp cosh() {
        return DfpMath.exp(this).add(DfpMath.exp(negate())).divide(2);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp sinh() {
        return DfpMath.exp(this).subtract(DfpMath.exp(negate())).divide(2);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp tanh() {
        Dfp dfpExp = DfpMath.exp(this);
        Dfp dfpExp2 = DfpMath.exp(negate());
        return dfpExp.subtract(dfpExp2).divide(dfpExp.add(dfpExp2));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp acosh() {
        return multiply(this).subtract(getOne()).sqrt().add(this).log();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp asinh() {
        return multiply(this).add(getOne()).sqrt().add(this).log();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp atanh() {
        return getOne().add(this).divide(getOne().subtract(this)).log().divide(2);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(Dfp[] dfpArr, Dfp[] dfpArr2) throws DimensionMismatchException {
        if (dfpArr.length != dfpArr2.length) {
            throw new DimensionMismatchException(dfpArr.length, dfpArr2.length);
        }
        Dfp zero = getZero();
        for (int i = 0; i < dfpArr.length; i++) {
            zero = zero.add(dfpArr[i].multiply(dfpArr2[i]));
        }
        return zero;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(double[] dArr, Dfp[] dfpArr) throws DimensionMismatchException {
        if (dArr.length != dfpArr.length) {
            throw new DimensionMismatchException(dArr.length, dfpArr.length);
        }
        Dfp zero = getZero();
        for (int i = 0; i < dArr.length; i++) {
            zero = zero.add(dfpArr[i].multiply(dArr[i]));
        }
        return zero;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(Dfp dfp, Dfp dfp2, Dfp dfp3, Dfp dfp4) {
        return dfp.multiply(dfp2).add(dfp3.multiply(dfp4));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(double d, Dfp dfp, double d2, Dfp dfp2) {
        return dfp.multiply(d).add(dfp2.multiply(d2));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(Dfp dfp, Dfp dfp2, Dfp dfp3, Dfp dfp4, Dfp dfp5, Dfp dfp6) {
        return dfp.multiply(dfp2).add(dfp3.multiply(dfp4)).add(dfp5.multiply(dfp6));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(double d, Dfp dfp, double d2, Dfp dfp2, double d3, Dfp dfp3) {
        return dfp.multiply(d).add(dfp2.multiply(d2)).add(dfp3.multiply(d3));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(Dfp dfp, Dfp dfp2, Dfp dfp3, Dfp dfp4, Dfp dfp5, Dfp dfp6, Dfp dfp7, Dfp dfp8) {
        return dfp.multiply(dfp2).add(dfp3.multiply(dfp4)).add(dfp5.multiply(dfp6)).add(dfp7.multiply(dfp8));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(double d, Dfp dfp, double d2, Dfp dfp2, double d3, Dfp dfp3, double d4, Dfp dfp4) {
        return dfp.multiply(d).add(dfp2.multiply(d2)).add(dfp3.multiply(d3)).add(dfp4.multiply(d4));
    }
}
