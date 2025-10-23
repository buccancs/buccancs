package org.apache.commons.lang;

/* loaded from: classes5.dex */
public class WordUtils {
    public static String wrap(String str, int i) {
        return wrap(str, i, null, false);
    }

    public static String wrap(String str, int i, String str2, boolean z) {
        if (str == null) {
            return null;
        }
        if (str2 == null) {
            str2 = SystemUtils.LINE_SEPARATOR;
        }
        if (i < 1) {
            i = 1;
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length + 32);
        int i2 = 0;
        while (length - i2 > i) {
            if (str.charAt(i2) == ' ') {
                i2++;
            } else {
                int i3 = i + i2;
                int iLastIndexOf = str.lastIndexOf(32, i3);
                if (iLastIndexOf >= i2) {
                    stringBuffer.append(str.substring(i2, iLastIndexOf));
                    stringBuffer.append(str2);
                    i2 = iLastIndexOf + 1;
                } else {
                    if (z) {
                        stringBuffer.append(str.substring(i2, i3));
                        stringBuffer.append(str2);
                    } else {
                        int iIndexOf = str.indexOf(32, i3);
                        if (iIndexOf >= 0) {
                            stringBuffer.append(str.substring(i2, iIndexOf));
                            stringBuffer.append(str2);
                            i3 = iIndexOf + 1;
                        } else {
                            stringBuffer.append(str.substring(i2));
                            i2 = length;
                        }
                    }
                    i2 = i3;
                }
            }
        }
        stringBuffer.append(str.substring(i2));
        return stringBuffer.toString();
    }

    public static String capitalize(String str) {
        return capitalize(str, null);
    }

    public static String capitalize(String str, char[] cArr) {
        int length = cArr == null ? -1 : cArr.length;
        if (str == null || str.length() == 0 || length == 0) {
            return str;
        }
        int length2 = str.length();
        StringBuffer stringBuffer = new StringBuffer(length2);
        boolean z = true;
        for (int i = 0; i < length2; i++) {
            char cCharAt = str.charAt(i);
            if (isDelimiter(cCharAt, cArr)) {
                stringBuffer.append(cCharAt);
                z = true;
            } else if (z) {
                stringBuffer.append(Character.toTitleCase(cCharAt));
                z = false;
            } else {
                stringBuffer.append(cCharAt);
            }
        }
        return stringBuffer.toString();
    }

    public static String capitalizeFully(String str) {
        return capitalizeFully(str, null);
    }

    public static String capitalizeFully(String str, char[] cArr) {
        return (str == null || str.length() == 0 || (cArr == null ? -1 : cArr.length) == 0) ? str : capitalize(str.toLowerCase(), cArr);
    }

    public static String uncapitalize(String str) {
        return uncapitalize(str, null);
    }

    public static String uncapitalize(String str, char[] cArr) {
        int length = cArr == null ? -1 : cArr.length;
        if (str == null || str.length() == 0 || length == 0) {
            return str;
        }
        int length2 = str.length();
        StringBuffer stringBuffer = new StringBuffer(length2);
        boolean z = true;
        for (int i = 0; i < length2; i++) {
            char cCharAt = str.charAt(i);
            if (isDelimiter(cCharAt, cArr)) {
                stringBuffer.append(cCharAt);
                z = true;
            } else if (z) {
                stringBuffer.append(Character.toLowerCase(cCharAt));
                z = false;
            } else {
                stringBuffer.append(cCharAt);
            }
        }
        return stringBuffer.toString();
    }

    public static String swapCase(String str) {
        int length;
        char upperCase;
        if (str == null || (length = str.length()) == 0) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(length);
        boolean zIsWhitespace = true;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt)) {
                upperCase = Character.toLowerCase(cCharAt);
            } else if (Character.isTitleCase(cCharAt)) {
                upperCase = Character.toLowerCase(cCharAt);
            } else if (!Character.isLowerCase(cCharAt)) {
                upperCase = cCharAt;
            } else if (zIsWhitespace) {
                upperCase = Character.toTitleCase(cCharAt);
            } else {
                upperCase = Character.toUpperCase(cCharAt);
            }
            stringBuffer.append(upperCase);
            zIsWhitespace = Character.isWhitespace(cCharAt);
        }
        return stringBuffer.toString();
    }

    public static String initials(String str) {
        return initials(str, null);
    }

    public static String initials(String str, char[] cArr) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (cArr != null && cArr.length == 0) {
            return "";
        }
        int length = str.length();
        char[] cArr2 = new char[(length / 2) + 1];
        int i = 0;
        boolean z = true;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (isDelimiter(cCharAt, cArr)) {
                z = true;
            } else if (z) {
                cArr2[i] = cCharAt;
                i++;
                z = false;
            }
        }
        return new String(cArr2, 0, i);
    }

    private static boolean isDelimiter(char c, char[] cArr) {
        if (cArr == null) {
            return Character.isWhitespace(c);
        }
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    public static String abbreviate(String str, int i, int i2, String str2) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return "";
        }
        if (i > str.length()) {
            i = str.length();
        }
        if (i2 == -1 || i2 > str.length()) {
            i2 = str.length();
        }
        if (i2 < i) {
            i2 = i;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int iIndexOf = StringUtils.indexOf(str, org.apache.commons.lang3.StringUtils.SPACE, i);
        if (iIndexOf == -1) {
            stringBuffer.append(str.substring(0, i2));
            if (i2 != str.length()) {
                stringBuffer.append(StringUtils.defaultString(str2));
            }
        } else if (iIndexOf > i2) {
            stringBuffer.append(str.substring(0, i2));
            stringBuffer.append(StringUtils.defaultString(str2));
        } else {
            stringBuffer.append(str.substring(0, iIndexOf));
            stringBuffer.append(StringUtils.defaultString(str2));
        }
        return stringBuffer.toString();
    }
}
