package org.apache.commons.math.fraction;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/fraction/BigFractionFormat.class */
public class BigFractionFormat extends AbstractFormat implements Serializable {
    private static final long serialVersionUID = -2932167925527338976L;

    public BigFractionFormat() {
    }

    public BigFractionFormat(NumberFormat format) {
        super(format);
    }

    public BigFractionFormat(NumberFormat numeratorFormat, NumberFormat denominatorFormat) {
        super(numeratorFormat, denominatorFormat);
    }

    public static Locale[] getAvailableLocales() {
        return NumberFormat.getAvailableLocales();
    }

    public static String formatBigFraction(BigFraction f) {
        return getImproperInstance().format(f);
    }

    public static BigFractionFormat getImproperInstance() {
        return getImproperInstance(Locale.getDefault());
    }

    public static BigFractionFormat getImproperInstance(Locale locale) {
        return new BigFractionFormat(getDefaultNumberFormat(locale));
    }

    public static BigFractionFormat getProperInstance() {
        return getProperInstance(Locale.getDefault());
    }

    public static BigFractionFormat getProperInstance(Locale locale) {
        return new ProperBigFractionFormat(getDefaultNumberFormat(locale));
    }

    public StringBuffer format(BigFraction BigFraction, StringBuffer toAppendTo, FieldPosition pos) {
        pos.setBeginIndex(0);
        pos.setEndIndex(0);
        getNumeratorFormat().format(BigFraction.getNumerator(), toAppendTo, pos);
        toAppendTo.append(" / ");
        getDenominatorFormat().format(BigFraction.getDenominator(), toAppendTo, pos);
        return toAppendTo;
    }

    @Override // java.text.NumberFormat, java.text.Format
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        StringBuffer ret;
        if (obj instanceof BigFraction) {
            ret = format((BigFraction) obj, toAppendTo, pos);
        } else if (obj instanceof BigInteger) {
            ret = format(new BigFraction((BigInteger) obj), toAppendTo, pos);
        } else if (obj instanceof Number) {
            ret = format(new BigFraction(((Number) obj).doubleValue()), toAppendTo, pos);
        } else {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.CANNOT_FORMAT_OBJECT_TO_FRACTION, new Object[0]);
        }
        return ret;
    }

    @Override // java.text.NumberFormat
    public BigFraction parse(String source) throws ParseException {
        ParsePosition parsePosition = new ParsePosition(0);
        BigFraction result = parse(source, parsePosition);
        if (parsePosition.getIndex() == 0) {
            throw MathRuntimeException.createParseException(parsePosition.getErrorIndex(), LocalizedFormats.UNPARSEABLE_FRACTION_NUMBER, source);
        }
        return result;
    }

    @Override // java.text.NumberFormat
    public BigFraction parse(String source, ParsePosition pos) {
        int initialIndex = pos.getIndex();
        parseAndIgnoreWhitespace(source, pos);
        BigInteger num = parseNextBigInteger(source, pos);
        if (num == null) {
            pos.setIndex(initialIndex);
            return null;
        }
        int startIndex = pos.getIndex();
        char c = parseNextCharacter(source, pos);
        switch (c) {
            case 0:
                return new BigFraction(num);
            case '/':
                parseAndIgnoreWhitespace(source, pos);
                BigInteger den = parseNextBigInteger(source, pos);
                if (den == null) {
                    pos.setIndex(initialIndex);
                    return null;
                }
                return new BigFraction(num, den);
            default:
                pos.setIndex(initialIndex);
                pos.setErrorIndex(startIndex);
                return null;
        }
    }

    protected BigInteger parseNextBigInteger(String source, ParsePosition pos) {
        int start = pos.getIndex();
        int end = source.charAt(start) == '-' ? start + 1 : start;
        while (end < source.length() && Character.isDigit(source.charAt(end))) {
            end++;
        }
        try {
            BigInteger n = new BigInteger(source.substring(start, end));
            pos.setIndex(end);
            return n;
        } catch (NumberFormatException e) {
            pos.setErrorIndex(start);
            return null;
        }
    }
}
