package org.apache.commons.math.exception.util;

import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/exception/util/DummyLocalizable.class */
public class DummyLocalizable implements Localizable {
    private static final long serialVersionUID = 8843275624471387299L;
    private final String source;

    public DummyLocalizable(String source) {
        this.source = source;
    }

    @Override // org.apache.commons.math.exception.util.Localizable
    public String getSourceString() {
        return this.source;
    }

    @Override // org.apache.commons.math.exception.util.Localizable
    public String getLocalizedString(Locale locale) {
        return this.source;
    }

    public String toString() {
        return this.source;
    }
}
