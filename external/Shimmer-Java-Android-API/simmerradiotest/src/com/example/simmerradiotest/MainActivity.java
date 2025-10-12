package com.example.simmerradiotest;

import com.shimmerresearch.androidradiodriver.ShimmerSerialPortAndroid;
import com.shimmerresearch.bluetooth.ShimmerRadioProtocol;
import com.shimmerresearch.comms.radioProtocol.LiteProtocol;
import com.shimmerresearch.comms.radioProtocol.RadioListener;
import com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet;
import com.shimmerresearch.driver.DeviceException;
import com.shimmerresearch.driver.Shimmer4Test;
import com.shimmerresearch.driver.UtilShimmer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    ShimmerRadioProtocol mShimmerRadioProtocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShimmerRadioProtocol = new ShimmerRadioProtocol(new ShimmerSerialPortAndroid("00:06:66:66:96:86"), new LiteProtocol());
        try {
            mShimmerRadioProtocol.connect();
        } catch (DeviceException e) {
            e.printStackTrace();
        }

        Button btnAccelRange = (Button) this.findViewById(R.id.btnReadAccelRange);

        btnAccelRange.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                byte[] ins = new byte[1];
                ins[0] = LiteProtocolInstructionSet.InstructionsGet.GET_ACCEL_SENSITIVITY_COMMAND_VALUE;
                mShimmerRadioProtocol.mRadioProtocol.writeInstruction(ins);
            }
        });
        mShimmerRadioProtocol.setRadioListener(new RadioListener() {

            @Override
            public void connected() {

                mShimmerRadioProtocol.mRadioProtocol.initialize();


                mShimmerRadioProtocol.mRadioProtocol.setPacketSize(41);
            }

            @Override
            public void disconnected() {

            }

            @Override
            public void eventNewPacket(byte[] pbA) {
                System.out.println("New Packet: " + UtilShimmer.bytesToHexString(pbA));

            }

            @Override
            public void eventResponseReceived(byte[] responseBytes) {
                System.out.println("Response Received: " + UtilShimmer.bytesToHexString(responseBytes));

            }

            @Override
            public void eventAckReceived(byte[] instructionSent) {
                System.out.println("Ack Received: " + UtilShimmer.bytesToHexString(instructionSent));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
