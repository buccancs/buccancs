package com.squareup.okhttp.internal;

import com.squareup.okhttp.internal.io.FileSystem;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.DiskFileUpload;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public final class DiskLruCache implements Closeable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final long ANY_SEQUENCE_NUMBER = -1;
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final String MAGIC = "libcore.io.DiskLruCache";
    static final String VERSION_1 = "1";
    static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    private static final Sink NULL_SINK = new Sink() { // from class: com.squareup.okhttp.internal.DiskLruCache.4
        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            buffer.skip(j);
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return Timeout.NONE;
        }
    };
    private final int appVersion;
    private final File directory;
    private final Executor executor;
    private final FileSystem fileSystem;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    private final int valueCount;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private boolean closed;
    private boolean hasJournalErrors;
    private boolean initialized;
    private BufferedSink journalWriter;
    private long maxSize;
    private int redundantOpCount;
    private long size = 0;
    private long nextSequenceNumber = 0;

    DiskLruCache(FileSystem fileSystem, File file, int i, int i2, long j, Executor executor) {
        this.fileSystem = fileSystem;
        this.directory = file;
        this.appVersion = i;
        this.journalFile = new File(file, JOURNAL_FILE);
        this.journalFileTmp = new File(file, JOURNAL_FILE_TEMP);
        this.journalFileBackup = new File(file, JOURNAL_FILE_BACKUP);
        this.valueCount = i2;
        this.maxSize = j;
        this.executor = executor;
    }

    public static DiskLruCache create(FileSystem fileSystem, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        return new DiskLruCache(fileSystem, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true)));
    }

    public File getDirectory() {
        return this.directory;
    }    private final Runnable cleanupRunnable = new Runnable() { // from class: com.squareup.okhttp.internal.DiskLruCache.1
        @Override // java.lang.Runnable
        public void run() {
            synchronized (DiskLruCache.this) {
                if ((!DiskLruCache.this.initialized) || DiskLruCache.this.closed) {
                    return;
                }
                try {
                    DiskLruCache.this.trimToSize();
                    if (DiskLruCache.this.journalRebuildRequired()) {
                        DiskLruCache.this.rebuildJournal();
                        DiskLruCache.this.redundantOpCount = 0;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };

    public synchronized void initialize() throws IOException {
        if (this.initialized) {
            return;
        }
        if (this.fileSystem.exists(this.journalFileBackup)) {
            if (this.fileSystem.exists(this.journalFile)) {
                this.fileSystem.delete(this.journalFileBackup);
            } else {
                this.fileSystem.rename(this.journalFileBackup, this.journalFile);
            }
        }
        if (this.fileSystem.exists(this.journalFile)) {
            try {
                readJournal();
                processJournal();
                this.initialized = true;
                return;
            } catch (IOException e) {
                Platform.get().logW("DiskLruCache " + this.directory + " is corrupt: " + e.getMessage() + ", removing");
                delete();
                this.closed = false;
            }
        } else {
            rebuildJournal();
            this.initialized = true;
            return;
        }
    }

    private void readJournal() throws IOException {
        BufferedSource bufferedSourceBuffer = Okio.buffer(this.fileSystem.source(this.journalFile));
        try {
            String utf8LineStrict = bufferedSourceBuffer.readUtf8LineStrict();
            String utf8LineStrict2 = bufferedSourceBuffer.readUtf8LineStrict();
            String utf8LineStrict3 = bufferedSourceBuffer.readUtf8LineStrict();
            String utf8LineStrict4 = bufferedSourceBuffer.readUtf8LineStrict();
            String utf8LineStrict5 = bufferedSourceBuffer.readUtf8LineStrict();
            if (!MAGIC.equals(utf8LineStrict) || !VERSION_1.equals(utf8LineStrict2) || !Integer.toString(this.appVersion).equals(utf8LineStrict3) || !Integer.toString(this.valueCount).equals(utf8LineStrict4) || !"".equals(utf8LineStrict5)) {
                throw new IOException("unexpected journal header: [" + utf8LineStrict + ", " + utf8LineStrict2 + ", " + utf8LineStrict4 + ", " + utf8LineStrict5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    readJournalLine(bufferedSourceBuffer.readUtf8LineStrict());
                    i++;
                } catch (EOFException unused) {
                    this.redundantOpCount = i - this.lruEntries.size();
                    if (!bufferedSourceBuffer.exhausted()) {
                        rebuildJournal();
                    } else {
                        this.journalWriter = newJournalWriter();
                    }
                    Util.closeQuietly(bufferedSourceBuffer);
                    return;
                }
            }
        } catch (Throwable th) {
            Util.closeQuietly(bufferedSourceBuffer);
            throw th;
        }
    }

    private BufferedSink newJournalWriter() throws FileNotFoundException {
        return Okio.buffer(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile)) { // from class: com.squareup.okhttp.internal.DiskLruCache.2
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // com.squareup.okhttp.internal.FaultHidingSink
            protected void onException(IOException iOException) {
                DiskLruCache.this.hasJournalErrors = true;
            }
        });
    }

    private void readJournalLine(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i);
        if (iIndexOf2 == -1) {
            strSubstring = str.substring(i);
            if (iIndexOf == 6 && str.startsWith(REMOVE)) {
                this.lruEntries.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i, iIndexOf2);
        }
        Entry entry = this.lruEntries.get(strSubstring);
        if (entry == null) {
            entry = new Entry(strSubstring);
            this.lruEntries.put(strSubstring, entry);
        }
        if (iIndexOf2 != -1 && iIndexOf == 5 && str.startsWith(CLEAN)) {
            String[] strArrSplit = str.substring(iIndexOf2 + 1).split(StringUtils.SPACE);
            entry.readable = true;
            entry.currentEditor = null;
            entry.setLengths(strArrSplit);
            return;
        }
        if (iIndexOf2 != -1 || iIndexOf != 5 || !str.startsWith(DIRTY)) {
            if (iIndexOf2 == -1 && iIndexOf == 4 && str.startsWith(READ)) {
                return;
            }
            throw new IOException("unexpected journal line: " + str);
        }
        entry.currentEditor = new Editor(entry);
    }

    private void processJournal() throws IOException {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator<Entry> it2 = this.lruEntries.values().iterator();
        while (it2.hasNext()) {
            Entry next = it2.next();
            int i = 0;
            if (next.currentEditor == null) {
                while (i < this.valueCount) {
                    this.size += next.lengths[i];
                    i++;
                }
            } else {
                next.currentEditor = null;
                while (i < this.valueCount) {
                    this.fileSystem.delete(next.cleanFiles[i]);
                    this.fileSystem.delete(next.dirtyFiles[i]);
                    i++;
                }
                it2.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void rebuildJournal() throws IOException {
        BufferedSink bufferedSink = this.journalWriter;
        if (bufferedSink != null) {
            bufferedSink.close();
        }
        BufferedSink bufferedSinkBuffer = Okio.buffer(this.fileSystem.sink(this.journalFileTmp));
        try {
            bufferedSinkBuffer.writeUtf8(MAGIC).writeByte(10);
            bufferedSinkBuffer.writeUtf8(VERSION_1).writeByte(10);
            bufferedSinkBuffer.writeDecimalLong(this.appVersion).writeByte(10);
            bufferedSinkBuffer.writeDecimalLong(this.valueCount).writeByte(10);
            bufferedSinkBuffer.writeByte(10);
            for (Entry entry : this.lruEntries.values()) {
                if (entry.currentEditor != null) {
                    bufferedSinkBuffer.writeUtf8(DIRTY).writeByte(32);
                    bufferedSinkBuffer.writeUtf8(entry.key);
                    bufferedSinkBuffer.writeByte(10);
                } else {
                    bufferedSinkBuffer.writeUtf8(CLEAN).writeByte(32);
                    bufferedSinkBuffer.writeUtf8(entry.key);
                    entry.writeLengths(bufferedSinkBuffer);
                    bufferedSinkBuffer.writeByte(10);
                }
            }
            bufferedSinkBuffer.close();
            if (this.fileSystem.exists(this.journalFile)) {
                this.fileSystem.rename(this.journalFile, this.journalFileBackup);
            }
            this.fileSystem.rename(this.journalFileTmp, this.journalFile);
            this.fileSystem.delete(this.journalFileBackup);
            this.journalWriter = newJournalWriter();
            this.hasJournalErrors = false;
        } catch (Throwable th) {
            bufferedSinkBuffer.close();
            throw th;
        }
    }

    public synchronized Snapshot get(String str) throws IOException {
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (entry != null && entry.readable) {
            Snapshot snapshot = entry.snapshot();
            if (snapshot == null) {
                return null;
            }
            this.redundantOpCount++;
            this.journalWriter.writeUtf8(READ).writeByte(32).writeUtf8(str).writeByte(10);
            if (journalRebuildRequired()) {
                this.executor.execute(this.cleanupRunnable);
            }
            return snapshot;
        }
        return null;
    }

    public Editor edit(String str) throws IOException {
        return edit(str, -1L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized Editor edit(String str, long j) throws IOException {
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (j != -1 && (entry == null || entry.sequenceNumber != j)) {
            return null;
        }
        if (entry != null && entry.currentEditor != null) {
            return null;
        }
        this.journalWriter.writeUtf8(DIRTY).writeByte(32).writeUtf8(str).writeByte(10);
        this.journalWriter.flush();
        if (this.hasJournalErrors) {
            return null;
        }
        if (entry == null) {
            entry = new Entry(str);
            this.lruEntries.put(str, entry);
        }
        Editor editor = new Editor(entry);
        entry.currentEditor = editor;
        return editor;
    }

    public synchronized long getMaxSize() {
        return this.maxSize;
    }

    public synchronized void setMaxSize(long j) {
        this.maxSize = j;
        if (this.initialized) {
            this.executor.execute(this.cleanupRunnable);
        }
    }

    public synchronized long size() throws IOException {
        initialize();
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void completeEdit(Editor editor, boolean z) throws IOException {
        Entry entry = editor.entry;
        if (entry.currentEditor != editor) {
            throw new IllegalStateException();
        }
        if (z && !entry.readable) {
            for (int i = 0; i < this.valueCount; i++) {
                if (!editor.written[i]) {
                    editor.abort();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                }
                if (!this.fileSystem.exists(entry.dirtyFiles[i])) {
                    editor.abort();
                    return;
                }
            }
        }
        for (int i2 = 0; i2 < this.valueCount; i2++) {
            File file = entry.dirtyFiles[i2];
            if (z) {
                if (this.fileSystem.exists(file)) {
                    File file2 = entry.cleanFiles[i2];
                    this.fileSystem.rename(file, file2);
                    long j = entry.lengths[i2];
                    long size = this.fileSystem.size(file2);
                    entry.lengths[i2] = size;
                    this.size = (this.size - j) + size;
                }
            } else {
                this.fileSystem.delete(file);
            }
        }
        this.redundantOpCount++;
        entry.currentEditor = null;
        if (entry.readable | z) {
            entry.readable = true;
            this.journalWriter.writeUtf8(CLEAN).writeByte(32);
            this.journalWriter.writeUtf8(entry.key);
            entry.writeLengths(this.journalWriter);
            this.journalWriter.writeByte(10);
            if (z) {
                long j2 = this.nextSequenceNumber;
                this.nextSequenceNumber = 1 + j2;
                entry.sequenceNumber = j2;
            }
        } else {
            this.lruEntries.remove(entry.key);
            this.journalWriter.writeUtf8(REMOVE).writeByte(32);
            this.journalWriter.writeUtf8(entry.key);
            this.journalWriter.writeByte(10);
        }
        this.journalWriter.flush();
        if (this.size > this.maxSize || journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean journalRebuildRequired() {
        int i = this.redundantOpCount;
        return i >= 2000 && i >= this.lruEntries.size();
    }

    public synchronized boolean remove(String str) throws IOException {
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (entry == null) {
            return false;
        }
        return removeEntry(entry);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean removeEntry(Entry entry) throws IOException {
        if (entry.currentEditor != null) {
            entry.currentEditor.hasErrors = true;
        }
        for (int i = 0; i < this.valueCount; i++) {
            this.fileSystem.delete(entry.cleanFiles[i]);
            this.size -= entry.lengths[i];
            entry.lengths[i] = 0;
        }
        this.redundantOpCount++;
        this.journalWriter.writeUtf8(REMOVE).writeByte(32).writeUtf8(entry.key).writeByte(10);
        this.lruEntries.remove(entry.key);
        if (journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
        return true;
    }

    public synchronized boolean isClosed() {
        return this.closed;
    }

    private synchronized void checkNotClosed() {
        if (isClosed()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        if (this.initialized) {
            checkNotClosed();
            trimToSize();
            this.journalWriter.flush();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.initialized && !this.closed) {
            for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
                if (entry.currentEditor != null) {
                    entry.currentEditor.abort();
                }
            }
            trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
            this.closed = true;
            return;
        }
        this.closed = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            removeEntry(this.lruEntries.values().iterator().next());
        }
    }

    public void delete() throws IOException {
        close();
        this.fileSystem.deleteContents(this.directory);
    }

    public synchronized void evictAll() throws IOException {
        initialize();
        for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
            removeEntry(entry);
        }
    }

    private void validateKey(String str) {
        if (LEGAL_KEY_PATTERN.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
    }

    public synchronized Iterator<Snapshot> snapshots() throws IOException {
        initialize();
        return new Iterator<Snapshot>() { // from class: com.squareup.okhttp.internal.DiskLruCache.3
            final Iterator<Entry> delegate;
            Snapshot nextSnapshot;
            Snapshot removeSnapshot;

            {
                this.delegate = new ArrayList(DiskLruCache.this.lruEntries.values()).iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.nextSnapshot != null) {
                    return true;
                }
                synchronized (DiskLruCache.this) {
                    if (DiskLruCache.this.closed) {
                        return false;
                    }
                    while (this.delegate.hasNext()) {
                        Snapshot snapshot = this.delegate.next().snapshot();
                        if (snapshot != null) {
                            this.nextSnapshot = snapshot;
                            return true;
                        }
                    }
                    return false;
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Snapshot next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Snapshot snapshot = this.nextSnapshot;
                this.removeSnapshot = snapshot;
                this.nextSnapshot = null;
                return snapshot;
            }

            @Override // java.util.Iterator
            public void remove() {
                Snapshot snapshot = this.removeSnapshot;
                if (snapshot == null) {
                    throw new IllegalStateException("remove() before next()");
                }
                try {
                    DiskLruCache.this.remove(snapshot.key);
                } catch (IOException unused) {
                } catch (Throwable th) {
                    this.removeSnapshot = null;
                    throw th;
                }
                this.removeSnapshot = null;
            }
        };
    }

    public final class Snapshot implements Closeable {
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final Source[] sources;

        private Snapshot(String str, long j, Source[] sourceArr, long[] jArr) {
            this.key = str;
            this.sequenceNumber = j;
            this.sources = sourceArr;
            this.lengths = jArr;
        }

        public String key() {
            return this.key;
        }

        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public Source getSource(int i) {
            return this.sources[i];
        }

        public long getLength(int i) {
            return this.lengths[i];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            for (Source source : this.sources) {
                Util.closeQuietly(source);
            }
        }
    }

    public final class Editor {
        private final Entry entry;
        private final boolean[] written;
        private boolean committed;
        private boolean hasErrors;

        private Editor(Entry entry) {
            this.entry = entry;
            this.written = entry.readable ? null : new boolean[DiskLruCache.this.valueCount];
        }

        public Source newSource(int i) throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                }
                if (!this.entry.readable) {
                    return null;
                }
                try {
                    return DiskLruCache.this.fileSystem.source(this.entry.cleanFiles[i]);
                } catch (FileNotFoundException unused) {
                    return null;
                }
            }
        }

        public Sink newSink(int i) throws IOException {
            FaultHidingSink faultHidingSink;
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                }
                if (!this.entry.readable) {
                    this.written[i] = true;
                }
                try {
                    faultHidingSink = new FaultHidingSink(DiskLruCache.this.fileSystem.sink(this.entry.dirtyFiles[i])) { // from class: com.squareup.okhttp.internal.DiskLruCache.Editor.1
                        @Override // com.squareup.okhttp.internal.FaultHidingSink
                        protected void onException(IOException iOException) {
                            synchronized (DiskLruCache.this) {
                                Editor.this.hasErrors = true;
                            }
                        }
                    };
                } catch (FileNotFoundException unused) {
                    return DiskLruCache.NULL_SINK;
                }
            }
            return faultHidingSink;
        }

        public void commit() throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.hasErrors) {
                    DiskLruCache.this.completeEdit(this, false);
                    DiskLruCache.this.removeEntry(this.entry);
                } else {
                    DiskLruCache.this.completeEdit(this, true);
                }
                this.committed = true;
            }
        }

        public void abort() throws IOException {
            synchronized (DiskLruCache.this) {
                DiskLruCache.this.completeEdit(this, false);
            }
        }

        public void abortUnlessCommitted() {
            synchronized (DiskLruCache.this) {
                if (!this.committed) {
                    try {
                        DiskLruCache.this.completeEdit(this, false);
                    } catch (IOException unused) {
                    }
                }
            }
        }
    }

    private final class Entry {
        private final File[] cleanFiles;
        private final File[] dirtyFiles;
        private final String key;
        private final long[] lengths;
        private Editor currentEditor;
        private boolean readable;
        private long sequenceNumber;

        private Entry(String str) {
            this.key = str;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new File[DiskLruCache.this.valueCount];
            this.dirtyFiles = new File[DiskLruCache.this.valueCount];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i = 0; i < DiskLruCache.this.valueCount; i++) {
                sb.append(i);
                this.cleanFiles[i] = new File(DiskLruCache.this.directory, sb.toString());
                sb.append(DiskFileUpload.postfix);
                this.dirtyFiles[i] = new File(DiskLruCache.this.directory, sb.toString());
                sb.setLength(length);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLengths(String[] strArr) throws IOException {
            if (strArr.length != DiskLruCache.this.valueCount) {
                throw invalidLengths(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.lengths[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    throw invalidLengths(strArr);
                }
            }
        }

        void writeLengths(BufferedSink bufferedSink) throws IOException {
            for (long j : this.lengths) {
                bufferedSink.writeByte(32).writeDecimalLong(j);
            }
        }

        private IOException invalidLengths(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        Snapshot snapshot() throws IOException {
            Source source;
            if (Thread.holdsLock(DiskLruCache.this)) {
                Source[] sourceArr = new Source[DiskLruCache.this.valueCount];
                long[] jArr = (long[]) this.lengths.clone();
                for (int i = 0; i < DiskLruCache.this.valueCount; i++) {
                    try {
                        sourceArr[i] = DiskLruCache.this.fileSystem.source(this.cleanFiles[i]);
                    } catch (FileNotFoundException unused) {
                        for (int i2 = 0; i2 < DiskLruCache.this.valueCount && (source = sourceArr[i2]) != null; i2++) {
                            Util.closeQuietly(source);
                        }
                        return null;
                    }
                }
                return new Snapshot(this.key, this.sequenceNumber, sourceArr, jArr);
            }
            throw new AssertionError();
        }
    }




}
