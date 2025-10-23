package com.shimmerresearch.verisense.communication.payloads;

import com.shimmerresearch.verisense.UtilVerisenseDriver;
import com.shimmerresearch.verisense.payloaddesign.VerisenseTimeDetails;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class RwcSchedulePayload extends AbstractPayload {
    public String adaptiveScheduler;
    public String bleControlCounter;
    public long bleNextAlarmTimeMinutesAdaptiveSch;
    public long bleNextAlarmTimeMinutesDataTransfer;
    public long bleNextAlarmTimeMinutesFlashWriteRetry;
    public long bleNextAlarmTimeMinutesRetry;
    public long bleNextAlarmTimeMinutesRtcSync;
    public long bleNextAlarmTimeMinutesStatus;
    public String currentOperation;
    public double currentTimeMs;
    public byte failCounterLong;
    public byte failCounterShort;
    public byte retryCount;
    public String retryOperation;
    public byte syncFailCounter;

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public byte[] generatePayloadContents() {
        return null;
    }

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public boolean parsePayloadContents(byte[] bArr) {
        this.payloadContents = bArr;
        this.isSuccess = false;
        this.currentTimeMs = VerisenseTimeDetails.parseTimeMsFromMinutesAndTicksAtIndex(bArr, 0);
        this.bleControlCounter = "";
        byte b = bArr[7];
        if ((b & 255) == 0) {
            this.bleControlCounter = "Data Transfer";
        } else if ((b & 255) == 1) {
            this.bleControlCounter = "Status";
        } else if ((b & 255) == 2) {
            this.bleControlCounter = "RTC Sync";
        } else if ((b & 255) == 255) {
            this.bleControlCounter = "Never";
        }
        this.bleNextAlarmTimeMinutesDataTransfer = VerisenseTimeDetails.parseTimeMinutesAtIndex(bArr, 8);
        this.bleNextAlarmTimeMinutesStatus = VerisenseTimeDetails.parseTimeMinutesAtIndex(bArr, 16);
        this.bleNextAlarmTimeMinutesRtcSync = VerisenseTimeDetails.parseTimeMinutesAtIndex(bArr, 24);
        this.bleNextAlarmTimeMinutesRetry = VerisenseTimeDetails.parseTimeMinutesAtIndex(bArr, 32);
        this.retryCount = bArr[40];
        if (bArr[41] == 1) {
            this.retryOperation = "BLE ON";
        } else {
            this.retryOperation = "BLE OFF";
        }
        int i = 52;
        if (bArr.length >= 52) {
            this.bleNextAlarmTimeMinutesAdaptiveSch = VerisenseTimeDetails.parseTimeMinutesAtIndex(bArr, 42);
            if (bArr[50] == 1) {
                this.adaptiveScheduler = "On";
            } else {
                this.adaptiveScheduler = "Off";
            }
            this.syncFailCounter = bArr[51];
        } else {
            i = 42;
        }
        if (bArr.length >= 63) {
            this.bleNextAlarmTimeMinutesFlashWriteRetry = VerisenseTimeDetails.parseTimeMinutesAtIndex(bArr, i);
            byte b2 = bArr[i + 8];
            if (b2 == 0) {
                this.currentOperation = "FLASH_WRITE_RETRY_INACTIVE";
            } else if (b2 == 1) {
                this.currentOperation = "SHORT_FLASH_WRITE_RETRY";
            } else if (b2 == 2) {
                this.currentOperation = "ATTEMPT_FLASH_WRITE";
            } else if (b2 == 3) {
                this.currentOperation = "LONG_FLASH_WRITE_RETRY";
            } else if (b2 == 4) {
                this.currentOperation = "SENSOR_PAUSED_UNTIL_USB_PLUG_IN";
            }
            this.failCounterShort = bArr[i + 9];
            this.failCounterLong = bArr[i + 10];
        }
        this.isSuccess = true;
        return this.isSuccess;
    }

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public String generateDebugString() {
        StringBuilder sb = new StringBuilder("RWC Schedule:\n********************************************************\nVerisense Current Time:\n");
        sb.append("\t" + UtilVerisenseDriver.millisecondsToStringWitNanos(this.currentTimeMs) + StringUtils.LF);
        sb.append("BLE Control Counter:\n");
        sb.append("\t" + this.bleControlCounter + StringUtils.LF);
        sb.append("BLE_DATA_TRANS:\n");
        long j = this.bleNextAlarmTimeMinutesDataTransfer;
        if (j == 0) {
            sb.append("\tNext Alarm Time\t= Not Set\n");
        } else {
            sb.append("\tNext Alarm Time\t= " + j + " minutes\n");
            sb.append("\tNext Alarm TIme Will Happen At: " + UtilVerisenseDriver.millisecondsToString((double) (this.bleNextAlarmTimeMinutesDataTransfer * 60000)) + StringUtils.LF);
        }
        sb.append("BLE_STATUS:\n");
        long j2 = this.bleNextAlarmTimeMinutesStatus;
        if (j2 == 0) {
            sb.append("\tNext Alarm Time\t= Not Set\n");
        } else {
            sb.append("\tNext Alarm Time\t= " + j2 + " minutes\n");
            sb.append("\tNext Alarm TIme Will Happen At: " + UtilVerisenseDriver.millisecondsToString((double) (this.bleNextAlarmTimeMinutesStatus * 60000)) + StringUtils.LF);
        }
        sb.append("BLE_RTC_SYNC:\n");
        long j3 = this.bleNextAlarmTimeMinutesRtcSync;
        if (j3 == 0) {
            sb.append("\tNext Alarm Time\t= Not Set\n");
        } else {
            sb.append("\tNext Alarm Time\t= " + j3 + " minutes\n");
            sb.append("\tNext Alarm TIme Will Happen At: " + UtilVerisenseDriver.millisecondsToString((double) (this.bleNextAlarmTimeMinutesRtcSync * 60000)) + StringUtils.LF);
        }
        sb.append("BLE RETRY COUNTER:\n");
        sb.append("\tNext Alarm Time will turn: " + this.retryOperation + StringUtils.LF);
        sb.append("\tRetry Count= " + ((int) this.retryCount) + StringUtils.LF);
        sb.append("\tNext Alarm Time\t= " + this.bleNextAlarmTimeMinutesRetry + " minutes\n");
        sb.append("\tNext Alarm TIme Will Happen At: " + UtilVerisenseDriver.millisecondsToString((double) (this.bleNextAlarmTimeMinutesRetry * 60000)) + StringUtils.LF);
        sb.append("********************************************************\nADAPTIVE SCHEDULER:\n");
        if (this.payloadContents.length >= 52) {
            sb.append("\tCurrent Status: " + this.adaptiveScheduler + StringUtils.LF);
            sb.append("\tSync Fail Counter: " + ((int) this.syncFailCounter) + StringUtils.LF);
            sb.append("\tNext Alarm Time\t= " + this.bleNextAlarmTimeMinutesAdaptiveSch + " minutes\n");
            sb.append("\tNext Alarm TIme Will Happen At: " + UtilVerisenseDriver.millisecondsToString((double) (this.bleNextAlarmTimeMinutesAdaptiveSch * 60000)) + StringUtils.LF);
        } else {
            sb.append("\tNot supported");
        }
        sb.append("********************************************************\nLTF Fail Retry Counter:");
        if (this.payloadContents.length >= 63) {
            sb.append("\tCurrent Operation: " + this.currentOperation + StringUtils.LF);
            sb.append("\tShort Fail Counter: " + ((int) this.failCounterShort) + StringUtils.LF);
            sb.append("\tLong Fail Counter: " + ((int) this.failCounterLong) + StringUtils.LF);
            sb.append("\tNext Alarm Time\t= " + this.bleNextAlarmTimeMinutesFlashWriteRetry + " minutes\n");
            sb.append("\tNext Alarm TIme Will Happen At: " + UtilVerisenseDriver.millisecondsToString((double) (this.bleNextAlarmTimeMinutesFlashWriteRetry * 60000)) + StringUtils.LF);
        } else {
            sb.append("\tNot supported\n");
        }
        sb.append("********************************************************\n");
        return sb.toString();
    }
}
