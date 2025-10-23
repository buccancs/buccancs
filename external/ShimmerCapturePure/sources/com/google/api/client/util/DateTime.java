package com.google.api.client.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes.dex */
public final class DateTime implements Serializable {
    private static final long serialVersionUID = 1;
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final String RFC3339_REGEX = "(\\d{4})-(\\d{2})-(\\d{2})([Tt](\\d{2}):(\\d{2}):(\\d{2})(\\.\\d{1,9})?)?([Zz]|([+-])(\\d{2}):(\\d{2}))?";
    private static final Pattern RFC3339_PATTERN = Pattern.compile(RFC3339_REGEX);
    private final boolean dateOnly;
    private final int tzShift;
    private final long value;

    public DateTime(Date date, TimeZone timeZone) {
        this(false, date.getTime(), timeZone == null ? null : Integer.valueOf(timeZone.getOffset(date.getTime()) / 60000));
    }

    public DateTime(long j) {
        this(false, j, null);
    }

    public DateTime(Date date) {
        this(date.getTime());
    }

    public DateTime(long j, int i) {
        this(false, j, Integer.valueOf(i));
    }

    public DateTime(boolean z, long j, Integer num) {
        int offset;
        this.dateOnly = z;
        this.value = j;
        if (z) {
            offset = 0;
        } else {
            offset = num == null ? TimeZone.getDefault().getOffset(j) / 60000 : num.intValue();
        }
        this.tzShift = offset;
    }

    public DateTime(String str) {
        DateTime rfc3339 = parseRfc3339(str);
        this.dateOnly = rfc3339.dateOnly;
        this.value = rfc3339.value;
        this.tzShift = rfc3339.tzShift;
    }

    public static DateTime parseRfc3339(String str) {
        return parseRfc3339WithNanoSeconds(str).toDateTime();
    }

    public static SecondsAndNanos parseRfc3339ToSecondsAndNanos(String str) {
        return parseRfc3339WithNanoSeconds(str).toSecondsAndNanos();
    }

    private static Rfc3339ParseResult parseRfc3339WithNanoSeconds(String str) throws NumberFormatException {
        int i;
        int i2;
        int i3;
        int i4;
        Integer numValueOf;
        Matcher matcher = RFC3339_PATTERN.matcher(str);
        if (!matcher.matches()) {
            throw new NumberFormatException("Invalid date/time format: " + str);
        }
        int i5 = Integer.parseInt(matcher.group(1));
        int i6 = Integer.parseInt(matcher.group(2)) - 1;
        int i7 = Integer.parseInt(matcher.group(3));
        boolean z = matcher.group(4) != null;
        String strGroup = matcher.group(9);
        boolean z2 = strGroup != null;
        if (z2 && !z) {
            throw new NumberFormatException("Invalid date/time format, cannot specify time zone shift without specifying time: " + str);
        }
        if (z) {
            int i8 = Integer.parseInt(matcher.group(5));
            int i9 = Integer.parseInt(matcher.group(6));
            int i10 = Integer.parseInt(matcher.group(7));
            if (matcher.group(8) != null) {
                i4 = Integer.parseInt(com.google.common.base.Strings.padEnd(matcher.group(8).substring(1), 9, '0'));
                i2 = i9;
                i3 = i10;
            } else {
                i2 = i9;
                i3 = i10;
                i4 = 0;
            }
            i = i8;
        } else {
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(GMT);
        gregorianCalendar.clear();
        gregorianCalendar.set(i5, i6, i7, i, i2, i3);
        long timeInMillis = gregorianCalendar.getTimeInMillis();
        if (!z || !z2) {
            numValueOf = null;
        } else if (Character.toUpperCase(strGroup.charAt(0)) != 'Z') {
            int i11 = (Integer.parseInt(matcher.group(11)) * 60) + Integer.parseInt(matcher.group(12));
            if (matcher.group(10).charAt(0) == '-') {
                i11 = -i11;
            }
            timeInMillis -= i11 * 60000;
            numValueOf = Integer.valueOf(i11);
        } else {
            numValueOf = 0;
        }
        return new Rfc3339ParseResult(timeInMillis / 1000, i4, z, numValueOf);
    }

    private static void appendInt(StringBuilder sb, int i, int i2) {
        if (i < 0) {
            sb.append('-');
            i = -i;
        }
        int i3 = i;
        while (i3 > 0) {
            i3 /= 10;
            i2--;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            sb.append('0');
        }
        if (i != 0) {
            sb.append(i);
        }
    }

    public int getTimeZoneShift() {
        return this.tzShift;
    }

    public long getValue() {
        return this.value;
    }

    public boolean isDateOnly() {
        return this.dateOnly;
    }

    public String toStringRfc3339() {
        StringBuilder sb = new StringBuilder();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(GMT);
        gregorianCalendar.setTimeInMillis(this.value + (this.tzShift * 60000));
        appendInt(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        appendInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        appendInt(sb, gregorianCalendar.get(5), 2);
        if (!this.dateOnly) {
            sb.append('T');
            appendInt(sb, gregorianCalendar.get(11), 2);
            sb.append(':');
            appendInt(sb, gregorianCalendar.get(12), 2);
            sb.append(':');
            appendInt(sb, gregorianCalendar.get(13), 2);
            if (gregorianCalendar.isSet(14)) {
                sb.append('.');
                appendInt(sb, gregorianCalendar.get(14), 3);
            }
            int i = this.tzShift;
            if (i == 0) {
                sb.append(Matrix.MATRIX_TYPE_ZERO);
            } else {
                if (i > 0) {
                    sb.append('+');
                } else {
                    sb.append('-');
                    i = -i;
                }
                appendInt(sb, i / 60, 2);
                sb.append(':');
                appendInt(sb, i % 60, 2);
            }
        }
        return sb.toString();
    }

    public String toString() {
        return toStringRfc3339();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DateTime)) {
            return false;
        }
        DateTime dateTime = (DateTime) obj;
        return this.dateOnly == dateTime.dateOnly && this.value == dateTime.value && this.tzShift == dateTime.tzShift;
    }

    public int hashCode() {
        long[] jArr = new long[3];
        jArr[0] = this.value;
        jArr[1] = this.dateOnly ? serialVersionUID : 0L;
        jArr[2] = this.tzShift;
        return Arrays.hashCode(jArr);
    }

    public static final class SecondsAndNanos implements Serializable {
        private final int nanos;
        private final long seconds;

        private SecondsAndNanos(long j, int i) {
            this.seconds = j;
            this.nanos = i;
        }

        public static SecondsAndNanos ofSecondsAndNanos(long j, int i) {
            return new SecondsAndNanos(j, i);
        }

        public int getNanos() {
            return this.nanos;
        }

        public long getSeconds() {
            return this.seconds;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SecondsAndNanos secondsAndNanos = (SecondsAndNanos) obj;
            return this.seconds == secondsAndNanos.seconds && this.nanos == secondsAndNanos.nanos;
        }

        public int hashCode() {
            return java.util.Objects.hash(Long.valueOf(this.seconds), Integer.valueOf(this.nanos));
        }

        public String toString() {
            return String.format("Seconds: %d, Nanos: %d", Long.valueOf(this.seconds), Integer.valueOf(this.nanos));
        }
    }

    private static class Rfc3339ParseResult implements Serializable {
        private final int nanos;
        private final long seconds;
        private final boolean timeGiven;
        private final Integer tzShift;

        private Rfc3339ParseResult(long j, int i, boolean z, Integer num) {
            this.seconds = j;
            this.nanos = i;
            this.timeGiven = z;
            this.tzShift = num;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public DateTime toDateTime() {
            return new DateTime(!this.timeGiven, TimeUnit.SECONDS.toMillis(this.seconds) + TimeUnit.NANOSECONDS.toMillis(this.nanos), this.tzShift);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public SecondsAndNanos toSecondsAndNanos() {
            return new SecondsAndNanos(this.seconds, this.nanos);
        }
    }
}
