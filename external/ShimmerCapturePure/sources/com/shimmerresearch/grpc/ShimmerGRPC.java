package com.shimmerresearch.grpc;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class ShimmerGRPC {
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_BluetoothDevicesDetails_BluetoothDeviceDetails_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_BluetoothDevicesDetails_BluetoothDeviceDetails_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_BluetoothDevicesDetails_DeviceMapEntry_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_BluetoothDevicesDetails_DeviceMapEntry_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_BluetoothDevicesDetails_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_BluetoothDevicesDetails_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_BoolMsg_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_BoolMsg_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_CommandStatus_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_CommandStatus_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_DeviceState_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_DeviceState_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_DoubleMsg_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_DoubleMsg_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_EmulatedDevices_EmulatedDevice_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_EmulatedDevices_EmulatedDevice_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_EmulatedDevices_EmulatedDevicesMapEntry_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_EmulatedDevices_EmulatedDevicesMapEntry_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_EmulatedDevices_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_EmulatedDevices_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_FileByteTransfer_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_FileByteTransfer_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_HelloReply_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_HelloReply_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_HelloRequest_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_HelloRequest_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_Ieee802154Info_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_Ieee802154Info_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_InfoSpans_InfoSpan_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_InfoSpans_InfoSpan_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_InfoSpans_SpanMapEntry_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_InfoSpans_SpanMapEntry_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_InfoSpans_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_InfoSpans_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_ObjectCluster2_DataMapEntry_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_ObjectCluster2_DataMapEntry_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_DataCluster2_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_DataCluster2_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_FormatMapEntry_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_FormatMapEntry_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_ObjectCluster2_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_ObjectCluster2_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_OperationRequest_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_OperationRequest_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_ShimmerRequest_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_ShimmerRequest_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_ShimmersInfo_ShimmerInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_ShimmersInfo_ShimmerInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_ShimmersInfo_ShimmerMapEntry_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_ShimmersInfo_ShimmerMapEntry_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_ShimmersInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_ShimmersInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_StreamRequest_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_StreamRequest_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_StringArrayMsg_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_StringArrayMsg_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_shimmerGRPC_StringMsg_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_shimmerGRPC_StringMsg_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\"src/ShimmerGrpcAndOJC_v1.0.0.proto\u0012\u000bshimmerGRPC\"A\n\u0010FileByteTransfer\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\f\n\u0004data\u0018\u0002 \u0001(\f\u0012\u0011\n\tendoffile\u0018\u0003 \u0001(\b\"\u001f\n\rCommandStatus\u0012\u000e\n\u0006status\u0018\u0001 \u0001(\t\"\u001c\n\fHelloRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"\u001d\n\nHelloReply\u0012\u000f\n\u0007message\u0018\u0001 \u0001(\t\"!\n\u000eShimmerRequest\u0012\u000f\n\u0007address\u0018\u0001 \u0001(\t\" \n\rStreamRequest\u0012\u000f\n\u0007message\u0018\u0001 \u0001(\t\"\u008e\u0005\n\u000eObjectCluster2\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0018\n\u0010bluetoothAddress\u0018\u0002 \u0001(\t\u0012H\n\u0011communicationType\u0018\u0003 \u0001(\u000e2-.shimmerGRPC.ObjectCluster2.CommunicationType\u00129\n\u0007dataMap\u0018\u0004 \u0003(\u000b2(.shimmerGRPC.ObjectCluster2.DataMapEntry\u0012\u0012\n\nsystemTime\u0018\u0005 \u0001(\u0003\u0012\u001b\n\u0013calibratedTimeStamp\u0018\u0006 \u0001(\u0001\u001a\u0088\u0002\n\u000eFormatCluster2\u0012L\n\tformatMap\u0018\u0001 \u0003(\u000b29.shimmerGRPC.ObjectCluster2.FormatCluster2.FormatMapEntry\u001a=\n\fDataCluster2\u0012\f\n\u0004unit\u0018\u0001 \u0001(\t\u0012\f\n\u0004data\u0018\u0002 \u0001(\u0001\u0012\u0011\n\tdataArray\u0018\u0003 \u0003(\u0001\u001ai\n\u000eFormatMapEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012F\n\u0005value\u0018\u0002 \u0001(\u000b27.shimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2:\u00028\u0001\u001aZ\n\fDataMapEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u00129\n\u0005value\u0018\u0002 \u0001(\u000b2*.shimmerGRPC.ObjectCluster2.FormatCluster2:\u00028\u0001\"7\n\u0011CommunicationType\u0012\u0006\n\u0002BT\u0010\u0000\u0012\u0006\n\u0002SD\u0010\u0001\u0012\u0012\n\u000eRadio_802_15_4\u0010\u0002\"\u001c\n\tStringMsg\u0012\u000f\n\u0007message\u0018\u0001 \u0001(\t\"&\n\u000eStringArrayMsg\u0012\u0014\n\fmessageArray\u0018\u0001 \u0003(\t\"\u0018\n\u0007BoolMsg\u0012\r\n\u0005state\u0018\u0001 \u0001(\b\"\u001b\n\tDoubleMsg\u0012\u000e\n\u0006number\u0018\u0001 \u0001(\u0001\"\u0088\u0001\n\u0010OperationRequest\u0012\u0012\n\nisFinished\u0018\u0001 \u0001(\b\u0012\u0011\n\tisSuccess\u0018\u0002 \u0001(\b\u0012\u000f\n\u0007message\u0018\u0003 \u0001(\t\u0012\u001a\n\u0012progressPercentage\u0018\u0004 \u0001(\u0001\u0012 \n\u0018progressPercentageParsed\u0018\u0005 \u0001(\t\"\u0089\u0006\n\fShimmersInfo\u0012\r\n\u0005state\u0018\u0001 \u0001(\b\u0012\u000f\n\u0007message\u0018\u0002 \u0001(\t\u0012=\n\nshimmerMap\u0018\u0003 \u0003(\u000b2).shimmerGRPC.ShimmersInfo.ShimmerMapEntry\u001a¿\u0004\n\u000bShimmerInfo\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0010\n\buniqueId\u0018\u0002 \u0001(\t\u0012\u0018\n\u0010bluetoothAddress\u0018\u0003 \u0001(\t\u0012\u0011\n\ttrialName\u0018\u0004 \u0001(\t\u0012\u001f\n\u0017batteryPercentageParsed\u0018\u0005 \u0001(\t\u0012\u0019\n\u0011batteryPercentage\u0018\u0006 \u0001(\u0001\u0012\u001c\n\u0014chargingStatusParsed\u0018\u0007 \u0001(\t\u0012\u0015\n\rdriveCapacity\u0018\b \u0001(\u0003\u0012\u0016\n\u000edriveSpaceUsed\u0018\t \u0001(\u0003\u0012\u0016\n\u000edriveSpaceFree\u0018\n \u0001(\u0003\u0012\u001b\n\u0013driveCapacityParsed\u0018\u000b \u0001(\t\u0012\u001a\n\u0012isRealTimeClockSet\u0018\f \u0001(\b\u0012!\n\u0019lastReadRtcValueMilliSecs\u0018\r \u0001(\u0003\u0012\u001e\n\u0016lastReadRtcValueParsed\u0018\u000e \u0001(\t\u0012\u0017\n\u000fhwVersionParsed\u0018\u000f \u0001(\t\u0012\u001b\n\u0013expBrdVersionParsed\u0018\u0010 \u0001(\t\u0012\u0017\n\u000ffwVersionParsed\u0018\u0011 \u0001(\t\u0012\u0014\n\fpairedDevice\u0018\u0012 \u0003(\t\u0012\u0012\n\nconfigTime\u0018\u0013 \u0001(\u0003\u0012\u0018\n\u0010configTimeParsed\u0018\u0014 \u0001(\t\u00123\n\u000eieee802154Info\u0018\u0015 \u0001(\u000b2\u001b.shimmerGRPC.Ieee802154Info\u001aX\n\u000fShimmerMapEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u00124\n\u0005value\u0018\u0002 \u0001(\u000b2%.shimmerGRPC.ShimmersInfo.ShimmerInfo:\u00028\u0001\"é\u0002\n\u0017BluetoothDevicesDetails\u0012\r\n\u0005state\u0018\u0001 \u0001(\b\u0012\u000f\n\u0007message\u0018\u0002 \u0001(\t\u0012F\n\tdeviceMap\u0018\u0003 \u0003(\u000b23.shimmerGRPC.BluetoothDevicesDetails.DeviceMapEntry\u001aw\n\u0016BluetoothDeviceDetails\u0012\u000f\n\u0007comPort\u0018\u0001 \u0001(\t\u0012\f\n\u0004name\u0018\u0002 \u0001(\t\u0012\r\n\u0005macId\u0018\u0003 \u0001(\t\u0012\u0012\n\ndeviceType\u0018\u0004 \u0001(\t\u0012\u001b\n\u0013lastConnectionState\u0018\u0005 \u0001(\t\u001am\n\u000eDeviceMapEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012J\n\u0005value\u0018\u0002 \u0001(\u000b2;.shimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetails:\u00028\u0001\"g\n\u000bDeviceState\u0012\u0012\n\ndeviceName\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007comPort\u0018\u0002 \u0001(\t\u0012\r\n\u0005macId\u0018\u0003 \u0001(\t\u0012$\n\u0005state\u0018\u0004 \u0001(\u000e2\u0015.shimmerGRPC.BT_STATE\"S\n\u000eIeee802154Info\u0012\u0014\n\fradioChannel\u0018\u0001 \u0001(\u0005\u0012\u0014\n\fradioGroupId\u0018\u0002 \u0001(\u0005\u0012\u0015\n\rradioDeviceId\u0018\u0003 \u0001(\u0005\"©\u0002\n\tInfoSpans\u0012\r\n\u0005state\u0018\u0001 \u0001(\b\u0012\u000f\n\u0007message\u0018\u0002 \u0001(\t\u00124\n\u0007spanMap\u0018\u0003 \u0003(\u000b2#.shimmerGRPC.InfoSpans.SpanMapEntry\u00123\n\u000eieee802154Info\u0018\u0004 \u0001(\u000b2\u001b.shimmerGRPC.Ieee802154Info\u001a@\n\bInfoSpan\u0012\u0010\n\buniqueId\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007portBsl\u0018\u0002 \u0001(\t\u0012\u0011\n\tportComms\u0018\u0003 \u0001(\t\u001aO\n\fSpanMapEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012.\n\u0005value\u0018\u0002 \u0001(\u000b2\u001f.shimmerGRPC.InfoSpans.InfoSpan:\u00028\u0001\"ú\u0002\n\u000fEmulatedDevices\u0012\r\n\u0005state\u0018\u0001 \u0001(\b\u0012\u000f\n\u0007message\u0018\u0002 \u0001(\t\u0012\u0016\n\u000eisEmulatorSide\u0018\u0003 \u0001(\b\u0012P\n\u0012emulatedDevicesMap\u0018\u0004 \u0003(\u000b24.shimmerGRPC.EmulatedDevices.EmulatedDevicesMapEntry\u001au\n\u000eEmulatedDevice\u0012\u0010\n\buniqueId\u0018\u0001 \u0001(\t\u0012\u0019\n\u0011deviceTypeOrdinal\u0018\u0002 \u0001(\u0005\u0012\u001d\n\u0015hwDeviceInterfacePath\u0018\u0003 \u0003(\t\u0012\u0017\n\u000fisDeviceEnabled\u0018\u0004 \u0001(\b\u001af\n\u0017EmulatedDevicesMapEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012:\n\u0005value\u0018\u0002 \u0001(\u000b2+.shimmerGRPC.EmulatedDevices.EmulatedDevice:\u00028\u0001*³\u0001\n\bBT_STATE\u0012\u0010\n\fDISCONNECTED\u0010\u0000\u0012\u000e\n\nCONNECTING\u0010\u0001\u0012\r\n\tCONNECTED\u0010\u0002\u0012\r\n\tSTREAMING\u0010\u0003\u0012\u001b\n\u0017STREAMING_AND_SDLOGGING\u0010\u0004\u0012\r\n\tSDLOGGING\u0010\u0005\u0012\u000f\n\u000bCONFIGURING\u0010\u0006\u0012\u0013\n\u000fCONNECTION_LOST\u0010\u0007\u0012\u0015\n\u0011CONNECTION_FAILED\u0010\b2\u0094\u0010\n\rShimmerServer\u0012@\n\bSayHello\u0012\u0019.shimmerGRPC.HelloRequest\u001a\u0017.shimmerGRPC.HelloReply\"\u0000\u0012L\n\rGetDataStream\u0012\u001a.shimmerGRPC.StreamRequest\u001a\u001b.shimmerGRPC.ObjectCluster2\"\u00000\u0001\u0012J\n\u000eSendDataStream\u0012\u001b.shimmerGRPC.ObjectCluster2\u001a\u0017.shimmerGRPC.HelloReply\"\u0000(\u0001\u0012L\n\u000eSendFileStream\u0012\u001d.shimmerGRPC.FileByteTransfer\u001a\u0017.shimmerGRPC.HelloReply\"\u0000(\u0001\u0012K\n\u000eConnectShimmer\u0012\u001b.shimmerGRPC.ShimmerRequest\u001a\u001a.shimmerGRPC.CommandStatus\"\u0000\u0012N\n\u0011DisconnectShimmer\u0012\u001b.shimmerGRPC.ShimmerRequest\u001a\u001a.shimmerGRPC.CommandStatus\"\u0000\u0012K\n\u000eStartStreaming\u0012\u001b.shimmerGRPC.ShimmerRequest\u001a\u001a.shimmerGRPC.CommandStatus\"\u0000\u0012J\n\rStopStreaming\u0012\u001b.shimmerGRPC.ShimmerRequest\u001a\u001a.shimmerGRPC.CommandStatus\"\u0000\u0012M\n\u0010CloseApplication\u0012\u001b.shimmerGRPC.ShimmerRequest\u001a\u001a.shimmerGRPC.CommandStatus\"\u0000\u0012P\n\u0015SetWorkspaceDirectory\u0012\u0016.shimmerGRPC.StringMsg\u001a\u001d.shimmerGRPC.OperationRequest\"\u0000\u0012I\n\u0015GetWorkspaceDirectory\u0012\u0016.shimmerGRPC.StringMsg\u001a\u0016.shimmerGRPC.StringMsg\"\u0000\u0012K\n\u0014GetDockedShimmerInfo\u0012\u0016.shimmerGRPC.StringMsg\u001a\u0019.shimmerGRPC.ShimmersInfo\"\u0000\u0012I\n\u0015GetMadgewickBetaValue\u0012\u0016.shimmerGRPC.StringMsg\u001a\u0016.shimmerGRPC.DoubleMsg\"\u0000\u0012L\n\fPairShimmers\u0012\u001b.shimmerGRPC.StringArrayMsg\u001a\u001d.shimmerGRPC.OperationRequest\"\u0000\u0012O\n\u0014GetOperationProgress\u0012\u0016.shimmerGRPC.StringMsg\u001a\u001d.shimmerGRPC.OperationRequest\"\u0000\u0012X\n\u0018ImportSdDataFromShimmers\u0012\u001b.shimmerGRPC.StringArrayMsg\u001a\u001d.shimmerGRPC.OperationRequest\"\u0000\u0012N\n\u0013ParseSdDataFromPath\u0012\u0016.shimmerGRPC.StringMsg\u001a\u001d.shimmerGRPC.OperationRequest\"\u0000\u0012Q\n\u0011ScanSdDataAndCopy\u0012\u001b.shimmerGRPC.StringArrayMsg\u001a\u001d.shimmerGRPC.OperationRequest\"\u0000\u0012O\n\u000fClearSdCardData\u0012\u001b.shimmerGRPC.StringArrayMsg\u001a\u001d.shimmerGRPC.OperationRequest\"\u0000\u0012X\n\u0018DockAccessSlotWithSdCard\u0012\u001b.shimmerGRPC.StringArrayMsg\u001a\u001d.shimmerGRPC.OperationRequest\"\u0000\u0012T\n\u0014DockRestoreAutoTasks\u0012\u001b.shimmerGRPC.StringArrayMsg\u001a\u001d.shimmerGRPC.OperationRequest\"\u0000\u0012@\n\fGetInfoSpans\u0012\u0016.shimmerGRPC.StringMsg\u001a\u0016.shimmerGRPC.InfoSpans\"\u0000\u0012I\n\u0012GetInfoAllShimmers\u0012\u0016.shimmerGRPC.StringMsg\u001a\u0019.shimmerGRPC.ShimmersInfo\"\u0000\u0012L\n\u0012GetEmulatedDevices\u0012\u0016.shimmerGRPC.StringMsg\u001a\u001c.shimmerGRPC.EmulatedDevices\"\u0000\u0012U\n\u0013GetPairedBtShimmers\u0012\u0016.shimmerGRPC.StringMsg\u001a$.shimmerGRPC.BluetoothDevicesDetails\"\u0000\u0012P\n\u0014GetDeviceStateStream\u0012\u001a.shimmerGRPC.StreamRequest\u001a\u0018.shimmerGRPC.DeviceState\"\u00000\u0001BB\n\u0018com.shimmerresearch.grpcB\u000bShimmerGRPCª\u0002\u0018com.shimmerresearch.grpcb\u0006proto3"}, new Descriptors.FileDescriptor[0]);

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_shimmerGRPC_FileByteTransfer_descriptor = descriptor2;
        internal_static_shimmerGRPC_FileByteTransfer_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Name", "Data", "Endoffile"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_shimmerGRPC_CommandStatus_descriptor = descriptor3;
        internal_static_shimmerGRPC_CommandStatus_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Status"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_shimmerGRPC_HelloRequest_descriptor = descriptor4;
        internal_static_shimmerGRPC_HelloRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Name"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_shimmerGRPC_HelloReply_descriptor = descriptor5;
        internal_static_shimmerGRPC_HelloReply_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Message"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(4);
        internal_static_shimmerGRPC_ShimmerRequest_descriptor = descriptor6;
        internal_static_shimmerGRPC_ShimmerRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"Address"});
        Descriptors.Descriptor descriptor7 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(5);
        internal_static_shimmerGRPC_StreamRequest_descriptor = descriptor7;
        internal_static_shimmerGRPC_StreamRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"Message"});
        Descriptors.Descriptor descriptor8 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(6);
        internal_static_shimmerGRPC_ObjectCluster2_descriptor = descriptor8;
        internal_static_shimmerGRPC_ObjectCluster2_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"Name", "BluetoothAddress", "CommunicationType", "DataMap", "SystemTime", "CalibratedTimeStamp"});
        Descriptors.Descriptor descriptor9 = (Descriptors.Descriptor) descriptor8.getNestedTypes().get(0);
        internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_descriptor = descriptor9;
        internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"FormatMap"});
        Descriptors.Descriptor descriptor10 = (Descriptors.Descriptor) descriptor9.getNestedTypes().get(0);
        internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_DataCluster2_descriptor = descriptor10;
        internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_DataCluster2_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"Unit", "Data", "DataArray"});
        Descriptors.Descriptor descriptor11 = (Descriptors.Descriptor) descriptor9.getNestedTypes().get(1);
        internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_FormatMapEntry_descriptor = descriptor11;
        internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_FormatMapEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor11, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor12 = (Descriptors.Descriptor) descriptor8.getNestedTypes().get(1);
        internal_static_shimmerGRPC_ObjectCluster2_DataMapEntry_descriptor = descriptor12;
        internal_static_shimmerGRPC_ObjectCluster2_DataMapEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor12, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor13 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(7);
        internal_static_shimmerGRPC_StringMsg_descriptor = descriptor13;
        internal_static_shimmerGRPC_StringMsg_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor13, new String[]{"Message"});
        Descriptors.Descriptor descriptor14 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(8);
        internal_static_shimmerGRPC_StringArrayMsg_descriptor = descriptor14;
        internal_static_shimmerGRPC_StringArrayMsg_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor14, new String[]{"MessageArray"});
        Descriptors.Descriptor descriptor15 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(9);
        internal_static_shimmerGRPC_BoolMsg_descriptor = descriptor15;
        internal_static_shimmerGRPC_BoolMsg_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor15, new String[]{"State"});
        Descriptors.Descriptor descriptor16 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(10);
        internal_static_shimmerGRPC_DoubleMsg_descriptor = descriptor16;
        internal_static_shimmerGRPC_DoubleMsg_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor16, new String[]{"Number"});
        Descriptors.Descriptor descriptor17 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(11);
        internal_static_shimmerGRPC_OperationRequest_descriptor = descriptor17;
        internal_static_shimmerGRPC_OperationRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor17, new String[]{"IsFinished", "IsSuccess", "Message", "ProgressPercentage", "ProgressPercentageParsed"});
        Descriptors.Descriptor descriptor18 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(12);
        internal_static_shimmerGRPC_ShimmersInfo_descriptor = descriptor18;
        internal_static_shimmerGRPC_ShimmersInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor18, new String[]{"State", "Message", "ShimmerMap"});
        Descriptors.Descriptor descriptor19 = (Descriptors.Descriptor) descriptor18.getNestedTypes().get(0);
        internal_static_shimmerGRPC_ShimmersInfo_ShimmerInfo_descriptor = descriptor19;
        internal_static_shimmerGRPC_ShimmersInfo_ShimmerInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor19, new String[]{"Name", "UniqueId", "BluetoothAddress", "TrialName", "BatteryPercentageParsed", "BatteryPercentage", "ChargingStatusParsed", "DriveCapacity", "DriveSpaceUsed", "DriveSpaceFree", "DriveCapacityParsed", "IsRealTimeClockSet", "LastReadRtcValueMilliSecs", "LastReadRtcValueParsed", "HwVersionParsed", "ExpBrdVersionParsed", "FwVersionParsed", "PairedDevice", "ConfigTime", "ConfigTimeParsed", "Ieee802154Info"});
        Descriptors.Descriptor descriptor20 = (Descriptors.Descriptor) descriptor18.getNestedTypes().get(1);
        internal_static_shimmerGRPC_ShimmersInfo_ShimmerMapEntry_descriptor = descriptor20;
        internal_static_shimmerGRPC_ShimmersInfo_ShimmerMapEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor20, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor21 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(13);
        internal_static_shimmerGRPC_BluetoothDevicesDetails_descriptor = descriptor21;
        internal_static_shimmerGRPC_BluetoothDevicesDetails_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor21, new String[]{"State", "Message", "DeviceMap"});
        Descriptors.Descriptor descriptor22 = (Descriptors.Descriptor) descriptor21.getNestedTypes().get(0);
        internal_static_shimmerGRPC_BluetoothDevicesDetails_BluetoothDeviceDetails_descriptor = descriptor22;
        internal_static_shimmerGRPC_BluetoothDevicesDetails_BluetoothDeviceDetails_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor22, new String[]{"ComPort", "Name", "MacId", "DeviceType", "LastConnectionState"});
        Descriptors.Descriptor descriptor23 = (Descriptors.Descriptor) descriptor21.getNestedTypes().get(1);
        internal_static_shimmerGRPC_BluetoothDevicesDetails_DeviceMapEntry_descriptor = descriptor23;
        internal_static_shimmerGRPC_BluetoothDevicesDetails_DeviceMapEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor23, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor24 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(14);
        internal_static_shimmerGRPC_DeviceState_descriptor = descriptor24;
        internal_static_shimmerGRPC_DeviceState_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor24, new String[]{"DeviceName", "ComPort", "MacId", "State"});
        Descriptors.Descriptor descriptor25 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(15);
        internal_static_shimmerGRPC_Ieee802154Info_descriptor = descriptor25;
        internal_static_shimmerGRPC_Ieee802154Info_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor25, new String[]{"RadioChannel", "RadioGroupId", "RadioDeviceId"});
        Descriptors.Descriptor descriptor26 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(16);
        internal_static_shimmerGRPC_InfoSpans_descriptor = descriptor26;
        internal_static_shimmerGRPC_InfoSpans_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor26, new String[]{"State", "Message", "SpanMap", "Ieee802154Info"});
        Descriptors.Descriptor descriptor27 = (Descriptors.Descriptor) descriptor26.getNestedTypes().get(0);
        internal_static_shimmerGRPC_InfoSpans_InfoSpan_descriptor = descriptor27;
        internal_static_shimmerGRPC_InfoSpans_InfoSpan_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor27, new String[]{"UniqueId", "PortBsl", "PortComms"});
        Descriptors.Descriptor descriptor28 = (Descriptors.Descriptor) descriptor26.getNestedTypes().get(1);
        internal_static_shimmerGRPC_InfoSpans_SpanMapEntry_descriptor = descriptor28;
        internal_static_shimmerGRPC_InfoSpans_SpanMapEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor28, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor29 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(17);
        internal_static_shimmerGRPC_EmulatedDevices_descriptor = descriptor29;
        internal_static_shimmerGRPC_EmulatedDevices_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor29, new String[]{"State", "Message", "IsEmulatorSide", "EmulatedDevicesMap"});
        Descriptors.Descriptor descriptor30 = (Descriptors.Descriptor) descriptor29.getNestedTypes().get(0);
        internal_static_shimmerGRPC_EmulatedDevices_EmulatedDevice_descriptor = descriptor30;
        internal_static_shimmerGRPC_EmulatedDevices_EmulatedDevice_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor30, new String[]{"UniqueId", "DeviceTypeOrdinal", "HwDeviceInterfacePath", "IsDeviceEnabled"});
        Descriptors.Descriptor descriptor31 = (Descriptors.Descriptor) descriptor29.getNestedTypes().get(1);
        internal_static_shimmerGRPC_EmulatedDevices_EmulatedDevicesMapEntry_descriptor = descriptor31;
        internal_static_shimmerGRPC_EmulatedDevices_EmulatedDevicesMapEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor31, new String[]{"Key", "Value"});
    }

    private ShimmerGRPC() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public enum BT_STATE implements ProtocolMessageEnum {
        DISCONNECTED(0),
        CONNECTING(1),
        CONNECTED(2),
        STREAMING(3),
        STREAMING_AND_SDLOGGING(4),
        SDLOGGING(5),
        CONFIGURING(6),
        CONNECTION_LOST(7),
        CONNECTION_FAILED(8),
        UNRECOGNIZED(-1);

        public static final int CONFIGURING_VALUE = 6;
        public static final int CONNECTED_VALUE = 2;
        public static final int CONNECTING_VALUE = 1;
        public static final int CONNECTION_FAILED_VALUE = 8;
        public static final int CONNECTION_LOST_VALUE = 7;
        public static final int DISCONNECTED_VALUE = 0;
        public static final int SDLOGGING_VALUE = 5;
        public static final int STREAMING_AND_SDLOGGING_VALUE = 4;
        public static final int STREAMING_VALUE = 3;
        private static final Internal.EnumLiteMap<BT_STATE> internalValueMap = new Internal.EnumLiteMap<BT_STATE>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.BT_STATE.1
            public BT_STATE findValueByNumber(int i) {
                return BT_STATE.forNumber(i);
            }
        };
        private static final BT_STATE[] VALUES = values();
        private final int value;

        BT_STATE(int i) {
            this.value = i;
        }

        public static BT_STATE forNumber(int i) {
            switch (i) {
                case 0:
                    return DISCONNECTED;
                case 1:
                    return CONNECTING;
                case 2:
                    return CONNECTED;
                case 3:
                    return STREAMING;
                case 4:
                    return STREAMING_AND_SDLOGGING;
                case 5:
                    return SDLOGGING;
                case 6:
                    return CONFIGURING;
                case 7:
                    return CONNECTION_LOST;
                case 8:
                    return CONNECTION_FAILED;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<BT_STATE> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static BT_STATE valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) ShimmerGRPC.getDescriptor().getEnumTypes().get(0);
        }

        public static BT_STATE valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
    }

    public interface BluetoothDevicesDetailsOrBuilder extends MessageOrBuilder {
        boolean containsDeviceMap(String str);

        @Deprecated
        Map<String, BluetoothDevicesDetails.BluetoothDeviceDetails> getDeviceMap();

        int getDeviceMapCount();

        Map<String, BluetoothDevicesDetails.BluetoothDeviceDetails> getDeviceMapMap();

        BluetoothDevicesDetails.BluetoothDeviceDetails getDeviceMapOrDefault(String str, BluetoothDevicesDetails.BluetoothDeviceDetails bluetoothDeviceDetails);

        BluetoothDevicesDetails.BluetoothDeviceDetails getDeviceMapOrThrow(String str);

        String getMessage();

        ByteString getMessageBytes();

        boolean getState();
    }

    public interface BoolMsgOrBuilder extends MessageOrBuilder {
        boolean getState();
    }

    public interface CommandStatusOrBuilder extends MessageOrBuilder {
        String getStatus();

        ByteString getStatusBytes();
    }

    public interface DeviceStateOrBuilder extends MessageOrBuilder {
        String getComPort();

        ByteString getComPortBytes();

        String getDeviceName();

        ByteString getDeviceNameBytes();

        String getMacId();

        ByteString getMacIdBytes();

        BT_STATE getState();

        int getStateValue();
    }

    public interface DoubleMsgOrBuilder extends MessageOrBuilder {
        double getNumber();
    }

    public interface EmulatedDevicesOrBuilder extends MessageOrBuilder {
        boolean containsEmulatedDevicesMap(String str);

        @Deprecated
        Map<String, EmulatedDevices.EmulatedDevice> getEmulatedDevicesMap();

        int getEmulatedDevicesMapCount();

        Map<String, EmulatedDevices.EmulatedDevice> getEmulatedDevicesMapMap();

        EmulatedDevices.EmulatedDevice getEmulatedDevicesMapOrDefault(String str, EmulatedDevices.EmulatedDevice emulatedDevice);

        EmulatedDevices.EmulatedDevice getEmulatedDevicesMapOrThrow(String str);

        boolean getIsEmulatorSide();

        String getMessage();

        ByteString getMessageBytes();

        boolean getState();
    }

    public interface FileByteTransferOrBuilder extends MessageOrBuilder {
        ByteString getData();

        boolean getEndoffile();

        String getName();

        ByteString getNameBytes();
    }

    public interface HelloReplyOrBuilder extends MessageOrBuilder {
        String getMessage();

        ByteString getMessageBytes();
    }

    public interface HelloRequestOrBuilder extends MessageOrBuilder {
        String getName();

        ByteString getNameBytes();
    }

    public interface Ieee802154InfoOrBuilder extends MessageOrBuilder {
        int getRadioChannel();

        int getRadioDeviceId();

        int getRadioGroupId();
    }

    public interface InfoSpansOrBuilder extends MessageOrBuilder {
        boolean containsSpanMap(String str);

        Ieee802154Info getIeee802154Info();

        Ieee802154InfoOrBuilder getIeee802154InfoOrBuilder();

        String getMessage();

        ByteString getMessageBytes();

        @Deprecated
        Map<String, InfoSpans.InfoSpan> getSpanMap();

        int getSpanMapCount();

        Map<String, InfoSpans.InfoSpan> getSpanMapMap();

        InfoSpans.InfoSpan getSpanMapOrDefault(String str, InfoSpans.InfoSpan infoSpan);

        InfoSpans.InfoSpan getSpanMapOrThrow(String str);

        boolean getState();

        boolean hasIeee802154Info();
    }

    public interface ObjectCluster2OrBuilder extends MessageOrBuilder {
        boolean containsDataMap(String str);

        String getBluetoothAddress();

        ByteString getBluetoothAddressBytes();

        double getCalibratedTimeStamp();

        ObjectCluster2.CommunicationType getCommunicationType();

        int getCommunicationTypeValue();

        @Deprecated
        Map<String, ObjectCluster2.FormatCluster2> getDataMap();

        int getDataMapCount();

        Map<String, ObjectCluster2.FormatCluster2> getDataMapMap();

        ObjectCluster2.FormatCluster2 getDataMapOrDefault(String str, ObjectCluster2.FormatCluster2 formatCluster2);

        ObjectCluster2.FormatCluster2 getDataMapOrThrow(String str);

        String getName();

        ByteString getNameBytes();

        long getSystemTime();
    }

    public interface OperationRequestOrBuilder extends MessageOrBuilder {
        boolean getIsFinished();

        boolean getIsSuccess();

        String getMessage();

        ByteString getMessageBytes();

        double getProgressPercentage();

        String getProgressPercentageParsed();

        ByteString getProgressPercentageParsedBytes();
    }

    public interface ShimmerRequestOrBuilder extends MessageOrBuilder {
        String getAddress();

        ByteString getAddressBytes();
    }

    public interface ShimmersInfoOrBuilder extends MessageOrBuilder {
        boolean containsShimmerMap(String str);

        String getMessage();

        ByteString getMessageBytes();

        @Deprecated
        Map<String, ShimmersInfo.ShimmerInfo> getShimmerMap();

        int getShimmerMapCount();

        Map<String, ShimmersInfo.ShimmerInfo> getShimmerMapMap();

        ShimmersInfo.ShimmerInfo getShimmerMapOrDefault(String str, ShimmersInfo.ShimmerInfo shimmerInfo);

        ShimmersInfo.ShimmerInfo getShimmerMapOrThrow(String str);

        boolean getState();
    }

    public interface StreamRequestOrBuilder extends MessageOrBuilder {
        String getMessage();

        ByteString getMessageBytes();
    }

    public interface StringArrayMsgOrBuilder extends MessageOrBuilder {
        String getMessageArray(int i);

        ByteString getMessageArrayBytes(int i);

        int getMessageArrayCount();

        /* renamed from: getMessageArrayList */
        List<String> mo6135getMessageArrayList();
    }

    public interface StringMsgOrBuilder extends MessageOrBuilder {
        String getMessage();

        ByteString getMessageBytes();
    }

    public static final class FileByteTransfer extends GeneratedMessageV3 implements FileByteTransferOrBuilder {
        public static final int DATA_FIELD_NUMBER = 2;
        public static final int ENDOFFILE_FIELD_NUMBER = 3;
        public static final int NAME_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final FileByteTransfer DEFAULT_INSTANCE = new FileByteTransfer();
        private static final Parser<FileByteTransfer> PARSER = new AbstractParser<FileByteTransfer>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransfer.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public FileByteTransfer m5388parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new FileByteTransfer(codedInputStream, extensionRegistryLite);
            }
        };
        private ByteString data_;
        private boolean endoffile_;
        private byte memoizedIsInitialized;
        private volatile Object name_;

        private FileByteTransfer(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private FileByteTransfer() {
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
            this.data_ = ByteString.EMPTY;
        }

        private FileByteTransfer(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.data_ = codedInputStream.readBytes();
                            } else if (tag == 24) {
                                this.endoffile_ = codedInputStream.readBool();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static FileByteTransfer getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FileByteTransfer> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_FileByteTransfer_descriptor;
        }

        public static FileByteTransfer parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FileByteTransfer) PARSER.parseFrom(byteBuffer);
        }

        public static FileByteTransfer parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FileByteTransfer) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static FileByteTransfer parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FileByteTransfer) PARSER.parseFrom(byteString);
        }

        public static FileByteTransfer parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FileByteTransfer) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static FileByteTransfer parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FileByteTransfer) PARSER.parseFrom(bArr);
        }

        public static FileByteTransfer parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FileByteTransfer) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static FileByteTransfer parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static FileByteTransfer parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FileByteTransfer parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static FileByteTransfer parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FileByteTransfer parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static FileByteTransfer parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m5386toBuilder();
        }

        public static Builder newBuilder(FileByteTransfer fileByteTransfer) {
            return DEFAULT_INSTANCE.m5386toBuilder().mergeFrom(fileByteTransfer);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransferOrBuilder
        public ByteString getData() {
            return this.data_;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FileByteTransfer m5381getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransferOrBuilder
        public boolean getEndoffile() {
            return this.endoffile_;
        }

        public Parser<FileByteTransfer> getParserForType() {
            return PARSER;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new FileByteTransfer();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_FileByteTransfer_fieldAccessorTable.ensureFieldAccessorsInitialized(FileByteTransfer.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransferOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransferOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
            }
            if (!this.data_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.data_);
            }
            boolean z = this.endoffile_;
            if (z) {
                codedOutputStream.writeBool(3, z);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
            if (!this.data_.isEmpty()) {
                iComputeStringSize += CodedOutputStream.computeBytesSize(2, this.data_);
            }
            boolean z = this.endoffile_;
            if (z) {
                iComputeStringSize += CodedOutputStream.computeBoolSize(3, z);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FileByteTransfer)) {
                return super.equals(obj);
            }
            FileByteTransfer fileByteTransfer = (FileByteTransfer) obj;
            return getName().equals(fileByteTransfer.getName()) && getData().equals(fileByteTransfer.getData()) && getEndoffile() == fileByteTransfer.getEndoffile() && this.unknownFields.equals(fileByteTransfer.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getData().hashCode()) * 37) + 3) * 53) + Internal.hashBoolean(getEndoffile())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5383newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5386toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FileByteTransferOrBuilder {
            private ByteString data_;
            private boolean endoffile_;
            private Object name_;

            private Builder() {
                this.name_ = "";
                this.data_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.data_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_FileByteTransfer_descriptor;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransferOrBuilder
            public ByteString getData() {
                return this.data_;
            }

            public Builder setData(ByteString byteString) {
                byteString.getClass();
                this.data_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransferOrBuilder
            public boolean getEndoffile() {
                return this.endoffile_;
            }

            public Builder setEndoffile(boolean z) {
                this.endoffile_ = z;
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_FileByteTransfer_fieldAccessorTable.ensureFieldAccessorsInitialized(FileByteTransfer.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = FileByteTransfer.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5397clear() {
                super.clear();
                this.name_ = "";
                this.data_ = ByteString.EMPTY;
                this.endoffile_ = false;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_FileByteTransfer_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public FileByteTransfer m5410getDefaultInstanceForType() {
                return FileByteTransfer.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public FileByteTransfer m5391build() throws UninitializedMessageException {
                FileByteTransfer fileByteTransferM5393buildPartial = m5393buildPartial();
                if (fileByteTransferM5393buildPartial.isInitialized()) {
                    return fileByteTransferM5393buildPartial;
                }
                throw newUninitializedMessageException(fileByteTransferM5393buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public FileByteTransfer m5393buildPartial() {
                FileByteTransfer fileByteTransfer = new FileByteTransfer(this);
                fileByteTransfer.name_ = this.name_;
                fileByteTransfer.data_ = this.data_;
                fileByteTransfer.endoffile_ = this.endoffile_;
                onBuilt();
                return fileByteTransfer;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5409clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5421setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5399clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5402clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5423setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5389addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5414mergeFrom(Message message) {
                if (message instanceof FileByteTransfer) {
                    return mergeFrom((FileByteTransfer) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(FileByteTransfer fileByteTransfer) {
                if (fileByteTransfer == FileByteTransfer.getDefaultInstance()) {
                    return this;
                }
                if (!fileByteTransfer.getName().isEmpty()) {
                    this.name_ = fileByteTransfer.name_;
                    onChanged();
                }
                if (fileByteTransfer.getData() != ByteString.EMPTY) {
                    setData(fileByteTransfer.getData());
                }
                if (fileByteTransfer.getEndoffile()) {
                    setEndoffile(fileByteTransfer.getEndoffile());
                }
                m5419mergeUnknownFields(fileByteTransfer.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransfer.Builder m5415mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransfer.m5380$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$FileByteTransfer r3 = (com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransfer) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$FileByteTransfer r4 = (com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransfer) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransfer.Builder.m5415mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$FileByteTransfer$Builder");
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransferOrBuilder
            public String getName() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.name_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setName(String str) {
                str.getClass();
                this.name_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransferOrBuilder
            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.name_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setNameBytes(ByteString byteString) {
                byteString.getClass();
                FileByteTransfer.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = FileByteTransfer.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder clearData() {
                this.data_ = FileByteTransfer.getDefaultInstance().getData();
                onChanged();
                return this;
            }

            public Builder clearEndoffile() {
                this.endoffile_ = false;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5425setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5419mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class CommandStatus extends GeneratedMessageV3 implements CommandStatusOrBuilder {
        public static final int STATUS_FIELD_NUMBER = 1;
        private static final CommandStatus DEFAULT_INSTANCE = new CommandStatus();
        private static final Parser<CommandStatus> PARSER = new AbstractParser<CommandStatus>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.CommandStatus.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public CommandStatus m5126parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CommandStatus(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private volatile Object status_;

        private CommandStatus(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CommandStatus() {
            this.memoizedIsInitialized = (byte) -1;
            this.status_ = "";
        }

        private CommandStatus(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.status_ = codedInputStream.readStringRequireUtf8();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static CommandStatus getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CommandStatus> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_CommandStatus_descriptor;
        }

        public static CommandStatus parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CommandStatus) PARSER.parseFrom(byteBuffer);
        }

        public static CommandStatus parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CommandStatus) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CommandStatus parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CommandStatus) PARSER.parseFrom(byteString);
        }

        public static CommandStatus parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CommandStatus) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CommandStatus parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CommandStatus) PARSER.parseFrom(bArr);
        }

        public static CommandStatus parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CommandStatus) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CommandStatus parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CommandStatus parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CommandStatus parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CommandStatus parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CommandStatus parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CommandStatus parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m5124toBuilder();
        }

        public static Builder newBuilder(CommandStatus commandStatus) {
            return DEFAULT_INSTANCE.m5124toBuilder().mergeFrom(commandStatus);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CommandStatus m5119getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<CommandStatus> getParserForType() {
            return PARSER;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new CommandStatus();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_CommandStatus_fieldAccessorTable.ensureFieldAccessorsInitialized(CommandStatus.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.CommandStatusOrBuilder
        public String getStatus() {
            Object obj = this.status_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.status_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.CommandStatusOrBuilder
        public ByteString getStatusBytes() {
            Object obj = this.status_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.status_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getStatusBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.status_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (!getStatusBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.status_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeStringSize;
            return iComputeStringSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CommandStatus)) {
                return super.equals(obj);
            }
            CommandStatus commandStatus = (CommandStatus) obj;
            return getStatus().equals(commandStatus.getStatus()) && this.unknownFields.equals(commandStatus.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getStatus().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5121newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5124toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CommandStatusOrBuilder {
            private Object status_;

            private Builder() {
                this.status_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.status_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_CommandStatus_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_CommandStatus_fieldAccessorTable.ensureFieldAccessorsInitialized(CommandStatus.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CommandStatus.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5135clear() {
                super.clear();
                this.status_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_CommandStatus_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CommandStatus m5148getDefaultInstanceForType() {
                return CommandStatus.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CommandStatus m5129build() throws UninitializedMessageException {
                CommandStatus commandStatusM5131buildPartial = m5131buildPartial();
                if (commandStatusM5131buildPartial.isInitialized()) {
                    return commandStatusM5131buildPartial;
                }
                throw newUninitializedMessageException(commandStatusM5131buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CommandStatus m5131buildPartial() {
                CommandStatus commandStatus = new CommandStatus(this);
                commandStatus.status_ = this.status_;
                onBuilt();
                return commandStatus;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5147clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5159setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5137clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5140clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5161setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5127addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5152mergeFrom(Message message) {
                if (message instanceof CommandStatus) {
                    return mergeFrom((CommandStatus) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CommandStatus commandStatus) {
                if (commandStatus == CommandStatus.getDefaultInstance()) {
                    return this;
                }
                if (!commandStatus.getStatus().isEmpty()) {
                    this.status_ = commandStatus.status_;
                    onChanged();
                }
                m5157mergeUnknownFields(commandStatus.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.CommandStatus.Builder m5153mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.CommandStatus.m5118$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$CommandStatus r3 = (com.shimmerresearch.grpc.ShimmerGRPC.CommandStatus) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$CommandStatus r4 = (com.shimmerresearch.grpc.ShimmerGRPC.CommandStatus) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.CommandStatus.Builder.m5153mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$CommandStatus$Builder");
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.CommandStatusOrBuilder
            public String getStatus() {
                Object obj = this.status_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.status_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setStatus(String str) {
                str.getClass();
                this.status_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.CommandStatusOrBuilder
            public ByteString getStatusBytes() {
                Object obj = this.status_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.status_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setStatusBytes(ByteString byteString) {
                byteString.getClass();
                CommandStatus.checkByteStringIsUtf8(byteString);
                this.status_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearStatus() {
                this.status_ = CommandStatus.getDefaultInstance().getStatus();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5163setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5157mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class HelloRequest extends GeneratedMessageV3 implements HelloRequestOrBuilder {
        public static final int NAME_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final HelloRequest DEFAULT_INSTANCE = new HelloRequest();
        private static final Parser<HelloRequest> PARSER = new AbstractParser<HelloRequest>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.HelloRequest.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public HelloRequest m5486parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new HelloRequest(codedInputStream, extensionRegistryLite);
            }
        };
        private byte memoizedIsInitialized;
        private volatile Object name_;

        private HelloRequest(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private HelloRequest() {
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
        }

        private HelloRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static HelloRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<HelloRequest> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_HelloRequest_descriptor;
        }

        public static HelloRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (HelloRequest) PARSER.parseFrom(byteBuffer);
        }

        public static HelloRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HelloRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static HelloRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (HelloRequest) PARSER.parseFrom(byteString);
        }

        public static HelloRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HelloRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static HelloRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (HelloRequest) PARSER.parseFrom(bArr);
        }

        public static HelloRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HelloRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static HelloRequest parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static HelloRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static HelloRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static HelloRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static HelloRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static HelloRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m5484toBuilder();
        }

        public static Builder newBuilder(HelloRequest helloRequest) {
            return DEFAULT_INSTANCE.m5484toBuilder().mergeFrom(helloRequest);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HelloRequest m5479getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<HelloRequest> getParserForType() {
            return PARSER;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new HelloRequest();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_HelloRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(HelloRequest.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.HelloRequestOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.HelloRequestOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (!getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeStringSize;
            return iComputeStringSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof HelloRequest)) {
                return super.equals(obj);
            }
            HelloRequest helloRequest = (HelloRequest) obj;
            return getName().equals(helloRequest.getName()) && this.unknownFields.equals(helloRequest.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5481newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5484toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HelloRequestOrBuilder {
            private Object name_;

            private Builder() {
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_HelloRequest_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_HelloRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(HelloRequest.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = HelloRequest.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5495clear() {
                super.clear();
                this.name_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_HelloRequest_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HelloRequest m5508getDefaultInstanceForType() {
                return HelloRequest.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HelloRequest m5489build() throws UninitializedMessageException {
                HelloRequest helloRequestM5491buildPartial = m5491buildPartial();
                if (helloRequestM5491buildPartial.isInitialized()) {
                    return helloRequestM5491buildPartial;
                }
                throw newUninitializedMessageException(helloRequestM5491buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HelloRequest m5491buildPartial() {
                HelloRequest helloRequest = new HelloRequest(this);
                helloRequest.name_ = this.name_;
                onBuilt();
                return helloRequest;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5507clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5519setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5497clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5500clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5521setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5487addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5512mergeFrom(Message message) {
                if (message instanceof HelloRequest) {
                    return mergeFrom((HelloRequest) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(HelloRequest helloRequest) {
                if (helloRequest == HelloRequest.getDefaultInstance()) {
                    return this;
                }
                if (!helloRequest.getName().isEmpty()) {
                    this.name_ = helloRequest.name_;
                    onChanged();
                }
                m5517mergeUnknownFields(helloRequest.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.HelloRequest.Builder m5513mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.HelloRequest.m5478$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$HelloRequest r3 = (com.shimmerresearch.grpc.ShimmerGRPC.HelloRequest) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$HelloRequest r4 = (com.shimmerresearch.grpc.ShimmerGRPC.HelloRequest) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.HelloRequest.Builder.m5513mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$HelloRequest$Builder");
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.HelloRequestOrBuilder
            public String getName() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.name_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setName(String str) {
                str.getClass();
                this.name_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.HelloRequestOrBuilder
            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.name_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setNameBytes(ByteString byteString) {
                byteString.getClass();
                HelloRequest.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = HelloRequest.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5523setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5517mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class HelloReply extends GeneratedMessageV3 implements HelloReplyOrBuilder {
        public static final int MESSAGE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final HelloReply DEFAULT_INSTANCE = new HelloReply();
        private static final Parser<HelloReply> PARSER = new AbstractParser<HelloReply>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.HelloReply.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public HelloReply m5437parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new HelloReply(codedInputStream, extensionRegistryLite);
            }
        };
        private byte memoizedIsInitialized;
        private volatile Object message_;

        private HelloReply(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private HelloReply() {
            this.memoizedIsInitialized = (byte) -1;
            this.message_ = "";
        }

        private HelloReply(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.message_ = codedInputStream.readStringRequireUtf8();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static HelloReply getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<HelloReply> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_HelloReply_descriptor;
        }

        public static HelloReply parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (HelloReply) PARSER.parseFrom(byteBuffer);
        }

        public static HelloReply parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HelloReply) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static HelloReply parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (HelloReply) PARSER.parseFrom(byteString);
        }

        public static HelloReply parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HelloReply) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static HelloReply parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (HelloReply) PARSER.parseFrom(bArr);
        }

        public static HelloReply parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HelloReply) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static HelloReply parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static HelloReply parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static HelloReply parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static HelloReply parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static HelloReply parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static HelloReply parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m5435toBuilder();
        }

        public static Builder newBuilder(HelloReply helloReply) {
            return DEFAULT_INSTANCE.m5435toBuilder().mergeFrom(helloReply);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HelloReply m5430getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<HelloReply> getParserForType() {
            return PARSER;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new HelloReply();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_HelloReply_fieldAccessorTable.ensureFieldAccessorsInitialized(HelloReply.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.HelloReplyOrBuilder
        public String getMessage() {
            Object obj = this.message_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.message_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.HelloReplyOrBuilder
        public ByteString getMessageBytes() {
            Object obj = this.message_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.message_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getMessageBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.message_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (!getMessageBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.message_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeStringSize;
            return iComputeStringSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof HelloReply)) {
                return super.equals(obj);
            }
            HelloReply helloReply = (HelloReply) obj;
            return getMessage().equals(helloReply.getMessage()) && this.unknownFields.equals(helloReply.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getMessage().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5432newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5435toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HelloReplyOrBuilder {
            private Object message_;

            private Builder() {
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_HelloReply_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_HelloReply_fieldAccessorTable.ensureFieldAccessorsInitialized(HelloReply.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = HelloReply.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5446clear() {
                super.clear();
                this.message_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_HelloReply_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HelloReply m5459getDefaultInstanceForType() {
                return HelloReply.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HelloReply m5440build() throws UninitializedMessageException {
                HelloReply helloReplyM5442buildPartial = m5442buildPartial();
                if (helloReplyM5442buildPartial.isInitialized()) {
                    return helloReplyM5442buildPartial;
                }
                throw newUninitializedMessageException(helloReplyM5442buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HelloReply m5442buildPartial() {
                HelloReply helloReply = new HelloReply(this);
                helloReply.message_ = this.message_;
                onBuilt();
                return helloReply;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5458clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5470setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5448clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5451clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5472setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5438addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5463mergeFrom(Message message) {
                if (message instanceof HelloReply) {
                    return mergeFrom((HelloReply) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(HelloReply helloReply) {
                if (helloReply == HelloReply.getDefaultInstance()) {
                    return this;
                }
                if (!helloReply.getMessage().isEmpty()) {
                    this.message_ = helloReply.message_;
                    onChanged();
                }
                m5468mergeUnknownFields(helloReply.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.HelloReply.Builder m5464mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.HelloReply.m5429$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$HelloReply r3 = (com.shimmerresearch.grpc.ShimmerGRPC.HelloReply) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$HelloReply r4 = (com.shimmerresearch.grpc.ShimmerGRPC.HelloReply) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.HelloReply.Builder.m5464mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$HelloReply$Builder");
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.HelloReplyOrBuilder
            public String getMessage() {
                Object obj = this.message_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.message_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setMessage(String str) {
                str.getClass();
                this.message_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.HelloReplyOrBuilder
            public ByteString getMessageBytes() {
                Object obj = this.message_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.message_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMessageBytes(ByteString byteString) {
                byteString.getClass();
                HelloReply.checkByteStringIsUtf8(byteString);
                this.message_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearMessage() {
                this.message_ = HelloReply.getDefaultInstance().getMessage();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5474setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5468mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class ShimmerRequest extends GeneratedMessageV3 implements ShimmerRequestOrBuilder {
        public static final int ADDRESS_FIELD_NUMBER = 1;
        private static final ShimmerRequest DEFAULT_INSTANCE = new ShimmerRequest();
        private static final Parser<ShimmerRequest> PARSER = new AbstractParser<ShimmerRequest>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.ShimmerRequest.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public ShimmerRequest m5907parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ShimmerRequest(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private volatile Object address_;
        private byte memoizedIsInitialized;

        private ShimmerRequest(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ShimmerRequest() {
            this.memoizedIsInitialized = (byte) -1;
            this.address_ = "";
        }

        private ShimmerRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.address_ = codedInputStream.readStringRequireUtf8();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static ShimmerRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ShimmerRequest> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_ShimmerRequest_descriptor;
        }

        public static ShimmerRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ShimmerRequest) PARSER.parseFrom(byteBuffer);
        }

        public static ShimmerRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ShimmerRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ShimmerRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ShimmerRequest) PARSER.parseFrom(byteString);
        }

        public static ShimmerRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ShimmerRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ShimmerRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ShimmerRequest) PARSER.parseFrom(bArr);
        }

        public static ShimmerRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ShimmerRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ShimmerRequest parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ShimmerRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ShimmerRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ShimmerRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ShimmerRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ShimmerRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m5905toBuilder();
        }

        public static Builder newBuilder(ShimmerRequest shimmerRequest) {
            return DEFAULT_INSTANCE.m5905toBuilder().mergeFrom(shimmerRequest);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ShimmerRequest m5900getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<ShimmerRequest> getParserForType() {
            return PARSER;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new ShimmerRequest();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_ShimmerRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ShimmerRequest.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmerRequestOrBuilder
        public String getAddress() {
            Object obj = this.address_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.address_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmerRequestOrBuilder
        public ByteString getAddressBytes() {
            Object obj = this.address_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.address_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getAddressBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.address_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (!getAddressBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.address_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeStringSize;
            return iComputeStringSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ShimmerRequest)) {
                return super.equals(obj);
            }
            ShimmerRequest shimmerRequest = (ShimmerRequest) obj;
            return getAddress().equals(shimmerRequest.getAddress()) && this.unknownFields.equals(shimmerRequest.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getAddress().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5902newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5905toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ShimmerRequestOrBuilder {
            private Object address_;

            private Builder() {
                this.address_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.address_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_ShimmerRequest_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_ShimmerRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ShimmerRequest.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ShimmerRequest.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5916clear() {
                super.clear();
                this.address_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_ShimmerRequest_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ShimmerRequest m5929getDefaultInstanceForType() {
                return ShimmerRequest.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ShimmerRequest m5910build() throws UninitializedMessageException {
                ShimmerRequest shimmerRequestM5912buildPartial = m5912buildPartial();
                if (shimmerRequestM5912buildPartial.isInitialized()) {
                    return shimmerRequestM5912buildPartial;
                }
                throw newUninitializedMessageException(shimmerRequestM5912buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ShimmerRequest m5912buildPartial() {
                ShimmerRequest shimmerRequest = new ShimmerRequest(this);
                shimmerRequest.address_ = this.address_;
                onBuilt();
                return shimmerRequest;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5928clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5940setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5918clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5921clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5942setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5908addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5933mergeFrom(Message message) {
                if (message instanceof ShimmerRequest) {
                    return mergeFrom((ShimmerRequest) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ShimmerRequest shimmerRequest) {
                if (shimmerRequest == ShimmerRequest.getDefaultInstance()) {
                    return this;
                }
                if (!shimmerRequest.getAddress().isEmpty()) {
                    this.address_ = shimmerRequest.address_;
                    onChanged();
                }
                m5938mergeUnknownFields(shimmerRequest.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.ShimmerRequest.Builder m5934mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.ShimmerRequest.m5899$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$ShimmerRequest r3 = (com.shimmerresearch.grpc.ShimmerGRPC.ShimmerRequest) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$ShimmerRequest r4 = (com.shimmerresearch.grpc.ShimmerGRPC.ShimmerRequest) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.ShimmerRequest.Builder.m5934mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$ShimmerRequest$Builder");
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmerRequestOrBuilder
            public String getAddress() {
                Object obj = this.address_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.address_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setAddress(String str) {
                str.getClass();
                this.address_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmerRequestOrBuilder
            public ByteString getAddressBytes() {
                Object obj = this.address_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.address_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setAddressBytes(ByteString byteString) {
                byteString.getClass();
                ShimmerRequest.checkByteStringIsUtf8(byteString);
                this.address_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAddress() {
                this.address_ = ShimmerRequest.getDefaultInstance().getAddress();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5944setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5938mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class StreamRequest extends GeneratedMessageV3 implements StreamRequestOrBuilder {
        public static final int MESSAGE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final StreamRequest DEFAULT_INSTANCE = new StreamRequest();
        private static final Parser<StreamRequest> PARSER = new AbstractParser<StreamRequest>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.StreamRequest.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public StreamRequest m6091parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new StreamRequest(codedInputStream, extensionRegistryLite);
            }
        };
        private byte memoizedIsInitialized;
        private volatile Object message_;

        private StreamRequest(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private StreamRequest() {
            this.memoizedIsInitialized = (byte) -1;
            this.message_ = "";
        }

        private StreamRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.message_ = codedInputStream.readStringRequireUtf8();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static StreamRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StreamRequest> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_StreamRequest_descriptor;
        }

        public static StreamRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (StreamRequest) PARSER.parseFrom(byteBuffer);
        }

        public static StreamRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StreamRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static StreamRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StreamRequest) PARSER.parseFrom(byteString);
        }

        public static StreamRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StreamRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static StreamRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StreamRequest) PARSER.parseFrom(bArr);
        }

        public static StreamRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StreamRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static StreamRequest parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static StreamRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static StreamRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static StreamRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static StreamRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static StreamRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m6089toBuilder();
        }

        public static Builder newBuilder(StreamRequest streamRequest) {
            return DEFAULT_INSTANCE.m6089toBuilder().mergeFrom(streamRequest);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public StreamRequest m6084getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<StreamRequest> getParserForType() {
            return PARSER;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new StreamRequest();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_StreamRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(StreamRequest.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.StreamRequestOrBuilder
        public String getMessage() {
            Object obj = this.message_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.message_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.StreamRequestOrBuilder
        public ByteString getMessageBytes() {
            Object obj = this.message_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.message_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getMessageBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.message_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (!getMessageBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.message_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeStringSize;
            return iComputeStringSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof StreamRequest)) {
                return super.equals(obj);
            }
            StreamRequest streamRequest = (StreamRequest) obj;
            return getMessage().equals(streamRequest.getMessage()) && this.unknownFields.equals(streamRequest.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getMessage().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6086newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6089toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StreamRequestOrBuilder {
            private Object message_;

            private Builder() {
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_StreamRequest_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_StreamRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(StreamRequest.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = StreamRequest.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6100clear() {
                super.clear();
                this.message_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_StreamRequest_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public StreamRequest m6113getDefaultInstanceForType() {
                return StreamRequest.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public StreamRequest m6094build() throws UninitializedMessageException {
                StreamRequest streamRequestM6096buildPartial = m6096buildPartial();
                if (streamRequestM6096buildPartial.isInitialized()) {
                    return streamRequestM6096buildPartial;
                }
                throw newUninitializedMessageException(streamRequestM6096buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public StreamRequest m6096buildPartial() {
                StreamRequest streamRequest = new StreamRequest(this);
                streamRequest.message_ = this.message_;
                onBuilt();
                return streamRequest;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6112clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6124setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6102clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6105clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6126setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6092addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6117mergeFrom(Message message) {
                if (message instanceof StreamRequest) {
                    return mergeFrom((StreamRequest) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(StreamRequest streamRequest) {
                if (streamRequest == StreamRequest.getDefaultInstance()) {
                    return this;
                }
                if (!streamRequest.getMessage().isEmpty()) {
                    this.message_ = streamRequest.message_;
                    onChanged();
                }
                m6122mergeUnknownFields(streamRequest.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.StreamRequest.Builder m6118mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.StreamRequest.m6083$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$StreamRequest r3 = (com.shimmerresearch.grpc.ShimmerGRPC.StreamRequest) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$StreamRequest r4 = (com.shimmerresearch.grpc.ShimmerGRPC.StreamRequest) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.StreamRequest.Builder.m6118mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$StreamRequest$Builder");
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.StreamRequestOrBuilder
            public String getMessage() {
                Object obj = this.message_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.message_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setMessage(String str) {
                str.getClass();
                this.message_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.StreamRequestOrBuilder
            public ByteString getMessageBytes() {
                Object obj = this.message_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.message_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMessageBytes(ByteString byteString) {
                byteString.getClass();
                StreamRequest.checkByteStringIsUtf8(byteString);
                this.message_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearMessage() {
                this.message_ = StreamRequest.getDefaultInstance().getMessage();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m6128setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m6122mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class ObjectCluster2 extends GeneratedMessageV3 implements ObjectCluster2OrBuilder {
        public static final int BLUETOOTHADDRESS_FIELD_NUMBER = 2;
        public static final int CALIBRATEDTIMESTAMP_FIELD_NUMBER = 6;
        public static final int COMMUNICATIONTYPE_FIELD_NUMBER = 3;
        public static final int DATAMAP_FIELD_NUMBER = 4;
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int SYSTEMTIME_FIELD_NUMBER = 5;
        private static final long serialVersionUID = 0;
        private static final ObjectCluster2 DEFAULT_INSTANCE = new ObjectCluster2();
        private static final Parser<ObjectCluster2> PARSER = new AbstractParser<ObjectCluster2>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public ObjectCluster2 m5701parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ObjectCluster2(codedInputStream, extensionRegistryLite);
            }
        };
        private volatile Object bluetoothAddress_;
        private double calibratedTimeStamp_;
        private int communicationType_;
        private MapField<String, FormatCluster2> dataMap_;
        private byte memoizedIsInitialized;
        private volatile Object name_;
        private long systemTime_;

        private ObjectCluster2(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ObjectCluster2() {
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
            this.bluetoothAddress_ = "";
            this.communicationType_ = 0;
        }

        private ObjectCluster2(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.bluetoothAddress_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 24) {
                                this.communicationType_ = codedInputStream.readEnum();
                            } else if (tag == 34) {
                                if (!(z2 & true)) {
                                    this.dataMap_ = MapField.newMapField(DataMapDefaultEntryHolder.defaultEntry);
                                    z2 |= true;
                                }
                                MapEntry message = codedInputStream.readMessage(DataMapDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.dataMap_.getMutableMap().put((String) message.getKey(), (FormatCluster2) message.getValue());
                            } else if (tag == 40) {
                                this.systemTime_ = codedInputStream.readInt64();
                            } else if (tag == 49) {
                                this.calibratedTimeStamp_ = codedInputStream.readDouble();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static ObjectCluster2 getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ObjectCluster2> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_descriptor;
        }

        public static ObjectCluster2 parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ObjectCluster2) PARSER.parseFrom(byteBuffer);
        }

        public static ObjectCluster2 parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ObjectCluster2) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ObjectCluster2 parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ObjectCluster2) PARSER.parseFrom(byteString);
        }

        public static ObjectCluster2 parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ObjectCluster2) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ObjectCluster2 parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ObjectCluster2) PARSER.parseFrom(bArr);
        }

        public static ObjectCluster2 parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ObjectCluster2) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ObjectCluster2 parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ObjectCluster2 parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ObjectCluster2 parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ObjectCluster2 parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ObjectCluster2 parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ObjectCluster2 parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m5699toBuilder();
        }

        public static Builder newBuilder(ObjectCluster2 objectCluster2) {
            return DEFAULT_INSTANCE.m5699toBuilder().mergeFrom(objectCluster2);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
        public double getCalibratedTimeStamp() {
            return this.calibratedTimeStamp_;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
        public int getCommunicationTypeValue() {
            return this.communicationType_;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ObjectCluster2 m5694getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<ObjectCluster2> getParserForType() {
            return PARSER;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
        public long getSystemTime() {
            return this.systemTime_;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new ObjectCluster2();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 4) {
                return internalGetDataMap();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectCluster2.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
        public String getBluetoothAddress() {
            Object obj = this.bluetoothAddress_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.bluetoothAddress_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
        public ByteString getBluetoothAddressBytes() {
            Object obj = this.bluetoothAddress_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bluetoothAddress_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
        public CommunicationType getCommunicationType() {
            CommunicationType communicationTypeValueOf = CommunicationType.valueOf(this.communicationType_);
            return communicationTypeValueOf == null ? CommunicationType.UNRECOGNIZED : communicationTypeValueOf;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MapField<String, FormatCluster2> internalGetDataMap() {
            MapField<String, FormatCluster2> mapField = this.dataMap_;
            return mapField == null ? MapField.emptyMapField(DataMapDefaultEntryHolder.defaultEntry) : mapField;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
        public int getDataMapCount() {
            return internalGetDataMap().getMap().size();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
        public boolean containsDataMap(String str) {
            str.getClass();
            return internalGetDataMap().getMap().containsKey(str);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
        @Deprecated
        public Map<String, FormatCluster2> getDataMap() {
            return getDataMapMap();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
        public Map<String, FormatCluster2> getDataMapMap() {
            return internalGetDataMap().getMap();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
        public FormatCluster2 getDataMapOrDefault(String str, FormatCluster2 formatCluster2) {
            str.getClass();
            Map map = internalGetDataMap().getMap();
            return map.containsKey(str) ? (FormatCluster2) map.get(str) : formatCluster2;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
        public FormatCluster2 getDataMapOrThrow(String str) {
            str.getClass();
            Map map = internalGetDataMap().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return (FormatCluster2) map.get(str);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
            }
            if (!getBluetoothAddressBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.bluetoothAddress_);
            }
            if (this.communicationType_ != CommunicationType.BT.getNumber()) {
                codedOutputStream.writeEnum(3, this.communicationType_);
            }
            GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetDataMap(), DataMapDefaultEntryHolder.defaultEntry, 4);
            long j = this.systemTime_;
            if (j != 0) {
                codedOutputStream.writeInt64(5, j);
            }
            double d = this.calibratedTimeStamp_;
            if (d != 0.0d) {
                codedOutputStream.writeDouble(6, d);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
            if (!getBluetoothAddressBytes().isEmpty()) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.bluetoothAddress_);
            }
            if (this.communicationType_ != CommunicationType.BT.getNumber()) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(3, this.communicationType_);
            }
            for (Map.Entry entry : internalGetDataMap().getMap().entrySet()) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(4, DataMapDefaultEntryHolder.defaultEntry.newBuilderForType().setKey((String) entry.getKey()).setValue((FormatCluster2) entry.getValue()).build());
            }
            long j = this.systemTime_;
            if (j != 0) {
                iComputeStringSize += CodedOutputStream.computeInt64Size(5, j);
            }
            double d = this.calibratedTimeStamp_;
            if (d != 0.0d) {
                iComputeStringSize += CodedOutputStream.computeDoubleSize(6, d);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ObjectCluster2)) {
                return super.equals(obj);
            }
            ObjectCluster2 objectCluster2 = (ObjectCluster2) obj;
            return getName().equals(objectCluster2.getName()) && getBluetoothAddress().equals(objectCluster2.getBluetoothAddress()) && this.communicationType_ == objectCluster2.communicationType_ && internalGetDataMap().equals(objectCluster2.internalGetDataMap()) && getSystemTime() == objectCluster2.getSystemTime() && Double.doubleToLongBits(getCalibratedTimeStamp()) == Double.doubleToLongBits(objectCluster2.getCalibratedTimeStamp()) && this.unknownFields.equals(objectCluster2.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getBluetoothAddress().hashCode()) * 37) + 3) * 53) + this.communicationType_;
            if (!internalGetDataMap().getMap().isEmpty()) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + internalGetDataMap().hashCode();
            }
            int iHashLong = (((((((((iHashCode * 37) + 5) * 53) + Internal.hashLong(getSystemTime())) * 37) + 6) * 53) + Internal.hashLong(Double.doubleToLongBits(getCalibratedTimeStamp()))) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashLong;
            return iHashLong;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5696newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5699toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public enum CommunicationType implements ProtocolMessageEnum {
            BT(0),
            SD(1),
            Radio_802_15_4(2),
            UNRECOGNIZED(-1);

            public static final int BT_VALUE = 0;
            public static final int Radio_802_15_4_VALUE = 2;
            public static final int SD_VALUE = 1;
            private static final Internal.EnumLiteMap<CommunicationType> internalValueMap = new Internal.EnumLiteMap<CommunicationType>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.CommunicationType.1
                public CommunicationType findValueByNumber(int i) {
                    return CommunicationType.forNumber(i);
                }
            };
            private static final CommunicationType[] VALUES = values();
            private final int value;

            CommunicationType(int i) {
                this.value = i;
            }

            public static CommunicationType forNumber(int i) {
                if (i == 0) {
                    return BT;
                }
                if (i == 1) {
                    return SD;
                }
                if (i != 2) {
                    return null;
                }
                return Radio_802_15_4;
            }

            public static Internal.EnumLiteMap<CommunicationType> internalGetValueMap() {
                return internalValueMap;
            }

            @Deprecated
            public static CommunicationType valueOf(int i) {
                return forNumber(i);
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return (Descriptors.EnumDescriptor) ObjectCluster2.getDescriptor().getEnumTypes().get(0);
            }

            public static CommunicationType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this == UNRECOGNIZED) {
                    throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
                }
                return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }
        }

        public interface FormatCluster2OrBuilder extends MessageOrBuilder {
            boolean containsFormatMap(String str);

            @Deprecated
            Map<String, FormatCluster2.DataCluster2> getFormatMap();

            int getFormatMapCount();

            Map<String, FormatCluster2.DataCluster2> getFormatMapMap();

            FormatCluster2.DataCluster2 getFormatMapOrDefault(String str, FormatCluster2.DataCluster2 dataCluster2);

            FormatCluster2.DataCluster2 getFormatMapOrThrow(String str);
        }

        public static final class FormatCluster2 extends GeneratedMessageV3 implements FormatCluster2OrBuilder {
            public static final int FORMATMAP_FIELD_NUMBER = 1;
            private static final long serialVersionUID = 0;
            private static final FormatCluster2 DEFAULT_INSTANCE = new FormatCluster2();
            private static final Parser<FormatCluster2> PARSER = new AbstractParser<FormatCluster2>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public FormatCluster2 m5752parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new FormatCluster2(codedInputStream, extensionRegistryLite);
                }
            };
            private MapField<String, DataCluster2> formatMap_;
            private byte memoizedIsInitialized;

            private FormatCluster2(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private FormatCluster2() {
                this.memoizedIsInitialized = (byte) -1;
            }

            private FormatCluster2(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int tag = codedInputStream.readTag();
                                if (tag != 0) {
                                    if (tag == 10) {
                                        if (!(z2 & true)) {
                                            this.formatMap_ = MapField.newMapField(FormatMapDefaultEntryHolder.defaultEntry);
                                            z2 |= true;
                                        }
                                        MapEntry message = codedInputStream.readMessage(FormatMapDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                        this.formatMap_.getMutableMap().put((String) message.getKey(), (DataCluster2) message.getValue());
                                    } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    }
                                }
                                z = true;
                            } catch (IOException e) {
                                throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw e2.setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = builderNewBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static FormatCluster2 getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<FormatCluster2> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_descriptor;
            }

            public static FormatCluster2 parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (FormatCluster2) PARSER.parseFrom(byteBuffer);
            }

            public static FormatCluster2 parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (FormatCluster2) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static FormatCluster2 parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (FormatCluster2) PARSER.parseFrom(byteString);
            }

            public static FormatCluster2 parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (FormatCluster2) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static FormatCluster2 parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (FormatCluster2) PARSER.parseFrom(bArr);
            }

            public static FormatCluster2 parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (FormatCluster2) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static FormatCluster2 parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static FormatCluster2 parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static FormatCluster2 parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static FormatCluster2 parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static FormatCluster2 parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static FormatCluster2 parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m5750toBuilder();
            }

            public static Builder newBuilder(FormatCluster2 formatCluster2) {
                return DEFAULT_INSTANCE.m5750toBuilder().mergeFrom(formatCluster2);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public FormatCluster2 m5745getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public Parser<FormatCluster2> getParserForType() {
                return PARSER;
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new FormatCluster2();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected MapField internalGetMapField(int i) {
                if (i == 1) {
                    return internalGetFormatMap();
                }
                throw new RuntimeException("Invalid map field number: " + i);
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_fieldAccessorTable.ensureFieldAccessorsInitialized(FormatCluster2.class, Builder.class);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public MapField<String, DataCluster2> internalGetFormatMap() {
                MapField<String, DataCluster2> mapField = this.formatMap_;
                return mapField == null ? MapField.emptyMapField(FormatMapDefaultEntryHolder.defaultEntry) : mapField;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2OrBuilder
            public int getFormatMapCount() {
                return internalGetFormatMap().getMap().size();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2OrBuilder
            public boolean containsFormatMap(String str) {
                str.getClass();
                return internalGetFormatMap().getMap().containsKey(str);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2OrBuilder
            @Deprecated
            public Map<String, DataCluster2> getFormatMap() {
                return getFormatMapMap();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2OrBuilder
            public Map<String, DataCluster2> getFormatMapMap() {
                return internalGetFormatMap().getMap();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2OrBuilder
            public DataCluster2 getFormatMapOrDefault(String str, DataCluster2 dataCluster2) {
                str.getClass();
                Map map = internalGetFormatMap().getMap();
                return map.containsKey(str) ? (DataCluster2) map.get(str) : dataCluster2;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2OrBuilder
            public DataCluster2 getFormatMapOrThrow(String str) {
                str.getClass();
                Map map = internalGetFormatMap().getMap();
                if (!map.containsKey(str)) {
                    throw new IllegalArgumentException();
                }
                return (DataCluster2) map.get(str);
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetFormatMap(), FormatMapDefaultEntryHolder.defaultEntry, 1);
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeMessageSize = 0;
                for (Map.Entry entry : internalGetFormatMap().getMap().entrySet()) {
                    iComputeMessageSize += CodedOutputStream.computeMessageSize(1, FormatMapDefaultEntryHolder.defaultEntry.newBuilderForType().setKey((String) entry.getKey()).setValue((DataCluster2) entry.getValue()).build());
                }
                int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof FormatCluster2)) {
                    return super.equals(obj);
                }
                FormatCluster2 formatCluster2 = (FormatCluster2) obj;
                return internalGetFormatMap().equals(formatCluster2.internalGetFormatMap()) && this.unknownFields.equals(formatCluster2.unknownFields);
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = 779 + getDescriptor().hashCode();
                if (!internalGetFormatMap().getMap().isEmpty()) {
                    iHashCode = (((iHashCode * 37) + 1) * 53) + internalGetFormatMap().hashCode();
                }
                int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5747newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5750toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public interface DataCluster2OrBuilder extends MessageOrBuilder {
                double getData();

                double getDataArray(int i);

                int getDataArrayCount();

                List<Double> getDataArrayList();

                String getUnit();

                ByteString getUnitBytes();
            }

            public static final class DataCluster2 extends GeneratedMessageV3 implements DataCluster2OrBuilder {
                public static final int DATAARRAY_FIELD_NUMBER = 3;
                public static final int DATA_FIELD_NUMBER = 2;
                public static final int UNIT_FIELD_NUMBER = 1;
                private static final DataCluster2 DEFAULT_INSTANCE = new DataCluster2();
                private static final Parser<DataCluster2> PARSER = new AbstractParser<DataCluster2>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2.1
                    /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                    public DataCluster2 m5804parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                        return new DataCluster2(codedInputStream, extensionRegistryLite);
                    }
                };
                private static final long serialVersionUID = 0;
                private int dataArrayMemoizedSerializedSize;
                private Internal.DoubleList dataArray_;
                private double data_;
                private byte memoizedIsInitialized;
                private volatile Object unit_;

                private DataCluster2(GeneratedMessageV3.Builder<?> builder) {
                    super(builder);
                    this.dataArrayMemoizedSerializedSize = -1;
                    this.memoizedIsInitialized = (byte) -1;
                }

                private DataCluster2() {
                    this.dataArrayMemoizedSerializedSize = -1;
                    this.memoizedIsInitialized = (byte) -1;
                    this.unit_ = "";
                    this.dataArray_ = emptyDoubleList();
                }

                private DataCluster2(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    this();
                    extensionRegistryLite.getClass();
                    UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                    boolean z = false;
                    boolean z2 = false;
                    while (!z) {
                        try {
                            try {
                                try {
                                    int tag = codedInputStream.readTag();
                                    if (tag != 0) {
                                        if (tag == 10) {
                                            this.unit_ = codedInputStream.readStringRequireUtf8();
                                        } else if (tag == 17) {
                                            this.data_ = codedInputStream.readDouble();
                                        } else if (tag == 25) {
                                            if (!(z2 & true)) {
                                                this.dataArray_ = newDoubleList();
                                                z2 |= true;
                                            }
                                            this.dataArray_.addDouble(codedInputStream.readDouble());
                                        } else if (tag == 26) {
                                            int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                            if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                                                this.dataArray_ = newDoubleList();
                                                z2 |= true;
                                            }
                                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                                this.dataArray_.addDouble(codedInputStream.readDouble());
                                            }
                                            codedInputStream.popLimit(iPushLimit);
                                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                        }
                                    }
                                    z = true;
                                } catch (InvalidProtocolBufferException e) {
                                    throw e.setUnfinishedMessage(this);
                                }
                            } catch (IOException e2) {
                                throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                            }
                        } finally {
                            if (z2 & true) {
                                this.dataArray_.makeImmutable();
                            }
                            this.unknownFields = builderNewBuilder.build();
                            makeExtensionsImmutable();
                        }
                    }
                }

                public static DataCluster2 getDefaultInstance() {
                    return DEFAULT_INSTANCE;
                }

                public static Parser<DataCluster2> parser() {
                    return PARSER;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_DataCluster2_descriptor;
                }

                public static DataCluster2 parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                    return (DataCluster2) PARSER.parseFrom(byteBuffer);
                }

                public static DataCluster2 parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (DataCluster2) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
                }

                public static DataCluster2 parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                    return (DataCluster2) PARSER.parseFrom(byteString);
                }

                public static DataCluster2 parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (DataCluster2) PARSER.parseFrom(byteString, extensionRegistryLite);
                }

                public static DataCluster2 parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                    return (DataCluster2) PARSER.parseFrom(bArr);
                }

                public static DataCluster2 parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (DataCluster2) PARSER.parseFrom(bArr, extensionRegistryLite);
                }

                public static DataCluster2 parseFrom(InputStream inputStream) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
                }

                public static DataCluster2 parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
                }

                public static DataCluster2 parseDelimitedFrom(InputStream inputStream) throws IOException {
                    return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
                }

                public static DataCluster2 parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
                }

                public static DataCluster2 parseFrom(CodedInputStream codedInputStream) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
                }

                public static DataCluster2 parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
                }

                public static Builder newBuilder() {
                    return DEFAULT_INSTANCE.m5802toBuilder();
                }

                public static Builder newBuilder(DataCluster2 dataCluster2) {
                    return DEFAULT_INSTANCE.m5802toBuilder().mergeFrom(dataCluster2);
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2OrBuilder
                public double getData() {
                    return this.data_;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2OrBuilder
                public List<Double> getDataArrayList() {
                    return this.dataArray_;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public DataCluster2 m5797getDefaultInstanceForType() {
                    return DEFAULT_INSTANCE;
                }

                public Parser<DataCluster2> getParserForType() {
                    return PARSER;
                }

                public final boolean isInitialized() {
                    byte b = this.memoizedIsInitialized;
                    if (b == 1) {
                        return true;
                    }
                    if (b == 0) {
                        return false;
                    }
                    this.memoizedIsInitialized = (byte) 1;
                    return true;
                }

                protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                    return new DataCluster2();
                }

                public final UnknownFieldSet getUnknownFields() {
                    return this.unknownFields;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_DataCluster2_fieldAccessorTable.ensureFieldAccessorsInitialized(DataCluster2.class, Builder.class);
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2OrBuilder
                public String getUnit() {
                    Object obj = this.unit_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.unit_ = stringUtf8;
                    return stringUtf8;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2OrBuilder
                public ByteString getUnitBytes() {
                    Object obj = this.unit_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.unit_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2OrBuilder
                public int getDataArrayCount() {
                    return this.dataArray_.size();
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2OrBuilder
                public double getDataArray(int i) {
                    return this.dataArray_.getDouble(i);
                }

                public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                    getSerializedSize();
                    if (!getUnitBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 1, this.unit_);
                    }
                    double d = this.data_;
                    if (d != 0.0d) {
                        codedOutputStream.writeDouble(2, d);
                    }
                    if (getDataArrayList().size() > 0) {
                        codedOutputStream.writeUInt32NoTag(26);
                        codedOutputStream.writeUInt32NoTag(this.dataArrayMemoizedSerializedSize);
                    }
                    for (int i = 0; i < this.dataArray_.size(); i++) {
                        codedOutputStream.writeDoubleNoTag(this.dataArray_.getDouble(i));
                    }
                    this.unknownFields.writeTo(codedOutputStream);
                }

                public int getSerializedSize() {
                    int i = this.memoizedSize;
                    if (i != -1) {
                        return i;
                    }
                    int iComputeStringSize = !getUnitBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.unit_) : 0;
                    double d = this.data_;
                    if (d != 0.0d) {
                        iComputeStringSize += CodedOutputStream.computeDoubleSize(2, d);
                    }
                    int size = getDataArrayList().size() * 8;
                    int iComputeInt32SizeNoTag = iComputeStringSize + size;
                    if (!getDataArrayList().isEmpty()) {
                        iComputeInt32SizeNoTag = iComputeInt32SizeNoTag + 1 + CodedOutputStream.computeInt32SizeNoTag(size);
                    }
                    this.dataArrayMemoizedSerializedSize = size;
                    int serializedSize = iComputeInt32SizeNoTag + this.unknownFields.getSerializedSize();
                    this.memoizedSize = serializedSize;
                    return serializedSize;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof DataCluster2)) {
                        return super.equals(obj);
                    }
                    DataCluster2 dataCluster2 = (DataCluster2) obj;
                    return getUnit().equals(dataCluster2.getUnit()) && Double.doubleToLongBits(getData()) == Double.doubleToLongBits(dataCluster2.getData()) && getDataArrayList().equals(dataCluster2.getDataArrayList()) && this.unknownFields.equals(dataCluster2.unknownFields);
                }

                public int hashCode() {
                    if (this.memoizedHashCode != 0) {
                        return this.memoizedHashCode;
                    }
                    int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getUnit().hashCode()) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getData()));
                    if (getDataArrayCount() > 0) {
                        iHashCode = (((iHashCode * 37) + 3) * 53) + getDataArrayList().hashCode();
                    }
                    int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
                    this.memoizedHashCode = iHashCode2;
                    return iHashCode2;
                }

                /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5799newBuilderForType() {
                    return newBuilder();
                }

                /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5802toBuilder() {
                    return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                    return new Builder(builderParent);
                }

                public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DataCluster2OrBuilder {
                    private int bitField0_;
                    private Internal.DoubleList dataArray_;
                    private double data_;
                    private Object unit_;

                    private Builder() {
                        this.unit_ = "";
                        this.dataArray_ = DataCluster2.emptyDoubleList();
                        maybeForceBuilderInitialization();
                    }

                    private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                        super(builderParent);
                        this.unit_ = "";
                        this.dataArray_ = DataCluster2.emptyDoubleList();
                        maybeForceBuilderInitialization();
                    }

                    public static final Descriptors.Descriptor getDescriptor() {
                        return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_DataCluster2_descriptor;
                    }

                    @Override
                    // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2OrBuilder
                    public double getData() {
                        return this.data_;
                    }

                    public Builder setData(double d) {
                        this.data_ = d;
                        onChanged();
                        return this;
                    }

                    public final boolean isInitialized() {
                        return true;
                    }

                    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                        return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_DataCluster2_fieldAccessorTable.ensureFieldAccessorsInitialized(DataCluster2.class, Builder.class);
                    }

                    private void maybeForceBuilderInitialization() {
                        boolean unused = DataCluster2.alwaysUseFieldBuilders;
                    }

                    /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m5813clear() {
                        super.clear();
                        this.unit_ = "";
                        this.data_ = 0.0d;
                        this.dataArray_ = DataCluster2.emptyDoubleList();
                        this.bitField0_ &= -2;
                        return this;
                    }

                    public Descriptors.Descriptor getDescriptorForType() {
                        return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_DataCluster2_descriptor;
                    }

                    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public DataCluster2 m5826getDefaultInstanceForType() {
                        return DataCluster2.getDefaultInstance();
                    }

                    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                    /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public DataCluster2 m5807build() throws UninitializedMessageException {
                        DataCluster2 dataCluster2M5809buildPartial = m5809buildPartial();
                        if (dataCluster2M5809buildPartial.isInitialized()) {
                            return dataCluster2M5809buildPartial;
                        }
                        throw newUninitializedMessageException(dataCluster2M5809buildPartial);
                    }

                    /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public DataCluster2 m5809buildPartial() {
                        DataCluster2 dataCluster2 = new DataCluster2(this);
                        dataCluster2.unit_ = this.unit_;
                        dataCluster2.data_ = this.data_;
                        if ((this.bitField0_ & 1) != 0) {
                            this.dataArray_.makeImmutable();
                            this.bitField0_ &= -2;
                        }
                        dataCluster2.dataArray_ = this.dataArray_;
                        onBuilt();
                        return dataCluster2;
                    }

                    /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m5825clone() {
                        return (Builder) super.clone();
                    }

                    /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m5837setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                        return (Builder) super.setField(fieldDescriptor, obj);
                    }

                    /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m5815clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                        return (Builder) super.clearField(fieldDescriptor);
                    }

                    /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m5818clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                        return (Builder) super.clearOneof(oneofDescriptor);
                    }

                    /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m5839setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                        return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                    }

                    /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m5805addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                        return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                    }

                    /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m5830mergeFrom(Message message) {
                        if (message instanceof DataCluster2) {
                            return mergeFrom((DataCluster2) message);
                        }
                        super.mergeFrom(message);
                        return this;
                    }

                    public Builder mergeFrom(DataCluster2 dataCluster2) {
                        if (dataCluster2 == DataCluster2.getDefaultInstance()) {
                            return this;
                        }
                        if (!dataCluster2.getUnit().isEmpty()) {
                            this.unit_ = dataCluster2.unit_;
                            onChanged();
                        }
                        if (dataCluster2.getData() != 0.0d) {
                            setData(dataCluster2.getData());
                        }
                        if (!dataCluster2.dataArray_.isEmpty()) {
                            if (this.dataArray_.isEmpty()) {
                                this.dataArray_ = dataCluster2.dataArray_;
                                this.bitField0_ &= -2;
                            } else {
                                ensureDataArrayIsMutable();
                                this.dataArray_.addAll(dataCluster2.dataArray_);
                            }
                            onChanged();
                        }
                        m5835mergeUnknownFields(dataCluster2.unknownFields);
                        onChanged();
                        return this;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                    /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2.Builder m5831mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                        /*
                            r2 = this;
                            r0 = 0
                            com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2.m5796$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                            java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                            com.shimmerresearch.grpc.ShimmerGRPC$ObjectCluster2$FormatCluster2$DataCluster2 r3 = (com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                            if (r3 == 0) goto L10
                            r2.mergeFrom(r3)
                        L10:
                            return r2
                        L11:
                            r3 = move-exception
                            goto L21
                        L13:
                            r3 = move-exception
                            com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                            com.shimmerresearch.grpc.ShimmerGRPC$ObjectCluster2$FormatCluster2$DataCluster2 r4 = (com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2) r4     // Catch: java.lang.Throwable -> L11
                            java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                            throw r3     // Catch: java.lang.Throwable -> L1f
                        L1f:
                            r3 = move-exception
                            r0 = r4
                        L21:
                            if (r0 == 0) goto L26
                            r2.mergeFrom(r0)
                        L26:
                            throw r3
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2.Builder.m5831mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$ObjectCluster2$FormatCluster2$DataCluster2$Builder");
                    }

                    @Override
                    // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2OrBuilder
                    public String getUnit() {
                        Object obj = this.unit_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.unit_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    public Builder setUnit(String str) {
                        str.getClass();
                        this.unit_ = str;
                        onChanged();
                        return this;
                    }

                    @Override
                    // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2OrBuilder
                    public ByteString getUnitBytes() {
                        Object obj = this.unit_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.unit_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setUnitBytes(ByteString byteString) {
                        byteString.getClass();
                        DataCluster2.checkByteStringIsUtf8(byteString);
                        this.unit_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearUnit() {
                        this.unit_ = DataCluster2.getDefaultInstance().getUnit();
                        onChanged();
                        return this;
                    }

                    public Builder clearData() {
                        this.data_ = 0.0d;
                        onChanged();
                        return this;
                    }

                    private void ensureDataArrayIsMutable() {
                        if ((this.bitField0_ & 1) == 0) {
                            this.dataArray_ = DataCluster2.mutableCopy(this.dataArray_);
                            this.bitField0_ |= 1;
                        }
                    }

                    @Override
                    // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2OrBuilder
                    public List<Double> getDataArrayList() {
                        return (this.bitField0_ & 1) != 0 ? Collections.unmodifiableList(this.dataArray_) : this.dataArray_;
                    }

                    @Override
                    // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2OrBuilder
                    public int getDataArrayCount() {
                        return this.dataArray_.size();
                    }

                    @Override
                    // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2OrBuilder
                    public double getDataArray(int i) {
                        return this.dataArray_.getDouble(i);
                    }

                    public Builder setDataArray(int i, double d) {
                        ensureDataArrayIsMutable();
                        this.dataArray_.setDouble(i, d);
                        onChanged();
                        return this;
                    }

                    public Builder addDataArray(double d) {
                        ensureDataArrayIsMutable();
                        this.dataArray_.addDouble(d);
                        onChanged();
                        return this;
                    }

                    public Builder addAllDataArray(Iterable<? extends Double> iterable) {
                        ensureDataArrayIsMutable();
                        AbstractMessageLite.Builder.addAll(iterable, this.dataArray_);
                        onChanged();
                        return this;
                    }

                    public Builder clearDataArray() {
                        this.dataArray_ = DataCluster2.emptyDoubleList();
                        this.bitField0_ &= -2;
                        onChanged();
                        return this;
                    }

                    /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public final Builder m5841setUnknownFields(UnknownFieldSet unknownFieldSet) {
                        return (Builder) super.setUnknownFields(unknownFieldSet);
                    }

                    /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public final Builder m5835mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                        return (Builder) super.mergeUnknownFields(unknownFieldSet);
                    }
                }
            }

            private static final class FormatMapDefaultEntryHolder {
                static final MapEntry<String, DataCluster2> defaultEntry = MapEntry.newDefaultInstance(ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_FormatMapEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, DataCluster2.getDefaultInstance());

                private FormatMapDefaultEntryHolder() {
                }
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FormatCluster2OrBuilder {
                private int bitField0_;
                private MapField<String, DataCluster2> formatMap_;

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_descriptor;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected MapField internalGetMapField(int i) {
                    if (i == 1) {
                        return internalGetFormatMap();
                    }
                    throw new RuntimeException("Invalid map field number: " + i);
                }

                protected MapField internalGetMutableMapField(int i) {
                    if (i == 1) {
                        return internalGetMutableFormatMap();
                    }
                    throw new RuntimeException("Invalid map field number: " + i);
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_fieldAccessorTable.ensureFieldAccessorsInitialized(FormatCluster2.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = FormatCluster2.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5761clear() {
                    super.clear();
                    internalGetMutableFormatMap().clear();
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_FormatCluster2_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public FormatCluster2 m5774getDefaultInstanceForType() {
                    return FormatCluster2.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public FormatCluster2 m5755build() throws UninitializedMessageException {
                    FormatCluster2 formatCluster2M5757buildPartial = m5757buildPartial();
                    if (formatCluster2M5757buildPartial.isInitialized()) {
                        return formatCluster2M5757buildPartial;
                    }
                    throw newUninitializedMessageException(formatCluster2M5757buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public FormatCluster2 m5757buildPartial() {
                    FormatCluster2 formatCluster2 = new FormatCluster2(this);
                    formatCluster2.formatMap_ = internalGetFormatMap();
                    formatCluster2.formatMap_.makeImmutable();
                    onBuilt();
                    return formatCluster2;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5773clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5785setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5763clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5766clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5787setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5753addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5778mergeFrom(Message message) {
                    if (message instanceof FormatCluster2) {
                        return mergeFrom((FormatCluster2) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(FormatCluster2 formatCluster2) {
                    if (formatCluster2 == FormatCluster2.getDefaultInstance()) {
                        return this;
                    }
                    internalGetMutableFormatMap().mergeFrom(formatCluster2.internalGetFormatMap());
                    m5783mergeUnknownFields(formatCluster2.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.Builder m5779mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.m5744$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.shimmerresearch.grpc.ShimmerGRPC$ObjectCluster2$FormatCluster2 r3 = (com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        if (r3 == 0) goto L10
                        r2.mergeFrom(r3)
                    L10:
                        return r2
                    L11:
                        r3 = move-exception
                        goto L21
                    L13:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                        com.shimmerresearch.grpc.ShimmerGRPC$ObjectCluster2$FormatCluster2 r4 = (com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2) r4     // Catch: java.lang.Throwable -> L11
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                        throw r3     // Catch: java.lang.Throwable -> L1f
                    L1f:
                        r3 = move-exception
                        r0 = r4
                    L21:
                        if (r0 == 0) goto L26
                        r2.mergeFrom(r0)
                    L26:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.Builder.m5779mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$ObjectCluster2$FormatCluster2$Builder");
                }

                private MapField<String, DataCluster2> internalGetFormatMap() {
                    MapField<String, DataCluster2> mapField = this.formatMap_;
                    return mapField == null ? MapField.emptyMapField(FormatMapDefaultEntryHolder.defaultEntry) : mapField;
                }

                private MapField<String, DataCluster2> internalGetMutableFormatMap() {
                    onChanged();
                    if (this.formatMap_ == null) {
                        this.formatMap_ = MapField.newMapField(FormatMapDefaultEntryHolder.defaultEntry);
                    }
                    if (!this.formatMap_.isMutable()) {
                        this.formatMap_ = this.formatMap_.copy();
                    }
                    return this.formatMap_;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2OrBuilder
                public int getFormatMapCount() {
                    return internalGetFormatMap().getMap().size();
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2OrBuilder
                public boolean containsFormatMap(String str) {
                    str.getClass();
                    return internalGetFormatMap().getMap().containsKey(str);
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2OrBuilder
                @Deprecated
                public Map<String, DataCluster2> getFormatMap() {
                    return getFormatMapMap();
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2OrBuilder
                public Map<String, DataCluster2> getFormatMapMap() {
                    return internalGetFormatMap().getMap();
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2OrBuilder
                public DataCluster2 getFormatMapOrDefault(String str, DataCluster2 dataCluster2) {
                    str.getClass();
                    Map map = internalGetFormatMap().getMap();
                    return map.containsKey(str) ? (DataCluster2) map.get(str) : dataCluster2;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2OrBuilder
                public DataCluster2 getFormatMapOrThrow(String str) {
                    str.getClass();
                    Map map = internalGetFormatMap().getMap();
                    if (!map.containsKey(str)) {
                        throw new IllegalArgumentException();
                    }
                    return (DataCluster2) map.get(str);
                }

                public Builder clearFormatMap() {
                    internalGetMutableFormatMap().getMutableMap().clear();
                    return this;
                }

                public Builder removeFormatMap(String str) {
                    str.getClass();
                    internalGetMutableFormatMap().getMutableMap().remove(str);
                    return this;
                }

                @Deprecated
                public Map<String, DataCluster2> getMutableFormatMap() {
                    return internalGetMutableFormatMap().getMutableMap();
                }

                public Builder putFormatMap(String str, DataCluster2 dataCluster2) {
                    str.getClass();
                    dataCluster2.getClass();
                    internalGetMutableFormatMap().getMutableMap().put(str, dataCluster2);
                    return this;
                }

                public Builder putAllFormatMap(Map<String, DataCluster2> map) {
                    internalGetMutableFormatMap().getMutableMap().putAll(map);
                    return this;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m5789setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m5783mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        private static final class DataMapDefaultEntryHolder {
            static final MapEntry<String, FormatCluster2> defaultEntry = MapEntry.newDefaultInstance(ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_DataMapEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, FormatCluster2.getDefaultInstance());

            private DataMapDefaultEntryHolder() {
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ObjectCluster2OrBuilder {
            private int bitField0_;
            private Object bluetoothAddress_;
            private double calibratedTimeStamp_;
            private int communicationType_;
            private MapField<String, FormatCluster2> dataMap_;
            private Object name_;
            private long systemTime_;

            private Builder() {
                this.name_ = "";
                this.bluetoothAddress_ = "";
                this.communicationType_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.bluetoothAddress_ = "";
                this.communicationType_ = 0;
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_descriptor;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
            public double getCalibratedTimeStamp() {
                return this.calibratedTimeStamp_;
            }

            public Builder setCalibratedTimeStamp(double d) {
                this.calibratedTimeStamp_ = d;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
            public int getCommunicationTypeValue() {
                return this.communicationType_;
            }

            public Builder setCommunicationTypeValue(int i) {
                this.communicationType_ = i;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
            public long getSystemTime() {
                return this.systemTime_;
            }

            public Builder setSystemTime(long j) {
                this.systemTime_ = j;
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected MapField internalGetMapField(int i) {
                if (i == 4) {
                    return internalGetDataMap();
                }
                throw new RuntimeException("Invalid map field number: " + i);
            }

            protected MapField internalGetMutableMapField(int i) {
                if (i == 4) {
                    return internalGetMutableDataMap();
                }
                throw new RuntimeException("Invalid map field number: " + i);
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectCluster2.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ObjectCluster2.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5710clear() {
                super.clear();
                this.name_ = "";
                this.bluetoothAddress_ = "";
                this.communicationType_ = 0;
                internalGetMutableDataMap().clear();
                this.systemTime_ = 0L;
                this.calibratedTimeStamp_ = 0.0d;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_ObjectCluster2_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ObjectCluster2 m5723getDefaultInstanceForType() {
                return ObjectCluster2.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ObjectCluster2 m5704build() throws UninitializedMessageException {
                ObjectCluster2 objectCluster2M5706buildPartial = m5706buildPartial();
                if (objectCluster2M5706buildPartial.isInitialized()) {
                    return objectCluster2M5706buildPartial;
                }
                throw newUninitializedMessageException(objectCluster2M5706buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ObjectCluster2 m5706buildPartial() {
                ObjectCluster2 objectCluster2 = new ObjectCluster2(this);
                objectCluster2.name_ = this.name_;
                objectCluster2.bluetoothAddress_ = this.bluetoothAddress_;
                objectCluster2.communicationType_ = this.communicationType_;
                objectCluster2.dataMap_ = internalGetDataMap();
                objectCluster2.dataMap_.makeImmutable();
                objectCluster2.systemTime_ = this.systemTime_;
                objectCluster2.calibratedTimeStamp_ = this.calibratedTimeStamp_;
                onBuilt();
                return objectCluster2;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5722clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5734setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5712clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5715clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5736setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5702addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5727mergeFrom(Message message) {
                if (message instanceof ObjectCluster2) {
                    return mergeFrom((ObjectCluster2) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ObjectCluster2 objectCluster2) {
                if (objectCluster2 == ObjectCluster2.getDefaultInstance()) {
                    return this;
                }
                if (!objectCluster2.getName().isEmpty()) {
                    this.name_ = objectCluster2.name_;
                    onChanged();
                }
                if (!objectCluster2.getBluetoothAddress().isEmpty()) {
                    this.bluetoothAddress_ = objectCluster2.bluetoothAddress_;
                    onChanged();
                }
                if (objectCluster2.communicationType_ != 0) {
                    setCommunicationTypeValue(objectCluster2.getCommunicationTypeValue());
                }
                internalGetMutableDataMap().mergeFrom(objectCluster2.internalGetDataMap());
                if (objectCluster2.getSystemTime() != 0) {
                    setSystemTime(objectCluster2.getSystemTime());
                }
                if (objectCluster2.getCalibratedTimeStamp() != 0.0d) {
                    setCalibratedTimeStamp(objectCluster2.getCalibratedTimeStamp());
                }
                m5732mergeUnknownFields(objectCluster2.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.Builder m5728mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.m5693$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$ObjectCluster2 r3 = (com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$ObjectCluster2 r4 = (com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.Builder.m5728mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$ObjectCluster2$Builder");
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
            public String getName() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.name_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setName(String str) {
                str.getClass();
                this.name_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.name_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setNameBytes(ByteString byteString) {
                byteString.getClass();
                ObjectCluster2.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = ObjectCluster2.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
            public String getBluetoothAddress() {
                Object obj = this.bluetoothAddress_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.bluetoothAddress_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setBluetoothAddress(String str) {
                str.getClass();
                this.bluetoothAddress_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
            public ByteString getBluetoothAddressBytes() {
                Object obj = this.bluetoothAddress_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bluetoothAddress_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setBluetoothAddressBytes(ByteString byteString) {
                byteString.getClass();
                ObjectCluster2.checkByteStringIsUtf8(byteString);
                this.bluetoothAddress_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearBluetoothAddress() {
                this.bluetoothAddress_ = ObjectCluster2.getDefaultInstance().getBluetoothAddress();
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
            public CommunicationType getCommunicationType() {
                CommunicationType communicationTypeValueOf = CommunicationType.valueOf(this.communicationType_);
                return communicationTypeValueOf == null ? CommunicationType.UNRECOGNIZED : communicationTypeValueOf;
            }

            public Builder setCommunicationType(CommunicationType communicationType) {
                communicationType.getClass();
                this.communicationType_ = communicationType.getNumber();
                onChanged();
                return this;
            }

            public Builder clearCommunicationType() {
                this.communicationType_ = 0;
                onChanged();
                return this;
            }

            private MapField<String, FormatCluster2> internalGetDataMap() {
                MapField<String, FormatCluster2> mapField = this.dataMap_;
                return mapField == null ? MapField.emptyMapField(DataMapDefaultEntryHolder.defaultEntry) : mapField;
            }

            private MapField<String, FormatCluster2> internalGetMutableDataMap() {
                onChanged();
                if (this.dataMap_ == null) {
                    this.dataMap_ = MapField.newMapField(DataMapDefaultEntryHolder.defaultEntry);
                }
                if (!this.dataMap_.isMutable()) {
                    this.dataMap_ = this.dataMap_.copy();
                }
                return this.dataMap_;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
            public int getDataMapCount() {
                return internalGetDataMap().getMap().size();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
            public boolean containsDataMap(String str) {
                str.getClass();
                return internalGetDataMap().getMap().containsKey(str);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
            @Deprecated
            public Map<String, FormatCluster2> getDataMap() {
                return getDataMapMap();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
            public Map<String, FormatCluster2> getDataMapMap() {
                return internalGetDataMap().getMap();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
            public FormatCluster2 getDataMapOrDefault(String str, FormatCluster2 formatCluster2) {
                str.getClass();
                Map map = internalGetDataMap().getMap();
                return map.containsKey(str) ? (FormatCluster2) map.get(str) : formatCluster2;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2OrBuilder
            public FormatCluster2 getDataMapOrThrow(String str) {
                str.getClass();
                Map map = internalGetDataMap().getMap();
                if (!map.containsKey(str)) {
                    throw new IllegalArgumentException();
                }
                return (FormatCluster2) map.get(str);
            }

            public Builder clearDataMap() {
                internalGetMutableDataMap().getMutableMap().clear();
                return this;
            }

            public Builder removeDataMap(String str) {
                str.getClass();
                internalGetMutableDataMap().getMutableMap().remove(str);
                return this;
            }

            @Deprecated
            public Map<String, FormatCluster2> getMutableDataMap() {
                return internalGetMutableDataMap().getMutableMap();
            }

            public Builder putDataMap(String str, FormatCluster2 formatCluster2) {
                str.getClass();
                formatCluster2.getClass();
                internalGetMutableDataMap().getMutableMap().put(str, formatCluster2);
                return this;
            }

            public Builder putAllDataMap(Map<String, FormatCluster2> map) {
                internalGetMutableDataMap().getMutableMap().putAll(map);
                return this;
            }

            public Builder clearSystemTime() {
                this.systemTime_ = 0L;
                onChanged();
                return this;
            }

            public Builder clearCalibratedTimeStamp() {
                this.calibratedTimeStamp_ = 0.0d;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5738setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5732mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class StringMsg extends GeneratedMessageV3 implements StringMsgOrBuilder {
        public static final int MESSAGE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final StringMsg DEFAULT_INSTANCE = new StringMsg();
        private static final Parser<StringMsg> PARSER = new AbstractParser<StringMsg>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.StringMsg.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public StringMsg m6190parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new StringMsg(codedInputStream, extensionRegistryLite);
            }
        };
        private byte memoizedIsInitialized;
        private volatile Object message_;

        private StringMsg(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private StringMsg() {
            this.memoizedIsInitialized = (byte) -1;
            this.message_ = "";
        }

        private StringMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.message_ = codedInputStream.readStringRequireUtf8();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static StringMsg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StringMsg> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_StringMsg_descriptor;
        }

        public static StringMsg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (StringMsg) PARSER.parseFrom(byteBuffer);
        }

        public static StringMsg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StringMsg) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static StringMsg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StringMsg) PARSER.parseFrom(byteString);
        }

        public static StringMsg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StringMsg) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static StringMsg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StringMsg) PARSER.parseFrom(bArr);
        }

        public static StringMsg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StringMsg) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static StringMsg parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static StringMsg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static StringMsg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static StringMsg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static StringMsg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static StringMsg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m6188toBuilder();
        }

        public static Builder newBuilder(StringMsg stringMsg) {
            return DEFAULT_INSTANCE.m6188toBuilder().mergeFrom(stringMsg);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public StringMsg m6183getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<StringMsg> getParserForType() {
            return PARSER;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new StringMsg();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_StringMsg_fieldAccessorTable.ensureFieldAccessorsInitialized(StringMsg.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.StringMsgOrBuilder
        public String getMessage() {
            Object obj = this.message_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.message_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.StringMsgOrBuilder
        public ByteString getMessageBytes() {
            Object obj = this.message_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.message_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getMessageBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.message_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (!getMessageBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.message_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeStringSize;
            return iComputeStringSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof StringMsg)) {
                return super.equals(obj);
            }
            StringMsg stringMsg = (StringMsg) obj;
            return getMessage().equals(stringMsg.getMessage()) && this.unknownFields.equals(stringMsg.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getMessage().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6185newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6188toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StringMsgOrBuilder {
            private Object message_;

            private Builder() {
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_StringMsg_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_StringMsg_fieldAccessorTable.ensureFieldAccessorsInitialized(StringMsg.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = StringMsg.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6199clear() {
                super.clear();
                this.message_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_StringMsg_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public StringMsg m6212getDefaultInstanceForType() {
                return StringMsg.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public StringMsg m6193build() throws UninitializedMessageException {
                StringMsg stringMsgM6195buildPartial = m6195buildPartial();
                if (stringMsgM6195buildPartial.isInitialized()) {
                    return stringMsgM6195buildPartial;
                }
                throw newUninitializedMessageException(stringMsgM6195buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public StringMsg m6195buildPartial() {
                StringMsg stringMsg = new StringMsg(this);
                stringMsg.message_ = this.message_;
                onBuilt();
                return stringMsg;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6211clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6223setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6201clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6204clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6225setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6191addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6216mergeFrom(Message message) {
                if (message instanceof StringMsg) {
                    return mergeFrom((StringMsg) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(StringMsg stringMsg) {
                if (stringMsg == StringMsg.getDefaultInstance()) {
                    return this;
                }
                if (!stringMsg.getMessage().isEmpty()) {
                    this.message_ = stringMsg.message_;
                    onChanged();
                }
                m6221mergeUnknownFields(stringMsg.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.StringMsg.Builder m6217mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.StringMsg.m6182$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$StringMsg r3 = (com.shimmerresearch.grpc.ShimmerGRPC.StringMsg) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$StringMsg r4 = (com.shimmerresearch.grpc.ShimmerGRPC.StringMsg) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.StringMsg.Builder.m6217mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$StringMsg$Builder");
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.StringMsgOrBuilder
            public String getMessage() {
                Object obj = this.message_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.message_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setMessage(String str) {
                str.getClass();
                this.message_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.StringMsgOrBuilder
            public ByteString getMessageBytes() {
                Object obj = this.message_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.message_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMessageBytes(ByteString byteString) {
                byteString.getClass();
                StringMsg.checkByteStringIsUtf8(byteString);
                this.message_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearMessage() {
                this.message_ = StringMsg.getDefaultInstance().getMessage();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m6227setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m6221mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class StringArrayMsg extends GeneratedMessageV3 implements StringArrayMsgOrBuilder {
        public static final int MESSAGEARRAY_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final StringArrayMsg DEFAULT_INSTANCE = new StringArrayMsg();
        private static final Parser<StringArrayMsg> PARSER = new AbstractParser<StringArrayMsg>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.StringArrayMsg.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public StringArrayMsg m6141parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new StringArrayMsg(codedInputStream, extensionRegistryLite);
            }
        };
        private byte memoizedIsInitialized;
        private LazyStringList messageArray_;

        private StringArrayMsg(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private StringArrayMsg() {
            this.memoizedIsInitialized = (byte) -1;
            this.messageArray_ = LazyStringArrayList.EMPTY;
        }

        private StringArrayMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if (!(z2 & true)) {
                                    this.messageArray_ = new LazyStringArrayList();
                                    z2 |= true;
                                }
                                this.messageArray_.add(stringRequireUtf8);
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.messageArray_ = this.messageArray_.getUnmodifiableView();
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static StringArrayMsg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StringArrayMsg> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_StringArrayMsg_descriptor;
        }

        public static StringArrayMsg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (StringArrayMsg) PARSER.parseFrom(byteBuffer);
        }

        public static StringArrayMsg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StringArrayMsg) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static StringArrayMsg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StringArrayMsg) PARSER.parseFrom(byteString);
        }

        public static StringArrayMsg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StringArrayMsg) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static StringArrayMsg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StringArrayMsg) PARSER.parseFrom(bArr);
        }

        public static StringArrayMsg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StringArrayMsg) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static StringArrayMsg parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static StringArrayMsg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static StringArrayMsg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static StringArrayMsg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static StringArrayMsg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static StringArrayMsg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m6139toBuilder();
        }

        public static Builder newBuilder(StringArrayMsg stringArrayMsg) {
            return DEFAULT_INSTANCE.m6139toBuilder().mergeFrom(stringArrayMsg);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public StringArrayMsg m6133getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.StringArrayMsgOrBuilder
        /* renamed from: getMessageArrayList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo6135getMessageArrayList() {
            return this.messageArray_;
        }

        public Parser<StringArrayMsg> getParserForType() {
            return PARSER;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new StringArrayMsg();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_StringArrayMsg_fieldAccessorTable.ensureFieldAccessorsInitialized(StringArrayMsg.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.StringArrayMsgOrBuilder
        public int getMessageArrayCount() {
            return this.messageArray_.size();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.StringArrayMsgOrBuilder
        public String getMessageArray(int i) {
            return (String) this.messageArray_.get(i);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.StringArrayMsgOrBuilder
        public ByteString getMessageArrayBytes(int i) {
            return this.messageArray_.getByteString(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.messageArray_.size(); i++) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.messageArray_.getRaw(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSizeNoTag = 0;
            for (int i2 = 0; i2 < this.messageArray_.size(); i2++) {
                iComputeStringSizeNoTag += computeStringSizeNoTag(this.messageArray_.getRaw(i2));
            }
            int size = iComputeStringSizeNoTag + mo6135getMessageArrayList().size() + this.unknownFields.getSerializedSize();
            this.memoizedSize = size;
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof StringArrayMsg)) {
                return super.equals(obj);
            }
            StringArrayMsg stringArrayMsg = (StringArrayMsg) obj;
            return mo6135getMessageArrayList().equals(stringArrayMsg.mo6135getMessageArrayList()) && this.unknownFields.equals(stringArrayMsg.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (getMessageArrayCount() > 0) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + mo6135getMessageArrayList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6136newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m6139toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StringArrayMsgOrBuilder {
            private int bitField0_;
            private LazyStringList messageArray_;

            private Builder() {
                this.messageArray_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.messageArray_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_StringArrayMsg_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_StringArrayMsg_fieldAccessorTable.ensureFieldAccessorsInitialized(StringArrayMsg.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = StringArrayMsg.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6150clear() {
                super.clear();
                this.messageArray_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_StringArrayMsg_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public StringArrayMsg m6163getDefaultInstanceForType() {
                return StringArrayMsg.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public StringArrayMsg m6144build() throws UninitializedMessageException {
                StringArrayMsg stringArrayMsgM6146buildPartial = m6146buildPartial();
                if (stringArrayMsgM6146buildPartial.isInitialized()) {
                    return stringArrayMsgM6146buildPartial;
                }
                throw newUninitializedMessageException(stringArrayMsgM6146buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public StringArrayMsg m6146buildPartial() {
                StringArrayMsg stringArrayMsg = new StringArrayMsg(this);
                if ((this.bitField0_ & 1) != 0) {
                    this.messageArray_ = this.messageArray_.getUnmodifiableView();
                    this.bitField0_ &= -2;
                }
                stringArrayMsg.messageArray_ = this.messageArray_;
                onBuilt();
                return stringArrayMsg;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6162clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6174setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6152clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6155clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6176setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6142addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6167mergeFrom(Message message) {
                if (message instanceof StringArrayMsg) {
                    return mergeFrom((StringArrayMsg) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(StringArrayMsg stringArrayMsg) {
                if (stringArrayMsg == StringArrayMsg.getDefaultInstance()) {
                    return this;
                }
                if (!stringArrayMsg.messageArray_.isEmpty()) {
                    if (this.messageArray_.isEmpty()) {
                        this.messageArray_ = stringArrayMsg.messageArray_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureMessageArrayIsMutable();
                        this.messageArray_.addAll(stringArrayMsg.messageArray_);
                    }
                    onChanged();
                }
                m6172mergeUnknownFields(stringArrayMsg.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.StringArrayMsg.Builder m6168mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.StringArrayMsg.m6132$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$StringArrayMsg r3 = (com.shimmerresearch.grpc.ShimmerGRPC.StringArrayMsg) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$StringArrayMsg r4 = (com.shimmerresearch.grpc.ShimmerGRPC.StringArrayMsg) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.StringArrayMsg.Builder.m6168mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$StringArrayMsg$Builder");
            }

            private void ensureMessageArrayIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.messageArray_ = new LazyStringArrayList(this.messageArray_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.StringArrayMsgOrBuilder
            /* renamed from: getMessageArrayList, reason: merged with bridge method [inline-methods] */
            public ProtocolStringList mo6135getMessageArrayList() {
                return this.messageArray_.getUnmodifiableView();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.StringArrayMsgOrBuilder
            public int getMessageArrayCount() {
                return this.messageArray_.size();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.StringArrayMsgOrBuilder
            public String getMessageArray(int i) {
                return (String) this.messageArray_.get(i);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.StringArrayMsgOrBuilder
            public ByteString getMessageArrayBytes(int i) {
                return this.messageArray_.getByteString(i);
            }

            public Builder setMessageArray(int i, String str) {
                str.getClass();
                ensureMessageArrayIsMutable();
                this.messageArray_.set(i, str);
                onChanged();
                return this;
            }

            public Builder addMessageArray(String str) {
                str.getClass();
                ensureMessageArrayIsMutable();
                this.messageArray_.add(str);
                onChanged();
                return this;
            }

            public Builder addAllMessageArray(Iterable<String> iterable) {
                ensureMessageArrayIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.messageArray_);
                onChanged();
                return this;
            }

            public Builder clearMessageArray() {
                this.messageArray_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                onChanged();
                return this;
            }

            public Builder addMessageArrayBytes(ByteString byteString) {
                byteString.getClass();
                StringArrayMsg.checkByteStringIsUtf8(byteString);
                ensureMessageArrayIsMutable();
                this.messageArray_.add(byteString);
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m6178setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m6172mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class BoolMsg extends GeneratedMessageV3 implements BoolMsgOrBuilder {
        public static final int STATE_FIELD_NUMBER = 1;
        private static final BoolMsg DEFAULT_INSTANCE = new BoolMsg();
        private static final Parser<BoolMsg> PARSER = new AbstractParser<BoolMsg>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.BoolMsg.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public BoolMsg m5077parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new BoolMsg(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private boolean state_;

        private BoolMsg(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private BoolMsg() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private BoolMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 8) {
                                this.state_ = codedInputStream.readBool();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static BoolMsg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BoolMsg> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_BoolMsg_descriptor;
        }

        public static BoolMsg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (BoolMsg) PARSER.parseFrom(byteBuffer);
        }

        public static BoolMsg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BoolMsg) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static BoolMsg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (BoolMsg) PARSER.parseFrom(byteString);
        }

        public static BoolMsg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BoolMsg) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static BoolMsg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (BoolMsg) PARSER.parseFrom(bArr);
        }

        public static BoolMsg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BoolMsg) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static BoolMsg parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static BoolMsg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BoolMsg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static BoolMsg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BoolMsg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static BoolMsg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m5075toBuilder();
        }

        public static Builder newBuilder(BoolMsg boolMsg) {
            return DEFAULT_INSTANCE.m5075toBuilder().mergeFrom(boolMsg);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public BoolMsg m5070getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<BoolMsg> getParserForType() {
            return PARSER;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.BoolMsgOrBuilder
        public boolean getState() {
            return this.state_;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new BoolMsg();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_BoolMsg_fieldAccessorTable.ensureFieldAccessorsInitialized(BoolMsg.class, Builder.class);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.state_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            boolean z = this.state_;
            int iComputeBoolSize = (z ? CodedOutputStream.computeBoolSize(1, z) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeBoolSize;
            return iComputeBoolSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof BoolMsg)) {
                return super.equals(obj);
            }
            BoolMsg boolMsg = (BoolMsg) obj;
            return getState() == boolMsg.getState() && this.unknownFields.equals(boolMsg.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getState())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5072newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5075toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BoolMsgOrBuilder {
            private boolean state_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_BoolMsg_descriptor;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BoolMsgOrBuilder
            public boolean getState() {
                return this.state_;
            }

            public Builder setState(boolean z) {
                this.state_ = z;
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_BoolMsg_fieldAccessorTable.ensureFieldAccessorsInitialized(BoolMsg.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = BoolMsg.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5086clear() {
                super.clear();
                this.state_ = false;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_BoolMsg_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public BoolMsg m5099getDefaultInstanceForType() {
                return BoolMsg.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public BoolMsg m5080build() throws UninitializedMessageException {
                BoolMsg boolMsgM5082buildPartial = m5082buildPartial();
                if (boolMsgM5082buildPartial.isInitialized()) {
                    return boolMsgM5082buildPartial;
                }
                throw newUninitializedMessageException(boolMsgM5082buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public BoolMsg m5082buildPartial() {
                BoolMsg boolMsg = new BoolMsg(this);
                boolMsg.state_ = this.state_;
                onBuilt();
                return boolMsg;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5098clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5110setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5091clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5112setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5078addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5103mergeFrom(Message message) {
                if (message instanceof BoolMsg) {
                    return mergeFrom((BoolMsg) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(BoolMsg boolMsg) {
                if (boolMsg == BoolMsg.getDefaultInstance()) {
                    return this;
                }
                if (boolMsg.getState()) {
                    setState(boolMsg.getState());
                }
                m5108mergeUnknownFields(boolMsg.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.BoolMsg.Builder m5104mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.BoolMsg.m5069$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$BoolMsg r3 = (com.shimmerresearch.grpc.ShimmerGRPC.BoolMsg) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$BoolMsg r4 = (com.shimmerresearch.grpc.ShimmerGRPC.BoolMsg) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.BoolMsg.Builder.m5104mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$BoolMsg$Builder");
            }

            public Builder clearState() {
                this.state_ = false;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5114setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5108mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class DoubleMsg extends GeneratedMessageV3 implements DoubleMsgOrBuilder {
        public static final int NUMBER_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final DoubleMsg DEFAULT_INSTANCE = new DoubleMsg();
        private static final Parser<DoubleMsg> PARSER = new AbstractParser<DoubleMsg>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.DoubleMsg.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public DoubleMsg m5229parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new DoubleMsg(codedInputStream, extensionRegistryLite);
            }
        };
        private byte memoizedIsInitialized;
        private double number_;

        private DoubleMsg(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private DoubleMsg() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private DoubleMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 9) {
                                this.number_ = codedInputStream.readDouble();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static DoubleMsg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DoubleMsg> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_DoubleMsg_descriptor;
        }

        public static DoubleMsg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (DoubleMsg) PARSER.parseFrom(byteBuffer);
        }

        public static DoubleMsg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DoubleMsg) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static DoubleMsg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DoubleMsg) PARSER.parseFrom(byteString);
        }

        public static DoubleMsg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DoubleMsg) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static DoubleMsg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DoubleMsg) PARSER.parseFrom(bArr);
        }

        public static DoubleMsg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DoubleMsg) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static DoubleMsg parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static DoubleMsg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DoubleMsg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static DoubleMsg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DoubleMsg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static DoubleMsg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m5227toBuilder();
        }

        public static Builder newBuilder(DoubleMsg doubleMsg) {
            return DEFAULT_INSTANCE.m5227toBuilder().mergeFrom(doubleMsg);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DoubleMsg m5222getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.DoubleMsgOrBuilder
        public double getNumber() {
            return this.number_;
        }

        public Parser<DoubleMsg> getParserForType() {
            return PARSER;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new DoubleMsg();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_DoubleMsg_fieldAccessorTable.ensureFieldAccessorsInitialized(DoubleMsg.class, Builder.class);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            double d = this.number_;
            if (d != 0.0d) {
                codedOutputStream.writeDouble(1, d);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            double d = this.number_;
            int iComputeDoubleSize = (d != 0.0d ? CodedOutputStream.computeDoubleSize(1, d) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeDoubleSize;
            return iComputeDoubleSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DoubleMsg)) {
                return super.equals(obj);
            }
            DoubleMsg doubleMsg = (DoubleMsg) obj;
            return Double.doubleToLongBits(getNumber()) == Double.doubleToLongBits(doubleMsg.getNumber()) && this.unknownFields.equals(doubleMsg.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getNumber()))) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5224newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5227toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DoubleMsgOrBuilder {
            private double number_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_DoubleMsg_descriptor;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.DoubleMsgOrBuilder
            public double getNumber() {
                return this.number_;
            }

            public Builder setNumber(double d) {
                this.number_ = d;
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_DoubleMsg_fieldAccessorTable.ensureFieldAccessorsInitialized(DoubleMsg.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = DoubleMsg.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5238clear() {
                super.clear();
                this.number_ = 0.0d;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_DoubleMsg_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public DoubleMsg m5251getDefaultInstanceForType() {
                return DoubleMsg.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public DoubleMsg m5232build() throws UninitializedMessageException {
                DoubleMsg doubleMsgM5234buildPartial = m5234buildPartial();
                if (doubleMsgM5234buildPartial.isInitialized()) {
                    return doubleMsgM5234buildPartial;
                }
                throw newUninitializedMessageException(doubleMsgM5234buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public DoubleMsg m5234buildPartial() {
                DoubleMsg doubleMsg = new DoubleMsg(this);
                doubleMsg.number_ = this.number_;
                onBuilt();
                return doubleMsg;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5250clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5262setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5240clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5243clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5264setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5230addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5255mergeFrom(Message message) {
                if (message instanceof DoubleMsg) {
                    return mergeFrom((DoubleMsg) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(DoubleMsg doubleMsg) {
                if (doubleMsg == DoubleMsg.getDefaultInstance()) {
                    return this;
                }
                if (doubleMsg.getNumber() != 0.0d) {
                    setNumber(doubleMsg.getNumber());
                }
                m5260mergeUnknownFields(doubleMsg.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.DoubleMsg.Builder m5256mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.DoubleMsg.m5221$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$DoubleMsg r3 = (com.shimmerresearch.grpc.ShimmerGRPC.DoubleMsg) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$DoubleMsg r4 = (com.shimmerresearch.grpc.ShimmerGRPC.DoubleMsg) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.DoubleMsg.Builder.m5256mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$DoubleMsg$Builder");
            }

            public Builder clearNumber() {
                this.number_ = 0.0d;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5266setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5260mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class OperationRequest extends GeneratedMessageV3 implements OperationRequestOrBuilder {
        public static final int ISFINISHED_FIELD_NUMBER = 1;
        public static final int ISSUCCESS_FIELD_NUMBER = 2;
        public static final int MESSAGE_FIELD_NUMBER = 3;
        public static final int PROGRESSPERCENTAGEPARSED_FIELD_NUMBER = 5;
        public static final int PROGRESSPERCENTAGE_FIELD_NUMBER = 4;
        private static final long serialVersionUID = 0;
        private static final OperationRequest DEFAULT_INSTANCE = new OperationRequest();
        private static final Parser<OperationRequest> PARSER = new AbstractParser<OperationRequest>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.OperationRequest.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public OperationRequest m5858parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new OperationRequest(codedInputStream, extensionRegistryLite);
            }
        };
        private boolean isFinished_;
        private boolean isSuccess_;
        private byte memoizedIsInitialized;
        private volatile Object message_;
        private volatile Object progressPercentageParsed_;
        private double progressPercentage_;

        private OperationRequest(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private OperationRequest() {
            this.memoizedIsInitialized = (byte) -1;
            this.message_ = "";
            this.progressPercentageParsed_ = "";
        }

        private OperationRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 8) {
                                    this.isFinished_ = codedInputStream.readBool();
                                } else if (tag == 16) {
                                    this.isSuccess_ = codedInputStream.readBool();
                                } else if (tag == 26) {
                                    this.message_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 33) {
                                    this.progressPercentage_ = codedInputStream.readDouble();
                                } else if (tag == 42) {
                                    this.progressPercentageParsed_ = codedInputStream.readStringRequireUtf8();
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        }
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static OperationRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<OperationRequest> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_OperationRequest_descriptor;
        }

        public static OperationRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (OperationRequest) PARSER.parseFrom(byteBuffer);
        }

        public static OperationRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (OperationRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static OperationRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (OperationRequest) PARSER.parseFrom(byteString);
        }

        public static OperationRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (OperationRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static OperationRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (OperationRequest) PARSER.parseFrom(bArr);
        }

        public static OperationRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (OperationRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static OperationRequest parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static OperationRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static OperationRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static OperationRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static OperationRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static OperationRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m5856toBuilder();
        }

        public static Builder newBuilder(OperationRequest operationRequest) {
            return DEFAULT_INSTANCE.m5856toBuilder().mergeFrom(operationRequest);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public OperationRequest m5851getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.OperationRequestOrBuilder
        public boolean getIsFinished() {
            return this.isFinished_;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.OperationRequestOrBuilder
        public boolean getIsSuccess() {
            return this.isSuccess_;
        }

        public Parser<OperationRequest> getParserForType() {
            return PARSER;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.OperationRequestOrBuilder
        public double getProgressPercentage() {
            return this.progressPercentage_;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new OperationRequest();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_OperationRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(OperationRequest.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.OperationRequestOrBuilder
        public String getMessage() {
            Object obj = this.message_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.message_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.OperationRequestOrBuilder
        public ByteString getMessageBytes() {
            Object obj = this.message_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.message_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.OperationRequestOrBuilder
        public String getProgressPercentageParsed() {
            Object obj = this.progressPercentageParsed_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.progressPercentageParsed_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.OperationRequestOrBuilder
        public ByteString getProgressPercentageParsedBytes() {
            Object obj = this.progressPercentageParsed_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.progressPercentageParsed_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.isFinished_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            boolean z2 = this.isSuccess_;
            if (z2) {
                codedOutputStream.writeBool(2, z2);
            }
            if (!getMessageBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.message_);
            }
            double d = this.progressPercentage_;
            if (d != 0.0d) {
                codedOutputStream.writeDouble(4, d);
            }
            if (!getProgressPercentageParsedBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 5, this.progressPercentageParsed_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            boolean z = this.isFinished_;
            int iComputeBoolSize = z ? CodedOutputStream.computeBoolSize(1, z) : 0;
            boolean z2 = this.isSuccess_;
            if (z2) {
                iComputeBoolSize += CodedOutputStream.computeBoolSize(2, z2);
            }
            if (!getMessageBytes().isEmpty()) {
                iComputeBoolSize += GeneratedMessageV3.computeStringSize(3, this.message_);
            }
            double d = this.progressPercentage_;
            if (d != 0.0d) {
                iComputeBoolSize += CodedOutputStream.computeDoubleSize(4, d);
            }
            if (!getProgressPercentageParsedBytes().isEmpty()) {
                iComputeBoolSize += GeneratedMessageV3.computeStringSize(5, this.progressPercentageParsed_);
            }
            int serializedSize = iComputeBoolSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof OperationRequest)) {
                return super.equals(obj);
            }
            OperationRequest operationRequest = (OperationRequest) obj;
            return getIsFinished() == operationRequest.getIsFinished() && getIsSuccess() == operationRequest.getIsSuccess() && getMessage().equals(operationRequest.getMessage()) && Double.doubleToLongBits(getProgressPercentage()) == Double.doubleToLongBits(operationRequest.getProgressPercentage()) && getProgressPercentageParsed().equals(operationRequest.getProgressPercentageParsed()) && this.unknownFields.equals(operationRequest.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getIsFinished())) * 37) + 2) * 53) + Internal.hashBoolean(getIsSuccess())) * 37) + 3) * 53) + getMessage().hashCode()) * 37) + 4) * 53) + Internal.hashLong(Double.doubleToLongBits(getProgressPercentage()))) * 37) + 5) * 53) + getProgressPercentageParsed().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5853newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5856toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OperationRequestOrBuilder {
            private boolean isFinished_;
            private boolean isSuccess_;
            private Object message_;
            private Object progressPercentageParsed_;
            private double progressPercentage_;

            private Builder() {
                this.message_ = "";
                this.progressPercentageParsed_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.message_ = "";
                this.progressPercentageParsed_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_OperationRequest_descriptor;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.OperationRequestOrBuilder
            public boolean getIsFinished() {
                return this.isFinished_;
            }

            public Builder setIsFinished(boolean z) {
                this.isFinished_ = z;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.OperationRequestOrBuilder
            public boolean getIsSuccess() {
                return this.isSuccess_;
            }

            public Builder setIsSuccess(boolean z) {
                this.isSuccess_ = z;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.OperationRequestOrBuilder
            public double getProgressPercentage() {
                return this.progressPercentage_;
            }

            public Builder setProgressPercentage(double d) {
                this.progressPercentage_ = d;
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_OperationRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(OperationRequest.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = OperationRequest.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5867clear() {
                super.clear();
                this.isFinished_ = false;
                this.isSuccess_ = false;
                this.message_ = "";
                this.progressPercentage_ = 0.0d;
                this.progressPercentageParsed_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_OperationRequest_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public OperationRequest m5880getDefaultInstanceForType() {
                return OperationRequest.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public OperationRequest m5861build() throws UninitializedMessageException {
                OperationRequest operationRequestM5863buildPartial = m5863buildPartial();
                if (operationRequestM5863buildPartial.isInitialized()) {
                    return operationRequestM5863buildPartial;
                }
                throw newUninitializedMessageException(operationRequestM5863buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public OperationRequest m5863buildPartial() {
                OperationRequest operationRequest = new OperationRequest(this);
                operationRequest.isFinished_ = this.isFinished_;
                operationRequest.isSuccess_ = this.isSuccess_;
                operationRequest.message_ = this.message_;
                operationRequest.progressPercentage_ = this.progressPercentage_;
                operationRequest.progressPercentageParsed_ = this.progressPercentageParsed_;
                onBuilt();
                return operationRequest;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5879clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5891setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5869clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5872clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5893setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5859addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5884mergeFrom(Message message) {
                if (message instanceof OperationRequest) {
                    return mergeFrom((OperationRequest) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(OperationRequest operationRequest) {
                if (operationRequest == OperationRequest.getDefaultInstance()) {
                    return this;
                }
                if (operationRequest.getIsFinished()) {
                    setIsFinished(operationRequest.getIsFinished());
                }
                if (operationRequest.getIsSuccess()) {
                    setIsSuccess(operationRequest.getIsSuccess());
                }
                if (!operationRequest.getMessage().isEmpty()) {
                    this.message_ = operationRequest.message_;
                    onChanged();
                }
                if (operationRequest.getProgressPercentage() != 0.0d) {
                    setProgressPercentage(operationRequest.getProgressPercentage());
                }
                if (!operationRequest.getProgressPercentageParsed().isEmpty()) {
                    this.progressPercentageParsed_ = operationRequest.progressPercentageParsed_;
                    onChanged();
                }
                m5889mergeUnknownFields(operationRequest.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.OperationRequest.Builder m5885mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.OperationRequest.m5850$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$OperationRequest r3 = (com.shimmerresearch.grpc.ShimmerGRPC.OperationRequest) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$OperationRequest r4 = (com.shimmerresearch.grpc.ShimmerGRPC.OperationRequest) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.OperationRequest.Builder.m5885mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$OperationRequest$Builder");
            }

            public Builder clearIsFinished() {
                this.isFinished_ = false;
                onChanged();
                return this;
            }

            public Builder clearIsSuccess() {
                this.isSuccess_ = false;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.OperationRequestOrBuilder
            public String getMessage() {
                Object obj = this.message_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.message_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setMessage(String str) {
                str.getClass();
                this.message_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.OperationRequestOrBuilder
            public ByteString getMessageBytes() {
                Object obj = this.message_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.message_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMessageBytes(ByteString byteString) {
                byteString.getClass();
                OperationRequest.checkByteStringIsUtf8(byteString);
                this.message_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearMessage() {
                this.message_ = OperationRequest.getDefaultInstance().getMessage();
                onChanged();
                return this;
            }

            public Builder clearProgressPercentage() {
                this.progressPercentage_ = 0.0d;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.OperationRequestOrBuilder
            public String getProgressPercentageParsed() {
                Object obj = this.progressPercentageParsed_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.progressPercentageParsed_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setProgressPercentageParsed(String str) {
                str.getClass();
                this.progressPercentageParsed_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.OperationRequestOrBuilder
            public ByteString getProgressPercentageParsedBytes() {
                Object obj = this.progressPercentageParsed_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.progressPercentageParsed_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setProgressPercentageParsedBytes(ByteString byteString) {
                byteString.getClass();
                OperationRequest.checkByteStringIsUtf8(byteString);
                this.progressPercentageParsed_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearProgressPercentageParsed() {
                this.progressPercentageParsed_ = OperationRequest.getDefaultInstance().getProgressPercentageParsed();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5895setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5889mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class ShimmersInfo extends GeneratedMessageV3 implements ShimmersInfoOrBuilder {
        public static final int MESSAGE_FIELD_NUMBER = 2;
        public static final int SHIMMERMAP_FIELD_NUMBER = 3;
        public static final int STATE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final ShimmersInfo DEFAULT_INSTANCE = new ShimmersInfo();
        private static final Parser<ShimmersInfo> PARSER = new AbstractParser<ShimmersInfo>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public ShimmersInfo m5960parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ShimmersInfo(codedInputStream, extensionRegistryLite);
            }
        };
        private byte memoizedIsInitialized;
        private volatile Object message_;
        private MapField<String, ShimmerInfo> shimmerMap_;
        private boolean state_;

        private ShimmersInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ShimmersInfo() {
            this.memoizedIsInitialized = (byte) -1;
            this.message_ = "";
        }

        private ShimmersInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 8) {
                                this.state_ = codedInputStream.readBool();
                            } else if (tag == 18) {
                                this.message_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 26) {
                                if (!(z2 & true)) {
                                    this.shimmerMap_ = MapField.newMapField(ShimmerMapDefaultEntryHolder.defaultEntry);
                                    z2 |= true;
                                }
                                MapEntry message = codedInputStream.readMessage(ShimmerMapDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.shimmerMap_.getMutableMap().put((String) message.getKey(), (ShimmerInfo) message.getValue());
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static ShimmersInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ShimmersInfo> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_ShimmersInfo_descriptor;
        }

        public static ShimmersInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ShimmersInfo) PARSER.parseFrom(byteBuffer);
        }

        public static ShimmersInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ShimmersInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ShimmersInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ShimmersInfo) PARSER.parseFrom(byteString);
        }

        public static ShimmersInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ShimmersInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ShimmersInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ShimmersInfo) PARSER.parseFrom(bArr);
        }

        public static ShimmersInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ShimmersInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ShimmersInfo parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ShimmersInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ShimmersInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ShimmersInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ShimmersInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ShimmersInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m5958toBuilder();
        }

        public static Builder newBuilder(ShimmersInfo shimmersInfo) {
            return DEFAULT_INSTANCE.m5958toBuilder().mergeFrom(shimmersInfo);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ShimmersInfo m5953getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<ShimmersInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
        public boolean getState() {
            return this.state_;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new ShimmersInfo();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 3) {
                return internalGetShimmerMap();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_ShimmersInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ShimmersInfo.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
        public String getMessage() {
            Object obj = this.message_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.message_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
        public ByteString getMessageBytes() {
            Object obj = this.message_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.message_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MapField<String, ShimmerInfo> internalGetShimmerMap() {
            MapField<String, ShimmerInfo> mapField = this.shimmerMap_;
            return mapField == null ? MapField.emptyMapField(ShimmerMapDefaultEntryHolder.defaultEntry) : mapField;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
        public int getShimmerMapCount() {
            return internalGetShimmerMap().getMap().size();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
        public boolean containsShimmerMap(String str) {
            str.getClass();
            return internalGetShimmerMap().getMap().containsKey(str);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
        @Deprecated
        public Map<String, ShimmerInfo> getShimmerMap() {
            return getShimmerMapMap();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
        public Map<String, ShimmerInfo> getShimmerMapMap() {
            return internalGetShimmerMap().getMap();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
        public ShimmerInfo getShimmerMapOrDefault(String str, ShimmerInfo shimmerInfo) {
            str.getClass();
            Map map = internalGetShimmerMap().getMap();
            return map.containsKey(str) ? (ShimmerInfo) map.get(str) : shimmerInfo;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
        public ShimmerInfo getShimmerMapOrThrow(String str) {
            str.getClass();
            Map map = internalGetShimmerMap().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return (ShimmerInfo) map.get(str);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.state_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            if (!getMessageBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.message_);
            }
            GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetShimmerMap(), ShimmerMapDefaultEntryHolder.defaultEntry, 3);
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            boolean z = this.state_;
            int iComputeBoolSize = z ? CodedOutputStream.computeBoolSize(1, z) : 0;
            if (!getMessageBytes().isEmpty()) {
                iComputeBoolSize += GeneratedMessageV3.computeStringSize(2, this.message_);
            }
            for (Map.Entry entry : internalGetShimmerMap().getMap().entrySet()) {
                iComputeBoolSize += CodedOutputStream.computeMessageSize(3, ShimmerMapDefaultEntryHolder.defaultEntry.newBuilderForType().setKey((String) entry.getKey()).setValue((ShimmerInfo) entry.getValue()).build());
            }
            int serializedSize = iComputeBoolSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ShimmersInfo)) {
                return super.equals(obj);
            }
            ShimmersInfo shimmersInfo = (ShimmersInfo) obj;
            return getState() == shimmersInfo.getState() && getMessage().equals(shimmersInfo.getMessage()) && internalGetShimmerMap().equals(shimmersInfo.internalGetShimmerMap()) && this.unknownFields.equals(shimmersInfo.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getState())) * 37) + 2) * 53) + getMessage().hashCode();
            if (!internalGetShimmerMap().getMap().isEmpty()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + internalGetShimmerMap().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5955newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5958toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public interface ShimmerInfoOrBuilder extends MessageOrBuilder {
            double getBatteryPercentage();

            String getBatteryPercentageParsed();

            ByteString getBatteryPercentageParsedBytes();

            String getBluetoothAddress();

            ByteString getBluetoothAddressBytes();

            String getChargingStatusParsed();

            ByteString getChargingStatusParsedBytes();

            long getConfigTime();

            String getConfigTimeParsed();

            ByteString getConfigTimeParsedBytes();

            long getDriveCapacity();

            String getDriveCapacityParsed();

            ByteString getDriveCapacityParsedBytes();

            long getDriveSpaceFree();

            long getDriveSpaceUsed();

            String getExpBrdVersionParsed();

            ByteString getExpBrdVersionParsedBytes();

            String getFwVersionParsed();

            ByteString getFwVersionParsedBytes();

            String getHwVersionParsed();

            ByteString getHwVersionParsedBytes();

            Ieee802154Info getIeee802154Info();

            Ieee802154InfoOrBuilder getIeee802154InfoOrBuilder();

            boolean getIsRealTimeClockSet();

            long getLastReadRtcValueMilliSecs();

            String getLastReadRtcValueParsed();

            ByteString getLastReadRtcValueParsedBytes();

            String getName();

            ByteString getNameBytes();

            String getPairedDevice(int i);

            ByteString getPairedDeviceBytes(int i);

            int getPairedDeviceCount();

            /* renamed from: getPairedDeviceList */
            List<String> mo6036getPairedDeviceList();

            String getTrialName();

            ByteString getTrialNameBytes();

            String getUniqueId();

            ByteString getUniqueIdBytes();

            boolean hasIeee802154Info();
        }

        public static final class ShimmerInfo extends GeneratedMessageV3 implements ShimmerInfoOrBuilder {
            public static final int BATTERYPERCENTAGEPARSED_FIELD_NUMBER = 5;
            public static final int BATTERYPERCENTAGE_FIELD_NUMBER = 6;
            public static final int BLUETOOTHADDRESS_FIELD_NUMBER = 3;
            public static final int CHARGINGSTATUSPARSED_FIELD_NUMBER = 7;
            public static final int CONFIGTIMEPARSED_FIELD_NUMBER = 20;
            public static final int CONFIGTIME_FIELD_NUMBER = 19;
            public static final int DRIVECAPACITYPARSED_FIELD_NUMBER = 11;
            public static final int DRIVECAPACITY_FIELD_NUMBER = 8;
            public static final int DRIVESPACEFREE_FIELD_NUMBER = 10;
            public static final int DRIVESPACEUSED_FIELD_NUMBER = 9;
            public static final int EXPBRDVERSIONPARSED_FIELD_NUMBER = 16;
            public static final int FWVERSIONPARSED_FIELD_NUMBER = 17;
            public static final int HWVERSIONPARSED_FIELD_NUMBER = 15;
            public static final int IEEE802154INFO_FIELD_NUMBER = 21;
            public static final int ISREALTIMECLOCKSET_FIELD_NUMBER = 12;
            public static final int LASTREADRTCVALUEMILLISECS_FIELD_NUMBER = 13;
            public static final int LASTREADRTCVALUEPARSED_FIELD_NUMBER = 14;
            public static final int NAME_FIELD_NUMBER = 1;
            public static final int PAIREDDEVICE_FIELD_NUMBER = 18;
            public static final int TRIALNAME_FIELD_NUMBER = 4;
            public static final int UNIQUEID_FIELD_NUMBER = 2;
            private static final long serialVersionUID = 0;
            private static final ShimmerInfo DEFAULT_INSTANCE = new ShimmerInfo();
            private static final Parser<ShimmerInfo> PARSER = new AbstractParser<ShimmerInfo>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfo.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public ShimmerInfo m6042parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new ShimmerInfo(codedInputStream, extensionRegistryLite);
                }
            };
            private volatile Object batteryPercentageParsed_;
            private double batteryPercentage_;
            private volatile Object bluetoothAddress_;
            private volatile Object chargingStatusParsed_;
            private volatile Object configTimeParsed_;
            private long configTime_;
            private volatile Object driveCapacityParsed_;
            private long driveCapacity_;
            private long driveSpaceFree_;
            private long driveSpaceUsed_;
            private volatile Object expBrdVersionParsed_;
            private volatile Object fwVersionParsed_;
            private volatile Object hwVersionParsed_;
            private Ieee802154Info ieee802154Info_;
            private boolean isRealTimeClockSet_;
            private long lastReadRtcValueMilliSecs_;
            private volatile Object lastReadRtcValueParsed_;
            private byte memoizedIsInitialized;
            private volatile Object name_;
            private LazyStringList pairedDevice_;
            private volatile Object trialName_;
            private volatile Object uniqueId_;

            private ShimmerInfo(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private ShimmerInfo() {
                this.memoizedIsInitialized = (byte) -1;
                this.name_ = "";
                this.uniqueId_ = "";
                this.bluetoothAddress_ = "";
                this.trialName_ = "";
                this.batteryPercentageParsed_ = "";
                this.chargingStatusParsed_ = "";
                this.driveCapacityParsed_ = "";
                this.lastReadRtcValueParsed_ = "";
                this.hwVersionParsed_ = "";
                this.expBrdVersionParsed_ = "";
                this.fwVersionParsed_ = "";
                this.pairedDevice_ = LazyStringArrayList.EMPTY;
                this.configTimeParsed_ = "";
            }

            private ShimmerInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int tag = codedInputStream.readTag();
                                switch (tag) {
                                    case 0:
                                        z = true;
                                    case 10:
                                        this.name_ = codedInputStream.readStringRequireUtf8();
                                    case 18:
                                        this.uniqueId_ = codedInputStream.readStringRequireUtf8();
                                    case 26:
                                        this.bluetoothAddress_ = codedInputStream.readStringRequireUtf8();
                                    case 34:
                                        this.trialName_ = codedInputStream.readStringRequireUtf8();
                                    case 42:
                                        this.batteryPercentageParsed_ = codedInputStream.readStringRequireUtf8();
                                    case 49:
                                        this.batteryPercentage_ = codedInputStream.readDouble();
                                    case 58:
                                        this.chargingStatusParsed_ = codedInputStream.readStringRequireUtf8();
                                    case 64:
                                        this.driveCapacity_ = codedInputStream.readInt64();
                                    case 72:
                                        this.driveSpaceUsed_ = codedInputStream.readInt64();
                                    case 80:
                                        this.driveSpaceFree_ = codedInputStream.readInt64();
                                    case RESET_TO_DEFAULT_CONFIGURATION_COMMAND_VALUE:
                                        this.driveCapacityParsed_ = codedInputStream.readStringRequireUtf8();
                                    case GET_INTERNAL_EXP_POWER_ENABLE_COMMAND_VALUE:
                                        this.isRealTimeClockSet_ = codedInputStream.readBool();
                                    case 104:
                                        this.lastReadRtcValueMilliSecs_ = codedInputStream.readInt64();
                                    case 114:
                                        this.lastReadRtcValueParsed_ = codedInputStream.readStringRequireUtf8();
                                    case 122:
                                        this.hwVersionParsed_ = codedInputStream.readStringRequireUtf8();
                                    case 130:
                                        this.expBrdVersionParsed_ = codedInputStream.readStringRequireUtf8();
                                    case 138:
                                        this.fwVersionParsed_ = codedInputStream.readStringRequireUtf8();
                                    case 146:
                                        String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                        if (!(z2 & true)) {
                                            this.pairedDevice_ = new LazyStringArrayList();
                                            z2 |= true;
                                        }
                                        this.pairedDevice_.add(stringRequireUtf8);
                                    case 152:
                                        this.configTime_ = codedInputStream.readInt64();
                                    case 162:
                                        this.configTimeParsed_ = codedInputStream.readStringRequireUtf8();
                                    case 170:
                                        Ieee802154Info ieee802154Info = this.ieee802154Info_;
                                        Ieee802154Info.Builder builderM5534toBuilder = ieee802154Info != null ? ieee802154Info.m5534toBuilder() : null;
                                        Ieee802154Info ieee802154Info2 = (Ieee802154Info) codedInputStream.readMessage(Ieee802154Info.parser(), extensionRegistryLite);
                                        this.ieee802154Info_ = ieee802154Info2;
                                        if (builderM5534toBuilder != null) {
                                            builderM5534toBuilder.mergeFrom(ieee802154Info2);
                                            this.ieee802154Info_ = builderM5534toBuilder.m5541buildPartial();
                                        }
                                    default:
                                        if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                            z = true;
                                        }
                                }
                            } catch (InvalidProtocolBufferException e) {
                                throw e.setUnfinishedMessage(this);
                            }
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        if (z2 & true) {
                            this.pairedDevice_ = this.pairedDevice_.getUnmodifiableView();
                        }
                        this.unknownFields = builderNewBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static ShimmerInfo getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ShimmerInfo> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_ShimmersInfo_ShimmerInfo_descriptor;
            }

            public static ShimmerInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (ShimmerInfo) PARSER.parseFrom(byteBuffer);
            }

            public static ShimmerInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ShimmerInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static ShimmerInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (ShimmerInfo) PARSER.parseFrom(byteString);
            }

            public static ShimmerInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ShimmerInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static ShimmerInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (ShimmerInfo) PARSER.parseFrom(bArr);
            }

            public static ShimmerInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ShimmerInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static ShimmerInfo parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static ShimmerInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static ShimmerInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static ShimmerInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static ShimmerInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static ShimmerInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m6040toBuilder();
            }

            public static Builder newBuilder(ShimmerInfo shimmerInfo) {
                return DEFAULT_INSTANCE.m6040toBuilder().mergeFrom(shimmerInfo);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public double getBatteryPercentage() {
                return this.batteryPercentage_;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public long getConfigTime() {
                return this.configTime_;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ShimmerInfo m6034getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public long getDriveCapacity() {
                return this.driveCapacity_;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public long getDriveSpaceFree() {
                return this.driveSpaceFree_;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public long getDriveSpaceUsed() {
                return this.driveSpaceUsed_;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public boolean getIsRealTimeClockSet() {
                return this.isRealTimeClockSet_;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public long getLastReadRtcValueMilliSecs() {
                return this.lastReadRtcValueMilliSecs_;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            /* renamed from: getPairedDeviceList, reason: merged with bridge method [inline-methods] */
            public ProtocolStringList mo6036getPairedDeviceList() {
                return this.pairedDevice_;
            }

            public Parser<ShimmerInfo> getParserForType() {
                return PARSER;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public boolean hasIeee802154Info() {
                return this.ieee802154Info_ != null;
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new ShimmerInfo();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_ShimmersInfo_ShimmerInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ShimmerInfo.class, Builder.class);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public String getName() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.name_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public String getUniqueId() {
                Object obj = this.uniqueId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.uniqueId_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public ByteString getUniqueIdBytes() {
                Object obj = this.uniqueId_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.uniqueId_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public String getBluetoothAddress() {
                Object obj = this.bluetoothAddress_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.bluetoothAddress_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public ByteString getBluetoothAddressBytes() {
                Object obj = this.bluetoothAddress_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bluetoothAddress_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public String getTrialName() {
                Object obj = this.trialName_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.trialName_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public ByteString getTrialNameBytes() {
                Object obj = this.trialName_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.trialName_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public String getBatteryPercentageParsed() {
                Object obj = this.batteryPercentageParsed_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.batteryPercentageParsed_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public ByteString getBatteryPercentageParsedBytes() {
                Object obj = this.batteryPercentageParsed_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.batteryPercentageParsed_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public String getChargingStatusParsed() {
                Object obj = this.chargingStatusParsed_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.chargingStatusParsed_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public ByteString getChargingStatusParsedBytes() {
                Object obj = this.chargingStatusParsed_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.chargingStatusParsed_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public String getDriveCapacityParsed() {
                Object obj = this.driveCapacityParsed_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.driveCapacityParsed_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public ByteString getDriveCapacityParsedBytes() {
                Object obj = this.driveCapacityParsed_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.driveCapacityParsed_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public String getLastReadRtcValueParsed() {
                Object obj = this.lastReadRtcValueParsed_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.lastReadRtcValueParsed_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public ByteString getLastReadRtcValueParsedBytes() {
                Object obj = this.lastReadRtcValueParsed_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.lastReadRtcValueParsed_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public String getHwVersionParsed() {
                Object obj = this.hwVersionParsed_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.hwVersionParsed_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public ByteString getHwVersionParsedBytes() {
                Object obj = this.hwVersionParsed_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.hwVersionParsed_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public String getExpBrdVersionParsed() {
                Object obj = this.expBrdVersionParsed_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.expBrdVersionParsed_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public ByteString getExpBrdVersionParsedBytes() {
                Object obj = this.expBrdVersionParsed_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.expBrdVersionParsed_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public String getFwVersionParsed() {
                Object obj = this.fwVersionParsed_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.fwVersionParsed_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public ByteString getFwVersionParsedBytes() {
                Object obj = this.fwVersionParsed_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.fwVersionParsed_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public int getPairedDeviceCount() {
                return this.pairedDevice_.size();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public String getPairedDevice(int i) {
                return (String) this.pairedDevice_.get(i);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public ByteString getPairedDeviceBytes(int i) {
                return this.pairedDevice_.getByteString(i);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public String getConfigTimeParsed() {
                Object obj = this.configTimeParsed_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.configTimeParsed_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public ByteString getConfigTimeParsedBytes() {
                Object obj = this.configTimeParsed_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.configTimeParsed_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public Ieee802154Info getIeee802154Info() {
                Ieee802154Info ieee802154Info = this.ieee802154Info_;
                return ieee802154Info == null ? Ieee802154Info.getDefaultInstance() : ieee802154Info;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
            public Ieee802154InfoOrBuilder getIeee802154InfoOrBuilder() {
                return getIeee802154Info();
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (!getNameBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
                }
                if (!getUniqueIdBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.uniqueId_);
                }
                if (!getBluetoothAddressBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.bluetoothAddress_);
                }
                if (!getTrialNameBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 4, this.trialName_);
                }
                if (!getBatteryPercentageParsedBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 5, this.batteryPercentageParsed_);
                }
                double d = this.batteryPercentage_;
                if (d != 0.0d) {
                    codedOutputStream.writeDouble(6, d);
                }
                if (!getChargingStatusParsedBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 7, this.chargingStatusParsed_);
                }
                long j = this.driveCapacity_;
                if (j != 0) {
                    codedOutputStream.writeInt64(8, j);
                }
                long j2 = this.driveSpaceUsed_;
                if (j2 != 0) {
                    codedOutputStream.writeInt64(9, j2);
                }
                long j3 = this.driveSpaceFree_;
                if (j3 != 0) {
                    codedOutputStream.writeInt64(10, j3);
                }
                if (!getDriveCapacityParsedBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 11, this.driveCapacityParsed_);
                }
                boolean z = this.isRealTimeClockSet_;
                if (z) {
                    codedOutputStream.writeBool(12, z);
                }
                long j4 = this.lastReadRtcValueMilliSecs_;
                if (j4 != 0) {
                    codedOutputStream.writeInt64(13, j4);
                }
                if (!getLastReadRtcValueParsedBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 14, this.lastReadRtcValueParsed_);
                }
                if (!getHwVersionParsedBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 15, this.hwVersionParsed_);
                }
                if (!getExpBrdVersionParsedBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 16, this.expBrdVersionParsed_);
                }
                if (!getFwVersionParsedBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 17, this.fwVersionParsed_);
                }
                for (int i = 0; i < this.pairedDevice_.size(); i++) {
                    GeneratedMessageV3.writeString(codedOutputStream, 18, this.pairedDevice_.getRaw(i));
                }
                long j5 = this.configTime_;
                if (j5 != 0) {
                    codedOutputStream.writeInt64(19, j5);
                }
                if (!getConfigTimeParsedBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 20, this.configTimeParsed_);
                }
                if (this.ieee802154Info_ != null) {
                    codedOutputStream.writeMessage(21, getIeee802154Info());
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
                if (!getUniqueIdBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.uniqueId_);
                }
                if (!getBluetoothAddressBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.bluetoothAddress_);
                }
                if (!getTrialNameBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.trialName_);
                }
                if (!getBatteryPercentageParsedBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(5, this.batteryPercentageParsed_);
                }
                double d = this.batteryPercentage_;
                if (d != 0.0d) {
                    iComputeStringSize += CodedOutputStream.computeDoubleSize(6, d);
                }
                if (!getChargingStatusParsedBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(7, this.chargingStatusParsed_);
                }
                long j = this.driveCapacity_;
                if (j != 0) {
                    iComputeStringSize += CodedOutputStream.computeInt64Size(8, j);
                }
                long j2 = this.driveSpaceUsed_;
                if (j2 != 0) {
                    iComputeStringSize += CodedOutputStream.computeInt64Size(9, j2);
                }
                long j3 = this.driveSpaceFree_;
                if (j3 != 0) {
                    iComputeStringSize += CodedOutputStream.computeInt64Size(10, j3);
                }
                if (!getDriveCapacityParsedBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(11, this.driveCapacityParsed_);
                }
                boolean z = this.isRealTimeClockSet_;
                if (z) {
                    iComputeStringSize += CodedOutputStream.computeBoolSize(12, z);
                }
                long j4 = this.lastReadRtcValueMilliSecs_;
                if (j4 != 0) {
                    iComputeStringSize += CodedOutputStream.computeInt64Size(13, j4);
                }
                if (!getLastReadRtcValueParsedBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(14, this.lastReadRtcValueParsed_);
                }
                if (!getHwVersionParsedBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(15, this.hwVersionParsed_);
                }
                if (!getExpBrdVersionParsedBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(16, this.expBrdVersionParsed_);
                }
                if (!getFwVersionParsedBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(17, this.fwVersionParsed_);
                }
                int iComputeStringSizeNoTag = 0;
                for (int i2 = 0; i2 < this.pairedDevice_.size(); i2++) {
                    iComputeStringSizeNoTag += computeStringSizeNoTag(this.pairedDevice_.getRaw(i2));
                }
                int size = iComputeStringSize + iComputeStringSizeNoTag + (mo6036getPairedDeviceList().size() * 2);
                long j5 = this.configTime_;
                if (j5 != 0) {
                    size += CodedOutputStream.computeInt64Size(19, j5);
                }
                if (!getConfigTimeParsedBytes().isEmpty()) {
                    size += GeneratedMessageV3.computeStringSize(20, this.configTimeParsed_);
                }
                if (this.ieee802154Info_ != null) {
                    size += CodedOutputStream.computeMessageSize(21, getIeee802154Info());
                }
                int serializedSize = size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ShimmerInfo)) {
                    return super.equals(obj);
                }
                ShimmerInfo shimmerInfo = (ShimmerInfo) obj;
                if (getName().equals(shimmerInfo.getName()) && getUniqueId().equals(shimmerInfo.getUniqueId()) && getBluetoothAddress().equals(shimmerInfo.getBluetoothAddress()) && getTrialName().equals(shimmerInfo.getTrialName()) && getBatteryPercentageParsed().equals(shimmerInfo.getBatteryPercentageParsed()) && Double.doubleToLongBits(getBatteryPercentage()) == Double.doubleToLongBits(shimmerInfo.getBatteryPercentage()) && getChargingStatusParsed().equals(shimmerInfo.getChargingStatusParsed()) && getDriveCapacity() == shimmerInfo.getDriveCapacity() && getDriveSpaceUsed() == shimmerInfo.getDriveSpaceUsed() && getDriveSpaceFree() == shimmerInfo.getDriveSpaceFree() && getDriveCapacityParsed().equals(shimmerInfo.getDriveCapacityParsed()) && getIsRealTimeClockSet() == shimmerInfo.getIsRealTimeClockSet() && getLastReadRtcValueMilliSecs() == shimmerInfo.getLastReadRtcValueMilliSecs() && getLastReadRtcValueParsed().equals(shimmerInfo.getLastReadRtcValueParsed()) && getHwVersionParsed().equals(shimmerInfo.getHwVersionParsed()) && getExpBrdVersionParsed().equals(shimmerInfo.getExpBrdVersionParsed()) && getFwVersionParsed().equals(shimmerInfo.getFwVersionParsed()) && mo6036getPairedDeviceList().equals(shimmerInfo.mo6036getPairedDeviceList()) && getConfigTime() == shimmerInfo.getConfigTime() && getConfigTimeParsed().equals(shimmerInfo.getConfigTimeParsed()) && hasIeee802154Info() == shimmerInfo.hasIeee802154Info()) {
                    return (!hasIeee802154Info() || getIeee802154Info().equals(shimmerInfo.getIeee802154Info())) && this.unknownFields.equals(shimmerInfo.unknownFields);
                }
                return false;
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getUniqueId().hashCode()) * 37) + 3) * 53) + getBluetoothAddress().hashCode()) * 37) + 4) * 53) + getTrialName().hashCode()) * 37) + 5) * 53) + getBatteryPercentageParsed().hashCode()) * 37) + 6) * 53) + Internal.hashLong(Double.doubleToLongBits(getBatteryPercentage()))) * 37) + 7) * 53) + getChargingStatusParsed().hashCode()) * 37) + 8) * 53) + Internal.hashLong(getDriveCapacity())) * 37) + 9) * 53) + Internal.hashLong(getDriveSpaceUsed())) * 37) + 10) * 53) + Internal.hashLong(getDriveSpaceFree())) * 37) + 11) * 53) + getDriveCapacityParsed().hashCode()) * 37) + 12) * 53) + Internal.hashBoolean(getIsRealTimeClockSet())) * 37) + 13) * 53) + Internal.hashLong(getLastReadRtcValueMilliSecs())) * 37) + 14) * 53) + getLastReadRtcValueParsed().hashCode()) * 37) + 15) * 53) + getHwVersionParsed().hashCode()) * 37) + 16) * 53) + getExpBrdVersionParsed().hashCode()) * 37) + 17) * 53) + getFwVersionParsed().hashCode();
                if (getPairedDeviceCount() > 0) {
                    iHashCode = (((iHashCode * 37) + 18) * 53) + mo6036getPairedDeviceList().hashCode();
                }
                int iHashLong = (((((((iHashCode * 37) + 19) * 53) + Internal.hashLong(getConfigTime())) * 37) + 20) * 53) + getConfigTimeParsed().hashCode();
                if (hasIeee802154Info()) {
                    iHashLong = (((iHashLong * 37) + 21) * 53) + getIeee802154Info().hashCode();
                }
                int iHashCode2 = (iHashLong * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6037newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m6040toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ShimmerInfoOrBuilder {
                private Object batteryPercentageParsed_;
                private double batteryPercentage_;
                private int bitField0_;
                private Object bluetoothAddress_;
                private Object chargingStatusParsed_;
                private Object configTimeParsed_;
                private long configTime_;
                private Object driveCapacityParsed_;
                private long driveCapacity_;
                private long driveSpaceFree_;
                private long driveSpaceUsed_;
                private Object expBrdVersionParsed_;
                private Object fwVersionParsed_;
                private Object hwVersionParsed_;
                private SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> ieee802154InfoBuilder_;
                private Ieee802154Info ieee802154Info_;
                private boolean isRealTimeClockSet_;
                private long lastReadRtcValueMilliSecs_;
                private Object lastReadRtcValueParsed_;
                private Object name_;
                private LazyStringList pairedDevice_;
                private Object trialName_;
                private Object uniqueId_;

                private Builder() {
                    this.name_ = "";
                    this.uniqueId_ = "";
                    this.bluetoothAddress_ = "";
                    this.trialName_ = "";
                    this.batteryPercentageParsed_ = "";
                    this.chargingStatusParsed_ = "";
                    this.driveCapacityParsed_ = "";
                    this.lastReadRtcValueParsed_ = "";
                    this.hwVersionParsed_ = "";
                    this.expBrdVersionParsed_ = "";
                    this.fwVersionParsed_ = "";
                    this.pairedDevice_ = LazyStringArrayList.EMPTY;
                    this.configTimeParsed_ = "";
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.name_ = "";
                    this.uniqueId_ = "";
                    this.bluetoothAddress_ = "";
                    this.trialName_ = "";
                    this.batteryPercentageParsed_ = "";
                    this.chargingStatusParsed_ = "";
                    this.driveCapacityParsed_ = "";
                    this.lastReadRtcValueParsed_ = "";
                    this.hwVersionParsed_ = "";
                    this.expBrdVersionParsed_ = "";
                    this.fwVersionParsed_ = "";
                    this.pairedDevice_ = LazyStringArrayList.EMPTY;
                    this.configTimeParsed_ = "";
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_ShimmersInfo_ShimmerInfo_descriptor;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public double getBatteryPercentage() {
                    return this.batteryPercentage_;
                }

                public Builder setBatteryPercentage(double d) {
                    this.batteryPercentage_ = d;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public long getConfigTime() {
                    return this.configTime_;
                }

                public Builder setConfigTime(long j) {
                    this.configTime_ = j;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public long getDriveCapacity() {
                    return this.driveCapacity_;
                }

                public Builder setDriveCapacity(long j) {
                    this.driveCapacity_ = j;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public long getDriveSpaceFree() {
                    return this.driveSpaceFree_;
                }

                public Builder setDriveSpaceFree(long j) {
                    this.driveSpaceFree_ = j;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public long getDriveSpaceUsed() {
                    return this.driveSpaceUsed_;
                }

                public Builder setDriveSpaceUsed(long j) {
                    this.driveSpaceUsed_ = j;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public boolean getIsRealTimeClockSet() {
                    return this.isRealTimeClockSet_;
                }

                public Builder setIsRealTimeClockSet(boolean z) {
                    this.isRealTimeClockSet_ = z;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public long getLastReadRtcValueMilliSecs() {
                    return this.lastReadRtcValueMilliSecs_;
                }

                public Builder setLastReadRtcValueMilliSecs(long j) {
                    this.lastReadRtcValueMilliSecs_ = j;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public boolean hasIeee802154Info() {
                    return (this.ieee802154InfoBuilder_ == null && this.ieee802154Info_ == null) ? false : true;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_ShimmersInfo_ShimmerInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ShimmerInfo.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = ShimmerInfo.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m6051clear() {
                    super.clear();
                    this.name_ = "";
                    this.uniqueId_ = "";
                    this.bluetoothAddress_ = "";
                    this.trialName_ = "";
                    this.batteryPercentageParsed_ = "";
                    this.batteryPercentage_ = 0.0d;
                    this.chargingStatusParsed_ = "";
                    this.driveCapacity_ = 0L;
                    this.driveSpaceUsed_ = 0L;
                    this.driveSpaceFree_ = 0L;
                    this.driveCapacityParsed_ = "";
                    this.isRealTimeClockSet_ = false;
                    this.lastReadRtcValueMilliSecs_ = 0L;
                    this.lastReadRtcValueParsed_ = "";
                    this.hwVersionParsed_ = "";
                    this.expBrdVersionParsed_ = "";
                    this.fwVersionParsed_ = "";
                    this.pairedDevice_ = LazyStringArrayList.EMPTY;
                    this.bitField0_ &= -2;
                    this.configTime_ = 0L;
                    this.configTimeParsed_ = "";
                    if (this.ieee802154InfoBuilder_ == null) {
                        this.ieee802154Info_ = null;
                    } else {
                        this.ieee802154Info_ = null;
                        this.ieee802154InfoBuilder_ = null;
                    }
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_ShimmersInfo_ShimmerInfo_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public ShimmerInfo m6064getDefaultInstanceForType() {
                    return ShimmerInfo.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public ShimmerInfo m6045build() throws UninitializedMessageException {
                    ShimmerInfo shimmerInfoM6047buildPartial = m6047buildPartial();
                    if (shimmerInfoM6047buildPartial.isInitialized()) {
                        return shimmerInfoM6047buildPartial;
                    }
                    throw newUninitializedMessageException(shimmerInfoM6047buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public ShimmerInfo m6047buildPartial() {
                    ShimmerInfo shimmerInfo = new ShimmerInfo(this);
                    shimmerInfo.name_ = this.name_;
                    shimmerInfo.uniqueId_ = this.uniqueId_;
                    shimmerInfo.bluetoothAddress_ = this.bluetoothAddress_;
                    shimmerInfo.trialName_ = this.trialName_;
                    shimmerInfo.batteryPercentageParsed_ = this.batteryPercentageParsed_;
                    shimmerInfo.batteryPercentage_ = this.batteryPercentage_;
                    shimmerInfo.chargingStatusParsed_ = this.chargingStatusParsed_;
                    shimmerInfo.driveCapacity_ = this.driveCapacity_;
                    shimmerInfo.driveSpaceUsed_ = this.driveSpaceUsed_;
                    shimmerInfo.driveSpaceFree_ = this.driveSpaceFree_;
                    shimmerInfo.driveCapacityParsed_ = this.driveCapacityParsed_;
                    shimmerInfo.isRealTimeClockSet_ = this.isRealTimeClockSet_;
                    shimmerInfo.lastReadRtcValueMilliSecs_ = this.lastReadRtcValueMilliSecs_;
                    shimmerInfo.lastReadRtcValueParsed_ = this.lastReadRtcValueParsed_;
                    shimmerInfo.hwVersionParsed_ = this.hwVersionParsed_;
                    shimmerInfo.expBrdVersionParsed_ = this.expBrdVersionParsed_;
                    shimmerInfo.fwVersionParsed_ = this.fwVersionParsed_;
                    if ((this.bitField0_ & 1) != 0) {
                        this.pairedDevice_ = this.pairedDevice_.getUnmodifiableView();
                        this.bitField0_ &= -2;
                    }
                    shimmerInfo.pairedDevice_ = this.pairedDevice_;
                    shimmerInfo.configTime_ = this.configTime_;
                    shimmerInfo.configTimeParsed_ = this.configTimeParsed_;
                    SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> singleFieldBuilderV3 = this.ieee802154InfoBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        shimmerInfo.ieee802154Info_ = this.ieee802154Info_;
                    } else {
                        shimmerInfo.ieee802154Info_ = singleFieldBuilderV3.build();
                    }
                    onBuilt();
                    return shimmerInfo;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m6063clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m6075setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m6053clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m6056clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m6077setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m6043addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m6068mergeFrom(Message message) {
                    if (message instanceof ShimmerInfo) {
                        return mergeFrom((ShimmerInfo) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(ShimmerInfo shimmerInfo) {
                    if (shimmerInfo == ShimmerInfo.getDefaultInstance()) {
                        return this;
                    }
                    if (!shimmerInfo.getName().isEmpty()) {
                        this.name_ = shimmerInfo.name_;
                        onChanged();
                    }
                    if (!shimmerInfo.getUniqueId().isEmpty()) {
                        this.uniqueId_ = shimmerInfo.uniqueId_;
                        onChanged();
                    }
                    if (!shimmerInfo.getBluetoothAddress().isEmpty()) {
                        this.bluetoothAddress_ = shimmerInfo.bluetoothAddress_;
                        onChanged();
                    }
                    if (!shimmerInfo.getTrialName().isEmpty()) {
                        this.trialName_ = shimmerInfo.trialName_;
                        onChanged();
                    }
                    if (!shimmerInfo.getBatteryPercentageParsed().isEmpty()) {
                        this.batteryPercentageParsed_ = shimmerInfo.batteryPercentageParsed_;
                        onChanged();
                    }
                    if (shimmerInfo.getBatteryPercentage() != 0.0d) {
                        setBatteryPercentage(shimmerInfo.getBatteryPercentage());
                    }
                    if (!shimmerInfo.getChargingStatusParsed().isEmpty()) {
                        this.chargingStatusParsed_ = shimmerInfo.chargingStatusParsed_;
                        onChanged();
                    }
                    if (shimmerInfo.getDriveCapacity() != 0) {
                        setDriveCapacity(shimmerInfo.getDriveCapacity());
                    }
                    if (shimmerInfo.getDriveSpaceUsed() != 0) {
                        setDriveSpaceUsed(shimmerInfo.getDriveSpaceUsed());
                    }
                    if (shimmerInfo.getDriveSpaceFree() != 0) {
                        setDriveSpaceFree(shimmerInfo.getDriveSpaceFree());
                    }
                    if (!shimmerInfo.getDriveCapacityParsed().isEmpty()) {
                        this.driveCapacityParsed_ = shimmerInfo.driveCapacityParsed_;
                        onChanged();
                    }
                    if (shimmerInfo.getIsRealTimeClockSet()) {
                        setIsRealTimeClockSet(shimmerInfo.getIsRealTimeClockSet());
                    }
                    if (shimmerInfo.getLastReadRtcValueMilliSecs() != 0) {
                        setLastReadRtcValueMilliSecs(shimmerInfo.getLastReadRtcValueMilliSecs());
                    }
                    if (!shimmerInfo.getLastReadRtcValueParsed().isEmpty()) {
                        this.lastReadRtcValueParsed_ = shimmerInfo.lastReadRtcValueParsed_;
                        onChanged();
                    }
                    if (!shimmerInfo.getHwVersionParsed().isEmpty()) {
                        this.hwVersionParsed_ = shimmerInfo.hwVersionParsed_;
                        onChanged();
                    }
                    if (!shimmerInfo.getExpBrdVersionParsed().isEmpty()) {
                        this.expBrdVersionParsed_ = shimmerInfo.expBrdVersionParsed_;
                        onChanged();
                    }
                    if (!shimmerInfo.getFwVersionParsed().isEmpty()) {
                        this.fwVersionParsed_ = shimmerInfo.fwVersionParsed_;
                        onChanged();
                    }
                    if (!shimmerInfo.pairedDevice_.isEmpty()) {
                        if (this.pairedDevice_.isEmpty()) {
                            this.pairedDevice_ = shimmerInfo.pairedDevice_;
                            this.bitField0_ &= -2;
                        } else {
                            ensurePairedDeviceIsMutable();
                            this.pairedDevice_.addAll(shimmerInfo.pairedDevice_);
                        }
                        onChanged();
                    }
                    if (shimmerInfo.getConfigTime() != 0) {
                        setConfigTime(shimmerInfo.getConfigTime());
                    }
                    if (!shimmerInfo.getConfigTimeParsed().isEmpty()) {
                        this.configTimeParsed_ = shimmerInfo.configTimeParsed_;
                        onChanged();
                    }
                    if (shimmerInfo.hasIeee802154Info()) {
                        mergeIeee802154Info(shimmerInfo.getIeee802154Info());
                    }
                    m6073mergeUnknownFields(shimmerInfo.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfo.Builder m6069mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfo.m6033$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.shimmerresearch.grpc.ShimmerGRPC$ShimmersInfo$ShimmerInfo r3 = (com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        if (r3 == 0) goto L10
                        r2.mergeFrom(r3)
                    L10:
                        return r2
                    L11:
                        r3 = move-exception
                        goto L21
                    L13:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                        com.shimmerresearch.grpc.ShimmerGRPC$ShimmersInfo$ShimmerInfo r4 = (com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfo) r4     // Catch: java.lang.Throwable -> L11
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                        throw r3     // Catch: java.lang.Throwable -> L1f
                    L1f:
                        r3 = move-exception
                        r0 = r4
                    L21:
                        if (r0 == 0) goto L26
                        r2.mergeFrom(r0)
                    L26:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfo.Builder.m6069mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$ShimmersInfo$ShimmerInfo$Builder");
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public String getName() {
                    Object obj = this.name_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.name_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setName(String str) {
                    str.getClass();
                    this.name_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public ByteString getNameBytes() {
                    Object obj = this.name_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.name_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setNameBytes(ByteString byteString) {
                    byteString.getClass();
                    ShimmerInfo.checkByteStringIsUtf8(byteString);
                    this.name_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearName() {
                    this.name_ = ShimmerInfo.getDefaultInstance().getName();
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public String getUniqueId() {
                    Object obj = this.uniqueId_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.uniqueId_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setUniqueId(String str) {
                    str.getClass();
                    this.uniqueId_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public ByteString getUniqueIdBytes() {
                    Object obj = this.uniqueId_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.uniqueId_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setUniqueIdBytes(ByteString byteString) {
                    byteString.getClass();
                    ShimmerInfo.checkByteStringIsUtf8(byteString);
                    this.uniqueId_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearUniqueId() {
                    this.uniqueId_ = ShimmerInfo.getDefaultInstance().getUniqueId();
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public String getBluetoothAddress() {
                    Object obj = this.bluetoothAddress_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.bluetoothAddress_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setBluetoothAddress(String str) {
                    str.getClass();
                    this.bluetoothAddress_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public ByteString getBluetoothAddressBytes() {
                    Object obj = this.bluetoothAddress_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.bluetoothAddress_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setBluetoothAddressBytes(ByteString byteString) {
                    byteString.getClass();
                    ShimmerInfo.checkByteStringIsUtf8(byteString);
                    this.bluetoothAddress_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearBluetoothAddress() {
                    this.bluetoothAddress_ = ShimmerInfo.getDefaultInstance().getBluetoothAddress();
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public String getTrialName() {
                    Object obj = this.trialName_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.trialName_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setTrialName(String str) {
                    str.getClass();
                    this.trialName_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public ByteString getTrialNameBytes() {
                    Object obj = this.trialName_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.trialName_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setTrialNameBytes(ByteString byteString) {
                    byteString.getClass();
                    ShimmerInfo.checkByteStringIsUtf8(byteString);
                    this.trialName_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearTrialName() {
                    this.trialName_ = ShimmerInfo.getDefaultInstance().getTrialName();
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public String getBatteryPercentageParsed() {
                    Object obj = this.batteryPercentageParsed_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.batteryPercentageParsed_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setBatteryPercentageParsed(String str) {
                    str.getClass();
                    this.batteryPercentageParsed_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public ByteString getBatteryPercentageParsedBytes() {
                    Object obj = this.batteryPercentageParsed_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.batteryPercentageParsed_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setBatteryPercentageParsedBytes(ByteString byteString) {
                    byteString.getClass();
                    ShimmerInfo.checkByteStringIsUtf8(byteString);
                    this.batteryPercentageParsed_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearBatteryPercentageParsed() {
                    this.batteryPercentageParsed_ = ShimmerInfo.getDefaultInstance().getBatteryPercentageParsed();
                    onChanged();
                    return this;
                }

                public Builder clearBatteryPercentage() {
                    this.batteryPercentage_ = 0.0d;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public String getChargingStatusParsed() {
                    Object obj = this.chargingStatusParsed_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.chargingStatusParsed_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setChargingStatusParsed(String str) {
                    str.getClass();
                    this.chargingStatusParsed_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public ByteString getChargingStatusParsedBytes() {
                    Object obj = this.chargingStatusParsed_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.chargingStatusParsed_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setChargingStatusParsedBytes(ByteString byteString) {
                    byteString.getClass();
                    ShimmerInfo.checkByteStringIsUtf8(byteString);
                    this.chargingStatusParsed_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearChargingStatusParsed() {
                    this.chargingStatusParsed_ = ShimmerInfo.getDefaultInstance().getChargingStatusParsed();
                    onChanged();
                    return this;
                }

                public Builder clearDriveCapacity() {
                    this.driveCapacity_ = 0L;
                    onChanged();
                    return this;
                }

                public Builder clearDriveSpaceUsed() {
                    this.driveSpaceUsed_ = 0L;
                    onChanged();
                    return this;
                }

                public Builder clearDriveSpaceFree() {
                    this.driveSpaceFree_ = 0L;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public String getDriveCapacityParsed() {
                    Object obj = this.driveCapacityParsed_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.driveCapacityParsed_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setDriveCapacityParsed(String str) {
                    str.getClass();
                    this.driveCapacityParsed_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public ByteString getDriveCapacityParsedBytes() {
                    Object obj = this.driveCapacityParsed_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.driveCapacityParsed_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setDriveCapacityParsedBytes(ByteString byteString) {
                    byteString.getClass();
                    ShimmerInfo.checkByteStringIsUtf8(byteString);
                    this.driveCapacityParsed_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearDriveCapacityParsed() {
                    this.driveCapacityParsed_ = ShimmerInfo.getDefaultInstance().getDriveCapacityParsed();
                    onChanged();
                    return this;
                }

                public Builder clearIsRealTimeClockSet() {
                    this.isRealTimeClockSet_ = false;
                    onChanged();
                    return this;
                }

                public Builder clearLastReadRtcValueMilliSecs() {
                    this.lastReadRtcValueMilliSecs_ = 0L;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public String getLastReadRtcValueParsed() {
                    Object obj = this.lastReadRtcValueParsed_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.lastReadRtcValueParsed_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setLastReadRtcValueParsed(String str) {
                    str.getClass();
                    this.lastReadRtcValueParsed_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public ByteString getLastReadRtcValueParsedBytes() {
                    Object obj = this.lastReadRtcValueParsed_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.lastReadRtcValueParsed_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setLastReadRtcValueParsedBytes(ByteString byteString) {
                    byteString.getClass();
                    ShimmerInfo.checkByteStringIsUtf8(byteString);
                    this.lastReadRtcValueParsed_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearLastReadRtcValueParsed() {
                    this.lastReadRtcValueParsed_ = ShimmerInfo.getDefaultInstance().getLastReadRtcValueParsed();
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public String getHwVersionParsed() {
                    Object obj = this.hwVersionParsed_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.hwVersionParsed_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setHwVersionParsed(String str) {
                    str.getClass();
                    this.hwVersionParsed_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public ByteString getHwVersionParsedBytes() {
                    Object obj = this.hwVersionParsed_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.hwVersionParsed_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setHwVersionParsedBytes(ByteString byteString) {
                    byteString.getClass();
                    ShimmerInfo.checkByteStringIsUtf8(byteString);
                    this.hwVersionParsed_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearHwVersionParsed() {
                    this.hwVersionParsed_ = ShimmerInfo.getDefaultInstance().getHwVersionParsed();
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public String getExpBrdVersionParsed() {
                    Object obj = this.expBrdVersionParsed_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.expBrdVersionParsed_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setExpBrdVersionParsed(String str) {
                    str.getClass();
                    this.expBrdVersionParsed_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public ByteString getExpBrdVersionParsedBytes() {
                    Object obj = this.expBrdVersionParsed_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.expBrdVersionParsed_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setExpBrdVersionParsedBytes(ByteString byteString) {
                    byteString.getClass();
                    ShimmerInfo.checkByteStringIsUtf8(byteString);
                    this.expBrdVersionParsed_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearExpBrdVersionParsed() {
                    this.expBrdVersionParsed_ = ShimmerInfo.getDefaultInstance().getExpBrdVersionParsed();
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public String getFwVersionParsed() {
                    Object obj = this.fwVersionParsed_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.fwVersionParsed_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setFwVersionParsed(String str) {
                    str.getClass();
                    this.fwVersionParsed_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public ByteString getFwVersionParsedBytes() {
                    Object obj = this.fwVersionParsed_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.fwVersionParsed_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setFwVersionParsedBytes(ByteString byteString) {
                    byteString.getClass();
                    ShimmerInfo.checkByteStringIsUtf8(byteString);
                    this.fwVersionParsed_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearFwVersionParsed() {
                    this.fwVersionParsed_ = ShimmerInfo.getDefaultInstance().getFwVersionParsed();
                    onChanged();
                    return this;
                }

                private void ensurePairedDeviceIsMutable() {
                    if ((this.bitField0_ & 1) == 0) {
                        this.pairedDevice_ = new LazyStringArrayList(this.pairedDevice_);
                        this.bitField0_ |= 1;
                    }
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                /* renamed from: getPairedDeviceList, reason: merged with bridge method [inline-methods] */
                public ProtocolStringList mo6036getPairedDeviceList() {
                    return this.pairedDevice_.getUnmodifiableView();
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public int getPairedDeviceCount() {
                    return this.pairedDevice_.size();
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public String getPairedDevice(int i) {
                    return (String) this.pairedDevice_.get(i);
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public ByteString getPairedDeviceBytes(int i) {
                    return this.pairedDevice_.getByteString(i);
                }

                public Builder setPairedDevice(int i, String str) {
                    str.getClass();
                    ensurePairedDeviceIsMutable();
                    this.pairedDevice_.set(i, str);
                    onChanged();
                    return this;
                }

                public Builder addPairedDevice(String str) {
                    str.getClass();
                    ensurePairedDeviceIsMutable();
                    this.pairedDevice_.add(str);
                    onChanged();
                    return this;
                }

                public Builder addAllPairedDevice(Iterable<String> iterable) {
                    ensurePairedDeviceIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.pairedDevice_);
                    onChanged();
                    return this;
                }

                public Builder clearPairedDevice() {
                    this.pairedDevice_ = LazyStringArrayList.EMPTY;
                    this.bitField0_ &= -2;
                    onChanged();
                    return this;
                }

                public Builder addPairedDeviceBytes(ByteString byteString) {
                    byteString.getClass();
                    ShimmerInfo.checkByteStringIsUtf8(byteString);
                    ensurePairedDeviceIsMutable();
                    this.pairedDevice_.add(byteString);
                    onChanged();
                    return this;
                }

                public Builder clearConfigTime() {
                    this.configTime_ = 0L;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public String getConfigTimeParsed() {
                    Object obj = this.configTimeParsed_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.configTimeParsed_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setConfigTimeParsed(String str) {
                    str.getClass();
                    this.configTimeParsed_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public ByteString getConfigTimeParsedBytes() {
                    Object obj = this.configTimeParsed_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.configTimeParsed_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setConfigTimeParsedBytes(ByteString byteString) {
                    byteString.getClass();
                    ShimmerInfo.checkByteStringIsUtf8(byteString);
                    this.configTimeParsed_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearConfigTimeParsed() {
                    this.configTimeParsed_ = ShimmerInfo.getDefaultInstance().getConfigTimeParsed();
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public Ieee802154Info getIeee802154Info() {
                    SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> singleFieldBuilderV3 = this.ieee802154InfoBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    Ieee802154Info ieee802154Info = this.ieee802154Info_;
                    return ieee802154Info == null ? Ieee802154Info.getDefaultInstance() : ieee802154Info;
                }

                public Builder setIeee802154Info(Ieee802154Info ieee802154Info) {
                    SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> singleFieldBuilderV3 = this.ieee802154InfoBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        ieee802154Info.getClass();
                        this.ieee802154Info_ = ieee802154Info;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(ieee802154Info);
                    }
                    return this;
                }

                public Builder setIeee802154Info(Ieee802154Info.Builder builder) {
                    SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> singleFieldBuilderV3 = this.ieee802154InfoBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.ieee802154Info_ = builder.m5539build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m5539build());
                    }
                    return this;
                }

                public Builder mergeIeee802154Info(Ieee802154Info ieee802154Info) {
                    SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> singleFieldBuilderV3 = this.ieee802154InfoBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        Ieee802154Info ieee802154Info2 = this.ieee802154Info_;
                        if (ieee802154Info2 != null) {
                            this.ieee802154Info_ = Ieee802154Info.newBuilder(ieee802154Info2).mergeFrom(ieee802154Info).m5541buildPartial();
                        } else {
                            this.ieee802154Info_ = ieee802154Info;
                        }
                        onChanged();
                    } else {
                        singleFieldBuilderV3.mergeFrom(ieee802154Info);
                    }
                    return this;
                }

                public Builder clearIeee802154Info() {
                    if (this.ieee802154InfoBuilder_ == null) {
                        this.ieee802154Info_ = null;
                        onChanged();
                    } else {
                        this.ieee802154Info_ = null;
                        this.ieee802154InfoBuilder_ = null;
                    }
                    return this;
                }

                public Ieee802154Info.Builder getIeee802154InfoBuilder() {
                    onChanged();
                    return getIeee802154InfoFieldBuilder().getBuilder();
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.ShimmerInfoOrBuilder
                public Ieee802154InfoOrBuilder getIeee802154InfoOrBuilder() {
                    SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> singleFieldBuilderV3 = this.ieee802154InfoBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return (Ieee802154InfoOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    Ieee802154Info ieee802154Info = this.ieee802154Info_;
                    return ieee802154Info == null ? Ieee802154Info.getDefaultInstance() : ieee802154Info;
                }

                private SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> getIeee802154InfoFieldBuilder() {
                    if (this.ieee802154InfoBuilder_ == null) {
                        this.ieee802154InfoBuilder_ = new SingleFieldBuilderV3<>(getIeee802154Info(), getParentForChildren(), isClean());
                        this.ieee802154Info_ = null;
                    }
                    return this.ieee802154InfoBuilder_;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m6079setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m6073mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        private static final class ShimmerMapDefaultEntryHolder {
            static final MapEntry<String, ShimmerInfo> defaultEntry = MapEntry.newDefaultInstance(ShimmerGRPC.internal_static_shimmerGRPC_ShimmersInfo_ShimmerMapEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, ShimmerInfo.getDefaultInstance());

            private ShimmerMapDefaultEntryHolder() {
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ShimmersInfoOrBuilder {
            private int bitField0_;
            private Object message_;
            private MapField<String, ShimmerInfo> shimmerMap_;
            private boolean state_;

            private Builder() {
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_ShimmersInfo_descriptor;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
            public boolean getState() {
                return this.state_;
            }

            public Builder setState(boolean z) {
                this.state_ = z;
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected MapField internalGetMapField(int i) {
                if (i == 3) {
                    return internalGetShimmerMap();
                }
                throw new RuntimeException("Invalid map field number: " + i);
            }

            protected MapField internalGetMutableMapField(int i) {
                if (i == 3) {
                    return internalGetMutableShimmerMap();
                }
                throw new RuntimeException("Invalid map field number: " + i);
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_ShimmersInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ShimmersInfo.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ShimmersInfo.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5969clear() {
                super.clear();
                this.state_ = false;
                this.message_ = "";
                internalGetMutableShimmerMap().clear();
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_ShimmersInfo_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ShimmersInfo m5982getDefaultInstanceForType() {
                return ShimmersInfo.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ShimmersInfo m5963build() throws UninitializedMessageException {
                ShimmersInfo shimmersInfoM5965buildPartial = m5965buildPartial();
                if (shimmersInfoM5965buildPartial.isInitialized()) {
                    return shimmersInfoM5965buildPartial;
                }
                throw newUninitializedMessageException(shimmersInfoM5965buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ShimmersInfo m5965buildPartial() {
                ShimmersInfo shimmersInfo = new ShimmersInfo(this);
                shimmersInfo.state_ = this.state_;
                shimmersInfo.message_ = this.message_;
                shimmersInfo.shimmerMap_ = internalGetShimmerMap();
                shimmersInfo.shimmerMap_.makeImmutable();
                onBuilt();
                return shimmersInfo;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5981clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5993setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5971clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5974clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5995setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5961addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5986mergeFrom(Message message) {
                if (message instanceof ShimmersInfo) {
                    return mergeFrom((ShimmersInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ShimmersInfo shimmersInfo) {
                if (shimmersInfo == ShimmersInfo.getDefaultInstance()) {
                    return this;
                }
                if (shimmersInfo.getState()) {
                    setState(shimmersInfo.getState());
                }
                if (!shimmersInfo.getMessage().isEmpty()) {
                    this.message_ = shimmersInfo.message_;
                    onChanged();
                }
                internalGetMutableShimmerMap().mergeFrom(shimmersInfo.internalGetShimmerMap());
                m5991mergeUnknownFields(shimmersInfo.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.Builder m5987mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.m5952$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$ShimmersInfo r3 = (com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$ShimmersInfo r4 = (com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfo.Builder.m5987mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$ShimmersInfo$Builder");
            }

            public Builder clearState() {
                this.state_ = false;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
            public String getMessage() {
                Object obj = this.message_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.message_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setMessage(String str) {
                str.getClass();
                this.message_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
            public ByteString getMessageBytes() {
                Object obj = this.message_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.message_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMessageBytes(ByteString byteString) {
                byteString.getClass();
                ShimmersInfo.checkByteStringIsUtf8(byteString);
                this.message_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearMessage() {
                this.message_ = ShimmersInfo.getDefaultInstance().getMessage();
                onChanged();
                return this;
            }

            private MapField<String, ShimmerInfo> internalGetShimmerMap() {
                MapField<String, ShimmerInfo> mapField = this.shimmerMap_;
                return mapField == null ? MapField.emptyMapField(ShimmerMapDefaultEntryHolder.defaultEntry) : mapField;
            }

            private MapField<String, ShimmerInfo> internalGetMutableShimmerMap() {
                onChanged();
                if (this.shimmerMap_ == null) {
                    this.shimmerMap_ = MapField.newMapField(ShimmerMapDefaultEntryHolder.defaultEntry);
                }
                if (!this.shimmerMap_.isMutable()) {
                    this.shimmerMap_ = this.shimmerMap_.copy();
                }
                return this.shimmerMap_;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
            public int getShimmerMapCount() {
                return internalGetShimmerMap().getMap().size();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
            public boolean containsShimmerMap(String str) {
                str.getClass();
                return internalGetShimmerMap().getMap().containsKey(str);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
            @Deprecated
            public Map<String, ShimmerInfo> getShimmerMap() {
                return getShimmerMapMap();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
            public Map<String, ShimmerInfo> getShimmerMapMap() {
                return internalGetShimmerMap().getMap();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
            public ShimmerInfo getShimmerMapOrDefault(String str, ShimmerInfo shimmerInfo) {
                str.getClass();
                Map map = internalGetShimmerMap().getMap();
                return map.containsKey(str) ? (ShimmerInfo) map.get(str) : shimmerInfo;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.ShimmersInfoOrBuilder
            public ShimmerInfo getShimmerMapOrThrow(String str) {
                str.getClass();
                Map map = internalGetShimmerMap().getMap();
                if (!map.containsKey(str)) {
                    throw new IllegalArgumentException();
                }
                return (ShimmerInfo) map.get(str);
            }

            public Builder clearShimmerMap() {
                internalGetMutableShimmerMap().getMutableMap().clear();
                return this;
            }

            public Builder removeShimmerMap(String str) {
                str.getClass();
                internalGetMutableShimmerMap().getMutableMap().remove(str);
                return this;
            }

            @Deprecated
            public Map<String, ShimmerInfo> getMutableShimmerMap() {
                return internalGetMutableShimmerMap().getMutableMap();
            }

            public Builder putShimmerMap(String str, ShimmerInfo shimmerInfo) {
                str.getClass();
                shimmerInfo.getClass();
                internalGetMutableShimmerMap().getMutableMap().put(str, shimmerInfo);
                return this;
            }

            public Builder putAllShimmerMap(Map<String, ShimmerInfo> map) {
                internalGetMutableShimmerMap().getMutableMap().putAll(map);
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5997setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5991mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class BluetoothDevicesDetails extends GeneratedMessageV3 implements BluetoothDevicesDetailsOrBuilder {
        public static final int DEVICEMAP_FIELD_NUMBER = 3;
        public static final int MESSAGE_FIELD_NUMBER = 2;
        public static final int STATE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final BluetoothDevicesDetails DEFAULT_INSTANCE = new BluetoothDevicesDetails();
        private static final Parser<BluetoothDevicesDetails> PARSER = new AbstractParser<BluetoothDevicesDetails>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public BluetoothDevicesDetails m4972parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new BluetoothDevicesDetails(codedInputStream, extensionRegistryLite);
            }
        };
        private MapField<String, BluetoothDeviceDetails> deviceMap_;
        private byte memoizedIsInitialized;
        private volatile Object message_;
        private boolean state_;

        private BluetoothDevicesDetails(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private BluetoothDevicesDetails() {
            this.memoizedIsInitialized = (byte) -1;
            this.message_ = "";
        }

        private BluetoothDevicesDetails(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 8) {
                                this.state_ = codedInputStream.readBool();
                            } else if (tag == 18) {
                                this.message_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 26) {
                                if (!(z2 & true)) {
                                    this.deviceMap_ = MapField.newMapField(DeviceMapDefaultEntryHolder.defaultEntry);
                                    z2 |= true;
                                }
                                MapEntry message = codedInputStream.readMessage(DeviceMapDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.deviceMap_.getMutableMap().put((String) message.getKey(), (BluetoothDeviceDetails) message.getValue());
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static BluetoothDevicesDetails getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BluetoothDevicesDetails> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_BluetoothDevicesDetails_descriptor;
        }

        public static BluetoothDevicesDetails parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (BluetoothDevicesDetails) PARSER.parseFrom(byteBuffer);
        }

        public static BluetoothDevicesDetails parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BluetoothDevicesDetails) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static BluetoothDevicesDetails parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (BluetoothDevicesDetails) PARSER.parseFrom(byteString);
        }

        public static BluetoothDevicesDetails parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BluetoothDevicesDetails) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static BluetoothDevicesDetails parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (BluetoothDevicesDetails) PARSER.parseFrom(bArr);
        }

        public static BluetoothDevicesDetails parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BluetoothDevicesDetails) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static BluetoothDevicesDetails parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static BluetoothDevicesDetails parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BluetoothDevicesDetails parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static BluetoothDevicesDetails parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BluetoothDevicesDetails parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static BluetoothDevicesDetails parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m4970toBuilder();
        }

        public static Builder newBuilder(BluetoothDevicesDetails bluetoothDevicesDetails) {
            return DEFAULT_INSTANCE.m4970toBuilder().mergeFrom(bluetoothDevicesDetails);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public BluetoothDevicesDetails m4965getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<BluetoothDevicesDetails> getParserForType() {
            return PARSER;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
        public boolean getState() {
            return this.state_;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new BluetoothDevicesDetails();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 3) {
                return internalGetDeviceMap();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_BluetoothDevicesDetails_fieldAccessorTable.ensureFieldAccessorsInitialized(BluetoothDevicesDetails.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
        public String getMessage() {
            Object obj = this.message_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.message_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
        public ByteString getMessageBytes() {
            Object obj = this.message_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.message_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MapField<String, BluetoothDeviceDetails> internalGetDeviceMap() {
            MapField<String, BluetoothDeviceDetails> mapField = this.deviceMap_;
            return mapField == null ? MapField.emptyMapField(DeviceMapDefaultEntryHolder.defaultEntry) : mapField;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
        public int getDeviceMapCount() {
            return internalGetDeviceMap().getMap().size();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
        public boolean containsDeviceMap(String str) {
            str.getClass();
            return internalGetDeviceMap().getMap().containsKey(str);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
        @Deprecated
        public Map<String, BluetoothDeviceDetails> getDeviceMap() {
            return getDeviceMapMap();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
        public Map<String, BluetoothDeviceDetails> getDeviceMapMap() {
            return internalGetDeviceMap().getMap();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
        public BluetoothDeviceDetails getDeviceMapOrDefault(String str, BluetoothDeviceDetails bluetoothDeviceDetails) {
            str.getClass();
            Map map = internalGetDeviceMap().getMap();
            return map.containsKey(str) ? (BluetoothDeviceDetails) map.get(str) : bluetoothDeviceDetails;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
        public BluetoothDeviceDetails getDeviceMapOrThrow(String str) {
            str.getClass();
            Map map = internalGetDeviceMap().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return (BluetoothDeviceDetails) map.get(str);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.state_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            if (!getMessageBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.message_);
            }
            GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetDeviceMap(), DeviceMapDefaultEntryHolder.defaultEntry, 3);
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            boolean z = this.state_;
            int iComputeBoolSize = z ? CodedOutputStream.computeBoolSize(1, z) : 0;
            if (!getMessageBytes().isEmpty()) {
                iComputeBoolSize += GeneratedMessageV3.computeStringSize(2, this.message_);
            }
            for (Map.Entry entry : internalGetDeviceMap().getMap().entrySet()) {
                iComputeBoolSize += CodedOutputStream.computeMessageSize(3, DeviceMapDefaultEntryHolder.defaultEntry.newBuilderForType().setKey((String) entry.getKey()).setValue((BluetoothDeviceDetails) entry.getValue()).build());
            }
            int serializedSize = iComputeBoolSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof BluetoothDevicesDetails)) {
                return super.equals(obj);
            }
            BluetoothDevicesDetails bluetoothDevicesDetails = (BluetoothDevicesDetails) obj;
            return getState() == bluetoothDevicesDetails.getState() && getMessage().equals(bluetoothDevicesDetails.getMessage()) && internalGetDeviceMap().equals(bluetoothDevicesDetails.internalGetDeviceMap()) && this.unknownFields.equals(bluetoothDevicesDetails.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getState())) * 37) + 2) * 53) + getMessage().hashCode();
            if (!internalGetDeviceMap().getMap().isEmpty()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + internalGetDeviceMap().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4967newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4970toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public interface BluetoothDeviceDetailsOrBuilder extends MessageOrBuilder {
            String getComPort();

            ByteString getComPortBytes();

            String getDeviceType();

            ByteString getDeviceTypeBytes();

            String getLastConnectionState();

            ByteString getLastConnectionStateBytes();

            String getMacId();

            ByteString getMacIdBytes();

            String getName();

            ByteString getNameBytes();
        }

        public static final class BluetoothDeviceDetails extends GeneratedMessageV3 implements BluetoothDeviceDetailsOrBuilder {
            public static final int COMPORT_FIELD_NUMBER = 1;
            public static final int DEVICETYPE_FIELD_NUMBER = 4;
            public static final int LASTCONNECTIONSTATE_FIELD_NUMBER = 5;
            public static final int MACID_FIELD_NUMBER = 3;
            public static final int NAME_FIELD_NUMBER = 2;
            private static final long serialVersionUID = 0;
            private static final BluetoothDeviceDetails DEFAULT_INSTANCE = new BluetoothDeviceDetails();
            private static final Parser<BluetoothDeviceDetails> PARSER = new AbstractParser<BluetoothDeviceDetails>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetails.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public BluetoothDeviceDetails m4991parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new BluetoothDeviceDetails(codedInputStream, extensionRegistryLite);
                }
            };
            private volatile Object comPort_;
            private volatile Object deviceType_;
            private volatile Object lastConnectionState_;
            private volatile Object macId_;
            private byte memoizedIsInitialized;
            private volatile Object name_;

            private BluetoothDeviceDetails(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private BluetoothDeviceDetails() {
                this.memoizedIsInitialized = (byte) -1;
                this.comPort_ = "";
                this.name_ = "";
                this.macId_ = "";
                this.deviceType_ = "";
                this.lastConnectionState_ = "";
            }

            private BluetoothDeviceDetails(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int tag = codedInputStream.readTag();
                                if (tag != 0) {
                                    if (tag == 10) {
                                        this.comPort_ = codedInputStream.readStringRequireUtf8();
                                    } else if (tag == 18) {
                                        this.name_ = codedInputStream.readStringRequireUtf8();
                                    } else if (tag == 26) {
                                        this.macId_ = codedInputStream.readStringRequireUtf8();
                                    } else if (tag == 34) {
                                        this.deviceType_ = codedInputStream.readStringRequireUtf8();
                                    } else if (tag == 42) {
                                        this.lastConnectionState_ = codedInputStream.readStringRequireUtf8();
                                    } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    }
                                }
                                z = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw e.setUnfinishedMessage(this);
                            }
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = builderNewBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static BluetoothDeviceDetails getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<BluetoothDeviceDetails> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_BluetoothDevicesDetails_BluetoothDeviceDetails_descriptor;
            }

            public static BluetoothDeviceDetails parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (BluetoothDeviceDetails) PARSER.parseFrom(byteBuffer);
            }

            public static BluetoothDeviceDetails parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (BluetoothDeviceDetails) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static BluetoothDeviceDetails parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (BluetoothDeviceDetails) PARSER.parseFrom(byteString);
            }

            public static BluetoothDeviceDetails parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (BluetoothDeviceDetails) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static BluetoothDeviceDetails parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (BluetoothDeviceDetails) PARSER.parseFrom(bArr);
            }

            public static BluetoothDeviceDetails parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (BluetoothDeviceDetails) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static BluetoothDeviceDetails parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static BluetoothDeviceDetails parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static BluetoothDeviceDetails parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static BluetoothDeviceDetails parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static BluetoothDeviceDetails parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static BluetoothDeviceDetails parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m4989toBuilder();
            }

            public static Builder newBuilder(BluetoothDeviceDetails bluetoothDeviceDetails) {
                return DEFAULT_INSTANCE.m4989toBuilder().mergeFrom(bluetoothDeviceDetails);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public BluetoothDeviceDetails m4984getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public Parser<BluetoothDeviceDetails> getParserForType() {
                return PARSER;
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new BluetoothDeviceDetails();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_BluetoothDevicesDetails_BluetoothDeviceDetails_fieldAccessorTable.ensureFieldAccessorsInitialized(BluetoothDeviceDetails.class, Builder.class);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
            public String getComPort() {
                Object obj = this.comPort_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.comPort_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
            public ByteString getComPortBytes() {
                Object obj = this.comPort_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.comPort_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
            public String getName() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.name_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
            public String getMacId() {
                Object obj = this.macId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.macId_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
            public ByteString getMacIdBytes() {
                Object obj = this.macId_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.macId_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
            public String getDeviceType() {
                Object obj = this.deviceType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.deviceType_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
            public ByteString getDeviceTypeBytes() {
                Object obj = this.deviceType_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.deviceType_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
            public String getLastConnectionState() {
                Object obj = this.lastConnectionState_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.lastConnectionState_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
            public ByteString getLastConnectionStateBytes() {
                Object obj = this.lastConnectionState_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.lastConnectionState_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (!getComPortBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.comPort_);
                }
                if (!getNameBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.name_);
                }
                if (!getMacIdBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.macId_);
                }
                if (!getDeviceTypeBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 4, this.deviceType_);
                }
                if (!getLastConnectionStateBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 5, this.lastConnectionState_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeStringSize = !getComPortBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.comPort_) : 0;
                if (!getNameBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.name_);
                }
                if (!getMacIdBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.macId_);
                }
                if (!getDeviceTypeBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.deviceType_);
                }
                if (!getLastConnectionStateBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(5, this.lastConnectionState_);
                }
                int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof BluetoothDeviceDetails)) {
                    return super.equals(obj);
                }
                BluetoothDeviceDetails bluetoothDeviceDetails = (BluetoothDeviceDetails) obj;
                return getComPort().equals(bluetoothDeviceDetails.getComPort()) && getName().equals(bluetoothDeviceDetails.getName()) && getMacId().equals(bluetoothDeviceDetails.getMacId()) && getDeviceType().equals(bluetoothDeviceDetails.getDeviceType()) && getLastConnectionState().equals(bluetoothDeviceDetails.getLastConnectionState()) && this.unknownFields.equals(bluetoothDeviceDetails.unknownFields);
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = ((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getComPort().hashCode()) * 37) + 2) * 53) + getName().hashCode()) * 37) + 3) * 53) + getMacId().hashCode()) * 37) + 4) * 53) + getDeviceType().hashCode()) * 37) + 5) * 53) + getLastConnectionState().hashCode()) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode;
                return iHashCode;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m4986newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m4989toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BluetoothDeviceDetailsOrBuilder {
                private Object comPort_;
                private Object deviceType_;
                private Object lastConnectionState_;
                private Object macId_;
                private Object name_;

                private Builder() {
                    this.comPort_ = "";
                    this.name_ = "";
                    this.macId_ = "";
                    this.deviceType_ = "";
                    this.lastConnectionState_ = "";
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.comPort_ = "";
                    this.name_ = "";
                    this.macId_ = "";
                    this.deviceType_ = "";
                    this.lastConnectionState_ = "";
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_BluetoothDevicesDetails_BluetoothDeviceDetails_descriptor;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_BluetoothDevicesDetails_BluetoothDeviceDetails_fieldAccessorTable.ensureFieldAccessorsInitialized(BluetoothDeviceDetails.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = BluetoothDeviceDetails.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5000clear() {
                    super.clear();
                    this.comPort_ = "";
                    this.name_ = "";
                    this.macId_ = "";
                    this.deviceType_ = "";
                    this.lastConnectionState_ = "";
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_BluetoothDevicesDetails_BluetoothDeviceDetails_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public BluetoothDeviceDetails m5013getDefaultInstanceForType() {
                    return BluetoothDeviceDetails.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public BluetoothDeviceDetails m4994build() throws UninitializedMessageException {
                    BluetoothDeviceDetails bluetoothDeviceDetailsM4996buildPartial = m4996buildPartial();
                    if (bluetoothDeviceDetailsM4996buildPartial.isInitialized()) {
                        return bluetoothDeviceDetailsM4996buildPartial;
                    }
                    throw newUninitializedMessageException(bluetoothDeviceDetailsM4996buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public BluetoothDeviceDetails m4996buildPartial() {
                    BluetoothDeviceDetails bluetoothDeviceDetails = new BluetoothDeviceDetails(this);
                    bluetoothDeviceDetails.comPort_ = this.comPort_;
                    bluetoothDeviceDetails.name_ = this.name_;
                    bluetoothDeviceDetails.macId_ = this.macId_;
                    bluetoothDeviceDetails.deviceType_ = this.deviceType_;
                    bluetoothDeviceDetails.lastConnectionState_ = this.lastConnectionState_;
                    onBuilt();
                    return bluetoothDeviceDetails;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5012clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5024setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5002clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5005clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5026setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m4992addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5017mergeFrom(Message message) {
                    if (message instanceof BluetoothDeviceDetails) {
                        return mergeFrom((BluetoothDeviceDetails) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(BluetoothDeviceDetails bluetoothDeviceDetails) {
                    if (bluetoothDeviceDetails == BluetoothDeviceDetails.getDefaultInstance()) {
                        return this;
                    }
                    if (!bluetoothDeviceDetails.getComPort().isEmpty()) {
                        this.comPort_ = bluetoothDeviceDetails.comPort_;
                        onChanged();
                    }
                    if (!bluetoothDeviceDetails.getName().isEmpty()) {
                        this.name_ = bluetoothDeviceDetails.name_;
                        onChanged();
                    }
                    if (!bluetoothDeviceDetails.getMacId().isEmpty()) {
                        this.macId_ = bluetoothDeviceDetails.macId_;
                        onChanged();
                    }
                    if (!bluetoothDeviceDetails.getDeviceType().isEmpty()) {
                        this.deviceType_ = bluetoothDeviceDetails.deviceType_;
                        onChanged();
                    }
                    if (!bluetoothDeviceDetails.getLastConnectionState().isEmpty()) {
                        this.lastConnectionState_ = bluetoothDeviceDetails.lastConnectionState_;
                        onChanged();
                    }
                    m5022mergeUnknownFields(bluetoothDeviceDetails.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetails.Builder m5018mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetails.m4983$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.shimmerresearch.grpc.ShimmerGRPC$BluetoothDevicesDetails$BluetoothDeviceDetails r3 = (com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetails) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        if (r3 == 0) goto L10
                        r2.mergeFrom(r3)
                    L10:
                        return r2
                    L11:
                        r3 = move-exception
                        goto L21
                    L13:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                        com.shimmerresearch.grpc.ShimmerGRPC$BluetoothDevicesDetails$BluetoothDeviceDetails r4 = (com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetails) r4     // Catch: java.lang.Throwable -> L11
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                        throw r3     // Catch: java.lang.Throwable -> L1f
                    L1f:
                        r3 = move-exception
                        r0 = r4
                    L21:
                        if (r0 == 0) goto L26
                        r2.mergeFrom(r0)
                    L26:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetails.Builder.m5018mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$BluetoothDevicesDetails$BluetoothDeviceDetails$Builder");
                }

                @Override
                // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
                public String getComPort() {
                    Object obj = this.comPort_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.comPort_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setComPort(String str) {
                    str.getClass();
                    this.comPort_ = str;
                    onChanged();
                    return this;
                }

                @Override
                // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
                public ByteString getComPortBytes() {
                    Object obj = this.comPort_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.comPort_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setComPortBytes(ByteString byteString) {
                    byteString.getClass();
                    BluetoothDeviceDetails.checkByteStringIsUtf8(byteString);
                    this.comPort_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearComPort() {
                    this.comPort_ = BluetoothDeviceDetails.getDefaultInstance().getComPort();
                    onChanged();
                    return this;
                }

                @Override
                // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
                public String getName() {
                    Object obj = this.name_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.name_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setName(String str) {
                    str.getClass();
                    this.name_ = str;
                    onChanged();
                    return this;
                }

                @Override
                // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
                public ByteString getNameBytes() {
                    Object obj = this.name_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.name_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setNameBytes(ByteString byteString) {
                    byteString.getClass();
                    BluetoothDeviceDetails.checkByteStringIsUtf8(byteString);
                    this.name_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearName() {
                    this.name_ = BluetoothDeviceDetails.getDefaultInstance().getName();
                    onChanged();
                    return this;
                }

                @Override
                // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
                public String getMacId() {
                    Object obj = this.macId_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.macId_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setMacId(String str) {
                    str.getClass();
                    this.macId_ = str;
                    onChanged();
                    return this;
                }

                @Override
                // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
                public ByteString getMacIdBytes() {
                    Object obj = this.macId_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.macId_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setMacIdBytes(ByteString byteString) {
                    byteString.getClass();
                    BluetoothDeviceDetails.checkByteStringIsUtf8(byteString);
                    this.macId_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearMacId() {
                    this.macId_ = BluetoothDeviceDetails.getDefaultInstance().getMacId();
                    onChanged();
                    return this;
                }

                @Override
                // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
                public String getDeviceType() {
                    Object obj = this.deviceType_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.deviceType_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setDeviceType(String str) {
                    str.getClass();
                    this.deviceType_ = str;
                    onChanged();
                    return this;
                }

                @Override
                // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
                public ByteString getDeviceTypeBytes() {
                    Object obj = this.deviceType_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.deviceType_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setDeviceTypeBytes(ByteString byteString) {
                    byteString.getClass();
                    BluetoothDeviceDetails.checkByteStringIsUtf8(byteString);
                    this.deviceType_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearDeviceType() {
                    this.deviceType_ = BluetoothDeviceDetails.getDefaultInstance().getDeviceType();
                    onChanged();
                    return this;
                }

                @Override
                // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
                public String getLastConnectionState() {
                    Object obj = this.lastConnectionState_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.lastConnectionState_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setLastConnectionState(String str) {
                    str.getClass();
                    this.lastConnectionState_ = str;
                    onChanged();
                    return this;
                }

                @Override
                // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.BluetoothDeviceDetailsOrBuilder
                public ByteString getLastConnectionStateBytes() {
                    Object obj = this.lastConnectionState_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.lastConnectionState_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setLastConnectionStateBytes(ByteString byteString) {
                    byteString.getClass();
                    BluetoothDeviceDetails.checkByteStringIsUtf8(byteString);
                    this.lastConnectionState_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearLastConnectionState() {
                    this.lastConnectionState_ = BluetoothDeviceDetails.getDefaultInstance().getLastConnectionState();
                    onChanged();
                    return this;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m5028setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m5022mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        private static final class DeviceMapDefaultEntryHolder {
            static final MapEntry<String, BluetoothDeviceDetails> defaultEntry = MapEntry.newDefaultInstance(ShimmerGRPC.internal_static_shimmerGRPC_BluetoothDevicesDetails_DeviceMapEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, BluetoothDeviceDetails.getDefaultInstance());

            private DeviceMapDefaultEntryHolder() {
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BluetoothDevicesDetailsOrBuilder {
            private int bitField0_;
            private MapField<String, BluetoothDeviceDetails> deviceMap_;
            private Object message_;
            private boolean state_;

            private Builder() {
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_BluetoothDevicesDetails_descriptor;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
            public boolean getState() {
                return this.state_;
            }

            public Builder setState(boolean z) {
                this.state_ = z;
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected MapField internalGetMapField(int i) {
                if (i == 3) {
                    return internalGetDeviceMap();
                }
                throw new RuntimeException("Invalid map field number: " + i);
            }

            protected MapField internalGetMutableMapField(int i) {
                if (i == 3) {
                    return internalGetMutableDeviceMap();
                }
                throw new RuntimeException("Invalid map field number: " + i);
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_BluetoothDevicesDetails_fieldAccessorTable.ensureFieldAccessorsInitialized(BluetoothDevicesDetails.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = BluetoothDevicesDetails.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5038clear() {
                super.clear();
                this.state_ = false;
                this.message_ = "";
                internalGetMutableDeviceMap().clear();
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_BluetoothDevicesDetails_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public BluetoothDevicesDetails m5051getDefaultInstanceForType() {
                return BluetoothDevicesDetails.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public BluetoothDevicesDetails m5032build() throws UninitializedMessageException {
                BluetoothDevicesDetails bluetoothDevicesDetailsM5034buildPartial = m5034buildPartial();
                if (bluetoothDevicesDetailsM5034buildPartial.isInitialized()) {
                    return bluetoothDevicesDetailsM5034buildPartial;
                }
                throw newUninitializedMessageException(bluetoothDevicesDetailsM5034buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public BluetoothDevicesDetails m5034buildPartial() {
                BluetoothDevicesDetails bluetoothDevicesDetails = new BluetoothDevicesDetails(this);
                bluetoothDevicesDetails.state_ = this.state_;
                bluetoothDevicesDetails.message_ = this.message_;
                bluetoothDevicesDetails.deviceMap_ = internalGetDeviceMap();
                bluetoothDevicesDetails.deviceMap_.makeImmutable();
                onBuilt();
                return bluetoothDevicesDetails;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5050clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5062setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5040clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5043clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5064setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5030addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5055mergeFrom(Message message) {
                if (message instanceof BluetoothDevicesDetails) {
                    return mergeFrom((BluetoothDevicesDetails) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(BluetoothDevicesDetails bluetoothDevicesDetails) {
                if (bluetoothDevicesDetails == BluetoothDevicesDetails.getDefaultInstance()) {
                    return this;
                }
                if (bluetoothDevicesDetails.getState()) {
                    setState(bluetoothDevicesDetails.getState());
                }
                if (!bluetoothDevicesDetails.getMessage().isEmpty()) {
                    this.message_ = bluetoothDevicesDetails.message_;
                    onChanged();
                }
                internalGetMutableDeviceMap().mergeFrom(bluetoothDevicesDetails.internalGetDeviceMap());
                m5060mergeUnknownFields(bluetoothDevicesDetails.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.Builder m5056mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.m4964$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$BluetoothDevicesDetails r3 = (com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$BluetoothDevicesDetails r4 = (com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetails.Builder.m5056mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$BluetoothDevicesDetails$Builder");
            }

            public Builder clearState() {
                this.state_ = false;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
            public String getMessage() {
                Object obj = this.message_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.message_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setMessage(String str) {
                str.getClass();
                this.message_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
            public ByteString getMessageBytes() {
                Object obj = this.message_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.message_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMessageBytes(ByteString byteString) {
                byteString.getClass();
                BluetoothDevicesDetails.checkByteStringIsUtf8(byteString);
                this.message_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearMessage() {
                this.message_ = BluetoothDevicesDetails.getDefaultInstance().getMessage();
                onChanged();
                return this;
            }

            private MapField<String, BluetoothDeviceDetails> internalGetDeviceMap() {
                MapField<String, BluetoothDeviceDetails> mapField = this.deviceMap_;
                return mapField == null ? MapField.emptyMapField(DeviceMapDefaultEntryHolder.defaultEntry) : mapField;
            }

            private MapField<String, BluetoothDeviceDetails> internalGetMutableDeviceMap() {
                onChanged();
                if (this.deviceMap_ == null) {
                    this.deviceMap_ = MapField.newMapField(DeviceMapDefaultEntryHolder.defaultEntry);
                }
                if (!this.deviceMap_.isMutable()) {
                    this.deviceMap_ = this.deviceMap_.copy();
                }
                return this.deviceMap_;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
            public int getDeviceMapCount() {
                return internalGetDeviceMap().getMap().size();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
            public boolean containsDeviceMap(String str) {
                str.getClass();
                return internalGetDeviceMap().getMap().containsKey(str);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
            @Deprecated
            public Map<String, BluetoothDeviceDetails> getDeviceMap() {
                return getDeviceMapMap();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
            public Map<String, BluetoothDeviceDetails> getDeviceMapMap() {
                return internalGetDeviceMap().getMap();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
            public BluetoothDeviceDetails getDeviceMapOrDefault(String str, BluetoothDeviceDetails bluetoothDeviceDetails) {
                str.getClass();
                Map map = internalGetDeviceMap().getMap();
                return map.containsKey(str) ? (BluetoothDeviceDetails) map.get(str) : bluetoothDeviceDetails;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.BluetoothDevicesDetailsOrBuilder
            public BluetoothDeviceDetails getDeviceMapOrThrow(String str) {
                str.getClass();
                Map map = internalGetDeviceMap().getMap();
                if (!map.containsKey(str)) {
                    throw new IllegalArgumentException();
                }
                return (BluetoothDeviceDetails) map.get(str);
            }

            public Builder clearDeviceMap() {
                internalGetMutableDeviceMap().getMutableMap().clear();
                return this;
            }

            public Builder removeDeviceMap(String str) {
                str.getClass();
                internalGetMutableDeviceMap().getMutableMap().remove(str);
                return this;
            }

            @Deprecated
            public Map<String, BluetoothDeviceDetails> getMutableDeviceMap() {
                return internalGetMutableDeviceMap().getMutableMap();
            }

            public Builder putDeviceMap(String str, BluetoothDeviceDetails bluetoothDeviceDetails) {
                str.getClass();
                bluetoothDeviceDetails.getClass();
                internalGetMutableDeviceMap().getMutableMap().put(str, bluetoothDeviceDetails);
                return this;
            }

            public Builder putAllDeviceMap(Map<String, BluetoothDeviceDetails> map) {
                internalGetMutableDeviceMap().getMutableMap().putAll(map);
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5066setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5060mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class DeviceState extends GeneratedMessageV3 implements DeviceStateOrBuilder {
        public static final int COMPORT_FIELD_NUMBER = 2;
        public static final int DEVICENAME_FIELD_NUMBER = 1;
        public static final int MACID_FIELD_NUMBER = 3;
        public static final int STATE_FIELD_NUMBER = 4;
        private static final long serialVersionUID = 0;
        private static final DeviceState DEFAULT_INSTANCE = new DeviceState();
        private static final Parser<DeviceState> PARSER = new AbstractParser<DeviceState>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.DeviceState.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public DeviceState m5181parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new DeviceState(codedInputStream, extensionRegistryLite);
            }
        };
        private volatile Object comPort_;
        private volatile Object deviceName_;
        private volatile Object macId_;
        private byte memoizedIsInitialized;
        private int state_;

        private DeviceState(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private DeviceState() {
            this.memoizedIsInitialized = (byte) -1;
            this.deviceName_ = "";
            this.comPort_ = "";
            this.macId_ = "";
            this.state_ = 0;
        }

        private DeviceState(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.deviceName_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.comPort_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 26) {
                                this.macId_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 32) {
                                this.state_ = codedInputStream.readEnum();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static DeviceState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DeviceState> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_DeviceState_descriptor;
        }

        public static DeviceState parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (DeviceState) PARSER.parseFrom(byteBuffer);
        }

        public static DeviceState parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceState) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static DeviceState parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DeviceState) PARSER.parseFrom(byteString);
        }

        public static DeviceState parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceState) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static DeviceState parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DeviceState) PARSER.parseFrom(bArr);
        }

        public static DeviceState parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceState) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static DeviceState parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static DeviceState parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DeviceState parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static DeviceState parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DeviceState parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static DeviceState parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m5179toBuilder();
        }

        public static Builder newBuilder(DeviceState deviceState) {
            return DEFAULT_INSTANCE.m5179toBuilder().mergeFrom(deviceState);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public DeviceState m5174getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<DeviceState> getParserForType() {
            return PARSER;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
        public int getStateValue() {
            return this.state_;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new DeviceState();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_DeviceState_fieldAccessorTable.ensureFieldAccessorsInitialized(DeviceState.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
        public String getDeviceName() {
            Object obj = this.deviceName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.deviceName_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
        public ByteString getDeviceNameBytes() {
            Object obj = this.deviceName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.deviceName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
        public String getComPort() {
            Object obj = this.comPort_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.comPort_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
        public ByteString getComPortBytes() {
            Object obj = this.comPort_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.comPort_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
        public String getMacId() {
            Object obj = this.macId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.macId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
        public ByteString getMacIdBytes() {
            Object obj = this.macId_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.macId_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
        public BT_STATE getState() {
            BT_STATE bt_stateValueOf = BT_STATE.valueOf(this.state_);
            return bt_stateValueOf == null ? BT_STATE.UNRECOGNIZED : bt_stateValueOf;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getDeviceNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.deviceName_);
            }
            if (!getComPortBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.comPort_);
            }
            if (!getMacIdBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.macId_);
            }
            if (this.state_ != BT_STATE.DISCONNECTED.getNumber()) {
                codedOutputStream.writeEnum(4, this.state_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getDeviceNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.deviceName_) : 0;
            if (!getComPortBytes().isEmpty()) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.comPort_);
            }
            if (!getMacIdBytes().isEmpty()) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.macId_);
            }
            if (this.state_ != BT_STATE.DISCONNECTED.getNumber()) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(4, this.state_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DeviceState)) {
                return super.equals(obj);
            }
            DeviceState deviceState = (DeviceState) obj;
            return getDeviceName().equals(deviceState.getDeviceName()) && getComPort().equals(deviceState.getComPort()) && getMacId().equals(deviceState.getMacId()) && this.state_ == deviceState.state_ && this.unknownFields.equals(deviceState.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getDeviceName().hashCode()) * 37) + 2) * 53) + getComPort().hashCode()) * 37) + 3) * 53) + getMacId().hashCode()) * 37) + 4) * 53) + this.state_) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5176newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5179toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DeviceStateOrBuilder {
            private Object comPort_;
            private Object deviceName_;
            private Object macId_;
            private int state_;

            private Builder() {
                this.deviceName_ = "";
                this.comPort_ = "";
                this.macId_ = "";
                this.state_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.deviceName_ = "";
                this.comPort_ = "";
                this.macId_ = "";
                this.state_ = 0;
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_DeviceState_descriptor;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
            public int getStateValue() {
                return this.state_;
            }

            public Builder setStateValue(int i) {
                this.state_ = i;
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_DeviceState_fieldAccessorTable.ensureFieldAccessorsInitialized(DeviceState.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = DeviceState.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5190clear() {
                super.clear();
                this.deviceName_ = "";
                this.comPort_ = "";
                this.macId_ = "";
                this.state_ = 0;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_DeviceState_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public DeviceState m5203getDefaultInstanceForType() {
                return DeviceState.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public DeviceState m5184build() throws UninitializedMessageException {
                DeviceState deviceStateM5186buildPartial = m5186buildPartial();
                if (deviceStateM5186buildPartial.isInitialized()) {
                    return deviceStateM5186buildPartial;
                }
                throw newUninitializedMessageException(deviceStateM5186buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public DeviceState m5186buildPartial() {
                DeviceState deviceState = new DeviceState(this);
                deviceState.deviceName_ = this.deviceName_;
                deviceState.comPort_ = this.comPort_;
                deviceState.macId_ = this.macId_;
                deviceState.state_ = this.state_;
                onBuilt();
                return deviceState;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5202clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5214setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5192clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5195clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5216setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5182addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5207mergeFrom(Message message) {
                if (message instanceof DeviceState) {
                    return mergeFrom((DeviceState) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(DeviceState deviceState) {
                if (deviceState == DeviceState.getDefaultInstance()) {
                    return this;
                }
                if (!deviceState.getDeviceName().isEmpty()) {
                    this.deviceName_ = deviceState.deviceName_;
                    onChanged();
                }
                if (!deviceState.getComPort().isEmpty()) {
                    this.comPort_ = deviceState.comPort_;
                    onChanged();
                }
                if (!deviceState.getMacId().isEmpty()) {
                    this.macId_ = deviceState.macId_;
                    onChanged();
                }
                if (deviceState.state_ != 0) {
                    setStateValue(deviceState.getStateValue());
                }
                m5212mergeUnknownFields(deviceState.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.DeviceState.Builder m5208mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.DeviceState.m5173$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$DeviceState r3 = (com.shimmerresearch.grpc.ShimmerGRPC.DeviceState) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$DeviceState r4 = (com.shimmerresearch.grpc.ShimmerGRPC.DeviceState) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.DeviceState.Builder.m5208mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$DeviceState$Builder");
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
            public String getDeviceName() {
                Object obj = this.deviceName_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.deviceName_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setDeviceName(String str) {
                str.getClass();
                this.deviceName_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
            public ByteString getDeviceNameBytes() {
                Object obj = this.deviceName_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.deviceName_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setDeviceNameBytes(ByteString byteString) {
                byteString.getClass();
                DeviceState.checkByteStringIsUtf8(byteString);
                this.deviceName_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearDeviceName() {
                this.deviceName_ = DeviceState.getDefaultInstance().getDeviceName();
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
            public String getComPort() {
                Object obj = this.comPort_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.comPort_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setComPort(String str) {
                str.getClass();
                this.comPort_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
            public ByteString getComPortBytes() {
                Object obj = this.comPort_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.comPort_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setComPortBytes(ByteString byteString) {
                byteString.getClass();
                DeviceState.checkByteStringIsUtf8(byteString);
                this.comPort_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearComPort() {
                this.comPort_ = DeviceState.getDefaultInstance().getComPort();
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
            public String getMacId() {
                Object obj = this.macId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.macId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setMacId(String str) {
                str.getClass();
                this.macId_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
            public ByteString getMacIdBytes() {
                Object obj = this.macId_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.macId_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMacIdBytes(ByteString byteString) {
                byteString.getClass();
                DeviceState.checkByteStringIsUtf8(byteString);
                this.macId_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearMacId() {
                this.macId_ = DeviceState.getDefaultInstance().getMacId();
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.DeviceStateOrBuilder
            public BT_STATE getState() {
                BT_STATE bt_stateValueOf = BT_STATE.valueOf(this.state_);
                return bt_stateValueOf == null ? BT_STATE.UNRECOGNIZED : bt_stateValueOf;
            }

            public Builder setState(BT_STATE bt_state) {
                bt_state.getClass();
                this.state_ = bt_state.getNumber();
                onChanged();
                return this;
            }

            public Builder clearState() {
                this.state_ = 0;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5218setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5212mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Ieee802154Info extends GeneratedMessageV3 implements Ieee802154InfoOrBuilder {
        public static final int RADIOCHANNEL_FIELD_NUMBER = 1;
        public static final int RADIODEVICEID_FIELD_NUMBER = 3;
        public static final int RADIOGROUPID_FIELD_NUMBER = 2;
        private static final Ieee802154Info DEFAULT_INSTANCE = new Ieee802154Info();
        private static final Parser<Ieee802154Info> PARSER = new AbstractParser<Ieee802154Info>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.Ieee802154Info.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Ieee802154Info m5536parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Ieee802154Info(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private int radioChannel_;
        private int radioDeviceId_;
        private int radioGroupId_;

        private Ieee802154Info(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Ieee802154Info() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private Ieee802154Info(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 8) {
                                this.radioChannel_ = codedInputStream.readInt32();
                            } else if (tag == 16) {
                                this.radioGroupId_ = codedInputStream.readInt32();
                            } else if (tag == 24) {
                                this.radioDeviceId_ = codedInputStream.readInt32();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static Ieee802154Info getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Ieee802154Info> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_Ieee802154Info_descriptor;
        }

        public static Ieee802154Info parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Ieee802154Info) PARSER.parseFrom(byteBuffer);
        }

        public static Ieee802154Info parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Ieee802154Info) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Ieee802154Info parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Ieee802154Info) PARSER.parseFrom(byteString);
        }

        public static Ieee802154Info parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Ieee802154Info) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Ieee802154Info parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Ieee802154Info) PARSER.parseFrom(bArr);
        }

        public static Ieee802154Info parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Ieee802154Info) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Ieee802154Info parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Ieee802154Info parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Ieee802154Info parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Ieee802154Info parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Ieee802154Info parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Ieee802154Info parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m5534toBuilder();
        }

        public static Builder newBuilder(Ieee802154Info ieee802154Info) {
            return DEFAULT_INSTANCE.m5534toBuilder().mergeFrom(ieee802154Info);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Ieee802154Info m5529getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Ieee802154Info> getParserForType() {
            return PARSER;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.Ieee802154InfoOrBuilder
        public int getRadioChannel() {
            return this.radioChannel_;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.Ieee802154InfoOrBuilder
        public int getRadioDeviceId() {
            return this.radioDeviceId_;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.Ieee802154InfoOrBuilder
        public int getRadioGroupId() {
            return this.radioGroupId_;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new Ieee802154Info();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_Ieee802154Info_fieldAccessorTable.ensureFieldAccessorsInitialized(Ieee802154Info.class, Builder.class);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.radioChannel_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            int i2 = this.radioGroupId_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(2, i2);
            }
            int i3 = this.radioDeviceId_;
            if (i3 != 0) {
                codedOutputStream.writeInt32(3, i3);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.radioChannel_;
            int iComputeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) : 0;
            int i3 = this.radioGroupId_;
            if (i3 != 0) {
                iComputeInt32Size += CodedOutputStream.computeInt32Size(2, i3);
            }
            int i4 = this.radioDeviceId_;
            if (i4 != 0) {
                iComputeInt32Size += CodedOutputStream.computeInt32Size(3, i4);
            }
            int serializedSize = iComputeInt32Size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Ieee802154Info)) {
                return super.equals(obj);
            }
            Ieee802154Info ieee802154Info = (Ieee802154Info) obj;
            return getRadioChannel() == ieee802154Info.getRadioChannel() && getRadioGroupId() == ieee802154Info.getRadioGroupId() && getRadioDeviceId() == ieee802154Info.getRadioDeviceId() && this.unknownFields.equals(ieee802154Info.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getRadioChannel()) * 37) + 2) * 53) + getRadioGroupId()) * 37) + 3) * 53) + getRadioDeviceId()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5531newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5534toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements Ieee802154InfoOrBuilder {
            private int radioChannel_;
            private int radioDeviceId_;
            private int radioGroupId_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_Ieee802154Info_descriptor;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.Ieee802154InfoOrBuilder
            public int getRadioChannel() {
                return this.radioChannel_;
            }

            public Builder setRadioChannel(int i) {
                this.radioChannel_ = i;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.Ieee802154InfoOrBuilder
            public int getRadioDeviceId() {
                return this.radioDeviceId_;
            }

            public Builder setRadioDeviceId(int i) {
                this.radioDeviceId_ = i;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.Ieee802154InfoOrBuilder
            public int getRadioGroupId() {
                return this.radioGroupId_;
            }

            public Builder setRadioGroupId(int i) {
                this.radioGroupId_ = i;
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_Ieee802154Info_fieldAccessorTable.ensureFieldAccessorsInitialized(Ieee802154Info.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Ieee802154Info.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5545clear() {
                super.clear();
                this.radioChannel_ = 0;
                this.radioGroupId_ = 0;
                this.radioDeviceId_ = 0;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_Ieee802154Info_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Ieee802154Info m5558getDefaultInstanceForType() {
                return Ieee802154Info.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Ieee802154Info m5539build() throws UninitializedMessageException {
                Ieee802154Info ieee802154InfoM5541buildPartial = m5541buildPartial();
                if (ieee802154InfoM5541buildPartial.isInitialized()) {
                    return ieee802154InfoM5541buildPartial;
                }
                throw newUninitializedMessageException(ieee802154InfoM5541buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Ieee802154Info m5541buildPartial() {
                Ieee802154Info ieee802154Info = new Ieee802154Info(this);
                ieee802154Info.radioChannel_ = this.radioChannel_;
                ieee802154Info.radioGroupId_ = this.radioGroupId_;
                ieee802154Info.radioDeviceId_ = this.radioDeviceId_;
                onBuilt();
                return ieee802154Info;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5557clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5569setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5547clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5550clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5571setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5537addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5562mergeFrom(Message message) {
                if (message instanceof Ieee802154Info) {
                    return mergeFrom((Ieee802154Info) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Ieee802154Info ieee802154Info) {
                if (ieee802154Info == Ieee802154Info.getDefaultInstance()) {
                    return this;
                }
                if (ieee802154Info.getRadioChannel() != 0) {
                    setRadioChannel(ieee802154Info.getRadioChannel());
                }
                if (ieee802154Info.getRadioGroupId() != 0) {
                    setRadioGroupId(ieee802154Info.getRadioGroupId());
                }
                if (ieee802154Info.getRadioDeviceId() != 0) {
                    setRadioDeviceId(ieee802154Info.getRadioDeviceId());
                }
                m5567mergeUnknownFields(ieee802154Info.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.Ieee802154Info.Builder m5563mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.Ieee802154Info.m5528$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$Ieee802154Info r3 = (com.shimmerresearch.grpc.ShimmerGRPC.Ieee802154Info) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$Ieee802154Info r4 = (com.shimmerresearch.grpc.ShimmerGRPC.Ieee802154Info) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.Ieee802154Info.Builder.m5563mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$Ieee802154Info$Builder");
            }

            public Builder clearRadioChannel() {
                this.radioChannel_ = 0;
                onChanged();
                return this;
            }

            public Builder clearRadioGroupId() {
                this.radioGroupId_ = 0;
                onChanged();
                return this;
            }

            public Builder clearRadioDeviceId() {
                this.radioDeviceId_ = 0;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5573setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5567mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class InfoSpans extends GeneratedMessageV3 implements InfoSpansOrBuilder {
        public static final int IEEE802154INFO_FIELD_NUMBER = 4;
        public static final int MESSAGE_FIELD_NUMBER = 2;
        public static final int SPANMAP_FIELD_NUMBER = 3;
        public static final int STATE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final InfoSpans DEFAULT_INSTANCE = new InfoSpans();
        private static final Parser<InfoSpans> PARSER = new AbstractParser<InfoSpans>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public InfoSpans m5590parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new InfoSpans(codedInputStream, extensionRegistryLite);
            }
        };
        private Ieee802154Info ieee802154Info_;
        private byte memoizedIsInitialized;
        private volatile Object message_;
        private MapField<String, InfoSpan> spanMap_;
        private boolean state_;

        private InfoSpans(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private InfoSpans() {
            this.memoizedIsInitialized = (byte) -1;
            this.message_ = "";
        }

        private InfoSpans(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 8) {
                                this.state_ = codedInputStream.readBool();
                            } else if (tag == 18) {
                                this.message_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 26) {
                                if (!(z2 & true)) {
                                    this.spanMap_ = MapField.newMapField(SpanMapDefaultEntryHolder.defaultEntry);
                                    z2 |= true;
                                }
                                MapEntry message = codedInputStream.readMessage(SpanMapDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.spanMap_.getMutableMap().put((String) message.getKey(), (InfoSpan) message.getValue());
                            } else if (tag == 34) {
                                Ieee802154Info ieee802154Info = this.ieee802154Info_;
                                Ieee802154Info.Builder builderM5534toBuilder = ieee802154Info != null ? ieee802154Info.m5534toBuilder() : null;
                                Ieee802154Info ieee802154Info2 = (Ieee802154Info) codedInputStream.readMessage(Ieee802154Info.parser(), extensionRegistryLite);
                                this.ieee802154Info_ = ieee802154Info2;
                                if (builderM5534toBuilder != null) {
                                    builderM5534toBuilder.mergeFrom(ieee802154Info2);
                                    this.ieee802154Info_ = builderM5534toBuilder.m5541buildPartial();
                                }
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static InfoSpans getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<InfoSpans> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_InfoSpans_descriptor;
        }

        public static InfoSpans parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (InfoSpans) PARSER.parseFrom(byteBuffer);
        }

        public static InfoSpans parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InfoSpans) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static InfoSpans parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (InfoSpans) PARSER.parseFrom(byteString);
        }

        public static InfoSpans parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InfoSpans) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static InfoSpans parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (InfoSpans) PARSER.parseFrom(bArr);
        }

        public static InfoSpans parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InfoSpans) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static InfoSpans parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static InfoSpans parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static InfoSpans parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static InfoSpans parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static InfoSpans parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static InfoSpans parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m5588toBuilder();
        }

        public static Builder newBuilder(InfoSpans infoSpans) {
            return DEFAULT_INSTANCE.m5588toBuilder().mergeFrom(infoSpans);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public InfoSpans m5583getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<InfoSpans> getParserForType() {
            return PARSER;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
        public boolean getState() {
            return this.state_;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
        public boolean hasIeee802154Info() {
            return this.ieee802154Info_ != null;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new InfoSpans();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 3) {
                return internalGetSpanMap();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_InfoSpans_fieldAccessorTable.ensureFieldAccessorsInitialized(InfoSpans.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
        public String getMessage() {
            Object obj = this.message_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.message_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
        public ByteString getMessageBytes() {
            Object obj = this.message_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.message_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MapField<String, InfoSpan> internalGetSpanMap() {
            MapField<String, InfoSpan> mapField = this.spanMap_;
            return mapField == null ? MapField.emptyMapField(SpanMapDefaultEntryHolder.defaultEntry) : mapField;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
        public int getSpanMapCount() {
            return internalGetSpanMap().getMap().size();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
        public boolean containsSpanMap(String str) {
            str.getClass();
            return internalGetSpanMap().getMap().containsKey(str);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
        @Deprecated
        public Map<String, InfoSpan> getSpanMap() {
            return getSpanMapMap();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
        public Map<String, InfoSpan> getSpanMapMap() {
            return internalGetSpanMap().getMap();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
        public InfoSpan getSpanMapOrDefault(String str, InfoSpan infoSpan) {
            str.getClass();
            Map map = internalGetSpanMap().getMap();
            return map.containsKey(str) ? (InfoSpan) map.get(str) : infoSpan;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
        public InfoSpan getSpanMapOrThrow(String str) {
            str.getClass();
            Map map = internalGetSpanMap().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return (InfoSpan) map.get(str);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
        public Ieee802154Info getIeee802154Info() {
            Ieee802154Info ieee802154Info = this.ieee802154Info_;
            return ieee802154Info == null ? Ieee802154Info.getDefaultInstance() : ieee802154Info;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
        public Ieee802154InfoOrBuilder getIeee802154InfoOrBuilder() {
            return getIeee802154Info();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.state_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            if (!getMessageBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.message_);
            }
            GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetSpanMap(), SpanMapDefaultEntryHolder.defaultEntry, 3);
            if (this.ieee802154Info_ != null) {
                codedOutputStream.writeMessage(4, getIeee802154Info());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            boolean z = this.state_;
            int iComputeBoolSize = z ? CodedOutputStream.computeBoolSize(1, z) : 0;
            if (!getMessageBytes().isEmpty()) {
                iComputeBoolSize += GeneratedMessageV3.computeStringSize(2, this.message_);
            }
            for (Map.Entry entry : internalGetSpanMap().getMap().entrySet()) {
                iComputeBoolSize += CodedOutputStream.computeMessageSize(3, SpanMapDefaultEntryHolder.defaultEntry.newBuilderForType().setKey((String) entry.getKey()).setValue((InfoSpan) entry.getValue()).build());
            }
            if (this.ieee802154Info_ != null) {
                iComputeBoolSize += CodedOutputStream.computeMessageSize(4, getIeee802154Info());
            }
            int serializedSize = iComputeBoolSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof InfoSpans)) {
                return super.equals(obj);
            }
            InfoSpans infoSpans = (InfoSpans) obj;
            if (getState() == infoSpans.getState() && getMessage().equals(infoSpans.getMessage()) && internalGetSpanMap().equals(infoSpans.internalGetSpanMap()) && hasIeee802154Info() == infoSpans.hasIeee802154Info()) {
                return (!hasIeee802154Info() || getIeee802154Info().equals(infoSpans.getIeee802154Info())) && this.unknownFields.equals(infoSpans.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getState())) * 37) + 2) * 53) + getMessage().hashCode();
            if (!internalGetSpanMap().getMap().isEmpty()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + internalGetSpanMap().hashCode();
            }
            if (hasIeee802154Info()) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + getIeee802154Info().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5585newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5588toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public interface InfoSpanOrBuilder extends MessageOrBuilder {
            String getPortBsl();

            ByteString getPortBslBytes();

            String getPortComms();

            ByteString getPortCommsBytes();

            String getUniqueId();

            ByteString getUniqueIdBytes();
        }

        public static final class InfoSpan extends GeneratedMessageV3 implements InfoSpanOrBuilder {
            public static final int PORTBSL_FIELD_NUMBER = 2;
            public static final int PORTCOMMS_FIELD_NUMBER = 3;
            public static final int UNIQUEID_FIELD_NUMBER = 1;
            private static final InfoSpan DEFAULT_INSTANCE = new InfoSpan();
            private static final Parser<InfoSpan> PARSER = new AbstractParser<InfoSpan>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpan.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public InfoSpan m5643parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new InfoSpan(codedInputStream, extensionRegistryLite);
                }
            };
            private static final long serialVersionUID = 0;
            private byte memoizedIsInitialized;
            private volatile Object portBsl_;
            private volatile Object portComms_;
            private volatile Object uniqueId_;

            private InfoSpan(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private InfoSpan() {
                this.memoizedIsInitialized = (byte) -1;
                this.uniqueId_ = "";
                this.portBsl_ = "";
                this.portComms_ = "";
            }

            private InfoSpan(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.uniqueId_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 18) {
                                    this.portBsl_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 26) {
                                    this.portComms_ = codedInputStream.readStringRequireUtf8();
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        } catch (InvalidProtocolBufferException e2) {
                            throw e2.setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = builderNewBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static InfoSpan getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<InfoSpan> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_InfoSpans_InfoSpan_descriptor;
            }

            public static InfoSpan parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (InfoSpan) PARSER.parseFrom(byteBuffer);
            }

            public static InfoSpan parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (InfoSpan) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static InfoSpan parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (InfoSpan) PARSER.parseFrom(byteString);
            }

            public static InfoSpan parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (InfoSpan) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static InfoSpan parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (InfoSpan) PARSER.parseFrom(bArr);
            }

            public static InfoSpan parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (InfoSpan) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static InfoSpan parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static InfoSpan parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static InfoSpan parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static InfoSpan parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static InfoSpan parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static InfoSpan parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m5641toBuilder();
            }

            public static Builder newBuilder(InfoSpan infoSpan) {
                return DEFAULT_INSTANCE.m5641toBuilder().mergeFrom(infoSpan);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public InfoSpan m5636getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public Parser<InfoSpan> getParserForType() {
                return PARSER;
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new InfoSpan();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_InfoSpans_InfoSpan_fieldAccessorTable.ensureFieldAccessorsInitialized(InfoSpan.class, Builder.class);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpanOrBuilder
            public String getUniqueId() {
                Object obj = this.uniqueId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.uniqueId_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpanOrBuilder
            public ByteString getUniqueIdBytes() {
                Object obj = this.uniqueId_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.uniqueId_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpanOrBuilder
            public String getPortBsl() {
                Object obj = this.portBsl_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.portBsl_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpanOrBuilder
            public ByteString getPortBslBytes() {
                Object obj = this.portBsl_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.portBsl_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpanOrBuilder
            public String getPortComms() {
                Object obj = this.portComms_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.portComms_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpanOrBuilder
            public ByteString getPortCommsBytes() {
                Object obj = this.portComms_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.portComms_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (!getUniqueIdBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.uniqueId_);
                }
                if (!getPortBslBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.portBsl_);
                }
                if (!getPortCommsBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.portComms_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeStringSize = !getUniqueIdBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.uniqueId_) : 0;
                if (!getPortBslBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.portBsl_);
                }
                if (!getPortCommsBytes().isEmpty()) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.portComms_);
                }
                int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof InfoSpan)) {
                    return super.equals(obj);
                }
                InfoSpan infoSpan = (InfoSpan) obj;
                return getUniqueId().equals(infoSpan.getUniqueId()) && getPortBsl().equals(infoSpan.getPortBsl()) && getPortComms().equals(infoSpan.getPortComms()) && this.unknownFields.equals(infoSpan.unknownFields);
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getUniqueId().hashCode()) * 37) + 2) * 53) + getPortBsl().hashCode()) * 37) + 3) * 53) + getPortComms().hashCode()) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode;
                return iHashCode;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5638newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5641toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements InfoSpanOrBuilder {
                private Object portBsl_;
                private Object portComms_;
                private Object uniqueId_;

                private Builder() {
                    this.uniqueId_ = "";
                    this.portBsl_ = "";
                    this.portComms_ = "";
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.uniqueId_ = "";
                    this.portBsl_ = "";
                    this.portComms_ = "";
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_InfoSpans_InfoSpan_descriptor;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_InfoSpans_InfoSpan_fieldAccessorTable.ensureFieldAccessorsInitialized(InfoSpan.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = InfoSpan.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5652clear() {
                    super.clear();
                    this.uniqueId_ = "";
                    this.portBsl_ = "";
                    this.portComms_ = "";
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_InfoSpans_InfoSpan_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public InfoSpan m5665getDefaultInstanceForType() {
                    return InfoSpan.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public InfoSpan m5646build() throws UninitializedMessageException {
                    InfoSpan infoSpanM5648buildPartial = m5648buildPartial();
                    if (infoSpanM5648buildPartial.isInitialized()) {
                        return infoSpanM5648buildPartial;
                    }
                    throw newUninitializedMessageException(infoSpanM5648buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public InfoSpan m5648buildPartial() {
                    InfoSpan infoSpan = new InfoSpan(this);
                    infoSpan.uniqueId_ = this.uniqueId_;
                    infoSpan.portBsl_ = this.portBsl_;
                    infoSpan.portComms_ = this.portComms_;
                    onBuilt();
                    return infoSpan;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5664clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5676setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5654clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5657clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5678setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5644addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5669mergeFrom(Message message) {
                    if (message instanceof InfoSpan) {
                        return mergeFrom((InfoSpan) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(InfoSpan infoSpan) {
                    if (infoSpan == InfoSpan.getDefaultInstance()) {
                        return this;
                    }
                    if (!infoSpan.getUniqueId().isEmpty()) {
                        this.uniqueId_ = infoSpan.uniqueId_;
                        onChanged();
                    }
                    if (!infoSpan.getPortBsl().isEmpty()) {
                        this.portBsl_ = infoSpan.portBsl_;
                        onChanged();
                    }
                    if (!infoSpan.getPortComms().isEmpty()) {
                        this.portComms_ = infoSpan.portComms_;
                        onChanged();
                    }
                    m5674mergeUnknownFields(infoSpan.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpan.Builder m5670mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpan.m5635$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.shimmerresearch.grpc.ShimmerGRPC$InfoSpans$InfoSpan r3 = (com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpan) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        if (r3 == 0) goto L10
                        r2.mergeFrom(r3)
                    L10:
                        return r2
                    L11:
                        r3 = move-exception
                        goto L21
                    L13:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                        com.shimmerresearch.grpc.ShimmerGRPC$InfoSpans$InfoSpan r4 = (com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpan) r4     // Catch: java.lang.Throwable -> L11
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                        throw r3     // Catch: java.lang.Throwable -> L1f
                    L1f:
                        r3 = move-exception
                        r0 = r4
                    L21:
                        if (r0 == 0) goto L26
                        r2.mergeFrom(r0)
                    L26:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpan.Builder.m5670mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$InfoSpans$InfoSpan$Builder");
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpanOrBuilder
                public String getUniqueId() {
                    Object obj = this.uniqueId_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.uniqueId_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setUniqueId(String str) {
                    str.getClass();
                    this.uniqueId_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpanOrBuilder
                public ByteString getUniqueIdBytes() {
                    Object obj = this.uniqueId_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.uniqueId_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setUniqueIdBytes(ByteString byteString) {
                    byteString.getClass();
                    InfoSpan.checkByteStringIsUtf8(byteString);
                    this.uniqueId_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearUniqueId() {
                    this.uniqueId_ = InfoSpan.getDefaultInstance().getUniqueId();
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpanOrBuilder
                public String getPortBsl() {
                    Object obj = this.portBsl_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.portBsl_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setPortBsl(String str) {
                    str.getClass();
                    this.portBsl_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpanOrBuilder
                public ByteString getPortBslBytes() {
                    Object obj = this.portBsl_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.portBsl_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setPortBslBytes(ByteString byteString) {
                    byteString.getClass();
                    InfoSpan.checkByteStringIsUtf8(byteString);
                    this.portBsl_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearPortBsl() {
                    this.portBsl_ = InfoSpan.getDefaultInstance().getPortBsl();
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpanOrBuilder
                public String getPortComms() {
                    Object obj = this.portComms_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.portComms_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setPortComms(String str) {
                    str.getClass();
                    this.portComms_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.InfoSpanOrBuilder
                public ByteString getPortCommsBytes() {
                    Object obj = this.portComms_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.portComms_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setPortCommsBytes(ByteString byteString) {
                    byteString.getClass();
                    InfoSpan.checkByteStringIsUtf8(byteString);
                    this.portComms_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearPortComms() {
                    this.portComms_ = InfoSpan.getDefaultInstance().getPortComms();
                    onChanged();
                    return this;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m5680setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m5674mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        private static final class SpanMapDefaultEntryHolder {
            static final MapEntry<String, InfoSpan> defaultEntry = MapEntry.newDefaultInstance(ShimmerGRPC.internal_static_shimmerGRPC_InfoSpans_SpanMapEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, InfoSpan.getDefaultInstance());

            private SpanMapDefaultEntryHolder() {
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements InfoSpansOrBuilder {
            private int bitField0_;
            private SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> ieee802154InfoBuilder_;
            private Ieee802154Info ieee802154Info_;
            private Object message_;
            private MapField<String, InfoSpan> spanMap_;
            private boolean state_;

            private Builder() {
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_InfoSpans_descriptor;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
            public boolean getState() {
                return this.state_;
            }

            public Builder setState(boolean z) {
                this.state_ = z;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
            public boolean hasIeee802154Info() {
                return (this.ieee802154InfoBuilder_ == null && this.ieee802154Info_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected MapField internalGetMapField(int i) {
                if (i == 3) {
                    return internalGetSpanMap();
                }
                throw new RuntimeException("Invalid map field number: " + i);
            }

            protected MapField internalGetMutableMapField(int i) {
                if (i == 3) {
                    return internalGetMutableSpanMap();
                }
                throw new RuntimeException("Invalid map field number: " + i);
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_InfoSpans_fieldAccessorTable.ensureFieldAccessorsInitialized(InfoSpans.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = InfoSpans.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5599clear() {
                super.clear();
                this.state_ = false;
                this.message_ = "";
                internalGetMutableSpanMap().clear();
                if (this.ieee802154InfoBuilder_ == null) {
                    this.ieee802154Info_ = null;
                } else {
                    this.ieee802154Info_ = null;
                    this.ieee802154InfoBuilder_ = null;
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_InfoSpans_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public InfoSpans m5612getDefaultInstanceForType() {
                return InfoSpans.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public InfoSpans m5593build() throws UninitializedMessageException {
                InfoSpans infoSpansM5595buildPartial = m5595buildPartial();
                if (infoSpansM5595buildPartial.isInitialized()) {
                    return infoSpansM5595buildPartial;
                }
                throw newUninitializedMessageException(infoSpansM5595buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public InfoSpans m5595buildPartial() {
                InfoSpans infoSpans = new InfoSpans(this);
                infoSpans.state_ = this.state_;
                infoSpans.message_ = this.message_;
                infoSpans.spanMap_ = internalGetSpanMap();
                infoSpans.spanMap_.makeImmutable();
                SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> singleFieldBuilderV3 = this.ieee802154InfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    infoSpans.ieee802154Info_ = this.ieee802154Info_;
                } else {
                    infoSpans.ieee802154Info_ = singleFieldBuilderV3.build();
                }
                onBuilt();
                return infoSpans;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5611clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5623setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5601clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5604clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5625setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5591addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5616mergeFrom(Message message) {
                if (message instanceof InfoSpans) {
                    return mergeFrom((InfoSpans) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(InfoSpans infoSpans) {
                if (infoSpans == InfoSpans.getDefaultInstance()) {
                    return this;
                }
                if (infoSpans.getState()) {
                    setState(infoSpans.getState());
                }
                if (!infoSpans.getMessage().isEmpty()) {
                    this.message_ = infoSpans.message_;
                    onChanged();
                }
                internalGetMutableSpanMap().mergeFrom(infoSpans.internalGetSpanMap());
                if (infoSpans.hasIeee802154Info()) {
                    mergeIeee802154Info(infoSpans.getIeee802154Info());
                }
                m5621mergeUnknownFields(infoSpans.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.Builder m5617mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.m5582$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$InfoSpans r3 = (com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$InfoSpans r4 = (com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.InfoSpans.Builder.m5617mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$InfoSpans$Builder");
            }

            public Builder clearState() {
                this.state_ = false;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
            public String getMessage() {
                Object obj = this.message_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.message_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setMessage(String str) {
                str.getClass();
                this.message_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
            public ByteString getMessageBytes() {
                Object obj = this.message_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.message_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMessageBytes(ByteString byteString) {
                byteString.getClass();
                InfoSpans.checkByteStringIsUtf8(byteString);
                this.message_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearMessage() {
                this.message_ = InfoSpans.getDefaultInstance().getMessage();
                onChanged();
                return this;
            }

            private MapField<String, InfoSpan> internalGetSpanMap() {
                MapField<String, InfoSpan> mapField = this.spanMap_;
                return mapField == null ? MapField.emptyMapField(SpanMapDefaultEntryHolder.defaultEntry) : mapField;
            }

            private MapField<String, InfoSpan> internalGetMutableSpanMap() {
                onChanged();
                if (this.spanMap_ == null) {
                    this.spanMap_ = MapField.newMapField(SpanMapDefaultEntryHolder.defaultEntry);
                }
                if (!this.spanMap_.isMutable()) {
                    this.spanMap_ = this.spanMap_.copy();
                }
                return this.spanMap_;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
            public int getSpanMapCount() {
                return internalGetSpanMap().getMap().size();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
            public boolean containsSpanMap(String str) {
                str.getClass();
                return internalGetSpanMap().getMap().containsKey(str);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
            @Deprecated
            public Map<String, InfoSpan> getSpanMap() {
                return getSpanMapMap();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
            public Map<String, InfoSpan> getSpanMapMap() {
                return internalGetSpanMap().getMap();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
            public InfoSpan getSpanMapOrDefault(String str, InfoSpan infoSpan) {
                str.getClass();
                Map map = internalGetSpanMap().getMap();
                return map.containsKey(str) ? (InfoSpan) map.get(str) : infoSpan;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
            public InfoSpan getSpanMapOrThrow(String str) {
                str.getClass();
                Map map = internalGetSpanMap().getMap();
                if (!map.containsKey(str)) {
                    throw new IllegalArgumentException();
                }
                return (InfoSpan) map.get(str);
            }

            public Builder clearSpanMap() {
                internalGetMutableSpanMap().getMutableMap().clear();
                return this;
            }

            public Builder removeSpanMap(String str) {
                str.getClass();
                internalGetMutableSpanMap().getMutableMap().remove(str);
                return this;
            }

            @Deprecated
            public Map<String, InfoSpan> getMutableSpanMap() {
                return internalGetMutableSpanMap().getMutableMap();
            }

            public Builder putSpanMap(String str, InfoSpan infoSpan) {
                str.getClass();
                infoSpan.getClass();
                internalGetMutableSpanMap().getMutableMap().put(str, infoSpan);
                return this;
            }

            public Builder putAllSpanMap(Map<String, InfoSpan> map) {
                internalGetMutableSpanMap().getMutableMap().putAll(map);
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
            public Ieee802154Info getIeee802154Info() {
                SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> singleFieldBuilderV3 = this.ieee802154InfoBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Ieee802154Info ieee802154Info = this.ieee802154Info_;
                return ieee802154Info == null ? Ieee802154Info.getDefaultInstance() : ieee802154Info;
            }

            public Builder setIeee802154Info(Ieee802154Info ieee802154Info) {
                SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> singleFieldBuilderV3 = this.ieee802154InfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ieee802154Info.getClass();
                    this.ieee802154Info_ = ieee802154Info;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(ieee802154Info);
                }
                return this;
            }

            public Builder setIeee802154Info(Ieee802154Info.Builder builder) {
                SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> singleFieldBuilderV3 = this.ieee802154InfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.ieee802154Info_ = builder.m5539build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m5539build());
                }
                return this;
            }

            public Builder mergeIeee802154Info(Ieee802154Info ieee802154Info) {
                SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> singleFieldBuilderV3 = this.ieee802154InfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Ieee802154Info ieee802154Info2 = this.ieee802154Info_;
                    if (ieee802154Info2 != null) {
                        this.ieee802154Info_ = Ieee802154Info.newBuilder(ieee802154Info2).mergeFrom(ieee802154Info).m5541buildPartial();
                    } else {
                        this.ieee802154Info_ = ieee802154Info;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(ieee802154Info);
                }
                return this;
            }

            public Builder clearIeee802154Info() {
                if (this.ieee802154InfoBuilder_ == null) {
                    this.ieee802154Info_ = null;
                    onChanged();
                } else {
                    this.ieee802154Info_ = null;
                    this.ieee802154InfoBuilder_ = null;
                }
                return this;
            }

            public Ieee802154Info.Builder getIeee802154InfoBuilder() {
                onChanged();
                return getIeee802154InfoFieldBuilder().getBuilder();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.InfoSpansOrBuilder
            public Ieee802154InfoOrBuilder getIeee802154InfoOrBuilder() {
                SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> singleFieldBuilderV3 = this.ieee802154InfoBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (Ieee802154InfoOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Ieee802154Info ieee802154Info = this.ieee802154Info_;
                return ieee802154Info == null ? Ieee802154Info.getDefaultInstance() : ieee802154Info;
            }

            private SingleFieldBuilderV3<Ieee802154Info, Ieee802154Info.Builder, Ieee802154InfoOrBuilder> getIeee802154InfoFieldBuilder() {
                if (this.ieee802154InfoBuilder_ == null) {
                    this.ieee802154InfoBuilder_ = new SingleFieldBuilderV3<>(getIeee802154Info(), getParentForChildren(), isClean());
                    this.ieee802154Info_ = null;
                }
                return this.ieee802154InfoBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5627setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5621mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class EmulatedDevices extends GeneratedMessageV3 implements EmulatedDevicesOrBuilder {
        public static final int EMULATEDDEVICESMAP_FIELD_NUMBER = 4;
        public static final int ISEMULATORSIDE_FIELD_NUMBER = 3;
        public static final int MESSAGE_FIELD_NUMBER = 2;
        public static final int STATE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final EmulatedDevices DEFAULT_INSTANCE = new EmulatedDevices();
        private static final Parser<EmulatedDevices> PARSER = new AbstractParser<EmulatedDevices>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public EmulatedDevices m5283parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new EmulatedDevices(codedInputStream, extensionRegistryLite);
            }
        };
        private MapField<String, EmulatedDevice> emulatedDevicesMap_;
        private boolean isEmulatorSide_;
        private byte memoizedIsInitialized;
        private volatile Object message_;
        private boolean state_;

        private EmulatedDevices(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private EmulatedDevices() {
            this.memoizedIsInitialized = (byte) -1;
            this.message_ = "";
        }

        private EmulatedDevices(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 8) {
                                    this.state_ = codedInputStream.readBool();
                                } else if (tag == 18) {
                                    this.message_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 24) {
                                    this.isEmulatorSide_ = codedInputStream.readBool();
                                } else if (tag == 34) {
                                    if (!(z2 & true)) {
                                        this.emulatedDevicesMap_ = MapField.newMapField(EmulatedDevicesMapDefaultEntryHolder.defaultEntry);
                                        z2 |= true;
                                    }
                                    MapEntry message = codedInputStream.readMessage(EmulatedDevicesMapDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                    this.emulatedDevicesMap_.getMutableMap().put((String) message.getKey(), (EmulatedDevice) message.getValue());
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static EmulatedDevices getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<EmulatedDevices> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerGRPC.internal_static_shimmerGRPC_EmulatedDevices_descriptor;
        }

        public static EmulatedDevices parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (EmulatedDevices) PARSER.parseFrom(byteBuffer);
        }

        public static EmulatedDevices parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EmulatedDevices) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static EmulatedDevices parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (EmulatedDevices) PARSER.parseFrom(byteString);
        }

        public static EmulatedDevices parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EmulatedDevices) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static EmulatedDevices parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (EmulatedDevices) PARSER.parseFrom(bArr);
        }

        public static EmulatedDevices parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EmulatedDevices) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static EmulatedDevices parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static EmulatedDevices parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static EmulatedDevices parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static EmulatedDevices parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static EmulatedDevices parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static EmulatedDevices parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m5281toBuilder();
        }

        public static Builder newBuilder(EmulatedDevices emulatedDevices) {
            return DEFAULT_INSTANCE.m5281toBuilder().mergeFrom(emulatedDevices);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public EmulatedDevices m5276getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
        public boolean getIsEmulatorSide() {
            return this.isEmulatorSide_;
        }

        public Parser<EmulatedDevices> getParserForType() {
            return PARSER;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
        public boolean getState() {
            return this.state_;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new EmulatedDevices();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 4) {
                return internalGetEmulatedDevicesMap();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerGRPC.internal_static_shimmerGRPC_EmulatedDevices_fieldAccessorTable.ensureFieldAccessorsInitialized(EmulatedDevices.class, Builder.class);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
        public String getMessage() {
            Object obj = this.message_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.message_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
        public ByteString getMessageBytes() {
            Object obj = this.message_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.message_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MapField<String, EmulatedDevice> internalGetEmulatedDevicesMap() {
            MapField<String, EmulatedDevice> mapField = this.emulatedDevicesMap_;
            return mapField == null ? MapField.emptyMapField(EmulatedDevicesMapDefaultEntryHolder.defaultEntry) : mapField;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
        public int getEmulatedDevicesMapCount() {
            return internalGetEmulatedDevicesMap().getMap().size();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
        public boolean containsEmulatedDevicesMap(String str) {
            str.getClass();
            return internalGetEmulatedDevicesMap().getMap().containsKey(str);
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
        @Deprecated
        public Map<String, EmulatedDevice> getEmulatedDevicesMap() {
            return getEmulatedDevicesMapMap();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
        public Map<String, EmulatedDevice> getEmulatedDevicesMapMap() {
            return internalGetEmulatedDevicesMap().getMap();
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
        public EmulatedDevice getEmulatedDevicesMapOrDefault(String str, EmulatedDevice emulatedDevice) {
            str.getClass();
            Map map = internalGetEmulatedDevicesMap().getMap();
            return map.containsKey(str) ? (EmulatedDevice) map.get(str) : emulatedDevice;
        }

        @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
        public EmulatedDevice getEmulatedDevicesMapOrThrow(String str) {
            str.getClass();
            Map map = internalGetEmulatedDevicesMap().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return (EmulatedDevice) map.get(str);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.state_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            if (!getMessageBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.message_);
            }
            boolean z2 = this.isEmulatorSide_;
            if (z2) {
                codedOutputStream.writeBool(3, z2);
            }
            GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetEmulatedDevicesMap(), EmulatedDevicesMapDefaultEntryHolder.defaultEntry, 4);
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            boolean z = this.state_;
            int iComputeBoolSize = z ? CodedOutputStream.computeBoolSize(1, z) : 0;
            if (!getMessageBytes().isEmpty()) {
                iComputeBoolSize += GeneratedMessageV3.computeStringSize(2, this.message_);
            }
            boolean z2 = this.isEmulatorSide_;
            if (z2) {
                iComputeBoolSize += CodedOutputStream.computeBoolSize(3, z2);
            }
            for (Map.Entry entry : internalGetEmulatedDevicesMap().getMap().entrySet()) {
                iComputeBoolSize += CodedOutputStream.computeMessageSize(4, EmulatedDevicesMapDefaultEntryHolder.defaultEntry.newBuilderForType().setKey((String) entry.getKey()).setValue((EmulatedDevice) entry.getValue()).build());
            }
            int serializedSize = iComputeBoolSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EmulatedDevices)) {
                return super.equals(obj);
            }
            EmulatedDevices emulatedDevices = (EmulatedDevices) obj;
            return getState() == emulatedDevices.getState() && getMessage().equals(emulatedDevices.getMessage()) && getIsEmulatorSide() == emulatedDevices.getIsEmulatorSide() && internalGetEmulatedDevicesMap().equals(emulatedDevices.internalGetEmulatedDevicesMap()) && this.unknownFields.equals(emulatedDevices.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getState())) * 37) + 2) * 53) + getMessage().hashCode()) * 37) + 3) * 53) + Internal.hashBoolean(getIsEmulatorSide());
            if (!internalGetEmulatedDevicesMap().getMap().isEmpty()) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + internalGetEmulatedDevicesMap().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5278newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m5281toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public interface EmulatedDeviceOrBuilder extends MessageOrBuilder {
            int getDeviceTypeOrdinal();

            String getHwDeviceInterfacePath(int i);

            ByteString getHwDeviceInterfacePathBytes(int i);

            int getHwDeviceInterfacePathCount();

            /* renamed from: getHwDeviceInterfacePathList */
            List<String> mo5331getHwDeviceInterfacePathList();

            boolean getIsDeviceEnabled();

            String getUniqueId();

            ByteString getUniqueIdBytes();
        }

        public static final class EmulatedDevice extends GeneratedMessageV3 implements EmulatedDeviceOrBuilder {
            public static final int DEVICETYPEORDINAL_FIELD_NUMBER = 2;
            public static final int HWDEVICEINTERFACEPATH_FIELD_NUMBER = 3;
            public static final int ISDEVICEENABLED_FIELD_NUMBER = 4;
            public static final int UNIQUEID_FIELD_NUMBER = 1;
            private static final long serialVersionUID = 0;
            private static final EmulatedDevice DEFAULT_INSTANCE = new EmulatedDevice();
            private static final Parser<EmulatedDevice> PARSER = new AbstractParser<EmulatedDevice>() { // from class: com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDevice.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public EmulatedDevice m5337parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new EmulatedDevice(codedInputStream, extensionRegistryLite);
                }
            };
            private int deviceTypeOrdinal_;
            private LazyStringList hwDeviceInterfacePath_;
            private boolean isDeviceEnabled_;
            private byte memoizedIsInitialized;
            private volatile Object uniqueId_;

            private EmulatedDevice(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private EmulatedDevice() {
                this.memoizedIsInitialized = (byte) -1;
                this.uniqueId_ = "";
                this.hwDeviceInterfacePath_ = LazyStringArrayList.EMPTY;
            }

            private EmulatedDevice(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.uniqueId_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 16) {
                                    this.deviceTypeOrdinal_ = codedInputStream.readInt32();
                                } else if (tag == 26) {
                                    String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                    if (!(z2 & true)) {
                                        this.hwDeviceInterfacePath_ = new LazyStringArrayList();
                                        z2 |= true;
                                    }
                                    this.hwDeviceInterfacePath_.add(stringRequireUtf8);
                                } else if (tag == 32) {
                                    this.isDeviceEnabled_ = codedInputStream.readBool();
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        if (z2 & true) {
                            this.hwDeviceInterfacePath_ = this.hwDeviceInterfacePath_.getUnmodifiableView();
                        }
                        this.unknownFields = builderNewBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static EmulatedDevice getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<EmulatedDevice> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_EmulatedDevices_EmulatedDevice_descriptor;
            }

            public static EmulatedDevice parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (EmulatedDevice) PARSER.parseFrom(byteBuffer);
            }

            public static EmulatedDevice parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (EmulatedDevice) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static EmulatedDevice parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (EmulatedDevice) PARSER.parseFrom(byteString);
            }

            public static EmulatedDevice parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (EmulatedDevice) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static EmulatedDevice parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (EmulatedDevice) PARSER.parseFrom(bArr);
            }

            public static EmulatedDevice parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (EmulatedDevice) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static EmulatedDevice parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static EmulatedDevice parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static EmulatedDevice parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static EmulatedDevice parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static EmulatedDevice parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static EmulatedDevice parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m5335toBuilder();
            }

            public static Builder newBuilder(EmulatedDevice emulatedDevice) {
                return DEFAULT_INSTANCE.m5335toBuilder().mergeFrom(emulatedDevice);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public EmulatedDevice m5329getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
            public int getDeviceTypeOrdinal() {
                return this.deviceTypeOrdinal_;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
            /* renamed from: getHwDeviceInterfacePathList, reason: merged with bridge method [inline-methods] */
            public ProtocolStringList mo5331getHwDeviceInterfacePathList() {
                return this.hwDeviceInterfacePath_;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
            public boolean getIsDeviceEnabled() {
                return this.isDeviceEnabled_;
            }

            public Parser<EmulatedDevice> getParserForType() {
                return PARSER;
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new EmulatedDevice();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_EmulatedDevices_EmulatedDevice_fieldAccessorTable.ensureFieldAccessorsInitialized(EmulatedDevice.class, Builder.class);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
            public String getUniqueId() {
                Object obj = this.uniqueId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.uniqueId_ = stringUtf8;
                return stringUtf8;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
            public ByteString getUniqueIdBytes() {
                Object obj = this.uniqueId_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.uniqueId_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
            public int getHwDeviceInterfacePathCount() {
                return this.hwDeviceInterfacePath_.size();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
            public String getHwDeviceInterfacePath(int i) {
                return (String) this.hwDeviceInterfacePath_.get(i);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
            public ByteString getHwDeviceInterfacePathBytes(int i) {
                return this.hwDeviceInterfacePath_.getByteString(i);
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (!getUniqueIdBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.uniqueId_);
                }
                int i = this.deviceTypeOrdinal_;
                if (i != 0) {
                    codedOutputStream.writeInt32(2, i);
                }
                for (int i2 = 0; i2 < this.hwDeviceInterfacePath_.size(); i2++) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.hwDeviceInterfacePath_.getRaw(i2));
                }
                boolean z = this.isDeviceEnabled_;
                if (z) {
                    codedOutputStream.writeBool(4, z);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeStringSize = !getUniqueIdBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.uniqueId_) : 0;
                int i2 = this.deviceTypeOrdinal_;
                if (i2 != 0) {
                    iComputeStringSize += CodedOutputStream.computeInt32Size(2, i2);
                }
                int iComputeStringSizeNoTag = 0;
                for (int i3 = 0; i3 < this.hwDeviceInterfacePath_.size(); i3++) {
                    iComputeStringSizeNoTag += computeStringSizeNoTag(this.hwDeviceInterfacePath_.getRaw(i3));
                }
                int size = iComputeStringSize + iComputeStringSizeNoTag + mo5331getHwDeviceInterfacePathList().size();
                boolean z = this.isDeviceEnabled_;
                if (z) {
                    size += CodedOutputStream.computeBoolSize(4, z);
                }
                int serializedSize = size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof EmulatedDevice)) {
                    return super.equals(obj);
                }
                EmulatedDevice emulatedDevice = (EmulatedDevice) obj;
                return getUniqueId().equals(emulatedDevice.getUniqueId()) && getDeviceTypeOrdinal() == emulatedDevice.getDeviceTypeOrdinal() && mo5331getHwDeviceInterfacePathList().equals(emulatedDevice.mo5331getHwDeviceInterfacePathList()) && getIsDeviceEnabled() == emulatedDevice.getIsDeviceEnabled() && this.unknownFields.equals(emulatedDevice.unknownFields);
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getUniqueId().hashCode()) * 37) + 2) * 53) + getDeviceTypeOrdinal();
                if (getHwDeviceInterfacePathCount() > 0) {
                    iHashCode = (((iHashCode * 37) + 3) * 53) + mo5331getHwDeviceInterfacePathList().hashCode();
                }
                int iHashBoolean = (((((iHashCode * 37) + 4) * 53) + Internal.hashBoolean(getIsDeviceEnabled())) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashBoolean;
                return iHashBoolean;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5332newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5335toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EmulatedDeviceOrBuilder {
                private int bitField0_;
                private int deviceTypeOrdinal_;
                private LazyStringList hwDeviceInterfacePath_;
                private boolean isDeviceEnabled_;
                private Object uniqueId_;

                private Builder() {
                    this.uniqueId_ = "";
                    this.hwDeviceInterfacePath_ = LazyStringArrayList.EMPTY;
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.uniqueId_ = "";
                    this.hwDeviceInterfacePath_ = LazyStringArrayList.EMPTY;
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_EmulatedDevices_EmulatedDevice_descriptor;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
                public int getDeviceTypeOrdinal() {
                    return this.deviceTypeOrdinal_;
                }

                public Builder setDeviceTypeOrdinal(int i) {
                    this.deviceTypeOrdinal_ = i;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
                public boolean getIsDeviceEnabled() {
                    return this.isDeviceEnabled_;
                }

                public Builder setIsDeviceEnabled(boolean z) {
                    this.isDeviceEnabled_ = z;
                    onChanged();
                    return this;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_EmulatedDevices_EmulatedDevice_fieldAccessorTable.ensureFieldAccessorsInitialized(EmulatedDevice.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = EmulatedDevice.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5346clear() {
                    super.clear();
                    this.uniqueId_ = "";
                    this.deviceTypeOrdinal_ = 0;
                    this.hwDeviceInterfacePath_ = LazyStringArrayList.EMPTY;
                    this.bitField0_ &= -2;
                    this.isDeviceEnabled_ = false;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ShimmerGRPC.internal_static_shimmerGRPC_EmulatedDevices_EmulatedDevice_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public EmulatedDevice m5359getDefaultInstanceForType() {
                    return EmulatedDevice.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public EmulatedDevice m5340build() throws UninitializedMessageException {
                    EmulatedDevice emulatedDeviceM5342buildPartial = m5342buildPartial();
                    if (emulatedDeviceM5342buildPartial.isInitialized()) {
                        return emulatedDeviceM5342buildPartial;
                    }
                    throw newUninitializedMessageException(emulatedDeviceM5342buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public EmulatedDevice m5342buildPartial() {
                    EmulatedDevice emulatedDevice = new EmulatedDevice(this);
                    emulatedDevice.uniqueId_ = this.uniqueId_;
                    emulatedDevice.deviceTypeOrdinal_ = this.deviceTypeOrdinal_;
                    if ((this.bitField0_ & 1) != 0) {
                        this.hwDeviceInterfacePath_ = this.hwDeviceInterfacePath_.getUnmodifiableView();
                        this.bitField0_ &= -2;
                    }
                    emulatedDevice.hwDeviceInterfacePath_ = this.hwDeviceInterfacePath_;
                    emulatedDevice.isDeviceEnabled_ = this.isDeviceEnabled_;
                    onBuilt();
                    return emulatedDevice;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5358clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5370setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5348clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5351clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5372setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5338addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m5363mergeFrom(Message message) {
                    if (message instanceof EmulatedDevice) {
                        return mergeFrom((EmulatedDevice) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(EmulatedDevice emulatedDevice) {
                    if (emulatedDevice == EmulatedDevice.getDefaultInstance()) {
                        return this;
                    }
                    if (!emulatedDevice.getUniqueId().isEmpty()) {
                        this.uniqueId_ = emulatedDevice.uniqueId_;
                        onChanged();
                    }
                    if (emulatedDevice.getDeviceTypeOrdinal() != 0) {
                        setDeviceTypeOrdinal(emulatedDevice.getDeviceTypeOrdinal());
                    }
                    if (!emulatedDevice.hwDeviceInterfacePath_.isEmpty()) {
                        if (this.hwDeviceInterfacePath_.isEmpty()) {
                            this.hwDeviceInterfacePath_ = emulatedDevice.hwDeviceInterfacePath_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureHwDeviceInterfacePathIsMutable();
                            this.hwDeviceInterfacePath_.addAll(emulatedDevice.hwDeviceInterfacePath_);
                        }
                        onChanged();
                    }
                    if (emulatedDevice.getIsDeviceEnabled()) {
                        setIsDeviceEnabled(emulatedDevice.getIsDeviceEnabled());
                    }
                    m5368mergeUnknownFields(emulatedDevice.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDevice.Builder m5364mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDevice.m5328$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        com.shimmerresearch.grpc.ShimmerGRPC$EmulatedDevices$EmulatedDevice r3 = (com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDevice) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        if (r3 == 0) goto L10
                        r2.mergeFrom(r3)
                    L10:
                        return r2
                    L11:
                        r3 = move-exception
                        goto L21
                    L13:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                        com.shimmerresearch.grpc.ShimmerGRPC$EmulatedDevices$EmulatedDevice r4 = (com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDevice) r4     // Catch: java.lang.Throwable -> L11
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                        throw r3     // Catch: java.lang.Throwable -> L1f
                    L1f:
                        r3 = move-exception
                        r0 = r4
                    L21:
                        if (r0 == 0) goto L26
                        r2.mergeFrom(r0)
                    L26:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDevice.Builder.m5364mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$EmulatedDevices$EmulatedDevice$Builder");
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
                public String getUniqueId() {
                    Object obj = this.uniqueId_;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.uniqueId_ = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                public Builder setUniqueId(String str) {
                    str.getClass();
                    this.uniqueId_ = str;
                    onChanged();
                    return this;
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
                public ByteString getUniqueIdBytes() {
                    Object obj = this.uniqueId_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.uniqueId_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setUniqueIdBytes(ByteString byteString) {
                    byteString.getClass();
                    EmulatedDevice.checkByteStringIsUtf8(byteString);
                    this.uniqueId_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearUniqueId() {
                    this.uniqueId_ = EmulatedDevice.getDefaultInstance().getUniqueId();
                    onChanged();
                    return this;
                }

                public Builder clearDeviceTypeOrdinal() {
                    this.deviceTypeOrdinal_ = 0;
                    onChanged();
                    return this;
                }

                private void ensureHwDeviceInterfacePathIsMutable() {
                    if ((this.bitField0_ & 1) == 0) {
                        this.hwDeviceInterfacePath_ = new LazyStringArrayList(this.hwDeviceInterfacePath_);
                        this.bitField0_ |= 1;
                    }
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
                /* renamed from: getHwDeviceInterfacePathList, reason: merged with bridge method [inline-methods] */
                public ProtocolStringList mo5331getHwDeviceInterfacePathList() {
                    return this.hwDeviceInterfacePath_.getUnmodifiableView();
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
                public int getHwDeviceInterfacePathCount() {
                    return this.hwDeviceInterfacePath_.size();
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
                public String getHwDeviceInterfacePath(int i) {
                    return (String) this.hwDeviceInterfacePath_.get(i);
                }

                @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.EmulatedDeviceOrBuilder
                public ByteString getHwDeviceInterfacePathBytes(int i) {
                    return this.hwDeviceInterfacePath_.getByteString(i);
                }

                public Builder setHwDeviceInterfacePath(int i, String str) {
                    str.getClass();
                    ensureHwDeviceInterfacePathIsMutable();
                    this.hwDeviceInterfacePath_.set(i, str);
                    onChanged();
                    return this;
                }

                public Builder addHwDeviceInterfacePath(String str) {
                    str.getClass();
                    ensureHwDeviceInterfacePathIsMutable();
                    this.hwDeviceInterfacePath_.add(str);
                    onChanged();
                    return this;
                }

                public Builder addAllHwDeviceInterfacePath(Iterable<String> iterable) {
                    ensureHwDeviceInterfacePathIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.hwDeviceInterfacePath_);
                    onChanged();
                    return this;
                }

                public Builder clearHwDeviceInterfacePath() {
                    this.hwDeviceInterfacePath_ = LazyStringArrayList.EMPTY;
                    this.bitField0_ &= -2;
                    onChanged();
                    return this;
                }

                public Builder addHwDeviceInterfacePathBytes(ByteString byteString) {
                    byteString.getClass();
                    EmulatedDevice.checkByteStringIsUtf8(byteString);
                    ensureHwDeviceInterfacePathIsMutable();
                    this.hwDeviceInterfacePath_.add(byteString);
                    onChanged();
                    return this;
                }

                public Builder clearIsDeviceEnabled() {
                    this.isDeviceEnabled_ = false;
                    onChanged();
                    return this;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m5374setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m5368mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        private static final class EmulatedDevicesMapDefaultEntryHolder {
            static final MapEntry<String, EmulatedDevice> defaultEntry = MapEntry.newDefaultInstance(ShimmerGRPC.internal_static_shimmerGRPC_EmulatedDevices_EmulatedDevicesMapEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, EmulatedDevice.getDefaultInstance());

            private EmulatedDevicesMapDefaultEntryHolder() {
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EmulatedDevicesOrBuilder {
            private int bitField0_;
            private MapField<String, EmulatedDevice> emulatedDevicesMap_;
            private boolean isEmulatorSide_;
            private Object message_;
            private boolean state_;

            private Builder() {
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerGRPC.internal_static_shimmerGRPC_EmulatedDevices_descriptor;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
            public boolean getIsEmulatorSide() {
                return this.isEmulatorSide_;
            }

            public Builder setIsEmulatorSide(boolean z) {
                this.isEmulatorSide_ = z;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
            public boolean getState() {
                return this.state_;
            }

            public Builder setState(boolean z) {
                this.state_ = z;
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected MapField internalGetMapField(int i) {
                if (i == 4) {
                    return internalGetEmulatedDevicesMap();
                }
                throw new RuntimeException("Invalid map field number: " + i);
            }

            protected MapField internalGetMutableMapField(int i) {
                if (i == 4) {
                    return internalGetMutableEmulatedDevicesMap();
                }
                throw new RuntimeException("Invalid map field number: " + i);
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerGRPC.internal_static_shimmerGRPC_EmulatedDevices_fieldAccessorTable.ensureFieldAccessorsInitialized(EmulatedDevices.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = EmulatedDevices.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5292clear() {
                super.clear();
                this.state_ = false;
                this.message_ = "";
                this.isEmulatorSide_ = false;
                internalGetMutableEmulatedDevicesMap().clear();
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerGRPC.internal_static_shimmerGRPC_EmulatedDevices_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public EmulatedDevices m5305getDefaultInstanceForType() {
                return EmulatedDevices.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public EmulatedDevices m5286build() throws UninitializedMessageException {
                EmulatedDevices emulatedDevicesM5288buildPartial = m5288buildPartial();
                if (emulatedDevicesM5288buildPartial.isInitialized()) {
                    return emulatedDevicesM5288buildPartial;
                }
                throw newUninitializedMessageException(emulatedDevicesM5288buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public EmulatedDevices m5288buildPartial() {
                EmulatedDevices emulatedDevices = new EmulatedDevices(this);
                emulatedDevices.state_ = this.state_;
                emulatedDevices.message_ = this.message_;
                emulatedDevices.isEmulatorSide_ = this.isEmulatorSide_;
                emulatedDevices.emulatedDevicesMap_ = internalGetEmulatedDevicesMap();
                emulatedDevices.emulatedDevicesMap_.makeImmutable();
                onBuilt();
                return emulatedDevices;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5304clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5316setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5294clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5297clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5318setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5284addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m5309mergeFrom(Message message) {
                if (message instanceof EmulatedDevices) {
                    return mergeFrom((EmulatedDevices) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(EmulatedDevices emulatedDevices) {
                if (emulatedDevices == EmulatedDevices.getDefaultInstance()) {
                    return this;
                }
                if (emulatedDevices.getState()) {
                    setState(emulatedDevices.getState());
                }
                if (!emulatedDevices.getMessage().isEmpty()) {
                    this.message_ = emulatedDevices.message_;
                    onChanged();
                }
                if (emulatedDevices.getIsEmulatorSide()) {
                    setIsEmulatorSide(emulatedDevices.getIsEmulatorSide());
                }
                internalGetMutableEmulatedDevicesMap().mergeFrom(emulatedDevices.internalGetEmulatedDevicesMap());
                m5314mergeUnknownFields(emulatedDevices.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.Builder m5310mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.m5275$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.grpc.ShimmerGRPC$EmulatedDevices r3 = (com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.shimmerresearch.grpc.ShimmerGRPC$EmulatedDevices r4 = (com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevices.Builder.m5310mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.grpc.ShimmerGRPC$EmulatedDevices$Builder");
            }

            public Builder clearState() {
                this.state_ = false;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
            public String getMessage() {
                Object obj = this.message_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.message_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setMessage(String str) {
                str.getClass();
                this.message_ = str;
                onChanged();
                return this;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
            public ByteString getMessageBytes() {
                Object obj = this.message_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.message_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMessageBytes(ByteString byteString) {
                byteString.getClass();
                EmulatedDevices.checkByteStringIsUtf8(byteString);
                this.message_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearMessage() {
                this.message_ = EmulatedDevices.getDefaultInstance().getMessage();
                onChanged();
                return this;
            }

            public Builder clearIsEmulatorSide() {
                this.isEmulatorSide_ = false;
                onChanged();
                return this;
            }

            private MapField<String, EmulatedDevice> internalGetEmulatedDevicesMap() {
                MapField<String, EmulatedDevice> mapField = this.emulatedDevicesMap_;
                return mapField == null ? MapField.emptyMapField(EmulatedDevicesMapDefaultEntryHolder.defaultEntry) : mapField;
            }

            private MapField<String, EmulatedDevice> internalGetMutableEmulatedDevicesMap() {
                onChanged();
                if (this.emulatedDevicesMap_ == null) {
                    this.emulatedDevicesMap_ = MapField.newMapField(EmulatedDevicesMapDefaultEntryHolder.defaultEntry);
                }
                if (!this.emulatedDevicesMap_.isMutable()) {
                    this.emulatedDevicesMap_ = this.emulatedDevicesMap_.copy();
                }
                return this.emulatedDevicesMap_;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
            public int getEmulatedDevicesMapCount() {
                return internalGetEmulatedDevicesMap().getMap().size();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
            public boolean containsEmulatedDevicesMap(String str) {
                str.getClass();
                return internalGetEmulatedDevicesMap().getMap().containsKey(str);
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
            @Deprecated
            public Map<String, EmulatedDevice> getEmulatedDevicesMap() {
                return getEmulatedDevicesMapMap();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
            public Map<String, EmulatedDevice> getEmulatedDevicesMapMap() {
                return internalGetEmulatedDevicesMap().getMap();
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
            public EmulatedDevice getEmulatedDevicesMapOrDefault(String str, EmulatedDevice emulatedDevice) {
                str.getClass();
                Map map = internalGetEmulatedDevicesMap().getMap();
                return map.containsKey(str) ? (EmulatedDevice) map.get(str) : emulatedDevice;
            }

            @Override // com.shimmerresearch.grpc.ShimmerGRPC.EmulatedDevicesOrBuilder
            public EmulatedDevice getEmulatedDevicesMapOrThrow(String str) {
                str.getClass();
                Map map = internalGetEmulatedDevicesMap().getMap();
                if (!map.containsKey(str)) {
                    throw new IllegalArgumentException();
                }
                return (EmulatedDevice) map.get(str);
            }

            public Builder clearEmulatedDevicesMap() {
                internalGetMutableEmulatedDevicesMap().getMutableMap().clear();
                return this;
            }

            public Builder removeEmulatedDevicesMap(String str) {
                str.getClass();
                internalGetMutableEmulatedDevicesMap().getMutableMap().remove(str);
                return this;
            }

            @Deprecated
            public Map<String, EmulatedDevice> getMutableEmulatedDevicesMap() {
                return internalGetMutableEmulatedDevicesMap().getMutableMap();
            }

            public Builder putEmulatedDevicesMap(String str, EmulatedDevice emulatedDevice) {
                str.getClass();
                emulatedDevice.getClass();
                internalGetMutableEmulatedDevicesMap().getMutableMap().put(str, emulatedDevice);
                return this;
            }

            public Builder putAllEmulatedDevicesMap(Map<String, EmulatedDevice> map) {
                internalGetMutableEmulatedDevicesMap().getMutableMap().putAll(map);
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5320setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m5314mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }
}
