package org.apache.commons.lang.time;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

/* loaded from: classes5.dex */
public class DateUtils {
    public static final int MILLIS_IN_DAY = 86400000;
    public static final int MILLIS_IN_HOUR = 3600000;
    public static final int MILLIS_IN_MINUTE = 60000;
    public static final int MILLIS_IN_SECOND = 1000;
    public static final long MILLIS_PER_DAY = 86400000;
    public static final long MILLIS_PER_HOUR = 3600000;
    public static final long MILLIS_PER_MINUTE = 60000;
    public static final long MILLIS_PER_SECOND = 1000;
    public static final int RANGE_MONTH_MONDAY = 6;
    public static final int RANGE_MONTH_SUNDAY = 5;
    public static final int RANGE_WEEK_CENTER = 4;
    public static final int RANGE_WEEK_MONDAY = 2;
    public static final int RANGE_WEEK_RELATIVE = 3;
    public static final int RANGE_WEEK_SUNDAY = 1;
    public static final int SEMI_MONTH = 1001;
    public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");
    private static final int MODIFY_CEILING = 2;
    private static final int MODIFY_ROUND = 1;
    private static final int MODIFY_TRUNCATE = 0;
    private static final int[][] fields = {new int[]{14}, new int[]{13}, new int[]{12}, new int[]{11, 10}, new int[]{5, 5, 9}, new int[]{2, 1001}, new int[]{1}, new int[]{0}};

    public static boolean isSameDay(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return isSameDay(calendar, calendar2);
    }

    public static boolean isSameDay(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
    }

    public static boolean isSameInstant(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return date.getTime() == date2.getTime();
    }

    public static boolean isSameInstant(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.getTime().getTime() == calendar2.getTime().getTime();
    }

    public static boolean isSameLocalTime(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.get(14) == calendar2.get(14) && calendar.get(13) == calendar2.get(13) && calendar.get(12) == calendar2.get(12) && calendar.get(10) == calendar2.get(10) && calendar.get(6) == calendar2.get(6) && calendar.get(1) == calendar2.get(1) && calendar.get(0) == calendar2.get(0) && calendar.getClass() == calendar2.getClass();
    }

    public static Date parseDate(String str, String[] strArr) throws ParseException {
        return parseDateWithLeniency(str, strArr, true);
    }

    public static Date parseDateStrictly(String str, String[] strArr) throws ParseException {
        return parseDateWithLeniency(str, strArr, false);
    }

    private static Date parseDateWithLeniency(String str, String[] strArr, boolean z) throws ParseException {
        String strReformatTimezone;
        if (str == null || strArr == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.setLenient(z);
        ParsePosition parsePosition = new ParsePosition(0);
        for (int i = 0; i < strArr.length; i++) {
            String strSubstring = strArr[i];
            if (strSubstring.endsWith("ZZ")) {
                strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
            }
            simpleDateFormat.applyPattern(strSubstring);
            parsePosition.setIndex(0);
            if (strArr[i].endsWith("ZZ")) {
                int iIndexOfSignChars = indexOfSignChars(str, 0);
                strReformatTimezone = str;
                while (iIndexOfSignChars >= 0) {
                    strReformatTimezone = reformatTimezone(strReformatTimezone, iIndexOfSignChars);
                    iIndexOfSignChars = indexOfSignChars(strReformatTimezone, iIndexOfSignChars + 1);
                }
            } else {
                strReformatTimezone = str;
            }
            Date date = simpleDateFormat.parse(strReformatTimezone, parsePosition);
            if (date != null && parsePosition.getIndex() == strReformatTimezone.length()) {
                return date;
            }
        }
        throw new ParseException(new StringBuffer("Unable to parse the date: ").append(str).toString(), -1);
    }

    private static int indexOfSignChars(String str, int i) {
        int iIndexOf = StringUtils.indexOf(str, '+', i);
        return iIndexOf < 0 ? StringUtils.indexOf(str, '-', i) : iIndexOf;
    }

    private static String reformatTimezone(String str, int i) {
        int i2;
        if (i < 0 || (i2 = i + 5) >= str.length() || !Character.isDigit(str.charAt(i + 1)) || !Character.isDigit(str.charAt(i + 2))) {
            return str;
        }
        int i3 = i + 3;
        if (str.charAt(i3) != ':') {
            return str;
        }
        int i4 = i + 4;
        return (Character.isDigit(str.charAt(i4)) && Character.isDigit(str.charAt(i2))) ? new StringBuffer().append(str.substring(0, i3)).append(str.substring(i4)).toString() : str;
    }

    public static Date addYears(Date date, int i) {
        return add(date, 1, i);
    }

    public static Date addMonths(Date date, int i) {
        return add(date, 2, i);
    }

    public static Date addWeeks(Date date, int i) {
        return add(date, 3, i);
    }

    public static Date addDays(Date date, int i) {
        return add(date, 5, i);
    }

    public static Date addHours(Date date, int i) {
        return add(date, 11, i);
    }

    public static Date addMinutes(Date date, int i) {
        return add(date, 12, i);
    }

    public static Date addSeconds(Date date, int i) {
        return add(date, 13, i);
    }

    public static Date addMilliseconds(Date date, int i) {
        return add(date, 14, i);
    }

    public static Date add(Date date, int i, int i2) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(i, i2);
        return calendar.getTime();
    }

    public static Date setYears(Date date, int i) {
        return set(date, 1, i);
    }

    public static Date setMonths(Date date, int i) {
        return set(date, 2, i);
    }

    public static Date setDays(Date date, int i) {
        return set(date, 5, i);
    }

    public static Date setHours(Date date, int i) {
        return set(date, 11, i);
    }

    public static Date setMinutes(Date date, int i) {
        return set(date, 12, i);
    }

    public static Date setSeconds(Date date, int i) {
        return set(date, 13, i);
    }

    public static Date setMilliseconds(Date date, int i) {
        return set(date, 14, i);
    }

    private static Date set(Date date, int i, int i2) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.setTime(date);
        calendar.set(i, i2);
        return calendar.getTime();
    }

    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date round(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i, 1);
        return calendar.getTime();
    }

    public static Calendar round(Calendar calendar, int i) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, i, 1);
        return calendar2;
    }

    public static Date round(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return round((Date) obj, i);
        }
        if (obj instanceof Calendar) {
            return round((Calendar) obj, i).getTime();
        }
        throw new ClassCastException(new StringBuffer("Could not round ").append(obj).toString());
    }

    public static Date truncate(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i, 0);
        return calendar.getTime();
    }

    public static Calendar truncate(Calendar calendar, int i) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, i, 0);
        return calendar2;
    }

    public static Date truncate(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return truncate((Date) obj, i);
        }
        if (obj instanceof Calendar) {
            return truncate((Calendar) obj, i).getTime();
        }
        throw new ClassCastException(new StringBuffer("Could not truncate ").append(obj).toString());
    }

    public static Date ceiling(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i, 2);
        return calendar.getTime();
    }

    public static Calendar ceiling(Calendar calendar, int i) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, i, 2);
        return calendar2;
    }

    public static Date ceiling(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return ceiling((Date) obj, i);
        }
        if (obj instanceof Calendar) {
            return ceiling((Calendar) obj, i).getTime();
        }
        throw new ClassCastException(new StringBuffer("Could not find ceiling of for type: ").append(obj.getClass()).toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x00c3, code lost:
    
        if (r17 == 9) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00c5, code lost:
    
        if (r17 == 1001) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00cc, code lost:
    
        if (r10[0] != 5) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00ce, code lost:
    
        r4 = r16.get(5);
        r6 = r4 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00d4, code lost:
    
        if (r6 < 15) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00d6, code lost:
    
        r4 = r4 - 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00d9, code lost:
    
        r4 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00db, code lost:
    
        if (r4 <= 7) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00dd, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00df, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00e0, code lost:
    
        r11 = r4;
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00e8, code lost:
    
        if (r10[0] != 11) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00ea, code lost:
    
        r4 = r16.get(11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00f0, code lost:
    
        if (r4 < 12) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00f2, code lost:
    
        r4 = r4 - 12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00f5, code lost:
    
        if (r4 < 6) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00f7, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00f9, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00fa, code lost:
    
        r11 = r4;
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00fd, code lost:
    
        r4 = false;
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00ff, code lost:
    
        if (r4 != false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0101, code lost:
    
        r4 = r16.getActualMinimum(r9[r5][0]);
        r8 = r16.getActualMaximum(r9[r5][0]);
        r11 = r16.get(r9[r5][0]) - r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x011e, code lost:
    
        if (r11 <= ((r8 - r4) / 2)) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0120, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0122, code lost:
    
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0123, code lost:
    
        r6 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0124, code lost:
    
        if (r11 == 0) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0126, code lost:
    
        r4 = r9[r5][0];
        r16.set(r4, r16.get(r4) - r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0135, code lost:
    
        r5 = r5 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void modify(java.util.Calendar r16, int r17, int r18) {
        /*
            Method dump skipped, instructions count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.DateUtils.modify(java.util.Calendar, int, int):void");
    }

    public static Iterator iterator(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return iterator(calendar, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007c A[LOOP:0: B:29:0x0076->B:31:0x007c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0086 A[LOOP:1: B:32:0x0080->B:34:0x0086, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Iterator iterator(java.util.Calendar r8, int r9) {
        /*
            if (r8 == 0) goto L90
            r0 = -1
            r1 = 2
            r2 = 5
            r3 = 1
            r4 = 7
            switch(r9) {
                case 1: goto L41;
                case 2: goto L41;
                case 3: goto L41;
                case 4: goto L41;
                case 5: goto L25;
                case 6: goto L25;
                default: goto La;
            }
        La:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            java.lang.String r1 = "The range style "
            r0.<init>(r1)
            java.lang.StringBuffer r9 = r0.append(r9)
            java.lang.String r0 = " is not valid."
            java.lang.StringBuffer r9 = r9.append(r0)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L25:
            java.util.Calendar r8 = truncate(r8, r1)
            java.lang.Object r5 = r8.clone()
            java.util.Calendar r5 = (java.util.Calendar) r5
            r5.add(r1, r3)
            r5.add(r2, r0)
            r6 = 6
            if (r9 != r6) goto L3c
            r6 = r5
            r5 = r8
        L3a:
            r8 = 1
            goto L66
        L3c:
            r6 = r5
            r1 = 1
            r5 = r8
            r8 = 7
            goto L66
        L41:
            java.util.Calendar r5 = truncate(r8, r2)
            java.util.Calendar r6 = truncate(r8, r2)
            if (r9 == r1) goto L3a
            r1 = 3
            if (r9 == r1) goto L60
            r7 = 4
            if (r9 == r7) goto L54
            r8 = 7
            r1 = 1
            goto L66
        L54:
            int r9 = r8.get(r4)
            int r9 = r9 - r1
            int r8 = r8.get(r4)
            int r8 = r8 + r1
            r1 = r9
            goto L66
        L60:
            int r1 = r8.get(r4)
            int r8 = r1 + (-1)
        L66:
            if (r1 >= r3) goto L6a
            int r1 = r1 + 7
        L6a:
            if (r1 <= r4) goto L6e
            int r1 = r1 + (-7)
        L6e:
            if (r8 >= r3) goto L72
            int r8 = r8 + 7
        L72:
            if (r8 <= r4) goto L76
            int r8 = r8 + (-7)
        L76:
            int r9 = r5.get(r4)
            if (r9 == r1) goto L80
            r5.add(r2, r0)
            goto L76
        L80:
            int r9 = r6.get(r4)
            if (r9 == r8) goto L8a
            r6.add(r2, r3)
            goto L80
        L8a:
            org.apache.commons.lang.time.DateUtils$DateIterator r8 = new org.apache.commons.lang.time.DateUtils$DateIterator
            r8.<init>(r5, r6)
            return r8
        L90:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "The date must not be null"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.DateUtils.iterator(java.util.Calendar, int):java.util.Iterator");
    }

    public static Iterator iterator(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return iterator((Date) obj, i);
        }
        if (obj instanceof Calendar) {
            return iterator((Calendar) obj, i);
        }
        throw new ClassCastException(new StringBuffer("Could not iterate based on ").append(obj).toString());
    }

    public static long getFragmentInMilliseconds(Date date, int i) {
        return getFragment(date, i, 14);
    }

    public static long getFragmentInSeconds(Date date, int i) {
        return getFragment(date, i, 13);
    }

    public static long getFragmentInMinutes(Date date, int i) {
        return getFragment(date, i, 12);
    }

    public static long getFragmentInHours(Date date, int i) {
        return getFragment(date, i, 11);
    }

    public static long getFragmentInDays(Date date, int i) {
        return getFragment(date, i, 6);
    }

    public static long getFragmentInMilliseconds(Calendar calendar, int i) {
        return getFragment(calendar, i, 14);
    }

    public static long getFragmentInSeconds(Calendar calendar, int i) {
        return getFragment(calendar, i, 13);
    }

    public static long getFragmentInMinutes(Calendar calendar, int i) {
        return getFragment(calendar, i, 12);
    }

    public static long getFragmentInHours(Calendar calendar, int i) {
        return getFragment(calendar, i, 11);
    }

    public static long getFragmentInDays(Calendar calendar, int i) {
        return getFragment(calendar, i, 6);
    }

    private static long getFragment(Date date, int i, int i2) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFragment(calendar, i, i2);
    }

    private static long getFragment(Calendar calendar, int i, int i2) {
        long j;
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        long millisPerUnit = getMillisPerUnit(i2);
        if (i == 1) {
            j = (calendar.get(6) * 86400000) / millisPerUnit;
        } else {
            j = i != 2 ? 0L : (calendar.get(5) * 86400000) / millisPerUnit;
        }
        if (i == 1 || i == 2 || i == 5 || i == 6) {
            j += (calendar.get(11) * 3600000) / millisPerUnit;
        } else {
            switch (i) {
                case 11:
                    break;
                case 12:
                    j += (calendar.get(13) * 1000) / millisPerUnit;
                case 13:
                    return j + (calendar.get(14) / millisPerUnit);
                case 14:
                    return j;
                default:
                    throw new IllegalArgumentException(new StringBuffer("The fragment ").append(i).append(" is not supported").toString());
            }
        }
        j += (calendar.get(12) * 60000) / millisPerUnit;
        j += (calendar.get(13) * 1000) / millisPerUnit;
        return j + (calendar.get(14) / millisPerUnit);
    }

    public static boolean truncatedEquals(Calendar calendar, Calendar calendar2, int i) {
        return truncatedCompareTo(calendar, calendar2, i) == 0;
    }

    public static boolean truncatedEquals(Date date, Date date2, int i) {
        return truncatedCompareTo(date, date2, i) == 0;
    }

    public static int truncatedCompareTo(Calendar calendar, Calendar calendar2, int i) {
        return truncate(calendar, i).getTime().compareTo(truncate(calendar2, i).getTime());
    }

    public static int truncatedCompareTo(Date date, Date date2, int i) {
        return truncate(date, i).compareTo(truncate(date2, i));
    }

    private static long getMillisPerUnit(int i) {
        if (i == 5 || i == 6) {
            return 86400000L;
        }
        switch (i) {
            case 11:
                return 3600000L;
            case 12:
                return 60000L;
            case 13:
                return 1000L;
            case 14:
                return 1L;
            default:
                throw new IllegalArgumentException(new StringBuffer("The unit ").append(i).append(" cannot be represented is milleseconds").toString());
        }
    }

    static class DateIterator implements Iterator {
        private final Calendar endFinal;
        private final Calendar spot;

        DateIterator(Calendar calendar, Calendar calendar2) {
            this.endFinal = calendar2;
            this.spot = calendar;
            calendar.add(5, -1);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.spot.before(this.endFinal);
        }

        @Override // java.util.Iterator
        public Object next() {
            if (this.spot.equals(this.endFinal)) {
                throw new NoSuchElementException();
            }
            this.spot.add(5, 1);
            return this.spot.clone();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
