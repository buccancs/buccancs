package org.apache.commons.math3.linear;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.util.CompositeFormat;

/* loaded from: classes5.dex */
public class RealVectorFormat {
    private static final String DEFAULT_PREFIX = "{";
    private static final String DEFAULT_SEPARATOR = "; ";
    private static final String DEFAULT_SUFFIX = "}";
    private final NumberFormat format;
    private final String prefix;
    private final String separator;
    private final String suffix;
    private final String trimmedPrefix;
    private final String trimmedSeparator;
    private final String trimmedSuffix;

    public RealVectorFormat() {
        this("{", "}", "; ", CompositeFormat.getDefaultNumberFormat());
    }

    public RealVectorFormat(NumberFormat numberFormat) {
        this("{", "}", "; ", numberFormat);
    }

    public RealVectorFormat(String str, String str2, String str3) {
        this(str, str2, str3, CompositeFormat.getDefaultNumberFormat());
    }

    public RealVectorFormat(String str, String str2, String str3, NumberFormat numberFormat) {
        this.prefix = str;
        this.suffix = str2;
        this.separator = str3;
        this.trimmedPrefix = str.trim();
        this.trimmedSuffix = str2.trim();
        this.trimmedSeparator = str3.trim();
        this.format = numberFormat;
    }

    public static Locale[] getAvailableLocales() {
        return NumberFormat.getAvailableLocales();
    }

    public static RealVectorFormat getInstance() {
        return getInstance(Locale.getDefault());
    }

    public static RealVectorFormat getInstance(Locale locale) {
        return new RealVectorFormat(CompositeFormat.getDefaultNumberFormat(locale));
    }

    public NumberFormat getFormat() {
        return this.format;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getSeparator() {
        return this.separator;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public String format(RealVector realVector) {
        return format(realVector, new StringBuffer(), new FieldPosition(0)).toString();
    }

    public StringBuffer format(RealVector realVector, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        stringBuffer.append(this.prefix);
        for (int i = 0; i < realVector.getDimension(); i++) {
            if (i > 0) {
                stringBuffer.append(this.separator);
            }
            CompositeFormat.formatDouble(realVector.getEntry(i), this.format, stringBuffer, fieldPosition);
        }
        stringBuffer.append(this.suffix);
        return stringBuffer;
    }

    public ArrayRealVector parse(String str) {
        ParsePosition parsePosition = new ParsePosition(0);
        ArrayRealVector arrayRealVector = parse(str, parsePosition);
        if (parsePosition.getIndex() != 0) {
            return arrayRealVector;
        }
        throw new MathParseException(str, parsePosition.getErrorIndex(), ArrayRealVector.class);
    }

    public ArrayRealVector parse(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
        if (!CompositeFormat.parseFixedstring(str, this.trimmedPrefix, parsePosition)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        while (z) {
            if (!arrayList.isEmpty()) {
                CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
                if (!CompositeFormat.parseFixedstring(str, this.trimmedSeparator, parsePosition)) {
                    z = false;
                }
            }
            if (z) {
                CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
                Number number = CompositeFormat.parseNumber(str, this.format, parsePosition);
                if (number != null) {
                    arrayList.add(number);
                } else {
                    parsePosition.setIndex(index);
                    return null;
                }
            }
        }
        CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
        if (!CompositeFormat.parseFixedstring(str, this.trimmedSuffix, parsePosition)) {
            return null;
        }
        int size = arrayList.size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((Number) arrayList.get(i)).doubleValue();
        }
        return new ArrayRealVector(dArr, false);
    }
}
