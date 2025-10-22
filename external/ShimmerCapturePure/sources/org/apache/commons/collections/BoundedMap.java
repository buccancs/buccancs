package org.apache.commons.collections;

import java.util.Map;

/* loaded from: classes5.dex */
public interface BoundedMap extends Map {
    boolean isFull();

    int maxSize();
}
