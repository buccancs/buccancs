package io.grpc.netty.shaded.io.netty.handler.codec.http.cookie;

import io.grpc.netty.shaded.io.netty.handler.codec.DateFormatter;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public final class ServerCookieEncoder extends CookieEncoder {
    public static final ServerCookieEncoder STRICT = new ServerCookieEncoder(true);
    public static final ServerCookieEncoder LAX = new ServerCookieEncoder(false);

    private ServerCookieEncoder(boolean z) {
        super(z);
    }

    private static List<String> dedup(List<String> list, Map<String, Integer> map) {
        boolean[] zArr = new boolean[list.size()];
        Iterator<Integer> it2 = map.values().iterator();
        while (it2.hasNext()) {
            zArr[it2.next().intValue()] = true;
        }
        ArrayList arrayList = new ArrayList(map.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (zArr[i]) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    public String encode(String str, String str2) {
        return encode(new DefaultCookie(str, str2));
    }

    public String encode(Cookie cookie) {
        String strName = ((Cookie) ObjectUtil.checkNotNull(cookie, "cookie")).name();
        String strValue = cookie.value() != null ? cookie.value() : "";
        validateCookie(strName, strValue);
        StringBuilder sbStringBuilder = CookieUtil.stringBuilder();
        if (cookie.wrap()) {
            CookieUtil.addQuoted(sbStringBuilder, strName, strValue);
        } else {
            CookieUtil.add(sbStringBuilder, strName, strValue);
        }
        if (cookie.maxAge() != Long.MIN_VALUE) {
            CookieUtil.add(sbStringBuilder, CookieHeaderNames.MAX_AGE, cookie.maxAge());
            Date date = new Date((cookie.maxAge() * 1000) + System.currentTimeMillis());
            sbStringBuilder.append("Expires");
            sbStringBuilder.append('=');
            DateFormatter.append(date, sbStringBuilder);
            sbStringBuilder.append(';');
            sbStringBuilder.append(' ');
        }
        if (cookie.path() != null) {
            CookieUtil.add(sbStringBuilder, CookieHeaderNames.PATH, cookie.path());
        }
        if (cookie.domain() != null) {
            CookieUtil.add(sbStringBuilder, CookieHeaderNames.DOMAIN, cookie.domain());
        }
        if (cookie.isSecure()) {
            CookieUtil.add(sbStringBuilder, CookieHeaderNames.SECURE);
        }
        if (cookie.isHttpOnly()) {
            CookieUtil.add(sbStringBuilder, CookieHeaderNames.HTTPONLY);
        }
        if (cookie instanceof DefaultCookie) {
            DefaultCookie defaultCookie = (DefaultCookie) cookie;
            if (defaultCookie.sameSite() != null) {
                CookieUtil.add(sbStringBuilder, CookieHeaderNames.SAMESITE, defaultCookie.sameSite().name());
            }
        }
        return CookieUtil.stripTrailingSeparator(sbStringBuilder);
    }

    public List<String> encode(Cookie... cookieArr) {
        if (((Cookie[]) ObjectUtil.checkNotNull(cookieArr, "cookies")).length == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(cookieArr.length);
        HashMap map = (!this.strict || cookieArr.length <= 1) ? null : new HashMap();
        boolean z = false;
        for (int i = 0; i < cookieArr.length; i++) {
            Cookie cookie = cookieArr[i];
            arrayList.add(encode(cookie));
            if (map != null) {
                z |= map.put(cookie.name(), Integer.valueOf(i)) != null;
            }
        }
        return z ? dedup(arrayList, map) : arrayList;
    }

    public List<String> encode(Collection<? extends Cookie> collection) {
        if (((Collection) ObjectUtil.checkNotNull(collection, "cookies")).isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        HashMap map = (!this.strict || collection.size() <= 1) ? null : new HashMap();
        boolean z = false;
        int i = 0;
        for (Cookie cookie : collection) {
            arrayList.add(encode(cookie));
            if (map != null) {
                int i2 = i + 1;
                z |= map.put(cookie.name(), Integer.valueOf(i)) != null;
                i = i2;
            }
        }
        return z ? dedup(arrayList, map) : arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<java.lang.String> encode(java.lang.Iterable<? extends io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.Cookie> r9) {
        /*
            r8 = this;
            java.lang.String r0 = "cookies"
            java.lang.Object r9 = io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil.checkNotNull(r9, r0)
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.Iterator r9 = r9.iterator()
            boolean r0 = r9.hasNext()
            if (r0 != 0) goto L17
            java.util.List r9 = java.util.Collections.emptyList()
            return r9
        L17:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.Object r1 = r9.next()
            io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.Cookie r1 = (io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.Cookie) r1
            boolean r2 = r8.strict
            if (r2 == 0) goto L32
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L32
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            goto L33
        L32:
            r2 = 0
        L33:
            java.lang.String r3 = r8.encode(r1)
            r0.add(r3)
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L51
            java.lang.String r1 = r1.name()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            java.lang.Object r1 = r2.put(r1, r5)
            if (r1 == 0) goto L4f
            r1 = 1
            r5 = 1
            goto L53
        L4f:
            r1 = 1
            goto L52
        L51:
            r1 = 0
        L52:
            r5 = 0
        L53:
            boolean r6 = r9.hasNext()
            if (r6 == 0) goto L7f
            java.lang.Object r6 = r9.next()
            io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.Cookie r6 = (io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.Cookie) r6
            java.lang.String r7 = r8.encode(r6)
            r0.add(r7)
            if (r2 == 0) goto L53
            java.lang.String r6 = r6.name()
            int r7 = r1 + 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object r1 = r2.put(r6, r1)
            if (r1 == 0) goto L7a
            r1 = 1
            goto L7b
        L7a:
            r1 = 0
        L7b:
            r1 = r1 | r5
            r5 = r1
            r1 = r7
            goto L53
        L7f:
            if (r5 == 0) goto L85
            java.util.List r0 = dedup(r0, r2)
        L85:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.ServerCookieEncoder.encode(java.lang.Iterable):java.util.List");
    }
}
