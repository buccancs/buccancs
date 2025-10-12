package com.shimmerresearch.bluetooth;

public class BtCommandDetails {
    public byte mCommand = (byte) 0x00;
    public String mDescription = " ";

    public byte expectedResponse = 0x00;
    public int mExpectedResponseByteLength = 1;


    public BtCommandDetails(byte command, String description, byte expectedResponse) {
        this.mCommand = command;
        this.mDescription = description;
        this.expectedResponse = expectedResponse;
    }

    public BtCommandDetails(byte command, String description, int expectedResponseByteLength) {
        this.mCommand = command;
        this.mDescription = description;
        this.mExpectedResponseByteLength = expectedResponseByteLength;
    }


    public BtCommandDetails(byte command, String description) {
        this.mCommand = command;
        this.mDescription = description;
    }

}