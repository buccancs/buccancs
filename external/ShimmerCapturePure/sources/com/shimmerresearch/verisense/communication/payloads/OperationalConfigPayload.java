package com.shimmerresearch.verisense.communication.payloads;

import com.google.common.base.Ascii;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.driverUtilities.ExpansionBoardDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.verisense.PendingEventSchedule;
import com.shimmerresearch.verisense.VerisenseDevice;
import com.shimmerresearch.verisense.payloaddesign.AsmBinaryFileConstants;
import com.shimmerresearch.verisense.payloaddesign.PayloadContentsDetails;
import com.shimmerresearch.verisense.sensors.SensorBattVoltageVerisense;
import com.shimmerresearch.verisense.sensors.SensorLIS2DW12;
import com.shimmerresearch.verisense.sensors.SensorLSM6DS3;
import com.shimmerresearch.verisense.sensors.SensorMAX86916;
import com.shimmerresearch.verisense.sensors.SensorMAX86XXX;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class OperationalConfigPayload extends AbstractPayload implements Serializable {
    public static final byte[] DEFAULT_OP_CONFIG_BYTES_FW_1_02_70 = {90, 0, 0, 0, 0, 0, 32, 0, 127, 0, 0, 0, 0, 0, 0, 0, -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, -12, 24, 60, 0, 10, 15, 0, 24, 60, 0, 10, 15, 0, 24, 60, 0, 10, 15, 0, 0, -1, -1, -1, -1, 60, 0, 14, 0, 0, ShimmerObject.GET_EXG_REGS_COMMAND, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, -52, -52, Ascii.RS, 0, 10, 0, 0, 0, 0, 1};

    public static byte[] getDefaultPayloadConfigForFwVersion(ShimmerVerObject shimmerVerObject) {
        if (PayloadContentsDetails.isPayloadDesignV7orAbove(shimmerVerObject)) {
            return (byte[]) DEFAULT_OP_CONFIG_BYTES_FW_1_02_70.clone();
        }
        return null;
    }

    public static int getPayloadConfigSizeForFwVersion(ShimmerVerObject shimmerVerObject) {
        return PayloadContentsDetails.isPayloadDesignV7orAbove(shimmerVerObject) ? 72 : 0;
    }

    public static byte[] generateDefaultOpConfigBytesFw_1_02_070() {
        VerisenseDevice verisenseDevice = new VerisenseDevice();
        ShimmerVerObject shimmerVerObject = VerisenseDevice.FW_CHANGES.CCF21_010_3;
        ExpansionBoardDetails expansionBoardDetails = new ExpansionBoardDetails(64, 1, 0);
        verisenseDevice.setShimmerVersionObject(shimmerVerObject);
        verisenseDevice.setHardwareVersion(expansionBoardDetails.getExpansionBoardId());
        verisenseDevice.setExpansionBoardDetails(expansionBoardDetails);
        verisenseDevice.sensorAndConfigMapsCreate();
        verisenseDevice.disableAllSensors();
        verisenseDevice.setDataCompressionMode(AsmBinaryFileConstants.DATA_COMPRESSION_MODE.NONE);
        verisenseDevice.setBatteryType(VerisenseDevice.BATTERY_TYPE.ZINC_AIR);
        verisenseDevice.setBluetoothEnabled(false);
        verisenseDevice.setUsbEnabled(false);
        verisenseDevice.setPrioritiseLongTermFlash(false);
        verisenseDevice.setDeviceEnabled(false);
        verisenseDevice.setRecordingEnabled(false);
        verisenseDevice.setAdaptiveSchedulerFailCount(255);
        verisenseDevice.setPasskeyMode(VerisenseDevice.PASSKEY_MODE.SECURE);
        verisenseDevice.setRecordingStartTimeMinutes(0L);
        verisenseDevice.setRecordingEndTimeMinutes(0L);
        verisenseDevice.setBleConnectionRetriesPerDay(3);
        verisenseDevice.setBleTxPower(VerisenseDevice.BLE_TX_POWER.MINUS_12_DBM);
        PendingEventSchedule pendingEventSchedule = new PendingEventSchedule(24, 60, 10, 15);
        verisenseDevice.setPendingEventScheduleDataTransfer(pendingEventSchedule);
        verisenseDevice.setPendingEventScheduleRwcSync(pendingEventSchedule);
        verisenseDevice.setPendingEventScheduleStatusSync(pendingEventSchedule);
        verisenseDevice.setAdaptiveSchedulerInterval((int) (Math.pow(2.0d, 16.0d) - 1.0d));
        verisenseDevice.setAdaptiveSchedulerFailCount((int) (Math.pow(2.0d, 8.0d) - 1.0d));
        verisenseDevice.setPpgRecordingDurationSeconds(60);
        verisenseDevice.setPpgRecordingIntervalMinutes(14);
        SensorLIS2DW12 sensorLIS2DW12 = verisenseDevice.getSensorLIS2DW12();
        sensorLIS2DW12.setAccelRange(SensorLIS2DW12.LIS2DW12_ACCEL_RANGE.RANGE_4G);
        sensorLIS2DW12.setAccelRate(SensorLIS2DW12.LIS2DW12_ACCEL_RATE.LOW_POWER_25_0_HZ);
        sensorLIS2DW12.setAccelMode(SensorLIS2DW12.LIS2DW12_MODE.LOW_POWER);
        sensorLIS2DW12.setAccelLpMode(SensorLIS2DW12.LIS2DW12_LP_MODE.LOW_POWER1_12BIT_4_5_MG_NOISE);
        sensorLIS2DW12.setBwFilt(SensorLIS2DW12.LIS2DW12_BW_FILT.ODR_DIVIDED_BY_2);
        sensorLIS2DW12.setFds(SensorLIS2DW12.LIS2DW12_FILTERED_DATA_TYPE_SELECTION.LOW_PASS_FILTER_PATH_SELECTED);
        sensorLIS2DW12.setLowNoise(SensorLIS2DW12.LIS2DW12_LOW_NOISE.DISABLED);
        sensorLIS2DW12.setHpFilterMode(SensorLIS2DW12.LIS2DW12_HP_REF_MODE.DISABLED);
        sensorLIS2DW12.setFifoMode(SensorLIS2DW12.LIS2DW12_FIFO_MODE.CONTINUOUS_TO_FIFO_MODE);
        sensorLIS2DW12.setFifoThreshold(SensorLIS2DW12.LIS2DW12_FIFO_THRESHOLD.SAMPLE_31);
        SensorLSM6DS3 sensorLSM6DS3 = verisenseDevice.getSensorLSM6DS3();
        sensorLSM6DS3.setGyroRange(SensorLSM6DS3.LSM6DS3_GYRO_RANGE.RANGE_500DPS);
        sensorLSM6DS3.setAccelRange(SensorLSM6DS3.LSM6DS3_ACCEL_RANGE.RANGE_4G);
        sensorLSM6DS3.setRate(SensorLSM6DS3.LSM6DS3_RATE.RATE_52_HZ);
        sensorLSM6DS3.setTimerPedoFifodEnable(false);
        sensorLSM6DS3.setTimerPedoFifodDrdy(false);
        sensorLSM6DS3.setDecimationFifoAccel(SensorLSM6DS3.FIFO_DECIMATION_ACCEL.NO_DECIMATION);
        sensorLSM6DS3.setDecimationFifoGyro(SensorLSM6DS3.FIFO_DECIMATION_GYRO.NO_DECIMATION);
        sensorLSM6DS3.setFifoMode(SensorLSM6DS3.FIFO_MODE.CONTINUOUS_MODE);
        sensorLSM6DS3.setAccelAntiAliasingBandwidthFilter(SensorLSM6DS3.ACCEL_ANTI_ALIASING_BANDWIDTH_FILTER.AT_400HZ);
        sensorLSM6DS3.setGyroFullScaleAt12dps(false);
        sensorLSM6DS3.setGyroHighPerformanceMode(false);
        sensorLSM6DS3.setGyroDigitalHighPassFilterEnable(false);
        sensorLSM6DS3.setGyroHighPassFilterCutOffFreq(SensorLSM6DS3.HIGH_PASS_FILTER_CUT_OFF_FREQ_GYRO.AT_0_0081_HZ);
        sensorLSM6DS3.setGyroDigitalHighPassFilterReset(false);
        sensorLSM6DS3.setRoundingStatus(false);
        sensorLSM6DS3.setAccelLowPassFilterLpf2Selection(false);
        sensorLSM6DS3.setAccelHighPassFilterCutOffFreq(SensorLSM6DS3.HIGH_PASS_FILTER_CUT_OFF_FREQ_ACCEL.SLOPE);
        sensorLSM6DS3.setAccelHighPassOrSlopeFilterSelectionEnable(false);
        sensorLSM6DS3.setLowPassOn6D(false);
        SensorMAX86916 sensorMax86916 = verisenseDevice.getSensorMax86916();
        sensorMax86916.setPpgAdcResolution(SensorMAX86XXX.MAX86XXX_ADC_RESOLUTION.RESOLUTION_15_BIT);
        sensorMax86916.setPpgPulseWidth(SensorMAX86XXX.MAX86XXX_PULSE_WIDTH.PW_400_US);
        sensorMax86916.setPpgSampleAverage(SensorMAX86XXX.MAX86XXX_SAMPLE_AVG.NO_AVERAGING);
        sensorMax86916.setSampleRate(SensorMAX86916.MAX86916_SAMPLE_RATE.SR_50_0_HZ);
        sensorMax86916.setPpgDefaultCurrentAllLedsMilliamps(40);
        sensorMax86916.setPpgMaxCurrentGreenBlueLedsMilliamps(204);
        sensorMax86916.setPpgMaxCurrentRedIrLedsMilliamps(204);
        sensorMax86916.setPpgAutoGainControlTargetPercentOfRange(30);
        sensorMax86916.setPpgProximityDetectionCurrentIrLedMilliamps(10);
        sensorMax86916.setPpgDac1CrossTalk(0);
        sensorMax86916.setPpgDac2CrossTalk(0);
        sensorMax86916.setPpgDac3CrossTalk(0);
        sensorMax86916.setPpgDac4CrossTalk(0);
        sensorMax86916.setProximityDetectionMode(SensorMAX86916.PROX_DETECTION_MODE.AUTO_GAIN_ON_PROX_DETECTION_ON_DRIVER);
        verisenseDevice.getSensorBatteryVoltage().setSensorSamplingRate(SensorBattVoltageVerisense.ADC_SAMPLING_RATES.OFF);
        return verisenseDevice.configBytesGenerate(false, Configuration.COMMUNICATION_TYPE.BLUETOOTH);
    }

    public static void main(String[] strArr) {
        testByteArrays(DEFAULT_OP_CONFIG_BYTES_FW_1_02_70, generateDefaultOpConfigBytesFw_1_02_070());
    }

    private static void testByteArrays(byte[] bArr, byte[] bArr2) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < bArr.length; i++) {
            System.out.println(i + ", " + UtilShimmer.byteToHexString(bArr[i]) + ", " + UtilShimmer.byteToHexString(bArr2[i]));
            if (bArr[i] != bArr2[i]) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        if (arrayList.size() > 0) {
            System.out.println("byte comparison failed at indexes " + arrayList);
        }
    }

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public String generateDebugString() {
        return "";
    }

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public byte[] generatePayloadContents() {
        return null;
    }

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public boolean parsePayloadContents(byte[] bArr) {
        this.payloadContents = bArr;
        this.isSuccess = bArr[0] == 90;
        return this.isSuccess;
    }

    public class OP_CONFIG_BYTE_INDEX {
        public static final int ACCEL1_CFG_0 = 5;
        public static final int ACCEL1_CFG_1 = 6;
        public static final int ACCEL1_CFG_2 = 7;
        public static final int ACCEL1_CFG_3 = 8;
        public static final int ADAPTIVE_SCHEDULER_FAILCOUNT_MAX = 54;
        public static final int ADAPTIVE_SCHEDULER_INT_LSB = 52;
        public static final int ADAPTIVE_SCHEDULER_INT_MSB = 53;
        public static final int ADC_SAMPLE_RATE = 50;
        public static final int BLE_DATA_TRANS_RETRY_INT_LSB = 36;
        public static final int BLE_DATA_TRANS_RETRY_INT_MSB = 37;
        public static final int BLE_DATA_TRANS_WKUP_DUR = 35;
        public static final int BLE_DATA_TRANS_WKUP_INT_HRS = 32;
        public static final int BLE_DATA_TRANS_WKUP_TIME_LSB = 33;
        public static final int BLE_DATA_TRANS_WKUP_TIME_MSB = 34;
        public static final int BLE_RETRY_COUNT = 30;
        public static final int BLE_RTC_SYNC_RETRY_INT_LSB = 48;
        public static final int BLE_RTC_SYNC_RETRY_INT_MSB = 49;
        public static final int BLE_RTC_SYNC_WKUP_DUR = 47;
        public static final int BLE_RTC_SYNC_WKUP_INT_HRS = 44;
        public static final int BLE_RTC_SYNC_WKUP_TIME_LSB = 45;
        public static final int BLE_RTC_SYNC_WKUP_TIME_MSB = 46;
        public static final int BLE_STATUS_RETRY_INT_LSB = 42;
        public static final int BLE_STATUS_RETRY_INT_MSB = 43;
        public static final int BLE_STATUS_WKUP_DUR = 41;
        public static final int BLE_STATUS_WKUP_INT_HRS = 38;
        public static final int BLE_STATUS_WKUP_TIME_LSB = 39;
        public static final int BLE_STATUS_WKUP_TIME_MSB = 40;
        public static final int BLE_TX_POWER = 31;
        public static final int END_TIME_BYTE_0 = 25;
        public static final int END_TIME_BYTE_1 = 26;
        public static final int END_TIME_BYTE_2 = 27;
        public static final int END_TIME_BYTE_3 = 28;
        public static final int GEN_CFG_0 = 1;
        public static final int GEN_CFG_1 = 2;
        public static final int GEN_CFG_2 = 3;
        public static final int GSR_RANGE_SETTING = 51;
        public static final int GYRO_ACCEL2_CFG_0 = 10;
        public static final int GYRO_ACCEL2_CFG_1 = 11;
        public static final int GYRO_ACCEL2_CFG_2 = 12;
        public static final int GYRO_ACCEL2_CFG_3 = 13;
        public static final int GYRO_ACCEL2_CFG_4 = 14;
        public static final int GYRO_ACCEL2_CFG_5 = 15;
        public static final int GYRO_ACCEL2_CFG_6 = 16;
        public static final int GYRO_ACCEL2_CFG_7 = 17;
        public static final int HEADER_BYTE = 0;
        public static final int PPG_AGC_TARGET_PERCENT_OF_RANGE = 64;
        public static final int PPG_DAC1_CROSSTALK = 67;
        public static final int PPG_DAC2_CROSSTALK = 68;
        public static final int PPG_DAC3_CROSSTALK = 69;
        public static final int PPG_DAC4_CROSSTALK = 70;
        public static final int PPG_FIFO_CONFIG = 59;
        public static final int PPG_MA_DEFAULT = 61;
        public static final int PPG_MA_LED_PILOT = 66;
        public static final int PPG_MA_MAX_GREEN_BLUE = 63;
        public static final int PPG_MA_MAX_RED_IR = 62;
        public static final int PPG_MODE_CONFIG2 = 60;
        public static final int PPG_REC_DUR_SECS_LSB = 55;
        public static final int PPG_REC_DUR_SECS_MSB = 56;
        public static final int PPG_REC_INT_MINS_LSB = 57;
        public static final int PPG_REC_INT_MINS_MSB = 58;
        public static final int PROX_AGC_MODE = 71;
        public static final int START_TIME_BYTE_0 = 21;
        public static final int START_TIME_BYTE_1 = 22;
        public static final int START_TIME_BYTE_2 = 23;
        public static final int START_TIME_BYTE_3 = 24;
        public static final int UNUSED_BYTE_18 = 18;
        public static final int UNUSED_BYTE_19 = 19;
        public static final int UNUSED_BYTE_20 = 20;
        public static final int UNUSED_BYTE_29 = 29;
        public static final int UNUSED_BYTE_4 = 4;
        public static final int UNUSED_BYTE_65 = 65;
        public static final int UNUSED_BYTE_9 = 9;

        public OP_CONFIG_BYTE_INDEX() {
        }
    }

    public class OP_CONFIG_BIT_MASK {
        public static final int BATTERY_TYPE = 1;
        public static final int BLUETOOTH_ENABLED = 16;
        public static final int DATA_COMPRESSION_MODE = 3;
        public static final int DEVICE_ENABLED = 2;
        public static final int ENABLED_SENSORS_GEN_CFG_0 = 224;
        public static final int ENABLED_SENSORS_GEN_CFG_1 = 252;
        public static final int ENABLED_SENSORS_GEN_CFG_2 = 2;
        public static final int PASSKEY_MODE = 3;
        public static final int PRIORITISE_LONG_TERM_FLASH_STORAGE = 4;
        public static final int RECORDING_ENABLED = 1;
        public static final int USB_ENABLED = 8;

        public OP_CONFIG_BIT_MASK() {
        }
    }

    public class OP_CONFIG_BIT_SHIFT {
        public static final int BATTERY_TYPE = 0;
        public static final int PASSKEY_MODE = 2;

        public OP_CONFIG_BIT_SHIFT() {
        }
    }
}
