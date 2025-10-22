package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class ViewModel {
    private final Map<String, Object> mBagOfTags;
    private final Set<Closeable> mCloseables;
    private volatile boolean mCleared;

    public ViewModel() {
        this.mBagOfTags = new HashMap();
        this.mCloseables = new LinkedHashSet();
        this.mCleared = false;
    }

    public ViewModel(Closeable... closeableArr) {
        this.mBagOfTags = new HashMap();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.mCloseables = linkedHashSet;
        this.mCleared = false;
        linkedHashSet.addAll(Arrays.asList(closeableArr));
    }

    private static void closeWithRuntimeException(Object obj) throws IOException {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void onCleared() {
    }

    public void addCloseable(Closeable closeable) {
        Set<Closeable> set = this.mCloseables;
        if (set != null) {
            synchronized (set) {
                this.mCloseables.add(closeable);
            }
        }
    }

    final void clear() {
        this.mCleared = true;
        Map<String, Object> map = this.mBagOfTags;
        if (map != null) {
            synchronized (map) {
                Iterator<Object> it2 = this.mBagOfTags.values().iterator();
                while (it2.hasNext()) {
                    closeWithRuntimeException(it2.next());
                }
            }
        }
        Set<Closeable> set = this.mCloseables;
        if (set != null) {
            synchronized (set) {
                Iterator<Closeable> it3 = this.mCloseables.iterator();
                while (it3.hasNext()) {
                    closeWithRuntimeException(it3.next());
                }
            }
        }
        onCleared();
    }

    /* JADX WARN: Multi-variable type inference failed */
    <T> T setTagIfAbsent(String str, T t) throws IOException {
        Object obj;
        synchronized (this.mBagOfTags) {
            obj = this.mBagOfTags.get(str);
            if (obj == 0) {
                this.mBagOfTags.put(str, t);
            }
        }
        if (obj != 0) {
            t = obj;
        }
        if (this.mCleared) {
            closeWithRuntimeException(t);
        }
        return t;
    }

    <T> T getTag(String str) {
        T t;
        Map<String, Object> map = this.mBagOfTags;
        if (map == null) {
            return null;
        }
        synchronized (map) {
            t = (T) this.mBagOfTags.get(str);
        }
        return t;
    }
}
