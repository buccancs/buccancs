package org.apache.commons.math.fraction;

import java.io.Serializable;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import org.apache.commons.math.exception.NullArgumentException;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/fraction/AbstractFormat.class */
public abstract class AbstractFormat extends NumberFormat implements Serializable {
    private static final long serialVersionUID = -6981118387974191891L;
    protected NumberFormat denominatorFormat;
    protected NumberFormat numeratorFormat;

    protected AbstractFormat() {
        this(getDefaultNumberFormat());
    }

    protected AbstractFormat(NumberFormat format) {
        this(format, (NumberFormat) format.clone());
    }

    protected AbstractFormat(NumberFormat numeratorFormat, NumberFormat denominatorFormat) {
        this.numeratorFormat = numeratorFormat;
        this.denominatorFormat = denominatorFormat;
    }

    protected static NumberFormat getDefaultNumberFormat() {
        return getDefaultNumberFormat(Locale.getDefault());
    }

    protected static NumberFormat getDefaultNumberFormat(Locale locale) {
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        nf.setMaximumFractionDigits(0);
        nf.setParseIntegerOnly(true);
        return nf;
    }

    protected static void parseAndIgnoreWhitespace(String source, ParsePosition pos) {
        parseNextCharacter(source, pos);
        pos.setIndex(pos.getIndex() - 1);
    }

    protected static char parseNextCharacter(String source, ParsePosition pos) {
        char c;
        int index = pos.getIndex();
        int n = source.length();
        char ret = 0;
        if (index < n) {
            do {
                int i = index;
                index++;
                c = source.charAt(i);
                if (!Character.isWhitespace(c)) {
                    break;
                }
            } while (index < n);
            pos.setIndex(index);
            if (index < n) {
                ret = c;
            }
        }
        return ret;
    }

    public NumberFormat getDenominatorFormat() {
        return this.denominatorFormat;
    }

    public void setDenominatorFormat(NumberFormat format) {
        if (format == null) {
            throw new NullArgumentException(LocalizedFormats.DENOMINATOR_FORMAT);
        }
        this.denominatorFormat = format;
    }

    public NumberFormat getNumeratorFormat() {
        return this.numeratorFormat;
    }

    public void setNumeratorFormat(NumberFormat format) {
        if (format == null) {
            throw new NullArgumentException(LocalizedFormats.NUMERATOR_FORMAT);
        }
        this.numeratorFormat = format;
    }

    @Override // java.text.NumberFormat
    public StringBuffer format(double value, StringBuffer buffer, FieldPosition position) {
        return format(Double.valueOf(value), buffer, position);
    }

    @Override // java.text.NumberFormat
    public StringBuffer format(long value, StringBuffer buffer, FieldPosition position) {
        return format(Long.valueOf(value), buffer, position);
    }
}
