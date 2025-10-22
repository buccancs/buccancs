package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.AssembledChronology;

/* loaded from: classes2.dex */
public final class GregorianChronology extends BasicGJChronology {
    private static final int DAYS_0000_TO_1970 = 719527;
    private static final int MAX_YEAR = 292278993;
    private static final long MILLIS_PER_MONTH = 2629746000L;
    private static final long MILLIS_PER_YEAR = 31556952000L;
    private static final int MIN_YEAR = -292275054;
    private static final long serialVersionUID = -861407383323710522L;
    private static final ConcurrentHashMap<DateTimeZone, GregorianChronology[]> cCache = new ConcurrentHashMap<>();
    private static final GregorianChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);

    private GregorianChronology(Chronology chronology, Object obj, int i) {
        super(chronology, obj, i);
    }

    public static GregorianChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    public static GregorianChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), 4);
    }

    public static GregorianChronology getInstance(DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, 4);
    }

    public static GregorianChronology getInstance(DateTimeZone dateTimeZone, int i) {
        GregorianChronology gregorianChronology;
        GregorianChronology[] gregorianChronologyArrPutIfAbsent;
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        ConcurrentHashMap<DateTimeZone, GregorianChronology[]> concurrentHashMap = cCache;
        GregorianChronology[] gregorianChronologyArr = concurrentHashMap.get(dateTimeZone);
        if (gregorianChronologyArr == null && (gregorianChronologyArrPutIfAbsent = concurrentHashMap.putIfAbsent(dateTimeZone, (gregorianChronologyArr = new GregorianChronology[7]))) != null) {
            gregorianChronologyArr = gregorianChronologyArrPutIfAbsent;
        }
        int i2 = i - 1;
        try {
            GregorianChronology gregorianChronology2 = gregorianChronologyArr[i2];
            if (gregorianChronology2 == null) {
                synchronized (gregorianChronologyArr) {
                    gregorianChronology2 = gregorianChronologyArr[i2];
                    if (gregorianChronology2 == null) {
                        if (dateTimeZone == DateTimeZone.UTC) {
                            gregorianChronology = new GregorianChronology(null, null, i);
                        } else {
                            gregorianChronology = new GregorianChronology(ZonedChronology.getInstance(getInstance(DateTimeZone.UTC, i), dateTimeZone), null, i);
                        }
                        gregorianChronologyArr[i2] = gregorianChronology;
                        gregorianChronology2 = gregorianChronology;
                    }
                }
            }
            return gregorianChronology2;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("Invalid min days in first week: " + i);
        }
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    long getApproxMillisAtEpochDividedByTwo() {
        return 31083597720000L;
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
        return 15778476000L;
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

    @Override // org.joda.time.chrono.BasicChronology, org.joda.time.chrono.AssembledChronology
    protected void assemble(AssembledChronology.Fields fields) {
        if (getBase() == null) {
            super.assemble(fields);
        }
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    boolean isLeapYear(int i) {
        return (i & 3) == 0 && (i % 100 != 0 || i % 400 == 0);
    }

    @Override
        // org.joda.time.chrono.BasicChronology
    long calculateFirstDayOfYearMillis(int i) {
        int i2;
        int i3 = i / 100;
        if (i < 0) {
            i2 = ((((i + 3) >> 2) - i3) + ((i3 + 3) >> 2)) - 1;
        } else {
            i2 = ((i >> 2) - i3) + (i3 >> 2);
            if (isLeapYear(i)) {
                i2--;
            }
        }
        return ((i * 365) + (i2 - DAYS_0000_TO_1970)) * 86400000;
    }
}
