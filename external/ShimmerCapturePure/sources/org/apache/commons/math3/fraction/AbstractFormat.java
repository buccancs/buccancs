package org.apache.commons.math3.fraction;

import java.io.Serializable;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public abstract class AbstractFormat extends NumberFormat implements Serializable {
    private static final long serialVersionUID = -6981118387974191891L;
    private NumberFormat denominatorFormat;
    private NumberFormat numeratorFormat;

    protected AbstractFormat() {
        this(getDefaultNumberFormat());
    }

    protected AbstractFormat(NumberFormat numberFormat) {
        this(numberFormat, (NumberFormat) numberFormat.clone());
    }

    protected AbstractFormat(NumberFormat numberFormat, NumberFormat numberFormat2) {
        this.numeratorFormat = numberFormat;
        this.denominatorFormat = numberFormat2;
    }

    protected static NumberFormat getDefaultNumberFormat() {
        return getDefaultNumberFormat(Locale.getDefault());
    }

    protected static NumberFormat getDefaultNumberFormat(Locale locale) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance(locale);
        numberInstance.setMaximumFractionDigits(0);
        numberInstance.setParseIntegerOnly(true);
        return numberInstance;
    }

    protected static void parseAndIgnoreWhitespace(String str, ParsePosition parsePosition) {
        parseNextCharacter(str, parsePosition);
        parsePosition.setIndex(parsePosition.getIndex() - 1);
    }

    protected static char parseNextCharacter(String str, ParsePosition parsePosition) {
        int i;
        char cCharAt;
        int index = parsePosition.getIndex();
        int length = str.length();
        if (index < length) {
            while (true) {
                i = index + 1;
                cCharAt = str.charAt(index);
                if (!Character.isWhitespace(cCharAt) || i >= length) {
                    break;
                }
                index = i;
            }
            parsePosition.setIndex(i);
            if (i < length) {
                return cCharAt;
            }
        }
        return (char) 0;
    }

    public NumberFormat getDenominatorFormat() {
        return this.denominatorFormat;
    }

    public void setDenominatorFormat(NumberFormat numberFormat) {
        if (numberFormat == null) {
            throw new NullArgumentException(LocalizedFormats.DENOMINATOR_FORMAT, new Object[0]);
        }
        this.denominatorFormat = numberFormat;
    }

    public NumberFormat getNumeratorFormat() {
        return this.numeratorFormat;
    }

    public void setNumeratorFormat(NumberFormat numberFormat) {
        if (numberFormat == null) {
            throw new NullArgumentException(LocalizedFormats.NUMERATOR_FORMAT, new Object[0]);
        }
        this.numeratorFormat = numberFormat;
    }

    @Override // java.text.NumberFormat
    public StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return format(Double.valueOf(d), stringBuffer, fieldPosition);
    }

    @Override // java.text.NumberFormat
    public StringBuffer format(long j, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return format(Long.valueOf(j), stringBuffer, fieldPosition);
    }
}
