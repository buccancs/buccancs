package org.apache.commons.lang.time;

import com.shimmerresearch.driver.Configuration;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang.text.StrBuilder;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class DurationFormatUtils {
    public static final String ISO_EXTENDED_FORMAT_PATTERN = "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'";
    static final Object y = "y";
    static final Object M = "M";
    static final Object d = "d";
    static final Object H = "H";
    static final Object m = Configuration.CHANNEL_UNITS.METER;
    static final Object s = Configuration.CHANNEL_UNITS.SECONDS;
    static final Object S = "S";

    public static String formatDurationHMS(long j) {
        return formatDuration(j, "H:mm:ss.SSS");
    }

    public static String formatDurationISO(long j) {
        return formatDuration(j, ISO_EXTENDED_FORMAT_PATTERN, false);
    }

    public static String formatDuration(long j, String str) {
        return formatDuration(j, str, true);
    }

    public static String formatDuration(long j, String str, boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        Token[] tokenArrLexx = lexx(str);
        if (Token.containsTokenWithValue(tokenArrLexx, d)) {
            int i5 = (int) (j / 86400000);
            j -= i5 * 86400000;
            i = i5;
        } else {
            i = 0;
        }
        if (Token.containsTokenWithValue(tokenArrLexx, H)) {
            int i6 = (int) (j / 3600000);
            j -= i6 * 3600000;
            i2 = i6;
        } else {
            i2 = 0;
        }
        if (Token.containsTokenWithValue(tokenArrLexx, m)) {
            int i7 = (int) (j / 60000);
            j -= i7 * 60000;
            i3 = i7;
        } else {
            i3 = 0;
        }
        if (Token.containsTokenWithValue(tokenArrLexx, s)) {
            int i8 = (int) (j / 1000);
            j -= i8 * 1000;
            i4 = i8;
        } else {
            i4 = 0;
        }
        return format(tokenArrLexx, 0, 0, i, i2, i3, i4, Token.containsTokenWithValue(tokenArrLexx, S) ? (int) j : 0, z);
    }

    public static String formatDurationWords(long j, boolean z, boolean z2) {
        String duration = formatDuration(j, "d' days 'H' hours 'm' minutes 's' seconds'");
        if (z) {
            duration = new StringBuffer(StringUtils.SPACE).append(duration).toString();
            String strReplaceOnce = org.apache.commons.lang.StringUtils.replaceOnce(duration, " 0 days", "");
            if (strReplaceOnce.length() != duration.length()) {
                String strReplaceOnce2 = org.apache.commons.lang.StringUtils.replaceOnce(strReplaceOnce, " 0 hours", "");
                if (strReplaceOnce2.length() != strReplaceOnce.length()) {
                    duration = org.apache.commons.lang.StringUtils.replaceOnce(strReplaceOnce2, " 0 minutes", "");
                    if (duration.length() != duration.length()) {
                        duration = org.apache.commons.lang.StringUtils.replaceOnce(duration, " 0 seconds", "");
                    }
                } else {
                    duration = strReplaceOnce;
                }
            }
            if (duration.length() != 0) {
                duration = duration.substring(1);
            }
        }
        if (z2) {
            String strReplaceOnce3 = org.apache.commons.lang.StringUtils.replaceOnce(duration, " 0 seconds", "");
            if (strReplaceOnce3.length() != duration.length()) {
                duration = org.apache.commons.lang.StringUtils.replaceOnce(strReplaceOnce3, " 0 minutes", "");
                if (duration.length() != strReplaceOnce3.length()) {
                    String strReplaceOnce4 = org.apache.commons.lang.StringUtils.replaceOnce(duration, " 0 hours", "");
                    if (strReplaceOnce4.length() != duration.length()) {
                        duration = org.apache.commons.lang.StringUtils.replaceOnce(strReplaceOnce4, " 0 days", "");
                    }
                } else {
                    duration = strReplaceOnce3;
                }
            }
        }
        return org.apache.commons.lang.StringUtils.replaceOnce(org.apache.commons.lang.StringUtils.replaceOnce(org.apache.commons.lang.StringUtils.replaceOnce(org.apache.commons.lang.StringUtils.replaceOnce(new StringBuffer(StringUtils.SPACE).append(duration).toString(), " 1 seconds", " 1 second"), " 1 minutes", " 1 minute"), " 1 hours", " 1 hour"), " 1 days", " 1 day").trim();
    }

    public static String formatPeriodISO(long j, long j2) {
        return formatPeriod(j, j2, ISO_EXTENDED_FORMAT_PATTERN, false, TimeZone.getDefault());
    }

    public static String formatPeriod(long j, long j2, String str) {
        return formatPeriod(j, j2, str, true, TimeZone.getDefault());
    }

    public static String formatPeriod(long j, long j2, String str, boolean z, TimeZone timeZone) {
        int i;
        int i2;
        int i3;
        Token[] tokenArrLexx = lexx(str);
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(new Date(j));
        Calendar calendar2 = Calendar.getInstance(timeZone);
        calendar2.setTime(new Date(j2));
        int i4 = calendar2.get(14) - calendar.get(14);
        int i5 = calendar2.get(13) - calendar.get(13);
        int i6 = calendar2.get(12) - calendar.get(12);
        int i7 = calendar2.get(11) - calendar.get(11);
        int actualMaximum = calendar2.get(5) - calendar.get(5);
        int i8 = calendar2.get(2) - calendar.get(2);
        int i9 = calendar2.get(1) - calendar.get(1);
        while (i4 < 0) {
            i4 += 1000;
            i5--;
        }
        while (i5 < 0) {
            i5 += 60;
            i6--;
        }
        while (i6 < 0) {
            i6 += 60;
            i7--;
        }
        while (i7 < 0) {
            i7 += 24;
            actualMaximum--;
        }
        int i10 = 0;
        if (Token.containsTokenWithValue(tokenArrLexx, M)) {
            while (actualMaximum < 0) {
                actualMaximum += calendar.getActualMaximum(5);
                i8--;
                calendar.add(2, 1);
            }
            while (i8 < 0) {
                i8 += 12;
                i9--;
            }
            if (!Token.containsTokenWithValue(tokenArrLexx, y) && i9 != 0) {
                while (i9 != 0) {
                    i8 += i9 * 12;
                    i9 = 0;
                }
            }
            i = i8;
        } else {
            if (!Token.containsTokenWithValue(tokenArrLexx, y)) {
                int i11 = calendar2.get(1);
                if (i8 < 0) {
                    i11--;
                }
                while (calendar.get(1) != i11) {
                    int actualMaximum2 = actualMaximum + (calendar.getActualMaximum(6) - calendar.get(6));
                    if ((calendar instanceof GregorianCalendar) && calendar.get(2) == 1 && calendar.get(5) == 29) {
                        actualMaximum2++;
                    }
                    calendar.add(1, 1);
                    actualMaximum = actualMaximum2 + calendar.get(6);
                }
                i9 = 0;
            }
            while (calendar.get(2) != calendar2.get(2)) {
                actualMaximum += calendar.getActualMaximum(5);
                calendar.add(2, 1);
            }
            i = 0;
            while (actualMaximum < 0) {
                actualMaximum += calendar.getActualMaximum(5);
                i--;
                calendar.add(2, 1);
            }
        }
        int i12 = i9;
        if (Token.containsTokenWithValue(tokenArrLexx, d)) {
            i2 = actualMaximum;
        } else {
            i7 += actualMaximum * 24;
            i2 = 0;
        }
        if (!Token.containsTokenWithValue(tokenArrLexx, H)) {
            i6 += i7 * 60;
            i7 = 0;
        }
        if (!Token.containsTokenWithValue(tokenArrLexx, m)) {
            i5 += i6 * 60;
            i6 = 0;
        }
        if (Token.containsTokenWithValue(tokenArrLexx, s)) {
            i3 = i4;
            i10 = i5;
        } else {
            i3 = i4 + (i5 * 1000);
        }
        return format(tokenArrLexx, i12, i, i2, i7, i6, i10, i3, z);
    }

    static String format(Token[] tokenArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
        StrBuilder strBuilder = new StrBuilder();
        int i8 = i7;
        boolean z2 = false;
        for (Token token : tokenArr) {
            Object value = token.getValue();
            int count = token.getCount();
            if (value instanceof StringBuffer) {
                strBuilder.append(value.toString());
            } else {
                if (value == y) {
                    String string = Integer.toString(i);
                    if (z) {
                        string = org.apache.commons.lang.StringUtils.leftPad(string, count, '0');
                    }
                    strBuilder.append(string);
                } else if (value == M) {
                    String string2 = Integer.toString(i2);
                    if (z) {
                        string2 = org.apache.commons.lang.StringUtils.leftPad(string2, count, '0');
                    }
                    strBuilder.append(string2);
                } else if (value == d) {
                    String string3 = Integer.toString(i3);
                    if (z) {
                        string3 = org.apache.commons.lang.StringUtils.leftPad(string3, count, '0');
                    }
                    strBuilder.append(string3);
                } else if (value == H) {
                    String string4 = Integer.toString(i4);
                    if (z) {
                        string4 = org.apache.commons.lang.StringUtils.leftPad(string4, count, '0');
                    }
                    strBuilder.append(string4);
                } else if (value == m) {
                    String string5 = Integer.toString(i5);
                    if (z) {
                        string5 = org.apache.commons.lang.StringUtils.leftPad(string5, count, '0');
                    }
                    strBuilder.append(string5);
                } else if (value == s) {
                    String string6 = Integer.toString(i6);
                    if (z) {
                        string6 = org.apache.commons.lang.StringUtils.leftPad(string6, count, '0');
                    }
                    strBuilder.append(string6);
                    z2 = true;
                } else if (value == S) {
                    if (z2) {
                        i8 += 1000;
                        String string7 = Integer.toString(i8);
                        if (z) {
                            string7 = org.apache.commons.lang.StringUtils.leftPad(string7, count, '0');
                        }
                        strBuilder.append(string7.substring(1));
                    } else {
                        String string8 = Integer.toString(i8);
                        if (z) {
                            string8 = org.apache.commons.lang.StringUtils.leftPad(string8, count, '0');
                        }
                        strBuilder.append(string8);
                    }
                }
                z2 = false;
            }
        }
        return strBuilder.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0093 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static org.apache.commons.lang.time.DurationFormatUtils.Token[] lexx(java.lang.String r10) {
        /*
            char[] r10 = r10.toCharArray()
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r10.length
            r0.<init>(r1)
            int r1 = r10.length
            r2 = 0
            r3 = 0
            r6 = r3
            r7 = r6
            r4 = 0
            r5 = 0
        L11:
            if (r4 >= r1) goto L97
            char r8 = r10[r4]
            r9 = 39
            if (r5 == 0) goto L20
            if (r8 == r9) goto L20
            r6.append(r8)
            goto L93
        L20:
            if (r8 == r9) goto L66
            r9 = 72
            if (r8 == r9) goto L63
            r9 = 77
            if (r8 == r9) goto L60
            r9 = 83
            if (r8 == r9) goto L5d
            r9 = 100
            if (r8 == r9) goto L5a
            r9 = 109(0x6d, float:1.53E-43)
            if (r8 == r9) goto L57
            r9 = 115(0x73, float:1.61E-43)
            if (r8 == r9) goto L54
            r9 = 121(0x79, float:1.7E-43)
            if (r8 == r9) goto L51
            if (r6 != 0) goto L4d
            java.lang.StringBuffer r6 = new java.lang.StringBuffer
            r6.<init>()
            org.apache.commons.lang.time.DurationFormatUtils$Token r9 = new org.apache.commons.lang.time.DurationFormatUtils$Token
            r9.<init>(r6)
            r0.add(r9)
        L4d:
            r6.append(r8)
            goto L7a
        L51:
            java.lang.Object r8 = org.apache.commons.lang.time.DurationFormatUtils.y
            goto L7b
        L54:
            java.lang.Object r8 = org.apache.commons.lang.time.DurationFormatUtils.s
            goto L7b
        L57:
            java.lang.Object r8 = org.apache.commons.lang.time.DurationFormatUtils.m
            goto L7b
        L5a:
            java.lang.Object r8 = org.apache.commons.lang.time.DurationFormatUtils.d
            goto L7b
        L5d:
            java.lang.Object r8 = org.apache.commons.lang.time.DurationFormatUtils.S
            goto L7b
        L60:
            java.lang.Object r8 = org.apache.commons.lang.time.DurationFormatUtils.M
            goto L7b
        L63:
            java.lang.Object r8 = org.apache.commons.lang.time.DurationFormatUtils.H
            goto L7b
        L66:
            if (r5 == 0) goto L6c
            r6 = r3
            r8 = r6
            r5 = 0
            goto L7b
        L6c:
            java.lang.StringBuffer r6 = new java.lang.StringBuffer
            r6.<init>()
            org.apache.commons.lang.time.DurationFormatUtils$Token r5 = new org.apache.commons.lang.time.DurationFormatUtils$Token
            r5.<init>(r6)
            r0.add(r5)
            r5 = 1
        L7a:
            r8 = r3
        L7b:
            if (r8 == 0) goto L93
            if (r7 == 0) goto L89
            java.lang.Object r6 = r7.getValue()
            if (r6 != r8) goto L89
            r7.increment()
            goto L92
        L89:
            org.apache.commons.lang.time.DurationFormatUtils$Token r6 = new org.apache.commons.lang.time.DurationFormatUtils$Token
            r6.<init>(r8)
            r0.add(r6)
            r7 = r6
        L92:
            r6 = r3
        L93:
            int r4 = r4 + 1
            goto L11
        L97:
            int r10 = r0.size()
            org.apache.commons.lang.time.DurationFormatUtils$Token[] r10 = new org.apache.commons.lang.time.DurationFormatUtils.Token[r10]
            java.lang.Object[] r10 = r0.toArray(r10)
            org.apache.commons.lang.time.DurationFormatUtils$Token[] r10 = (org.apache.commons.lang.time.DurationFormatUtils.Token[]) r10
            org.apache.commons.lang.time.DurationFormatUtils$Token[] r10 = (org.apache.commons.lang.time.DurationFormatUtils.Token[]) r10
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.DurationFormatUtils.lexx(java.lang.String):org.apache.commons.lang.time.DurationFormatUtils$Token[]");
    }

    static class Token {
        private int count;
        private Object value;

        Token(Object obj) {
            this.value = obj;
            this.count = 1;
        }

        Token(Object obj, int i) {
            this.value = obj;
            this.count = i;
        }

        static boolean containsTokenWithValue(Token[] tokenArr, Object obj) {
            for (Token token : tokenArr) {
                if (token.getValue() == obj) {
                    return true;
                }
            }
            return false;
        }

        int getCount() {
            return this.count;
        }

        Object getValue() {
            return this.value;
        }

        void increment() {
            this.count++;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            if (this.value.getClass() != token.value.getClass() || this.count != token.count) {
                return false;
            }
            Object obj2 = this.value;
            if (obj2 instanceof StringBuffer) {
                return obj2.toString().equals(token.value.toString());
            }
            if (obj2 instanceof Number) {
                return obj2.equals(token.value);
            }
            return obj2 == token.value;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public String toString() {
            return org.apache.commons.lang.StringUtils.repeat(this.value.toString(), this.count);
        }
    }
}
