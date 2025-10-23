package com.google.re2j;

/* loaded from: classes2.dex */
public class PatternSyntaxException extends RuntimeException {
    private final String error;
    private final String input;

    public PatternSyntaxException(String str, String str2) {
        super("error parsing regexp: " + str + ": `" + str2 + "`");
        this.error = str;
        this.input = str2;
    }

    public PatternSyntaxException(String str) {
        super("error parsing regexp: " + str);
        this.error = str;
        this.input = "";
    }

    public String getDescription() {
        return this.error;
    }

    public int getIndex() {
        return -1;
    }

    public String getPattern() {
        return this.input;
    }
}
