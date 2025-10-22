package com.shimmerresearch.android.guiUtilities;

import android.R;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import com.shimmerresearch.driver.ShimmerDevice;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class ConnectedShimmersListFragment extends ListFragment {
    static final String LOG_TAG = "SHIMMER";
    Context context;
    OnShimmerDeviceSelectedListener mCallBack;
    String selectedDeviceAddress;
    String selectedDeviceName;
    List<ShimmerDevice> shimmerDeviceList;
    ListView savedListView = null;
    ArrayAdapter<String> savedListAdapter = null;
    int selectedItemPos = -1;
    int selectedDevicePos = -1;

    public static ConnectedShimmersListFragment newInstance() {
        ConnectedShimmersListFragment connectedShimmersListFragment = new ConnectedShimmersListFragment();
        connectedShimmersListFragment.setArguments(new Bundle());
        return connectedShimmersListFragment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mCallBack = (OnShimmerDeviceSelectedListener) activity;
        } catch (ClassCastException unused) {
            throw new ClassCastException(activity.toString() + " must implement OnShimmerDeviceSelectedListener");
        }
    }

    public void buildShimmersConnectedListView(List<ShimmerDevice> list, Context context) {
        if (isVisible()) {
            this.shimmerDeviceList = list;
            this.context = context;
            if (list == null) {
                setListAdapter(new ArrayAdapter(context, R.layout.simple_list_item_1, new String[]{"No devices connected"}));
                return;
            }
            int size = list.size();
            final String[] strArr = new String[size];
            final String[] strArr2 = new String[list.size()];
            String[] strArr3 = new String[list.size()];
            for (int i = 0; i < size; i++) {
                strArr[i] = list.get(i).getShimmerUserAssignedName();
                strArr2[i] = list.get(i).getMacId();
                strArr3[i] = strArr[i] + StringUtils.LF + strArr2[i];
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, com.shimmerresearch.androidinstrumentdriver.R.layout.simple_list_item_multiple_choice_force_black_text, strArr3);
            setListAdapter(arrayAdapter);
            ListView listView = getListView();
            listView.setChoiceMode(1);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.shimmerresearch.android.guiUtilities.ConnectedShimmersListFragment.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                    ConnectedShimmersListFragment.this.selectedItemPos = i2;
                    ConnectedShimmersListFragment.this.selectedDeviceAddress = strArr2[i2];
                    ConnectedShimmersListFragment.this.selectedDeviceName = strArr[i2];
                    ConnectedShimmersListFragment.this.selectedDevicePos = i2;
                    try {
                        ConnectedShimmersListFragment.this.mCallBack.onShimmerDeviceSelected(strArr2[i2], strArr[i2]);
                    } catch (ClassCastException unused) {
                    }
                }
            });
            this.savedListView = listView;
            this.savedListAdapter = arrayAdapter;
            if (this.selectedDeviceAddress != null) {
                for (int i2 = 0; i2 < listView.getAdapter().getCount(); i2++) {
                    CheckedTextView checkedTextView = (CheckedTextView) getViewByPosition(i2, listView).findViewById(R.id.text1);
                    if (checkedTextView != null) {
                        if (checkedTextView.getText().toString().contains(this.selectedDeviceAddress)) {
                            listView.setItemChecked(i2, true);
                        } else {
                            listView.setItemChecked(i2, false);
                        }
                    }
                }
            }
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        if (this.savedListView != null && this.savedListAdapter != null) {
            buildShimmersConnectedListView(this.shimmerDeviceList, this.context);
        } else {
            buildShimmersConnectedListView(null, getActivity().getApplicationContext());
        }
        Log.e(LOG_TAG, "ConnectedShimmersListFragment onResume()");
        super.onResume();
    }

    public View getViewByPosition(int i, ListView listView) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int childCount = (listView.getChildCount() + firstVisiblePosition) - 1;
        if (i < firstVisiblePosition || i > childCount) {
            return listView.getAdapter().getView(i, null, listView);
        }
        return listView.getChildAt(i - firstVisiblePosition);
    }

    public interface OnShimmerDeviceSelectedListener {
        void onShimmerDeviceSelected(String str, String str2);
    }
}
