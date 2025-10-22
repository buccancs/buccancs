package org.apache.commons.math3.complex;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.CompositeFormat;

/* loaded from: classes5.dex */
public class ComplexFormat {
    private static final String DEFAULT_IMAGINARY_CHARACTER = "i";
    private final String imaginaryCharacter;
    private final NumberFormat imaginaryFormat;
    private final NumberFormat realFormat;

    public ComplexFormat() {
        this.imaginaryCharacter = DEFAULT_IMAGINARY_CHARACTER;
        NumberFormat defaultNumberFormat = CompositeFormat.getDefaultNumberFormat();
        this.imaginaryFormat = defaultNumberFormat;
        this.realFormat = defaultNumberFormat;
    }

    public ComplexFormat(NumberFormat numberFormat) throws NullArgumentException {
        if (numberFormat == null) {
            throw new NullArgumentException(LocalizedFormats.IMAGINARY_FORMAT, new Object[0]);
        }
        this.imaginaryCharacter = DEFAULT_IMAGINARY_CHARACTER;
        this.imaginaryFormat = numberFormat;
        this.realFormat = numberFormat;
    }

    public ComplexFormat(NumberFormat numberFormat, NumberFormat numberFormat2) throws NullArgumentException {
        if (numberFormat2 == null) {
            throw new NullArgumentException(LocalizedFormats.IMAGINARY_FORMAT, new Object[0]);
        }
        if (numberFormat == null) {
            throw new NullArgumentException(LocalizedFormats.REAL_FORMAT, new Object[0]);
        }
        this.imaginaryCharacter = DEFAULT_IMAGINARY_CHARACTER;
        this.imaginaryFormat = numberFormat2;
        this.realFormat = numberFormat;
    }

    public ComplexFormat(String str) throws NullArgumentException, NoDataException {
        this(str, CompositeFormat.getDefaultNumberFormat());
    }

    public ComplexFormat(String str, NumberFormat numberFormat) throws NullArgumentException, NoDataException {
        this(str, numberFormat, numberFormat);
    }

    public ComplexFormat(String str, NumberFormat numberFormat, NumberFormat numberFormat2) throws NullArgumentException, NoDataException {
        if (str == null) {
            throw new NullArgumentException();
        }
        if (str.length() == 0) {
            throw new NoDataException();
        }
        if (numberFormat2 == null) {
            throw new NullArgumentException(LocalizedFormats.IMAGINARY_FORMAT, new Object[0]);
        }
        if (numberFormat == null) {
            throw new NullArgumentException(LocalizedFormats.REAL_FORMAT, new Object[0]);
        }
        this.imaginaryCharacter = str;
        this.imaginaryFormat = numberFormat2;
        this.realFormat = numberFormat;
    }

    public static Locale[] getAvailableLocales() {
        return NumberFormat.getAvailableLocales();
    }

    public static ComplexFormat getInstance() {
        return getInstance(Locale.getDefault());
    }

    public static ComplexFormat getInstance(Locale locale) {
        return new ComplexFormat(CompositeFormat.getDefaultNumberFormat(locale));
    }

    public static ComplexFormat getInstance(String str, Locale locale) throws NullArgumentException, NoDataException {
        return new ComplexFormat(str, CompositeFormat.getDefaultNumberFormat(locale));
    }

    public String getImaginaryCharacter() {
        return this.imaginaryCharacter;
    }

    public NumberFormat getImaginaryFormat() {
        return this.imaginaryFormat;
    }

    public NumberFormat getRealFormat() {
        return this.realFormat;
    }

    public String format(Complex complex) {
        return format(complex, new StringBuffer(), new FieldPosition(0)).toString();
    }

    public String format(Double d) {
        return format(new Complex(d.doubleValue(), 0.0d), new StringBuffer(), new FieldPosition(0)).toString();
    }

    public StringBuffer format(Complex complex, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        CompositeFormat.formatDouble(complex.getReal(), getRealFormat(), stringBuffer, fieldPosition);
        double imaginary = complex.getImaginary();
        if (imaginary < 0.0d) {
            stringBuffer.append(" - ");
            stringBuffer.append(formatImaginary(-imaginary, new StringBuffer(), fieldPosition));
            stringBuffer.append(getImaginaryCharacter());
        } else if (imaginary > 0.0d || Double.isNaN(imaginary)) {
            stringBuffer.append(" + ");
            stringBuffer.append(formatImaginary(imaginary, new StringBuffer(), fieldPosition));
            stringBuffer.append(getImaginaryCharacter());
        }
        return stringBuffer;
    }

    private StringBuffer formatImaginary(double d, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        CompositeFormat.formatDouble(d, getImaginaryFormat(), stringBuffer, fieldPosition);
        if (stringBuffer.toString().equals("1")) {
            stringBuffer.setLength(0);
        }
        return stringBuffer;
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) throws MathIllegalArgumentException {
        if (obj instanceof Complex) {
            return format((Complex) obj, stringBuffer, fieldPosition);
        }
        if (obj instanceof Number) {
            return format(new Complex(((Number) obj).doubleValue(), 0.0d), stringBuffer, fieldPosition);
        }
        throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_FORMAT_INSTANCE_AS_COMPLEX, obj.getClass().getName());
    }

    public Complex parse(String str) throws MathParseException {
        ParsePosition parsePosition = new ParsePosition(0);
        Complex complex = parse(str, parsePosition);
        if (parsePosition.getIndex() != 0) {
            return complex;
        }
        throw new MathParseException(str, parsePosition.getErrorIndex(), Complex.class);
    }

    public Complex parse(String str, ParsePosition parsePosition) {
        int i;
        int index = parsePosition.getIndex();
        CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
        Number number = CompositeFormat.parseNumber(str, getRealFormat(), parsePosition);
        if (number == null) {
            parsePosition.setIndex(index);
            return null;
        }
        int index2 = parsePosition.getIndex();
        char nextCharacter = CompositeFormat.parseNextCharacter(str, parsePosition);
        if (nextCharacter == 0) {
            return new Complex(number.doubleValue(), 0.0d);
        }
        if (nextCharacter == '+') {
            i = 1;
        } else {
            if (nextCharacter != '-') {
                parsePosition.setIndex(index);
                parsePosition.setErrorIndex(index2);
                return null;
            }
            i = -1;
        }
        CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
        Number number2 = CompositeFormat.parseNumber(str, getRealFormat(), parsePosition);
        if (number2 == null) {
            parsePosition.setIndex(index);
            return null;
        }
        if (CompositeFormat.parseFixedstring(str, getImaginaryCharacter(), parsePosition)) {
            return new Complex(number.doubleValue(), number2.doubleValue() * i);
        }
        return null;
    }
}
