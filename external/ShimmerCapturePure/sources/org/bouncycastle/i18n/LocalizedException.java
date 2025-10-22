package org.bouncycastle.i18n;

import java.util.Locale;

/* loaded from: classes5.dex */
public class LocalizedException extends Exception {
    protected ErrorBundle message;
    private Throwable cause;

    public LocalizedException(ErrorBundle errorBundle) {
        super(errorBundle.getText(Locale.getDefault()));
        this.message = errorBundle;
    }

    public LocalizedException(ErrorBundle errorBundle, Throwable th) {
        super(errorBundle.getText(Locale.getDefault()));
        this.message = errorBundle;
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public ErrorBundle getErrorMessage() {
        return this.message;
    }
}
