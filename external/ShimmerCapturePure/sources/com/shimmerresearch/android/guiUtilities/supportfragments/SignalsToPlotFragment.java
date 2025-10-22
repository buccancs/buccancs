package com.shimmerresearch.android.guiUtilities.supportfragments;

import android.R;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.ListFragment;
import com.androidplot.xy.XYPlot;
import com.shimmerresearch.android.shimmerService.ShimmerService;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class SignalsToPlotFragment extends ListFragment {
    static final String LOG_TAG = "SignalsToPlotFragment";
    String bluetoothAddress;
    LinkedHashMap<String, ChannelDetails> channelsMap;
    Context context;
    XYPlot dynamicPlot;
    ListView listView;
    ShimmerService shimmerService;
    List<String[]> listOfEnabledChannelsAndFormats = new ArrayList();
    List<String[]> mList = new ArrayList();
    ShimmerDevice shimmerDevice = null;

    public static SignalsToPlotFragment newInstance() {
        Bundle bundle = new Bundle();
        SignalsToPlotFragment signalsToPlotFragment = new SignalsToPlotFragment();
        signalsToPlotFragment.setArguments(bundle);
        return signalsToPlotFragment;
    }

    public static String joinStrings(String[] strArr) {
        String str = "";
        for (int i = 0; i < strArr.length; i++) {
            str = i == 0 ? strArr[i] : str + StringUtils.SPACE + strArr[i];
        }
        return str;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        this.listView = getListView();
        super.onActivityCreated(bundle);
        setListAdapter(new ArrayAdapter(getActivity(), R.layout.simple_list_item_1, new String[]{"No device selected, signals unavailable"}));
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    public void buildSignalsToPlotList(Context context, ShimmerService shimmerService, String str, XYPlot xYPlot) {
        this.context = context;
        this.shimmerService = shimmerService;
        this.dynamicPlot = xYPlot;
        ShimmerDevice shimmer = shimmerService.getBluetoothManager().getShimmer(str);
        if (shimmer.isStreaming()) {
            shimmer.getListofEnabledChannelSignalsandFormats();
            this.channelsMap = shimmer.getMapOfEnabledChannelsForStreaming();
            ArrayList arrayList = new ArrayList();
            List<String[]> listofEnabledSensorSignals = this.shimmerService.getListofEnabledSensorSignals(str);
            ArrayList arrayList2 = new ArrayList();
            Iterator<ChannelDetails> it2 = this.channelsMap.values().iterator();
            while (it2.hasNext()) {
                this.listOfEnabledChannelsAndFormats.addAll(it2.next().getListOfChannelSignalsAndFormats());
            }
            for (int i = 0; i < listofEnabledSensorSignals.size(); i++) {
                arrayList2.add(joinStrings(listofEnabledSensorSignals.get(i)));
            }
            String shimmerUserAssignedName = shimmer.getShimmerUserAssignedName();
            final ArrayList arrayList3 = new ArrayList();
            int i2 = 0;
            for (ChannelDetails channelDetails : this.channelsMap.values()) {
                List<ChannelDetails.CHANNEL_TYPE> list = channelDetails.mListOfChannelTypes;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    String strName = list.get(i3).name();
                    if (strName.contains("UNCAL")) {
                        arrayList3.add(i2, new String[]{shimmerUserAssignedName, channelDetails.getChannelObjectClusterName(), strName, channelDetails.mDefaultUncalUnit});
                    } else {
                        arrayList3.add(i2, new String[]{shimmerUserAssignedName, channelDetails.getChannelObjectClusterName(), strName, channelDetails.mDefaultCalUnits});
                    }
                    i2++;
                }
            }
            for (int i4 = 0; i4 < arrayList3.size(); i4++) {
                String[] strArr = (String[]) arrayList3.get(i4);
                arrayList.add(i4, strArr[1] + StringUtils.SPACE + strArr[2]);
            }
            this.mList = arrayList3;
            setListAdapter(new ArrayAdapter(context, com.shimmerresearch.androidinstrumentdriver.R.layout.simple_list_item_multiple_choice_force_black_text, R.id.text1, (String[]) arrayList.toArray(new String[arrayList.size()])));
            this.listView.setChoiceMode(2);
            updateCheckboxes();
            this.shimmerService.mPlotManager.removeAllSignals();
            SparseBooleanArray checkedItemPositions = this.listView.getCheckedItemPositions();
            for (int i5 = 0; i5 < this.listView.getCount(); i5++) {
                if (checkedItemPositions.get(i5)) {
                    try {
                        if (this.dynamicPlot == null) {
                            Log.e(LOG_TAG, "dynamicPlot is null!");
                        }
                        this.shimmerService.mPlotManager.addSignal((String[]) arrayList3.get(i5), this.dynamicPlot);
                    } catch (Exception e) {
                        Log.e(LOG_TAG, "Error! Could not add signal: " + e);
                        e.printStackTrace();
                    }
                }
            }
            this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.shimmerresearch.android.guiUtilities.supportfragments.SignalsToPlotFragment.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i6, long j) {
                    if (!SignalsToPlotFragment.this.shimmerService.mPlotManager.checkIfPropertyExist((String[]) arrayList3.get(i6))) {
                        try {
                            if (SignalsToPlotFragment.this.dynamicPlot == null) {
                                Log.e(SignalsToPlotFragment.LOG_TAG, "dynamicPlot is null!");
                            }
                            SignalsToPlotFragment.this.shimmerService.mPlotManager.addSignal((String[]) arrayList3.get(i6), SignalsToPlotFragment.this.dynamicPlot);
                            return;
                        } catch (Exception e2) {
                            Log.e(SignalsToPlotFragment.LOG_TAG, "Error! Could not add signal: " + e2);
                            e2.printStackTrace();
                            return;
                        }
                    }
                    SignalsToPlotFragment.this.shimmerService.mPlotManager.removeSignal((String[]) arrayList3.get(i6));
                }
            });
        }
    }

    public void updateCheckboxes() {
        for (int i = 0; i < this.mList.size(); i++) {
            if (this.shimmerService.mPlotManager.checkIfPropertyExist(this.mList.get(i))) {
                this.listView.setItemChecked(i, true);
            } else {
                this.listView.setItemChecked(i, false);
            }
        }
    }

    public void setDeviceNotStreamingView() {
        setListAdapter(new ArrayAdapter(getActivity(), R.layout.simple_list_item_1, new String[]{"Device not streaming", "Signals to plot can only be displayed when device is streaming"}));
    }
}
