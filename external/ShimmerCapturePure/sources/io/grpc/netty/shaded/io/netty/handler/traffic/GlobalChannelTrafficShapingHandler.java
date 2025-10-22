package io.grpc.netty.shaded.io.netty.handler.traffic;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler;
import io.grpc.netty.shaded.io.netty.util.Attribute;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.AbstractCollection;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@ChannelHandler.Sharable
/* loaded from: classes3.dex */
public class GlobalChannelTrafficShapingHandler extends AbstractTrafficShapingHandler {
    private static final float DEFAULT_ACCELERATION = -0.1f;
    private static final float DEFAULT_DEVIATION = 0.1f;
    private static final float DEFAULT_SLOWDOWN = 0.4f;
    private static final float MAX_DEVIATION = 0.4f;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) GlobalChannelTrafficShapingHandler.class);
    final ConcurrentMap<Integer, PerChannel> channelQueues;
    private final AtomicLong cumulativeReadBytes;
    private final AtomicLong cumulativeWrittenBytes;
    private final AtomicLong queuesSize;
    volatile long maxGlobalWriteSize;
    private volatile float accelerationFactor;
    private volatile float maxDeviation;
    private volatile long readChannelLimit;
    private volatile boolean readDeviationActive;
    private volatile float slowDownFactor;
    private volatile long writeChannelLimit;
    private volatile boolean writeDeviationActive;

    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService, long j, long j2, long j3, long j4, long j5, long j6) {
        super(j, j2, j5, j6);
        this.channelQueues = PlatformDependent.newConcurrentHashMap();
        this.queuesSize = new AtomicLong();
        this.cumulativeWrittenBytes = new AtomicLong();
        this.cumulativeReadBytes = new AtomicLong();
        this.maxGlobalWriteSize = 419430400L;
        createGlobalTrafficCounter(scheduledExecutorService);
        this.writeChannelLimit = j3;
        this.readChannelLimit = j4;
    }

    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService, long j, long j2, long j3, long j4, long j5) {
        super(j, j2, j5);
        this.channelQueues = PlatformDependent.newConcurrentHashMap();
        this.queuesSize = new AtomicLong();
        this.cumulativeWrittenBytes = new AtomicLong();
        this.cumulativeReadBytes = new AtomicLong();
        this.maxGlobalWriteSize = 419430400L;
        this.writeChannelLimit = j3;
        this.readChannelLimit = j4;
        createGlobalTrafficCounter(scheduledExecutorService);
    }

    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService, long j, long j2, long j3, long j4) {
        super(j, j2);
        this.channelQueues = PlatformDependent.newConcurrentHashMap();
        this.queuesSize = new AtomicLong();
        this.cumulativeWrittenBytes = new AtomicLong();
        this.cumulativeReadBytes = new AtomicLong();
        this.maxGlobalWriteSize = 419430400L;
        this.writeChannelLimit = j3;
        this.readChannelLimit = j4;
        createGlobalTrafficCounter(scheduledExecutorService);
    }

    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService, long j) {
        super(j);
        this.channelQueues = PlatformDependent.newConcurrentHashMap();
        this.queuesSize = new AtomicLong();
        this.cumulativeWrittenBytes = new AtomicLong();
        this.cumulativeReadBytes = new AtomicLong();
        this.maxGlobalWriteSize = 419430400L;
        createGlobalTrafficCounter(scheduledExecutorService);
    }

    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService) {
        this.channelQueues = PlatformDependent.newConcurrentHashMap();
        this.queuesSize = new AtomicLong();
        this.cumulativeWrittenBytes = new AtomicLong();
        this.cumulativeReadBytes = new AtomicLong();
        this.maxGlobalWriteSize = 419430400L;
        createGlobalTrafficCounter(scheduledExecutorService);
    }

    private long computeBalancedWait(float f, float f2, long j) {
        float f3;
        if (f2 == 0.0f) {
            return j;
        }
        float f4 = f / f2;
        if (f4 <= this.maxDeviation) {
            f3 = this.accelerationFactor;
        } else {
            if (f4 < 1.0f - this.maxDeviation) {
                return j;
            }
            f3 = this.slowDownFactor;
            if (j < 10) {
                j = 10;
            }
        }
        return (long) (j * f3);
    }

    public float accelerationFactor() {
        return this.accelerationFactor;
    }

    public long getMaxGlobalWriteSize() {
        return this.maxGlobalWriteSize;
    }

    public void setMaxGlobalWriteSize(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxGlobalWriteSize must be positive");
        }
        this.maxGlobalWriteSize = j;
    }

    public long getReadChannelLimit() {
        return this.readChannelLimit;
    }

    public void setReadChannelLimit(long j) {
        this.readChannelLimit = j;
        long jMilliSecondFromNano = TrafficCounter.milliSecondFromNano();
        Iterator<PerChannel> it2 = this.channelQueues.values().iterator();
        while (it2.hasNext()) {
            it2.next().channelTrafficCounter.resetAccounting(jMilliSecondFromNano);
        }
    }

    public long getWriteChannelLimit() {
        return this.writeChannelLimit;
    }

    public void setWriteChannelLimit(long j) {
        this.writeChannelLimit = j;
        long jMilliSecondFromNano = TrafficCounter.milliSecondFromNano();
        Iterator<PerChannel> it2 = this.channelQueues.values().iterator();
        while (it2.hasNext()) {
            it2.next().channelTrafficCounter.resetAccounting(jMilliSecondFromNano);
        }
    }

    public float maxDeviation() {
        return this.maxDeviation;
    }

    public float slowDownFactor() {
        return this.slowDownFactor;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler
    protected int userDefinedWritabilityIndex() {
        return 3;
    }

    void createGlobalTrafficCounter(ScheduledExecutorService scheduledExecutorService) {
        setMaxDeviation(DEFAULT_DEVIATION, 0.4f, DEFAULT_ACCELERATION);
        if (scheduledExecutorService == null) {
            throw new IllegalArgumentException("Executor must not be null");
        }
        GlobalChannelTrafficCounter globalChannelTrafficCounter = new GlobalChannelTrafficCounter(this, scheduledExecutorService, "GlobalChannelTC", this.checkInterval);
        setTrafficCounter(globalChannelTrafficCounter);
        globalChannelTrafficCounter.start();
    }

    public void setMaxDeviation(float f, float f2, float f3) {
        if (f > 0.4f) {
            throw new IllegalArgumentException("maxDeviation must be <= 0.4");
        }
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("slowDownFactor must be >= 0");
        }
        if (f3 > 0.0f) {
            throw new IllegalArgumentException("accelerationFactor must be <= 0");
        }
        this.maxDeviation = f;
        this.accelerationFactor = f3 + 1.0f;
        this.slowDownFactor = f2 + 1.0f;
    }

    private void computeDeviationCumulativeBytes() {
        long j = 0;
        long j2 = Long.MAX_VALUE;
        long j3 = Long.MAX_VALUE;
        long j4 = 0;
        for (PerChannel perChannel : this.channelQueues.values()) {
            long jCumulativeWrittenBytes = perChannel.channelTrafficCounter.cumulativeWrittenBytes();
            if (j < jCumulativeWrittenBytes) {
                j = jCumulativeWrittenBytes;
            }
            if (j2 > jCumulativeWrittenBytes) {
                j2 = jCumulativeWrittenBytes;
            }
            long jCumulativeReadBytes = perChannel.channelTrafficCounter.cumulativeReadBytes();
            if (j4 < jCumulativeReadBytes) {
                j4 = jCumulativeReadBytes;
            }
            if (j3 > jCumulativeReadBytes) {
                j3 = jCumulativeReadBytes;
            }
        }
        boolean z = false;
        boolean z2 = this.channelQueues.size() > 1;
        this.readDeviationActive = z2 && j3 < j4 / 2;
        if (z2 && j2 < j / 2) {
            z = true;
        }
        this.writeDeviationActive = z;
        this.cumulativeWrittenBytes.set(j);
        this.cumulativeReadBytes.set(j4);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler
    protected void doAccounting(TrafficCounter trafficCounter) {
        computeDeviationCumulativeBytes();
        super.doAccounting(trafficCounter);
    }

    public long queuesSize() {
        return this.queuesSize.get();
    }

    public void configureChannel(long j, long j2) {
        this.writeChannelLimit = j;
        this.readChannelLimit = j2;
        long jMilliSecondFromNano = TrafficCounter.milliSecondFromNano();
        Iterator<PerChannel> it2 = this.channelQueues.values().iterator();
        while (it2.hasNext()) {
            it2.next().channelTrafficCounter.resetAccounting(jMilliSecondFromNano);
        }
    }

    public final void release() {
        this.trafficCounter.stop();
    }

    private PerChannel getOrSetPerChannel(ChannelHandlerContext channelHandlerContext) {
        Integer numValueOf = Integer.valueOf(channelHandlerContext.channel().hashCode());
        PerChannel perChannel = this.channelQueues.get(numValueOf);
        if (perChannel != null) {
            return perChannel;
        }
        PerChannel perChannel2 = new PerChannel();
        perChannel2.messagesQueue = new ArrayDeque<>();
        perChannel2.channelTrafficCounter = new TrafficCounter(this, null, "ChannelTC" + channelHandlerContext.channel().hashCode(), this.checkInterval);
        perChannel2.queueSize = 0L;
        perChannel2.lastReadTimestamp = TrafficCounter.milliSecondFromNano();
        perChannel2.lastWriteTimestamp = perChannel2.lastReadTimestamp;
        this.channelQueues.put(numValueOf, perChannel2);
        return perChannel2;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        getOrSetPerChannel(channelHandlerContext);
        this.trafficCounter.resetCumulativeTime();
        super.handlerAdded(channelHandlerContext);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.trafficCounter.resetCumulativeTime();
        Channel channel = channelHandlerContext.channel();
        PerChannel perChannelRemove = this.channelQueues.remove(Integer.valueOf(channel.hashCode()));
        if (perChannelRemove != null) {
            synchronized (perChannelRemove) {
                if (channel.isActive()) {
                    Iterator<ToSend> it2 = perChannelRemove.messagesQueue.iterator();
                    while (it2.hasNext()) {
                        ToSend next = it2.next();
                        long jCalculateSize = calculateSize(next.toSend);
                        this.trafficCounter.bytesRealWriteFlowControl(jCalculateSize);
                        perChannelRemove.channelTrafficCounter.bytesRealWriteFlowControl(jCalculateSize);
                        perChannelRemove.queueSize -= jCalculateSize;
                        this.queuesSize.addAndGet(-jCalculateSize);
                        channelHandlerContext.write(next.toSend, next.promise);
                    }
                } else {
                    this.queuesSize.addAndGet(-perChannelRemove.queueSize);
                    Iterator<ToSend> it3 = perChannelRemove.messagesQueue.iterator();
                    while (it3.hasNext()) {
                        ToSend next2 = it3.next();
                        if (next2.toSend instanceof ByteBuf) {
                            ((ByteBuf) next2.toSend).release();
                        }
                    }
                }
                perChannelRemove.messagesQueue.clear();
            }
        }
        releaseWriteSuspended(channelHandlerContext);
        releaseReadSuspended(channelHandlerContext);
        super.handlerRemoved(channelHandlerContext);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        long j;
        long jCalculateSize = calculateSize(obj);
        long jMilliSecondFromNano = TrafficCounter.milliSecondFromNano();
        if (jCalculateSize > 0) {
            long timeToWait = this.trafficCounter.readTimeToWait(jCalculateSize, getReadLimit(), this.maxTime, jMilliSecondFromNano);
            PerChannel perChannel = this.channelQueues.get(Integer.valueOf(channelHandlerContext.channel().hashCode()));
            if (perChannel != null) {
                long timeToWait2 = perChannel.channelTrafficCounter.readTimeToWait(jCalculateSize, this.readChannelLimit, this.maxTime, jMilliSecondFromNano);
                if (this.readDeviationActive) {
                    long jCumulativeReadBytes = perChannel.channelTrafficCounter.cumulativeReadBytes();
                    long j2 = this.cumulativeReadBytes.get();
                    jComputeBalancedWait = jCumulativeReadBytes > 0 ? jCumulativeReadBytes : 0L;
                    if (j2 < jComputeBalancedWait) {
                        j2 = jComputeBalancedWait;
                    }
                    jComputeBalancedWait = computeBalancedWait(jComputeBalancedWait, j2, timeToWait2);
                } else {
                    jComputeBalancedWait = timeToWait2;
                }
            }
            if (jComputeBalancedWait < timeToWait) {
                jComputeBalancedWait = timeToWait;
            }
            j = jMilliSecondFromNano;
            long jCheckWaitReadTime = checkWaitReadTime(channelHandlerContext, jComputeBalancedWait, jMilliSecondFromNano);
            if (jCheckWaitReadTime >= 10) {
                Channel channel = channelHandlerContext.channel();
                ChannelConfig channelConfigConfig = channel.config();
                InternalLogger internalLogger = logger;
                if (internalLogger.isDebugEnabled()) {
                    internalLogger.debug("Read Suspend: " + jCheckWaitReadTime + ':' + channelConfigConfig.isAutoRead() + ':' + isHandlerActive(channelHandlerContext));
                }
                if (channelConfigConfig.isAutoRead() && isHandlerActive(channelHandlerContext)) {
                    channelConfigConfig.setAutoRead(false);
                    channel.attr(READ_SUSPENDED).set(true);
                    Attribute attributeAttr = channel.attr(REOPEN_TASK);
                    Runnable reopenReadTimerTask = (Runnable) attributeAttr.get();
                    if (reopenReadTimerTask == null) {
                        reopenReadTimerTask = new AbstractTrafficShapingHandler.ReopenReadTimerTask(channelHandlerContext);
                        attributeAttr.set(reopenReadTimerTask);
                    }
                    channelHandlerContext.executor().schedule(reopenReadTimerTask, jCheckWaitReadTime, TimeUnit.MILLISECONDS);
                    if (internalLogger.isDebugEnabled()) {
                        internalLogger.debug("Suspend final status => " + channelConfigConfig.isAutoRead() + ':' + isHandlerActive(channelHandlerContext) + " will reopened at: " + jCheckWaitReadTime);
                    }
                }
            }
        } else {
            j = jMilliSecondFromNano;
        }
        informReadOperation(channelHandlerContext, j);
        channelHandlerContext.fireChannelRead(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler
    protected long checkWaitReadTime(ChannelHandlerContext channelHandlerContext, long j, long j2) {
        PerChannel perChannel = this.channelQueues.get(Integer.valueOf(channelHandlerContext.channel().hashCode()));
        return (perChannel == null || j <= this.maxTime || (j2 + j) - perChannel.lastReadTimestamp <= this.maxTime) ? j : this.maxTime;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler
    protected void informReadOperation(ChannelHandlerContext channelHandlerContext, long j) {
        PerChannel perChannel = this.channelQueues.get(Integer.valueOf(channelHandlerContext.channel().hashCode()));
        if (perChannel != null) {
            perChannel.lastReadTimestamp = j;
        }
    }

    protected long maximumCumulativeWrittenBytes() {
        return this.cumulativeWrittenBytes.get();
    }

    protected long maximumCumulativeReadBytes() {
        return this.cumulativeReadBytes.get();
    }

    public Collection<TrafficCounter> channelTrafficCounters() {
        return new AbstractCollection<TrafficCounter>() { // from class: io.grpc.netty.shaded.io.netty.handler.traffic.GlobalChannelTrafficShapingHandler.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
            public Iterator<TrafficCounter> iterator() {
                return new Iterator<TrafficCounter>() { // from class: io.grpc.netty.shaded.io.netty.handler.traffic.GlobalChannelTrafficShapingHandler.1.1
                    final Iterator<PerChannel> iter;

                    {
                        this.iter = GlobalChannelTrafficShapingHandler.this.channelQueues.values().iterator();
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.iter.hasNext();
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    public TrafficCounter next() {
                        return this.iter.next().channelTrafficCounter;
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection
            public int size() {
                return GlobalChannelTrafficShapingHandler.this.channelQueues.size();
            }
        };
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler, io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        long jCalculateSize = calculateSize(obj);
        long jMilliSecondFromNano = TrafficCounter.milliSecondFromNano();
        if (jCalculateSize > 0) {
            long jWriteTimeToWait = this.trafficCounter.writeTimeToWait(jCalculateSize, getWriteLimit(), this.maxTime, jMilliSecondFromNano);
            PerChannel perChannel = this.channelQueues.get(Integer.valueOf(channelHandlerContext.channel().hashCode()));
            if (perChannel != null) {
                long jWriteTimeToWait2 = perChannel.channelTrafficCounter.writeTimeToWait(jCalculateSize, this.writeChannelLimit, this.maxTime, jMilliSecondFromNano);
                if (this.writeDeviationActive) {
                    long jCumulativeWrittenBytes = perChannel.channelTrafficCounter.cumulativeWrittenBytes();
                    long j = this.cumulativeWrittenBytes.get();
                    jComputeBalancedWait = jCumulativeWrittenBytes > 0 ? jCumulativeWrittenBytes : 0L;
                    jComputeBalancedWait = computeBalancedWait(jComputeBalancedWait, j < jComputeBalancedWait ? jComputeBalancedWait : j, jWriteTimeToWait2);
                } else {
                    jComputeBalancedWait = jWriteTimeToWait2;
                }
            }
            if (jComputeBalancedWait >= jWriteTimeToWait) {
                jWriteTimeToWait = jComputeBalancedWait;
            }
            if (jWriteTimeToWait >= 10) {
                InternalLogger internalLogger = logger;
                if (internalLogger.isDebugEnabled()) {
                    internalLogger.debug("Write suspend: " + jWriteTimeToWait + ':' + channelHandlerContext.channel().config().isAutoRead() + ':' + isHandlerActive(channelHandlerContext));
                }
                submitWrite(channelHandlerContext, obj, jCalculateSize, jWriteTimeToWait, jMilliSecondFromNano, channelPromise);
                return;
            }
        }
        submitWrite(channelHandlerContext, obj, jCalculateSize, 0L, jMilliSecondFromNano, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler
    protected void submitWrite(final ChannelHandlerContext channelHandlerContext, Object obj, long j, long j2, long j3, ChannelPromise channelPromise) {
        PerChannel orSetPerChannel = this.channelQueues.get(Integer.valueOf(channelHandlerContext.channel().hashCode()));
        if (orSetPerChannel == null) {
            orSetPerChannel = getOrSetPerChannel(channelHandlerContext);
        }
        final PerChannel perChannel = orSetPerChannel;
        synchronized (perChannel) {
            if (j2 == 0) {
                if (perChannel.messagesQueue.isEmpty()) {
                    this.trafficCounter.bytesRealWriteFlowControl(j);
                    perChannel.channelTrafficCounter.bytesRealWriteFlowControl(j);
                    channelHandlerContext.write(obj, channelPromise);
                    perChannel.lastWriteTimestamp = j3;
                    return;
                }
            }
            long j4 = (j2 <= this.maxTime || (j3 + j2) - perChannel.lastWriteTimestamp <= this.maxTime) ? j2 : this.maxTime;
            ToSend toSend = new ToSend(j4 + j3, obj, j, channelPromise);
            perChannel.messagesQueue.addLast(toSend);
            perChannel.queueSize += j;
            this.queuesSize.addAndGet(j);
            checkWriteSuspend(channelHandlerContext, j4, perChannel.queueSize);
            boolean z = this.queuesSize.get() > this.maxGlobalWriteSize;
            if (z) {
                setUserDefinedWritability(channelHandlerContext, false);
            }
            final long j5 = toSend.relativeTimeAction;
            channelHandlerContext.executor().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.traffic.GlobalChannelTrafficShapingHandler.2
                @Override // java.lang.Runnable
                public void run() {
                    GlobalChannelTrafficShapingHandler.this.sendAllValid(channelHandlerContext, perChannel, j5);
                }
            }, j4, TimeUnit.MILLISECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAllValid(ChannelHandlerContext channelHandlerContext, PerChannel perChannel, long j) {
        synchronized (perChannel) {
            ToSend toSendPollFirst = perChannel.messagesQueue.pollFirst();
            while (true) {
                if (toSendPollFirst != null) {
                    if (toSendPollFirst.relativeTimeAction <= j) {
                        long j2 = toSendPollFirst.size;
                        this.trafficCounter.bytesRealWriteFlowControl(j2);
                        perChannel.channelTrafficCounter.bytesRealWriteFlowControl(j2);
                        perChannel.queueSize -= j2;
                        this.queuesSize.addAndGet(-j2);
                        channelHandlerContext.write(toSendPollFirst.toSend, toSendPollFirst.promise);
                        perChannel.lastWriteTimestamp = j;
                        toSendPollFirst = perChannel.messagesQueue.pollFirst();
                    } else {
                        perChannel.messagesQueue.addFirst(toSendPollFirst);
                        break;
                    }
                } else {
                    break;
                }
            }
            if (perChannel.messagesQueue.isEmpty()) {
                releaseWriteSuspended(channelHandlerContext);
            }
        }
        channelHandlerContext.flush();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler
    public String toString() {
        StringBuilder sb = new StringBuilder(340);
        sb.append(super.toString());
        sb.append(" Write Channel Limit: ");
        sb.append(this.writeChannelLimit);
        sb.append(" Read Channel Limit: ");
        sb.append(this.readChannelLimit);
        return sb.toString();
    }

    static final class PerChannel {
        TrafficCounter channelTrafficCounter;
        long lastReadTimestamp;
        long lastWriteTimestamp;
        ArrayDeque<ToSend> messagesQueue;
        long queueSize;

        PerChannel() {
        }
    }

    private static final class ToSend {
        final ChannelPromise promise;
        final long relativeTimeAction;
        final long size;
        final Object toSend;

        private ToSend(long j, Object obj, long j2, ChannelPromise channelPromise) {
            this.relativeTimeAction = j;
            this.toSend = obj;
            this.size = j2;
            this.promise = channelPromise;
        }
    }
}
