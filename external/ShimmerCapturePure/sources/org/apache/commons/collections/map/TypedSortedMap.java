package org.apache.commons.collections.map;

import java.util.SortedMap;

import org.apache.commons.collections.functors.InstanceofPredicate;

/* loaded from: classes5.dex */
public class TypedSortedMap {
    protected TypedSortedMap() {
    }

    public static SortedMap decorate(SortedMap sortedMap, Class cls, Class cls2) {
        return new PredicatedSortedMap(sortedMap, InstanceofPredicate.getInstance(cls), InstanceofPredicate.getInstance(cls2));
    }
}
