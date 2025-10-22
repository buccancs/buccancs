package io.grpc.netty.shaded.io.netty.util.internal.logging;

import org.apache.commons.logging.LogFactory;

@Deprecated
/* loaded from: classes3.dex */
public class CommonsLoggerFactory extends InternalLoggerFactory {
    public static final InternalLoggerFactory INSTANCE = new CommonsLoggerFactory();

    @Deprecated
    public CommonsLoggerFactory() {
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory
    public InternalLogger newInstance(String str) {
        return new CommonsLogger(LogFactory.getLog(str), str);
    }
}
