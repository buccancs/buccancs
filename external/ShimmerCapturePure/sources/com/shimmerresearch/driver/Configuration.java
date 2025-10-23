package com.shimmerresearch.driver;

import com.shimmerresearch.algorithms.orientation.OrientationModule6DOF;
import com.shimmerresearch.algorithms.orientation.OrientationModule9DOF;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.sensors.SensorADC;
import com.shimmerresearch.sensors.SensorBattVoltage;
import com.shimmerresearch.sensors.SensorBridgeAmp;
import com.shimmerresearch.sensors.SensorECGToHRFw;
import com.shimmerresearch.sensors.SensorEXG;
import com.shimmerresearch.sensors.SensorGSR;
import com.shimmerresearch.sensors.SensorPPG;
import com.shimmerresearch.sensors.SensorShimmerClock;
import com.shimmerresearch.sensors.SensorSystemTimeStamp;
import com.shimmerresearch.sensors.ShimmerStreamingProperties;
import com.shimmerresearch.sensors.adxl371.SensorADXL371;
import com.shimmerresearch.sensors.bmpX80.SensorBMP180;
import com.shimmerresearch.sensors.kionix.SensorKionixAccel;
import com.shimmerresearch.sensors.lisxmdl.SensorLIS3MDL;
import com.shimmerresearch.sensors.lsm303.SensorLSM303;
import com.shimmerresearch.sensors.lsm303.SensorLSM303DLHC;
import com.shimmerresearch.sensors.mpu9x50.SensorMPU9X50;
import com.shimmerresearch.verisense.sensors.SensorLSM6DS3;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2CodecUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public class Configuration {
    public static final double ACCELERATION_DUE_TO_GRAVITY = 9.81d;

    @Deprecated
    public static void setTooLegacyObjectClusterSensorNames() {
        Shimmer3.ObjectClusterSensorName.TIMESTAMP = "Timestamp";
        Shimmer3.ObjectClusterSensorName.REAL_TIME_CLOCK = SensorShimmerClock.ObjectClusterSensorName.REAL_TIME_CLOCK;
        Shimmer3.ObjectClusterSensorName.ACCEL_LN_X = "Low Noise Accelerometer X";
        Shimmer3.ObjectClusterSensorName.ACCEL_LN_Y = "Low Noise Accelerometer Y";
        Shimmer3.ObjectClusterSensorName.ACCEL_LN_Z = "Low Noise Accelerometer Z";
        Shimmer3.ObjectClusterSensorName.BATTERY = "VSenseBatt";
        Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A7 = "ExpBoard A7";
        Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A6 = "ExpBoard A6";
        Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A15 = "External ADC A15";
        Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A12 = "Internal ADC A12";
        Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A13 = "Internal ADC A13";
        Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A14 = "Internal ADC A14";
        Shimmer3.ObjectClusterSensorName.BRIDGE_AMP_HIGH = "Bridge Amplifier High";
        Shimmer3.ObjectClusterSensorName.BRIDGE_AMP_LOW = "Bridge Amplifier Low";
        Shimmer3.ObjectClusterSensorName.GSR_RESISTANCE = "GSR";
        Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A1 = "Internal ADC A1";
        Shimmer3.ObjectClusterSensorName.RESISTANCE_AMP = "Resistance Amp";
        Shimmer3.ObjectClusterSensorName.GYRO_X = "Gyroscope X";
        Shimmer3.ObjectClusterSensorName.GYRO_Y = "Gyroscope Y";
        Shimmer3.ObjectClusterSensorName.GYRO_Z = "Gyroscope Z";
        Shimmer3.ObjectClusterSensorName.ACCEL_WR_X = "Wide Range Accelerometer X";
        Shimmer3.ObjectClusterSensorName.ACCEL_WR_Y = "Wide Range Accelerometer Y";
        Shimmer3.ObjectClusterSensorName.ACCEL_WR_Z = "Wide Range Accelerometer Z";
        Shimmer3.ObjectClusterSensorName.MAG_X = "Magnetometer X";
        Shimmer3.ObjectClusterSensorName.MAG_Y = "Magnetometer Y";
        Shimmer3.ObjectClusterSensorName.MAG_Z = "Magnetometer Z";
        Shimmer3.ObjectClusterSensorName.ACCEL_MPU_X = "Accel_MPU_X";
        Shimmer3.ObjectClusterSensorName.ACCEL_MPU_Y = "Accel_MPU_Y";
        Shimmer3.ObjectClusterSensorName.ACCEL_MPU_Z = "Accel_MPU_Z";
        Shimmer3.ObjectClusterSensorName.MAG_MPU_X = "Mag_MPU_X";
        Shimmer3.ObjectClusterSensorName.MAG_MPU_Y = "Mag_MPU_Y";
        Shimmer3.ObjectClusterSensorName.MAG_MPU_Z = "Mag_MPU_Z";
        Shimmer3.ObjectClusterSensorName.TEMPERATURE_BMP180 = "Temperature";
        Shimmer3.ObjectClusterSensorName.PRESSURE_BMP180 = "Pressure";
        Shimmer3.ObjectClusterSensorName.EMG_CH1_24BIT = "EMG CH1";
        Shimmer3.ObjectClusterSensorName.EMG_CH2_24BIT = "EMG CH2";
        Shimmer3.ObjectClusterSensorName.EMG_CH1_16BIT = "EMG CH1";
        Shimmer3.ObjectClusterSensorName.EMG_CH2_16BIT = "EMG CH2";
        Shimmer3.ObjectClusterSensorName.ECG_LL_RA_24BIT = "ECG LL-RA";
        Shimmer3.ObjectClusterSensorName.ECG_LA_RA_24BIT = "ECG LA-RA";
        Shimmer3.ObjectClusterSensorName.ECG_LL_LA_24BIT = "ECG LL-LA";
        Shimmer3.ObjectClusterSensorName.ECG_LL_LA_16BIT = "ECG LL-LA";
        Shimmer3.ObjectClusterSensorName.ECG_LL_RA_16BIT = "ECG LL-RA";
        Shimmer3.ObjectClusterSensorName.ECG_LA_RA_16BIT = "ECG LA-RA";
        Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_24BIT = "EXG1 CH1";
        Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_24BIT = "EXG1 CH1";
        Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_24BIT = "EXG1 CH2";
        Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_24BIT = "EXG1 CH2";
        Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_16BIT = "EXG1 CH1 16BIT";
        Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_16BIT = "EXG1 CH1 16BIT";
        Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_16BIT = "EXG1 CH2 16BIT";
        Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_16BIT = "EXG1 CH2 16BIT";
        Shimmer3.ObjectClusterSensorName.EXG1_STATUS = "EXG1 Status";
        Shimmer3.ObjectClusterSensorName.ECG_RESP_24BIT = "ECG RESP";
        Shimmer3.ObjectClusterSensorName.ECG_VX_RL_24BIT = "ECG Vx-RL";
        Shimmer3.ObjectClusterSensorName.ECG_RESP_16BIT = "ECG RESP";
        Shimmer3.ObjectClusterSensorName.ECG_VX_RL_16BIT = "ECG Vx-RL";
        Shimmer3.ObjectClusterSensorName.EXG1_CH1_24BIT = "ExG1 CH1";
        Shimmer3.ObjectClusterSensorName.EXG1_CH2_24BIT = "ExG1 CH2";
        Shimmer3.ObjectClusterSensorName.EXG1_CH1_16BIT = "ExG1 CH1 16Bit";
        Shimmer3.ObjectClusterSensorName.EXG1_CH2_16BIT = "ExG1 CH2 16Bit";
        Shimmer3.ObjectClusterSensorName.EXG2_CH1_24BIT = "ExG2 CH1";
        Shimmer3.ObjectClusterSensorName.EXG2_CH2_24BIT = "ExG2 CH2";
        Shimmer3.ObjectClusterSensorName.EXG2_CH1_16BIT = "ExG2 CH1 16Bit";
        Shimmer3.ObjectClusterSensorName.EXG2_CH2_16BIT = "ExG2 CH2 16Bit";
        Shimmer3.ObjectClusterSensorName.EXG2_STATUS = "EXG2 Status";
        Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_W = "Quat_MPL_6DOF_W";
        Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_X = "Quat_MPL_6DOF_X";
        Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_Y = "Quat_MPL_6DOF_Y";
        Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_Z = "Quat_MPL_6DOF_Z";
        Shimmer3.ObjectClusterSensorName.QUAT_MPL_9DOF_W = "Quat_MPL_9DOF_W";
        Shimmer3.ObjectClusterSensorName.QUAT_MPL_9DOF_X = "Quat_MPL_9DOF_X";
        Shimmer3.ObjectClusterSensorName.QUAT_MPL_9DOF_Y = "Quat_MPL_9DOF_Y";
        Shimmer3.ObjectClusterSensorName.QUAT_MPL_9DOF_Z = "Quat_MPL_9DOF_Z";
        Shimmer3.ObjectClusterSensorName.EULER_MPL_6DOF_X = "Euler_MPL_6DOF_X";
        Shimmer3.ObjectClusterSensorName.EULER_MPL_6DOF_Y = "Euler_MPL_6DOF_Y";
        Shimmer3.ObjectClusterSensorName.EULER_MPL_6DOF_Z = "Euler_MPL_6DOF_Z";
        Shimmer3.ObjectClusterSensorName.EULER_MPL_9DOF_X = "Euler_MPL_9DOF_X";
        Shimmer3.ObjectClusterSensorName.EULER_MPL_9DOF_Y = "Euler_MPL_9DOF_Y";
        Shimmer3.ObjectClusterSensorName.EULER_MPL_9DOF_Z = "Euler_MPL_9DOF_Z";
        Shimmer3.ObjectClusterSensorName.MPL_HEADING = "MPL_heading";
        Shimmer3.ObjectClusterSensorName.MPL_TEMPERATURE = "MPL_Temperature";
        Shimmer3.ObjectClusterSensorName.MPL_PEDOM_CNT = "MPL_Pedom_cnt";
        Shimmer3.ObjectClusterSensorName.MPL_PEDOM_TIME = "MPL_Pedom_Time";
        Shimmer3.ObjectClusterSensorName.TAPDIRANDTAPCNT = "TapDirAndTapCnt";
        Shimmer3.ObjectClusterSensorName.MOTIONANDORIENT = "MotionAndOrient";
        Shimmer3.ObjectClusterSensorName.GYRO_MPU_MPL_X = "Gyro_MPU_MPL_X";
        Shimmer3.ObjectClusterSensorName.GYRO_MPU_MPL_Y = "Gyro_MPU_MPL_Y";
        Shimmer3.ObjectClusterSensorName.GYRO_MPU_MPL_Z = "Gyro_MPU_MPL_Z";
        Shimmer3.ObjectClusterSensorName.ACCEL_MPU_MPL_X = "Accel_MPU_MPL_X";
        Shimmer3.ObjectClusterSensorName.ACCEL_MPU_MPL_Y = "Accel_MPU_MPL_Y";
        Shimmer3.ObjectClusterSensorName.ACCEL_MPU_MPL_Z = "Accel_MPU_MPL_Z";
        Shimmer3.ObjectClusterSensorName.MAG_MPU_MPL_X = "Mag_MPU_MPL_X";
        Shimmer3.ObjectClusterSensorName.MAG_MPU_MPL_Y = "Mag_MPU_MPL_Y";
        Shimmer3.ObjectClusterSensorName.MAG_MPU_MPL_Z = "Mag_MPU_MPL_Z";
        Shimmer3.ObjectClusterSensorName.QUAT_DMP_6DOF_W = "Quat_DMP_6DOF_W";
        Shimmer3.ObjectClusterSensorName.QUAT_DMP_6DOF_X = "Quat_DMP_6DOF_X";
        Shimmer3.ObjectClusterSensorName.QUAT_DMP_6DOF_Y = "Quat_DMP_6DOF_Y";
        Shimmer3.ObjectClusterSensorName.QUAT_DMP_6DOF_Z = "Quat_DMP_6DOF_Z";
        Shimmer3.ObjectClusterSensorName.ECG_TO_HR_FW = "ECGtoHR";
        Shimmer3.ObjectClusterSensorName.PPG_TO_HR = "PPGtoHR";
        Shimmer3.ObjectClusterSensorName.QUAT_MADGE_6DOF_W = "Quaternion 0";
        Shimmer3.ObjectClusterSensorName.QUAT_MADGE_6DOF_X = "Quaternion 1";
        Shimmer3.ObjectClusterSensorName.QUAT_MADGE_6DOF_Y = "Quaternion 2";
        Shimmer3.ObjectClusterSensorName.QUAT_MADGE_6DOF_Z = "Quaternion 3";
        Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_W = "Quaternion 0";
        Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_X = "Quaternion 1";
        Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_Y = "Quaternion 2";
        Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_Z = "Quaternion 3";
        Shimmer3.ObjectClusterSensorName.EULER_6DOF_YAW = OrientationModule6DOF.ObjectClusterSensorName.EULER_6DOF_YAW;
        Shimmer3.ObjectClusterSensorName.EULER_6DOF_PITCH = OrientationModule6DOF.ObjectClusterSensorName.EULER_6DOF_PITCH;
        Shimmer3.ObjectClusterSensorName.EULER_6DOF_ROLL = OrientationModule6DOF.ObjectClusterSensorName.EULER_6DOF_ROLL;
        Shimmer3.ObjectClusterSensorName.EULER_9DOF_YAW = OrientationModule9DOF.ObjectClusterSensorName.EULER_9DOF_YAW;
        Shimmer3.ObjectClusterSensorName.EULER_9DOF_PITCH = OrientationModule9DOF.ObjectClusterSensorName.EULER_9DOF_PITCH;
        Shimmer3.ObjectClusterSensorName.EULER_9DOF_ROLL = OrientationModule9DOF.ObjectClusterSensorName.EULER_9DOF_ROLL;
        Shimmer3.ObjectClusterSensorName.AXIS_ANGLE_9DOF_A = "Axis Angle A";
        Shimmer3.ObjectClusterSensorName.AXIS_ANGLE_9DOF_X = "Axis Angle X";
        Shimmer3.ObjectClusterSensorName.AXIS_ANGLE_9DOF_Y = "Axis Angle Y";
        Shimmer3.ObjectClusterSensorName.AXIS_ANGLE_9DOF_Z = "Axis Angle Z";
        SensorPPG.ObjectClusterSensorName.PPG_A12 = "PPG_A12";
        SensorPPG.ObjectClusterSensorName.PPG_A13 = "PPG_A13";
        SensorPPG.ObjectClusterSensorName.PPG1_A12 = "PPG1_A12";
        SensorPPG.ObjectClusterSensorName.PPG1_A13 = "PPG1_A13";
        SensorPPG.ObjectClusterSensorName.PPG2_A1 = "PPG2_A1";
        SensorPPG.ObjectClusterSensorName.PPG2_A14 = "PPG2_A14";
        Shimmer2.ObjectClusterSensorName.TIMESTAMP = "Timestamp";
        Shimmer2.ObjectClusterSensorName.REAL_TIME_CLOCK = SensorShimmerClock.ObjectClusterSensorName.REAL_TIME_CLOCK;
        Shimmer2.ObjectClusterSensorName.ACCEL_X = "Accelerometer X";
        Shimmer2.ObjectClusterSensorName.ACCEL_Y = "Accelerometer Y";
        Shimmer2.ObjectClusterSensorName.ACCEL_Z = "Accelerometer Z";
        Shimmer2.ObjectClusterSensorName.BATTERY = "VSenseBatt";
        Shimmer2.ObjectClusterSensorName.VOLT_REG = "VSenseReg";
        Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_HIGH = "Bridge Amplifier High";
        Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_LOW = "Bridge Amplifier Low";
        Shimmer2.ObjectClusterSensorName.GSR = "GSR";
        Shimmer2.ObjectClusterSensorName.GSR_RES = "GSR Res";
        Shimmer2.ObjectClusterSensorName.EXP_BOARD_A0 = "ExpBoard A0";
        Shimmer2.ObjectClusterSensorName.EXP_BOARD_A7 = "ExpBoard A7";
        Shimmer2.ObjectClusterSensorName.GYRO_X = "Gyroscope X";
        Shimmer2.ObjectClusterSensorName.GYRO_Y = "Gyroscope Y";
        Shimmer2.ObjectClusterSensorName.GYRO_Z = "Gyroscope Z";
        Shimmer2.ObjectClusterSensorName.MAG_X = "Magnetometer X";
        Shimmer2.ObjectClusterSensorName.MAG_Y = "Magnetometer Y";
        Shimmer2.ObjectClusterSensorName.MAG_Z = "Magnetometer Z";
        Shimmer2.ObjectClusterSensorName.EMG = "EMG";
        Shimmer2.ObjectClusterSensorName.ECG_RA_LL = "ECG RA-LL";
        Shimmer2.ObjectClusterSensorName.ECG_LA_LL = "ECG LA-LL";
        Shimmer2.ObjectClusterSensorName.ECG_TO_HR = "ECGtoHR";
        Shimmer2.ObjectClusterSensorName.QUAT_MADGE_6DOF_W = "Quaternion 0";
        Shimmer2.ObjectClusterSensorName.QUAT_MADGE_6DOF_X = "Quaternion 1";
        Shimmer2.ObjectClusterSensorName.QUAT_MADGE_6DOF_Y = "Quaternion 2";
        Shimmer2.ObjectClusterSensorName.QUAT_MADGE_6DOF_Z = "Quaternion 3";
        Shimmer2.ObjectClusterSensorName.QUAT_MADGE_9DOF_W = "Quaternion 0";
        Shimmer2.ObjectClusterSensorName.QUAT_MADGE_9DOF_X = "Quaternion 1";
        Shimmer2.ObjectClusterSensorName.QUAT_MADGE_9DOF_Y = "Quaternion 2";
        Shimmer2.ObjectClusterSensorName.QUAT_MADGE_9DOF_Z = "Quaternion 3";
        Shimmer2.ObjectClusterSensorName.EULER_6DOF_YAW = "Euler_6DOF_YAW";
        Shimmer2.ObjectClusterSensorName.EULER_6DOF_PITCH = "Euler_6DOF_PITCH";
        Shimmer2.ObjectClusterSensorName.EULER_6DOF_ROLL = OrientationModule6DOF.ObjectClusterSensorName.EULER_6DOF_ROLL;
        Shimmer2.ObjectClusterSensorName.EULER_9DOF_YAW = "Euler_9DOF_YAW";
        Shimmer2.ObjectClusterSensorName.EULER_9DOF_PITCH = "Euler_9DOF_PITCH";
        Shimmer2.ObjectClusterSensorName.EULER_9DOF_ROLL = OrientationModule9DOF.ObjectClusterSensorName.EULER_9DOF_ROLL;
        Shimmer2.ObjectClusterSensorName.HEART_RATE = "Heart_Rate";
        Shimmer2.ObjectClusterSensorName.AXIS_ANGLE_6DOF_A = "Axis Angle A 6DOF";
        Shimmer2.ObjectClusterSensorName.AXIS_ANGLE_6DOF_X = "Axis Angle X 6DOF";
        Shimmer2.ObjectClusterSensorName.AXIS_ANGLE_6DOF_Y = "Axis Angle Y 6DOF";
        Shimmer2.ObjectClusterSensorName.AXIS_ANGLE_6DOF_Z = "Axis Angle Z 6DOF";
        Shimmer2.ObjectClusterSensorName.AXIS_ANGLE_9DOF_A = "Axis Angle A 9DOF";
        Shimmer2.ObjectClusterSensorName.AXIS_ANGLE_9DOF_X = "Axis Angle X 9DOF";
        Shimmer2.ObjectClusterSensorName.AXIS_ANGLE_9DOF_Y = "Axis Angle Y 9DOF";
        Shimmer2.ObjectClusterSensorName.AXIS_ANGLE_9DOF_Z = "Axis Angle Z 9DOF";
    }

    public enum COMMUNICATION_ACTION {
        READ,
        WRITE
    }

    public enum COMMUNICATION_TYPE {
        ALL,
        DOCK,
        BLUETOOTH,
        IEEE802154,
        SD,
        HID,
        USB,
        CLASS
    }

    public static class CHANNEL_UNITS {
        public static final String ACCEL_CAL_UNIT = "m/(s^2)";
        public static final String ASCII_CODE = "ASCII";
        public static final String BEATS_PER_MINUTE = "BPM";
        public static final String CLOCK_UNIT = "Ticks";
        public static final String DATE_FORMAT = "yyyy/mm/dd hh:mm:ss.000";
        public static final String DEGREES = "Degrees";
        public static final String DEGREES_CELSIUS = "Degrees Celsius";
        public static final String DEGREES_CELSIUS_SHORT = "°C";
        public static final String DEGREES_PER_SECOND = "deg/s";
        public static final String FREQUENCY = "Hz";
        public static final String GRAVITY = "g";
        public static final String GYRO_CAL_UNIT = "deg/s";
        public static final String KOHMS = "kOhms";
        public static final String KOHMS_PER_SECOND = "kOhms/s";
        public static final String KOHMS_SECONDS = "kOhm.s";
        public static final String KPASCAL = "kPa";
        public static final String LOCAL = "local";
        public static final String LOCAL_FLUX = "local_flux";
        public static final String MAG_CAL_UNIT = "local_flux";
        public static final String METER = "m";
        public static final String METER_PER_SECOND = "m/s";
        public static final String METER_PER_SECOND_SQUARE = "m/(s^2)";
        public static final String METER_SQUARE = "m^2";
        public static final String MICROSECONDS = "us";
        public static final String MILLIAMPS = "mA";
        public static final String MILLIAMP_HOUR = "mAh";
        public static final String MILLIMETER = "mm";
        public static final String MILLIMETER_PER_SECOND = "mm/s";
        public static final String MILLIMETER_PER_SECOND_SQUARE = "mm/(s^2)";
        public static final String MILLIMETER_SQUARE = "mm^2";
        public static final String MILLIMETER_SQUARE_PER_SECOND = "mm^2/s";
        public static final String MILLISECONDS = "ms";
        public static final String MILLIVOLTS = "mV";
        public static final String MINUTES = "min";
        public static final String NANOAMPS = "nA";
        public static final String NO_UNITS = "no_units";
        public static final String PERCENT = "%";
        public static final String PIXEL = "px";
        public static final String POWER = "dB";
        public static final String RPM = "rpm";
        public static final String SCORE = "Score";
        public static final String SECONDS = "s";
        public static final String U_SIEMENS = "uS";
        public static final String U_TESLA = "uT";
    }

    public static final class ShimmerDeviceCommon {
        public static final String[] ListOfOnOff = {"On", "Off"};
        public static final Integer[] ListOfOnOffConfigValues = {1, 0};
    }

    public static class ShimmerGq802154 {

        public static class ObjectClusterSensorName {
            public static final String FREQUENCY = "Frequency";
            public static final String POWER = "Power";
            public static final String POWER_CALC_15 = "SpanManager-15";
            public static final String POWER_CALC_85 = "SpanManager-85";
            public static final String RADIO_RECEPTION = "Radio_Reception";
        }
    }

    public static class Shimmer2 {
        public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
        public static final String[] ListofCompatibleSensors = {OrientationModule6DOF.GuiLabelConfig.ACCELEROMETER, "Gyroscope", "Magnetometer", "Battery Voltage", "ECG", "EMG", "GSR", "Exp Board", "Bridge Amplifier", "Heart Rate"};
        public static final String[] ListofAccelRange = {"+/- 1.5g", "+/- 6g"};
        public static final String[] ListofMagRange = {"+/- 0.8Ga", "+/- 1.3Ga", "+/- 1.9Ga", "+/- 2.5Ga", "+/- 4.0Ga", "+/- 4.7Ga", "+/- 5.6Ga", "+/- 8.1Ga"};
        public static final String[] ListofGSRRange = {"10kOhm to 56kOhm", "56kOhm to 220kOhm", "220kOhm to 680kOhm", "680kOhm to 4.7MOhm", "Auto Range"};

        static {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(0, new SensorDetailsRef(128L, 0L, OrientationModule6DOF.GuiLabelConfig.ACCELEROMETER));
            linkedHashMap.put(1, new SensorDetailsRef(64L, 0L, "Gyroscope"));
            linkedHashMap.put(2, new SensorDetailsRef(32L, 0L, "Magnetometer"));
            linkedHashMap.put(3, new SensorDetailsRef(8L, 0L, "EMG"));
            linkedHashMap.put(4, new SensorDetailsRef(16L, 0L, "ECG"));
            linkedHashMap.put(5, new SensorDetailsRef(4L, 0L, "GSR"));
            linkedHashMap.put(6, new SensorDetailsRef(2L, 0L, "Exp Board A7"));
            linkedHashMap.put(7, new SensorDetailsRef(1L, 0L, "Exp Board A0"));
            linkedHashMap.put(8, new SensorDetailsRef(3L, 0L, "Exp Board"));
            linkedHashMap.put(9, new SensorDetailsRef(32768L, 0L, "Bridge Amplifier"));
            linkedHashMap.put(10, new SensorDetailsRef(DefaultHttpDataFactory.MINSIZE, 0L, "Heart Rate"));
            linkedHashMap.put(11, new SensorDetailsRef(Http2CodecUtil.DEFAULT_HEADER_LIST_SIZE, 0L, "Battery Voltage"));
            linkedHashMap.put(12, new SensorDetailsRef(2048L, 0L, "External ADC A15"));
            linkedHashMap.put(13, new SensorDetailsRef(1024L, 0L, "Internal ADC A1"));
            linkedHashMap.put(14, new SensorDetailsRef(512L, 0L, "Internal ADC A12"));
            linkedHashMap.put(15, new SensorDetailsRef(256L, 0L, "Internal ADC A13"));
            linkedHashMap.put(16, new SensorDetailsRef(8388608L, 0L, "Internal ADC A14"));
            ((SensorDetailsRef) linkedHashMap.get(1)).mListOfSensorIdsConflicting = Arrays.asList(4, 3, 5, 9);
            ((SensorDetailsRef) linkedHashMap.get(2)).mListOfSensorIdsConflicting = Arrays.asList(4, 3, 5, 9);
            ((SensorDetailsRef) linkedHashMap.get(3)).mListOfSensorIdsConflicting = Arrays.asList(5, 4, 9, 1, 2);
            ((SensorDetailsRef) linkedHashMap.get(4)).mListOfSensorIdsConflicting = Arrays.asList(5, 3, 9, 1, 2);
            ((SensorDetailsRef) linkedHashMap.get(5)).mListOfSensorIdsConflicting = Arrays.asList(4, 3, 9, 1, 2);
            ((SensorDetailsRef) linkedHashMap.get(6)).mListOfSensorIdsConflicting = Arrays.asList(10, 11);
            ((SensorDetailsRef) linkedHashMap.get(7)).mListOfSensorIdsConflicting = Arrays.asList(10, 11);
            ((SensorDetailsRef) linkedHashMap.get(9)).mListOfSensorIdsConflicting = Arrays.asList(4, 3, 5, 1, 2);
            ((SensorDetailsRef) linkedHashMap.get(10)).mListOfSensorIdsConflicting = Arrays.asList(7, 6);
            ((SensorDetailsRef) linkedHashMap.get(11)).mListOfSensorIdsConflicting = Arrays.asList(7, 6);
            mSensorMapRef = Collections.unmodifiableMap(linkedHashMap);
        }

        public static class ObjectClusterSensorName {
            public static String ACCEL_X = "Accel_X";
            public static String ACCEL_Y = "Accel_Y";
            public static String ACCEL_Z = "Accel_Z";
            public static String AXIS_ANGLE_6DOF_A = "Axis_Angle_A";
            public static String AXIS_ANGLE_6DOF_X = "Axis_Angle_X";
            public static String AXIS_ANGLE_6DOF_Y = "Axis_Angle_Y";
            public static String AXIS_ANGLE_6DOF_Z = "Axis_Angle_Z";
            public static String AXIS_ANGLE_9DOF_A = "Axis_Angle_A";
            public static String AXIS_ANGLE_9DOF_X = "Axis_Angle_X";
            public static String AXIS_ANGLE_9DOF_Y = "Axis_Angle_Y";
            public static String AXIS_ANGLE_9DOF_Z = "Axis_Angle_Z";
            public static String BATTERY = "Battery";
            public static String BRIDGE_AMP_HIGH = "Bridge_Amp_High";
            public static String BRIDGE_AMP_LOW = "Bridge_Amp_Low";
            public static String ECG_LA_LL = "ECG_LA-LL";
            public static String ECG_RA_LL = "ECG_RA-LL";
            public static String ECG_TO_HR = "ECGtoHR";
            public static String EMG = "EMG";
            public static String EULER_6DOF_PITCH = "Euler_6DOF_PITCH";
            public static String EULER_6DOF_ROLL = "Euler_6DOF_Roll";
            public static String EULER_6DOF_YAW = "Euler_6DOF_Yaw";
            public static String EULER_9DOF_PITCH = "Euler_9DOF_PITCH";
            public static String EULER_9DOF_ROLL = "Euler_9DOF_Roll";
            public static String EULER_9DOF_YAW = "Euler_9DOF_YAW";
            public static String EXP_BOARD_A0 = "Exp_Board_A0";
            public static String EXP_BOARD_A7 = "Exp_Board_A7";
            public static String EXT_EXP_A15 = "Ext_Exp_A15";
            public static String EXT_EXP_A6 = "Ext_Exp_A6";
            public static String EXT_EXP_A7 = "Ext_Exp_A7";
            public static String GSR = "GSR";
            public static String GSR_RES = "GSR Res";
            public static String GYRO_X = "Gyro_X";
            public static String GYRO_Y = "Gyro_Y";
            public static String GYRO_Z = "Gyro_Z";
            public static String HEART_RATE = "Heart_Rate";
            public static String INT_EXP_A1 = "Int_Exp_A1";
            public static String INT_EXP_A12 = "Int_Exp_A12";
            public static String INT_EXP_A13 = "Int_Exp_A13";
            public static String INT_EXP_A14 = "Int_Exp_A14";
            public static String MAG_X = "Mag_X";
            public static String MAG_Y = "Mag_Y";
            public static String MAG_Z = "Mag_Z";
            public static String QUAT_MADGE_6DOF_W = "Quat_Madge_6DOF_W";
            public static String QUAT_MADGE_6DOF_X = "Quat_Madge_6DOF_X";
            public static String QUAT_MADGE_6DOF_Y = "Quat_Madge_6DOF_Y";
            public static String QUAT_MADGE_6DOF_Z = "Quat_Madge_6DOF_Z";
            public static String QUAT_MADGE_9DOF_W = "Quat_Madge_9DOF_W";
            public static String QUAT_MADGE_9DOF_X = "Quat_Madge_9DOF_X";
            public static String QUAT_MADGE_9DOF_Y = "Quat_Madge_9DOF_Y";
            public static String QUAT_MADGE_9DOF_Z = "Quat_Madge_9DOF_Z";
            public static String REAL_TIME_CLOCK = "RealTime";
            public static String REG = "Reg";
            public static String TIMESTAMP = "Timestamp";
            public static String VOLT_REG = "VSenseReg";
        }

        public class Channel {
            public static final int AnExA0 = 14;
            public static final int AnExA7 = 15;
            public static final int BridgeAmpHigh = 16;
            public static final int BridgeAmpLow = 17;
            public static final int EcgLaLl = 10;
            public static final int EcgRaLl = 9;
            public static final int Emg = 13;
            public static final int GsrRaw = 11;
            public static final int GsrRes = 12;
            public static final int HeartRate = 18;
            public static final int XAccel = 0;
            public static final int XGyro = 3;
            public static final int XMag = 6;
            public static final int YAccel = 1;
            public static final int YGyro = 4;
            public static final int YMag = 7;
            public static final int ZAccel = 2;
            public static final int ZGyro = 5;
            public static final int ZMag = 8;

            public Channel() {
            }
        }

        public class SensorBitmap {
            public static final int SENSOR_ACCEL = 128;
            public static final int SENSOR_BRIDGE_AMP = 32768;
            public static final int SENSOR_ECG = 16;
            public static final int SENSOR_EMG = 8;
            public static final int SENSOR_EXP_BOARD_A0 = 1;
            public static final int SENSOR_EXP_BOARD_A7 = 2;
            public static final int SENSOR_GSR = 4;
            public static final int SENSOR_GYRO = 64;
            public static final int SENSOR_HEART = 16384;
            public static final int SENSOR_MAG = 32;

            public SensorBitmap() {
            }
        }

        public class SENSOR_ID {
            public static final int ACCEL = 0;
            public static final int BATT = 11;
            public static final int BRIDGE_AMP = 9;
            public static final int ECG = 4;
            public static final int EMG = 3;
            public static final int EXP_BOARD = 8;
            public static final int EXP_BOARD_A0 = 7;
            public static final int EXP_BOARD_A7 = 6;
            public static final int EXT_ADC_A15 = 12;
            public static final int GSR = 5;
            public static final int GYRO = 1;
            public static final int HEART = 10;
            public static final int INT_ADC_A1 = 13;
            public static final int INT_ADC_A12 = 14;
            public static final int INT_ADC_A13 = 15;
            public static final int INT_ADC_A14 = 16;
            public static final int MAG = 2;

            public SENSOR_ID() {
            }
        }
    }

    public static class Shimmer3 {
        public static final String[] ListofBluetoothBaudRates = {"115200", "1200", "2400", "4800", "9600", "19200", "38400", "57600", "230400", "460800", "921600"};
        public static final Integer[] ListofBluetoothBaudRatesConfigValues = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        @Deprecated
        public static final String[] ListofCompatibleSensors = {"Low Noise Accelerometer", "Wide Range Accelerometer", "Gyroscope", "Magnetometer", "Battery Voltage", "External ADC A7", "External ADC A6", "External ADC A15", "Internal ADC A1", "Internal ADC A12", "Internal ADC A13", "Internal ADC A14", "Pressure", "GSR", "EXG1", "EXG2", "EXG1 16Bit", "EXG2 16Bit", "Bridge Amplifier"};
        public static final ConfigOptionDetailsSensor configOptionLowPowerAutoStop;
        public static final Map<String, ChannelDetails> mChannelMapRef;
        public static final Map<String, ChannelDetails> mChannelMapRef3r;
        public static final Map<String, ConfigOptionDetailsSensor> mConfigOptionsMapRef;
        public static final Map<String, ConfigOptionDetailsSensor> mConfigOptionsMapRef3r;
        public static final Map<Integer, SensorGroupingDetails> mSensorGroupingMapRef;
        public static final Map<Integer, SensorGroupingDetails> mSensorGroupingMapRef3r;
        public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
        public static final Map<Integer, SensorDetailsRef> mSensorMapRef3r;

        static {
            List<String> list;
            List<String> list2;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.putAll(SensorShimmerClock.mSensorMapRef);
            linkedHashMap.putAll(SensorGSR.mSensorMapRef);
            linkedHashMap.putAll(SensorADC.mSensorMapRef);
            linkedHashMap.putAll(SensorBattVoltage.mSensorMapRef);
            linkedHashMap.putAll(SensorEXG.mSensorMapRef);
            linkedHashMap.putAll(SensorBridgeAmp.mSensorMapRef);
            linkedHashMap.putAll(SensorPPG.mSensorMapRef);
            mSensorMapRef = Collections.unmodifiableMap(linkedHashMap);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.putAll(SensorShimmerClock.mSensorMapRef);
            linkedHashMap2.putAll(SensorGSR.mSensorMapRef);
            linkedHashMap2.putAll(SensorADC.mSensorMapRef3r);
            linkedHashMap2.putAll(SensorBattVoltage.mSensorMapRef);
            linkedHashMap2.putAll(SensorEXG.mSensorMapRef);
            linkedHashMap2.putAll(SensorBridgeAmp.mSensorMapRef);
            linkedHashMap2.putAll(SensorPPG.mSensorMapRef3r);
            mSensorMapRef3r = Collections.unmodifiableMap(linkedHashMap2);
            LinkedHashMap linkedHashMap3 = new LinkedHashMap();
            linkedHashMap3.put("Batt_Percentage", SensorBattVoltage.channelBattPercentage);
            linkedHashMap3.put("Packet_Reception_Rate_Current", SensorShimmerClock.channelReceptionRateCurrent);
            linkedHashMap3.put("Packet_Reception_Rate_Trial", SensorShimmerClock.channelReceptionRateTrial);
            linkedHashMap3.put(ObjectClusterSensorName.EVENT_MARKER, SensorShimmerClock.channelEventMarker);
            linkedHashMap3.put("Timestamp", SensorShimmerClock.channelShimmerClock2byte);
            linkedHashMap3.put("System_Timestamp", SensorShimmerClock.channelSystemTimestamp);
            linkedHashMap3.put(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT, SensorShimmerClock.channelSystemTimestampPlot);
            linkedHashMap3.put(SensorShimmerClock.ObjectClusterSensorName.REAL_TIME_CLOCK, SensorShimmerClock.channelRealTimeClock);
            linkedHashMap3.put(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_OFFSET, SensorShimmerClock.channelShimmerClockOffset);
            linkedHashMap3.putAll(SensorBattVoltage.mChannelMapRef);
            linkedHashMap3.putAll(SensorADC.mChannelMapRef);
            linkedHashMap3.putAll(SensorBridgeAmp.mChannelMapRef);
            linkedHashMap3.putAll(SensorGSR.mChannelMapRef);
            linkedHashMap3.putAll(SensorEXG.mChannelMapRef);
            linkedHashMap3.putAll(SensorPPG.mChannelMapRef);
            linkedHashMap3.put(ObjectClusterSensorName.ECG_TO_HR_FW, SensorECGToHRFw.channelEcgToHrFw);
            linkedHashMap3.put(ObjectClusterSensorName.PPG_TO_HR, new ChannelDetails(ObjectClusterSensorName.PPG_TO_HR, ObjectClusterSensorName.PPG_TO_HR, DatabaseChannelHandles.PPG_TO_HR, CHANNEL_UNITS.BEATS_PER_MINUTE, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL)));
            mChannelMapRef = Collections.unmodifiableMap(linkedHashMap3);
            LinkedHashMap linkedHashMap4 = new LinkedHashMap();
            linkedHashMap4.put("Batt_Percentage", SensorBattVoltage.channelBattPercentage);
            linkedHashMap4.put("Packet_Reception_Rate_Current", SensorShimmerClock.channelReceptionRateCurrent);
            linkedHashMap4.put("Packet_Reception_Rate_Trial", SensorShimmerClock.channelReceptionRateTrial);
            linkedHashMap4.put(ObjectClusterSensorName.EVENT_MARKER, SensorShimmerClock.channelEventMarker);
            linkedHashMap4.put("Timestamp", SensorShimmerClock.channelShimmerClock2byte);
            linkedHashMap4.put("System_Timestamp", SensorShimmerClock.channelSystemTimestamp);
            linkedHashMap4.put(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT, SensorShimmerClock.channelSystemTimestampPlot);
            linkedHashMap4.put(SensorShimmerClock.ObjectClusterSensorName.REAL_TIME_CLOCK, SensorShimmerClock.channelRealTimeClock);
            linkedHashMap4.put(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_OFFSET, SensorShimmerClock.channelShimmerClockOffset);
            linkedHashMap4.putAll(SensorBattVoltage.mChannelMapRef);
            linkedHashMap4.putAll(SensorADC.mChannelMapRef3r);
            linkedHashMap4.putAll(SensorBridgeAmp.mChannelMapRef);
            linkedHashMap4.putAll(SensorGSR.mChannelMapRef);
            linkedHashMap4.putAll(SensorEXG.mChannelMapRef);
            linkedHashMap4.putAll(SensorPPG.mChannelMapRef3r);
            linkedHashMap4.put(ObjectClusterSensorName.ECG_TO_HR_FW, SensorECGToHRFw.channelEcgToHrFw);
            linkedHashMap4.put(ObjectClusterSensorName.PPG_TO_HR, new ChannelDetails(ObjectClusterSensorName.PPG_TO_HR, ObjectClusterSensorName.PPG_TO_HR, DatabaseChannelHandles.PPG_TO_HR, CHANNEL_UNITS.BEATS_PER_MINUTE, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL)));
            mChannelMapRef3r = Collections.unmodifiableMap(linkedHashMap4);
            TreeMap treeMap = new TreeMap();
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.STREAMING_PROPERTIES.ordinal()), SensorShimmerClock.sensorGroupStreamingProperties);
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.BATTERY_MONITORING.ordinal()), SensorBattVoltage.sensorGroupBattVoltage);
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.EXTERNAL_EXPANSION_ADC.ordinal()), SensorADC.sensorGroupExternalExpansionADCs);
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.GSR.ordinal()), new SensorGroupingDetails(SensorGSR.LABEL_SENSOR_TILE.GSR, Arrays.asList(19, 105), CompatibilityInfoForMaps.listOfCompatibleVersionInfoGsr));
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.EXG.ordinal()), new SensorGroupingDetails(SensorEXG.LABEL_SENSOR_TILE.EXG, Arrays.asList(100, 101, 102, 116, 103, 106), CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgGeneral));
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.PROTO3_MINI.ordinal()), new SensorGroupingDetails(SensorADC.LABEL_SENSOR_TILE.PROTO3_MINI, Arrays.asList(17, 7, 10, 13), CompatibilityInfoForMaps.listOfCompatibleVersionInfoProto3Mini));
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.PROTO3_DELUXE.ordinal()), new SensorGroupingDetails(SensorADC.LABEL_SENSOR_TILE.PROTO3_DELUXE, Arrays.asList(17, 7, 10, 13), CompatibilityInfoForMaps.listOfCompatibleVersionInfoProto3Deluxe));
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.PROTO3_DELUXE_SUPP.ordinal()), new SensorGroupingDetails(LABEL_SENSOR_TILE.PROTO3_DELUXE_SUPP.getTileText(), Arrays.asList(110, 113), CompatibilityInfoForMaps.listOfCompatibleVersionInfoProto3Deluxe));
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.BRIDGE_AMPLIFIER.ordinal()), SensorBridgeAmp.sensorGroupBrAmp);
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.BRIDGE_AMPLIFIER_SUPP.ordinal()), SensorBridgeAmp.sensorGroupBrAmpTemperature);
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.ADXL377_ACCEL_200G.ordinal()), new SensorGroupingDetails(LABEL_SENSOR_TILE.ADXL377_ACCEL_200G.getTileText(), Arrays.asList(7, 10, 13), CompatibilityInfoForMaps.listOfCompatibleVersionInfoAdxl377Accel200G));
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.INTERNAL_EXPANSION_ADC.ordinal()), SensorADC.sensorGroupInternalExpansionADCs);
            for (SensorGroupingDetails sensorGroupingDetails : treeMap.values()) {
                Iterator<Integer> it2 = sensorGroupingDetails.mListOfSensorIdsAssociated.iterator();
                while (it2.hasNext()) {
                    SensorDetailsRef sensorDetailsRef = mSensorMapRef.get(it2.next());
                    if (sensorDetailsRef != null && (list2 = sensorDetailsRef.mListOfConfigOptionKeysAssociated) != null) {
                        for (String str : list2) {
                            if (!sensorGroupingDetails.mListOfConfigOptionKeysAssociated.contains(str)) {
                                sensorGroupingDetails.mListOfConfigOptionKeysAssociated.add(str);
                            }
                        }
                    }
                }
            }
            ((SensorGroupingDetails) treeMap.get(Integer.valueOf(LABEL_SENSOR_TILE.PROTO3_DELUXE_SUPP.ordinal()))).mListOfConfigOptionKeysAssociated.add(SensorPPG.GuiLabelConfig.PPG1_ADC_SELECTION);
            ((SensorGroupingDetails) treeMap.get(Integer.valueOf(LABEL_SENSOR_TILE.PROTO3_DELUXE_SUPP.ordinal()))).mListOfConfigOptionKeysAssociated.add(SensorPPG.GuiLabelConfig.PPG2_ADC_SELECTION);
            mSensorGroupingMapRef = Collections.unmodifiableMap(treeMap);
            TreeMap treeMap2 = new TreeMap();
            treeMap2.put(Integer.valueOf(LABEL_SENSOR_TILE.STREAMING_PROPERTIES.ordinal()), SensorShimmerClock.sensorGroupStreamingProperties);
            treeMap2.put(Integer.valueOf(LABEL_SENSOR_TILE.BATTERY_MONITORING.ordinal()), SensorBattVoltage.sensorGroupBattVoltage);
            treeMap2.put(Integer.valueOf(LABEL_SENSOR_TILE.EXTERNAL_EXPANSION_ADC.ordinal()), SensorADC.sensorGroupExternalExpansionADCs);
            treeMap2.put(Integer.valueOf(LABEL_SENSOR_TILE.GSR.ordinal()), new SensorGroupingDetails(SensorGSR.LABEL_SENSOR_TILE.GSR, Arrays.asList(19, 105), CompatibilityInfoForMaps.listOfCompatibleVersionInfoGsr));
            treeMap2.put(Integer.valueOf(LABEL_SENSOR_TILE.EXG.ordinal()), new SensorGroupingDetails(SensorEXG.LABEL_SENSOR_TILE.EXG, Arrays.asList(100, 101, 102, 116, 103, 106), CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgGeneral));
            treeMap2.put(Integer.valueOf(LABEL_SENSOR_TILE.PROTO3_MINI.ordinal()), new SensorGroupingDetails(SensorADC.LABEL_SENSOR_TILE.PROTO3_MINI, Arrays.asList(17, 7, 10, 13), CompatibilityInfoForMaps.listOfCompatibleVersionInfoProto3Mini));
            treeMap2.put(Integer.valueOf(LABEL_SENSOR_TILE.PROTO3_DELUXE.ordinal()), new SensorGroupingDetails(SensorADC.LABEL_SENSOR_TILE.PROTO3_DELUXE, Arrays.asList(17, 7, 10, 13), CompatibilityInfoForMaps.listOfCompatibleVersionInfoProto3Deluxe));
            treeMap2.put(Integer.valueOf(LABEL_SENSOR_TILE.PROTO3_DELUXE_SUPP.ordinal()), new SensorGroupingDetails(LABEL_SENSOR_TILE.PROTO3_DELUXE_SUPP.getTileText(), Arrays.asList(110, 113), CompatibilityInfoForMaps.listOfCompatibleVersionInfoProto3Deluxe));
            treeMap2.put(Integer.valueOf(LABEL_SENSOR_TILE.BRIDGE_AMPLIFIER.ordinal()), SensorBridgeAmp.sensorGroupBrAmp);
            treeMap2.put(Integer.valueOf(LABEL_SENSOR_TILE.BRIDGE_AMPLIFIER_SUPP.ordinal()), SensorBridgeAmp.sensorGroupBrAmpTemperature);
            treeMap2.put(Integer.valueOf(LABEL_SENSOR_TILE.ADXL377_ACCEL_200G.ordinal()), new SensorGroupingDetails(LABEL_SENSOR_TILE.ADXL377_ACCEL_200G.getTileText(), Arrays.asList(7, 10, 13), CompatibilityInfoForMaps.listOfCompatibleVersionInfoAdxl377Accel200G));
            treeMap2.put(Integer.valueOf(LABEL_SENSOR_TILE.INTERNAL_EXPANSION_ADC.ordinal()), SensorADC.sensorGroupInternalExpansionADCs);
            for (SensorGroupingDetails sensorGroupingDetails2 : treeMap2.values()) {
                Iterator<Integer> it3 = sensorGroupingDetails2.mListOfSensorIdsAssociated.iterator();
                while (it3.hasNext()) {
                    SensorDetailsRef sensorDetailsRef2 = mSensorMapRef3r.get(it3.next());
                    if (sensorDetailsRef2 != null && (list = sensorDetailsRef2.mListOfConfigOptionKeysAssociated) != null) {
                        for (String str2 : list) {
                            if (!sensorGroupingDetails2.mListOfConfigOptionKeysAssociated.contains(str2)) {
                                sensorGroupingDetails2.mListOfConfigOptionKeysAssociated.add(str2);
                            }
                        }
                    }
                }
            }
            ((SensorGroupingDetails) treeMap2.get(Integer.valueOf(LABEL_SENSOR_TILE.PROTO3_DELUXE_SUPP.ordinal()))).mListOfConfigOptionKeysAssociated.add(SensorPPG.GuiLabelConfig.PPG1_ADC_SELECTION);
            ((SensorGroupingDetails) treeMap2.get(Integer.valueOf(LABEL_SENSOR_TILE.PROTO3_DELUXE_SUPP.ordinal()))).mListOfConfigOptionKeysAssociated.add(SensorPPG.GuiLabelConfig.PPG2_ADC_SELECTION);
            mSensorGroupingMapRef3r = Collections.unmodifiableMap(treeMap2);
            ConfigOptionDetailsSensor configOptionDetailsSensor = new ConfigOptionDetailsSensor(GuiLabelConfig.LOW_POWER_AUTOSTOP, ShimmerDevice.DatabaseConfigHandle.LOW_POWER_AUTOSTOP, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, Arrays.asList(new ShimmerVerObject(3, 0, 9, 6), new ShimmerVerObject(2, 0, 17, 3)));
            configOptionLowPowerAutoStop = configOptionDetailsSensor;
            LinkedHashMap linkedHashMap5 = new LinkedHashMap();
            linkedHashMap5.put("Shimmer Name", new ConfigOptionDetailsSensor("Shimmer Name", ShimmerDevice.DatabaseConfigHandle.SHIMMER_NAME, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD));
            linkedHashMap5.put("Sampling Rate", new ConfigOptionDetailsSensor("Sampling Rate", ShimmerDevice.DatabaseConfigHandle.SAMPLE_RATE, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD));
            linkedHashMap5.put("InfoMem MAC", new ConfigOptionDetailsSensor("InfoMem MAC", null, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD));
            linkedHashMap5.put(GuiLabelConfig.BUFFER_SIZE, new ConfigOptionDetailsSensor(GuiLabelConfig.BUFFER_SIZE, null, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD));
            linkedHashMap5.put("Config Time", new ConfigOptionDetailsSensor("Config Time", ShimmerDevice.DatabaseConfigHandle.CONFIG_TIME, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD));
            linkedHashMap5.put(GuiLabelConfig.TRIAL_NAME, new ConfigOptionDetailsSensor(GuiLabelConfig.TRIAL_NAME, ShimmerDevice.DatabaseConfigHandle.TRIAL_NAME, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD));
            linkedHashMap5.put(GuiLabelConfig.EXPERIMENT_ID, new ConfigOptionDetailsSensor(GuiLabelConfig.EXPERIMENT_ID, ShimmerDevice.DatabaseConfigHandle.TRIAL_ID, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD, CompatibilityInfoForMaps.listOfCompatibleVersionInfoLogging));
            linkedHashMap5.put(GuiLabelConfig.EXPERIMENT_NUMBER_OF_SHIMMERS, new ConfigOptionDetailsSensor(GuiLabelConfig.EXPERIMENT_NUMBER_OF_SHIMMERS, ShimmerDevice.DatabaseConfigHandle.N_SHIMMER, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD, CompatibilityInfoForMaps.listOfCompatibleVersionInfoSdLog));
            linkedHashMap5.put(GuiLabelConfig.EXPERIMENT_DURATION_ESTIMATED, new ConfigOptionDetailsSensor(GuiLabelConfig.EXPERIMENT_DURATION_ESTIMATED, null, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD, CompatibilityInfoForMaps.listOfCompatibleVersionInfoSdLog));
            linkedHashMap5.put(GuiLabelConfig.EXPERIMENT_DURATION_MAXIMUM, new ConfigOptionDetailsSensor(GuiLabelConfig.EXPERIMENT_DURATION_MAXIMUM, null, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD, CompatibilityInfoForMaps.listOfCompatibleVersionInfoSdLog));
            linkedHashMap5.put(GuiLabelConfig.BROADCAST_INTERVAL, new ConfigOptionDetailsSensor(GuiLabelConfig.BROADCAST_INTERVAL, ShimmerDevice.DatabaseConfigHandle.BROADCAST_INTERVAL, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD, new ArrayList() { // from class: com.shimmerresearch.driver.Configuration.Shimmer3.1
            }));
            String[] strArr = ListofBluetoothBaudRates;
            Integer[] numArr = ListofBluetoothBaudRatesConfigValues;
            linkedHashMap5.put(GuiLabelConfig.BLUETOOTH_BAUD_RATE, new ConfigOptionDetailsSensor(GuiLabelConfig.BLUETOOTH_BAUD_RATE, ShimmerDevice.DatabaseConfigHandle.BAUD_RATE, strArr, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, (List<ShimmerVerObject>) CompatibilityInfoForMaps.listOfCompatibleVersionInfoStreaming));
            linkedHashMap5.put("GSR Range", SensorGSR.configOptionGsrRange);
            linkedHashMap5.put(SensorEXG.GuiLabelConfig.EXG_GAIN, SensorEXG.configOptionExgGain);
            linkedHashMap5.put(SensorEXG.GuiLabelConfig.EXG_RESOLUTION, SensorEXG.configOptionExgResolution);
            linkedHashMap5.put(SensorEXG.GuiLabelConfig.EXG_REFERENCE_ELECTRODE, SensorEXG.configOptionExgRefElectrode);
            linkedHashMap5.put(SensorEXG.GuiLabelConfig.EXG_BYTES, SensorEXG.configOptionExgBytes);
            linkedHashMap5.put(SensorEXG.GuiLabelConfig.EXG_RATE, SensorEXG.configOptionExgRate);
            linkedHashMap5.put(SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_DETECTION, SensorEXG.configOptionExgLeadOffDetection);
            linkedHashMap5.put(SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_CURRENT, SensorEXG.configOptionExgLeadOffCurrent);
            linkedHashMap5.put(SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_COMPARATOR, SensorEXG.configOptionExgLeadOffComparator);
            linkedHashMap5.put(SensorEXG.GuiLabelConfig.EXG_RESOLUTION, SensorEXG.configOptionExgResolution);
            linkedHashMap5.put(SensorEXG.GuiLabelConfig.EXG_RESPIRATION_DETECT_FREQ, SensorEXG.configOptionExgRespirationDetectFreq);
            linkedHashMap5.put(SensorEXG.GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE, SensorEXG.configOptionExgRespirationDetectPhase);
            linkedHashMap5.put(GuiLabelConfig.USER_BUTTON_START, new ConfigOptionDetailsSensor(GuiLabelConfig.USER_BUTTON_START, ShimmerDevice.DatabaseConfigHandle.USER_BUTTON, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, CompatibilityInfoForMaps.listOfCompatibleVersionInfoLogging));
            linkedHashMap5.put(GuiLabelConfig.SINGLE_TOUCH_START, new ConfigOptionDetailsSensor(GuiLabelConfig.SINGLE_TOUCH_START, ShimmerDevice.DatabaseConfigHandle.SINGLE_TOUCH_START, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, CompatibilityInfoForMaps.listOfCompatibleVersionInfoSdLog));
            linkedHashMap5.put(GuiLabelConfig.EXPERIMENT_MASTER_SHIMMER, new ConfigOptionDetailsSensor(GuiLabelConfig.EXPERIMENT_MASTER_SHIMMER, ShimmerDevice.DatabaseConfigHandle.MASTER_CONFIG, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, CompatibilityInfoForMaps.listOfCompatibleVersionInfoSdLog));
            linkedHashMap5.put(GuiLabelConfig.EXPERIMENT_SYNC_WHEN_LOGGING, new ConfigOptionDetailsSensor(GuiLabelConfig.EXPERIMENT_SYNC_WHEN_LOGGING, ShimmerDevice.DatabaseConfigHandle.SYNC_CONFIG, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, CompatibilityInfoForMaps.listOfCompatibleVersionInfoSdLog));
            linkedHashMap5.put(GuiLabelConfig.KINEMATIC_LPM, new ConfigOptionDetailsSensor(GuiLabelConfig.KINEMATIC_LPM, null, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW));
            linkedHashMap5.put("Internal Expansion Board Power", new ConfigOptionDetailsSensor("Internal Expansion Board Power", null, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX));
            linkedHashMap5.put(SensorPPG.GuiLabelConfig.PPG_ADC_SELECTION, SensorPPG.configOptionPpgAdcSelection);
            linkedHashMap5.put(SensorPPG.GuiLabelConfig.PPG1_ADC_SELECTION, SensorPPG.configOptionPpg1AdcSelection);
            linkedHashMap5.put(SensorPPG.GuiLabelConfig.PPG2_ADC_SELECTION, SensorPPG.configOptionPpg2AdcSelection);
            linkedHashMap5.put(GuiLabelConfig.ENABLE_ERROR_LEDS_RTC, new ConfigOptionDetailsSensor(GuiLabelConfig.ENABLE_ERROR_LEDS_RTC, null, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, Arrays.asList(new ShimmerVerObject(3, 0, 7, 12), new ShimmerVerObject(2, 0, 11, 3))));
            linkedHashMap5.put(GuiLabelConfig.ENABLE_ERROR_LEDS_SD, new ConfigOptionDetailsSensor(GuiLabelConfig.ENABLE_ERROR_LEDS_SD, null, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, Arrays.asList(new ShimmerVerObject(3, 0, 7, 12))));
            linkedHashMap5.put(GuiLabelConfig.TCXO, new ConfigOptionDetailsSensor(GuiLabelConfig.TCXO, ShimmerDevice.DatabaseConfigHandle.TXCO, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardAndFw));
            linkedHashMap5.put(GuiLabelConfig.LOW_POWER_AUTOSTOP, configOptionDetailsSensor);
            mConfigOptionsMapRef = Collections.unmodifiableMap(linkedHashMap5);
            LinkedHashMap linkedHashMap6 = new LinkedHashMap();
            linkedHashMap6.put("Shimmer Name", new ConfigOptionDetailsSensor("Shimmer Name", ShimmerDevice.DatabaseConfigHandle.SHIMMER_NAME, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD));
            linkedHashMap6.put("Sampling Rate", new ConfigOptionDetailsSensor("Sampling Rate", ShimmerDevice.DatabaseConfigHandle.SAMPLE_RATE, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD));
            linkedHashMap6.put("InfoMem MAC", new ConfigOptionDetailsSensor("InfoMem MAC", null, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD));
            linkedHashMap6.put(GuiLabelConfig.BUFFER_SIZE, new ConfigOptionDetailsSensor(GuiLabelConfig.BUFFER_SIZE, null, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD));
            linkedHashMap6.put("Config Time", new ConfigOptionDetailsSensor("Config Time", ShimmerDevice.DatabaseConfigHandle.CONFIG_TIME, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD));
            linkedHashMap6.put(GuiLabelConfig.TRIAL_NAME, new ConfigOptionDetailsSensor(GuiLabelConfig.TRIAL_NAME, ShimmerDevice.DatabaseConfigHandle.TRIAL_NAME, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD));
            linkedHashMap6.put(GuiLabelConfig.EXPERIMENT_ID, new ConfigOptionDetailsSensor(GuiLabelConfig.EXPERIMENT_ID, ShimmerDevice.DatabaseConfigHandle.TRIAL_ID, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD, CompatibilityInfoForMaps.listOfCompatibleVersionInfoLogging));
            linkedHashMap6.put(GuiLabelConfig.EXPERIMENT_NUMBER_OF_SHIMMERS, new ConfigOptionDetailsSensor(GuiLabelConfig.EXPERIMENT_NUMBER_OF_SHIMMERS, ShimmerDevice.DatabaseConfigHandle.N_SHIMMER, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD, CompatibilityInfoForMaps.listOfCompatibleVersionInfoSdLog));
            linkedHashMap6.put(GuiLabelConfig.EXPERIMENT_DURATION_ESTIMATED, new ConfigOptionDetailsSensor(GuiLabelConfig.EXPERIMENT_DURATION_ESTIMATED, null, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD, CompatibilityInfoForMaps.listOfCompatibleVersionInfoSdLog));
            linkedHashMap6.put(GuiLabelConfig.EXPERIMENT_DURATION_MAXIMUM, new ConfigOptionDetailsSensor(GuiLabelConfig.EXPERIMENT_DURATION_MAXIMUM, null, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD, CompatibilityInfoForMaps.listOfCompatibleVersionInfoSdLog));
            linkedHashMap6.put(GuiLabelConfig.BROADCAST_INTERVAL, new ConfigOptionDetailsSensor(GuiLabelConfig.BROADCAST_INTERVAL, ShimmerDevice.DatabaseConfigHandle.BROADCAST_INTERVAL, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD, new ArrayList() { // from class: com.shimmerresearch.driver.Configuration.Shimmer3.2
            }));
            linkedHashMap6.put(GuiLabelConfig.BLUETOOTH_BAUD_RATE, new ConfigOptionDetailsSensor(GuiLabelConfig.BLUETOOTH_BAUD_RATE, ShimmerDevice.DatabaseConfigHandle.BAUD_RATE, strArr, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, (List<ShimmerVerObject>) CompatibilityInfoForMaps.listOfCompatibleVersionInfoStreaming));
            linkedHashMap6.put("GSR Range", SensorGSR.configOptionGsrRange);
            linkedHashMap6.put(SensorEXG.GuiLabelConfig.EXG_GAIN, SensorEXG.configOptionExgGain);
            linkedHashMap6.put(SensorEXG.GuiLabelConfig.EXG_RESOLUTION, SensorEXG.configOptionExgResolution);
            linkedHashMap6.put(SensorEXG.GuiLabelConfig.EXG_REFERENCE_ELECTRODE, SensorEXG.configOptionExgRefElectrode);
            linkedHashMap6.put(SensorEXG.GuiLabelConfig.EXG_BYTES, SensorEXG.configOptionExgBytes);
            linkedHashMap6.put(SensorEXG.GuiLabelConfig.EXG_RATE, SensorEXG.configOptionExgRate);
            linkedHashMap6.put(SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_DETECTION, SensorEXG.configOptionExgLeadOffDetection);
            linkedHashMap6.put(SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_CURRENT, SensorEXG.configOptionExgLeadOffCurrent);
            linkedHashMap6.put(SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_COMPARATOR, SensorEXG.configOptionExgLeadOffComparator);
            linkedHashMap6.put(SensorEXG.GuiLabelConfig.EXG_RESOLUTION, SensorEXG.configOptionExgResolution);
            linkedHashMap6.put(SensorEXG.GuiLabelConfig.EXG_RESPIRATION_DETECT_FREQ, SensorEXG.configOptionExgRespirationDetectFreq);
            linkedHashMap6.put(SensorEXG.GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE, SensorEXG.configOptionExgRespirationDetectPhase);
            linkedHashMap6.put(GuiLabelConfig.USER_BUTTON_START, new ConfigOptionDetailsSensor(GuiLabelConfig.USER_BUTTON_START, ShimmerDevice.DatabaseConfigHandle.USER_BUTTON, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, CompatibilityInfoForMaps.listOfCompatibleVersionInfoLogging));
            linkedHashMap6.put(GuiLabelConfig.SINGLE_TOUCH_START, new ConfigOptionDetailsSensor(GuiLabelConfig.SINGLE_TOUCH_START, ShimmerDevice.DatabaseConfigHandle.SINGLE_TOUCH_START, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, CompatibilityInfoForMaps.listOfCompatibleVersionInfoSdLog));
            linkedHashMap6.put(GuiLabelConfig.EXPERIMENT_MASTER_SHIMMER, new ConfigOptionDetailsSensor(GuiLabelConfig.EXPERIMENT_MASTER_SHIMMER, ShimmerDevice.DatabaseConfigHandle.MASTER_CONFIG, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, CompatibilityInfoForMaps.listOfCompatibleVersionInfoSdLog));
            linkedHashMap6.put(GuiLabelConfig.EXPERIMENT_SYNC_WHEN_LOGGING, new ConfigOptionDetailsSensor(GuiLabelConfig.EXPERIMENT_SYNC_WHEN_LOGGING, ShimmerDevice.DatabaseConfigHandle.SYNC_CONFIG, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, CompatibilityInfoForMaps.listOfCompatibleVersionInfoSdLog));
            linkedHashMap6.put(GuiLabelConfig.KINEMATIC_LPM, new ConfigOptionDetailsSensor(GuiLabelConfig.KINEMATIC_LPM, null, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW));
            linkedHashMap6.put("Internal Expansion Board Power", new ConfigOptionDetailsSensor("Internal Expansion Board Power", null, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX));
            linkedHashMap6.put(SensorPPG.GuiLabelConfig.PPG_ADC_SELECTION, SensorPPG.configOptionPpgAdcSelection3r);
            linkedHashMap6.put(SensorPPG.GuiLabelConfig.PPG1_ADC_SELECTION, SensorPPG.configOptionPpg1AdcSelection3r);
            linkedHashMap6.put(SensorPPG.GuiLabelConfig.PPG2_ADC_SELECTION, SensorPPG.configOptionPpg2AdcSelection3r);
            linkedHashMap6.put(GuiLabelConfig.ENABLE_ERROR_LEDS_RTC, new ConfigOptionDetailsSensor(GuiLabelConfig.ENABLE_ERROR_LEDS_RTC, null, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, Arrays.asList(new ShimmerVerObject(3, 0, 7, 12), new ShimmerVerObject(2, 0, 11, 3))));
            linkedHashMap6.put(GuiLabelConfig.ENABLE_ERROR_LEDS_SD, new ConfigOptionDetailsSensor(GuiLabelConfig.ENABLE_ERROR_LEDS_SD, null, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, Arrays.asList(new ShimmerVerObject(3, 0, 7, 12))));
            linkedHashMap6.put(GuiLabelConfig.TCXO, new ConfigOptionDetailsSensor(GuiLabelConfig.TCXO, ShimmerDevice.DatabaseConfigHandle.TXCO, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardAndFw));
            linkedHashMap6.put(GuiLabelConfig.LOW_POWER_AUTOSTOP, configOptionDetailsSensor);
            mConfigOptionsMapRef3r = Collections.unmodifiableMap(linkedHashMap6);
        }

        public enum LABEL_SENSOR_TILE {
            STREAMING_PROPERTIES("Device Properties"),
            LOW_NOISE_ACCEL("Low-Noise Accelerometer"),
            LOW_NOISE_ACCEL_3R("Low-Noise Accelerometer"),
            WIDE_RANGE_ACCEL("Wide-Range Accelerometer"),
            HIGH_G_ACCEL("High-G Accelerometer"),
            WIDE_RANGE_ACCEL_3R("Wide-Range Accelerometer"),
            GYRO("Gyroscope"),
            GYRO_3R("Gyroscope"),
            MAG("Magnetometer"),
            MAG_3R("Magnetometer"),
            ALT_MAG_3R("Alternate Magnetometer"),
            PRESSURE_TEMPERATURE_BMP180("Pressure & Temperature"),
            PRESSURE_TEMPERATURE_BMP280("Pressure & Temperature"),
            PRESSURE_TEMPERATURE_BMP390("Pressure & Temperature"),
            BATTERY_MONITORING("Battery Voltage"),
            EXTERNAL_EXPANSION_ADC(SensorADC.LABEL_SENSOR_TILE.EXTERNAL_EXPANSION_ADC),
            INTERNAL_EXPANSION_ADC(SensorADC.LABEL_SENSOR_TILE.INTERNAL_EXPANSION_ADC),
            GSR(SensorGSR.LABEL_SENSOR_TILE.GSR),
            EXG(SensorEXG.LABEL_SENSOR_TILE.EXG),
            PROTO3_MINI(SensorADC.LABEL_SENSOR_TILE.PROTO3_MINI),
            PROTO3_DELUXE(SensorADC.LABEL_SENSOR_TILE.PROTO3_DELUXE),
            PROTO3_DELUXE_SUPP("PPG"),
            BRIDGE_AMPLIFIER("Bridge Amplifier+"),
            BRIDGE_AMPLIFIER_SUPP("Skin Temperature"),
            ADXL377_ACCEL_200G(GuiLabelSensors.ADXL377_ACCEL_200G),
            MPU_ACCEL_GYRO_MAG(SensorMPU9X50.LABEL_SENSOR_TILE.MPU_ACCEL_GYRO_MAG),
            MPU(SensorMPU9X50.LABEL_SENSOR_TILE.MPU),
            MPU_OTHER(SensorMPU9X50.LABEL_SENSOR_TILE.MPU_OTHER),
            GPS("GPS"),
            STC3100_MONITORING("Battery Monitor (STC3100)"),
            SWEATCH_ADC("Sweatch ADC");

            private String tileText;

            LABEL_SENSOR_TILE(String str) {
                this.tileText = str;
            }

            public String getTileText() {
                return this.tileText;
            }
        }

        public enum GuiLabelAlgorithmGrouping {
            TIME_SYNC("Time Sync"),
            GYRO_ON_THE_FLY_CAL("Gyro on-the-fly Calibration"),
            ORIENTATION_9DOF("9DOF"),
            ORIENTATION_6DOF("6DOF"),
            ECG_TO_HR("ECG-to-HR (with IBI)"),
            PPG_TO_HR("PPG-to-HR (with IBI)"),
            PPG_TO_HR_ADAPTIVE("PPG-to-HR Adaptive"),
            HRV_ECG("HRV"),
            EMG("EMG Processing"),
            ACTIVITY("Activity"),
            GAIT("Gait"),
            GSR_METRICS("GSR Metrics"),
            GSR_BASELINE("GSR Baseline");

            private String tileText;

            GuiLabelAlgorithmGrouping(String str) {
                this.tileText = str;
            }

            public String getTileText() {
                return this.tileText;
            }
        }

        public static class DatabaseChannelHandles {
            public static final String ECG_TO_HR = "ECGToHR";
            public static final String FILTERED = "_Filtered";
            public static final String PPG_TO_HR = "PPGToHR";
        }

        public static class NEW_IMU_EXP_REV {
            public static final int ANY_EXP_BRD_WITH_SPECIAL_REV = 171;
            public static final int BRIDGE_AMP = 3;
            public static final int EXG_UNIFIED = 3;
            public static final int GSR_UNIFIED = 3;
            public static final int IMU = 6;
            public static final int PROTO3_DELUXE = 3;
            public static final int PROTO3_MINI = 3;
        }

        public static class ObjectClusterSensorName {
            public static final String BATT_PERCENTAGE = "Batt_Percentage";
            public static final String FREQUENCY = "Frequency";
            public static final String PACKET_RECEPTION_RATE_CURRENT = "Packet_Reception_Rate_Current";
            public static final String PACKET_RECEPTION_RATE_OVERALL = "Packet_Reception_Rate_Trial";
            public static final String SKIN_TEMPERATURE_PROBE = "Skin_Temperature";
            public static String BATTERY = "Battery";
            public static String REAL_TIME_CLOCK = "RealTime";
            public static String SYSTEM_TIMESTAMP = "System_Timestamp";
            public static String SYSTEM_TIMESTAMP_PLOT = "System_Timestamp_Plot";
            public static String TIMESTAMP = "Timestamp";
            public static String TIMESTAMP_OFFSET = "Offset";
            public static String ACCEL_LN_X = SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_X;
            public static String ACCEL_LN_Y = SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Y;
            public static String ACCEL_LN_Z = SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Z;
            public static String EXT_EXP_ADC_A7 = SensorADC.ObjectClusterSensorName.EXT_EXP_ADC_A7;
            public static String EXT_EXP_ADC_A6 = SensorADC.ObjectClusterSensorName.EXT_EXP_ADC_A6;
            public static String EXT_EXP_ADC_A15 = SensorADC.ObjectClusterSensorName.EXT_EXP_ADC_A15;
            public static String INT_EXP_ADC_A1 = SensorADC.ObjectClusterSensorName.INT_EXP_ADC_A1;
            public static String INT_EXP_ADC_A12 = SensorADC.ObjectClusterSensorName.INT_EXP_ADC_A12;
            public static String INT_EXP_ADC_A13 = SensorADC.ObjectClusterSensorName.INT_EXP_ADC_A13;
            public static String INT_EXP_ADC_A14 = SensorADC.ObjectClusterSensorName.INT_EXP_ADC_A14;
            public static String EXT_ADC_0 = SensorADC.ObjectClusterSensorName.EXT_ADC_0;
            public static String EXT_ADC_1 = SensorADC.ObjectClusterSensorName.EXT_ADC_1;
            public static String EXT_ADC_2 = SensorADC.ObjectClusterSensorName.EXT_ADC_2;
            public static String INT_ADC_3 = SensorADC.ObjectClusterSensorName.INT_ADC_3;
            public static String INT_ADC_0 = SensorADC.ObjectClusterSensorName.INT_ADC_0;
            public static String INT_ADC_1 = SensorADC.ObjectClusterSensorName.INT_ADC_1;
            public static String INT_ADC_2 = SensorADC.ObjectClusterSensorName.INT_ADC_2;
            public static String ACCEL_HIGHG_X = SensorADXL371.ObjectClusterSensorName.ACCEL_HIGHG_X;
            public static String ACCEL_HIGHG_Y = SensorADXL371.ObjectClusterSensorName.ACCEL_HIGHG_Y;
            public static String ACCEL_HIGHG_Z = SensorADXL371.ObjectClusterSensorName.ACCEL_HIGHG_Z;
            public static String ALT_MAG_X = SensorLIS3MDL.ObjectClusterSensorName.MAG_ALT_X;
            public static String ALT_MAG_Y = SensorLIS3MDL.ObjectClusterSensorName.MAG_ALT_Y;
            public static String ALT_MAG_Z = SensorLIS3MDL.ObjectClusterSensorName.MAG_ALT_Z;
            public static String BRIDGE_AMP_HIGH = SensorBridgeAmp.ObjectClusterSensorName.BRIDGE_AMP_HIGH;
            public static String BRIDGE_AMP_LOW = SensorBridgeAmp.ObjectClusterSensorName.BRIDGE_AMP_LOW;
            public static String RESISTANCE_AMP = SensorBridgeAmp.ObjectClusterSensorName.RESISTANCE_AMP;
            public static String GSR_RESISTANCE = SensorGSR.ObjectClusterSensorName.GSR_RESISTANCE;
            public static String GSR_CONDUCTANCE = SensorGSR.ObjectClusterSensorName.GSR_CONDUCTANCE;
            public static String ACCEL_WR_X = SensorLSM303.ObjectClusterSensorName.ACCEL_WR_X;
            public static String ACCEL_WR_Y = SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Y;
            public static String ACCEL_WR_Z = SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Z;
            public static String MAG_X = SensorLSM303.ObjectClusterSensorName.MAG_X;
            public static String MAG_Y = SensorLSM303.ObjectClusterSensorName.MAG_Y;
            public static String MAG_Z = SensorLSM303.ObjectClusterSensorName.MAG_Z;
            public static String TEMPERATURE_BMP180 = SensorBMP180.ObjectClusterSensorName.TEMPERATURE_BMP180;
            public static String PRESSURE_BMP180 = SensorBMP180.ObjectClusterSensorName.PRESSURE_BMP180;
            public static String ECG_GQ = SensorEXG.ObjectClusterSensorName.ECG_GQ;
            public static String EXG1_STATUS = SensorEXG.ObjectClusterSensorName.EXG1_STATUS;
            public static String EXG2_STATUS = SensorEXG.ObjectClusterSensorName.EXG2_STATUS;
            public static String EXG1_CH1_24BIT = SensorEXG.ObjectClusterSensorName.EXG1_CH1_24BIT;
            public static String EXG1_CH2_24BIT = SensorEXG.ObjectClusterSensorName.EXG1_CH2_24BIT;
            public static String EXG1_CH1_16BIT = SensorEXG.ObjectClusterSensorName.EXG1_CH1_16BIT;
            public static String EXG1_CH2_16BIT = SensorEXG.ObjectClusterSensorName.EXG1_CH2_16BIT;
            public static String EXG2_CH1_24BIT = SensorEXG.ObjectClusterSensorName.EXG2_CH1_24BIT;
            public static String EXG2_CH2_24BIT = SensorEXG.ObjectClusterSensorName.EXG2_CH2_24BIT;
            public static String EXG2_CH1_16BIT = SensorEXG.ObjectClusterSensorName.EXG2_CH1_16BIT;
            public static String EXG2_CH2_16BIT = SensorEXG.ObjectClusterSensorName.EXG2_CH2_16BIT;
            public static String EMG_CH1_24BIT = SensorEXG.ObjectClusterSensorName.EMG_CH1_24BIT;
            public static String EMG_CH2_24BIT = SensorEXG.ObjectClusterSensorName.EMG_CH2_24BIT;
            public static String EMG_CH1_16BIT = SensorEXG.ObjectClusterSensorName.EMG_CH1_16BIT;
            public static String EMG_CH2_16BIT = SensorEXG.ObjectClusterSensorName.EMG_CH2_16BIT;
            public static String ECG_LA_RA_24BIT = SensorEXG.ObjectClusterSensorName.ECG_LA_RA_24BIT;
            public static String ECG_LA_RL_24BIT = SensorEXG.ObjectClusterSensorName.ECG_LA_RL_24BIT;
            public static String ECG_LL_RA_24BIT = SensorEXG.ObjectClusterSensorName.ECG_LL_RA_24BIT;
            public static String ECG_LL_LA_24BIT = SensorEXG.ObjectClusterSensorName.ECG_LL_LA_24BIT;
            public static String ECG_RESP_24BIT = SensorEXG.ObjectClusterSensorName.ECG_RESP_24BIT;
            public static String ECG_VX_RL_24BIT = SensorEXG.ObjectClusterSensorName.ECG_VX_RL_24BIT;
            public static String ECG_LA_RA_16BIT = SensorEXG.ObjectClusterSensorName.ECG_LA_RA_16BIT;
            public static String ECG_LA_RL_16BIT = SensorEXG.ObjectClusterSensorName.ECG_LA_RL_16BIT;
            public static String ECG_LL_RA_16BIT = SensorEXG.ObjectClusterSensorName.ECG_LL_RA_16BIT;
            public static String ECG_LL_LA_16BIT = SensorEXG.ObjectClusterSensorName.ECG_LL_LA_16BIT;
            public static String ECG_RESP_16BIT = SensorEXG.ObjectClusterSensorName.ECG_RESP_16BIT;
            public static String ECG_VX_RL_16BIT = SensorEXG.ObjectClusterSensorName.ECG_VX_RL_16BIT;
            public static String EXG_TEST_CHIP1_CH1_24BIT = SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_24BIT;
            public static String EXG_TEST_CHIP1_CH2_24BIT = SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_24BIT;
            public static String EXG_TEST_CHIP2_CH1_24BIT = SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_24BIT;
            public static String EXG_TEST_CHIP2_CH2_24BIT = SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_24BIT;
            public static String EXG_TEST_CHIP1_CH1_16BIT = SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_16BIT;
            public static String EXG_TEST_CHIP1_CH2_16BIT = SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_16BIT;
            public static String EXG_TEST_CHIP2_CH1_16BIT = SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_16BIT;
            public static String EXG_TEST_CHIP2_CH2_16BIT = SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_16BIT;
            public static String GYRO_X = SensorMPU9X50.ObjectClusterSensorName.GYRO_X;
            public static String GYRO_Y = SensorMPU9X50.ObjectClusterSensorName.GYRO_Y;
            public static String GYRO_Z = SensorMPU9X50.ObjectClusterSensorName.GYRO_Z;
            public static String ACCEL_MPU_X = SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_X;
            public static String ACCEL_MPU_Y = SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_Y;
            public static String ACCEL_MPU_Z = SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_Z;
            public static String MAG_MPU_X = SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_X;
            public static String MAG_MPU_Y = SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_Y;
            public static String MAG_MPU_Z = SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_Z;
            public static String QUAT_MPL_6DOF_W = SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_W;
            public static String QUAT_MPL_6DOF_X = SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_X;
            public static String QUAT_MPL_6DOF_Y = SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_Y;
            public static String QUAT_MPL_6DOF_Z = SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_6DOF_Z;
            public static String QUAT_MPL_9DOF_W = SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_W;
            public static String QUAT_MPL_9DOF_X = SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_X;
            public static String QUAT_MPL_9DOF_Y = SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_Y;
            public static String QUAT_MPL_9DOF_Z = SensorMPU9X50.ObjectClusterSensorName.QUAT_MPL_9DOF_Z;
            public static String EULER_MPL_6DOF_X = SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_6DOF_X;
            public static String EULER_MPL_6DOF_Y = SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_6DOF_Y;
            public static String EULER_MPL_6DOF_Z = SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_6DOF_Z;
            public static String EULER_MPL_9DOF_X = SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_9DOF_X;
            public static String EULER_MPL_9DOF_Y = SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_9DOF_Y;
            public static String EULER_MPL_9DOF_Z = SensorMPU9X50.ObjectClusterSensorName.EULER_MPL_9DOF_Z;
            public static String MPL_HEADING = SensorMPU9X50.ObjectClusterSensorName.MPL_HEADING;
            public static String MPL_TEMPERATURE = SensorMPU9X50.ObjectClusterSensorName.MPL_TEMPERATURE;
            public static String MPL_PEDOM_CNT = SensorMPU9X50.ObjectClusterSensorName.MPL_PEDOM_CNT;
            public static String MPL_PEDOM_TIME = SensorMPU9X50.ObjectClusterSensorName.MPL_PEDOM_TIME;
            public static String TAPDIRANDTAPCNT = SensorMPU9X50.ObjectClusterSensorName.TAPDIRANDTAPCNT;
            public static String MOTIONANDORIENT = SensorMPU9X50.ObjectClusterSensorName.MOTIONANDORIENT;
            public static String GYRO_MPU_MPL_X = SensorMPU9X50.ObjectClusterSensorName.GYRO_MPU_MPL_X;
            public static String GYRO_MPU_MPL_Y = SensorMPU9X50.ObjectClusterSensorName.GYRO_MPU_MPL_Y;
            public static String GYRO_MPU_MPL_Z = SensorMPU9X50.ObjectClusterSensorName.GYRO_MPU_MPL_Z;
            public static String ACCEL_MPU_MPL_X = SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_MPL_X;
            public static String ACCEL_MPU_MPL_Y = SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_MPL_Y;
            public static String ACCEL_MPU_MPL_Z = SensorMPU9X50.ObjectClusterSensorName.ACCEL_MPU_MPL_Z;
            public static String MAG_MPU_MPL_X = SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_MPL_X;
            public static String MAG_MPU_MPL_Y = SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_MPL_Y;
            public static String MAG_MPU_MPL_Z = SensorMPU9X50.ObjectClusterSensorName.MAG_MPU_MPL_Z;
            public static String QUAT_DMP_6DOF_W = SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_W;
            public static String QUAT_DMP_6DOF_X = SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_X;
            public static String QUAT_DMP_6DOF_Y = SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_Y;
            public static String QUAT_DMP_6DOF_Z = SensorMPU9X50.ObjectClusterSensorName.QUAT_DMP_6DOF_Z;
            public static String EVENT_MARKER = ShimmerStreamingProperties.ObjectClusterSensorName.EVENT_MARKER;
            public static String QUAT_MADGE_6DOF_W = OrientationModule6DOF.ObjectClusterSensorName.QUAT_MADGE_6DOF_W;
            public static String QUAT_MADGE_6DOF_X = OrientationModule6DOF.ObjectClusterSensorName.QUAT_MADGE_6DOF_X;
            public static String QUAT_MADGE_6DOF_Y = OrientationModule6DOF.ObjectClusterSensorName.QUAT_MADGE_6DOF_Y;
            public static String QUAT_MADGE_6DOF_Z = OrientationModule6DOF.ObjectClusterSensorName.QUAT_MADGE_6DOF_Z;
            public static String QUAT_MADGE_9DOF_W = OrientationModule9DOF.ObjectClusterSensorName.QUAT_MADGE_9DOF_W;
            public static String QUAT_MADGE_9DOF_X = OrientationModule9DOF.ObjectClusterSensorName.QUAT_MADGE_9DOF_X;
            public static String QUAT_MADGE_9DOF_Y = OrientationModule9DOF.ObjectClusterSensorName.QUAT_MADGE_9DOF_Y;
            public static String QUAT_MADGE_9DOF_Z = OrientationModule9DOF.ObjectClusterSensorName.QUAT_MADGE_9DOF_Z;
            public static String AXIS_ANGLE_6DOF_A = OrientationModule6DOF.ObjectClusterSensorName.AXIS_ANGLE_6DOF_A;
            public static String AXIS_ANGLE_6DOF_X = OrientationModule6DOF.ObjectClusterSensorName.AXIS_ANGLE_6DOF_X;
            public static String AXIS_ANGLE_6DOF_Y = OrientationModule6DOF.ObjectClusterSensorName.AXIS_ANGLE_6DOF_Y;
            public static String AXIS_ANGLE_6DOF_Z = OrientationModule6DOF.ObjectClusterSensorName.AXIS_ANGLE_6DOF_Z;
            public static String AXIS_ANGLE_9DOF_A = OrientationModule9DOF.ObjectClusterSensorName.AXIS_ANGLE_9DOF_A;
            public static String AXIS_ANGLE_9DOF_X = OrientationModule9DOF.ObjectClusterSensorName.AXIS_ANGLE_9DOF_X;
            public static String AXIS_ANGLE_9DOF_Y = OrientationModule9DOF.ObjectClusterSensorName.AXIS_ANGLE_9DOF_Y;
            public static String AXIS_ANGLE_9DOF_Z = OrientationModule9DOF.ObjectClusterSensorName.AXIS_ANGLE_9DOF_Z;
            public static String EULER_6DOF_YAW = OrientationModule6DOF.ObjectClusterSensorName.EULER_6DOF_YAW;
            public static String EULER_6DOF_PITCH = OrientationModule6DOF.ObjectClusterSensorName.EULER_6DOF_PITCH;
            public static String EULER_6DOF_ROLL = OrientationModule6DOF.ObjectClusterSensorName.EULER_6DOF_ROLL;
            public static String EULER_9DOF_YAW = OrientationModule9DOF.ObjectClusterSensorName.EULER_9DOF_YAW;
            public static String EULER_9DOF_PITCH = OrientationModule9DOF.ObjectClusterSensorName.EULER_9DOF_PITCH;
            public static String EULER_9DOF_ROLL = OrientationModule9DOF.ObjectClusterSensorName.EULER_9DOF_ROLL;
            public static String ECG_TO_HR_FW = SensorECGToHRFw.ObjectClusterSensorName.ECG_TO_HR_FW_GQ;
            public static String PPG_TO_HR = "PPGtoHR";
            public static String PPG_TO_HR1 = "PPGtoHR1";
            public static String PPG_TO_HR2 = "PPGtoHR2";
        }

        public static class CompatibilityInfoForMaps {
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoADXL371;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoAnyExpBoardStandardFW;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoBMP180;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoBMP280;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoBMP390;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoBattVoltage;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoBrAmp;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoExgEcg;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoExgEcgGq;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoExgEmg;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoExgGeneral;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoExgRespiration;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoExgTest;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoExgThreeUnipolar;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoExtAdcs;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoGsr;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoIntAdcsGeneral;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoIntExpA1;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoIntExpA10;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoIntExpA12;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoIntExpA13;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoIntExpA14;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoIntExpA15;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoIntExpA16;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoIntExpA17;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoKionixKXRB52042;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoKionixKXTC92050;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoLIS2DW12;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoLIS2MDL;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoLIS3MDL;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoLSM303AH;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoLSM6DSV;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoMPLSensors;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoMPU9250;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoProto3Deluxe;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoShimmer4;
            public static final ShimmerVerObject svoInShimmerTestLogAndStream;
            public static final ShimmerVerObject svoInShimmerTestSdLog;
            public static final ShimmerVerObject svoShimmer3LogAndStreamWithSDLogSyncSupport;
            public static final ShimmerVerObject svoShimmer3RLogAndStream;
            public static final ShimmerVerObject svoShimmer3RLogAndStreamWithSDLogSyncSupport;
            public static final ShimmerVerObject svoShimmer4Stock;
            public static final ShimmerVerObject svoShimmerECGmd;
            public static final ShimmerVerObject svoStrokare;
            public static final ShimmerVerObject svoSweatch;
            private static final List<ShimmerVerObject> listOfCompatibleVersionInfoAdxl377Accel200G;
            private static final List<ShimmerVerObject> listOfCompatibleVersionInfoAnyExpBoardAndFw;
            private static final List<ShimmerVerObject> listOfCompatibleVersionInfoLogging;
            private static final List<ShimmerVerObject> listOfCompatibleVersionInfoProto3Mini;
            private static final List<ShimmerVerObject> listOfCompatibleVersionInfoSdLog;
            private static final List<ShimmerVerObject> listOfCompatibleVersionInfoStreaming;
            private static final ShimmerVerObject svoAdxl377Accel200GBtStream;
            private static final ShimmerVerObject svoAdxl377Accel200GLogAndStream;
            private static final ShimmerVerObject svoAdxl377Accel200GSdLog;
            private static final ShimmerVerObject svoAnyIntExpBoardAndBtStream;
            private static final ShimmerVerObject svoAnyIntExpBoardAndFw;
            private static final ShimmerVerObject svoAnyIntExpBoardAndLogAndStream;
            private static final ShimmerVerObject svoAnyIntExpBoardAndLogAndStream3R;
            private static final ShimmerVerObject svoAnyIntExpBoardAndSdlog;
            private static final ShimmerVerObject svoArduino;
            private static final ShimmerVerObject svoBrAmpBtStream;
            private static final ShimmerVerObject svoBrAmpLogAndStream;
            private static final ShimmerVerObject svoBrAmpSdLog;
            private static final ShimmerVerObject svoBrAmpUnifiedBtStream;
            private static final ShimmerVerObject svoBrAmpUnifiedLogAndStream;
            private static final ShimmerVerObject svoBrAmpUnifiedNewImuLogAndStream;
            private static final ShimmerVerObject svoBrAmpUnifiedNewImuSdLog;
            private static final ShimmerVerObject svoBrAmpUnifiedSdLog;
            private static final ShimmerVerObject svoBtStream;
            private static final ShimmerVerObject svoExgBtStream;
            private static final ShimmerVerObject svoExgLogAndStream;
            private static final ShimmerVerObject svoExgSdLog;
            private static final ShimmerVerObject svoExgUnifiedBtStream;
            private static final ShimmerVerObject svoExgUnifiedLogAndStream;
            private static final ShimmerVerObject svoExgUnifiedNewImuLogAndStream;
            private static final ShimmerVerObject svoExgUnifiedNewImuSdLog;
            private static final ShimmerVerObject svoExgUnifiedSdLog;
            private static final ShimmerVerObject svoGsrBtStream;
            private static final ShimmerVerObject svoGsrGqBle;
            private static final ShimmerVerObject svoGsrLogAndStream;
            private static final ShimmerVerObject svoGsrSdLog;
            private static final ShimmerVerObject svoGsrUnifiedBtStream;
            private static final ShimmerVerObject svoGsrUnifiedGqBle;
            private static final ShimmerVerObject svoGsrUnifiedLogAndStream;
            private static final ShimmerVerObject svoGsrUnifiedNewImuLogAndStream;
            private static final ShimmerVerObject svoGsrUnifiedNewImuSdLog;
            private static final ShimmerVerObject svoGsrUnifiedSdLog;
            private static final ShimmerVerObject svoLogAndStream;
            private static final ShimmerVerObject svoNewImuAnyExpBrdLogAndStream;
            private static final ShimmerVerObject svoNewImuAnyExpBrdSdLog;
            private static final ShimmerVerObject svoNewImuLogAndStream;
            private static final ShimmerVerObject svoNewImuSdLog;
            private static final ShimmerVerObject svoNoIntExpBoardSdLog;
            private static final ShimmerVerObject svoProto3DeluxeBtStream;
            private static final ShimmerVerObject svoProto3DeluxeLogAndStream;
            private static final ShimmerVerObject svoProto3DeluxeNewImuLogAndStream;
            private static final ShimmerVerObject svoProto3DeluxeNewImuSdLog;
            private static final ShimmerVerObject svoProto3DeluxeSdLog;
            private static final ShimmerVerObject svoProto3MiniBtStream;
            private static final ShimmerVerObject svoProto3MiniLogAndStream;
            private static final ShimmerVerObject svoProto3MiniNewImuLogAndStream;
            private static final ShimmerVerObject svoProto3MiniNewImuSdLog;
            private static final ShimmerVerObject svoProto3MiniSdLog;
            private static final ShimmerVerObject svoSdLog;
            private static final ShimmerVerObject svoSdLogMpl;
            private static final ShimmerVerObject svoShimmer2rGq;
            private static final ShimmerVerObject svoShimmer3RAdxl377Accel200GLogAndStream;
            private static final ShimmerVerObject svoShimmer3RBrAmpUnifiedLogAndStream;
            private static final ShimmerVerObject svoShimmer3RExgUnifiedLogAndStream;
            private static final ShimmerVerObject svoShimmer3RGsrNewImuLogAndStream;
            private static final ShimmerVerObject svoShimmer3RGsrUnifiedLogAndStream;
            private static final ShimmerVerObject svoShimmer3RImuAnyExpBrdLogAndStream;
            private static final ShimmerVerObject svoShimmer3RImuLogAndStream;
            private static final ShimmerVerObject svoShimmer3RProto3DeluxeLogAndStream;
            private static final ShimmerVerObject svoShimmerGq802154Lr;
            private static final ShimmerVerObject svoShimmerGq802154Nr;

            static {
                ShimmerVerObject shimmerVerObject = new ShimmerVerObject(-1, -1, -1, -1, -1, -1);
                svoAnyIntExpBoardAndFw = shimmerVerObject;
                ShimmerVerObject shimmerVerObject2 = new ShimmerVerObject(3, 2, -1, -1, -1, -1);
                svoAnyIntExpBoardAndSdlog = shimmerVerObject2;
                ShimmerVerObject shimmerVerObject3 = new ShimmerVerObject(3, 1, -1, -1, -1, -1);
                svoAnyIntExpBoardAndBtStream = shimmerVerObject3;
                ShimmerVerObject shimmerVerObject4 = new ShimmerVerObject(3, 3, -1, -1, -1, -1);
                svoAnyIntExpBoardAndLogAndStream = shimmerVerObject4;
                ShimmerVerObject shimmerVerObject5 = new ShimmerVerObject(10, 3, -1, -1, -1, -1);
                svoAnyIntExpBoardAndLogAndStream3R = shimmerVerObject5;
                svoNoIntExpBoardSdLog = new ShimmerVerObject(3, 2, 0, 8, 0, 255);
                ShimmerVerObject shimmerVerObject6 = new ShimmerVerObject(3, 2, 0, 8, 0, -1);
                svoSdLog = shimmerVerObject6;
                ShimmerVerObject shimmerVerObject7 = new ShimmerVerObject(3, 2, 0, 10, 1, -1);
                svoSdLogMpl = shimmerVerObject7;
                ShimmerVerObject shimmerVerObject8 = new ShimmerVerObject(3, 1, 0, 5, 0, -1);
                svoBtStream = shimmerVerObject8;
                ShimmerVerObject shimmerVerObject9 = new ShimmerVerObject(3, 3, 0, 3, 3, -1);
                svoLogAndStream = shimmerVerObject9;
                ShimmerVerObject shimmerVerObject10 = new ShimmerVerObject(3, 2, 0, 15, 1, 31, 6);
                svoNewImuSdLog = shimmerVerObject10;
                ShimmerVerObject shimmerVerObject11 = new ShimmerVerObject(3, 3, 0, 8, 1, 31, 6);
                svoNewImuLogAndStream = shimmerVerObject11;
                ShimmerVerObject shimmerVerObject12 = new ShimmerVerObject(3, 2, 0, 15, 1, -1, -1, 171);
                svoNewImuAnyExpBrdSdLog = shimmerVerObject12;
                ShimmerVerObject shimmerVerObject13 = new ShimmerVerObject(3, 3, 0, 8, 1, -1, -1, 6);
                svoNewImuAnyExpBrdLogAndStream = shimmerVerObject13;
                ShimmerVerObject shimmerVerObject14 = new ShimmerVerObject(10, 3, 0, 1, 0, 31, 6);
                svoShimmer3RImuLogAndStream = shimmerVerObject14;
                ShimmerVerObject shimmerVerObject15 = new ShimmerVerObject(10, 3, 0, 1, 0, -1, -1, 6);
                svoShimmer3RImuAnyExpBrdLogAndStream = shimmerVerObject15;
                ShimmerVerObject shimmerVerObject16 = new ShimmerVerObject(10, 3, -1, -1, -1, -1);
                svoShimmer3RLogAndStream = shimmerVerObject16;
                svoShimmer3LogAndStreamWithSDLogSyncSupport = new ShimmerVerObject(3, 3, 0, 16, 11, -1);
                svoShimmer3RLogAndStreamWithSDLogSyncSupport = new ShimmerVerObject(10, 3, -1, -1, -1, -1);
                ShimmerVerObject shimmerVerObject17 = new ShimmerVerObject(56, -1, -1, -1, -1, -1);
                svoShimmerGq802154Lr = shimmerVerObject17;
                ShimmerVerObject shimmerVerObject18 = new ShimmerVerObject(57, -1, -1, -1, -1, -1);
                svoShimmerGq802154Nr = shimmerVerObject18;
                ShimmerVerObject shimmerVerObject19 = new ShimmerVerObject(9, -1, -1, -1, -1, -1);
                svoShimmer2rGq = shimmerVerObject19;
                ShimmerVerObject shimmerVerObject20 = new ShimmerVerObject(3, 2, 0, 8, 0, 37);
                svoExgSdLog = shimmerVerObject20;
                ShimmerVerObject shimmerVerObject21 = new ShimmerVerObject(3, 2, 0, 8, 0, 47);
                svoExgUnifiedSdLog = shimmerVerObject21;
                ShimmerVerObject shimmerVerObject22 = new ShimmerVerObject(3, 2, 0, 15, 1, 47, 3);
                svoExgUnifiedNewImuSdLog = shimmerVerObject22;
                ShimmerVerObject shimmerVerObject23 = new ShimmerVerObject(3, 1, 0, 5, 0, 37);
                svoExgBtStream = shimmerVerObject23;
                ShimmerVerObject shimmerVerObject24 = new ShimmerVerObject(3, 1, 0, 5, 0, 47);
                svoExgUnifiedBtStream = shimmerVerObject24;
                ShimmerVerObject shimmerVerObject25 = new ShimmerVerObject(3, 3, 0, 3, 3, 37);
                svoExgLogAndStream = shimmerVerObject25;
                ShimmerVerObject shimmerVerObject26 = new ShimmerVerObject(3, 3, 0, 3, 3, 47);
                svoExgUnifiedLogAndStream = shimmerVerObject26;
                ShimmerVerObject shimmerVerObject27 = new ShimmerVerObject(3, 3, 0, 8, 1, 47, 3);
                svoExgUnifiedNewImuLogAndStream = shimmerVerObject27;
                ShimmerVerObject shimmerVerObject28 = new ShimmerVerObject(10, 3, 0, 1, 0, 47, 3);
                svoShimmer3RExgUnifiedLogAndStream = shimmerVerObject28;
                ShimmerVerObject shimmerVerObject29 = new ShimmerVerObject(3, 2, 0, 8, 0, 14);
                svoGsrSdLog = shimmerVerObject29;
                ShimmerVerObject shimmerVerObject30 = new ShimmerVerObject(3, 2, 0, 8, 0, 48);
                svoGsrUnifiedSdLog = shimmerVerObject30;
                ShimmerVerObject shimmerVerObject31 = new ShimmerVerObject(3, 2, 0, 15, 1, 48, 3);
                svoGsrUnifiedNewImuSdLog = shimmerVerObject31;
                ShimmerVerObject shimmerVerObject32 = new ShimmerVerObject(3, 1, 0, 5, 0, 14);
                svoGsrBtStream = shimmerVerObject32;
                ShimmerVerObject shimmerVerObject33 = new ShimmerVerObject(3, 1, 0, 5, 0, 48);
                svoGsrUnifiedBtStream = shimmerVerObject33;
                ShimmerVerObject shimmerVerObject34 = new ShimmerVerObject(3, 3, 0, 3, 3, 14);
                svoGsrLogAndStream = shimmerVerObject34;
                ShimmerVerObject shimmerVerObject35 = new ShimmerVerObject(3, 3, 0, 3, 3, 48);
                svoGsrUnifiedLogAndStream = shimmerVerObject35;
                ShimmerVerObject shimmerVerObject36 = new ShimmerVerObject(3, 3, 0, 8, 1, 48, 3);
                svoGsrUnifiedNewImuLogAndStream = shimmerVerObject36;
                ShimmerVerObject shimmerVerObject37 = new ShimmerVerObject(3, 5, -1, -1, -1, 14);
                svoGsrGqBle = shimmerVerObject37;
                ShimmerVerObject shimmerVerObject38 = new ShimmerVerObject(3, 5, -1, -1, -1, 48);
                svoGsrUnifiedGqBle = shimmerVerObject38;
                ShimmerVerObject shimmerVerObject39 = new ShimmerVerObject(10, 3, 0, 1, 0, 48, 3);
                svoShimmer3RGsrUnifiedLogAndStream = shimmerVerObject39;
                ShimmerVerObject shimmerVerObject40 = new ShimmerVerObject(10, 3, -1, -1, -1, 14);
                svoShimmer3RGsrNewImuLogAndStream = shimmerVerObject40;
                ShimmerVerObject shimmerVerObject41 = new ShimmerVerObject(3, 2, 0, 8, 0, 8);
                svoBrAmpSdLog = shimmerVerObject41;
                ShimmerVerObject shimmerVerObject42 = new ShimmerVerObject(3, 2, 0, 8, 0, 49);
                svoBrAmpUnifiedSdLog = shimmerVerObject42;
                ShimmerVerObject shimmerVerObject43 = new ShimmerVerObject(3, 2, 0, 15, 1, 49, 3);
                svoBrAmpUnifiedNewImuSdLog = shimmerVerObject43;
                ShimmerVerObject shimmerVerObject44 = new ShimmerVerObject(3, 1, 0, 5, 0, 8);
                svoBrAmpBtStream = shimmerVerObject44;
                ShimmerVerObject shimmerVerObject45 = new ShimmerVerObject(3, 1, 0, 5, 0, 49);
                svoBrAmpUnifiedBtStream = shimmerVerObject45;
                ShimmerVerObject shimmerVerObject46 = new ShimmerVerObject(3, 3, 0, 3, 3, 8);
                svoBrAmpLogAndStream = shimmerVerObject46;
                ShimmerVerObject shimmerVerObject47 = new ShimmerVerObject(3, 3, 0, 3, 3, 49);
                svoBrAmpUnifiedLogAndStream = shimmerVerObject47;
                ShimmerVerObject shimmerVerObject48 = new ShimmerVerObject(3, 3, 0, 8, 1, 49, 3);
                svoBrAmpUnifiedNewImuLogAndStream = shimmerVerObject48;
                ShimmerVerObject shimmerVerObject49 = new ShimmerVerObject(10, 3, 0, 1, 0, 49, 3);
                svoShimmer3RBrAmpUnifiedLogAndStream = shimmerVerObject49;
                ShimmerVerObject shimmerVerObject50 = new ShimmerVerObject(3, 2, 0, 8, 0, 36);
                svoProto3MiniSdLog = shimmerVerObject50;
                ShimmerVerObject shimmerVerObject51 = new ShimmerVerObject(3, 2, 0, 15, 1, 36, 3);
                svoProto3MiniNewImuSdLog = shimmerVerObject51;
                ShimmerVerObject shimmerVerObject52 = new ShimmerVerObject(3, 1, 0, 5, 0, 36);
                svoProto3MiniBtStream = shimmerVerObject52;
                ShimmerVerObject shimmerVerObject53 = new ShimmerVerObject(3, 3, 0, 3, 3, 36);
                svoProto3MiniLogAndStream = shimmerVerObject53;
                ShimmerVerObject shimmerVerObject54 = new ShimmerVerObject(3, 3, 0, 8, 1, 36, 3);
                svoProto3MiniNewImuLogAndStream = shimmerVerObject54;
                ShimmerVerObject shimmerVerObject55 = new ShimmerVerObject(3, 2, 0, 8, 0, 38);
                svoProto3DeluxeSdLog = shimmerVerObject55;
                ShimmerVerObject shimmerVerObject56 = new ShimmerVerObject(3, 2, 0, 15, 1, 38, 3);
                svoProto3DeluxeNewImuSdLog = shimmerVerObject56;
                ShimmerVerObject shimmerVerObject57 = new ShimmerVerObject(3, 1, 0, 5, 0, 38);
                svoProto3DeluxeBtStream = shimmerVerObject57;
                ShimmerVerObject shimmerVerObject58 = new ShimmerVerObject(3, 3, 0, 3, 3, 38);
                svoProto3DeluxeLogAndStream = shimmerVerObject58;
                ShimmerVerObject shimmerVerObject59 = new ShimmerVerObject(3, 3, 0, 8, 1, 38, 3);
                svoProto3DeluxeNewImuLogAndStream = shimmerVerObject59;
                ShimmerVerObject shimmerVerObject60 = new ShimmerVerObject(10, 3, 0, 1, 0, 38, 3);
                svoShimmer3RProto3DeluxeLogAndStream = shimmerVerObject60;
                ShimmerVerObject shimmerVerObject61 = new ShimmerVerObject(3, 2, 0, 8, 0, 44);
                svoAdxl377Accel200GSdLog = shimmerVerObject61;
                ShimmerVerObject shimmerVerObject62 = new ShimmerVerObject(3, 1, 0, 5, 0, 44);
                svoAdxl377Accel200GBtStream = shimmerVerObject62;
                ShimmerVerObject shimmerVerObject63 = new ShimmerVerObject(3, 3, 0, 3, 3, 44);
                svoAdxl377Accel200GLogAndStream = shimmerVerObject63;
                ShimmerVerObject shimmerVerObject64 = new ShimmerVerObject(10, 3, 0, 1, 0, 44);
                svoShimmer3RAdxl377Accel200GLogAndStream = shimmerVerObject64;
                svoInShimmerTestLogAndStream = new ShimmerVerObject(3, 3, 0, 16, 7);
                svoInShimmerTestSdLog = new ShimmerVerObject(3, 2, 0, 22, 7);
                ShimmerVerObject shimmerVerObject65 = new ShimmerVerObject(58, 12, -1, -1, -1, -1);
                svoShimmer4Stock = shimmerVerObject65;
                ShimmerVerObject shimmerVerObject66 = new ShimmerVerObject(1003, -1, -1, -1, -1, -1);
                svoArduino = shimmerVerObject66;
                ShimmerVerObject shimmerVerObject67 = new ShimmerVerObject(4, -1, -1, -1, -1, -1);
                svoSweatch = shimmerVerObject67;
                ShimmerVerObject shimmerVerObject68 = new ShimmerVerObject(3, -1, -1, -1, -1, 59);
                svoShimmerECGmd = shimmerVerObject68;
                ShimmerVerObject shimmerVerObject69 = new ShimmerVerObject(3, 15, -1, -1, -1, -1);
                svoStrokare = shimmerVerObject69;
                listOfCompatibleVersionInfoExgGeneral = Arrays.asList(shimmerVerObject20, shimmerVerObject23, shimmerVerObject25, shimmerVerObject21, shimmerVerObject24, shimmerVerObject26, shimmerVerObject17, shimmerVerObject18, shimmerVerObject19, shimmerVerObject68, shimmerVerObject65, shimmerVerObject69, shimmerVerObject28);
                listOfCompatibleVersionInfoExgEmg = Arrays.asList(shimmerVerObject20, shimmerVerObject23, shimmerVerObject25, shimmerVerObject21, shimmerVerObject24, shimmerVerObject26, shimmerVerObject68, shimmerVerObject65, shimmerVerObject69, shimmerVerObject28);
                listOfCompatibleVersionInfoExgEcg = Arrays.asList(shimmerVerObject20, shimmerVerObject23, shimmerVerObject25, shimmerVerObject21, shimmerVerObject24, shimmerVerObject26, shimmerVerObject17, shimmerVerObject18, shimmerVerObject19, shimmerVerObject68, shimmerVerObject65, shimmerVerObject28);
                listOfCompatibleVersionInfoExgEcgGq = Arrays.asList(shimmerVerObject17, shimmerVerObject18, shimmerVerObject19);
                listOfCompatibleVersionInfoExgTest = Arrays.asList(shimmerVerObject20, shimmerVerObject23, shimmerVerObject25, shimmerVerObject21, shimmerVerObject24, shimmerVerObject26, shimmerVerObject17, shimmerVerObject18, shimmerVerObject19, shimmerVerObject68, shimmerVerObject65, shimmerVerObject28);
                listOfCompatibleVersionInfoExgThreeUnipolar = Arrays.asList(shimmerVerObject20, shimmerVerObject23, shimmerVerObject25, shimmerVerObject21, shimmerVerObject24, shimmerVerObject26, shimmerVerObject17, shimmerVerObject18, shimmerVerObject19, shimmerVerObject68, shimmerVerObject65, shimmerVerObject28);
                listOfCompatibleVersionInfoExgRespiration = Arrays.asList(shimmerVerObject21, shimmerVerObject24, shimmerVerObject26, shimmerVerObject65, shimmerVerObject28);
                listOfCompatibleVersionInfoSdLog = Arrays.asList(shimmerVerObject6);
                listOfCompatibleVersionInfoAnyExpBoardAndFw = Arrays.asList(shimmerVerObject);
                listOfCompatibleVersionInfoAnyExpBoardStandardFW = Arrays.asList(shimmerVerObject2, shimmerVerObject3, shimmerVerObject4, shimmerVerObject17, shimmerVerObject18, shimmerVerObject19, shimmerVerObject5, shimmerVerObject65, shimmerVerObject66, shimmerVerObject67, shimmerVerObject69);
                listOfCompatibleVersionInfoBattVoltage = Arrays.asList(shimmerVerObject2, shimmerVerObject3, shimmerVerObject4, shimmerVerObject5, shimmerVerObject65, shimmerVerObject67);
                listOfCompatibleVersionInfoGsr = Arrays.asList(shimmerVerObject29, shimmerVerObject32, shimmerVerObject34, shimmerVerObject37, shimmerVerObject30, shimmerVerObject33, shimmerVerObject35, shimmerVerObject38, shimmerVerObject17, shimmerVerObject18, shimmerVerObject19, shimmerVerObject65, shimmerVerObject39, shimmerVerObject40);
                listOfCompatibleVersionInfoBMP180 = Arrays.asList(shimmerVerObject2, shimmerVerObject3, shimmerVerObject4, shimmerVerObject65);
                listOfCompatibleVersionInfoBMP280 = Arrays.asList(shimmerVerObject10, shimmerVerObject11, shimmerVerObject16, shimmerVerObject12, shimmerVerObject13, shimmerVerObject31, shimmerVerObject36, shimmerVerObject22, shimmerVerObject27, shimmerVerObject43, shimmerVerObject48, shimmerVerObject51, shimmerVerObject54, shimmerVerObject56, shimmerVerObject59, shimmerVerObject65);
                listOfCompatibleVersionInfoBMP390 = Arrays.asList(shimmerVerObject14, shimmerVerObject16, shimmerVerObject15, shimmerVerObject39, shimmerVerObject28, shimmerVerObject49, shimmerVerObject60, shimmerVerObject65);
                listOfCompatibleVersionInfoMPU9250 = Arrays.asList(shimmerVerObject10, shimmerVerObject11, shimmerVerObject16, shimmerVerObject12, shimmerVerObject13, shimmerVerObject31, shimmerVerObject36, shimmerVerObject22, shimmerVerObject27, shimmerVerObject43, shimmerVerObject48, shimmerVerObject51, shimmerVerObject54, shimmerVerObject56, shimmerVerObject59, shimmerVerObject65);
                listOfCompatibleVersionInfoLSM303AH = Arrays.asList(shimmerVerObject10, shimmerVerObject11, shimmerVerObject16, shimmerVerObject12, shimmerVerObject13, shimmerVerObject31, shimmerVerObject36, shimmerVerObject22, shimmerVerObject27, shimmerVerObject43, shimmerVerObject48, shimmerVerObject51, shimmerVerObject54, shimmerVerObject56, shimmerVerObject59);
                listOfCompatibleVersionInfoLIS2MDL = Arrays.asList(shimmerVerObject14, shimmerVerObject16, shimmerVerObject15, shimmerVerObject39, shimmerVerObject28, shimmerVerObject49, shimmerVerObject60);
                listOfCompatibleVersionInfoLIS3MDL = Arrays.asList(shimmerVerObject14, shimmerVerObject16, shimmerVerObject15, shimmerVerObject39, shimmerVerObject28, shimmerVerObject49, shimmerVerObject60);
                listOfCompatibleVersionInfoADXL371 = Arrays.asList(shimmerVerObject14, shimmerVerObject16, shimmerVerObject15, shimmerVerObject39, shimmerVerObject28, shimmerVerObject49, shimmerVerObject60);
                listOfCompatibleVersionInfoKionixKXRB52042 = Arrays.asList(shimmerVerObject2, shimmerVerObject3, shimmerVerObject4, shimmerVerObject65);
                listOfCompatibleVersionInfoKionixKXTC92050 = Arrays.asList(shimmerVerObject10, shimmerVerObject11, shimmerVerObject16, shimmerVerObject12, shimmerVerObject13, shimmerVerObject31, shimmerVerObject36, shimmerVerObject22, shimmerVerObject27, shimmerVerObject43, shimmerVerObject48, shimmerVerObject51, shimmerVerObject54, shimmerVerObject56, shimmerVerObject59);
                listOfCompatibleVersionInfoLSM6DSV = Arrays.asList(shimmerVerObject14, shimmerVerObject16, shimmerVerObject15, shimmerVerObject39, shimmerVerObject28, shimmerVerObject49, shimmerVerObject60);
                listOfCompatibleVersionInfoLIS2DW12 = Arrays.asList(shimmerVerObject14, shimmerVerObject16, shimmerVerObject15, shimmerVerObject39, shimmerVerObject28, shimmerVerObject49, shimmerVerObject60);
                listOfCompatibleVersionInfoBrAmp = Arrays.asList(shimmerVerObject41, shimmerVerObject44, shimmerVerObject46, shimmerVerObject42, shimmerVerObject45, shimmerVerObject47, shimmerVerObject49, shimmerVerObject65);
                listOfCompatibleVersionInfoProto3Mini = Arrays.asList(shimmerVerObject50, shimmerVerObject52, shimmerVerObject53);
                listOfCompatibleVersionInfoProto3Deluxe = Arrays.asList(shimmerVerObject55, shimmerVerObject57, shimmerVerObject58, shimmerVerObject60, shimmerVerObject65);
                listOfCompatibleVersionInfoIntExpA1 = Arrays.asList(shimmerVerObject50, shimmerVerObject52, shimmerVerObject53, shimmerVerObject55, shimmerVerObject57, shimmerVerObject58, shimmerVerObject65);
                listOfCompatibleVersionInfoIntExpA12 = Arrays.asList(shimmerVerObject50, shimmerVerObject52, shimmerVerObject53, shimmerVerObject55, shimmerVerObject57, shimmerVerObject58, shimmerVerObject29, shimmerVerObject32, shimmerVerObject34, shimmerVerObject30, shimmerVerObject33, shimmerVerObject35, shimmerVerObject61, shimmerVerObject62, shimmerVerObject63, shimmerVerObject65);
                listOfCompatibleVersionInfoIntExpA13 = Arrays.asList(shimmerVerObject50, shimmerVerObject52, shimmerVerObject53, shimmerVerObject55, shimmerVerObject57, shimmerVerObject58, shimmerVerObject29, shimmerVerObject32, shimmerVerObject34, shimmerVerObject30, shimmerVerObject33, shimmerVerObject35, shimmerVerObject61, shimmerVerObject62, shimmerVerObject63, shimmerVerObject65);
                listOfCompatibleVersionInfoIntExpA14 = Arrays.asList(shimmerVerObject50, shimmerVerObject52, shimmerVerObject53, shimmerVerObject55, shimmerVerObject57, shimmerVerObject58, shimmerVerObject61, shimmerVerObject62, shimmerVerObject63, shimmerVerObject65);
                listOfCompatibleVersionInfoIntExpA10 = Arrays.asList(shimmerVerObject60, shimmerVerObject40, shimmerVerObject39, shimmerVerObject64, shimmerVerObject65);
                listOfCompatibleVersionInfoIntExpA15 = Arrays.asList(shimmerVerObject60, shimmerVerObject40, shimmerVerObject39, shimmerVerObject64, shimmerVerObject65);
                listOfCompatibleVersionInfoIntExpA16 = Arrays.asList(shimmerVerObject60, shimmerVerObject64, shimmerVerObject65);
                listOfCompatibleVersionInfoIntExpA17 = Arrays.asList(shimmerVerObject60, shimmerVerObject64, shimmerVerObject65);
                listOfCompatibleVersionInfoIntAdcsGeneral = Arrays.asList(shimmerVerObject29, shimmerVerObject32, shimmerVerObject34, shimmerVerObject30, shimmerVerObject33, shimmerVerObject35, shimmerVerObject39, shimmerVerObject40, shimmerVerObject65);
                listOfCompatibleVersionInfoExtAdcs = Arrays.asList(shimmerVerObject2, shimmerVerObject3, shimmerVerObject4, shimmerVerObject5, shimmerVerObject65);
                listOfCompatibleVersionInfoStreaming = Arrays.asList(shimmerVerObject8, shimmerVerObject9, shimmerVerObject65);
                listOfCompatibleVersionInfoLogging = Arrays.asList(shimmerVerObject6, shimmerVerObject9, shimmerVerObject65, shimmerVerObject69);
                listOfCompatibleVersionInfoAdxl377Accel200G = Arrays.asList(shimmerVerObject61, shimmerVerObject62, shimmerVerObject63);
                listOfCompatibleVersionInfoMPLSensors = Arrays.asList(shimmerVerObject7);
                listOfCompatibleVersionInfoShimmer4 = Arrays.asList(shimmerVerObject65);
            }
        }

        public class Channel {
            public static final int BridgeAmpHigh = 39;
            public static final int BridgeAmpLow = 40;
            public static final int EXG_ADS1292R_1_CH1_16BIT = 35;
            public static final int EXG_ADS1292R_1_CH1_24BIT = 30;
            public static final int EXG_ADS1292R_1_CH2_16BIT = 36;
            public static final int EXG_ADS1292R_1_CH2_24BIT = 31;
            public static final int EXG_ADS1292R_1_STATUS = 29;
            public static final int EXG_ADS1292R_2_CH1_16BIT = 37;
            public static final int EXG_ADS1292R_2_CH1_24BIT = 33;
            public static final int EXG_ADS1292R_2_CH2_16BIT = 38;
            public static final int EXG_ADS1292R_2_CH2_24BIT = 34;
            public static final int EXG_ADS1292R_2_STATUS = 32;
            public static final int ExtAdc11 = 14;
            public static final int ExtAdc12 = 15;
            public static final int ExtAdc15 = 15;
            public static final int ExtAdc6 = 14;
            public static final int ExtAdc7 = 13;
            public static final int ExtAdc9 = 13;
            public static final int GsrRaw = 28;
            public static final int IntAdc1 = 16;
            public static final int IntAdc10 = 17;
            public static final int IntAdc12 = 17;
            public static final int IntAdc13 = 18;
            public static final int IntAdc14 = 19;
            public static final int IntAdc15 = 18;
            public static final int IntAdc16 = 19;
            public static final int IntAdc17 = 16;
            public static final int Pressure = 27;
            public static final int Temperature = 26;
            public static final int VBatt = 3;
            public static final int XAAccel = 0;
            public static final int XAlterAccel = 20;
            public static final int XAlterMag = 23;
            public static final int XDAccel = 4;
            public static final int XGyro = 10;
            public static final int XMag = 7;
            public static final int YAAccel = 1;
            public static final int YAlterAccel = 21;
            public static final int YAlterMag = 24;
            public static final int YDAccel = 5;
            public static final int YGyro = 11;
            public static final int YMag = 8;
            public static final int ZAAccel = 2;
            public static final int ZAlterAccel = 22;
            public static final int ZAlterMag = 25;
            public static final int ZDAccel = 6;
            public static final int ZGyro = 12;
            public static final int ZMag = 9;

            public Channel() {
            }
        }

        public class SensorBitmap {
            public static final int SENSOR_ALT_ACCEL = 4194304;
            public static final int SENSOR_ALT_MAG = 2097152;
            public static final int SENSOR_A_ACCEL = 128;
            public static final int SENSOR_BMP180 = 262144;
            public static final int SENSOR_BRIDGE_AMP = 32768;
            public static final int SENSOR_D_ACCEL = 4096;
            public static final int SENSOR_EXG1_16BIT = 1048576;
            public static final int SENSOR_EXG1_24BIT = 16;
            public static final int SENSOR_EXG2_16BIT = 524288;
            public static final int SENSOR_EXG2_24BIT = 8;
            public static final int SENSOR_EXT_A15 = 2048;
            public static final int SENSOR_EXT_A6 = 1;
            public static final int SENSOR_EXT_A7 = 2;
            public static final int SENSOR_GSR = 4;
            public static final int SENSOR_GYRO = 64;
            public static final int SENSOR_INT_A1 = 1024;
            public static final int SENSOR_INT_A12 = 512;
            public static final int SENSOR_INT_A13 = 256;
            public static final int SENSOR_INT_A14 = 8388608;
            public static final int SENSOR_MAG = 32;
            public static final int SENSOR_VBATT = 8192;

            public SensorBitmap() {
            }
        }

        public class SENSOR_ID {
            public static final int HOST_CPU_USAGE = 1004;
            public static final int HOST_ECG = 100;
            public static final int HOST_EMG = 101;
            public static final int HOST_EXG_CUSTOM = 116;
            public static final int HOST_EXG_RESPIRATION = 103;
            public static final int HOST_EXG_TEST = 102;
            public static final int HOST_EXG_THREE_UNIPOLAR = 106;
            public static final int HOST_KEYBOARD_LISTENER = 1001;
            public static final int HOST_MOUSE_LISTENER = 1002;
            public static final int HOST_PPG1_A12 = 9;
            public static final int HOST_PPG1_A13 = 12;
            public static final int HOST_PPG1_DUMMY = 110;
            public static final int HOST_PPG2_A1 = 18;
            public static final int HOST_PPG2_A14 = 14;
            public static final int HOST_PPG2_DUMMY = 113;
            public static final int HOST_PPG_A12 = 8;
            public static final int HOST_PPG_A13 = 11;
            public static final int HOST_PPG_DUMMY = 105;
            public static final int HOST_REAL_TIME_CLOCK = 152;
            public static final int HOST_REAL_TIME_CLOCK_SYNC = 153;
            public static final int HOST_SHIMMER_STREAMING_PROPERTIES = -100;
            public static final int HOST_SKIN_TEMPERATURE_PROBE = 104;
            public static final int HOST_SYSTEM_TIMESTAMP = -101;
            public static final int HOST_TIMESTAMP_SYNC = 151;
            public static final int HOST_WEBCAM = 1003;
            public static final int RESERVED_ANY_SENSOR = -1;
            public static final int SHIMMER_ADXL371_ACCEL_HIGHG = 40;
            public static final int SHIMMER_ANALOG_ACCEL = 2;
            public static final int SHIMMER_BMP390_PRESSURE = 43;
            public static final int SHIMMER_BMPX80_PRESSURE = 36;
            public static final int SHIMMER_BRIDGE_AMP = 15;
            public static final int SHIMMER_ECG_TO_HR_FW = 150;
            public static final int SHIMMER_EXT_EXP_ADC_A15 = 6;
            public static final int SHIMMER_EXT_EXP_ADC_A6 = 5;
            public static final int SHIMMER_EXT_EXP_ADC_A7 = 4;
            public static final int SHIMMER_GSR = 19;
            public static final int SHIMMER_INT_EXP_ADC_A1 = 17;
            public static final int SHIMMER_INT_EXP_ADC_A12 = 7;
            public static final int SHIMMER_INT_EXP_ADC_A13 = 10;
            public static final int SHIMMER_INT_EXP_ADC_A14 = 13;
            public static final int SHIMMER_LIS2DW12_ACCEL_WR = 39;
            public static final int SHIMMER_LIS2MDL_MAG = 42;
            public static final int SHIMMER_LIS3MDL_MAG_ALT = 41;
            public static final int SHIMMER_LSM303_ACCEL = 31;
            public static final int SHIMMER_LSM303_MAG = 32;
            public static final int SHIMMER_LSM6DSV_ACCEL_LN = 37;
            public static final int SHIMMER_LSM6DSV_GYRO = 38;
            public static final int SHIMMER_MPU9X50_ACCEL = 33;
            public static final int SHIMMER_MPU9X50_GYRO = 30;
            public static final int SHIMMER_MPU9X50_MAG = 34;
            public static final int SHIMMER_MPU9X50_MPL_ACCEL = 59;
            public static final int SHIMMER_MPU9X50_MPL_EULER_6DOF = 52;
            public static final int SHIMMER_MPU9X50_MPL_EULER_9DOF = 53;
            public static final int SHIMMER_MPU9X50_MPL_GYRO = 58;
            public static final int SHIMMER_MPU9X50_MPL_HEADING = 54;
            public static final int SHIMMER_MPU9X50_MPL_MAG = 60;
            public static final int SHIMMER_MPU9X50_MPL_MOTION_ORIENT = 57;
            public static final int SHIMMER_MPU9X50_MPL_PEDOMETER = 55;
            public static final int SHIMMER_MPU9X50_MPL_QUAT_6DOF = 50;
            public static final int SHIMMER_MPU9X50_MPL_QUAT_6DOF_RAW = 61;
            public static final int SHIMMER_MPU9X50_MPL_QUAT_9DOF = 51;
            public static final int SHIMMER_MPU9X50_MPL_TAP = 56;
            public static final int SHIMMER_MPU9X50_TEMP = 35;
            public static final int SHIMMER_RESISTANCE_AMP = 16;
            public static final int SHIMMER_STC3100 = 62;
            public static final int SHIMMER_TIMESTAMP = -200;
            public static final int SHIMMER_VBATT = 3;
            public static final int THIRD_PARTY_NONIN = 1000;

            public SENSOR_ID() {
            }
        }

        public class DerivedSensorsBitMask {
            public static final int ACTIVITY_MODULE = 256;
            public static final int ECG2HR_CHIP1_CH1 = 32768;
            public static final int ECG2HR_CHIP1_CH2 = 16384;
            public static final int ECG2HR_CHIP2_CH1 = 8192;
            public static final int ECG2HR_CHIP2_CH2 = 4096;
            public static final int ECG2HR_HRV_FREQ_DOMAIN = 1024;
            public static final int ECG2HR_HRV_TIME_DOMAIN = 2048;
            public static final int EMG_PROCESSING_CHAN1 = 33554432;
            public static final int EMG_PROCESSING_CHAN2 = 16777216;
            public static final int GAIT_MODULE = 268435456;
            public static final int GSR_BASELINE = 67108864;
            public static final int GSR_METRICS_GENERAL = 512;
            public static final int GSR_METRICS_TREND_PEAK = 134217728;
            public static final int GYRO_ON_THE_FLY_CAL = 536870912;
            public static final int ORIENTATION_6DOF_LN_EULER = 8388608;
            public static final int ORIENTATION_6DOF_LN_QUAT = 4194304;
            public static final int ORIENTATION_6DOF_WR_EULER = 524288;
            public static final int ORIENTATION_6DOF_WR_QUAT = 262144;
            public static final int ORIENTATION_9DOF_LN_EULER = 2097152;
            public static final int ORIENTATION_9DOF_LN_QUAT = 1048576;
            public static final int ORIENTATION_9DOF_WR_EULER = 131072;
            public static final int ORIENTATION_9DOF_WR_QUAT = 65536;
            public static final int PPG1_12_13 = 8;
            public static final int PPG2_1_14 = 16;
            public static final int PPG_12_13 = 4;
            public static final int PPG_TO_HR1_12_13 = 64;
            public static final int PPG_TO_HR2_1_14 = 128;
            public static final int PPG_TO_HR_12_13 = 32;
            public static final int RES_AMP = 1;
            public static final int SKIN_TEMP = 2;

            public DerivedSensorsBitMask() {
            }
        }

        public class GuiLabelConfig {
            public static final String BLUETOOTH_BAUD_RATE = "Bluetooth Baud Rate";
            public static final String BROADCAST_INTERVAL = "Broadcast Interval";
            public static final String BUFFER_SIZE = "Buffer Size";
            public static final String CALIBRATION_ALL = "Calibration all";
            public static final String CALIBRATION_PER_SENSOR = "Calibration";
            public static final String CONFIG_TIME = "Config Time";
            public static final String ENABLED_SENSORS = "Enabled Sensors Int";
            public static final String ENABLED_SENSORS_IDS = "Enabled SensorsIds";
            public static final String ENABLE_ERROR_LEDS_RTC = "RTC Error LEDs";
            public static final String ENABLE_ERROR_LEDS_SD = "SD Error LEDs";
            public static final String EXPERIMENT_DURATION_ESTIMATED = "Estimated Duration";
            public static final String EXPERIMENT_DURATION_MAXIMUM = "Maximum Duration";
            public static final String EXPERIMENT_ID = "Experiment ID";
            public static final String EXPERIMENT_MASTER_SHIMMER = "Master Shimmer";
            public static final String EXPERIMENT_NUMBER_OF_SHIMMERS = "Number Of Shimmers";
            public static final String EXPERIMENT_SYNC_WHEN_LOGGING = "Sync When Logging";
            public static final String INT_EXP_BRD_POWER_BOOLEAN = "Internal Expansion Board Power";
            public static final String INT_EXP_BRD_POWER_INTEGER = "Int Exp Power";
            public static final String KINEMATIC_LPM = "Kinematic Sensors Low-Power Mode";
            public static final String LOW_POWER_AUTOSTOP = "Low-power Autostop";
            public static final String SD_BT_STREAM_WHEN_RECORDING = "<html>SD Log and/or<br> Bluetooth Stream</html>";
            public static final String SD_STREAM_WHEN_RECORDING = "<html>SD Log Recording<br>Only</html>";
            public static final String SD_SYNC_STREAM_WHEN_RECORDING = "<html>SD Log with<br> Inter-device Sync</html>";
            public static final String SHIMMER_AND_SENSORS_SAMPLING_RATE = "Shimmer and Sensors Sampling Rate";
            public static final String SHIMMER_MAC_FROM_INFOMEM = "InfoMem MAC";
            public static final String SHIMMER_SAMPLING_RATE = "Sampling Rate";
            public static final String SHIMMER_USER_ASSIGNED_NAME = "Shimmer Name";
            public static final String SINGLE_TOUCH_START = "Single Touch Start";
            public static final String TCXO = "TCX0";
            public static final String TRIAL_NAME = "Trial Name";
            public static final String UNDOCK_START = "Undock/Dock";
            public static final String USER_BUTTON_START = "User Button";

            public GuiLabelConfig() {
            }
        }

        public class GuiLabelSensors {
            public static final String ACCEL_MPU_MPL = "MPU Accel";
            public static final String ADXL371_ACCEL_HIGHG = "High-G Accel";
            public static final String ADXL377_ACCEL_200G = "200g Accel";
            public static final String ALT_MAG_3R = "Alternate Magnetometer";
            public static final String BRAMP_HIGHGAIN = "High Gain";
            public static final String BRAMP_LOWGAIN = "Low Gain";
            public static final String BRIDGE_AMPLIFIER = "Bridge Amplifier+";
            public static final String ECG = "ECG";
            public static final String ECG_TO_HR = "ECG To HR";
            public static final String EMG = "EMG";
            public static final String EULER_ANGLES_6DOF = "Euler Angles (6DOF)";
            public static final String EULER_ANGLES_9DOF = "Euler Angles (9DOF)";
            public static final String EULER_MPL_6DOF = "MPU Euler 6DOF";
            public static final String EULER_MPL_9DOF = "MPU Euler 9DOF";
            public static final String EXG1_16BIT = "EXG1 16BIT";
            public static final String EXG1_24BIT = "EXG1 24BIT";
            public static final String EXG2_16BIT = "EXG2 16BIT";
            public static final String EXG2_24BIT = "EXG2 24BIT";
            public static final String EXG_CUSTOM = "Custom";
            public static final String EXG_RESPIRATION = "Respiration";
            public static final String EXG_TEST = "ExG Test";
            public static final String GYRO_MPU_MPL = "MPU Gyro";
            public static final String MAG = "Magnetometer";
            public static final String MAG_MPU_MPL = "MPU Mag";
            public static final String MPL_HEADING = "MPU Heading";
            public static final String MPL_MOTIONANDORIENT = "MotionAndOrient";
            public static final String MPL_PEDOM_CNT = "MPL_Pedom_cnt";
            public static final String MPL_PEDOM_TIME = "MPL_Pedom_Time";
            public static final String MPL_TAPDIRANDTAPCNT = "TapDirAndTapCnt";
            public static final String MPL_TEMPERATURE = "MPU Temp";
            public static final String ORIENTATION_3D_6DOF = "3D Orientation (6DOF)";
            public static final String ORIENTATION_3D_9DOF = "3D Orientation (9DOF)";
            public static final String PPG_TO_HR = "PPG To HR";
            public static final String QUAT_DMP_6DOF = "MPU Quat 6DOF (from DMP)";
            public static final String QUAT_MPL_6DOF = "MPU Quat 6DOF";
            public static final String QUAT_MPL_9DOF = "MPU Quat 9DOF";
            public static final String RESISTANCE_AMP = "Resistance Amp";
            public static final String SKIN_TEMP_PROBE = "Skin Temperature";

            public GuiLabelSensors() {
            }
        }
    }

    public static class ShimmerGqBle {
        public static final String[] ListofSamplingRateDividers = {"0.75Hz", "1.5Hz", "3Hz", "7.5Hz", "15Hz", "30Hz", "75Hz", "220Hz"};
        public static final Integer[] ListofSamplingRateDividersValues = {0, 1, 2, 3, 4, 5, 6, 7};
        public static final Map<String, ConfigOptionDetailsSensor> mConfigOptionsMapRef;
        public static final Map<Integer, SensorGroupingDetails> mSensorGroupingMapRef;
        public static final Map<Integer, SensorDetailsRef> mSensorMapRef;

        static {
            List<String> list;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(10, new SensorDetailsRef(0L, 0L, "Battery Voltage"));
            linkedHashMap.put(11, new SensorDetailsRef(0L, 0L, "Wide-Range Accelerometer"));
            linkedHashMap.put(5, new SensorDetailsRef(0L, 0L, "GSR"));
            linkedHashMap.put(108, new SensorDetailsRef(0L, 0L, GuiLabelSensors.BEACON));
            linkedHashMap.put(107, new SensorDetailsRef(0L, 0L, "PPG"));
            ((SensorDetailsRef) linkedHashMap.get(10)).mListOfCompatibleVersionInfo = CompatibilityInfoForMaps.listOfCompatibleVersionInfoGq;
            ((SensorDetailsRef) linkedHashMap.get(11)).mListOfCompatibleVersionInfo = CompatibilityInfoForMaps.listOfCompatibleVersionInfoGq;
            ((SensorDetailsRef) linkedHashMap.get(5)).mListOfCompatibleVersionInfo = CompatibilityInfoForMaps.listOfCompatibleVersionInfoGq;
            ((SensorDetailsRef) linkedHashMap.get(108)).mListOfCompatibleVersionInfo = CompatibilityInfoForMaps.listOfCompatibleVersionInfoGq;
            ((SensorDetailsRef) linkedHashMap.get(107)).mListOfCompatibleVersionInfo = CompatibilityInfoForMaps.listOfCompatibleVersionInfoGq;
            ((SensorDetailsRef) linkedHashMap.get(10)).mListOfConfigOptionKeysAssociated = Arrays.asList("VBATT Divider");
            ((SensorDetailsRef) linkedHashMap.get(11)).mListOfConfigOptionKeysAssociated = Arrays.asList("Wide Range Accel Range", "Wide Range Accel Rate", "Wide Range Accel Low-Power Mode", GuiLabelConfig.SAMPLING_RATE_DIVIDER_LSM303DLHC_ACCEL);
            ((SensorDetailsRef) linkedHashMap.get(5)).mListOfConfigOptionKeysAssociated = Arrays.asList("GSR Range", "GSR Divider");
            ((SensorDetailsRef) linkedHashMap.get(108)).mListOfConfigOptionKeysAssociated = Arrays.asList(GuiLabelConfig.SAMPLING_RATE_DIVIDER_BEACON);
            ((SensorDetailsRef) linkedHashMap.get(107)).mListOfConfigOptionKeysAssociated = Arrays.asList("PPG Divider");
            mSensorMapRef = Collections.unmodifiableMap(linkedHashMap);
            TreeMap treeMap = new TreeMap();
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.BATTERY_MONITORING.ordinal()), new SensorGroupingDetails(LABEL_SENSOR_TILE.BATTERY_MONITORING.getTileText(), Arrays.asList(10)));
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.WIDE_RANGE_ACCEL.ordinal()), new SensorGroupingDetails(LABEL_SENSOR_TILE.WIDE_RANGE_ACCEL.getTileText(), Arrays.asList(11)));
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.GSR.ordinal()), new SensorGroupingDetails(LABEL_SENSOR_TILE.GSR.getTileText(), Arrays.asList(5, 107)));
            treeMap.put(Integer.valueOf(LABEL_SENSOR_TILE.BEACON.ordinal()), new SensorGroupingDetails(LABEL_SENSOR_TILE.BEACON.getTileText(), Arrays.asList(108)));
            ((SensorGroupingDetails) treeMap.get(LABEL_SENSOR_TILE.BATTERY_MONITORING)).mListOfCompatibleVersionInfo = CompatibilityInfoForMaps.listOfCompatibleVersionInfoGq;
            ((SensorGroupingDetails) treeMap.get(LABEL_SENSOR_TILE.WIDE_RANGE_ACCEL)).mListOfCompatibleVersionInfo = CompatibilityInfoForMaps.listOfCompatibleVersionInfoGq;
            ((SensorGroupingDetails) treeMap.get(LABEL_SENSOR_TILE.GSR)).mListOfCompatibleVersionInfo = CompatibilityInfoForMaps.listOfCompatibleVersionInfoGq;
            ((SensorGroupingDetails) treeMap.get(LABEL_SENSOR_TILE.BEACON)).mListOfCompatibleVersionInfo = CompatibilityInfoForMaps.listOfCompatibleVersionInfoGq;
            for (SensorGroupingDetails sensorGroupingDetails : treeMap.values()) {
                sensorGroupingDetails.mListOfConfigOptionKeysAssociated.clear();
                for (Integer num : sensorGroupingDetails.mListOfSensorIdsAssociated) {
                    Map<Integer, SensorDetailsRef> map = mSensorMapRef;
                    if (map.containsKey(num) && (list = map.get(num).mListOfConfigOptionKeysAssociated) != null) {
                        for (String str : list) {
                            if (!sensorGroupingDetails.mListOfConfigOptionKeysAssociated.contains(str)) {
                                sensorGroupingDetails.mListOfConfigOptionKeysAssociated.add(str);
                            }
                        }
                    }
                }
            }
            mSensorGroupingMapRef = Collections.unmodifiableMap(treeMap);
            new LinkedHashMap();
            HashMap map2 = new HashMap();
            map2.put("Sampling Rate", new ConfigOptionDetailsSensor("Sampling Rate", ShimmerDevice.DatabaseConfigHandle.SAMPLE_RATE, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD));
            map2.put("Config Time", new ConfigOptionDetailsSensor("Config Time", ShimmerDevice.DatabaseConfigHandle.CONFIG_TIME, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD, CompatibilityInfoForMaps.listOfCompatibleVersionInfoGq));
            map2.put(Shimmer3.GuiLabelConfig.TRIAL_NAME, new ConfigOptionDetailsSensor(Shimmer3.GuiLabelConfig.TRIAL_NAME, ShimmerDevice.DatabaseConfigHandle.TRIAL_NAME, ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD, CompatibilityInfoForMaps.listOfCompatibleVersionInfoGq));
            map2.put("Wide Range Accel Range", SensorLSM303DLHC.configOptionAccelRange);
            map2.put("Wide Range Accel Rate", SensorLSM303DLHC.configOptionAccelRate);
            map2.put("GSR Range", SensorGSR.configOptionGsrRange);
            String[] strArr = ListofSamplingRateDividers;
            Integer[] numArr = ListofSamplingRateDividersValues;
            map2.put("GSR Divider", new ConfigOptionDetailsSensor("GSR Divider", null, strArr, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX));
            map2.put(GuiLabelConfig.SAMPLING_RATE_DIVIDER_LSM303DLHC_ACCEL, new ConfigOptionDetailsSensor(GuiLabelConfig.SAMPLING_RATE_DIVIDER_LSM303DLHC_ACCEL, null, strArr, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX));
            map2.put("PPG Divider", new ConfigOptionDetailsSensor("PPG Divider", null, strArr, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX));
            map2.put("VBATT Divider", new ConfigOptionDetailsSensor("VBATT Divider", null, strArr, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX));
            map2.put(GuiLabelConfig.SAMPLING_RATE_DIVIDER_BEACON, new ConfigOptionDetailsSensor(GuiLabelConfig.SAMPLING_RATE_DIVIDER_BEACON, null, strArr, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX));
            map2.put("Int Exp Power", new ConfigOptionDetailsSensor("Int Exp Power", ShimmerDevice.DatabaseConfigHandle.EXP_PWR, ShimmerDeviceCommon.ListOfOnOff, ShimmerDeviceCommon.ListOfOnOffConfigValues, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX));
            map2.put(Shimmer3.GuiLabelConfig.USER_BUTTON_START, new ConfigOptionDetailsSensor(Shimmer3.GuiLabelConfig.USER_BUTTON_START, ShimmerDevice.DatabaseConfigHandle.USER_BUTTON, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, CompatibilityInfoForMaps.listOfCompatibleVersionInfoGq));
            map2.put(Shimmer3.GuiLabelConfig.SINGLE_TOUCH_START, new ConfigOptionDetailsSensor(Shimmer3.GuiLabelConfig.SINGLE_TOUCH_START, ShimmerDevice.DatabaseConfigHandle.SINGLE_TOUCH_START, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, CompatibilityInfoForMaps.listOfCompatibleVersionInfoGq));
            map2.put("Wide Range Accel Low-Power Mode", SensorLSM303DLHC.configOptionAccelLpm);
            map2.put("Internal Expansion Board Power", new ConfigOptionDetailsSensor("Internal Expansion Board Power", null, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX));
            mConfigOptionsMapRef = Collections.unmodifiableMap(map2);
        }

        public enum LABEL_SENSOR_TILE {
            GYRO("Gyroscope"),
            MAG("Magnetometer"),
            BATTERY_MONITORING("Battery Voltage"),
            WIDE_RANGE_ACCEL("Wide-Range Accelerometer"),
            GSR(SensorGSR.LABEL_SENSOR_TILE.GSR),
            BEACON(GuiLabelSensors.BEACON);

            private String tileText;

            LABEL_SENSOR_TILE(String str) {
                this.tileText = str;
            }

            public String getTileText() {
                return this.tileText;
            }
        }

        public static class CompatibilityInfoForMaps {
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoGq;
            private static final ShimmerVerObject svoAnyIntExpBoardAndFw;

            static {
                ShimmerVerObject shimmerVerObject = new ShimmerVerObject(5, 5, -1, -1, -1, -1);
                svoAnyIntExpBoardAndFw = shimmerVerObject;
                listOfCompatibleVersionInfoGq = Arrays.asList(shimmerVerObject);
            }
        }

        public class SENSOR_ID {
            public static final int BEACON = 108;
            public static final int GSR = 5;
            public static final int LSM303DLHC_ACCEL = 11;
            public static final int MPU9150_ACCEL = 17;
            public static final int MPU9150_GYRO = 1;
            public static final int MPU9150_MAG = 18;
            public static final int PPG = 107;
            public static final int VBATT = 10;

            public SENSOR_ID() {
            }
        }

        public class GuiLabelSensors {
            public static final String ACCEL_MPU = "Alternative Accel";
            public static final String ACCEL_WR = "Wide-Range Accelerometer";
            public static final String BATTERY = "Battery Voltage";
            public static final String BEACON = "Beacon";
            public static final String GSR = "GSR";
            public static final String GYRO = "Gyroscope";
            public static final String MAG = "Magnetometer";
            public static final String MAG_MPU = "Alternative Mag";
            public static final String PPG = "PPG";
            public static final String PPG_TO_HR = "PPG To HR";

            public GuiLabelSensors() {
            }
        }

        public class GuiLabelConfig {
            public static final String CONFIG_TIME = "Config Time";
            public static final String EXPERIMENT_NAME = "Experiment Name";
            public static final String GSR_RANGE = "GSR Range";
            public static final String LSM303DLHC_ACCEL_LPM = "Wide Range Accel Low-Power Mode";
            public static final String LSM303DLHC_ACCEL_RANGE = "Wide Range Accel Range";
            public static final String LSM303DLHC_ACCEL_RATE = "Wide Range Accel Rate";
            public static final String SAMPLING_RATE_DIVIDER_BEACON = "Beacon Divider";
            public static final String SAMPLING_RATE_DIVIDER_GSR = "GSR Divider";
            public static final String SAMPLING_RATE_DIVIDER_LSM303DLHC_ACCEL = "LSM303DLHC Divider";
            public static final String SAMPLING_RATE_DIVIDER_PPG = "PPG Divider";
            public static final String SAMPLING_RATE_DIVIDER_VBATT = "VBATT Divider";
            public static final String SHIMMER_MAC_FROM_INFOMEM = "InfoMem MAC";
            public static final String SHIMMER_SAMPLING_RATE = "Sampling Rate";
            public static final String SHIMMER_USER_ASSIGNED_NAME = "Shimmer Name";

            public GuiLabelConfig() {
            }
        }
    }

    public static class Shimmer4 {
        public static final ConfigOptionDetailsSensor configOptionIntExpBrdPowerBoolean;
        public static final ConfigOptionDetailsSensor configOptionIntExpBrdPowerInteger;
        public static final Map<String, ConfigOptionDetailsSensor> mConfigOptionsMapRef;

        static {
            ConfigOptionDetailsSensor configOptionDetailsSensor = new ConfigOptionDetailsSensor("Int Exp Power", ShimmerDevice.DatabaseConfigHandle.EXP_PWR, ShimmerDeviceCommon.ListOfOnOff, ShimmerDeviceCommon.ListOfOnOffConfigValues, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX);
            configOptionIntExpBrdPowerInteger = configOptionDetailsSensor;
            ConfigOptionDetailsSensor configOptionDetailsSensor2 = new ConfigOptionDetailsSensor("Internal Expansion Board Power", null, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX);
            configOptionIntExpBrdPowerBoolean = configOptionDetailsSensor2;
            new LinkedHashMap();
            HashMap map = new HashMap();
            map.put("Int Exp Power", configOptionDetailsSensor);
            map.put("Internal Expansion Board Power", configOptionDetailsSensor2);
            mConfigOptionsMapRef = Collections.unmodifiableMap(map);
        }

        public class GuiLabelConfig {
            public static final String INT_EXP_BRD_POWER_BOOLEAN = "Internal Expansion Board Power";
            public static final String INT_EXP_BRD_POWER_INTEGER = "Int Exp Power";

            public GuiLabelConfig() {
            }
        }
    }

    public static final class Arduino {

        public enum LABEL_SENSOR_TILE {
            ANALOG_IN("Analog In"),
            DIGITAL_IN("Digital In");

            private String tileText;

            LABEL_SENSOR_TILE(String str) {
                this.tileText = str;
            }

            public String getTileText() {
                return this.tileText;
            }
        }
    }

    public static final class Sweatch {

        public class SENSOR_ID {
            public static final int SWEATCH_ADC = 1005;

            public SENSOR_ID() {
            }
        }
    }

    public static class Webcam {

        public static class CompatibilityInfoForMaps {
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoWebcam;
            public static final ShimmerVerObject svoWebcamDigiOptixSmartGlasses;
            public static final ShimmerVerObject svoWebcamGeneric;
            public static final ShimmerVerObject svoWebcamLogitechHdC920;
            public static final ShimmerVerObject svoWebcamLogitechHdC930E;

            static {
                ShimmerVerObject shimmerVerObject = new ShimmerVerObject(1004, -1, -1, -1, -1, -1);
                svoWebcamGeneric = shimmerVerObject;
                ShimmerVerObject shimmerVerObject2 = new ShimmerVerObject(1005, -1, -1, -1, -1);
                svoWebcamLogitechHdC920 = shimmerVerObject2;
                ShimmerVerObject shimmerVerObject3 = new ShimmerVerObject(1006, -1, -1, -1, -1);
                svoWebcamLogitechHdC930E = shimmerVerObject3;
                svoWebcamDigiOptixSmartGlasses = new ShimmerVerObject(1008, -1, -1, -1, -1);
                listOfCompatibleVersionInfoWebcam = Arrays.asList(shimmerVerObject, shimmerVerObject2, shimmerVerObject3);
            }
        }
    }

    public static final class Verisense {

        public enum LABEL_SENSOR_TILE {
            ACCEL("ACCEL"),
            GSR(SensorGSR.LABEL_SENSOR_TILE.GSR),
            PPG("PPG"),
            VBATT("Battery Voltage"),
            ACCEL2_GYRO(SensorLSM6DS3.LABEL_SENSOR_TILE.ACCEL2_GYRO);

            private String tileText;

            LABEL_SENSOR_TILE(String str) {
                this.tileText = str;
            }

            public String getTileText() {
                return this.tileText;
            }
        }

        public static class CompatibilityInfoForMaps {
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoGsr;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoLIS2DW12;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoLSM6DS3;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoMAX86150;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoMAX86916;
            public static final List<ShimmerVerObject> listOfCompatibleVersionInfoVbatt;
            public static final ShimmerVerObject svoVerisenseDevBrd;
            public static final ShimmerVerObject svoVerisenseGsrPlus;
            public static final ShimmerVerObject svoVerisenseImu;
            public static final ShimmerVerObject svoVerisensePpg0;
            public static final ShimmerVerObject svoVerisensePpg1;
            public static final ShimmerVerObject svoVerisensePulsePlus;

            static {
                ShimmerVerObject shimmerVerObject = new ShimmerVerObject(64, -1, -1, -1, -1, -1);
                svoVerisenseDevBrd = shimmerVerObject;
                ShimmerVerObject shimmerVerObject2 = new ShimmerVerObject(61, -1, -1, -1, -1, -1);
                svoVerisenseImu = shimmerVerObject2;
                ShimmerVerObject shimmerVerObject3 = new ShimmerVerObject(62, -1, -1, -1, -1, -1);
                svoVerisenseGsrPlus = shimmerVerObject3;
                ShimmerVerObject shimmerVerObject4 = new ShimmerVerObject(63, -1, -1, -1, -1, 63, 0);
                svoVerisensePpg0 = shimmerVerObject4;
                ShimmerVerObject shimmerVerObject5 = new ShimmerVerObject(63, -1, -1, -1, -1, 63, 1);
                svoVerisensePpg1 = shimmerVerObject5;
                ShimmerVerObject shimmerVerObject6 = new ShimmerVerObject(68, -1, -1, -1, -1, -1);
                svoVerisensePulsePlus = shimmerVerObject6;
                listOfCompatibleVersionInfoLIS2DW12 = Arrays.asList(shimmerVerObject, shimmerVerObject2, shimmerVerObject4, shimmerVerObject5, shimmerVerObject3, shimmerVerObject6);
                listOfCompatibleVersionInfoLSM6DS3 = Arrays.asList(shimmerVerObject, shimmerVerObject2, shimmerVerObject4, shimmerVerObject5, shimmerVerObject3);
                listOfCompatibleVersionInfoMAX86150 = Arrays.asList(shimmerVerObject4);
                listOfCompatibleVersionInfoMAX86916 = Arrays.asList(shimmerVerObject, shimmerVerObject5, shimmerVerObject6);
                listOfCompatibleVersionInfoVbatt = Arrays.asList(shimmerVerObject, shimmerVerObject2, shimmerVerObject4, shimmerVerObject5, shimmerVerObject3, shimmerVerObject6);
                listOfCompatibleVersionInfoGsr = Arrays.asList(shimmerVerObject3, shimmerVerObject6);
            }
        }

        public class SensorBitmap {
            public static final int GSR = 32768;
            public static final int LIS2DW12_ACCEL = 128;
            public static final int LSM6DS3_ACCEL = 64;
            public static final int LSM6DS3_GYRO = 32;
            public static final int MAX86150_ECG = 2048;
            public static final int MAX86916_PPG_BLUE = 1024;
            public static final int MAX86916_PPG_GREEN = 16384;
            public static final int MAX86XXX_PPG_IR = 4096;
            public static final int MAX86XXX_PPG_RED = 8192;
            public static final int VBATT = 512;

            public SensorBitmap() {
            }
        }

        public class DerivedSensorsBitMask {
            public static final int GYRO_ON_THE_FLY_CAL = 128;
            public static final int NON_WEAR_DETECTION_LIS2DW12 = 1;
            public static final int NON_WEAR_DETECTION_LSM6DS3 = 2;
            public static final int ORIENTATION_6DOF_EULER = 512;
            public static final int ORIENTATION_6DOF_QUAT = 256;
            public static final int PPG_TO_HR_BLUE_LED = 32;
            public static final int PPG_TO_HR_GREEN_LED = 16;
            public static final int PPG_TO_HR_IR_LED = 8;
            public static final int PPG_TO_HR_RED_LED = 4;
            public static final int PPG_TO_SPO2 = 64;

            public DerivedSensorsBitMask() {
            }
        }

        public class SENSOR_ID {
            public static final int GSR = 2014;
            public static final int LIS2DW12_ACCEL = 2005;
            public static final int LSM6DS3_ACCEL = 2007;
            public static final int LSM6DS3_GYRO = 2006;
            public static final int MAX86150_ECG = 2010;
            public static final int MAX86916_PPG_BLUE = 2012;
            public static final int MAX86916_PPG_GREEN = 2011;
            public static final int MAX86XXX_PPG_IR = 2009;
            public static final int MAX86XXX_PPG_RED = 2008;
            public static final int VBATT = 2013;
            public static final int VERISENSE_TIMESTAMP = 2000;

            public SENSOR_ID() {
            }
        }
    }
}
