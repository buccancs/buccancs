package com.shimmerresearch.verisense.payloaddesign;

import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.verisense.UtilVerisenseDriver;
import com.shimmerresearch.verisense.VerisenseDevice;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class PayloadDetails {
    public int crcOriginal;
    public int payloadHeaderLength;
    public int payloadIndex;
    public int payloadLengthBytesValue;
    public int payloadWhiteSpace;
    public byte[] ramBlockDataBytes;
    public byte[] payloadConfig = null;
    public byte[] payloadWithoutCrc = null;
    public PayloadContentsDetails payloadContentsDetails = null;
    private CRC16CCITT CRC16CCITT = new CRC16CCITT();

    public boolean parsePayload(InputStream inputStream, boolean z, int i) throws IOException {
        ShimmerVerObject firmwareVersionToShimmerVerObject;
        byte[] bArr = new byte[6];
        try {
            readBytesFromStream(inputStream, bArr);
            byte[] bArr2 = new byte[2];
            System.arraycopy(bArr, 0, bArr2, 0, 2);
            this.payloadIndex = UtilVerisenseDriver.lsbByteArrayToInt(bArr2);
            byte[] bArr3 = new byte[2];
            System.arraycopy(bArr, 2, bArr3, 0, 2);
            int iLsbByteArrayToInt = UtilVerisenseDriver.lsbByteArrayToInt(bArr3);
            this.payloadLengthBytesValue = iLsbByteArrayToInt;
            if (this.payloadIndex == iLsbByteArrayToInt && iLsbByteArrayToInt == 65535) {
                throw new IOException("Bad Crc binary file: Bytes for payload index and size are " + UtilShimmer.bytesToHexStringWithSpacesFormatted(bArr3));
            }
            byte[] bArr4 = new byte[2];
            System.arraycopy(bArr, 4, bArr4, 0, 2);
            byte[] bArr5 = new byte[0];
            if (VerisenseDevice.isExtendedPayloadConfig(bArr4[0])) {
                byte[] bArr6 = new byte[4];
                readBytesFromStream(inputStream, bArr6);
                firmwareVersionToShimmerVerObject = VerisenseDevice.parseFirmwareVersionToShimmerVerObject(bArr6);
                int iCalculateExtendedPayloadConfigBytesSize = PayloadContentsDetails.calculateExtendedPayloadConfigBytesSize(firmwareVersionToShimmerVerObject);
                byte[] bArr7 = new byte[iCalculateExtendedPayloadConfigBytesSize];
                readBytesFromStream(inputStream, bArr7);
                byte[] bArr8 = new byte[4 + iCalculateExtendedPayloadConfigBytesSize];
                System.arraycopy(bArr6, 0, bArr8, 0, 4);
                System.arraycopy(bArr7, 0, bArr8, 4, iCalculateExtendedPayloadConfigBytesSize);
                bArr5 = bArr8;
            } else {
                firmwareVersionToShimmerVerObject = null;
            }
            byte[] bArr9 = new byte[bArr5.length + 2];
            this.payloadConfig = bArr9;
            System.arraycopy(bArr4, 0, bArr9, 0, 2);
            System.arraycopy(bArr5, 0, this.payloadConfig, 2, bArr5.length);
            this.payloadHeaderLength = bArr5.length + 6;
            byte[] bArr10 = new byte[calculateRamBlockDataLength(this.payloadLengthBytesValue, firmwareVersionToShimmerVerObject)];
            this.ramBlockDataBytes = bArr10;
            readBytesFromStream(inputStream, bArr10);
            byte[] bArr11 = new byte[2];
            readBytesFromStream(inputStream, bArr11);
            this.crcOriginal = this.CRC16CCITT.crcBytesToInt(bArr11);
            byte[] bArr12 = new byte[this.payloadHeaderLength + this.ramBlockDataBytes.length];
            this.payloadWithoutCrc = bArr12;
            System.arraycopy(bArr, 0, bArr12, 0, 6);
            System.arraycopy(bArr5, 0, this.payloadWithoutCrc, 6, bArr5.length);
            byte[] bArr13 = this.ramBlockDataBytes;
            System.arraycopy(bArr13, 0, this.payloadWithoutCrc, 6 + bArr5.length, bArr13.length);
            return true;
        } catch (IOException e) {
            if (!z || i == 0) {
                throw e;
            }
            System.out.println("Reached end of ZIP file");
            return false;
        }
    }

    private int calculateRamBlockDataLength(int i, ShimmerVerObject shimmerVerObject) {
        return (shimmerVerObject == null || !PayloadContentsDetails.isPayloadDesignV8orAbove(shimmerVerObject)) ? i : i - this.payloadConfig.length;
    }

    private int readBytesFromStream(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        readBytesFromStream(inputStream, bArr);
        return UtilVerisenseDriver.lsbByteArrayToInt(bArr);
    }

    private void readBytesFromStream(InputStream inputStream, byte[] bArr) throws IOException {
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (length > 0) {
            if (inputStream.available() == 0) {
                System.err.println("readBytesFromStream: Tried To Read=" + bArr.length + "\tLength Read=" + i2 + "\tBytes=" + UtilShimmer.bytesToHexStringWithSpacesFormatted(bArr));
                throw new IOException("No bytes available in input stream");
            }
            i2 = inputStream.read(bArr, i, length);
            if (i2 > 0) {
                i += i2;
                length -= i2;
            }
        }
    }

    public boolean checkCrc() {
        return this.CRC16CCITT.checkCrc(this.payloadWithoutCrc, this.crcOriginal);
    }

    public void printCrcDetails() {
        int iComputeCrc = this.CRC16CCITT.computeCrc(this.payloadWithoutCrc);
        System.err.println("\tCRC fail on Payload " + this.payloadIndex + "\tcrcParsed=" + UtilShimmer.intToHexStringFormatted(this.crcOriginal, 2, false) + "\tcrcComputed=" + UtilShimmer.intToHexStringFormatted(iComputeCrc, 2, false));
    }

    public PayloadContentsDetails parsePayloadContentsHeaderFooter(byte[] bArr, int i, VerisenseDevice verisenseDevice, int i2) throws IOException {
        PayloadContentsDetails payloadContentsDetailsCreatePayloadContentsDetailsForVersionObject = createPayloadContentsDetailsForVersionObject(verisenseDevice);
        if (payloadContentsDetailsCreatePayloadContentsDetailsForVersionObject.parsePayloadContentsHeaderAndFooter(bArr, i, i2)) {
            this.payloadContentsDetails = payloadContentsDetailsCreatePayloadContentsDetailsForVersionObject;
        } else {
            this.payloadContentsDetails = null;
        }
        return this.payloadContentsDetails;
    }

    private PayloadContentsDetails createPayloadContentsDetailsForVersionObject(VerisenseDevice verisenseDevice) {
        if (PayloadContentsDetails.isPayloadDesignV8orAbove(verisenseDevice.getShimmerVerObject())) {
            return new PayloadContentsDetailsV8orAbove(verisenseDevice);
        }
        return new PayloadContentsDetailsV1_7(verisenseDevice);
    }

    public int calculateTotalPayloadLength() {
        int i = this.payloadHeaderLength + this.payloadLengthBytesValue + 1;
        PayloadContentsDetails payloadContentsDetails = this.payloadContentsDetails;
        return (payloadContentsDetails == null || payloadContentsDetails.verisenseDevice == null || !this.payloadContentsDetails.verisenseDevice.isPayloadDesignV8orAbove()) ? i : i - this.payloadConfig.length;
    }

    public void consolePrintPayloadHeaderInfo(VerisenseDevice verisenseDevice, int i) {
        int i2 = this.payloadLengthBytesValue;
        int iCalculateTotalPayloadLength = calculateTotalPayloadLength() + i;
        StringBuilder sb = new StringBuilder();
        sb.append("Payload Info:\n |_ ByteIndexes [Start = " + UtilShimmer.intToHexStringFormatted(i, 4, true) + ", End = " + UtilShimmer.intToHexStringFormatted(iCalculateTotalPayloadLength, 4, true) + "], Payload Length Bytes = " + i2 + " bytes (" + UtilShimmer.intToHexStringFormatted(i2, 2, false) + ")\n");
        Iterator<AbstractSensor.SENSORS> it2 = this.payloadContentsDetails.getMapOfDataSegmentsPerSensor().keySet().iterator();
        while (it2.hasNext()) {
            sb.append(" |_ " + verisenseDevice.generateSensorConfigStrSingleSensor(it2.next(), Double.NaN));
            sb.append(StringUtils.LF);
        }
        sb.append(" |_ FW Ver = " + verisenseDevice.getFirmwareVersionParsed());
        sb.append(", [" + verisenseDevice.generateResetReasonStr() + "]");
        PayloadContentsDetails payloadContentsDetails = this.payloadContentsDetails;
        if (payloadContentsDetails == null) {
            sb.append(StringUtils.LF);
        } else {
            sb.append(", Temperature=" + payloadContentsDetails.getTemperatureCal() + " °C, Battery Voltage=" + this.payloadContentsDetails.getBatteryVoltageCal() + " mV\n |_ Payload Timing [Start=" + this.payloadContentsDetails.getStartTimeRwcStr() + ", End=" + this.payloadContentsDetails.getEndTimeRwcStr() + ", Duration=" + this.payloadContentsDetails.getPayloadDurationStr() + ", Packaging Delay=" + this.payloadContentsDetails.getPayloadPackagingDelayStr() + "]");
        }
        System.out.println(sb.toString());
        PayloadContentsDetails payloadContentsDetails2 = this.payloadContentsDetails;
        if (payloadContentsDetails2 != null) {
            payloadContentsDetails2.datasetToSave.printReportOfDataSegments();
        }
    }
}
