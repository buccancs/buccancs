package org.apache.commons.math3.geometry.euclidean.twod;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.commons.math3.util.CompositeFormat;

/* loaded from: classes5.dex */
public class Vector2DFormat extends VectorFormat<Euclidean2D> {
    public Vector2DFormat() {
        super(VectorFormat.DEFAULT_PREFIX, VectorFormat.DEFAULT_SUFFIX, VectorFormat.DEFAULT_SEPARATOR, CompositeFormat.getDefaultNumberFormat());
    }

    public Vector2DFormat(NumberFormat numberFormat) {
        super(VectorFormat.DEFAULT_PREFIX, VectorFormat.DEFAULT_SUFFIX, VectorFormat.DEFAULT_SEPARATOR, numberFormat);
    }

    public Vector2DFormat(String str, String str2, String str3) {
        super(str, str2, str3, CompositeFormat.getDefaultNumberFormat());
    }

    public Vector2DFormat(String str, String str2, String str3, NumberFormat numberFormat) {
        super(str, str2, str3, numberFormat);
    }

    public static Vector2DFormat getInstance() {
        return getInstance(Locale.getDefault());
    }

    public static Vector2DFormat getInstance(Locale locale) {
        return new Vector2DFormat(CompositeFormat.getDefaultNumberFormat(locale));
    }

    @Override // org.apache.commons.math3.geometry.VectorFormat
    public StringBuffer format(Vector<Euclidean2D> vector, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        Vector2D vector2D = (Vector2D) vector;
        return format(stringBuffer, fieldPosition, vector2D.getX(), vector2D.getY());
    }

    @Override // org.apache.commons.math3.geometry.VectorFormat
    public Vector2D parse(String str) throws MathParseException {
        ParsePosition parsePosition = new ParsePosition(0);
        Vector2D vector2D = parse(str, parsePosition);
        if (parsePosition.getIndex() != 0) {
            return vector2D;
        }
        throw new MathParseException(str, parsePosition.getErrorIndex(), Vector2D.class);
    }

    @Override // org.apache.commons.math3.geometry.VectorFormat
    public Vector2D parse(String str, ParsePosition parsePosition) {
        double[] coordinates = parseCoordinates(2, str, parsePosition);
        if (coordinates == null) {
            return null;
        }
        return new Vector2D(coordinates[0], coordinates[1]);
    }
}
