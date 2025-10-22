package org.apache.commons.lang3.math;

import java.lang.reflect.Array;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: classes5.dex */
public class NumberUtils {
    public static final Long LONG_ZERO = 0L;
    public static final Long LONG_ONE = 1L;
    public static final Long LONG_MINUS_ONE = -1L;
    public static final Integer INTEGER_ZERO = 0;
    public static final Integer INTEGER_ONE = 1;
    public static final Integer INTEGER_MINUS_ONE = -1;
    public static final Short SHORT_ZERO = 0;
    public static final Short SHORT_ONE = 1;
    public static final Short SHORT_MINUS_ONE = -1;
    public static final Byte BYTE_ZERO = (byte) 0;
    public static final Byte BYTE_ONE = (byte) 1;
    public static final Byte BYTE_MINUS_ONE = (byte) -1;
    public static final Double DOUBLE_ZERO = Double.valueOf(0.0d);
    public static final Double DOUBLE_ONE = Double.valueOf(1.0d);
    public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0d);
    public static final Float FLOAT_ZERO = Float.valueOf(0.0f);
    public static final Float FLOAT_ONE = Float.valueOf(1.0f);
    public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0f);

    public static int compare(byte b, byte b2) {
        return b - b2;
    }

    public static int compare(int i, int i2) {
        if (i == i2) {
            return 0;
        }
        return i < i2 ? -1 : 1;
    }

    public static int compare(long j, long j2) {
        if (j == j2) {
            return 0;
        }
        return j < j2 ? -1 : 1;
    }

    public static int compare(short s, short s2) {
        if (s == s2) {
            return 0;
        }
        return s < s2 ? -1 : 1;
    }

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

    public static int toInt(String str) {
        return toInt(str, 0);
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

    /* JADX WARN: Code restructure failed: missing block: B:82:0x0137, code lost:
    
        if (r1 == 'l') goto L83;
     */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0193 A[Catch: NumberFormatException -> 0x019f, TRY_LEAVE, TryCatch #3 {NumberFormatException -> 0x019f, blocks: (B:104:0x0189, B:106:0x0193), top: B:163:0x0189 }] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0189 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x017e A[Catch: NumberFormatException -> 0x0189, TRY_LEAVE, TryCatch #4 {NumberFormatException -> 0x0189, blocks: (B:97:0x0174, B:99:0x017e), top: B:165:0x0174 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Number createNumber(java.lang.String r15) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 580
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.NumberUtils.createNumber(java.lang.String):java.lang.Number");
    }

    private static String getMantissa(String str) {
        return getMantissa(str, str.length());
    }

    private static String getMantissa(String str, int i) {
        char cCharAt = str.charAt(0);
        return str.substring((cCharAt == '-' || cCharAt == '+') ? 1 : 0, i);
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
        return Long.decode(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.math.BigInteger createBigInteger(java.lang.String r4) {
        /*
            if (r4 != 0) goto L4
            r4 = 0
            return r4
        L4:
            java.lang.String r0 = "-"
            boolean r0 = r4.startsWith(r0)
            java.lang.String r1 = "0x"
            boolean r1 = r4.startsWith(r1, r0)
            r2 = 16
            if (r1 != 0) goto L42
            java.lang.String r1 = "0X"
            boolean r1 = r4.startsWith(r1, r0)
            if (r1 == 0) goto L1d
            goto L42
        L1d:
            java.lang.String r1 = "#"
            boolean r1 = r4.startsWith(r1, r0)
            if (r1 == 0) goto L28
            int r1 = r0 + 1
            goto L44
        L28:
            java.lang.String r1 = "0"
            boolean r1 = r4.startsWith(r1, r0)
            if (r1 == 0) goto L3e
            int r1 = r4.length()
            int r2 = r0 + 1
            if (r1 <= r2) goto L3e
            r1 = 8
            r1 = r2
            r2 = 8
            goto L44
        L3e:
            r2 = 10
            r1 = r0
            goto L44
        L42:
            int r1 = r0 + 2
        L44:
            java.math.BigInteger r3 = new java.math.BigInteger
            java.lang.String r4 = r4.substring(r1)
            r3.<init>(r4, r2)
            if (r0 == 0) goto L53
            java.math.BigInteger r3 = r3.negate()
        L53:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.NumberUtils.createBigInteger(java.lang.String):java.math.BigInteger");
    }

    public static BigDecimal createBigDecimal(String str) {
        if (str == null) {
            return null;
        }
        if (StringUtils.isBlank(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        if (str.trim().startsWith("--")) {
            throw new NumberFormatException(str + " is not a valid number.");
        }
        return new BigDecimal(str);
    }

    public static long min(long... jArr) {
        validateArray(jArr);
        long j = jArr[0];
        for (int i = 1; i < jArr.length; i++) {
            long j2 = jArr[i];
            if (j2 < j) {
                j = j2;
            }
        }
        return j;
    }

    public static int min(int... iArr) {
        validateArray(iArr);
        int i = iArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i3 < i) {
                i = i3;
            }
        }
        return i;
    }

    public static short min(short... sArr) {
        validateArray(sArr);
        short s = sArr[0];
        for (int i = 1; i < sArr.length; i++) {
            short s2 = sArr[i];
            if (s2 < s) {
                s = s2;
            }
        }
        return s;
    }

    public static byte min(byte... bArr) {
        validateArray(bArr);
        byte b = bArr[0];
        for (int i = 1; i < bArr.length; i++) {
            byte b2 = bArr[i];
            if (b2 < b) {
                b = b2;
            }
        }
        return b;
    }

    public static double min(double... dArr) {
        validateArray(dArr);
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

    public static float min(float... fArr) {
        validateArray(fArr);
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

    public static long max(long... jArr) {
        validateArray(jArr);
        long j = jArr[0];
        for (int i = 1; i < jArr.length; i++) {
            long j2 = jArr[i];
            if (j2 > j) {
                j = j2;
            }
        }
        return j;
    }

    public static int max(int... iArr) {
        validateArray(iArr);
        int i = iArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i3 > i) {
                i = i3;
            }
        }
        return i;
    }

    public static short max(short... sArr) {
        validateArray(sArr);
        short s = sArr[0];
        for (int i = 1; i < sArr.length; i++) {
            short s2 = sArr[i];
            if (s2 > s) {
                s = s2;
            }
        }
        return s;
    }

    public static byte max(byte... bArr) {
        validateArray(bArr);
        byte b = bArr[0];
        for (int i = 1; i < bArr.length; i++) {
            byte b2 = bArr[i];
            if (b2 > b) {
                b = b2;
            }
        }
        return b;
    }

    public static double max(double... dArr) {
        validateArray(dArr);
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

    public static float max(float... fArr) {
        validateArray(fArr);
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

    private static void validateArray(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        Validate.isTrue(Array.getLength(obj) != 0, "Array cannot be empty.", new Object[0]);
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

    public static boolean isDigits(String str) {
        return StringUtils.isNumeric(str);
    }

    @Deprecated
    public static boolean isNumber(String str) {
        return isCreatable(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x00cf, code lost:
    
        if (r13 != false) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x00d1, code lost:
    
        if (r14 == false) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x00d3, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x00f2, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x008c, code lost:
    
        if (r7 >= r0.length) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x008e, code lost:
    
        r0 = r0[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0090, code lost:
    
        if (r0 < '0') goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0092, code lost:
    
        if (r0 > '9') goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0096, code lost:
    
        if (org.apache.commons.lang3.SystemUtils.IS_JAVA_1_6 == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0098, code lost:
    
        if (r3 == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x009a, code lost:
    
        if (r16 != false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x009c, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x009d, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x009e, code lost:
    
        if (r0 == 'e') goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00a0, code lost:
    
        if (r0 != 'E') goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00a3, code lost:
    
        if (r0 != '.') goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00a5, code lost:
    
        if (r16 != false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00a7, code lost:
    
        if (r15 == false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00aa, code lost:
    
        return r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00ab, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00ac, code lost:
    
        if (r13 != false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00b0, code lost:
    
        if (r0 == 'd') goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x00b4, code lost:
    
        if (r0 == 'D') goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00b6, code lost:
    
        if (r0 == 'f') goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x00ba, code lost:
    
        if (r0 != 'F') goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00bc, code lost:
    
        return r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x00bf, code lost:
    
        if (r0 == 'l') goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x00c3, code lost:
    
        if (r0 != 'L') goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x00c6, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x00c7, code lost:
    
        if (r14 == false) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x00c9, code lost:
    
        if (r15 != false) goto L157;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x00cb, code lost:
    
        if (r16 != false) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x00cd, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isCreatable(java.lang.String r18) {
        /*
            Method dump skipped, instructions count: 285
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.NumberUtils.isCreatable(java.lang.String):boolean");
    }

    public static boolean isParsable(String str) {
        if (StringUtils.isEmpty(str) || str.charAt(str.length() - 1) == '.') {
            return false;
        }
        if (str.charAt(0) == '-') {
            if (str.length() == 1) {
                return false;
            }
            return withDecimalsParsing(str, 1);
        }
        return withDecimalsParsing(str, 0);
    }

    private static boolean withDecimalsParsing(String str, int i) {
        int i2 = 0;
        while (i < str.length()) {
            boolean z = str.charAt(i) == '.';
            if (z) {
                i2++;
            }
            if (i2 > 1) {
                return false;
            }
            if (!z && !Character.isDigit(str.charAt(i))) {
                return false;
            }
            i++;
        }
        return true;
    }
}
