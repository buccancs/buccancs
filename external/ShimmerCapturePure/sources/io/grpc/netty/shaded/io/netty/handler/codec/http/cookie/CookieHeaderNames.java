package io.grpc.netty.shaded.io.netty.handler.codec.http.cookie;

/* loaded from: classes3.dex */
public final class CookieHeaderNames {
    public static final String DOMAIN = "Domain";
    public static final String EXPIRES = "Expires";
    public static final String HTTPONLY = "HTTPOnly";
    public static final String MAX_AGE = "Max-Age";
    public static final String PATH = "Path";
    public static final String SAMESITE = "SameSite";
    public static final String SECURE = "Secure";

    private CookieHeaderNames() {
    }

    public enum SameSite {
        Lax,
        Strict,
        None;

        static SameSite of(String str) {
            if (str == null) {
                return null;
            }
            for (SameSite sameSite : (SameSite[]) SameSite.class.getEnumConstants()) {
                if (sameSite.name().equalsIgnoreCase(str)) {
                    return sameSite;
                }
            }
            return null;
        }
    }
}
