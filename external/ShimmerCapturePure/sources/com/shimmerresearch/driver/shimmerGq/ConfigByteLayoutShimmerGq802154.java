package com.shimmerresearch.driver.shimmerGq;

import com.shimmerresearch.driver.ConfigByteLayout;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.verisense.sensors.SensorMAX86XXX;

import java.io.Serializable;
import java.util.HashMap;

import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.math.Primes;

/* loaded from: classes2.dex */
public class ConfigByteLayoutShimmerGq802154 extends ConfigByteLayout implements Serializable {
    private static final long serialVersionUID = -5729543049033754281L;
    public int idxDerivedSensors3;
    public int idxDerivedSensors4;
    public int idxDerivedSensors5;
    public int idxDerivedSensors6;
    public int idxDerivedSensors7;
    public int idxShimmerSamplingRate = 0;
    public int idxSensors0 = 3;
    public int idxSensors1 = 4;
    public int idxSensors2 = 5;
    public int idxConfigSetupByte0 = 6;
    public int idxConfigSetupByte1 = 7;
    public int idxConfigSetupByte2 = 8;
    public int idxConfigSetupByte3 = 9;
    public int idxEXGADS1292RChip1Config1 = 10;
    public int idxEXGADS1292RChip1Config2 = 11;
    public int idxEXGADS1292RChip1LOff = 12;
    public int idxEXGADS1292RChip1Ch1Set = 13;
    public int idxEXGADS1292RChip1Ch2Set = 14;
    public int idxEXGADS1292RChip1RldSens = 15;
    public int idxEXGADS1292RChip1LOffSens = 16;
    public int idxEXGADS1292RChip1LOffStat = 17;
    public int idxEXGADS1292RChip1Resp1 = 18;
    public int idxEXGADS1292RChip1Resp2 = 19;
    public int idxDerivedSensors0 = 31;
    public int idxDerivedSensors1 = 32;
    public int idxDerivedSensors2 = 33;
    public int idxSDShimmerName = CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256;
    public int idxSDEXPIDName = 199;
    public int idxSDConfigTime0 = Primes.SMALL_FACTOR_LIMIT;
    public int idxSDConfigTime1 = 212;
    public int idxSDConfigTime2 = SensorMAX86XXX.MAX_FIFOS_IN_PAYLOAD_3_CHANNELS;
    public int idxSDConfigTime3 = 214;
    public int idxSDExperimentConfig0 = 217;
    public int idxMacAddress = 224;
    public int maskShimmerSamplingRate = 255;
    public int maskBufferSize = 255;
    public int maskSensors = 255;
    public int byteShiftSensors0 = 0;
    public int byteShiftSensors1 = 8;
    public int byteShiftSensors2 = 16;
    public int bitShiftGSRRange = 1;
    public int maskGSRRange = 7;
    public int bitShiftEXPPowerEnable = 0;
    public int maskEXPPowerEnable = 1;
    public int maskDerivedChannelsByte = 255;
    public int byteShiftDerivedSensors0 = 0;
    public int byteShiftDerivedSensors1 = 8;
    public int byteShiftDerivedSensors2 = 16;
    public int byteShiftDerivedSensors3 = 24;
    public int byteShiftDerivedSensors4 = 32;
    public int byteShiftDerivedSensors5 = 40;
    public int byteShiftDerivedSensors6 = 48;
    public int byteShiftDerivedSensors7 = 56;
    public int lengthShimmerName = 12;
    public int lengthExperimentName = 12;
    public int lengthConfigTimeBytes = 4;
    public int bitShiftTimeSyncWhenLogging = 2;
    public int maskTimeSyncWhenLogging = 1;
    public int lengthMacIdBytes = 6;
    public int bitShiftSDConfigTime0 = 24;
    public int bitShiftSDConfigTime1 = 16;
    public int bitShiftSDConfigTime2 = 8;
    public int bitShiftSDConfigTime3 = 0;
    public int maskExg1_24bitFlag = 16;
    public int maskExg2_24bitFlag = 8;
    public int maskExg1_16bitFlag = 1048576;
    public int maskExg2_16bitFlag = 524288;
    public int idxSrRadioChannel = 256;
    public int idxSrRadioGroupId = 257;
    public int idxSrRadioMyAddress = 259;
    public int idxSrRadioResponseWindow = 261;
    public int idxSrRadioConfigStart = 256;
    public int lengthRadioConfig = 7;

    public ConfigByteLayoutShimmerGq802154(int i, int i2, int i3, int i4) {
        this.idxDerivedSensors3 = 0;
        this.idxDerivedSensors4 = 0;
        this.idxDerivedSensors5 = 0;
        this.idxDerivedSensors6 = 0;
        this.idxDerivedSensors7 = 0;
        this.mShimmerVerObject = new ShimmerVerObject(i, i2, i3, i4);
        this.mInfoMemSize = calculateConfigByteLength();
        this.MSP430_5XX_INFOMEM_D_ADDRESS = 0;
        this.MSP430_5XX_INFOMEM_C_ADDRESS = 128;
        this.MSP430_5XX_INFOMEM_B_ADDRESS = 256;
        this.MSP430_5XX_INFOMEM_A_ADDRESS = 384;
        this.MSP430_5XX_INFOMEM_LAST_ADDRESS = 511;
        if (this.mShimmerVerObject.isSupportedEightByteDerivedSensors()) {
            this.idxDerivedSensors3 = 118;
            this.idxDerivedSensors4 = 119;
            this.idxDerivedSensors5 = 120;
            this.idxDerivedSensors6 = 121;
            this.idxDerivedSensors7 = 122;
        }
    }

    @Override // com.shimmerresearch.driver.ConfigByteLayout
    public int calculateConfigByteLength(ShimmerVerObject shimmerVerObject) {
        return 384;
    }

    @Override // com.shimmerresearch.driver.ConfigByteLayout
    public HashMap<Integer, String> getMapOfByteDescriptions() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(Integer.valueOf(this.idxShimmerSamplingRate), "SamplingRate_LSB");
        map.put(Integer.valueOf(this.idxShimmerSamplingRate + 1), "SamplingRate_MSB");
        map.put(Integer.valueOf(this.idxSensors0), "Sensors0");
        map.put(Integer.valueOf(this.idxSensors1), "Sensors1");
        map.put(Integer.valueOf(this.idxSensors2), "Sensors2");
        map.put(Integer.valueOf(this.idxConfigSetupByte0), "ConfigSetupByte0");
        map.put(Integer.valueOf(this.idxConfigSetupByte1), "ConfigSetupByte1");
        map.put(Integer.valueOf(this.idxConfigSetupByte2), "ConfigSetupByte2");
        map.put(Integer.valueOf(this.idxConfigSetupByte3), "ConfigSetupByte3");
        map.put(Integer.valueOf(this.idxEXGADS1292RChip1Config1), "EXGADS1292RChip1Config1");
        map.put(Integer.valueOf(this.idxEXGADS1292RChip1Config2), "EXGADS1292RChip1Config2");
        map.put(Integer.valueOf(this.idxEXGADS1292RChip1LOff), "EXGADS1292RChip1LOff");
        map.put(Integer.valueOf(this.idxEXGADS1292RChip1Ch1Set), "EXGADS1292RChip1Ch1Set");
        map.put(Integer.valueOf(this.idxEXGADS1292RChip1Ch2Set), "EXGADS1292RChip1Ch2Set");
        map.put(Integer.valueOf(this.idxEXGADS1292RChip1RldSens), "EXGADS1292RChip1RldSens");
        map.put(Integer.valueOf(this.idxEXGADS1292RChip1LOffSens), "EXGADS1292RChip1LOffSens");
        map.put(Integer.valueOf(this.idxEXGADS1292RChip1LOffStat), "EXGADS1292RChip1LOffStat");
        map.put(Integer.valueOf(this.idxEXGADS1292RChip1Resp1), "EXGADS1292RChip1Resp1");
        map.put(Integer.valueOf(this.idxEXGADS1292RChip1Resp2), "EXGADS1292RChip1Resp2");
        map.put(Integer.valueOf(this.idxDerivedSensors0), "DerivedSensors0");
        map.put(Integer.valueOf(this.idxDerivedSensors1), "DerivedSensors1");
        map.put(Integer.valueOf(this.idxDerivedSensors2), "DerivedSensors2");
        map.put(Integer.valueOf(this.idxDerivedSensors3), "DerivedSensors3");
        map.put(Integer.valueOf(this.idxDerivedSensors4), "DerivedSensors4");
        map.put(Integer.valueOf(this.idxDerivedSensors5), "DerivedSensors5");
        map.put(Integer.valueOf(this.idxDerivedSensors6), "DerivedSensors6");
        map.put(Integer.valueOf(this.idxDerivedSensors7), "DerivedSensors7");
        map.put(Integer.valueOf(this.idxSDShimmerName), "SDShimmerName");
        map.put(Integer.valueOf(this.idxSDEXPIDName), "SDEXPIDName");
        map.put(Integer.valueOf(this.idxSDConfigTime0), "SDConfigTime0");
        map.put(Integer.valueOf(this.idxSDConfigTime1), "SDConfigTime1");
        map.put(Integer.valueOf(this.idxSDConfigTime2), "SDConfigTime2");
        map.put(Integer.valueOf(this.idxSDConfigTime3), "SDConfigTime3");
        map.put(Integer.valueOf(this.idxSDExperimentConfig0), "SDExperimentConfig0");
        map.put(Integer.valueOf(this.idxMacAddress), "MacAddress");
        map.put(Integer.valueOf(this.idxSrRadioChannel), "RadioChannel");
        map.put(Integer.valueOf(this.idxSrRadioGroupId), "RadioGroupId_MSB");
        map.put(Integer.valueOf(this.idxSrRadioGroupId + 1), "RadioGroupId_LSB");
        map.put(Integer.valueOf(this.idxSrRadioMyAddress), "RadioMyAddress_MSB");
        map.put(Integer.valueOf(this.idxSrRadioMyAddress + 1), "RadioMyAddress_LSB");
        map.put(Integer.valueOf(this.idxSrRadioResponseWindow), "RadioResponseWindow_MSB");
        map.put(Integer.valueOf(this.idxSrRadioResponseWindow + 1), "RadioResponseWindow_LSB");
        return map;
    }
}
