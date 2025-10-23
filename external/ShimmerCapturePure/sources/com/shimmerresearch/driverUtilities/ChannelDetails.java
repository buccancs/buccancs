package com.shimmerresearch.driverUtilities;

import com.shimmerresearch.driver.Configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class ChannelDetails implements Serializable {
    private static final long serialVersionUID = -2662151922286820989L;
    public CHANNEL_TYPE mChannelFormatDerivedFromShimmerDataPacket;
    public int mChannelId;
    public CHANNEL_SOURCE mChannelSource;
    public String mDefaultCalUnits;
    public CHANNEL_DATA_ENDIAN mDefaultChannelDataEndian;
    public CHANNEL_DATA_TYPE mDefaultChannelDataType;
    public int mDefaultNumBytes;
    public String mDefaultUncalUnit;
    public String mGuiName;
    public int mLegacyChannelId;
    public List<CHANNEL_TYPE> mListOfChannelTypes;
    public String mObjectClusterName;
    public boolean mShowWhileStreaming;
    public boolean mStoreToDatabase;
    private String mDatabaseChannelHandle;

    public ChannelDetails() {
        this.mGuiName = "";
        this.mObjectClusterName = "";
        this.mDatabaseChannelHandle = "";
        this.mChannelId = -1;
        this.mDefaultNumBytes = 0;
        this.mLegacyChannelId = -1;
        this.mDefaultChannelDataType = CHANNEL_DATA_TYPE.UNKOWN;
        this.mDefaultChannelDataEndian = CHANNEL_DATA_ENDIAN.UNKOWN;
        this.mDefaultUncalUnit = Configuration.CHANNEL_UNITS.NO_UNITS;
        this.mDefaultCalUnits = Configuration.CHANNEL_UNITS.NO_UNITS;
        this.mChannelFormatDerivedFromShimmerDataPacket = CHANNEL_TYPE.UNCAL;
        this.mListOfChannelTypes = new ArrayList();
        this.mShowWhileStreaming = true;
        this.mStoreToDatabase = true;
        this.mChannelSource = CHANNEL_SOURCE.SHIMMER;
    }

    public ChannelDetails(String str, String str2, String str3, List<CHANNEL_TYPE> list) {
        this.mGuiName = "";
        this.mObjectClusterName = "";
        this.mDatabaseChannelHandle = "";
        this.mChannelId = -1;
        this.mDefaultNumBytes = 0;
        this.mLegacyChannelId = -1;
        this.mDefaultChannelDataType = CHANNEL_DATA_TYPE.UNKOWN;
        this.mDefaultChannelDataEndian = CHANNEL_DATA_ENDIAN.UNKOWN;
        this.mDefaultUncalUnit = Configuration.CHANNEL_UNITS.NO_UNITS;
        this.mDefaultCalUnits = Configuration.CHANNEL_UNITS.NO_UNITS;
        this.mChannelFormatDerivedFromShimmerDataPacket = CHANNEL_TYPE.UNCAL;
        this.mListOfChannelTypes = new ArrayList();
        this.mShowWhileStreaming = true;
        this.mStoreToDatabase = true;
        this.mChannelSource = CHANNEL_SOURCE.SHIMMER;
        this.mObjectClusterName = str;
        this.mGuiName = str2;
        this.mDefaultCalUnits = str3;
        this.mListOfChannelTypes = list;
        setDatabaseChannelHandleFromChannelLabel(str);
    }

    public ChannelDetails(String str, String str2, String str3, List<CHANNEL_TYPE> list, String str4) {
        this(str, str2, str3, list);
        setDatabaseChannelHandle(str4);
    }

    public ChannelDetails(String str, String str2, String str3, CHANNEL_DATA_TYPE channel_data_type, int i, CHANNEL_DATA_ENDIAN channel_data_endian, String str4, List<CHANNEL_TYPE> list, int i2) {
        this(str, str2, str3, channel_data_type, i, channel_data_endian, str4, list);
        this.mLegacyChannelId = i2;
    }

    public ChannelDetails(String str, String str2, String str3, List<CHANNEL_TYPE> list, boolean z, boolean z2) {
        this(str, str2, str3, list);
        this.mShowWhileStreaming = z;
        this.mStoreToDatabase = z2;
    }

    public ChannelDetails(String str, String str2, String str3, String str4, List<CHANNEL_TYPE> list) {
        this(str, str2, str4, list, str3);
    }

    public ChannelDetails(String str, String str2, String str3, String str4, List<CHANNEL_TYPE> list, boolean z, boolean z2) {
        this(str, str2, str4, list, str3);
        this.mShowWhileStreaming = z;
        this.mStoreToDatabase = z2;
    }

    public ChannelDetails(String str, String str2, String str3, CHANNEL_DATA_TYPE channel_data_type, int i, CHANNEL_DATA_ENDIAN channel_data_endian, String str4, List<CHANNEL_TYPE> list) {
        this(str, str2, str4, list, str3);
        this.mDefaultChannelDataType = channel_data_type;
        this.mDefaultNumBytes = i;
        this.mDefaultChannelDataEndian = channel_data_endian;
    }

    public ChannelDetails(String str, String str2, String str3, CHANNEL_DATA_TYPE channel_data_type, int i, CHANNEL_DATA_ENDIAN channel_data_endian, String str4, List<CHANNEL_TYPE> list, boolean z, boolean z2) {
        this(str, str2, str3, channel_data_type, i, channel_data_endian, str4, list);
        this.mShowWhileStreaming = z;
        this.mStoreToDatabase = z2;
    }

    public ChannelDetails(String str, String str2, String str3, int i, CHANNEL_DATA_TYPE channel_data_type, int i2, CHANNEL_DATA_ENDIAN channel_data_endian, String str4, List<CHANNEL_TYPE> list) {
        this(str, str2, str3, channel_data_type, i2, channel_data_endian, str4, list);
        this.mChannelId = i;
    }

    public String getChannelObjectClusterName() {
        return this.mObjectClusterName;
    }

    public int getLegacyChannelId() {
        return this.mLegacyChannelId;
    }

    public boolean isShowWhileStreaming() {
        return this.mShowWhileStreaming;
    }

    public boolean isStoreToDatabase() {
        return this.mStoreToDatabase;
    }

    public String getDatabaseChannelHandle() {
        return !this.mDatabaseChannelHandle.isEmpty() ? this.mDatabaseChannelHandle : getChannelObjectClusterName();
    }

    public void setDatabaseChannelHandle(String str) {
        this.mDatabaseChannelHandle = str;
    }

    private void setDatabaseChannelHandleFromChannelLabel(String str) {
        this.mDatabaseChannelHandle = str;
        String strReplace = str.replace(StringUtils.SPACE, "_");
        this.mDatabaseChannelHandle = strReplace;
        this.mDatabaseChannelHandle = strReplace.replace("-", "_");
    }

    public List<String[]> getListOfChannelSignalsAndFormats() {
        ArrayList arrayList = new ArrayList();
        Iterator<CHANNEL_TYPE> it2 = this.mListOfChannelTypes.iterator();
        while (it2.hasNext()) {
            arrayList.add(getChannelSignalsAndFormats(it2.next()));
        }
        return arrayList;
    }

    public String[] getChannelSignalsAndFormats(CHANNEL_TYPE channel_type) {
        String[] strArr = new String[4];
        strArr[0] = this.mObjectClusterName;
        strArr[1] = channel_type.toString();
        if (channel_type == CHANNEL_TYPE.UNCAL) {
            strArr[2] = this.mDefaultUncalUnit;
        } else if (channel_type == CHANNEL_TYPE.CAL) {
            strArr[2] = this.mDefaultCalUnits;
        }
        strArr[3] = "";
        return strArr;
    }

    public String getChannelNameJoined(CHANNEL_TYPE channel_type) {
        return UtilShimmer.joinStrings(getChannelSignalsAndFormats(channel_type));
    }

    public enum CHANNEL_AXES {
        TIME,
        FREQUENCY,
        VALUE
    }

    public enum CHANNEL_DATA_ENDIAN {
        UNKOWN,
        LSB,
        MSB
    }

    public enum CHANNEL_SOURCE {
        SHIMMER,
        API
    }

    public enum CHANNEL_DATA_TYPE {
        UNKOWN(0, 0, false),
        UINT8(8, 1, false),
        UINT12(12, 2, false),
        UINT14(14, 2, false),
        UINT16(16, 2, false),
        UINT24(24, 3, false),
        UINT32(32, 4, false),
        UINT48(48, 6, false),
        UINT64(64, 8, false),
        INT8(8, 1, true),
        INT12(12, 2, true),
        INT14(14, 2, true),
        INT16(16, 2, true),
        INT24(24, 3, true),
        INT32(32, 4, true),
        INT64(64, 8, true);

        private final boolean isSigned;
        private final int numBits;
        private final int numBytes;

        CHANNEL_DATA_TYPE(int i, int i2, boolean z) {
            this.numBits = i;
            this.numBytes = i2;
            this.isSigned = z;
        }

        public int getNumBits() {
            return this.numBits;
        }

        public int getNumBytes() {
            return this.numBytes;
        }

        public boolean isSigned() {
            return this.isSigned;
        }

        public long getMaxVal() {
            if (!this.isSigned) {
                return (long) (Math.pow(2.0d, this.numBits) - 1.0d);
            }
            long j = 0;
            int i = 0;
            while (true) {
                int i2 = this.numBits;
                if (i >= i2 - 1) {
                    return UtilParseData.calculatetwoscomplement(j, i2);
                }
                j |= 1 << i;
                i++;
            }
        }

        public long getMinVal() {
            if (!this.isSigned) {
                return 0L;
            }
            int i = this.numBits;
            return UtilParseData.calculatetwoscomplement(1 << (i - 1), i);
        }
    }

    public enum CHANNEL_TYPE {
        CAL("CAL", "Calibrated"),
        UNCAL("UNCAL", "Uncalibrated"),
        DERIVED("DERIVED", "Derived");

        private final String longText;
        private final String shortText;

        CHANNEL_TYPE(String str, String str2) {
            this.shortText = str;
            this.longText = str2;
        }

        public String getLongText() {
            return this.longText;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.shortText;
        }
    }
}
