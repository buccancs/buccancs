package org.hamcrest.core;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
public class StringContains extends SubstringMatcher {
    public StringContains(String str) {
        super(str);
    }

    @Factory
    public static Matcher<String> containsString(String str) {
        return new StringContains(str);
    }

    @Override // org.hamcrest.core.SubstringMatcher
    protected String relationship() {
        return "containing";
    }

    @Override // org.hamcrest.core.SubstringMatcher
    protected boolean evalSubstringOf(String str) {
        return str.indexOf(this.substring) >= 0;
    }
}
