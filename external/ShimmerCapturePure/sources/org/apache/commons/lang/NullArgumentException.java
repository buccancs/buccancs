package org.apache.commons.lang;

/* loaded from: classes5.dex */
public class NullArgumentException extends IllegalArgumentException {
    private static final long serialVersionUID = 1174360235354917591L;

    public NullArgumentException(String str) {
        super(new StringBuffer().append(str == null ? "Argument" : str).append(" must not be null.").toString());
    }
}
