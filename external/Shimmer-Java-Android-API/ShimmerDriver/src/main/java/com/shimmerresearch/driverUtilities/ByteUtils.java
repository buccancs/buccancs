package com.shimmerresearch.driverUtilities;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteUtils {

    private static int longSize = Long.SIZE / Byte.SIZE;
    private static int shortSize = Long.SIZE / Byte.SIZE;

    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(longSize);
        buffer.putLong(0, x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(longSize);
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();
        return buffer.getLong();
    }

    public static long bytesToLong(byte[] bytes, ByteOrder byteOrder) {
        ByteBuffer buffer = ByteBuffer.allocate(longSize);
        buffer.order(byteOrder);
        buffer.put(bytes, 0, bytes.length);
        return buffer.getLong();
    }

    public static byte[] shortToBytes(short x, ByteOrder byteOrder) {
        ByteBuffer bufferShort = ByteBuffer.allocate(shortSize);
        bufferShort.putShort(0, x).order(byteOrder);
        return bufferShort.array();
    }

    public static short bytesToShort(byte[] bytes, ByteOrder byteOrder) {
        ByteBuffer bufferShort = ByteBuffer.allocate(2);
        bufferShort.order(byteOrder);
        bufferShort.put(bytes);
        return bufferShort.getShort(0);
    }

    public static byte[] intToByteArray(int value, ByteOrder byteOrder) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(byteOrder);
        buffer.putInt(value);
        return buffer.array();
    }

    public static byte[] removeFirstByte(byte[] array) {
        if (array == null || array.length <= 1) {
            return new byte[0];
        }

        byte[] result = new byte[array.length - 1];
        System.arraycopy(array, 1, result, 0, result.length);
        return result;
    }

    public static byte[] removeFirstBytes(byte[] array, int lengthToRemove) {
        if (array == null || array.length <= lengthToRemove) {
            return new byte[0];
        }

        byte[] result = new byte[array.length - lengthToRemove];
        System.arraycopy(array, lengthToRemove, result, 0, result.length);
        return result;
    }

    public static byte[] joinArrays(byte[] array1, byte[] array2) {
        int joinedLength = array1.length + array2.length;

        byte[] joinedArray = new byte[joinedLength];

        System.arraycopy(array1, 0, joinedArray, 0, array1.length);

        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);

        return joinedArray;
    }

}