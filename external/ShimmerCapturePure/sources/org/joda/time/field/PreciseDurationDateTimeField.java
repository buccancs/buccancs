package org.joda.time.field;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

/* loaded from: classes2.dex */
public abstract class PreciseDurationDateTimeField extends BaseDateTimeField {
    private static final long serialVersionUID = 5004523158306266035L;
    final long iUnitMillis;
    private final DurationField iUnitField;

    public PreciseDurationDateTimeField(DateTimeFieldType dateTimeFieldType, DurationField durationField) {
        super(dateTimeFieldType);
        if (!durationField.isPrecise()) {
            throw new IllegalArgumentException("Unit duration field must be precise");
        }
        long unitMillis = durationField.getUnitMillis();
        this.iUnitMillis = unitMillis;
        if (unitMillis < 1) {
            throw new IllegalArgumentException("The unit milliseconds must be at least 1");
        }
        this.iUnitField = durationField;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public DurationField getDurationField() {
        return this.iUnitField;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int getMinimumValue() {
        return 0;
    }

    public final long getUnitMillis() {
        return this.iUnitMillis;
    }

    @Override // org.joda.time.DateTimeField
    public boolean isLenient() {
        return false;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long set(long j, int i) {
        FieldUtils.verifyValueBounds(this, i, getMinimumValue(), getMaximumValueForSet(j, i));
        return j + ((i - get(j)) * this.iUnitMillis);
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long roundFloor(long j) {
        long j2;
        if (j >= 0) {
            j2 = j % this.iUnitMillis;
        } else {
            long j3 = j + 1;
            j2 = this.iUnitMillis;
            j = j3 - (j3 % j2);
        }
        return j - j2;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long roundCeiling(long j) {
        if (j > 0) {
            long j2 = j - 1;
            long j3 = this.iUnitMillis;
            return (j2 - (j2 % j3)) + j3;
        }
        return j - (j % this.iUnitMillis);
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long remainder(long j) {
        if (j >= 0) {
            return j % this.iUnitMillis;
        }
        long j2 = this.iUnitMillis;
        return (((j + 1) % j2) + j2) - 1;
    }

    protected int getMaximumValueForSet(long j, int i) {
        return getMaximumValue(j);
    }
}
