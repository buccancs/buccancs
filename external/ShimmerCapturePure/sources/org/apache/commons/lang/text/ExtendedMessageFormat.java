package org.apache.commons.lang.text;

import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;

/* loaded from: classes5.dex */
public class ExtendedMessageFormat extends MessageFormat {
    private static final String DUMMY_PATTERN = "";
    private static final char END_FE = '}';
    private static final String ESCAPED_QUOTE = "''";
    private static final int HASH_SEED = 31;
    private static final char QUOTE = '\'';
    private static final char START_FE = '{';
    private static final char START_FMT = ',';
    private static final long serialVersionUID = -2362048321261811743L;
    private final Map registry;
    private String toPattern;

    public ExtendedMessageFormat(String str) {
        this(str, Locale.getDefault());
    }

    public ExtendedMessageFormat(String str, Locale locale) {
        this(str, locale, null);
    }

    public ExtendedMessageFormat(String str, Map map) {
        this(str, Locale.getDefault(), map);
    }

    public ExtendedMessageFormat(String str, Locale locale, Map map) {
        super("");
        setLocale(locale);
        this.registry = map;
        applyPattern(str);
    }

    @Override // java.text.MessageFormat
    public String toPattern() {
        return this.toPattern;
    }

    @Override // java.text.MessageFormat
    public final void applyPattern(String str) {
        String formatDescription;
        Format format;
        if (this.registry == null) {
            super.applyPattern(str);
            this.toPattern = super.toPattern();
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        StrBuilder strBuilder = new StrBuilder(str.length());
        int i = 0;
        ParsePosition parsePosition = new ParsePosition(0);
        char[] charArray = str.toCharArray();
        int i2 = 0;
        while (parsePosition.getIndex() < str.length()) {
            char c = charArray[parsePosition.getIndex()];
            if (c == '\'') {
                appendQuotedString(str, parsePosition, strBuilder, true);
            } else {
                if (c == '{') {
                    i2++;
                    seekNonWs(str, parsePosition);
                    int index = parsePosition.getIndex();
                    strBuilder.append(START_FE).append(readArgumentIndex(str, next(parsePosition)));
                    seekNonWs(str, parsePosition);
                    if (charArray[parsePosition.getIndex()] == ',') {
                        formatDescription = parseFormatDescription(str, next(parsePosition));
                        format = getFormat(formatDescription);
                        if (format == null) {
                            strBuilder.append(',').append(formatDescription);
                        }
                    } else {
                        formatDescription = null;
                        format = null;
                    }
                    arrayList.add(format);
                    arrayList2.add(format != null ? formatDescription : null);
                    Validate.isTrue(arrayList.size() == i2);
                    Validate.isTrue(arrayList2.size() == i2);
                    if (charArray[parsePosition.getIndex()] != '}') {
                        throw new IllegalArgumentException(new StringBuffer("Unreadable format element at position ").append(index).toString());
                    }
                }
                strBuilder.append(charArray[parsePosition.getIndex()]);
                next(parsePosition);
            }
        }
        super.applyPattern(strBuilder.toString());
        this.toPattern = insertFormats(super.toPattern(), arrayList2);
        if (containsElements(arrayList)) {
            Format[] formats = getFormats();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                Format format2 = (Format) it2.next();
                if (format2 != null) {
                    formats[i] = format2;
                }
                i++;
            }
            super.setFormats(formats);
        }
    }

    @Override // java.text.MessageFormat
    public void setFormat(int i, Format format) {
        throw new UnsupportedOperationException();
    }

    @Override // java.text.MessageFormat
    public void setFormatByArgumentIndex(int i, Format format) {
        throw new UnsupportedOperationException();
    }

    @Override // java.text.MessageFormat
    public void setFormats(Format[] formatArr) {
        throw new UnsupportedOperationException();
    }

    @Override // java.text.MessageFormat
    public void setFormatsByArgumentIndex(Format[] formatArr) {
        throw new UnsupportedOperationException();
    }

    @Override // java.text.MessageFormat
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !super.equals(obj) || ObjectUtils.notEqual(getClass(), obj.getClass())) {
            return false;
        }
        ExtendedMessageFormat extendedMessageFormat = (ExtendedMessageFormat) obj;
        return (ObjectUtils.notEqual(this.toPattern, extendedMessageFormat.toPattern) || ObjectUtils.notEqual(this.registry, extendedMessageFormat.registry)) ? false : true;
    }

    @Override // java.text.MessageFormat
    public int hashCode() {
        return (((super.hashCode() * 31) + ObjectUtils.hashCode(this.registry)) * 31) + ObjectUtils.hashCode(this.toPattern);
    }

    private Format getFormat(String str) {
        String strTrim;
        if (this.registry != null) {
            int iIndexOf = str.indexOf(44);
            if (iIndexOf > 0) {
                String strTrim2 = str.substring(0, iIndexOf).trim();
                strTrim = str.substring(iIndexOf + 1).trim();
                str = strTrim2;
            } else {
                strTrim = null;
            }
            FormatFactory formatFactory = (FormatFactory) this.registry.get(str);
            if (formatFactory != null) {
                return formatFactory.getFormat(str, strTrim, getLocale());
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003d A[PHI: r2
  0x003d: PHI (r2v7 char) = (r2v6 char), (r2v11 char), (r2v11 char) binds: [B:7:0x002a, B:9:0x0037, B:10:0x0039] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int readArgumentIndex(java.lang.String r8, java.text.ParsePosition r9) {
        /*
            r7 = this;
            int r0 = r9.getIndex()
            r7.seekNonWs(r8, r9)
            org.apache.commons.lang.text.StrBuilder r1 = new org.apache.commons.lang.text.StrBuilder
            r1.<init>()
            r2 = 0
        Ld:
            if (r2 != 0) goto L5d
            int r3 = r9.getIndex()
            int r4 = r8.length()
            if (r3 >= r4) goto L5d
            int r2 = r9.getIndex()
            char r2 = r8.charAt(r2)
            boolean r3 = java.lang.Character.isWhitespace(r2)
            r4 = 1
            r5 = 125(0x7d, float:1.75E-43)
            r6 = 44
            if (r3 == 0) goto L3d
            r7.seekNonWs(r8, r9)
            int r2 = r9.getIndex()
            char r2 = r8.charAt(r2)
            if (r2 == r6) goto L3d
            if (r2 == r5) goto L3d
            r2 = 1
            goto L59
        L3d:
            if (r2 == r6) goto L41
            if (r2 != r5) goto L50
        L41:
            int r3 = r1.length()
            if (r3 <= 0) goto L50
            java.lang.String r3 = r1.toString()     // Catch: java.lang.NumberFormatException -> L50
            int r8 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L50
            return r8
        L50:
            boolean r3 = java.lang.Character.isDigit(r2)
            r3 = r3 ^ r4
            r1.append(r2)
            r2 = r3
        L59:
            r7.next(r9)
            goto Ld
        L5d:
            if (r2 == 0) goto L86
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            java.lang.String r3 = "Invalid format argument index at position "
            r2.<init>(r3)
            java.lang.StringBuffer r2 = r2.append(r0)
            java.lang.String r3 = ": "
            java.lang.StringBuffer r2 = r2.append(r3)
            int r9 = r9.getIndex()
            java.lang.String r8 = r8.substring(r0, r9)
            java.lang.StringBuffer r8 = r2.append(r8)
            java.lang.String r8 = r8.toString()
            r1.<init>(r8)
            throw r1
        L86:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuffer r9 = new java.lang.StringBuffer
            java.lang.String r1 = "Unterminated format element at position "
            r9.<init>(r1)
            java.lang.StringBuffer r9 = r9.append(r0)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.text.ExtendedMessageFormat.readArgumentIndex(java.lang.String, java.text.ParsePosition):int");
    }

    private String parseFormatDescription(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        seekNonWs(str, parsePosition);
        int index2 = parsePosition.getIndex();
        int i = 1;
        while (parsePosition.getIndex() < str.length()) {
            char cCharAt = str.charAt(parsePosition.getIndex());
            if (cCharAt == '\'') {
                getQuotedString(str, parsePosition, false);
            } else if (cCharAt == '{') {
                i++;
            } else if (cCharAt == '}' && i - 1 == 0) {
                return str.substring(index2, parsePosition.getIndex());
            }
            next(parsePosition);
        }
        throw new IllegalArgumentException(new StringBuffer("Unterminated format element at position ").append(index).toString());
    }

    private String insertFormats(String str, ArrayList arrayList) {
        if (!containsElements(arrayList)) {
            return str;
        }
        StrBuilder strBuilder = new StrBuilder(str.length() * 2);
        ParsePosition parsePosition = new ParsePosition(0);
        int i = -1;
        int i2 = 0;
        while (parsePosition.getIndex() < str.length()) {
            char cCharAt = str.charAt(parsePosition.getIndex());
            if (cCharAt == '\'') {
                appendQuotedString(str, parsePosition, strBuilder, false);
            } else if (cCharAt != '{') {
                if (cCharAt == '}') {
                    i2--;
                }
                strBuilder.append(cCharAt);
                next(parsePosition);
            } else {
                i2++;
                if (i2 == 1) {
                    i++;
                    strBuilder.append(START_FE).append(readArgumentIndex(str, next(parsePosition)));
                    String str2 = (String) arrayList.get(i);
                    if (str2 != null) {
                        strBuilder.append(',').append(str2);
                    }
                }
            }
        }
        return strBuilder.toString();
    }

    private void seekNonWs(String str, ParsePosition parsePosition) {
        char[] charArray = str.toCharArray();
        do {
            int iIsMatch = StrMatcher.splitMatcher().isMatch(charArray, parsePosition.getIndex());
            parsePosition.setIndex(parsePosition.getIndex() + iIsMatch);
            if (iIsMatch <= 0) {
                return;
            }
        } while (parsePosition.getIndex() < str.length());
    }

    private ParsePosition next(ParsePosition parsePosition) {
        parsePosition.setIndex(parsePosition.getIndex() + 1);
        return parsePosition;
    }

    private StrBuilder appendQuotedString(String str, ParsePosition parsePosition, StrBuilder strBuilder, boolean z) {
        int index = parsePosition.getIndex();
        char[] charArray = str.toCharArray();
        if (z && charArray[index] == '\'') {
            next(parsePosition);
            if (strBuilder == null) {
                return null;
            }
            return strBuilder.append(QUOTE);
        }
        int index2 = index;
        for (int index3 = parsePosition.getIndex(); index3 < str.length(); index3++) {
            if (z && str.substring(index3).startsWith(ESCAPED_QUOTE)) {
                strBuilder.append(charArray, index2, parsePosition.getIndex() - index2).append(QUOTE);
                parsePosition.setIndex(index3 + 2);
                index2 = parsePosition.getIndex();
            } else {
                if (charArray[parsePosition.getIndex()] == '\'') {
                    next(parsePosition);
                    if (strBuilder == null) {
                        return null;
                    }
                    return strBuilder.append(charArray, index2, parsePosition.getIndex() - index2);
                }
                next(parsePosition);
            }
        }
        throw new IllegalArgumentException(new StringBuffer("Unterminated quoted string at position ").append(index).toString());
    }

    private void getQuotedString(String str, ParsePosition parsePosition, boolean z) {
        appendQuotedString(str, parsePosition, null, z);
    }

    private boolean containsElements(Collection collection) {
        if (collection != null && collection.size() != 0) {
            Iterator it2 = collection.iterator();
            while (it2.hasNext()) {
                if (it2.next() != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
