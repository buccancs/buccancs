package org.apache.commons.lang.math;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang.StringUtils;

/* loaded from: classes5.dex */
public class NumberUtils {
    public static final Long LONG_ZERO = new Long(0);
    public static final Long LONG_ONE = new Long(1);
    public static final Long LONG_MINUS_ONE = new Long(-1);
    public static final Integer INTEGER_ZERO = new Integer(0);
    public static final Integer INTEGER_ONE = new Integer(1);
    public static final Integer INTEGER_MINUS_ONE = new Integer(-1);
    public static final Short SHORT_ZERO = new Short((short) 0);
    public static final Short SHORT_ONE = new Short((short) 1);
    public static final Short SHORT_MINUS_ONE = new Short((short) -1);
    public static final Byte BYTE_ZERO = new Byte((byte) 0);
    public static final Byte BYTE_ONE = new Byte((byte) 1);
    public static final Byte BYTE_MINUS_ONE = new Byte((byte) -1);
    public static final Double DOUBLE_ZERO = new Double(0.0d);
    public static final Double DOUBLE_ONE = new Double(1.0d);
    public static final Double DOUBLE_MINUS_ONE = new Double(-1.0d);
    public static final Float FLOAT_ZERO = new Float(0.0f);
    public static final Float FLOAT_ONE = new Float(1.0f);
    public static final Float FLOAT_MINUS_ONE = new Float(-1.0f);

    public static byte max(byte b, byte b2, byte b3) {
        if (b2 > b) {
            b = b2;
        }
        return b3 > b ? b3 : b;
    }

    public static int max(int i, int i2, int i3) {
        if (i2 > i) {
            i = i2;
        }
        return i3 > i ? i3 : i;
    }

    public static long max(long j, long j2, long j3) {
        if (j2 > j) {
            j = j2;
        }
        return j3 > j ? j3 : j;
    }

    public static short max(short s, short s2, short s3) {
        if (s2 > s) {
            s = s2;
        }
        return s3 > s ? s3 : s;
    }

    public static byte min(byte b, byte b2, byte b3) {
        if (b2 < b) {
            b = b2;
        }
        return b3 < b ? b3 : b;
    }

    public static int min(int i, int i2, int i3) {
        if (i2 < i) {
            i = i2;
        }
        return i3 < i ? i3 : i;
    }

    public static long min(long j, long j2, long j3) {
        if (j2 < j) {
            j = j2;
        }
        return j3 < j ? j3 : j;
    }

    public static short min(short s, short s2, short s3) {
        if (s2 < s) {
            s = s2;
        }
        return s3 < s ? s3 : s;
    }

    public static int stringToInt(String str) {
        return toInt(str);
    }

    public static int toInt(String str) {
        return toInt(str, 0);
    }

    public static int stringToInt(String str, int i) {
        return toInt(str, i);
    }

    public static int toInt(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static long toLong(String str) {
        return toLong(str, 0L);
    }

    public static long toLong(String str, long j) {
        if (str == null) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static float toFloat(String str) {
        return toFloat(str, 0.0f);
    }

    public static float toFloat(String str, float f) {
        if (str == null) {
            return f;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f;
        }
    }

    public static double toDouble(String str) {
        return toDouble(str, 0.0d);
    }

    public static double toDouble(String str, double d) {
        if (str == null) {
            return d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static byte toByte(String str) {
        return toByte(str, (byte) 0);
    }

    public static byte toByte(String str, byte b) {
        if (str == null) {
            return b;
        }
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException unused) {
            return b;
        }
    }

    public static short toShort(String str) {
        return toShort(str, (short) 0);
    }

    public static short toShort(String str, short s) {
        if (str == null) {
            return s;
        }
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException unused) {
            return s;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x00f0, code lost:
    
        if (r1 == 'l') goto L59;
     */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0144 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0139 A[Catch: NumberFormatException -> 0x0144, TRY_LEAVE, TryCatch #4 {NumberFormatException -> 0x0144, blocks: (B:73:0x012f, B:75:0x0139), top: B:140:0x012f }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x014e A[Catch: NumberFormatException -> 0x015a, TRY_LEAVE, TryCatch #7 {NumberFormatException -> 0x015a, blocks: (B:80:0x0144, B:82:0x014e), top: B:146:0x0144 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Number createNumber(java.lang.String r15) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 486
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.math.NumberUtils.createNumber(java.lang.String):java.lang.Number");
    }

    private static boolean isAllZeros(String str) {
        if (str == null) {
            return true;
        }
        for (int length = str.length() - 1; length >= 0; length--) {
            if (str.charAt(length) != '0') {
                return false;
            }
        }
        return str.length() > 0;
    }

    public static Float createFloat(String str) {
        if (str == null) {
            return null;
        }
        return Float.valueOf(str);
    }

    public static Double createDouble(String str) {
        if (str == null) {
            return null;
        }
        return Double.valueOf(str);
    }

    public static Integer createInteger(String str) {
        if (str == null) {
            return null;
        }
        return Integer.decode(str);
    }

    public static Long createLong(String str) {
        if (str == null) {
            return null;
        }
        return Long.valueOf(str);
    }

    public static BigInteger createBigInteger(String str) {
        if (str == null) {
            return null;
        }
        return new BigInteger(str);
    }

    public static BigDecimal createBigDecimal(String str) {
        if (str == null) {
            return null;
        }
        if (StringUtils.isBlank(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        return new BigDecimal(str);
    }

    public static long min(long[] jArr) {
        if (jArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (jArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        long j = jArr[0];
        for (int i = 1; i < jArr.length; i++) {
            long j2 = jArr[i];
            if (j2 < j) {
                j = j2;
            }
        }
        return j;
    }

    public static int min(int[] iArr) {
        if (iArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (iArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        int i = iArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i3 < i) {
                i = i3;
            }
        }
        return i;
    }

    public static short min(short[] sArr) {
        if (sArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (sArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        short s = sArr[0];
        for (int i = 1; i < sArr.length; i++) {
            short s2 = sArr[i];
            if (s2 < s) {
                s = s2;
            }
        }
        return s;
    }

    public static byte min(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (bArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        byte b = bArr[0];
        for (int i = 1; i < bArr.length; i++) {
            byte b2 = bArr[i];
            if (b2 < b) {
                b = b2;
            }
        }
        return b;
    }

    public static double min(double[] dArr) {
        if (dArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (dArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        double d = dArr[0];
        for (int i = 1; i < dArr.length; i++) {
            if (Double.isNaN(dArr[i])) {
                return Double.NaN;
            }
            double d2 = dArr[i];
            if (d2 < d) {
                d = d2;
            }
        }
        return d;
    }

    public static float min(float[] fArr) {
        if (fArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (fArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        float f = fArr[0];
        for (int i = 1; i < fArr.length; i++) {
            if (Float.isNaN(fArr[i])) {
                return Float.NaN;
            }
            float f2 = fArr[i];
            if (f2 < f) {
                f = f2;
            }
        }
        return f;
    }

    public static long max(long[] jArr) {
        if (jArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (jArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        long j = jArr[0];
        for (int i = 1; i < jArr.length; i++) {
            long j2 = jArr[i];
            if (j2 > j) {
                j = j2;
            }
        }
        return j;
    }

    public static int max(int[] iArr) {
        if (iArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (iArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        int i = iArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i3 > i) {
                i = i3;
            }
        }
        return i;
    }

    public static short max(short[] sArr) {
        if (sArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (sArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        short s = sArr[0];
        for (int i = 1; i < sArr.length; i++) {
            short s2 = sArr[i];
            if (s2 > s) {
                s = s2;
            }
        }
        return s;
    }

    public static byte max(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (bArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        byte b = bArr[0];
        for (int i = 1; i < bArr.length; i++) {
            byte b2 = bArr[i];
            if (b2 > b) {
                b = b2;
            }
        }
        return b;
    }

    public static double max(double[] dArr) {
        if (dArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (dArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        double d = dArr[0];
        for (int i = 1; i < dArr.length; i++) {
            if (Double.isNaN(dArr[i])) {
                return Double.NaN;
            }
            double d2 = dArr[i];
            if (d2 > d) {
                d = d2;
            }
        }
        return d;
    }

    public static float max(float[] fArr) {
        if (fArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (fArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        float f = fArr[0];
        for (int i = 1; i < fArr.length; i++) {
            if (Float.isNaN(fArr[i])) {
                return Float.NaN;
            }
            float f2 = fArr[i];
            if (f2 > f) {
                f = f2;
            }
        }
        return f;
    }

    public static double min(double d, double d2, double d3) {
        return Math.min(Math.min(d, d2), d3);
    }

    public static float min(float f, float f2, float f3) {
        return Math.min(Math.min(f, f2), f3);
    }

    public static double max(double d, double d2, double d3) {
        return Math.max(Math.max(d, d2), d3);
    }

    public static float max(float f, float f2, float f3) {
        return Math.max(Math.max(f, f2), f3);
    }

    public static int compare(double d, double d2) {
        if (d < d2) {
            return -1;
        }
        if (d > d2) {
            return 1;
        }
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        long jDoubleToLongBits2 = Double.doubleToLongBits(d2);
        if (jDoubleToLongBits == jDoubleToLongBits2) {
            return 0;
        }
        return jDoubleToLongBits < jDoubleToLongBits2 ? -1 : 1;
    }

    public static int compare(float f, float f2) {
        if (f < f2) {
            return -1;
        }
        if (f > f2) {
            return 1;
        }
        int iFloatToIntBits = Float.floatToIntBits(f);
        int iFloatToIntBits2 = Float.floatToIntBits(f2);
        if (iFloatToIntBits == iFloatToIntBits2) {
            return 0;
        }
        return iFloatToIntBits < iFloatToIntBits2 ? -1 : 1;
    }

    public static boolean isDigits(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:127:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0063, code lost:
    
        if (r3 >= r0.length) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0065, code lost:
    
        r0 = r0[r3];
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0067, code lost:
    
        if (r0 < '0') goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0069, code lost:
    
        if (r0 > '9') goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x006b, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x006c, code lost:
    
        if (r0 == 'e') goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x006e, code lost:
    
        if (r0 != 'E') goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0071, code lost:
    
        if (r0 != '.') goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0073, code lost:
    
        if (r14 != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0075, code lost:
    
        if (r13 == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0078, code lost:
    
        return r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0079, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x007a, code lost:
    
        if (r11 != false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x007e, code lost:
    
        if (r0 == 'd') goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0082, code lost:
    
        if (r0 == 'D') goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0084, code lost:
    
        if (r0 == 'f') goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0088, code lost:
    
        if (r0 != 'F') goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x008a, code lost:
    
        return r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x008d, code lost:
    
        if (r0 == 'l') goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0091, code lost:
    
        if (r0 != 'L') goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0094, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0095, code lost:
    
        if (r12 == false) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0097, code lost:
    
        if (r13 != false) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0099, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x009b, code lost:
    
        if (r11 != false) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x009d, code lost:
    
        if (r12 == false) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x009f, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00b9, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isNumber(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.math.NumberUtils.isNumber(java.lang.String):boolean");
    }
}
