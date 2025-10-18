package com.shimmerresearch.efficientdataarrayexample;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.clj.fastble.BleManager;
import com.shimmerresearch.android.Shimmer;
import com.shimmerresearch.android.guiUtilities.FileListActivity;
import com.shimmerresearch.android.guiUtilities.ShimmerBluetoothDialog;
import com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.driver.CallbackObject;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.HwDriverShimmerDeviceDetails;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.tools.FileUtils;
import com.shimmerresearch.verisense.VerisenseDevice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.shimmerresearch.android.guiUtilities.ShimmerBluetoothDialog.EXTRA_DEVICE_ADDRESS;
import static com.shimmerresearch.android.guiUtilities.ShimmerBluetoothDialog.EXTRA_DEVICE_NAME;
import static com.shimmerresearch.android.guiUtilities.ShimmerBluetoothDialog.REQUEST_CONNECT_SHIMMER;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;

public class MainActivity extends Activity {
    private static final int PERMISSION_FILE_REQUEST_SHIMMER = 99;
    private final static String LOG_TAG = "ArraysExample";
    private final static String CSV_FILE_NAME_PREFIX = "Data";
    private final static String APP_FOLDER_NAME = "ShimmerArraysExample";
    private final static String APP_FILE_PROVIDER_AUTHORITY = "com.shimmerresearch.efficientdataarrayexample.fileprovider";
    private final static int PERMISSIONS_REQUEST_WRITE_STORAGE = 5;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    ShimmerBluetoothManagerAndroid btManager;
    boolean firstTimeWrite = true;
    Uri mTreeUri;
    ShimmerBluetoothManagerAndroid.BT_TYPE preferredBtType;
    Looper looper = Looper.myLooper();
    int numberOfChannels = 0;
    private String bluetoothAdd = "";
    private String APP_DIR_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + APP_FOLDER_NAME + File.separator;
    private BufferedWriter bw;
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case ShimmerBluetooth.MSG_IDENTIFIER_DATA_PACKET:
                    if ((msg.obj instanceof ObjectCluster)) {


                        ObjectCluster objc = (ObjectCluster) msg.obj;

                        double data = objc.getFormatClusterValue(Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_WR_X, ChannelDetails.CHANNEL_TYPE.CAL.toString());
                        Log.i(LOG_TAG, Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_WR_X + " data: " + data);

                        int index = -1;
                        for (int i = 0; i < objc.sensorDataArray.mSensorNames.length; i++) {
                            if (objc.sensorDataArray.mSensorNames[i] != null) {
                                if (objc.sensorDataArray.mSensorNames[i].equals(Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_WR_X)) {
                                    index = i;
                                }
                            }
                        }
                        if (index != -1) {
                            data = objc.sensorDataArray.mCalData[index];
                            Log.w(LOG_TAG, Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_WR_X + " data: " + data);
                        }

                        index = objc.getIndexForChannelName(Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_WR_X);
                        if (index != -1) {
                            data = objc.sensorDataArray.mCalData[index];
                            Log.e(LOG_TAG, Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_WR_X + " data: " + data);
                        }

                        index = objc.getIndexForChannelName(Configuration.Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_OVERALL);
                        if (index != -1) {
                            data = objc.sensorDataArray.mCalData[index];
                            Log.e(LOG_TAG, Configuration.Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_OVERALL + " data: " + data);
                        }

                        /*
                            if (firstTimeWrite) {
                                for (String channelName : objc.sensorDataArray.mSensorNames) {
                                    try {
                                        bw.write(channelName + ",");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                try {
                                    bw.write("\n");
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                                firstTimeWrite = false;
                            }
                            for (double calData : objc.sensorDataArray.mCalData) {
                                String dataString = String.valueOf(calData);
                                try {
                                    bw.write(dataString + ",");
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            try {
                                bw.write("\n");
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
*/

                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                writeDataToFile(objc);
                            }
                        });
                    }
                    break;
                case Shimmer.MESSAGE_TOAST:
                    Toast.makeText(getApplicationContext(), msg.getData().getString(Shimmer.TOAST), Toast.LENGTH_SHORT).show();
                    break;
                case ShimmerBluetooth.MSG_IDENTIFIER_STATE_CHANGE:
                    ShimmerBluetooth.BT_STATE state = null;
                    String macAddress = "";

                    if (msg.obj instanceof ObjectCluster) {
                        state = ((ObjectCluster) msg.obj).mState;
                        macAddress = ((ObjectCluster) msg.obj).getMacAddress();
                    } else if (msg.obj instanceof CallbackObject) {
                        state = ((CallbackObject) msg.obj).mState;
                        macAddress = ((CallbackObject) msg.obj).mBluetoothAddress;
                    }
                    switch (state) {
                        case CONNECTED:
                            if (bw != null && !firstTimeWrite) {
                                try {
                                    bw.flush();
                                    bw.close();
                                    firstTimeWrite = true;
                                } catch (
                                        IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        case CONNECTING:
                            break;
                        case STREAMING:
                            break;
                        case STREAMING_AND_SDLOGGING:
                            break;
                        case SDLOGGING:
                            break;
                        case DISCONNECTED:
                            break;
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean permissionGranted = true;
        int permissionCheck = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT);

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                permissionGranted = false;
            }
            permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                permissionGranted = false;
            }
        } else {
            permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                permissionGranted = false;
            }
        }
        permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            permissionGranted = false;
        }
        permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            permissionGranted = false;
        }


        if (!permissionGranted) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 110);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 110);
            }
        } else {

            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);

            startActivityForResult(intent, PERMISSION_FILE_REQUEST_SHIMMER);


            try {
                BleManager.getInstance().init(getApplication());
                btManager = new ShimmerBluetoothManagerAndroid(this, mHandler);


            } catch (
                    Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void connectDevice(View v) {
        Intent intent = new Intent(getApplicationContext(), ShimmerBluetoothDialog.class);
        startActivityForResult(intent, REQUEST_CONNECT_SHIMMER);
    }

    public void disconnectDevice(View v) {
        btManager.disconnectAllDevices();
    }

    public void startStreaming(View v) {
        ShimmerBluetooth shimmer = (ShimmerBluetooth) btManager.getShimmer(bluetoothAdd);
        if (shimmer != null) {
            setupCSV();
            shimmer.enablePCTimeStamps(false);
            shimmer.stopAllTimers();
            shimmer.enableArraysDataStructure(true);
            try {
                btManager.startStreaming(bluetoothAdd);
            } catch (
                    ShimmerException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Can't start streaming\nShimmer device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void stopStreaming(View v) {
        if (btManager.getShimmer(bluetoothAdd) != null) {
            try {
                ShimmerBluetooth shimmer = (ShimmerBluetooth) btManager.getShimmer(bluetoothAdd);
                btManager.stopStreaming(bluetoothAdd);
            } catch (
                    ShimmerException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Can't stop streaming\nShimmer device is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                bluetoothAdd = data.getStringExtra(EXTRA_DEVICE_ADDRESS);
                String deviceName = data.getStringExtra(EXTRA_DEVICE_NAME);
                preferredBtType = ShimmerBluetoothManagerAndroid.BT_TYPE.BT_CLASSIC;
                if (deviceName.contains(HwDriverShimmerDeviceDetails.DEVICE_TYPE.SHIMMER3R.toString())) {
                    showBtTypeConnectionOption();
                }
                btManager.connectShimmerThroughBTAddress(bluetoothAdd, deviceName, preferredBtType);
            }

        }
        if (resultCode == RESULT_OK && requestCode == PERMISSION_FILE_REQUEST_SHIMMER) {
            if (data != null) {
                mTreeUri = data.getData();
                FileUtils futils = new FileUtils(MainActivity.this);
                File file = new File(futils.getPath(mTreeUri, FileUtils.UriType.FOLDER));
                APP_DIR_PATH = file.getAbsolutePath();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void showBtTypeConnectionOption() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Choose preferred Bluetooth type");
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "BT CLASSIC", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                preferredBtType = ShimmerBluetoothManagerAndroid.BT_TYPE.BT_CLASSIC;
                looper.quit();
            }

            ;
        });
        alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "BLE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                preferredBtType = ShimmerBluetoothManagerAndroid.BT_TYPE.BLE;
                looper.quit();
            }

            ;
        });
        alertDialog.show();
        try {
            looper.loop();
        } catch (
                RuntimeException e) {
        }
    }

    private int countNonNulls(String[] dataArray) {
        int count = 0;
        for (String data : dataArray) {
            if (data != null) {
                count++;
            }
        }
        return count;
    }

    private void writeDataToFile(ObjectCluster objc) {
        if (firstTimeWrite) {
            numberOfChannels = countNonNulls(objc.sensorDataArray.mSensorNames);
            int count = 1;
            for (String channelName : objc.sensorDataArray.mSensorNames) {
                try {
                    if (channelName != null) {
                        if (count < numberOfChannels) {
                            bw.write(channelName + ",");
                        } else {
                            bw.write(channelName);
                        }
                    }
                    count++;
                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                bw.write("\n");
            } catch (
                    IOException e2) {
                e2.printStackTrace();
            }
            firstTimeWrite = false;
        }
        int count = 1;
        for (double calData : objc.sensorDataArray.mCalData) {

            String dataString = String.valueOf(calData);
            try {
                if (objc.sensorDataArray.mSensorNames[count - 1] != null) {
                    if (count < numberOfChannels) {
                        bw.write(dataString + ",");
                    } else {
                        bw.write(dataString);
                    }
                }
                count++;
            } catch (
                    IOException e3) {
                e3.printStackTrace();
            }
        }
        try {
            bw.write("\n");
        } catch (
                IOException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 110) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);

            startActivityForResult(intent, PERMISSION_FILE_REQUEST_SHIMMER);


            try {
                btManager = new ShimmerBluetoothManagerAndroid(this, mHandler);


            } catch (
                    Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setupCSV() {
        File dir = new File(APP_DIR_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String fileName = CSV_FILE_NAME_PREFIX + "_" + new SimpleDateFormat("dd-MM-yy_HHmm").format(new Date());
        String filePath = APP_DIR_PATH + File.separator + fileName;
        DocumentFile pickedDir = DocumentFile.fromTreeUri(MainActivity.this, mTreeUri);
        DocumentFile newFile = pickedDir.createFile("text/comma-separated-values", fileName);
        if (newFile != null) {
            try {
                OutputStream outputStream = MainActivity.this.getContentResolver().openOutputStream(newFile.getUri());
                bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            } catch (
                    IOException e) {
                System.out.println();
            }
        } else {

        }


    }

    public void openLogFilesList(View v) {
        Intent intent = new Intent(getApplicationContext(), FileListActivity.class);
        intent.putExtra(FileListActivity.INTENT_EXTRA_DIR_PATH, APP_DIR_PATH);
        intent.putExtra(FileListActivity.INTENT_EXTRA_PROVIDER_AUTHORITY, APP_FILE_PROVIDER_AUTHORITY);
        startActivityForResult(intent, REQUEST_CONNECT_SHIMMER);
    }

}
