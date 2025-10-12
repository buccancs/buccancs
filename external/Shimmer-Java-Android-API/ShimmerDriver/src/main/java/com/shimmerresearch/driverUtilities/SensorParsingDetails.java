package com.shimmerresearch.driverUtilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SensorParsingDetails implements Serializable {

    private static final long serialVersionUID = 8384885695261304687L;

    public int head_byte_mask = -9999;
    public int sampling_divider = -9999;
    public int sampling_period = -9999;
    public double sampling_rate = -9999.0;
    public int data_rate = -9999;
    public int range = -9999;
    public int numChannels = -9999;

    public List<ChannelDetails> mListOfChannels = new ArrayList<ChannelDetails>();

    public double[][] offsetVector = new double[3][1];
    public double[][] sensitivityVector = new double[3][3];
    public double[][] alignmentVector = new double[3][3];

}