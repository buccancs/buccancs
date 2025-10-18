package com.shimmerresearch.comms.radioProtocol;

public final class ShimmerLiteProtocolInstructionSet {
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_tutorial_LiteProtocolInstructionSet_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_tutorial_LiteProtocolInstructionSet_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        java.lang.String[] descriptorData = {
                "\n$src/LiteProtocolInstructionSet.proto\022\010" +
                        "tutorial\032 google/protobuf/descriptor.pro" +
                        "to\"\310(\n\032LiteProtocolInstructionSet\"\260\r\n\017In" +
                        "structionsSet\022\017\n\013DATA_PACKET\020\000\022\035\n\031SET_SA" +
                        "MPLING_RATE_COMMAND\020\005\022\026\n\022TOGGLE_LED_COMM" +
                        "AND\020\006\022\033\n\027START_STREAMING_COMMAND\020\007\022\027\n\023SE" +
                        "T_SENSORS_COMMAND\020\010\022!\n\035SET_ACCEL_SENSITI" +
                        "VITY_COMMAND\020\t\022\034\n\030SET_5V_REGULATOR_COMMA" +
                        "ND\020\014\022\024\n\020SET_PMUX_COMMAND\020\r\022\034\n\030SET_CONFIG" +
                        "_BYTE0_COMMAND\020\016\022\032\n\026STOP_STREAMING_COMMA",
                "ND\020 \022!\n\035SET_ACCEL_CALIBRATION_COMMAND\020\021\022" +
                        ",\n(SET_LSM303DLHC_ACCEL_CALIBRATION_COMM" +
                        "AND\020\032\022 \n\034SET_GYRO_CALIBRATION_COMMAND\020\024\022" +
                        "\037\n\033SET_MAG_CALIBRATION_COMMAND\020\027\022\031\n\025SET_" +
                        "GSR_RANGE_COMMAND\020!\022\037\n\033SET_EMG_CALIBRATI" +
                        "ON_COMMAND\020&\022\037\n\033SET_ECG_CALIBRATION_COMM" +
                        "AND\020)\022\021\n\rSET_BLINK_LED\0200\022\036\n\032SET_GYRO_TEM" +
                        "P_VREF_COMMAND\0203\022\033\n\027SET_BUFFER_SIZE_COMM" +
                        "AND\0204\022\030\n\024SET_MAG_GAIN_COMMAND\0207\022!\n\035SET_M" +
                        "AG_SAMPLING_RATE_COMMAND\020:\022#\n\037SET_ACCEL_",
                "SAMPLING_RATE_COMMAND\020@\022\'\n#SET_LSM303DLH" +
                        "C_ACCEL_LPMODE_COMMAND\020C\022\'\n#SET_LSM303DL" +
                        "HC_ACCEL_HRMODE_COMMAND\020F\022\"\n\036SET_MPU9150" +
                        "_GYRO_RANGE_COMMAND\020I\022%\n!SET_MPU9150_SAM" +
                        "PLING_RATE_COMMAND\020L\022&\n\"SET_BMP180_PRES_" +
                        "RESOLUTION_COMMAND\020R\022\'\n#SET_BMP180_PRES_" +
                        "CALIBRATION_COMMAND\020U\022*\n&RESET_TO_DEFAUL" +
                        "T_CONFIGURATION_COMMAND\020Z\022#\n\037RESET_CALIB" +
                        "RATION_VALUE_COMMAND\020[\022)\n%SET_INTERNAL_E" +
                        "XP_POWER_ENABLE_COMMAND\020^\022\030\n\024SET_EXG_REG",
                "S_COMMAND\020a\022\031\n\025SET_BAUD_RATE_COMMAND\020j\022\035" +
                        "\n\031SET_DERIVED_CHANNEL_BYTES\020m\022\026\n\022START_S" +
                        "DBT_COMMAND\020p\022\034\n\030SET_TRIAL_CONFIG_COMMAN" +
                        "D\020s\022\026\n\022SET_CENTER_COMMAND\020v\022\033\n\027SET_SHIMM" +
                        "ERNAME_COMMAND\020y\022\025\n\021SET_EXPID_COMMAND\020|\022" +
                        "\024\n\020SET_MYID_COMMAND\020\177\022\031\n\024SET_NSHIMMER_CO" +
                        "MMAND\020\202\001\022\033\n\026SET_CONFIGTIME_COMMAND\020\205\001\022\030\n" +
                        "\023SET_INFOMEM_COMMAND\020\214\001\022\024\n\017SET_CRC_COMMA" +
                        "ND\020\213\001\022\024\n\017SET_RWC_COMMAND\020\217\001\022\032\n\025ROUTINE_C" +
                        "OMMUNICATION\020\340\001\022\032\n\025ACK_COMMAND_PROCESSED",
                "\020\377\001\022\037\n\032START_LOGGING_ONLY_COMMAND\020\222\001\022\036\n\031" +
                        "STOP_LOGGING_ONLY_COMMAND\020\223\001\022\034\n\027TEST_CON" +
                        "NECTION_COMMAND\020\226\001\022\026\n\021STOP_SDBT_COMMAND\020" +
                        "\227\001\022\033\n\026SET_CALIB_DUMP_COMMAND\020\230\001\022\036\n\031UPD_C" +
                        "ONFIG_MEMORY_COMMAND\020\233\001\022+\n&SET_I2C_BATT_" +
                        "STATUS_FREQ_COMMAND_VALUE\020\234\001\"\257\014\n\017Instruc" +
                        "tionsGet\022\020\n\014NOT_USED_GET\020\000\022\023\n\017INQUIRY_CO" +
                        "MMAND\020\001\022\035\n\031GET_SAMPLING_RATE_COMMAND\020\003\022!" +
                        "\n\035GET_ACCEL_SENSITIVITY_COMMAND\020\013\022\034\n\030GET" +
                        "_CONFIG_BYTE0_COMMAND\020\020\022,\n(GET_LSM303DLH",
                "C_ACCEL_CALIBRATION_COMMAND\020\034\022!\n\035GET_ACC" +
                        "EL_CALIBRATION_COMMAND\020\023\022 \n\034GET_GYRO_CAL" +
                        "IBRATION_COMMAND\020\026\022\037\n\033GET_MAG_CALIBRATIO" +
                        "N_COMMAND\020\031\022\031\n\025GET_GSR_RANGE_COMMAND\020#\022\037" +
                        "\n\033GET_SHIMMER_VERSION_COMMAND\020$\022#\n\037GET_S" +
                        "HIMMER_VERSION_COMMAND_NEW\020?\022\037\n\033GET_EMG_" +
                        "CALIBRATION_COMMAND\020(\022\037\n\033GET_ECG_CALIBRA" +
                        "TION_COMMAND\020+\022\037\n\033GET_ALL_CALIBRATION_CO" +
                        "MMAND\020,\022\032\n\026GET_FW_VERSION_COMMAND\020.\022\021\n\rG" +
                        "ET_BLINK_LED\0202\022\033\n\027GET_BUFFER_SIZE_COMMAN",
                "D\0206\022\030\n\024GET_MAG_GAIN_COMMAND\0209\022!\n\035GET_MAG" +
                        "_SAMPLING_RATE_COMMAND\020<\022#\n\037GET_ACCEL_SA" +
                        "MPLING_RATE_COMMAND\020B\022\'\n#GET_LSM303DLHC_" +
                        "ACCEL_LPMODE_COMMAND\020E\022\'\n#GET_LSM303DLHC" +
                        "_ACCEL_HRMODE_COMMAND\020H\022\"\n\036GET_MPU9150_G" +
                        "YRO_RANGE_COMMAND\020K\022%\n!GET_MPU9150_SAMPL" +
                        "ING_RATE_COMMAND\020N\022&\n\"GET_BMP180_PRES_RE" +
                        "SOLUTION_COMMAND\020T\022\'\n#GET_BMP180_PRES_CA" +
                        "LIBRATION_COMMAND\020W\022/\n+GET_BMP180_CALIBR" +
                        "ATION_COEFFICIENTS_COMMAND\020Y\022)\n%GET_MPU9",
                "150_MAG_SENS_ADJ_VALS_COMMAND\020]\022)\n%GET_I" +
                        "NTERNAL_EXP_POWER_ENABLE_COMMAND\020`\022\030\n\024GE" +
                        "T_EXG_REGS_COMMAND\020c\022 \n\034GET_DAUGHTER_CAR" +
                        "D_ID_COMMAND\020f\022\031\n\025GET_BAUD_RATE_COMMAND\020" +
                        "l\022\035\n\031GET_DERIVED_CHANNEL_BYTES\020o\022\026\n\022GET_" +
                        "STATUS_COMMAND\020r\022\034\n\030GET_TRIAL_CONFIG_COM" +
                        "MAND\020u\022\026\n\022GET_CENTER_COMMAND\020x\022\033\n\027GET_SH" +
                        "IMMERNAME_COMMAND\020{\022\025\n\021GET_EXPID_COMMAND" +
                        "\020~\022\025\n\020GET_MYID_COMMAND\020\201\001\022\031\n\024GET_NSHIMME" +
                        "R_COMMAND\020\204\001\022\033\n\026GET_CONFIGTIME_COMMAND\020\207",
                "\001\022\024\n\017GET_DIR_COMMAND\020\211\001\022\030\n\023GET_INFOMEM_C" +
                        "OMMAND\020\216\001\022\024\n\017GET_RWC_COMMAND\020\221\001\022\026\n\021GET_V" +
                        "BATT_COMMAND\020\225\001\022\033\n\026GET_CALIB_DUMP_COMMAN" +
                        "D\020\232\001\022&\n!GET_I2C_BATT_STATUS_COMMAND_VALU" +
                        "E\020\236\001\0220\n+GET_BMP280_CALIBRATION_COEFFICIE" +
                        "NTS_COMMAND\020\237\001\"\210\016\n\024InstructionsResponse\022" +
                        "\025\n\021NOT_USED_RESPONSE\020\000\022\024\n\020INQUIRY_RESPON" +
                        "SE\020\002\022 \n\026SAMPLING_RATE_RESPONSE\020\004\032\004\230\265\030\002\022$" +
                        "\n\032ACCEL_SENSITIVITY_RESPONSE\020\n\032\004\230\265\030\001\022\037\n\025" +
                        "CONFIG_BYTE0_RESPONSE\020\017\032\004\230\265\030\004\022$\n\032ACCEL_C",
                "ALIBRATION_RESPONSE\020\022\032\004\230\265\030\025\022/\n%LSM303DLH" +
                        "C_ACCEL_CALIBRATION_RESPONSE\020\033\032\004\230\265\030\025\022#\n\031" +
                        "GYRO_CALIBRATION_RESPONSE\020\025\032\004\230\265\030\025\022\"\n\030MAG" +
                        "_CALIBRATION_RESPONSE\020\030\032\004\230\265\030\025\022\034\n\022GSR_RAN" +
                        "GE_RESPONSE\020\"\032\004\230\265\030\001\022&\n\034GET_SHIMMER_VERSI" +
                        "ON_RESPONSE\020%\032\004\230\265\030\001\022\"\n\030EMG_CALIBRATION_R" +
                        "ESPONSE\020\'\032\004\230\265\030\004\022\"\n\030ECG_CALIBRATION_RESPO" +
                        "NSE\020*\032\004\230\265\030\004\022\"\n\030ALL_CALIBRATION_RESPONSE\020" +
                        "-\032\004\230\265\030T\022\035\n\023FW_VERSION_RESPONSE\020/\032\004\230\265\030\006\022\034" +
                        "\n\022BLINK_LED_RESPONSE\0201\032\004\230\265\030\001\022\036\n\024BUFFER_S",
                "IZE_RESPONSE\0205\032\004\230\265\030\001\022\033\n\021MAG_GAIN_RESPONS" +
                        "E\0208\032\004\230\265\030\001\022$\n\032MAG_SAMPLING_RATE_RESPONSE\020" +
                        ";\032\004\230\265\030\001\022&\n\034ACCEL_SAMPLING_RATE_RESPONSE\020" +
                        "A\032\004\230\265\030\001\022*\n LSM303DLHC_ACCEL_LPMODE_RESPO" +
                        "NSE\020D\032\004\230\265\030\001\022*\n LSM303DLHC_ACCEL_HRMODE_R" +
                        "ESPONSE\020G\032\004\230\265\030\001\022%\n\033MPU9150_GYRO_RANGE_RE" +
                        "SPONSE\020J\032\004\230\265\030\001\022(\n\036MPU9150_SAMPLING_RATE_" +
                        "RESPONSE\020M\032\004\230\265\030\001\022)\n\037BMP180_PRES_RESOLUTI" +
                        "ON_RESPONSE\020S\032\004\230\265\030\001\022$\n BMP180_PRES_CALIB" +
                        "RATION_RESPONSE\020V\0222\n(BMP180_CALIBRATION_",
                "COEFFICIENTS_RESPONSE\020X\032\004\230\265\030\026\022&\n\"MPU9150" +
                        "_MAG_SENS_ADJ_VALS_RESPONSE\020\\\022,\n\"INTERNA" +
                        "L_EXP_POWER_ENABLE_RESPONSE\020_\032\004\230\265\030\001\022\033\n\021E" +
                        "XG_REGS_RESPONSE\020b\032\004\230\265\030\013\022#\n\031DAUGHTER_CAR" +
                        "D_ID_RESPONSE\020e\032\004\230\265\030\003\022\034\n\022BAUD_RATE_RESPO" +
                        "NSE\020k\032\004\230\265\030\001\022(\n\036DERIVED_CHANNEL_BYTES_RES" +
                        "PONSE\020n\032\004\230\265\030\003\022\031\n\017STATUS_RESPONSE\020q\032\004\230\265\030\001" +
                        "\022\037\n\025TRIAL_CONFIG_RESPONSE\020t\032\004\230\265\030\003\022\"\n\017CEN" +
                        "TER_RESPONSE\020w\032\r\230\265\030\377\377\377\377\377\377\377\377\377\001\022\'\n\024SHIMMER" +
                        "NAME_RESPONSE\020z\032\r\230\265\030\377\377\377\377\377\377\377\377\377\001\022!\n\016EXPID_",
                "RESPONSE\020}\032\r\230\265\030\377\377\377\377\377\377\377\377\377\001\022\030\n\rMYID_RESPON" +
                        "SE\020\200\001\032\004\230\265\030\001\022\034\n\021NSHIMMER_RESPONSE\020\203\001\032\004\230\265\030" +
                        "\001\022\'\n\023CONFIGTIME_RESPONSE\020\206\001\032\r\230\265\030\377\377\377\377\377\377\377\377" +
                        "\377\001\022 \n\014DIR_RESPONSE\020\210\001\032\r\230\265\030\377\377\377\377\377\377\377\377\377\001\022\032\n\025" +
                        "INSTREAM_CMD_RESPONSE\020\212\001\022$\n\020INFOMEM_RESP" +
                        "ONSE\020\215\001\032\r\230\265\030\377\377\377\377\377\377\377\377\377\001\022\027\n\014RWC_RESPONSE\020\220" +
                        "\001\032\004\230\265\030\010\022\031\n\016VBATT_RESPONSE\020\224\001\032\004\230\265\030\003\022*\n\026RS" +
                        "P_CALIB_DUMP_COMMAND\020\231\001\032\r\230\265\030\377\377\377\377\377\377\377\377\377\001\022," +
                        "\n!RSP_I2C_BATT_STATUS_COMMAND_VALUE\020\235\001\032\004" +
                        "\230\265\030\n\0223\n(BMP280_CALIBRATION_COEFFICIENTS_",
                "RESPONSE\020\240\001\032\004\230\265\030\0302:\n\rresponse_size\022!.goo" +
                        "gle.protobuf.EnumValueOptions\030\323\206\003 \001(\005Bp\n" +
                        "\'com.shimmerresearch.comms.radioProtocol" +
                        "B!ShimmerLiteProtocolInstructionSet\252\002!co" +
                        "m.shimmerresearch.radioprotocolb\006proto3"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                                com.google.protobuf.DescriptorProtos.getDescriptor(),
                        }, assigner);
        internal_static_tutorial_LiteProtocolInstructionSet_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_tutorial_LiteProtocolInstructionSet_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_tutorial_LiteProtocolInstructionSet_descriptor,
                new java.lang.String[]{});
        com.google.protobuf.ExtensionRegistry registry =
                com.google.protobuf.ExtensionRegistry.newInstance();
        registry.add(com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.responseSize);
        com.google.protobuf.Descriptors.FileDescriptor
                .internalUpdateFileDescriptor(descriptor, registry);
        com.google.protobuf.DescriptorProtos.getDescriptor();
    }

    private ShimmerLiteProtocolInstructionSet() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistryLite registry) {
        registry.add(com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.responseSize);
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions(
                (com.google.protobuf.ExtensionRegistryLite) registry);
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface LiteProtocolInstructionSetOrBuilder extends
            com.google.protobuf.MessageOrBuilder {
    }

    public static final class LiteProtocolInstructionSet extends
            com.google.protobuf.GeneratedMessageV3 implements
            LiteProtocolInstructionSetOrBuilder {
        public static final int RESPONSE_SIZE_FIELD_NUMBER = 50003;
        public static final
        com.google.protobuf.GeneratedMessage.GeneratedExtension<
                com.google.protobuf.DescriptorProtos.EnumValueOptions,
                java.lang.Integer> responseSize = com.google.protobuf.GeneratedMessage
                .newMessageScopedGeneratedExtension(
                        com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.getDefaultInstance(),
                        0,
                        java.lang.Integer.class,
                        null);
        private static final long serialVersionUID = 0L;
        private static final com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet DEFAULT_INSTANCE;
        private static final com.google.protobuf.Parser<LiteProtocolInstructionSet>
                PARSER = new com.google.protobuf.AbstractParser<LiteProtocolInstructionSet>() {
            public LiteProtocolInstructionSet parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new LiteProtocolInstructionSet(input, extensionRegistry);
            }
        };

        static {
            DEFAULT_INSTANCE = new com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet();
        }

        private byte memoizedIsInitialized = -1;

        private LiteProtocolInstructionSet(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private LiteProtocolInstructionSet() {
        }

        private LiteProtocolInstructionSet(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        default: {
                            if (!input.skipField(tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (
                    com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (
                    java.io.IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                makeExtensionsImmutable();
            }
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.internal_static_tutorial_LiteProtocolInstructionSet_descriptor;
        }

        public static com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }

        public static com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public static com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static com.google.protobuf.Parser<LiteProtocolInstructionSet> parser() {
            return PARSER;
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
        }

        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.internal_static_tutorial_LiteProtocolInstructionSet_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.class, com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.Builder.class);
        }

        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1)
                return true;
            if (isInitialized == 0)
                return false;

            memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
        }

        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1)
                return size;

            size = 0;
            memoizedSize = size;
            return size;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet)) {
                return super.equals(obj);
            }
            com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet other = (com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet) obj;

            boolean result = true;
            return result;
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<LiteProtocolInstructionSet> getParserForType() {
            return PARSER;
        }

        public com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public enum InstructionsSet
                implements com.google.protobuf.ProtocolMessageEnum {
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
            SET_I2C_BATT_STATUS_FREQ_COMMAND_VALUE(156),
            UNRECOGNIZED(-1),
            ;

            public static final int DATA_PACKET_VALUE = 0;
            public static final int SET_SAMPLING_RATE_COMMAND_VALUE = 5;
            public static final int TOGGLE_LED_COMMAND_VALUE = 6;
            public static final int START_STREAMING_COMMAND_VALUE = 7;
            public static final int SET_SENSORS_COMMAND_VALUE = 8;
            public static final int SET_ACCEL_SENSITIVITY_COMMAND_VALUE = 9;
            public static final int SET_5V_REGULATOR_COMMAND_VALUE = 12;
            public static final int SET_PMUX_COMMAND_VALUE = 13;
            public static final int SET_CONFIG_BYTE0_COMMAND_VALUE = 14;
            public static final int STOP_STREAMING_COMMAND_VALUE = 32;
            public static final int SET_ACCEL_CALIBRATION_COMMAND_VALUE = 17;
            public static final int SET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND_VALUE = 26;
            public static final int SET_GYRO_CALIBRATION_COMMAND_VALUE = 20;
            public static final int SET_MAG_CALIBRATION_COMMAND_VALUE = 23;
            public static final int SET_GSR_RANGE_COMMAND_VALUE = 33;
            public static final int SET_EMG_CALIBRATION_COMMAND_VALUE = 38;
            public static final int SET_ECG_CALIBRATION_COMMAND_VALUE = 41;
            public static final int SET_BLINK_LED_VALUE = 48;
            public static final int SET_GYRO_TEMP_VREF_COMMAND_VALUE = 51;
            public static final int SET_BUFFER_SIZE_COMMAND_VALUE = 52;
            public static final int SET_MAG_GAIN_COMMAND_VALUE = 55;
            public static final int SET_MAG_SAMPLING_RATE_COMMAND_VALUE = 58;
            public static final int SET_ACCEL_SAMPLING_RATE_COMMAND_VALUE = 64;
            public static final int SET_LSM303DLHC_ACCEL_LPMODE_COMMAND_VALUE = 67;
            public static final int SET_LSM303DLHC_ACCEL_HRMODE_COMMAND_VALUE = 70;
            public static final int SET_MPU9150_GYRO_RANGE_COMMAND_VALUE = 73;
            public static final int SET_MPU9150_SAMPLING_RATE_COMMAND_VALUE = 76;
            public static final int SET_BMP180_PRES_RESOLUTION_COMMAND_VALUE = 82;
            public static final int SET_BMP180_PRES_CALIBRATION_COMMAND_VALUE = 85;
            public static final int RESET_TO_DEFAULT_CONFIGURATION_COMMAND_VALUE = 90;
            public static final int RESET_CALIBRATION_VALUE_COMMAND_VALUE = 91;
            public static final int SET_INTERNAL_EXP_POWER_ENABLE_COMMAND_VALUE = 94;
            public static final int SET_EXG_REGS_COMMAND_VALUE = 97;
            public static final int SET_BAUD_RATE_COMMAND_VALUE = 106;
            public static final int SET_DERIVED_CHANNEL_BYTES_VALUE = 109;
            public static final int START_SDBT_COMMAND_VALUE = 112;
            public static final int SET_TRIAL_CONFIG_COMMAND_VALUE = 115;
            public static final int SET_CENTER_COMMAND_VALUE = 118;
            public static final int SET_SHIMMERNAME_COMMAND_VALUE = 121;
            public static final int SET_EXPID_COMMAND_VALUE = 124;
            public static final int SET_MYID_COMMAND_VALUE = 127;
            public static final int SET_NSHIMMER_COMMAND_VALUE = 130;
            public static final int SET_CONFIGTIME_COMMAND_VALUE = 133;
            public static final int SET_INFOMEM_COMMAND_VALUE = 140;
            public static final int SET_CRC_COMMAND_VALUE = 139;
            public static final int SET_RWC_COMMAND_VALUE = 143;
            public static final int ROUTINE_COMMUNICATION_VALUE = 224;
            public static final int ACK_COMMAND_PROCESSED_VALUE = 255;
            public static final int START_LOGGING_ONLY_COMMAND_VALUE = 146;
            public static final int STOP_LOGGING_ONLY_COMMAND_VALUE = 147;
            public static final int TEST_CONNECTION_COMMAND_VALUE = 150;
            public static final int STOP_SDBT_COMMAND_VALUE = 151;
            public static final int SET_CALIB_DUMP_COMMAND_VALUE = 152;
            public static final int UPD_CONFIG_MEMORY_COMMAND_VALUE = 155;
            public static final int SET_I2C_BATT_STATUS_FREQ_COMMAND_VALUE_VALUE = 156;
            private static final com.google.protobuf.Internal.EnumLiteMap<
                    InstructionsSet> internalValueMap =
                    new com.google.protobuf.Internal.EnumLiteMap<InstructionsSet>() {
                        public InstructionsSet findValueByNumber(int number) {
                            return InstructionsSet.forNumber(number);
                        }
                    };
            private static final InstructionsSet[] VALUES = values();
            private final int value;

            private InstructionsSet(int value) {
                this.value = value;
            }

            @java.lang.Deprecated
            public static InstructionsSet valueOf(int value) {
                return forNumber(value);
            }

            public static InstructionsSet forNumber(int value) {
                switch (value) {
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
                    case 32:
                        return STOP_STREAMING_COMMAND;
                    case 17:
                        return SET_ACCEL_CALIBRATION_COMMAND;
                    case 26:
                        return SET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND;
                    case 20:
                        return SET_GYRO_CALIBRATION_COMMAND;
                    case 23:
                        return SET_MAG_CALIBRATION_COMMAND;
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
                    case 85:
                        return SET_BMP180_PRES_CALIBRATION_COMMAND;
                    case 90:
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
                    case 140:
                        return SET_INFOMEM_COMMAND;
                    case 139:
                        return SET_CRC_COMMAND;
                    case 143:
                        return SET_RWC_COMMAND;
                    case 224:
                        return ROUTINE_COMMUNICATION;
                    case 255:
                        return ACK_COMMAND_PROCESSED;
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
                        return SET_I2C_BATT_STATUS_FREQ_COMMAND_VALUE;
                    default:
                        return null;
                }
            }

            public static com.google.protobuf.Internal.EnumLiteMap<InstructionsSet>
            internalGetValueMap() {
                return internalValueMap;
            }

            public static final com.google.protobuf.Descriptors.EnumDescriptor
            getDescriptor() {
                return com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.getDescriptor().getEnumTypes().get(0);
            }

            public static InstructionsSet valueOf(
                    com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != getDescriptor()) {
                    throw new java.lang.IllegalArgumentException(
                            "EnumValueDescriptor is not for this type.");
                }
                if (desc.getIndex() == -1) {
                    return UNRECOGNIZED;
                }
                return VALUES[desc.getIndex()];
            }

            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new java.lang.IllegalArgumentException(
                            "Can't get the number of an unknown enum value.");
                }
                return value;
            }

            public final com.google.protobuf.Descriptors.EnumValueDescriptor
            getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }

            public final com.google.protobuf.Descriptors.EnumDescriptor
            getDescriptorForType() {
                return getDescriptor();
            }

        }

        public enum InstructionsGet
                implements com.google.protobuf.ProtocolMessageEnum {
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
            GET_EXPID_COMMAND(126),
            GET_MYID_COMMAND(129),
            GET_NSHIMMER_COMMAND(132),
            GET_CONFIGTIME_COMMAND(135),
            GET_DIR_COMMAND(137),
            GET_INFOMEM_COMMAND(142),
            GET_RWC_COMMAND(145),
            GET_VBATT_COMMAND(149),
            GET_CALIB_DUMP_COMMAND(154),
            GET_I2C_BATT_STATUS_COMMAND_VALUE(158),
            GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND(159),
            UNRECOGNIZED(-1),
            ;

            public static final int NOT_USED_GET_VALUE = 0;
            public static final int INQUIRY_COMMAND_VALUE = 1;
            public static final int GET_SAMPLING_RATE_COMMAND_VALUE = 3;
            public static final int GET_ACCEL_SENSITIVITY_COMMAND_VALUE = 11;
            public static final int GET_CONFIG_BYTE0_COMMAND_VALUE = 16;
            public static final int GET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND_VALUE = 28;
            public static final int GET_ACCEL_CALIBRATION_COMMAND_VALUE = 19;
            public static final int GET_GYRO_CALIBRATION_COMMAND_VALUE = 22;
            public static final int GET_MAG_CALIBRATION_COMMAND_VALUE = 25;
            public static final int GET_GSR_RANGE_COMMAND_VALUE = 35;
            public static final int GET_SHIMMER_VERSION_COMMAND_VALUE = 36;
            public static final int GET_SHIMMER_VERSION_COMMAND_NEW_VALUE = 63;
            public static final int GET_EMG_CALIBRATION_COMMAND_VALUE = 40;
            public static final int GET_ECG_CALIBRATION_COMMAND_VALUE = 43;
            public static final int GET_ALL_CALIBRATION_COMMAND_VALUE = 44;
            public static final int GET_FW_VERSION_COMMAND_VALUE = 46;
            public static final int GET_BLINK_LED_VALUE = 50;
            public static final int GET_BUFFER_SIZE_COMMAND_VALUE = 54;
            public static final int GET_MAG_GAIN_COMMAND_VALUE = 57;
            public static final int GET_MAG_SAMPLING_RATE_COMMAND_VALUE = 60;
            public static final int GET_ACCEL_SAMPLING_RATE_COMMAND_VALUE = 66;
            public static final int GET_LSM303DLHC_ACCEL_LPMODE_COMMAND_VALUE = 69;
            public static final int GET_LSM303DLHC_ACCEL_HRMODE_COMMAND_VALUE = 72;
            public static final int GET_MPU9150_GYRO_RANGE_COMMAND_VALUE = 75;
            public static final int GET_MPU9150_SAMPLING_RATE_COMMAND_VALUE = 78;
            public static final int GET_BMP180_PRES_RESOLUTION_COMMAND_VALUE = 84;
            public static final int GET_BMP180_PRES_CALIBRATION_COMMAND_VALUE = 87;
            public static final int GET_BMP180_CALIBRATION_COEFFICIENTS_COMMAND_VALUE = 89;
            public static final int GET_MPU9150_MAG_SENS_ADJ_VALS_COMMAND_VALUE = 93;
            public static final int GET_INTERNAL_EXP_POWER_ENABLE_COMMAND_VALUE = 96;
            public static final int GET_EXG_REGS_COMMAND_VALUE = 99;
            public static final int GET_DAUGHTER_CARD_ID_COMMAND_VALUE = 102;
            public static final int GET_BAUD_RATE_COMMAND_VALUE = 108;
            public static final int GET_DERIVED_CHANNEL_BYTES_VALUE = 111;
            public static final int GET_STATUS_COMMAND_VALUE = 114;
            public static final int GET_TRIAL_CONFIG_COMMAND_VALUE = 117;
            public static final int GET_CENTER_COMMAND_VALUE = 120;
            public static final int GET_SHIMMERNAME_COMMAND_VALUE = 123;
            public static final int GET_EXPID_COMMAND_VALUE = 126;
            public static final int GET_MYID_COMMAND_VALUE = 129;
            public static final int GET_NSHIMMER_COMMAND_VALUE = 132;
            public static final int GET_CONFIGTIME_COMMAND_VALUE = 135;
            public static final int GET_DIR_COMMAND_VALUE = 137;
            public static final int GET_INFOMEM_COMMAND_VALUE = 142;
            public static final int GET_RWC_COMMAND_VALUE = 145;
            public static final int GET_VBATT_COMMAND_VALUE = 149;
            public static final int GET_CALIB_DUMP_COMMAND_VALUE = 154;
            public static final int GET_I2C_BATT_STATUS_COMMAND_VALUE_VALUE = 158;
            public static final int GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND_VALUE = 159;
            private static final com.google.protobuf.Internal.EnumLiteMap<
                    InstructionsGet> internalValueMap =
                    new com.google.protobuf.Internal.EnumLiteMap<InstructionsGet>() {
                        public InstructionsGet findValueByNumber(int number) {
                            return InstructionsGet.forNumber(number);
                        }
                    };
            private static final InstructionsGet[] VALUES = values();
            private final int value;

            private InstructionsGet(int value) {
                this.value = value;
            }

            @java.lang.Deprecated
            public static InstructionsGet valueOf(int value) {
                return forNumber(value);
            }

            public static InstructionsGet forNumber(int value) {
                switch (value) {
                    case 0:
                        return NOT_USED_GET;
                    case 1:
                        return INQUIRY_COMMAND;
                    case 3:
                        return GET_SAMPLING_RATE_COMMAND;
                    case 11:
                        return GET_ACCEL_SENSITIVITY_COMMAND;
                    case 16:
                        return GET_CONFIG_BYTE0_COMMAND;
                    case 28:
                        return GET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND;
                    case 19:
                        return GET_ACCEL_CALIBRATION_COMMAND;
                    case 22:
                        return GET_GYRO_CALIBRATION_COMMAND;
                    case 25:
                        return GET_MAG_CALIBRATION_COMMAND;
                    case 35:
                        return GET_GSR_RANGE_COMMAND;
                    case 36:
                        return GET_SHIMMER_VERSION_COMMAND;
                    case 63:
                        return GET_SHIMMER_VERSION_COMMAND_NEW;
                    case 40:
                        return GET_EMG_CALIBRATION_COMMAND;
                    case 43:
                        return GET_ECG_CALIBRATION_COMMAND;
                    case 44:
                        return GET_ALL_CALIBRATION_COMMAND;
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
                    case 84:
                        return GET_BMP180_PRES_RESOLUTION_COMMAND;
                    case 87:
                        return GET_BMP180_PRES_CALIBRATION_COMMAND;
                    case 89:
                        return GET_BMP180_CALIBRATION_COEFFICIENTS_COMMAND;
                    case 93:
                        return GET_MPU9150_MAG_SENS_ADJ_VALS_COMMAND;
                    case 96:
                        return GET_INTERNAL_EXP_POWER_ENABLE_COMMAND;
                    case 99:
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
                    case 126:
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
                        return GET_I2C_BATT_STATUS_COMMAND_VALUE;
                    case 159:
                        return GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND;
                    default:
                        return null;
                }
            }

            public static com.google.protobuf.Internal.EnumLiteMap<InstructionsGet>
            internalGetValueMap() {
                return internalValueMap;
            }

            public static final com.google.protobuf.Descriptors.EnumDescriptor
            getDescriptor() {
                return com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.getDescriptor().getEnumTypes().get(1);
            }

            public static InstructionsGet valueOf(
                    com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != getDescriptor()) {
                    throw new java.lang.IllegalArgumentException(
                            "EnumValueDescriptor is not for this type.");
                }
                if (desc.getIndex() == -1) {
                    return UNRECOGNIZED;
                }
                return VALUES[desc.getIndex()];
            }

            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new java.lang.IllegalArgumentException(
                            "Can't get the number of an unknown enum value.");
                }
                return value;
            }

            public final com.google.protobuf.Descriptors.EnumValueDescriptor
            getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }

            public final com.google.protobuf.Descriptors.EnumDescriptor
            getDescriptorForType() {
                return getDescriptor();
            }

        }

        public enum InstructionsResponse
                implements com.google.protobuf.ProtocolMessageEnum {
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
            RSP_I2C_BATT_STATUS_COMMAND_VALUE(157),
            BMP280_CALIBRATION_COEFFICIENTS_RESPONSE(160),
            UNRECOGNIZED(-1),
            ;

            public static final int NOT_USED_RESPONSE_VALUE = 0;
            public static final int INQUIRY_RESPONSE_VALUE = 2;
            public static final int SAMPLING_RATE_RESPONSE_VALUE = 4;
            public static final int ACCEL_SENSITIVITY_RESPONSE_VALUE = 10;
            public static final int CONFIG_BYTE0_RESPONSE_VALUE = 15;
            public static final int ACCEL_CALIBRATION_RESPONSE_VALUE = 18;
            public static final int LSM303DLHC_ACCEL_CALIBRATION_RESPONSE_VALUE = 27;
            public static final int GYRO_CALIBRATION_RESPONSE_VALUE = 21;
            public static final int MAG_CALIBRATION_RESPONSE_VALUE = 24;
            public static final int GSR_RANGE_RESPONSE_VALUE = 34;
            public static final int GET_SHIMMER_VERSION_RESPONSE_VALUE = 37;
            public static final int EMG_CALIBRATION_RESPONSE_VALUE = 39;
            public static final int ECG_CALIBRATION_RESPONSE_VALUE = 42;
            public static final int ALL_CALIBRATION_RESPONSE_VALUE = 45;
            public static final int FW_VERSION_RESPONSE_VALUE = 47;
            public static final int BLINK_LED_RESPONSE_VALUE = 49;
            public static final int BUFFER_SIZE_RESPONSE_VALUE = 53;
            public static final int MAG_GAIN_RESPONSE_VALUE = 56;
            public static final int MAG_SAMPLING_RATE_RESPONSE_VALUE = 59;
            public static final int ACCEL_SAMPLING_RATE_RESPONSE_VALUE = 65;
            public static final int LSM303DLHC_ACCEL_LPMODE_RESPONSE_VALUE = 68;
            public static final int LSM303DLHC_ACCEL_HRMODE_RESPONSE_VALUE = 71;
            public static final int MPU9150_GYRO_RANGE_RESPONSE_VALUE = 74;
            public static final int MPU9150_SAMPLING_RATE_RESPONSE_VALUE = 77;
            public static final int BMP180_PRES_RESOLUTION_RESPONSE_VALUE = 83;
            public static final int BMP180_PRES_CALIBRATION_RESPONSE_VALUE = 86;
            public static final int BMP180_CALIBRATION_COEFFICIENTS_RESPONSE_VALUE = 88;
            public static final int MPU9150_MAG_SENS_ADJ_VALS_RESPONSE_VALUE = 92;
            public static final int INTERNAL_EXP_POWER_ENABLE_RESPONSE_VALUE = 95;
            public static final int EXG_REGS_RESPONSE_VALUE = 98;
            public static final int DAUGHTER_CARD_ID_RESPONSE_VALUE = 101;
            public static final int BAUD_RATE_RESPONSE_VALUE = 107;
            public static final int DERIVED_CHANNEL_BYTES_RESPONSE_VALUE = 110;
            public static final int STATUS_RESPONSE_VALUE = 113;
            public static final int TRIAL_CONFIG_RESPONSE_VALUE = 116;
            public static final int CENTER_RESPONSE_VALUE = 119;
            public static final int SHIMMERNAME_RESPONSE_VALUE = 122;
            public static final int EXPID_RESPONSE_VALUE = 125;
            public static final int MYID_RESPONSE_VALUE = 128;
            public static final int NSHIMMER_RESPONSE_VALUE = 131;
            public static final int CONFIGTIME_RESPONSE_VALUE = 134;
            public static final int DIR_RESPONSE_VALUE = 136;
            public static final int INSTREAM_CMD_RESPONSE_VALUE = 138;
            public static final int INFOMEM_RESPONSE_VALUE = 141;
            public static final int RWC_RESPONSE_VALUE = 144;
            public static final int VBATT_RESPONSE_VALUE = 148;
            public static final int RSP_CALIB_DUMP_COMMAND_VALUE = 153;
            public static final int RSP_I2C_BATT_STATUS_COMMAND_VALUE_VALUE = 157;
            public static final int BMP280_CALIBRATION_COEFFICIENTS_RESPONSE_VALUE = 160;
            private static final com.google.protobuf.Internal.EnumLiteMap<
                    InstructionsResponse> internalValueMap =
                    new com.google.protobuf.Internal.EnumLiteMap<InstructionsResponse>() {
                        public InstructionsResponse findValueByNumber(int number) {
                            return InstructionsResponse.forNumber(number);
                        }
                    };
            private static final InstructionsResponse[] VALUES = values();
            private final int value;

            private InstructionsResponse(int value) {
                this.value = value;
            }

            @java.lang.Deprecated
            public static InstructionsResponse valueOf(int value) {
                return forNumber(value);
            }

            public static InstructionsResponse forNumber(int value) {
                switch (value) {
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
                    case 27:
                        return LSM303DLHC_ACCEL_CALIBRATION_RESPONSE;
                    case 21:
                        return GYRO_CALIBRATION_RESPONSE;
                    case 24:
                        return MAG_CALIBRATION_RESPONSE;
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
                    case 83:
                        return BMP180_PRES_RESOLUTION_RESPONSE;
                    case 86:
                        return BMP180_PRES_CALIBRATION_RESPONSE;
                    case 88:
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
                        return RSP_I2C_BATT_STATUS_COMMAND_VALUE;
                    case 160:
                        return BMP280_CALIBRATION_COEFFICIENTS_RESPONSE;
                    default:
                        return null;
                }
            }

            public static com.google.protobuf.Internal.EnumLiteMap<InstructionsResponse>
            internalGetValueMap() {
                return internalValueMap;
            }

            public static final com.google.protobuf.Descriptors.EnumDescriptor
            getDescriptor() {
                return com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.getDescriptor().getEnumTypes().get(2);
            }

            public static InstructionsResponse valueOf(
                    com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != getDescriptor()) {
                    throw new java.lang.IllegalArgumentException(
                            "EnumValueDescriptor is not for this type.");
                }
                if (desc.getIndex() == -1) {
                    return UNRECOGNIZED;
                }
                return VALUES[desc.getIndex()];
            }

            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new java.lang.IllegalArgumentException(
                            "Can't get the number of an unknown enum value.");
                }
                return value;
            }

            public final com.google.protobuf.Descriptors.EnumValueDescriptor
            getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }

            public final com.google.protobuf.Descriptors.EnumDescriptor
            getDescriptorForType() {
                return getDescriptor();
            }

        }

        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSetOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.internal_static_tutorial_LiteProtocolInstructionSet_descriptor;
            }

            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.internal_static_tutorial_LiteProtocolInstructionSet_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.class, com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }

            public Builder clear() {
                super.clear();
                return this;
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.internal_static_tutorial_LiteProtocolInstructionSet_descriptor;
            }

            public com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet getDefaultInstanceForType() {
                return com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.getDefaultInstance();
            }

            public com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet build() {
                com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet buildPartial() {
                com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet result = new com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet(this);
                onBuilt();
                return result;
            }

            public Builder clone() {
                return (Builder) super.clone();
            }

            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    Object value) {
                return (Builder) super.setField(field, value);
            }

            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet) {
                    return mergeFrom((com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet other) {
                if (other == com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.getDefaultInstance())
                    return this;
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (
                        com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return this;
            }

            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return this;
            }


        }
    }

}
