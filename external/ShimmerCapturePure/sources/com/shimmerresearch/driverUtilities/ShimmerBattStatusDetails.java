package com.shimmerresearch.driverUtilities;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.sensors.SensorADC;

import java.io.Serializable;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class ShimmerBattStatusDetails implements Serializable {
    private static final double BATTERY_ERROR_VOLTAGE = 4.5d;
    private static final long serialVersionUID = -1108374309087845014L;
    private int mBattAdcValue = 0;
    private int mChargingStatusRaw = 0;
    private CHARGING_STATUS mChargingStatus = CHARGING_STATUS.UNKNOWN;
    private double mBattVoltage = 0.0d;
    private Double mEstimatedChargePercentage = Double.valueOf(-1.0d);

    public ShimmerBattStatusDetails() {
    }

    public ShimmerBattStatusDetails(byte[] bArr) {
        if (bArr.length >= 3) {
            update(((bArr[1] & 255) << 8) + (bArr[0] & 255), bArr[2] & 255);
        }
    }

    public ShimmerBattStatusDetails(int i, int i2) {
        update(i, i2);
    }

    public static double adcValToBattVoltage(int i) {
        return (SensorADC.calibrateU12AdcValueToMillivolts(i, 0.0d, 3.0d, 1.0d) * 1.988d) / 1000.0d;
    }

    public static int battVoltageToAdcVal(double d) {
        return SensorADC.uncalibrateU12AdcValueFromMillivolts((d * 1000.0d) / 1.988d, 0.0d, 3.0d, 1.0d);
    }

    public static double battVoltageToBattPercentage(double d) {
        return ((((Math.pow(d, 4.0d) * 1109.739792d) - (Math.pow(d, 3.0d) * 17167.12674d)) + (Math.pow(d, 2.0d) * 99232.71686d)) - (d * 253825.397d)) + 242266.0527d;
    }

    public static double battPercentageToBattVoltage(double d) {
        return (((-(Math.pow(d, 4.0d) * 6.6E-8d)) + (Math.pow(d, 3.0d) * 1.6E-5d)) - (Math.pow(d, 2.0d) * 0.0012d)) + (d * 0.039d) + 3.3d;
    }

    public static int battPercentageToAdc(double d) {
        return battVoltageToAdcVal(battPercentageToBattVoltage(d));
    }

    public static BATTERY_LEVEL estimateBatteryLevel(double d) {
        if (d <= 0.0d) {
            return BATTERY_LEVEL.UNKNOWN;
        }
        if (d < 33.0d) {
            return BATTERY_LEVEL.LOW;
        }
        if (d < 66.0d) {
            return BATTERY_LEVEL.MEDIUM;
        }
        return BATTERY_LEVEL.HIGH;
    }

    public static void main(String[] strArr) {
        ShimmerBattStatusDetails shimmerBattStatusDetails = new ShimmerBattStatusDetails();
        System.out.println("Testing SDLog <v0.12.3 firmware thresholds...");
        System.out.println(">>>--------------------------------->");
        for (Integer num : Arrays.asList(2400, 2450, 2600, 2650, 2670)) {
            shimmerBattStatusDetails.update(num.intValue(), 0);
            System.out.println("ADC val: " + num + "\tVoltage: " + shimmerBattStatusDetails.getBattVoltage() + "\tPercent: " + shimmerBattStatusDetails.getEstimatedChargePercentageParsed() + "\tLevel: " + shimmerBattStatusDetails.getEstimatedBatteryLevel().toString());
        }
        System.out.println("");
        System.out.println("Calculating ADC values from preferred thresholds");
        System.out.println("<---------------------------------<<<");
        for (Double d : Arrays.asList(Double.valueOf(0.0d), Double.valueOf(33.0d), Double.valueOf(66.0d), Double.valueOf(100.0d))) {
            System.out.println("ADC val: " + battPercentageToAdc(d.doubleValue()) + "\tVoltage: " + battPercentageToBattVoltage(d.doubleValue()) + "\tPercent: " + d + "\tADC buffer: " + (battPercentageToAdc(d.doubleValue()) - 25) + "-to-" + (battPercentageToAdc(d.doubleValue()) + 25));
        }
    }

    public byte[] generateBattStatusBytes() {
        int i = this.mBattAdcValue;
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) this.mChargingStatusRaw};
    }

    public double getBattAdcValue() {
        return this.mBattAdcValue;
    }

    public void setBattAdcValue(int i) {
        this.mBattAdcValue = i;
        double dAdcValToBattVoltage = adcValToBattVoltage(i);
        this.mBattVoltage = dAdcValToBattVoltage;
        if (dAdcValToBattVoltage <= BATTERY_ERROR_VOLTAGE) {
            calculateBattPercentage(dAdcValToBattVoltage);
        }
    }

    public double getBattVoltage() {
        return this.mBattVoltage;
    }

    public CHARGING_STATUS getChargingStatus() {
        return this.mChargingStatus;
    }

    public void setChargingStatus(int i) {
        this.mChargingStatusRaw = i;
        if (this.mBattVoltage > BATTERY_ERROR_VOLTAGE) {
            this.mChargingStatus = CHARGING_STATUS.CHECKING;
            return;
        }
        if ((i & 255) == 192) {
            this.mChargingStatus = CHARGING_STATUS.SUSPENDED;
            return;
        }
        if ((i & 255) == 64) {
            this.mChargingStatus = CHARGING_STATUS.FULLY_CHARGED;
            return;
        }
        if ((i & 255) == 128) {
            this.mChargingStatus = CHARGING_STATUS.CHARGING;
            return;
        }
        if ((i & 255) == 0) {
            this.mChargingStatus = CHARGING_STATUS.BAD_BATTERY;
        } else if ((i & 255) == 255) {
            this.mChargingStatus = CHARGING_STATUS.UNKNOWN;
        } else {
            this.mChargingStatus = CHARGING_STATUS.ERROR;
        }
    }

    public void update(int i, int i2) {
        setBattAdcValue(i);
        setChargingStatus(i2);
    }

    public void calculateBattPercentage(double d) {
        this.mBattVoltage = d;
        if (d > 4.367d) {
            this.mBattVoltage = 4.167d;
        } else if (d < 3.0d) {
            this.mBattVoltage = 3.2d;
        }
        Double dValueOf = Double.valueOf(battVoltageToBattPercentage(this.mBattVoltage));
        this.mEstimatedChargePercentage = dValueOf;
        if (dValueOf.doubleValue() > 100.0d) {
            this.mEstimatedChargePercentage = Double.valueOf(100.0d);
        } else if (this.mEstimatedChargePercentage.doubleValue() < 0.0d) {
            this.mEstimatedChargePercentage = Double.valueOf(0.0d);
        }
    }

    public String getChargingStatusParsed() {
        String string = this.mChargingStatus.toString();
        if (this.mChargingStatus != CHARGING_STATUS.CHARGING) {
            return string;
        }
        if (this.mBattVoltage < 3.0d) {
            return string + " (Preconditioning)";
        }
        return string + "...";
    }

    public String getBattVoltageParsed() {
        return String.format("%,.3f", Double.valueOf(this.mBattVoltage)) + " V";
    }

    public double getEstimatedChargePercentage() {
        return this.mEstimatedChargePercentage.doubleValue();
    }

    public String getEstimatedChargePercentageParsed() {
        if ((this.mChargingStatusRaw & 255) == 0) {
            return "0.0%";
        }
        if (this.mEstimatedChargePercentage.doubleValue() == -1.0d) {
            return null;
        }
        return String.format("%,.1f", this.mEstimatedChargePercentage) + Configuration.CHANNEL_UNITS.PERCENT;
    }

    public BATTERY_LEVEL getEstimatedBatteryLevel() {
        return estimateBatteryLevel(this.mEstimatedChargePercentage.doubleValue());
    }

    public enum BATTERY_LEVEL {
        UNKNOWN,
        LOW,
        MEDIUM,
        HIGH
    }

    public enum CHARGING_STATUS {
        UNKNOWN("Unknown"),
        CHECKING("Checking..."),
        SUSPENDED("Charging suspended"),
        CHARGING("Charging"),
        FULLY_CHARGED("Fully charged"),
        BAD_BATTERY("Bad battery"),
        ERROR("Error");

        private final String text;

        CHARGING_STATUS(String str) {
            this.text = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }

    public class CHARGING_STATUS_BYTE {
        public static final int BAD_BATTERY = 0;
        public static final int FULLY_CHARGED = 64;
        public static final int PRECONDITIONING = 128;
        public static final int SUSPENDED = 192;
        public static final int UNKNOWN = 255;

        public CHARGING_STATUS_BYTE() {
        }
    }
}
