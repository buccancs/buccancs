package io.grpc;

/* loaded from: classes2.dex */
public abstract class ChannelLogger {

    public abstract void log(ChannelLogLevel channelLogLevel, String str);

    public abstract void log(ChannelLogLevel channelLogLevel, String str, Object... objArr);

    public enum ChannelLogLevel {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }
}
