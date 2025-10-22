package com.shimmerresearch.comms.wiredProtocol;

import com.shimmerresearch.comms.wiredProtocol.UartPacketDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;

import java.util.List;

/* loaded from: classes2.dex */
public class UartComponentPropertyDetails {
    public byte[] mCompPropByteArray;
    public UartPacketDetails.UART_COMPONENT mComponent;
    public byte mComponentByte;
    public boolean mIsWriteExpectedResponseAck;
    public boolean mIsWriteExpectedResponseStatus;
    public List<ShimmerVerObject> mListOfCompatibleVersionInfo;
    public PERMISSION mPermission;
    public int mProperty;
    public byte mPropertyByte;
    public String mPropertyName;

    public UartComponentPropertyDetails(UartPacketDetails.UART_COMPONENT uart_component, int i, PERMISSION permission, List<ShimmerVerObject> list, String str) {
        this.mComponentByte = (byte) 0;
        this.mProperty = 0;
        this.mPropertyByte = (byte) 0;
        this.mPropertyName = "";
        this.mPermission = PERMISSION.UNKNOWN;
        this.mIsWriteExpectedResponseAck = false;
        this.mIsWriteExpectedResponseStatus = false;
        this.mCompPropByteArray = null;
        this.mComponent = uart_component;
        byte cmdByte = uart_component.toCmdByte();
        this.mComponentByte = cmdByte;
        this.mProperty = i;
        byte b = (byte) i;
        this.mPropertyByte = b;
        this.mPropertyName = str;
        this.mListOfCompatibleVersionInfo = list;
        this.mCompPropByteArray = new byte[]{cmdByte, b};
    }

    public UartComponentPropertyDetails(UartPacketDetails.UART_COMPONENT uart_component, int i, PERMISSION permission, List<ShimmerVerObject> list, String str, boolean z) {
        this(uart_component, i, permission, list, str);
        this.mIsWriteExpectedResponseAck = z;
    }

    public boolean isWriteExpectedResponseAck() {
        return this.mIsWriteExpectedResponseAck;
    }

    public boolean isWriteExpectedResponseStatus() {
        return this.mIsWriteExpectedResponseStatus;
    }

    public enum PERMISSION {
        UNKNOWN,
        READ_ONLY,
        WRITE_ONLY,
        READ_WRITE
    }
}
