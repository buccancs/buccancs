package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.AssembledChronology;
import org.joda.time.field.SkipDateTimeField;

/* loaded from: classes2.dex */
public final class CopticChronology extends BasicFixedMonthChronology {
    public static final int AM = 1;
    private static final int MAX_YEAR = 292272708;
    private static final int MIN_YEAR = -292269337;
    private static final long serialVersionUID = -5972804258688333942L;
    private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("AM");
    private static final ConcurrentHashMap<DateTimeZone, CopticChronology[]> cCache = new ConcurrentHashMap<>();
    private static final CopticChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);

    CopticChronology(Chronology chronology, Object obj, int i) {
        super(chronology, obj, i);
    }

    public static CopticChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    public static CopticChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), 4);
    }

    public static CopticChronology getInstance(DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, 4);
    }

    public static CopticChronology getInstance(DateTimeZone dateTimeZone, int i) {
        CopticChronology copticChronology;
        CopticChronology[] copticChronologyArrPutIfAbsent;
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        ConcurrentHashMap<DateTimeZone, CopticChronology[]> concurrentHashMap = cCache;
        CopticChronology[] copticChronologyArr = concurrentHashMap.get(dateTimeZone);
        if (copticChronologyArr == null && (copticChronologyArrPutIfAbsent = concurrentHashMap.putIfAbsent(dateTimeZone, (copticChronologyArr = new CopticChronology[7]))) != null) {
            copticChronologyArr = copticChronologyArrPutIfAbsent;
        }
        int i2 = i - 1;
        try {
            CopticChronology copticChronology2 = copticChronologyArr[i2];
            if (copticChronology2 == null) {
                synchronized (copticChronologyArr) {
                    copticChronology2 = copticChronologyArr[i2];
                    if (copticChronology2 == null) {
                        if (dateTimeZone == DateTimeZone.UTC) {
                            CopticChronology copticChronology3 = new CopticChronology(null, null, i);
                            copticChronology = new CopticChronology(LimitChronology.getInstance(copticChronology3, new DateTime(1, 1, 1, 0, 0, 0, 0, copticChronology3), null), null, i);
                        } else {
                            copticChronology = new CopticChronology(ZonedChronology.getInstance(getInstance(DateTimeZone.UTC, i), dateTimeZone), null, i);
                        }
                        copticChronologyArr[i2] = copticChronology;
                        copticChronology2 = copticChronology;
                    }
                }
            }
            return copticChronology2;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("Invalid min days in first week: " + i);
        }
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    long getApproxMillisAtEpochDividedByTwo() {
        return 26607895200000L;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    int getMaxYear() {
        return MAX_YEAR;
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    int getMinYear() {
        return MIN_YEAR;
    }

    @Override // org.joda.time.chrono.BaseChronology, org.joda.time.Chronology
    public Chronology withUTC() {
        return INSTANCE_UTC;
    }

    private Object readResolve() {
        Chronology base = getBase();
        int minimumDaysInFirstWeek = getMinimumDaysInFirstWeek();
        if (minimumDaysInFirstWeek == 0) {
            minimumDaysInFirstWeek = 4;
        }
        return getInstance(base == null ? DateTimeZone.UTC : base.getZone(), minimumDaysInFirstWeek);
    }

    @Override // org.joda.time.chrono.BaseChronology, org.joda.time.Chronology
    public Chronology withZone(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        return dateTimeZone == getZone() ? this : getInstance(dateTimeZone);
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    boolean isLeapDay(long j) {
        return dayOfMonth().get(j) == 6 && monthOfYear().isLeap(j);
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    long calculateFirstDayOfYearMillis(int i) {
        int i2;
        int i3 = i - 1687;
        if (i3 <= 0) {
            i2 = (i - 1684) >> 2;
        } else {
            int i4 = i3 >> 2;
            i2 = !isLeapYear(i) ? i4 + 1 : i4;
        }
        return (((i3 * 365) + i2) * 86400000) + 21859200000L;
    }

    @Override // org.joda.time.chrono.BasicChronology, org.joda.time.chrono.AssembledChronology
    protected void assemble(AssembledChronology.Fields fields) {
        if (getBase() == null) {
            super.assemble(fields);
            fields.year = new SkipDateTimeField(this, fields.year);
            fields.weekyear = new SkipDateTimeField(this, fields.weekyear);
            fields.era = ERA_FIELD;
            fields.monthOfYear = new BasicMonthOfYearDateTimeField(this, 13);
            fields.months = fields.monthOfYear.getDurationField();
        }
    }
}
