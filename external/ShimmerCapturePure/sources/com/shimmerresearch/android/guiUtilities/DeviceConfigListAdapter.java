package com.shimmerresearch.android.guiUtilities;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import com.shimmerresearch.androidinstrumentdriver.R;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class DeviceConfigListAdapter extends BaseExpandableListAdapter {
    ShimmerDevice cloneDevice;
    List<String> expandableListTitle;
    ShimmerDevice shimmerDevice;
    private Context context;
    private HashMap<String, List<String>> expandableListDetail = new HashMap<>();
    private HashMap<String, String> currentSettingsMap = new HashMap<>();

    public DeviceConfigListAdapter(Context context, List<String> list, Map<String, ConfigOptionDetailsSensor> map, ShimmerDevice shimmerDevice, ShimmerDevice shimmerDevice2) {
        this.context = context;
        this.expandableListTitle = list;
        this.cloneDevice = shimmerDevice2;
        this.shimmerDevice = shimmerDevice;
        for (String str : list) {
            ConfigOptionDetailsSensor configOptionDetailsSensor = map.get(str);
            if (configOptionDetailsSensor != null) {
                String[] guiValues = configOptionDetailsSensor.getGuiValues();
                if (configOptionDetailsSensor.mGuiComponentType == ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX) {
                    if (guiValues != null) {
                        this.expandableListDetail.put(str, Arrays.asList(guiValues));
                        Object configValueUsingConfigLabel = this.shimmerDevice.getConfigValueUsingConfigLabel(str);
                        if (configValueUsingConfigLabel != null) {
                            int iIndexOf = Arrays.asList(map.get(str).getConfigValues()).indexOf(Integer.valueOf(((Integer) configValueUsingConfigLabel).intValue()));
                            this.currentSettingsMap.put(str, iIndexOf >= 0 ? (String) Arrays.asList(map.get(str).getGuiValues()).get(iIndexOf) : "");
                        }
                    } else {
                        Log.e("SHIMMER", "cs is null for key " + str);
                    }
                } else if (configOptionDetailsSensor.mGuiComponentType == ConfigOptionDetails.GUI_COMPONENT_TYPE.TEXTFIELD) {
                    String str2 = (String) this.shimmerDevice.getConfigValueUsingConfigLabel(str);
                    this.expandableListDetail.put(str, Arrays.asList("TEXTFIELD"));
                    this.currentSettingsMap.put(str, str2);
                }
            }
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public void updateCloneDevice(ShimmerDevice shimmerDevice) {
        this.cloneDevice = shimmerDevice;
    }

    private String getConfigValueLabelFromConfigLabel(String str) {
        ConfigOptionDetailsSensor configOptionDetailsSensor = this.cloneDevice.getConfigOptionsMap().get(str);
        int iIntValue = ((Integer) this.cloneDevice.getConfigValueUsingConfigLabel(str)).intValue();
        Integer[] configValues = configOptionDetailsSensor.getConfigValues();
        String[] guiValues = configOptionDetailsSensor.getGuiValues();
        int i = -1;
        for (int i2 = 0; i2 < configValues.length; i2++) {
            if (iIntValue == configValues[i2].intValue()) {
                i = i2;
            }
        }
        if (i == -1) {
            System.out.println();
            return "";
        }
        return guiValues[i];
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(final int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        String str = (String) getChild(i, i2);
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        if (str.contains("TEXTFIELD")) {
            if (view == null || view.findViewById(R.id.editText) == null) {
                view = layoutInflater.inflate(R.layout.list_item_textfield, (ViewGroup) null);
            }
            final EditText editText = (EditText) view.findViewById(R.id.editText);
            editText.setText((String) this.cloneDevice.getConfigValueUsingConfigLabel((String) getGroup(i)));
            editText.append("");
            editText.setOnKeyListener(new View.OnKeyListener() { // from class: com.shimmerresearch.android.guiUtilities.DeviceConfigListAdapter.1
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View view2, int i3, KeyEvent keyEvent) {
                    if (i3 != 66) {
                        return false;
                    }
                    DeviceConfigListAdapter.this.cloneDevice.setConfigValueUsingConfigLabel((String) DeviceConfigListAdapter.this.getGroup(i), editText.getText().toString());
                    DeviceConfigListAdapter.this.notifyDataSetChanged();
                    return false;
                }
            });
        } else {
            if (view == null || view.findViewById(R.id.expandedListItem) == null) {
                view = layoutInflater.inflate(R.layout.list_item, (ViewGroup) null);
            }
            CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(R.id.expandedListItem);
            checkedTextView.setText(str);
            if (getConfigValueLabelFromConfigLabel((String) getGroup(i)).equals(str)) {
                checkedTextView.setChecked(true);
            } else {
                checkedTextView.setChecked(false);
            }
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        return this.expandableListDetail.get(this.expandableListTitle.get(i)).size();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i, int i2) {
        return this.expandableListDetail.get(this.expandableListTitle.get(i)).get(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        return this.expandableListTitle.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        String str = (String) getGroup(i);
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        if (view == null || view.findViewById(R.id.listTitle) == null) {
            view = layoutInflater.inflate(R.layout.list_group, (ViewGroup) null);
        }
        TextView textView = (TextView) view.findViewById(R.id.listTitle);
        textView.setTypeface(null, 1);
        textView.setText(str);
        return view;
    }

    @Override // android.widget.BaseExpandableListAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void replaceCurrentSetting(String str, String str2) {
        this.currentSettingsMap.remove(str);
        this.currentSettingsMap.put(str, str2);
    }
}
