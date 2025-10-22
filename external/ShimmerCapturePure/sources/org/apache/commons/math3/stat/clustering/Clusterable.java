package org.apache.commons.math3.stat.clustering;

import java.util.Collection;

@Deprecated
/* loaded from: classes5.dex */
public interface Clusterable<T> {
    T centroidOf(Collection<T> collection);

    double distanceFrom(T t);
}
