package io.grpc.netty.shaded.io.netty.util.internal.logging;

import java.security.AccessController;
import java.security.PrivilegedAction;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.apache.logging.log4j.spi.ExtendedLoggerWrapper;

/* loaded from: classes3.dex */
class Log4J2Logger extends ExtendedLoggerWrapper implements InternalLogger {
    private static final boolean VARARGS_ONLY = ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() { // from class: io.grpc.netty.shaded.io.netty.util.internal.logging.Log4J2Logger.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.security.PrivilegedAction
        public Boolean run() throws NoSuchMethodException, SecurityException {
            try {
                Logger.class.getMethod("debug", String.class, Object.class);
                return false;
            } catch (NoSuchMethodException unused) {
                return true;
            } catch (SecurityException unused2) {
                return false;
            }
        }
    })).booleanValue();
    private static final long serialVersionUID = 5485418394879791397L;

    Log4J2Logger(Logger logger) {
        super((ExtendedLogger) logger, logger.getName(), logger.getMessageFactory());
        if (VARARGS_ONLY) {
            throw new UnsupportedOperationException("Log4J2 version mismatch");
        }
    }

    private static Level toLevel(InternalLogLevel internalLogLevel) {
        int i = AnonymousClass2.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            return Level.INFO;
        }
        if (i == 2) {
            return Level.DEBUG;
        }
        if (i == 3) {
            return Level.WARN;
        }
        if (i == 4) {
            return Level.ERROR;
        }
        if (i == 5) {
            return Level.TRACE;
        }
        throw new Error();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public String name() {
        return getName();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void trace(Throwable th) {
        log(Level.TRACE, "Unexpected exception:", th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void debug(Throwable th) {
        log(Level.DEBUG, "Unexpected exception:", th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void info(Throwable th) {
        log(Level.INFO, "Unexpected exception:", th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void warn(Throwable th) {
        log(Level.WARN, "Unexpected exception:", th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void error(Throwable th) {
        log(Level.ERROR, "Unexpected exception:", th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public boolean isEnabled(InternalLogLevel internalLogLevel) {
        return isEnabled(toLevel(internalLogLevel));
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str) {
        log(toLevel(internalLogLevel), str);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Object obj) {
        log(toLevel(internalLogLevel), str, obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Object obj, Object obj2) {
        log(toLevel(internalLogLevel), str, obj, obj2);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Object... objArr) {
        log(toLevel(internalLogLevel), str, objArr);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Throwable th) {
        log(toLevel(internalLogLevel), str, th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, Throwable th) {
        log(toLevel(internalLogLevel), "Unexpected exception:", th);
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.util.internal.logging.Log4J2Logger$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$util$internal$logging$InternalLogLevel;

        static {
            int[] iArr = new int[InternalLogLevel.values().length];
            $SwitchMap$io$netty$util$internal$logging$InternalLogLevel = iArr;
            try {
                iArr[InternalLogLevel.INFO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel[InternalLogLevel.DEBUG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel[InternalLogLevel.WARN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel[InternalLogLevel.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel[InternalLogLevel.TRACE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
