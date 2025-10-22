package com.google.common.cache;

import com.google.common.cache.LocalCache;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes.dex */
interface ReferenceEntry<K, V> {
    long getAccessTime();

    void setAccessTime(long j);

    int getHash();

    @NullableDecl
    K getKey();

    @NullableDecl
    ReferenceEntry<K, V> getNext();

    ReferenceEntry<K, V> getNextInAccessQueue();

    void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry);

    ReferenceEntry<K, V> getNextInWriteQueue();

    void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry);

    ReferenceEntry<K, V> getPreviousInAccessQueue();

    void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry);

    ReferenceEntry<K, V> getPreviousInWriteQueue();

    void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry);

    LocalCache.ValueReference<K, V> getValueReference();

    void setValueReference(LocalCache.ValueReference<K, V> valueReference);

    long getWriteTime();

    void setWriteTime(long j);
}
