package io.grpc.netty.shaded.io.netty.handler.codec.http.cookie;

import io.grpc.netty.shaded.io.netty.util.internal.InternalThreadLocalMap;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: classes3.dex */
public final class ClientCookieEncoder extends CookieEncoder {
    public static final ClientCookieEncoder STRICT = new ClientCookieEncoder(true);
    public static final ClientCookieEncoder LAX = new ClientCookieEncoder(false);
    static final Comparator<Cookie> COOKIE_COMPARATOR = new Comparator<Cookie>() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.ClientCookieEncoder.1
        @Override // java.util.Comparator
        public int compare(Cookie cookie, Cookie cookie2) {
            String strPath = cookie.path();
            String strPath2 = cookie2.path();
            return (strPath2 != null ? strPath2.length() : Integer.MAX_VALUE) - (strPath == null ? Integer.MAX_VALUE : strPath.length());
        }
    };

    private ClientCookieEncoder(boolean z) {
        super(z);
    }

    public String encode(String str, String str2) {
        return encode(new DefaultCookie(str, str2));
    }

    public String encode(Cookie cookie) {
        StringBuilder sbStringBuilder = CookieUtil.stringBuilder();
        encode(sbStringBuilder, (Cookie) ObjectUtil.checkNotNull(cookie, "cookie"));
        return CookieUtil.stripTrailingSeparator(sbStringBuilder);
    }

    public String encode(Cookie... cookieArr) {
        if (((Cookie[]) ObjectUtil.checkNotNull(cookieArr, "cookies")).length == 0) {
            return null;
        }
        StringBuilder sbStringBuilder = CookieUtil.stringBuilder();
        int i = 0;
        if (this.strict) {
            if (cookieArr.length == 1) {
                encode(sbStringBuilder, cookieArr[0]);
            } else {
                Cookie[] cookieArr2 = (Cookie[]) Arrays.copyOf(cookieArr, cookieArr.length);
                Arrays.sort(cookieArr2, COOKIE_COMPARATOR);
                int length = cookieArr2.length;
                while (i < length) {
                    encode(sbStringBuilder, cookieArr2[i]);
                    i++;
                }
            }
        } else {
            int length2 = cookieArr.length;
            while (i < length2) {
                encode(sbStringBuilder, cookieArr[i]);
                i++;
            }
        }
        return CookieUtil.stripTrailingSeparatorOrNull(sbStringBuilder);
    }

    public String encode(Collection<? extends Cookie> collection) {
        if (((Collection) ObjectUtil.checkNotNull(collection, "cookies")).isEmpty()) {
            return null;
        }
        StringBuilder sbStringBuilder = CookieUtil.stringBuilder();
        if (this.strict) {
            if (collection.size() == 1) {
                encode(sbStringBuilder, collection.iterator().next());
            } else {
                Cookie[] cookieArr = (Cookie[]) collection.toArray(new Cookie[0]);
                Arrays.sort(cookieArr, COOKIE_COMPARATOR);
                for (Cookie cookie : cookieArr) {
                    encode(sbStringBuilder, cookie);
                }
            }
        } else {
            Iterator<? extends Cookie> it2 = collection.iterator();
            while (it2.hasNext()) {
                encode(sbStringBuilder, it2.next());
            }
        }
        return CookieUtil.stripTrailingSeparatorOrNull(sbStringBuilder);
    }

    public String encode(Iterable<? extends Cookie> iterable) {
        Iterator it2 = ((Iterable) ObjectUtil.checkNotNull(iterable, "cookies")).iterator();
        if (!it2.hasNext()) {
            return null;
        }
        StringBuilder sbStringBuilder = CookieUtil.stringBuilder();
        if (this.strict) {
            Cookie cookie = (Cookie) it2.next();
            if (!it2.hasNext()) {
                encode(sbStringBuilder, cookie);
            } else {
                ArrayList arrayList = InternalThreadLocalMap.get().arrayList();
                arrayList.add(cookie);
                while (it2.hasNext()) {
                    arrayList.add(it2.next());
                }
                Cookie[] cookieArr = (Cookie[]) arrayList.toArray(new Cookie[0]);
                Arrays.sort(cookieArr, COOKIE_COMPARATOR);
                for (Cookie cookie2 : cookieArr) {
                    encode(sbStringBuilder, cookie2);
                }
            }
        } else {
            while (it2.hasNext()) {
                encode(sbStringBuilder, (Cookie) it2.next());
            }
        }
        return CookieUtil.stripTrailingSeparatorOrNull(sbStringBuilder);
    }

    private void encode(StringBuilder sb, Cookie cookie) {
        String strName = cookie.name();
        String strValue = cookie.value() != null ? cookie.value() : "";
        validateCookie(strName, strValue);
        if (cookie.wrap()) {
            CookieUtil.addQuoted(sb, strName, strValue);
        } else {
            CookieUtil.add(sb, strName, strValue);
        }
    }
}
