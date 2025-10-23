package org.apache.commons.collections;

import java.util.Collection;

/* loaded from: classes5.dex */
public interface BoundedCollection extends Collection {
    boolean isFull();

    int maxSize();
}
