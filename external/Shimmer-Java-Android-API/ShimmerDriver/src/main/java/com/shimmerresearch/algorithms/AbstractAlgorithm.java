package com.shimmerresearch.algorithms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import com.shimmerresearch.driver.BasicProcessWithCallBack;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.MsgDock;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;

public abstract class AbstractAlgorithm extends BasicProcessWithCallBack implements Serializable {

    public static final double mVersion = 1.0;
        private static final long serialVersionUID = 1L;
        public String mAlgorithmName;

    ;
    @Deprecated
    public String mAlgorithmGroupingName;
    public AlgorithmDetails mAlgorithmDetails;
        public List<ShimmerVerObject> mListOfCompatibleSVO = new ArrayList<ShimmerVerObject>();
    public List<String> mOutputChannels;

    public FILTERING_OPTION mFilteringOptions = FILTERING_OPTION.NONE;
    public ALGORITHM_TYPE mAlgorithmType = ALGORITHM_TYPE.ALGORITHM_TYPE_CONTINUOUS;
    public ALGORITHM_RESULT_TYPE mAlgorithmResultType = ALGORITHM_RESULT_TYPE.ALGORITHM_RESULT_TYPE_SINGLE_OBJECT_CLUSTER;

    public ALGORITHM_INPUT_TYPE mAlgorithmInputType = ALGORITHM_INPUT_TYPE.ALGORITHM_INPUT_TYPE_SINGLE_OBJECT_CLUSTER;
        public HashMap<String, ConfigOptionDetails> mConfigOptionsMap = new HashMap<String, ConfigOptionDetails>();
    public HashMap<String, AlgorithmDetails> mAlgorithmChannelsMap = new HashMap<String, AlgorithmDetails>();
    public TreeMap<Integer, SensorGroupingDetails> mMapOfAlgorithmGrouping = new TreeMap<Integer, SensorGroupingDetails>();
        public List<COMMUNICATION_TYPE> mListOfCommunicationTypesSupported = Arrays.asList(COMMUNICATION_TYPE.values());
    protected String mTrialName;
    @Deprecated
        protected String[] mSignalName = new String[1];
    @Deprecated
    protected String[] mSignalFormat = new String[1];
    protected String mTimeStampName = "";
    protected String mTimeStampFormat = "";
    protected boolean mInitialized = false;
    protected boolean mIsEnabled = false;
    protected double mMinSamplingRateForAlgorithhm = 0.0;
    protected double mShimmerSamplingRate = 128.0;
    protected ShimmerDevice mShimmerDevice = null;
    protected UtilShimmer mUtilShimmer = new UtilShimmer(this.getClass().getSimpleName(), true);
        @Deprecated
    protected String[] mSignalOutputNameArray;
        @Deprecated
    protected String[] mSignalOutputFormatArray;
        @Deprecated
    protected String[] mSignalOutputUnitArray;

    public AbstractAlgorithm() {
        setGeneralAlgorithmName();
    }

    public AbstractAlgorithm(AlgorithmDetails algorithmDetails) {
        setAlgorithmDetails(algorithmDetails);
    }

    public AbstractAlgorithm(ShimmerDevice shimmerDevice, AlgorithmDetails algorithmDetails) {
        setAlgorithmDetails(algorithmDetails);
        mShimmerDevice = shimmerDevice;
    }

    public abstract void setGeneralAlgorithmName();

    public abstract void setFilteringOption();

    public abstract void setMinSamplingRateForAlgorithm();

        public abstract void setSupportedVerInfo();

        public abstract void generateConfigOptionsMap();

    public abstract void generateAlgorithmGroupingMap();

    public abstract void initialize() throws Exception;

        public abstract void resetAlgorithm() throws Exception;

        public abstract void resetAlgorithmBuffers();

    public abstract Object getSettings(String componentName);

    public abstract void setSettings(String componentName, Object valueToSet);

    public abstract Object getDefaultSettings(String componentName);

        public abstract AlgorithmResultObject processDataRealTime(ObjectCluster ojc) throws Exception;

        public abstract AlgorithmResultObject processDataPostCapture(Object object) throws Exception;

        public abstract String printBatchMetrics();

        public abstract void eventDataReceived(ShimmerMsg shimmerMSG);

    public abstract LinkedHashMap<String, Object> generateConfigMap();

    public abstract void parseConfigMapFromDb(LinkedHashMap<String, Object> mapOfConfigPerShimmer);

    private void setAlgorithmDetails(AlgorithmDetails algorithmDetails) {
        mAlgorithmDetails = algorithmDetails;
        mAlgorithmName = algorithmDetails.mAlgorithmName;
        mAlgorithmGroupingName = algorithmDetails.mAlgorithmName;
        mAlgorithmChannelsMap.put(mAlgorithmName, mAlgorithmDetails);
    }

    public void setupAlgorithm() {
        setFilteringOption();
        setMinSamplingRateForAlgorithm();
        setSupportedVerInfo();

        generateConfigOptionsMap();
        generateAlgorithmGroupingMap();
    }


    public String getAlgorithmName() {
        return mAlgorithmName;
    }

    @Deprecated
    public void setSignalName(String signalName, int channelNum) {
        if (channelNum >= 0 && channelNum < mSignalName.length)
            mSignalName[channelNum] = signalName;
        else
            throw new ArrayIndexOutOfBoundsException("Invalid attempt to set element of mSignalName array.");
    }

    @Deprecated
    public String getSignalName() {
        return mSignalName[0];
    }


    @Deprecated
    public void setSignalName(String signalName) {
        mSignalName[0] = signalName;
    }

    @Deprecated
    public String getSignalName(int channelNum) {
        if (channelNum >= 0 && channelNum < mSignalName.length)
            return mSignalName[channelNum];
        else
            throw new ArrayIndexOutOfBoundsException("Invalid attempt to get element of mSignalName array.");
    }

    @Deprecated
    public void setSignalFormat(String signalFormat, int channelNum) {
        if (channelNum >= 0 && channelNum < mSignalName.length)
            mSignalFormat[channelNum] = signalFormat;
        else
            throw new ArrayIndexOutOfBoundsException("Invalid attempt to set element of mSignalFormat array.");
    }

    @Deprecated
    public String getSignalFormat() {
        return mSignalFormat[0];
    }

    @Deprecated
    public void setSignalFormat(String signalFormat) {
        mSignalFormat[0] = signalFormat;
    }

    @Deprecated
    public String getSignalFormat(int channelNum) {
        if (channelNum >= 0 && channelNum < mSignalName.length)
            return mSignalFormat[channelNum];
        else
            throw new ArrayIndexOutOfBoundsException("Invalid attempt to get element of mSignalFormat array.");
    }

    public String getTimeStampName() {
        return mTimeStampName;
    }

    public void setTimeStampName(String TimeStampName) {
        mTimeStampName = TimeStampName;
    }

    public String getTimeStampFormat() {
        return mTimeStampFormat;
    }

    public void setTimeStampFormat(String TimeStampFormat) {
        mTimeStampFormat = TimeStampFormat;
    }

    public HashMap<String, ConfigOptionDetails> getConfigOptionsMap() {
        return mConfigOptionsMap;
    }

    public boolean isInitialized() {
        return mInitialized;
    }

    public void setIsInitialized(boolean state) {
        mInitialized = state;
    }

    public boolean isEnabled() {
        return mIsEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {

        mIsEnabled = isEnabled;
        if (!isEnabled) {
            setIsInitialized(false);
        }
    }

        public void algorithmMapUpdateFromEnabledSensorsVars(long derivedSensorBitmapID) {
        if (mAlgorithmDetails != null) {
            boolean state = (mAlgorithmDetails.mDerivedSensorBitmapID & derivedSensorBitmapID) > 0 ? true : false;
            setIsEnabled(state);
        }
    }

        public long getDerivedSensorBitmapID() {
        if (mAlgorithmDetails != null && isEnabled()) {
            return mAlgorithmDetails.mDerivedSensorBitmapID;
        }
        return 0;
    }

        @Deprecated
    public String[] getSignalOutputNameArray() {
        return mSignalOutputNameArray;
    }

        @Deprecated
    public String[] getSignalOutputFormatArray() {
        return mSignalOutputFormatArray;
    }

        @Deprecated
    public String[] getSignalOutputUnitArray() {
        return mSignalOutputUnitArray;
    }

        public List<ChannelDetails> getChannelDetails() {
        return getChannelDetails(false);
    }

        public List<ChannelDetails> getChannelDetails(boolean showDisabledChannels) {
        if (mAlgorithmDetails != null) {
            return mAlgorithmDetails.getChannelDetails();
        }
        return null;
    }


        public Integer getNumberOfEnabledChannels() {
        List<ChannelDetails> listOfChannels = getChannelDetails(false);
        if (listOfChannels != null) {
            return listOfChannels.size();
        }
        return 0;
    }


        public List<String> getOutputChannelList() {
        return mOutputChannels;
    }

    public ALGORITHM_TYPE getAlgorithmType() {
        return mAlgorithmType;
    }

    public ALGORITHM_INPUT_TYPE getAlgorithmInputType() {
        return mAlgorithmInputType;
    }

    public ALGORITHM_RESULT_TYPE getAlgorithmResultType() {
        return mAlgorithmResultType;
    }

    public void setFiltering(FILTERING_OPTION option) {
        mFilteringOptions = option;
    }

    public String[] getComboBoxOptions(String key) {
        return mConfigOptionsMap.get(key).mGuiValues;
    }

        public void sendProcessingResultMsg(AlgorithmResultObject aro) {
        sendCallBackMsg(MsgDock.MSG_ID_SOURCE_ALGORITHM, aro);
    }

    @Override
    protected void processMsgFromCallback(ShimmerMsg shimmerMSG) {
        if (shimmerMSG.mIdentifier == MsgDock.MSG_ID_DATA_TO_ALGO) {
            eventDataReceived(shimmerMSG);
        }
    }

    public String getTrialName() {
        return mTrialName;
    }

    public void setTrialName(String name) {
        mTrialName = name;
    }

    public double getMinSamplingRateForAlgorithm() {
        return mMinSamplingRateForAlgorithhm;
    }

    public AlgorithmResultObject processDataRealTime(List<ObjectCluster> objectClusterArray) {
        for (ObjectCluster ojc : objectClusterArray) {
            try {
                return processDataRealTime(ojc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public HashMap<String, Object> getAlgorithmSettings() {
        HashMap<String, Object> mapOfAlgorithmSettings = new HashMap<String, Object>();
        for (String configOptionKey : mConfigOptionsMap.keySet()) {
            Object configValue = getSettings(configOptionKey);
            if (configValue != null) {
                consolePrintLn("getEnabledAlgorithmSettings\t" + "\tConfigKey=" + configOptionKey + "\tConfigValue=" + configValue);
                mapOfAlgorithmSettings.put(configOptionKey, configValue);
            }
        }
        return mapOfAlgorithmSettings;
    }

    public void setAlgorithmSettings(HashMap<String, Object> mapOfAlgoSettings) {
        for (String configOptionKey : mapOfAlgoSettings.keySet()) {
            setSettings(configOptionKey, mapOfAlgoSettings.get(configOptionKey));
        }
    }

    public void setDefaultSetting() {

    }

    public void setGeneralAlgorithmName(String generalAlgorithmName) {
        mAlgorithmName = generalAlgorithmName;
    }

    public double getShimmerSamplingRate() {
        return mShimmerSamplingRate;
    }

    public void setShimmerSamplingRate(double samplingRate) {
        mShimmerSamplingRate = samplingRate;
        if (isEnabled()) {
            try {
                initialize();
            } catch (Exception e) {
                System.err.println("sampling rate=" + samplingRate);
                e.printStackTrace();
            }
        }
    }

    public void addConfigOption(ConfigOptionDetails configOptionDetails) {
        mConfigOptionsMap.put(configOptionDetails.mGuiHandle, configOptionDetails);
    }

    protected void setListOfCommunicationTypesSupported(COMMUNICATION_TYPE commType) {
        mListOfCommunicationTypesSupported = Arrays.asList(commType);
    }

    protected void setListOfCommunicationTypesSupported(List<COMMUNICATION_TYPE> listOfCommTypesSupported) {
        mListOfCommunicationTypesSupported = listOfCommTypesSupported;
    }

    protected void consolePrintLn(String message) {
        if (mShimmerDevice != null) {
            mShimmerDevice.consolePrintLn(message);
        } else {
            mUtilShimmer.consolePrintLn(message);
        }
    }

    protected void consolePrintErrLn(String message) {
        if (mShimmerDevice != null) {
            mShimmerDevice.consolePrintErrLn(message);
        } else {
            mUtilShimmer.consolePrintErrLn(message);
        }
    }

    protected void consolePrintException(String message, StackTraceElement[] stackTrace) {
        if (mShimmerDevice != null) {
            mShimmerDevice.consolePrintExeptionLn(message, stackTrace);
        } else {
            mUtilShimmer.consolePrintExeptionLn(message, stackTrace);
        }
    }

        public void loadAlgorithmVariables(AbstractAlgorithm abstractAlgorithmSource) {

    }

    public enum FILTERING_OPTION {
        NONE,
        DEFAULT
    }

    public enum ALGORITHM_TYPE {
        ALGORITHM_TYPE_CONTINUOUS("ALGORITHM TYPE CONTINUOUS"),
        ALGORITHM_TYPE_ONE_SHOT("ALGORITHM TYPE ONE SHOT"),
        ALGORITHM_TYPE_EVENT_DRIVEN("ALGORITHM TYPE EVENT DRIVEN");

        private final String text;

                private ALGORITHM_TYPE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public enum ALGORITHM_RESULT_TYPE {
        ALGORITHM_RESULT_TYPE_SINGLE_OBJECT_CLUSTER("ALGORITHM RESULT TYPE SINGLE"),
        ALGORITHM_RESULT_TYPE_ARRAY_OBJECT_CLUSTER("ALGORITHM RESULT TYPE ARRAY");

        private final String text;

                private ALGORITHM_RESULT_TYPE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public enum ALGORITHM_INPUT_TYPE {
        ALGORITHM_INPUT_TYPE_SINGLE_OBJECT_CLUSTER("ALGORITHM RESULT TYPE SINGLE"),
        ALGORITHM_INPUT_TYPE_ARRAY_OBJECT_CLUSTER("ALGORITHM RESULT TYPE ARRAY");

        private final String text;

                private ALGORITHM_INPUT_TYPE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public class GuiLabelConfigCommon {
        public final static String SHIMMER_SAMPLING_RATE = Configuration.Shimmer3.GuiLabelConfig.SHIMMER_SAMPLING_RATE;
        public final static String MIN_ALGO_SAMPLING_RATE = "Algo Min " + SHIMMER_SAMPLING_RATE;
        public final static String TIMESTAMP_SIGNAL_NAME = "Time Stamp Signal Name";
        public final static String TIMESTAMP_SIGNAL_FORMAT = "Time Stamp Signal Format";
    }

}
