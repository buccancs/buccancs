package org.hamcrest.core;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
abstract class ShortcutCombination<T> extends BaseMatcher<T> {
    private final Iterable<Matcher<? super T>> matchers;

    public ShortcutCombination(Iterable<Matcher<? super T>> iterable) {
        this.matchers = iterable;
    }

    @Override // org.hamcrest.SelfDescribing
    public abstract void describeTo(Description description);

    @Override // org.hamcrest.Matcher
    public abstract boolean matches(Object obj);

    protected boolean matches(Object obj, boolean z) {
        Iterator<Matcher<? super T>> it2 = this.matchers.iterator();
        while (it2.hasNext()) {
            if (it2.next().matches(obj) == z) {
                return z;
            }
        }
        return !z;
    }

    public void describeTo(Description description, String str) {
        description.appendList("(", StringUtils.SPACE + str + StringUtils.SPACE, ")", this.matchers);
    }
}
