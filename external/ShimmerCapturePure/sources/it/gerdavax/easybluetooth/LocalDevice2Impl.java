package it.gerdavax.easybluetooth;

import android.R;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.io.IOException;

import kotlin.jvm.internal.ShortCompanionObject;

/* loaded from: classes4.dex */
class LocalDevice2Impl extends LocalDevice {
    private static BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
    private NotificationManager notificationManager = null;    private final BroadcastReceiver receiver = new BroadcastReceiver() { // from class: it.gerdavax.easybluetooth.LocalDevice2Impl.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LocalDevice2Impl.log.v("received " + action);
            if (action.equals("android.bluetooth.device.action.FOUND")) {
                LocalDevice2Impl.this.scanListener.notifyDeviceFound(new RemoteDevice2Impl((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), intent.getShortExtra("android.bluetooth.device.extra.RSSI", ShortCompanionObject.MIN_VALUE)));
            } else if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                LocalDevice2Impl.this.scanListener.notifyScanCompleted();
                context.unregisterReceiver(LocalDevice2Impl.this.receiver);
            }
        }
    };
    LocalDevice2Impl() {
    }

    @Override // it.gerdavax.easybluetooth.LocalDevice
    public void destroy() {
        try {
            this.ctx.unregisterReceiver(this.receiver);
        } catch (IllegalArgumentException unused) {
        }
        this.ctx = null;
        super.destroy();
    }

    @Override // it.gerdavax.easybluetooth.LocalDevice
    public void init(Context context, final ReadyListener readyListener) {
        super.init(context, readyListener);
        if (!adapter.isEnabled()) {
            this.ctx.registerReceiver(new BroadcastReceiver() { // from class: it.gerdavax.easybluetooth.LocalDevice2Impl.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    if (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10) == 12) {
                        context2.unregisterReceiver(this);
                        ReadyListener readyListener2 = readyListener;
                        if (readyListener2 != null) {
                            readyListener2.notifyReady();
                        }
                    }
                }
            }, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            adapter.enable();
        } else if (readyListener != null) {
            readyListener.notifyReady();
        }
    }

    @Override // it.gerdavax.easybluetooth.LocalDevice
    public void scan(ScanListener scanListener) {
        super.scan(scanListener);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        this.ctx.registerReceiver(this.receiver, intentFilter);
        adapter.startDiscovery();
    }

    @Override // it.gerdavax.easybluetooth.LocalDevice
    public void stopScan() {
        adapter.cancelDiscovery();
    }

    @Override // it.gerdavax.easybluetooth.LocalDevice
    public RemoteDevice getRemoteForAddr(String str) {
        return new RemoteDevice2Impl(adapter.getRemoteDevice(str));
    }

    @Override // it.gerdavax.easybluetooth.LocalDevice
    public ServerControl listenForConnection(ConnectionListener connectionListener, int i) {
        try {
            ConnectionAlert connectionAlert = new ConnectionAlert((BluetoothServerSocket) adapter.getClass().getMethod("listenUsingRfcommOn", Integer.TYPE).invoke(adapter, Integer.valueOf(i)), connectionListener);
            connectionAlert.start();
            return connectionAlert;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // it.gerdavax.easybluetooth.LocalDevice
    public void ensureDiscoverable() {
        this.ctx.startActivity(new Intent("android.bluetooth.adapter.action.REQUEST_DISCOVERABLE"));
    }

    public void showDefaultPinInputActivity(BluetoothDevice bluetoothDevice, boolean z) {
        if (z) {
            clearSystemNotification();
        }
        Intent intent = new Intent("android.bluetooth.device.action.PAIRING_REQUEST");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", bluetoothDevice);
        intent.putExtra("android.bluetooth.device.extra.PAIRING_VARIANT", 0);
        intent.setFlags(268435456);
        this.ctx.startActivity(intent);
    }

    public void clearSystemNotification() {
        try {
            if (this.notificationManager == null) {
                this.notificationManager = (NotificationManager) this.ctx.getSystemService("notification");
            }
            this.notificationManager.cancel(R.drawable.stat_sys_data_bluetooth);
        } catch (Exception unused) {
        }
    }

    private class ConnectionAlert extends Thread implements ServerControl {
        private BluetoothServerSocket connection;
        private ConnectionListener listener;

        public ConnectionAlert(BluetoothServerSocket bluetoothServerSocket, ConnectionListener connectionListener) {
            this.connection = bluetoothServerSocket;
            this.listener = connectionListener;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws IOException {
            try {
                BluetoothSocket bluetoothSocketAccept = this.connection.accept(Integer.MAX_VALUE);
                LocalDevice2Impl.log.d(this, "connection unlocked");
                this.listener.notifyConnectionWaiting(new BtSocket2Impl(bluetoothSocketAccept));
            } catch (IOException e) {
                e.printStackTrace();
                this.listener.notifyConnectionError();
            }
        }

        @Override // it.gerdavax.easybluetooth.ServerControl
        public void halt() throws IOException {
            try {
                this.connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            interrupt();
        }
    }


}
