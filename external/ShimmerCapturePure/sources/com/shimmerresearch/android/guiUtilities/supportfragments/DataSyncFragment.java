package com.shimmerresearch.android.guiUtilities.supportfragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.shimmerresearch.androidinstrumentdriver.R;

/* loaded from: classes2.dex */
public class DataSyncFragment extends Fragment {
    public Button ButtonDataSync;
    public TextView TextViewDirectory;
    public TextView TextViewPayloadIndex;
    public TextView TextViewSpeed;
    public EditText editTextParticipantName;
    public EditText editTextTrialName;
    Context context;

    public static DataSyncFragment newInstance() {
        DataSyncFragment dataSyncFragment = new DataSyncFragment();
        dataSyncFragment.setArguments(new Bundle());
        return dataSyncFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.context = getActivity();
        return layoutInflater.inflate(R.layout.data_sync, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        this.ButtonDataSync = (Button) getView().findViewById(R.id.dataSyncButton);
        this.editTextParticipantName = (EditText) getView().findViewById(R.id.participantName);
        this.editTextTrialName = (EditText) getView().findViewById(R.id.trialName);
        this.TextViewPayloadIndex = (TextView) getView().findViewById(R.id.payloadIndex);
        this.TextViewSpeed = (TextView) getView().findViewById(R.id.speed);
        this.TextViewDirectory = (TextView) getView().findViewById(R.id.directory);
        super.onActivityCreated(bundle);
    }
}
