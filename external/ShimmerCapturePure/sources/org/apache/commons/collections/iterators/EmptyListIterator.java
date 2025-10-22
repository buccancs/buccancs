package org.apache.commons.collections.iterators;

import java.util.ListIterator;

import org.apache.commons.collections.ResettableListIterator;

/* loaded from: classes5.dex */
public class EmptyListIterator extends AbstractEmptyIterator implements ResettableListIterator {
    public static final ListIterator INSTANCE;
    public static final ResettableListIterator RESETTABLE_INSTANCE;

    static {
        EmptyListIterator emptyListIterator = new EmptyListIterator();
        RESETTABLE_INSTANCE = emptyListIterator;
        INSTANCE = emptyListIterator;
    }

    protected EmptyListIterator() {
    }
}
