package com.shimmerresearch.driverUtilities;

import com.shimmerresearch.driverUtilities.ChannelDetails;

/* loaded from: classes2.dex */
public class UtilParseData {
    public static boolean mIsDebugEnabled = false;

    public static long parseData(byte[] bArr, ChannelDetails.CHANNEL_DATA_TYPE channel_data_type, ChannelDetails.CHANNEL_DATA_ENDIAN channel_data_endian) {
        long jCalculatetwoscomplement = 0;
        if (channel_data_type == ChannelDetails.CHANNEL_DATA_TYPE.UNKOWN) {
            consolePrintLnDebugging("Unknown data type!");
            return 0L;
        }
        consolePrintLnDebugging("Parsing:\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(bArr) + "\twith\t" + channel_data_type + "\t&\t" + channel_data_endian);
        if (bArr.length < channel_data_type.getNumBytes()) {
            consolePrintLnDebugging("Parsing error, not enough bytes");
            return 0L;
        }
        if (channel_data_endian == ChannelDetails.CHANNEL_DATA_ENDIAN.LSB) {
            reverse(bArr);
            consolePrintLnDebugging("Reversed data:\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(bArr));
        }
        if (channel_data_type.getNumBytes() > 1) {
            for (int i = 0; i < channel_data_type.getNumBytes(); i++) {
                if (i == 0) {
                    jCalculatetwoscomplement = bArr[i] & 255;
                } else {
                    jCalculatetwoscomplement = (jCalculatetwoscomplement << 8) | (255 & bArr[i]);
                }
            }
            long jPow = (long) Math.pow(2.0d, channel_data_type.getNumBits());
            if (channel_data_type.getNumBits() < 64) {
                jPow--;
            }
            consolePrintLnDebugging("Mask to apply:\t" + Long.toHexString(jPow));
            jCalculatetwoscomplement &= jPow;
        } else if (channel_data_type.getNumBytes() == 1) {
            jCalculatetwoscomplement = bArr[0] & 255;
        }
        if (channel_data_type.isSigned()) {
            jCalculatetwoscomplement = calculatetwoscomplement(jCalculatetwoscomplement, channel_data_type.getNumBits());
        }
        consolePrintLnDebugging("Parsing result:\t" + jCalculatetwoscomplement);
        return jCalculatetwoscomplement;
    }

    public static void consolePrintLnDebugging(String str) {
        if (mIsDebugEnabled) {
            System.out.println(str);
        }
    }

    @Deprecated
    public static long[] parseData(byte[] bArr, String[] strArr) {
        long[] jArr;
        int i;
        String[] strArr2 = strArr;
        long[] jArr2 = new long[strArr2.length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < strArr2.length) {
            String str = strArr2[i2];
            if (str == "u8") {
                jArr2[i2] = bArr[i3] & 255;
            } else if (str == "i8") {
                jArr2[i2] = calculatetwoscomplement(bArr[i3] & 255, 8);
            } else {
                if (str == "u12" || str == "u14") {
                    jArr2[i2] = (bArr[i3] & 255) + ((bArr[i3 + 1] & 255) << 8);
                } else if (str == "i12>") {
                    long jCalculatetwoscomplement = calculatetwoscomplement((bArr[i3] & 255) + ((bArr[i3 + 1] & 255) << 8), 16);
                    jArr2[i2] = jCalculatetwoscomplement;
                    jArr2[i2] = jCalculatetwoscomplement >> 4;
                } else if (str == "u16") {
                    jArr2[i2] = (bArr[i3] & 255) + ((bArr[i3 + 1] & 255) << 8);
                } else if (str == "u16r") {
                    jArr2[i2] = (bArr[i3 + 1] & 255) + ((bArr[i3] & 255) << 8);
                } else if (str == "i16") {
                    jArr2[i2] = calculatetwoscomplement((bArr[i3] & 255) + ((bArr[i3 + 1] & 255) << 8), 16);
                } else if (str == "i16r") {
                    jArr2[i2] = calculatetwoscomplement((bArr[i3 + 1] & 255) + ((bArr[i3] & 255) << 8), 16);
                } else {
                    if (str == "u24r") {
                        jArr2[i2] = ((bArr[i3] & 255) << 16) + ((bArr[i3 + 1] & 255) << 8) + (bArr[i3 + 2] & 255);
                    } else if (str == "u24") {
                        jArr2[i2] = ((bArr[i3 + 2] & 255) << 16) + ((bArr[i3 + 1] & 255) << 8) + (bArr[i3] & 255);
                    } else if (str == "i24r") {
                        jArr2[i2] = calculatetwoscomplement((int) (((bArr[i3] & 255) << 16) + ((bArr[i3 + 1] & 255) << 8) + (bArr[i3 + 2] & 255)), 24);
                    } else {
                        if (str == "u32signed") {
                            long j = bArr[i3] & 255;
                            jArr = jArr2;
                            jArr[i2] = (1 - ((j != 255 ? j : 0L) * 2)) * (((bArr[i3 + 4] & 255) << 24) + ((bArr[i3 + 3] & 255) << 16) + ((bArr[i3 + 2] & 255) << 8) + (bArr[i3 + 1] & 255));
                            i3 += 5;
                        } else {
                            jArr = jArr2;
                            if (str == "u32") {
                                jArr[i2] = ((bArr[i3 + 3] & 255) << 24) + ((bArr[i3 + 2] & 255) << 16) + ((bArr[i3 + 1] & 255) << 8) + (bArr[i3] & 255);
                            } else if (str == "u32r") {
                                jArr[i2] = ((bArr[i3] & 255) << 24) + ((bArr[i3 + 1] & 255) << 16) + ((bArr[i3 + 2] & 255) << 8) + (bArr[i3 + 3] & 255);
                            } else if (str == "i32") {
                                jArr[i2] = calculatetwoscomplement(((bArr[i3 + 3] & 255) << 24) + ((bArr[i3 + 2] & 255) << 16) + ((bArr[i3 + 1] & 255) << 8) + (bArr[i3] & 255), 32);
                            } else if (str == "i32r") {
                                jArr[i2] = calculatetwoscomplement(((bArr[i3] & 255) << 24) + ((bArr[i3 + 1] & 255) << 16) + ((bArr[i3 + 2] & 255) << 8) + (bArr[i3 + 3] & 255), 32);
                            } else {
                                if (str == "u72") {
                                    long j2 = bArr[i3] & 255;
                                    long j3 = j2 != 255 ? j2 : 0L;
                                    long j4 = (bArr[i3 + 6] & 255) << 40;
                                    long j5 = (bArr[i3 + 5] & 255) << 32;
                                    long j6 = (bArr[i3 + 4] & 255) << 24;
                                    i = i2;
                                    jArr[i] = (1 - (j3 * 2)) * (((bArr[i3 + 8] & 15) << 56) + ((bArr[i3 + 7] & 255) << 48) + j4 + j5 + j6 + ((bArr[i3 + 3] & 255) << 16) + ((bArr[i3 + 2] & 255) << 8) + (bArr[i3 + 1] & 255));
                                    i3 += 9;
                                } else {
                                    i = i2;
                                    int i4 = i3;
                                    if (str == "i12*>") {
                                        long j7 = ((bArr[i4] & 255) << 4) | ((bArr[i4 + 1] & 255) >> 4);
                                        jArr[i] = j7;
                                        jArr[i] = calculatetwoscomplement(j7, 12);
                                        i3 = i4 + 2;
                                    } else {
                                        i3 = i4;
                                    }
                                }
                                i2 = i + 1;
                                strArr2 = strArr;
                                jArr2 = jArr;
                            }
                            i3 += 4;
                        }
                        i = i2;
                        i2 = i + 1;
                        strArr2 = strArr;
                        jArr2 = jArr;
                    }
                    i3 += 3;
                    jArr = jArr2;
                    i = i2;
                    i2 = i + 1;
                    strArr2 = strArr;
                    jArr2 = jArr;
                }
                i3 += 2;
                jArr = jArr2;
                i = i2;
                i2 = i + 1;
                strArr2 = strArr;
                jArr2 = jArr;
            }
            i3++;
            jArr = jArr2;
            i = i2;
            i2 = i + 1;
            strArr2 = strArr;
            jArr2 = jArr;
        }
        return jArr2;
    }

    @Deprecated
    public static int[] formatDataPacketReverse(byte[] bArr, String[] strArr) {
        int[] iArr = new int[strArr.length];
        int i = 0;
        int i2 = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            if (str == "u8") {
                iArr[i] = bArr[i2];
            } else if (str == "i8") {
                iArr[i] = calculatetwoscomplement(bArr[i2] & 255, 8);
            } else {
                if (str == "u12" || str == "u16") {
                    iArr[i] = (bArr[i2 + 1] & 255) + ((bArr[i2] & 255) << 8);
                } else if (str == "i16") {
                    iArr[i] = calculatetwoscomplement((bArr[i2 + 1] & 255) + ((bArr[i2] & 255) << 8), 16);
                } else {
                    i++;
                }
                i2 += 2;
                i++;
            }
            i2++;
            i++;
        }
        return iArr;
    }

    public static void reverse(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        int length = bArr.length - 1;
        for (int i = 0; length > i; i++) {
            byte b = bArr[length];
            bArr[length] = bArr[i];
            bArr[i] = b;
            length--;
        }
    }

    public static int calculatetwoscomplement(int i, int i2) {
        return i >= (1 << (i2 + (-1))) ? -((i ^ ((int) (Math.pow(2.0d, i2) - 1.0d))) + 1) : i;
    }

    public static long calculatetwoscomplement(long j, int i) {
        return j >= (1 << (i + (-1))) ? -((j ^ ((long) (Math.pow(2.0d, i) - 1.0d))) + 1) : j;
    }

    public static int countBytesFromDataTypes(String[] strArr) {
        int i = 0;
        for (String str : strArr) {
            if (str != null) {
                str.hashCode();
                switch (str) {
                    case "u32signed":
                        i += 5;
                        break;
                    case "i8":
                    case "u8":
                        i++;
                        break;
                    case "i16":
                    case "u12":
                    case "u14":
                    case "u16":
                    case "i12>":
                    case "i16r":
                    case "u16r":
                    case "i12*>":
                        i += 2;
                        break;
                    case "i32":
                    case "u32":
                    case "i32r":
                    case "u32r":
                        i += 4;
                        break;
                    case "u24":
                    case "i24r":
                    case "u24r":
                        i += 3;
                        break;
                    case "u72":
                        i += 9;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown data type: " + str);
                }
            }
        }
        return i;
    }
}
