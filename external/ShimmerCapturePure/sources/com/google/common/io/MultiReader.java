package com.google.common.io;

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes.dex */
class MultiReader extends Reader {

    /* renamed from: it, reason: collision with root package name */
    private final Iterator<? extends CharSource> f5it;
    @NullableDecl
    private Reader current;

    MultiReader(Iterator<? extends CharSource> it2) throws IOException {
        this.f5it = it2;
        advance();
    }

    private void advance() throws IOException {
        close();
        if (this.f5it.hasNext()) {
            this.current = this.f5it.next().openStream();
        }
    }

    @Override // java.io.Reader
    public int read(@NullableDecl char[] cArr, int i, int i2) throws IOException {
        Reader reader = this.current;
        if (reader == null) {
            return -1;
        }
        int i3 = reader.read(cArr, i, i2);
        if (i3 != -1) {
            return i3;
        }
        advance();
        return read(cArr, i, i2);
    }

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        Preconditions.checkArgument(j >= 0, "n is negative");
        if (j > 0) {
            while (true) {
                Reader reader = this.current;
                if (reader == null) {
                    break;
                }
                long jSkip = reader.skip(j);
                if (jSkip > 0) {
                    return jSkip;
                }
                advance();
            }
        }
        return 0L;
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        Reader reader = this.current;
        return reader != null && reader.ready();
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Reader reader = this.current;
        if (reader != null) {
            try {
                reader.close();
            } finally {
                this.current = null;
            }
        }
    }
}
