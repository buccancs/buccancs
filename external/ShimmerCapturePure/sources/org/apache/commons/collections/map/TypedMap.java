package org.apache.commons.collections.map;

import java.util.Map;

import org.apache.commons.collections.functors.InstanceofPredicate;

/* loaded from: classes5.dex */
public class TypedMap {
    protected TypedMap() {
    }

    public static Map decorate(Map map, Class cls, Class cls2) {
        return new PredicatedMap(map, InstanceofPredicate.getInstance(cls), InstanceofPredicate.getInstance(cls2));
    }
}
