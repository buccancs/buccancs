package com.google.api.client.util;

import java.util.Collection;

/* loaded from: classes.dex */
public final class Collections2 {
    private Collections2() {
    }

    static <T> Collection<T> cast(Iterable<T> iterable) {
        return (Collection) iterable;
    }
}
