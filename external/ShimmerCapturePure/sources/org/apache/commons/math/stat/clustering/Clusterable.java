package org.apache.commons.math.stat.clustering;

import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/clustering/Clusterable.class */
public interface Clusterable<T> {
    double distanceFrom(T t);

    T centroidOf(Collection<T> collection);
}
