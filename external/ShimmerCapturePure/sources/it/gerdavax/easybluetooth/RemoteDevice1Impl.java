package it.gerdavax.easybluetooth;

import it.gerdavax.android.bluetooth.BluetoothException;
import it.gerdavax.android.bluetooth.LocalBluetoothDevice;
import it.gerdavax.android.bluetooth.RemoteBluetoothDevice;
import it.gerdavax.android.bluetooth.RemoteBluetoothDeviceListener;
import it.gerdavax.util.Logger;

import java.util.UUID;

/* loaded from: classes4.dex */
class RemoteDevice1Impl extends RemoteDevice {
    private final Logger log = Logger.getLogger("EASYBT");
    private RemoteBluetoothDevice rbd;

    RemoteDevice1Impl(RemoteBluetoothDevice remoteBluetoothDevice) {
        this.rbd = null;
        this.rbd = remoteBluetoothDevice;
    }

    @Override // it.gerdavax.easybluetooth.RemoteDevice
    public String getFriendlyName() {
        try {
            return this.rbd.getName();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // it.gerdavax.easybluetooth.RemoteDevice
    public String getAddress() {
        return this.rbd.getAddress();
    }

    @Override // it.gerdavax.easybluetooth.RemoteDevice
    public BtSocket openSocket(UUID uuid) throws Exception {
        int leastSignificantBits = (int) uuid.getLeastSignificantBits();
        Object obj = new Object();
        C1PortDiscoverer c1PortDiscoverer = new C1PortDiscoverer(obj);
        this.rbd.setListener(c1PortDiscoverer);
        this.rbd.getRemoteServiceChannel(leastSignificantBits);
        try {
            obj.wait();
        } catch (InterruptedException unused) {
        }
        return openSocket(c1PortDiscoverer.port);
    }

    @Override // it.gerdavax.easybluetooth.RemoteDevice
    public int getRSSI() {
        return this.rbd.getRSSI();
    }

    @Override // it.gerdavax.easybluetooth.RemoteDevice
    public BtSocket openSocket(int i) throws BluetoothException {
        return new BtSocket1Impl(this.rbd.openSocket(i));
    }

    @Override // it.gerdavax.easybluetooth.RemoteDevice
    public boolean ensurePaired(String str) {
        if (this.rbd.isPaired()) {
            return true;
        }
        this.rbd.setListener(new RemoteBluetoothDeviceListener() { // from class: it.gerdavax.easybluetooth.RemoteDevice1Impl.1
            @Override // it.gerdavax.android.bluetooth.RemoteBluetoothDeviceListener
            public void gotServiceChannel(int i, int i2) {
            }

            @Override // it.gerdavax.android.bluetooth.RemoteBluetoothDeviceListener
            public void serviceChannelNotAvailable(int i) {
            }

            @Override // it.gerdavax.android.bluetooth.RemoteBluetoothDeviceListener
            public void pinRequested() {
                LocalBluetoothDevice.getLocalDevice().showDefaultPinInputActivity(RemoteDevice1Impl.this.getAddress(), true);
            }

            @Override // it.gerdavax.android.bluetooth.RemoteBluetoothDeviceListener
            public void paired() {
                RemoteDevice1Impl.this.log.i("paired()");
            }
        });
        if (str == null) {
            this.rbd.pair();
            return false;
        }
        this.rbd.pair(str);
        return false;
    }

    /* renamed from: it.gerdavax.easybluetooth.RemoteDevice1Impl$1PortDiscoverer, reason: invalid class name */
    class C1PortDiscoverer implements RemoteBluetoothDeviceListener {
        private final /* synthetic */ Object val$lock;
        int port;

        C1PortDiscoverer(Object obj) {
            this.val$lock = obj;
        }

        @Override // it.gerdavax.android.bluetooth.RemoteBluetoothDeviceListener
        public void paired() {
        }

        @Override // it.gerdavax.android.bluetooth.RemoteBluetoothDeviceListener
        public void serviceChannelNotAvailable(int i) {
            this.port = 1;
            this.val$lock.notify();
        }

        @Override // it.gerdavax.android.bluetooth.RemoteBluetoothDeviceListener
        public void pinRequested() {
            LocalBluetoothDevice.getLocalDevice().showDefaultPinInputActivity(RemoteDevice1Impl.this.getAddress(), true);
        }

        @Override // it.gerdavax.android.bluetooth.RemoteBluetoothDeviceListener
        public void gotServiceChannel(int i, int i2) {
            this.port = i2;
            this.val$lock.notify();
        }
    }
}
