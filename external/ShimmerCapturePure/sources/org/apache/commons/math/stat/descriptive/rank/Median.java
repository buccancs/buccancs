package org.apache.commons.math.stat.descriptive.rank;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/descriptive/rank/Median.class */
public class Median extends Percentile implements Serializable {
    private static final long serialVersionUID = -3961477041290915687L;

    public Median() {
        super(50.0d);
    }

    public Median(Median original) {
        super(original);
    }
}
