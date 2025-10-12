package com.shimmerresearch.driverUtilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.shimmerresearch.driver.Configuration.CHANNEL_UNITS;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_DATA_ENDIAN;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_DATA_TYPE;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;

public class ChannelDetails implements Serializable {

    private static final long serialVersionUID = -2662151922286820989L;
    public String mGuiName = "";
    public String mObjectClusterName = "";
    public int mChannelId = -1;

    public int mDefaultNumBytes = 0;
    public int mLegacyChannelId = -1;
    public CHANNEL_DATA_TYPE mDefaultChannelDataType = CHANNEL_DATA_TYPE.UNKOWN;
    public CHANNEL_DATA_ENDIAN mDefaultChannelDataEndian = CHANNEL_DATA_ENDIAN.UNKOWN;
    public String mDefaultUncalUnit = CHANNEL_UNITS.NO_UNITS;
    public String mDefaultCalUnits = CHANNEL_UNITS.NO_UNITS;
        public CHANNEL_TYPE mChannelFormatDerivedFromShimmerDataPacket = CHANNEL_TYPE.UNCAL;
    public List<CHANNEL_TYPE> mListOfChannelTypes = new ArrayList<CHANNEL_TYPE>();
    public boolean mShowWhileStreaming = true;
    public boolean mStoreToDatabase = true;
    public CHANNEL_SOURCE mChannelSource = CHANNEL_SOURCE.SHIMMER;
    private String mDatabaseChannelHandle = "";

        public ChannelDetails() {
    }

    public ChannelDetails(String objectClusterName, String guiName, String defaultCalibratedUnits, List<CHANNEL_TYPE> listOfChannelTypes) {
        mObjectClusterName = objectClusterName;
        mGuiName = guiName;
        mDefaultCalUnits = defaultCalibratedUnits;
        mListOfChannelTypes = listOfChannelTypes;
        setDatabaseChannelHandleFromChannelLabel(objectClusterName);
    }


    public ChannelDetails(String objectClusterName,
                          String guiName,
                          String defaultCalibratedUnits,
                          List<CHANNEL_TYPE> listOfChannelTypes,
                          String databaseChannelHandle) {
        this(objectClusterName, guiName, defaultCalibratedUnits, listOfChannelTypes);
        setDatabaseChannelHandle(databaseChannelHandle);
    }

    public ChannelDetails(String objectClusterName,
                          String guiName,
                          String databaseChannelHandle,
                          CHANNEL_DATA_TYPE defaultChannelDataType,
                          int defaultNumBytes,
                          CHANNEL_DATA_ENDIAN channelDataEndian,
                          String defaultCalibratedUnits,
                          List<CHANNEL_TYPE> listOfChannelTypes,
                          int legacyChannelId) {
        this(objectClusterName, guiName, databaseChannelHandle, defaultChannelDataType, defaultNumBytes, channelDataEndian, defaultCalibratedUnits, listOfChannelTypes);
        mLegacyChannelId = legacyChannelId;
    }

    public ChannelDetails(String objectClusterName,
                          String guiName,
                          String defaultCalibratedUnits,
                          List<CHANNEL_TYPE> listOfChannelTypes,
                          boolean showWhileStreaming,
                          boolean storeToDatabase) {
        this(objectClusterName, guiName, defaultCalibratedUnits, listOfChannelTypes);

        mShowWhileStreaming = showWhileStreaming;
        mStoreToDatabase = storeToDatabase;
    }

    public ChannelDetails(String objectClusterName,
                          String guiName,
                          String databaseChannelHandle,
                          String defaultCalibratedUnits,
                          List<CHANNEL_TYPE> listOfChannelTypes) {
        this(objectClusterName, guiName, defaultCalibratedUnits, listOfChannelTypes, databaseChannelHandle);
    }

    public ChannelDetails(String objectClusterName,
                          String guiName,
                          String databaseChannelHandle,
                          String defaultCalibratedUnits,
                          List<CHANNEL_TYPE> listOfChannelTypes,
                          boolean showWhileStreaming,
                          boolean storeToDatabase) {
        this(objectClusterName, guiName, defaultCalibratedUnits, listOfChannelTypes, databaseChannelHandle);

        mShowWhileStreaming = showWhileStreaming;
        mStoreToDatabase = storeToDatabase;
    }

        public ChannelDetails(String objectClusterName,
                          String guiName,
                          String databaseChannelHandle,
                          CHANNEL_DATA_TYPE defaultChannelDataType,
                          int defaultNumBytes,
                          CHANNEL_DATA_ENDIAN channelDataEndian,
                          String defaultCalibratedUnits,
                          List<CHANNEL_TYPE> listOfChannelTypes) {
        this(objectClusterName, guiName, defaultCalibratedUnits, listOfChannelTypes, databaseChannelHandle);

        mDefaultChannelDataType = defaultChannelDataType;
        mDefaultNumBytes = defaultNumBytes;
        mDefaultChannelDataEndian = channelDataEndian;
    }

    public ChannelDetails(String objectClusterName,
                          String guiName,
                          String databaseChannelHandle,
                          CHANNEL_DATA_TYPE defaultChannelDataType,
                          int defaultNumBytes,
                          CHANNEL_DATA_ENDIAN channelDataEndian,
                          String defaultCalibratedUnits,
                          List<CHANNEL_TYPE> listOfChannelTypes,
                          boolean showWhileStreaming,
                          boolean storeToDatabase) {

        this(objectClusterName,
                guiName,
                databaseChannelHandle,
                defaultChannelDataType,
                defaultNumBytes,
                channelDataEndian,
                defaultCalibratedUnits,
                listOfChannelTypes);

        mShowWhileStreaming = showWhileStreaming;
        mStoreToDatabase = storeToDatabase;
    }

        public ChannelDetails(String objectClusterName,
                          String guiName,
                          String databaseChannelHandle,
                          int channelId,
                          CHANNEL_DATA_TYPE defaultChannelDataType,
                          int defaultNumBytes,
                          CHANNEL_DATA_ENDIAN channelDataEndian,
                          String defaultCalibratedUnits,
                          List<CHANNEL_TYPE> listOfChannelTypes) {

        this(objectClusterName,
                guiName,
                databaseChannelHandle,
                defaultChannelDataType,
                defaultNumBytes,
                channelDataEndian,
                defaultCalibratedUnits,
                listOfChannelTypes);

        mChannelId = channelId;
    }

    public int getLegacyChannelId() {
        return mLegacyChannelId;
    }

    public String getChannelObjectClusterName() {
        return mObjectClusterName;
    }

    public String getDatabaseChannelHandle() {
        if (!mDatabaseChannelHandle.isEmpty()) {
            return mDatabaseChannelHandle;
        } else {
            return getChannelObjectClusterName();
        }
    }

    public void setDatabaseChannelHandle(String databaseChannelHandle) {
        mDatabaseChannelHandle = databaseChannelHandle;
    }


    private void setDatabaseChannelHandleFromChannelLabel(String objectClusterName) {
        mDatabaseChannelHandle = objectClusterName;
        mDatabaseChannelHandle = mDatabaseChannelHandle.replace(" ", "_");
        mDatabaseChannelHandle = mDatabaseChannelHandle.replace("-", "_");
    }

    public boolean isShowWhileStreaming() {
        return mShowWhileStreaming;
    }

    public List<String[]> getListOfChannelSignalsAndFormats() {
        List<String[]> listOfChannelSignalsAndFormats = new ArrayList<String[]>();
        for (CHANNEL_TYPE channelType : mListOfChannelTypes) {
            String[] signalProperties = getChannelSignalsAndFormats(channelType);
            listOfChannelSignalsAndFormats.add(signalProperties);
        }
        return listOfChannelSignalsAndFormats;
    }

    public String[] getChannelSignalsAndFormats(CHANNEL_TYPE channelType) {
        String[] signalProperties = new String[4];
        signalProperties[0] = mObjectClusterName;
        signalProperties[1] = channelType.toString();

        if (channelType == CHANNEL_TYPE.UNCAL) {
            signalProperties[2] = mDefaultUncalUnit;
        } else if (channelType == CHANNEL_TYPE.CAL) {
            signalProperties[2] = mDefaultCalUnits;
        }

        signalProperties[3] = "";
        return signalProperties;
    }

    public String getChannelNameJoined(CHANNEL_TYPE channelType) {
        return UtilShimmer.joinStrings(getChannelSignalsAndFormats(channelType));
    }

    public boolean isStoreToDatabase() {
        return mStoreToDatabase;
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

        private final int numBits;
        private final int numBytes;
        private final boolean isSigned;

                private CHANNEL_DATA_TYPE(int numBits, int numBytes, boolean isSigned) {
            this.numBits = numBits;
            this.numBytes = numBytes;
            this.isSigned = isSigned;
        }

        public int getNumBytes() {
            return numBytes;
        }

        public int getNumBits() {
            return numBits;
        }

        public boolean isSigned() {
            return isSigned;
        }

        public long getMaxVal() {
            if (isSigned) {
                long mask = 0;
                for (int i = 0; i < numBits - 1; i++) {
                    mask |= (0x01 << i);
                }
                return UtilParseData.calculatetwoscomplement(mask, numBits);
            } else {
                return (long) (Math.pow(2, numBits) - 1);
            }
        }

        public long getMinVal() {
            if (isSigned) {
                return UtilParseData.calculatetwoscomplement((0x01 << (numBits - 1)), numBits);
            } else {
                return 0;
            }
        }
    }

    public enum CHANNEL_DATA_ENDIAN {
        UNKOWN,
        LSB,
        MSB
    }

    public enum CHANNEL_TYPE {
        CAL("CAL", "Calibrated"),
        UNCAL("UNCAL", "Uncalibrated"),
        DERIVED("DERIVED", "Derived");

        private final String shortText;
        private final String longText;

                private CHANNEL_TYPE(final String text, final String longText) {
            this.shortText = text;
            this.longText = longText;
        }

                @Override
        public String toString() {
            return shortText;
        }

        public String getLongText() {
            return longText;
        }
    }

    public enum CHANNEL_SOURCE {
        SHIMMER,
        API
    }

    public enum CHANNEL_AXES {
        TIME,
        FREQUENCY,
        VALUE
    }

}
