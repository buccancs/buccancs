package org.joda.time.chrono;

import org.joda.time.Chronology;

/* loaded from: classes2.dex */
abstract class BasicFixedMonthChronology extends BasicChronology {
    static final long MILLIS_PER_MONTH = 2592000000L;
    static final long MILLIS_PER_YEAR = 31557600000L;
    static final int MONTH_LENGTH = 30;
    private static final long serialVersionUID = 261387371998L;

    BasicFixedMonthChronology(Chronology chronology, Object obj, int i) {
        super(chronology, obj, i);
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    long getAverageMillisPerMonth() {
        return MILLIS_PER_MONTH;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    long getAverageMillisPerYear() {
        return MILLIS_PER_YEAR;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    long getAverageMillisPerYearDividedByTwo() {
        return 15778800000L;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    int getDaysInMonthMax() {
        return 30;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    int getDaysInMonthMax(int i) {
        return i != 13 ? 30 : 6;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    int getMaxMonth() {
        return 13;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    long getTotalMillisByYearMonth(int i, int i2) {
        return (i2 - 1) * MILLIS_PER_MONTH;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    boolean isLeapYear(int i) {
        return (i & 3) == 3;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    long setYear(long j, int i) {
        int dayOfYear = getDayOfYear(j, getYear(j));
        int millisOfDay = getMillisOfDay(j);
        if (dayOfYear > 365 && !isLeapYear(i)) {
            dayOfYear--;
        }
        return getYearMonthDayMillis(i, 1, dayOfYear) + millisOfDay;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    long getYearDifference(long j, long j2) {
        int year = getYear(j);
        int year2 = getYear(j2);
        long yearMillis = j - getYearMillis(year);
        int i = year - year2;
        if (yearMillis < j2 - getYearMillis(year2)) {
            i--;
        }
        return i;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    int getDayOfMonth(long j) {
        return ((getDayOfYear(j) - 1) % 30) + 1;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    int getDaysInYearMonth(int i, int i2) {
        if (i2 != 13) {
            return 30;
        }
        return isLeapYear(i) ? 6 : 5;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    int getMonthOfYear(long j) {
        return ((getDayOfYear(j) - 1) / 30) + 1;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    int getMonthOfYear(long j, int i) {
        return ((int) ((j - getYearMillis(i)) / MILLIS_PER_MONTH)) + 1;
    }
}
