package com.google.re2j;

import org.apache.http.message.TokenParser;

/* loaded from: classes2.dex */
public final class Matcher {
    private final int groupCount;
    private final int[] groups;
    private final Pattern pattern;
    private int anchorFlag;
    private int appendPos;
    private boolean hasGroups;
    private boolean hasMatch;
    private int inputLength;
    private CharSequence inputSequence;

    private Matcher(Pattern pattern) {
        if (pattern == null) {
            throw new NullPointerException("pattern is null");
        }
        this.pattern = pattern;
        int iNumberOfCapturingGroups = pattern.re2().numberOfCapturingGroups();
        this.groupCount = iNumberOfCapturingGroups;
        this.groups = new int[(iNumberOfCapturingGroups * 2) + 2];
    }

    Matcher(Pattern pattern, CharSequence charSequence) {
        this(pattern);
        reset(charSequence);
    }

    public static String quoteReplacement(String str) {
        if (str.indexOf(92) < 0 && str.indexOf(36) < 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\\' || cCharAt == '$') {
                sb.append(TokenParser.ESCAPE);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    public int groupCount() {
        return this.groupCount;
    }

    int inputLength() {
        return this.inputLength;
    }

    public Pattern pattern() {
        return this.pattern;
    }

    public Matcher reset() {
        this.inputLength = this.inputSequence.length();
        this.appendPos = 0;
        this.hasMatch = false;
        this.hasGroups = false;
        return this;
    }

    public Matcher reset(CharSequence charSequence) {
        if (charSequence == null) {
            throw new NullPointerException("input is null");
        }
        this.inputSequence = charSequence;
        reset();
        return this;
    }

    public int start() {
        return start(0);
    }

    public int end() {
        return end(0);
    }

    public int start(int i) {
        loadGroup(i);
        return this.groups[i * 2];
    }

    public int end(int i) {
        loadGroup(i);
        return this.groups[(i * 2) + 1];
    }

    public String group() {
        return group(0);
    }

    public String group(int i) {
        int iStart = start(i);
        int iEnd = end(i);
        if (iStart >= 0 || iEnd >= 0) {
            return substring(iStart, iEnd);
        }
        return null;
    }

    private void loadGroup(int i) {
        if (i < 0 || i > this.groupCount) {
            throw new IndexOutOfBoundsException("Group index out of bounds: " + i);
        }
        if (!this.hasMatch) {
            throw new IllegalStateException("perhaps no match attempted");
        }
        if (i == 0 || this.hasGroups) {
            return;
        }
        int i2 = this.groups[1] + 1;
        int i3 = this.inputLength;
        int i4 = i2 > i3 ? i3 : i2;
        RE2 re2 = this.pattern.re2();
        CharSequence charSequence = this.inputSequence;
        int[] iArr = this.groups;
        if (!re2.match(charSequence, iArr[0], i4, this.anchorFlag, iArr, this.groupCount + 1)) {
            throw new IllegalStateException("inconsistency in matching group data");
        }
        this.hasGroups = true;
    }

    public boolean matches() {
        return genMatch(0, 2);
    }

    public boolean lookingAt() {
        return genMatch(0, 1);
    }

    public boolean find() {
        int i;
        if (this.hasMatch) {
            int[] iArr = this.groups;
            i = iArr[1];
            if (iArr[0] == i) {
                i++;
            }
        } else {
            i = 0;
        }
        return genMatch(i, 0);
    }

    public boolean find(int i) {
        if (i < 0 || i > this.inputLength) {
            throw new IndexOutOfBoundsException("start index out of bounds: " + i);
        }
        reset();
        return genMatch(i, 0);
    }

    private boolean genMatch(int i, int i2) {
        if (!this.pattern.re2().match(this.inputSequence, i, this.inputLength, i2, this.groups, 1)) {
            return false;
        }
        this.hasMatch = true;
        this.hasGroups = false;
        this.anchorFlag = i2;
        return true;
    }

    String substring(int i, int i2) {
        return this.inputSequence.subSequence(i, i2).toString();
    }

    public Matcher appendReplacement(StringBuffer stringBuffer, String str) {
        int iStart = start();
        int iEnd = end();
        int i = this.appendPos;
        if (i < iStart) {
            stringBuffer.append(substring(i, iStart));
        }
        this.appendPos = iEnd;
        StringBuilder sb = new StringBuilder();
        appendReplacementInternal(sb, str);
        stringBuffer.append((CharSequence) sb);
        return this;
    }

    public Matcher appendReplacement(StringBuilder sb, String str) {
        int iStart = start();
        int iEnd = end();
        int i = this.appendPos;
        if (i < iStart) {
            sb.append(substring(i, iStart));
        }
        this.appendPos = iEnd;
        appendReplacementInternal(sb, str);
        return this;
    }

    private void appendReplacementInternal(StringBuilder sb, String str) {
        char cCharAt;
        int i;
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        while (i2 < length - 1) {
            if (str.charAt(i2) == '\\') {
                if (i3 < i2) {
                    sb.append(str.substring(i3, i2));
                }
                i2++;
                i3 = i2;
            } else if (str.charAt(i2) == '$' && '0' <= (cCharAt = str.charAt(i2 + 1)) && cCharAt <= '9') {
                int i4 = cCharAt - '0';
                if (i3 < i2) {
                    sb.append(str.substring(i3, i2));
                }
                int i5 = i2 + 2;
                while (i5 < length) {
                    char cCharAt2 = str.charAt(i5);
                    if (cCharAt2 < '0' || cCharAt2 > '9' || ((i4 * 10) + cCharAt2) - 48 > this.groupCount) {
                        break;
                    }
                    i5++;
                    i4 = i;
                }
                if (i4 > this.groupCount) {
                    throw new IndexOutOfBoundsException("n > number of groups: " + i4);
                }
                String strGroup = group(i4);
                if (strGroup != null) {
                    sb.append(strGroup);
                }
                i3 = i5;
                i2 = i5 - 1;
            }
            i2++;
        }
        if (i3 < length) {
            sb.append(str.substring(i3, length));
        }
    }

    public StringBuffer appendTail(StringBuffer stringBuffer) {
        stringBuffer.append(substring(this.appendPos, this.inputLength));
        return stringBuffer;
    }

    public StringBuilder appendTail(StringBuilder sb) {
        sb.append(substring(this.appendPos, this.inputLength));
        return sb;
    }

    public String replaceAll(String str) {
        return replace(str, true);
    }

    public String replaceFirst(String str) {
        return replace(str, false);
    }

    private String replace(String str, boolean z) {
        reset();
        StringBuffer stringBuffer = new StringBuffer();
        while (find()) {
            appendReplacement(stringBuffer, str);
            if (!z) {
                break;
            }
        }
        appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
