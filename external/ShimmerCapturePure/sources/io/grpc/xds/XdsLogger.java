package io.grpc.xds;

import com.google.common.base.Preconditions;
import io.grpc.InternalLogId;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/* loaded from: classes3.dex */
final class XdsLogger {
    private static final Logger logger = Logger.getLogger(XdsLogger.class.getName());
    private final String prefix;

    private XdsLogger(String str) {
        this.prefix = (String) Preconditions.checkNotNull(str, "prefix");
    }

    static XdsLogger withLogId(InternalLogId internalLogId) {
        Preconditions.checkNotNull(internalLogId, "logId");
        return new XdsLogger(internalLogId.toString());
    }

    static XdsLogger withPrefix(String str) {
        return new XdsLogger(str);
    }

    private static void logOnly(String str, Level level, String str2) {
        Logger logger2 = logger;
        if (logger2.isLoggable(level)) {
            LogRecord logRecord = new LogRecord(level, "[" + str + "] " + str2);
            logRecord.setLoggerName(logger2.getName());
            logRecord.setSourceClassName(logger2.getName());
            logRecord.setSourceMethodName("log");
            logger2.log(logRecord);
        }
    }

    private static Level toJavaLogLevel(XdsLogLevel xdsLogLevel) {
        int i = AnonymousClass1.$SwitchMap$io$grpc$xds$XdsLogger$XdsLogLevel[xdsLogLevel.ordinal()];
        if (i == 1 || i == 2) {
            return Level.FINE;
        }
        if (i == 3) {
            return Level.FINER;
        }
        return Level.FINEST;
    }

    boolean isLoggable(XdsLogLevel xdsLogLevel) {
        return logger.isLoggable(toJavaLogLevel(xdsLogLevel));
    }

    void log(XdsLogLevel xdsLogLevel, String str) {
        logOnly(this.prefix, toJavaLogLevel(xdsLogLevel), str);
    }

    void log(XdsLogLevel xdsLogLevel, String str, Object... objArr) {
        Level javaLogLevel = toJavaLogLevel(xdsLogLevel);
        if (logger.isLoggable(javaLogLevel)) {
            logOnly(this.prefix, javaLogLevel, MessageFormat.format(str, objArr));
        }
    }

    enum XdsLogLevel {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    /* renamed from: io.grpc.xds.XdsLogger$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$xds$XdsLogger$XdsLogLevel;

        static {
            int[] iArr = new int[XdsLogLevel.values().length];
            $SwitchMap$io$grpc$xds$XdsLogger$XdsLogLevel = iArr;
            try {
                iArr[XdsLogLevel.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$xds$XdsLogger$XdsLogLevel[XdsLogLevel.WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$grpc$xds$XdsLogger$XdsLogLevel[XdsLogLevel.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
