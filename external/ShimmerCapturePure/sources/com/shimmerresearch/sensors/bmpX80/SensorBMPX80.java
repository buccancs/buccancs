package com.shimmerresearch.sensors.bmpX80;

import com.shimmerresearch.driver.ConfigByteLayout;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.shimmer2r3.ConfigByteLayoutShimmer3;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.sensors.AbstractSensor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class SensorBMPX80 extends AbstractSensor {
    private static final long serialVersionUID = 1772340408277892416L;
    public CalibDetailsBmpX80 mCalibDetailsBmpX80;
    protected int mPressureResolution;

    public SensorBMPX80(AbstractSensor.SENSORS sensors, ShimmerVerObject shimmerVerObject) {
        super(sensors, shimmerVerObject);
        this.mPressureResolution = 0;
        this.mCalibDetailsBmpX80 = new CalibDetailsBmp180();
    }

    public SensorBMPX80(AbstractSensor.SENSORS sensors, ShimmerDevice shimmerDevice) {
        super(sensors, shimmerDevice);
        this.mPressureResolution = 0;
        this.mCalibDetailsBmpX80 = new CalibDetailsBmp180();
    }

    public abstract List<Double> getPressTempConfigValuesLegacy();

    public int getPressureResolution() {
        return this.mPressureResolution;
    }

    public abstract void setPressureResolution(int i);

    public byte[] getRawCalibrationParameters(ShimmerVerObject shimmerVerObject) {
        byte[] bArr = new byte[1];
        if (!this.mShimmerVerObject.isShimmerGen3() && !this.mShimmerVerObject.isShimmerGen3R() && !this.mShimmerVerObject.isShimmerGen4()) {
            bArr[0] = 0;
            return bArr;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(5);
            byteArrayOutputStream.write(this.mCalibDetailsBmpX80.getPressureRawCoefficients().length);
            byteArrayOutputStream.write(this.mCalibDetailsBmpX80.getPressureRawCoefficients());
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return bArr;
        }
    }

    public void parseCalParamByteArray(byte[] bArr, CalibDetails.CALIB_READ_SOURCE calib_read_source) {
        this.mCalibDetailsBmpX80.parseCalParamByteArray(bArr, calib_read_source);
    }

    public double[] calibratePressureSensorData(double d, double d2) {
        return this.mCalibDetailsBmpX80.calibratePressureSensorData(d, d2);
    }

    public void retrievePressureCalibrationParametersFromPacket(byte[] bArr, CalibDetails.CALIB_READ_SOURCE calib_read_source) {
        this.mCalibDetailsBmpX80.parseCalParamByteArray(bArr, calib_read_source);
        this.mCalibDetailsBmpX80.mRangeValue = getPressureResolution();
    }

    public void updateCurrentPressureCalibInUse() {
        this.mCalibDetailsBmpX80.mRangeValue = getPressureResolution();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        ConfigByteLayout configByteLayout = shimmerDevice.getConfigByteLayout();
        if (configByteLayout instanceof ConfigByteLayoutShimmer3) {
            ConfigByteLayoutShimmer3 configByteLayoutShimmer3 = (ConfigByteLayoutShimmer3) configByteLayout;
            int i = configByteLayoutShimmer3.idxConfigSetupByte3;
            bArr[i] = (byte) (((byte) ((getPressureResolution() & configByteLayoutShimmer3.maskBMPX80PressureResolution) << configByteLayoutShimmer3.bitShiftBMPX80PressureResolution)) | bArr[i]);
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        ConfigByteLayout configByteLayout = shimmerDevice.getConfigByteLayout();
        if (configByteLayout instanceof ConfigByteLayoutShimmer3) {
            ConfigByteLayoutShimmer3 configByteLayoutShimmer3 = (ConfigByteLayoutShimmer3) configByteLayout;
            setPressureResolution(configByteLayoutShimmer3.maskBMPX80PressureResolution & (bArr[configByteLayoutShimmer3.idxConfigSetupByte3] >> configByteLayoutShimmer3.bitShiftBMPX80PressureResolution));
        }
    }

    public class GuiLabelConfig {
        public static final String PRESSURE_RATE = "Pressure Rate";
        public static final String PRESSURE_RESOLUTION = "Pressure Resolution";

        public GuiLabelConfig() {
        }
    }

    public class GuiLabelSensors {
        public static final String PRESS_TEMP_BMPX80 = "Pressure & Temperature";

        public GuiLabelSensors() {
        }
    }

    public class LABEL_SENSOR_TILE {
        public static final String PRESSURE_TEMPERATURE = "Pressure & Temperature";

        public LABEL_SENSOR_TILE() {
        }
    }
}
