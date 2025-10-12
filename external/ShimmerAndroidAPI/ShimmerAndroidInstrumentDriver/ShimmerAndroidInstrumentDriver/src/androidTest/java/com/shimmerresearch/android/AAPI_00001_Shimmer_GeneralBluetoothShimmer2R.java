package com.shimmerresearch.android;

import android.os.Handler;
import android.support.test.runner.AndroidJUnit4;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AAPI_00001_Shimmer_GeneralBluetoothShimmer2R {
    private static final String mMacAddress = "00:06:66:43:B4:D6";
    private static Handler handler = null;
    final static Shimmer shimmer = new Shimmer(handler);
    final private int DELAY_DURATION_MS = 3000;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        shimmer.connect(mMacAddress, "default");
        Thread.sleep(10000);
    }

    @Test
    public void test001_Connect() throws Exception {

        if (shimmer.isConnected()) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
        System.out.println("GeneralBluetoothShimmer2RTest A : Connection");

    }

    @Test
    public void test002_GetHW() throws Exception {
        System.out.println("GeneralBluetoothShimmer2RTest B : Hardware version");
        if (shimmer.getHardwareVersion() == ShimmerVerDetails.HW_ID.SHIMMER_2R) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }

    }

    @Test
    public void test003_GetFW() throws Exception {

        if (shimmer.getFirmwareIdentifier() == ShimmerVerDetails.FW_ID.BTSTREAM) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
        System.out.println("GeneralBluetoothShimmer2RTest C : Firmware version");
    }

    @Test
    public void test004_WriteReadSamplingRate() throws Exception {

        double samplingrate;
        shimmer.readSamplingRate();
        Thread.sleep(DELAY_DURATION_MS);
        System.out.println("Current Shimmer 2R sampling rate is:" + shimmer.getSamplingRateShimmer());

        if (shimmer.getSamplingRateShimmer() == 128) {
            samplingrate = 51.2;
        } else {
            samplingrate = 128;
        }
        shimmer.writeShimmerAndSensorsSamplingRate(samplingrate);
        Thread.sleep(DELAY_DURATION_MS);
        shimmer.readSamplingRate();
        Thread.sleep(DELAY_DURATION_MS);


        if (shimmer.getSamplingRateShimmer() == samplingrate) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
        System.out.println("GeneralBluetoothShimmer2RTest D : Sampling Rate");
    }

    @Test
    public void test005_StartStreaming() throws Exception {

        shimmer.startStreaming();
        Thread.sleep(DELAY_DURATION_MS);
        if (shimmer.isStreaming()) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
        System.out.println("GeneralBluetoothShimmer2RTest E : Start Streaming");
    }

    @Test
    public void test006_StopStreaming() throws Exception {
        shimmer.stopStreaming();
        Thread.sleep(DELAY_DURATION_MS);
        if (shimmer.isStreaming()) {
            assertTrue(false);
        } else {
            assertTrue(true);
        }
        System.out.println("GeneralBluetoothShimmer2RTest F : Stop Streaming");
    }

    @Test
    public void test007_MultipleStartStopStreaming() throws Exception {
        for (int i = 1; i < 8; i++) {

            if (i % 2 != 0) {
                shimmer.startStreaming();
                Thread.sleep(DELAY_DURATION_MS);
                assertTrue(shimmer.isStreaming());
            } else {
                shimmer.stopStreaming();
                Thread.sleep(DELAY_DURATION_MS);
                assertFalse(shimmer.isStreaming());
            }
        }
        System.out.println("GeneralBluetoothShimmer2RTest G : Multiple start and stop streaming");
    }

    @Test
    public void test008_Disconnect() {
        shimmer.disconnect();
        if (shimmer.isConnected()) {
            assertTrue(false);
        } else {
            assertTrue(true);
        }
        System.out.println("GeneralBluetoothShimmer2RTest H : Disconnect");
    }

}