package com.shimmerresearch.comms.radioProtocol;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class ShimmerLiteProtocolInstructionSet {
    private static final Descriptors.Descriptor internal_static_tutorial_LiteProtocolInstructionSet_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_tutorial_LiteProtocolInstructionSet_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n LiteProtocolInstructionSet.proto\u0012\btutorial\u001a google/protobuf/descriptor.proto\"÷(\n\u001aLiteProtocolInstructionSet\"ª\r\n\u000fInstructionsSet\u0012\u000f\n\u000bDATA_PACKET\u0010\u0000\u0012\u001d\n\u0019SET_SAMPLING_RATE_COMMAND\u0010\u0005\u0012\u0016\n\u0012TOGGLE_LED_COMMAND\u0010\u0006\u0012\u001b\n\u0017START_STREAMING_COMMAND\u0010\u0007\u0012\u0017\n\u0013SET_SENSORS_COMMAND\u0010\b\u0012!\n\u001dSET_ACCEL_SENSITIVITY_COMMAND\u0010\t\u0012\u001c\n\u0018SET_5V_REGULATOR_COMMAND\u0010\f\u0012\u0014\n\u0010SET_PMUX_COMMAND\u0010\r\u0012\u001c\n\u0018SET_CONFIG_BYTE0_COMMAND\u0010\u000e\u0012\u001a\n\u0016STOP_STREAMING_COMMAND\u0010 ", "\u0012!\n\u001dSET_ACCEL_CALIBRATION_COMMAND\u0010\u0011\u0012,\n(SET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND\u0010\u001a\u0012 \n\u001cSET_GYRO_CALIBRATION_COMMAND\u0010\u0014\u0012\u001f\n\u001bSET_MAG_CALIBRATION_COMMAND\u0010\u0017\u0012\u0019\n\u0015SET_GSR_RANGE_COMMAND\u0010!\u0012\u001f\n\u001bSET_EMG_CALIBRATION_COMMAND\u0010&\u0012\u001f\n\u001bSET_ECG_CALIBRATION_COMMAND\u0010)\u0012\u0011\n\rSET_BLINK_LED\u00100\u0012\u001e\n\u001aSET_GYRO_TEMP_VREF_COMMAND\u00103\u0012\u001b\n\u0017SET_BUFFER_SIZE_COMMAND\u00104\u0012\u0018\n\u0014SET_MAG_GAIN_COMMAND\u00107\u0012!\n\u001dSET_MAG_SAMPLING_RATE_COMMAND\u0010:\u0012#\n\u001fSET_ACCEL_SAMP", "LING_RATE_COMMAND\u0010@\u0012'\n#SET_LSM303DLHC_ACCEL_LPMODE_COMMAND\u0010C\u0012'\n#SET_LSM303DLHC_ACCEL_HRMODE_COMMAND\u0010F\u0012\"\n\u001eSET_MPU9150_GYRO_RANGE_COMMAND\u0010I\u0012%\n!SET_MPU9150_SAMPLING_RATE_COMMAND\u0010L\u0012&\n\"SET_BMP180_PRES_RESOLUTION_COMMAND\u0010R\u0012'\n#SET_BMP180_PRES_CALIBRATION_COMMAND\u0010U\u0012*\n&RESET_TO_DEFAULT_CONFIGURATION_COMMAND\u0010Z\u0012#\n\u001fRESET_CALIBRATION_VALUE_COMMAND\u0010[\u0012)\n%SET_INTERNAL_EXP_POWER_ENABLE_COMMAND\u0010^\u0012\u0018\n\u0014SET_EXG_REGS_CO", "MMAND\u0010a\u0012\u0019\n\u0015SET_BAUD_RATE_COMMAND\u0010j\u0012\u001d\n\u0019SET_DERIVED_CHANNEL_BYTES\u0010m\u0012\u0016\n\u0012START_SDBT_COMMAND\u0010p\u0012\u001c\n\u0018SET_TRIAL_CONFIG_COMMAND\u0010s\u0012\u0016\n\u0012SET_CENTER_COMMAND\u0010v\u0012\u001b\n\u0017SET_SHIMMERNAME_COMMAND\u0010y\u0012\u0015\n\u0011SET_EXPID_COMMAND\u0010|\u0012\u0014\n\u0010SET_MYID_COMMAND\u0010\u007f\u0012\u0019\n\u0014SET_NSHIMMER_COMMAND\u0010\u0082\u0001\u0012\u001b\n\u0016SET_CONFIGTIME_COMMAND\u0010\u0085\u0001\u0012\u0018\n\u0013SET_INFOMEM_COMMAND\u0010\u008c\u0001\u0012\u0014\n\u000fSET_CRC_COMMAND\u0010\u008b\u0001\u0012\u0014\n\u000fSET_RWC_COMMAND\u0010\u008f\u0001\u0012\u001a\n\u0015ROUTINE_COMMUNICATION\u0010à\u0001\u0012\u001a\n\u0015ACK_COMMAND_PROCESSED\u0010ÿ\u0001\u0012", "\u001f\n\u001aSTART_LOGGING_ONLY_COMMAND\u0010\u0092\u0001\u0012\u001e\n\u0019STOP_LOGGING_ONLY_COMMAND\u0010\u0093\u0001\u0012\u001c\n\u0017TEST_CONNECTION_COMMAND\u0010\u0096\u0001\u0012\u0016\n\u0011STOP_SDBT_COMMAND\u0010\u0097\u0001\u0012\u001b\n\u0016SET_CALIB_DUMP_COMMAND\u0010\u0098\u0001\u0012\u001e\n\u0019UPD_CONFIG_MEMORY_COMMAND\u0010\u009b\u0001\u0012%\n SET_I2C_BATT_STATUS_FREQ_COMMAND\u0010\u009c\u0001\"È\f\n\u000fInstructionsGet\u0012\u0010\n\fNOT_USED_GET\u0010\u0000\u0012\u0013\n\u000fINQUIRY_COMMAND\u0010\u0001\u0012\u001d\n\u0019GET_SAMPLING_RATE_COMMAND\u0010\u0003\u0012!\n\u001dGET_ACCEL_SENSITIVITY_COMMAND\u0010\u000b\u0012\u001c\n\u0018GET_CONFIG_BYTE0_COMMAND\u0010\u0010\u0012,\n(GET_LSM303DLHC_ACCEL_CA", "LIBRATION_COMMAND\u0010\u001c\u0012!\n\u001dGET_ACCEL_CALIBRATION_COMMAND\u0010\u0013\u0012 \n\u001cGET_GYRO_CALIBRATION_COMMAND\u0010\u0016\u0012\u001f\n\u001bGET_MAG_CALIBRATION_COMMAND\u0010\u0019\u0012\u0019\n\u0015GET_GSR_RANGE_COMMAND\u0010#\u0012\u001f\n\u001bGET_SHIMMER_VERSION_COMMAND\u0010$\u0012#\n\u001fGET_SHIMMER_VERSION_COMMAND_NEW\u0010?\u0012\u001f\n\u001bGET_EMG_CALIBRATION_COMMAND\u0010(\u0012\u001f\n\u001bGET_ECG_CALIBRATION_COMMAND\u0010+\u0012\u001f\n\u001bGET_ALL_CALIBRATION_COMMAND\u0010,\u0012\u001a\n\u0016GET_FW_VERSION_COMMAND\u0010.\u0012\u0011\n\rGET_BLINK_LED\u00102\u0012\u001b\n\u0017GET_BUFFER_SIZE_COMMAND\u00106\u0012\u0018\n\u0014GET", "_MAG_GAIN_COMMAND\u00109\u0012!\n\u001dGET_MAG_SAMPLING_RATE_COMMAND\u0010<\u0012\u001d\n\u0019GET_UNIQUE_SERIAL_COMMAND\u0010>\u0012#\n\u001fGET_ACCEL_SAMPLING_RATE_COMMAND\u0010B\u0012'\n#GET_LSM303DLHC_ACCEL_LPMODE_COMMAND\u0010E\u0012'\n#GET_LSM303DLHC_ACCEL_HRMODE_COMMAND\u0010H\u0012\"\n\u001eGET_MPU9150_GYRO_RANGE_COMMAND\u0010K\u0012%\n!GET_MPU9150_SAMPLING_RATE_COMMAND\u0010N\u0012&\n\"GET_BMP180_PRES_RESOLUTION_COMMAND\u0010T\u0012'\n#GET_BMP180_PRES_CALIBRATION_COMMAND\u0010W\u0012/\n+GET_BMP180_CALIBRATION_COEFFICIENTS_", "COMMAND\u0010Y\u0012)\n%GET_MPU9150_MAG_SENS_ADJ_VALS_COMMAND\u0010]\u0012)\n%GET_INTERNAL_EXP_POWER_ENABLE_COMMAND\u0010`\u0012\u0018\n\u0014GET_EXG_REGS_COMMAND\u0010c\u0012 \n\u001cGET_DAUGHTER_CARD_ID_COMMAND\u0010f\u0012\u0019\n\u0015GET_BAUD_RATE_COMMAND\u0010l\u0012\u001d\n\u0019GET_DERIVED_CHANNEL_BYTES\u0010o\u0012\u0016\n\u0012GET_STATUS_COMMAND\u0010r\u0012\u001c\n\u0018GET_TRIAL_CONFIG_COMMAND\u0010u\u0012\u0016\n\u0012GET_CENTER_COMMAND\u0010x\u0012\u001b\n\u0017GET_SHIMMERNAME_COMMAND\u0010{\u0012\u0015\n\u0011GET_EXPID_COMMAND\u0010~\u0012\u0015\n\u0010GET_MYID_COMMAND\u0010\u0081\u0001\u0012\u0019\n\u0014GET_NSHIMMER_COMMAND\u0010\u0084\u0001\u0012\u001b\n\u0016GET", "_CONFIGTIME_COMMAND\u0010\u0087\u0001\u0012\u0014\n\u000fGET_DIR_COMMAND\u0010\u0089\u0001\u0012\u0018\n\u0013GET_INFOMEM_COMMAND\u0010\u008e\u0001\u0012\u0014\n\u000fGET_RWC_COMMAND\u0010\u0091\u0001\u0012\u0016\n\u0011GET_VBATT_COMMAND\u0010\u0095\u0001\u0012\u001b\n\u0016GET_CALIB_DUMP_COMMAND\u0010\u009a\u0001\u0012 \n\u001bGET_I2C_BATT_STATUS_COMMAND\u0010\u009e\u0001\u00120\n+GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND\u0010 \u0001\"¤\u000e\n\u0014InstructionsResponse\u0012\u0015\n\u0011NOT_USED_RESPONSE\u0010\u0000\u0012\u0014\n\u0010INQUIRY_RESPONSE\u0010\u0002\u0012 \n\u0016SAMPLING_RATE_RESPONSE\u0010\u0004\u001a\u0004\u0098µ\u0018\u0002\u0012$\n\u001aACCEL_SENSITIVITY_RESPONSE\u0010\n\u001a\u0004\u0098µ\u0018\u0001\u0012\u001f\n\u0015CONFIG_BYTE0_RESPONSE\u0010\u000f\u001a\u0004", "\u0098µ\u0018\u0004\u0012$\n\u001aACCEL_CALIBRATION_RESPONSE\u0010\u0012\u001a\u0004\u0098µ\u0018\u0015\u0012/\n%LSM303DLHC_ACCEL_CALIBRATION_RESPONSE\u0010\u001b\u001a\u0004\u0098µ\u0018\u0015\u0012#\n\u0019GYRO_CALIBRATION_RESPONSE\u0010\u0015\u001a\u0004\u0098µ\u0018\u0015\u0012\"\n\u0018MAG_CALIBRATION_RESPONSE\u0010\u0018\u001a\u0004\u0098µ\u0018\u0015\u0012\u001c\n\u0012GSR_RANGE_RESPONSE\u0010\"\u001a\u0004\u0098µ\u0018\u0001\u0012&\n\u001cGET_SHIMMER_VERSION_RESPONSE\u0010%\u001a\u0004\u0098µ\u0018\u0001\u0012\"\n\u0018EMG_CALIBRATION_RESPONSE\u0010'\u001a\u0004\u0098µ\u0018\u0004\u0012\"\n\u0018ECG_CALIBRATION_RESPONSE\u0010*\u001a\u0004\u0098µ\u0018\u0004\u0012\"\n\u0018ALL_CALIBRATION_RESPONSE\u0010-\u001a\u0004\u0098µ\u0018T\u0012\u001d\n\u0013FW_VERSION_RESPONSE\u0010/\u001a\u0004\u0098µ\u0018\u0006\u0012\u001c\n\u0012BLINK_LED_RESPONSE\u00101\u001a\u0004\u0098", "µ\u0018\u0001\u0012\u001e\n\u0014BUFFER_SIZE_RESPONSE\u00105\u001a\u0004\u0098µ\u0018\u0001\u0012\u001b\n\u0011MAG_GAIN_RESPONSE\u00108\u001a\u0004\u0098µ\u0018\u0001\u0012$\n\u001aMAG_SAMPLING_RATE_RESPONSE\u0010;\u001a\u0004\u0098µ\u0018\u0001\u0012 \n\u0016UNIQUE_SERIAL_RESPONSE\u0010=\u001a\u0004\u0098µ\u0018\b\u0012&\n\u001cACCEL_SAMPLING_RATE_RESPONSE\u0010A\u001a\u0004\u0098µ\u0018\u0001\u0012*\n LSM303DLHC_ACCEL_LPMODE_RESPONSE\u0010D\u001a\u0004\u0098µ\u0018\u0001\u0012*\n LSM303DLHC_ACCEL_HRMODE_RESPONSE\u0010G\u001a\u0004\u0098µ\u0018\u0001\u0012%\n\u001bMPU9150_GYRO_RANGE_RESPONSE\u0010J\u001a\u0004\u0098µ\u0018\u0001\u0012(\n\u001eMPU9150_SAMPLING_RATE_RESPONSE\u0010M\u001a\u0004\u0098µ\u0018\u0001\u0012)\n\u001fBMP180_PRES_RESOLUTION_RESPONSE\u0010S\u001a\u0004\u0098µ\u0018\u0001\u0012$\n BMP180_P", "RES_CALIBRATION_RESPONSE\u0010V\u00122\n(BMP180_CALIBRATION_COEFFICIENTS_RESPONSE\u0010X\u001a\u0004\u0098µ\u0018\u0016\u0012&\n\"MPU9150_MAG_SENS_ADJ_VALS_RESPONSE\u0010\\\u0012,\n\"INTERNAL_EXP_POWER_ENABLE_RESPONSE\u0010_\u001a\u0004\u0098µ\u0018\u0001\u0012\u001b\n\u0011EXG_REGS_RESPONSE\u0010b\u001a\u0004\u0098µ\u0018\u000b\u0012#\n\u0019DAUGHTER_CARD_ID_RESPONSE\u0010e\u001a\u0004\u0098µ\u0018\u0003\u0012\u001c\n\u0012BAUD_RATE_RESPONSE\u0010k\u001a\u0004\u0098µ\u0018\u0001\u0012(\n\u001eDERIVED_CHANNEL_BYTES_RESPONSE\u0010n\u001a\u0004\u0098µ\u0018\u0003\u0012\u0019\n\u000fSTATUS_RESPONSE\u0010q\u001a\u0004\u0098µ\u0018\u0001\u0012\u001f\n\u0015TRIAL_CONFIG_RESPONSE\u0010t\u001a\u0004\u0098µ\u0018\u0003\u0012\"\n\u000fCENTER_RESPONSE\u0010w\u001a\r\u0098µ\u0018ÿÿÿÿÿÿÿÿÿ\u0001\u0012'", "\n\u0014SHIMMERNAME_RESPONSE\u0010z\u001a\r\u0098µ\u0018ÿÿÿÿÿÿÿÿÿ\u0001\u0012!\n\u000eEXPID_RESPONSE\u0010}\u001a\r\u0098µ\u0018ÿÿÿÿÿÿÿÿÿ\u0001\u0012\u0018\n\rMYID_RESPONSE\u0010\u0080\u0001\u001a\u0004\u0098µ\u0018\u0001\u0012\u001c\n\u0011NSHIMMER_RESPONSE\u0010\u0083\u0001\u001a\u0004\u0098µ\u0018\u0001\u0012'\n\u0013CONFIGTIME_RESPONSE\u0010\u0086\u0001\u001a\r\u0098µ\u0018ÿÿÿÿÿÿÿÿÿ\u0001\u0012 \n\fDIR_RESPONSE\u0010\u0088\u0001\u001a\r\u0098µ\u0018ÿÿÿÿÿÿÿÿÿ\u0001\u0012\u001a\n\u0015INSTREAM_CMD_RESPONSE\u0010\u008a\u0001\u0012$\n\u0010INFOMEM_RESPONSE\u0010\u008d\u0001\u001a\r\u0098µ\u0018ÿÿÿÿÿÿÿÿÿ\u0001\u0012\u0017\n\fRWC_RESPONSE\u0010\u0090\u0001\u001a\u0004\u0098µ\u0018\b\u0012\u0019\n\u000eVBATT_RESPONSE\u0010\u0094\u0001\u001a\u0004\u0098µ\u0018\u0003\u0012*\n\u0016RSP_CALIB_DUMP_COMMAND\u0010\u0099\u0001\u001a\r\u0098µ\u0018ÿÿÿÿÿÿÿÿÿ\u0001\u0012&\n\u001bRSP_I2C_BATT_STATUS_COMMAND\u0010\u009d", "\u0001\u001a\u0004\u0098µ\u0018\n\u00123\n(BMP280_CALIBRATION_COEFFICIENTS_RESPONSE\u0010\u009f\u0001\u001a\u0004\u0098µ\u0018\u00182:\n\rresponse_size\u0012!.google.protobuf.EnumValueOptions\u0018Ó\u0086\u0003 \u0001(\u0005Bp\n'com.shimmerresearch.comms.radioProtocolB!ShimmerLiteProtocolInstructionSetª\u0002!com.shimmerresearch.radioprotocolb\u0006proto3"}, new Descriptors.FileDescriptor[]{DescriptorProtos.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.1
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                ShimmerLiteProtocolInstructionSet.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_tutorial_LiteProtocolInstructionSet_descriptor = descriptor2;
        internal_static_tutorial_LiteProtocolInstructionSet_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[0]);
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(LiteProtocolInstructionSet.responseSize);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        DescriptorProtos.getDescriptor();
    }

    private ShimmerLiteProtocolInstructionSet() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add(LiteProtocolInstructionSet.responseSize);
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public interface LiteProtocolInstructionSetOrBuilder extends MessageOrBuilder {
    }

    public static final class LiteProtocolInstructionSet extends GeneratedMessageV3 implements LiteProtocolInstructionSetOrBuilder {
        public static final int RESPONSE_SIZE_FIELD_NUMBER = 50003;
        private static final long serialVersionUID = 0;
        private static final LiteProtocolInstructionSet DEFAULT_INSTANCE = new LiteProtocolInstructionSet();
        public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.EnumValueOptions, Integer> responseSize = GeneratedMessage.newMessageScopedGeneratedExtension(getDefaultInstance(), 0, Integer.class, (Message) null);
        private static final Parser<LiteProtocolInstructionSet> PARSER = new AbstractParser<LiteProtocolInstructionSet>() { // from class: com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public LiteProtocolInstructionSet m4535parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new LiteProtocolInstructionSet(codedInputStream, extensionRegistryLite);
            }
        };
        private byte memoizedIsInitialized;

        private LiteProtocolInstructionSet(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private LiteProtocolInstructionSet() {
            this.memoizedIsInitialized = (byte) -1;
        }

        private LiteProtocolInstructionSet(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag == 0 || !parseUnknownFieldProto3(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            z = true;
                        }
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

        public static LiteProtocolInstructionSet getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LiteProtocolInstructionSet> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ShimmerLiteProtocolInstructionSet.internal_static_tutorial_LiteProtocolInstructionSet_descriptor;
        }

        public static LiteProtocolInstructionSet parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (LiteProtocolInstructionSet) PARSER.parseFrom(byteBuffer);
        }

        public static LiteProtocolInstructionSet parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LiteProtocolInstructionSet) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static LiteProtocolInstructionSet parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (LiteProtocolInstructionSet) PARSER.parseFrom(byteString);
        }

        public static LiteProtocolInstructionSet parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LiteProtocolInstructionSet) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static LiteProtocolInstructionSet parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (LiteProtocolInstructionSet) PARSER.parseFrom(bArr);
        }

        public static LiteProtocolInstructionSet parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LiteProtocolInstructionSet) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static LiteProtocolInstructionSet parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static LiteProtocolInstructionSet parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static LiteProtocolInstructionSet parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static LiteProtocolInstructionSet parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static LiteProtocolInstructionSet parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static LiteProtocolInstructionSet parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m4533toBuilder();
        }

        public static Builder newBuilder(LiteProtocolInstructionSet liteProtocolInstructionSet) {
            return DEFAULT_INSTANCE.m4533toBuilder().mergeFrom(liteProtocolInstructionSet);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public LiteProtocolInstructionSet m4528getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<LiteProtocolInstructionSet> getParserForType() {
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

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ShimmerLiteProtocolInstructionSet.internal_static_tutorial_LiteProtocolInstructionSet_fieldAccessorTable.ensureFieldAccessorsInitialized(LiteProtocolInstructionSet.class, Builder.class);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof LiteProtocolInstructionSet)) {
                return super.equals(obj);
            }
            return this.unknownFields.equals(((LiteProtocolInstructionSet) obj).unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4530newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m4533toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public enum InstructionsSet implements ProtocolMessageEnum {
            DATA_PACKET(0),
            SET_SAMPLING_RATE_COMMAND(5),
            TOGGLE_LED_COMMAND(6),
            START_STREAMING_COMMAND(7),
            SET_SENSORS_COMMAND(8),
            SET_ACCEL_SENSITIVITY_COMMAND(9),
            SET_5V_REGULATOR_COMMAND(12),
            SET_PMUX_COMMAND(13),
            SET_CONFIG_BYTE0_COMMAND(14),
            STOP_STREAMING_COMMAND(32),
            SET_ACCEL_CALIBRATION_COMMAND(17),
            SET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND(26),
            SET_GYRO_CALIBRATION_COMMAND(20),
            SET_MAG_CALIBRATION_COMMAND(23),
            SET_GSR_RANGE_COMMAND(33),
            SET_EMG_CALIBRATION_COMMAND(38),
            SET_ECG_CALIBRATION_COMMAND(41),
            SET_BLINK_LED(48),
            SET_GYRO_TEMP_VREF_COMMAND(51),
            SET_BUFFER_SIZE_COMMAND(52),
            SET_MAG_GAIN_COMMAND(55),
            SET_MAG_SAMPLING_RATE_COMMAND(58),
            SET_ACCEL_SAMPLING_RATE_COMMAND(64),
            SET_LSM303DLHC_ACCEL_LPMODE_COMMAND(67),
            SET_LSM303DLHC_ACCEL_HRMODE_COMMAND(70),
            SET_MPU9150_GYRO_RANGE_COMMAND(73),
            SET_MPU9150_SAMPLING_RATE_COMMAND(76),
            SET_BMP180_PRES_RESOLUTION_COMMAND(82),
            SET_BMP180_PRES_CALIBRATION_COMMAND(85),
            RESET_TO_DEFAULT_CONFIGURATION_COMMAND(90),
            RESET_CALIBRATION_VALUE_COMMAND(91),
            SET_INTERNAL_EXP_POWER_ENABLE_COMMAND(94),
            SET_EXG_REGS_COMMAND(97),
            SET_BAUD_RATE_COMMAND(106),
            SET_DERIVED_CHANNEL_BYTES(109),
            START_SDBT_COMMAND(112),
            SET_TRIAL_CONFIG_COMMAND(115),
            SET_CENTER_COMMAND(118),
            SET_SHIMMERNAME_COMMAND(121),
            SET_EXPID_COMMAND(124),
            SET_MYID_COMMAND(127),
            SET_NSHIMMER_COMMAND(130),
            SET_CONFIGTIME_COMMAND(133),
            SET_INFOMEM_COMMAND(140),
            SET_CRC_COMMAND(139),
            SET_RWC_COMMAND(143),
            ROUTINE_COMMUNICATION(224),
            ACK_COMMAND_PROCESSED(255),
            START_LOGGING_ONLY_COMMAND(146),
            STOP_LOGGING_ONLY_COMMAND(147),
            TEST_CONNECTION_COMMAND(150),
            STOP_SDBT_COMMAND(151),
            SET_CALIB_DUMP_COMMAND(152),
            UPD_CONFIG_MEMORY_COMMAND(155),
            SET_I2C_BATT_STATUS_FREQ_COMMAND(156),
            UNRECOGNIZED(-1);

            public static final int ACK_COMMAND_PROCESSED_VALUE = 255;
            public static final int DATA_PACKET_VALUE = 0;
            public static final int RESET_CALIBRATION_VALUE_COMMAND_VALUE = 91;
            public static final int RESET_TO_DEFAULT_CONFIGURATION_COMMAND_VALUE = 90;
            public static final int ROUTINE_COMMUNICATION_VALUE = 224;
            public static final int SET_5V_REGULATOR_COMMAND_VALUE = 12;
            public static final int SET_ACCEL_CALIBRATION_COMMAND_VALUE = 17;
            public static final int SET_ACCEL_SAMPLING_RATE_COMMAND_VALUE = 64;
            public static final int SET_ACCEL_SENSITIVITY_COMMAND_VALUE = 9;
            public static final int SET_BAUD_RATE_COMMAND_VALUE = 106;
            public static final int SET_BLINK_LED_VALUE = 48;
            public static final int SET_BMP180_PRES_CALIBRATION_COMMAND_VALUE = 85;
            public static final int SET_BMP180_PRES_RESOLUTION_COMMAND_VALUE = 82;
            public static final int SET_BUFFER_SIZE_COMMAND_VALUE = 52;
            public static final int SET_CALIB_DUMP_COMMAND_VALUE = 152;
            public static final int SET_CENTER_COMMAND_VALUE = 118;
            public static final int SET_CONFIGTIME_COMMAND_VALUE = 133;
            public static final int SET_CONFIG_BYTE0_COMMAND_VALUE = 14;
            public static final int SET_CRC_COMMAND_VALUE = 139;
            public static final int SET_DERIVED_CHANNEL_BYTES_VALUE = 109;
            public static final int SET_ECG_CALIBRATION_COMMAND_VALUE = 41;
            public static final int SET_EMG_CALIBRATION_COMMAND_VALUE = 38;
            public static final int SET_EXG_REGS_COMMAND_VALUE = 97;
            public static final int SET_EXPID_COMMAND_VALUE = 124;
            public static final int SET_GSR_RANGE_COMMAND_VALUE = 33;
            public static final int SET_GYRO_CALIBRATION_COMMAND_VALUE = 20;
            public static final int SET_GYRO_TEMP_VREF_COMMAND_VALUE = 51;
            public static final int SET_I2C_BATT_STATUS_FREQ_COMMAND_VALUE = 156;
            public static final int SET_INFOMEM_COMMAND_VALUE = 140;
            public static final int SET_INTERNAL_EXP_POWER_ENABLE_COMMAND_VALUE = 94;
            public static final int SET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND_VALUE = 26;
            public static final int SET_LSM303DLHC_ACCEL_HRMODE_COMMAND_VALUE = 70;
            public static final int SET_LSM303DLHC_ACCEL_LPMODE_COMMAND_VALUE = 67;
            public static final int SET_MAG_CALIBRATION_COMMAND_VALUE = 23;
            public static final int SET_MAG_GAIN_COMMAND_VALUE = 55;
            public static final int SET_MAG_SAMPLING_RATE_COMMAND_VALUE = 58;
            public static final int SET_MPU9150_GYRO_RANGE_COMMAND_VALUE = 73;
            public static final int SET_MPU9150_SAMPLING_RATE_COMMAND_VALUE = 76;
            public static final int SET_MYID_COMMAND_VALUE = 127;
            public static final int SET_NSHIMMER_COMMAND_VALUE = 130;
            public static final int SET_PMUX_COMMAND_VALUE = 13;
            public static final int SET_RWC_COMMAND_VALUE = 143;
            public static final int SET_SAMPLING_RATE_COMMAND_VALUE = 5;
            public static final int SET_SENSORS_COMMAND_VALUE = 8;
            public static final int SET_SHIMMERNAME_COMMAND_VALUE = 121;
            public static final int SET_TRIAL_CONFIG_COMMAND_VALUE = 115;
            public static final int START_LOGGING_ONLY_COMMAND_VALUE = 146;
            public static final int START_SDBT_COMMAND_VALUE = 112;
            public static final int START_STREAMING_COMMAND_VALUE = 7;
            public static final int STOP_LOGGING_ONLY_COMMAND_VALUE = 147;
            public static final int STOP_SDBT_COMMAND_VALUE = 151;
            public static final int STOP_STREAMING_COMMAND_VALUE = 32;
            public static final int TEST_CONNECTION_COMMAND_VALUE = 150;
            public static final int TOGGLE_LED_COMMAND_VALUE = 6;
            public static final int UPD_CONFIG_MEMORY_COMMAND_VALUE = 155;
            private static final Internal.EnumLiteMap<InstructionsSet> internalValueMap = new Internal.EnumLiteMap<InstructionsSet>() { // from class: com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsSet.1
                public InstructionsSet findValueByNumber(int i) {
                    return InstructionsSet.forNumber(i);
                }
            };
            private static final InstructionsSet[] VALUES = values();
            private final int value;

            InstructionsSet(int i) {
                this.value = i;
            }

            public static InstructionsSet forNumber(int i) {
                switch (i) {
                    case 0:
                        return DATA_PACKET;
                    case 5:
                        return SET_SAMPLING_RATE_COMMAND;
                    case 6:
                        return TOGGLE_LED_COMMAND;
                    case 7:
                        return START_STREAMING_COMMAND;
                    case 8:
                        return SET_SENSORS_COMMAND;
                    case 9:
                        return SET_ACCEL_SENSITIVITY_COMMAND;
                    case 12:
                        return SET_5V_REGULATOR_COMMAND;
                    case 13:
                        return SET_PMUX_COMMAND;
                    case 14:
                        return SET_CONFIG_BYTE0_COMMAND;
                    case 17:
                        return SET_ACCEL_CALIBRATION_COMMAND;
                    case 20:
                        return SET_GYRO_CALIBRATION_COMMAND;
                    case 23:
                        return SET_MAG_CALIBRATION_COMMAND;
                    case 26:
                        return SET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND;
                    case 32:
                        return STOP_STREAMING_COMMAND;
                    case 33:
                        return SET_GSR_RANGE_COMMAND;
                    case 38:
                        return SET_EMG_CALIBRATION_COMMAND;
                    case 41:
                        return SET_ECG_CALIBRATION_COMMAND;
                    case 48:
                        return SET_BLINK_LED;
                    case 51:
                        return SET_GYRO_TEMP_VREF_COMMAND;
                    case 52:
                        return SET_BUFFER_SIZE_COMMAND;
                    case 55:
                        return SET_MAG_GAIN_COMMAND;
                    case 58:
                        return SET_MAG_SAMPLING_RATE_COMMAND;
                    case 64:
                        return SET_ACCEL_SAMPLING_RATE_COMMAND;
                    case 67:
                        return SET_LSM303DLHC_ACCEL_LPMODE_COMMAND;
                    case 70:
                        return SET_LSM303DLHC_ACCEL_HRMODE_COMMAND;
                    case 73:
                        return SET_MPU9150_GYRO_RANGE_COMMAND;
                    case 76:
                        return SET_MPU9150_SAMPLING_RATE_COMMAND;
                    case 82:
                        return SET_BMP180_PRES_RESOLUTION_COMMAND;
                    case SET_BMP180_PRES_CALIBRATION_COMMAND_VALUE:
                        return SET_BMP180_PRES_CALIBRATION_COMMAND;
                    case RESET_TO_DEFAULT_CONFIGURATION_COMMAND_VALUE:
                        return RESET_TO_DEFAULT_CONFIGURATION_COMMAND;
                    case 91:
                        return RESET_CALIBRATION_VALUE_COMMAND;
                    case 94:
                        return SET_INTERNAL_EXP_POWER_ENABLE_COMMAND;
                    case 97:
                        return SET_EXG_REGS_COMMAND;
                    case 106:
                        return SET_BAUD_RATE_COMMAND;
                    case 109:
                        return SET_DERIVED_CHANNEL_BYTES;
                    case 112:
                        return START_SDBT_COMMAND;
                    case 115:
                        return SET_TRIAL_CONFIG_COMMAND;
                    case 118:
                        return SET_CENTER_COMMAND;
                    case 121:
                        return SET_SHIMMERNAME_COMMAND;
                    case 124:
                        return SET_EXPID_COMMAND;
                    case 127:
                        return SET_MYID_COMMAND;
                    case 130:
                        return SET_NSHIMMER_COMMAND;
                    case 133:
                        return SET_CONFIGTIME_COMMAND;
                    case 139:
                        return SET_CRC_COMMAND;
                    case 140:
                        return SET_INFOMEM_COMMAND;
                    case 143:
                        return SET_RWC_COMMAND;
                    case 146:
                        return START_LOGGING_ONLY_COMMAND;
                    case 147:
                        return STOP_LOGGING_ONLY_COMMAND;
                    case 150:
                        return TEST_CONNECTION_COMMAND;
                    case 151:
                        return STOP_SDBT_COMMAND;
                    case 152:
                        return SET_CALIB_DUMP_COMMAND;
                    case 155:
                        return UPD_CONFIG_MEMORY_COMMAND;
                    case 156:
                        return SET_I2C_BATT_STATUS_FREQ_COMMAND;
                    case 224:
                        return ROUTINE_COMMUNICATION;
                    case 255:
                        return ACK_COMMAND_PROCESSED;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<InstructionsSet> internalGetValueMap() {
                return internalValueMap;
            }

            @Deprecated
            public static InstructionsSet valueOf(int i) {
                return forNumber(i);
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return (Descriptors.EnumDescriptor) LiteProtocolInstructionSet.getDescriptor().getEnumTypes().get(0);
            }

            public static InstructionsSet valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
                return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }
        }

        public enum InstructionsGet implements ProtocolMessageEnum {
            NOT_USED_GET(0),
            INQUIRY_COMMAND(1),
            GET_SAMPLING_RATE_COMMAND(3),
            GET_ACCEL_SENSITIVITY_COMMAND(11),
            GET_CONFIG_BYTE0_COMMAND(16),
            GET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND(28),
            GET_ACCEL_CALIBRATION_COMMAND(19),
            GET_GYRO_CALIBRATION_COMMAND(22),
            GET_MAG_CALIBRATION_COMMAND(25),
            GET_GSR_RANGE_COMMAND(35),
            GET_SHIMMER_VERSION_COMMAND(36),
            GET_SHIMMER_VERSION_COMMAND_NEW(63),
            GET_EMG_CALIBRATION_COMMAND(40),
            GET_ECG_CALIBRATION_COMMAND(43),
            GET_ALL_CALIBRATION_COMMAND(44),
            GET_FW_VERSION_COMMAND(46),
            GET_BLINK_LED(50),
            GET_BUFFER_SIZE_COMMAND(54),
            GET_MAG_GAIN_COMMAND(57),
            GET_MAG_SAMPLING_RATE_COMMAND(60),
            GET_UNIQUE_SERIAL_COMMAND(62),
            GET_ACCEL_SAMPLING_RATE_COMMAND(66),
            GET_LSM303DLHC_ACCEL_LPMODE_COMMAND(69),
            GET_LSM303DLHC_ACCEL_HRMODE_COMMAND(72),
            GET_MPU9150_GYRO_RANGE_COMMAND(75),
            GET_MPU9150_SAMPLING_RATE_COMMAND(78),
            GET_BMP180_PRES_RESOLUTION_COMMAND(84),
            GET_BMP180_PRES_CALIBRATION_COMMAND(87),
            GET_BMP180_CALIBRATION_COEFFICIENTS_COMMAND(89),
            GET_MPU9150_MAG_SENS_ADJ_VALS_COMMAND(93),
            GET_INTERNAL_EXP_POWER_ENABLE_COMMAND(96),
            GET_EXG_REGS_COMMAND(99),
            GET_DAUGHTER_CARD_ID_COMMAND(102),
            GET_BAUD_RATE_COMMAND(108),
            GET_DERIVED_CHANNEL_BYTES(111),
            GET_STATUS_COMMAND(114),
            GET_TRIAL_CONFIG_COMMAND(117),
            GET_CENTER_COMMAND(120),
            GET_SHIMMERNAME_COMMAND(123),
            GET_EXPID_COMMAND(GET_EXPID_COMMAND_VALUE),
            GET_MYID_COMMAND(129),
            GET_NSHIMMER_COMMAND(132),
            GET_CONFIGTIME_COMMAND(135),
            GET_DIR_COMMAND(137),
            GET_INFOMEM_COMMAND(142),
            GET_RWC_COMMAND(145),
            GET_VBATT_COMMAND(149),
            GET_CALIB_DUMP_COMMAND(154),
            GET_I2C_BATT_STATUS_COMMAND(158),
            GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND(160),
            UNRECOGNIZED(-1);

            public static final int GET_ACCEL_CALIBRATION_COMMAND_VALUE = 19;
            public static final int GET_ACCEL_SAMPLING_RATE_COMMAND_VALUE = 66;
            public static final int GET_ACCEL_SENSITIVITY_COMMAND_VALUE = 11;
            public static final int GET_ALL_CALIBRATION_COMMAND_VALUE = 44;
            public static final int GET_BAUD_RATE_COMMAND_VALUE = 108;
            public static final int GET_BLINK_LED_VALUE = 50;
            public static final int GET_BMP180_CALIBRATION_COEFFICIENTS_COMMAND_VALUE = 89;
            public static final int GET_BMP180_PRES_CALIBRATION_COMMAND_VALUE = 87;
            public static final int GET_BMP180_PRES_RESOLUTION_COMMAND_VALUE = 84;
            public static final int GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND_VALUE = 160;
            public static final int GET_BUFFER_SIZE_COMMAND_VALUE = 54;
            public static final int GET_CALIB_DUMP_COMMAND_VALUE = 154;
            public static final int GET_CENTER_COMMAND_VALUE = 120;
            public static final int GET_CONFIGTIME_COMMAND_VALUE = 135;
            public static final int GET_CONFIG_BYTE0_COMMAND_VALUE = 16;
            public static final int GET_DAUGHTER_CARD_ID_COMMAND_VALUE = 102;
            public static final int GET_DERIVED_CHANNEL_BYTES_VALUE = 111;
            public static final int GET_DIR_COMMAND_VALUE = 137;
            public static final int GET_ECG_CALIBRATION_COMMAND_VALUE = 43;
            public static final int GET_EMG_CALIBRATION_COMMAND_VALUE = 40;
            public static final int GET_EXG_REGS_COMMAND_VALUE = 99;
            public static final int GET_EXPID_COMMAND_VALUE = 126;
            public static final int GET_FW_VERSION_COMMAND_VALUE = 46;
            public static final int GET_GSR_RANGE_COMMAND_VALUE = 35;
            public static final int GET_GYRO_CALIBRATION_COMMAND_VALUE = 22;
            public static final int GET_I2C_BATT_STATUS_COMMAND_VALUE = 158;
            public static final int GET_INFOMEM_COMMAND_VALUE = 142;
            public static final int GET_INTERNAL_EXP_POWER_ENABLE_COMMAND_VALUE = 96;
            public static final int GET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND_VALUE = 28;
            public static final int GET_LSM303DLHC_ACCEL_HRMODE_COMMAND_VALUE = 72;
            public static final int GET_LSM303DLHC_ACCEL_LPMODE_COMMAND_VALUE = 69;
            public static final int GET_MAG_CALIBRATION_COMMAND_VALUE = 25;
            public static final int GET_MAG_GAIN_COMMAND_VALUE = 57;
            public static final int GET_MAG_SAMPLING_RATE_COMMAND_VALUE = 60;
            public static final int GET_MPU9150_GYRO_RANGE_COMMAND_VALUE = 75;
            public static final int GET_MPU9150_MAG_SENS_ADJ_VALS_COMMAND_VALUE = 93;
            public static final int GET_MPU9150_SAMPLING_RATE_COMMAND_VALUE = 78;
            public static final int GET_MYID_COMMAND_VALUE = 129;
            public static final int GET_NSHIMMER_COMMAND_VALUE = 132;
            public static final int GET_RWC_COMMAND_VALUE = 145;
            public static final int GET_SAMPLING_RATE_COMMAND_VALUE = 3;
            public static final int GET_SHIMMERNAME_COMMAND_VALUE = 123;
            public static final int GET_SHIMMER_VERSION_COMMAND_NEW_VALUE = 63;
            public static final int GET_SHIMMER_VERSION_COMMAND_VALUE = 36;
            public static final int GET_STATUS_COMMAND_VALUE = 114;
            public static final int GET_TRIAL_CONFIG_COMMAND_VALUE = 117;
            public static final int GET_UNIQUE_SERIAL_COMMAND_VALUE = 62;
            public static final int GET_VBATT_COMMAND_VALUE = 149;
            public static final int INQUIRY_COMMAND_VALUE = 1;
            public static final int NOT_USED_GET_VALUE = 0;
            private static final Internal.EnumLiteMap<InstructionsGet> internalValueMap = new Internal.EnumLiteMap<InstructionsGet>() { // from class: com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet.1
                public InstructionsGet findValueByNumber(int i) {
                    return InstructionsGet.forNumber(i);
                }
            };
            private static final InstructionsGet[] VALUES = values();
            private final int value;

            InstructionsGet(int i) {
                this.value = i;
            }

            public static InstructionsGet forNumber(int i) {
                if (i == 0) {
                    return NOT_USED_GET;
                }
                if (i == 1) {
                    return INQUIRY_COMMAND;
                }
                if (i == 35) {
                    return GET_GSR_RANGE_COMMAND;
                }
                if (i == 36) {
                    return GET_SHIMMER_VERSION_COMMAND;
                }
                if (i == 43) {
                    return GET_ECG_CALIBRATION_COMMAND;
                }
                if (i == 44) {
                    return GET_ALL_CALIBRATION_COMMAND;
                }
                if (i == 62) {
                    return GET_UNIQUE_SERIAL_COMMAND;
                }
                if (i == 63) {
                    return GET_SHIMMER_VERSION_COMMAND_NEW;
                }
                switch (i) {
                    case 3:
                        return GET_SAMPLING_RATE_COMMAND;
                    case 11:
                        return GET_ACCEL_SENSITIVITY_COMMAND;
                    case 16:
                        return GET_CONFIG_BYTE0_COMMAND;
                    case 19:
                        return GET_ACCEL_CALIBRATION_COMMAND;
                    case 22:
                        return GET_GYRO_CALIBRATION_COMMAND;
                    case 25:
                        return GET_MAG_CALIBRATION_COMMAND;
                    case 28:
                        return GET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND;
                    case 40:
                        return GET_EMG_CALIBRATION_COMMAND;
                    case 46:
                        return GET_FW_VERSION_COMMAND;
                    case 50:
                        return GET_BLINK_LED;
                    case 54:
                        return GET_BUFFER_SIZE_COMMAND;
                    case 57:
                        return GET_MAG_GAIN_COMMAND;
                    case 60:
                        return GET_MAG_SAMPLING_RATE_COMMAND;
                    case 66:
                        return GET_ACCEL_SAMPLING_RATE_COMMAND;
                    case 69:
                        return GET_LSM303DLHC_ACCEL_LPMODE_COMMAND;
                    case 72:
                        return GET_LSM303DLHC_ACCEL_HRMODE_COMMAND;
                    case 75:
                        return GET_MPU9150_GYRO_RANGE_COMMAND;
                    case 78:
                        return GET_MPU9150_SAMPLING_RATE_COMMAND;
                    case GET_BMP180_PRES_RESOLUTION_COMMAND_VALUE:
                        return GET_BMP180_PRES_RESOLUTION_COMMAND;
                    case GET_BMP180_PRES_CALIBRATION_COMMAND_VALUE:
                        return GET_BMP180_PRES_CALIBRATION_COMMAND;
                    case GET_BMP180_CALIBRATION_COEFFICIENTS_COMMAND_VALUE:
                        return GET_BMP180_CALIBRATION_COEFFICIENTS_COMMAND;
                    case 93:
                        return GET_MPU9150_MAG_SENS_ADJ_VALS_COMMAND;
                    case GET_INTERNAL_EXP_POWER_ENABLE_COMMAND_VALUE:
                        return GET_INTERNAL_EXP_POWER_ENABLE_COMMAND;
                    case GET_EXG_REGS_COMMAND_VALUE:
                        return GET_EXG_REGS_COMMAND;
                    case 102:
                        return GET_DAUGHTER_CARD_ID_COMMAND;
                    case 108:
                        return GET_BAUD_RATE_COMMAND;
                    case 111:
                        return GET_DERIVED_CHANNEL_BYTES;
                    case 114:
                        return GET_STATUS_COMMAND;
                    case 117:
                        return GET_TRIAL_CONFIG_COMMAND;
                    case 120:
                        return GET_CENTER_COMMAND;
                    case 123:
                        return GET_SHIMMERNAME_COMMAND;
                    case GET_EXPID_COMMAND_VALUE:
                        return GET_EXPID_COMMAND;
                    case 129:
                        return GET_MYID_COMMAND;
                    case 132:
                        return GET_NSHIMMER_COMMAND;
                    case 135:
                        return GET_CONFIGTIME_COMMAND;
                    case 137:
                        return GET_DIR_COMMAND;
                    case 142:
                        return GET_INFOMEM_COMMAND;
                    case 145:
                        return GET_RWC_COMMAND;
                    case 149:
                        return GET_VBATT_COMMAND;
                    case 154:
                        return GET_CALIB_DUMP_COMMAND;
                    case 158:
                        return GET_I2C_BATT_STATUS_COMMAND;
                    case 160:
                        return GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<InstructionsGet> internalGetValueMap() {
                return internalValueMap;
            }

            @Deprecated
            public static InstructionsGet valueOf(int i) {
                return forNumber(i);
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return (Descriptors.EnumDescriptor) LiteProtocolInstructionSet.getDescriptor().getEnumTypes().get(1);
            }

            public static InstructionsGet valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
                return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }
        }

        public enum InstructionsResponse implements ProtocolMessageEnum {
            NOT_USED_RESPONSE(0),
            INQUIRY_RESPONSE(2),
            SAMPLING_RATE_RESPONSE(4),
            ACCEL_SENSITIVITY_RESPONSE(10),
            CONFIG_BYTE0_RESPONSE(15),
            ACCEL_CALIBRATION_RESPONSE(18),
            LSM303DLHC_ACCEL_CALIBRATION_RESPONSE(27),
            GYRO_CALIBRATION_RESPONSE(21),
            MAG_CALIBRATION_RESPONSE(24),
            GSR_RANGE_RESPONSE(34),
            GET_SHIMMER_VERSION_RESPONSE(37),
            EMG_CALIBRATION_RESPONSE(39),
            ECG_CALIBRATION_RESPONSE(42),
            ALL_CALIBRATION_RESPONSE(45),
            FW_VERSION_RESPONSE(47),
            BLINK_LED_RESPONSE(49),
            BUFFER_SIZE_RESPONSE(53),
            MAG_GAIN_RESPONSE(56),
            MAG_SAMPLING_RATE_RESPONSE(59),
            UNIQUE_SERIAL_RESPONSE(61),
            ACCEL_SAMPLING_RATE_RESPONSE(65),
            LSM303DLHC_ACCEL_LPMODE_RESPONSE(68),
            LSM303DLHC_ACCEL_HRMODE_RESPONSE(71),
            MPU9150_GYRO_RANGE_RESPONSE(74),
            MPU9150_SAMPLING_RATE_RESPONSE(77),
            BMP180_PRES_RESOLUTION_RESPONSE(83),
            BMP180_PRES_CALIBRATION_RESPONSE(86),
            BMP180_CALIBRATION_COEFFICIENTS_RESPONSE(88),
            MPU9150_MAG_SENS_ADJ_VALS_RESPONSE(92),
            INTERNAL_EXP_POWER_ENABLE_RESPONSE(95),
            EXG_REGS_RESPONSE(98),
            DAUGHTER_CARD_ID_RESPONSE(101),
            BAUD_RATE_RESPONSE(107),
            DERIVED_CHANNEL_BYTES_RESPONSE(110),
            STATUS_RESPONSE(113),
            TRIAL_CONFIG_RESPONSE(116),
            CENTER_RESPONSE(119),
            SHIMMERNAME_RESPONSE(122),
            EXPID_RESPONSE(125),
            MYID_RESPONSE(128),
            NSHIMMER_RESPONSE(131),
            CONFIGTIME_RESPONSE(134),
            DIR_RESPONSE(136),
            INSTREAM_CMD_RESPONSE(138),
            INFOMEM_RESPONSE(141),
            RWC_RESPONSE(144),
            VBATT_RESPONSE(148),
            RSP_CALIB_DUMP_COMMAND(153),
            RSP_I2C_BATT_STATUS_COMMAND(157),
            BMP280_CALIBRATION_COEFFICIENTS_RESPONSE(159),
            UNRECOGNIZED(-1);

            public static final int ACCEL_CALIBRATION_RESPONSE_VALUE = 18;
            public static final int ACCEL_SAMPLING_RATE_RESPONSE_VALUE = 65;
            public static final int ACCEL_SENSITIVITY_RESPONSE_VALUE = 10;
            public static final int ALL_CALIBRATION_RESPONSE_VALUE = 45;
            public static final int BAUD_RATE_RESPONSE_VALUE = 107;
            public static final int BLINK_LED_RESPONSE_VALUE = 49;
            public static final int BMP180_CALIBRATION_COEFFICIENTS_RESPONSE_VALUE = 88;
            public static final int BMP180_PRES_CALIBRATION_RESPONSE_VALUE = 86;
            public static final int BMP180_PRES_RESOLUTION_RESPONSE_VALUE = 83;
            public static final int BMP280_CALIBRATION_COEFFICIENTS_RESPONSE_VALUE = 159;
            public static final int BUFFER_SIZE_RESPONSE_VALUE = 53;
            public static final int CENTER_RESPONSE_VALUE = 119;
            public static final int CONFIGTIME_RESPONSE_VALUE = 134;
            public static final int CONFIG_BYTE0_RESPONSE_VALUE = 15;
            public static final int DAUGHTER_CARD_ID_RESPONSE_VALUE = 101;
            public static final int DERIVED_CHANNEL_BYTES_RESPONSE_VALUE = 110;
            public static final int DIR_RESPONSE_VALUE = 136;
            public static final int ECG_CALIBRATION_RESPONSE_VALUE = 42;
            public static final int EMG_CALIBRATION_RESPONSE_VALUE = 39;
            public static final int EXG_REGS_RESPONSE_VALUE = 98;
            public static final int EXPID_RESPONSE_VALUE = 125;
            public static final int FW_VERSION_RESPONSE_VALUE = 47;
            public static final int GET_SHIMMER_VERSION_RESPONSE_VALUE = 37;
            public static final int GSR_RANGE_RESPONSE_VALUE = 34;
            public static final int GYRO_CALIBRATION_RESPONSE_VALUE = 21;
            public static final int INFOMEM_RESPONSE_VALUE = 141;
            public static final int INQUIRY_RESPONSE_VALUE = 2;
            public static final int INSTREAM_CMD_RESPONSE_VALUE = 138;
            public static final int INTERNAL_EXP_POWER_ENABLE_RESPONSE_VALUE = 95;
            public static final int LSM303DLHC_ACCEL_CALIBRATION_RESPONSE_VALUE = 27;
            public static final int LSM303DLHC_ACCEL_HRMODE_RESPONSE_VALUE = 71;
            public static final int LSM303DLHC_ACCEL_LPMODE_RESPONSE_VALUE = 68;
            public static final int MAG_CALIBRATION_RESPONSE_VALUE = 24;
            public static final int MAG_GAIN_RESPONSE_VALUE = 56;
            public static final int MAG_SAMPLING_RATE_RESPONSE_VALUE = 59;
            public static final int MPU9150_GYRO_RANGE_RESPONSE_VALUE = 74;
            public static final int MPU9150_MAG_SENS_ADJ_VALS_RESPONSE_VALUE = 92;
            public static final int MPU9150_SAMPLING_RATE_RESPONSE_VALUE = 77;
            public static final int MYID_RESPONSE_VALUE = 128;
            public static final int NOT_USED_RESPONSE_VALUE = 0;
            public static final int NSHIMMER_RESPONSE_VALUE = 131;
            public static final int RSP_CALIB_DUMP_COMMAND_VALUE = 153;
            public static final int RSP_I2C_BATT_STATUS_COMMAND_VALUE = 157;
            public static final int RWC_RESPONSE_VALUE = 144;
            public static final int SAMPLING_RATE_RESPONSE_VALUE = 4;
            public static final int SHIMMERNAME_RESPONSE_VALUE = 122;
            public static final int STATUS_RESPONSE_VALUE = 113;
            public static final int TRIAL_CONFIG_RESPONSE_VALUE = 116;
            public static final int UNIQUE_SERIAL_RESPONSE_VALUE = 61;
            public static final int VBATT_RESPONSE_VALUE = 148;
            private static final Internal.EnumLiteMap<InstructionsResponse> internalValueMap = new Internal.EnumLiteMap<InstructionsResponse>() { // from class: com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsResponse.1
                public InstructionsResponse findValueByNumber(int i) {
                    return InstructionsResponse.forNumber(i);
                }
            };
            private static final InstructionsResponse[] VALUES = values();
            private final int value;

            InstructionsResponse(int i) {
                this.value = i;
            }

            public static InstructionsResponse forNumber(int i) {
                switch (i) {
                    case 0:
                        return NOT_USED_RESPONSE;
                    case 2:
                        return INQUIRY_RESPONSE;
                    case 4:
                        return SAMPLING_RATE_RESPONSE;
                    case 10:
                        return ACCEL_SENSITIVITY_RESPONSE;
                    case 15:
                        return CONFIG_BYTE0_RESPONSE;
                    case 18:
                        return ACCEL_CALIBRATION_RESPONSE;
                    case 21:
                        return GYRO_CALIBRATION_RESPONSE;
                    case 24:
                        return MAG_CALIBRATION_RESPONSE;
                    case 27:
                        return LSM303DLHC_ACCEL_CALIBRATION_RESPONSE;
                    case 34:
                        return GSR_RANGE_RESPONSE;
                    case 37:
                        return GET_SHIMMER_VERSION_RESPONSE;
                    case 39:
                        return EMG_CALIBRATION_RESPONSE;
                    case 42:
                        return ECG_CALIBRATION_RESPONSE;
                    case 45:
                        return ALL_CALIBRATION_RESPONSE;
                    case 47:
                        return FW_VERSION_RESPONSE;
                    case 49:
                        return BLINK_LED_RESPONSE;
                    case 53:
                        return BUFFER_SIZE_RESPONSE;
                    case 56:
                        return MAG_GAIN_RESPONSE;
                    case 59:
                        return MAG_SAMPLING_RATE_RESPONSE;
                    case 61:
                        return UNIQUE_SERIAL_RESPONSE;
                    case 65:
                        return ACCEL_SAMPLING_RATE_RESPONSE;
                    case 68:
                        return LSM303DLHC_ACCEL_LPMODE_RESPONSE;
                    case 71:
                        return LSM303DLHC_ACCEL_HRMODE_RESPONSE;
                    case 74:
                        return MPU9150_GYRO_RANGE_RESPONSE;
                    case 77:
                        return MPU9150_SAMPLING_RATE_RESPONSE;
                    case BMP180_PRES_RESOLUTION_RESPONSE_VALUE:
                        return BMP180_PRES_RESOLUTION_RESPONSE;
                    case BMP180_PRES_CALIBRATION_RESPONSE_VALUE:
                        return BMP180_PRES_CALIBRATION_RESPONSE;
                    case BMP180_CALIBRATION_COEFFICIENTS_RESPONSE_VALUE:
                        return BMP180_CALIBRATION_COEFFICIENTS_RESPONSE;
                    case 92:
                        return MPU9150_MAG_SENS_ADJ_VALS_RESPONSE;
                    case 95:
                        return INTERNAL_EXP_POWER_ENABLE_RESPONSE;
                    case 98:
                        return EXG_REGS_RESPONSE;
                    case 101:
                        return DAUGHTER_CARD_ID_RESPONSE;
                    case 107:
                        return BAUD_RATE_RESPONSE;
                    case 110:
                        return DERIVED_CHANNEL_BYTES_RESPONSE;
                    case 113:
                        return STATUS_RESPONSE;
                    case 116:
                        return TRIAL_CONFIG_RESPONSE;
                    case 119:
                        return CENTER_RESPONSE;
                    case 122:
                        return SHIMMERNAME_RESPONSE;
                    case 125:
                        return EXPID_RESPONSE;
                    case 128:
                        return MYID_RESPONSE;
                    case 131:
                        return NSHIMMER_RESPONSE;
                    case 134:
                        return CONFIGTIME_RESPONSE;
                    case 136:
                        return DIR_RESPONSE;
                    case 138:
                        return INSTREAM_CMD_RESPONSE;
                    case 141:
                        return INFOMEM_RESPONSE;
                    case 144:
                        return RWC_RESPONSE;
                    case 148:
                        return VBATT_RESPONSE;
                    case 153:
                        return RSP_CALIB_DUMP_COMMAND;
                    case 157:
                        return RSP_I2C_BATT_STATUS_COMMAND;
                    case 159:
                        return BMP280_CALIBRATION_COEFFICIENTS_RESPONSE;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<InstructionsResponse> internalGetValueMap() {
                return internalValueMap;
            }

            @Deprecated
            public static InstructionsResponse valueOf(int i) {
                return forNumber(i);
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return (Descriptors.EnumDescriptor) LiteProtocolInstructionSet.getDescriptor().getEnumTypes().get(2);
            }

            public static InstructionsResponse valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
                return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LiteProtocolInstructionSetOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ShimmerLiteProtocolInstructionSet.internal_static_tutorial_LiteProtocolInstructionSet_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ShimmerLiteProtocolInstructionSet.internal_static_tutorial_LiteProtocolInstructionSet_fieldAccessorTable.ensureFieldAccessorsInitialized(LiteProtocolInstructionSet.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = LiteProtocolInstructionSet.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m4544clear() {
                super.clear();
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ShimmerLiteProtocolInstructionSet.internal_static_tutorial_LiteProtocolInstructionSet_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public LiteProtocolInstructionSet m4557getDefaultInstanceForType() {
                return LiteProtocolInstructionSet.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public LiteProtocolInstructionSet m4538build() throws UninitializedMessageException {
                LiteProtocolInstructionSet liteProtocolInstructionSetM4540buildPartial = m4540buildPartial();
                if (liteProtocolInstructionSetM4540buildPartial.isInitialized()) {
                    return liteProtocolInstructionSetM4540buildPartial;
                }
                throw newUninitializedMessageException(liteProtocolInstructionSetM4540buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public LiteProtocolInstructionSet m4540buildPartial() {
                LiteProtocolInstructionSet liteProtocolInstructionSet = new LiteProtocolInstructionSet(this);
                onBuilt();
                return liteProtocolInstructionSet;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m4556clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m4568setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m4546clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m4549clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m4570setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m4536addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m4561mergeFrom(Message message) {
                if (message instanceof LiteProtocolInstructionSet) {
                    return mergeFrom((LiteProtocolInstructionSet) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(LiteProtocolInstructionSet liteProtocolInstructionSet) {
                if (liteProtocolInstructionSet == LiteProtocolInstructionSet.getDefaultInstance()) {
                    return this;
                }
                m4566mergeUnknownFields(liteProtocolInstructionSet.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.Builder m4562mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.m4527$$Nest$sfgetPARSER()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet$LiteProtocolInstructionSet r3 = (com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet$LiteProtocolInstructionSet r4 = (com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.Builder.m4562mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet$LiteProtocolInstructionSet$Builder");
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m4572setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m4566mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }
}
