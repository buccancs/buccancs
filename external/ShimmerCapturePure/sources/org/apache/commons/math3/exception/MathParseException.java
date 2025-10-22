package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.ExceptionContextProvider;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public class MathParseException extends MathIllegalStateException implements ExceptionContextProvider {
    private static final long serialVersionUID = -6024911025449780478L;

    public MathParseException(String str, int i, Class<?> cls) {
        getContext().addMessage(LocalizedFormats.CANNOT_PARSE_AS_TYPE, str, Integer.valueOf(i), cls.getName());
    }

    public MathParseException(String str, int i) {
        getContext().addMessage(LocalizedFormats.CANNOT_PARSE, str, Integer.valueOf(i));
    }
}
