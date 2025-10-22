package com.google.re2j;

import java.io.Serializable;

/* loaded from: classes2.dex */
public final class Pattern implements Serializable {
    public static final int CASE_INSENSITIVE = 1;
    public static final int DISABLE_UNICODE_GROUPS = 8;
    public static final int DOTALL = 2;
    public static final int MULTILINE = 4;
    private static final long serialVersionUID = 0;
    private final int flags;
    private final String pattern;
    private final transient RE2 re2;

    Pattern(String str, int i, RE2 re2) {
        if (str == null) {
            throw new NullPointerException("pattern is null");
        }
        if (re2 == null) {
            throw new NullPointerException("re2 is null");
        }
        this.pattern = str;
        this.flags = i;
        this.re2 = re2;
    }

    public static Pattern compile(String str) {
        return compile(str, str, 0);
    }

    public static Pattern compile(String str, int i) {
        String str2;
        if ((i & 1) != 0) {
            str2 = "(?i)" + str;
        } else {
            str2 = str;
        }
        if ((i & 2) != 0) {
            str2 = "(?s)" + str2;
        }
        if ((i & 4) != 0) {
            str2 = "(?m)" + str2;
        }
        if ((i & (-16)) != 0) {
            throw new IllegalArgumentException("Flags should only be a combination of MULTILINE, DOTALL, CASE_INSENSITIVE, DISABLE_UNICODE_GROUPS");
        }
        return compile(str2, str, i);
    }

    private static Pattern compile(String str, String str2, int i) {
        return new Pattern(str2, i, RE2.compileImpl(str, (i & 8) != 0 ? 84 : 212, false));
    }

    public static boolean matches(String str, CharSequence charSequence) {
        return compile(str).matcher(charSequence).matches();
    }

    public static String quote(String str) {
        return RE2.quoteMeta(str);
    }

    public int flags() {
        return this.flags;
    }

    public String pattern() {
        return this.pattern;
    }

    RE2 re2() {
        return this.re2;
    }

    public String toString() {
        return this.pattern;
    }

    public void reset() {
        this.re2.reset();
    }

    public boolean matches(String str) {
        return matcher(str).matches();
    }

    public Matcher matcher(CharSequence charSequence) {
        return new Matcher(this, charSequence);
    }

    public String[] split(String str) {
        return split(str, 0);
    }

    public String[] split(String str, int i) {
        return split(new Matcher(this, str), i);
    }

    private String[] split(Matcher matcher, int i) {
        int i2 = 0;
        int iEnd = 0;
        int i3 = 0;
        int i4 = 0;
        while (matcher.find()) {
            i3++;
            if (i != 0 || iEnd < matcher.start()) {
                i4 = i3;
            }
            iEnd = matcher.end();
        }
        int i5 = 1;
        if (iEnd < matcher.inputLength() || i != 0) {
            i4 = i3 + 1;
        }
        if (i <= 0 || i4 <= i) {
            i = i4;
            i5 = 0;
        }
        String[] strArr = new String[i];
        matcher.reset();
        int iEnd2 = 0;
        while (matcher.find() && i2 < i - i5) {
            strArr[i2] = matcher.substring(iEnd2, matcher.start());
            iEnd2 = matcher.end();
            i2++;
        }
        if (i2 < i) {
            strArr[i2] = matcher.substring(iEnd2, matcher.inputLength());
        }
        return strArr;
    }

    public int groupCount() {
        return this.re2.numberOfCapturingGroups();
    }

    Object readResolve() {
        return compile(this.pattern, this.flags);
    }
}
