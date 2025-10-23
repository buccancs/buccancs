package com.squareup.okhttp;

import com.google.api.client.http.HttpMethods;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.DiskLruCache;
import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.CacheRequest;
import com.squareup.okhttp.internal.http.CacheStrategy;
import com.squareup.okhttp.internal.http.HttpMethod;
import com.squareup.okhttp.internal.http.OkHeaders;
import com.squareup.okhttp.internal.http.StatusLine;
import com.squareup.okhttp.internal.io.FileSystem;

import java.io.File;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

/* loaded from: classes2.dex */
public final class Cache {
    private static final int ENTRY_BODY = 1;
    private static final int ENTRY_COUNT = 2;
    private static final int ENTRY_METADATA = 0;
    private static final int VERSION = 201105;
    final InternalCache internalCache;
    private final DiskLruCache cache;
    private int hitCount;
    private int networkCount;
    private int requestCount;
    private int writeAbortCount;
    private int writeSuccessCount;

    public Cache(File file, long j) {
        this(file, j, FileSystem.SYSTEM);
    }

    Cache(File file, long j, FileSystem fileSystem) {
        this.internalCache = new InternalCache() { // from class: com.squareup.okhttp.Cache.1
            @Override // com.squareup.okhttp.internal.InternalCache
            public Response get(Request request) throws IOException {
                return Cache.this.get(request);
            }

            @Override // com.squareup.okhttp.internal.InternalCache
            public CacheRequest put(Response response) throws IOException {
                return Cache.this.put(response);
            }

            @Override // com.squareup.okhttp.internal.InternalCache
            public void remove(Request request) throws IOException {
                Cache.this.remove(request);
            }

            @Override // com.squareup.okhttp.internal.InternalCache
            public void update(Response response, Response response2) throws IOException {
                Cache.this.update(response, response2);
            }

            @Override // com.squareup.okhttp.internal.InternalCache
            public void trackConditionalCacheHit() {
                Cache.this.trackConditionalCacheHit();
            }

            @Override // com.squareup.okhttp.internal.InternalCache
            public void trackResponse(CacheStrategy cacheStrategy) {
                Cache.this.trackResponse(cacheStrategy);
            }
        };
        this.cache = DiskLruCache.create(fileSystem, file, VERSION, 2, j);
    }

    static /* synthetic */ int access$808(Cache cache) {
        int i = cache.writeSuccessCount;
        cache.writeSuccessCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$908(Cache cache) {
        int i = cache.writeAbortCount;
        cache.writeAbortCount = i + 1;
        return i;
    }

    private static String urlToKey(Request request) {
        return Util.md5Hex(request.urlString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int readInt(BufferedSource bufferedSource) throws IOException {
        try {
            long decimalLong = bufferedSource.readDecimalLong();
            String utf8LineStrict = bufferedSource.readUtf8LineStrict();
            if (decimalLong >= 0 && decimalLong <= 2147483647L && utf8LineStrict.isEmpty()) {
                return (int) decimalLong;
            }
            throw new IOException("expected an int but was \"" + decimalLong + utf8LineStrict + "\"");
        } catch (NumberFormatException e) {
            throw new IOException(e.getMessage());
        }
    }

    Response get(Request request) throws IOException {
        try {
            DiskLruCache.Snapshot snapshot = this.cache.get(urlToKey(request));
            if (snapshot == null) {
                return null;
            }
            try {
                Entry entry = new Entry(snapshot.getSource(0));
                Response response = entry.response(request, snapshot);
                if (entry.matches(request, response)) {
                    return response;
                }
                Util.closeQuietly(response.body());
                return null;
            } catch (IOException unused) {
                Util.closeQuietly(snapshot);
                return null;
            }
        } catch (IOException unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CacheRequest put(Response response) throws IOException {
        DiskLruCache.Editor editorEdit;
        String strMethod = response.request().method();
        if (HttpMethod.invalidatesCache(response.request().method())) {
            try {
                remove(response.request());
            } catch (IOException unused) {
            }
            return null;
        }
        if (!strMethod.equals(HttpMethods.GET) || OkHeaders.hasVaryAll(response)) {
            return null;
        }
        Entry entry = new Entry(response);
        try {
            editorEdit = this.cache.edit(urlToKey(response.request()));
            if (editorEdit == null) {
                return null;
            }
            try {
                entry.writeTo(editorEdit);
                return new CacheRequestImpl(editorEdit);
            } catch (IOException unused2) {
                abortQuietly(editorEdit);
                return null;
            }
        } catch (IOException unused3) {
            editorEdit = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void remove(Request request) throws IOException {
        this.cache.remove(urlToKey(request));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update(Response response, Response response2) {
        DiskLruCache.Editor editorEdit;
        Entry entry = new Entry(response2);
        try {
            editorEdit = ((CacheResponseBody) response.body()).snapshot.edit();
            if (editorEdit != null) {
                try {
                    entry.writeTo(editorEdit);
                    editorEdit.commit();
                } catch (IOException unused) {
                    abortQuietly(editorEdit);
                }
            }
        } catch (IOException unused2) {
            editorEdit = null;
        }
    }

    private void abortQuietly(DiskLruCache.Editor editor) {
        if (editor != null) {
            try {
                editor.abort();
            } catch (IOException unused) {
            }
        }
    }

    public void initialize() throws IOException {
        this.cache.initialize();
    }

    public void delete() throws IOException {
        this.cache.delete();
    }

    public void evictAll() throws IOException {
        this.cache.evictAll();
    }

    public Iterator<String> urls() throws IOException {
        return new Iterator<String>() { // from class: com.squareup.okhttp.Cache.2
            final Iterator<DiskLruCache.Snapshot> delegate;
            boolean canRemove;
            String nextUrl;

            {
                this.delegate = Cache.this.cache.snapshots();
            }

            @Override // java.util.Iterator
            public boolean hasNext() throws IOException {
                if (this.nextUrl != null) {
                    return true;
                }
                this.canRemove = false;
                while (this.delegate.hasNext()) {
                    DiskLruCache.Snapshot next = this.delegate.next();
                    try {
                        this.nextUrl = Okio.buffer(next.getSource(0)).readUtf8LineStrict();
                        return true;
                    } catch (IOException unused) {
                    } finally {
                        next.close();
                    }
                }
                return false;
            }

            @Override // java.util.Iterator
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                String str = this.nextUrl;
                this.nextUrl = null;
                this.canRemove = true;
                return str;
            }

            @Override // java.util.Iterator
            public void remove() {
                if (!this.canRemove) {
                    throw new IllegalStateException("remove() before next()");
                }
                this.delegate.remove();
            }
        };
    }

    public synchronized int getWriteAbortCount() {
        return this.writeAbortCount;
    }

    public synchronized int getWriteSuccessCount() {
        return this.writeSuccessCount;
    }

    public long getSize() throws IOException {
        return this.cache.size();
    }

    public long getMaxSize() {
        return this.cache.getMaxSize();
    }

    public void flush() throws IOException {
        this.cache.flush();
    }

    public void close() throws IOException {
        this.cache.close();
    }

    public File getDirectory() {
        return this.cache.getDirectory();
    }

    public boolean isClosed() {
        return this.cache.isClosed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void trackResponse(CacheStrategy cacheStrategy) {
        this.requestCount++;
        if (cacheStrategy.networkRequest != null) {
            this.networkCount++;
        } else if (cacheStrategy.cacheResponse != null) {
            this.hitCount++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void trackConditionalCacheHit() {
        this.hitCount++;
    }

    public synchronized int getNetworkCount() {
        return this.networkCount;
    }

    public synchronized int getHitCount() {
        return this.hitCount;
    }

    public synchronized int getRequestCount() {
        return this.requestCount;
    }

    private static final class Entry {
        private final int code;
        private final Handshake handshake;
        private final String message;
        private final Protocol protocol;
        private final String requestMethod;
        private final Headers responseHeaders;
        private final String url;
        private final Headers varyHeaders;

        public Entry(Source source) throws IOException {
            try {
                BufferedSource bufferedSourceBuffer = Okio.buffer(source);
                this.url = bufferedSourceBuffer.readUtf8LineStrict();
                this.requestMethod = bufferedSourceBuffer.readUtf8LineStrict();
                Headers.Builder builder = new Headers.Builder();
                int i = Cache.readInt(bufferedSourceBuffer);
                for (int i2 = 0; i2 < i; i2++) {
                    builder.addLenient(bufferedSourceBuffer.readUtf8LineStrict());
                }
                this.varyHeaders = builder.build();
                StatusLine statusLine = StatusLine.parse(bufferedSourceBuffer.readUtf8LineStrict());
                this.protocol = statusLine.protocol;
                this.code = statusLine.code;
                this.message = statusLine.message;
                Headers.Builder builder2 = new Headers.Builder();
                int i3 = Cache.readInt(bufferedSourceBuffer);
                for (int i4 = 0; i4 < i3; i4++) {
                    builder2.addLenient(bufferedSourceBuffer.readUtf8LineStrict());
                }
                this.responseHeaders = builder2.build();
                if (isHttps()) {
                    String utf8LineStrict = bufferedSourceBuffer.readUtf8LineStrict();
                    if (utf8LineStrict.length() > 0) {
                        throw new IOException("expected \"\" but was \"" + utf8LineStrict + "\"");
                    }
                    this.handshake = Handshake.get(bufferedSourceBuffer.readUtf8LineStrict(), readCertificateList(bufferedSourceBuffer), readCertificateList(bufferedSourceBuffer));
                } else {
                    this.handshake = null;
                }
            } finally {
                source.close();
            }
        }

        public Entry(Response response) {
            this.url = response.request().urlString();
            this.varyHeaders = OkHeaders.varyHeaders(response);
            this.requestMethod = response.request().method();
            this.protocol = response.protocol();
            this.code = response.code();
            this.message = response.message();
            this.responseHeaders = response.headers();
            this.handshake = response.handshake();
        }

        public void writeTo(DiskLruCache.Editor editor) throws IOException {
            BufferedSink bufferedSinkBuffer = Okio.buffer(editor.newSink(0));
            bufferedSinkBuffer.writeUtf8(this.url);
            bufferedSinkBuffer.writeByte(10);
            bufferedSinkBuffer.writeUtf8(this.requestMethod);
            bufferedSinkBuffer.writeByte(10);
            bufferedSinkBuffer.writeDecimalLong(this.varyHeaders.size());
            bufferedSinkBuffer.writeByte(10);
            int size = this.varyHeaders.size();
            for (int i = 0; i < size; i++) {
                bufferedSinkBuffer.writeUtf8(this.varyHeaders.name(i));
                bufferedSinkBuffer.writeUtf8(": ");
                bufferedSinkBuffer.writeUtf8(this.varyHeaders.value(i));
                bufferedSinkBuffer.writeByte(10);
            }
            bufferedSinkBuffer.writeUtf8(new StatusLine(this.protocol, this.code, this.message).toString());
            bufferedSinkBuffer.writeByte(10);
            bufferedSinkBuffer.writeDecimalLong(this.responseHeaders.size());
            bufferedSinkBuffer.writeByte(10);
            int size2 = this.responseHeaders.size();
            for (int i2 = 0; i2 < size2; i2++) {
                bufferedSinkBuffer.writeUtf8(this.responseHeaders.name(i2));
                bufferedSinkBuffer.writeUtf8(": ");
                bufferedSinkBuffer.writeUtf8(this.responseHeaders.value(i2));
                bufferedSinkBuffer.writeByte(10);
            }
            if (isHttps()) {
                bufferedSinkBuffer.writeByte(10);
                bufferedSinkBuffer.writeUtf8(this.handshake.cipherSuite());
                bufferedSinkBuffer.writeByte(10);
                writeCertList(bufferedSinkBuffer, this.handshake.peerCertificates());
                writeCertList(bufferedSinkBuffer, this.handshake.localCertificates());
            }
            bufferedSinkBuffer.close();
        }

        private boolean isHttps() {
            return this.url.startsWith("https://");
        }

        private List<Certificate> readCertificateList(BufferedSource bufferedSource) throws IOException, CertificateException {
            int i = Cache.readInt(bufferedSource);
            if (i == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList(i);
                for (int i2 = 0; i2 < i; i2++) {
                    String utf8LineStrict = bufferedSource.readUtf8LineStrict();
                    Buffer buffer = new Buffer();
                    buffer.write(ByteString.decodeBase64(utf8LineStrict));
                    arrayList.add(certificateFactory.generateCertificate(buffer.inputStream()));
                }
                return arrayList;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        private void writeCertList(BufferedSink bufferedSink, List<Certificate> list) throws IOException {
            try {
                bufferedSink.writeDecimalLong(list.size());
                bufferedSink.writeByte(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    bufferedSink.writeUtf8(ByteString.of(list.get(i).getEncoded()).base64());
                    bufferedSink.writeByte(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        public boolean matches(Request request, Response response) {
            return this.url.equals(request.urlString()) && this.requestMethod.equals(request.method()) && OkHeaders.varyMatches(response, this.varyHeaders, request);
        }

        public Response response(Request request, DiskLruCache.Snapshot snapshot) {
            String str = this.responseHeaders.get("Content-Type");
            String str2 = this.responseHeaders.get("Content-Length");
            return new Response.Builder().request(new Request.Builder().url(this.url).method(this.requestMethod, null).headers(this.varyHeaders).build()).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new CacheResponseBody(snapshot, str, str2)).handshake(this.handshake).build();
        }
    }

    private static class CacheResponseBody extends ResponseBody {
        private final BufferedSource bodySource;
        private final String contentLength;
        private final String contentType;
        private final DiskLruCache.Snapshot snapshot;

        public CacheResponseBody(final DiskLruCache.Snapshot snapshot, String str, String str2) {
            this.snapshot = snapshot;
            this.contentType = str;
            this.contentLength = str2;
            this.bodySource = Okio.buffer(new ForwardingSource(snapshot.getSource(1)) { // from class: com.squareup.okhttp.Cache.CacheResponseBody.1
                @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    snapshot.close();
                    super.close();
                }
            });
        }

        @Override // com.squareup.okhttp.ResponseBody
        public BufferedSource source() {
            return this.bodySource;
        }

        @Override // com.squareup.okhttp.ResponseBody
        public MediaType contentType() {
            String str = this.contentType;
            if (str != null) {
                return MediaType.parse(str);
            }
            return null;
        }

        @Override // com.squareup.okhttp.ResponseBody
        public long contentLength() {
            try {
                String str = this.contentLength;
                if (str != null) {
                    return Long.parseLong(str);
                }
                return -1L;
            } catch (NumberFormatException unused) {
                return -1L;
            }
        }
    }

    private final class CacheRequestImpl implements CacheRequest {
        private final DiskLruCache.Editor editor;
        private Sink body;
        private Sink cacheOut;
        private boolean done;

        public CacheRequestImpl(final DiskLruCache.Editor editor) throws IOException {
            this.editor = editor;
            this.cacheOut = editor.newSink(1);
            this.body = new ForwardingSink(this.cacheOut) { // from class: com.squareup.okhttp.Cache.CacheRequestImpl.1
                @Override // okio.ForwardingSink, okio.Sink, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    synchronized (Cache.this) {
                        if (CacheRequestImpl.this.done) {
                            return;
                        }
                        CacheRequestImpl.this.done = true;
                        Cache.access$808(Cache.this);
                        super.close();
                        editor.commit();
                    }
                }
            };
        }

        @Override // com.squareup.okhttp.internal.http.CacheRequest
        public Sink body() {
            return this.body;
        }

        @Override // com.squareup.okhttp.internal.http.CacheRequest
        public void abort() throws IOException {
            synchronized (Cache.this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                Cache.access$908(Cache.this);
                Util.closeQuietly(this.cacheOut);
                try {
                    this.editor.abort();
                } catch (IOException unused) {
                }
            }
        }
    }
}
