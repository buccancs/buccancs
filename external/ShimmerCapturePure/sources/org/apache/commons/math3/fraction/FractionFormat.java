package org.apache.commons.math3.fraction;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public class FractionFormat extends AbstractFormat {
    private static final long serialVersionUID = 3008655719530972611L;

    public FractionFormat() {
    }

    public FractionFormat(NumberFormat numberFormat) {
        super(numberFormat);
    }

    public FractionFormat(NumberFormat numberFormat, NumberFormat numberFormat2) {
        super(numberFormat, numberFormat2);
    }

    public static Locale[] getAvailableLocales() {
        return NumberFormat.getAvailableLocales();
    }

    public static String formatFraction(Fraction fraction) {
        return getImproperInstance().format(fraction);
    }

    public static FractionFormat getImproperInstance() {
        return getImproperInstance(Locale.getDefault());
    }

    public static FractionFormat getImproperInstance(Locale locale) {
        return new FractionFormat(getDefaultNumberFormat(locale));
    }

    public static FractionFormat getProperInstance() {
        return getProperInstance(Locale.getDefault());
    }

    public static FractionFormat getProperInstance(Locale locale) {
        return new ProperFractionFormat(getDefaultNumberFormat(locale));
    }

    protected static NumberFormat getDefaultNumberFormat() {
        return getDefaultNumberFormat(Locale.getDefault());
    }

    public StringBuffer format(Fraction fraction, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        getNumeratorFormat().format(fraction.getNumerator(), stringBuffer, fieldPosition);
        stringBuffer.append(" / ");
        getDenominatorFormat().format(fraction.getDenominator(), stringBuffer, fieldPosition);
        return stringBuffer;
    }

    @Override // java.text.NumberFormat, java.text.Format
    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) throws FractionConversionException, MathIllegalArgumentException {
        if (obj instanceof Fraction) {
            return format((Fraction) obj, stringBuffer, fieldPosition);
        }
        if (obj instanceof Number) {
            return format(new Fraction(((Number) obj).doubleValue()), stringBuffer, fieldPosition);
        }
        throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_FORMAT_OBJECT_TO_FRACTION, new Object[0]);
    }

    @Override // java.text.NumberFormat
    public Fraction parse(String str) throws MathParseException {
        ParsePosition parsePosition = new ParsePosition(0);
        Fraction fraction = parse(str, parsePosition);
        if (parsePosition.getIndex() != 0) {
            return fraction;
        }
        throw new MathParseException(str, parsePosition.getErrorIndex(), Fraction.class);
    }

    @Override // java.text.NumberFormat
    public Fraction parse(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        parseAndIgnoreWhitespace(str, parsePosition);
        Number number = getNumeratorFormat().parse(str, parsePosition);
        if (number == null) {
            parsePosition.setIndex(index);
            return null;
        }
        int index2 = parsePosition.getIndex();
        char nextCharacter = parseNextCharacter(str, parsePosition);
        if (nextCharacter == 0) {
            return new Fraction(number.intValue(), 1);
        }
        if (nextCharacter != '/') {
            parsePosition.setIndex(index);
            parsePosition.setErrorIndex(index2);
            return null;
        }
        parseAndIgnoreWhitespace(str, parsePosition);
        Number number2 = getDenominatorFormat().parse(str, parsePosition);
        if (number2 == null) {
            parsePosition.setIndex(index);
            return null;
        }
        return new Fraction(number.intValue(), number2.intValue());
    }
}
