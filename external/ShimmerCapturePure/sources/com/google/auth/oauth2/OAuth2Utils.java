package com.google.auth.oauth2;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.auth.http.HttpTransportFactory;
import com.google.common.io.ByteStreams;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
class OAuth2Utils {
    static final String BEARER_PREFIX = "Bearer ";
    static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    static final URI TOKEN_SERVER_URI = URI.create("https://oauth2.googleapis.com/token");
    static final URI TOKEN_REVOKE_URI = URI.create("https://oauth2.googleapis.com/revoke");
    static final URI USER_AUTH_URI = URI.create("https://accounts.google.com/o/oauth2/auth");
    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final HttpTransportFactory HTTP_TRANSPORT_FACTORY = new DefaultHttpTransportFactory();
    static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    static final Charset UTF_8 = Charset.forName("UTF-8");
    private static String VALUE_NOT_FOUND_MESSAGE = "%sExpected value %s not found.";
    private static String VALUE_WRONG_TYPE_MESSAGE = "%sExpected %s value %s of wrong type.";

    private OAuth2Utils() {
    }

    static boolean headersContainValue(HttpHeaders httpHeaders, String str, String str2) {
        Object obj = httpHeaders.get(str);
        if (obj instanceof Collection) {
            return ((Collection) obj).contains(str2);
        }
        return false;
    }

    static GenericJson parseJson(String str) throws IOException {
        return (GenericJson) new JsonObjectParser(JSON_FACTORY).parseAndClose((InputStream) new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8, GenericJson.class);
    }

    static String validateString(Map<String, Object> map, String str, String str2) throws IOException {
        Object obj = map.get(str);
        if (obj == null) {
            throw new IOException(String.format(VALUE_NOT_FOUND_MESSAGE, str2, str));
        }
        if (!(obj instanceof String)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, str2, TypedValues.Custom.S_STRING, str));
        }
        return (String) obj;
    }

    static void writeInputStreamToFile(InputStream inputStream, String str) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
        try {
            ByteStreams.copy(inputStream, fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }

    static String validateOptionalString(Map<String, Object> map, String str, String str2) throws IOException {
        Object obj = map.get(str);
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof String)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, str2, TypedValues.Custom.S_STRING, str));
        }
        return (String) obj;
    }

    static int validateInt32(Map<String, Object> map, String str, String str2) throws IOException {
        Object obj = map.get(str);
        if (obj == null) {
            throw new IOException(String.format(VALUE_NOT_FOUND_MESSAGE, str2, str));
        }
        if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).intValueExact();
        }
        if (!(obj instanceof Integer)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, str2, TypedValues.Custom.S_INT, str));
        }
        return ((Integer) obj).intValue();
    }

    static long validateLong(Map<String, Object> map, String str, String str2) throws IOException {
        Object obj = map.get(str);
        if (obj == null) {
            throw new IOException(String.format(VALUE_NOT_FOUND_MESSAGE, str2, str));
        }
        if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).longValueExact();
        }
        if (!(obj instanceof Long)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, str2, "long", str));
        }
        return ((Long) obj).longValue();
    }

    static Map<String, Object> validateMap(Map<String, Object> map, String str, String str2) throws IOException {
        Object obj = map.get(str);
        if (obj == null) {
            throw new IOException(String.format(VALUE_NOT_FOUND_MESSAGE, str2, str));
        }
        if (!(obj instanceof Map)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, str2, "Map", str));
        }
        return (Map) obj;
    }

    static class DefaultHttpTransportFactory implements HttpTransportFactory {
        DefaultHttpTransportFactory() {
        }

        @Override // com.google.auth.http.HttpTransportFactory
        public HttpTransport create() {
            return OAuth2Utils.HTTP_TRANSPORT;
        }
    }
}
