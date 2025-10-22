package org.bouncycastle.crypto.generators;

import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.sensors.lsm6dsv.SensorLSM6DSV;

import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.Set;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* loaded from: classes5.dex */
public class OpenBSDBCrypt {
    private static final Set<String> allowedVersions;
    private static final String defaultVersion = "2y";
    private static final byte[] encodingTable = {ShimmerObject.GET_FW_VERSION_COMMAND, ShimmerObject.FW_VERSION_RESPONSE, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, 82, 83, 84, 85, 86, 87, 88, 89, 90, ShimmerObject.SET_EXG_REGS_COMMAND, ShimmerObject.EXG_REGS_RESPONSE, ShimmerObject.GET_EXG_REGS_COMMAND, 100, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, 103, 104, 105, ShimmerObject.SET_BAUD_RATE_COMMAND, ShimmerObject.BAUD_RATE_RESPONSE, ShimmerObject.GET_BAUD_RATE_COMMAND, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, ShimmerObject.START_SDBT_COMMAND, ShimmerObject.STATUS_RESPONSE, ShimmerObject.GET_STATUS_COMMAND, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, ShimmerObject.TRIAL_CONFIG_RESPONSE, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, ShimmerObject.SET_CENTER_COMMAND, ShimmerObject.CENTER_RESPONSE, ShimmerObject.GET_CENTER_COMMAND, ShimmerObject.SET_SHIMMERNAME_COMMAND, ShimmerObject.SHIMMERNAME_RESPONSE, ShimmerObject.SET_BLINK_LED, ShimmerObject.BLINK_LED_RESPONSE, ShimmerObject.GET_BLINK_LED, 51, ShimmerObject.SET_BUFFER_SIZE_COMMAND, ShimmerObject.BUFFER_SIZE_RESPONSE, ShimmerObject.GET_BUFFER_SIZE_COMMAND, 55, 56, 57};
    private static final byte[] decodingTable = new byte[128];

    static {
        HashSet hashSet = new HashSet();
        allowedVersions = hashSet;
        hashSet.add("2a");
        hashSet.add(defaultVersion);
        hashSet.add("2b");
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = decodingTable;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = encodingTable;
            if (i >= bArr2.length) {
                return;
            }
            decodingTable[bArr2[i]] = (byte) i;
            i++;
        }
    }

    public static boolean checkPassword(String str, char[] cArr) throws NumberFormatException {
        if (str.length() != 60) {
            throw new DataLengthException("Bcrypt String length: " + str.length() + ", 60 required.");
        }
        if (str.charAt(0) != '$' || str.charAt(3) != '$' || str.charAt(6) != '$') {
            throw new IllegalArgumentException("Invalid Bcrypt String format.");
        }
        String strSubstring = str.substring(1, 3);
        if (!allowedVersions.contains(strSubstring)) {
            throw new IllegalArgumentException("Bcrypt version '" + strSubstring + "' is not supported by this implementation");
        }
        String strSubstring2 = str.substring(4, 6);
        try {
            int i = Integer.parseInt(strSubstring2);
            if (i >= 4 && i <= 31) {
                if (cArr != null) {
                    return str.equals(generate(strSubstring, cArr, decodeSaltString(str.substring(str.lastIndexOf(36) + 1, str.length() - 31)), i));
                }
                throw new IllegalArgumentException("Missing password.");
            }
            throw new IllegalArgumentException("Invalid cost factor: " + i + ", 4 < cost < 31 expected.");
        } catch (NumberFormatException unused) {
            throw new IllegalArgumentException("Invalid cost factor: " + strSubstring2);
        }
    }

    private static String createBcryptString(String str, byte[] bArr, byte[] bArr2, int i) {
        String string;
        if (!allowedVersions.contains(str)) {
            throw new IllegalArgumentException("Version " + str + " is not accepted by this implementation.");
        }
        StringBuffer stringBuffer = new StringBuffer(60);
        stringBuffer.append('$');
        stringBuffer.append(str);
        stringBuffer.append('$');
        if (i < 10) {
            string = "0" + i;
        } else {
            string = Integer.toString(i);
        }
        stringBuffer.append(string);
        stringBuffer.append('$');
        stringBuffer.append(encodeData(bArr2));
        stringBuffer.append(encodeData(BCrypt.generate(bArr, bArr2, i)));
        return stringBuffer.toString();
    }

    private static byte[] decodeSaltString(String str) {
        char[] charArray = str.toCharArray();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16);
        if (charArray.length != 22) {
            throw new DataLengthException("Invalid base64 salt length: " + charArray.length + " , 22 required.");
        }
        for (char c : charArray) {
            if (c > 'z' || c < '.' || (c > '9' && c < 'A')) {
                throw new IllegalArgumentException("Salt string contains invalid character: " + ((int) c));
            }
        }
        char[] cArr = new char[24];
        System.arraycopy(charArray, 0, cArr, 0, charArray.length);
        for (int i = 0; i < 24; i += 4) {
            byte[] bArr = decodingTable;
            byte b = bArr[cArr[i]];
            byte b2 = bArr[cArr[i + 1]];
            byte b3 = bArr[cArr[i + 2]];
            byte b4 = bArr[cArr[i + 3]];
            byteArrayOutputStream.write((b << 2) | (b2 >> 4));
            byteArrayOutputStream.write((b2 << 4) | (b3 >> 2));
            byteArrayOutputStream.write(b4 | (b3 << 6));
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byte[] bArr2 = new byte[16];
        System.arraycopy(byteArray, 0, bArr2, 0, 16);
        return bArr2;
    }

    private static String encodeData(byte[] bArr) {
        boolean z;
        if (bArr.length != 24 && bArr.length != 16) {
            throw new DataLengthException("Invalid length: " + bArr.length + ", 24 for key or 16 for salt expected");
        }
        if (bArr.length == 16) {
            byte[] bArr2 = new byte[18];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            bArr = bArr2;
            z = true;
        } else {
            bArr[bArr.length - 1] = 0;
            z = false;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = bArr.length;
        for (int i = 0; i < length; i += 3) {
            int i2 = bArr[i] & 255;
            int i3 = bArr[i + 1] & 255;
            byte b = bArr[i + 2];
            byte[] bArr3 = encodingTable;
            byteArrayOutputStream.write(bArr3[(i2 >>> 2) & 63]);
            byteArrayOutputStream.write(bArr3[((i2 << 4) | (i3 >>> 4)) & 63]);
            byteArrayOutputStream.write(bArr3[((i3 << 2) | ((b & 255) >>> 6)) & 63]);
            byteArrayOutputStream.write(bArr3[b & ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW]);
        }
        String strFromByteArray = Strings.fromByteArray(byteArrayOutputStream.toByteArray());
        return strFromByteArray.substring(0, z ? 22 : strFromByteArray.length() - 1);
    }

    public static String generate(String str, char[] cArr, byte[] bArr, int i) {
        if (!allowedVersions.contains(str)) {
            throw new IllegalArgumentException("Version " + str + " is not accepted by this implementation.");
        }
        if (cArr == null) {
            throw new IllegalArgumentException("Password required.");
        }
        if (bArr == null) {
            throw new IllegalArgumentException("Salt required.");
        }
        if (bArr.length != 16) {
            throw new DataLengthException("16 byte salt required: " + bArr.length);
        }
        if (i < 4 || i > 31) {
            throw new IllegalArgumentException("Invalid cost factor.");
        }
        byte[] uTF8ByteArray = Strings.toUTF8ByteArray(cArr);
        int length = uTF8ByteArray.length < 72 ? uTF8ByteArray.length + 1 : 72;
        byte[] bArr2 = new byte[length];
        if (length > uTF8ByteArray.length) {
            length = uTF8ByteArray.length;
        }
        System.arraycopy(uTF8ByteArray, 0, bArr2, 0, length);
        Arrays.fill(uTF8ByteArray, (byte) 0);
        String strCreateBcryptString = createBcryptString(str, bArr2, bArr, i);
        Arrays.fill(bArr2, (byte) 0);
        return strCreateBcryptString;
    }

    public static String generate(char[] cArr, byte[] bArr, int i) {
        return generate(defaultVersion, cArr, bArr, i);
    }
}
