package com.shimmerresearch.android.guiUtilities.supportfragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.shimmerresearch.androidinstrumentdriver.R;


public class DataSyncFragment extends Fragment {

    public EditText editTextParticipantName;
    public EditText editTextTrialName;
    public TextView TextViewPayloadIndex;
    public TextView TextViewSpeed;
    public TextView TextViewDirectory;
    public Button ButtonDataSync;
    Context context;

    public DataSyncFragment() {
    }

    public static DataSyncFragment newInstance() {
        DataSyncFragment fragment = new DataSyncFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        return inflater.inflate(R.layout.data_sync, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ButtonDataSync = (Button) getView().findViewById(R.id.dataSyncButton);
        editTextParticipantName = (EditText) getView().findViewById(R.id.participantName);
        editTextTrialName = (EditText) getView().findViewById(R.id.trialName);
        TextViewPayloadIndex = (TextView) getView().findViewById(R.id.payloadIndex);
        TextViewSpeed = (TextView) getView().findViewById(R.id.speed);
        TextViewDirectory = (TextView) getView().findViewById(R.id.directory);
        super.onActivityCreated(savedInstanceState);
    }
}