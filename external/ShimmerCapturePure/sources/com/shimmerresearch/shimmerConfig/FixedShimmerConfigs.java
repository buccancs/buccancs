package com.shimmerresearch.shimmerConfig;

import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.sensors.SensorEXG;

import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class FixedShimmerConfigs {

    public static void setFixedConfig2(ShimmerDevice shimmerDevice) {
    }

    public static boolean setFixedConfigWhenConnecting(ShimmerDevice shimmerDevice, FIXED_SHIMMER_CONFIG_MODE fixed_shimmer_config_mode, LinkedHashMap<String, Object> linkedHashMap) {
        if (fixed_shimmer_config_mode != FIXED_SHIMMER_CONFIG_MODE.NONE) {
            int expansionBoardId = shimmerDevice.getExpansionBoardId();
            shimmerDevice.setDefaultShimmerConfiguration();
            shimmerDevice.disableAllAlgorithms();
            shimmerDevice.disableAllSensors();
            if (fixed_shimmer_config_mode == FIXED_SHIMMER_CONFIG_MODE.USER && linkedHashMap != null) {
                for (Map.Entry<String, Object> entry : linkedHashMap.entrySet()) {
                    shimmerDevice.setConfigValueUsingConfigLabel(entry.getKey(), entry.getValue());
                }
                return true;
            }
            if (fixed_shimmer_config_mode == FIXED_SHIMMER_CONFIG_MODE.CADENCE) {
                setFixedConfig0(shimmerDevice);
                return true;
            }
            if (fixed_shimmer_config_mode == FIXED_SHIMMER_CONFIG_MODE.CIMIT) {
                if (expansionBoardId == 37 || expansionBoardId == 47 || expansionBoardId == 59) {
                    setFixedConfig1(shimmerDevice);
                    return true;
                }
            } else if (fixed_shimmer_config_mode == FIXED_SHIMMER_CONFIG_MODE.NEUHOME) {
                if (expansionBoardId != 37 && expansionBoardId != 47 && (expansionBoardId == 14 || expansionBoardId == 48)) {
                    setFixedConfig4(shimmerDevice);
                    return true;
                }
            } else if (fixed_shimmer_config_mode == FIXED_SHIMMER_CONFIG_MODE.NEUHOMEGSRONLY) {
                if (expansionBoardId != 37 && expansionBoardId != 47 && (expansionBoardId == 14 || expansionBoardId == 48)) {
                    setFixedConfig5(shimmerDevice);
                    return true;
                }
            } else if (fixed_shimmer_config_mode == FIXED_SHIMMER_CONFIG_MODE.CALIBRATION_IMU) {
                setFixedConfig2(shimmerDevice);
                return true;
            }
        }
        return false;
    }

    public static void setFixedConfig0(ShimmerDevice shimmerDevice) {
        if (shimmerDevice.getSensorIdsSet().contains(31)) {
            shimmerDevice.setSensorEnabledState(31, true);
            shimmerDevice.setConfigValueUsingConfigLabel((Integer) 31, "Wide Range Accel Range", (Object) 1);
        }
        shimmerDevice.setShimmerAndSensorsSamplingRate(51.2d);
    }

    public static void setFixedConfig4(ShimmerDevice shimmerDevice) {
        int expansionBoardId = shimmerDevice.getExpansionBoardId();
        if (expansionBoardId == 14 || expansionBoardId == 48) {
            shimmerDevice.setShimmerAndSensorsSamplingRate(256.0d);
            shimmerDevice.setSensorEnabledState(11, true);
            shimmerDevice.setSensorEnabledState(19, true);
        }
    }

    public static void setFixedConfig5(ShimmerDevice shimmerDevice) {
        int expansionBoardId = shimmerDevice.getExpansionBoardId();
        if (expansionBoardId == 14 || expansionBoardId == 48) {
            shimmerDevice.setShimmerAndSensorsSamplingRate(256.0d);
            shimmerDevice.setSensorEnabledState(19, true);
        }
    }

    public static void setFixedConfig1(ShimmerDevice shimmerDevice) {
        int expansionBoardId = shimmerDevice.getExpansionBoardId();
        if (expansionBoardId == 37 || expansionBoardId == 47 || expansionBoardId == 59) {
            if (shimmerDevice.getSensorIdsSet().contains(100)) {
                shimmerDevice.setSensorEnabledState(100, true);
                shimmerDevice.setConfigValueUsingConfigLabel((Integer) 100, SensorEXG.GuiLabelConfig.EXG_GAIN, (Object) 0);
                shimmerDevice.setConfigValueUsingConfigLabel((Integer) 100, SensorEXG.GuiLabelConfig.EXG_RESOLUTION, (Object) 1);
                shimmerDevice.setConfigValueUsingConfigLabel((Integer) 100, SensorEXG.GuiLabelConfig.EXG_REFERENCE_ELECTRODE, (Object) 13);
            }
            shimmerDevice.setShimmerAndSensorsSamplingRate(1024.0d);
        }
    }

    public enum FIXED_SHIMMER_CONFIG_MODE {
        NONE,
        CADENCE,
        CIMIT,
        CALIBRATION_IMU,
        USER,
        SWEATCH_ANDROID,
        NEUHOME,
        NEUHOMEGSRONLY
    }
}
