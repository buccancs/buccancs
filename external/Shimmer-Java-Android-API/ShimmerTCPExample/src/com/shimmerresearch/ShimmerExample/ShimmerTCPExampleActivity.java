

package com.shimmerresearch.ShimmerExample;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

import com.shimmerresearch.ShimmerExample.R;
import com.shimmerresearch.driver.*;
import com.shimmerresearch.android.*;
import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE;


public class ShimmerTCPExampleActivity extends Activity {

    Timer mTimer;
    DataOutputStream dOut = null;
    Socket clientSocket;
    String FromServer = "";
    String ToServer;
    boolean firstTime = true;
    private Shimmer mShimmerDevice1 = null;
    private final Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Shimmer.MESSAGE_READ:
                    if ((msg.obj instanceof ObjectCluster)) {
                        ObjectCluster objectCluster = (ObjectCluster) msg.obj;
                        byte[] dataojc = objectCluster.serialize();
                        if (dOut != null) {
                            try {
                                dOut.writeInt(dataojc.length);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                dOut.write(dataojc);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.print("dout fail \n");
                        }


                    }
                    break;
                case Shimmer.MESSAGE_TOAST:
                    Log.d("toast", msg.getData().getString(Shimmer.TOAST));
                    Toast.makeText(getApplicationContext(), msg.getData().getString(Shimmer.TOAST),
                            Toast.LENGTH_SHORT).show();
                    break;

                case Shimmer.MESSAGE_STATE_CHANGE:


                    switch (((ObjectCluster) msg.obj).mState) {
                        case CONNECTED:


                            if (firstTime) {
                                mShimmerDevice1.writeEnabledSensors(ShimmerObject.SENSOR_ACCEL);
                                Thread thread = new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            clientSocket = new Socket("10.1.1.1", 5000);
                                            dOut = new DataOutputStream(clientSocket.getOutputStream());
                                        } catch (UnknownHostException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };

                                thread.start();


                                Log.d("ConnectionStatus", "Successful");

                                mShimmerDevice1.startStreaming();
                                firstTime = false;
                            }
                            break;
                        case CONNECTING:

                            Log.d("ConnectionStatus", "Connecting");
                            break;
                        case STREAMING:
                            break;
                        case STREAMING_AND_SDLOGGING:
                            break;
                        case SDLOGGING:
                            break;
                        case DISCONNECTED:
                            Log.d("ConnectionStatus", "No State");
                            break;
                    }


                    break;

            }
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mShimmerDevice1 = new Shimmer(this, mHandler, "RightArm", false);
        String bluetoothAddress = "00:06:66:66:96:86";
        mShimmerDevice1.connect(bluetoothAddress, "default");
        Log.d("ConnectionStatus", "Trying");


    }

    public synchronized void shimmerTimer(int seconds) {
        mTimer = new Timer();
        mTimer.schedule(new responseTask(), seconds * 1000);
    }

    class responseTask extends TimerTask {
        public void run() {
            shimmerTimer(5);
        }
    }
}
    



    
    