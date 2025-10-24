package com.shimmerresearch.android.guiUtilities;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.shimmerresearch.android.Shimmer;
import com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid;
import com.shimmerresearch.androidinstrumentdriver.R;
import com.shimmerresearch.bluetoothmanager.guiUtilities.AbstractSensorConfigDialog;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.AssembleShimmerConfig;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager;

import java.util.ArrayList;
import java.util.List;

public class DeviceSensorConfigFragment extends Fragment {

    DeviceConfigListAdapter expandListAdapter;
    ExpandableListView expandListView;
    int buttonBackgroundResourceId = -1;
    SensorConfigDialog scd;

    public DeviceSensorConfigFragment() {
    }

    public static DeviceSensorConfigFragment newInstance() {
        DeviceSensorConfigFragment fragment = new DeviceSensorConfigFragment();
        return fragment;
    }

    public void buildDeviceConfigList(final ShimmerDevice shimmerDevice, final Context context,
                                      final ShimmerBluetoothManagerAndroid bluetoothManager,
                                      final int buttonResourceId) {
        buttonBackgroundResourceId = buttonResourceId;
        buildDeviceConfigList(shimmerDevice, context, bluetoothManager);
    }

    public void buildDeviceConfigList(final ShimmerDevice shimmerDevice, final Context context,
                                      final ShimmerBluetoothManagerAndroid bluetoothManager) {
        scd = new SensorConfigDialog(shimmerDevice, bluetoothManager, context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_device_config, null);
    }

    public ShimmerDevice getCloneDevice() {
        return scd.getCloneDevice();
    }

    public class SensorConfigDialog extends AbstractSensorConfigDialog {

        Context context;

        SensorConfigDialog(ShimmerDevice shimmerDevice, ShimmerBluetoothManager bluetoothManager, Context context) {
            super(shimmerDevice, bluetoothManager);
            this.context = context;

            List<String> filterList = new ArrayList<String>();
            filterList.add("Wide Range Accel Rate");
            setSensorKeysFilter(filterList, true);

            List<String> displayButDisableFilterList = new ArrayList<String>();
            displayButDisableFilterList.add("Wide Range Accel Rate");
            displayButDisableFilterList.add("Mag Rate");
            displayButDisableFilterList.add("Gyro Sampling Rate");
            setSensorDisplayButDisableKeysFilter(displayButDisableFilterList, true);
            initialize();
            showFrame();
        }

        public ShimmerDevice getCloneDevice() {
            return cloneDevice;
        }

        @Override
        public void createComboBox(int numOfOptions, String key, ConfigOptionDetailsSensor cods, Object[] checkBox, boolean isEnabled) {

        }

        @Override
        public void createEditText(String key, boolean isEnabled) {

        }

        @Override
        public void createLabel(String label) {

        }

        @Override
        public void createFrame() {

        }

        @Override
        public void showFrame() {
            expandListAdapter = new DeviceConfigListAdapter(context, listOfKeys, configOptionsMap, shimmerDevice, cloneDevice);
            expandListView = (ExpandableListView) getView().findViewById(R.id.expandable_listview);
            expandListView.setAdapter(expandListAdapter);

            expandListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    final int editTextGroupPosition = groupPosition;
                    if (v.findViewById(R.id.expandedListItem) != null) {
                        CheckedTextView checkedTextView = (CheckedTextView) v.findViewById(R.id.expandedListItem);
                        if (checkedTextView.isChecked()) {
                            checkedTextView.setChecked(false);
                        } else {
                            checkedTextView.setChecked(true);
                        }

                        String newSetting = (String) expandListAdapter.getChild(groupPosition, childPosition);
                        String keySetting = (String) expandListAdapter.getGroup(groupPosition);

                        final ConfigOptionDetailsSensor cods = configOptionsMap.get(keySetting);

                        cloneDevice.setConfigValueUsingConfigLabel(keySetting, cods.mConfigValues[childPosition]);

                        expandListAdapter.replaceCurrentSetting(keySetting, newSetting);
                        expandListAdapter.notifyDataSetChanged();

                    }
                    return false;
                }
            });

            if (expandListView.getFooterViewsCount() == 0) {
                LinearLayout buttonLayout = new LinearLayout(context);
                buttonLayout.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
                buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
                buttonLayout.setGravity(Gravity.CENTER_HORIZONTAL);
                Button writeConfigButton = new Button(context);
                Button resetListButton = new Button(context);
                LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
                writeConfigButton.setLayoutParams(buttonParams);
                resetListButton.setLayoutParams(buttonParams);
                if (buttonBackgroundResourceId != -1) {
                    writeConfigButton.setBackgroundResource(buttonBackgroundResourceId);
                    resetListButton.setBackgroundResource(buttonBackgroundResourceId);
                }
                writeConfigButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Writing config to Shimmer...", Toast.LENGTH_SHORT).show();
                        List<ShimmerDevice> cloneList = new ArrayList<ShimmerDevice>();
                        cloneList.add(0, cloneDevice);
                        AssembleShimmerConfig.generateMultipleShimmerConfig(cloneList, Configuration.COMMUNICATION_TYPE.BLUETOOTH);

                        if (cloneDevice instanceof Shimmer) {
                            bluetoothManager.configureShimmer(cloneDevice);
                        }

                    }
                });
                resetListButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cloneDevice = shimmerDevice.deepClone();
                        expandListAdapter.updateCloneDevice(cloneDevice);
                        expandListAdapter.notifyDataSetChanged();
                        Toast.makeText(context, "Settings have been reset", Toast.LENGTH_SHORT).show();
                    }
                });
                writeConfigButton.setText("Save Changes");
                resetListButton.setText("Reset List");
                buttonLayout.addView(resetListButton);
                buttonLayout.addView(writeConfigButton);
                expandListView.addFooterView(buttonLayout);
            }
        }
    }

}
