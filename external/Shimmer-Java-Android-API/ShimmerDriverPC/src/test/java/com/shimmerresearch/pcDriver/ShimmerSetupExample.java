package com.shimmerresearch.pcDriver;

import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE;
import com.shimmerresearch.driver.BasicProcessWithCallBack;
import com.shimmerresearch.driver.CallbackObject;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.pcDriver.ShimmerPC;

public class ShimmerSetupExample extends BasicProcessWithCallBack {

    public static void main(String[] args) {
        ShimmerSetupExample s = new ShimmerSetupExample();
        s.initialize();
    }

    public void initialize() {
        Integer[] arraySensorID = {Configuration.Shimmer3.SENSOR_ID.SHIMMER_BMPX80_PRESSURE};
        ShimmerPC pc = new ShimmerPC("test", 51.2, 1, 4, arraySensorID, 0, 0, 0, 0);
        pc.connect("COM4", "");
        try {
            pc.connect();
        } catch (ShimmerException e) {
            e.printStackTrace();
        }
        setWaitForData(pc);
    }

    @Override
    protected void processMsgFromCallback(ShimmerMsg shimmerMSG) {

        int ind = shimmerMSG.mIdentifier;

        Object object = (Object) shimmerMSG.mB;

        if (ind == ShimmerPC.MSG_IDENTIFIER_STATE_CHANGE) {
            CallbackObject callbackObject = (CallbackObject) object;

            if (callbackObject.mState == BT_STATE.CONNECTING) {

            } else if (callbackObject.mState == BT_STATE.CONNECTED) {
                System.out.println("CONNECTED");
            }
        }
    }

}
