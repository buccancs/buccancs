package org.apache.commons.math3.exception.util;

import java.util.Locale;

/* loaded from: classes5.dex */
public class DummyLocalizable implements Localizable {
    private static final long serialVersionUID = 8843275624471387299L;
    private final String source;

    public DummyLocalizable(String str) {
        this.source = str;
    }

    @Override // org.apache.commons.math3.exception.util.Localizable
    public String getLocalizedString(Locale locale) {
        return this.source;
    }

    @Override // org.apache.commons.math3.exception.util.Localizable
    public String getSourceString() {
        return this.source;
    }

    public String toString() {
        return this.source;
    }
}
