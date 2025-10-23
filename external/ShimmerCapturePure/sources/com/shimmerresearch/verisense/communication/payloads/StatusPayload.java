package com.shimmerresearch.verisense.communication.payloads;

import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.UtilParseData;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.verisense.VerisenseDevice;
import com.shimmerresearch.verisense.sensors.SensorVerisenseClock;

import java.io.Serializable;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes2.dex */
public class StatusPayload extends AbstractPayload implements Serializable {
    public static final double MAX_FOUR_BTE_UNSIGNED_VALUE = Math.pow(2.0d, 32.0d) - 1.0d;
    public boolean adaptiveSchedulerEnabled;
    public boolean dfuServiceOn;
    public boolean flashIsFull;
    public int flashWriteFailCounter;
    public boolean powerIsGood;
    public boolean recordingPaused;
    public boolean secondaryStatusMsg;
    public boolean statusFlagFirstBoot;
    public long statusFlagsReversed;
    public long timestampNextSyncAttempt;
    public boolean usbPowered;
    public String verisenseId;
    public double batteryLevelMillivolts = 0.0d;
    public double batteryPercentage = 0.0d;
    public double verisenseStatusTimestampMs = 0.0d;
    public double lastTransferSuccessTimestampMs = 0.0d;
    public double lastTransferFailTimestampMs = 0.0d;
    public long baseStationTimestampMs = 0;
    public double nextSyncAttemptTimestampMs = 0.0d;
    public long batteryVoltageFallCounter = 0;
    public long statusFlags = -1;
    public int syncType = -1;
    public int storageFreekB = -1;
    public int storageFullkB = -1;
    public int storageToDelkB = -1;
    public int storageBadkB = -1;
    public int storageOtherkB = -1;
    public int storageCapacitykB = -1;
    public long failedBleConnectionAttemptCount = 0;
    public int flashWriteRetryCounterShortTry = -1;
    public int flashWriteRetryCounterLongTry = -1;
    public boolean isChargerChipPresent = false;
    public int batteryChargerStatusValue = -1;
    public Object batteryChargerStatus = null;
    private int hwVerMajor = -1;
    private int hwVerMinor = -1;
    private int hwVerInternal = -1;

    public void calculateStorageOther() {
        this.storageOtherkB = this.storageCapacitykB - (((this.storageFreekB + this.storageFullkB) + this.storageToDelkB) + this.storageBadkB);
    }

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public byte[] generatePayloadContents() {
        return null;
    }

    public double getStorageBadPercent() {
        return (this.storageBadkB * 100.0d) / this.storageCapacitykB;
    }

    public double getStorageFreePercent() {
        return (this.storageFreekB * 100.0d) / this.storageCapacitykB;
    }

    public double getStorageFullPercent() {
        return (this.storageFullkB * 100.0d) / this.storageCapacitykB;
    }

    public double getStorageOtherPercent() {
        return (this.storageOtherkB * 100.0d) / this.storageCapacitykB;
    }

    public double getStorageToDeletePercent() {
        return (this.storageToDelkB * 100.0d) / this.storageCapacitykB;
    }

    public boolean isStatusFlagFirstBoot() {
        return this.statusFlagFirstBoot;
    }

    public boolean isStatusFlagRecordingPaused() {
        return this.recordingPaused;
    }

    public boolean isStatusFlagSecondaryStatusMsg() {
        return this.secondaryStatusMsg;
    }

    public boolean isStatusFlagValid() {
        return this.statusFlags != -1;
    }

    public void setShimmerHwVer(int i, int i2, int i3) {
        this.hwVerMajor = i;
        this.hwVerMinor = i2;
        this.hwVerInternal = i3;
    }

    public void updateStorageCapacitykBBasedOnHw(int i, int i2, int i3) {
        if (i == 68 && i2 == 8) {
            this.storageCapacitykB = 131072;
        } else {
            this.storageCapacitykB = 524288;
        }
    }

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public boolean parsePayloadContents(byte[] bArr) {
        long byteArrayAtIndex;
        long byteArrayAtIndex2;
        this.payloadContents = bArr;
        this.isSuccess = false;
        this.verisenseId = parseVerisenseId(bArr, 0);
        this.verisenseStatusTimestampMs = SensorVerisenseClock.convertRtcMinutesAndTicksToMs(parseByteArrayAtIndex(bArr, 6, ChannelDetails.CHANNEL_DATA_TYPE.UINT32), bArr.length > 34 ? parseByteArrayAtIndex(bArr, 34, ChannelDetails.CHANNEL_DATA_TYPE.UINT24) : 0L);
        this.batteryLevelMillivolts = parseByteArrayAtIndex(bArr, 10, ChannelDetails.CHANNEL_DATA_TYPE.UINT16);
        this.batteryPercentage = bArr[12];
        long byteArrayAtIndex3 = parseByteArrayAtIndex(bArr, 13, ChannelDetails.CHANNEL_DATA_TYPE.UINT32);
        long byteArrayAtIndex4 = parseByteArrayAtIndex(bArr, 17, ChannelDetails.CHANNEL_DATA_TYPE.UINT32);
        if (bArr.length > 34) {
            byteArrayAtIndex = parseByteArrayAtIndex(bArr, 37, ChannelDetails.CHANNEL_DATA_TYPE.UINT24);
            byteArrayAtIndex2 = parseByteArrayAtIndex(bArr, 40, ChannelDetails.CHANNEL_DATA_TYPE.UINT24);
        } else {
            byteArrayAtIndex = 0;
            byteArrayAtIndex2 = 0;
        }
        double d = byteArrayAtIndex3;
        double d2 = MAX_FOUR_BTE_UNSIGNED_VALUE;
        this.lastTransferSuccessTimestampMs = d == d2 ? -1.0d : SensorVerisenseClock.convertRtcMinutesAndTicksToMs(byteArrayAtIndex3, byteArrayAtIndex);
        this.lastTransferFailTimestampMs = ((double) byteArrayAtIndex4) == d2 ? -1.0d : SensorVerisenseClock.convertRtcMinutesAndTicksToMs(byteArrayAtIndex4, byteArrayAtIndex2);
        if (isStatusFromFwV1_02_102_onwards()) {
            this.storageCapacitykB = (int) parseByteArrayAtIndex(bArr, 60, ChannelDetails.CHANNEL_DATA_TYPE.UINT32);
        } else {
            updateStorageCapacitykBBasedOnHw();
        }
        if (isStatusFromFwV1_02_102_onwards()) {
            this.storageFreekB = parseStorageValueSplitFwV1_02_102(bArr, 21, 57);
        } else {
            this.storageFreekB = (int) parseByteArrayAtIndex(bArr, 21, ChannelDetails.CHANNEL_DATA_TYPE.UINT24);
        }
        if (isStatusFromFwV1_02_055_onwards()) {
            if (isStatusFromFwV1_02_102_onwards()) {
                this.storageFullkB = parseStorageValueSplitFwV1_02_102(bArr, 47, 58);
                this.storageToDelkB = parseStorageValueSplitFwV1_02_102(bArr, 50, 59);
                this.storageBadkB = (int) parseByteArrayAtIndex(bArr, 53, ChannelDetails.CHANNEL_DATA_TYPE.UINT32);
            } else {
                this.storageFullkB = (int) parseByteArrayAtIndex(bArr, 47, ChannelDetails.CHANNEL_DATA_TYPE.UINT24);
                this.storageToDelkB = (int) parseByteArrayAtIndex(bArr, 50, ChannelDetails.CHANNEL_DATA_TYPE.UINT24);
                this.storageBadkB = (int) parseByteArrayAtIndex(bArr, 53, ChannelDetails.CHANNEL_DATA_TYPE.UINT24);
            }
            calculateStorageOther();
        }
        if (bArr.length <= 24) {
            this.batteryVoltageFallCounter = -1L;
        } else {
            this.batteryVoltageFallCounter = parseByteArrayAtIndex(bArr, 24, ChannelDetails.CHANNEL_DATA_TYPE.UINT16);
        }
        if (bArr.length > 26) {
            this.statusFlags = parseByteArrayAtIndex(bArr, 26, ChannelDetails.CHANNEL_DATA_TYPE.UINT64);
            byte[] bArr2 = new byte[8];
            System.arraycopy(bArr, 26, bArr2, 0, 8);
            ArrayUtils.reverse(bArr2);
            this.statusFlagsReversed = Long.parseLong(Hex.toHexString(bArr2).replace("-", ""), 16);
            parseStatusFlagBytes(this.statusFlags);
        }
        if (bArr.length > 34) {
            long byteArrayAtIndex5 = parseByteArrayAtIndex(bArr, 43, ChannelDetails.CHANNEL_DATA_TYPE.UINT32);
            this.nextSyncAttemptTimestampMs = ((double) byteArrayAtIndex5) != d2 ? SensorVerisenseClock.convertRtcMinutesAndTicksToMs(byteArrayAtIndex5, 0L) : -1.0d;
        }
        if (isStatusFromFwV1_02_055_onwards()) {
            this.timestampNextSyncAttempt = parseByteArrayAtIndex(bArr, 43, ChannelDetails.CHANNEL_DATA_TYPE.UINT32);
        }
        if (isStatusFromFwV1_02_102_onwards()) {
            byte b = bArr[64];
            this.isChargerChipPresent = (b & 1) > 0;
            this.batteryChargerStatusValue = (b >> 1) & 3;
            parseBatteryChargerStatusValue();
        }
        this.isSuccess = true;
        return this.isSuccess;
    }

    public void parseStatusFlagBytes(long j) {
        if (isStatusFlagValid()) {
            this.usbPowered = (1 & j) > 0;
            this.recordingPaused = (2 & j) > 0;
            this.flashIsFull = (4 & j) > 0;
            this.powerIsGood = (8 & j) > 0;
            this.adaptiveSchedulerEnabled = (16 & j) > 0;
            this.dfuServiceOn = (32 & j) > 0;
            this.statusFlagFirstBoot = (64 & j) > 0;
            this.secondaryStatusMsg = (128 & j) > 0;
            this.flashWriteRetryCounterShortTry = (int) ((j >> 8) & 65535);
            this.flashWriteRetryCounterLongTry = (int) ((j >> 24) & 65535);
            this.flashWriteFailCounter = (int) ((j >> 40) & 65535);
            this.failedBleConnectionAttemptCount = (int) ((j >> 56) & 255);
        }
    }

    public void parseBatteryChargerStatusValue() {
        int i = this.hwVerMajor;
        if (i != -1 && VerisenseDevice.isChargerLm3658dPresent(i, this.hwVerMinor, this.hwVerInternal)) {
            this.batteryChargerStatus = VerisenseDevice.CHARGER_STATUS_LM3658D.values()[this.batteryChargerStatusValue];
            return;
        }
        int i2 = this.hwVerMajor;
        if (i2 == -1 || !VerisenseDevice.isChargerLm3658dPresent(i2, this.hwVerMinor, this.hwVerInternal)) {
            this.batteryChargerStatus = null;
        } else {
            this.batteryChargerStatus = VerisenseDevice.CHARGER_STATUS_LTC4123.values()[this.batteryChargerStatusValue];
        }
    }

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public String generateDebugString() {
        String str;
        StringBuilder sb = new StringBuilder("ASM Status:\n");
        sb.append("\tASM Identifier:\t\t\t" + this.verisenseId + StringUtils.LF);
        sb.append("\tStatus Timestamp:\t\t" + UtilShimmer.convertMilliSecondsToDateString((long) this.verisenseStatusTimestampMs, false) + StringUtils.LF);
        sb.append("\tBattery Level:\t\t\t" + this.batteryLevelMillivolts + "mV\n");
        sb.append("\tBattery Percentage:\t\t" + this.batteryPercentage + "%\n");
        double d = this.lastTransferSuccessTimestampMs;
        sb.append("\tLast Ok Data Transfer:\t\t" + (d == -1.0d ? "Not valid" : UtilShimmer.convertMilliSecondsToDateString((long) d, false)) + StringUtils.LF);
        double d2 = this.lastTransferFailTimestampMs;
        sb.append("\tLast Fail Data Transfer:\t" + (d2 != -1.0d ? UtilShimmer.convertMilliSecondsToDateString((long) d2, false) : "Not valid") + StringUtils.LF);
        int i = this.storageCapacitykB;
        int i2 = this.storageFreekB;
        sb.append("\tMemory free:\t\t\t" + i2 + " kBytes / " + i + " kBytes => " + UtilShimmer.formatDoubleToNdecimalPlaces(getStorageFreePercent(), 2) + " %\n");
        StringBuilder sb2 = new StringBuilder("\tMemory Used:\t\t\t");
        sb2.append(i - i2);
        sb2.append(" kBytes\n");
        sb.append(sb2.toString());
        if (this.payloadContents.length >= 38) {
            sb.append("\t\tFULL Banks:\t\t" + this.storageFullkB + " kBytes / " + this.storageCapacitykB + " kBytes => " + UtilShimmer.formatDoubleToNdecimalPlaces(getStorageFullPercent(), 2) + " %\n");
            sb.append("\t\t2DEL Banks:\t\t" + this.storageToDelkB + " kBytes / " + this.storageCapacitykB + " kBytes => " + UtilShimmer.formatDoubleToNdecimalPlaces(getStorageToDeletePercent(), 2) + " %\n");
            sb.append("\t\tBAD Banks:\t\t" + this.storageBadkB + " kBytes / " + this.storageCapacitykB + " kBytes => " + UtilShimmer.formatDoubleToNdecimalPlaces(getStorageBadPercent(), 2) + " %\n");
            sb.append("\t\tOther:\t\t\t" + this.storageOtherkB + " kBytes / " + this.storageCapacitykB + " kBytes => " + UtilShimmer.formatDoubleToNdecimalPlaces(getStorageOtherPercent(), 2) + " %\n");
        }
        if (this.payloadContents.length >= 26) {
            sb.append("\tVBatt Fall Counter:\t\t" + this.batteryVoltageFallCounter + StringUtils.LF);
        }
        if (this.payloadContents.length >= 34) {
            sb.append("\tStatus Message Flags:\n");
            sb.append("\t\tUSB_PLUGGED_IN:\t\t\t" + this.usbPowered + StringUtils.LF);
            sb.append("\t\tRECORDING_PAUSED:\t\t" + this.recordingPaused + StringUtils.LF);
            sb.append("\t\tFLASH_IS_FULL:\t\t\t" + this.flashIsFull + StringUtils.LF);
            sb.append("\t\tPOWER_IS_GOOD:\t\t\t" + this.powerIsGood + StringUtils.LF);
            sb.append("\t\tADAPTIVE_SCHEDULER_ON:\t\t" + this.adaptiveSchedulerEnabled + StringUtils.LF);
            sb.append("\t\tDFU_SERVICE_ON:\t\t\t" + this.dfuServiceOn + StringUtils.LF);
            sb.append("\t\tFIRST BOOT ON:\t\t\t" + this.statusFlagFirstBoot + StringUtils.LF);
            sb.append("\t\tSecondary Status:\t\t" + this.secondaryStatusMsg + StringUtils.LF);
            sb.append("\t\tLTF WRITE - Short Retry Counter (2 minutes gap, 10 retries):\t" + this.flashWriteRetryCounterShortTry + StringUtils.LF);
            sb.append("\t\tLTF WRITE - Long Retry Counter (4hrs gap, 1 retry):\t\t" + this.flashWriteRetryCounterLongTry + StringUtils.LF);
            sb.append("\t\tLTF WRITE FAIL COUNTER:\t\t" + this.failedBleConnectionAttemptCount + StringUtils.LF);
            sb.append("\t\tFAIL SYNC COUNTER:\t\t" + this.flashWriteFailCounter + StringUtils.LF);
        }
        if (isStatusFromFwV1_02_055_onwards()) {
            long j = this.timestampNextSyncAttempt;
            if (j == 0) {
                str = "Not Set";
            } else {
                str = "Will Happen At:" + j;
            }
            sb.append("\tNext Sync Attempt:\t\t" + str + StringUtils.LF);
        }
        return sb.toString();
    }

    public boolean isStatusFromFwV1_02_055_onwards() {
        return this.payloadContents.length >= 56;
    }

    public boolean isStatusFromFwV1_02_102_onwards() {
        return this.payloadContents.length >= 64;
    }

    private int parseStorageValueSplitFwV1_02_102(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[3];
        System.arraycopy(bArr, i, bArr2, 0, 3);
        System.arraycopy(bArr, i2, bArr2, 3, 1);
        return (int) UtilParseData.parseData(bArr2, ChannelDetails.CHANNEL_DATA_TYPE.UINT32, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB);
    }

    public void updateStorageCapacitykBBasedOnHw() {
        int i = this.hwVerMajor;
        if (i != -1) {
            updateStorageCapacitykBBasedOnHw(i, this.hwVerMinor, this.hwVerInternal);
        } else {
            updateStorageCapacitykBBasedOnHw(61, 1, 0);
        }
    }

    public String getChargerChipStatusDescriptionShort() {
        Object obj;
        if (this.isChargerChipPresent && (obj = this.batteryChargerStatus) != null) {
            if (obj instanceof VerisenseDevice.CHARGER_STATUS_LTC4123) {
                return ((VerisenseDevice.CHARGER_STATUS_LTC4123) obj).descriptionShort;
            }
            if (obj instanceof VerisenseDevice.CHARGER_STATUS_LM3658D) {
                return ((VerisenseDevice.CHARGER_STATUS_LM3658D) obj).descriptionShort;
            }
        }
        return "N/A";
    }

    public enum SyncType {
        READ_AND_CLEAR_PENDING_EVENTS("PendingEvent"),
        FORCE_DATA_TRANSFER_SYNC("Forced"),
        UNPAIR("Unpair"),
        PAIR("Pair");

        String name;

        SyncType(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    public class STATUS_FLAGS {
        public static final int BIT_MASK_ADAPTIVE_SCHEDULER_ON = 16;
        public static final int BIT_MASK_DFU_SERVICE_ON = 32;
        public static final int BIT_MASK_FIRST_BOOT = 64;
        public static final int BIT_MASK_FLASH_IS_FULL = 4;
        public static final int BIT_MASK_POWER_IS_GOOD = 8;
        public static final int BIT_MASK_RECORDING_PAUSED = 2;
        public static final int BIT_MASK_SECONDARY_STATUS = 128;
        public static final int BIT_MASK_USB_PLUGGED_IN = 1;
        public static final int BIT_SHIFT_FAIL_COUNT_BLE_SYNC = 56;
        public static final int BIT_SHIFT_FAIL_COUNT_FLASH_WRITE_LSB = 40;
        public static final int BIT_SHIFT_FAIL_COUNT_FLASH_WRITE_MSB = 48;
        public static final int BIT_SHIFT_FLASH_WRITE_RETRY_COUNTER_LONG_TRY_LSB = 24;
        public static final int BIT_SHIFT_FLASH_WRITE_RETRY_COUNTER_LONG_TRY_MSB = 32;
        public static final int BIT_SHIFT_FLASH_WRITE_RETRY_COUNTER_SHORT_TRY_LSB = 8;
        public static final int BIT_SHIFT_FLASH_WRITE_RETRY_COUNTER_SHORT_TRY_MSB = 16;

        public STATUS_FLAGS() {
        }
    }
}
