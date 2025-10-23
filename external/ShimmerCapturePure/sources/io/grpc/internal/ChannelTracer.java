package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.ChannelLogger;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
final class ChannelTracer {
    static final Logger logger = Logger.getLogger(ChannelLogger.class.getName());
    private final long channelCreationTimeNanos;

    @Nullable
    private final Collection<InternalChannelz.ChannelTrace.Event> events;
    private final Object lock = new Object();
    private final InternalLogId logId;
    private int eventsLogged;

    ChannelTracer(InternalLogId internalLogId, final int i, long j, String str) {
        Preconditions.checkNotNull(str, "description");
        this.logId = (InternalLogId) Preconditions.checkNotNull(internalLogId, "logId");
        if (i > 0) {
            this.events = new ArrayDeque<InternalChannelz.ChannelTrace.Event>() { // from class: io.grpc.internal.ChannelTracer.1
                @Override
                // java.util.ArrayDeque, java.util.AbstractCollection, java.util.Collection, java.util.Deque, java.util.Queue
                public boolean add(InternalChannelz.ChannelTrace.Event event) {
                    if (size() == i) {
                        removeFirst();
                    }
                    ChannelTracer.access$008(ChannelTracer.this);
                    return super.add((AnonymousClass1) event);
                }
            };
        } else {
            this.events = null;
        }
        this.channelCreationTimeNanos = j;
        reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription(str + " created").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(j).build());
    }

    static /* synthetic */ int access$008(ChannelTracer channelTracer) {
        int i = channelTracer.eventsLogged;
        channelTracer.eventsLogged = i + 1;
        return i;
    }

    static void logOnly(InternalLogId internalLogId, Level level, String str) {
        Logger logger2 = logger;
        if (logger2.isLoggable(level)) {
            LogRecord logRecord = new LogRecord(level, "[" + internalLogId + "] " + str);
            logRecord.setLoggerName(logger2.getName());
            logRecord.setSourceClassName(logger2.getName());
            logRecord.setSourceMethodName("log");
            logger2.log(logRecord);
        }
    }

    InternalLogId getLogId() {
        return this.logId;
    }

    void reportEvent(InternalChannelz.ChannelTrace.Event event) {
        Level level;
        int i = AnonymousClass2.$SwitchMap$io$grpc$InternalChannelz$ChannelTrace$Event$Severity[event.severity.ordinal()];
        if (i == 1) {
            level = Level.FINE;
        } else if (i == 2) {
            level = Level.FINER;
        } else {
            level = Level.FINEST;
        }
        traceOnly(event);
        logOnly(this.logId, level, event.description);
    }

    boolean isTraceEnabled() {
        boolean z;
        synchronized (this.lock) {
            z = this.events != null;
        }
        return z;
    }

    void traceOnly(InternalChannelz.ChannelTrace.Event event) {
        synchronized (this.lock) {
            Collection<InternalChannelz.ChannelTrace.Event> collection = this.events;
            if (collection != null) {
                collection.add(event);
            }
        }
    }

    void updateBuilder(InternalChannelz.ChannelStats.Builder builder) {
        synchronized (this.lock) {
            if (this.events == null) {
                return;
            }
            builder.setChannelTrace(new InternalChannelz.ChannelTrace.Builder().setNumEventsLogged(this.eventsLogged).setCreationTimeNanos(this.channelCreationTimeNanos).setEvents(new ArrayList(this.events)).build());
        }
    }

    /* renamed from: io.grpc.internal.ChannelTracer$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$InternalChannelz$ChannelTrace$Event$Severity;

        static {
            int[] iArr = new int[InternalChannelz.ChannelTrace.Event.Severity.values().length];
            $SwitchMap$io$grpc$InternalChannelz$ChannelTrace$Event$Severity = iArr;
            try {
                iArr[InternalChannelz.ChannelTrace.Event.Severity.CT_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$InternalChannelz$ChannelTrace$Event$Severity[InternalChannelz.ChannelTrace.Event.Severity.CT_WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
