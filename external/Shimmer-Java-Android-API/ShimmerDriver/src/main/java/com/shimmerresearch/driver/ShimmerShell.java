package com.shimmerresearch.driver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shimmerresearch.comms.radioProtocol.CommsProtocolRadio;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.exceptions.ShimmerException;

public class ShimmerShell extends ShimmerDevice {

    private static final long serialVersionUID = 3505947289367382624L;

    public ShimmerShell() {
        setFirstDockRead();
    }

    public ShimmerShell(String dockId, int slotNumber) {
        this();
        setDockInfo(dockId, slotNumber);
    }

    public ShimmerShell(String dockId, int slotNumber, COMMUNICATION_TYPE connectionType) {
        this(dockId, slotNumber);
        addCommunicationRoute(connectionType);
    }

    @Override
    public void connect() {
        try {
            super.connect();
        } catch (ShimmerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ShimmerShell deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (ShimmerShell) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean setSensorEnabledState(int sensorId, boolean state) {
        return false;
    }

    @Override
    public List<Integer> sensorMapConflictCheck(Integer key) {
        return null;
    }

    @Override
    public Object setConfigValueUsingConfigLabel(String groupName, String componentName, Object configValue) {
        return null;
    }

    @Override
    public Object getConfigValueUsingConfigLabel(String componentName) {
        return null;
    }

    @Override
    public void configBytesParse(byte[] configBytes, COMMUNICATION_TYPE commType) {

    }

    @Override
    public byte[] configBytesGenerate(boolean generateForWritingToShimmer, COMMUNICATION_TYPE commType) {
        return null;
    }

    @Override
    protected void processMsgFromCallback(ShimmerMsg shimmerMSG) {

    }

    @Override
    public void sensorAndConfigMapsCreate() {

    }

    @Override
    protected void interpretDataPacketFormat(Object object, COMMUNICATION_TYPE commType) {

    }

    @Override
    public void createConfigBytesLayout() {

    }

    public void setRadio(CommsProtocolRadio shimmerRadioProtocol) {
    }

    @Override
    protected void dataHandler(ObjectCluster ojc) {

    }

}
