package org.apache.http.entity;

import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicHeaderValueFormatter;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.ParserCursor;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.TextUtils;

/* loaded from: classes5.dex */
public final class ContentType implements Serializable {
    public static final ContentType APPLICATION_ATOM_XML;
    public static final ContentType APPLICATION_FORM_URLENCODED;
    public static final ContentType APPLICATION_JSON;
    public static final ContentType APPLICATION_OCTET_STREAM;
    public static final ContentType APPLICATION_SOAP_XML;
    public static final ContentType APPLICATION_SVG_XML;
    public static final ContentType APPLICATION_XHTML_XML;
    public static final ContentType APPLICATION_XML;
    public static final ContentType DEFAULT_BINARY;
    public static final ContentType DEFAULT_TEXT;
    public static final ContentType IMAGE_BMP;
    public static final ContentType IMAGE_GIF;
    public static final ContentType IMAGE_JPEG;
    public static final ContentType IMAGE_PNG;
    public static final ContentType IMAGE_SVG;
    public static final ContentType IMAGE_TIFF;
    public static final ContentType IMAGE_WEBP;
    public static final ContentType MULTIPART_FORM_DATA;
    public static final ContentType TEXT_HTML;
    public static final ContentType TEXT_PLAIN;
    public static final ContentType TEXT_XML;
    public static final ContentType WILDCARD;
    private static final Map<String, ContentType> CONTENT_TYPE_MAP;
    private static final long serialVersionUID = -7768694718232371896L;

    static {
        ContentType contentTypeCreate = create("application/atom+xml", Consts.ISO_8859_1);
        APPLICATION_ATOM_XML = contentTypeCreate;
        ContentType contentTypeCreate2 = create("application/x-www-form-urlencoded", Consts.ISO_8859_1);
        APPLICATION_FORM_URLENCODED = contentTypeCreate2;
        ContentType contentTypeCreate3 = create(HttpHeaders.Values.APPLICATION_JSON, Consts.UTF_8);
        APPLICATION_JSON = contentTypeCreate3;
        APPLICATION_OCTET_STREAM = create("application/octet-stream", (Charset) null);
        APPLICATION_SOAP_XML = create("application/soap+xml", Consts.UTF_8);
        ContentType contentTypeCreate4 = create("application/svg+xml", Consts.ISO_8859_1);
        APPLICATION_SVG_XML = contentTypeCreate4;
        ContentType contentTypeCreate5 = create("application/xhtml+xml", Consts.ISO_8859_1);
        APPLICATION_XHTML_XML = contentTypeCreate5;
        ContentType contentTypeCreate6 = create("application/xml", Consts.ISO_8859_1);
        APPLICATION_XML = contentTypeCreate6;
        ContentType contentTypeCreate7 = create("image/bmp");
        IMAGE_BMP = contentTypeCreate7;
        ContentType contentTypeCreate8 = create("image/gif");
        IMAGE_GIF = contentTypeCreate8;
        ContentType contentTypeCreate9 = create("image/jpeg");
        IMAGE_JPEG = contentTypeCreate9;
        ContentType contentTypeCreate10 = create("image/png");
        IMAGE_PNG = contentTypeCreate10;
        ContentType contentTypeCreate11 = create("image/svg+xml");
        IMAGE_SVG = contentTypeCreate11;
        ContentType contentTypeCreate12 = create("image/tiff");
        IMAGE_TIFF = contentTypeCreate12;
        ContentType contentTypeCreate13 = create("image/webp");
        IMAGE_WEBP = contentTypeCreate13;
        ContentType contentTypeCreate14 = create(HttpHeaders.Values.MULTIPART_FORM_DATA, Consts.ISO_8859_1);
        MULTIPART_FORM_DATA = contentTypeCreate14;
        ContentType contentTypeCreate15 = create("text/html", Consts.ISO_8859_1);
        TEXT_HTML = contentTypeCreate15;
        ContentType contentTypeCreate16 = create("text/plain", Consts.ISO_8859_1);
        TEXT_PLAIN = contentTypeCreate16;
        ContentType contentTypeCreate17 = create("text/xml", Consts.ISO_8859_1);
        TEXT_XML = contentTypeCreate17;
        WILDCARD = create("*/*", (Charset) null);
        ContentType[] contentTypeArr = {contentTypeCreate, contentTypeCreate2, contentTypeCreate3, contentTypeCreate4, contentTypeCreate5, contentTypeCreate6, contentTypeCreate7, contentTypeCreate8, contentTypeCreate9, contentTypeCreate10, contentTypeCreate11, contentTypeCreate12, contentTypeCreate13, contentTypeCreate14, contentTypeCreate15, contentTypeCreate16, contentTypeCreate17};
        HashMap map = new HashMap();
        for (int i = 0; i < 17; i++) {
            ContentType contentType = contentTypeArr[i];
            map.put(contentType.getMimeType(), contentType);
        }
        CONTENT_TYPE_MAP = Collections.unmodifiableMap(map);
        DEFAULT_TEXT = TEXT_PLAIN;
        DEFAULT_BINARY = APPLICATION_OCTET_STREAM;
    }

    private final Charset charset;
    private final String mimeType;
    private final NameValuePair[] params;

    ContentType(String str, Charset charset) {
        this.mimeType = str;
        this.charset = charset;
        this.params = null;
    }

    ContentType(String str, Charset charset, NameValuePair[] nameValuePairArr) {
        this.mimeType = str;
        this.charset = charset;
        this.params = nameValuePairArr;
    }

    private static boolean valid(String str) {
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"' || cCharAt == ',' || cCharAt == ';') {
                return false;
            }
        }
        return true;
    }

    public static ContentType create(String str, Charset charset) {
        String lowerCase = ((String) Args.notBlank(str, "MIME type")).toLowerCase(Locale.ROOT);
        Args.check(valid(lowerCase), "MIME type may not contain reserved characters");
        return new ContentType(lowerCase, charset);
    }

    public static ContentType create(String str) {
        return create(str, (Charset) null);
    }

    public static ContentType create(String str, String str2) throws UnsupportedCharsetException {
        return create(str, !TextUtils.isBlank(str2) ? Charset.forName(str2) : null);
    }

    private static ContentType create(HeaderElement headerElement, boolean z) {
        return create(headerElement.getName(), headerElement.getParameters(), z);
    }

    private static ContentType create(String str, NameValuePair[] nameValuePairArr, boolean z) {
        Charset charsetForName;
        int length = nameValuePairArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            NameValuePair nameValuePair = nameValuePairArr[i];
            if (nameValuePair.getName().equalsIgnoreCase("charset")) {
                String value = nameValuePair.getValue();
                if (!TextUtils.isBlank(value)) {
                    try {
                        charsetForName = Charset.forName(value);
                    } catch (UnsupportedCharsetException e) {
                        if (z) {
                            throw e;
                        }
                        charsetForName = null;
                        if (nameValuePairArr == null || nameValuePairArr.length <= 0) {
                            nameValuePairArr = null;
                        }
                        return new ContentType(str, charsetForName, nameValuePairArr);
                    }
                }
            } else {
                i++;
            }
        }
    }

    public static ContentType create(String str, NameValuePair... nameValuePairArr) throws UnsupportedCharsetException {
        Args.check(valid(((String) Args.notBlank(str, "MIME type")).toLowerCase(Locale.ROOT)), "MIME type may not contain reserved characters");
        return create(str, nameValuePairArr, true);
    }

    public static ContentType parse(String str) throws ParseException, UnsupportedCharsetException {
        Args.notNull(str, "Content type");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        HeaderElement[] elements = BasicHeaderValueParser.INSTANCE.parseElements(charArrayBuffer, new ParserCursor(0, str.length()));
        if (elements.length > 0) {
            return create(elements[0], true);
        }
        throw new ParseException("Invalid content type: " + str);
    }

    public static ContentType get(HttpEntity httpEntity) throws ParseException, UnsupportedCharsetException {
        Header contentType;
        if (httpEntity != null && (contentType = httpEntity.getContentType()) != null) {
            HeaderElement[] elements = contentType.getElements();
            if (elements.length > 0) {
                return create(elements[0], true);
            }
        }
        return null;
    }

    public static ContentType getLenient(HttpEntity httpEntity) {
        Header contentType;
        if (httpEntity != null && (contentType = httpEntity.getContentType()) != null) {
            try {
                HeaderElement[] elements = contentType.getElements();
                if (elements.length > 0) {
                    return create(elements[0], false);
                }
            } catch (ParseException unused) {
            }
        }
        return null;
    }

    public static ContentType getOrDefault(HttpEntity httpEntity) throws ParseException, UnsupportedCharsetException {
        ContentType contentType = get(httpEntity);
        return contentType != null ? contentType : DEFAULT_TEXT;
    }

    public static ContentType getLenientOrDefault(HttpEntity httpEntity) throws ParseException, UnsupportedCharsetException {
        ContentType contentType = get(httpEntity);
        return contentType != null ? contentType : DEFAULT_TEXT;
    }

    public static ContentType getByMimeType(String str) {
        if (str == null) {
            return null;
        }
        return CONTENT_TYPE_MAP.get(str);
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getParameter(String str) {
        Args.notEmpty(str, "Parameter name");
        NameValuePair[] nameValuePairArr = this.params;
        if (nameValuePairArr == null) {
            return null;
        }
        for (NameValuePair nameValuePair : nameValuePairArr) {
            if (nameValuePair.getName().equalsIgnoreCase(str)) {
                return nameValuePair.getValue();
            }
        }
        return null;
    }

    public String toString() {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(64);
        charArrayBuffer.append(this.mimeType);
        if (this.params != null) {
            charArrayBuffer.append(VectorFormat.DEFAULT_SEPARATOR);
            BasicHeaderValueFormatter.INSTANCE.formatParameters(charArrayBuffer, this.params, false);
        } else if (this.charset != null) {
            charArrayBuffer.append(HTTP.CHARSET_PARAM);
            charArrayBuffer.append(this.charset.name());
        }
        return charArrayBuffer.toString();
    }

    public ContentType withCharset(Charset charset) {
        return create(getMimeType(), charset);
    }

    public ContentType withCharset(String str) {
        return create(getMimeType(), str);
    }

    public ContentType withParameters(NameValuePair... nameValuePairArr) throws UnsupportedCharsetException {
        if (nameValuePairArr.length == 0) {
            return this;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        NameValuePair[] nameValuePairArr2 = this.params;
        if (nameValuePairArr2 != null) {
            for (NameValuePair nameValuePair : nameValuePairArr2) {
                linkedHashMap.put(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        for (NameValuePair nameValuePair2 : nameValuePairArr) {
            linkedHashMap.put(nameValuePair2.getName(), nameValuePair2.getValue());
        }
        ArrayList arrayList = new ArrayList(linkedHashMap.size() + 1);
        if (this.charset != null && !linkedHashMap.containsKey("charset")) {
            arrayList.add(new BasicNameValuePair("charset", this.charset.name()));
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            arrayList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        return create(getMimeType(), (NameValuePair[]) arrayList.toArray(new NameValuePair[arrayList.size()]), true);
    }
}
