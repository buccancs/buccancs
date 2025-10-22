package io.grpc.netty.shaded.io.netty.handler.codec.http.cookie;

import io.grpc.netty.shaded.io.netty.handler.codec.DateFormatter;
import io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.CookieHeaderNames;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.Date;

/* loaded from: classes3.dex */
public final class ClientCookieDecoder extends CookieDecoder {
    public static final ClientCookieDecoder STRICT = new ClientCookieDecoder(true);
    public static final ClientCookieDecoder LAX = new ClientCookieDecoder(false);

    private ClientCookieDecoder(boolean z) {
        super(z);
    }

    public Cookie decode(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        int length = ((String) ObjectUtil.checkNotNull(str, "header")).length();
        if (length == 0) {
            return null;
        }
        CookieBuilder cookieBuilder = null;
        int i5 = 0;
        while (i5 != length) {
            char cCharAt = str.charAt(i5);
            if (cCharAt == ',') {
                break;
            }
            if (cCharAt == '\t' || cCharAt == '\n' || cCharAt == 11 || cCharAt == '\f' || cCharAt == '\r' || cCharAt == ' ' || cCharAt == ';') {
                i5++;
            } else {
                int i6 = i5;
                while (true) {
                    char cCharAt2 = str.charAt(i6);
                    if (cCharAt2 == ';') {
                        i = i6;
                        i2 = i;
                        break;
                    }
                    if (cCharAt2 == '=') {
                        i3 = i6 + 1;
                        if (i3 == length) {
                            i = i6;
                            i2 = i3;
                            i4 = 0;
                            i3 = 0;
                        } else {
                            int iIndexOf = str.indexOf(59, i3);
                            i2 = iIndexOf > 0 ? iIndexOf : length;
                            i = i6;
                            i4 = i2;
                        }
                    } else {
                        i6++;
                        if (i6 == length) {
                            i = length;
                            i2 = i6;
                            break;
                        }
                    }
                }
                i4 = -1;
                i3 = -1;
                if (i4 > 0 && str.charAt(i4 - 1) == ',') {
                    i4--;
                }
                int i7 = i4;
                if (cookieBuilder == null) {
                    DefaultCookie defaultCookieInitCookie = initCookie(str, i5, i, i3, i7);
                    if (defaultCookieInitCookie == null) {
                        return null;
                    }
                    cookieBuilder = new CookieBuilder(defaultCookieInitCookie, str);
                } else {
                    cookieBuilder.appendAttribute(i5, i, i3, i7);
                }
                i5 = i2;
            }
        }
        if (cookieBuilder != null) {
            return cookieBuilder.cookie();
        }
        return null;
    }

    private static class CookieBuilder {
        private final DefaultCookie cookie;
        private final String header;
        private String domain;
        private int expiresEnd;
        private int expiresStart;
        private boolean httpOnly;
        private long maxAge = Long.MIN_VALUE;
        private String path;
        private CookieHeaderNames.SameSite sameSite;
        private boolean secure;

        CookieBuilder(DefaultCookie defaultCookie, String str) {
            this.cookie = defaultCookie;
            this.header = str;
        }

        private static boolean isValueDefined(int i, int i2) {
            return (i == -1 || i == i2) ? false : true;
        }

        private long mergeMaxAgeAndExpires() {
            Date httpDate;
            long j = this.maxAge;
            if (j != Long.MIN_VALUE) {
                return j;
            }
            if (!isValueDefined(this.expiresStart, this.expiresEnd) || (httpDate = DateFormatter.parseHttpDate(this.header, this.expiresStart, this.expiresEnd)) == null) {
                return Long.MIN_VALUE;
            }
            long time = httpDate.getTime() - System.currentTimeMillis();
            return (time / 1000) + (time % 1000 != 0 ? 1 : 0);
        }

        Cookie cookie() {
            this.cookie.setDomain(this.domain);
            this.cookie.setPath(this.path);
            this.cookie.setMaxAge(mergeMaxAgeAndExpires());
            this.cookie.setSecure(this.secure);
            this.cookie.setHttpOnly(this.httpOnly);
            this.cookie.setSameSite(this.sameSite);
            return this.cookie;
        }

        void appendAttribute(int i, int i2, int i3, int i4) {
            int i5 = i2 - i;
            if (i5 == 4) {
                parse4(i, i3, i4);
                return;
            }
            if (i5 == 6) {
                parse6(i, i3, i4);
            } else if (i5 == 7) {
                parse7(i, i3, i4);
            } else if (i5 == 8) {
                parse8(i, i3, i4);
            }
        }

        private void parse4(int i, int i2, int i3) {
            if (this.header.regionMatches(true, i, CookieHeaderNames.PATH, 0, 4)) {
                this.path = computeValue(i2, i3);
            }
        }

        private void parse6(int i, int i2, int i3) {
            if (this.header.regionMatches(true, i, CookieHeaderNames.DOMAIN, 0, 5)) {
                this.domain = computeValue(i2, i3);
            } else if (this.header.regionMatches(true, i, CookieHeaderNames.SECURE, 0, 5)) {
                this.secure = true;
            }
        }

        private void setMaxAge(String str) {
            try {
                this.maxAge = Math.max(Long.parseLong(str), 0L);
            } catch (NumberFormatException unused) {
            }
        }

        private void parse7(int i, int i2, int i3) {
            if (this.header.regionMatches(true, i, "Expires", 0, 7)) {
                this.expiresStart = i2;
                this.expiresEnd = i3;
            } else if (this.header.regionMatches(true, i, CookieHeaderNames.MAX_AGE, 0, 7)) {
                setMaxAge(computeValue(i2, i3));
            }
        }

        private void parse8(int i, int i2, int i3) {
            if (this.header.regionMatches(true, i, CookieHeaderNames.HTTPONLY, 0, 8)) {
                this.httpOnly = true;
            } else if (this.header.regionMatches(true, i, CookieHeaderNames.SAMESITE, 0, 8)) {
                this.sameSite = CookieHeaderNames.SameSite.of(computeValue(i2, i3));
            }
        }

        private String computeValue(int i, int i2) {
            if (isValueDefined(i, i2)) {
                return this.header.substring(i, i2);
            }
            return null;
        }
    }
}
