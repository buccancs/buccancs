package org.apache.commons.math3.fraction;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class ProperFractionFormat extends FractionFormat {
    private static final long serialVersionUID = 760934726031766749L;
    private NumberFormat wholeFormat;

    public ProperFractionFormat() {
        this(getDefaultNumberFormat());
    }

    public ProperFractionFormat(NumberFormat numberFormat) {
        this(numberFormat, (NumberFormat) numberFormat.clone(), (NumberFormat) numberFormat.clone());
    }

    public ProperFractionFormat(NumberFormat numberFormat, NumberFormat numberFormat2, NumberFormat numberFormat3) {
        super(numberFormat2, numberFormat3);
        setWholeFormat(numberFormat);
    }

    public NumberFormat getWholeFormat() {
        return this.wholeFormat;
    }

    public void setWholeFormat(NumberFormat numberFormat) {
        if (numberFormat == null) {
            throw new NullArgumentException(LocalizedFormats.WHOLE_FORMAT, new Object[0]);
        }
        this.wholeFormat = numberFormat;
    }

    @Override // org.apache.commons.math3.fraction.FractionFormat
    public StringBuffer format(Fraction fraction, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        int numerator = fraction.getNumerator();
        int denominator = fraction.getDenominator();
        int i = numerator / denominator;
        int iAbs = numerator % denominator;
        if (i != 0) {
            getWholeFormat().format(i, stringBuffer, fieldPosition);
            stringBuffer.append(' ');
            iAbs = FastMath.abs(iAbs);
        }
        getNumeratorFormat().format(iAbs, stringBuffer, fieldPosition);
        stringBuffer.append(" / ");
        getDenominatorFormat().format(denominator, stringBuffer, fieldPosition);
        return stringBuffer;
    }

    @Override // org.apache.commons.math3.fraction.FractionFormat, java.text.NumberFormat
    public Fraction parse(String str, ParsePosition parsePosition) {
        Fraction fraction = super.parse(str, parsePosition);
        if (fraction != null) {
            return fraction;
        }
        int index = parsePosition.getIndex();
        parseAndIgnoreWhitespace(str, parsePosition);
        Number number = getWholeFormat().parse(str, parsePosition);
        if (number == null) {
            parsePosition.setIndex(index);
            return null;
        }
        parseAndIgnoreWhitespace(str, parsePosition);
        Number number2 = getNumeratorFormat().parse(str, parsePosition);
        if (number2 == null) {
            parsePosition.setIndex(index);
            return null;
        }
        if (number2.intValue() < 0) {
            parsePosition.setIndex(index);
            return null;
        }
        int index2 = parsePosition.getIndex();
        char nextCharacter = parseNextCharacter(str, parsePosition);
        if (nextCharacter == 0) {
            return new Fraction(number2.intValue(), 1);
        }
        if (nextCharacter != '/') {
            parsePosition.setIndex(index);
            parsePosition.setErrorIndex(index2);
            return null;
        }
        parseAndIgnoreWhitespace(str, parsePosition);
        Number number3 = getDenominatorFormat().parse(str, parsePosition);
        if (number3 == null) {
            parsePosition.setIndex(index);
            return null;
        }
        if (number3.intValue() < 0) {
            parsePosition.setIndex(index);
            return null;
        }
        int iIntValue = number.intValue();
        int iIntValue2 = number2.intValue();
        int iIntValue3 = number3.intValue();
        return new Fraction(((FastMath.abs(iIntValue) * iIntValue3) + iIntValue2) * MathUtils.copySign(1, iIntValue), iIntValue3);
    }
}
