package org.apache.commons.lang;

import java.util.Arrays;

/* loaded from: classes5.dex */
public class IncompleteArgumentException extends IllegalArgumentException {
    private static final long serialVersionUID = 4954193403612068178L;

    public IncompleteArgumentException(String str) {
        super(new StringBuffer().append(str).append(" is incomplete.").toString());
    }

    public IncompleteArgumentException(String str, String[] strArr) {
        super(new StringBuffer().append(str).append(" is missing the following items: ").append(safeArrayToString(strArr)).toString());
    }

    private static final String safeArrayToString(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        return Arrays.asList(objArr).toString();
    }
}
