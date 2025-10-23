package org.apache.commons.math.dfp;

import com.shimmerresearch.verisense.communication.VerisenseMessage;

import java.util.Arrays;

import org.apache.commons.math.Field;
import org.apache.commons.math.FieldElement;
import org.apache.commons.math.dfp.DfpField;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/dfp/Dfp.class */
public class Dfp implements FieldElement<Dfp> {
    public static final int RADIX = 10000;
    public static final int MIN_EXP = -32767;
    public static final int MAX_EXP = 32768;
    public static final int ERR_SCALE = 32760;
    public static final byte FINITE = 0;
    public static final byte INFINITE = 1;
    public static final byte SNAN = 2;
    public static final byte QNAN = 3;
    private static final String NAN_STRING = "NaN";
    private static final String POS_INFINITY_STRING = "Infinity";
    private static final String NEG_INFINITY_STRING = "-Infinity";
    private static final String ADD_TRAP = "add";
    private static final String MULTIPLY_TRAP = "multiply";
    private static final String DIVIDE_TRAP = "divide";
    private static final String SQRT_TRAP = "sqrt";
    private static final String ALIGN_TRAP = "align";
    private static final String TRUNC_TRAP = "trunc";
    private static final String NEXT_AFTER_TRAP = "nextAfter";
    private static final String LESS_THAN_TRAP = "lessThan";
    private static final String GREATER_THAN_TRAP = "greaterThan";
    private static final String NEW_INSTANCE_TRAP = "newInstance";
    private final DfpField field;
    protected int[] mant;
    protected byte sign;
    protected int exp;
    protected byte nans;

    protected Dfp(DfpField field) {
        this.mant = new int[field.getRadixDigits()];
        this.sign = (byte) 1;
        this.exp = 0;
        this.nans = (byte) 0;
        this.field = field;
    }

    protected Dfp(DfpField field, byte x) {
        this(field, x);
    }

    protected Dfp(DfpField field, int x) {
        this(field, x);
    }

    protected Dfp(DfpField field, long x) {
        this.mant = new int[field.getRadixDigits()];
        this.nans = (byte) 0;
        this.field = field;
        boolean isLongMin = false;
        if (x == Long.MIN_VALUE) {
            isLongMin = true;
            x++;
        }
        if (x < 0) {
            this.sign = (byte) -1;
            x = -x;
        } else {
            this.sign = (byte) 1;
        }
        this.exp = 0;
        while (x != 0) {
            System.arraycopy(this.mant, this.mant.length - this.exp, this.mant, (this.mant.length - 1) - this.exp, this.exp);
            this.mant[this.mant.length - 1] = (int) (x % VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER);
            x /= VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER;
            this.exp++;
        }
        if (isLongMin) {
            for (int i = 0; i < this.mant.length - 1; i++) {
                if (this.mant[i] != 0) {
                    int[] iArr = this.mant;
                    int i2 = i;
                    iArr[i2] = iArr[i2] + 1;
                    return;
                }
            }
        }
    }

    protected Dfp(DfpField field, double x) {
        this.mant = new int[field.getRadixDigits()];
        this.sign = (byte) 1;
        this.exp = 0;
        this.nans = (byte) 0;
        this.field = field;
        long bits = Double.doubleToLongBits(x);
        long mantissa = bits & 4503599627370495L;
        int exponent = ((int) ((bits & 9218868437227405312L) >> 52)) - 1023;
        if (exponent == -1023) {
            if (x == 0.0d) {
                return;
            }
            exponent++;
            while ((mantissa & 4503599627370496L) == 0) {
                exponent--;
                mantissa <<= 1;
            }
            mantissa &= 4503599627370495L;
        }
        if (exponent != 1024) {
            Dfp xdfp = new Dfp(field, mantissa).divide(new Dfp(field, 4503599627370496L)).add(field.getOne()).multiply(DfpMath.pow(field.getTwo(), exponent));
            xdfp = (bits & Long.MIN_VALUE) != 0 ? xdfp.negate() : xdfp;
            System.arraycopy(xdfp.mant, 0, this.mant, 0, this.mant.length);
            this.sign = xdfp.sign;
            this.exp = xdfp.exp;
            this.nans = xdfp.nans;
            return;
        }
        if (x != x) {
            this.sign = (byte) 1;
            this.nans = (byte) 3;
        } else if (x < 0.0d) {
            this.sign = (byte) -1;
            this.nans = (byte) 1;
        } else {
            this.sign = (byte) 1;
            this.nans = (byte) 1;
        }
    }

    public Dfp(Dfp d) {
        this.mant = (int[]) d.mant.clone();
        this.sign = d.sign;
        this.exp = d.exp;
        this.nans = d.nans;
        this.field = d.field;
    }

    protected Dfp(DfpField field, String s) {
        String fpdecimal;
        this.mant = new int[field.getRadixDigits()];
        this.sign = (byte) 1;
        this.exp = 0;
        this.nans = (byte) 0;
        this.field = field;
        boolean decimalFound = false;
        char[] striped = new char[(getRadixDigits() * 4) + 8];
        if (s.equals(POS_INFINITY_STRING)) {
            this.sign = (byte) 1;
            this.nans = (byte) 1;
            return;
        }
        if (s.equals(NEG_INFINITY_STRING)) {
            this.sign = (byte) -1;
            this.nans = (byte) 1;
            return;
        }
        if (s.equals(NAN_STRING)) {
            this.sign = (byte) 1;
            this.nans = (byte) 3;
            return;
        }
        int p = s.indexOf("e");
        p = p == -1 ? s.indexOf("E") : p;
        int sciexp = 0;
        if (p != -1) {
            fpdecimal = s.substring(0, p);
            String fpexp = s.substring(p + 1);
            boolean negative = false;
            for (int i = 0; i < fpexp.length(); i++) {
                if (fpexp.charAt(i) == '-') {
                    negative = true;
                } else if (fpexp.charAt(i) >= '0' && fpexp.charAt(i) <= '9') {
                    sciexp = ((sciexp * 10) + fpexp.charAt(i)) - 48;
                }
            }
            if (negative) {
                sciexp = -sciexp;
            }
        } else {
            fpdecimal = s;
        }
        if (fpdecimal.indexOf("-") != -1) {
            this.sign = (byte) -1;
        }
        int p2 = 0;
        int decimalPos = 0;
        do {
            if (fpdecimal.charAt(p2) >= '1' && fpdecimal.charAt(p2) <= '9') {
                break;
            }
            if (decimalFound && fpdecimal.charAt(p2) == '0') {
                decimalPos--;
            }
            decimalFound = fpdecimal.charAt(p2) == '.' ? true : decimalFound;
            p2++;
        } while (p2 != fpdecimal.length());
        int q = 4;
        striped[0] = '0';
        striped[1] = '0';
        striped[2] = '0';
        striped[3] = '0';
        int significantDigits = 0;
        while (p2 != fpdecimal.length() && q != (this.mant.length * 4) + 4 + 1) {
            if (fpdecimal.charAt(p2) == '.') {
                decimalFound = true;
                decimalPos = significantDigits;
                p2++;
            } else if (fpdecimal.charAt(p2) < '0' || fpdecimal.charAt(p2) > '9') {
                p2++;
            } else {
                striped[q] = fpdecimal.charAt(p2);
                q++;
                p2++;
                significantDigits++;
            }
        }
        if (decimalFound && q != 4) {
            while (true) {
                q--;
                if (q == 4 || striped[q] != '0') {
                    break;
                } else {
                    significantDigits--;
                }
            }
        }
        if (decimalFound && significantDigits == 0) {
            decimalPos = 0;
        }
        decimalPos = decimalFound ? decimalPos : q - 4;
        int p3 = (significantDigits - 1) + 4;
        int trailingZeros = 0;
        while (p3 > 4 && striped[p3] == '0') {
            trailingZeros++;
            p3--;
        }
        int i2 = ((400 - decimalPos) - (sciexp % 4)) % 4;
        int q2 = 4 - i2;
        int decimalPos2 = decimalPos + i2;
        while (p3 - q2 < this.mant.length * 4) {
            for (int i3 = 0; i3 < 4; i3++) {
                p3++;
                striped[p3] = '0';
            }
        }
        for (int i4 = this.mant.length - 1; i4 >= 0; i4--) {
            this.mant[i4] = ((striped[q2] - '0') * 1000) + ((striped[q2 + 1] - '0') * 100) + ((striped[q2 + 2] - '0') * 10) + (striped[q2 + 3] - '0');
            q2 += 4;
        }
        this.exp = (decimalPos2 + sciexp) / 4;
        if (q2 < striped.length) {
            round((striped[q2] - '0') * 1000);
        }
    }

    protected Dfp(DfpField field, byte sign, byte nans) {
        this.field = field;
        this.mant = new int[field.getRadixDigits()];
        this.sign = sign;
        this.exp = 0;
        this.nans = nans;
    }

    private static int compare(Dfp a, Dfp b) {
        if (a.mant[a.mant.length - 1] == 0 && b.mant[b.mant.length - 1] == 0 && a.nans == 0 && b.nans == 0) {
            return 0;
        }
        if (a.sign != b.sign) {
            if (a.sign == -1) {
                return -1;
            }
            return 1;
        }
        if (a.nans == 1 && b.nans == 0) {
            return a.sign;
        }
        if (a.nans == 0 && b.nans == 1) {
            return -b.sign;
        }
        if (a.nans == 1 && b.nans == 1) {
            return 0;
        }
        if (b.mant[b.mant.length - 1] != 0 && a.mant[b.mant.length - 1] != 0) {
            if (a.exp < b.exp) {
                return -a.sign;
            }
            if (a.exp > b.exp) {
                return a.sign;
            }
        }
        for (int i = a.mant.length - 1; i >= 0; i--) {
            if (a.mant[i] > b.mant[i]) {
                return a.sign;
            }
            if (a.mant[i] < b.mant[i]) {
                return -a.sign;
            }
        }
        return 0;
    }

    public static Dfp copysign(Dfp x, Dfp y) {
        Dfp result = x.newInstance(x);
        result.sign = y.sign;
        return result;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.commons.math.dfp.DfpField] */
    public Dfp newInstance() {
        return new Dfp((DfpField) getField2());
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.commons.math.dfp.DfpField] */
    public Dfp newInstance(byte x) {
        return new Dfp((DfpField) getField2(), x);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.commons.math.dfp.DfpField] */
    public Dfp newInstance(int x) {
        return new Dfp((DfpField) getField2(), x);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.commons.math.dfp.DfpField] */
    public Dfp newInstance(long x) {
        return new Dfp((DfpField) getField2(), x);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.commons.math.dfp.DfpField] */
    public Dfp newInstance(double x) {
        return new Dfp((DfpField) getField2(), x);
    }

    public Dfp newInstance(Dfp d) {
        if (this.field.getRadixDigits() != d.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = newInstance(getZero());
            result.nans = (byte) 3;
            return dotrap(1, NEW_INSTANCE_TRAP, d, result);
        }
        return new Dfp(d);
    }

    public Dfp newInstance(String s) {
        return new Dfp(this.field, s);
    }

    public Dfp newInstance(byte sig, byte code) {
        return this.field.newDfp(sig, code);
    }

    @Override // org.apache.commons.math.FieldElement
    /* renamed from: getField, reason: merged with bridge method [inline-methods] */
    public Field<Dfp> getField2() {
        return this.field;
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
        for (int i = this.mant.length - 1; i > 0; i--) {
            this.mant[i] = this.mant[i - 1];
        }
        this.mant[0] = 0;
        this.exp--;
    }

    protected void shiftRight() {
        for (int i = 0; i < this.mant.length - 1; i++) {
            this.mant[i] = this.mant[i + 1];
        }
        this.mant[this.mant.length - 1] = 0;
        this.exp++;
    }

    protected int align(int e) {
        int lostdigit = 0;
        boolean inexact = false;
        int diff = this.exp - e;
        int adiff = diff;
        if (adiff < 0) {
            adiff = -adiff;
        }
        if (diff == 0) {
            return 0;
        }
        if (adiff > this.mant.length + 1) {
            Arrays.fill(this.mant, 0);
            this.exp = e;
            this.field.setIEEEFlagsBits(16);
            dotrap(16, ALIGN_TRAP, this, this);
            return 0;
        }
        for (int i = 0; i < adiff; i++) {
            if (diff < 0) {
                if (lostdigit != 0) {
                    inexact = true;
                }
                lostdigit = this.mant[0];
                shiftRight();
            } else {
                shiftLeft();
            }
        }
        if (inexact) {
            this.field.setIEEEFlagsBits(16);
            dotrap(16, ALIGN_TRAP, this, this);
        }
        return lostdigit;
    }

    public boolean lessThan(Dfp x) {
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = newInstance(getZero());
            result.nans = (byte) 3;
            dotrap(1, LESS_THAN_TRAP, x, result);
            return false;
        }
        if (!isNaN() && !x.isNaN()) {
            return compare(this, x) < 0;
        }
        this.field.setIEEEFlagsBits(1);
        dotrap(1, LESS_THAN_TRAP, x, newInstance(getZero()));
        return false;
    }

    public boolean greaterThan(Dfp x) {
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = newInstance(getZero());
            result.nans = (byte) 3;
            dotrap(1, GREATER_THAN_TRAP, x, result);
            return false;
        }
        if (!isNaN() && !x.isNaN()) {
            return compare(this, x) > 0;
        }
        this.field.setIEEEFlagsBits(1);
        dotrap(1, GREATER_THAN_TRAP, x, newInstance(getZero()));
        return false;
    }

    public boolean isInfinite() {
        return this.nans == 1;
    }

    public boolean isNaN() {
        return this.nans == 3 || this.nans == 2;
    }

    public boolean equals(Object other) {
        if (other instanceof Dfp) {
            Dfp x = (Dfp) other;
            return !isNaN() && !x.isNaN() && this.field.getRadixDigits() == x.field.getRadixDigits() && compare(this, x) == 0;
        }
        return false;
    }

    public int hashCode() {
        return 17 + (this.sign << 8) + (this.nans << 16) + this.exp + Arrays.hashCode(this.mant);
    }

    public boolean unequal(Dfp x) {
        if (isNaN() || x.isNaN() || this.field.getRadixDigits() != x.field.getRadixDigits()) {
            return false;
        }
        return greaterThan(x) || lessThan(x);
    }

    public Dfp rint() {
        return trunc(DfpField.RoundingMode.ROUND_HALF_EVEN);
    }

    public Dfp floor() {
        return trunc(DfpField.RoundingMode.ROUND_FLOOR);
    }

    public Dfp ceil() {
        return trunc(DfpField.RoundingMode.ROUND_CEIL);
    }

    public Dfp remainder(Dfp d) {
        Dfp result = subtract(divide(d).rint().multiply(d));
        if (result.mant[this.mant.length - 1] == 0) {
            result.sign = this.sign;
        }
        return result;
    }

    protected Dfp trunc(DfpField.RoundingMode rmode) {
        boolean changed = false;
        if (isNaN()) {
            return newInstance(this);
        }
        if (this.nans == 1) {
            return newInstance(this);
        }
        if (this.mant[this.mant.length - 1] == 0) {
            return newInstance(this);
        }
        if (this.exp < 0) {
            this.field.setIEEEFlagsBits(16);
            return dotrap(16, TRUNC_TRAP, this, newInstance(getZero()));
        }
        if (this.exp >= this.mant.length) {
            return newInstance(this);
        }
        Dfp result = newInstance(this);
        for (int i = 0; i < this.mant.length - result.exp; i++) {
            changed |= result.mant[i] != 0;
            result.mant[i] = 0;
        }
        if (changed) {
            switch (rmode) {
                case ROUND_FLOOR:
                    if (result.sign == -1) {
                        result = result.add(newInstance(-1));
                        break;
                    }
                    break;
                case ROUND_CEIL:
                    if (result.sign == 1) {
                        result = result.add(getOne());
                        break;
                    }
                    break;
                case ROUND_HALF_EVEN:
                default:
                    Dfp half = newInstance("0.5");
                    Dfp a = subtract(result);
                    a.sign = (byte) 1;
                    if (a.greaterThan(half)) {
                        a = newInstance(getOne());
                        a.sign = this.sign;
                        result = result.add(a);
                    }
                    if (a.equals(half) && result.exp > 0 && (result.mant[this.mant.length - result.exp] & 1) != 0) {
                        Dfp a2 = newInstance(getOne());
                        a2.sign = this.sign;
                        result = result.add(a2);
                        break;
                    }
                    break;
            }
            this.field.setIEEEFlagsBits(16);
            return dotrap(16, TRUNC_TRAP, this, result);
        }
        return result;
    }

    public int intValue() {
        int result = 0;
        Dfp rounded = rint();
        if (rounded.greaterThan(newInstance(Integer.MAX_VALUE))) {
            return Integer.MAX_VALUE;
        }
        if (rounded.lessThan(newInstance(Integer.MIN_VALUE))) {
            return Integer.MIN_VALUE;
        }
        for (int i = this.mant.length - 1; i >= this.mant.length - rounded.exp; i--) {
            result = (result * 10000) + rounded.mant[i];
        }
        if (rounded.sign == -1) {
            result = -result;
        }
        return result;
    }

    public int log10K() {
        return this.exp - 1;
    }

    public Dfp power10K(int e) {
        Dfp d = newInstance(getOne());
        d.exp = e + 1;
        return d;
    }

    public int log10() {
        if (this.mant[this.mant.length - 1] > 1000) {
            return (this.exp * 4) - 1;
        }
        if (this.mant[this.mant.length - 1] > 100) {
            return (this.exp * 4) - 2;
        }
        if (this.mant[this.mant.length - 1] > 10) {
            return (this.exp * 4) - 3;
        }
        return (this.exp * 4) - 4;
    }

    public Dfp power10(int e) {
        Dfp d = newInstance(getOne());
        if (e >= 0) {
            d.exp = (e / 4) + 1;
        } else {
            d.exp = (e + 1) / 4;
        }
        switch (((e % 4) + 4) % 4) {
            case 0:
                break;
            case 1:
                d = d.multiply(10);
                break;
            case 2:
                d = d.multiply(100);
                break;
            default:
                d = d.multiply(1000);
                break;
        }
        return d;
    }

    protected int complement(int extra) {
        int extra2 = 10000 - extra;
        for (int i = 0; i < this.mant.length; i++) {
            this.mant[i] = (10000 - this.mant[i]) - 1;
        }
        int rh = extra2 / 10000;
        int extra3 = extra2 - (rh * 10000);
        for (int i2 = 0; i2 < this.mant.length; i2++) {
            int r = this.mant[i2] + rh;
            rh = r / 10000;
            this.mant[i2] = r - (rh * 10000);
        }
        return extra3;
    }

    @Override // org.apache.commons.math.FieldElement
    public Dfp add(Dfp x) {
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = newInstance(getZero());
            result.nans = (byte) 3;
            return dotrap(1, ADD_TRAP, x, result);
        }
        if (this.nans != 0 || x.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (x.isNaN()) {
                return x;
            }
            if (this.nans == 1 && x.nans == 0) {
                return this;
            }
            if (x.nans == 1 && this.nans == 0) {
                return x;
            }
            if (x.nans == 1 && this.nans == 1 && this.sign == x.sign) {
                return x;
            }
            if (x.nans == 1 && this.nans == 1 && this.sign != x.sign) {
                this.field.setIEEEFlagsBits(1);
                Dfp result2 = newInstance(getZero());
                result2.nans = (byte) 3;
                return dotrap(1, ADD_TRAP, x, result2);
            }
        }
        Dfp a = newInstance(this);
        Dfp b = newInstance(x);
        Dfp result3 = newInstance(getZero());
        byte asign = a.sign;
        byte bsign = b.sign;
        a.sign = (byte) 1;
        b.sign = (byte) 1;
        byte rsign = bsign;
        if (compare(a, b) > 0) {
            rsign = asign;
        }
        if (b.mant[this.mant.length - 1] == 0) {
            b.exp = a.exp;
        }
        if (a.mant[this.mant.length - 1] == 0) {
            a.exp = b.exp;
        }
        int aextradigit = 0;
        int bextradigit = 0;
        if (a.exp < b.exp) {
            aextradigit = a.align(b.exp);
        } else {
            bextradigit = b.align(a.exp);
        }
        if (asign != bsign) {
            if (asign == rsign) {
                bextradigit = b.complement(bextradigit);
            } else {
                aextradigit = a.complement(aextradigit);
            }
        }
        int rh = 0;
        for (int i = 0; i < this.mant.length; i++) {
            int r = a.mant[i] + b.mant[i] + rh;
            rh = r / 10000;
            result3.mant[i] = r - (rh * 10000);
        }
        result3.exp = a.exp;
        result3.sign = rsign;
        if (rh != 0 && asign == bsign) {
            int lostdigit = result3.mant[0];
            result3.shiftRight();
            result3.mant[this.mant.length - 1] = rh;
            int excp = result3.round(lostdigit);
            if (excp != 0) {
                result3 = dotrap(excp, ADD_TRAP, x, result3);
            }
        }
        for (int i2 = 0; i2 < this.mant.length && result3.mant[this.mant.length - 1] == 0; i2++) {
            result3.shiftLeft();
            if (i2 == 0) {
                result3.mant[0] = aextradigit + bextradigit;
                aextradigit = 0;
                bextradigit = 0;
            }
        }
        if (result3.mant[this.mant.length - 1] == 0) {
            result3.exp = 0;
            if (asign != bsign) {
                result3.sign = (byte) 1;
            }
        }
        int excp2 = result3.round(aextradigit + bextradigit);
        if (excp2 != 0) {
            result3 = dotrap(excp2, ADD_TRAP, x, result3);
        }
        return result3;
    }

    public Dfp negate() {
        Dfp result = newInstance(this);
        result.sign = (byte) (-result.sign);
        return result;
    }

    @Override // org.apache.commons.math.FieldElement
    public Dfp subtract(Dfp x) {
        return add(x.negate());
    }

    protected int round(int n) {
        boolean inc;
        switch (this.field.getRoundingMode()) {
            case ROUND_FLOOR:
            default:
                inc = this.sign == -1 && n != 0;
                break;
            case ROUND_CEIL:
                inc = this.sign == 1 && n != 0;
                break;
            case ROUND_HALF_EVEN:
                inc = n > 5000 || (n == 5000 && (this.mant[0] & 1) == 1);
                break;
            case ROUND_DOWN:
                inc = false;
                break;
            case ROUND_UP:
                inc = n != 0;
                break;
            case ROUND_HALF_UP:
                inc = n >= 5000;
                break;
            case ROUND_HALF_DOWN:
                inc = n > 5000;
                break;
            case ROUND_HALF_ODD:
                inc = n > 5000 || (n == 5000 && (this.mant[0] & 1) == 0);
                break;
        }
        if (inc) {
            int rh = 1;
            for (int i = 0; i < this.mant.length; i++) {
                int r = this.mant[i] + rh;
                rh = r / 10000;
                this.mant[i] = r - (rh * 10000);
            }
            if (rh != 0) {
                shiftRight();
                this.mant[this.mant.length - 1] = rh;
            }
        }
        if (this.exp < -32767) {
            this.field.setIEEEFlagsBits(8);
            return 8;
        }
        if (this.exp > 32768) {
            this.field.setIEEEFlagsBits(4);
            return 4;
        }
        if (n != 0) {
            this.field.setIEEEFlagsBits(16);
            return 16;
        }
        return 0;
    }

    @Override // org.apache.commons.math.FieldElement
    public Dfp multiply(Dfp x) {
        int excp;
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = newInstance(getZero());
            result.nans = (byte) 3;
            return dotrap(1, MULTIPLY_TRAP, x, result);
        }
        Dfp result2 = newInstance(getZero());
        if (this.nans != 0 || x.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (x.isNaN()) {
                return x;
            }
            if (this.nans == 1 && x.nans == 0 && x.mant[this.mant.length - 1] != 0) {
                Dfp result3 = newInstance(this);
                result3.sign = (byte) (this.sign * x.sign);
                return result3;
            }
            if (x.nans == 1 && this.nans == 0 && this.mant[this.mant.length - 1] != 0) {
                Dfp result4 = newInstance(x);
                result4.sign = (byte) (this.sign * x.sign);
                return result4;
            }
            if (x.nans == 1 && this.nans == 1) {
                Dfp result5 = newInstance(this);
                result5.sign = (byte) (this.sign * x.sign);
                return result5;
            }
            if ((x.nans == 1 && this.nans == 0 && this.mant[this.mant.length - 1] == 0) || (this.nans == 1 && x.nans == 0 && x.mant[this.mant.length - 1] == 0)) {
                this.field.setIEEEFlagsBits(1);
                Dfp result6 = newInstance(getZero());
                result6.nans = (byte) 3;
                return dotrap(1, MULTIPLY_TRAP, x, result6);
            }
        }
        int[] product = new int[this.mant.length * 2];
        for (int i = 0; i < this.mant.length; i++) {
            int rh = 0;
            for (int j = 0; j < this.mant.length; j++) {
                int r = (this.mant[i] * x.mant[j]) + product[i + j] + rh;
                rh = r / 10000;
                product[i + j] = r - (rh * 10000);
            }
            product[i + this.mant.length] = rh;
        }
        int md = (this.mant.length * 2) - 1;
        int i2 = (this.mant.length * 2) - 1;
        while (true) {
            if (i2 < 0) {
                break;
            }
            if (product[i2] != 0) {
                md = i2;
                break;
            }
            i2--;
        }
        for (int i3 = 0; i3 < this.mant.length; i3++) {
            result2.mant[(this.mant.length - i3) - 1] = product[md - i3];
        }
        result2.exp = (((this.exp + x.exp) + md) - (2 * this.mant.length)) + 1;
        result2.sign = (byte) (this.sign == x.sign ? 1 : -1);
        if (result2.mant[this.mant.length - 1] == 0) {
            result2.exp = 0;
        }
        if (md > this.mant.length - 1) {
            excp = result2.round(product[md - this.mant.length]);
        } else {
            excp = result2.round(0);
        }
        if (excp != 0) {
            result2 = dotrap(excp, MULTIPLY_TRAP, x, result2);
        }
        return result2;
    }

    public Dfp multiply(int x) {
        Dfp result = newInstance(this);
        if (this.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (this.nans == 1 && x != 0) {
                return newInstance(this);
            }
            if (this.nans == 1 && x == 0) {
                this.field.setIEEEFlagsBits(1);
                Dfp result2 = newInstance(getZero());
                result2.nans = (byte) 3;
                return dotrap(1, MULTIPLY_TRAP, newInstance(getZero()), result2);
            }
        }
        if (x < 0 || x >= 10000) {
            this.field.setIEEEFlagsBits(1);
            Dfp result3 = newInstance(getZero());
            result3.nans = (byte) 3;
            return dotrap(1, MULTIPLY_TRAP, result3, result3);
        }
        int rh = 0;
        for (int i = 0; i < this.mant.length; i++) {
            int r = (this.mant[i] * x) + rh;
            rh = r / 10000;
            result.mant[i] = r - (rh * 10000);
        }
        int lostdigit = 0;
        if (rh != 0) {
            lostdigit = result.mant[0];
            result.shiftRight();
            result.mant[this.mant.length - 1] = rh;
        }
        if (result.mant[this.mant.length - 1] == 0) {
            result.exp = 0;
        }
        int excp = result.round(lostdigit);
        if (excp != 0) {
            result = dotrap(excp, MULTIPLY_TRAP, result, result);
        }
        return result;
    }

    @Override // org.apache.commons.math.FieldElement
    public Dfp divide(Dfp divisor) {
        int excp;
        int trial = 0;
        if (this.field.getRadixDigits() != divisor.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = newInstance(getZero());
            result.nans = (byte) 3;
            return dotrap(1, DIVIDE_TRAP, divisor, result);
        }
        Dfp result2 = newInstance(getZero());
        if (this.nans != 0 || divisor.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (divisor.isNaN()) {
                return divisor;
            }
            if (this.nans == 1 && divisor.nans == 0) {
                Dfp result3 = newInstance(this);
                result3.sign = (byte) (this.sign * divisor.sign);
                return result3;
            }
            if (divisor.nans == 1 && this.nans == 0) {
                Dfp result4 = newInstance(getZero());
                result4.sign = (byte) (this.sign * divisor.sign);
                return result4;
            }
            if (divisor.nans == 1 && this.nans == 1) {
                this.field.setIEEEFlagsBits(1);
                Dfp result5 = newInstance(getZero());
                result5.nans = (byte) 3;
                return dotrap(1, DIVIDE_TRAP, divisor, result5);
            }
        }
        if (divisor.mant[this.mant.length - 1] == 0) {
            this.field.setIEEEFlagsBits(2);
            Dfp result6 = newInstance(getZero());
            result6.sign = (byte) (this.sign * divisor.sign);
            result6.nans = (byte) 1;
            return dotrap(2, DIVIDE_TRAP, divisor, result6);
        }
        int[] dividend = new int[this.mant.length + 1];
        int[] quotient = new int[this.mant.length + 2];
        int[] remainder = new int[this.mant.length + 1];
        dividend[this.mant.length] = 0;
        quotient[this.mant.length] = 0;
        quotient[this.mant.length + 1] = 0;
        remainder[this.mant.length] = 0;
        for (int i = 0; i < this.mant.length; i++) {
            dividend[i] = this.mant[i];
            quotient[i] = 0;
            remainder[i] = 0;
        }
        int nsqd = 0;
        for (int qd = this.mant.length + 1; qd >= 0; qd--) {
            int divMsb = (dividend[this.mant.length] * 10000) + dividend[this.mant.length - 1];
            int min = divMsb / (divisor.mant[this.mant.length - 1] + 1);
            int max = (divMsb + 1) / divisor.mant[this.mant.length - 1];
            boolean trialgood = false;
            while (!trialgood) {
                trial = (min + max) / 2;
                int rh = 0;
                int i2 = 0;
                while (i2 < this.mant.length + 1) {
                    int dm = i2 < this.mant.length ? divisor.mant[i2] : 0;
                    int r = (dm * trial) + rh;
                    rh = r / 10000;
                    remainder[i2] = r - (rh * 10000);
                    i2++;
                }
                int rh2 = 1;
                for (int i3 = 0; i3 < this.mant.length + 1; i3++) {
                    int r2 = (9999 - remainder[i3]) + dividend[i3] + rh2;
                    rh2 = r2 / 10000;
                    remainder[i3] = r2 - (rh2 * 10000);
                }
                if (rh2 == 0) {
                    max = trial - 1;
                } else {
                    int minadj = ((remainder[this.mant.length] * 10000) + remainder[this.mant.length - 1]) / (divisor.mant[this.mant.length - 1] + 1);
                    if (minadj >= 2) {
                        min = trial + minadj;
                    } else {
                        trialgood = false;
                        for (int i4 = this.mant.length - 1; i4 >= 0; i4--) {
                            if (divisor.mant[i4] > remainder[i4]) {
                                trialgood = true;
                            }
                            if (divisor.mant[i4] < remainder[i4]) {
                                break;
                            }
                        }
                        if (remainder[this.mant.length] != 0) {
                            trialgood = false;
                        }
                        if (!trialgood) {
                            min = trial + 1;
                        }
                    }
                }
            }
            quotient[qd] = trial;
            if (trial != 0 || nsqd != 0) {
                nsqd++;
            }
            if ((this.field.getRoundingMode() == DfpField.RoundingMode.ROUND_DOWN && nsqd == this.mant.length) || nsqd > this.mant.length) {
                break;
            }
            dividend[0] = 0;
            for (int i5 = 0; i5 < this.mant.length; i5++) {
                dividend[i5 + 1] = remainder[i5];
            }
        }
        int md = this.mant.length;
        int i6 = this.mant.length + 1;
        while (true) {
            if (i6 < 0) {
                break;
            }
            if (quotient[i6] != 0) {
                md = i6;
                break;
            }
            i6--;
        }
        for (int i7 = 0; i7 < this.mant.length; i7++) {
            result2.mant[(this.mant.length - i7) - 1] = quotient[md - i7];
        }
        result2.exp = ((this.exp - divisor.exp) + md) - this.mant.length;
        result2.sign = (byte) (this.sign == divisor.sign ? 1 : -1);
        if (result2.mant[this.mant.length - 1] == 0) {
            result2.exp = 0;
        }
        if (md > this.mant.length - 1) {
            excp = result2.round(quotient[md - this.mant.length]);
        } else {
            excp = result2.round(0);
        }
        if (excp != 0) {
            result2 = dotrap(excp, DIVIDE_TRAP, divisor, result2);
        }
        return result2;
    }

    public Dfp divide(int divisor) {
        if (this.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (this.nans == 1) {
                return newInstance(this);
            }
        }
        if (divisor == 0) {
            this.field.setIEEEFlagsBits(2);
            Dfp result = newInstance(getZero());
            result.sign = this.sign;
            result.nans = (byte) 1;
            return dotrap(2, DIVIDE_TRAP, getZero(), result);
        }
        if (divisor < 0 || divisor >= 10000) {
            this.field.setIEEEFlagsBits(1);
            Dfp result2 = newInstance(getZero());
            result2.nans = (byte) 3;
            return dotrap(1, DIVIDE_TRAP, result2, result2);
        }
        Dfp result3 = newInstance(this);
        int rl = 0;
        for (int i = this.mant.length - 1; i >= 0; i--) {
            int r = (rl * 10000) + result3.mant[i];
            int rh = r / divisor;
            rl = r - (rh * divisor);
            result3.mant[i] = rh;
        }
        if (result3.mant[this.mant.length - 1] == 0) {
            result3.shiftLeft();
            int r2 = rl * 10000;
            int rh2 = r2 / divisor;
            rl = r2 - (rh2 * divisor);
            result3.mant[0] = rh2;
        }
        int excp = result3.round((rl * 10000) / divisor);
        if (excp != 0) {
            result3 = dotrap(excp, DIVIDE_TRAP, result3, result3);
        }
        return result3;
    }

    public Dfp sqrt() {
        if (this.nans == 0 && this.mant[this.mant.length - 1] == 0) {
            return newInstance(this);
        }
        if (this.nans != 0) {
            if (this.nans == 1 && this.sign == 1) {
                return newInstance(this);
            }
            if (this.nans == 3) {
                return newInstance(this);
            }
            if (this.nans == 2) {
                this.field.setIEEEFlagsBits(1);
                return dotrap(1, SQRT_TRAP, null, newInstance(this));
            }
        }
        if (this.sign == -1) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = newInstance(this);
            result.nans = (byte) 3;
            return dotrap(1, SQRT_TRAP, null, result);
        }
        Dfp x = newInstance(this);
        if (x.exp < -1 || x.exp > 1) {
            x.exp = this.exp / 2;
        }
        switch (x.mant[this.mant.length - 1] / 2000) {
            case 0:
                x.mant[this.mant.length - 1] = (x.mant[this.mant.length - 1] / 2) + 1;
                break;
            case 1:
            default:
                x.mant[this.mant.length - 1] = 3000;
                break;
            case 2:
                x.mant[this.mant.length - 1] = 1500;
                break;
            case 3:
                x.mant[this.mant.length - 1] = 2200;
                break;
        }
        newInstance(x);
        Dfp px = getZero();
        getZero();
        while (x.unequal(px)) {
            Dfp dx = newInstance(x);
            dx.sign = (byte) -1;
            Dfp dx2 = dx.add(divide(x)).divide(2);
            Dfp ppx = px;
            px = x;
            x = x.add(dx2);
            if (x.equals(ppx) || dx2.mant[this.mant.length - 1] == 0) {
                return x;
            }
        }
        return x;
    }

    public String toString() {
        if (this.nans != 0) {
            if (this.nans == 1) {
                return this.sign < 0 ? NEG_INFINITY_STRING : POS_INFINITY_STRING;
            }
            return NAN_STRING;
        }
        if (this.exp > this.mant.length || this.exp < -1) {
            return dfp2sci();
        }
        return dfp2string();
    }

    protected String dfp2sci() {
        int p;
        char[] rawdigits = new char[this.mant.length * 4];
        char[] outputbuffer = new char[(this.mant.length * 4) + 20];
        int p2 = 0;
        for (int i = this.mant.length - 1; i >= 0; i--) {
            int i2 = p2;
            int p3 = p2 + 1;
            rawdigits[i2] = (char) ((this.mant[i] / 1000) + 48);
            int p4 = p3 + 1;
            rawdigits[p3] = (char) (((this.mant[i] / 100) % 10) + 48);
            int p5 = p4 + 1;
            rawdigits[p4] = (char) (((this.mant[i] / 10) % 10) + 48);
            p2 = p5 + 1;
            rawdigits[p5] = (char) ((this.mant[i] % 10) + 48);
        }
        int p6 = 0;
        while (p6 < rawdigits.length && rawdigits[p6] == '0') {
            p6++;
        }
        int shf = p6;
        int q = 0;
        if (this.sign == -1) {
            q = 0 + 1;
            outputbuffer[0] = '-';
        }
        if (p6 != rawdigits.length) {
            int i3 = q;
            int q2 = q + 1;
            int i4 = p6;
            int p7 = p6 + 1;
            outputbuffer[i3] = rawdigits[i4];
            int q3 = q2 + 1;
            outputbuffer[q2] = '.';
            while (p7 < rawdigits.length) {
                int i5 = q3;
                q3++;
                int i6 = p7;
                p7++;
                outputbuffer[i5] = rawdigits[i6];
            }
            int i7 = q3;
            int q4 = q3 + 1;
            outputbuffer[i7] = 'e';
            int e = ((this.exp * 4) - shf) - 1;
            int ae = e;
            if (e < 0) {
                ae = -e;
            }
            int i8 = 1000000000;
            while (true) {
                p = i8;
                if (p <= ae) {
                    break;
                }
                i8 = p / 10;
            }
            if (e < 0) {
                q4++;
                outputbuffer[q4] = '-';
            }
            while (p > 0) {
                int i9 = q4;
                q4++;
                outputbuffer[i9] = (char) ((ae / p) + 48);
                ae %= p;
                p /= 10;
            }
            return new String(outputbuffer, 0, q4);
        }
        int i10 = q;
        int q5 = q + 1;
        outputbuffer[i10] = '0';
        int q6 = q5 + 1;
        outputbuffer[q5] = '.';
        int q7 = q6 + 1;
        outputbuffer[q6] = '0';
        int q8 = q7 + 1;
        outputbuffer[q7] = 'e';
        int i11 = q8 + 1;
        outputbuffer[q8] = '0';
        return new String(outputbuffer, 0, 5);
    }

    protected String dfp2string() {
        char[] buffer = new char[(this.mant.length * 4) + 20];
        int p = 1;
        int e = this.exp;
        boolean pointInserted = false;
        buffer[0] = ' ';
        if (e <= 0) {
            int p2 = 1 + 1;
            buffer[1] = '0';
            p = p2 + 1;
            buffer[p2] = '.';
            pointInserted = true;
        }
        while (e < 0) {
            int i = p;
            int p3 = p + 1;
            buffer[i] = '0';
            int p4 = p3 + 1;
            buffer[p3] = '0';
            int p5 = p4 + 1;
            buffer[p4] = '0';
            p = p5 + 1;
            buffer[p5] = '0';
            e++;
        }
        for (int i2 = this.mant.length - 1; i2 >= 0; i2--) {
            int i3 = p;
            int p6 = p + 1;
            buffer[i3] = (char) ((this.mant[i2] / 1000) + 48);
            int p7 = p6 + 1;
            buffer[p6] = (char) (((this.mant[i2] / 100) % 10) + 48);
            int p8 = p7 + 1;
            buffer[p7] = (char) (((this.mant[i2] / 10) % 10) + 48);
            p = p8 + 1;
            buffer[p8] = (char) ((this.mant[i2] % 10) + 48);
            e--;
            if (e == 0) {
                p++;
                buffer[p] = '.';
                pointInserted = true;
            }
        }
        while (e > 0) {
            int i4 = p;
            int p9 = p + 1;
            buffer[i4] = '0';
            int p10 = p9 + 1;
            buffer[p9] = '0';
            int p11 = p10 + 1;
            buffer[p10] = '0';
            p = p11 + 1;
            buffer[p11] = '0';
            e--;
        }
        if (!pointInserted) {
            int i5 = p;
            p++;
            buffer[i5] = '.';
        }
        int q = 1;
        while (buffer[q] == '0') {
            q++;
        }
        if (buffer[q] == '.') {
            q--;
        }
        while (buffer[p - 1] == '0') {
            p--;
        }
        if (this.sign < 0) {
            q--;
            buffer[q] = '-';
        }
        return new String(buffer, q, p - q);
    }

    public Dfp dotrap(int type, String what, Dfp oper, Dfp result) {
        Dfp def = result;
        switch (type) {
            case 1:
                def = newInstance(getZero());
                def.sign = result.sign;
                def.nans = (byte) 3;
                break;
            case 2:
                if (this.nans == 0 && this.mant[this.mant.length - 1] != 0) {
                    def = newInstance(getZero());
                    def.sign = (byte) (this.sign * oper.sign);
                    def.nans = (byte) 1;
                }
                if (this.nans == 0 && this.mant[this.mant.length - 1] == 0) {
                    def = newInstance(getZero());
                    def.nans = (byte) 3;
                }
                if (this.nans == 1 || this.nans == 3) {
                    def = newInstance(getZero());
                    def.nans = (byte) 3;
                }
                if (this.nans == 1 || this.nans == 2) {
                    def = newInstance(getZero());
                    def.nans = (byte) 3;
                    break;
                }
                break;
            case 3:
            case 5:
            case 6:
            case 7:
            default:
                def = result;
                break;
            case 4:
                result.exp -= 32760;
                def = newInstance(getZero());
                def.sign = result.sign;
                def.nans = (byte) 1;
                break;
            case 8:
                if (result.exp + this.mant.length < -32767) {
                    def = newInstance(getZero());
                    def.sign = result.sign;
                } else {
                    def = newInstance(result);
                }
                result.exp += 32760;
                break;
        }
        return trap(type, what, oper, def, result);
    }

    protected Dfp trap(int type, String what, Dfp oper, Dfp def, Dfp result) {
        return def;
    }

    public int classify() {
        return this.nans;
    }

    public Dfp nextAfter(Dfp x) {
        Dfp result;
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result2 = newInstance(getZero());
            result2.nans = (byte) 3;
            return dotrap(1, NEXT_AFTER_TRAP, x, result2);
        }
        boolean up = false;
        if (lessThan(x)) {
            up = true;
        }
        if (compare(this, x) == 0) {
            return newInstance(x);
        }
        if (lessThan(getZero())) {
            up = !up;
        }
        if (up) {
            Dfp inc = newInstance(getOne());
            inc.exp = (this.exp - this.mant.length) + 1;
            inc.sign = this.sign;
            if (equals(getZero())) {
                inc.exp = (-32767) - this.mant.length;
            }
            result = add(inc);
        } else {
            Dfp inc2 = newInstance(getOne());
            inc2.exp = this.exp;
            inc2.sign = this.sign;
            if (equals(inc2)) {
                inc2.exp = this.exp - this.mant.length;
            } else {
                inc2.exp = (this.exp - this.mant.length) + 1;
            }
            if (equals(getZero())) {
                inc2.exp = (-32767) - this.mant.length;
            }
            result = subtract(inc2);
        }
        if (result.classify() == 1 && classify() != 1) {
            this.field.setIEEEFlagsBits(16);
            result = dotrap(16, NEXT_AFTER_TRAP, x, result);
        }
        if (result.equals(getZero()) && !equals(getZero())) {
            this.field.setIEEEFlagsBits(16);
            result = dotrap(16, NEXT_AFTER_TRAP, x, result);
        }
        return result;
    }

    public double toDouble() throws NumberFormatException {
        if (isInfinite()) {
            if (lessThan(getZero())) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        if (isNaN()) {
            return Double.NaN;
        }
        Dfp y = this;
        boolean negate = false;
        if (lessThan(getZero())) {
            y = negate();
            negate = true;
        }
        int exponent = (int) (y.log10() * 3.32d);
        if (exponent < 0) {
            exponent--;
        }
        Dfp tempDfp = DfpMath.pow(getTwo(), exponent);
        while (true) {
            if (!tempDfp.lessThan(y) && !tempDfp.equals(y)) {
                break;
            }
            tempDfp = tempDfp.multiply(2);
            exponent++;
        }
        int exponent2 = exponent - 1;
        Dfp y2 = y.divide(DfpMath.pow(getTwo(), exponent2));
        if (exponent2 > -1023) {
            y2 = y2.subtract(getOne());
        }
        if (exponent2 < -1074) {
            return 0.0d;
        }
        if (exponent2 > 1023) {
            return negate ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        String str = y2.multiply(newInstance(4503599627370496L)).rint().toString();
        long mantissa = Long.parseLong(str.substring(0, str.length() - 1));
        if (mantissa == 4503599627370496L) {
            mantissa = 0;
            exponent2++;
        }
        if (exponent2 <= -1023) {
            exponent2--;
        }
        while (exponent2 < -1023) {
            exponent2++;
            mantissa >>>= 1;
        }
        long bits = mantissa | ((exponent2 + 1023) << 52);
        double x = Double.longBitsToDouble(bits);
        if (negate) {
            x = -x;
        }
        return x;
    }

    public double[] toSplitDouble() {
        double[] split = new double[2];
        split[0] = Double.longBitsToDouble(Double.doubleToLongBits(toDouble()) & (-1073741824));
        split[1] = subtract(newInstance(split[0])).toDouble();
        return split;
    }
}
