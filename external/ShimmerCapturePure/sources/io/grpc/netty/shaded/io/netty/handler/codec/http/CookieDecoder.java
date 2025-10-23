package io.grpc.netty.shaded.io.netty.handler.codec.http;

import com.shimmerresearch.verisense.UtilVerisenseDriver;
import io.grpc.netty.shaded.io.netty.handler.codec.DateFormatter;
import io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.CookieHeaderNames;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Deprecated
/* loaded from: classes3.dex */
public final class CookieDecoder {
    private static final String COMMENT = "Comment";
    private static final String COMMENTURL = "CommentURL";
    private static final String DISCARD = "Discard";
    private static final String PORT = "Port";
    private static final String VERSION = "Version";
    private static final CookieDecoder STRICT = new CookieDecoder(true);
    private static final CookieDecoder LAX = new CookieDecoder(false);
    private final InternalLogger logger = InternalLoggerFactory.getInstance(getClass());
    private final boolean strict;

    private CookieDecoder(boolean z) {
        this.strict = z;
    }

    public static Set<Cookie> decode(String str) {
        return decode(str, true);
    }

    public static Set<Cookie> decode(String str, boolean z) {
        return (z ? STRICT : LAX).doDecode(str);
    }

    private static void extractKeyValuePairs(String str, List<String> list, List<String> list2) {
        String strSubstring;
        String string;
        String strSubstring2;
        int length = str.length();
        int i = 0;
        while (i != length) {
            char cCharAt = str.charAt(i);
            if (cCharAt != ' ' && cCharAt != ',' && cCharAt != ';') {
                switch (cCharAt) {
                    case '\t':
                    case '\n':
                    case 11:
                    case '\f':
                    case '\r':
                        break;
                    default:
                        while (i != length) {
                            if (str.charAt(i) == '$') {
                                i++;
                            } else {
                                String str2 = null;
                                if (i == length) {
                                    string = null;
                                } else {
                                    int i2 = i;
                                    while (true) {
                                        char cCharAt2 = str.charAt(i2);
                                        if (cCharAt2 == ';') {
                                            strSubstring = str.substring(i, i2);
                                        } else if (cCharAt2 != '=') {
                                            i2++;
                                            if (i2 == length) {
                                                strSubstring = str.substring(i);
                                            }
                                        } else {
                                            String strSubstring3 = str.substring(i, i2);
                                            i = i2 + 1;
                                            if (i == length) {
                                                string = "";
                                            } else {
                                                char cCharAt3 = str.charAt(i);
                                                if (cCharAt3 == '\"' || cCharAt3 == '\'') {
                                                    StringBuilder sb = new StringBuilder(str.length() - i);
                                                    int i3 = i2 + 2;
                                                    while (true) {
                                                        boolean z = false;
                                                        while (i3 != length) {
                                                            if (z) {
                                                                int i4 = i3 + 1;
                                                                char cCharAt4 = str.charAt(i3);
                                                                if (cCharAt4 == '\"' || cCharAt4 == '\'' || cCharAt4 == '\\') {
                                                                    sb.setCharAt(sb.length() - 1, cCharAt4);
                                                                } else {
                                                                    sb.append(cCharAt4);
                                                                }
                                                                i3 = i4;
                                                            } else {
                                                                int i5 = i3 + 1;
                                                                char cCharAt5 = str.charAt(i3);
                                                                if (cCharAt5 == cCharAt3) {
                                                                    string = sb.toString();
                                                                    str2 = strSubstring3;
                                                                    i = i5;
                                                                } else {
                                                                    sb.append(cCharAt5);
                                                                    if (cCharAt5 == '\\') {
                                                                        i3 = i5;
                                                                        z = true;
                                                                    } else {
                                                                        i3 = i5;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        string = sb.toString();
                                                        i = i3;
                                                    }
                                                } else {
                                                    int iIndexOf = str.indexOf(59, i);
                                                    if (iIndexOf > 0) {
                                                        strSubstring2 = str.substring(i, iIndexOf);
                                                    } else {
                                                        strSubstring2 = str.substring(i);
                                                        iIndexOf = length;
                                                    }
                                                    string = strSubstring2;
                                                    i = iIndexOf;
                                                }
                                            }
                                            str2 = strSubstring3;
                                        }
                                    }
                                    string = null;
                                    str2 = strSubstring;
                                    i = i2;
                                }
                                list.add(str2);
                                list2.add(string);
                                continue;
                            }
                        }
                        return;
                }
            }
            i++;
        }
    }

    private Set<Cookie> doDecode(String str) throws NumberFormatException {
        int i;
        int i2;
        ArrayList arrayList;
        ArrayList arrayList2;
        boolean z;
        ArrayList arrayList3 = new ArrayList(8);
        ArrayList arrayList4 = new ArrayList(8);
        extractKeyValuePairs(str, arrayList3, arrayList4);
        if (arrayList3.isEmpty()) {
            return Collections.emptySet();
        }
        boolean z2 = false;
        if (((String) arrayList3.get(0)).equalsIgnoreCase(VERSION)) {
            try {
                i = Integer.parseInt((String) arrayList4.get(0));
            } catch (NumberFormatException unused) {
                i = 0;
            }
            i2 = 1;
        } else {
            i = 0;
            i2 = 0;
        }
        if (arrayList3.size() <= i2) {
            return Collections.emptySet();
        }
        TreeSet treeSet = new TreeSet();
        while (i2 < arrayList3.size()) {
            String str2 = (String) arrayList3.get(i2);
            String str3 = (String) arrayList4.get(i2);
            if (str3 == null) {
                str3 = "";
            }
            DefaultCookie defaultCookieInitCookie = initCookie(str2, str3);
            if (defaultCookieInitCookie == null) {
                break;
            }
            ArrayList arrayList5 = new ArrayList(2);
            int i3 = i2 + 1;
            String str4 = null;
            TreeSet treeSet2 = treeSet;
            String str5 = null;
            long j = Long.MIN_VALUE;
            boolean z3 = false;
            boolean z4 = false;
            String str6 = null;
            String str7 = null;
            while (true) {
                if (i3 >= arrayList3.size()) {
                    arrayList = arrayList3;
                    arrayList2 = arrayList4;
                    z = z2;
                    break;
                }
                String str8 = (String) arrayList3.get(i3);
                arrayList = arrayList3;
                String str9 = (String) arrayList4.get(i3);
                arrayList2 = arrayList4;
                if (DISCARD.equalsIgnoreCase(str8)) {
                    z2 = true;
                } else if (CookieHeaderNames.SECURE.equalsIgnoreCase(str8)) {
                    z3 = true;
                } else if (CookieHeaderNames.HTTPONLY.equalsIgnoreCase(str8)) {
                    z4 = true;
                } else if (COMMENT.equalsIgnoreCase(str8)) {
                    str4 = str9;
                } else if (COMMENTURL.equalsIgnoreCase(str8)) {
                    str6 = str9;
                } else if (CookieHeaderNames.DOMAIN.equalsIgnoreCase(str8)) {
                    str7 = str9;
                } else if (CookieHeaderNames.PATH.equalsIgnoreCase(str8)) {
                    str5 = str9;
                } else {
                    if ("Expires".equalsIgnoreCase(str8)) {
                        Date httpDate = DateFormatter.parseHttpDate(str9);
                        if (httpDate != null) {
                            long time = httpDate.getTime() - System.currentTimeMillis();
                            z = z2;
                            j = (time / 1000) + (time % 1000 != 0 ? 1 : 0);
                        } else {
                            z = z2;
                        }
                    } else {
                        z = z2;
                        if (CookieHeaderNames.MAX_AGE.equalsIgnoreCase(str8)) {
                            j = Integer.parseInt(str9);
                        } else if (VERSION.equalsIgnoreCase(str8)) {
                            i = Integer.parseInt(str9);
                        } else {
                            if (!PORT.equalsIgnoreCase(str8)) {
                                break;
                            }
                            String[] strArrSplit = str9.split(UtilVerisenseDriver.CSV_DELIMITER);
                            int length = strArrSplit.length;
                            int i4 = 0;
                            while (i4 < length) {
                                String[] strArr = strArrSplit;
                                try {
                                    arrayList5.add(Integer.valueOf(strArrSplit[i4]));
                                } catch (NumberFormatException unused2) {
                                }
                                i4++;
                                strArrSplit = strArr;
                            }
                        }
                    }
                    z2 = z;
                }
                i3++;
                i2++;
                arrayList4 = arrayList2;
                arrayList3 = arrayList;
            }
            defaultCookieInitCookie.setVersion(i);
            defaultCookieInitCookie.setMaxAge(j);
            defaultCookieInitCookie.setPath(str5);
            defaultCookieInitCookie.setDomain(str7);
            defaultCookieInitCookie.setSecure(z3);
            defaultCookieInitCookie.setHttpOnly(z4);
            if (i > 0) {
                defaultCookieInitCookie.setComment(str4);
            }
            if (i > 1) {
                defaultCookieInitCookie.setCommentUrl(str6);
                defaultCookieInitCookie.setPorts(arrayList5);
                defaultCookieInitCookie.setDiscard(z);
            }
            treeSet2.add(defaultCookieInitCookie);
            i2++;
            treeSet = treeSet2;
            arrayList4 = arrayList2;
            arrayList3 = arrayList;
            z2 = false;
        }
        return treeSet;
    }

    private DefaultCookie initCookie(String str, String str2) {
        int iFirstInvalidCookieValueOctet;
        int iFirstInvalidCookieNameOctet;
        if (str == null || str.length() == 0) {
            this.logger.debug("Skipping cookie with null name");
            return null;
        }
        if (str2 == null) {
            this.logger.debug("Skipping cookie with null value");
            return null;
        }
        CharSequence charSequenceUnwrapValue = CookieUtil.unwrapValue(str2);
        if (charSequenceUnwrapValue == null) {
            this.logger.debug("Skipping cookie because starting quotes are not properly balanced in '{}'", charSequenceUnwrapValue);
            return null;
        }
        if (this.strict && (iFirstInvalidCookieNameOctet = CookieUtil.firstInvalidCookieNameOctet(str)) >= 0) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Skipping cookie because name '{}' contains invalid char '{}'", str, Character.valueOf(str.charAt(iFirstInvalidCookieNameOctet)));
            }
            return null;
        }
        boolean z = charSequenceUnwrapValue.length() != str2.length();
        if (this.strict && (iFirstInvalidCookieValueOctet = CookieUtil.firstInvalidCookieValueOctet(charSequenceUnwrapValue)) >= 0) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Skipping cookie because value '{}' contains invalid char '{}'", charSequenceUnwrapValue, Character.valueOf(charSequenceUnwrapValue.charAt(iFirstInvalidCookieValueOctet)));
            }
            return null;
        }
        DefaultCookie defaultCookie = new DefaultCookie(str, charSequenceUnwrapValue.toString());
        defaultCookie.setWrap(z);
        return defaultCookie;
    }
}
