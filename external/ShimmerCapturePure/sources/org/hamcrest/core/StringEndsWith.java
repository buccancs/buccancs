package org.hamcrest.core;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
public class StringEndsWith extends SubstringMatcher {
    public StringEndsWith(String str) {
        super(str);
    }

    @Factory
    public static Matcher<String> endsWith(String str) {
        return new StringEndsWith(str);
    }

    @Override // org.hamcrest.core.SubstringMatcher
    protected String relationship() {
        return "ending with";
    }

    @Override // org.hamcrest.core.SubstringMatcher
    protected boolean evalSubstringOf(String str) {
        return str.endsWith(this.substring);
    }
}
