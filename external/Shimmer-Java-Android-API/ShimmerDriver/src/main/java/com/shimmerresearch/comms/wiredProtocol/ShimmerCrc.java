package com.shimmerresearch.comms.wiredProtocol;

import com.shimmerresearch.driverUtilities.UtilShimmer;

public class ShimmerCrc {

        protected static int shimmerUartCrcByte(int crc, byte b) {
        crc &= 0xFFFF;
        crc = ((crc & 0xFFFF) >>> 8) | ((crc & 0xFFFF) << 8);
        crc ^= (b & 0xFF);
        crc ^= (crc & 0xFF) >>> 4;
        crc ^= crc << 12;
        crc ^= (crc & 0xFF) << 5;
        crc &= 0xFFFF;
        return crc;
    }

        public static byte[] shimmerUartCrcCalc(byte[] msg, int len) {
        int CRC_INIT = 0xB0CA;
        int crcCalc;
        int i;

        crcCalc = shimmerUartCrcByte(CRC_INIT, msg[0]);
        for (i = 1; i < len; i++) {
            crcCalc = shimmerUartCrcByte(crcCalc, (msg[i]));
        }
        if (len % 2 > 0) {
            crcCalc = shimmerUartCrcByte(crcCalc, (byte) 0x00);
        }

        byte[] crcCalcArray = new byte[2];
        crcCalcArray[0] = (byte) (crcCalc & 0xFF);  // CRC LSB
        crcCalcArray[1] = (byte) ((crcCalc >> 8) & 0xFF); // CRC MSB

        return crcCalcArray;
    }

        public static boolean shimmerUartCrcCheck(byte[] msg) {
        byte[] crc = shimmerUartCrcCalc(msg, msg.length - 2);

        if ((crc[0] == msg[msg.length - 2])
                && (crc[1] == msg[msg.length - 1]))
            return true;
        else
            return false;
    }


    public static void main(String[] args) {

        byte[] testPacket = new byte[]{
                (byte) 0x00,
                (byte) 0xb6, (byte) 0xf8, (byte) 0xbb,
                (byte) 0xff, (byte) 0x80, (byte) 0x00, (byte) 0x01, (byte) 0x80, (byte) 0x00, (byte) 0x01,
                (byte) 0xff, (byte) 0x80, (byte) 0x00, (byte) 0x01, (byte) 0x80, (byte) 0x00, (byte) 0x01,
                (byte) 0x9a, (byte) 0xb0
        };
        System.out.println("Valid CRC?:\t" + shimmerUartCrcCheck(testPacket));

        System.out.println("CRC Bytes should be [LSB MSB]:\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(shimmerUartCrcCalc(testPacket, testPacket.length - 2)));
    }

}
