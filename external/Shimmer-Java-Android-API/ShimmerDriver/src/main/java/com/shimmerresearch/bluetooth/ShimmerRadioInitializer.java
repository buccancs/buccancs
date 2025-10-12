package com.shimmerresearch.bluetooth;

import java.util.Arrays;

import com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet;
import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.driverUtilities.ExpansionBoardDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.exceptions.ShimmerException;

public class ShimmerRadioInitializer {

    private static boolean mUseLegacyDelayToDelayForResponse = false;
    protected AbstractSerialPortHal serialCommPort;
    private ShimmerVerObject shimmerVerObject = null;
    private ExpansionBoardDetails expansionBoardDetails = null;

    public ShimmerRadioInitializer() {
    }

    public ShimmerRadioInitializer(AbstractSerialPortHal serialCommPort) {
        this.serialCommPort = serialCommPort;
    }

    public ShimmerRadioInitializer(AbstractSerialPortHal serialCommPort, boolean useLegacyDelayBeforeBtRead) {
        mUseLegacyDelayToDelayForResponse = useLegacyDelayBeforeBtRead;
        this.serialCommPort = serialCommPort;
    }

    public static void useLegacyDelayBeforeBtRead(boolean useLegacyDelayBeforeBtRead) {
        mUseLegacyDelayToDelayForResponse = useLegacyDelayBeforeBtRead;
    }

    public ShimmerVerObject readShimmerVerObject() throws ShimmerException {
        try {
            int hardwareVersion = readHardwareVersion();
            byte[] bufferFWVersion = readFirmwareVersion();
            shimmerVerObject = createShimmerVerObject(hardwareVersion, bufferFWVersion);

            return shimmerVerObject;
        } catch (ShimmerException e) {
            throw (e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected int readHardwareVersion() throws ShimmerException, InterruptedException {
        byte[] instruction = {InstructionsGet.GET_SHIMMER_VERSION_COMMAND_VALUE};

        serialCommPort.txBytes(instruction);
        if (mUseLegacyDelayToDelayForResponse)
            Thread.sleep(200);
        byte[] response = serialCommPort.rxBytes(3);
        if (response == null) {
            serialCommPort.disconnect();
            throw new ShimmerException();
        }
        int hardwareVersion = response[2];

        return hardwareVersion;
    }

    protected byte[] readFirmwareVersion() throws ShimmerException, InterruptedException {
        byte[] instruction = {InstructionsGet.GET_FW_VERSION_COMMAND_VALUE};

        serialCommPort.txBytes(instruction);
        byte[] bufferInquiry = new byte[6];
        if (mUseLegacyDelayToDelayForResponse)
            Thread.sleep(200);
        serialCommPort.rxBytes(1);
        serialCommPort.rxBytes(1);
        bufferInquiry = serialCommPort.rxBytes(6);
        if (bufferInquiry == null) {
            serialCommPort.disconnect();
            throw new ShimmerException();
        }
        return bufferInquiry;
    }

    protected ShimmerVerObject createShimmerVerObject(int hardwareVersion, byte[] bufferInquiry) {
        int firmwareIdentifier = (int) ((bufferInquiry[1] & 0xFF) << 8) + (int) (bufferInquiry[0] & 0xFF);
        int firmwareVersionMajor = (int) ((bufferInquiry[3] & 0xFF) << 8) + (int) (bufferInquiry[2] & 0xFF);
        int firmwareVersionMinor = ((int) ((bufferInquiry[4] & 0xFF)));
        int firmwareVersionInternal = (int) (bufferInquiry[5] & 0xFF);
        ShimmerVerObject shimmerVersion = new ShimmerVerObject(hardwareVersion, firmwareIdentifier, firmwareVersionMajor, firmwareVersionMinor, firmwareVersionInternal);

        return shimmerVersion;
    }

    public ExpansionBoardDetails readExpansionBoardID() {
        try {
            if (shimmerVerObject != null && shimmerVerObject.isShimmerHardware() && shimmerVerObject.getFirmwareVersionCode() >= 5) {
                byte[] instruction = {InstructionsGet.GET_EXPID_COMMAND_VALUE, 3, 0};

                serialCommPort.txBytes(instruction);
                if (mUseLegacyDelayToDelayForResponse)
                    Thread.sleep(200);
                byte[] response = serialCommPort.rxBytes(3 + 1);

                if (response != null) {
                    byte[] expBoardArraySplit = Arrays.copyOfRange(response, 1, 4);
                    expansionBoardDetails = new ExpansionBoardDetails(expBoardArraySplit);
                    return expansionBoardDetails;
                }
            }
        } catch (ShimmerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AbstractSerialPortHal getSerialCommPort() {
        return this.serialCommPort;
    }

    public void setSerialCommPort(AbstractSerialPortHal serialPortComm) {
        this.serialCommPort = serialPortComm;
    }

}
