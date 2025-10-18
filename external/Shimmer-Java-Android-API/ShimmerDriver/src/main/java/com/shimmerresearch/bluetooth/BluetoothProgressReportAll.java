package com.shimmerresearch.bluetooth;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;


import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.driver.shimmer4sdk.Shimmer4sdk;

public class BluetoothProgressReportAll implements Serializable {

    public static final String[] mListOfOperationStates = new String[]{
            "Pending",
            "In Progress",
            "Finished",
            "Cancelling",
            "Cancelled"
    };
    private static final long serialVersionUID = 2543348738920447317L;
    public List<ShimmerDevice> mListOfShimmers;
    public LinkedHashMap<String, BluetoothProgressReportPerDevice> mMapOfOperationProgressInfo = new LinkedHashMap<String, BluetoothProgressReportPerDevice>();
    public BT_STATE currentOperationBtState = BT_STATE.DISCONNECTED;
    public OperationState mOperationState = OperationState.PENDING;
    public int mProgressEndValue = 100;
    public int mProgressCounter = 0;
    public int mNumberOfFails = 0;
    public int mProgressPercentageComplete = 0;

    public BluetoothProgressReportAll(BT_STATE currentOperationBtState, List<ShimmerDevice> lso) {
        this.currentOperationBtState = currentOperationBtState;
        mListOfShimmers = lso;

        mMapOfOperationProgressInfo.clear();
        for (ShimmerDevice shimmer : lso) {
            String comPort = shimmer.getComPort();
            mMapOfOperationProgressInfo.put(comPort, new BluetoothProgressReportPerDevice(shimmer, currentOperationBtState, 1));
        }
        updateProgressTotal();
    }

    public BluetoothProgressReportAll(List<String> listOfShimmerHandles, BT_STATE currentOperationBtState) {
        this.currentOperationBtState = currentOperationBtState;

        mMapOfOperationProgressInfo.clear();
        for (String shimmerHandle : listOfShimmerHandles) {
            mMapOfOperationProgressInfo.put(shimmerHandle, new BluetoothProgressReportPerDevice(shimmerHandle, currentOperationBtState, 1));
        }
        updateProgressTotal();
    }

    public void updateProgressTotal() {
        mProgressEndValue = mMapOfOperationProgressInfo.keySet().size();
    }

    public void updateProgressCount() {
        mProgressCounter += 1;
        mProgressPercentageComplete = ((int) (((double) mProgressCounter / (double) mProgressEndValue) * 100));
    }

    public void updateProgressPerDevice(String comPort, BluetoothProgressReportPerDevice pRPD) {
        mMapOfOperationProgressInfo.put(comPort, pRPD);
        if (mMapOfOperationProgressInfo.get(comPort).mProgressCounter == mMapOfOperationProgressInfo.get(comPort).mProgressEndValue) {
            updateProgressCount();
        }
    }

    public BluetoothProgressReportAll deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (BluetoothProgressReportAll) ois.readObject();
        } catch (
                IOException e) {
            e.printStackTrace();
            return null;
        } catch (
                ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static enum OperationState {
        PENDING,
        INPROGRESS,
        FINISHED,
        CANCELLING,
        CANCELLED
    }
}