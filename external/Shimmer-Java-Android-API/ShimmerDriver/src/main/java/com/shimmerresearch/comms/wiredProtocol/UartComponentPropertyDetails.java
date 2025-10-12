package com.shimmerresearch.comms.wiredProtocol;

import java.util.List;

import com.shimmerresearch.driverUtilities.ShimmerVerDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;

public class UartComponentPropertyDetails {

    public UartPacketDetails.UART_COMPONENT mComponent;
    public byte mComponentByte = 0;
    public int mProperty = 0;
    public byte mPropertyByte = 0;
    public String mPropertyName = "";
    public List<ShimmerVerObject> mListOfCompatibleVersionInfo;
    public PERMISSION mPermission = PERMISSION.UNKNOWN;
    public boolean mIsWriteExpectedResponseAck = false;
    public boolean mIsWriteExpectedResponseStatus = false;

    public byte[] mCompPropByteArray = null;

    public UartComponentPropertyDetails(UartPacketDetails.UART_COMPONENT component, int property, PERMISSION readWrite, List<ShimmerVerObject> listOfCompatibleVersionInfo, String propertyName) {
        mComponent = component;
        mComponentByte = component.toCmdByte();
        mProperty = property;
        mPropertyByte = (byte) property;
        mPropertyName = propertyName;
        mListOfCompatibleVersionInfo = listOfCompatibleVersionInfo;

        mCompPropByteArray = new byte[]{(byte) this.mComponentByte, (byte) this.mPropertyByte};
    }

    public UartComponentPropertyDetails(UartPacketDetails.UART_COMPONENT component, int property, PERMISSION readWrite, List<ShimmerVerObject> listOfCompatibleVersionInfo, String propertyName, boolean isWriteExpectedResponseAck) {
        this(component, property, readWrite, listOfCompatibleVersionInfo, propertyName);
        mIsWriteExpectedResponseAck = isWriteExpectedResponseAck;
    }

    public boolean isWriteExpectedResponseAck() {
        return mIsWriteExpectedResponseAck;
    }

    public boolean isWriteExpectedResponseStatus() {
        return mIsWriteExpectedResponseStatus;
    }

    public enum PERMISSION {
        UNKNOWN,
        READ_ONLY,
        WRITE_ONLY,
        READ_WRITE
    }
}