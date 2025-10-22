package com.shimmerresearch.verisense.communication.payloads;

import com.shimmerresearch.driverUtilities.ChannelDetails;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class RecordBufferDetailsPayload extends AbstractPayload {
    private static int NUM_MEMORY_BANKS = 2;
    private List<RecordBufferDetails> listOfRecordBufferDetails = new ArrayList();

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public byte[] generatePayloadContents() {
        return null;
    }

    public List<RecordBufferDetails> getListOfRecordBufferDetails() {
        return this.listOfRecordBufferDetails;
    }

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public boolean parsePayloadContents(byte[] bArr) {
        this.payloadContents = bArr;
        this.isSuccess = false;
        this.listOfRecordBufferDetails.clear();
        int length = bArr.length / NUM_MEMORY_BANKS;
        for (int i = 0; i < bArr.length; i += length) {
            RecordBufferDetails recordBufferDetails = new RecordBufferDetails(length);
            recordBufferDetails.bufferIndex = bArr[i];
            recordBufferDetails.bufferState = bArr[i + 1];
            recordBufferDetails.packagedPayloadIndex = parseByteArrayAtIndex(bArr, i + 2, ChannelDetails.CHANNEL_DATA_TYPE.UINT16);
            recordBufferDetails.currentByteIndexForSensorData = parseByteArrayAtIndex(bArr, i + 4, ChannelDetails.CHANNEL_DATA_TYPE.UINT16);
            recordBufferDetails.usedBufferLength = parseByteArrayAtIndex(bArr, i + 6, ChannelDetails.CHANNEL_DATA_TYPE.UINT16);
            recordBufferDetails.fifoTicks = parseByteArrayAtIndex(bArr, i + 8, ChannelDetails.CHANNEL_DATA_TYPE.UINT16);
            recordBufferDetails.dataTsRwcMinutes = parseByteArrayAtIndex(bArr, i + 10, ChannelDetails.CHANNEL_DATA_TYPE.UINT32);
            recordBufferDetails.dataTsRwcTicks = parseByteArrayAtIndex(bArr, i + 14, ChannelDetails.CHANNEL_DATA_TYPE.UINT24);
            recordBufferDetails.temperatureData = parseByteArrayAtIndex(bArr, i + 17, ChannelDetails.CHANNEL_DATA_TYPE.UINT16);
            if (length >= 25) {
                recordBufferDetails.dataTsUcClockMinutes = parseByteArrayAtIndex(bArr, i + 19, ChannelDetails.CHANNEL_DATA_TYPE.UINT32);
                recordBufferDetails.dataTsUcClockTicks = parseByteArrayAtIndex(bArr, i + 23, ChannelDetails.CHANNEL_DATA_TYPE.UINT24);
            }
            this.listOfRecordBufferDetails.add(recordBufferDetails);
        }
        this.isSuccess = true;
        return this.isSuccess;
    }

    @Override // com.shimmerresearch.verisense.communication.payloads.AbstractPayload
    public String generateDebugString() {
        StringBuilder sb = new StringBuilder();
        Iterator<RecordBufferDetails> it2 = this.listOfRecordBufferDetails.iterator();
        while (it2.hasNext()) {
            sb.append(it2.next().generateDebugString());
        }
        return sb.toString();
    }

    public class RecordBufferDetails {
        public byte bufferIndex;
        public byte bufferState;
        public long currentByteIndexForSensorData;
        public long dataTsRwcMinutes;
        public long dataTsRwcTicks;
        public long dataTsUcClockMinutes;
        public long dataTsUcClockTicks;
        public long fifoTicks;
        public long packagedPayloadIndex;
        public long temperatureData;
        public long usedBufferLength;
        private int numBytesPerRecordBuffer;

        public RecordBufferDetails(int i) {
            this.numBytesPerRecordBuffer = i;
        }

        public String generateDebugString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Index = " + ((int) this.bufferIndex) + ", State = " + ((int) this.bufferState) + StringUtils.LF);
            long j = this.packagedPayloadIndex;
            StringBuilder sb2 = new StringBuilder("\tpackagedPayloadIndex = ");
            sb2.append(j);
            sb2.append(StringUtils.LF);
            sb.append(sb2.toString());
            sb.append("\tcurrentByteIndexForSensorData = " + this.currentByteIndexForSensorData + StringUtils.LF);
            sb.append("\tusedBufferLength = " + this.usedBufferLength + StringUtils.LF);
            sb.append("\tfifoTicks = " + this.fifoTicks + StringUtils.LF);
            sb.append("\tdataTsRwcMinutes = " + this.dataTsRwcMinutes + StringUtils.LF);
            sb.append("\tdataTsRwcTicks = " + this.dataTsRwcTicks + StringUtils.LF);
            sb.append("\ttemperatureData = " + this.temperatureData + StringUtils.LF);
            if (this.numBytesPerRecordBuffer >= 25) {
                sb.append("\tdataTsUcClockMinutes = " + this.dataTsUcClockMinutes + StringUtils.LF);
                sb.append("\tdataTsUcClockTicks = " + this.dataTsUcClockTicks + StringUtils.LF);
            }
            return sb.toString();
        }
    }
}
