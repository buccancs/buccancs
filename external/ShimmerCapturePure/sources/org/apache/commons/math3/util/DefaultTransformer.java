package org.apache.commons.math3.util;

import java.io.Serializable;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public class DefaultTransformer implements NumberTransformer, Serializable {
    private static final long serialVersionUID = 4019938025047800455L;

    public int hashCode() {
        return 401993047;
    }

    @Override // org.apache.commons.math3.util.NumberTransformer
    public double transform(Object obj) throws MathIllegalArgumentException {
        if (obj == null) {
            throw new NullArgumentException(LocalizedFormats.OBJECT_TRANSFORMATION, new Object[0]);
        }
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        try {
            return Double.parseDouble(obj.toString());
        } catch (NumberFormatException unused) {
            throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_TRANSFORM_TO_DOUBLE, obj.toString());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof DefaultTransformer;
    }
}
