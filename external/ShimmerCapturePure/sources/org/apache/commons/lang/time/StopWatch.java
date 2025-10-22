package org.apache.commons.lang.time;

/* loaded from: classes5.dex */
public class StopWatch {
    private static final int STATE_RUNNING = 1;
    private static final int STATE_SPLIT = 11;
    private static final int STATE_STOPPED = 2;
    private static final int STATE_SUSPENDED = 3;
    private static final int STATE_UNSPLIT = 10;
    private static final int STATE_UNSTARTED = 0;
    private int runningState = 0;
    private int splitState = 10;
    private long startTime = -1;
    private long stopTime = -1;

    public void reset() {
        this.runningState = 0;
        this.splitState = 10;
        this.startTime = -1L;
        this.stopTime = -1L;
    }

    public void start() {
        int i = this.runningState;
        if (i == 2) {
            throw new IllegalStateException("Stopwatch must be reset before being restarted. ");
        }
        if (i != 0) {
            throw new IllegalStateException("Stopwatch already started. ");
        }
        this.stopTime = -1L;
        this.startTime = System.currentTimeMillis();
        this.runningState = 1;
    }

    public void stop() {
        int i = this.runningState;
        if (i != 1 && i != 3) {
            throw new IllegalStateException("Stopwatch is not running. ");
        }
        if (i == 1) {
            this.stopTime = System.currentTimeMillis();
        }
        this.runningState = 2;
    }

    public void split() {
        if (this.runningState != 1) {
            throw new IllegalStateException("Stopwatch is not running. ");
        }
        this.stopTime = System.currentTimeMillis();
        this.splitState = 11;
    }

    public void unsplit() {
        if (this.splitState != 11) {
            throw new IllegalStateException("Stopwatch has not been split. ");
        }
        this.stopTime = -1L;
        this.splitState = 10;
    }

    public void suspend() {
        if (this.runningState != 1) {
            throw new IllegalStateException("Stopwatch must be running to suspend. ");
        }
        this.stopTime = System.currentTimeMillis();
        this.runningState = 3;
    }

    public void resume() {
        if (this.runningState != 3) {
            throw new IllegalStateException("Stopwatch must be suspended to resume. ");
        }
        this.startTime += System.currentTimeMillis() - this.stopTime;
        this.stopTime = -1L;
        this.runningState = 1;
    }

    public long getTime() {
        long jCurrentTimeMillis;
        long j;
        int i = this.runningState;
        if (i == 2 || i == 3) {
            jCurrentTimeMillis = this.stopTime;
            j = this.startTime;
        } else {
            if (i == 0) {
                return 0L;
            }
            if (i == 1) {
                jCurrentTimeMillis = System.currentTimeMillis();
                j = this.startTime;
            } else {
                throw new RuntimeException("Illegal running state has occured. ");
            }
        }
        return jCurrentTimeMillis - j;
    }

    public long getSplitTime() {
        if (this.splitState == 11) {
            return this.stopTime - this.startTime;
        }
        throw new IllegalStateException("Stopwatch must be split to get the split time. ");
    }

    public long getStartTime() {
        if (this.runningState != 0) {
            return this.startTime;
        }
        throw new IllegalStateException("Stopwatch has not been started");
    }

    public String toString() {
        return DurationFormatUtils.formatDurationHMS(getTime());
    }

    public String toSplitString() {
        return DurationFormatUtils.formatDurationHMS(getSplitTime());
    }
}
