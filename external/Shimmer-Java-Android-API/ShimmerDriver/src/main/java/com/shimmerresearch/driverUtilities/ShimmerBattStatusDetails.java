package com.shimmerresearch.driverUtilities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.shimmerresearch.driverUtilities.ShimmerBattStatusDetails.BATTERY_LEVEL;
import com.shimmerresearch.sensors.SensorADC;

public class ShimmerBattStatusDetails implements Serializable {

    private static final long serialVersionUID = -1108374309087845014L;
    private static final double BATTERY_ERROR_VOLTAGE = 4.5;
    private int mBattAdcValue = 0;
    private int mChargingStatusRaw = 0;
    private CHARGING_STATUS mChargingStatus = CHARGING_STATUS.UNKNOWN;
    private double mBattVoltage = 0.0;
    private Double mEstimatedChargePercentage = -1.0;

    public ShimmerBattStatusDetails() {
    }

    public ShimmerBattStatusDetails(byte[] rxBuf) {
        if (rxBuf.length >= 3) {
            int battAdcValue = (((rxBuf[1] & 0xFF) << 8) + (rxBuf[0] & 0xFF));
            int chargingStatus = rxBuf[2] & 0xFF;
            update(battAdcValue, chargingStatus);
        }
    }

    public ShimmerBattStatusDetails(int battAdcValue, int chargingStatus) {
        update(battAdcValue, chargingStatus);
    }

    public static double adcValToBattVoltage(int adcVal) {
        double calibratedData = SensorADC.calibrateU12AdcValueToMillivolts(adcVal, 0.0, 3.0, 1.0);
        double battVoltage = ((calibratedData * 1.988)) / 1000;
        return battVoltage;
    }

    public static int battVoltageToAdcVal(double battVoltage) {
        double uncalibratedData = (battVoltage * 1000) / 1.988;
        int adcVal = SensorADC.uncalibrateU12AdcValueFromMillivolts(uncalibratedData, 0.0, 3.0, 1.0);
        return adcVal;
    }

    public static double battVoltageToBattPercentage(double battVoltage) {
        double battPercentage = (1109.739792 * Math.pow(battVoltage, 4)) - (17167.12674 * Math.pow(battVoltage, 3)) + (99232.71686 * Math.pow(battVoltage, 2)) - (253825.397 * battVoltage) + 242266.0527;
        return battPercentage;
    }

    public static double battPercentageToBattVoltage(double battPercentage) {
        double battVoltage =
                -(6.6e-8 * Math.pow(battPercentage, 4))
                        + (1.6e-5 * Math.pow(battPercentage, 3))
                        - (0.0012 * Math.pow(battPercentage, 2))
                        + (0.039 * battPercentage)
                        + 3.3;
        return battVoltage;
    }

    public static int battPercentageToAdc(double battPercentage) {
        double battVoltage = battPercentageToBattVoltage(battPercentage);
        return battVoltageToAdcVal(battVoltage);
    }

    public static BATTERY_LEVEL estimateBatteryLevel(double percentageBattery) {
        if (percentageBattery <= 0.0) {
            return BATTERY_LEVEL.UNKNOWN;
        } else if (percentageBattery < 33) {
            return BATTERY_LEVEL.LOW;
        } else if (percentageBattery < 66) {
            return BATTERY_LEVEL.MEDIUM;
        } else {
            return BATTERY_LEVEL.HIGH;
        }
    }

    public static void main(String[] args) {

        ShimmerBattStatusDetails shimmerBattStatusDetails = new ShimmerBattStatusDetails();

        System.out.println("Testing SDLog <v0.12.3 firmware thresholds...");
        System.out.println(">>>--------------------------------->");
        List<Integer> listOfSdLogThresholdsForLedChange = Arrays.asList(
                2400, //Below 2400 = LOW batt LED (RED)
                2450,
                2600, //Above 2600 = HIGH batt LED (GREEN)
                2650,
                2670);

        for (Integer i : listOfSdLogThresholdsForLedChange) {
            shimmerBattStatusDetails.update(i, 0);
            System.out.println("ADC val: " + i
                    + "\tVoltage: " + shimmerBattStatusDetails.getBattVoltage()
                    + "\tPercent: " + shimmerBattStatusDetails.getEstimatedChargePercentageParsed()
                    + "\tLevel: " + shimmerBattStatusDetails.getEstimatedBatteryLevel().toString());
        }


        System.out.println("");
        System.out.println("Calculating ADC values from preferred thresholds");
        System.out.println("<---------------------------------<<<");
        List<Double> listOfPreferredThresholdsForLedChange = Arrays.asList(
                0.0,
                33.0,
                66.0,
                100.0);
        for (Double d : listOfPreferredThresholdsForLedChange) {
            System.out.println("ADC val: " + ShimmerBattStatusDetails.battPercentageToAdc(d)
                    + "\tVoltage: " + ShimmerBattStatusDetails.battPercentageToBattVoltage(d)
                    + "\tPercent: " + d
                    + "\tADC buffer: " + (ShimmerBattStatusDetails.battPercentageToAdc(d) - 25) + "-to-" + (ShimmerBattStatusDetails.battPercentageToAdc(d) + 25)
            );
        }

    }

    public byte[] generateBattStatusBytes() {
        byte[] battStatusBytes = new byte[3];
        battStatusBytes[0] = (byte) (mBattAdcValue & 0xFF);
        battStatusBytes[1] = (byte) ((mBattAdcValue >> 8) & 0xFF);
        battStatusBytes[2] = (byte) mChargingStatusRaw;
        return battStatusBytes;
    }

    public void update(int battAdcValue, int chargingStatus) {
        setBattAdcValue(battAdcValue);
        setChargingStatus(chargingStatus);
    }

    public CHARGING_STATUS getChargingStatus() {
        return mChargingStatus;
    }

    public void setChargingStatus(int chargingStatus) {
        mChargingStatusRaw = chargingStatus;

        if (mBattVoltage > BATTERY_ERROR_VOLTAGE) {
            mChargingStatus = CHARGING_STATUS.CHECKING;
        } else if ((mChargingStatusRaw & 0xFF) == CHARGING_STATUS_BYTE.SUSPENDED) {
            mChargingStatus = CHARGING_STATUS.SUSPENDED;
        } else if ((mChargingStatusRaw & 0xFF) == CHARGING_STATUS_BYTE.FULLY_CHARGED) {
            mChargingStatus = CHARGING_STATUS.FULLY_CHARGED;
        } else if ((mChargingStatusRaw & 0xFF) == CHARGING_STATUS_BYTE.PRECONDITIONING) {
            mChargingStatus = CHARGING_STATUS.CHARGING;
        } else if ((mChargingStatusRaw & 0xFF) == CHARGING_STATUS_BYTE.BAD_BATTERY) {
            mChargingStatus = CHARGING_STATUS.BAD_BATTERY;
        } else if ((mChargingStatusRaw & 0xFF) == CHARGING_STATUS_BYTE.UNKNOWN) {
            mChargingStatus = CHARGING_STATUS.UNKNOWN;
        } else {
            mChargingStatus = CHARGING_STATUS.ERROR;
        }
    }

    public void calculateBattPercentage(double battVoltage) {
        mBattVoltage = battVoltage;

        if (mBattVoltage > (4.167 + 0.2)) {
            mBattVoltage = 4.167;
        } else if (mBattVoltage < (3.2 - 0.2)) {
            mBattVoltage = 3.2;
        }

        mEstimatedChargePercentage = battVoltageToBattPercentage(mBattVoltage);
        if (mEstimatedChargePercentage > 100) {
            mEstimatedChargePercentage = 100.0;
        } else if (mEstimatedChargePercentage < 0) {
            mEstimatedChargePercentage = 0.0;
        }
    }

    public String getChargingStatusParsed() {
        String mChargingStatusParsed = mChargingStatus.toString();
        if (mChargingStatus == CHARGING_STATUS.CHARGING) {
            if (mBattVoltage < 3.0) {
                mChargingStatusParsed += " (Preconditioning)";
            } else {
                mChargingStatusParsed += "...";
            }

        }
        return mChargingStatusParsed;
    }

    public double getBattVoltage() {
        return mBattVoltage;
    }

    public String getBattVoltageParsed() {
        String mBattVoltageParsed = String.format("%,.3f", mBattVoltage) + " V";
        return mBattVoltageParsed;
    }

    public double getBattAdcValue() {
        return mBattAdcValue;
    }

    public void setBattAdcValue(int battAdcValue) {
        mBattAdcValue = battAdcValue;
        mBattVoltage = adcValToBattVoltage(mBattAdcValue);
        if (mBattVoltage <= BATTERY_ERROR_VOLTAGE) {
            calculateBattPercentage(mBattVoltage);
        }
    }

    public double getEstimatedChargePercentage() {
        return mEstimatedChargePercentage;
    }

    public String getEstimatedChargePercentageParsed() {
        if ((mChargingStatusRaw & 0xFF) != CHARGING_STATUS_BYTE.BAD_BATTERY) {
            if (mEstimatedChargePercentage == -1.0) {
                return null;
            } else {
                return (String.format("%,.1f", mEstimatedChargePercentage) + "%");
            }
        } else {
            return "0.0%";
        }
    }

    public BATTERY_LEVEL getEstimatedBatteryLevel() {
        return estimateBatteryLevel(mEstimatedChargePercentage);
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

        private CHARGING_STATUS(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public enum BATTERY_LEVEL {
        UNKNOWN,
        LOW,
        MEDIUM,
        HIGH
    }
	
	/* SDLog Firmware code
	 * void SetBattVal(){
		   if(battStat & BATT_MID){
		      if(*(uint16_t*)battVal<2400){
		         battStat = BATT_LOW;
		      }else if(*(uint16_t*)battVal<2650){
		         battStat = BATT_MID;
		      }else
		         battStat = BATT_HIGH;
		   }else if(battStat & BATT_LOW){
		      if(*(uint16_t*)battVal<2450){
		         battStat = BATT_LOW;
		      }else if(*(uint16_t*)battVal<2600){
		         battStat = BATT_MID;
		      }else
		         battStat = BATT_HIGH;
		   }else{
		      if(*(uint16_t*)battVal<2400){
		         battStat = BATT_LOW;
		      }else if(*(uint16_t*)battVal<2600){
		         battStat = BATT_MID;
		      }else
		         battStat = BATT_HIGH;
		   }
		   battVal[2] = P2IN & 0xC0;
		}
	*/

    public class CHARGING_STATUS_BYTE {
        public static final int SUSPENDED = 0xC0;
        public static final int FULLY_CHARGED = 0x40;
        public static final int PRECONDITIONING = 0x80;
        public static final int BAD_BATTERY = 0x00;
        public static final int UNKNOWN = 0xFF;
    }

}
