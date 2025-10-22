package io.grpc.netty.shaded.io.netty.util.concurrent;

import androidx.core.os.EnvironmentCompat;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public class DefaultThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolId = new AtomicInteger();
    protected final ThreadGroup threadGroup;
    private final boolean daemon;
    private final AtomicInteger nextId;
    private final String prefix;
    private final int priority;

    public DefaultThreadFactory(Class<?> cls) {
        this(cls, false, 5);
    }

    public DefaultThreadFactory(String str) {
        this(str, false, 5);
    }

    public DefaultThreadFactory(Class<?> cls, boolean z) {
        this(cls, z, 5);
    }

    public DefaultThreadFactory(String str, boolean z) {
        this(str, z, 5);
    }

    public DefaultThreadFactory(Class<?> cls, int i) {
        this(cls, false, i);
    }

    public DefaultThreadFactory(String str, int i) {
        this(str, false, i);
    }

    public DefaultThreadFactory(Class<?> cls, boolean z, int i) {
        this(toPoolName(cls), z, i);
    }

    public DefaultThreadFactory(String str, boolean z, int i, ThreadGroup threadGroup) {
        this.nextId = new AtomicInteger();
        ObjectUtil.checkNotNull(str, "poolName");
        if (i < 1 || i > 10) {
            throw new IllegalArgumentException("priority: " + i + " (expected: Thread.MIN_PRIORITY <= priority <= Thread.MAX_PRIORITY)");
        }
        this.prefix = str + '-' + poolId.incrementAndGet() + '-';
        this.daemon = z;
        this.priority = i;
        this.threadGroup = threadGroup;
    }

    public DefaultThreadFactory(String str, boolean z, int i) {
        this(str, z, i, System.getSecurityManager() == null ? Thread.currentThread().getThreadGroup() : System.getSecurityManager().getThreadGroup());
    }

    public static String toPoolName(Class<?> cls) {
        ObjectUtil.checkNotNull(cls, "poolType");
        String strSimpleClassName = StringUtil.simpleClassName(cls);
        int length = strSimpleClassName.length();
        if (length == 0) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        if (length == 1) {
            return strSimpleClassName.toLowerCase(Locale.US);
        }
        if (!Character.isUpperCase(strSimpleClassName.charAt(0)) || !Character.isLowerCase(strSimpleClassName.charAt(1))) {
            return strSimpleClassName;
        }
        return Character.toLowerCase(strSimpleClassName.charAt(0)) + strSimpleClassName.substring(1);
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread threadNewThread = newThread(FastThreadLocalRunnable.wrap(runnable), this.prefix + this.nextId.incrementAndGet());
        try {
            boolean zIsDaemon = threadNewThread.isDaemon();
            boolean z = this.daemon;
            if (zIsDaemon != z) {
                threadNewThread.setDaemon(z);
            }
            int priority = threadNewThread.getPriority();
            int i = this.priority;
            if (priority != i) {
                threadNewThread.setPriority(i);
            }
        } catch (Exception unused) {
        }
        return threadNewThread;
    }

    protected Thread newThread(Runnable runnable, String str) {
        return new FastThreadLocalThread(this.threadGroup, runnable, str);
    }
}
