package org.apache.commons.math.geometry;

import org.apache.commons.math.MathException;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/geometry/CardanEulerSingularityException.class */
public class CardanEulerSingularityException extends MathException {
    private static final long serialVersionUID = -1360952845582206770L;

    public CardanEulerSingularityException(boolean isCardan) {
        super(isCardan ? LocalizedFormats.CARDAN_ANGLES_SINGULARITY : LocalizedFormats.EULER_ANGLES_SINGULARITY, new Object[0]);
    }
}
