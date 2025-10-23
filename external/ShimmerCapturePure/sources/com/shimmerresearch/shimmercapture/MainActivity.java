package com.shimmerresearch.shimmercapture;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.androidplot.xy.XYPlot;
import com.clj.fastble.BleManager;
import com.shimmerresearch.android.Shimmer;
import com.shimmerresearch.android.Shimmer4Android;
import com.shimmerresearch.android.guiUtilities.ShimmerBluetoothDialog;
import com.shimmerresearch.android.guiUtilities.ShimmerDialogConfigurations;
import com.shimmerresearch.android.guiUtilities.supportfragments.ConnectedShimmersListFragment;
import com.shimmerresearch.android.guiUtilities.supportfragments.DataSyncFragment;
import com.shimmerresearch.android.guiUtilities.supportfragments.DeviceConfigFragment;
import com.shimmerresearch.android.guiUtilities.supportfragments.PlotFragment;
import com.shimmerresearch.android.guiUtilities.supportfragments.SensorsEnabledFragment;
import com.shimmerresearch.android.guiUtilities.supportfragments.SignalsToPlotFragment;
import com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid;
import com.shimmerresearch.android.protocol.VerisenseProtocolByteCommunicationAndroid;
import com.shimmerresearch.android.shimmerService.ShimmerService;
import com.shimmerresearch.androidradiodriver.Shimmer3BLEAndroid;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.driver.CallbackObject;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.AssembleShimmerConfig;
import com.shimmerresearch.driverUtilities.HwDriverShimmerDeviceDetails;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.sensors.lisxmdl.SensorLIS3MDL;
import com.shimmerresearch.tools.FileUtils;
import com.shimmerresearch.verisense.VerisenseDevice;
import com.shimmerresearch.verisense.communication.SyncProgressDetails;
import com.shimmerresearch.verisense.communication.VerisenseProtocolByteCommunication;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class MainActivity extends AppCompatActivity implements ConnectedShimmersListFragment.OnShimmerDeviceSelectedListener, SensorsEnabledFragment.OnSensorsSelectedListener {
    static final String LOG_TAG = "Shimmer";
    static final int REQUEST_CONNECT_SHIMMER = 2;
    static final String SERVICE_TAG = "ShimmerService";
    private static final int PERMISSION_FILE_REQUEST_SHIMMER = 99;
    private static final int PERMISSION_FILE_REQUEST_VERISENSE = 100;
    public static APP_RELEASE_TYPE appReleaseType = APP_RELEASE_TYPE.PUBLIC;
    public String selectedDeviceAddress;
    public String selectedDeviceName;
    BluetoothAdapter btAdapter;
    ConnectedShimmersListFragment connectedShimmersListFragment;
    DataSyncFragment dataSyncFragment;
    DeviceConfigFragment deviceConfigFragment;
    ShimmerDialogConfigurations dialog;
    XYPlot dynamicPlot;
    ShimmerService mService;
    PlotFragment plotFragment;
    ShimmerBluetoothManagerAndroid.BT_TYPE preferredBtType;
    SensorsEnabledFragment sensorsEnabledFragment;
    SignalsToPlotFragment signalsToPlotFragment;
    boolean isServiceStarted = false;
    Looper looper = Looper.myLooper();
    int mNumberOfCurrentlyConnectedDevices = 0;
    private SectionsPagerAdapter1 mSectionsPagerAdapter1;
    private ViewPager mViewPager;
    private Handler mHandler = new Handler() { // from class: com.shimmerresearch.shimmercapture.MainActivity.13
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ShimmerBluetooth.BT_STATE bt_state;
            String macAddress;
            String shimmerName;
            ShimmerDevice shimmer;
            if (message.what == 0) {
                if (message.obj instanceof ObjectCluster) {
                    bt_state = ((ObjectCluster) message.obj).mState;
                    macAddress = ((ObjectCluster) message.obj).getMacAddress();
                    shimmerName = ((ObjectCluster) message.obj).getShimmerName();
                } else if (message.obj instanceof CallbackObject) {
                    bt_state = ((CallbackObject) message.obj).mState;
                    macAddress = ((CallbackObject) message.obj).mBluetoothAddress;
                    shimmerName = "";
                } else {
                    bt_state = null;
                    macAddress = "";
                    shimmerName = macAddress;
                }
                int i = AnonymousClass15.$SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE[bt_state.ordinal()];
                if (i == 1) {
                    if (MainActivity.this.isNumberOfConnectedDevicesChanged()) {
                        MainActivity.this.connectedShimmersListFragment.buildShimmersConnectedListView(MainActivity.this.mService.getListOfConnectedDevices(), MainActivity.this.getApplicationContext());
                        if (MainActivity.this.selectedDeviceAddress != null && (shimmer = MainActivity.this.mService.getShimmer(MainActivity.this.selectedDeviceAddress)) != null) {
                            MainActivity.this.deviceConfigFragment.buildDeviceConfigList(shimmer, MainActivity.this.getApplicationContext(), MainActivity.this.mService.getBluetoothManager());
                        }
                    }
                    if (MainActivity.this.dataSyncFragment != null) {
                        MainActivity.this.dataSyncFragment.TextViewPayloadIndex.setText("");
                        MainActivity.this.dataSyncFragment.TextViewSpeed.setText("");
                        MainActivity.this.dataSyncFragment.editTextTrialName.setEnabled(true);
                        MainActivity.this.dataSyncFragment.editTextParticipantName.setEnabled(true);
                    }
                } else if (i == 3) {
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Device streaming: " + shimmerName + StringUtils.SPACE + macAddress, 0).show();
                    if (MainActivity.this.selectedDeviceAddress.contains(macAddress) && MainActivity.this.dynamicPlot != null) {
                        MainActivity.this.signalsToPlotFragment.buildSignalsToPlotList(MainActivity.this.getApplicationContext(), MainActivity.this.mService, macAddress, MainActivity.this.dynamicPlot);
                    }
                } else if (i != 4) {
                    if (i == 5) {
                        MainActivity.this.connectedShimmersListFragment.buildShimmersConnectedListView(MainActivity.this.mService.getListOfConnectedDevices(), MainActivity.this.getApplicationContext());
                    } else if (i == 6) {
                        Toast.makeText(MainActivity.this.getApplicationContext(), "Data Sync: " + shimmerName + StringUtils.SPACE + macAddress, 0).show();
                        MainActivity.this.dataSyncFragment.editTextTrialName.setEnabled(false);
                        MainActivity.this.dataSyncFragment.editTextParticipantName.setEnabled(false);
                    } else if (i == 7) {
                        MainActivity.this.isNumberOfConnectedDevicesChanged();
                        Toast.makeText(MainActivity.this.getApplicationContext(), "Device disconnected: " + shimmerName + StringUtils.SPACE + macAddress, 0).show();
                        MainActivity.this.connectedShimmersListFragment.buildShimmersConnectedListView(MainActivity.this.mService.getListOfConnectedDevices(), MainActivity.this.getApplicationContext());
                    }
                } else if (MainActivity.this.selectedDeviceAddress.contains(macAddress) && MainActivity.this.dynamicPlot != null) {
                    MainActivity.this.signalsToPlotFragment.buildSignalsToPlotList(MainActivity.this.getApplicationContext(), MainActivity.this.mService, macAddress, MainActivity.this.dynamicPlot);
                }
            } else if (message.what == 10) {
                SyncProgressDetails syncProgressDetails = (SyncProgressDetails) ((CallbackObject) message.obj).mMyObject;
                MainActivity.this.dataSyncFragment.TextViewPayloadIndex.setText("Current Payload Index : " + Integer.toString(syncProgressDetails.mPayloadIndex));
                MainActivity.this.dataSyncFragment.TextViewSpeed.setText("Speed(KBps) : " + String.format("%.2f", Double.valueOf(syncProgressDetails.mTransferRateBytes / 1024.0d)));
                MainActivity.this.dataSyncFragment.TextViewDirectory.setText("Bin file path : " + syncProgressDetails.mBinFilePath);
            } else if (message.what == 1 && ((CallbackObject) message.obj).mIndicator == 2) {
                Toast.makeText(MainActivity.this.getApplicationContext(), "Device fully initialized: ", 0).show();
                ShimmerDevice shimmer2 = MainActivity.this.mService.getShimmer(((CallbackObject) message.obj).mBluetoothAddress);
                DeviceConfigFragment deviceConfigFragment = MainActivity.this.deviceConfigFragment;
                MainActivity mainActivity = MainActivity.this;
                deviceConfigFragment.buildDeviceConfigList(shimmer2, mainActivity, mainActivity.mService.getBluetoothManager());
            }
            if (message.arg1 == 5) {
                MainActivity.this.signalsToPlotFragment.setDeviceNotStreamingView();
            }
        }
    };
    private ServiceConnection mConnection = new ServiceConnection() { // from class: com.shimmerresearch.shimmercapture.MainActivity.10
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MainActivity.this.mService = ((ShimmerService.LocalBinder) iBinder).getService();
            MainActivity.this.isServiceStarted = true;
            MainActivity.this.mService.addHandlerToList(MainActivity.this.mHandler);
            Log.d(MainActivity.SERVICE_TAG, "Shimmer Service Bound");
            if (MainActivity.this.connectedShimmersListFragment != null) {
                MainActivity.this.connectedShimmersListFragment.buildShimmersConnectedListView(MainActivity.this.mService.getListOfConnectedDevices(), MainActivity.this.getApplicationContext());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MainActivity.this.mService = null;
            MainActivity.this.isServiceStarted = false;
            Log.d(MainActivity.SERVICE_TAG, "Shimmer Service Disconnected");
        }
    };

    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Shimmer Capture App collect Location data to enable Bluetooth devices scanning on Android versions 11 and lower even when the app is closed or not in use.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.shimmerresearch.shimmercapture.MainActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) throws Resources.NotFoundException {
                MainActivity.this.requestPermissions();
            }
        });
        builder.show();
    }

    public void requestPermissions() throws Resources.NotFoundException {
        boolean z = ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0;
        if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_SCAN") != 0) {
            z = false;
        }
        boolean z2 = ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? z : false;
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0 || !z2) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, 110);
        } else {
            startServiceandBTManager();
        }
        setContentView(R.layout.activity_main);
        this.mSectionsPagerAdapter1 = new SectionsPagerAdapter1(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        this.mViewPager = viewPager;
        viewPager.setAdapter(this.mSectionsPagerAdapter1);
        this.mViewPager.setOffscreenPageLimit(5);
        this.btAdapter = BluetoothAdapter.getDefaultAdapter();
        this.dialog = new ShimmerDialogConfigurations();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
            startServiceandBTManager();
        }
    }

    protected void startServiceandBTManager() {
        BleManager.getInstance().init(getApplication());
        Intent intent = new Intent(this, (Class<?>) ShimmerService.class);
        startService(intent);
        getApplicationContext().bindService(intent, this.mConnection, 1);
        Log.d("Shimmer", "Shimmer Service started");
        Toast.makeText(this, "Shimmer Service started", 0).show();
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (appReleaseType.equals(APP_RELEASE_TYPE.PUBLIC) && this.mService != null) {
            menu.findItem(R.id.connect_device).setEnabled(this.mService.getListOfConnectedDevices().isEmpty());
        }
        MenuItem menuItemFindItem = menu.findItem(R.id.data_sync);
        MenuItem menuItemFindItem2 = menu.findItem(R.id.disable_logging);
        MenuItem menuItemFindItem3 = menu.findItem(R.id.erase_data);
        MenuItem menuItemFindItem4 = menu.findItem(R.id.set_sampling_rate);
        MenuItem menuItemFindItem5 = menu.findItem(R.id.enable_write_to_csv);
        MenuItem menuItemFindItem6 = menu.findItem(R.id.disable_write_to_csv);
        MenuItem menuItemFindItem7 = menu.findItem(R.id.low_power_mode);
        MenuItem menuItemFindItem8 = menu.findItem(R.id.start_sd_logging);
        MenuItem menuItemFindItem9 = menu.findItem(R.id.stop_sd_logging);
        MenuItem menuItemFindItem10 = menu.findItem(R.id.device_info);
        MenuItem menuItemFindItem11 = menu.findItem(R.id.privacy_policy);
        String str = this.selectedDeviceAddress;
        if (str != null) {
            ShimmerDevice shimmer = this.mService.getShimmer(str);
            if (shimmer instanceof VerisenseDevice) {
                menuItemFindItem.setVisible(true);
                menuItemFindItem2.setVisible(true);
                menuItemFindItem3.setVisible(true);
                menuItemFindItem4.setVisible(false);
                menuItemFindItem5.setVisible(false);
                menuItemFindItem6.setVisible(false);
                menuItemFindItem7.setVisible(false);
                menuItemFindItem8.setVisible(false);
                menuItemFindItem9.setVisible(false);
                menuItemFindItem10.setVisible(false);
                menuItemFindItem11.setVisible(false);
                if (((VerisenseDevice) shimmer).isRecordingEnabled()) {
                    menuItemFindItem2.setTitle("Disable Logging");
                    return true;
                }
                menuItemFindItem2.setTitle("Enable Logging");
                return true;
            }
            menuItemFindItem4.setVisible(true);
            menuItemFindItem5.setVisible(true);
            menuItemFindItem6.setVisible(true);
            menuItemFindItem7.setVisible(true);
            menuItemFindItem8.setVisible(true);
            menuItemFindItem9.setVisible(true);
            menuItemFindItem10.setVisible(true);
            menuItemFindItem11.setVisible(true);
        }
        menuItemFindItem.setVisible(false);
        menuItemFindItem2.setVisible(false);
        menuItemFindItem3.setVisible(false);
        return true;
    }

    /* JADX WARN: Type inference failed for: r3v23, types: [com.shimmerresearch.shimmercapture.MainActivity$2] */
    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) throws Resources.NotFoundException, IOException {
        final Toast toastMakeText = Toast.makeText(this, "Please wait until current task is finished", 1);
        int itemId = menuItem.getItemId();
        if (itemId == R.id.connect_device) {
            startActivityForResult(new Intent(getApplicationContext(), (Class<?>) ShimmerBluetoothDialog.class), 2);
            return true;
        }
        int i = 5;
        if (itemId == R.id.start_streaming) {
            String str = this.selectedDeviceAddress;
            if (str != null) {
                ShimmerDevice shimmer = this.mService.getShimmer(str);
                if (shimmer instanceof ShimmerBluetooth) {
                    ShimmerBluetooth shimmerBluetooth = (ShimmerBluetooth) shimmer;
                    shimmerBluetooth.enablePCTimeStamps(false);
                    shimmerBluetooth.stopAllTimers();
                }
                try {
                    shimmer.startStreaming();
                    ViewPager viewPager = this.mViewPager;
                    if (!(shimmer instanceof VerisenseDevice)) {
                        i = 4;
                    }
                    viewPager.setCurrentItem(i);
                } catch (ShimmerException e) {
                    if (e.getMessage() == VerisenseProtocolByteCommunication.ERROR_MSG_TASK_ONGOING) {
                        toastMakeText.show();
                    }
                    e.printStackTrace();
                }
                this.signalsToPlotFragment.buildSignalsToPlotList(this, this.mService, this.selectedDeviceAddress, this.dynamicPlot);
            }
            return true;
        }
        if (itemId == R.id.stop_streaming) {
            String str2 = this.selectedDeviceAddress;
            if (str2 != null) {
                try {
                    this.mService.getShimmer(str2).stopStreaming();
                } catch (ShimmerException e2) {
                    if (e2.getMessage() == VerisenseProtocolByteCommunication.ERROR_MSG_TASK_ONGOING) {
                        toastMakeText.show();
                    }
                    e2.printStackTrace();
                }
            }
            return true;
        }
        if (itemId == R.id.data_sync) {
            if (this.selectedDeviceAddress != null) {
                this.mViewPager.setCurrentItem(5);
            }
            return true;
        }
        if (itemId == R.id.disable_logging) {
            String str3 = this.selectedDeviceAddress;
            if (str3 != null) {
                VerisenseDevice verisenseDevice = (VerisenseDevice) this.mService.getShimmer(str3);
                VerisenseDevice verisenseDeviceDeepClone = verisenseDevice.deepClone();
                boolean z = !verisenseDevice.isRecordingEnabled();
                verisenseDeviceDeepClone.setRecordingEnabled(z);
                try {
                    verisenseDevice.getMapOfVerisenseProtocolByteCommunication().get(Configuration.COMMUNICATION_TYPE.BLUETOOTH).writeAndReadOperationalConfig(verisenseDeviceDeepClone.configBytesGenerate(true, Configuration.COMMUNICATION_TYPE.BLUETOOTH));
                    Toast.makeText(this, z ? "Logging Enabled" : "Logging Disabled", 0).show();
                } catch (ShimmerException e3) {
                    if (e3.getMessage() == VerisenseProtocolByteCommunication.ERROR_MSG_TASK_ONGOING) {
                        toastMakeText.show();
                    }
                    e3.printStackTrace();
                }
            }
            return true;
        }
        if (itemId == R.id.erase_data) {
            String str4 = this.selectedDeviceAddress;
            if (str4 != null) {
                final VerisenseDevice verisenseDevice2 = (VerisenseDevice) this.mService.getShimmer(str4);
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Erasing data");
                progressDialog.setMessage("Please wait for the operation to complete...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                new Thread() { // from class: com.shimmerresearch.shimmercapture.MainActivity.2
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            verisenseDevice2.getMapOfVerisenseProtocolByteCommunication().get(Configuration.COMMUNICATION_TYPE.BLUETOOTH).eraseDataTask().waitForCompletion(60L, TimeUnit.SECONDS);
                            progressDialog.dismiss();
                        } catch (ShimmerException | InterruptedException e4) {
                            progressDialog.dismiss();
                            if (e4.getMessage() == VerisenseProtocolByteCommunication.ERROR_MSG_TASK_ONGOING) {
                                toastMakeText.show();
                            }
                            e4.printStackTrace();
                        }
                    }
                }.start();
            }
            return true;
        }
        if (itemId == R.id.disconnect_all_devices) {
            this.mService.disconnectAllDevices();
            this.connectedShimmersListFragment.buildShimmersConnectedListView(null, getApplicationContext());
            this.mViewPager.setCurrentItem(0);
            this.selectedDeviceAddress = null;
            this.selectedDeviceName = null;
            this.connectedShimmersListFragment.removeSelectedDevice();
            return true;
        }
        if (itemId == R.id.enable_write_to_csv) {
            startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 99);
            this.mService.setEnableLogging(true);
            return true;
        }
        if (itemId == R.id.disable_write_to_csv) {
            this.mService.setEnableLogging(false);
            return true;
        }
        if (itemId == R.id.set_sampling_rate) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter Sampling Rate");
            final EditText editText = new EditText(this);
            String str5 = this.selectedDeviceAddress;
            if (str5 == null) {
                return true;
            }
            final ShimmerBluetooth shimmerBluetooth2 = (ShimmerBluetooth) this.mService.getShimmer(str5);
            editText.setText(Double.toString(shimmerBluetooth2.getSamplingRateShimmer()));
            builder.setView(editText);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.shimmerresearch.shimmercapture.MainActivity.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) throws NumberFormatException {
                    double d = Double.parseDouble(editText.getText().toString());
                    ShimmerDevice shimmerDeviceDeepClone = shimmerBluetooth2.deepClone();
                    shimmerDeviceDeepClone.setSamplingRateShimmer(d);
                    AssembleShimmerConfig.generateSingleShimmerConfig(shimmerDeviceDeepClone, Configuration.COMMUNICATION_TYPE.BLUETOOTH);
                    MainActivity.this.mService.configureShimmer(shimmerDeviceDeepClone);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: com.shimmerresearch.shimmercapture.MainActivity.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
            return true;
        }
        if (itemId == R.id.low_power_mode) {
            String str6 = this.selectedDeviceAddress;
            if (str6 == null) {
                return true;
            }
            final ShimmerDevice shimmer2 = this.mService.getShimmer(str6);
            final ShimmerDevice shimmerDeviceDeepClone = shimmer2.deepClone();
            View viewInflate = View.inflate(this, R.layout.checkbox, null);
            CheckBox checkBox = (CheckBox) viewInflate.findViewById(R.id.cbMagLPMode);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.shimmerresearch.shimmercapture.MainActivity.5
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                    shimmerDeviceDeepClone.setConfigValueUsingConfigLabel((Integer) 41, SensorLIS3MDL.GuiLabelConfig.LIS3MDL_ALT_MAG_LP, (Object) Boolean.valueOf(z2));
                }
            });
            checkBox.setText("Enable Mag LP Mode");
            checkBox.setChecked(Boolean.valueOf(shimmerDeviceDeepClone.getConfigGuiValueUsingConfigLabel(41, SensorLIS3MDL.GuiLabelConfig.LIS3MDL_ALT_MAG_LP)).booleanValue());
            CheckBox checkBox2 = (CheckBox) viewInflate.findViewById(R.id.cbWRAccelLPMode);
            checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.shimmerresearch.shimmercapture.MainActivity.6
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                    shimmerDeviceDeepClone.setConfigValueUsingConfigLabel((Integer) 39, "Wide Range Accel Low-Power Mode", (Object) Boolean.valueOf(z2));
                }
            });
            checkBox2.setText("Enable WR Accel LP Mode");
            checkBox2.setChecked(Boolean.valueOf(shimmerDeviceDeepClone.getConfigGuiValueUsingConfigLabel(39, "Wide Range Accel Low-Power Mode")).booleanValue());
            CheckBox checkBox3 = (CheckBox) viewInflate.findViewById(R.id.cbGyroLPMode);
            checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.shimmerresearch.shimmercapture.MainActivity.7
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                    shimmerDeviceDeepClone.setConfigValueUsingConfigLabel((Integer) 38, "Gyro Low-Power Mode", (Object) Boolean.valueOf(z2));
                }
            });
            checkBox3.setText("Enable LN Accel and Gyro LP Mode");
            if (shimmerDeviceDeepClone.getHardwareVersion() == 10) {
                checkBox3.setText("Enable LN Accel and Gyro LP Mode");
            } else {
                checkBox3.setText("Enable Gyro LP Mode");
            }
            checkBox3.setChecked(Boolean.valueOf(shimmerDeviceDeepClone.getConfigGuiValueUsingConfigLabel(38, "Gyro Low-Power Mode")).booleanValue());
            AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
            builder2.setTitle("Low Power Mode");
            builder2.setView(viewInflate).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.shimmerresearch.shimmercapture.MainActivity.9
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    if (shimmerDeviceDeepClone != null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(0, shimmerDeviceDeepClone);
                        AssembleShimmerConfig.generateMultipleShimmerConfig(arrayList, Configuration.COMMUNICATION_TYPE.BLUETOOTH);
                        ShimmerDevice shimmerDevice = shimmer2;
                        if ((shimmerDevice instanceof Shimmer) || (shimmerDevice instanceof VerisenseDevice) || (shimmerDevice instanceof Shimmer3BLEAndroid)) {
                            MainActivity.this.mService.getBluetoothManager().configureShimmer(shimmerDeviceDeepClone);
                        } else {
                            boolean z2 = shimmerDevice instanceof Shimmer4Android;
                        }
                    }
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: com.shimmerresearch.shimmercapture.MainActivity.8
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            }).show();
            return true;
        }
        if (itemId == R.id.start_sd_logging) {
            String str7 = this.selectedDeviceAddress;
            if (str7 == null) {
                return true;
            }
            this.mService.startLogging(str7);
            return true;
        }
        if (itemId == R.id.stop_sd_logging) {
            String str8 = this.selectedDeviceAddress;
            if (str8 == null) {
                return true;
            }
            this.mService.stopLogging(str8);
            return true;
        }
        if (itemId != R.id.device_info) {
            if (itemId == R.id.privacy_policy) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.shimmersensing.com/privacy/")));
                return true;
            }
            return super.onOptionsItemSelected(menuItem);
        }
        String str9 = this.selectedDeviceAddress;
        if (str9 == null) {
            return true;
        }
        ShimmerDevice shimmer3 = this.mService.getShimmer(str9);
        double dDoubleValue = shimmer3.getEstimatedChargePercentage().doubleValue();
        String hardwareVersionParsed = shimmer3.getHardwareVersionParsed();
        String firmwareVersionParsed = shimmer3.getFirmwareVersionParsed();
        if (firmwareVersionParsed.equals("")) {
            firmwareVersionParsed = "Unknown";
        }
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this).create();
        alertDialogCreate.setTitle("Device Info");
        alertDialogCreate.setMessage("Shimmer Version: " + hardwareVersionParsed + "\n\nFirmware Version: " + firmwareVersionParsed + "\n\nCharge Percentage: " + ((int) dDoubleValue) + Configuration.CHANNEL_UNITS.PERCENT);
        alertDialogCreate.show();
        return true;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && i == 100 && intent != null) {
            Uri data = intent.getData();
            VerisenseDevice verisenseDevice = (VerisenseDevice) this.mService.getShimmer(this.selectedDeviceAddress);
            verisenseDevice.setTrialName(this.dataSyncFragment.editTextTrialName.getText().toString());
            verisenseDevice.setParticipantID(this.dataSyncFragment.editTextParticipantName.getText().toString());
            new File(new FileUtils(this).getPath(data, FileUtils.UriType.FOLDER));
            ((VerisenseProtocolByteCommunicationAndroid) verisenseDevice.getMapOfVerisenseProtocolByteCommunication().get(Configuration.COMMUNICATION_TYPE.BLUETOOTH)).enableWriteToBinFile(this, data);
            try {
                verisenseDevice.getMapOfVerisenseProtocolByteCommunication().get(Configuration.COMMUNICATION_TYPE.BLUETOOTH).readLoggedData();
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace();
            }
        }
        if (i2 == -1 && i == 99 && intent != null) {
            this.mService.mFileURI = intent.getData();
            this.mService.mResolver = getContentResolver();
            this.mService.mContext = this;
        }
        if (i != 1) {
            if (i == 2 && i2 == -1) {
                String stringExtra = intent.getStringExtra(ShimmerBluetoothDialog.EXTRA_DEVICE_ADDRESS);
                String stringExtra2 = intent.getStringExtra(ShimmerBluetoothDialog.EXTRA_DEVICE_NAME);
                if (!stringExtra2.contains("Verisense")) {
                    if (!appReleaseType.equals(APP_RELEASE_TYPE.PUBLIC) || stringExtra2.contains(HwDriverShimmerDeviceDetails.DEVICE_TYPE.SHIMMER3R.toString())) {
                        showBtTypeConnectionOption();
                    } else {
                        this.preferredBtType = ShimmerBluetoothManagerAndroid.BT_TYPE.BT_CLASSIC;
                    }
                }
                this.mService.connectShimmer(stringExtra, stringExtra2, this.preferredBtType, this);
                return;
            }
            return;
        }
        if (i2 == -1) {
            Intent intent2 = new Intent(this, (Class<?>) ShimmerService.class);
            startService(intent2);
            getApplicationContext().bindService(intent2, this.mConnection, 1);
            Log.d("Shimmer", "Shimmer Service started");
            Toast.makeText(this, "Shimmer Service started", 0).show();
            return;
        }
        if (i2 == 0) {
            Toast.makeText(this, "Please enable Bluetooth to proceed.", 1).show();
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1);
        } else {
            Toast.makeText(this, "Unknown Error! Your device may not support Bluetooth!", 1).show();
        }
    }

    public void showBtTypeConnectionOption() {
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this).create();
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.setMessage("Choose preferred Bluetooth type");
        alertDialogCreate.setButton(-1, "BT CLASSIC", new DialogInterface.OnClickListener() { // from class: com.shimmerresearch.shimmercapture.MainActivity.11
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.preferredBtType = ShimmerBluetoothManagerAndroid.BT_TYPE.BT_CLASSIC;
                MainActivity.this.looper.quit();
            }
        });
        alertDialogCreate.setButton(-2, "BLE", new DialogInterface.OnClickListener() { // from class: com.shimmerresearch.shimmercapture.MainActivity.12
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.preferredBtType = ShimmerBluetoothManagerAndroid.BT_TYPE.BLE;
                MainActivity.this.looper.quit();
            }
        });
        alertDialogCreate.show();
        try {
            Looper.loop();
        } catch (RuntimeException unused) {
        }
    }

    public boolean isNumberOfConnectedDevicesChanged() {
        if (this.mService.getListOfConnectedDevices().size() == this.mNumberOfCurrentlyConnectedDevices) {
            return false;
        }
        this.mNumberOfCurrentlyConnectedDevices = this.mService.getListOfConnectedDevices().size();
        return true;
    }

    @Override
    // com.shimmerresearch.android.guiUtilities.supportfragments.ConnectedShimmersListFragment.OnShimmerDeviceSelectedListener
    public void onShimmerDeviceSelected(String str, String str2, Boolean bool) {
        if (bool.booleanValue()) {
            Toast.makeText(this, "Selected Device: " + str2 + StringUtils.LF + str, 0).show();
            this.selectedDeviceAddress = str;
            this.selectedDeviceName = str2;
            ShimmerDevice shimmer = this.mService.getShimmer(str);
            boolean z = shimmer instanceof Shimmer3BLEAndroid;
            if (z) {
                ((ShimmerBluetooth) shimmer).stopTimerReadStatus();
            }
            this.mSectionsPagerAdapter1.notifyDataSetChanged();
            this.sensorsEnabledFragment.setShimmerService(this.mService);
            this.sensorsEnabledFragment.buildSensorsList(shimmer, this, this.mService.getBluetoothManager());
            this.deviceConfigFragment.buildDeviceConfigList(shimmer, this, this.mService.getBluetoothManager());
            this.plotFragment.setShimmerService(this.mService);
            this.plotFragment.clearPlot();
            this.plotFragment.setSelectedDeviceAddress(this.selectedDeviceAddress);
            this.dynamicPlot = this.plotFragment.getDynamicPlot();
            this.mService.stopStreamingAllDevices();
            this.signalsToPlotFragment.setDeviceNotStreamingView();
            if (shimmer instanceof VerisenseDevice) {
                if (this.dataSyncFragment.editTextParticipantName.getText().toString().isEmpty()) {
                    this.dataSyncFragment.editTextParticipantName.setText("Default Participant");
                }
                if (this.dataSyncFragment.editTextTrialName.getText().toString().isEmpty()) {
                    this.dataSyncFragment.editTextTrialName.setText("Default trial");
                }
                this.dataSyncFragment.ButtonDataSync.setVisibility(0);
                this.dataSyncFragment.ButtonDataSync.setOnClickListener(new View.OnClickListener() { // from class: com.shimmerresearch.shimmercapture.MainActivity.14
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 100);
                    }
                });
            }
            if (z) {
                ((ShimmerBluetooth) shimmer).startTimerReadStatus();
                return;
            }
            return;
        }
        this.selectedDeviceAddress = null;
        this.mSectionsPagerAdapter1.notifyDataSetChanged();
    }

    @Override
    // com.shimmerresearch.android.guiUtilities.supportfragments.SensorsEnabledFragment.OnSensorsSelectedListener
    public void onSensorsSelected() {
        this.deviceConfigFragment.buildDeviceConfigList(this.mService.getShimmer(this.selectedDeviceAddress), this, this.mService.getBluetoothManager());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            getApplicationContext().bindService(new Intent(this, (Class<?>) ShimmerService.class), this.mConnection, 1);
        }
    }

    public enum APP_RELEASE_TYPE {
        INTERNAL,
        PUBLIC,
        TESTING
    }

    /* renamed from: com.shimmerresearch.shimmercapture.MainActivity$15, reason: invalid class name */
    static /* synthetic */ class AnonymousClass15 {
        static final /* synthetic */ int[] $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE;

        static {
            int[] iArr = new int[ShimmerBluetooth.BT_STATE.values().length];
            $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE = iArr;
            try {
                iArr[ShimmerBluetooth.BT_STATE.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE[ShimmerBluetooth.BT_STATE.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE[ShimmerBluetooth.BT_STATE.STREAMING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE[ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE[ShimmerBluetooth.BT_STATE.SDLOGGING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE[ShimmerBluetooth.BT_STATE.STREAMING_LOGGED_DATA.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE[ShimmerBluetooth.BT_STATE.DISCONNECTED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public class SectionsPagerAdapter1 extends FragmentStatePagerAdapter {
        static final int SHIMMER3_PAGE_COUNT = 5;
        static final int VERISENSE_PAGE_COUNT = 6;
        ArrayList<Fragment> fragmentArrayList;
        ArrayList<String> fragmentTitle;

        public SectionsPagerAdapter1(FragmentManager fragmentManager) {
            super(fragmentManager);
            this.fragmentArrayList = new ArrayList<>();
            this.fragmentTitle = new ArrayList<>();
            MainActivity.this.connectedShimmersListFragment = ConnectedShimmersListFragment.newInstance();
            MainActivity.this.sensorsEnabledFragment = SensorsEnabledFragment.newInstance(null, null);
            MainActivity.this.deviceConfigFragment = DeviceConfigFragment.newInstance();
            MainActivity.this.plotFragment = PlotFragment.newInstance();
            MainActivity.this.signalsToPlotFragment = SignalsToPlotFragment.newInstance();
            add(MainActivity.this.connectedShimmersListFragment, "Connected Devices");
            add(MainActivity.this.sensorsEnabledFragment, "Enable Sensors");
            add(MainActivity.this.deviceConfigFragment, "Device Configuration");
            add(MainActivity.this.plotFragment, "Plot");
            add(MainActivity.this.signalsToPlotFragment, "Signals to Plot");
            if (MainActivity.appReleaseType.equals(APP_RELEASE_TYPE.PUBLIC)) {
                return;
            }
            MainActivity.this.dataSyncFragment = DataSyncFragment.newInstance();
            add(MainActivity.this.dataSyncFragment, "Verisense Sync");
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(Object obj) {
            return -2;
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int i) {
            if (i >= this.fragmentArrayList.size()) {
                return null;
            }
            if (i == 0) {
                MainActivity.this.connectedShimmersListFragment.buildShimmersConnectedListView(null, MainActivity.this.getApplicationContext());
            }
            return this.fragmentArrayList.get(i);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.fragmentArrayList.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int i) {
            return i < this.fragmentTitle.size() ? this.fragmentTitle.get(i) : "";
        }

        public void add(Fragment fragment, String str, int i) {
            this.fragmentArrayList.add(i, fragment);
            this.fragmentTitle.add(i, str);
        }

        public void add(Fragment fragment, String str) {
            this.fragmentArrayList.add(fragment);
            this.fragmentTitle.add(str);
        }

        public void remove(int i) {
            if (this.fragmentArrayList.size() <= 1 || this.fragmentTitle.size() <= 1) {
                return;
            }
            this.fragmentArrayList.remove(i);
            this.fragmentTitle.remove(i);
        }
    }
}
