package com.shimmerresearch.android.guiUtilities;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.shimmerresearch.android.Shimmer;
import com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid;
import com.shimmerresearch.androidinstrumentdriver.R;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.AssembleShimmerConfig;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorDetails;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class DeviceConfigFragment extends Fragment {
    int buttonBackgroundResourceId = -1;
    DeviceConfigListAdapter expandListAdapter;
    ExpandableListView expandListView;
    ShimmerDevice shimmerDeviceClone;

    public static DeviceConfigFragment newInstance() {
        return new DeviceConfigFragment();
    }

    public void buildDeviceConfigList(ShimmerDevice shimmerDevice, Context context, ShimmerBluetoothManagerAndroid shimmerBluetoothManagerAndroid, int i) {
        this.buttonBackgroundResourceId = i;
        buildDeviceConfigList(shimmerDevice, context, shimmerBluetoothManagerAndroid);
    }

    public void buildDeviceConfigList(final ShimmerDevice shimmerDevice, final Context context, final ShimmerBluetoothManagerAndroid shimmerBluetoothManagerAndroid) {
        final Map<String, ConfigOptionDetailsSensor> configOptionsMap = shimmerDevice.getConfigOptionsMap();
        this.shimmerDeviceClone = shimmerDevice.deepClone();
        LinkedHashMap<Integer, SensorDetails> sensorMap = shimmerDevice.getSensorMap();
        ArrayList<String> arrayList = new ArrayList();
        for (SensorDetails sensorDetails : sensorMap.values()) {
            if (sensorDetails.mSensorDetailsRef.mListOfConfigOptionKeysAssociated != null && sensorDetails.isEnabled()) {
                arrayList.addAll(sensorDetails.mSensorDetailsRef.mListOfConfigOptionKeysAssociated);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (String str : arrayList) {
            if (configOptionsMap.get(str) == null) {
                arrayList2.add(str);
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
        this.expandListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() { // from class: com.shimmerresearch.android.guiUtilities.DeviceConfigFragment.1
            @Override // android.widget.ExpandableListView.OnChildClickListener
            public boolean onChildClick(ExpandableListView expandableListView2, View view, int i, int i2, long j) {
                if (view.findViewById(R.id.expandedListItem) != null) {
                    CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(R.id.expandedListItem);
                    if (checkedTextView.isChecked()) {
                        checkedTextView.setChecked(false);
                    } else {
                        checkedTextView.setChecked(true);
                    }
                    String str2 = (String) DeviceConfigFragment.this.expandListAdapter.getChild(i, i2);
                    String str3 = (String) DeviceConfigFragment.this.expandListAdapter.getGroup(i);
                    DeviceConfigFragment.this.shimmerDeviceClone.setConfigValueUsingConfigLabel(str3, ((ConfigOptionDetailsSensor) configOptionsMap.get(str3)).mConfigValues[i2]);
                    DeviceConfigFragment.this.expandListAdapter.replaceCurrentSetting(str3, str2);
                    DeviceConfigFragment.this.expandListAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        if (this.expandListView.getFooterViewsCount() == 0) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
            linearLayout.setOrientation(0);
            linearLayout.setGravity(1);
            Button button = new Button(context);
            Button button2 = new Button(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2, 1.0f);
            button.setLayoutParams(layoutParams);
            button2.setLayoutParams(layoutParams);
            int i = this.buttonBackgroundResourceId;
            if (i != -1) {
                button.setBackgroundResource(i);
                button2.setBackgroundResource(this.buttonBackgroundResourceId);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.shimmerresearch.android.guiUtilities.DeviceConfigFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Toast.makeText(context, "Writing config to Shimmer...", 0).show();
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(0, DeviceConfigFragment.this.shimmerDeviceClone);
                    AssembleShimmerConfig.generateMultipleShimmerConfig(arrayList3, Configuration.COMMUNICATION_TYPE.BLUETOOTH);
                    if (DeviceConfigFragment.this.shimmerDeviceClone instanceof Shimmer) {
                        shimmerBluetoothManagerAndroid.configureShimmer(DeviceConfigFragment.this.shimmerDeviceClone);
                    }
                }
            });
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.shimmerresearch.android.guiUtilities.DeviceConfigFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DeviceConfigFragment.this.shimmerDeviceClone = shimmerDevice.deepClone();
                    DeviceConfigFragment.this.expandListAdapter.updateCloneDevice(DeviceConfigFragment.this.shimmerDeviceClone);
                    DeviceConfigFragment.this.expandListAdapter.notifyDataSetChanged();
                    Toast.makeText(context, "Settings have been reset", 0).show();
                }
            });
            button.setText("Save Changes");
            button2.setText("Reset List");
            linearLayout.addView(button2);
            linearLayout.addView(button);
            this.expandListView.addFooterView(linearLayout);
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_device_config, (ViewGroup) null);
    }
}
