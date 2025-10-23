package com.shimmerresearch.driverUtilities;

import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.verisense.UtilVerisenseDriver;

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class UtilShimmer implements Serializable {
    public static final String CHECK_MARK_STRING = " ✓";
    public static final String CROSS_MARK_STRING = "  x";
    public static final String MAC_ADDRESS_FFFFS = "FFFFFFFFFFFF";
    public static final String MAC_ADDRESS_ZEROS = "000000000000";
    public static final String STRING_CONSTANT_FOR_BUTTON_EVENT = "EVENT BUTTON PRESSED: ";
    public static final String STRING_CONSTANT_FOR_UNKNOWN = "Unknown";
    public static final String UNICODE_APPROX_EQUAL = "≈";
    public static final String UNICODE_CHECK_MARK = "✓";
    public static final String UNICODE_CROSS_MARK = "❌";
    public static final String UNICODE_MICRO = "µ";
    public static final String UNICODE_OHMS = "Ω";
    public static final String UNICODE_PLUS_MINUS = "±";
    protected static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static final long serialVersionUID = -3892204042703820796L;
    public Boolean mDebugMode;
    public String mParentClassName;
    public Boolean mVerboseMode;

    public UtilShimmer(String str, Boolean bool) {
        this.mParentClassName = "UpdateCheck";
        this.mDebugMode = true;
        Boolean.valueOf(true);
        this.mParentClassName = str;
        this.mVerboseMode = bool;
    }

    public UtilShimmer(String str, boolean z, boolean z2) {
        this(str, Boolean.valueOf(z));
        this.mDebugMode = Boolean.valueOf(z2);
    }

    public static boolean compareVersions(int i, int i2, int i3, int i4, int i5, int i6) {
        if (i > i4) {
            return true;
        }
        if (i != i4 || i2 <= i5) {
            return i == i4 && i2 == i5 && i3 >= i6;
        }
        return true;
    }

    public static byte[] generateRadioConfigByteArray(int i, int i2, int i3, int i4) {
        return new byte[]{(byte) (i & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255), (byte) ((i3 >> 8) & 255), (byte) (i3 & 255), (byte) ((i4 >> 8) & 255), (byte) (i4 & 255)};
    }

    public static String getCheckOrCrossForBoolean(boolean z) {
        return z ? CHECK_MARK_STRING : CROSS_MARK_STRING;
    }

    public static String getDayString(int i) {
        switch (i) {
            case 1:
                return "Sun";
            case 2:
                return "Mon";
            case 3:
                return "Tue";
            case 4:
                return "Wed";
            case 5:
                return "Thur";
            case 6:
                return "Fri";
            case 7:
                return "Sat";
            default:
                return "";
        }
    }

    private static String getMonthString(int i) {
        switch (i) {
            case 0:
                return "Jan";
            case 1:
                return "Feb";
            case 2:
                return "Mar";
            case 3:
                return "Apr";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "Aug";
            case 8:
                return "Sept";
            case 9:
                return "Oct";
            case 10:
                return "Nov";
            case 11:
                return "Dec";
            default:
                return "";
        }
    }

    public static boolean isAsciiPrintable(char c) {
        return c >= ' ' && c < 127;
    }

    public static boolean isBitSet(long j, int i) {
        return (j & (1 << i)) != 0;
    }

    public static String convertSecondsToDateString(long j) {
        return convertMilliSecondsToDateString(j * 1000, false);
    }

    public static String convertMilliSecondsToDateString(long j, boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i = calendar.get(5);
        String dayOfMonthSuffix = getDayOfMonthSuffix(i);
        String monthString = getMonthString(calendar.get(2));
        return new SimpleDateFormat(z ? "//yyyy HH:mm:ss.SSS" : "//yyyy HH:mm:ss").format(new Date(j)).replaceFirst("//", i + dayOfMonthSuffix + StringUtils.SPACE + monthString + StringUtils.SPACE);
    }

    public static String convertMilliSecondsToHrMinSecUTC(long j) {
        return convertMilliSecondsToUTC(j, "HH:mm:ss");
    }

    public static String convertMilliSecondsToHrMinSecMilliSecUTC(long j) {
        return convertMilliSecondsToUTC(j, "HH:mm:ss.SSS");
    }

    private static String convertMilliSecondsToUTC(long j, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(new Date(j));
    }

    public static String convertMilliSecondsToHrMinSecLocal(long j) {
        return convertMilliSecondsToFormat(j, "HH:mm:ss", false);
    }

    public static String convertMilliSecondsToHrMinSecMilliSecLocal(long j) {
        return convertMilliSecondsToFormat(j, "HH:mm:ss.SSS", false);
    }

    public static String convertMilliSecondsToFormat(long j, String str, boolean z) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        if (z) {
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        return j == 0 ? "00:00:00" : simpleDateFormat.format(new Date(j));
    }

    private static String getDayOfMonthSuffix(int i) {
        if (i >= 11 && i <= 13) {
            return "th";
        }
        int i2 = i % 10;
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? "th" : "rd" : "nd" : "st";
    }

    public static String convertBytesToReadableString(double d) {
        String str;
        double d2 = ((d / 1024.0d) / 1024.0d) / 1024.0d;
        if (d2 < 0.001d) {
            d2 = d2 * 1024.0d * 1024.0d;
            str = " KB";
        } else if (d2 < 1.0d) {
            d2 *= 1024.0d;
            str = " MB";
        } else {
            str = " GB";
        }
        return String.format("%.2f", Double.valueOf(d2)) + str;
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static String bytesToHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            char[] cArr2 = hexArray;
            cArr[i2] = cArr2[(b & 255) >>> 4];
            cArr[i2 + 1] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    public static String byteToHexString(byte b) {
        char[] cArr = hexArray;
        return new String(new char[]{cArr[(b & 255) >>> 4], cArr[b & 15]});
    }

    public static String bytesToHexStringWithSpaces(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        char[] cArr = new char[bArr.length * 3];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 3;
            char[] cArr2 = hexArray;
            cArr[i2] = cArr2[(b & 255) >>> 4];
            cArr[i2 + 1] = cArr2[b & 15];
            cArr[i2 + 2] = ' ';
        }
        return new String(cArr);
    }

    public static String byteToHexStringFormatted(byte b) {
        char[] cArr = hexArray;
        return "[" + new String(new char[]{'0', 'x', cArr[(b & 255) >>> 4], cArr[b & 15]}) + "]";
    }

    public static String intToHexStringFormatted(int i, int i2, boolean z) {
        byte[] bArrIntToByteArrayLsb;
        if (z) {
            bArrIntToByteArrayLsb = intToByteArrayMsb(i, i2);
        } else {
            bArrIntToByteArrayLsb = intToByteArrayLsb(i, i2);
        }
        char[] cArr = new char[(i2 * 2) + 2];
        cArr[0] = '0';
        cArr[1] = 'x';
        int i3 = 2;
        for (byte b : bArrIntToByteArrayLsb) {
            char[] cArr2 = hexArray;
            cArr[i3] = cArr2[(b & 255) >>> 4];
            cArr[i3 + 1] = cArr2[b & 15];
            i3 += 2;
        }
        return new String(cArr);
    }

    public static byte[] intToByteArrayLsb(int i, int i2) {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) (i >> (i3 * 8));
        }
        return bArr;
    }

    public static byte[] intToByteArrayMsb(int i, int i2) {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) (i >> (((i2 - 1) - i3) * 8));
        }
        return bArr;
    }

    public static String bytesToHexStringWithSpacesFormatted(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        char[] cArr = new char[(bArr.length * 5) - 1];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 5;
            cArr[i2] = '0';
            cArr[i2 + 1] = 'x';
            char[] cArr2 = hexArray;
            cArr[i2 + 2] = cArr2[(b & 255) >>> 4];
            cArr[i2 + 3] = cArr2[b & 15];
            if (i != bArr.length - 1) {
                cArr[i2 + 4] = ' ';
            }
        }
        return "[" + new String(cArr) + "]";
    }

    public static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[1];
        if (str.toCharArray().length == 0) {
            bArr[0] = 0;
        } else if (str.toCharArray().length == 1) {
            bArr[0] = (byte) Character.digit(str.charAt(0), 16);
        } else {
            bArr = new byte[length / 2];
            for (int i = 0; i < length; i += 2) {
                bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
            }
        }
        return bArr;
    }

    public static int hexStringToInt(String str) {
        int length = str.length();
        if (length == 0 || length > 8) {
            return 0;
        }
        if (length != 8 || Integer.parseInt(str.substring(0, 1), 16) <= 7) {
            return Integer.parseInt(str, 16);
        }
        return 0;
    }

    public static String convertByteToUnsignedIntegerString(byte b) {
        return Integer.toString(b & 255);
    }

    public static byte[] convertLongToByteArray(long j) {
        return ByteBuffer.allocate(8).putLong(j).array();
    }

    public static long convertByteArrayToLong(byte[] bArr) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.put(bArr);
        return byteBufferAllocate.getLong();
    }

    public static boolean compareVersions(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        if (i6 == -1 || i == i6) {
            return compareVersions(i2, i3, i4, i5, i7, i8, i9, i10);
        }
        return false;
    }

    public static boolean compareVersions(ShimmerVerObject shimmerVerObject, ShimmerVerObject shimmerVerObject2) {
        int i = shimmerVerObject.mHardwareVersion;
        int i2 = shimmerVerObject.mFirmwareIdentifier;
        int i3 = shimmerVerObject.mFirmwareVersionMajor;
        int i4 = shimmerVerObject.mFirmwareVersionMinor;
        int i5 = shimmerVerObject.mFirmwareVersionInternal;
        int i6 = shimmerVerObject2.mHardwareVersion;
        int i7 = shimmerVerObject2.mFirmwareIdentifier;
        int i8 = shimmerVerObject2.mFirmwareVersionMajor;
        int i9 = shimmerVerObject2.mFirmwareVersionMinor;
        int i10 = shimmerVerObject2.mFirmwareVersionInternal;
        if (i6 == -1 || i == i6) {
            return compareVersions(i2, i3, i4, i5, i7, i8, i9, i10);
        }
        return false;
    }

    public static boolean compareVersions(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i5 == -1 || i == i5) {
            return compareVersions(i2, i3, i4, i6, i7, i8);
        }
        return false;
    }

    public static boolean doesFirstBytesMatch(byte[] bArr, byte[] bArr2) {
        if (bArr.length < bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareVersions(String str, String str2, String str3, String str4, String str5, String str6) {
        try {
            return compareVersions(Integer.parseInt(str), Integer.parseInt(str2), Integer.parseInt(str3), Integer.parseInt(str4), Integer.parseInt(str5), Integer.parseInt(str6));
        } catch (NumberFormatException e) {
            System.out.println("UpdateChecker - Version parsing error");
            e.printStackTrace();
            return true;
        }
    }

    public static String convertDuration(int i) {
        double d = i / 1000;
        return String.format("%02d:%02d:%02d", Integer.valueOf((int) (d / 3600.0d)), Integer.valueOf((int) ((d % 3600.0d) / 60.0d)), Integer.valueOf((int) (d % 60.0d)));
    }

    public static String fromMilToDateExcelCompatible(String str, Boolean bool) {
        return fromMilToDataExcelCompatible(str, bool.booleanValue(), Integer.MAX_VALUE);
    }

    public static String fromMilToDataExcelCompatible(String str, boolean z, int i) throws NumberFormatException {
        String id;
        SimpleDateFormat simpleDateFormat;
        if (str == null) {
            return "null";
        }
        double d = Double.parseDouble(str);
        if (i != Integer.MAX_VALUE) {
            id = TimeZone.getDefault().getID();
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            d += i;
        } else {
            id = null;
        }
        Date date = new Date((long) d);
        if (z) {
            simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        } else {
            simpleDateFormat = new SimpleDateFormat(UtilVerisenseDriver.DATE_FORMAT_NO_MILLIS);
        }
        String str2 = simpleDateFormat.format(date);
        if (id != null) {
            TimeZone.setDefault(TimeZone.getTimeZone(id));
        }
        return str2;
    }

    public static long fromTimeStringToMilliseconds(String str) {
        try {
            return new SimpleDateFormat("HH:mm:ss:SSS").parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static long fromDateAndTimeMillisToTimeMilli(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm.ss.SSS");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        try {
            return simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime())).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static String getCurrentDateAndTimeFormatted() {
        return new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(Calendar.getInstance().getTime());
    }

    public static int getCurrentLocalTimezoneOffsetMillis() {
        return ZoneId.systemDefault().getRules().getOffset(Instant.now()).getTotalSeconds() * 1000;
    }

    public static int getLocalTimezoneOffsetMillisForSpecificDate(long j) {
        return ZoneId.systemDefault().getRules().getOffset(Instant.ofEpochMilli(j)).getTotalSeconds() * 1000;
    }

    public static File[] getArrayOfFilesWithFileType(File file, final String str) {
        return file.listFiles(new FilenameFilter() { // from class: com.shimmerresearch.driverUtilities.UtilShimmer.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str2) {
                int iLastIndexOf = str2.lastIndexOf(46);
                return (iLastIndexOf > Math.max(str2.lastIndexOf(47), str2.lastIndexOf(92)) ? str2.substring(iLastIndexOf + 1) : "").matches(str);
            }
        });
    }

    public static boolean stringContainsItemFromListUpperCaseCheck(String str, String[] strArr) {
        for (String str2 : strArr) {
            if (str.toUpperCase().contains(str2.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static boolean stringContainsItemFromList(String str, String[] strArr) {
        for (String str2 : strArr) {
            if (str.contains(str2)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] convertMilliSecondsToShimmerRtcDataBytesLSB(long j) {
        byte[] bArrConvertMilliSecondsToShimmerRtcDataBytesMSB = convertMilliSecondsToShimmerRtcDataBytesMSB(j);
        ArrayUtils.reverse(bArrConvertMilliSecondsToShimmerRtcDataBytesMSB);
        return bArrConvertMilliSecondsToShimmerRtcDataBytesMSB;
    }

    public static byte[] convertMilliSecondsToShimmerRtcDataBytesMSB(long j) {
        return ByteBuffer.allocate(8).putLong((long) (j * 32.768d)).array();
    }

    public static long convertShimmerRtcDataBytesToMilliSecondsLSB(byte[] bArr) {
        byte[] bArrAddAll = ArrayUtils.addAll(bArr, (byte[]) null);
        ArrayUtils.reverse(bArrAddAll);
        return convertShimmerRtcDataBytesToMilliSecondsMSB(bArrAddAll);
    }

    public static long convertShimmerRtcDataBytesToMilliSecondsMSB(byte[] bArr) {
        return (long) (ByteBuffer.wrap(bArr).getLong() / 32.768d);
    }

    public static String joinStrings(String[] strArr) {
        String str = "";
        for (int i = 0; i < strArr.length; i++) {
            if (i == 0) {
                str = strArr[i];
            } else {
                str = str + StringUtils.SPACE + strArr[i];
            }
        }
        return str;
    }

    public static double[][] deepCopyDoubleMatrix(double[][] dArr) {
        if (dArr == null) {
            return null;
        }
        double[][] dArr2 = new double[dArr.length][];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = (double[]) dArr[i].clone();
        }
        return dArr2;
    }

    public static ArrayList<Double> sortByComparator(Map<Double, Double> map, final boolean z) {
        LinkedList<Map.Entry> linkedList = new LinkedList(map.entrySet());
        Collections.sort(linkedList, new Comparator<Map.Entry<Double, Double>>() { // from class: com.shimmerresearch.driverUtilities.UtilShimmer.2
            @Override // java.util.Comparator
            public int compare(Map.Entry<Double, Double> entry, Map.Entry<Double, Double> entry2) {
                if (z) {
                    return entry.getValue().compareTo(entry2.getValue());
                }
                return entry2.getValue().compareTo(entry.getValue());
            }
        });
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : linkedList) {
            linkedHashMap.put((Double) entry.getKey(), (Double) entry.getValue());
        }
        return new ArrayList<>(linkedHashMap.keySet());
    }

    public static boolean isValidMac(String str) {
        return (str == null || str.isEmpty() || str.equals(MAC_ADDRESS_FFFFS) || str.equals(MAC_ADDRESS_ZEROS)) ? false : true;
    }

    public static File createFileAndDeleteIfExists(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return file;
        }
        file.delete();
        return new File(str);
    }

    public static String getConfigTimeFromFullTrialName(String str) {
        return str.split("_")[r1.length - 1];
    }

    public static String convertStackTraceToString(StackTraceElement[] stackTraceElementArr) {
        Exception exc = new Exception();
        exc.setStackTrace(stackTraceElementArr);
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static String longToHexStringWithSpacesFormatted(long j, int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) ((j >> (i2 * 8)) & 255);
        }
        return bytesToHexStringWithSpacesFormatted(bArr);
    }

    public static String longToHexString(long j, int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) ((j >> (i2 * 8)) & 255);
        }
        return bytesToHexString(bArr);
    }

    public static String convertLongToHexString(long j) {
        return bytesToHexString(convertLongToByteArray(j));
    }

    public static String doubleArrayToString(double[][] dArr) {
        String str = "";
        for (double[] dArr2 : dArr) {
            int i = 0;
            while (true) {
                if (i < dArr2.length) {
                    str = str + dArr2[i] + "\t";
                    i++;
                }
            }
            str = str + StringUtils.LF;
        }
        return str;
    }

    public static double[][] nudgeDoubleArray(double d, double d2, int i, double[][] dArr) {
        for (int i2 = 0; i2 < dArr.length; i2++) {
            int i3 = 0;
            while (true) {
                double[] dArr2 = dArr[i2];
                if (i3 < dArr2.length) {
                    dArr2[i3] = nudgeDouble(dArr2[i3], d2, d);
                    double[] dArr3 = dArr[i2];
                    dArr3[i3] = applyPrecisionCorrection(dArr3[i3], i);
                    i3++;
                }
            }
        }
        return dArr;
    }

    public static double applyPrecisionCorrection(double d, int i) {
        return new BigDecimal(d).setScale(i, 4).doubleValue();
    }

    public static double round(double d, int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        return new BigDecimal(d).setScale(i, RoundingMode.HALF_UP).doubleValue();
    }

    public static double nudgeDouble(double d, double d2, double d3) {
        return Math.max(d2, Math.min(d3, d));
    }

    public static int nudgeInteger(int i, int i2, int i3) {
        return Math.max(i2, Math.min(i3, i));
    }

    public static long nudgeLong(long j, long j2, long j3) {
        return Math.max(j2, Math.min(j3, j));
    }

    public static boolean isAllZeros(double[][] dArr) {
        if (dArr == null) {
            return false;
        }
        for (int i = 0; i < dArr[1].length; i++) {
            for (int i2 = 0; i2 < dArr.length; i2++) {
                if (dArr[i][i2] != 0.0d) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isAnyValueOutsideRange(double[][] dArr, int i) {
        if (dArr == null) {
            return true;
        }
        for (int i2 = 0; i2 < dArr[1].length; i2++) {
            for (int i3 = 0; i3 < dArr.length; i3++) {
                if (Math.abs(dArr[i2][i3]) > i) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAllFF(byte[] bArr) {
        for (byte b : bArr) {
            if (b != -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllZeros(byte[] bArr) {
        for (byte b : bArr) {
            if (b != 0) {
                return false;
            }
        }
        return true;
    }

    public static String formatDouble(double d) {
        if (d == 0.0d || (d >= 1.0d && d <= 9999.0d)) {
            return Double.toString(round(d, 2));
        }
        if (d >= 0.001d && d < 1.0d) {
            return Double.toString(round(d, 3));
        }
        return new DecimalFormat("##0.0E0").format(d);
    }

    public static String formatDoubleToNdecimalPlaces(double d, int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("0");
        }
        String str = new DecimalFormat("#." + sb.toString()).format(d);
        int iIndexOf = str.indexOf(".");
        if (iIndexOf != 0) {
            return str.charAt(iIndexOf - 1) == '-' ? new StringBuilder(str).insert(1, "0").toString() : str;
        }
        return "0" + str;
    }

    public static double calculateDistanceFromRssi(long j, double d) {
        return Math.pow(10.0d, (d - j) / 20.0d);
    }

    public static StackTraceElement[] getCurrentStackTrace() {
        return Thread.currentThread().getStackTrace();
    }

    public static void consolePrintCurrentStackTrace() {
        System.out.println(convertStackTraceToString(getCurrentStackTrace()));
    }

    public static boolean doesFileExist(String str) {
        return new File(str).exists();
    }

    public static byte[] interleaveByteArrays(byte[] bArr, byte[] bArr2) {
        int length = bArr.length + bArr2.length;
        byte[] bArr3 = new byte[length];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length / 2; i4++) {
            if (i4 % 2 == 0) {
                bArr3[i] = bArr[i3];
                bArr3[i + 1] = bArr[i3 + 1];
                i3 += 2;
            } else {
                bArr3[i] = bArr2[i2];
                bArr3[i + 1] = bArr2[i2 + 1];
                i2 += 2;
            }
            i += 2;
        }
        return bArr3;
    }

    public static byte[] my_int_to_bb_le(int i) {
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(i).array();
    }

    public static int my_bb_to_int_le(byte[] bArr) {
        return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    public static byte[] my_int_to_bb_be(int i) {
        return ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(i).array();
    }

    public static int my_bb_to_int_be(byte[] bArr) {
        return ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).getInt();
    }

    public static List<ShimmerDevice> cloneShimmerDevices(List<ShimmerDevice> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<ShimmerDevice> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().deepClone());
        }
        return arrayList;
    }

    public static List<String> getListOfUniqueIdsFromShimmerDevices(List<ShimmerDevice> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<ShimmerDevice> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().getUniqueId());
        }
        return arrayList;
    }

    public static String removeCheckMarkIfPresent(String str) {
        return str.replace(CHECK_MARK_STRING, "").replace(CROSS_MARK_STRING, "");
    }

    public static int[] generateSawToothIntSignal(int i, int i2, int i3, int i4, int i5) {
        int[] iArr = new int[i3];
        iArr[0] = i5;
        for (int i6 = 1; i6 < i3; i6++) {
            int i7 = iArr[i6 - 1] + i4;
            iArr[i6] = i7;
            if (i7 > i2) {
                iArr[i6] = i;
            }
        }
        return iArr;
    }

    public static int generateRandomIntInRange(int i, int i2) {
        return new Random().nextInt(i2 - i) + i;
    }

    public static int getBitSetBinaryValue(long j, int i) {
        return isBitSet(j, i) ? 1 : 0;
    }

    public static String getParsedSamplingRateWithTwoDecimalPlaces(double d) {
        String string = Double.toString(d);
        String[] strArrSplit = String.valueOf(d).split("\\.");
        if (strArrSplit.length < 2 || strArrSplit[1].length() <= 2) {
            return string;
        }
        String str = strArrSplit[0] + ".";
        for (int i = 0; i < 2; i++) {
            str = str + strArrSplit[1].charAt(i);
        }
        return str;
    }

    public static String getTimeZoneID() {
        return TimeZone.getDefault().getID();
    }

    public static int getTimeZoneOffset() {
        return TimeZone.getDefault().getOffset(System.currentTimeMillis());
    }

    public void setParentClassName(String str) {
        this.mParentClassName = str;
    }

    public void consolePrintLn(Object obj) {
        if (this.mVerboseMode.booleanValue()) {
            System.out.println(generateConsolePrintHeader() + obj);
        }
    }

    public void consolePrintErrLn(Object obj) {
        System.err.println(generateConsolePrintHeader() + obj);
    }

    public void consolePrintExeptionLn(String str, StackTraceElement[] stackTraceElementArr) {
        if (this.mVerboseMode.booleanValue()) {
            consolePrintErrLn(str + StringUtils.LF + convertStackTraceToString(stackTraceElementArr));
        }
    }

    public void consolePrintShimmerException(ShimmerException shimmerException) {
        if (this.mVerboseMode.booleanValue()) {
            consolePrintErrLn(shimmerException.getErrStringFormatted());
        }
    }

    private String generateConsolePrintHeader() {
        Calendar calendar = Calendar.getInstance();
        return ("[" + String.format("%02d", Integer.valueOf(calendar.get(11))) + ":" + String.format("%02d", Integer.valueOf(calendar.get(12))) + ":" + String.format("%02d", Integer.valueOf(calendar.get(13))) + ":" + String.format("%03d", Integer.valueOf(calendar.get(14))) + "]") + StringUtils.SPACE + this.mParentClassName + ": ";
    }

    public void consolePrint(String str) {
        if (this.mVerboseMode.booleanValue()) {
            System.out.print(str);
        }
    }

    public void consolePrintLnDebug(String str) {
        if (this.mDebugMode.booleanValue()) {
            consolePrintLn(str);
        }
    }

    public void setVerboseMode(boolean z) {
        this.mVerboseMode = Boolean.valueOf(z);
    }

    public String fromMilToDate(double d) {
        return new SimpleDateFormat(UtilVerisenseDriver.DATE_FORMAT_NO_MILLIS).format(new Date((long) d));
    }

    public String fromSecondsToDate(String str) {
        return new SimpleDateFormat(UtilVerisenseDriver.DATE_FORMAT_NO_MILLIS).format(new Date((long) (Double.valueOf(str).doubleValue() * 1000.0d)));
    }

    public void threadSleep(long j) throws InterruptedException {
        millisecondDelay(j);
    }

    public void millisecondDelay(long j) throws InterruptedException {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
            consolePrintLn("Thread sleep FAIL");
        }
    }

    public void nanosecondDelay(int i) throws InterruptedException {
        try {
            Thread.sleep(0L, i);
        } catch (InterruptedException unused) {
            consolePrintLn("Thread sleep FAIL");
        }
    }

    public enum ENUM_FILE_DELIMITERS {
        TAB("\t", "tab (\\t)"),
        COMMA(UtilVerisenseDriver.CSV_DELIMITER, "Comma (,)"),
        SEMI_COLON(";", "Semicolon (;)");

        public String delimiter;
        public String guiFriendlyName;

        ENUM_FILE_DELIMITERS(String str, String str2) {
            this.delimiter = str;
            this.guiFriendlyName = str2;
        }
    }

    public enum ENUM_FILE_FORMAT {
        CSV(UtilVerisenseDriver.FILE_EXTENSION.CSV),
        DAT(".dat"),
        TXT(".txt"),
        MAT(".mat");

        public String fileExtension;

        ENUM_FILE_FORMAT(String str) {
            this.fileExtension = str;
        }
    }

    public static class SHIMMER_DEFAULT_COLOURS {
        public static final int[] colourShimmerOrange = {241, 93, 34};
        public static final int[] colourBrown = {153, 76, 0};
        public static final int[] colourCyanAqua = {0, 153, 153};
        public static final int[] colourPurple = {102, 0, 204};
        public static final int[] colourMaroon = {102, 0, 0};
        public static final int[] colourGreen = {0, 153, 76};
        public static final int[] colourShimmerGrey = {119, 120, 124};
        public static final int[] colourShimmerBlue = {0, 129, 198};
        public static final int[] colourLightRed = {255, 0, 0};
    }

    public static class UNICODE_CHAR {
        public static final String ARROW_FROM_START = "↦";
        public static final String ARROW_TO_END = "⇥";
    }
}
