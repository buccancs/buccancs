package com.shimmerresearch.driver;

import com.shimmerresearch.comms.radioProtocol.CommsProtocolRadio;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.exceptions.ShimmerException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/* loaded from: classes2.dex */
public class ShimmerShell extends ShimmerDevice {
    private static final long serialVersionUID = 3505947289367382624L;

    public ShimmerShell() {
        setFirstDockRead();
    }

    public ShimmerShell(String str, int i) {
        this();
        setDockInfo(str, i);
    }

    public ShimmerShell(String str, int i, Configuration.COMMUNICATION_TYPE communication_type) {
        this(str, i);
        addCommunicationRoute(communication_type);
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public byte[] configBytesGenerate(boolean z, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void configBytesParse(byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void createConfigBytesLayout() {
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    protected void dataHandler(ObjectCluster objectCluster) {
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public Object getConfigValueUsingConfigLabel(String str) {
        return null;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    protected void interpretDataPacketFormat(Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
    }

    @Override // com.shimmerresearch.driver.BasicProcessWithCallBack
    protected void processMsgFromCallback(ShimmerMsg shimmerMsg) {
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void sensorAndConfigMapsCreate() {
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public List<Integer> sensorMapConflictCheck(Integer num) {
        return null;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public Object setConfigValueUsingConfigLabel(String str, String str2, Object obj) {
        return null;
    }

    public void setRadio(CommsProtocolRadio commsProtocolRadio) {
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public boolean setSensorEnabledState(int i, boolean z) {
        return false;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void connect() {
        try {
            super.connect();
        } catch (ShimmerException e) {
            e.printStackTrace();
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public ShimmerShell deepClone() throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            return (ShimmerShell) new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
