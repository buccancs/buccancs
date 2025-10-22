package com.shimmerresearch.verisense.communication.payloads;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.verisense.UtilVerisenseDriver;
import com.shimmerresearch.verisense.payloaddesign.VerisenseTimeDetails;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class EventLogPayload extends AbstractPayload {
    public final String NO_EVENTS_LOGGED_TXT = "No events logged.";
    List<EventLogEntry> listOfEventLogEntries = new ArrayList();

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public byte[] generatePayloadContents() {
        return null;
    }

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public boolean parsePayloadContents(byte[] bArr) {
        this.payloadContents = bArr;
        this.isSuccess = false;
        this.listOfEventLogEntries.clear();
        if (bArr.length > 0) {
            for (int i = 0; i < bArr.length; i += 8) {
                int i2 = bArr[i + 7] & 255;
                if (i2 != LOG_EVENT.NONE.ordinal()) {
                    if (i2 == LOG_EVENT.BATTERY_VOLTAGE.ordinal()) {
                        this.listOfEventLogEntries.add(new EventLogEntry(i2, parseByteArrayAtIndex(bArr, i, ChannelDetails.CHANNEL_DATA_TYPE.UINT24)));
                    } else {
                        this.listOfEventLogEntries.add(new EventLogEntry(i2, VerisenseTimeDetails.parseTimeMsFromMinutesAndTicksAtIndex(bArr, i)));
                    }
                }
            }
        }
        this.isSuccess = true;
        return false;
    }

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public String generateDebugString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Received Response, length= " + this.payloadContents.length + " bytes:\n");
        sb.append(generateDebugStringNoHeader());
        return sb.toString();
    }

    public String generateDebugStringNoHeader() {
        StringBuilder sb = new StringBuilder();
        if (this.listOfEventLogEntries.size() == 0) {
            sb.append("No events logged.\n");
        } else {
            for (int i = 0; i < this.listOfEventLogEntries.size(); i++) {
                EventLogEntry eventLogEntry = this.listOfEventLogEntries.get(i);
                sb.append("Index = " + i + ", ");
                if (eventLogEntry.event == LOG_EVENT.BATTERY_VOLTAGE.ordinal()) {
                    sb.append("Battery Level = " + eventLogEntry.batteryVoltage + Configuration.CHANNEL_UNITS.MILLIVOLTS);
                } else {
                    sb.append("Time = " + eventLogEntry.getTimeString());
                    sb.append(", Event = " + eventLogEntry.getLogEventStr());
                }
                sb.append(StringUtils.LF);
            }
        }
        return sb.toString();
    }

    public enum LOG_EVENT {
        NONE,
        BATTERY_FALL,
        BATTERY_RECOVER,
        WRITE_TO_FLASH_SUCCESS,
        WRITE_TO_FLASH_FAIL_GENERAL,
        WRITE_TO_FLASH_FULL,
        WRITE_TO_FLASH_FAIL_CHECK_ADDR_FREE,
        WRITE_TO_FLASH_FAIL_LOW_BATT_CHECK_ADDR_FREE,
        WRITE_TO_FLASH_FAIL_LOW_BATT_FLASH_ON,
        WRITE_TO_FLASH_FAIL_LOW_BATT_FLASH_WRITE,
        WRITE_TO_FLASH_FAIL_LOW_BATT_BEFORE_START,
        USB_PLUGGED_IN,
        USB_PLUGGED_OUT,
        RECORDING_PAUSED,
        RECORDING_RESUMED,
        BATTERY_RECOVER_IN_BATT_CHECK_TIMER,
        TSK_FREE_UP_FLASH,
        FREE_UP_FLASH_FAIL_LOW_BATT,
        PAYLOAD_PACKAGING_TASK_SET,
        PAYLOAD_PACKAGING_FUNCTION_CALL,
        BATTERY_VOLTAGE,
        TSK_WRITE_LOOKUP_TBL_CHANGES_TO_EEPROM,
        LPCOMP_ON,
        LPCOMP_ON_ALREADY,
        LPCOMP_OFF,
        LPCOMP_TRIED_BUT_BATT_LOW,
        BLE_CONNECTED,
        BLE_DISCONNECTED,
        TSK_WRITE_FLASH,
        PPG_TIMER_START,
        PAYLOAD_OVERSHOT,
        ADVERTISING_START,
        ADVERTISING_STOP,
        NIMH_BATT_PPG_BLOCKED_BLE_RETRY,
        NIMH_BATT_PPG_BLOCKED_BLE_ADAPT_SCH,
        NIMH_BATT_PPG_BLOCKED_BLE_PENDING_EVENTS,
        NIMH_BATT_BLE_BLOCKED_PPG,
        USB_PORT_OPEN,
        USB_PORT_CLOSED,
        FIFO_INT_SAFETY_CHECK_EVENT_ACCEL1,
        FIFO_INT_SAFETY_CHECK_EVENT_ACCEL2GYRO,
        FIFO_INT_SAFETY_CHECK_EVENT_MAX86XXX,
        FIFO_INT_SAFETY_CHECK_EVENT_MAX3000X,
        FIFO_INT_SAFETY_CHECK_EVENT_ADC
    }

    public class EventLogEntry {
        public long batteryVoltage;
        public int event;
        public double timeMs;

        public EventLogEntry(int i, double d) {
            this.event = i;
            this.timeMs = d;
        }

        public EventLogEntry(int i, long j) {
            this.event = i;
            this.batteryVoltage = j;
        }

        public String getLogEventStr() {
            for (LOG_EVENT log_event : LOG_EVENT.values()) {
                if (log_event.ordinal() == this.event) {
                    return log_event.toString();
                }
            }
            return Integer.toString(this.event);
        }

        public String getTimeString() {
            return UtilVerisenseDriver.millisecondsToStringWitNanos(this.timeMs);
        }
    }
}
