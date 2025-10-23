package com.shimmerresearch.verisense;

import com.shimmerresearch.driver.Configuration;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class UtilVerisenseDriver implements Serializable {
    public static final String BAD_CRC_IN_FILE_NAME = "_BadCRC";
    public static final String CSV_DELIMITER = ",";
    public static final int CSV_HEADER_LINES_SEARCH_LIMIT = 17;
    public static final String CSV_HEADER_SEPARATOR = "----------------------------------------";
    public static final String CSV_HEADER_SEPARATOR_LEGACY = "--------------------------------------------------,,\"";
    public static final String DATE_FORMAT_DEBUGGING = "yyyy/MM/dd HH:mm:ss.SSS";
    public static final String DATE_FORMAT_FILENAME = "yyMMdd_HHmmss";
    public static final String DATE_FORMAT_IN_DB = "dd MMM yyyy HH:mm:ss:SSS";
    public static final String DATE_FORMAT_NO_MILLIS = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_SHORT_WITH_MILLIS = "yyMMdd HH:mm:ss.SSS";
    public static final String DATE_FORMAT_VERISENSE_HEADER = "yyyy/MM/dd HH:mm:ss.SSS";
    public static final String DATE_FORMAT_VERISENSE_HEADER_LEGACY = "dd/MM/yyyy HH:mm:ss.SSS";
    public static final String FEATURE_NOT_AVAILABLE = "N/A";
    public static final String FILE_FILTER_69 = "69";
    public static final String FILE_FILTER_70 = "70";
    public static final String FILE_FILTER_DUPLICATE = ").";
    public static final String FILE_FILTER_PREFIX_69;
    public static final String FILE_FILTER_PREFIX_70;
    public static final String LINE_SEPARATOR;
    public static final LocalTime LT23_59;
    public static final TimeZone TIME_ZONE;
    public static final ZoneId TIME_ZONE_ID;
    public static final int TIME_ZONE_OFFSET = 0;
    public static final String TIME_ZONE_STR = "GMT+0";
    public static final String UNAVAILABLE = "Unavailable";
    public static final DateTimeFormatter formatterDate;
    public static final DateTimeFormatter formatterDateTime;
    private static final long serialVersionUID = 3008337300454867021L;
    public static Calendar calendar = null;
    public static SimpleDateFormat sdfGgir = null;
    private static DateFormat dfForFileNameUtc = null;

    static {
        TimeZone timeZone = TimeZone.getTimeZone(TIME_ZONE_STR);
        TIME_ZONE = timeZone;
        TIME_ZONE_ID = ZoneId.of(TIME_ZONE_STR);
        LINE_SEPARATOR = System.getProperty("line.separator");
        calendar = Calendar.getInstance(timeZone);
        sdfGgir = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        formatterDate = DateTimeFormatter.ofPattern("yyMMdd");
        formatterDateTime = DateTimeFormatter.ofPattern(DATE_FORMAT_FILENAME);
        LT23_59 = LocalTime.of(23, 59, 59, 999999999);
        dfForFileNameUtc = new SimpleDateFormat(DATE_FORMAT_FILENAME);
        FILE_FILTER_PREFIX_69 = File.separator + FILE_FILTER_69;
        FILE_FILTER_PREFIX_70 = File.separator + FILE_FILTER_70;
    }

    public static String getDateFormatForVerisenseHeader(boolean z) {
        return z ? DATE_FORMAT_VERISENSE_HEADER_LEGACY : "yyyy/MM/dd HH:mm:ss.SSS";
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

    public static String binaryFileDirToDeviceLevelDir(String str) {
        return new File(str).getParentFile().getParent();
    }

    public static String binaryFileDirToParsedFileDir(String str) {
        new File(str);
        return binaryFileDirToParentDir(str) + ASM_DIRECTORY_NAMES.PARSED_FILES;
    }

    public static String binaryFileDirToParentDir(String str) {
        return new File(str).getParentFile() + "/";
    }

    public static String payloadIndexToString(int i) {
        return String.format("%05d", Integer.valueOf(i));
    }

    public static boolean isTransitionMidDayOrMidnight(double d, double d2) {
        return isTransistionMidday(d, d2) || isTransistionMidnight(d, d2);
    }

    public static boolean isTransistionMidday(double d, double d2) {
        return isTsBeforeMidday((long) d) && isTsAfterMidday((long) d2);
    }

    public static boolean isTransistionMidnight(double d, double d2) {
        return isTsAfterMidday((long) d) && isTsBeforeMidday((long) d2);
    }

    public static boolean isTsBeforeMidday(long j) {
        return millisToHrOfDay(j) < 12;
    }

    public static boolean isTsAfterMidday(long j) {
        return millisToHrOfDay(j) >= 12;
    }

    public static long millisToHrOfDay(long j) {
        return millisToPartOfDay(j, 11);
    }

    public static long millisToSecondsOfMinute(long j) {
        return millisToPartOfDay(j, 13);
    }

    public static long millisToPartOfDay(long j, int i) {
        calendar.setTime(new Date(j));
        return calendar.get(i);
    }

    public static String timeMsToString(double d) {
        return timeMsToString((long) d);
    }

    public static String timeMsToString(long j) {
        dfForFileNameUtc.setTimeZone(TIME_ZONE);
        return dfForFileNameUtc.format(new Date(j));
    }

    public static String convertMilliSecondsToDateString(long j, boolean z) {
        return convertMilliSecondsToDateString(j, z, TIME_ZONE_STR);
    }

    public static String convertMilliSecondsToDateString(long j, boolean z, String str) {
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j);
        int i = calendar2.get(5);
        String dayOfMonthSuffix = getDayOfMonthSuffix(i);
        String monthString = getMonthString(calendar2.get(2));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(z ? "//yyyy HH:mm:ss.SSS" : "//yyyy HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(str));
        return simpleDateFormat.format(new Date(j)).replaceFirst("//", i + dayOfMonthSuffix + StringUtils.SPACE + monthString + StringUtils.SPACE);
    }

    public static String millisecondsToStringWitNanos(double d) {
        double d2 = d / 1000.0d;
        return millisecondsToString((long) d) + "." + String.format("%06d", Integer.valueOf((int) ((d2 - ((int) d2)) * 1000000.0d)));
    }

    public static String millisecondsToString(double d) {
        return convertMilliSecondsToFormat((long) d, DATE_FORMAT_NO_MILLIS, TIME_ZONE_STR);
    }

    public static LocalDate toLocalDate(long j) {
        return toLocalDate(j, DATE_FORMAT_IN_DB, TIME_ZONE_STR);
    }

    public static LocalDate toLocalDate(long j, String str, String str2) {
        try {
            Date date = new Date(j);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(str2));
            return LocalDate.parse(simpleDateFormat.format(date), DateTimeFormatter.ofPattern(str));
        } catch (Exception e) {
            System.out.println("Exception in toLocalDate()");
            e.printStackTrace();
            return null;
        }
    }

    public static long getSystemTimeNowUTC() {
        return Instant.now().toEpochMilli();
    }

    public static LocalDate getLocalDateNowUTC() {
        return Instant.now().atZone(TIME_ZONE_ID).toLocalDate();
    }

    public static long fromTimeStringToMilliseconds(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        simpleDateFormat.setTimeZone(TIME_ZONE);
        try {
            return simpleDateFormat.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    private static String getDayOfMonthSuffix(int i) {
        if (i >= 11 && i <= 13) {
            return "th";
        }
        int i2 = i % 10;
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? "th" : "rd" : "nd" : "st";
    }

    public static String convertMilliSecondsToFormat(long j, String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(str2));
        return j == 0 ? "00:00:00" : simpleDateFormat.format(new Date(j));
    }

    public static String convertMilliSecondsToCsvHeaderFormat(long j) {
        return convertMilliSecondsToFormat(j, "yyyy/MM/dd HH:mm:ss.SSS", TIME_ZONE_STR);
    }

    public static String convertSecondsToHHmmssSSS(double d) {
        long jAbs = Math.abs((long) ((d * 1000.0d) % 1000.0d));
        return convertSecondsToHHmmss(d) + String.format(".%03d", Long.valueOf(jAbs));
    }

    public static String convertSecondsToHHmmss(double d) {
        String str = d >= 0.0d ? "" : "-";
        double dAbs = Math.abs(d);
        return str + String.format("%02d:%02d:%02d", Long.valueOf((long) (dAbs / 3600.0d)), Long.valueOf((long) ((dAbs / 60.0d) % 60.0d)), Long.valueOf((long) (dAbs % 60.0d)));
    }

    public static double[] parseStringArrayToDoubles(String[] strArr) {
        double[] dArr = new double[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (i == 0 && str.contains("T")) {
                try {
                    dArr[i] = sdfGgir.parse(str.replace("T", StringUtils.SPACE)).getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    dArr[i] = Double.parseDouble(str);
                } catch (NumberFormatException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return dArr;
    }

    public static int countLines(String str) throws IOException {
        return countLines(new BufferedInputStream(new FileInputStream(str)));
    }

    public static int countLines(InputStream inputStream) throws IOException {
        try {
            byte[] bArr = new byte[1024];
            int i = inputStream.read(bArr);
            if (i == -1) {
                return 0;
            }
            int i2 = 0;
            while (i == 1024) {
                int i3 = 0;
                while (i3 < 1024) {
                    int i4 = i3 + 1;
                    if (bArr[i3] == 10) {
                        i2++;
                    }
                    i3 = i4;
                }
                i = inputStream.read(bArr);
            }
            while (i != -1) {
                for (int i5 = 0; i5 < i; i5++) {
                    if (bArr[i5] == 10) {
                        i2++;
                    }
                }
                i = inputStream.read(bArr);
            }
            if (i2 == 0) {
                i2 = 1;
            }
            return i2;
        } finally {
            inputStream.close();
        }
    }

    public static boolean skipHeader(BufferedReader bufferedReader, boolean z) throws IOException {
        int i = 17;
        int i2 = 0;
        while (i2 < i) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return false;
                }
                int i3 = i2 + 1;
                if (isLineCsvHeaderSeparator(line)) {
                    if (!z) {
                        return true;
                    }
                    i = i2 + 3;
                }
                i2 = i3;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static File[] filterFileArrayForAnyFileNamePrefixAndSuffixAndSort(File[] fileArr, String str, String str2, boolean z) {
        return filterFileArrayForAnyFileNamePrefixAndAnySuffixAndSort(fileArr, str, new String[]{str2}, z);
    }

    public static File[] filterFileArrayForAnyFileNamePrefixAndAnySuffixAndSort(File[] fileArr, String str, String[] strArr, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (File file : fileArr) {
            String name = file.getName();
            if ((str == null || name.startsWith(str)) && (strArr == null || doesStringEndWithAny(name, strArr))) {
                arrayList.add(file);
            }
        }
        File[] fileArr2 = (File[]) arrayList.toArray(new File[arrayList.size()]);
        if (z) {
            Arrays.sort(fileArr2);
        } else {
            Arrays.sort(fileArr2, Collections.reverseOrder());
        }
        return fileArr2;
    }

    public static File[] filterFileArrayForAllAndRemoveDuplicates(File[] fileArr, String str, String str2) {
        return filterFileArrayForAllAndRemoveDuplicates(fileArr, (str == null || str.isEmpty()) ? null : new String[][]{new String[]{str}}, (String[][]) null, str2);
    }

    public static File[] filterFileArrayForAllAndRemoveDuplicates(File[] fileArr, String[][] strArr, String[][] strArr2, String str) {
        return filterFileArrayForAllAndRemoveDuplicates(fileArr, strArr, strArr2, str == null ? null : new String[]{str});
    }

    public static File[] filterFileArrayForAllAndRemoveDuplicates(File[] fileArr, String[][] strArr, String[][] strArr2, String[] strArr3) {
        return filterFileArrayForAllAndRemoveDuplicates(fileArr, strArr, strArr2, strArr3, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.io.File[] filterFileArrayForAllAndRemoveDuplicates(java.io.File[] r8, java.lang.String[][] r9, java.lang.String[][] r10, java.lang.String[] r11, java.lang.String[] r12) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r1 = r8.length
            r2 = 0
            r3 = 0
        L8:
            if (r3 >= r1) goto L69
            r4 = r8[r3]
            java.lang.String r5 = r4.getName()
            if (r9 == 0) goto L19
            boolean r6 = doesStringContainAllOfAnySet(r5, r9)
            if (r6 != 0) goto L19
            goto L66
        L19:
            if (r10 == 0) goto L26
            java.lang.String r6 = r4.getPath()
            boolean r6 = doesStringContainAllOfAnySet(r6, r10)
            if (r6 != 0) goto L26
            goto L66
        L26:
            java.lang.String r6 = r4.getName()
            java.lang.String r7 = ")."
            boolean r6 = r6.contains(r7)
            if (r6 == 0) goto L33
            goto L66
        L33:
            if (r11 == 0) goto L4b
            r6 = 0
        L36:
            int r7 = r11.length
            if (r6 >= r7) goto L4b
            r7 = r11[r6]
            boolean r7 = r5.endsWith(r7)
            if (r7 == 0) goto L42
            goto L4b
        L42:
            int r7 = r11.length
            int r7 = r7 + (-1)
            if (r6 != r7) goto L48
            goto L66
        L48:
            int r6 = r6 + 1
            goto L36
        L4b:
            if (r12 == 0) goto L63
            r6 = 0
        L4e:
            int r7 = r12.length
            if (r6 >= r7) goto L63
            r7 = r12[r6]
            boolean r7 = r5.startsWith(r7)
            if (r7 == 0) goto L5a
            goto L63
        L5a:
            int r7 = r12.length
            int r7 = r7 + (-1)
            if (r6 != r7) goto L60
            goto L66
        L60:
            int r6 = r6 + 1
            goto L4e
        L63:
            r0.add(r4)
        L66:
            int r3 = r3 + 1
            goto L8
        L69:
            int r8 = r0.size()
            java.io.File[] r8 = new java.io.File[r8]
            java.lang.Object[] r8 = r0.toArray(r8)
            java.io.File[] r8 = (java.io.File[]) r8
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.verisense.UtilVerisenseDriver.filterFileArrayForAllAndRemoveDuplicates(java.io.File[], java.lang.String[][], java.lang.String[][], java.lang.String[], java.lang.String[]):java.io.File[]");
    }

    public static File[] filterOutOfFileArrayDuplicatesAnd69And70Files(File[] fileArr) {
        return filterFilenamePrefixesOutOfFileArray(filterOutOfFileArray(fileArr, new String[]{FILE_FILTER_DUPLICATE}, null), new String[]{FILE_FILTER_69, FILE_FILTER_70});
    }

    public static File[] filterOutOfFileArray(File[] fileArr, String[] strArr, String[] strArr2) {
        ArrayList arrayList = new ArrayList();
        for (File file : fileArr) {
            String name = file.getName();
            if ((strArr == null || !doesStringContainAny(name, strArr)) && (strArr2 == null || !doesStringContainAny(file.getPath(), strArr2))) {
                arrayList.add(file);
            }
        }
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }

    public static File[] filterFilenamePrefixesOutOfFileArray(File[] fileArr, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (File file : fileArr) {
            String name = file.getName();
            if (strArr == null || !doesStringStartWithAny(name, strArr)) {
                arrayList.add(file);
            }
        }
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }

    public static boolean doesStringContainAllOfAnySet(String str, String[][] strArr) {
        for (String[] strArr2 : strArr) {
            if (doesStringContainAll(str, strArr2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean doesStringContainAll(String str, String[] strArr) {
        for (String str2 : strArr) {
            if (!str.contains(str2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean doesStringStartWithAny(String str, String[] strArr) {
        for (String str2 : strArr) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean doesStringEndWithAny(String str, String[] strArr) {
        for (String str2 : strArr) {
            if (str.endsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean doesStringContainAny(String str, String[] strArr) {
        for (String str2 : strArr) {
            if (str.contains(str2)) {
                return true;
            }
        }
        return false;
    }

    public static File[] filterCalibrationFilesInDir(File file, final String str, final String str2, final String str3) {
        File[] fileArrListFiles = file.listFiles(new FilenameFilter() { // from class: com.shimmerresearch.verisense.UtilVerisenseDriver.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str4) {
                if (str4.endsWith(str3)) {
                    if (str4.startsWith(str + "_" + str2)) {
                        return true;
                    }
                }
                return false;
            }
        });
        Arrays.sort(fileArrListFiles, Collections.reverseOrder());
        return fileArrListFiles;
    }

    public static File[] filterFileDuplicates(File[] fileArr) {
        ArrayList arrayList = new ArrayList();
        for (File file : fileArr) {
            if (!file.getName().contains(FILE_FILTER_DUPLICATE)) {
                arrayList.add(file);
            }
        }
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }

    public static boolean isAnyNullOrEmpty(String[] strArr) {
        for (String str : strArr) {
            if (isNullOrEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static int lsbByteArrayToInt(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i += (bArr[i2] & 255) << (i2 * 8);
        }
        return i;
    }

    public static boolean isOsWindows() {
        return System.getProperty("os.name").startsWith("Windows");
    }

    public static long getTimeFromDateZoneAdjusted(Date date) {
        return date.getTime() - (date.getTimezoneOffset() * 60000);
    }

    public static long getUnixTimeMsFromVerisenseFileName(File file) {
        return fromTimeStringToMilliseconds(file.getName().substring(0, 13), DATE_FORMAT_FILENAME);
    }

    public static LocalDate getYearMonthDateFromFileName(File file) {
        return LocalDate.parse(file.getName().substring(0, 6), DateTimeFormatter.ofPattern("yyMMdd"));
    }

    public static void printFileNamesFromArray(File[] fileArr) {
        System.out.println("File names:");
        for (File file : fileArr) {
            System.out.println("\t" + file.getName());
        }
    }

    public static String leftPad(String str, int i, String str2) {
        return String.format("%1$" + i + Configuration.CHANNEL_UNITS.SECONDS, str).replace(StringUtils.SPACE, str2);
    }

    public static double calcSamplingRate(double d, double d2, long j) {
        if (Double.isNaN(d) || Double.isNaN(d2) || j == 0 || j == Long.MIN_VALUE) {
            return Double.NaN;
        }
        return calcSamplingRate((d2 - d) / 1000.0d, j);
    }

    public static double calcSamplingRate(double d, long j) {
        if (Double.isNaN(d) || j == 0) {
            return Double.NaN;
        }
        return j / d;
    }

    public static double calculateAverage(List<Double> list) {
        double dDoubleValue = 0.0d;
        for (int i = 0; i < list.size(); i++) {
            dDoubleValue += list.get(i).doubleValue();
        }
        return dDoubleValue / list.size();
    }

    public static boolean isLineCsvHeaderSeparator(String str) {
        return str.equals(CSV_HEADER_SEPARATOR) || str.equals(CSV_HEADER_SEPARATOR_LEGACY);
    }

    public static boolean isHeaderWithCsvHeaderSeparator(String str) {
        return str.contains(CSV_HEADER_SEPARATOR) || str.contains(CSV_HEADER_SEPARATOR_LEGACY);
    }

    public static String formatDoubleToNdecimalPlaces(double d, int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("0");
        }
        return new DecimalFormat("#0." + sb.toString()).format(d);
    }

    public static String pickoutParameterFromLine(String str, String str2) {
        int iIndexOf = str.indexOf(str2);
        if (iIndexOf < 0) {
            return "";
        }
        int iIndexOf2 = str.indexOf(";", iIndexOf + 1);
        if (iIndexOf2 <= 0) {
            iIndexOf2 = str.length();
        }
        return str.substring(iIndexOf + str2.length(), iIndexOf2);
    }

    public static String getDateTimeStrInFileName(File file) {
        return file.getName().substring(0, 13);
    }

    public static boolean isDashboardEmulatorParticipant(String str) {
        return str.matches("P[0-9]{3}");
    }

    public static boolean isDashboardEmulatorSensor(String str) {
        return str.matches("S[0-9]{3}");
    }

    public long millisToMinutesOfHour(long j) {
        return millisToPartOfDay(j, 12);
    }

    public Calendar toCalendar(long j) {
        calendar.setTime(new Date(j));
        return calendar;
    }

    public double getMedian(double[] dArr) {
        Arrays.sort(dArr);
        return (dArr[(dArr.length / 2) - 1] + dArr[dArr.length / 2]) / 2.0d;
    }

    public double getAverage(double[] dArr) {
        double d = 0.0d;
        for (double d2 : dArr) {
            d += d2;
        }
        return d / dArr.length;
    }

    public static class FileComparator implements Comparator<File> {
        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            return extractDateAndTimeInFileName(file.getName()).compareTo(extractDateAndTimeInFileName(file2.getName()));
        }

        private String extractDateAndTimeInFileName(String str) {
            return str.length() > 13 ? str.substring(0, 13) : str;
        }
    }

    public class FILE_EXTENSION {
        public static final String BIN = ".bin";
        public static final String CSV = ".csv";
        public static final String JSON = ".json";
        public static final String VLOG = ".vlog";
        public static final String ZIP = ".zip";

        public FILE_EXTENSION() {
        }
    }

    public class ASM_DIRECTORY_NAMES {
        public static final String ALGORITHMS = "Algorithms";
        public static final String BINARY_FILES = "BinaryFiles";
        public static final String CALIBRATION_PARAMETERS = "CalibrationParameters";
        public static final String PARSED_FILES = "ParsedFiles";
        public static final String SUMMARY = "Summary";

        public ASM_DIRECTORY_NAMES() {
        }
    }
}
