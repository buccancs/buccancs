package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
final class CipherSuiteConverter {
    private static final Map<String, String> j2oTls13;
    private static final Map<String, Map<String, String>> o2jTls13;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) CipherSuiteConverter.class);
    private static final Pattern JAVA_CIPHERSUITE_PATTERN = Pattern.compile("^(?:TLS|SSL)_((?:(?!_WITH_).)+)_WITH_(.*)_(.*)$");
    private static final Pattern OPENSSL_CIPHERSUITE_PATTERN = Pattern.compile("^(?:((?:(?:EXP-)?(?:(?:DHE|EDH|ECDH|ECDHE|SRP|RSA)-(?:DSS|RSA|ECDSA|PSK)|(?:ADH|AECDH|KRB5|PSK|SRP)))|EXP)-)?(.*)-(.*)$");
    private static final Pattern JAVA_AES_CBC_PATTERN = Pattern.compile("^(AES)_([0-9]+)_CBC$");
    private static final Pattern JAVA_AES_PATTERN = Pattern.compile("^(AES)_([0-9]+)_(.*)$");
    private static final Pattern OPENSSL_AES_CBC_PATTERN = Pattern.compile("^(AES)([0-9]+)$");
    private static final Pattern OPENSSL_AES_PATTERN = Pattern.compile("^(AES)([0-9]+)-(.*)$");
    private static final ConcurrentMap<String, String> j2o = PlatformDependent.newConcurrentHashMap();
    private static final ConcurrentMap<String, Map<String, String>> o2j = PlatformDependent.newConcurrentHashMap();

    static {
        HashMap map = new HashMap();
        map.put("TLS_AES_128_GCM_SHA256", "AEAD-AES128-GCM-SHA256");
        map.put("TLS_AES_256_GCM_SHA384", "AEAD-AES256-GCM-SHA384");
        map.put("TLS_CHACHA20_POLY1305_SHA256", "AEAD-CHACHA20-POLY1305-SHA256");
        j2oTls13 = Collections.unmodifiableMap(map);
        HashMap map2 = new HashMap();
        map2.put("TLS_AES_128_GCM_SHA256", Collections.singletonMap("TLS", "TLS_AES_128_GCM_SHA256"));
        map2.put("TLS_AES_256_GCM_SHA384", Collections.singletonMap("TLS", "TLS_AES_256_GCM_SHA384"));
        map2.put("TLS_CHACHA20_POLY1305_SHA256", Collections.singletonMap("TLS", "TLS_CHACHA20_POLY1305_SHA256"));
        map2.put("AEAD-AES128-GCM-SHA256", Collections.singletonMap("TLS", "TLS_AES_128_GCM_SHA256"));
        map2.put("AEAD-AES256-GCM-SHA384", Collections.singletonMap("TLS", "TLS_AES_256_GCM_SHA384"));
        map2.put("AEAD-CHACHA20-POLY1305-SHA256", Collections.singletonMap("TLS", "TLS_CHACHA20_POLY1305_SHA256"));
        o2jTls13 = Collections.unmodifiableMap(map2);
    }

    private CipherSuiteConverter() {
    }

    private static String toJavaHmacAlgo(String str) {
        return str;
    }

    private static String toOpenSslHmacAlgo(String str) {
        return str;
    }

    static void clearCache() {
        j2o.clear();
        o2j.clear();
    }

    static boolean isJ2OCached(String str, String str2) {
        return str2.equals(j2o.get(str));
    }

    static boolean isO2JCached(String str, String str2, String str3) {
        Map<String, String> map = o2j.get(str);
        if (map == null) {
            return false;
        }
        return str3.equals(map.get(str2));
    }

    static String toOpenSsl(String str, boolean z) {
        String str2 = j2o.get(str);
        return str2 != null ? str2 : cacheFromJava(str, z);
    }

    private static String cacheFromJava(String str, boolean z) {
        String str2 = j2oTls13.get(str);
        if (str2 != null) {
            return z ? str2 : str;
        }
        String openSslUncached = toOpenSslUncached(str, z);
        if (openSslUncached == null) {
            return null;
        }
        j2o.putIfAbsent(str, openSslUncached);
        String strSubstring = str.substring(4);
        HashMap map = new HashMap(4);
        map.put("", strSubstring);
        map.put("SSL", "SSL_" + strSubstring);
        map.put("TLS", "TLS_" + strSubstring);
        o2j.put(openSslUncached, map);
        logger.debug("Cipher suite mapping: {} => {}", str, openSslUncached);
        return openSslUncached;
    }

    static String toOpenSslUncached(String str, boolean z) {
        String str2 = j2oTls13.get(str);
        if (str2 != null) {
            return z ? str2 : str;
        }
        Matcher matcher = JAVA_CIPHERSUITE_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        String openSslHandshakeAlgo = toOpenSslHandshakeAlgo(matcher.group(1));
        String openSslBulkCipher = toOpenSslBulkCipher(matcher.group(2));
        String openSslHmacAlgo = toOpenSslHmacAlgo(matcher.group(3));
        if (openSslHandshakeAlgo.isEmpty()) {
            return openSslBulkCipher + '-' + openSslHmacAlgo;
        }
        if (openSslBulkCipher.contains("CHACHA20")) {
            return openSslHandshakeAlgo + '-' + openSslBulkCipher;
        }
        return openSslHandshakeAlgo + '-' + openSslBulkCipher + '-' + openSslHmacAlgo;
    }

    private static String toOpenSslHandshakeAlgo(String str) {
        boolean zEndsWith = str.endsWith("_EXPORT");
        if (zEndsWith) {
            str = str.substring(0, str.length() - 7);
        }
        if ("RSA".equals(str)) {
            str = "";
        } else if (str.endsWith("_anon")) {
            str = "A" + str.substring(0, str.length() - 5);
        }
        if (zEndsWith) {
            if (str.isEmpty()) {
                str = "EXP";
            } else {
                str = "EXP-" + str;
            }
        }
        return str.replace('_', '-');
    }

    private static String toOpenSslBulkCipher(String str) {
        if (str.startsWith("AES_")) {
            Matcher matcher = JAVA_AES_CBC_PATTERN.matcher(str);
            if (matcher.matches()) {
                return matcher.replaceFirst("$1$2");
            }
            Matcher matcher2 = JAVA_AES_PATTERN.matcher(str);
            if (matcher2.matches()) {
                return matcher2.replaceFirst("$1$2-$3");
            }
        }
        return "3DES_EDE_CBC".equals(str) ? "DES-CBC3" : ("RC4_128".equals(str) || "RC4_40".equals(str)) ? "RC4" : ("DES40_CBC".equals(str) || "DES_CBC_40".equals(str)) ? "DES-CBC" : "RC2_CBC_40".equals(str) ? "RC2-CBC" : str.replace('_', '-');
    }

    static String toJava(String str, String str2) {
        Map<String, String> mapCacheFromOpenSsl = o2j.get(str);
        if (mapCacheFromOpenSsl == null && (mapCacheFromOpenSsl = cacheFromOpenSsl(str)) == null) {
            return null;
        }
        String str3 = mapCacheFromOpenSsl.get(str2);
        if (str3 != null) {
            return str3;
        }
        String str4 = mapCacheFromOpenSsl.get("");
        if (str4 == null) {
            return null;
        }
        return str2 + '_' + str4;
    }

    private static Map<String, String> cacheFromOpenSsl(String str) {
        Map<String, String> map = o2jTls13.get(str);
        if (map != null) {
            return map;
        }
        String javaUncached0 = toJavaUncached0(str, false);
        if (javaUncached0 == null) {
            return null;
        }
        String str2 = "SSL_" + javaUncached0;
        String str3 = "TLS_" + javaUncached0;
        HashMap map2 = new HashMap(4);
        map2.put("", javaUncached0);
        map2.put("SSL", str2);
        map2.put("TLS", str3);
        o2j.putIfAbsent(str, map2);
        ConcurrentMap<String, String> concurrentMap = j2o;
        concurrentMap.putIfAbsent(str3, str);
        concurrentMap.putIfAbsent(str2, str);
        InternalLogger internalLogger = logger;
        internalLogger.debug("Cipher suite mapping: {} => {}", str3, str);
        internalLogger.debug("Cipher suite mapping: {} => {}", str2, str);
        return map2;
    }

    static String toJavaUncached(String str) {
        return toJavaUncached0(str, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String toJavaUncached0(java.lang.String r4, boolean r5) {
        /*
            if (r5 == 0) goto L15
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> r5 = io.grpc.netty.shaded.io.netty.handler.ssl.CipherSuiteConverter.o2jTls13
            java.lang.Object r5 = r5.get(r4)
            java.util.Map r5 = (java.util.Map) r5
            if (r5 == 0) goto L15
            java.lang.String r4 = "TLS"
            java.lang.Object r4 = r5.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            return r4
        L15:
            java.util.regex.Pattern r5 = io.grpc.netty.shaded.io.netty.handler.ssl.CipherSuiteConverter.OPENSSL_CIPHERSUITE_PATTERN
            java.util.regex.Matcher r4 = r5.matcher(r4)
            boolean r5 = r4.matches()
            if (r5 != 0) goto L23
            r4 = 0
            return r4
        L23:
            r5 = 1
            java.lang.String r0 = r4.group(r5)
            r1 = 0
            java.lang.String r2 = ""
            if (r0 != 0) goto L30
            r0 = r2
        L2e:
            r5 = 0
            goto L47
        L30:
            java.lang.String r3 = "EXP-"
            boolean r3 = r0.startsWith(r3)
            if (r3 == 0) goto L3e
            r1 = 4
            java.lang.String r0 = r0.substring(r1)
            goto L47
        L3e:
            java.lang.String r3 = "EXP"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L2e
            r0 = r2
        L47:
            java.lang.String r0 = toJavaHandshakeAlgo(r0, r5)
            r1 = 2
            java.lang.String r1 = r4.group(r1)
            java.lang.String r5 = toJavaBulkCipher(r1, r5)
            r1 = 3
            java.lang.String r4 = r4.group(r1)
            java.lang.String r4 = toJavaHmacAlgo(r4)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "_WITH_"
            r1.append(r0)
            r1.append(r5)
            r0 = 95
            r1.append(r0)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            java.lang.String r0 = "CHACHA20"
            boolean r5 = r5.contains(r0)
            if (r5 == 0) goto L92
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r4)
            java.lang.String r4 = "_SHA256"
            r5.append(r4)
            java.lang.String r4 = r5.toString()
        L92:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.ssl.CipherSuiteConverter.toJavaUncached0(java.lang.String, boolean):java.lang.String");
    }

    private static String toJavaHandshakeAlgo(String str, boolean z) {
        if (str.isEmpty()) {
            str = "RSA";
        } else if ("ADH".equals(str)) {
            str = "DH_anon";
        } else if ("AECDH".equals(str)) {
            str = "ECDH_anon";
        }
        String strReplace = str.replace('-', '_');
        if (!z) {
            return strReplace;
        }
        return strReplace + "_EXPORT";
    }

    private static String toJavaBulkCipher(String str, boolean z) {
        if (str.startsWith("AES")) {
            Matcher matcher = OPENSSL_AES_CBC_PATTERN.matcher(str);
            if (matcher.matches()) {
                return matcher.replaceFirst("$1_$2_CBC");
            }
            Matcher matcher2 = OPENSSL_AES_PATTERN.matcher(str);
            if (matcher2.matches()) {
                return matcher2.replaceFirst("$1_$2_$3");
            }
        }
        if ("DES-CBC3".equals(str)) {
            return "3DES_EDE_CBC";
        }
        if ("RC4".equals(str)) {
            return z ? "RC4_40" : "RC4_128";
        }
        if ("DES-CBC".equals(str)) {
            return z ? "DES_CBC_40" : "DES_CBC";
        }
        if ("RC2-CBC".equals(str)) {
            return z ? "RC2_CBC_40" : "RC2_CBC";
        }
        return str.replace('-', '_');
    }

    static void convertToCipherStrings(Iterable<String> iterable, StringBuilder sb, StringBuilder sb2, boolean z) {
        String next;
        Iterator<String> it2 = iterable.iterator();
        while (it2.hasNext() && (next = it2.next()) != null) {
            String openSsl = toOpenSsl(next, z);
            if (openSsl == null) {
                openSsl = next;
            }
            if (!OpenSsl.isCipherSuiteAvailable(openSsl)) {
                throw new IllegalArgumentException("unsupported cipher suite: " + next + '(' + openSsl + ')');
            }
            if (SslUtils.isTLSv13Cipher(openSsl) || SslUtils.isTLSv13Cipher(next)) {
                sb2.append(openSsl);
                sb2.append(':');
            } else {
                sb.append(openSsl);
                sb.append(':');
            }
        }
        if (sb.length() == 0 && sb2.length() == 0) {
            throw new IllegalArgumentException("empty cipher suites");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        if (sb2.length() > 0) {
            sb2.setLength(sb2.length() - 1);
        }
    }
}
