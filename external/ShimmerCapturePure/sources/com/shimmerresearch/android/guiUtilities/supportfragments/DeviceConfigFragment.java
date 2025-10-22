package com.shimmerresearch.android.guiUtilities.supportfragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid;
import com.shimmerresearch.androidinstrumentdriver.R;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.AssembleShimmerConfig;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.verisense.VerisenseDevice;
import com.shimmerresearch.verisense.sensors.SensorLIS2DW12;
import com.shimmerresearch.verisense.sensors.SensorLSM6DS3;
import com.shimmerresearch.verisense.sensors.SensorMAX86XXX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class DeviceConfigFragment extends Fragment {
    DeviceConfigListAdapter expandListAdapter;
    ExpandableListView expandListView;
    ShimmerDevice shimmerDeviceClone;

    public static DeviceConfigFragment newInstance() {
        return new DeviceConfigFragment();
    }

    public void buildDeviceConfigList(final ShimmerDevice shimmerDevice, final Context context, final ShimmerBluetoothManagerAndroid shimmerBluetoothManagerAndroid) {
        final Map<String, ConfigOptionDetailsSensor> configOptionsMap = shimmerDevice.getConfigOptionsMap();
        this.shimmerDeviceClone = shimmerDevice.deepClone();
        LinkedHashMap<Integer, SensorDetails> sensorMap = shimmerDevice.getSensorMap();
        final ArrayList<String> arrayList = new ArrayList();
        for (SensorDetails sensorDetails : sensorMap.values()) {
            if (sensorDetails.mSensorDetailsRef.mListOfConfigOptionKeysAssociated != null && sensorDetails.isEnabled()) {
                for (String str : sensorDetails.mSensorDetailsRef.mListOfConfigOptionKeysAssociated) {
                    if (!arrayList.contains(str)) {
                        arrayList.add(str);
                    }
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (String str2 : arrayList) {
            if (configOptionsMap.get(str2) == null) {
                arrayList2.add(str2);
            }
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList.remove((String) it2.next());
        }
        this.expandListAdapter = new DeviceConfigListAdapter(context, arrayList, configOptionsMap, shimmerDevice, this.shimmerDeviceClone);
        ExpandableListView expandableListView = (ExpandableListView) getView().findViewById(R.id.expandable_listview);
        this.expandListView = expandableListView;
        expandableListView.setAdapter(this.expandListAdapter);
        this.expandListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() { // from class: com.shimmerresearch.android.guiUtilities.supportfragments.DeviceConfigFragment.1
            @Override // android.widget.ExpandableListView.OnChildClickListener
            public boolean onChildClick(ExpandableListView expandableListView2, View view, int i, int i2, long j) {
                if (view.findViewById(R.id.expandedListItem) != null) {
                    CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(R.id.expandedListItem);
                    if (checkedTextView.isEnabled()) {
                        if (checkedTextView.isChecked()) {
                            checkedTextView.setChecked(false);
                        } else {
                            checkedTextView.setChecked(true);
                        }
                        String str3 = (String) DeviceConfigFragment.this.expandListAdapter.getChild(i, i2);
                        String str4 = (String) DeviceConfigFragment.this.expandListAdapter.getGroup(i);
                        DeviceConfigFragment.this.shimmerDeviceClone.setConfigValueUsingConfigLabel(str4, ((ConfigOptionDetailsSensor) configOptionsMap.get(str4)).mConfigValues[i2]);
                        DeviceConfigFragment.this.expandListAdapter.replaceCurrentSetting(str4, str3);
                        DeviceConfigFragment.this.expandListAdapter.notifyDataSetChanged();
                    }
                }
                return false;
            }
        });
        if (this.expandListView.getFooterViewsCount() == 0) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            linearLayout.setOrientation(1);
            Button button = new Button(context);
            Button button2 = new Button(context);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.shimmerresearch.android.guiUtilities.supportfragments.DeviceConfigFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Toast.makeText(context, "Writing config to Shimmer...", 0).show();
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(0, DeviceConfigFragment.this.shimmerDeviceClone);
                    AssembleShimmerConfig.generateMultipleShimmerConfig(arrayList3, Configuration.COMMUNICATION_TYPE.BLUETOOTH);
                    shimmerBluetoothManagerAndroid.configureShimmer(DeviceConfigFragment.this.shimmerDeviceClone);
                }
            });
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.shimmerresearch.android.guiUtilities.supportfragments.DeviceConfigFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DeviceConfigFragment.this.shimmerDeviceClone = shimmerDevice.deepClone();
                    DeviceConfigFragment.this.expandListAdapter.updateCloneDevice(DeviceConfigFragment.this.shimmerDeviceClone);
                    DeviceConfigFragment.this.expandListAdapter.notifyDataSetChanged();
                    Toast.makeText(context, "Settings have been reset", 0).show();
                }
            });
            button.setText("Write config to Shimmer");
            button2.setText("Reset settings");
            if (shimmerDevice instanceof VerisenseDevice) {
                Button button3 = new Button(context);
                button3.setText("Set default config");
                button3.setOnClickListener(new View.OnClickListener() { // from class: com.shimmerresearch.android.guiUtilities.supportfragments.DeviceConfigFragment.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ConfigOptionDetailsSensor configOptionDetailsSensor;
                        HashMap map = new HashMap();
                        map.put(SensorLSM6DS3.GuiLabelConfig.LSM6DS3_ACCEL_RANGE, 3);
                        map.put(SensorLSM6DS3.GuiLabelConfig.LSM6DS3_GYRO_RANGE, 3);
                        map.put(SensorLIS2DW12.GuiLabelConfig.LIS2DW12_RATE, 2);
                        map.put(SensorLIS2DW12.GuiLabelConfig.LIS2DW12_MODE, 1);
                        map.put(SensorLSM6DS3.GuiLabelConfig.LSM6DS3_RATE, 6);
                        map.put(SensorLIS2DW12.GuiLabelConfig.LIS2DW12_LP_MODE, 1);
                        map.put("Range", 3);
                        map.put(SensorMAX86XXX.GuiLabelConfigCommonMax86.MAX86XXX_PPG_RATE, 3);
                        DeviceConfigFragment.this.shimmerDeviceClone = shimmerDevice.deepClone();
                        for (String str3 : arrayList) {
                            if (map.containsKey(str3) && (configOptionDetailsSensor = (ConfigOptionDetailsSensor) configOptionsMap.get(str3)) != null) {
                                DeviceConfigFragment.this.shimmerDeviceClone.setConfigValueUsingConfigLabel(str3, configOptionDetailsSensor.mConfigValues[((Integer) map.get(str3)).intValue()]);
                            }
                        }
                        DeviceConfigFragment.this.expandListAdapter.updateCloneDevice(DeviceConfigFragment.this.shimmerDeviceClone);
                        DeviceConfigFragment.this.expandListAdapter.notifyDataSetChanged();
                        Toast.makeText(context, "Settings have been set to default", 0).show();
                    }
                });
                linearLayout.addView(button3);
            }
            linearLayout.addView(button2);
            linearLayout.addView(button);
            this.expandListView.addFooterView(linearLayout);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_device_config, (ViewGroup) null);
    }
}
