package com.shimmerresearch.verisense;

import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.verisense.communication.payloads.AbstractPayload;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class PendingEventSchedule implements Serializable {
    private int intervalHours;
    private int retryIntervalMinutes;
    private int wakeupDurationMinutes;
    private int wakeupTimeMinutes;

    public PendingEventSchedule(byte[] bArr, int i) {
        this.intervalHours = (int) AbstractPayload.parseByteArrayAtIndex(bArr, i, ChannelDetails.CHANNEL_DATA_TYPE.UINT8);
        this.wakeupTimeMinutes = (int) AbstractPayload.parseByteArrayAtIndex(bArr, i + 1, ChannelDetails.CHANNEL_DATA_TYPE.UINT16);
        this.wakeupDurationMinutes = (int) AbstractPayload.parseByteArrayAtIndex(bArr, i + 3, ChannelDetails.CHANNEL_DATA_TYPE.UINT8);
        this.retryIntervalMinutes = (int) AbstractPayload.parseByteArrayAtIndex(bArr, i + 4, ChannelDetails.CHANNEL_DATA_TYPE.UINT16);
    }

    public PendingEventSchedule(int i, int i2, int i3, int i4) {
        this.intervalHours = i;
        this.wakeupTimeMinutes = i2;
        this.wakeupDurationMinutes = i3;
        this.retryIntervalMinutes = i4;
    }

    public byte[] generateByteArray() {
        int i = this.wakeupTimeMinutes;
        int i2 = this.retryIntervalMinutes;
        return new byte[]{(byte) this.intervalHours, (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) this.wakeupDurationMinutes, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255)};
    }

    public int getIntervalHours() {
        return this.intervalHours;
    }

    public void setIntervalHours(int i) {
        this.intervalHours = UtilShimmer.nudgeInteger(i, 0, (int) (Math.pow(2.0d, 8.0d) - 1.0d));
    }

    public int getRetryIntervalMinutes() {
        return this.retryIntervalMinutes;
    }

    public void setRetryIntervalMinutes(int i) {
        this.retryIntervalMinutes = UtilShimmer.nudgeInteger(i, 0, (int) (Math.pow(2.0d, 16.0d) - 1.0d));
    }

    public int getWakeupDurationMinutes() {
        return this.wakeupDurationMinutes;
    }

    public void setWakeupDurationMinutes(int i) {
        this.wakeupDurationMinutes = UtilShimmer.nudgeInteger(i, 0, (int) (Math.pow(2.0d, 8.0d) - 1.0d));
    }

    public int getWakeupTimeMinutes() {
        return this.wakeupTimeMinutes;
    }

    public void setWakeupTimeMinutes(int i) {
        this.wakeupTimeMinutes = UtilShimmer.nudgeInteger(i, 0, (int) (Math.pow(2.0d, 16.0d) - 1.0d));
    }
}
