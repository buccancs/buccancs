package com.shimmerresearch.driverUtilities;

import java.util.ArrayList;
import java.util.List;

import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.shimmer4sdk.Shimmer4sdk;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.ShimmerObject;

public class AssembleShimmerConfig {

    public static List<ShimmerDevice> listForConfiguringDocked = new ArrayList<ShimmerDevice>();
    public static List<ShimmerDevice> listForConfiguringBT = new ArrayList<ShimmerDevice>();

    public static void generateSingleShimmerConfig(ShimmerDevice shimmerDevice, COMMUNICATION_TYPE commType) {
        List<ShimmerDevice> cloneList = new ArrayList<ShimmerDevice>();
        cloneList.add(0, shimmerDevice);
        generateMultipleShimmerConfig(cloneList, commType);
    }

    public static void generateMultipleShimmerConfig(List<ShimmerDevice> listOfShimmersToConfigureClone, COMMUNICATION_TYPE commType) {
        generateMultipleShimmerConfig(listOfShimmersToConfigureClone, commType, true, true, true);
    }

    public static void generateMultipleShimmerConfig(List<ShimmerDevice> listOfShimmersToConfigureClone,
                                                     COMMUNICATION_TYPE commType,
                                                     boolean isBasic,
                                                     boolean overrideShowErrorLedsRtc,
                                                     boolean overrideShowErrorLedsSd) {

        listForConfiguringDocked.clear();
        listForConfiguringBT.clear();


        if (listOfShimmersToConfigureClone.size() > 0) {

            boolean isSyncEnabledOnAll = true;
            for (ShimmerDevice shimmerDevice : listOfShimmersToConfigureClone) {
                if (shimmerDevice instanceof ShimmerBluetooth) {
                    ShimmerBluetooth shimmerBluetooth = (ShimmerBluetooth) shimmerDevice;
                    if (!shimmerBluetooth.isSyncWhenLogging()) {
                        isSyncEnabledOnAll = false;
                        break;
                    }
                }
            }

            List<String> syncNodesList = new ArrayList<String>();
            if (isSyncEnabledOnAll) {
                if (listOfShimmersToConfigureClone.get(0) instanceof ShimmerBluetooth) {
                    ShimmerBluetooth shimmerBluetooth = ((ShimmerBluetooth) listOfShimmersToConfigureClone.get(0));
                    if (isBasic) {
                        shimmerBluetooth.setSyncWhenLogging(false);
                    }
                    if (shimmerBluetooth.isSyncWhenLogging()) {
                        for (ShimmerDevice shimmerDevice : listOfShimmersToConfigureClone) {
                            if (shimmerDevice.isSupportedSdLogSync()) {
                                if (shimmerDevice instanceof ShimmerBluetooth) {
                                    if (((ShimmerBluetooth) shimmerDevice).isMasterShimmer()) {
                                        syncNodesList.add(0, shimmerDevice.getMacId());
                                    } else {
                                        syncNodesList.add(shimmerDevice.getMacId());
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                for (ShimmerDevice shimmerDevice : listOfShimmersToConfigureClone) {
                    if (shimmerDevice instanceof ShimmerBluetooth) {
                        ShimmerBluetooth shimmerBluetooth = (ShimmerBluetooth) shimmerDevice;
                        shimmerBluetooth.setSyncWhenLogging(false);
                        shimmerBluetooth.setMasterShimmer(false);
                    }
                }
            }

            for (ShimmerDevice shimmerDevice : listOfShimmersToConfigureClone) {
                if (shimmerDevice instanceof ShimmerBluetooth) {
                    ShimmerBluetooth shimmerBluetooth = (ShimmerBluetooth) shimmerDevice;
                    if (shimmerBluetooth.isSyncWhenLogging() && !syncNodesList.isEmpty()) {
                        shimmerBluetooth.setSyncNodesList(syncNodesList);
                    } else {
                        shimmerBluetooth.setSyncNodesList(new ArrayList<String>());
                    }
                }
            }

            AssembleShimmerConfig.correctShimmerNameDuplicates(listOfShimmersToConfigureClone);

            long configTime = System.currentTimeMillis() / 1000;
            int index = 1; // first id is one
            for (ShimmerDevice shimmerDevice : listOfShimmersToConfigureClone) {
                shimmerDevice.setConfigTime(configTime); // Set Shimmer configuration time
                shimmerDevice.checkShimmerConfigBeforeConfiguring();
                if (isBasic) {
                    shimmerDevice.disableAllAlgorithms();
                }

                if (shimmerDevice instanceof ShimmerBluetooth) {
                    ShimmerBluetooth shimmerPcmss = (ShimmerBluetooth) shimmerDevice;
                    shimmerPcmss.setExperimentId(index); // set experiment id
                    shimmerPcmss.setExperimentNumberOfShimmers(listOfShimmersToConfigureClone.size()); // set num Shimmers in experiment


                    shimmerPcmss.setIsOverrideShowErrorLedsRtc(overrideShowErrorLedsRtc);
                    shimmerPcmss.setIsOverrideShowErrorLedsSd(overrideShowErrorLedsSd);

                    if (commType == COMMUNICATION_TYPE.DOCK) {
                        shimmerPcmss.setBluetoothBaudRate(9); // Set the MSP-to-RN42 baud rate to be 460800
                    }

                    shimmerPcmss.setConfigFileCreationFlag(true);
                    shimmerPcmss.generateConfigBytesForWritingToShimmer();
                    index++;
                } else {
                    shimmerDevice.configBytesGenerate(true);
                }
            }

            if (listOfShimmersToConfigureClone.size() > 0) {
                for (ShimmerDevice shimmerDevice : listOfShimmersToConfigureClone) {
                    if (commType == COMMUNICATION_TYPE.DOCK
                            && shimmerDevice.isDocked()
                            && !shimmerDevice.isStreaming()) {
                        listForConfiguringDocked.add(shimmerDevice.deepClone());
                    }
                    else if (commType == COMMUNICATION_TYPE.BLUETOOTH
                            && (shimmerDevice.isInitialised() && !shimmerDevice.isStreaming())) {
                        listForConfiguringBT.add(shimmerDevice.deepClone());
                    }
                }
            }

        }
    }

    public static void correctShimmerNameDuplicates(List<ShimmerDevice> listOfNewShimmerDevices) {

        List<String> listOfShimmerNames = new ArrayList<String>();
        for (ShimmerDevice dockedShimmer : listOfNewShimmerDevices) {
            String currentShimmerName = dockedShimmer.getShimmerUserAssignedName();
            if (currentShimmerName.isEmpty()) {
                dockedShimmer.setShimmerUserAssignedName(ShimmerObject.DEFAULT_SHIMMER_NAME);
                currentShimmerName = dockedShimmer.getShimmerUserAssignedName();

            }
            if (!listOfShimmerNames.contains(currentShimmerName)) {
                listOfShimmerNames.add(currentShimmerName);
            } else {
                if (currentShimmerName.length() > 7) { // 12 char max minus "_XXXX"
                    currentShimmerName = currentShimmerName.substring(0, 7);
                }
                dockedShimmer.setShimmerUserAssignedName(currentShimmerName + "_" + dockedShimmer.getMacIdFromUartParsed());
            }
        }
    }

}
