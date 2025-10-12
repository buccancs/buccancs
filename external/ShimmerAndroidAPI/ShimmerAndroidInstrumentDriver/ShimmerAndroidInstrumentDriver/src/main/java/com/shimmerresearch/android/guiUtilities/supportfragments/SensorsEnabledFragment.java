package com.shimmerresearch.android.guiUtilities.supportfragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SensorsEnabledFragment extends ListFragment {

    final static String LOG_TAG = "SHIMMER";
    ShimmerDevice shimmerDeviceClone, originalShimmerDevice;
    ShimmerService shimmerService = null;
    TreeMap<Integer, SensorGroupingDetails> compatibleSensorGroupMap;
    int sensorKeys[];
    OnSensorsSelectedListener mCallback;


    public SensorsEnabledFragment() {
    }

    public static SensorsEnabledFragment newInstance(String param1, String param2) {
        SensorsEnabledFragment fragment = new SensorsEnabledFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (SensorsEnabledFragment.OnSensorsSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnSensorSelectedListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[]{"No device selected, sensors unavailable"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    public void buildSensorsList(final ShimmerDevice shimmerDevice, final Context activityContext,
                                 final ShimmerBluetoothManagerAndroid bluetoothManager) {

        originalShimmerDevice = shimmerDevice;
        shimmerDeviceClone = shimmerDevice.deepClone();

        compatibleSensorGroupMap = new TreeMap<Integer, SensorGroupingDetails>();
        TreeMap<Integer, SensorGroupingDetails> groupMap = shimmerDeviceClone.getSensorGroupingMap();
        for (Map.Entry<Integer, SensorGroupingDetails> entry : groupMap.entrySet()) {
            if (isSensorGroupCompatible(shimmerDeviceClone, entry.getValue())) {
                compatibleSensorGroupMap.put(entry.getKey(), entry.getValue());
            }
        }

        for (SensorGroupingDetails sgd : groupMap.values()) {
            List<ShimmerVerObject> listOfCompatibleVersionInfo = sgd.mListOfCompatibleVersionInfo;
            List<String> listOfGuiConfigNames = sgd.mListofGuiConfigNames;
            List<Integer> listOfSensorMapKeysAssociated = sgd.mListOfSensorIdsAssociated;
            int a = 1;
            a++;
        }

        final List<Integer> mSelectedItems = new ArrayList();
        Map<Integer, SensorDetails> sensorMap = shimmerDeviceClone.getSensorMap();
        int count = 0;
        for (SensorDetails sd : sensorMap.values()) {
            if (shimmerDeviceClone.isVerCompatibleWithAnyOf(sd.mSensorDetailsRef.mListOfCompatibleVersionInfo)) {
                count++;
            }

        }
        String[] arraySensors = new String[count];
        final boolean[] listEnabled = new boolean[count];
        sensorKeys = new int[count];
        count = 0;

        for (int key : sensorMap.keySet()) {
            SensorDetails sd = sensorMap.get(key);
            if (shimmerDeviceClone.isVerCompatibleWithAnyOf(sd.mSensorDetailsRef.mListOfCompatibleVersionInfo)) {
                arraySensors[count] = sd.mSensorDetailsRef.mGuiFriendlyLabel;
                listEnabled[count] = sd.isEnabled();
                sensorKeys[count] = key;
                count++;
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activityContext, android.R.layout.simple_list_item_multiple_choice, arraySensors);
        setListAdapter(adapter);

        final ListView listView = getListView();
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        if (listView.getFooterViewsCount() == 0) {
            Button button = new Button(activityContext);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Toast.makeText(activityContext, "Writing config to Shimmer...", Toast.LENGTH_SHORT).show();
                    if (shimmerDevice == null) {
                        Toast.makeText(activityContext, "Error! The Shimmer Device is null!", Toast.LENGTH_SHORT).show();
                    }
                    if (shimmerDeviceClone != null) {
                        for (int selected : mSelectedItems) {
                            shimmerDeviceClone.setSensorEnabledState((int) sensorKeys[selected], listEnabled[selected]);
                        }

                        List<ShimmerDevice> cloneList = new ArrayList<ShimmerDevice>();
                        cloneList.add(0, shimmerDeviceClone);
                        AssembleShimmerConfig.generateMultipleShimmerConfig(cloneList, Configuration.COMMUNICATION_TYPE.BLUETOOTH);

                        if (shimmerDevice instanceof Shimmer || shimmerDevice instanceof VerisenseDevice || shimmerDevice instanceof Shimmer3BLEAndroid) {
                            /*try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }*/


                            bluetoothManager.configureShimmer(shimmerDeviceClone);
                            mCallback.onSensorsSelected();

                        } else if (shimmerDevice instanceof Shimmer4Android) {
                        }
                    } else {
                        Toast.makeText(activityContext, "Error! Shimmer Device clone is null!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            button.setText("Write config");
            button.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.WRAP_CONTENT, ListView.LayoutParams.WRAP_CONTENT));
            listView.addFooterView(button);
        }

        updateCheckboxes(listView, count);
        final int countUpdate = count;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                List<SensorDetails> enabledSensorsList = shimmerDeviceClone.getListOfEnabledSensors();
                CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(android.R.id.text1);
                CharSequence cs = checkedTextView.getText();
                String sensorName = cs.toString();

                if (checkedTextView.isChecked()) {
                    shimmerDeviceClone.setSensorEnabledState(sensorKeys[position], true);
                } else {
                    shimmerDeviceClone.setSensorEnabledState(sensorKeys[position], false);
                }

                updateCheckboxes(listView, countUpdate);

            }
        });

    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    protected boolean isSensorGroupCompatible(ShimmerDevice device, SensorGroupingDetails groupDetails) {
        List<ShimmerVerObject> listOfCompatibleVersionInfo = groupDetails.mListOfCompatibleVersionInfo;
        return device.isVerCompatibleWithAnyOf(listOfCompatibleVersionInfo);
    }

    public void setShimmerService(ShimmerService service) {
        shimmerService = service;
    }

    private void updateCheckboxes(ListView listView, int count) {
        for (int i = 0; i < count; i++) {
            View v = getViewByPosition(i, listView);
            CheckedTextView cTextView = (CheckedTextView) v.findViewById(android.R.id.text1);
            if (cTextView != null) {
                if (shimmerDeviceClone.isSensorEnabled(sensorKeys[i])) {
                    listView.setItemChecked(i, true);
                } else {
                    listView.setItemChecked(i, false);
                }
            } else {
                Log.e(LOG_TAG, "CheckedTextView is null!");
            }
        }
    }

    public interface OnSensorsSelectedListener {
        public void onSensorsSelected();
    }

}
