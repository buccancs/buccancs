package io.grpc.netty.shaded.io.netty.handler.codec.http;

import com.google.api.client.http.HttpMethods;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.internal.MathUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* loaded from: classes3.dex */
public class HttpMethod implements Comparable<HttpMethod> {
    public static final HttpMethod CONNECT;
    public static final HttpMethod DELETE;
    public static final HttpMethod GET;
    public static final HttpMethod HEAD;
    public static final HttpMethod OPTIONS;
    public static final HttpMethod PATCH;
    public static final HttpMethod POST;
    public static final HttpMethod PUT;
    public static final HttpMethod TRACE;
    private static final EnumNameMap<HttpMethod> methodMap;

    static {
        HttpMethod httpMethod = new HttpMethod(HttpMethods.OPTIONS);
        OPTIONS = httpMethod;
        HttpMethod httpMethod2 = new HttpMethod(HttpMethods.GET);
        GET = httpMethod2;
        HttpMethod httpMethod3 = new HttpMethod(HttpMethods.HEAD);
        HEAD = httpMethod3;
        HttpMethod httpMethod4 = new HttpMethod("POST");
        POST = httpMethod4;
        HttpMethod httpMethod5 = new HttpMethod(HttpMethods.PUT);
        PUT = httpMethod5;
        HttpMethod httpMethod6 = new HttpMethod(HttpMethods.PATCH);
        PATCH = httpMethod6;
        HttpMethod httpMethod7 = new HttpMethod(HttpMethods.DELETE);
        DELETE = httpMethod7;
        HttpMethod httpMethod8 = new HttpMethod(HttpMethods.TRACE);
        TRACE = httpMethod8;
        HttpMethod httpMethod9 = new HttpMethod(HttpMethods.CONNECT);
        CONNECT = httpMethod9;
        methodMap = new EnumNameMap<>(new EnumNameMap.Node(httpMethod.toString(), httpMethod), new EnumNameMap.Node(httpMethod2.toString(), httpMethod2), new EnumNameMap.Node(httpMethod3.toString(), httpMethod3), new EnumNameMap.Node(httpMethod4.toString(), httpMethod4), new EnumNameMap.Node(httpMethod5.toString(), httpMethod5), new EnumNameMap.Node(httpMethod6.toString(), httpMethod6), new EnumNameMap.Node(httpMethod7.toString(), httpMethod7), new EnumNameMap.Node(httpMethod8.toString(), httpMethod8), new EnumNameMap.Node(httpMethod9.toString(), httpMethod9));
    }

    private final AsciiString name;

    public HttpMethod(String str) {
        String strTrim = ((String) ObjectUtil.checkNotNull(str, "name")).trim();
        if (strTrim.isEmpty()) {
            throw new IllegalArgumentException("empty name");
        }
        for (int i = 0; i < strTrim.length(); i++) {
            char cCharAt = strTrim.charAt(i);
            if (Character.isISOControl(cCharAt) || Character.isWhitespace(cCharAt)) {
                throw new IllegalArgumentException("invalid character in name");
            }
        }
        this.name = AsciiString.cached(strTrim);
    }

    public static HttpMethod valueOf(String str) {
        HttpMethod httpMethod = methodMap.get(str);
        return httpMethod != null ? httpMethod : new HttpMethod(str);
    }

    public AsciiString asciiName() {
        return this.name;
    }

    public String name() {
        return this.name.toString();
    }

    public int hashCode() {
        return name().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HttpMethod) {
            return name().equals(((HttpMethod) obj).name());
        }
        return false;
    }

    public String toString() {
        return this.name.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(HttpMethod httpMethod) {
        if (httpMethod == this) {
            return 0;
        }
        return name().compareTo(httpMethod.name());
    }

    private static final class EnumNameMap<T> {
        private final Node<T>[] values;
        private final int valuesMask;

        EnumNameMap(Node<T>... nodeArr) {
            this.values = new Node[MathUtil.findNextPositivePowerOfTwo(nodeArr.length)];
            this.valuesMask = r0.length - 1;
            for (Node<T> node : nodeArr) {
                int iHashCode = hashCode(node.key) & this.valuesMask;
                Node<T>[] nodeArr2 = this.values;
                if (nodeArr2[iHashCode] != null) {
                    throw new IllegalArgumentException("index " + iHashCode + " collision between values: [" + this.values[iHashCode].key + ", " + node.key + ']');
                }
                nodeArr2[iHashCode] = node;
            }
        }

        private static int hashCode(String str) {
            return str.hashCode() >>> 6;
        }

        T get(String str) {
            Node<T> node = this.values[hashCode(str) & this.valuesMask];
            if (node == null || !node.key.equals(str)) {
                return null;
            }
            return node.value;
        }

        private static final class Node<T> {
            final String key;
            final T value;

            Node(String str, T t) {
                this.key = str;
                this.value = t;
            }
        }
    }
}
