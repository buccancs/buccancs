package com.shimmerresearch.verisense.communication;

import com.google.common.collect.Maps;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.lsm6dsv.SensorLSM6DSV;
import com.shimmerresearch.verisense.communication.payloads.AbstractPayload;
import com.shimmerresearch.verisense.payloaddesign.CRC16CCITT;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class VerisenseMessage implements Serializable {
    public final int mPacketMaxSize = 32767;
    public long endTimeMs;
    public long lastTransactionMs;
    public int mCurrentLengthBytes;
    public int mExpectedLengthBytes;
    public double mTransferRateByes;
    public byte[] payloadBytes;
    public long startTimeMs;
    public boolean mCRCErrorPayload = false;
    public int payloadIndex = -1;
    byte commandAndProperty;
    byte commandMask;
    byte propertyMask;

    public VerisenseMessage(byte[] bArr, long j) {
        this.startTimeMs = j;
        this.lastTransactionMs = j;
        setCommandAndProperty(bArr[0]);
        int byteArrayAtIndex = (int) AbstractPayload.parseByteArrayAtIndex(bArr, 1, ChannelDetails.CHANNEL_DATA_TYPE.UINT16);
        this.mExpectedLengthBytes = byteArrayAtIndex;
        this.payloadBytes = new byte[byteArrayAtIndex];
        if (bArr.length > 3) {
            appendToDataChuck(bArr, 3, j);
        }
        if (isCurrentLengthGreaterThanOrEqualToExpectedLength()) {
            this.endTimeMs = j;
        }
    }

    public VerisenseMessage(byte b, byte[] bArr) {
        setCommandAndProperty(b);
        this.payloadBytes = bArr;
        int length = bArr.length;
        this.mExpectedLengthBytes = length;
        this.mCurrentLengthBytes = length;
    }

    private void setCommandAndProperty(byte b) {
        this.commandAndProperty = b;
        this.commandMask = (byte) (b & 240);
        this.propertyMask = (byte) (b & 15);
    }

    public boolean isCurrentLengthEqualToExpectedLength() {
        return this.mCurrentLengthBytes == this.mExpectedLengthBytes;
    }

    public boolean isCurrentLengthGreaterThanExpectedLength() {
        return this.mCurrentLengthBytes > this.mExpectedLengthBytes;
    }

    public boolean isExpired(long j) {
        return j - this.lastTransactionMs > 2000;
    }

    public void appendToDataChuck(byte[] bArr, long j) {
        this.lastTransactionMs = j;
        appendToDataChuck(bArr, 0, j);
        if (isCurrentLengthGreaterThanOrEqualToExpectedLength()) {
            this.endTimeMs = j;
        }
    }

    public void appendToDataChuck(byte[] bArr, int i, long j) {
        int length = bArr.length - i;
        System.arraycopy(bArr, i, this.payloadBytes, this.mCurrentLengthBytes, length);
        this.mCurrentLengthBytes += length;
        if (this.commandAndProperty == VERISENSE_PROPERTY.DATA.responseByte() && this.payloadIndex == -1 && this.mCurrentLengthBytes >= 2) {
            this.payloadIndex = (int) AbstractPayload.parseByteArrayAtIndex(this.payloadBytes, 0, ChannelDetails.CHANNEL_DATA_TYPE.UINT16);
        }
    }

    public boolean isCurrentLengthGreaterThanOrEqualToExpectedLength() {
        return isCurrentLengthGreaterThanExpectedLength() || isCurrentLengthEqualToExpectedLength();
    }

    public String consolePrintTransferTime(String str) {
        long j = this.endTimeMs - this.startTimeMs;
        System.out.println("Duration : " + j);
        if (j == 0) {
            return null;
        }
        double d = this.mCurrentLengthBytes / ((this.endTimeMs - this.startTimeMs) / 1000.0d);
        this.mTransferRateByes = d;
        String str2 = String.format("%f KB/s", Double.valueOf(d / 1024.0d)) + "(Payload Index : " + this.payloadIndex + ")";
        System.out.println(str + StringUtils.SPACE + str2);
        return str2;
    }

    boolean CRCCheck() {
        int i = this.mExpectedLengthBytes - 2;
        byte[] bArr = new byte[i];
        System.arraycopy(this.payloadBytes, 0, bArr, 0, i);
        CRC16CCITT crc16ccitt = new CRC16CCITT();
        byte[] bArr2 = this.payloadBytes;
        return crc16ccitt.checkCrc(bArr, crc16ccitt.crcBytesToInt(new byte[]{bArr2[bArr2.length - 2], bArr2[bArr2.length - 1]}));
    }

    public byte[] generatePacket() {
        int i = this.mExpectedLengthBytes;
        byte[] bArr = new byte[i + 3];
        bArr[0] = this.commandAndProperty;
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
        byte[] bArr2 = this.payloadBytes;
        if (bArr2.length > 0) {
            System.arraycopy(bArr2, 0, bArr, 3, i);
        }
        return bArr;
    }

    public String generateDebugString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Command=" + VERISENSE_COMMAND.lookupByMask(this.commandMask).toString());
        sb.append(", Property=" + VERISENSE_PROPERTY.lookupByMask(this.propertyMask).toString());
        sb.append(", Expected Length = " + this.mExpectedLengthBytes);
        sb.append(", Current Length = " + this.mCurrentLengthBytes);
        return sb.toString();
    }

    public String generatePayloadByteString() {
        return UtilShimmer.bytesToHexStringWithSpacesFormatted(generatePacket());
    }

    public enum VERISENSE_COMMAND {
        READ((byte) 16),
        WRITE((byte) 32),
        RESPONSE(ShimmerObject.SET_BLINK_LED),
        ACK((byte) 64),
        NACK_BAD_HEADER_COMMAND(SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE),
        NACK_BAD_HEADER_PROPERTY(ShimmerObject.GET_INTERNAL_EXP_POWER_ENABLE_COMMAND),
        NACK_GENERIC(ShimmerObject.START_SDBT_COMMAND),
        ACK_NEXT_STAGE((byte) -128);

        private static final Map<Byte, VERISENSE_COMMAND> nameIndex = Maps.newHashMapWithExpectedSize(values().length);

        static {
            for (VERISENSE_COMMAND verisense_command : values()) {
                nameIndex.put(Byte.valueOf(verisense_command.getCommandMask()), verisense_command);
            }
        }

        private byte commandMask;

        VERISENSE_COMMAND(byte b) {
            this.commandMask = b;
        }

        public static VERISENSE_COMMAND lookupByMask(byte b) {
            return nameIndex.get(Byte.valueOf(b));
        }

        public byte getCommandMask() {
            return this.commandMask;
        }
    }

    public enum VERISENSE_PROPERTY {
        STATUS((byte) 1),
        DATA((byte) 2),
        CONFIG_PROD((byte) 3),
        CONFIG_OPER((byte) 4),
        TIME((byte) 5),
        DFU_MODE((byte) 6),
        PENDING_EVENTS((byte) 7),
        FW_TEST((byte) 8),
        FW_DEBUG((byte) 9),
        DEVICE_DISCONNECT((byte) 11),
        STREAMING((byte) 10);

        private static final Map<Byte, VERISENSE_PROPERTY> nameIndex = Maps.newHashMapWithExpectedSize(values().length);

        static {
            for (VERISENSE_PROPERTY verisense_property : values()) {
                nameIndex.put(Byte.valueOf(verisense_property.getPropertyMask()), verisense_property);
            }
        }

        private byte propertyMask;

        VERISENSE_PROPERTY(byte b) {
            this.propertyMask = b;
        }

        public static VERISENSE_PROPERTY lookupByMask(byte b) {
            return nameIndex.get(Byte.valueOf(b));
        }

        public byte getPropertyMask() {
            return this.propertyMask;
        }

        public byte readByte() {
            return (byte) (this.propertyMask | VERISENSE_COMMAND.READ.getCommandMask());
        }

        public byte writeByte() {
            return (byte) (this.propertyMask | VERISENSE_COMMAND.WRITE.getCommandMask());
        }

        public byte ackByte() {
            return (byte) (this.propertyMask | VERISENSE_COMMAND.ACK.getCommandMask());
        }

        public byte ackNextStageByte() {
            return (byte) (this.propertyMask | VERISENSE_COMMAND.ACK_NEXT_STAGE.getCommandMask());
        }

        public byte responseByte() {
            return (byte) (this.propertyMask | VERISENSE_COMMAND.RESPONSE.getCommandMask());
        }

        public byte nackByte() {
            return (byte) (this.propertyMask | VERISENSE_COMMAND.NACK_GENERIC.getCommandMask());
        }
    }

    public enum VERISENSE_TEST_MODE {
        SHORT_TERM_FLASH1((byte) 1),
        SHORT_TERM_FLASH2((byte) 2),
        LONG_TERM_FLASH((byte) 3),
        EEPROM((byte) 4),
        ACCEL_1((byte) 5),
        BATT_VOLTAGE((byte) 6),
        USB_POWER_GOOD((byte) 7),
        ACCEL2_AND_GYRO((byte) 8),
        MAX86XXX((byte) 9),
        MAX30002((byte) 11),
        ALL((byte) -1);

        private byte testId;

        VERISENSE_TEST_MODE(byte b) {
            this.testId = b;
        }

        public Byte getTestId() {
            return Byte.valueOf(this.testId);
        }
    }

    public class VERISENSE_DEBUG_MODE {
        public static final byte CHECK_PAYLOADS_FOR_CRC_ERRORS = 15;
        public static final byte CLEAR_PENDING_EVENTS = 9;
        public static final byte ERASE_FLASH_AND_LOOKUP = 10;
        public static final byte ERASE_OPERATIONAL_CONFIG = 7;
        public static final byte ERASE_PRODUCTION_CONFIG = 8;
        public static final byte EVENT_LOG = 16;
        public static final byte FLASH_LOOKUP_TABLE_READ = 1;
        public static final byte MAX86XXX_LED_TEST = 14;
        public static final byte RECORD_BUFFER_DETAILS = 18;
        public static final byte RWC_SCHEDULE_READ = 3;
        public static final byte TRANSFER_LOOP = 11;

        public VERISENSE_DEBUG_MODE() {
        }
    }

    public class STREAMING_COMMAND {
        public static final byte STREAMING_START = 1;
        public static final byte STREAMING_STOP = 2;

        public STREAMING_COMMAND() {
        }
    }

    public class TIMEOUT_MS {
        public static final long ALL_TEST_TIMEOUT = 5000;
        public static final long BETWEEN_NACK_AND_PAYLOAD = 1000;
        public static final long BETWEEN_PACKETS = 2000;
        public static final long DATA_TRANSFER = 10000;
        public static final long ERASE_FLASH_AND_LOOKUP_TABLE = 40000;
        public static final long ERASE_LONG_TERM_FLASH = 40000;
        public static final long ERASE_SHORT_TERM_FLASH = 2000;
        public static final long FAKE_LOOKUPTABLE = 120000;
        public static final long FLASH_AND_LOOKUP = 80000;
        public static final long READ_LOOKUP_TABLE = 20000;
        public static final long STANDARD = 1000;

        public TIMEOUT_MS() {
        }
    }
}
