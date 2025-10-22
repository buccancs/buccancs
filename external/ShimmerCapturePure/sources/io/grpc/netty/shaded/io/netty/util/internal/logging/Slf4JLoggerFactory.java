package io.grpc.netty.shaded.io.netty.util.internal.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

/* loaded from: classes3.dex */
public class Slf4JLoggerFactory extends InternalLoggerFactory {
    public static final InternalLoggerFactory INSTANCE = new Slf4JLoggerFactory();
    static final /* synthetic */ boolean $assertionsDisabled = false;

    @Deprecated
    public Slf4JLoggerFactory() {
    }

    Slf4JLoggerFactory(boolean z) {
        if (LoggerFactory.getILoggerFactory() instanceof NOPLoggerFactory) {
            throw new NoClassDefFoundError("NOPLoggerFactory not supported");
        }
    }

    static InternalLogger wrapLogger(Logger logger) {
        return logger instanceof LocationAwareLogger ? new LocationAwareSlf4JLogger((LocationAwareLogger) logger) : new Slf4JLogger(logger);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory
    public InternalLogger newInstance(String str) {
        return wrapLogger(LoggerFactory.getLogger(str));
    }
}
