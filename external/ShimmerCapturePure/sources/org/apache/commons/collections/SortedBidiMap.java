package org.apache.commons.collections;

import java.util.SortedMap;

/* loaded from: classes5.dex */
public interface SortedBidiMap extends OrderedBidiMap, SortedMap {
    @Override
        // org.apache.commons.collections.OrderedBidiMap, org.apache.commons.collections.BidiMap
    BidiMap inverseBidiMap();

    SortedBidiMap inverseSortedBidiMap();
}
