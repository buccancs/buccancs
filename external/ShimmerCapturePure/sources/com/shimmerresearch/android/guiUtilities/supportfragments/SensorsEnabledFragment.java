package com.shimmerresearch.android.guiUtilities.supportfragments;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.ListFragment;
import com.shimmerresearch.android.Shimmer;
import com.shimmerresearch.android.Shimmer4Android;
import com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid;
import com.shimmerresearch.android.shimmerService.ShimmerService;
import com.shimmerresearch.androidradiodriver.Shimmer3BLEAndroid;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.AssembleShimmerConfig;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.verisense.VerisenseDevice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public class SensorsEnabledFragment extends ListFragment {
    static final String LOG_TAG = "SHIMMER";
    TreeMap<Integer, SensorGroupingDetails> compatibleSensorGroupMap;
    OnSensorsSelectedListener mCallback;
    ShimmerDevice originalShimmerDevice;
    int[] sensorKeys;
    ShimmerDevice shimmerDeviceClone;
    ShimmerService shimmerService = null;

    public static SensorsEnabledFragment newInstance(String str, String str2) {
        SensorsEnabledFragment sensorsEnabledFragment = new SensorsEnabledFragment();
        sensorsEnabledFragment.setArguments(new Bundle());
        return sensorsEnabledFragment;
    }

    public void setShimmerService(ShimmerService shimmerService) {
        this.shimmerService = shimmerService;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mCallback = (OnSensorsSelectedListener) activity;
        } catch (ClassCastException unused) {
            throw new ClassCastException(activity.toString() + " must implement OnSensorSelectedListener");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setListAdapter(new ArrayAdapter(getActivity(), R.layout.simple_list_item_1, new String[]{"No device selected, sensors unavailable"}));
    }

    public void buildSensorsList(final ShimmerDevice shimmerDevice, final Context context, final ShimmerBluetoothManagerAndroid shimmerBluetoothManagerAndroid) {
        this.originalShimmerDevice = shimmerDevice;
        this.shimmerDeviceClone = shimmerDevice.deepClone();
        this.compatibleSensorGroupMap = new TreeMap<>();
        TreeMap<Integer, SensorGroupingDetails> sensorGroupingMap = this.shimmerDeviceClone.getSensorGroupingMap();
        for (Map.Entry<Integer, SensorGroupingDetails> entry : sensorGroupingMap.entrySet()) {
            if (isSensorGroupCompatible(this.shimmerDeviceClone, entry.getValue())) {
                this.compatibleSensorGroupMap.put(entry.getKey(), entry.getValue());
            }
        }
        for (SensorGroupingDetails sensorGroupingDetails : sensorGroupingMap.values()) {
            List<ShimmerVerObject> list = sensorGroupingDetails.mListOfCompatibleVersionInfo;
            List<String> list2 = sensorGroupingDetails.mListofGuiConfigNames;
            List<Integer> list3 = sensorGroupingDetails.mListOfSensorIdsAssociated;
        }
        final ArrayList arrayList = new ArrayList();
        LinkedHashMap<Integer, SensorDetails> sensorMap = this.shimmerDeviceClone.getSensorMap();
        Iterator<SensorDetails> it2 = sensorMap.values().iterator();
        int i = 0;
        while (it2.hasNext()) {
            if (this.shimmerDeviceClone.isVerCompatibleWithAnyOf(it2.next().mSensorDetailsRef.mListOfCompatibleVersionInfo)) {
                i++;
            }
        }
        String[] strArr = new String[i];
        final boolean[] zArr = new boolean[i];
        this.sensorKeys = new int[i];
        Iterator<Integer> it3 = sensorMap.keySet().iterator();
        final int i2 = 0;
        while (it3.hasNext()) {
            int iIntValue = it3.next().intValue();
            SensorDetails sensorDetails = sensorMap.get(Integer.valueOf(iIntValue));
            if (this.shimmerDeviceClone.isVerCompatibleWithAnyOf(sensorDetails.mSensorDetailsRef.mListOfCompatibleVersionInfo)) {
                strArr[i2] = sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel;
                zArr[i2] = sensorDetails.isEnabled();
                this.sensorKeys[i2] = iIntValue;
                i2++;
            }
        }
        setListAdapter(new ArrayAdapter(context, R.layout.simple_list_item_multiple_choice, strArr));
        final ListView listView = getListView();
        listView.setChoiceMode(2);
        if (listView.getFooterViewsCount() == 0) {
            Button button = new Button(context);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.shimmerresearch.android.guiUtilities.supportfragments.SensorsEnabledFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Toast.makeText(context, "Writing config to Shimmer...", 0).show();
                    if (shimmerDevice == null) {
                        Toast.makeText(context, "Error! The Shimmer Device is null!", 0).show();
                    }
                    if (SensorsEnabledFragment.this.shimmerDeviceClone != null) {
                        Iterator it4 = arrayList.iterator();
                        while (it4.hasNext()) {
                            int iIntValue2 = ((Integer) it4.next()).intValue();
                            SensorsEnabledFragment.this.shimmerDeviceClone.setSensorEnabledState(SensorsEnabledFragment.this.sensorKeys[iIntValue2], zArr[iIntValue2]);
                        }
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(0, SensorsEnabledFragment.this.shimmerDeviceClone);
                        AssembleShimmerConfig.generateMultipleShimmerConfig(arrayList2, Configuration.COMMUNICATION_TYPE.BLUETOOTH);
                        ShimmerDevice shimmerDevice2 = shimmerDevice;
                        if ((shimmerDevice2 instanceof Shimmer) || (shimmerDevice2 instanceof VerisenseDevice) || (shimmerDevice2 instanceof Shimmer3BLEAndroid)) {
                            shimmerBluetoothManagerAndroid.configureShimmer(SensorsEnabledFragment.this.shimmerDeviceClone);
                            SensorsEnabledFragment.this.mCallback.onSensorsSelected();
                            return;
                        } else {
                            boolean z = shimmerDevice2 instanceof Shimmer4Android;
                            return;
                        }
                    }
                    Toast.makeText(context, "Error! Shimmer Device clone is null!", 0).show();
                }
            });
            button.setText("Write config");
            button.setLayoutParams(new AbsListView.LayoutParams(-2, -2));
            listView.addFooterView(button);
        }
        updateCheckboxes(listView, i2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.shimmerresearch.android.guiUtilities.supportfragments.SensorsEnabledFragment.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i3, long j) {
                SensorsEnabledFragment.this.shimmerDeviceClone.getListOfEnabledSensors();
                CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(R.id.text1);
                checkedTextView.getText().toString();
                if (checkedTextView.isChecked()) {
                    SensorsEnabledFragment.this.shimmerDeviceClone.setSensorEnabledState(SensorsEnabledFragment.this.sensorKeys[i3], true);
                } else {
                    SensorsEnabledFragment.this.shimmerDeviceClone.setSensorEnabledState(SensorsEnabledFragment.this.sensorKeys[i3], false);
                }
                SensorsEnabledFragment.this.updateCheckboxes(listView, i2);
            }
        });
    }

    public View getViewByPosition(int i, ListView listView) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int childCount = (listView.getChildCount() + firstVisiblePosition) - 1;
        if (i < firstVisiblePosition || i > childCount) {
            return listView.getAdapter().getView(i, null, listView);
        }
        return listView.getChildAt(i - firstVisiblePosition);
    }

    protected boolean isSensorGroupCompatible(ShimmerDevice shimmerDevice, SensorGroupingDetails sensorGroupingDetails) {
        return shimmerDevice.isVerCompatibleWithAnyOf(sensorGroupingDetails.mListOfCompatibleVersionInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCheckboxes(ListView listView, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (((CheckedTextView) getViewByPosition(i2, listView).findViewById(R.id.text1)) != null) {
                if (this.shimmerDeviceClone.isSensorEnabled(this.sensorKeys[i2])) {
                    listView.setItemChecked(i2, true);
                } else {
                    listView.setItemChecked(i2, false);
                }
            } else {
                Log.e(LOG_TAG, "CheckedTextView is null!");
            }
        }
    }

    public interface OnSensorsSelectedListener {
        void onSensorsSelected();
    }
}
